package com.fdcrms.service.impl;

import com.fdcrms.dao.LoginDao;
import com.fdcrms.dao.MemberDao;
import com.fdcrms.dao.impl.LoginDaoImpl;
import com.fdcrms.dao.impl.MemberDaoImpl;
import com.fdcrms.pojo.Login;
import com.fdcrms.pojo.Member;
import com.fdcrms.service.LoginService;
import com.fdcrms.util.RegexUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * author: CodingPang
 * Date: 2022/07/30 21:37
 * Description:
 * Version: V1.0
 */
public class LoginServiceImpl implements LoginService {
    private MemberDao memberDao = new MemberDaoImpl();
    private LoginDao loginDao = new LoginDaoImpl();
    @Override
    public HashMap<String, Object> login(String UName, String password) throws SQLException, ClassNotFoundException {
        HashMap<String, Object> hashMap = new HashMap<>();
        // 封装前端的用户数据到user对象
        Login user = new Login();

        LoginDao loginDao = new LoginDaoImpl();

        // 非空校验
        //判断是不是手机号的正则表达式
        // String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String ph = "^[1][34578]\\d{9}$";

        if (UName != null && !UName.isEmpty() && password != null && !password.isEmpty()) { // 如果前端的手机号参数不为空
            if (UName.matches(ph)) { // 判断是不是手机号
                user.setUName(UName);
                System.out.println("用户手机号是：" + user.getUName());
                Login login = loginDao.selectOnrByUName(UName);// 查询手机号是否存在
                if (login.getUName() != null) { // 手机号存在
                    user.setUPassword(password); // 存入密码
                    Login userByPhoneAndPwd = loginDao.selectUserByPhoneAndPwd(user);
                    if (userByPhoneAndPwd != null) {
                        MemberDaoImpl memberDao = new MemberDaoImpl();
                        Member member = memberDao.selectByPhone(UName);
                        hashMap.put("code", 200);
                        hashMap.put("msg", "登录成功");
                        hashMap.put("data", member);
                    } else {
                        hashMap.put("code", 202);
                        hashMap.put("msg", "手机号或者密码错误");
                        hashMap.put("data", null);
                    }
                } else {
                    hashMap.put("code", 202);
                    hashMap.put("msg", "用户不存在，请注册账号");
                    hashMap.put("data", null);
                }
            }
        } else {
            hashMap.put("code", 202);
            hashMap.put("msg", "用户名或密码不能为空！");
            hashMap.put("data", null);
        }
        System.out.println(hashMap);
        return hashMap;

    }

    @Override
    public HashMap<String, Object> register(String memName, String memPhone, String memGender, String memAge, String memPwd, String reMemPwd) throws SQLException, ClassNotFoundException {
        // 判断结果，并用HashMap封装结果
        HashMap<String, Object> map = new HashMap<>();

        // 0、非空校验
        if (memName == null || memName.trim().isEmpty()) { // 家庭成员姓名非空校验
            map.put("code",202);
            map.put("msg", "家庭成员姓名不能为空");
            return map;
        }

        if (memPhone == null || memPhone.trim().isEmpty()){ // 家庭成员手机号非空校验
            map.put("code",202);
            map.put("msg", "家庭成员手机号不能为空");
            return map;
        }


        if (memGender == null || memGender.trim().isEmpty()){ // 家庭成员性别非空校验
            map.put("code",202);
            map.put("msg", "家庭成员性别不能为空");
            return map;
        }


        if (memAge == null || memAge.trim().isEmpty()){ //年龄不能为空
            map.put("code",202);
            map.put("msg", "年龄不能为空");
            return map;

        }

        if (memPwd == null || memPwd.trim().isEmpty()) { // 密码不能为空
            map.put("code",202);
            map.put("msg", "密码不能为空");
            return map;

        }

        if (reMemPwd == null || reMemPwd.trim().isEmpty()) { // 再次确定密码不能为空
            map.put("code",202);
            map.put("msg", "再次确定密码不能为空");
            return map;

        }


        if (!RegexUtils.isLegalName(memPhone)){ // 姓名格式校验
            map.put("code",202);
            map.put("msg", "姓名格式不正确");
            return map;
        }

        if (!RegexUtils.isMobileExact(memPhone)){ // 手机号格式校验
            map.put("code",202);
            map.put("msg", "手机号格式不正确");
            return map;
        }

        if (!RegexUtils.isGender(memGender)){ // 性别校验
            map.put("code",202);
            map.put("msg", "性别只能为男或为女");
            return map;
        }

        if (!RegexUtils.isAge(memAge)){ // 年龄校验
            map.put("code",202);
            map.put("msg", "年龄范围只能在1到120岁");
            return map;
        }

        // 在存入前检查数据库是否已经有记录
        Member memberExists = memberDao.selectByPhone(memPhone);
        if (memberExists.getMemPhone() != null){
            map.put("code",202);
            map.put("msg", "该用户已存在，请换个手机号注册！");
            return map;
        }

        if (!RegexUtils.isStrongPwd(memPwd)){ // 密码格式校验
            map.put("code",202);
            map.put("msg", "密码必须包含大小字母与数字，不能使用特殊字符，且不低于8位");
            return map;
        }

        if (!reMemPwd.equals(memPwd)){ // 两次输入密码不一致
            map.put("code",202);
            map.put("msg", "两次输入密码不一致");
            return map;
        }

        // 1、参数类型转换
        Integer memAgeToInt = Integer.valueOf(memAge);
        // 2、将转换后的结果封装到Member与Login实体类中
        Member member = new Member(memName, memPhone, memGender, memAgeToInt);
        Login login = new Login(memPhone,memPwd);
        // 3、调用MemberDao的save方法，以及loginDao的addOne方法完成操作
        int result = memberDao.save(member);
        int loginResult = loginDao.addOne(login); // 注册账号
        if (result == 1 && loginResult == 1){ // 新增成功
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
}
