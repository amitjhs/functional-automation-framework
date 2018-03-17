package com.automationframework.functionaltest;

import org.testng.Assert;

public class MAssert {

    public static void assertAreEqual(Object A, Object B) {
        try {
            Assert.assertEquals(A, B);
        } catch (Exception e) {
            System.out.println("Assert Error: " + e.getMessage());
        }
    }

    public static void assertTrue(Object A) {
        try {
            Assert.assertTrue((Boolean) A);
        } catch (Exception e) {
            System.out.println("Assert Error: " + e.getMessage());
        }
    }
}

