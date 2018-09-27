set feedback off
set define off
--PIN��PUK���ѯ�������նˣ�.sql
prompt delete NetPukquery_Atsv...
delete from int_command_define where commandid='NetPukquery_Atsv';
delete from int_command_param where commandid='NetPukquery_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetPukquery_Atsv', 'PIN\PUK���ѯ�������նˣ�', 1, 4, 'CIntCommonDispatcher', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '�����ֻ������ѯpin/puk����Ϣ�������ն˵���', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetPukquery_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetPukquery_Atsv', 3, 2, 'PIN1=0&PIN2=1&PUK1=2&PUK2=3', '', '', '�������ת��', 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetPukquery_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetPukquery_Atsv', 1, null, '', 'OPCODE', 'CCEIQrySimPuk', 'PIN\PUK���ѯ�������նˣ�', null);
--����ȡ�ֵ������.sql
prompt delete GetDictByGroup_Atsv...
delete from int_command_define where commandid='GetDictByGroup_Atsv';
delete from int_command_param where commandid='GetDictByGroup_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('GetDictByGroup_Atsv', '����ȡ�ֵ������', 1, 4, 'CIntCommonDispatcher', to_date('26-01-2007', 'dd-mm-yyyy'), 1, to_date('26-01-2007 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '����ȡ�ֵ������', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('GetDictByGroup_Atsv', 1, null, '', 'OPCODE', 'CCEIGetDictByGroup', '����ȡ�ֵ������', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('GetDictByGroup_Atsv', 3, 4, '', '', '', '�������ת��', 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('GetDictByGroup_Atsv', 2, 0, 'TELNUM=MSISDN&GROUPID=GROUPID&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('GetDictByGroup_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
--��ѯ�����ʷѺ��ѿ�ͨ����.sql
prompt delete QrySubsFeeInfo_Atsv...
delete from int_command_define where commandid='QrySubsFeeInfo_Atsv';
delete from int_command_param where commandid='QrySubsFeeInfo_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QrySubsFeeInfo_Atsv', '��ѯ�����ʷѺ��ѿ�ͨ����', 1, 5, 'CIntCommonDispatcher', to_date('13-06-2010 18:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('13-06-2010 18:00:00', 'dd-mm-yyyy hh24:mi:ss'), '��ѯ�����ʷѺ��ѿ�ͨ����', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsFeeInfo_Atsv', 1, null, '', 'OPCODE', 'QrySubsFeeInfo', '���õĺ���', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsFeeInfo_Atsv', 2, 0, 'TELNUM=TELNUM&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsFeeInfo_Atsv', 3, 4, '', '', '', '��������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsFeeInfo_Atsv', 4, 0, '100=0', '', '', '����ֵת��', null);
--��ѯ���--��ѯ���(����).sql
prompt delete QryBalance_Atsv...
delete from int_command_define where commandid='QryBalance_Atsv';
delete from int_command_param where commandid='QryBalance_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryBalance_Atsv', '��ѯ���--��ѯ���(����)', null, null, 'CChargeInterface', to_date('30-12-2006', 'dd-mm-yyyy'), 1, null, '��ѯ���--��ѯ���(����)', '', 0, 0, 1, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 1, null, '', 'QRYDROPFLAG', '1', '���������û�����,�ڳ�Ҫ��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 1, null, '', 'PRINTTRACELOG', '1', '�����ӡ��־', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 1, null, '', 'OPCODE', 'QryBalance', '��ѯ���--��ѯ���(����)', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 3, 0, 'FEE=FEE&PAY_BALANCE=PAY_BALANCE&CARD_BALANCE=CARD_BALANCE&CURRENT_XYK=CURRENT_XYK&CURRENT_COMP=CURRENT_COMP&LEFT_XYK=LEFT_XYK&LEFT_COMP=LEFT_COMP&LEFT_SKY=LEFT_SKY', '', '', '�������ת��', null);
--��ѯ������ѷ�ֵ������Ӫҵ����.sql
prompt delete NetQryAlarmBalance_Atsv...
delete from int_command_define where commandid='NetQryAlarmBalance_Atsv';
delete from int_command_param where commandid='NetQryAlarmBalance_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetQryAlarmBalance_Atsv', '��ѯ������ѷ�ֵ������Ӫҵ����', 1, 1, 'CRecInterface', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '��ѯ������ѷ�ֵ������Ӫҵ������', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryAlarmBalance_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryAlarmBalance_Atsv', 3, 2, 'PREPAYTYPE=36&CREDIT=138', '', '', '�������ת��', 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryAlarmBalance_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryAlarmBalance_Atsv', 1, null, '', 'OPCODE', 'QryUserInfo', '��ѯ������ѷ�ֵ������Ӫҵ����', null);
--������ѡ�ţ��ͷ���.sql
prompt delete NetQryNumByNet_Atsv...
delete from int_command_define where commandid='NetQryNumByNet_Atsv';
delete from int_command_param where commandid='NetQryNumByNet_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetQryNumByNet_Atsv', '������ѡ�ţ��ͷ���', 1, 5, 'CAgentInterface', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '������ѡ�ţ��ͷ�����', '', 0, 1, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 1, null, '', 'OPCODE', 'QryNumByNet', '������ѡ�ţ��ͷ���', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 1, null, '', 'OPTYPE', 'COMMAGT_SELTEL', 'ԭ���г�ֵ������ѡ��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 2, 0, 'COUNTY_ID=COUNTY_ID&STAFF_ID=STAFF_ID&TEL_BEGIN=CODE_BEGIN&TEL_END=CODE_END&TEL_SUFFIX=MSISDN_SUFFIX&TEL_PREFIX=MSISDN_PRE&SELECT_RULE=SELE_RULE&TEL_BRANDID=SERV_TYPE&NEEDOPER=NEEDOPER', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 3, 3, '0=0&1=3&2=2', '', '', '�������ת��', 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 4, null, '10000=0', '', '', '����ֵת��', null);
--��ǰ���Ѳ�ѯ.sql
prompt delete QryCurrentBill_Atsv...
delete from int_command_define where commandid='QryCurrentBill_Atsv';
delete from int_command_param where commandid='QryCurrentBill_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryCurrentBill_Atsv', '��ǰ���Ѳ�ѯ', 1, 7, 'CChargeInterface', to_date('08-05-2009 17:24:57', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '��ǰ���Ѳ�ѯ', '', 0, 0, 0, 1);
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
--����nocde(��)��ѯ��Ʒ,�Żݵ��ʷ�������Ϣ.sql
prompt delete QryProductFeeNew_Atsv...
delete from int_command_define where commandid='QryProductFeeNew_Atsv';
delete from int_command_param where commandid='QryProductFeeNew_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryProductFeeNew_Atsv', '����nocde(��)��ѯ��Ʒ,�Żݵ��ʷ�������Ϣ', 1, 1, 'CRecInterface', to_date('06-05-2009', 'dd-mm-yyyy'), 1, null, '����nocde��ѯ��Ʒ,�Żݵ��ʷ�������Ϣ', '', 0, 0, 0, 0);
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
--����ת��.sql
prompt delete CallTransfer_Atsv...
delete from int_command_define where commandid='CallTransfer_Atsv';
delete from int_command_param where commandid='CallTransfer_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CallTransfer_Atsv', '����ת��', null, null, 'CIntCommonDispatcher', to_date('04-01-2007', 'dd-mm-yyyy'), 1, null, '����ת��', '', 0, 1, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CallTransfer_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CallTransfer_Atsv', 1, null, '', 'OPCODE', 'CCEIDealCallTransfer', '����ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CallTransfer_Atsv', 2, 0, 'TELNUM=MSISDN&DEALTYPE=DEALTYPE&CALLTYPE=HZLX&CALLEDNUMM=HZHM&CALLERNUM=CALL_NUMBER&OPERID=OPERID&OPERID=OPERID', '', '', '�������ת��', null);
--���Ѳ�ѯ.sql
prompt delete QryBill_Atsv...
delete from int_command_define where commandid='QryBill_Atsv';
delete from int_command_param where commandid='QryBill_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryBill_Atsv', '���Ѳ�ѯ', null, 6, 'CChargeInterface', to_date('30-12-2006', 'dd-mm-yyyy'), 1, null, '���Ѳ�ѯ', '', 0, 0, 1, 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 1, null, '', 'OPCODE', 'QryBill', '���Ѳ�ѯ', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 1, null, '', 'QRYTYPE', '0', '��ѯ��־', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 2, 0, 'TELNUM=MSISDN&MONTH=MONTH&BILLTEMPLNO=BILLTEMPLNO&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 3, 3, '0=1&1=2&2=3&3=4&4=5&5=6', '', '', '�������ת��', 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
--��ȡ�ֻ������أ�����Ӫҵ����.sql
prompt delete NetGetAC_Atsv...
delete from int_command_define where commandid='NetGetAC_Atsv';
delete from int_command_param where commandid='NetGetAC_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetGetAC_Atsv', '��ȡ�ֻ������أ�����Ӫҵ����', 1, 1, 'CIntCommonDispatcher', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '�����ֻ����룬�õ����Ĺ��������ź�ʡ���룬�ͷ�ϵͳ����', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAC_Atsv', 3, 2, 'CITYNAME=4&PROVNAME=7', '', '', '�������ת��', 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAC_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAC_Atsv', 2, 0, 'TELNUM=QRYTELNUM&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAC_Atsv', 1, null, '', 'OPCODE', 'CCEIQueryLocation', '��ȡ�ֻ������أ�����Ӫҵ����', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAC_Atsv', 1, null, '', 'LINK_PROV_INFO', '1', '��Ҫ����ʡ������Ϣ', null);
--���ֵ���.sql
prompt delete CommScoreChange_Atsv...
delete from int_command_define where commandid='CommScoreChange_Atsv';
delete from int_command_param where commandid='CommScoreChange_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CommScoreChange_Atsv', '���ֵ���', 1, 1, 'CRecInterface', to_date('25-12-2007', 'dd-mm-yyyy'), 1, null, '���ֵ���', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CommScoreChange_Atsv', 1, null, '', 'OPCODE', 'CommScoreChange', '���ֵ���', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CommScoreChange_Atsv', 2, null, 'TELNUM=TELNUM&OPERTYPE=OPERTYPE&SCOREVALUE=SCOREVALUE&ISNEGATIVE=ISNEGATIVE&SCORETYPEID=SCORETYPEID&REASON=REASON&OPERID=OPERID', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CommScoreChange_Atsv', 4, null, '100=0', '', '', '', null);
--�ɷ���ʷ��ѯ������Ӫҵ����.sql
prompt delete NetBSFMX_Atsv...
delete from int_command_define where commandid='NetBSFMX_Atsv';
delete from int_command_param where commandid='NetBSFMX_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetBSFMX_Atsv', '�ɷ���ʷ��ѯ������Ӫҵ����', 1, 7, 'CRecInterface', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '�����ֻ������ѯ���û��ĽɷѼ�¼������Ӫҵ��ϵͳ����', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 3, 3, '0=1&1=5&2=4&3=6&4=9&5=15&6=17', '', '', '�������ת��', 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 2, 0, 'TELNUM=MSISDN&STARTDATE=STARTDATE&ENDDATE=ENDDATE&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 1, null, '', 'OPCODE', 'QryReceptionHistory', '�ɷ���ʷ��ѯ������Ӫҵ����', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 1, null, '', 'RECEPTION_TYPE', 'Charge', '�ɷ���ʷ��ѯ������Ӫҵ�����ã�', null);
--ɽ�����˵���ѯ.sql
prompt delete QryDetailBill_Atsv...
delete from int_command_define where commandid='QryDetailBill_Atsv';
delete from int_command_param where commandid='QryDetailBill_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryDetailBill_Atsv', 'ɽ�����˵���ѯ', 1, 4, 'CChargeInterface', to_date('30-12-2006', 'dd-mm-yyyy'), 1, null, 'ɽ�����ʵ���ѯ', '', 0, 0, 0, 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryDetailBill_Atsv', 1, null, '', 'ISMAINACC', '0', 'ֻ��ѯ���ʵ�', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryDetailBill_Atsv', 1, null, '', 'OPCODE', 'arGetBillAndAcctAndScore', '�ʵ���ѯ', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryDetailBill_Atsv', 2, 0, 'TELNBR=MSISDN&CYCLE=BILLING_CYCLE_ID&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryDetailBill_Atsv', 3, 4, '', '', '', '�������ת��', null);
--������ˮ��Ϣ��ѯ������Ӫҵ����.sql
prompt delete NetGetAcpt_Atsv...
delete from int_command_define where commandid='NetGetAcpt_Atsv';
delete from int_command_param where commandid='NetGetAcpt_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetGetAcpt_Atsv', '������ˮ��Ϣ��ѯ������Ӫҵ����', 1, 5, 'CRecInterface', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '����������ֻ����룬��ѯ���û���������ˮ��Ϣ������Ӫҵ������', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAcpt_Atsv', 1, null, '', 'QRYALLFLAG', '1', '��ѯ����������ˮ��Ϣ', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAcpt_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAcpt_Atsv', 3, 3, '0=4&1=9&3=1', '', '', '�������ת��', 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAcpt_Atsv', 1, null, '', 'OPCODE', 'QryReceptionSimple', '������ˮ��Ϣ��ѯ������Ӫҵ����', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetGetAcpt_Atsv', 2, 0, 'TELNUM=MSISDN&STARTDATE=STARTDATE&ENDDATE=ENDDATE&OPERID=OPERID', '', '', '�������ת��', null);
--�ײ�ʹ�������ѯ.sql
prompt delete QuePriUsed_Atsv...
delete from int_command_define where commandid='QuePriUsed_Atsv';
delete from int_command_param where commandid='QuePriUsed_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QuePriUsed_Atsv', '�ײ�ʹ�������ѯ', null, 9, 'CRecInterface', to_date('30-01-2007 11:38:28', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '�ײ�ʹ�������ѯ', '', 0, 0, 1, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QuePriUsed_Atsv', 4, 0, '100=0&200=-1', '0', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QuePriUsed_Atsv', 1, null, '', 'OPCODE', 'qryPriUsed', '�ײ�ʹ�������ѯ', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QuePriUsed_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QuePriUsed_Atsv', 3, 0, 'CALCTIME=CALCTIME', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QuePriUsed_Atsv', 3, 4, '', '', '', '�������ת��', 0);
--��������趨.sql
prompt delete PreBlr_Atsv...
delete from int_command_define where commandid='PreBlr_Atsv';
delete from int_command_param where commandid='PreBlr_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('PreBlr_Atsv', '��������趨', 1, 1, 'CRecInterface', to_date('18-12-2006 16:25:14', 'dd-mm-yyyy hh24:mi:ss'), 1, null, 'ͨ��1861���û������Լ��趨Ҫ���ѵ������ȡ��������ѣ���������е�CREDIT������0', '', 0, 1, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('PreBlr_Atsv', 4, null, '100=0&101=-2', '', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('PreBlr_Atsv', 2, 0, 'TELNUM=MSISDN&NOTIFYVALUE=CREDIT&CALLERNUM=CALL_NUMBER&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('PreBlr_Atsv', 1, null, '', 'OPCODE', 'SetUserAlarmBalance', '��������趨', null);
--����Ӫҵ��������֤���޸�.sql
prompt delete NetChkAndChgPwd_Atsv...
delete from int_command_define where commandid='NetChkAndChgPwd_Atsv';
delete from int_command_param where commandid='NetChkAndChgPwd_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetChkAndChgPwd_Atsv', '����Ӫҵ��������֤���޸�', 1, 1, 'CIntCommonDispatcher', to_date('09-02-2007', 'dd-mm-yyyy'), 1, to_date('09-02-2007', 'dd-mm-yyyy'), '������֤���޸ģ�������Ӫҵ������', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetChkAndChgPwd_Atsv', 2, 0, 'TELNUM=MSISDN&SUBSOLDPASSWORD=OLD_PASSWD&SUBSNEWPASSWORD=NEW_PASSWD&SUBCMDID=SUBCMDID&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetChkAndChgPwd_Atsv', 1, null, '', 'OPCODE', 'CCEIChangeSubsPassWord', '����Ӫҵ��������֤���޸�', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetChkAndChgPwd_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
--����Ӫҵ��ͣ����ҵ���Ȳ�ѯ�û�״̬��.sql
prompt delete QryUserStatus_Atsv...
delete from int_command_define where commandid='QryUserStatus_Atsv';
delete from int_command_param where commandid='QryUserStatus_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryUserStatus_Atsv', '����Ӫҵ��ͣ����ҵ���Ȳ�ѯ�û�״̬��', 1, 2, 'CRecInterface', to_date('16-01-2007', 'dd-mm-yyyy'), 1, null, '����Ӫҵ���û�ͣ����', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryUserStatus_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryUserStatus_Atsv', 3, 2, 'STATUS=39&STATUSID=48', '', '', '�������ת��', 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryUserStatus_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryUserStatus_Atsv', 1, null, '', 'OPCODE', 'QryUserInfo', '����Ӫҵ��ͣ����ҵ���Ȳ�ѯ�û�״̬��', null);
--����Ӫҵ���û���½��֤.sql
prompt delete LogIn_Atsv...
delete from int_command_define where commandid='LogIn_Atsv';
delete from int_command_param where commandid='LogIn_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('LogIn_Atsv', '����Ӫҵ���û���½��֤', 1, 1, 'CRecInterface', to_date('16-01-2007', 'dd-mm-yyyy'), 1, null, '����Ӫҵ���û���½��֤', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('LogIn_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('LogIn_Atsv', 3, 0, 'SUBNAME=SUBNAME&REGION=REGION&REGIONNAME=REGNAME&PRODUCTID=PRODUCTID&PRODUCTNAME=PRODUCTNAME&PRODUCTGROUP=PRODUCTGROUP&VIPTYPE=VIPTYPE&LOGINTYPE=LOGINTYPE&FEEFLAG=FEEFLAG&QUESTION=QUESTION&ANSWER=ANSWER&NEEDCHECKSTR=NEEDCHECKSTR&STATUS=STATUS', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('LogIn_Atsv', 2, 0, 'TELNUM=MSISDN&PASSWORD=PASSWORD&ISCHECKPASS=ISCHECKPASS&OPERID=OPERID&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('LogIn_Atsv', 1, null, '', 'OPCODE', 'SubsLogIn', '����Ӫҵ���û���½��֤', null);
--����Ԥ�����루�����նˣ�.sql
prompt delete NetOccupytRes_Atsv...
delete from int_command_define where commandid='NetOccupytRes_Atsv';
delete from int_command_param where commandid='NetOccupytRes_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetOccupytRes_Atsv', '����Ԥ�����루�����նˣ�', 1, 1, 'CIntCommonDispatcher', to_date('23-08-2011', 'dd-mm-yyyy'), 1, null, '����Ԥ�����룬�����ն˵���', '', 0, 1, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 1, null, '', 'EFF_LEN', '4320', 'ռ��ʱ��Ĭ��Ϊ3��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 2, 0, 'TELNUM=MSISDN&REGION=REGION&ORGID=ORG_ID&CERTTYPE=CERTTYPE&CERTID=CERTI_NO&CONTACTPHONE=CONTACTTEL&CUSTNAME=NAME&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 1, null, '', 'OPCODE', 'CCEIOccupytRes', '����ռ�ţ������նˣ�', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 3, 0, 'ORDERID=ORDERID', '', '', '', null);
--�û����ֲ�ѯ.sql
prompt delete QrySubsDetailAvailScore_Atsv...
delete from int_command_define where commandid='QrySubsDetailAvailScore_Atsv';
delete from int_command_param where commandid='QrySubsDetailAvailScore_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QrySubsDetailAvailScore_Atsv', '�û����ֲ�ѯ', 1, 1, 'CRecInterface', to_date('21-01-2009', 'dd-mm-yyyy'), 1, null, '�����û�������ֻ����룬��ѯ���û��ĵ�ǰ���֡�����Ȼ��֡�����Ȼ��֡�ǰ��Ȼ��ֵ���Ϣ���ͷ�ϵͳ����', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsDetailAvailScore_Atsv', 1, null, '', 'OPCODE', 'QrySubsDetailAvailScore', '�û���ϸ���ֲ�ѯ', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsDetailAvailScore_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsDetailAvailScore_Atsv', 3, 0, 'CUR_CREDIT_COUNT=AVAILSCORE&CURSCORE=CURSCORE&PRISCORE=PRISCORE&PRIBEFSCORE=PRIBEFSCORE', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsDetailAvailScore_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
--�³��۷Ѳ�ѯ.sql
prompt delete YCKFCX_Atsv...
delete from int_command_define where commandid='YCKFCX_Atsv';
delete from int_command_param where commandid='YCKFCX_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('YCKFCX_Atsv', '�³��۷Ѳ�ѯ', null, 4, 'CChargeInterface', to_date('30-12-2006', 'dd-mm-yyyy'), 1, null, '��ѯ�û��³��۷�������ͷ�ϵͳ����', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('YCKFCX_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('YCKFCX_Atsv', 1, null, '', 'OPCODE', 'YCKFCX', '�³��۷Ѳ�ѯ', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('YCKFCX_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('YCKFCX_Atsv', 3, 4, '', '', '', '�������ת��', 1);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('YCKFCX_Atsv', 3, 0, 'SUM_FUNCFEE=SUM_FUNCFEE&SUM_PACKAGEFEE=SUM_PACKAGEFEE&SUM_BASEFEE=SUM_BASEFEE', '', '', '�������ת��', null);
--��ֵҵ��ͳһ��ѯ�ӿ�.sql
prompt delete QrySubsAllIntServ_Atsv...
delete from int_command_define where commandid='QrySubsAllIntServ_Atsv';
delete from int_command_param where commandid='QrySubsAllIntServ_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QrySubsAllIntServ_Atsv', '��ֵҵ��ͳһ��ѯ�ӿ�', 1, 28, 'CIntCommonDispatcher', to_date('25-05-2010', 'dd-mm-yyyy'), 1, null, 'ͳһ��ѯ', '', 0, 0, 1, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsAllIntServ_Atsv', 1, null, '', 'OPCODE', 'CCEIQrySubsAllIntServ', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsAllIntServ_Atsv', 2, 0, 'TELNUM=MSISDN&QUERYSN=SN&REGTYPE=REGTYPE&QUERYSPID=QUERYSPID&QUERYHISMODLE=QUERYHISMODLE&OPERID=OPERID', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsAllIntServ_Atsv', 3, 4, '', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QrySubsAllIntServ_Atsv', 4, null, '100=0', '', '', '', null);
--��ֵҵ��ͳһ�˶��ӿڣ������ն˵���.sql
prompt delete CancleSubsIntServ_Atsv...
delete from int_command_define where commandid='CancleSubsIntServ_Atsv';
delete from int_command_param where commandid='CancleSubsIntServ_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CancleSubsIntServ_Atsv', '��ֵҵ��ͳһ�˶��ӿڣ������ն˵���', 0, 0, 'CIntCommonDispatcher', to_date('25-05-2010', 'dd-mm-yyyy'), 1, null, 'ͳһ�˶�', '', 0, 0, 1, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CancleSubsIntServ_Atsv', 1, null, '', 'OPCODE', 'CCEICancleSubsIntServ', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CancleSubsIntServ_Atsv', 2, 0, 'TELNUM=TELNUM&OPERTYPE=OPERTYPE&SOURCE=SOURCE&CHKTYPE=CHKTYPE&FLAG=FLAG&SRPFLAG=SRPFLAG&CANCEL_FLAG=CANCEL_FLAG&DETAILINFO=DETAILINFO&ISNOTIFY=ISNOTIFY&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CancleSubsIntServ_Atsv', 3, 0, 'ORDERRESULT=ORDERRESULT&ORDERTIME=ORDERTIME&ORDERFLAG=ORDERFLAG&TIMEOUTS=TIMEOUTS&AFFIRM=AFFIRM&EFFETITIME=EFFETITIME&ABATETIME=ABATETIME', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CancleSubsIntServ_Atsv', 4, null, '100=0', '', '', '', null);
--�����ն˷��Ͷ��Žӿ�.sql
prompt delete SendSMSInfo_Atsv...
delete from int_command_define where commandid='SendSMSInfo_Atsv';
delete from int_command_param where commandid='SendSMSInfo_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('SendSMSInfo_Atsv', '�����ն˷��Ͷ��Žӿ�', 0, 0, 'CIntCommInterface', to_date('11-03-2009', 'dd-mm-yyyy'), 1, null, '�����ն˷��Ͷ��Žӿ�', '', 0, 0, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('SendSMSInfo_Atsv', 2, 0, 'TELNUM=SERVNUM&TEMPLATENO=TEMPLATENO&SMPARAM=SMPARAM&ISRVCALL=ISRVCALL&NOTIFYTYPE=NOTIFYTYPE&DISPPORT=DISPPORT&RELATEDFORMNUM=RELATEDFORMNUM&YWFORMNUM=YWFORMNUM&SENDDATE=SENDDATE&RETCODE=OUTRETCODE&RETMSG=RETMSG&OPERID=OPERID', '', '', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('SendSMSInfo_Atsv', 1, null, '', 'OPCODE', 'SendSMSInfo', '', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('SendSMSInfo_Atsv', 4, null, '100=0', '', '', '', null);
--�����ն��������ýӿ�.sql
prompt delete CheckResetPassword_Atsv...
delete from int_command_define where commandid='CheckResetPassword_Atsv';
delete from int_command_param where commandid='CheckResetPassword_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CheckResetPassword_Atsv', '�����ն��������ýӿ�', 0, 0, 'CIntCommonDispatcher', to_date('16-08-2011', 'dd-mm-yyyy'), 1, to_date('16-08-2011', 'dd-mm-yyyy'), '�����ն��������ýӿ�', '', 0, 1, 1, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CheckResetPassword_Atsv', 4, null, '100=0&101=988706&105=-3003&102=-2020&104=-3001', '', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CheckResetPassword_Atsv', 3, 0, 'COMMITFLAG=COMMITFLAG&REMINDFLAG=REMINDFLAG&MAXFAILNUM=MAXFAILNUM&CURFAILNUM=CURFAILNUM&REMINDNUM=REMINDNUM&ISFREEZE=ISFREEZE', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CheckResetPassword_Atsv', 2, 0, 'TELNUM=TELNUM&OLD_PASSWD=OLD_PASSWD&NEW_PASSWD=NEW_PASSWD&CALLERNUM=CALL_NUMBER&ISSENDSMS=ISSENDSMS&FLAG=FLAG&CHKTYPE=CHKTYPE&SUBCMDID=SUBCMDID&ISREMIND=ISREMIND&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CheckResetPassword_Atsv', 1, null, '', 'OPCODE', 'CCEICheckResetPassword', '�¿ͷ�������֤���޸�', null);
--�����ն����֤У��ӿ�.sql
prompt delete CidCheck_Atsv...
delete from int_command_define where commandid='CidCheck_Atsv';
delete from int_command_param where commandid='CidCheck_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CidCheck_Atsv', '�����ն����֤У��ӿ�', 1, 1, 'CIntCommonDispatcher', to_date('16-08-2011', 'dd-mm-yyyy'), 1, null, '�����ն����֤У��ӿ�', '', 0, 1, 0, 0);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CidCheck_Atsv', 4, null, '100=0&101=103&102=-4999&103=988706&104=-1112', '', '', '����ֵת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CidCheck_Atsv', 3, 0, 'MAXFAILNUM=MAXFAILNUM&CURFAILNUM=CURFAILNUM', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CidCheck_Atsv', 2, 0, 'CERTID=CERTID&TELNUM=MSISDN&OPERID=OPERID', '', '', '�������ת��', null);
insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('CidCheck_Atsv', 1, null, '', 'OPCODE', 'CCEIIDCardCheck', '���֤У��', null);
--�����ն��û�����ѯ.sql
prompt delete UniGetBalanceByTelnum_Atsv...
delete from int_command_define where commandid='UniGetBalanceByTelnum_Atsv';
delete from int_command_param where commandid='UniGetBalanceByTelnum_Atsv';
insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('UniGetBalanceByTelnum_Atsv', '�����ն��û�����ѯ', 1, 1, 'CIntCommonDispatcher', to_date('24-08-2011 15:46:03', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '�����ն��û�����ѯ', '', 0, 0, 0, 0);
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
