package mx.infotec.dads.kukulkan.engine.grammar;

import static mx.infotec.dads.kukulkan.util.DataBaseMapping.fillModelMetaData;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createDefaultPrimaryKey;

import java.util.ArrayList;
import java.util.List;

import mx.infotec.dads.kukulkan.engine.domain.core.DataModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaProperty;
import mx.infotec.dads.kukulkan.grammar.kukulkanBaseVisitor;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.EntityContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.EntityFieldContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.FieldTypeContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.NumericTypesContext;
import mx.infotec.dads.kukulkan.util.InflectorProcessor;
import mx.infotec.dads.kukulkan.util.SchemaPropertiesParser;

/**
 * KukulkanGrammarVisitor implements the
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class KukulkanGrammarVisitor extends kukulkanBaseVisitor<List<DataModelElement>> {

    @Override
    public List<DataModelElement> visitDomainModel(kukulkanParser.DomainModelContext ctx) {
        List<DataModelElement> dmeList = new ArrayList<>();
        ctx.entities.forEach(entity -> {
            DataModelElement dme = DataModelElement.createOrderedDataModel();
            processMetaData(dmeList, entity, dme);
            processProperties(dme, entity.fields);
        });
        return dmeList;
    }

    public void processMetaData(List<DataModelElement> dmeList, EntityContext entity, DataModelElement dme) {
        String singularName = InflectorProcessor.getInstance().singularize(entity.name.getText());
        dme.setTableName(entity.name.getText().toUpperCase());
        dme.setName(entity.name.getText());
        dme.setCamelCaseFormat(SchemaPropertiesParser.parseToPropertyName(singularName));
        dme.setCamelCasePluralFormat(InflectorProcessor.getInstance().pluralize(dme.getCamelCaseFormat()));
        dme.setPrimaryKey(createDefaultPrimaryKey());
        dmeList.add(dme);
    }

    private static void processProperties(DataModelElement dme, List<EntityFieldContext> fields) {
        fields.forEach(field -> {
            String propertyName = SchemaPropertiesParser.parseToPropertyName(field.id.getText());
            String propertyType = extractPropertyType(field.type);
            JavaProperty javaProperty = JavaProperty.builder()
                    .withName(propertyName)
                    .withType(propertyType)
                    .withColumnName(propertyName)
                    .withColumnType(propertyType)
                    .withQualifiedName(getCannonicalName(propertyType))
                    .isNullable(true)
                    .isPrimaryKey(false)
                    .isIndexed(false)
                    .addType(field.type)
                    .build();
            dme.addProperty(javaProperty);
            GrammarMapping.addImports(dme.getImports(), javaProperty);
            fillModelMetaData(dme, javaProperty);
        });

    }

    private static String getCannonicalName(String propertyType) {
        // TODO Auto-generated method stub
        return null;
    }

    private static String extractPropertyType(FieldTypeContext type) {
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

    private static String getNumericType(NumericTypesContext numericTypes) {
        return null;
    }
}
