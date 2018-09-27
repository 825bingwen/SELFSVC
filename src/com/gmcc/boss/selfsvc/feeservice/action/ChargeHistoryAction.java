/*
 * �ļ�����ChargeHistoryAction.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-12-17
 * �޸����ݣ��������ɷ���ʷ��¼��ѯ
 */
package com.gmcc.boss.selfsvc.feeservice.action;

import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.bean.ChargeHistoryBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * �������ڵĽɷ���ʷ��¼��ѯ
 * 
 * @author g00140516
 * @version 1.0��2010-12-17
 * @see
 * @since
 */
public class ChargeHistoryAction extends BaseAction
{
    private static Log logger = LogFactory.getLog(ChargeHistoryAction.class);
    
    private static final long serialVersionUID = -3950351941316700610L;
    
    private transient ChargeHistoryBean chargeHistoryBean = null;
    
    private String curMenuId = "";
    
    private String tableTitle = "";
    
    private List records = null;
    
    private String pageNo = "";
    
    // ��ʼʱ��
    private String startDate = "";
    
    // ����ʱ��
    private String endDate = "";

    /**
     * ��ѯ�ɷ���ʷ��¼����������ģ����û����뿪ʼ���ڡ��������ڣ����������ʡ�ݣ�Ĭ�ϲ�ѯ�������ڵ�
     * 
     * @return
     * @see
     */
    public String qryChargeHistory()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryChargeHistory Starting ...");
        }
        
        //���ĵĽɷ���ʷ��ѯ�����û������ѯ��ʼʱ�䡢����ʱ��
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
        {
            return "inputMonth";
        }
        
        return qryCharegeHistoryRecords();
    }
    
    public String qryCharegeHistoryRecords()
    {
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
        {
            // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
            this.getRequest().setAttribute("backStep", "-1");
            // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
            
            // ���û�����Ŀ�ʼʱ�䡢����ʱ����и�ʽ�ж�
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
            String errMsg = getErrMsg();
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
            
            if (!"".equals(errMsg))
            {
                this.getRequest().setAttribute("errormessage", errMsg);
                
                this.createRecLog(Constants.BUSITYPE_FEEHISTORY, "0", "0", "1", errMsg);
                
                logger.error(errMsg);
                
                return forward;
            }
        }
        else
        {
            startDate = CommonUtil.getLastMonth("yyyyMMdd", 6);        
            
            endDate = CommonUtil.getLastMonth("yyyyMMdd", 0);
        }     
        
        // �������ڵ�ȫ����¼
        Vector result = chargeHistoryBean.qryChargeHistory(customerSimp, termInfo, startDate, endDate, curMenuId);
        
        List allRecords = null;
        
        if (result != null && result.size() > 1)
        {
            tableTitle = (String)result.get(0);
            
            allRecords = (List)result.get(1);
        }
        
        if (allRecords == null || allRecords.size() == 0)
        {
            if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
            {
                this.getRequest().setAttribute("errormessage", "δ��ѯ��������ʷ��¼");
                
                this.createRecLog(Constants.BUSITYPE_FEEHISTORY, "0", "0", "1", "������ʷ��ѯ������δ���ҵ��������ڵĽ��Ѽ�¼");
                
                logger.error("������ʷ��ѯ��δ���ҵ�" + startDate + "��" + endDate + "֮��ļ�¼");
            }
            else
            {
                String resultMsg = getConvertMsg("δ��ѯ���ɷ���ʷ��¼", "zz_04_16_000001", null, null);
                
                this.getRequest().setAttribute("errormessage", resultMsg);
                
                this.createRecLog(Constants.BUSITYPE_FEEHISTORY, "0", "0", "1", resultMsg);
                
                logger.error("�ɷ���ʷ��ѯ��δ���ҵ�" + startDate + "��" + endDate + "֮��ļ�¼");
            }
        }
        else
        {
            if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
            {
                this.createRecLog(Constants.BUSITYPE_FEEHISTORY, "0", "0", "0", "������ʷ��ѯ�ɹ�");
            }
            else
            {
                this.createRecLog(Constants.BUSITYPE_FEEHISTORY, "0", "0", "0", "�ɷ���ʷ��ѯ�ɹ�");
            }
            
            this.getRequest().setAttribute("total", String.valueOf(allRecords.size()));
            
            // ��¼��ҳ��ʾ
            // records = getQueryPageData(allRecords);
            records = allRecords;

            forward = "success";
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryChargeHistory End");
        }
        
        return forward;
    }

    /**
     * ���û�����Ŀ�ʼʱ�䡢����ʱ����и�ʽ�ж�
     * @param startDate
     * @param endDate
     * @return �������
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
     */
	private String getErrMsg()
	{
		String errMsg = "";
		
		String pattern = "\\d{8}";
		String currMonth = CommonUtil.getLastMonth("yyyyMMdd", 0);
		
		if (startDate == null || "".equals(startDate) || !Pattern.matches(pattern, startDate)
		        || endDate == null || "".equals(endDate) || !Pattern.matches(pattern, endDate))
		{
		    errMsg = "�ɷ���ʷ��ѯ����ʼʱ��" + startDate + "�����ʱ��" + endDate + "��ʽ������ȷ��ʽ��yyyyMMdd����20110101";
		}
		else if (startDate.compareTo(currMonth) > 0 || endDate.compareTo(currMonth) > 0)
		{
		    errMsg = "�ɷ���ʷ��ѯ����ʼʱ��" + startDate + "�ͽ���ʱ��" + endDate + "�����ܴ��ڵ�ǰ��" + currMonth;
		}
		else if (startDate.compareTo(endDate) > 0)
		{
		    errMsg = "�ɷ���ʷ��ѯ����ʼʱ��" + startDate + "���ܴ��ڽ���ʱ��" + endDate;
		}
		return errMsg;
	}
    
    /**
     * ��¼��ҳ��ʾ
     * 
     * @param allRecords��ȫ����¼
     * @return
     * @see
     */
    public List getQueryPageData(List allRecords)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("getQueryPageData Starting ...");
        }
        
        Vector result = new Vector();
        if (allRecords != null && allRecords.size() > 0)
        {
            if (pageNo == null || pageNo.trim().length() < 1)
            {
                pageNo = "1";
            }
            
            int iPage = Integer.parseInt(pageNo);
            
            int maxSize = iPage * Constants.DEFAULT_PAGE_SIZE;
            
            maxSize = (maxSize <= allRecords.size()) ? maxSize : allRecords.size();
            
            for (int i = (iPage - 1) * Constants.DEFAULT_PAGE_SIZE; i < maxSize; i++)
            {
                result.add(allRecords.get(i));
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("getQueryPageData End");
        }
        
        return result;
    }
    
    public ChargeHistoryBean getChargeHistoryBean()
    {
        return chargeHistoryBean;
    }
    
    public void setChargeHistoryBean(ChargeHistoryBean chargeHistoryBean)
    {
        this.chargeHistoryBean = chargeHistoryBean;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getTableTitle()
    {
        return tableTitle;
    }
    
    public void setTableTitle(String tableTitle)
    {
        this.tableTitle = tableTitle;
    }
    
    public List getRecords()
    {
        return records;
    }
    
    public void setRecords(List records)
    {
        this.records = records;
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
}
