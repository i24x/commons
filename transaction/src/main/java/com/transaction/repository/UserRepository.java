package com.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transaction.model.User;

/**
 * @description: 一定要写注释啊
 * @date: 2019-06-24 12:43
 * @author: 十一
 */
public interface UserRepository extends JpaRepository<User, Integer> {}
