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
     * 银联卡缴费记录日志
     * 
     * @param cardChargeLogVO 银行卡信息
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.insertChargeLog", cardChargeLogVO);
    }
    
    /**
     * 银联扣款成功之后，更新日志
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
     * 更新充值缴费记录
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
     * 记录打印发票日志
     * 
     * @param record
     * @see
     */
    public void insertInvoiceLog(InvoicePrintRecord record)
    {
        sqlMapClientTemplate.insert("charge.insertInvoiceLog", record);
    }
    
    /**
     * 获取缴费日志OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID()
    {
        return (String)sqlMapClientTemplate.queryForObject("charge.getChargeLogOID");
    }
    
    /**
     * 插入重复缴费错误日志
     * 
     * @param cashFeeErrorInfo
     * @see [类、类#方法、类#成员]
     */
    public void insertFeeErrorLog(CashFeeErrorInfoVO cashFeeErrorInfo)
    {
        sqlMapClientTemplate.insert("chargeHub.insertFeeErrorLog", cashFeeErrorInfo);
    }
    /**
     * 根据用户的交费类型，及交费金额，查询最适合用户的抽奖活动
     * 〈功能详细描述〉
     * @param [type] [交费类型]
     * @param [交费金额] [mcount]
     * @return 抽奖活动Id
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark create yKF73963 （2012-11-09） OR_HUB_201209_706  电子渠道-抽奖平台-抽奖接口改造 
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
