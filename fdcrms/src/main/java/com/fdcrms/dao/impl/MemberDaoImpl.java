package com.fdcrms.dao.impl;

import com.fdcrms.dao.MemberDao;
import com.fdcrms.pojo.Bill;
import com.fdcrms.pojo.Member;
import com.fdcrms.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * author: CodingPang
 * Date: 2022/08/01 1:12
 * Description:
 * Version: V1.0
 */
public class MemberDaoImpl implements MemberDao {
    @Override
    public Member selectByPhone(String phone) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "SELECT mem_no, mem_name, mem_phone, mem_gender, mem_age FROM t_member WHERE mem_phone = ?";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ps.setString(1, phone);
        // 执行查询操作，并处理查询结果集
        ResultSet resultSet = ps.executeQuery();
        // 需要用一个List集合装所有的查询结果(Bill)
        Member member = new Member();
        if (resultSet.next()) {
            member.setMemNo(resultSet.getInt("mem_no")); // 家庭成员编号
            member.setMemName(resultSet.getString("mem_name")); // 家庭成员姓名
            member.setMemPhone(resultSet.getString("mem_phone")); // 家庭成员手机号
            member.setMemGender(resultSet.getString("mem_gender")); // 家庭成员性别
            member.setMemAge(resultSet.getInt("mem_age"));

        }

        // 关闭流
        DBUtil.close();
        return member;
    }

    @Override
    public List<Member> selectAll() throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "SELECT mem_no, mem_name, mem_phone, mem_gender, mem_age FROM t_member";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 执行查询操作，并处理查询结果集
        ResultSet resultSet = ps.executeQuery();
        // 需要用一个List集合装所有的查询结果(Bill)
        List<Member> memberList = new ArrayList<>();
        while (resultSet.next()) {
            Member member = new Member();
            member.setMemNo(resultSet.getInt("mem_no")); // 家庭成员编号
            member.setMemName(resultSet.getString("mem_name")); // 家庭成员姓名
            member.setMemPhone(resultSet.getString("mem_phone")); // 家庭成员手机号
            member.setMemGender(resultSet.getString("mem_gender")); // 家庭成员性别
            member.setMemAge(resultSet.getInt("mem_age"));

            memberList.add(member);
        }

        // 关闭流
        DBUtil.close();
        return memberList;
    }

    @Override
    public Member selectOneByName(String billConsumer) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "SELECT mem_no, mem_name, mem_phone, mem_gender, mem_age FROM t_member WHERE mem_name = ?";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ps.setString(1, billConsumer);
        // 执行查询操作，并处理查询结果集
        ResultSet resultSet = ps.executeQuery();
        // 需要用一个List集合装所有的查询结果(Bill)
        Member member = new Member();
        if (resultSet.next()) {
            member.setMemNo(resultSet.getInt("mem_no")); // 家庭成员编号
            member.setMemName(resultSet.getString("mem_name")); // 家庭成员姓名
            member.setMemPhone(resultSet.getString("mem_phone")); // 家庭成员手机号
            member.setMemGender(resultSet.getString("mem_gender")); // 家庭成员性别
            member.setMemAge(resultSet.getInt("mem_age"));

        }

        // 关闭流
        DBUtil.close();
        return member;
    }

    @Override
    public int save(Member member) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "INSERT INTO t_member(mem_name,mem_phone,mem_gender,mem_age) VALUES (?,?,?,?)";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ps.setString(1, member.getMemName());
        ps.setString(2, member.getMemPhone());
        ps.setString(3, member.getMemGender());
        ps.setInt(4, member.getMemAge());
        // 执行添加操作
        int result = ps.executeUpdate();

        // 关闭流
        DBUtil.close();
        return result;
    }

    @Override
    public Member selectById(Integer memNo) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "SELECT mem_no, mem_name, mem_phone, mem_gender, mem_age FROM t_member WHERE mem_no = ?";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ps.setInt(1, memNo);
        // 执行查询操作，并处理查询结果集
        ResultSet resultSet = ps.executeQuery();
        // 需要用一个List集合装所有的查询结果(Bill)
        Member member = new Member();
        if (resultSet.next()) {
            member.setMemNo(resultSet.getInt("mem_no")); // 家庭成员编号
            member.setMemName(resultSet.getString("mem_name")); // 家庭成员姓名
            member.setMemPhone(resultSet.getString("mem_phone")); // 家庭成员手机号
            member.setMemGender(resultSet.getString("mem_gender")); // 家庭成员性别
            member.setMemAge(resultSet.getInt("mem_age"));

        }

        // 关闭流
        DBUtil.close();
        return member;
    }

    @Override
    public int updateOneMember(Member member) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "UPDATE t_member SET mem_name = ?, mem_phone = ?, mem_gender = ?, mem_age = ? WHERE mem_no = ?";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ps.setString(1, member.getMemName());
        ps.setString(2, member.getMemPhone());
        ps.setString(3, member.getMemGender());
        ps.setInt(4, member.getMemAge());
        ps.setInt(5,member.getMemNo());
        // 执行添加操作
        int result = ps.executeUpdate();

        // 关闭流
        DBUtil.close();
        return result;
    }

 /*   @Override
    public int updateMemberPhoneNum(Member member) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "UPDATE t_member mem_phone = ?, mem_gender = ?, mem_age = ? WHERE mem_no = ?";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ps.setString(1, member.getMemPhone());
        ps.setString(2, member.getMemGender());
        ps.setInt(3, member.getMemAge());
        ps.setInt(4,member.getMemNo());
        // 执行添加操作
        int result = ps.executeUpdate();

        // 关闭流
        DBUtil.close();
        return result;
    }*/

    @Override
    public int deleteById(Integer memNo) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "DELETE FROM t_member WHERE mem_no = ?";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ps.setInt(1,memNo);
        // 执行添加操作
        int result = ps.executeUpdate();

        // 关闭流
        DBUtil.close();
        return result;
    }
}
