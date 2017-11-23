package mx.infotec.dads.kukulkan.service.dto;

import java.io.Serializable;
import java.util.List;

import mx.infotec.dads.kukulkan.engine.domain.core.GeneratedElement;

/**
 * GeneratedDto
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class GeneratedDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<GeneratedElement> mainElements;
    private List<GeneratedElement> elements;

    public List<GeneratedElement> getMainElements() {
        return mainElements;
    }

    public void setMainElements(List<GeneratedElement> mainElements) {
        this.mainElements = mainElements;
    }

    public List<GeneratedElement> getElements() {
        return elements;
    }

    public void setElements(List<GeneratedElement> elements) {
        this.elements = elements;
    }

}
