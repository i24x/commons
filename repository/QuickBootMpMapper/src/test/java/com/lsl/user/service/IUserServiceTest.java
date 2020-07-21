package com.lsl.user.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsl.QuickstartApplication;
import com.lsl.user.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuickstartApplication.class}) // 指定启动类
public class IUserServiceTest {

    @Autowired
    IUserService userService;

    @Test
    public void queryWrapper() {
        QueryWrapper<User> wp = new QueryWrapper<User>();
        wp.likeRight("username", "han");
        // wp.and()
        wp.or((p) -> {
            p.eq("mark", "S");
        });
        // wp.or().likeRight("username", "lu");
        List<User> users = userService.queryWrapper(wp);
        users.stream().forEach(v -> {
            System.out.println(v);
        });
    }

}