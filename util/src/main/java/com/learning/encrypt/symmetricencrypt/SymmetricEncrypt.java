package com.learning.encrypt.symmetricencrypt;

/**
 * @author fanyuwen
 * 对称加密抽象
 */
public abstract class SymmetricEncrypt {
    //生成key -->>  转成字符串
    //加密(需要加密的字符串, key字符串)
    //解密(需要解密的字符串, key字符串)

    public String keyGenerator(){
        return "";
    }
}