package com.learning.encrypt.symmetricencrypt;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * @author fanyuwen
 * @date 2019/6/15 18:55
 * 3des对称加密实现
 */
public class THREEDES {
    private static final String ALGORITHM = "DESeds";
    private static final String ENCRYMODE = "DESede/ECB/PKCS5Padding";


    public byte[] generateKey() {
        //生成key
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        //keyGenerator.init(112)  3DES需要112 or 168位
        keyGenerator.init(new SecureRandom());//或者使用这种方式默认长度，无需指定长度
        SecretKey secretKey = keyGenerator.generateKey();//生成key的材料
        return secretKey.getEncoded(); //生成key
    }

    public byte[] encrypt(String src, String keyStr) {
        //key 转换成密钥
        byte[] encoded = Base64.getDecoder().decode(keyStr);
        try {
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(encoded);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey key = factory.generateSecret(deSedeKeySpec);
            //加密
            Cipher cipher = Cipher.getInstance(ENCRYMODE);
            cipher.init(Cipher.ENCRYPT_MODE, key);//指定为加密模式
            return cipher.doFinal(src.getBytes());
        } catch (InvalidKeyException |
                NoSuchAlgorithmException |
                InvalidKeySpecException |
                NoSuchPaddingException |
                IllegalBlockSizeException |
                BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public String decrypt(byte[] cipherByte, String keyStr) {
        //解密
        byte[] encoded = Base64.getDecoder().decode(keyStr);
        try {
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(encoded);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey key = factory.generateSecret(deSedeKeySpec);
            //加密
            Cipher cipher = Cipher.getInstance(ENCRYMODE);
            cipher.init(Cipher.DECRYPT_MODE, key);//指定为加密模式
            return new String(cipher.doFinal(cipherByte));
        } catch (InvalidKeyException |
                NoSuchAlgorithmException |
                InvalidKeySpecException |
                NoSuchPaddingException |
                IllegalBlockSizeException |
                BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

}
