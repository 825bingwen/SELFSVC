alter table COMMON_DICT.INT_BUSINESS_DEFINE add COLPERF NUMBER(1) default 1;
comment on column COMMON_DICT.INT_BUSINESS_DEFINE.COLPERF
  is '指标采集 0：不采集指标，也不进行统计；1：采集指标，只进行统计，不记录详细日志；2：采集指标，进行统计，并且记录详细日志';
  
alter table COMMON_DICT.INT_FLOW_DEFINE add FLOWTYPE NUMBER(2) default 0;
comment on column COMMON_DICT.INT_FLOW_DEFINE.FLOWTYPE
  is '流程类型，0:客户端流程 1：服务端流程  2:opcode 虚拟用例';
  
alter table COMMON_DICT.INT_BUSINESS_DEFINE modify BUSITYPE VARCHAR2(32) default 'IntNGMain';
