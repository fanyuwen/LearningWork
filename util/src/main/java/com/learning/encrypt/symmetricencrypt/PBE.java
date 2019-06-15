package com.learning.encrypt.symmetricencrypt;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * @author fanyuwen
 * @date 2019/6/15 07:50
 * 基于口令的对称加密算法（它其实是对之前的算法的包装，比如说MD5和DES，我这里就是的是对MD5和DES包装的PBE算法，
 * 还有其他类型的PBE），口令就是我们俗话说的密码，PBE中有一个salt（盐）的概念，盐就是干扰码
 */
public class PBE {
    private static final String ALGORITHM = "PBEWITHMD5andDES";

    private static final int SEEDLEN = 8;

    private static final byte[] SALT;

    static {
        //初始化盐
        SecureRandom random = new SecureRandom();
        SALT = random.generateSeed(SEEDLEN);
    }

    private static final String PASSWORD = "lynu";//口令

    public Key generateKey(String password) {
        //口令和密钥
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            return keyFactory.generateSecret(pbeKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密实现
     * @param value 待加密字符串
     * @param keyStr 加密键
     * @return 加密之后的字符串
     */
    public String encrypt(String value, String keyStr) {
        Key key = generateKey(keyStr);
        //参数规范，第一个参数是盐,第二个是迭代次数(经过散列函数多次迭代)
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(SALT, 100);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                InvalidKeyException |
                InvalidAlgorithmParameterException |
                IllegalBlockSizeException |
                BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密实现
     * @param data 待解密字符串
     * @param keyStr 加密键
     * @return 解密之后还原的字符串
     */
    public String decrypt(String data, String keyStr) {
        Key key = generateKey(keyStr);
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(SALT, 100);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                InvalidKeyException |
                InvalidAlgorithmParameterException |
                IllegalBlockSizeException |
                BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
