/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mx.infotec.dads.kukulkan.engine.grammar;

import static org.apache.metamodel.schema.SuperColumnType.BINARY_TYPE;
import static org.apache.metamodel.schema.SuperColumnType.BOOLEAN_TYPE;
import static org.apache.metamodel.schema.SuperColumnType.LITERAL_TYPE;
import static org.apache.metamodel.schema.SuperColumnType.NUMBER_TYPE;
import static org.apache.metamodel.schema.SuperColumnType.TIME_TYPE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;

import org.apache.metamodel.schema.ColumnType;
import org.apache.metamodel.schema.ColumnTypeImpl;
import org.apache.metamodel.util.HasName;

/**
 * GrammarPropertyType
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public interface GrammarPropertyType extends HasName, Serializable {

    /*
     * Literal
     */
    public static final ColumnType CHAR = new ColumnTypeImpl("TextBlob", LITERAL_TYPE);
    public static final ColumnType VARCHAR = new ColumnTypeImpl("String", LITERAL_TYPE);
    /*
     * Numbers
     */
    public static final ColumnType TINYINT = new ColumnTypeImpl("Integer", NUMBER_TYPE, Integer.class);
    public static final ColumnType SMALLINT = new ColumnTypeImpl("Long", NUMBER_TYPE, Long.class);
    public static final ColumnType INTEGER = new ColumnTypeImpl("BigDecimal", NUMBER_TYPE, BigDecimal.class);
    public static final ColumnType BIGINT = new ColumnTypeImpl("Float", NUMBER_TYPE, Float.class);
    public static final ColumnType FLOAT = new ColumnTypeImpl("Double", NUMBER_TYPE, Double.class);

    /*
     * Time based
     */
    public static final ColumnType DATE = new ColumnTypeImpl("Date", TIME_TYPE, Date.class);
    public static final ColumnType LOCAL_DATE = new ColumnTypeImpl("LocalDate", TIME_TYPE, LocalDate.class);
    public static final ColumnType ZONED_DATETIME = new ColumnTypeImpl("ZonedDateTime", TIME_TYPE, LocalDate.class);

    /*
     * Booleans
     */
    public static final ColumnType BOOLEAN = new ColumnTypeImpl("Boolean", BOOLEAN_TYPE);

    /*
     * Binary types
     */
    public static final ColumnType BLOB = new ColumnTypeImpl("Blob", BINARY_TYPE, Blob.class, true);
    public static final ColumnType ANY_BLOB = new ColumnTypeImpl("AnyBlob", BINARY_TYPE, Blob.class, true);
    public static final ColumnType IMAGE_BLOB = new ColumnTypeImpl("ImageBlob", BINARY_TYPE, Blob.class, true);


}
