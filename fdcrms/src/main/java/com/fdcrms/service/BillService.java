package com.fdcrms.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * author: CodingPang
 * Date: 2022/07/30 0:15
 * Description: 消费账单 业务逻辑层 接口
 * Version: V1.0
 */
public interface BillService {
    /**
     * 查询所有的账单记录
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    HashMap<String,Object> selectAll() throws SQLException, ClassNotFoundException;

    /**
     * 新增账单
     * @param billDate 账单日期
     * @param billType 账单类型
     * @param billMoney 消费金额
     * @param billConsumer 消费人
     * @return 处理结果
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    HashMap<String,Object> save(String billDate, String billType, String billMoney, String billConsumer) throws SQLException, ClassNotFoundException;

    /**
     * 根据账单编号获取账单信息
     * @param billNo 账单编号
     * @return
     */
    HashMap<String,Object>  selectBillById(String billNo) throws SQLException, ClassNotFoundException;

    /**
     * 修改消费账单
     * @param billNo 账单编号
     * @param billDate 账单日期
     * @param billType 账单类型
     * @param billMoney 消费金额
     * @param billConsumer 消费人
     * @return
     */
    HashMap<String,Object> editBill(String billNo, String billDate, String billType, String billMoney, String billConsumer) throws SQLException, ClassNotFoundException;

    /**
     * 删除消费账单
     * @param billNo
     * @return
     */
    HashMap<String,Object> delBill(String billNo) throws SQLException, ClassNotFoundException;

    // HashMap<String,Object> selectOneBillByMouth(String bi) throws SQLException, ClassNotFoundException;
}
