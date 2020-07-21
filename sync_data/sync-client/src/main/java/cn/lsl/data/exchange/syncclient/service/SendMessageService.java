package cn.lsl.data.exchange.syncclient.service;

import java.io.IOException;

public interface SendMessageService {

    public void send(String startTime,String endTime) throws IOException;
}
