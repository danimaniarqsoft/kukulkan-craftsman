package mx.infotec.dads.kukulkan.engine.grammar;

import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createDefaultPrimaryKey;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
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
            DomainModelElement dme = DomainModelElement.createDataModel();
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
            GrammarPropertyType propertyType = GrammarPropertyMapping.getPropertyType(field.type);
            JavaProperty javaProperty = createJavaProperty(field, propertyName, propertyType);
            dme.addProperty(javaProperty);
            addContentType(dme, propertyName, propertyType);
            GrammarMapping.addImports(dme.getImports(), javaProperty);
            DataBaseMapping.fillModelMetaData(dme,javaProperty);
        });
    }

    public static void addContentType(DomainModelElement dme, String propertyName, GrammarPropertyType propertyType) {
        if(propertyType.isBinary()){
            dme.addProperty(createContentTypeProperty(propertyName));
        }
    }

    public static JavaProperty createJavaProperty(EntityFieldContext field, String propertyName,
            GrammarPropertyType propertyType) {
        return JavaProperty.builder()
                .withName(propertyName)
                .withType(propertyType.getJavaName())
                .withColumnName(propertyName)
                .withColumnType(propertyType.getGrammarName())
                .withQualifiedName(propertyType.getJavaQualifiedName())
                .isNullable(true)
                .isPrimaryKey(false)
                .isIndexed(false)
                .isLocalDate(propertyType.getJavaEquivalentClass().equals(LocalDate.class))
                .isZoneDateTime(propertyType.getJavaEquivalentClass().equals(ZonedDateTime.class))
                .isInstance(propertyType.getJavaEquivalentClass().equals(Instant.class))
                // add if it is zoneDateTime or dateTime or Instant or other.
                .isLargeObject(propertyType.isLargeObject())
                .addType(field.type)
                .build();
    }
    
    public static JavaProperty createContentTypeProperty(String propertyName){
        return JavaProperty.builder()
                .withName(propertyName+"ContentType")
                .withType("String")
                .withColumnName(propertyName+"ContentType")
                .withColumnType("TextBlob")
                .withQualifiedName("java.lang.String")
                .isNullable(true)
                .isPrimaryKey(false)
                .isIndexed(false)
                .isLargeObject(false)
                .isLiteral(true)
                .build();
    }
}
