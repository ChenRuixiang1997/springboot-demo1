package com.kgc.exam.springbootdemo1.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kgc.exam.springbootdemo1.conf.CurrentUser;
import com.kgc.exam.springbootdemo1.conf.LoginRequired;
import com.kgc.exam.springbootdemo1.dto.Goods;
import com.kgc.exam.springbootdemo1.dto.LoginUser;
import com.kgc.exam.springbootdemo1.dto.TestUser;
import com.kgc.exam.springbootdemo1.enums.UserErrorEnums;
import com.kgc.exam.springbootdemo1.exception.UserBussinessException;
import com.kgc.exam.springbootdemo1.service.GoodsService;
import com.kgc.exam.springbootdemo1.service.TestUserService;
import com.kgc.exam.springbootdemo1.util.IPUtils;
import com.kgc.exam.springbootdemo1.util.PageUtils;
import com.kgc.exam.springbootdemo1.util.RedisUtils;
import com.kgc.exam.springbootdemo1.util.ReturnResultsUtils;
import com.kgc.exam.springbootdemo1.vo.ReturnResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户访问接口")
@Log4j
public class UserController {

    private Goods goods;

    @Autowired
    private IPUtils ipUtils;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ReturnResultsUtils resultsUtils;

    @Autowired
    private TestUserService testUserService;


    @GetMapping(value = "/setGoods")
    public void setGoods(@Validated Goods goods, HttpServletRequest request) {
        String jsonStr = JSON.toJSONString(goods);
        redisUtils.set(ipUtils.getIpAddr(request), jsonStr);
    }

    @GetMapping(value = "/getGoods")
    public Goods getGoods(HttpServletRequest request) {
        Object obj = redisUtils.get(ipUtils.getIpAddr(request));
        if (!ObjectUtils.isEmpty(obj)) {
            goods = JSONObject.parseObject(obj.toString(), Goods.class);
            return goods;
        }
        return null;
    }


    public void testJDK8() {
        List<Object> goodList = new ArrayList<Object>();
        /*goodList.stream().forEach(g -> g.getGId());
        goodList.stream().forEach(Goods::getGId);

        List<Long> ids = goodList.stream().map(Goods::getGId).collect(Collectors.toList());

        long sum = ids.stream().mapToLong(n -> n).sum();
*/
    }

    @GetMapping(value = "/loginTest")
    @ApiOperation(value = "login", tags = "登录测试")
    public String loginTest(HttpServletRequest request) {
        String token = request.getSession().getId();
        LoginUser loginUser = new LoginUser();
        loginUser.setId(1);
        loginUser.setAge(22);
        loginUser.setName("crx");
        loginUser.setSex(1);
        String jsonStr = JSONObject.toJSONString(loginUser);
        redisUtils.set(token, jsonStr);
        return token;
    }

    @GetMapping(value = "/testWhenRequestDontHaveToken/{str}")
    @LoginRequired
    public String testWhenRequestDontHaveToken(@CurrentUser LoginUser loginUser, @PathVariable(name = "str") String str) {
        String jsonStr = JSON.toJSONString(loginUser);
        String s = str;
        return jsonStr;
    }

    /*@ApiOperation(value = "查询测试")
    @GetMapping(value = "/getGoodsTest")
    public PageUtils<Goods> getGoodsTest(int pageNo ,int pageSize){
        PageUtils<Goods> pageUtils = new PageUtils<Goods>();
        pageUtils.setCurrentPage(pageNo);
        pageUtils.setPageNo(pageNo);
        pageUtils.setPageSize(pageSize);
        PageUtils<Goods> pageUtils1 = goodsService.selectByPageUtils(pageUtils);
        return pageUtils1;
    }*/

    @ApiOperation(value = "查询测试")
    @GetMapping(value = "/getGoodsTest")
    public ReturnResults<PageUtils<Goods>> getGoodsTest(@Validated PageUtils pageUtils) {
        ReturnResults returnResults = null;
        try {
            PageUtils data = goodsService.selectByPageUtils(pageUtils);
            if (!ObjectUtils.isEmpty(data)) {
                returnResults = resultsUtils.returnDefined(1, "success", data);
                return returnResults;
            } else {
                returnResults = resultsUtils.returnFail(2, "empty");
                return returnResults;
            }
        } catch (Exception e) {
            return resultsUtils.returnFail(666, "unknow error");
        }
    }


    @ApiOperation(value = "插入测试")
    @GetMapping(value = "/insertTest")
    public boolean insertTest(Goods goods) {
        return goodsService.insert(goods);
    }

    @ApiOperation(value = "更新测试")
    @GetMapping(value = "/updateGoodsTest")
    public boolean updateGoodsTest(Goods goods) {
        return goodsService.update(goods, "测试商品1");
    }

    @ApiOperation(value = "注册测试")
    @GetMapping(value = "/registerUser")
    public ReturnResults<Boolean> registerUser(@Validated TestUser testUser) {
        try {
            testUser = null;
            testUserService.register(testUser);
            return resultsUtils.returnDefined(9, "注册成功", true);
        } catch (Exception e) {
            throw new UserBussinessException(UserErrorEnums.EMPTY_PARAM);
        }
    }

    @ApiOperation(value = "日志测试")
    @GetMapping(value = "/runLogger")
    public void runLogger(){
        try {
            //todo xxxx
            log.info("info");
        } catch (Exception e){
            log.error("error");
        }
    }


    @ApiOperation(value = "测试异常抛出")
    @GetMapping(value = "/testError")
    public void testError() throws UserBussinessException{
        throw new UserBussinessException(UserErrorEnums.EMPTY_PARAM.getErrorCode(),UserErrorEnums.EMPTY_PARAM.getMessage());
    }

}
