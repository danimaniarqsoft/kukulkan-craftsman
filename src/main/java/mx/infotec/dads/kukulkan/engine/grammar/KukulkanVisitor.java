package mx.infotec.dads.kukulkan.engine.grammar;

import static mx.infotec.dads.kukulkan.engine.grammar.GrammarUtil.addContentType;
import static mx.infotec.dads.kukulkan.engine.grammar.GrammarUtil.addMetaData;
import static mx.infotec.dads.kukulkan.engine.grammar.GrammarUtil.createJavaProperty;

import java.util.ArrayList;

import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaProperty;
import mx.infotec.dads.kukulkan.grammar.kukulkanBaseVisitor;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.EntityContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.EntityFieldContext;
import mx.infotec.dads.kukulkan.util.DataBaseMapping;

/**
 * KukulkanGrammarVisitor implements the
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class KukulkanVisitor extends kukulkanBaseVisitor<VisitorContext> {

    private final VisitorContext vctx = new VisitorContext(new ArrayList<DomainModelElement>());
    private DomainModelElement dme = null;

    @Override
    public VisitorContext visitEntity(EntityContext ctx) {
        dme = DomainModelElement.createDomainModelElement();
        addMetaData(ctx, dme);
        getVctx().getElements().add(dme);
        return super.visitEntity(ctx);
    }

    @Override
    public VisitorContext visitEntityField(EntityFieldContext ctx) {
        String propertyName = ctx.id.getText();
        GrammarPropertyType propertyType = GrammarPropertyMapping.getPropertyType(ctx.type);
        JavaProperty javaProperty = createJavaProperty(ctx, propertyName, propertyType);
        dme.addProperty(javaProperty);
        addContentType(dme, propertyName, propertyType);
        GrammarMapping.addImports(dme.getImports(), javaProperty);
        DataBaseMapping.fillModelMetaData(dme, javaProperty);
        return super.visitEntityField(ctx);
    }

    public VisitorContext getVctx() {
        return vctx;
    }
}
