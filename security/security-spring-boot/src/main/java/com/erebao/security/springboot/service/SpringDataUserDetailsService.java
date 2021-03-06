package com.erebao.security.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erebao.security.springboot.dao.UserDao;
import com.erebao.security.springboot.model.UserDto;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    // 根据账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 将来连接数据库根据账号查询用户信息
        UserDto userDto = userDao.getUserByUsername(username);
        if (userDto == null) {
            // 如果用户查询不到，返回null，由provider来抛出异常
            return null;
        }
        // 根据用户的id查询权限
        List<String> permissions = userDao.findPermissionsByUserId(userDto.getId());
        // 将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);

        UserDetails userDetails = User.withUsername(userDto.getUsername())// 账号
            .password(userDto.getPassword())// 密码
            .authorities(permissionArray)// 权限
            .build();
        return userDetails;
    }
}
