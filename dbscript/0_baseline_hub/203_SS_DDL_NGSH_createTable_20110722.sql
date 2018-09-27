--==============================================================
-- 注意：本脚本仅供开发测试使用包含以下内容（用户ngsh）
-- 在生产环境上： regionlis、操作员表、组织信息表、组织信息CHILD表 需要建立与tbcs的同义词
-- 请参考脚本“NG自助终端同义词_NGSH_DDL.sql”

-- 1.删除用户下所有数据库表(drop)
-- 2.建立用户下所有数据库表,索引，主键

-- 本脚本以ngsh用户执行
-- 
--==============================================================
set feedback off
set define off
-----------------------------
prompt Dropping SH_INFO_REGIONLIST...
drop table SH_INFO_REGIONLIST cascade constraints;
prompt Dropping SH_INFO_OPERATOR...
drop table SH_INFO_OPERATOR cascade constraints;
prompt Dropping SH_ORGANIZATION...
drop table SH_ORGANIZATION cascade constraints;
prompt Dropping SH_ORGANIZATION_CHILD...
drop table SH_ORGANIZATION_CHILD cascade constraints;



prompt Creating SH_INFO_REGIONLIST...
create table SH_INFO_REGIONLIST
(
  REGION     VARCHAR2(10) not null,
  CAPABILITY NUMBER(7,2) not null,
  REGIONNAME VARCHAR2(20),
  ORGID      VARCHAR2(20),
  CITY_ID    CHAR(1),
  PARTITION  VARCHAR2(2),
  ZIP        VARCHAR2(7),
  INT_BIT    NUMBER(1),
  DBAREA     VARCHAR2(8),
  DBSID      VARCHAR2(8)
)
;
comment on table SH_INFO_REGIONLIST is '地区列表';
comment on column SH_INFO_REGIONLIST.REGION is '地区';
comment on column SH_INFO_REGIONLIST.CAPABILITY is '容量';
comment on column SH_INFO_REGIONLIST.REGIONNAME is '地区名称';
comment on column SH_INFO_REGIONLIST.ORGID is '单位';
comment on column SH_INFO_REGIONLIST.CITY_ID is '城市编号';
comment on column SH_INFO_REGIONLIST.PARTITION is '分区标志';
comment on column SH_INFO_REGIONLIST.ZIP is '压缩标志';
comment on column SH_INFO_REGIONLIST.INT_BIT is 'INT_BIT';
comment on column SH_INFO_REGIONLIST.DBAREA is '数据库区域';
comment on column SH_INFO_REGIONLIST.DBSID is '数据库ID';

prompt Creating SH_INFO_OPERATOR...
create table SH_INFO_OPERATOR
(
  OPERID           VARCHAR2(20),
  REGION           NUMBER(5),
  OPERNAME         VARCHAR2(100),
  PASSWORD         VARCHAR2(200),
  PASSCHGDATE      DATE,
  OPERGROUP        VARCHAR2(20),
  OPERTYPE         VARCHAR2(16),
  OPERLEVEL        VARCHAR2(20),
  ISMANAGER        NUMBER(1),
  CONTACTPHONE     VARCHAR2(20),
  ORGID            VARCHAR2(32),
  ISRESTRICT       NUMBER(1),
  STARTTIME        NUMBER(2),
  ENDTIME          NUMBER(2),
  ENABLEGPRS       NUMBER(1),
  GPRSSTARTTIME    NUMBER(2),
  GPRSENDTIME      NUMBER(2),
  ISCHKMAC         NUMBER(2),
  MAC              VARCHAR2(64),
  NOTES            VARCHAR2(256),
  CREATEDATE       DATE,
  STATUS           VARCHAR2(2),
  STATUSDATE       DATE,
  RELE_STAFF_ID    VARCHAR2(20),
  START_USING_TIME DATE,
  END_USING_TIME   DATE,
  LOGINTYPE        VARCHAR2(16),
  AREAID           VARCHAR2(32),
  ROLEID           VARCHAR2(32)
)
;
comment on table SH_INFO_OPERATOR is '	终端操作员表';
comment on column SH_INFO_OPERATOR.OPERID is '操作员ID';
comment on column SH_INFO_OPERATOR.REGION is '区域编码';
comment on column SH_INFO_OPERATOR.OPERNAME is '操作员名称';
comment on column SH_INFO_OPERATOR.PASSWORD is '密码';
comment on column SH_INFO_OPERATOR.PASSCHGDATE is '密码修改时间';
comment on column SH_INFO_OPERATOR.OPERGROUP is '操作员组';
comment on column SH_INFO_OPERATOR.OPERTYPE is '操作员类型';
comment on column SH_INFO_OPERATOR.OPERLEVEL is '操作员级别';
comment on column SH_INFO_OPERATOR.ISMANAGER is '是否否管理';
comment on column SH_INFO_OPERATOR.CONTACTPHONE is '电话号码';
comment on column SH_INFO_OPERATOR.ORGID is '片区编码';
comment on column SH_INFO_OPERATOR.STARTTIME is '开始时间';
comment on column SH_INFO_OPERATOR.ENDTIME is '结束时间';
comment on column SH_INFO_OPERATOR.ENABLEGPRS is 'GPRS是否可用';
comment on column SH_INFO_OPERATOR.GPRSSTARTTIME is 'GPRS开始时间';
comment on column SH_INFO_OPERATOR.GPRSENDTIME is 'GPRS结束时间';
comment on column SH_INFO_OPERATOR.ISCHKMAC is '是否校验MAC地址';
comment on column SH_INFO_OPERATOR.MAC is 'MAC地址';
comment on column SH_INFO_OPERATOR.NOTES is '备注';
comment on column SH_INFO_OPERATOR.CREATEDATE is '创建时间';
comment on column SH_INFO_OPERATOR.STATUS is '状态';
comment on column SH_INFO_OPERATOR.STATUSDATE is '状态时间';
comment on column SH_INFO_OPERATOR.LOGINTYPE is '登录类型';
comment on column SH_INFO_OPERATOR.AREAID is '区域ID';

prompt Creating SH_ORGANIZATION...
create table SH_ORGANIZATION
(
  ORGID        VARCHAR2(32) not null,
  REGION       NUMBER,
  ORGNAME      VARCHAR2(200),
  ORGTYPE      VARCHAR2(20),
  ORGLEVEL     NUMBER(2),
  ISINNER      NUMBER(1),
  MANAGERID    VARCHAR2(32),
  PARENTID     VARCHAR2(32),
  OPENPHONE    VARCHAR2(20),
  ADDRESS      VARCHAR2(128),
  POSTCODE     VARCHAR2(10),
  CONTACTMAN   VARCHAR2(64),
  CONTACTPHONE VARCHAR2(20),
  FAX          VARCHAR2(20),
  EMAIL        VARCHAR2(64),
  COUNTY       VARCHAR2(128),
  AREAID       VARCHAR2(32),
  OWNERTYPE    VARCHAR2(32),
  NOTES        VARCHAR2(256),
  CREATEDATE   DATE,
  STATUS       NUMBER,
  STATUSDATE   DATE
)
;
comment on table SH_ORGANIZATION is '  组织机构基本信息表，用于存储组织机构的基本信息。';
create unique index IDX_SH_ORGID on SH_ORGANIZATION (ORGID);
create index IDX_SH_STATUS on SH_ORGANIZATION (STATUS, PARENTID);

prompt Creating SH_ORGANIZATION_CHILD...
create table SH_ORGANIZATION_CHILD
(
  PARWAYID    VARCHAR2(32) not null,
  SUBWAYID    VARCHAR2(32) not null,
  HICHYOFFSET NUMBER(3) not null,
  CREATETIME  DATE,
  REGION      NUMBER(5),
  SUBWAYNAME  VARCHAR2(200)
)
;
comment on table SH_ORGANIZATION_CHILD is '  营业组织结构关系表';
create unique index IDX_SH_ORGACHILD on SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID);

set feedback on
set define on
prompt Done.
