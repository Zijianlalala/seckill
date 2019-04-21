package org.seckill.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void getById() {
        Seckill seckill = seckillService.getById(1000l);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void exportSeckillUrl() {
        long id=1004;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);
        /**
         * 17:01:56.330 [main] INFO  o.s.s.impl.SeckillServiceImplTest -
         * exposer=Exposer{exposed=true, md5='b07909a4d9b2498c6a92fa7adb9a5aa6',
         * seckillId=1004, now=0, start=0, end=0}
         */
    }

    //集成测试代码完整逻辑，注意可重复执行
    @Test
    public void excuteSeckill() {
        long id = 1004;
        long phoneNumber=18853855263L;
        String md5 = "b07909a4d9b2498c6a92fa7adb9a5aa6";
        SeckillExcution seckillExcution =
                seckillService.excuteSeckill(id, phoneNumber, md5);
        logger.info("result={}", seckillExcution);
        //org.seckill.exception.SeckillException: seckill data rewrite
        /**
         * 17:05:24.979 [main] INFO  o.s.s.impl.SeckillServiceImplTest -
         * result=org.seckill.dto.SeckillExcution@17d2ed1b
         * 17:05:24.853 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@aa004a0 [wrapping: com.mysql.jdbc.JDBC4Connection@4c98a6d5]] will be managed by Spring
         * 17:05:24.857 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==>  Preparing: -- 具体的sql语句 update seckill set number = number - 1 where seckill_id = ? and start_time <= ? and end_time >= ? and number > 0;
         * 17:05:24.900 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==> Parameters: 1004(Long), 2019-04-20 17:05:24.822(Timestamp), 2019-04-20 17:05:24.822(Timestamp)
         * 17:05:24.909 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - <==    Updates: 1
         * 17:05:24.910 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@1ecfcbc9]
         * 17:05:24.910 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@1ecfcbc9] from current transaction
         * 17:05:24.910 [main] DEBUG o.s.d.S.insertSuccessKilled - ==>  Preparing: -- 主键冲突，报错 insert ignore into success_killed(seckill_id,user_phone) values(?, ?)
         * 17:05:24.912 [main] DEBUG o.s.d.S.insertSuccessKilled - ==> Parameters: 1004(Long), 18853855263(Long)
         * 17:05:24.920 [main] DEBUG o.s.d.S.insertSuccessKilled - <==    Updates: 1
         * 17:05:24.935 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@1ecfcbc9]
         * 17:05:24.935 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@1ecfcbc9] from current transaction
         * 17:05:24.940 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==>  Preparing: -- 根据id查询SuccessKilled并携带Seckill实体 -- 如何告诉MyBatis把结果映射到SuccessKilled同时映射seckill属性 （将一个实体映射到另一个实体当中） -- MyBatis特点：可以自由控制SQL语句 select sk.seckill_id, sk.user_phone, sk.create_time, sk.state, s.seckill_id "seckill.seckill_id", s.name "seckill.name", s.number "seckill.number", s.start_time "seckill.start_time", s.end_time "seckill.end_time", s.create_time "seckill.create_time" from success_killed sk inner join seckill s on sk.seckill_id=s.seckill_id where sk.seckill_id = ? and sk.user_phone=?
         * 17:05:24.941 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==> Parameters: 1004(Long), 18853855263(Long)
         * 17:05:24.965 [main] DEBUG o.s.d.S.queryByIdWithSeckill - <==      Total: 1
         */



    }
}