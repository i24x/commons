package com.lsl.commons.lang.utils.lang;

import com.lsl.commons.model.Email;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class LslUnsafe {

    private static final Unsafe unsafe = LslUnsafe.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            Field f = Email.class.getDeclaredField("cuid");
            valueOffset = unsafe.objectFieldOffset(f);
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println(valueOffset);
        /**
         * CAS操作 Compare And Swap（比较并交换），当需要改变的值为期望的值时，那么就替换它为新的值，是原子 （不可在分割）的操作。很多并发框架底层都用到了CAS操作，CAS操作优势是无锁，可以减少线程切换耗费
         * 的时间，但CAS经常失败运行容易引起性能问题，也存在ABA问题。在Unsafe中包含compareAndSwapObject、
         * compareAndSwapInt、compareAndSwapLong三个方法，compareAndSwapInt的简单示例如下。
         * https://blog.csdn.net/u010398771/article/details/85319991 https://www.cnblogs.com/throwable/p/9139947.html
         */
        Email data = new Email();
        data.setCuid("0123");
        Field cuid = data.getClass().getDeclaredField("cuid");
        long cuidOffset = unsafe.objectFieldOffset(cuid);
        cuid.setAccessible(true);
        // 比较并交换，比如id的值如果是所期望的值0123，那么就替换为12345，否则不做处理
        unsafe.compareAndSwapObject(data, valueOffset, "01234", "12345");
        System.out.println(data.getCuid());
        unsafe.compareAndSwapObject(data, valueOffset, "0123", "12345");
        System.out.println(data.getCuid());
        System.out.println(data.getAddress());

    }

    public static Unsafe getUnsafe() {
        Field f = null;
        Unsafe unsafe = null;
        try {
            f = Unsafe.class.getDeclaredField("theUnsafe");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        try {
            unsafe = (Unsafe)f.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return unsafe;
    }
}
