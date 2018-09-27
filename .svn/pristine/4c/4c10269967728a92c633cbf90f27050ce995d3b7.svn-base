package com.gmcc.boss.selfsvc.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 安全过滤器，临时规避struts2漏洞(漏洞编号S2-045,CVE编号：cve-2017-5638)
 * 
 * @author lKF60882
 * @version [版本号, 2017-03-14]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SecurityFilter extends HttpServlet implements Filter
{
    private static final long serialVersionUID = 1L;
    
    private FilterConfig filterConfig = null;
    
    public final String www_url_encode = "application/x-www-form-urlencoded";
    
    public final String mul_data = "multipart/form-data";
    
    public final String txt_pla = "text/plain";
    
    public FilterConfig getFilterConfig()
    {
        return filterConfig;
    }
    
    public void setFilterConfig(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }
    
    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
    }
    
    public void destroy()
    {
        this.filterConfig = null;
    }
    
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
            ServletException
    {
        HttpServletRequest request = (HttpServletRequest)arg0;
        HttpServletResponse response = (HttpServletResponse)arg1;
        
        String contenType = String.valueOf(request.getHeader("conTent-type"));
        
        if (contenType != null && !contenType.equals("") && contenType.toLowerCase(Locale.US).contains("ognl"))
        {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("非法请求Content-Type！");
            return;
        }
        arg2.doFilter(request, response);
    }
}
