package com.lsl.common.lang.math;

import com.lsl.dsp.prototype.User;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionExample {

    public static void main(String args[]) {

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
        return Arrays.asList(new User("liming", "18502817210"), new User("liming", "18502817211"),
            new User("hsp", "18202817211")).stream();
    }
}