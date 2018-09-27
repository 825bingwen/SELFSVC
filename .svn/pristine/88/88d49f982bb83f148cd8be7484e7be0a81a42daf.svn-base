
--自助终端接入点配置
delete int_access_point where ACCESSID = 'SelfTerminalAccessID';

insert into int_access_point (ACCESSID, PROTOCOLID, ACCESSNAME, DEFCHANID, AUTHMODE, ROUTEFLAG, ROUTEFIELD, ROUTESCRIPT, EFFTIME, EXPTIME, REMARK, LOGLEVEL)
values ('SelfTerminalAccessID', 'SelfTerminalProtocolID', '自助终端接入点配置', 'bsacAtsv', 0, 0, '', '', sysdate, sysdate, '', 0);

--自助终端报文协议定义
delete int_protocol_define where PROTOCOLID = 'SelfTerminalProtocolID';

insert into int_protocol_define (PROTOCOLID, PROTOCOLNAME, PROTOCOLTYPE, ENCRYPT, ENCRYPTKEY, REQHEAD, RESPHEAD, EFFTIME, EXPTIME, REMARK)
values ('SelfTerminalProtocolID', '自助终端包头协议解析', 1, 0, '', 'SelfTerminalProtocolReq', 'SelfTerminalProtocolResp', sysdate, sysdate, '');

--业务和流程对应关系
delete int_business_define where businessid='cli_busi_sendbill' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_stopopensubs' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_cancelsp' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_productfee' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_productrec' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_spinfo' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_alarmbalance' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_chgpwd' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_alarmbalance' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_chargehistory' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_sendsmsinfo' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_package_chgcommit_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_package_chgcontent_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_pukcode' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_userstate' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_rechistory' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_zfinfo' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_calltransfer' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_sendsms' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_numregion' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_dictitem' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_scorevalue' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_promotions_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_telnum_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_telsection_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_netnbr_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_occupytel_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_chargeguide_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_billanalysis_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_check_cdrauth_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_fee_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_chargefee_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_invoice_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_package_changelist_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_cardpay' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_parametervaluebyid_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_balance_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_taocan_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_cdr' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_cidcheck' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_pwdreset' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_promotions_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_userinfo' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_qry_detailedbill_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_busi_sendbill_old' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';
delete int_business_define where businessid='cli_install_bookcertinfo_hub' and channelid='bsacAtsv' and protocolid='SelfTerminalProtocolID';

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_sendbill', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_SendBill', '账单设置', '按手机号码', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_stopopensubs', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_busi_stopopensubs', '停开机', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_cancelsp', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_ChangeSubsMonServ', '增值业务统一退订', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_productfee', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_GetNotesByProdID', '产品资费信息查询', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_productrec', 'bsacAtsv', 'SelfTerminalProtocolID', 'IncProductSrv2', 0, 122, 'CLT_bsacAtsv_IncProductSrv2', '产品受理通用接口', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_spinfo', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qry_spinfo', '  增值业务统一查询', '采用手机号码路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_alarmbalance', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_busi_alarmbalance', '余额提醒设置接口', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_chgpwd', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_busi_chgpwd', '  密码修改', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_alarmbalance', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_QryAlarmBalance', '余额提醒查询接口', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_chargehistory', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qry_chargehistory', '缴费历史查询', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_sendsmsinfo', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_SendSMSInfo', '短信下发接口', '根据参数下发短信，采用手机号路由，', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_package_chgcommit_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_Package_ChgCommit_Hub', '自助终端套餐转换接口', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_package_chgcontent_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_Package_ChgContent_Hub', '自助终端套餐转换优惠/服务/产品变更清单接口', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_pukcode', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_qry_pukcode', 'PUK码查询', '采用手机号码路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_userstate', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_qry_userstate', '  当前用户状态查询', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_rechistory', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qry_rechistory', '受理历史查询', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_zfinfo', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qry_zfinfo', '本机品牌资费及已开通服务、优惠查询', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_calltransfer', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_CallTransfer', '呼叫转移', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_sendsms', 'bsacAtsv', 'SelfTerminalProtocolID', 'SendSms', 0, 122, 'CLT_bsacAtsv_SendSms', '短信下发接口', '根据指定短信内容下发短信，采用手机号路由，', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_numregion', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_NetGetAC', '手机归属地查询', '采用地区路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_dictitem', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qryDictItem', '查询字典数据接口', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_scorevalue', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_QrySubsDetailAvailScore_Hub', '用户积分查询', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_promotions_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_QryPromotions_Hub', '获取用户优惠列表', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_telnum_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_QryTelnums_Hub', '自助选号号码提取接口', '采用地区路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_telsection_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_QryTelsection_Hub', '自助选号获取网段接口口', '采用地区路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_netnbr_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_QryNetnbr_Hub', '自助选号获取网号接口', '采用地区路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_occupytel_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_BusiOccupytel_Hub', '自助选号号码预定接口', '采用地区路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_chargeguide_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_GetChargeGuide_Hub', '湖北资费推荐', '采用地区路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_billanalysis_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_QryBillAnalysis_Hub', '账单分析查询', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_check_cdrauth_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_CheckQryCdr_Hub', '自助终端清单查询权限验证', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_fee_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_Qry_Fee_Hub', '自助终端缴费查询接口', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_chargefee_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_Busi_ChargeFee_Hub', '自助终端缴费接口', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_invoice_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_Chargefee_Invoice_Hub', '自助终端取发票接口', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_package_changelist_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_Package_ChangeList_Hub', '自助终端获取用户可转换套餐清单接口', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_cardpay', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_Cardpay', '充值卡缴费', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_parametervaluebyid_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_QryParameterValue_Hub', '自助终端获取系统参数', '采用区号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_balance_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_Qry_Balance_Hub', '帐户余额信息查询接口', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_taocan_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_Qry_Taocan_Hub', '自助终端套餐信息查询接口', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_cdr', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_Qry_Cdr_Hub', '自助终端详单查询接口', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_cidcheck', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_busi_cidcheck', ' 身份证号认证', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_pwdreset', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_busi_pwdreset', '  密码重置', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_promotions_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_modifyprivilege_Hub', '优惠办理', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_userinfo', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_Qry_UserInfo_Hub', '校验用户密码并提取用户信息', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_detailedbill_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_Qry_DetailedBill_Hub', '自助终端月账单查询查询接口', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_sendbill_old', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_SendBill', '账单寄送', '采用手机号路由', sysdate, sysdate, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_install_bookcertinfo_hub', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 122, 'CLT_bsacAtsv_InstallBookCertInfo_Hub', '自助终端身份证入网预约', '采用区号路由', sysdate, sysdate, 0, 1);

--流程定义
delete int_flow_define where flowid='CLT_bsacAtsv_BusiOccupytel_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Busi_ChargeFee_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_CallTransfer' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Cardpay' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_ChangeSubsMonServ' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Chargefee_Invoice_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Chargefee_Invoice_Hub' and sortorder='1';
delete int_flow_define where flowid='CLT_bsacAtsv_CheckQryCdr_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_GetChargeGuide_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_GetNotesByProdID' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_IncProductSrv2' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_IncProductSrv2' and sortorder='1';
delete int_flow_define where flowid='CLT_bsacAtsv_InstallBookCertInfo_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_NetGetAC' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Package_ChangeList_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Package_ChgCommit_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Package_ChgContent_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Package_ChgContent_Hub' and sortorder='1';
delete int_flow_define where flowid='CLT_bsacAtsv_QryAlarmBalance' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_QryBillAnalysis_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_QryNetnbr_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_QryParameterValue_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_QryPromotions_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_QryPromotions_Hub' and sortorder='1';
delete int_flow_define where flowid='CLT_bsacAtsv_QrySubsDetailAvailScore_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_QryTelnums_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_QryTelsection_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Qry_Balance_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Qry_Balance_Hub' and sortorder='1';
delete int_flow_define where flowid='CLT_bsacAtsv_Qry_Cdr_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Qry_Cdr_Hub' and sortorder='1';
delete int_flow_define where flowid='CLT_bsacAtsv_Qry_DetailedBill_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Qry_DetailedBill_Hub' and sortorder='1';
delete int_flow_define where flowid='CLT_bsacAtsv_Qry_Fee_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Qry_Fee_Hub' and sortorder='1';
delete int_flow_define where flowid='CLT_bsacAtsv_Qry_Taocan_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Qry_Taocan_Hub' and sortorder='1';
delete int_flow_define where flowid='CLT_bsacAtsv_Qry_UserInfo_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_Qry_UserInfo_Hub' and sortorder='1';
delete int_flow_define where flowid='CLT_bsacAtsv_SendBill' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_SendSMSInfo' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_SendSms' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_busi_alarmbalance' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_busi_chgpwd' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_busi_cidcheck' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_busi_cidcheck' and sortorder='1';
delete int_flow_define where flowid='CLT_bsacAtsv_busi_pwdreset' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_busi_stopopensubs' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_modifyprivilege_Hub' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_qryDictItem' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_qry_chargehistory' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_qry_chargehistory' and sortorder='1';
delete int_flow_define where flowid='CLT_bsacAtsv_qry_pukcode' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_qry_rechistory' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_qry_rechistory' and sortorder='1';
delete int_flow_define where flowid='CLT_bsacAtsv_qry_spinfo' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_qry_spinfo' and sortorder='1';
delete int_flow_define where flowid='CLT_bsacAtsv_qry_spinfo' and sortorder='2';
delete int_flow_define where flowid='CLT_bsacAtsv_qry_userstate' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_qry_zfinfo' and sortorder='0';
delete int_flow_define where flowid='CLT_bsacAtsv_qry_zfinfo' and sortorder='1';


insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_BusiOccupytel_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<seltelprepay>/message_request/message_body/message_content/seltelprepay/text()</seltelprepay>
<channelid>/message_request/message_body/message_content/channelid/text()</channelid>
<CredentFlag>/message_request/message_body/message_content/CredentFlag/text()</CredentFlag>
<certtype>/message_request/message_body/message_content/certtype/text()</certtype>
<certid>/message_request/message_body/message_content/certid/text()</certid>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
<batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<oid></oid>
<vidatetime></vidatetime>
<remind></remind>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<oid>./message_content/oid/insert($OID)</oid>
<vidatetime>./message_content/vidatetime/insert($VIDATETIME)</vidatetime>
<remind>./message_content/remind/insert($REMIND)</remind>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="bsacAtsv" commandid="Atsv_busi_occupytel_Hub" retcode="$RETCODE" retmsg="$RETMSG" region="$region">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<SELTELPREPAY>$seltelprepay</SELTELPREPAY>
<CHANNELID>$channelid</CHANNELID>
<CREDENTFLAG>$CredentFlag</CREDENTFLAG>
<CERTTYPE>$certtype</CERTTYPE>
<CERTID>$certid</CERTID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<OID>$OID</OID>
<VIDATETIME>$VIDATETIME</VIDATETIME>
<REMIND>$REMIND</REMIND>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Busi_ChargeFee_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<amount>/message_request/message_body/message_content/amount/text()</amount>
<pay_type>/message_request/message_body/message_content/pay_type/text()</pay_type>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content>
<dealNum></dealNum>
<dealTime></dealTime>
</message_content>
]]></templet>
<infill>
<single>
<xml>
<dealNum>./message_content/dealNum/insert($DEALNUM)</dealNum>
<dealTime>./message_content/dealTime/insert($DEALTIME)</dealTime>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Busi_ChargeFee_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<AMOUNT>$amount</AMOUNT>
<PAY_TYPE>$pay_type</PAY_TYPE>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<DEALNUM>$DEALNUM</DEALNUM>
<DEALTIME>$DEALTIME</DEALTIME>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_CallTransfer', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<dealtype>/message_request/message_body/message_content/dealtype/text()</dealtype>
<calltype>/message_request/message_body/message_content/calltype/text()</calltype>
<callednum>/message_request/message_body/message_content/callednum/text()</callednum>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Busi_CallTransfer" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$telnum</MSISDN>
<DEALTYPE>$dealtype</DEALTYPE>
<HZLX>$calltype</HZLX>
<HZHM>$callednum</HZHM>
<CALL_NUMBER>$telnum</CALL_NUMBER>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Cardpay', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<card_pwd>/message_request/message_body/message_content/card_pwd/text()</card_pwd>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Busi_cardPay_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<CARD_PWD>$card_pwd</CARD_PWD>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_ChangeSubsMonServ', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<oper_type>/message_request/message_body/message_content/oper_type/text()</oper_type>
<cancel_type>/message_request/message_body/message_content/cancel_type/text()</cancel_type>
<deal_type>/message_request/message_body/message_content/deal_type/text()</deal_type>
<domain>/message_request/message_body/message_content/domain/text()</domain>
<spid>/message_request/message_body/message_content/spid/text()</spid>
<bizid>/message_request/message_body/message_content/bizid/text()</bizid>
<biztype>/message_request/message_body/message_content/biztype/text()</biztype>
<effect_type>/message_request/message_body/message_content/effect_type/text()</effect_type>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Busi_ChgSubsMonServ" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<TELNUM>$telnum</TELNUM>
<complex handle="NTOKEN">
<DETAILINFO>
<str1 fmt="%s,">$spid</str1>
<str2 fmt="%s,">$bizid</str2>
<str3 fmt="%s,">$biztype</str3>
<str4 fmt="%s">$domain</str4>
</DETAILINFO>
</complex>
<CANCEL_FLAG>3</CANCEL_FLAG>
<CANCELFLAG>1</CANCELFLAG>
<OPERTYPE>$oper_type</OPERTYPE>
<ACCESSTYPE></ACCESSTYPE>
<SPDOMAIN>$domain</SPDOMAIN>
<ACCESSMODEL></ACCESSMODEL>
<CHKTYPE></CHKTYPE>
<SOURCE></SOURCE>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Chargefee_Invoice_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<formnum>/message_request/message_body/message_content/formnum/text()</formnum>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
<batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<invoice_cnt></invoice_cnt>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<invoice_cnt>./message_content/invoice_cnt/insert($INVOICE_CNT)</invoice_cnt>
</xml>
</single>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<invoicelist>
<chargedate></chargedate>
<invoiceinfo></invoiceinfo>
</invoicelist>
]]></templet>
<infill>
<single>
<xml>
<chargedate>./invoicelist/chargedate/insert(invoke_resp_cr[$,0])</chargedate>
<invoiceinfo>./invoicelist/invoiceinfo/insert(invoke_resp_cr[$,1])</invoiceinfo>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Chargefee_Invoice_Hub', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_InvoiceData_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<FORMNUM>$formnum</FORMNUM>
</tagset>
</pkg>
</request>
<response>
<success>
<pkg>
<tagset>
<INVOICE_CNT>$INVOICE_CNT</INVOICE_CNT>
</tagset>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="2">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
</cols>
</rows>
</crset>
</pkg>
</success>
<fail>
<pkg>
<tagset>
<INVOICE_CNT>$INVOICE_CNT</INVOICE_CNT>
</tagset>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="2">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
</cols>
</rows>
</crset>
</pkg>
</fail>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_CheckQryCdr_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Check_QryCdr_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
</tagset>
</pkg>
</request>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_GetChargeGuide_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<region>/message_request/message_body/message_content/region/text()</region>
<streamno>/message_request/message_body/message_content/streamno/text()</streamno>
<questioncode>/message_request/message_body/message_content/questioncode/text()</questioncode>
<answercode>/message_request/message_body/message_content/answercode/text()</answercode>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
<batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<streamno></streamno>
<questioncode></questioncode>
<question></question>
<recinfo></recinfo>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<streamno>./message_content/streamno/insert($STREAMNO)</streamno>
<questioncode>./message_content/questioncode/insert($QUESTIONCODE)</questioncode>
<question>./message_content/question/insert($QUESTION)</question>
<recinfo>./message_content/recinfo/insert($RECINFO)</recinfo>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_ChargeGuide_Hub" retcode="$RETCODE" retmsg="$RETMSG" region="$region">
<request>
<pkg>
<tagset>
<REGION>$region</REGION>
<STREAMNO>$streamno</STREAMNO>
<QUESTIONCODE>$questioncode</QUESTIONCODE>
<ANSWERCODE>$answercode</ANSWERCODE>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<STREAMNO>$STREAMNO</STREAMNO>
<QUESTIONCODE>$QUESTIONCODE</QUESTIONCODE>
<QUESTION>$QUESTION</QUESTION>
<RECINFO>$RECINFO</RECINFO>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_GetNotesByProdID', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<ncode>/message_request/message_body/message_content/ncode/text()</ncode>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<desc></desc>
</message_content>
]]>
</templet>
<infill>
<single>
<xml>
<desc>./message_content/desc/insert($PRODUCTNOTE)</desc>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_ProductFee" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<NCODE>$ncode</NCODE>
<TELNUM>$telnum</TELNUM>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<PRODUCTNOTE>$DESC</PRODUCTNOTE>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_IncProductSrv2', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<ncode>/message_request/message_body/message_content/ncode/text()</ncode>
<stype>/message_request/message_body/message_content/stype/text()</stype>
<effect_type>/message_request/message_body/message_content/effect_type/text()</effect_type>
<param>/message_request/message_body/message_content/param/text()</param>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
<batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<addncode></addncode>
<delncode></delncode>
<curncode></curncode>
<add_startdate></add_startdate>
<add_enddate></add_enddate>
<del_enddate></del_enddate>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<addncode>./message_content/addncode/insert($ADDNCODE)</addncode>
<delncode>./message_content/delncode/insert($DELNCODE)</delncode>
<curncode>./message_content/curncode/insert($CURNCODE)</curncode>
<add_startdate>./message_content/add_startdate/insert($ADD_STARTDATE)</add_startdate>
<add_enddate>./message_content/add_enddate/insert($ADD_ENDDATE)</add_enddate>
<del_enddate>./message_content/del_enddate/insert($DEL_ENDDATE)</del_enddate>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_IncProductSrv2', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<TELNUM>$telnum</TELNUM>
<NCODE>$ncode</NCODE>
<STYPE>$stype</STYPE>
<PARM>$param</PARM>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<ADDNCODE>$ADDNCODE</ADDNCODE>
<DELNCODE>$DELNCODE</DELNCODE>
<CURNCODE>$CURNCODE</CURNCODE>
<ADD_STARTDATE>$ADD_STARTDATE</ADD_STARTDATE>
<ADD_ENDDATE>$ADD_ENDDATE</ADD_ENDDATE>
<DEL_ENDDATE>$DEL_ENDDATE</DEL_ENDDATE>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_InstallBookCertInfo_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<certid>/message_request/message_body/message_content/certid/text()</certid>
<certname>/message_request/message_body/message_content/certname/text()</certname>
<certaddr>/message_request/message_body/message_content/certaddr/text()</certaddr>
<certgender>/message_request/message_body/message_content/certgender/text()</certgender>
<certstartdate>/message_request/message_body/message_content/certstartdate/text()</certstartdate>
<certenddate>/message_request/message_body/message_content/certenddate/text()</certenddate>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Install_BookCertInfo_Hub" retcode="$RETCODE" retmsg="$RETMSG" region="$region">
<request>
<pkg>
<tagset>
<CERTID>$certid</CERTID>
<CERTNAME>$certname</CERTNAME>
<CERTADDR>$certaddr</CERTADDR>
<CERTGENDER>$certgender</CERTGENDER>
<CERTSTARTDATE>$certstartdate</CERTSTARTDATE>
<CERTENDDATE>$certenddate</CERTENDDATE>
</tagset>
</pkg>
</request>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_NetGetAC', 0, '<portal><request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<msisdn>/message_request/message_body/message_content/telnum/text()</msisdn>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<provname></provname>
<cityname></cityname>
<region></region>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<provname>./message_content/provname/insert($PROVNAME)</provname>
<cityname>./message_content/cityname/insert($CITYNAME)</cityname>
<region>./message_content/region/insert($CITYCODE)</region>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_GetAC" retcode="$RETCODE" retmsg="$RETMSG" region="$region">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<QRYTELNUM>$msisdn</QRYTELNUM>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<PROVNAME>$PROVNAME</PROVNAME>
<CITYNAME>$CITYNAME</CITYNAME>
<CITYCODE>$CITYCODE</CITYCODE>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Package_ChangeList_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
<batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<sprid></sprid>
<sprname></sprname>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<sprid>./message_content/sprid/insert($SPRID)</sprid>
<sprname>./message_content/sprname/insert($SPRNAME)</sprname>
</xml>
</single>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<changelist>
<tprid></tprid>
<tprname></tprname>
</changelist>
]]></templet>
<infill>
<single>
<xml>
<tprid>./changelist/tprid/insert(invoke_resp_cr[$,0])</tprid>
<tprname>./changelist/tprname/insert(invoke_resp_cr[$,1])</tprname>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Package_ChangeList_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
</tagset>
</pkg>
</request>
<response>
<success>
<pkg>
<tagset>
<SPRID>$SPRID</SPRID>
<SPRNAME>$SPRNAME</SPRNAME>
</tagset>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="2">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
</cols>
</rows>
</crset>
</pkg>
</success>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Package_ChgCommit_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<sn>/message_request/message_body/message_content/sn/text()</sn>
<sprid>/message_request/message_body/message_content/sprid/text()</sprid>
<tprid>/message_request/message_body/message_content/tprid/text()</tprid>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Package_ChgCommit_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<SN>$sn</SN>
<SPRID>$sprid</SPRID>
<TPRID>$tprid</TPRID>
</tagset>
</pkg>
</request>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Package_ChgContent_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<sprid>/message_request/message_body/message_content/sprid/text()</sprid>
<tprid>/message_request/message_body/message_content/tprid/text()</tprid>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
<batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<sn></sn>
<sprname></sprname>
<tprname></tprname>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<sn>./message_content/sn/insert($SN)</sn>
<sprname>./message_content/sprname/insert($SPRNAME)</sprname>
<tprname>./message_content/tprname/insert($TPRNAME)</tprname>
</xml>
</single>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<chgcontent>
<optype></optype>
<prtype></prtype>
<prid></prid>
<prname></prname>
</chgcontent>
]]></templet>
<infill>
<single>
<xml>
<optype>./chgcontent/optype/insert(invoke_resp_cr[$,0])</optype>
<prtype>./chgcontent/prtype/insert(invoke_resp_cr[$,1])</prtype>
<prid>./chgcontent/prid/insert(invoke_resp_cr[$,2])</prid>
<prname>./chgcontent/prname/insert(invoke_resp_cr[$,3])</prname>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Package_ChgContent_Hub', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Package_ChgContent_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<SPRID>$sprid</SPRID>
<TPRID>$tprid</TPRID>
</tagset>
</pkg>
</request>
<response>
<success>
<pkg>
<tagset>
<SN>$SN</SN>
<SPRNAME>$SPRNAME</SPRNAME>
<TPRNAME>$TPRNAME</TPRNAME>
</tagset>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="4">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
<COL_2>RESULTCRSET[*,2]</COL_2>
<COL_3>RESULTCRSET[*,3]</COL_3>
</cols>
</rows>
</crset>
</pkg>
</success>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QryAlarmBalance', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<MSISDN>/message_request/message_body/message_content/telnum/text()</MSISDN>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content>
<prepay_type></prepay_type>
<credit></credit>
</message_content>
]]></templet>
<infill>
<single>
<xml>
<prepay_type>./message_content/prepay_type/insert($PREPAYTYPE)</prepay_type>
<credit>./message_content/credit/insert($CREDIT)</credit>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="NetQryAlarmBalance_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<MSISDN>$MSISDN</MSISDN>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<PREPAYTYPE>$PREPAYTYPE</PREPAYTYPE>
<CREDIT>$CREDIT</CREDIT>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QryBillAnalysis_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<month>/message_request/message_body/message_content/month/text()</month>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<restatus></restatus>
<billAnalysisInfo></billAnalysisInfo>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<restatus>./message_content/restatus/insert($RESTATUS)</restatus>
<billAnalysisInfo>./message_content/billAnalysisInfo/insert($BILLANALYSISINFO)</billAnalysisInfo>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_BillAnalysis_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<BILLCYCLE>$month</BILLCYCLE>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<RESTATUS>$RECCODE</RESTATUS>
<BILLANALYSISINFO>$RECSTR</BILLANALYSISINFO>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QryNetnbr_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<nettype>/message_request/message_body/message_content/nettype/text()</nettype>
<pur>/message_request/message_body/message_content/pur/text()</pur>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content></message_content>
]]></templet>
<infill>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<tellist>
<netnum></netnum>
</tellist>
]]></templet>
<infill>
<single>
<xml>
<netnum>./tellist/netnum/insert(invoke_resp_cr[$,0])</netnum>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_getNetnbr_Hub" retcode="$RETCODE" retmsg="$RETMSG" region="$region">
<request>
<pkg>
<tagset>
<NETTYPE>$nettype</NETTYPE>
<PUR>$pur</PUR>
</tagset>
</pkg>
</request>
<response>
<pkg>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="1">
<COL_0>RESULTCRSET[*,0]</COL_0>
</cols>
</rows>
</crset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QryParameterValue_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<paramid>/message_request/message_body/message_content/paramid/text()</paramid>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<paramvalue></paramvalue>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<paramvalue>./message_content/paramvalue/insert($PARAMVALUE)</paramvalue>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Get_ParameterValueByID_Hub" retcode="$RETCODE" retmsg="$RETMSG" region="$region">
<request>
<pkg>
<tagset>
<PARAMID>$paramid</PARAMID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<PARAMVALUE>$PARAMVALUE</PARAMVALUE>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QryPromotions_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body></message_body>
]]>
</templet>
<infill>
<batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content></message_content>
]]></templet>
<infill>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<reclist>
<region></region><orgid></orgid><privid></privid><privname></privname>
<ncode></ncode><prepayfee></prepayfee><groupid></groupid>
<groupname></groupname></reclist>
]]></templet>
<infill>
<single>
<xml>
<region>./reclist/region/insert(invoke_resp_cr[$,0])</region>
<orgid>./reclist/orgid/insert(invoke_resp_cr[$,1])</orgid>
<privid>./reclist/privid/insert(invoke_resp_cr[$,2])</privid>
<privname>./reclist/privname/insert(invoke_resp_cr[$,3])</privname>
<ncode>./reclist/ncode/insert(invoke_resp_cr[$,4])</ncode>
<prepayfee>./reclist/prepayfee/insert(invoke_resp_cr[$,5])</prepayfee>
<groupid>./reclist/groupid/insert(invoke_resp_cr[$,6])</groupid>
<groupname>./reclist/groupname/insert(invoke_resp_cr[$,7])</groupname>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QryPromotions_Hub', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_RecType_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset><TELNUM>$telnum</TELNUM></tagset>
</pkg>
</request>
<response>
<pkg>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="8">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
<COL_2>RESULTCRSET[*,2]</COL_2>
<COL_3>RESULTCRSET[*,3]</COL_3>
<COL_4>RESULTCRSET[*,4]</COL_4>
<COL_5>RESULTCRSET[*,5]</COL_5>
<COL_6>RESULTCRSET[*,6]</COL_6>
<COL_7>RESULTCRSET[*,7]</COL_7>
</cols>
</rows>
</crset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QrySubsDetailAvailScore_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<MSISDN>/message_request/message_body/message_content/telnum/text()</MSISDN>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<scoreinfo></scoreinfo>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<scoreinfo>./message_content/scoreinfo/insert($SCOREINFO)</scoreinfo>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_ScoreValue_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$MSISDN</TELNUM>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<SCOREINFO>$RETURNDATA</SCOREINFO>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QryTelnums_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<model>/message_request/message_body/message_content/model/text()</model>
<pur>/message_request/message_body/message_content/pur/text()</pur>
<pageIndex>/message_request/message_body/message_content/pageIndex/text()</pageIndex>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content></message_content>
]]></templet>
<infill>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<tellist>
<telnum></telnum>
<seltel_prepayfee></seltel_prepayfee>
</tellist>
]]></templet>
<infill>
<single>
<xml>
<telnum>./tellist/telnum/insert(invoke_resp_cr[$,0])</telnum>
<seltel_prepayfee>./tellist/seltel_prepayfee/insert(invoke_resp_cr[$,1])</seltel_prepayfee>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_getTelnums_Hub" retcode="$RETCODE" retmsg="$RETMSG" region="$region">
<request>
<pkg>
<tagset>
<MODEL>$model</MODEL>
<PUR>$pur</PUR>
<PAGEINDEX>$pageIndex</PAGEINDEX>
</tagset>
</pkg>
</request>
<response>
<pkg>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="2">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
</cols>
</rows>
</crset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QryTelsection_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<netnbr>/message_request/message_body/message_content/netnbr/text()</netnbr>
<nettype>/message_request/message_body/message_content/nettype/text()</nettype>
<pur>/message_request/message_body/message_content/pur/text()</pur>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content></message_content>
]]></templet>
<infill>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<tellist>
<telsection></telsection>
</tellist>
]]></templet>
<infill>
<single>
<xml>
<telsection>./tellist/telsection/insert(invoke_resp_cr[$,0])</telsection>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_getTelsection_Hub" retcode="$RETCODE" retmsg="$RETMSG" region="$region">
<request>
<pkg>
<tagset>
<NETNBR>$netnbr</NETNBR>
<NETTYPE>$nettype</NETTYPE>
<PUR>$pur</PUR>
</tagset>
</pkg>
</request>
<response>
<pkg>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="1">
<COL_0>RESULTCRSET[*,0]</COL_0>
</cols>
</rows>
</crset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Qry_Balance_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content>
<balance></balance>
<cashBalance></cashBalance>
<cardBalance></cardBalance>
<monDeduction></monDeduction>
<presentBalance></presentBalance>
<monPresentBalance></monPresentBalance>
<DKBalance></DKBalance>
<preDKBalance></preDKBalance>
<availableBalance></availableBalance>
<credit></credit>
<realTimeFee></realTimeFee>
<hisArrears></hisArrears>
</message_content>
]]></templet>
<infill>
<single>
<xml>
<balance>./message_content/balance/insert($BALANCE)</balance>
<cashBalance>./message_content/cashBalance/insert($CASHBALANCE)</cashBalance>
<cardBalance>./message_content/cardBalance/insert($CARDBALANCE)</cardBalance>
<monDeduction>./message_content/monDeduction/insert($MONDEDUCTION)</monDeduction>
<presentBalance>./message_content/presentBalance/insert($PRESENTBALANCE)</presentBalance>
<monPresentBalance>./message_content/monPresentBalance/insert($MONPRESENTBALANCE)</monPresentBalance>
<DKBalance>./message_content/DKBalance/insert($DKBALANCE)</DKBalance>
<preDKBalance>./message_content/preDKBalance/insert($PREDKBALANCE)</preDKBalance>
<availableBalance>./message_content/availableBalance/insert($AVAILABLEBALANCE)</availableBalance>
<credit>./message_content/credit/insert($CREDIT)</credit>
<realTimeFee>./message_content/realTimeFee/insert($REALTIMEFEE)</realTimeFee>
<hisArrears>./message_content/hisArrears/insert($HISARREARS)</hisArrears>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Qry_Balance_Hub', 1, '</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_Balance_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<BALANCE>$BALANCE</BALANCE>
<CASHBALANCE>$CASHBALANCE</CASHBALANCE>
<CARDBALANCE>$CARDBALANCE</CARDBALANCE>
<MONDEDUCTION>$MONDEDUCTION</MONDEDUCTION>
<PRESENTBALANCE>$PRESENTBALANCE</PRESENTBALANCE>
<MONPRESENTBALANCE>$MONPRESENTBALANCE</MONPRESENTBALANCE>
<DKBALANCE>$DKBALANCE</DKBALANCE>
<PREDKBALANCE>$PREDKBALANCE</PREDKBALANCE>
<AVAILABLEBALANCE>$AVAILABLEBALANCE</AVAILABLEBALANCE>
<CREDIT>$CREDIT</CREDIT>
<REALTIMEFEE>$REALTIMEFEE</REALTIMEFEE>
<HISARREARS>$HISARREARS</HISARREARS>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Qry_Cdr_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<month>/message_request/message_body/message_content/month/text()</month>
<count_flag>/message_request/message_body/message_content/count_flag/text()</count_flag>
<query_flag>/message_request/message_body/message_content/query_flag/text()</query_flag>
<cdrtype>/message_request/message_body/message_content/cdrtype/text()</cdrtype>
<factory>/message_request/message_body/message_content/factory/text()</factory>
<fee_type>/message_request/message_body/message_content/fee_type/text()</fee_type>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
<batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<cust_name></cust_name>
<product_name></product_name>
<product_info></product_info>
<createdate></createdate>
<total_fee></total_fee>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<cust_name>./message_content/cust_name/insert($SUBNAME)</cust_name>
<product_name>./message_content/product_name/insert($PRODUCTNAME)</product_name>
<product_info>./message_content/product_info/insert($PRODUCTINFO)</product_info>
<createdate>./message_content/createdate/insert($CREATEDATE)</createdate>
<total_fee>./message_content/total_fee/insert($TOTALFEE)</total_fee>
</xml>
</single>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<billitem>
<bill></bill>
</billitem>
]]></templet>
<infill>
<single>
<xml>
<bill>./billitem/bill/insert(invoke_resp_cr[$,0])</bill>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Qry_Cdr_Hub', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_CdrList_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<BILLCYCLE>$month</BILLCYCLE>
<COUNTFLAG>$count_flag</COUNTFLAG>
<QUERYFLAG>$query_flag</QUERYFLAG>
<expr name="CDRTYPE" handle="DECODE">
<param1>$cdrtype</param1>
<param2>1</param2>
<param3>GSMCDR</param3>
<param4>2</param4>
<param5>SMSCDR</param5>
<param6>3</param6>
<param7>IMSGCDR</param7>
<param8>4</param8>
<param9>GPRSCDR</param9>
<param10>5</param10>
<param11>WLANCDR</param11>
<param12>6</param12>
<param13>MMSCDR</param13>
<param14>7</param14>
<param15>INFOFEECDR</param15>
<param16>8</param16>
<param17>VPMNCDR</param17>
<param18>9</param18>
<param19>PIMMCDR</param19>
<param20>10</param20>
<param21>FLASHCDRKF1</param21>
<param22>11</param22>
<param23>G3GPRSCDR</param23>
<param24>12</param24>
<param25>GAMECDR</param25>
<param26>$cdrtype</param26>
</expr>
<FEETYPE>$fee_type</FEETYPE>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<PRODUCTNAME>$PRODUCTNAME</PRODUCTNAME>
<SUBNAME>$SUBNAME</SUBNAME>
<PRODUCTINFO>$PRODUCTINFO</PRODUCTINFO>
<CREATEDATE>$CREATEDATE</CREATEDATE>
<TOTALFEE>$TOTALFEE</TOTALFEE>
</tagset>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="1">
<COL_0>RESULTCRSET[*,0]</COL_0>
</cols>
</rows>
</crset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Qry_DetailedBill_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<month>/message_request/message_body/message_content/month/text()</month>
<type>/message_request/message_body/message_content/type/text()</type>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content></message_content>
]]></templet>
<infill>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<billItem>
<type></type>
<recName></recName>
<recFee></recFee>
</billItem>
]]></templet>
<infill>
<single>
<xml>
<type>./billItem/type/insert(invoke_resp_cr[$,0])</type>
<recName>./billItem/recName/insert(invoke_resp_cr[$,1])</recName>
<recFee>./billItem/recFee/insert(invoke_resp_cr[$,2])</recFee>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Qry_DetailedBill_Hub', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_DetailedBill_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<BILLCYCLE>$month</BILLCYCLE>
<QRYTYPE>$type</QRYTYPE>
</tagset>
</pkg>
</request>
<response>
<pkg>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="3">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
<COL_2>RESULTCRSET[*,2]</COL_2>
</cols>
</rows>
</crset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Qry_Fee_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content>
<subname></subname>
<region></region>
<regionname></regionname>
<productid></productid>
<productname></productname>
<productgroup></productgroup>
<viptype></viptype>
<nettype></nettype>
<balance></balance>
<status></status>
</message_content>
]]></templet>
<infill>
<single>
<xml>
<subname>./message_content/subname/insert($SUBNAME)</subname>
<region>./message_content/region/insert($REGION)</region>
<regionname>./message_content/regionname/insert($REGIONNAME)</regionname>
<productid>./message_content/productid/insert($PRODUCTID)</productid>
<productname>./message_content/productname/insert($PRODUCTNAME)</productname>
<productgroup>./message_content/productgroup/insert($PRODUCTGROUP)</productgroup>
<viptype>./message_content/viptype/insert($VIPTYPE)</viptype>
<nettype>./message_content/nettype/insert($NETTYPE)</nettype>
<balance>./message_content/balance/insert($BALANCE)</balance>
<status>./message_content/status/insert($STATUS)</status>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Qry_Fee_Hub', 1, '</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_Fee_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<SUBNAME>$SUBNAME</SUBNAME>
<REGION>$REGION</REGION>
<REGIONNAME>$REGIONNAME</REGIONNAME>
<PRODUCTID>$PRODUCTID</PRODUCTID>
<PRODUCTNAME>$PRODUCTNAME</PRODUCTNAME>
<PRODUCTGROUP>$PRODUCTGROUP</PRODUCTGROUP>
<VIPTYPE>$VIPTYPE</VIPTYPE>
<NETTYPE>$NETTYPE</NETTYPE>
<BALANCE>$BALANCE</BALANCE>
<STATUS>$STATUS</STATUS>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Qry_Taocan_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<billcycle>/message_request/message_body/message_content/billcycle/text()</billcycle>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
<batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<billcycle></billcycle>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<billcycle>./message_content/billcycle/insert($BILLCYCLE)</billcycle>
</xml>
</single>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<privitem>
<type></type>
<privid></privid>
<privname></privname>
<attname></attname>
<attcost></attcost>
<cyclecount></cyclecount>
<unit></unit>
</privitem>
]]></templet>
<infill>
<single>
<xml>
<type>./privitem/type/insert(invoke_resp_cr[$,0])</type>
<privid>./privitem/privid/insert(invoke_resp_cr[$,1])</privid>
<privname>./privitem/privname/insert(invoke_resp_cr[$,2])</privname>
<attname>./privitem/attname/insert(invoke_resp_cr[$,3])</attname>
<attcost>./privitem/attcost/insert(invoke_resp_cr[$,4])</attcost>
<cyclecount>./privitem/cyclecount/insert(invoke_resp_cr[$,5])</cyclecount>
<unit>./privitem/unit/insert(invoke_resp_cr[$,6])</unit>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Qry_Taocan_Hub', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_Priv_Uesd_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<BILLCYCLE>$billcycle</BILLCYCLE>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<BILLCYCLE>$BILLCYCLE</BILLCYCLE>
</tagset>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="7">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
<COL_2>RESULTCRSET[*,2]</COL_2>
<COL_3>RESULTCRSET[*,3]</COL_3>
<COL_4>RESULTCRSET[*,4]</COL_4>
<COL_5>RESULTCRSET[*,5]</COL_5>
<COL_6>RESULTCRSET[*,6]</COL_6>
</cols>
</rows>
</crset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Qry_UserInfo_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<ischeckpass>/message_request/message_body/message_content/ischeckpass/text()</ischeckpass>
<password>/message_request/message_body/message_content/password/text()</password>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content>
<subname></subname>
<region></region>
<regionname></regionname>
<productid></productid>
<productname></productname>
<productgroup></productgroup>
<viptype></viptype>
<logintype></logintype>
<feeflag></feeflag>
<question></question>
<answer></answer>
<needcheckstr></needcheckstr>
<nettype></nettype>
<contactid></contactid>
<status></status>
</message_content>
]]></templet>
<infill>
<single>
<xml>
<subname>./message_content/subname/insert($SUBNAME)</subname>
<region>./message_content/region/insert($REGION)</region>
<regionname>./message_content/regionname/insert($REGIONNAME)</regionname>
<productid>./message_content/productid/insert($PRODUCTID)</productid>
<productname>./message_content/productname/insert($PRODUCTNAME)</productname>
<productgroup>./message_content/productgroup/insert($PRODUCTGROUP)</productgroup>
<viptype>./message_content/viptype/insert($VIPTYPE)</viptype>
<nettype>./message_content/nettype/insert($NETTYPE)</nettype>
<contactid>./message_content/contactid/insert($CONTACTID)</contactid>
<status>./message_content/status/insert($STATUS)</status>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Qry_UserInfo_Hub', 1, '</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_UserInfo_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<ISCHECKPASS>$ischeckpass</ISCHECKPASS>
<PASSWORD>$password</PASSWORD>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<SUBNAME>$SUBNAME</SUBNAME>
<REGION>$REGION</REGION>
<REGIONNAME>$REGIONNAME</REGIONNAME>
<PRODUCTID>$PRODUCTID</PRODUCTID>
<PRODUCTNAME>$PRODUCTNAME</PRODUCTNAME>
<PRODUCTGROUP>$PRODUCTGROUP</PRODUCTGROUP>
<VIPTYPE>$VIPTYPE</VIPTYPE>
<NETTYPE>$NETTYPE</NETTYPE>
<CONTACTID>$CONTACTID</CONTACTID>
<STATUS>$STATUS</STATUS>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_SendBill', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>
/message_request/message_body/message_content/telnum/text()
</telnum>
<mailtype>
/message_request/message_body/message_content/mailtype/text()
</mailtype>
<mailaddr>
/message_request/message_body/message_content/mailaddr/text()
</mailaddr>
<oprtype>1
</oprtype>
<orgid>
</orgid>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Busi_modacct_Hub"
retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<MAILTYPE>$mailtype</MAILTYPE>
<MAILADDR>$mailaddr</MAILADDR>
<OPRTYPE>$oprtype</OPRTYPE>
<ORGID>$orgid</ORGID>
</tagset>
</pkg>
</request>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_SendSMSInfo', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<servnum>/message_request/message_body/message_content/telnum/text()</servnum>
<templateno>/message_request/message_body/message_content/templateno/text()</templateno>
<smsparam>/message_request/message_body/message_content/smsparam/text()</smsparam>
<isrvcall>/message_request/message_body/message_content/isrvcall/text()</isrvcall>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Busi_SendSMSInfo" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<SERVNUM>$servnum</SERVNUM>
<TEMPLATENO>$templateno</TEMPLATENO>
<SMPARAM>$smsparam</SMPARAM>
<ISRVCALL>$isrvcall</ISRVCALL>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_SendSms', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<smscont>/message_request/message_body/message_content/smscont/text()</smscont>
<priority>/message_request/message_body/message_content/priority/text()</priority>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="SendSms" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<TELNUM>$telnum</TELNUM>
<SMSCONT>$smscont</SMSCONT>
<PRIORITY>$priority</PRIORITY>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_busi_alarmbalance', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<credit>/message_request/message_body/message_content/credit/text()</credit>
<systemid>/message_request/message_body/message_content/systemid/text()</systemid>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="PreBlr_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$telnum</MSISDN>
<CREDIT>$credit</CREDIT>
<CALL_NUMBER>$telnum</CALL_NUMBER>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_busi_chgpwd', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<old_password>/message_request/message_body/message_content/old_password/text()</old_password>
<new_password>/message_request/message_body/message_content/new_password/text()</new_password>
<subcmdid>/message_request/message_body/message_content/subcmdid/text()</subcmdid>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="IntNet" commandid="Atsv_Busi_ChkChgPwd" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$telnum</MSISDN>
<OLD_PASSWD>$old_password</OLD_PASSWD>
<NEW_PASSWD>$new_password</NEW_PASSWD>
<SUBCMDID>$subcmdid</SUBCMDID>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_busi_cidcheck', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<certid>/message_request/message_body/message_content/certid/text()</certid>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content>
<maxfailnum></maxfailnum>
<curfailnum></curfailnum>
</message_content>
]]></templet>
<infill>
<single>
<xml>
<maxfailnum>./message_content/maxfailnum/insert($MAXFAILNUM)</maxfailnum>
<curfailnum>./message_content/curfailnum/insert($CURFAILNUM)</curfailnum>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_busi_cidcheck', 1, '</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Busi_ChkIDCard" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<CERTID>$certid</CERTID>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<MAXFAILNUM>$MAXFAILNUM</MAXFAILNUM>
<CURFAILNUM>$CURFAILNUM</CURFAILNUM>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_busi_pwdreset', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<oldpwd>/message_request/message_body/message_content/oldpwd/text()</oldpwd>
<newpwd>/message_request/message_body/message_content/newpwd/text()</newpwd>
<subcmdid>/message_request/message_body/message_content/subcmdid/text()</subcmdid>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
</xml>
</success>
<fail>
<xml>
</xml>
<head default="1"/>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Busi_ChkSetPwd_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<OLDPWD>$oldpwd</OLDPWD>
<NEWPWD>$newpwd</NEWPWD>
<SUBCMDID>$subcmdid</SUBCMDID>
</tagset>
</pkg>
</request>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_busi_stopopensubs', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<stoptype>/message_request/message_body/message_content/stoptype/text()</stoptype>
<reason>/message_request/message_body/message_content/reason/text()</reason>
<expr name="commandid" handle="DECODE">
<param1>$stoptype</param1>
<param2>OpenSubs</param2>
<param3>Atsv_Busi_OpenSubs</param3>
<param4>StopSubs</param4>
<param5>Atsv_Busi_StopSubs</param5>
<param6>$stoptype</param6>
</expr>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="$commandid" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$telnum</MSISDN>
<CALL_NUMBER>$telnum</CALL_NUMBER>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_modifyprivilege_Hub', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<ncode>/message_request/message_body/message_content/ncode/text()</ncode>
<stype>/message_request/message_body/message_content/stype/text()</stype>
<issubmit>/message_request/message_body/message_content/issubmit/text()</issubmit>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Modify_Privilege_Hub" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<NCODE>$ncode</NCODE>
<STYPE>$stype</STYPE>
<ISSUBMIT>$issubmit</ISSUBMIT>
</tagset>
</pkg>
</request>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qryDictItem', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<MSISDN>/message_request/message_body/message_content/telnum/text()</MSISDN>
<GROUPID>/message_request/message_body/message_content/groupid/text()</GROUPID>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content></message_content>
]]></templet>
<infill>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<dictitem>
<dict_id></dict_id>
<dict_name></dict_name>
</dictitem>
]]></templet>
<infill>
<single>
<xml>
<dict_id>./dictitem/dict_id/insert(invoke_resp_cr[$,0])</dict_id>
<dict_name>./dictitem/dict_name/insert(invoke_resp_cr[$,1])</dict_name>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_GetDictByGroup" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<MSISDN>$MSISDN</MSISDN>
<GROUPID>$GROUPID</GROUPID>
<OPERID>$operator</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="2">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
</cols>
</rows>
</crset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_chargehistory', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<startdate>/message_request/message_body/message_content/startdate/text()</startdate>
<enddate>/message_request/message_body/message_content/enddate/text()</enddate>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content></message_content>
]]></templet>
<infill>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<payhistoryitem>
<pay_serial_nbr></pay_serial_nbr>
<operation_type></operation_type>
<pay_date></pay_date>
<billing_cycle_id></billing_cycle_id>
<amount></amount>
<statusname></statusname>
<payment_method></payment_method>
</payhistoryitem>
]]></templet>
<infill>
<single>
<xml>
<pay_serial_nbr>./payhistoryitem/pay_serial_nbr/insert(invoke_resp_cr[$,0])</pay_serial_nbr>
<operation_type>./payhistoryitem/operation_type/insert(invoke_resp_cr[$,1])</operation_type>
<pay_date>./payhistoryitem/pay_date/insert(invoke_resp_cr[$,2])</pay_date>
<billing_cycle_id>./payhistoryitem/billing_cycle_id/insert(invoke_resp_cr[$,3])</billing_cycle_id>
<amount>./payhistoryitem/amount/insert(invoke_resp_cr[$,4])</amount>
<statusname>./payhistoryitem/statusname/insert(invoke_resp_cr[$,5])</statusname>
<payment_method>./payhistoryitem/payment_method/insert(invoke_resp_cr[$,6])</payment_method>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_chargehistory', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_PayHis" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$telnum</MSISDN>
<STARTDATE>$startdate</STARTDATE>
<ENDDATE>$enddate</ENDDATE>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="7">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
<expr name="COL_2" handle="DATEFMT" from="RESULTCRSET">
<datetime>RESULTCRSET[$,2]</datetime>
<fromfmt>yyyy-mm-dd hh:mi:ss</fromfmt>
<tofmt>YYYYMMDDHH24MISS</tofmt>
</expr>
<COL_3>RESULTCRSET[*,3]</COL_3>
<COL_4>RESULTCRSET[*,4]</COL_4>
<COL_5>RESULTCRSET[*,5]</COL_5>
<COL_6>RESULTCRSET[*,6]</COL_6>
</cols>
</rows>
</crset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_pukcode', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<pin1></pin1>
<pin2></pin2>
<puk1></puk1>
<puk2></puk2>
</message_content>
]]>
</templet>
<infill>
<single>
<xml>
<pin1>./message_content/pin1/insert($PIN1)</pin1>
<pin2>./message_content/pin2/insert($PIN2)</pin2>
<puk1>./message_content/puk1/insert($PUK1)</puk1>
<puk2>./message_content/puk2/insert($PUK2)</puk2>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="IntNet" commandid="Atsv_Qry_Puk" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$telnum</MSISDN>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<PIN1>$PIN1</PIN1>
<PIN2>$PIN2</PIN2>
<PUK1>$PUK1</PUK1>
<PUK2>$PUK2</PUK2>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_rechistory', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<startdate>/message_request/message_body/message_content/startdate/text()</startdate>
<enddate>/message_request/message_body/message_content/enddate/text()</enddate>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content></message_content>
]]></templet>
<infill>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<acptitem>
<accept_time></accept_time>
<accept_site></accept_site>
<accept_staff></accept_staff>
<accept_type></accept_type>
<contents></contents>
</acptitem>
]]></templet>
<infill>
<single>
<xml>
<accept_time>./acptitem/accept_time/insert(invoke_resp_cr[$,0])</accept_time>
<accept_site>./acptitem/accept_site/insert(invoke_resp_cr[$,1])</accept_site>
<accept_type>./acptitem/accept_type/insert(invoke_resp_cr[$,3])</accept_type>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_rechistory', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_GetAcpt" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$telnum</MSISDN>
<STARTDATE>$startdate</STARTDATE>
<ENDDATE>$enddate</ENDDATE>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="4">
<expr name="COL_0" handle="DATEFMT" from="RESULTCRSET">
<datetime>RESULTCRSET[$,0]</datetime>
<fromfmt>yyyy-mm-dd hh:mi:ss</fromfmt>
<tofmt>YYYYMMDDHH24MISS</tofmt>
</expr>
<COL_1>RESULTCRSET[*,1]</COL_1>
<COL_2>RESULTCRSET[*,2]</COL_2>
<COL_3>RESULTCRSET[*,3]</COL_3>
</cols>
</rows>
</crset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_spinfo', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<sn>/message_request/message_body/message_content/sn/text()</sn>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content></message_content>
]]></templet>
<infill>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<cusermoninfodt>
<usermoninfo_deal_type></usermoninfo_deal_type>
<usermoninfo_spid></usermoninfo_spid>
<usermoninfo_spname></usermoninfo_spname>
<usermoninfo_spbizid></usermoninfo_spbizid>
<usermoninfo_spbizname></usermoninfo_spbizname>
<usermoninfo_spbiztype></usermoninfo_spbiztype>
<usermoninfo_pricetype></usermoninfo_pricetype>
<usermoninfo_price></usermoninfo_price>
<usermoninfo_domain></usermoninfo_domain>
<usermoninfo_start_time></usermoninfo_start_time>
<usermoninfo_end_time></usermoninfo_end_time>
<usermoninfo_seq></usermoninfo_seq>
<usermoninfo_replace_charge></usermoninfo_replace_charge>
<usermoninfo_pricedesc></usermoninfo_pricedesc>
</cusermoninfodt>
]]></templet>
<infill>
<single>
<xml>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_spinfo', 1, '<usermoninfo_deal_type>./cusermoninfodt/usermoninfo_deal_type/insert(invoke_resp_cr[$,0])</usermoninfo_deal_type>
<usermoninfo_spid>./cusermoninfodt/usermoninfo_spid/insert(invoke_resp_cr[$,1])</usermoninfo_spid>
<usermoninfo_spname>./cusermoninfodt/usermoninfo_spname/insert(invoke_resp_cr[$,2])</usermoninfo_spname>
<usermoninfo_spbizid>./cusermoninfodt/usermoninfo_spbizid/insert(invoke_resp_cr[$,3])</usermoninfo_spbizid>
<usermoninfo_spbizname>./cusermoninfodt/usermoninfo_spbizname/insert(invoke_resp_cr[$,4])</usermoninfo_spbizname>
<usermoninfo_spbiztype>./cusermoninfodt/usermoninfo_spbiztype/insert(invoke_resp_cr[$,5])</usermoninfo_spbiztype>
<usermoninfo_pricetype>./cusermoninfodt/usermoninfo_pricetype/insert(invoke_resp_cr[$,6])</usermoninfo_pricetype>
<usermoninfo_price>./cusermoninfodt/usermoninfo_price/insert(invoke_resp_cr[$,7])</usermoninfo_price>
<usermoninfo_domain>./cusermoninfodt/usermoninfo_domain/insert(invoke_resp_cr[$,8])</usermoninfo_domain>
<usermoninfo_start_time>./cusermoninfodt/usermoninfo_start_time/insert(invoke_resp_cr[$,9])</usermoninfo_start_time>
<usermoninfo_end_time></usermoninfo_end_time>
<usermoninfo_seq></usermoninfo_seq>
<usermoninfo_replace_charge>./cusermoninfodt/usermoninfo_replace_charge/insert(invoke_resp_cr[$,12])</usermoninfo_replace_charge>
<usermoninfo_pricedesc>./cusermoninfodt/usermoninfo_pricedesc/insert(invoke_resp_cr[$,13])</usermoninfo_pricedesc>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_spinfo', 2, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_SubsAllIntServ" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$telnum</MSISDN>
<SN>$sn</SN>
<OUTOPERID>$operatorid</OUTOPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="28">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
<COL_2>RESULTCRSET[*,2]</COL_2>
<COL_3>RESULTCRSET[*,3]</COL_3>
<COL_4>RESULTCRSET[*,4]</COL_4>
<COL_5>RESULTCRSET[*,5]</COL_5>
<COL_6>RESULTCRSET[*,6]</COL_6>
<COL_7>RESULTCRSET[*,7]</COL_7>
<COL_8>RESULTCRSET[*,8]</COL_8>
<COL_9>RESULTCRSET[*,9]</COL_9>
<COL_10>RESULTCRSET[*,10]</COL_10>
<COL_11>RESULTCRSET[*,11]</COL_11>
<COL_12>RESULTCRSET[*,12]</COL_12>
<COL_13>RESULTCRSET[*,13]</COL_13>
</cols>
</rows>
</crset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_userstate', 0, '<portal><request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<state></state>
</message_content>
]]>
</templet>
<infill>
<single>
<xml>
<state>./message_content/state/insert($STATUS)</state>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_UserStatus" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$telnum</MSISDN>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<STATUS>$STATUS</STATUS>
<STATUSID>$STATUSID</STATUSID>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_zfinfo', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<qrytype>/message_request/message_body/message_content/qrytype/text()</qrytype>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
<batch>
<cdata path="./message_body">
<templet><![CDATA[
<message_content></message_content>
]]></templet>
<infill>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<info>
<type></type>
<name></name>
<desp></desp>
<start_time></start_time>
<end_time></end_time>
</info>
]]></templet>
<infill>
<single>
<xml>
<type>./info/type/insert(invoke_resp_cr[$,0])</type>
<name>./info/name/insert(invoke_resp_cr[$,1])</name>
<desp>./info/desp/insert(invoke_resp_cr[$,2])</desp>
<start_time>./info/start_time/insert(invoke_resp_cr[$,3])</start_time>
<end_time>./info/end_time/insert(invoke_resp_cr[$,4])</end_time>
</xml>
</single>
</infill>
</crset>
</batch>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_zfinfo', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="Atsv_Qry_SubsFeeInfo" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<TELNUM>$telnum</TELNUM>
<QRYTYPE>$qrytype</QRYTYPE>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="5">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
<COL_2>RESULTCRSET[*,2]</COL_2>
<expr name="COL_3" handle="DATEFMT" from="RESULTCRSET">
<datetime>RESULTCRSET[$,3]</datetime>
<fromfmt>yyyy-mm-dd hh:mi:ss</fromfmt>
<tofmt>YYYYMMDDHH24MISS</tofmt>
</expr>
<expr name="COL_4" handle="DATEFMT" from="RESULTCRSET">
<datetime>RESULTCRSET[$,4]</datetime>
<fromfmt>yyyy-mm-dd hh:mi:ss</fromfmt>
<tofmt>YYYYMMDDHH24MISS</tofmt>
</expr>
</cols>
</rows>
</crset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

--返回码转换
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_alarmbalance' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_calltransfer' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_cancelsp' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_cardpay' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_chargefee_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_chgpwd' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_cidcheck' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_occupytel_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_productrec' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_promotions_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_pwdreset' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_sendbill' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_sendsms' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_sendsmsinfo' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_busi_stopopensubs' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_check_cdrauth_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_install_bookcertinfo_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_package_changelist_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_package_chgcommit_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_package_chgcontent_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_alarmbalance' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_balance_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_billanalysis_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_cdr' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_chargeguide_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_chargehistory' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_detailedbill_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_dictitem' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_fee_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_invoice_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_netnbr_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_numregion' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_parametervaluebyid_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_productfee' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_promotions_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_pukcode' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_rechistory' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_scorevalue' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_spinfo' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_taocan_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_telnum_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_telsection_hub' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_userinfo' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_userstate' and from_retcode='0';
delete int_retcode_convert where channelid='bsacAtsv' and businessid='cli_qry_zfinfo' and from_retcode='0';


insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_alarmbalance', '0', '100', '', '0', 1, sysdate, sysdate, '余额提醒设置接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_calltransfer', '0', '100', '', '0', 1, sysdate, sysdate, '呼叫转移设置');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_cancelsp', '0', '100', '', '0', 1, sysdate, sysdate, '增值业务统一退订');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_cardpay', '0', '100', '', '0', 1, sysdate, sysdate, '充值卡缴费');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_chargefee_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端缴费接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_chgpwd', '0', '100', '', '0', 1, sysdate, sysdate, '密码修改');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_cidcheck', '0', '100', '', '0', 1, sysdate, sysdate, '身份证号认证');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_occupytel_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端自助选号预订接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_productrec', '0', '100', '', '0', 1, to_date('22-08-2010', 'dd-mm-yyyy'), to_date('22-08-2010', 'dd-mm-yyyy'), '产品受理通用接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_promotions_hub', '0', '100', '', '0', 1, sysdate, sysdate, '查询用户积分');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_pwdreset', '0', '100', '', '0', 1, sysdate, sysdate, '密码重置');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_sendbill', '0', '100', '', '0', 1, sysdate, sysdate, '账单寄送');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_sendsms', '0', '100', '', '0', 1, sysdate, sysdate, '短信下发接口（指定短信内容）');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_sendsmsinfo', '0', '100', '', '0', 1, sysdate, sysdate, '下发短信接口（参数）');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_stopopensubs', '0', '100', '', '0', 1, sysdate, sysdate, '停开机');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_check_cdrauth_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端清单查询权限验证');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_install_bookcertinfo_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端身份证入网预约');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_package_changelist_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端获取用户可转换套餐清单接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_package_chgcommit_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端套餐转换接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_package_chgcontent_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端套餐转换优惠/服务/产品变更清单接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_alarmbalance', '0', '100', '', '0', 1, to_date('17-08-2010', 'dd-mm-yyyy'), to_date('17-08-2010', 'dd-mm-yyyy'), '查询余额提醒阀值');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_balance_hub', '0', '100', '', '0', 1, sysdate, sysdate, '帐户余额信息查询接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_billanalysis_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端话费账单分析接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_cdr', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端详单查询接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_chargeguide_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端资费推荐接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_chargehistory', '0', '100', '', '0', 1, sysdate, sysdate, '缴费历史查询接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_detailedbill_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端月账单查询查询接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_dictitem', '0', '100', '', '0', 1, to_date('17-08-2010', 'dd-mm-yyyy'), to_date('17-08-2010', 'dd-mm-yyyy'), '查询字典数据');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_fee_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端缴费查询接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_invoice_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端取发票接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_netnbr_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助选号号码提取接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_numregion', '0', '100', '', '0', 1, sysdate, sysdate, '  号码归属地查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_parametervaluebyid_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端获取系统参数');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_productfee', '0', '100', '', '0', 1, sysdate, sysdate, '产品资费信息查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_promotions_hub', '0', '100', '', '0', 1, sysdate, sysdate, '查询用户积分');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_pukcode', '0', '100', '', '0', 1, sysdate, sysdate, 'puk码查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_rechistory', '0', '100', '', '0', 1, sysdate, sysdate, '受理历史查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_scorevalue', '0', '100', '', '0', 1, sysdate, sysdate, '查询用户积分');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_spinfo', '0', '100', '', '0', 1, sysdate, sysdate, '增值业务统一查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_taocan_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端套餐信息查询接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_telnum_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助选号号码提取接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_telsection_hub', '0', '100', '', '0', 1, sysdate, sysdate, '自助选号号码提取接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_userinfo', '0', '100', '', '0', 1, sysdate, sysdate, '自助终端清单查询权限验证');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_userstate', '0', '100', '', '0', 1, sysdate, sysdate, '用户状态查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_zfinfo', '0', '100', '', '0', 1, sysdate, sysdate, '  本机品牌资费及已开通服务、优惠查询');

