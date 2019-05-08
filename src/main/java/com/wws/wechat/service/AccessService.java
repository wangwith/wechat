package com.wws.wechat.service;

/**
*@Description:
*@Author: wangwith
*@date: 2019/5/8
*/

public interface AccessService {

    /**
    *@Description: 微信接入校验service
    *@Author: wangwith
    *@date: 2019/5/8
    */
    boolean checkSignature(String signature, String timestamp, String nonce);
}
