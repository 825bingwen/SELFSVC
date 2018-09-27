package com.gmcc.boss.selfsvc.managerOperation.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.managerOperation.model.CashDetailPO;
import com.gmcc.boss.selfsvc.managerOperation.model.ManagerOperationPO;

/**
 * ά����Ա���� �����ֽ����
 * 
 * @author xKF29026
 * 
 */
public class ManagerOperationDaoImpl extends BaseDaoImpl
{
    public static final Log logger = LogFactory.getLog(ManagerOperationDaoImpl.class);
        
    /**
     * У����˼ල��Ա����
     */
    public String checkAuditPassword(ManagerOperationPO managerOperationPO)
    {
    	ManagerOperationPO checkStatus = (ManagerOperationPO) sqlMapClientTemplate.queryForObject("managerOperation.checkAuditPassword", managerOperationPO);
    	if(checkStatus != null)
    	{
    		return "1";
    	}
    	else
    	{
    		return "2";
    	}
    }
    
    /** 
     * ��ѯ�ն˻����������� 
     * 
     * @param managerOperationPO
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark: create zKF69263 2015/04/10 OR_SD_201502_169_SD_�����ն�ʵ���ֽ���˹���
     */
    public String qryTermAuditPass(ManagerOperationPO managerOperationPO)
    {
        return (String) sqlMapClientTemplate.queryForObject("managerOperation.qryTermAuditPass", managerOperationPO);
    }
    
    /**
     * ��ȡϵͳ�����ܽ��
     */
    public String getSysMoney(String termid)
    {
        ManagerOperationPO managerOperationPO = new ManagerOperationPO();
        managerOperationPO.setTermid(termid);
        String auditEndTime = (String)sqlMapClientTemplate.queryForObject("managerOperation.selectAuditEndTime", termid);
        if (auditEndTime == null)
        {
            auditEndTime = "2010-01-01 00:00:00";
        }
        else
        {
            auditEndTime = auditEndTime.substring(0, 19);
        }
        managerOperationPO.setAuditEndTime(auditEndTime);
        String totalFeeValus = (String)sqlMapClientTemplate.queryForObject("managerOperation.selectAuditTotalFee",
                managerOperationPO);
        if (totalFeeValus == null || totalFeeValus .equals("0"))
        {
            return "000";
        }
        return totalFeeValus;
    }
    
    /**
     * �����ֽ������־
     */
    public String insertAuditCashLog(ManagerOperationPO managerOperationPO)
    {
        String auditEndTime = (String)sqlMapClientTemplate.queryForObject("managerOperation.selectAuditEndTime",
                managerOperationPO.getTermid());
        if (auditEndTime == null)
        {
            auditEndTime = "2010-01-01 00:00:00";
        }
        else
        {
            auditEndTime = auditEndTime.substring(0, 19);
        }
        managerOperationPO.setAuditEndTime(auditEndTime);
        String totalFeeValus = (String)sqlMapClientTemplate.queryForObject("managerOperation.selectAuditTotalFee",
                managerOperationPO);
        if (totalFeeValus == null)
        {
            managerOperationPO.setSysMoney("0");
        }
        else
        {
            managerOperationPO.setSysMoney(totalFeeValus);
        }
        try
        {
            sqlMapClientTemplate.insert("managerOperation.insertAuditCashLog", managerOperationPO);
            return "1";
        }
        catch (Exception e)
        {
            logger.error("��¼�ֽ������־�쳣��", e);
            
            return "0";
        }
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
        List<ManagerOperationPO> auditLogs = (List<ManagerOperationPO>) sqlMapClientTemplate.queryForList("managerOperation.selectLastAuditLog", termid);
        if (null == auditLogs || auditLogs.size() == 0)
        {
            return null;
        }
        
        return auditLogs.get(0);
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
        return (String) sqlMapClientTemplate.queryForObject("managerOperation.selectSystemTotalFee", log);
    }
    
    /**
     * ��ѯ����ʱ����ڸ�������ֽ������
     * 
     * @param log
     * @return
     * @see 
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public List<CashDetailPO> qryCashDetailInfo(ManagerOperationPO log)
    {
        return (List<CashDetailPO>) sqlMapClientTemplate.queryForList("managerOperation.getCashDetailInfo", log);
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
        String result = "1";
        
        String auditStartTime = managerOperationPO.getAuditStartTime();
        if ("".equals(auditStartTime))
        {
            try
            {
                sqlMapClientTemplate.insert("managerOperation.insertNXFirstAuditLog", managerOperationPO);
            }
            catch (Exception e)
            {
                logger.error("��¼�����ֽ������־�쳣��", e);
                
                result = "0";
            }
        }
        else
        {
            try
            {
                sqlMapClientTemplate.insert("managerOperation.insertNXAuditLog", managerOperationPO);
            }
            catch (Exception e)
            {
                logger.error("��¼�����ֽ������־�쳣��", e);
                
                result = "0";
            }
        }
        
        return result;
    }
    
    /**
     * ��ѯ����ʱ����ڵ�ǰ6���ɷѼ�¼���6���ɷѼ�¼
     * 
     * @param sqlID
     * @param managerOperationPO
     * @return
     * @see 
     * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
     */
    public List<CardChargeLogVO> qrySixChargeRecords(String sqlID, ManagerOperationPO managerOperationPO)
    {
        List<CardChargeLogVO> records = null;
        
        try
        {
            records = (List<CardChargeLogVO>) sqlMapClientTemplate.queryForList("managerOperation." + sqlID, managerOperationPO);
        }
        catch (Exception e)
        {
            logger.error("����" + sqlID + "��ѯ����ʱ����ڵ�ǰ6���ɷѼ�¼���6���ɷѼ�¼�쳣��", e);
        }
        
        return records;
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
        String result = "0";
        
        try
        {
            result = (String) sqlMapClientTemplate.queryForObject("managerOperation." + sqlID, managerOperationPO);
        }
        catch (Exception e)
        {
            logger.error("����" + sqlID + "��ѯ���������ļ�¼�쳣��", e);
        }
        
        return result;
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
        List<CardChargeLogVO> records = null;
        
        try
        {
            records = (List<CardChargeLogVO>) sqlMapClientTemplate.queryForList("managerOperation.selectFailedRecords" , managerOperationPO);
        }
        catch (Exception e)
        {
            logger.error("��ѯ�ֽ�ɷ�ʧ�ܼ�¼�쳣��", e);
        }
        
        return records;
    }
    
    /**
     * ��ѯδ��ӡ�ļ�¼_����
     * 
     * @param managerOperationPO
     * @return
     * @see 
     */
    public List<ManagerOperationPO> qryUnPrintRecords(ManagerOperationPO managerOperationPO)
    {
        List<ManagerOperationPO> records = null;
        
        try
        {
            records = (List<ManagerOperationPO>) sqlMapClientTemplate.queryForList("managerOperation.qryUnPrintRecords" , managerOperationPO);
        }
        catch (Exception e)
        {
            logger.error("��ѯδ��ӡ�Ľ��˵���¼ʧ�ܣ�", e);
        }
        
        return records;
    }
    
    /**
     * ����ǰ��¼��־_����
     * 
     * @param managerOperationPO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public void insertLogByAuditBefore(ManagerOperationPO managerOperationPO)
    {
        try
        {
            sqlMapClientTemplate.insert("managerOperation.insertLogByAuditBefore" , managerOperationPO);
        }
        catch (Exception e)
        {
            logger.error("����ǰ��¼��־ʧ�ܣ�", e);
        }
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
        String result = "1";

        try
        {
            sqlMapClientTemplate.insert("managerOperation.updateNXAuditLog", managerOperationPO);
        }
        catch (Exception e)
        {
            logger.error("��¼�����ֽ������־�쳣��", e);
            
            result = "0";
        }
        
        return result;
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
        String result = "1";

        try
        {
            sqlMapClientTemplate.insert("managerOperation.updatePringFlag", managerOperationPO);
        }
        catch (Exception e)
        {
            logger.error("��¼�����ֽ������־�쳣��", e);
            
            result = "0";
        }
        return result;
    }
    
}
