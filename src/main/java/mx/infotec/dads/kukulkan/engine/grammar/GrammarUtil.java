package mx.infotec.dads.kukulkan.engine.grammar;

import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.infotec.dads.kukulkan.grammar.kukulkanLexer;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser;
import mx.infotec.dads.kukulkan.util.exceptions.ApplicationException;

/**
 * Grammar Util, It is used to performe common operation in the grammar.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class GrammarUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrammarUtil.class);

    private GrammarUtil() {

    }

    public static kukulkanParser.DomainModelContext getDomainModelContext(String file) {
        try {
            LOGGER.debug("Interpreting file {}", file);
            kukulkanLexer lexer;
            lexer = new kukulkanLexer(new ANTLRFileStream(file));
            return getDomainModelContext(lexer);
        } catch (IOException e) {
            throw new ApplicationException("getDomainModelContext Error: ", e);
        }
    }

    public static kukulkanParser.DomainModelContext getDomainModelContext(kukulkanLexer lexer) {
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        kukulkanParser parser = new kukulkanParser(tokens);
        return parser.domainModel();
    }

    public static kukulkanLexer getKukulkanLexer(String file) throws IOException {
        return new kukulkanLexer(new ANTLRFileStream(file));
    }

    public static kukulkanLexer getKukulkanLexer(InputStream is) throws IOException {
        return new kukulkanLexer(new ANTLRInputStream(is));
    }
}
