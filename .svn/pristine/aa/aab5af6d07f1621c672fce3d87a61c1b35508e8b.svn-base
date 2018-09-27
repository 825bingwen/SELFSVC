
package com.gmcc.boss.selfsvc.charge.service;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

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
public interface ChargeService
{

	/**
	 * <更新交费状态>
	 * <功能详细描述>
	 * @param cardChargeLogVO
	 * @see [类、类#方法、类#成员]
	 */
    public void updateChargeStatus(CardChargeLogVO cardChargeLogVO);
    
    /**
     * 获取缴费日志OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID();
    
    /**
     * <添加交费日志>
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void addChargeLog(CardChargeLogVO chargeLog);
    
    /**
     * <银联扣款后更新交费日志>
     * <功能详细描述>
     * @param chargeLog
     * @see [类、类#方法、类#成员]
     */
    public void updateCardChargeLog(CardChargeLogVO chargeLog);
    
    /**
     * <生成交易流水序列>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public String getNonlocalChargeID();
    
    /**
     * <查询手机号码归属地>
     * <功能详细描述>
     * @param servnumber 手机号
     * @param provinceCode 省份编码
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String qryServRegion(String servnumber, String provinceCode);
    
    /**
     * <新增现金缴费日志>
     * <功能详细描述>
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
     *  @param TerminalInfoPO
     * @return chargeLogOID 日志序列号
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-3-23 15:33:00 OR_NX_201501_1030 跨省异地缴费
     */
    public String addCashLog(String servnumber, String tMoney, String terminalSeq, String acceptType, 
            String servRegion, String status, String description,String recType, String bankno, 
            String provinceCode, TerminalInfoPO termInfo);
    
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
            String acceptType, String recType, String provinceCode, TerminalInfoPO termInfo);
    
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
            String unionpayfee, String initPosResCode,String cardnumber,String terminalSeq, String bankno, 
            String busitype, TerminalInfoPO termInfo);
    
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
    public void insertCashDetailInfoNX(String termID, String terminalSeq, String servNumber, String cashDetail);
    
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
            String dealnum, String unionRetCode, String unionpayuser, String unionpaycode, TerminalInfoPO termInfo);
    
    /**
     * <根据规则生成跨区交费的交易流水号>
     * <省BOSS的编码规则：3位省代码+8位业务编码（BIPCode）+14位组包时间YYYYMMDDHH24MMSS+6位流水号（定长），
     * 序号从000001开始，增量步长为1>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getNonlocalChargeSeq();
    
    /**
     * 添加交费记录
     * @param chargeLogVO
     * @param fee
     * @param recType
     * @param termInfo
     * @return
     * @see [类、类#方法、类#成员]
     * create by hWX5316476 2015-3-27 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
     */
    public CardChargeLogVO addChargeLog(CardChargeLogVO chargeLogVO, String fee, String recType,TerminalInfoPO termInfo);
    
    /**
     * 投币或扣款成功后更新交费记录
     * @param chargeLogVO
     * @param status
     * @param dealNum
     * @param termInfo
     * @param description
     * @return
     * @see [类、类#方法、类#成员]
     * create by hWX5316476 2015-3-27 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
     */
    public CardChargeLogVO updateCardLog(CardChargeLogVO chargeLogVO, String status,String dealNum, 
            TerminalInfoPO termInfo);
    
}
