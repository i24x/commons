package com.lsl.common.concurrent.cas;

/**
 * CreateDate: 2019/8/15 该问题可以验证
 *
 * @author yang.cao
 * @version 1.0
 */

public class LslFinal {
    final int x;
    int y;
    // static final int z;//编译错误
    static LslFinal f;

    public LslFinal() {
        x = 3;
        y = 4;
        // z = 12;
    }

    void writer() {
        f = new LslFinal();
    }

    static void reader() {
        if (f != null) {
            int i = f.x; // 保证看到3
            int j = f.y; // 可以看到0
        }
    }
}
