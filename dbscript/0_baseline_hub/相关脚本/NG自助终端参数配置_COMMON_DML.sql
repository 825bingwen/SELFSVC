--�����ӿڲ���Ա����
delete from SA_DB_ACCAGENTUNIT where agentunit='bsacAtsv';

insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBWH270', '270', 'bsacAtsv');
insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBXF710', '710', 'bsacAtsv');
insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBEZ711', '711', 'bsacAtsv');
insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBXG712', '712', 'bsacAtsv');
insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBHG713', '713', 'bsacAtsv');
insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBHS714', '714', 'bsacAtsv');
insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBXN715', '715', 'bsacAtsv');
insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBJZ716', '716', 'bsacAtsv');
insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBYC717', '717', 'bsacAtsv');
insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBNS718', '718', 'bsacAtsv');
insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBSY719', '719', 'bsacAtsv');
insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBSZ722', '722', 'bsacAtsv');
insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBJM724', '724', 'bsacAtsv');
insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBJH728', '728', 'bsacAtsv');
insert into SA_DB_ACCAGENTUNIT (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE) 
values ('bsacAtsv', 'һ��BOSS���⹤��', 'IBWH270', '999', 'bsacAtsv');

--�����ն˷��Ͷ���ͨ��ģ��
delete SA_DB_SMTEMPLATE where TEMPLATE_NO = 'Atsv0001';

insert into SA_DB_SMTEMPLATE (TEMPLATE_NO, TEMPLATE_TYPE, DESCRIPTION, SMS_PORT, DISP_FROM, REPORT, VALID_TIME, CONTENT, INTIME, DO_VALID_TIME, BEGINDATE, ENDDATE, PRIORITY, WORKTIME, LONGSMSTYPE, FEETYPE, FEECODE, OVERTIME, DELAYTIME, AUDITSTATUS, ENCPARA)
values ('Atsv0001', 'REC003  ', '�����ն˷��Ͷ���ͨ��ģ��', '10086', '10086', '0', 86400, '%1', sysdate, 86400, to_date('01-01-1999', 'dd-mm-yyyy'), to_date('01-01-2099', 'dd-mm-yyyy'), 1, '', 0, '1', '0', 0, 0, 1, '');
