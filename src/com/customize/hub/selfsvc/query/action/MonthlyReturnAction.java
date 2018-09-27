/**
 * 
 */
package com.customize.hub.selfsvc.query.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.QueryBean;
import com.customize.hub.selfsvc.common.ConstantsHub;
import com.customize.hub.selfsvc.query.model.MonthlyReturnInfoVO;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * �������·������������ѯ���� ���·������������ѯ Add by LiFeng [XQ[2011]_06_020]�����������·������������ѯ�����ص�����
 * 
 * @author LiFeng
 * @version [�汾��, 2011-9-13]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class MonthlyReturnAction extends BaseAction
{
    
    /**
     * ע������
     */
    private static final long serialVersionUID = 1L;
    
    // modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
    //private static final Logger log = Logger.getLogger(MonthlyReturnAction.class);
    private static Log log = LogFactory.getLog(MonthlyReturnAction.class);
    // modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
    
    private QueryBean queryBean;
    
    private String startDate;
    
    private String endDate;
    
    private Vector<MonthlyReturnInfoVO> mrInfoList;
    
    private int infoSize = 0;
    
    // ��ǰ�˵�
    private String curMenuId;
    
    // ������Ϣ
    private String errormessage = "";
    
    public String qryInit()
    {
        return SUCCESS;
    }
    
    public String qryMonthlyReturnInfo()
    {
        
        mrInfoList = new Vector<MonthlyReturnInfoVO>();
        
        Map<String, String> inMap = new HashMap<String, String>();
        
        getInMap(inMap);
        
        inMap.put("stardate", startDate.concat("000000"));
        
        inMap.put("enddate", endDate.concat("235959"));
        
        Map result = queryBean.qryMonthlyReturnInfo(inMap);
        if ("1".equals(result.get("status")))
        {
            Vector retData = (Vector)(result.get("returnObj"));
            if (null != retData)
            {
                CTagSet tagSet = (CTagSet)retData.get(0);
                if ("0".equals(tagSet.get("flag")) && retData.size() == 2)
                {
                    CRSet crset = (CRSet)(retData.get(1));
                    MonthlyReturnInfoVO monthlyReturnInfo = null;
                    
                    for (int i = 0; i < crset.GetRowCount(); i++)
                    {
                        monthlyReturnInfo = new MonthlyReturnInfoVO();
                        monthlyReturnInfo.setBillCycle(crset.GetValue(i, 0));
                        monthlyReturnInfo.setStoreFlowamt(CommonUtil.fenToYuan(crset.GetValue(i, 1)));
                        monthlyReturnInfo.setStoreBalance(CommonUtil.fenToYuan(crset.GetValue(i, 2)));
                        monthlyReturnInfo.setGiftFlowamt(CommonUtil.fenToYuan(crset.GetValue(i, 3)));
                        monthlyReturnInfo.setGiftBalance(CommonUtil.fenToYuan(crset.GetValue(i, 4)));
                        mrInfoList.add(monthlyReturnInfo);
                    }
                }
            }
            infoSize = mrInfoList.size();
            this.createRecLog(ConstantsHub.MONTHLY_RETURN_INFO, "0", "0", "1", (String)result.get("returnMsg"));
            return SUCCESS;
        }
        else
        {
            String errMsg = (String)result.get("returnMsg");
            setErrormessage(errMsg);
            log.error(errMsg);
            this.createRecLog(ConstantsHub.MONTHLY_RETURN_INFO, "0", "0", "0", errMsg);
            return ERROR;
        }
    }
    
    private void getInMap(Map<String, String> inMap)
    {
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ���ò���Աid
        inMap.put("curOper", terminalInfoPO.getOperid());
        
        // �����ն˻�id
        inMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // ���ÿͻ��Ӵ�id
        inMap.put("touchoid", customer.getContactId());
        
        // ���õ�ǰ�˵�
        inMap.put("curMenuId", curMenuId == null ? "" : curMenuId);
        
        // ���ÿͻ��ֻ���
        inMap.put("telnumber", customer.getServNumber());
        inMap.put("telnum", customer.getServNumber());
        
        // ����
        inMap.put("region", terminalInfoPO.getRegion());
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	/**
     * @return ���� errormessage
     */
    public String getErrormessage()
    {
        return errormessage;
    }
    
    /**
     * @param ��errormessage���и�ֵ
     */
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    /**
     * @return ���� queryBean
     */
    public QueryBean getQueryBean()
    {
        return queryBean;
    }
    
    /**
     * @param ��queryBean���и�ֵ
     */
    public void setQueryBean(QueryBean queryBean)
    {
        this.queryBean = queryBean;
    }
    
    /**
     * @return ���� startDate
     */
    public String getStartDate()
    {
        return startDate;
    }
    
    /**
     * @param ��startDate���и�ֵ
     */
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    
    /**
     * @return ���� endDate
     */
    public String getEndDate()
    {
        return endDate;
    }
    
    /**
     * @param ��endDate���и�ֵ
     */
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
    /**
     * @return ���� mrInfoList
     */
    public Vector<MonthlyReturnInfoVO> getMrInfoList()
    {
        return mrInfoList;
    }
    
    /**
     * @param ��mrInfoList���и�ֵ
     */
    public void setMrInfoList(Vector<MonthlyReturnInfoVO> mrInfoList)
    {
        this.mrInfoList = mrInfoList;
    }
    
    /**
     * @return ���� serialversionuid
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    /**
     * @return ���� infoSize
     */
    public int getInfoSize()
    {
        return infoSize;
    }

    /**
     * @param ��infoSize���и�ֵ
     */
    public void setInfoSize(int infoSize)
    {
        this.infoSize = infoSize;
    }
    
}
