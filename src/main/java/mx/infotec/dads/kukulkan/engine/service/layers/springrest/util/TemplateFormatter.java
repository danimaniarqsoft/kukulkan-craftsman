package mx.infotec.dads.kukulkan.engine.service.layers.springrest.util;

/**
 * Template Formatter
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class TemplateFormatter {
    private TemplateFormatter() {
    }

    public static String formatNameTemplate(String template) {
    	return template.replaceAll("entity|.ftl", "");
    }

    public static void main(String[] args) {
        System.out.println(formatNameTemplate("entity.controller.js.ftl"));
        System.out.println(formatNameTemplate("entity-delete-dialog.controller.js.ftl"));
        System.out.println(formatNameTemplate("entity-delete-dialog.html.ftl"));
        System.out.println(formatNameTemplate("entity-detail.controller.js.ftl"));
        System.out.println(formatNameTemplate("entity-detail.html.ftl"));
        System.out.println(formatNameTemplate("entity-dialog.controller.js.ftl"));
        System.out.println(formatNameTemplate("entity-dialog.html.ftl"));
        System.out.println(formatNameTemplate("entity.html.ftl"));
        System.out.println(formatNameTemplate("entity.search.service.js.ftl"));
        System.out.println(formatNameTemplate("entity.service.js.ftl"));
        System.out.println(formatNameTemplate("entity.state.js.ftl"));
        System.out.println(formatNameTemplate("entity.js.ftl"));
        System.out.println(formatNameTemplate("entity-detail.html.ftl"));
    }
}
