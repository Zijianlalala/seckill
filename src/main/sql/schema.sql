 -- 数据库初始化脚本

 -- 创建数据库
 CREATE DATABASE seckill;

 -- 使用数据库
 use seckill;

 -- 创建数据表
 CREATE TABLE seckill
 (
     `seckill_id`  bigint       NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
     `name`        varchar(120) NOT NULL COMMENT '商品名',
     `number`      int          NOT NULL COMMENT '库存数量',
     `create_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `start_time`  timestamp    NOT NULL COMMENT '秒杀开启时间',
     `end_time` timestamp    NOT NULL COMMENT '秒杀结束时间',

     PRIMARY KEY (seckill_id),
     key idx_start_time (start_time),
     key idx_end_time (end_time),
     key idx_create_time (create_time)
 )ENGINE =InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀数据库';

 -- 初始化数

 insert into seckill(name,number,start_time,end_time)
 values
        ('1000元秒杀iPhone xr',100,'2019-04-16 00:00:00','2019-04-17 00:00:00'),
        ('500元秒杀iPhone 8',200,'2019-04-16 00:00:00','2019-04-17 00:00:00'),
        ('100元秒杀iPad mini5',10,'2019-04-16 00:00:00','2019-04-17 00:00:00'),
        ('1元秒杀iPhone xs Max',5,'2019-04-16 00:00:00','2019-04-17 00:00:00');

 -- 秒杀成功明细表
 -- 用户登录认证相关的信息
create table success_killed(
    `seckill_id` bigint NOT NULL COMMENT '秒杀商品id',
    `user_phone` bigint not null comment '用户手机号',
    `state` tinyint not null default -1 comment '状态表示：-1：无效 0：成功 1：已付款 2：已发货 ',
    `create_time` timestamp not null comment '创建时间',
    PRIMARY KEY (seckill_id,user_phone), /* 联合主键*/
    key idx_create_time(create_time)
)ENGINE =InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

 -- 连接数据库控制台
 mysql -u root -p

 -- 为什么手写DDL
 -- 记录每次上线的DDL修改
 -- 上线 V1.1


 insert into seckill(name,number,start_time,end_time)
 values
 ('2元秒杀iPhone xs Max',5,'2019-04-20 00:00:00','2019-04-21 00:00:00');
