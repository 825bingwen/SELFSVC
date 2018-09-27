package com.gmcc.boss.selfsvc.util;

import java.security.SecureRandom;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

/**
 * use bouncycastle to AES encrypt
 */
public class CryptUtil
{
    
    public static final int ALGORITHM_AES_ECB = 0;
    public static final int ALGORITHM_AES_CBC = 1;  
    public static final int ALGORITHM_BLOWFISH_ECB = 2;
    public static final int ALGORITHM_BLOWFISH_CBC = 3;          
    
    private static final int MIN_ALGORITHM_TYPE = 0;
    private static final int MAX_ALGORITHM_TYPE = 3;
        
    static final String[] CIPHER_TYPE = {"AES/ECB/PKCS5Padding","AES/CBC/PKCS5Padding","Blowfish/ECB/PKCS5Padding","Blowfish/CBC/PKCS5Padding"};
    
    public static final int STR_KEY_LENGTH = 16;
    static final int[] KEY_SIZE = {128, 192, 256};
    
    public static final int ENCODE_HEX = 16;
    public static final int ENCODE_BASE64 = 64;
    
    private static final int ALGORITHM_INVALID = -1;
    private static final String[] KEY_TYPE = {"AES", "Blowfish"};
        
    private int algorithmType = 0;
    
    private int keyType = 0;
    private SecretKey key = null;    
    private IvParameterSpec iv = null;
    
    private static BouncyCastleProvider provider = new BouncyCastleProvider();

    /**
     * 构造一个默认支持AES/ECB/PKCS5Padding加密模式的实例，使用的加密密钥字节形式为keyBytes
     */
    public CryptUtil(byte[] keyBytes)
    {
        this(0, null, keyBytes);
    }
    
    /**
     * 构造一个默认支持AES/ECB/PKCS5Padding加密模式的实例，使用的加密密钥为Base64编码字符串keyStr
     */
    public CryptUtil(String keyStr)
    {
        this(0, null, decodeBase64(keyStr));
    }
    
    /**
     * 构造一个默认支持AES/CBC/PKCS5Padding加密模式的实例，使用的加密初始向量字节形式为ivBytes，加密密钥字节形式为keyBytes
     */
    public CryptUtil(byte[] ivBytes, byte[] keyBytes)
    {
        this(1, ivBytes, keyBytes);
    }
    
    /**
     * 构造一个默认支持AES/CBC/PKCS5Padding加密模式的实例，使用的加密初始向量Base64编码字符串为ivStr，加密密钥为Base64编码字符串keyStr
     */
    public CryptUtil(String ivStr, String keyStr)
    {
        this(1, decodeBase64(ivStr), decodeBase64(keyStr));
    }
    
    /**
     * 构造一个指定加密模式的实例，使用的加密初始向量字节形式为ivBytes，加密密钥字节形式为keyBytes
     */
    public CryptUtil(int algorithmType, byte[] ivBytes, byte[] keyBytes)
    {
        if(algorithmType>=MIN_ALGORITHM_TYPE && algorithmType<=MAX_ALGORITHM_TYPE)
        {        
            this.algorithmType = algorithmType;
            
            //AES:keyType=0;BlowFish：KeyType=1
            switch(algorithmType)
            {
                case ALGORITHM_AES_ECB:
                case ALGORITHM_AES_CBC:
                    keyType = 0;
                    break;
                case ALGORITHM_BLOWFISH_ECB:
                case ALGORITHM_BLOWFISH_CBC:
                    keyType = 1;
                    break;
                default:
                    algorithmType = ALGORITHM_INVALID;
            }        
            
            //实例化AES密钥材料，密钥转换
            key = new SecretKeySpec(keyBytes, KEY_TYPE[keyType]);
            
            //CBC模式生加密初始向量
            if((algorithmType==ALGORITHM_BLOWFISH_CBC || algorithmType==ALGORITHM_AES_CBC) && null!=ivBytes)
            {
                iv = new IvParameterSpec(ivBytes);
            }
        }else{
            algorithmType = ALGORITHM_INVALID;
        }       
    }   
    
    /**
     * 加密数据并编码为Base64形式
     * 使用当前实例对clearStr进行加密，加密后的密文转换为Base64编码的字符串
     * @param clearStr
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String encrypt(String clearStr) throws Exception
    {
        return encrypt(clearStr, 64);
    }
    
    /**
     * 加密数据并以指定形式编码
     * 使用当前实例对clearStr进行加密，
     * 加密后的密文转换为Base64编码的字符串（outputEncoder为64时）或16进制字符串（outputEncoder为16时）
     * @param clearStr
     * @param outputEncoder
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String encrypt(String clearStr, int outputEncoder) throws Exception
    {
        if(algorithmType==ALGORITHM_INVALID)
        {
            throw new Exception("invalid algorithm type, must between " + MIN_ALGORITHM_TYPE + " and " + MAX_ALGORITHM_TYPE);
        }
        
        if(outputEncoder != ENCODE_HEX && outputEncoder !=ENCODE_BASE64)
        {
            throw new Exception("invalid outputEncoder, must be " + ENCODE_HEX + " or " + ENCODE_BASE64);
        }
            
            //实例化
            Cipher in = Cipher.getInstance(CIPHER_TYPE[algorithmType], provider);            
            
            //CBC工作模式下，初始化
            if((algorithmType==ALGORITHM_BLOWFISH_CBC || algorithmType==ALGORITHM_AES_CBC) && null!=iv)
            {             
                in.init(Cipher.ENCRYPT_MODE, key, iv);     
            }
            //ECB工作模式下，初始化
            else
            {
                in.init(Cipher.ENCRYPT_MODE, key);
            }
            
            //执行加密操作
            byte[] enc = in.doFinal(clearStr.getBytes());
            
            //加密后密文转换为何种格式
            if(outputEncoder == ENCODE_BASE64)
            {
                return new String(Base64.encode(enc));
            }else
            {
                return byte2hexString(enc);
            }                   
    }
    
    /**
     * 对Base64编码后的加密串进行解密
     * 使用当前实例对Base64编码的字符串encStr进行解密，输出为普通明文
     * @param encStr
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String decrypt(String encStr) throws Exception
    {
        return decrypt(encStr, 64);
    }
    
    /**
     * 指定加密串的编码进行解密
     * 使用当前实例对encStr进行解密，输出为普通明文。
     * encStr可能是Base64编码的字符串（inputEncoder为64时）或16进制字符串（inputEncoder为16时）
     * @param encStr
     * @param inputEncoder
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String decrypt(String encStr, int inputEncoder) throws Exception
    {
        if(algorithmType==ALGORITHM_INVALID)
        {
            throw new Exception("invalid algorithm type, must between " + MIN_ALGORITHM_TYPE + " and " + MAX_ALGORITHM_TYPE);
        }
        
        if(inputEncoder != ENCODE_HEX && inputEncoder !=ENCODE_BASE64)
        {
            throw new Exception("invalid inputEncoder, must be " + ENCODE_HEX + " or " + ENCODE_BASE64);
        }
        
            //实例化
            Cipher in = Cipher.getInstance(CIPHER_TYPE[algorithmType], provider);  
            
            //CBC工作模式下，初始化
            if(algorithmType==ALGORITHM_BLOWFISH_CBC || algorithmType==ALGORITHM_AES_CBC && null!=iv)
            {              
                in.init(Cipher.DECRYPT_MODE, key, iv);     
            }
            //ECB工作模式下，初始化
            else
            {
                in.init(Cipher.DECRYPT_MODE, key);
            }  
            
            byte[] data = null;
            
            //先对密文进行对应处理
            if(inputEncoder == ENCODE_BASE64)
            {
                data = Base64.decode(encStr.getBytes());
            }
            else
            {
                data = hex2Bin(encStr);
            }
            
            //执行解密
            byte[] dec = in.doFinal(data);
            
            return new String(dec);               
    }  
    
    /**
     * 生成默认长度密钥
     * 生成一个指定加密算法的128位密钥，keyType必须是KEY_TYPE常量数组中的一个值。
     * KEY_TYPE中目前有AES、Blowfish
     * @param keyType
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static SecretKey generateKey(String keyType) throws Exception {  
        return generateKey(keyType, 128);
    }
    
    /**
     * 生成指定长度密钥
     * 生成一个指定加密算法的keySize位密钥，keyType必须是KEY_TYPE常量数组中的一个值。
     * KEY_TYPE中目前有AES、Blowfish；
     * keySize默认只允许128，更大长度（192/256）需要修改JRE设置
     * @param keyType
     * @param keySize
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static SecretKey generateKey(String keyType, int keySize) throws Exception {       
            boolean isValid = false;
            for(String s: KEY_TYPE)
            {
                if(s.equals(keyType))
                {
                    isValid = true;
                    break;
                }
            }
            
            if(isValid)
            {            
                for(int i: KEY_SIZE)
                {
                    if(i==keySize)
                    {
                        isValid = true;
                        break;
                    }
                }
            }
            
            if(isValid)
            { 
                KeyGenerator keyGen = KeyGenerator.getInstance(keyType,
                        provider);
                final int KEY_SIZE = keySize;
                keyGen.init(KEY_SIZE, new SecureRandom());
                SecretKey key = keyGen.generateKey();
                
                return key;
            }else
            {
                throw new Exception("Invalid KeyType: " + keyType);
            }
    }
     
   /**
    * 生成默认长度密钥字符串
    * 生成一个指定加密算法的128位密钥的Base64字符串形式，keyType必须是KEY_TYPE常量数组中的一个值。
    * KEY_TYPE中目前有AES、Blowfish
    * @param keyType
    * @return
    * @throws Exception
    * @see [类、类#方法、类#成员]
    */
    public static String generateKeyStr(String keyType) throws Exception {  
        return generateKeyStr(keyType, 128);
    }
    
    /**
     * 生成指定长度密钥字符串
     * 生成一个指定加密算法的128位密钥的Base64编码字符串形式，keyType必须是KEY_TYPE常量数组中的一个值。
     * KEY_TYPE中目前有AES、Blowfish；keySize默认只允许128，更大长度（192/256）需要修改JRE设置
     * @param keyType
     * @param keySize
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static String generateKeyStr(String keyType, int keySize) throws Exception {
        SecretKey key = generateKey(keyType, keySize);
        if(null!=key)
        {
            return encodeBase64(key.getEncoded());
        }else
        {
            return null;
        }
    }
    
    /**
     * 生成初始化向量字符串
     * 生成一个用于CBC加密模式的随机初始化向量Base64编码字符串形式
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static String generateIVStr() throws Exception {
        return encodeBase64(generateRndStr(16).getBytes());
    }
    
    public static byte[] decodeBase64(String encodedStr)
    {
        return Base64.decode(encodedStr.getBytes());
    }
    
    public static byte[] encodeBase64(String unencodestr)
    {
        return Base64.encode(unencodestr.getBytes());
    }

    public static String decodeBase64(byte[] encodedBytes)
    {
        return new String(Base64.decode(encodedBytes));
    }
    
    public static String encodeBase64(byte[] unencodeBytes)
    {
        return new String(Base64.encode(unencodeBytes));
    }
    
    private static byte[] hex2Bin(String src)
    {
        if (src.length() < 1)
            return null;
        byte[] encrypted = new byte[src.length() / 2];
        for (int i = 0; i < src.length() / 2; i++)
        {
            int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);
            
            encrypted[i] = (byte)(high * 16 + low);
        }
        return encrypted;
    }

    public static String byte2hexString(byte buf[])
    {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        
        for (i = 0; i < buf.length; i++)
        {
            if (((int)buf[i] & 0xff) < 0x10)
                strbuf.append("0");
            
            strbuf.append(Long.toString((int)buf[i] & 0xff, 16));
        }
        
        //return strbuf.toString().toUpperCase();
        String value = strbuf.toString();                
        Locale locale = new Locale(value);            
        value = value.toUpperCase(locale);  
        return value;
    }
    
    public static String generateRndHexStr(int len)
    {
        return byte2hexString(generateRndStr(len).getBytes());
    }
        
    public static String generateRndStr(int len)
    {
        SecureRandom random = new SecureRandom();
        random.setSeed(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < 16; i++)
        {
            int c = random.nextInt(128);
            if (c < 33)
            {
                c = 33 + c;
            }
            sb.append((char)c);
        }
        String s = sb.toString();
        return s;
    }
   
    public static void main(String[] args)
    {
        if (args == null || args.length < 3)
        {
        	System.err.println("用法： java CryptUtil 数据库连接串明文 加密算法 密钥 [初始化向量]");
        	System.err.println("加密算法： 0或者1。当加密算法为1时，初始化向量不可为空。");
        	System.err.println("密钥： 长度为16。");
        	System.err.println("初始化向量： 长度为16。");
        	return;
        }
        
        if (!"1".equals(args[1]) && !"0".equals(args[1]))
        {
        	System.err.println("加密算法只能为0或1。");
        	return;
        }
        
        if ("1".equals(args[1]) && args.length < 4)
        {
        	System.err.println("当加密算法为1时，初始化向量不可为空。");
        	return;
        }
        
        if (args[2].length() != 16)
        {
        	System.err.println("密钥长度为16");
        	return;
        }
        
        if (args.length >= 4 && args[3].length() != 16)
        {
        	System.err.println("初始化向量长度为16");
        	return;
        }
    	
    	try
        {
    		if (args.length >= 4)
    		{
    			System.out.println("经Base64编码后的初始化向量：" + encodeBase64(args[3].getBytes()));
    		}
         
    		System.out.println("经Base64编码后的密钥：" + encodeBase64(args[2].getBytes()));
         
    		CryptUtil util = null;
         
    		if (args.length >= 4)
    		{
    			util = new CryptUtil(encodeBase64(args[3].getBytes()), encodeBase64(args[2].getBytes()));
    		}
	        else
	        {
	        	util = new CryptUtil(encodeBase64(args[2].getBytes()));
	        }
         
    		String encString = util.encrypt(args[0]);
    		System.out.println(encString);
         
    		String desString = util.decrypt(encString);
    		System.out.println(desString);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
     }
}