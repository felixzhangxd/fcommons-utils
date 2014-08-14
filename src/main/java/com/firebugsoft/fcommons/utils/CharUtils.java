package com.firebugsoft.fcommons.utils;

import com.firebugsoft.fcommons.utils.def.Ascii;

public final class CharUtils {
    private CharUtils() {}

    public static final boolean isUpperCase(final char c) {
        return (Ascii.A <= c) && (c <= Ascii.Z);
    }

    public static final boolean isLowerCase(final char c) {
        return (Ascii.a <= c) && (c <= Ascii.z);
    }

    public static final char toUpperCase(char c) {
        return CharUtils.isLowerCase(c) ? (c -= 0x20) : c;
    }

    public static final char toLowerCase(char c) {
        return CharUtils.isUpperCase(c) ? (c += 0x20) : c;
    }

    public static final void toUpperCase(final char[] cs) {
        for (int i = 0; i < cs.length; i++) {
            cs[i] = CharUtils.toUpperCase(cs[i]);
        }
    }

    public static final void toLowerCase(final char[] cs) {
        for (int i = 0; i < cs.length; i++) {
            cs[i] = CharUtils.toLowerCase(cs[i]);
        }
    }

    public static final void toLowerFirst(final char[] cs) {
        cs[0] = CharUtils.toLowerCase(cs[0]);
    }

    public static final void toUpperFirst(final char[] cs) {
        cs[0] = CharUtils.toUpperCase(cs[0]);
    }
}
