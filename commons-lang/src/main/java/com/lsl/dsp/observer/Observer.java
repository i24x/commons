package com.lsl.dsp.observer;

public interface Observer {
    public void update(float temp, float humidity, float pressure);
}