package dataencryption.des;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * 较des安全性更高的加解密算法  效率较低 速度较慢
 * Created by 浮生若梦 on 2016/8/22.
 */
public class Test3Des {
    private static String src = "This is a test string.";
    public static void main(String[] args) {
        jsk3DES();
    }

    /**
     * jdk 3重des加解密
     */
    private static void jsk3DES(){
        try {
            //生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
//            keyGenerator.init(168);
            keyGenerator.init(new SecureRandom());//根据不同加密算法填充默认的初始化长度
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKeys = secretKey.getEncoded();
            //KEY转换
            DESedeKeySpec desedeKeySpec = new DESedeKeySpec(byteKeys);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            //生成的秘钥
            Key convertSecretKey = factory.generateSecret(desedeKeySpec);
            //加解密前面部分一样
            //加密
            Cipher p = Cipher.getInstance("DESede/ECB/PKCS5Padding");//加解密算法/工作方式/填充方式
            p.init(Cipher.ENCRYPT_MODE,convertSecretKey);//设置为加密模式
            byte[] result = p.doFinal(src.getBytes());//加密后
            BASE64Encoder encode = new BASE64Encoder();
            System.out.println("3desede encrypt :"+ encode.encode(result));

            //解密
            p.init(Cipher.DECRYPT_MODE,convertSecretKey);//设置为解密模式
            result = p.doFinal(result);
            System.out.println("3desede decrypt :"+ new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
