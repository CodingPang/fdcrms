package com.fdcrms.dao;

import com.fdcrms.pojo.Login;
import com.fdcrms.pojo.Member;

import java.sql.SQLException;

/**
 * author: CodingPang
 * Date: 2022/07/30 21:25
 * Description:
 * Version: V1.0
 */
public interface LoginDao {
    /**
     * 通过用户名查询用户
     * @param UName
     * @return
     */
    Login selectOnrByUName(String UName) throws SQLException, ClassNotFoundException;

    /**
     * 通过用户名（手机号）和密码查询用户
     * @param user
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Login selectUserByPhoneAndPwd(Login user) throws SQLException, ClassNotFoundException;

    /**
     * 新增账号
     * @param login
     * @return
     */
    int addOne(Login login) throws SQLException, ClassNotFoundException;

    Login selectOneById(Integer uId);

    /**
     * 删除登录账号
     * @param uId
     * @return
     */
    int deleteById(Integer uId) throws SQLException, ClassNotFoundException;


    /**
     * 登录账号同时更新手机号和密码
     * @param login 家庭成员新手机号和密码的账号
     * @param phoneNum 旧手机号
     * @return
     */
    int updatePhoneAndPwd(Login login, String phoneNum) throws SQLException, ClassNotFoundException;

    /**
     * 登录账号只更新手机号
     * @param newPhone 新手机号
     * @param oldPhone
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    int updatePhone(String newPhone, String oldPhone) throws SQLException, ClassNotFoundException;
}
