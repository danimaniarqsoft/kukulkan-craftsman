package mx.infotec.dads.kukulkan.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import mx.infotec.dads.kukulkan.KukulkanConfigurationProperties;
import mx.infotec.dads.kukulkan.domain.DataStore;
import mx.infotec.dads.kukulkan.domain.enumeration.ArchetypeType;
import mx.infotec.dads.kukulkan.engine.domain.core.DataContextContainer;
import mx.infotec.dads.kukulkan.engine.domain.core.DataContextType;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModel;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelGroup;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaDomainModel;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.domain.core.Rule;
import mx.infotec.dads.kukulkan.engine.domain.core.RuleType;
import mx.infotec.dads.kukulkan.engine.factories.LayerTaskFactory;
import mx.infotec.dads.kukulkan.engine.grammar.GrammarMapping;
import mx.infotec.dads.kukulkan.engine.grammar.KukulkanSemanticAnalyzer;
import mx.infotec.dads.kukulkan.engine.repository.RuleRepository;
import mx.infotec.dads.kukulkan.engine.repository.RuleTypeRepository;
import mx.infotec.dads.kukulkan.engine.service.GenerationService;
import mx.infotec.dads.kukulkan.repository.DataStoreRepository;
import mx.infotec.dads.kukulkan.service.DataStoreService;
import mx.infotec.dads.kukulkan.service.ProjectService;
import mx.infotec.dads.kukulkan.service.dto.GeneratedDto;
import mx.infotec.dads.kukulkan.util.DataStoreConstants;
import mx.infotec.dads.kukulkan.util.InflectorProcessor;
import mx.infotec.dads.kukulkan.util.PKGenerationStrategy;
import mx.infotec.dads.kukulkan.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Project.
 */
@RestController
@RequestMapping("/api")
public class GrammarResource {

    private final Logger log = LoggerFactory.getLogger(GrammarResource.class);

    private static final String ENTITY_NAME = "grammar";

    private final ProjectService projectService;

    @Autowired
    private DataStoreService dataStoreService;

    @Autowired
    private GenerationService generationService;

    @Autowired
    private KukulkanConfigurationProperties prop;

    @Autowired
    private DataStoreRepository dataStoreRepository;
    @Autowired
    private RuleRepository ruleRepository;
    @Autowired
    private RuleTypeRepository ruleTypeRepository;
    @Autowired
    private LayerTaskFactory layerTaskFactory;

    public GrammarResource(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * POST /projects : Create a new project.
     *
     * @param project
     *            the project to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     *         new project, or with status 400 (Bad Request) if the project has
     *         already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PostMapping("/grammar")
    @Timed
    public ResponseEntity<GeneratedDto> generateCode(@Valid @RequestBody String code) throws URISyntaxException {
        log.debug("REST request to save Project : {}", code);
        System.out.println(code);
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
        pConf.setId("kukulkanmongo");
        pConf.setGroupId("mx.infotec.dads.mongo");
        pConf.setVersion("1.0.0");
        pConf.setPackaging("mx.infotec.dads.mongo");
        pConf.setYear("2017");
        pConf.setAuthor("KUKULKAN");
        pConf.setWebLayerName("web.rest");
        pConf.setServiceLayerName("service");
        pConf.setDaoLayerName("repository");
        pConf.setDomainLayerName("domain");
        pConf.setMongoDb(true);
        pConf.setGlobalGenerationType(PKGenerationStrategy.SEQUENCE);
        // Create DataStore
        DataStore dsExample = new DataStore();
        dsExample.setName(DataStoreConstants.DATA_STORE_TYPE_GRAMMAR);
        Example<DataStore> dataStoreFilter = Example.of(dsExample);
        List<DataStore> findAllDataStores = dataStoreRepository.findAll(dataStoreFilter);
        DataStore dataStore = findAllDataStores.get(0);
        System.out.println(dataStore.getDataStoreType().getName());

        // Create DataModel
        DomainModel dataModel = new JavaDomainModel();
        DataContextContainer<?> dataContext = dataStoreService.createDataContext(dataStore);
        KukulkanSemanticAnalyzer semanticAnalyzer = null;
        if (dataContext.getDataContextType() == DataContextType.KUKULKAN_GRAMMAR) {
            semanticAnalyzer = (KukulkanSemanticAnalyzer) dataContext.getDataContext();
        }

        // Tables to process
        List<String> tablesToProcess = new ArrayList<>();
        // Mapping DataContext into DataModel
        List<DomainModelGroup> dmgList = GrammarMapping.createSingleDataModelGroupList(semanticAnalyzer);
        dataModel.setDomainModelGroup(dmgList);
        // Create GeneratorContext
        GeneratorContext genCtx = new GeneratorContext(dataModel, pConf);
        // Process Activities
        generationService.process(genCtx, layerTaskFactory.getLayerTaskSet(ArchetypeType.ANGULAR_SPRING));
        // if (project.getId() != null) {
        // return ResponseEntity.badRequest().headers(
        // HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new project
        // cannot already have an ID"))
        // .body(null);
        // }
        // Project result = projectService.save(project);
        // return ResponseEntity.created(new URI("/api/projects/" +
        // result.getId()))
        // .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,
        // result.getId().toString())).body(result);
        GeneratedDto dto = new GeneratedDto();
        dto.setGeneratedElements(genCtx.getDomainModel().getGeneratedElements());
        return ResponseEntity.created(new URI("/api/grammar/" + 1))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, "")).body(dto);
    }
}
