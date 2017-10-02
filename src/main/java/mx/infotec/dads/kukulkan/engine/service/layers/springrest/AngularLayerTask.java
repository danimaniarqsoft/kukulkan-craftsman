/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mx.infotec.dads.kukulkan.engine.service.layers.springrest;

import static mx.infotec.dads.kukulkan.util.NameConventionFormatter.camelCaseToHyphens;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.engine.domain.core.DataModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratedElement;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.service.layers.springrest.util.LayerConstants;
import mx.infotec.dads.kukulkan.engine.service.layers.springrest.util.TemplateFormatter;
import mx.infotec.dads.kukulkan.templating.service.TemplateService;
import mx.infotec.dads.kukulkan.util.BasePathEnum;

/**
 * Service Layer Task
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service("angularLayerTask")
public class AngularLayerTask extends SpringRestLayerTaskVisitor {

    @Autowired
    private TemplateService templateService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AngularLayerTask.class);

    @Override
    public void doForEachDataModelElement(ProjectConfiguration pConf, Collection<DataModelElement> dmElementCollection,
            Map<String, Object> model, String dmgName) {
        String basePackage = pConf.getPackaging() + dmgName;
        LOGGER.info("AngularLayerTask {}", basePackage);
        for (DataModelElement dmElement : dmElementCollection) {
            fillModel(pConf, model, dmElement);
            dmElement.getPrimaryKey().setGenerationType(pConf.getGlobalGenerationType());
            fillEntityControllerJs(pConf, model, dmgName, basePackage, dmElement);
            fillEntityDeleteDialogControllerJs(pConf, model, dmgName, basePackage, dmElement);
            fillEntityDeleteDialogHtml(pConf, model, dmgName, basePackage, dmElement);
            fillEntityDetailControllerJs(pConf, model, dmgName, basePackage, dmElement);
            fillEntityDetailHtml(pConf, model, dmgName, basePackage, dmElement);
            fillEntityDialogControllerJs(pConf, model, dmgName, basePackage, dmElement);
            fillEntityDialogHtml(pConf, model, dmgName, basePackage, dmElement);
            fillEntityHtml(pConf, model, dmgName, basePackage, dmElement);
            fillEntitySearchServiceJs(pConf, model, dmgName, basePackage, dmElement);
            fillEntityServiceJs(pConf, model, dmgName, basePackage, dmElement);
            fillEntityStateJs(pConf, model, dmgName, basePackage, dmElement);
            fillIdiomaEsJs(pConf, model, dmgName, basePackage, dmElement);
            fillIdiomaEnJs(pConf, model, dmgName, basePackage, dmElement);
        }
    }

    private void fillModel(ProjectConfiguration pConf, Map<String, Object> model, DataModelElement dmElement) {
        model.put("entityCamelCase", dmElement.getCamelCaseFormat());
        model.put("entity", dmElement.getName());
        model.put("id", dmElement.getPrimaryKey().getType());
        model.put("properties", dmElement.getProperties());
        model.put("projectName", pConf.getId());
        model.put("entityHyphenNotation", camelCaseToHyphens(dmElement.getCamelCaseFormat()));
        model.put("entityHyphenNotationPlural", camelCaseToHyphens(dmElement.getCamelCasePluralFormat()));
        model.put("entityCamelCasePlural", dmElement.getCamelCasePluralFormat());
        model.put("hasTimeProperties", dmElement.isHasTimeProperties());
        model.put("hasBlobProperties", dmElement.isHasBlobProperties());
    }

    private void fillEntityControllerJs(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
            String basePackage, DataModelElement dmElement) {
        LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_CONTROLLER_JS);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_CONTROLLER_JS, false);

    }

    private void fillIdiomaEsJs(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
            String basePackage, DataModelElement dmElement) {
        LOGGER.info("AngularLayerTask filling {}", LayerConstants.IDIOMA_JS);
        saveInternationalizationTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_I18N_LOCATION_ES,
                LayerConstants.IDIOMA_JS, "es");
    }

    private void fillIdiomaEnJs(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
            String basePackage, DataModelElement dmElement) {
        LOGGER.info("AngularLayerTask filling {}", LayerConstants.IDIOMA_JS);
        saveInternationalizationTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_I18N_LOCATION_EN,
                LayerConstants.IDIOMA_JS, "en");
    }

    private void fillEntityStateJs(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
            String basePackage, DataModelElement dmElement) {
        LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_STATE_JS);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_STATE_JS, false);
    }

    private void fillEntityServiceJs(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
            String basePackage, DataModelElement dmElement) {
        LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_SERVICE_JS);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_SERVICE_JS, false);
    }

    private void fillEntitySearchServiceJs(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
            String basePackage, DataModelElement dmElement) {
        LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_SEARCH_SERVICE_JS);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_SEARCH_SERVICE_JS, false);
    }

    private void fillEntityHtml(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
            String basePackage, DataModelElement dmElement) {
        LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_HTML);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_HTML, true);
    }

    private void fillEntityDialogHtml(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
            String basePackage, DataModelElement dmElement) {
        LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_DETAIL_HTML);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_DIALOG_HTML, false);
    }

    private void fillEntityDialogControllerJs(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
            String basePackage, DataModelElement dmElement) {
        LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_DIALOG_CONTROLLER_JS);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_DIALOG_CONTROLLER_JS, false);
    }

    private void fillEntityDetailControllerJs(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
            String basePackage, DataModelElement dmElement) {
        LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_DETAIL_CONTROLLER_JS);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_DETAIL_CONTROLLER_JS, false);
    }

    private void fillEntityDetailHtml(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
            String basePackage, DataModelElement dmElement) {
        LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_DETAIL_HTML);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_DETAIL_HTML, false);
    }

    private void fillEntityDeleteDialogHtml(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
            String basePackage, DataModelElement dmElement) {
        LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_DELETE_DIALOG_HTML);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_DELETE_DIALOG_HTML, false);
    }

    private void fillEntityDeleteDialogControllerJs(ProjectConfiguration pConf, Map<String, Object> model,
            String dmgName, String basePackage, DataModelElement dmElement) {
        LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_DELETE_DIALOG_CONTROLLER_JS);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_DELETE_DIALOG_CONTROLLER_JS, false);
    }

    private void saveFrontEndTemplate(ProjectConfiguration pConf, Map<String, Object> model, DataModelElement dmElement,
            String templateLocation, String templateName, boolean isPlural) {
        // pfCOnf.getId, templateName, model, dmElement.getPropertyName.
        String fileNamingConvention = camelCaseToHyphens(dmElement.getCamelCaseFormat());
        String entityName = fileNamingConvention;
        if (isPlural) {
            entityName = camelCaseToHyphens(dmElement.getCamelCasePluralFormat());
        }
        templateService.fillModel(dmElement, pConf.getId(), templateLocation + templateName, model,
                BasePathEnum.WEB_APP_ENTITIES,
                fileNamingConvention + "/" + entityName + TemplateFormatter.formatNameTemplate(templateName));
    }

    private void saveInternationalizationTemplate(ProjectConfiguration pConf, Map<String, Object> model,
            DataModelElement dmElement, String templateLocation, String templateName, String idiomaKey) {
        // pfCOnf.getId, templateName, model, dmElement.getPropertyName.
        String fileNamingConvention = camelCaseToHyphens(dmElement.getCamelCaseFormat());
        templateService.fillModel(dmElement, pConf.getId(), templateLocation + templateName, model,
                BasePathEnum.WEN_APP_I18N,
                idiomaKey + "/" + fileNamingConvention + TemplateFormatter.formatNameTemplate(templateName));
    }

}
