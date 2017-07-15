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
package mx.infotec.dads.kukulkan.loadMongoDataBase;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import mx.infotec.dads.kukulkan.KukulkancraftsmanApp;
import mx.infotec.dads.kukulkan.domain.DataStore;
import mx.infotec.dads.kukulkan.engine.domain.core.DataStoreType;
import mx.infotec.dads.kukulkan.engine.domain.core.RuleType;
import mx.infotec.dads.kukulkan.engine.repository.DataStoreTypeRepository;
import mx.infotec.dads.kukulkan.engine.repository.RuleRepository;
import mx.infotec.dads.kukulkan.engine.repository.RuleTypeRepository;
import mx.infotec.dads.kukulkan.repository.DataStoreRepository;
import mx.infotec.dads.kukulkan.repository.ProjectRepository;
import mx.infotec.dads.kukulkan.util.EntitiesFactory;

/**
 * Test for GeneratorService
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = KukulkancraftsmanApp.class)
public class LoadDataBaseTest {

	@Autowired
	private DataStoreRepository dsRepository;
	@Autowired
	private DataStoreTypeRepository dsTypeRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private RuleRepository ruleRepository;

	@Autowired
	private RuleTypeRepository ruleTypeRepository;

	@BeforeClass
	public static void runOnceBeforeClass() {
	}

	@Test
	public void loadDataBase() throws Exception {
		dsRepository.deleteAll();
		dsTypeRepository.deleteAll();
		ruleRepository.deleteAll();
		ruleTypeRepository.deleteAll();
		projectRepository.deleteAll();
		DataStoreType dst = EntitiesFactory.createDefaultDataStoreType();
		dst = dsTypeRepository.save(dst);
		DataStore dsValuApp = EntitiesFactory.createDefaultDataStore(dst);
		dsRepository.save(dsValuApp);
		DataStore testDs = EntitiesFactory.createTestDataStore(dst);
		dsRepository.save(testDs);
		DataStore dsConacyt = EntitiesFactory.createConacytDataStore(dst);
		dsRepository.save(dsConacyt);
		RuleType singularRuleType = ruleTypeRepository.save(EntitiesFactory.createDefaultSingularRuleType());
		ruleTypeRepository.save(EntitiesFactory.createDefaultPluralRuleType());
		ruleRepository.save(EntitiesFactory.createOsRule(singularRuleType));
		ruleRepository.save(EntitiesFactory.createEsRule(singularRuleType));

	}
}
