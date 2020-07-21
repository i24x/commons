package com.lsl.common;

import com.lsl.domain.User;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * 集合框架测试
 *
 * @Author
 * @Date
 */
public class CollectionTest {
    /**
     * TODO yangcao,2020-04-24,2020-04-27 Map测试
     */
    @Test
    public void testMap() {
        // FIXME yangcao,2020-04-24,2020-04-27
        // Instant now = Instant.now();
        // LocalDateTime localDateTime = now.atZone(ZoneOffset.systemDefault()).toLocalDateTime();
        // System.out.println(now.toEpochMilli());
        // System.out.println(System.currentTimeMillis());

        User u = User.instance();
        // Optional.of(u).map(x -> x.getPhone()).ifPresent(System.out::println);

        consumeIfNotNull(u, (v) -> {
            System.out.println(v);
        });

        // consumeIfNotNull(u, v -> {
        // System.out.println(v);
        // },m->{
        // System.out.println(m+"then");
        // });

        // System.out.println(optSanmu(5, 9, 1, 9));
    }

    public static int optSanmu(int x, int y, int z, int w) {
        return (x > y) ? (z > w) ? x : z : w;
    }

    public static <T> void consumeIfNotNull(T t, Consumer<T> p, Consumer<T> th) {
        if (t != null) {
            // p.accept(t);
            p.andThen(th).accept(t);
        }

    }

    public static <T> void consumeIfNotNull(T t, Consumer<T> p) {
        if (t != null) {
            p.accept(t);
            p.andThen(x -> {
                System.out.println("andThen");
            }).accept(t);
        }
    }
}
