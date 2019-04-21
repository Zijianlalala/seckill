package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * 配置Spring和Junit整合，JUnit启动时加载springIOC容器
 * spring-test junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉JUnit Spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void testInsertSuccessKilled() {
        int result=
                successKilledDao.insertSuccessKilled(1000L,18853855263L);
        System.out.println("affected rows:" + result);
    }

    @Test
    public void testQueryByIdWithSeckill() {
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(1000L, 18853855263l);
        System.out.println(successKilled.getSeckill());
        System.out.println(successKilled);
    }
}