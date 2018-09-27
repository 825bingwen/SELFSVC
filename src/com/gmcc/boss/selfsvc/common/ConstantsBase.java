/*
 * 文 件 名:  ConstantsBase.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zKF69263
 * 修改时间:  2014-7-21
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.gmcc.boss.selfsvc.common;

/**
 * 常量基类
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-7-21]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ConstantsBase
{
    /**
     * 默认查询分页大小
     */
    public static final int DEFAULT_PAGE_SIZE = 100;
    
    /**
     * log4j的日志级别，同SH_PARAM表中的paramid
     */
    public static final String LOG4J_LEVEL = "SH_LOG4J_LEVEL";
    
    /**
     * 缓存定时刷新时间
     */
    public static final String REFRESH_TIME = "PARAMREFRESH.INTERVAL";
    
    /**
     * 页面超时时间
     */
    public static final String PAGE_TIMEOUT = "SH_RETURN_HOMEPAGE";
    
    /**
     * 省份标识
     */
    public static final String PROVINCE_ID = "ProvinceID";
    
    /**
     * 是否是webservice接口调用方式
     */
    public static final String WEBSERVICE = "ISWEBSERVICE";
    
    /**
     * 
     */
    public static final String CDR_TYPES = "listDynServiceName";
    
    /**
     * 默认每次打印记录条数
     */
    public static final int DEFAULT_PRINT_PAGE_SIZE = 400;
    
    /**
     * 首页菜单个数
     */
    public static final int MENU_NUM = 9;
    
    // 非首页菜单个数
    public static final int CHILD_MENU_NUM = 6;
    
    /**
     * 余额提醒阀值的groupid
     */
    public static final String LEFT_MONEY_NOTIFS = "LeftMoneyNotifs";
    
    /**
     * session中保存的详单的名称
     */
    public static final String LIST_DATA_SESSION_NAME = "LIST-DATA-";
    
    /**
     * 存储在Map中的合计字段值的key常量名后缀。
     */
    public static final String LIST_DATA_SUM_KEY_NAME = "LIST-DATA-SUM";
    
    /**
     * 详单打印次数限制
     */
    public static final String SH_CDR_PRTTIMES = "SH_CDR_PRTTIMES";
    
    public static final String MENU_INFO = "MENUINFO";
    
    public static final String REGION_INFO = "REGIONINFO";
    
    public static final String MEDIA_FILE_PATH = "mediaFilePath";
    
    public static final String GOODS_FILE_PATH = "sellGoodsImgsPath";
    
    public static final String YHDZ_FILE_PATH = "yhdzImgsPath";
    
    public static final String TERMINAL_INFO = "TERMINALINFO";
    
    //add begin lwx439898 lwx439898 2017-10-12 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
    public static final String TERM_INFO = "TREMINFO";
    
    /**
     * 保存用户信息
     */
    public static final String USER_INFO = "CustomerSimpInfo";
    
    /**
     * 首页面
     */
    public static final String HOME_PAGE = "homePage";
    
    public static final String STR_SPLIT_TRANS = ",";
    
    public static final String PROVINCE_REGION_999 = "999";
    
    
    //add begion gwx223032 2015/05/20  OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1
    
    public static final String SMALLREGION = "SMALLREGION";
    public static final String BroadBandAppointbusiType = "APPOINT_BUSITYPE";
    
    public static final String CITY420000 = "City420000";
    public static final String DISTRICT = "District";
    
    //add end gwx223032 2015/05/20 OR_HUB_201504_412_湖北_关于自助终端承载宽带预约功能的需求v1.1
    /**
     * 随机密码长度
     */
    public static final String RANDOM_PWD_LEN = "SH_RANDOMPWD_LEN";
    
    /**
     * 随机密码有效期
     */
    public static final String RANDOM_PWD_VALIDITY = "SH_RANDOMPWD_VALIDITY";
    
    /**
     * 随机密码短信内容
     */
    public static final String RANDOM_PWD_CONTENT = "SH_RANDOMPWD_CONTENT";
    
    public static final String CUR_MENUMAP = "menuMap";
    
    public static final String CUR_TERM_ID = "termId";
    
    public static final String CUR_TERM_SPECIAL = "termSpecial";
    
    public static final String BROWSER_IE = "IE";
    
    public static final String BROWSER_FIREFOX = "FIREFOX";
    
    public static final String UNION_PLUGIN = "unionpluginid";
    
    /**
     * 当前菜单ID
     */
    public static final String CUR_MENUID = "curMenuId";
    
    public static final String CUR_SERVNUMBER = "servnumber";
    
    public static final String CUR_SUBSSHOULDPAY = "shouldPayTmp";
    
    /**
     * 省份：山东
     */
    public static final String PROOPERORGID_SD = "SD";
    
    //add begion g00140516 2011/11/08 R003C11L11n01 增加省份信息 
    /**
     * 省份：重庆
     */
    public static final String PROOPERORGID_CQ = "CQ";
    //add end g00140516 2011/11/08 R003C11L11n01 增加省份信息 
    
    /**
     * 省份：湖北
     */   
    public static final String PROOPERORGID_HUB = "HUB";
    
    /**
     * 省份：宁夏
     */     
    public static final String PROOPERORGID_NX = "NX";
    
    /**
     * 服务密码认证
     * 
     */
    public static final String BUSITYPE_SUBSVERIFYPWD = "SHVerifyPWD";
    
    /**
     * 身份证号认证
     * 
     */
    public static final String BUSITYPE_SUBSVERIFYID = "SHVerifyID";
    
    /**
     * 使用服务号码获取用户信息
     * 
     */
    public static final String BUSITYPE_SUBCHKPHONE = "SHChkPhoneNo";
    
    /**
     * 月帐单查询
     * 
     */
    public static final String BUSITYPE_SUBSQRYBILLITEM = "SHQryBilItem";
    
    // add begin hWX5316476 2014-05-30  V200R003C10LG0601  OR_huawei_201405_878_山东_营业厅全业务流程优化-业务分流(自助终端)_支撑包月量查询功能
    /**
     * 包月量信息查询
     */
    public static final String BUSITYPE_SUBSQRYMONTHLYAMOUNT = "SHQryMonthlyAmount";
    // add end hWX5316476 2014-05-30  V200R003C10LG0601  OR_huawei_201405_878_山东_营业厅全业务流程优化-业务分流(自助终端)_支撑包月量查询功能
    
    /** 清单查询 */
    public static final String BUSITYPE_SUBSQRYMUSTER = "SHQryMuster";
    
    /**
     * 短信随机密码认证
     * 
     */
    public static final String BUSITYPE_VALIDATERESULT = "SHValidateResult";
    
    /**
     * 业务信息查询-->已开通优惠查询
     * 
     */
    public static final String BUSITYPE_SUBSQRYFAVOURABLE = "SHQrySubsFavourable";
    
    /**
     * 业务信息查询-->套餐信息查询
     * 
     */
    public static final String BUSITYPE_SUBSQRYPACKAGE = "SHQrySubsPackage";
    
    /**
     * 业务信息查询-->受理历史记录查询
     * 
     */
    public static final String BUSITYPE_SUBSQRYRECHISTORY = "SHQrySubsRecHistory";
    
    //add begin l00190940 2011-11-03 积分兑换信息查询
    /** 积分兑换信息查询* */
    public static final String BUSITYPE_WBQRYSCOREEXCHAANGE = "SHQryValidScoreExchange";
    //add end l00190940 2011-11-03 积分兑换信息查询
    
    /**
     * 业务信息查询-->梦网业务查询退订
     * 
     */
    public static final String BUSITYPE_SUBSCANCELSP = "SHCancelSubsSP";
    
    //add begion g00140516 2011/11/08 R003C11L11n01 增加常量
    /**
     * 业务办理-->产品查询受理
     */
    public static final String BUSITYPE_PRODORDER = "SHProdOrder";
    //add end g00140516 2011/11/08 R003C11L11n01 增加常量 
    
    /** 业务信息查询-->PUK码查询 */
    public static final String BUSITYPE_PUKITEM = "SHPukQuery";
    
    /** 业务信息查询-->号码归属地查询 */
    public static final String BUSITYPE_USERREGION = "SHGetUserRegion";
    
    // /** 话费信息查询-->余额查询 */
    public static final String BUSITYPE_WBQRYBALANCE = "SHQryBalance";
    
    /** 话费信息查询-->积分查询* */
    public static final String BUSITYPE_WBQRYSCORE = "SHQryValidScore";
    
    /**
     * 话费查询-->缴费历史查询
     * 
     */
    public static final String BUSITYPE_FEEHISTORY = "SHQryPayHistory";
    
    /** 话费查询-->月初扣款查询 */
    public static final String BUSITYPE_MONTHFEE = "SHQryMonthFee";
    
    /** 话费查询-->当前状态 */
    public static final String BUSITYPE_CURRENTSTATUS = "SHQryCurrentStatus";

    // /*******************************************************************************************************************
    // * ****************** 服务 *****************************************
    // ******************************************************************************************************************/
    //    
    /** 自助选号 */
    public static final String BUSITYPE_CHOOSE_TEL_NUM = "SHChooseTelNum";
     
    /** 自助选号查询 */
    public static final String BUSITYPE_CHOOSE_TEL_NUM_QRY = "SHChooseTelNumQry";

    // /*******************************************************************************************************************
    // * ****************** 业务受理 *****************************************
    // ******************************************************************************************************************/
    
    /** 密码修改 */
    public static final String BUSITYPE_CHGSUBSPWD = "SHChgSubsPwd";
    
    /**密码重置*/
    public static final String BUSITYPE_RESETPWD = "SHResetPwd";
    
    // add begin hWX5316476 2014-06-07  V200R003C10LG0601  OR_huawei_201404_1116_山东_营业厅全业务流程优化-业务分流(自助终端)_转套餐产品
    /**
     * 套餐资费变更
     */
    public static final String BUSITYPE_PRODUCTCHANGE = "SHRecProductChange";
    // add end hWX5316476 2014-06-07  V200R003C10LG0601  OR_huawei_201404_1116_山东_营业厅全业务流程优化-业务分流(自助终端)_转套餐产品

    // add begin hWX5316476 2014-06-07  V200R003C10LG0601 OR_SD_201405_849_山东_关于在营业厅增加实名制认证的功能
    /**
     * 实名登记受理5选2验证方式
     */
    public static final String BUSITYPE_REALNAMEREG2 = "SHRealNameReg2";
    // add end hWX5316476 2014-06-07  V200R003C10LG0601  OR_SD_201405_849_山东_关于在营业厅增加实名制认证的功能
    
    // add begin wWX217192 2014-08-13 V200R003C10LG0601 OR_SD_201408_166 自助终端新增实名登记认证（自助终端5选3认证方式)
    // 实名认证5选3验证方式
    public static final String BUSITYPE_REALNAMEREG1 = "SHRealNameReg1";
    // add end wWX217192 2014-08-13 V200R003C10LG0601 OR_SD_201408_166 自助终端新增实名登记认证（自助终端5选3认证方式)

    //    
    // /** 移动秘书 */
    // public static final String BUSITYPE_RECMOBILESEC = "SHMobileSEC";
    
    /** 余额提醒值设置成功 */
    public static final String BUSITYPE_CHGBALANCEURGE = "SHChgBalanceUrge";
    
    /**
     * 呼叫转移申请
     * 
     */
    public static final String BUSITYPE_RECCALLTRANSFER = "SHCallTransfer";
    
    /**
     * 
     * 主叫显示
     * 
     */
    public static final String BUSITYPE_CALLDISPLAY = "SHCallDisplay";
    
    /**
     * 手机上网
     * 
     */
    public static final String BUSITYPE_GPRSSERV = "SHGPRSServ";
    
    /** 账单寄送 */
    public static final String BUSITYPE_BILLSEND = "SHBillSend";
    
    /** 身份证入网预约 */
    public static final String BUSITYPE_IdCardBook = "SHIdCardBook";
    
    /** 余额提醒 */
    public static final String BUSITYPE_BALANCEUD = "SHBalanceUD";
    
    /**
     * 移动全时通
     * 
     */
    public static final String BUSITYPE_INCOMINGAWAKE = "SHIncomingAwake";
    
    /** 停开机 */
    public static final String BUSITYPE_STOPOPEN = "SHstopOpen";

    // /*******************************************************************************************************************
    // * ****************** 缴 费 ********************************************
    // ******************************************************************************************************************/
        
    /**
     * 充值缴费
     */
    public static final String FEE_CHARGE = "feeCharge";

    /** 是否需要签到,1:需要签到,0:不用签到 */
    public static final String ISNEEDSIGN = "needSign";
    
    /*******************************************************************************************************************
     * ****************** 广告屏保 ***********************************
     ******************************************************************************************************************/
    
    /** 广告类型 */
    public static final String ADV_TYPE = "adv";
    
    /** 屏保类型 */
    public static final String SC_TYPE = "sc";
    
    /**
     * 广告屏保相对路径
     * 
     */
    public static final String MEDIA_FILE_RELATIVE_PATH = "ssResources/mediaResource";
    
    /**
     * 优惠打折相对路径
     * 
     */
    public static final String ABATE_FILE_RELATIVELY_PATH = "ssResources/adateResource";
    
    /**
     * 自助售货相对路径
     * 
     */
    public static final String SELLGOODS_FILE_RELATIVELY_PATH = "ssResources/sellgoodsResource";
    
    /**
     * 语音提示相对路径
     * 
     */
    public static final String PROMPT_FILE_RELATIVELY_PATH = "ssResources/promptResource";
    
    /** 文件类型 */
    public static final String FILE_TYPE_SWF = "swf";
    
    public static final String FILE_TYPE_MWV = "mwv";
    
    public static final String FILE_TYPE_AVI = "avi";

    /*******************************************************************************************************************
     * ****************** SH_PARAM ***********************************
     ******************************************************************************************************************/
    
    /** 自助终端管理平台socket地址和端口 */
    public static final String SH_SOCKADDR = "SH_SOCKADDR";
    
    /** 自助终端上传日志的地址和端口 */
    public static final String SH_LOGFTPADDR = "SH_LOGFTPADDR";
    
    /** 自助终端首页flash地址 */
    public static final String SH_WELCOME_PATH = "SH_WELCOME_PATH";
    
    /** 银行卡缴费负责申明省份 */
    public static final String SH_DUTY_PROVINCE = "SH_DUTY_PROVINCE";
    
    /** WebService服务地址 */
    public static final String SH_WEBSERVICE_URL = "SH_WEBSERVICE";
    
    /** 连接WebService服务密码 */
    public static final String WEBINTER_OUTOPERPWD = "SH_WEBINTER_OUTOPERPWD";
    
    /** 自助终端设置余额提醒值 */
    public static final String SH_BALANCE = "SH_BALANCE";
    
    /** 缴费完成后返回主页面等待时间(秒) */
    public static final String SH_RETURNIDX_TIME = "SH_RETURNIDX_TIME";
    
    /** 银联卡缴费吞卡等待时间(秒) */
    public static final String SH_CAPTURECARD_TIME = "SH_CAPTURECARD_TIME";
    
    /** 银联卡缴费缴费确认等待时间(秒) */
    public static final String SH_MAKESURE_TIME = "SH_MAKESURE_TIME";
    
    /** 银联卡缴费缴费金额输入等待时间(秒) */
    public static final String SH_INPUTMONEY_TIME = "SH_INPUTMONEY_TIME";
    
    /** 银联卡缴费密码输入等待时间(秒) */
    public static final String SH_INPUTCARDPWD_TIME = "SH_INPUTCARDPWD_TIME";
    
    /** 银联卡缴费读卡等待时间(秒) */
    public static final String SH_READCARD_TIME = "SH_READCARD_TIME";
    
    /** 现金缴费投币等待时间(秒) */
    public static final String SH_PAYMONEY_TIME = "SH_PAYMONEY_TIME";
    
    /** 自助终端客户端媒体文件地址 */
    public static final String SH_LOCAL_FILE_PATH = "SH_LOCAL_FILE_PATH";
    
    /** 自助终端客户端屏保本地媒体文件列表 */
    public static final String SH_LOCAL_SCPLAY_LIST = "SH_LOCAL_SCPLAY_LIST";
    
    /** 自助终端客户端广告本地媒体文件列表 */
    public static final String SH_LOCAL_ADVPLAY_LIST = "SH_LOCAL_ADVPLAY_LIST";
    
    /** 自助终端客户端屏保播放列表文件名 */
    public static final String SH_LOCAL_SCWPL_LIST = "SH_LOCAL_SCWPL_LIST";
    
    /** 自助终端客户端广告播放列表文件名 */
    public static final String SH_LOCAL_ADVWPL_LIST = "SH_LOCAL_ADVWPL_LIST";
    
    /** 自助终端客户端主面跳到屏保页等待时间 */
    public static final String SH_GO_SCEEN_TIME = "SH_GO_SCEEN_TIME";
    
    /** 业务体验链接 */
    public static final String SH_REC_TASTE_URL = "SH_REC_TASTE_URL";
    
    /** 优惠打折免费打印次数 */
    public static final String SH_ABATEFREEPRINTNUM = "SH_ABATEFREEPRINTNUM";
    
    /** 手机支付账户信息查询 */
    public static final String BUSITYPE_QRYMAINFEE = "SH_QRY_MAIN_FEE";
    
    /** 手机支付账户支付 */
    public static final String BUSITYPE_RECMAINFEE = "SH_REC_MAIN_FEE";
    
    /** 控件检查频率 * */
    public static final String SH_OCX_CHECK_FREQUENCY = "SH_OCX_CHECK_FREQUENCY";
    
    /** 系统是否需要屏保*/
    public static final String SH_IS_THERE_SCREENSAVERS = "SH_IS_THERE_SCREENSAVERS";

    /** 银联卡支付 */
    public static final String PAYBYUNIONCARD = "1";

    /**
     * 现金支付
     * 
     */
    public static final String PAYBYMONEY = "4";

    /**
     * 服务密码认证
     */
    public static final String AUTHTYPE_SERVICEPWD = "SERVICEPWD";
    
    /**
     * 随机密码认证
     */
    public static final String AUTHTYPE_RANDOMPWD = "RANDOMPWD";
    
    /**
     * 用户使用服务密码或者随机密码进行身份认证，在规定时间内连续输入的错误次数达到系统限制后，手机号码会被锁定一定的时间
     */
    public static final String SH_LOGIN_TIMESCOPE = "SH_LOGIN_TIMESCOPE";
    
    public static final String SH_LOGIN_MAXTIMES = "SH_LOGIN_MAXTIMES";
    
    public static final String SH_LOGIN_LOCKEDTIME = "SH_LOGIN_LOCKEDTIME";
    
    /**
     * 取消
     */
    public static final String SERVICE_CANCEL = "0";
    
    public static final String SERVICE_CANCEL_STR = "DEL";
    
    /**
     * 开通
     */
    public static final String SERVICE_APPLY = "1";
    
    public static final String SERVICE_APPLY_STR = "ADD";
    
    /**
     * 呼叫转移
     */
    public static final String CALL_TRANSFER_CANCEL = "28";
    
    public static final String CALL_TRANSFER_ALL = "24";
    
    public static final String CALL_TRANSFER_BUSY = "25";
    
    public static final String CALL_TRANSFER_NOANSWER = "26";
    
    public static final String CALL_TRANSFER_OUTOFNETWORK = "27";
    
    /**
     * 发票打印
     */
    public static final String BUSITYPE_PRINTINVOICE = "PrintInvoice";
    // add begin cKF48754 2011/11/01 R003C11L10n01 OR_huawei_201111_148
    /** 用户退出登录* */
    public static final String BUSITYPE_LOGOUT = "SHLogOut";
    // add end cKF48754 2011/11/01 R003C11L11n01 OR_huawei_201111_148
    
    // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    // 缴费类型
    public static final String ChargeType = "ChargeType";
    // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    
    // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    /** 详单寄送开通139邮箱 */
    public static final String ADD_BILLMAIL = "ADD_BILLMAIL";
    
    /** 邮件服务器地址 */
    public static final String SH_MAIL_HOST = "SH_MAIL_HOST";
    
    /** 邮件服务器端口 */
    public static final String SH_MAIL_PORT = "SH_MAIL_PORT";
    
    /** 发件人邮箱地址 */
    public static final String SH_MAIL_FROM = "SH_MAIL_FROM";
    
    /** 发件人用户名 */
    public static final String SH_MAIL_USERNAME = "SH_MAIL_USERNAME";
    
    /** 发件人密码 */
    public static final String SH_MAIL_PWD = "SH_MAIL_PWD";
    
    /** 是否使用代理标识 */
    public static final String SH_MAIL_HASPROXY = "SH_MAIL_HASPROXY";
    
    /** 代理类型 */
    public static final String SH_MAIL_PROXYTYPE = "SH_MAIL_PROXYTYPE";
    
    /** 代理主机 */
    public static final String SH_MAIL_PROXYHOST = "SH_MAIL_PROXYHOST";
    
    /** 代理主机端口 */
    public static final String SH_MAIL_PROXYHOSTPORT = "SH_MAIL_PROXYHOSTPORT";
    // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    
    //add begin g00140516 2011/12/09 R003C11L12n01 详单查询实现socket协议
    public static final String CHANNEL_ID = "bsacAtsv";
    
    public static final String SUCCESS_RETCODE_ZERO = "0";
    
    public static final String SUCCESS_RETCODE_HUNDRED = "100";
    
    /**
     * 后续包标志
     */
    public static final String NEXTPKG_FLAG = "1";
    
    /**
     * 无后续包标志
     */
    public static final String NO_NEXTPKG_FLAG = "0";    
    
    /**
     * 详单查询时使用的厂商信息
     */
    public static final String CDR_FACTORY_INFO = "9A3A9B26E157B508228301EF1F7BF352";
    
    /**
     * 统一接口平台服务端的IP
     */
    public static final String INT_SOCKET_IP = "INT_SOCKET_IP";
    
    /**
     * 统一接口平台服务端的端口
     */
    public static final String INT_SOCKET_PORT = "INT_SOCKET_PORT";
    //add end g00140516 2011/12/09 R003C11L12n01 详单查询实现socket协议
    
    //add begin g00140516 2012/01/07 R003C11L12n01 bug 18636
    public static final int MENU_TOPRIGNT_MAX = 2;
    
    public static final int MENU_BOTTOMRIGHT_MAX = 5;
    //add end g00140516 2012/01/07 R003C11L12n01 bug 18636
    
    
    
    //add begin ykf38827 2012/02/28 R003C12L03n01   OR_HUB_201201_63
    /**
     * 短信模板
     */
    public static final String CDR_SMTEMPLATE = "SMTemplate";
    /**
     * 月详单
     */
    public static final String CDR_QRYMUSTER = "qryMuster";
   
    // add end ykf38827 2012/02/28 R003C12L03n01   OR_HUB_201201_63
   
    // add begin YKF70747 2012/04/08 R003C12L04n01    OR_SD_201203_818
    /**
     * 终端键盘厂商信息
     */
    public static final String SH_TERMI_KEY_INFO = "SH_TERMI_KEY_INFO";
    // add begin YKF70747 2012/04/08 R003C12L04n01 OR_SD_201203_818
   
    // add begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
    public static final String SH_CLEARFLAG = "SH_CLEARFLAG"; 
    
    public static final String SH_CLEARFLAG_0 = "0";
    // add end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
    
    // add begin YKF70747 2012/04/20 R003C12L04n01 OR_HUB_201202_1193
    /**
     * 是否显示取消原因列(主体产品变更模块)
     */
    public static final String SH_ISSHOWREASON = "SH_ISSHOWREASON";
    
    /**
     * 是否显示取消原因列0:不显示
     */
    public static final String SH_ISSHOWREASON_N = "0";    
    // add end YKF70747 2012/04/20 R003C12L04n01  OR_HUB_201202_1193
    
    /**
     * 现金识币器状态监测间隔时间（宁夏现金稽核）
     */
    public static final String SH_CASHSTATUS_CHECKINTERVAL = "SH_CASHSTATUS_CHECKINTERVAL";

    /**
     * 新版自助选号功能启用标识。1--启动；0--不启用
     */
    public static final String SH_SELECTTEL_NEW = "SH_SELECTTEL_NEW";
    
    /**
     * 吉祥号码规则
     */
    public static final String LUCKY_NUMBER_RULES = "LuckyNumRules";
    
    /**
     * 自助选号号码预存费用，单位元
     */    
    public static final String SH_TEL_FEE = "SH_TEL_FEE";

    /**
     * 自助选号最大号码数量
     */
    public static final String SH_SELECTTEL_MAXCOUNT = "SH_SELECTTEL_MAXCOUNT";
    
    //add begin g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
    /**
     * 终端机对应的业务推荐营业员的手机号码，目前仅宁夏使用
     */
    public static final String SH_INFO_RECTEL = "SH_INFO_RECTEL";
    
    /**
     * 业务推荐营业员手机号码维护工对应的操作类型，记日志使用。
     */
    public static final String SH_RECTEL_MGT = "RectelMgt";
    //add end g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
    
    //add begin CKF76106 2012/07/03 R003C12L07n01 OR_HUB_201206_597
    /**
     * 当前产品信息
     */
    public static final String PROD_INFO = "CURRENTPROD";
    
    /**
     *  产品快速发布-用户已订购产品信息查询 
     */
    public static final String BUSITYPE_QRYHASPRODS = "SHQryHasProds";
    
    /**
     *  产品快速发布-产品附加属性查询
     */
    public static final String BUSITYPE_QRYADDATTR = "SHQryAddAttr";
    
    /**
     *  产品快速发布-产品受理 
     */
    public static final String BUSITYPE_PRODREC = "SHProdRec";
    
    /**
     *  产品快速发布-子产品查询 
     */
    public static final String BUSITYPE_QRYSUBPRODS = "SHQrySubProds";
    //add end CKF76106 2012/07/03 R003C12L07n01 OR_HUB_201206_597
    
    //add begin g00140516 2012/07/06 R003C12L07n01 OR_NX_201205_649
    public static final String DATE_PATTERN_YYYYMMDD_1 = "yyyy-MM-dd";

    public static final String EFFECT_TYPE = "EffectType";
    //add end g00140516 2012/07/06 R003C12L07n01 OR_NX_201205_649
    
    // add begin g00140516 2012/07/23 R003C12L07n01 山东充值交费记录详细日志
    public static final String SH_CHARGELOG_DETAILFLAG = "SH_CHARGELOG_DETAILFLAG";
    // add end g00140516 2012/07/23 R003C12L07n01 山东充值交费记录详细日志
    
    // add begin g00140516 2012/08/06 R003C12L08n01 OR_NX_201206_794
    public static final String SH_HUAKOU_CHARGETYPE = "SH_HUAKOU_CHARGETYPE";

    public static final String SH_HUAKOU_MERCHANTID = "SH_UNIONPAY_USERID";
    
    public static final String SH_HUAKOU_TRIGGERMONEY = "SH_HUAKOU_TRIGGERMONEY";
    
    public static final String SH_HUAKOU_PROTOCOL = "SH_HUAKOU_PROTOCOL";
    
    public static final String DATE_PATTERN_LONG = "yyyyMMddHHmmss";
    // add end g00140516 2012/08/06 R003C12L08n01 OR_NX_201206_794
    
    // add begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
    public static final String SH_ALERTMSG_CONVERTFLAG = "SH_ALERTMSG_CONVERTFLAG";
    public static final String SH_ALERTMSG_DEFAULTINFO = "SH_ALERTMSG_DEFAULTINFO";
    public static final String SH_ALERTMSG_DEFAULTID = "SH_ALERTMSG_DEFAULTID";
    // add end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
    
    /**
     *要过滤随机密码验证的菜单 
     */
    public static final String NO_RANDOMPWD_MENU =  "SH_NORANDOMPWD_MENU";
    
    /**
     * 要过滤随机密码验证的产品
     */
    public static final String NO_RANDOMPWD_PRO = "SH_NORANDOMPWD_PRO";
    
    // add begin g00140516 2012/08/23 R003C12L08n01 产品快速发布
    public static final String SH_PATH_PRODIMG = "SH_PATH_PRODIMG";
    
    public static final String PRODIMG_FILE_RELATIVELY_PATH = "ssResources/prodimg";
    // add end g00140516 2012/08/23 R003C12L08n01 产品快速发布
    
    // add begin cKF76106 2012/08/21 R003C12L08n01 OR_HUB_201206_96
    // 支持营销推荐活动的菜单
    public static final String ACTMENU = "ActMenu";
    //查询用户可推荐的业务列表
    public static final String BUSITYPE_QRYRECPRODLIST = "SHQryRecProdList";
    //记录业务推荐结果
    public static final String BUSITYPE_RECFEEDBACK = "SHRecFeedback";
    //推荐业务受理
    public static final String BUSITYPE_RECPROD = "SHRecProd";
    //更新业务推荐结果为“办理成功”
    public static final String BUSITYPE_SETRECSUCCESS = "SHSetRecSuccess";
    //已向用户推荐活动标志
    public static final String ALREADY_REC_FLAG = "AlreadyRecFlag";
    // add end cKF76106 2012/08/21 R003C12L08n01 OR_HUB_201206_96
    
    // add begin zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
    // 查询用户回馈定义信息列表
    public static final String BUSITYPE_QRYFEEDBACKDEFLIST = "SHQryFeedBackDefList";
    
    // 用户活动回馈定义受理
    public static final String BUSITYPE_RECFEEDBACKDEF = "SHRecFeedBackDef";
    // add end zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
    
    //add begin m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179
    /**
     * 月账单查询页面下方的文字链接
     */
    public static final String MONTHBILL_TEXT_HREF = "SH_MONTHBILL_HREF";
    /**
     * 点击月账单查询页面下方的超链接后所指向的内容
     */
    public static final String MONTHBILL_TEXT_URL = "SH_MONTHBILL_URL";
    //add end m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179
    
    //add begin m00227318 2012/09/13
    /**
     * 账单查询中使用赠款项的返回报文名称
     */
    public static final String MONTHBILL_GRANTSFEE = "SH_BILL_GRANTFEE";
    //add end m00227318 2012/09/13
    
    /**
     * 充值交费语音提示功能启动标识
     */
    public static final String SH_CHARGE_PHONICMSG = "SH_CHARGE_PHONICMSG";
    
    // add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
    /**
     * 营销资费案特别提示
     */
    public static final String SH_PRODTIPS_FLAG = "SH_PRODTIPS_FLAG";
    // add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
    
    // add begin g00140516 2012/09/18 R003C12L09n01 提示信息改造
    /**
     * 提示信息专题，自助终端模块
     */
    public static final String SH_ALERTMSG_MODEL = "zz";
    // add end g00140516 2012/09/18 R003C12L09n01 提示信息改造
    
    // add begin g00140516 2012/09/19 R003C12L09n01 OR_NX_201207_781
    /**
     * 是否支持金属键盘操作。1，支持
     */
    public static final String SH_OPERATION_KEYFLAG = "SH_OPERATION_KEYFLAG";
    
    /**
     * 错误信息弹出提示标志。1，以弹出窗口形式显示；0，页面显示。
     */
    public static final String SH_ERRORMSG_POPUPFLAG = "SH_ERRORMSG_POPUPFLAG";
    // add end g00140516 2012/09/19 R003C12L09n01 OR_NX_201207_781    
        
    //add begin m00227318 2012/09/17 eCommerce V200R003C12L09 OR_huawei_201209_33
    /**
     * 湖北积分兑换电子券，活动编码
     */
    public static final String SCOREEXECOUP_PRODID = "SH_SCORE_EXECOUP_PRODID";
    /**
     * 湖北积分兑换电子券，档次编码
     */
    public static final String SCOREEXECOUP_PRIVID = "SH_SCORE_EXECOUP_PRIVID";
    //add end m00227318 2012/09/17 eCommerce V200R003C12L09 OR_huawei_201209_33
    /**
     * 资助终端账单协同查询之139email
     * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
     */
    /**
     * 账单协同查询 139邮箱接收账单信息
     * 
     */
    public static final String billColQuery139Email = "billColQuery139Email";
    /**
     * 账单协同查询之短信
     * 
     */
    public static final String billColQueryMessage = "billColQueryMessage";
    /**
     * 资助终端账单协同查询之彩信
     * 
     */
    public static final String billColQueryColorMessage = "billColQueryColorMessage";
    
    // add begin g00140516 2012/10/12 eCommerce R003C12L07_EHUB OR_HUB_201206_597
    /**
     * 产品快速发布上线标识
     */
    public static final String SH_QUICKPUBLISH_FLAG = "SH_QUICKPUBLISH_FLAG";
    // add end g00140516 2012/10/12 eCommerce R003C12L07_EHUB OR_HUB_201206_597

    // add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
    public static final String SH_BACKWAITING_FLAG = "SH_BACKWAITING_FLAG";
    // add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
    /**
     *免费抽奖
     *
     */
    // add begin yKF73963 （2012-11-09） OR_HUB_201209_706  电子渠道-抽奖平台-抽奖接口改造 
    public static final String  MIANFEICHOUJIANG= "MIANFEICHOUJIANG";
    public static final String  SH_AWARDSWITCH_HUB= "SH_AWARDSWITCH_HUB";
    // add end yKF73963 （2012-11-09） OR_HUB_201209_706  电子渠道-抽奖平台-抽奖接口改造 
    
    //add begin m00227318 2012/11/16 V200R003C12L11 OR_huawei_201211_242
    //加密标识，0表示不加密，1表示加密
    public static final String SH_ENCRYPT_FLAG = "SH_ENCRYPT_FLAG";
  
    //加密算法。0：AES/ECB/PKCS5Padding；1：AES/CBC/PKCS5Padding
    public static final String SH_ALGORITHM_TYPE = "SH_ALGORITHM_TYPE";
    
    //密钥
    public static final String SH_ENCRYPT_KEY = "SH_ENCRYPT_KEY";
    
    //初始化向量
    public static final String SH_ENCRYPT_IV = "SH_ENCRYPT_IV";
    //add end m00227318 2012/11/16 V200R003C12L11 OR_huawei_201211_242
    
    // add begin zKF69263 2013/04/12 R003C13L04n01 OR_NX_201304_18_宁夏_关于修改铁通无线固话清单查询流程的工单
    /**
     * 特定前缀手机号码不校验短信码（宁夏）
     */
    public static final String SH_RANDOMPWDAUTH_EXCLUDE_PRETEL = "SH_RANDOMPWDAUTH_EXCLUDE_PRETEL";
    // add end zKF69263 2013/04/12 R003C13L04n01 OR_NX_201304_18_宁夏_关于修改铁通无线固话清单查询流程的工单
    
    /**
     * 服务加密开关（宁夏）
     */
    public static final String SH_PASSWORD_ENCRYPT_FLAG = "SH_PASSWORD_ENCRYPT_FLAG";
    
    // add begin g00140516 2012/12/10 eCommerce V200R003C12L11 OR_SD_201211_692
    public static final String SH_CHARGE_CARD_LIMIT = "SH_CHARGE_CARD_LIMIT";
    
    public static final String SH_CHARGE_CARD_MSG = "SH_CHARGE_CARD_MSG";
    // add end g00140516 2012/12/10 eCommerce V200R003C12L11 OR_SD_201211_692
    
    // add begin g00140516 2013/02/22 R003C13L02n01 OR_NX_201302_600
    /**
     * 话费总额查询
     */
    public static final String BUSITYPE_CURRBILLFEE = "SHQryCurrBillFee";
    
    /**
     * 随机密码发送时间间隔，单位：秒
     */
    public static final String SH_RANDOMPWD_INTERVAL = "SH_RANDOMPWD_INTERVAL";
    
    /**
     * 随机密码再次获取标识。1，支持；0，不支持
     */
    public static final String SH_RANDOMPWD_NEXTFLAG = "SH_RANDOMPWD_NEXTFLAG";
    // add end g00140516 2013/02/22 R003C13L02n01 OR_NX_201302_600
    
    // 湖北积分兑换电子券最少允许进行兑换的积分值
    public static final String SH_SCOREEXECOUP_VALUE = "2";
     
     // 湖北积分兑换电子券头部内容
    public static final String SH_SCOREEXECOUP_TITLE = "0";
    
    // 湖北积分兑换电子券列表
    public static final String SH_SCOREEXECOUP_DATA = "1";
    
    //菜单图片存放路径
    // add begin zWX176560 2013/08/15 OR_NX_201307_930 菜单管理
    public static final String MENUIMGPATH = "menuImgPath";
    // end begin zWX176560 2013/08/15 OR_NX_201307_930 菜单管理
    
    //菜单图片存放相对路径
    // add begin zWX176560 2013/08/15 OR_NX_201307_930 菜单图片存放相对路径
    public static final String MENUIMG_FILE_RELATIVELY_PATH = "ssResources/menuImg";
    // end begin zWX176560 2013/08/15 OR_NX_201307_930 菜单图片存放相对路径
    
    //用户积分类型的groupId
    // add begin zWX176560 2013/08/21 OR_NX_201308_595  
    public static final String SCORE_TYPE = "SCORE_TYPE";
    // end begin zWX176560 2013/08/21 OR_NX_201308_595  
    
    //根据类型查询积分
    // add begin zWX176560 2013/08/21 OR_NX_201308_595 
    public static final String QRYUSERSCOREINFOBYTYPE = "qryUserScoreInfoByType";
    // end begin zWX176560 2013/08/21 OR_NX_201308_595 
    
    //银行卡绑定
    // add begin zWX176560 2013/09/17 OR_SD_201309_66 
    
    //连接网关URL
    public static final String SH_EASYPAYCHANGE_QRYBIND_URL = "SH_EASYPAYCHANGE_QRYBIND_URL";
    
    //编码
    public static final String SH_EASYPAYCHANGE_CHARSET = "SH_EASYPAYCHANGE_CHARSET";
    
    //无密绑定提交URL
    public static final String SH_EASYPAYCHANGE_NOENCRPTY_URL = "SH_EASYPAYCHANGE_NOENCRPTY_URL";
    
    //山东银行卡绑定详细日志记录
    public static final String SH_BINDBANKCARD_DETAILFLAG = "SH_BINDBANKCARD_DETAILFLAG";
    
    //已经绑定信息提示
    public static final String SH_EASYPAYCHANGE_BINDTIP = "SH_EASYPAYCHANGE_BINDTIP";
    
    //解约绑定关系提交URL
    public static final String SH_EASYPAYCHANGE_UNBIND_URL = "SH_EASYPAYCHANGE_UNBIND_URL";
    
    // end begin zWX176560 2013/09/17 OR_SD_201309_66 
    
    //add begin lWX5316086 2013/09/20
    
    //开机编码
    public static final String TERMON = "TERMON";
    
    //开机描述
    public static final String SH_TERMON = "SH_TERMON";
    
    //add end lWX5316086 2013/09/20
    
    //add begin zWX176560 2013/10/15 OR_HUB_201303_200 自助终端一体化营销功能优化
    // 产品推荐开关 湖北
    public static final String RECOMMENDPRODUCTKEY = "SH_RECOMMENDPRODUCT_KEY";
    //add begin zWX176560 2013/10/15 OR_HUB_201303_200 自助终端一体化营销功能优化
    
    // add begin hWX5316476 2013/10/11 OR_huawei_201305_1248
    /**
     * 代理商自助终端的现金缴费开关（湖北）
     */
    public static final String SH_CASHCHARGE_AGENT = "SH_CASHCHARGE_AGENT";
    
    /**
     * 代理商自助终端科目（湖北）
     */
    public static final String SH_SUBJECT_AGENT = "SH_SUBJECT_AGENT";

    /**
     * 判定自助终端是否属于代理商的orgtype值(湖北)
     */
    public static final String SH_ORGTYPE_AGENT = "SH_ORGTYPE_AGENT";
    
    // add end hWX5316476 2013/10/11 OR_huawei_201305_1248
    
    // add begin yWX163692 2013/11/6 OR_NX_201303_280  宁夏自助终端优化需求之在线开户
    /**
     * 自助终端在线开户
     */
    public static final String SH_PROD_INSTALL_NX = "SH_PROD_INSTALL_NX";
    // add end yWX163692 2013/11/6 OR_NX_201303_280  宁夏自助终端优化需求之在线开户
    
    /**
     * 自助终端空白卡开户
     */
    public static final String SH_CARD_INSTALL = "SH_CARD_INSTALL";
    
    /**
     * 自助终端开户选择产品模板
     */
    public static final String SH_CHOOSE_PRODTEMPLET = "SH_CHOOSE_PRODTEMPLET";
    
    // 关闭自动交费信息提示 山东
    // create yWX163692 2013年11月19日 OR_SD_201309_940
    public static final String SH_EASYPAYCHANGE_UNBINDTIP = "SH_EASYPAYCHANGE_UNBINDTIP";
    // end yWX163692 2013年11月19日 OR_SD_201309_940
    
    // add by hWX5316476 2014-2-19 OR_NX_201402_306 宁夏自助终端优化需求_弱密码改造需求
    // 登录时用户密码为弱密码提示信息(宁夏)
    public static final String SH_WEAKPWD_PROMPT = "SH_WEAKPWD_PROMPT";
    // add end hWX5316476 2014-2-19 OR_NX_201402_306 宁夏自助终端优化需求_弱密码改造需求
    
    // add begin hWX5316476 2014-2-28 OR_SD_201402_278 申请为所有自助系统打印的发票添加大写发票金额
    // 缴费金额转换为大写开关(1：开启  0：关闭)(山东)
    public static final String SH_TOCAPITALFEE = "SH_TOCAPITALFEE";
    // add end hWX5316476 2014-2-28 OR_SD_201402_278 申请为所有自助系统打印的发票添加大写发票金额
    
    // add begin hWX5316476 2014-3-24 OR_huawei_201312_668 [营改增 营业 二阶段][湖北] 缴费接口_票据处理_外围渠道改造
    public static final String SH_DEALADDEDTAX = "SH_DEALADDEDTAX";
    // add end hWX5316476 2014-3-24 OR_huawei_201312_668 [营改增 营业 二阶段][湖北] 缴费接口_票据处理_外围渠道改造
    
    // add begin hWX5316476 2014-3-26  OR_NX_201403_1590_宁夏_[自助终端需求]账单查询改造需求
    // 账单信息通信量明细资费政策名称代替套餐名称标识（0：关闭 1：开启）
    public static final String  SH_BILLFEENAMESWITCH = "SH_BILLFEENAMESWITCH";
    // add end hWX5316476 2014-3-26 OR_NX_201403_1590_宁夏_[自助终端需求]账单查询改造需求
    
    // add begin hWX5316476 2014-4-10 OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
    // 湖北统一接口平台转EBUS开关（1：开启  0：关闭）
    public static final String SH_TRANS_EBUS_SWITCH = "SH_TRANS_EBUS_SWITCH";
    
    // 支持统一接口平台转EBUS接口列表
    public static final String EBUS_INTERFACE_LIST = "ebusInterfaceList";
    
    // 使用统一接口平台转EBUS接口区分开关（1：开启  0：关闭）
    public static final String SH_EBUS_INTERFACE_SWITCH = "SH_EBUS_INTERFACE_SWITCH";
    // add end hWX5316476 2014-4-10 OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
    //add begin sWX219697 2014-04-30 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
    //山东账单邮件下发接口开关（1：开启，0：关闭）
    public static final String SEND_BILL_MAIL_SWITCH = "SEND_BILL_MAIL_SWITCH";
    
    //山东详单邮件下发接口开关（1：开启，0：关闭）
    public static final String SEND_RECORDS_MAIL_SWITCH = "SEND_RECORDS_MAIL_SWITCH";
    
    //山东详单接口下发，是否发送短信提醒（0：发送，1：不发送）
    public static final String SEND_RECORDS_NOSMS = "SEND_RECORDS_NOSMS";
    
    //发送帐单至139邮箱接口业务id
    public static final String SEND_BILL_BUSI_ID = "SEND_BILL_BUSI_ID";
    
    //发送详单至139邮箱接口 业务id
    public static final String SEND_RECORDS_BUIS_ID = "SEND_RECORDS_BUIS_ID";
    //add end sWX219697 2014-04-30 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
    
    //add begin sWX219697 2014-05-09 OR_SD_201404_777 
    //山东 用户没有开通积分计划时提示的信息
    public static final String SCORE_NOT_OPEN_MSG = "SCORE_NOT_OPEN_MSG";
    
    //山东用户是否开通积分计划接口 产品id
    public static final String SCORE_PLAN_PROD_ID = "SCORE_PLAN_PROD_ID";
    public static final String SCORE_PLAN_SWITCH = "SCORE_PLAN_SWITCH";
    //add end sWX219697 2014-05-09 OR_SD_201404_777 
    
    // add begin by zKF69263 on 2014-06-03 for OR_SD_201404_563 关于对易充值安全验证及页面进行优化调整的需求
    // 短信随机码连续输入错误的最大次数
    public static final String SH_SENDRANDOMERR_MAXTIMES = "SH_SENDRANDOMERR_MAXTIMES";
    // add end by zKF69263 on 2014-06-03 for OR_SD_201404_563 关于对易充值安全验证及页面进行优化调整的需求
    
    // Add begin by wWX217192 on 20140522 for OR_huawei_201404_1108  营业厅全业务流程优化-业务分流(自助终端)_打印月结发票
    // 山东月结发票
    public static final String MONTHINVOICE_PRINT_SD = "MONTHINVOICE_PRINT";
    // Add end by wWX217192 on 20140522 for OR_huawei_201404_1108  营业厅全业务流程优化-业务分流(自助终端)_打印月结发票
    
    // 补打发票信息查询
    public static final String OPERTYPE_QRYPRINTINVOICE = "SHQryPrintInvoice";
    
    // Add begin by wWX217192 on 20140619 for OR_SD_201403_1491 山东智能服务营销系统_电渠配套改造
    // 事件编码
    public static final String SH_COMPAIGN_WEBSERVICE_EVENTCODE = "SH_COMPAIGN_WEBSERVICE_EVENTCODE";
    
    // 调用地址WSURL
    public static final String SH_COMPAIGN_WEBSERVICE_WSURL = "SH_COMPAIGN_WEBSERVICE_WSURL";
  
    // 调用方法
    public static final String SH_COMPAIGN_WEBSERVICE_OPERATION = "SH_COMPAIGN_WEBSERVICE_OPERATION";
    
    // 启用山东只能服务营销系统的开关
    public static final String SH_COMPAIGN_WEBSERVICE_SWITCH = "SH_COMPAIGN_WEBSERVICE_SWITCH";
    
    // 命名空间
    public static final String SH_COMPAIGN_WEBSERVICE_OMSERNS = "SH_COMPAIGN_WEBSERVICE_OMSERNS";
    
    // WDSL文件的命名空间
    public static final String SH_COMPAIGN_WEBSERVICE_OMXSDNS = "SH_COMPAIGN_WEBSERVICE_OMXSDNS";
    // Add end by wWX217192 on 20140619 for OR_SD_201403_1491 山东智能服务营销系统_电渠配套改造
    
    //add by sWX219697 2014-6-19 14:48:59 OR_huawei_201404_1118 山东_[自助终端]_支撑代理商空中充值续费
    //代理商充值时可选择的金额，用于字典查询
    public static final String AGENT_SELECT_MONEY = "agentSelectMoney";
    
    //单笔充值的最低金额
    public static final String AGENT_MIN_MONEY = "AGENT_MIN_MONEY";
    
    //代理商充值提示语 查询代理商信息失败
    public static final String AGENT_QRY_INFO_ERR_MSG = "AGENT_QRY_INFO_ERR_MSG";
    
    //代理商充值提示语 银联扣款成功但交费失败的提示语
    public static final String AGENT_CHARGE_ERR_MSG = "AGENT_CHARGE_ERR_MSG";
    
    //代理商充值业务,用于记录日志
    public static final String AGENT_CHARGE = "AGENT_CHARGE";
    //add end sWX219697 2014-6-19 14:48:59 OR_huawei_201404_1118 山东_[自助终端]_支撑代理商空中充值续费

    // Add begin by jWX216858 on 20140623 for OR_HUB_201405_829_湖北_[营改增]自助终端提供增值税月结发票打印
    // 湖北月结发票
    public static final String MONTHINVOICE_PRINT_HUB = "MONTHINVOICE_PRINT_HUB";
    // Add end by jWX216858 on 20140623 for OR_HUB_201405_829_湖北_[营改增]自助终端提供增值税月结发票打印

    // Add begin by jWX216858 on 20140623 forOR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印
    // 宁夏月结发票
    public static final String MONTHINVOICE_PRINT_NX = "MONTHINVOICE_PRINT_NX";
    // Add end by jWX216858 on 20140623 forOR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印

    // add begin hWX5316476 2014-06-25  OR_SD_201405_849_山东_关于在营业厅增加实名制认证的功能
    // 实名制认证通话记录验证提示信息
    public static final String SH_REALNAMEREG_CALLRECORD_NOTE = "SH_REALNAMEREG_CALLRECORD_NOTE";
    
    // 实名制认证充值记录验证提示信息
    public static final String SH_REALNAMEREG_CHARGE_NOTE = "SH_REALNAMEREG_CHARGE_NOTE";
    
    // 实名制第二次认证菜单打开一次可以验证次数
    public static final int SECONDREALNAMEREG_MAXTIMES = 5;
    // add end hWX5316476 2014-06-25  OR_SD_201405_849_山东_关于在营业厅增加实名制认证的功能
    
    //add begin sWX219697 2014-7-3 OR_HUB_201406_1115_湖北跨运营商携号转网
    //湖北携号转网用户 订购的业务不在白名单中时
    public static final String TRANSIN_MSG = "TRANSIN_MSG";
    //add end sWX219697 2014-7-3 OR_HUB_201406_1115_湖北跨运营商携号转网
    
    // add begin hWX5316476 2014-07-02 OR_huawei_201407_85_山东_营业厅全业务流程优化-业务分流(自助终端)_停开机去掉短信验证码
    // 停开机业务是否去掉短信验证码开关（1开启：去掉短信验证 0关闭：保留短信验证）
    public static final String CLOSESMSCHK_STOPOPEN_SWITCH = "CLOSESMSCHK_STOPOPEN_SWITCH";
    // add end hWX5316476 2014-07-02 OR_huawei_201407_85_山东_营业厅全业务流程优化-业务分流(自助终端)_停开机去掉短信验证码
    
    // add begin hWX5316476 2014-07-02 OR_huawei_201407_86 营业厅全业务流程优化-业务分流(自助终端)_国际长话及漫游业务屏蔽开通功能
    // 屏蔽开通业务的按钮列表
    public static final String SHIELDOPEN_LIST = "shieldOpenGroup";
    // add end hWX5316476 2014-07-02 OR_huawei_201407_86 营业厅全业务流程优化-业务分流(自助终端)_国际长话及漫游业务屏蔽开通功能
    
    // add begin wWX217192 2014-07-15  V200R003C10LG0601 OR_huawei_201406_338_营业厅全业务流程优化-业务分流(自助终端)_非实名制补卡
    /**
     * 非实名制认证
     */
    public static final String BUSITYPE_NOREALNAMEREG = "SHNoRealNameReg";
    // add end wWX217192 2014-07-15  V200R003C10LG0601  OR_huawei_201406_338_营业厅全业务流程优化-业务分流(自助终端)_非实名制补卡
    
    // add begin g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
    public static final String DATE_PATTERN_YYYYMM = "yyyyMM";
    
    public static final String DATE_PATTERN_YYYYMMDD = "yyyyMMdd";
    
    public static final String DATE_PATTERN_YYYYMMDD_CH = "yyyy年MM月dd日";
    
    public static final String DATE_PATTERN_YYYYMM_1 = "yyyy-MM";
    
    public static final String DATE_PATTERN_MMDD = "MM-dd";
    
    public static final String DATE_PATTERN_CHMD = "M月d日";
    
    public static final String CDRTYPE_FIXFEE = "FIXFEE";    
    public static final String CDRTYPE_GSM = "GSM";
    public static final String CDRTYPE_SMS = "SMS";
    public static final String CDRTYPE_GPRSWLAN = "GPRSWLAN";
    public static final String CDRTYPE_ISMG = "ISMG";
    public static final String CDRTYPE_INFOFEE = "INFOFEE";
    public static final String CDRTYPE_OTHERFEE = "OTHERFEE";
    public static final String CDRTYPE_DISCOUNT = "DISCOUNT";
    
    /**
     * 新详单查询功能的割接月份的参数ID
     */
    public static final String NEWCDR_EFFECTMONTH = "SH_NEWCDR_EFFECTMONTH";
    // add end g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
    
    //add begin sWX219697 2014-8-9 09:32:06 OR_SD_201408_20_ISSS平台对接自助终端的需求(山东)
    //事件编码
    public static final String SH_CHARGE_WEBSERVICE_EVENTCODE = "SH_CHARGE_WEBSERVICE_EVENTCODE";
    
    //wsurl
    public static final String SH_CHARGE_WEBSERVICE_WSURL = "SH_CHARGE_WEBSERVICE_WSURL";
    
    //调用方法
    public static final String SH_CHARGE_WEBSERVICE_OPERATION = "SH_CHARGE_WEBSERVICE_OPERATION";
    
    //服务端命名空间
    public static final String SH_CHARGE_WEBSERVICE_SERNS = "SH_CHARGE_WEBSERVICE_SERNS";
    
    //命名空间
    public static final String SH_CHARGE_WEBSERVICE_XSDNS = "SH_CHARGE_WEBSERVICE_XSDNS";
    
    //功能开关
    public static final String SH_CHARGE_WEBSERVICE_SWITCH = "SH_CHARGE_WEBSERVICE_SWITCH";
    //add begin sWX219697 2014-8-9 09:32:06 OR_SD_201408_20_ISSS平台对接自助终端的需求(山东)
    
    // add begin jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
    // 打印收据开关
    public static final String SH_PRINTRECEIPT_SWITCH = "SH_PRINTRECEIPT_SWITCH";
    
    // 宁夏打印收据日志
    public static final String RECEIPT_PRINT = "RECEIPT_PRINT";
    // add end jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
    
    //add begin sWX219697 2014-9-15 17:53:52 OR_SD_201409_556_自助终端营销功能优化
    //wsurl
    public static final String SH_ISSS_FEEDBACK_WSURL = "SH_ISSS_FEEDBACK_WSURL";
    
    //调用方法
    public static final String SH_ISSS_FEEDBACK_OPERATION = "SH_ISSS_FEEDBACK_OPERATION";
    
    //服务端命名空间
    public static final String SH_ISSS_FEEDBACK_IMPLNS = "SH_ISSS_FEEDBACK_IMPLNS";
    
    //客户端命名空间
    public static final String SH_ISSS_FEEDBACK_XSDNS = "SH_ISSS_FEEDBACK_XSDNS";
    
    //客户端命名空间
    public static final String SH_ISSS_FEEDBACK_XSDNS1 = "SH_ISSS_FEEDBACK_XSDNS1";
    
    //页面提示信息
    public static final String SH_ISSS_DIALOG_MSG = "SH_ISSS_DIALOG_MSG";
    
    // 请求报文中的密码，可配置 add by lKF60882 OR_SD_201609_165_[营销平台升级]自助终端配合改造需求
    public static final String SH_ISSS_PASSWORD = "SH_ISSS_Password";
    //add end sWX219697 2014-9-15 17:53:52 OR_SD_201409_556_自助终端营销功能优化
    
    // GprsWlan显示正常和优惠时段流量开关参数（1：显示  0：不显示）
    public static final String SH_GPRSWLAN_SHOWFLUX = "SH_GPRSWLAN_SHOWFLUX";
    
    // add begin wWX217192 2014-09-16 V200R003C10LG0901 OR_HUB_201403_1773 自助终端LOGO更换及屏保一键更新功能
    // 终端组屏保资源配置按组显示还是按省份显示 1：按省份 0：按组
    public static final String SH_SCREEN_FLAG = "SH_SCREEN_FLAG";
    // add end wWX217192 2014-09-16 V200R003C10LG0901 OR_HUB_201403_1773 自助终端LOGO更换及屏保一键更新功能

    // add begin jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_山东_关于自助终端产品变更功能添加4G自选套餐以及修改GPRS和4G互斥的功能（全业务流程优化）
    // 语音通话受理日志
    public static final String SH_VOICECALLREC = "SH_VOICECALLREC";
    
    // 上网流量
    public static final String SH_GPRSWLANREC = "SH_GPRSWLANREC";
    // add end jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_山东_关于自助终端产品变更功能添加4G自选套餐以及修改GPRS和4G互斥的功能（全业务流程优化）

    // add begin hWX5316476 2014-9-26 V200R003C10LG1001 OR_SD_201407_1077_自助终端管理平台告警及维护管理优化的需求
    // 开机心跳检测周期 每隔SH_ON_OFF_FREQUENCY分检测一次，单位：分
    public static final String SH_ON_OFF_FREQUENCY = "SH_ON_OFF_FREQUENCY";
    // add end hWX5316476 2014-9-26 V200R003C10LG1001 OR_SD_201407_1077_自助终端管理平台告警及维护管理优化的需求
    
    // add begin hWX5316476 2014-12-12 OR_SD_201410_482_SD_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
    // 4G套餐描述查询
    public static final String SH_PACKAGE4G_DESC = "SH_PACKAGE4G_DESC";
    
    // 特惠业务包办理
    public static final String SH_PRIV_PACK_REC = "SH_PRIV_PACK_REC";
    // add end hWX5316476 2014-12-12 OR_SD_201410_482_SD_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
    
    // add begin wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
    // 和包易充值查询签约关系
    public static final String SH_HEBAO_SIGNRELATIONSHIP = "SH_HEBAO_SIGNRELATIONSHIP";
    
    // 和包易充值发送短信验证码
    public static final String SH_HEBAO_SENDRANDOMPWD = "SH_HEBAO_SENDRANDOMPWD";
    
    // 和包易充值签约
    public static final String SH_HEBAO_SIGN = "SH_HEBAO_SIGN";
    
    // 易充值与和包易充值所占比例
    public static final String SH_EASTPAY_PERCENTAGE = "SH_EASTPAY_PERCENTAGE";
    
    // 用户信息页面的温馨提示信息'
    public static final String SH_HBUSERINFO_TIPS = "SH_HBUSERINFO_TIPS";
    
    // 易充值管理页面取消绑定提示信息
    public static final String SH_UNSIGNHB_TIPS = "SH_UNSIGNHB_TIPS";
    
    // 修改自动交费金额的弹出框提示信息
    public static final String SH_AUTOCHARGEHB_TIPS = "SH_AUTOCHARGEHB_TIPS";
    
    // 和包易充值最低触发余额
    public static final String SH_HEBAO_TRIGAMT = "SH_HEBAO_TRIGAMT";
    
    // 和包易充值自动交费金额
    public static final String SH_HEBAO_DRAWAMT = "SH_HEBAO_DRAWAMT";
    // add end wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
    
    //add begin sWX219697 2014-12-16 14:48:45 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
    //易充值管理页面开关， 1：开启
    public static final String SH_EASYPAYMNG_SWITCH = "SH_EASYPAYMNG_SWITCH";
    
    //预付费用户自动交费触发余额
    public static final String SH_EASYPAY_FZ = "SH_EASYPAY_FZ";
    
    //预付费用户自动交费划扣金额
    public static final String SH_EASYPAY_HK = "SH_EASYPAY_HK";
    
    //温馨提示配置项
    public static final String SH_EASYPAY_MNG_TIP = "SH_EASYPAY_MNG_TIP";
    //add end sWX219697 2014-12-16 14:52:12 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
    
    // 图片存放ftp路径
    public static final String PHONEIMGPATH = "recommendProdPath";
    // add end jWX216858 2014/12/25 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
    
    // add begin jWX216858 2014/12/25 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
    // 4G终端推荐图片存放相对路径
    public static final String PHONEIMG_FILE_RELATIVELY_PATH = "ssResources/phoneResource";

    // add begin wWX217192 2015-01-06 OR_HUB_201408_620 自助终端界面改版优化
    // 湖北自助终端首页的中间部分的菜单数量
    public static final int CENTER_MENU_MAX_HUB = 6;
    
    // 湖北自助终端首页的右侧部分的菜单数量
    public static final int RIGHT_MENU_MAX_HUB = 6;
    // add end wWX217192 2015-01-06 OR_HUB_201408_620 自助终端界面改版优化
    
    // add begin jWX216858 2015/1/16 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
    // 手机号码最大数量
    public static final String SH_TELNUM_MAXCOUNT = "SH_TELNUM_MAXCOUNT";
    // add end jWX216858 2015/1/16 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求

    // add begin hWX5316476 2015-1-28 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
    // 原营销推荐活动开关 1：开  0：关 
    public static final String SH_ACT_RECOMMEND = "SH_ACT_RECOMMEND";
    
    // 新营销推荐活动开关1：开  0：关
    public static final String SH_ACT_RECOMMEND_NEW = "SH_ACT_RECOMMEND_NEW";
    
    // 新营销推荐活动菜单组
    public static final String ACTMENUNEW = "ActMenuNew";
    
    // 查询新营销推荐活动列表
    public static final String BUSITYPE_QRYRECACTLIST = "SHQryRecActList";
    // add end hWX5316476 2015-1-28 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
    
    // add begin jWX216858 2015-2-3 OR_HUB_201501_167 关于自助终端菜单层级优化需求
    // 湖北套餐信息查询流程优化，改为只查当前月 1：开  0：关 
    public static final String SH_COMBOINFO_SWITCH = "SH_COMBOINFO_SWITCH";
    // add end jWX216858 2015-2-3 OR_HUB_201501_167 关于自助终端菜单层级优化需求
    
    // add begin wWX217192 2015-03-02 OR_HUB_201502_161_关于在自助终端界面新增倒计时提醒功能的需求
    // 湖北倒计时功能的开关 
    public static final String SH_SWITCH_COUNTDOWN = "SH_SWITCH_COUNTDOWN";
    
    // 湖北倒计时功能倒计时开始时的剩余毫秒数
    public static final String SH_COUNTDOWN_LENGTH = "SH_COUNTDOWN_LENGTH";
    
    // 湖北倒计时功能提示信息
    public static final String SH_COUNTDOWN_INFO = "SH_COUNTDOWN_INFO";
    // add end wWX217192 2015-03-02 OR_HUB_201502_161_关于在自助终端界面新增倒计时提醒功能的需求
    
    // add begin wWX217192 2015-03-10 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
    // 自助选号调用webservice接口的开关
    public static final String SH_WEBSERVICE_CHOOSETEL_SWITCH = "SH_WEBSERVICE_CHOOSETEL_SWITCH";
    
    // 调用商城webservice接口的命名空间
    public static final String SH_CHOOSETEL_WEBSERVICE_OMSERNS = "SH_CHOOSETEL_WEBSERVICE_OMSERNS";
    
    // 调用商城webservice接口的XDS命名空间
    public static final String SH_CHOOSETEL_WEBSERVICE_OMXSDNS = "SH_CHOOSETEL_WEBSERVICE_OMXSDNS";
    
    // 查询号码wsUrl
    public static final String SH_QUERYNUM_WEBSERVICE_WSURL = "SH_QUERYNUM_WEBSERVICE_WSURL";
    
    // 返回行数
    public static final String SH_CHOOSETEL_QUERYNUM_ROWNUM = "SH_CHOOSETEL_QUERYNUM_ROWNUM";
    
    // webservice接口调用成功的返回码
    public static final String SH_CHOOSETEL_SUCCESSCODE = "100";
    
    // 暂选号码wsURL
    public static final String SH_PICKNUM_WEBSERVICE_WSURL = "SH_PICKNUM_WEBSERVICE_WSURL";
    
    // 预订号码wsURL
    public static final String SH_BOOKNUM_WEBSERVICE_WSURL = "SH_BOOKNUM_WEBSERVICE_WSURL";
    
    // 查询号码调用的方法名
    public static final String SH_QUERYNUM_OPERATION = "SH_QUERYNUM_OPERATION";
    
    // 暂选号码调用的方法名
    public static final String SH_PICKNUM_OPERATION = "SH_PICKNUM_OPERATION";
    
    // 预约号码调用的方法名
    public static final String SH_BOOKNUM_OPERATION = "SH_BOOKNUM_OPERATION";
    
    // 查询号码时预存款值
    public static final String SH_CHOOSETEL_QUERYNUM_PREFEE = "SH_CHOOSETEL_QUERYNUM_PREFEE";
    
    // 查询自定义号码的开关
    public static final String SH_WEBSERVICE_QRYMANUNUM_FLAG = "SH_WEBSERVICE_QRYMANUNUM_FLAG";
    // add end wWX217192 2015-03-10 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
    
    // add begin zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能
    // 现金稽核开关
    public static final String SH_CASHAUDIT_SWITCH = "SH_CASHAUDIT_SWITCH";
    
    // 现金稽核人密码
    public static final String SH_CASHAUDIT_PASSWORD = "SH_CASHAUDIT_PASSWORD";
    
    // 山东现金稽核打印描述
    public static final String SH_CASHAUDIT_PRINTDESC = "SH_CASHAUDIT_PRINTDESC";
    // add end zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能 
    
    // add begin hWX5316476 2015-3-25 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知 
    // 跨省充值缴费业务类型
    public static final String BUSITYPE_NONLOCAL_CHARGE = "nonlocalPhone";
    
    // 当前省份编码
    public static final String SH_CURRPROVINCE_CODE = "SH_CURRPROVINCE_CODE";
    
    // 扣款或者投币成功，BOSS缴费成功
    public static final String CHARGE_SUCCESS = "11";
    
    // 扣款或者投币成功，BOSS缴费失败
    public static final String PAYSUCCESS_CHARGEERROR = "10";
    
    // 扣款或者投币失败或未交费
    public static final String CHARGE_ERROR = "00";
    
    // 业务日志的业务状态 0：成功
    public static final String RECSTATUS_SUCCESS = "0";
    
    // 业务日志的业务状态 1：失败
    public static final String RECSTATUS_FALID = "1";
    // add end hWX5316476 2015-3-25 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知 

    // add begin jWX216858 2015-3-27 OR_SD_201501_1063 自助终端支撑连缴功能优化
    // 话费连缴功能开关
    public static final String SH_MOREPHONE_SWITCH = "SH_MOREPHONE_SWITCH";
    
    // 连缴人数
    public static final String SH_MOREPHONE_COUNT = "SH_MOREPHONE_COUNT";
    
    // 连缴日志
    public static final String SH_MOREPHONE = "SH_MOREPHONE";
    // add end jWX216858 2015-3-27 OR_SD_201501_1063 自助终端支撑连缴功能优化
    
    // add begin zKF69263 2015-05-07 OR_SD_201503_333_SD_自助终端活动受理预存赠送
    // 业务类型：预存赠
    public static final String ACCEPTTYPE_PRESTORED_REWARD = "ZZHD";
    
    // 业务类型：开户
    public static final String ACCEPTTYPE_CARDINSTALL = "ZZKH";
    
    // 业务类型：补卡
    public static final String ACCEPTTYPE_REISSUECARD = "ZZBK";
    // add end zKF69263 2015-05-07 OR_SD_201503_333_SD_自助终端活动受理预存赠送
    
    //湖北跨省交费一级boss接口URL
    public static final String NONLOCALCHARGE_BOSSURL = "SH_NonlocalCharge_InterBOSSURL";
    
    //跨省交费最低金额
    public static final String NONLOCAL_CHARGE_MIN = "SH_nonlocalCharge_min";
    
    //跨省交费最高金额
    public static final String NONLOCAL_CHARGE_MAX = "SH_nonlocalCharge_max";
    
    //是否查询用户可转换的主体产品列表
    public static final String MAINPROD_CHANGE_SWITCH = "sh_mainProdChange_switch";

    // add begin wWX217192 2015-04-29 OR_SD_201502_373_山东_关于自助终端承载和娱乐包新业务的支撑需求
    // SP业务办理的标志位
    public static final String SH_SPRECEPTION_FLAG = "SH_SPRECEPTION_FLAG";
    // add end wWX217192 2015-04-29 OR_SD_201502_373_山东_关于自助终端承载和娱乐包新业务的支撑需求
    
    // add begin wWX217192 2015-05-04 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
    // 用户一次可最多购买的有价卡数量
    public static final String SH_VALUECARD_MAXNUM = "SH_VALUECARD_MAXNUM";
    
    // 电子有价卡销售的面值列表的字典项GROUPID
    public static final String FEE_EVCARD = "FEE_EVCARD";
    
    // 电子有价卡类型
    public static final String VALUECARD_TYPE = "ValueCardType";
    
    // 电子有价卡购买的业务类型
    public static final String VALUECARD_RECLOG = "valueCard";
    
    // 电子有价卡购买温馨提示信息
    public static final String SH_VALUECARD_TIPS = "SH_VALUECARD_TIPS";
    
    // add end wWX217192 2015-05-04 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
    
    //add begin sWX219697 2015-05-11 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
    //有价卡充值业务类型
    public static final String VALUECARD_CHARGE = "recValueCardCharge";
    
    //湖北省代码
    public static final String VALUECARD_HUB_PROVCODE = "270";
    //add end sWX219697 2015-05-11 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造

    // add begin jWX216858 2015-5-13
    // js版本号，例：20150513 解决IE客户端缓存未清理导致JS未更新
    public static final String SH_JSVERSION = "SH_JSVERSION";
    // add end jWX216858 2015-5-13
    
    // add begin jWX216858 2015-5-25 OR_SD_201504_452_山东_ISSS自助终端UCD改造
    // 营销推荐活动超时时间，超时自动关闭(默认10000，单位毫秒)
    public static final String SH_CLOSESERVICE_OUTTIME = "SH_CLOSESERVICE_OUTTIME";
    // add end jWX216858 2015-5-25 OR_SD_201504_452_山东_ISSS自助终端UCD改造
    
    //add begin sWX219697 2015-5-29 10:50:36 OR_SD_201505_61自助终端上增加积分兑换电子券
    //积分兑换业务类型
    public static final String SH_SCOREEXECASH = "SH_SCOREEXECASH";
    
    //积分兑换电子券提示信息
    public static final String SH_SCOREEXCHANGE_TIP = "SH_SCOREEXCHANGE_TIP";
    
    //查询出的电子券信息为空时的提示信息
    public static final String SH_SCOREEXCHANGE_EMPTY_TIP = "SH_SCOREEXCHANGE_EMPTY_TIP";
    //add end sWX219697 2015-5-29 10:50:42 OR_SD_201505_61自助终端上增加积分兑换电子券
    
    //使用预约号码进行开户
    public static final String BOOKED_TELNUM = "bookedTelnum";

    // add begin wWX217192 2015-06-08 OR_SD_201503_942_山东_自助终端提示换USIM
    // USIM卡是否校验的开关
    public static final String SH_USIMCHANGE_FLAG = "SH_USIMCHANGE_FLAG";
    // add end wWX217192 2015-06-08 OR_SD_201503_942_山东_自助终端提示换USIM
    
    // add begin hWX5316476 2015-6-10 OR_SD_201505_1022_山东电子充值卡改造需求_自助终端改造
    // 山东省份编码
    public static final String VALUECARD_SD_PROVCODE = "531";
    // add end hWX5316476 2015-6-10 OR_SD_201505_1022_山东电子充值卡改造需求_自助终端改造
    
    // add begin hWX5316476 2015-6-24 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面
    // 正在处理业务等待提示信息
    public static final String REC_WAITLOADING_MSG = "REC_WAITLOADING_MSG";
    
    // 正在查询等待提示信息
    public static final String QRY_WAITLOADING_MSG = "QRY_WAITLOADING_MSG";
    // add end hWX5316476 2015-6-24 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面
    
    // add begin jWX216858 2015-6-29 OR_SD_201506_152_山东_在自助终端增加“热门APP应用”下载
    // 热门app图片存放路径
    public static final String APPIMGPATH = "appImgPath";
    
    // 热门app下载业务类型
    public static final String SH_HOTAPPDOWNLOAD = "SH_HOTAPPDOWNLOAD";
    
    // 热门app下载短信发送模板
    public static final String SH_APPDOWNLOAD_CONTENT = "SH_APPDOWNLOAD_CONTENT";
    // add end jWX216858 2015-6-29 OR_SD_201506_152_山东_在自助终端增加“热门APP应用”下载
    
    // add begin qWX279398 2015-7-30 OR_SD_201504_1108自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示
    /**
     * 硬件开关
     */
    public static final String SH_HARDWARE_ISOPEN = "SH_HARDWARE_ISOPEN";
    // add end qWX279398 2015-7-30 OR_SD_201504_1108自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示
    
    // add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
    /**
     * 湖北随机密码发送时间间隔，单位：秒
     */
    public static final String SH_SMSCODE_TIME = "SH_SMSCODE_TIME";
    
    /**
     * 湖北错误页面特殊字符校验开关
     */
    public static final String SH_ERROR_FLAG = "SH_ERROR_FLAG";
    // add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
    
    /**
     * 积分一键全部兑换计算方式
     */
    public static final String SH_RTSCOREEXEALL_RULES = "SH_RTSCOREEXEALL_RULES"; 
    
    // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
    /**
     * 菜单可选登录方式样式      1：新样式 0：原先样式
     */
    public static final String SH_AVAILABLECODE_FLAG = "SH_AVAILABLECODE_FLAG";
    // modify end by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
    
    // modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
    /**
     * 缴费完成后登陆鉴权后打印发票开关        1:开启    0:关闭
     */
    public static final String SH_PRINT_INVOICENEW = "SH_PRINT_INVOICENEW";
    
    /**
     * 缴费完成后登陆鉴权后打印发票开关打开
     */
    public static final String PRINT_INVOICE_OPEN = "1";
    // modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
    
    // add begin wWX217192 2016-01-27 OR_HUB_201512_256_湖北_关于在BOSS系统中标记天猫话费充值已开票金额的需求
    // 湖北月结发票是否打印第三方支付的开关
    public static final String SH_MONTHINVOICE_HUB_THIRDPAY = "SH_MONTHINVOICE_HUB_THIRDPAY";
    
    // 湖北打印月结发票第三方开关开启1，关闭0
    public static final String SH_MONTHINVOICE_HUB_OPEN = "1";
    // add end wWX217192 2016-01-27 OR_HUB_201512_256_湖北_关于在BOSS系统中标记天猫话费充值已开票金额的需求
    
    // 渠道编码
    public static final String SH_CHANNELID = "bsacAtsv";
    
    // add begin lwx439898 2017-03-05 R005C20LG2701 OR_HUB_201802_33 业务办理类短信优化需求
    /**
     * 湖北下发短信新模板开关
     */
    public static final String SH_SMSINFO_NEWMOD = "SH_SMSINFO_NEWMOD";
    // add end lwx439898 2017-03-05 R005C20LG2701 OR_HUB_201802_33 业务办理类短信优化需求
    
    // add begin lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
    /**
     * 防短信炸弹开关
     */
    public static final String SH_ANTISMSBOMB = "AntiSMSBomb";
    
    /**
     * 短信发送频率,单位:秒
     */
    public static final String SH_SENDCODEINTERVAL = "sendCodeInterval";
    
    /**
     * 获取多长时间内只允许下发多少次的时间，单位：小时
     */
    public static final String SH_SMSNUMTIME = "smsNumTime";
    
    /**
     * 获取多长时间内只允许下发多少次的次数
     */
    public static final String SH_SMSNUM = "smsNum";
    // add end lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
}
