package mx.infotec.dads.kukulkan.engine.grammar;

import java.util.Optional;

import mx.infotec.dads.kukulkan.engine.domain.core.DataModel;
import mx.infotec.dads.kukulkan.grammar.kukulkanBaseVisitor;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser;

/**
 * KukulkanGrammarVisitor implements the
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class KukulkanGrammarVisitor extends kukulkanBaseVisitor<DataModel> {

    @Override
    public DataModel visitDomainModel(kukulkanParser.DomainModelContext ctx) {
        ctx.entities.forEach(entity -> {
            System.out.println(entity.name.getText());
            Optional.ofNullable(entity.tableName).ifPresent(tableName -> System.out.println(tableName.getText()));
            entity.fields.forEach(field ->{
                System.out.println(field.id.getText());
            });; 
        });
        return visitChildren(ctx);
    }
}
