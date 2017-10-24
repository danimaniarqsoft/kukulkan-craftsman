package mx.infotec.dads.kukulkan.engine.grammar;

import java.util.ArrayList;
import java.util.List;

import mx.infotec.dads.kukulkan.engine.domain.core.DataModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.PrimaryKey;
import mx.infotec.dads.kukulkan.grammar.kukulkanBaseVisitor;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser;
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
            String singularName = InflectorProcessor.getInstance().singularize(entity.name.getText());
            dme.setTableName(entity.name.getText());
            dme.setName(entity.name.getText());
            dme.setCamelCaseFormat(SchemaPropertiesParser.parseToPropertyName(singularName));
            dme.setCamelCasePluralFormat(InflectorProcessor.getInstance().pluralize(dme.getCamelCaseFormat()));
            PrimaryKey pk = PrimaryKey.createOrderedDataModel();
            pk.setType("Long");
            pk.setName(SchemaPropertiesParser.parseToPropertyName("id"));
            pk.setQualifiedLabel("java.lang.Long");
            pk.setComposed(false);
            dmeList.add(dme);
//            Optional.ofNullable(entity.tableName).ifPresent(tableName -> System.out.println(tableName.getText()));
//            entity.fields.forEach(field ->{
//                System.out.println(field.id.getText());
//            });; 
        });
        return dmeList;
    }
}
