package com.lsl.commons.lang.optional;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import com.lsl.commons.lang.pojo.User;

public class OptionalTester {

    public static void main(String[] args) {
        String userName = getName(null);
        System.out.println(userName);
        Optional<User> optionalUser = Optional.of(new User());
        optionalUser.ifPresent((x) -> {
            System.out.println(x.name);
        });
        System.out.println(optionalUser.isPresent());
        getUser().orElse(new User());
    }

    public static String getName(User u) {
        return Optional.ofNullable(u).map(user -> user.name).orElse("Unknown");
    }

    public static Optional<User> getUser() {
        return Optional.of(null);
    }

    public static Stream<User> getUsers() {
        return Arrays.asList(new User(), new User(), new User()).stream();
    }
}
