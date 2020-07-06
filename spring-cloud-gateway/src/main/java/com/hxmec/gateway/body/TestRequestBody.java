package com.hxmec.gateway.body;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

/**
 * 功能描述: 
 * @author  Trazen
 * @date  2020/7/2 14:06
 */
@Component
@Slf4j
public class TestRequestBody implements Predicate {

    /**
     * 根据内容判断是否匹配该路由
     * @param o body的内容
     * @return ture-匹配成功，false-匹配失败
     */
    @Override
    public boolean test(Object o) {
        //可以对body内容进行判断处理
        System.out.println(o);
        //这里不做处理直接返回成功
        return true;
    }
}