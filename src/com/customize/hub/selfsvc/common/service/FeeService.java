package com.customize.hub.selfsvc.common.service;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;

public interface FeeService 
{
	/**
	 * ����ǰ��¼��־���ֽ𽻷Ѻ����������Ѷ��ɵ��ô˷���
	 * @param selfCardPayLogVO
	 * @param recType
	 * @param posNum
	 * @param batchNumBeforeKoukuan
	 * @see
	 * @return selfCardPayLogVO
	 */ 
	CardChargeLogVO addChargeLog(CardChargeLogVO selfCardPayLogVO, String recType);
	
	/**
	 * �ֽ𽻷�Ͷ�Һ󣬸��½�����־
	 * @param selfCardPayLogVO
	 */
	void updateCashChargeLog(CardChargeLogVO selfCardPayLogVO);
	
	/**
	 * �ֽ��������������ɺ������־
	 * @param selfCardPayLogVO
	 */
	void updateChargeResult(CardChargeLogVO selfCardPayLogVO);
	
	/**
	 * �����ֽ𽻷��쳣����־
	 * @param selfCardPayLogVO
	 * @param errormessage
	 * @param recType
	 * @param tMoney
	 */
	void updateCashChargeError(CardChargeLogVO selfCardPayLogVO, String errormessage, 
			String recType, String tMoney);
	
	/**
	 * ����������������־
	 * @param selfCardPayLogVO
	 */
	String updateCardChargeLog(CardChargeLogVO selfCardPayLogVO);
	
	/**
	 * ���������������쳣����־
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
