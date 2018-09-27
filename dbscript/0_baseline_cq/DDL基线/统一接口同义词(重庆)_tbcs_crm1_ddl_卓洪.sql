/*
确保以下数据表在tbcs下的同义词能用
select * from INT_ACCESS_POINT;
select * from INT_PROTOCOL_DEFINE;
select * from INT_BUSINESS_DEFINE;
select * from INT_FLOW_DEFINE;
select * from INT_RETCODE_CONVERT;
select * from INT_IP_AUTH;
select * from INT_USER_AUTH;
*/

--创建同义词
create or replace synonym INT_RETCODE_CONVERT
  for COMMON_DICT.INT_RETCODE_CONVERT;
create or replace synonym INT_PROTOCOL_DEFINE
  for COMMON_DICT.INT_PROTOCOL_DEFINE;