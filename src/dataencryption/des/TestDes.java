package dataencryption.des;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * DES加解密算法实现
 * 目前这种算法安全性较低 已经几乎不再使用 一般出现在演示案例或较早的一些项目中
 * Created by 浮生若梦 on 2016/8/22.
 */
public class TestDes {
    private static String src = "This is a test string.";
    public static void main(String[] args) {
        jdkDES();
        bcDES();
    }

    /**
     * jdk  DES加解密
     */
    public static void jdkDES(){
        try {
            //生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKeys = secretKey.getEncoded();
            //KEY转换
            DESKeySpec desKeySoec = new DESKeySpec(byteKeys);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            //生成的秘钥
            Key convertSecretKey = factory.generateSecret(desKeySoec);
            //加解密前面部分一样
            //加密
            Cipher p = Cipher.getInstance("DES/ECB/PKCS5Padding");//加解密算法/工作方式/填充方式
            p.init(Cipher.ENCRYPT_MODE,convertSecretKey);//设置为加密模式
            byte[] result = p.doFinal(src.getBytes());//加密后
            BASE64Encoder encode = new BASE64Encoder();
            System.out.println("jdk encrypt :"+ encode.encode(result));

            //解密
            p.init(Cipher.DECRYPT_MODE,convertSecretKey);//设置为解密模式
            result = p.doFinal(result);
            System.out.println("jdk decrypt :"+ new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * bc DES加解密方式
     */
    public static void bcDES(){
        try {
            //增加provider 后续一样
            Security.addProvider(new BouncyCastleProvider());
            //生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");//使用BC的provider
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKeys = secretKey.getEncoded();
            //KEY转换
            DESKeySpec desKeySoec = new DESKeySpec(byteKeys);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            //生成的秘钥
            Key convertSecretKey = factory.generateSecret(desKeySoec);
            //加解密前面部分一样
            //加密
            Cipher p = Cipher.getInstance("DES/ECB/PKCS5Padding");//加解密算法/工作方式/填充方式
            p.init(Cipher.ENCRYPT_MODE,convertSecretKey);//设置为加密模式
            byte[] result = p.doFinal(src.getBytes());//加密后
            BASE64Encoder encode = new BASE64Encoder();
            System.out.println("bc encrypt :"+ encode.encode(result));

            //解密
            p.init(Cipher.DECRYPT_MODE,convertSecretKey);//设置为解密模式
            result = p.doFinal(result);
            System.out.println("bcs decrypt :"+ new String(result));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
