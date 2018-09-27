package com.gmcc.boss.selfsvc.query.tag;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.query.common.SelfSvcCdrType;

/**
 * 分页标签
 * 
 * @author lixuewen
 * 
 */
public class PaginatorTag extends BodyTagSupport
{
    private static final long serialVersionUID = 7465309615187836499L;
    
    private static Log log = LogFactory.getLog(PaginatorTag.class);
    
    /**
     * 总页数
     */
    private int pageCount = 0;
    
    /**
     * 链接地址
     */
    private String linkUrl = "";
    
    /**
     * 记录总数
     */
    int recordCount = 0;
    
    /**
     * 当前页,必选参数
     */
    int page = 1;
    
    /**
     * 每页显示记录数参数,可选参数,默认20条
     */
    int pageSize = Constants.DEFAULT_PAGE_SIZE;
    
    public int getRecordCount()
    {
        return recordCount;
    }
    
    public void setRecordCount(int recordCount)
    {
        this.recordCount = recordCount;
    }
    
    public int getPage()
    {
        return page;
    }
    
    public void setPage(int page)
    {
        this.page = page;
    }
    
    public int getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public int doStartTag() throws JspException
    {
        return SKIP_BODY;
    }
    
    public int doEndTag() throws JspException
    {
        String html = renderPageHtml();
        try
        {
            pageContext.getOut().print(html);
        }
        catch (IOException e)
        {
            log.error("输出错误:" + e.getMessage());
        }
        
        return EVAL_PAGE;
        
    }
    
    /**
     * 生成分页html代码
     * 
     * @return
     */
    private String renderPageHtml()
    {
        StringBuffer htmlBuf = new StringBuffer();
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        try
        {
            request.setCharacterEncoding("GBK");
        }
        catch (UnsupportedEncodingException uee)
        {
            log.error(uee.getMessage());
        }
        
        // 取各种参数
        String isQueryAll = request.getParameter("isQueryAll");
        String currentListType = request.getParameter("listtype");
        // String month = request.getParameter("month");
        String returnFlag = request.getParameter("returnflag");
        
        // 当前清单记录总数
        String currentListRecordCount = (String)request.getAttribute("recordCount");
        if (currentListRecordCount == null || currentListRecordCount.trim().length() < 1)
            currentListRecordCount = "0";
        recordCount = Integer.parseInt(currentListRecordCount);
        
        // 当前页码
        String currentPage = request.getParameter("page");
        if (currentPage == null)
            currentPage = "1";
        page = Integer.parseInt(currentPage);
        
        // 根据记录总数和每页数量计算总页数
        pageCount = (recordCount % pageSize == 0) ? recordCount / pageSize : recordCount / pageSize + 1;
        
        // 如果取到当前清单，则根据当前清单和清单顺序(TYPE_SERVICE_NAME_ARRAY决定顺序)计算上下清单.清单的范围是从1开始，0表示全部
        int previousListType = 0;
        int nextListType = SelfSvcCdrType.getSupportServiceNames().length + 1;
        //if (currentListType != null && currentListType.trim().length() > 0)
        if (this.isNotEmpty(currentListType))
        {
            int iCurrentListType = Integer.parseInt(currentListType);
//            if (iCurrentListType == 0)
//            { // 如果当前清单是全部清单，则默认情况下是查第一个清单。
//                iCurrentListType++;
//            }
            iCurrentListType = (iCurrentListType == 0) ? iCurrentListType++ : iCurrentListType;
            
            iCurrentListType = SelfSvcCdrType.supportType(iCurrentListType);
            previousListType = SelfSvcCdrType.previousFactType(iCurrentListType - 1);
            nextListType = SelfSvcCdrType.nextFactType(iCurrentListType + 1);
        }
        // begin zKF66389 2015-09-10 9月份findbugs修改
//        int listTypeCount = Constants.TYPE_SERVICE_NAME_ARRAY.length - 1; // 清单数,减去全部清单
        int listTypeCount = Constants.getTypeServiceNameArray().length - 1; // 清单数,减去全部清单
        // end zKF66389 2015-09-10 9月份findbugs修改
        
        // 翻页链接
        String strUrl = request.getContextPath() + linkUrl;
        strUrl += "?returnflag=" + returnFlag;
        //if (isQueryAll != null && isQueryAll.trim().length() > 0)
        if (this.isNotEmpty(isQueryAll))
        {
            strUrl += "&isQueryAll=" + isQueryAll;
        }
        strUrl += "&listtype=" + currentListType;
        
        // 上一清单和下一清单的链接
        String strUrl2 = request.getContextPath() + linkUrl;
        strUrl2 += "?returnflag=" + returnFlag;
        //if (isQueryAll != null && isQueryAll.trim().length() > 0)
        if (this.isNotEmpty(isQueryAll))
        {
            strUrl2 += "&isQueryAll=" + isQueryAll;
        }
        
        String tmpStr = "";
        // 如果是查询全部清单，并且不为第一个清单，则显示上一清单分页
        //if (isQueryAll != null && isQueryAll.trim().length() > 0 && previousListType > 0)
        if (this.isNotEmpty(isQueryAll) && previousListType > 0)
        {
            tmpStr = "&listtype=" + previousListType;
            //htmlBuf.append("<a id='previousListType' class='bt2 fs16' href='#' onclick=\"nextPage('" + strUrl2 + tmpStr + "');\" title='上一清单'>上一清单</a>&nbsp;&nbsp;");
            htmlBuf.append("<input type=\"button\" class=\"bt2_liebiao\" style=\"color:#FFFFFF;\" value=\"上一清单\" onmousedown=\"\" onmouseup=\"nextPage('" + strUrl2 + tmpStr + "');\"> ");
        }
        
        // 如果是查询全部清单，并且不为最后一个清单，则不显示上一清单分页
        //if (isQueryAll != null && isQueryAll.trim().length() > 0 && nextListType <= listTypeCount)
        if (this.isNotEmpty(isQueryAll) && nextListType <= listTypeCount)
        {
            tmpStr = "&listtype=" + nextListType;
            //htmlBuf.append("<a id='nextListType' class='bt2 fs16' href='#' onclick=\"nextPage('" + strUrl2 + tmpStr + "');\" title='下一清单'>下一清单</a>");
            htmlBuf.append("<input type=\"button\" class=\"bt2_liebiao\" style=\"color:#FFFFFF;\" value=\"下一清单\" onmousedown=\"\" onmouseup=\"nextPage('" + strUrl2 + tmpStr + "');\"> ");
        }
        
        if (page >= 2)
        {
            tmpStr = "&page=" + (page - 1);
            //htmlBuf.append("<a id='previousPage' class='bt2 fs16' href='#' onclick=\"nextPage('" + strUrl + tmpStr + "');\" title='上一页'>上一页</a>&nbsp;&nbsp;");
            htmlBuf.append("<input type=\"button\" class=\"bt2_liebiao\" style=\"color:#FFFFFF;\" value=\"上一页\" onmousedown=\"\" onmouseup=\"nextPage('" + strUrl + tmpStr + "');\"> ");
        }
        if (pageCount - page >= 1)
        {
            tmpStr = "&page=" + (page + 1);
            //htmlBuf.append("<a id='nextPage' class='bt2 fs16' href='#' onclick=\"nextPage('" + strUrl + tmpStr + "');\" title='下一页'>下一页</a>&nbsp;&nbsp;");
            htmlBuf.append("<input type=\"button\" class=\"bt2_liebiao\" style=\"color:#FFFFFF;\" value=\"下一页\" onmousedown=\"\" onmouseup=\"nextPage('" + strUrl + tmpStr + "');\"> ");
        }       
        
        htmlBuf.append("&nbsp;&nbsp;页次：<strong><font color=red>" + page + "</font>/" + pageCount
                + "</strong>页&nbsp;&nbsp;");
        htmlBuf.append("共<b><font color='red'>" + recordCount + "</font></b>条详单&nbsp;&nbsp;");
        htmlBuf.append("<b>" + pageSize + "</b>条/页");
        return (htmlBuf.toString());
        
    }
    
    // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
    /**
     * 判断空串
     * <功能详细描述>
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean isNotEmpty(String str)
    {
        return str != null && str.trim().length() > 0;
    }
    // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
    
    public String getLinkUrl()
    {
        return linkUrl;
    }
    
    public void setLinkUrl(String linkUrl)
    {
        this.linkUrl = linkUrl;
    }
}
