package com.firebugsoft.fcommons.utils;

import java.lang.reflect.Field;
import java.util.List;

public final class SqlUtils {
    private SqlUtils() {}

    public static final String getTableName(final Class<Object> poClass) {
        return StringUtils.toUnderScoreCase(poClass.getSimpleName());
    }

    public static final String getTableName(final Object po) {
        return SqlUtils.getTableName(po.getClass());
    }

    public static final String getSqlSelectById(final Integer id, final Class<Object> poClass) {
        final String tableName = SqlUtils.getTableName(poClass);
        return String.format("SELECT * FROM %s WHERE id=%s", tableName, id);
    }

    public static final String getSqlDeleteById(final Object po) {
        final String tableName = SqlUtils.getTableName(po);
        Object id = 0;
        try {
            id = po.getClass().getMethod("getId").invoke(po);
        } catch (final Exception e) {}
        return String.format("DELETE FROM %s WHERE id=%s", tableName, id);
    }

    public static final String getSqlUpdateById(final Object po, final List<Object> args) {
        final StringBuilder sql = new StringBuilder();
        sql.append(String.format("UPDATE %s SET ", SqlUtils.getTableName(po)));
        final Field[] fs = po.getClass().getDeclaredFields();
        for (final Field element : fs) {
            final String fieldName = element.getName();
            if ("id".equals(fieldName)) {
                continue;
            }
            try {
                final Object value = po.getClass().getMethod(StringUtils.toGetMethod(fieldName)).invoke(po);
                if (value != null) {
                    if (args.size() > 0) {
                        sql.append(", ");
                    }
                    sql.append(StringUtils.toUnderScoreCase(fieldName)).append("=?");
                    args.add(value);
                }
            } catch (final Exception e) {}
        }
        sql.append(" WHERE id=?");
        Object id = 0;
        try {
            id = po.getClass().getMethod("getId").invoke(po);
        } catch (final Exception e) {}
        args.add(id);
        return sql.toString();
    }
}
