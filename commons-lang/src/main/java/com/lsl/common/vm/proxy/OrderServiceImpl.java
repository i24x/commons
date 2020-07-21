package com.lsl.common.vm.proxy;

public class OrderServiceImpl implements OrderService {
    @Override
    public void addOrder(String id) {
        System.out.println(String.format("add order [%1$s]", id));
    }

    @Override
    public void remove() {

    }
}
