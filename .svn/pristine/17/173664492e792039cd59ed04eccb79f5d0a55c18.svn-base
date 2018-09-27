set feedback off
set define off
--PIN，PUK码查询（自助终端）.sql
prompt delete NetPukquery_Atsv...
delete from int_command_define where commandid='NetPukquery_Atsv';
delete from int_command_param where commandid='NetPukquery_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetPukquery_Atsv', 'PIN\PUK码查询（自助终端）', 1, 4, 'CIntCommonDispatcher', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '根据手机号码查询pin/puk码信息，自助终端调用', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetPukquery_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetPukquery_Atsv', 3, 2, 'PIN1=0&PIN2=1&PUK1=2&PUK2=3', '', '', '输出参数转换', 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetPukquery_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetPukquery_Atsv', 1, null, '', 'OPCODE', 'CCEIQrySimPuk', 'PIN\PUK码查询（自助终端）', null);
--按组取字典表数据.sql
prompt delete GetDictByGroup_Atsv...
delete from int_command_define where commandid='GetDictByGroup_Atsv';
delete from int_command_param where commandid='GetDictByGroup_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('GetDictByGroup_Atsv', '按组取字典表数据', 1, 4, 'CIntCommonDispatcher', to_date('26-01-2007', 'dd-mm-yyyy'), 1, to_date('26-01-2007 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '按组取字典表数据', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('GetDictByGroup_Atsv', 1, null, '', 'OPCODE', 'CCEIGetDictByGroup', '按组取字典表数据', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('GetDictByGroup_Atsv', 3, 4, '', '', '', '输出参数转换', 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('GetDictByGroup_Atsv', 2, 0, 'TELNUM=MSISDN&GROUPID=GROUPID&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('GetDictByGroup_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
--查询本机资费和已开通服务.sql
prompt delete QrySubsFeeInfo_Atsv...
delete from int_command_define where commandid='QrySubsFeeInfo_Atsv';
delete from int_command_param where commandid='QrySubsFeeInfo_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QrySubsFeeInfo_Atsv', '查询本机资费和已开通服务', 1, 5, 'CIntCommonDispatcher', to_date('13-06-2010 18:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('13-06-2010 18:00:00', 'dd-mm-yyyy hh24:mi:ss'), '查询本机资费和已开通服务', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsFeeInfo_Atsv', 1, null, '', 'OPCODE', 'QrySubsFeeInfo', '调用的函数', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsFeeInfo_Atsv', 2, 0, 'TELNUM=TELNUM&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsFeeInfo_Atsv', 3, 4, '', '', '', '输出结果集转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsFeeInfo_Atsv', 4, 0, '100=0', '', '', '返回值转换', null);
--查询余额--查询余额(帐务).sql
prompt delete QryBalance_Atsv...
delete from int_command_define where commandid='QryBalance_Atsv';
delete from int_command_param where commandid='QryBalance_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryBalance_Atsv', '查询余额--查询余额(帐务)', null, null, 'CChargeInterface', to_date('30-12-2006', 'dd-mm-yyyy'), 1, null, '查询余额--查询余额(帐务)', '', 0, 0, 1, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 1, null, '', 'QRYDROPFLAG', '1', '返回消户用户资料,于超要求', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 1, null, '', 'PRINTTRACELOG', '1', '出错打印日志', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 1, null, '', 'OPCODE', 'QryBalance', '查询余额--查询余额(帐务)', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 3, 0, 'FEE=FEE&PAY_BALANCE=PAY_BALANCE&CARD_BALANCE=CARD_BALANCE&CURRENT_XYK=CURRENT_XYK&CURRENT_COMP=CURRENT_COMP&LEFT_XYK=LEFT_XYK&LEFT_COMP=LEFT_COMP&LEFT_SKY=LEFT_SKY', '', '', '输出参数转换', null);
--查询余额提醒阀值（网上营业厅）.sql
prompt delete NetQryAlarmBalance_Atsv...
delete from int_command_define where commandid='NetQryAlarmBalance_Atsv';
delete from int_command_param where commandid='NetQryAlarmBalance_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetQryAlarmBalance_Atsv', '查询余额提醒阀值（网上营业厅）', 1, 1, 'CRecInterface', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '查询余额提醒阀值，网上营业厅调用', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryAlarmBalance_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryAlarmBalance_Atsv', 3, 2, 'PREPAYTYPE=36&CREDIT=138', '', '', '输出参数转换', 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryAlarmBalance_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryAlarmBalance_Atsv', 1, null, '', 'OPCODE', 'QryUserInfo', '查询余额提醒阀值（网上营业厅）', null);
--触摸屏选号（客服）.sql
prompt delete NetQryNumByNet_Atsv...
delete from int_command_define where commandid='NetQryNumByNet_Atsv';
delete from int_command_param where commandid='NetQryNumByNet_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetQryNumByNet_Atsv', '触摸屏选号（客服）', 1, 5, 'CAgentInterface', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '触摸屏选号，客服调用', '', 0, 1, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 1, null, '', 'OPCODE', 'QryNumByNet', '触摸屏选号（客服）', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 1, null, '', 'OPTYPE', 'COMMAGT_SELTEL', '原空中充值代理商选号', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 2, 0, 'COUNTY_ID=COUNTY_ID&STAFF_ID=STAFF_ID&TEL_BEGIN=CODE_BEGIN&TEL_END=CODE_END&TEL_SUFFIX=MSISDN_SUFFIX&TEL_PREFIX=MSISDN_PRE&SELECT_RULE=SELE_RULE&TEL_BRANDID=SERV_TYPE&NEEDOPER=NEEDOPER', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 3, 3, '0=0&1=3&2=2', '', '', '输出参数转换', 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 4, null, '10000=0', '', '', '返回值转换', null);
--当前话费查询.sql
prompt delete QryCurrentBill_Atsv...
delete from int_command_define where commandid='QryCurrentBill_Atsv';
delete from int_command_param where commandid='QryCurrentBill_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryCurrentBill_Atsv', '当前话费查询', 1, 7, 'CChargeInterface', to_date('08-05-2009 17:24:57', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '当前话费查询', '', 0, 0, 0, 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryCurrentBill_Atsv', 4, null, '100=0', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryCurrentBill_Atsv', 1, null, '', 'OPCODE', 'QryGivenBill', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryCurrentBill_Atsv', 1, null, '', 'QRYTYPE', '0', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryCurrentBill_Atsv', 2, 0, 'TELNUM=MSISDN&CYCLEOFFSET=CYCLEOFFSET&BILLTEMPLNO=BILLTEMPLNO&OPERID=OPERID', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryCurrentBill_Atsv', 3, 3, '0=0&1=1&2=2&3=3&4=4&5=5&6=6', '', '', '', 1);
--根据nocde(新)查询产品,优惠的资费描述信息.sql
prompt delete QryProductFeeNew_Atsv...
delete from int_command_define where commandid='QryProductFeeNew_Atsv';
delete from int_command_param where commandid='QryProductFeeNew_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryProductFeeNew_Atsv', '根据nocde(新)查询产品,优惠的资费描述信息', 1, 1, 'CRecInterface', to_date('06-05-2009', 'dd-mm-yyyy'), 1, null, '根据nocde查询产品,优惠的资费描述信息', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryProductFeeNew_Atsv', 1, null, '', 'OPCODE', 'QryRatePlane', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryProductFeeNew_Atsv', 1, null, '', 'OPTYPE', 'PCOpRec', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryProductFeeNew_Atsv', 2, 0, 'TELNUM=TELNUM&NCODE=NCODE&OPERID=OPERID', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryProductFeeNew_Atsv', 3, 0, 'DESC=DESC', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryProductFeeNew_Atsv', 4, null, '100=0', '', '', '', null);
--呼叫转移.sql
prompt delete CallTransfer_Atsv...
delete from int_command_define where commandid='CallTransfer_Atsv';
delete from int_command_param where commandid='CallTransfer_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CallTransfer_Atsv', '呼叫转移', null, null, 'CIntCommonDispatcher', to_date('04-01-2007', 'dd-mm-yyyy'), 1, null, '呼叫转移', '', 0, 1, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CallTransfer_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CallTransfer_Atsv', 1, null, '', 'OPCODE', 'CCEIDealCallTransfer', '呼叫转移', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CallTransfer_Atsv', 2, 0, 'TELNUM=MSISDN&DEALTYPE=DEALTYPE&CALLTYPE=HZLX&CALLEDNUMM=HZHM&CALLERNUM=CALL_NUMBER&OPERID=OPERID&OPERID=OPERID', '', '', '输入参数转换', null);
--话费查询.sql
prompt delete QryBill_Atsv...
delete from int_command_define where commandid='QryBill_Atsv';
delete from int_command_param where commandid='QryBill_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryBill_Atsv', '话费查询', null, 6, 'CChargeInterface', to_date('30-12-2006', 'dd-mm-yyyy'), 1, null, '话费查询', '', 0, 0, 1, 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 1, null, '', 'OPCODE', 'QryBill', '话费查询', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 1, null, '', 'QRYTYPE', '0', '查询标志', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 2, 0, 'TELNUM=MSISDN&MONTH=MONTH&BILLTEMPLNO=BILLTEMPLNO&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 3, 3, '0=1&1=2&2=3&3=4&4=5&5=6', '', '', '输出参数转换', 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
--获取手机归属地（网上营业厅）.sql
prompt delete NetGetAC_Atsv...
delete from int_command_define where commandid='NetGetAC_Atsv';
delete from int_command_param where commandid='NetGetAC_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetGetAC_Atsv', '获取手机归属地（网上营业厅）', 1, 1, 'CIntCommonDispatcher', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '根据手机号码，得到它的归属地区号和省代码，客服系统调用', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAC_Atsv', 3, 2, 'CITYNAME=4&PROVNAME=7', '', '', '输出参数转换', 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAC_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAC_Atsv', 2, 0, 'TELNUM=QRYTELNUM&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAC_Atsv', 1, null, '', 'OPCODE', 'CCEIQueryLocation', '获取手机归属地（网上营业厅）', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAC_Atsv', 1, null, '', 'LINK_PROV_INFO', '1', '需要返回省代码信息', null);
--积分调整.sql
prompt delete CommScoreChange_Atsv...
delete from int_command_define where commandid='CommScoreChange_Atsv';
delete from int_command_param where commandid='CommScoreChange_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CommScoreChange_Atsv', '积分调整', 1, 1, 'CRecInterface', to_date('25-12-2007', 'dd-mm-yyyy'), 1, null, '积分调整', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CommScoreChange_Atsv', 1, null, '', 'OPCODE', 'CommScoreChange', '积分调整', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CommScoreChange_Atsv', 2, null, 'TELNUM=TELNUM&OPERTYPE=OPERTYPE&SCOREVALUE=SCOREVALUE&ISNEGATIVE=ISNEGATIVE&SCORETYPEID=SCORETYPEID&REASON=REASON&OPERID=OPERID', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CommScoreChange_Atsv', 4, null, '100=0', '', '', '', null);
--缴费历史查询（网上营业厅）.sql
prompt delete NetBSFMX_Atsv...
delete from int_command_define where commandid='NetBSFMX_Atsv';
delete from int_command_param where commandid='NetBSFMX_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetBSFMX_Atsv', '缴费历史查询（网上营业厅）', 1, 7, 'CRecInterface', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '根据手机号码查询该用户的缴费纪录，网上营业厅系统调用', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 3, 3, '0=1&1=5&2=4&3=6&4=9&5=15&6=17', '', '', '输出参数转换', 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 2, 0, 'TELNUM=MSISDN&STARTDATE=STARTDATE&ENDDATE=ENDDATE&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 1, null, '', 'OPCODE', 'QryReceptionHistory', '缴费历史查询（网上营业厅）', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 1, null, '', 'RECEPTION_TYPE', 'Charge', '缴费历史查询（网上营业厅调用）', null);
--山东月账单查询.sql
prompt delete QryDetailBill_Atsv...
delete from int_command_define where commandid='QryDetailBill_Atsv';
delete from int_command_param where commandid='QryDetailBill_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryDetailBill_Atsv', '山东月账单查询', 1, 4, 'CChargeInterface', to_date('30-12-2006', 'dd-mm-yyyy'), 1, null, '山东月帐单查询', '', 0, 0, 0, 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryDetailBill_Atsv', 1, null, '', 'ISMAINACC', '0', '只查询主帐单', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryDetailBill_Atsv', 1, null, '', 'OPCODE', 'arGetBillAndAcctAndScore', '帐单查询', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryDetailBill_Atsv', 2, 0, 'TELNBR=MSISDN&CYCLE=BILLING_CYCLE_ID&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryDetailBill_Atsv', 3, 4, '', '', '', '输出参数转换', null);
--受理流水信息查询（网上营业厅）.sql
prompt delete NetGetAcpt_Atsv...
delete from int_command_define where commandid='NetGetAcpt_Atsv';
delete from int_command_param where commandid='NetGetAcpt_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetGetAcpt_Atsv', '受理流水信息查询（网上营业厅）', 1, 5, 'CRecInterface', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '根据输入的手机号码，查询该用户的受理流水信息，网上营业厅调用', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAcpt_Atsv', 1, null, '', 'QRYALLFLAG', '1', '查询所有受理流水信息', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAcpt_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAcpt_Atsv', 3, 3, '0=4&1=9&3=1', '', '', '输出参数转换', 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAcpt_Atsv', 1, null, '', 'OPCODE', 'QryReceptionSimple', '受理流水信息查询（网上营业厅）', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAcpt_Atsv', 2, 0, 'TELNUM=MSISDN&STARTDATE=STARTDATE&ENDDATE=ENDDATE&OPERID=OPERID', '', '', '输入参数转换', null);
--套餐使用情况查询.sql
prompt delete QuePriUsed_Atsv...
delete from int_command_define where commandid='QuePriUsed_Atsv';
delete from int_command_param where commandid='QuePriUsed_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QuePriUsed_Atsv', '套餐使用情况查询', null, 9, 'CRecInterface', to_date('30-01-2007 11:38:28', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '套餐使用情况查询', '', 0, 0, 1, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QuePriUsed_Atsv', 4, 0, '100=0&200=-1', '0', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QuePriUsed_Atsv', 1, null, '', 'OPCODE', 'qryPriUsed', '套餐使用情况查询', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QuePriUsed_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QuePriUsed_Atsv', 3, 0, 'CALCTIME=CALCTIME', '', '', '输出参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QuePriUsed_Atsv', 3, 4, '', '', '', '输出参数转换', 0);
--提醒余额设定.sql
prompt delete PreBlr_Atsv...
delete from int_command_define where commandid='PreBlr_Atsv';
delete from int_command_param where commandid='PreBlr_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('PreBlr_Atsv', '提醒余额设定', 1, 1, 'CRecInterface', to_date('18-12-2006 16:25:14', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '通过1861，用户可以自己设定要提醒的余额。如果取消余额提醒，输入参数中的CREDIT参数传0', '', 0, 1, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('PreBlr_Atsv', 4, null, '100=0&101=-2', '', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('PreBlr_Atsv', 2, 0, 'TELNUM=MSISDN&NOTIFYVALUE=CREDIT&CALLERNUM=CALL_NUMBER&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('PreBlr_Atsv', 1, null, '', 'OPCODE', 'SetUserAlarmBalance', '提醒余额设定', null);
--网上营业厅密码验证及修改.sql
prompt delete NetChkAndChgPwd_Atsv...
delete from int_command_define where commandid='NetChkAndChgPwd_Atsv';
delete from int_command_param where commandid='NetChkAndChgPwd_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetChkAndChgPwd_Atsv', '网上营业厅密码验证及修改', 1, 1, 'CIntCommonDispatcher', to_date('09-02-2007', 'dd-mm-yyyy'), 1, to_date('09-02-2007', 'dd-mm-yyyy'), '密码验证及修改，供网上营业厅调用', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetChkAndChgPwd_Atsv', 2, 0, 'TELNUM=MSISDN&SUBSOLDPASSWORD=OLD_PASSWD&SUBSNEWPASSWORD=NEW_PASSWD&SUBCMDID=SUBCMDID&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetChkAndChgPwd_Atsv', 1, null, '', 'OPCODE', 'CCEIChangeSubsPassWord', '网上营业厅密码验证及修改', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetChkAndChgPwd_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
--网上营业厅停开机业务（先查询用户状态）.sql
prompt delete QryUserStatus_Atsv...
delete from int_command_define where commandid='QryUserStatus_Atsv';
delete from int_command_param where commandid='QryUserStatus_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryUserStatus_Atsv', '网上营业厅停开机业务（先查询用户状态）', 1, 2, 'CRecInterface', to_date('16-01-2007', 'dd-mm-yyyy'), 1, null, '网上营业厅用户停开机', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryUserStatus_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryUserStatus_Atsv', 3, 2, 'STATUS=39&STATUSID=48', '', '', '输出参数转换', 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryUserStatus_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryUserStatus_Atsv', 1, null, '', 'OPCODE', 'QryUserInfo', '网上营业厅停开机业务（先查询用户状态）', null);
--网上营业厅用户登陆验证.sql
prompt delete LogIn_Atsv...
delete from int_command_define where commandid='LogIn_Atsv';
delete from int_command_param where commandid='LogIn_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('LogIn_Atsv', '网上营业厅用户登陆验证', 1, 1, 'CRecInterface', to_date('16-01-2007', 'dd-mm-yyyy'), 1, null, '网上营业厅用户登陆验证', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('LogIn_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('LogIn_Atsv', 3, 0, 'SUBNAME=SUBNAME&REGION=REGION&REGIONNAME=REGNAME&PRODUCTID=PRODUCTID&PRODUCTNAME=PRODUCTNAME&PRODUCTGROUP=PRODUCTGROUP&VIPTYPE=VIPTYPE&LOGINTYPE=LOGINTYPE&FEEFLAG=FEEFLAG&QUESTION=QUESTION&ANSWER=ANSWER&NEEDCHECKSTR=NEEDCHECKSTR&STATUS=STATUS', '', '', '输出参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('LogIn_Atsv', 2, 0, 'TELNUM=MSISDN&PASSWORD=PASSWORD&ISCHECKPASS=ISCHECKPASS&OPERID=OPERID&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('LogIn_Atsv', 1, null, '', 'OPCODE', 'SubsLogIn', '网上营业厅用户登陆验证', null);
--网上预订号码（自助终端）.sql
prompt delete NetOccupytRes_Atsv...
delete from int_command_define where commandid='NetOccupytRes_Atsv';
delete from int_command_param where commandid='NetOccupytRes_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetOccupytRes_Atsv', '网上预订号码（自助终端）', 1, 1, 'CIntCommonDispatcher', to_date('23-08-2011', 'dd-mm-yyyy'), 1, null, '网上预订号码，自助终端调用', '', 0, 1, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 1, null, '', 'EFF_LEN', '4320', '占号时间默认为3天', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 2, 0, 'TELNUM=MSISDN&REGION=REGION&ORGID=ORG_ID&CERTTYPE=CERTTYPE&CERTID=CERTI_NO&CONTACTPHONE=CONTACTTEL&CUSTNAME=NAME&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 1, null, '', 'OPCODE', 'CCEIOccupytRes', '网上占号（自助终端）', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 3, 0, 'ORDERID=ORDERID', '', '', '', null);
--用户积分查询.sql
prompt delete QrySubsDetailAvailScore_Atsv...
delete from int_command_define where commandid='QrySubsDetailAvailScore_Atsv';
delete from int_command_param where commandid='QrySubsDetailAvailScore_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QrySubsDetailAvailScore_Atsv', '用户积分查询', 1, 1, 'CRecInterface', to_date('21-01-2009', 'dd-mm-yyyy'), 1, null, '根据用户输入的手机号码，查询该用户的当前积分、本年度积分、上年度积分、前年度积分等信息，客服系统调用', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsDetailAvailScore_Atsv', 1, null, '', 'OPCODE', 'QrySubsDetailAvailScore', '用户详细积分查询', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsDetailAvailScore_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsDetailAvailScore_Atsv', 3, 0, 'CUR_CREDIT_COUNT=AVAILSCORE&CURSCORE=CURSCORE&PRISCORE=PRISCORE&PRIBEFSCORE=PRIBEFSCORE', '', '', '输出参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsDetailAvailScore_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
--月初扣费查询.sql
prompt delete YCKFCX_Atsv...
delete from int_command_define where commandid='YCKFCX_Atsv';
delete from int_command_param where commandid='YCKFCX_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('YCKFCX_Atsv', '月初扣费查询', null, 4, 'CChargeInterface', to_date('30-12-2006', 'dd-mm-yyyy'), 1, null, '查询用户月初扣费情况，客服系统调用', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('YCKFCX_Atsv', 4, null, '100=0', '', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('YCKFCX_Atsv', 1, null, '', 'OPCODE', 'YCKFCX', '月初扣费查询', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('YCKFCX_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('YCKFCX_Atsv', 3, 4, '', '', '', '输出参数转换', 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('YCKFCX_Atsv', 3, 0, 'SUM_FUNCFEE=SUM_FUNCFEE&SUM_PACKAGEFEE=SUM_PACKAGEFEE&SUM_BASEFEE=SUM_BASEFEE', '', '', '输出参数转换', null);
--增值业务统一查询接口.sql
prompt delete QrySubsAllIntServ_Atsv...
delete from int_command_define where commandid='QrySubsAllIntServ_Atsv';
delete from int_command_param where commandid='QrySubsAllIntServ_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QrySubsAllIntServ_Atsv', '增值业务统一查询接口', 1, 28, 'CIntCommonDispatcher', to_date('25-05-2010', 'dd-mm-yyyy'), 1, null, '统一查询', '', 0, 0, 1, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsAllIntServ_Atsv', 1, null, '', 'OPCODE', 'CCEIQrySubsAllIntServ', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsAllIntServ_Atsv', 2, 0, 'TELNUM=MSISDN&QUERYSN=SN&REGTYPE=REGTYPE&QUERYSPID=QUERYSPID&QUERYHISMODLE=QUERYHISMODLE&OPERID=OPERID', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsAllIntServ_Atsv', 3, 4, '', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsAllIntServ_Atsv', 4, null, '100=0', '', '', '', null);
--增值业务统一退订接口，自助终端调用.sql
prompt delete CancleSubsIntServ_Atsv...
delete from int_command_define where commandid='CancleSubsIntServ_Atsv';
delete from int_command_param where commandid='CancleSubsIntServ_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CancleSubsIntServ_Atsv', '增值业务统一退订接口，自助终端调用', 0, 0, 'CIntCommonDispatcher', to_date('25-05-2010', 'dd-mm-yyyy'), 1, null, '统一退订', '', 0, 0, 1, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CancleSubsIntServ_Atsv', 1, null, '', 'OPCODE', 'CCEICancleSubsIntServ', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CancleSubsIntServ_Atsv', 2, 0, 'TELNUM=TELNUM&OPERTYPE=OPERTYPE&SOURCE=SOURCE&CHKTYPE=CHKTYPE&FLAG=FLAG&SRPFLAG=SRPFLAG&CANCEL_FLAG=CANCEL_FLAG&DETAILINFO=DETAILINFO&ISNOTIFY=ISNOTIFY&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CancleSubsIntServ_Atsv', 3, 0, 'ORDERRESULT=ORDERRESULT&ORDERTIME=ORDERTIME&ORDERFLAG=ORDERFLAG&TIMEOUTS=TIMEOUTS&AFFIRM=AFFIRM&EFFETITIME=EFFETITIME&ABATETIME=ABATETIME', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CancleSubsIntServ_Atsv', 4, null, '100=0', '', '', '', null);
--自助终端发送短信接口.sql
prompt delete SendSMSInfo_Atsv...
delete from int_command_define where commandid='SendSMSInfo_Atsv';
delete from int_command_param where commandid='SendSMSInfo_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('SendSMSInfo_Atsv', '自助终端发送短信接口', 0, 0, 'CIntCommInterface', to_date('11-03-2009', 'dd-mm-yyyy'), 1, null, '自助终端发送短信接口', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('SendSMSInfo_Atsv', 2, 0, 'TELNUM=SERVNUM&TEMPLATENO=TEMPLATENO&SMPARAM=SMPARAM&ISRVCALL=ISRVCALL&NOTIFYTYPE=NOTIFYTYPE&DISPPORT=DISPPORT&RELATEDFORMNUM=RELATEDFORMNUM&YWFORMNUM=YWFORMNUM&SENDDATE=SENDDATE&RETCODE=OUTRETCODE&RETMSG=RETMSG&OPERID=OPERID', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('SendSMSInfo_Atsv', 1, null, '', 'OPCODE', 'SendSMSInfo', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('SendSMSInfo_Atsv', 4, null, '100=0', '', '', '', null);
--自助终端密码重置接口.sql
prompt delete CheckResetPassword_Atsv...
delete from int_command_define where commandid='CheckResetPassword_Atsv';
delete from int_command_param where commandid='CheckResetPassword_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CheckResetPassword_Atsv', '自助终端密码重置接口', 0, 0, 'CIntCommonDispatcher', to_date('16-08-2011', 'dd-mm-yyyy'), 1, to_date('16-08-2011', 'dd-mm-yyyy'), '自助终端密码重置接口', '', 0, 1, 1, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CheckResetPassword_Atsv', 4, null, '100=0&101=988706&105=-3003&102=-2020&104=-3001', '', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CheckResetPassword_Atsv', 3, 0, 'COMMITFLAG=COMMITFLAG&REMINDFLAG=REMINDFLAG&MAXFAILNUM=MAXFAILNUM&CURFAILNUM=CURFAILNUM&REMINDNUM=REMINDNUM&ISFREEZE=ISFREEZE', '', '', '输出参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CheckResetPassword_Atsv', 2, 0, 'TELNUM=TELNUM&OLD_PASSWD=OLD_PASSWD&NEW_PASSWD=NEW_PASSWD&CALLERNUM=CALL_NUMBER&ISSENDSMS=ISSENDSMS&FLAG=FLAG&CHKTYPE=CHKTYPE&SUBCMDID=SUBCMDID&ISREMIND=ISREMIND&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CheckResetPassword_Atsv', 1, null, '', 'OPCODE', 'CCEICheckResetPassword', '新客服密码验证及修改', null);
--自助终端身份证校验接口.sql
prompt delete CidCheck_Atsv...
delete from int_command_define where commandid='CidCheck_Atsv';
delete from int_command_param where commandid='CidCheck_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CidCheck_Atsv', '自助终端身份证校验接口', 1, 1, 'CIntCommonDispatcher', to_date('16-08-2011', 'dd-mm-yyyy'), 1, null, '自助终端身份证校验接口', '', 0, 1, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CidCheck_Atsv', 4, null, '100=0&101=103&102=-4999&103=988706&104=-1112', '', '', '返回值转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CidCheck_Atsv', 3, 0, 'MAXFAILNUM=MAXFAILNUM&CURFAILNUM=CURFAILNUM', '', '', '输出参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CidCheck_Atsv', 2, 0, 'CERTID=CERTID&TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CidCheck_Atsv', 1, null, '', 'OPCODE', 'CCEIIDCardCheck', '身份证校验', null);
--自助终端用户余额查询.sql
prompt delete UniGetBalanceByTelnum_Atsv...
delete from int_command_define where commandid='UniGetBalanceByTelnum_Atsv';
delete from int_command_param where commandid='UniGetBalanceByTelnum_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('UniGetBalanceByTelnum_Atsv', '自助终端用户余额查询', 1, 1, 'CIntCommonDispatcher', to_date('24-08-2011 15:46:03', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '自助终端用户余额查询', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('UniGetBalanceByTelnum_Atsv', 1, null, '', 'ISOFFSET', '1', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('UniGetBalanceByTelnum_Atsv', 1, null, '', 'OPCODE', 'arIntQryBalByServnum', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('UniGetBalanceByTelnum_Atsv', 2, 0, 'TELNUM=TELNUM&OPERID=OPERID', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('UniGetBalanceByTelnum_Atsv', 3, 0, 'PREPAYTYPE=PREPAYTYPE&CURRBILLFEE=CURRBILLFEE&HISBILLFEE=HISBILLFEE&BALANCE=BALANCE&EXPIREDATE=EXPIREDATE&CONTRACT_BALANCE=CONTRACT_BALANCE&CONTRACT_CANUSE=CONTRACT_CANUSE&CONTRACT_THISUSED=CONTRACT_THISUSED&CONTRACT_DRAWAMT=CONTRACT_DRAWAMT&PRESENT_BALANCE=PRESENT_BALANCE&PRESENT_CANUSE=PRESENT_CANUSE&PRESENT_THISUSED=PRESENT_THISUSED&PRESENT_DRAWAMT=PRESENT_DRAWAMT&CASH_BALANCE=CASH_BALANCE&OTHER_BALANCE=OTHER_BALANCE', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('UniGetBalanceByTelnum_Atsv', 4, null, '100=0', '', '', '', null);
