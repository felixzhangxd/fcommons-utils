package com.firebugsoft.fcommons.utils;

import com.firebugsoft.fcommons.utils.def.Ascii;

/**
 * @author felix
 */
public final class CharUtils {
    private CharUtils() {}
    
    /** 判断是否是大写字母 */
    public static final boolean isUpperCase(final char c) {
        return (Ascii.A <= c) && (c <= Ascii.Z);
    }
    /** 判断是否是小写字母 */
    public static final boolean isLowerCase(final char c) {
        return (Ascii.a <= c) && (c <= Ascii.z);
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
    public static final void toUpperCase(final char[] cs) {
        for (int i = 0; i < cs.length; i++) {
            cs[i] = CharUtils.toUpperCase(cs[i]);
        }
    }
    /** 大写字母 转 小写字母*/
    public static final void toLowerCase(final char[] cs) {
        for (int i = 0; i < cs.length; i++) {
            cs[i] = CharUtils.toLowerCase(cs[i]);
        }
    }
    /** 首字母 转 大写字母*/
    public static final void toUpperFirst(final char[] cs) {
        cs[0] = CharUtils.toUpperCase(cs[0]);
    }
    /** 首字母 转 小写字母*/
    public static final void toLowerFirst(final char[] cs) {
        cs[0] = CharUtils.toLowerCase(cs[0]);
    }
}
