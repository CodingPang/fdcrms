package com.fdcrms.dao.impl;

import com.fdcrms.dao.LoginDao;
import com.fdcrms.pojo.Login;
import com.fdcrms.pojo.Member;
import com.fdcrms.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * author: CodingPang
 * Date: 2022/07/30 21:25
 * Description:
 * Version: V1.0
 */
public class LoginDaoImpl implements LoginDao {
    @Override
    public Login selectOnrByUName(String UName) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "SELECT u_id, u_name, u_pwd FROM t_login WHERE u_name = ?";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 执行查询操作，并处理查询结果集
        ps.setString(1,UName);
        ResultSet resultSet = ps.executeQuery();
        Login login = new Login();
        if (resultSet.next()) {
            login.setUid(resultSet.getInt("u_id"));
            login.setUName(resultSet.getString("u_name"));
            login.setUPassword("u_pwd");
        }
        // 关闭流
        DBUtil.close();
        return login;
    }

    @Override
    public Login selectUserByPhoneAndPwd(Login user) throws SQLException, ClassNotFoundException {
        String sql = "SELECT u_id, u_name, u_pwd FROM t_login WHERE u_name = ? AND u_pwd = ?";


        PreparedStatement ps = DBUtil.createPreparedStatement(sql); // 预编译sql语句

        String phone = user.getUName(); // 取出参数中的手机号
        String pwd = user.getUPassword(); // 取出参数中的密码

        ps.setString(1,phone); // 将手机号作为参数传进sql语句
        ps.setString(2, pwd);

        ResultSet resultSet = ps.executeQuery(); // 执行查询操作

        // 先创建一个临时的User对象
        Login backUser =  null;
        if (resultSet.next()) {
            backUser = new Login();
            backUser.setUid(resultSet.getInt("u_id"));
            backUser.setUName(resultSet.getString("u_name"));
            backUser.setUPassword(resultSet.getString("u_pwd"));
        }

        // 释放连接
        DBUtil.close();

        return backUser;
    }


    @Override
    public Login selectOneById(Integer uId) {
        return null;
    }

    @Override
    public int deleteById(Integer uId) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "DELETE FROM t_login WHERE u_id = ?;";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ps.setInt(1, uId);

        // 执行删除操作
        int result = ps.executeUpdate();

        // 关闭流
        DBUtil.close();
        return result;
    }

    @Override
    public int addOne(Login login) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "INSERT INTO t_login(u_name,u_pwd) VALUES (?,?);";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ps.setString(1, login.getUName());
        ps.setString(2, login.getUPassword());

        // 执行添加操作
        int result = ps.executeUpdate();

        // 关闭流
        DBUtil.close();
        return result;

    }

    @Override
    public int updatePhoneAndPwd(Login login, String phoneNum) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "UPDATE t_login SET u_name = ?, u_pwd = ? WHERE u_name = ?";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ps.setString(1, login.getUName());
        ps.setString(2, login.getUPassword());
        ps.setString(3,phoneNum);
        // 执行更新操作
        int result = ps.executeUpdate();

        // 关闭流
        DBUtil.close();
        return result;
    }

    @Override
    public int updatePhone(String newPhone, String oldPhone) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "UPDATE t_login SET u_name = ? WHERE u_name = ?";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ps.setString(1, newPhone);
        ps.setString(2, oldPhone);

        // 执行更新操作
        int result = ps.executeUpdate();

        // 关闭流
        DBUtil.close();
        return result;
    }
}
