--==============================================================
-- 本脚本包含以下内容（以DBA用户执行）
-- 1.建立用户ngsh前的初始化，
-- 2.建立用户ngsh 的密码 指定默认表空间 临时表空间 并授权
--==============================================================

-- 1 . 建立用户ngsh前确定用户未存在
-- drop user ngsh cascade;

-- 2 . 建立用户 ngsh 密码为 ngsh 默认表空间 ngsh_data 临时表空间为 temp 并授权
create user ngsh identified by ngsh default tablespace NGSH_DATA temporary tablespace temp;

-- Grant/Revoke role privileges 
grant connect to ngsh with admin option;
grant dba to ngsh with admin option;
-- grant connect,resource,create public database link,drop public database link to ngsh;

-- Grant/Revoke system privileges 
grant unlimited tablespace to ngsh with admin option;

--alter user ngsh identified by u8tTVwe;