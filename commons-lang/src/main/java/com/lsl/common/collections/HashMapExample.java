package com.lsl.common.collections;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("0", "1");
        System.out.println(map.getOrDefault("0", "default"));
        System.out.println(map.getOrDefault("1", "default"));
        map.putIfAbsent("0", "000");
        map.putIfAbsent("1", "111");
        map.forEach((k, v) -> {
            System.out.println(k + "," + v);
        });
        Object v2 = map.computeIfAbsent("2", (x) -> {
            return x + "HashMap";
        });
        System.out.println(map.get("2"));
        map.computeIfPresent("0", (k, v) -> {
            System.out.println(map.get("2"));
            return k + "|" + v;
        });
        System.out.println(map.get("0"));
    }
}
