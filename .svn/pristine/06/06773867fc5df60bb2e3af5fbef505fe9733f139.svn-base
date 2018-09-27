package com.gmcc.boss.selfsvc.query.tag;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.common.Constants;

/**
 * 通用分页标签 1.从表单中获得全部查询条件参数,生成对应的<input type=hidden>以备下次提交查询条件参数及值.
 * 2.把当前页号(pageNo)设置成了请求参数,以便后台程序可以获得该值进行时时从数据数据库查询相应要显示的数据 3.设置总页数放入request属性中，方便查询最后一页 4.设置此标签注意增加键盘事件 如：上一页 U/pageUp ;
 * 下一页 D/pageDown ; 首 页 0 ; 尾 页 1 ;
 * 
 */
public class CommonPaginationTag extends BodyTagSupport
{
    
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(CommonPaginationTag.class);
    
    /** 每页显示的记录数(标签的属性) */
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;;
    
    /** 目的地(标签的属性) */
    private String gotoURI;
    
    /** 总记录数名 */
    public static final String TOTAL = "total";
    
    /** 当前页号名 */
    public static final String PAGNENO = "pageNo";
    
    /** 每页要显示的记录数名 */
    public static final String RECORDCOUNT = "pageSize";
    
    /** 目的地名 */
    public static final String GOTOURI = "gotoURI";
    
    private static final String MENUID = "curMenuId";
    
    // 标签处理程序
    public int doStartTag() throws JspException
    {
        /** 当前页号(从请求对象中得到) */
        int pageNo = 1;
        /** 总记录数(从请求对象中得到) */
        int total = 0;
        /** 总页数(计算得出) */
        int totalPage = 1;
        
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        
        // 要输出到页面的HTML文本
        StringBuffer sb = new StringBuffer();
        
        // 获取所有提交的参数(包括查询条件参数)
        Enumeration enumeration = request.getParameterNames();
        String name = null;
        String value = null;
        while (enumeration.hasMoreElements())
        {
            name = (String)enumeration.nextElement();
            value = request.getParameter(name);
            
            if (name.equals(RECORDCOUNT))
            {
                continue;
            }
            // 从请求对象中获取要跳转到的页号
            if (name.equals(PAGNENO))
            {
                if (null != value && !"".equals(value))
                {
                    pageNo = Integer.parseInt(value);
                }
                continue;
            }
            if (name.equals(TOTAL) || name.equals(MENUID))
            {
                // if (null != value && !"".equals(value))
                // {
                // totalPage = Integer.parseInt(value);
                // }
                continue;
            }
            
            sb.append("<input type='hidden' name='").append(name).append("' value='").append(value).append("'/>\r\n");
        }
        
        // 把当前页号设置成请求参数
        sb.append("<input type='hidden' name='").append(PAGNENO).append("' value='").append(pageNo).append("'/>\r\n");
        
        // 从请求对象中获取总记录数
        String tot = (String)request.getAttribute(TOTAL);
        String psize = (String)request.getAttribute("pageSize");
     // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
//        if (null != tot && !"".equals(tot))
//        {
//            total = Integer.parseInt(tot);
//        }
        total = (null != tot && !"".equals(tot)) ? Integer.parseInt(tot) : total;
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
        if (psize == null)
        {
            psize = String.valueOf(this.pageSize);
        }
        else
        {
            setPageSize(Integer.parseInt(psize));
        }
        // 计算总页数
        totalPage = getTotalPage(total);
        
        sb.append("<input type='hidden' name='").append(TOTAL).append("' value='").append(totalPage).append("'/>\r\n");
        // 设置总页数请求域属性
        request.setAttribute("totalPage", String.valueOf(totalPage));
        
//        sb.append("<hr width='100%'/>\r\n");
//        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
        sb.append("共 ").append(totalPage).append(" 页,当前第 ").append(pageNo).append(" 页\r\n");
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");

        if (pageNo == 1)
        {
            sb.append("<button id='firstPage' class='relative btn3_mouseout'>首页(0)</button>&nbsp;&nbsp;&nbsp;");
            sb.append("<button id='previousPage' class='relative btn3_mouseout'>上一页(U)</button>&nbsp;&nbsp;&nbsp;");
        }
        else
        {
            sb.append("<button id='firstPage' class='relative btn3_mouseout' onclick='turnOverPage(1)'>首页(0)</button>&nbsp;&nbsp;&nbsp;");
            sb.append("<button id='previousPage' class='relative btn3_mouseout' onclick='turnOverPage(").append((pageNo - 1)).append(")'>上一页(U)</button>&nbsp;&nbsp;&nbsp;");
        }
        
        if (pageNo == totalPage)
        {
            sb.append("<button id='nextPage' class='relative btn3_mouseout'>下一页(D)</button>&nbsp;&nbsp;&nbsp;");
            sb.append("<button id='lastPage' class='relative btn3_mouseout'>尾页(1)</button>");
        }
        else
        {
            sb.append("<button id='nextPage' class='relative btn3_mouseout' onclick='turnOverPage(").append((pageNo + 1)).append(")'>下一页(D)</button>&nbsp;&nbsp;&nbsp;");
            sb.append("<button id='lastPage' class='relative btn3_mouseout' onclick='turnOverPage(").append(totalPage).append(")'>尾页(1)</button>");
        }
        
        // 生成提交表单的JS
        sb.append("<script language='javascript'>\r\n");
        sb.append("  function turnOverPage(no){\r\n");
        // sb.append(" //alert(no);\r\n");
        sb.append("    var form = document.actform;\r\n");
        sb.append("    //页号越界处理\r\n");
        sb.append("    if(no").append(">").append(totalPage).append(") {\r\n");
        sb.append("        no=").append(totalPage).append(";\r\n");
        sb.append("    }\r\n");
        sb.append("    if(no").append("<=0){\r\n");
        sb.append("        no=1;\r\n");
        sb.append("    }\r\n");
        sb.append("    form.").append(PAGNENO).append(".value=no;\r\n");
        sb.append("    form.target='_self';\r\n");
        sb.append("    form.action='").append(gotoURI).append("';\r\n");
        sb.append("    form.submit();\r\n");
        sb.append("  }\r\n");
        sb.append("</script>\r\n");
        
        try
        {
            pageContext.getOut().println(sb.toString());
        }
        catch (IOException e)
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return super.doStartTag();
    }
    
    public String getGotoURI()
    {
        return gotoURI;
    }
    
    public void setGotoURI(String gotoURI)
    {
        this.gotoURI = gotoURI;
    }
    
    public int getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
    
    /**
     * 根据总记录数得到总页数
     * 
     * @return int 总页数
     */
    private int getTotalPage(int total)
    {
        int totalPage = 1;
        if (total == 0)
        {
            totalPage = 1;
        }
        else
        {
            totalPage = (total % getPageSize() == 0) ? (total / getPageSize()) : (total / getPageSize() + 1);
        }
        
        return totalPage;
    }
    
}
