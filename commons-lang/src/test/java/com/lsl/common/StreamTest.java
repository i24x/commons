package com.lsl.common;

import com.alibaba.fastjson.JSON;
import com.lsl.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    public void filterTest() {
        List<String> list = Arrays.asList("abc", "", "bc", "efg");
        Optional.of(list).ifPresent(x -> {
            System.out.println(x.stream().filter(v -> StringUtils.isNotEmpty(v)).count());
        });
    }

    @Test
    public void joiningTest() {
        List<String> list = Arrays.asList("abc", "", "bc", "efg");
        Optional.of(list).ifPresent(x -> {
            String mergeResult = x.stream().filter(v -> StringUtils.isNotEmpty(v)).collect(Collectors.joining(","));
            Assert.assertEquals(mergeResult, "abc,bc,efg");
        });
    }

    @Test
    public void distinctTest() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        long count = numbers.stream().distinct().count();
        Assert.assertEquals(count, 4);
    }

    @Test
    public void groupByTest() {

        Map<Object, List<User>> collect = Arrays.asList(User.instance(), User.instance(), User.instance()).stream()
            // .collect(Collectors.groupingBy(User::getName));
            .collect(Collectors.groupingBy((u) -> {
                return u.getName();
            }));
        System.out.println(JSON.toJSONString(collect));
    }

    @Test
    public void flatMapTest() {
        String collect = Arrays.asList("abc", "", "bc", "efg").stream().flatMap(s -> Stream.of(s.split("b")))
            .filter(v -> StringUtils.isNotEmpty(v)).distinct().collect(Collectors.joining("-"));
        System.out.println(collect);

        String collect1 = Arrays.asList(User.instance(), User.instance(), User.instance()).stream()
            .map(v -> v.getName()).collect(Collectors.joining("-"));
        System.out.println(collect1);
    }

    @Test
    public void flatTest() {
        Stream<String> stream = Arrays.asList("abc", "", "bc", "efg").stream();
        String collect =
            stream.filter(v -> StringUtils.isNotEmpty(v)).distinct().sorted().collect(Collectors.joining("-"));
        System.out.println(collect);
        Optional<String> first = Arrays.asList("abc", "", "bc", "efg").stream().findFirst();
        first.ifPresent(x -> {
            System.out.println(x);
        });
        first = new ArrayList<String>().stream().findFirst();
        first.ifPresent(x -> {
            System.out.println(x);
        });
    }

    public static void main1(String args[]) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl", "asas");
        long count = 0;
        List<String> filtered = null;
        String mergedString = null;
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = null;
        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt());
        }
        count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println("strings.stream().filter: " + count);
        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("count: " + count);
        // collect
        filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("Collectors.toList(): " + filtered);

        mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));

        System.out.println("Collectors.joining: " + mergedString);

        squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());

        System.out.println("Squares List: " + squaresList);
        System.out.println("stream().map: " + integers);

        IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("summaryStatistics: " + stats.getMax());
        System.out.println("summaryStatistics: " + stats.getMin());
        System.out.println("summaryStatistics: " + stats.getSum());
        System.out.println("summaryStatistics: " + stats.getAverage());

        random.ints().limit(10).sorted().forEach(System.out::println);
        // filter
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("count()" + count);

        Optional<String> findFirst = strings.stream().findFirst();
        findFirst.ifPresent(System.out::println);

        System.out.println(strings.stream().anyMatch((x) -> {
            return x.startsWith("a");
        }));
        System.out.println(strings.stream().allMatch((x) -> {
            return x.startsWith("a");
        }));
        Optional<String> reduce = strings.stream().filter(x -> !"".equals(x)).reduce((x, y) -> {
            return x + "->" + y;
        });
        System.out.println(reduce.get());
        String reduce2 = strings.stream().filter(x -> !"".equals(x)).reduce("K", (x, y) -> {
            return x + "->" + y;
        });
        System.out.println(reduce2);

        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Integer lengthSum = stream.reduce(1, (sum, str) -> sum + str.length(), (a, b) -> a + b);
        // int lengthSum = stream.mapToInt(str -> str.length()).sum();
        System.out.println(lengthSum);

        // accumulator:2+6=8
        // accumulator:2+4=6
        // accumulator:2+3=5
        // accumulator:2+2=4
        // accumulator:2+1=3
        // accumulator:2+5=7
        // combiner:7+8=15
        // combiner:6+15=21
        // combiner:4+5=9
        // combiner:3+9=12
        // combiner:12+21=33

        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6);
        intList.stream().parallel().reduce(2, (u, x) -> {
            System.out.println("accumulator:" + (u + "+" + x + "=" + (u + x)));
            return u + x;
        }, (u, x) -> {
            System.out.println("combiner:" + (u + "+" + x + "=" + (u + x)));
            return u + x;
        });

        Map<Object, List<User>> collect = getUsers()
            // .collect(Collectors.groupingBy(User::getName));
            .collect(Collectors.groupingBy((u) -> {
                return u.getName();
            }));
        System.out.println(collect);
    }

    public static Stream<User> getUsers() {
        return Arrays.asList(User.instance(), User.instance(), User.instance()).stream();
    }
}