alter table COMMON_DICT.INT_BUSINESS_DEFINE add COLPERF NUMBER(1) default 1;
comment on column COMMON_DICT.INT_BUSINESS_DEFINE.COLPERF
  is 'ָ��ɼ� 0�����ɼ�ָ�꣬Ҳ������ͳ�ƣ�1���ɼ�ָ�ֻ꣬����ͳ�ƣ�����¼��ϸ��־��2���ɼ�ָ�꣬����ͳ�ƣ����Ҽ�¼��ϸ��־';
  
alter table COMMON_DICT.INT_FLOW_DEFINE add FLOWTYPE NUMBER(2) default 0;
comment on column COMMON_DICT.INT_FLOW_DEFINE.FLOWTYPE
  is '�������ͣ�0:�ͻ������� 1�����������  2:opcode ��������';
  
alter table COMMON_DICT.INT_BUSINESS_DEFINE modify BUSITYPE VARCHAR2(32) default 'IntNGMain';
