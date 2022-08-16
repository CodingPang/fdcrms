package com.fdcrms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * author: CodingPang
 * Date: 2022/07/29 21:31
 * Description: 账单表
 * Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    private Integer billNo; // 账单编号
    private Timestamp billDate; // 账单日期
    private String billType; // 消费类型
    private BigDecimal billMoney; // 消费金额
    private String billConsumer; // 消费人

    // 当前订单属于哪个家庭成员
    // private Member member; // 家庭成员

    public Bill(Timestamp billDate, String billType, BigDecimal billMoney, String billConsumer) {
        this.billDate = billDate;
        this.billType = billType;
        this.billMoney = billMoney;
        this.billConsumer = billConsumer;
    }
}
