--==============================================================
-- 本脚本包含以下内容

-- 1.删除用户下所有数据库表(drop)
-- 2.建立用户下所有数据库表,索引，主键

-- 本脚本以新建用户执行
-- 
--==============================================================
set feedback off
set define off
------------------------------
prompt Dropping SH_ALARM_BIND...
drop table SH_ALARM_BIND cascade constraints;
prompt Dropping SH_ALARM_INFO...
drop table SH_ALARM_INFO cascade constraints;
prompt Dropping SH_ALARM_RULE...
drop table SH_ALARM_RULE cascade constraints;
prompt Dropping SH_CASHFILE_LOG...
drop table SH_CASHFILE_LOG cascade constraints;
prompt Dropping SH_CASHRECORD_LOG...
drop table SH_CASHRECORD_LOG cascade constraints;
prompt Dropping SH_CHARGE_LOG...
drop table SH_CHARGE_LOG cascade constraints;
prompt Dropping SH_CHECK_LOG...
drop table SH_CHECK_LOG cascade constraints;
prompt Dropping SH_CHOOSETEL_NUM...
drop table SH_CHOOSETEL_NUM cascade constraints;
prompt Dropping SH_COMMONPAY_LOG...
drop table SH_COMMONPAY_LOG cascade constraints;
prompt Dropping SH_DICT_ITEM...
drop table SH_DICT_ITEM cascade constraints;
prompt Dropping SH_GROUP_ADV...
drop table SH_GROUP_ADV cascade constraints;
prompt Dropping SH_GROUP_BUDDY...
drop table SH_GROUP_BUDDY cascade constraints;
prompt Dropping SH_GROUP_INFO...
drop table SH_GROUP_INFO cascade constraints;
prompt Dropping SH_GROUP_MENU...
drop table SH_GROUP_MENU cascade constraints;
prompt Dropping SH_GROUP_SCREEN...
drop table SH_GROUP_SCREEN cascade constraints;
prompt Dropping SH_INFO_ABATE...
drop table SH_INFO_ABATE cascade constraints;
prompt Dropping SH_INFO_BUDDY...
drop table SH_INFO_BUDDY cascade constraints;
prompt Dropping SH_INFO_MANUFACTURER...
drop table SH_INFO_MANUFACTURER cascade constraints;
prompt Dropping SH_INFO_MEDIARES...
drop table SH_INFO_MEDIARES cascade constraints;
prompt Dropping SH_INFO_PLUGIN...
drop table SH_INFO_PLUGIN cascade constraints;

prompt Dropping SH_LOG_ABATE...
drop table SH_LOG_ABATE cascade constraints;
prompt Dropping SH_LOG_AUDIT...
drop table SH_LOG_AUDIT cascade constraints;
prompt Dropping SH_LOG_BUSINESS...
drop table SH_LOG_BUSINESS cascade constraints;
prompt Dropping SH_LOG_CASHRETURN...
drop table SH_LOG_CASHRETURN cascade constraints;
prompt Dropping SH_LOG_CHECKBILL...
drop table SH_LOG_CHECKBILL cascade constraints;
prompt Dropping SH_LOG_LOGINERRNUM...
drop table SH_LOG_LOGINERRNUM cascade constraints;
prompt Dropping SH_LOG_MOBILEPAY...
drop table SH_LOG_MOBILEPAY cascade constraints;
prompt Dropping SH_LOG_MOBILEWALLET...
drop table SH_LOG_MOBILEWALLET cascade constraints;
prompt Dropping SH_LOG_PRINTBILLNUM...
drop table SH_LOG_PRINTBILLNUM cascade constraints;
prompt Dropping SH_LOG_PRINTINVOICE...
drop table SH_LOG_PRINTINVOICE cascade constraints;
prompt Dropping SH_LOG_PRIV...
drop table SH_LOG_PRIV cascade constraints;
prompt Dropping SH_LOG_PRODCHANGE...
drop table SH_LOG_PRODCHANGE cascade constraints;
prompt Dropping SH_LOG_TERMSTATUS...
drop table SH_LOG_TERMSTATUS cascade constraints;
prompt Dropping SH_LOG_TERMUP...
drop table SH_LOG_TERMUP cascade constraints;
prompt Dropping SH_MENU_ITEM...
drop table SH_MENU_ITEM cascade constraints;
prompt Dropping SH_MENU_ITEM_MGR...
drop table SH_MENU_ITEM_MGR cascade constraints;
prompt Dropping SH_OPERATOR_ROLE...
drop table SH_OPERATOR_ROLE cascade constraints;
prompt Dropping SH_PARAM...
drop table SH_PARAM cascade constraints;
prompt Dropping SH_PARAM_VALUE...
drop table SH_PARAM_VALUE cascade constraints;
prompt Dropping SH_PC_DSMP_SPBIZINFO...
drop table SH_PC_DSMP_SPBIZINFO cascade constraints;
prompt Dropping SH_RANDOM_PASSWORD...
drop table SH_RANDOM_PASSWORD cascade constraints;
prompt Dropping SH_REC_LOG...
drop table SH_REC_LOG cascade constraints;
prompt Dropping SH_REC_LOG_MGR...
drop table SH_REC_LOG_MGR cascade constraints;
prompt Dropping SH_ROLE_INFO...
drop table SH_ROLE_INFO cascade constraints;
prompt Dropping SH_ROLE_MENU...
drop table SH_ROLE_MENU cascade constraints;
prompt Dropping SH_SELLGOODS_LOG...
drop table SH_SELLGOODS_LOG cascade constraints;
prompt Dropping SH_TERM_CONFIG...
drop table SH_TERM_CONFIG cascade constraints;
prompt Dropping SH_TERM_GROUP...
drop table SH_TERM_GROUP cascade constraints;
prompt Dropping SH_TERM_MODEL...
drop table SH_TERM_MODEL cascade constraints;
prompt Dropping SH_UNIONFILE_LOG...
drop table SH_UNIONFILE_LOG cascade constraints;
prompt Dropping SH_UNIONPAY_LOG...
drop table SH_UNIONPAY_LOG cascade constraints;
prompt Dropping SH_UNIONRECORD_LOG...
drop table SH_UNIONRECORD_LOG cascade constraints;
prompt Dropping SH_UNIONRECORD_LOG_SD...
drop table SH_UNIONRECORD_LOG_SD cascade constraints;
prompt Creating SH_ALARM_BIND...
create table SH_ALARM_BIND
(
  ALARMBINDID VARCHAR2(32) not null,
  TERMID      VARCHAR2(32) not null,
  SERVNUM     VARCHAR2(32) not null,
  STATUS      NUMBER(1)
)
;
comment on table SH_ALARM_BIND is '	终端告警绑定信息';
comment on column SH_ALARM_BIND.ALARMBINDID is '告警绑定(序列：SEQ_SH_RECOID)';
comment on column SH_ALARM_BIND.TERMID is '终端编号';
comment on column SH_ALARM_BIND.SERVNUM is '告警手机';
comment on column SH_ALARM_BIND.STATUS is '状态  1：有效 0：无效';

prompt Creating SH_ALARM_INFO...
create table SH_ALARM_INFO
(
  ALARMINFOID   VARCHAR2(32) not null,
  ALARMDATE     DATE not null,
  EXCEPTIONTYPE VARCHAR2(32) not null,
  TERMID        VARCHAR2(32) not null,
  ALARMLEVEL    NUMBER(1),
  UNCHAINDATE   DATE,
  PERSISTDEGREE NUMBER(2),
  DEALER        VARCHAR2(32),
  DEALTIME      DATE,
  DEALDESP      VARCHAR2(256),
  UNCHAIN       NUMBER(1)
)
;
comment on table SH_ALARM_INFO is '	终端告警信息';
comment on column SH_ALARM_INFO.ALARMINFOID is '告警绑定(序列：SEQ_SH_RECOID)';
comment on column SH_ALARM_INFO.ALARMDATE is '告警时间';
comment on column SH_ALARM_INFO.EXCEPTIONTYPE is '异常类型';
comment on column SH_ALARM_INFO.TERMID is '终端ID';
comment on column SH_ALARM_INFO.ALARMLEVEL is '告警级别。0，普通；1，重要；2，严重。';
comment on column SH_ALARM_INFO.UNCHAINDATE is '告警解除时间';
comment on column SH_ALARM_INFO.PERSISTDEGREE is '发送告警短信的次数';
comment on column SH_ALARM_INFO.DEALER is '处理人';
comment on column SH_ALARM_INFO.DEALTIME is '处理时间';
comment on column SH_ALARM_INFO.DEALDESP is '处理方法';
comment on column SH_ALARM_INFO.UNCHAIN is '告警解除状态(0:未解除 1:解除)';

prompt Creating SH_ALARM_RULE...
create table SH_ALARM_RULE
(
  ALARMRULEID    VARCHAR2(32) not null,
  EXCEPTIONTYPE  VARCHAR2(32) not null,
  STARTNUM       VARCHAR2(32) not null,
  ENDNUM         VARCHAR2(32) not null,
  STATUS         NUMBER(1) not null,
  NOTES          VARCHAR2(256),
  STATUSDATE     DATE not null,
  CREATEOPERATOR VARCHAR2(32)
)
;
comment on table SH_ALARM_RULE is '	终端告警规则';
comment on column SH_ALARM_RULE.ALARMRULEID is 'ID(序列:SEQ_SH_RECOID)';
comment on column SH_ALARM_RULE.EXCEPTIONTYPE is '导常类型';
comment on column SH_ALARM_RULE.STARTNUM is '开始分钟';
comment on column SH_ALARM_RULE.ENDNUM is '结束分钟';
comment on column SH_ALARM_RULE.STATUS is '状态(1：未处理 0：已处理)';
comment on column SH_ALARM_RULE.NOTES is '备注';
comment on column SH_ALARM_RULE.STATUSDATE is '状态时间';
comment on column SH_ALARM_RULE.CREATEOPERATOR is '创建用户';

prompt Creating SH_CASHFILE_LOG...
create table SH_CASHFILE_LOG
(
  FILEOID        VARCHAR2(32),
  FILENAME       VARCHAR2(100),
  FILECREATEDATE DATE,
  IPADDR         VARCHAR2(32),
  RECNUM         NUMBER(14),
  RECFEE         NUMBER(14),
  RESULTSTATUS   CHAR(1),
  RESULTINFO     VARCHAR2(100),
  RECDATE        DATE
)
;
comment on column SH_CASHFILE_LOG.FILEOID is '日志流水号';
comment on column SH_CASHFILE_LOG.FILENAME is '文件名称';
comment on column SH_CASHFILE_LOG.FILECREATEDATE is '文件创建时间';
comment on column SH_CASHFILE_LOG.IPADDR is 'IP地址';
comment on column SH_CASHFILE_LOG.RECNUM is '文件内数据量';
comment on column SH_CASHFILE_LOG.RECFEE is '文件内总金额';
comment on column SH_CASHFILE_LOG.RESULTSTATUS is '文件处理结果 0:正常（入库） 1:格式错误（入库） 2:入库异常（等待下次处理）';
comment on column SH_CASHFILE_LOG.RESULTINFO is '文件处理详细信息';
comment on column SH_CASHFILE_LOG.RECDATE is '入库时间';

prompt Creating SH_CASHRECORD_LOG...
create table SH_CASHRECORD_LOG
(
  ID            VARCHAR2(32),
  FORMNUM       VARCHAR2(32),
  SERVNUM       VARCHAR2(11),
  CASHFEE       NUMBER(14),
  RECDATE       DATE,
  ORGID         VARCHAR2(32),
  REGION        VARCHAR2(32),
  FILEOID       VARCHAR2(32),
  CREATEDATE    DATE,
  COMPARESTATUS CHAR(1),
  TERMID        VARCHAR2(32)
)
;
comment on column SH_CASHRECORD_LOG.ID is '唯一标识';
comment on column SH_CASHRECORD_LOG.FORMNUM is '现金缴费流水号';
comment on column SH_CASHRECORD_LOG.SERVNUM is '手机号码';
comment on column SH_CASHRECORD_LOG.CASHFEE is '金额';
comment on column SH_CASHRECORD_LOG.RECDATE is '缴款日期';
comment on column SH_CASHRECORD_LOG.ORGID is '渠道编码';
comment on column SH_CASHRECORD_LOG.REGION is '地区';
comment on column SH_CASHRECORD_LOG.FILEOID is '入库文件流水号';
comment on column SH_CASHRECORD_LOG.CREATEDATE is '入库时间';
comment on column SH_CASHRECORD_LOG.COMPARESTATUS is '对账状态（0：未对账、1：对账成功  2：对账失败）';
comment on column SH_CASHRECORD_LOG.TERMID is '终端ID';

prompt Creating SH_CHARGE_LOG...
create table SH_CHARGE_LOG
(
  CHARGELOGOID          VARCHAR2(32),
  REGION                NUMBER(5),
  TERMID                VARCHAR2(32),
  OPERID                VARCHAR2(20),
  SERVNUMBER            VARCHAR2(32),
  PAYTYPE               VARCHAR2(1),
  FEE                   NUMBER(16),
  UNIONPAYUSER          VARCHAR2(32),
  UNIONPAYCODE          VARCHAR2(32),
  BUSITYPE              VARCHAR2(32),
  CARDNUMBER            VARCHAR2(32),
  BATCHNUM              VARCHAR2(32),
  AUTHORIZATIONCODE     VARCHAR2(32),
  BUSINESSREFERENCENUM  VARCHAR2(32),
  UNIONPAYTIME          DATE,
  VOUCHERNUM            VARCHAR2(32),
  UNIONPAYFEE           NUMBER(16),
  TERMINALSEQ           VARCHAR2(32),
  RECDATE               DATE,
  STATUS                VARCHAR2(5),
  DESCRIPTION           VARCHAR2(1024),
  DEALNUM               VARCHAR2(32),
  ACCEPTTYPE            VARCHAR2(32),
  SERVREGION            NUMBER(5),
  CHECKFLAG             NUMBER(1) default 0,
  ORGID                 VARCHAR2(32),
  POSNUM                VARCHAR2(32),
  BATCHNUMBEFOREKOUKUAN VARCHAR2(32),
  CHECKRESULT           NUMBER(1) default 9,
  RECTYPE               VARCHAR2(32),
  REPAIRDATE            DATE
)
;
comment on table SH_CHARGE_LOG is '	话费缴费日志表';
comment on column SH_CHARGE_LOG.CHARGELOGOID is '缴费日志唯一标识(SEQ_SH_RECOID)';
comment on column SH_CHARGE_LOG.REGION is '缴费地点';
comment on column SH_CHARGE_LOG.TERMID is '终端机编号';
comment on column SH_CHARGE_LOG.OPERID is '操作员编号';
comment on column SH_CHARGE_LOG.SERVNUMBER is '缴费号码';
comment on column SH_CHARGE_LOG.PAYTYPE is '缴费方式，1：银联卡；4：现金';
comment on column SH_CHARGE_LOG.FEE is '缴费金额，单位（分）';
comment on column SH_CHARGE_LOG.UNIONPAYUSER is '商户编号';
comment on column SH_CHARGE_LOG.UNIONPAYCODE is '湖北：终端编号；POS机编号';
comment on column SH_CHARGE_LOG.BUSITYPE is '交易类型';
comment on column SH_CHARGE_LOG.CARDNUMBER is '银行卡号';
comment on column SH_CHARGE_LOG.BATCHNUM is '扣款之后的批次号';
comment on column SH_CHARGE_LOG.AUTHORIZATIONCODE is '授权码';
comment on column SH_CHARGE_LOG.BUSINESSREFERENCENUM is '交易参考号';
comment on column SH_CHARGE_LOG.UNIONPAYTIME is '银联扣款日期时间';
comment on column SH_CHARGE_LOG.VOUCHERNUM is '凭证号';
comment on column SH_CHARGE_LOG.UNIONPAYFEE is '银联扣款金额，单位（分）';
comment on column SH_CHARGE_LOG.TERMINALSEQ is '交易流水号，即终端流水号';
comment on column SH_CHARGE_LOG.RECDATE is '交易时间';
comment on column SH_CHARGE_LOG.STATUS is '交易状态，11：缴费成功；10：投币成功或者银联扣款成功，但是缴费失败；01：投币成功或者银联扣款成功的临时状态；00：除10外其它缴费失败的情况';
comment on column SH_CHARGE_LOG.DESCRIPTION is '交易结果描述';
comment on column SH_CHARGE_LOG.DEALNUM is 'BOSS受理编号';
comment on column SH_CHARGE_LOG.ACCEPTTYPE is 'BOSS受理类型';
comment on column SH_CHARGE_LOG.SERVREGION is '缴费号码的归属地市';
comment on column SH_CHARGE_LOG.CHECKFLAG is '1，已对账；其它值，未对账';
comment on column SH_CHARGE_LOG.ORGID is '组织结构id';
comment on column SH_CHARGE_LOG.POSNUM is '湖北：扣款之前的跟踪号';
comment on column SH_CHARGE_LOG.BATCHNUMBEFOREKOUKUAN is '湖北：扣款之前的批次号';
comment on column SH_CHARGE_LOG.CHECKRESULT is '对账结果 0成功  1失败   2需补缴 3已补缴 4已回退 9未对账';
comment on column SH_CHARGE_LOG.RECTYPE is '业务类型（phone：话费缴费  favourable：优惠缴费 mpay：主账户充值）';
comment on column SH_CHARGE_LOG.REPAIRDATE is '补缴费时间';
create index IDX_SH_CHARGELOG_REGION on SH_CHARGE_LOG (REGION);
create index IDX_SH_CHARGELOG_SERV on SH_CHARGE_LOG (SERVNUMBER);

prompt Creating SH_CHECK_LOG...
create table SH_CHECK_LOG
(
  TERMID         VARCHAR2(32) not null,
  LOGTYPE        VARCHAR2(16) not null,
  LASTUPLOADTIME VARCHAR2(32),
  UPLOADFILENAME VARCHAR2(32),
  CHECKTIME      VARCHAR2(32),
  CHECKRESULT    VARCHAR2(128),
  TERMLOGCHECKID VARCHAR2(16) not null
)
;
comment on table SH_CHECK_LOG is '终端机日志上传情况';
comment on column SH_CHECK_LOG.TERMID is '终端编号';
comment on column SH_CHECK_LOG.LOGTYPE is '日志文件类型';
comment on column SH_CHECK_LOG.LASTUPLOADTIME is '最近一次上传的时间';
comment on column SH_CHECK_LOG.UPLOADFILENAME is '最近一次上传的文件名';
comment on column SH_CHECK_LOG.CHECKTIME is '检测时间';
comment on column SH_CHECK_LOG.CHECKRESULT is '检查结果';
comment on column SH_CHECK_LOG.TERMLOGCHECKID is '序列号';

prompt Creating SH_CHOOSETEL_NUM...
create table SH_CHOOSETEL_NUM
(
  IDCARD        VARCHAR2(32) not null,
  CHOOSETELTIME NUMBER(2) not null,
  CHOOSETELDATE DATE not null
)
;
comment on table SH_CHOOSETEL_NUM is '  号码预约次数表';
comment on column SH_CHOOSETEL_NUM.IDCARD is '身份证';
comment on column SH_CHOOSETEL_NUM.CHOOSETELTIME is '选号次数';
comment on column SH_CHOOSETEL_NUM.CHOOSETELDATE is '选号时间';

prompt Creating SH_COMMONPAY_LOG...
create table SH_COMMONPAY_LOG
(
  COMMONPAYID VARCHAR2(16) not null,
  REGION      NUMBER(5) not null,
  MER_TYPE    VARCHAR2(2) not null,
  MER_CODE    VARCHAR2(7) not null,
  FEE_CODE    VARCHAR2(20) not null,
  PAY_MONEY   VARCHAR2(8) not null,
  CUSTOMER    VARCHAR2(16),
  STATUS      NUMBER(1) default 1,
  RECDATE     DATE,
  TERMID      VARCHAR2(32) not null,
  PAYTYPE     NUMBER(1),
  TRM_CODE    VARCHAR2(10),
  MPAY_FLOW   VARCHAR2(20),
  RES_DATE    DATE,
  RTN_CODE    VARCHAR2(4),
  RTN_MSG     VARCHAR2(200),
  ORGID       VARCHAR2(32)
)
;
comment on table SH_COMMONPAY_LOG is '	公共事业缴费日志表';
comment on column SH_COMMONPAY_LOG.COMMONPAYID is '缴费历史ID（序列:SEQ_SH_RECOID）缴费请求流水';
comment on column SH_COMMONPAY_LOG.REGION is '缴费地点';
comment on column SH_COMMONPAY_LOG.MER_TYPE is '缴费单位类型(08:电费 02:水费 03:煤气 04: 有线电视费)';
comment on column SH_COMMONPAY_LOG.MER_CODE is '缴费单位编码（从字典表中取得）';
comment on column SH_COMMONPAY_LOG.FEE_CODE is '公共事业缴费号码(如交电费的电卡卡号)';
comment on column SH_COMMONPAY_LOG.PAY_MONEY is '缴费金额(分)';
comment on column SH_COMMONPAY_LOG.CUSTOMER is '缴费人信息（如缴费方式为手机支付，此处为手机号码）';
comment on column SH_COMMONPAY_LOG.STATUS is '缴费状态   0：失败     1：成功     2：生成缴费流水号';
comment on column SH_COMMONPAY_LOG.RECDATE is '缴费时间';
comment on column SH_COMMONPAY_LOG.TERMID is '终端机编号';
comment on column SH_COMMONPAY_LOG.PAYTYPE is '缴费方式，（0:乐付话费;
1:CMPAY;
2:网银;
）0：手机钱包；1：银联卡；2：手机支付；3：积分兑换';
comment on column SH_COMMONPAY_LOG.TRM_CODE is '设备编码(如：A020000001、A020000002、A020000003)';
comment on column SH_COMMONPAY_LOG.MPAY_FLOW is '支付流水    乐付网侧流水';
comment on column SH_COMMONPAY_LOG.RES_DATE is '响应时间';
comment on column SH_COMMONPAY_LOG.RTN_CODE is '返回状态';
comment on column SH_COMMONPAY_LOG.RTN_MSG is '响应描述';
comment on column SH_COMMONPAY_LOG.ORGID is '组织结构ID';

prompt Creating SH_DICT_ITEM...
create table SH_DICT_ITEM
(
  DICTID      VARCHAR2(32) not null,
  DICTNAME    VARCHAR2(64),
  GROUPID     VARCHAR2(32) not null,
  SORTORDER   NUMBER(3),
  STATUS      NUMBER(1),
  STATUSDATE  DATE,
  DESCRIPTION VARCHAR2(128)
)
;
comment on table SH_DICT_ITEM is '	字典表';
comment on column SH_DICT_ITEM.DICTID is 'ID';
comment on column SH_DICT_ITEM.DICTNAME is '名称';
comment on column SH_DICT_ITEM.GROUPID is '组ID';
comment on column SH_DICT_ITEM.SORTORDER is '顺序';
comment on column SH_DICT_ITEM.STATUS is '状态';
comment on column SH_DICT_ITEM.STATUSDATE is '状态日期';
comment on column SH_DICT_ITEM.DESCRIPTION is '说明';
create unique index PK_SH_DICTITEM on SH_DICT_ITEM (DICTID, GROUPID);

prompt Creating SH_GROUP_ADV...
create table SH_GROUP_ADV
(
  TERMGRPID   VARCHAR2(32) not null,
  ADVLIST     VARCHAR2(4000),
  CREATEDATE  DATE not null,
  CREATEOPER  VARCHAR2(20) not null,
  STATUS      NUMBER not null,
  STATUSDATE  DATE not null,
  RELEASEOPER VARCHAR2(20)
)
;
comment on table SH_GROUP_ADV is '	终端组广告资源关系表';
comment on column SH_GROUP_ADV.TERMGRPID is '终端组ID';
comment on column SH_GROUP_ADV.ADVLIST is '广告列表（如：10000001,1000002）';
comment on column SH_GROUP_ADV.CREATEDATE is '创建时间';
comment on column SH_GROUP_ADV.CREATEOPER is '创建用户';
comment on column SH_GROUP_ADV.STATUS is '状态(1：有效 0：无效)';
comment on column SH_GROUP_ADV.STATUSDATE is '状态时间';
comment on column SH_GROUP_ADV.RELEASEOPER is '发布用户';

prompt Creating SH_GROUP_BUDDY...
create table SH_GROUP_BUDDY
(
  TERMGROUPID VARCHAR2(32) not null,
  BUDDYID     VARCHAR2(4000),
  CREATEDATE  DATE,
  STATUS      NUMBER(1),
  STATUSDATE  DATE
)
;
comment on table SH_GROUP_BUDDY is '	终端组优惠打折商家关系表';
comment on column SH_GROUP_BUDDY.TERMGROUPID is '终端组ID';
comment on column SH_GROUP_BUDDY.BUDDYID is '商家ID';
comment on column SH_GROUP_BUDDY.CREATEDATE is '创建时间';
comment on column SH_GROUP_BUDDY.STATUS is '状态（1：有效 0：无效）';
comment on column SH_GROUP_BUDDY.STATUSDATE is '状态时间';

prompt Creating SH_GROUP_INFO...
create table SH_GROUP_INFO
(
  REGION        NUMBER(5) not null,
  TERMGROUPID   VARCHAR2(32) not null,
  TERMGROUPNAME VARCHAR2(32) not null,
  STATUS        NUMBER(1) not null,
  STATUSDATE    DATE not null,
  ORGID         VARCHAR2(32) not null,
  NOTES         VARCHAR2(256)
)
;
comment on table SH_GROUP_INFO is '	终端组信息';
comment on column SH_GROUP_INFO.REGION is '区域';
comment on column SH_GROUP_INFO.TERMGROUPID is '终端组ID(序列：SEQ_SH_RECOID)';
comment on column SH_GROUP_INFO.TERMGROUPNAME is '终端组名称';
comment on column SH_GROUP_INFO.STATUS is '状态';
comment on column SH_GROUP_INFO.STATUSDATE is '状态时间';
comment on column SH_GROUP_INFO.ORGID is '所属机构';
comment on column SH_GROUP_INFO.NOTES is '备注';

prompt Creating SH_GROUP_MENU...
create table SH_GROUP_MENU
(
  TERMGRPID  VARCHAR2(32) not null,
  MENUID     VARCHAR2(32) not null,
  STATUS     NUMBER(1) not null,
  STATUSDATE DATE not null
)
;
comment on table SH_GROUP_MENU is '	菜单与终端组的关系表';
comment on column SH_GROUP_MENU.TERMGRPID is '终端组ID';
comment on column SH_GROUP_MENU.MENUID is '菜单ID';
comment on column SH_GROUP_MENU.STATUS is '状态，1在用，0禁用';
comment on column SH_GROUP_MENU.STATUSDATE is '状态时间';
create unique index PK_SH_GROUP_MENU on SH_GROUP_MENU (TERMGRPID, MENUID);

prompt Creating SH_GROUP_SCREEN...
create table SH_GROUP_SCREEN
(
  TERMGRPID   VARCHAR2(32) not null,
  SCLIST      VARCHAR2(4000),
  CREATEDATE  DATE not null,
  CREATEOPER  VARCHAR2(20) not null,
  STATUS      NUMBER not null,
  STATUSDATE  DATE not null,
  RELEASEOPER VARCHAR2(20)
)
;
comment on table SH_GROUP_SCREEN is '	终端组屏保资源关系表';
comment on column SH_GROUP_SCREEN.TERMGRPID is '终端组ID';
comment on column SH_GROUP_SCREEN.SCLIST is '广告列表(如1000001,100002)';
comment on column SH_GROUP_SCREEN.CREATEDATE is '创建时间';
comment on column SH_GROUP_SCREEN.CREATEOPER is '创建用户';
comment on column SH_GROUP_SCREEN.STATUS is '状态';
comment on column SH_GROUP_SCREEN.STATUSDATE is '状态时间';
comment on column SH_GROUP_SCREEN.RELEASEOPER is '发布用户';

prompt Creating SH_INFO_ABATE...
create table SH_INFO_ABATE
(
  ABATEID        VARCHAR2(16) not null,
  ABATENAME      VARCHAR2(64) not null,
  BUDDYID        VARCHAR2(16) not null,
  ABATEINFO      VARCHAR2(64) not null,
  ABATEPRICE     VARCHAR2(16),
  ABATESUM       NUMBER(6),
  ABATEUSENUM    NUMBER(6),
  STATUS         NUMBER(1),
  CREATEDATE     DATE,
  STATUSDATE     DATE,
  ENDDATE        DATE,
  ABATEIMAGEPATH VARCHAR2(64),
  ISFORFREE      VARCHAR2(1)
)
;
comment on table SH_INFO_ABATE is '	折扣产品表';
comment on column SH_INFO_ABATE.ABATEID is '优惠券ID(序列：SEQ_SH_RECOID)';
comment on column SH_INFO_ABATE.ABATENAME is '优惠券名称';
comment on column SH_INFO_ABATE.BUDDYID is '所属商家ID';
comment on column SH_INFO_ABATE.ABATEINFO is '优惠券信息';
comment on column SH_INFO_ABATE.ABATEPRICE is '优惠券单价';
comment on column SH_INFO_ABATE.ABATESUM is '优惠券数量';
comment on column SH_INFO_ABATE.ABATEUSENUM is '已使用数量';
comment on column SH_INFO_ABATE.STATUS is '状态';
comment on column SH_INFO_ABATE.CREATEDATE is '创建时间';
comment on column SH_INFO_ABATE.STATUSDATE is '状态时间';
comment on column SH_INFO_ABATE.ENDDATE is '结束时间';
comment on column SH_INFO_ABATE.ABATEIMAGEPATH is '图片路径';
comment on column SH_INFO_ABATE.ISFORFREE is '是否免费';

prompt Creating SH_INFO_BUDDY...
create table SH_INFO_BUDDY
(
  BUDDYID    VARCHAR2(16) not null,
  BUDDYNAME  VARCHAR2(64) not null,
  BUDDYTYPE  NUMBER(2),
  REGION     NUMBER(5),
  STATUS     NUMBER(1),
  CREATEDATE DATE,
  STATUSDATE DATE,
  ORGID      VARCHAR2(32)
)
;
comment on table SH_INFO_BUDDY is '	合作商家表';
comment on column SH_INFO_BUDDY.BUDDYID is '商家ID(序列：SEQ_SH_RECOID)';
comment on column SH_INFO_BUDDY.BUDDYNAME is '商家名称';
comment on column SH_INFO_BUDDY.BUDDYTYPE is '商家类型';
comment on column SH_INFO_BUDDY.REGION is '区域';
comment on column SH_INFO_BUDDY.STATUS is '状态';
comment on column SH_INFO_BUDDY.CREATEDATE is '创建时间';
comment on column SH_INFO_BUDDY.STATUSDATE is '状态时间';
comment on column SH_INFO_BUDDY.ORGID is '所属组织';

prompt Creating SH_INFO_MANUFACTURER...
create table SH_INFO_MANUFACTURER
(
  MANUFACTURERID      VARCHAR2(32) not null,
  MANUFACTURERNAME    VARCHAR2(256) not null,
  MANUFACTURERADDRESS VARCHAR2(1024) not null,
  PHONENUMBER         VARCHAR2(32) not null,
  OPERID              VARCHAR2(32) not null,
  CREATEDATE          DATE not null,
  RECDATE             DATE not null
)
;
comment on table SH_INFO_MANUFACTURER is '	终端厂商资料';
comment on column SH_INFO_MANUFACTURER.MANUFACTURERID is '厂商ID（序列：SEQ_SH_RECOID）';
comment on column SH_INFO_MANUFACTURER.MANUFACTURERNAME is '厂商名称';
comment on column SH_INFO_MANUFACTURER.MANUFACTURERADDRESS is '厂商地址';
comment on column SH_INFO_MANUFACTURER.PHONENUMBER is '电话号码';
comment on column SH_INFO_MANUFACTURER.OPERID is '创建人';
comment on column SH_INFO_MANUFACTURER.CREATEDATE is '创建时间';
comment on column SH_INFO_MANUFACTURER.RECDATE is '业务时间';

prompt Creating SH_INFO_MEDIARES...
create table SH_INFO_MEDIARES
(
  REGION           NUMBER(5) not null,
  RESID            VARCHAR2(32) not null,
  RESNAME          VARCHAR2(64) not null,
  RESLOCALFILENAME VARCHAR2(64) not null,
  RESFORMAT        VARCHAR2(20) not null,
  RESLINK          VARCHAR2(512) not null,
  RESTYPE          VARCHAR2(16) not null,
  STATUS           NUMBER(1) not null,
  STATUSDATE       DATE not null,
  CREATEDATE       DATE not null,
  CREATEOPER       VARCHAR2(20) not null,
  RESPLAYTIME      VARCHAR2(8),
  ORGID            VARCHAR2(32)
)
;
comment on table SH_INFO_MEDIARES is '	广告屏保信息表';
comment on column SH_INFO_MEDIARES.REGION is '区域';
comment on column SH_INFO_MEDIARES.RESID is '资源ID(序列:SEQ_SH_RECOID)';
comment on column SH_INFO_MEDIARES.RESNAME is '资源名称';
comment on column SH_INFO_MEDIARES.RESLOCALFILENAME is '本地文件名';
comment on column SH_INFO_MEDIARES.RESTYPE is '资源类型';
comment on column SH_INFO_MEDIARES.STATUS is '状态（1：有效 0：无效）';
comment on column SH_INFO_MEDIARES.STATUSDATE is '状态时间';
comment on column SH_INFO_MEDIARES.CREATEDATE is '创建时间';
comment on column SH_INFO_MEDIARES.CREATEOPER is '创建用户';
comment on column SH_INFO_MEDIARES.RESPLAYTIME is '播放时间（秒）';
comment on column SH_INFO_MEDIARES.ORGID is '所属区域';

prompt Creating SH_INFO_PLUGIN...
create table SH_INFO_PLUGIN
(
  PROVIDERID          VARCHAR2(32) not null,
  BROWSERTYPE         VARCHAR2(32) not null,
  SERVIPADDR          VARCHAR2(32),
  FTPUSERNAME         VARCHAR2(32),
  FTPPWD              VARCHAR2(32),
  PRINTERVER          VARCHAR2(32),
  PRTFTPADDR          VARCHAR2(256),
  PRTPLUGINFLAG       VARCHAR2(128),
  INVPRINTERVER       VARCHAR2(32),
  INVPRTFTPADDR       VARCHAR2(256),
  INVPRTPLUGINFLAG    VARCHAR2(128),
  KEYBOARDVER         VARCHAR2(32),
  KEYBRDFTPADDR       VARCHAR2(256),
  KEYBRDPLUGINFLAG    VARCHAR2(128),
  CASHVER             VARCHAR2(32),
  CASHFTPADDR         VARCHAR2(256),
  CASHPLUGINFLAG      VARCHAR2(128),
  CARDVER             VARCHAR2(32),
  CARDFTPADDR         VARCHAR2(256),
  CARDPLUGINFLAG      VARCHAR2(128),
  MANAGERVER          VARCHAR2(32),
  MGRFTPADDR          VARCHAR2(256),
  MGRPLUGINFLAG       VARCHAR2(128),
  SOCKETPORT          VARCHAR2(6) not null,
  MOVECARDVER         VARCHAR2(32),
  MOVECARDADDR        VARCHAR2(256),
  MOVECARDFLAG        VARCHAR2(128),
  WRITECARDVER        VARCHAR2(32),
  WRITECARDADDR       VARCHAR2(256),
  WRITECARDFLAG       VARCHAR2(128),
  IDCARDVER           VARCHAR2(32),
  IDCARDADDR          VARCHAR2(256),
  IDCARDFLAG          VARCHAR2(128),
  UNIONVER            VARCHAR2(32),
  UNIONFTPADDR        VARCHAR2(256),
  UNIONPLUGINFLAG     VARCHAR2(128),
  PURSEVER            VARCHAR2(32),
  PURSEFTPADDR        VARCHAR2(256),
  PURSEPLUGINFLAG     VARCHAR2(128),
  SELLGOODSVER        VARCHAR2(32),
  SELLGOODSFTPADDR    VARCHAR2(256),
  SELLGOODSPLUGINFLAG VARCHAR2(128)
)
;
comment on table SH_INFO_PLUGIN is '	终端插件信息表';
comment on column SH_INFO_PLUGIN.PROVIDERID is '厂商编号';
comment on column SH_INFO_PLUGIN.BROWSERTYPE is '浏览器类型ie,firefox';
comment on column SH_INFO_PLUGIN.SERVIPADDR is 'FTP服务器IP地址';
comment on column SH_INFO_PLUGIN.FTPUSERNAME is 'FTP服务器用户名';
comment on column SH_INFO_PLUGIN.FTPPWD is 'FTP服务器密码';
comment on column SH_INFO_PLUGIN.PRINTERVER is '打印机控件版本';
comment on column SH_INFO_PLUGIN.PRTFTPADDR is '打印机控件下载目录';
comment on column SH_INFO_PLUGIN.PRTPLUGINFLAG is '打印机控件标识';
comment on column SH_INFO_PLUGIN.INVPRINTERVER is '发票打印机控件版本';
comment on column SH_INFO_PLUGIN.INVPRTFTPADDR is '发票打印机控件下载目录';
comment on column SH_INFO_PLUGIN.INVPRTPLUGINFLAG is '发票打印机控件标识';
comment on column SH_INFO_PLUGIN.KEYBOARDVER is '密码键盘控件版本';
comment on column SH_INFO_PLUGIN.KEYBRDFTPADDR is '密码键盘控件下载目录';
comment on column SH_INFO_PLUGIN.KEYBRDPLUGINFLAG is '密码键盘控件标识';
comment on column SH_INFO_PLUGIN.CASHVER is '现金识币器控件版本';
comment on column SH_INFO_PLUGIN.CASHFTPADDR is '现金识币器控件下载目录';
comment on column SH_INFO_PLUGIN.CASHPLUGINFLAG is '现金识币器控件标识';
comment on column SH_INFO_PLUGIN.CARDVER is '读卡器控件版本';
comment on column SH_INFO_PLUGIN.CARDFTPADDR is '读卡器控件下载目录';
comment on column SH_INFO_PLUGIN.CARDPLUGINFLAG is '读卡器控件标识';
comment on column SH_INFO_PLUGIN.MANAGERVER is '终端管理控件版本';
comment on column SH_INFO_PLUGIN.MGRFTPADDR is '终端管理控件下载目录';
comment on column SH_INFO_PLUGIN.MGRPLUGINFLAG is '终端管理控件标识';
comment on column SH_INFO_PLUGIN.SOCKETPORT is '终端机开放SOCKET连接端口';
comment on column SH_INFO_PLUGIN.MOVECARDVER is '发卡器控件版本';
comment on column SH_INFO_PLUGIN.MOVECARDADDR is '发卡器控件下载目录';
comment on column SH_INFO_PLUGIN.MOVECARDFLAG is '发卡器控件标识';
comment on column SH_INFO_PLUGIN.WRITECARDVER is '写卡器控件版本';
comment on column SH_INFO_PLUGIN.WRITECARDADDR is '写卡器控件下载目录';
comment on column SH_INFO_PLUGIN.WRITECARDFLAG is '写卡器控件标识';
comment on column SH_INFO_PLUGIN.IDCARDVER is '身份证阅读器控件版本';
comment on column SH_INFO_PLUGIN.IDCARDADDR is '身份证阅读器控件下载目录';
comment on column SH_INFO_PLUGIN.IDCARDFLAG is '身份证阅读器控件标识';
comment on column SH_INFO_PLUGIN.UNIONVER is '银联扣费控件版本';
comment on column SH_INFO_PLUGIN.UNIONFTPADDR is '银联扣费控件下载目录';
comment on column SH_INFO_PLUGIN.UNIONPLUGINFLAG is '银联扣费控件标识';
comment on column SH_INFO_PLUGIN.PURSEVER is '手机钱包控件版本';
comment on column SH_INFO_PLUGIN.PURSEFTPADDR is '手机钱包控件下载目录';
comment on column SH_INFO_PLUGIN.PURSEPLUGINFLAG is '手机钱包控件标识';
comment on column SH_INFO_PLUGIN.SELLGOODSVER is '售货机控件版本';
comment on column SH_INFO_PLUGIN.SELLGOODSFTPADDR is '售货机控件下载目录';
comment on column SH_INFO_PLUGIN.SELLGOODSPLUGINFLAG is '售货机控件标识';

prompt Creating SH_LOG_ABATE...
create table SH_LOG_ABATE
(
  ABATELOGID  VARCHAR2(20) not null,
  REGION      NUMBER(5) not null,
  TERMID      VARCHAR2(32) not null,
  OPERATORID  VARCHAR2(20) not null,
  BUDDYID     VARCHAR2(16),
  ABATEID     VARCHAR2(16),
  PAYTYPE     NUMBER(1),
  ACCOUNTNUM  VARCHAR2(32),
  SELLNUM     NUMBER(4),
  PRICE       VARCHAR2(16),
  SELLFEE     VARCHAR2(8),
  RECDATE     DATE not null,
  STATUS      VARCHAR2(8) not null,
  DESCRIPTION VARCHAR2(1024),
  ORGID       VARCHAR2(32)
)
;
comment on table SH_LOG_ABATE is '优惠打折日志表';
comment on column SH_LOG_ABATE.ABATELOGID is '终端交易流水，如果获取方式是手机钱包，该字段与sh_purse_log的recoid字段关联';
comment on column SH_LOG_ABATE.REGION is '售出地点';
comment on column SH_LOG_ABATE.TERMID is '终端机编号';
comment on column SH_LOG_ABATE.OPERATORID is '操作员编号';
comment on column SH_LOG_ABATE.BUDDYID is '厂商ID';
comment on column SH_LOG_ABATE.ABATEID is '折扣产品ID';
comment on column SH_LOG_ABATE.PAYTYPE is '获取方式，0：手机钱包 1：银联卡 2：手机钱包主账户 3：积分兑换 4：现金 9：免费获取';
comment on column SH_LOG_ABATE.ACCOUNTNUM is '如果获取方式是手机钱包，为手机钱包卡号；如果获取方式是免费获取，为手机号码';
comment on column SH_LOG_ABATE.SELLNUM is '售出数量';
comment on column SH_LOG_ABATE.PRICE is '单价';
comment on column SH_LOG_ABATE.SELLFEE is '售出金额';
comment on column SH_LOG_ABATE.RECDATE is '受理时间';
comment on column SH_LOG_ABATE.STATUS is '状态，11：交易成功；10：手机钱包扣款成功或者用户身份验证成功，但是最终交易失败；01：手机钱包扣款成功或者用户身份验证成功的临时状态；00：除10外的其他交易失败情况';
comment on column SH_LOG_ABATE.DESCRIPTION is '失败时，必须记录出错信息';

prompt Creating SH_LOG_AUDIT...
create table SH_LOG_AUDIT
(
  ID        VARCHAR2(32) not null,
  TERMID    VARCHAR2(64) not null,
  STARTTIME DATE not null,
  ENDTIME   DATE not null,
  SYSMONEY  NUMBER not null,
  REALMONEY NUMBER not null
)
;
comment on table SH_LOG_AUDIT is '	现金稽核日志';
comment on column SH_LOG_AUDIT.ID is 'ID  使用序列SEQ_SH_RECOID';
comment on column SH_LOG_AUDIT.TERMID is '终端编号';
comment on column SH_LOG_AUDIT.STARTTIME is '开始时间';
comment on column SH_LOG_AUDIT.ENDTIME is '结束时间';
comment on column SH_LOG_AUDIT.SYSMONEY is '系统金额(单位：分)';
comment on column SH_LOG_AUDIT.REALMONEY is '实际金额(单位：分)';

prompt Creating SH_LOG_BUSINESS...
create table SH_LOG_BUSINESS
(
  ID        NUMBER,
  CHECKDATE DATE,
  RESULT    NUMBER,
  FILENAME  VARCHAR2(100)
)
;
comment on table SH_LOG_BUSINESS is '	记录向运营方提供交易对账文件日志';
comment on column SH_LOG_BUSINESS.ID is '主建(序列SEQ_SH_RECOID)';
comment on column SH_LOG_BUSINESS.CHECKDATE is '对帐日期';
comment on column SH_LOG_BUSINESS.RESULT is '0：初始状态 1：生成文件失败 2：生成文件成功 3：上传失败  4：上传成功';
comment on column SH_LOG_BUSINESS.FILENAME is '对帐文件名';

prompt Creating SH_LOG_CASHRETURN...
create table SH_LOG_CASHRETURN
(
  CASHRETURNID VARCHAR2(32),
  TERMID       VARCHAR2(32),
  SERVNUMBER   VARCHAR2(32),
  FEE          NUMBER(16),
  RECDATA      DATE,
  STATUS       NUMBER(1),
  TOUCHOID     VARCHAR2(32),
  TERMINALSEQ  VARCHAR2(32)
)
;
comment on table SH_LOG_CASHRETURN is '现金交易返回信息表';
comment on column SH_LOG_CASHRETURN.CASHRETURNID is '现金交易返回唯一标识(SEQ_SH_RECOID)';
comment on column SH_LOG_CASHRETURN.TERMID is '终端ID';
comment on column SH_LOG_CASHRETURN.SERVNUMBER is '手机号';
comment on column SH_LOG_CASHRETURN.FEE is '交易金额（分）';
comment on column SH_LOG_CASHRETURN.RECDATA is '交易时间';
comment on column SH_LOG_CASHRETURN.STATUS is '状态 0:成功 1:失败';
comment on column SH_LOG_CASHRETURN.TOUCHOID is '自动终端生成的接触流水';
comment on column SH_LOG_CASHRETURN.TERMINALSEQ is 'boss流水，对帐用';

prompt Creating SH_LOG_CHECKBILL...
create table SH_LOG_CHECKBILL
(
  ID         NUMBER,
  CHECKDATE  DATE,
  SERVREGION NUMBER,
  PAYTYPE    NUMBER,
  RESULT     NUMBER,
  FILENAME   VARCHAR2(100)
)
;
comment on table SH_LOG_CHECKBILL is '	记录BOSS话费缴费对账文件日志';
comment on column SH_LOG_CHECKBILL.ID is '主建(SEQ_SH_RECOID)';
comment on column SH_LOG_CHECKBILL.CHECKDATE is '对帐日期';
comment on column SH_LOG_CHECKBILL.SERVREGION is '手机号码所属区域';
comment on column SH_LOG_CHECKBILL.PAYTYPE is '4：现金支付  1：银联卡支付';
comment on column SH_LOG_CHECKBILL.RESULT is '0：初始状态 1：生成文件失败 2：生成文件成功 3：上传失败  4：上传成功';
comment on column SH_LOG_CHECKBILL.FILENAME is '对帐文件名';

prompt Creating SH_LOG_LOGINERRNUM...
create table SH_LOG_LOGINERRNUM
(
  SERVNUMBER  VARCHAR2(11) not null,
  AUTHTYPE    VARCHAR2(32) not null,
  ERROR_TIMES NUMBER(1) not null,
  FIRST_TIME  DATE not null,
  LAST_TIME   DATE not null
)
;
comment on table SH_LOG_LOGINERRNUM is '用户随机验证密码表';
comment on column SH_LOG_LOGINERRNUM.SERVNUMBER is '手机号码';
comment on column SH_LOG_LOGINERRNUM.AUTHTYPE is '认证方式，SERVICEPWD：服务密码；RANDOMPWD：随机密码';
comment on column SH_LOG_LOGINERRNUM.ERROR_TIMES is '错误次数';
comment on column SH_LOG_LOGINERRNUM.FIRST_TIME is '首次认证失败时间';
comment on column SH_LOG_LOGINERRNUM.LAST_TIME is '最后一次认证失败时间';

prompt Creating SH_LOG_MOBILEPAY...
create table SH_LOG_MOBILEPAY
(
  MPAYLOGID   NUMBER(14) not null,
  REGION      NUMBER(5) not null,
  TERMID      VARCHAR2(32) not null,
  OPERID      VARCHAR2(20),
  PAYTYPE     NUMBER(1),
  SERVNUMBER  VARCHAR2(20),
  FEE         VARCHAR2(10),
  RECDATE     DATE,
  STATUS      VARCHAR2(3),
  DESCRIPTION VARCHAR2(512),
  BOSSSEQ     VARCHAR2(32),
  MPAYSEQ     VARCHAR2(32),
  ORGID       VARCHAR2(32)
)
;
comment on table SH_LOG_MOBILEPAY is '手机支付主账户充值日志记录';
comment on column SH_LOG_MOBILEPAY.MPAYLOGID is '终端流水号(SEQ_SH_RECOID)';
comment on column SH_LOG_MOBILEPAY.REGION is '营业员地区编号';
comment on column SH_LOG_MOBILEPAY.TERMID is '终端编号';
comment on column SH_LOG_MOBILEPAY.OPERID is '操作员编号';
comment on column SH_LOG_MOBILEPAY.PAYTYPE is '支付方式。0：手机钱包 1：银联卡 2：手机支付 3：积分兑换 4：现金支付9：免费获取';
comment on column SH_LOG_MOBILEPAY.SERVNUMBER is '充值号码';
comment on column SH_LOG_MOBILEPAY.FEE is '充值金额，单位分';
comment on column SH_LOG_MOBILEPAY.RECDATE is '交易时间，精确到秒';
comment on column SH_LOG_MOBILEPAY.STATUS is '交易结果，11：投币成功，缴费成功；10：投币成功，但是缴费失败；01：投币成功的临时状态 ；00：除10之外的缴费失败情况';
comment on column SH_LOG_MOBILEPAY.DESCRIPTION is '交易结果描述，主要是系统出现异常或者交易失败时用，比如投币失败的错误信息、缴费失败的错误信息';
comment on column SH_LOG_MOBILEPAY.BOSSSEQ is 'BOSS流水号';
comment on column SH_LOG_MOBILEPAY.MPAYSEQ is '手机支付系统流水号';

prompt Creating SH_LOG_MOBILEWALLET...
create table SH_LOG_MOBILEWALLET
(
  RECOID       VARCHAR2(16) not null,
  MONEY        VARCHAR2(10) not null,
  POSSEQID     VARCHAR2(16),
  POSTERMID    VARCHAR2(8),
  BUSINESSID   VARCHAR2(15),
  BUSINESSNAME VARCHAR2(20),
  OPERID       VARCHAR2(2),
  RECCARDNO    VARCHAR2(20),
  TERMID       VARCHAR2(32),
  BATCHNUM     VARCHAR2(6),
  POSOID       VARCHAR2(6),
  RECDATE      DATE,
  APPAID       VARCHAR2(32),
  SENDCARDID   VARCHAR2(16),
  OFFRECNUM    VARCHAR2(4),
  KEYEDITION   VARCHAR2(2),
  KEYSEQNUM    VARCHAR2(2),
  TERMCODE     VARCHAR2(12),
  TERMRECSEQ   VARCHAR2(8),
  TACNUM       VARCHAR2(8),
  CREATEDATE   DATE not null,
  RECTYPE      VARCHAR2(1),
  REGION       VARCHAR2(6),
  ORGID        VARCHAR2(32)
)
;
comment on table SH_LOG_MOBILEWALLET is '手机钱包交易日志表';
comment on column SH_LOG_MOBILEWALLET.RECOID is '自助终端交易流水(SEQ_SH_RECOID)';
comment on column SH_LOG_MOBILEWALLET.MONEY is '交易金额(单位分)';
comment on column SH_LOG_MOBILEWALLET.POSSEQID is 'POS终端序列号';
comment on column SH_LOG_MOBILEWALLET.POSTERMID is 'POS终端号';
comment on column SH_LOG_MOBILEWALLET.BUSINESSID is '商户编号';
comment on column SH_LOG_MOBILEWALLET.BUSINESSNAME is '商户名称简写';
comment on column SH_LOG_MOBILEWALLET.OPERID is '操作员号';
comment on column SH_LOG_MOBILEWALLET.RECCARDNO is '交易卡号';
comment on column SH_LOG_MOBILEWALLET.TERMID is '终端号';
comment on column SH_LOG_MOBILEWALLET.BATCHNUM is 'POS批次号';
comment on column SH_LOG_MOBILEWALLET.POSOID is 'POS流水号';
comment on column SH_LOG_MOBILEWALLET.RECDATE is '终端交易日期';
comment on column SH_LOG_MOBILEWALLET.APPAID is '应用标识符AID';
comment on column SH_LOG_MOBILEWALLET.SENDCARDID is '发卡方标识';
comment on column SH_LOG_MOBILEWALLET.OFFRECNUM is '脱机交易序号';
comment on column SH_LOG_MOBILEWALLET.KEYEDITION is '密钥版本号';
comment on column SH_LOG_MOBILEWALLET.KEYSEQNUM is '密钥索引号';
comment on column SH_LOG_MOBILEWALLET.TERMCODE is '终端机编号';
comment on column SH_LOG_MOBILEWALLET.TERMRECSEQ is '终端交易序号';
comment on column SH_LOG_MOBILEWALLET.TACNUM is 'TAC';
comment on column SH_LOG_MOBILEWALLET.CREATEDATE is '记录时间';
comment on column SH_LOG_MOBILEWALLET.RECTYPE is '0，自助售货，1，优惠打折';

prompt Creating SH_LOG_PRINTBILLNUM...
create table SH_LOG_PRINTBILLNUM
(
  TELNUM        VARCHAR2(32) not null,
  CYCLE         VARCHAR2(32) not null,
  PRINTNUM      NUMBER(6) not null,
  LASTPRINTDATE DATE
)
;
comment on table SH_LOG_PRINTBILLNUM is '	详单打印日志表';
comment on column SH_LOG_PRINTBILLNUM.TELNUM is '用户号码';
comment on column SH_LOG_PRINTBILLNUM.CYCLE is '帐期';
comment on column SH_LOG_PRINTBILLNUM.PRINTNUM is '打印次数';
comment on column SH_LOG_PRINTBILLNUM.LASTPRINTDATE is '最近打印时间';

prompt Creating SH_LOG_PRINTINVOICE...
create table SH_LOG_PRINTINVOICE
(
  SERVNUMBER VARCHAR2(11) not null,
  CYCLE      VARCHAR2(32),
  RECDATE    DATE not null,
  TERMID     VARCHAR2(32) not null
)
;
comment on table SH_LOG_PRINTINVOICE is '	发票打印日志表';
comment on column SH_LOG_PRINTINVOICE.SERVNUMBER is '手机号码';
comment on column SH_LOG_PRINTINVOICE.CYCLE is '发票对应的账期';
comment on column SH_LOG_PRINTINVOICE.RECDATE is '打印时间';
comment on column SH_LOG_PRINTINVOICE.TERMID is '终端机ID';

prompt Creating SH_LOG_PRIV...
create table SH_LOG_PRIV
(
  PRIVLOGOID    NUMBER(14) not null,
  CHARGEID      NUMBER(14),
  REGION        NUMBER(5) not null,
  SERVNUMBER    VARCHAR2(20),
  PRIVID        VARCHAR2(20),
  PRIVNCODE     VARCHAR2(32),
  RECDATE       DATE,
  PRIVFEE       NUMBER(16),
  TOFEE         NUMBER(16),
  CHARGEFEE     NUMBER(16) default 0,
  CHARGETYPE    VARCHAR2(1),
  RECSTAUTS     VARCHAR2(2),
  RECSTAUTSDESC VARCHAR2(512)
)
;
comment on column SH_LOG_PRIV.PRIVLOGOID is 'SH_PRIV_LOG唯一流水号';
comment on column SH_LOG_PRIV.CHARGEID is '与SH_CHARGE_lOG关联';
comment on column SH_LOG_PRIV.REGION is '地区';
comment on column SH_LOG_PRIV.SERVNUMBER is '用户服务号码';
comment on column SH_LOG_PRIV.PRIVID is '优惠受理的ID';
comment on column SH_LOG_PRIV.PRIVNCODE is '优惠受理的NCODE';
comment on column SH_LOG_PRIV.RECDATE is '优惠受理时间.YYYY-MM-DD HH24:MI:SS';
comment on column SH_LOG_PRIV.PRIVFEE is '优惠受理费用';
comment on column SH_LOG_PRIV.TOFEE is '用户实缴金额';
comment on column SH_LOG_PRIV.CHARGEFEE is '多余缴费金额';
comment on column SH_LOG_PRIV.CHARGETYPE is '缴费方式，1：银联卡；4：现金';
comment on column SH_LOG_PRIV.RECSTAUTS is '优惠受理的状态;01:优惠受理失败;02:金额不足,缴费失败;
03:优惠受理成功,缴费失败;04:优惠受理成功,缴费成功;
05:优惠受理成功;06:金额不足,缴费成功;';
comment on column SH_LOG_PRIV.RECSTAUTSDESC is '优惠受理状态描述';
create index IDX_SH_PRIVLOG_SERVNUM on SH_LOG_PRIV (SERVNUMBER);
create unique index PK_SH_PRIVLOG_OID on SH_LOG_PRIV (PRIVLOGOID, REGION);

prompt Creating SH_LOG_PRODCHANGE...
create table SH_LOG_PRODCHANGE
(
  PRODLOGOID NUMBER(14) not null,
  PRODSN     VARCHAR2(20),
  REGION     NUMBER(5) not null,
  SERVNUMBER VARCHAR2(20),
  FROMPRODID VARCHAR2(20),
  TOPRODID   VARCHAR2(32),
  OPTYPE     VARCHAR2(1),
  PRODTYPE   VARCHAR2(20),
  CHANGEID   VARCHAR2(20),
  RECDATE    DATE,
  RECSTAUTS  VARCHAR2(2)
)
;
comment on column SH_LOG_PRODCHANGE.PRODLOGOID is 'SH_PRODCHANGE_LOG流水号';
comment on column SH_LOG_PRODCHANGE.PRODSN is '产品受理SN';
comment on column SH_LOG_PRODCHANGE.REGION is '地区';
comment on column SH_LOG_PRODCHANGE.SERVNUMBER is '用户服务号码';
comment on column SH_LOG_PRODCHANGE.FROMPRODID is '当前用户产品ID';
comment on column SH_LOG_PRODCHANGE.TOPRODID is '转换的产品ID  ';
comment on column SH_LOG_PRODCHANGE.OPTYPE is '操作类型';
comment on column SH_LOG_PRODCHANGE.PRODTYPE is '受理类型';
comment on column SH_LOG_PRODCHANGE.CHANGEID is '所需转换的产品ID';
comment on column SH_LOG_PRODCHANGE.RECDATE is '优惠受理时间.YYYY-MM-DD HH24:MI:SS';
comment on column SH_LOG_PRODCHANGE.RECSTAUTS is '优惠受理的状态0:失败,1:成功
);';
create index IDX_SH_PRODLOG_PRODSN on SH_LOG_PRODCHANGE (PRODSN, REGION);
create index IDX_SH_PRODLOG_SERVNUM on SH_LOG_PRODCHANGE (SERVNUMBER);

prompt Creating SH_LOG_TERMSTATUS...
create table SH_LOG_TERMSTATUS
(
  REGION     NUMBER(6) not null,
  IPADDR     VARCHAR2(32) not null,
  STATUS     VARCHAR2(32) not null,
  DETAIL     VARCHAR2(512),
  STATUSDATE DATE not null
)
;
comment on table SH_LOG_TERMSTATUS is '	终端状态表';
comment on column SH_LOG_TERMSTATUS.REGION is '地区编号';
comment on column SH_LOG_TERMSTATUS.IPADDR is 'IP地址';
comment on column SH_LOG_TERMSTATUS.STATUS is '状态 状态取值组号TERMSTATUS';
comment on column SH_LOG_TERMSTATUS.DETAIL is '详细状态描述信息';
comment on column SH_LOG_TERMSTATUS.STATUSDATE is '状态时间';

prompt Creating SH_LOG_TERMUP...
create table SH_LOG_TERMUP
(
  FILENAME    VARCHAR2(64) not null,
  REGION      NUMBER(5) not null,
  IPADDRESS   VARCHAR2(32) not null,
  CREATEDATE  DATE,
  LOGFILEPATH VARCHAR2(128),
  STATUS      NUMBER(1),
  STATUSDATE  DATE,
  REMARK      VARCHAR2(256),
  ORGID       VARCHAR2(32)
)
;
comment on table SH_LOG_TERMUP is '	终端硬件日志文件传记录';
comment on column SH_LOG_TERMUP.FILENAME is '文件名';
comment on column SH_LOG_TERMUP.REGION is '终端机所属区域';
comment on column SH_LOG_TERMUP.IPADDRESS is '终端IP地址';
comment on column SH_LOG_TERMUP.CREATEDATE is '日志产生的时间';
comment on column SH_LOG_TERMUP.LOGFILEPATH is '日志文件存放的路径';
comment on column SH_LOG_TERMUP.STATUS is '0初始状态，1成功归类，2异常';
comment on column SH_LOG_TERMUP.STATUSDATE is '状态时间';
comment on column SH_LOG_TERMUP.REMARK is '备注，发生异常时记录异常信息';
comment on column SH_LOG_TERMUP.ORGID is '所属机构';

prompt Creating SH_MENU_ITEM...
create table SH_MENU_ITEM
(
  MENUID     VARCHAR2(32) not null,
  MENUNAME   VARCHAR2(64) not null,
  PARENTID   VARCHAR2(32),
  GUIOBJ     VARCHAR2(128),
  TIPTEXT    VARCHAR2(128),
  SORTORDER  NUMBER(2) not null,
  CREATEDATE DATE,
  STATUS     NUMBER(1) not null,
  STATUSDATE DATE,
  IMGPATH    VARCHAR2(32),
  REGION     NUMBER(5) not null,
  BRANDID    VARCHAR2(20) not null,
  AUTHCODE   VARCHAR2(10) not null,
  MENULEVEL  NUMBER(2) not null,
  BUSIDETAIL VARCHAR2(2000),
  POSITION   VARCHAR2(6),
  IMGPATH2   VARCHAR2(64)
)
;
comment on table SH_MENU_ITEM is '	终端前台菜单表';
comment on column SH_MENU_ITEM.MENUID is '菜单ID';
comment on column SH_MENU_ITEM.MENUNAME is '菜单名称';
comment on column SH_MENU_ITEM.PARENTID is '父菜单ID';
comment on column SH_MENU_ITEM.GUIOBJ is 'URL';
comment on column SH_MENU_ITEM.TIPTEXT is '提示信息';
comment on column SH_MENU_ITEM.SORTORDER is '序号';
comment on column SH_MENU_ITEM.CREATEDATE is '创建时间';
comment on column SH_MENU_ITEM.STATUS is '状态';
comment on column SH_MENU_ITEM.STATUSDATE is '状态时间';
comment on column SH_MENU_ITEM.IMGPATH is '根据移动发布的自助终端客户界面模板，部分菜单会在两个不同的页面显示，对应的图片不管是大小，还是样式，都不一样。所以，使用两个字段维护菜单对应的图片。如果菜单需要在主页显示，则需要维护此信息；如果不需要在主页显示，则不需要维护此信息。';
comment on column SH_MENU_ITEM.REGION is '区域';
comment on column SH_MENU_ITEM.BRANDID is '菜单适用品牌。ALL：适用所有品牌；BrandMzone：仅动感地带用户可用；BrandSzx：仅神州行用户可用；BrandGotone：仅全球通用户可用';
comment on column SH_MENU_ITEM.AUTHCODE is '认证方式。目前支持的认证方式有服务密码、随机密码、身份证、手机钱包，0000代表办理业务前不需要进行身份认证；1000代表办理业务前需进行服务密码认证；1100代表办理业务前需进行服务密码和随机码两种认证';
comment on column SH_MENU_ITEM.MENULEVEL is '菜单级别';
comment on column SH_MENU_ITEM.BUSIDETAIL is '业务介绍';
comment on column SH_MENU_ITEM.POSITION is '菜单显示位置。C：center，中间位置；BR：bottom-right，右下位置；TR：top-right，右上位置。';
comment on column SH_MENU_ITEM.IMGPATH2 is '说明参考IMGPATH字段。';

prompt Creating SH_MENU_ITEM_MGR...
create table SH_MENU_ITEM_MGR
(
  MODULEID        VARCHAR2(20) not null,
  MENUID          VARCHAR2(60) not null,
  PARENTID        VARCHAR2(60) not null,
  IMAGEURL        VARCHAR2(100),
  MENUNAME        VARCHAR2(50) not null,
  MENUDESCRIPTION VARCHAR2(500),
  MENUURL         VARCHAR2(500),
  DISPLAYNO       NUMBER(5) not null,
  OPENMODULE      VARCHAR2(2) not null,
  AUTHID          VARCHAR2(80) not null,
  FASTKEY         VARCHAR2(100),
  ISHIDDEN        VARCHAR2(2) not null,
  SERVICEID       VARCHAR2(20),
  ISDISPLAY       VARCHAR2(2) not null,
  CALLBACK        VARCHAR2(20),
  ISCALLBARITEM   VARCHAR2(2),
  DATASOURCEID    VARCHAR2(32),
  DISPLAYTYPE     VARCHAR2(2),
  DYFIELD1        VARCHAR2(100),
  DYFIELD2        VARCHAR2(100),
  DYFIELD3        VARCHAR2(100),
  DYFIELD4        VARCHAR2(100),
  DYFIELD5        VARCHAR2(100),
  CLICKTOTAL      NUMBER,
  APPENDINFO      VARCHAR2(32),
  TABTYPE         VARCHAR2(2),
  LOGLEVEL        NUMBER(10),
  STATUS          NUMBER(1),
  ENABLEDATE      DATE,
  DISABLEDATE     DATE,
  REGION          NUMBER(5),
  STATUSDATE      DATE,
  CREATEDATE      DATE,
  MENUTYPE        VARCHAR2(20),
  EMERGENCYID     VARCHAR2(20),
  MUTEXFLAG       VARCHAR2(32),
  HELPLINK        VARCHAR2(32),
  INTERFACEOBJECT VARCHAR2(32)
)
;
comment on table SH_MENU_ITEM_MGR is '	终端管理平台菜单表';
comment on column SH_MENU_ITEM_MGR.MODULEID is '模块ID';
comment on column SH_MENU_ITEM_MGR.MENUID is '菜单ID';
comment on column SH_MENU_ITEM_MGR.PARENTID is '父菜单ID';
comment on column SH_MENU_ITEM_MGR.IMAGEURL is '图片地址';
comment on column SH_MENU_ITEM_MGR.MENUNAME is '菜单名称';
comment on column SH_MENU_ITEM_MGR.MENUDESCRIPTION is '菜单描述';
comment on column SH_MENU_ITEM_MGR.MENUURL is '菜单URL';
comment on column SH_MENU_ITEM_MGR.DISPLAYNO is '是否显示';
comment on column SH_MENU_ITEM_MGR.LOGLEVEL is 'LOG级别';
comment on column SH_MENU_ITEM_MGR.STATUS is '状态';
comment on column SH_MENU_ITEM_MGR.ENABLEDATE is '可用时间';
comment on column SH_MENU_ITEM_MGR.DISABLEDATE is '不可用时间';
comment on column SH_MENU_ITEM_MGR.REGION is '区域';
comment on column SH_MENU_ITEM_MGR.STATUSDATE is '状态时间';
comment on column SH_MENU_ITEM_MGR.CREATEDATE is '创建时间';
comment on column SH_MENU_ITEM_MGR.MENUTYPE is '菜单类型';

prompt Creating SH_OPERATOR_ROLE...
create table SH_OPERATOR_ROLE
(
  OPERID     VARCHAR2(32) not null,
  ROLEID     VARCHAR2(32) not null,
  STATUS     NUMBER(1) not null,
  STATUSDATE DATE not null
)
;
comment on table SH_OPERATOR_ROLE is '操作员角色关系表';
comment on column SH_OPERATOR_ROLE.OPERID is '操作员ID';
comment on column SH_OPERATOR_ROLE.ROLEID is '角色ID';
comment on column SH_OPERATOR_ROLE.STATUS is '状态，1在用，0禁用';
comment on column SH_OPERATOR_ROLE.STATUSDATE is '状态时间';

prompt Creating SH_PARAM...
create table SH_PARAM
(
  PARAMID     VARCHAR2(32) not null,
  PARAMNAME   VARCHAR2(64),
  DATATYPE    VARCHAR2(16),
  MANAGEABLE  NUMBER(1),
  CREATEDATE  DATE,
  STATUS      NUMBER(1),
  STATUSDATE  DATE,
  DESCRIPTION VARCHAR2(128)
)
;
comment on table SH_PARAM is '	系统参数定义表';
comment on column SH_PARAM.PARAMID is '参数ID';
comment on column SH_PARAM.PARAMNAME is '参数名称';
comment on column SH_PARAM.DATATYPE is '数据类型';
comment on column SH_PARAM.CREATEDATE is '创建日期';
comment on column SH_PARAM.STATUS is '状态';
comment on column SH_PARAM.STATUSDATE is '状态日期';
comment on column SH_PARAM.DESCRIPTION is '说明';

prompt Creating SH_PARAM_VALUE...
create table SH_PARAM_VALUE
(
  PARAMID       VARCHAR2(32) not null,
  PARAMVALUE    VARCHAR2(256),
  STATUS        NUMBER(1) not null,
  STATUSDATE    DATE,
  ADDDATA       VARCHAR2(64),
  LOG_OPER_ID   VARCHAR2(16) not null,
  LOG_OPER_DATE DATE not null
)
;
comment on table SH_PARAM_VALUE is '参数值';
comment on column SH_PARAM_VALUE.PARAMID is '参数编码';
comment on column SH_PARAM_VALUE.PARAMVALUE is '参数值';
comment on column SH_PARAM_VALUE.STATUS is '状态';
comment on column SH_PARAM_VALUE.STATUSDATE is '状态时间';
comment on column SH_PARAM_VALUE.ADDDATA is '附加数据';

prompt Creating SH_PC_DSMP_SPBIZINFO...
create table SH_PC_DSMP_SPBIZINFO
(
  SERVTYPE        VARCHAR2(6) not null,
  SPCODE          VARCHAR2(6) not null,
  OPERATORCODE    VARCHAR2(32) not null,
  OPERATORNAME    VARCHAR2(64),
  OTHERBALOBJ1    VARCHAR2(32),
  OTHERBALOBJ2    VARCHAR2(32),
  BILLFLAG        VARCHAR2(2) not null,
  FEE             VARCHAR2(32),
  VALIDDATE       VARCHAR2(8) default to_char(sysdate,'yyyymmdd'),
  EXPIREDATE      VARCHAR2(8),
  BALPROP         VARCHAR2(32),
  COUNT           VARCHAR2(16) not null,
  SERVATTR        VARCHAR2(2),
  ISTHIRDVALIDATE VARCHAR2(2) not null,
  RECONFIRM       VARCHAR2(2) not null,
  IDORDER         VARCHAR2(12),
  EXPAND          VARCHAR2(64),
  CHECKFLAG       VARCHAR2(2),
  CHECKRSLT       VARCHAR2(64),
  STATUS          NUMBER(1),
  CREATEDATE      DATE,
  STATUSDATE      DATE,
  HAVEAPD         NUMBER(1) default 0,
  OPFILENAME      VARCHAR2(128) not null,
  DOMAIN          VARCHAR2(16) not null,
  SERVICEID       VARCHAR2(16),
  PRODUCTID       VARCHAR2(64),
  SERVPROPERTY    VARCHAR2(2),
  NOTE            VARCHAR2(1024),
  AUDITSTATUS     VARCHAR2(16) default 0,
  AUDITDATE       DATE,
  AUDITOPER       VARCHAR2(16),
  AUDITOPINION    VARCHAR2(255),
  SPBIZUSAGE      VARCHAR2(2) default 'A',
  ISCHKSP         NUMBER(1) default 0 not null,
  AFFECTTYPE      VARCHAR2(16) default 'EffectImmed' not null,
  CHGAFFECTTYPE   NUMBER(1) default 0,
  DELENDTYPE      VARCHAR2(16) default 'DelEndImmed' not null,
  CHGDELENDTYPE   NUMBER(1) default 0,
  ISDEPDOMAIN     NUMBER(1) default 0,
  DEDUCT_CLUE     VARCHAR2(2),
  QUERY_TD        VARCHAR2(2),
  SIMS_TIME       DATE
)
;
comment on column SH_PC_DSMP_SPBIZINFO.SERVTYPE is '业务类型';
comment on column SH_PC_DSMP_SPBIZINFO.SPCODE is '企业代码';
comment on column SH_PC_DSMP_SPBIZINFO.OPERATORCODE is '业务代码';
comment on column SH_PC_DSMP_SPBIZINFO.OPERATORNAME is '业务产品名称';
comment on column SH_PC_DSMP_SPBIZINFO.OTHERBALOBJ1 is '其他结算方1';
comment on column SH_PC_DSMP_SPBIZINFO.OTHERBALOBJ2 is '其他结算方2';
comment on column SH_PC_DSMP_SPBIZINFO.BILLFLAG is '计费类型，0免费，1按条 2包月';
comment on column SH_PC_DSMP_SPBIZINFO.FEE is '信息费';
comment on column SH_PC_DSMP_SPBIZINFO.VALIDDATE is '生效日期';
comment on column SH_PC_DSMP_SPBIZINFO.EXPIREDATE is '失效日期';
comment on column SH_PC_DSMP_SPBIZINFO.BALPROP is '结算比例';
comment on column SH_PC_DSMP_SPBIZINFO.COUNT is '包次次数/包天天数';
comment on column SH_PC_DSMP_SPBIZINFO.SERVATTR is '业务属性';
comment on column SH_PC_DSMP_SPBIZINFO.ISTHIRDVALIDATE is '是否向业务平台做第三方确认';
comment on column SH_PC_DSMP_SPBIZINFO.RECONFIRM is '是否需要二次确认';
comment on column SH_PC_DSMP_SPBIZINFO.IDORDER is '记录序号';
comment on column SH_PC_DSMP_SPBIZINFO.EXPAND is '扩展字段';
comment on column SH_PC_DSMP_SPBIZINFO.CHECKFLAG is '校验标记0未校验1已校验';
comment on column SH_PC_DSMP_SPBIZINFO.CHECKRSLT is '校验结果';
comment on column SH_PC_DSMP_SPBIZINFO.STATUS is '状态';
comment on column SH_PC_DSMP_SPBIZINFO.CREATEDATE is '创建时间';
comment on column SH_PC_DSMP_SPBIZINFO.STATUSDATE is '状态更新时间';
comment on column SH_PC_DSMP_SPBIZINFO.HAVEAPD is '是否有附加属性0没有,1有（）';
comment on column SH_PC_DSMP_SPBIZINFO.OPFILENAME is '文件名';
comment on column SH_PC_DSMP_SPBIZINFO.DOMAIN is '业务平台';
comment on column SH_PC_DSMP_SPBIZINFO.SERVPROPERTY is '服务特性';
comment on column SH_PC_DSMP_SPBIZINFO.SPBIZUSAGE is 'sp业务用途,对应dict_group字典组SpUsageType';
comment on column SH_PC_DSMP_SPBIZINFO.ISCHKSP is '是否需要交叉检查,1检查;0不检查';
comment on column SH_PC_DSMP_SPBIZINFO.AFFECTTYPE is '生效类型';
comment on column SH_PC_DSMP_SPBIZINFO.CHGAFFECTTYPE is '是否可修改生效类型';
comment on column SH_PC_DSMP_SPBIZINFO.DELENDTYPE is '结束类型';
comment on column SH_PC_DSMP_SPBIZINFO.CHGDELENDTYPE is '是否可修改结束类型';
comment on column SH_PC_DSMP_SPBIZINFO.ISDEPDOMAIN is '是否依赖平台生效';
comment on column SH_PC_DSMP_SPBIZINFO.DEDUCT_CLUE is '扣费提醒标识 0：需要扣费提醒 1：不需要扣费提醒';
comment on column SH_PC_DSMP_SPBIZINFO.QUERY_TD is '统一查询退订标识 0: 统一查询退订标识 1：不统一查询退订标识';
comment on column SH_PC_DSMP_SPBIZINFO.SIMS_TIME is 'SIMS操作时间';

prompt Creating SH_RANDOM_PASSWORD...
create table SH_RANDOM_PASSWORD
(
  SERVNUMBER     VARCHAR2(20) not null,
  RANDOMPASSWORD VARCHAR2(20),
  CREATETIME     DATE,
  VALIDATETIME   DATE,
  STATUS         NUMBER(1)
)
;
comment on table SH_RANDOM_PASSWORD is '	随即密码表';
comment on column SH_RANDOM_PASSWORD.SERVNUMBER is '服务号码';
comment on column SH_RANDOM_PASSWORD.RANDOMPASSWORD is '随机密码';
comment on column SH_RANDOM_PASSWORD.CREATETIME is '生成时间';
comment on column SH_RANDOM_PASSWORD.VALIDATETIME is '验证码到期时间';
comment on column SH_RANDOM_PASSWORD.STATUS is '状态(1有效，2已成功处理-移历史表，3超时-移历史表)';
create index IDX_SH_RAND_PASS_SERV on SH_RANDOM_PASSWORD (SERVNUMBER);
create index IDX_SH_RAND_PASS_SRS on SH_RANDOM_PASSWORD (SERVNUMBER, RANDOMPASSWORD);

prompt Creating SH_REC_LOG...
create table SH_REC_LOG
(
  OID           NUMBER(14) not null,
  REGION        NUMBER(5) not null,
  TERMID        VARCHAR2(32) not null,
  RECFORMNUM    VARCHAR2(32) default 0,
  TOURCHOID     VARCHAR2(32) default 0,
  SERVNUMBER    VARCHAR2(20) default 0,
  BUSITYPE      VARCHAR2(32),
  OPERID        VARCHAR2(20),
  ORGID         VARCHAR2(50),
  RECDATE       DATE,
  RECSTATUS     VARCHAR2(16),
  RECSTATUSDESC VARCHAR2(512),
  RECFEE        NUMBER(16) default 0,
  DISCOUNT      NUMBER(16) default 0,
  BRANDID       VARCHAR2(32)
)
;
comment on table SH_REC_LOG is '	业务日志表';
comment on column SH_REC_LOG.OID is '日志流水号(SEQ_SH_RECOID)';
comment on column SH_REC_LOG.REGION is '营业员地区编号';
comment on column SH_REC_LOG.TERMID is '终端编号';
comment on column SH_REC_LOG.RECFORMNUM is '业务流水号。和reception表关联，非业务受理、缴费类业务则为”0”。';
comment on column SH_REC_LOG.TOURCHOID is '客户接触流水号，用户登陆后返回，默认为0';
comment on column SH_REC_LOG.SERVNUMBER is '非用户类业务为”0”';
comment on column SH_REC_LOG.BUSITYPE is '业务类型。如现金缴费为charge，银行卡缴费为chargebycard，发票打印及自助查询中清单打印等类型。见代码字典定义';
comment on column SH_REC_LOG.OPERID is '操作员工号';
comment on column SH_REC_LOG.ORGID is '业务受理单位编号';
comment on column SH_REC_LOG.RECDATE is '业务受理时间。YYYY-MM-DD HH24:MI:SS';
comment on column SH_REC_LOG.RECSTATUS is '受理状态,成功：0，失败：1';
comment on column SH_REC_LOG.RECSTATUSDESC is '受理状态描述';
comment on column SH_REC_LOG.RECFEE is '业务受理费用，无费用的业务为0';
comment on column SH_REC_LOG.DISCOUNT is '业务减免费用';
comment on column SH_REC_LOG.BRANDID is '用户所属品牌';
create index IDX_SH_RECLOG_OPERID on SH_REC_LOG (OPERID, REGION, RECDATE);
create index IDX_SH_RECLOG_RECFORMNUM on SH_REC_LOG (RECFORMNUM);
create index IDX_SH_RECLOG_SERVNUM on SH_REC_LOG (SERVNUMBER);
create unique index PK_SH_RECLOG_OID on SH_REC_LOG (OID, REGION, RECDATE);

prompt Creating SH_REC_LOG_MGR...
create table SH_REC_LOG_MGR
(
  MGRLOGID VARCHAR2(32) not null,
  OPERID   VARCHAR2(20) not null,
  REGION   NUMBER(5) not null,
  ORGID    VARCHAR2(32) not null,
  BUSITYPE VARCHAR2(32) not null,
  RECDATE  DATE not null,
  STATUS   NUMBER(1) not null,
  NOTES    VARCHAR2(256)
)
;
comment on table SH_REC_LOG_MGR is '	后台管理日志';
comment on column SH_REC_LOG_MGR.MGRLOGID is '日志ID(序列:SEQ_SH_RECOID)';
comment on column SH_REC_LOG_MGR.OPERID is '操作员ID';
comment on column SH_REC_LOG_MGR.REGION is '区域';
comment on column SH_REC_LOG_MGR.ORGID is '单位';
comment on column SH_REC_LOG_MGR.BUSITYPE is '类务类型';
comment on column SH_REC_LOG_MGR.RECDATE is '操作时间';
comment on column SH_REC_LOG_MGR.STATUS is '状态 1：成功   0：失败';
comment on column SH_REC_LOG_MGR.NOTES is '备注';

prompt Creating SH_ROLE_INFO...
create table SH_ROLE_INFO
(
  ROLEID         VARCHAR2(32) not null,
  ROLENAME       VARCHAR2(32) not null,
  CREATEDATE     DATE not null,
  CREATEOPERATOR VARCHAR2(32) not null,
  STATUS         NUMBER(1) not null,
  NOTES          VARCHAR2(256)
)
;
comment on table SH_ROLE_INFO is '	角色信息表';
comment on column SH_ROLE_INFO.ROLEID is '角色ID(序列：SEQ_SH_RECOID)';
comment on column SH_ROLE_INFO.ROLENAME is '角色名称';
comment on column SH_ROLE_INFO.CREATEDATE is '创建时间';
comment on column SH_ROLE_INFO.CREATEOPERATOR is '创建人';
comment on column SH_ROLE_INFO.STATUS is '状态，1在用，0禁用';
comment on column SH_ROLE_INFO.NOTES is '状态时间';

prompt Creating SH_ROLE_MENU...
create table SH_ROLE_MENU
(
  ROLEID     VARCHAR2(32) not null,
  MENUID     VARCHAR2(32) not null,
  STATUS     NUMBER(1) not null,
  STATUSDATE DATE not null
)
;
comment on table SH_ROLE_MENU is '	角色菜单关系表';
comment on column SH_ROLE_MENU.ROLEID is '角色ID';
comment on column SH_ROLE_MENU.MENUID is '菜单ID';
comment on column SH_ROLE_MENU.STATUS is '状态，1在用，0禁用';
comment on column SH_ROLE_MENU.STATUSDATE is '状态时间';

prompt Creating SH_SELLGOODS_LOG...
create table SH_SELLGOODS_LOG
(
  SELLGOODSLOGID       VARCHAR2(16) not null,
  REGION               NUMBER(5) not null,
  TERMID               VARCHAR2(32) not null,
  OPERID               VARCHAR2(20) not null,
  BOOTHCODE            VARCHAR2(64) not null,
  MERCHANDISEID        VARCHAR2(16) not null,
  PAYTYPE              NUMBER(1) not null,
  CUSTOMER             VARCHAR2(32),
  SELLNUM              NUMBER(8) not null,
  PRICE                VARCHAR2(8) not null,
  SELLFEE              VARCHAR2(8) not null,
  RECDATE              DATE,
  STATUS               VARCHAR2(2),
  DESCRIPTION          VARCHAR2(64),
  SHIPMENTNUM          VARCHAR2(8),
  SCORETOPRICE         VARCHAR2(8) default 0,
  UNIONPAYUSER         VARCHAR2(32),
  UNIONPAYCODE         VARCHAR2(32),
  BUSITYPE             VARCHAR2(32),
  CARDNUMBER           VARCHAR2(32),
  BATCHNUM             VARCHAR2(32),
  AUTHORIZATIONCODE    VARCHAR2(32),
  BUSINESSREFERENCENUM VARCHAR2(32),
  UNIONPAYTIME         DATE,
  VOUCHERNUM           VARCHAR2(32),
  UNIONPAYFEE          NUMBER(16)
)
;
comment on table SH_SELLGOODS_LOG is '	售货历史表';
comment on column SH_SELLGOODS_LOG.SELLGOODSLOGID is '终端流水(序列SEQ_SH_RECOID)';
comment on column SH_SELLGOODS_LOG.REGION is '售出地点';
comment on column SH_SELLGOODS_LOG.TERMID is '终端机编号';
comment on column SH_SELLGOODS_LOG.OPERID is '操作员编号';
comment on column SH_SELLGOODS_LOG.BOOTHCODE is '货道号';
comment on column SH_SELLGOODS_LOG.MERCHANDISEID is '商品ID';
comment on column SH_SELLGOODS_LOG.PAYTYPE is '支付方式 0：手机钱包支付 1：银联卡支付 2：手机支付 3：积分兑换';
comment on column SH_SELLGOODS_LOG.CUSTOMER is '购买人信息 如果是手机钱包支付，为手机钱包卡号；如果是积分兑换方式，为手机号码';
comment on column SH_SELLGOODS_LOG.SELLNUM is '售出数量';
comment on column SH_SELLGOODS_LOG.PRICE is '单价 如果是手机钱包支付，为商品售价，单位分；如果是积分兑换方式，为所需积分';
comment on column SH_SELLGOODS_LOG.SELLFEE is '总金额 如果是手机钱包支付，为单价 * 购买数量，单位分；如果是积分兑换方式，为所需全部积分';
comment on column SH_SELLGOODS_LOG.RECDATE is '受理时间（秒）';
comment on column SH_SELLGOODS_LOG.STATUS is '交易结果，11：交易成功；10：手机钱包扣款成功或者积分扣减成功，但是最终交易失败（包含未正常出货和扣款后的其它异常情况）；01：积分扣减成功或者手机钱包扣款成功的临时状态；00：除10之外的其他交易失败情况';
comment on column SH_SELLGOODS_LOG.DESCRIPTION is '交易结果描述';
comment on column SH_SELLGOODS_LOG.SHIPMENTNUM is '实际出货数量';
comment on column SH_SELLGOODS_LOG.SCORETOPRICE is '积分对应金额';
comment on column SH_SELLGOODS_LOG.UNIONPAYUSER is '商户编号';
comment on column SH_SELLGOODS_LOG.UNIONPAYCODE is 'POS机编号';
comment on column SH_SELLGOODS_LOG.BUSITYPE is '交易类型';
comment on column SH_SELLGOODS_LOG.CARDNUMBER is '银行卡号';
comment on column SH_SELLGOODS_LOG.BATCHNUM is '批次号';
comment on column SH_SELLGOODS_LOG.AUTHORIZATIONCODE is '授权码';
comment on column SH_SELLGOODS_LOG.BUSINESSREFERENCENUM is '交易参考号';
comment on column SH_SELLGOODS_LOG.UNIONPAYTIME is '银联扣款日期时间';
comment on column SH_SELLGOODS_LOG.VOUCHERNUM is '凭证号';
comment on column SH_SELLGOODS_LOG.UNIONPAYFEE is '银联扣款金额，单位（分）';

prompt Creating SH_TERM_CONFIG...
create table SH_TERM_CONFIG
(
  TERMID       VARCHAR2(32) not null,
  TERMNAME     VARCHAR2(64) not null,
  IPADDR       VARCHAR2(32) not null,
  MAC          VARCHAR2(32),
  OPERID       VARCHAR2(20) not null,
  PLANSIGNTIME VARCHAR2(14),
  REALSIGNTIME DATE,
  UNIONUSERID  VARCHAR2(32),
  UNIONPAYCODE VARCHAR2(32),
  BROWSERTYPE  VARCHAR2(32),
  TERMSPECIAL  VARCHAR2(20) not null,
  PROVIDERCODE VARCHAR2(32) not null,
  ORGID        VARCHAR2(32) not null,
  SOCKADDR     VARCHAR2(32),
  CREATEDATE   DATE not null,
  STATUS       NUMBER(1) not null,
  STATUSDATE   DATE not null,
  REGION       NUMBER(5) not null,
  ORGNAME      VARCHAR2(64) not null,
  MACHINEMODEL VARCHAR2(32),
  TRMCODE      VARCHAR2(10),
  DESCRIBE     VARCHAR2(200),
  PASSWORD     VARCHAR2(200)
)
;
comment on table SH_TERM_CONFIG is '	终端信息表';
comment on column SH_TERM_CONFIG.TERMID is '终端编号';
comment on column SH_TERM_CONFIG.TERMNAME is '终端名称';
comment on column SH_TERM_CONFIG.IPADDR is 'IP地址';
comment on column SH_TERM_CONFIG.MAC is 'Mac地址';
comment on column SH_TERM_CONFIG.OPERID is '操作员工号';
comment on column SH_TERM_CONFIG.PLANSIGNTIME is '计划签到时间HH24MISS';
comment on column SH_TERM_CONFIG.REALSIGNTIME is '实际签到时间YYYY-MM-DD HH24:MI:SS';
comment on column SH_TERM_CONFIG.UNIONUSERID is '银联商户号';
comment on column SH_TERM_CONFIG.UNIONPAYCODE is '银联为刷卡的终端分配的唯一编号';
comment on column SH_TERM_CONFIG.BROWSERTYPE is 'ie;firefox';
comment on column SH_TERM_CONFIG.TERMSPECIAL is '终端特性,现为12位数字字符串.第1位是否支持打印票据,第2位是否支持打印发票,第3位是否带有加密键盘,第4位是否支持现金缴费,第5位是否支持银行卡缴费,第6位是否带有管理控件,第7位是否带有发卡控件,第8位是否带有写卡控件,第9位是否支持读取第二代省份证信息;第10位银联OCX;第11位是否支持手机钱包;第12位是否带有自助售货。1表示支持，0表示不支持。例如111101111表示除了银行卡缴费不支持其余都支持.';
comment on column SH_TERM_CONFIG.PROVIDERCODE is '厂商编码';
comment on column SH_TERM_CONFIG.ORGID is '营业点单位编码';
comment on column SH_TERM_CONFIG.SOCKADDR is '自助终端机连接自助管理平台的ip和端口，ip,port';
comment on column SH_TERM_CONFIG.CREATEDATE is '创建时间';
comment on column SH_TERM_CONFIG.STATUS is '状态 1可用，0禁用';
comment on column SH_TERM_CONFIG.STATUSDATE is '状态时间';
comment on column SH_TERM_CONFIG.REGION is '地市';
comment on column SH_TERM_CONFIG.ORGNAME is '营业点单位名称';
comment on column SH_TERM_CONFIG.MACHINEMODEL is '机器型号';
comment on column SH_TERM_CONFIG.TRMCODE is '设备编码(公共事业缴费时用到)';
comment on column SH_TERM_CONFIG.DESCRIBE is '备注（可写具体地址）';
comment on column SH_TERM_CONFIG.PASSWORD is '密码';

prompt Creating SH_TERM_GROUP...
create table SH_TERM_GROUP
(
  TERMID     VARCHAR2(32) not null,
  TERMGRPID  VARCHAR2(32) not null,
  CREATEDATE DATE not null,
  STATUS     NUMBER(1) not null,
  STATUSDATE DATE not null
)
;
comment on table SH_TERM_GROUP is '	终端组包含终端信息表';
comment on column SH_TERM_GROUP.TERMID is '终端ID';
comment on column SH_TERM_GROUP.TERMGRPID is '终端组编号';
comment on column SH_TERM_GROUP.CREATEDATE is '创建时间';
comment on column SH_TERM_GROUP.STATUS is '状态，1在用，0禁用';
comment on column SH_TERM_GROUP.STATUSDATE is '状态时间';

prompt Creating SH_TERM_MODEL...
create table SH_TERM_MODEL
(
  TERMMODELID    VARCHAR2(32) not null,
  TERMMODEL      VARCHAR2(64) not null,
  MANUFACTURERID VARCHAR2(32) not null,
  DESCRIBE       VARCHAR2(96),
  STATUS         NUMBER(1) not null,
  STATUSDATE     DATE not null
)
;
comment on table SH_TERM_MODEL is '终端型号表';
comment on column SH_TERM_MODEL.TERMMODELID is '型号ID(序列：SEQ_SH_RECOID)';
comment on column SH_TERM_MODEL.TERMMODEL is '型号';
comment on column SH_TERM_MODEL.MANUFACTURERID is '所属厂商';
comment on column SH_TERM_MODEL.DESCRIBE is '描述';
comment on column SH_TERM_MODEL.STATUS is '状态，1，可用;0，停用';
comment on column SH_TERM_MODEL.STATUSDATE is '状态日期';

prompt Creating SH_UNIONFILE_LOG...
create table SH_UNIONFILE_LOG
(
  FILEOID      VARCHAR2(32),
  FILENAME     VARCHAR2(100),
  RECNUM       NUMBER(14),
  RECFEE       NUMBER(14),
  REALRECNUM   NUMBER(14),
  REALRECFEE   NUMBER(14),
  RESULTSTATUS VARCHAR2(4),
  RESULTINFO   VARCHAR2(100),
  RECDATE      DATE
)
;
comment on column SH_UNIONFILE_LOG.FILEOID is '日志流水号';
comment on column SH_UNIONFILE_LOG.FILENAME is '文件名称';
comment on column SH_UNIONFILE_LOG.RECNUM is '文件内数据量';
comment on column SH_UNIONFILE_LOG.RECFEE is '文件内总金额';
comment on column SH_UNIONFILE_LOG.REALRECNUM is '文件内实际数据量';
comment on column SH_UNIONFILE_LOG.REALRECFEE is '文件内实际总金额';
comment on column SH_UNIONFILE_LOG.RESULTSTATUS is '文件处理结果  0:正常 1:格式错误 2:入库异常 1001:文件头的记录数与文件体中的行数不一致 1003:文件头的总金额与文件体中的总金额不一致';
comment on column SH_UNIONFILE_LOG.RESULTINFO is '文件处理详细信息';
comment on column SH_UNIONFILE_LOG.RECDATE is '入库时间';

prompt Creating SH_UNIONPAY_LOG...
create table SH_UNIONPAY_LOG
(
  BANK       VARCHAR2(32) not null,
  UNIONSEQ   VARCHAR2(20) not null,
  BUSITYPE   VARCHAR2(32) not null,
  TERMINALID VARCHAR2(32) not null,
  FEE        NUMBER(16) not null,
  SHANGHUSEQ VARCHAR2(32) not null,
  RECDATE    DATE,
  CARDNO     VARCHAR2(32) not null,
  FLAG       VARCHAR2(32) not null
)
;
comment on table SH_UNIONPAY_LOG is '	银联缴费交易对账中间表';
comment on column SH_UNIONPAY_LOG.BANK is '发卡行';
comment on column SH_UNIONPAY_LOG.UNIONSEQ is '银联交易流水号';
comment on column SH_UNIONPAY_LOG.BUSITYPE is '交易类型';
comment on column SH_UNIONPAY_LOG.TERMINALID is '终端编号';
comment on column SH_UNIONPAY_LOG.FEE is '金额';
comment on column SH_UNIONPAY_LOG.SHANGHUSEQ is '商户流水号';
comment on column SH_UNIONPAY_LOG.RECDATE is '交易时间';
comment on column SH_UNIONPAY_LOG.CARDNO is '卡号';
comment on column SH_UNIONPAY_LOG.FLAG is '冲正标识';

prompt Creating SH_UNIONRECORD_LOG...
create table SH_UNIONRECORD_LOG
(
  ID            VARCHAR2(32),
  FORMNUM       VARCHAR2(32),
  POSNUM        VARCHAR2(32),
  BATCHNUM      VARCHAR2(32),
  CARDNUMBER    VARCHAR2(32),
  UNIONPAYFEE   NUMBER(14),
  RECDATE       DATE,
  FILEOID       VARCHAR2(32),
  CREATEDATE    DATE,
  COMPARESTATUS CHAR(1)
)
;
comment on column SH_UNIONRECORD_LOG.ID is '维一标识';
comment on column SH_UNIONRECORD_LOG.FORMNUM is '现金缴费流水号';
comment on column SH_UNIONRECORD_LOG.POSNUM is '跟踪号';
comment on column SH_UNIONRECORD_LOG.BATCHNUM is '批次号';
comment on column SH_UNIONRECORD_LOG.CARDNUMBER is '银行卡号';
comment on column SH_UNIONRECORD_LOG.UNIONPAYFEE is '缴费金额(分)';
comment on column SH_UNIONRECORD_LOG.RECDATE is '缴费时间';
comment on column SH_UNIONRECORD_LOG.FILEOID is '入库文件流水号';
comment on column SH_UNIONRECORD_LOG.CREATEDATE is '入库时间';
comment on column SH_UNIONRECORD_LOG.COMPARESTATUS is '对账状态（0：未对账、1：对账成功  2：对账失败）';

prompt Creating SH_UNIONRECORD_LOG_SD...
create table SH_UNIONRECORD_LOG_SD
(
  ID            VARCHAR2(32),
  CARDBANK      VARCHAR2(32),
  CENTERSEQ     VARCHAR2(32),
  BUSINESSTYPE  VARCHAR2(32),
  TERMCODE      VARCHAR2(32),
  UNIONPAYFEE   NUMBER(14),
  TRADERSEQ     VARCHAR2(32),
  CARDNUM       VARCHAR2(32),
  RECDATE       DATE,
  CZSTATUS      VARCHAR2(32),
  FILEOID       VARCHAR2(32),
  CREATEDATE    DATE,
  COMPARESTATUS CHAR(1)
)
;
-- Add comments to the columns 
comment on column SH_UNIONRECORD_LOG_SD.ID
  is '维一标识';
comment on column SH_UNIONRECORD_LOG_SD.CARDBANK
  is '发卡行';
comment on column SH_UNIONRECORD_LOG_SD.CENTERSEQ
  is '中心流水';
comment on column SH_UNIONRECORD_LOG_SD.BUSINESSTYPE
  is '交易类型';
comment on column SH_UNIONRECORD_LOG_SD.TERMCODE
  is '终端编码';
comment on column SH_UNIONRECORD_LOG_SD.UNIONPAYFEE
  is '缴费金额(分)';
comment on column SH_UNIONRECORD_LOG_SD.TRADERSEQ
  is '商户流水号';
comment on column SH_UNIONRECORD_LOG_SD.CARDNUM
  is '卡号';
comment on column SH_UNIONRECORD_LOG_SD.RECDATE
  is '缴费时间';
comment on column SH_UNIONRECORD_LOG_SD.CZSTATUS
  is '冲正标志';
comment on column SH_UNIONRECORD_LOG_SD.FILEOID
  is '入库文件流水号';
comment on column SH_UNIONRECORD_LOG_SD.CREATEDATE
  is '入库时间';
comment on column SH_UNIONRECORD_LOG_SD.COMPARESTATUS
  is '对账状态（0：未对账、1：对账成功  2：对账失败）';

-- Create table
create table SH_PROD_NCODE
(
  NCODE          VARCHAR2(32) not null,
  NAME           VARCHAR2(64) not null,
  REMARK         VARCHAR2(128),
  GROUPCOLOR     VARCHAR2(7),
  BRANDS         VARCHAR2(3),
  EFFTYPE        VARCHAR2(1),
  STATUS         NUMBER(1),
  STATUSTIME     DATE
);
-- Add comments to the table 
comment on table SH_PROD_NCODE
  is '可受理产品定义表';
-- Add comments to the columns 
comment on column SH_PROD_NCODE.NCODE
  is '业务编码';
comment on column SH_PROD_NCODE.NAME
  is '名称';
comment on column SH_PROD_NCODE.REMARK
  is '备注';
comment on column SH_PROD_NCODE.GROUPCOLOR
  is '分组颜色';
comment on column SH_PROD_NCODE.BRANDS
  is '可受理品牌 1:是 0:否 第一位代表全球通，第二位代表动感地带，第三位代表神州行';
comment on column SH_PROD_NCODE.EFFTYPE
  is '生效方式 0:立即生效 1:下月生效 2:立即或下月';
comment on column SH_PROD_NCODE.STATUS
  is '状态 1:有效 0:无效';
comment on column SH_PROD_NCODE.STATUSTIME
  is '状态时间';
-- Create/Recreate indexes 
create unique index PK_SH_PROD_NCODE on SH_PROD_NCODE (NCODE);

set feedback on
set define on
prompt Done.
