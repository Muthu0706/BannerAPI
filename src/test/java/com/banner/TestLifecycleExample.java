//package com.banner;
//
//
//
//import org.junit.After;
//
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//public class TestLifecycleExample {
//
//    @BeforeClass
//    public static void setUpBeforeClass() {
//        System.out.println("Executed Before All Tests (setUpBeforeClass)");
//    }
//
//    @Before
//    public void setUp() {
//        System.out.println("Executed Before Each Test (setUp)");
//    }
//
//    @Test
//    public void testMethod1() {
//        System.out.println("Executing Test Method 1");
//        // Your test logic here
//    }
//
//    @Test
//    public void testMethod2() {
//        System.out.println("Executing Test Method 2");
//        // Your test logic here
//    }
//
//    @After
//    public void tearDown() {
//        System.out.println("Executed After Each Test (tearDown)");
//    }
//
//    @AfterClass
//    public static void tearDownAfterClass() {
//        System.out.println("Executed After All Tests (tearDownAfterClass)");
//    }
//}