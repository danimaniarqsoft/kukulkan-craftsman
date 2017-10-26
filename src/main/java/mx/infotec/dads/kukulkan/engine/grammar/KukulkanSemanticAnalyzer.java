package mx.infotec.dads.kukulkan.engine.grammar;

import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createDefaultPrimaryKey;

import java.util.ArrayList;
import java.util.List;

import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaProperty;
import mx.infotec.dads.kukulkan.grammar.kukulkanBaseVisitor;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.EntityContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.EntityFieldContext;
import mx.infotec.dads.kukulkan.util.DataBaseMapping;
import mx.infotec.dads.kukulkan.util.InflectorProcessor;
import mx.infotec.dads.kukulkan.util.SchemaPropertiesParser;

/**
 * KukulkanGrammarVisitor implements the
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class KukulkanSemanticAnalyzer extends kukulkanBaseVisitor<List<DomainModelElement>> {

    @Override
    public List<DomainModelElement> visitDomainModel(kukulkanParser.DomainModelContext ctx) {
        List<DomainModelElement> dmeList = new ArrayList<>();
        ctx.entities.forEach(entity -> {
            DomainModelElement dme = DomainModelElement.createOrderedDataModel();
            processMetaData(dmeList, entity, dme);
            processProperties(dme, entity.fields);
        });
        return dmeList;
    }

    public void processMetaData(List<DomainModelElement> dmeList, EntityContext entity, DomainModelElement dme) {
        String singularName = InflectorProcessor.getInstance().singularize(entity.name.getText());
        dme.setTableName(entity.name.getText().toUpperCase());
        dme.setName(entity.name.getText());
        dme.setCamelCaseFormat(SchemaPropertiesParser.parseToPropertyName(singularName));
        dme.setCamelCasePluralFormat(InflectorProcessor.getInstance().pluralize(dme.getCamelCaseFormat()));
        dme.setPrimaryKey(createDefaultPrimaryKey());
        dmeList.add(dme);
    }

    private static void processProperties(DomainModelElement dme, List<EntityFieldContext> fields) {
        fields.forEach(field -> {
            String propertyName = field.id.getText();
            GrammarPropertyType grammarPropertyType = GrammarPropertyMapping.getPropertyType(field.type);
            JavaProperty javaProperty = JavaProperty.builder()
                    .withName(propertyName)
                    .withType(grammarPropertyType.getJavaName())
                    .withColumnName(propertyName)
                    .withColumnType(grammarPropertyType.getGrammarName())
                    .withQualifiedName(grammarPropertyType.getJavaQualifiedName())
                    .isNullable(true)
                    .isPrimaryKey(false)
                    .isIndexed(false)
                    .addType(field.type)
                    .build();
            dme.addProperty(javaProperty);
            GrammarMapping.addImports(dme.getImports(), javaProperty);
            DataBaseMapping.fillModelMetaData(dme,javaProperty);
        });
    }
}
