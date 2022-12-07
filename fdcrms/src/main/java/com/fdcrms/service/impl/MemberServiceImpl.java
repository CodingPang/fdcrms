package com.fdcrms.service.impl;

import com.fdcrms.dao.BillDao;
import com.fdcrms.dao.LoginDao;
import com.fdcrms.dao.MemberDao;
import com.fdcrms.dao.impl.BillDaoImpl;
import com.fdcrms.dao.impl.LoginDaoImpl;
import com.fdcrms.dao.impl.MemberDaoImpl;
import com.fdcrms.pojo.Bill;
import com.fdcrms.pojo.Login;
import com.fdcrms.pojo.Member;
import com.fdcrms.service.MemberService;
import com.fdcrms.util.RegexUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * author: CodingPang
 * Date: 2022/08/02 23:34
 * Description:
 * Version: V1.0
 */
public class MemberServiceImpl implements MemberService {
    private MemberDao memberDao = new MemberDaoImpl();
    private LoginDao loginDao = new LoginDaoImpl();

    @Override
    public HashMap<String, Object> selectAll() throws SQLException, ClassNotFoundException {
        // 1、通过memberDao.selectAll()查询所有的家庭成员
        List<Member> memberList = memberDao.selectAll();
        // 2、通过memberList封装HashMap
        HashMap<String, Object> map = new HashMap<>();
        if (memberList.size() != 0) {
            map.put("code", 200);
            map.put("msg", "所有家庭成员信息查询成功");
            map.put("data", memberList);
        } else {
            map.put("code", 202);
            map.put("msg", "暂无家庭成员哦，请添加！");
            map.put("data", null);

        }
        return map;
    }

    @Override
    public HashMap<String, Object> addOneMem(String memName, String memPhone, String memGender, String memAge, String memPwd, String reMemPwd) throws SQLException, ClassNotFoundException {
        // 判断结果，并用HashMap封装结果
        HashMap<String, Object> map = new HashMap<>();

        // 0、非空校验
        if (memName == null || memName.trim().isEmpty()) { // 家庭成员姓名非空校验
            map.put("code", 202);
            map.put("msg", "家庭成员姓名不能为空");
            return map;
        }

        if (memPhone == null || memPhone.trim().isEmpty()) { // 家庭成员手机号非空校验
            map.put("code", 202);
            map.put("msg", "家庭成员手机号不能为空");
            return map;
        }


        if (memGender == null || memGender.trim().isEmpty()) { // 家庭成员性别非空校验
            map.put("code", 202);
            map.put("msg", "家庭成员性别不能为空");
            return map;
        }


        if (memAge == null || memAge.trim().isEmpty()) { //年龄不能为空
            map.put("code", 202);
            map.put("msg", "年龄不能为空");
            return map;

        }

        if (memPwd == null || memPwd.trim().isEmpty()) { // 密码不能为空
            map.put("code", 202);
            map.put("msg", "密码不能为空");
            return map;

        }

        if (reMemPwd == null || reMemPwd.trim().isEmpty()) { // 再次确定密码不能为空
            map.put("code", 202);
            map.put("msg", "再次确定密码不能为空");
            return map;

        }


        if (RegexUtils.isLegalName(memName)) { // 姓名格式校验
            map.put("code", 202);
            map.put("msg", "姓名格式不正确");
            return map;
        }

        if (!RegexUtils.isMobileExact(memPhone)) { // 手机号格式校验
            map.put("code", 202);
            map.put("msg", "手机号格式不正确");
            return map;
        }

        if (!RegexUtils.isGender(memGender)) { // 性别校验
            map.put("code", 202);
            map.put("msg", "性别只能为男或为女");
            return map;
        }

        if (!RegexUtils.isAge(memAge)) { // 年龄校验
            map.put("code", 202);
            map.put("msg", "年龄范围只能在1到120岁");
            return map;
        }

        // 在存入前检查数据库是否已经有记录
        Member memberExists = memberDao.selectByPhone(memPhone);
        if (memberExists.getMemPhone() != null) {
            map.put("code", 202);
            map.put("msg", "该用户已存在，请换个手机号注册！");
            return map;
        }

        if (!RegexUtils.isStrongPwd(memPwd)) { // 密码格式校验
            map.put("code", 202);
            map.put("msg", "密码必须包含大小字母与数字，不能使用特殊字符，且不低于8位");
            return map;
        }

        if (!reMemPwd.equals(memPwd)) { // 两次输入密码不一致
            map.put("code", 202);
            map.put("msg", "两次输入密码不一致");
            return map;
        }

        // 1、参数类型转换
        Integer memAgeToInt = Integer.valueOf(memAge);
        // 2、将转换后的结果封装到Member与Login实体类中
        Member member = new Member(memName, memPhone, memGender, memAgeToInt);
        Login login = new Login(memPhone, memPwd);
        // 3、调用MemberDao的save方法，以及loginDao的addOne方法完成操作
        int result = memberDao.save(member);
        int loginResult = loginDao.addOne(login); // 注册账号
        if (result == 1 && loginResult == 1) { // 新增成功
            map.put("code", 200);
            map.put("msg", "家庭成员新增成功");

            List<Member> memberList = memberDao.selectAll();
            map.put("data", memberList);
        } else {
            map.put("code", 202);
            map.put("msg", "家庭成员新增失败，系统异常！");
            map.put("data", null);
        }
        return map;
    }

    @Override
    public HashMap<String, Object> selectMemberById(String memNo) throws SQLException, ClassNotFoundException {
        // 转换memNo数据类型
        Integer memNoToInt = Integer.valueOf(memNo);
        // 调用memberDao的方法查询对应的家庭成员信息
        Member member = memberDao.selectById(memNoToInt);
        // HashMap封装返回结果
        HashMap<String, Object> map = new HashMap<>();
        if (member != null) {// 查询成功
            //map.put("code", 200); // 状态码
            //map.put("msg", "所有账单记录查询成功"); // 消息
            map.put("data", member); // 数据

        } else {
            // 查询出错，系统异常，请等待系统管理员修复！
            //map.put("code", 500); // 状态码
            //map.put("msg", "内部服务器错误"); // 消息
            map.put("data", null); // 数据
        }
        return map;
    }

    @Override
    public HashMap<String, Object> editMember(String memNo, String memName, String memPhone, String memGender, String memAge) throws SQLException, ClassNotFoundException {
        // 4、利用HashMap封装返回结果
        HashMap<String, Object> map = new HashMap<>();
        // 1、参数类型转换
        Integer memNoToInt = Integer.valueOf(memNo);


        // 0、非空校验
        if (memName == null || memName.trim().isEmpty()) { // 家庭成员姓名非空校验
            map.put("code", 202);
            map.put("msg", "家庭成员姓名不能为空");
            map.put("data", memberDao.selectById(memNoToInt));
            return map;
        }

        if (memPhone == null || memPhone.trim().isEmpty()) { // 家庭成员手机号非空校验
            map.put("code", 202);
            map.put("msg", "家庭成员手机号不能为空");
            map.put("data", memberDao.selectById(memNoToInt));
            return map;
        }


        if (memGender == null || memGender.trim().isEmpty()) { // 家庭成员性别非空校验
            map.put("code", 202);
            map.put("msg", "家庭成员性别不能为空");
            map.put("data", memberDao.selectById(memNoToInt));
            return map;
        }


        if (memAge == null || memAge.trim().isEmpty()) { //年龄不能为空
            map.put("code", 202);
            map.put("msg", "年龄不能为空");
            map.put("data", memberDao.selectById(memNoToInt));
            return map;

        }


        if (!RegexUtils.isLegalName(memName)) { // 姓名格式校验
            map.put("code", 202);
            map.put("msg", "姓名格式不正确");
            map.put("data", memberDao.selectById(memNoToInt));
            return map;
        }

        if (!RegexUtils.isMobileExact(memPhone)) { // 手机号格式校验
            map.put("code", 202);
            map.put("msg", "手机号格式不正确");
            map.put("data", memberDao.selectById(memNoToInt));
            return map;
        }

        if (!RegexUtils.isGender(memGender)) { // 性别校验
            map.put("code", 202);
            map.put("msg", "性别只能为男或为女");
            map.put("data", memberDao.selectById(memNoToInt));
            return map;
        }

        if (!RegexUtils.isAge(memAge)) { // 年龄校验
            map.put("code", 202);
            map.put("msg", "年龄范围只能在1到120岁");
            map.put("data", memberDao.selectById(memNoToInt));
            return map;
        }

        Integer memAgeToInt = Integer.valueOf(memAge);
        // 2、将转换后的结果封装到Member实体类中
        Member member = new Member(memNoToInt, memName, memPhone, memGender, memAgeToInt);

        int result = memberDao.updateOneMember(member);

        if (result == 1) {
            map.put("code", 200);
            map.put("msg", "家庭成员信息变更成功，登录手机号现为：" + memPhone);

        } else {
            map.put("code", 202);
            map.put("msg", "家庭成员信息变更失败");
            map.put("data", memberDao.selectById(memNoToInt));
        }

        return map;
    }

    @Override
    public HashMap<String, Object> delMember(String memNo) throws SQLException, ClassNotFoundException {
        // 1、转换参数
        Integer memNoToInt = Integer.valueOf(memNo);
        // 2、删除对应家庭成员的消费账单记录与账号记录后，调用memberDao完成数据删除家庭成员
        // 2.1、通过家庭成员编号查用户名
        Member member = memberDao.selectById(memNoToInt);
        String memName = member.getMemName(); // 成员名字
        String loginName = member.getMemPhone(); // 获取登录名

        BillDao billDao = new BillDaoImpl();
        List<Bill> bills = billDao.selectBillsByConsumer(member);

        int delBillsCounts = 0; // 统计总共被删除了多少条账单
        for (int i = 0; i < bills.size(); i++) {
            billDao.deleteById(bills.get(i).getBillNo());
            delBillsCounts += 1;
        }
        System.out.println(memName + "被删除消费订单：" + delBillsCounts + "条");

        LoginDao loginDao = new LoginDaoImpl();
        int deleteToLogin = loginDao.deleteById(loginDao.selectOnrByUName(loginName).getUid());// 通过手机号查找到登录表中的id，然后删除登录表中的手机号与密码
        System.out.println(memName + "被删除登录账户：" + deleteToLogin + "条");

        int result = memberDao.deleteById(memNoToInt); // 删除家庭成员表中对应的记录
        // 3、将执行结果封装进HashMap
        HashMap<String, Object> map = new HashMap<>();
        if (bills.size() == delBillsCounts && deleteToLogin == 1 && result == 1){
            map.put("code",200);
            map.put("msg", "删除家庭成员记录成功");
        } else {
            map.put("code",202);
            map.put("msg", "删除家庭成员出错啦，亲，未知异常，请联系管理人员");
        }

        /*int result = memberDao.deleteById(memNoToInt);

        HashMap<String, Object> map = new HashMap<>();
        if (result == 1) {
            map.put("code", 200);
            map.put("msg", memberDao.selectById(memNoToInt).getMemName() + "的家庭成员信息删除成功");

        } else {
            map.put("code", 202);
            map.put("msg", "家庭成员信息删除失败");
        }*/

        return map;
    }
}
