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
 * ͨ�÷�ҳ��ǩ 1.�ӱ��л��ȫ����ѯ��������,���ɶ�Ӧ��<input type=hidden>�Ա��´��ύ��ѯ����������ֵ.
 * 2.�ѵ�ǰҳ��(pageNo)���ó����������,�Ա��̨������Ի�ø�ֵ����ʱʱ���������ݿ��ѯ��ӦҪ��ʾ������ 3.������ҳ������request�����У������ѯ���һҳ 4.���ô˱�ǩע�����Ӽ����¼� �磺��һҳ U/pageUp ;
 * ��һҳ D/pageDown ; �� ҳ 0 ; β ҳ 1 ;
 * 
 */
public class CommonPaginationTag extends BodyTagSupport
{
    
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(CommonPaginationTag.class);
    
    /** ÿҳ��ʾ�ļ�¼��(��ǩ������) */
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;;
    
    /** Ŀ�ĵ�(��ǩ������) */
    private String gotoURI;
    
    /** �ܼ�¼���� */
    public static final String TOTAL = "total";
    
    /** ��ǰҳ���� */
    public static final String PAGNENO = "pageNo";
    
    /** ÿҳҪ��ʾ�ļ�¼���� */
    public static final String RECORDCOUNT = "pageSize";
    
    /** Ŀ�ĵ��� */
    public static final String GOTOURI = "gotoURI";
    
    private static final String MENUID = "curMenuId";
    
    // ��ǩ�������
    public int doStartTag() throws JspException
    {
        /** ��ǰҳ��(����������еõ�) */
        int pageNo = 1;
        /** �ܼ�¼��(����������еõ�) */
        int total = 0;
        /** ��ҳ��(����ó�) */
        int totalPage = 1;
        
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        
        // Ҫ�����ҳ���HTML�ı�
        StringBuffer sb = new StringBuffer();
        
        // ��ȡ�����ύ�Ĳ���(������ѯ��������)
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
            // ����������л�ȡҪ��ת����ҳ��
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
        
        // �ѵ�ǰҳ�����ó��������
        sb.append("<input type='hidden' name='").append(PAGNENO).append("' value='").append(pageNo).append("'/>\r\n");
        
        // ����������л�ȡ�ܼ�¼��
        String tot = (String)request.getAttribute(TOTAL);
        String psize = (String)request.getAttribute("pageSize");
     // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
//        if (null != tot && !"".equals(tot))
//        {
//            total = Integer.parseInt(tot);
//        }
        total = (null != tot && !"".equals(tot)) ? Integer.parseInt(tot) : total;
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
        if (psize == null)
        {
            psize = String.valueOf(this.pageSize);
        }
        else
        {
            setPageSize(Integer.parseInt(psize));
        }
        // ������ҳ��
        totalPage = getTotalPage(total);
        
        sb.append("<input type='hidden' name='").append(TOTAL).append("' value='").append(totalPage).append("'/>\r\n");
        // ������ҳ������������
        request.setAttribute("totalPage", String.valueOf(totalPage));
        
//        sb.append("<hr width='100%'/>\r\n");
//        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
        sb.append("�� ").append(totalPage).append(" ҳ,��ǰ�� ").append(pageNo).append(" ҳ\r\n");
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");

        if (pageNo == 1)
        {
            sb.append("<button id='firstPage' class='relative btn3_mouseout'>��ҳ(0)</button>&nbsp;&nbsp;&nbsp;");
            sb.append("<button id='previousPage' class='relative btn3_mouseout'>��һҳ(U)</button>&nbsp;&nbsp;&nbsp;");
        }
        else
        {
            sb.append("<button id='firstPage' class='relative btn3_mouseout' onclick='turnOverPage(1)'>��ҳ(0)</button>&nbsp;&nbsp;&nbsp;");
            sb.append("<button id='previousPage' class='relative btn3_mouseout' onclick='turnOverPage(").append((pageNo - 1)).append(")'>��һҳ(U)</button>&nbsp;&nbsp;&nbsp;");
        }
        
        if (pageNo == totalPage)
        {
            sb.append("<button id='nextPage' class='relative btn3_mouseout'>��һҳ(D)</button>&nbsp;&nbsp;&nbsp;");
            sb.append("<button id='lastPage' class='relative btn3_mouseout'>βҳ(1)</button>");
        }
        else
        {
            sb.append("<button id='nextPage' class='relative btn3_mouseout' onclick='turnOverPage(").append((pageNo + 1)).append(")'>��һҳ(D)</button>&nbsp;&nbsp;&nbsp;");
            sb.append("<button id='lastPage' class='relative btn3_mouseout' onclick='turnOverPage(").append(totalPage).append(")'>βҳ(1)</button>");
        }
        
        // �����ύ����JS
        sb.append("<script language='javascript'>\r\n");
        sb.append("  function turnOverPage(no){\r\n");
        // sb.append(" //alert(no);\r\n");
        sb.append("    var form = document.actform;\r\n");
        sb.append("    //ҳ��Խ�紦��\r\n");
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
     * �����ܼ�¼���õ���ҳ��
     * 
     * @return int ��ҳ��
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
