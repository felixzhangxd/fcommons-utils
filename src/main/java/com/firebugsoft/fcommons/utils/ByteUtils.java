package com.firebugsoft.fcommons.utils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.firebugsoft.fcommons.utils.def.Ascii;

public class ByteUtils {
    private ByteUtils() {}

    public static boolean isUpperCase(byte b) {
        return (Ascii.A <= b) && (b <= Ascii.Z);
    }

    public static boolean isLowerCase(byte b) {
        return (Ascii.a <= b) && (b <= Ascii.z);
    }

    public static byte toUpperCase(byte b) {
        return ByteUtils.isLowerCase(b) ? (b -= 0x20) : b;
    }

    public static byte toLowerCase(byte b) {
        return ByteUtils.isUpperCase(b) ? (b += 0x20) : b;
    }

    public static void toLowerCase(byte[] bs) {
        for (int i = 0; i < bs.length; i++) {
            bs[i] = ByteUtils.toLowerCase(bs[i]);
        }
    }
    
    public static void toUpperCase(byte[] bs) {
        for (int i = 0; i < bs.length; i++) {
            bs[i] = ByteUtils.toUpperCase(bs[i]);
        }
    }
    
    public static void toLowerFirst(byte[] bs) {
        bs[0] = ByteUtils.toLowerCase(bs[0]);
    }
    
    public static void toUpperFirst(byte[] bs) {
        bs[0] = ByteUtils.toUpperCase(bs[0]);
    }

    public static int indexOf(byte[] source, byte[] findContent, int startIndex) {
        int length = source.length - findContent.length + 1;
        for (int i = startIndex; i < length; i++) {
            boolean isSame = true;
            for (int j = 0; j < findContent.length; j++) {
                if (source[i + j] != findContent[j]) {
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                i = i + findContent.length - 1;
                return i;
            }
        }
        return -1;
    }

    public static List<Integer> indexesOf(byte[] source, byte[] findContent, int startIndex) {
        List<Integer> indexes = new LinkedList<Integer>();
        while (true) {
            int index = ByteUtils.indexOf(source, findContent, startIndex);
            if (index == -1) {
                break;
            }
            indexes.add(index);
            startIndex = index + findContent.length;
        }
        return indexes;
    }

    public static int ignoreIndexOf(byte[] source, byte[] findContent, int startIndex) {
        ByteUtils.toLowerCase(findContent);
        int length = source.length - findContent.length + 1;
        for (int i = startIndex; i < length; i++) {
            boolean isSame = true;
            for (int j = 0; j < findContent.length; j++) {
                byte b = ByteUtils.toLowerCase(source[i + j]);
                if (b != findContent[j]) {
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                i = i + findContent.length - 1;
                return i;
            }
        }
        return -1;
    }

    public static List<Integer> ignoreIndexesOf(byte[] source, byte[] findContent, int startIndex) {
        List<Integer> indexes = new LinkedList<Integer>();
        while (true) {
            int index = ByteUtils.ignoreIndexOf(source, findContent, startIndex);
            if (index == -1) {
                break;
            }
            indexes.add(index);
            startIndex = index + findContent.length;
        }
        return indexes;
    }

    public static byte[] replaceFirst(byte[] source, byte[] findContent, byte[] replaceContent) {
        Integer index = ByteUtils.indexOf(source, findContent, 0);
        if (index == -1) {
            return source;
        }
        return ByteUtils.replace(source, findContent, replaceContent, index);
    }

    public static byte[] ignoreReplaceFirst(byte[] source, byte[] findContent, byte[] replaceContent) {
        Integer index = ByteUtils.ignoreIndexOf(source, findContent, 0);
        if (index == -1) {
            return source;
        }
        return ByteUtils.replace(source, findContent, replaceContent, index);
    }

    public static byte[] replaceAll(byte[] source, byte[] findContent, byte[] replaceContent) {
        List<Integer> indexes = ByteUtils.indexesOf(source, findContent, 0);
        if (indexes.isEmpty()) {
            return source;
        }
        return ByteUtils.replace(source, findContent, replaceContent, indexes);
    }

    public static byte[] ignoreReplaceAll(byte[] source, byte[] findContent, byte[] replaceContent) {
        List<Integer> indexes = ByteUtils.ignoreIndexesOf(source, findContent, 0);
        if (indexes.isEmpty()) {
            return source;
        }
        return ByteUtils.replace(source, findContent, replaceContent, indexes);
    }

    private static byte[] replace(byte[] source, byte[] findContent, byte[] replaceContent, Collection<Integer> indexes) {
        Integer[] is = new Integer[indexes.size()];
        is = indexes.toArray(is);
        return replace(source, findContent, replaceContent, indexes);
    }

    private static byte[] replace(byte[] source, byte[] findContent, byte[] replaceContent, Integer... indexes) {
        int length = source.length + indexes.length * (replaceContent.length - findContent.length);
        byte[] target = new byte[length];
        int sourceStartIndex = 0;
        int targetStartIndex = 0;
        for (Integer index : indexes) {
            int copyLength = index - sourceStartIndex;
            System.arraycopy(source, sourceStartIndex, target, targetStartIndex, copyLength);
            sourceStartIndex += copyLength;
            targetStartIndex += copyLength;
            System.arraycopy(replaceContent, 0, target, targetStartIndex, replaceContent.length);
            sourceStartIndex += findContent.length;
            targetStartIndex += replaceContent.length;
        }
        System.arraycopy(source, sourceStartIndex, target, targetStartIndex, source.length - sourceStartIndex);
        return target;
    }
}
