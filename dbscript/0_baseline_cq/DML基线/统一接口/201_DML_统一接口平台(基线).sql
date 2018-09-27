set feedback off
set define off
-------------------------------------------------------
prompt Disabling triggers for int_protocol_define...
alter table int_protocol_define disable all triggers;
prompt Disabling triggers for int_access_point...
alter table int_access_point disable all triggers;
prompt Disabling triggers for int_business_define...
alter table int_business_define disable all triggers;
prompt Disabling triggers for int_flow_define...
alter table int_flow_define disable all triggers;
prompt Disabling triggers for int_retcode_convert...
alter table int_retcode_convert disable all triggers;

prompt Deleting int_protocol_define...
delete from int_protocol_define where PROTOCOLID = 'SelfTerminalProtocolID';
commit;

prompt Deleting int_access_point...
delete from int_access_point where ACCESSID = 'NG23SelfTerminalAccessID';
commit;

prompt Deleting int_business_define...
delete from int_business_define where CHANNELID = 'bsacAtsv' and PROTOCOLID = 'SelfTerminalProtocolID';
commit;

prompt Deleting int_flow_define...
delete from int_flow_define where flowid in ('CLT_bsacAtsv_qry_chargehistory', 'CLT_bsacAtsv_qry_balance', 'CLT_bsacAtsv_qry_bill', 'CLT_bsacAtsv_ChargeFee', 'CLT_bsacAtsv_SendSMSInfo', 
'CLT_bsacAtsv_NG23_QrySubsDetailAvailScore', 'CLT_bsacAtsv_Proxy_MPAY_ACC_QRY', 'CLT_bsacAtsv_qry_userinfo', 'CLT_bsacAtsv_qry_pukcode', 
'CLT_bsacAtsv_qry_userstate', 'CLT_bsacAtsv_qry_rechistory', 'CLT_bsacAtsv_qry_numbynet', 'CLT_bsacAtsv_qry_zfinfo', 'CLT_bsacAtsv_CallTransfer', 
'CLT_bsacAtsv_SendSms', 'CLT_bsacAtsv_QuePriUsed', 'CLT_bsacAtsv_NetGetAC', 'CLT_bsacAtsv_QryCDR', 'CLT_bsacAtsv_CommScoreChange', 
'CLT_bsacAtsv_qryDictItem', 'CLT_bsacAtsv_Busi_Mpaycharge', 'CLT_bsacAtsv_busi_checkid', 'CLT_bsacAtsv_busi_resetpwd', 'CLT_bsacAtsv_qry_detailbill', 
'CLT_bsacAtsv_occupytel', 'CLT_bsacAtsv_qry_currentbill', 'CLT_bsacAtsv_busi_stopopensubs', 'CLT_bsacAtsv_NG23_ChangeSubsMonServ', 
'CLT_bsacAtsv_GetNotesByProdID', 'CLT_bsacAtsv_IncProductSrv2', 'CLT_bsacAtsv_QueryFee', 'CLT_bsacAtsv_qry_spinfo', 'CLT_bsacAtsv_busi_alarmbalance', 
'CLT_bsacAtsv_qry_yckf', 'CLT_bsacAtsv_busi_chgpwd', 'CLT_bsacAtsv_QryAlarmBalance', 'SelfTerminalProtocolResp', 'SelfTerminalProtocolReq');
commit;

prompt Deleting int_retcode_convert...
delete from int_retcode_convert where CHANNELID = 'bsacAtsv';
commit;

prompt Loading int_protocol_define...
insert into int_protocol_define (PROTOCOLID, PROTOCOLNAME, PROTOCOLTYPE, ENCRYPT, ENCRYPTKEY, REQHEAD, RESPHEAD, EFFTIME, EXPTIME, REMARK)
values ('SelfTerminalProtocolID', '自助终端包头协议解析', 1, 0, '', 'SelfTerminalProtocolReq', 'SelfTerminalProtocolResp', to_date('09-08-2010', 'dd-mm-yyyy'), to_date('01-08-2051', 'dd-mm-yyyy'), '');
commit;
prompt 1 records loaded

prompt Loading int_access_point...
insert into int_access_point (ACCESSID, PROTOCOLID, ACCESSNAME, DEFCHANID, AUTHMODE, ROUTEFLAG, ROUTEFIELD, ROUTESCRIPT, EFFTIME, EXPTIME, REMARK, LOGLEVEL)
values ('NG23SelfTerminalAccessID', 'SelfTerminalProtocolID', 'NG2.3自助终端专题接入点配置', 'bsacAtsv', 0, 0, '', '', to_date('09-08-2010', 'dd-mm-yyyy'), to_date('01-08-2051', 'dd-mm-yyyy'), '', 3);
commit;
prompt 1 records loaded

prompt Loading int_business_define...
insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_currentbill', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qry_currentbill', '  当前话费查询', '采用手机号路由', to_date('09-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_stopopensubs', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_busi_stopopensubs', '停开机', '采用手机号路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_cancelsp', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_NG23_ChangeSubsMonServ', '增值业务统一退订', '采用手机号路由', to_date('09-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_productfee', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_GetNotesByProdID', '产品资费信息查询', '采用手机号路由', to_date('09-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_productrec', 'bsacAtsv', 'SelfTerminalProtocolID', 'IncProductSrv2', 0, 60, 'CLT_bsacAtsv_IncProductSrv2', '产品受理通用接口', '采用手机号路由', to_date('17-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_fee', 'bsacAtsv', 'SelfTerminalProtocolID', 'QueryFee', 0, 60, 'CLT_bsacAtsv_QueryFee', '查询话费接口，用于现金缴费和银联卡缴费', '采用手机号路由', to_date('18-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_spinfo', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qry_spinfo', '  增值业务统一查询', '采用手机号码路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_alarmbalance', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_busi_alarmbalance', '余额提醒设置接口', '采用手机号路由', to_date('09-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_yckf', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qry_yckf', '月初扣费查询', '采用手机号路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_chgpwd', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_busi_chgpwd', '  密码修改', '采用手机号路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_alarmbalance', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_QryAlarmBalance', '余额提醒查询接口', '采用手机号路由', to_date('17-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_chargehistory', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qry_chargehistory', '缴费历史查询', '采用手机号路由', to_date('09-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_balance', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscBatchGetInfo', 0, 60, 'CLT_bsacAtsv_qry_balance', '账户余额查询', '采用手机号路由', to_date('09-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_bill', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qry_bill', '话费查询', '采用手机号路由', to_date('09-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_chargefee', 'bsacAtsv', 'SelfTerminalProtocolID', 'ChargeFee', 0, 60, 'CLT_bsacAtsv_ChargeFee', '缴纳话费接口，用于现金缴费和银联卡缴费', '采用手机号路由', to_date('22-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_sendsmsinfo', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_SendSMSInfo', '短信下发接口', '根据参数下发短信，采用手机号路由，', to_date('09-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_scorevalue', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_NG23_QrySubsDetailAvailScore', '用户积分查询', '采用手机号路由', to_date('09-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_mpayacc', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_Proxy_MPAY_ACC_QRY', '手机支付账户信息查询', '采用手机号路由', to_date('12-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_userinfo', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_qry_userinfo', '校验用户密码并提取用户信息', '采用手机号路由', to_date('09-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_pukcode', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_qry_pukcode', 'PUK码查询', '采用手机号码路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_userstate', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_qry_userstate', '  当前用户状态查询', '采用手机号路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_rechistory', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qry_rechistory', '受理历史查询', '采用手机号路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_numbynet', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qry_numbynet', '自助选号查询', '采用地区路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_zfinfo', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qry_zfinfo', '本机品牌资费及已开通服务、优惠查询', '采用手机号路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_calltransfer', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_CallTransfer', '呼叫转移', '采用手机号路由', to_date('14-08-2010 11:58:18', 'dd-mm-yyyy hh24:mi:ss'), null, 0, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_sendsms', 'bsacAtsv', 'SelfTerminalProtocolID', 'SendSms', 0, 60, 'CLT_bsacAtsv_SendSms', '短信下发接口', '根据指定短信内容下发短信，采用手机号路由，', to_date('09-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_taocan', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_QuePriUsed', '套餐使用情况查询', '采用手机号路由', to_date('09-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_numregion', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_NetGetAC', '手机归属地查询', '采用地区路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_cdr', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_QryCDR', '查询详单，包括：通话清单、短信清单、梦网清单、GPRS清单。', '采用手机号路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_chgscore', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_CommScoreChange', '积分扣减', '采用手机号路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_dictitem', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qryDictItem', '查询字典数据接口', '采用手机号路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_mpaycharge', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_Busi_Mpaycharge', '  手机支付主账户现金充值', '采用手机号路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_cidcheck', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_busi_checkid', '身份证认证', '采用手机号路由', to_date('16-08-2011', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_pwdreset', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetInfo', 0, 60, 'CLT_bsacAtsv_busi_resetpwd', '密码重置', '采用手机号路由', to_date('16-08-2011', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 1);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_detailedbill', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_qry_detailbill', '山东月账单查询', '采用地区路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 5, 2);

insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_busi_occupytel', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscGetSetInfo', 0, 60, 'CLT_bsacAtsv_occupytel', '自助选号预订', '采用地区路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 3, 2);

commit;
prompt 36 records loaded

prompt Loading int_flow_define...
insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_busi_checkid', 0, '<portal>
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
<templet>
<![CDATA[
<message_content>
<maxfailnum></maxfailnum>
<curfailnum></curfailnum>
</message_content>
]]>
</templet>
<infill>
<single>
<xml>
<maxfailnum>./message_content/maxfailnum/insert($MAXFAILNUM)</maxfailnum>
<curfailnum>./message_content/curfailnum/insert($CURFAILNUM)</curfailnum>
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
<invoke intid="$channelid" commandid="CidCheck_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<CERTID>$certid</CERTID>
<MSISDN>$telnum</MSISDN>
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
values ('CLT_bsacAtsv_busi_resetpwd', 0, '<portal>
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
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" commandid="CheckResetPassword_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<TELNUM>$telnum</TELNUM>
<OLD_PASSWD>$oldpwd</OLD_PASSWD>
<NEW_PASSWD>$newpwd</NEW_PASSWD>
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
values ('CLT_bsacAtsv_occupytel', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<region>/message_request/message_body/message_content/region/text()</region>
<org_id>/message_request/message_body/message_content/org_id/text()</org_id>
<certtype>/message_request/message_body/message_content/certtype/text()</certtype>
<certid>/message_request/message_body/message_content/certid/text()</certid>
<name>/message_request/message_body/message_content/name/text()</name>
<contacttel>/message_request/message_body/message_content/contacttel/text()</contacttel>
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
<orderid></orderid>
</message_content>
]]>
</templet>
<infill>
<single>
<xml>
<orderid>./message_content/orderid/insert($ORDERID)</orderid>
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
<invoke intid="$channelid" commandid="NetOccupytRes_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<MSISDN>$telnum</MSISDN>
<REGION>$region</REGION>
<ORG_ID>$org_id</ORG_ID>
<CERTTYPE>$certtype</CERTTYPE>
<CERTI_NO>$certid</CERTI_NO>
<OPERID>$operatorid</OPERID>
<NAME>$name</NAME>
<CONTACTTEL>$contacttel</CONTACTTEL>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<ORDERID>$ORDERID</ORDERID>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_chargehistory', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="NetBSFMX_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
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
values ('CLT_bsacAtsv_qry_userinfo', 0, '<portal>
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
<region> </region>
<regionname></regionname>
<productid> </productid>
<productname></productname>
<productgroup></productgroup>
<viptype></viptype>
<logintype></logintype>
<feeflag></feeflag>
<question></question>
<answer></answer>
<needcheckstr></needcheckstr>
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
<logintype>./message_content/logintype/insert($LOGINTYPE)</logintype>
<feeflag>./message_content/feeflag/insert($FEEFLAG)</feeflag>
<question>./message_content/question/insert($QUESTION)</question>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_userinfo', 1, '<answer>./message_content/answer/insert($ANSWER)</answer>
<needcheckstr>./message_content/needcheckstr/insert($NEEDCHECKSTR)</needcheckstr>
<contactid>./message_content/contactid/insert($CONTACTID)</contactid>
<status>./message_content/status/insert($STATUS)</status>
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
<invoke intid="$channelid" commandid="LogIn_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<OPERID>$operatorid</OPERID>
<UniContactFlag>1</UniContactFlag><UNITID>$unitid</UNITID>
<MSISDN>$telnum</MSISDN>
<PASSWORD>$password</PASSWORD>
<expr name="ISCHECKPASS" handle="DECODE">
<param1>$ischeckpass</param1>
<param2>0</param2>
<param3>false</param3>
<param4>1</param4>
<param5>true</param5>
<param6>$ischeckpass</param6>
</expr>
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
<LOGINTYPE>$LOGINTYPE</LOGINTYPE>
<FEEFLAG>$FEEFLAG</FEEFLAG>
<QUESTION>$QUESTION</QUESTION>
<ANSWER>$ANSWER</ANSWER>
<NEEDCHECKSTR>$NEEDCHECKSTR</NEEDCHECKSTR>
<CONTACTID>$CONTACTID</CONTACTID>
<STATUS>$STATUS</STATUS>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_currentbill', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<flag>/message_request/message_body/message_content/flag/text()</flag>
<billtemplno>/message_request/message_body/message_content/billtemplno/text()</billtemplno>
<cycleoffset>/message_request/message_body/message_content/cycleoffset/text()</cycleoffset>
<qrytype>/message_request/message_body/message_content/qrytype/text()</qrytype>
<expr name="channelid" handle="DECODE">
<param1>$channelid</param1>
<param2>bsacAtsv</param2>
<param3>IntAuto</param3>
<param4>$channelid</param4>
</expr>
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
<billitem>
<sequenec></sequenec>
<itemcode></itemcode>
<itemname></itemname>
<fee></fee>
<startdate></startdate>
<enddate></enddate>
<billcycle></billcycle>
</billitem>
]]></templet>
<infill>
<single>
<xml>
<sequenec>./billitem/sequenec/insert(invoke_resp_cr[$,0])</sequenec>
<itemcode>./billitem/itemcode/insert(invoke_resp_cr[$,1])</itemcode>
<itemname>./billitem/itemname/insert(invoke_resp_cr[$,2])</itemname>
<fee>./billitem/fee/insert(invoke_resp_cr[$,3])</fee>
<startdate>./billitem/startdate/insert(invoke_resp_cr[$,4])</startdate>
<enddate>./billitem/enddate/insert(invoke_resp_cr[$,5])</enddate>
<billcycle>./billitem/billcycle/insert(invoke_resp_cr[$,6])</billcycle>
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
<param3>OpenSubs_Atsv</param3>
<param4>StopSubs</param4>
<param5>StopSubs_Atsv</param5>
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
<invoke intid="$channelid" commandid="GetDictByGroup_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<MSISDN>$MSISDN</MSISDN>
<GROUPID>$GROUPID</GROUPID>
<OPERID>$operatorid</OPERID>
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
values ('CLT_bsacAtsv_qry_bill', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<billtemplno>/message_request/message_body/message_content/billtemplno/text()</billtemplno>
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
<templet><![CDATA[
<message_content></message_content>
]]></templet>
<infill>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<billitem>
<sequenec></sequenec>
<itemcode></itemcode>
<itemname></itemname>
<fee></fee>
<startdate></startdate>
<enddate></enddate>
<billcycle></billcycle>
</billitem>
]]></templet>
<infill>
<single>
<xml>
<itemcode>./billitem/itemcode/insert(invoke_resp_cr[$,0])</itemcode>
<itemname>./billitem/itemname/insert(invoke_resp_cr[$,1])</itemname>
<fee>./billitem/fee/insert(invoke_resp_cr[$,2])</fee>
<startdate>./billitem/startdate/insert(invoke_resp_cr[$,3])</startdate>
<enddate>./billitem/enddate/insert(invoke_resp_cr[$,4])</enddate>
<billcycle>./billitem/billcycle/insert(invoke_resp_cr[$,5])</billcycle>
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
', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_bill', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="QryBill_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$telnum</MSISDN>
<MONTH>$month</MONTH>
<BILLTEMPLNO>$billtemplno</BILLTEMPLNO>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="6">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
<COL_2>RESULTCRSET[*,2]</COL_2>
<COL_3>RESULTCRSET[*,3]</COL_3>
<COL_4>RESULTCRSET[*,4]</COL_4>
<COL_5>RESULTCRSET[*,5]</COL_5>
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
<invoke intid="$channelid" commandid="QrySubsAllIntServ_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<ATSVNUM>$operatorid</ATSVNUM>
<MSISDN>$telnum</MSISDN>
<OPERID>$operatorid</OPERID>
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
<COL_3>RESULTCRSET[*,4]</COL_3>
<COL_4>RESULTCRSET[*,5]</COL_4>
<COL_5>RESULTCRSET[*,6]</COL_5>
<COL_6>RESULTCRSET[*,7]</COL_6>
<COL_7>RESULTCRSET[*,8]</COL_7>
<COL_8>RESULTCRSET[*,12]</COL_8>
<COL_9>RESULTCRSET[*,9]</COL_9>
<COL_10>RESULTCRSET[*,10]</COL_10>
<COL_11>RESULTCRSET[*,11]</COL_11>
<COL_12>RESULTCRSET[*,23]</COL_12>
<COL_13>RESULTCRSET[*,25]</COL_13>
</cols>
</rows>
</crset>
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
values ('CLT_bsacAtsv_qry_currentbill', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="QryCurrentBill_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$telnum</MSISDN>
<CYCLEOFFSET>$cycleoffset</CYCLEOFFSET>
<billtemplno>$billtemplno</billtemplno>
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
values ('CLT_bsacAtsv_qry_yckf', 0, '<portal>
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
<sum_funcfee></sum_funcfee>
<sum_packagefee></sum_packagefee>
<sum_basefee></sum_basefee>
</message_content>
]]>
</templet>
<infill>
<single>
<xml>
<sum_funcfee>./message_content/sum_funcfee/insert($SUM_FUNCFEE)</sum_funcfee>
<sum_packagefee>./message_content/sum_packagefee/insert($SUM_PACKAGEFEE)</sum_packagefee>
<sum_basefee>./message_content/sum_basefee/insert($SUM_BASEFEE)</sum_basefee>
</xml>
</single>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<feeitem>
<type></type>
<name></name>
<fee></fee>
<time></time>
</feeitem>
]]></templet>
<infill>
<single>
<xml>
<type>./feeitem/type/insert(invoke_resp_cr[$,0])</type>
<name>./feeitem/name/insert(invoke_resp_cr[$,1])</name>
<fee>./feeitem/fee/insert(invoke_resp_cr[$,2])</fee>
<time>./feeitem/time/insert(invoke_resp_cr[$,3])</time>
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
', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_yckf', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="YCKFCX_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
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
<SUM_FUNCFEE>$SUM_FUNCFEE</SUM_FUNCFEE>
<SUM_PACKAGEFEE>$SUM_PACKAGEFEE</SUM_PACKAGEFEE>
<SUM_BASEFEE>$SUM_BASEFEE</SUM_BASEFEE>
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
<invoke intid="$channelid" commandid="QrySubsFeeInfo_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
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
<invoke intid="$channelid" commandid="QryUserStatus_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
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
values ('CLT_bsacAtsv_Proxy_MPAY_ACC_QRY', 2, '<OprFlag>/AccQueryReq/OprFlag/insert($oper_flag)</OprFlag>
<SecurePass>/AccQueryReq/SecurePass/insert($secure_pwd)</SecurePass>
<MSISDN>/AccQueryReq/MSISDN/insert($telnum)</MSISDN>
<ActionID>/AccQueryReq/ActionID/insert($orgid)</ActionID>
<ActionUserID>/AccQueryReq/ActionUserID/insert($operid)</ActionUserID>
<ActionTime>/AccQueryReq/ActionTime/insert($action_time)</ActionTime>
</xml>
</single>
</infill>
</xml>
</request>
<response>
<xml process="totree">
<tagset>
<RSPCODE>/AccQueryRsp/RspCode/text()</RSPCODE>
<RSPDESC>/AccQueryRsp/RspDesc/text()</RSPDESC>
<complex handle="IFELSE">
<if _00="$RSPCODE">
<BOSSSEQ>/AccQueryRsp/BossSeq/text()</BOSSSEQ>
<MSISDN>/AccQueryRsp/MSISDN/text()</MSISDN>
<CARDTYPE>/AccQueryRsp/CardType/text()</CARDTYPE>
<CARDNUM>/AccQueryRsp/CardNum/text()</CARDNUM>
<USERNAME>/AccQueryRsp/UserName/text()</USERNAME>
<OTHERNAME>/AccQueryRsp/OtherName/text()</OTHERNAME>
<TRUENAME>/AccQueryRsp/TrueName/text()</TRUENAME>
<SACCSTATUS>/AccQueryRsp/SAccStatus/text()</SACCSTATUS>
<ACCMESS>/AccQueryRsp/AccMess/text()</ACCMESS>
<SACCMESS>/AccQueryRsp/SAccMess/text()</SACCMESS>
<CASH>/AccQueryRsp/Cash/text()</CASH>
<CARD>/AccQueryRsp/Card/text()</CARD>
<WAIT>/AccQueryRsp/Wait/text()</WAIT>
</if>
</complex>
</tagset>
</xml>
</response>
</http>
</proxy>
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
values ('CLT_bsacAtsv_QryCDR', 0, '<portal>
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
<expr name="month" handle="DATEFMT">
<datetime>$month</datetime>
<fromfmt>YYYYMM</fromfmt>
<tofmt>yyyy-mm</tofmt>
</expr>
<expr name="commandid" handle="DECODE">
<param1>$cdrtype</param1>
<param2>1</param2>
<param3>QryGsmCdr_Atsv</param3>
<param4>2</param4>
<param5>QrySmsCdr_Atsv</param5>
<param6>3</param6>
<param7>QryMwCdr_Atsv</param7>
<param8>4</param8>
<param9>QryGprsCdr_Atsv</param9>
<param10>5</param10>
<param11>QryWlanCdr_Atsv</param11>
<param12>$cdrtype</param12>
</expr>
<expr name="iRsetCols" handle="DECODE">
<param1>$cdrtype</param1>
<param2>1</param2>
<param3>9</param3>
<param4>2</param4>
<param5>6</param5>
<param6>3</param6>
<param7>7</param7>
<param8>4</param8>
<param9>10</param9>
<param10>5</param10>
<param11>7</param11>
<param12>$cdrtype</param12>
</expr>
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
<xml>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Proxy_MPAY_ACC_QRY', 1, '<boss_seq>./message_content/boss_seq/insert($BOSSSEQ)</boss_seq>
<telnum>./message_content/telnum/insert($MSISDN)</telnum>
<card_type>./message_content/card_type/insert($CARDTYPE)</card_type>
<card_num>./message_content/card_num/insert($CARDNUM)</card_num>
<user_name>./message_content/user_name/insert($USERNAME)</user_name>
<other_name>./message_content/other_name/insert($OTHERNAME)</other_name>
<true_name>./message_content/true_name/insert($TRUENAME)</true_name>
<sacc_status>./message_content/sacc_status/insert($SACCSTATUS)</sacc_status>
<acc_mess>./message_content/acc_mess/insert($ACCMESS)</acc_mess>
<sacc_mess>./message_content/sacc_mess/insert($SACCMESS)</sacc_mess>
<cash>./message_content/cash/insert($CASH)</cash>
<card>./message_content/card/insert($CARD)</card>
<wait>./message_content/wait/insert($WAIT)</wait>
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
<proxy>
<http url="http://10.19.90.182/iboss/MPayReceiver" method="POST" direct="no" urlencode="0" Charset="UTF-8" retcode="$RSPCODE" retmsg="$RSPDESC">
<request>
<xml>
<templet><![CDATA[
<AccQueryReq>
<OprFlag></OprFlag>
<SecurePass></SecurePass>
<MSISDN></MSISDN>
<ActionID></ActionID>
<ActionUserID></ActionUserID>
<ActionTime></ActionTime>
</AccQueryReq>
]]></templet>
<infill>
<single>
<xml>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QryCDR', 2, '<COL_0>
<str1 fmt="%s~">RESULTCRSET[*,0]</str1>
<str2 fmt="%s~">RESULTCRSET[*,1]</str2>
<str3 fmt="%s~">RESULTCRSET[*,2]</str3>
<str4 fmt="%s~">RESULTCRSET[*,3]</str4>
<str5 fmt="%s~">RESULTCRSET[*,4]</str5>
<str6 fmt="%s">RESULTCRSET[*,5]</str6>
</COL_0>
</complex>
</cols>
</rows>
</crset>
</elseif>
<elseif _7="$iRsetCols">
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="1">
<complex handle="NTOKEN">
<COL_0>
<str1 fmt="%s~">RESULTCRSET[*,0]</str1>
<str2 fmt="%s~">RESULTCRSET[*,1]</str2>
<str3 fmt="%s~">RESULTCRSET[*,2]</str3>
<str4 fmt="%s~">RESULTCRSET[*,3]</str4>
<str5 fmt="%s~">RESULTCRSET[*,4]</str5>
<str6 fmt="%s~">RESULTCRSET[*,5]</str6>
<str7 fmt="%s">RESULTCRSET[*,6]</str7>
</COL_0>
</complex>
</cols>
</rows>
</crset>
</elseif>
<elseif _9="$iRsetCols">
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="1">
<complex handle="NTOKEN">
<COL_0>
<str1 fmt="%s~">RESULTCRSET[*,0]</str1>
<str2 fmt="%s~">RESULTCRSET[*,1]</str2>
<str3 fmt="%s~">RESULTCRSET[*,2]</str3>
<str4 fmt="%s~">RESULTCRSET[*,3]</str4>
<str5 fmt="%s~">RESULTCRSET[*,4]</str5>
<str6 fmt="%s~">RESULTCRSET[*,5]</str6>
<str7 fmt="%s~">RESULTCRSET[*,6]</str7>
<str8 fmt="%s~">RESULTCRSET[*,8]</str8>
<str9 fmt="%s">RESULTCRSET[*,7]</str9>
</COL_0>
</complex>
</cols>
</rows>
</crset>
</elseif>
<elseif _10="$iRsetCols">
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="1">
<complex handle="NTOKEN">
<COL_0>
<str1 fmt="%s~">RESULTCRSET[*,0]</str1>
<str2 fmt="%s~">RESULTCRSET[*,1]</str2>
<str3 fmt="%s~">RESULTCRSET[*,2]</str3>
<str4 fmt="%s~">RESULTCRSET[*,3]</str4>
<str5 fmt="%s~">RESULTCRSET[*,4]</str5>
<str6 fmt="%s~">RESULTCRSET[*,5]</str6>
<str7 fmt="%s~">RESULTCRSET[*,6]</str7>
<str8 fmt="%s~">RESULTCRSET[*,7]</str8>
<str9 fmt="%s~">RESULTCRSET[*,8]</str9>
<str10 fmt="%s">RESULTCRSET[*,9]</str10>
</COL_0>
</complex>
</cols>
</rows>
</crset>
</elseif>
</complex>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QuePriUsed', 0, '<portal>
<request>
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
<calctime></calctime>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<calctime>./message_content/calctime/insert($CALCTIME)</calctime>
</xml>
</single>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<privitem>
<privset></privset>
<type></type>
<sumnum></sumnum>
<leftnum></leftnum>
<unit></unit>
<feename></feename>
<servtype></servtype>
<starttime></starttime>
<endtime></endtime>
</privitem>
]]></templet>
<infill>
<single>
<xml>
<privset>./privitem/privset/insert(invoke_resp_cr[$,0])</privset>
<type>./privitem/type/insert(invoke_resp_cr[$,1])</type>
<sumnum>./privitem/sumnum/insert(invoke_resp_cr[$,2])</sumnum>
<leftnum>./privitem/leftnum/insert(invoke_resp_cr[$,3])</leftnum>
<unit>./privitem/unit/insert(invoke_resp_cr[$,4])</unit>
<feename>./privitem/feename/insert(invoke_resp_cr[$,5])</feename>
<servtype>./privitem/servtype/insert(invoke_resp_cr[$,6])</servtype>
<starttime>./privitem/starttime/insert(invoke_resp_cr[$,7])</starttime>
<endtime>./privitem/endtime/insert(invoke_resp_cr[$,8])</endtime>
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
<invoke intid="$channelid" commandid="QuePriUsed_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QueryFee', 2, '<when handle="IN">
<param1>$ACCEPT_TYPE</param1>
<param2>S20</param2>
<param3>S30</param3>
</when>
<then>
<xml>
<templet><![CDATA[<message_response>
<message_head version="1.0">
<menuid></menuid>
<process_code></process_code>
<verify_code></verify_code>
<resp_time></resp_time>
<sequence>
<req_seq></req_seq>
<operation_seq></operation_seq>
</sequence>
<retinfo>
<rettype></rettype>
<retcode></retcode>
<retmsg></retmsg>
</retinfo>
</message_head>
<message_body></message_body>
</message_response>]]></templet>
<infill>
<single>
<xml>
<menuid>/message_response/message_head/menuid/insert($menuid)</menuid>
<process_code>/message_response/message_head/process_code/insert($process_code)</process_code>
<verify_code>/message_response/message_head/verify_code/insert($verify_code)</verify_code>
<resp_time>/message_response/message_head/resp_time/insert(SYSDATE)</resp_time>
<req_seq>/message_response/message_head/sequence/req_seq/insert($req_seq)</req_seq>
<operation_seq>/message_response/message_head/sequence/operation_seq/insert($OPERATION_SEQ)</operation_seq>
<rettype>/message_response/message_head/retinfo/rettype/insert(RETTYPE)</rettype>
<retcode>/message_response/message_head/retinfo/retcode/insert(100)</retcode>
<retmsg>/message_response/message_head/retinfo/retmsg/insertCDATA(RETMSG)</retmsg>
</xml>
</single>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QueryFee', 3, '<batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<servregion></servregion>
<accept_type></accept_type>
<acct_id></acct_id>
<cust_name></cust_name>
<balance></balance>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<servregion>./message_content/servregion/insert($REGION)</servregion>
<accept_type>./message_content/accept_type/insert($ACCEPT_TYPE)</accept_type>
<acct_id>./message_content/acct_id/insert($ACCT_ID)</acct_id>
<cust_name>./message_content/cust_name/insert($CUST_NAME)</cust_name>
<balance>./message_content/balance/insert($BALANCE)</balance>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</then>
<else>
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
<servregion></servregion>
<accept_type></accept_type>
<acct_id></acct_id>
<cust_name></cust_name>
<balance></balance>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<servregion>./message_content/servregion/insert($REGION)</servregion>
<accept_type>./message_content/accept_type/insert($ACCEPT_TYPE)</accept_type>
<acct_id>./message_content/acct_id/insert($ACCT_ID)</acct_id>
<cust_name>./message_content/cust_name/insert($CUST_NAME)</cust_name>
<balance>./message_content/balance/insert($BALANCE)</balance>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</else>
</success>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QueryFee', 4, '<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="IntBank" commandid="" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum" operator="$bank_no">
<request>
<pkg>
<tagset>
<UNITID>$bank_no</UNITID>
<BANK_NO>$bank_no</BANK_NO>
<PAY_DATE>$pay_date</PAY_DATE>
<MSISDN>$telnum</MSISDN>
<ACCEPT_TYPE>$accept_type</ACCEPT_TYPE>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<REGION>$REGION</REGION>
<ACCEPT_TYPE>$ACCEPT_TYPE</ACCEPT_TYPE>
<ACCT_ID>$ACCT_ID</ACCT_ID>
<CUST_NAME>$CUST_NAME</CUST_NAME>
<BALANCE>$BALANCE</BALANCE>
<TOTAL_FEE>$TOTAL_FEE</TOTAL_FEE>
<SUM_FEE>$SUM_FEE</SUM_FEE>
<BCYCLE_COUNT>$BCYCLE_COUNT</BCYCLE_COUNT>
<expr name="RETCODE" handle="DECODE">
<param1>$RETCODE</param1>
<param2>0</param2>
<param3>100</param3>
<param4>$RETCODE</param4>
</expr>
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
<invoke intid="$channelid" commandid="NetGetAC_Atsv" retcode="$RETCODE" retmsg="$RETMSG" region="$region">
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
values ('CLT_bsacAtsv_Proxy_MPAY_ACC_QRY', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<oper_flag>/message_request/message_body/message_content/oper_flag/text()</oper_flag>
<secure_pwd>/message_request/message_body/message_content/secure_pwd/text()</secure_pwd>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<orgid>/message_request/message_body/message_content/orgid/text()</orgid>
<operid>/message_request/message_body/message_content/operid/text()</operid>
<action_time>/message_request/message_body/message_content/action_time/text()</action_time>
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
<boss_seq></boss_seq>
<telnum></telnum>
<card_type></card_type>
<card_num></card_num>
<user_name></user_name>
<other_name></other_name>
<true_name></true_name>
<sacc_status></sacc_status>
<acc_mess></acc_mess>
<sacc_mess></sacc_mess>
<cash></cash>
<card></card>
<wait></wait>
</message_content>]]>
</templet>
<infill>
<single>
<xml>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_detailbill', 0, '<portal>
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
<cycle></cycle>
<type></type>
<key></key>
<value></value>
</billItem>
]]></templet>
<infill>
<single>
<xml>
<cycle>./billItem/cycle/insert(invoke_resp_cr[$,0])</cycle>
<type>./billItem/type/insert(invoke_resp_cr[$,1])</type>
<key>./billItem/key/insert(invoke_resp_cr[$,2])</key>
<value>./billItem/value/insert(invoke_resp_cr[$,3])</value>
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
values ('CLT_bsacAtsv_qry_detailbill', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="QryDetailBill_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<MSISDN>$telnum</MSISDN>
<BILLING_CYCLE_ID>$month</BILLING_CYCLE_ID>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
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
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_numbynet', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<county_id>/message_request/message_body/message_content/county_id/text()</county_id>
<sele_rule>/message_request/message_body/message_content/sele_rule/text()</sele_rule>
<tel_prefix>/message_request/message_body/message_content/tel_prefix/text()</tel_prefix>
<tel_suffix>/message_request/message_body/message_content/tel_suffix/text()</tel_suffix>
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
<productinfo></productinfo>
<fee></fee>
<org_id></org_id>
<region></region>
</tellist>
]]></templet>
<infill>
<single>
<xml>
<telnum>./tellist/telnum/insert(invoke_resp_cr[$,0])</telnum>
<productinfo>./tellist/productinfo/insert(invoke_resp_cr[$,1])</productinfo>
<fee>./tellist/fee/insert(invoke_resp_cr[$,2])</fee>
<org_id>./tellist/org_id/insert(invoke_resp_cr[$,3])</org_id>
<region>./tellist/region/insert(invoke_resp_cr[$,4])</region>
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
', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_numbynet', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="NetQryNumByNet_Atsv" retcode="$RETCODE" retmsg="$RETMSG" region="$region">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<COUNTY_ID>$county_id</COUNTY_ID>
<MSISDN_SUFFIX>$tel_suffix</MSISDN_SUFFIX>
<SELE_RULE>$sele_rule</SELE_RULE>
<TEL_PREFIX>$tel_prefix</TEL_PREFIX>
<OPERID>$operatorid</OPERID>
<STAFF_ID>$operatorid</STAFF_ID>
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
<COL_3>RESULTCRSET[*,3]</COL_3>
<COL_4>RESULTCRSET[*,4]</COL_4>
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
<invoke intid="$channelid" commandid="NetPukquery_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
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
values ('CLT_bsacAtsv_qry_rechistory', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="NetGetAcpt_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
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
values ('CLT_bsacAtsv_NG23_ChangeSubsMonServ', 0, '<portal>
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
<invoke intid="$channelid" commandid="CancleSubsIntServ_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<OPERID>$operatorid</OPERID>
<TELNUM>$telnum</TELNUM>
<complex handle="NTOKEN">
<DETAILINFO>
<str1 fmt="%s,">$deal_type</str1>
<str2 fmt="%s,">$spid</str2>
<str3 fmt="%s,">$bizid</str3>
<str4 fmt="%s,">$biztype</str4>
<str5 fmt="%s">$domain</str5>
</DETAILINFO>
</complex>
</tagset>
</pkg>
</request>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_NG23_QrySubsDetailAvailScore', 0, '<portal>
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
<invoke intid="$channelid" commandid="QrySubsDetailAvailScore_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$MSISDN</MSISDN>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<complex handle="NTOKEN">
<SCOREINFO>
<str1 fmt="%s~">$CURSCORE</str1>
<str2 fmt="%s~">$PRISCORE</str2>
<str3 fmt="%s~">$PRIBEFSCORE</str3>
<str4 fmt="%s">$CUR_CREDIT_COUNT</str4>
</SCOREINFO>
</complex>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QueryFee', 1, '<batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<servregion></servregion>
<accept_type></accept_type>
<acct_id></acct_id>
<cust_name></cust_name>
<balance></balance>
<total_fee></total_fee>
<sum_fee></sum_fee>
<bcycle_count></bcycle_count>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<servregion>./message_content/servregion/insert($REGION)</servregion>
<accept_type>./message_content/accept_type/insert($ACCEPT_TYPE)</accept_type>
<acct_id>./message_content/acct_id/insert($ACCT_ID)</acct_id>
<cust_name>./message_content/cust_name/insert($CUST_NAME)</cust_name>
<balance>./message_content/balance/insert($BALANCE)</balance>
<total_fee>./message_content/total_fee/insert($TOTAL_FEE)</total_fee>
<sum_fee>./message_content/sum_fee/insert($SUM_FEE)</sum_fee>
<bcycle_count>./message_content/bcycle_count/insert($BCYCLE_COUNT)</bcycle_count>
</xml>
</single>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<bill>
<bcycleid></bcycleid>
<ss_fee></ss_fee>
<ys_fee></ys_fee>
<capital_fee></capital_fee>
<last_balance></last_balance>
<this_balance></this_balance>
<item_count></item_count>
<item_set></item_set>
</bill>
]]></templet>
<infill>
<single>
<xml>
<bcycleid>./bill/bcycleid/insert(invoke_resp_cr[$,0])</bcycleid>
<ss_fee>./bill/ss_fee/insert(invoke_resp_cr[$,1])</ss_fee>
<ys_fee>./bill/ys_fee/insert(invoke_resp_cr[$,2])</ys_fee>
<last_balance>./bill/last_balance/insert(invoke_resp_cr[$,3])</last_balance>
<this_balance>./bill/this_balance/insert(invoke_resp_cr[$,4])</this_balance>
<item_count>./bill/item_count/insert(invoke_resp_cr[$,5])</item_count>
<item_set>./bill/item_set/insert(invoke_resp_cr[$,6])</item_set>
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
</then>', 0);

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
<invoke intid="$channelid" commandid="CallTransfer_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
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
values ('CLT_bsacAtsv_CommScoreChange', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<opertype>/message_request/message_body/message_content/opertype/text()</opertype>
<scorevalue>/message_request/message_body/message_content/scorevalue/text()</scorevalue>
<scoretypeid>/message_request/message_body/message_content/scoretypeid/text()</scoretypeid>
<isnegative>/message_request/message_body/message_content/isnegative/text()</isnegative>
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
<invoke intid="$channelid" commandid="CommScoreChange_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<TELNUM>$telnum</TELNUM>
<OPERTYPE>$opertype</OPERTYPE>
<SCOREVALUE>$scorevalue</SCOREVALUE>
<ISNEGATIVE>$isnegative</ISNEGATIVE>
<SCORETYPEID>$scoretypeid</SCORETYPEID>
<REASON>$reason</REASON>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
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
<invoke intid="$channelid" commandid="QryProductFeeNew_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
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
values ('CLT_bsacAtsv_QueryFee', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<bank_no>/message_request/message_body/message_content/bank_no/text()</bank_no>
<pay_date>/message_request/message_body/message_content/pay_date/text()</pay_date>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<accept_type>/message_request/message_body/message_content/accept_type/text()</accept_type>
</tagset>
</xml>
</request>
<response>
<success>
<when handle="EQUAL">
<param1>$ACCEPT_TYPE</param1>
<param2>S10</param2>
</when>
<then>
<xml>
<templet><![CDATA[<message_response>
<message_head version="1.0">
<menuid></menuid>
<process_code></process_code>
<verify_code></verify_code>
<resp_time></resp_time>
<sequence>
<req_seq></req_seq>
<operation_seq></operation_seq>
</sequence>
<retinfo>
<rettype></rettype>
<retcode></retcode>
<retmsg></retmsg>
</retinfo>
</message_head>
<message_body></message_body>
</message_response>]]></templet>
<infill>
<single>
<xml>
<menuid>/message_response/message_head/menuid/insert($menuid)</menuid>
<process_code>/message_response/message_head/process_code/insert($process_code)</process_code>
<verify_code>/message_response/message_head/verify_code/insert($verify_code)</verify_code>
<resp_time>/message_response/message_head/resp_time/insert(SYSDATE)</resp_time>
<req_seq>/message_response/message_head/sequence/req_seq/insert($req_seq)</req_seq>
<operation_seq>/message_response/message_head/sequence/operation_seq/insert($OPERATION_SEQ)</operation_seq>
<rettype>/message_response/message_head/retinfo/rettype/insert(RETTYPE)</rettype>
<retcode>/message_response/message_head/retinfo/retcode/insert(100)</retcode>
<retmsg>/message_response/message_head/retinfo/retmsg/insertCDATA(RETMSG)</retmsg>
</xml>
</single>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_balance', 1, '<when handle="EQUAL">
<param1>$SERV_FLAG</param1>
<param2>0</param2>
</when>
<then>
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
<serv_flag></serv_flag>
<exp_date_flag></exp_date_flag>
<this_exp_date></this_exp_date>
<freebalance></freebalance>
<protocol_all></protocol_all>
<present_all></present_all>
<protcl_curcycle></protcl_curcycle>
<protcl_used></protcl_used>
<protcl_left></protcl_left>
<presnt_curcycle></presnt_curcycle>
<presnt_used></presnt_used>
<presnt_left></presnt_left>
</message_content>
]]></templet>
<infill>
<single>
<xml>
<serv_flag>./message_content/serv_flag/insert($SERV_FLAG)</serv_flag>
<exp_date_flag>./message_content/exp_date_flag/insert($EXP_DATE_FLAG)</exp_date_flag>
<this_exp_date>./message_content/this_exp_date/insert($THIS_EXP_DATE)</this_exp_date>
<freebalance>./message_content/freebalance/insert($FREEBALANCE)</freebalance>
<protocol_all>./message_content/protocol_all/insert($PROTOCOL_ALL)</protocol_all>
<present_all>./message_content/present_all/insert($PRESENT_ALL)</present_all>
<protcl_curcycle>./message_content/protcl_curcycle/insert($PROTCL_CURCYCLE)</protcl_curcycle>
<protcl_used>./message_content/protcl_used/insert($PROTCL_USED)</protcl_used>
<protcl_left>./message_content/protcl_left/insert($PRESNT_LEFT)</protcl_left>
<presnt_curcycle>./message_content/presnt_curcycle/insert($PRESNT_CURCYCLE)</presnt_curcycle>
<presnt_used>./message_content/presnt_used/insert($PRESNT_USED)</presnt_used>
<presnt_left>./message_content/presnt_left/insert($PRESNT_LEFT)</presnt_left>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</then>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_balance', 2, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="QryBalance_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$telnum</MSISDN>
<COMMANDID1>QryBilledDebt_Atsv</COMMANDID1>
<COMMANDID2>QryLeftMoney3_Atsv</COMMANDID2>
<QRYTYPE>0</QRYTYPE>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<SERV_FLAG>$SERV_FLAG</SERV_FLAG>
<FREEBALANCE>$FEE</FREEBALANCE>
<PROTOCOL_ALL>$PROTOCOL_ALL</PROTOCOL_ALL>
<PRESENT_ALL>$PRESENT_ALL</PRESENT_ALL>
<PROTCL_CURCYCLE>$PROTCL_CURCYCLE</PROTCL_CURCYCLE>
<PROTCL_USED>$PROTCL_USED</PROTCL_USED>
<PROTCL_LEFT>$PROTCL_LEFT</PROTCL_LEFT>
<PRESNT_CURCYCLE>$PRESNT_CURCYCLE</PRESNT_CURCYCLE>
<PRESNT_USED>$PRESNT_USED</PRESNT_USED>
<PRESNT_LEFT>$PRESNT_LEFT</PRESNT_LEFT>
<PAY_BALANCE>$PAY_BALANCE</PAY_BALANCE>
<CARD_BALANCE>$CARD_BALANCE</CARD_BALANCE>
<CURRENT_XYK>$CURRENT_XYK</CURRENT_XYK>
<CURRENT_COMP>$CURRENT_COMP</CURRENT_COMP>
<LEFT_XYK>$LEFT_XYK</LEFT_XYK>
<LEFT_COMP>$LEFT_COMP</LEFT_COMP>
<LEFT_SKY>$LEFT_SKY</LEFT_SKY>
<LEFT_NOPAY>$LEFT_NOPAY</LEFT_NOPAY>
<EXP_DATE_FLAG>$EXP_DATE_FLAG</EXP_DATE_FLAG>
<THIS_EXP_DATE>$THIS_EXP_DATE</THIS_EXP_DATE>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_ChargeFee', 3, '<response>
<pkg>
<tagset>
<REGION>$REGION</REGION>
<ACCEPT_NAME>$ACCEPT_NAME</ACCEPT_NAME>
<ACCT_ID>$ACCT_ID</ACCT_ID>
<CUST_NAME>$CUST_NAME</CUST_NAME>
<PAY_DATE>$PAY_DATE</PAY_DATE>
<PRT_COUNT>$PRT_COUNT</PRT_COUNT>
<BCYCLE_COUNT>$BCYCLE_COUNT</BCYCLE_COUNT>
<COMMENT>$COMMENT</COMMENT>
<BILL_NBR>$BILL_NBR</BILL_NBR>
<expr name="RETCODE" handle="DECODE">
<param1>$RETCODE</param1>
<param2>0</param2>
<param3>100</param3>
<param4>$RETCODE</param4>
</expr>
</tagset>
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
values ('CLT_bsacAtsv_ChargeFee', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<bank_no>/message_request/message_body/message_content/bank_no/text()</bank_no>
<pay_date>/message_request/message_body/message_content/pay_date/text()</pay_date>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<bank_nbr>/message_request/message_body/message_content/bank_nbr/text()</bank_nbr>
<amount>/message_request/message_body/message_content/amount/text()</amount>
<accept_type>/message_request/message_body/message_content/accept_type/text()</accept_type>
<print_flag>/message_request/message_body/message_content/print_flag/text()</print_flag>
<bsite>/message_request/message_body/message_content/bsite/text()</bsite>
<boperid>/message_request/message_body/message_content/boperid/text()</boperid>
<systemid>/message_request/message_body/message_content/systemid/text()</systemid>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<templet><![CDATA[<message_response>
<message_head version="1.0">
<menuid></menuid>
<process_code></process_code>
<verify_code></verify_code>
<resp_time></resp_time>
<sequence>
<req_seq></req_seq>
<operation_seq></operation_seq>
</sequence>
<retinfo>
<rettype></rettype>
<retcode></retcode>
<retmsg></retmsg>
</retinfo>
</message_head>
<message_body></message_body>
</message_response>]]></templet>
<infill>
<single>
<xml>
<menuid>/message_response/message_head/menuid/insert($menuid)</menuid>
<process_code>/message_response/message_head/process_code/insert($process_code)</process_code>
<verify_code>/message_response/message_head/verify_code/insert($verify_code)</verify_code>
<resp_time>/message_response/message_head/resp_time/insert(SYSDATE)</resp_time>
<req_seq>/message_response/message_head/sequence/req_seq/insert($req_seq)</req_seq>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_ChargeFee', 1, '<operation_seq>/message_response/message_head/sequence/operation_seq/insert($OPERATION_SEQ)</operation_seq>
<rettype>/message_response/message_head/retinfo/rettype/insert(RETTYPE)</rettype>
<retcode>/message_response/message_head/retinfo/retcode/insert(100)</retcode>
<retmsg>/message_response/message_head/retinfo/retmsg/insertCDATA(RETMSG)</retmsg>
</xml>
</single>
<batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<servregion></servregion>
<bill_nbr></bill_nbr>
<accept_name></accept_name>
<acct_id></acct_id>
<cust_name></cust_name>
<pay_date></pay_date>
<prt_count></prt_count>
<comment></comment>
<bcycle_count></bcycle_count>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<servregion>./message_content/servregion/insert($REGION)</servregion>
<bill_nbr>./message_content/bill_nbr/insert($BILL_NBR)</bill_nbr>
<accept_name>./message_content/accept_name/insert($ACCEPT_NAME)</accept_name>
<acct_id>./message_content/acct_id/insert($ACCT_ID)</acct_id>
<cust_name>./message_content/cust_name/insert($CUST_NAME)</cust_name>
<pay_date>./message_content/pay_date/insert($PAY_DATE)</pay_date>
<prt_count>./message_content/prt_count/insert($PRT_COUNT)</prt_count>
<comment>./message_content/comment/insert($COMMENT)</comment>
<bcycle_count>./message_content/bcycle_count/insert($BCYCLE_COUNT)</bcycle_count>
</xml>
</single>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<bill>
<bcycleid></bcycleid>
<ss_fee></ss_fee>
<ys_fee></ys_fee>
<capital_fee></capital_fee>
<last_balance></last_balance>
<this_balance></this_balance>
<item_count></item_count>
<item_set></item_set>
</bill>
]]></templet>
<infill>
<single>
<xml>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_ChargeFee', 2, ' <bcycleid>./bill/bcycleid/insert(invoke_resp_cr[$,0])</bcycleid>
<ss_fee>./bill/ss_fee/insert(invoke_resp_cr[$,1])</ss_fee>
<ys_fee>./bill/ys_fee/insert(invoke_resp_cr[$,2])</ys_fee>
<capital_fee>./bill/capital_fee/insert(invoke_resp_cr[$,3])</capital_fee>
<last_balance>./bill/last_balance/insert(invoke_resp_cr[$,4])</last_balance>
<this_balance>./bill/this_balance/insert(invoke_resp_cr[$,5])</this_balance>
<item_count>./bill/item_count/insert(invoke_resp_cr[$,6])</item_count>
<item_set>./bill/item_set/insert(invoke_resp_cr[$,7])</item_set>
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
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="IntBank" commandid="" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum" operator="$bank_no">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<BANK_NO>$bank_no</BANK_NO>
<PAY_DATE>$pay_date</PAY_DATE>
<MSISDN>$telnum</MSISDN>
<BANK_NBR>$bank_nbr</BANK_NBR>
<AMOUNT>$amount</AMOUNT>
<ACCEPT_TYPE>$accept_type</ACCEPT_TYPE>
<PRINT_FLAG>$print_flag</PRINT_FLAG>
<BSITE>$bsite</BSITE>
<BOPERID>$operatorid</BOPERID>
<OUTSYSTEMID>$systemid</OUTSYSTEMID>
</tagset>
</pkg>
</request>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('SelfTerminalProtocolResp', 0, '<head>
<xml>
<templet><![CDATA[<message_response>
<message_head version="1.0">
<menuid></menuid>
<process_code></process_code>
<verify_code></verify_code>
<resp_time></resp_time>
<sequence>
<req_seq></req_seq>
<operation_seq></operation_seq>
</sequence>
<retinfo>
<rettype></rettype>
<retcode></retcode>
<retmsg></retmsg>
</retinfo>
</message_head>
</message_response>]]></templet>
<infill>
<single>
<xml>
<menuid>/message_response/message_head/menuid/insert($menuid)</menuid>
<process_code>/message_response/message_head/process_code/insert($process_code)</process_code>
<verify_code>/message_response/message_head/verify_code/insert($verify_code)</verify_code>
<resp_time>/message_response/message_head/resp_time/insert(SYSDATE)</resp_time>
<req_seq>/message_response/message_head/sequence/req_seq/insert($req_seq)</req_seq>
<operation_seq>/message_response/message_head/sequence/operation_seq/insert($OPERATION_SEQ)</operation_seq>
<rettype>/message_response/message_head/retinfo/rettype/insert(RETTYPE)</rettype>
<retcode>/message_response/message_head/retinfo/retcode/insert(RETCODE)</retcode>
<retmsg>/message_response/message_head/retinfo/retmsg/insertCDATA(RETMSG)</retmsg>
</xml>
</single>
</infill>
</xml>
</head>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QryCDR', 1, '<cust_name>./message_content/cust_name/insert($CUST_NAME)</cust_name>
<product_name>./message_content/product_name/insert($PRODUCT_NAME)</product_name>
<product_info>./message_content/product_info/insert($PRODUCT_INFO)</product_info>
<createdate>./message_content/createdate/insert($CREATEDATE)</createdate>
<total_fee>./message_content/total_fee/insert($TOTAL_FEE)</total_fee>
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
<billitem>./billitem/bill/insert(invoke_resp_cr[$,0])</billitem>
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
<invoke intid="$channelid" commandid="$commandid" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$telnum</MSISDN>
<TELNUM>$telnum</TELNUM>
<MONTH>$month</MONTH>
<COUNT_FLAG>$count_flag</COUNT_FLAG>
<QUERY_FLAG>$query_flag</QUERY_FLAG>
<FACTORY>$factory</FACTORY>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<CUST_NAME>$CUST_NAME</CUST_NAME>
<PRODUCT_NAME>$PRODUCT_NAME</PRODUCT_NAME>
<PRODUCT_INFO>$PRODUCT_INFO</PRODUCT_INFO>
<TOTAL_FEE>$TOTAL_FEE</TOTAL_FEE>
<CREATEDATE>$CREATEDATE</CREATEDATE>
</tagset>
<complex handle="IFELSE">
<if _5="$iRsetCols">
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="1">
<complex handle="NTOKEN">
<COL_0>
<str1 fmt="%s~">RESULTCRSET[*,0]</str1>
<str2 fmt="%s~">RESULTCRSET[*,1]</str2>
<str3 fmt="%s~">RESULTCRSET[*,2]</str3>
<str4 fmt="%s~">RESULTCRSET[*,3]</str4>
<str5 fmt="%s">RESULTCRSET[*,4]</str5>
</COL_0>
</complex>
</cols>
</rows>
</crset>
</if>
<elseif _6="$iRsetCols">
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="1">
<complex handle="NTOKEN">', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QuePriUsed', 1, '<tagset>
<UNITID>$unitid</UNITID>
<MSISDN>$msisdn</MSISDN>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<success>
<pkg>
<tagset>
<CALCTIME>$CALCTIME</CALCTIME>
</tagset>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="9">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
<COL_2>RESULTCRSET[*,2]</COL_2>
<COL_3>RESULTCRSET[*,3]</COL_3>
<COL_4>RESULTCRSET[*,4]</COL_4>
<COL_5>RESULTCRSET[*,5]</COL_5>
<COL_6>RESULTCRSET[*,6]</COL_6>
<COL_7>RESULTCRSET[*,7]</COL_7>
<COL_8>RESULTCRSET[*,8]</COL_8>
</cols>
</rows>
</crset>
</pkg>
</success>
<fail>
<pkg>
<tagset>
<CALCTIME>$CALCTIME</CALCTIME>
</tagset>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="9">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
<COL_2>RESULTCRSET[*,2]</COL_2>
<COL_3>RESULTCRSET[*,3]</COL_3>
<COL_4>RESULTCRSET[*,4]</COL_4>
<COL_5>RESULTCRSET[*,5]</COL_5>
<COL_6>RESULTCRSET[*,6]</COL_6>
<COL_7>RESULTCRSET[*,7]</COL_7>
<COL_8>RESULTCRSET[*,8]</COL_8>
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
<invoke intid="$channelid" commandid="SendSMSInfo_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
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
<invoke intid="$channelid" commandid="NetChkAndChgPwd_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
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
values ('SelfTerminalProtocolReq', 0, '<head ruleid="$process_code" channel="$channelid" operator="$operatorid">
<xml process="totree">
<tagset>
<menuid>/message_request/message_head/menuid/text()</menuid>
<process_code>/message_request/message_head/process_code/text()</process_code>
<verify_code>/message_request/message_head/verify_code/text()</verify_code>
<req_time>/message_request/message_head/req_time/text()</req_time>
<req_seq>/message_request/message_head/req_seq/text()</req_seq>
<unicontact>/message_request/message_head/unicontact/text()</unicontact>
<testflag need="0">/message_request/message_head/testflag/text()</testflag>
<route_type>/message_request/message_head/route/route_type/text()</route_type>
<route_value>/message_request/message_head/route/route_value/text()</route_value>
<channelid>/message_request/message_head/channelinfo/channelid/text()</channelid>
<unitid>/message_request/message_head/channelinfo/unitid/text()</unitid>
<operatorid>/message_request/message_head/channelinfo/operatorid/text()</operatorid>
<complex handle="IFELSE">
<if _0="$route_type">
<region>$route_value</region>
</if>
<elseif _1="$route_type">
<telnum>$route_value</telnum>
</elseif>
<else>
<region>$route_value</region>
</else>
</complex>
</tagset>
</xml>
</head>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Busi_Mpaycharge', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<oper_flag>/message_request/message_body/message_content/oper_flag/text()</oper_flag>
<secure_pwd>/message_request/message_body/message_content/secure_pwd/text()</secure_pwd>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<orgid>/message_request/message_body/message_content/orgid/text()</orgid>
<operid>/message_request/message_body/message_content/operid/text()</operid>
<action_time>/message_request/message_body/message_content/action_time/text()</action_time>
<payed>/message_request/message_body/message_content/payed/text()</payed>
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
<boss_seq></boss_seq>
<mpay_seq></mpay_seq>
<cash></cash>
</message_content>]]>
</templet>
<infill>
<single>
<xml>
<boss_seq>./message_content/boss_seq/insert($BOSSSEQ)</boss_seq>
<mpay_seq>./message_content/mpay_seq/insert($MPAYSYSSEQ)</mpay_seq>
<cash>./message_content/cash/insert($ACCCASHBALA)</cash>
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
<proxy>
<http url="http://10.19.90.182/iboss/MPayReceiver" method="POST" direct="no" urlencode="0" Charset="UTF-8" retcode="$RSPCODE" retmsg="$RSPDESC">
<request>
<xml>
<templet><![CDATA[
<PaymentReq>
    <OprFlag></OprFlag>
    <SecurePass></SecurePass>
    <MSISDN></MSISDN>
    <ActionID></ActionID>
    <ActionUserID></ActionUserID>
    <ActionTime></ActionTime>
    <Payed></Payed>
</PaymentReq>
]]></templet>
<infill>
<single>
<xml>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_Busi_Mpaycharge', 1, '<OprFlag>/PaymentReq/OprFlag/insert($oper_flag)</OprFlag>
<SecurePass>/PaymentReq/SecurePass/insert($secure_pwd)</SecurePass>
<MSISDN>/PaymentReq/MSISDN/insert($telnum)</MSISDN>
<ActionID>/PaymentReq/ActionID/insert($orgid)</ActionID>
<ActionUserID>/PaymentReq/ActionUserID/insert($operid)</ActionUserID>
<ActionTime>/PaymentReq/ActionTime/insert($action_time)</ActionTime>
<Payed>/PaymentReq/Payed/insert($payed)</Payed>
</xml>
</single>
</infill>
</xml>
</request>
<response>
<xml process="totree">
<tagset>
<RSPCODE>/PaymentRsp/RspCode/text()</RSPCODE>
<RSPDESC>/PaymentRsp/RspDesc/text()</RSPDESC>
<complex handle="IFELSE">
<if _00="$RSPCODE">
<BOSSSEQ>/PaymentRsp/BossSeq/text()</BOSSSEQ>
<RSPINFO>/PaymentRsp/RspInfo/text()</RSPINFO>
<MPAYSYSSEQ>/PaymentRsp/MpaySysSeq/text()</MPAYSYSSEQ>
<ACCCASHBALA>/PaymentRsp/AccCashBala/text()</ACCCASHBALA>
</if>
</complex>
</tagset>
</xml>
</response>
</http>
</proxy>
</opProcessor>
</portal>', 0);

insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_balance', 0, '<portal>
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
<when handle="EQUAL">
<param1>$SERV_FLAG</param1>
<param2>1</param2>
</when>
<then>
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
<serv_flag></serv_flag>
<exp_date_flag></exp_date_flag>
<this_exp_date></this_exp_date>
<pay_balance></pay_balance>
<card_balance></card_balance>
<current_xyk></current_xyk>
<current_comp></current_comp>
<left_xyk></left_xyk>
<left_comp></left_comp>
<left_nopay></left_nopay>
<left_sky></left_sky>
</message_content>
]]></templet>
<infill>
<single>
<xml>
<serv_flag>./message_content/serv_flag/insert($SERV_FLAG)</serv_flag>
<exp_date_flag>./message_content/exp_date_flag/insert($EXP_DATE_FLAG)</exp_date_flag>
<this_exp_date>./message_content/this_exp_date/insert($THIS_EXP_DATE)</this_exp_date>
<pay_balance>./message_content/pay_balance/insert($PAY_BALANCE)</pay_balance>
<card_balance>./message_content/card_balance/insert($CARD_BALANCE)</card_balance>
<current_xyk>./message_content/current_xyk/insert($CURRENT_XYK)</current_xyk>
<current_comp>./message_content/current_comp/insert($CURRENT_COMP)</current_comp>
<left_xyk>./message_content/left_xyk/insert($LEFT_XYK)</left_xyk>
<left_comp>./message_content/left_comp/insert($LEFT_COMP)</left_comp>
<left_nopay>./message_content/left_nopay/insert($LEFT_NOPAY)</left_nopay>
<left_sky>./message_content/left_sky/insert($LEFT_SKY)</left_sky>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</then>', 0);
prompt 70 records loaded

prompt Loading int_retcode_convert...
insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_alarmbalance', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '余额提醒设置接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_calltransfer', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '呼叫转移设置');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_cancelsp', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '增值业务统一退订');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_chgpwd', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '密码修改');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_chgscore', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '  积分扣减');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_cidcheck', '0', '100', '', '0', 1, to_date('21-08-2011', 'dd-mm-yyyy'), to_date('21-08-2011', 'dd-mm-yyyy'), '身份证验证');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_mpaycharge', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '  手机支付主账户现金充值');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_occupytel', '0', '100', '', '0', 1, to_date('21-08-2011', 'dd-mm-yyyy'), to_date('21-08-2011', 'dd-mm-yyyy'), '自助选号预订接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_productrec', '0', '100', '', '0', 1, to_date('22-08-2010', 'dd-mm-yyyy'), to_date('22-08-2010', 'dd-mm-yyyy'), '产品受理通用接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_pwdreset', '0', '100', '', '0', 1, to_date('21-08-2011', 'dd-mm-yyyy'), to_date('21-08-2011', 'dd-mm-yyyy'), '密码重置');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_sendsms', '100', '0', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('11-08-2010', 'dd-mm-yyyy'), '下发短信接口(内容)');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_sendsmsinfo', '0', '100', '', '0', 1, to_date('10-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '下发短信接口（参数）');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_busi_stopopensubs', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '停开机');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_alarmbalance', '0', '100', '', '0', 1, to_date('17-08-2010', 'dd-mm-yyyy'), to_date('17-08-2010', 'dd-mm-yyyy'), '查询余额提醒阀值');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_balance', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '账户余额查询接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_bill', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '话费查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_cdr', '0', '100', '', '0', 1, to_date('13-08-2010', 'dd-mm-yyyy'), to_date('13-08-2010', 'dd-mm-yyyy'), '详单查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_chargehistory', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '缴费历史查询接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_currentbill', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '手机归属地查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_dictitem', '0', '100', '', '0', 1, to_date('17-08-2010', 'dd-mm-yyyy'), to_date('17-08-2010', 'dd-mm-yyyy'), '查询字典数据');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_mpayacc', '0', '100', '', '0', 1, to_date('18-08-2010', 'dd-mm-yyyy'), to_date('18-08-2010', 'dd-mm-yyyy'), '手机支付账户信息查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_numbynet', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '自助选号查询接口');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_numregion', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '  号码归属地查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_productfee', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '产品资费信息查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_pukcode', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), 'puk码查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_rechistory', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '受理历史查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_scorevalue', '0', '100', '', '0', 1, to_date('10-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '查询用户积分');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_spinfo', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '增值业务统一查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_taocan', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '  套餐信息查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_userinfo', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '  密码验证');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_userstate', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '用户状态查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_yckf', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '  月初扣费查询');

insert into int_retcode_convert (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_zfinfo', '0', '100', '', '0', 1, to_date('11-08-2010', 'dd-mm-yyyy'), to_date('10-08-2010', 'dd-mm-yyyy'), '  本机品牌资费及已开通服务、优惠查询');
commit;
prompt 33 records loaded

prompt Enabling triggers for int_protocol_define...
alter table int_protocol_define enable all triggers;
prompt Enabling triggers for int_access_point...
alter table int_access_point enable all triggers;
prompt Enabling triggers for int_business_define...
alter table int_business_define enable all triggers;
prompt Enabling triggers for int_flow_define...
alter table int_flow_define enable all triggers;
prompt Enabling triggers for int_retcode_convert...
alter table int_retcode_convert enable all triggers;

-------------------------------------------------------
set feedback on
set define on
prompt Done.