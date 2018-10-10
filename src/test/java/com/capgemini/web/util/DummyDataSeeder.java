package com.capgemini.web.util;

import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;

/**
 * This is a stub so test database is not seeded with production seeds
 */
@Primary
public class DummyDataSeeder {
    @PostConstruct
    public void seedTestData() {
    }
}
