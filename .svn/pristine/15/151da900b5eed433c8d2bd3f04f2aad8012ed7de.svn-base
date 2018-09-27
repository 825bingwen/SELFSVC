package com.gmcc.boss.selfsvc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import com.gmcc.boss.selfsvc.call.IntMsgUtil;

public class HttpURLConnectionUtil
{
    /**
     * Post调用接口
     * 
     * @param interfaceUrl
     * @param content
     * @return
     * @remark lKF60882 2017-1-24 OR_huawei_201701_269 【山东移动接口迁移专题】-能开接口改造、服务规划(公共部分)
     */
    public static String sendByPost(String interfaceUrl, String content)
    {
        return sendByPost(interfaceUrl, content, "json");
    }
    
    /**
     * Post调用接口
     * 
     * @param interfaceUrl
     * @param content
     * @return
     * @see [类、类#方法、类#成员]
     * @remark lKF60882 2017-1-24 OR_huawei_201701_269 【山东移动接口迁移专题】-能开接口改造、服务规划(公共部分)
     * 
     */
    public static String sendByPost(String interfaceUrl, String content, String type)
    {
        URL url = null;
        HttpURLConnection urlConn = null;
        BufferedReader in = null;
        PrintWriter out = null;
        String sLine = null;
        StringBuffer sbBuf = new StringBuffer();
        
        try
        {
            String charset = IntMsgUtil.getEncodingForEbusContentType();
            
            url = new URL(interfaceUrl);
            urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setConnectTimeout(5000);
            urlConn.setReadTimeout(5000);
            if("json".equals(type))
            {
                urlConn.setRequestProperty("Content-Type", "application/json; charset=" + charset);
            }
            urlConn.setRequestMethod("POST");
            
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            
            out = new PrintWriter(new OutputStreamWriter(urlConn.getOutputStream(), charset));
            out.print(content);
            
            out.flush();
            
            in = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), charset));
            
            while ((sLine = in.readLine()) != null)
            {
                sbBuf.append(sLine).append("\n");
            }
            return sbBuf.toString();
        }
        catch (SocketTimeoutException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (null != in)
                {
                    in.close();
                    in = null;
                }
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            
            if (null != out)
            {
                out.close();
                out = null;
            }
            
            try
            {
                if (urlConn != null)
                {
                    urlConn.disconnect();
                    urlConn = null;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return sLine;
        
    }
}
