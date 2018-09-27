/*
 * 文 件 名:  INonlocalChargeService.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <山东异地缴费service>
 * 修 改 人:  jWX216858
 * 修改时间:  Apr 27, 2015
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.charge.service;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;

/**
 * <山东异地缴费service>
 * <功能详细描述>
 * 
 * @author  jWX216858
 * @version  [版本号, Apr 27, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本 OR_SD_201503_949_自助终端新增跨省缴费功能的支撑]
 */
public interface INonlocalChargeService
{
	/**
	 * 客户应缴费用总额查询
	 * 
	 * @param servNumber 手机号码
	 * @param curMenuId 当前菜单
	 * @return
     * @see [类、类#方法、类#成员]
	 */
	public CardChargeLogVO qryfeeChargeAccount(String servNumber, String curMenuId);
	
	/**
	 * 增加缴费日志
	 * 
	 * @param chargeLogVO 缴费信息
	 * @param curMenuId 当前菜单id
	 * @param unionRetCode 银联返回错误码
	 * @return
     * @see [类、类#方法、类#成员]
	 */
	public CardChargeLogVO chargeCommit(CardChargeLogVO chargeLogVO, String curMenuId, String unionRetCode);
	
}
