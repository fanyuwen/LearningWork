package com.learning.bitoperate;

/**
 * 参考《算法心得》数,学习操作位运算技巧
 * 都是一些位操作的奇淫技巧
 *
 * @author fanyuwen
 * 2020-06-30 12:45
 */
public class BitOperate {

    public static void main(String[] args) {
        new BitOperate().morganLaw();
    }

    public void bitSkill() {
        //1.将最右边的值为1的位元"关闭"(设置成0),如果不存在值为1的位元,结果为0
        //可用来判断某个数是不是2的幂或0,判断结果是否为0
        int firstNum = 0b0101_1110;
        int r = firstNum & firstNum - 1;// 0b0101_1100

        //2.将最右边的值为0的位元"打开"(设置成1),如果不存在值为0的位元,结果的每一位为1
        int secondNum = 0b1010_0111;
        r = secondNum | secondNum + 1;//0b1010_1111

        //3.将最字组尾部的1都变成0,如果尾部没有1,则结果不变
        //可以通过判断结果是否是0来判断某个数是不是2^n-1或者0,也可以判断某个数的所有位元是否均为1
        int thirdNum = 0b1010_0111;
        r = thirdNum & thirdNum + 1;//0b1010_0000

        //4.将字组尾部的0都变成1,如果尾部没有0,则结果不变
        int forthNum = 0b1010_1000;
        r = forthNum | forthNum - 1;//0b1010_1111

        //5.将字组最靠右且值为0的位元变为1,并将其余位元置0,如果没有值为0的位元,则结果为0
        int fifthNum = 0b1010_0111;
        r = ~fifthNum & fifthNum + 1;//0b0000_1000

        //6.将字组最靠右且值为1的位元变为0,并将其余位元置1,如果没有值为1的位元,则每个位元均为1
        int sixthNum = 0b1010_1000;
        r = ~sixthNum | sixthNum - 1;//0b1111_0111

        //7.下面3个公式都可以把字组尾部所有值为0的位元变成1,并将其余位元置0,如果没有值为0的位元,则结果是0
        //第一个公式可以发挥处理器的某些指令级并行能力
        int seventhNum = 0b0101_1000;
        r = ~seventhNum & seventhNum - 1; //0b0000_0111
        r = ~(seventhNum | -seventhNum);  //0b0000_0111
        r = (seventhNum & -seventhNum) - 1;//0b0000_0111

        //8.将字组尾部所有值为1的位元都变成0,并将其余位元设为1,如果没有值为1的位元,则运算结果中的每个位元均是1
        int eighthNum = 0b1010_0111;
        r = ~eighthNum | eighthNum + 1;//0b1111_1000

        //9.将保留字组中最靠右且值为1的位元,并将其余位元置0,若不存在值为1的位元,则运算结果为0
        int ninethNum = 0b0101_1000;
        r = ninethNum & -ninethNum;

        //10.将字组最靠右且值为1的位元,及其右方所有值为0的位元都变成1,并将左方位元置0,若没有值为1的位元,则运算结果每一位都是1
        //而当尾部没有值为0的位元时,运算结果是1
        int tenthNum = 0b0101_1000;
        r = tenthNum ^ tenthNum - 1;//0b0000_1111

        //11.将字组最靠右且值为0的位元,及其右方所有值为1的位元都设为1,并将左方位元置0,若没有值为0的位元,则运算结果的每一位都是1
        //当没有值为1的位元时,运算结果是1
        int eleventh = 0b0101_0111;
        r = eleventh ^ eleventh + 1;//0b0000_1111

        //12.下列两个公式将字组最右侧连续出现且值为1的位元置0
        //如果值为非负数,并且套用上述公式运算结果为0,就表明可以写成 2 * x - 2 * k (j >= k >= 0)
        int twelfth = 0b0101_1100;
        r = (twelfth | twelfth - 1) + 1 & twelfth;//0b0100_0000
        r = (twelfth & -twelfth) + twelfth & twelfth;//0b0100_0000
    }

    private int XNOR(int x, int y) {
        return ~(x ^ y);
    }

    /**
     * 摩根定律
     */
    void morganLaw() {
        boolean r;
        int x = 1, y = 2;
        r = ~(x & y) == (~x | ~y);
        r = ~(x | y) == (~x & ~y);
        //
        r = ~(x + 1) == ~x - 1;
        r = ~(x - 1) == ~x + 1;
        //
        r = ~-x == x - 1;
        r = -~x == x + 1;
        //
        r = ~(x ^ y) == (~x ^ y);
        r = (~x ^ y) == XNOR(x, y);
        //
        r = ~(XNOR(x, y)) == XNOR(~x, y);
        r = XNOR(~x, y) == (x ^ y);
        //
        r = ~(x + y) == ~x - y;
        r = ~(x - y) == ~x + y;
        //上述公式

    }
}