
delete from int_command_define where commandid='Atsv_Qry_RecType_Hub';

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_RecType_Hub', '获取当前可办理优惠', 1, 8, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);


delete from int_command_param where commandid = 'Atsv_Qry_RecType_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_RecType_Hub', 1, null, '', 'OPCODE', 'Atsv_Qry_RecType_Hub', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_RecType_Hub', 2, 0, 'TELNUM=TELNUM', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_RecType_Hub', 3, 3, '0=0&1=1&2=2&3=3&4=4&5=5&6=6&7=7', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_RecType_Hub', 4, null, '100=0', '', '', '返回值转换', null);

delete from int_command_define where commandid='Atsv_Modify_Privilege_Hub';

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Modify_Privilege_Hub', '优惠办理', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);

delete from int_command_param where commandid = 'Atsv_Modify_Privilege_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Modify_Privilege_Hub', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Modify_Privilege_Hub', 1, null, '', 'OPCODE', 'Atsv_Modify_Privilege_Hub', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Modify_Privilege_Hub', 2, 0, 'TELNUM=TELNUM&NCODE=NCODE&STYPE=STYPE&ISSUBMIT=ISSUBMIT', '', '', '输入参数转换', null);

delete from int_command_define where commandid='Atsv_getTelnums_Hub';

insert into int_command_define (commandid, commandname, toincols, tooutcols, processclass, insertdate, status, statusdate, commanddesc, nextsql, iswritefilelog, iswritetablog, needtrans, retconvert)
values ('Atsv_getTelnums_Hub', '自助选号号码提取接口', 1, 2, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);



delete from int_command_param where commandid = 'Atsv_getTelnums_Hub'; 
insert into int_command_param (commandid, paramtype, paramfmt, paramdesc)
values ('Atsv_getTelnums_Hub', 4, '100=0', '返回值转换');
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_getTelnums_Hub', 1, null, null, 'OPCODE', 'Atsv_getTelnums_Hub', null, null);
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_getTelnums_Hub', 2, '0', 'MODEL=MODEL&PUR=PUR&PAGEINDEX=PAGEINDEX', null, null, '输入参数转换', null);
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_getTelnums_Hub', 3, '4', '', null, null, '输出参数转换', '');

delete from int_command_define where commandid='Atsv_getNetnbr_Hub';
insert into int_command_define (commandid, commandname, toincols, tooutcols, processclass, insertdate, status, statusdate, commanddesc, nextsql, iswritefilelog, iswritetablog, needtrans, retconvert)
values ('Atsv_getNetnbr_Hub', '自助选号获取网号接口', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);

delete from int_command_param where commandid = 'Atsv_getNetnbr_Hub'; 
insert into int_command_param (commandid, paramtype, paramfmt, paramdesc)
values ('Atsv_getNetnbr_Hub', 4, '100=0', '返回值转换');
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_getNetnbr_Hub', 1, null, null, 'OPCODE', 'Atsv_getNetnbr_Hub', null, null);
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_getNetnbr_Hub', 2, '0', 'NETTYPE=NETTYPE&PUR=PUR', null, null, '输入参数转换', null);
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_getNetnbr_Hub', 3, '4', '', null, null, '输出参数转换', '');


delete from int_command_define where commandid='Atsv_getTelsection_Hub';
insert into int_command_define (commandid, commandname, toincols, tooutcols, processclass, insertdate, status, statusdate, commanddesc, nextsql, iswritefilelog, iswritetablog, needtrans, retconvert)
values ('Atsv_getTelsection_Hub', '自助选号获取网段接口', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);

delete from int_command_param where commandid = 'Atsv_getTelsection_Hub'; 
insert into int_command_param (commandid, paramtype, paramfmt, paramdesc)
values ('Atsv_getTelsection_Hub', 4, '100=0', '返回值转换');
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_getTelsection_Hub', 1, null, null, 'OPCODE', 'Atsv_getTelsection_Hub', null, null);
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_getTelsection_Hub', 2, '0', 'NETNBR=NETNBR&NETTYPE=NETTYPE&PUR=PUR', null, null, '输入参数转换', null);
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_getTelsection_Hub', 3, '4', '', null, null, '输出参数转换', '');


delete from int_command_define where commandid='Atsv_busi_occupytel_Hub';
insert into int_command_define (commandid, commandname, toincols, tooutcols, processclass, insertdate, status, statusdate, commanddesc, nextsql, iswritefilelog, iswritetablog, needtrans, retconvert)
values ('Atsv_busi_occupytel_Hub', '自助选号预订接口', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);



delete from int_command_param where commandid = 'Atsv_busi_occupytel_Hub'; 

insert into int_command_param (commandid, paramtype, paramfmt, paramdesc)
values ('Atsv_busi_occupytel_Hub', 4, '100=0', '返回值转换');
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_busi_occupytel_Hub', 1, null, null, 'OPCODE', 'Atsv_busi_occupytel_Hub', null, null);
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_busi_occupytel_Hub', 2, '0', 'TELNUM=TELNUM&SELTELPREPAY=SELTELPREPAY&CHANNELID=CHANNELID&CREDENTFLAG=CREDENTFLAG&CERTTYPE=CERTTYPE&CERTID=CERTID', null, null, '输入参数转换', null);
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_busi_occupytel_Hub', 3, '0', 'OID=OID&VIDATETIME=VIDATETIME&REMIND=REMIND', null, null, '输出参数转换', '');



delete from int_command_define where commandid='Atsv_Qry_ChargeGuide_Hub';

insert into int_command_define (commandid, commandname, toincols, tooutcols, processclass, insertdate, status, statusdate, commanddesc, nextsql, iswritefilelog, iswritetablog, needtrans, retconvert)
values ('Atsv_Qry_ChargeGuide_Hub', '自助终端资费推荐接口', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);



delete from int_command_param where commandid = 'Atsv_Qry_ChargeGuide_Hub'; 

insert into int_command_param (commandid, paramtype, paramfmt, paramdesc)
values ('Atsv_Qry_ChargeGuide_Hub', 4, '100=0', '返回值转换');

insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_Qry_ChargeGuide_Hub', 1, null, null, 'OPCODE', 'Atsv_Qry_ChargeGuide_Hub', null, null);

insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_Qry_ChargeGuide_Hub', 2, '0', 'REGION=REGION&STREAMNO=STREAMNO&QUESTIONCODE=QUESTIONCODE&ANSWERCODE=ANSWERCODE', null, null, '输入参数转换', null);

insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_Qry_ChargeGuide_Hub', 3, '0', 'STREAMNO=STREAMNO&QUESTIONCODE=QUESTIONCODE&QUESTION=QUESTION&RECINFO=RECINFO', null, null, '输出参数转换', '');


delete from int_command_define where commandid='Atsv_Qry_BillAnalysis_Hub';

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_BillAnalysis_Hub', '自助终端话费账单分析', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);


delete from int_command_param where commandid = 'Atsv_Qry_BillAnalysis_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_BillAnalysis_Hub', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_BillAnalysis_Hub', 1, null, '', 'OPCODE', 'Atsv_Qry_BillAnalysis_Hub', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_BillAnalysis_Hub', 2, 0, 'TELNUM=TELNUM&BILLCYCLE=BILLCYCLE', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_BillAnalysis_Hub', 3, 0, 'RECCODE=RECCODE&RECSTR=RECSTR', '', '', '输出参数转换', null);


delete from int_command_define where commandid='Atsv_Qry_ScoreValue_Hub';

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_ScoreValue_Hub', '自助终端积分查询接口', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);

delete from int_command_param where commandid = 'Atsv_Qry_ScoreValue_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_ScoreValue_Hub', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_ScoreValue_Hub', 1, null, '', 'OPCODE', 'Atsv_Qry_ScoreValue_Hub', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_ScoreValue_Hub', 2, 0, 'TELNUM=TELNUM', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_ScoreValue_Hub', 3, 0, 'RETURNDATA=RETURNDATA', '', '', '输出参数转换', null);


delete from int_command_define where commandid='Atsv_Check_QryCdr_Hub';
insert into int_command_define (commandid, commandname, toincols, tooutcols, processclass, insertdate, status, statusdate, commanddesc, nextsql, iswritefilelog, iswritetablog, needtrans, retconvert)
values ('Atsv_Check_QryCdr_Hub', '自助终端清单查询权限验证', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);


delete from int_command_param where commandid = 'Atsv_Check_QryCdr_Hub'; 
insert into int_command_param (commandid, paramtype, paramfmt, paramdesc)
values ('Atsv_Check_QryCdr_Hub', 4, '100=0', '返回值转换');
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_Check_QryCdr_Hub', 1, null, null, 'OPCODE', 'Atsv_Check_QryCdr_Hub', null, null);
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_Check_QryCdr_Hub', 2, '0', 'TELNUM=TELNUM', null, null, '输入参数转换', null);
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_Check_QryCdr_Hub', 3, '', '', null, null, '输出参数转换', '');


delete from int_command_define where commandid='Atsv_Qry_UserInfo_Hub';

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_UserInfo_Hub', '自助终端密码验证接口', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);

delete from int_command_param where commandid = 'Atsv_Qry_UserInfo_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_UserInfo_Hub', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_UserInfo_Hub', 1, null, '', 'OPCODE', 'Atsv_Qry_UserInfo_Hub', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_UserInfo_Hub', 2, 0, 'TELNUM=TELNUM&ISCHECKPASS=ISCHECKPASS&PASSWORD=PASSWORD', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_UserInfo_Hub', 3, 0, 'SUBNAME=SUBNAME&REGION=REGION&REGIONNAME=REGIONNAME&PRODUCTID=PRODUCTID&PRODUCTNAME=PRODUCTNAME&PRODUCTGROUP=PRODUCTGROUP&VIPTYPE=VIPTYPE&NETTYPE=NETTYPE&CONTACTID=CONTACTID&STATUS=STATUS', '', '', '输出参数转换', null);


delete from int_command_define where commandid='Atsv_Qry_Fee_Hub';
insert into int_command_define (commandid, commandname, toincols, tooutcols, processclass, insertdate, status, statusdate, commanddesc, nextsql, iswritefilelog, iswritetablog, needtrans, retconvert)
values ('Atsv_Qry_Fee_Hub', '自助终端缴费查询接口', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);

delete from int_command_param where commandid = 'Atsv_Qry_Fee_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Fee_Hub', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Fee_Hub', 1, null, '', 'OPCODE', 'Atsv_Qry_Fee_Hub', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Fee_Hub', 2, 0, 'TELNUM=TELNUM', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Fee_Hub', 3, 0, 'SUBNAME=SUBNAME&REGION=REGION&REGIONNAME=REGIONNAME&PRODUCTID=PRODUCTID&PRODUCTNAME=PRODUCTNAME&PRODUCTGROUP=PRODUCTGROUP&VIPTYPE=VIPTYPE&NETTYPE=NETTYPE&BALANCE=BALANCE&STATUS=STATUS', '', '', '输出参数转换', null);


delete from int_command_define where commandid='Atsv_Busi_ChargeFee_Hub';
insert into int_command_define (commandid, commandname, toincols, tooutcols, processclass, insertdate, status, statusdate, commanddesc, nextsql, iswritefilelog, iswritetablog, needtrans, retconvert)
values ('Atsv_Busi_ChargeFee_Hub', '自助终端缴费接口', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);

delete from int_command_param where commandid = 'Atsv_Busi_ChargeFee_Hub'; 
insert into int_command_param (commandid, paramtype, paramfmt, paramdesc)
values ('Atsv_Busi_ChargeFee_Hub', 4, '100=0', '返回值转换');
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_Busi_ChargeFee_Hub', 1, null, null, 'OPCODE', 'Atsv_Busi_ChargeFee_Hub', null, null);
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_Busi_ChargeFee_Hub', 2, '0', 'TELNUM=TELNUM&AMOUNT=AMOUNT&PAY_TYPE=PAY_TYPE', null, null, '输入参数转换', null);
insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_Busi_ChargeFee_Hub', 3, '0', 'DEALNUM=DEALNUM&DEALTIME=DEALTIME', null, null, '输出参数转换', '');

delete from int_command_define where commandid='Atsv_Qry_InvoiceData_Hub';

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_InvoiceData_Hub', '自助终端取发票接口', 1, 2, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);


delete from int_command_param where commandid = 'Atsv_Qry_InvoiceData_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_InvoiceData_Hub', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_InvoiceData_Hub', 1, null, '', 'OPCODE', 'Atsv_Qry_InvoiceData_Hub', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_InvoiceData_Hub', 2, 0, 'TELNUM=TELNUM&FORMNUM=FORMNUM', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_InvoiceData_Hub', 3, 0, 'INVOICE_CNT=INVOICE_CNT', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_InvoiceData_Hub', 3, 4, '', '', '', '输出参数转换', null);



delete from int_command_define where commandid='Atsv_Qry_Balance_Hub';

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_Balance_Hub', '自助终端帐户余额信息查询接口', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);


delete from int_command_param where commandid = 'Atsv_Qry_Balance_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Balance_Hub', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Balance_Hub', 1, null, '', 'OPCODE', 'Atsv_Qry_Balance_Hub', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Balance_Hub', 2, 0, 'TELNUM=TELNUM', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Balance_Hub', 3, 0, 'BALANCE=BALANCE&CASHBALANCE=CASHBALANCE&CARDBALANCE=CARDBALANCE&MONDEDUCTION=MONDEDUCTION&PRESENTBALANCE=PRESENTBALANCE&MONPRESENTBALANCE=MONPRESENTBALANCE&DKBALANCE=DKBALANCE&PREDKBALANCE=PREDKBALANCE&AVAILABLEBALANCE=AVAILABLEBALANCE&CREDIT=CREDIT&REALTIMEFEE=REALTIMEFEE&HISARREARS=HISARREARS', '', '', '输出参数转换', null);

delete from int_command_define where commandid='Atsv_Qry_Priv_Uesd_Hub';

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_Priv_Uesd_Hub', '自助终端套餐信息查询接口', 1, 7, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);


delete from int_command_param where commandid = 'Atsv_Qry_Priv_Uesd_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Priv_Uesd_Hub', 3, 0, 'BILLCYCLE=BILLCYCLE', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Priv_Uesd_Hub', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Priv_Uesd_Hub', 1, null, '', 'OPCODE', 'Atsv_Qry_Priv_Uesd_Hub', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Priv_Uesd_Hub', 2, 0, 'TELNUM=TELNUM&BILLCYCLE=BILLCYCLE', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Priv_Uesd_Hub', 3, 4, '', '', '', '输出参数转换', null);

delete from int_command_define where commandid='Atsv_Qry_CdrList_Hub';

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT, ROWID)
values ('Atsv_Qry_CdrList_Hub', '自助终端详单查询接口', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0, 'AAIbJ6ADwAAA/0wAAB');

delete from int_command_param where commandid = 'Atsv_Qry_CdrList_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW, ROWID)
values ('Atsv_Qry_CdrList_Hub', 3, 4, '', '', '', '输出参数转换', null, 'AAIbJ+AAeAAASUSABN');

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW, ROWID)
values ('Atsv_Qry_CdrList_Hub', 4, null, '100=0', '', '', '返回值转换', null, 'AAIbJ+AAeAAASUYAAA');

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW, ROWID)
values ('Atsv_Qry_CdrList_Hub', 1, null, '', 'OPCODE', 'Atsv_Qry_CdrList_Hub', '', null, 'AAIbJ+AAeAAASUYAAB');

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW, ROWID)
values ('Atsv_Qry_CdrList_Hub', 2, 0, 'TELNUM=TELNUM&BILLCYCLE=BILLCYCLE&COUNTFLAG=COUNTFLAG&QUERYFLAG=QUERYFLAG&CDRTYPE=CDRTYPE&FEETYPE=FEETYPE', '', '', '输入参数转换', null, 'AAIbJ+AAeAAASUYAAC');

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW, ROWID)
values ('Atsv_Qry_CdrList_Hub', 3, 0, 'PRODUCTNAME=PRODUCTNAME&SUBNAME=SUBNAME&PRODUCTINFO=PRODUCTINFO&CREATEDATE=CREATEDATE&TOTALFEE=TOTALFEE', '', '', '输出参数转换', null, 'AAIbJ+AAeAAASUYAAI');


delete from int_command_define where commandid='Atsv_Qry_DetailedBill_Hub';

insert into int_command_define (commandid, commandname, toincols, tooutcols, processclass, insertdate, status, statusdate, commanddesc, nextsql, iswritefilelog, iswritetablog, needtrans, retconvert)
values ('Atsv_Qry_DetailedBill_Hub', '自助终端月账单查询查询接口', 1, 3, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);


delete from int_command_param where commandid = 'Atsv_Qry_DetailedBill_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_DetailedBill_Hub', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_DetailedBill_Hub', 1, null, '', 'OPCODE', 'Atsv_Qry_DetailedBill_Hub', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_DetailedBill_Hub', 2, 0, 'TELNUM=TELNUM&BILLCYCLE=BILLCYCLE&QRYTYPE=QRYTYPE', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_DetailedBill_Hub', 3, 4, '', '', '', '输出参数转换', null);


delete from int_command_define where commandid='Atsv_Package_ChangeList_Hub';

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Package_ChangeList_Hub', '自助终端获取用户可转换套餐清单接口', 1, 2, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);

delete from int_command_param where commandid = 'Atsv_Package_ChangeList_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Package_ChangeList_Hub', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Package_ChangeList_Hub', 1, null, '', 'OPCODE', 'Atsv_Package_ChangeList_Hub', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Package_ChangeList_Hub', 2, 0, 'TELNUM=TELNUM', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Package_ChangeList_Hub', 3, 0, 'SPRID=SPRID&SPRNAME=SPRNAME', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Package_ChangeList_Hub', 3, 4, '', '', '', '输出参数转换', null);



delete from int_command_define where commandid='Atsv_Package_ChgContent_Hub';

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Package_ChgContent_Hub', '自助终端套餐转换优惠/服务/产品变更清单接口', 1, 4, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);

delete from int_command_param where commandid = 'Atsv_Package_ChgContent_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Package_ChgContent_Hub', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Package_ChgContent_Hub', 1, null, '', 'OPCODE', 'Atsv_Package_ChgContent_Hub', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Package_ChgContent_Hub', 2, 0, 'TELNUM=TELNUM&SPRID=SPRID&TPRID=TPRID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Package_ChgContent_Hub', 3, 0, 'SN=SN&SPRNAME=SPRNAME&TPRNAME=TPRNAME', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Package_ChgContent_Hub', 3, 4, '', '', '', '输出参数转换', null);


delete from int_command_define where commandid='Atsv_Package_ChgCommit_Hub';

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Package_ChgCommit_Hub', '自助终端套餐转换接口', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);

delete from int_command_param where commandid = 'Atsv_Package_ChgCommit_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Package_ChgCommit_Hub', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Package_ChgCommit_Hub', 1, null, '', 'OPCODE', 'Atsv_Package_ChgCommit_Hub', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Package_ChgCommit_Hub', 2, 0, 'TELNUM=TELNUM&SPRID=SPRID&TPRID=TPRID&SN=SN', '', '', '输入参数转换', null);



delete from int_command_define where commandid='Atsv_Install_BookCertInfo_Hub';

insert into int_command_define (commandid, commandname, toincols, tooutcols, processclass, insertdate, status, statusdate, commanddesc, nextsql, iswritefilelog, iswritetablog, needtrans, retconvert)
values ('Atsv_Install_BookCertInfo_Hub', '自助终端身份证入网预约', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);

delete from int_command_param where commandid = 'Atsv_Install_BookCertInfo_Hub'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Install_BookCertInfo_Hub', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Install_BookCertInfo_Hub', 1, null, '', 'OPCODE', 'Atsv_Install_BookCertInfo_Hub', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Install_BookCertInfo_Hub', 2, 0, 'CERTID=CERTID&CERTNAME=CERTNAME&CERTADDR=CERTADDR&CERTGENDER=CERTGENDER&CERTSTARTDATE=CERTSTARTDATE&CERTENDDATE=CERTENDDATE', '', '', '输入参数转换', null);


delete from int_command_define where commandid='Atsv_Get_ParameterValueByID_Hub';

insert into int_command_define (commandid, commandname, toincols, tooutcols, processclass, insertdate, status, statusdate, commanddesc, nextsql, iswritefilelog, iswritetablog, needtrans, retconvert)
values ('Atsv_Get_ParameterValueByID_Hub', '自助终端获取系统参数', 1, 1, 'CHuBCustSvcInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);

delete from int_command_param where commandid = 'Atsv_Get_ParameterValueByID_Hub'; 

insert into int_command_param (commandid, paramtype, paramfmt, paramdesc)
values ('Atsv_Get_ParameterValueByID_Hub', 4, '100=0', '返回值转换');

insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_Get_ParameterValueByID_Hub', 1, null, null, 'OPCODE', 'Atsv_Get_ParameterValueByID_Hub', null, null);

insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_Get_ParameterValueByID_Hub', 2, '0', 'PARAMID=PARAMID', null, null, '输入参数转换', null);

insert into int_command_param (commandid, paramtype, transtype, paramfmt, paramname, paramvalue, paramdesc, beginrow)
values ('Atsv_Get_ParameterValueByID_Hub', 3, '0', 'PARAMVALUE=PARAMVALUE', null, null, '输出参数转换', '');




delete from int_command_define where commandid='Atsv_Busi_CallTransfer';
delete from int_command_define where commandid='Atsv_Busi_ChgSubsMonServ';
delete from int_command_define where commandid='Atsv_Busi_ChkChgPwd';
delete from int_command_define where commandid='Atsv_Busi_ChkIDCard';
delete from int_command_define where commandid='Atsv_Busi_ChkSetPwd_Hub';
delete from int_command_define where commandid='Atsv_Busi_OpenSubs';
delete from int_command_define where commandid='Atsv_Busi_SendSMSInfo';
delete from int_command_define where commandid='Atsv_Busi_StopSubs';
delete from int_command_define where commandid='Atsv_Busi_cardPay_Hub';
delete from int_command_define where commandid='Atsv_Busi_modacct_Hub';
delete from int_command_define where commandid='Atsv_Qry_GetAC';
delete from int_command_define where commandid='Atsv_Qry_GetAcpt';
delete from int_command_define where commandid='Atsv_Qry_GetDictByGroup';
delete from int_command_define where commandid='Atsv_Qry_PayHis';
delete from int_command_define where commandid='Atsv_Qry_ProductFee';
delete from int_command_define where commandid='Atsv_Qry_Puk';
delete from int_command_define where commandid='Atsv_Qry_SubsAllIntServ';
delete from int_command_define where commandid='Atsv_Qry_SubsFeeInfo';
delete from int_command_define where commandid='Atsv_Qry_UserStatus';

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Busi_SendSMSInfo', '发送短信接口', 0, 0, 'CIntCommInterface', sysdate, 1, sysdate, '发送短信接口', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_UserStatus', '自助终端停开机业务（先查询用户状态）', 1, 2, 'CRecInterface', sysdate, 1, sysdate, '自助终端', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Busi_CallTransfer', '呼叫转移', null, null, 'CIntCommonDispatcher', sysdate, 1, sysdate, '呼叫转移', '', 0, 1, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Busi_StopSubs', '申请停机', null, null, 'CIntCommonDispatcher', sysdate, 1, sysdate, '申请停机', '', 0, 1, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Busi_OpenSubs', '申请开机', null, null, 'CIntCommonDispatcher', sysdate, 1, sysdate, '申请开机', '', 0, 1, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Busi_ChgSubsMonServ', '用户梦网服务受理', 0, 0, 'CRecInterface', sysdate, 1, sysdate, '用户梦网服务受理', '', 0, 1, 1, 1);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_ProductFee', '根据nocde(新)查询产品,优惠的资费描述信息', 1, 1, 'CRecInterface', sysdate, 1, sysdate, '根据nocde查询产品,优惠的资费描述信息', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_GetDictByGroup', '按组取字典表数据', 1, 4, 'CIntCommonDispatcher', sysdate, 1, sysdate, '按组取字典表数据', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Busi_ChkSetPwd_Hub', '初始化1860用户密码', 1, 1, 'CRecInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_Puk', 'PIN\PUK码查询（自助终端）', 1, 4, 'CIntCommonDispatcher', sysdate, 1, sysdate, '根据手机号码查询pin/puk码信息，自助终端调用', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_SubsFeeInfo', '查询本机资费和已开通服务', 1, 5, 'CIntCommonDispatcher', sysdate, 1, sysdate, '查询本机资费和已开通服务', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Busi_cardPay_Hub', '发送充值卡密码充值（自助终端）', 1, 1, 'CRecInterface', sysdate, 1, sysdate, '', '', 0, 1, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_GetAC', '获取手机归属地（自助终端）', 1, 1, 'CIntCommonDispatcher', sysdate, 1, sysdate, '根据手机号码，得到它的归属地区号和省代码，客服系统调用', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_PayHis', '缴费历史查询（自助终端）', 1, 7, 'CRecInterface', sysdate, 1, sysdate, '根据手机号码查询该用户的缴费纪录，自助终端系统调用', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Busi_ChkChgPwd', '自助终端密码验证及修改', 1, 1, 'CIntCommonDispatcher', sysdate, 1, sysdate, '密码验证及修改，供自助终端调用', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_GetAcpt', '受理流水信息查询（自助终端）', 1, 5, 'CRecInterface', sysdate, 1, sysdate, '根据输入的手机号码，查询该用户的受理流水信息，自助终端调用', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Busi_ChkIDCard', '身份证验证（返回可校验次数）', 1, 1, 'CIntCommonDispatcher', sysdate, 1, sysdate, '自助终端使用', '', 0, 0, 1, 1);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Busi_modacct_Hub', '短信帐单业务受理 ', 1, 1, 'CHubRecStrInterface', sysdate, 1, sysdate, '', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_SubsAllIntServ', '用户梦网服务查询（自助终端）', 1, 24, 'CIntCommonDispatcher', sysdate, 1, sysdate, '用户梦网服务查询', '', 0, 0, 1, 0);

delete from int_command_param where commandid = 'Atsv_Busi_CallTransfer'; 
delete from int_command_param where commandid = 'Atsv_Busi_ChgSubsMonServ'; 
delete from int_command_param where commandid = 'Atsv_Busi_ChkChgPwd'; 
delete from int_command_param where commandid = 'Atsv_Busi_ChkIDCard'; 
delete from int_command_param where commandid = 'Atsv_Busi_ChkSetPwd_Hub'; 
delete from int_command_param where commandid = 'Atsv_Busi_OpenSubs'; 
delete from int_command_param where commandid = 'Atsv_Busi_SendSMSInfo'; 
delete from int_command_param where commandid = 'Atsv_Busi_StopSubs'; 
delete from int_command_param where commandid = 'Atsv_Busi_cardPay_Hub'; 
delete from int_command_param where commandid = 'Atsv_Busi_modacct_Hub'; 
delete from int_command_param where commandid = 'Atsv_Qry_GetAC'; 
delete from int_command_param where commandid = 'Atsv_Qry_GetAcpt'; 
delete from int_command_param where commandid = 'Atsv_Qry_GetDictByGroup'; 
delete from int_command_param where commandid = 'Atsv_Qry_PayHis'; 
delete from int_command_param where commandid = 'Atsv_Qry_ProductFee'; 
delete from int_command_param where commandid = 'Atsv_Qry_Puk'; 
delete from int_command_param where commandid = 'Atsv_Qry_SubsAllIntServ'; 
delete from int_command_param where commandid = 'Atsv_Qry_SubsFeeInfo'; 
delete from int_command_param where commandid = 'Atsv_Qry_UserStatus'; 

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_CallTransfer', 1, null, '', 'OPCODE', 'CCEIDealCallTransfer', '呼叫转移', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_CallTransfer', 2, 0, 'TELNUM=MSISDN&DEALTYPE=DEALTYPE&CALLTYPE=HZLX&CALLEDNUMM=HZHM&CALLERNUM=CALL_NUMBER&OPERID=OPERID&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_CallTransfer', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChgSubsMonServ', 1, null, '', 'OPCODE', 'ChangeSubsMonServ', 'DSMP发起到BOSS的单条订购/退订', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChgSubsMonServ', 2, 0, 'TELNUM=TELNUM&DETAILINFO=DETAILINFO&CANCEL_FLAG=CANCEL_FLAG&CANCELFLAG=CANCELFLAG&OPERTYPE=OPERTYPE&ACCESSTYPE=ACCESSTYPE&SPDOMAIN=SPDOMAIN&ACCESSMODEL=ACCESSMODEL&CHKTYPE=CHKTYPE&SOURCE=SOURCE&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChgSubsMonServ', 3, 0, 'ORDERRESULT=ORDERRESULT&ORDERTIME=ORDERTIME&ORDERFLAG=ORDERFLAG&TIMEOUTS=TIMEOUTS', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChgSubsMonServ', 4, null, '100=0', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChkChgPwd', 1, null, '', 'OPCODE', 'CCEIChangeSubsPassWord', '自助终端密码验证及修改', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChkChgPwd', 2, 0, 'TELNUM=MSISDN&SUBSOLDPASSWORD=OLD_PASSWD&SUBSNEWPASSWORD=NEW_PASSWD&SUBCMDID=SUBCMDID&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChkChgPwd', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChkIDCard', 1, null, '', 'OPCODE', 'CCEIIDCardCheck', '身份证验证（客服系统调用）', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChkIDCard', 2, 0, 'TELNUM=TELNUM&CERTID=CERTID&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChkIDCard', 3, 0, 'MAXFAILNUM=MAXFAILNUM&CURFAILNUM=CURFAILNUM&LEFTFAILNUM=LEFTFAILNUM', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChkIDCard', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChkSetPwd_Hub', 1, null, '', 'OPCODE', 'fmSetInitPwd', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChkSetPwd_Hub', 2, null, 'TELNUM=TELNUM&REGION=REGION', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_ChkSetPwd_Hub', 3, 0, 'RECINFO=RECINFO', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_OpenSubs', 1, null, '', 'OPCODE', 'CCEIoutStopOpenSubs', '申请开机', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_OpenSubs', 1, null, '', 'stoptype', 'OpenSubs', '用户开停机类型是开机', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_OpenSubs', 2, 0, 'TELNUM=MSISDN&CALLERNUM=CALL_NUMBER&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_OpenSubs', 3, 0, 'RECFORMNUM=RECFORMNUM', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_OpenSubs', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_SendSMSInfo', 1, null, '', 'OPCODE', 'SendSMSInfo', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_SendSMSInfo', 2, 0, 'TELNUM=SERVNUM&TEMPLATENO=TEMPLATENO&SMPARAM=SMPARAM&ISRVCALL=ISRVCALL&NOTIFYTYPE=NOTIFYTYPE&DISPPORT=DISPPORT&RELATEDFORMNUM=RELATEDFORMNUM&YWFORMNUM=YWFORMNUM&SENDDATE=SENDDATE&RETCODE=OUTRETCODE&RETMSG=RETMSG&OPERID=OPERID', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_SendSMSInfo', 4, null, '100=0', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_StopSubs', 1, null, '', 'OPCODE', 'CCEIoutStopOpenSubs', '申请停机', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_StopSubs', 1, null, '', 'stoptype', 'StopSubs', '用户开停机类型是停机', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_StopSubs', 2, 0, 'TELNUM=MSISDN&CALLERNUM=CALL_NUMBER&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_StopSubs', 3, 0, 'RECFORMNUM=RECFORMNUM', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_StopSubs', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_cardPay_Hub', 1, null, '', 'QRYDROPFLAG', '1', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_cardPay_Hub', 1, null, '', 'OPCODE', 'CCEIRecCardVCCharge', '短信充值', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_cardPay_Hub', 2, 0, 'TELNUM=TELNUM&CARDPIN=CARD_PWD&ACCOUNTTYPE=ACCOUNTTYPE&OPERNUM=OPERNUM&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_cardPay_Hub', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_modacct_Hub', 1, null, '', 'OPCODE', 'DoAccBillMail', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_modacct_Hub', 2, 0, 'TELNUM=TELNUM&MAILTYPE=MAILTYPE&MAILADDR=MAILADDR&OPRTYPE=OPRTYPE&ORGID=ORGID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Busi_modacct_Hub', 3, 0, 'retmsg=RECINFO', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_GetAC', 1, null, '', 'OPCODE', 'CCEIQueryLocation', '获取手机归属地（自助终端）', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_GetAC', 1, null, '', 'LINK_PROV_INFO', '1', '需要返回省代码信息', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_GetAC', 2, 0, 'TELNUM=QRYTELNUM&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_GetAC', 3, 2, 'CITYNAME=4&PROVNAME=7&CITYCODE=5', '', '', '输出参数转换', 0);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_GetAC', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_GetAcpt', 1, null, '', 'QRYALLFLAG', '1', '查询所有受理流水信息', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_GetAcpt', 1, null, '', 'OPCODE', 'QryReceptionSimple', '受理流水信息查询（自助终端）', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_GetAcpt', 2, 0, 'TELNUM=MSISDN&STARTDATE=STARTDATE&ENDDATE=ENDDATE&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_GetAcpt', 3, 3, '0=4&1=9&3=1', '', '', '输出参数转换', 1);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_GetAcpt', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_GetDictByGroup', 1, null, '', 'OPCODE', 'CCEIGetDictByGroup', '按组取字典表数据', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_GetDictByGroup', 2, 0, 'TELNUM=MSISDN&GROUPID=GROUPID&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_GetDictByGroup', 3, 4, '', '', '', '输出参数转换', 0);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_GetDictByGroup', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_PayHis', 1, null, '', 'OPCODE', 'QryReceptionHistory', '缴费历史查询（自助终端）', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_PayHis', 1, null, '', 'RECEPTION_TYPE', 'Charge', '缴费历史查询（自助终端调用）', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_PayHis', 2, 0, 'TELNUM=MSISDN&STARTDATE=STARTDATE&ENDDATE=ENDDATE&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_PayHis', 3, 3, '0=1&1=5&2=4&3=6&4=9&5=15&6=17', '', '', '输出参数转换', 0);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_PayHis', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_ProductFee', 1, null, '', 'OPCODE', 'QryRatePlane', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_ProductFee', 1, null, '', 'OPTYPE', 'PCOpRec', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_ProductFee', 2, 0, 'TELNUM=TELNUM&NCODE=NCODE&OPERID=OPERID', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_ProductFee', 3, 0, 'DESC=DESC', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_ProductFee', 4, null, '100=0', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Puk', 1, null, '', 'OPCODE', 'CCEIQrySimPuk', 'PIN\PUK码查询（自助终端）', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Puk', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Puk', 3, 2, 'PIN1=0&PIN2=1&PUK1=2&PUK2=3', '', '', '输出参数转换', 1);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_Puk', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_SubsAllIntServ', 1, null, '', 'QUERYHISMODLE', '1', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_SubsAllIntServ', 1, null, '', 'OPCODE', 'CCEIQuerySubsMonterServ', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_SubsAllIntServ', 2, 0, 'TELNUM=MSISDN&QUERYSN=SN&REGTYPE=REGTYPE&QUERYSPID=QUERYSPID&OUTOPERID=OUTOPERID&OPERID=OUTOPERID', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_SubsAllIntServ', 3, 3, '1=0&2=1&3=3&4=4&5=5&6=15&7=8&8=12&9=9&10=22&13=17', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_SubsAllIntServ', 4, null, '100=0', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_SubsFeeInfo', 1, null, '', 'OPCODE', 'QrySubsFeeInfo', '调用的函数', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_SubsFeeInfo', 2, 0, 'TELNUM=TELNUM&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_SubsFeeInfo', 3, 4, '', '', '', '输出结果集转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_SubsFeeInfo', 4, 0, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_UserStatus', 1, null, '', 'OPCODE', 'QryUserInfo', '自助终端停开机业务（先查询用户状态）', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_UserStatus', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_UserStatus', 3, 2, 'STATUS=39&STATUSID=48', '', '', '输出参数转换', 1);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('Atsv_Qry_UserStatus', 4, null, '100=0', '', '', '返回值转换', null);

