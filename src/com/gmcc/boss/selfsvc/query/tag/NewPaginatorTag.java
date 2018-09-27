/*
* @filename: NewPaginatorTag.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  分页，显示首页、上一页、页码(3页)、下一页、尾页
* @author: g00140516
* @de:  2012/05/25 
* @description: 
* @remark: create g00140516 2012/05/25 R003C12L05n01 OR_huawei_201201_94
*/
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
 * 分页，显示首页、上一页、页码(3页)、下一页、尾页
 * 
 * @author  g00140516
 * @version  1.0, 2012/05/25
 * @see  
 * @since  
 */
public class NewPaginatorTag extends BodyTagSupport
{
    private static final long serialVersionUID = 7465309615187836499L;
    
    private static Log log = LogFactory.getLog(PaginatorTag.class);
    
    /**
     * 总记录数
     */
    private int recordsCount = 0;
    
    /**
     * 总页数
     */
    private int pagesCount = 0;
    
    /**
     * 每页显示记录数
     */
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;
    
    /**
     * 当前页
     */
    private int page = 1;
    
    /**
     * 链接地址
     */
    private String linkUrl = "";
    
    /**
     * 显示位置：居中？靠右？靠左？默认居中显示
     */
    private String alignType = "";
    
    /**
     * 详单查询功能？
     */
    private String cdrFlag = "false";
    
    /**
     * 查询全部详单？
     */
    private String isQueryAll = "false";
    
    /**
     * 详单类型
     */
    private int listtype = 0;

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
            log.error("分页输出错误：", e);
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
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        
        try
        {
            request.setCharacterEncoding("GBK");
        }
        catch (UnsupportedEncodingException uee)
        {
            log.error("分页异常：", uee);
        }
        
        StringBuffer subBuf = new StringBuffer(512);
        
        // 点击首页、上一页、下一页、尾页等对应的action
        String strUrl = request.getContextPath() + "/" + linkUrl;
        
        // 如果URL不带？，则加上？，后面需追加参数
        int index = strUrl.indexOf("?");
        if (index == -1)
        {
            strUrl += "?";
        }
        
        // 如果URL既不是以?结尾，也不是以&结尾，则在最后加上&，后面需追加参数
        if (!strUrl.endsWith("?") && !strUrl.endsWith("&"))
        {
            strUrl += "&";
        }
        
        // 详单查询功能，用户选择查询全部详单时，还应显示上一清单、下一清单链接
        if ("true".equalsIgnoreCase(cdrFlag) && "true".equalsIgnoreCase(isQueryAll))
        {
            strUrl += "isQueryAll=true&";
            
            int tmpListType = listtype;
            
            // 用户选择查询全部详单时，默认查询第一种详单
            if (tmpListType == 0)
            {
                tmpListType = 1;
            }
            
            // 如果取到当前清单，则根据当前清单和清单顺序(TYPE_SERVICE_NAME_ARRAY决定顺序)计算上下清单.清单的范围是从1开始，0表示全部
            tmpListType = SelfSvcCdrType.supportType(tmpListType);
            
            int previousListType = SelfSvcCdrType.previousFactType(tmpListType - 1);
            int nextListType = SelfSvcCdrType.nextFactType(tmpListType + 1);           
            
            // begin zKF66389 2015-09-10 9月份findbugs修改
            //int listTypeCount = Constants.TYPE_SERVICE_NAME_ARRAY.length - 1; // 清单数,减去全部清单
            int listTypeCount = Constants.getTypeServiceNameArray().length - 1; // 清单数,减去全部清单
            // begin zKF66389 2015-09-10 9月份findbugs修改
            
            // 如果是查询全部清单，并且不为第一个清单，则显示上一清单分页
            if (previousListType > 0)
            {
                subBuf.append("<input type='button' class='bt2_liebiao white' value='上一清单' onmousedown='this.className=\"bt2_liebiao_on white\"' onmouseup='this.className=\"bt2_liebiao white\";nextPage(\"").append(strUrl).append("\", ").append(previousListType).append(")' > ");
            }
            
            // 如果是查询全部清单，并且不为最后一个清单，则不显示上一清单分页
            if (nextListType <= listTypeCount)
            {
                subBuf.append("<input type='button' class='bt2_liebiao white' value='下一清单' onmousedown='this.className=\"bt2_liebiao_on white\"' onmouseup='this.className=\"bt2_liebiao white\";nextPage(\"").append(strUrl).append("\", ").append(nextListType).append(")' > ");
            }            
        }
        
        if(recordsCount > 0 && "telProdDiy/qryTelProdList.action".equals(linkUrl))
        {
            // 计算总页数
            if (0 == recordsCount % pageSize)
            {
                pagesCount = recordsCount / pageSize;
            }
            else
            {
                pagesCount = recordsCount / pageSize + 1;
            }        
            
            // 第一页：上一页不可用
            if (1 == page)
            {
                subBuf.append("<a href='javascript:void(0)' class='leftBtn'></a>");
            }
            else
            {
                subBuf.append("<a href='javascript:void(0)' class='leftBtn' onclick='nextPage(\"").append(strUrl).append("page=").append(page - 1).append("\", ").append(listtype).append(");return false;' ></a>");
            }
            
            // 最后一页：下一页不可用
            if (pagesCount == page)
            {
                subBuf.append("<a href='javascript:void(0)' class='rightBtn'></a>");
            }
            else
            {
                subBuf.append("<a href='javascript:void(0)' class='rightBtn' onclick='nextPage(\"").append(strUrl).append("page=").append(page + 1).append("\", ").append(listtype).append(");return false;' ></a> ");
            }
            
            subBuf.append("第").append(page).append("页,");

            subBuf.append("共").append(pagesCount).append("页");
            
            return subBuf.toString();
            
            
        }
        
        // 只有记录数大于0时，才需要进行分页
        if (recordsCount > 0)
        {
            // 计算总页数
            if (0 == recordsCount % pageSize)
            {
                pagesCount = recordsCount / pageSize;
            }
            else
            {
                pagesCount = recordsCount / pageSize + 1;
            }        
            
            // 总页数大于1时，才需要进行分页
            if (pagesCount > 1)
            {
                // 第一页：首页、上一页均不可用
                if (1 == page)
                {
                    // modify begin g00140516 2012/06/05 R003C12L05n01 bug 26567
                	subBuf.append("<input type='button' class='bt2_liebiao_off white' value='首页' > ")
                            .append("<input type='button' class='bt2_liebiao_off white' value='上一页' > ");
                	// modify end g00140516 2012/06/05 R003C12L05n01 bug 26567
                }
                else
                {
                    // nextPage函数有两个参数，第一个是点击后对应的action，第二个是详单类型。只有详单查询功能，第二个参数才有效
                    subBuf.append("<input type='button' class='bt2_liebiao white' value='首页' onmousedown='this.className=\"bt2_liebiao_on white\"' onmouseup='this.className=\"bt2_liebiao white\";nextPage(\"").append(strUrl).append("page=1\", ").append(listtype).append(")' > ")
                            .append("<input type='button' class='bt2_liebiao white' value='上一页' onmousedown='this.className=\"bt2_liebiao_on white\"' onmouseup='this.className=\"bt2_liebiao white\";nextPage(\"").append(strUrl).append("page=").append(page - 1).append("\", ").append(listtype).append(")' > ");
                }        
                
                // 共两页或三页
                if (2 == pagesCount || 3 == pagesCount)
                {           
                    for (int i = 0; i < pagesCount; i++)
                    {
                        if (page == (i + 1))
                        {
                            subBuf.append("<span class='fs18 p3 bg_blue'>").append(i + 1).append("</span>");
                        }
                        else
                        {
                            subBuf.append("<span class='fs18 p3'>").append(i + 1).append("</span>");
                        }
                        
                    }  
                }
                // 四页及以上
                else 
                {
                    // 第一页
                    if (page == 1)
                    {
                        subBuf.append("<span class='fs18 p3 bg_blue'>1</span><span class='fs18 p3'>2</span><span class='fs18 p3'>3</span>");
                    }
                    // 最后一页
                    else if (page == pagesCount)
                    {
                        subBuf.append("<span class='fs18 p3'>").append(pagesCount - 2).append("</span>");
                        subBuf.append("<span class='fs18 p3'>").append(pagesCount - 1).append("</span>");
                        subBuf.append("<span class='fs18 p3 bg_blue'>").append(pagesCount).append("</span>");
                    }
                    // 其它页
                    else
                    {
                        subBuf.append("<span class='fs18 p3'>").append(page - 1).append("</span>");
                        subBuf.append("<span class='fs18 p3 bg_blue'>").append(page).append("</span>");
                        subBuf.append("<span class='fs18 p3'>").append(page + 1).append("</span>");
                    }
                }
                
                // 最后一页：下一页、尾页均不可用
                if (pagesCount == page)
                {
                    subBuf.append("<input type='button' class='bt2_liebiao_off white' value='下一页' >")
                            .append("<input type='button' class='bt2_liebiao_off white' value='尾页' >");
                }
                else
                {
                    subBuf.append("<input type='button' class='bt2_liebiao white' value='下一页' onmousedown='this.className=\"bt2_liebiao_on white\"' onmouseup='this.className=\"bt2_liebiao white\";nextPage(\"").append(strUrl).append("page=").append(page + 1).append("\", ").append(listtype).append(")' > ")
                            .append("<input type='button' class='bt2_liebiao white' value='尾页' onmousedown='this.className=\"bt2_liebiao_on white\"' onmouseup='this.className=\"bt2_liebiao white\";nextPage(\"").append(strUrl).append("page=").append(pagesCount).append("\", ").append(listtype).append(")' > ");
                }
                
                subBuf.append("<span class='fs18 p3'>共").append(pagesCount).append("页</span>");
            }           
        }
       
        if (subBuf.length() > 0)
        {
            StringBuffer htmlBuf = new StringBuffer(512);
            if ("left".equalsIgnoreCase(alignType))
            {
                htmlBuf.append("<div class='page-left'>");
            }
            else if ("right".equalsIgnoreCase(alignType))
            {
                htmlBuf.append("<div class='page-right'>");
            }
            else
            {
                htmlBuf.append("<div class='page-centre'>");
            }
            
            htmlBuf.append(subBuf.toString()).append("</div>");
            
            return htmlBuf.toString();
        }
        
        return "";   
    }
    
    public String getLinkUrl()
    {
        return linkUrl;
    }
    
    public void setLinkUrl(String linkUrl)
    {
        this.linkUrl = linkUrl;
    }

    public int getRecordsCount()
    {
        return recordsCount;
    }

    public void setRecordsCount(int recordsCount)
    {
        this.recordsCount = recordsCount;
    }

    public int getPagesCount()
    {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount)
    {
        this.pagesCount = pagesCount;
    }
    
    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public String getAlignType()
    {
        return alignType;
    }

    public void setAlignType(String alignType)
    {
        this.alignType = alignType;
    }

    public String getCdrFlag()
    {
        return cdrFlag;
    }

    public void setCdrFlag(String cdrFlag)
    {
        this.cdrFlag = cdrFlag;
    }

    public String getIsQueryAll()
    {
        return isQueryAll;
    }

    public void setIsQueryAll(String isQueryAll)
    {
        this.isQueryAll = isQueryAll;
    }

    public int getListtype()
    {
        return listtype;
    }

    public void setListtype(int listtype)
    {
        this.listtype = listtype;
    }
}
