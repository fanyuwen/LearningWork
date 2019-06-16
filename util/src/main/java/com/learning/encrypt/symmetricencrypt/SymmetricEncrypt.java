package com.learning.encrypt.symmetricencrypt;

import com.learning.StringUtils;

import javax.crypto.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author fanyuwen
 * 对称加密抽象
 */
public abstract class SymmetricEncrypt {
    //生成key -->>  转成字符串  -->>  持久化方案
    //加密(需要加密的字符串, key字符串)
    //解密(需要解密的字符串, key字符串)

    private boolean isDefaultKey;

    SymmetricEncrypt(boolean isDefault) {
        this.isDefaultKey = isDefault;
    }

    /**
     * 所使用的算法名称
     *
     * @return
     */
    abstract String algorithmName();

    /**
     * 输出到的指定的父目录
     *
     * @return 父目录路径
     */
    String parentDir() {
        return "com/learning/encrypt/symmetricencrypt";
    }

    /**
     * 将key 字节数组转换成Key
     *
     * @param encoded 字节数组
     * @return
     */
    abstract Key getKey(byte[] encoded);

    /**
     * 将字符串key转换为字节数组
     *
     * @param key 字符串key
     * @return 字节数组
     */
    byte[] convertStrKeyToBytes(String key) {
        return isDefaultKey ? defaultConvert(key) : conventionConvert(key);
    }

    /**
     * 默认key的方式转换为字节数组
     *
     * @param key
     * @return
     */
    byte[] defaultConvert(String key) {
        return StringUtils.convertHexStrToBytes(key);
    }

    /**
     * 将字符串key(从文件中读取的)转换为字节数组
     *
     * @param key
     * @return
     */
    byte[] conventionConvert(String key) {
        return Base64.getDecoder().decode(key);
    }

    /**
     * 获取加密的key
     *
     * @return
     */
    Key getKey() {
        String key = isDefaultKey ? defaultKey() : resolveKeyfromfile();
        byte[] encoded = convertStrKeyToBytes(key);
        return getKey(encoded);
    }

    /**
     * 指定默认的key
     *
     * @return
     */
    public String defaultKey() {
        return "A1B2C3D4E5F60708";
    }

    /**
     * 生成随机的key
     *
     * @return key字符串
     */
    String keyGenerator() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithmName());
            keyGenerator.init(new SecureRandom());
//            keyGenerator.init(512); 指定初始化长度
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] encoded = secretKey.getEncoded();
            return Base64.getEncoder().encodeToString(encoded);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取输出的key的文件(Path形式)
     *
     * @return path
     */
    private Path frompath() {
        URL url = getClass().getClassLoader().getResource("");
        if (url == null) {
            throw new RuntimeException("can't get the url,the url is null");
        }
        Path path = Paths.get(new File(url.getPath()).getPath(), parentDir(), "persistenceKey.txt");
        if (Files.notExists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return path;
    }

    /**
     * 持久化
     * 目前采用持久化到指定文件里(jdk7添加的path处理)
     *
     * @return true
     */
    public boolean persistenceKey() {
        Path path = frompath();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(path, StandardOpenOption.READ)));) {
            String line;
            StringBuilder sb = new StringBuilder();
            while (StringUtils.isNotEmpty(line = br.readLine())) {
                if (!line.startsWith(algorithmName())) {
                    sb.append(line).append("\n");
                }
            }
            try (PrintWriter pw = new PrintWriter(Files.newOutputStream(path, StandardOpenOption.TRUNCATE_EXISTING))) {
                String key = keyGenerator();
                sb.append(algorithmName()).append(":").append(key);
                pw.println(sb.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * 从文件中读取key
     *
     * @return
     */
    public String resolveKeyfromfile() {
        Path path = frompath();
        String line;
        try (BufferedReader br = Files.newBufferedReader(path)) {
            while (StringUtils.isNotEmpty(line = br.readLine())) {
                if (line.startsWith(algorithmName())) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return line.split(":")[1];
    }

    /**
     * 加密
     *
     * @return 加密之后的字符串表示
     */
    public String encrypt(String origin) {
        Key key = getKey();
        try {
            Cipher cipher = Cipher.getInstance(algorithmName());
            cipher.init(Cipher.ENCRYPT_MODE, key, new SecureRandom());
            return Base64.getEncoder().encodeToString(cipher.doFinal(origin.getBytes()));
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                BadPaddingException |
                IllegalBlockSizeException |
                InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密
     *
     * @param ciphertext 加密方法的返回值(加密的密文)
     * @return 解密之后的字符串
     */
    public String decrypt(String ciphertext) {
        Key key = getKey();
        try {
            Cipher cipher = Cipher.getInstance(algorithmName());
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)));
        } catch (NoSuchAlgorithmException |
                InvalidKeyException |
                IllegalBlockSizeException |
                BadPaddingException |
                NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        byte c = (byte)0b0000_0111;
        System.out.println(c);
    }
}