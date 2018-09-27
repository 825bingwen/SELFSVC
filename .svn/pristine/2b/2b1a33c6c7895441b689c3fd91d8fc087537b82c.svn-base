package com.customize.hub.selfsvc.common.service;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;

public interface FeeService 
{
	/**
	 * 交费前记录日志，现金交费和银联卡交费都可调用此方法
	 * @param selfCardPayLogVO
	 * @param recType
	 * @param posNum
	 * @param batchNumBeforeKoukuan
	 * @see
	 * @return selfCardPayLogVO
	 */ 
	CardChargeLogVO addChargeLog(CardChargeLogVO selfCardPayLogVO, String recType);
	
	/**
	 * 现金交费投币后，更新交费日志
	 * @param selfCardPayLogVO
	 */
	void updateCashChargeLog(CardChargeLogVO selfCardPayLogVO);
	
	/**
	 * 现金和银联卡交费完成后更新日志
	 * @param selfCardPayLogVO
	 */
	void updateChargeResult(CardChargeLogVO selfCardPayLogVO);
	
	/**
	 * 更新现金交费异常的日志
	 * @param selfCardPayLogVO
	 * @param errormessage
	 * @param recType
	 * @param tMoney
	 */
	void updateCashChargeError(CardChargeLogVO selfCardPayLogVO, String errormessage, 
			String recType, String tMoney);
	
	/**
	 * 更新银联卡交费日志
	 * @param selfCardPayLogVO
	 */
	String updateCardChargeLog(CardChargeLogVO selfCardPayLogVO);
	
	/**
	 * 更新银联卡交费异常的日志
	 * @param selfCardPayLogVO
	 * @param errormessage
	 * @param recType
	 * @param tMoney
	 * @param errPosResCode
	 * @param errorType
	 * @see
	 */
	void updateCardChargeError(CardChargeLogVO selfCardPayLogVO, String errormessage, 
			String recType, String tMoney, String errPosResCode, String errorType);
}
