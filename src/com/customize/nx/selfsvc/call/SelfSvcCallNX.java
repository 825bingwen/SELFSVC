package com.customize.nx.selfsvc.call;

import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;

public interface SelfSvcCallNX
{
    
    /**
     * �˻�����ѯ
     * 
     * @param paramMap
     * @return
     */
    public ReturnWrap queryBalance(Map<String, String> paramMap);
    
    /**
     * ���˵���ѯ
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryMonthBill(Map map);
    
    /**
     * ���û���������������
     * 
     * @param map
     * @return
     * @see
     */
    public ReturnWrap sendSMS(Map map);
    
    /**
     * �ɷѲ�ѯ�ӿ�
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryfeeChargeAccount(Map map);
    
    /**
     * �ɷѽӿ�
     * 
     * @param map
     * @return
     */
    public ReturnWrap chargeCommit(Map map);
    
    /**
     * �����ײ���Ϣ��ѯ
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryPackageInfo(Map map);
    
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
     * ��ѯ���л���֧����ʽ
     * 
     * @param paramMap
     * @return
     * @see 
     */
    public ReturnWrap qryChargeType(Map<String, String> paramMap);
    
    /**
     * ȡ�����л���֧����ʽ
     * 
     * @param paramMap
     * @return
     * @see 
     */
    public ReturnWrap cancelChargeType(Map<String, String> paramMap);
    
    /**
     * �������л���֧����ʽ
     * 
     * @param paramMap
     * @return
     * @see 
     */
    public ReturnWrap addChargeType(Map<String, String> paramMap);
    
    /**
     * ��Ʊ��ѯ
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/02/04 R003C13L01n01 OR_huawei_201302_122 
     */
    public ReturnWrap queryInvoice(Map<String, String> map);
    
    /**
     *  �����Ʒ���ȷ����Ϣ��ѯ
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281
     */
    public ReturnWrap mainProductRecInfo(Map<String,String> map);
    
    /**
     *  �����Ʒ�������
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281 
     */
    public ReturnWrap mainProductChangeSubmit(Map<String,String> map);
    
    /**
     *  ��ѯ�ɱ�������Ʒ�б�
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281 
     */
    public ReturnWrap qryChangeMainProduct(Map<String,String> map);
    
    /**
     * �������Ͳ�ѯ����ֵ
     * 
     * @param map
     * @return returnWrap
     * @remark create zWX176560 2013/08/22 R003C13L09n01 OR_NX_201308_595
     */
    public ReturnWrap qryUserScoreInfoByType(Map paramMap);
    
    /**
     * ����ʱ֤������У��
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chkCertSubs(Map map);
    
    /**
     * ��ѯ�ֻ������б�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryNumberByProdid(Map map);
    
    /**
     * ������Դ��ѡ�ӿ�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap telNumTmpPick(Map map);
    
    /**
     * У��հ׿���Դ�Ƿ����
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chkBlankNo(Map map);
    
    /**
     * �հ׿���Դ��ѡ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap blankCardTmpPick(Map map);
    
    /**
     * �ſ�У��
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chkTelSimcard(Map map);
    
    /**
     * �������
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap reckonRecFee(Map map);
    
    /**
     * ����
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap terminalInstall(Map map);
    
    //add begin jWX216858 2014/6/17 OR_NX_201406_553_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ
    /**
     * ��ȡ�½ᷢƱ��Ϣ�����ģ�
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_NX_201406_553_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ
     */
    public ReturnWrap getMonInvoiceData(Map<String, String> paramMap);
    
    /**
     * ��ѯ���ڣ����ģ�
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_NX_201406_553_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ
     */
    public ReturnWrap qryBillCycle(Map<String, String> paramMap);
    //add end jWX216858 2014/6/17 OR_NX_201406_553_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ

    
}
