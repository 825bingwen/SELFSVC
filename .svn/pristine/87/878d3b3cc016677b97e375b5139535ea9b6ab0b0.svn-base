/*
* @filename: DESedeEncryptNX
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  三DES加密，宁夏专用
* @author: g00140516
* @de:  2012/11/29 
* @description: 新增
* @remark: create g00140516 2012/11/29 R003C12L10n01 OR_NX_201209_589
*/
package com.gmcc.boss.selfsvc.util;

import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.cache.PublicCache;

/**
 * 
 * DESede加密
 * 
 * @author  g00140516
 * @version  1.0, 2012/03/21
 * @see  
 * @since 
 */
public class DESedeEncryptNX
{
    private static Log logger = LogFactory.getLog(DESedeEncryptNX.class);
    
    /**
     * 编码格式
     */    
    private final String CHARSET = "UTF-8";
    
    /**
     * 加密算法
     */
    public final String KEY_ALGORITHM = "DESede";
    
    private static Cipher encryptCipher = null;
    
    private static Cipher decryptCipher = null;
    
    private static DESedeEncryptNX instance = null;
    
    /**
     * 返回类的实例
     * 
     * @return
     */
    public static synchronized DESedeEncryptNX getInstance()
    {
        if (instance == null)
        {
            instance = new DESedeEncryptNX();
        }
        
        return instance;
    }
    
    /**
     * 私有构造方法
     * 
     * @throws Exception
     */
    private DESedeEncryptNX()
    {
        try
        {
            String encryptKey = (String) PublicCache.getInstance().getCachedData("ENCRYPT_KEY");
            if (null == encryptKey || "".equalsIgnoreCase(encryptKey.trim()))
            {
                encryptKey = "BOSS_encrypt_defaultkey1";
            }
            
            // 生成密钥
            SecretKey key = new SecretKeySpec(encryptKey.getBytes(CHARSET), KEY_ALGORITHM);
            
            // 转换成16进制，需配置在统一接口平台的表中
            byte[] buffer = key.getEncoded();
            if (logger.isInfoEnabled())
            {
                logger.info("48位密钥：" + byteArrToHexStr(buffer));
            }
            
            encryptCipher = Cipher.getInstance(KEY_ALGORITHM);
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            
            decryptCipher = Cipher.getInstance(KEY_ALGORITHM);
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        }
        catch (Exception e)
        {
            logger.error("三DES加密失败：", e);
        }        
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
        if (null == strIn || "".equals(strIn))
        {
        	return "";
        }
        StringBuffer sbuf = new StringBuffer(strIn);
        int strLength = strIn.length();
        
        while (strLength < 8)
        {
        	sbuf.append("\0");
        	strLength++;
        }
        strIn = sbuf.toString();
    	return byteArrToHexStr(encrypt(strIn.getBytes(CHARSET)));
    }
    
    /**
     * 将byte数组转换为表示16进制值的字符串
     * 
     * @param arrB 需要转换的byte数组
     * @return 转换后的字符串
     * @throws Exception
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
    
    /**
     * 将表示16进制值的字符串转换为byte数组
     * 
     * @param strIn 需要转换的字符串
     * @return 转换后的byte数组
     * @throws Exception
     */
    private byte[] hexStrToByteArr(String strIn) throws Exception
    {
        byte[] arrB = strIn.getBytes(CHARSET);
        int iLen = arrB.length;
        
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        
        String strTmp = "";
        for (int i = 0; i < iLen; i = i + 2)
        {
            strTmp = new String(arrB, i, 2, CHARSET);
            
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        
        return arrOut;
    }

    public static void main(String[] args)
    {
        try
        {
            String test = "123123";
            
            DESedeEncryptNX des = DESedeEncryptNX.getInstance();            
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
