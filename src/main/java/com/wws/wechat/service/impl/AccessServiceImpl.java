package com.wws.wechat.service.impl;

import com.wws.wechat.service.AccessService;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
*@Description:
*@Author: wangwith
*@date: 2019/5/8
*/

@Service
public class AccessServiceImpl implements AccessService {

    public static final String TOKEN = "wws310";

    Logger log = Logger.getLogger(AccessServiceImpl.class);

    @Override
    public boolean checkSignature(String signature, String timestamp, String nonce) {
        String sortStr = sort(TOKEN,timestamp,nonce);
        String mySignature = shal(sortStr);
        if(""!= mySignature && mySignature.equals(signature)){
            log.info("签名校验通过。。。");
            return true;
        }
        return false;
    }

    /**
    *@Description: 将token、timestamp、nonce三个参数进行字典序排序
    *@Author: wangwith
    *@date: 2019/5/6
    */
    public String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }
        return sb.toString();
    }


    /**
    *@Description:  字符串sha1加密
    *@Author: wangwith
    *@date: 2019/5/6
    */
    public String shal(String str){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
