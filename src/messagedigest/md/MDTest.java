package messagedigest.md;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;

/**
 * MD摘要算法 message digest
 * 多用在检验用户登录 对密码进行摘要 且保证数据库管理员也不可知密码源值
 * 一旦数据库被泄露 也能保证密码安全
 * Created by 浮生若梦 on 2016/8/24.
 */
public class MDTest {
    private static String str = "my test string 123456.";
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        jdkMD5();
        jdkMD2();
        bcMD4();
        bcMD5();
        ccMD5();
        ccMD2();
        System.out.println("耗时："+(System.currentTimeMillis()-start));
    }

    /**
     * jdk MD5摘要算法
     */
    public static void jdkMD5(){
        try {
            MessageDigest  md = MessageDigest.getInstance("MD5");
            byte[] md5Byte = md.digest(str.getBytes());
            System.out.println("jdk md5 encode:"+ Hex.encodeHexString(md5Byte));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * jdk MD2摘要算法
     */
    public static void jdkMD2(){
        try {
            MessageDigest  md = MessageDigest.getInstance("MD2");
            byte[] md5Byte = md.digest(str.getBytes());
            System.out.println("jdk md2 encode:"+ Hex.encodeHexString(md5Byte));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * bc MD4摘要算法
     */
    public static void bcMD4(){
        try {
            //实现方式一
            Digest digest = new MD4Digest();
            digest.update(str.getBytes(),0,str.getBytes().length);
            byte[] md4Byte = new byte[digest.getDigestSize()];
            digest.doFinal(md4Byte, 0);
            System.out.println("bc md4 encode1:"+ Hex.encodeHexString(md4Byte));
            //实现方式二
            Security.addProvider(new BouncyCastleProvider());
            MessageDigest md = MessageDigest.getInstance("MD4");
            Provider provider = md.getProvider();
            byte[] bcMD4Byte = md.digest(str.getBytes());
            System.out.println("bc md4 encode2:"+Hex.encodeHexString(bcMD4Byte));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * bc MD5摘要算法
     */
    public static void bcMD5(){
        try {
            Digest digest = new MD5Digest();
            digest.update(str.getBytes(),0,str.getBytes().length);
            byte[] md5Byte = new byte[digest.getDigestSize()];
            digest.doFinal(md5Byte,0);
            System.out.println("bc MD5 encode:"+ Hex.encodeHexString(md5Byte));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * cc MD5:commons codec
     * cc是简化jdk的操作  不同于bc 是添加了一些jdk没有的支持 比如MD4
     */
    public static void ccMD5(){
        String hexStr = DigestUtils.md5Hex(str);
        System.out.println("cc MD5 encode:"+hexStr);
    }

    /**
     * cc MD2
     */
    public static void ccMD2(){
        String hexStr = DigestUtils.md2Hex(str);
        System.out.println("cc MD2 encode:"+hexStr);
    }

}
