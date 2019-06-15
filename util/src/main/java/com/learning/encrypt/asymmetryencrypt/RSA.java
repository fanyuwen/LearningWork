package com.learning.encrypt.asymmetryencrypt;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author fanyuwen
 * @date 2019/6/15 11:52
 * rsa非堆成加密算法
 */
public class RSA {

    private static final String ALGORITHM = "RSA";

    public void fun(String src) throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //公钥
        RSAPublicKey rsaPublicKey = (RSAPublicKey)keyPair.getPublic();
        //私钥
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)keyPair.getPrivate();
        //私钥加密,公钥解密--加密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(src.getBytes());

        //私钥加密,公钥解密--解密
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        keyFactory = KeyFactory.getInstance(ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        result = cipher.doFinal(result);

        //====================================================
        //公钥加密,私钥解密--加密
        x509EncodedKeySpec=new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        keyFactory = KeyFactory.getInstance(ALGORITHM);
        publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        result = cipher.doFinal(src.getBytes());

        //公钥加密,私钥解密--解密
        pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        keyFactory = KeyFactory.getInstance(ALGORITHM);
        privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        result = cipher.doFinal(result);


    }

}
