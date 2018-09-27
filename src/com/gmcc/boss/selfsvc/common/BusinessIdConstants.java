/*
 * 文 件 名:  BusinessIdConstants.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  lKF60882
 * 修改时间:  2017-4-19
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.gmcc.boss.selfsvc.common;

/**
 * businessid常量定义
 * 
 * @author  lKF60882
 * @version  [版本号, 2017-4-19]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BusinessIdConstants
{
    // 短信下发
    public static final String CLI_BUSI_SENDSMSINFO = "cli_busi_sendsmsinfo";
    
    // 服务密码校验/修改/重置
    public static final String CLI_BUSI_PWDRESET = "cli_busi_pwdreset";
    
    // 服务密码校验
    public static final String CLI_BUSI_PWDRESET_CHK = "cli_busi_pwdreset_chk";
    
    // 服务密码修改
    public static final String CLI_BUSI_PWDRESET_MOD = "cli_busi_pwdreset_mod";
    
    // 服务密码修改
    public static final String CLI_BUSI_CHGPWD = "cli_busi_chgpwd";
    
    // 用户信息查询
    public static final String CLI_QRY_USERINFO = "cli_qry_userinfo";
    
    // 查询账单信息
    public static final String CLI_QRY_BILL2012_SD = "cli_qry_bill2012_sd";
    
    // 查询用户是否已开通手机邮箱
    public static final String CLI_QRY_MAILBOX = "cli_qry_mailbox";
    
    // 根据手机号码查询该用户的套餐信息
    public static final String CLI_QRY_TAOCAN = "cli_qry_taocan";
    
    // 查询详单记录
    public static final String CLI_QRY_CDR2012_SD = "cli_qry_cdr2012_sd";
    
    // 账户余额查询
    public static final String CLI_QRY_BALANCE = "cli_qry_balance";
    
    // 缴费历史查询
    public static final String CLI_QRY_CHARGEHISTORY = "cli_qry_chargehistory";
    
    // 月初扣费查询
    public static final String CLI_QRY_YCKF = "cli_qry_yckf";
    
    // 查询本机资费和已开通服务、优惠
    public static final String CLI_QRY_ZFINFO = "cli_qry_zfinfo";
    
    // PUK码查询
    public static final String CLI_QRY_PUKCODE = "cli_qry_pukcode";
    
    // 号码归属地查询
    public static final String CLI_QRY_NUMREGION = "cli_qry_numregion";
    
    // 验证身份证号码的准确性
    public static final String CLI_BUSI_CIDCHECK = "cli_busi_cidcheck";
    
    // 积分查询
    public static final String CLI_QRY_SCOREVALUESD = "cli_qry_scorevalueSD";
    
    // 产品受理通用接口
    public static final String CLI_BUSI_PRODUCTREC = "cli_busi_productrec";
    
    // 山东语音通话受理接口
    public static final String CLI_BUSI_CHANGEPRODUCTSUBMITSD = "cli_busi_ChangeProductSubmitSD";
    
    // 产品变更确认
    public static final String CLI_BUSI_PRODCHANGECONFIR = "cli_busi_prodchangeconfir";
    
    // 主体产品变更提交
    public static final String CLI_BUSI_PRODCHANGEINFO = "cli_busi_prodchangeinfo";
    
    //查询可补打的票据记录
    public static final String CLI_QRY_NOINVOICEPRINT_SD = "cli_qry_noinvoiceprint_sd";
    
    //票据补打，查询要打印的发票打印项数据
    public static final String CLI_QRY_INVOICEINFO_SD = "cli_qry_invoiceinfo_sd";
    
    //查询客户信息
    public static final String CLI_QRY_CUSTINFO = "cli_qry_custinfo";
    
    //查询空白卡是否是预置空卡
    public static final String CLI_BUSI_CHKISPRESETBLANKCARD = "cli_busi_chkIsPreSetBlankCard";
    
    //申请写卡，包括空白卡资源暂选和获取加密数据
    public static final String CLI_QRY_GENWRITECARDDATAENCRYPT = "cli_qry_genWriteCardDataEncrypt";
    
    //呼叫转移
    public static final String CLI_BUSI_CALLTRANSFER = "cli_busi_calltransfer";
    
    //积分发放明细查询
    public static final String CLI_QRY_PAYMENTSOCRE = "cli_qry_paymentSocre";
    
    //积分兑换历史查询
    public static final String CLI_QRY_SCORECHANGE = "cli_qry_scorechange";
    
    //add begin lwx439898 2017-05-05 OR_huawei_201704_404_【山东移动接口迁移专题】-自助终端业务办理2
    //校验用户证件下号码数量
    public static final String CLI_BUSI_CHKCERTINFOFORINSTALL = "cli_qry_chkCertInfoForInstall";
    //add end lwx439898 2017-05-05 OR_huawei_201704_404_【山东移动接口迁移专题】-自助终端业务办理2
    
    //记录缴费前日志接口
    public static final String CLI_BUSI_WRITENETFEEINFO = "cli_busi_writeNetFeeInfo";
    
    //写卡成功失败
    public static final String CLI_QRY_QRYRECFEEFORINSTALL = "cli_qry_qryRecFeeForInstall";
    
    //自助选号预订(号码预约)
    public static final String CLI_BUSI_OCCUPYTELNEW_SD = "cli_busi_occupytelnew_sd";
    
    //手机号码与身份证号码是否相符(补卡)
    public static final String CLI_BUSI_CHKCERTBYSERVNUM = "cli_busi_ChkCertByServnum";
       
    //证件号码认证
    public static final String CLI_BUSI_CHKCERTBYCERT = "cli_busi_ChkCertByCert";
    
    //校验是否电子有价卡 
    public static final String CLI_QRY_CHKIFEVCCARD = "cli_qry_chkIfEvcCard";
    
    //有价卡充值
    public static final String CLI_BUSI_ELECCARDCHARGE = "cli_busi_elecCardCharge";
    
    //积分兑换电子券
    public static final String CLI_BUSI_SCOREEXCHANGESD = "cli_busi_scoreExchangeSD";
    
    //add begin by cwx456134 2017-05-10 OR_huawei_201704_376_【山东移动接口迁移专题】-自助终端业务办理1
    //月结发票账期查询接口
    public static final String CLI_QRY_BILLCYCLECUSTINFO = "cli_qry_billCycleCustInfo";
    
    //月结发票打印信息查询
    public static final String CLI_QRY_MONTHINVOICEINFO = "cli_qry_monthinvoiceinfo";

    //查询可变更的主体产品信息
    public static final String CLI_QRY_CONVERTPRODINFO = "cli_qry_convertprodinfo";
    
    //根据主体产品编码查询主体产品信息，查询用户当前主体产品信息
    public static final String CLI_QRY_GETPRODINFOBYID = "cli_qry_getProdInfobyId";
    //add end by cwx456134 2017-05-10 OR_huawei_201704_376_【山东移动接口迁移专题】-自助终端业务办理1

    //add begin by cwx456134 2017-05-10 OR_huawei_201704_404_【山东移动接口迁移专题】-自助终端业务办理2
    //校验空白卡资源是否可用
    public static final String CLI_BUSI_CHECKBLANKCARD = "cli_busi_checkBlankCard";
    
    //将号码暂时选定
    public static final String CLI_BUSI_LOCKORUNLOCKTELNUM = "cli_busi_lockOrUnLockTelNum";
    //add end by cwx456134 2017-05-10 OR_huawei_201704_404_【山东移动接口迁移专题】-自助终端业务办理2

    
    //add begin by cwx456134 2017-05-10 OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
    //1_cli_qry_chkPrivAndCalcFee 营销方案校验及算费 现有
    public static final String CLI_QRY_CHKPRIVANDCALCFEE = "cli_qry_chkPrivAndCalcFee";
    
    //2_cli_busi_checkRecValid 业务有效性校验 现有
    public static final String CLI_BUSI_CHECKRECVALID = "cli_busi_checkRecValid";
    
    //3_cli_qry_userstate 当前用户状态查询 现有
    public static final String CLI_QRY_USERSTATE = "cli_qry_userstate";
    
    //4_cli_busi_cardpay 充值卡缴费  新增
    public static final String CLI_BUSI_CARDPAY = "cli_busi_cardpay";
    
    //5_cli_busi_chargefee 缴纳话费接口，用于现金缴费和银联卡缴费 已有
    public static final String CLI_BUSI_CHARGEFEE = "cli_busi_chargefee";

    //6_cli_busi_nonlocalCharge 跨省异地缴费 新增
    public static final String CLI_BUSI_NONLOCALCHARGE = "cli_busi_nonlocalCharge";
    
    //7_cli_qry_retcharge 根据手机号码查询该用户的应缴话费金额查询 新增
    public static final String CLI_QRY_RETCHARGE = "cli_qry_retcharge";
    
    //8_cli_qry_fee 查询话费接口，用于现金缴费和银联卡缴费 新增
    public static final String CLI_QRY_FEE = "cli_qry_fee";
    //add end by cwx456134 2017-05-10 OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
    
    //空白卡开户提交
    public static final String CLI_QRY_SELFINSTALL = "cli_qry_selfInstall";
    
    //业务受理成功后记录日志
    public static final String WRITEAGENTCHARGEZZZD = "writeagentchargezzzd";
    
    //add begin fwx439896 2017-05-22  OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3_需求分析设计说明书 
    //余额提醒查询
    public static final String CLI_QRY_ALARMBALANCE = "cli_qry_alarmbalance";
    
    //余额提醒设置 
    public static final String CLI_BUSI_ALARMBALANCE = "cli_busi_alarmbalance";
    
    // 自助选号查询
    public static final String CLI_QRY_NUMBYNET = "cli_qry_numbynet";
  
    // 梦网业务开通与退订
    public static final String CLI_SD_CHANGESUBSMONSERV = "cli_sd_ChangeSubsMonServ";
    //add end fwx439896 2017-05-22  OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3_需求分析设计说明书 
   
    //受理历史查询
    public static final String CLI_QRY_RECHISTORY = "cli_qry_rechistory";
    
    //促销活动受理接口
    public static final String CLI_BUSI_RECREWARDCOMMITSD = "cli_busi_recRewardCommitSD";
    
    //赠品列表查询接口
    public static final String CLI_QRY_REWARDLISTSD = "cli_qry_rewardListSD";
    
    //用户已办理活动档次
    public static final String CLI_QRY_SUBSPRIVLISTSD = "cli_qry_subsPrivListSD";
     
    //查询营销方案费用和用户预存费用
    public static final String CLI_QRY_PRIVFEESD = "cli_qry_privFeeSD";
    
    //积分明细查询
    public static final String CLI_QRY_SCROEDETAIL = "cli_qry_scroedetail";
    
    //查询主体产品模板信息
    public static final String CLI_QRY_PRODTEMPLATEINFO = "cli_qry_prodtemplateinfo";
    
    //查询同组业务用户订购信息
    public static final String CLI_BUSI_PRODUCTQRY = "cli_busi_productQry";
    
    //查询资费描述信息
    public static final String CLI_QRY_PRODUCTFEE = "cli_qry_productfee";
}
