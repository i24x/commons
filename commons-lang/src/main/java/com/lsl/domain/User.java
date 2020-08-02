package com.lsl.domain;

import com.lsl.common.core.math.MathUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String id;
    private String name;
    private String address;
    private Long phone;
    private String email;
    private IdCard idCard;

    public void setId(String id) {
        this.id = id;
    }

    public static User instance() {
        // OptionalLong.of(System.nanoTime()).
        return User.builder().id(MathUtils.getId()).name("lsl" + System.nanoTime())
            .address("Asia/ShangHai" + System.nanoTime()).phone(System.nanoTime())
            .email("lsl" + System.nanoTime() + "@gmail.com").build();
    }

    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", address='" + address + '\'' + ", phone="
            + phone + ", email='" + email + '\'' + '}';
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("User对象被回收:" + id);
        super.finalize();
    }

    public synchronized void sayHello() {
        System.out.println("hello->" + name);
    }

    public static void sayGoodBye() {
        System.out.println("sayGoodBye。。。");
    }

    public static void say() {
        System.out.println("say。。。");
    }

    public static void print(String msg) {
        System.out.println("print。。。" + msg);
    }

    // Function<T,R>
    public static String get(String s) {
        return s;
    }

    public static void say(String name, Function<String, String> action) {
        System.out.println("name-" + System.identityHashCode(name) + ":" + action.apply(name));
    }

    private static Role r = new Role(1, "admin");

    private static User createUserCard() {
        IdCard idCard = new IdCard(r);
        User user = User.instance();
        user.setIdCard(idCard);
        return user;
    }

    public static void main(String[] args) throws InterruptedException {
        User userCard = createUserCard();
        userCard = null;
        TimeUnit.SECONDS.sleep(3);

    }
}
