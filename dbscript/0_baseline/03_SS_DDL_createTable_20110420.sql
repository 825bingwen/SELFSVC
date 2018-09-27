--==============================================================
-- ���ű������������ݣ��û�ngsh��
-- 1.ɾ���û����������ݿ��(drop)
-- 2.�����û����������ݿ��,����������
-- 3.ɾ���û������е���ͼ(drop)
-- 4.�����û������е���ͼ
-- 
-- ���ű���ngsh�û�ִ��
-- 
--==============================================================

set feedback off
set define off

-- 1.ɾ���û����������ݿ��(drop)
prompt Dropping SH_ALARM_BIND...
drop table SH_ALARM_BIND cascade constraints;
prompt Dropping SH_ALARM_INFO...
drop table SH_ALARM_INFO cascade constraints;
prompt Dropping SH_ALARM_RULE...
drop table SH_ALARM_RULE cascade constraints;
prompt Dropping SH_CHARGE_LOG...
drop table SH_CHARGE_LOG cascade constraints;
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
prompt Dropping SH_INFO_OPERATOR...
drop table SH_INFO_OPERATOR cascade constraints;
prompt Dropping SH_INFO_PLUGIN...
drop table SH_INFO_PLUGIN cascade constraints;
prompt Dropping SH_INFO_REGIONLIST...
drop table SH_INFO_REGIONLIST cascade constraints;
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
prompt Dropping SH_LOG_TERMSTATUS...
drop table SH_LOG_TERMSTATUS cascade constraints;
prompt Dropping SH_LOG_TERMUP...
drop table SH_LOG_TERMUP cascade constraints;
prompt Dropping SH_MENU_ITEM...
drop table SH_MENU_ITEM cascade constraints;
prompt Dropping SH_MENU_ITEM_MGR...
drop table SH_MENU_ITEM_MGR cascade constraints;
prompt Dropping SH_ORGANIZATION...
drop table SH_ORGANIZATION cascade constraints;
prompt Dropping SH_ORGANIZATION_CHILD...
drop table SH_ORGANIZATION_CHILD cascade constraints;
prompt Dropping SH_PARAM...
drop table SH_PARAM cascade constraints;
prompt Dropping SH_PARAM_VALUE...
drop table SH_PARAM_VALUE cascade constraints;
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
prompt Dropping SH_UNIONPAY_LOG...
drop table SH_UNIONPAY_LOG cascade constraints;





-- 2.�����û����������ݿ��,����������


prompt Creating SH_ALARM_BIND...
create table SH_ALARM_BIND
(
  ALARMBINDID VARCHAR2(32) not null,
  TERMID      VARCHAR2(32) not null,
  OPERID      VARCHAR2(32) not null,
  STATUS      NUMBER(1)
)
;
comment on table SH_ALARM_BIND is '	�ն˸澯����Ϣ';
comment on column SH_ALARM_BIND.ALARMBINDID is '�澯��(���У�SEQ_SH_RECOID)';
comment on column SH_ALARM_BIND.TERMID is '�ն˱��';
comment on column SH_ALARM_BIND.OPERID is '���ܸ澯��';
comment on column SH_ALARM_BIND.STATUS is '״̬  1����Ч 0����Ч';

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
comment on table SH_ALARM_INFO is '	�ն˸澯��Ϣ';
comment on column SH_ALARM_INFO.ALARMINFOID is '�澯��(���У�SEQ_SH_RECOID)';
comment on column SH_ALARM_INFO.ALARMDATE is '�澯ʱ��';
comment on column SH_ALARM_INFO.EXCEPTIONTYPE is '�쳣����';
comment on column SH_ALARM_INFO.TERMID is '�ն�ID';
comment on column SH_ALARM_INFO.ALARMLEVEL is '�澯����0����ͨ��1����Ҫ��2�����ء�';
comment on column SH_ALARM_INFO.UNCHAINDATE is '�澯���ʱ��';
comment on column SH_ALARM_INFO.PERSISTDEGREE is '���͸澯���ŵĴ���';
comment on column SH_ALARM_INFO.DEALER is '������';
comment on column SH_ALARM_INFO.DEALTIME is '����ʱ��';
comment on column SH_ALARM_INFO.DEALDESP is '��������';
comment on column SH_ALARM_INFO.UNCHAIN is '�澯���״̬(0:δ��� 1:���)';

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
comment on table SH_ALARM_RULE is '	�ն˸澯����';
comment on column SH_ALARM_RULE.ALARMRULEID is 'ID(����:SEQ_SH_RECOID)';
comment on column SH_ALARM_RULE.EXCEPTIONTYPE is '��������';
comment on column SH_ALARM_RULE.STARTNUM is '��ʼ����';
comment on column SH_ALARM_RULE.ENDNUM is '��������';
comment on column SH_ALARM_RULE.STATUS is '״̬(1��δ���� 0���Ѵ���)';
comment on column SH_ALARM_RULE.NOTES is '��ע';
comment on column SH_ALARM_RULE.STATUSDATE is '״̬ʱ��';
comment on column SH_ALARM_RULE.CREATEOPERATOR is '�����û�';

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
  CHECKFLAG             NUMBER(1),
  ORGID                 VARCHAR2(32),
  POSNUM                VARCHAR2(32),
  BATCHNUMBEFOREKOUKUAN VARCHAR2(32)
)
;
comment on table SH_CHARGE_LOG is '	���ѽɷ���־��';
comment on column SH_CHARGE_LOG.CHARGELOGOID is '�ɷ���־Ψһ��ʶ(SEQ_SH_RECOID)';
comment on column SH_CHARGE_LOG.REGION is '�ɷѵص�';
comment on column SH_CHARGE_LOG.TERMID is '�ն˻����';
comment on column SH_CHARGE_LOG.OPERID is '����Ա���';
comment on column SH_CHARGE_LOG.SERVNUMBER is '�ɷѺ���';
comment on column SH_CHARGE_LOG.PAYTYPE is '�ɷѷ�ʽ��1����������4���ֽ�';
comment on column SH_CHARGE_LOG.FEE is '�ɷѽ���λ���֣�';
comment on column SH_CHARGE_LOG.UNIONPAYUSER is '�̻����';
comment on column SH_CHARGE_LOG.UNIONPAYCODE is '�������ն˱�ţ�POS�����';
comment on column SH_CHARGE_LOG.BUSITYPE is '��������';
comment on column SH_CHARGE_LOG.CARDNUMBER is '���п���';
comment on column SH_CHARGE_LOG.BATCHNUM is '�ۿ�֮������κ�';
comment on column SH_CHARGE_LOG.AUTHORIZATIONCODE is '��Ȩ��';
comment on column SH_CHARGE_LOG.BUSINESSREFERENCENUM is '���ײο���';
comment on column SH_CHARGE_LOG.UNIONPAYTIME is '�����ۿ�����ʱ��';
comment on column SH_CHARGE_LOG.VOUCHERNUM is 'ƾ֤��';
comment on column SH_CHARGE_LOG.UNIONPAYFEE is '�����ۿ����λ���֣�';
comment on column SH_CHARGE_LOG.TERMINALSEQ is '������ˮ�ţ����ն���ˮ��';
comment on column SH_CHARGE_LOG.RECDATE is '����ʱ��';
comment on column SH_CHARGE_LOG.STATUS is '����״̬��11���ɷѳɹ���10��Ͷ�ҳɹ����������ۿ�ɹ������ǽɷ�ʧ�ܣ�01��Ͷ�ҳɹ����������ۿ�ɹ�����ʱ״̬��00����10�������ɷ�ʧ�ܵ����';
comment on column SH_CHARGE_LOG.DESCRIPTION is '���׽������';
comment on column SH_CHARGE_LOG.DEALNUM is 'BOSS�������';
comment on column SH_CHARGE_LOG.ACCEPTTYPE is 'BOSS��������';
comment on column SH_CHARGE_LOG.SERVREGION is '�ɷѺ���Ĺ�������';
comment on column SH_CHARGE_LOG.CHECKFLAG is '�����������ɷѼ�¼���ã������¼�������ˣ�ֵΪ1(���������ۿ�ɹ�)������Ϊ�ա�ȡ���˼�¼ʱ����ȡcheckflag=1�ļ�¼';
comment on column SH_CHARGE_LOG.ORGID is '��֯�ṹid';
comment on column SH_CHARGE_LOG.POSNUM is '�������ۿ�֮ǰ�ĸ��ٺ�';
comment on column SH_CHARGE_LOG.BATCHNUMBEFOREKOUKUAN is '�������ۿ�֮ǰ�����κ�';
create index IDX_SH_CHARGELOG_REGION on SH_CHARGE_LOG (REGION);
create index IDX_SH_CHARGELOG_SERV on SH_CHARGE_LOG (SERVNUMBER);

prompt Creating SH_CHOOSETEL_NUM...
create table SH_CHOOSETEL_NUM
(
  IDCARD        VARCHAR2(32) not null,
  CHOOSETELTIME NUMBER(2) not null,
  CHOOSETELDATE DATE not null
)
;
comment on table SH_CHOOSETEL_NUM is '  ����ԤԼ������';
comment on column SH_CHOOSETEL_NUM.IDCARD is '����֤';
comment on column SH_CHOOSETEL_NUM.CHOOSETELTIME is 'ѡ�Ŵ���';
comment on column SH_CHOOSETEL_NUM.CHOOSETELDATE is 'ѡ��ʱ��';

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
comment on table SH_COMMONPAY_LOG is '	������ҵ�ɷ���־��';
comment on column SH_COMMONPAY_LOG.COMMONPAYID is '�ɷ���ʷID������:SEQ_SH_RECOID���ɷ�������ˮ';
comment on column SH_COMMONPAY_LOG.REGION is '�ɷѵص�';
comment on column SH_COMMONPAY_LOG.MER_TYPE is '�ɷѵ�λ����(08:��� 02:ˮ�� 03:ú�� 04: ���ߵ��ӷ�)';
comment on column SH_COMMONPAY_LOG.MER_CODE is '�ɷѵ�λ���루���ֵ����ȡ�ã�';
comment on column SH_COMMONPAY_LOG.FEE_CODE is '������ҵ�ɷѺ���(�罻��ѵĵ翨����)';
comment on column SH_COMMONPAY_LOG.PAY_MONEY is '�ɷѽ��(��)';
comment on column SH_COMMONPAY_LOG.CUSTOMER is '�ɷ�����Ϣ����ɷѷ�ʽΪ�ֻ�֧�����˴�Ϊ�ֻ����룩';
comment on column SH_COMMONPAY_LOG.STATUS is '�ɷ�״̬   0��ʧ��     1���ɹ�     2�����ɽɷ���ˮ��';
comment on column SH_COMMONPAY_LOG.RECDATE is '�ɷ�ʱ��';
comment on column SH_COMMONPAY_LOG.TERMID is '�ն˻����';
comment on column SH_COMMONPAY_LOG.PAYTYPE is '�ɷѷ�ʽ����0:�ָ�����;
1:CMPAY;
2:����;
��0���ֻ�Ǯ����1����������2���ֻ�֧����3�����ֶһ�';
comment on column SH_COMMONPAY_LOG.TRM_CODE is '�豸����(�磺A020000001��A020000002��A020000003)';
comment on column SH_COMMONPAY_LOG.MPAY_FLOW is '֧����ˮ    �ָ�������ˮ';
comment on column SH_COMMONPAY_LOG.RES_DATE is '��Ӧʱ��';
comment on column SH_COMMONPAY_LOG.RTN_CODE is '����״̬';
comment on column SH_COMMONPAY_LOG.RTN_MSG is '��Ӧ����';
comment on column SH_COMMONPAY_LOG.ORGID is '��֯�ṹID';

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
comment on table SH_DICT_ITEM is '	�ֵ��';
comment on column SH_DICT_ITEM.DICTID is 'ID';
comment on column SH_DICT_ITEM.DICTNAME is '����';
comment on column SH_DICT_ITEM.GROUPID is '��ID';
comment on column SH_DICT_ITEM.SORTORDER is '˳��';
comment on column SH_DICT_ITEM.STATUS is '״̬';
comment on column SH_DICT_ITEM.STATUSDATE is '״̬����';
comment on column SH_DICT_ITEM.DESCRIPTION is '˵��';
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
comment on table SH_GROUP_ADV is '	�ն�������Դ��ϵ��';
comment on column SH_GROUP_ADV.TERMGRPID is '�ն���ID';
comment on column SH_GROUP_ADV.ADVLIST is '����б����磺10000001,1000002��';
comment on column SH_GROUP_ADV.CREATEDATE is '����ʱ��';
comment on column SH_GROUP_ADV.CREATEOPER is '�����û�';
comment on column SH_GROUP_ADV.STATUS is '״̬(1����Ч 0����Ч)';
comment on column SH_GROUP_ADV.STATUSDATE is '״̬ʱ��';
comment on column SH_GROUP_ADV.RELEASEOPER is '�����û�';

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
comment on table SH_GROUP_BUDDY is '	�ն����Żݴ����̼ҹ�ϵ��';
comment on column SH_GROUP_BUDDY.TERMGROUPID is '�ն���ID';
comment on column SH_GROUP_BUDDY.BUDDYID is '�̼�ID';
comment on column SH_GROUP_BUDDY.CREATEDATE is '����ʱ��';
comment on column SH_GROUP_BUDDY.STATUS is '״̬��1����Ч 0����Ч��';
comment on column SH_GROUP_BUDDY.STATUSDATE is '״̬ʱ��';

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
comment on table SH_GROUP_INFO is '	�ն�����Ϣ';
comment on column SH_GROUP_INFO.REGION is '����';
comment on column SH_GROUP_INFO.TERMGROUPID is '�ն���ID(���У�SEQ_SH_RECOID)';
comment on column SH_GROUP_INFO.TERMGROUPNAME is '�ն�������';
comment on column SH_GROUP_INFO.STATUS is '״̬';
comment on column SH_GROUP_INFO.STATUSDATE is '״̬ʱ��';
comment on column SH_GROUP_INFO.ORGID is '��������';
comment on column SH_GROUP_INFO.NOTES is '��ע';

prompt Creating SH_GROUP_MENU...
create table SH_GROUP_MENU
(
  TERMGRPID  VARCHAR2(32) not null,
  MENUID     VARCHAR2(32) not null,
  STATUS     NUMBER(1) not null,
  STATUSDATE DATE not null
)
;
comment on table SH_GROUP_MENU is '	�˵����ն���Ĺ�ϵ��';
comment on column SH_GROUP_MENU.TERMGRPID is 'SH_TERMINAL_GROUP.termgrpid';
comment on column SH_GROUP_MENU.MENUID is 'SH_MENUITEM.menuid';
comment on column SH_GROUP_MENU.STATUS is '״̬��1���ã�0����';
comment on column SH_GROUP_MENU.STATUSDATE is '״̬ʱ��';
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
comment on table SH_GROUP_SCREEN is '	�ն���������Դ��ϵ��';
comment on column SH_GROUP_SCREEN.TERMGRPID is '�ն���ID';
comment on column SH_GROUP_SCREEN.SCLIST is '����б�(��1000001,100002)';
comment on column SH_GROUP_SCREEN.CREATEDATE is '����ʱ��';
comment on column SH_GROUP_SCREEN.CREATEOPER is '�����û�';
comment on column SH_GROUP_SCREEN.STATUS is '״̬';
comment on column SH_GROUP_SCREEN.STATUSDATE is '״̬ʱ��';
comment on column SH_GROUP_SCREEN.RELEASEOPER is '�����û�';

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
comment on table SH_INFO_ABATE is '	�ۿ۲�Ʒ��';
comment on column SH_INFO_ABATE.ABATEID is '�Ż�ȯID(���У�SEQ_SH_RECOID)';
comment on column SH_INFO_ABATE.ABATENAME is '�Ż�ȯ����';
comment on column SH_INFO_ABATE.BUDDYID is '�����̼�ID';
comment on column SH_INFO_ABATE.ABATEINFO is '�Ż�ȯ��Ϣ';
comment on column SH_INFO_ABATE.ABATEPRICE is '�Ż�ȯ����';
comment on column SH_INFO_ABATE.ABATESUM is '�Ż�ȯ����';
comment on column SH_INFO_ABATE.ABATEUSENUM is '��ʹ������';
comment on column SH_INFO_ABATE.STATUS is '״̬';
comment on column SH_INFO_ABATE.CREATEDATE is '����ʱ��';
comment on column SH_INFO_ABATE.STATUSDATE is '״̬ʱ��';
comment on column SH_INFO_ABATE.ENDDATE is '����ʱ��';
comment on column SH_INFO_ABATE.ABATEIMAGEPATH is 'ͼƬ·��';
comment on column SH_INFO_ABATE.ISFORFREE is '�Ƿ����';

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
comment on table SH_INFO_BUDDY is '	�����̼ұ�';
comment on column SH_INFO_BUDDY.BUDDYID is '�̼�ID(���У�SEQ_SH_RECOID)';
comment on column SH_INFO_BUDDY.BUDDYNAME is '�̼�����';
comment on column SH_INFO_BUDDY.BUDDYTYPE is '�̼�����';
comment on column SH_INFO_BUDDY.REGION is '����';
comment on column SH_INFO_BUDDY.STATUS is '״̬';
comment on column SH_INFO_BUDDY.CREATEDATE is '����ʱ��';
comment on column SH_INFO_BUDDY.STATUSDATE is '״̬ʱ��';
comment on column SH_INFO_BUDDY.ORGID is '������֯';

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
comment on table SH_INFO_MANUFACTURER is '	�ն˳�������';
comment on column SH_INFO_MANUFACTURER.MANUFACTURERID is '����ID�����У�SEQ_SH_RECOID��';
comment on column SH_INFO_MANUFACTURER.MANUFACTURERNAME is '��������';
comment on column SH_INFO_MANUFACTURER.MANUFACTURERADDRESS is '���̵�ַ';
comment on column SH_INFO_MANUFACTURER.PHONENUMBER is '�绰����';
comment on column SH_INFO_MANUFACTURER.OPERID is '������';
comment on column SH_INFO_MANUFACTURER.CREATEDATE is '����ʱ��';
comment on column SH_INFO_MANUFACTURER.RECDATE is 'ҵ��ʱ��';

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
comment on table SH_INFO_MEDIARES is '	���������Ϣ��';
comment on column SH_INFO_MEDIARES.REGION is '����';
comment on column SH_INFO_MEDIARES.RESID is '��ԴID(����:SEQ_SH_RECOID)';
comment on column SH_INFO_MEDIARES.RESNAME is '��Դ����';
comment on column SH_INFO_MEDIARES.RESLOCALFILENAME is '�����ļ���';
comment on column SH_INFO_MEDIARES.RESTYPE is '��Դ����';
comment on column SH_INFO_MEDIARES.STATUS is '״̬��1����Ч 0����Ч��';
comment on column SH_INFO_MEDIARES.STATUSDATE is '״̬ʱ��';
comment on column SH_INFO_MEDIARES.CREATEDATE is '����ʱ��';
comment on column SH_INFO_MEDIARES.CREATEOPER is '�����û�';
comment on column SH_INFO_MEDIARES.RESPLAYTIME is '����ʱ�䣨�룩';
comment on column SH_INFO_MEDIARES.ORGID is '��������';

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
comment on table SH_INFO_OPERATOR is '	�ն˲���Ա��';
comment on column SH_INFO_OPERATOR.OPERID is '����ԱID';
comment on column SH_INFO_OPERATOR.REGION is '�������';
comment on column SH_INFO_OPERATOR.OPERNAME is '����Ա����';
comment on column SH_INFO_OPERATOR.PASSWORD is '����';
comment on column SH_INFO_OPERATOR.PASSCHGDATE is '�����޸�ʱ��';
comment on column SH_INFO_OPERATOR.OPERGROUP is '����Ա��';
comment on column SH_INFO_OPERATOR.OPERTYPE is '����Ա����';
comment on column SH_INFO_OPERATOR.OPERLEVEL is '����Ա����';
comment on column SH_INFO_OPERATOR.ISMANAGER is '�Ƿ�����';
comment on column SH_INFO_OPERATOR.CONTACTPHONE is '�绰����';
comment on column SH_INFO_OPERATOR.ORGID is 'Ƭ������';
comment on column SH_INFO_OPERATOR.STARTTIME is '��ʼʱ��';
comment on column SH_INFO_OPERATOR.ENDTIME is '����ʱ��';
comment on column SH_INFO_OPERATOR.ENABLEGPRS is 'GPRS�Ƿ����';
comment on column SH_INFO_OPERATOR.GPRSSTARTTIME is 'GPRS��ʼʱ��';
comment on column SH_INFO_OPERATOR.GPRSENDTIME is 'GPRS����ʱ��';
comment on column SH_INFO_OPERATOR.ISCHKMAC is '�Ƿ�У��MAC��ַ';
comment on column SH_INFO_OPERATOR.MAC is 'MAC��ַ';
comment on column SH_INFO_OPERATOR.NOTES is '��ע';
comment on column SH_INFO_OPERATOR.CREATEDATE is '����ʱ��';
comment on column SH_INFO_OPERATOR.STATUS is '״̬';
comment on column SH_INFO_OPERATOR.STATUSDATE is '״̬ʱ��';
comment on column SH_INFO_OPERATOR.LOGINTYPE is '��¼����';
comment on column SH_INFO_OPERATOR.AREAID is '����ID';

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
comment on table SH_INFO_PLUGIN is '	�ն˲����Ϣ��';
comment on column SH_INFO_PLUGIN.PROVIDERID is '���̱��';
comment on column SH_INFO_PLUGIN.BROWSERTYPE is '���������ie,firefox';
comment on column SH_INFO_PLUGIN.SERVIPADDR is 'FTP������IP��ַ';
comment on column SH_INFO_PLUGIN.FTPUSERNAME is 'FTP�������û���';
comment on column SH_INFO_PLUGIN.FTPPWD is 'FTP����������';
comment on column SH_INFO_PLUGIN.PRINTERVER is '��ӡ���ؼ��汾';
comment on column SH_INFO_PLUGIN.PRTFTPADDR is '��ӡ���ؼ�����Ŀ¼';
comment on column SH_INFO_PLUGIN.PRTPLUGINFLAG is '��ӡ���ؼ���ʶ';
comment on column SH_INFO_PLUGIN.INVPRINTERVER is '��Ʊ��ӡ���ؼ��汾';
comment on column SH_INFO_PLUGIN.INVPRTFTPADDR is '��Ʊ��ӡ���ؼ�����Ŀ¼';
comment on column SH_INFO_PLUGIN.INVPRTPLUGINFLAG is '��Ʊ��ӡ���ؼ���ʶ';
comment on column SH_INFO_PLUGIN.KEYBOARDVER is '������̿ؼ��汾';
comment on column SH_INFO_PLUGIN.KEYBRDFTPADDR is '������̿ؼ�����Ŀ¼';
comment on column SH_INFO_PLUGIN.KEYBRDPLUGINFLAG is '������̿ؼ���ʶ';
comment on column SH_INFO_PLUGIN.CASHVER is '�ֽ�ʶ�����ؼ��汾';
comment on column SH_INFO_PLUGIN.CASHFTPADDR is '�ֽ�ʶ�����ؼ�����Ŀ¼';
comment on column SH_INFO_PLUGIN.CASHPLUGINFLAG is '�ֽ�ʶ�����ؼ���ʶ';
comment on column SH_INFO_PLUGIN.CARDVER is '�������ؼ��汾';
comment on column SH_INFO_PLUGIN.CARDFTPADDR is '�������ؼ�����Ŀ¼';
comment on column SH_INFO_PLUGIN.CARDPLUGINFLAG is '�������ؼ���ʶ';
comment on column SH_INFO_PLUGIN.MANAGERVER is '�ն˹����ؼ��汾';
comment on column SH_INFO_PLUGIN.MGRFTPADDR is '�ն˹����ؼ�����Ŀ¼';
comment on column SH_INFO_PLUGIN.MGRPLUGINFLAG is '�ն˹����ؼ���ʶ';
comment on column SH_INFO_PLUGIN.SOCKETPORT is '�ն˻�����SOCKET���Ӷ˿�';
comment on column SH_INFO_PLUGIN.MOVECARDVER is '�������ؼ��汾';
comment on column SH_INFO_PLUGIN.MOVECARDADDR is '�������ؼ�����Ŀ¼';
comment on column SH_INFO_PLUGIN.MOVECARDFLAG is '�������ؼ���ʶ';
comment on column SH_INFO_PLUGIN.WRITECARDVER is 'д�����ؼ��汾';
comment on column SH_INFO_PLUGIN.WRITECARDADDR is 'д�����ؼ�����Ŀ¼';
comment on column SH_INFO_PLUGIN.WRITECARDFLAG is 'д�����ؼ���ʶ';
comment on column SH_INFO_PLUGIN.IDCARDVER is '����֤�Ķ����ؼ��汾';
comment on column SH_INFO_PLUGIN.IDCARDADDR is '����֤�Ķ����ؼ�����Ŀ¼';
comment on column SH_INFO_PLUGIN.IDCARDFLAG is '����֤�Ķ����ؼ���ʶ';
comment on column SH_INFO_PLUGIN.UNIONVER is '�����۷ѿؼ��汾';
comment on column SH_INFO_PLUGIN.UNIONFTPADDR is '�����۷ѿؼ�����Ŀ¼';
comment on column SH_INFO_PLUGIN.UNIONPLUGINFLAG is '�����۷ѿؼ���ʶ';
comment on column SH_INFO_PLUGIN.PURSEVER is '�ֻ�Ǯ���ؼ��汾';
comment on column SH_INFO_PLUGIN.PURSEFTPADDR is '�ֻ�Ǯ���ؼ�����Ŀ¼';
comment on column SH_INFO_PLUGIN.PURSEPLUGINFLAG is '�ֻ�Ǯ���ؼ���ʶ';
comment on column SH_INFO_PLUGIN.SELLGOODSVER is '�ۻ����ؼ��汾';
comment on column SH_INFO_PLUGIN.SELLGOODSFTPADDR is '�ۻ����ؼ�����Ŀ¼';
comment on column SH_INFO_PLUGIN.SELLGOODSPLUGINFLAG is '�ۻ����ؼ���ʶ';

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
comment on table SH_INFO_REGIONLIST is '	������Ϣ��';
comment on column SH_INFO_REGIONLIST.REGION is '����';
comment on column SH_INFO_REGIONLIST.REGIONNAME is '��������';
comment on column SH_INFO_REGIONLIST.ORGID is '��������';

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
comment on table SH_LOG_ABATE is '�Żݴ�����־��';
comment on column SH_LOG_ABATE.ABATELOGID is '�ն˽�����ˮ�������ȡ��ʽ���ֻ�Ǯ�������ֶ���sh_purse_log��recoid�ֶι���';
comment on column SH_LOG_ABATE.REGION is '�۳��ص�';
comment on column SH_LOG_ABATE.TERMID is '�ն˻����';
comment on column SH_LOG_ABATE.OPERATORID is '����Ա���';
comment on column SH_LOG_ABATE.BUDDYID is '����ID';
comment on column SH_LOG_ABATE.ABATEID is '�ۿ۲�ƷID';
comment on column SH_LOG_ABATE.PAYTYPE is '��ȡ��ʽ��0���ֻ�Ǯ�� 1�������� 2���ֻ�Ǯ�����˻� 3�����ֶһ� 4���ֽ� 9����ѻ�ȡ';
comment on column SH_LOG_ABATE.ACCOUNTNUM is '�����ȡ��ʽ���ֻ�Ǯ����Ϊ�ֻ�Ǯ�����ţ������ȡ��ʽ����ѻ�ȡ��Ϊ�ֻ�����';
comment on column SH_LOG_ABATE.SELLNUM is '�۳�����';
comment on column SH_LOG_ABATE.PRICE is '����';
comment on column SH_LOG_ABATE.SELLFEE is '�۳����';
comment on column SH_LOG_ABATE.RECDATE is '����ʱ��';
comment on column SH_LOG_ABATE.STATUS is '״̬��11�����׳ɹ���10���ֻ�Ǯ���ۿ�ɹ������û�������֤�ɹ����������ս���ʧ�ܣ�01���ֻ�Ǯ���ۿ�ɹ������û�������֤�ɹ�����ʱ״̬��00����10�����������ʧ�����';
comment on column SH_LOG_ABATE.DESCRIPTION is 'ʧ��ʱ�������¼������Ϣ';

prompt Creating SH_LOG_AUDIT...
create table SH_LOG_AUDIT
(
  ID        VARCHAR2(32) not null,
  TERMID    VARCHAR2(64) not null,
  STARTTIME DATE not null,
  ENDTIME   DATE not null,
  SYSMONEY  NUMBER not null,
  REALMONEY NUMBER not null,
  OPERID1   VARCHAR2(20) not null,
  OPERID2   VARCHAR2(20) not null
)
;
comment on table SH_LOG_AUDIT is '	�ֽ������־';
comment on column SH_LOG_AUDIT.ID is 'ID  ʹ������SEQ_SH_RECOID';
comment on column SH_LOG_AUDIT.TERMID is '�ն˱��';
comment on column SH_LOG_AUDIT.STARTTIME is '��ʼʱ��';
comment on column SH_LOG_AUDIT.ENDTIME is '����ʱ��';
comment on column SH_LOG_AUDIT.SYSMONEY is 'ϵͳ���(��λ����)';
comment on column SH_LOG_AUDIT.REALMONEY is 'ʵ�ʽ��(��λ����)';
comment on column SH_LOG_AUDIT.OPERID1 is '����ԱID1';
comment on column SH_LOG_AUDIT.OPERID2 is '����ԱID2';

prompt Creating SH_LOG_BUSINESS...
create table SH_LOG_BUSINESS
(
  ID        NUMBER,
  CHECKDATE DATE,
  RESULT    NUMBER,
  FILENAME  VARCHAR2(100)
)
;
comment on table SH_LOG_BUSINESS is '	��¼����Ӫ���ṩ���׶����ļ���־';
comment on column SH_LOG_BUSINESS.ID is '����(����SEQ_SH_RECOID)';
comment on column SH_LOG_BUSINESS.CHECKDATE is '��������';
comment on column SH_LOG_BUSINESS.RESULT is '0����ʼ״̬ 1�������ļ�ʧ�� 2�������ļ��ɹ� 3���ϴ�ʧ��  4���ϴ��ɹ�';
comment on column SH_LOG_BUSINESS.FILENAME is '�����ļ���';

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
comment on table SH_LOG_CASHRETURN is '�ֽ��׷�����Ϣ��';
comment on column SH_LOG_CASHRETURN.CASHRETURNID is '�ֽ��׷���Ψһ��ʶ(SEQ_SH_RECOID)';
comment on column SH_LOG_CASHRETURN.TERMID is '�ն�ID';
comment on column SH_LOG_CASHRETURN.SERVNUMBER is '�ֻ���';
comment on column SH_LOG_CASHRETURN.FEE is '���׽��֣�';
comment on column SH_LOG_CASHRETURN.RECDATA is '����ʱ��';
comment on column SH_LOG_CASHRETURN.STATUS is '״̬ 0:�ɹ� 1:ʧ��';
comment on column SH_LOG_CASHRETURN.TOUCHOID is '�Զ��ն����ɵĽӴ���ˮ';
comment on column SH_LOG_CASHRETURN.TERMINALSEQ is 'boss��ˮ��������';

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
comment on table SH_LOG_CHECKBILL is '	��¼BOSS���ѽɷѶ����ļ���־';
comment on column SH_LOG_CHECKBILL.ID is '����(SEQ_SH_RECOID)';
comment on column SH_LOG_CHECKBILL.CHECKDATE is '��������';
comment on column SH_LOG_CHECKBILL.SERVREGION is '�ֻ�������������';
comment on column SH_LOG_CHECKBILL.PAYTYPE is '4���ֽ�֧��  1��������֧��';
comment on column SH_LOG_CHECKBILL.RESULT is '0����ʼ״̬ 1�������ļ�ʧ�� 2�������ļ��ɹ� 3���ϴ�ʧ��  4���ϴ��ɹ�';
comment on column SH_LOG_CHECKBILL.FILENAME is '�����ļ���';

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
comment on table SH_LOG_LOGINERRNUM is '�û������֤�����';
comment on column SH_LOG_LOGINERRNUM.SERVNUMBER is '�ֻ�����';
comment on column SH_LOG_LOGINERRNUM.AUTHTYPE is '��֤��ʽ��SERVICEPWD���������룻RANDOMPWD���������';
comment on column SH_LOG_LOGINERRNUM.ERROR_TIMES is '�������';
comment on column SH_LOG_LOGINERRNUM.FIRST_TIME is '�״���֤ʧ��ʱ��';
comment on column SH_LOG_LOGINERRNUM.LAST_TIME is '���һ����֤ʧ��ʱ��';

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
comment on table SH_LOG_MOBILEPAY is '�ֻ�֧�����˻���ֵ��־��¼';
comment on column SH_LOG_MOBILEPAY.MPAYLOGID is '�ն���ˮ��(SEQ_SH_RECOID)';
comment on column SH_LOG_MOBILEPAY.REGION is 'ӪҵԱ�������';
comment on column SH_LOG_MOBILEPAY.TERMID is '�ն˱��';
comment on column SH_LOG_MOBILEPAY.OPERID is '����Ա���';
comment on column SH_LOG_MOBILEPAY.PAYTYPE is '֧����ʽ��0���ֻ�Ǯ�� 1�������� 2���ֻ�֧�� 3�����ֶһ� 4���ֽ�֧��9����ѻ�ȡ';
comment on column SH_LOG_MOBILEPAY.SERVNUMBER is '��ֵ����';
comment on column SH_LOG_MOBILEPAY.FEE is '��ֵ����λ��';
comment on column SH_LOG_MOBILEPAY.RECDATE is '����ʱ�䣬��ȷ����';
comment on column SH_LOG_MOBILEPAY.STATUS is '���׽����11��Ͷ�ҳɹ����ɷѳɹ���10��Ͷ�ҳɹ������ǽɷ�ʧ�ܣ�01��Ͷ�ҳɹ�����ʱ״̬ ��00����10֮��Ľɷ�ʧ�����';
comment on column SH_LOG_MOBILEPAY.DESCRIPTION is '���׽����������Ҫ��ϵͳ�����쳣���߽���ʧ��ʱ�ã�����Ͷ��ʧ�ܵĴ�����Ϣ���ɷ�ʧ�ܵĴ�����Ϣ';
comment on column SH_LOG_MOBILEPAY.BOSSSEQ is 'BOSS��ˮ��';
comment on column SH_LOG_MOBILEPAY.MPAYSEQ is '�ֻ�֧��ϵͳ��ˮ��';

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
comment on table SH_LOG_MOBILEWALLET is '�ֻ�Ǯ��������־��';
comment on column SH_LOG_MOBILEWALLET.RECOID is '�����ն˽�����ˮ(SEQ_SH_RECOID)';
comment on column SH_LOG_MOBILEWALLET.MONEY is '���׽��(��λ��)';
comment on column SH_LOG_MOBILEWALLET.POSSEQID is 'POS�ն����к�';
comment on column SH_LOG_MOBILEWALLET.POSTERMID is 'POS�ն˺�';
comment on column SH_LOG_MOBILEWALLET.BUSINESSID is '�̻����';
comment on column SH_LOG_MOBILEWALLET.BUSINESSNAME is '�̻����Ƽ�д';
comment on column SH_LOG_MOBILEWALLET.OPERID is '����Ա��';
comment on column SH_LOG_MOBILEWALLET.RECCARDNO is '���׿���';
comment on column SH_LOG_MOBILEWALLET.TERMID is '�ն˺�';
comment on column SH_LOG_MOBILEWALLET.BATCHNUM is 'POS���κ�';
comment on column SH_LOG_MOBILEWALLET.POSOID is 'POS��ˮ��';
comment on column SH_LOG_MOBILEWALLET.RECDATE is '�ն˽�������';
comment on column SH_LOG_MOBILEWALLET.APPAID is 'Ӧ�ñ�ʶ��AID';
comment on column SH_LOG_MOBILEWALLET.SENDCARDID is '��������ʶ';
comment on column SH_LOG_MOBILEWALLET.OFFRECNUM is '�ѻ��������';
comment on column SH_LOG_MOBILEWALLET.KEYEDITION is '��Կ�汾��';
comment on column SH_LOG_MOBILEWALLET.KEYSEQNUM is '��Կ������';
comment on column SH_LOG_MOBILEWALLET.TERMCODE is '�ն˻����';
comment on column SH_LOG_MOBILEWALLET.TERMRECSEQ is '�ն˽������';
comment on column SH_LOG_MOBILEWALLET.TACNUM is 'TAC';
comment on column SH_LOG_MOBILEWALLET.CREATEDATE is '��¼ʱ��';
comment on column SH_LOG_MOBILEWALLET.RECTYPE is '0�������ۻ���1���Żݴ���';

prompt Creating SH_LOG_PRINTBILLNUM...
create table SH_LOG_PRINTBILLNUM
(
  TELNUM        VARCHAR2(32) not null,
  CYCLE         VARCHAR2(32) not null,
  PRINTNUM      NUMBER(6) not null,
  LASTPRINTDATE DATE
)
;
comment on table SH_LOG_PRINTBILLNUM is '	�굥��ӡ��־��';
comment on column SH_LOG_PRINTBILLNUM.TELNUM is '�û�����';
comment on column SH_LOG_PRINTBILLNUM.CYCLE is '����';
comment on column SH_LOG_PRINTBILLNUM.PRINTNUM is '��ӡ����';
comment on column SH_LOG_PRINTBILLNUM.LASTPRINTDATE is '�����ӡʱ��';

prompt Creating SH_LOG_PRINTINVOICE...
create table SH_LOG_PRINTINVOICE
(
  SERVNUMBER VARCHAR2(11) not null,
  CYCLE      VARCHAR2(32) not null,
  RECDATE    DATE not null,
  TERMID     VARCHAR2(32) not null
)
;
comment on table SH_LOG_PRINTINVOICE is '	��Ʊ��ӡ��־��';
comment on column SH_LOG_PRINTINVOICE.SERVNUMBER is '�ֻ�����';
comment on column SH_LOG_PRINTINVOICE.CYCLE is '��Ʊ��Ӧ������';
comment on column SH_LOG_PRINTINVOICE.RECDATE is '��ӡʱ��';
comment on column SH_LOG_PRINTINVOICE.TERMID is '�ն˻�ID';

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
comment on table SH_LOG_TERMSTATUS is '	�ն�״̬��';
comment on column SH_LOG_TERMSTATUS.REGION is '�������';
comment on column SH_LOG_TERMSTATUS.IPADDR is 'IP��ַ';
comment on column SH_LOG_TERMSTATUS.STATUS is '״̬ ״̬ȡֵ���TERMSTATUS';
comment on column SH_LOG_TERMSTATUS.DETAIL is '��ϸ״̬������Ϣ';
comment on column SH_LOG_TERMSTATUS.STATUSDATE is '״̬ʱ��';

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
comment on table SH_LOG_TERMUP is '	�ն�Ӳ����־�ļ�����¼';
comment on column SH_LOG_TERMUP.FILENAME is '�ļ���';
comment on column SH_LOG_TERMUP.REGION is '�ն˻���������';
comment on column SH_LOG_TERMUP.IPADDRESS is '�ն�IP��ַ';
comment on column SH_LOG_TERMUP.CREATEDATE is '��־������ʱ��';
comment on column SH_LOG_TERMUP.LOGFILEPATH is '��־�ļ���ŵ�·��';
comment on column SH_LOG_TERMUP.STATUS is '0��ʼ״̬��1�ɹ����࣬2�쳣';
comment on column SH_LOG_TERMUP.STATUSDATE is '״̬ʱ��';
comment on column SH_LOG_TERMUP.REMARK is '��ע�������쳣ʱ��¼�쳣��Ϣ';
comment on column SH_LOG_TERMUP.ORGID is '��������';

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
comment on table SH_MENU_ITEM is '	�ն�ǰ̨�˵���';
comment on column SH_MENU_ITEM.MENUID is '�˵�ID';
comment on column SH_MENU_ITEM.MENUNAME is '�˵�����';
comment on column SH_MENU_ITEM.PARENTID is '���˵�ID';
comment on column SH_MENU_ITEM.GUIOBJ is 'URL';
comment on column SH_MENU_ITEM.TIPTEXT is '��ʾ��Ϣ';
comment on column SH_MENU_ITEM.SORTORDER is '���';
comment on column SH_MENU_ITEM.CREATEDATE is '����ʱ��';
comment on column SH_MENU_ITEM.STATUS is '״̬';
comment on column SH_MENU_ITEM.STATUSDATE is '״̬ʱ��';
comment on column SH_MENU_ITEM.IMGPATH is '�����ƶ������������ն˿ͻ�����ģ�壬���ֲ˵�����������ͬ��ҳ����ʾ����Ӧ��ͼƬ�����Ǵ�С��������ʽ������һ�������ԣ�ʹ�������ֶ�ά���˵���Ӧ��ͼƬ������˵���Ҫ����ҳ��ʾ������Ҫά������Ϣ���������Ҫ����ҳ��ʾ������Ҫά������Ϣ��';
comment on column SH_MENU_ITEM.REGION is '����';
comment on column SH_MENU_ITEM.BRANDID is '�˵�����Ʒ�ơ�ALL����������Ʒ�ƣ�BrandMzone�������еش��û����ã�BrandSzx�����������û����ã�BrandGotone����ȫ��ͨ�û�����';
comment on column SH_MENU_ITEM.AUTHCODE is '��֤��ʽ��Ŀǰ֧�ֵ���֤��ʽ�з������롢������롢����֤���ֻ�Ǯ����0000��������ҵ��ǰ����Ҫ����������֤��1000��������ҵ��ǰ����з���������֤��1100��������ҵ��ǰ����з�������������������֤';
comment on column SH_MENU_ITEM.MENULEVEL is '�˵�����';
comment on column SH_MENU_ITEM.BUSIDETAIL is 'ҵ�����';
comment on column SH_MENU_ITEM.POSITION is '�˵���ʾλ�á�C��center���м�λ�ã�BR��bottom-right������λ�ã�TR��top-right������λ�á�';
comment on column SH_MENU_ITEM.IMGPATH2 is '˵���ο�IMGPATH�ֶΡ�';

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
comment on table SH_MENU_ITEM_MGR is '	�ն˹���ƽ̨�˵���';
comment on column SH_MENU_ITEM_MGR.MODULEID is 'ģ��ID';
comment on column SH_MENU_ITEM_MGR.MENUID is '�˵�ID';
comment on column SH_MENU_ITEM_MGR.PARENTID is '���˵�ID';
comment on column SH_MENU_ITEM_MGR.IMAGEURL is 'ͼƬ��ַ';
comment on column SH_MENU_ITEM_MGR.MENUNAME is '�˵�����';
comment on column SH_MENU_ITEM_MGR.MENUDESCRIPTION is '�˵�����';
comment on column SH_MENU_ITEM_MGR.MENUURL is '�˵�URL';
comment on column SH_MENU_ITEM_MGR.DISPLAYNO is '�Ƿ���ʾ';
comment on column SH_MENU_ITEM_MGR.LOGLEVEL is 'LOG����';
comment on column SH_MENU_ITEM_MGR.STATUS is '״̬';
comment on column SH_MENU_ITEM_MGR.ENABLEDATE is '����ʱ��';
comment on column SH_MENU_ITEM_MGR.DISABLEDATE is '������ʱ��';
comment on column SH_MENU_ITEM_MGR.REGION is '����';
comment on column SH_MENU_ITEM_MGR.STATUSDATE is '״̬ʱ��';
comment on column SH_MENU_ITEM_MGR.CREATEDATE is '����ʱ��';
comment on column SH_MENU_ITEM_MGR.MENUTYPE is '�˵�����';

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
comment on table SH_ORGANIZATION is '	��֯����������Ϣ�������ڴ洢��֯�����Ļ�����Ϣ��';
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
comment on table SH_ORGANIZATION_CHILD is '	Ӫҵ��֯�ṹ��ϵ��';
create unique index IDX_SH_ORGACHILD on SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID);

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
comment on table SH_PARAM is '	ϵͳ���������';
comment on column SH_PARAM.PARAMID is '����ID';
comment on column SH_PARAM.PARAMNAME is '��������';
comment on column SH_PARAM.DATATYPE is '��������';
comment on column SH_PARAM.CREATEDATE is '��������';
comment on column SH_PARAM.STATUS is '״̬';
comment on column SH_PARAM.STATUSDATE is '״̬����';
comment on column SH_PARAM.DESCRIPTION is '˵��';

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
comment on table SH_PARAM_VALUE is '����ֵ';
comment on column SH_PARAM_VALUE.PARAMID is '��������';
comment on column SH_PARAM_VALUE.PARAMVALUE is '����ֵ';
comment on column SH_PARAM_VALUE.STATUS is '״̬';
comment on column SH_PARAM_VALUE.STATUSDATE is '״̬ʱ��';
comment on column SH_PARAM_VALUE.ADDDATA is '��������';

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
comment on table SH_RANDOM_PASSWORD is '	�漴�����';
comment on column SH_RANDOM_PASSWORD.SERVNUMBER is '�������';
comment on column SH_RANDOM_PASSWORD.RANDOMPASSWORD is '�������';
comment on column SH_RANDOM_PASSWORD.CREATETIME is '����ʱ��';
comment on column SH_RANDOM_PASSWORD.VALIDATETIME is '��֤�뵽��ʱ��';
comment on column SH_RANDOM_PASSWORD.STATUS is '״̬(1��Ч��2�ѳɹ�����-����ʷ����3��ʱ-����ʷ��)';
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
comment on table SH_REC_LOG is '	ҵ����־��';
comment on column SH_REC_LOG.OID is '��־��ˮ��(SEQ_SH_RECOID)';
comment on column SH_REC_LOG.REGION is 'ӪҵԱ�������';
comment on column SH_REC_LOG.TERMID is '�ն˱��';
comment on column SH_REC_LOG.RECFORMNUM is 'ҵ����ˮ�š���reception����������ҵ���������ɷ���ҵ����Ϊ��0����';
comment on column SH_REC_LOG.TOURCHOID is '�ͻ��Ӵ���ˮ�ţ��û���½�󷵻أ�Ĭ��Ϊ0';
comment on column SH_REC_LOG.SERVNUMBER is '���û���ҵ��Ϊ��0��';
comment on column SH_REC_LOG.BUSITYPE is 'ҵ�����͡����ֽ�ɷ�Ϊcharge�����п��ɷ�Ϊchargebycard����Ʊ��ӡ��������ѯ���嵥��ӡ�����͡��������ֵ䶨��';
comment on column SH_REC_LOG.OPERID is '����Ա����';
comment on column SH_REC_LOG.ORGID is 'ҵ��������λ���';
comment on column SH_REC_LOG.RECDATE is 'ҵ������ʱ�䡣YYYY-MM-DD HH24:MI:SS';
comment on column SH_REC_LOG.RECSTATUS is '����״̬,�ɹ���0��ʧ�ܣ�1';
comment on column SH_REC_LOG.RECSTATUSDESC is '����״̬����';
comment on column SH_REC_LOG.RECFEE is 'ҵ���������ã��޷��õ�ҵ��Ϊ0';
comment on column SH_REC_LOG.DISCOUNT is 'ҵ��������';
comment on column SH_REC_LOG.BRANDID is '�û�����Ʒ��';
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
comment on table SH_REC_LOG_MGR is '	��̨������־';
comment on column SH_REC_LOG_MGR.MGRLOGID is '��־ID(����:SEQ_SH_RECOID)';
comment on column SH_REC_LOG_MGR.OPERID is '����ԱID';
comment on column SH_REC_LOG_MGR.REGION is '����';
comment on column SH_REC_LOG_MGR.ORGID is '��λ';
comment on column SH_REC_LOG_MGR.BUSITYPE is '��������';
comment on column SH_REC_LOG_MGR.RECDATE is '����ʱ��';
comment on column SH_REC_LOG_MGR.STATUS is '״̬ 1���ɹ�   0��ʧ��';
comment on column SH_REC_LOG_MGR.NOTES is '��ע';

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
comment on table SH_ROLE_INFO is '	��ɫ��Ϣ��';
comment on column SH_ROLE_INFO.ROLEID is '��ɫID(���У�SEQ_SH_RECOID)';
comment on column SH_ROLE_INFO.ROLENAME is '��ɫ����';
comment on column SH_ROLE_INFO.CREATEDATE is '����ʱ��';
comment on column SH_ROLE_INFO.CREATEOPERATOR is '������';
comment on column SH_ROLE_INFO.STATUS is '״̬��1���ã�0����';
comment on column SH_ROLE_INFO.NOTES is '״̬ʱ��';

prompt Creating SH_ROLE_MENU...
create table SH_ROLE_MENU
(
  ROLEID     VARCHAR2(32) not null,
  MENUID     VARCHAR2(32) not null,
  STATUS     NUMBER(1) not null,
  STATUSDATE DATE not null
)
;
comment on table SH_ROLE_MENU is '	��ɫ�˵���ϵ��';
comment on column SH_ROLE_MENU.ROLEID is '��ɫID';
comment on column SH_ROLE_MENU.MENUID is '�˵�ID';
comment on column SH_ROLE_MENU.STATUS is '״̬��1���ã�0����';
comment on column SH_ROLE_MENU.STATUSDATE is '״̬ʱ��';

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
comment on table SH_SELLGOODS_LOG is '	�ۻ���ʷ��';
comment on column SH_SELLGOODS_LOG.SELLGOODSLOGID is '�ն���ˮ(����SEQ_SH_RECOID)';
comment on column SH_SELLGOODS_LOG.REGION is '�۳��ص�';
comment on column SH_SELLGOODS_LOG.TERMID is '�ն˻����';
comment on column SH_SELLGOODS_LOG.OPERID is '����Ա���';
comment on column SH_SELLGOODS_LOG.BOOTHCODE is '������';
comment on column SH_SELLGOODS_LOG.MERCHANDISEID is '��ƷID';
comment on column SH_SELLGOODS_LOG.PAYTYPE is '֧����ʽ 0���ֻ�Ǯ��֧�� 1��������֧�� 2���ֻ�֧�� 3�����ֶһ�';
comment on column SH_SELLGOODS_LOG.CUSTOMER is '��������Ϣ ������ֻ�Ǯ��֧����Ϊ�ֻ�Ǯ�����ţ�����ǻ��ֶһ���ʽ��Ϊ�ֻ�����';
comment on column SH_SELLGOODS_LOG.SELLNUM is '�۳�����';
comment on column SH_SELLGOODS_LOG.PRICE is '���� ������ֻ�Ǯ��֧����Ϊ��Ʒ�ۼۣ���λ�֣�����ǻ��ֶһ���ʽ��Ϊ�������';
comment on column SH_SELLGOODS_LOG.SELLFEE is '�ܽ�� ������ֻ�Ǯ��֧����Ϊ���� * ������������λ�֣�����ǻ��ֶһ���ʽ��Ϊ����ȫ������';
comment on column SH_SELLGOODS_LOG.RECDATE is '����ʱ�䣨�룩';
comment on column SH_SELLGOODS_LOG.STATUS is '���׽����11�����׳ɹ���10���ֻ�Ǯ���ۿ�ɹ����߻��ֿۼ��ɹ����������ս���ʧ�ܣ�����δ���������Ϳۿ��������쳣�������01�����ֿۼ��ɹ������ֻ�Ǯ���ۿ�ɹ�����ʱ״̬��00����10֮�����������ʧ�����';
comment on column SH_SELLGOODS_LOG.DESCRIPTION is '���׽������';
comment on column SH_SELLGOODS_LOG.SHIPMENTNUM is 'ʵ�ʳ�������';
comment on column SH_SELLGOODS_LOG.SCORETOPRICE is '���ֶ�Ӧ���';
comment on column SH_SELLGOODS_LOG.UNIONPAYUSER is '�̻����';
comment on column SH_SELLGOODS_LOG.UNIONPAYCODE is 'POS�����';
comment on column SH_SELLGOODS_LOG.BUSITYPE is '��������';
comment on column SH_SELLGOODS_LOG.CARDNUMBER is '���п���';
comment on column SH_SELLGOODS_LOG.BATCHNUM is '���κ�';
comment on column SH_SELLGOODS_LOG.AUTHORIZATIONCODE is '��Ȩ��';
comment on column SH_SELLGOODS_LOG.BUSINESSREFERENCENUM is '���ײο���';
comment on column SH_SELLGOODS_LOG.UNIONPAYTIME is '�����ۿ�����ʱ��';
comment on column SH_SELLGOODS_LOG.VOUCHERNUM is 'ƾ֤��';
comment on column SH_SELLGOODS_LOG.UNIONPAYFEE is '�����ۿ����λ���֣�';

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
  DESCRIBE     VARCHAR2(200)
)
;
comment on table SH_TERM_CONFIG is '	�ն���Ϣ��';
comment on column SH_TERM_CONFIG.TERMID is '�ն˱��';
comment on column SH_TERM_CONFIG.TERMNAME is '�ն�����';
comment on column SH_TERM_CONFIG.IPADDR is 'IP��ַ';
comment on column SH_TERM_CONFIG.MAC is 'Mac��ַ';
comment on column SH_TERM_CONFIG.OPERID is '����Ա����';
comment on column SH_TERM_CONFIG.PLANSIGNTIME is '�ƻ�ǩ��ʱ��HH24MISS';
comment on column SH_TERM_CONFIG.REALSIGNTIME is 'ʵ��ǩ��ʱ��YYYY-MM-DD HH24:MI:SS';
comment on column SH_TERM_CONFIG.UNIONUSERID is '�����̻���';
comment on column SH_TERM_CONFIG.UNIONPAYCODE is '����Ϊˢ�����ն˷����Ψһ���';
comment on column SH_TERM_CONFIG.BROWSERTYPE is 'ie;firefox';
comment on column SH_TERM_CONFIG.TERMSPECIAL is '�ն�����,��Ϊ12λ�����ַ���.��1λ�Ƿ�֧�ִ�ӡƱ��,��2λ�Ƿ�֧�ִ�ӡ��Ʊ,��3λ�Ƿ���м��ܼ���,��4λ�Ƿ�֧���ֽ�ɷ�,��5λ�Ƿ�֧�����п��ɷ�,��6λ�Ƿ���й����ؼ�,��7λ�Ƿ���з����ؼ�,��8λ�Ƿ����д���ؼ�,��9λ�Ƿ�֧�ֶ�ȡ�ڶ���ʡ��֤��Ϣ;��10λ����OCX;��11λ�Ƿ�֧���ֻ�Ǯ��;��12λ�Ƿ���������ۻ���1��ʾ֧�֣�0��ʾ��֧�֡�����111101111��ʾ�������п��ɷѲ�֧�����඼֧��.';
comment on column SH_TERM_CONFIG.PROVIDERCODE is '���̱���';
comment on column SH_TERM_CONFIG.ORGID is 'Ӫҵ�㵥λ����';
comment on column SH_TERM_CONFIG.SOCKADDR is '�����ն˻�������������ƽ̨��ip�Ͷ˿ڣ�ip,port';
comment on column SH_TERM_CONFIG.CREATEDATE is '����ʱ��';
comment on column SH_TERM_CONFIG.STATUS is '״̬ 1���ã�0����';
comment on column SH_TERM_CONFIG.STATUSDATE is '״̬ʱ��';
comment on column SH_TERM_CONFIG.REGION is '����';
comment on column SH_TERM_CONFIG.ORGNAME is 'Ӫҵ�㵥λ����';
comment on column SH_TERM_CONFIG.MACHINEMODEL is '�����ͺ�';
comment on column SH_TERM_CONFIG.TRMCODE is '�豸����(������ҵ�ɷ�ʱ�õ�)';
comment on column SH_TERM_CONFIG.DESCRIBE is '��ע����д�����ַ��';

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
comment on table SH_TERM_GROUP is '	�ն�������ն���Ϣ��';
comment on column SH_TERM_GROUP.TERMID is '�ն�ID';
comment on column SH_TERM_GROUP.TERMGRPID is '�ն�����';
comment on column SH_TERM_GROUP.CREATEDATE is '����ʱ��';
comment on column SH_TERM_GROUP.STATUS is '״̬��1���ã�0����';
comment on column SH_TERM_GROUP.STATUSDATE is '״̬ʱ��';

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
comment on table SH_TERM_MODEL is '�ն��ͺű�';
comment on column SH_TERM_MODEL.TERMMODELID is '�ͺ�ID(���У�SEQ_SH_RECOID)';
comment on column SH_TERM_MODEL.TERMMODEL is '�ͺ�';
comment on column SH_TERM_MODEL.MANUFACTURERID is '��������';
comment on column SH_TERM_MODEL.DESCRIBE is '����';
comment on column SH_TERM_MODEL.STATUS is '״̬��1������;0��ͣ��';
comment on column SH_TERM_MODEL.STATUSDATE is '״̬����';

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
comment on table SH_UNIONPAY_LOG is '	�����ɷѽ��׶����м��';
comment on column SH_UNIONPAY_LOG.BANK is '������';
comment on column SH_UNIONPAY_LOG.UNIONSEQ is '����������ˮ��';
comment on column SH_UNIONPAY_LOG.BUSITYPE is '��������';
comment on column SH_UNIONPAY_LOG.TERMINALID is '�ն˱��';
comment on column SH_UNIONPAY_LOG.FEE is '���';
comment on column SH_UNIONPAY_LOG.SHANGHUSEQ is '�̻���ˮ��';
comment on column SH_UNIONPAY_LOG.RECDATE is '����ʱ��';
comment on column SH_UNIONPAY_LOG.CARDNO is '����';
comment on column SH_UNIONPAY_LOG.FLAG is '������ʶ';



----------------------------------------------------------


create or replace view v_sh_charge_log as
select a.chargelogoid,
       a.region,
       a.termid,
       a.operid,
       a.servnumber,
       a.paytype,
       a.fee,
       a.unionpayuser,
       a.unionpaycode,
       a.busitype,
       a.unionpaytime,
       a.unionpayfee,
       to_char(a.recdate, 'YYYY-MM-DD') recdatedd,
       to_char(a.recdate, 'YYYY-MM') recdatemm,
       a.status,
       a.accepttype,
       a.orgid
  from sh_charge_log a
 where a.status = 11
 ;

create or replace view v_sh_log_mobilepay as
select a.mpaylogid,
       a.region,
       a.termid,
       a.paytype,
       a.servnumber,
       a.fee,
       to_char(a.recdate, 'YYYY-MM-DD') recdatedd,
       to_char(a.recdate, 'YYYY-MM') recdatemm,
       a.status,
       a.bossseq,
       a.mpayseq,
       a.orgid
  from sh_log_mobilepay a
 where a.status=11
 ;

create or replace view v_sh_log_mobilewallet as
select a.termid,
       a.region,
       a.reccardno,
       to_char(a.createdate, 'YYYY-MM-DD') createdatedd,
       to_char(a.createdate, 'YYYY-MM') createdatemm,
       a.money,
       a.orgid
  from SH_LOG_MOBILEWALLET a
;


CREATE OR REPLACE VIEW V_SH_NGR_MENU AS
SELECT
    MENU.MENUID             FUNCID,
    MENU.MENUNAME           FUNCNAME,
    DECODE(MENU.PARENTID, '00', '0', PARENTID)           PARENTID,
    MENU.MENUURL            GUIOBJECT,
    ''                      RLTGUIOBJID,
    MENU.AppendInfo         ADDEDINFO,
    MENU.MENUDESCRIPTION    TIPTEXT,
    MENU.MODULEID           SUBSYSTEMID,
    'WEB'           GUITYPE,
    ''                      RECDEFID,
    MENU.DISPLAYNO          SORTORDER,
    ''                      CREATEDATE,
    nvl(status,1)           STATUS,
    ''                      STATUSDATE,
    ''                      HELPLINK,
    MENU.tabType            TABTYPE,
    MENU.LOGLEVEL           LOG_LEVEL
FROM SH_MENU_ITEM_MGR MENU
;

















------------------------------------------
set feedback on
set define on
prompt Done.