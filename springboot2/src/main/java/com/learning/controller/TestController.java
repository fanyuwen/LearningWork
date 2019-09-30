package com.learning.controller;


import com.learning.modle.TestRq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author bairenjie
 * @date 2019/9/30 11:18
 */
@RestController
@Api(value = "TestController,测试swagger APi",tags = "测试swagger APi文档")
public class TestController {

    @PostMapping("/swagger/test")
    @ApiOperation(value = "测试swagger api的简单接口",notes = "TestSwaggerApi---仅为测试使用")
    public String TestSwaggerApi(@RequestBody TestRq testRq){
        StringBuilder str = new StringBuilder("Hello,my name is ");
        str.append(testRq.getName())
                .append(",i am ")
                .append(testRq.getAge())
                .append(" years old,")
                .append("i live in ")
                .append(testRq.getAddress());
        String word = new String(str);
        return word;
    }
}
