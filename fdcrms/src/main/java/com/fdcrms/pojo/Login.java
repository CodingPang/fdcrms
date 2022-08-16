package com.fdcrms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: CodingPang
 * Date: 2022/07/29 22:25
 * Description: 系统登录类
 * Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    private Integer uid; // 登录表的id
    private String uName; // 登录名(采用手机号)
    private String uPassword; // 登录密码

    // 登录的用户是哪位家庭成员
    //private Member member;

    public Login(String uName, String uPassword) {
        this.uName = uName;
        this.uPassword = uPassword;
    }
}
