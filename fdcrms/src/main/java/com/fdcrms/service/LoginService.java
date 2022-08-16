package com.fdcrms.service;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * author: CodingPang
 * Date: 2022/07/30 21:37
 * Description:
 * Version: V1.0
 */
public interface LoginService {
    /**
     * 登录
     * @param UName 用户名
     * @param password 密码
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    HashMap<String, Object> login(String UName, String password) throws SQLException, ClassNotFoundException;

    /**
     * 注册家庭成员账号
     * @param memName 姓名
     * @param memPhone 手机号
     * @param memGender 性别
     * @param memAge 年龄
     * @param memPwd 密码
     * @param reMemPwd 再次输入密码
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    HashMap<String, Object> register(String memName, String memPhone, String memGender, String memAge, String memPwd, String reMemPwd) throws SQLException, ClassNotFoundException;
}
