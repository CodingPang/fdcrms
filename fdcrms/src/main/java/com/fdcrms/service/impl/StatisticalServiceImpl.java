package com.fdcrms.service.impl;

import com.fdcrms.dao.MemberDao;
import com.fdcrms.dao.StatisticalDao;
import com.fdcrms.dao.impl.MemberDaoImpl;
import com.fdcrms.dao.impl.StatisticalDaoImpl;
import com.fdcrms.pojo.Member;
import com.fdcrms.service.StatisticalService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: CodingPang
 * Date: 2022/08/15 8:45
 * Description: 统计模块 业务逻辑层 实现类
 * Version: V1.0
 */
public class StatisticalServiceImpl  implements StatisticalService {
    private MemberDao memberDao = new MemberDaoImpl();
    private StatisticalDao statisticalDao = new StatisticalDaoImpl();
    @Override
    public HashMap<String, Object> selectOneBillByMonth(String memNo) throws SQLException, ClassNotFoundException {
        // 1、转换参数
        Integer memNoToInt = Integer.valueOf(memNo);

        // 2、通过家庭成员编号查找此家庭成员
        Member member = memberDao.selectById(memNoToInt);

        // 3、传入查到的member对象，查询此家庭成员的月度消费账单
        List<Map<String, Object>> maps = statisticalDao.selectOneBillByMonth(member);

        // 4、封装处理结果
        HashMap<String, Object> map = new HashMap<>();
        if (maps.size() > 0) {
            map.put("code",200);
            map.put("msg","个人月消费总额查询成功");
            map.put("data",maps);
        } else {
            map.put("code",200);
            map.put("msg","个人月消费总额查询失败");
            map.put("data",null);
        }

        return map;
    }
}
