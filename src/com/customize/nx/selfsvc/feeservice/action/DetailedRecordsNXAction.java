/*
* @filename: DetailedRecordsNXAction.java
* @  All Right Reserved (C), 2012-2106, HUAWEI TECO CO.
* @brif:  NG3.5���굥����֮�굥��ѯ
* @author: ��Ⱥ/g00140516
* @de:  2012/03/13 
* @description: 
* @remark: create g00140516 2012/03/13 R003C12L03n01 OR_NX_201203_128
*/
package com.customize.nx.selfsvc.feeservice.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.DetailedRecordsBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.feeservice.model.CycleInfoPO;
import com.gmcc.boss.selfsvc.feeservice.model.DetailedRecordsTimesPO;
import com.gmcc.boss.selfsvc.feeservice.service.DetailedRecordsService;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.CurrencyUtil;

/**
 * NG3.5���굥����֮�굥��ѯ
 * 
 * @author  g00140516
 * @version  1.0, 2012/03/13
 * @see  
 * @since  
 */
public class DetailedRecordsNXAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(DetailedRecordsNXAction.class);
    
    /**
     * �˵�
     */
    private String curMenuId = "";
    
    /**
     * �ɲ�ѯ�·��б�
     */
    private String[] months = null;
    
    /**
     * ��ѯ�·�
     */
    private String month = "";
    
    /**
     * �굥����
     */
    private String cdrType = "";
    
    /**
     * �굥������������
     */
    private String cdrTypeName = "";
    
    /**
     * �굥��¼�ֶ�
     */
    private String recordFields = "";
    
    /**
     * �ϼ���Ϣ
     */
    private Map<String, String> billTotal = null;
    
    /**
     * �ײ���̶����嵥��¼
     */
    private List<String> fixfeeRecords = null;
    
    /**
     * �ײ���̶���֮����������͵��굥��¼
     */
    private Vector resultRecords = null;
    
    /**
     * �ͻ�����
     */
    private String custName = "";
    
    /**
     * ����
     */
    private String cycle = "";
    
    /**
     * ���ڿ�ʼ����
     */
    private String startDate = "";
    
    /**
     * ���ڽ�������
     */
    private String endDate = "";
    
    /**
     * ��ѯ����
     */
    private String queryDate = "";
    
    /**
     * ���Ĳ�ѯ����
     */
    private String chQueryDate = "";
    
    /**
     * ���ĵ����ڿ�ʼ����
     */
    private String chStartDate = "";
    
    /**
     * ���ĵ����ڽ�������
     */
    private String chEndDate = "";
    
    /**
     * �����б�
     */
    private List<CycleInfoPO> cycles = null;
    
    /**
     * �ɰ��굥���͵�Ӣ������
     */
    private String typeName = "";
    
    /**
     * �ɰ��굥���͵���������
     */
    private String tableHeader = "";
    
    /**
     * �ɰ��굥��¼��ʾ�ֶ�
     */
    private String[] tableTitle = null;
    
    /**
     * �ɰ��굥��¼������
     */
    private String[] tableTail = null;
    
    /**
     * �ɰ��굥������˵��
     */
    private String[] tableDescription = null;
    
    /**
     * �ɰ��굥չʾ����
     */
    private int titleCols = 0;
    
    // delete begin g00140516 2013/02/01 R003C13L01n01 ÿ���12���£�ÿ���굥��ֻ�ܴ�ӡһ��
    // delete end g00140516 2013/02/01 R003C13L01n01 ÿ���12���£�ÿ���굥��ֻ�ܴ�ӡһ��

    private transient DetailedRecordsService detailedRecordsService = null;
    
    private transient DetailedRecordsBean detailedRecordsBean = null;
    
    private String additionalInfo = "";
    
    /**
     * ��ʾ�ɲ�ѯ�·�
     * 
     * @return
     * @see 
     */
    public String selectMonth()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("selectMonth Starting ...");
        }
        
        String forward = "months";
        
        HttpServletRequest request = this.getRequest();
        
        HttpSession session = request.getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        if (customerSimp != null)
        {
            // ɾ��session�б�����û����굥���ݡ�checkQueryAuth������Ҳ����һ�����˴���ɾ��һ�µ�ԭ���ǣ�����ʡ�ݲ���У���ѯȨ�ޣ���ֱ�ӽ���˺���
            session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + customerSimp.getServNumber());
        }        

        // �ɲ�ѯ��ǰ�º�ǰ����µ��굥��Ϣ
        months = CommonUtil.getLastMonths(Constants.DATE_PATTERN_YYYYMM, 6);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectMonth End");
        }
        
        return forward;
    }
    
    /**
     * ѡ������
     * 
     * @return
     * @see
     */
    public String selectCycle()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("selectCycle Starting ...");
        }
        
        String forward = "failed";
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        request.setAttribute("backStep", "-1");
        // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        // �ͻ���Ϣ��ѯ���ɹ�ʱӦ����һ��CTagSet��һ��CRSet
        Vector v = detailedRecordsBean.queryCustomerInfo(customerSimp, terminalInfo, month, "1", curMenuId);
        if (null == v || 2 > v.size())
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "�ͻ���Ϣ��ѯʧ��");
            
            logger.error("�ͻ�(" + customerSimp.getServNumber() + ")��Ϣ��ѯʧ��");
            
            request.setAttribute("errormessage", "�ͻ���Ϣ��ѯʧ�ܣ�");
        }
        else
        {
            CTagSet tagset = (CTagSet) v.get(0);
            custName = tagset.GetValue("custname");
            
            try
            {
                custName = URLEncoder.encode(custName, "GBK");
            }
            catch (UnsupportedEncodingException e)
            {
                logger.error("ʹ��GBK�Կͻ����Ʊ���ʱ�쳣", e);
            }
            
            CRSet rset = (CRSet) v.get(1);
            
            if (null == rset || 0 == rset.GetRowCount())
            {
                this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "�ͻ���Ϣ��ѯʧ��");
                
                logger.error("�ͻ�(" + customerSimp.getServNumber() + ")��Ϣ��ѯʧ��");
                
                request.setAttribute("errormessage", "�ͻ���Ϣ��ѯʧ�ܣ�");
            }
            else
            {
                int rowCount = rset.GetRowCount();
                
                // ��Ȼ������
                if (1 == rowCount)
                {
                    cycle = rset.GetValue(0, 0);
                    startDate = rset.GetValue(0, 1);
                    endDate = rset.GetValue(0, 2);
                    
                    forward = "singleCycle";
                }
                // ������ڶ�����
                else
                {
                    cycles = new ArrayList<CycleInfoPO>();
                    CycleInfoPO cycleInfo = null;
                    for (int i = 0; i < rowCount; i++)
                    {
                        cycleInfo = new CycleInfoPO();
                        cycleInfo.setCycle(rset.GetValue(i, 0));
                        cycleInfo.setStartDate(rset.GetValue(i, 1));
                        cycleInfo.setEndDate(rset.GetValue(i, 2));
                        
                        cycles.add(cycleInfo);
                    }
                    
                    forward = "cycles";
                }
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectCycle End");
        }
        
        return forward;
    }
    
    /**
     * �굥����ѡ��ҳ��
     * 
     * @return
     * @see 
     */
    public String selectType()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("selectType Starting ...");
        }
        
        String forward = "types";
        
        // �ж�����ʱ��㣬��ʽ��yyyyMMdd��
        String effectMonth = (String) PublicCache.getInstance().getCachedData(Constants.NEWCDR_EFFECTMONTH);
        
        if (null == effectMonth || "".equals(effectMonth))
        {
            logger.error("ϵͳδ�������굥��ѯ���ܵ�����ʱ��㣬��ʹ���µ��굥���ͽ��в�ѯ");
        }
        // �������ʱ���Ϊ20120320��ֻҪ��ѯ��ʼʱ��������ʱ���֮ǰ���Ͱ��ϰ��ѯ�����򣬰��°��ѯ����������ʱ��㵱��
        else if (startDate.compareTo(effectMonth) <= 0)
        {
            forward = "oldTypes";
        }
        
        HttpServletRequest request = this.getRequest();
        
        // ��ȡsession
        HttpSession session = request.getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ȡ�˵�����ܰ��ʾ��Ϣ
        List totalMenus = (List)PublicCache.getInstance().getCachedData(terminalInfoPO.getTermgrpid());
        
        MenuInfoPO menuInfoPO = null;
        for (int i = 0; i < totalMenus.size(); i++)
        {
            menuInfoPO = (MenuInfoPO)totalMenus.get(i);
            if (this.curMenuId.equals(menuInfoPO.getMenuid()))
            {
                break;
            }
            else
            {
                menuInfoPO = null;
            }
        }
        
        if (menuInfoPO != null)
        {
            additionalInfo = menuInfoPO.getAdditionalInfo();
        }
        
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectType End");
        }
        
        return forward;
    }
    
    /**
     * �ϰ��ѯ�굥��¼
     * 
     * @return
     * @see 
     */
    public String qryDetailedRecordsOld()
    {
        logger.debug("qryDetailedRecordsOld Starting ...");
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        // �嵥���� "GSMCDR"
        typeName = getTypeServiceName(cdrType);
        
        // ����嵥��ͷ��Ϣ [[��ͨ���굥��], [���, �Է�����, ��������, ����������, ͨ����ʼʱ��, ͨ��ʱ��(��), ������(Ԫ), ��;��(Ԫ), ��Ѷ��Ϣ��(Ԫ), �ܻ���(Ԫ)], [�굥������,
        // ʱ���ϼƣ�, ���úϼ�(Ԫ)��], [recordcount, totaltimes, totalfee]]
        Object[] tableDetail = getTableDetail(cdrType);
        
        // �嵥�������� "[��ͨ���굥��]"
        tableHeader = ((String[]) (tableDetail[0]))[0];
        
        // ��ͷ���� [���, �Է�����, ��������, ����������, ͨ����ʼʱ��, ͨ��ʱ��(��), ������(Ԫ), ��;��(Ԫ), ��Ѷ��Ϣ��(Ԫ), �ܻ���(Ԫ)]
        tableTitle = (String[]) (tableDetail[1]);
        titleCols = tableTitle.length;
        
        // ��ͷ����
        request.setAttribute("titleCols", tableTitle.length);
        
        // �ϼ����� [�굥������, ʱ���ϼƣ�, ���úϼ�(Ԫ)��]
        tableTail = (String[]) (tableDetail[2]);
        
        // �ϼ�Ӣ�� [recordcount, totaltimes, totalfee]
        request.setAttribute("summaryTitle", tableDetail[3]);
        
        if (tableDetail.length >= 5)
        {
            tableDescription = (String[]) tableDetail[4];
        }
        
        /**
         * ƴװ���� type��0--GSMCDR header����ͨ���굥�� title����� �Է����� �������� ���������� ͨ����ʼʱ�� ͨ��ʱ��(��) ������(Ԫ) ��;��(Ԫ) ��Ѷ��Ϣ��(Ԫ) �ܻ���(Ԫ)
         * tail���굥������ ʱ���ϼƣ� ���úϼ�(Ԫ)��
         */
        if (logger.isDebugEnabled())
        {
            StringBuffer buffer = new StringBuffer(1024);
            
            buffer.append("type��").append(cdrType).append("--").append(typeName);
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
            
            logger.debug(buffer.toString());
        }
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        String servnumber = customerSimp.getServNumber();
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        // �ȴ�session��ȡ�굥��¼�����session���У�ֱ�ӷ��أ����session��û�У����ýӿڲ�ѯ
        // session�б�����굥���ݣ��������ƣ�Constants.LIST_DATA_SESSION_NAME + servNumber������ֵ��map
        // map�е����ݣ�key��201012-GSMCDR��value��Vector
        // Vector�б����¼��ÿ����¼��һ��String������ֶ�֮����","��������
        resultRecords = null;
        
        Map detailedRecords = (Map) session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);

        if (detailedRecords != null && detailedRecords.get(cycle + "-" + typeName) != null)
        {
            resultRecords = (Vector)detailedRecords.get(cycle + "-" + typeName);
            
            Map totalMap = (Map)detailedRecords.get(cycle + "-" + typeName + "-SUM");
            
            request.setAttribute("totalMap", totalMap);
        }
        else
        {
            if (detailedRecords == null)
            {
                detailedRecords = new HashMap();
                session.setAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber, detailedRecords);
            }
            
            resultRecords = detailedRecordsBean.queryCDRListNXOld(customerSimp,
                    terminalInfo,
                    "",
                    cdrType,                   
                    curMenuId,
                    tableTitle.length - 1,
                    startDate,
                    endDate);          
        }
        
        //δ��ѯ���굥��¼
        if (resultRecords == null || resultRecords.size() == 0)
        {
            request.setAttribute("errormessage", "���굥(" + cycle + "-" + typeName + ")��ѯ������δ�ҵ����������ļ�¼");
            
            request.setAttribute("recordCount", "0");
            
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "���굥(" + cycle + "-" + typeName + ")��ѯ������δ�ҵ����������ļ�¼");

            return "success";
        }
        
        detailedRecords.put(cycle + "-" + typeName, resultRecords);
        
        Map totalMap = calDynTotalNX(resultRecords);
        
        detailedRecords.put(cycle + "-" + typeName + "-SUM", totalMap);
        
        request.setAttribute("totalMap", totalMap);
        
        this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "0", "���굥(" + cycle + "-" + typeName + ")��ѯ�ɹ�");
        
        // delete begin g00140516 2013/02/01 R003C13L01n01 ÿ���12���£�ÿ���굥��ֻ�ܴ�ӡһ��
        // delete end g00140516 2013/02/01 R003C13L01n01 ÿ���12���£�ÿ���굥��ֻ�ܴ�ӡһ��
        
        logger.debug("qryDetailedRecordsOld End");
        
        return "success";
    }
    
    /**
     * �°��ȡ�굥��¼
     * 
     * @return
     * @see 
     */
    public String qryDetailedRecords()
    {        
        logger.debug("qryDetailedRecords Starting ...");
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        Object[] cdrTypeInfo = Constants.CDRTYPEMAP.get(cdrType);
        
        // �����굥����δ��ȡ����Ӧ����Ϣ
        if (null == cdrTypeInfo || 2 > cdrTypeInfo.length)
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "ϵͳ��֧���굥����" + cdrType);
            
            logger.error("ϵͳ��֧���굥����" + cdrType);
            
            // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
            request.setAttribute("backStep", "-1");
            // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
            
            request.setAttribute("errormessage", "ϵͳ��֧���굥����" + cdrType + "������ϵӪҵ��Ա�����");
            
            return "failed";
        }
        
        cdrTypeName = (String) cdrTypeInfo[0];
        
        recordFields = (String) cdrTypeInfo[1];        
        
        try
        {
            custName = URLDecoder.decode(custName, "GBK");
        }
        catch (UnsupportedEncodingException e1)
        {
            logger.error("ʹ��GBK�Կͻ����ƽ���ʱ�쳣", e1);
        }
                
        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMMDD);
        SimpleDateFormat sdf2 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMMDD_CH);
        
        Date date = new Date();
        chQueryDate = sdf2.format(date);
        
        try
        {
            date = sdf1.parse(startDate);
            chStartDate = sdf2.format(date);
        }
        catch (ParseException e)
        {
            logger.error("��ʽ�����ڿ�ʼʱ�����", e);
            
            chStartDate = startDate;
        }
        
        try
        {
            date = sdf1.parse(endDate);
            chEndDate = sdf2.format(date);
        }
        catch (ParseException e)
        {
            logger.error("��ʽ�����ڿ�ʼʱ�����", e);
            
            chEndDate = endDate;
        }
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        String servnumber = customerSimp.getServNumber();        
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        /*
         * ĳ�����͵��굥��¼��Ϣ
         */
        Map<String, String> resultMap = null;
        
        /*
         * session�б�����û��굥��¼�����ݽṹ��
         * ʹ��Map����û���ȫ���굥��¼����ֵΪ(cycle + "-" + cdrType)����Ӧ���굥��¼ΪresultMap
         */
        Map<String, Map<String, String>> detailedRecords = (Map<String, Map<String, String>>) session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);
        if (null != detailedRecords)
        {
            resultMap = detailedRecords.get(cycle + "-" + cdrType);
        }
        else
        {
            detailedRecords = new HashMap<String, Map<String, String>>();
            
            session.setAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber, detailedRecords);
        }
        
        // ��session��δȡ���굥��¼������ú�̨�ӿڻ�ȡ��ͬʱ��ŵ�session��
        if (null == resultMap || 0 == resultMap.size())
        {
        	// modify begin g00140516 2013/02/28 R003C13L02n01 BUG_NX_201302_123 �������ڲ�ѯ�굥ʱ����������Σ�ISCYCLE��BILLCYCLE
        	resultMap = detailedRecordsBean.queryDetailedRecordsNX2012(customerSimp, terminalInfo, cdrType, startDate, endDate, curMenuId, cycle);
        	// modify end g00140516 2013/02/28 R003C13L02n01 BUG_NX_201302_123 �������ڲ�ѯ�굥ʱ����������Σ�ISCYCLE��BILLCYCLE
        	
            detailedRecords.put(cycle + "-" + cdrType, resultMap);
        }
        
        if (null != resultMap)
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "0", "���굥(" + cycle + "-" + cdrType + ")��ѯ�ɹ�");
            
            if (Constants.CDRTYPE_FIXFEE.equalsIgnoreCase(cdrType))
            {
                // �ײ���̶����嵥
                parseFixfeeRecords(resultMap);
            }
            else
            {
                // ���ײ���̶����嵥
                parseSingleTypeRecords(resultMap);
            }
            
            // delete begin g00140516 2013/02/01 R003C13L01n01 ÿ���12���£�ÿ���굥��ֻ�ܴ�ӡһ��
            // delete end g00140516 2013/02/01 R003C13L01n01 ÿ���12���£�ÿ���굥��ֻ�ܴ�ӡһ��
        }
        else
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "���굥(" + cycle + "-" + cdrType + ")��ѯ������δ�ҵ����������ļ�¼");
        }
        
        logger.debug("qryDetailedRecords End");
        
        return cdrType;
    }
    
    /**
     * �����ײͼ��̶����굥��¼
     * 
     * @param allRecords
     * @return
     * @see
     */
    private void parseFixfeeRecords(Map<String, String> resultMap)
    {
        // ����굥��¼
        fixfeeRecords = new ArrayList<String>();
        
        // ��źϼ���Ϣ
        billTotal = new HashMap<String, String>();        
        
        String allRecords = resultMap.get(cdrType);
        if (null == allRecords || "".equals(allRecords.trim()))
        {
            return;
        }
        
        // �ϼƷ���
        String totalFee = resultMap.get("totalfee");        
        
        String[] recordsArray = allRecords.split("\\|");
        int recordsCount = recordsArray.length;
        
        // ������¼
        String record = "";        
        
        for (int i = 1; i < recordsCount; i++)
        {
            record = recordsArray[i];
            
            // �ռ�¼
            if (null == record || "".equals(record.trim()))
            {
                continue;
            }
            
            // �����@_@��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
            if (record.endsWith("@_@"))
            {
                record = record + " ";
            }
            
            // ��¼��������@_@@_@��˵���м�ĳ���ֶ�Ϊ�գ�ʹ�ÿո���棬��Ϊ@_@ @_@
            record = record.replaceAll("@@", "@ @");
            
            fixfeeRecords.add(record);
        }
        
        billTotal.put("totalFee", totalFee);
    }
    
    /**
     * ���ײͼ��̶����굥
     * 
     * @param allRecords
     * @return
     * @see
     */
    private void parseSingleTypeRecords(Map<String, String> resultMap)
    {
        // ��Ž�������굥��¼����ʽ��8��1�գ�8��1�յ�ȫ����¼��8��2�գ�8��2�յ�ȫ����¼
        resultRecords = new Vector();
        
        // ��źϼ���Ϣ
        billTotal = new HashMap<String, String>();       
        
        // ������¼
        String record = "";
        
        // ������¼�ĸ��ֶ�
        String[] fields = null;
        
        // ������¼��ʱ��
        String timeField = "";
        
        // ������¼��Ӧ������
        String day = "";
        
        // ���ͬһ������м�¼
        List<String> dayRecords = null;
        
        String lastDay = "";
        
        String allRecords = resultMap.get(cdrType);
        if (null == allRecords || "".equals(allRecords.trim()))
        {
            return;
        }        
        
        // �ϼƷ���
        billTotal.put("totalFee", resultMap.get("totalfee"));
        
        // ����������
        billTotal.put("smsTotalNum", resultMap.get("smstotalnum"));
        
        // ����������
        billTotal.put("mmsTotalNum", resultMap.get("mmstotalnum"));
        
        // ����ͨ����
        billTotal.put("localcall", resultMap.get("localcall"));
                
        // ��;ͨ����
        billTotal.put("longcall", resultMap.get("longcall"));   
        
        // ����ͨ����
        billTotal.put("roamcall", resultMap.get("roamcall"));
        
        // �л���
        billTotal.put("citycall", resultMap.get("citycall"));
        
        // ����ҵ���
        billTotal.put("othercall", resultMap.get("othercall"));
        
        // ���ڳ�;
        billTotal.put("inlancall", resultMap.get("inlancall"));
        
        // �۰�̨��;
        billTotal.put("colonycall", resultMap.get("colonycall"));
        
        // ���ʳ�;
        billTotal.put("interncall", resultMap.get("interncall"));
        
        // ��������
        billTotal.put("inlanroam", resultMap.get("inlanroam"));
        
        // �۰�̨����
        billTotal.put("colonyroam", resultMap.get("colonyroam"));
        
        // ��������
        billTotal.put("internroam", resultMap.get("internroam"));
        
        // GPRS��������
        billTotal.put("gprssum", resultMap.get("gprssum"));
        
        // �շ�����
        billTotal.put("chargeflux", resultMap.get("chargeflux"));
        
        // �������
        billTotal.put("freechargeflux", resultMap.get("freechargeflux"));
        
        // ������
        billTotal.put("sumflux", resultMap.get("sumflux"));
        
        // wlan��ʱ��
        billTotal.put("wlansumtime", resultMap.get("wlansumtime"));
        
        // wlan�ܷ���
        billTotal.put("wlansumfee", resultMap.get("wlansumfee"));
        
        // wlan��������
        billTotal.put("wlansum", resultMap.get("wlansum"));
        
        // wlan������
        billTotal.put("wlansumflux", resultMap.get("wlansumflux"));
                
        // gprs�ܷ���
        billTotal.put("gprssumfee", resultMap.get("gprssumfee"));
        
        // CMWAP����
        billTotal.put("cmwapsum", resultMap.get("cmwapsum"));
                
        // CMNET����
        billTotal.put("cmnetsum", resultMap.get("cmnetsum"));
        
        // CMWAP�շ�����
        billTotal.put("cmwapflux", resultMap.get("cmwapflux"));
                
        // CMNET�շ�����
        billTotal.put("cmnetflux", resultMap.get("cmnetflux"));
                
        // CMWAP�������
        billTotal.put("cmwapfreeflux", resultMap.get("cmwapfreeflux"));
        
        // CMNET�������
        billTotal.put("cmnetfreeflux", resultMap.get("cmnetfreeflux"));
        
        // CMWAP������ 
        billTotal.put("cmwapsumflux", resultMap.get("cmwapsumflux"));
        
        // CMNET������
        billTotal.put("cmnetsumflux", resultMap.get("cmnetsumflux"));
        
        // ����WLAN����
        billTotal.put("pubwlansum", resultMap.get("pubwlansum"));
     
        // У԰WLAN����
        billTotal.put("campuswlansum", resultMap.get("campuswlansum"));
     
        // ����WLAN����
        billTotal.put("pubwlanflux", resultMap.get("pubwlanflux"));
        
        // У԰WLAN����
        billTotal.put("campuswlanflux", resultMap.get("campuswlanflux"));
        
        // ����WLANʱ��
        billTotal.put("pubwlantime", resultMap.get("pubwlantime"));
     
        // У԰WLANʱ��
        billTotal.put("campuswlantime", resultMap.get("campuswlantime"));
     
        // ����WLAN����
        billTotal.put("pubwlanfee", resultMap.get("pubwlanfee"));
     
        // У԰WLAN����
        billTotal.put("campuswlanfee", resultMap.get("campuswlanfee"));
     
        // CMWAP����
        billTotal.put("cmwapfee", resultMap.get("cmwapfee"));
     
        // CMNET����
        billTotal.put("cmnetfee", resultMap.get("cmnetfee"));
        
        String[] recordsArray = allRecords.split("\\|");
        int recordsCount = recordsArray.length;
        
        for (int i = 1; i < recordsCount; i++)
        {
            record = recordsArray[i];
            
            // �ռ�¼
            if (null == record || "".equals(record.trim()))
            {
                continue;
            }
            
            // �����@_@��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
            if (record.endsWith("@_@"))
            {
                record = record + " ";
            }
            
            // ��¼��������@_@@_@��˵���м�ĳ���ֶ�Ϊ�գ�ʹ�ÿո���棬��Ϊ@_@ @_@
            record = record.replaceAll("@@", "@ @");
            
            fields = record.split("@_@");
            
            timeField = fields[0];
            
            // ÿ����¼�ĵ�һ���ֶζ���ʱ�䣬ʱ���ʽӦΪyyyy-mm-dd hh24:mi:ss������ȡ����yyyy-mm-dd
            int index = timeField.indexOf(" ");
            if (-1 != index)
            {
                day = timeField.substring(0, index);
            }
            else
            {
                day = timeField;
            }
            
            // �����ǰ��¼��������֮ǰ�����ļ�¼�����ڲ�ͬ������vector���������ڣ�ͬʱ�ں���׷�ӱ�������¼���б�
            // ��vector�б��������Ϊ��8��1�գ�8��1�յ�ȫ����¼��8��2�գ�8��2�յ�ȫ����¼
            if (!lastDay.equals(day))
            {
                lastDay = day;
                
                dayRecords = new ArrayList<String>();
                
                resultRecords.add(day);
                resultRecords.add(dayRecords);
            }
            
            dayRecords.add(record.substring(index + 1));
        }
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
        	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
//            typeServiceName = Constants.TYPE_SERVICE_NAME_ARRAY[1];
            typeServiceName = Constants.getTypeServiceNameArray()[1];
            // end zKF66389 2015-09-10 9�·�findbugs�޸�
        }
        else
        {
        	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
//            typeServiceName = Constants.TYPE_SERVICE_NAME_ARRAY[Integer.parseInt(type)];
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
//            typeTableDetail = (Object[]) Constants.TYPE_TABLE_DETAIL[1];
            typeTableDetail = (Object[]) Constants.getTypeTableDetail()[1];
            // end zKF66389 2015-09-10 9�·�findbugs�޸�
        }
        else
        {
        	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
//            typeTableDetail = (Object[]) Constants.TYPE_TABLE_DETAIL[Integer.parseInt(type)];
            typeTableDetail = (Object[]) Constants.getTypeTableDetail()[Integer.parseInt(type)];
            // end zKF66389 2015-09-10 9�·�findbugs�޸�
        }
        
        return typeTableDetail;
    }
    
    /**
     * �ϰ��ͳ����Ϣ
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
     * ��ȡ�ϰ��ӡ����
     * 
     * @param request
     * @return
     */
    public String getPrintData()
    {
        logger.debug("getPrintData Starting ...");
        
        try
        {
            HttpServletRequest request = this.getRequest();
            HttpSession session = request.getSession();
            
            StringBuffer scripts = new StringBuffer("var printDataObject = {");
            StringBuffer records = new StringBuffer("");
            
            TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
            
            NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            String servnumber = customerSimp.getServNumber();
            
            int iListType = Integer.parseInt(cdrType);
            
            String typeServiceName = getTypeServiceName(cdrType);
            
            // ȡ���嵥����
            Vector dataV = null;
            Map totalM = null;
            
            Map detailedRecords = (Map)session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);
            if (detailedRecords != null && detailedRecords.get(cycle + "-" + typeServiceName) != null)
            {
                dataV = (Vector)detailedRecords.get(cycle + "-" + typeServiceName);
                
                totalM = (Map)detailedRecords.get(cycle + "-" + typeServiceName + "-SUM");
            }
            else
            {
                if (detailedRecords == null)
                {
                    detailedRecords = new HashMap();
                    session.setAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber, detailedRecords);
                }
                
                dataV = detailedRecordsBean.queryCDRListNXOld(customerSimp,
                        terminalInfo,
                        "",
                        cdrType,                   
                        curMenuId,
                        tableTitle.length - 1,
                        startDate,
                        endDate);
                
                totalM = calDynTotalNX(dataV);
                
                detailedRecords.put(cycle + "-" + typeServiceName, dataV); 
                detailedRecords.put(cycle + "-" + typeServiceName + "-SUM", totalM);
            }
            
            // ��ӡ����
            //if (dataV != null && dataV.size() > 0)
            if (isNotEmpty(dataV))
            {
            	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
//                createRecord(records, Constants.TYPE_TITLES[iListType - 1]);
                createRecord(records, Constants.getTypeTitles()[iListType - 1]);
                // end zKF66389 2015-09-10 9�·�findbugs�޸�
                
                String cstr = "";
                String[] sAry = null;
                
                int maxSize = dataV.size();
                
                if ("GSMCDR".equals(typeServiceName))
                {
                    for (int i = 0; i < maxSize; i++)
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
                    for (int i = 0; i < maxSize; i++)
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
                    for (int i = 0; i < maxSize; i++)
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
            
            createLine(records);
            
            // begin zKF66389 2015-09-10 9�·�findbugs�޸�
//            String[] typeTotalColumn = (String[]) ((Object[]) Constants.TYPE_TABLE_DETAIL[iListType])[3];
//            String[] typeTotalColChnName = (String[]) ((Object[]) Constants.TYPE_TABLE_DETAIL[iListType])[2];
            String[] typeTotalColumn = (String[]) ((Object[]) Constants.getTypeTableDetail()[iListType])[3];
            String[] typeTotalColChnName = (String[]) ((Object[]) Constants.getTypeTableDetail()[iListType])[2];
            // end zKF66389 2015-09-10 9�·�findbugs�޸�
            String s = "";
            StringBuffer sbuf  = new StringBuffer();
            for (int i = 0; i < typeTotalColumn.length; i++)
            {
                sbuf.append(typeTotalColChnName[i]).append(totalM.get(typeTotalColumn[i])).append(" ");
            }
            s = sbuf.toString();
            createRecord(records, s);
            createRecord(records, " ");
            
            scripts.append("printTail:'YES'");
            
            // begin zKF66389 2015-09-10 9�·�findbugs�޸�
//            scripts.append(",printTypeName:'" + Constants.TYPE_NAME_ARRAY[iListType] + "'");
            scripts.append(",printTypeName:'" + Constants.getTypeNameArray()[iListType] + "'");
            // end zKF66389 2015-09-10 9�·�findbugs�޸�
            
            scripts.append("}");
            
            String strRecords = "var printRecords = [";
         // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
//            if (!"".equals(records.toString().trim()))
//            {
//                strRecords += records.substring(0, records.toString().length() - 1);
//            }
            strRecords = (!"".equals(records.toString().trim())) ? strRecords + records.substring(0, records.toString().length() - 1) : strRecords;
            // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
            strRecords += "]";
            
            request.setAttribute("printData", scripts.toString() + ";" + strRecords);
            
            logger.debug("getPrintData End");
            
            return "success";
        }
        catch (Exception e)
        {
            logger.error("��װ�嵥��ӡ����ʧ�ܣ�", e);
            
            this.getRequest().setAttribute("errormessage", "��װ��ӡ���ݳ������굥��ӡʧ��");
            
            logger.debug("getPrintData End");
            
            return "failed";
        }
    }
    
    /**
     * �ж��Ƿ�Ϊ��
     * <������ϸ����>
     * @param dataV
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private boolean isNotEmpty(Vector dataV)
    {
        return dataV != null && dataV.size() > 0;
    }
    
    /**
     * �°��굥��ѯ��ȡ��ӡ����
     * 
     * @param request
     * @return
     */
    public String getPrintDataNew()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("getPrintDataNew Starting ...");
        }
        
        try
        {
            HttpServletRequest request = this.getRequest();
            HttpSession session = request.getSession();           
            
            StringBuffer records = new StringBuffer(1024);
           
            NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
            String servnumber = customerSimp.getServNumber();
            
            TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
            
            /*
             * �굥��¼��Ϣ
             */
            Map<String, String> resultMap = null;
            
            Map<String, Map<String, String>> detailedRecords = (Map<String, Map<String, String>>) session.getAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber);
            if (null != detailedRecords)
            {
                resultMap = detailedRecords.get(cycle + "-" + cdrType);
            }
            else
            {
                detailedRecords = new HashMap<String, Map<String, String>>();
                
                session.setAttribute(Constants.LIST_DATA_SESSION_NAME + servnumber, detailedRecords);
            }
            
            // ��session��δȡ���굥��¼������ú�̨�ӿڻ�ȡ��ͬʱ��ŵ�session��
            if (null == resultMap || 0 == resultMap.size())
            {
            	// modify begin g00140516 2013/02/28 R003C13L02n01 BUG_NX_201302_123 �������ڲ�ѯ�굥ʱ����������Σ�ISCYCLE��BILLCYCLE
            	resultMap = detailedRecordsBean.queryDetailedRecordsNX2012(customerSimp, terminalInfo, cdrType, startDate, endDate, curMenuId, cycle);
            	// modify end g00140516 2013/02/28 R003C13L02n01 BUG_NX_201302_123 �������ڲ�ѯ�굥ʱ����������Σ�ISCYCLE��BILLCYCLE
            	
                detailedRecords.put(cycle + "-" + cdrType, resultMap);
            }            
            
            if (null != resultMap)
            {
                if (Constants.CDRTYPE_FIXFEE.equalsIgnoreCase(cdrType))
                {
                    // �ϼƷ���
                    String totalFee = resultMap.get("totalfee");
                    
                    // ������¼
                    String record = "";                    
                    
                    String allRecords = resultMap.get(cdrType);
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {
                        records.append("'�۷����� ʹ������ �ײͼ��̶������� ����(Ԫ)',");
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 1; i < recordsCount; i++)
                        {
                            record = recordsArray[i];
                            
                            // �ռ�¼
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }
                            
                            // �����@_@��β��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }
                            
                            records.append(formatFixfeeRecord(record));
                        }
                        
                        records.append("'�ϼƣ�").append(CommonUtil.fillLeftBlank(totalFee, 44)).append("'"); 
                    }
                }
                else if (Constants.CDRTYPE_GSM.equalsIgnoreCase(cdrType))
                {
                    // �ϼƷ���
                    String totalFee = resultMap.get("totalfee");
                    
                    // ����ͨ����
                    String localcall = resultMap.get("localcall");
                    
                    // ��;ͨ����
                    String longcall = resultMap.get("longcall");
                    
                    // ����ͨ����
                    String roamcall = resultMap.get("roamcall");
                    
                    // �л���
                    String citycall = resultMap.get("citycall");
                    
                    // ����ҵ���
                    String othercall = resultMap.get("othercall");
                    
                    // ���ڳ�;
                    String inlancall = resultMap.get("inlancall");
                    
                    // �۰�̨��;
                    String colonycall = resultMap.get("colonycall");
                    
                    // ���ʳ�;
                    String interncall = resultMap.get("interncall");
                    
                    // ��������
                    String inlanroam = resultMap.get("inlanroam");
                    
                    // �۰�̨����
                    String colonyroam = resultMap.get("colonyroam");
                    
                    // ��������
                    String internroam = resultMap.get("internroam");
                    
                    // ������¼
                    String record = "";                    

                    // ������¼�ĸ��ֶ�
                    String[] fields = null;
                    
                    // ������¼��ʱ��
                    String timeField = "";
                    
                    // ������¼��Ӧ������
                    String day = "";
                    
                    String lastDay = "";
                    
                    String allRecords = resultMap.get(cdrType);
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {
                        records.append("'����ͨ���ѣ�").append(CommonUtil.fillLeftBlank(localcall, 8)).append("',")
                                .append("'    �л��ѣ�").append(CommonUtil.fillLeftBlank(citycall, 8)).append("',")
                                .append("'����ҵ��ѣ�").append(CommonUtil.fillLeftBlank(othercall, 8)).append("',")
                                .append("'��;ͨ���ѣ�").append(CommonUtil.fillLeftBlank(longcall, 8)).append("',")
                                .append("'  ���ڳ�;��").append(CommonUtil.fillLeftBlank(inlancall, 8)).append("',")
                                .append("'�۰�̨��;��").append(CommonUtil.fillLeftBlank(colonycall, 8)).append("',")
                                .append("'  ���ʳ�;��").append(CommonUtil.fillLeftBlank(interncall, 8)).append("',")
                                .append("'����ͨ���ѣ�").append(CommonUtil.fillLeftBlank(roamcall, 8)).append("',")
                                .append("'  �������Σ�").append(CommonUtil.fillLeftBlank(inlanroam, 8)).append("',")
                                .append("'�۰�̨���Σ�").append(CommonUtil.fillLeftBlank(colonyroam, 8)).append("',")
                                .append("'  �������Σ�").append(CommonUtil.fillLeftBlank(internroam, 8)).append("',")
                                .append("'��ʼʱ�� ͨ�ŵص� ͨ�� �Է�����    ͨ��ʱ�� ͨ����',")
                                .append("'                  ��ʽ                        (Ԫ)',");
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 1; i < recordsCount; i++)
                        {
                            record = recordsArray[i];
                            
                            // �ռ�¼
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }
                            
                            // �����@_@��β��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }                            

                            // ��¼��������@_@@_@��˵���м�ĳ���ֶ�Ϊ�գ�ʹ�ÿո���棬��Ϊ@_@ @_@
                            record = record.replaceAll("@@", "@ @");
                            
                            fields = record.split("@_@");
                            
                            timeField = fields[0];
                            
                            // ÿ����¼�ĵ�һ���ֶζ���ʱ�䣬ʱ���ʽӦΪyyyy-mm-dd hh24:mi:ss������ȡ����yyyy-mm-dd
                            int index = timeField.indexOf(" ");
                            if (-1 != index)
                            {
                                day = timeField.substring(0, index);
                            }
                            else
                            {
                                day = timeField;
                            }
                            
                            // �����ǰ��¼��������֮ǰ�����ļ�¼�����ڲ�ͬ������vector���������ڣ�ͬʱ�ں���׷�ӱ�������¼���б�
                            // ��vector�б��������Ϊ��8��1�գ�8��1�յ�ȫ����¼��8��2�գ�8��2�յ�ȫ����¼
                            if (!lastDay.equals(day))
                            {
                                lastDay = day;
                                
                                records.append("'").append(day).append("',");
                            }                           
                            
                            records.append(formatGsmRecord(record));
                        }
                        
                        records.append("'�ϼƣ�").append(CommonUtil.fillLeftBlank(totalFee, 44)).append("'"); 
                    }                    
                }
                else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(cdrType))
                {
                    // �ϼƷ���
                    String totalFee = resultMap.get("totalfee");
                    String smsTotalNum = resultMap.get("smstotalnum");
                    String mmsTotalNum = resultMap.get("mmstotalnum");
                    
                    // ������¼
                    String record = "";                    

                    // ������¼�ĸ��ֶ�
                    String[] fields = null;
                    
                    // ������¼��ʱ��
                    String timeField = "";
                    
                    // ������¼��Ӧ������
                    String day = "";
                    
                    String lastDay = "";
                    
                    String allRecords = resultMap.get(cdrType);
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {                        
                        records.append("'������������").append(CommonUtil.fillLeftBlank(smsTotalNum, 7))
                                .append("            ������������").append(CommonUtil.fillLeftBlank(mmsTotalNum, 7)).append("',");
                        records.append("'��ʼʱ�� ͨ�ŵص� �Է�����     ��Ϣ���� ͨ�ŷ�(Ԫ)',");
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 1; i < recordsCount; i++)
                        {
                            record = recordsArray[i];
                            
                            // �ռ�¼
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }
                            
                            // �����@_@��β��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }                            

                            // ��¼��������@_@@_@��˵���м�ĳ���ֶ�Ϊ�գ�ʹ�ÿո���棬��Ϊ@_@ @_@
                            record = record.replaceAll("@@", "@ @");
                            
                            fields = record.split("@_@");
                            
                            timeField = fields[0];
                            
                            // ÿ����¼�ĵ�һ���ֶζ���ʱ�䣬ʱ���ʽӦΪyyyy-mm-dd hh24:mi:ss������ȡ����yyyy-mm-dd
                            int index = timeField.indexOf(" ");
                            if (-1 != index)
                            {
                                day = timeField.substring(0, index);
                            }
                            else
                            {
                                day = timeField;
                            }
                            
                            // �����ǰ��¼��������֮ǰ�����ļ�¼�����ڲ�ͬ������vector���������ڣ�ͬʱ�ں���׷�ӱ�������¼���б�
                            // ��vector�б��������Ϊ��8��1�գ�8��1�յ�ȫ����¼��8��2�գ�8��2�յ�ȫ����¼
                            if (!lastDay.equals(day))
                            {
                                lastDay = day;
                                
                                records.append("'").append(day).append("',");
                            } 
                            
                            records.append(formatSmsRecord(record));
                        }
                        
                        records.append("'�ϼƣ�").append(CommonUtil.fillLeftBlank(totalFee, 44)).append("'"); 
                    }                    
                }
                else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(cdrType))
                {
                    // GPRS��������
                    String gprssum = resultMap.get("gprssum");
                    
                    // CMWAP����
                    String cmwapsum = resultMap.get("cmwapsum");

                    // CMNET����
                    String cmnetsum = resultMap.get("cmnetsum");
                    
                    // �շ�����
                    String chargeflux = resultMap.get("chargeflux");
                    
                    // CMWAP�շ�����
                    String cmwapflux = resultMap.get("cmwapflux");
                    
                    // CMNET�շ�����
                    String cmnetflux = resultMap.get("cmnetflux");
                    
                    // �������
                    String freechargeflux = resultMap.get("freechargeflux");
                    
                    // CMWAP�������
                    String cmwapfreeflux = resultMap.get("cmwapfreeflux");
                    
                    // CMNET�������
                    String cmnetfreeflux = resultMap.get("cmnetfreeflux");                    
                    
                    // ������
                    String sumflux = resultMap.get("sumflux");
                    
                    // CMWAP������
                    String cmwapsumflux = resultMap.get("cmwapsumflux");
                    
                    // CMNET������
                    String cmnetsumflux = resultMap.get("cmnetsumflux");
                    
                    // WLAN��������
                    String wlansum = resultMap.get("wlansum");
                    
                    // ����WLAN����
                    String pubwlansum = resultMap.get("pubwlansum");

                    // У԰WLAN����
                    String campuswlansum = resultMap.get("campuswlansum");

                    // WLAN������
                    String wlansumflux = resultMap.get("wlansumflux");

                    // ����WLAN����
                    String pubwlanflux = resultMap.get("pubwlanflux");

                    // У԰WLAN����
                    String campuswlanflux = resultMap.get("campuswlanflux");

                    // WLAN��ʱ��
                    String wlansumtime = resultMap.get("wlansumtime");

                    // ����WLANʱ��
                    String pubwlantime = resultMap.get("pubwlantime");

                    // У԰WLANʱ��
                    String campuswlantime = resultMap.get("campuswlantime");

                    // WLAN�ܷ���
                    String wlansumfee = resultMap.get("wlansumfee");

                    // ����WLAN����
                    String pubwlanfee = resultMap.get("pubwlanfee");

                    // У԰WLAN����
                    String campuswlanfee = resultMap.get("campuswlanfee");                    
                    
                    // ������¼
                    String record = "";                    

                    // ������¼�ĸ��ֶ�
                    String[] fields = null;
                    
                    // ������¼��ʱ��
                    String timeField = "";
                    
                    // ������¼��Ӧ������
                    String day = "";
                    
                    String lastDay = "";
                    
                    String allRecords = resultMap.get(cdrType);
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {                        
                        records.append("'       GPRS��������:").append(CommonUtil.fillLeftBlank(gprssum, 19)).append("',")
                                .append("'    ���У�CMWAP����:").append(CommonUtil.fillLeftBlank(cmwapsum, 19)).append("',")
                                .append("'    ���У�CMNET����:").append(CommonUtil.fillLeftBlank(cmnetsum, 19)).append("',");
                        
                        records.append("'           �շ�����:").append(CommonUtil.fillLeftBlank(chargeflux, 19)).append("',")
                                .append("'���У�CMWAP�շ�����:").append(CommonUtil.fillLeftBlank(cmwapflux, 19)).append("',")
                                .append("'���У�CMNET�շ�����:").append(CommonUtil.fillLeftBlank(cmnetflux, 19)).append("',");
                        
                        records.append("'           �������:").append(CommonUtil.fillLeftBlank(freechargeflux, 19)).append("',")
                                .append("'���У�CMWAP�������:").append(CommonUtil.fillLeftBlank(cmwapfreeflux, 19)).append("',")
                                .append("'���У�CMNET�������:").append(CommonUtil.fillLeftBlank(cmnetfreeflux, 19)).append("',");
                
                        records.append("'             ������:").append(CommonUtil.fillLeftBlank(sumflux, 19)).append("',")
                                .append("'  ���У�CMWAP������:").append(CommonUtil.fillLeftBlank(cmwapsumflux, 19)).append("',")
                                .append("'  ���У�CMNET������:").append(CommonUtil.fillLeftBlank(cmnetsumflux, 19)).append("',");
                                                
                        records.append("'       WLAN��������:").append(CommonUtil.fillLeftBlank(wlansum, 19)).append("',")
                                .append("' ���У�����WLAN����:").append(CommonUtil.fillLeftBlank(pubwlansum, 19)).append("',")
                                .append("' ���У�У԰WLAN����:").append(CommonUtil.fillLeftBlank(campuswlansum, 19)).append("',");
                        
                        records.append("'         WLAN������:").append(CommonUtil.fillLeftBlank(wlansumflux, 19)).append("',")
                                .append("' ���У�����WLAN����:").append(CommonUtil.fillLeftBlank(pubwlanflux, 19)).append("',")
                                .append("' ���У�У԰WLAN����:").append(CommonUtil.fillLeftBlank(campuswlanflux, 19)).append("',");
                        
                        records.append("'         WLAN��ʱ��:").append(CommonUtil.fillLeftBlank(wlansumtime, 19)).append("',")
                                .append("' ���У�����WLANʱ��:").append(CommonUtil.fillLeftBlank(pubwlantime, 19)).append("',")
                                .append("' ���У�У԰WLANʱ��:").append(CommonUtil.fillLeftBlank(campuswlantime, 19)).append("',");
                                                
                        records.append("'         WLAN�ܷ���:").append(CommonUtil.fillLeftBlank(wlansumfee, 19)).append("',")
                                .append("' ���У�����WLAN����:").append(CommonUtil.fillLeftBlank(pubwlanfee, 19)).append("',")
                                .append("' ���У�У԰WLAN����:").append(CommonUtil.fillLeftBlank(campuswlanfee, 19)).append("',");
                        
                        //records.append("'��ʼʱ�� ͨ�ŵص� ������ʽ ʱ�� ���� 2G/3G��ʶ ͨ�ŷ�',");
                        String[] recordsArray = allRecords.split("\\|");
                        
                        // gWX294598 �޸�
//                        int recordsCount = recordsArray.length;
                        int recordsCount = recordsArray.length/2+1;
                        
                        for (int i = 0; i < recordsCount; i++)
                        {
//                            record = recordsArray[i];
                            
                            if(i==0) {
                        		record = recordsArray[i];
                        	}else {
                        		record = recordsArray[(i*2)-1]+recordsArray[i*2];
                        	}
                            
                            // �ռ�¼
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }                            

                            // �����@_@��β��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }                            

                            // ��¼��������@_@@_@��˵���м�ĳ���ֶ�Ϊ�գ�ʹ�ÿո���棬��Ϊ@_@ @_@
                            record = record.replaceAll("@@", "@ @");
                            
                            fields = record.split("@_@");
                            
                            timeField = fields[0];
                            
                            // ÿ����¼�ĵ�һ���ֶζ���ʱ�䣬ʱ���ʽӦΪyyyy-mm-dd hh24:mi:ss������ȡ����yyyy-mm-dd
                            int index = timeField.indexOf(" ");
                            if (-1 != index)
                            {
                                day = timeField.substring(0, index);
                            }
                            else
                            {
                                day = timeField;
                            }
                            
                            // �����ǰ��¼��������֮ǰ�����ļ�¼�����ڲ�ͬ������vector���������ڣ�ͬʱ�ں���׷�ӱ�������¼���б�
                            // ��vector�б��������Ϊ��8��1�գ�8��1�յ�ȫ����¼��8��2�գ�8��2�յ�ȫ����¼
                            if (!lastDay.equals(day))
                            {
                                lastDay = day;
                                
                                records.append("'").append(day).append("',");
                            } 
                            
                            records.append(formatGprsWlanRecord(record));
                        }
                    }                    
                }
                else if (Constants.CDRTYPE_ISMG.equalsIgnoreCase(cdrType))
                {
                    // �ϼƷ���
                    String totalFee = resultMap.get("totalfee");
                    
                    // ������¼
                    String record = "";                    

                    // ������¼�ĸ��ֶ�
                    String[] fields = null;
                    
                    // ������¼��ʱ��
                    String timeField = "";
                    
                    // ������¼��Ӧ������
                    String day = "";
                    
                    String lastDay = "";
                    
                    String allRecords = resultMap.get(cdrType);
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {
                        records.append("'ʱ��     ʹ�÷�ʽ   ҵ������       ҵ��˿�   ����',");

                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 1; i < recordsCount; i++)
                        {
                            record = recordsArray[i];
                            
                            // �ռ�¼
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }

                            // �����@_@��β��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }                            

                            // ��¼��������@_@@_@��˵���м�ĳ���ֶ�Ϊ�գ�ʹ�ÿո���棬��Ϊ@_@ @_@
                            record = record.replaceAll("@@", "@ @");
                            
                            fields = record.split("@_@");
                            
                            timeField = fields[0];
                            
                            // ÿ����¼�ĵ�һ���ֶζ���ʱ�䣬ʱ���ʽӦΪyyyy-mm-dd hh24:mi:ss������ȡ����yyyy-mm-dd
                            int index = timeField.indexOf(" ");
                            if (-1 != index)
                            {
                                day = timeField.substring(0, index);
                            }
                            else
                            {
                                day = timeField;
                            }
                            
                            // �����ǰ��¼��������֮ǰ�����ļ�¼�����ڲ�ͬ������vector���������ڣ�ͬʱ�ں���׷�ӱ�������¼���б�
                            // ��vector�б��������Ϊ��8��1�գ�8��1�յ�ȫ����¼��8��2�գ�8��2�յ�ȫ����¼
                            if (!lastDay.equals(day))
                            {
                                lastDay = day;
                                
                                records.append("'").append(day).append("',");
                            } 
                            
                            records.append(formatIsmgRecord(record));
                        }
                        
                        records.append("'�ϼƣ�").append(CommonUtil.fillLeftBlank(totalFee, 44)).append("'"); 
                    } 
                }
                else if (Constants.CDRTYPE_INFOFEE.equalsIgnoreCase(cdrType))
                {
                    // �ϼƷ���
                    String totalFee = resultMap.get("totalfee");
                    
                    // ������¼
                    String record = "";                    

                    // ������¼�ĸ��ֶ�
                    String[] fields = null;
                    
                    // ������¼��ʱ��
                    String timeField = "";
                    
                    // ������¼��Ӧ������
                    String day = "";
                    
                    String lastDay = "";
                    
                    String allRecords = resultMap.get(cdrType);
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {
                        records.append("'ʱ��     ҵ�� ҵ��˿� �����ṩ�� �������ͷ���(Ԫ)',");
                        records.append("'         ����',");
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 1; i < recordsCount; i++)
                        {
                            record = recordsArray[i];
                            
                            // �ռ�¼
                            if (null == record || "".equals(record.trim()))
                            {
                                continue;
                            }

                            // �����@_@��β��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
                            if (record.endsWith("@_@"))
                            {
                                record = record + " ";
                            }                            

                            // ��¼��������@_@@_@��˵���м�ĳ���ֶ�Ϊ�գ�ʹ�ÿո���棬��Ϊ@_@ @_@
                            record = record.replaceAll("@@", "@ @");
                            
                            fields = record.split("@_@");
                            
                            timeField = fields[0];
                            
                            // ÿ����¼�ĵ�һ���ֶζ���ʱ�䣬ʱ���ʽӦΪyyyy-mm-dd hh24:mi:ss������ȡ����yyyy-mm-dd
                            int index = timeField.indexOf(" ");
                            if (-1 != index)
                            {
                                day = timeField.substring(0, index);
                            }
                            else
                            {
                                day = timeField;
                            }
                            
                            // �����ǰ��¼��������֮ǰ�����ļ�¼�����ڲ�ͬ������vector���������ڣ�ͬʱ�ں���׷�ӱ�������¼���б�
                            // ��vector�б��������Ϊ��8��1�գ�8��1�յ�ȫ����¼��8��2�գ�8��2�յ�ȫ����¼
                            if (!lastDay.equals(day))
                            {
                                lastDay = day;
                                
                                records.append("'").append(day).append("',");
                            } 
                            
                            records.append(formatInfofeeRecord(record));
                        }
                        
                        records.append("'�ϼƣ�").append(CommonUtil.fillLeftBlank(totalFee, 44)).append("'"); 
                    } 
                }
            }
            
            String strRecords = "var printDataObject = {printTail:'YES'};var printRecords = [";
            if (!"".equals(records.toString().trim()))
            {
                strRecords += records;
            }
            strRecords += "]";
            
            System.out.println(strRecords);
            request.setAttribute("printData", strRecords);
            
            if (logger.isDebugEnabled())
            {
                logger.debug("getPrintDataNew End");
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
        // modify begin cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
        HttpSession session = this.getRequest().getSession();

        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ����굥��ӡ�Ƿ񳬳���������
        Map<String, String> resultMap = detailedRecordsBean.writePrintCdrLog(customerSimp,
                terminalInfo,
                cdrType,
                curMenuId);
        
        if (null != resultMap && null != resultMap.get("success"))
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "0", "���굥(" + cycle + "-" + cdrType + ")�����굥��ӡ�����ɹ�");

        }
        else if (null != resultMap && null != resultMap.get("errormsg"))
        {
            String msg = resultMap.get("errormsg");
            if ("".equals(msg))
            {
                msg = "����굥��ӡ�Ƿ񳬳���������ʧ��";
            }
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "���굥(" + cycle + "-" + cdrType + ")�����굥��ӡ����ʧ�ܣ�" + msg);

        }
        else
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "���굥(" + cycle + "-" + cdrType + ")�����굥��ӡ����ʧ�ܣ�");
        }
        // modify end cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
        
        if (logger.isDebugEnabled())
        {
            logger.debug("updatePrintTimes End");
        }
        return "success";
    }
    
    /**
     * ��ӡ�굥֮ǰ�����ж�֮ǰ�Ƿ��Ѿ���ӡ��������ǣ���ʾ�û������ظ���ӡ��
     * @throws Exception
     * @remark create g00140516 2013/02/01 R003C13L01n01 ÿ���12���£�ÿ���굥��ֻ�ܴ�ӡһ��
     */
    public void checkPrintTimes() throws Exception
    {
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        HttpServletResponse response = this.getResponse();
        
        response.setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        
        String msg = "";
        // modify begin cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
        try
        {
            NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            
            TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // ����굥��ӡ�Ƿ񳬳���������
            Map<String, String> resultMap = detailedRecordsBean.queryPrintCdr(customerSimp,
                    terminalInfo,
                    cdrType,
                    curMenuId);
            
            if (null != resultMap && null != resultMap.get("success"))
            {
                msg = "";
            }
            else if (null != resultMap && null != resultMap.get("errormsg"))
            {
                msg = resultMap.get("errormsg");
                if ("".equals(msg))
                {
                    msg = "����굥��ӡ�Ƿ񳬳���������ʧ��";
                }
            }
            else
            {
                msg = "����굥��ӡ�Ƿ񳬳���������ʧ��";
            }
            
        }
        catch (Exception e)
        {
            msg = e.getMessage();
            
            logger.error("����굥��ӡ�Ƿ񳬳���������ʱ�����쳣��", e);
        }
        finally
        {
            // �����client
            if (writer != null)
            {
                writer.print(msg);
                
                try
                {
                    writer.close();
                    writer = null;
                }
                catch (Exception e)
                {
                    logger.error("����굥��ӡ�Ƿ񳬳���������ʱ�����쳣��", e);
                    
                    throw new Exception("����굥��ӡ�Ƿ񳬳���������ʱ�����쳣");
                }
            }
        }
        // modify end cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
    }
    
    /**
     * ��ӡ�굥֮ǰ�����ж�֮ǰ�Ƿ��Ѿ���ӡ��������ǣ���ʾ�û������ظ���ӡ��
     * @throws Exception
     * @remark create g00140516 2013/02/01 R003C13L01n01 ÿ���12���£�ÿ���굥��ֻ�ܴ�ӡһ��
     */
    ////modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
    /*
    private String formatDate(String srcDate, String srcPattern, String destPattern)
    {
    	String destDate = srcDate;
    	
    	try
    	{
    		SimpleDateFormat srcFormat = new SimpleDateFormat(srcPattern);
        	SimpleDateFormat destFormat = new SimpleDateFormat(destPattern);
        	
        	destDate = destFormat.format(srcFormat.parse(srcDate));
    	}
    	catch (Exception e)
    	{
    		logger.error("��ʽ�������쳣��", e);
    	}
    	
    	return destDate;
    }
    */
    /**
     * ��ʽ�������ײͼ��̶����굥��¼
     * �۷�����ռ10���ֽڣ�����룻�ײͼ��̶�������ռ34���ֽڣ�����룻����(Ԫ)ռ6�ֽڣ��Ҷ��롣
     * 
     * @param record
     * @return
     * @see 
     */
    /*
    private String formatFixfeeRecordbak(String record)
    {
        StringBuffer buffer = new StringBuffer(256);
        
        // ������¼�ĸ��ֶ�
        String[] fields = record.split("@_@");
               
        String[] rows = null;
        int rowNum = 0;
        
        if (fields[2].getBytes().length <= 34)
        {
            rowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % 17)
            {
                rowNum = fields[2].length() / 17;
            }
            else
            {
                rowNum = fields[2].length() / 17 + 1;
            }                              
        }
        
        rows = new String[rowNum];
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[0], 10);    
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 10);
            }
            
            if (m == rowNum - 1)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 17), 34);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 17, (m + 1) * 17), 34);
            }                               
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[3], 6);    
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", 6);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    */
    //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
    /**
     * ��ʽ�������ײͼ��̶����굥��¼
     * �۷�����ռ10���ֽڣ�����룻�ײͼ��̶�������ռ34���ֽڣ�����룻����(Ԫ)ռ6�ֽڣ��Ҷ��롣
     * 
     * @param record
     * @return
     * @see 
     */
    private String formatFixfeeRecord(String record)
    {
        String[] fields = record.split("@_@");
        
        //��ʼ
        String str = "'";
        
        // ��¼
        str = str + 
        fields[0] + " " + // �۷�����
        fields[1] + " " + // ʹ������
        fields[2] + " " + // �ײͼ��̶�������
        fields[3]; // ����(Ԫ)
        
        // ����
        str = str + "',";
        
        logger.error("�ײͼ��̶����굥��¼:");
        logger.error(str);
        
        return str;
    }
    
    /**
     * ��ʽ������ͨ���굥��¼
     * ��ʼʱ�䣬ռ8���ֽڣ�����룻ͨ�ŵص�ռ5���ֽڣ�����룻ͨ�ŷ�ʽռ4���ֽڣ�����룻�Է�����ռ12�ֽڣ�����룻
     * ͨ��ʱ��ռ8���ֽڣ�����룻ʵ��ͨ����(Ԫ)ռ7���ֽڣ��Ҷ��룻ʵ�ճ�;��(Ԫ)ռ6���ֽڣ��Ҷ��롣
     * 
     * @param record
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private String formatGsmRecord(String record)
    {
        StringBuffer buffer = new StringBuffer(256);
        
        // ������¼�ĸ��ֶ�
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // ͨ�ŵص�        
        if (fields[1].getBytes().length <= 9)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % 4)
            {
                tempRowNum = fields[1].length() / 4;
            }
            else
            {
                tempRowNum = fields[1].length() / 4 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ͨ�ŷ�ʽ
        if (fields[2].getBytes().length <= 5)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % 2)
            {
                tempRowNum = fields[2].length() / 2;
            }
            else
            {
                tempRowNum = fields[2].length() / 2 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // �Է�����
        if (fields[3].getBytes().length <= 12)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % 12)
            {
                tempRowNum = fields[3].length() / 12;
            }
            else
            {
                tempRowNum = fields[3].length() / 12 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ͨ��ʱ��
        if (fields[4].getBytes().length <= 9)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % 4)
            {
                tempRowNum = fields[4].length() / 4;
            }
            else
            {
                tempRowNum = fields[4].length() / 4 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }        
        
        rows = new String[rowNum];
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), 9); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], 9);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 9);
            }
            
            // ͨ�ŵص�
            if (fields[1].getBytes().length <= 9)
            {
            	if (m == 0)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[1], 9);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 9);
            	}
            }
            else
            {
            	if (fields[1].length() > (m + 1) * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 4, (m + 1) * 4), 9);
                }
                else if (fields[1].length() > m * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 4), 9);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 9);
                }
            }
            
            // ͨ�ŷ�ʽ
            if (fields[2].getBytes().length <= 5)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[2], 5);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 5);
            	}
            }
            else
            {
            	if (fields[2].length() > (m + 1) * 2)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 2, (m + 1) * 2), 5);
                }
                else if (fields[2].length() > m * 2)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 2), 5);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 5);
                }
            }
            
            // �Է�����
            if (fields[3].length() > (m + 1) * 12)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 12, (m + 1) * 12), 12);
            }
            else if (fields[3].length() > m * 12)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 12), 12);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 12);
            }
            
            // ͨ��ʱ�� 
            if (fields[4].getBytes().length <= 9)
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4], 9);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 9);
                }
            }
            else
            {
                if (fields[4].length() > (m + 1) * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 4, (m + 1) * 4), 9);
                }
                else if (fields[4].length() > m * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 4), 9);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 9);
                }
            }
            
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[7], 6);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", 6);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * ��ʽ�������̲����굥��¼
     * ��ʼʱ�䣬ռ9���ֽڣ�����룻ͨ�ŵص�ռ9���ֽڣ�����룻�Է�����ռ13���ֽڣ�����룻��Ϣ����ռ9���ֽڣ�����룻
     * ͨ�ŷ�(Ԫ)ռ10���ֽڣ��Ҷ��롣
     * 
     * @param record
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private String formatSmsRecord(String record)
    {
        StringBuffer buffer = new StringBuffer(256);
        
        // ������¼�ĸ��ֶ�
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // ͨ�ŵص�        
        if (fields[1].getBytes().length <= 9)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % 4)
            {
                tempRowNum = fields[1].length() / 4;
            }
            else
            {
                tempRowNum = fields[1].length() / 4 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // �Է�����
        if (fields[2].getBytes().length <= 13)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % 13)
            {
                tempRowNum = fields[2].length() / 13;
            }
            else
            {
                tempRowNum = fields[2].length() / 13 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ��Ϣ����
        if (fields[4].getBytes().length <= 9)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % 4)
            {
                tempRowNum = fields[4].length() / 4;
            }
            else
            {
                tempRowNum = fields[4].length() / 4 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        rows = new String[rowNum];
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), 9); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], 9);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 9);
            }
            
            // ͨ�ŵص�
            if (fields[1].getBytes().length <= 9)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[1], 9);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 9);
            	}
            }
            else
            {
            	if (fields[1].length() > (m + 1) * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 4, (m + 1) * 4), 9);
                }
                else if (fields[1].length() > m * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 4), 9);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 9);
                }
            }
         
            // �Է�����
            if (fields[2].length() > (m + 1) * 13)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 13, (m + 1) * 13), 13);
            }
            else if (fields[2].length() > m * 13)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 13), 13);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 13);
            }
            
            // ��Ϣ����
            if (fields[4].getBytes().length <= 9)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[4], 9);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 9);
            	}
            }
            else
            {
            	if (fields[4].length() > (m + 1) * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 4, (m + 1) * 4), 9);
                }
                else if (fields[4].length() > m * 4)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 4), 9);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 9);
                }
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[7], 10);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", 10);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * ��ʽ�����������굥��¼
     * ��ʼʱ�䣬ռ8���ֽڣ�����룻ͨ�ŵص�5��������ʽ6��ʱ��11������7���ֽڣ�����룻2G/3G(3���ֽ�)ͨ�ŷ�(Ԫ)ռ5���ֽڣ��Ҷ��롣
     * @param record
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private String formatGprsWlanRecord(String record)
    {
        StringBuffer buffer = new StringBuffer(256);
        
        // ������¼�ĸ��ֶ�
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // ͨ�ŵص�        
        if (fields[1].getBytes().length <= 5)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % 2)
            {
                tempRowNum = fields[1].length() / 2;
            }
            else
            {
                tempRowNum = fields[1].length() / 2 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ��������ʽ�������⴦����Ӣ��ռ����        
        tempRowNum = 0;
        
        int offset = 0;
        int len = 6;
        while (offset < fields[2].length())
        {
            if (offset + len > fields[2].length())
            {
                len = fields[2].length() - offset;
            }
            
            while (fields[2].substring(offset, offset + len).getBytes().length > 6)
            {
                len -= 1;
            }
            
            offset += len;
            
            tempRowNum++;
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ʱ��
        if (fields[3].getBytes().length <= 12)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % 12)
            {
                tempRowNum = fields[3].length() / 12;
            }
            else
            {
                tempRowNum = fields[3].length() / 12 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ����
        if (fields[4].getBytes().length <= 7)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % 7)
            {
                tempRowNum = fields[4].length() / 7;
            }
            else
            {
                tempRowNum = fields[4].length() / 7 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        } 
        
        // 2G/3G
        String fileds6 = fields[6];
        if(fileds6.equals("2G/3G��ʶ"))
        {
            fileds6 = "2G/3G";
        }
        if (fileds6.getBytes().length <= 3)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fileds6.length() % 2)
            {
                tempRowNum = fileds6.length() / 2;
            }
            else
            {
                tempRowNum = fileds6.length() / 2 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        String txFee = fields[7];
        if(txFee.equals("ͨ�ŷ�"))
        {
            txFee = "ͨ�ŷ�(Ԫ)";
        }
        else
        {
            txFee = txFee.substring(0,txFee.length()-1);
        }
        // ͨѶ��
        if (txFee.getBytes().length <= 5)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == txFee.length() % 4)
            {
                tempRowNum = txFee.length() / 4;
            }
            else
            {
                tempRowNum = txFee.length() / 4 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
            
        rows = new String[rowNum];
        
        // ��������ʽ�������⴦����Ӣ��ռ����
        offset = 0;
        len = 6;
        
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), 8); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], 8);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 8);
            }
            
            // ͨ�ŵص�
            if (fields[1].getBytes().length <= 5)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[1], 5);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 5);
            	}
            }
            else
            {
            	if (fields[1].length() > (m + 1) * 2)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 2, (m + 1) * 2), 5);
                }
                else if (fields[1].length() > m * 2)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 2), 5);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 5);
                }
            }
            
            // ������ʽ
            if (offset < fields[2].length())
            {
                if (offset + len > fields[2].length())
                {
                    len = fields[2].length() - offset;
                }
                
                while (fields[2].substring(offset, offset + len).getBytes().length > 6)
                {
                    len -= 1;
                }
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(offset, offset + len), 6);
                offset += len; 
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 6);
            }
            
            // ʱ��
            if (fields[3].getBytes().length <= 12)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[3], 12);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 12);
            	}
            }
            else
            {
            	if (fields[3].length() > (m + 1) * 8)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 8, (m + 1) * 8), 12);
                }
                else if (fields[3].length() > m * 8)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 8), 12);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 12);
                }
            }
            
            // ����
            if (fields[4].length() > (m + 1) * 6)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 6, (m + 1) * 6), 7);
            }
            else if (fields[4].length() > m * 6)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 6), 7);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 7);
            }
            
            // 2G/3G
            if (fileds6.length() > (m + 1) * 2)
            {
                rows[m] += CommonUtil.fillRightBlank(fileds6.substring(m * 2, (m + 1) * 2), 3);
            }
            else if (fileds6.length() > m * 2)
            {
                rows[m] += CommonUtil.fillRightBlank(fileds6.substring(m * 2), 3);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 3);
            }
            
//            if (fields[6].getBytes().length <= 3)
//            {
//                if (0 == m)
//                {
//                    rows[m] += CommonUtil.fillRightBlank(fields[6], 3);
//                }
//                else
//                {
//                    rows[m] += CommonUtil.fillRightBlank("", 3);
//                }
//            }
//            else
//            {
//                if (fields[6].length() > (m + 1) * 2)
//                {
//                    rows[m] += CommonUtil.fillRightBlank(fields[6].substring(m * 2, (m + 1) * 2), 3);
//                }
//                else if (fields[6].length() > m * 2)
//                {
//                    rows[m] += CommonUtil.fillRightBlank(fields[6].substring(m * 2), 3);
//                }
//                else
//                {
//                    rows[m] += CommonUtil.fillRightBlank("", 3);
//                }
//            }
            
            // ͨѶ��
            if (txFee.getBytes().length <= 5)
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(txFee, 5);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 5);
                }
            }
            else
            {
                if (txFee.length() > (m + 1) * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(txFee.substring(m * 5, (m + 1) * 5), 5);
                }
                else if (txFee.length() > m * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(txFee.substring(m * 5), 5);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 5);
                }
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * ��ʽ�����������굥��¼
     * ��ʼʱ�䣬ռ8���ֽڣ�����룻ͨ�ŵص㡢������ʽ��ʱ����������ռ9���ֽڣ�����룻ͨ�ŷ�(Ԫ)ռ6���ֽڣ��Ҷ��롣
     * @param record
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
    /*
    private String formatGprsWlanRecordBak(String record)
    {
        String[] fields = record.split("@_@");
        
        //��ʼ
        String str = "'";
        
        // ��¼
        str = str + 
        fields[0].split(" ")[1] + " " + // ��ʼʱ��
        fields[1] + " " + // ͨ�ŵص�
        fields[2] + " " + // ������ʽ
        fields[3] + " " + // ʱ��
        fields[4] + " " + // ����
        fields[6] + " " + // 2/3G
        fields[7].replaceAll("Ԫ", "");// ͨ�ŷ�(Ԫ)
        
        // ����
        str = str + "',";
        
        logger.error("�����굥��¼:");
        logger.error(str);
        
        return str;
    }
    */
    //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
    /**
     * ��ʽ��������ֵҵ��۷Ѽ�¼
     * ʱ�䣬ռ9���ֽڣ�����룻ʹ�÷�ʽռ11���ֽڣ�����룻ҵ������ռ15���ֽڣ�����룻ҵ��˿�ռ9���ֽڣ�����룻����ռ6���ֽڣ��Ҷ��롣
     * 
     * @param record
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private String formatIsmgRecord(String record)
    {
        StringBuffer buffer = new StringBuffer(256);
        
        // ������¼�ĸ��ֶ�
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // ʹ�÷�ʽ       
        if (fields[1].getBytes().length <= 11)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % 5)
            {
                tempRowNum = fields[1].length() / 5;
            }
            else
            {
                tempRowNum = fields[1].length() / 5 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ҵ������
        if (fields[2].getBytes().length <= 15)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % 7)
            {
                tempRowNum = fields[2].length() / 7;
            }
            else
            {
                tempRowNum = fields[2].length() / 7 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ҵ��˿�
        if (fields[3].getBytes().length <= 9)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % 9)
            {
                tempRowNum = fields[3].length() / 9;
            }
            else
            {
                tempRowNum = fields[3].length() / 9 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        rows = new String[rowNum];
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), 9); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], 9);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 9);
            }
            
            // ʹ�÷�ʽ
            if (fields[1].getBytes().length <= 11)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[1], 11);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 11);
            	}
            }
            else
            {
            	if (fields[1].length() > (m + 1) * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 5, (m + 1) * 5), 11);
                }
                else if (fields[1].length() > m * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 5), 11);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 11);
                }
            }
            
            // ҵ������
            if (fields[2].getBytes().length <= 15)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[2], 15);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 15);
            	}
            }
            else
            {
            	if (fields[2].length() > (m + 1) * 7)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 7, (m + 1) * 7), 15);
                }
                else if (fields[2].length() > m * 7)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 7), 15);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 15);
                }
            }
            
            // �������
            if (fields[3].length() > (m + 1) * 9)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 9, (m + 1) * 9), 9);
            }
            else if (fields[3].length() > m * 9)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 9), 9);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 9);
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[4], 6);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", 6);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * ��ʽ���������շ�ҵ��۷Ѽ�¼
     * ʱ�䣬ռ9���ֽڣ�����룻ҵ������ռ5���ֽڣ�����룻ҵ��˿�ռ9���ֽڣ�����룻�����ṩ��11���ֽڣ�����룻��������ռ10���ֽڣ�����룻
     * ����ռ6���ֽڣ��Ҷ��롣
     * 
     * @param record
     * @return
     * @see 
     */
    private String formatInfofeeRecord(String record)
    {
        StringBuffer buffer = new StringBuffer(256);
        
        // ������¼�ĸ��ֶ�
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // ҵ������      
        if (fields[2].getBytes().length <= 5)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % 2)
            {
                tempRowNum = fields[2].length() / 2;
            }
            else
            {
                tempRowNum = fields[2].length() / 2 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ҵ��˿�
        if (fields[3].getBytes().length <= 9)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % 9)
            {
                tempRowNum = fields[3].length() / 9;
            }
            else
            {
                tempRowNum = fields[3].length() / 9 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // �����ṩ��
        if (fields[4].getBytes().length <= 11)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % 5)
            {
                tempRowNum = fields[4].length() / 5;
            }
            else
            {
                tempRowNum = fields[4].length() / 5 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ��������
        if (fields[6].getBytes().length <= 10)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[6].length() % 5)
            {
                tempRowNum = fields[6].length() / 5;
            }
            else
            {
                tempRowNum = fields[6].length() / 5 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }      
        
        rows = new String[rowNum];
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), 9); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], 9);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 9);
            }
            
            // ҵ������
            if (fields[2].getBytes().length <= 5)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[2], 5);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 5);
            	}
            }
            else
            {
            	if (fields[2].length() > (m + 1) * 2)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 2, (m + 1) * 2), 5);
                }
                else if (fields[2].length() > m * 2)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * 2), 5);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 5);
                }
            }
            
            // �������
            if (fields[3].length() > (m + 1) * 9)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 9, (m + 1) * 9), 9);
            }
            else if (fields[3].length() > m * 9)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 9), 9);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 9);
            }
            
            // �����ṩ��
            if (fields[4].getBytes().length <= 11)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[4], 11);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 11);
            	}
            }
            else
            {
            	if (fields[4].length() > (m + 1) * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 5, (m + 1) * 5), 11);
                }
                else if (fields[4].length() > m * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 5), 11);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 11);
                }
            }
            
            // ��������
            if (fields[6].getBytes().length <= 10)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[6], 10);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 10);
            	}
            }
            else
            {
            	if (fields[6].length() > (m + 1) * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[6].substring(m * 5, (m + 1) * 5), 10);
                }
                else if (fields[6].length() > m * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[6].substring(m * 5), 10);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 10);
                }
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[7], 6);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", 6);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    private void createRecord(StringBuffer sb, String text)
    {
        sb.append("'" + text + "',");
    }
    
    private void createLine(StringBuffer sb)
    {
        sb.append("' ---------------------------------------------',");
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

	public String[] getMonths()
    {
        return months;
    }

    public void setMonths(String[] months)
    {
        this.months = months;
    }

    public String getMonth()
    {
        return month;
    }

    public void setMonth(String month)
    {
        this.month = month;
    }

    public String getCdrType()
    {
        return cdrType;
    }

    public void setCdrType(String cdrType)
    {
        this.cdrType = cdrType;
    }

    public String getCdrTypeName()
    {
        return cdrTypeName;
    }

    public void setCdrTypeName(String cdrTypeName)
    {
        this.cdrTypeName = cdrTypeName;
    }

    public String getRecordFields()
    {
        return recordFields;
    }

    public void setRecordFields(String recordFields)
    {
        this.recordFields = recordFields;
    }

    public Vector getResultRecords()
    {
        return resultRecords;
    }

    public void setResultRecords(Vector resultRecords)
    {
        this.resultRecords = resultRecords;
    }

    public List<String> getFixfeeRecords()
    {
        return fixfeeRecords;
    }

    public void setFixfeeRecords(List<String> fixfeeRecords)
    {
        this.fixfeeRecords = fixfeeRecords;
    }

    public Map<String, String> getBillTotal()
    {
        return billTotal;
    }

    public void setBillTotal(Map<String, String> billTotal)
    {
        this.billTotal = billTotal;
    }
    
    
    public String getCycle()
    {
        return cycle;
    }

    public void setCycle(String cycle)
    {
        this.cycle = cycle;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getStartDate()
    {
        return startDate;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getEndDate()
    {
        return endDate;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getQueryDate()
    {
        return queryDate;
    }

    public void setQueryDate(String queryDate)
    {
        this.queryDate = queryDate;
    }

    public String getChQueryDate()
    {
        return chQueryDate;
    }

    public void setChQueryDate(String chQueryDate)
    {
        this.chQueryDate = chQueryDate;
    }

    public String getChStartDate()
    {
        return chStartDate;
    }

    public void setChStartDate(String chStartDate)
    {
        this.chStartDate = chStartDate;
    }

    public String getChEndDate()
    {
        return chEndDate;
    }

    public void setChEndDate(String chEndDate)
    {
        this.chEndDate = chEndDate;
    }

    public List<CycleInfoPO> getCycles()
    {
        return cycles;
    }

    public void setCycles(List<CycleInfoPO> cycles)
    {
        this.cycles = cycles;
    }

    public String getCustName()
    {
        return custName;
    }

    public void setCustName(String custName)
    {
        this.custName = custName;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
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
        return tableTitle;
    }

    public void setTableTitle(String[] tableTitle)
    {
        this.tableTitle = tableTitle;
    }

    public String[] getTableTail()
    {
        return tableTail;
    }

    public void setTableTail(String[] tableTail)
    {
        this.tableTail = tableTail;
    }

    public String[] getTableDescription()
    {
        return tableDescription;
    }

    public void setTableDescription(String[] tableDescription)
    {
        this.tableDescription = tableDescription;
    }

    public int getTitleCols()
    {
        return titleCols;
    }

    public void setTitleCols(int titleCols)
    {
        this.titleCols = titleCols;
    }

    public DetailedRecordsService getDetailedRecordsService()
    {
        return detailedRecordsService;
    }

    public void setDetailedRecordsService(DetailedRecordsService detailedRecordsService)
    {
        this.detailedRecordsService = detailedRecordsService;
    }

    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }
    
    
}