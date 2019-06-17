package com.learning;

import com.learning.encrypt.symmetricencrypt.AES;
import com.learning.encrypt.symmetricencrypt.DES;
import com.learning.encrypt.symmetricencrypt.PBE;
import com.learning.encrypt.symmetricencrypt.THREEDES;
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

    @Test
    public void desTest() {
        DES des = new DES(true);
        String encode = "4oKuwRogwEk=";
        String decode = des.decrypt(encode);
        System.out.println(decode);
        Assert.assertEquals(decode, "root");
    }

    @Test
    public void threedesTest() {
        THREEDES threedes = new THREEDES(true);
        String decode = "bairenjie";
        String encode = threedes.encrypt(decode);
        System.out.println("encode: " + encode);
        Assert.assertEquals(threedes.decrypt(encode), decode);
    }

    @Test
    public void pbetest() {
        PBE pbe = new PBE(true, "lynu");
        String decode = "bairenjie";
        String encode = pbe.encrypt(decode);
        System.out.println(encode);
        Assert.assertEquals(pbe.decrypt(encode), decode);
    }

}