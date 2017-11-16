package mx.infotec.dads.kukulkan.engine.service.layers;

import static mx.infotec.dads.kukulkan.util.JavaFileNameParser.formatToImportStatement;
import static mx.infotec.dads.kukulkan.util.NameConventionFormatter.camelCaseToHyphens;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;

/**
 * LayerUtils
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class LayerUtils {

    /**
     * Authoring Properties
     */
    private static final String YEAR_PROPERTY = "year";
    private static final String AUTHOR_PROPERTY = "author";
    private static final String IS_MONGO_PROPERTY = "isMongoDB";
    private static final String PROJECT_NAME_PROPERTY = "projectName";
    private static final String DATA_MODEL_GROUP_PROPERTY = "dataModelGroup";
    /**
     * Common Properties per Element
     */
    private static final String ID_PROPERTY = "id";
    private static final String ENTITY_PROPERTY = "entity";
    private static final String ENTITY_CAMEL_CASE_PROPERTY = "entityCamelCase";
    private static final String ENTITY_CAMEL_CASE_PLURAL_PROPERTY = "entityCamelCasePlural";
    private static final String ENTITY_HYPHEN_NOTATION_PLURAL_PROPERTY = "entityHyphenNotationPlural";
    private static final String ENTITY_HYPHEN_NOTATION_PROPERTY = "entityHyphenNotation";
    private static final String IMPORT_PRIMARY_KEY_PROPERTY = "importPrimaryKey";
    private static final String IMPORT_MODEL_PROPERTY = "importModel";
    private static final String PRIMARY_KEY_PROPERTY = "primaryKey";
    private static final String PROPERTIES_PROPERTY = "properties";
    private static final String HAS_BLOB_PROPERTIES_PROPERTY = "hasBlobProperties";
    private static final String HAS_LOCAL_DATE_PROPERTY = "hasLocalDate";
    private static final String HAS_TIME_PROPERTIES_PROPERTY = "hasTimeProperties";

    private LayerUtils() {

    }

    /**
     * createGeneralDescription for the template engine. It adds common
     * properties needed to identify meta info.
     * 
     * @param context
     * @return Map<String, Object>
     */
    public static Map<String, Object> addAuthoringData(GeneratorContext context) {
        Map<String, Object> model = new HashMap<>();
        model.put(YEAR_PROPERTY, context.getProjectConfiguration().getYear());
        model.put(AUTHOR_PROPERTY, context.getProjectConfiguration().getAuthor());
        model.put(IS_MONGO_PROPERTY, context.getProjectConfiguration().isMongoDb());
        model.put(PROJECT_NAME_PROPERTY, context.getProjectConfiguration().getId());
        model.put(DATA_MODEL_GROUP_PROPERTY, context.getDomainModel().getDomainModelGroup());
        return model;
    }

    /**
     * addCommonDataModelElements, add needed model properties for the template
     * engine
     * 
     * @param conf
     * @param map
     * @param bPackage
     * @param dme
     */
    public static void addCommonDataModelElements(ProjectConfiguration conf, Map<String, Object> map, String bPackage,
            DomainModelElement dme) {
        map.put(IMPORT_MODEL_PROPERTY, formatToImportStatement(bPackage, conf.getDomainLayerName(), dme.getName()));
        map.put(ENTITY_CAMEL_CASE_PROPERTY, dme.getCamelCaseFormat());
        map.put(ENTITY_PROPERTY, dme.getName());
        map.put(ID_PROPERTY, dme.getPrimaryKey().getType());
        addPrimaryKeyIfComposed(conf, map, bPackage, dme);
        map.put(PROPERTIES_PROPERTY, dme.getProperties());
        map.put(PRIMARY_KEY_PROPERTY, dme.getPrimaryKey());
        map.put(ENTITY_HYPHEN_NOTATION_PROPERTY, camelCaseToHyphens(dme.getCamelCaseFormat()));
        map.put(ENTITY_HYPHEN_NOTATION_PLURAL_PROPERTY, camelCaseToHyphens(dme.getCamelCasePluralFormat()));
        map.put(ENTITY_CAMEL_CASE_PLURAL_PROPERTY, dme.getCamelCasePluralFormat());
        map.put(HAS_TIME_PROPERTIES_PROPERTY, dme.isHasTimeProperties());
        map.put(HAS_LOCAL_DATE_PROPERTY, dme.isHasLocalDate());
        map.put(HAS_BLOB_PROPERTIES_PROPERTY, dme.isHasBlobProperties());
    }

    public static void addPrimaryKeyIfComposed(ProjectConfiguration conf, Map<String, Object> model, String bPackage,
            DomainModelElement dme) {
        if (dme.getPrimaryKey().isComposed()) {
            model.put(IMPORT_PRIMARY_KEY_PROPERTY,
                    formatToImportStatement(bPackage, conf.getDomainLayerName(), dme.getPrimaryKey().getType()));
        }
    }
}
