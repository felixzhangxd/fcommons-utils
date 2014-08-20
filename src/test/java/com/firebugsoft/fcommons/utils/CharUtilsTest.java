package com.firebugsoft.fcommons.utils;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author felix
 */
public class CharUtilsTest {
    @Test
    public void isUpperCase() {
        Assert.assertTrue(CharUtils.isUpperCase('A'));
        Assert.assertTrue(CharUtils.isUpperCase('Z'));
        Assert.assertFalse(CharUtils.isUpperCase('a'));
        Assert.assertFalse(CharUtils.isUpperCase('z'));
    }

    @Test
    public void isLowerCase() {
        Assert.assertFalse(CharUtils.isLowerCase('A'));
        Assert.assertFalse(CharUtils.isLowerCase('Z'));
        Assert.assertTrue(CharUtils.isLowerCase('a'));
        Assert.assertTrue(CharUtils.isLowerCase('z'));
    }

    @Test
    public void toUpperCase() {
        Assert.assertEquals('A', CharUtils.toUpperCase('a'));
        Assert.assertEquals('Z', CharUtils.toUpperCase('z'));
        char[] cs = "felix".toCharArray();
        CharUtils.toUpperCase(cs);
        Assert.assertEquals("FELIX", new String(cs));
    }

    @Test
    public void toLowerCase() {
        Assert.assertEquals('a', CharUtils.toLowerCase('A'));
        Assert.assertEquals('z', CharUtils.toLowerCase('Z'));
        char[] cs = "FELIX".toCharArray();
        CharUtils.toLowerCase(cs);
        Assert.assertEquals("felix", new String(cs));
    }

    @Test
    public void toUpperFirst() {
        char[] cs = "felix".toCharArray();
        CharUtils.toUpperFirst(cs);
        Assert.assertEquals("Felix", new String(cs));
    }

    @Test
    public void toLowerFirst() {
        char[] cs = "FELIX".toCharArray();
        CharUtils.toLowerFirst(cs);
        Assert.assertEquals("fELIX", new String(cs));
    }
}
