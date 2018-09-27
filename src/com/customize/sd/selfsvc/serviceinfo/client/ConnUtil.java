/*
 * �� �� ��:  ConnUtil.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <���˳���������>
 * �� �� ��:  zWX176560
 * �޸�ʱ��:  Sep 14, 2013
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <����>
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
 * <���˳��������Ӳ�ͨ��>
 * <������ϸ����>
 * 
 * @author  zWX176560
 * @version  2013/09/14 R003C13L08n01 OR_SD_201309_66
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ConnUtil
{
    /**
     * ���л�
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * ��־
     */
    private static Log logger = LogFactory.getLog(ConnUtil.class);
    
    /**
     * Ĭ������,��ȡ��ʱʱ�䡣
     */
    public static final int DEFAULT_TIMEOUT = 20000;
    
    /**
     * ������ӳ�ʱʱ�䣬��λms����δ����Ĭ��20s
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
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
            logger.error("SH_INVOKE_TIME���ò����쳣,ʹ��Ĭ�ϲ���ֵ20000");
            result = ConnUtil.DEFAULT_TIMEOUT;
        }
        return result;
    }
    
    /**
     * ��ȡ��ȡ��ʱʱ�䣬��λms����δ����Ĭ��20s
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
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
            logger.error("SH_INVOKE_TIME���ò����쳣,ʹ��Ĭ�ϲ���ֵ20000");
            result = ConnUtil.DEFAULT_TIMEOUT;
        }
        return result;
    }
    
    /**
     * ʹ��http��ʽ���ͱ���,�������ӳ�ʱ�Ͷ�ȡ��ʱ
     * 
     * @param url
     * @param xml
     * @return
     * @throws Exception 
     * @throws Exception
     * @see [�ࡢ��#��������#��Ա]
     */
    public String sendXMLReq(String url, String xml,String charset) throws Exception
    {
        int contimeout = this.getConTimeout();
        int sotimeout = this.getSoTimeout();
        return this.sendXMLReq(url, xml, contimeout, sotimeout, charset);
    }
    
    /**
     * ʹ��http��ʽ���ͱ���,�������ӳ�ʱ�Ͷ�ȡ��ʱ
     * 
     * @param url
     * @param xml
     * @return
     * @throws Exception
     * @see [�ࡢ��#��������#��Ա]
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
            
            // �����׳�UnsupportedEncodingException
            RequestEntity requestXml = new StringRequestEntity(xml, "text/xml", "utf-8");
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
            
            post.setRequestEntity(requestXml);
            
            // post,�����׳�HttpException,IOException
            httpClient.executeMethod(post);
            
            // ��÷�����Ϣ,�����׳�IOException
            String resp = post.getResponseBodyAsString();
            logger.info("retmessage:" + resp);
            post.releaseConnection();
            return resp;
        }
        catch(HttpException e)
        {
            logger.error("����httpʧ�ܣ����õ�ַ��" + url + "reqMessage:" + xml);
            e.printStackTrace();
            throw e;
        }
        catch( IOException e1)
        {
            logger.error("����httpʧ�ܣ����õ�ַ��" + url + "reqMessage:" + xml);
            e1.printStackTrace();
            throw e1;
        }
    }
}
