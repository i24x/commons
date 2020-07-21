package com.lsl.account.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsl.account.entity.TAccount;
import com.lsl.account.service.TAccountService;

/**
 *
 * @author Chrow Yeung
 * @since 2020-02-07
 */
@Controller
@RequestMapping("/account")
public class TAccountController {

    @Autowired
    public TAccountService accountService;

    /**
     * 跳转列表页面
     * 
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/tAccount/index"})
    public String index(HttpServletRequest request) {
        return "tAccountIndex";
    }

    /**
     * 保存和修改
     * 
     * @param tAccount
     *            实体
     * @return 0 失败 1 成功
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public boolean tAccountSave(TAccount tAccount) {
        return accountService.saveOrUpdate(tAccount);
    }

    /**
     * 根据id删除对象
     * 
     * @param id
     *            实体ID
     * @return 0 失败 1 成功
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/tAccount/{id}")
    public boolean tAccountDelete(Long id) {
        return accountService.removeById(id);
    }

    /**
     * 查询所有用户
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public List<TAccount> selectAll() {
        return accountService.list();
    }
}