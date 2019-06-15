package com.learning.encrypt.symmetricencrypt;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author fanyuwen
 */
public class AES {
    private static final String ALGORITHM = "AES";

    private static final String ENCRYMODE = "AES/ECB/PKCS5padding";

    public String generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyEncoded = secretKey.getEncoded();
            return Base64.getEncoder().encodeToString(keyEncoded);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] encrypt(String value, String key) {
        byte[] keyEncoded = Base64.getDecoder().decode(key);
        Key key_ = new SecretKeySpec(keyEncoded, ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(ENCRYMODE);
            cipher.init(Cipher.ENCRYPT_MODE, key_);
            return cipher.doFinal(value.getBytes());
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                InvalidKeyException |
                IllegalBlockSizeException |
                BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public String decrypt(byte[] cipherByte, String key){
        byte[] keyEncoded = Base64.getDecoder().decode(key);
        Key key_ = new SecretKeySpec(keyEncoded, ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(ENCRYMODE);
            cipher.init(Cipher.DECRYPT_MODE, key_);
            return new String(cipher.doFinal(cipherByte));
        } catch (NoSuchAlgorithmException|
                NoSuchPaddingException|
                InvalidKeyException|
                IllegalBlockSizeException|
                BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}