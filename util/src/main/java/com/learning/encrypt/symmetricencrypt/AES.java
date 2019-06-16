package com.learning.encrypt.symmetricencrypt;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * @author fanyuwen
 */
public class AES extends SymmetricEncrypt {
    private static final String ALGORITHM = "AES";

    private static final String ENCRYMODE = "AES/ECB/PKCS5padding";

    public AES(boolean isDefaultKey) {
        super(isDefaultKey);
    }

    @Override
    String algorithmName() {
        return ALGORITHM;
    }

    @Override
    Key getKey(byte[] encoded) {
        return new SecretKeySpec(encoded, ALGORITHM);
    }

    @Override
    public String defaultKey() {
        return super.defaultKey() + super.defaultKey();
    }
}