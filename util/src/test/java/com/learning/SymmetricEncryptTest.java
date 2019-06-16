package com.learning;

import com.learning.encrypt.symmetricencrypt.AES;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author fanyuwen
 * @date 2019/6/16 18:42
 */
public class SymmetricEncryptTest {

    @Test
    public void aesTest() {
        AES aes = new AES(true);
        String encode = aes.encrypt("bairenjie");
        System.out.println("encode: " + encode);
        String decode;
        System.out.println(decode = aes.decrypt(encode));
        Assert.assertEquals(decode, "bairenjie");
    }

}