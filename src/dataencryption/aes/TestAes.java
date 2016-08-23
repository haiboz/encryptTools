package dataencryption.aes;

import org.apache.commons.codec.binary.Hex;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * AES 高级加密标准
 * 是3DES的改进 是目前最常用的对称加密算法 效率更高 且至今尚未有被破解的记录
 * Created by 浮生若梦 on 2016/8/22.
 */
public class TestAes {
    private static String src = "this is a secret String.";
    private static String modle = "AES/ECB/PKCS5Padding";
    private static String key = "123213453557567234";
    public static void main(String[] args) {
        jdkAes();
        aseByKey(key);
    }

    public static void jdkAes(){
        try {
            //生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(new SecureRandom());//128 256
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            //key转换
            Key key = new SecretKeySpec(keyBytes,"AES");
            //加密
            Cipher p = Cipher.getInstance(modle);//加解密算法/工作方式/填充方式
            p.init(Cipher.ENCRYPT_MODE,key);
            //加密后的结果
            byte[] result = p.doFinal(src.getBytes());
            System.out.println("jdk aes :"+ Hex.encodeHexString(result));

            //解密
            p.init(Cipher.DECRYPT_MODE,key);
            result = p.doFinal(result);
            System.out.println("jdk aes :"+new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据给定的秘钥key进行加解密
     * @param key
     */
    public static void aseByKey(String key){
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            keyGenerator.init(128,secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();//生成的key

            //加密
            Cipher p = Cipher.getInstance(modle);
            p.init(Cipher.ENCRYPT_MODE,secretKey);
            byte[] result = p.doFinal(src.getBytes());//加密后的数组
            BASE64Encoder encode = new BASE64Encoder();
            System.out.println("encrypt aes by key :"+ encode.encode(result));

            //解密
            p.init(Cipher.DECRYPT_MODE,secretKey);
            result = p.doFinal(result);
            System.out.println("decrypt aes by key :"+new String(result));
//            in为inoutStream流 则 cin为加/解密过的流
//            CipherInputStream cin = new CipherInputStream(in,c);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
