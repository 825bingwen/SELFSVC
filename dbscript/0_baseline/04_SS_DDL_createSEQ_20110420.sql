--==============================================================
-- 本脚本包含以下内容（用户ngsh）
-- 1.删除Sequences (drop)
-- 2.建立Sequences 

-- 本脚本以ngsh用户执行
-- 
--==============================================================

set feedback off
set define off


-- 1.删除Sequences 
-- 
-- drop sequence SEQ_SH_RECOID ;
 


-- 2.建立Sequences 

prompt 建序列.....
 -- Create sequence 
create sequence SEQ_SH_RECOID
minvalue 10000000000000
maxvalue 99999999999999
start with 10000000001000
increment by 1
cache 100;



prompt  SQL EXECUTE  END...
-----------------------------------------------------
set feedback on
set define on
prompt Done.
