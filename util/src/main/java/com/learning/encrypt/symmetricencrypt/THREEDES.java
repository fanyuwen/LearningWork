package com.learning.encrypt.symmetricencrypt;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author fanyuwen
 * @date 2019/6/15 18:55
 * 3des对称加密实现
 */
public class THREEDES extends SymmetricEncrypt {
    private static final String ALGORITHM = "DESede";
    private static final String ENCRYMODE = "DESede/ECB/PKCS5Padding";

    public THREEDES(boolean isDefault) {
        super(isDefault);
    }

    @Override
    String algorithmName() {
        return ALGORITHM;
    }

    @Override
    String cipherAlgorithmName() {
        return ENCRYMODE;
    }

    //A1B2C3D4E5F60708
    @Override
    public String defaultKey() {
        String s = super.defaultKey() + "090a0b0c0d0e0f";
        return s + s;
    }

    @Override
    Key getKey(byte[] encoded) {
        try {
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(encoded);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            return factory.generateSecret(deSedeKeySpec);
        } catch (InvalidKeyException |
                NoSuchAlgorithmException |
                InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
