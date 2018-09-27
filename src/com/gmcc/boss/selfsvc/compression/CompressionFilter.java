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
 * GZIP�Ծ�̬�ļ�����ѹ��
 * <������ϸ����>
 * 
 * @author  zKF66389
 * @version  [�汾��, Aug 22, 2013]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
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
        // �ж�IE�ͻ����Ƿ�֧��GZIPѹ��
        boolean compress = false; 
        if (request instanceof HttpServletRequest)
        { 
            HttpServletRequest httpRequest = (HttpServletRequest) request; 
            
            // HTTP Header��Accept-Encoding �����������������,���������֧�ֵı�������
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
        
        // �������Ƿ�����GZIPѹ�� 0������ 1������
        String sh_gzip_compress = PublicCache.getInstance().getCachedData("SH_GZIP_COMPRESS") == null ? "1" : (String)PublicCache.getInstance().getCachedData("SH_GZIP_COMPRESS");
        // ֧��GZIPѹ�����Է�����Ϣ����ѹ��
        if (compress && "0".equals(sh_gzip_compress))
        {
            HttpServletResponse httpResponse = (HttpServletResponse) response; 
            httpResponse.addHeader("Content-Encoding", "gzip"); 
            CompressionResponse compressionResponse = new CompressionResponse(httpResponse);
            chain.doFilter(request, compressionResponse); 
            compressionResponse.close(); 
        } 
        // ��֧��GZIPѹ������������
        else{
            chain.doFilter(request, response); 
        } 
        
        // ����Ϊ���Դ���
//        String data = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";   
//        System.out.println("ѹ��ǰ��" + data.length());   
//           
//        ByteArrayOutputStream bout = new ByteArrayOutputStream();   
//        GZIPOutputStream gzip = new GZIPOutputStream(bout);   
//        gzip.write(data.getBytes());   
//        gzip.flush();   
//        gzip.close();   
//        //dataѹ�������Ѿ�����bout�ֽ���������   
//           
//           
//        byte[] buf = bout.toByteArray();   
//        //ȡ���������ݣ�����ѹ���������   
//        System.out.println("ѹ����" + buf.length);   
        
        
//        chain.doFilter(request, response); 

    } 
    
    public void init(FilterConfig config) throws ServletException 
    { 
    } 
    
    public void destroy()
    { 
    } 
} 