--==============================================================
-- ���ű������������ݣ���DBA�û�ִ�У�
-- 1.�����û�ngshǰ�ĳ�ʼ����
-- 2.�����û�ngsh ������ ָ��Ĭ�ϱ�ռ� ��ʱ��ռ� ����Ȩ
--==============================================================

-- 1 . �����û�ngshǰȷ���û�δ����
-- drop user ngsh cascade;

-- 2 . �����û� ngsh ����Ϊ ngsh Ĭ�ϱ�ռ� ngsh_data ��ʱ��ռ�Ϊ temp ����Ȩ
create user ngsh identified by ngsh default tablespace NGSH_DATA temporary tablespace temp;

-- Grant/Revoke role privileges 
grant connect to ngsh with admin option;
grant dba to ngsh with admin option;
-- grant connect,resource,create public database link,drop public database link to ngsh;

-- Grant/Revoke system privileges 
grant unlimited tablespace to ngsh with admin option;

--alter user ngsh identified by u8tTVwe;