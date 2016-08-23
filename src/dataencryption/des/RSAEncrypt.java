package dataencryption.des;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 非对称加解密 RSA 目前世界上还没有攻击RSA的有效方法
 * 该加解密方式速度较慢
 * Created by 浮生若梦 on 2016/8/23.
 */
public class RSAEncrypt {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            RSAEncrypt encrypt = new RSAEncrypt();
            String encryptText = "12345678sads";//需要加密的内容
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(1024);
            KeyPair keyPair = keyPairGen.generateKeyPair();

            // Generate keys
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // 私钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // 公钥
            byte[] e = encrypt.encrypt(publicKey, encryptText.getBytes());
            byte[] de = encrypt.decrypt(privateKey, e);
            System.out.println("加密后："+encrypt.bytesToString(e));
            System.out.println();
            System.out.println("解密后："+encrypt.bytesToString(de));
            System.out.println("耗时："+(System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * byte数组转为string
     * @param encrytpByte
     * @return
     */
    protected String bytesToString(byte[] encrytpByte) {
        String result = "";
        for (Byte bytes : encrytpByte) {
            result += (char) bytes.intValue();
        }
        return result;
    }

    /**
     * 加密方法
     * @param publicKey
     * @param obj
     * @return
     */
    protected byte[] encrypt(RSAPublicKey publicKey, byte[] obj) {
        if (publicKey != null) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                return cipher.doFinal(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 解密方法
     * @param privateKey
     * @param obj
     * @return
     */
    protected byte[] decrypt(RSAPrivateKey privateKey, byte[] obj) {
        if (privateKey != null) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                return cipher.doFinal(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
