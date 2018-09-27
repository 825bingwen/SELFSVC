--==============================================================
-- 本脚本包含以下内容（以用户common执行）
-- 1.删除common用户下SA_DB_SMTEMPLATE相关数据
-- 2.建立common用户下SA_DB_SMTEMPLATE相关数据
-- 
-- 本脚本以common用户执行
-- 
--==============================================================
set feedback off
set define off

-- delete SA_DB_SMTEMPLATE where template_no ='SELFSVCBUHUO';
-- delete SA_DB_SMTEMPLATE where template_no ='TERMEX1';
-- delete SA_DB_SMTEMPLATE where template_no ='TERMEX15';



insert into SA_DB_SMTEMPLATE (TEMPLATE_NO, TEMPLATE_TYPE, DESCRIPTION, SMS_PORT, DISP_FROM, REPORT, VALID_TIME, CONTENT, INTIME, DO_VALID_TIME, BEGINDATE, ENDDATE, PRIORITY, WORKTIME, LONGSMSTYPE, FEETYPE, FEECODE, OVERTIME, DELAYTIME, AUDITSTATUS, ENCPARA)
values ('SELFSVCBUHUO', 'REC003  ', '自助终端售货补货通知', '10086', '10086', '0', 86400, '终端机%1【%2】的货道%3现有商品%4的数量是%5，请及时补货。', sysdate, 86400, to_date('01-07-2010', 'dd-mm-yyyy'), to_date('01-01-2099', 'dd-mm-yyyy'), 1, '', 0, '1', '0', 0, 0, 1, '');

insert into SA_DB_SMTEMPLATE (TEMPLATE_NO, TEMPLATE_TYPE, DESCRIPTION, SMS_PORT, DISP_FROM, REPORT, VALID_TIME, CONTENT, INTIME, DO_VALID_TIME, BEGINDATE, ENDDATE, PRIORITY, WORKTIME, LONGSMSTYPE, FEETYPE, FEECODE, OVERTIME, DELAYTIME, AUDITSTATUS, ENCPARA)
values ('TERMEX1', 'REC003  ', '自助终端机异常', '10086', '10086', '0', 86400, '终端机%1【%2】%3！请您及时处理!', sysdate, 86400, to_date('01-01-1999', 'dd-mm-yyyy'), to_date('01-01-2099', 'dd-mm-yyyy'), 1, '', 0, '1', '0', 0, 0, 1, '');

insert into SA_DB_SMTEMPLATE (TEMPLATE_NO, TEMPLATE_TYPE, DESCRIPTION, SMS_PORT, DISP_FROM, REPORT, VALID_TIME, CONTENT, INTIME, DO_VALID_TIME, BEGINDATE, ENDDATE, PRIORITY, WORKTIME, LONGSMSTYPE, FEETYPE, FEECODE, OVERTIME, DELAYTIME, AUDITSTATUS, ENCPARA)
values ('TERMEX15', 'REC003  ', '自助终端机15分钟没有发送状态', '10086', '10086', '0', 86400, '终端机%1【%2】%3！请您及时处理！', sysdate, 86400, to_date('01-01-1999', 'dd-mm-yyyy'), to_date('01-01-2099', 'dd-mm-yyyy'), 1, '', 0, '1', '0', 0, 0, 1, '');


prompt 3 records loaded

set feedback on
set define on
prompt Done.
