package com.lsl.account.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lsl.QuickstartApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuickstartApplication.class})
public class TAccountTest {
    // AR模式
    @Test
    public void insert() {
        TAccount account = new TAccount();
        account.setName(System.getProperty("user.name") + System.nanoTime());
        account.setOrg((System.nanoTime() % 2 == 0 ? "成都公安局" : "南充公安局") + System.nanoTime());
        account.setMark((System.nanoTime() % 2 == 0 ? "无效" : "有效") + System.nanoTime());
        account.insert();
    }

    @Test
    public void update() {
        TAccount account = new TAccount();
        account.setOrg((System.nanoTime() % 2 == 0 ? "成都公安局" : "南充公安局") + System.nanoTime());
        account.setMark((System.nanoTime() % 2 == 0 ? "无效" : "有效") + System.nanoTime());
        UpdateWrapper<TAccount> update = Wrappers.<TAccount>update().eq("1", 1);
        account.update(update);
    }

}