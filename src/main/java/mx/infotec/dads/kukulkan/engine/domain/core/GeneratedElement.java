package mx.infotec.dads.kukulkan.engine.domain.core;

import java.io.File;

/**
 * KukulkanProject represent a generated project
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class GeneratedElement {

    private File file;
    private String content;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
