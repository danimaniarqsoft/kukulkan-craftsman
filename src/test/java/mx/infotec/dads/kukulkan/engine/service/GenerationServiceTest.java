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
package mx.infotec.dads.kukulkan.engine.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Example;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import mx.infotec.dads.kukulkan.KukulkancraftsmanApp;
import mx.infotec.dads.kukulkan.domain.DataStore;
import mx.infotec.dads.kukulkan.domain.enumeration.Archetype;
import mx.infotec.dads.kukulkan.engine.domain.core.DataModel;
import mx.infotec.dads.kukulkan.engine.domain.core.DataModelGroup;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaDataModelContext;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.domain.core.Rule;
import mx.infotec.dads.kukulkan.engine.domain.core.RuleType;
import mx.infotec.dads.kukulkan.engine.factories.LayerTaskFactory;
import mx.infotec.dads.kukulkan.engine.repository.RuleRepository;
import mx.infotec.dads.kukulkan.engine.repository.RuleTypeRepository;
import mx.infotec.dads.kukulkan.repository.DataStoreRepository;
import mx.infotec.dads.kukulkan.service.DataStoreService;
import mx.infotec.dads.kukulkan.util.DataMapping;
import mx.infotec.dads.kukulkan.util.GenerationType;
import mx.infotec.dads.kukulkan.util.H2FileDatabaseConfiguration;
import mx.infotec.dads.kukulkan.util.InflectorProcessor;

/**
 * Test for GeneratorService
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = KukulkancraftsmanApp.class)
@DirtiesContext
public class GenerationServiceTest {

    @Autowired
    private GenerationService generationService;
    @Autowired
    private DataStoreService dataStoreService;
    @Autowired
    private DataStoreRepository dataStoreRepository;
    @Autowired
    private RuleRepository ruleRepository;
    @Autowired
    private LayerTaskFactory layerTaskFactory;
    @Autowired
    private RuleTypeRepository ruleTypeRepository;

    @BeforeClass
    public static void runOnceBeforeClass() {
        H2FileDatabaseConfiguration.run();
    }

    @Test
    public void generationService() throws Exception {
        Rule rule = new Rule();
        RuleType ruleType = ruleTypeRepository.findAll().get(0);
        ruleType.setName("singular");
        rule.setRuleType(ruleType);
        Example<Rule> ruleExample = Example.of(rule);
        List<Rule> rulesList = ruleRepository.findAll(ruleExample);
        for (Rule item : rulesList) {
            InflectorProcessor.getInstance().addSingularize(item.getExpression(), item.getReplacement());
        }
        // Create ProjectConfiguration
        ProjectConfiguration pConf = new ProjectConfiguration();
        pConf.setId("test-kukulkan");
        pConf.setGroupId("mx.infotec.dads");
        pConf.setVersion("1.0.0");
        pConf.setPackaging("mx.infotec.dads.rsr");
        pConf.setYear("2017");
        pConf.setAuthor("KUKULKAN");
        pConf.setWebLayerName("rest");
        pConf.setServiceLayerName("service");
        pConf.setDaoLayerName("repository");
        pConf.setDomainLayerName("model");
        pConf.setGlobalGenerationType(GenerationType.SEQUENCE);
        // Create DataStore
        DataStore dsExample = new DataStore();
        dsExample.setName("h2-db-test");
        Example<DataStore> dataStoreFilter = Example.of(dsExample);
        List<DataStore> findAllDataStores = dataStoreRepository.findAll(dataStoreFilter);
        DataStore dataStore = findAllDataStores.get(0);
        // Create DataModel
        DataModel dataModel = new JavaDataModelContext(dataStore);
        dataModel.setDataContext(dataStoreService.createDataContext(dataStore));
        // Tables to process
        List<String> tablesToProcess = new ArrayList<>();
        // Mapping DataContext into DataModel
        List<DataModelGroup> dmgList = DataMapping.createSingleDataModelGroupList(
                dataModel.getDataContext().getDefaultSchema().getTables(), tablesToProcess);
        dataModel.setDataModelGroup(dmgList);
        // Create GeneratorContext
        GeneratorContext genCtx = new GeneratorContext(dataModel, pConf);
        // Process Activities
        generationService.process(genCtx, layerTaskFactory.getLayerTaskSet(Archetype.ANGULAR_SPRING));
    }
}