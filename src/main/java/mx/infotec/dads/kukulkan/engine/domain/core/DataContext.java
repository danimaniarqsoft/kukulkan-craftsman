package mx.infotec.dads.kukulkan.engine.domain.core;

import mx.infotec.dads.kukulkan.engine.grammar.KukulkanGrammarVisitor;

/**
 * DataContext, it represent the context of data used during the model
 * generation
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class DataContext {
    
    private DataContextType dataContextType;
    
    private org.apache.metamodel.DataContext dbDataContext;
    
    private KukulkanGrammarVisitor grammarDataContext;
    
    public DataContext(org.apache.metamodel.DataContext dbDataContext, DataContextType dataContextType){
        this.dbDataContext = dbDataContext;
        this.dataContextType=dataContextType;
    }
    
    public DataContext(KukulkanGrammarVisitor grammarDataContext, DataContextType dataContextType){
        this.grammarDataContext = grammarDataContext;
        this.dataContextType=dataContextType;
    }
    
    public DataContextType getDataContextType() {
        return dataContextType;
    }
    public void setDataContextType(DataContextType dataContextType) {
        this.dataContextType = dataContextType;
    }
    public org.apache.metamodel.DataContext getDbDataContext() {
        return dbDataContext;
    }
    public void setDbDataContext(org.apache.metamodel.DataContext dbDataContext) {
        this.dbDataContext = dbDataContext;
    }
    public KukulkanGrammarVisitor getGrammarDataContext() {
        return grammarDataContext;
    }
    public void setGrammarDataContext(KukulkanGrammarVisitor grammarDataContext) {
        this.grammarDataContext = grammarDataContext;
    }

}
