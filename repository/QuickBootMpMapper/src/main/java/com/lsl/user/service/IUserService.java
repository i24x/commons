package com.lsl.user.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lsl.user.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Chrow Yeung
 * @since 2020-02-05
 */
public interface IUserService extends IService<User> {
    public List<User> queryWrapper(Wrapper<User> queryWrapper);
}
