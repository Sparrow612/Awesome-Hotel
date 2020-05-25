package com.example.hotel.bl.user;

import com.example.hotel.po.User;
import com.example.hotel.vo.*;

import java.util.List;

/**
 * @author huwen
 * @date 2019/3/23
 */
public interface AccountService {

    /**
     * 注册账号
     *
     * @return
     */
    ResponseVO registerAccount(UserVO userVO);

    /**
     * 用户登录，登录成功会将用户信息保存再session中
     *
     * @return
     */
    User login(UserForm userForm);

    /**
     * 获取用户个人信息
     *
     * @param id
     * @return
     */
    User getUserInfo(int id);

    /**
     * 更新用户个人信息
     *
     * @param id
     * @param password
     * @param username
     * @param phonenumber
     * @return
     */
    ResponseVO updateUserInfo(int id, String password, String username, String phonenumber);

    //todo
    ResponseVO updateCredit(int id, double credit);

    ResponseVO personalVIP(int id, String birthday);

    ResponseVO corporateVIP(int id, String corporate);

    // 把对应企业的所有企业用户全都设置成普通用户
    ResponseVO normalUser(String corporate);

    void updatePortrait(int userId, String url);

    // 根据酒店id来获取工作人员的telephone number
    List<String> getManagerTelephone(int hotelId);

}
