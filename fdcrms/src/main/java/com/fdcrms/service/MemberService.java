package com.fdcrms.service;

import com.fdcrms.pojo.Member;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * author: CodingPang
 * Date: 2022/08/02 23:34
 * Description:
 * Version: V1.0
 */
public interface MemberService {
    /**
     * 查询所有的家庭成员
     * @return
     */
    HashMap<String,Object> selectAll() throws SQLException, ClassNotFoundException;

    /**
     * 新增家庭成员
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
    HashMap<String, Object> addOneMem(String memName, String memPhone, String memGender, String memAge, String memPwd, String reMemPwd) throws SQLException, ClassNotFoundException;

    /**
     * 根据家庭成员编号获取家庭成员信息
     * @param memNo
     * @return
     */
    HashMap<String,Object> selectMemberById(String memNo) throws SQLException, ClassNotFoundException;

    /**
     * 修改家庭成员的信息
     * @param memNo
     * @param memName
     * @param memPhone
     * @param memGender
     * @param memAge
     * @return
     */
    HashMap<String, Object> editMember(String memNo, String memName, String memPhone, String memGender, String memAge) throws SQLException, ClassNotFoundException;

    /**
     * 通过家庭成员编号删除家庭成员
     * @param memNo
     * @return
     */
    HashMap<String, Object> delMember(String memNo) throws SQLException, ClassNotFoundException;
}
