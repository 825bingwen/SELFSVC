package com.gmcc.boss.selfsvc.compression;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.RegionInfoPO;

/**
 * 
 * GZIP对静态文件进行压缩
 * <功能详细描述>
 * 
 * @author  zKF66389
 * @version  [版本号, Aug 22, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CompressionFilter implements Filter { 

    protected Log log = LogFactory.getFactory().getInstance(this.getClass().getName()); 
    
    /**
     * 
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {
        // 判断IE客户端是否支持GZIP压缩
        boolean compress = false; 
        if (request instanceof HttpServletRequest)
        { 
            HttpServletRequest httpRequest = (HttpServletRequest) request; 
            
            // HTTP Header中Accept-Encoding 是浏览器发给服务器,声明浏览器支持的编码类型
            Enumeration headers = httpRequest.getHeaders("Accept-Encoding"); 
            while (headers.hasMoreElements())
            { 
                // gzip, deflate
                String value = (String) headers.nextElement(); 
                if (value.indexOf("gzip") != -1)
                {
                    compress = true; 
                }
            } 
        }
        
        // 服务器是否启用GZIP压缩 0：启用 1：禁用
        String sh_gzip_compress = PublicCache.getInstance().getCachedData("SH_GZIP_COMPRESS") == null ? "1" : (String)PublicCache.getInstance().getCachedData("SH_GZIP_COMPRESS");
        // 支持GZIP压缩：对返回信息进行压缩
        if (compress && "0".equals(sh_gzip_compress))
        {
            HttpServletResponse httpResponse = (HttpServletResponse) response; 
            httpResponse.addHeader("Content-Encoding", "gzip"); 
            CompressionResponse compressionResponse = new CompressionResponse(httpResponse);
            chain.doFilter(request, compressionResponse); 
            compressionResponse.close(); 
        } 
        // 不支持GZIP压缩：不做处理
        else{
            chain.doFilter(request, response); 
        } 
        
        // 以下为测试代码
//        String data = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";   
//        System.out.println("压缩前：" + data.length());   
//           
//        ByteArrayOutputStream bout = new ByteArrayOutputStream();   
//        GZIPOutputStream gzip = new GZIPOutputStream(bout);   
//        gzip.write(data.getBytes());   
//        gzip.flush();   
//        gzip.close();   
//        //data压缩数据已经进入bout字节数组中了   
//           
//           
//        byte[] buf = bout.toByteArray();   
//        //取出来的数据，就是压缩后的数据   
//        System.out.println("压缩后：" + buf.length);   
        
        
//        chain.doFilter(request, response); 

    } 
    
    public void init(FilterConfig config) throws ServletException 
    { 
    } 
    
    public void destroy()
    { 
    } 
} 