package mx.infotec.dads.kukulkan.engine.grammar;

import static mx.infotec.dads.kukulkan.engine.grammar.SuperColumnType.BOOLEAN_TYPE;
import static mx.infotec.dads.kukulkan.engine.grammar.SuperColumnType.LITERAL_TYPE;
import static mx.infotec.dads.kukulkan.engine.grammar.SuperColumnType.NUMBER_TYPE;
import static mx.infotec.dads.kukulkan.engine.grammar.SuperColumnType.TIME_TYPE;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

import mx.infotec.dads.kukulkan.grammar.kukulkanParser.FieldTypeContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.NumericTypesContext;

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

        /*
         * Booleans
         */
        map.put("Boolean", new GrammarPropertyTypeImpl("Boolean", "", BOOLEAN_TYPE));
    }

    private GrammarPropertyMapping() {
    }

    public static GrammarPropertyType getPropertyType(String property) {
        return map.get(property);
    }

    public static GrammarPropertyType getPropertyType(FieldTypeContext type) {
        return map.get(extractPropertyType(type));
    }

    public static String extractPropertyType(FieldTypeContext type) {
        if (type.stringFieldType() != null) {
            return type.stringFieldType().name.getText();
        } else if (type.numericFieldType() != null) {
            return getNumericType(type.numericFieldType().numericTypes());
        } else if (type.booleanFieldType() != null) {
            return type.booleanFieldType().name.getText();
        } else if (type.dateFieldType() != null) {
            return type.dateFieldType().type.getText();
        } else if (type.blobFieldType() != null) {
            return type.blobFieldType().name.getText();
        } else {
        }
        return null;
    }

    public static String getNumericType(NumericTypesContext numericTypes) {
        return null;
    }
}
