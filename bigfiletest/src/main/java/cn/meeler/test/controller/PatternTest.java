package cn.meeler.test.controller;

import org.junit.Test;

import java.io.*;
import java.util.Base64;
import java.util.regex.Pattern;

public class PatternTest {

    @Test
    public void test() {
        //url匹配
        String reg = "(((https|http)?://)?([a-z0-9]+[.])|(www.))\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";
        Pattern pattern = Pattern.compile(reg);
        boolean matches = pattern.matcher("http://www.it-times.com.cn/a/digital/2018/0823/22896.html").matches();
        System.out.println(matches);

        //统一社会信用代码
        String reg1 = "[1-9A-GY]{1}[1239]{1}[1-5]{1}[0-9]{5}[0-9A-Z]{10}";
        Pattern pattern1 = Pattern.compile(reg1);
        boolean matches1 = pattern1.matcher("91440300MA5EYKXC7W").matches();
        System.out.println(matches1+"==============================");

        String reg2 = "(111)";
        Pattern pattern2 = Pattern.compile(reg2);
        boolean matches2 = pattern2.matcher("111").matches();
        System.out.println(matches2);

        String reg3 = "^[A-Za-z\\d+/]([A-Za-z\\d+/][A-Za-z\\d+/=]|==)$";
        Pattern pattern3 = Pattern.compile(reg3);
        boolean matches3 = pattern3.matcher("").matches();
        System.out.println(matches3);

        //身份证号码
        String reg4 = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";
        Pattern pattern4 = Pattern.compile(reg4);
        boolean matches4 = pattern4.matcher("430124199210031719").matches();
        System.out.println(matches4);

        //电话号码
        String reg5 = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern pattern5 = Pattern.compile(reg5);
        boolean matches5 = pattern5.matcher("18670745469").matches();
        System.out.println(matches5);

        //base64
        String reg6 = "^[A-Za-z\\d+/]{10,1048576}([A-Za-z\\d+/][A-Za-z\\d+/=]|==)$";
        Pattern pattern6 = Pattern.compile(reg6);
        boolean matches6 = pattern6.matcher("FSDFSFASDFASFASFDS").matches();
        System.out.println(matches6);

        String reg7 = "[0-9a-z,]{32}";
        Pattern pattern7 = Pattern.compile(reg7);
        boolean matches7 = pattern7.matcher("ff8080816b54f27c016b6550be800df0").matches();
        System.out.println(matches7);



    }


    public void test2(){

    }
    public static String Base64ToFile(String encode, String originalName) {
        String temppath = "D:/aaa/aaaaaaaa.jpg";
        byte[] decode = Base64.getDecoder().decode(encode);
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fos = new FileOutputStream(temppath);
            bos = new BufferedOutputStream(fos);
            bos.write(decode);
            return temppath;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (bos != null) {
                try {
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
