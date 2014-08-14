package com.firebugsoft.fcommons.utils;

import java.lang.reflect.Field;

import com.firebugsoft.fcommons.utils.def.Ascii;

public final class StringUtils {
    private StringUtils() {}

    /**
     * 首字母改大写
     * 如:user => User
     */
    public static final String toUpperFirst(final String s) {
        byte[] bs = s.getBytes();
        ByteUtils.toUpperFirst(bs);
        return new String(bs);
    }

    /**
     * 首字母改小写
     * 如:User => user
     */
    public static final String toLowerFirst(final String s) {
        byte[] bs = s.getBytes();
        ByteUtils.toLowerFirst(bs);
        return new String(bs);
    }

    /**
     * 下划线命名 转 小驼峰命名
     * 如: user_name => userName
     */
    public static final String toLowerCamelCase(final String underScoreCase) {
        int index = 0;
        byte[] bs = underScoreCase.getBytes();
        for (int i = 0; i < bs.length; i++) {
            bs[index++] = (bs[i] == Ascii.UNDER_SCORE) ? ByteUtils.toUpperCase(bs[++i]) : bs[i];
        }
        return new String(bs, 0, index);
    }

    /**
     * 下划线命名 转 大驼峰命名
     * 如: user_name => UserName
     */
    public static final String toUpperCamelCase(final String underScoreCase) {
        byte[] bs = underScoreCase.getBytes();
        ByteUtils.toUpperFirst(bs);
        int index = 0;
        for (int i = 0; i < bs.length; i++) {
            bs[index++] = (bs[i] == Ascii.UNDER_SCORE) ? ByteUtils.toUpperCase(bs[++i]) : bs[i];
        }
        return new String(bs, 0, index);
    }

    /**
     * 驼峰命名 转 下划线命名
     * 如: UserName => user_name
     * 如: userName => user_name
     */
    public static final String toUnderScoreCase(final String camelCase) {
        byte[] bs = camelCase.getBytes();
        ByteUtils.toLowerFirst(bs);
        byte[] underScoreCases = new byte[bs.length * 2];
        int index = 0;
        for (byte b : bs) {
            if (ByteUtils.isUpperCase(b)) {
                underScoreCases[index++] = Ascii.UNDER_SCORE;
            }
            underScoreCases[index++] = ByteUtils.toLowerCase(b);
        }
        return new String(underScoreCases, 0, index);
    }

    /**
     * 属性名称 转 get方法
     * 如: userName => getUserName
     */
    public static final String toGet(final String fieldName) {
        return "get" + toUpperFirst(fieldName);
    }

    /**
     * 属性名称 转 set方法
     * 如: userName => setUserName
     */
    public static final String toSet(final String fieldName) {
        return "set" + toUpperFirst(fieldName);
    }
    /**
     * pojo对象 转 String
     * 如: id:1, name:felix, pwd:fpwd 
     */
    public static final String toString(Object pojo) {
        StringBuilder s = new StringBuilder();
        Class<?> cls = pojo.getClass();
        Field[] fs = cls.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            if (i > 0) {
                s.append(", ");
            }
            String name = fs[i].getName();
            s.append(name).append(":");
            try {
                s.append(cls.getDeclaredMethod(toGet(name)).invoke(pojo));
            } catch (Exception e) {}
        }
        return s.toString();
    }
}
