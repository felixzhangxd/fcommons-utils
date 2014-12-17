package com.firebugsoft.fcommons.utils;

/**
 * @author felix
 */
public final class CharUtils {
    private CharUtils() {}
    
    /** 判断是否是大写字母 */
    public static final boolean isUpperCase(char c) {
        return (0x41 <= c) && (c <= 0x5A);
    }
    /** 判断是否是小写字母 */
    public static final boolean isLowerCase(char c) {
        return (0x61 <= c) && (c <= 0x7A);
    }
    /** 小写字母 转 大写字母*/
    public static final char toUpperCase(char c) {
        return CharUtils.isLowerCase(c) ? (c -= 0x20) : c;
    }
    /** 大写字母 转 小写字母*/
    public static final char toLowerCase(char c) {
        return CharUtils.isUpperCase(c) ? (c += 0x20) : c;
    }
    /** 小写字母 转 大写字母*/
    public static final void toUpperCase(char[] cs) {
        for (int i = 0; i < cs.length; i++) {
            cs[i] = CharUtils.toUpperCase(cs[i]);
        }
    }
    /** 大写字母 转 小写字母*/
    public static final void toLowerCase(char[] cs) {
        for (int i = 0; i < cs.length; i++) {
            cs[i] = CharUtils.toLowerCase(cs[i]);
        }
    }
    /** 首字母 转 大写字母*/
    public static final void toUpperFirst(char[] cs) {
        cs[0] = CharUtils.toUpperCase(cs[0]);
    }
    /** 首字母 转 小写字母*/
    public static final void toLowerFirst(char[] cs) {
        cs[0] = CharUtils.toLowerCase(cs[0]);
    }
}
