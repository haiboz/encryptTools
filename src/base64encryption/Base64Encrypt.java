package base64encryption;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.IOException;

/**
 *
 * Created by 浮生若梦 on 2016/8/23.
 */
public class Base64Encrypt {
    private static String str = "zhb test string";
    public static void main(String[] args) {
        jdkBase64();
        commonsCodecBase64();
        bouncyCastleBase64();
    }
    /**
     * jdk 实现base64的加解密
     */
    public static void jdkBase64(){
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encode = base64Encoder.encode(str.getBytes());
        System.out.println("encode:"+encode);
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            byte[] decode = base64Decoder.decodeBuffer(encode);
            System.out.println("decode:"+new String(decode));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * cc的base64加解密
     */
    public static void commonsCodecBase64(){
        byte[] encode = Base64.encodeBase64(str.getBytes());
        System.out.println("encode:"+new String(encode));
        byte[] decode = Base64.decodeBase64(encode);
        System.out.println("decode:"+new String(decode));
    }

    /**
     * bc Base64加解密
     */
    public static void bouncyCastleBase64(){
        byte[] encode = org.bouncycastle.util.encoders.Base64.encode(str.getBytes());
        System.out.println("encode:"+new String(encode));
        byte[] decode = Base64.decodeBase64(encode);
        System.out.println("decode:"+new String(decode));
    }
}
