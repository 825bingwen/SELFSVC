--==============================================================
-- ���ű������������ݣ���DBA�û�ִ�У�
-- 1.�����û�ngshtǰ�ĳ�ʼ����
-- 2.�����û�ngsht ������ ָ��Ĭ�ϱ�ռ� ��ʱ��ռ� ����Ȩ
--==============================================================

-- 1 . �����û�ngshtǰȷ���û�δ����
-- drop user ngsht cascade;

-- 2 . �����û� ngsht ����Ϊ ngsht Ĭ�ϱ�ռ� ngsh_data ��ʱ��ռ�Ϊ temp ����Ȩ
create user ngsht identified by ngsht default tablespace NGSH_DATA temporary tablespace temp;

-- Grant/Revoke role privileges 
grant connect to ngsht with admin option;
grant dba to ngsht with admin option;
-- grant connect,resource,create public database link,drop public database link to ngsht;

-- Grant/Revoke system privileges 
grant unlimited tablespace to ngsht with admin option;

--alter user ngsht identified by u8tTVwe;