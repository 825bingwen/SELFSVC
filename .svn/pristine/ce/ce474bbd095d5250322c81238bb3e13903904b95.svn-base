/*
 * 文 件 名:  IFeeChargeServiceSD.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <山东缴费日志记录service>
 * 修 改 人:  jWX216858
 * 修改时间:  May 22, 2015
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.common.service;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;

/**
 * <山东缴费日志记录service>
 * <功能详细描述>
 * 
 * @author  jWX216858
 * @version  [版本号, May 22, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本 OR_SD_201503_949_自助终端新增跨省缴费功能的支撑]
 */
public interface IFeeServiceSD 
{
	/**
	 * 增加缴费日志
	 * 
	 * @param chargeLogVO 缴费信息
	 * @param chargeLogOID 缴费日志流水
	 * @param recType 交易类型
	 * @param servRegion 手机号码归属地
	 * @return
     * @see [类、类#方法、类#成员]
	 */
	public CardChargeLogVO addChargeLog(CardChargeLogVO chargeLogVO, String chargeLogOID, String recType, String servRegion);
	
	/**
	 * 银联扣款成功更新缴费日志
	 * 
	 * @param chargeLogVO 缴费信息
	 * @return
     * @see [类、类#方法、类#成员]
	 */
	public void updateCardChargeLog(CardChargeLogVO chargeLogVO);
	
	/**
	 * 银更新缴费日志
	 * 
	 * @param chargeLogVO 缴费信息
	 * @param message 描述信息
	 * @param unionRetCode 银联
	 * @param status 缴费状态
	 * @return
     * @see [类、类#方法、类#成员]
	 */
	public void updateChargeLog(CardChargeLogVO chargeLogVO, String message, String unionRetCode, String status);
}
