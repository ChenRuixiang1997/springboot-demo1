package com.kgc.exam.springbootdemo1.service;

import com.kgc.exam.springbootdemo1.dto.Goods;
import com.kgc.exam.springbootdemo1.dto.GoodsExample;
import com.kgc.exam.springbootdemo1.mapper.GoodsMapper;
import com.kgc.exam.springbootdemo1.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public PageUtils<Goods> selectByPageUtils(PageUtils<Goods> pageUtils){
        GoodsExample goodsExample = new GoodsExample();
        //goodsExample.setLimit(pageUtils.getPageNo());
        //goodsExample.setOffset(pageUtils.getPageSize());
        List<Goods> content = goodsMapper.selectByExample(goodsExample);
        pageUtils.setCurrentList(content);
        pageUtils.setTotalCount((int) goodsMapper.countByExample(goodsExample));
        return pageUtils;
    }

    public boolean insert(Goods goods){
        if(goodsMapper.insertSelective(goods)>0){
            return true;
        }
        return false;
    }

    public boolean update(Goods goods,String gName){
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andGNameEqualTo(gName);
        if (goodsMapper.updateByExampleSelective(goods,goodsExample)>0){
            return true;
        }
        return false;
    }
}
