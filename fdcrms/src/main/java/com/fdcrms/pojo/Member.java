package com.fdcrms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: CodingPang
 * Date: 2022/07/29 21:38
 * Description: 家庭成员实体类
 * Version: V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Integer memNo; // 家庭成员编号
    private String memName; // 家庭成员姓名
    private String memPhone; // 家庭成员手机号
    private String memGender; // 家庭成员登录密码
    private Integer memAge; // 家庭成员年龄

    public Member(String memName, String memPhone, String memGender, Integer memAge) {
        this.memName = memName;
        this.memPhone = memPhone;
        this.memGender = memGender;
        this.memAge = memAge;
    }
}
