/*
 * 文件名：MonthFeeBean.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：月账单查询Bean
 * 修改人：g00140516
 * 修改时间：2010-12-8
 * 修改内容：新增
 * 
 */
package com.customize.sd.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 月账单查询
 * 
 * @author g00140516
 * @version 1.0，2010-12-8
 * @see
 * @since
 */
public class MonthFeeBean extends BaseBeanSDImpl
{
    /**
     * 月账单查询
     * 
     * @param customerSimp，用户信息
     * @param terminalInfo，终端机信息
     * @param month，查询月份
     * @param curMenuId，当前菜单
     * @param billcycle 账期
     * @param verifyCode 校验码，密码验证接口返回
     * 
     * @return 月账单信息
     * @see
     * @remark modify g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
     */
    public ReturnWrap qryMonthBill(String telnum, TerminalInfoPO terminalInfo, String month,
            String curMenuId,String billcycle, String verifyCode)
    {
        Map map = new HashMap();
        map.put("telnumber", telnum);
        map.put("billCycle", month);
        map.put("menuID", curMenuId);
        map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        map.put("verifyCode", verifyCode);
        
        ReturnWrap rw = this.getSelfSvcCallSD().qryMonthBill(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	return rw;
        }
        else 
        {
        	return null;
        }
    }

    /**
     * 查询用户是否已开通手机邮箱
     * @return
     */
    public String qrymailBox(String telnum, TerminalInfoPO terminalInfo,String curMenuId)
    {
    	Map map = new HashMap();
        map.put("telnum", telnum);
        map.put("email", telnum+"@139.com");
        map.put("menuID", curMenuId);
        map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallSD().qrymailBox(map);
        
        String mailFlag = null;
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	CTagSet tagset = (CTagSet) rw.getReturnObject();
        	mailFlag = tagset.GetValue("havemailbox");
        	
        	// modify by lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1 begin
        	if(StringUtils.isBlank(mailFlag))
        	{
        	    mailFlag = tagset.GetValue("ISHAVEMAILBOX");
        	}
        	// modify by lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1 end
        }
        
    	return mailFlag;
    }
    
    /**
     * 开通139免费邮箱
     */
    public String add139Mail(String telnum, TerminalInfoPO terminalInfo,String curMenuId)
    {
    	Map map = new HashMap();
        map.put("telnum", telnum);
        map.put("email", telnum+"@139.com");
        map.put("menuID", curMenuId);
        map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallSD().add139Mail(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	return "1";
        }
        else
        {
        	return "0";
        }
    }
    
    // add begin cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    /**
     * 账单备注查询 <功能详细描述>
     * 
     * @param telnum
     * @param terminalInfo
     * @param month
     * @param CurMenuid
     * @param verifyCode  校验码，密码验证接口返回
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
     */
    public ReturnWrap queryBillAddInfo(String telnum, TerminalInfoPO terminalInfo, String month, String curMenuId, String verifyCode)
    {
        
        String beginTime = "";
        String endTime = "";
        
        String[] days = new String[] {"31", "28", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31"};
        
        String subYear = month.substring(0, 4);
        String subMonth = month.substring(4, 6);
        
        /**
         * 如果是2月，需要计算是不是闰年
         */
        int nMonth = Integer.parseInt(subMonth);
        if (nMonth == 2)
        {
            int nYear = Integer.parseInt(subYear);
            
            // 世纪年，能被400整除，即为闰年。
            // 普通年，能被4整除，即为闰年。
            // 如2000是闰年，但是1900不是
            if ((nYear % 100 == 0 && nYear % 400 == 0) || (nYear % 100 != 0 && nYear % 4 == 0))
            {
                days[2] = "29";
            }
        }
        
        beginTime = subYear + "-" + subMonth + "-01";
        endTime = subYear + "-" + subMonth + "-" + days[nMonth - 1];
        
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 设置操作员id
        paramMap.put("operID", terminalInfo.getOperid());
        
        // 设置终端机id
        paramMap.put("termID", terminalInfo.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnumber", telnum);
        
        // 设置客户接触id
        paramMap.put("touchOID", "");
        
        // 设置当前菜单id
        paramMap.put("menuID", curMenuId);
        
        // 开始时间
        paramMap.put("beginTime", beginTime);
        
        // 结束时间
        paramMap.put("endTime", endTime);
        
        paramMap.put("verifyCode", verifyCode);
        
        // 获取结果
        ReturnWrap rw = this.getSelfSvcCallSD().queryBillAddInfo(paramMap);
        // modify begin cKF48754 2011/11/25 R003C11L10n01 OR_SD_201110_598
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return rw;
        }
        else 
        {
            return null;
        }
        // modify end cKF48754 2011/11/25 R003C11L10n01 OR_SD_201110_598
    }
    // add end cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    
    /**
     * 根据手机号码查询客户信息
     * 
     * @param terminalInfo 终端机信息
     * @param CurMenuid 当前菜单
     * @param telnum 手机号码
     * @param cycle 账期
     * @return 客户信息
     * @see
     */
    public ReturnWrap getCustinfo(TerminalInfoPO terminalInfo, String curMenuId, String telnum, String cycle)
    {
        Map map = new HashMap();
        map.put("menuID", curMenuId);
        map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        map.put("telnum", telnum);// 手机号码
        map.put("cycle", cycle);// 账期
        
        ReturnWrap rw = this.getSelfSvcCallSD().getCustinfo(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	Object obj = rw.getReturnObject();
        	if (obj instanceof Vector)
        	{
        		return rw;
        	}
        }
        
        // 返回
        return null;
        
    }
    
    /**
     * 取账单信息_新版
     * <功能详细描述>
     * @param terminalInfo 终端信息
     * @param CurMenuid 当前菜单
     * @param telnum 手机号码
     * @param acctid 帐户ID，同客户信息查询接口返回的主账号
     * @param subsid 用户ID
     * @param startcycle 开始帐期
     * @param starttime 账期开始时间，格式yyyymmddhh24miss。客户信息查询接口返回的账期开始日期+000000
     * @param endtime 账期结束时间，格式yyyymmddhh24miss。客户信息查询接口返回的账期结束日期+235959
     * @param isunitpayment 是否合并付费
     * @param region 地市信息
     * @param arealist 区域列表，以逗号分割的区域标识。取值如下：
     *        SCORE 积分信息
     *        PKGINFO 通信量信息 
     *        BILLTREND 半年消费趋势图
     *        RECOMMEND 资费推荐
     *        ACCTBALANCE 平衡信息
     *        AGREEMENT 协议款
     *        PRESENT 赠送款
     *        PAYEDBYOTHER他人代付费
     *        PAYEDFOROTHER代他人付费
     *        SPBILL 代收费
     *        BILLINFO 费用详细信息
     *        INOUT 入账明细
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryMonthBill_new(TerminalInfoPO terminalInfo, String curMenuId, String telnum, String acctid, String subsid,
            String startcycle, String starttime, String endtime, String isunitpayment, String region, String arealist, String factory)
    {
        Map map = new HashMap();
        map.put("menuID", curMenuId);
        map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        map.put("telnum", telnum);// 手机号码
        map.put("acctid", acctid);// 帐户ID
        map.put("subsid", subsid);// 用户ID
        map.put("startcycle", startcycle);// 开始帐期
        map.put("starttime", starttime);// 帐期开始时间
        map.put("endtime", endtime);// 帐期结束时间
        map.put("isunitpayment", isunitpayment);// 是否合并付费
        map.put("region", region);// 区域
        map.put("arealist", arealist);// 区域列表
        map.put("factory", factory);// 厂家编码的密文标识，不能为空
        
        
        ReturnWrap rw = this.getSelfSvcCallSD().qryMonthBill_new(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return rw;
        }
        else 
        {
            return null;
        }
    }
    
    /**
     * <账单邮件下发接口>
     * <功能详细描述>
     * @param terminalInfo 终端机信息
     * @param telnum 客户手机号
     * @param cycle 账期
     * @param isunitepayment 是否合并付费
     * @param CurMenuid 当前菜单编号
     * @remark create by sWX219697 2014-04-29 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
     * @return 
     * @see [类、类#方法、类#成员]
     */
    public String sendBillMail(String operID,String termID,String telnum,String cycle,String isunitepayment,String curMenuId)
    {
    	Map<String,String> map = new HashMap<String,String>();
    	
    	//客户手机号码
    	map.put("telnum", telnum);

    	//查询账期应该为YYYYMM
    	String new_cycle = (cycle != null && cycle.length() > 6) ? cycle.substring(0, 6) : cycle;
    	
    	//查询账期
    	map.put("cycle", new_cycle);
    	
    	//是否合并付费 1 合并，0 不合并
    	map.put("isunitepayment", isunitepayment);
    	
    	//菜单id
    	map.put("menuID", "qryBillItemNew");
    	
    	//客户接触id
    	map.put("touchOID", "");
    	
    	//操作员id
    	map.put("operID", operID);
    	
    	//终端机id
    	map.put("termID", termID);
    	
    	ReturnWrap rw = this.getSelfSvcCallSD().sendBillMail(map);

    	if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
    		
    		//发送成功
            return "1";
        }
        else
        {
        	
        	//发送失败
           	return "0";
        }
    	
    	
    }
}
