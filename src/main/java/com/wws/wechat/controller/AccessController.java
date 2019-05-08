package com.wws.wechat.controller;

import com.wws.wechat.service.AccessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
*@Description: 微信接入 controller
*@Author: wangwith
*@date: 2019/5/8
*/
@Controller
public class AccessController {

    @Autowired
    AccessService accessService;

    Logger log = Logger.getLogger(AccessController.class);

    /**
    *@Description: 微信接入校验
    *@Author: wangwith
    *@date: 2019/5/6
    */
    @RequestMapping(value= "/api/access",method = RequestMethod.GET)
    @ResponseBody
    public String checkSignature(String signature, String timestamp, String nonce, String echostr) {
        log.info("微信接入。。。");
        if (accessService.checkSignature(signature, timestamp, nonce)) {
            return  echostr;
        }else {
            return null;
        }
    }


}
