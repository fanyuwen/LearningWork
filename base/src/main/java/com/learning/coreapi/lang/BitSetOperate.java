package com.learning.coreapi.lang;

import java.util.BitSet;

/**
 * java.util.BitSet使用
 *
 * @author fanyuwen
 * 2020-06-29 15:24
 */
public class BitSetOperate {
    /*
     * 采用位操作进行保存数据的方式,通过位索引来记录数据,在二进制的世界里每一位都是0或1  0000 0000
     * 一个int类型的数字占32位(4个字节),就可以存储32个数(1~32),这样一个32数就能存储32个数
     * bitset采用long类型数组来存储数据
     */
    void bitSetShow() {
        BitSet bitSet = new BitSet();
        bitSet.set(12);
        bitSet.set(128);
    }
}
