--==============================================================
-- 本脚本包含以下内容（以DBA用户执行）
-- 1.建立用户ngsht前的初始化，
-- 2.建立用户ngsht 的密码 指定默认表空间 临时表空间 并授权
--==============================================================

-- 1 . 建立用户ngsht前确定用户未存在
-- drop user ngsh cascade;

-- 2 . 建立用户 ngsh 密码为 ngsh 默认表空间 d_data 临时表空间为 temp 并授权
create user ngsh identified by ngsh default tablespace d_data temporary tablespace temp;

-- Grant/Revoke role privileges 
--grant connect to ngsh with admin option;  --dba评审不执行
--grant dba to ngsht with admin option;   --dba评审这个权限太大,不执行
-- grant connect,resource,create public database link,drop public database link to ngsht;

-- Grant/Revoke system privileges 
--grant unlimited tablespace to ngsht with admin option;   --dba评审,不需要执行

--alter user ngsht identified by u8tTVwe;

grant connect,resource to ngsh;
grant select on tbcs.operator to ngsh;
grant select on tbcs.organization to ngsh;
grant select on icdpub.T_UCP_ORGACHILD to ngsh;
grant select on common_dict.SA_DB_REGION_LIST to ngsh;
