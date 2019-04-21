package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置Spring和Junit整合，JUnit启动时加载springIOC容器
 * spring-test junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉JUnit Spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testQueryById() throws Exception{
        long id=1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll() throws Exception{
        /**
         * Caused by: org.apache.ibatis.binding.BindingException:
         * Parameter 'offset' not found.
         * Available parameters are [arg1, arg0, param1, param2]
         * Java没有保存形参的记录：queryAll(int offset, int limit)->queryAll(arg0, arg1)
         * 有多个参数时，应告诉mybatis参数名字（在Dao中方法参数前添加注解@Param()）
         */
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill: seckills)
            System.out.println(seckill);
    }

    @Test
    public void testReduceNumber() throws Exception{
        Date killTime = new Date();
        int updateCount =
        seckillDao.reduceNumber(1000L, killTime);
        System.out.println(updateCount);
    }


}