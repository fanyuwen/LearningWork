package com.learning.encrypt.digestencrypt;

import com.learning.StringUtils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author fanyuwen
 * @date 2019/6/15 14:51
 * 摘要算法抽象
 * SHA-1和MD5的比较:
 * 因为二者均由MD4导出，SHA-1和MD5彼此很相似。相应的，他们的强度和其他特性也是相似，但还有以下几点不同：
 * 1:对强行攻击的安全性：最显著和最重要的区别是SHA-1摘要比MD5摘要长32 位。使用强行技术，产生任何一个报文使其摘要等于给定报摘要的难度对MD5是2^128数量级的操作，
 * 而对SHA-1则是2^160数量级的操作。这样，SHA-1对强行攻击有更大的强度。
 * 2:对密码分析的安全性：由于MD5的设计，易受密码分析的攻击，SHA-1显得不易受这样的攻击。
 * <p>
 * 3:速度：在相同的硬件上，SHA-1的运行速度比MD5慢。
 */
public abstract class DigestEncrypt {
    protected static final char[] COMPARISON_HEX_TABLE;
    /**
     * 字符编码集
     */
    private Charset charset;

    public DigestEncrypt() {
        this.charset = Charset.defaultCharset();
    }

    public DigestEncrypt(Charset charset) {
        this.charset = charset;
    }

    protected Charset getCharset() {
        return charset;
    }

    static {
        final int HEX = 16;
        COMPARISON_HEX_TABLE = new char[HEX];
        for (int i = 0; i < 10; i++) {
            COMPARISON_HEX_TABLE[i] = (char) ('0' + i);
        }
        for (int i = 10; i < HEX; i++) {
            COMPARISON_HEX_TABLE[i] = (char) ('a' + i - 10);
        }
    }

    protected char[] byteToHexCharArray(byte num) {
        final int high4bit = 0xf0;
        final int low4bit = 0xf;
        char high = COMPARISON_HEX_TABLE[(high4bit & num) >> 4];
        char low = COMPARISON_HEX_TABLE[low4bit & num];
        return new char[]{high, low};
    }

    /**
     * 模板模式
     *
     * @param message 待加密的字符串
     * @param key     需要传递的摘要的key,如果没有就使用 {@link #DEFAULT_KEY}
     * @return 加密之后的字符串
     */
    public final String encode(String message, String key) {
        if (StringUtils.isEmpty(message)) {
            return message;
        }
        return enEncode(message, key);
    }

    /**
     * 采用默认的key的编码方式
     *
     * @param message 待加密的字符串
     * @return 加密之后的字符串
     */
    public final String encode(String message) {
        return encode(message, DEFAULT_KEY);
    }

    public static final String DEFAULT_KEY = "";

    public String getKey() {
        return DEFAULT_KEY;
    }

    protected abstract String enEncode(String message, String key);

    protected abstract String getAlgorithmName();

    final String getNameWithNoEmpty() {
        String name;
        if (StringUtils.isEmpty(name = getAlgorithmName())) {
            throw new RuntimeException("加密算法名称不能为空");
        }
        return name;
    }

    protected final String defaultEncode(String message) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(getNameWithNoEmpty());
            byte[] messageBytes = message.getBytes(getCharset());
            byte[] md5messageBytes = messageDigest.digest(messageBytes);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < md5messageBytes.length; i++) {
                byte current = md5messageBytes[i];
                char[] chars = byteToHexCharArray(current);
                sb.append(chars[0]).append(chars[1]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            //ignore --> must be correct
        }
        return message;
    }
}