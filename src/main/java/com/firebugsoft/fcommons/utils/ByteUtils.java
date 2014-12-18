package com.firebugsoft.fcommons.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author felix
 */
public final class ByteUtils {
    private ByteUtils() {}

    /** 判断是否是大写字母 */
    public static final boolean isUpperCase(byte b) {
        return (0x41 <= b) && (b <= 0x5A);
    }

    /** 判断是否是小写字母 */
    public static final boolean isLowerCase(byte b) {
        return (0x61 <= b) && (b <= 0x7A);
    }

    /** 小写字母 转 大写字母 */
    public static final byte toUpperCase(byte b) {
        return ByteUtils.isLowerCase(b) ? (b -= 0x20) : b;
    }

    /** 大写字母 转 小写字母 */
    public static final byte toLowerCase(byte b) {
        return ByteUtils.isUpperCase(b) ? (b += 0x20) : b;
    }

    /** 小写字母 转 大写字母 */
    public static final void toUpperCase(byte[] bs) {
        for (int i = 0; i < bs.length; i++) {
            bs[i] = ByteUtils.toUpperCase(bs[i]);
        }
    }

    /** 大写字母 转 小写字母 */
    public static final void toLowerCase(byte[] bs) {
        for (int i = 0; i < bs.length; i++) {
            bs[i] = ByteUtils.toLowerCase(bs[i]);
        }
    }

    /** 首字母 转 大写字母 */
    public static final void toUpperFirst(byte[] bs) {
        bs[0] = ByteUtils.toUpperCase(bs[0]);
    }

    /** 首字母 转 小写字母 */
    public static final void toLowerFirst(byte[] bs) {
        bs[0] = ByteUtils.toLowerCase(bs[0]);
    }

    /** 在source中，从startIndex开始，区分大小写查找第1个findContent的下标 */
    public static final int indexOf(byte[] source, int startIndex, byte... findContent) {
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
                return i;
            }
        }
        return -1;
    }

    /** 在source中，从startIndex开始，不区分大小写查找第1个findContent的下标 */
    public static final int indexOfIgnoreCase(byte[] source, int startIndex, byte... findContent) {
        byte[] bs = Arrays.copyOf(findContent, findContent.length);
        ByteUtils.toLowerCase(bs);
        int length = source.length - bs.length + 1;
        for (int i = startIndex; i < length; i++) {
            boolean isSame = true;
            for (int j = 0; j < bs.length; j++) {
                byte b = ByteUtils.toLowerCase(source[i + j]);
                if (b != bs[j]) {
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                return i;
            }
        }
        return -1;
    }

    /** 在source中，从startIndex开始，区分大小写查找所有findContent的下标 */
    public static final List<Integer> indexesOf(byte[] source, int startIndex, byte... findContent) {
        List<Integer> indexes = new LinkedList<Integer>();
        while (true) {
            int index = ByteUtils.indexOf(source, startIndex, findContent);
            if (index == -1) {
                break;
            }
            indexes.add(index);
            startIndex = index + findContent.length;
        }
        return indexes;
    }

    /** 在source中，从startIndex开始，不区分大小写查找所有findContent的下标 */
    public static final List<Integer> indexesOfIgnoreCase(byte[] source, int startIndex, byte... findContent) {
        List<Integer> indexes = new LinkedList<Integer>();
        while (true) {
            int index = ByteUtils.indexOfIgnoreCase(source, startIndex, findContent);
            if (index == -1) {
                break;
            }
            indexes.add(index);
            startIndex = index + findContent.length;
        }
        return indexes;
    }

    /** 在source中，区分大小写查找第1个findContent替换为replaceContent */
    public static final byte[] replaceFirst(byte[] source, byte[] findContent, byte[] replaceContent) {
        Integer index = ByteUtils.indexOf(source, 0, findContent);
        if (index == -1) {
            return source;
        }
        return ByteUtils.replace(source, findContent, replaceContent, index);
    }

    /** 在source中，不区分大小写查找第1个findContent替换为replaceContent */
    public static final byte[] replaceFirstIgnoreCase(byte[] source, byte[] findContent, byte[] replaceContent) {
        Integer index = ByteUtils.indexOfIgnoreCase(source, 0, findContent);
        if (index == -1) {
            return source;
        }
        return ByteUtils.replace(source, findContent, replaceContent, index);
    }

    /** 在source中，区分大小写查找所有findContent替换为replaceContent */
    public static final byte[] replaceAll(byte[] source, byte[] findContent, byte[] replaceContent) {
        List<Integer> indexes = ByteUtils.indexesOf(source, 0, findContent);
        if (indexes.isEmpty()) {
            return source;
        }
        return ByteUtils.replace(source, findContent, replaceContent, indexes);
    }

    /** 在source中，不区分大小写查找所有findContent替换为replaceContent */
    public static final byte[] replaceAllIgnoreCase(byte[] source, byte[] findContent, byte[] replaceContent) {
        List<Integer> indexes = ByteUtils.indexesOfIgnoreCase(source, 0, findContent);
        if (indexes.isEmpty()) {
            return source;
        }
        return ByteUtils.replace(source, findContent, replaceContent, indexes);
    }

    private static byte[] replace(byte[] source, byte[] findContent, byte[] replaceContent, Collection<Integer> indexes) {
        Integer[] is = new Integer[indexes.size()];
        is = indexes.toArray(is);
        return ByteUtils.replace(source, findContent, replaceContent, is);
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
