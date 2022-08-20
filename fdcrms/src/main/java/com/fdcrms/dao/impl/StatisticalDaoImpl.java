package com.fdcrms.dao.impl;

import com.fdcrms.dao.StatisticalDao;
import com.fdcrms.pojo.Bill;
import com.fdcrms.pojo.Member;
import com.fdcrms.util.DBUtil;
import com.fdcrms.util.ModelConvert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author: CodingPang
 * Date: 2022/08/16 18:18
 * Description: 消费账单统计 数据持久层 实现类
 * Version: V1.0
 */
public class StatisticalDaoImpl implements StatisticalDao {
    @Override
    public List<Map<String, Object>> selectOneBillByMonth(Member member) throws SQLException, ClassNotFoundException {
        // 1、定义SQL
        String sql = "SELECT bill_consumer AS consumer, MONTH(bill_date) AS inMonth, SUM(bill_money) AS allConsume\n" +
                "FROM t_bill\n" +
                "WHERE bill_consumer = ? \n" +
                "GROUP BY bill_consumer,MONTH(bill_date)";
        // 2、获取预编译sql对象，并预编译
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 3、传入参数
        ps.setString(1, member.getMemName());
        // 4、执行查询操作
        ResultSet resultSet = ps.executeQuery();
        // 5、处理结果集
        // 返回结果不只有一条数据，所有需要使用List<Map<String, Object>>集合
        List<Map<String, Object>> maps = ModelConvert.convertList(resultSet);
        // 6、释放资源
        DBUtil.close();

        return maps;
    }

    @Override
    public List<Map<String, Object>> selectAllBillByMonth() throws SQLException, ClassNotFoundException {
        // 1、定义SQL
        String sql = "SELECT MONTH(bill_date) AS inMonth,SUM(IFNULL(bill_money,0)) AS allConsume\n" +
                "FROM t_bill\n" +
                "GROUP BY MONTH(bill_date)";
        // 2、获取预编译sql对象，并预编译
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 4、执行查询操作
        ResultSet resultSet = ps.executeQuery();
        // 5、处理结果集
        // 返回结果不只有一条数据，所有需要使用List<Map<String, Object>>集合
        List<Map<String, Object>> maps = ModelConvert.convertList(resultSet);
        // 6、释放资源
        DBUtil.close();

        return maps;
    }

    @Override
    public List<Map<String, Object>> selectTypeBillByMonth() throws SQLException, ClassNotFoundException {
        // 1、定义SQL
        String sql = "SELECT bill_type, MONTH(bill_date) AS inMonth,SUM(IFNULL(bill_money,0)) AS allConsume\n" +
                "FROM t_bill\n" +
                "GROUP BY bill_type, MONTH(bill_date)";
        // 2、获取预编译sql对象，并预编译
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 4、执行查询操作
        ResultSet resultSet = ps.executeQuery();
        // 5、处理结果集
        // 返回结果不只有一条数据，所有需要使用List<Map<String, Object>>集合
        List<Map<String, Object>> maps = ModelConvert.convertList(resultSet);
        // 6、释放资源
        DBUtil.close();

        return maps;
    }

    @Override
    public List<Map<String, Object>> selectOneBillByYear(Member member) throws SQLException, ClassNotFoundException {
        // 1、定义SQL
        String sql = "SELECT bill_consumer AS consumer, YEAR(bill_date) AS inYear, SUM(IFNULL(bill_money,0)) AS allConsume \n" +
                "FROM t_bill\n" +
                "WHERE bill_consumer = ? \n" +
                "GROUP BY bill_consumer,YEAR(bill_date)";
        // 2、获取预编译sql对象，并预编译
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 3、传入参数
        ps.setString(1, member.getMemName());
        // 4、执行查询操作
        ResultSet resultSet = ps.executeQuery();
        // 5、处理结果集
        // 返回结果不只有一条数据，所有需要使用List<Map<String, Object>>集合
        List<Map<String, Object>> maps = ModelConvert.convertList(resultSet);
        // 6、释放资源
        DBUtil.close();

        return maps;
    }

    @Override
    public List<Map<String, Object>> selectAllBillByYear() throws SQLException, ClassNotFoundException {
        // 1、定义SQL
        String sql = "SELECT YEAR(bill_date) AS inYear,SUM(IFNULL(bill_money,0)) AS allConsume \n" +
                "FROM t_bill\n" +
                "GROUP BY YEAR(bill_date)";
        // 2、获取预编译sql对象，并预编译
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 4、执行查询操作
        ResultSet resultSet = ps.executeQuery();
        // 5、处理结果集
        // 返回结果不只有一条数据，所有需要使用List<Map<String, Object>>集合
        List<Map<String, Object>> maps = ModelConvert.convertList(resultSet);
        // 6、释放资源
        DBUtil.close();

        return maps;
    }

    @Override
    public List<Map<String, Object>> selectTypeBillByYear() throws SQLException, ClassNotFoundException {
        // 1、定义SQL
        String sql = "SELECT bill_type,YEAR(bill_date) AS inYear,SUM(IFNULL(bill_money,0)) AS allConsume \n" +
                "FROM t_bill\n" +
                "GROUP BY bill_type,YEAR(bill_date)";
        // 2、获取预编译sql对象，并预编译
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 4、执行查询操作
        ResultSet resultSet = ps.executeQuery();
        // 5、处理结果集
        // 返回结果不只有一条数据，所有需要使用List<Map<String, Object>>集合
        List<Map<String, Object>> maps = ModelConvert.convertList(resultSet);
        // 6、释放资源
        DBUtil.close();

        return maps;
    }

    @Override
    public List<Map<String, Object>> selectOneMemAllType(Integer memNoToInt) throws SQLException, ClassNotFoundException {
        // 1、定义SQL
        String sql = "SELECT DISTINCT t.bill_type AS billType FROM t_member m,t_bill t WHERE m.mem_name = t.bill_consumer AND mem_no = ?";
        // 2、获取预编译sql对象，并预编译
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 4、传入参数
        ps.setInt(1, memNoToInt);
        // 5、执行查询操作
        ResultSet resultSet = ps.executeQuery();

        // 6、处理结果集
        // 返回结果不只有一条数据，所有需要使用List<Map<String, Object>>集合
        List<Map<String, Object>> maps = ModelConvert.convertList(resultSet);
        // 7、释放资源
        DBUtil.close();

        return maps;
    }

    @Override
    public List<Map<String, Object>> selectOneMemOneTypeByMonth(Member member, String billType) throws SQLException, ClassNotFoundException {
        // 1、定义SQL
        String sql = "SELECT bill_consumer AS consumer,bill_type AS type, MONTH(bill_date) AS inMonth,SUM(IFNULL(bill_money,0)) AS allConsume\n" +
                "FROM t_bill\n" +
                "WHERE bill_consumer = ? AND bill_type = ? \n" +
                "GROUP BY bill_consumer,bill_type,MONTH(bill_date)\n";
        // 2、获取预编译sql对象，并预编译
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 4、传入参数
        ps.setString(1, member.getMemName());
        ps.setString(2, billType);
        // 5、执行查询操作
        ResultSet resultSet = ps.executeQuery();

        // 6、处理结果集
        // 返回结果不只有一条数据，所有需要使用List<Map<String, Object>>集合
        List<Map<String, Object>> maps = ModelConvert.convertList(resultSet);
        // 7、释放资源
        DBUtil.close();

        return maps;
    }

    @Override
    public List<Map<String, Object>> selectOneMemOneTypeByYear(Member member, String billType) throws SQLException, ClassNotFoundException {
        // 1、定义SQL
        String sql = "SELECT bill_consumer,bill_type, YEAR(bill_date) AS inYear,SUM(IFNULL(bill_money,0)) AS allConsume \n" +
                "FROM t_bill\n" +
                "WHERE bill_consumer = ? AND bill_type = ? \n" +
                "GROUP BY bill_consumer,bill_type,YEAR(bill_date);";
        // 2、获取预编译sql对象，并预编译
        PreparedStatement ps = DBUtil.createPreparedStatement(sql);
        // 4、传入参数
        ps.setString(1, member.getMemName());
        ps.setString(2, billType);
        // 5、执行查询操作
        ResultSet resultSet = ps.executeQuery();

        // 6、处理结果集
        // 返回结果不只有一条数据，所有需要使用List<Map<String, Object>>集合
        List<Map<String, Object>> maps = ModelConvert.convertList(resultSet);
        // 7、释放资源
        DBUtil.close();

        return maps;
    }
}
