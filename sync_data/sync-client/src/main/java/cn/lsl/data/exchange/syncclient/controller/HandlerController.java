package cn.lsl.data.exchange.syncclient.controller;

import cn.lsl.data.exchange.syncclient.properties.ReceiveProperties;
import cn.lsl.data.exchange.syncclient.service.SendMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 *@author sy
 */
@RestController
public class HandlerController {

    private Logger logger = LoggerFactory.getLogger(HandlerController.class);
    @Autowired
    private SendMessageService sendMessageService;
    @Autowired
    private ReceiveProperties properties;

    @RequestMapping("/sync")
    public String sync(@RequestParam String startTime,
                        @RequestParam String endTime){
        try {
            sendMessageService.send("\'" + startTime + "\'","\'" + endTime + "\'");
            return "success";
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return "fail";
    }
}
