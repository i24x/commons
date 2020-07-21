package com.lsl.commons.zookeeper.api;

import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * ＺＫ基本操作
 * 
 * @author Admin
 *
 */
public class ZkClientTest {

    static final String CONNECT_ADDR = "192.168.200.3:2181";

    public static void main(String[] args) throws Exception {
        ZkClient client = ZkClientBuilder.create(new ZkClientConfig(CONNECT_ADDR));
        // 1. create and delete方法
        client.delete("/temp");
        client.deleteRecursive("/super");
        client.createEphemeral("/temp");
        client.createPersistent("/super/c1", true);
        // Thread.sleep(10000);
        client.delete("/temp");
        client.deleteRecursive("/super");

        // 2. 设置path和data 并且读取子节点和每个节点的内容
        client.createPersistent("/super", "1234");
        client.createPersistent("/super/c1", "c1内容");
        client.createPersistent("/super/c2", "c2内容");
        List<String> list = client.getChildren("/super");
        for (String p : list) {
            System.out.println(p);
            String rp = "/super/" + p;
            String data = client.readData(rp);
            System.out.println("节点为：" + rp + "，内容为: " + data);
        }

        // //3. 更新和判断节点是否存在
        client.writeData("/super/c1", "新内容");
        System.out.println(client.<String>readData("/super/c1"));
        System.out.println(client.exists("/super/c1"));
        //
        // //4.递归删除/super内容
        client.deleteRecursive("/super");
    }
}
