/*
 * 文 件 名:  InvoicePrintBean.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  发票打印Bean
 * 修 改 人: zKF69263
 * 修改时间:  2014-5-9
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.bean;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.customize.sd.selfsvc.invoice.model.InvoicePrintPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 发票打印Bean
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-5-9]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class InvoicePrintBean extends BaseBeanSDImpl
{   
    /** 
     * 查询要打印发票的记录信息
     * 
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @param month 查询月份
     * @return Map
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 OR_huawei_201404_1109 营业厅全业务流程优化-业务分流(自助终端)_补打缴费发票
     */
    @SuppressWarnings("unchecked")
    public Map invoiceList(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId,String month)
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
        
        // 设置当前菜单
        paramMap.put("menuID", curMenuId);
        
        // 设置查询开始时间YYYYMMDDHH24MISS
        paramMap.put("startTime", month + "01000000");
        
        // 计算选择月的最后一天
        Calendar cal = new GregorianCalendar(Integer.parseInt(month.substring(0, 4)), 
            Integer.parseInt(month.substring(4)) - 1, 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        
        // 设置查询结束时间YYYYMMDDHH24MISS
        paramMap.put("endTime", DateFormatUtils.format(cal.getTime(), "yyyyMMdd") + "235959");
        
        ReturnWrap rw = getSelfSvcCallSD().invoiceList(paramMap);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", rw.getReturnObject());
            
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        else if (rw != null)
        {
            Map map = new HashMap();
            
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        
        return null;
    }
    
    /** 
     * 打印发票信息
     * 
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单Id
     * @param servnumber 办理业务手机号码
     * @param invoicePrint 
     * @return Map
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 OR_huawei_201404_1109 营业厅全业务流程优化-业务分流(自助终端)_补打缴费发票
     */
    @SuppressWarnings("unchecked")
    public Map invoiceData(TerminalInfoPO termInfo, String curMenuId, String servnumber, InvoicePrintPO invoicePrint)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        paramMap.put("menuID", curMenuId);
        paramMap.put("telnumber", servnumber);
        paramMap.put("recoid", invoicePrint.getRecoid());
        paramMap.put("billCycle", invoicePrint.getBillCycle());
        paramMap.put("acctId", invoicePrint.getAcctId());
        paramMap.put("invType", invoicePrint.getInvType());
        paramMap.put("touchOID", "");
        
        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
        //读取开关配置，是否启用电子发票,true为开启
        String isElectronInvoice = CommonUtil.getDictNameById(termInfo.getRegion(), "SH_ELECTRON_INVOICE");
        if("true".equals(isElectronInvoice))
        {
            //是否开具电子发票1是 0否
            paramMap.put("eleinvType", "1");
        }
        else
        {
            //是否开具电子发票1是 0否
            paramMap.put("eleinvType", "0");
        }
        //推送方式 1邮箱
        paramMap.put("pushType", "1");
        //推送信息 电子邮件地址
        paramMap.put("receiveMode", servnumber+"@139.com");
        //add end by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
        
        ReturnWrap rw = this.getSelfSvcCallSD().invoiceData(paramMap);
        
        if (rw != null)
        {
            Map map = new HashMap();
            
            if (rw.getStatus() == SSReturnCode.SUCCESS) {
                
                // 设置返回结果
                map.put("returnObj", rw.getReturnObject());
            }
            
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        
        return null;
    }
    
    /**
     * 月结发票的账期接口查询
     * 
     * @param customerSimp，用户信息
     * @param terminalInfo，终端机信息
     * @param month，查询月份
     * @param CurMenuid，当前菜单
     * @param billcycle 账期
     * 
     * @return 账期信息
     * @see
     * @remark add wWX217192 2014/05/04 OR_huawei_201404_1108
     */
	@SuppressWarnings("unchecked")
	public ReturnWrap qryBillCycle(String telNum, TerminalInfoPO terminalInfo, String curMenuId, String billCycle)
	{
		Map map = new HashMap();
		map.put("servnum", telNum);
		map.put("cycle", billCycle);
		map.put("menuID", curMenuId);
		map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallSD().qryBillCycle(map);
		
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return rw;
        }
        
        return null;
	}
	
	/**
	 * 月结发票打印数据的查询接口
	 * @param telNum，手机号码
	 * @param terminalInfo，终端机信息
	 * @param cycle，账期
	 * @param startdate，账期开始时间
	 * @param enddate，账期结束时间
	 * @param acctid，主账号
	 * @param curMenuId，当前菜单
	 * 
	 * @return 月结发票数据
	 * @see
	 * @remark add wWX217192 2014/05/04 OR_huawei_201404_1108
	 */
	@SuppressWarnings("unchecked")
	public ReturnWrap qryMonthInvoice(String telNum, TerminalInfoPO terminalInfo, String cycle, String startdate, String enddate, String acctid, String curMenuId)
	{
		Map map = new HashMap();
		map.put("menuID", curMenuId);
		map.put("touchOID", "");
		map.put("operID", terminalInfo.getOperid());
		map.put("termID", terminalInfo.getTermid());
		map.put("servnum", telNum);
		map.put("acctid", acctid);
		map.put("billcycle", cycle);
		map.put("startdate", startdate);
		map.put("enddate", enddate);
		
        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
        //读取开关配置，是否启用电子发票,true为开启
        String isElectronInvoice = CommonUtil.getDictNameById(terminalInfo.getRegion(), "SH_ELECTRON_INVOICE");
        if("true".equals(isElectronInvoice))
        {
            //是否开具电子发票1是 0否
            map.put("eleinvType", "1");
        }
        else
        {
            //是否开具电子发票1是 0否
            map.put("eleinvType", "0");
        }
        //推送方式 1邮箱
        map.put("pushType", "1");
        //推送信息 电子邮件地址
        map.put("receiveMode", telNum+"@139.com");
        //add end by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
		
		ReturnWrap rw = this.getSelfSvcCallSD().qryMonthInvoice(map);
		
		// 调用接口成功，无论接口返回的信息是否为正确的月结发票信息，都将其返回前台
		if(rw != null)
		{
			return rw;
		}
		
		return null;
	}
}
