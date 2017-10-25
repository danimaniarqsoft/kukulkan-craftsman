package mx.infotec.dads.kukulkan.engine.grammar;

/**
 * GrammarConstants, It have all the constants represented in the grammar
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class GrammarConstants {

    /**
     * Numeric Types
     */
    public static final String INTEGER = "Integer";
    public static final String LONG = "Long";
    public static final String BIG_DECIMAL = "BigDecimal";
    public static final String FLOAT = "Float";
    public static final String DOUBLE = "Double";

    /**
     * Boolean Types
     */
    public static final String BOOLEAN = "Boolean";

    /**
     * Date Types
     */
    public static final String DATE = "Date";
    public static final String LOCAL_DATE = "LocalDate";
    public static final String ZONED_DATETIME = "ZonedDateTime";
    public static final String INSTANT = "Instant";

    /**
     * Blob Types
     */
    public static final String BLOB = "Blob";
    public static final String ANY_BLOB = "AnyBlob";
    public static final String IMAGE_BLOB = "ImageBlob";

    /**
     * Literal Types
     */
    public static final String TEXT_BLOB = "TextBlob";
    public static final String STRING = "String";

    private GrammarConstants() {

    }

}
