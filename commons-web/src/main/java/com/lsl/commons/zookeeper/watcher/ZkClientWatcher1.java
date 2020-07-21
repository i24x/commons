package com.lsl.commons.zookeeper.watcher;

import com.lsl.commons.zookeeper.api.ZkClientBuilder;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

public class ZkClientWatcher1 {

    public static void main(String[] args) throws Exception {
        ZkClient zkc = ZkClientBuilder.createDefault();

        // 对父节点添加监听子节点变化。
        zkc.subscribeChildChanges("/super", new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("parentPath: " + parentPath);
                System.out.println("currentChilds: " + currentChilds);
                System.out.println(zkc.<String>readData("/super/c1"));
            }
        });

        Thread.sleep(3000);

        zkc.createPersistent("/super");
        Thread.sleep(1000);

        zkc.createPersistent("/super" + "/" + "c1", "c1内容");
        Thread.sleep(1000);

        zkc.createPersistent("/super" + "/" + "c2", "c2内容");
        Thread.sleep(1000);

        zkc.delete("/super/c2");
        Thread.sleep(1000);

        // zkc.deleteRecursive("/super");
        Thread.sleep(Integer.MAX_VALUE);

    }
}
