package dataencryption.other;

import java.io.IOException;
import java.util.Scanner;

/**
 * 测试获取机器码
 * Created by 浮生若梦 on 2016/8/23.
 */
public class TestMacId {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            String[] commoned = new String[]{"wmic", "cpu", "get", "ProcessorId"};
            Process process = Runtime.getRuntime().exec(commoned);
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            String property = sc.next();
            String serial = sc.next();
            System.out.println(property +":"+ serial);
            String macId = MD5Util.encode(serial);
            System.out.println("macId:"+macId);
            System.out.println("time:"+ (System.currentTimeMillis() - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
