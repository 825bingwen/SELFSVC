/*
 * �� �� ��:  IFeeChargeServiceSD.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <ɽ���ɷ���־��¼service>
 * �� �� ��:  jWX216858
 * �޸�ʱ��:  May 22, 2015
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.common.service;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;

/**
 * <ɽ���ɷ���־��¼service>
 * <������ϸ����>
 * 
 * @author  jWX216858
 * @version  [�汾��, May 22, 2015]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾 OR_SD_201503_949_�����ն�������ʡ�ɷѹ��ܵ�֧��]
 */
public interface IFeeServiceSD 
{
	/**
	 * ���ӽɷ���־
	 * 
	 * @param chargeLogVO �ɷ���Ϣ
	 * @param chargeLogOID �ɷ���־��ˮ
	 * @param recType ��������
	 * @param servRegion �ֻ����������
	 * @return
     * @see [�ࡢ��#��������#��Ա]
	 */
	public CardChargeLogVO addChargeLog(CardChargeLogVO chargeLogVO, String chargeLogOID, String recType, String servRegion);
	
	/**
	 * �����ۿ�ɹ����½ɷ���־
	 * 
	 * @param chargeLogVO �ɷ���Ϣ
	 * @return
     * @see [�ࡢ��#��������#��Ա]
	 */
	public void updateCardChargeLog(CardChargeLogVO chargeLogVO);
	
	/**
	 * �����½ɷ���־
	 * 
	 * @param chargeLogVO �ɷ���Ϣ
	 * @param message ������Ϣ
	 * @param unionRetCode ����
	 * @param status �ɷ�״̬
	 * @return
     * @see [�ࡢ��#��������#��Ա]
	 */
	public void updateChargeLog(CardChargeLogVO chargeLogVO, String message, String unionRetCode, String status);
}
