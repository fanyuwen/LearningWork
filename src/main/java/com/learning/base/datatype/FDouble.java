package com.learning.base.datatype;

/**
 * @author fanyuwen
 */
public class FDouble {
    //double 类型数据长度为64 最高位符号位(+-)
    private double value;

    void compare(double value1, double value2) {
        //使用
        int result;
        if ((result = Double.compare(value1, value2)) == 0) {
            System.out.println("相等");
        }else if(result > 0){
            System.out.println("value1 大于 value2");
        }else{
            System.out.println("value2 大于 value1");
        }
    }
}
