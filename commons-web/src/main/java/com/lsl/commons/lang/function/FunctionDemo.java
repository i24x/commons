package com.lsl.commons.lang.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.lsl.commons.lang.pojo.User;

public class FunctionDemo {
    public static void main(String[] args) {
        Consumer<String> c = User::print;
        c.accept("OK");
        Supplier<User> supplier = User::new;
        User user = supplier.get();
        Function<String, String> g = user::get;
        System.out.println(g.apply("123456"));
        user.say("Liming", (name) -> {
            return name + System.nanoTime();
        });
        Supplier<String> toString = user::toString;
        System.out.println(toString.get());
        CallBackFunction sayGoodBye = user::sayGoodBye;
        sayGoodBye.invoke();
    }

}
