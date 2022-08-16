package com.fdcrms.service;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * author: CodingPang
 * Date: 2022/08/15 8:45
 * Description: 统计模块 业务逻辑层 接口
 * Version: V1.0
 */
public interface StatisticalService {
    /**
     * 按月为单位，统计某个家庭成员的消费总额。
     * @param memNo
     * @return
     */
    HashMap<String, Object> selectOneBillByMonth(String memNo) throws SQLException, ClassNotFoundException;

    /**
     * 按月为单位，统计整个家庭的消费总额。
     * @return
     */
    HashMap<String, Object> selectAllBillByMonth() throws SQLException, ClassNotFoundException;

    /**
     * 按月为单位，统计某种消费类型的消费总额。
     * @return
     */
    HashMap<String, Object> selectTypeBillByMonth() throws SQLException, ClassNotFoundException;

    /**
     * 按年为单位，统计某个家庭成员的消费总额。
     * @param memNo
     * @return
     */
    HashMap<String, Object> selectOneBillByYear(String memNo) throws SQLException, ClassNotFoundException;

    /**
     *  按年为单位，统计整个家庭的消费总额。
     * @return
     */
    HashMap<String, Object> selectAllBillByYear() throws SQLException, ClassNotFoundException;

    /**
     * 按年为单位，统计某种消费类型的消费总额。
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    HashMap<String, Object> selectTypeBillByYear() throws SQLException, ClassNotFoundException;
}
