package cn.meeler.test.controller.bdss;

import cn.org.bjca.client.exceptions.*;
import cn.org.bjca.client.security.SecurityEngineDeal;
import org.junit.Before;
import org.junit.Test;

public class BDSSDemo {

    SecurityEngineDeal sed = null;

    @Before
    public void init() throws ApplicationNotFoundException, InitException, SVSConnectException {
        //设置配置文件路径
        SecurityEngineDeal.setProfilePath("D:\\BJCAROOT");
        //实例化
        sed = SecurityEngineDeal.getInstance("TSSDefault");
    }

    @Test
    public void bdss1() throws ParameterTooLongException, ParameterInvalidException, SVSConnectException, ParameterOutRangeException {
        String inData = "test";
        //创建时间戳请求
        String tsRequest = sed.createTSRequest(inData.getBytes(), Boolean.TRUE);
        //产生时间戳
        String tsRes = sed.createTS(tsRequest);
        //获取时间戳信息 1.返回时间 2.返回原文hash值 3.返回签名证书 4.返回服务器证书序列号
        String tsInfo = sed.getTSInfo(tsRes, 4);
        //验证时间戳并验证原文
        int i = sed.verifyTS(tsRes, inData.getBytes());
        System.out.println(tsInfo);
        System.out.println(i);
        System.out.println(tsRes);
    }

    @Test
    public void bdss2() throws ParameterTooLongException, ParameterInvalidException, SVSConnectException, ParameterOutRangeException {
        String inData = "test";
        //根据原文直接返回时间戳
        String tsByOriginalData = sed.createTSByOriginalData(inData);
        //验证时间戳
        boolean b = sed.verifyTimeStamp(tsByOriginalData);
        System.out.println(b);
        //返回当前应用时间戳服务器证书
        String serverCertificate = sed.getServerCertificate();
        System.out.println(serverCertificate);
        String cert = sed.getCert(serverCertificate);
        System.out.println(cert);
    }

    @Test
    public void bdss3() throws ParameterTooLongException, ParameterInvalidException, SVSConnectException, ParameterOutRangeException {
        String inData = "test";
        //对数据进行base64编码
        String s = sed.base64Encode(inData.getBytes());
        System.out.println(s);
        //对base64进行解码
        byte[] bytes = sed.base64Decode(s);
        String ss = new String(bytes);
        System.out.println(ss);

    }
}
