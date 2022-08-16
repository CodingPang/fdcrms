package com.fdcrms.dao.impl;

import com.fdcrms.dao.BillDao;
import com.fdcrms.pojo.Bill;
import com.fdcrms.pojo.Member;
import com.fdcrms.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * author: CodingPang
 * Date: 2022/07/29 22:29
 * Description: 消费账单 数据持久层 实现类
 * Version: V1.0
 */
public class BillDaoImpl implements BillDao {
    @Override
    public List<Bill> selectAll() throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "SELECT BILL_NO, BILL_DATE, BILL_TYPE, BILL_MONEY, BILL_CONSUMER FROM t_bill";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 执行查询操作，并处理查询结果集
        ResultSet resultSet = ps.executeQuery();
        // 需要用一个List集合装所有的查询结果(Bill)
        List<Bill> billList = new ArrayList<>();
        while (resultSet.next()) {
            Bill bill = new Bill();
            bill.setBillNo(resultSet.getInt("bill_no")); // 账单编号
            bill.setBillDate(resultSet.getTimestamp("bill_date")); // 账单时间
            bill.setBillType(resultSet.getString("bill_type")); // 账单类型
            bill.setBillMoney(resultSet.getBigDecimal("bill_money")); // 消费金额
            bill.setBillConsumer(resultSet.getString("bill_consumer")); // 消费人
            billList.add(bill);
        }

        // 关闭流
        DBUtil.close();
        return billList;
    }

    @Override
    public Bill selectById(Integer billNo) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "SELECT BILL_NO, BILL_DATE, BILL_TYPE, BILL_MONEY, BILL_CONSUMER FROM t_bill WHERE BILL_NO = ?";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 传入参数
        ps.setInt(1, billNo);
        // 执行查询操作，并处理查询结果集
        ResultSet resultSet = ps.executeQuery();

        Bill bill = null;
        if (resultSet.next()) {
            bill = new Bill();
            bill.setBillNo(resultSet.getInt("bill_no")); // 账单编号
            bill.setBillDate(resultSet.getTimestamp("bill_date")); // 账单时间
            bill.setBillType(resultSet.getString("bill_type")); // 账单类型
            bill.setBillMoney(resultSet.getBigDecimal("bill_money")); // 消费金额
            bill.setBillConsumer(resultSet.getString("bill_consumer")); // 消费人
        }

        // 关闭流
        DBUtil.close();
        return bill;
    }

    @Override
    public int deleteById(Integer billNo) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "DELETE FROM t_bill WHERE bill_no = ?";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 传入参数
        ps.setInt(1, billNo);

        // 执行查询操作，并处理查询结果集
        int result = ps.executeUpdate();
        // 需要用一个List集合装所有的查询结果(Bill)

        // 关闭流
        DBUtil.close();

        return result;
    }

    @Override
    public int insertOneBill(Bill bill) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "INSERT INTO t_bill(bill_date,bill_type,bill_money,bill_consumer) VALUES (?,?,?,?)";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 传入参数
        ps.setTimestamp(1, Timestamp.valueOf(bill.getBillDate().toLocalDateTime()));
        ps.setString(2, bill.getBillType());
        ps.setBigDecimal(3, bill.getBillMoney());
        ps.setString(4, bill.getBillConsumer());

        // 执行查询操作，并处理查询结果集
        int result = ps.executeUpdate();
        // 需要用一个List集合装所有的查询结果(Bill)

        // 关闭流
        DBUtil.close();

        return result;
    }

    @Override
    public int updateOneBill(Bill bill) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "UPDATE t_bill SET bill_date = ?, bill_type = ?, bill_money = ?, bill_consumer = ? WHERE bill_no = ?";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 传入参数
        ps.setTimestamp(1, Timestamp.valueOf(bill.getBillDate().toLocalDateTime()));
        ps.setString(2, bill.getBillType());
        ps.setBigDecimal(3, bill.getBillMoney());
        ps.setString(4, bill.getBillConsumer());
        ps.setInt(5, bill.getBillNo());

        // 执行查询操作，并处理查询结果集
        int result = ps.executeUpdate();
        // 需要用一个List集合装所有的查询结果(Bill)

        // 关闭流
        DBUtil.close();

        return result;
    }

    @Override
    public List<Bill> selectBillsByConsumer(Member member) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "SELECT BILL_NO, BILL_DATE, BILL_TYPE, BILL_MONEY, BILL_CONSUMER FROM t_bill WHERE BILL_CONSUMER = ?";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ps.setString(1, member.getMemName());
        // 执行查询操作，并处理查询结果集
        ResultSet resultSet = ps.executeQuery();
        // 需要用一个List集合装所有的查询结果(Bill)
        List<Bill> billList = new ArrayList<>();
        while (resultSet.next()) {
            Bill bill = new Bill();
            bill.setBillNo(resultSet.getInt("bill_no")); // 账单编号
            bill.setBillDate(resultSet.getTimestamp("bill_date")); // 账单时间
            bill.setBillType(resultSet.getString("bill_type")); // 账单类型
            bill.setBillMoney(resultSet.getBigDecimal("bill_money")); // 消费金额
            bill.setBillConsumer(resultSet.getString("bill_consumer")); // 消费人
            billList.add(bill);
        }

        // 关闭流
        DBUtil.close();
        return billList;
    }

    @Override
    public List<Bill> selectOneBillByMouth(Bill bill) throws SQLException, ClassNotFoundException {
        // SQL语句
        String sql = "SELECT bill_consumer, MONTH(bill_date),SUM(bill_money)\n" +
                "FROM t_bill\n" +
                "WHERE bill_consumer = ? \n" +
                "GROUP BY bill_consumer,MONTH(bill_date)";
        // 获取预编译对象PreparedStatement，并预编译sql语句
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        ps.setString(1, bill.getBillConsumer());
        // 执行查询操作，并处理查询结果集
        ResultSet resultSet = ps.executeQuery();
        // 需要用一个List集合装所有的查询结果(Bill)
        List<Bill> billList = new ArrayList<>();
        while (resultSet.next()) {
            Bill billResult = new Bill();
            billResult.setBillDate(resultSet.getTimestamp("bill_date")); // 账单时间
            bill.setBillMoney(resultSet.getBigDecimal("bill_money")); // 消费金额
            billResult.setBillConsumer(resultSet.getString("bill_consumer")); // 消费人
            billList.add(bill);
        }

        // 关闭流
        DBUtil.close();
        return billList;
    }
}
