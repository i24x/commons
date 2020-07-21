package com.lsl.dsp.observer;

public class WeatherStationTest {
    /**
     * 注意如果维护中心单独抽取出来就是发布订阅模式 订阅维护中心 MaintainCenter或registerCenter
     */
    public static void main(String[] args) {
        WeatherData weatherCenter = new WeatherData();
        new AdCard(weatherCenter, "春熙路");
        new AdCard(weatherCenter, "万年场");
        weatherCenter.setMeasurements(80, 65, 30.4f);
    }
}