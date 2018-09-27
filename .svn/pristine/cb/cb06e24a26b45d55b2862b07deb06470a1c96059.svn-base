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
     * 山东套餐信息查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryComboInfo(Map map);
    
    /**
     * 山东月账单查询
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryMonthBill(Map map);
    
    /**
     * 山东余额查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap queryBalance(Map<String, String> paramMap);
    
    /**
     * 话费充值账户信息查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryfeeChargeAccount(Map map);
    
    /**
     * 话费充值
     * 
     * @param map
     * @return
     */
    public ReturnWrap chargeCommit(Map<String,String> map);
    
    /**
     * 不校验密码，直接获取用户信息
     * 
     * @param map
     * @return
     * @see
     */
    public ReturnWrap getUserStatus(Map map);
    
    
    /**
     * 获取账单寄送查询的返回信息
     * 
     * @param map
     * @return 
     * @see
     */
    public ReturnWrap getMailBillSendInfo(Map map);	
	
    /**
     * 撤销原邮箱寄送地址
     * 
     * @param map
     * @return 
     * @see
     */
    public ReturnWrap getCancelCaseInfo(Map map);
    
    /**
     * 开通邮箱寄送
     * 
     * @param map
     * @return 
     * @see
     */
	public ReturnWrap getOpenBillMailInfo(Map map);
	
	/**
     * 查询用户是否已开通手机邮箱
     * @param map
     * @return
     */
    public ReturnWrap qrymailBox(Map map);
    
    /**
     * 开通139免费邮箱
     */
    public ReturnWrap add139Mail(Map map);
    
    // add begin cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    /**
     * 账单备注查询
     */
    public ReturnWrap queryBillAddInfo(Map map);
    // add end cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    
    /**
     * 根据手机号码查询客户信息
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap getCustinfo(Map map);
    
    /**
     * 查询月账信息_新版
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryMonthBill_new(Map map);

    /**
     * 校验用户是否实名注册
     * 
     * @param bindBankCardPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap checkFactNameRegist(Map paramMap);
    
    /**
     * 自动交费操作接口
     * 
     * @param msgHeader
     * @param ncode
     * @param oprtype
     * @param trigamt 触发金额 分
     * @param drawamt 自动充值金额 分     
     * @return
     * @see [类、类#方法、类#成员]
     * @modify yWX163692 2013年11月19日 OR_SD_201309_940 易充值二阶段，解约新增自动交费判断流程  
     * @remark modify by sWX219697 2014-12-2 15:33:07 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
     */
    public ReturnWrap autoFeeSet(MsgHeaderPO msgHeader, String oprtype, String trigamt, String drawamt);
    
    /**
     * 易充值签约之前调用接口检查是否满足产品开通条件
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap checkProCondition(Map map);
    
    // add begin zKF69263 2014/05/09 R003C14L05n01 OR_huawei_201404_1109
    /**
     * 查询未打印的发票记录数据
     * 
     * @param map
     * @return ReturnWrap
     */
    public ReturnWrap invoiceList(Map map);
    
    /**
     * 查询要打印的发票打印项数据
     * 
     * @param map
     * @return ReturnWrap
     */
    public ReturnWrap invoiceData(Map map);
    // add end zKF69263 2014/05/09 R003C14L05n01 OR_huawei_201404_1109
    
    /**
     * 话费充值账户应缴费用查询
     * @param paramMap
     * @remark  add by hWX5316476 2014-03-12 OR_SD_201312_90_山东_自助终端交费应交话费显示的优化需求
     * @return ReturnWrap
     */
    public ReturnWrap qryRetcharge(Map paramMap);
    
    /**
     * 账单邮件下发接口
     * @param map 
     * @remark  create sWX219697 2014-04-29 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
     * @return ReturnWrap
     */
    public ReturnWrap sendBillMail(Map<String,String> map);

    /**
     * 账期查询数据
     * @param paramMap
     * @remark add by wWX217192 on 20140504 for OR_huawei_201404_1108 营业厅全业务流程优化-业务分流(自助终端)_打印月结发票
     * @return
     */
    public ReturnWrap qryBillCycle(Map map);
    
    /**
     * 月结发票数据查询
     * @param paramMap
     * @remark add by wWX217192 on 20140504 for OR_huawei_201404_1108 营业厅全业务流程优化-业务分流(自助终端)_打印月结发票
     * @return
     */
    public ReturnWrap qryMonthInvoice(Map map);

    /**
     * <查询代理商空充账户信息>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryAgentInfo(Map<String,String> map);
    
    /**
     * <代理商缴费前记录>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap beforeAgentCharge(Map<String,String> map);
    
    /**
     * <代理商充值>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap agentCharge(Map<String,String> map);

    /**
     * 查询可变更的主体产品信息
     * @param paramMap
     * @return ReturnWrap
     * @remark add by jWX216858 2014-5-7 OR_huawei_201404_1116_山东_营业厅全业务流程优化-业务分流(自助终端)_转套餐产品
     */
    public ReturnWrap qryMainProdInfo(Map<String, String> paramMap);
    
    /**
     * 查询主体产品模板信息
     * @param paramMap
     * @return ReturnWrap
     * @remark add by jWX216858 2014-5-8 OR_huawei_201404_1116_山东_营业厅全业务流程优化-业务分流(自助终端)_转套餐产品
     */
    public ReturnWrap qryProdTemplateInfo(Map<String, String> paramMap);
    
    /**
	 * 执行主体产品变更
	 * @param paramMap
     * @return ReturnWrap
	 * @remark add by jWX216858 2014-5-8 OR_huawei_201404_1116_山东_营业厅全业务流程优化-业务分流(自助终端)_转套餐产品
	 */
    public ReturnWrap mainProdChangeRec(Map<String, String> paramMap);

    /**
     * 验证SIM卡
     * @param paramMap
     * @return ReturnWrap
     * @remark add by hWX5316476 2014-06-23 V200R003C10LG0601 OR_SD_201405_849_关于在营业厅增加实名制认证的功能
     */
    public ReturnWrap chkSIMCardNo(Map<String,String> paramMap);
    
    /**
     * 记录实名制认证受理日志
     * @param paramMap
     * @return ReturnWrap
     * @remark add by hWX5316476 2014-06-24 V200R003C10LG0601 OR_SD_201405_849_关于在营业厅增加实名制认证的功能
     * @remark modify gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
     */
    public ReturnWrap saveRealNameChkRecLog(Map<String,String> paramMap, Map<String,String> map);
    
    /**
     * 充值记录认证
     * @param paramMap
     * @return 
     * @remark add by hWX5316476 2014-06-24 V200R003C10LG0601 OR_SD_201405_849_关于在营业厅增加实名制认证的功能
     */
    public ReturnWrap chkChargeRecord(Map<String, Object> paramMap);
    
    /**
     * 通话记录验证
     * @param paramMap
     * @return
     * @remark add by hWX5316476 2014-06-24 V200R003C10LG0601 OR_SD_201405_849_关于在营业厅增加实名制认证的功能
     */
    public ReturnWrap chkCallRecord(Map<String, String> paramMap);
    
    /**
	 * 查询用户实名制登记标志
	 * @param 手机号码
	 * @param 终端信息
	 * @param 菜单信息
	 * @return 接口调用成功与否的标志位及接口返回信息
	 * @remark create wWX217192 2014-06-23 OR_huawei_201406_338
	 */
    public ReturnWrap qryRealNameType(Map<String, String> map);
    
    /**
	 * 生成短信验证随机码
	 * 
	 * @param map
	 * @return 接口返回信息
	 * @remark create wWX217192 2014-06-24 OR_huawei_201406_338
	 */
	public ReturnWrap getRandomPwd(Map<String, String> map);
	
	/**
	 * 短信随机密码验证
	 * 
	 * @param map
	 * @return 接口返回信息
	 * @remark create wWX217192 2014-06-25 OR_huawei_201406_338
	 */
	public ReturnWrap checkRandomPwd(Map<String, String> map);
	
	/**
	 * 个人密码验证接口
	 * 
	 * @param map
	 * @return 接口返回信息
	 * @remark create wWX217192 2014-06-25 OR_huawei_201406_338
	 */
	public ReturnWrap checkUserPwd(Map<String, String> map);
	
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param msgHeader 消息头部
     * @param orgId 组织机构id
     * @param agentAccount 资金账户编码
     * @param subjectId 科目编码
     * @param tMoney 交费金额 分
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-8-23 10:43:09 OR_huawei_201408_657_自助终端代理商资金账户充值功能优化
     */
	public ReturnWrap checkBeforeAgentCharge(MsgHeaderPO msgHeader, String orgId, String agentAccount, 
			String subjectId, String tMoney);
	
	/**
	 * <查询用户的付费类型>
	 * <功能详细描述>
	 * @param msgHeader 消息头部
	 * @return
	 * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-10-7 14:27:18 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
	 */
	public ReturnWrap qrySubsPrepayType(MsgHeaderPO msgHeader);
	
	/**
	 * 语音通话受理
	 * @param msgHeader 报文头信息
	 * @param nCode nCode
	 * @return
	 * @remark create by jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_山东_关于自助终端产品变更功能添加4G自选套餐以及修改GPRS和4G互斥的功能（全业务流程优化）
	 */
	public ReturnWrap voiceCallRec(MsgHeaderPO msgHeader, String nCode);
	
	/**
	 * 上网流量受理
	 * @param header 消息头
	 * @param productset 增值产品串(产品包,增值产品,优惠;产品包,增值产品,优惠;)
	 * @return
	 * @remark create by jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_山东_关于自助终端产品变更功能添加4G自选套餐以及修改GPRS和4G互斥的功能（全业务流程优化）
	 */
	public ReturnWrap gprsWlanRec(MsgHeaderPO header, String productset);
	
	/**
	 * <查询用户副号码列表>
	 * <功能详细描述>
	 * @param msgHeader
	 * @param ncode
	 * @param stype
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @remark sWX219697 2014-12-2 19:36:41 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
	 */
	public ReturnWrap viceNumberQry(MsgHeaderPO msgHeader, String ncode, String stype);
	
	/**
	 * <新增、删除易充值副号码>
	 * <功能详细描述>
	 * @param msgHeader
	 * @param viceArray 副号码数组
	 * @param opertype 操作类型，1：新增
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @remark sWX219697 2014-12-4 12:00:03 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
	 */
	public ReturnWrap viceNumberSet(MsgHeaderPO msgHeader, String[] viceArray, String opertype);
	
	/**
	 * 查询当前用户是否签约和包易充值
	 * @param headerPo 消息头
	 * @return 用户签约信息
	 * @remark create by wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap checkHeBao(MsgHeaderPO headerPo, String bankCardNum);
	
	/**
	 * 和包易充值平台发送短信随机密码
	 * @param headerPo 请求报文头
	 * @param smsType 验证码类型
	 * @param AGRNO 协议号
	 * @return 和包易充值平台的返回报文
	 * @remark create by wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap sendHeBaoRandom(MsgHeaderPO headerPo, String smsType, BindBankCardPO cardPo);
	
	/**
	 * 和包易充值签约
	 * @param headerPo
	 * @param cardPo
	 * @param smsCode
	 * @return
	 * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap signHeBao(MsgHeaderPO headerPo, BindBankCardPO cardPo, String smsCode);
	
	/**
	 * 和包易充值解约接口
	 * @param headerPo
	 * @param cardPo
	 * @param smsCode
	 * @return
	 * @remark create by wWX217192 2014-11-29 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap unsignHeBao(MsgHeaderPO headerPo, BindBankCardPO cardPo, String smsCode);
	
	/**
	 * 和包易充值自动交费功能设置
	 * @param headerPo
	 * @param oprType
	 * @param trigAmt
	 * @param drawAmt
	 * @param bankId
	 * @return
	 * @remark create by wWX217192 2014-12-10 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap setHeBaoAutoValue(MsgHeaderPO headerPo, String oprType, BankCardInfoPO bankCardInfoPO);
	
	/**
     * <校验用户身份证信息与手机号是否相符>
     * <功能详细描述>
     * @param msgHeader
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap checkReissueIdCard(MsgHeaderPO msgHeader, String idCardNo);
    
    /**
     * <校验用户补卡次数>
     * <功能详细描述>
     * @param msgHeader
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap checkReissueNum(MsgHeaderPO msgHeader);
    
    /**
     * <补卡算费>
     * <功能详细描述>
     * @param msgHeader
     * @param iccid
     * @param blankCardNum 空白卡卡号
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap countReissueFee(MsgHeaderPO msgHeader, String iccid, String blankCardNum);
    
    /**
     * <补卡提交>
     * <功能详细描述>
     * @param msgHeader
     * @param recFee 应缴费用
     * @param payType 支付方式
     * @param simInfo sim卡信息
     * @param bankNo 银行编号
     * @param bankNbr 银行缴费流水号
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap reissueCommit(MsgHeaderPO msgHeader, String recFee, String payType, String blankno,
        SimInfoPO simInfo, String bankNo, String bankNbr);
    
    /**
     * <查询用户信息>
     * <功能详细描述>
     * @param msgHeader
     * @param region
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap getSubscriberByTel(MsgHeaderPO msgHeader,String region);
	
	/**
	 * 业务有效性校验
	 * 
	 * @param msgHeader 消息头
	 * @return true：可以继续办理业务，false：终止办理业务
	 * @see [类、类#方法、类#成员]
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap checkRecValid(MsgHeaderPO msgHeader);
	
	/**
	 * 查询用户已经存在的档次
	 * @param msgHeader 信息头
	 * @return
	 * @remark create by jWX216858 2014-11-29 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap qrySubsActLevelList(MsgHeaderPO msgHeader);
	
	/**
	 * 查询奖品列表
	 * @param msgHeader 消息头
	 * @param actLevelId 档次编码
	 * @param activityId 活动编码
	 * @return
	 * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap qryRewardList(MsgHeaderPO msgHeader, String actLevelId, String activityId);
	
	/**
	 * 查询活动费用
	 * @param msgHeader 消息头
	 * @param actid 活动编码
	 * @param levelid 档次编码
	 * @param rewardId 奖品编码
	 * @return
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap qryActivityFee(MsgHeaderPO msgHeader, String actid, String levelid, String rewardId);

	/**
	 * 预存有礼受理
	 * 
	 * @param msgHeader 消息头
	 * @param prestoredRewardPO 入参 
	 * @param bankNo 银行编号
     * @param bankNbr 银行缴费流水号
	 * @return
	 * @remark create by jWX216858 2014-12-08 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap recRewardCommit(MsgHeaderPO msgHeader,PrestoredRewardPO prestoredRewardPO, String bankNo,
        String bankNbr);
	
	/** 
     * 业务办理前记录业务费用信息
     * 
     * @param msgHeader 报文请求头
     * @param bankNo 银行号
     * @param acceptType 业务类型(开户：ZZKH 补卡：ZZBK 预存赠：ZZHD)
     * @param bankNbr 唯一流水(和agentcharge表的AGENTFORMNUM字段保持一致) ，终端机返回的termseq
     * @param amount 订单金额
     * @param prestoredRewardPO 活动信息
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2015-05-07 OR_SD_201503_333_SD_自助终端活动受理预存赠送
     */
    public ReturnWrap writeNetFeeInfo(MsgHeaderPO msgHeader, String bankNo, String acceptType,
        String bankNbr, String amount, PrestoredRewardPO prestoredRewardPO);

	/**
	 * 查询营销方案费用和用户预存费用
	 * @param msgHeader 消息头
	 * @param recoid 受理流水
	 * @param totalFee 用户存入的费用
	 * @return
	 * @remark create by jWX216858 2014-12-08 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap qryRecFeeAndPreFee(MsgHeaderPO msgHeader, String recoid, String totalFee);
	
	/**
     * 办理特惠业务包
     * @param msgHeader
     * @param privServPackPO
     * @return
     * @remark create by hWX5316476 2014-12-24 OR_SD_201410_482_SD_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
     */
    public ReturnWrap privServPackRec(MsgHeaderPO msgHeader,PrivServPackPO privServPackPO);
    
    /**
     * 根据主体产品Id获取主体产品信息
     * @param msgHeader
     * @param prodid
     * @param type 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-1-9 OR_SD_201411_411_SD_关于自助终端产品受理功能优化的需求
     */
    public ReturnWrap qryProdInfoById(MsgHeaderPO msgHeader,String prodid,String type);
    
    /**
     * 组内档次转换
     * @param msgHeader
     * @param ncode NCODE
     * @param stype 操作类型 ADD 增加 DEL 删除 MOD 修改 QRY 查询
     * @param preparebusi 预受理 固定传BsacNBSubmit
     * @param premutex 是否将互斥或依赖的进行关联删除
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-1-9 OR_SD_201411_411_SD_关于自助终端产品受理功能优化的需求
     */
    public ReturnWrap chgLevelInGroup(MsgHeaderPO msgHeader, String ncode, String stype, String preparebusi, String premutex);
    
    /** 
     * 开户时证件号码校验
     * 
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param certType 证件类型
     * @param certId 证件号码
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap chkCertSubs(MsgHeaderPO msgHeader, String certType, String certId);
    
    /** 
     * 依据选号规则查询手机号码列表
     * 
     * @param msgHeader 报文头信息
     * @param orgId 组织机构
     * @param fitmod 选号规则
     * @param mainProdid 主体产品ID
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-4 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap qryTelNumberListByRule(MsgHeaderPO msgHeader, String orgId, String fitmod, String mainProdid);

    /**
     * 号码资源暂选接口
     * 
     * @param msgHeader 消息头
     * @param telnum 手机号
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap telNumTmpPick(MsgHeaderPO msgHeader, String telnum);

	/**
     * 校验空白卡资源是否可用
     * 
     * @param msgHeader 消息头
     * @param termInfo 终端机信息
     * @param blankNo 空白卡号
     * @param prodid 主体产品
     * @param recType 受理类型，开户Install，补卡ChangeEnum
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap chkBlankNo(MsgHeaderPO msgHeader, TerminalInfoPO termInfo, String blankNo, String prodid, String recType);
	
	/**
     * 校验空白卡是否是预置空卡
     * 
     * @param msgHeader 消息头
     * @param blankNo 空白卡序列号
     * @param telNum 手机号
     * @param recType 受理类型，开户Install，补卡ChangeEnum
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap chkPreSetBlankCard(MsgHeaderPO msgHeader, String blankNo, String telNum, String recType);

	/**
     * 开户算费
     * 
     * @param msgHeader 消息头
     * @param tpltTempletPO 模板po
     * @param simInfoPO sim卡po
     * @param blankno 空白卡序列号
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap reckonRecFee(MsgHeaderPO msgHeader, ProdTempletPO tpltTempletPO, SimInfoPO simInfoPO, String blankno);
	
	/**
     * 申请写卡，包括空白卡资源暂选和获取加密数据
     * 
     * @param msgHeader 消息头
     * @param blankno 空白卡序列号
     * @param recType 受理类型，开户Install，补卡ChangeEnum
     * @param region region
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap getEncryptedData(MsgHeaderPO msgHeader, String blankNo, String recType, String region);

	/**
     * 作废卡，写卡失败时调用
     * 
     * @param msgHeader 消息头
     * @param blankno 空白卡序列号
     * @param simInfoPO sim卡信息
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap updateWriteCardResult(MsgHeaderPO msgHeader, String blankno, SimInfoPO simInfoPO);

	/**
     * 开户提交
     * 
     * @param msgHeader 消息头
     * @param simInfoPO sim卡信息
     * @param tpltTempletPO 模板信息
     * @param idCardPO 用户身份信息
     * @param totalfee 总费用
     * @param passwd 服务密码
     * @param telnum 手机号码
     * @param paytype 支付方式 1：银联卡
     * @param bankNo 银行编号
     * @param bankNbr 银行缴费流水号
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-15 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap terminalInstall(MsgHeaderPO msgHeader, SimInfoPO simInfoPO, ProdTempletPO tpltTempletPO,
        IdCardPO idCardPO, String totalfee, String passwd, String telnum, String bankNo, String bankNbr);
	

    
    /**
     * <查询用户已预约的号码列表>
     * <功能详细描述>
     * @param msgHeader
     * @param Document doc
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-6-9 OR_SD_201505_489_开户中增加预约选号功能
     */
    public ReturnWrap qryBookedTelnum(MsgHeaderPO msgHeader, Document doc);
    
    
    /**
	 * 山东有价卡购买接口
	 * @param header
	 * @param doc
	 * @return
	 * @remark create by wWX217192 2015-06-17 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造
	 */
	public ReturnWrap buyValueCard(MsgHeaderPO header, Document doc);
	
}
