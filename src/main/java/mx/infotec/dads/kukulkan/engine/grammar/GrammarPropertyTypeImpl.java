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

import org.apache.metamodel.schema.ColumnTypeImpl;
import org.apache.metamodel.schema.SuperColumnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GrammarPropertyTypeImpl
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class GrammarPropertyTypeImpl implements GrammarPropertyType {

    private static final long serialVersionUID = 1L;

    public static final Logger logger = LoggerFactory.getLogger(ColumnTypeImpl.class);

    private final String _name;
    private final SuperColumnType _superColumnType;
    private final Class<?> _javaType;
    private final boolean _largeObject;

    public GrammarPropertyTypeImpl(String name, SuperColumnType superColumnType) {
        this(name, superColumnType, null);
    }

    public GrammarPropertyTypeImpl(String name, SuperColumnType superColumnType, Class<?> javaType) {
        this(name, superColumnType, javaType, false);
    }

    public GrammarPropertyTypeImpl(String name, SuperColumnType superColumnType, Class<?> javaType,
            boolean largeObject) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (superColumnType == null) {
            throw new IllegalArgumentException("SuperColumnType cannot be null");
        }
        _name = name;
        _superColumnType = superColumnType;
        if (javaType == null) {
            _javaType = superColumnType.getJavaEquivalentClass();
        } else {
            _javaType = javaType;
        }
        _largeObject = largeObject;
    }

    @Override
    public String getName() {
        return _name;
    }

    public boolean isBoolean() {
        return _superColumnType == SuperColumnType.BOOLEAN_TYPE;
    }

    public boolean isBinary() {
        return _superColumnType == SuperColumnType.BINARY_TYPE;
    }

    public boolean isNumber() {
        return _superColumnType == SuperColumnType.NUMBER_TYPE;
    }

    public boolean isTimeBased() {
        return _superColumnType == SuperColumnType.TIME_TYPE;
    }

    public boolean isLiteral() {
        return _superColumnType == SuperColumnType.LITERAL_TYPE;
    }

    public boolean isLargeObject() {
        return _largeObject;
    }

    public Class<?> getJavaEquivalentClass() {
        return _javaType;
    }

    public SuperColumnType getSuperType() {
        return _superColumnType;
    }

}
