/*
 * 文 件 名:  TelProdDiyAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <自选套餐>
 * 创 建 人: jWX216858
 * 创建时间: 2014-10-10
 */
package com.customize.sd.selfsvc.bean;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 自选套餐action
 * @author  jWX216858
 * @version  [版本号, 2014-10-10]
 * @see  
 * @since 
 */
public class TelProdDiyBean extends BaseBeanSDImpl
{
	/**
	 * 语音通话受理
	 * @param termInfo 终端机信息
	 * @param customer 客户信息
	 * @param curMenuId 菜单id
	 * @param nCode nCode
	 * @return
	 * @remark create by jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_山东_关于自助终端产品变更功能添加4G自选套餐以及修改GPRS和4G互斥的功能（全业务流程优化）
	 */
	public ReturnWrap voiceCallRec(TerminalInfoPO termInfo, NserCustomerSimp customer,
			String curMenuId, String nCode)
	{
		// 组装报文头信息
		MsgHeaderPO header = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
				customer.getContactId(), "1", customer.getServNumber());
		
		return getSelfSvcCallSD().voiceCallRec(header, nCode);
	}
	
	/**
	 * 上网流量受理
	 * @param termInfo 终端机信息
	 * @param customer 客户信息
	 * @param productset 开通增值产品串(产品包,增值产品,优惠;产品包,增值产品,优惠;)
	 * @param menuId
	 * @return
	 * @remark create by jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_山东_关于自助终端产品变更功能添加4G自选套餐以及修改GPRS和4G互斥的功能（全业务流程优化）
	 */
	public ReturnWrap gprsWlanRec(TerminalInfoPO termInfo, NserCustomerSimp customer,
			String productset, String menuId)
	{
		// 组装报文头信息
		MsgHeaderPO header = new MsgHeaderPO(menuId, termInfo.getOperid(), termInfo.getTermid(),
				customer.getContactId(), "1", customer.getServNumber());
		
		return getSelfSvcCallSD().gprsWlanRec(header, productset);
	}
}