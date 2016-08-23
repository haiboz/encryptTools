package dataencryption.pbe;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * PBE:password based encryotion 基于口令的加密
 * 对称加密  加salt 对已有的算法进行包装
 * PBEWithMD5AndAES
 * Created by 浮生若梦 on 2016/8/23.
 */
public class PBEEncrypt {
    private static String str = "zhb test sdfsdf";
    public static void main(String[] args) {
        jdkPEB();
    }

    public static void jdkPEB(){
        try {
            //初始化加密因子
            SecureRandom secureRandom = new SecureRandom();
            byte[] salt = secureRandom.generateSeed(8);

            //口令与秘钥
            String password = "123456";
            PBEKeySpec pbeKeySoec = new PBEKeySpec(password.toCharArray());//秘钥转换必须的对象
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            Key key = factory.generateSecret(pbeKeySoec);//转换成秘钥
            //加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt,100);//加解密因子和迭代次数
            Cipher p = Cipher.getInstance("PBEWITHMD5andDES");
            p.init(Cipher.ENCRYPT_MODE,key,pbeParameterSpec);
            byte[] result = p.doFinal(str.getBytes());
            System.out.println("jdk pbe 加密后："+ Base64.encodeBase64String(result));
            //解密
            p.init(Cipher.DECRYPT_MODE,key,pbeParameterSpec);
            result = p.doFinal(result);
            System.out.println("jdk pbe 解密后:"+ new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
