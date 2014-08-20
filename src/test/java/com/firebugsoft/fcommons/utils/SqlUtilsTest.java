package com.firebugsoft.fcommons.utils;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SqlUtilsTest {
    private User user = new User(1,"felix", "fpwd");
    @Test
    public void getTableName() {
        String tableName = SqlUtils.getTableName(user);
        Assert.assertEquals("user", tableName);
    }
    @Test
    public void getSqlDeleteById() {
        String sql = SqlUtils.getSqlDeleteById(user);
        String export = "DELETE FROM user WHERE id="+user.getId();
        Assert.assertEquals(export, sql);
    }
    @Test
    public void getSqlUpdateById() {
        List<Object> args = new LinkedList<Object>();
        String sql = "UPDATE user SET name=?, pwd=? WHERE id=?";
        Assert.assertEquals(sql, SqlUtils.getSqlUpdateById(user, args));
        Assert.assertEquals(user.getName(), args.get(0));
        Assert.assertEquals(user.getPwd(), args.get(1));
        Assert.assertEquals(user.getId(), args.get(2));
    }
}

