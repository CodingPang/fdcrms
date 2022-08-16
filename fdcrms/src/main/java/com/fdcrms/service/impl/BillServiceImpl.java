package com.fdcrms.service.impl;

import com.fdcrms.dao.BillDao;
import com.fdcrms.dao.LoginDao;
import com.fdcrms.dao.MemberDao;
import com.fdcrms.dao.impl.BillDaoImpl;
import com.fdcrms.dao.impl.LoginDaoImpl;
import com.fdcrms.dao.impl.MemberDaoImpl;
import com.fdcrms.pojo.Bill;
import com.fdcrms.pojo.Member;
import com.fdcrms.service.BillService;
import com.fdcrms.util.RegexUtils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * author: CodingPang
 * Date: 2022/07/30 0:18
 * Description:
 * Version: V1.0
 */
public class BillServiceImpl implements BillService {
    private BillDao billDao = new BillDaoImpl();
    private MemberDao memberDao = new MemberDaoImpl();
    @Override
    public HashMap<String, Object> selectAll() throws SQLException, ClassNotFoundException {
        // 1、调用对应的数据持久层方法 - selectAll()
        List<Bill> billList = billDao.selectAll();

        // 2、将结果封装进HashMap
        HashMap<String, Object> map = new HashMap<>();
        if (billList.size() != 0) { // 查询成功
            //map.put("code", 200); // 状态码
            //map.put("msg", "所有账单记录查询成功"); // 消息
            map.put("data", billList); // 数据
        } else { // 查询出错，系统异常，请等待系统管理员修复！
            //map.put("code", 500); // 状态码
            //map.put("msg", "内部服务器错误"); // 消息
            map.put("data", null); // 数据
        }
        System.out.println(map.toString());
        return map;
    }

    @Override
    public HashMap<String, Object> save(String billDate, String billType, String billMoney, String billConsumer) throws SQLException, ClassNotFoundException {
        // 判断结果，并用HashMap封装结果
        HashMap<String, Object> map = new HashMap<>();

        // 0、非空校验
        if (billDate == null || billDate.trim().isEmpty()) { // 账单日期非空校验
            map.put("code",202);
            map.put("msg", "账单日期不能为空");
            return map;
        }

        if (billType == null || billType.trim().isEmpty()){ // 消费类型非空校验
            map.put("code",202);
            map.put("msg", "消费类型不能为空");
            return map;
        }

        if (billMoney == null || billMoney.trim().isEmpty()){ // 消费类型非空校验
            map.put("code",202);
            map.put("msg", "消费金额不能为空");
            return map;
        }


        if (!RegexUtils.isMoney(billMoney)){
            map.put("code",202);
            map.put("msg", "消费金额格式不正确");
            return map;
        }

        if (billConsumer == null || billConsumer.trim().isEmpty()){ // 消费人不能为空
            map.put("code",202);
            map.put("msg", "消费人不能为空");
            return map;

        }

        Member member = memberDao.selectOneByName(billConsumer);
        if (!billConsumer.equals(member.getMemName())){
            // 消费人不在家庭成员中
            map.put("code",202);
            map.put("msg", "家庭成员查无此人，请先添加此家庭成员");
            return map;
        }


        // 1、参数类型转换
        // Integer billNoToInt = Integer.valueOf(billNo); // 账单编号

        // 时间格式转换
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 转换后的时间结果
        String dateResult = null;
        try {
            Date time = format.parse(billDate);
            dateResult = defaultFormat.format(time);
            System.out.println(dateResult);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Timestamp billDateTo = Timestamp.valueOf(dateResult); // 账单时间
        BigDecimal billMoneyToDecimal = new BigDecimal(billMoney); // 消费金额

        // 2、将转换后的结果封装到Bill实体类中
        Bill bill = new Bill(billDateTo, billType, billMoneyToDecimal, billConsumer);

        // 调用BillDao的save方法完成操作
        int result = billDao.insertOneBill(bill);


        if (result != 0) {
            map.put("code",200);
            map.put("msg", "新增账单记录成功");
        } else {
            map.put("code",202);
            map.put("msg", "新增账单出错啦，请联系管理员！");
        }


        return map;
    }

    @Override
    public HashMap<String, Object> selectBillById(String billNo) throws SQLException, ClassNotFoundException {
        // 转换billNo数据类型
        Integer billNoToInt = Integer.valueOf(billNo);
        // 调用BillDao的方法查询对应的账单记录
        Bill bill = billDao.selectById(billNoToInt);
        // HashMap封装返回结果
        HashMap<String,Object> map = new HashMap<>();
        if (bill != null) { // 查询成功
            //map.put("code", 200); // 状态码
            //map.put("msg", "所有账单记录查询成功"); // 消息
            map.put("data", bill); // 数据
        } else { // 查询出错，系统异常，请等待系统管理员修复！
            //map.put("code", 500); // 状态码
            //map.put("msg", "内部服务器错误"); // 消息
            map.put("data", null); // 数据
        }
        return map;
    }

    @Override
    public HashMap<String, Object> editBill(String billNo,String billDate, String billType, String billMoney, String billConsumer) throws SQLException, ClassNotFoundException {
        // 4、利用HashMap封装返回结果
        HashMap<String,Object> map = new HashMap<>();

        // 0、非空校验
        if (billDate == null || billDate.trim().isEmpty()) { // 账单日期非空校验
            map.put("code",202);
            map.put("msg", "账单日期不能为空");
            return map;
        }

        if (billType == null || billType.trim().isEmpty()){ // 消费类型非空校验
            map.put("code",202);
            map.put("msg", "消费类型不能为空");
            return map;
        }

        if (billMoney == null || billMoney.trim().isEmpty()){ // 消费类型非空校验
            map.put("code",202);
            map.put("msg", "消费金额不能为空");
            return map;
        }

        if (!RegexUtils.isMoney(billMoney)){
            map.put("code",202);
            map.put("msg", "消费金额格式不正确");
            return map;
        }

        if (billConsumer == null || billConsumer.trim().isEmpty()){ // 消费人不能为空
            map.put("code",202);
            map.put("msg", "消费人不能为空");
            return map;

        }

        Member member = memberDao.selectOneByName(billConsumer);
        if (!billConsumer.equals(member.getMemName())){
            // 消费人不在家庭成员中
            map.put("code",202);
            map.put("msg", "家庭成员查无此人，请先添加此家庭成员");
            return map;
        }
        // 1、参数类型转换
         Integer billNoToInt = Integer.valueOf(billNo); // 账单编号
        // 时间格式转换
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 转换后的时间结果
        String dateResult = null;
        try {
            Date time = format.parse(billDate);
            dateResult = defaultFormat.format(time);
            System.out.println(dateResult);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Timestamp billDateTo = Timestamp.valueOf(dateResult); // 账单时间
        BigDecimal billMoneyToDecimal = new BigDecimal(billMoney); // 消费金额

        // 2、将转换后的结果封装到Bill实体类中
        Bill bill = new Bill(billNoToInt,billDateTo, billType, billMoneyToDecimal, billConsumer);

        // 3、调用BillDao的save方法完成操作
        int result = billDao.updateOneBill(bill);


        if (result == 1){
            map.put("code",200);
            map.put("msg", "修改账单记录成功");
        } else {
            map.put("code",202);
            map.put("msg", "修改账单出错啦，亲，未知异常，请联系管理人员");
        }

        return map;
    }

    @Override
    public HashMap<String, Object> delBill(String billNo) throws SQLException, ClassNotFoundException {
        // 1、转换参数
        Integer billNoInt = Integer.valueOf(billNo);
        // 2、调用BillDao完成数据删除
        int result = billDao.deleteById(billNoInt);
        // 3、将执行结果封装进HashMap
        HashMap<String, Object> map = new HashMap<>();

            if (result == 1){
                map.put("code",200);
                map.put("msg", "删除账单记录成功");
            } else {
                map.put("code",202);
                map.put("msg", "删除账单出错啦，亲，未知异常，请联系管理人员");
            }


        return map;
    }
}
