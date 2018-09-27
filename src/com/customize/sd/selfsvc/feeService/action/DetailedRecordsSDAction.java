/*
 * @filename: DetailedRecordsSDAction.java
 * @  All Right Reserved (C), 2012-2106, HUAWEI TECO CO.
 * @brif:  NG3.5���굥����֮�굥��ѯ
 * @author: ��Ⱥ/g00140516
 * @de:  2012/02/09 
 * @description: 
 * @remark: create g00140516 2012/02/09 R003C12L02n01 OR_huawei_201112_953
 */
package com.customize.sd.selfsvc.feeService.action;

import java.io.IOException;
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
import java.util.Timer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.DetailedRecordsBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.feeservice.model.CycleInfoPO;
import com.gmcc.boss.selfsvc.feeservice.model.DetailedRecordsTimesPO;
import com.gmcc.boss.selfsvc.feeservice.service.DetailedRecordsService;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.CurrencyUtil;
import com.gmcc.boss.selfsvc.util.SendMailUtil;

/**
 * NG3.5���굥����֮�굥��ѯ
 * 
 * @author g00140516
 * @version 1.0, 2012/02/09
 * @see
 * @since
 */
public class DetailedRecordsSDAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(DetailedRecordsSDAction.class);
    
    private transient DetailedRecordsBean detailedRecordsBean = null;
    
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
     * �ϼ���Ϣ
     */
    private Map<String, String> billTotal = null;
    
    /**
     * �ײ���̶����嵥��¼
     */
    private List<String> fixfeeRecords = null;
    
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
     * ��ʱ��β�ѯʱ��Ҳֻ�ܲ�ѯ6����֮�ڵ�
     */
    private String farthestDate = "";
    
    /**
     * �����б�
     */
    private List<CycleInfoPO> cycles = null;
    
    /**
     * �°�ͨ���굥���̲����굥�������굥
     */
    private Vector resultRecords = null;
    
    /**
     * �°���ֵҵ��������۷Ѽ�¼�����շ�ҵ��������۷Ѽ�¼
     */
    private Vector voiceRecords = null;
    
    /**
     * �°���ֵҵ���������۷Ѽ�¼�����շ�ҵ���������۷Ѽ�¼
     */
    private Vector nonVoiceRecords = null;
    
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
    
    private transient DetailedRecordsService detailedRecordsService = null;
    
    /**
     * �굥��ӡ��ʶ
     */
    private String printFlag = "0";
    
    /**
     * �汾��ʶ��0���ɰ棻1���°�
     */
    private String verFlag = "";
    
    /**
     * ��ѯ��ʶ��0����ʱ��Σ�1�������ڲ�
     */
    private String iscycle = "0";
    
    //add begin sWX219697 2014-6-3 15:02:07 OR_huawei_201405_877
    //�굥��������
    private String detailPwd;
    //add end sWX219697 2014-6-3 15:02:07 OR_huawei_201405_877
    
    /**
     * ͨ���굥չʾ��ʶ
     * add  lwx439898 2017-07-17 OR_SD_201707_264__2017�곤����һ�廯ר��--�����ն�ͨ���굥��ѯ����
     */
    private String isshow;
    
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
        
        // �ɲ�ѯ��ǰ�º�ǰ����µ��굥��Ϣ
        months = CommonUtil.getLastMonths(Constants.DATE_PATTERN_YYYYMM_1, 6);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectMonth End");
        }
        
        return forward;
    }
    
    /**
     * ��ʱ��β�ѯ
     * 
     * @return
     * @see
     */
    public String inputTime()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("inputTime Starting ...");
        }
        
        String forward = "failed";
        
        // ��ʱ��β�ѯ
        iscycle = "0";
        
        // ��ѯ��ʼ���������ڣ�Ĭ��Ϊ����
        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMMDD);
        
        queryDate = sdf1.format(new Date());
        
        startDate = queryDate.substring(0, 6) + "01";
        endDate = queryDate;        
        
        // ��ʱ��β�ѯҲֻ�ܲ�ѯ6����֮�ڵģ�������ѯ����������
        farthestDate = CommonUtil.getLastMonth(Constants.DATE_PATTERN_YYYYMM, 5) + "01";
        
        // ��ѯ�ͻ���Ϣ����ȡcustname
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();  
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // �ͻ���Ϣ��ѯ���ɹ�ʱӦ����һ��CTagSet��һ��CRSet
        Vector v = detailedRecordsBean.queryCustomerInfo(customerSimp, terminalInfo, 
                queryDate.substring(0, 6), "1", curMenuId);
        if (null == v || 2 > v.size())
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "�ͻ���Ϣ��ѯʧ��");
            
            logger.error("�ͻ�(" + customerSimp.getServNumber() + ")��Ϣ��ѯʧ��");
            
            request.setAttribute("errormessage", "�ͻ���Ϣ��ѯʧ�ܣ�");
            
            request.setAttribute("backStep", "-1");
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
            
            forward = "input";
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("inputTime End");
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
        
        // �����ڲ�ѯ
        iscycle = "1";
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        request.setAttribute("backStep", "-1");
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMM_1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMM);
        
        String strMonth = "";
        try 
        {
            strMonth = sdf2.format(sdf1.parse(month));
        } 
        catch (ParseException e) 
        {
            logger.error("�굥��ѯ����ʽ������ʱ������", e);
            
            strMonth = month;
        }
        
        // add begin lwx439898 2017-07-17 OR_SD_201707_264__2017�곤����һ�廯ר��--�����ն�ͨ���굥��ѯ����
        // ���ڲ���
        String thxdaccount = CommonUtil.getParamValue("SH_THXDACCOUNT_INFO");
        // �޶����Բ���λ������Ϊ6λ
        if (StringUtils.isBlank(thxdaccount) || thxdaccount.length() < 6)
        {
            logger.error("ϵͳ�������ò��Ϸ���");
            
            request.setAttribute("errormessage", "ϵͳ�������ò��Ϸ���");
            throw new ReceptionException("ϵͳ�������ò��Ϸ���");
        }
        else
        {
            // ��ȡǰ��λ����ȷ���·�
            thxdaccount = thxdaccount.substring(0, 6);
        }
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMM);
        try
        {
            Date accot = sdf.parse(thxdaccount);
            Date ntime = sdf.parse(strMonth);
            // �жϲ�����ѡ��չʾģʽ
            if (accot.getTime() >= ntime.getTime())
            {
                isshow = "false";
            }
            else
            {
                isshow = "true";
            }
        }
        catch (Exception e)
        {
            logger.error("���ڸ�ʽת������", e);
            throw new ReceptionException("���ڸ�ʽת������");
        }
        getRequest().getSession().setAttribute("show", isshow);
        // end begin lwx439898 2017-07-17 OR_SD_201707_264__2017�곤����һ�廯ר��--�����ն�ͨ���굥��ѯ����
        
        // �ͻ���Ϣ��ѯ���ɹ�ʱӦ����һ��CTagSet��һ��CRSet
        Vector v = detailedRecordsBean.queryCustomerInfo(customerSimp, terminalInfo, strMonth, "1", curMenuId);
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
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectType End");
        }
        
        return forward;
    }
    
    /**
     * ��ѯ�굥��¼�°�
     * 
     * @return
     * @see
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String qryDetailedRecords()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryDetailedRecords Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
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
            //modify begin lwx439898 2017-07-17 OR_SD_201707_264__2017�곤����һ�廯ר��--�����ն�ͨ���굥��ѯ����
            date = sdf1.parse(startDate.replace("-", ""));
            //modify end lwx439898 2017-07-17 OR_SD_201707_264__2017�곤����һ�廯ר��--�����ն�ͨ���굥��ѯ����
            chStartDate = sdf2.format(date);
        }
        catch (ParseException e)
        {
            logger.error("��ʽ�����ڿ�ʼʱ�����", e);
            
            chStartDate = startDate;
        }
        
        // add begin lwx439898 2017-07-17 OR_SD_201707_264__2017�곤����һ�廯ר��--�����ն�ͨ���굥��ѯ����
        // ʱ��β�ѯ
        if ("0".equals(iscycle))
        {
            String thxdaccount = CommonUtil.getParamValue("SH_THXDACCOUNT_INFO");
            // �޶����Բ���λ������Ϊ6λ
            if (StringUtils.isBlank(thxdaccount) || thxdaccount.length() < 6)
            {
                logger.error("ϵͳ�������ò��Ϸ���");
                
                request.setAttribute("errormessage", "ϵͳ�������ò��Ϸ���");
                throw new ReceptionException("ϵͳ�������ò��Ϸ���");
            }
            else
            {
                // ��ȡǰ��λ����ȷ���·�
                thxdaccount = thxdaccount.substring(0, 6);
            }           
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMM);
            try
            {
                Date accot = sdf.parse(thxdaccount);
                String stt = startDate.replace("-", "");
                Date sttdate = sdf.parse(stt.substring(0, 6));
                // �жϲ�����ѡ��չʾģʽ
                if (accot.getTime() >= sttdate.getTime())
                {
                    isshow = "false";
                }
                else
                {
                    isshow = "true";
                }
            }
            catch (Exception e)
            {
                logger.error("���ڸ�ʽת������", e);
                throw new ReceptionException("���ڸ�ʽת������");
            }
            getRequest().getSession().setAttribute("show", isshow);
        }
        // end begin lwx439898 2017-07-17 OR_SD_201707_264__2017�곤����һ�廯ר��--�����ն�ͨ���굥��ѯ���� 
 
        try
        {
            //modify begin lwx439898 2017-07-17 OR_SD_201707_264__2017�곤����һ�廯ר��--�����ն�ͨ���굥��ѯ����
            date = sdf1.parse(endDate.replace("-", ""));
            //modify end lwx439898 2017-07-17 OR_SD_201707_264__2017�곤����һ�廯ר��--�����ն�ͨ���굥��ѯ����
            chEndDate = sdf2.format(date);
        }
        catch (ParseException e)
        {
            logger.error("��ʽ�����ڿ�ʼʱ�����", e);
            
            chEndDate = endDate;
        }
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // add begin lwx439898 2017-06-26 OR_SD_201706_196_�����ն˲��ֲ˵����ͻ����ơ�ģ��������       
        request.setAttribute("custNames", CommonUtil.getUserLastName(customerSimp.getCustomerName()));       
        // add end lwx439898 2017-06-26 OR_SD_201706_196_�����ն˲��ֲ˵����ͻ����ơ�ģ��������
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        /*
         * �굥��¼��Ϣ������totalfee��smstotalnum��billinfo�ȡ� billinfoΪ08-03 21:56:04@_@����@_@����@_@66174709@_@19��@_@0.4|08-05
         * 21:56:04@_@����@_@����@_@66174709@_@19��@_@0.4
         */
        Map<String, String> resultMap = detailedRecordsBean.queryDetailedRecordsSD2012(customerSimp,
                terminalInfo,
                cdrType,
                startDate,
                endDate,
                curMenuId,
                iscycle,
                cycle);
        
        String description = "";
        
        // ��ʱ��β�ѯ
        if ("0".equals(iscycle))
        {
            description = "��ʱ���(" + startDate + "-" + endDate + ")��ѯ��";
        }
        // �����ڲ�ѯ
        else if ("1".equals(iscycle))
        {
            description = "������(" + cycle + ")��ѯ��";
        }
        
        //add begin lwx439898 ���ﻷ������ʹ�ã����Բ�������
        //Ϊtrueʱ��ʾ�������ݿ���
        if("true".equals(CommonUtil.getDictNameById("NGCC_XDCX", "NGCC_XDCX")))
        {
            resultMap = new HashMap<String,String>();
            resultMap.put("errorMessage", "");                      
        }
        //add end lwx439898 ���ﻷ������ʹ�ã����Բ�������
        
        if (null != resultMap && null != resultMap.get("errorMessage") && !"".equals(resultMap.get("errorMessage")))
        {         
            request.setAttribute("errormessage", resultMap.get("errorMessage"));
            
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", description + "�굥" + cdrType
                    + resultMap.get("errorMessage"));
            
            return "failed";
        }
        if (null != resultMap)
        {           
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "0", description + "�굥" + cdrType + "��ѯ�ɹ�");
            
            if (Constants.CDRTYPE_FIXFEE.equalsIgnoreCase(cdrType))
            {
                // �ײ���̶����嵥������ϼƷ���
                parseFixfeeRecords(resultMap);
            }
            else if (Constants.CDRTYPE_GSM.equalsIgnoreCase(cdrType))
            {
                // ͨ���굥�������ڷ���
                parseSingleTypeRecords(resultMap);
            }
            else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(cdrType))
            {
                // �̲����굥��������ȡ���š����ţ�Ȼ�����ڷ���
                parseSmsRecords(resultMap);
            }
            else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(cdrType))
            {
                // �����굥��������ȡGPRS��WLAN��Ȼ�����ڷ���
                parseGprsWlanRecords(resultMap);
            }
            else
            {
                // ��ֵҵ��۷Ѽ�¼�����շ�ҵ��۷Ѽ�¼�������������ͷ�����
                parseMultiTypesRecords(resultMap);
            }
            
            // �ж��û������Ƿ񻹿��Դ�ӡ�굥
            String termSpecial = terminalInfo.getTermspecial();
            String strMaxTimes = (String) PublicCache.getInstance().getCachedData(Constants.SH_CDR_PRTTIMES);
            
            if ('1' == termSpecial.charAt(0) && null != strMaxTimes && !"".equals(strMaxTimes.trim())
                    && !"0".equals(strMaxTimes.trim()))
            {
                SimpleDateFormat sdf3 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMM);
                String qryMonth = sdf3.format(new Date());
                
                DetailedRecordsTimesPO timesPO = detailedRecordsService.getPrintTimes(customerSimp.getServNumber(),
                        qryMonth,
                        cdrType);
                
                if (null == timesPO || timesPO.getTimes() < Integer.parseInt(strMaxTimes.trim()))
                {
                    printFlag = "1";
                }
            }
        }
        else
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", description + "�굥" + cdrType + "��ѯ������δ�ҵ����������ļ�¼");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryDetailedRecords End");
        }
        
        return cdrType;
    }
    
    /**
     * <�굥��������ҳ����ת>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-6-3 OR_huawei_201405_877 �굥��������
     */
    public String detailPrintPwd()
    {
    	return "detailPwd";
    }
    
    /**
     * <�첽��֤����Ĳ��������Ƿ���ȷ>
     * <������ϸ����>
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-6-3 OR_huawei_201405_877 �굥��������
     */
    public void authDetailPrintPwd() throws IOException
    {
    	PrintWriter out = this.getResponse().getWriter();
    	HttpSession session = this.getRequest().getSession();
    	
    	//�ն˻���Ϣ��
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        //��֤�������룬��������ȷ�����1
        if(detailedRecordsService.authDetailPrintPwd(terminalInfo.getTermid(), detailPwd))
        {
        	out.write("1");
        	out.flush();
        }
        else
        {
        	out.write("0");
        	out.flush();
        }
    }
    
    /**
     * ��ѯ�굥��¼
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String qryDetailedRecordsOld()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryDetailedRecordsOld Starting ...");
        }
        
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
        
		// add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
        /**
         * ƴװ���� type��0--GSMCDR header����ͨ���굥�� title����� �Է����� �������� ���������� ͨ����ʼʱ�� ͨ��ʱ��(��) ������(Ԫ) ��;��(Ԫ) ��Ѷ��Ϣ��(Ԫ) �ܻ���(Ԫ)
         * tail���굥������ ʱ���ϼƣ� ���úϼ�(Ԫ)��
         */
        pringLog();
		// add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        resultRecords = detailedRecordsBean.queryCDRListSDOld(customerSimp,
                terminalInfo,
                startDate,
                endDate,
                "",
                cdrType,
                curMenuId,
                tableTitle.length - 1, month, iscycle);
        
        String description = "";
        
        // ��ʱ��β�ѯ
        if ("0".equals(iscycle))
        {
            description = "��ʱ���(" + startDate + "-" + endDate + ")��ѯ��";
        }
        // �����ڲ�ѯ
        else if ("1".equals(iscycle))
        {
            description = "������(" + month + ")��ѯ��";
        }        
        
        // δ��ѯ���굥��¼
        if (resultRecords == null || resultRecords.size() == 0)
        {
            request.setAttribute("errormessage", description + "�굥(" + typeName + ")��ѯ������δ�ҵ����������ļ�¼");
            
            request.setAttribute("recordCount", "0");
            
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", description + "�굥(" + typeName + ")��ѯ������δ�ҵ����������ļ�¼");
            
            return "success";
        }
        
        Map<String, String> totalMap = calDynTotalSD(resultRecords);
        request.setAttribute("totalMap", totalMap);
        
        this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "0", description + "�굥(" + typeName + ")��ѯ�ɹ�");
        
        // �ж��û������Ƿ񻹿��Դ�ӡ�굥
        String termSpecial = terminalInfo.getTermspecial();
        String strMaxTimes = (String) PublicCache.getInstance().getCachedData(Constants.SH_CDR_PRTTIMES);
        
        if ('1' == termSpecial.charAt(0) && null != strMaxTimes && !"".equals(strMaxTimes.trim())
                && !"0".equals(strMaxTimes.trim()))
        {
            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMM);
            
            DetailedRecordsTimesPO timesPO = detailedRecordsService.getPrintTimes(customerSimp.getServNumber(),
                    sdf1.format(new Date()),
                    cdrType);
            
            if (null == timesPO || timesPO.getTimes() < Integer.parseInt(strMaxTimes.trim()))
            {
                printFlag = "1";
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryDetailedRecordsOld End");
        }
        
        return "success";
    }

    // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
    /**
     * ƴװ���� type��0--GSMCDR header����ͨ���굥�� title����� �Է����� �������� ���������� ͨ����ʼʱ�� ͨ��ʱ��(��) ������(Ԫ) ��;��(Ԫ) ��Ѷ��Ϣ��(Ԫ) �ܻ���(Ԫ)
     * tail���굥������ ʱ���ϼƣ� ���úϼ�(Ԫ)��
     */
    private void pringLog()
    {

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
    }
    // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն�     
    
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
            //typeServiceName = Constants.TYPE_SERVICE_NAME_ARRAY[1];
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
            //typeTableDetail = (Object[]) Constants.TYPE_TABLE_DETAIL[1];
            typeTableDetail = (Object[]) Constants.getTypeTableDetail()[1];
            // end zKF66389 2015-09-10 9�·�findbugs�޸�
            
        }
        else
        {
        	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
            //typeTableDetail = (Object[]) Constants.TYPE_TABLE_DETAIL[Integer.parseInt(type)];
            typeTableDetail = (Object[]) Constants.getTypeTableDetail()[Integer.parseInt(type)];
            // end zKF66389 2015-09-10 9�·�findbugs�޸�
        }
        
        return typeTableDetail;
    }
    
    /**
     * ɽ��ͳ��
     * 
     * @param v
     * @param typeServiceName
     * @return
     */
    private Map<String, String> calDynTotalSD(Vector v)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("calDynTotalSD Starting ...");
        }
        
        Map<String, String> totalM = new HashMap<String, String>();
        // �����嵥�ķ��ã������� �ϼ�
        String cstr = null;
        String[] sAry = null;
        if ("GSMCDR".equals(typeName))
        {
            totalM = totalByGsmcdr(v, totalM);            
        }
        else if ("SMSCDR".equals(typeName))
        {
            totalM = totalSmscdr(v, totalM);
        }
        else if ("IMSGCDR".equals(typeName))
        {
            totalM = totalImsgcdr(v, totalM);
        }
        
        else if ("WLANCDR".equals(typeName))
        {
            totalM = totalWlancdr(v, totalM);
        }
        else if ("GPRSCDR".equals(typeName))
        {
            totalM = totalGprscdr(v, totalM);
        }
        
        if (v != null)
        {
            totalM.put("recordcount", String.valueOf(v.size()));
        }
        else
        {
            totalM.put("recordcount", "0");
        }
        
        logger.debug("calDynTotalSD End");
        
        return totalM;
    }

    /**
     * GPRSCDR �ϼ�
     * <������ϸ����>
     * @param v
     * @param totalM
     * @see [�ࡢ��#��������#��Ա]
     */
    private Map<String, String> totalGprscdr(Vector v, Map<String, String> totalM)
    {
        String cstr;
        String[] sAry;
        String totalGprs1 = "0";
        String totalGprs2 = "0";
        if (v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String) v.get(i);
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
     * WLANCDR �ϼ�
     * <������ϸ����>
     * @param v
     * @param totalM
     * @see [�ࡢ��#��������#��Ա]
     */
    private Map<String, String> totalWlancdr(Vector v, Map<String, String> totalM)
    {
        String cstr;
        String[] sAry;
        String totalTimes = "0";
        String totalFee = "0";
        if (v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String) v.get(i);
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
     * IMSGCDR �ϼ�
     * <������ϸ����>
     * @param v
     * @param totalM
     * @see [�ࡢ��#��������#��Ա]
     */
    private Map<String, String> totalImsgcdr(Vector v, Map<String, String> totalM)
    {
        String cstr;
        String[] sAry;
        String totalFee1 = "0";
        if (v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String) v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalFee1 = CurrencyUtil.add(totalFee1, sAry[2]);
                totalFee1 = CurrencyUtil.add(totalFee1, sAry[3]);
            }
        }
        totalM.put("totalfee1", totalFee1);
        
        return totalM;
    }

    /**
     * SMSCDR �ϼ�
     * <������ϸ����>
     * @param v
     * @param totalM
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private Map<String, String> totalSmscdr(Vector v, Map<String, String> totalM)
    {
        String cstr;
        String[] sAry;
        String totalSmsFee = "0";
        if (v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String) v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalSmsFee = CurrencyUtil.add(totalSmsFee, (sAry.length > 6 ? sAry[6] : "0"));
            }
        }
        totalM.put("totalfee", totalSmsFee);
        
        return totalM;
    }

    /**
     * GSMCDR �ϼ�
     * <������ϸ����>
     * @param v
     * @param totalM
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private Map<String,String> totalByGsmcdr(Vector v, Map<String, String> totalM)
    {
        String cstr;
        String[] sAry;
        String totalTimes = "0";
        String totalGsmFee = "0";
        if (v != null)
        {
            for (int i = 0; i < v.size(); i++)
            {
                cstr = (String) v.get(i);
                sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                totalGsmFee = CurrencyUtil.add(totalGsmFee, sAry[9]);
                totalTimes = CurrencyUtil.add(totalTimes, sAry[5], 0);
            }
        }
        totalM.put("totalfee", totalGsmFee);
        totalM.put("totaltimes", CommonUtil.formatSecondsTime(totalTimes));
        
        return totalM;
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
        
        for (int i = 0; i < recordsCount; i++)
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
     * ͨ���굥
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
        String totalFee = resultMap.get("totalfee");
        billTotal.put("totalFee", totalFee);
        
        // GPRS/3G��������������
        String gprsTotalNum = resultMap.get("gprstotalnum");
        billTotal.put("gprsTotalNum", gprsTotalNum);
        
        // GPRS/3G�����ܷ���
        String gprsTotalFee = resultMap.get("gprstotalfee");
        billTotal.put("gprsTotalFee", gprsTotalFee);
        
        // WLAN����������
        String wlanTotalNum = resultMap.get("wlantotalnum");
        billTotal.put("wlanTotalNum", wlanTotalNum);
        
        // WLAN�����ܷ���
        String wlanTotalFee = resultMap.get("wlantotalfee");
        billTotal.put("wlanTotalFee", wlanTotalFee);
        
        // �굥�Ż������Ӹ��ֺϼ�  add by lKF60882 2012-07-05 begin
        
        // ͨ��ʱ��  (*Сʱ*��*�룩
        String txtotaltime = resultMap.get("txtotaltime");
     // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
//        if (null == txtotaltime || "".equals(txtotaltime))
//        {
//            txtotaltime = "0";
//        }
        txtotaltime = (null == txtotaltime || "".equals(txtotaltime)) ? "0" : txtotaltime;
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 

        billTotal.put("txtotaltime", txtotaltime);
        
        // ͨ���ѣ�Ԫ��
        String thtotalfee = resultMap.get("thtotalfee");
     // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
//        if (null == thtotalfee || "".equals(thtotalfee))
//        {
//            thtotalfee = "0";
//        }
        thtotalfee = (null == thtotalfee || "".equals(thtotalfee)) ? "0" : thtotalfee;
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
        billTotal.put("thtotalfee", thtotalfee);
        
        // ��;�ѣ�Ԫ��
        String cttotalfee = resultMap.get("cttotalfee");
     // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
//        if (null == cttotalfee || "".equals(cttotalfee))
//        {
//            cttotalfee = "0";
//        }
        cttotalfee = (null == cttotalfee || "".equals(cttotalfee)) ? "0" : cttotalfee;
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 Ȧ���Ӷ�_�����ն� 
        billTotal.put("cttotalfee", cttotalfee);
        
        // �굥�Ż������Ӹ��ֺϼ�  add by lKF60882 2012-07-05 end
        
        String[] recordsArray = allRecords.split("\\|");
        int recordsCount = recordsArray.length;
        
        for (int i = 0; i < recordsCount; i++)
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
     * �̲����굥
     * 
     * @param allRecords
     * @return
     * @see
     */
    private void parseSmsRecords(Map<String, String> resultMap)
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
        String totalFee = resultMap.get("totalfee");
        billTotal.put("totalFee", totalFee);
        
        // ����������
        String smsTotalNum = resultMap.get("smstotalnum");
        if (null == smsTotalNum || "".equals(smsTotalNum.trim()))
        {
            smsTotalNum = "0";
        }
        billTotal.put("smsTotalNum", smsTotalNum);
        
        // ����������
        String mmsTotalNum = resultMap.get("mmstotalnum");
        if (null == mmsTotalNum || "".equals(mmsTotalNum.trim()))
        {
            mmsTotalNum = "0";
        }
        billTotal.put("mmsTotalNum", mmsTotalNum);
        
        String[] recordsArray = allRecords.split("\\|");
        int recordsCount = recordsArray.length;
        
        for (int i = 0; i < recordsCount; i++)
        {
            // smsTotalNum�����Ŵ�������������lastDay��day�����½������ż�¼�����⽫���š����Ż���һ��
            if (i == Integer.parseInt(smsTotalNum))
            {
                day = "";
                lastDay = "";
            }
            
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
     * �ַ���Ϊ�շ��� 0
     * @param str
     * @return
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
     */
    private String chkEmptyGetZeroNoTrim(String str)
    {
    	if (null == str || "".equals(str))
        {
            str = "0";
        }
    	return str;
    }
    
    /**
     * �ַ���Ϊ�շ��� 0
     * @param str
     * @return
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
     */
    private String chkEmptyGetZero(String str)
    {
    	if (null == str || "".equals(str.trim()))
        {
            str = "0";
        }
    	return str;
    }
    
    /**
     * �����굥
     * 
     * @param allRecords
     * @return
     * @see
     * @remark modify by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
     */
    private void parseGprsWlanRecords(Map<String, String> resultMap)
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
        if (CommonUtil.isEmpty(allRecords))
        {
            return;
        }        
        
        // GPRS/3G��������������
        String gprsTotalNum = resultMap.get("gprstotalnum");
        gprsTotalNum = this.chkEmptyGetZero(gprsTotalNum);
        billTotal.put("gprsTotalNum", gprsTotalNum);
        
        // GPRS/3G�����ܷ���
        String gprsTotalFee = resultMap.get("gprstotalfee");
        billTotal.put("gprsTotalFee", gprsTotalFee);
        
        // WLAN����������
        String wlanTotalNum = resultMap.get("wlantotalnum");
        wlanTotalNum = this.chkEmptyGetZero(wlanTotalNum);
        billTotal.put("wlanTotalNum", wlanTotalNum);
        
        // WLAN�����ܷ���
        String wlantotalfee = resultMap.get("wlantotalfee");
        wlantotalfee = this.chkEmptyGetZeroNoTrim(wlantotalfee);
        billTotal.put("wlanTotalFee", wlantotalfee);
        
        // �굥�Ż������Ӹ��ֺϼ�  add by lKF60882 2012-07-05 begin
        
        // GPRS/3G������ʱ��
        String gprstotaltime = resultMap.get("gprstotaltime");
        gprstotaltime = this.chkEmptyGetZeroNoTrim(gprstotaltime);
        billTotal.put("gprstotaltime", gprstotaltime);
        
        // GPRS/3G����������
        String gprstotalflux = resultMap.get("gprstotalflux");
        gprstotalflux = this.chkEmptyGetZeroNoTrim(gprstotalflux);
        billTotal.put("gprstotalflux", gprstotalflux);
        
        // WLAN������ʱ��
        String wlantotaltime = resultMap.get("wlantotaltime");
        wlantotaltime = this.chkEmptyGetZeroNoTrim(wlantotaltime);
        billTotal.put("wlantotaltime", wlantotaltime);
        
        // WLAN����������
        String wlantotalflux = resultMap.get("wlantotalflux");
        wlantotalflux = this.chkEmptyGetZeroNoTrim(wlantotalflux);
        billTotal.put("wlantotalflux", wlantotalflux);
        
        // �굥�Ż������Ӹ��ֺϼ�  add by lKF60882 2012-07-05 end
        
        // add begin qWX279398 2015-8-3 OR_SD_201506_821_ɽ��_���ӿ����굥��ѯ����
        // ���߿�������������
        String wiredtotalnum = resultMap.get("wiredtotalnum");
        wiredtotalnum = this.chkEmptyGetZeroNoTrim(wiredtotalnum);
        billTotal.put("wiredtotalnum", wiredtotalnum);
        
        // ���߿��������ܷ���
        String wiredtotalfee = resultMap.get("wiredtotalfee");
        wiredtotalfee = this.chkEmptyGetZeroNoTrim(wiredtotalfee);
        billTotal.put("wiredtotalfee", wiredtotalfee);
        
        // ���߿���������ʱ��
        String wiredtotaltime = resultMap.get("wiredtotaltime");
        wiredtotaltime = this.chkEmptyGetZeroNoTrim(wiredtotaltime);
        billTotal.put("wiredtotaltime", wiredtotaltime);
        
        // ���߿�������������
        String wiredtotalflux = resultMap.get("wiredtotalflux");
        wiredtotalflux = this.chkEmptyGetZeroNoTrim(wiredtotalflux);
        billTotal.put("wiredtotalflux", wiredtotalflux);
        // add end qWX279398 2015-8-3 OR_SD_201506_821_ɽ��_���ӿ����굥��ѯ����
        
        String[] recordsArray = allRecords.split("\\|");
        int recordsCount = recordsArray.length;
        
        for (int i = 0; i < recordsCount; i++)
        {
            // GPRS/3G����������������������lastDay��day�����½���WLAN���������⽫���ֻ���һ��
            if (i == Integer.parseInt(gprsTotalNum))
            {
                day = "";
                lastDay = "";
            }
            
            record = recordsArray[i];
            
            // �ռ�¼
            if (CommonUtil.isEmpty(record))
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
     * ��ֵҵ��۷Ѽ�¼�����շ�ҵ��۷Ѽ�¼
     * 
     * @param resultMap
     * @return
     * @see
     */
    private void parseMultiTypesRecords(Map<String, String> resultMap)
    {
        // ��Ž�������굥��¼����ʽ��8��1�գ�8��1�յ�ȫ����¼��8��2�գ�8��2�յ�ȫ����¼
        voiceRecords = new Vector();
        nonVoiceRecords = new Vector();
        
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
        
        // ������۷Ѽ�¼
        String allRecords = resultMap.get(cdrType + "-voice");
        if (null != allRecords && 0 < allRecords.trim().length())
        {
            // �ϼƷ���
            String voiceTotalFee = resultMap.get("totalfee-voice");
            billTotal.put("voiceTotalFee", voiceTotalFee);
            
            // �굥�Ż������Ӹ��ֺϼ�  add by lKF60882 2012-07-05 begin
            
            // ������ҵ��۷Ѽ�¼��ʱ��
            String zzywtotaltime = resultMap.get("zzywtotaltime");
            
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
            zzywtotaltime = this.chkEmptyGetZeroNoTrim(zzywtotaltime);
            // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
            billTotal.put("zzywtotaltime", zzywtotaltime);
            
            // ���շ�ҵ��۷Ѽ�¼��ʱ��
            String dsftotaltime = resultMap.get("dsftotaltime");
            	
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
            dsftotaltime = this.chkEmptyGetZeroNoTrim(dsftotaltime);
            // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�

            billTotal.put("dsftotaltime", dsftotaltime);
            
            // �굥�Ż������Ӹ��ֺϼ�  add by lKF60882 2012-07-05 end
            
            String[] recordsArray = allRecords.split("\\|");
            int recordsCount = recordsArray.length;
            
            for (int i = 0; i < recordsCount; i++)
            {
                record = recordsArray[i];
                
                // �ռ�¼
                // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
                if (CommonUtil.isEmpty(record))
                {
                    continue;
                }
                // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
                
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
                
                // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
                day = (-1 != index) ? timeField.substring(0, index) : timeField;
                // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
                
                // �����ǰ��¼��������֮ǰ�����ļ�¼�����ڲ�ͬ������vector���������ڣ�ͬʱ�ں���׷�ӱ�������¼���б�
                // ��vector�б��������Ϊ��8��1�գ�8��1�յ�ȫ����¼��8��2�գ�8��2�յ�ȫ����¼
                if (!lastDay.equals(day))
                {
                    lastDay = day;
                    
                    dayRecords = new ArrayList<String>();
                    
                    voiceRecords.add(day);
                    voiceRecords.add(dayRecords);
                }
                
                dayRecords.add(record.substring(index + 1));
            }           
        }
        
        // ��������۷Ѽ�¼
        lastDay = "";
        allRecords = resultMap.get(cdrType + "-nonvoice");
        if (null != allRecords && 0 < allRecords.trim().length())
        {
            // �ϼƷ���
            String nonVoiceTotalFee = resultMap.get("totalfee-nonvoice");
            billTotal.put("nonVoiceTotalFee", nonVoiceTotalFee);
            
            String[] recordsArray = allRecords.split("\\|");
            int recordsCount = recordsArray.length;
            
            for (int i = 0; i < recordsCount; i++)
            {
                record = recordsArray[i];
                
                // �ռ�¼
                // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
                if (CommonUtil.isEmpty(record))
                {
                    continue;
                }
                // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
                
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
                
                // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
                day = (-1 != index) ? timeField.substring(0, index) : timeField;
                // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
                
                // �����ǰ��¼��������֮ǰ�����ļ�¼�����ڲ�ͬ������vector���������ڣ�ͬʱ�ں���׷�ӱ�������¼���б�
                // ��vector�б��������Ϊ��8��1�գ�8��1�յ�ȫ����¼��8��2�գ�8��2�յ�ȫ����¼
                if (!lastDay.equals(day))
                {
                    lastDay = day;
                    
                    dayRecords = new ArrayList<String>();
                    
                    nonVoiceRecords.add(day);
                    nonVoiceRecords.add(dayRecords);
                }
                
                dayRecords.add(record.substring(index + 1));
            }           
        }
    }
    
    /**
     * �ɰ��굥��ѯ��ȡ��ӡ����
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
            
            NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
            
            TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
            
            int iListType = Integer.parseInt(cdrType);
            
            String typeServiceName = getTypeServiceName(cdrType);
            typeName = typeServiceName;
            
            // ����嵥��ͷ��Ϣ [[��ͨ���굥��], [���, �Է�����, ��������, ����������, ͨ����ʼʱ��, ͨ��ʱ��(��), ������(Ԫ), ��;��(Ԫ), ��Ѷ��Ϣ��(Ԫ), �ܻ���(Ԫ)], [�굥������,
            // ʱ���ϼƣ�, ���úϼ�(Ԫ)��], [recordcount, totaltimes, totalfee]]
            Object[] tableDetail = getTableDetail(cdrType);
            tableTitle = (String[]) (tableDetail[1]);
            
            // ȡ���嵥����
            Vector dataV = detailedRecordsBean.queryCDRListSDOld(customerSimp,
                    terminalInfo,
                    startDate,
                    endDate,
                    "",
                    cdrType,
                    curMenuId,
                    tableTitle.length - 1, month, iscycle);
            Map<String, String> totalM = calDynTotalSD(dataV);
            
            // ��ӡ����
            //if (dataV != null && dataV.size() > 0)
            if (isNotEmpty(dataV))
            {
            	// begin zKF66389 2015-09-10 9�·�findbugs�޸�
                //createRecord(records, Constants.TYPE_TITLES[iListType - 1]);
            	createRecord(records, Constants.getTypeTitles()[iListType - 1]);
                // end zKF66389 2015-09-10 9�·�findbugs�޸�
                
                String cstr = "";
                String[] sAry = null;
                
                int maxSize = dataV.size();
                
                if ("GSMCDR".equals(typeServiceName))
                {
                    for (int i = 0; i < maxSize; i++)
                    {
                        cstr = (String) dataV.get(i);
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
                    for (int i = 0; i < maxSize; i++)
                    {
                        cstr = (String) dataV.get(i);
                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                        createRecord(records, "" + CommonUtil.fillRightBlank(sAry[2].trim(), 11) + " "
                                + CommonUtil.fillLeftBlank(sAry[3].trim(), 13) + " "
                                + CommonUtil.fillLeftBlank(sAry[4].trim(), 15) + " "
                                + CommonUtil.fillLeftBlank(sAry[6].trim(), 5));
                    }
                }
                else if ("GPRSCDR".equals(typeServiceName))
                {
                    for (int i = 0; i < maxSize; i++)
                    {
                        cstr = (String) dataV.get(i);
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
                    for (int i = 0; i < maxSize; i++)
                    {
                        cstr = (String) dataV.get(i);
                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                        createRecord(records, "" + CommonUtil.fillRightBlank(sAry[1], 18) + " "
                                + CommonUtil.fillLeftBlank(sAry[6], 14) + " "
                                + CommonUtil.fillLeftBlank(sAry[2].trim(), 6) + " "
                                + CommonUtil.fillLeftBlank(sAry[3].trim(), 7));
                    }
                }
                else if ("WLANCDR".equals(typeServiceName))
                {
                    for (int i = 0; i < maxSize; i++)
                    {
                        cstr = (String) dataV.get(i);
                        sAry = cstr.split(Constants.STR_SPLIT_TRANS);
                        createRecord(records, "" + CommonUtil.fillRightBlank(sAry[1], 6) + " "
                                + CommonUtil.fillLeftBlank(sAry[3], 14) + " " + CommonUtil.fillLeftBlank(sAry[4], 6)
                                + " " + CommonUtil.fillLeftBlank(CurrencyUtil.calByteToK(sAry[5]), 6) + " "
                                + CommonUtil.fillLeftBlank(CurrencyUtil.calByteToK(sAry[6]), 6) + " "
                                + CommonUtil.fillLeftBlank(sAry[7], 5));
                    }
                }
            }
            
            createLine(records);
            
            // begin zKF66389 2015-09-10 9�·�findbugs�޸�
            //String[] typeTotalColumn = (String[]) ((Object[]) Constants.TYPE_TABLE_DETAIL[iListType])[3];
            //String[] typeTotalColChnName = (String[]) ((Object[]) Constants.TYPE_TABLE_DETAIL[iListType])[2];
            String[] typeTotalColumn = (String[]) ((Object[]) Constants.getTypeTableDetail()[iListType])[3];
            String[] typeTotalColChnName = (String[]) ((Object[]) Constants.getTypeTableDetail()[iListType])[2];
            // end zKF66389 2015-09-10 9�·�findbugs�޸�
            String s = "";
            StringBuffer sbuf = new StringBuffer(s);
            for (int i = 0; i < typeTotalColumn.length; i++)
            {
                sbuf.append(typeTotalColChnName[i]).append(totalM.get(typeTotalColumn[i])).append(" ");
            }
            s= sbuf.toString();
            createRecord(records, s);
            createRecord(records, " ");
            
            scripts.append("printTail:'YES'");
            
            // begin zKF66389 2015-09-10 9�·�findbugs�޸�
            //scripts.append(",printTypeName:'" + Constants.TYPE_NAME_ARRAY[iListType] + "'");
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
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();           
        
        try
        {
            StringBuffer records = new StringBuffer(1024);
           
            NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
            
            String referenceServNumber = request.getParameter("referenceServNumber");
            if (customerSimp == null || customerSimp.getServNumber() == null || referenceServNumber == null 
                    || "".equals(referenceServNumber.trim()) 
                    || !referenceServNumber.trim().equals(customerSimp.getServNumber()))
            {
                throw new Exception("Ҫ��ӡ�嵥���ֻ�������Ự�б�����û����ֻ����벻һ��");
            }
            else if (logger.isWarnEnabled())
            {
                logger.warn("�û�" + referenceServNumber + "ѡ���ӡ�굥��" + cdrType);
            }
            
            TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
            
            /*
             * �굥��¼��Ϣ������totalfee��smstotalnum��billinfo�ȡ� billinfoΪ08-03 21:56:04@_@����@_@����@_@66174709@_@19��@_@0.4|08-05
             * 21:56:04@_@����@_@����@_@66174709@_@19��@_@0.4
             */
            Map<String, String> resultMap = detailedRecordsBean.queryDetailedRecordsSD2012(customerSimp,
                    terminalInfo,
                    cdrType,
                    startDate,
                    endDate,
                    curMenuId,
                    iscycle,
                    cycle);
            
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
                        records.append("'�۷�����  �ײͼ��̶�������            ����(Ԫ)',");
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
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
                        
                        records.append("'�ϼƣ�").append(CommonUtil.fillLeftBlank(totalFee, 40)).append("'"); 
                    }
                }
                else if (Constants.CDRTYPE_GSM.equalsIgnoreCase(cdrType))
                {
                    // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 begin
                    // �ϼƷ���
                    //String totalFee = resultMap.get("totalfee");
                    
                    // yKF28472
                    
                    // ͨ��ʱ��  (*Сʱ*��*�룩
                    String txtotaltime = resultMap.get("txtotaltime");
                    if (null == txtotaltime || "".equals(txtotaltime))
                    {
                        txtotaltime = "0";
                    }
                    
                    // ͨ���ѣ�Ԫ��
                    String thtotalfee = resultMap.get("thtotalfee");
                    if (null == thtotalfee || "".equals(thtotalfee))
                    {
                        thtotalfee = "0";
                    }
                    
                    // ��;�ѣ�Ԫ��
                    String cttotalfee = resultMap.get("cttotalfee");
                    if (null == cttotalfee || "".equals(cttotalfee))
                    {
                        cttotalfee = "0";
                    }
                    
                    // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 end
                    
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
                    String noShowToll = (String)getRequest().getSession().getAttribute("show");
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {
                        if("true".equals(noShowToll))
                        {
                            records.append("'��ʼʱ��ͨ�� ͨ�ŶԷ�����   ͨ�� ͨ����','        �ص� ��ʽ           ʱ��   (Ԫ)',");
                        }
                        else
                        {
                            records.append("'��ʼʱ��ͨ�� ͨ�ŶԷ�����   ͨ�� ͨ���� ��;��','        �ص� ��ʽ           ʱ��   (Ԫ)   (Ԫ)',");
                        }
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
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

                        // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 begin
                        //modify begin lwx439898 2017-09-05 OR_SD_201707_264__2017�곤����һ�廯ר��--�����ն�ͨ���굥��ѯ��������� 
                        String gsmHjRecord = "";
                        if("true".equals(noShowToll))
                        {
                            gsmHjRecord = "�ϼ�:@_@ @_@ @_@ @_@"+txtotaltime+"@_@ @_@ @_@"+thtotalfee;
                        }
                        else
                        {
                            gsmHjRecord = "�ϼ�:@_@ @_@ @_@ @_@"+txtotaltime+"@_@ @_@ @_@"+thtotalfee+"@_@"+cttotalfee;
                        }
                        //modify end lwx439898 2017-09-05 OR_SD_201707_264__2017�곤����һ�廯ר��--�����ն�ͨ���굥��ѯ��������� 
                        
                        records.append(formatGsmRecord(gsmHjRecord));
                        // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 begin
                    }                    
                }
                else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(cdrType))
                {
                    // �ϼƷ���
                    String totalFee = resultMap.get("totalfee");
                    String smsTotalNum = resultMap.get("smstotalnum");
                    if (null == smsTotalNum || "".equals(smsTotalNum.trim()))
                    {
                        smsTotalNum = "0";
                    }
                    
                    String mmsTotalNum = resultMap.get("mmstotalnum");
                    if (null == mmsTotalNum || "".equals(mmsTotalNum.trim()))
                    {
                        mmsTotalNum = "0";
                    }
                    
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
                        records.append("'������������").append(CommonUtil.fillLeftBlank(smsTotalNum, 5))
                                .append("��        ������������").append(CommonUtil.fillLeftBlank(mmsTotalNum, 5)).append("��',")
                                .append("'��ʼʱ�� ͨ�ŵص� �Է�����     ��Ϣ���� ͨ�ŷ�','                                          (Ԫ)',");
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
                        {
                            // smsTotalNum�����Ŵ�������������lastDay��day�����½������ż�¼�����⽫���š����Ż���һ��
                            if (i == Integer.parseInt(smsTotalNum))
                            {
                                day = "";
                                lastDay = "";
                            }
                            
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
                        
                        records.append("'�ϼƣ�").append(CommonUtil.fillLeftBlank(totalFee, 40)).append("'"); 
                    }                    
                }
                else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(cdrType))
                {
                    // GPRS/3G��������������
                    String gprsTotalNum = resultMap.get("gprstotalnum");
                    if (null == gprsTotalNum || "".equals(gprsTotalNum.trim()))
                    {
                        gprsTotalNum = "0";
                    }
                    
                    // GPRS/3G�����ܷ���
                    String gprsTotalFee = resultMap.get("gprstotalfee");
                    
                    // WLAN����������
                    String wlanTotalNum = resultMap.get("wlantotalnum");
                    if (null == wlanTotalNum || "".equals(wlanTotalNum.trim()))
                    {
                        wlanTotalNum = "0";
                    }
                    
                    // WLAN�����ܷ���
                    String wlanTotalFee = resultMap.get("wlantotalfee");
                    
                    // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-05 begin
                    
                    // modify begin zKF66389 2015-6-11 OR_SD_201412_479_SD_�����޸������굥��ѯ�Ļ�����Ϣ��ʱ���ֶ���ʾ������
                    // GPRS/3G������ʱ��
//                    String gprstotaltime = resultMap.get("gprstotaltime");
//                    if (null == gprstotaltime || "".equals(gprstotaltime))
//                    {
//                        gprstotaltime = "0";
//                    }
                    // modify end zKF66389 2015-6-11 OR_SD_201412_479_SD_�����޸������굥��ѯ�Ļ�����Ϣ��ʱ���ֶ���ʾ������
                    
                    // GPRS/3G����������
                    String gprstotalflux = resultMap.get("gprstotalflux");
                    if (null == gprstotalflux || "".equals(gprstotalflux))
                    {
                        gprstotalflux = "0";
                    }
                    
                    // WLAN������ʱ��
                    String wlantotaltime = resultMap.get("wlantotaltime");
                    if (null == wlantotaltime || "".equals(wlantotaltime))
                    {
                        wlantotaltime = "0";
                    }
                    
                    // WLAN����������
                    String wlantotalflux = resultMap.get("wlantotalflux");
                    if (null == wlantotalflux || "".equals(wlantotalflux))
                    {
                        wlantotalflux = "0";
                    }
                    
                    // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-05 end
                    
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
                        records.append("'�ƶ�����������������������:").append(CommonUtil.fillLeftBlank(gprsTotalNum, 6)).append("��',")
                               .append("'�ƶ��������������ܷ���:    ").append(CommonUtil.fillLeftBlank(gprsTotalFee, 6)).append("Ԫ',");
                        records.append("'WLAN����������:       ").append(CommonUtil.fillLeftBlank(wlanTotalNum, 6)).append("��',")
                               .append("'WLAN�����ܷ���:       ").append(CommonUtil.fillLeftBlank(wlanTotalFee, 6)).append("Ԫ',");
                        // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-05 start
                        // modify begin zKF66389 2015-6-13 OR_SD_201412_479_SD_�����޸������굥��ѯ�Ļ�����Ϣ��ʱ���ֶ���ʾ������
//                        records.append("'�ƶ���������������ʱ��:    ").append(CommonUtil.fillLeftBlank(gprstotaltime, 6)).append("',")
//                               .append("'�ƶ�������������������:    ").append(CommonUtil.fillLeftBlank(gprstotalflux, 6)).append("',");
//                        records.append("'WLAN������ʱ��:       ").append(CommonUtil.fillLeftBlank(wlantotaltime, 6)).append("',")
//                               .append("'WLAN����������:       ").append(CommonUtil.fillLeftBlank(wlantotalflux, 6)).append("',");
                        records.append("'�ƶ�������������������:    ").append(CommonUtil.fillLeftBlank(gprstotalflux, 6)).append("',")
                        	.append("'WLAN����������:    ").append(CommonUtil.fillLeftBlank(wlantotalflux, 6)).append("',");
                        records.append("'WLAN������ʱ��:       ").append(CommonUtil.fillLeftBlank(wlantotaltime, 6)).append("',");

                        // modify end zKF66389 2015-6-13 OR_SD_201412_479_SD_�����޸������굥��ѯ�Ļ�����Ϣ��ʱ���ֶ���ʾ������
                        // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-05 end
                        
                        // add begin zKF69263 2014-11-20 OR_SD_201311_1126 ���������굥��ѯչʾ���������Ż�ʱ�ε��޸��������
                        // GprsWlan��ʾ�������Ż�ʱ���������ز�����1����ʾ  0������ʾ��
                        String showGprsWlanFlux = CommonUtil.getParamValue(Constants.SH_GPRSWLAN_SHOWFLUX);
                        if ("1".equals(showGprsWlanFlux))
                        {
                            records.append("'��ʼʱ��ͨ�ŵص������� ʱ�� ����ʱ �Ż�ʱ ͨ�ŷ�','                ʽ          ������ ������  (Ԫ)',");
                        }
                        else
                        {
                            records.append("'��ʼʱ��ͨ�ŵص������� ʱ��    ����     ͨ�ŷ�','                ʽ                        (Ԫ)',");
                        }
                        // add end zKF69263 2014-11-20 OR_SD_201311_1126 ���������굥��ѯչʾ���������Ż�ʱ�ε��޸��������
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
                        {
                            // GPRS/3G����������������������lastDay��day�����½���WLAN���������⽫���ֻ���һ��
                            if (i == Integer.parseInt(gprsTotalNum))
                            {
                                day = "";
                                lastDay = "";
                            }
                            
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
                            
                            records.append(formatGprsWlanRecord(record));
                        }
                    }                    
                }
                // ��ֵҵ��۷Ѽ�¼
                else if (Constants.CDRTYPE_ISMG.equalsIgnoreCase(cdrType))
                {
                    // �ϼƷ���
                    String voiceTotalFee = resultMap.get("totalfee-voice");
                    String nonvoiceTotalFee = resultMap.get("totalfee-nonvoice");
                    
                    // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 begin
                    // ������ҵ��۷Ѽ�¼��ʱ��
                    String zzywtotaltime = resultMap.get("zzywtotaltime");
                    if (null == zzywtotaltime || "".equals(zzywtotaltime))
                    {
                        zzywtotaltime = "0";
                    }
                    // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 end
                    
                    // ������¼
                    String record = "";                    

                    // ������¼�ĸ��ֶ�
                    String[] fields = null;
                    
                    // ������¼��ʱ��
                    String timeField = "";
                    
                    // ������¼��Ӧ������
                    String day = "";
                    
                    String lastDay = "";
                    
                    String allRecords = resultMap.get(cdrType + "-voice");
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {                        
                        records.append("'������ҵ��۷Ѽ�¼',");
                        records.append("'ʱ��    ʹ�÷� ҵ�����Ʒ������ʱ��       ����','        ʽ                                (Ԫ)',");
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
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
                            
                            records.append(formatIsmgVoiceRecord(record));
                        }
                        // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 begin
                        
                        String hjRecord = "�ϼ�:@_@ @_@ @_@ @_@"+zzywtotaltime+"@_@"+voiceTotalFee;
                        records.append(formatIsmgVoiceRecord(hjRecord));
                        //records.append("'�ϼƣ�").append(CommonUtil.fillLeftBlank(voiceTotalFee, 44)).append("',");
                        
                        // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 end
                    } 
                    
                    allRecords = resultMap.get(cdrType + "-nonvoice");
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {                        
                        records.append("'',");
                        records.append("'��������ҵ��۷Ѽ�¼',");
                        records.append("'ʱ��    ʹ�÷�ʽ   ҵ������      ҵ���   ����','                                 ��',");

                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
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
                            
                            records.append(formatIsmgNonVoiceRecord(record));
                        }
                        
                        records.append("'�ϼƣ�").append(CommonUtil.fillLeftBlank(nonvoiceTotalFee, 40)).append("'"); 
                    } 
                }
                // ���շ�ҵ��۷Ѽ�¼
                else if (Constants.CDRTYPE_INFOFEE.equalsIgnoreCase(cdrType))
                {
                    // �ϼƷ���
                    String voiceTotalFee = resultMap.get("totalfee-voice");
                    String nonvoiceTotalFee = resultMap.get("totalfee-nonvoice");
                    
                    // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-05 begin
                    
                    String dsftotaltime = resultMap.get("dsftotaltime");
                    if (null == dsftotaltime || "".equals(dsftotaltime))
                    {
                        dsftotaltime = "0";
                    }
                    
                    // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-05 end
                    
                    // ������¼
                    String record = "";                    

                    // ������¼�ĸ��ֶ�
                    String[] fields = null;
                    
                    // ������¼��ʱ��
                    String timeField = "";
                    
                    // ������¼��Ӧ������
                    String day = "";
                    
                    String lastDay = "";
                    
                    String allRecords = resultMap.get(cdrType + "-voice");
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {
                        records.append("'������ҵ��۷Ѽ�¼',");
                        records.append("'ʱ��    ҵ�������� �����ṩ�� ʱ��      ����','        ����                              (Ԫ)',");
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
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
                            
                            records.append(formatInfofeeVoiceRecord(record));
                        }
                        
                        // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 begin
                        
                        String hjRecord = "�ϼ�:@_@ @_@ @_@ @_@ @_@"+dsftotaltime+"@_@"+voiceTotalFee;
                        records.append(formatInfofeeVoiceRecord(hjRecord));
                        //records.append("'�ϼƣ�").append(CommonUtil.fillLeftBlank(voiceTotalFee, 44)).append("',"); 
                        
                        // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 end
                    } 
                    
                    allRecords = resultMap.get(cdrType + "-nonvoice");
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {                        
                        records.append("'',");
                        records.append("'��������ҵ��۷Ѽ�¼',");
                        records.append("'ʱ��    ҵ�������� �����ṩ�� ��������  ����',");
                        records.append("'        ����                              (Ԫ)',");
                        
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        for (int i = 0; i < recordsCount; i++)
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
                            
                            records.append(formatInfofeeNonVoiceRecord(record));
                        }
                        
                        records.append("'�ϼƣ�").append(CommonUtil.fillLeftBlank(nonvoiceTotalFee, 40)).append("'"); 
                    } 
                }
            }
            
            String strRecords = "var printDataObject = {printTail:'YES'};var printRecords = [";
            if (!"".equals(records.toString().trim()))
            {
                strRecords += records;
            }
            strRecords += "]";
            
            if (logger.isInfoEnabled())
            {
                logger.info(strRecords);
            }
            else if (logger.isWarnEnabled())
            {
                logger.warn(strRecords.length() > 512 ? strRecords.substring(0, 512) : strRecords);
            }
            
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
            
            String strRecords = "var printDataObject = {printTail:'YES'};";
            
            request.setAttribute("printData", strRecords);
            
            if (logger.isDebugEnabled())
            {
                logger.debug("getPrintData End");
            }
            
            return "failed";
        }
    }
    
    /**
     * ��ʽ�������ײͼ��̶����굥��¼
     * �۷�����ռ10���ֽڣ�����룻�ײͼ��̶�������ռ28���ֽڣ�����룻����(Ԫ)ռ8�ֽڣ��Ҷ��롣
     * 
     * @param record
     * @return
     * @see 
     */
    private String formatFixfeeRecord(String record)
    {
        // ���ֶ���ռ�ֽ���
        int fieldLength[] = new int[]{10, 28, 8};
        
        // ���ֶ�ÿ�д�ӡ������һ������ռ�����ֽ�
        int fieldSize[] = new int[]{10, 14, 8};
        
        StringBuffer buffer = new StringBuffer(256);
        
        // ������¼�ĸ��ֶ�
        String[] fields = record.split("@_@");
               
        String[] rows = null;
        int rowNum = 0;
        
        // �ײͼ��̶�������
        if (fields[2].getBytes().length <= fieldLength[1])
        {
            rowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % fieldSize[1])
            {
                rowNum = fields[2].length() / fieldSize[1];
            }
            else
            {
                rowNum = fields[2].length() / fieldSize[1] + 1;
            }                              
        }
        
        rows = new String[rowNum];
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);    
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            if (m == rowNum - 1)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[1]), fieldLength[1]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
            }                               
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[3], fieldLength[2]);    
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", fieldLength[2]);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * ��ʽ������ͨ���굥��¼
     * ��ʼʱ�䣬ռ8���ֽڣ�����룻ͨ�ŵص�ռ5���ֽڣ�����룻ͨ�ŷ�ʽռ4���ֽڣ�����룻�Է�����ռ11�ֽڣ�����룻
     * ͨ��ʱ��ռ5���ֽڣ�����룻ͨ����(Ԫ)ռ6���ֽڣ��Ҷ��룻��;��(Ԫ)ռ7���ֽڣ��Ҷ��롣
     * 
     * @param record
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private String formatGsmRecord(String record)
    {
        // ���ֶ���ռ�ֽ���
        int fieldLength[]=null;
        // ���ֶ�ÿ�д�ӡ������ÿ������ռ�����ֽڡ�ͨ��ʱ�����⴦�������к���Ҳ�����֣�����ռ����
        int fieldSize[] =null;
        String noShowToll = (String)getRequest().getSession().getAttribute("show");
        if("true".equals(noShowToll))
        {
            fieldLength = new int[]{8, 5, 4, 11, 5, 6};
            fieldSize= new int[]{8, 2, 2, 11, 5, 6};
        }
        else
        {
            fieldLength = new int[]{8, 5, 4, 11, 5, 6, 7};
            fieldSize= new int[]{8, 2, 2, 11, 5, 6, 7};
        }

        StringBuffer buffer = new StringBuffer(256);
        
        // ������¼�ĸ��ֶ�
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // ͨ�ŵص�        
        if (fields[1].getBytes().length <= fieldLength[1])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % fieldSize[1])
            {
                tempRowNum = fields[1].length() / fieldSize[1];
            }
            else
            {
                tempRowNum = fields[1].length() / fieldSize[1] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ͨ�ŷ�ʽ
        if (fields[2].getBytes().length <= fieldLength[2])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % fieldSize[2])
            {
                tempRowNum = fields[2].length() / fieldSize[2];
            }
            else
            {
                tempRowNum = fields[2].length() / fieldSize[2] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // �Է�����
        if (fields[3].getBytes().length <= fieldLength[3])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % fieldSize[3])
            {
                tempRowNum = fields[3].length() / fieldSize[3];
            }
            else
            {
                tempRowNum = fields[3].length() / fieldSize[3] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ��ͨ��ʱ���������⴦��������ռ������tempRowNum֮ǰ�Ѹ�ֵ�����Դ˴�������Ϊ0
        int offset = 0;
        int len = 0;
        tempRowNum = 0;
        while (offset < fields[4].length())
        {
            len = fieldLength[4];
            
            if (offset + len > fields[4].length())
            {
                len = fields[4].length() - offset;
            }
            
            while (fields[4].substring(offset, offset + len).getBytes().length > fieldLength[4])
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
        
        rows = new String[rowNum];
        
        offset = 0;
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), fieldLength[0]); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            // ͨ�ŵص�
            if (fields[1].getBytes().length <= fieldLength[1])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1], fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            else
            {
                if (fields[1].length() > (m + 1) * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
                }
                else if (fields[1].length() > m * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1]), fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            
            // ͨ�ŷ�ʽ
            if (fields[2].getBytes().length <= fieldLength[2])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2], fieldLength[2]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
                }
            }
            else
            {
                if (fields[2].length() > (m + 1) * fieldSize[2])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2], (m + 1) * fieldSize[2]), fieldLength[2]);
                }
                else if (fields[2].length() > m * fieldSize[2])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2]), fieldLength[2]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
                }
            }
            
            // �Է�����
            if (fields[3].length() > (m + 1) * fieldSize[3])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[3], (m + 1) * fieldSize[3]), fieldLength[3]);
            }
            else if (fields[3].length() > m * fieldSize[3])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[3]), fieldLength[3]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
            }

            // ͨ��ʱ��
            len = fieldLength[4];
            if (offset < fields[4].length())
            {
                if (offset + len > fields[4].length())
                {
                    len = fields[4].length() - offset;
                }
                
                while (fields[4].substring(offset, offset + len).getBytes().length > fieldLength[4])
                {
                    len -= 1;
                }
                rows[m] += CommonUtil.fillRightBlank(fields[4].substring(offset, offset + len), fieldLength[4]);
                offset += len; 
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[4]);
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[7], fieldLength[5]);
                if(!"true".equals(noShowToll))
                {
                    rows[m] += CommonUtil.fillLeftBlank(fields[8], fieldLength[6]);
                }
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", fieldLength[5]);
                if(!"true".equals(noShowToll))
                {
                   rows[m] += CommonUtil.fillLeftBlank("", fieldLength[6]);
                }
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * ��ʽ�������̲����굥��¼
     * ��ʼʱ�䣬ռ9���ֽڣ�����룻ͨ�ŵص�ռ9���ֽڣ�����룻�Է�����ռ13���ֽڣ�����룻��Ϣ����ռ9���ֽڣ�����룻
     * ͨ�ŷ�(Ԫ)ռ6���ֽڣ��Ҷ��롣
     * 
     * @param record
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private String formatSmsRecord(String record)
    {
        // ���ֶ���ռ�ֽ���
        int fieldLength[] = new int[]{9, 9, 13, 9, 6};
        
        // ���ֶ�ÿ�д�ӡ������һ������ռ�����ֽ�
        int fieldSize[] = new int[]{9, 4, 13, 4, 6};
        
        StringBuffer buffer = new StringBuffer(256);
        
        // ������¼�ĸ��ֶ�
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // ͨ�ŵص�        
        if (fields[1].getBytes().length <= fieldLength[1])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % fieldSize[1])
            {
                tempRowNum = fields[1].length() / fieldSize[1];
            }
            else
            {
                tempRowNum = fields[1].length() / fieldSize[1] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // �Է�����
        if (fields[2].getBytes().length <= fieldLength[2])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % fieldSize[2])
            {
                tempRowNum = fields[2].length() / fieldSize[2];
            }
            else
            {
                tempRowNum = fields[2].length() / fieldSize[2] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ��Ϣ����
        if (fields[4].getBytes().length <= fieldLength[3])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % fieldSize[3])
            {
                tempRowNum = fields[4].length() / fieldSize[3];
            }
            else
            {
                tempRowNum = fields[4].length() / fieldSize[3] + 1;
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
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), fieldLength[0]); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            // ͨ�ŵص�
            if (fields[1].getBytes().length <= fieldLength[1])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1], fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            else
            {
                if (fields[1].length() > (m + 1) * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
                }
                else if (fields[1].length() > m * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1]), fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
         
            // �Է�����
            if (fields[2].length() > (m + 1) * fieldSize[2])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2], (m + 1) * fieldSize[2]), fieldLength[2]);
            }
            else if (fields[2].length() > m * fieldSize[2])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2]), fieldLength[2]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
            }
            
            // ��Ϣ����
            if (fields[4].getBytes().length <= fieldLength[3])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4], fieldLength[3]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
                }
            }
            else
            {
                if (fields[4].length() > (m + 1) * fieldSize[3])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[3], (m + 1) * fieldSize[3]), fieldLength[3]);
                }
                else if (fields[4].length() > m * fieldSize[3])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[3]), fieldLength[3]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
                }
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[7], fieldLength[4]);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", fieldLength[4]);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * ��ʽ�����������굥��¼
     * ��ʼʱ�䣬ռ8���ֽڣ�����룻ͨ�ŵص�ռ8���ֽڡ�������ʽռ7���ֽڡ�ʱ��ռ8���ֽڡ�����ռ9���ֽڣ�����룻ͨ�ŷ�(Ԫ)ռ6���ֽڣ��Ҷ��롣
     * @param record
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private String formatGprsWlanRecord(String record)
    {
        // ���ֶ���ռ�ֽ���
        int fieldLength[] = new int[]{8, 8, 7, 8, 9, 6};
        
        // ���ֶ�ÿ�д�ӡ������һ������ռ�����ֽ�
        int fieldSize[] = new int[]{8, 4, 7, 8, 9, 6};
        
        // add begin zKF69263 2014-11-20 OR_SD_201311_1126 ���������굥��ѯչʾ���������Ż�ʱ�ε��޸��������
        // GprsWlan��ʾ�������Ż�ʱ���������ز�����1����ʾ  0������ʾ��
        String showGprsWlanFlux = CommonUtil.getParamValue(Constants.SH_GPRSWLAN_SHOWFLUX);
        if ("1".equals(showGprsWlanFlux))
        {
            fieldLength = new int[]{8, 8, 7, 5, 7, 7, 6};
            fieldSize = new int[]{8, 4, 7, 5, 6, 6, 6};
        }
        // add end zKF69263 2014-11-20 OR_SD_201311_1126 ���������굥��ѯչʾ���������Ż�ʱ�ε��޸��������
        
        StringBuffer buffer = new StringBuffer(256);
        
        // ������¼�ĸ��ֶ�
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // ͨ�ŵص�        
        if (fields[1].getBytes().length <= fieldLength[1])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % fieldSize[1])
            {
                tempRowNum = fields[1].length() / fieldSize[1];
            }
            else
            {
                tempRowNum = fields[1].length() / fieldSize[1] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ��������ʽ�������⴦����Ӣ��ռ����        
        tempRowNum = 0;
        
        int offset1 = 0;
        int len = 0;
        while (offset1 < fields[2].length())
        {
            len = fieldLength[2];
            
            if (offset1 + len > fields[2].length())
            {
                len = fields[2].length() - offset1;
            }
            
            while (fields[2].substring(offset1, offset1 + len).getBytes().length > fieldLength[2])
            {
                len -= 1;
            }
            
            offset1 += len;
            
            tempRowNum++;
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ��ʱ���������⴦��������ռ����
        tempRowNum = 0;
        
        int offset2 = 0;
        
        while (offset2 < fields[3].length())
        {
            len = fieldLength[3];
            
            if (offset2 + len > fields[3].length())
            {
                len = fields[3].length() - offset2;
            }
            
            while (fields[3].substring(offset2, offset2 + len).getBytes().length > fieldLength[3])
            {
                len -= 1;
            }
            
            offset2 += len;
            
            tempRowNum++;
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ����������ʱ������
        if (fields[4].getBytes().length <= fieldLength[4])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % fieldSize[4])
            {
                tempRowNum = fields[4].length() / fieldSize[4];
            }
            else
            {
                tempRowNum = fields[4].length() / fieldSize[4] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }      
        
        // add begin zKF69263 2014-11-20 OR_SD_201311_1126 ���������굥��ѯչʾ���������Ż�ʱ�ε��޸��������
        // GprsWlan��ʾ�������Ż�ʱ���������ز�����1����ʾ  0������ʾ��
        if ("1".equals(showGprsWlanFlux))
        {
        	// �Ż�ʱ������
            if (fields[5].getBytes().length <= fieldLength[5])
            {
                tempRowNum = 1;
            }
            else
            {
                if (0 == fields[5].length() % fieldSize[5])
                {
                    tempRowNum = fields[5].length() / fieldSize[5];
                }
                else
                {
                    tempRowNum = fields[5].length() / fieldSize[5] + 1;
                }                              
            }
            
            if (rowNum < tempRowNum)
            {
                rowNum = tempRowNum;
            }
        }   
        // add end zKF69263 2014-11-20 OR_SD_201311_1126 ���������굥��ѯչʾ���������Ż�ʱ�ε��޸�������� 
            
        rows = new String[rowNum];
        
        // ��������ʽ��ʱ���������⴦����Ӣ�ġ�����ռ����
        offset1 = 0;
        offset2 = 0;
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), fieldLength[0]); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            // ͨ�ŵص�
            if (fields[1].getBytes().length <= fieldLength[1])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1], fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            else
            {
                if (fields[1].length() > (m + 1) * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
                }
                else if (fields[1].length() > m * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1]), fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            
            // ������ʽ
            len = fieldLength[2];
            if (offset1 < fields[2].length())
            {
                if (offset1 + len > fields[2].length())
                {
                    len = fields[2].length() - offset1;
                }
                
                while (fields[2].substring(offset1, offset1 + len).getBytes().length > fieldLength[2])
                {
                    len -= 1;
                }
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(offset1, offset1 + len), fieldLength[2]);
                offset1 += len; 
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
            }
            
            // ʱ��
            len = fieldLength[3];
            if (offset2 < fields[3].length())
            {
                if (offset2 + len > fields[3].length())
                {
                    len = fields[3].length() - offset2;
                }
                
                while (fields[3].substring(offset2, offset2 + len).getBytes().length > fieldLength[3])
                {
                    len -= 1;
                }
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(offset2, offset2 + len), fieldLength[3]);
                offset2 += len; 
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
            }
            
            // ����������ʱ������
            if (fields[4].length() > (m + 1) * fieldSize[4])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[4], (m + 1) * fieldSize[4]), fieldLength[4]);
            }
            else if (fields[4].length() > m * fieldSize[4])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[4]), fieldLength[4]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[4]);
            }
            
            // add begin zKF69263 2014-11-20 OR_SD_201311_1126 ���������굥��ѯչʾ���������Ż�ʱ�ε��޸��������
            // GprsWlan��ʾ�������Ż�ʱ���������ز�����1����ʾ  0������ʾ��
            if ("1".equals(showGprsWlanFlux))
            {
                // �Ż�ʱ������
                if (fields[5].length() > (m + 1) * fieldSize[5])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[5].substring(m * fieldSize[5], (m + 1) * fieldSize[5]), fieldLength[5]);
                }
                else if (fields[5].length() > m * fieldSize[5])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[5].substring(m * fieldSize[5]), fieldLength[5]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[5]);
                }
                
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillLeftBlank(fields[8], fieldLength[6]);
                }
                else
                {
                    rows[m] += CommonUtil.fillLeftBlank("", fieldLength[6]);
                }
            }
            else
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillLeftBlank(" "+fields[6], fieldLength[5]);
                }
                else
                {
                    rows[m] += CommonUtil.fillLeftBlank("", fieldLength[5]);
                }
            }
            // add end zKF69263 2014-11-20 OR_SD_201311_1126 ���������굥��ѯչʾ���������Ż�ʱ�ε��޸��������
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }    
    
    /**
     * ��ʽ��������ֵҵ��������۷Ѽ�¼
     * ʱ�䲻��ӡ��ռ8���ֽڣ�����룻ʹ�÷�ʽռ7���ֽڡ�ҵ������ռ8���ֽڡ��������ռ8���ֽڡ�ʱ��ռ9���ֽڣ�����룻����(Ԫ)ռ6���ֽڣ��Ҷ��롣
     * 
     * @param record
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private String formatIsmgVoiceRecord(String record)
    {
        // ���ֶ���ռ�ֽ���
        int fieldLength[] = new int[]{8, 7, 8, 8, 9, 6};
        
        // ���ֶ�ÿ�д�ӡ������һ������ռ�����ֽ�
        int fieldSize[] = new int[]{8, 3, 4, 8, 9, 6};
        
        StringBuffer buffer = new StringBuffer(256);
        
        // ������¼�ĸ��ֶ�
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;

        // ʹ�÷�ʽ       
        if (fields[1].getBytes().length <= fieldLength[1])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % fieldSize[1])
            {
                tempRowNum = fields[1].length() / fieldSize[1];
            }
            else
            {
                tempRowNum = fields[1].length() / fieldSize[1] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ҵ������
        if (fields[2].getBytes().length <= fieldLength[2])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % fieldSize[2])
            {
                tempRowNum = fields[2].length() / fieldSize[2];
            }
            else
            {
                tempRowNum = fields[2].length() / fieldSize[2] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // �������
        if (fields[3].getBytes().length <= fieldLength[3])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % fieldSize[3])
            {
                tempRowNum = fields[3].length() / fieldSize[3];
            }
            else
            {
                tempRowNum = fields[3].length() / fieldSize[3] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ʱ��
        tempRowNum = 0;
        
        int offset = 0;
        int len = 0;
        while (offset < fields[4].length())
        {
            len = fieldLength[4];
            
            if (offset + len > fields[4].length())
            {
                len = fields[4].length() - offset;
            }
            
            while (fields[4].substring(offset, offset + len).getBytes().length > fieldLength[4])
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
        
        rows = new String[rowNum];
        
        offset = 0;
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), fieldLength[0]); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            // ʹ�÷�ʽ
            if (fields[1].getBytes().length <= fieldLength[1])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1], fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            else
            {
                if (fields[1].length() > (m + 1) * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
                }
                else if (fields[1].length() > m * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1]), fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            
            // ҵ������
            if (fields[2].getBytes().length <= fieldLength[2])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2], fieldLength[2]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
                }
            }
            else
            {
                if (fields[2].length() > (m + 1) * fieldSize[2])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2], (m + 1) * fieldSize[2]), fieldLength[2]);
                }
                else if (fields[2].length() > m * fieldSize[2])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2]), fieldLength[2]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
                }
            }
            
            // �������
            if (fields[3].length() > (m + 1) * fieldSize[3])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[3], (m + 1) * fieldSize[3]), fieldLength[3]);
            }
            else if (fields[3].length() > m * fieldSize[3])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[3]), fieldLength[3]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
            }
            
            // ʱ��
            len = fieldLength[4];
            if (offset < fields[4].length())
            {
                if (offset + len > fields[4].length())
                {
                    len = fields[4].length() - offset;
                }
                
                while (fields[4].substring(offset, offset + len).getBytes().length > fieldLength[4])
                {
                    len -= 1;
                }
                rows[m] += CommonUtil.fillRightBlank(fields[4].substring(offset, offset + len), fieldLength[4]);
                offset += len; 
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[4]);
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[5], fieldLength[5]);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", fieldLength[5]);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * ��ʽ��������ֵҵ���������۷Ѽ�¼
     * ʱ�䣬ռ8���ֽڣ�����룻ʹ�÷�ʽռ10���ֽڣ�����룻ҵ������ռ14���ֽڣ�����룻ҵ��˿�ռ8���ֽڣ�����룻����ռ6���ֽڣ��Ҷ��롣
     * 
     * @param record
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private String formatIsmgNonVoiceRecord(String record)
    {
        // ���ֶ���ռ�ֽ���
        int fieldLength[] = new int[]{8, 10, 14, 8, 6};
        
        // ���ֶ�ÿ�д�ӡ������һ������ռ�����ֽ�
        int fieldSize[] = new int[]{8, 5, 7, 8, 6};
        
        StringBuffer buffer = new StringBuffer(256);
        
        // ������¼�ĸ��ֶ�
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // ʹ�÷�ʽ       
        if (fields[1].getBytes().length <= fieldLength[1])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % fieldSize[1])
            {
                tempRowNum = fields[1].length() / fieldSize[1];
            }
            else
            {
                tempRowNum = fields[1].length() / fieldSize[1] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ҵ������
        if (fields[2].getBytes().length <= fieldLength[2])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % fieldSize[2])
            {
                tempRowNum = fields[2].length() / fieldSize[2];
            }
            else
            {
                tempRowNum = fields[2].length() / fieldSize[2] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ҵ��˿�
        if (fields[3].getBytes().length <= fieldLength[3])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % fieldSize[3])
            {
                tempRowNum = fields[3].length() / fieldSize[3];
            }
            else
            {
                tempRowNum = fields[3].length() / fieldSize[3] + 1;
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
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), fieldLength[0]); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            // ʹ�÷�ʽ
            if (fields[1].getBytes().length <= fieldLength[1])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1], fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            else
            {
                if (fields[1].length() > (m + 1) * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
                }
                else if (fields[1].length() > m * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * fieldSize[1]), fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            
            // ҵ������
            if (fields[2].getBytes().length <= fieldLength[2])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2], fieldLength[2]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
                }
            }
            else
            {
                if (fields[2].length() > (m + 1) * fieldSize[2])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2], (m + 1) * fieldSize[2]), fieldLength[2]);
                }
                else if (fields[2].length() > m * fieldSize[2])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[2]), fieldLength[2]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
                }
            }
            
            // �������
            if (fields[3].length() > (m + 1) * fieldSize[3])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[3], (m + 1) * fieldSize[3]), fieldLength[3]);
            }
            else if (fields[3].length() > m * fieldSize[3])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[3]), fieldLength[3]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[4], fieldLength[4]);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", fieldLength[4]);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * ��ʽ���������շ�ҵ��������۷Ѽ�¼
     * ʱ�䣬ռ8���ֽڣ�����룻ҵ������ռ4���ֽڣ�����룻�������ռ9���ֽڣ�����룻�����ṩ��11���ֽڣ�����룻ʱ��ռ8���ֽڣ�����룻
     * ����ռ6���ֽڣ��Ҷ��롣
     * 
     * @param record
     * @return
     * @see 
     */
    private String formatInfofeeVoiceRecord(String record)
    {
        // ���ֶ���ռ�ֽ���
        int fieldLength[] = new int[]{8, 4, 9, 11, 8, 6};
        
        // ���ֶ�ÿ�д�ӡ������һ������ռ�����ֽ�
        int fieldSize[] = new int[]{8, 2, 9, 5, 8, 6};
        
        StringBuffer buffer = new StringBuffer(256);
        
        // ������¼�ĸ��ֶ�
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // ҵ������      
        if (fields[2].getBytes().length <= fieldLength[1])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % fieldSize[1])
            {
                tempRowNum = fields[2].length() / fieldSize[1];
            }
            else
            {
                tempRowNum = fields[2].length() / fieldSize[1] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // �������
        if (fields[3].getBytes().length <= fieldLength[2])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % fieldSize[2])
            {
                tempRowNum = fields[3].length() / fieldSize[2];
            }
            else
            {
                tempRowNum = fields[3].length() / fieldSize[2] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // �����ṩ��
        if (fields[4].getBytes().length <= fieldLength[3])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % fieldSize[3])
            {
                tempRowNum = fields[4].length() / fieldSize[3];
            }
            else
            {
                tempRowNum = fields[4].length() / fieldSize[3] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ʱ��
        tempRowNum = 0;
        
        int offset = 0;
        int len = 0;
        while (offset < fields[5].length())
        {
            len = fieldLength[4];
            
            if (offset + len > fields[5].length())
            {
                len = fields[5].length() - offset;
            }
            
            while (fields[5].substring(offset, offset + len).getBytes().length > fieldLength[4])
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
        
        rows = new String[rowNum];
        
        offset = 0;
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), fieldLength[0]); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            // ҵ������
            if (fields[2].getBytes().length <= fieldLength[1])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2], fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            else
            {
                if (fields[2].length() > (m + 1) * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
                }
                else if (fields[2].length() > m * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[1]), fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            
            // �������
            if (fields[3].length() > (m + 1) * fieldSize[2])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[2], (m + 1) * fieldSize[2]), fieldLength[2]);
            }
            else if (fields[3].length() > m * fieldSize[2])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[2]), fieldLength[2]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
            }
            
            // �����ṩ��
            if (fields[4].getBytes().length <= fieldLength[3])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4], fieldLength[3]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
                }
            }
            else
            {
                if (fields[4].length() > (m + 1) * fieldSize[3])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[3], (m + 1) * fieldSize[3]), fieldLength[3]);
                }
                else if (fields[4].length() > m * fieldSize[3])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[3]), fieldLength[3]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
                }
            }
            
            // ʱ��
            len = fieldLength[4];
            if (offset < fields[5].length())
            {
                if (offset + len > fields[5].length())
                {
                    len = fields[5].length() - offset;
                }
                
                while (fields[5].substring(offset, offset + len).getBytes().length > fieldLength[4])
                {
                    len -= 1;
                }
                rows[m] += CommonUtil.fillRightBlank(fields[5].substring(offset, offset + len), fieldLength[4]);
                offset += len; 
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[4]);
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[6], fieldLength[5]);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", fieldLength[5]);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }
    
    /**
     * ��ʽ���������շ�ҵ���������۷Ѽ�¼
     * ʱ�䣬ռ8���ֽڣ�����룻ҵ������ռ4���ֽڣ�����룻�������ռ9���ֽڣ�����룻�����ṩ��11���ֽڣ�����룻��������ռ8���ֽڣ�����룻
     * ����ռ6���ֽڣ��Ҷ��롣
     * 
     * @param record
     * @return
     * @see 
     */
    private String formatInfofeeNonVoiceRecord(String record)
    {
        // ���ֶ���ռ�ֽ���
        int fieldLength[] = new int[]{8, 4, 9, 11, 8, 6};
        
        // ���ֶ�ÿ�д�ӡ������һ������ռ�����ֽ�
        int fieldSize[] = new int[]{8, 2, 9, 5, 4, 6};
        
        StringBuffer buffer = new StringBuffer(256);
        
        // ������¼�ĸ��ֶ�
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // ҵ������      
        if (fields[2].getBytes().length <= fieldLength[1])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[2].length() % fieldSize[1])
            {
                tempRowNum = fields[2].length() / fieldSize[1];
            }
            else
            {
                tempRowNum = fields[2].length() / fieldSize[1] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // �������
        if (fields[3].getBytes().length <= fieldLength[2])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % fieldSize[2])
            {
                tempRowNum = fields[3].length() / fieldSize[2];
            }
            else
            {
                tempRowNum = fields[3].length() / fieldSize[2] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // �����ṩ��
        if (fields[4].getBytes().length <= fieldLength[3])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % fieldSize[3])
            {
                tempRowNum = fields[4].length() / fieldSize[3];
            }
            else
            {
                tempRowNum = fields[4].length() / fieldSize[3] + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // ��������
        if (fields[6].getBytes().length <= fieldLength[4])
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[6].length() % fieldSize[4])
            {
                tempRowNum = fields[6].length() / fieldSize[4];
            }
            else
            {
                tempRowNum = fields[6].length() / fieldSize[4] + 1;
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
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), fieldLength[0]); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], fieldLength[0]);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[0]);
            }
            
            // ҵ������
            if (fields[2].getBytes().length <= fieldLength[1])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2], fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            else
            {
                if (fields[2].length() > (m + 1) * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[1], (m + 1) * fieldSize[1]), fieldLength[1]);
                }
                else if (fields[2].length() > m * fieldSize[1])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[2].substring(m * fieldSize[1]), fieldLength[1]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[1]);
                }
            }
            
            // �������
            if (fields[3].length() > (m + 1) * fieldSize[2])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[2], (m + 1) * fieldSize[2]), fieldLength[2]);
            }
            else if (fields[3].length() > m * fieldSize[2])
            {
                rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * fieldSize[2]), fieldLength[2]);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", fieldLength[2]);
            }
            
            // �����ṩ��
            if (fields[4].getBytes().length <= fieldLength[3])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4], fieldLength[3]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
                }
            }
            else
            {
                if (fields[4].length() > (m + 1) * fieldSize[3])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[3], (m + 1) * fieldSize[3]), fieldLength[3]);
                }
                else if (fields[4].length() > m * fieldSize[3])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * fieldSize[3]), fieldLength[3]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[3]);
                }
            }
            
            // ��������
            if (fields[6].getBytes().length <= fieldLength[4])
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[6], fieldLength[4]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[4]);
                }
            }
            else
            {
                if (fields[6].length() > (m + 1) * fieldSize[4])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[6].substring(m * fieldSize[4], (m + 1) * fieldSize[4]), fieldLength[4]);
                }
                else if (fields[6].length() > m * fieldSize[4])
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[6].substring(m * fieldSize[4]), fieldLength[4]);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", fieldLength[4]);
                }
            }
            
            if (0 == m)
            {
                rows[m] += CommonUtil.fillLeftBlank(fields[7], fieldLength[5]);
            }
            else
            {
                rows[m] += CommonUtil.fillLeftBlank("", fieldLength[5]);
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
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
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        String servNumber = customerSimp.getServNumber();
        
        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_PATTERN_YYYYMM);
        String qryMonth = sdf1.format(new Date());
        
        DetailedRecordsTimesPO timesPO = detailedRecordsService.getPrintTimes(servNumber, qryMonth, cdrType);
        if (timesPO == null)
        {
            detailedRecordsService.addPrintTimes(servNumber, qryMonth, 1, cdrType);
        }
        else
        {
            detailedRecordsService.updatePrintTimes(servNumber, qryMonth, timesPO.getTimes() + 1, cdrType);
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("updatePrintTimes End");
        }
        return "success";
    }
    
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
        
        if (null != result && 0 < result.size())
        {
            CTagSet tagSet = (CTagSet)result.get("returnObj");
            
            // 0��δ��ͨ 1���ѿ�ͨ
            String haveMailbox = tagSet.GetValue("havemailbox");
            
            // modify by lKF60882 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹�������� 2017-4-1 begin
            if(StringUtils.isBlank(haveMailbox))
            {
                haveMailbox = tagSet.GetValue("ISHAVEMAILBOX");
            }
            // modify by lKF60882 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹�������� 2017-4-1 end
            
            if (null != haveMailbox && "1".equals(haveMailbox))
            {                
                out.write("1");
                out.flush();
                
                //modify begin sWX219697 2014-04-30 OR_SD_201404_105_ɽ��_��������139�����굥Ͷ����Ŀ�ĺ�
                //�굥�ʼ��·����أ�1��������0���رգ�
                String sendRecords = (String) PublicCache.getInstance().getCachedData(Constants.SEND_RECORDS_MAIL_SWITCH);

                if("1".equals(sendRecords))
                {
                	//ɽ���°��굥�ʼ��·��ӿ�
                	sendRecordsMail_new("0");
                }
                else
                {
                	sendMail("0");
                }
                //modify end sWX219697 2014-04-30 OR_SD_201404_105_ɽ��_��������139�����굥Ͷ����Ŀ�ĺ�
                
            }
            else
            {
                out.write("0");
                out.flush();
            }
        }
        else
        {
            // ��¼������־
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "��ѯ�û��Ƿ��ѿ�ͨ�ֻ�����ʧ��!");
            
            logger.error("��ѯ�û�" + customerSimp.getServNumber() + "�Ƿ��ѿ�ͨ�ֻ�����ʧ��!");
            
            out.write("2");
            out.flush();
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryMailbox End!");
        }
    }
    
    /**
     * <�굥�ʼ��·�-��ͨ139����>
     * <������ϸ����>
     * @throws IOException
     * @see [�ࡢ��#��������#��Ա]
     * @remark modify by sWX219697 2014-04-29 OR_SD_201404_105_ɽ��_��������139�����굥Ͷ����Ŀ�ĺ�
     */
    public void add139Mail() throws IOException
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("addBillMail Starting ...");
        }
        
        PrintWriter out = this.getResponse().getWriter();
        
        // ��ȡsession
        HttpSession session = this.getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ���ýӿڿ�ͨ139����
        Map result = detailedRecordsBean.add139Mail(customer, terminalInfoPO, curMenuId);
        
        if (null != result && 1 < result.size())
        {
            out.write("1");
            out.flush();
            
            // �ӳ�ʱ�䣨���ӣ�
            String delayTime = (String) PublicCache.getInstance().getCachedData("SH_MAIL_DELAY");
            
            if (null == delayTime || "".equals(delayTime.trim()))
            {
                delayTime = "3";
            }
            
            //modify begin sWX219697 2014-04-30 OR_SD_201404_105_ɽ��_��������139�����굥Ͷ����Ŀ�ĺ�
            //�Ƿ����°��굥�ʼ��·��ӿڣ�1��������0���رգ�
            String sendRecords = (String) PublicCache.getInstance().getCachedData(Constants.SEND_RECORDS_MAIL_SWITCH);

            if("1".equals(sendRecords))
            {
            	//ɽ���°��굥�ʼ��·��ӿ�
            	sendRecordsMail_new(delayTime);
            }
            else
            {
            	
                // 139���俪ͨ�ɹ��������ʼ�
                sendMail(delayTime);
            }
            //modify begin sWX219697 2014-04-30 OR_SD_201404_105_ɽ��_��������139�����굥Ͷ����Ŀ�ĺ�

        }
        else
        {
            // ��¼������־
            this.createRecLog(Constants.BUSITYPE_SUBSQRYMUSTER, "0", "0", "1", "139���俪ͨʧ��");
            
            logger.error("Ϊ�û�" + customer.getServNumber() + "��ͨ139����ʧ��!");
            
            out.write("0");
            out.flush();
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("addBillMail End");
        }
    }
    
    /**
     * <�굥�ʼ����ͷ���>
     * <������ϸ����>
     * @param delayTime �ӳ�ʱ��
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-04-30 OR_SD_201404_105_ɽ��_��������139�����굥Ͷ����Ŀ�ĺ�
     */
    private void sendRecordsMail_new(String delayTime)
    {
        HttpSession session = this.getRequest().getSession();
               
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        //�Ƿ��Ͷ������ѣ�0 ���ͣ�1 �����ͣ�Ĭ��Ϊ0��
        String nosms = "0";
        
        //�������ѿ����ж�
        String sendSms = (String) PublicCache.getInstance().getCachedData(Constants.SEND_RECORDS_NOSMS);
        
        //1�������Ͷ�������
        if("1".equals(sendSms))
        {
        	nosms="1";
        }
/*      // �ӳ�ʱ��,��λ������
        int delay = Integer.parseInt(delayTime);
        
        // �����ʼ�
        Timer timer = new Timer();
        
        //���ö�ʱ�������ʼ�
        timer.schedule(new SendRecordsMailUtil(customerSimp, terminalInfo, curMenuId, startDate, endDate, 
        		month, iscycle, cdrType, nosms), new Date(System.currentTimeMillis() + delay * 60L * 1000));
		//�굥�ʼ��·��ӿڵ���
*/		
        //modify by sWX219697 2014-8-13
        //�޸Ľӿڵ��÷�ʽ����Timer��ʱ���ýӿ�ʱ���ײ��ȡ����Action�������ģ�����ʧ��
        detailedRecordsBean.sendRecordsMail(customerSimp, terminalInfo, curMenuId, startDate, 
				endDate, month, iscycle, cdrType, nosms);
    }
    
    /**
     * ���û���139���䷢���굥��Ϣ
     * 
     * @param delayTime
     * @see 
     */
    private void sendMail(String delayTime)
    {
        StringBuffer mailContent = new StringBuffer(1024);
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
               
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        if ("0".equals(verFlag))
        {           
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
            
            // Vector�б����¼��ÿ����¼��һ��String������ֶ�֮����","����
            resultRecords = detailedRecordsBean.queryCDRListSDOld(customerSimp,
                    terminalInfo,
                    startDate,
                    endDate,
                    "",
                    cdrType,
                    curMenuId,
                    tableTitle.length - 1, month, iscycle);
            
            // ��ѯ���굥��¼
            if (null != resultRecords && 0 < resultRecords.size())
            {
                Map<String, String> totalMap = calDynTotalSD(resultRecords);
                
                // ƴװ�ʼ�����:��ͷ
                mailContent.append("<html>")
                        .append("<head>")
                        .append("<style type='text/css'>")
                        .append(".tb_blue02, .tb_blue02 td, .tb_blue02 th{border:1px solid #1a9ae3; border-collapse:collapse; font-size:14px; line-height:30px; height:30px; text-align:center;}")
                        .append(".tit_info{line-height:30px; height:30px; font-size:22px; position:relative;}")
                        .append("</style>")
                        .append("</head>")
                        .append("<body>")
                        .append("<p class='tit_info' align='left'>")
                        .append(tableHeader)
                        .append("</p>")
                        .append("<table class='tb_blue02' width='100%'>")
                        .append("<tr>");
                        
                for (int i = 0; i < tableTitle.length; i++)
                {
                    mailContent.append("<th>").append(tableTitle[i]).append("</th>");
                }
                
                mailContent.append("</tr>");
                
                // �ϼ����� [�굥������, ʱ���ϼƣ�, ���úϼ�(Ԫ)��]
                tableTail = (String[])(tableDetail[2]);
                
                if (tableDetail.length >= 5)
                {
                    tableDescription = (String[])tableDetail[4];
                }
                
                // ƴװ�ʼ����ݣ���ѯ���
                String recordStr = "";        
                String[] recordArray = null;
                for (int i = 0; i < resultRecords.size(); i++)
                {
                    recordStr = (String) resultRecords.get(i);            
                    recordArray = recordStr.split(",");
                    
                    mailContent.append("<tr>");
                    
                    for (int j = 0; j < recordArray.length; j++)
                    {
                        mailContent.append("<td>").append(recordArray[j]).append("</td>");
                    }
                    
                    mailContent.append("</tr>");
                }        
                
                // ƴװ�ʼ����ݣ��굥������, ʱ���ϼƣ�, ���úϼ�(Ԫ)��
                mailContent.append("<tr><td colspan='").append(tableTitle.length).append("'>");
                
                // �ϼ�Ӣ�� [recordcount, totaltimes, totalfee]
                String[] summaryTitle = (String[]) tableDetail[3];       
                for (int i = 0; i < summaryTitle.length; i++)
                {
                    if (0 == i)
                    {
                        mailContent.append("<strong>");
                    }
                    
                    mailContent.append(tableTail[i]).append(totalMap.get(summaryTitle[i])).append("     ");
                    
                    if (summaryTitle.length - 1 == i)
                    {
                        mailContent.append("</strong>");
                    }            
                }

                mailContent.append("</td></tr>");
                
                // ƴװ�ʼ����ݣ�������˵��
                if (tableDescription != null && tableDescription.length > 0)
                {
                    mailContent.append("<tr><td colspan='").append(tableTitle.length).append("'>");
                    
                    for (int i = 0; i < tableDescription.length; i++)
                    {
                        mailContent.append(tableDescription[i]).append("<br/>");
                    }
                    
                    mailContent.append("</td></tr>");
                }
                
                mailContent.append("</table></body></html>");
                
                if (logger.isDebugEnabled())
                {
                    logger.debug(mailContent.toString());
                }
            }
            else
            {
                logger.error("���û�" + customerSimp.getServNumber() + "��139�����з����굥��Ϣʱ����ѯʧ��");
            }            
        }
        else if ("1".equals(verFlag))
        {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy��MM��dd��");
            
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
            
            /*
             * �굥��¼��Ϣ������totalfee��smstotalnum��billinfo�ȡ� billinfoΪ08-03 21:56:04@_@����@_@����@_@66174709@_@19��@_@0.4|08-05
             * 21:56:04@_@����@_@����@_@66174709@_@19��@_@0.4
             */
            Map<String, String> resultMap = detailedRecordsBean.queryDetailedRecordsSD2012(customerSimp,
                    terminalInfo,
                    cdrType,
                    startDate,
                    endDate,
                    curMenuId,
                    iscycle,
                    cycle);
            
            if (null != resultMap)
            {
                // ƴװ�ʼ�����:��ͷ
                mailContent.append("<html>")
                        .append("<head>")
                        .append("<style type='text/css'>")
                        .append(".tb_blue02, .tb_blue02 td, .tb_blue02 th{border:1px solid #1a9ae3; border-collapse:collapse; font-size:14px; line-height:30px; height:30px; text-align:center;}")
                        .append(".tit_info{line-height:30px; height:30px; font-size:22px; position:relative; font-weight:bold;}")
                        .append("body{font-family:Arial,'΢���ź�' }")
                        .append(".bold{ font-weight:bold;}")
                        .append(".tb_blue02 td.tr, .tb_blue02 th.tr{text-align:right;}")
                        .append(".tb_blue02 td.tl, .tb_blue02 th.tl{text-align:left;}")
                        .append("</style>")
                        .append("</head>")
                        .append("<body>")
                        .append("<p class='tit_info' align='center'>�й��ƶ�ͨ�� �ͻ��굥</p>")
                        .append("<table class='tb_blue02' width='60%' align='center'>")              
                        .append("<tr>")
                        .append("<th class='tl'>�ͻ�����</th><td class='tl'>")
                        .append(customerSimp.getCustomerName())
                        .append("</td><th class='tl'>�ֻ�����</th><td class='tl'>")
                        .append(customerSimp.getServNumber())
                        .append("</td></tr>")
                        .append("<tr>")
                        .append("<th class='tl'>��ѯʱ��</th><td class='tl'>")
                        .append(chStartDate).append("-").append(chEndDate)
                        .append("</td><th class='tl'>��ѯ����</th><td class='tl'>")
                        .append(chQueryDate)
                        .append("</td></tr>")
                        .append("</table>")
                        .append("<table class='tb_blue02' width='100%' style='margin-top: 30px;'>");
                
                // �ײͼ��̶����굥
                if (Constants.CDRTYPE_FIXFEE.equalsIgnoreCase(cdrType))
                {
                    mailContent.append("<tr>")
                            .append("<th colspan='4' class='tl'>�ײͼ��̶����굥</th>")
                            .append("</tr>")
                            .append("<tr><th>�۷�����</th><th>ʹ������</th><th>�ײͼ��̶�������</th><th>����(Ԫ)</th></tr>");
                    
                    String allRecords = resultMap.get(cdrType);
                    if (null != allRecords && !"".equals(allRecords.trim()))
                    {
                        // �ϼƷ���
                        String[] recordsArray = allRecords.split("\\|");
                        int recordsCount = recordsArray.length;
                        
                        // ������¼
                        String record = "";                        
                        String[] fields = null;
                        
                        for (int i = 0; i < recordsCount; i++)
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
                            
                            mailContent.append("<tr>");
                            
                            fields = record.split("@_@");                            
                            for (int m = 0; m < fields.length; m++)
                            {                               
                                if (m == fields.length - 1)
                                {
                                    mailContent.append("<td class='tr'>").append(fields[m]).append("</td>");
                                }
                                else
                                {
                                    mailContent.append("<td>").append(fields[m]).append("</td>");
                                }
                            }
                            
                            mailContent.append("</tr>");
                        }
                        
                        String totalfee = resultMap.get("totalfee");
                        if (null == totalfee || "".equals(totalfee))
                        {
                            totalfee = "0";
                        }
                        mailContent.append("<tr><td>�ϼƣ�</td><td></td><td></td><td class='tr'>"+totalfee+"</td></tr>");
                    }
                }
                else if (Constants.CDRTYPE_GSM.equalsIgnoreCase(cdrType))
                {                    
                    parseSingleTypeRecords(resultMap);
                    
                    mailContent.append("<tr>")
                            .append("<th colspan='9' class='tl'>ͨ���굥</th>")
                            .append("</tr>")
                            .append("<tr><th>��ʼʱ��(hh:mm:ss)</th><th>ͨ�ŵص�</th><th>ͨ�ŷ�ʽ</th><th>�Է�����</th>")
                            .append("<th>ͨ��ʱ��</th><th>ͨ������</th><th>�ײ��Ż�</th><th>ͨ����(Ԫ)</th><th>��;��(Ԫ)</th></tr>");
                    // begin zKF66389 2015-09-15 9�·�findbugs�޸�
                    //if (null != resultRecords || 0 < resultRecords.size())
                    if (0 < resultRecords.size())
                    {
                        List<String> dayRecordsList = null;
                        
                        for (int m = 0; m < resultRecords.size(); m++)
                        {
                            if (0 == m % 2)
                            {
                                mailContent.append("<tr><td colspan='9' class='tl'>").append((String) resultRecords.get(m)).append("</td></tr>");
                            }
                            else
                            {
                                dayRecordsList = (List<String>) resultRecords.get(m);
                                
                                if (null != dayRecordsList && 0 < dayRecordsList.size())
                                {
                                    // ������¼
                                    String record = "";                        
                                    String[] fields = null;
                                    
                                    for (int k = 0; k < dayRecordsList.size(); k++)
                                    {
                                        record = dayRecordsList.get(k);
                                        
                                        // �����@_@��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
                                        if (record.endsWith("@_@"))
                                        {
                                            record = record + " ";
                                        }
                                        
                                        mailContent.append("<tr>");
                                        
                                        fields = record.split("@_@");                            
                                        for (int p = 0; p < fields.length; p++)
                                        {                               
                                            if (p == fields.length - 1 || p == fields.length - 2)
                                            {
                                                mailContent.append("<td class='tr'>").append(fields[p]).append("</td>");
                                            }
                                            else
                                            {
                                                mailContent.append("<td>").append(fields[p]).append("</td>");
                                            }
                                        }
                                        
                                        mailContent.append("</tr>");
                                    }                                    
                                }
                            }
                        }
                        // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 begin
                        mailContent.append("<tr><td>�ϼƣ�</td><td></td><td></td><td></td><td>"+resultMap.get("txtotaltime")+"</td><td></td><td></td><td>"+resultMap.get("thtotalfee")+"</td><td>"+resultMap.get("cttotalfee")+"</td></tr>");
                        //mailContent.append("<tr><td>�ϼƣ�</td><td></td><td></td><td></td><td></td><td></td><td></td><td colspan='2' class='tr'>").append(resultMap.get("totalfee")).append("</td></tr>");
                        // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 end
                    }           
                }
                else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(cdrType))
                {
                    parseSmsRecords(resultMap);
                    
                    mailContent.append("<tr>")
                            .append("<th colspan='8' class='tl'>��/�����굥</th>")
                            .append("</tr>")
                            .append("<tr>")
                            .append("<th>������������</th><th>").append(resultMap.get("smstotalnum")).append("��</th><th>������������</th><th>").append(resultMap.get("mmstotalnum")).append("��</th>")
                            .append("</tr>")
                            .append("<tr><th>��ʼʱ��(hh:mm:ss)</th><th>ͨ�ŵص�</th><th>�Է�����</th>")
                            .append("<th>ͨ�ŷ�ʽ</th><th>��Ϣ����</th><th>ҵ������</th><th>�ײ��Ż�</th><th>ͨ�ŷ�(Ԫ)</th></tr>");
                    // begin zKF66389 2015-09-15 9�·�findbugs�޸�
                    //if (null != resultRecords || 0 < resultRecords.size())
                    if (0 < resultRecords.size())
                    {
                        List<String> dayRecordsList = null;
                        
                        for (int m = 0; m < resultRecords.size(); m++)
                        {
                            if (0 == m % 2)
                            {
                                mailContent.append("<tr><td colspan='8' class='tl'>").append((String) resultRecords.get(m)).append("</td></tr>");
                            }
                            else
                            {
                                dayRecordsList = (List<String>) resultRecords.get(m);
                                
                                if (null != dayRecordsList && 0 < dayRecordsList.size())
                                {
                                    // ������¼
                                    String record = "";                        
                                    String[] fields = null;
                                    
                                    for (int k = 0; k < dayRecordsList.size(); k++)
                                    {
                                        record = dayRecordsList.get(k);
                                        
                                        // �����@_@��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
                                        if (record.endsWith("@_@"))
                                        {
                                            record = record + " ";
                                        }
                                        
                                        mailContent.append("<tr>");
                                        
                                        fields = record.split("@_@");                            
                                        for (int p = 0; p < fields.length; p++)
                                        {                               
                                            if (p == fields.length - 1)
                                            {
                                                mailContent.append("<td class='tr'>").append(fields[p]).append("</td>");
                                            }
                                            else
                                            {
                                                mailContent.append("<td>").append(fields[p]).append("</td>");
                                            }
                                        }
                                        
                                        mailContent.append("</tr>");
                                    }                                    
                                }
                            }
                        }
                        
                        mailContent.append("<tr><td>�ϼƣ�</td><td></td><td></td><td></td><td></td><td></td><td></td><td class='tr'>").append(resultMap.get("totalfee")).append("</td></tr>");
                    }  
                }
                else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(cdrType))
                {
                    parseGprsWlanRecords(resultMap);
                    
                    mailContent.append("<tr>")
                            .append("<th colspan='7' class='tl'>�����굥</th>")
                            .append("</tr>")
                            .append("<tr>")
                            .append("<th colspan='2'>�ƶ�����������������������</th><th>")
                            .append(resultMap.get("gprstotalnum"))
                            .append("��</th>")
                            .append("<th colspan='2'>�ƶ��������������ܷ���</th><th></th><th>")
                            .append(resultMap.get("gprstotalfee"))
                            .append("Ԫ</th>")
                            .append("</tr>")
                            .append("<tr>")
                            .append("<th colspan='2'>WLAN����������</th><th>")
                            .append(resultMap.get("wlantotalnum"))
                            .append("��</th>")
                            .append("<th colspan='2'>WLAN�����ܷ���</th><th></th><th>")
                            .append(resultMap.get("wlantotalfee"))
                            .append("Ԫ</th>")
                            .append("</tr>")
                            
                            // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 begin
                            // modify begin zKF66389 2015-6-13 OR_SD_201412_479_SD_�����޸������굥��ѯ�Ļ�����Ϣ��ʱ���ֶ���ʾ������
//                            .append("<tr>")
//                            .append("<th colspan='2'>�ƶ���������������ʱ��</th><th>")
//                            .append(resultMap.get("gprstotaltime"))
//                            .append("</th>")
//                            .append("<th colspan='2'>�ƶ�������������������</th><th></th><th>")
//                            .append(resultMap.get("gprstotalflux"))
//                            .append("</th>")
//                            .append("</tr>")
//                            
//                            .append("<tr>")
//                            .append("<th colspan='2'>WLAN������ʱ��</th><th>")
//                            .append(resultMap.get("wlantotaltime"))
//                            .append("</th>")
//                            .append("<th colspan='2'>WLAN����������</th><th></th><th>")
//                            .append(resultMap.get("wlantotalflux"))
//                            .append("</th>")
//                            .append("</tr>")
                            .append("<tr>")
                            .append("<th colspan='2'>�ƶ�������������������</th><th>")
                            .append(resultMap.get("gprstotalflux"))
                            .append("</th>")
                            .append("<th colspan='2'>WLAN����������</th><th></th><th>")
                            .append(resultMap.get("wlantotalflux"))
                            .append("</th>")
                            .append("</tr>")
                            
                            .append("<tr>")
                            .append("<th colspan='2'>WLAN������ʱ��</th><th>")
                            .append(resultMap.get("wlantotaltime"))
                            .append("</th>")
                            .append("<th colspan='2'></th><th></th><th>")
                            .append("")
                            .append("</th>")
                            .append("</tr>")
                            // modify end zKF66389 2015-6-13 OR_SD_201412_479_SD_�����޸������굥��ѯ�Ļ�����Ϣ��ʱ���ֶ���ʾ������
                            // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 end
                            
                            .append("<tr><th>��ʼʱ��(hh:mm:ss)</th><th>ͨ�ŵص�</th><th>������ʽ</th>")
                            .append("<th>ʱ��</th><th>����</th><th>�ײ��Ż�</th><th>ͨ�ŷ�(Ԫ)</th></tr>");
                    // begin zKF66389 2015-09-15 9�·�findbugs�޸�
                    //if (null != resultRecords || 0 < resultRecords.size())
                    if (0 < resultRecords.size())
                    {
                        List<String> dayRecordsList = null;
                        
                        for (int m = 0; m < resultRecords.size(); m++)
                        {
                            if (0 == m % 2)
                            {
                                mailContent.append("<tr><td colspan='7' class='tl'>").append((String) resultRecords.get(m)).append("</td></tr>");
                            }
                            else
                            {
                                dayRecordsList = (List<String>) resultRecords.get(m);
                                
                                if (null != dayRecordsList && 0 < dayRecordsList.size())
                                {
                                    // ������¼
                                    String record = "";                        
                                    String[] fields = null;
                                    
                                    for (int k = 0; k < dayRecordsList.size(); k++)
                                    {
                                        record = dayRecordsList.get(k);
                                        
                                        // �����@_@��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
                                        if (record.endsWith("@_@"))
                                        {
                                            record = record + " ";
                                        }
                                        
                                        mailContent.append("<tr>");
                                        
                                        fields = record.split("@_@");                            
                                        for (int p = 0; p < fields.length; p++)
                                        {     
                                            if (p == fields.length - 1)
                                            {
                                                mailContent.append("<td class='tr'>").append(fields[p]).append("</td>");
                                            }
                                            else
                                            {
                                                mailContent.append("<td>").append(fields[p]).append("</td>");
                                            }
                                        }
                                        
                                        mailContent.append("</tr>");
                                    }                                    
                                }
                            }
                        }
                    }
                }
                else if (Constants.CDRTYPE_ISMG.equalsIgnoreCase(cdrType))
                {
                    parseMultiTypesRecords(resultMap);
                    
                    mailContent.append("<tr>")
                            .append("<th colspan='6' class='tl'>��ֵҵ��۷Ѽ�¼</th>")
                            .append("</tr>");                            
                    // begin zKF66389 2015-09-15 9�·�findbugs�޸�
                    //if (null != voiceRecords || 0 < voiceRecords.size())
                    if (0 < voiceRecords.size())
                    {
                        mailContent.append("<tr>")
                                .append("<th colspan='6' class='tl'>������ҵ��۷Ѽ�¼</th>")
                                .append("</tr>")
                                .append("<tr><th>ʱ��(hh:mm:ss)</th><th>ʹ�÷�ʽ</th><th>ҵ������</th>")
                                .append("<th>�������</th><th>ʱ��</th><th>����(Ԫ)</th></tr>");;
                        
                        List<String> dayRecordsList = null;
                        
                        for (int m = 0; m < voiceRecords.size(); m++)
                        {
                            if (0 == m % 2)
                            {
                                mailContent.append("<tr><td colspan='6' class='tl'>").append((String) voiceRecords.get(m)).append("</td></tr>");
                            }
                            else
                            {
                                dayRecordsList = (List<String>) voiceRecords.get(m);
                                
                                if (null != dayRecordsList && 0 < dayRecordsList.size())
                                {
                                    // ������¼
                                    String record = "";                        
                                    String[] fields = null;
                                    
                                    for (int k = 0; k < dayRecordsList.size(); k++)
                                    {
                                        record = dayRecordsList.get(k);
                                        
                                        // �����@_@��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
                                        if (record.endsWith("@_@"))
                                        {
                                            record = record + " ";
                                        }
                                        
                                        mailContent.append("<tr>");
                                        
                                        fields = record.split("@_@");                            
                                        for (int p = 0; p < fields.length; p++)
                                        {                               
                                            if (p == fields.length - 1)
                                            {
                                                mailContent.append("<td class='tr'>").append(fields[p]).append("</td>");
                                            }
                                            else
                                            {
                                                mailContent.append("<td>").append(fields[p]).append("</td>");
                                            }
                                        }
                                        
                                        mailContent.append("</tr>");
                                    }                                    
                                }
                            }
                        }
                        // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 begin
                        mailContent.append("<tr><td>�ϼƣ�</td><td></td><td></td><td></td><td>"+resultMap.get("zzywtotaltime")+"</td><td class='tr'>").append(resultMap.get("totalfee-voice")).append("</td></tr></table>");
                        //mailContent.append("<tr><td>�ϼƣ�</td><td></td><td></td><td></td><td></td><td class='tr'>").append(resultMap.get("totalfee-voice")).append("</td></tr></table>");
                        // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 end
                    }
                    // begin zKF66389 2015-09-15 9�·�findbugs�޸�
                    //if (null != nonVoiceRecords || 0 < nonVoiceRecords.size())
                    if (0 < nonVoiceRecords.size())
                    {
                        mailContent.append("<table class='tb_blue02' width='100%' style='margin-top: 30px;'><tr>")
                                .append("<th colspan='5' class='tl'>��������ҵ��۷Ѽ�¼</th>")
                                .append("</tr>")
                                .append("<tr><th>ʱ��(hh:mm:ss)</th><th>ʹ�÷�ʽ</th><th>ҵ������</th>")
                                .append("<th>ҵ��˿�</th><th>����</th></tr>");;
                        
                        List<String> dayRecordsList = null;
                        
                        for (int m = 0; m < nonVoiceRecords.size(); m++)
                        {
                            if (0 == m % 2)
                            {
                                mailContent.append("<tr><td colspan='5' class='tl'>").append((String) nonVoiceRecords.get(m)).append("</td></tr>");
                            }
                            else
                            {
                                dayRecordsList = (List<String>) nonVoiceRecords.get(m);
                                
                                if (null != dayRecordsList && 0 < dayRecordsList.size())
                                {
                                    // ������¼
                                    String record = "";                        
                                    String[] fields = null;
                                    
                                    for (int k = 0; k < dayRecordsList.size(); k++)
                                    {
                                        record = dayRecordsList.get(k);
                                        
                                        // �����@_@��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
                                        if (record.endsWith("@_@"))
                                        {
                                            record = record + " ";
                                        }
                                        
                                        mailContent.append("<tr>");
                                        
                                        fields = record.split("@_@");                            
                                        for (int p = 0; p < fields.length; p++)
                                        {                               
                                            if (p == fields.length - 1)
                                            {
                                                mailContent.append("<td class='tr'>").append(fields[p]).append("</td>");
                                            }
                                            else
                                            {
                                                mailContent.append("<td>").append(fields[p]).append("</td>");
                                            }
                                        }
                                        
                                        mailContent.append("</tr>");
                                    }                                    
                                }
                            }
                        }
                        
                        mailContent.append("<tr><td>�ϼƣ�</td><td></td><td></td><td></td><td class='tr'>").append(resultMap.get("totalfee-nonvoice")).append("</td></tr>");
                    }
                }
                else if (Constants.CDRTYPE_INFOFEE.equalsIgnoreCase(cdrType))
                {
                    parseMultiTypesRecords(resultMap);
                    
                    mailContent.append("<tr>")
                            .append("<th colspan='7' class='tl'>���շ�ҵ��۷Ѽ�¼</th>")
                            .append("</tr>");                            
                    // begin zKF66389 2015-09-15 9�·�findbugs�޸�
                    //if (null != voiceRecords || 0 < voiceRecords.size())
                    if (0 < voiceRecords.size())
                    {
                        mailContent.append("<tr>")
                                .append("<th colspan='7' class='tl'>������ҵ��۷Ѽ�¼</th>")
                                .append("</tr>")
                                .append("<tr><th>ʱ��(hh:mm:ss)</th><th>ʹ�÷�ʽ</th><th>ҵ������</th>")
                                .append("<th>�������</th><th>�����ṩ��</th><th>ʱ��</th><th>����(Ԫ)</th></tr>");;
                
                        List<String> dayRecordsList = null;
                        
                        for (int m = 0; m < voiceRecords.size(); m++)
                        {
                            if (0 == m % 2)
                            {
                                mailContent.append("<tr><td colspan='7' class='tl'>").append((String) voiceRecords.get(m)).append("</td></tr>");
                            }
                            else
                            {
                                dayRecordsList = (List<String>) voiceRecords.get(m);
                                
                                if (null != dayRecordsList && 0 < dayRecordsList.size())
                                {
                                    // ������¼
                                    String record = "";                        
                                    String[] fields = null;
                                    
                                    for (int k = 0; k < dayRecordsList.size(); k++)
                                    {
                                        record = dayRecordsList.get(k);
                                        
                                        // �����@_@��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
                                        if (record.endsWith("@_@"))
                                        {
                                            record = record + " ";
                                        }
                                        
                                        mailContent.append("<tr>");
                                        
                                        fields = record.split("@_@");                            
                                        for (int p = 0; p < fields.length; p++)
                                        {                               
                                            if (p == fields.length - 1)
                                            {
                                                mailContent.append("<td class='tr'>").append(fields[p]).append("</td>");
                                            }
                                            else
                                            {
                                                mailContent.append("<td>").append(fields[p]).append("</td>");
                                            }
                                        }
                                        
                                        mailContent.append("</tr>");
                                    }                                    
                                }
                            }
                        }
                        
                        // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 begin
                        mailContent.append("<tr><td>�ϼƣ�</td><td></td><td></td><td></td><td></td><td>"+resultMap.get("dsftotaltime")+"</td><td class='tr'>"+resultMap.get("totalfee-voice")+"</td></tr></table>");
                        //mailContent.append("<tr><td>�ϼƣ�</td><td></td><td></td><td></td><td></td><td></td><td>").append(resultMap.get("totalfee-voice")).append("</td></tr></table>");
                        // �굥�Ż������Ӹ��ֺϼ�  add by yKF28472 2012-07-09 end
                    }
                    // begin zKF66389 2015-09-15 9�·�findbugs�޸�
                    //if (null != nonVoiceRecords || 0 < nonVoiceRecords.size())
                    if (0 < nonVoiceRecords.size())
                    {
                        mailContent.append("<table class='tb_blue02' width='100%' style='margin-top: 30px;'><tr>")
                                .append("<th colspan='7' class='tl'>��������ҵ��۷Ѽ�¼</th>")
                                .append("</tr>")
                                .append("<tr><th>ʱ��(hh:mm:ss)</th><th>ʹ�÷�ʽ</th><th>ҵ������</th>")
                                .append("<th>�������</th><th>�����ṩ��</th><th>��ҵ����</th><th>��������</th><th>����(Ԫ)</th></tr>");;
                        
                        List<String> dayRecordsList = null;
                        
                        for (int m = 0; m < nonVoiceRecords.size(); m++)
                        {
                            if (0 == m % 2)
                            {
                                mailContent.append("<tr><td colspan='7' class='tl'>").append((String) nonVoiceRecords.get(m)).append("</td></tr>");
                            }
                            else
                            {
                                dayRecordsList = (List<String>) nonVoiceRecords.get(m);
                                
                                if (null != dayRecordsList && 0 < dayRecordsList.size())
                                {
                                    // ������¼
                                    String record = "";                        
                                    String[] fields = null;
                                    
                                    for (int k = 0; k < dayRecordsList.size(); k++)
                                    {
                                        record = dayRecordsList.get(k);
                                        
                                        // �����@_@��˵�����һ���ֶ�Ϊ�գ�׷�ӿո�
                                        if (record.endsWith("@_@"))
                                        {
                                            record = record + " ";
                                        }
                                        
                                        mailContent.append("<tr>");
                                        
                                        fields = record.split("@_@");                            
                                        for (int p = 0; p < fields.length; p++)
                                        {                               
                                            if (p == fields.length - 1)
                                            {
                                                mailContent.append("<td class='tr'>").append(fields[p]).append("</td>");
                                            }
                                            else
                                            {
                                                mailContent.append("<td>").append(fields[p]).append("</td>");
                                            }
                                        }
                                        
                                        mailContent.append("</tr>");
                                    }                                    
                                }
                            }
                        }
                        
                        mailContent.append("<tr><td>�ϼƣ�</td><td></td><td></td><td></td><td></td><td></td><td></td><td class='tr'>").append(resultMap.get("totalfee-nonvoice")).append("</td></tr>");
                    }
                }
                
                mailContent.append("</table></body></html>");

                if (logger.isDebugEnabled())
                {
                    logger.debug(mailContent.toString());
                }
            }
        }
        else
        {
            logger.error("���û�" + customerSimp.getServNumber() + "��139�����з����굥��Ϣʧ�ܣ���֧�ֵĲ�ѯ�汾" + verFlag);
        }
        
        if (null != mailContent && 0 < mailContent.length())
        {
            // �ռ�������
            String to = customerSimp.getServNumber() + "@139.com";
            
            // �ʼ�����
            String subject = "��" + chStartDate + "��" + chEndDate + "���굥��Ϣ�ѵ�������ա�";
            
            // �ӳ�ʱ��,��λ������
            int delay = Integer.parseInt(delayTime);
            
            // �����ʼ�
            Timer timer = new Timer();
            timer.schedule(new SendMailUtil(to, subject, mailContent.toString()), new Date(
                    System.currentTimeMillis() + delay * 60L * 1000));
        }
    }
    
    /** 
     * ȡ���Ƿ���ʾGprsWlan�������Ż�ʱ���������ز���
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark add by zKF69263 2014-11-20 OR_SD_201311_1126 ���������굥��ѯչʾ���������Ż�ʱ�ε��޸��������
     */
    public String getShowGprsWlanFlux()
    {
        // GprsWlan��ʾ�������Ż�ʱ���������ز�����1����ʾ  0������ʾ��
        return CommonUtil.getParamValue(Constants.SH_GPRSWLAN_SHOWFLUX);
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
    
    public String getStartDate()
    {
        return startDate;
    }
    
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    
    public String getEndDate()
    {
        return endDate;
    }
    
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
    public List<CycleInfoPO> getCycles()
    {
        return cycles;
    }
    
    public void setCycles(List<CycleInfoPO> cycles)
    {
        this.cycles = cycles;
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
    
    public String getQueryDate()
    {
        return queryDate;
    }
    
    public void setQueryDate(String queryDate)
    {
        this.queryDate = queryDate;
    }
    
    public List<String> getFixfeeRecords()
    {
        return fixfeeRecords;
    }
    
    public void setFixfeeRecords(List<String> fixfeeRecords)
    {
        this.fixfeeRecords = fixfeeRecords;
    }
    
    public Vector getResultRecords()
    {
        return resultRecords;
    }
    
    public void setResultRecords(Vector resultRecords)
    {
        this.resultRecords = resultRecords;
    }
    
    public Vector getVoiceRecords()
    {
        return voiceRecords;
    }
    
    public void setVoiceRecords(Vector voiceRecords)
    {
        this.voiceRecords = voiceRecords;
    }
    
    public Vector getNonVoiceRecords()
    {
        return nonVoiceRecords;
    }
    
    public void setNonVoiceRecords(Vector nonVoiceRecords)
    {
        this.nonVoiceRecords = nonVoiceRecords;
    }
    
    public String getChQueryDate()
    {
        return chQueryDate;
    }
    
    public void setChQueryDate(String chQueryDate)
    {
        this.chQueryDate = chQueryDate;
    }
    
    public String getFarthestDate()
    {
        return farthestDate;
    }
    
    public void setFarthestDate(String farthestDate)
    {
        this.farthestDate = farthestDate;
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
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public int getTitleCols()
    {
        return titleCols;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
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
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPrintFlag()
    {
        return printFlag;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPrintFlag(String printFlag)
    {
        this.printFlag = printFlag;
    }

    public String getVerFlag()
    {
        return verFlag;
    }

    public void setVerFlag(String verFlag)
    {
        this.verFlag = verFlag;
    }

    public String getCustName()
    {
        return custName;
    }

    public void setCustName(String custName)
    {
        this.custName = custName;
    }

    public String getIscycle()
    {
        return iscycle;
    }

    public void setIscycle(String iscycle)
    {
        this.iscycle = iscycle;
    }

	public String getDetailPwd() {
		return detailPwd;
	}

	public void setDetailPwd(String detailPwd) {
		this.detailPwd = detailPwd;
	}
}