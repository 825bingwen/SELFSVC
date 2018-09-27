--==============================================================
-- 本脚本包含以下内容

-- 1.创建用户下所有视图

-- 本脚本以新建用户执行
-- 
--==============================================================
set feedback off
set define off
-------------------------------------------------------
prompt CREATE OR REPLACE V_SH_NGR_MENU...

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
FROM SH_MENU_ITEM_MGR MENU;
/
prompt CREATE OR REPLACE v_sh_log_mobilewallet...
create or replace view v_sh_log_mobilewallet as
select a.termid,
       a.region,
       a.reccardno,
       to_char(a.createdate, 'YYYY-MM-DD') createdatedd,
       to_char(a.createdate, 'YYYY-MM') createdatemm,
       a.money,
       a.orgid
  from SH_LOG_MOBILEWALLET a;
/
prompt CREATE OR REPLACE v_sh_log_mobilepay...
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
 where a.status=11;
/
prompt CREATE OR REPLACE v_sh_charge_log...
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
 where a.status = 11;
/



-------------------------------------------------------
set feedback on
set define on
prompt Done.
