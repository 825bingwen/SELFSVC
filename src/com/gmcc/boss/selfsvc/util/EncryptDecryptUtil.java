package com.gmcc.boss.selfsvc.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.util.CryptUtil;

public class EncryptDecryptUtil 
{
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
	//add begin m00227318 2012/11/15 V200R003C12L11 OR_huawei_201211_242
    //private static Logger log = Logger.getLogger(EncryptDecryptUtil.class);
	private static Log log = LogFactory.getLog(EncryptDecryptUtil.class);
    //add end m00227318 2012/11/15 V200R003C12L11 OR_huawei_201211_242
	// modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
 
    /**
     * �Կ����������Ϣ����
     * @param oldpwd �����ܵ��ַ���
     * @return ���ܺ���ַ���
     * @remark create m00227318 2012/11/13 V200R003C12L11 OR_huawei_201211_242
     */
    public static String encryptAesPwd (String oldpwd)
    {
        //���ܱ�ʶ��0��ʾ�����ܣ�1��ʾ����
        String encryptFlag = (String)PublicCache.getInstance().getCachedData(Constants.SH_ENCRYPT_FLAG);
        
        //�����㷨��0��AES/ECB/PKCS5Padding��1��AES/CBC/PKCS5Padding
        String algorithmType = (String)PublicCache.getInstance().getCachedData(Constants.SH_ALGORITHM_TYPE);
        
        //��Կ
        String encryptKey = (String)PublicCache.getInstance().getCachedData(Constants.SH_ENCRYPT_KEY); 
        
        //��ʼ������
        String encryptIv = (String)PublicCache.getInstance().getCachedData(Constants.SH_ENCRYPT_IV);
        
        //���ܺ���ַ���
        String encrAesPwd = oldpwd;
        
        try
        {
	        if (null != oldpwd && !"".equals(oldpwd))
	        {
	        	//1������
			    if ("1".equals(encryptFlag))
			    {
			        if (null == algorithmType || "".equals(algorithmType))
			        {
			        	log.error("��������SH_ALGORITHM_TYPE��ֵ����Ϊ�գ�");
			        }
			        //����AES/ECB/PKCS5Padding��ʽ����
			        else if ("0".equals(algorithmType))
			        {
			        	if (null == encryptKey || "".equals(encryptKey))
			        	{
			        		log.error("��������SH_ENCRYPT_KEY��ֵ����Ϊ�գ�");
			        	}
			        	else 
			        	{
			        		CryptUtil util = new CryptUtil(encryptKey);
			        		encrAesPwd = util.encrypt(oldpwd);
			        	}	
			        }
			        //����AES/CBC/PKCS5Padding��ʽ����
			        else if ("1".equals(algorithmType))
			        {
			            // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
			        	//if (null == encryptKey || "".equals(encryptKey) || null == encryptIv || "".equals(encryptIv))
			            if (isNull(encryptKey, encryptIv))
		                // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
			        	{
			        		log.error("��������SH_ENCRYPT_KEY��SH_ENCRYPT_IV��ֵ������Ϊ�գ�");
			        	}
			        	else
			        	{
			        		CryptUtil util = new CryptUtil(encryptIv, encryptKey);
			        		encrAesPwd = util.encrypt(oldpwd);
			        	}
			        }
			     }
	         }
         }
	     catch (Exception e)
	     {
	        log.error("ftp�������ʧ�ܣ�", e);
	     }
	     
	     return encrAesPwd;
    }
    
    /**
     * �ж��Ƿ�Ϊ��
     * <������ϸ����>
     * @param encryptKey
     * @param encryptIv
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public static boolean isNull(String encryptKey, String encryptIv)
    {
        return null == encryptKey || "".equals(encryptKey) || null == encryptIv || "".equals(encryptIv);
    }
    
    /**
     * �������ݿ���ȡ�õĿ����������Ϣ����
     * @param oldpwd �����ܵ��ַ���
     * @return ���ܺ���ַ���
     * @remark create m00227318 2012/11/13 V200R003C12L11 OR_huawei_201211_242
     */
    public static String decryptAesPwd (String oldpwd)
    {
        //���ܱ�ʶ��0��ʾ�����ܣ�1��ʾ����
        String encryptFlag = (String)PublicCache.getInstance().getCachedData(Constants.SH_ENCRYPT_FLAG);
        
        //�����㷨��0��AES/ECB/PKCS5Padding��1��AES/CBC/PKCS5Padding
        String algorithmType = (String)PublicCache.getInstance().getCachedData(Constants.SH_ALGORITHM_TYPE);
        
        //��Կ
        String encryptKey = (String)PublicCache.getInstance().getCachedData(Constants.SH_ENCRYPT_KEY); 
        
        //��ʼ������
        String encryptIv = (String)PublicCache.getInstance().getCachedData(Constants.SH_ENCRYPT_IV);
        
        //���ܺ���ַ���
        String decrAesPwd = oldpwd;
        
        try
        {
        	if (null != oldpwd && !"".equals(oldpwd))
        	{
        		//1�������
			    if ("1".equals(encryptFlag))
			    {
			    	if (null == algorithmType || "".equals(algorithmType))
			        {
			        	log.error("��������SH_ALGORITHM_TYPE��ֵ����Ϊ�գ�");
			        }
			        //����AES/ECB/PKCS5Padding��ʽ����
			        else if ("0".equals(algorithmType))
			        {
			        	if (null == encryptKey || "".equals(encryptKey))
			        	{
			        		log.error("��������SH_ENCRYPT_KEY��ֵ����Ϊ�գ�");
			        	}
			        	else 
			        	{
			        		CryptUtil util = new CryptUtil(encryptKey);
			        		decrAesPwd = util.decrypt(oldpwd);
			        	}	
			        }
			    	//����AES/CBC/PKCS5Padding��ʽ����
			        else if ("1".equals(algorithmType))
			        {
			            // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
			        	//if (null == encryptKey || "".equals(encryptKey) || null == encryptIv || "".equals(encryptIv))
			        	if (isNull(encryptKey, encryptIv))
		                // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
			        	{
			        		log.error("��������SH_ENCRYPT_KEY��SH_ENCRYPT_IV��ֵ������Ϊ�գ�");
			        	}
			        	else
			        	{
			        		CryptUtil util = new CryptUtil(encryptIv, encryptKey);
			        		decrAesPwd = util.decrypt(oldpwd);
			        	}
			        }
			    }
        	}
        }
        catch (Exception e)
        {
        	log.error("ftp�������ʧ�ܣ�", e);
        }
        
        return decrAesPwd;
    }
	
}
