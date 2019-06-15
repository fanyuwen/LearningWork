package com.learning.encrypt.digestencrypt;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author fanyuwen
 * HMAC
 * HMAC(Hash Message Authentication Code,散列消息鉴别码,基于密钥的Hash算法的认证协议.
 * 消息鉴别码实现鉴别的原理是,用公开函数和密钥产生一个固定长度的值作为认证标识,用这个标识鉴别消息的完整性.
 * 使用一个密钥生成一个固定大小的小数据块,
 * 即MAC,并将其加入到消息中,然后传输.接收方利用与发送方共享的密钥进行鉴别认证等.
 */
public class HMACMD5 {
    private static final String KEY_MAC = "HmacMD5";

    private static final String KEY = initMacKey();

    /**
     * 初始化HMAC密钥(同一个密钥加密生成的结果是一样的)
     *
     * @return
     * @throws Exception
     */
    public static String initMacKey() {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(KEY_MAC);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    /**
     * HMAC加密: 主要方法
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptHMAC(byte[] data, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return new String(mac.doFinal(data));
    }
}
