--==============================================================
-- 本脚本包含以下内容（以DBA用户执行）
-- 1.建表空间前的初始化
-- 2.建立表空间,指定表空间拥有的数据文件路径以及大小等参数
-- 备注:其中数据文件路径为数据库表空间存放数据文件位置,ss为数据库的SID
--==============================================================
    

-- 1. 建表空间前确定对象未存在
--drop tablespace ngsh_data including contents;


-- 2. 建立表空间,指定表空间拥有的数据文件，路径，大小，
--   以及自动扩展参数(100M)，无上限(MAXSIZE UNLIMITED),
--   表空间管理参数(local)
CREATE TABLESPACE ngsh_data
  DATAFILE 'D:\oracle\oradata\ss\ngsh_data.dbf' SIZE 1024M REUSE 
  AUTOEXTEND ON NEXT 100M MAXSIZE UNLIMITED EXTENT MANAGEMENT LOCAL; 
