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
package mx.infotec.dads.kukulkan.engine.service.layers.angularspring;

import static mx.infotec.dads.kukulkan.util.NameConventionFormatter.camelCaseToHyphens;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.engine.domain.core.DomainModel;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
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
public class AngularLayerTask extends AbstractAngularSpringLayerTask {

    @Autowired
    private TemplateService templateService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AngularLayerTask.class);

    @Override
    public void doBeforeProcessDataModelGroup(GeneratorContext context, Map<String, Object> model) {
        fillNavBar(context.getProjectConfiguration(), model, context.getDomainModel());
        fillIdiomaGlobalEsJs(context.getProjectConfiguration(), model, context.getDomainModel());
        fillIdiomaGlobalEnJs(context.getProjectConfiguration(), model, context.getDomainModel());
    }

    @Override
    public void visitDomainModelElement(ProjectConfiguration pConf, Collection<DomainModelElement> dmElementCollection,
            Map<String, Object> propertiesMap, String dmgName, DomainModelElement dmElement, String basePackage) {
        dmElement.getPrimaryKey().setGenerationType(pConf.getGlobalGenerationType());
        fillEntityControllerJs(pConf, propertiesMap, dmElement);
        fillEntityDeleteDialogControllerJs(pConf, propertiesMap, dmElement);
        fillEntityDeleteDialogHtml(pConf, propertiesMap, dmElement);
        fillEntityDetailControllerJs(pConf, propertiesMap, dmElement);
        fillEntityDetailHtml(pConf, propertiesMap, dmElement);
        fillEntityDialogControllerJs(pConf, propertiesMap, dmElement);
        fillEntityDialogHtml(pConf, propertiesMap, dmElement);
        fillEntityHtml(pConf, propertiesMap, dmElement);
        fillEntitySearchServiceJs(pConf, propertiesMap, dmElement);
        fillEntityServiceJs(pConf, propertiesMap, dmElement);
        fillEntityStateJs(pConf, propertiesMap, dmElement);
        fillIdiomaEsJs(pConf, propertiesMap, dmElement);
        fillIdiomaEnJs(pConf, propertiesMap, dmElement);
    }

    private void fillNavBar(ProjectConfiguration pConf, Map<String, Object> model, DomainModel domainModel) {
        templateService.fillModel(domainModel, pConf.getId(), "rest-spring-jpa/frontEnd/navbar.html.ftl", model,
                BasePathEnum.WEB_APP_NAV_BAR, "/navbar.html");
    }

    private void fillIdiomaGlobalEnJs(ProjectConfiguration pConf, Map<String, Object> model, DomainModel domainModel) {
        templateService.fillModel(domainModel, pConf.getId(), "rest-spring-jpa/frontEnd/i18n/en/global.json.ftl", model,
                BasePathEnum.WEB_APP_I18N, "/en/global.json");
    }

    private void fillIdiomaGlobalEsJs(ProjectConfiguration pConf, Map<String, Object> model, DomainModel domainModel) {
        templateService.fillModel(domainModel, pConf.getId(), "rest-spring-jpa/frontEnd/i18n/es/global.json.ftl", model,
                BasePathEnum.WEB_APP_I18N, "/es/global.json");
    }

    private void fillEntityControllerJs(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityControllerJs {}", LayerConstants.ENTITY_CONTROLLER_JS);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_CONTROLLER_JS, false);

    }

    private void fillIdiomaEsJs(ProjectConfiguration pConf, Map<String, Object> model, DomainModelElement dmElement) {
        LOGGER.info("fillIdiomaEsJs {}", LayerConstants.IDIOMA_JS);
        saveInternationalizationTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_I18N_LOCATION_ES,
                LayerConstants.IDIOMA_JS, "es");
    }

    private void fillIdiomaEnJs(ProjectConfiguration pConf, Map<String, Object> model, DomainModelElement dmElement) {
        LOGGER.info("fillIdiomaEnJs {}", LayerConstants.IDIOMA_JS);
        saveInternationalizationTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_I18N_LOCATION_EN,
                LayerConstants.IDIOMA_JS, "en");
    }

    private void fillEntityStateJs(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityStateJs {}", LayerConstants.ENTITY_STATE_JS);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_STATE_JS, false);
    }

    private void fillEntityServiceJs(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityServiceJs {}", LayerConstants.ENTITY_SERVICE_JS);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_SERVICE_JS, false);
    }

    private void fillEntitySearchServiceJs(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntitySearchServiceJs {}", LayerConstants.ENTITY_SEARCH_SERVICE_JS);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_SEARCH_SERVICE_JS, false);
    }

    private void fillEntityHtml(ProjectConfiguration pConf, Map<String, Object> model, DomainModelElement dmElement) {
        LOGGER.info("fillEntityHtml {}", LayerConstants.ENTITY_HTML);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_HTML, true);
    }

    private void fillEntityDialogHtml(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityDialogHtml {}", LayerConstants.ENTITY_DETAIL_HTML);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_DIALOG_HTML, false);
    }

    private void fillEntityDialogControllerJs(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityDialogControllerJs {}", LayerConstants.ENTITY_DIALOG_CONTROLLER_JS);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_DIALOG_CONTROLLER_JS, false);
    }

    private void fillEntityDetailControllerJs(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityDetailControllerJs {}", LayerConstants.ENTITY_DETAIL_CONTROLLER_JS);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_DETAIL_CONTROLLER_JS, false);
    }

    private void fillEntityDetailHtml(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityDetailHtml {}", LayerConstants.ENTITY_DETAIL_HTML);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_DETAIL_HTML, false);
    }

    private void fillEntityDeleteDialogHtml(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityDeleteDialogHtml {}", LayerConstants.ENTITY_DELETE_DIALOG_HTML);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_DELETE_DIALOG_HTML, false);
    }

    private void fillEntityDeleteDialogControllerJs(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityDeleteDialogControllerJs {}", LayerConstants.ENTITY_DELETE_DIALOG_CONTROLLER_JS);
        saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.FRONT_END_ENTITIES_LOCATION,
                LayerConstants.ENTITY_DELETE_DIALOG_CONTROLLER_JS, false);
    }

    private void saveFrontEndTemplate(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement, String templateLocation, String templateName, boolean isPlural) {
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
            DomainModelElement dmElement, String templateLocation, String templateName, String idiomaKey) {
        // pfCOnf.getId, templateName, model, dmElement.getPropertyName.
        String fileNamingConvention = camelCaseToHyphens(dmElement.getCamelCaseFormat());
        templateService.fillModel(dmElement, pConf.getId(), templateLocation + templateName, model,
                BasePathEnum.WEB_APP_I18N,
                idiomaKey + "/" + fileNamingConvention + TemplateFormatter.formatNameTemplate(templateName));
    }
}
