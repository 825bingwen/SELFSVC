package com.gmcc.boss.selfsvc.managerOperation.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.managerOperation.dao.ManagerOperationDaoImpl;
import com.gmcc.boss.selfsvc.managerOperation.model.CashDetailPO;
import com.gmcc.boss.selfsvc.managerOperation.model.ManagerOperationPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * ά����Ա���� �����ֽ����
 * 
 * @author xKF29026
 *
 */
public class ManagerOperationServiceImpl implements ManagerOperationService
{
    private ManagerOperationDaoImpl managerOperationDaoImpl;
    
    /**
     * У�������Ա����
     */
    public String checkAuditPassword(ManagerOperationPO managerOperationPO)
    {
        // add begin zKF69263 2015-4-2 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
        // �ֽ���˿���
        String cashAuditSwitch = CommonUtil.getParamValue(Constants.SH_CASHAUDIT_SWITCH);
        
        // Ϊ1��ʾ�ֽ���˿��ؿ���
        if ("1".equals(cashAuditSwitch))
        {
            // ��ѯ�ն˻����������� 
            String termAuditPass = managerOperationDaoImpl.qryTermAuditPass(managerOperationPO);
            
            // ����ն˻�û�����û��������룬����Ϊ��������
            if (StringUtils.isEmpty(termAuditPass))
            {
                // ȡ�ù������벢����MD5����
                termAuditPass = CommonUtil.MD5Encode(CommonUtil.getParamValue(Constants.SH_CASHAUDIT_PASSWORD));
            }
            
            // ��֤�����������Ƿ���ȷ����ȷ����1�����󷵻�2
            if (StringUtils.isNotEmpty(termAuditPass) && termAuditPass.equals(managerOperationPO.getAuditPsw()))
            {
                return "1";
            }
            
            return "2";
        }
        // add end zKF69263 2015-4-2 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
        
        return managerOperationDaoImpl.checkAuditPassword(managerOperationPO);
    }
    
    /**
     * ��ȡϵͳ�����ܽ��
     */
    public String getSysMoney(String termid)
    {
        return managerOperationDaoImpl.getSysMoney(termid);
    }
    
    /**
     * �����ֽ������־
     */
    public String insertAuditCashLog(ManagerOperationPO managerOperationPO)
    {
        return managerOperationDaoImpl.insertAuditCashLog(managerOperationPO);
    }
        
    /**
     * ���ݻ���ʱ��β�ѯϵͳͳ�ƽ��
     * 
     * @param log
     * @return
     * @see 
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public String getSysMoney(ManagerOperationPO log)
    {
        String strMoney = managerOperationDaoImpl.getSysMoney(log);
        
        if (null == strMoney || "".equals(strMoney.trim()))
        {
            strMoney = "0";
        }
        
        return strMoney;
    }
    
    /**
     * ��ѯ����ʱ����ڸ�������ֽ������
     * 
     * @param log
     * @param fillStr
     * @return
     * @see 
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public List<CashDetailPO> qryCashDetailInfo(ManagerOperationPO log, String fillStr)
    {
        List<CashDetailPO> cashes = managerOperationDaoImpl.qryCashDetailInfo(log);
        if (null != cashes && cashes.size() > 0)
        {
            CashDetailPO cash = null;
            for (int i = 0; i < cashes.size(); i++)
            {
                cash = cashes.get(i);
                
                // �������ԣ��˴���ʹ������&nbsp;��ֻ��һ���Ļ����޷��Ҷ���
                cash.setCashFee(CommonUtil.fillLeftBlank(cash.getCashFee(), 3, fillStr));
                cash.setCashNum(CommonUtil.fillLeftBlank(cash.getCashNum(), 4, fillStr));
            }
        }
        
        return cashes;
    }
    
    /**
     * ��ȡ�ϴλ�����־
     * 
     * @param termid
     * @return
     * @see 
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public ManagerOperationPO qryLastAuditLog(String termid)
    {
        return managerOperationDaoImpl.qryLastAuditLog(termid);
    }
    
    /**
     * ��¼�����ֽ������־
     * 
     * @param managerOperationPO
     * @return
     * @see 
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public String insertNXAuditLog(ManagerOperationPO managerOperationPO)
    {
        return managerOperationDaoImpl.insertNXAuditLog(managerOperationPO);
    }
    
    /**
     * ��ѯ����ʱ����ڵ�ǰ6���ɷѼ�¼���6���ɷѼ�¼
     * 
     * @param flag first��ǰ6�������򣬺�6��
     * @param managerOperationPO
     * @return
     * @see 
     * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
     */
    public List<CardChargeLogVO> qrySixChargeRecords(String flag, ManagerOperationPO managerOperationPO)
    {
        if ("first".equalsIgnoreCase(flag))
        {
            return managerOperationDaoImpl.qrySixChargeRecords("selectFirstSixChargeRecords", managerOperationPO);
        }
                
        return managerOperationDaoImpl.qrySixChargeRecords("selectLastSixChargeRecords", managerOperationPO);
    }
    
    /**
     * ����ָ����sqlID��ѯ���������ĽɷѼ�¼������
     * 
     * @param sqlID
     * @param managerOperationPO
     * @return
     * @see 
     * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
     */
    public String qryChargeRecordsNum(String sqlID, ManagerOperationPO managerOperationPO)
    {
        return managerOperationDaoImpl.qryChargeRecordsNum(sqlID, managerOperationPO);
    }
    
    /**
     * ��ѯ�ɷ�ʧ�ܼ�¼
     * 
     * @param managerOperationPO
     * @return
     * @see 
     * @remark: create  cKF76106 2012/08/07 R003C12L07n01 OR_NX_201207_780
     */
    public List<CardChargeLogVO> qryFailedRecords(ManagerOperationPO managerOperationPO)
    {
        return managerOperationDaoImpl.qryFailedRecords(managerOperationPO);
    }
    
    /**
     * ��ѯδ��ӡ�ļ�¼
     * <������ϸ����>
     * @param managerOperationPO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<ManagerOperationPO> qryUnPrintRecords(ManagerOperationPO managerOperationPO)
    {
        return managerOperationDaoImpl.qryUnPrintRecords(managerOperationPO);
    }
    
    /**
     * ����ǰ��¼����
     * <������ϸ����>
     * @param managerOperationPO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public void insertLogByAuditBefore(ManagerOperationPO managerOperationPO)
    {
        managerOperationDaoImpl.insertLogByAuditBefore(managerOperationPO);
    }
    
    /**
     * ���������ֽ������־
     * <������ϸ����>
     * @param managerOperationPO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String updateNXAuditLog(ManagerOperationPO managerOperationPO)
    {
        return managerOperationDaoImpl.updateNXAuditLog(managerOperationPO);
    }
    
    /**
     * ���������ֽ������־
     * <������ϸ����>
     * @param managerOperationPO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String updatePringFlag(ManagerOperationPO managerOperationPO)
    {
        return managerOperationDaoImpl.updatePringFlag(managerOperationPO);
    }
    
    public ManagerOperationDaoImpl getManagerOperationDaoImpl()
    {
        return managerOperationDaoImpl;
    }
    
    public void setManagerOperationDaoImpl(ManagerOperationDaoImpl managerOperationDaoImpl)
    {
        this.managerOperationDaoImpl = managerOperationDaoImpl;
    }
}
