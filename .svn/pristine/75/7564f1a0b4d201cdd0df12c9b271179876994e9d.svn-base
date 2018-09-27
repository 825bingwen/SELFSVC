--==============================================================
-- 本脚本包含以下内容（用户ngsh）

-- 1.删除用户下所有数据库表数据(delete)
-- 2.建初始化表数据

-- 本脚本以ngsh用户执行
-- 
--==============================================================
set feedback off
set define off
-------------------------------------------------------
prompt Disabling triggers for SH_ALARM_BIND...
alter table SH_ALARM_BIND disable all triggers;
prompt Disabling triggers for SH_ALARM_INFO...
alter table SH_ALARM_INFO disable all triggers;
prompt Disabling triggers for SH_ALARM_RULE...
alter table SH_ALARM_RULE disable all triggers;
prompt Disabling triggers for SH_CASHFILE_LOG...
alter table SH_CASHFILE_LOG disable all triggers;
prompt Disabling triggers for SH_CASHRECORD_LOG...
alter table SH_CASHRECORD_LOG disable all triggers;
prompt Disabling triggers for SH_CHARGE_LOG...
alter table SH_CHARGE_LOG disable all triggers;
prompt Disabling triggers for SH_CHECK_LOG...
alter table SH_CHECK_LOG disable all triggers;
prompt Disabling triggers for SH_CHOOSETEL_NUM...
alter table SH_CHOOSETEL_NUM disable all triggers;
prompt Disabling triggers for SH_COMMONPAY_LOG...
alter table SH_COMMONPAY_LOG disable all triggers;
prompt Disabling triggers for SH_DICT_ITEM...
alter table SH_DICT_ITEM disable all triggers;
prompt Disabling triggers for SH_GROUP_ADV...
alter table SH_GROUP_ADV disable all triggers;
prompt Disabling triggers for SH_GROUP_BUDDY...
alter table SH_GROUP_BUDDY disable all triggers;
prompt Disabling triggers for SH_GROUP_INFO...
alter table SH_GROUP_INFO disable all triggers;
prompt Disabling triggers for SH_GROUP_MENU...
alter table SH_GROUP_MENU disable all triggers;
prompt Disabling triggers for SH_GROUP_SCREEN...
alter table SH_GROUP_SCREEN disable all triggers;
prompt Disabling triggers for SH_INFO_ABATE...
alter table SH_INFO_ABATE disable all triggers;
prompt Disabling triggers for SH_INFO_BUDDY...
alter table SH_INFO_BUDDY disable all triggers;
prompt Disabling triggers for SH_INFO_MANUFACTURER...
alter table SH_INFO_MANUFACTURER disable all triggers;
prompt Disabling triggers for SH_INFO_MEDIARES...
alter table SH_INFO_MEDIARES disable all triggers;
prompt Disabling triggers for SH_INFO_PLUGIN...
alter table SH_INFO_PLUGIN disable all triggers;

prompt Disabling triggers for SH_LOG_ABATE...
alter table SH_LOG_ABATE disable all triggers;
prompt Disabling triggers for SH_LOG_AUDIT...
alter table SH_LOG_AUDIT disable all triggers;
prompt Disabling triggers for SH_LOG_BUSINESS...
alter table SH_LOG_BUSINESS disable all triggers;
prompt Disabling triggers for SH_LOG_CASHRETURN...
alter table SH_LOG_CASHRETURN disable all triggers;
prompt Disabling triggers for SH_LOG_CHECKBILL...
alter table SH_LOG_CHECKBILL disable all triggers;
prompt Disabling triggers for SH_LOG_LOGINERRNUM...
alter table SH_LOG_LOGINERRNUM disable all triggers;
prompt Disabling triggers for SH_LOG_MOBILEPAY...
alter table SH_LOG_MOBILEPAY disable all triggers;
prompt Disabling triggers for SH_LOG_MOBILEWALLET...
alter table SH_LOG_MOBILEWALLET disable all triggers;
prompt Disabling triggers for SH_LOG_PRINTBILLNUM...
alter table SH_LOG_PRINTBILLNUM disable all triggers;
prompt Disabling triggers for SH_LOG_PRINTINVOICE...
alter table SH_LOG_PRINTINVOICE disable all triggers;
prompt Disabling triggers for SH_LOG_PRIV...
alter table SH_LOG_PRIV disable all triggers;
prompt Disabling triggers for SH_LOG_PRODCHANGE...
alter table SH_LOG_PRODCHANGE disable all triggers;
prompt Disabling triggers for SH_LOG_TERMSTATUS...
alter table SH_LOG_TERMSTATUS disable all triggers;
prompt Disabling triggers for SH_LOG_TERMUP...
alter table SH_LOG_TERMUP disable all triggers;
prompt Disabling triggers for SH_MENU_ITEM...
alter table SH_MENU_ITEM disable all triggers;
prompt Disabling triggers for SH_MENU_ITEM_MGR...
alter table SH_MENU_ITEM_MGR disable all triggers;
prompt Disabling triggers for SH_OPERATOR_ROLE...
alter table SH_OPERATOR_ROLE disable all triggers;
prompt Disabling triggers for SH_PARAM...
alter table SH_PARAM disable all triggers;
prompt Disabling triggers for SH_PARAM_VALUE...
alter table SH_PARAM_VALUE disable all triggers;
prompt Disabling triggers for SH_PC_DSMP_SPBIZINFO...
alter table SH_PC_DSMP_SPBIZINFO disable all triggers;
prompt Disabling triggers for SH_RANDOM_PASSWORD...
alter table SH_RANDOM_PASSWORD disable all triggers;
prompt Disabling triggers for SH_REC_LOG...
alter table SH_REC_LOG disable all triggers;
prompt Disabling triggers for SH_REC_LOG_MGR...
alter table SH_REC_LOG_MGR disable all triggers;
prompt Disabling triggers for SH_ROLE_INFO...
alter table SH_ROLE_INFO disable all triggers;
prompt Disabling triggers for SH_ROLE_MENU...
alter table SH_ROLE_MENU disable all triggers;
prompt Disabling triggers for SH_SELLGOODS_LOG...
alter table SH_SELLGOODS_LOG disable all triggers;
prompt Disabling triggers for SH_TERM_CONFIG...
alter table SH_TERM_CONFIG disable all triggers;
prompt Disabling triggers for SH_TERM_GROUP...
alter table SH_TERM_GROUP disable all triggers;
prompt Disabling triggers for SH_TERM_MODEL...
alter table SH_TERM_MODEL disable all triggers;
prompt Disabling triggers for SH_UNIONFILE_LOG...
alter table SH_UNIONFILE_LOG disable all triggers;
prompt Disabling triggers for SH_UNIONPAY_LOG...
alter table SH_UNIONPAY_LOG disable all triggers;
prompt Disabling triggers for SH_UNIONRECORD_LOG...
alter table SH_UNIONRECORD_LOG disable all triggers;



prompt Deleting SH_UNIONRECORD_LOG...
delete from SH_UNIONRECORD_LOG;
commit;
prompt Deleting SH_UNIONPAY_LOG...
delete from SH_UNIONPAY_LOG;
commit;
prompt Deleting SH_UNIONFILE_LOG...
delete from SH_UNIONFILE_LOG;
commit;
prompt Deleting SH_TERM_MODEL...
delete from SH_TERM_MODEL;
commit;
prompt Deleting SH_TERM_GROUP...
delete from SH_TERM_GROUP;
commit;
prompt Deleting SH_TERM_CONFIG...
delete from SH_TERM_CONFIG;
commit;
prompt Deleting SH_SELLGOODS_LOG...
delete from SH_SELLGOODS_LOG;
commit;
prompt Deleting SH_ROLE_MENU...
delete from SH_ROLE_MENU;
commit;
prompt Deleting SH_ROLE_INFO...
delete from SH_ROLE_INFO;
commit;
prompt Deleting SH_REC_LOG_MGR...
delete from SH_REC_LOG_MGR;
commit;
prompt Deleting SH_REC_LOG...
delete from SH_REC_LOG;
commit;
prompt Deleting SH_RANDOM_PASSWORD...
delete from SH_RANDOM_PASSWORD;
commit;
prompt Deleting SH_PC_DSMP_SPBIZINFO...
delete from SH_PC_DSMP_SPBIZINFO;
commit;
prompt Deleting SH_PARAM_VALUE...
delete from SH_PARAM_VALUE;
commit;
prompt Deleting SH_PARAM...
delete from SH_PARAM;
commit;
prompt Deleting SH_OPERATOR_ROLE...
delete from SH_OPERATOR_ROLE;
commit;
prompt Deleting SH_MENU_ITEM_MGR...
delete from SH_MENU_ITEM_MGR;
commit;
prompt Deleting SH_MENU_ITEM...
delete from SH_MENU_ITEM;
commit;
prompt Deleting SH_LOG_TERMUP...
delete from SH_LOG_TERMUP;
commit;
prompt Deleting SH_LOG_TERMSTATUS...
delete from SH_LOG_TERMSTATUS;
commit;
prompt Deleting SH_LOG_PRODCHANGE...
delete from SH_LOG_PRODCHANGE;
commit;
prompt Deleting SH_LOG_PRIV...
delete from SH_LOG_PRIV;
commit;
prompt Deleting SH_LOG_PRINTINVOICE...
delete from SH_LOG_PRINTINVOICE;
commit;
prompt Deleting SH_LOG_PRINTBILLNUM...
delete from SH_LOG_PRINTBILLNUM;
commit;
prompt Deleting SH_LOG_MOBILEWALLET...
delete from SH_LOG_MOBILEWALLET;
commit;
prompt Deleting SH_LOG_MOBILEPAY...
delete from SH_LOG_MOBILEPAY;
commit;
prompt Deleting SH_LOG_LOGINERRNUM...
delete from SH_LOG_LOGINERRNUM;
commit;
prompt Deleting SH_LOG_CHECKBILL...
delete from SH_LOG_CHECKBILL;
commit;
prompt Deleting SH_LOG_CASHRETURN...
delete from SH_LOG_CASHRETURN;
commit;
prompt Deleting SH_LOG_BUSINESS...
delete from SH_LOG_BUSINESS;
commit;
prompt Deleting SH_LOG_AUDIT...
delete from SH_LOG_AUDIT;
commit;
prompt Deleting SH_LOG_ABATE...
delete from SH_LOG_ABATE;
commit;

prompt Deleting SH_INFO_PLUGIN...
delete from SH_INFO_PLUGIN;
commit;
prompt Deleting SH_INFO_MEDIARES...
delete from SH_INFO_MEDIARES;
commit;
prompt Deleting SH_INFO_MANUFACTURER...
delete from SH_INFO_MANUFACTURER;
commit;
prompt Deleting SH_INFO_BUDDY...
delete from SH_INFO_BUDDY;
commit;
prompt Deleting SH_INFO_ABATE...
delete from SH_INFO_ABATE;
commit;
prompt Deleting SH_GROUP_SCREEN...
delete from SH_GROUP_SCREEN;
commit;
prompt Deleting SH_GROUP_MENU...
delete from SH_GROUP_MENU;
commit;
prompt Deleting SH_GROUP_INFO...
delete from SH_GROUP_INFO;
commit;
prompt Deleting SH_GROUP_BUDDY...
delete from SH_GROUP_BUDDY;
commit;
prompt Deleting SH_GROUP_ADV...
delete from SH_GROUP_ADV;
commit;
prompt Deleting SH_DICT_ITEM...
delete from SH_DICT_ITEM;
commit;
prompt Deleting SH_COMMONPAY_LOG...
delete from SH_COMMONPAY_LOG;
commit;
prompt Deleting SH_CHOOSETEL_NUM...
delete from SH_CHOOSETEL_NUM;
commit;
prompt Deleting SH_CHECK_LOG...
delete from SH_CHECK_LOG;
commit;
prompt Deleting SH_CHARGE_LOG...
delete from SH_CHARGE_LOG;
commit;
prompt Deleting SH_CASHRECORD_LOG...
delete from SH_CASHRECORD_LOG;
commit;
prompt Deleting SH_CASHFILE_LOG...
delete from SH_CASHFILE_LOG;
commit;
prompt Deleting SH_ALARM_RULE...
delete from SH_ALARM_RULE;
commit;
prompt Deleting SH_ALARM_INFO...
delete from SH_ALARM_INFO;
commit;
prompt Deleting SH_ALARM_BIND...
delete from SH_ALARM_BIND;
commit;







prompt Loading SH_ALARM_BIND...
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, SERVNUM, STATUS)
values ('10000000000601', '100000', '13900000000', 1);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, SERVNUM, STATUS)
values ('10000000000601', '100001', '13900000000', 1);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, SERVNUM, STATUS)
values ('10000000000601', '100002', '13900000000', 1);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, SERVNUM, STATUS)
values ('10000000000601', '100003', '13900000000', 1);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, SERVNUM, STATUS)
values ('10000000000601', '100004', '13900000000', 1);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, SERVNUM, STATUS)
values ('10000000000601', '100005', '13900000000', 1);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, SERVNUM, STATUS)
values ('10000000000601', '100006', '13900000000', 1);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, SERVNUM, STATUS)
values ('10000000000601', '100007', '13900000000', 1);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, SERVNUM, STATUS)
values ('10000000000601', '100008', '13900000000', 1);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, SERVNUM, STATUS)
values ('10000000000601', '100009', '13900000000', 1);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, SERVNUM, STATUS)
values ('10000000000601', '100010', '13900000000', 1);
commit;
prompt 11 records loaded
prompt Loading SH_ALARM_INFO...
prompt Table is empty
prompt Loading SH_ALARM_RULE...
insert into SH_ALARM_RULE (ALARMRULEID, EXCEPTIONTYPE, STARTNUM, ENDNUM, STATUS, NOTES, STATUSDATE, CREATEOPERATOR)
values ('10000000000500', 'ALL', '60', '120', 1, '未处理异常', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin');
insert into SH_ALARM_RULE (ALARMRULEID, EXCEPTIONTYPE, STARTNUM, ENDNUM, STATUS, NOTES, STATUSDATE, CREATEOPERATOR)
values ('10000000000501', '399', '30', '60', 1, '15分钟内未上传状态', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin');
commit;
prompt 2 records loaded
prompt Loading SH_CASHFILE_LOG...
prompt Table is empty
prompt Loading SH_CASHRECORD_LOG...
prompt Table is empty
prompt Loading SH_CHARGE_LOG...
prompt Table is empty
prompt Loading SH_CHECK_LOG...
prompt Table is empty
prompt Loading SH_CHOOSETEL_NUM...
prompt Table is empty
prompt Loading SH_COMMONPAY_LOG...
prompt Table is empty
prompt Loading SH_DICT_ITEM...
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('711', 'http://171.0.0.30:9080/fcgi-bin/SelfTerminal', 'fcgiUrl', 50, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('999', 'http://171.0.0.30:9080/fcgi-bin/SelfTerminal', 'fcgiUrl', 51, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('139spbizid', '+MAILMF+MAILBZ+MAILVIP', '139emailService', 1, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '判断139邮箱是否开通spbizid字段信息');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('139spid', '917270', '139emailService', 2, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '判断139邮箱是否开通spid字段信息');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('ALL', '未处理异常', 'TermStatus', 30, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('1', '发票缺纸、换新发票纸', 'dealdesp', 1, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '告警处理方法');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('2', '小票缺纸、换新热敏纸', 'dealdesp', 2, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '告警处理方法');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('0', '正常', 'TermStatus', 0, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('399', '15分钟内未上传状态', 'TermStatus', 21, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('1', '普通', 'alarmLevel', 1, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '告警级别最低级');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('2', '严重', 'alarmLevel', 2, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '告警级别中级');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('3', '紧急', 'alarmLevel', 3, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '告警级别最高级');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_REC_MAIN_FEE', '手机支付主账户充值', 'OperationType', 42, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryMonthFee', '月初扣费查询', 'OperationType', 7, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQrySubsFavourable', '已开通优惠查询', 'OperationType', 11, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQrySubsSP', '已定制SP查询', 'OperationType', 14, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHCancelSubsSP', '梦网业务查询退订', 'OperationType', 24, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryProductFee', '本机品牌资费', 'OperationType', 17, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryCurrentStatus', '当前状态查询', 'OperationType', 18, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQrySubsPackage', '套餐信息查询', 'OperationType', 19, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQrySubsRecHistory', '受理历史查询', 'OperationType', 20, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHSell', '自助售货', 'OperationType', 41, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('fireFox', 'fireFox', 'BrowserType', 1, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'linux下使用的浏览器');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('IE', 'IE', 'BrowserType', 0, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'windows操作系统下使用的浏览器');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHChooseTelNum', '自助选号', 'OperationType', 27, 0, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('314', '写本地日志失败', 'TermStatus', 14, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('313', '识币器钱箱被夹', 'TermStatus', 13, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('305', '设备初始化失败', 'TermStatus', 5, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('304', '端口故障', 'TermStatus', 4, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('303', '设备底层驱动程序未安装', 'TermStatus', 3, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('302', '打印机故障', 'TermStatus', 2, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('301', '票据打印缺纸', 'TermStatus', 1, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('315', '控件初始化失败', 'TermStatus', 15, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('312', '识币器入口被夹', 'TermStatus', 12, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('311', '识币器钱箱打开', 'TermStatus', 11, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('310', '识币器钱箱满', 'TermStatus', 10, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('309', '识币器故障', 'TermStatus', 9, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('308', '识币器初始化失败', 'TermStatus', 8, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('307', '加密键盘失败', 'TermStatus', 7, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('306', '发票打印缺纸', 'TermStatus', 6, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('319', '与银联通信故障', 'TermStatus', 19, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('318', '键盘故障', 'TermStatus', 18, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('317', '读卡器故障', 'TermStatus', 17, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('316', 'POS机故障', 'TermStatus', 16, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('320', '其它状态', 'TermStatus', 20, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHInvoiceData', '取发票数据', 'OperationType', 0, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHVerifyPWD', '验证服务密码', 'OperationType', 0, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHChkPhoneNo', '验证手机号', 'OperationType', 8, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryBilItem', '月账单查询', 'OperationType', 1, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryMuster', '月详单查询', 'OperationType', 4, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHGetValidateCode', '生成短信随机码', 'OperationType', 2, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHValidateResult', '验证短信随机码', 'OperationType', 3, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQrySubsServList', '现有功能查询', 'OperationType', 12, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHPukQuery', 'PUK码查询', 'OperationType', 15, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHGetUserRegion', '号码归属地查询', 'OperationType', 16, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryBalance', '账户余额查询', 'OperationType', 5, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryValidScore', '积分查询', 'OperationType', 13, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryPayHistory', '缴费历史查询', 'OperationType', 6, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHChgSubsPwd', '密码修改', 'OperationType', 26, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHChgBalanceUrge', '设置余额提醒值', 'OperationType', 23, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHCallTransfer', '呼叫转移', 'OperationType', 29, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHCallDisplay', '主叫显示', 'OperationType', 28, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHGPRSServ', '手机上网', 'OperationType', 35, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHIncomingAwake', '移动全时通', 'OperationType', 38, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHstopOpen', '停开机', 'OperationType', 25, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('PAYTYPE_CARD', '银联卡缴费', 'OperationType', 11, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('PAYTYPE_CASH', '现金缴费', 'OperationType', 9, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHAbate', '优惠打折信息', 'OperationType', 39, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHProvide', '供货管理', 'OperationType', 40, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHBillSend', '账单寄送', 'OperationType', 43, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('01', '投币日志', 'TermLogType', 0, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '硬件日志类型');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('02', '识币器工作日志', 'TermLogType', 1, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '硬件日志类型');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('03', '票据打印机工作日志', 'TermLogType', 2, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '硬件日志类型');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('04', '发票打印机工作日志', 'TermLogType', 3, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '硬件日志类型');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('05', '密码键盘工作日志', 'TermLogType', 4, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '硬件日志类型');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('06', '身份证阅读器工作日志', 'TermLogType', 5, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '硬件日志类型');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('07', '管理控件工作日志', 'TermLogType', 6, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '硬件日志类型');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('08', '发卡/写卡器工作日志', 'TermLogType', 7, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '硬件日志类型');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('09', '手机钱包控件工作日志', 'TermLogType', 8, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '硬件日志类型');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('10', '售货机工作日志', 'TermLogType', 9, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '硬件日志类型');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('99', '其它日志', 'TermLogType', 10, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '硬件日志类型');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('0', '成功', 'checkResult', 60, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '对账结果');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('1', '失败', 'checkResult', 61, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '对账结果');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('2', '需补缴', 'checkResult', 62, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '对账结果');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('3', '已补缴', 'checkResult', 63, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '对账结果');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHAcceptPriv', '优惠受理', 'OperationType', 44, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('PRIV_PAYTYPE_CASH', '优惠现金缴费', 'OperationType', 45, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_ProdChange', '套餐转换', 'OperationType', 46, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('phone', '交话费', 'RecType', 50, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '缴费业务类型');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('favourable', '交优惠费', 'RecType', 51, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '缴费业务类型');
commit;
prompt 90 records loaded
prompt Loading SH_GROUP_ADV...
prompt Table is empty
prompt Loading SH_GROUP_BUDDY...
prompt Table is empty
prompt Loading SH_GROUP_INFO...
insert into SH_GROUP_INFO (REGION, TERMGROUPID, TERMGROUPNAME, STATUS, STATUSDATE, ORGID, NOTES)
values (999, '10000000000300', '省级', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'HB', '省级，不可删');
insert into SH_GROUP_INFO (REGION, TERMGROUPID, TERMGROUPNAME, STATUS, STATUSDATE, ORGID, NOTES)
values (711, '10000000000301', '鄂州通用', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'HB.EZ', '不可删');
insert into SH_GROUP_INFO (REGION, TERMGROUPID, TERMGROUPNAME, STATUS, STATUSDATE, ORGID, NOTES)
values (711, '10000000000302', '鄂州查打机', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'HB.EZ', '提供查询打印功能');
commit;
prompt 3 records loaded
prompt Loading SH_GROUP_MENU...
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recColorfulRing', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recFlyMessage', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recMessageReturn', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recMusicOrdinarilyMem', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recMusicAdvancedMem', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recGPRS', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recMsgTransfer', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recBookManager', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recIncomingAwake', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'favourableAbate', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'bursePay', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'freePay', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'selfSell', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'sellGoodsBursePay', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'sellGoodsScorePay', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'commonpay', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'electricFee', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'payment', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'mobilePayment', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryArrear', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryMonthBill', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'waterFee', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'coalGasFee', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'catvFee', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'selfRecTaste', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'chooseTel', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryArrearHub', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'cardPay', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recIdCardBook', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'privcardcharge', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryChargeGuide', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryBillAnalysis', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recPrivAccept', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'privcashcharge', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recProdChange', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryMmsMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryInfoFeeMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryVpmnMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryPimMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryFlashMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'root', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryBill', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryBillItem', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryAllMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryGsmMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryImsgMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qrySmsMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryWlanMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryGprsMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryBalance', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryFeeHistory', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryMonthFee', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'charge', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'feeCharge', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'cashcharge', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'cardcharge', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'mainAccoutCharge', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'cashchargemain', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryService', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryServiceQry', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryFavourable', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryScore', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryMonternet', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryPukQry', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'telNumInput', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryProductFee', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryCurrentStatus', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qrySerPri', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryServiceHistory', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'baseService', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recPhoneFeeAwake', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recPasswordReset', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recChgBalanceUrge', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recQrySPinfo', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recStopOpen', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recChangePwd', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'rec', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'install', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recCallDisplay', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'recCallTransfer', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryG3Muster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000301', 'qryGameMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'root', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryBill', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryBillItem', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryBillItemByCust', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryBillItemByAcct', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryBillAnalysis', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryMuster', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryAllMuster', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryGsmMuster', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qrySmsMuster', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryImsgMuster', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryGprsMuster', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryWlanMuster', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryMmsMuster', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryInfoFeeMuster', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryVpmnMuster', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryPimMuster', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 100 records committed...
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryFlashMuster', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryG3Muster', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryGameMuster', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryBalance', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryFeeHistory', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryMonthFee', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryArrearHisHub', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'chooseTel', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'rec', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'recChgBalanceUrge', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'recPhoneFeeAwake', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryService', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryServiceQry', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryFavourable', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryScore', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryMonternet', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryPukQry', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'telNumInput', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryProductFee', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryCurrentStatus', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qrySerPri', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qrySerPersion', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryServiceHistory', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000302', 'qryChargeGuide', 1, to_date('21-07-2011 16:43:57', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 124 records loaded
prompt Loading SH_GROUP_SCREEN...
insert into SH_GROUP_SCREEN (TERMGRPID, SCLIST, CREATEDATE, CREATEOPER, STATUS, STATUSDATE, RELEASEOPER)
values ('10000000000301', '10000000000400,10000000000401', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin');
insert into SH_GROUP_SCREEN (TERMGRPID, SCLIST, CREATEDATE, CREATEOPER, STATUS, STATUSDATE, RELEASEOPER)
values ('10000000000302', '10000000000400', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin');
commit;
prompt 2 records loaded
prompt Loading SH_INFO_ABATE...
prompt Table is empty
prompt Loading SH_INFO_BUDDY...
prompt Table is empty
prompt Loading SH_INFO_MANUFACTURER...
insert into SH_INFO_MANUFACTURER (MANUFACTURERID, MANUFACTURERNAME, MANUFACTURERADDRESS, PHONENUMBER, OPERID, CREATEDATE, RECDATE)
values ('10000000000100', '开发测试', '开发测试地址', '13900000000', 'admin', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_INFO_MANUFACTURER (MANUFACTURERID, MANUFACTURERNAME, MANUFACTURERADDRESS, PHONENUMBER, OPERID, CREATEDATE, RECDATE)
values ('10000000000101', '凯信达科技', '凯信达科技地址', '13900000000', 'admin', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_INFO_MANUFACTURER (MANUFACTURERID, MANUFACTURERNAME, MANUFACTURERADDRESS, PHONENUMBER, OPERID, CREATEDATE, RECDATE)
values ('10000000000102', '印天海洛科技', '印天海洛科技地址', '13900000000', 'admin', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_INFO_MANUFACTURER (MANUFACTURERID, MANUFACTURERNAME, MANUFACTURERADDRESS, PHONENUMBER, OPERID, CREATEDATE, RECDATE)
values ('10000000000103', '政通科技', '政通科技地址', '13900000000', 'admin', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_INFO_MANUFACTURER (MANUFACTURERID, MANUFACTURERNAME, MANUFACTURERADDRESS, PHONENUMBER, OPERID, CREATEDATE, RECDATE)
values ('10000000000104', '西安中兴', '西安中兴地址', '13900000000', 'admin', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_INFO_MANUFACTURER (MANUFACTURERID, MANUFACTURERNAME, MANUFACTURERADDRESS, PHONENUMBER, OPERID, CREATEDATE, RECDATE)
values ('10000000000105', '西安普声', '西安普声地址', '13900000000', 'admin', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 6 records loaded
prompt Loading SH_INFO_MEDIARES...
insert into SH_INFO_MEDIARES (REGION, RESID, RESNAME, RESLOCALFILENAME, RESFORMAT, RESLINK, RESTYPE, STATUS, STATUSDATE, CREATEDATE, CREATEOPER, RESPLAYTIME, ORGID)
values (711, '10000000000400', 'SC20000101010000.wmv', 'SC20000101010000.wmv', 'wmv', '/selfsvcmgr', 'sc', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'HB.EZ');
insert into SH_INFO_MEDIARES (REGION, RESID, RESNAME, RESLOCALFILENAME, RESFORMAT, RESLINK, RESTYPE, STATUS, STATUSDATE, CREATEDATE, CREATEOPER, RESPLAYTIME, ORGID)
values (711, '10000000000401', 'SC20000101010001.wmv', 'SC20000101010001.wmv', 'wmv', '/selfsvcmgr', 'sc', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'HB.EZ');
commit;
prompt 2 records loaded
prompt Loading SH_INFO_PLUGIN...
insert into SH_INFO_PLUGIN (PROVIDERID, BROWSERTYPE, SERVIPADDR, FTPUSERNAME, FTPPWD, PRINTERVER, PRTFTPADDR, PRTPLUGINFLAG, INVPRINTERVER, INVPRTFTPADDR, INVPRTPLUGINFLAG, KEYBOARDVER, KEYBRDFTPADDR, KEYBRDPLUGINFLAG, CASHVER, CASHFTPADDR, CASHPLUGINFLAG, CARDVER, CARDFTPADDR, CARDPLUGINFLAG, MANAGERVER, MGRFTPADDR, MGRPLUGINFLAG, SOCKETPORT, MOVECARDVER, MOVECARDADDR, MOVECARDFLAG, WRITECARDVER, WRITECARDADDR, WRITECARDFLAG, IDCARDVER, IDCARDADDR, IDCARDFLAG, UNIONVER, UNIONFTPADDR, UNIONPLUGINFLAG, PURSEVER, PURSEFTPADDR, PURSEPLUGINFLAG, SELLGOODSVER, SELLGOODSFTPADDR, SELLGOODSPLUGINFLAG)
values ('10000000000100', 'IE', '171.0.0.4', 'weblogic', 'weblogic', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:E7BF2F65-3E4E-4712-90FE-5A7F2B7379BB', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:9D1C4FCA-0AD5-4E58-99E9-C367F5950269', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:A7DB1A4E-B1EB-4C45-9B5F-4754E4A0C718', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:1BF62D9B-D1E1-49CD-BD33-6017C9EA07B3', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:A8064A9B-C5F9-4C5F-9F7D-EC397248D3C8', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:5B1A469E-0F9C-4DE4-90EA-31D205E2CB27', '6000', null, null, null, null, null, null, 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:6FEAAE87-5BB3-4AB4-9F60-2101D14F4FFF', 'hwsd1-1-1', '/plugins/zhengde/xp', 'clsid:7AE7497B-CAD8-4E66-A58B-DDE9BCAF6B61', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:8E35C67E-7AAE-4404-A91C-6F0719915845', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:E7FA3F16-3F80-4880-AE17-634021B1DB8E');
insert into SH_INFO_PLUGIN (PROVIDERID, BROWSERTYPE, SERVIPADDR, FTPUSERNAME, FTPPWD, PRINTERVER, PRTFTPADDR, PRTPLUGINFLAG, INVPRINTERVER, INVPRTFTPADDR, INVPRTPLUGINFLAG, KEYBOARDVER, KEYBRDFTPADDR, KEYBRDPLUGINFLAG, CASHVER, CASHFTPADDR, CASHPLUGINFLAG, CARDVER, CARDFTPADDR, CARDPLUGINFLAG, MANAGERVER, MGRFTPADDR, MGRPLUGINFLAG, SOCKETPORT, MOVECARDVER, MOVECARDADDR, MOVECARDFLAG, WRITECARDVER, WRITECARDADDR, WRITECARDFLAG, IDCARDVER, IDCARDADDR, IDCARDFLAG, UNIONVER, UNIONFTPADDR, UNIONPLUGINFLAG, PURSEVER, PURSEFTPADDR, PURSEPLUGINFLAG, SELLGOODSVER, SELLGOODSFTPADDR, SELLGOODSPLUGINFLAG)
values ('10000000000101', 'IE', '171.0.0.4', 'weblogic', 'weblogic', '1.0', '/plugins/kxd/xp', 'clsid:72DCAE44-1514-454E-BF52-30C24ED2F02E', '1.0', '/plugins/kxd/xp', 'clsid:F8A65458-43A9-46CD-910A-85FA19B5C1A1', '1.0', '/plugins/kxd/xp', 'clsid:E7F10CAE-07A9-47AC-98C3-57BE966C6FFC', '1.0', '/plugins/kxd/xp', 'clsid:62B9DE93-7C8E-44C5-93CD-081F542AB584', '1.0', '/plugins/kxd/xp', 'clsid:1C57436F-81CB-41A5-A077-97A2ABED8679', '1.0', '/plugins/kxd/xp', 'clsid:8B3AA752-49E0-40D9-AF46-CF3F939BA87B', '6000', null, null, null, null, null, null, '1.0', '/plugins/kxd/xp', 'CLSID:6FEAAE87-5BB3-4AB4-9F60-2101D14F4FFF', '1.0', '/plugins/kxd/xp', 'clsid:7AE7497B-CAD8-4E66-A58B-DDE9BCAF6B61', '1.0', '/plugins/kxd/xp', 'CLSID:8E35C67E-7AAE-4404-A91C-6F0719915845', '1.0', '/plugins/kxd/xp', 'CLSID:E7FA3F16-3F80-4880-AE17-634021B1DB8E');
insert into SH_INFO_PLUGIN (PROVIDERID, BROWSERTYPE, SERVIPADDR, FTPUSERNAME, FTPPWD, PRINTERVER, PRTFTPADDR, PRTPLUGINFLAG, INVPRINTERVER, INVPRTFTPADDR, INVPRTPLUGINFLAG, KEYBOARDVER, KEYBRDFTPADDR, KEYBRDPLUGINFLAG, CASHVER, CASHFTPADDR, CASHPLUGINFLAG, CARDVER, CARDFTPADDR, CARDPLUGINFLAG, MANAGERVER, MGRFTPADDR, MGRPLUGINFLAG, SOCKETPORT, MOVECARDVER, MOVECARDADDR, MOVECARDFLAG, WRITECARDVER, WRITECARDADDR, WRITECARDFLAG, IDCARDVER, IDCARDADDR, IDCARDFLAG, UNIONVER, UNIONFTPADDR, UNIONPLUGINFLAG, PURSEVER, PURSEFTPADDR, PURSEPLUGINFLAG, SELLGOODSVER, SELLGOODSFTPADDR, SELLGOODSPLUGINFLAG)
values ('10000000000102', 'IE', '171.0.0.4', 'weblogic', 'weblogic', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:E7BF2F65-3E4E-4712-90FE-5A7F2B7379BB', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:9D1C4FCA-0AD5-4E58-99E9-C367F5950269', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:A7DB1A4E-B1EB-4C45-9B5F-4754E4A0C718', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:1BF62D9B-D1E1-49CD-BD33-6017C9EA07B3', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:A8064A9B-C5F9-4C5F-9F7D-EC397248D3C8', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:5B1A469E-0F9C-4DE4-90EA-31D205E2CB27', '6000', null, null, null, null, null, null, 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:6FEAAE87-5BB3-4AB4-9F60-2101D14F4FFF', 'hwsd1-1-1', '/plugins/zhengde/xp', 'clsid:7AE7497B-CAD8-4E66-A58B-DDE9BCAF6B61', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:8E35C67E-7AAE-4404-A91C-6F0719915845', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:E7FA3F16-3F80-4880-AE17-634021B1DB8E');
insert into SH_INFO_PLUGIN (PROVIDERID, BROWSERTYPE, SERVIPADDR, FTPUSERNAME, FTPPWD, PRINTERVER, PRTFTPADDR, PRTPLUGINFLAG, INVPRINTERVER, INVPRTFTPADDR, INVPRTPLUGINFLAG, KEYBOARDVER, KEYBRDFTPADDR, KEYBRDPLUGINFLAG, CASHVER, CASHFTPADDR, CASHPLUGINFLAG, CARDVER, CARDFTPADDR, CARDPLUGINFLAG, MANAGERVER, MGRFTPADDR, MGRPLUGINFLAG, SOCKETPORT, MOVECARDVER, MOVECARDADDR, MOVECARDFLAG, WRITECARDVER, WRITECARDADDR, WRITECARDFLAG, IDCARDVER, IDCARDADDR, IDCARDFLAG, UNIONVER, UNIONFTPADDR, UNIONPLUGINFLAG, PURSEVER, PURSEFTPADDR, PURSEPLUGINFLAG, SELLGOODSVER, SELLGOODSFTPADDR, SELLGOODSPLUGINFLAG)
values ('10000000000103', 'IE', '171.0.0.4', 'weblogic', 'weblogic', '1.0', '/plugins/zt/xp', 'clsid:D6661897-B33E-42C6-BFF5-427E0CCAD7D2', '1.0', '/plugins/zt/xp', 'clsid:3AE9FC06-CE84-4D65-92AB-4535333F4035', '1.0', '/plugins/zt/xp', 'clsid:E7F10CAE-07A9-47AC-98C3-57BE966C6FFC', '1.0', '/plugins/zt/xp', 'clsid:8BE7B946-7896-4F01-A749-43E133CB99CD', '1.0', '/plugins/zt/xp', 'clsid:1C57436F-81CB-41A5-A077-97A2ABED8679', '1.0', '/plugins/zt/xp', 'clsid:845DB60D-C753-4DC7-BCE7-D6B066112192', '6000', null, null, null, null, null, null, '1.0', '/plugins/zt/xp', 'CLSID:6FEAAE87-5BB3-4AB4-9F60-2101D14F4FFF', '1.0', '/plugins/zt/xp', 'clsid:7AE7497B-CAD8-4E66-A58B-DDE9BCAF6B61', '1.0', '/plugins/zt/xp', 'CLSID:8E35C67E-7AAE-4404-A91C-6F0719915845', '1.0', '/plugins/zt/xp', 'CLSID:E7FA3F16-3F80-4880-AE17-634021B1DB8E');
insert into SH_INFO_PLUGIN (PROVIDERID, BROWSERTYPE, SERVIPADDR, FTPUSERNAME, FTPPWD, PRINTERVER, PRTFTPADDR, PRTPLUGINFLAG, INVPRINTERVER, INVPRTFTPADDR, INVPRTPLUGINFLAG, KEYBOARDVER, KEYBRDFTPADDR, KEYBRDPLUGINFLAG, CASHVER, CASHFTPADDR, CASHPLUGINFLAG, CARDVER, CARDFTPADDR, CARDPLUGINFLAG, MANAGERVER, MGRFTPADDR, MGRPLUGINFLAG, SOCKETPORT, MOVECARDVER, MOVECARDADDR, MOVECARDFLAG, WRITECARDVER, WRITECARDADDR, WRITECARDFLAG, IDCARDVER, IDCARDADDR, IDCARDFLAG, UNIONVER, UNIONFTPADDR, UNIONPLUGINFLAG, PURSEVER, PURSEFTPADDR, PURSEPLUGINFLAG, SELLGOODSVER, SELLGOODSFTPADDR, SELLGOODSPLUGINFLAG)
values ('10000000000104', 'IE', '171.0.0.4', 'weblogic', 'weblogic', '1.0', '/plugins/zx/xp', 'clsid:D6661897-B33E-42C6-BFF5-427E0CCAD7D2', '1.0', '/plugins/zx/xp', 'clsid:3AE9FC06-CE84-4D65-92AB-4535333F4035', '1.0', '/plugins/zx/xp', 'clsid:E7F10CAE-07A9-47AC-98C3-57BE966C6FFC', '1.0', '/plugins/zx/xp', 'clsid:8BE7B946-7896-4F01-A749-43E133CB99CD', '1.0', '/plugins/zx/xp', 'clsid:1C57436F-81CB-41A5-A077-97A2ABED8679', '1.0', '/plugins/zx/xp', 'clsid:845DB60D-C753-4DC7-BCE7-D6B066112192', '6000', null, null, null, null, null, null, '1.0', '/plugins/zt/xp', 'CLSID:6FEAAE87-5BB3-4AB4-9F60-2101D14F4FFF', '1.0', '/plugins/zx/xp', 'clsid:7AE7497B-CAD8-4E66-A58B-DDE9BCAF6B61', '1.0', '/plugins/zx/xp', 'CLSID:8E35C67E-7AAE-4404-A91C-6F0719915845', '1.0', '/plugins/zx/xp', 'CLSID:E7FA3F16-3F80-4880-AE17-634021B1DB8E');
commit;
prompt 5 records loaded

prompt Loading SH_LOG_ABATE...
prompt Table is empty
prompt Loading SH_LOG_AUDIT...
prompt Table is empty
prompt Loading SH_LOG_BUSINESS...
prompt Table is empty
prompt Loading SH_LOG_CASHRETURN...
prompt Table is empty
prompt Loading SH_LOG_CHECKBILL...
prompt Table is empty
prompt Loading SH_LOG_LOGINERRNUM...
prompt Table is empty
prompt Loading SH_LOG_MOBILEPAY...
prompt Table is empty
prompt Loading SH_LOG_MOBILEWALLET...
prompt Table is empty
prompt Loading SH_LOG_PRINTBILLNUM...
prompt Table is empty
prompt Loading SH_LOG_PRINTINVOICE...
prompt Table is empty
prompt Loading SH_LOG_PRIV...
prompt Table is empty
prompt Loading SH_LOG_PRODCHANGE...
prompt Table is empty
prompt Loading SH_LOG_TERMSTATUS...
prompt Table is empty
prompt Loading SH_LOG_TERMUP...
prompt Table is empty
prompt Loading SH_MENU_ITEM...
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qrySmsMuster', '短信详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=2', '提供5+1短信清单查询', 2, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryWlanMuster', 'WLAN详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=5', '提供5+1WLAN话单查询', 5, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryServiceQry', '现有功能查询', 'qryService', 'SelfQryServAction.do?actionCase=qryService&forMenuid=qryServiceQry', '为您提供已有服务查询', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, 'images/bg_bill_02-trans.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryFavourable', '品牌资费及已开通功能', 'qryService', 'serviceinfo/qryFavourable.action', '查询本机资费及已开通业务', 2, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryScore', '积分查询', 'qryService', 'scoreQry/qryScore.action', '查询截至昨日的累计积分', 3, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryMonternet', '已定制sp查询', 'qryService', 'SelfQryServAction.do?actionCase=monternet&forMenuid=qryMonternet', '为您提供已订购梦网业务查询', 4, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, 'images/bg_bill_02-trans.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryPukQry', 'PUK码查询', 'qryService', 'serviceinfo/qryPukCode.action', '查询手机号码的PUK码信息', 5, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('telNumInput', '号码归属地查询', 'qryService', 'serviceinfo/telNumInput.action', '查询指定号码所属省份及地市', 6, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryProductFee', '本机品牌资费', 'qryService', 'SelfQryActon.do?actionCase=qryProductFee&forMenuid=qryProductFee', '提供本品牌资费', 7, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, 'images/bg_bill_02-trans.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recMessageReturn', '短信回执', 'rec', 'SelfRecAction.do?actionCase=commonServPage&sType=SHMessageReturn&isInput=0&forMenuid=recMessageReturn', '办理短信回执申请和取消业务', 6, to_date('18-05-2009', 'dd-mm-yyyy'), 0, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recIncomingAwake', '移动全时通', 'rec', 'privilege/privilege.action?nCode=C06', '办理移动全时通开通和退订业务', 12, to_date('18-05-2009', 'dd-mm-yyyy'), 0, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '移动全时通是指手机关机或不在服务区时，它能将在这期间的所有来电转移到移动全时通平台，当在打开手机或者手机恢复正常后，将会收到关机或者不在服务区期间漏接来话的短信息。基本资费：3元/月<br/>全时通业务开通、取消均立即生效。', null, 'images/can/service_icon14.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recCallDisplay', '主叫显示', 'rec', 'privilege/privilege.action?nCode=H01', '办理主叫显示开通和退订业务', 2, to_date('18-05-2009', 'dd-mm-yyyy'), 0, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '让您的手机显示来电号码，已接或未接的来电号码会自动存储在手机上。<br/>标准资费：后付费客户功能费3元/月；预付费客户功能费0.1元/天。神州行经典卡暂时不收取来电显示费。', null, 'images/can/service_icon13.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recChangePwd', '修改密码', 'rec', 'baseService/changePasswordPage.action', '修改密码业务受理', 6, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/can/service_icon6.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryAllMuster', '全部详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=0&isQueryAll=true', '提供5+1全部清单查询', 0, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('bursePay', '手机钱包支付', 'favourableAbate', 'selfAbateAction.do?actionCase=readBurse&CurMenuid=favourableAbate', '为您提供手机钱包支付服务', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recColorfulRing', '彩铃', 'rec', 'SelfRecAction.do?actionCase=commonServPage&sType=SHColorfulRing&isInput=0&forMenuid=recColorfulRing', '办理彩铃申请和取消业务', 4, to_date('18-05-2009', 'dd-mm-yyyy'), 0, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recFlyMessage', '飞信', 'rec', 'SelfRecAction.do?actionCase=commonServPage&sType=SHFlyMessage&isInput=0&forMenuid=recFlyMessage', '办理飞信申请和取消业务', 5, to_date('18-05-2009', 'dd-mm-yyyy'), 0, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recMusicOrdinarilyMem', '音乐普通会员', 'rec', 'SelfRecAction.do?actionCase=commonServPage&sType=SHMusicOrdinarilyMem&isInput=0&forMenuid=recMusicOrdinarilyMem', '办理音乐俱乐部普通会员申请和取消业务', 7, to_date('18-05-2009', 'dd-mm-yyyy'), 0, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recMusicAdvancedMem', '音乐高级会员', 'rec', 'SelfRecAction.do?actionCase=commonServPage&sType=SHMusicAdvancedMem&isInput=0&forMenuid=recMusicAdvancedMem', '办理音乐俱乐部高级会员申请和取消业务', 8, to_date('18-05-2009', 'dd-mm-yyyy'), 0, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recPhoneFeeAwake', '话费提醒', 'rec', 'SelfRecAction.do?actionCase=commonServPage&sType=SHPhoneFeeAwake&isInput=0&forMenuid=recPhoneFeeAwake', '提供话费提醒', 1, to_date('21-05-2010', 'dd-mm-yyyy'), 0, to_date('21-05-2010', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recMsgTransfer', '短信呼转', 'rec', 'SelfRecAction.do?actionCase=commonServPage&sType=SHMsgTransfer&isInput=1&forMenuid=recMsgTransfer', '办理短信呼转申请和取消业务', 10, to_date('22-05-2009', 'dd-mm-yyyy'), 0, to_date('22-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recBookManager', '号簿管家', 'rec', 'SelfRecAction.do?actionCase=commonServPage&sType=SHBookManager&isInput=1&forMenuid=recBookManager', '办理号簿管家申请和取消业务', 11, to_date('22-05-2009', 'dd-mm-yyyy'), 0, to_date('22-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recChgBalanceUrge', '余额提醒', 'rec', 'baseService/chgBalanceUrgePage.action', '设置余额提醒值业务受理', 1, to_date('18-05-2009', 'dd-mm-yyyy'), 0, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/can/service_icon15.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recStopOpen', '停开机', 'rec', 'baseService/commonServPage.action?sType=SHstopOpen&isInput=0', '办理停开机申请和取消业务', 5, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/can/service_icon12.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recGPRS', '手机上网', 'rec', 'privilege/privilege.action?nCode=GPRS10', '办理GPRS开通和退订业务', 9, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, 'GPRS是一种无线上网方式，被称为第2.5代移动通信技术，具有永远在线、按流量计费、话音数据自由切换等特点。<br/>' || chr(10) || 'GPRS功能与GPRS套餐的关系：GPRS功能一般在您的号码开户时默认开通，GPRS套餐需要申请开通。开通GPRS套餐时，系统将自动为您开通GPRS功能，但取消GPRS套餐时不会取消GPRS功能。取消GPRS功能后，已开通的GPRS套餐也将一并失效，再次开通GPRS功能后需要重新选择开通GPRS套餐，否则按基本资费0.01元/KB计费。<br/>基本资费：开通了GPRS功能而未开通GPRS套餐的客户，基本资费为0.01元/KB。如果您每月使用GPRS流量较多，建议开通适当档次的GPRS套餐。', null, 'images/can/service_icon2.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recQrySPinfo', '业务查询退订', 'rec', 'baseService/querySP.action?operType=Cancel', '办理梦网业务退订业务', 4, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/can/service_icon16.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryBill', '账单详单查询', 'root', 'feeservice/feeServiceFunc.action', '可查询最近六个月内的账单详单信息', 2, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/index_pic5.png', 999, 'ALL', '0000', 1, null, 'C', 'images/nav_5_logo.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryService', '服务信息查询', 'root', 'serviceinfo/serviceInfoFunc.action', '为您提供服务类信息的查询功能', 5, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/index_bt6.png', 999, 'ALL', '0000', 1, null, 'BR', 'images/nav_9_logo.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('rec', '业务办理', 'root', 'reception/receptionFunc.action', '可办理多种语音套餐、增值业务的开通和变更', 4, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/index_pic4.png', 999, 'ALL', '0000', 1, null, 'C', 'images/nav_4_logo.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('baseService', '基础服务办理', 'root', 'baseService/baseServiceFunc.action', '为您提供移动基础性业务的办理', 6, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/index_bt2.png', 999, 'ALL', '0000', 1, null, null, 'images/nav_6_logo.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('favourableAbate', '优惠打折信息', 'root', 'selfAbateAction.do?actionCase=selectBuddy&forMenuid=favourableAbate', '为您提供优惠打折服务', 6, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/s_6.png', 999, 'ALL', '0000', 1, null, null, 'images/s_6.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryBillItem', '月账单查询', 'qryBill', 'monthFee/currMonthFee.action', '可查询近6个月(含当前月)的明细账单' || chr(10) || '', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '综合账单查询功能，可查询本月和前五个月的话费。', null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryMuster', '月详单查询', 'qryBill', 'feeservice/detailedRecords.action', '可查询近6个月(含当前月)的详单记录' || chr(10) || '', 10, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1100', 2, '可查询当前月和前5个月的详单，包括通话清单、短信清单、梦网短信、GPRS清单、WLAN清单。(1)您可以选择详单的种类查询。<br/>(2)您可以按月份查询。<br/>(3)分种类查询详单时，不提供打印功能。只有选择查询“全部详单”时，才提供打印功能。', null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryBalance', '账户余额查询', 'qryBill', 'hubfeeservice/qryAccBalance.action', '余额查询', 15, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0010', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryFeeHistory', '缴费历史查询', 'qryBill', 'feeservice/chargeHistory.action', '可查询近6个月(含当前月)的缴费记录', 20, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '为您提供6个月内的缴费历史记录，包括缴费时间，缴费方式，缴费金额。', null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('privcardcharge', '银行卡缴费', 'recPrivAccept', 'privAccept/privcardCharge.action', '为您提供银行卡缴费服务', 2, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/can/card_1.png', 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('privcashcharge', '现金缴费', 'recPrivAccept', 'privAccept/privcashCharge.action', '为您提供现金缴费服务', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/can/card_3.png', 999, 'ALL', '0000', 3, null, null, 'images/can/card_3n.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recDsmpOrder', '全网梦网订购', 'rec', 'baseService/querySP.action?operType=Order', '办理全网梦网业务的订购业务', 16, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/qwmwdg.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qrySerPri', '套餐信息查询', 'qryService', 'comboInfo/selectTaoCanMonth.action', '当前在用的套餐使用情况', 9, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryServiceHistory', '受理历史查询', 'qryService', 'serviceinfo/qryServiceHistoryInput.action', '查询用户办理过的所有业务的历史记录', 10, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('chooseTel', '自助选号', 'root', 'chooseTel/selectRegion.action?bz=1', '为您提供自助选号服务', 3, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/index_pic3.png', 999, 'ALL', '0000', 1, null, 'C', 'images/nav_3_logo.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('root', '欢迎使用自助终端', '-1', 'login/index.action', '自助终端根菜单', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 0, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryGprsMuster', 'GPRS详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=4', '提供5+1GPRS清单查询', 4, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('sellGoodsBursePay', '手机钱包支付', 'selfSell', 'selfSellGoodsAction.do?actionCase=readBurse&CurMenuid=selfSell', '为您提供手机钱包支付服务', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('freePay', '免费获取', 'favourableAbate', 'selfAbateAction.do?actionCase=abateLogin&forMenuid=freePay', '手机号登录免费获取优惠卷', 2, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('sellGoodsScorePay', '积分兑换', 'selfSell', 'selfSellGoodsAction.do?actionCase=scoreLogin&CurMenuid=selfSell', '为您提供积分兑换服务', 2, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('cardcharge', '银行卡缴费', 'feeCharge', 'charge/cardCharge.action', '为您提供银行卡缴费服务', 2, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/can/card_1.png', 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('cashcharge', '现金缴费', 'feeCharge', 'charge/cashCharge.action', '为您提供现金缴费服务', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/can/card_3.png', 999, 'ALL', '0000', 3, null, null, 'images/can/card_3n.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('cashchargemain', '现金充值', 'mainAccoutCharge', 'mpaycharge/cashCharge.action', '为您提供现金充值服务', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 4, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('feeCharge', '话费充值', 'charge', 'charge/feeCharge.action', '为您提供话费充值服务', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('mainAccoutCharge', '主账户充值', 'root', 'mpaycharge/cmpay.action', '为您提供手机支付主账户充值服务', 10, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/index_bt8.png', 999, 'ALL', '0000', 1, null, 'BR', 'images/nav_10_logo.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('feeCharge', '充值交费', 'root', 'charge/feeCharge.action', '可使用银行卡和现金为手机充值交费', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/index_pic1.png', 999, 'ALL', '0000', 1, null, 'C', 'images/nav_1_logo.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('electricFee', '电费', 'commonpay', 'commonPayAction.do?actionCase=ElectricFeeUnit&forMenuid=commonpay&CurMenuid=electricFee', '为您提供电费缴费', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('payment', '缴费', 'electricFee', 'commonPayAction.do?actionCase=selectPaymentType&forMenuid=electricFee&CurMenuid=payment', '为您提供公共事业缴费', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryMonthBill', '查询月账单', 'electricFee', 'commonPayAction.do?actionCase=qryBill&forMenuid=electricFee&CurMenuid=qryMonthBill', '为您提供月账单查询', 3, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryArrear', '查询欠费', 'electricFee', 'commonPayAction.do?actionCase=qryArrearFee&forMenuid=electricFee&CurMenuid=qryArrear', '为您提供欠费查询服务', 2, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('mobilePayment', '手机支付', 'payment', 'commonPayAction.do?actionCase=paymentLogin&CurMenuid=mobilePayment&forMenuid=payment&fromMenuid=electricFee&payType=0', '为您提供支付方式的选择', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('commonpay', '公共事业缴费', 'root', 'SubsLoginAction.do?actionCase=loginIndex&forMenuid=commonpay     ', '为您提供公共事业的缴费', 8, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/s_8.png', 999, 'ALL', '0000', 1, null, null, 'images/s_8.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('selfRecTaste', '移动业务体验', 'root', 'jsp/SelfMedia/SelfRecTaste/SelfRecTaste.jsp', '为您提供移动业务体验服务', 7, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/index_bt7.png', 999, 'ALL', '0000', 1, null, 'BR', 'images/nav_8_logo.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('waterFee', '水费', 'commonpay', 'commonPayAction.do?actionCase=WaterFeeUnit&forMenuid=commonpay&CurMenuid=waterFee', '为您提供水费缴费', 2, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('coalGasFee', '煤气费', 'commonpay', 'commonPayAction.do?actionCase=CoalGasFeeUnit&forMenuid=commonpay&CurMenuid=coalGasFee', '为您提供煤气费缴费', 3, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('catvFee', '有线电视费', 'commonpay', 'commonPayAction.do?actionCase=CATVFeeUnit&forMenuid=commonpay&CurMenuid=catvFee', '为您提供有线电视费缴费', 4, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('selfSell', '自助售货', 'root', 'selfSellGoodsAction.do?actionCase=selectGoods&forMenuid=selfSell', '为您提供自助售货服务', 7, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/s_7.png', 999, 'ALL', '0000', 1, null, null, 'images/s_7.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recCallTransfer', '呼叫转移', 'rec', 'reception/transferType.action?recType=SHCallTransfer', '办理呼叫转移开通和退订业务', 3, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '"呼叫转移"可将来电转移到预先设定的固定电话、手机等号码上，从而避免来电信息丢失。 <br/>在设置呼叫转移号码时，国内号码规则为"区号(带前缀"0")+固定电话号码"或手机号码,前面不带86；(如:053188161234或13905310000,请不要在区号和电话号码间加"-","+"等)。国际号码规则为"00+国家或地区代码+区号(去掉前缀"0")+电话号码"或"00+国家或地区代码+手机号码”,前面必须有"00"。（目前国际及港澳台呼转暂时关闭）<br/>呼转资费：若客户在归属地将号码转移至归属地移动号码、小灵通、固定电话收取0.2元/分基本呼转费。若客户在归属地将号码转移至归属地联通号码收取0.3元/分基本呼转费。对于漫游时或呼转至外地号码资费，详情请询10086。', null, 'images/can/service_icon5.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recPasswordReset', '密码重置', 'rec', 'baseService/sendRandomPwd.action', '提供密码重置功能', 7, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0010', 2, '如果您没有设置过服务密码或忘记服务密码，可以通过此功能重置服务密码。<br/>服务密码为6位数字。为了保障您个人信息的安全，请不要设置为有序数字、相同数字等。<br/>身份证号为15位或18位数字，号码中带有X的用户不能通过自助终端重置服务密码。', null, 'images/can/service_icon17.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryArrearHisHub', '欠费历史查询', 'qryBill', 'hubfeeservice/qryArrearHub.action', '查询用户欠费历史记录', 35, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recBillSend', '账单寄送', 'rec', 'baseService/billSendPage.action', '办理账单寄送服务', 13, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '可通过短息，彩信和邮箱的方法寄送账单', null, 'images/can/service_icon18.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('cardPay', '充值卡缴费', 'root', 'freecharge/cardPay.action', '为您提供充值卡缴费服务', 6, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/index_bt8.png', 999, 'ALL', '0000', 1, null, 'BR', 'images/nav_10_logo.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryBillItemByCust', '月账单查询(客户)', 'qryBill', 'hubfeeservice/monthBill.action?qryType=1', '可按照客户查询近6个月(含当前月)的明细账单', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '综合账单查询功能，可查询本月和前五个月的话费。', null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryBillItemByAcct', '月账单查询(账户)', 'qryBill', 'hubfeeservice/monthBill.action?qryType=0', '可按照账户查询近6个月(含当前月)的明细账单', 5, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '综合账单查询功能，可查询本月和前五个月的话费。', null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recPrivAccept', '优惠办理', 'rec', 'privAccept/qryPriv.action', '办理优惠业务', 14, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), 'images/yhbl0.png', 999, 'ALL', '1000', 2, '受理优惠', null, 'images/yhbl2.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryMonthFee', '月初扣费查询', 'qryBill', 'feeservice/monthDeduct.action', '查询月初扣费信息', 30, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryGsmMuster', '通话详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=1', '提供5+1语音清单查询', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryImsgMuster', '移动梦网详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=3', '提供5+1梦网话单查询', 3, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryCurrentStatus', '当前状态查询', 'qryService', 'serviceinfo/qryCurrentStatus.action', '查询手机的当前服务状态', 8, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryMmsMuster', '彩信详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=6', '提供5+1彩信详单查询', 6, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryChargeGuide', '资费推荐', 'qryService', 'hubfeeservice/qryChargeGuide.action', '为您推荐最合适的资费', 11, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryBillAnalysis', '账单分析查询', 'qryBill', 'hubfeeservice/billAnalysis.action', '查询近6个月(含当前月)分析账单信息', 6, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '可查询当前月和前5个月的账单分析', null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryInfoFeeMuster', '代收信息费详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=7', '提供5+1代收信息费详单查询', 7, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryVpmnMuster', 'VPMN详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=8', '提供5+1VPMN详单查询', 8, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryPimMuster', 'PIM详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=9', '提供5+1PIM详单查询', 9, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryFlashMuster', '手机动画详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=10', '提供5+1手机动画详单查询', 10, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryG3Muster', 'G3详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=11', '提供5+1G3详单查询', 11, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryGameMuster', '游戏点卡详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=12', '提供5+1游戏点卡详单查询', 12, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recProdChange', '产品变更', 'rec', 'prodChange/qryChangeList.action', '办理套餐转换', 15, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '套餐转换', null, 'images/cpbg.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qrySerPersion', '我的个人信息', 'qryService', 'persionInfo/qryPersionInfo.action', '我的个人信息查询情况', 10, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recIdCardBook', '身份证入网预约', 'rec', 'idCardBook/toReadCard.action', '身份证入网预约', 14, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, null, null, 'images/can/service_icon19.png');
commit;
prompt 87 records loaded
prompt Loading SH_MENU_ITEM_MGR...
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_OperMgr', 'SH_SysData', null, '操作员角色设置', '操作员角色设置', 'operator/operatorQry.action', 4, '-1', 'SH_OperMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '操作员角色设置', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_MainPayLog', 'SH_PaymentLog', null, '主账户充值日志查询', '主账户充值日志查询', 'userPayLogQry/mainPayLogPage.action', 2, '-1', 'SH_MainPayLog', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '主账户充值日志查询', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_CallChargeStat', 'SH_StatisticsMgr', null, '话费充值统计', '话费充值统计', 'statisticsMgr/initStatMgrPage.action?statName=callChgStat', 0, '-1', 'SH_CallChargeStat', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '话费充值统计', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_MainAccStat', 'SH_StatisticsMgr', null, '主账户充值统计', '主账户充值统计', 'statisticsMgr/initStatMgrPage.action?statName=mainAccountStat', 1, '-1', 'SH_MainAccStat', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '主账户充值统计', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_PhonePkgStat', 'SH_StatisticsMgr', null, '手机钱包消费统计', '手机钱包消费统计', 'statisticsMgr/initStatMgrPage.action?statName=phonePkgStat', 2, '-1', 'SH_PhonePkgStat', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '手机钱包消费统计', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_ScoreStat', 'SH_StatisticsMgr', null, '积分消费统计', '积分消费统计', null, 3, '-1', 'SH_ScoreStat', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '积分消费统计', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_PubChargeStat', 'SH_StatisticsMgr', null, '公共事业缴费统计', '公共事业缴费统计', 'selfsvcmgr.jsp?targetUrl=statisticsMgr/statisticsMgrAction.do?method=initPubChgFrame', 4, '-1', 'SH_PubChargeStat', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '公共事业缴费统计', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_StatisticsMgr', 'SH_SelfHelp', null, '统计报表管理', '统计报表管理', null, 9, '-1', 'SH_StatisticsMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '统计报表管理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_pubChargeLog', 'SH_PaymentLog', null, '公共事业缴费日志查询', '公共事业缴费日志查询', 'userPayLogQry/pubChargeLogPage.action', 4, '-1', 'SH_pubChargeLog', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '公共事业缴费日志查询', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_ScoreChangeFee', 'SH_SysData', null, '积分与金额兑换管理', '积分与金额兑换管理', 'selfsvcmgr.jsp?targetUrl=scorechangefeeMgr/scoreChangeFeeAction.do?method=frameInit', 5, '-1', 'SH_ScoreChangeFee', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '积分与金额兑换管理', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_PaymentLog', 'SH_SelfHelp', null, '支付日志查询', '支付日志查询', null, 8, '-1', 'SH_PaymentLog', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '支付日志查询', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_PurseLog', 'SH_PaymentLog', null, '手机钱包消费日志查询', '手机钱包日志查询', 'userPayLogQry/purseLogPage.action', 3, '-1', 'SH_PurseLog', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '手机钱包日志查询', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_ChargeLog', 'SH_PaymentLog', null, '交易日志查询', '交易日志查询', 'userPayLogQry/chargeLogPage.action', 1, '-1', 'SH_ChargeLog', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '交易日志查询', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_SelfHelp', '-1', null, '自助终端管理', '自助终端管理', null, 0, '-1', 'SH_SelfHelp', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '自助终端管理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_LogFileInfoMgr', 'SH_TermQuery', null, '终端日志文件查询', '日志文件查询', 'terminalQryStat/termLogQryPage.action', 3, '-1', 'SH_LogFileInfoMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '日志文件查询', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_TermRuleMgr', 'SH_TermControlMgr', null, '告警规则设置', '告警规则设置', 'alarmRule/initQryPage.action', 2, '-1', 'SH_TermRuleMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '告警规则设置', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_TermBaseInfo', 'SH_TermInfo', null, '终端基本资料管理', '对终端设备整体所具有的属性进行登记、变更等操作', 'termConfig/initQryPage.action', 3, '-1', 'SH_TermBaseInfo', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '终端基本资料管理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_TermControlMgr', 'SH_SelfHelp', null, '监控管理', '监控管理', null, 7, '-1', 'SH_TermControlMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '监控管理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_TermPluginInfoMgr', 'SH_TermInfo', null, '终端控件信息管理', '终端控件信息管理', 'terminfo/initPluginInfo.action', 4, '-1', 'SH_TermPluginInfoMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '终端控件信息管理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_TermQuery', 'SH_SelfHelp', null, '终端查询统计', '终端查询统计', null, 3, '-1', 'SH_TermQuery', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '终端查询统计', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_TermRecLogQuery', 'SH_TermQuery', null, '用户日志查询', '用户日志查询', 'terminalQryStat/userLogQuery.action', 1, '-1', 'SH_TermRecLogQuery', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '终端日志查询', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_TermRightMgr', 'SH_TermInfo', null, '终端业务状态管理', '按终端组查询和管理终端上可使用的业务的功能', 'termGroup/initQryPage.action', 5, '-1', 'SH_TermRightMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '终端业务状态管理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_TermWelcomeResMgr', 'SH_MediaPlay', null, '终端首页资源管理', '终端首页资源管理', 'selfsvcmgr.jsp?targetUrl=termmgr/termWelResMgrAction.do?method=initPage', 5, '-1', 'SH_TermWelcomeResMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '终端首页资源管理', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_TermScMgr', 'SH_MediaPlay', null, '广告资源维护', '广告资源维护', 'screenAdvMgr/advPage.action', 3, '-1', 'SH_TermScMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '广告资源维护', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_Yhdz', 'SH_SelfHelp', null, '优惠打折管理 ', '优惠打折管理', null, 5, '-1', 'SH_Yhdz', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '优惠打折', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_Business', 'SH_Yhdz', null, '优惠打折商家维护', '优惠打折商家维护', 'abateMgr/bubbyQryPage.action', 1, '-1', 'SH_Business', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '优惠打折商家维护', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_Production', 'SH_Yhdz', null, '优惠打折产品管理', '优惠打折产品管理', 'abateMgr/productQryPage.action', 2, '-1', 'SH_Production', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '优惠打折商品管理', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_Config', 'SH_Yhdz', null, '优惠打折信息配置', '优惠打折信息配置', 'abateMgr/abateInfoConfigPage.action', 3, '-1', 'SH_Config', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '优惠打折信息终端配置', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_Logs', 'SH_Yhdz', null, '优惠打折日志查询', '优惠打折日志查询', 'abateMgr/abateLogQryPage.action', 4, '-1', 'SH_Logs', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '优惠打折日志查询', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_ScreenProtectMgr', 'SH_MediaPlay', null, '屏保资源维护', '屏保资源维护', 'screenAdvMgr/screenProtectPage.action', 1, '-1', 'SH_ScreenProtectMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '屏保资源维护', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_TermAdvCfg', 'SH_MediaPlay', null, '终端组广告资源配置', '终端组广告资源配置', 'screenAdvMgr/termGrpAdvConfigPage.action', 4, '-1', 'SH_TermAdvCfg', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '终端组广告资源配置', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_TermGroupScreenConfigMgr', 'SH_MediaPlay', null, '终端组屏保资源配置', '终端组屏保资源配置', 'screenAdvMgr/termGrpscreenConfigPage.action', 2, '-1', 'SH_TermGroupScreenConfigMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '终端组屏保资源配置', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_AlarmBindMgr', 'SH_TermControlMgr', null, '告警手机设置', '告警手机设置', 'alarmBind/initQryPage.action', 3, '-1', 'SH_AlarmBindMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '告警手机设置', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_AlarmInfoMgr', 'SH_TermControlMgr', null, '告警信息处理', '告警信息处理', 'alarmInfo/initQryPage.action', 5, '-1', 'SH_AlarmInfoMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '告警信息处理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_SysData', 'SH_SelfHelp', null, '系统数据管理', '系统数据管理', null, 1, '-1', 'SH_SysData', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '系统数据管理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_RoleMgr', 'SH_SysData', null, '角色管理', '角色配置管理', 'role/initQryPage.action', 1, '-1', 'SH_RoleMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '角色配置管理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_TermInfo', 'SH_SelfHelp', null, '终端资料管理', '终端资料是指与终端设备相关的物理属性和抽象信息，主要包括厂商资料、基本资料、控件信息、归属信息等。', null, 2, '-1', 'SH_TermInfo', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '终端资料管理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_ManufacturerInfo', 'SH_TermInfo', null, '终端厂商资料管理', '对终端设备厂商的资料进行登记、变更等操作', 'terminfo/initManufacturerInfo.action', 1, '-1', 'SH_ManufacturerInfo', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '终端厂商资料管理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_RemoteControl', 'SH_TermControlMgr', null, '远程操控', '远程对终端机进行关机、重启、锁定、解锁操作', 'remoteControl/initPage.action', 1, '-1', 'SH_RemoteControl', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '远程操控', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_ManagerLog', 'SH_TermQuery', null, '维护人员日志查询', '维护人员日志查询', 'terminalQryStat/manageLogQuery.action', 2, '-1', 'SH_ManagerLog', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '管理日志查询', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_TermControl', 'SH_TermControlMgr', null, '终端监控', '终端监控', 'termAlarm/initQryPage.action', 4, '-1', 'SH_TermControl', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '终端监控', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_MediaPlay', 'SH_SelfHelp', null, '屏保广告管理', '屏保广告管理', null, 4, '-1', 'SH_MediaPlay', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '屏保广告管理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_SellGoodsMgr', 'SH_SelfHelp', null, '自助售货管理', '自助售货管理', null, 6, '-1', 'SH_SellGoodsMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '自助售货管理', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_MerchandiseMgr', 'SH_SellGoodsMgr', null, '商品信息管理', '商品管理信息', 'selfsvcmgr.jsp?targetUrl=sellgoods/merchandise.do?method=frameInit', 1, '-1', 'SH_MerchandiseMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '商品管理', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_BoothMgr', 'SH_SellGoodsMgr', null, '货道管理信息', '货道管理信息', 'selfsvcmgr.jsp?targetUrl=sellgoods/booth.do?method=frameInit', 2, '-1', 'SH_BoothMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '货道管理', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_SSDeliverMgr', 'SH_SellGoodsMgr', null, '供货人员管理', '供货人员管理', 'selfsvcmgr.jsp?targetUrl=sellgoods/ssDeliver.do?method=frameInit', 4, '-1', 'SH_SSDeliverMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '供货人员信息管理', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_SellManagerMgr', 'SH_SellGoodsMgr', null, '自助售货管理', '自助售货管理', 'selfsvcmgr.jsp?targetUrl=sellgoods/sellManager.do?method=frameInit', 3, '-1', 'SH_SellManagerMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '自助售货管理', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_DictMgr', 'SH_SysData', null, '字典数据管理', '字典数据管理', 'dictitem/initQryPage.action', 3, '-1', 'SH_DictMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '字典数据管理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_ProvideGoodsLog', 'SH_SellGoodsMgr', null, '供货日志查询', '供货日志查询', 'selfsvcmgr.jsp?targetUrl=sellgoods/provideGoodsLog.do?method=frameInit', 6, '-1', 'SH_ProvideGoodsLog', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '供货日志查询', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_SellGoodsLog', 'SH_SellGoodsMgr', null, '售货日志查询', '售货日志查询', 'selfsvcmgr.jsp?targetUrl=sellgoods/sellGoodsLog.do?method=frameInit', 5, '-1', 'SH_SellGoodsLog', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '售货日志查询', '32', 1, 0, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_MachineModel', 'SH_TermInfo', null, '终端型号资料管理', '终端型号资料管理', 'termModel/initQryPage.action', 2, '-1', 'SH_MachineModel', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '终端型号资料管理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_ChgPassword', 'SH_SysData', null, '密码修改', '密码修改', 'operator/chgPassPage.action', 2, '-1', 'SH_ChgPassword', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '密码修改', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_TermCashAudit', 'SH_PaymentLog', null, '现金稽核日志查询', '现金稽核日志查询', 'userPayLogQry/initCashAuditPage.action', 5, '-1', 'SH_TermCashAudit', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '现金稽核日志查询', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_InvoicePrintLog', 'SH_PaymentLog', null, '发票打印日志查询', '发票打印日志查询', 'userPayLogQry/invoicePrintLogPage.action', 6, '-1', 'SH_InvoicePrintLog', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '发票打印日志查询', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_CashRecord', 'SH_PaymentLog', null, '现金对账原始记录查询', '现金对账原始记录查询', 'userPayLogQry/cashRecordPage.action', 7, '-1', 'SH_CashRecord', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '现金对账原始记录查询', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_UnionRecord', 'SH_PaymentLog', null, '银联对账原始记录查询', '银联对账原始记录查询', 'userPayLogQry/unionRecordPage.action', 8, '-1', 'SH_UnionRecord', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '银联对账原始记录查询', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_CheckLog', 'SH_TermControlMgr', null, '终端机日志上传检查', '终端机日志上传检查', 'termLogCheck/initQryPage.action', 6, '-1', 'SH_CheckLog', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '终端机日志上传检查', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
commit;
prompt 57 records loaded
prompt Loading SH_OPERATOR_ROLE...
insert into SH_OPERATOR_ROLE (OPERID, ROLEID, STATUS, STATUSDATE)
values ('admin', '10000000000000', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_OPERATOR_ROLE (OPERID, ROLEID, STATUS, STATUSDATE)
values ('1301834', '10000000000001', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_OPERATOR_ROLE (OPERID, ROLEID, STATUS, STATUSDATE)
values ('1301143', '10000000000001', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 3 records loaded
prompt Loading SH_PARAM...
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CDR_PRTTIMES', '清单打印次数限制', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_RETURN_HOMEPAGE', '从其它页面返回首页面的时间，单位毫秒', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHOOSETEL_IDCARD_TIME', '身份证选号次数', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('DEFAULT_PAGE_SIZE', '设置后台分页中每页条数', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_OCX_CHECK_FREQUENCY', '小票打印机与发票打印机检测频率(1:1次 0:N次)', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_REC_TASTE_URL', '业务体验URL', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL_DEALTIME', '现金对账处理时间', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL', '现金对账开关(1：开启  0：关闭)', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL_PATH', '现金对帐文件存放路径', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_SOCKET_PORT', '自助终端终端机设备统一SOCKET端口(控制重启关机时用到)', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_UPLOAD_TIME', '自助终端终端机设备统一状态上传时间(单位:分钟)', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_WEBINTER_OUTOPERPWD', '连接WebService服务密码', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BALANCE', '自助终端设置余额提醒值', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOGFTPADDR', '自助终端上传日志的地址和端口,如192.168.0.1,21', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_SOCKADDR', '自助终端管理平台socket地址和端口,如192.168.0.1,8888', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_WELCOME_WIDTH', '自助终端首页资源宽度资源', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_WELCOME_HEIGHT', '自助终端首页资源高度资源', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_WELCOME_PATH', '自助终端首页资源地址资源', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_WELCOME_PATH_TMP', '自助终端首页资源临时地址', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_WEBSERVICE', 'WebService服务地址', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_DUTY_PROVINCE', '银行卡缴费负责申明省份', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_PAYMONEY_TIME', '现金缴费投币等待时间(秒)', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_RETURNIDX_TIME', '缴费完成后返回主页面等待时间(秒)', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CAPTURECARD_TIME', '银联卡缴费吞卡等待时间(秒)', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_MAKESURE_TIME', '银联卡缴费缴费确认等待时间(秒)', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_INPUTMONEY_TIME', '银联卡缴费缴费金额输入等待时间(秒)', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_INPUTCARDPWD_TIME', '银联卡缴费密码输入等待时间(秒)', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_READCARD_TIME', '银联卡缴费读卡等待时间(秒)', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOCAL_SCWPL_LIST', '自助终端客户端屏保播放列表文件名', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOCAL_FILE_PATH', '自助终端客户端媒体文件地址', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOCAL_ADVWPL_LIST', '自助终端客户端广告播放列表文件名', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOCAL_SCPLAY_LIST', '自助终端客户端屏保本地媒体文件列表', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOCAL_ADVPLAY_LIST', '自助终端客户端广告本地媒体文件列表', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_GO_SCEEN_TIME', '自助终端客户端主面跳到屏保页等待时间', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL_FTPSERVER', '现金对帐FTP服务器地址', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL_USERNAME', '现金对帐FTP服务器用户名', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL_PASSWORD', '现金对帐FTP服务器密码', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOGIN_TIMESCOPE', '密码连续输入错误的时间范围，单位分钟', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOGIN_MAXTIMES', '密码连续输入错误的最大次数', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOGIN_LOCKEDTIME', '号码被锁定的时间，单位分钟', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_UPLOADOCX_TMPPATH', '控件上传存放的临时目录', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_RANDOMPWD_LEN', '自助终端系统使用的随机密码的长度', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_RANDOMPWD_VALIDITY', '自助终端系统使用的随机密码的有效期，单位分钟', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_RANDOMPWD_CONTENT', '随机密码短信内容', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BACKPAT_CODE', '乐付网缴费冲正的功能码', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BACKPAT_URL', '乐付网缴费冲正的URL地址', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOG4J_LEVEL', 'log4j的日志级别', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_UNIONPAY_PASSWORD', '银联对账文件存放的ftp的密码', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_UNIONPAY_USERNAME', '银联对账文件存放的ftp的用户名', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_UNIONPAY_FTPSERVER', '银联对账文件存放的ftp地址', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_UNIONPAY_SUBDIRECTORY', '银联对账文件存放的目录', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL_REPEAT', '缴费对账重试次数。', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL_INTERVAL', 'boss对帐重试之间的时间间隔，单位：分钟', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BUSINESS_DEALTIME', '交易明细上传时间', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BUSINESS', '交易明细对账开关(1：开启  0：关闭)', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BUSINESS_TMPPATH', '交易明细文件临时存放路径', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BUSINESS_REPEAT', '交易对账重试次数。', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BUSINESS_INTERVAL', '交易对帐重试之间的时间间隔，单位：分钟', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BUSINESS_FTPPATH', '交易明细文件FTP存放路径', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_PRTINVOICE_RANDOMPWD', '发票打印时是否需要随机码', 'String', 0, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_PRTINVOICE_RANDOMPWD_CONTENT', '打印发票时，随机密码短信内容', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_FCGIURL_711', '统一接口平台地址_711', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKLOG_OUTTIME', '终端机硬件日志上传超时时间', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKLOG_TYPE', '需要检测的终端机日志文件', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CASHINTERVAL', '现金对账文件上传时间间隔，单位：分钟', 'String', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
commit;
prompt 65 records loaded
prompt Loading SH_PARAM_VALUE...
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_GO_SCEEN_TIME', '120', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CDR_PRTTIMES', '1', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_RETURN_HOMEPAGE', '1201000', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BACKPAT_CODE', '0907', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BACKPAT_URL', 'http://218.57.146.190/presys/servlet/ZhuoWangServlet', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHOOSETEL_IDCARD_TIME', '4', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('DEFAULT_PAGE_SIZE', '15', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_OCX_CHECK_FREQUENCY', '1', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_REC_TASTE_URL', 'http://218.206.81.78/wwwpps/loginServlet?method=doLogin&branchId=null&rememberURL=/z/sdDFT/index.html&username=053103400&password=111qqq$', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL_DEALTIME', '11:40', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL', '0', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL_PATH', 'C:/checkbill/', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_SOCKET_PORT', '6000', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_UPLOAD_TIME', '10', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_WEBINTER_OUTOPERPWD', '12345678', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BALANCE', '100000', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOGFTPADDR', '171.0.0.1,21', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_SOCKADDR', '10.31.81.101,6666', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_WELCOME_HEIGHT', '600px', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_WELCOME_WIDTH', '500px', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_WELCOME_PATH', './images/welcome/cmcc_txtnew.swf', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_WELCOME_PATH_TMP', './images/welcome/cmcc_txtnew_tmp.swf', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_WEBSERVICE', 'http://10.164.130.108:8080/axis/SelfSvcTest.jws?wsdl', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_DUTY_PROVINCE', '山东', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_PAYMONEY_TIME', '30', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_READCARD_TIME', '30', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_INPUTCARDPWD_TIME', '30', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_INPUTMONEY_TIME', '120', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_MAKESURE_TIME', '60', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CAPTURECARD_TIME', '30', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_RETURNIDX_TIME', '20', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOCAL_ADVPLAY_LIST', 'advfile.xml', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOCAL_SCPLAY_LIST', 'scfile.xml', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOCAL_FILE_PATH', 'D:/media', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOCAL_SCWPL_LIST', 'sc.wpl', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOCAL_ADVWPL_LIST', 'adv.wpl', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOGIN_TIMESCOPE', '15', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL_PASSWORD', 'yanqiang', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL_USERNAME', 'yanqiang', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL_FTPSERVER', '192.168.0.22', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOGIN_MAXTIMES', '3', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOGIN_LOCKEDTIME', '60', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_UPLOADOCX_TMPPATH', 'D:/ocxTmp', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_RANDOMPWD_LEN', '6', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_RANDOMPWD_VALIDITY', '5', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_RANDOMPWD_CONTENT', '您的自助终端随机密码是：[%1]，请输入系统。该密码仅限本次使用。', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOG4J_LEVEL', 'INFO', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_UNIONPAY_FTPSERVER', '192.168.1.72', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_UNIONPAY_USERNAME', 'yanqiang', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_UNIONPAY_PASSWORD', 'yanqiang', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_UNIONPAY_SUBDIRECTORY', 'sdbcn', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL_REPEAT', '3', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL_INTERVAL', '2', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BUSINESS_DEALTIME', '11:05:00', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BUSINESS', '1', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BUSINESS_TMPPATH', 'C:/business/', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BUSINESS_REPEAT', '3', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BUSINESS_INTERVAL', '2', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BUSINESS_FTPPATH', 'business', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_PRTINVOICE_RANDOMPWD', '0', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_PRTINVOICE_RANDOMPWD_CONTENT', '您在移动公司的自助终端上打印发票，您的发票打印短信验证码是:[%1]', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_FCGIURL_711', 'http://171.0.0.30:9080/fcgi-bin/SelfTerminal', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKLOG_OUTTIME', '25', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKLOG_TYPE', '02|03', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CASHINTERVAL', '60', 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 65 records loaded
prompt Loading SH_PC_DSMP_SPBIZINFO...
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '801071', '120301', '天下娱乐通', null, null, '2', '10000', '20100204', '29991231', '85:0:0', '0', 'G', '0', '1', '392', null, null, null, 1, to_date('05-06-2009 11:25:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('05-02-2010 00:10:17', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MOD_SPOPERATOR_000103_20100204.300', 'DSMP', null, null, '9', '明星资讯评论，生活原来可以如此精彩，为您道上天下趣事。', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '801071', '130303', '生活快报', null, null, '2', '15000', '20100204', '29991231', '85:0:0', '0', 'G', '0', '1', '393', null, null, null, 1, to_date('05-06-2009 11:25:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('05-02-2010 00:10:17', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MOD_SPOPERATOR_000103_20100204.300', 'DSMP', null, null, '9', '指点健康生活，引领潮流生活，领略自然风采，百姓生活资讯全报道。', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '801027', '150301', '音乐小天使', null, null, '2', '15000', '20100204', '29991231', '85:0:0', '0', 'G', '0', '1', '207', null, null, null, 1, to_date('05-06-2009 11:25:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('05-02-2010 00:10:21', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MOD_SPOPERATOR_000103_20100204.300', 'DSMP', null, null, '9', '铃声＋歌曲介绍＋歌曲点评/音乐人的故事', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('090508', '698001', '010840', '手机阅读点播10840', null, null, '1', '108400', '20100901', '29991231', '80', '0', 'G', '0', '0', null, null, null, null, 1, to_date('13-09-2010 17:26:29', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-09-2010 03:40:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MCBBJ_00_SP_OPER_20100927.txt', 'CMREAD', null, null, null, '手机阅读点播10840', '1', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('090508', '698001', '010841', '手机阅读点播10841', null, null, '1', '108410', '20100901', '29991231', '80', '0', 'G', '0', '0', null, null, null, null, 1, to_date('13-09-2010 17:26:29', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-09-2010 03:40:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MCBBJ_00_SP_OPER_20100927.txt', 'CMREAD', null, null, null, '手机阅读点播10841', '1', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('090508', '698001', '010842', '手机阅读点播10842', null, null, '1', '108420', '20100901', '29991231', '80', '0', 'G', '0', '0', null, null, null, null, 1, to_date('13-09-2010 17:26:29', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-09-2010 03:40:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MCBBJ_00_SP_OPER_20100927.txt', 'CMREAD', null, null, null, '手机阅读点播10842', '1', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('090508', '698001', '010843', '手机阅读点播10843', null, null, '1', '108430', '20100901', '29991231', '80', '0', 'G', '0', '0', null, null, null, null, 1, to_date('13-09-2010 17:26:29', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-09-2010 03:40:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MCBBJ_00_SP_OPER_20100927.txt', 'CMREAD', null, null, null, '手机阅读点播10843', '1', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('090508', '698001', '010844', '手机阅读点播10844', null, null, '1', '108440', '20100901', '29991231', '80', '0', 'G', '0', '0', null, null, null, null, 1, to_date('13-09-2010 17:26:29', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-09-2010 03:40:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MCBBJ_00_SP_OPER_20100927.txt', 'CMREAD', null, null, null, '手机阅读点播10844', '1', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '801061', '101100', '帮助', null, null, '0', '0', '20050618', '29991231', '85:0:0', '0', 'G', '0', '1', '347', null, null, null, 1, to_date('05-06-2009 11:25:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('05-06-2009 11:25:27', 'dd-mm-yyyy hh24:mi:ss'), 0, 'ADD_SPOPERATOR_000103_20090605.300', 'DSMP', null, null, '7', '通知等免费信息', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, null, null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '801061', '101101', 'CMCCTEST', null, null, '0', '0', '20080120', '29991231', '85:0:0', '0', 'G', '0', '1', '348', null, null, null, 1, to_date('05-06-2009 11:25:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('05-06-2009 11:25:27', 'dd-mm-yyyy hh24:mi:ss'), 0, 'ADD_SPOPERATOR_000103_20090605.300', 'DSMP', null, null, '8', '统一业务测试指令', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, null, null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('090508', '698001', '014439', '手机阅读点播14439', null, null, '1', '144390', '20100901', '29991231', '80', '0', 'G', '0', '0', null, null, null, null, 1, to_date('13-09-2010 17:26:21', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-09-2010 03:40:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MCBBJ_00_SP_OPER_20100927.txt', 'CMREAD', null, null, null, '手机阅读点播14439', '1', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('090508', '698001', '014440', '手机阅读点播14440', null, null, '1', '144400', '20100901', '29991231', '80', '0', 'G', '0', '0', null, null, null, null, 1, to_date('13-09-2010 17:26:21', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-09-2010 03:40:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MCBBJ_00_SP_OPER_20100927.txt', 'CMREAD', null, null, null, '手机阅读点播14440', '1', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('090508', '698001', '014441', '手机阅读点播14441', null, null, '1', '144410', '20100901', '29991231', '80', '0', 'G', '0', '0', null, null, null, null, 1, to_date('13-09-2010 17:26:21', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-09-2010 03:40:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MCBBJ_00_SP_OPER_20100927.txt', 'CMREAD', null, null, null, '手机阅读点播14441', '1', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '801061', '124302', '子夜影城', null, null, '2', '15000', '20100309', '29991231', '85:0:0', '0', 'G', '0', '1', '32', null, null, null, 1, to_date('05-06-2009 11:25:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('11-03-2010 01:54:12', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MOD_SPOPERATOR_000103_20100309.300', 'DSMP', null, null, '9', '欧美、港台、日韩影片介绍。赶快行动享受体验吧！', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '801061', '124311', '音乐风云榜', null, null, '2', '15000', '20100309', '29991231', '85:0:0', '0', 'G', '0', '1', '33', null, null, null, 1, to_date('05-06-2009 11:25:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('11-03-2010 01:54:12', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MOD_SPOPERATOR_000103_20100309.300', 'DSMP', null, null, '9', '结合电视节目音乐风云榜，介绍上榜歌曲的演唱者，风格，歌词等情况。', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('090508', '698001', '014442', '手机阅读点播14442', null, null, '1', '144420', '20100901', '29991231', '80', '0', 'G', '0', '0', null, null, null, null, 1, to_date('13-09-2010 17:26:21', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-09-2010 03:40:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MCBBJ_00_SP_OPER_20100927.txt', 'CMREAD', null, null, null, '手机阅读点播14442', '1', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '801061', '134302', '迷情特刊', null, null, '2', '15000', '20100309', '29991231', '85:0:0', '0', 'G', '0', '1', '36', null, null, null, 1, to_date('05-06-2009 11:25:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('11-03-2010 01:54:12', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MOD_SPOPERATOR_000103_20100309.300', 'DSMP', null, null, '9', '邀你静夜里共享这份彩信特刊，图文并茂的真实描绘，还等什么，即刻拥有这份激情杂志。', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '801061', '134303', '燃情图库', null, null, '2', '15000', '20100309', '29991231', '85:0:0', '0', 'G', '0', '1', '37', null, null, null, 1, to_date('05-06-2009 11:25:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('11-03-2010 01:54:12', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MOD_SPOPERATOR_000103_20100309.300', 'DSMP', null, null, '9', '让您眩目、迷恋的S玉体曲线，神秘、燃情的图库——你我的家园！', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '801061', '136302', '两性宝典', null, null, '2', '15000', '20100309', '29991231', '85:0:0', '0', 'G', '0', '1', '38', null, null, null, 1, to_date('05-06-2009 11:25:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('11-03-2010 01:54:12', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MOD_SPOPERATOR_000103_20100309.300', 'DSMP', null, null, '9', '向您倡导两性健康，营造浪漫的两性生活。一点点神秘，一点点诱惑，一点点窍门，尽在性爱宝典两性伊甸园。', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '819014', '000', '免费信息', null, null, '0', '0', '20050601', '29991231', '85:0:0', '0', 'G', '0', '1', '72', null, null, null, 1, to_date('05-06-2009 11:25:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('05-06-2009 11:25:25', 'dd-mm-yyyy hh24:mi:ss'), 0, 'ADD_SPOPERATOR_000103_20090605.300', 'DSMP', null, null, '7', '帮助信息', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, null, null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('090508', '698001', '014443', '手机阅读点播14443', null, null, '1', '144430', '20100901', '29991231', '80', '0', 'G', '0', '0', null, null, null, null, 1, to_date('13-09-2010 17:26:21', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-09-2010 03:40:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MCBBJ_00_SP_OPER_20100927.txt', 'CMREAD', null, null, null, '手机阅读点播14443', '1', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '819014', '102321', '漫画世界', null, null, '2', '10000', '20100204', '29991231', '85:0:0', '0', 'G', '0', '1', '626', null, null, null, 1, to_date('05-06-2009 11:25:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('05-02-2010 00:10:22', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MOD_SPOPERATOR_000103_20100204.300', 'DSMP', null, null, '9', '提供专业的动漫卡通下载', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '819014', '102323', '时尚空间', null, null, '2', '10000', '20100204', '29991231', '85:0:0', '0', 'G', '0', '1', '627', null, null, null, 1, to_date('05-06-2009 11:25:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('05-02-2010 00:10:22', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MOD_SPOPERATOR_000103_20100204.300', 'DSMP', null, null, '9', '提供专业的时尚最新资讯和时尚动态走向。', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '801001', '115301', '娱乐新闻', null, null, '2', '15000', '20100204', '29991231', '85:0:0', '0', 'G', '0', '1', '6', null, null, null, 1, to_date('05-06-2009 11:25:33', 'dd-mm-yyyy hh24:mi:ss'), to_date('05-02-2010 00:10:17', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MOD_SPOPERATOR_000103_20100204.300', 'DSMP', null, null, '3', '每条彩信里包含7－10条图文并茂，国际国内娱乐界最新动态、电影资讯、明星行踪.', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('000103', '801001', '115303', '明星串串秀', null, null, '2', '8000', '20100212', '29991231', '85:0:0', '0', 'G', '0', '1', '46', null, null, null, 1, to_date('05-06-2009 11:25:33', 'dd-mm-yyyy hh24:mi:ss'), to_date('13-02-2010 00:10:14', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MOD_SPOPERATOR_000103_20100212.300', 'DSMP', null, null, '9', '明星串串秀是一款引领全球明星娱乐导航的彩信杂志，带您走近大腕明星时尚的衣食住行和个人嗜好，报道明星代言活动和超级歪情的独家行踪，细数传奇明星复活记录。', '0', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('090508', '698001', '014444', '手机阅读点播14444', null, null, '1', '144440', '20100901', '29991231', '80', '0', 'G', '0', '0', null, null, null, null, 1, to_date('13-09-2010 17:26:21', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-09-2010 03:40:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MCBBJ_00_SP_OPER_20100927.txt', 'CMREAD', null, null, null, '手机阅读点播14444', '1', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
insert into SH_PC_DSMP_SPBIZINFO (SERVTYPE, SPCODE, OPERATORCODE, OPERATORNAME, OTHERBALOBJ1, OTHERBALOBJ2, BILLFLAG, FEE, VALIDDATE, EXPIREDATE, BALPROP, COUNT, SERVATTR, ISTHIRDVALIDATE, RECONFIRM, IDORDER, EXPAND, CHECKFLAG, CHECKRSLT, STATUS, CREATEDATE, STATUSDATE, HAVEAPD, OPFILENAME, DOMAIN, SERVICEID, PRODUCTID, SERVPROPERTY, NOTE, AUDITSTATUS, AUDITDATE, AUDITOPER, AUDITOPINION, SPBIZUSAGE, ISCHKSP, AFFECTTYPE, CHGAFFECTTYPE, DELENDTYPE, CHGDELENDTYPE, ISDEPDOMAIN, DEDUCT_CLUE, QUERY_TD, SIMS_TIME)
values ('090508', '698001', '014445', '手机阅读点播14445', null, null, '1', '144450', '20100901', '29991231', '80', '0', 'G', '0', '0', null, null, null, null, 1, to_date('13-09-2010 17:26:21', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-09-2010 03:40:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 'MCBBJ_00_SP_OPER_20100927.txt', 'CMREAD', null, null, null, '手机阅读点播14445', '1', null, null, null, 'A', 0, 'EffectImmed', 0, 'DelEndImmed', 0, 0, '0', null, null);
commit;
prompt 27 records loaded
prompt Loading SH_RANDOM_PASSWORD...
prompt Table is empty
prompt Loading SH_REC_LOG...
prompt Table is empty
prompt Loading SH_REC_LOG_MGR...
prompt Table is empty
prompt Loading SH_ROLE_INFO...
insert into SH_ROLE_INFO (ROLEID, ROLENAME, CREATEDATE, CREATEOPERATOR, STATUS, NOTES)
values ('10000000000000', '开发使用', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', 1, '不可以删除，不可以修改');
insert into SH_ROLE_INFO (ROLEID, ROLENAME, CREATEDATE, CREATEOPERATOR, STATUS, NOTES)
values ('10000000000001', '省级管理员', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', 1, '不可以删除，不可以修改');
commit;
prompt 2 records loaded
prompt Loading SH_ROLE_MENU...
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_SelfHelp', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_SysData', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_RoleMgr', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_ChgPassword', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_DictMgr', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_OperMgr', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_TermInfo', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_ManufacturerInfo', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_MachineModel', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_TermBaseInfo', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_TermPluginInfoMgr', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_TermRightMgr', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_TermQuery', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_TermRecLogQuery', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_ManagerLog', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_LogFileInfoMgr', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_MediaPlay', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_ScreenProtectMgr', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_TermGroupScreenConfigMgr', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_TermControlMgr', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_RemoteControl', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_TermRuleMgr', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_AlarmBindMgr', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_TermControl', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_AlarmInfoMgr', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_PaymentLog', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_ChargeLog', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_TermCashAudit', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_InvoicePrintLog', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_CashRecord', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_UnionRecord', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_StatisticsMgr', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_CallChargeStat', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_MainAccStat', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000001', 'SH_PhonePkgStat', 1, to_date('05-07-2011 16:37:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_SelfHelp', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_SysData', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_RoleMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_ChgPassword', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_DictMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_OperMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_ScoreChangeFee', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_TermInfo', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_ManufacturerInfo', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_MachineModel', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_TermBaseInfo', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_TermPluginInfoMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_TermRightMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_TermQuery', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_TermRecLogQuery', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_ManagerLog', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_LogFileInfoMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_MediaPlay', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_ScreenProtectMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_TermGroupScreenConfigMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_TermScMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_TermAdvCfg', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_TermWelcomeResMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_Yhdz', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_Business', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_Production', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_Config', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_Logs', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_SellGoodsMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_MerchandiseMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_BoothMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_SellManagerMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_SSDeliverMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_SellGoodsLog', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_ProvideGoodsLog', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_TermControlMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_RemoteControl', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_TermRuleMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_AlarmBindMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_TermControl', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_AlarmInfoMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_CheckLog', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_PaymentLog', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_ChargeLog', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_MainPayLog', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_PurseLog', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_pubChargeLog', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_TermCashAudit', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_InvoicePrintLog', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_StatisticsMgr', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_CallChargeStat', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_MainAccStat', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_PhonePkgStat', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_ScoreStat', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000000000', 'SH_PubChargeStat', 1, to_date('05-07-2011 13:48:42', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 90 records loaded
prompt Loading SH_SELLGOODS_LOG...
prompt Table is empty
prompt Loading SH_TERM_CONFIG...
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE, PASSWORD)
values ('100000', 'XXX', '127.0.0.1', '00-00-00-00-00-00', '1301143', null, null, null, null, 'IE', '111111101100', '10000000000100', 'HB.EZ', '171.0.0.4,6666', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 711, '鄂州', '10000000000200', null, null, 'e10adc3949ba59abbe56e057f20f883e');
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE, PASSWORD)
values ('100001', '姚远', '10.31.81.61', '00-00-00-00-00-00', '1301143', null, null, null, null, 'IE', '111111000110', '10000000000100', 'HB.EZ', '171.0.0.4,6666', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 711, '湖北鄂州(HB.EZ)', '10000000000200', 'A0000711', null, 'e10adc3949ba59abbe56e057f20f883e');
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE, PASSWORD)
values ('100002', '高群', '192.168.10.159', '00-00-00-00-00-00', '1301834', null, null, '银联商户号', '银联终端ID', 'IE', '111111000110', '10000000000100', 'HB.EZ', '171.0.0.4,6666', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 711, '湖北鄂州(HB.EZ)', '10000000000200', 'A0000001', null, 'e10adc3949ba59abbe56e057f20f883e');
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE, PASSWORD)
values ('100003', '闫强', '10.31.81.101', '00-21-97-AB-29-46', '1301143', null, null, null, null, 'IE', '111111001000', '10000000000100', 'HB.EZ', '171.0.0.4,6666', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 711, '湖北鄂州(HB.EZ)', '10000000000200', '1301143', null, 'e10adc3949ba59abbe56e057f20f883e');
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE, PASSWORD)
values ('100004', '赵鹏', '10.31.81.93', '68-B5-99-E7-87-B0', '1301143', null, null, null, null, 'IE', '111111111111', '10000000000100', 'HB.EZ', '171.0.0.4,6666', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 711, '湖北鄂州(HB.EZ)', '10000000000200', 'A000001', null, 'e10adc3949ba59abbe56e057f20f883e');
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE, PASSWORD)
values ('100005', '徐国潍', '10.31.81.102', '00-00-00-00-00-00', '1301143', null, null, null, null, 'IE', '111111000110', '10000000000100', 'HB.EZ', '171.0.0.4,6666', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 711, '湖北鄂州(HB.EZ)', '10000000000200', 'A0000001', null, 'e10adc3949ba59abbe56e057f20f883e');
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE, PASSWORD)
values ('100006', '李锋', '10.31.81.37', '00-00-00-00-00-00', '1301834', null, null, null, null, 'IE', '111111001110', '10000000000100', 'HB.EZ', '171.0.0.4,6666', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 711, '鄂州', '10000000000200', 'A0000001', null, 'e10adc3949ba59abbe56e057f20f883e');
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE, PASSWORD)
values ('100007', '凯信达测试', '10.31.81.237', '00-21-97-AB-29-41', '1301143', null, null, null, null, 'IE', '111111000110', '10000000000101', 'HB.EZ', '171.0.0.4,6666', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 711, '湖北鄂州(HB.EZ)', '10000000000201', '1301143', null, 'e10adc3949ba59abbe56e057f20f883e');
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE, PASSWORD)
values ('100008', '政通测试', '10.31.81.234', '00-21-97-AB-29-42', '1301143', null, null, null, null, 'IE', '111111001110', '10000000000103', 'HB.EZ', '171.0.0.4,6666', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 711, '湖北鄂州(HB.EZ)', '10000000000203', '1301143', null, 'e10adc3949ba59abbe56e057f20f883e');
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE, PASSWORD)
values ('100009', '印天海洛测试', '10.31.81.198', '00-21-97-AB-29-40', '1301143', null, null, null, null, 'IE', '111111001110', '10000000000102', 'HB.EZ', '171.0.0.4,6666', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 711, '湖北鄂州(HB.EZ)', '10000000000202', '1301143', null, 'e10adc3949ba59abbe56e057f20f883e');
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE, PASSWORD)
values ('100010', '西安中兴测试', '10.31.81.103', '00-21-97-AB-29-42', '1301143', null, null, null, null, 'IE', '111111001110', '10000000000104', 'HB.EZ', '171.0.0.4,6666', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), 711, '湖北鄂州(HB.EZ)', '10000000000204', '1301143', null, 'e10adc3949ba59abbe56e057f20f883e');
commit;
prompt 11 records loaded
prompt Loading SH_TERM_GROUP...
prompt Table is empty
prompt Loading SH_TERM_MODEL...
insert into SH_TERM_MODEL (TERMMODELID, TERMMODEL, MANUFACTURERID, DESCRIBE, STATUS, STATUSDATE)
values ('10000000000200', '开发测试通用', '10000000000100', null, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_MODEL (TERMMODELID, TERMMODEL, MANUFACTURERID, DESCRIBE, STATUS, STATUSDATE)
values ('10000000000201', '凯信达科技通用', '10000000000101', null, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_MODEL (TERMMODELID, TERMMODEL, MANUFACTURERID, DESCRIBE, STATUS, STATUSDATE)
values ('10000000000202', '印天海洛科技通用', '10000000000102', null, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_MODEL (TERMMODELID, TERMMODEL, MANUFACTURERID, DESCRIBE, STATUS, STATUSDATE)
values ('10000000000203', '政通科技通用', '10000000000103', null, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_MODEL (TERMMODELID, TERMMODEL, MANUFACTURERID, DESCRIBE, STATUS, STATUSDATE)
values ('10000000000204', '西安普声通用', '10000000000105', null, 1, to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 5 records loaded
prompt Loading SH_UNIONFILE_LOG...
prompt Table is empty
prompt Loading SH_UNIONPAY_LOG...
prompt Table is empty
prompt Loading SH_UNIONRECORD_LOG...
prompt Table is empty
prompt Enabling triggers for SH_ALARM_BIND...
alter table SH_ALARM_BIND enable all triggers;
prompt Enabling triggers for SH_ALARM_INFO...
alter table SH_ALARM_INFO enable all triggers;
prompt Enabling triggers for SH_ALARM_RULE...
alter table SH_ALARM_RULE enable all triggers;
prompt Enabling triggers for SH_CASHFILE_LOG...
alter table SH_CASHFILE_LOG enable all triggers;
prompt Enabling triggers for SH_CASHRECORD_LOG...
alter table SH_CASHRECORD_LOG enable all triggers;
prompt Enabling triggers for SH_CHARGE_LOG...
alter table SH_CHARGE_LOG enable all triggers;
prompt Enabling triggers for SH_CHECK_LOG...
alter table SH_CHECK_LOG enable all triggers;
prompt Enabling triggers for SH_CHOOSETEL_NUM...
alter table SH_CHOOSETEL_NUM enable all triggers;
prompt Enabling triggers for SH_COMMONPAY_LOG...
alter table SH_COMMONPAY_LOG enable all triggers;
prompt Enabling triggers for SH_DICT_ITEM...
alter table SH_DICT_ITEM enable all triggers;
prompt Enabling triggers for SH_GROUP_ADV...
alter table SH_GROUP_ADV enable all triggers;
prompt Enabling triggers for SH_GROUP_BUDDY...
alter table SH_GROUP_BUDDY enable all triggers;
prompt Enabling triggers for SH_GROUP_INFO...
alter table SH_GROUP_INFO enable all triggers;
prompt Enabling triggers for SH_GROUP_MENU...
alter table SH_GROUP_MENU enable all triggers;
prompt Enabling triggers for SH_GROUP_SCREEN...
alter table SH_GROUP_SCREEN enable all triggers;
prompt Enabling triggers for SH_INFO_ABATE...
alter table SH_INFO_ABATE enable all triggers;
prompt Enabling triggers for SH_INFO_BUDDY...
alter table SH_INFO_BUDDY enable all triggers;
prompt Enabling triggers for SH_INFO_MANUFACTURER...
alter table SH_INFO_MANUFACTURER enable all triggers;
prompt Enabling triggers for SH_INFO_MEDIARES...
alter table SH_INFO_MEDIARES enable all triggers;
prompt Enabling triggers for SH_INFO_PLUGIN...
alter table SH_INFO_PLUGIN enable all triggers;
prompt Enabling triggers for SH_INFO_REGIONLIST...
alter table SH_INFO_REGIONLIST enable all triggers;
prompt Enabling triggers for SH_LOG_ABATE...
alter table SH_LOG_ABATE enable all triggers;
prompt Enabling triggers for SH_LOG_AUDIT...
alter table SH_LOG_AUDIT enable all triggers;
prompt Enabling triggers for SH_LOG_BUSINESS...
alter table SH_LOG_BUSINESS enable all triggers;
prompt Enabling triggers for SH_LOG_CASHRETURN...
alter table SH_LOG_CASHRETURN enable all triggers;
prompt Enabling triggers for SH_LOG_CHECKBILL...
alter table SH_LOG_CHECKBILL enable all triggers;
prompt Enabling triggers for SH_LOG_LOGINERRNUM...
alter table SH_LOG_LOGINERRNUM enable all triggers;
prompt Enabling triggers for SH_LOG_MOBILEPAY...
alter table SH_LOG_MOBILEPAY enable all triggers;
prompt Enabling triggers for SH_LOG_MOBILEWALLET...
alter table SH_LOG_MOBILEWALLET enable all triggers;
prompt Enabling triggers for SH_LOG_PRINTBILLNUM...
alter table SH_LOG_PRINTBILLNUM enable all triggers;
prompt Enabling triggers for SH_LOG_PRINTINVOICE...
alter table SH_LOG_PRINTINVOICE enable all triggers;
prompt Enabling triggers for SH_LOG_PRIV...
alter table SH_LOG_PRIV enable all triggers;
prompt Enabling triggers for SH_LOG_PRODCHANGE...
alter table SH_LOG_PRODCHANGE enable all triggers;
prompt Enabling triggers for SH_LOG_TERMSTATUS...
alter table SH_LOG_TERMSTATUS enable all triggers;
prompt Enabling triggers for SH_LOG_TERMUP...
alter table SH_LOG_TERMUP enable all triggers;
prompt Enabling triggers for SH_MENU_ITEM...
alter table SH_MENU_ITEM enable all triggers;
prompt Enabling triggers for SH_MENU_ITEM_MGR...
alter table SH_MENU_ITEM_MGR enable all triggers;
prompt Enabling triggers for SH_OPERATOR_ROLE...
alter table SH_OPERATOR_ROLE enable all triggers;
prompt Enabling triggers for SH_PARAM...
alter table SH_PARAM enable all triggers;
prompt Enabling triggers for SH_PARAM_VALUE...
alter table SH_PARAM_VALUE enable all triggers;
prompt Enabling triggers for SH_PC_DSMP_SPBIZINFO...
alter table SH_PC_DSMP_SPBIZINFO enable all triggers;
prompt Enabling triggers for SH_RANDOM_PASSWORD...
alter table SH_RANDOM_PASSWORD enable all triggers;
prompt Enabling triggers for SH_REC_LOG...
alter table SH_REC_LOG enable all triggers;
prompt Enabling triggers for SH_REC_LOG_MGR...
alter table SH_REC_LOG_MGR enable all triggers;
prompt Enabling triggers for SH_ROLE_INFO...
alter table SH_ROLE_INFO enable all triggers;
prompt Enabling triggers for SH_ROLE_MENU...
alter table SH_ROLE_MENU enable all triggers;
prompt Enabling triggers for SH_SELLGOODS_LOG...
alter table SH_SELLGOODS_LOG enable all triggers;
prompt Enabling triggers for SH_TERM_CONFIG...
alter table SH_TERM_CONFIG enable all triggers;
prompt Enabling triggers for SH_TERM_GROUP...
alter table SH_TERM_GROUP enable all triggers;
prompt Enabling triggers for SH_TERM_MODEL...
alter table SH_TERM_MODEL enable all triggers;
prompt Enabling triggers for SH_UNIONFILE_LOG...
alter table SH_UNIONFILE_LOG enable all triggers;
prompt Enabling triggers for SH_UNIONPAY_LOG...
alter table SH_UNIONPAY_LOG enable all triggers;
prompt Enabling triggers for SH_UNIONRECORD_LOG...
alter table SH_UNIONRECORD_LOG enable all triggers;



-------------------------------------------------------
set feedback on
set define on
prompt Done.
