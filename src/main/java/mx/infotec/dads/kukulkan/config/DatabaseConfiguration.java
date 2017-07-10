package mx.infotec.dads.kukulkan.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;

import io.github.jhipster.config.JHipsterConstants;
import io.github.jhipster.domain.util.JSR310DateConverters.DateToZonedDateTimeConverter;
import io.github.jhipster.domain.util.JSR310DateConverters.ZonedDateTimeToDateConverter;
import mx.infotec.dads.kukulkan.domain.DataStore;
import mx.infotec.dads.kukulkan.engine.domain.core.DataStoreType;
import mx.infotec.dads.kukulkan.engine.domain.core.RuleType;
import mx.infotec.dads.kukulkan.engine.repository.DataStoreTypeRepository;
import mx.infotec.dads.kukulkan.engine.repository.RuleRepository;
import mx.infotec.dads.kukulkan.engine.repository.RuleTypeRepository;
import mx.infotec.dads.kukulkan.repository.DataStoreRepository;
import mx.infotec.dads.kukulkan.util.EntitiesFactory;

@Configuration
@Profile("!" + JHipsterConstants.SPRING_PROFILE_CLOUD)
@EnableMongoRepositories(basePackages = { "mx.infotec.dads.kukulkan.repository",
		"mx.infotec.dads.kukulkan.engine.repository" })
@Import(value = MongoAutoConfiguration.class)
@EnableMongoAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class DatabaseConfiguration {

	private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

	@Bean
	CommandLineRunner init(final DataStoreRepository dsRepository, final DataStoreTypeRepository dsTypeRepository,
			final RuleRepository ruleRepository, final RuleTypeRepository ruleTypeRepository) {
		return commandLineRunner -> {
			log.info("DELETE DATABASES");
			dsRepository.deleteAll();
			dsTypeRepository.deleteAll();
			ruleRepository.deleteAll();
			ruleTypeRepository.deleteAll();
			DataStoreType dst = EntitiesFactory.createDefaultDataStoreType();
			dst = dsTypeRepository.save(dst);
			log.info("dataStore1:"+dst.getId());
			DataStore dsValuApp = EntitiesFactory.createDefaultDataStore(dst);
			dsRepository.save(dsValuApp);
			log.info("dataStore2:",dsValuApp.getId());
			DataStore testDs = EntitiesFactory.createTestDataStore(dst);
			dsRepository.save(testDs);
			log.info("dataStore3:",testDs.getId());
			DataStore dsConacyt = EntitiesFactory.createConacytDataStore(dst);
			dsRepository.save(dsConacyt);
			RuleType singularRuleType = ruleTypeRepository.save(EntitiesFactory.createDefaultSingularRuleType());
			ruleTypeRepository.save(EntitiesFactory.createDefaultPluralRuleType());
			ruleRepository.save(EntitiesFactory.createOsRule(singularRuleType));
			ruleRepository.save(EntitiesFactory.createEsRule(singularRuleType));
		};
	}

	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener() {
		return new ValidatingMongoEventListener(validator());
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public CustomConversions customConversions() {
		List<Converter<?, ?>> converters = new ArrayList<>();
		converters.add(DateToZonedDateTimeConverter.INSTANCE);
		converters.add(ZonedDateTimeToDateConverter.INSTANCE);
		return new CustomConversions(converters);
	}

	@Bean
	public Mongobee mongobee(MongoClient mongoClient, MongoTemplate mongoTemplate, MongoProperties mongoProperties) {
		log.debug("Configuring Mongobee");
		Mongobee mongobee = new Mongobee(mongoClient);
		mongobee.setDbName(mongoProperties.getDatabase());
		mongobee.setMongoTemplate(mongoTemplate);
		// package to scan for migrations
		mongobee.setChangeLogsScanPackage("mx.infotec.dads.kukulkan.config.dbmigrations");
		mongobee.setEnabled(true);
		return mongobee;
	}
}
