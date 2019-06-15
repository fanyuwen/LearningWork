package com.learning.encrypt.asymmetryencrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

/**
 * @author fanyuwen
 * @date 2019/6/15 11:24
 * 非对称加密实现
 */
public class DH {

    private static final String ALGORITHM = "DH";

    public void fun(String src) throws Exception {
        //初始化发送方密钥
        KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        //初始化生成器
        senderKeyPairGenerator.initialize(512);
        //获得密钥对
        KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();
        //获取公钥
        byte[] senderPublicKeyEnc = senderKeyPair.getPublic().getEncoded();

        //初始化接收方密钥
        KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicKeyEnc);
        //根据从发送方得到的key解析
        PublicKey receiverPublicKey = factory.generatePublic(x509EncodedKeySpec);
        DHParameterSpec dhParameterSpec = ((DHPublicKey) receiverPublicKey).getParams();

        KeyPairGenerator receiverKeyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        receiverKeyPairGenerator.initialize(dhParameterSpec);
        KeyPair receiverKeyPair = receiverKeyPairGenerator.generateKeyPair();

        PrivateKey receiverPrivateKey = receiverKeyPair.getPrivate();
        byte[] receiverPublicKeyEnc = receiverKeyPair.getPublic().getEncoded();

        //密钥构建
        KeyAgreement receiverKeyAgreement = KeyAgreement.getInstance(ALGORITHM);
        receiverKeyAgreement.init(receiverPrivateKey);
        receiverKeyAgreement.doPhase(receiverPublicKey, true);
        //发送方 密钥(公钥)
        SecretKey receiverDESKey = receiverKeyAgreement.generateSecret("DES");

        KeyFactory senderKeyFactory = KeyFactory.getInstance(ALGORITHM);
        x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
        PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509EncodedKeySpec);
        KeyAgreement senderKeyAgreement = KeyAgreement.getInstance(ALGORITHM);
        senderKeyAgreement.init(senderKeyPair.getPrivate());
        senderKeyAgreement.doPhase(senderPublicKey, true);
        //接收方 密钥(私钥)
        SecretKey senderDESKey = senderKeyAgreement.generateSecret("DES");

        if(Objects.equals(receiverDESKey, senderDESKey)){
            System.out.println("双方密钥相同");
        }
        //加密
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, senderDESKey);
        byte[] result = cipher.doFinal(src.getBytes());
        System.out.println();

        //解密
        cipher.init(Cipher.DECRYPT_MODE, receiverDESKey);
        result = cipher.doFinal(result);
    }

}
