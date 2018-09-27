package com.gmcc.boss.selfsvc.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.util.CryptUtil;

public class EncryptDecryptUtil 
{
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
	//add begin m00227318 2012/11/15 V200R003C12L11 OR_huawei_201211_242
    //private static Logger log = Logger.getLogger(EncryptDecryptUtil.class);
	private static Log log = LogFactory.getLog(EncryptDecryptUtil.class);
    //add end m00227318 2012/11/15 V200R003C12L11 OR_huawei_201211_242
	// modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
 
    /**
     * 对口令和敏感信息加密
     * @param oldpwd 待加密的字符串
     * @return 加密后的字符串
     * @remark create m00227318 2012/11/13 V200R003C12L11 OR_huawei_201211_242
     */
    public static String encryptAesPwd (String oldpwd)
    {
        //加密标识，0表示不加密，1表示加密
        String encryptFlag = (String)PublicCache.getInstance().getCachedData(Constants.SH_ENCRYPT_FLAG);
        
        //加密算法。0：AES/ECB/PKCS5Padding；1：AES/CBC/PKCS5Padding
        String algorithmType = (String)PublicCache.getInstance().getCachedData(Constants.SH_ALGORITHM_TYPE);
        
        //密钥
        String encryptKey = (String)PublicCache.getInstance().getCachedData(Constants.SH_ENCRYPT_KEY); 
        
        //初始化向量
        String encryptIv = (String)PublicCache.getInstance().getCachedData(Constants.SH_ENCRYPT_IV);
        
        //加密后的字符串
        String encrAesPwd = oldpwd;
        
        try
        {
	        if (null != oldpwd && !"".equals(oldpwd))
	        {
	        	//1，加密
			    if ("1".equals(encryptFlag))
			    {
			        if (null == algorithmType || "".equals(algorithmType))
			        {
			        	log.error("参数表中SH_ALGORITHM_TYPE的值不能为空！");
			        }
			        //采用AES/ECB/PKCS5Padding方式加密
			        else if ("0".equals(algorithmType))
			        {
			        	if (null == encryptKey || "".equals(encryptKey))
			        	{
			        		log.error("参数表中SH_ENCRYPT_KEY的值不能为空！");
			        	}
			        	else 
			        	{
			        		CryptUtil util = new CryptUtil(encryptKey);
			        		encrAesPwd = util.encrypt(oldpwd);
			        	}	
			        }
			        //采用AES/CBC/PKCS5Padding方式加密
			        else if ("1".equals(algorithmType))
			        {
			            // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
			        	//if (null == encryptKey || "".equals(encryptKey) || null == encryptIv || "".equals(encryptIv))
			            if (isNull(encryptKey, encryptIv))
		                // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
			        	{
			        		log.error("参数表中SH_ENCRYPT_KEY和SH_ENCRYPT_IV的值均不能为空！");
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
	        log.error("ftp密码加密失败！", e);
	     }
	     
	     return encrAesPwd;
    }
    
    /**
     * 判断是否为空
     * <功能详细描述>
     * @param encryptKey
     * @param encryptIv
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNull(String encryptKey, String encryptIv)
    {
        return null == encryptKey || "".equals(encryptKey) || null == encryptIv || "".equals(encryptIv);
    }
    
    /**
     * 将从数据库中取得的口令和敏感信息解密
     * @param oldpwd 待解密的字符串
     * @return 解密后的字符串
     * @remark create m00227318 2012/11/13 V200R003C12L11 OR_huawei_201211_242
     */
    public static String decryptAesPwd (String oldpwd)
    {
        //加密标识，0表示不加密，1表示加密
        String encryptFlag = (String)PublicCache.getInstance().getCachedData(Constants.SH_ENCRYPT_FLAG);
        
        //加密算法。0：AES/ECB/PKCS5Padding；1：AES/CBC/PKCS5Padding
        String algorithmType = (String)PublicCache.getInstance().getCachedData(Constants.SH_ALGORITHM_TYPE);
        
        //密钥
        String encryptKey = (String)PublicCache.getInstance().getCachedData(Constants.SH_ENCRYPT_KEY); 
        
        //初始化向量
        String encryptIv = (String)PublicCache.getInstance().getCachedData(Constants.SH_ENCRYPT_IV);
        
        //解密后的字符串
        String decrAesPwd = oldpwd;
        
        try
        {
        	if (null != oldpwd && !"".equals(oldpwd))
        	{
        		//1，需解密
			    if ("1".equals(encryptFlag))
			    {
			    	if (null == algorithmType || "".equals(algorithmType))
			        {
			        	log.error("参数表中SH_ALGORITHM_TYPE的值不能为空！");
			        }
			        //采用AES/ECB/PKCS5Padding方式解密
			        else if ("0".equals(algorithmType))
			        {
			        	if (null == encryptKey || "".equals(encryptKey))
			        	{
			        		log.error("参数表中SH_ENCRYPT_KEY的值不能为空！");
			        	}
			        	else 
			        	{
			        		CryptUtil util = new CryptUtil(encryptKey);
			        		decrAesPwd = util.decrypt(oldpwd);
			        	}	
			        }
			    	//采用AES/CBC/PKCS5Padding方式解密
			        else if ("1".equals(algorithmType))
			        {
			            // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
			        	//if (null == encryptKey || "".equals(encryptKey) || null == encryptIv || "".equals(encryptIv))
			        	if (isNull(encryptKey, encryptIv))
		                // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
			        	{
			        		log.error("参数表中SH_ENCRYPT_KEY和SH_ENCRYPT_IV的值均不能为空！");
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
        	log.error("ftp密码解密失败！", e);
        }
        
        return decrAesPwd;
    }
	
}
