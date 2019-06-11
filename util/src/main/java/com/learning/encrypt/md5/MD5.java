package com.learning.encrypt.md5;

import com.learning.encrypt.Encrypt;

import java.nio.charset.Charset;

/**
 * @author fanyuwen
 * MD5加密算法实现
 * 概述:
 * Message Digest Algorithm MD5（中文名为消息摘要算法第五版）为计算机安全领域广泛使用的一种散列函数，
 * 用以提供消息的完整性保护。该算法的文件号为RFC 1321（R.Rivest,MIT Laboratory for Computer Science and RSA Data Security Inc. April 1992）
 * <p>
 * MD5的全称是Message-Digest Algorithm 5（信息-摘要算法），
 * 在90年代初由MIT Laboratory for Computer Science和RSA Data Security Inc的Ronald L. Rivest开发出来，经MD2、MD3和MD4发展而来。
 * <p>
 * MD5用于确保信息传输完整一致。是计算机广泛使用的杂凑算法之一（又译摘要算法、哈希算法），主流编程语言普遍已有MD5实现。
 * 将数据（如汉字）运算为另一固定长度值，是杂凑算法的基础原理，MD5的前身有MD2、MD3和MD4。
 * <p>
 * MD5的作用是让大容量信息在用数字签名软件签署私人密钥前被"压缩"成一种保密的格式(就是把一个任意长度的字节串变换成一定长的十六进制数字串).
 * <p>
 * 算法原理:
 * 对MD5算法简要的叙述可以为：MD5以512位分组来处理输入的信息，且每一分组又被划分为16个32位子分组，经过了一系列的处理后，算法的输出由四个32位分组组成，将这四个32位分组级联后将生成一个128位散列值。
 * <p>
 * 在MD5算法中，首先需要对信息进行填充，使其位长对512求余的结果等于448。因此，信息的位长（Bits Length）将被扩展至N*512+448，N为一个非负整数，N可以是零。填充的方法如下，在信息的后面填充一个1和无数个0，
 * 直到满足上面的条件时才停止用0对信息的填充。然后，在这个结果后面附加一个以64位二进制表示的填充前信息长度。经过这两步的处理，信息的位长=N*512+448+64=(N+1）*512，即长度恰好是512的整数倍。
 * 这样做的原因是为满足后面处理中对信息长度的要求。
 */
public class MD5 extends Encrypt {
    private static final String MD5 = "MD5";

    public MD5() {
    }

    public MD5(Charset charset) {
        super(charset);
    }

    public String enEncode(String message) {
        return defaultEncode(message);
    }

    @Override
    protected String getAlgorithmName() {
        return MD5;
    }
}