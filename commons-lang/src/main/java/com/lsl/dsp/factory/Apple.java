package com.lsl.dsp.factory;

public class Apple implements Fruit {

    @Override
    public void grow() {
        System.out.println("Apple grow");
    }

    @Override
    public void plant() {
        System.out.println("Apple plant");
    }

}
