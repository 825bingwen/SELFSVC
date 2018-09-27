/* ***************************************************************
 * Program ID: DESEncrypt.java 
 * Description: 
 * 1、实现了字符串DES加密
 * 2、支持汉字密钥
 * 3、支持汉字字符串加解密
 * Author ：Mingkai.Wang
 * Version：V1.0
 * Date: 2009-07-29
 * Modifi Log:
 * **************************************************************/
package com.gmcc.boss.selfsvc.util;

import java.security.SecureRandom;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESEncrypt
{
    // 默认密钥
    private static final String DEFAULT_KEY = "HuaWei_BOSS";
    
    // 编码格式
    private static final String CHARSET = "UTF-8";
    
    private Cipher encryptCipher = null;
    
    private Cipher decryptCipher = null;
    
    /**
     * 将byte数组转换为表示16进制值的字符串
     * 
     * @param arrB 需要转换的byte数组
     * @return 转换后的字符串
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出
     */
    private String byteArrToHexStr(byte[] arrB) throws Exception
    {
        int iLen = arrB.length;
        // 每个byte用十六进制两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++)
        {
            int intTmp = arrB[i];
            // 把负数转换为正数
            if (intTmp < 0)
            {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16)
            {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        //return sb.toString().toUpperCase();
        String value = sb.toString();                
        Locale locale = new Locale(value);            
        value = value.toUpperCase(locale);  
        return value;
    }
    
    /**
     * 将表示16进制值的字符串转换为byte数组
     * 
     * @param strIn 需要转换的字符串
     * @return 转换后的byte数组
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出
     */
    private byte[] hexStrToByteArr(String strIn) throws Exception
    {
        // strIn串不存在汉字，不需要对编码格式进行特殊指定
        byte[] arrB = strIn.getBytes(CHARSET);
        int iLen = arrB.length;
        
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        String strTmp = "";
        for (int i = 0; i < iLen; i = i + 2)
        {
            strTmp = new String(arrB, i, 2, CHARSET);
            arrOut[i / 2] = (byte)Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }
    
    /**
     * 默认构造方法，使用默认密钥
     * 
     * @throws Exception
     */
    public DESEncrypt() throws Exception
    {
        this(DEFAULT_KEY);
    }
    
    /**
     * 指定密钥构造方法
     * 
     * @param strKey 指定的密钥
     * @throws Exception
     */
    public DESEncrypt(String strKey) throws Exception
    {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(strKey.getBytes(CHARSET));
        
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        encryptCipher = Cipher.getInstance("DES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key, sr);
        
        decryptCipher = Cipher.getInstance("DES");
        decryptCipher.init(Cipher.DECRYPT_MODE, key, sr);
    }
    
    /**
     * 加密字节数组
     * 
     * @param arrB 需加密的字节数组
     * @return 加密后的字节数组
     * @throws Exception
     */
    public byte[] encrypt(byte[] arrB) throws Exception
    {
        return encryptCipher.doFinal(arrB);
    }
    
    /**
     * 加密字符串
     * 
     * @param strIn 需加密的字符串
     * @return 加密后的字符串
     * @throws Exception
     */
    public String encrypt(String strIn) throws Exception
    {
        return byteArrToHexStr(encrypt(strIn.getBytes(CHARSET)));
    }
    
    /**
     * 解密字节数组
     * 
     * @param arrB 需解密的字节数组
     * @return 解密后的字节数组
     * @throws Exception
     */
    public byte[] decrypt(byte[] arrB) throws Exception
    {
        return decryptCipher.doFinal(arrB);
    }
    
    /**
     * 解密字符串
     * 
     * @param strIn 需解密的字符串
     * @return 解密后的字符串
     * @throws Exception
     */
    public String decrypt(String strIn) throws Exception
    {
        return new String(decrypt(hexStrToByteArr(strIn)), CHARSET);
    }
    
    public static void main(String[] args)
    {
        try
        {
            String test = "ceshi测试,123【】";
            // DESEncrypt des = new DESEncrypt();// 默认密钥
            DESEncrypt des = new DESEncrypt();// 自定义密钥
            System.out.println("加密前的字符：" + test);
            String stren = des.encrypt(test);
            System.out.println("加密后的字符：" + stren);
            String strde = des.decrypt(stren);
            System.out.println("解密后的字符：" + strde);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
