package com.banner;

import org.junit.jupiter.api.*;

public class TestLifeCycleJunit5 {

	    @Test
	    public void name() {
	        System.out.println("test method");
	    }

	    @BeforeEach
	    void setUp() {
	        System.out.println("before each");
	    }

	    @AfterEach
	    void tearDown() {
	        System.out.println("after each");
	    }


	    @BeforeAll
	    static void beforeAll() {
	        System.out.println("before all");
	    }

	    @AfterAll
	    static void afterAll() {
	        System.out.println("after all");
	    }

	}


