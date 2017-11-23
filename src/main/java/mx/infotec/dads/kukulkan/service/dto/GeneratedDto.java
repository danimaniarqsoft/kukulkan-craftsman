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

    private List<GeneratedElement> generatedElements;

    public List<GeneratedElement> getGeneratedElements() {
        return generatedElements;
    }

    public void setGeneratedElements(List<GeneratedElement> generatedElements) {
        this.generatedElements = generatedElements;
    }

}
