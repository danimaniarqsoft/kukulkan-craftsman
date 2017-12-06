package mx.infotec.dads.kukulkan.engine.grammar;

import static mx.infotec.dads.kukulkan.engine.grammar.GrammarPropertyMapping.getDateType;
import static mx.infotec.dads.kukulkan.engine.grammar.GrammarPropertyMapping.getNumericType;
import static mx.infotec.dads.kukulkan.engine.grammar.GrammarUtil.addContentType;
import static mx.infotec.dads.kukulkan.engine.grammar.GrammarUtil.addMetaData;
import static mx.infotec.dads.kukulkan.engine.grammar.GrammarUtil.createJavaProperty;

import java.util.ArrayList;
import java.util.Optional;

import mx.infotec.dads.kukulkan.engine.domain.core.Constraint;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaProperty;
import mx.infotec.dads.kukulkan.grammar.kukulkanBaseVisitor;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.BlobFieldTypeContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.BooleanFieldTypeContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.DateFieldTypeContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.EntityContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.EntityFieldContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.NumericFieldTypeContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.RequiredValidatorContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.StringFieldTypeContext;
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
    private EntityFieldContext efc = null;
    private String propertyName = null;
    private JavaProperty javaProperty = null;
    private Constraint constraint =null;

    @Override
    public VisitorContext visitEntity(EntityContext ctx) {
        dme = DomainModelElement.createDomainModelElement();
        addMetaData(ctx, dme);
        getVctx().getElements().add(dme);
        return super.visitEntity(ctx);
    }

    @Override
    public VisitorContext visitEntityField(EntityFieldContext ctx) {
        efc = ctx;
        propertyName = ctx.id.getText();
        constraint = new Constraint();
        super.visitEntityField(ctx);
        javaProperty.setConstraint(constraint);
        return super.visitEntityField(ctx);
    }

    @Override
    public VisitorContext visitStringFieldType(StringFieldTypeContext ctx) {
        Optional<GrammarPropertyType> optional = Optional.of(GrammarPropertyMapping.getMap().get(ctx.name.getText()));
        processFieldType(optional);
        return super.visitStringFieldType(ctx);
    }

    @Override
    public VisitorContext visitDateFieldType(DateFieldTypeContext ctx) {
        Optional<GrammarPropertyType> optional = Optional
                .of(GrammarPropertyMapping.getMap().get(getDateType(ctx.dateTypes())));
        processFieldType(optional);
        return super.visitDateFieldType(ctx);
    }

    @Override
    public VisitorContext visitNumericFieldType(NumericFieldTypeContext ctx) {
        Optional<GrammarPropertyType> optional = Optional
                .of(GrammarPropertyMapping.getMap().get(getNumericType(ctx.numericTypes())));
        processFieldType(optional);
        return super.visitNumericFieldType(ctx);
    }


    @Override
    public VisitorContext visitBlobFieldType(BlobFieldTypeContext ctx) {
        Optional<GrammarPropertyType> optional = Optional.of(GrammarPropertyMapping.getMap().get(ctx.name.getText()));
        processFieldType(optional);
        return super.visitBlobFieldType(ctx);
    }

    @Override
    public VisitorContext visitBooleanFieldType(BooleanFieldTypeContext ctx) {
        Optional<GrammarPropertyType> optional = Optional.of(GrammarPropertyMapping.getMap().get(ctx.name.getText()));
        processFieldType(optional);
        return super.visitBooleanFieldType(ctx);
    }

    @Override
    public VisitorContext visitStringValidators(kukulkanParser.StringValidatorsContext ctx) {
//        if (ctx.required != null) {
//            constraint.setNullable(false);
//        }
//        if (ctx.maxLenght != null) {
//            constraint.setMaxValue(ctx.maxLenght.NUMERIC_VALUE().getText());
//        }
//        if (ctx.minLenght != null) {
//            constraint.setMinValue(ctx.minLenght.NUMERIC_VALUE().getText());
//        }
//        if (ctx.pattern != null) {
//            constraint.setPatter(ctx.pattern.NUMERIC_VALUE().getText());
//        }
//        javaProperty.setConstraint(constraint);
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitNumericValidators(kukulkanParser.NumericValidatorsContext ctx) {
//        if (ctx.required != null) {
//            constraint.setNullable(false);
//        }
//        if (ctx.minValue != null) {
//            constraint.setMinValue(ctx.minValue.NUMERIC_VALUE().getText());
//        }
//        if (ctx.maxValue != null) {
//            constraint.setMaxValue(ctx.maxValue.NUMERIC_VALUE().getText());
//        }
//        javaProperty.setConstraint(constraint);
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitBlobValidators(kukulkanParser.BlobValidatorsContext ctx) {
//        if (ctx.required != null) {
//            constraint.setNullable(false);
//        }
//        if (ctx.minBytesValue != null) {
//            constraint.setMinValue(ctx.minBytesValue.NUMERIC_VALUE().getText());
//        }
//        if (ctx.maxBytesValue != null) {
//            constraint.setMaxValue(ctx.maxBytesValue.NUMERIC_VALUE().getText());
//        }
//        javaProperty.setConstraint(constraint);
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitRequiredValidator(kukulkanParser.RequiredValidatorContext ctx) {
        constraint.setNullable(false);
        return super.visitChildren(ctx);
    }
    
    

    @Override
    public VisitorContext visitMinLengthValidator(kukulkanParser.MinLengthValidatorContext ctx) {
        constraint.setMinValue(ctx.NUMERIC_VALUE().getText());
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitMaxLengthValidator(kukulkanParser.MaxLengthValidatorContext ctx) {
        constraint.setMaxValue(ctx.NUMERIC_VALUE().getText());
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitPatternValidator(kukulkanParser.PatternValidatorContext ctx) {
        constraint.setPatter(ctx.NUMERIC_VALUE().getText());
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitMinValidator(kukulkanParser.MinValidatorContext ctx) {
        constraint.setMinValue(ctx.NUMERIC_VALUE().getText());
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitMaxValidator(kukulkanParser.MaxValidatorContext ctx) {
        constraint.setMaxValue(ctx.NUMERIC_VALUE().getText());
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitMinBytesValidator(kukulkanParser.MinBytesValidatorContext ctx) {
        constraint.setMinValue(ctx.NUMERIC_VALUE().getText());
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitMaxBytesValidator(kukulkanParser.MaxBytesValidatorContext ctx) {
        constraint.setMaxValue(ctx.NUMERIC_VALUE().getText());
        return super.visitChildren(ctx);
    }

    public VisitorContext getVctx() {
        return vctx;
    }
    
    public void processFieldType(Optional<GrammarPropertyType> optional) {
        if (optional.isPresent()) {
            GrammarPropertyType grammarPropertyType = optional.get();
            javaProperty = createJavaProperty(efc, propertyName, grammarPropertyType);
            dme.addProperty(javaProperty);
            addContentType(dme, propertyName, grammarPropertyType);
            GrammarMapping.addImports(dme.getImports(), javaProperty);
            DataBaseMapping.fillModelMetaData(dme, javaProperty);
        }
    }
}
