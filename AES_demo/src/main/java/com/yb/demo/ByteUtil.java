package com.yb.demo;


/**
 * describe:
 *
 * @author helloworldyu
 * @data 2017/12/20
 */
public class ByteUtil {
    private ByteUtil() {
    }


    /**
     * bcd 转码
     *
     * @param bytes
     * @return
     */
    public static String bcd2Str(byte[] bytes) {
        StringBuffer temp = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
            temp.append((byte) (bytes[i] & 0x0f));
        }
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp
                .toString().substring(1) : temp.toString();
    }


    /**
     * 转bcd码
     *
     * @param asc
     * @return
     */
    public static byte[] str2Bcd(String asc) {
        int len = asc.length();
        int mod = len % 2;
        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }
        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }
        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;
        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }
            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }


    /**
     * 获取一个字节中某一位的值
     *
     * @param b   字节
     * @param idx 从左至右数,base-0
     * @return 值为1返回true, 否则返回false
     */
    public static boolean getBit(byte b, int idx) {
        return (b & (0x80 >> idx)) != 0x00 ? true : false;
    }

    /**
     * 值做转换
     *
     * @param byteArray
     * @return
     */
    public static String toString(byte[] byteArray) {
        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x0a) {
                hexString.append("0");
            }
            hexString.append(byteArray[i]);
        }
        return hexString.toString();
    }

    /**
     * 单个字节转换为十六进制
     * @param b
     * @return
     */
    public static String toHexString(byte b){
        StringBuilder sb = new StringBuilder();
        if((b & 0xff) < 0x10){
            sb.append("0");
        }
        sb.append(Integer.toHexString(0xff & b));
        return sb.toString();
    }

    /**
     * 文本一致
     *
     * @param byteArray
     * @return
     */
    public static String toHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1) {
            throw new IllegalArgumentException("字节数组不能为空，长度不能为0");
        }

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10) {
                hexString.append("0");
            }
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }

    /**
     * 值转换
     *
     * @param s
     * @return
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * 文本一致
     *
     * @param s
     * @return
     */
    public static byte[] hexStringToByteArray2(String s) {
        byte[] bytes;
        bytes = new byte[s.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    public static byte getByte(byte[] memory, int index) {
        return memory[index];
    }

    public static short getShort(byte[] memory, int index) {
        return (short) (memory[index] << 8 | memory[index + 1] & 0xFF);
    }

    public static short getShortLE(byte[] memory, int index) {
        return (short) (memory[index] & 0xff | memory[index + 1] << 8);
    }

    public static int getUnsignedMedium(byte[] memory, int index) {
        return (memory[index] & 0xff) << 16 |
                (memory[index + 1] & 0xff) << 8 |
                memory[index + 2] & 0xff;
    }

    public static int getUnsignedMediumLE(byte[] memory, int index) {
        return memory[index] & 0xff |
                (memory[index + 1] & 0xff) << 8 |
                (memory[index + 2] & 0xff) << 16;
    }

    public static int getInt(byte[] memory, int index) {
        return (memory[index] & 0xff) << 24 |
                (memory[index + 1] & 0xff) << 16 |
                (memory[index + 2] & 0xff) << 8 |
                memory[index + 3] & 0xff;
    }

    public static int getIntLE(byte[] memory, int index) {
        return memory[index] & 0xff |
                (memory[index + 1] & 0xff) << 8 |
                (memory[index + 2] & 0xff) << 16 |
                (memory[index + 3] & 0xff) << 24;
    }

    public static long getLong(byte[] memory, int index) {
        return ((long) memory[index] & 0xff) << 56 |
                ((long) memory[index + 1] & 0xff) << 48 |
                ((long) memory[index + 2] & 0xff) << 40 |
                ((long) memory[index + 3] & 0xff) << 32 |
                ((long) memory[index + 4] & 0xff) << 24 |
                ((long) memory[index + 5] & 0xff) << 16 |
                ((long) memory[index + 6] & 0xff) << 8 |
                (long) memory[index + 7] & 0xff;
    }

    public static long getLongLE(byte[] memory, int index) {
        return (long) memory[index] & 0xff |
                ((long) memory[index + 1] & 0xff) << 8 |
                ((long) memory[index + 2] & 0xff) << 16 |
                ((long) memory[index + 3] & 0xff) << 24 |
                ((long) memory[index + 4] & 0xff) << 32 |
                ((long) memory[index + 5] & 0xff) << 40 |
                ((long) memory[index + 6] & 0xff) << 48 |
                ((long) memory[index + 7] & 0xff) << 56;
    }

    public static byte[] getBytes(byte[] memory, int index, int length) {
        byte[] bs = new byte[length];
        for (int i = 0; i < length; i++) {
            bs[i] = memory[index + i];
        }
        return bs;
    }

    public static void setByte(byte[] memory, int index, int value) {
        memory[index] = (byte) value;
    }

    public static void setShort(byte[] memory, int index, int value) {
        memory[index] = (byte) (value >>> 8);
        memory[index + 1] = (byte) value;
    }

    public static void setShortLE(byte[] memory, int index, int value) {
        memory[index] = (byte) value;
        memory[index + 1] = (byte) (value >>> 8);
    }

    public static void setMedium(byte[] memory, int index, int value) {
        memory[index] = (byte) (value >>> 16);
        memory[index + 1] = (byte) (value >>> 8);
        memory[index + 2] = (byte) value;
    }

    public static void setMediumLE(byte[] memory, int index, int value) {
        memory[index] = (byte) value;
        memory[index + 1] = (byte) (value >>> 8);
        memory[index + 2] = (byte) (value >>> 16);
    }

    public static void setInt(byte[] memory, int index, int value) {
        memory[index] = (byte) (value >>> 24);
        memory[index + 1] = (byte) (value >>> 16);
        memory[index + 2] = (byte) (value >>> 8);
        memory[index + 3] = (byte) value;
    }

    public static void setIntLE(byte[] memory, int index, int value) {
        memory[index] = (byte) value;
        memory[index + 1] = (byte) (value >>> 8);
        memory[index + 2] = (byte) (value >>> 16);
        memory[index + 3] = (byte) (value >>> 24);
    }

    public static void setLong(byte[] memory, int index, long value) {
        memory[index] = (byte) (value >>> 56);
        memory[index + 1] = (byte) (value >>> 48);
        memory[index + 2] = (byte) (value >>> 40);
        memory[index + 3] = (byte) (value >>> 32);
        memory[index + 4] = (byte) (value >>> 24);
        memory[index + 5] = (byte) (value >>> 16);
        memory[index + 6] = (byte) (value >>> 8);
        memory[index + 7] = (byte) value;
    }

    public static void setLongLE(byte[] memory, int index, long value) {
        memory[index] = (byte) value;
        memory[index + 1] = (byte) (value >>> 8);
        memory[index + 2] = (byte) (value >>> 16);
        memory[index + 3] = (byte) (value >>> 24);
        memory[index + 4] = (byte) (value >>> 32);
        memory[index + 5] = (byte) (value >>> 40);
        memory[index + 6] = (byte) (value >>> 48);
        memory[index + 7] = (byte) (value >>> 56);
    }

    public static String unicode2String(String data) {
        char[] chars = data.toCharArray();
        StringBuilder result = new StringBuilder();
        for (char ch : chars) {
            // 将字符f转换成\f
            if (ch == 0x66) {
                ch = 0x0c;
            }

            String hex = Integer.toHexString((int) ch);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            result.append(hex);
        }
        return result.toString();
    }

    public static String getString(byte[] src, int srcPos, int length) {
        byte[] bytes = new byte[length];
        System.arraycopy(src, srcPos, bytes, 0, length);
        String str = ByteUtil.toString(bytes);

        str = str.replaceAll("^(0+)", "");
        return str;
    }

    /**
     * 获取低四位
     * @param data
     * @return
     */
    public static byte getLow(byte data ) {
        return (byte) (data & 0x0f);
    }
}