package messagedigest.mac;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * MAC:消息认证码算法 包含了MD和HSA算法
 * 含有秘钥的散列函数算法
 * Created by 浮生若梦 on 2016/8/24.
 */
public class MACTest {
    private static String str = "test hshf string";
    public static void main(String[] args) {
        jdkHmacMD5();
        bcHmacMD5();
    }

    /**
     * jdk Hmac MD5算法
     */
    public static void jdkHmacMD5(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");//初始化KeyGenerator
            SecretKey secretKey = keyGenerator.generateKey();//产生秘钥
//            byte[] key = secretKey.getEncoded();//获得秘钥

            byte[]key = Hex.decodeHex("1122334455".toCharArray());//获得秘钥

            //还原秘钥
            SecretKey restoreKey = new SecretKeySpec(key,"HmacMD5");
            //实例化MAC
            Mac mac = Mac.getInstance(restoreKey.getAlgorithm());
            mac.init(restoreKey);
            byte[] encode = mac.doFinal(str.getBytes());
            System.out.println("jdk Hmac encode:"+ Hex.encodeHexString(encode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * bc Hmac MD5  对比jdk的实现
     */
    public static void bcHmacMD5(){
        HMac hmac = new HMac(new MD5Digest());
        hmac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode("1122334455")));//秘钥的初始化来源参数 1122334455
        hmac.update(str.getBytes(),0,str.getBytes().length);
        byte[] hmacByte = new byte[hmac.getMacSize()];
        hmac.doFinal(hmacByte,0);
        System.out.println("bc hmac MD5 encode:"+ Hex.encodeHexString(hmacByte));
    }

}
