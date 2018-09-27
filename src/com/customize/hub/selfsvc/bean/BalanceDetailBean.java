/*
 * 文 件 名:  BalanceDetailBean.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wWX217192
 * 修改时间:  2016-3-31
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.customize.hub.selfsvc.balanceDetail.model.BalanceDetailPO;
import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wWX217192
 * @version  [版本号, 2016-3-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BalanceDetailBean extends BaseBeanHubImpl 
{
	/**
	 * <解析余额明细查询接口的返回报文>
	 * <功能详细描述>
	 * @param terminalInfoPO
	 * @param telNum
	 * @param curMenuId
	 * @return
	 * @see [类、类#方法、类#成员]、
	 * @remark create by wWX217192 2016-04-01 OR_HUB_201602_493
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> showBalanceDetail(TerminalInfoPO terminalInfoPO, String telNum, String curMenuId)
	{
		// 组装报文头信息
		MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
                "",MsgHeaderPO.ROUTETYPE_TELNUM, telNum);
		
		ReturnWrap rw = super.getSelfSvcCallHub().showBalanceDetail(msgHeader);
		
		List<BalanceDetailPO> balanceList = new ArrayList<BalanceDetailPO>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(SSReturnCode.SUCCESS == rw.getStatus())
		{
			Vector v = null;
			CTagSet tagSet = null;
			
			// 取返回对象信息
			if(rw.getReturnObject() instanceof CTagSet)
			{
				tagSet = (CTagSet)rw.getReturnObject();
			}
			else
			{
				v = (Vector)rw.getReturnObject();
				tagSet = (CTagSet) v.get(0);
				
				CRSet rSet = (CRSet)v.get(1);
				
				BalanceDetailPO po = null;
				
				for (int i = 0; i < rSet.GetRowCount(); i++)
				{
					po = new BalanceDetailPO();
					
					// 余额大类
					po.setSubjectClass(rSet.GetValue(i, 0));
					
					// 科目类别
					po.setSubjectType(rSet.GetValue(i, 1));
					
					// 可消费范围
					po.setRange(rSet.GetValue(i, 2));
					
					// 未核减已产生消费的余额
					if(!rSet.GetValue(i, 3).isEmpty())
					{
						// modify begin wWX217192 2016-06-03 OR_HUB_201602_493_湖北_【2016年十大需求_BOSS】关于客户账本余额查询与管理需求（钱立进）
						po.setConsume(CommonUtil.fenToYuan(String.valueOf((rSet.GetValue(i, 3)))));
					}
					
					// 有效期
					po.setExpiryDate(rSet.GetValue(i, 4));
					
					// 专款可销的费用
					if(!rSet.GetValue(i, 5).isEmpty())
					{
						po.setFunds(CommonUtil.fenToYuan(String.valueOf((rSet.GetValue(i, 5)))));
						// modify end wWX217192 2016-06-03 OR_HUB_201602_493_湖北_【2016年十大需求_BOSS】关于客户账本余额查询与管理需求（钱立进）
					}
					
					balanceList.add(po);
				}
				
				// 余额明细
				map.put("balanceList", balanceList);
			}
			
			// 账单总费用
			map.put("CURFEE", tagSet.get("curFee"));
			
			// 当前余额
			map.put("LEFTBALANCE", tagSet.get("leftBalance"));
			
			// 信用度
			map.put("CREDIT", tagSet.get("credit"));
			
			
		}
		else
		{
			// 接口调用详细结果描述
			map.put("returnMsg", rw.getReturnMsg());
		}
		
		return map;
	}
}
