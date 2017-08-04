package mx.infotec.dads.kukulkan.config.dbmigrations;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import mx.infotec.dads.kukulkan.assets.domain.Discipline;
import mx.infotec.dads.kukulkan.assets.domain.LevelOfImplementation;
import mx.infotec.dads.kukulkan.assets.domain.Phase;
import mx.infotec.dads.kukulkan.assets.domain.ProblemDomain;
import mx.infotec.dads.kukulkan.assets.domain.State;
import mx.infotec.dads.kukulkan.domain.DataStore;
import mx.infotec.dads.kukulkan.engine.domain.core.DataStoreType;
import mx.infotec.dads.kukulkan.engine.domain.core.RuleType;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.*;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "002")
public class CatalogSetupMigration {

    @ChangeSet(order = "01", author = "dcp", id = "02-kukulkan")
    public void addAuthorities(MongoTemplate mongoTemplate) {
        DataStoreType dst = createDefaultDataStoreType();
        mongoTemplate.save(dst);
        DataStore testDs = createTestDataStore(dst);
        mongoTemplate.save(testDs);
        RuleType singularRuleType = createDefaultSingularRuleType();
        mongoTemplate.save(singularRuleType);
        mongoTemplate.save(createDefaultPluralRuleType());
        mongoTemplate.save(createOsRule(singularRuleType));
        mongoTemplate.save(createEsRule(singularRuleType));
    }

    @ChangeSet(order = "02", author = "dcp", id = "02-assets")
    public void addAssets(MongoTemplate mongoTemplate) {
        createLevelOfImplementation().forEach(entity -> mongoTemplate.save(entity));
        createStates().forEach(entity -> mongoTemplate.save(entity));
        createDisciplines().forEach(entity -> mongoTemplate.save(entity));
        createPhases().forEach(entity -> mongoTemplate.save(entity));
        createDomains().forEach(entity -> mongoTemplate.save(entity));
        createGranularities().forEach(entity -> mongoTemplate.save(entity));
    }
}
