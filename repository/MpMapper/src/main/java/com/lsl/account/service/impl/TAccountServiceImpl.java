package com.lsl.account.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.account.entity.TAccount;
import com.lsl.account.mapper.TAccountMapper;
import com.lsl.account.service.TAccountService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Chrow Yeung
 * @since 2020-02-07
 */
@Service
public class TAccountServiceImpl extends ServiceImpl<TAccountMapper, TAccount> implements TAccountService {

}
