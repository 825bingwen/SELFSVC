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
 * 新增分月返还到账情况查询功能 分月返还到账情况查询 Add by LiFeng [XQ[2011]_06_020]电子渠道分月返还到账情况查询需求【重点需求】
 * 
 * @author LiFeng
 * @version [版本号, 2011-9-13]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MonthlyReturnAction extends BaseAction
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    // modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    //private static final Logger log = Logger.getLogger(MonthlyReturnAction.class);
    private static Log log = LogFactory.getLog(MonthlyReturnAction.class);
    // modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    
    private QueryBean queryBean;
    
    private String startDate;
    
    private String endDate;
    
    private Vector<MonthlyReturnInfoVO> mrInfoList;
    
    private int infoSize = 0;
    
    // 当前菜单
    private String curMenuId;
    
    // 错误信息
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
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 设置操作员id
        inMap.put("curOper", terminalInfoPO.getOperid());
        
        // 设置终端机id
        inMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // 设置客户接触id
        inMap.put("touchoid", customer.getContactId());
        
        // 设置当前菜单
        inMap.put("curMenuId", curMenuId == null ? "" : curMenuId);
        
        // 设置客户手机号
        inMap.put("telnumber", customer.getServNumber());
        inMap.put("telnum", customer.getServNumber());
        
        // 地区
        inMap.put("region", terminalInfoPO.getRegion());
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	/**
     * @return 返回 errormessage
     */
    public String getErrormessage()
    {
        return errormessage;
    }
    
    /**
     * @param 对errormessage进行赋值
     */
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    /**
     * @return 返回 queryBean
     */
    public QueryBean getQueryBean()
    {
        return queryBean;
    }
    
    /**
     * @param 对queryBean进行赋值
     */
    public void setQueryBean(QueryBean queryBean)
    {
        this.queryBean = queryBean;
    }
    
    /**
     * @return 返回 startDate
     */
    public String getStartDate()
    {
        return startDate;
    }
    
    /**
     * @param 对startDate进行赋值
     */
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    
    /**
     * @return 返回 endDate
     */
    public String getEndDate()
    {
        return endDate;
    }
    
    /**
     * @param 对endDate进行赋值
     */
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
    /**
     * @return 返回 mrInfoList
     */
    public Vector<MonthlyReturnInfoVO> getMrInfoList()
    {
        return mrInfoList;
    }
    
    /**
     * @param 对mrInfoList进行赋值
     */
    public void setMrInfoList(Vector<MonthlyReturnInfoVO> mrInfoList)
    {
        this.mrInfoList = mrInfoList;
    }
    
    /**
     * @return 返回 serialversionuid
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    /**
     * @return 返回 infoSize
     */
    public int getInfoSize()
    {
        return infoSize;
    }

    /**
     * @param 对infoSize进行赋值
     */
    public void setInfoSize(int infoSize)
    {
        this.infoSize = infoSize;
    }
    
}
