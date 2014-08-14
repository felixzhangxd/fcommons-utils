package com.firebugsoft.fcommons.utils;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void toUpperFirst() {
        String s = "user";
        String upperFirst = StringUtils.toUpperFirst(s);
        Assert.assertEquals("User", upperFirst);
    }

    @Test
    public void toLowerFirst() {
        String s = "USER";
        String upperFirst = StringUtils.toLowerFirst(s);
        Assert.assertEquals("uSER", upperFirst);
    }

    @Test
    public void toLowerCamelCase() {
        String underScoreCase = "user_name";
        String lowerCamelCase = StringUtils.toLowerCamelCase(underScoreCase);
        Assert.assertEquals("userName", lowerCamelCase);
    }

    @Test
    public void toUpperCamelCase() {
        String underScoreCase = "user_name";
        String lowerCamelCase = StringUtils.toUpperCamelCase(underScoreCase);
        Assert.assertEquals("UserName", lowerCamelCase);
    }

    @Test
    public void toUnderScoreCase() {
        String camelCase = "UserName";
        String underScoreCase = StringUtils.toUnderScoreCase(camelCase);
        Assert.assertEquals("user_name", underScoreCase);
    }

    @Test
    public void toGet() {
        String field = "userName";
        String get = StringUtils.toGet(field);
        Assert.assertEquals("getUserName", get);
    }

    @Test
    public void toSet() {
        String field = "userName";
        String get = StringUtils.toSet(field);
        Assert.assertEquals("setUserName", get);
    }
    @Test
    public void toString(User u) {
        String toString = StringUtils.toString(new User(1,"felix", "fpwd"));
        String expected = "id:1, name:felix, pwd:fpwd";
        Assert.assertEquals(expected , toString);
    }
}

