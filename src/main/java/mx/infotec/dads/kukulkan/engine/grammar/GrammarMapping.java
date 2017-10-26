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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import mx.infotec.dads.kukulkan.engine.domain.core.DataModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.DataModelGroup;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaProperty;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.BlobTypesContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.DateTypesContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.DomainModelContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.FieldTypeContext;

/**
 * DataMapping utility class
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class GrammarMapping {

    private GrammarMapping() {

    }

    /**
     * Create a DataModelGroup Class
     * 
     * @param dataContext
     * @return DataModelGroup
     */
    public static DataModelGroup createDefaultDataModelGroup(DomainModelContext dmc, KukulkanSemanticAnalyzer visitor) {
        DataModelGroup dmg = new DataModelGroup();
        dmg.setName("");
        dmg.setDescription("Default package");
        dmg.setBriefDescription("Default package");
        dmg.setDataModelElements(new ArrayList<>());
        List<DataModelElement> dmeList = new ArrayList<>();
        createDataModelElement(dmc, visitor, dmeList);
        dmg.setDataModelElements(dmeList);
        return dmg;
    }

    /**
     * createDataModelElement is used for map the KukulkanGrammar to
     * DataModelElement.
     * 
     * @param dmc
     * @param visitor
     * @param dmeList
     */
    private static void createDataModelElement(DomainModelContext dmc, KukulkanSemanticAnalyzer visitor,
            List<DataModelElement> dmeList) {
        dmeList.addAll(visitor.visit(dmc));
    }

    public static boolean addImports(Collection<String> imports, JavaProperty property) {
        if (property.isBigDecimal() || property.isLocalDate() || property.isZoneDateTime() || property.isInstant()) {
            imports.add(property.getQualifiedName());
            return true;
        } else {
            return false;
        }
    }

    public static void addType(JavaProperty javaProperty, FieldTypeContext type) {
        if (type.booleanFieldType() != null) {
            javaProperty.setBoolean(true);
        } else if (type.dateFieldType() != null) {
            setKindOfDateType(javaProperty, type.dateFieldType().type);
        } else if (type.blobFieldType() != null) {
            setKindOfBlobType(javaProperty, type.blobFieldType().blobTypes());
            javaProperty.setBlob(true);
        } else if (type.numericFieldType() != null) {
            javaProperty.setNumber(true);
        } else if (type.stringFieldType() != null) {
            javaProperty.setLiteral(true);
        }
    }

    private static void setKindOfBlobType(JavaProperty property, BlobTypesContext ctx) {
        if (ctx.BLOB() != null || ctx.ANY_BLOB() != null || ctx.IMAGE_BLOB() != null) {
            property.setBlob(true);
        } else if (ctx.TEXT_BLOB() != null) {
            property.setClob(true);
        }
    }

    private static void setKindOfDateType(JavaProperty property, DateTypesContext type) {
        property.setTime(true);
        if (type.ZONED_DATETIME() != null) {
            property.setZoneDateTime(true);
        } else if (type.DATE() != null || type.LOCAL_DATE() != null) {
            property.setLocalDate(true);
        }else if (type.INSTANT() != null) {
            property.setInstant(true);
        }
    }

    /**
     * createSingleDataModelGroupList
     * 
     * @param visitor
     * @param tablesToProcess
     * @return
     * @throws IOException
     */
    public static List<DataModelGroup> createSingleDataModelGroupList(KukulkanSemanticAnalyzer visitor,
            List<String> tablesToProcess) throws IOException {
        String program = "src/test/resources/grammar/test." + "3k";
        System.out.println("Interpreting file " + program);
        DomainModelContext tree = GrammarUtil.getDomainModelContext(program);
        List<DataModelGroup> dataModelGroupList = new ArrayList<>();
        dataModelGroupList.add(createDefaultDataModelGroup(tree, visitor));
        return dataModelGroupList;
    }

    public static Optional<GrammarPropertyType> getPropertyType(String type) {
        return Optional.of(GrammarPropertyMapping.getPropertyType(type));
    }
}
