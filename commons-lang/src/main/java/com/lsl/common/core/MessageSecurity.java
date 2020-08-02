package com.lsl.common.core;

import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

import com.lsl.common.exception.BizRuntimeException;

/**
 * 安全加密类
 * 
 * @version v1.0
 * @description
 * @date 2020/3/4 12:45
 * @created by yangcao
 **/
public class MessageSecurity {

    private static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final String KEY_ALGORITHM_RSA = "RSA";
    public static final String SIGNATURE_ALGORITHM_RSA_MD5 = "MD5withRSA";
    private static final String PUBLIC_KEY_RSA = "RSAPublicKey";
    private static final String PRIVATE_KEY_RSA = "RSAPrivateKey";

    private static String md5(String msg) {
        try {
            if (msg == null) {
                return null;
            }
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(msg.getBytes(Charset.forName("UTF-8")));
            return toHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BizRuntimeException("不支持MD5加密算法");
        }
    }

    private static String sha1(String msg) throws NoSuchAlgorithmException {
        try {
            if (msg == null) {
                return null;
            }
            MessageDigest md5 = MessageDigest.getInstance("SHA1");
            byte[] digest = md5.digest(msg.getBytes(Charset.forName("UTF-8")));
            return toHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BizRuntimeException("不支持SHA1加密算法");
        }
    }

    /**
     * DES 加密
     *
     * @param msg
     *            待加密信息
     * @param key
     *            密码
     * @return
     */
    public static String encryptWithDES(String msg, String key) {
        if (key == null || key.length() < 8) {
            throw new BizRuntimeException("KEY长度不能小于8");
        }
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");

            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            return Base64.getEncoder().encodeToString(cipher.doFinal(msg.getBytes(Charset.forName("UTF-8"))));
        } catch (InvalidKeyException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new BizRuntimeException("无效KEY或生成KEY无效：" + key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BizRuntimeException("无效加密算法DES");
        } catch (NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new BizRuntimeException("加密失败");
        }
    }

    static String decryptWithDES(String src, String key) {
        SecureRandom random = new SecureRandom();
        try {
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            return new String(cipher.doFinal(Base64.getDecoder().decode(src)));
        } catch (InvalidKeyException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new BizRuntimeException("无效KEY或生成解密KEY无效：" + key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BizRuntimeException("无效加密算法DES");
        } catch (NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new BizRuntimeException("解密失败");
        }
    }

    public static String toHex(byte[] digest) {
        int length = digest.length;
        char[] hexChars = new char[length * 2];
        int k = 0;
        for (int i = 0; i < length; i++) {
            byte byte0 = digest[i];
            hexChars[k++] = hexDigits[byte0 >>> 4 & 0xf];
            hexChars[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(hexChars);
    }

    /**
     * 通常用户CA签名网站pub.key 用私钥对信息生成数字签名
     *
     * @param data
     *            加密数据
     * @param privateKey
     *            私钥
     * @return 返回结果使用Base64编码
     */
    public static String sign(byte[] data, String privateKey) {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        try {
            // 构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            // KEY_ALGORITHM_RSA 指定的加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
            // 取私钥匙对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 用私钥对信息生成数字签名
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM_RSA_MD5);
            signature.initSign(priKey);
            signature.update(data);
            byte[] sign = signature.sign();
            return Base64.getEncoder().encodeToString(sign);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BizRuntimeException("数字签名失败，不支持数字签名算法：" + KEY_ALGORITHM_RSA);
        } catch (InvalidKeySpecException | InvalidKeyException e) {
            e.printStackTrace();
            throw new BizRuntimeException("数字签名失败，无效私钥：" + privateKey);
        } catch (SignatureException e) {
            e.printStackTrace();
            throw new BizRuntimeException("数字签名失败");
        }
    }

    /**
     * 校验数字签名 和生成数值签名逻辑一致
     *
     * @param data
     *            加密数据
     * @param publicKey
     *            公钥
     * @param sign
     *            使用数字签名结果
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) {
        // 解密由base64编码的公钥
        try {
            byte[] keyBytes = Base64.getDecoder().decode(publicKey);
            // 构造X509EncodedKeySpec对象
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            // KEY_ALGORITHM_RSA 指定的加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
            // 取公钥匙对象
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            // 加密和HASH
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM_RSA_MD5);
            signature.initVerify(pubKey);
            // 验证签名是否正常
            signature.update(data);
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (NoSuchAlgorithmException e) {
            throw new BizRuntimeException("验证数字签名失败，加密算法不支持：" + publicKey);
        } catch (InvalidKeySpecException | InvalidKeyException e) {
            throw new BizRuntimeException("验证数字签名失败，无效公钥：" + publicKey);
        } catch (SignatureException e) {
            throw new BizRuntimeException("验证数字签名失败：" + publicKey);
        }
    }

    /**
     * 解密<br>
     * 用私钥解密加密后Base64编码数据
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(String data, String key) throws Exception {
        return decryptByPrivateKey(Base64.getDecoder().decode(data), key);
    }

    public static byte[] decryptByPrivateKey(byte[] data, String privKey) {
        // 对密钥解密
        byte[] keyBytes = Base64.getDecoder().decode(privKey);
        try {
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            throw new BizRuntimeException("公钥解密算法无效：" + privKey);
        } catch (InvalidKeySpecException | InvalidKeyException e) {
            throw new BizRuntimeException("无效公钥KEY：" + privKey);
        } catch (NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            throw new BizRuntimeException("使用公钥解密失败：" + privKey);
        }
    }

    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param pubKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String pubKey) {
        // 对密钥解密
        byte[] keyBytes = Base64.getDecoder().decode(pubKey);
        try {
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            throw new BizRuntimeException("公钥解密算法无效：" + pubKey);
        } catch (InvalidKeySpecException | InvalidKeyException e) {
            throw new BizRuntimeException("无效公钥KEY：" + pubKey);
        } catch (NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            throw new BizRuntimeException("使用公钥解密失败：" + pubKey);
        }
    }

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param pubKey
     * @return
     */
    public static byte[] encryptByPublicKey(String data, String pubKey) {
        // 对公钥解密
        byte[] keyBytes = Base64.getDecoder().decode(pubKey);
        try {
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            // 对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new BizRuntimeException("公钥加密算法无效：" + KEY_ALGORITHM_RSA);
        } catch (InvalidKeySpecException | InvalidKeyException e) {
            throw new BizRuntimeException("无效公钥KEY：" + pubKey);
        } catch (NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            throw new BizRuntimeException("使用公钥加密失败：" + pubKey);
        }
    }

    /**
     * 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param privKey
     * @return
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privKey) {
        // 对密钥解密
        try {
            byte[] keyBytes = Base64.getDecoder().decode(privKey);
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            throw new BizRuntimeException("私钥加密算法无效：" + KEY_ALGORITHM_RSA);
        } catch (InvalidKeySpecException | InvalidKeyException e) {
            throw new BizRuntimeException("无效私钥KEY：" + privKey);
        } catch (NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            throw new BizRuntimeException("使用私钥加密失败：" + privKey);
        }
    }

    /**
     * 获取（Base64编码后）私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Key> keyMap) {
        Key key = keyMap.get(PRIVATE_KEY_RSA);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    /**
     * 取得（Base64编码后）公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Key> keyMap) {
        Key key = keyMap.get(PUBLIC_KEY_RSA);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Key> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM_RSA);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String, Key> keyMap = new HashMap(2);
        keyMap.put(PUBLIC_KEY_RSA, keyPair.getPublic());// RSA公钥
        keyMap.put(PRIVATE_KEY_RSA, keyPair.getPrivate());// RSA私钥
        return keyMap;
    }

    public static void main(String[] args) throws Exception {

        // MD5
        System.out.println("MD5:" + md5("MD5"));
        // SHA1
        System.out.println("SHA1:" + sha1("SHA1"));
        String msg = "DES";
        String key = "12345678";
        System.out.println("DES");
        String desEncryptMsg = encryptWithDES(msg, key);
        System.out.println(desEncryptMsg);
        System.out.println(decryptWithDES(desEncryptMsg, key));

        Map<String, Key> keyMap = initKey();
        String publicKey = getPublicKey(keyMap);
        String privateKey = getPrivateKey(keyMap);
        System.out.println("------------publicKey-----------------------");
        System.out.println(publicKey);
        System.out.println("-------------privateKey----------------------");
        System.out.println(privateKey);

        System.out.println("--------------encryptByPrivateKey---123456----decryptByPublicKey--------------");
        byte[] encryptByPrivateKeyData = encryptByPrivateKey("123456".getBytes(), privateKey);
        System.out.println(new String(decryptByPublicKey(encryptByPrivateKeyData, publicKey)));

        System.out.println("--------------encryptByPublicKey---123456----decryptByPrivateKey--------------");
        byte[] encryptByPublicKeyData = encryptByPublicKey("123456", publicKey);
        System.out.println(new String(decryptByPrivateKey(encryptByPublicKeyData, privateKey)));

        System.out.println("---------sign---privateKey------CA把身份信息+pubKey签名----------------");
        // String sign = sign(encryptByPrivateKeyData, privateKey);
        String sign = sign(encryptByPrivateKeyData, privateKey);
        System.out.println(sign);
        System.out.println("------verify------publicKey-----浏览器获取证书签名信息sign校验签名--------保证公钥不被篡改----------");
        boolean verify = verify(encryptByPrivateKeyData, publicKey, sign);
        System.out.println("校验签名结果：" + verify);

    }

}
