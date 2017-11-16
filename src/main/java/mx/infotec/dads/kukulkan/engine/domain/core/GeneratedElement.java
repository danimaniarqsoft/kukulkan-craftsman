package mx.infotec.dads.kukulkan.engine.domain.core;

import java.nio.file.Path;

/**
 * KukulkanProject represent a generated project
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class GeneratedElement {
    /**
     * The Path where the content must be generated
     */
    private Path path;

    /**
     * The content of the generated Element, it is filled from the freemarker
     * Template,
     */
    private String content;

    public GeneratedElement(){}
    
    public GeneratedElement(Path path, String content) {
        this.path = path;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
