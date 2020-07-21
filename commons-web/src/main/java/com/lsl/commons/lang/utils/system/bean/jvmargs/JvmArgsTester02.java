package com.lsl.commons.lang.utils.system.bean.jvmargs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lsl.commons.model.User;

public class JvmArgsTester02 {

    public static void main(String[] args) throws IOException {

        // 1
        // -Xms20m -Xmx20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC

        // 2
        // -Xms20m -Xmx20m -Xmn7m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC

        // 3
        // -XX:NewRatio=老年代/新生代
        // -Xms20m -Xmx20m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC

        /**
         * -server -Xms50m -Xmx50m -Xmn20m -XX:+UseParallelOldGC -XX:+PrintGCApplicationStoppedTime
         * -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=c:/heap.bin
         * 
         * jmap -heap pid
         * 
         * Heap Configuration: MinHeapFreeRatio = 0 MaxHeapFreeRatio = 100 MaxHeapSize = 52428800 (50.0MB) NewSize =
         * 20971520 (20.0MB) MaxNewSize = 20971520 (20.0MB) OldSize = 5439488 (5.1875MB) NewRatio = 2 SurvivorRatio = 8
         * PermSize = 21757952 (20.75MB) MaxPermSize = 85983232 (82.0MB) G1HeapRegionSize = 0 (0.0MB)
         * 
         * Heap Usage: PS Young Generation Eden Space: capacity = 15728640 (15.0MB) used = 2767464
         * (2.6392593383789062MB) free = 12961176 (12.360740661621094MB 17.595062255859375% used From Space: capacity =
         * 2621440 (2.5MB) used = 2619328 (2.49798583984375MB) free = 2112 (0.00201416015625MB) 99.91943359375% used To
         * Space: capacity = 2621440 (2.5MB) used = 0 (0.0MB) free = 2621440 (2.5MB) 0.0% used PS Old Generation
         * capacity = 31457280 (30.0MB) used = 10534912 (10.046875MB) free = 20922368 (19.953125MB) 33.489583333333336%
         * used PS Perm Generation capacity = 22020096 (21.0MB) used = 2647088 (2.5244598388671875MB) free = 19373008
         * (18.475540161132812MB 12.021237327938987% used
         * 
         * NewRatio = 2 //新生代占用最大堆内存比 1/2 SurvivorRatio = 8 //1个survivor 占整个新生代的1/8 2.5*8=20
         * 
         * C:\Users\Administrator>jstat -gc 8924 250 6 S0C S1C S0U S1U EC EU OC OU PC PU YGC YGCT FGC FGCT GCT 2560.0
         * 2560.0 0.0 64.0 15360.0 3728.9 30720.0 14933.3 21504.0 2610.6 3 0.035 1 0.159 0.194 2560.0 2560.0 0.0 64.0
         * 15360.0 3728.9 30720.0 14933.3 21504.0 2610.6 3 0.035 1 0.159 0.194 2560.0 2560.0 0.0 64.0 15360.0 3728.9
         * 30720.0 14933.3 21504.0 2610.6 3 0.035 1 0.159 0.194 2560.0 2560.0 0.0 64.0 15360.0 3728.9 30720.0 14933.3
         * 21504.0 2610.6 3 0.035 1 0.159 0.194 2560.0 2560.0 0.0 64.0 15360.0 3728.9 30720.0 14933.3 21504.0 2610.6 3
         * 0.035 1 0.159 0.194 2560.0 2560.0 0.0 64.0 15360.0 3728.9 30720.0 14933.3 21504.0 2610.6 3 0.035 1 0.159
         * 0.194
         */

        byte[] b = null;
        // Thread task = new Thread(new Runnable() {
        // @Override
        // public void run() {
        // JvmArgsTester02 t = new JvmArgsTester02();
        // t.createUser();
        // System.out.println("内存泄漏....");
        // }
        //
        // });
        // task.start();

        Thread task2 = new Thread(new Task(), "test");
        task2.start();
        // createUser2();
        // JvmArgsTester02 t = new JvmArgsTester02();
        // t.createUser();

        System.gc();
        System.in.read();
    }

    private /*static*/ void createUser() {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 30000; i++) {
            users.add(new User());
        }
        System.out.println("createUser");
        // users.clear();
    }

    private static void createUser2() {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 30000; i++) {
            users.add(new User());
        }
        System.out.println("createUser");
        // users.clear();
    }
}

class Task implements Runnable {

    @Override
    public void run() {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 30000; i++) {
            users.add(new User());
        }
        System.out.println("createUser");
        System.gc();
    }

}