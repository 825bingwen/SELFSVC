--==============================================================
-- ���ű������������ݣ��û�ngsh��

-- 1.�����û���������ͼ

-- ���ű���ngsh�û�ִ��
-- 
--==============================================================
prompt drop sequence SEQ_SH_TERMID...
drop sequence SEQ_SH_TERMID;
prompt create sequence SEQ_SH_TERMID...
create sequence SEQ_SH_TERMID
minvalue 100000
maxvalue 999999
start with 100101
increment by 1
cache 100;

prompt drop sequence SEQ_SH_RECOID...
drop sequence SEQ_SH_RECOID;
prompt create sequence SEQ_SH_RECOID...
create sequence SEQ_SH_RECOID
minvalue 10000000000000
maxvalue 99999999999999
start with 10000000001001
increment by 1
cache 100;