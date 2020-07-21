package com.lsl.common.util;

import redis.clients.jedis.Jedis;

public class RedisMonitor {

    static class MonitorTask implements Runnable {
        private Jedis client;

        MonitorTask(Jedis jedis) {
            client = jedis;
        }

        @Override
        public void run() {
            client.monitor(new redis.clients.jedis.JedisMonitor() {
                @Override
                public void onCommand(String command) {
                    System.out.println(command);
                }
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Jedis monitor = new Jedis("localhost", 6379);
        Thread monitorTask = new Thread(new MonitorTask(monitor));
        monitorTask.start();

    }

}
