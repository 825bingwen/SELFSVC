package com.customize.hub.selfsvc.charge.dao;

import java.util.List;

import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.customize.hub.selfsvc.charge.model.SheExtractprizeRuleVO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

public class FeeChargeDaoHubImpl extends BaseDaoImpl
{
    /**
     * �������ɷѼ�¼��־
     * 
     * @param cardChargeLogVO ���п���Ϣ
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.insertChargeLog", cardChargeLogVO);
    }
    
    /**
     * �����ۿ�ɹ�֮�󣬸�����־
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
    	//modified by xkf57421 for XQ[2011]_10_082 begin
    	//sqlMapClientTemplate.insert("charge.updateCardChargeLog", cardChargeLogVO);
    	sqlMapClientTemplate.insert("chargeHub.updateCardChargeLog", cardChargeLogVO);
    	//modified by xkf57421 for XQ[2011]_10_082 end
    }
    
    /**
     * ���³�ֵ�ɷѼ�¼
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        //modified by xkf57421 for XQ[2011]_10_082 begin
    	//sqlMapClientTemplate.insert("charge.updateChargeLog", cardChargeLogVO);
    	sqlMapClientTemplate.insert("chargeHub.updateChargeLog", cardChargeLogVO);
    	//modified by xkf57421 for XQ[2011]_10_082 end
    }
    
    /**
     * ��¼��ӡ��Ʊ��־
     * 
     * @param record
     * @see
     */
    public void insertInvoiceLog(InvoicePrintRecord record)
    {
        sqlMapClientTemplate.insert("charge.insertInvoiceLog", record);
    }
    
    /**
     * ��ȡ�ɷ���־OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID()
    {
        return (String)sqlMapClientTemplate.queryForObject("charge.getChargeLogOID");
    }
    
    /**
     * �����ظ��ɷѴ�����־
     * 
     * @param cashFeeErrorInfo
     * @see [�ࡢ��#��������#��Ա]
     */
    public void insertFeeErrorLog(CashFeeErrorInfoVO cashFeeErrorInfo)
    {
        sqlMapClientTemplate.insert("chargeHub.insertFeeErrorLog", cashFeeErrorInfo);
    }
    /**
     * �����û��Ľ������ͣ������ѽ���ѯ���ʺ��û��ĳ齱�
     * ��������ϸ������
     * @param [type] [��������]
     * @param [���ѽ��] [mcount]
     * @return �齱�Id
     * @exception/throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     * @depreced
     * @remark create yKF73963 ��2012-11-09�� OR_HUB_201209_706  ��������-�齱ƽ̨-�齱�ӿڸ��� 
     */
    public String getActId(String type,String mcount){
        SheExtractprizeRuleVO sheExtractprizeRuleVO=new SheExtractprizeRuleVO();
        sheExtractprizeRuleVO.setRectype(type);
        List<SheExtractprizeRuleVO> list= sqlMapClientTemplate.queryForList("chargeHub.getActidsByType1",sheExtractprizeRuleVO);
        for(SheExtractprizeRuleVO vo:list){
            String[] steps=vo.getStep().trim().split("-");
            Double pDouble=Double.parseDouble(steps[0]);
            Double lDouble=Double.parseDouble(steps[1]);
            Double tDouble=Double.parseDouble(mcount);
            
           if(tDouble>pDouble&&tDouble<=lDouble){
               
               return vo.getActid();
           }
        }
        
       
        return null;
    }
}
