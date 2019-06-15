package com.learning.encrypt.symmetricencrypt;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * @author fanyuwen
 * 对称加密算法
 * DES算法全称为Data Encryption Standard，即数据加密算法，它是IBM公司于1975年研究成功并公开发表的。
 * DES算法的入口参数有三个：Key、Data、Mode。其中Key为8个字节共64位，是DES算法的工作密钥；Data也为8个字节64位，
 * 是要被加密或被解密的数据；Mode为DES的工作方式,有两种：加密或解密。
 * <p>
 * DES算法把64位的明文输入块变为64位的密文输出块，它所使用的密钥也是64位，其算法主要分为两步：
 * 1.初始置换:
 * 其功能是把输入的64位数据块按位重新组合,并把输出分为L0、R0两部分，每部分各长32位，其置换规则为将输入的第58位换到第一位，
 * 第50位换到第2位……依此类推,最后一位是原来的第7位。L0、R0则是换位输出后的两部分，L0是输出的左32位，R0是右32位，
 * 例：设置换前的输入值为D1D2D3……D64，则经过初始置换后的结果为:L0=D58D50……D8；R0=D57D49……D7。
 * 2.逆置换:
 * 经过16次迭代运算后，得到L16、R16,将此作为输入，进行逆置换，逆置换正好是初始置换的逆运算，由此即得到密文输出。
 * 五种分组模式:
 * a.EBC模式
 * b.CBC模式
 * c.CFB模式
 * d.OFB模式
 * f.CTR模式
 * 常用填充方式:
 * NoPadding
 * ZerosPadding
 * PKCS5Padding
 */
public class DES {
    private static final String KEY_ALGORITHM = "DES";

    private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

    private static final String DEFAULT_KEY = "A1B2C3D4E5F60708";

    /**
     * 通过键生成器生成键
     * @return
     */
    private static byte[] keyGenerator() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            keyGenerator.init(DEFAULT_KEY.length());
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static SecretKey keyGenerator(String keyStr) {
        try {
            byte[] input = HexString2Bytes(keyStr);
            DESKeySpec desKey = new DESKeySpec(input);
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generateSecret(desKey);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private static int parse(char c) {
        if (c >= 'a') return (c - 'a' + 10) & 0x0f;
        if (c >= 'A') return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    // 从十六进制字符串到字节数组转换
    public static byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return 加密后的数据
     */
    public static String encrypt(String data, String key) throws Exception {
        Key deskey = keyGenerator(key);
        // 实例化Cipher对象，它用于完成实际的加密操作
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        SecureRandom random = new SecureRandom();
        // 初始化Cipher对象，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
        byte[] results = cipher.doFinal(data.getBytes());
        // 该部分是为了与加解密在线测试网站（http://tripledes.online-domain-tools.com/）的十六进制结果进行核对
        for (byte result : results) {
            System.out.print(result + " ");
        }
        System.out.println();
        // 执行加密操作。加密后的结果通常都会用Base64编码进行传输
        return Base64.getEncoder().encodeToString((results));
    }

    public static String encrypt(String data) throws Exception {
        return encrypt(data, DEFAULT_KEY);
    }

    /**
     * 解密数据
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return 解密后的数据
     */
    public static String decrypt(String data, String key) {
        try {
            Key deskey = keyGenerator(key);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            //初始化Cipher对象，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, deskey);
            // 执行解密操作
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                InvalidKeyException |
                IllegalBlockSizeException |
                BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String data) {
        return decrypt(data, DEFAULT_KEY);
    }
}