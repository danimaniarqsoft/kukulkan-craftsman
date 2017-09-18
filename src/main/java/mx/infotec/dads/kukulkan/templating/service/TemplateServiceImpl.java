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
package mx.infotec.dads.kukulkan.templating.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import mx.infotec.dads.kukulkan.KukulkanConfigurationProperties;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratedElement;
import mx.infotec.dads.kukulkan.util.BasePathEnum;
import mx.infotec.dads.kukulkan.util.FileUtil;
import mx.infotec.dads.kukulkan.util.TemplateUtil;
import mx.infotec.dads.kukulkan.util.exceptions.ApplicationException;

/**
 * 
 * @author Daniel Cortes Pichardo
 * @since 1.0.0
 * @version 1.0.0
 */

@Service
public class TemplateServiceImpl implements TemplateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateServiceImpl.class);

    @Autowired
    private Configuration fmConfiguration;

    @Autowired
    private KukulkanConfigurationProperties prop;

    @Override
    public Optional<GeneratedElement> fillModel(String proyectoId, String templateName, Object model,
            BasePathEnum basePath, String filePath) {
        Optional<Template> templateOptional = TemplateUtil.getTemplate(fmConfiguration, templateName);
        GeneratedElement gElement = null;
        if (templateOptional.isPresent()) {
            Path path = FileUtil.createPath(proyectoId, basePath, filePath, prop.getOutputdir());
            FileUtil.createParentsFileIfNotExist(path);
            gElement = processTemplate(model, templateOptional.get(), path);
        }
        return Optional.ofNullable(gElement);
    }

    public static GeneratedElement processTemplate(Object model, Template template, Path path) {
        try (Writer stringWriter = new FileWriter(path.toFile())) {
            LOGGER.info("Generating to: {}", path.normalize().toFile());
            template.process(model, stringWriter);
            return new GeneratedElement(path, stringWriter.toString());
        } catch (IOException | TemplateException e) {
            throw new ApplicationException("Fill Model Error", e);
        }
    }
    
    public static void main(String[] args) {
        StringWriter sw = new StringWriter();
        sw.write("hola mundo");
        System.out.println(sw.toString());
    }
}
