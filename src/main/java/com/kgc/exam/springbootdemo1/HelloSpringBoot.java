package com.kgc.exam.springbootdemo1;

import com.kgc.exam.springbootdemo1.conf.RedisConfig;
import com.kgc.exam.springbootdemo1.dto.Image;
import com.kgc.exam.springbootdemo1.dto.User;
import com.kgc.exam.springbootdemo1.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hello")
public class HelloSpringBoot {
    @Value(value = "${user.admin}")
    private String userName;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private User user;
    /*
    * 设定请求方式为get并且地址为"/springboot"
    * */
    @GetMapping(value = "/springboot")
    public String hello(){
        return "hello Spring Boot";
    }

    @GetMapping("/getName")
    public String getName(){
        if (!StringUtils.isEmpty(userName)){
            return userName;
        }
        return null;
    }

    @GetMapping(value = "/getUser")
    private User getUser(){
        if (!ObjectUtils.isEmpty(user)){
            return user;
        }
        return new User();
    }

    @GetMapping(value = "/test")
    public Integer test(){
        redisUtils.set("RedisKey",123);
        return (Integer)redisUtils.get("RedisKey");
    }

    @GetMapping(value = "/set_expire")
    public void set_expire(){
        if (redisUtils.get("k1")==null){
            redisUtils.set("k1","k1");
        }
        redisUtils.expire("k1",10);
    }

    @GetMapping(value = "/get")
    public String get(){
        if(redisUtils.get("k1") == null){
            return null;
        }
        return (redisUtils.get("k1")).toString();
    }

    @GetMapping(value = "/getExpire")
    public long getExpire(){
        return redisUtils.getExpire("k1");
    }

    @GetMapping(value = "/inc")
    public long inc(){
        return redisUtils.incr("RedisKey",1);
    }

    @GetMapping(value = "/dec")
    public long dec(){
        return redisUtils.decr("RedisKey",1);
    }
}
