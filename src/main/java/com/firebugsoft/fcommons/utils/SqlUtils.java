package com.firebugsoft.fcommons.utils;

import java.lang.reflect.Field;
import java.util.List;

public final class SqlUtils {
    private SqlUtils() {}

    public static final String getTableName(Object po) {
        String className = po.getClass().getSimpleName();
        return StringUtils.toUnderScoreCase(className);
    }

    public static final String getSqlDeleteById(Object po) {
        String tableName = getTableName(po);
        Object id = 0;
        try {
            id = po.getClass().getMethod("getId").invoke(po);
        } catch (Exception e) {}
        return String.format("DELETE FROM %s WHERE id=%s", tableName, id);
    }

    public static final String getSqlUpdateById(Object po, List<Object> args) {
        StringBuilder sql = new StringBuilder();
        sql.append(String.format("UPDATE %s SET ", getTableName(po)));
        Field[] fs = po.getClass().getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            String fieldName = fs[i].getName();
            if("id".equals(fieldName)) {
                continue;
            }
            Object value = null;
            String get = StringUtils.toGet(fieldName);
            try {
                value = po.getClass().getMethod(get).invoke(po);
            } catch (Exception e) {}
            if(value != null) {
                if(args.size() > 0) {
                    sql.append(", ");
                }
                sql.append(StringUtils.toUnderScoreCase(fieldName)).append("=?");
                args.add(value);
            }
        }
        sql.append(" WHERE id=?");
        Object id = 0;
        try {
            id = po.getClass().getMethod("getId").invoke(po);
        } catch (Exception e) {}
        args.add(id);
        return sql.toString();
    }
}
