/*
 * �ļ�����DetailedRecordsAction.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-12-16
 * �޸����ݣ����������굥��ѯ
 */
package com.gmcc.boss.selfsvc.feeservice.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.DetailedRecordsBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.feeservice.model.DetailedRecordsTimesPO;
import com.gmcc.boss.selfsvc.feeservice.service.DetailedRecordsService;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.query.common.SelfSvcCdrType;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.CurrencyUtil;
import com.gmcc.boss.selfsvc.util.SendMailUtil;

/**
 * 
 * ���굥��ѯ
 * 
 * 
 * @author g00140516
 * @version 1.0��2010-12-16
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class DetailedRecordsAction extends BaseAction
{
    private static Log logger = LogFactory.getLog(DetailedRecordsAction.class);
    
    private static final long serialVersionUID = -3950351941316700610L;
    
    private transient DetailedRecordsBean detailedRecordsBean = null;
    
    private transient DetailedRecordsService detailedRecordsService = null;
    
    /**
     * ��ѯ�·�
     */
    private String month = "";
    
    /**
     * �˵�
     */
    private String curMenuId = "";
    
    /**
     * ��ǰҳ��
     */
    private String page = "";
    
    /**
     * ÿҳ��ʾ��¼��
     */
    private final int pageSize = Constants.DEFAULT_PAGE_SIZE;
    
    /**
     * ���ֱ�ʶ���굥����
     */
    private String listtype = "";
    
    /**
     * Ӣ�ĵ��굥��������
     */
    private String typeName = "";
    
    /**
     * �Ƿ��ǲ�ѯȫ�����굥������ǣ��ṩ��ӡ����
     */
    private String isQueryAll = "";
    
    /**
     * ���ĵ��굥��������
     */
    private String tableHeader = "";
    
    /**
     * �굥��¼��ʾ�ֶ�
     */
    private String[] tableTitle = null;
    
    /**
     * �굥��¼������
     */
    private String[] tableTail = null;
    
    /**
     * ������˵��
     */
    private String[] tableDescription = null;
    
    /**
     * ҳ����ʾ���굥��¼
     */
    private Vector resultRecords = null;
    
    private String pagecount = "";
    
    /**
     * ��ѯ��ʽ
     */
    private String fee_type = "";
    
    // add begin cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652
    /**
     * ���俪ͨ���
     */
    private String haveMailbox = "";
    // add end cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652
    
    /**
     * �굥���Ͷ�Ӧ��url
     */
    private String menuURL = "";

    /**
     * �ɲ�ѯ��ǰ�¼�ǰ����µ��굥
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String showMonths()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("showMonths Starting ...");
        }
        
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        if (customerSimp != null)
        {
            session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + customerSimp.getServNumber());
        }
        
        if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")) )
        {
           
            TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // Ȩ����֤
            boolean bz = detailedRecordsBean.checkQueryAuth(customerSimp, terminalInfo, customerSimp.getServNumber(), curMenuId);
            
            if (!bz)
            {
                this.getRequest().setAttribute("errormessage", "���ֻ��������굥��ѯȨ�ޣ�");
                return "failed";
            }
        }
        // �ɲ�ѯ��ǰ�º�ǰ����µ��굥��Ϣ
        String pattern = "yyyyMM";
        
        String[] months = CommonUtil.getLastMonths(pattern, 6);
        
        this.getRequest().setAttribute("months", months);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("showMonths End");
        }
        
        return "success";
    }
    
    /**
     * ���·�ѡ��ҳ�淵�أ�֮���Բ��߹����ķ��ش����߼�������Ϊ��ʱ��Ҫ���session�б�����嵥����
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String backToFunctionList()
    {
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        if (customerSimp != null)
        {
            session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + customerSimp.getServNumber());
        }
        
        List titleTotalMenus = (List)PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
        
        // ���ݵ�ǰ�˵���ȡ���˵�ID
        String parentMenuID = CommonUtil.getParentMenuInfo(titleTotalMenus, curMenuId);
        
        // �����˵�ID��Ϊ��ǰ�˵�
        this.getRequest().setAttribute(Constants.CUR_MENUID, parentMenuID);
        
        return "back";
    }
    
    /**
     * ��ʾ�굥��ѯ����
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String showTypes()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("showTypes Starting ...");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("showTypes End");
        }
        
        // add begin g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
        if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
        {
            return "hub_success";
        }
        // add end g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
        
        return "success";
    }
    
    /**
     * ���굥��¼��ʾҳ�淵�ص��굥���Ͳ�ѯҳ��
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String backToTypesPage()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("backToTypesPage Starting ...");
        }
        
        // �ն���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        List titleTotalMenus = (List)PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
        
        // ���ݵ�ǰ�˵���ȡ���˵�ID
        String parentMenuID = CommonUtil.getParentMenuInfo(titleTotalMenus, curMenuId);
        
        // �����˵�ID��Ϊ��ǰ�˵�
        this.getRequest().setAttribute(Constants.CUR_MENUID, parentMenuID);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("backToTypesPage End");
        }
        
        return "success";
    }
    
    /**
     * ��ѯ�굥��¼
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String qryDetailedRecords()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryDetailedRecords Starting ...");
        }
        // yKF28472
        if (listtype.split(",").length > 1)
        {
            listtype = listtype.split(",")[0];
        }
            
        //�������굥��ѯ��Ҫѡ���ѯ��ʽ(������õġ�����õġ�ȫ����)
        if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID"))
                && (!"true".equals(isQueryAll) || "0".equals(listtype))
                && (fee_type == null || "".equals(fee_type.trim())))
        {
            //�����ն˵��굥��ѯ���ͳ�����ͨ���굥�������굥�Ⱦ��������֮�⣬����ȫ���굥��
            //�û���ѡ����ȫ���굥֮��ϵͳ�Ȳ�ѯ��1���굥(��ͨ���굥�����õ�)����ʾ��ͬʱ��ҳ������ʾ����һ�굥�������ӣ��û�������Ӻ�Ż��ѯ��һ���굥(������굥)��
            //����˵�����û�ѡ����ȫ���굥��ϵͳҲ�����û��Ĳ���֮��������ʾÿ���굥��
            //����û���ѯʱѡ����ͨ���굥���߶����굥����ô���뷽ʽѡ��ҳ�棻����û�ѡ����ȫ���굥���Ҳ�ѯ���ǵ�һ���굥��Ҳ��Ҫ���뷽ʽѡ��ҳ�档
            //����û�ѡ�����ȫ���굥������ѯ�Ĳ��ǵ�һ���굥��˵����ʽ�Ѿ�ѡ����ˣ�����Ҫ�ٽ��뷽ʽѡ��ҳ�档
            return "fee_type";            
        }
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // �嵥���� "GSMCDR"
        typeName = getTypeServiceName(listtype);
        
        // ����嵥��ͷ��Ϣ [[��ͨ���굥��], [���, �Է�����, ��������, ����������, ͨ����ʼʱ��, ͨ��ʱ��(��), ������(Ԫ), ��;��(Ԫ), ��Ѷ��Ϣ��(Ԫ), �ܻ���(Ԫ)], [�굥������, ʱ���ϼƣ�, ���úϼ�(Ԫ)��], [recordcount, totaltimes, totalfee]]
        Object[] tableDetail = getTableDetail(listtype);
        
        // �嵥�������� "[��ͨ���굥��]"
        tableHeader = ((String[])(tableDetail[0]))[0];
        
        // ��ͷ���� [���, �Է�����, ��������, ����������, ͨ����ʼʱ��, ͨ��ʱ��(��), ������(Ԫ), ��;��(Ԫ), ��Ѷ��Ϣ��(Ԫ), �ܻ���(Ԫ)]
        tableTitle = (String[])(tableDetail[1]);
        
        // ��ͷ����
        request.setAttribute("titleCols", tableTitle.length);
        
        // �ϼ����� [�굥������, ʱ���ϼƣ�, ���úϼ�(Ԫ)��]
        tableTail = (String[])(tableDetail[2]);
        
        // �ϼ�Ӣ�� [recordcount, totaltimes, totalfee]
        request.setAttribute("summaryTitle", tableDetail[3]);
        
        if (tableDetail.length >= 5)
        {
            tableDescription = (String[])tableDetail[4];
        }
        
        /** ƴװ����
        type��0--GSMCDR
        header����ͨ���굥��
        title�����  �Է�����  ��������  ����������  ͨ����ʼʱ��  ͨ��ʱ��(��)  ������(Ԫ)  ��;��(Ԫ)  ��Ѷ��Ϣ��(Ԫ)  �ܻ���(Ԫ)  
        tail���굥������  ʱ���ϼƣ�  ���úϼ�(Ԫ)��  
        **/
        if (logger.isInfoEnabled())
        {
            StringBuffer buffer = new StringBuffer(1024);
            
            buffer.append("type��").append(listtype).append("--").append(typeName);
            buffer.append(System.getProperty("line.separator")).append("header��").append(tableHeader);
            buffer.append(System.getProperty("line.separator")).append("title��");
            for (int i = 0; i < tableTitle.length; i++)
            {
                buffer.append(tableTitle[i]).append("  ");
            }
            buffer.append(System.getProperty("line.separator")).append("tail��");
            for (int i = 0; i < tableTail.length; i++)
            {
                buffer.append(tableTail[i]).append("  ");
            }
            if (tableDescription != null && tableDescription.length > 0)
            {
                buffer.append(System.getProperty("line.separator")).append("description��");
                for (int i = 0; i < tableDescription.length; i++)
                {
                    buffer.append(tableDescription[i]).append("  ");
                }
            }
            
            logger.info(buffer.toString());
        }
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        String servnumber = customerSimp.getServNumber();
        
        String currMonth = CommonUtil.getLastMonth("yyyyMM", 0);
        // �ǵ�ǰ���굥������ӡ
        // ����ֻ����ѡ��ȫ���굥��ǰ���²��ܴ�ӡ
//        if (!currMonth.equals(month) && "true".equalsIgnoreCase(isQueryAll))
//        {
            //DetailedRecordsTimesPO timesPO = detailedRecordsService.getPrintTimes(servnumber, month);
            // yKF28472
            DetailedRecordsTimesPO timesPO = detailedRecordsService.getPrintTimes(servnumber, month, this.listtype);
            int times = 0;
            if (timesPO != null)
            {
                times = timesPO.getTimes();
            }
            
            // ֻ�д�ӡ����С������ֵ���ſ��Դ�ӡ������������ӡ
            if (times < Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_CDR_PRTTIMES)))
            {
                request.setAttribute("printFlag", "1");
            }
//        }
        
        // �ȴ�session��ȡ�굥��¼�����session���У�ֱ�ӷ��أ����session��û�У����ýӿڲ�ѯ
        // session�б�����굥���ݣ��������ƣ�Constants.LIST_DATA_SESSION_NAME + servNumber������ֵ��map
        // map�е����ݣ�key��201012-GSMCDR��value��Vector
        // Vector�б����¼��ÿ����¼��һ��String������ֶ�֮����","��������
        Vector records = null;
        
        Map detailedRecords = (Map)session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);
        
        // ����
        String feeType = "";
        if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
		{
        	feeType = "-" + fee_type;
		}

        if (detailedRecords != null && detailedRecords.get(month + "-" + typeName + feeType) != null)
        {
            records = (Vector)detailedRecords.get(month + "-" + typeName + feeType);
            
            Map totalMap = (Map)detailedRecords.get(month + "-" + typeName + feeType + "-SUM");
            
            request.setAttribute("totalMap", totalMap);
        }
        else
        {
            if (detailedRecords == null)
            {
                detailedRecords = new HashMap();
                session.setAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber, detailedRecords);
            }
            
            records = qryCDRList(listtype, month,fee_type,servnumber, tableTitle.length - 1);
            
            //δ��ѯ���굥��¼
            if (records == null || records.size() == 0)
            {
                String resultMsg = getConvertMsg("δ��ѯ���굥��¼", "zz_04_17_000001", null, formatParams(month, typeName, fee_type));
                
                request.setAttribute("errormessage", resultMsg);
                
                request.setAttribute("recordCount", "0");
                
                this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", resultMsg);
                
                //Change by LiFeng �޸� "�嵥��ѯȫ���嵥ʱ��ĳһ�嵥��ѯ�������ĺ�����嵥�����ܿ�" ��BUG
                //return "failed";
                return "success";
            }
            
            detailedRecords.put(month + "-" + typeName + feeType, records);
            
            Map totalMap = null;
            
            if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
    		{
            	totalMap = calDynTotalHUB(records);
    		}
            else if (Constants.PROOPERORGID_SD.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
            {
            	totalMap = calDynTotalSD(records);
            }
            else if (Constants.PROOPERORGID_NX.equals(PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
            {
                totalMap = calDynTotalNX(records);
            }
            else if (Constants.PROOPERORGID_CQ.equals(PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
            {
                totalMap = calDynTotalCQ(records);
            }
            
            detailedRecords.put(month + "-" + typeName + feeType + "-SUM", totalMap);
            
            request.setAttribute("totalMap", totalMap);
        }
        
        request.setAttribute("recordCount", "" + records.size());
        
        this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "0", "���굥(" + month + "-" + typeName + feeType + ")��ѯ�ɹ�");
        
        resultRecords = getQueryPageData(records);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryDetailedRecords End");
        }
        
        return "success";
    }
    
    /**
     * ��ȡ��ӡ����
     * 
     * @param request
     * @return
     */
    public String getPrintData()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("getPrintData Starting ...");
        }
        
        try
        {
            HttpServletRequest request = this.getRequest();
            HttpSession session = request.getSession();
            
            StringBuffer scripts = new StringBuffer("var printDataObject = {");
            StringBuffer records = new StringBuffer("");
            
            NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            String servnumber = customerSimp.getServNumber();
            
            String listType = request.getParameter("listtype");
            if (listType == null || listType.trim().length() < 1 || Integer.parseInt(listType) < 0)
            {
                // ���û��ȡ���嵥���ͣ��򷵻ؿ�
                scripts.append("printTail:'YES', errmsg:'�嵥���Ͳ�������:" + listType + "'}");
                
                request.setAttribute("printData", scripts.toString());
                
                if (logger.isDebugEnabled())
                {
                    logger.debug("getPrintData End");
                }
                
                return "success";
            }
            
            // yKF28472 20111126 �޸ĺ���ȫ����ӡ����
            if ("0".equals(listType))
            {
                // ����Ǵ�ӡȫ���嵥����Ĭ���ȴ�ӡ��һ������
                listType = "1";
            }
            else
            {
                listType = this.listtype;
            }
            
            int iListType = Integer.parseInt(listType);
            
            String typeServiceName = getTypeServiceName(listType);
            
            String page = request.getParameter("page");
            if (page == null || page.trim().length() < 1)
            {
                // �����ӡҳ�������򷵻ؿ�
                page = "1";
            }
            int iPage = Integer.parseInt(page);
            
            String qryMonth = request.getParameter("month");
            
            // ȡ���嵥����
            Vector dataV = null;
            Map totalM = null;
            
            Map detailedRecords = (Map)session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);

            if (detailedRecords != null && detailedRecords.get(qryMonth + "-" + typeServiceName) != null)
            {
                dataV = (Vector)detailedRecords.get(qryMonth + "-" + typeServiceName);
                
                totalM = (Map)detailedRecords.get(qryMonth + "-" + typeServiceName + "-SUM");
            }
            else
            {
                if (detailedRecords == null)
                {
                    detailedRecords = new HashMap();
                    session.setAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber, detailedRecords);
                }
                
                Object[] tableDetail = getTableDetail(listType);
                
                String[] tableTitle1 = (String[])(tableDetail[1]);
                
                // ��������_0��ȫ���嵥��1��������嵥��2����������嵥
                String fee_type = "";
                if (request.getParameter("fee_type") != null)
                {
                    fee_type = request.getParameter("fee_type");
                }
                else
                {
                    fee_type = "0";
                }
                
                dataV = qryCDRList(listType, qryMonth,fee_type, servnumber, tableTitle1.length - 1);
                detailedRecords.put(qryMonth + "-" + typeServiceName, dataV);
                
                // ����
                if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
        		{
                	this.typeName = typeServiceName;
                	totalM = calDynTotalHUB(dataV);
        		}
                else if (Constants.PROOPERORGID_SD.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
                {
                	this.typeName = typeServiceName;
                	totalM = calDynTotalSD(dataV);
                }
                else if (Constants.PROOPERORGID_NX.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
                {
                	this.typeName = typeServiceName;
                    totalM = calDynTotalNX(dataV);
                }
                detailedRecords.put(qryMonth + "-" + typeServiceName + "-SUM", totalM);
            }
            // ��ӡ����
            if (dataV != null && dataV.size() > 0)
            {
                int maxSize = iPage * Constants.DEFAULT_PRINT_PAGE_SIZE;
                maxSize = (maxSize <= dataV.size()) ? maxSize : dataV.size();
                
                String cstr = "";
                String[] sAry = null;
                if (iPage == 1)
                {
                	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
//                    createRecord(records, Constants.TYPE_TITLES[iListType - 1]);
                    createRecord(records, Constants.getTypeTitles()[iListType - 1]);
                    // end zKF66389 2015-09-10 9�·�findbugs�޸�
                }
                
                // ����
                if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
        		{
                	//1��ͨ���嵥
    				if ("GSMCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
    								+ sAry[3] + " "//��������
    								+ CommonUtil.formatServnumber(sAry[4]) + " "//�Է�����
    								+ CommonUtil.subDateTime(sAry[1], 6) + " "//��ʼʱ��
    								+ CommonUtil.fillBlank(sAry[2]) + "  "//ʱ��(��)
    								+ sAry[11].trim() + " " //����
    								+ sAry[8]);//����������
    					}
    				} 
    				//2�������嵥
    				else if ("SMSCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
    								+ CommonUtil.formatServnumber(sAry[3]) + "  "//�Է�����
    								+ CommonUtil.subDateTime(sAry[4], 3) + "   "//ͨ����ʼʱ��
    								+ (sAry.length > 6 ? sAry[6].trim() : ""));//���ŷ�
    					}
    				} 
    				//3�������嵥
    				else if ("IMSGCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
    								+ sAry[3]+"  "//�������
    								+ CommonUtil.fillBlank(sAry[1], 51, 10) + "  "//ҵ������
    								+ CommonUtil.subDateTime(sAry[6], 3) + "   "//��ʼʱ��
    								+ (sAry.length > 8 ? sAry[8].trim() : "0.00")+"  "//���·�
    								+ sAry[7].trim());//��Ϣ��
    					}
    				}
    				//4��GPRS�嵥
    				else if ("GPRSCDR".equals(typeServiceName) || "G3GPRSCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
    								+ sAry[0] + " " //����
    								+ CommonUtil.subDateTime(sAry[2], 4) + " "//��ʼʱ��
    								+ CommonUtil.fillBlank(CommonUtil.calByteToK(sAry[4]), 8) + " " //�Ʒ���������
    								+ CommonUtil.fillBlank(CommonUtil.calByteToK(sAry[5]), 8) + " " //�Ʒ���������
    								+ sAry[7] + " "//ҵ������
    								+ sAry[9]);//ͨ������
    					}
    				}
    				//5��WLAN�嵥
    				else if ("WLANCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
    								+ CommonUtil.subDateTime(sAry[3], 3) + "   "//ͨ����ʼʱ��
    								+ CommonUtil.fillBlank(sAry[4]) + "  "//ʱ��
    								+ sAry[1] + "   "//ͨ����
    								+ (sAry.length > 5 ? sAry[5].trim() : ""));//ͨ����
    					}
    				}
    				//6�������嵥
    				else if ("MMSCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, ""
    								+ CommonUtil.formatServnumber(sAry[7]) + " "//�Է�����
    								+ CommonUtil.subDateTime(sAry[8], 3) + "  "//����ʱ��
    								+ CommonUtil.fillBlank(sAry[10].trim(), 6) + "  "//ͨ�ŷ�
    								+ ((sAry.length > 11 ? sAry[11].trim() : "0.00") + "  "//��Ϣ��
    								+ String.valueOf(Float.parseFloat(sAry[10].trim())+Float.parseFloat((sAry.length > 11 ? sAry[11].trim() : "0.00")))));//�ܷ���
    					}
    				}
    				//7��������Ϣ���嵥
    				else if ("INFOFEECDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
    								+ CommonUtil.formatServnumber(sAry[4]) + "  "//�Է�����
    								+ (sAry.length > 5 ? sAry[5] : "") + "  "//������
    								+ CommonUtil.subDateTime(sAry[1], 6) + "   "//ͨ����ʼʱ��
    								+ CommonUtil.fillBlank(sAry[2]) + "  "//ʱ��
    								+ sAry[3].trim());//����
    					}
    				} 
    				//8��VPMN�嵥
    				else if ("VPMNCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
//    						createRecord(records, ""
//    								+ CommonUtil.formatServnumber(sAry[2]) + "  "//�Է�����
//    								+ CommonUtil.subDateTime(sAry[1], 6) + "  "//ͨ����ʼʱ��
//    								+ CommonUtil.fillBlank(sAry[2]) + "  "//ʱ��
//    								+ sAry[8] + "  " //ͨ���ص�
//    								+ sAry[9].trim());//ͨ�ŷ�
    						createRecord(records, ""
    								+ CommonUtil.formatServnumber(sAry[2]) + "  "//�Է�����
    								+ CommonUtil.subDateTime(sAry[3], 6) + "  "//ͨ����ʼʱ��
    								+ CommonUtil.fillBlank(sAry[4]) + "  "//ʱ��
    								+ sAry[5] + "  " //ͨ���ص�
    								+ sAry[6].trim());//ͨ�ŷ�
    					}
    				} 
    				//9��PIM�嵥
    				else if ("PIMMCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
    								+ CommonUtil.formatServnumber(sAry[2]) + " "//ҵ������
    								+ CommonUtil.subDateTime(sAry[4], 8) + "   "//��������ʱ��
    								+ CommonUtil.fillBlank(sAry[5].trim(), 6)+ "  "//����
    								+ (sAry.length > 6 ? sAry[6].trim() : ""));//���·�
    					}
    				}           
    				//10���ֻ������嵥
    			    else if ("FLASHCDRKF1".equals(typeServiceName)) {
	    				for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	    					createRecord(records, ""
	    							+ CommonUtil.subDateTime(sAry[4], 8) + "   "//URL����ʱ��
	    							+ sAry[2] + "  "//SPҵ�����
	    							+ sAry[1]+ "  "//SP��ҵ����
	    							+ sAry[7]+ "  "//��Ϣ��
	    							+ sAry[8]);//���·�
	    				}
    			    }
    				//11��G3�嵥
    			    else if ("G3GPRSCDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, ""
									+ sAry[0] + " " //����
									+ CommonUtil.subDateTime(sAry[2], 4) + " "//��ʼʱ��
									+ CommonUtil.fillBlank(CommonUtil.calByteToK(sAry[4]), 8) + " " //�Ʒ���������
									+ CommonUtil.fillBlank(CommonUtil.calByteToK(sAry[5]), 8) + " " //�Ʒ���������
									+ sAry[7] + " "//ҵ������
									+ sAry[9]);//ͨ������
    					}
    			    }
    				//12����Ϸ�㿨�嵥
    				else if ("GAMECDR".equals(typeServiceName)) {
    					for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++) {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
    						createRecord(records, "  "
    								+ sAry[1] + "  " //ҵ�����
    								+ (String)sAry[6].substring(0,11) + "   "//����ʱ��
    								+ CommonUtil.fillBlank(sAry[7].trim(), 6)+ "  "//���ε���
    								+ CommonUtil.fillBlank(sAry[8].trim(), 6));//���µ���
    					}
    				}
        		}
                //����
                else if (Constants.PROOPERORGID_NX.equalsIgnoreCase((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
                {
                    if ("GSMCDR".equals(typeServiceName))
                    {
                        for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
                        {
                            cstr = (String)dataV.get(i);
                            sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                            createRecord(records, "" + CommonUtil.fillRightBlank(sAry[1], 8) // ͨ������
                                    + CommonUtil.fillLeftBlank(CommonUtil.formatServnumber(sAry[2]), 11) + " " // �Զ˺���
                                    + CommonUtil.fillLeftBlank(sAry[3].substring(2), 17) + " " // ���� ʱ��
                                    + CommonUtil.fillLeftBlank(sAry[4].trim(), 6) + " " // ʱ��
                                    + CommonUtil.fillLeftBlank(sAry[6].trim(), 5)); // ����
                        }
                    }
                    else if ("IMSGCDR".equals(typeServiceName))
                    {
                        for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
                        {
                            cstr = (String)dataV.get(i);
                            sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                            createRecord(records, "" + CommonUtil.fillRightBlank(sAry[1], 18) + " "
                                    + CommonUtil.fillLeftBlank(sAry[4].substring(2), 17) + " "
                                    + CommonUtil.fillLeftBlank(sAry[5].trim(), 5));
                        }
                    }
                    else if ("GPRSCDR".equals(typeServiceName))
                    {
                        for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
                        {
                            cstr = (String)dataV.get(i);
                            sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                            createRecord(records, "" + CommonUtil.fillLeftBlank(sAry[2].substring(2), 17) + " "
                                    + CommonUtil.fillLeftBlank(sAry[4], 8) + " "
                                    + CommonUtil.fillLeftBlank(sAry[6], 8) + " " 
                                    + CommonUtil.fillLeftBlank(sAry[7], 5));
                        }
                    }
                }
                // ͨ��
                else
                {
	                if ("GSMCDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "" + CommonUtil.fillRightBlank(sAry[2], 4) + " " // ͨ������
	                                + CommonUtil.fillLeftBlank(CommonUtil.formatServnumber(sAry[1]), 11) + " " // �Զ˺���
	                                + CommonUtil.fillLeftBlank(sAry[4].trim(), 14) + " " // ���� ʱ��
	                                + CommonUtil.fillLeftBlank(sAry[5].trim(), 5) + " " // ʱ��
	                                + CommonUtil.fillLeftBlank(sAry[9].trim(), 5) + " " // ����
	                                + CommonUtil.fillLeftBlank(sAry[3].trim().replaceAll("&nbsp;", ""), 4)); // ����������
	                    }
	                }
	                else if ("SMSCDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "" + CommonUtil.fillRightBlank(sAry[2].trim(), 11) + " "
	                                + CommonUtil.fillLeftBlank(sAry[3].trim(), 13) + " "
	                                + CommonUtil.fillLeftBlank(sAry[4].trim(), 15) + " "
	                                + CommonUtil.fillLeftBlank(sAry[6].trim(), 5));
	                    }
	                }
	                else if ("MMSCDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "  " + sAry[1].trim() + "  " + CommonUtil.subDateTime(sAry[6], 3) + "   "
	                                + CommonUtil.fillBlank(sAry[2].trim(), 6) + "   "
	                                + CommonUtil.fillBlank(sAry[3].trim(), 6));
	                    }
	                }
	                else if ("GPRSCDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, ""
	                                + CommonUtil.fillRightBlank(sAry[1], 8)
	                                + " "
	                                + CommonUtil.fillLeftBlank(sAry[3].trim(), 8)
	                                + " "
	                                + CommonUtil.fillLeftBlank(CurrencyUtil.calByteToK(CurrencyUtil.addLong(sAry[4],
	                                        sAry[7])), 11)
	                                + " "
	                                + CommonUtil.fillLeftBlank(CurrencyUtil.calByteToK(CurrencyUtil.addLong(sAry[5],
	                                        sAry[8])), 11) + " " + CommonUtil.fillLeftBlank(sAry[10], 6));
	                    }
	                }
	                else if ("IMSGCDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "" + CommonUtil.fillRightBlank(sAry[1], 18) + " "
	                                + CommonUtil.fillLeftBlank(sAry[6], 14) + " "
	                                + CommonUtil.fillLeftBlank(sAry[2].trim(), 6) + " "
	                                + CommonUtil.fillLeftBlank(sAry[3].trim(), 7));
	                    }
	                }
	                else if ("WLANCDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "" + CommonUtil.fillRightBlank(sAry[1], 6) + " "
	                                + CommonUtil.fillLeftBlank(sAry[3], 14) + " " + CommonUtil.fillLeftBlank(sAry[4], 6)
	                                + " " + CommonUtil.fillLeftBlank(CurrencyUtil.calByteToK(sAry[5]), 6) + " "
	                                + CommonUtil.fillLeftBlank(CurrencyUtil.calByteToK(sAry[6]), 6) + " "
	                                + CommonUtil.fillLeftBlank(sAry[7], 5));
	                    }
	                }
	                else if ("INFOFEECDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "  " + CommonUtil.formatServnumber(sAry[3]) + "  "
	                                + (sAry.length > 4 ? sAry[4] : "") + "  " + CommonUtil.subDateTime(sAry[0], 6) + "   "
	                                + CommonUtil.fillBlank(sAry[1]) + "  " + sAry[2].trim());
	                    }
	                }
	                else if ("VPMNCDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "  " + CommonUtil.formatServnumber(sAry[3]) + "  "
	                                + CommonUtil.subDateTime(sAry[0], 6) + "  " + CommonUtil.fillBlank(sAry[1]) + "  "
	                                + sAry[7] + "  " + sAry[8].trim());
	                    }
	                }
	                else if ("LBSCDR".equals(typeServiceName))
	                {
	                    for (int i = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE; i < maxSize; i++)
	                    {
	                        cstr = (String)dataV.get(i);
	                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                        createRecord(records, "  " + sAry[1].trim() + "  " + CommonUtil.formatServnumber(sAry[3])
	                                + "  " + CommonUtil.subDateTime(sAry[9], 6) + "  "
	                                + CommonUtil.fillBlank(sAry[11].trim(), 6));
	                    }
	                }
                }
            }
            
            // �����¼����
            int recordCount = dataV != null ? dataV.size() : 0;
            int nextPage = iPage + 1;
            int pageCount = (recordCount % Constants.DEFAULT_PRINT_PAGE_SIZE == 0) ? (recordCount / Constants.DEFAULT_PRINT_PAGE_SIZE)
                    : (recordCount / Constants.DEFAULT_PRINT_PAGE_SIZE + 1);
            scripts.append("pageCount:" + pageCount);
            
            // ����ǵ�ǰ��ӡ�嵥�����һҳ�������
            if (iPage == pageCount)
            {
                createLine(records);
                
                // begin zKF66389 2015-09-10 9�·�findbugs�޸�
//                String[] typeTotalColumn = (String[])((Object[])Constants.TYPE_TABLE_DETAIL[iListType])[3];
//                String[] typeTotalColChnName = (String[])((Object[])Constants.TYPE_TABLE_DETAIL[iListType])[2];
                String[] typeTotalColumn = (String[])((Object[])Constants.getTypeTableDetail()[iListType])[3];
                String[] typeTotalColChnName = (String[])((Object[])Constants.getTypeTableDetail()[iListType])[2];
                // end zKF66389 2015-09-10 9�·�findbugs�޸�
                String s = "";
                StringBuffer sbuf = new StringBuffer(s);
                for (int i = 0; i < typeTotalColumn.length; i++)
                {
                    sbuf.append(typeTotalColChnName[i]).append(totalM.get(typeTotalColumn[i])).append(" ");
                }
                s = sbuf.toString();
                createRecord(records, s);
                createRecord(records, " ");
            }
            int tempType = SelfSvcCdrType.supportType(iListType);
            
            // int iNextListType = iListType + 1;// �嵥���ֱ�־�ǰ���1��9��˳�򣬼�1��ʾ��һ�嵥
            int iNextListType = SelfSvcCdrType.nextFactType(tempType + 1);
            
            String isPrintAll = request.getParameter("isPrintAll");
            // ȡ��һ��ӡҳ�ʹ�ӡ�嵥
            if (!"YES".equals(isPrintAll) && nextPage <= pageCount)
            {
                scripts.append(",currentListType:'" + listType + "', page:" + nextPage);
            }
            if ("YES".equals(isPrintAll))
            {
                if (nextPage <= pageCount)
                { // ��ǰ�嵥δ��ӡ��
                    scripts.append(",currentListType:'" + listType + "', page:" + nextPage);
                }
                // begin zKF66389 2015-09-10 9�·�findbugs�޸�
                //else if (nextPage > pageCount && iNextListType <= (Constants.TYPE_SERVICE_NAME_ARRAY.length - 1))
                else if (nextPage > pageCount && iNextListType <= (Constants.getTypeServiceNameArray().length - 1))
                // end zKF66389 2015-09-10 9�·�findbugs�޸�
                { // ��ǰ�嵥�Ѵ�ӡ�꣬������һҪ��ӡ�嵥����
                    scripts.append(",currentListType:'" + iNextListType + "', page:1");
                }
            }
            
            // �ж��Ƿ��ӡβ����Ϣ��������ӡ
            // begin zKF66389 2015-09-10 9�·�findbugs�޸�
//            if ("YES".equals(isPrintAll) && nextPage > pageCount && iNextListType > (Constants.TYPE_SERVICE_NAME_ARRAY.length - 1))
            if ("YES".equals(isPrintAll) && nextPage > pageCount && iNextListType > (Constants.getTypeServiceNameArray().length - 1))
            // end zKF66389 2015-09-10 9�·�findbugs�޸�
            { // �����ӡȫ���嵥�������嵥Ϊ���һ���嵥�������һҳ
                scripts.append(",printTail:'YES'");
            }
            if (!"YES".equals(isPrintAll) && nextPage > pageCount)
            { // ����Ǵ�ӡ�����嵥�������Ѿ���ӡ�����һҳ
                scripts.append(",printTail:'YES'");
            }
            
            // �ж��Ƿ��ӡ�嵥������Ϣ
            if ("YES".equals(isPrintAll) && iPage == 1 && recordCount > 0)
            {
            	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
//                scripts.append(",printTypeName:'" + Constants.TYPE_NAME_ARRAY[iListType] + "'");
                scripts.append(",printTypeName:'" + Constants.getTypeNameArray()[iListType] + "'");
                // end zKF66389 2015-09-10 9�·�findbugs�޸�
            }
            
            // ���ش�ӡ������ʾ
            if (iPage <= pageCount)
            {
                int startRecord = (iPage - 1) * Constants.DEFAULT_PRINT_PAGE_SIZE;
                int endRecord = iPage * Constants.DEFAULT_PRINT_PAGE_SIZE;
                endRecord = endRecord <= recordCount ? endRecord : recordCount;
                // begin zKF66389 2015-09-10 9�·�findbugs�޸�
//                String s = "���ڴ�ӡ��" + Constants.TYPE_NAME_ARRAY[iListType] + "����" + startRecord + "��" + endRecord + "��ļ�¼,���Ժ�...";
                String s = "���ڴ�ӡ��" + Constants.getTypeNameArray()[iListType] + "����" + startRecord + "��" + endRecord + "��ļ�¼,���Ժ�...";
                // end zKF66389 2015-09-10 9�·�findbugs�޸�
                
                scripts.append(",process:'" + s + "'");
            }
            
            scripts.append("}");
            
            String strRecords = "var printRecords = [";
            if (!"".equals(records.toString().trim()))
            {
                strRecords += records.substring(0, records.toString().length() - 1);
            }
            strRecords += "]";
            
            request.setAttribute("printData", scripts.toString() + ";" + strRecords);
            
            if (logger.isDebugEnabled())
            {
                logger.debug("getPrintData End");
            }
            
            return "success";
        }
        catch (Exception e)
        {
            logger.error("��װ�嵥��ӡ����ʧ�ܣ�", e);
            
            this.getRequest().setAttribute("errormessage", "��װ��ӡ���ݳ������굥��ӡʧ��");
            
            if (logger.isDebugEnabled())
            {
                logger.debug("getPrintData End");
            }
            
            return "failed";
        }
    }
    
    /**
     * �����굥��ӡ����
     * 
     * @see [�ࡢ��#��������#��Ա]
     */
    public String updatePrintTimes()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("updatePrintTimes Starting ...");
        }
        
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        String servNumber = customerSimp.getServNumber();
        
        //DetailedRecordsTimesPO timesPO = detailedRecordsService.getPrintTimes(servNumber, month);
        // yKF28472
        DetailedRecordsTimesPO timesPO = detailedRecordsService.getPrintTimes(servNumber, month, this.listtype);
        if (timesPO == null)
        {
            detailedRecordsService.addPrintTimes(servNumber, month, 1, this.listtype);
        }
        else
        {
            detailedRecordsService.updatePrintTimes(servNumber, month, timesPO.getTimes() + 1, this.listtype);
        }
        
        // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953        
        if (logger.isDebugEnabled())
        {
            logger.debug("updatePrintTimes End");
        }
        // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953 
        
        return "success";
        
    }
    
    private void createRecord(StringBuffer sb, String text)
    {
        sb.append("'" + text + "',");
    }
    
    private void createLine(StringBuffer sb)
    {
        sb.append("' ---------------------------------------------',");
    }
    
    /**
     * �����嵥���ͣ���ȡ�嵥����Ӣ������
     * 
     * @param type���嵥����
     * @return
     */
    private String getTypeServiceName(String type)
    {
        String typeServiceName = ""; // �嵥���ͷ���ӿ���
        
        // ѡ��ȫ���嵥��Ĭ�ϲ�ѯ��һ�����͵��嵥
        if ("0".equals(type))
        {
//        	 begin zKF66389 2015-09-10 9�·�findbugs�޸�
//            typeServiceName = Constants.TYPE_SERVICE_NAME_ARRAY[1];
            typeServiceName = Constants.getTypeServiceNameArray()[1];
            // end zKF66389 2015-09-10 9�·�findbugs�޸�
        }
        else
        {
        	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
            //typeServiceName = Constants.TYPE_SERVICE_NAME_ARRAY[Integer.parseInt(type)];
        	typeServiceName = Constants.getTypeServiceNameArray()[Integer.parseInt(type)];
            // end zKF66389 2015-09-10 9�·�findbugs�޸�
        }
        
        return typeServiceName;
    }
    
    /**
     * �����굥���ͣ���ȡ�����ʾ��Ϣ
     * 
     * @param type���굥����
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private Object[] getTableDetail(String type)
    {
        Object[] typeTableDetail = null; // ��ͷ
        
        if ("0".equals(type))
        {
        	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
//            typeTableDetail = (Object[])Constants.TYPE_TABLE_DETAIL[1];
            typeTableDetail = (Object[])Constants.getTypeTableDetail()[1];
            // end zKF66389 2015-09-10 9�·�findbugs�޸�
            
        }
        else
        {
        	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
//            typeTableDetail = (Object[])Constants.TYPE_TABLE_DETAIL[Integer.parseInt(type)];
            typeTableDetail = (Object[])Constants.getTypeTableDetail()[Integer.parseInt(type)];
            // end zKF66389 2015-09-10 9�·�findbugs�޸�
        }
        
        return typeTableDetail;
    }
    
    private Vector qryCDRList(String type, String qryMonth, String fee_type, String servnumber, int colNum)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCDRList Starting ...");
        }
        
        /**
         * cdrType�����ýӿ�ʱ���ݵ��굥���� 1��ͨ����2�����ţ�3��������4��GPRS��5��WLAN type��ҳ�洫�ݵ��굥���� 0/1��ͨ����2�����ţ�4��������5��GPRS��6��WLAN
         */
        String cdrType = "";
        if ("0".equals(type) || "1".equals(type))
        {
            cdrType = "1";
        }
        else
        {
        	cdrType = type;
        }
//        else if ("2".equals(type))
//        {
//            cdrType = "2";
//        }
//        else if ("4".equals(type))
//        {
//            cdrType = "3";
//        }
//        else if ("5".equals(type))
//        {
//            cdrType = "4";
//        }
//        else if ("6".equals(type))
//        {
//            cdrType = "5";
//        }
        
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCDRList End");
        }
        
        return detailedRecordsBean.queryCDRList(customerSimp, terminalInfo, qryMonth, fee_type,cdrType, curMenuId, colNum);
    }
    
    /**
     * ɽ��ͳ��
     * 
     * @param v
     * @param typeServiceName
     * @return
     */
    private Map calDynTotalSD(Vector v)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalSD Starting ...");
        }
        
        Map totalM = new HashMap();
        // �����嵥�ķ��ã������� �ϼ�
        String cstr = null;
        String[] sAry = null;
        if ("GSMCDR".equals(typeName))
        {
            totalM = totalGsmCdr(v, totalM);
            
        }
        else if ("SMSCDR".equals(typeName))
        {
            totalM = totalSmsCdr(v, totalM);
        }
        else if ("IMSGCDR".equals(typeName))
        {
            totalM = totalImsgCdr(v, totalM);
        }
        
        else if ("WLANCDR".equals(typeName))
        {
            totalM = totalWlanCdr(v, totalM);
        }
        else if ("GPRSCDR".equals(typeName))
        {
            totalM = totalGprsCdr(v, totalM);
        }
        
        if (v != null)
        {
        	totalM.put("recordcount", String.valueOf(v.size()));
        }
        else
        {
        	totalM.put("recordcount", "0");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalSD End");
        }
        
        return totalM;
    }

    // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
    /**
     * GPRSCDR ͳ��
     * <������ϸ����>
     * @param v
     * @param totalM
     * @see [�ࡢ��#��������#��Ա]
     */
    private Map totalGprsCdr(Vector v, Map totalM)
    {
        String cstr;
        String[] sAry;
        String totalGprs1 = "0";
        String totalGprs2 = "0";
        if(v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String)v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalGprs1 = CurrencyUtil.addInt(totalGprs1, sAry[4]);
                totalGprs1 = CurrencyUtil.addInt(totalGprs1, sAry[7]);
                
                totalGprs2 = CurrencyUtil.addInt(totalGprs2, sAry[5]);
                totalGprs2 = CurrencyUtil.addInt(totalGprs2, sAry[8]);
            }
        }
        totalM.put("totalgprs1", CurrencyUtil.calByteToK(totalGprs1));
        totalM.put("totalgprs2", CurrencyUtil.calByteToK(totalGprs2));
        
        return totalM;
    }

    /**
     * WLANCDR ͳ��
     * <������ϸ����>
     * @param v
     * @param totalM
     * @see [�ࡢ��#��������#��Ա]
     */
    private Map totalWlanCdr(Vector v, Map totalM)
    {
        String cstr;
        String[] sAry;
        String totalTimes = "0";
        String totalFee = "0";
        if(v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String)v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalFee = CurrencyUtil.add(totalFee, (sAry.length > 6 ? sAry[7] : "0"));
                totalTimes = CurrencyUtil.add(totalTimes, sAry[4], 0);
            }
        }
        totalM.put("totalfee", totalFee);
        totalM.put("totaltimes", CommonUtil.formatSecondsTime(totalTimes));
        
        return totalM;
    }

    /**
     * IMSGCDR ͳ��
     * <������ϸ����>
     * @param v
     * @param totalM
     * @see [�ࡢ��#��������#��Ա]
     */
    private Map totalImsgCdr(Vector v, Map totalM)
    {
        String cstr;
        String[] sAry;
        String totalFee1 = "0";
        if(v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String)v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalFee1 = CurrencyUtil.add(totalFee1, sAry[2]);
                totalFee1 = CurrencyUtil.add(totalFee1, sAry[3]);
            }
        }
        totalM.put("totalfee1", totalFee1);
        
        return totalM;
    }

    /**
     * SMSCDR ͳ��
     * <������ϸ����>
     * @param v
     * @param totalM
     * @see [�ࡢ��#��������#��Ա]
     */
    private Map totalSmsCdr(Vector v, Map totalM)
    {
        String cstr;
        String[] sAry;
        String totalSmsFee = "0";
        if(v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String)v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalSmsFee = CurrencyUtil.add(totalSmsFee, (sAry.length > 6 ? sAry[6] : "0"));
            }
        }
        totalM.put("totalfee", totalSmsFee);
        
        return totalM;
    }

    /**
     * GSMCDR ͳ��
     * <������ϸ����>
     * @param v
     * @param totalM
     * @see [�ࡢ��#��������#��Ա]
     */
    private Map totalGsmCdr(Vector v, Map totalM)
    {
        String cstr;
        String[] sAry;
        String totalTimes = "0";
        String totalGsmFee = "0";
        if(v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String)v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalGsmFee = CurrencyUtil.add(totalGsmFee, sAry[9]);
                totalTimes = CurrencyUtil.add(totalTimes, sAry[5], 0);
            }
        }
        totalM.put("totalfee", totalGsmFee);
        totalM.put("totaltimes", CommonUtil.formatSecondsTime(totalTimes));
        
        return totalM;
    }
    // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
    
    /**
     * ����ͳ��
     * 
     * @param v
     * @param typeServiceName
     * @return
     */
    private Map calDynTotalCQ(Vector v)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalCQ Starting ...");
        }
        
        Map totalM = new HashMap();
        // �����嵥�ķ��ã������� �ϼ�
        String cstr = null;
        String[] sAry = null;
        if ("GSMCDR".equals(typeName)||"SGSMCDR".equals(typeName)
        		||"LGSMCDR".equals(typeName)||"RGSMCDR".equals(typeName))
        {
            String totalGsmFee = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalGsmFee = CurrencyUtil.add(totalGsmFee, (sAry.length > 5 ? sAry[5] : "0"));
	            }
            }
            totalM.put("totalfee", totalGsmFee);
            
        }
        else if ("SMSCDR".equals(typeName))
        {
            String totalSmsFee = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalSmsFee = CurrencyUtil.add(totalSmsFee, (sAry.length > 5 ? sAry[5] : "0"));
	            }
            }
            totalM.put("totalfee", totalSmsFee);
        }
        else if ("GPRSCDR".equals(typeName))
        {
        	String totalGprsFee = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalGprsFee = CurrencyUtil.add(totalGprsFee, (sAry.length > 4 ? sAry[4] : "0"));
	            }
            }
            totalM.put("totalfee", totalGprsFee);
        }
        else if ("MMSCDR".equals(typeName))
        {
        	String totalFee1 = "0";
			String totalFee2 = "0";
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++) {
					cstr = (String) v.get(i);
					sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee1 = CurrencyUtil.add(totalFee1, (sAry.length > 5 ? sAry[5] : "0"));
					totalFee2 = CurrencyUtil.add(totalFee2, (sAry.length > 6 ? sAry[6] : "0"));
				}
			}
			totalM.put("totalfee1", totalFee1);
			totalM.put("totalfee2", totalFee2);
        }
        else if ("MMRCDR".equals(typeName))
        {
        	String totalMmrFee = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalMmrFee = CurrencyUtil.add(totalMmrFee, (sAry.length > 4 ? sAry[4] : "0"));
	            }
            }
            totalM.put("totalfee", totalMmrFee);
        }
        
        if (v != null)
        {
        	totalM.put("recordcount", String.valueOf(v.size()));
        }
        else
        {
        	totalM.put("recordcount", "0");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalCQ End");
        }
        
        return totalM;
    }
    
    /**
     * ����ͳ��
     * 
     * @param v
     * @param typeServiceName
     * @return
     */
    private Map calDynTotalHUB(Vector v)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalSD Starting ...");
        }
        
        Map totalM = new HashMap();
        // �����嵥�ķ��ã������� �ϼ�
        String cstr = null;
        String[] sAry = null;
        // ͨ��(cdrtype=1)
        if ("GSMCDR".equals(typeName))
        {
            String totalTimes = "0";
            String totalGsmFee = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalGsmFee = CurrencyUtil.add(totalGsmFee, sAry[11]);
					totalTimes = CurrencyUtil.add(totalTimes, sAry[2], 0);
	            }
            }
            totalM.put("totalfee", totalGsmFee);
            totalM.put("totaltimes", CommonUtil.formatSecondsTime(totalTimes));
            
        }
        // ����(cdrtype=2)
        else if ("SMSCDR".equals(typeName))
        {
            String totalSmsFee = "0";
            if (v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalSmsFee = CurrencyUtil.add(totalSmsFee, (sAry.length > 6 ? sAry[6] : "0"));
	            }
            }
            totalM.put("totalfee", totalSmsFee);
        }
        // ����(cdrtype=3)
        else if ("IMSGCDR".equals(typeName))
        {
            String totalFee1 = "0";
            String totalFee2 = "0";
            if (v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee1 = CurrencyUtil.add(totalFee1, sAry[7]);
					totalFee2 = CurrencyUtil.add(totalFee2, (sAry.length > 8 ? sAry[8] : "0"));
	            }
            }
            totalM.put("totalfee1", totalFee1);
            totalM.put("totalfee2", totalFee2);
        }
        // GRPS�굥(cdrtype=4)
        else if ("GPRSCDR".equals(typeName))
        {
            String totalGprs1 = "0";
            String totalGprs2 = "0";
            if (v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalGprs1 = CurrencyUtil.addInt(totalGprs1, CurrencyUtil.calByteToK(sAry[4])+"");
					totalGprs2 = CurrencyUtil.addInt(totalGprs2, CurrencyUtil.calByteToK(sAry[5])+"");
	            }
            }
            totalM.put("totalgprs1", CurrencyUtil.calByteToK(totalGprs1));
            totalM.put("totalgprs2", CurrencyUtil.calByteToK(totalGprs2));
        }
        // WLAN�굥(cdrtype=5)
        else if ("WLANCDR".equals(typeName))
        {
            String totalTimes = "0";
            String totalFee = "0";
            if (v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee = CurrencyUtil.add(totalFee,(sAry.length > 5 ? sAry[5] : "0"));
					totalTimes = CurrencyUtil.add(totalTimes, sAry[4], 0);
	            }
            }
            totalM.put("totalfee", totalFee);
            totalM.put("totaltimes", CommonUtil.formatSecondsTime(totalTimes));
        }
        // ����(cdrtype=6)
        else if ("MMSCDR".equals(typeName)) {
			String totalFee1 = "0";
			String totalFee2 = "0";
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++) {
					cstr = (String) v.get(i);
					sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee1 = CurrencyUtil.add(totalFee1, sAry[10]);
					//totalFee2 = CurrencyUtil.add(totalFee2, CurrencyUtil.add(sAry[8],(sAry.length > 9 ? sAry[9] : "0")));
					totalFee2 = CurrencyUtil.add(totalFee2, sAry[11]);
				}
			}
			totalM.put("totalfee1", totalFee1);
			totalM.put("totalfee2", totalFee2);
		}
        // ������Ϣ��(cdrtype=7)
        else if ("INFOFEECDR".equals(typeName)) {
			String totalTimes = "0";
			String totalFee = "0";
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++) {
					cstr = (String) v.get(i);
					sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee = CurrencyUtil.add(totalFee, sAry[3]);
					totalTimes = CurrencyUtil.add(totalTimes, sAry[2], 0);
				}
			}
			totalM.put("totalfee", totalFee);
			totalM.put("totaltimes", CommonUtil.formatSecondsTime(totalTimes));
		}
        // VPMN�嵥(cdrtype=8)
        else if ("VPMNCDR".equals(typeName)) {
			String totalFee = "0";
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++) {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);  
	                //totalFee = CurrencyUtil.add(totalFee, sAry[9]);
	                totalFee = CurrencyUtil.add(totalFee, sAry[6]);
				}
			}
			totalM.put("totalfee", totalFee);
		}
        // PIM�嵥��ѯ(cdrtype=9)
        else if ("PIMMCDR".equals(typeName)) {
			String totalFee1 = "0";
			String totalFee2 = "0";
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++) {
					cstr = (String) v.get(i);
					sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee1 = CurrencyUtil.add(totalFee1, sAry[5]);
					totalFee2 = CurrencyUtil.add(totalFee2, (sAry.length > 6 ? sAry[6] : "0"));
				}
			}
			totalM.put("totalfee1", totalFee1);
			totalM.put("totalfee2", totalFee2);
		}
        // �ֻ������嵥(cdrtype=10)
		else if ("FLASHCDRKF1".equals(typeName)) {
			String totalFee1 = "0";
			String totalFee2 = "0";
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++) {
					cstr = (String) v.get(i);
					sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee1 = CurrencyUtil.add(totalFee1, sAry[7]);
					totalFee2 = CurrencyUtil.add(totalFee2, (sAry.length > 9 ? sAry[8] : "0"));
				}
			}
			totalM.put("totalfee1", totalFee1);
			totalM.put("totalfee2", totalFee2);
		}
        // G3�嵥(cdrtype=11)
		else if ("G3GPRSCDR".equals(typeName)) {
            String totalGprs1 = "0";
            String totalGprs2 = "0";
            if (v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalGprs1 = CurrencyUtil.addInt(totalGprs1, CurrencyUtil.calByteToK(sAry[4])+"");
					totalGprs2 = CurrencyUtil.addInt(totalGprs2, CurrencyUtil.calByteToK(sAry[5])+"");
	            }
            }
            totalM.put("totalgprs1", CurrencyUtil.calByteToK(totalGprs1));
            totalM.put("totalgprs2", CurrencyUtil.calByteToK(totalGprs2));
		}
        // ��Ϸ�㿨�嵥(cdrtype=12)
		else if ("GAMECDR".equals(typeName)) {
			String totalFee1 = "0";
			String totalFee2 = "0";
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++) {
					cstr = (String) v.get(i);
					sAry = cstr.split(Constants.STR_SPLIT_TRANS);
					totalFee1 = CurrencyUtil.add(totalFee1, sAry[7]);
					totalFee2 = CurrencyUtil.add(totalFee2, sAry[8]);
				}
			}
			totalM.put("totalgame1", totalFee1);
			totalM.put("totalgame2", totalFee2);
		}
        
        if (v != null)
        {
        	totalM.put("recordcount", String.valueOf(v.size()));
        }
        else
        {
        	totalM.put("recordcount", "0");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalSD End");
        }
        
        return totalM;
    }
    
    /**
     * ����ͳ��
     * 
     * @param v
     * @param typeServiceName
     * @return
     */
    private Map calDynTotalNX(Vector v)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalNX Starting ...");
        }
        
        Map totalM = new HashMap();
        // �����嵥�ķ��ã������� �ϼ�
        String cstr = null;
        String[] sAry = null;
        if ("GSMCDR".equals(typeName))
        {
            String totalTimes = "0";
            String totalGsmFee = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalGsmFee = CurrencyUtil.add(totalGsmFee, sAry[6]);
	                totalTimes = CurrencyUtil.add(totalTimes, sAry[4], 0);
	            }
            }
            totalM.put("totalfee", totalGsmFee);
            totalM.put("totaltimes", CommonUtil.formatSecondsTime(totalTimes));
            
        }
        else if ("IMSGCDR".equals(typeName))
        {
            String totalFee1 = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalFee1 = CurrencyUtil.add(totalFee1, sAry[5]);
	            }
            }
            totalM.put("totalfee1", totalFee1);
        }        
        else if ("GPRSCDR".equals(typeName))
        {
            String totalGprs1 = "0";
            String totalGprs2 = "0";
            String totalfee = "0";
            if(v != null)
            {
	            for (int i = 0; i < v.size(); i++)
	            {
	                cstr = (String)v.get(i);
	                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
	                totalGprs1 = CurrencyUtil.add(totalGprs1, sAry[4]);                
	                totalGprs2 = CurrencyUtil.add(totalGprs2, sAry[6]);
	                totalfee = CurrencyUtil.add(totalfee, sAry[7]);
	            }
            }
            totalM.put("totalgprs1", totalGprs1);
            totalM.put("totalgprs2", totalGprs2);
            totalM.put("totalfee", totalfee);
        }
        
        if (v != null)
        {
        	totalM.put("recordcount", String.valueOf(v.size()));
        }
        else
        {
        	totalM.put("recordcount", "0");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalNX End");
        }
        
        return totalM;
    }
    
    /**
     * �ӻ����Map�л�ȡ��ѯ��ҳ����
     * 
     * @param request
     * @param dataM
     * @param listType
     * @return
     */
    private Vector getQueryPageData(Vector dataV)
    {
        Vector result = new Vector();
        if (dataV != null && dataV.size() > 0)
        {
            if (page == null || page.trim().length() < 1)
            {
                page = "1";
            }
            int iPage = Integer.parseInt(page);
            int maxSize = iPage * pageSize;
            maxSize = maxSize <= dataV.size() ? maxSize : dataV.size();
            for (int i = (iPage - 1) * pageSize; i < maxSize; i++)
            {
                result.add(dataV.get(i));
            }
        }
        
        return result;
    }
    
    /**
     * ��ѯ�Ƿ�ͨ139����
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public void qryMailbox() throws IOException
    {
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryMailbox Starting...");
        }
        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        this.getResponse().setContentType("text/html;charset=GBK");
        
        PrintWriter out = this.getResponse().getWriter();
        
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ѯ�û��Ƿ��ѿ�ͨ�ֻ�����
        Map result = detailedRecordsBean.qryMailbox(customerSimp, terminalInfoPO, curMenuId);
        
        if (result != null && result.size() > 0)
        {
            CTagSet tagSet = (CTagSet)result.get("returnObj");
            
            // 0��δ��ͨ 1���ѿ�ͨ
            haveMailbox = tagSet.GetValue("havemailbox");
            
            // modify by lKF60882 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹�������� 2017-4-1 begin
            if(StringUtils.isBlank(haveMailbox))
            {
                haveMailbox = tagSet.GetValue("ISHAVEMAILBOX");
            }
            // modify by lKF60882 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹�������� 2017-4-1 end
            
            // ��¼�ɹ���־
            this.createRecLog("qryMailbox", "0", "0", "0", "��ѯ�û��Ƿ��ѿ�ͨ�ֻ�����ɹ�!");
            
            if (haveMailbox != null && haveMailbox.equals("1"))
            {
                
                out.write("1");
                out.flush();
                
                sendMail("0");
            }
            
            if (haveMailbox != null && haveMailbox.equals("0"))
            {
                out.write("0");
                out.flush();
            }
        }
        else
        {
            this.getRequest().setAttribute("errormessage", "��ѯ�û��Ƿ��ѿ�ͨ�ֻ�����ʧ��!");
            
            // ��¼������־
            this.createRecLog("qryMailbox", "0", "0", "1", "��ѯ�û��Ƿ��ѿ�ͨ�ֻ�����ʧ��!");
            
            out.write("2");
            out.flush();
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryMailbox End!");
        }
    }
    
    /**
     * �����ʼ�
     * 
     * @return
     * @throws IOException
     * @see [�ࡢ��#��������#��Ա]
     */
    public void sendMail(String delayTime) throws IOException
    {
        logger.debug("sendMail Starting ...");
        
        StringBuffer mailContent = new StringBuffer(1024);
        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        this.getResponse().setContentType("text/html;charset=GBK");
        
        HttpSession session= request.getSession();
                
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        // �ֻ���
        String servnumber = customerSimp.getServNumber();
        
        // �嵥���� "GSMCDR"
        typeName = getTypeServiceName(listtype);        
        
        String feeType = "";
        
        // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
        if (CommonUtil.isNotEmpty(fee_type))
		{
        	feeType = "-" + fee_type;
		}
        // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
        
        // ��session��ȡ�굥��¼
        Vector records = null;        
        Map totalMap = null;
        
        Map detailedRecords = (Map) session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);
        if (null != detailedRecords && null != detailedRecords.get(month + "-" + typeName + feeType))
        {
            records = (Vector)detailedRecords.get(month + "-" + typeName + feeType);
            
            totalMap = (Map)detailedRecords.get(month + "-" + typeName + feeType + "-SUM");            
        }
        
        if (null == records || 0 == records.size())
        {
            logger.debug("sendMail End");
        	return;
        }        
        
        // ����嵥��ͷ��Ϣ [[��ͨ���굥��], [���, �Է�����, ��������, ����������, ͨ����ʼʱ��, ͨ��ʱ��(��), ������(Ԫ), ��;��(Ԫ), ��Ѷ��Ϣ��(Ԫ), �ܻ���(Ԫ)], [�굥������, ʱ���ϼƣ�, ���úϼ�(Ԫ)��], [recordcount, totaltimes, totalfee]]
        Object[] tableDetail = getTableDetail(listtype);
        
        // �嵥�������� "[��ͨ���굥��]"
        tableHeader = ((String[])(tableDetail[0]))[0];
        
        // ��ͷ���� [���, �Է�����, ��������, ����������, ͨ����ʼʱ��, ͨ��ʱ��(��), ������(Ԫ), ��;��(Ԫ), ��Ѷ��Ϣ��(Ԫ), �ܻ���(Ԫ)]
        tableTitle = (String[])(tableDetail[1]);
        
        // ƴװ�ʼ�����:��ͷ
    	mailContent.append("<html><head><style type='text/css'>")
    			.append(".tb_blue02,.tb_blue02 td ,.tb_blue02 th,.tb_num{border:1px solid #1a9ae3; border-collapse:collapse; font-size:14px; color:#fff; line-height:30px; height:30px; text-align:center;background:#056e99;}")
    			.append(".tit_info{ line-height:30px; height:30px; padding-left:40px; font-size:22px; position:relative;}")
    			.append("</style>" + "</head>" + "<body>" + "<p class='tit_info' align='left'><span class='bg'></span>")
    			.append(month).append("��").append(tableHeader)
    			.append("</p><table class='tb_blue02' align='center' width='100%'><tr class='tr_color'>");
        		
        for (int i = 0; i < tableTitle.length; i++)
        {
            mailContent.append("<th class='tr_color' align='center'>").append(tableTitle[i]).append("<br/></th>");
        }
        
        mailContent.append("</tr>");
        
        // �ϼ����� [�굥������, ʱ���ϼƣ�, ���úϼ�(Ԫ)��]
        tableTail = (String[])(tableDetail[2]);
        
        if (tableDetail.length >= 5)
        {
            tableDescription = (String[])tableDetail[4];
        }
        
        // ƴװ�ʼ����ݣ���ѯ���
        for (int i = 0; i < records.size(); i++)
        {
            String recordStr = (String)records.get(i);
            
            String[] recordArray = recordStr.split(",");
            
            mailContent.append("<tr>");
            
            for (int j = 0; j < recordArray.length; j++)
            {
                mailContent.append("<td align='center'>").append(recordArray[j]).append("</td>");
            }
            
            mailContent.append("</tr>");
        }        
        
        // ƴװ�ʼ����ݣ��굥������, ʱ���ϼƣ�, ���úϼ�(Ԫ)��
        mailContent.append("<tr><td colspan='").append(tableTitle.length).append("' align='left'>");
        
        // �ϼ�Ӣ�� [recordcount, totaltimes, totalfee]
        String[] summaryTitle = (String[])tableDetail[3];       
        for (int i = 0; i < summaryTitle.length; i++)
        {
            mailContent.append("<strong>").append(tableTail[i]).append("</strong>").append(totalMap.get(summaryTitle[i])).append("     ");
        }
        
        // ƴװ�ʼ����ݣ�������˵��
        if (tableDescription != null && tableDescription.length > 0)
        {
            mailContent.append("<tr><td colspan='").append(tableTitle.length).append("' align='left'>");
            
            for (int i = 0; i < tableDescription.length; i++)
            {
                mailContent.append(tableDescription[i]).append("<br/>");
            }
            
            mailContent.append("</td></tr>");
        }
        
        mailContent.append("</table></body></html>");
        
        // �ռ�������
        String to = servnumber + "@139.com";
        
        // �ʼ�����
        String subject = "��" + month + "�·ݵ��굥�ѵ�������ա�";
        
        // �ӳ�ʱ��,��λ������
        int delay = Integer.parseInt(delayTime);
        
        // �����ʼ�
        Timer timer = new Timer();
        timer.schedule(new SendMailUtil(to, subject, mailContent.toString()), new Date(
                System.currentTimeMillis() + delay * 60L * 1000));
        
        logger.debug("sendMail End");

    }
    
    /**
     * �굥�������俪ͨ
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public void add139Mail() throws IOException
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("addBillMail Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        this.getResponse().setContentType("text/html;charset=GBK");
        
        PrintWriter out = this.getResponse().getWriter();
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ���ýӿڿ�ͨ139����
        Map result = detailedRecordsBean.add139Mail(customer, terminalInfoPO, curMenuId);
        
        if (result != null && result.size() > 1)
        {
            // ��¼�ɹ���־
            this.createRecLog(Constants.ADD_BILLMAIL, "0", "0", "0", "139���俪ͨ�ɹ�");
            
            // �ӳ�ʱ�䣨���ӣ�
            String delayTime = (String) PublicCache.getInstance().getCachedData("SH_MAIL_DELAY");
            if (null == delayTime || "".equals(delayTime.trim()))
            {
            	delayTime = "3";
            }
            
            out.write("1");
            out.flush();
            
            // 139���俪ͨ�ɹ��������ʼ�
            sendMail(delayTime);
        }
        else
        {
            // ��¼������־
            this.createRecLog(Constants.ADD_BILLMAIL, "0", "0", "1", "139���俪ͨʧ��");
            
            out.write("0");
            out.flush();
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("addBillMail End");
        }
    }
    
    /**
     * ��ʾ��Ϣ����
     * 
     * @param strMonth ��ѯ�·�
     * @param strCDRType �굥����
     * @param strFeeType ��������
     * @return
     * @see 
     * @remark: create g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
     */
    private String[] formatParams(String strMonth, String strCDRType, String strFeeType)
    {
        String[] params = new String[]{"", "", "", ""};
        
        if (strMonth != null && strMonth.length() == 6)
        {
            params[0] = strMonth.substring(0, 4);
            params[1] = strMonth.substring(4);
        }
        
        if ("1".equals(strFeeType))
        {
            params[2] = "�������";
        }
        else if ("2".equals(strFeeType))
        {
            params[2] = "����Ϊ��";
        }
        
        // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
        params[3] = getCdrTypeName(strCDRType);
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
        
        return params;
    }

    /**
     * ��������
     * <������ϸ����>
     * @param strCDRType
     * @param params
     * @see [�ࡢ��#��������#��Ա]
     */
    private String getCdrTypeName(String strCDRType)
    {
        String cdrTypeName = "";
        if ("GSMCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "ͨ���굥";
        }
        else if ("SMSCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "�����굥";
        }
        else if ("IMSGCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "�ƶ������굥";
        }
        else if ("GPRSCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "GPRS�굥";
        }
        else if ("WLANCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "WLAN�굥";
        }
        else if ("MMSCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "�����굥";
        }
        else if ("INFOFEECDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "������Ϣ���굥";
        }
        else if ("VPMNCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "VPMN�굥";
        }
        else if ("PIMMCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "PIM�굥";
        }
        else if ("FLASHCDRKF1".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "�ֻ������굥";
        }
        else if ("G3GPRSCDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "G3�굥";
        }
        else if ("GAMECDR".equalsIgnoreCase(strCDRType))
        {
            cdrTypeName = "��Ϸ�㿨�굥";
        }
        return cdrTypeName;
    }
    
    public DetailedRecordsService getDetailedRecordsService()
    {
        return detailedRecordsService;
    }
    
    public void setDetailedRecordsService(DetailedRecordsService detailedRecordsService)
    {
        this.detailedRecordsService = detailedRecordsService;
    }
    
    public String getMonth()
    {
        return month;
    }
    
    public void setMonth(String month)
    {
        this.month = month;
    }
    
    public DetailedRecordsBean getDetailedRecordsBean()
    {
        return detailedRecordsBean;
    }
    
    public void setDetailedRecordsBean(DetailedRecordsBean detailedRecordsBean)
    {
        this.detailedRecordsBean = detailedRecordsBean;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getTypeName()
    {
        return typeName;
    }
    
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }
    
    public Vector getResultRecords()
    {
        return resultRecords;
    }
    
    public void setResultRecords(Vector resultRecords)
    {
        this.resultRecords = resultRecords;
    }
    
    public String getTableHeader()
    {
        return tableHeader;
    }
    
    public void setTableHeader(String tableHeader)
    {
        this.tableHeader = tableHeader;
    }
    
    public String[] getTableTitle()
    {
        return tableTitle.clone();
    }
    
    public void setTableTitle(String[] tableTitle)
    {
        this.tableTitle = tableTitle.clone();
    }
    
    public String[] getTableTail()
    {
        return tableTail.clone();
    }
    
    public void setTableTail(String[] tableTail)
    {
        this.tableTail = tableTail.clone();
    }
    
    public String[] getTableDescription()
    {
        if (tableDescription == null)
        {
            return tableDescription;
        }
        else
        {
            return tableDescription.clone();
        }
        
    }
    
    public void setTableDescription(String[] tableDescription)
    {
        this.tableDescription = tableDescription.clone();
    }
    
    public String getListtype()
    {
        return listtype;
    }
    
    public void setListtype(String listtype)
    {
        this.listtype = listtype;
    }
    
    public String getPage()
    {
        return page;
    }
    
    public void setPage(String page)
    {
        this.page = page;
    }
    
    public String getIsQueryAll()
    {
        return isQueryAll;
    }
    
    public void setIsQueryAll(String isQueryAll)
    {
        this.isQueryAll = isQueryAll;
    }
    
    public String getPagecount()
    {
        return pagecount;
    }
    
    public void setPagecount(String pagecount)
    {
        this.pagecount = pagecount;
    }

    public String getFee_type()
    {
        return fee_type;
    }

    public void setFee_type(String fee_type)
    {
        this.fee_type = fee_type;
    }
    
    // add begin cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652
    public String getHaveMailbox()
    {
        return haveMailbox;
    }
    
    public void setHaveMailbox(String haveMailbox)
    {
        this.haveMailbox = haveMailbox;
    }
    // add end cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652

    public String getMenuURL()
    {
        return menuURL;
    }

    public void setMenuURL(String menuURL)
    {
        this.menuURL = menuURL;
    }

    public int getPageSize()
    {
        return pageSize;
    }
}