/*
 * 文件名：ChargeHistoryAction.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-12-17
 * 修改内容：新增，缴费历史记录查询
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
 * 六个月内的缴费历史记录查询
 * 
 * @author g00140516
 * @version 1.0，2010-12-17
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
    
    // 开始时间
    private String startDate = "";
    
    // 结束时间
    private String endDate = "";

    /**
     * 查询缴费历史记录，如果是宁夏，由用户输入开始日期、结束日期；如果是其它省份，默认查询六个月内的
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
        
        //宁夏的缴费历史查询，由用户输入查询开始时间、结束时间
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
            // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            this.getRequest().setAttribute("backStep", "-1");
            // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            
            // 对用户输入的开始时间、结束时间进行格式判断
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
            String errMsg = getErrMsg();
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
            
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
        
        // 六个月内的全部记录
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
                this.getRequest().setAttribute("errormessage", "未查询到交费历史记录");
                
                this.createRecLog(Constants.BUSITYPE_FEEHISTORY, "0", "0", "1", "交费历史查询结束，未查找到六个月内的交费记录");
                
                logger.error("交费历史查询，未查找到" + startDate + "至" + endDate + "之间的记录");
            }
            else
            {
                String resultMsg = getConvertMsg("未查询到缴费历史记录", "zz_04_16_000001", null, null);
                
                this.getRequest().setAttribute("errormessage", resultMsg);
                
                this.createRecLog(Constants.BUSITYPE_FEEHISTORY, "0", "0", "1", resultMsg);
                
                logger.error("缴费历史查询，未查找到" + startDate + "至" + endDate + "之间的记录");
            }
        }
        else
        {
            if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
            {
                this.createRecLog(Constants.BUSITYPE_FEEHISTORY, "0", "0", "0", "交费历史查询成功");
            }
            else
            {
                this.createRecLog(Constants.BUSITYPE_FEEHISTORY, "0", "0", "0", "缴费历史查询成功");
            }
            
            this.getRequest().setAttribute("total", String.valueOf(allRecords.size()));
            
            // 记录分页显示
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
     * 对用户输入的开始时间、结束时间进行格式判断
     * @param startDate
     * @param endDate
     * @return 错误语句
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
     */
	private String getErrMsg()
	{
		String errMsg = "";
		
		String pattern = "\\d{8}";
		String currMonth = CommonUtil.getLastMonth("yyyyMMdd", 0);
		
		if (startDate == null || "".equals(startDate) || !Pattern.matches(pattern, startDate)
		        || endDate == null || "".equals(endDate) || !Pattern.matches(pattern, endDate))
		{
		    errMsg = "缴费历史查询，开始时间" + startDate + "或结束时间" + endDate + "格式错误，正确格式：yyyyMMdd，如20110101";
		}
		else if (startDate.compareTo(currMonth) > 0 || endDate.compareTo(currMonth) > 0)
		{
		    errMsg = "缴费历史查询，开始时间" + startDate + "和结束时间" + endDate + "均不能大于当前月" + currMonth;
		}
		else if (startDate.compareTo(endDate) > 0)
		{
		    errMsg = "缴费历史查询，开始时间" + startDate + "不能大于结束时间" + endDate;
		}
		return errMsg;
	}
    
    /**
     * 记录分页显示
     * 
     * @param allRecords，全部记录
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
