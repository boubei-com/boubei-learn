package com.boubei.learn.jk.security;

/* ==================================================================   
 * Created [2006-6-19 15:04:59] by Jon.King 
 * ==================================================================  
 * Learn 
 * ================================================================== 
 * Copyright (c) Jon.King, 2006-2007 
 * ================================================================== 
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author Jon.King 2006-6-19
 * 
 * DSA数字签名 ：使用公、私钥进行非对称加密。
 */
public class CA {
    private final static Logger log = Logger.getLogger(CA.class);

    private final static String BASE_PATH = "target/";
    
    private String getPrivateKeyPath(String keySeed){ return BASE_PATH + keySeed + "_prikey.dat"; }
    private String getPublicKeyPath (String keySeed){ return BASE_PATH + keySeed + "_pubkey.dat"; }
    
    /**
     * 非对称加密DSA数字签名通过使用密钥对来实现。
     * 根据非对称加密的原理，分发公钥并用其加密，私钥保密，用于解密。
     * 而DSA的数字签名则是利用私钥加密，公钥解密，用以保证不可否认性和完整性。
     * 以DSA数字签名为例：首先需要生成一对密钥： 
     * 
     * @param keySeed   密钥种子
     */
    public void generateKeyPair(String keySeed) {
        try {
            KeyPairGenerator keygen = KeyPairGenerator.getInstance("DSA");
            keygen.initialize(512, new SecureRandom(keySeed.getBytes()));  // 密钥种子

            KeyPair keys = keygen.generateKeyPair();
            PublicKey publicKey = keys.getPublic();    //公钥
            PrivateKey privateKey = keys.getPrivate(); //私钥

            // 将生成的密钥（公钥以及私钥）序列化到文件
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(getPrivateKeyPath(keySeed)));
            out.writeObject(privateKey);
            out.close();

            out = new ObjectOutputStream(new FileOutputStream(getPublicKeyPath(keySeed)));
            out.writeObject(publicKey);
            out.close();
            log.info("成功生成 " + keySeed + " 的密钥对");
        } catch (Exception e) {
            log.error("用 DSA 生成公钥，密钥对时出错，Exception: " + e);
        }
    }
    
    //导入私钥
    private PrivateKey readPrivateKey(String keySeed) {
        PrivateKey prikey = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(getPrivateKeyPath(keySeed)));
            prikey = (PrivateKey) in.readObject();
            in.close();
        } catch (Exception e) {
            log.error("导入私钥时出错，Exception: " + e);
        }
        return prikey;
    }
    
    //导入公钥
    private PublicKey readPublicKey(String keySeed) {
        PublicKey publicKey = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(getPublicKeyPath(keySeed)));
            publicKey = (PublicKey) in.readObject();
            in.close();
        } catch (Exception e) {
            log.error("导入公钥时出错，Exception: " + e);
        }
        return publicKey;
    }
    
    //读入数据文件
    private Object[] readDataFile(String dataFile) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataFile));
            byte[] originalData = (byte[]) in.readObject();   //原始数据：明文
            byte[] signedData   = (byte[]) in.readObject();   //加密后的数据：密文
            in.close();
            
            return new Object[]{originalData, signedData};
        } catch (Exception e) {
            throw new RuntimeException("读入数据文件时出错，Exception: " + e);
        }
    }

    /**
     * 然后可以使用自己的私钥对数据签名：
     * @param password
     * @param key
     */
    public String signData(byte[] originalData, PrivateKey prikey) {
        String dataFile = BASE_PATH + System.currentTimeMillis() + ".dat";
        try {
            // 对数据签名
            Signature signature = Signature.getInstance("DSA");
            signature.initSign(prikey);
            signature.update(originalData);
            byte[] signedName = signature.sign(); //生成数据签名

            // 将"原始数据" 和 "签名"一起写入文件
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dataFile));
            out.writeObject(originalData);
            out.writeObject(signedName);
            out.close();
        } catch (Exception e) {
            log.error("用私钥对数据进行签名时出错，Exception: " + e);
        }
        return dataFile;
    }

    /**
     * 运行之后在当前路径产生一个加密后的文件，将 此文件和公钥 一起分发给接收者。 
     * 下面方法是使用公钥验证签名是否正常，运行之后如果密钥匹配无误，会显示加密的内容。
     * 如果公钥格式被破坏，会抛出异常。 
     */
    public void verifySign(byte[] originalData, byte[] signedName, PublicKey pubkey) {
        try {
            // 根据公钥验证数据的完整性
            Signature signCheck = Signature.getInstance("DSA");
            signCheck.initVerify(pubkey);
            signCheck.update(originalData);
            
            if (signCheck.verify(signedName)) {
            	log.info("使用公钥检测签名正确：" + new String(originalData));
            } else {
            	log.info("签名不正确");
            }
        } catch (Exception e) {
            log.error("用 DSA 验证签名时出错，Exception: " + e);
        }
    }
    
    
    /**
     * 利用DES算法对数据进行加密,双向
     * @param data
     */
    public void encodeData(String data){
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        String Algorithm = "DES"; // 定義 加密演算法,可用 DES,DESede,Blowfish
        try {
            // 產生金鑰
            KeyGenerator keygen = KeyGenerator.getInstance(Algorithm);
            SecretKey deskey = keygen.generateKey();

            log.info("加密前的訊息:" + data);
            log.info("加密前的二进制串:" + byte2hex(data.getBytes()));
            
            // 加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] cipherByte = c1.doFinal(data.getBytes());
            log.info("加密後的二进制串:" + byte2hex(cipherByte));
            
            // 解密
            Cipher c2 = Cipher.getInstance(Algorithm);
            c2.init(Cipher.DECRYPT_MODE, deskey);
            byte[] clearByte = c2.doFinal(cipherByte);
            log.info("解密後的二进制串:" + byte2hex(clearByte));
            log.info("解密後的訊息:" + (new String(clearByte)) + "\n");
        }catch (Exception e) {
            log.error("用 DES 算法加密文件时出错，Exception: " + e);
        }
    }
    // 二進位制轉字串
    public String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = java.lang.Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            
            if (n < b.length - 1)
                hs = hs + ":";
        }
        return hs.toUpperCase();
    }
    
    /**
     * 公开密钥密码体制的奠基人Diffie和Hellman所提出的 "指数密钥一致协议"(Exponential Key Agreement Protocol),
     * 该协议不要求别的安全性先决条件,允许两名用户在公开媒体上交换信息以生成"一致"的,可以共享的密钥。
     * @throws Exception
     */
    public void run() throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        log.info("Alice: 产生 DH 对 ...");
        KeyPairGenerator aliceKpairGen = KeyPairGenerator.getInstance("DH");
        aliceKpairGen.initialize(512);
        KeyPair aliceKpair = aliceKpairGen.generateKeyPair(); // 生成时间长
        
        // Alice生成公共密钥 AlicePubKeyEnc 并发送给Bob, 比如用文件方式, socket.....
        byte[] AlicePubKeyEnc = aliceKpair.getPublic().getEncoded();
        
        // Bob接收到Alice的编码后的公钥,将其解码
        KeyFactory bobKeyFac = KeyFactory.getInstance("DH");
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(AlicePubKeyEnc);
        PublicKey alicePubKey = bobKeyFac.generatePublic(x509KeySpec);
        log.info("Alice公钥Bob解码成功");
        
        // Bob必须用相同的参数初始化的他的DH KEY对,所以要从Alice发给他的公开密钥,
        // 中读出参数,再用这个参数初始化他的 DH key对从AlicePubKye中取Alice初始化时用的参数
        DHParameterSpec dhParamSpec = ((DHPublicKey) alicePubKey).getParams();
        KeyPairGenerator bobKpairGen = KeyPairGenerator.getInstance("DH");
       bobKpairGen.initialize(dhParamSpec);
        KeyPair BobKpair = bobKpairGen.generateKeyPair();
        log.info("Bob: 生成 DH key 对成功");
        
        KeyAgreement BobKeyAgree = KeyAgreement.getInstance("DH");
        BobKeyAgree.init(BobKpair.getPrivate());
        log.info("Bob: 初始化本地key成功");
        
        // Bob 生成本地的密钥 BobDesKey
        BobKeyAgree.doPhase(alicePubKey, true);
        SecretKey BobDesKey = BobKeyAgree.generateSecret("DES");
        log.info("Bob: 用Alice的公钥定位本地key,生成本地DES密钥成功");
        
        // Bob生成公共密钥 BobPubKeyEnc 并发送给Alice, 比如用文件方式, socket....., 使其生成本地密钥
        byte[] BobPubKeyEnc = BobKpair.getPublic().getEncoded();
        log.info("Bob向Alice发送公钥");
        
        // Alice接收到 BobPubKeyEnc后生成BobPubKey 再进行定位,使AliceKeyAgree定位在BobPubKey
        KeyFactory aliceKeyFac = KeyFactory.getInstance("DH");
        x509KeySpec = new X509EncodedKeySpec(BobPubKeyEnc);
        PublicKey bobPubKey = aliceKeyFac.generatePublic(x509KeySpec);
        log.info("Alice接收Bob公钥并解码成功");
        
        KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("DH");
        aliceKeyAgree.init(aliceKpair.getPrivate());
        log.info("ALICE: 初始化本地key成功");
        aliceKeyAgree.doPhase(bobPubKey, true);
        
        // alice 生成本地的密钥 aliceDesKey
        SecretKey aliceDesKey = aliceKeyAgree.generateSecret("DES");
        log.info("ALICE: 用Bob的公钥定位本地key,并生成本地DES密钥");
        if (aliceDesKey.equals(BobDesKey))
        log.info("Alice和Bob的密钥相同");
        
        // 现在Alice和Bob的本地的deskey是相同的所以,完全可以进行发送加密,接收后解密,达到安全通道的的目的
        
        /* Bob用BobDesKey密钥加密信息 */
        Cipher BobCipher = Cipher.getInstance("DES");
        BobCipher.init(Cipher.ENCRYPT_MODE, BobDesKey);
        String Bobinfo = "这是Bob的机密信息";
        log.info("Bob加密前原文:" + Bobinfo);
        byte[] cleartext = Bobinfo.getBytes();
        byte[] ciphertext = BobCipher.doFinal(cleartext);
        
        /* Alice用aliceDesKey密钥解密 */
        Cipher aliceCipher = Cipher.getInstance("DES");
        aliceCipher.init(Cipher.DECRYPT_MODE, aliceDesKey);
        byte[] recovered = aliceCipher.doFinal(ciphertext);
        log.info("alice解密Bob的信息:" + (new String(recovered)));
        if (!java.util.Arrays.equals(cleartext, recovered))
            throw new Exception("解密后与原文信息不同");
        log.info("解密后相同");
    }


    public static void main(String[] args) throws Exception {
        CA ca = new CA();
        log.setLevel(Level.DEBUG);
        String data = "花自飘零水自流";
        
        ca.generateKeyPair("Bob.Son"); //给Bob颁发证书
        //ca.generateKeyPair("Jon.King");//给Jon颁发证书
        
        //Bob用自己的私钥加密数据（给数据签名），签名后将文件和签名一起发给接收人Jon
        String dataFile = ca.signData(data.getBytes(), ca.readPrivateKey("Bob.Son"));
        
        Object[] datas = ca.readDataFile(dataFile);
        //Jon用Bob的发布出去的公钥验证数据的签名（看数据是否是Bob签名）
        ca.verifySign((byte[])datas[0], (byte[])datas[1], ca.readPublicKey("Bob.Son"));
        
        ca.encodeData(data);
        
        ca.run();
    }
}

