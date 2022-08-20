-- 按照月为单位，统计某个家庭成员的消费总额。
SELECT bill_consumer AS consumer, MONTH(bill_date) AS inMonth, SUM(bill_money) AS allConsume
FROM t_bill
WHERE bill_consumer = '张三丰'
GROUP BY bill_consumer,MONTH(bill_date);

-- 按照月为单位，统计整个家庭的消费总额。
SELECT MONTH(bill_date) AS inMonth,SUM(IFNULL(bill_money,0)) AS allConsume
FROM t_bill
GROUP BY MONTH(bill_date);

-- 按照月为单位，统计某种消费类型的消费总额。
SELECT bill_type AS billType, MONTH(bill_date) AS inMonth,SUM(IFNULL(bill_money,0)) AS allConsume
FROM t_bill
GROUP BY bill_type, MONTH(bill_date);

# 通过家庭成员编号查找此家庭成员的所有消费类型
SELECT DISTINCT t.bill_type AS billType  FROM t_member m,t_bill t WHERE m.mem_name = t.bill_consumer AND mem_no = 1001;

-- 按月为单位，统计某个家庭成员某种消费类型的消费总额。
SELECT bill_consumer AS consumer,bill_type AS type, MONTH(bill_date) AS inMonth,SUM(IFNULL(bill_money,0)) AS allConsume
FROM t_bill
WHERE bill_consumer = '赵敏' AND bill_type = '高跟鞋'
GROUP BY bill_consumer,bill_type,MONTH(bill_date);

-- 按年为单位，统计某个家庭成员的消费总额。
SELECT bill_consumer AS consumer, YEAR(bill_date) AS inYear, SUM(IFNULL(bill_money,0)) AS allConsume
FROM t_bill
WHERE bill_consumer = '张三丰'
GROUP BY bill_consumer,YEAR(bill_date);

-- 按年为单位，统计整个家庭的消费总额。
SELECT YEAR(bill_date) AS inYear,SUM(IFNULL(bill_money,0)) AS allConsume
FROM t_bill
GROUP BY YEAR(bill_date);

-- 按年为单位，统计某种消费类型的消费总额。
SELECT bill_type,YEAR(bill_date) AS inYear,SUM(IFNULL(bill_money,0)) AS allConsume
FROM t_bill
WHERE bill_type = '高跟鞋'
GROUP BY bill_type,YEAR(bill_date);

-- 按年为单位，统计某个家庭成员某种消费类型的消费总额。
SELECT bill_consumer,bill_type, YEAR(bill_date) AS inYear,SUM(IFNULL(bill_money,0)) AS allConsume
FROM t_bill
WHERE bill_consumer = '赵敏' AND bill_type = '高跟鞋'
GROUP BY bill_consumer,bill_type,YEAR(bill_date);