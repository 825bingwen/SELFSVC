
package com.gmcc.boss.selfsvc.charge.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.gmcc.boss.selfsvc.charge.dao.ChargeDaoImpl;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.CashDetailLogPO;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;
/**
 * 
 * <充值日志记录>
 * <功能详细描述>
 * 
 * @author  sWX219697
 * @version  [版本号, Mar 19, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChargeServiceImpl implements ChargeService
{
    private ChargeDaoImpl chargeDaoImpl;
    
	/**
	 * <更新交费状态>
	 * <功能详细描述>
	 * @param cardChargeLogVO
	 * @see [类、类#方法、类#成员]
	 */
    public void updateChargeStatus(CardChargeLogVO cardChargeLogVO)
    {
    	chargeDaoImpl.updateChargeStatus(cardChargeLogVO);
    }
    
    /**
     * 获取缴费日志OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID()
    {
        return chargeDaoImpl.getChargeLogOID();
    }
    
    /**
     * <添加交费日志>
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void addChargeLog(CardChargeLogVO chargeLog)
    {
    	chargeDaoImpl.addChargeLog(chargeLog);
    }
    
    /**
     * <银联扣款后更新交费日志>
     * <功能详细描述>
     * @param chargeLog
     * @see [类、类#方法、类#成员]
     */
    public void updateCardChargeLog(CardChargeLogVO chargeLog)
    {
        // 设置修改时间
        chargeLog.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
        
    	chargeDaoImpl.updateCardChargeLog(chargeLog);
    }
    
    /**
     * <生成交易流水序列>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public String getNonlocalChargeID()
    {
    	return chargeDaoImpl.getNonlocalChargeID();
    }
    
    /**
     * <查询手机号码归属地>
     * <功能详细描述>
     * @param servernumber 手机号
     * @param provinceCode 省份编码
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-3-31 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
     */
    public String qryServRegion(String servnumber, String provinceCode)
    {
        CardChargeLogVO chargeLog = new CardChargeLogVO();
     
        // 手机号码
        chargeLog.setServnumber(servnumber);
        
        // 省份编码
        chargeLog.setProvinceCode(provinceCode);
        
        // 查询手机号码归属地
        List<String> servRegionLists = chargeDaoImpl.qryServRegion(chargeLog);
        
        if(null != servRegionLists && servRegionLists.size() > 0)
        {
            return servRegionLists.get(0);
        }
        else
        {
            throw new ReceptionException("对不起，没有查询到手机号码的归属地！");
        }
        
    }
    
    /**
     * <新增现金缴费日志>
     * @param servnumber 手机号码
     * @param tMoney 投币金额 分
     * @param terminalSeq 交费流水
     * @param acceptType 受理类型
     * @param servRegion 手机号码归属地
     * @param status 交费状态：00:初始状态，01:投币成功或银联交费成功的临时状态，10:扣款成功，交费失败，11:交费成功
     * @param description 描述信息
     * @param recType 业务类型
     * @param bankno 银行号
     * @param provinceCode
     * @param TerminalInfoPO
     * @return chargeLogOID 日志序列号
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-3-23 15:33:00 OR_NX_201501_1030 跨省异地缴费
     */
    public String addCashLog(String servnumber, String tMoney, String terminalSeq, String acceptType, 
            String servRegion, String status, String description, String recType, String bankno, 
            String provinceCode, TerminalInfoPO termInfo)
    {
        //记录现金缴费日志
        CardChargeLogVO cashChargeLog = new CardChargeLogVO();
        
        //日志序列id
        String chargeLogOID = chargeDaoImpl.getChargeLogOID();
        cashChargeLog.setChargeLogOID(chargeLogOID);
        
        //记录终端信息
        cashChargeLog.setRegion(termInfo.getRegion());
        cashChargeLog.setTermID(termInfo.getTermid());
        cashChargeLog.setOperID(termInfo.getOperid());
        cashChargeLog.setOrgID(termInfo.getOrgid());
        
        //手机号码
        cashChargeLog.setServnumber(servnumber);
        
        //缴费方式
        cashChargeLog.setPayType(Constants.PAYBYMONEY);
        
        //投币金额,单位：分
        cashChargeLog.setFee(tMoney);
        
        if (StringUtils.isNotBlank(terminalSeq))
        {
            terminalSeq = termInfo.getTermid() + terminalSeq;
            if (terminalSeq.length() > 20)
            {
                terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
            }
        }
        else
        {
            terminalSeq = "";
        }
        
        //交费流水号
        cashChargeLog.setTerminalSeq(terminalSeq);
        
        //交易时间
        cashChargeLog.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
        
        //受理类型
        cashChargeLog.setAcceptType(acceptType);
        
        //手机号码归属地
        cashChargeLog.setServRegion(servRegion);
        
        //交费状态
        cashChargeLog.setStatus(status);
        
        //描述信息
        cashChargeLog.setDescription(description);
        
        //业务类型
        cashChargeLog.setRecType(recType);
        cashChargeLog.setBankno(bankno);
        
        //省份编码
        cashChargeLog.setProvinceCode(provinceCode);
        
        chargeDaoImpl.addChargeLog(cashChargeLog);
        return chargeLogOID;
    }
    
    /**
     * <添加银联交费日志>
     * @param servnumber
     * @param tMoney
     * @param cardnumber
     * @param terminalSeq
     * @param status
     * @param description
     * @param servRegion
     * @param posNum
     * @param batchNumBeforeKoukuan
     * @param acceptType
     * @param recType
     * @param provinceCode
     *  @param TerminalInfoPO
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-3-23 15:33:00 OR_NX_201501_1030 跨省异地缴费
     */
    public String addCardLog(String servnumber, String tMoney, String cardnumber, String terminalSeq, 
            String status, String description, String servRegion, String posNum, String batchNumBeforeKoukuan,
            String acceptType, String recType, String provinceCode, TerminalInfoPO termInfo)
    {
        CardChargeLogVO chargeLog = new CardChargeLogVO();
        String logOID = chargeDaoImpl.getChargeLogOID();
        chargeLog.setChargeLogOID(logOID);
        
        chargeLog.setRegion(termInfo.getRegion());
        chargeLog.setTermID(termInfo.getTermid());
        chargeLog.setOrgID(termInfo.getOrgid());
        chargeLog.setOperID(termInfo.getOperid());
        
        chargeLog.setServnumber(servnumber);
        chargeLog.setPayType(Constants.PAYBYUNIONCARD);
        chargeLog.setFee(tMoney);
        
        //往数据库里存入加密后的银联卡号
        chargeLog.setCardnumber(cardnumber);

        chargeLog.setTerminalSeq(terminalSeq);
        
        chargeLog.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
        
        chargeLog.setStatus(status);
        chargeLog.setDescription(description);
        chargeLog.setAcceptType(acceptType);
        chargeLog.setServRegion(servRegion);
        
        chargeLog.setPosNum(posNum);
        chargeLog.setBatchNumBeforeKoukuan(batchNumBeforeKoukuan);
        
        chargeLog.setRecType(recType);
        chargeLog.setProvinceCode(provinceCode);
        
        // 插入缴费日志
        chargeDaoImpl.addChargeLog(chargeLog);
        return logOID;
    }
    
    /**
     * <银联扣款成功后更新交费日志>
     * @param chargeLogOID 日志id
     * @param unionpayuser 商户编号
     * @param unionpaycode 终端编号
     * @param batchnum 批次号
     * @param authorizationcode 授权码
     * @param businessreferencenum 交易参考号
     * @param unionpaytime 交易时间
     * @param vouchernum 凭证号
     * @param unionpayfee 交易金额
     * @param initPosResCode 银联返回码 湖北用
     * @param cardnumber 银行卡号
     * @param terminalSeq 交易流水号
     * @param bankno 银行号，宁夏需要
     * @param busitype 消费类型
     * @param TerminalInfoPO 
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-3-23 OR_NX_201501_1030 跨省异地缴费
     */
    public void updateCardLog(String chargeLogOID, String unionpayuser, String unionpaycode, String batchnum,
            String authorizationcode, String businessreferencenum, String unionpaytime, String vouchernum, 
            String unionpayfee, String initPosResCode,String cardnumber,String terminalSeq, 
            String bankno, String busitype, TerminalInfoPO termInfo)
    {
        // 组装日志对象
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        cardChargeLogVO.setChargeLogOID(chargeLogOID);
        cardChargeLogVO.setUnionpayuser(unionpayuser);
        cardChargeLogVO.setUnionpaycode(unionpaycode);
        
        cardChargeLogVO.setBusiType(busitype);
        
        cardChargeLogVO.setBatchnum(batchnum);
        cardChargeLogVO.setAuthorizationcode(authorizationcode);
        cardChargeLogVO.setBusinessreferencenum(businessreferencenum);
        cardChargeLogVO.setUnionpaytime(unionpaytime);
        cardChargeLogVO.setVouchernum(vouchernum);
        
        cardChargeLogVO.setUnionpayfee(unionpayfee);
        
        cardChargeLogVO.setStatus("01");
        cardChargeLogVO.setDescription("银联扣款成功的临时状态");
        
        cardChargeLogVO.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
        
        cardChargeLogVO.setPosResCode(initPosResCode);
        
        cardChargeLogVO.setCardnumber(cardnumber);

        cardChargeLogVO.setTerminalSeq(terminalSeq);
        
        //终端机region，提高更新效率
        cardChargeLogVO.setRegion(termInfo.getRegion());
        
        //银行号，宁夏用
        cardChargeLogVO.setBankno(bankno);
        
        chargeDaoImpl.updateCardChargeLog(cardChargeLogVO);
    }
    
    /**
     * <交费后日志状态更新>
     * <功能详细描述>
     * @param chargeLogOID 日志id
     * @param status 交易状态：00:初始状态，01:投币成功或银联交费成功的临时状态，10:扣款成功，交费失败，11:交费成功
     * @param description 描述信息
     * @param dealnum 交易流水号
     * @param unionRetCode 银联返回码，用于记录错误日志时
     * @param unionpayuser 商户编码，湖北记录异常日志时用到
     * @param unionpaycode 终端编码，湖北记录异常日志时用到
     * @param TerminalInfoPO
     * @see [类、类#方法、类#成员]
     * @return
     * @remark create by sWX219697 2015-3-23 OR_NX_201501_1030 跨省异地缴费
     */
    public String updateChargeStatus(String chargeLogOID, String status, String description, 
            String dealnum, String unionRetCode, String unionpayuser, String unionpaycode, TerminalInfoPO termInfo)
    {
        CardChargeLogVO chargeLogVO = new CardChargeLogVO();
        
        //交费日志id
        chargeLogVO.setChargeLogOID(chargeLogOID);
        
        //交费状态
        chargeLogVO.setStatus(status);
        
        //描述信息
        chargeLogVO.setDescription(description);
        
        //交易流水号
        chargeLogVO.setDealnum(dealnum);
        
        Date date = new Date();
        
        String dealTime = CommonUtil.dateToString(date, "yyyy-MM-dd HH:mm:ss");
        
        //交易时间
        chargeLogVO.setRecdate(CommonUtil.dateToString(date, "yyyyMMddHHmmss"));
        
        //银联返回码
        chargeLogVO.setPosResCode(unionRetCode);
        
        //银联商家编码 湖北更新异常日志时用到
        chargeLogVO.setUnionpayuser(unionpayuser);
        
        //终端编码，湖北记录异常日志时用到
        chargeLogVO.setUnionpaycode(unionpaycode);
        
        //终端机region，提高更新效率
        chargeLogVO.setRegion(termInfo.getRegion());
        
        chargeDaoImpl.updateChargeStatus(chargeLogVO);
        
        return dealTime;
    }
    
    /**
     * <根据规则生成跨区交费的交易流水号>
     * <省BOSS的编码规则：3位省代码+8位业务编码（BIPCode）+14位组包时间YYYYMMDDHH24MMSS+6位流水号（定长），
     * 序号从000001开始，增量步长为1>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getNonlocalChargeSeq()
    {
        String nonlocalChargeID = chargeDaoImpl.getNonlocalChargeID();
        
        while(nonlocalChargeID.length() < 6)
        {
            nonlocalChargeID = "0" + nonlocalChargeID;
        }
        
        return CommonUtil.getParamValue("SH_CURRPROVINCE_CODE") + CommonUtil.getParamValue("SH_NonlocalChargeBIPCode")
                + CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss") + nonlocalChargeID;
    }

    /**
     * 添加sh_charge_log日志
     * @param chargeLogVO
     * @param fee
     * @param recType
     * @param termInfo
     * @return
     */
    public CardChargeLogVO addChargeLog(CardChargeLogVO chargeLogVO, String fee, String recType,TerminalInfoPO termInfo)
    {
        // 投币金额处理
        if (StringUtils.isBlank(fee) || "0".equals(fee.trim()))
        {
            fee = "0";
            chargeLogVO.setStatus(Constants.CHARGE_ERROR);
        }
        else
        {
            chargeLogVO.setStatus(Constants.PAYSUCCESS_CHARGEERROR);
        }
        
        // 日志序列id
        String chargeLogOID = chargeDaoImpl.getChargeLogOID();
        chargeLogVO.setChargeLogOID(chargeLogOID);
        
        // 记录终端信息
        chargeLogVO.setTermID(termInfo.getTermid());
        chargeLogVO.setRegion(termInfo.getRegion());
        chargeLogVO.setOperID(termInfo.getOperid());
        chargeLogVO.setOrgID(termInfo.getOrgid());
        
        // 投币金额,单位：分
        chargeLogVO.setFee(fee);
        
        Date date = new Date();
        
        String dealTime = CommonUtil.dateToString(date, "yyyy-MM-dd HH:mm:ss");
        
        // 交易时间
        chargeLogVO.setRecdate(CommonUtil.dateToString(date, "yyyyMMddHHmmss"));
        
        // 业务类型
        chargeLogVO.setRecType(recType);
        
        // 记录现金缴费日志
        if(Constants.PAYBYMONEY.equals(chargeLogVO.getPayType()))
        {
            // 终端交费流水号
            if (StringUtils.isNotBlank(chargeLogVO.getTerminalSeq()))
            {
                String terminalSeq = termInfo.getTermid() + chargeLogVO.getTerminalSeq();
                if (terminalSeq.length() > 20)
                {
                    terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
                }
                chargeLogVO.setTerminalSeq(terminalSeq);
            }
            
            //描述信息
            chargeLogVO.setDescription("缴费之前记录投币日志");
        }
        // 添加银联扣款前日志
        else if(Constants.PAYBYUNIONCARD.equals(chargeLogVO.getPayType()))
        {
            // 描述信息
            chargeLogVO.setDescription("扣款之前记录投币日志");
            
        }
        else
        {
            throw new ReceptionException("对不起，记录交费日志异常，请联系营业厅管理员！");
        }
        
        // 添加交费日志
        chargeDaoImpl.addChargeLog(chargeLogVO);
        
        // 处理时间
        chargeLogVO.setRecdate(dealTime);
        
        return chargeLogVO;

    }
    
    /**
     * 二次更新交费日志
     * @param chargeLogVO
     * @param status
     * @param dealNum
     * @param termInfo
     * @param description
     * @return
     */
    public CardChargeLogVO updateCardLog(CardChargeLogVO chargeLogVO, String status,String dealNum, 
            TerminalInfoPO termInfo)
    {
        // 交费状态
        chargeLogVO.setStatus(status);
        
        // BOSS交费流水
        chargeLogVO.setDealnum(dealNum);
        
        //终端机region，提高更新效率
        chargeLogVO.setRegion(termInfo.getRegion());
        
        // 设置修改时间
        chargeLogVO.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
        
        // 用户刷卡卡号
        chargeLogVO.setCardnumber(EncryptDecryptUtil.encryptAesPwd(chargeLogVO.getCardnumber()));
        
        // 银联实际扣款金额，单位（分）
        chargeLogVO.setUnionpayfee(CommonUtil.formatNumberStr(chargeLogVO.getUnionpayfee()));
      
        // 银联实际扣款时间
        String unionpaytime = new SimpleDateFormat("yyyy").format(new Date()) + chargeLogVO.getUnionpaytime();

        chargeLogVO.setUnionpaytime(unionpaytime);
        
        // 更新银联卡缴费
        chargeDaoImpl.updateCardChargeLog(chargeLogVO);
        
        return chargeLogVO;
    }
    
    /**
     * 记录用户交费日志之前先记录用户的投币情况
     * @param termID
     * @param terminalSeq
     * @param servNumber
     * @param cashDetail
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-3-27 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
     */
    public void insertCashDetailInfoNX(String termID, String terminalSeq, String servNumber, String cashDetail)
    {
        // 用户投币的面额信息，如50;100，既投币两次，第一次50，第二次100
        if (StringUtils.isBlank(cashDetail))
        {
            return;
        }
        
        // 处理终端交易流水
        if(StringUtils.isNotBlank(terminalSeq))
        {
            terminalSeq = termID + terminalSeq;
            if (terminalSeq.length() > 20)
            {
                terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
            }
        }
        else
        {
            terminalSeq = "";
        }
   
        String[] cashes = cashDetail.split(";");
        
        CashDetailLogPO log = null;
        
        // 循环插入投币日志
        for (int i = 0; i < cashes.length; i++)
        {
            log = new CashDetailLogPO();
            
            // 手机号
            log.setServNum(servNumber);
            
            // 终端机ID
            log.setTermID(termID);
            
            // 终端流水
            log.setFormNum(terminalSeq);
            
            // 纸币金额
            log.setCashFee(cashes[i]);
            
            chargeDaoImpl.insertCashDetailInfo(log);
        }
    }

	public void setChargeDaoImpl(ChargeDaoImpl chargeDaoImpl) 
	{
		this.chargeDaoImpl = chargeDaoImpl;
	}
}
