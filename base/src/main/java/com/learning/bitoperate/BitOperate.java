package com.learning.bitoperate;

/**
 * 参考《算法心得》数,学习操作位运算技巧
 * 都是一些位操作的奇淫技巧
 *
 * @author fanyuwen
 * 2020-06-30 12:45
 */
public class BitOperate {

    public void bitSkill() {
        //1.将最右边的值为1的位元"关闭"(设置成0),如果不存在值为1的位元,结果为0
        //可用来判断某个数是不是2的幂或0,判断结果是否为0
        int firstNum = 0b0101_1110;
        int r = firstNum & firstNum - 1;// 0b0101_1100
        //2.将最右边的值为0的位元"打开"(设置成1),如果不存在值为0的位元,结果的每一位为1
        int secondNum = 0b1010_0111;
        r = secondNum | secondNum + 1;//0b1010_1111
        //3.将最字组尾部的1都变成0,如果尾部没有1,则结果不变
        int thirdNum = 0b1010_0111;
        r = thirdNum & thirdNum + 1;//0b1010_0000
        //4.将最字组尾部的1都变成0,如果尾部没有1,则结果不变
        //

    }

}