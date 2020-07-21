package cn.lsl.data.exchange.syncclient.controller;

import cn.lsl.data.exchange.syncclient.properties.ReceiveProperties;
import cn.lsl.data.exchange.syncclient.service.SendMessageService;
import cn.lsl.data.exchange.syncclient.utils.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScheduleController {

    @Autowired
    private SendMessageService sendMessageService;

    @Value("${sync.change}")
    private int change;

    @Autowired
    private ReceiveProperties properties;

    @Scheduled(cron = "${sync.time}")
    public void sync(){
        String endTime = TimeHelper.getCurrentTime().substring(0,15)+"0:00";
        String startTime = "\'" + TimeHelper.addMinute(endTime,change).substring(0,17)+"00" +"\'";
//        String endTime = "2020-02-29 23:00:00";
//        String startTime = "\'2020-02-01 23:30:00\'";
        try {
            sendMessageService.send(startTime,"\'"+endTime+ "\'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String endTime = TimeHelper.getCurrentTime();
        String startTime = TimeHelper.addMinute(endTime,-10);
        System.out.println(endTime);
        System.out.println(startTime);
    }
}
