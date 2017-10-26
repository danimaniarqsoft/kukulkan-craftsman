package mx.infotec.dads.kukulkan.engine.grammar;

import static mx.infotec.dads.kukulkan.engine.grammar.SuperColumnType.BINARY_TYPE;
import static mx.infotec.dads.kukulkan.engine.grammar.SuperColumnType.BOOLEAN_TYPE;
import static mx.infotec.dads.kukulkan.engine.grammar.SuperColumnType.LITERAL_TYPE;
import static mx.infotec.dads.kukulkan.engine.grammar.SuperColumnType.NUMBER_TYPE;
import static mx.infotec.dads.kukulkan.engine.grammar.SuperColumnType.TIME_TYPE;

import java.math.BigDecimal;
import java.sql.Blob;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import mx.infotec.dads.kukulkan.grammar.kukulkanParser.DateTypesContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.FieldTypeContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.NumericTypesContext;
import mx.infotec.dads.kukulkan.util.exceptions.ApplicationException;

/**
 * GrammarPropertyMapping
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class GrammarPropertyMapping {

    private static final HashMap<String, GrammarPropertyType> map;
    static {
        map = new HashMap<>();
        /*
         * Literal
         */
        map.put("TextBlob", new GrammarPropertyTypeImpl("TextBlob", "String", LITERAL_TYPE));
        map.put("String", new GrammarPropertyTypeImpl("String", "String", LITERAL_TYPE));

        /*
         * Numbers
         */
        map.put("Integer", new GrammarPropertyTypeImpl("Integer", "Integer", NUMBER_TYPE, Integer.class));
        map.put("Long", new GrammarPropertyTypeImpl("Long", "Long", NUMBER_TYPE, Long.class));
        map.put("BigDecimal", new GrammarPropertyTypeImpl("BigDecimal", "BigDecimal", NUMBER_TYPE, BigDecimal.class));
        map.put("Float", new GrammarPropertyTypeImpl("Float", "Float", NUMBER_TYPE, Float.class));
        map.put("Double", new GrammarPropertyTypeImpl("Double", "Double", NUMBER_TYPE, Double.class));

        /*
         * Time based
         */
        map.put("Date", new GrammarPropertyTypeImpl("Date", "Date", TIME_TYPE, Date.class));
        map.put("LocalDate", new GrammarPropertyTypeImpl("LocalDate", "LocalDate", TIME_TYPE, LocalDate.class));
        map.put("ZonedDateTime", new GrammarPropertyTypeImpl("ZonedDateTime", "LocalDate", TIME_TYPE, LocalDate.class));
        map.put("Instant", new GrammarPropertyTypeImpl("Instant", "Instant", TIME_TYPE, Instant.class));

        /*
         * Booleans
         */
        map.put("Boolean", new GrammarPropertyTypeImpl("Boolean", "", BOOLEAN_TYPE));

        /*
         * Blobs
         */
        map.put("Blob", new GrammarPropertyTypeImpl("Blob", "", BINARY_TYPE, Blob.class, true));
        map.put("AnyBlob", new GrammarPropertyTypeImpl("Blob", "", BINARY_TYPE, Blob.class, true));
        map.put("ImageBlob", new GrammarPropertyTypeImpl("Blob", "", BINARY_TYPE, Blob.class, true));

    }

    private GrammarPropertyMapping() {
    }

    public static GrammarPropertyType getPropertyType(String property) {
        return map.get(property);
    }

    public static GrammarPropertyType getPropertyType(FieldTypeContext type) {
        System.out.println(type.getText());
        Optional<GrammarPropertyType> optional = Optional.of(map.get(extractPropertyType(type)));
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ApplicationException("Property Not Found" + type.getText());
        }
    }

    public static String extractPropertyType(FieldTypeContext type) {
        if (type.stringFieldType() != null) {
            return type.stringFieldType().name.getText();
        } else if (type.numericFieldType() != null) {
            return getNumericType(type.numericFieldType().numericTypes());
        } else if (type.booleanFieldType() != null) {
            return type.booleanFieldType().name.getText();
        } else if (type.dateFieldType() != null) {
            return getDateType(type.dateFieldType().dateTypes());
        } else if (type.blobFieldType() != null) {
            return type.blobFieldType().name.getText();
        } else {
            throw new ApplicationException("Property type not found for: " + type.getText());
        }
    }

    public static String getNumericType(NumericTypesContext numericTypes) {
        if (numericTypes.LONG() != null) {
            return numericTypes.LONG().getText();
        } else if (numericTypes.FLOAT() != null) {
            return numericTypes.FLOAT().getText();
        } else if (numericTypes.INTEGER() != null) {
            return numericTypes.INTEGER().getText();
        } else if (numericTypes.BIG_DECIMAL() != null) {
            return numericTypes.BIG_DECIMAL().getText();
        } else if (numericTypes.DOUBLE() != null) {
            return numericTypes.DOUBLE().getText();
        } else {
            throw new ApplicationException("Numeric Type Not Found for: " + numericTypes.getText());
        }
    }

    public static String getDateType(DateTypesContext dataTypes) {
        if (dataTypes.DATE() != null) {
            return dataTypes.DATE().getText();
        } else if (dataTypes.INSTANT() != null) {
            return dataTypes.INSTANT().getText();
        } else if (dataTypes.LOCAL_DATE() != null) {
            return dataTypes.LOCAL_DATE().getText();
        } else if (dataTypes.ZONED_DATETIME() != null) {
            return dataTypes.ZONED_DATETIME().getText();
        } else {
            throw new ApplicationException("Date Type Not Found for: " + dataTypes.getText());
        }
    }
}