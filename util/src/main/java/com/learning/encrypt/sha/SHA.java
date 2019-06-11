package com.learning.encrypt.sha;

import com.learning.encrypt.Encrypt;

import java.nio.charset.Charset;

/**
 * @author fanyuwen
 * 概述:
 * SHA是一种数据加密算法， 该算法经过加密专家多年来的发展和改进已日益完善，现在已成为公认的最安全的散列算法之一，并被广泛使用。该算法的思想是接收一段明文，
 * 然后以一种不可逆 的方式将它转换成一段（通常更小）密文，也可以简单的理解为取一串输入码（称为预映射或信息），并把它们转化为长度较短、位数固定的输出序列即散列值（也 称为信息摘要或信息认证代码）的过程。
 * 散列函数值可以说是对明文的一种“指纹”或是“摘要”所以对散列值的数字签名就可以视为对此明文的数字签名。
 * <p>
 * 安全散列算法SHA（Secure Hash Algorithm，SHA)是美国国家标准技术研究所发布的国家标准FIPS PUB 180，最新的标准已经于2008年更新到FIPS PUB 180-3。
 * 其中规定了SHA-1，SHA-224，SHA-256，SHA-384，和SHA-512这几种单向散列算法。SHA-1，SHA-224和SHA-256适用于长度不超过2^64二进制位的消息。SHA-384和SHA-512适用于长度不超过2^128二进制位的消息。
 * <p>
 * 原理:
 * SHA-1是一种数据加密算法，该算法的思想是接收一段明文，然后以一种不可逆的方式将它转换成一段（通常更小）密文，也可以简单的理解为取一串输入码（称为预映射或信息），
 * 并把它们转化为长度较短、位数固定的输出序列即散列值（也称为信息摘要或信息认证代码）的过程。
 * <p>
 * 单向散列函数的安全性在于其产生散列值的操作过程具有较强的单向性。如果在输入序列中嵌入密码，那么任何人在不知道密码的情况下都不能产生正确的散列值，从而保证了其安全性。
 * SHA将输入流按照每块512位（64个字节）进行分块，并产生20个字节的被称为信息认证代码或信息摘要的输出。
 * <p>
 * 该算法输入报文的长度不限，产生的输出是一个160位的报文摘要。输入是按512 位的分组进行处理的。SHA-1是不可逆的、防冲突，并具有良好的雪崩效应。
 * <p>
 * 通过散列算法可实现数字签名实现，数字签名的原理是将要传送的明文通过一种函数运算（Hash）转换成报文摘要（不同的明文对应不同的报文摘要），报文摘要加密后与明文一起传送给接受方，
 * 接受方将接受的明文产生新的报文摘要与发送方的发来报文摘要解密比较，比较结果一致表示明文未被改动，如果不一致表示明文已被篡改。
 * <p>
 * MAC （信息认证代码）就是一个散列结果，其中部分输入信息是密码，只有知道这个密码的参与者才能再次计算和验证MAC码的合法性。
 */
public class SHA extends Encrypt {
    private static final String SHA = "SHA";

    public SHA(){}

    public SHA(Charset charset){
        super(charset);
    }

    @Override
    public String enEncode(String message) {
        return defaultEncode(message);
    }

    @Override
    protected String getAlgorithmName() {
        return SHA;
    }
}
