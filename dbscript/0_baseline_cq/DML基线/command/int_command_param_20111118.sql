set define off;
delete from int_command_param
 where commandid in
       ('Atsv_Busi_ChkChgPwd'
,'Atsv_Qry_producttree'
,'CallTransfer_Atsv'
,'CancleSubsIntServ_Atsv'
,'CheckResetPassword_Atsv'
,'CidCheck_Atsv'
,'NetBSFMX_Atsv'
,'NetChkAndChgPwd_Atsv'
,'NetGetAC_Atsv'
,'NetGetAcpt_Atsv'
,'NetOccupytRes_Atsv'
,'NetPukquery_Atsv'
,'NetQryAlarmBalance_Atsv'
,'NetQryNumByNet_Atsv'
,'OpenStopSubs_Atsv'
,'PreBlr_Atsv'
,'QryBalance_Atsv'
,'QryBill_Atsv'
,'QryCurrentBill_Atsv'
,'QryDetailBill_Atsv'
,'QryProductFeeNew_Atsv'
,'QrySubsAllIntServ_Atsv'
,'QrySubsDetailAvailScore_Atsv'
,'QrySubsFeeInfo_Atsv'
,'QryUserStatus_Atsv'
,'QuePriUsed_Atsv'
,'SendSMSInfo_Atsv'
,'UniGetBalanceByTelnum_Atsv'
,'YCKFCX_Atsv'
,'cli_busi_modacct_Atsv'
,'CommScoreChange_Atsv'
,'GetDictByGroup_Atsv'
,'LogIn_Atsv');

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('cli_busi_modacct_Atsv', 1, null, '', 'OPCODE', 'DoAccBillMail', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('cli_busi_modacct_Atsv', 2, 0, 'TELNUM=TELNUM&MAILTYPE=MAILTYPE&MAILADDR=MAILADDR&OPRTYPE=OPRTYPE&ORGID=ORGID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('cli_busi_modacct_Atsv', 3, 0, 'retmsg=RECINFO', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetPukquery_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetPukquery_Atsv', 3, 2, 'PIN1=0&PIN2=1&PUK1=2&PUK2=3', '', '', '输出参数转换', 1);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetPukquery_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetPukquery_Atsv', 1, null, '', 'OPCODE', 'CCEIQrySimPuk', 'PIN\PUK码查询（自助终端）', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('GetDictByGroup_Atsv', 1, null, '', 'OPCODE', 'CCEIGetDictByGroup', '按组取字典表数据', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('GetDictByGroup_Atsv', 3, 4, '', '', '', '输出参数转换', 0);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('GetDictByGroup_Atsv', 2, 0, 'TELNUM=MSISDN&GROUPID=GROUPID&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('GetDictByGroup_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsFeeInfo_Atsv', 1, null, '', 'OPCODE', 'QrySubsFeeInfo', '调用的函数', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsFeeInfo_Atsv', 2, 0, 'TELNUM=TELNUM&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsFeeInfo_Atsv', 3, 4, '', '', '', '输出结果集转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsFeeInfo_Atsv', 4, 0, '100=0', '', '', '返回值转换', null);

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

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryAlarmBalance_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryAlarmBalance_Atsv', 3, 2, 'PREPAYTYPE=36&CREDIT=138', '', '', '输出参数转换', 1);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryAlarmBalance_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryAlarmBalance_Atsv', 1, null, '', 'OPCODE', 'QryUserInfo', '查询余额提醒阀值（网上营业厅）', null);

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

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CallTransfer_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CallTransfer_Atsv', 1, null, '', 'OPCODE', 'CCEIDealCallTransfer', '呼叫转移', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CallTransfer_Atsv', 2, 0, 'TELNUM=MSISDN&DEALTYPE=DEALTYPE&CALLTYPE=HZLX&CALLEDNUMM=HZHM&CALLERNUM=CALL_NUMBER&OPERID=OPERID&OPERID=OPERID', '', '', '输入参数转换', null);

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

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CommScoreChange_Atsv', 1, null, '', 'OPCODE', 'CommScoreChange', '积分调整', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CommScoreChange_Atsv', 2, null, 'TELNUM=TELNUM&OPERTYPE=OPERTYPE&SCOREVALUE=SCOREVALUE&ISNEGATIVE=ISNEGATIVE&SCORETYPEID=SCORETYPEID&REASON=REASON&OPERID=OPERID', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CommScoreChange_Atsv', 4, null, '100=0', '', '', '', null);

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

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryDetailBill_Atsv', 1, null, '', 'ISMAINACC', '0', '只查询主帐单', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryDetailBill_Atsv', 1, null, '', 'OPCODE', 'arGetBillAndAcctAndScore', '帐单查询', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryDetailBill_Atsv', 2, 0, 'TELNBR=MSISDN&CYCLE=BILLING_CYCLE_ID&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryDetailBill_Atsv', 3, 4, '', '', '', '输出参数转换', null);

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

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('PreBlr_Atsv', 4, null, '100=0&101=-2', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('PreBlr_Atsv', 2, 0, 'TELNUM=MSISDN&NOTIFYVALUE=CREDIT&CALLERNUM=CALL_NUMBER&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('PreBlr_Atsv', 1, null, '', 'OPCODE', 'SetUserAlarmBalance', '提醒余额设定', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetChkAndChgPwd_Atsv', 2, 0, 'TELNUM=MSISDN&SUBSOLDPASSWORD=OLD_PASSWD&SUBSNEWPASSWORD=NEW_PASSWD&SUBCMDID=SUBCMDID&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetChkAndChgPwd_Atsv', 1, null, '', 'OPCODE', 'CCEIChangeSubsPassWord', '网上营业厅密码验证及修改', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetChkAndChgPwd_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryUserStatus_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryUserStatus_Atsv', 3, 2, 'STATUS=39&STATUSID=48', '', '', '输出参数转换', 1);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryUserStatus_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryUserStatus_Atsv', 1, null, '', 'OPCODE', 'QryUserInfo', '网上营业厅停开机业务（先查询用户状态）', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('LogIn_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('LogIn_Atsv', 3, 0, 'SUBNAME=SUBNAME&REGION=REGION&REGIONNAME=REGNAME&PRODUCTID=PRODUCTID&PRODUCTNAME=PRODUCTNAME&PRODUCTGROUP=PRODUCTGROUP&VIPTYPE=VIPTYPE&LOGINTYPE=LOGINTYPE&FEEFLAG=FEEFLAG&QUESTION=QUESTION&ANSWER=ANSWER&NEEDCHECKSTR=NEEDCHECKSTR&STATUS=STATUS', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('LogIn_Atsv', 2, 0, 'TELNUM=MSISDN&PASSWORD=PASSWORD&ISCHECKPASS=ISCHECKPASS&OPERID=OPERID&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('LogIn_Atsv', 1, null, '', 'OPCODE', 'SubsLogIn', '网上营业厅用户登陆验证', null);

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

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsDetailAvailScore_Atsv', 1, null, '', 'OPCODE', 'QrySubsDetailAvailScore', '用户详细积分查询', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsDetailAvailScore_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsDetailAvailScore_Atsv', 3, 0, 'CUR_CREDIT_COUNT=AVAILSCORE&CURSCORE=CURSCORE&PRISCORE=PRISCORE&PRIBEFSCORE=PRIBEFSCORE', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsDetailAvailScore_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

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

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsAllIntServ_Atsv', 1, null, '', 'OPCODE', 'CCEIQrySubsAllIntServ', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsAllIntServ_Atsv', 2, 0, 'TELNUM=MSISDN&QUERYSN=SN&REGTYPE=REGTYPE&QUERYSPID=QUERYSPID&QUERYHISMODLE=QUERYHISMODLE&OPERID=OPERID', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsAllIntServ_Atsv', 3, 4, '', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsAllIntServ_Atsv', 4, null, '100=0', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CancleSubsIntServ_Atsv', 1, null, '', 'OPCODE', 'CCEICancleSubsIntServ', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CancleSubsIntServ_Atsv', 2, 0, 'TELNUM=TELNUM&OPERTYPE=OPERTYPE&SOURCE=SOURCE&CHKTYPE=CHKTYPE&FLAG=FLAG&SRPFLAG=SRPFLAG&CANCEL_FLAG=CANCEL_FLAG&DETAILINFO=DETAILINFO&ISNOTIFY=ISNOTIFY&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CancleSubsIntServ_Atsv', 3, 0, 'ORDERRESULT=ORDERRESULT&ORDERTIME=ORDERTIME&ORDERFLAG=ORDERFLAG&TIMEOUTS=TIMEOUTS&AFFIRM=AFFIRM&EFFETITIME=EFFETITIME&ABATETIME=ABATETIME', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CancleSubsIntServ_Atsv', 4, null, '100=0', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('SendSMSInfo_Atsv', 2, 0, 'TELNUM=SERVNUM&TEMPLATENO=TEMPLATENO&SMPARAM=SMPARAM&ISRVCALL=ISRVCALL&NOTIFYTYPE=NOTIFYTYPE&DISPPORT=DISPPORT&RELATEDFORMNUM=RELATEDFORMNUM&YWFORMNUM=YWFORMNUM&SENDDATE=SENDDATE&RETCODE=OUTRETCODE&RETMSG=RETMSG&OPERID=OPERID', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('SendSMSInfo_Atsv', 1, null, '', 'OPCODE', 'SendSMSInfo', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('SendSMSInfo_Atsv', 4, null, '100=0', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CheckResetPassword_Atsv', 4, null, '100=0&101=988706&105=-3003&102=-2020&104=-3001', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CheckResetPassword_Atsv', 3, 0, 'COMMITFLAG=COMMITFLAG&REMINDFLAG=REMINDFLAG&MAXFAILNUM=MAXFAILNUM&CURFAILNUM=CURFAILNUM&REMINDNUM=REMINDNUM&ISFREEZE=ISFREEZE', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CheckResetPassword_Atsv', 2, 0, 'TELNUM=TELNUM&OLD_PASSWD=OLD_PASSWD&NEW_PASSWD=NEW_PASSWD&CALLERNUM=CALL_NUMBER&ISSENDSMS=ISSENDSMS&FLAG=FLAG&CHKTYPE=CHKTYPE&SUBCMDID=SUBCMDID&ISREMIND=ISREMIND&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CheckResetPassword_Atsv', 1, null, '', 'OPCODE', 'CCEICheckResetPassword', '新客服密码验证及修改', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CidCheck_Atsv', 4, null, '100=0&101=103&102=-4999&103=988706&104=-1112', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CidCheck_Atsv', 3, 0, 'MAXFAILNUM=MAXFAILNUM&CURFAILNUM=CURFAILNUM', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CidCheck_Atsv', 2, 0, 'CERTID=CERTID&TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CidCheck_Atsv', 1, null, '', 'OPCODE', 'CCEIIDCardCheck', '身份证校验', null);

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

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChkChgPwd', 1, null, '', 'OPCODE', 'changeSubsPassWord', '密码修改', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChkChgPwd', 1, null, '', 'SUBCMDID', '1', '验证旧密码', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChkChgPwd', 2, 0, 'TELNUM=TELNUM&SUBSNEWPASSWORD=NEW_PASSWD&SUBSOLDPASSWORD=OLD_PASSWD', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChkChgPwd', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_producttree', 1, null, '', 'OPCODE', 'KF_Q_PRODUCTTREE', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_producttree', 2, null, 'TELNUM=TELNUM', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_producttree', 3, 3, '0=0&1=1&2=2&3=3&4=4&5=5', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('LogIn_Atsv', 1, null, '', 'OPERID', 'innernet', '网上营业厅用户登陆验证', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('OpenStopSubs_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('OpenStopSubs_Atsv', 2, 0, 'TELNUM=TELNUM&stoptype=stoptype', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('OpenStopSubs_Atsv', 1, null, '', 'OPCODE', 'CCEIoutStopOpenSubs', '用户停&开机业务', null);

