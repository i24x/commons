package com.lsl.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsl.user.entity.User;
import com.lsl.user.service.IUserService;

/**
 *
 * @author Chrow Yeung
 * @since 2020-02-05
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    public IUserService userService;

    /**
     * 跳转列表页面
     * 
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/user/index"})
    public String index(HttpServletRequest request) {
        return "userIndex";
    }

    /**
     * 保存和修改
     * 
     * @param user
     *            实体
     * @return 0 失败 1 成功
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public int userSave(User user) {
        return userService.saveOrUpdate(user) ? 1 : 0;
    }

    /**
     * 根据id删除对象
     * 
     * @param id
     *            实体ID
     * @return 0 失败 1 成功
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public int userDelete(Long id) {
        return userService.removeById(id) ? 1 : 0;
    }

    /**
     * 查询所有用户
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public List<User> selectAll() {
        return userService.list();
    }
}