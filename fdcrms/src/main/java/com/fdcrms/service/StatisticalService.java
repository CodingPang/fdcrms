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
}
