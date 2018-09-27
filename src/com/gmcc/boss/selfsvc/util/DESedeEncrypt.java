/*
* @filename: DESedeEncrypt
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  DESede����
* @author: g00140516
* @de:  2012/03/21 
* @description: ����
* @remark: create g00140516 2012/03/21 R003C12L02n01 OR_huawei_201112_953
*/
package com.gmcc.boss.selfsvc.util;

import java.security.SecureRandom;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.cache.PublicCache;

/**
 * 
 * DESede����
 * 
 * @author  g00140516
 * @version  1.0, 2012/03/21
 * @see  
 * @since 
 */
public class DESedeEncrypt
{
    private static Log logger = LogFactory.getLog(DESedeEncrypt.class);
    
    /**
     * ���ӣ������á����г���Ӧͳһ
     */
    private static String encryptKey = "";
    
    /**
     * �����ʽ
     */    
    private final String CHARSET = "UTF-8";
    
    /**
     * �����㷨
     */
    public final String KEY_ALGORITHM = "DESede";
    
    private Cipher encryptCipher = null;
    
    private Cipher decryptCipher = null;
    
    /**
     * ���췽��
     * 
     * @throws Exception
     */
    public DESedeEncrypt() throws Exception
    {
        this(encryptKey);
    }
    
    /**
     * ���췽��
     * 
     * @param strKey ָ������
     * @throws Exception
     */
    public DESedeEncrypt(String strKey) throws Exception
    {    	
    	// ������Կ
        KeyGenerator generator = KeyGenerator.getInstance(KEY_ALGORITHM);
        generator.init(168, new SecureRandom(strKey.getBytes(CHARSET)));        
        SecretKey key =  generator.generateKey();

        // ת����16���ƣ���������ͳһ�ӿ�ƽ̨�ı���
        byte[] buffer = key.getEncoded();
        if (logger.isInfoEnabled())
        {
            logger.info("48λ��Կ��" + byteArrToHexStr(buffer));
        }
        
        encryptCipher = Cipher.getInstance(KEY_ALGORITHM);
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        
        decryptCipher = Cipher.getInstance(KEY_ALGORITHM);
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }
    
    /**
     * �����ֽ�����
     * 
     * @param arrB ����ܵ��ֽ�����
     * @return ���ܺ���ֽ�����
     * @throws Exception
     */
    public byte[] encrypt(byte[] arrB) throws Exception
    {
        return encryptCipher.doFinal(arrB);
    }
    
    /**
     * �����ַ���
     * 
     * @param strIn ����ܵ��ַ���
     * @return ���ܺ���ַ���
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
     * ��byte����ת��Ϊ��ʾ16����ֵ���ַ���
     * 
     * @param arrB ��Ҫת����byte����
     * @return ת������ַ���
     * @throws Exception
     */
    private String byteArrToHexStr(byte[] arrB) throws Exception
    {
        int iLen = arrB.length;
        
        // ÿ��byte��ʮ�����������ַ����ܱ�ʾ�������ַ����ĳ��������鳤�ȵ�����
        StringBuffer sb = new StringBuffer(iLen * 2);
        
        for (int i = 0; i < iLen; i++)
        {
            int intTmp = arrB[i];
            
            // �Ѹ���ת��Ϊ����
            if (intTmp < 0)
            {
                intTmp = intTmp + 256;
            }
            
            // С��0F������Ҫ��ǰ�油0
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
     * �����ֽ�����
     * 
     * @param arrB ����ܵ��ֽ�����
     * @return ���ܺ���ֽ�����
     * @throws Exception
     */
    public byte[] decrypt(byte[] arrB) throws Exception
    {
        return decryptCipher.doFinal(arrB);
    }
    
    /**
     * �����ַ���
     * 
     * @param strIn ����ܵ��ַ���
     * @return ���ܺ���ַ���
     * @throws Exception
     * @modify by lKF60882 OR_huawei_201703_629  ��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1 2017-4-13
     */
    public String decrypt(String strIn) throws Exception
    {
        String desStr = new String(decrypt(hexStrToByteArr(strIn)), CHARSET);
        
        // ������ʱ�������ӵ��ַ���ȥ��
        StringBuffer sbuf = new StringBuffer(desStr);
        int strLength = sbuf.length();
        while (sbuf.toString().endsWith("\0"))
        {
            sbuf.deleteCharAt(strLength - 1);
            strLength--;
        }
        desStr = sbuf.toString();
        
        return desStr;
    }
    
    /**
     * ����ʾ16����ֵ���ַ���ת��Ϊbyte����
     * 
     * @param strIn ��Ҫת�����ַ���
     * @return ת�����byte����
     * @throws Exception
     */
    private byte[] hexStrToByteArr(String strIn) throws Exception
    {
        byte[] arrB = strIn.getBytes(CHARSET);
        int iLen = arrB.length;
        
        // �����ַ���ʾһ���ֽڣ������ֽ����鳤�����ַ������ȳ���2
        byte[] arrOut = new byte[iLen / 2];
        
        String strTmp = "";
        for (int i = 0; i < iLen; i = i + 2)
        {
            strTmp = new String(arrB, i, 2, CHARSET);
            
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        
        return arrOut;
    }
    
    static
    {
    	encryptKey = (String) PublicCache.getInstance().getCachedData("ENCRYPT_KEY");
        if (null == encryptKey || "".equalsIgnoreCase(encryptKey.trim()))
        {
        	encryptKey = "HuaWei_BOSS_encrypt_defaultkey";
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("��Կ���ӣ�" + encryptKey);
        }        
    }
    
    public static void main(String[] args)
    {
        try
        {
            String test = "123123";
            System.out.println("����ǰ���ַ���" + test);
            
            DESedeEncrypt des = new DESedeEncrypt();            
            String stren = des.encrypt(test);
            System.out.println("���ܺ���ַ���" + stren);
            
            String strde = des.decrypt(stren);
            System.out.println("���ܺ���ַ���" + strde);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}