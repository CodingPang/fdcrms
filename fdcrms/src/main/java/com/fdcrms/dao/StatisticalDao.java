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
     * 按月为单位，统计整个家庭的消费总额。
     * 按月为单位，统计某种消费类型的消费总额。
     * 按月为单位，统计某个家庭成员某种消费类型的消费总额。
     * 按年为单位，统计某个家庭成员的消费总额。
     * 按年为单位，统计整个家庭的消费总额。
     * 按年为单位，统计某种消费类型的消费总额。
     * 按年为单位，统计某个家庭成员某种消费类型的消费总额。
     * @param member
     * @return
     */
    List<Map<String, Object>> selectOneBillByMonth(Member member) throws SQLException, ClassNotFoundException;
}
