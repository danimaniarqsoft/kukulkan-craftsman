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

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.engine.domain.core.DataModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.service.layers.springrest.util.LayerConstants;
import mx.infotec.dads.kukulkan.engine.service.layers.springrest.util.TemplateFormatter;
import mx.infotec.dads.kukulkan.templating.service.TemplateService;
import mx.infotec.dads.kukulkan.util.BasePathEnum;
import mx.infotec.dads.kukulkan.util.InflectorProcessor;

import static mx.infotec.dads.kukulkan.util.NameConventionFormatter.camelCaseToHyphens;

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
			model.put("entityCamelCase", dmElement.getCamelCaseFormat());
			model.put("entity", dmElement.getName());
			model.put("id", dmElement.getPrimaryKey().getType());
			model.put("properties", dmElement.getProperties());
			model.put("projectName", pConf.getId());
			model.put("entityHyphenNotation", camelCaseToHyphens(dmElement.getCamelCaseFormat()));
			model.put("entityCamelCasePlural", InflectorProcessor.getInstance().pluralize(dmElement.getCamelCaseFormat()));
			model.put("hasTimeProperties", dmElement.isHasTimeProperties());
			model.put("hasBlobProperties", dmElement.isHasBlobProperties());
			dmElement.getPrimaryKey().setGenerationType(pConf.getGlobalGenerationType());
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
			fillIdiomaJs(pConf, model, dmgName, basePackage, dmElement);
		}
	}

	private void fillIdiomaJs(ProjectConfiguration pConf, Map<String, Object> model, String dmgName, String basePackage,
			DataModelElement dmElement) {
		LOGGER.info("AngularLayerTask filling {}", LayerConstants.IDIOMA_JS);
		saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.IDIOMA_JS);
	}

	private void fillEntityStateJs(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
			String basePackage, DataModelElement dmElement) {
		LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_STATE_JS);
		saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.ENTITY_STATE_JS);
	}

	private void fillEntityServiceJs(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
			String basePackage, DataModelElement dmElement) {
		LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_SERVICE_JS);
		saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.ENTITY_SERVICE_JS);
	}

	private void fillEntitySearchServiceJs(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
			String basePackage, DataModelElement dmElement) {
		LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_SEARCH_SERVICE_JS);
		saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.ENTITY_SEARCH_SERVICE_JS);
	}

	private void fillEntityHtml(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
			String basePackage, DataModelElement dmElement) {
		LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_HTML);
		saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.ENTITY_HTML);
	}

	private void fillEntityDialogHtml(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
			String basePackage, DataModelElement dmElement) {
		LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_DETAIL_HTML);
		saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.ENTITY_DIALOG_HTML);
	}

	private void fillEntityDialogControllerJs(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
			String basePackage, DataModelElement dmElement) {
		LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_DIALOG_CONTROLLER_JS);
		saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.ENTITY_DIALOG_CONTROLLER_JS);
	}

	private void fillEntityDetailControllerJs(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
			String basePackage, DataModelElement dmElement) {
		LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_DETAIL_CONTROLLER_JS);
		saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.ENTITY_DETAIL_CONTROLLER_JS);
	}

	private void fillEntityDetailHtml(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
			String basePackage, DataModelElement dmElement) {
		LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_DETAIL_HTML);
		saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.ENTITY_DETAIL_HTML);
	}

	private void fillEntityDeleteDialogHtml(ProjectConfiguration pConf, Map<String, Object> model, String dmgName,
			String basePackage, DataModelElement dmElement) {
		LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_DELETE_DIALOG_HTML);
		saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.ENTITY_DELETE_DIALOG_HTML);
	}

	private void fillEntityDeleteDialogControllerJs(ProjectConfiguration pConf, Map<String, Object> model,
			String dmgName, String basePackage, DataModelElement dmElement) {
		LOGGER.info("AngularLayerTask filling {}", LayerConstants.ENTITY_DELETE_DIALOG_CONTROLLER_JS);
		saveFrontEndTemplate(pConf, model, dmElement, LayerConstants.ENTITY_DELETE_DIALOG_CONTROLLER_JS);
	}

	private void saveFrontEndTemplate(ProjectConfiguration pConf, Map<String, Object> model, DataModelElement dmElement,
			String templateName) {
		// pfCOnf.getId, templateName, model, dmElement.getPropertyName.
		String fileNamingConvention = camelCaseToHyphens(dmElement.getCamelCaseFormat());
		templateService.fillModel(pConf.getId(), "rest-spring-jpa/frontEnd/" + templateName, model,
				BasePathEnum.WEB_APP_ENTITIES,
				fileNamingConvention + "/" + fileNamingConvention + TemplateFormatter.formatNameTemplate(templateName));
	}

}
