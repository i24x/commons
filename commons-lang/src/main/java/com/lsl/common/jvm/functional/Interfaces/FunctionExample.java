package com.lsl.common.jvm.functional.Interfaces;

import com.lsl.domain.User;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionExample {
    public static void main(String[] args) {
        Consumer<String> c = User::print;
        c.accept("OK");
        Supplier<User> supplier = User::new;
        User user = supplier.get();
        Function<String, String> g = User::get;
        System.out.println(g.apply("123456"));
        User.say("Liming", (name) -> {
            return name + System.nanoTime();
        });
        Supplier<String> toString = user::toString;
        System.out.println(toString.get());
        InvokeFunction sayGoodBye = User::sayGoodBye;
        sayGoodBye.invoke();

    }

}
