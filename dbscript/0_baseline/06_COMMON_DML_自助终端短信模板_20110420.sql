--==============================================================
-- ���ű������������ݣ����û�commonִ�У�
-- 1.ɾ��common�û���SA_DB_SMTEMPLATE�������
-- 2.����common�û���SA_DB_SMTEMPLATE�������
-- 
-- ���ű���common�û�ִ��
-- 
--==============================================================
set feedback off
set define off

-- delete SA_DB_SMTEMPLATE where template_no ='SELFSVCBUHUO';
-- delete SA_DB_SMTEMPLATE where template_no ='TERMEX1';
-- delete SA_DB_SMTEMPLATE where template_no ='TERMEX15';



insert into SA_DB_SMTEMPLATE (TEMPLATE_NO, TEMPLATE_TYPE, DESCRIPTION, SMS_PORT, DISP_FROM, REPORT, VALID_TIME, CONTENT, INTIME, DO_VALID_TIME, BEGINDATE, ENDDATE, PRIORITY, WORKTIME, LONGSMSTYPE, FEETYPE, FEECODE, OVERTIME, DELAYTIME, AUDITSTATUS, ENCPARA)
values ('SELFSVCBUHUO', 'REC003  ', '�����ն��ۻ�����֪ͨ', '10086', '10086', '0', 86400, '�ն˻�%1��%2���Ļ���%3������Ʒ%4��������%5���뼰ʱ������', sysdate, 86400, to_date('01-07-2010', 'dd-mm-yyyy'), to_date('01-01-2099', 'dd-mm-yyyy'), 1, '', 0, '1', '0', 0, 0, 1, '');

insert into SA_DB_SMTEMPLATE (TEMPLATE_NO, TEMPLATE_TYPE, DESCRIPTION, SMS_PORT, DISP_FROM, REPORT, VALID_TIME, CONTENT, INTIME, DO_VALID_TIME, BEGINDATE, ENDDATE, PRIORITY, WORKTIME, LONGSMSTYPE, FEETYPE, FEECODE, OVERTIME, DELAYTIME, AUDITSTATUS, ENCPARA)
values ('TERMEX1', 'REC003  ', '�����ն˻��쳣', '10086', '10086', '0', 86400, '�ն˻�%1��%2��%3��������ʱ����!', sysdate, 86400, to_date('01-01-1999', 'dd-mm-yyyy'), to_date('01-01-2099', 'dd-mm-yyyy'), 1, '', 0, '1', '0', 0, 0, 1, '');

insert into SA_DB_SMTEMPLATE (TEMPLATE_NO, TEMPLATE_TYPE, DESCRIPTION, SMS_PORT, DISP_FROM, REPORT, VALID_TIME, CONTENT, INTIME, DO_VALID_TIME, BEGINDATE, ENDDATE, PRIORITY, WORKTIME, LONGSMSTYPE, FEETYPE, FEECODE, OVERTIME, DELAYTIME, AUDITSTATUS, ENCPARA)
values ('TERMEX15', 'REC003  ', '�����ն˻�15����û�з���״̬', '10086', '10086', '0', 86400, '�ն˻�%1��%2��%3��������ʱ����', sysdate, 86400, to_date('01-01-1999', 'dd-mm-yyyy'), to_date('01-01-2099', 'dd-mm-yyyy'), 1, '', 0, '1', '0', 0, 0, 1, '');


prompt 3 records loaded

set feedback on
set define on
prompt Done.
