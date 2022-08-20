package com.fdcrms.dao;

import com.fdcrms.pojo.Bill;
import com.fdcrms.pojo.Member;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * author: CodingPang
 * Date: 2022/08/16 18:12
 * Description: 消费账单统计 数据持久层
 * Version: V1.0
 */
public interface StatisticalDao {

    /**
     * 按月为单位，统计某个家庭成员的消费总额。
     *
     * @param member
     * @return
     */
    List<Map<String, Object>> selectOneBillByMonth(Member member) throws SQLException, ClassNotFoundException;

    /**
     * 按月为单位，统计整个家庭的消费总额。
     *
     * @return
     */
    List<Map<String, Object>> selectAllBillByMonth() throws SQLException, ClassNotFoundException;

    /**
     * 按月为单位，统计某种消费类型的消费总额。
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    List<Map<String, Object>> selectTypeBillByMonth() throws SQLException, ClassNotFoundException;

    ;

    /**
     * 按年为单位，统计某个家庭成员的消费总额。
     *
     * @param member
     * @return
     */
    List<Map<String, Object>> selectOneBillByYear(Member member) throws SQLException, ClassNotFoundException;

    /**
     * 按年为单位，统计整个家庭的消费总额。
     *
     * @return
     */
    List<Map<String, Object>> selectAllBillByYear() throws SQLException, ClassNotFoundException;

    /**
     * 按年为单位，统计某种消费类型的消费总额。
     *
     * @return
     */
    List<Map<String, Object>> selectTypeBillByYear() throws SQLException, ClassNotFoundException;

    /**
     * 通过家庭成员编号查找此家庭成员的所有消费类型
     *
     * @param memNoToInt 家庭成员编号
     * @return
     */
    List<Map<String, Object>> selectOneMemAllType(Integer memNoToInt) throws SQLException, ClassNotFoundException;

    /**
     * 按月为单位，统计某个家庭成员某种消费类型的消费总额。
     *
     * @param member
     * @param billType
     * @return
     */
    List<Map<String, Object>> selectOneMemOneTypeByMonth(Member member, String billType) throws SQLException, ClassNotFoundException;

    /**
     * 按年为单位，统计某个家庭成员某种消费类型的消费总额。
     *
     * @param member
     * @param billType
     * @return
     */
    List<Map<String, Object>> selectOneMemOneTypeByYear(Member member, String billType) throws SQLException, ClassNotFoundException;
}
