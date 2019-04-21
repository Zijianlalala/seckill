package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;

/**
 * 封装秒杀执行后的结果
 */
public class SeckillExcution {

    private long seckillId;
    private int state;//执行结果状态
    private String stateInfo;//状态表示
    private SuccessKilled successKille;//秒杀成功对象

    public SeckillExcution(long seckillId, SeckillStatEnum statEnum, SuccessKilled successKille) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
        this.successKille = successKille;
    }

    public SeckillExcution(long seckillId, SeckillStatEnum statEnum) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKille() {
        return successKille;
    }

    public void setSuccessKille(SuccessKilled successKille) {
        this.successKille = successKille;
    }
}

