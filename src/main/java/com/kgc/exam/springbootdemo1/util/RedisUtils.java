package com.kgc.exam.springbootdemo1.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    public boolean set(String key,Object value){
        try{
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * String类型数据获取
     * @param key
     * @return object
     */
    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }


    public void del(String... key){
        if(key != null && key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }


    /*
    * 设置key过期
    * */
    public boolean expire(String key,long time){
        try{
            redisTemplate.expire(key,time,TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /*
    * 递增
    * */
    public long incr(String key,long delta){
        if(delta<0){
            throw new RuntimeException("递增因子小于零");
        }else {
            return redisTemplate.opsForValue().increment(key,delta);
        }
    }

    /*
    * 递减
    * */
    public long decr(String key,long delta){
        if(delta<0){
            throw new RuntimeException("递减因子小于零");
        }else {
            return redisTemplate.opsForValue().increment(key,-delta);
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
}
