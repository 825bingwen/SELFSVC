package com.customize.sd.selfsvc.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * <代理商空中充值账户续费接口调用>
 * <功能详细描述>
 * 
 * @author  sWX219697
 * @version  [版本号, Jun 5, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark create by sWX219697 OR_huawei_201404_1118 山东_[自助终端]_支撑代理商空中充值续费
 */
public class AgentChargeBean extends BaseBeanSDImpl
{

	/**
	 * <查询代理商信息>
	 * <功能详细描述>
	 * @param termInfo 终端机信息
	 * @param servnumber 代理商手机号码
	 * @param curMenuId 当前菜单编号
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String,String> qryAgentInfo(TerminalInfoPO termInfo, String telnum, String curMenuId)
	{
		Map<String, String> map = new HashMap<String,String>();
		
		//操作员id
		map.put("operid", termInfo.getOperid());
		
		//终端机id
		map.put("atsvNum", termInfo.getTermid());
		
		//代理商手机号码
		map.put("telnum", telnum);
		
		//统一接触流水
		map.put("touchoid", "");
		
		//当前菜单id
		map.put("curMenuid", curMenuId);
		
		ReturnWrap rw = this.getSelfSvcCallSD().qryAgentInfo(map);
		
		Map<String,String> returnMap = new HashMap<String,String>();
		
		//返回成功
		if(null != rw && SSReturnCode.SUCCESS == rw.getStatus())
		{
            Vector<Object> v = (Vector)rw.getReturnObject();
            
            CTagSet tagSet = (CTagSet)v.get(0);
            CRSet crset = (CRSet)v.get(1);
            
            //代理商组织机构编码
            returnMap.put("orgId", tagSet.GetValue("orgid"));
            
            //代理商名称
            returnMap.put("agentName", tagSet.GetValue("orgname"));
            
            //代理商资金账户编码
            returnMap.put("agentAccount", crset.GetValue(0, 0));
            
        	//代理商科目编码
            returnMap.put("subjectId", crset.GetValue(0, 2));
        	
        	//账户余额
            returnMap.put("balance", crset.GetValue(0, 5));
            
            returnMap.put("retcode", String.valueOf(rw.getStatus()));
            
		}
		
		//封装失败的消息
		else
		{
			returnMap.put("retcode", String.valueOf(SSReturnCode.ERROR));
			
			if(null != rw)
			{
			    returnMap.put("returnMsg", rw.getReturnMsg());
			}	    
		}
		
		 return returnMap;
	}

	/**
	 * <充值前校验用户输入的金额>
	 * <功能详细描述>
	 * @param termInfo 终端机信息
	 * @param servnumber 手机号码
	 * @param curMenuId 菜单
	 * @param agentAccount 代理商账户
	 * @param tMoney 充值金额 分
	 * @param subjectId 科目编码
	 * @return 1 校验成功，可以充值
	 * @see [类、类#方法、类#成员]
	 * @remark create by sWX219697 2014-8-23 10:43:09 OR_huawei_201408_657_自助终端代理商资金账户充值功能优化
	 */
	public String checkBeforeCharge(TerminalInfoPO termInfo, String servRegion, String curMenuId,
			String agentAccount,String tMoney, String subjectId, String orgId)
	{
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_REGION, servRegion);
        
        ReturnWrap rw = this.getSelfSvcCallSD().checkBeforeAgentCharge(msgHeader, orgId, agentAccount, 
        		subjectId, tMoney);
        
        String returnMsg = "";
        
		//校验通过
		if(SSReturnCode.SUCCESS == rw.getStatus())
		{
			returnMsg = "1";
		}
		
		//校验失败
		else
		{
			returnMsg = rw.getReturnMsg();
			returnMsg = StringUtils.isEmpty(returnMsg) ? "充值金额校验失败，请稍后再试" : returnMsg;
    		
    		//防止错误信息过长，弹出框变形
			returnMsg = returnMsg.length() > 50 ? returnMsg.substring(0,50)+"..." : returnMsg;
			returnMsg = "0~~"+ returnMsg;
		}
		
		return returnMsg;
	}

	/**
	 * <代理商交费前记录>
	 * <功能详细描述>
	 * @param termInfo 终端机信息
	 * @param servnumber 代理商手机号码
	 * @param curMenuId 当前菜单
	 * @param orgId 代理商组织机构编码
	 * @param agentAccount 代理商账户
	 * @param tMoney 充值金额
	 * @param subjectId 科目编码
	 * @return 
	 * @see [类、类#方法、类#成员]
	 */
	public String beforeAgentCharge(TerminalInfoPO termInfo, String servnumber, String curMenuId, String orgId 
			,String agentAccount,String tMoney, String subjectId)
	{
		Map<String, String> map = new HashMap<String,String>();
		
		//操作员id
		map.put("operid", termInfo.getOperid());
		
		//终端机id
		map.put("atsvNum", termInfo.getTermid());
		
		//统一接触流水
		map.put("touchoid", "");
		
		//当前菜单id
		map.put("curMenuid", curMenuId);
		
		//代理商组织结构编码
		map.put("orgId", orgId);
		
		//代理商账户编码
		map.put("fundacctid", agentAccount);
		
		//充值金额 单位 分
		map.put("amount", tMoney);
		
		//受理类型
		map.put("accept_type", "selfsvc_agent_charge");
		
		//银行号 银联133
		map.put("bank_no", "133");
		
		//科目编码
		map.put("subjectid", subjectId);
		
		//手机号码
		map.put("msisdn", servnumber);
		
		//交费日期
		map.put("pay_date", CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
		
		//受理渠道
		map.put("channel", "bsacAtsv");
		
		ReturnWrap rw = this.getSelfSvcCallSD().beforeAgentCharge(map);
		
		//缴费前流水号
		String beforeChargeNo = null;
		
		//调用成功，返回交费前流水号
		if(null != rw && SSReturnCode.SUCCESS == rw.getStatus())
		{
			CTagSet tagSet = (CTagSet)rw.getReturnObject();
			
			beforeChargeNo = tagSet.GetValue("orderno");
		}
		
		return beforeChargeNo;
	}
	
	/**
	 * <代理商充值>
	 * <银联扣款成功后调用接口为代理商充值>
	 * @param termInfo 终端机信息
	 * @param servnumber 代理商手机号码
	 * @param curMenuId 当前菜单编号
	 * @param tMoney 实际扣款金额
	 * @param beforeChargeNo
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String,Object> agentCharge(TerminalInfoPO termInfo, String servnumber, 
			String curMenuId, String tMoney,String beforeChargeNo)
	{
        Map<String, String> map = new HashMap<String,String>();
		
		//操作员id
		map.put("operid", termInfo.getOperid());
		
		//终端机id
		map.put("atsvNum", termInfo.getTermid());
		
		//统一接触流水
		map.put("touchoid", "");
		
		//代理商手机号码
		map.put("telnum", servnumber);
		
		//当前菜单id
		map.put("curMenuid", curMenuId);
		
		//充值金额 单位 元
		map.put("amount", tMoney);
		
		//缴费前流水号
		map.put("bank_nbr", beforeChargeNo);
		
		//银行号，银联 133
		map.put("bank_no", "133");
		
		//交费时间
		map.put("pay_date", CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
		
		//受理渠道编码
		map.put("channel", "bsacAtsv");
		
		//以下参数传空
		map.put("settle_date", "");
		map.put("print_flag", "");
		map.put("bsite", "");
		map.put("boperid", "");
		
		ReturnWrap rw = this.getSelfSvcCallSD().agentCharge(map);
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		//接口调用成功
		if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
		{
	        returnMap.put("retcode", SSReturnCode.SUCCESS);
	        return returnMap;
		}
		
		//封装失败的消息
		else if (null != rw)
		{
			 returnMap.put("retcode", SSReturnCode.ERROR);
			 returnMap.put("returnMsg", rw.getReturnMsg());
		     return returnMap;
		}
		
		return null;
	}
	
	
}
