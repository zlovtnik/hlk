package com.climate.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MongoConfigTest {

    @Autowired
    private ValidatingMongoEventListener validatingMongoEventListener;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Test
    public void testMongoConfigBeansExist() {
        // Verify that beans are properly created and autowired
        assertNotNull(validatingMongoEventListener);
        assertNotNull(validator);
    }
}
