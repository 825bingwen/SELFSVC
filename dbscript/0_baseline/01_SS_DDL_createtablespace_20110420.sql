--==============================================================
-- ���ű������������ݣ���DBA�û�ִ�У�
-- 1.����ռ�ǰ�ĳ�ʼ��
-- 2.������ռ�,ָ����ռ�ӵ�е������ļ�·���Լ���С�Ȳ���
-- ��ע:���������ļ�·��Ϊ���ݿ��ռ��������ļ�λ��,ssΪ���ݿ��SID
--==============================================================
    

-- 1. ����ռ�ǰȷ������δ����
--drop tablespace ngsh_data including contents;


-- 2. ������ռ�,ָ����ռ�ӵ�е������ļ���·������С��
--   �Լ��Զ���չ����(100M)��������(MAXSIZE UNLIMITED),
--   ��ռ�������(local)
CREATE TABLESPACE ngsh_data
  DATAFILE 'D:\oracle\oradata\ss\ngsh_data.dbf' SIZE 1024M REUSE 
  AUTOEXTEND ON NEXT 100M MAXSIZE UNLIMITED EXTENT MANAGEMENT LOCAL; 
