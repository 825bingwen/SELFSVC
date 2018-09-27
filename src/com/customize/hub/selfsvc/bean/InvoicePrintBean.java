/*
 * 文 件 名:  InvoicePrintBean.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人: YKF38827
 * 修改时间:  Mar 13, 2012
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.customize.hub.selfsvc.invoice.model.CyclePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  YKF38827
 * @version  [NGESHOP V1.0, Mar 13, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class InvoicePrintBean extends BaseBeanHubImpl
{   
    
    
    /**
     * <查询要打印发票的记录信息>
     * <功能详细描述>
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public Map invoiceList(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId)
    {
        Map paramMap = new HashMap();
        
        // 设置操作员id
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnum", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        // 渠道
        paramMap.put("accesstype", customer.getContactId()); 
        
        // 设置当前菜单
        paramMap.put("menuID", curMenuId);
        
        ReturnWrap rw = getSelfSvcCallHub().invoiceList(paramMap);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", rw.getReturnObject());
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
    }
    
    /**
     * 向用户发送随机密码短信
     * 
     * @param servNumber，手机号码
     * @param termInfo，终端机信息
     * @param shortMessage，短信内容
     * @param curMenuId，当前菜单
     * @return
     * @see
     */
    @SuppressWarnings("unchecked")
    public boolean sendRandomPwd(String servNumber, TerminalInfoPO termInfo, String shortMessage, String curMenuId)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servNumber);
        paramMap.put("smsContent", shortMessage);
        paramMap.put("priority", "5");
        paramMap.put("menuID", curMenuId);
        paramMap.put("touchOID", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallHub().sendSMS(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * <打印发票信息>
     * <功能详细描述>
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public Map invoiceData(TerminalInfoPO termInfo, String curMenuId, String servnumber, String formnum, String billCycle)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        paramMap.put("menuID", curMenuId);
        paramMap.put("telnumber", servnumber);
        paramMap.put("formnum", formnum);
        paramMap.put("billCycle", billCycle);
        paramMap.put("touchOID", "");
        
        ReturnWrap rw = this.getSelfSvcCallHub().invoiceData(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet v = (CRSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
    }
    
    /**
     * 向用户发送随机密码短信(根据SA_DB_SMTEMPLATE中配置的模板编号)
     * 
     * @param termInfo
     * @param smsparam
     * @param servnumber
     * @param templateno
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
     */
    @SuppressWarnings("unchecked")
    public boolean sendRandomPwdHub(TerminalInfoPO termInfo, String smsparam, String servnumber, String templateno, String curMenuId)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servnumber);
        paramMap.put("smsparam", smsparam);
        paramMap.put("templateno", templateno);
        paramMap.put("priority", "5");
        paramMap.put("menuID", curMenuId);
        paramMap.put("touchOID", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallHub().sendSmsHub(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
    
    // add begin jWX216858 2014/06/17 OR_HUB_201405_829_湖北_[营改增]自助终端提供增值税月结发票打印
    /**
	 * 获取打印发票数据
	 * 
	 * @param termInfo 终端机信息
	 * @param customer 客户信息
	 * @param curMenuId 当前菜单
	 * @param cycle 账期
	 * @param cyclepo 账期信息
	 * @return
	 * @remark create jWX216858 2014/06/17 OR_HUB_201405_829_湖北_[营改增]自助终端提供增值税月结发票打印
	 */
	public ReturnWrap invoiceData(TerminalInfoPO termInfo,NserCustomerSimp customer, String curMenuId, CyclePO cyclepo)
	{
		// 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
		
		return this.getSelfSvcCallHub().getMonInvoiceData(msgHeader, cyclepo);
	}
	
	/**
     * 月结发票的账期接口查询
     * 
     * @param customerSimp，用户信息
     * @param terminalInfo，终端机信息
     * @param month，查询月份
     * @param curMenuId，当前菜单
     * @param billcycle 账期
     * 
     * @return 账期信息
     * @see
     * @remark create jWX216858 2014/06/17 OR_HUB_201405_829_湖北_[营改增]自助终端提供增值税月结发票打印
     */
	public ReturnWrap qryBillCycle(String telNum, TerminalInfoPO termInfo, String curMenuId, String billCycle)
	{
		// 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, telNum);

        ReturnWrap rw = this.getSelfSvcCallHub().qryBillCycle(msgHeader, billCycle);
		
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return rw;
        }
        
        return null;
	}
	// add end jWX216858 2014/06/17 OR_HUB_201405_829_湖北_[营改增]自助终端提供增值税月结发票打印
}
