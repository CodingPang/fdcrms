package com.fdcrms.dao;

import com.fdcrms.pojo.Member;

import java.sql.SQLException;
import java.util.List;

/**
 * author: CodingPang
 * Date: 2022/08/01 1:10
 * Description:
 * Version: V1.0
 */
public interface MemberDao {
    /**
     * 通过手机号找到对应的家庭成员
     * @param phone
     * @return
     */
    Member selectByPhone(String phone) throws SQLException, ClassNotFoundException;

    /**
     * 查询全部家庭成员
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    List<Member> selectAll() throws SQLException, ClassNotFoundException;

    /**
     * 通过消费人找到家庭成员
     * @param billConsumer
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    Member selectOneByName(String billConsumer) throws SQLException, ClassNotFoundException;

    /**
     * 新增家庭成员
     * @param member
     * @return
     */
    int save(Member member) throws SQLException, ClassNotFoundException;

    /**
     * 通过家庭成员编号找到家庭成员
     * @param memNo
     * @return
     */
    Member selectById(Integer memNo) throws SQLException, ClassNotFoundException;

    /**
     * 修改家庭成员信息
     * @param member
     * @return
     */
    int updateOneMember(Member member) throws SQLException, ClassNotFoundException;

/*    *//**
     *
     * @param member
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     *//*
    int updateMemberPhoneNum(Member member) throws SQLException, ClassNotFoundException;*/

    /**
     * 通过编号删除家庭成员
     * @param memNo
     * @return
     */
    int deleteById(Integer memNo) throws SQLException, ClassNotFoundException;
}
