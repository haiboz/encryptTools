package dataencryption.md5;

import java.security.MessageDigest;

/**
 * MD5加密类 不可逆 只能暴力破解
 * Created by 浮生若梦 on 2016/8/23.
 */
public class MD5Util {
    public static String encode(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] tempByte = plainText.getBytes();
            md.update(tempByte);
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
                return buf.toString();
            //System.out.println("result: " + buf.toString());// 32位的加密
            //System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String args[]) {
        String str = "12345678";
        Long b1 = System.currentTimeMillis();

        for(int i =0;i<1;i++){
            //MD5.encode(str);
            System.out.println(MD5Util.encode(str)); //900150983cd24fb0d6963f7d28e17f72

//          Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//          //md5.setEncodeHashAsBase64(false);
//          //md5.encodePassword(str, "");
//          System.out.println(md5.encodePassword(str, ""));
        }
        Long e1 = System.currentTimeMillis();
        System.out.println("MD5.encode耗时:"+(e1-b1));
    }
}
