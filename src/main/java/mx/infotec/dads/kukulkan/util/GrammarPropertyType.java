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
package mx.infotec.dads.kukulkan.util;

import static org.apache.metamodel.schema.SuperColumnType.BINARY_TYPE;
import static org.apache.metamodel.schema.SuperColumnType.BOOLEAN_TYPE;
import static org.apache.metamodel.schema.SuperColumnType.LITERAL_TYPE;
import static org.apache.metamodel.schema.SuperColumnType.NUMBER_TYPE;
import static org.apache.metamodel.schema.SuperColumnType.TIME_TYPE;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.util.UUID;

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
    public static final ColumnType CHAR = new ColumnTypeImpl("CHAR", LITERAL_TYPE);
    public static final ColumnType VARCHAR = new ColumnTypeImpl("VARCHAR", LITERAL_TYPE);
    public static final ColumnType LONGVARCHAR = new ColumnTypeImpl("LONGVARCHAR", LITERAL_TYPE);
    public static final ColumnType CLOB = new ColumnTypeImpl("CLOB", LITERAL_TYPE, Clob.class, true);
    public static final ColumnType NCHAR = new ColumnTypeImpl("NCHAR", LITERAL_TYPE);
    public static final ColumnType NVARCHAR = new ColumnTypeImpl("NVARCHAR", LITERAL_TYPE);
    public static final ColumnType LONGNVARCHAR = new ColumnTypeImpl("LONGNVARCHAR", LITERAL_TYPE);
    public static final ColumnType NCLOB = new ColumnTypeImpl("NCLOB", LITERAL_TYPE, Clob.class, true);

    /*
     * Numbers
     */
    public static final ColumnType TINYINT = new ColumnTypeImpl("TINYINT", NUMBER_TYPE, Short.class);
    public static final ColumnType SMALLINT = new ColumnTypeImpl("SMALLINT", NUMBER_TYPE, Short.class);
    public static final ColumnType INTEGER = new ColumnTypeImpl("INTEGER", NUMBER_TYPE, Integer.class);
    public static final ColumnType BIGINT = new ColumnTypeImpl("BIGINT", NUMBER_TYPE, BigInteger.class);
    public static final ColumnType FLOAT = new ColumnTypeImpl("FLOAT", NUMBER_TYPE, Double.class);
    public static final ColumnType REAL = new ColumnTypeImpl("REAL", NUMBER_TYPE, Double.class);
    public static final ColumnType DOUBLE = new ColumnTypeImpl("DOUBLE", NUMBER_TYPE, Double.class);
    public static final ColumnType NUMERIC = new ColumnTypeImpl("NUMERIC", NUMBER_TYPE, Double.class);
    public static final ColumnType DECIMAL = new ColumnTypeImpl("DECIMAL", NUMBER_TYPE, Double.class);
    public static final ColumnType UUID = new ColumnTypeImpl("UUID", NUMBER_TYPE, UUID.class);

    /*
     * Time based
     */
    public static final ColumnType DATE = new ColumnTypeImpl("DATE", TIME_TYPE);
    public static final ColumnType TIME = new ColumnTypeImpl("TIME", TIME_TYPE);
    public static final ColumnType TIMESTAMP = new ColumnTypeImpl("TIMESTAMP", TIME_TYPE);

    /*
     * Booleans
     */
    public static final ColumnType BIT = new ColumnTypeImpl("BIT", BOOLEAN_TYPE);
    public static final ColumnType BOOLEAN = new ColumnTypeImpl("BOOLEAN", BOOLEAN_TYPE);

    /*
     * Binary types
     */
    public static final ColumnType BINARY = new ColumnTypeImpl("BINARY", BINARY_TYPE);
    public static final ColumnType VARBINARY = new ColumnTypeImpl("VARBINARY", BINARY_TYPE);
    public static final ColumnType LONGVARBINARY = new ColumnTypeImpl("LONGVARBINARY", BINARY_TYPE);
    public static final ColumnType BLOB = new ColumnTypeImpl("BLOB", BINARY_TYPE, Blob.class, true);

}
