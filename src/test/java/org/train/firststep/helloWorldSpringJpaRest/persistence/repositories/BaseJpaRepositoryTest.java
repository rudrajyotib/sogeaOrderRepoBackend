package org.train.firststep.helloWorldSpringJpaRest.persistence.repositories;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigurationPackage(basePackages = "org.train.firststep.helloWorldSpringJpaRest")
@TestPropertySource(locations = "/org/train/firststep/helloWorldSpringJpaRest/spring-jpa-test-config-h2.properties")
@EnableAutoConfiguration
public class BaseJpaRepositoryTest {
}
