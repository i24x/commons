package com.lsl.user.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lsl.QuickstartApplication;
import com.lsl.user.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuickstartApplication.class})
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void queryRowBound() {
        QueryWrapper<User> wp = new QueryWrapper<User>();
        wp.likeRight("username", "han");
        // wp.inSql()
        Page page = new Page();
        Wrapper<User> wrapper2 = Condition.<User>wrapper().like("username", "han%");
        Wrapper wrapper = Condition.create().like("username", "han%");

        IPage<User> userIPage = userMapper.selectPage(page, wp);
        userIPage.getRecords().stream().forEach(System.out::println);
    }
}