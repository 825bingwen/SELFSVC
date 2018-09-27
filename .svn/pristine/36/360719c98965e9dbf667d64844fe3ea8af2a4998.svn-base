--==============================================================
-- 本脚本包含以下内容（以DBA用户执行）
-- 1.建立用户ngsht前的初始化，
-- 2.建立用户ngsht 的密码 指定默认表空间 临时表空间 并授权
--==============================================================

-- 1 . 建立用户ngsht前确定用户未存在
-- drop user ngsht cascade;

-- 2 . 建立用户 ngsht 密码为 ngsht 默认表空间 ngsh_data 临时表空间为 temp 并授权
create user ngsht identified by ngsht default tablespace NGSH_DATA temporary tablespace temp;

-- Grant/Revoke role privileges 
grant connect to ngsht with admin option;
grant dba to ngsht with admin option;
-- grant connect,resource,create public database link,drop public database link to ngsht;

-- Grant/Revoke system privileges 
grant unlimited tablespace to ngsht with admin option;

--alter user ngsht identified by u8tTVwe;