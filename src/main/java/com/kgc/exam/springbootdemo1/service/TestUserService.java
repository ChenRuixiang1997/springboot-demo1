package com.kgc.exam.springbootdemo1.service;

import com.kgc.exam.springbootdemo1.dto.TestUser;
import com.kgc.exam.springbootdemo1.mapper.TestUserMapper;
import com.kgc.exam.springbootdemo1.util.SecuritySHA1Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class TestUserService {

    @Autowired
    private TestUserMapper testUserMapper;



    public boolean register(TestUser registerUser) {
        String pwd = registerUser.getuPassword();
        //加密
        String scurityPwd = null;
        try {
            scurityPwd = SecuritySHA1Utils.shaEncode(pwd);
            if(!StringUtils.isEmpty(scurityPwd)){
                registerUser.setuPassword(scurityPwd);
                if(testUserMapper.insertSelective(registerUser)==1){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
