package com.encryption.symmetricencrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * PBE 加密算法
 * @author YUDI
 * @date 2020/9/26 17:01
 */
public class PBESummary {

    private static String plainText = "Anger will lower your mind";

    public static void main(String[] args) throws Exception {
        pbe();
    }

    public static void pbe() throws Exception {

        // 加入 Bouncy Castle 扩展
        // Security.addProvider(new BouncyCastleProvider());

        // 生成随机盐，长度为 8
        SecureRandom random = new SecureRandom();
        byte[] salt = random.generateSeed(8);

        // 创建口令(password)，指定算法构建 SecretKeyFactory 并生成密钥
        String password = "YUDI";
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        Key key = factory.generateSecret(pbeKeySpec);

        // 加密
        // 生成加密参数集，将盐迭代 100 次
        PBEParameterSpec param = new PBEParameterSpec(salt,100);
        Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
        cipher.init(Cipher.ENCRYPT_MODE,key,param);
        byte[] encryptBytes = cipher.doFinal(plainText.getBytes());
        System.out.println("PBE encrypt："+ Base64.encodeBase64String(encryptBytes));

        // 解密
        cipher.init(Cipher.DECRYPT_MODE,key,param);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        System.out.println("PBE decrypt："+ new String(decryptBytes));
    }
}
