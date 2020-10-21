package com.yb.demo;

//import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

/**
 * @author yb
 * @description
 * @data 2020/9/7
 */
public class AESTest {
    //JDK默认不支持PKCS7的填充方式，需要添加这个静态代码块
//    static {
//        Security.addProvider(new BouncyCastleProvider());
//    }

    private static final Logger logger = LoggerFactory.getLogger(AESTest.class);



    // 加密
    public static byte[] encrypt(byte[] sSrc, byte[] sKey) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");//"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc);
            return encrypted;
        } catch (Exception e) {
            logger.error("加密失败!");
            e.printStackTrace();
        }
        return null;
    }

    // 解密
    public static byte[] decrypt(byte[] sSrc, byte[] sKey) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] original = cipher.doFinal(sSrc);
            return original;
        } catch (Exception e) {
            logger.error("解密失败!");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        byte[] secret = ByteUtil.hexStringToByteArray("496F744D657465723230313830303031");
        String Str = "01ffff80000000000000000000000000";
        byte[] encrypt = AESTest.encrypt(ByteUtil.hexStringToByteArray(Str),secret);
        System.out.println(ByteUtil.toHexString(encrypt));

        byte[] decrypt = AESTest.decrypt(encrypt, secret);
        System.out.println(ByteUtil.toHexString(decrypt));
    }
}
