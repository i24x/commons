package com.lsl.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.user.entity.User;
import com.lsl.user.mapper.UserMapper;
import com.lsl.user.service.IUserService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Chrow Yeung
 * @since 2020-02-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public List<User> queryWrapper(Wrapper<User> queryWrapper) {

        return baseMapper.selectList(queryWrapper);
    }
}