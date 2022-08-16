package com.fdcrms.dao;

import com.fdcrms.pojo.Bill;
import com.fdcrms.pojo.Member;

import java.sql.SQLException;
import java.util.List;

/**
 * author: CodingPang
 * Date: 2022/07/29 22:29
 * Description: 消费账单 数据持久层 接口
 * Version: V1.0
 */
public interface BillDao {
        List<Bill> selectAll() throws SQLException, ClassNotFoundException;

        /**
         * 通过主键查询单条账单记录
         * @param billNo
         * @return
         */
        Bill selectById(Integer billNo) throws SQLException, ClassNotFoundException;


        /**
         * 通过主键删除单条账单记录
         * @param billNo
         * @return
         */
        int deleteById(Integer billNo) throws SQLException, ClassNotFoundException;

        /**
         * 插入一条账单记录
         * @param bill
         * @return
         */
        int insertOneBill(Bill bill) throws SQLException, ClassNotFoundException;

        /**
         * 更新单条账单记录
          * @param bill
         * @return
         */
        int updateOneBill(Bill bill) throws SQLException, ClassNotFoundException;

       List<Bill> selectBillsByConsumer(Member member) throws SQLException, ClassNotFoundException;

        /**
         * 按照月为单位，统计某个家庭成员的消费总额。
         */
        List<Bill> selectOneBillByMouth(Bill bill) throws SQLException, ClassNotFoundException;
}
