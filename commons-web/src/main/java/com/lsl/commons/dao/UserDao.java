package com.lsl.commons.dao;

import java.util.Map;

import com.lsl.commons.model.User;

public interface UserDao {
    public int updateUser(int id, String name);

    public User selectUser(int id);

    public Map selectPtp();
}
