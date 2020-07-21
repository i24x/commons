package com.lsl.user.mapper;

import org.apache.ibatis.annotations.CacheNamespace;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsl.user.entity.User;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Chrow Yeung
 * @since 2020-02-05
 */
@CacheNamespace
public interface UserMapper extends BaseMapper<User> {

}
