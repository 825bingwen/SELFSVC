package com.customize.sd.selfsvc.call;

import java.util.Map;

import org.dom4j.Document;

import com.customize.sd.selfsvc.cardbusi.model.IdCardPO;
import com.customize.sd.selfsvc.cardbusi.model.ProdTempletPO;
import com.customize.sd.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.sd.selfsvc.packageService.model.PrivServPackPO;
import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;
import com.customize.sd.selfsvc.serviceinfo.model.BankCardInfoPO;
import com.customize.sd.selfsvc.serviceinfo.model.BindBankCardPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public interface SelfSvcCallSD
{    
    /**
     * ɽ���ײ���Ϣ��ѯ
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryComboInfo(Map map);
    
    /**
     * ɽ�����˵���ѯ
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryMonthBill(Map map);
    
    /**
     * ɽ������ѯ
     * 
     * @param map
     * @return
     */
    public ReturnWrap queryBalance(Map<String, String> paramMap);
    
    /**
     * ���ѳ�ֵ�˻���Ϣ��ѯ
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryfeeChargeAccount(Map map);
    
    /**
     * ���ѳ�ֵ
     * 
     * @param map
     * @return
     */
    public ReturnWrap chargeCommit(Map<String,String> map);
    
    /**
     * ��У�����룬ֱ�ӻ�ȡ�û���Ϣ
     * 
     * @param map
     * @return
     * @see
     */
    public ReturnWrap getUserStatus(Map map);
    
    
    /**
     * ��ȡ�˵����Ͳ�ѯ�ķ�����Ϣ
     * 
     * @param map
     * @return 
     * @see
     */
    public ReturnWrap getMailBillSendInfo(Map map);	
	
    /**
     * ����ԭ������͵�ַ
     * 
     * @param map
     * @return 
     * @see
     */
    public ReturnWrap getCancelCaseInfo(Map map);
    
    /**
     * ��ͨ�������
     * 
     * @param map
     * @return 
     * @see
     */
	public ReturnWrap getOpenBillMailInfo(Map map);
	
	/**
     * ��ѯ�û��Ƿ��ѿ�ͨ�ֻ�����
     * @param map
     * @return
     */
    public ReturnWrap qrymailBox(Map map);
    
    /**
     * ��ͨ139�������
     */
    public ReturnWrap add139Mail(Map map);
    
    // add begin cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    /**
     * �˵���ע��ѯ
     */
    public ReturnWrap queryBillAddInfo(Map map);
    // add end cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    
    /**
     * �����ֻ������ѯ�ͻ���Ϣ
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap getCustinfo(Map map);
    
    /**
     * ��ѯ������Ϣ_�°�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryMonthBill_new(Map map);

    /**
     * У���û��Ƿ�ʵ��ע��
     * 
     * @param bindBankCardPO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap checkFactNameRegist(Map paramMap);
    
    /**
     * �Զ����Ѳ����ӿ�
     * 
     * @param msgHeader
     * @param ncode
     * @param oprtype
     * @param trigamt ������� ��
     * @param drawamt �Զ���ֵ��� ��     
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @modify yWX163692 2013��11��19�� OR_SD_201309_940 �׳�ֵ���׶Σ���Լ�����Զ������ж�����  
     * @remark modify by sWX219697 2014-12-2 15:33:07 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
     */
    public ReturnWrap autoFeeSet(MsgHeaderPO msgHeader, String oprtype, String trigamt, String drawamt);
    
    /**
     * �׳�ֵǩԼ֮ǰ���ýӿڼ���Ƿ������Ʒ��ͨ����
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap checkProCondition(Map map);
    
    // add begin zKF69263 2014/05/09 R003C14L05n01 OR_huawei_201404_1109
    /**
     * ��ѯδ��ӡ�ķ�Ʊ��¼����
     * 
     * @param map
     * @return ReturnWrap
     */
    public ReturnWrap invoiceList(Map map);
    
    /**
     * ��ѯҪ��ӡ�ķ�Ʊ��ӡ������
     * 
     * @param map
     * @return ReturnWrap
     */
    public ReturnWrap invoiceData(Map map);
    // add end zKF69263 2014/05/09 R003C14L05n01 OR_huawei_201404_1109
    
    /**
     * ���ѳ�ֵ�˻�Ӧ�ɷ��ò�ѯ
     * @param paramMap
     * @remark  add by hWX5316476 2014-03-12 OR_SD_201312_90_ɽ��_�����ն˽���Ӧ��������ʾ���Ż�����
     * @return ReturnWrap
     */
    public ReturnWrap qryRetcharge(Map paramMap);
    
    /**
     * �˵��ʼ��·��ӿ�
     * @param map 
     * @remark  create sWX219697 2014-04-29 OR_SD_201404_105_ɽ��_��������139�����굥Ͷ����Ŀ�ĺ�
     * @return ReturnWrap
     */
    public ReturnWrap sendBillMail(Map<String,String> map);

    /**
     * ���ڲ�ѯ����
     * @param paramMap
     * @remark add by wWX217192 on 20140504 for OR_huawei_201404_1108 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_��ӡ�½ᷢƱ
     * @return
     */
    public ReturnWrap qryBillCycle(Map map);
    
    /**
     * �½ᷢƱ���ݲ�ѯ
     * @param paramMap
     * @remark add by wWX217192 on 20140504 for OR_huawei_201404_1108 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_��ӡ�½ᷢƱ
     * @return
     */
    public ReturnWrap qryMonthInvoice(Map map);

    /**
     * <��ѯ�����̿ճ��˻���Ϣ>
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryAgentInfo(Map<String,String> map);
    
    /**
     * <�����̽ɷ�ǰ��¼>
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap beforeAgentCharge(Map<String,String> map);
    
    /**
     * <�����̳�ֵ>
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap agentCharge(Map<String,String> map);

    /**
     * ��ѯ�ɱ���������Ʒ��Ϣ
     * @param paramMap
     * @return ReturnWrap
     * @remark add by jWX216858 2014-5-7 OR_huawei_201404_1116_ɽ��_Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_ת�ײͲ�Ʒ
     */
    public ReturnWrap qryMainProdInfo(Map<String, String> paramMap);
    
    /**
     * ��ѯ�����Ʒģ����Ϣ
     * @param paramMap
     * @return ReturnWrap
     * @remark add by jWX216858 2014-5-8 OR_huawei_201404_1116_ɽ��_Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_ת�ײͲ�Ʒ
     */
    public ReturnWrap qryProdTemplateInfo(Map<String, String> paramMap);
    
    /**
	 * ִ�������Ʒ���
	 * @param paramMap
     * @return ReturnWrap
	 * @remark add by jWX216858 2014-5-8 OR_huawei_201404_1116_ɽ��_Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_ת�ײͲ�Ʒ
	 */
    public ReturnWrap mainProdChangeRec(Map<String, String> paramMap);

    /**
     * ��֤SIM��
     * @param paramMap
     * @return ReturnWrap
     * @remark add by hWX5316476 2014-06-23 V200R003C10LG0601 OR_SD_201405_849_������Ӫҵ������ʵ������֤�Ĺ���
     */
    public ReturnWrap chkSIMCardNo(Map<String,String> paramMap);
    
    /**
     * ��¼ʵ������֤������־
     * @param paramMap
     * @return ReturnWrap
     * @remark add by hWX5316476 2014-06-24 V200R003C10LG0601 OR_SD_201405_849_������Ӫҵ������ʵ������֤�Ĺ���
     * @remark modify gWX301560 2015-08-12 OR_SD_201506_971_ɽ��_���ڴ�����ʵ���ƿͻ����Ǽ����ϵͳ֧�ŵ�����
     */
    public ReturnWrap saveRealNameChkRecLog(Map<String,String> paramMap, Map<String,String> map);
    
    /**
     * ��ֵ��¼��֤
     * @param paramMap
     * @return 
     * @remark add by hWX5316476 2014-06-24 V200R003C10LG0601 OR_SD_201405_849_������Ӫҵ������ʵ������֤�Ĺ���
     */
    public ReturnWrap chkChargeRecord(Map<String, Object> paramMap);
    
    /**
     * ͨ����¼��֤
     * @param paramMap
     * @return
     * @remark add by hWX5316476 2014-06-24 V200R003C10LG0601 OR_SD_201405_849_������Ӫҵ������ʵ������֤�Ĺ���
     */
    public ReturnWrap chkCallRecord(Map<String, String> paramMap);
    
    /**
	 * ��ѯ�û�ʵ���ƵǼǱ�־
	 * @param �ֻ�����
	 * @param �ն���Ϣ
	 * @param �˵���Ϣ
	 * @return �ӿڵ��óɹ����ı�־λ���ӿڷ�����Ϣ
	 * @remark create wWX217192 2014-06-23 OR_huawei_201406_338
	 */
    public ReturnWrap qryRealNameType(Map<String, String> map);
    
    /**
	 * ���ɶ�����֤�����
	 * 
	 * @param map
	 * @return �ӿڷ�����Ϣ
	 * @remark create wWX217192 2014-06-24 OR_huawei_201406_338
	 */
	public ReturnWrap getRandomPwd(Map<String, String> map);
	
	/**
	 * �������������֤
	 * 
	 * @param map
	 * @return �ӿڷ�����Ϣ
	 * @remark create wWX217192 2014-06-25 OR_huawei_201406_338
	 */
	public ReturnWrap checkRandomPwd(Map<String, String> map);
	
	/**
	 * ����������֤�ӿ�
	 * 
	 * @param map
	 * @return �ӿڷ�����Ϣ
	 * @remark create wWX217192 2014-06-25 OR_huawei_201406_338
	 */
	public ReturnWrap checkUserPwd(Map<String, String> map);
	
    /**
     * <һ�仰���ܼ���>
     * <������ϸ����>
     * @param msgHeader ��Ϣͷ��
     * @param orgId ��֯����id
     * @param agentAccount �ʽ��˻�����
     * @param subjectId ��Ŀ����
     * @param tMoney ���ѽ�� ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-8-23 10:43:09 OR_huawei_201408_657_�����ն˴������ʽ��˻���ֵ�����Ż�
     */
	public ReturnWrap checkBeforeAgentCharge(MsgHeaderPO msgHeader, String orgId, String agentAccount, 
			String subjectId, String tMoney);
	
	/**
	 * <��ѯ�û��ĸ�������>
	 * <������ϸ����>
	 * @param msgHeader ��Ϣͷ��
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-10-7 14:27:18 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
	 */
	public ReturnWrap qrySubsPrepayType(MsgHeaderPO msgHeader);
	
	/**
	 * ����ͨ������
	 * @param msgHeader ����ͷ��Ϣ
	 * @param nCode nCode
	 * @return
	 * @remark create by jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_ɽ��_���������ն˲�Ʒ����������4G��ѡ�ײ��Լ��޸�GPRS��4G����Ĺ��ܣ�ȫҵ�������Ż���
	 */
	public ReturnWrap voiceCallRec(MsgHeaderPO msgHeader, String nCode);
	
	/**
	 * ������������
	 * @param header ��Ϣͷ
	 * @param productset ��ֵ��Ʒ��(��Ʒ��,��ֵ��Ʒ,�Ż�;��Ʒ��,��ֵ��Ʒ,�Ż�;)
	 * @return
	 * @remark create by jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_ɽ��_���������ն˲�Ʒ����������4G��ѡ�ײ��Լ��޸�GPRS��4G����Ĺ��ܣ�ȫҵ�������Ż���
	 */
	public ReturnWrap gprsWlanRec(MsgHeaderPO header, String productset);
	
	/**
	 * <��ѯ�û��������б�>
	 * <������ϸ����>
	 * @param msgHeader
	 * @param ncode
	 * @param stype
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark sWX219697 2014-12-2 19:36:41 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
	 */
	public ReturnWrap viceNumberQry(MsgHeaderPO msgHeader, String ncode, String stype);
	
	/**
	 * <������ɾ���׳�ֵ������>
	 * <������ϸ����>
	 * @param msgHeader
	 * @param viceArray ����������
	 * @param opertype �������ͣ�1������
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark sWX219697 2014-12-4 12:00:03 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
	 */
	public ReturnWrap viceNumberSet(MsgHeaderPO msgHeader, String[] viceArray, String opertype);
	
	/**
	 * ��ѯ��ǰ�û��Ƿ�ǩԼ�Ͱ��׳�ֵ
	 * @param headerPo ��Ϣͷ
	 * @return �û�ǩԼ��Ϣ
	 * @remark create by wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
	 */
	public ReturnWrap checkHeBao(MsgHeaderPO headerPo, String bankCardNum);
	
	/**
	 * �Ͱ��׳�ֵƽ̨���Ͷ����������
	 * @param headerPo ������ͷ
	 * @param smsType ��֤������
	 * @param AGRNO Э���
	 * @return �Ͱ��׳�ֵƽ̨�ķ��ر���
	 * @remark create by wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
	 */
	public ReturnWrap sendHeBaoRandom(MsgHeaderPO headerPo, String smsType, BindBankCardPO cardPo);
	
	/**
	 * �Ͱ��׳�ֵǩԼ
	 * @param headerPo
	 * @param cardPo
	 * @param smsCode
	 * @return
	 * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
	 */
	public ReturnWrap signHeBao(MsgHeaderPO headerPo, BindBankCardPO cardPo, String smsCode);
	
	/**
	 * �Ͱ��׳�ֵ��Լ�ӿ�
	 * @param headerPo
	 * @param cardPo
	 * @param smsCode
	 * @return
	 * @remark create by wWX217192 2014-11-29 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
	 */
	public ReturnWrap unsignHeBao(MsgHeaderPO headerPo, BindBankCardPO cardPo, String smsCode);
	
	/**
	 * �Ͱ��׳�ֵ�Զ����ѹ�������
	 * @param headerPo
	 * @param oprType
	 * @param trigAmt
	 * @param drawAmt
	 * @param bankId
	 * @return
	 * @remark create by wWX217192 2014-12-10 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
	 */
	public ReturnWrap setHeBaoAutoValue(MsgHeaderPO headerPo, String oprType, BankCardInfoPO bankCardInfoPO);
	
	/**
     * <У���û����֤��Ϣ���ֻ����Ƿ����>
     * <������ϸ����>
     * @param msgHeader
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap checkReissueIdCard(MsgHeaderPO msgHeader, String idCardNo);
    
    /**
     * <У���û���������>
     * <������ϸ����>
     * @param msgHeader
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap checkReissueNum(MsgHeaderPO msgHeader);
    
    /**
     * <�������>
     * <������ϸ����>
     * @param msgHeader
     * @param iccid
     * @param blankCardNum �հ׿�����
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap countReissueFee(MsgHeaderPO msgHeader, String iccid, String blankCardNum);
    
    /**
     * <�����ύ>
     * <������ϸ����>
     * @param msgHeader
     * @param recFee Ӧ�ɷ���
     * @param payType ֧����ʽ
     * @param simInfo sim����Ϣ
     * @param bankNo ���б��
     * @param bankNbr ���нɷ���ˮ��
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap reissueCommit(MsgHeaderPO msgHeader, String recFee, String payType, String blankno,
        SimInfoPO simInfo, String bankNo, String bankNbr);
    
    /**
     * <��ѯ�û���Ϣ>
     * <������ϸ����>
     * @param msgHeader
     * @param region
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap getSubscriberByTel(MsgHeaderPO msgHeader,String region);
	
	/**
	 * ҵ����Ч��У��
	 * 
	 * @param msgHeader ��Ϣͷ
	 * @return true�����Լ�������ҵ��false����ֹ����ҵ��
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ�����
	 */
	public ReturnWrap checkRecValid(MsgHeaderPO msgHeader);
	
	/**
	 * ��ѯ�û��Ѿ����ڵĵ���
	 * @param msgHeader ��Ϣͷ
	 * @return
	 * @remark create by jWX216858 2014-11-29 OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ�����
	 */
	public ReturnWrap qrySubsActLevelList(MsgHeaderPO msgHeader);
	
	/**
	 * ��ѯ��Ʒ�б�
	 * @param msgHeader ��Ϣͷ
	 * @param actLevelId ���α���
	 * @param activityId �����
	 * @return
	 * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ�����
	 */
	public ReturnWrap qryRewardList(MsgHeaderPO msgHeader, String actLevelId, String activityId);
	
	/**
	 * ��ѯ�����
	 * @param msgHeader ��Ϣͷ
	 * @param actid �����
	 * @param levelid ���α���
	 * @param rewardId ��Ʒ����
	 * @return
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ�����
	 */
	public ReturnWrap qryActivityFee(MsgHeaderPO msgHeader, String actid, String levelid, String rewardId);

	/**
	 * Ԥ����������
	 * 
	 * @param msgHeader ��Ϣͷ
	 * @param prestoredRewardPO ��� 
	 * @param bankNo ���б��
     * @param bankNbr ���нɷ���ˮ��
	 * @return
	 * @remark create by jWX216858 2014-12-08 OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ�����
	 */
	public ReturnWrap recRewardCommit(MsgHeaderPO msgHeader,PrestoredRewardPO prestoredRewardPO, String bankNo,
        String bankNbr);
	
	/** 
     * ҵ�����ǰ��¼ҵ�������Ϣ
     * 
     * @param msgHeader ��������ͷ
     * @param bankNo ���к�
     * @param acceptType ҵ������(������ZZKH ������ZZBK Ԥ������ZZHD)
     * @param bankNbr Ψһ��ˮ(��agentcharge���AGENTFORMNUM�ֶα���һ��) ���ն˻����ص�termseq
     * @param amount �������
     * @param prestoredRewardPO ���Ϣ
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by zKF69263 2015-05-07 OR_SD_201503_333_SD_�����ն˻����Ԥ������
     */
    public ReturnWrap writeNetFeeInfo(MsgHeaderPO msgHeader, String bankNo, String acceptType,
        String bankNbr, String amount, PrestoredRewardPO prestoredRewardPO);

	/**
	 * ��ѯӪ���������ú��û�Ԥ�����
	 * @param msgHeader ��Ϣͷ
	 * @param recoid ������ˮ
	 * @param totalFee �û�����ķ���
	 * @return
	 * @remark create by jWX216858 2014-12-08 OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ�����
	 */
	public ReturnWrap qryRecFeeAndPreFee(MsgHeaderPO msgHeader, String recoid, String totalFee);
	
	/**
     * �����ػ�ҵ���
     * @param msgHeader
     * @param privServPackPO
     * @return
     * @remark create by hWX5316476 2014-12-24 OR_SD_201410_482_SD_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ�����
     */
    public ReturnWrap privServPackRec(MsgHeaderPO msgHeader,PrivServPackPO privServPackPO);
    
    /**
     * ���������ƷId��ȡ�����Ʒ��Ϣ
     * @param msgHeader
     * @param prodid
     * @param type 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-1-9 OR_SD_201411_411_SD_���������ն˲�Ʒ�������Ż�������
     */
    public ReturnWrap qryProdInfoById(MsgHeaderPO msgHeader,String prodid,String type);
    
    /**
     * ���ڵ���ת��
     * @param msgHeader
     * @param ncode NCODE
     * @param stype �������� ADD ���� DEL ɾ�� MOD �޸� QRY ��ѯ
     * @param preparebusi Ԥ���� �̶���BsacNBSubmit
     * @param premutex �Ƿ񽫻���������Ľ��й���ɾ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-1-9 OR_SD_201411_411_SD_���������ն˲�Ʒ�������Ż�������
     */
    public ReturnWrap chgLevelInGroup(MsgHeaderPO msgHeader, String ncode, String stype, String preparebusi, String premutex);
    
    /** 
     * ����ʱ֤������У��
     * 
     * @param termInfo �ն���Ϣ
     * @param curMenuId ��ǰ�˵�ID
     * @param certType ֤������
     * @param certId ֤������
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap chkCertSubs(MsgHeaderPO msgHeader, String certType, String certId);
    
    /** 
     * ����ѡ�Ź����ѯ�ֻ������б�
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param orgId ��֯����
     * @param fitmod ѡ�Ź���
     * @param mainProdid �����ƷID
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-4 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap qryTelNumberListByRule(MsgHeaderPO msgHeader, String orgId, String fitmod, String mainProdid);

    /**
     * ������Դ��ѡ�ӿ�
     * 
     * @param msgHeader ��Ϣͷ
     * @param telnum �ֻ���
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
	public ReturnWrap telNumTmpPick(MsgHeaderPO msgHeader, String telnum);

	/**
     * У��հ׿���Դ�Ƿ����
     * 
     * @param msgHeader ��Ϣͷ
     * @param termInfo �ն˻���Ϣ
     * @param blankNo �հ׿���
     * @param prodid �����Ʒ
     * @param recType �������ͣ�����Install������ChangeEnum
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
	public ReturnWrap chkBlankNo(MsgHeaderPO msgHeader, TerminalInfoPO termInfo, String blankNo, String prodid, String recType);
	
	/**
     * У��հ׿��Ƿ���Ԥ�ÿտ�
     * 
     * @param msgHeader ��Ϣͷ
     * @param blankNo �հ׿����к�
     * @param telNum �ֻ���
     * @param recType �������ͣ�����Install������ChangeEnum
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
	public ReturnWrap chkPreSetBlankCard(MsgHeaderPO msgHeader, String blankNo, String telNum, String recType);

	/**
     * �������
     * 
     * @param msgHeader ��Ϣͷ
     * @param tpltTempletPO ģ��po
     * @param simInfoPO sim��po
     * @param blankno �հ׿����к�
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
	public ReturnWrap reckonRecFee(MsgHeaderPO msgHeader, ProdTempletPO tpltTempletPO, SimInfoPO simInfoPO, String blankno);
	
	/**
     * ����д���������հ׿���Դ��ѡ�ͻ�ȡ��������
     * 
     * @param msgHeader ��Ϣͷ
     * @param blankno �հ׿����к�
     * @param recType �������ͣ�����Install������ChangeEnum
     * @param region region
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
	public ReturnWrap getEncryptedData(MsgHeaderPO msgHeader, String blankNo, String recType, String region);

	/**
     * ���Ͽ���д��ʧ��ʱ����
     * 
     * @param msgHeader ��Ϣͷ
     * @param blankno �հ׿����к�
     * @param simInfoPO sim����Ϣ
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
	public ReturnWrap updateWriteCardResult(MsgHeaderPO msgHeader, String blankno, SimInfoPO simInfoPO);

	/**
     * �����ύ
     * 
     * @param msgHeader ��Ϣͷ
     * @param simInfoPO sim����Ϣ
     * @param tpltTempletPO ģ����Ϣ
     * @param idCardPO �û������Ϣ
     * @param totalfee �ܷ���
     * @param passwd ��������
     * @param telnum �ֻ�����
     * @param paytype ֧����ʽ 1��������
     * @param bankNo ���б��
     * @param bankNbr ���нɷ���ˮ��
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-15 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap terminalInstall(MsgHeaderPO msgHeader, SimInfoPO simInfoPO, ProdTempletPO tpltTempletPO,
        IdCardPO idCardPO, String totalfee, String passwd, String telnum, String bankNo, String bankNbr);
	

    
    /**
     * <��ѯ�û���ԤԼ�ĺ����б�>
     * <������ϸ����>
     * @param msgHeader
     * @param Document doc
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-6-9 OR_SD_201505_489_����������ԤԼѡ�Ź���
     */
    public ReturnWrap qryBookedTelnum(MsgHeaderPO msgHeader, Document doc);
    
    
    /**
	 * ɽ���мۿ�����ӿ�
	 * @param header
	 * @param doc
	 * @return
	 * @remark create by wWX217192 2015-06-17 OR_SD_201505_1022_ɽ��_ɽ�����ӳ�ֵ����������_�����ն˸���
	 */
	public ReturnWrap buyValueCard(MsgHeaderPO header, Document doc);
	
}
