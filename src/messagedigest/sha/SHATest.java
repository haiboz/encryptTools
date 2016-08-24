package messagedigest.sha;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;

/**
 * SHA:secure hash algorithm 安全散列算法
 * cc 是jdk的封装  减少代码量 bc是jdk的扩展
 * Created by 浮生若梦 on 2016/8/24.
 */
public class SHATest {
    private static String str = " Test message Digest.";
    public static void main(String[] args) {
        jdkSHA1();
        bcSHA1();
        bcSHA224();
        bcSHA224_provider();
        ccSHA1();
    }

    /**
     * jdk SHA1
     */
    public static void jdkSHA1(){
        try {
            //方法一
            MessageDigest md1 = MessageDigest.getInstance("SHA");
            md1.update(str.getBytes());
            byte[] mdByte1 = md1.digest();
            System.out.println("jdk SHA encode1: "+ Hex.encodeHexString(mdByte1));
            //方法二
            MessageDigest md2 = MessageDigest.getInstance("SHA");
            byte[] mdByte2 = md2.digest(str.getBytes());
            System.out.println("jdk SHA encode2: "+ Hex.encodeHexString(mdByte2));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * bc SHA1
     */
    public static void bcSHA1(){
        Digest digest = new SHA1Digest();
        digest.update(str.getBytes(),0,str.getBytes().length);
        byte[] sha1Byte = new byte[digest.getDigestSize()];
        digest.doFinal(sha1Byte,0);
        System.out.println("bc  sha1 encode: "+Hex.encodeHexString(sha1Byte));
    }

    /**
     * bc SHA224
     */
    public static void bcSHA224(){
        Digest digest = new SHA224Digest();
        digest.update(str.getBytes(),0,str.getBytes().length);
        byte[] sha1Byte = new byte[digest.getDigestSize()];
        digest.doFinal(sha1Byte,0);
        System.out.println("bc SHA-224 encode: "+Hex.encodeHexString(sha1Byte));
    }

    /**
     * bc SHA224 使用provider方式
     */
    public static void bcSHA224_provider(){
        try {
            Security.addProvider(new BouncyCastleProvider());
            MessageDigest md = MessageDigest.getInstance("SHA-224");
            byte[] mdByte = md.digest(str.getBytes());
            System.out.println("bc SHA-224 provider:"+Hex.encodeHexString(mdByte));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * cc SHA1
     */
    public static void ccSHA1(){
        String encode1 = sha1Hex(str.getBytes());
        String encode2 = DigestUtils.sha1Hex(str);
        System.out.println("cc SHA1 encode1:"+encode1);
        System.out.println("cc SHA1 encode2:"+encode2);
    }


}
