package com.firebugsoft.fcommons.utils;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.firebugsoft.fcommons.utils.def.Ascii;

/**
 * @author felix
 */
public class ByteUtilsTest {
    @Test
    public void isUpperCase() {
        Assert.assertTrue(ByteUtils.isUpperCase(Ascii.A));
        Assert.assertTrue(ByteUtils.isUpperCase(Ascii.Z));
        Assert.assertFalse(ByteUtils.isUpperCase(Ascii.a));
        Assert.assertFalse(ByteUtils.isUpperCase(Ascii.z));
    }

    @Test
    public void isLowerCase() {
        Assert.assertFalse(ByteUtils.isLowerCase(Ascii.A));
        Assert.assertFalse(ByteUtils.isLowerCase(Ascii.Z));
        Assert.assertTrue(ByteUtils.isLowerCase(Ascii.a));
        Assert.assertTrue(ByteUtils.isLowerCase(Ascii.z));
    }

    @Test
    public void toUpperCase() {
        Assert.assertEquals(Ascii.A, ByteUtils.toUpperCase(Ascii.a));
        Assert.assertEquals(Ascii.Z, ByteUtils.toUpperCase(Ascii.z));
        byte[] bs = "felix".getBytes();
        ByteUtils.toUpperCase(bs);
        Assert.assertEquals("FELIX", new String(bs));
    }

    @Test
    public void toLowerCase() {
        Assert.assertEquals('a', ByteUtils.toLowerCase(Ascii.A));
        Assert.assertEquals('z', ByteUtils.toLowerCase(Ascii.Z));
        byte[] bs = "FELIX".getBytes();
        ByteUtils.toLowerCase(bs);
        Assert.assertEquals("felix", new String(bs));
    }

    @Test
    public void toUpperFirst() {
        byte[] bs = "felix".getBytes();
        ByteUtils.toUpperFirst(bs);
        Assert.assertEquals("Felix", new String(bs));
    }

    @Test
    public void toLowerFirst() {
        byte[] bs = "FELIX".getBytes();
        ByteUtils.toLowerFirst(bs);
        Assert.assertEquals("fELIX", new String(bs));
    }

    @Test
    public void indexOf() {
        byte[] source = "felix".getBytes();
        byte[] findContent = "lix".getBytes();
        int index = ByteUtils.indexOf(source, findContent, 0);
        Assert.assertEquals(2, index);
        //
        findContent = "lixs".getBytes();
        index = ByteUtils.indexOf(source, findContent, 0);
        Assert.assertEquals(-1, index);
    }
    @Test
    public void ignoreIndexOf() {
        byte[] source = "felix".getBytes();
        byte[] findContent = "LIX".getBytes();
        int index = ByteUtils.ignoreIndexOf(source, findContent, 0);
        Assert.assertEquals(2, index);
        //
        findContent = "LIXS".getBytes();
        index = ByteUtils.ignoreIndexOf(source, findContent, 0);
        Assert.assertEquals(-1, index);
    }
    @Test
    public void indexesOf() {
        byte[] source = "ffff".getBytes();
        byte[] findContent = "ff".getBytes();
        List<Integer> indexes = ByteUtils.indexesOf(source, findContent, 0);
        Integer index0 = 0;
        Assert.assertEquals(index0, indexes.get(0));
        Integer index1 = 2;
        Assert.assertEquals(index1, indexes.get(1));
    }
    @Test
    public void ignoreIndexesOf() {
        byte[] source = "ffff".getBytes();
        byte[] findContent = "FF".getBytes();
        List<Integer> indexes = ByteUtils.ignoreIndexesOf(source, findContent, 0);
        Integer index0 = 0;
        Assert.assertEquals(index0, indexes.get(0));
        Integer index1 = 2;
        Assert.assertEquals(index1, indexes.get(1));
    }
    @Test
    public void replaceFirst() {
        byte[] source = "felix".getBytes();
        byte[] findContent = "li".getBytes();
        byte[] replaceContent = "".getBytes();
        source = ByteUtils.replaceFirst(source, findContent, replaceContent);
        Assert.assertEquals("fex",new String(source));
    }
    @Test
    public void ignoreReplaceFirst() {
        byte[] source = "felix".getBytes();
        byte[] findContent = "LI".getBytes();
        byte[] replaceContent = "".getBytes();
        source = ByteUtils.ignoreReplaceFirst(source, findContent, replaceContent);
        Assert.assertEquals("fex",new String(source));
    }
    @Test
    public void replaceAll() {
        byte[] source = "iiiiiii".getBytes();
        byte[] findContent = "ii".getBytes();
        byte[] replaceContent = "i".getBytes();
        source = ByteUtils.replaceAll(source, findContent, replaceContent);
        Assert.assertEquals("iiii",new String(source));
    }
    @Test
    public void ignoreReplaceAll() {
        byte[] source = "iiiiiii".getBytes();
        byte[] findContent = "II".getBytes();
        byte[] replaceContent = "i".getBytes();
        source = ByteUtils.ignoreReplaceAll(source, findContent, replaceContent);
        Assert.assertEquals("iiii",new String(source));
    }
   
}
