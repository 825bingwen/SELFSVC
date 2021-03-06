prompt PL/SQL Developer import file
prompt Created on 2011年6月13日 by user
set feedback off
set define off
prompt Loading SH_ALARM_BIND...
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, OPERID, STATUS)
values ('1000001', 'yanqiang2', 'admin', null);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, OPERID, STATUS)
values ('1000000004', 'yanqiang2', 'p0000002', null);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, OPERID, STATUS)
values ('1000000020', '1', 'gaoqun', 1);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, OPERID, STATUS)
values ('1000000005', 'yanqiang', 'p0000001', 1);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, OPERID, STATUS)
values ('1000000060', 'g00140516', 'gaoqun', 1);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, OPERID, STATUS)
values ('1000000081', 'xuguowei', 'gaoqun', 1);
insert into SH_ALARM_BIND (ALARMBINDID, TERMID, OPERID, STATUS)
values ('1000000080', 'g00140516', 'p0000002', 1);
commit;
prompt 7 records loaded
prompt Loading SH_ALARM_INFO...
insert into SH_ALARM_INFO (ALARMINFOID, ALARMDATE, EXCEPTIONTYPE, TERMID, ALARMLEVEL, UNCHAINDATE, PERSISTDEGREE, DEALER, DEALTIME, DEALDESP, UNCHAIN)
values ('1000000082', to_date('29-04-2011 15:04:42', 'dd-mm-yyyy hh24:mi:ss'), '399', 'zp', 2, null, 1, null, null, null, 0);
insert into SH_ALARM_INFO (ALARMINFOID, ALARMDATE, EXCEPTIONTYPE, TERMID, ALARMLEVEL, UNCHAINDATE, PERSISTDEGREE, DEALER, DEALTIME, DEALDESP, UNCHAIN)
values ('1000000080', to_date('29-04-2011 16:04:41', 'dd-mm-yyyy hh24:mi:ss'), '399', 'g00140516', 1, null, 1, null, null, null, 0);
insert into SH_ALARM_INFO (ALARMINFOID, ALARMDATE, EXCEPTIONTYPE, TERMID, ALARMLEVEL, UNCHAINDATE, PERSISTDEGREE, DEALER, DEALTIME, DEALDESP, UNCHAIN)
values ('1000000081', to_date('29-04-2011 11:04:41', 'dd-mm-yyyy hh24:mi:ss'), '399', 'yanqiang', 3, null, 1, null, null, null, 0);
insert into SH_ALARM_INFO (ALARMINFOID, ALARMDATE, EXCEPTIONTYPE, TERMID, ALARMLEVEL, UNCHAINDATE, PERSISTDEGREE, DEALER, DEALTIME, DEALDESP, UNCHAIN)
values ('1000000083', to_date('15-04-2011 11:04:42', 'dd-mm-yyyy hh24:mi:ss'), '399', 'xuguowei', 3, null, 1, null, null, null, 0);
commit;
prompt 4 records loaded
prompt Loading SH_ALARM_RULE...
insert into SH_ALARM_RULE (ALARMRULEID, EXCEPTIONTYPE, STARTNUM, ENDNUM, STATUS, NOTES, STATUSDATE, CREATEOPERATOR)
values ('1000000129', 'ALL', '60', '120', 1, '未处理异常', to_date('29-04-2011 15:26:49', 'dd-mm-yyyy hh24:mi:ss'), 'admin');
insert into SH_ALARM_RULE (ALARMRULEID, EXCEPTIONTYPE, STARTNUM, ENDNUM, STATUS, NOTES, STATUSDATE, CREATEOPERATOR)
values ('1000000181', '301', '30', '60', 1, 'aaaa', to_date('07-04-2011 11:36:32', 'dd-mm-yyyy hh24:mi:ss'), 'admin');
commit;
prompt 2 records loaded
prompt Loading SH_DICT_ITEM...
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('ALL', '未处理异常', 'TermStatus', 30, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('1', '发票缺纸、换新发票纸', 'dealdesp', 1, 1, to_date('23-03-2011 11:29:08', 'dd-mm-yyyy hh24:mi:ss'), '告警处理方法');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('2', '小票缺纸、换新热敏纸', 'dealdesp', 2, 1, to_date('23-03-2011 11:29:08', 'dd-mm-yyyy hh24:mi:ss'), '告警处理方法');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('0', '正常', 'TermStatus', 0, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('399', '15分钟内未上传状态', 'TermStatus', 21, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('1', '普通', 'alarmLevel', 1, 1, to_date('23-03-2011 11:28:24', 'dd-mm-yyyy hh24:mi:ss'), '告警级别最低级');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('2', '严重', 'alarmLevel', 2, 1, to_date('23-03-2011 11:28:48', 'dd-mm-yyyy hh24:mi:ss'), '告警级别中级');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('3', '紧急', 'alarmLevel', 3, 1, to_date('23-03-2011 11:29:08', 'dd-mm-yyyy hh24:mi:ss'), '告警级别最高级');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_REC_MAIN_FEE', '手机支付主账户充值', 'OperationType', 42, 1, to_date('12-10-2010', 'dd-mm-yyyy'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryMonthFee', '月初扣费查询', 'OperationType', 7, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQrySubsFavourable', '已开通优惠查询', 'OperationType', 11, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQrySubsSP', '已定制SP查询', 'OperationType', 14, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHCancelSubsSP', '梦网业务查询退订', 'OperationType', 24, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryProductFee', '本机品牌资费', 'OperationType', 17, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryCurrentStatus', '当前状态查询', 'OperationType', 18, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQrySubsPackage', '套餐信息查询', 'OperationType', 19, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQrySubsRecHistory', '受理历史查询', 'OperationType', 20, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHSell', '自助售货', 'OperationType', 41, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('fireFox', 'fireFox', 'BrowserType', 1, 1, to_date('01-04-2011 09:51:04', 'dd-mm-yyyy hh24:mi:ss'), 'linux下使用的浏览器');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('IE', 'IE', 'BrowserType', 0, 1, to_date('28-04-2011 16:52:32', 'dd-mm-yyyy hh24:mi:ss'), 'windows操作系统下使用的浏览器');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHChooseTelNum', '自助选号', 'OperationType', 27, 0, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('314', '写本地日志失败', 'TermStatus', 14, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('313', '识币器钱箱被夹', 'TermStatus', 13, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('305', '设备初始化失败', 'TermStatus', 5, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('304', '端口故障', 'TermStatus', 4, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('303', '设备底层驱动程序未安装', 'TermStatus', 3, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('302', '打印机故障', 'TermStatus', 2, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('301', '票据打印缺纸', 'TermStatus', 1, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('315', '控件初始化失败', 'TermStatus', 15, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('312', '识币器入口被夹', 'TermStatus', 12, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('311', '识币器钱箱打开', 'TermStatus', 11, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('310', '识币器钱箱满', 'TermStatus', 10, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('309', '识币器故障', 'TermStatus', 9, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('308', '识币器初始化失败', 'TermStatus', 8, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('307', '加密键盘失败', 'TermStatus', 7, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('306', '发票打印缺纸', 'TermStatus', 6, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('319', '与银联通信故障', 'TermStatus', 19, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('318', '键盘故障', 'TermStatus', 18, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('317', '读卡器故障', 'TermStatus', 17, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('316', 'POS机故障', 'TermStatus', 16, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('320', '其它状态', 'TermStatus', 20, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), '自助终端状态');
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHInvoiceData', '取发票数据', 'OperationType', 0, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHVerifyPWD', '验证服务密码', 'OperationType', 0, 1, to_date('25-05-2011 10:44:39', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHChkPhoneNo', '验证手机号', 'OperationType', 8, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryBilItem', '月账单查询', 'OperationType', 1, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryMuster', '月详单查询', 'OperationType', 4, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHGetValidateCode', '生成短信随机码', 'OperationType', 2, 1, to_date('25-05-2011 10:44:54', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHValidateResult', '验证短信随机码', 'OperationType', 3, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQrySubsServList', '现有功能查询', 'OperationType', 12, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHPukQuery', 'PUK码查询', 'OperationType', 15, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHGetUserRegion', '号码归属地查询', 'OperationType', 16, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryBalance', '账户余额查询', 'OperationType', 5, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryValidScore', '积分查询', 'OperationType', 13, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHQryPayHistory', '缴费历史查询', 'OperationType', 6, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHChgSubsPwd', '密码修改', 'OperationType', 26, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHChgBalanceUrge', '设置余额提醒值', 'OperationType', 23, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHCallTransfer', '呼叫转移', 'OperationType', 29, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHCallDisplay', '主叫显示', 'OperationType', 28, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHGPRSServ', '手机上网', 'OperationType', 35, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHIncomingAwake', '移动全时通', 'OperationType', 38, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHstopOpen', '停开机', 'OperationType', 25, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_PAYBYCARD', '银联卡缴费', 'OperationType', 11, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_PAYBYCASH', '现金缴费', 'OperationType', 9, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHAbate', '优惠打折信息', 'OperationType', 39, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHProvide', '供货管理', 'OperationType', 40, 1, to_date('23-08-2010 16:35:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_DICT_ITEM (DICTID, DICTNAME, GROUPID, SORTORDER, STATUS, STATUSDATE, DESCRIPTION)
values ('SHBillSend', '账单寄送', 'OperationType', 43, 1, to_date('25-05-2011 10:43:52', 'dd-mm-yyyy hh24:mi:ss'), null);
commit;
prompt 66 records loaded
prompt Loading SH_GROUP_INFO...
insert into SH_GROUP_INFO (REGION, TERMGROUPID, TERMGROUPNAME, STATUS, STATUSDATE, ORGID, NOTES)
values (999, '1000000061', '省级', 1, to_date('02-04-2011 13:45:48', 'dd-mm-yyyy hh24:mi:ss'), 'SD', '省级，不可删');
insert into SH_GROUP_INFO (REGION, TERMGROUPID, TERMGROUPNAME, STATUS, STATUSDATE, ORGID, NOTES)
values (531, '1000000062', '济南地区', 1, to_date('02-04-2011 13:46:17', 'dd-mm-yyyy hh24:mi:ss'), 'SD.LA', '不可删');
insert into SH_GROUP_INFO (REGION, TERMGROUPID, TERMGROUPNAME, STATUS, STATUSDATE, ORGID, NOTES)
values (531, '1000000063', '历城区', 1, to_date('02-04-2011 15:42:00', 'dd-mm-yyyy hh24:mi:ss'), 'SD.LA.07', '不可删');
insert into SH_GROUP_INFO (REGION, TERMGROUPID, TERMGROUPNAME, STATUS, STATUSDATE, ORGID, NOTES)
values (531, '1000000064', '历下区', 1, to_date('02-04-2011 13:46:52', 'dd-mm-yyyy hh24:mi:ss'), 'SD.LA.0a', '不可删');
insert into SH_GROUP_INFO (REGION, TERMGROUPID, TERMGROUPNAME, STATUS, STATUSDATE, ORGID, NOTES)
values (999, '10000000000009', 'aaa', 1, to_date('27-04-2011 17:15:10', 'dd-mm-yyyy hh24:mi:ss'), 'SD', 'aa');
commit;
prompt 5 records loaded
prompt Loading SH_GROUP_MENU...
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'root', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryBill', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryBillItem', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryAllMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryGsmMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryImsgMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qrySmsMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryWlanMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryGprsMuster', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryBalance', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryFeeHistory', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryMonthFee', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'charge', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'feeCharge', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'cashcharge', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'cardcharge', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'mainAccoutCharge', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'cashchargemain', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryService', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryServiceQry', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryFavourable', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryScore', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryMonternet', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryPukQry', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'telNumInput', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryProductFee', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryCurrentStatus', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qrySerPri', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryServiceHistory', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'baseService', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recPhoneFeeAwake', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recPasswordReset', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recChgBalanceUrge', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recQrySPinfo', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recStopOpen', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recChangePwd', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'rec', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'install', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recCallDisplay', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recCallTransfer', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recColorfulRing', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recFlyMessage', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recMessageReturn', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recMusicOrdinarilyMem', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recMusicAdvancedMem', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recGPRS', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recMsgTransfer', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recBookManager', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'recIncomingAwake', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'favourableAbate', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'bursePay', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'freePay', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'selfSell', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'sellGoodsBursePay', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'sellGoodsScorePay', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'commonpay', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'electricFee', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'payment', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'mobilePayment', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryArrear', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryMonthBill', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'waterFee', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'coalGasFee', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'catvFee', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'selfRecTaste', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'chooseTel', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000009', 'root', 1, to_date('27-04-2011 17:15:23', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000009', 'qryBill', 1, to_date('27-04-2011 17:15:23', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000009', 'qryMuster', 1, to_date('27-04-2011 17:15:23', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000009', 'qryAllMuster', 1, to_date('27-04-2011 17:15:23', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000009', 'qryGsmMuster', 1, to_date('27-04-2011 17:15:23', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('10000000000009', 'qrySmsMuster', 1, to_date('27-04-2011 17:15:23', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'qryArrearHub', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_GROUP_MENU (TERMGRPID, MENUID, STATUS, STATUSDATE)
values ('1000000063', 'cardPay', 1, to_date('12-04-2011 10:05:17', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 75 records loaded
prompt Loading SH_INFO_MANUFACTURER...
insert into SH_INFO_MANUFACTURER (MANUFACTURERID, MANUFACTURERNAME, MANUFACTURERADDRESS, PHONENUMBER, OPERID, CREATEDATE, RECDATE)
values ('ZdSelfTermXP', '正德科技', '重市市花园路北口', '13533334444', 'admin', to_date('02-03-2011 17:26:56', 'dd-mm-yyyy hh24:mi:ss'), to_date('06-04-2011 10:53:25', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 1 records loaded
prompt Loading SH_INFO_OPERATOR...
insert into SH_INFO_OPERATOR (OPERID, REGION, OPERNAME, PASSWORD, PASSCHGDATE, OPERGROUP, OPERTYPE, OPERLEVEL, ISMANAGER, CONTACTPHONE, ORGID, ISRESTRICT, STARTTIME, ENDTIME, ENABLEGPRS, GPRSSTARTTIME, GPRSENDTIME, ISCHKMAC, MAC, NOTES, CREATEDATE, STATUS, STATUSDATE, RELE_STAFF_ID, START_USING_TIME, END_USING_TIME, LOGINTYPE, AREAID, ROLEID)
values ('admin', 999, '系统管理员', '96e79218965eb72c92a549dd5a330112', to_date('25-05-2011 10:41:25', 'dd-mm-yyyy hh24:mi:ss'), null, 'OPRT31', null, 1, '13645319981', 'SD', 0, 0, 0, 0, 0, 0, 0, '00-10-A4-B8-DF-C0', '不可删除、修改', to_date('10-11-2005 18:23:50', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '00000000', to_date('20-10-2005', 'dd-mm-yyyy'), to_date('20-10-2038', 'dd-mm-yyyy'), null, null, '10000000006436');
commit;
prompt 1 records loaded
prompt Loading SH_INFO_PLUGIN...
insert into SH_INFO_PLUGIN (PROVIDERID, BROWSERTYPE, SERVIPADDR, FTPUSERNAME, FTPPWD, PRINTERVER, PRTFTPADDR, PRTPLUGINFLAG, INVPRINTERVER, INVPRTFTPADDR, INVPRTPLUGINFLAG, KEYBOARDVER, KEYBRDFTPADDR, KEYBRDPLUGINFLAG, CASHVER, CASHFTPADDR, CASHPLUGINFLAG, CARDVER, CARDFTPADDR, CARDPLUGINFLAG, MANAGERVER, MGRFTPADDR, MGRPLUGINFLAG, SOCKETPORT, MOVECARDVER, MOVECARDADDR, MOVECARDFLAG, WRITECARDVER, WRITECARDADDR, WRITECARDFLAG, IDCARDVER, IDCARDADDR, IDCARDFLAG, UNIONVER, UNIONFTPADDR, UNIONPLUGINFLAG, PURSEVER, PURSEFTPADDR, PURSEPLUGINFLAG, SELLGOODSVER, SELLGOODSFTPADDR, SELLGOODSPLUGINFLAG)
values ('ZdSelfTermXP', 'IE', '133.96.64.31', 'selfsvc', 'selfsvc2009', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:EE47DC39-0A5D-4926-B26A-A21BEA8D757D', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:562C7EA3-180B-45B6-98F9-190A304091D8', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:47B4E067-4F5A-4CA9-909C-03AE497CF42E', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:9B99BEBC-F760-4630-8686-9C652AB50E24', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:A8064A9B-C5F9-4C5F-9F7D-EC397248D3C8', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:90B097CF-4A8F-47B8-B7C2-166153FB4155', '6000', null, null, null, null, null, null, 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:279D4362-AE40-40D6-BB18-90CDFEB9AFF3', 'hwsd1-1-1', '/plugins/zhengde/xp', 'clsid:7AE7497B-CAD8-4E66-A58B-DDE9BCAF6B61', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:8E35C67E-7AAE-4404-A91C-6F0719915845', 'hwsd1-1-1', '/plugins/zhengde/xp', 'CLSID:E7FA3F16-3F80-4880-AE17-634021B1DB8E');
insert into SH_INFO_PLUGIN (PROVIDERID, BROWSERTYPE, SERVIPADDR, FTPUSERNAME, FTPPWD, PRINTERVER, PRTFTPADDR, PRTPLUGINFLAG, INVPRINTERVER, INVPRTFTPADDR, INVPRTPLUGINFLAG, KEYBOARDVER, KEYBRDFTPADDR, KEYBRDPLUGINFLAG, CASHVER, CASHFTPADDR, CASHPLUGINFLAG, CARDVER, CARDFTPADDR, CARDPLUGINFLAG, MANAGERVER, MGRFTPADDR, MGRPLUGINFLAG, SOCKETPORT, MOVECARDVER, MOVECARDADDR, MOVECARDFLAG, WRITECARDVER, WRITECARDADDR, WRITECARDFLAG, IDCARDVER, IDCARDADDR, IDCARDFLAG, UNIONVER, UNIONFTPADDR, UNIONPLUGINFLAG, PURSEVER, PURSEFTPADDR, PURSEPLUGINFLAG, SELLGOODSVER, SELLGOODSFTPADDR, SELLGOODSPLUGINFLAG)
values ('ZdSelfTermXP', 'fireFox', '192.168.1.72', 'yanqiang', 'yanqiang', '1', 'ZdSelfTermXPfireFox', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '6000', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
insert into SH_INFO_PLUGIN (PROVIDERID, BROWSERTYPE, SERVIPADDR, FTPUSERNAME, FTPPWD, PRINTERVER, PRTFTPADDR, PRTPLUGINFLAG, INVPRINTERVER, INVPRTFTPADDR, INVPRTPLUGINFLAG, KEYBOARDVER, KEYBRDFTPADDR, KEYBRDPLUGINFLAG, CASHVER, CASHFTPADDR, CASHPLUGINFLAG, CARDVER, CARDFTPADDR, CARDPLUGINFLAG, MANAGERVER, MGRFTPADDR, MGRPLUGINFLAG, SOCKETPORT, MOVECARDVER, MOVECARDADDR, MOVECARDFLAG, WRITECARDVER, WRITECARDADDR, WRITECARDFLAG, IDCARDVER, IDCARDADDR, IDCARDFLAG, UNIONVER, UNIONFTPADDR, UNIONPLUGINFLAG, PURSEVER, PURSEFTPADDR, PURSEPLUGINFLAG, SELLGOODSVER, SELLGOODSFTPADDR, SELLGOODSPLUGINFLAG)
values ('aaa', 'IE', '192.168.10.174', 'yanqiang', 'yanqiang', '111', 'aaaIE', '1', '123', 'aaaIE', '33', '3w', 'aaaIE', 'wew', null, null, null, null, null, null, null, null, null, '1234', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
commit;
prompt 3 records loaded
prompt Loading SH_MENU_ITEM...
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qrySmsMuster', '短信详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=2', '提供5+1短信清单查询', 2, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryWlanMuster', 'WLAN详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=6', '提供5+1WLAN话单查询', 5, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
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
values ('recIncomingAwake', '移动全时通', 'rec', 'privilege/privilege.action?nCode=C06', '办理移动全时通开通和退订业务', 12, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '移动全时通是指手机关机或不在服务区时，它能将在这期间的所有来电转移到移动全时通平台，当在打开手机或者手机恢复正常后，将会收到关机或者不在服务区期间漏接来话的短信息。基本资费：3元/月<br/>全时通业务开通、取消均立即生效。', null, 'images/can/service_icon14.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recCallDisplay', '主叫显示', 'rec', 'privilege/privilege.action?nCode=H01', '办理主叫显示开通和退订业务', 2, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '让您的手机显示来电号码，已接或未接的来电号码会自动存储在手机上。<br/>标准资费：后付费客户功能费3元/月；预付费客户功能费0.1元/天。神州行经典卡暂时不收取来电显示费。', null, 'images/can/service_icon13.png');
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
values ('recChgBalanceUrge', '余额提醒', 'rec', 'baseService/chgBalanceUrgePage.action', '设置余额提醒值业务受理', 1, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/can/service_icon15.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recStopOpen', '停开机', 'rec', 'baseService/commonServPage.action?sType=SHstopOpen&isInput=0', '办理停开机申请和取消业务', 5, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/can/service_icon12.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recGPRS', '手机上网', 'rec', 'privilege/privilege.action?nCode=Z07', '办理GPRS开通和退订业务', 9, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, 'GPRS是一种无线上网方式，被称为第2.5代移动通信技术，具有永远在线、按流量计费、话音数据自由切换等特点。<br/>' || chr(10) || 'GPRS功能与GPRS套餐的关系：GPRS功能一般在您的号码开户时默认开通，GPRS套餐需要申请开通。开通GPRS套餐时，系统将自动为您开通GPRS功能，但取消GPRS套餐时不会取消GPRS功能。取消GPRS功能后，已开通的GPRS套餐也将一并失效，再次开通GPRS功能后需要重新选择开通GPRS套餐，否则按基本资费0.01元/KB计费。<br/>基本资费：开通了GPRS功能而未开通GPRS套餐的客户，基本资费为0.01元/KB。如果您每月使用GPRS流量较多，建议开通适当档次的GPRS套餐。', null, 'images/can/service_icon2.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recQrySPinfo', '业务查询退订', 'rec', 'baseService/querySP.action', '办理梦网业务退订业务', 4, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/can/service_icon16.png');
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
values ('qryBalance', '账户余额查询', 'qryBill', 'hubfeeservice/qryAccBalance.action', '余额查询', 15, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryFeeHistory', '缴费历史查询', 'qryBill', 'feeservice/chargeHistory.action', '可查询近6个月(含当前月)的缴费记录', 20, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '为您提供6个月内的缴费历史记录，包括缴费时间，缴费方式，缴费金额。', null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryMonthFee', '月初扣费查询', 'qryBill', 'feeservice/monthDeduct.action', '查询月初扣费信息', 30, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryGsmMuster', '通话详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=1', '提供5+1语音清单查询', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryImsgMuster', '移动梦网详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=4', '提供5+1梦网话单查询', 3, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryCurrentStatus', '当前状态查询', 'qryService', 'serviceinfo/qryCurrentStatus.action', '查询手机的当前服务状态', 8, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qrySerPri', '套餐信息查询', 'qryService', 'comboInfo/qryComboInfo.action', '当前在用的套餐使用情况', 9, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryServiceHistory', '受理历史查询', 'qryService', 'serviceinfo/qryServiceHistoryInput.action', '查询用户办理过的所有业务的历史记录', 10, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('chooseTel', '选号开户', 'root', 'chooseTel/selectRegion.action?bz=1', '为您提供自助选号服务', 3, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/index_pic3.png', 999, 'ALL', '0000', 1, null, 'C', 'images/nav_3_logo.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('root', '欢迎使用自助终端', '-1', 'login/index.action', '自助终端根菜单', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 0, null, null, null);
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryGprsMuster', 'GPRS详单', 'qryMuster', 'feeservice/qryDetailedRecords.action?listtype=5', '提供5+1GPRS清单查询', 4, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 3, null, null, null);
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
values ('mainAccoutCharge', '主账户充值', 'root', 'mpaycharge/cmpay.action', '为您提供手机支付主账户充值服务', 10, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/index_bt8.png', 999, 'ALL', '0000', 1, null, 'BR', 'images/nav_10_logo.png');
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
values ('selfRecTaste', '移动业务体验', 'root', 'jsp/SelfMedia/SelfRecTaste/SelfRecTaste.jsp', '为您提供移动业务体验服务', 7, to_date('16-05-2009', 'dd-mm-yyyy'), 0, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/index_bt7.png', 999, 'ALL', '0000', 1, null, 'BR', 'images/nav_8_logo.png');
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
values ('recPasswordReset', '密码重置', 'rec', 'baseService/passwordReset.action', '提供密码重置功能', 7, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '0000', 2, '如果您没有设置过服务密码或忘记服务密码，可以通过此功能重置服务密码。<br/>服务密码为6位数字。为了保障您个人信息的安全，请不要设置为有序数字、相同数字等。<br/>身份证号为15位或18位数字，号码中带有X的用户不能通过自助终端重置服务密码。', null, 'images/can/service_icon17.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryArrearHisHub', '欠费历史查询', 'qryBill', 'hubfeeservice/qryArrearHub.action', '查询用户欠费历史记录', 35, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, null, null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('recBillSend', '账单寄送', 'rec', 'baseService/billSendPage.action', '办理账单寄送服务', 13, to_date('18-05-2009', 'dd-mm-yyyy'), 1, to_date('18-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '可通过短息，彩信和邮箱的方法寄送账单', null, 'images/can/service_icon18.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('cardPay', '充值卡缴费', 'root', 'freecharge/cardPay.action', '为您提供充值卡缴费服务', 6, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), 'images/index_bt8.png', 999, 'ALL', '0000', 1, null, 'BR', 'images/nav_10_logo.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryBillItemByCust', '月账单查询(客户)', 'qryBill', 'hubfeeservice/monthBill.action?qryType=1', '可按照客户查询近6个月(含当前月)的明细账单', 1, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '综合账单查询功能，可查询本月和前五个月的话费。', null, 'images/bg_bill_02.png');
insert into SH_MENU_ITEM (MENUID, MENUNAME, PARENTID, GUIOBJ, TIPTEXT, SORTORDER, CREATEDATE, STATUS, STATUSDATE, IMGPATH, REGION, BRANDID, AUTHCODE, MENULEVEL, BUSIDETAIL, POSITION, IMGPATH2)
values ('qryBillItemByAcct', '月账单查询(账户)', 'qryBill', 'hubfeeservice/monthBill.action?qryType=0', '可按照账户查询近6个月(含当前月)的明细账单', 5, to_date('16-05-2009', 'dd-mm-yyyy'), 1, to_date('16-05-2009', 'dd-mm-yyyy'), null, 999, 'ALL', '1000', 2, '综合账单查询功能，可查询本月和前五个月的话费。', null, 'images/bg_bill_02.png');
commit;
prompt 71 records loaded
prompt Loading SH_MENU_ITEM_MGR...
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_OperMgr', 'SH_SysData', null, '操作员管理', '操作员配置管理', 'operator/operatorQry.action', 4, '-1', 'SH_OperMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '操作员配置管理', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
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
values ('SH_SelfHelp', 'SH_PaymentLog', 'SH_SelfHelp', null, '支付日志查询', '支付日志查询', null, 8, '-1', 'SH_SellGoodsMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '支付日志查询', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_PurseLog', 'SH_PaymentLog', null, '手机钱包消费日志查询', '手机钱包日志查询', 'userPayLogQry/purseLogPage.action', 3, '-1', 'SH_SellGoodsMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '手机钱包日志查询', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
insert into SH_MENU_ITEM_MGR (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, APPENDINFO, TABTYPE, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, MENUTYPE, EMERGENCYID, MUTEXFLAG, HELPLINK, INTERFACEOBJECT)
values ('SH_SelfHelp', 'SH_ChargeLog', 'SH_PaymentLog', null, '话费充值缴纳日志查询', '话费充值缴纳日志查询', 'userPayLogQry/chargeLogPage.action', 1, '-1', 'SH_ChargeLog', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '话费充值缴纳日志查询', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
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
values ('SH_SelfHelp', 'SH_AlarmBindMgr', 'SH_TermControlMgr', null, '告警处理人设置', '告警处理人设置', 'alarmBind/initQryPage.action', 3, '-1', 'SH_AlarmBindMgr', null, '-1', null, '1', null, null, null, null, null, null, null, null, null, 0, '告警处理人设置', '32', 1, 1, null, null, null, null, null, 'WEB', '1111', '0', null, null);
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
commit;
prompt 54 records loaded
prompt Loading SH_PARAM...
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CDR_PRTTIMES', '清单打印次数限制', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_RETURN_HOMEPAGE', '从其它页面返回首页面的时间，单位毫秒', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHOOSETEL_IDCARD_TIME', '身份证选号次数', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('DEFAULT_PAGE_SIZE', '设置后台分页中每页条数', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_OCX_CHECK_FREQUENCY', '小票打印机与发票打印机检测频率(1:1次 0:N次)', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_REC_TASTE_URL', '业务体验URL', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL_DEALTIME', '现金对账处理时间', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL', '现金对账开关(1：开启  0：关闭)', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL_PATH', '现金对帐文件存放路径', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_SOCKET_PORT', '自助终端终端机设备统一SOCKET端口(控制重启关机时用到)', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_UPLOAD_TIME', '自助终端终端机设备统一状态上传时间(单位:分钟)', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_WEBINTER_OUTOPERPWD', '连接WebService服务密码', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BALANCE', '自助终端设置余额提醒值', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOGFTPADDR', '自助终端上传日志的地址和端口,如192.168.0.1,21', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_SOCKADDR', '自助终端管理平台socket地址和端口,如192.168.0.1,8888', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_WELCOME_WIDTH', '自助终端首页资源宽度资源', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_WELCOME_HEIGHT', '自助终端首页资源高度资源', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_WELCOME_PATH', '自助终端首页资源地址资源', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_WELCOME_PATH_TMP', '自助终端首页资源临时地址', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_WEBSERVICE', 'WebService服务地址', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_DUTY_PROVINCE', '银行卡缴费负责申明省份', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_PAYMONEY_TIME', '现金缴费投币等待时间(秒)', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_RETURNIDX_TIME', '缴费完成后返回主页面等待时间(秒)', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CAPTURECARD_TIME', '银联卡缴费吞卡等待时间(秒)', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_MAKESURE_TIME', '银联卡缴费缴费确认等待时间(秒)', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_INPUTMONEY_TIME', '银联卡缴费缴费金额输入等待时间(秒)', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_INPUTCARDPWD_TIME', '银联卡缴费密码输入等待时间(秒)', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_READCARD_TIME', '银联卡缴费读卡等待时间(秒)', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOCAL_SCWPL_LIST', '自助终端客户端屏保播放列表文件名', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOCAL_FILE_PATH', '自助终端客户端媒体文件地址', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOCAL_ADVWPL_LIST', '自助终端客户端广告播放列表文件名', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOCAL_SCPLAY_LIST', '自助终端客户端屏保本地媒体文件列表', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOCAL_ADVPLAY_LIST', '自助终端客户端广告本地媒体文件列表', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_GO_SCEEN_TIME', '自助终端客户端主面跳到屏保页等待时间', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL_FTPSERVER', '现金对帐FTP服务器地址', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL_USERNAME', '现金对帐FTP服务器用户名', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL_PASSWORD', '现金对帐FTP服务器密码', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOGIN_TIMESCOPE', '密码连续输入错误的时间范围，单位分钟', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOGIN_MAXTIMES', '密码连续输入错误的最大次数', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOGIN_LOCKEDTIME', '号码被锁定的时间，单位分钟', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_UPLOADOCX_TMPPATH', '控件上传存放的临时目录', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_RANDOMPWD_LEN', '自助终端系统使用的随机密码的长度', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_RANDOMPWD_VALIDITY', '自助终端系统使用的随机密码的有效期，单位分钟', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_RANDOMPWD_CONTENT', '随机密码短信内容', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BACKPAT_CODE', '乐付网缴费冲正的功能码', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BACKPAT_URL', '乐付网缴费冲正的URL地址', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_LOG4J_LEVEL', 'log4j的日志级别', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_UNIONPAY_PASSWORD', '银联对账文件存放的ftp的密码', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_UNIONPAY_USERNAME', '银联对账文件存放的ftp的用户名', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_UNIONPAY_FTPSERVER', '银联对账文件存放的ftp地址', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_UNIONPAY_SUBDIRECTORY', '银联对账文件存放的目录', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL_REPEAT', '缴费对账重试次数。', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_CHECKBILL_INTERVAL', 'boss对帐重试之间的时间间隔，单位：分钟', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BUSINESS_DEALTIME', '交易明细上传时间', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BUSINESS', '交易明细对账开关(1：开启  0：关闭)', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BUSINESS_TMPPATH', '交易明细文件临时存放路径', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BUSINESS_REPEAT', '交易对账重试次数。', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BUSINESS_INTERVAL', '交易对帐重试之间的时间间隔，单位：分钟', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_BUSINESS_FTPPATH', '交易明细文件FTP存放路径', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_PRTINVOICE_RANDOMPWD', '发票打印时是否需要随机码', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SH_PARAM (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_PRTINVOICE_RANDOMPWD_CONTENT', '打印发票时，随机密码短信内容', 'String', 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-08-2010 16:52:48', 'dd-mm-yyyy hh24:mi:ss'), null);
commit;
prompt 61 records loaded
prompt Loading SH_PARAM_VALUE...
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_GO_SCEEN_TIME', '120', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CDR_PRTTIMES', '1', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_RETURN_HOMEPAGE', '1201000', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BACKPAT_CODE', '0907', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BACKPAT_URL', 'http://218.57.146.190/presys/servlet/ZhuoWangServlet', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHOOSETEL_IDCARD_TIME', '4', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('DEFAULT_PAGE_SIZE', '20', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_OCX_CHECK_FREQUENCY', '1', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_REC_TASTE_URL', 'http://218.206.81.78/wwwpps/loginServlet?method=doLogin&branchId=null&rememberURL=/z/sdDFT/index.html&username=053103400&password=111qqq$', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL_DEALTIME', '11:40', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL', '1', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL_PATH', 'C:/checkbill/', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_SOCKET_PORT', '6000', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_UPLOAD_TIME', '10', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_WEBINTER_OUTOPERPWD', '12345678', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BALANCE', '100000', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOGFTPADDR', '10.131.74.12,21', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_SOCKADDR', '10.13.232.14,6666', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_WELCOME_HEIGHT', '600px', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_WELCOME_WIDTH', '500px', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_WELCOME_PATH', './images/welcome/cmcc_txtnew.swf', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_WELCOME_PATH_TMP', './images/welcome/cmcc_txtnew_tmp.swf', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_WEBSERVICE', 'http://10.164.130.108:8080/axis/SelfSvcTest.jws?wsdl', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_DUTY_PROVINCE', '山东', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_PAYMONEY_TIME', '10', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_READCARD_TIME', '30', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_INPUTCARDPWD_TIME', '30', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_INPUTMONEY_TIME', '120', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_MAKESURE_TIME', '60', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CAPTURECARD_TIME', '30', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_RETURNIDX_TIME', '20', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOCAL_ADVPLAY_LIST', 'advfile.xml', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOCAL_SCPLAY_LIST', 'scfile.xml', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOCAL_FILE_PATH', 'D:/media', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOCAL_SCWPL_LIST', 'sc.wpl', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOCAL_ADVWPL_LIST', 'adv.wpl', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOGIN_TIMESCOPE', '15', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL_PASSWORD', 'yanqiang', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL_USERNAME', 'yanqiang', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL_FTPSERVER', '192.168.0.22', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOGIN_MAXTIMES', '3', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOGIN_LOCKEDTIME', '60', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_UPLOADOCX_TMPPATH', 'D:/ocxTmp', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_RANDOMPWD_LEN', '6', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_RANDOMPWD_VALIDITY', '5', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_RANDOMPWD_CONTENT', '您的自助终端随机密码是：[%1]，请输入系统。该密码仅限本次使用。', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_LOG4J_LEVEL', 'INFO', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_UNIONPAY_FTPSERVER', '192.168.1.72', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_UNIONPAY_USERNAME', 'yanqiang', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_UNIONPAY_PASSWORD', 'yanqiang', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_UNIONPAY_SUBDIRECTORY', 'sdbcn', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL_REPEAT', '3', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_CHECKBILL_INTERVAL', '2', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BUSINESS_DEALTIME', '11:05:00', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BUSINESS', '1', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BUSINESS_TMPPATH', 'C:/business/', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BUSINESS_REPEAT', '3', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BUSINESS_INTERVAL', '2', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_BUSINESS_FTPPATH', 'business', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_PRTINVOICE_RANDOMPWD', '1', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_PARAM_VALUE (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_PRTINVOICE_RANDOMPWD_CONTENT', '您在移动公司的自助终端上打印发票，您的发票打印短信验证码是:[%1]', 1, to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'), null, 'sys_oper', to_date('23-08-2010 16:55:07', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 61 records loaded
prompt Loading SH_ROLE_INFO...
insert into SH_ROLE_INFO (ROLEID, ROLENAME, CREATEDATE, CREATEOPERATOR, STATUS, NOTES)
values ('10000000006436', '开发使用', to_date('25-05-2011 10:39:47', 'dd-mm-yyyy hh24:mi:ss'), 'admin', 1, '不可以删除，不可以修改');
commit;
prompt 1 records loaded
prompt Loading SH_ROLE_MENU...
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_SysData', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_RoleMgr', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_ChgPassword', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_DictMgr', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_OperMgr', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_TermInfo', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_ManufacturerInfo', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_MachineModel', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_TermBaseInfo', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_TermPluginInfoMgr', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_TermRightMgr', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_TermQuery', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_TermRecLogQuery', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_ManagerLog', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_LogFileInfoMgr', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_MediaPlay', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_ScreenProtectMgr', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_TermGroupScreenConfigMgr', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_TermControlMgr', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_RemoteControl', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_TermRuleMgr', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_AlarmBindMgr', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_TermControl', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_AlarmInfoMgr', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_PaymentLog', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_ChargeLog', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_MainPayLog', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_PurseLog', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_TermCashAudit', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_InvoicePrintLog', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_StatisticsMgr', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_CallChargeStat', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_MainAccStat', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_PhonePkgStat', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ROLE_MENU (ROLEID, MENUID, STATUS, STATUSDATE)
values ('10000000006436', 'SH_SelfHelp', 1, to_date('25-05-2011 10:40:37', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 35 records loaded
prompt Loading SH_TERM_CONFIG...
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE)
values ('g00140516', '高群', '192.168.10.159', '00-00-00-00-00-00', 'adZ200001', null, null, '银联商户号', '银联终端ID', 'IE', '011111000110', 'ZdSelfTermXP', 'SD.LA.07', '192.168.1.41,24444', to_date('02-04-2011 13:38:19', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-06-2011 14:19:48', 'dd-mm-yyyy hh24:mi:ss'), 531, '历城(SD.LA.07)', '1000000039', 'A0000001', null);
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE)
values ('zp', '赵鹏', '192.168.10.160', '90-FB-A6-0A-D8-26', 'adZ20001', null, null, null, null, 'IE', '111111111111', 'ZdSelfTermXP', 'SD.LA.07', '192.168.1.41,24444', to_date('13-04-2011 17:14:57', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('13-04-2011 17:24:30', 'dd-mm-yyyy hh24:mi:ss'), 531, '历城(SD.LA.07)', '1000000039', 'A000001', null);
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE)
values ('xuguowei', '徐国潍', '192.168.10.173', '00-00-00-00-00-00', 'adZ200001', null, null, null, null, 'IE', '111111000110', 'ZdSelfTermXP', 'SD.LA.07', '192.168.1.41,24444', to_date('02-04-2011 13:38:19', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-04-2011 15:32:54', 'dd-mm-yyyy hh24:mi:ss'), 531, '历城(SD.LA.07)', '1000000039', 'A0000001', null);
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE)
values ('y68902', '测试', '192.168.10.190', '00-00-00-00-00-00', 'adZ20001', null, null, null, null, 'IE', '100010000000', 'ZdSelfTermXP', 'SD.LA.07.01.1f', '192.168.1.41,24444', to_date('06-04-2011 16:37:40', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('28-04-2011 17:29:59', 'dd-mm-yyyy hh24:mi:ss'), 531, '历城-山大齐鲁软件学院营业厅(G3业务)(SD.LA.07.01.1f)', '1000000039', '1', null);
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE)
values ('gaoyudong', '高玉东', '192.168.10.162', '00-00-00-00-00-00', 'adZ20001', null, null, null, null, 'IE', '000010001000', 'ZdSelfTermXP', 'SD', '192.168.1.41,24444', to_date('27-04-2011 17:14:56', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-04-2011 17:23:25', 'dd-mm-yyyy hh24:mi:ss'), 999, '山东', '1000000039', 'aaa', null);
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE)
values ('lifeng', '李锋', '192.168.10.189', '00-00-00-00-00-00', 'adZ200001', null, null, null, null, 'IE', '111111000110', 'ZdSelfTermXP', 'SD.LA.07', '192.168.1.41,24444', to_date('02-04-2011 13:38:19', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('17-05-2011 14:13:06', 'dd-mm-yyyy hh24:mi:ss'), 531, '历城(SD.LA.07)', '1000000039', 'A0000001', null);
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE)
values ('chenhangcheng', '陈航程', '192.168.10.192', '00-00-00-00-00-00', 'adZ200001', null, null, null, null, 'IE', '111111000110', 'ZdSelfTermXP', 'SD.LA.07', '192.168.1.41,24444', to_date('02-04-2011 13:38:19', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('12-04-2011 09:43:40', 'dd-mm-yyyy hh24:mi:ss'), 531, '历城(SD.LA.07)', '1000000039', 'A0000001', null);
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE)
values ('zhangbo', '张博', '192.168.10.244', '00-00-00-00-00-00', 'adZ200001', null, null, null, null, 'IE', '111111000110', 'ZdSelfTermXP', 'SD.LA.07', '192.168.1.41,24444', to_date('02-04-2011 13:38:19', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('12-04-2011 09:43:40', 'dd-mm-yyyy hh24:mi:ss'), 531, '历城(SD.LA.07)', '1000000039', 'A0000001', null);
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE)
values ('jichengceshi', '持续集成测试', '192.168.10.185', '00-00-00-00-00-00', 'adZ200001', null, null, null, null, 'IE', '111111000110', 'ZdSelfTermXP', 'SD.LA.07', '192.168.1.41,24444', to_date('02-04-2011 13:38:19', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('12-04-2011 09:43:40', 'dd-mm-yyyy hh24:mi:ss'), 531, '历城(SD.LA.07)', '1000000039', 'A0000001', null);
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE)
values ('hanlili', '资料开发', '192.168.10.155', '00-00-00-00-00-00', 'adZ200001', null, null, null, null, 'IE', '111111000110', 'ZdSelfTermXP', 'SD.LA.07', '192.168.1.41,24444', to_date('02-04-2011 13:38:19', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('12-04-2011 09:43:40', 'dd-mm-yyyy hh24:mi:ss'), 531, '历城(SD.LA.07)', '1000000039', 'A0000001', null);
insert into SH_TERM_CONFIG (TERMID, TERMNAME, IPADDR, MAC, OPERID, PLANSIGNTIME, REALSIGNTIME, UNIONUSERID, UNIONPAYCODE, BROWSERTYPE, TERMSPECIAL, PROVIDERCODE, ORGID, SOCKADDR, CREATEDATE, STATUS, STATUSDATE, REGION, ORGNAME, MACHINEMODEL, TRMCODE, DESCRIBE)
values ('yanqiang', '闫强', '192.168.10.174', '00-00-00-00-00-00', 'adZ200001', null, null, null, null, 'IE', '111111000110', 'ZdSelfTermXP', 'SD.LA.07', '192.168.1.41,24444', to_date('02-04-2011 13:38:19', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-04-2011 15:32:54', 'dd-mm-yyyy hh24:mi:ss'), 531, '历城(SD.LA.07)', '1000000039', 'A0000001', null);
commit;
prompt 11 records loaded
prompt Loading SH_TERM_GROUP...
insert into SH_TERM_GROUP (TERMID, TERMGRPID, CREATEDATE, STATUS, STATUSDATE)
values ('zp', '1000000063', to_date('13-04-2011 17:24:41', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('13-04-2011 17:24:41', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_GROUP (TERMID, TERMGRPID, CREATEDATE, STATUS, STATUSDATE)
values ('yanqiang', '1000000063', to_date('02-04-2011 14:25:15', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('02-04-2011 14:25:15', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_GROUP (TERMID, TERMGRPID, CREATEDATE, STATUS, STATUSDATE)
values ('xuguowei', '1000000063', to_date('11-04-2011 17:17:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2011 17:17:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_GROUP (TERMID, TERMGRPID, CREATEDATE, STATUS, STATUSDATE)
values ('g00140516', '1000000063', to_date('12-04-2011 09:50:02', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('12-04-2011 09:50:02', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_GROUP (TERMID, TERMGRPID, CREATEDATE, STATUS, STATUSDATE)
values ('y68902', '1000000063', to_date('12-04-2011 09:50:02', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('12-04-2011 09:50:02', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_GROUP (TERMID, TERMGRPID, CREATEDATE, STATUS, STATUSDATE)
values ('lifeng', '1000000063', to_date('12-04-2011 09:50:02', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('12-04-2011 09:50:02', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_GROUP (TERMID, TERMGRPID, CREATEDATE, STATUS, STATUSDATE)
values ('chenhangcheng', '1000000063', to_date('12-04-2011 09:50:02', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('12-04-2011 09:50:02', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_GROUP (TERMID, TERMGRPID, CREATEDATE, STATUS, STATUSDATE)
values ('zhangbo', '1000000063', to_date('12-04-2011 09:50:02', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('12-04-2011 09:50:02', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_GROUP (TERMID, TERMGRPID, CREATEDATE, STATUS, STATUSDATE)
values ('gaoyudong', '1000000063', to_date('12-04-2011 09:50:02', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('12-04-2011 09:50:02', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_GROUP (TERMID, TERMGRPID, CREATEDATE, STATUS, STATUSDATE)
values ('jichengceshi', '1000000063', to_date('12-04-2011 09:50:02', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('12-04-2011 09:50:02', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_GROUP (TERMID, TERMGRPID, CREATEDATE, STATUS, STATUSDATE)
values ('hanlili', '1000000063', to_date('13-04-2011 17:24:41', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('13-04-2011 17:24:41', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 11 records loaded
prompt Loading SH_TERM_MODEL...
insert into SH_TERM_MODEL (TERMMODELID, TERMMODEL, MANUFACTURERID, DESCRIBE, STATUS, STATUSDATE)
values ('1000000039', 'ZD_0011', 'ZdSelfTermXP', '测试使用，不可进行任何操作', 1, to_date('14-04-2011 17:13:02', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_MODEL (TERMMODELID, TERMMODEL, MANUFACTURERID, DESCRIBE, STATUS, STATUSDATE)
values ('1000000040', 'ZD_002', 'ZdSelfTermXP', null, 1, to_date('11-03-2011 09:35:28', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_TERM_MODEL (TERMMODELID, TERMMODEL, MANUFACTURERID, DESCRIBE, STATUS, STATUSDATE)
values ('1000000041', 'ZD_00', 'ZdSelfTermXP', '003', 1, to_date('14-04-2011 17:12:35', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 3 records loaded
set feedback on
set define on
prompt Done.
