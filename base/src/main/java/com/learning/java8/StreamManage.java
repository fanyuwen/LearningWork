package com.learning.java8;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author SONGWEI
 * @version 1.0
 * Date: 2019/6/11/011
 */
public class StreamManage {

    public static void main(String[] args) {
        strManage();
    }

    /**
     * 通过java8流式处理字符串数组
     */
    static void strManage() {
        // 定义字符串数组
        String[] strs = {"1","2","3","4"};
        // 通过流式处理将每个字符串拼接“8” 最终返回字符串 [18-28-38-48]
        String s = Arrays.stream(strs).map(item -> item + "8")
                .collect(Collectors.joining("-", "[", "]"));
        System.out.println(s);
    }
}
