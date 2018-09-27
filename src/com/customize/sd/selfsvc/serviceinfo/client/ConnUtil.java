/*
 * 文 件 名:  ConnUtil.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <与浪潮建立连接>
 * 修 改 人:  zWX176560
 * 修改时间:  Sep 14, 2013
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <新增>
 */
package com.customize.sd.selfsvc.serviceinfo.client;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.cache.PublicCache;

/**
 * <与浪潮建立连接并通信>
 * <功能详细描述>
 * 
 * @author  zWX176560
 * @version  2013/09/14 R003C13L08n01 OR_SD_201309_66
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ConnUtil
{
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 日志
     */
    private static Log logger = LogFactory.getLog(ConnUtil.class);
    
    /**
     * 默认连接,读取超时时间。
     */
    public static final int DEFAULT_TIMEOUT = 20000;
    
    /**
     * 获得连接超时时间，单位ms，若未设置默认20s
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    private int getConTimeout()
    {
        int result;
        try
        {
            String invokeTime = (String)PublicCache.getInstance().getCachedData("SH_INVOKE_TIME");
            result = Integer.parseInt(invokeTime)*1000;
        }
        catch (NumberFormatException e)
        {
            logger.error("SH_INVOKE_TIME配置参数异常,使用默认参数值20000");
            result = ConnUtil.DEFAULT_TIMEOUT;
        }
        return result;
    }
    
    /**
     * 获取读取超时时间，单位ms，若未设置默认20s
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    private int getSoTimeout()
    {
        int result;
        try
        {
            String invokeTime = (String)PublicCache.getInstance().getCachedData("SH_INVOKE_TIME");
            result = Integer.parseInt(invokeTime)*1000;
        }
        catch (NumberFormatException e)
        {
            logger.error("SH_INVOKE_TIME配置参数异常,使用默认参数值20000");
            result = ConnUtil.DEFAULT_TIMEOUT;
        }
        return result;
    }
    
    /**
     * 使用http方式发送报文,设置连接超时和读取超时
     * 
     * @param url
     * @param xml
     * @return
     * @throws Exception 
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String sendXMLReq(String url, String xml,String charset) throws Exception
    {
        int contimeout = this.getConTimeout();
        int sotimeout = this.getSoTimeout();
        return this.sendXMLReq(url, xml, contimeout, sotimeout, charset);
    }
    
    /**
     * 使用http方式发送报文,设置连接超时和读取超时
     * 
     * @param url
     * @param xml
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String sendXMLReq(String url, String xml, int contimeout, int sotimeout, String charset) throws Exception
    {
        try{
            logger.info("url:"+url);
            logger.info("send Xml:"+xml);
            HttpClient httpClient = new HttpClient();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(contimeout);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(sotimeout);
            
            PostMethod post = new PostMethod(url);
            if(null == charset || "".equals(charset))
            {
                charset = "UTF-8";
            }
            
            // 可能抛出UnsupportedEncodingException
            RequestEntity requestXml = new StringRequestEntity(xml, "text/xml", "utf-8");
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
            
            post.setRequestEntity(requestXml);
            
            // post,可能抛出HttpException,IOException
            httpClient.executeMethod(post);
            
            // 获得返回信息,可能抛出IOException
            String resp = post.getResponseBodyAsString();
            logger.info("retmessage:" + resp);
            post.releaseConnection();
            return resp;
        }
        catch(HttpException e)
        {
            logger.error("调用http失败，调用地址：" + url + "reqMessage:" + xml);
            e.printStackTrace();
            throw e;
        }
        catch( IOException e1)
        {
            logger.error("调用http失败，调用地址：" + url + "reqMessage:" + xml);
            e1.printStackTrace();
            throw e1;
        }
    }
}
