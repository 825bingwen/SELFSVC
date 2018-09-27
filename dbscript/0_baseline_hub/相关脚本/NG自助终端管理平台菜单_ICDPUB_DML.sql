delete t_pub_module where moduleid = 'SH_SelfHelp';

insert into t_pub_module (MODULEID, PARENTID, MODULENAME, AUTHTABLE, DATASOURCEID, HASLOAD, DISPLAYNO, MATCHURL)
values ('SH_SelfHelp', '0', 'BOSS_自助终端管理平台', 'T_UCP_AUTHELEMENT', '', '1', '82', 'boss');

delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_AlarmBindMgr';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_AlarmInfoMgr';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_CallChargeStat';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_ChargeLog';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_CheckLog';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_DictMgr';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_InvoicePrintLog';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_LogFileInfoMgr';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_MachineModel';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_ManagerLog';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_ManufacturerInfo';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_MediaPlay';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_PaymentLog';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_RemoteControl';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_ScreenProtectMgr';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_SelfHelp';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_StatisticsMgr';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_SysData';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_TermBaseInfo';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_TermCashAudit';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_TermControl';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_TermControlMgr';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_TermGroupScreenConfigMgr';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_TermInfo';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_TermPluginInfoMgr';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_TermQuery';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_TermRecLogQuery';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_TermRightMgr';
delete t_ucp_menu where moduleid= 'SH_SelfHelp' and menuid = 'SH_TermRuleMgr';

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_AlarmBindMgr', 'SH_TermControlMgr', '', '告警处理人设置', '告警处理人设置', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FalarmBind%2FinitQryPage.action', 3, '-1', 'SH_AlarmBindMgr', '', '-1', '', '1', '', '', '', '', 'WEB', '告警处理人设置', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_AlarmInfoMgr', 'SH_TermControlMgr', '', '告警信息处理', '告警信息处理', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FalarmInfo%2FinitQryPage.action', 5, '-1', 'SH_AlarmInfoMgr', '', '-1', '', '1', '', '', '', '', 'WEB', '告警信息处理', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_CallChargeStat', 'SH_StatisticsMgr', '', '话费充值统计', '话费充值统计', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FstatisticsMgr%2FinitStatMgrPage.action?statName=callChgStat', 0, '-1', 'SH_CallChargeStat', '', '-1', '', '1', '', '', '', '', 'WEB', '话费充值统计', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_ChargeLog', 'SH_PaymentLog', '', '缴纳日志查询', '缴纳日志查询', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FuserPayLogQry%2FchargeLogPage.action', 1, '-1', 'SH_ChargeLog', '', '-1', '', '1', '', '', '', '', 'WEB', '缴纳日志查询', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_CheckLog', 'SH_TermControlMgr', '', '终端机日志上传检查', '终端机日志上传检查', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FtermLogCheck%2FinitQryPage.action', 6, '-1', 'SH_CheckLog', '', '-1', '', '1', '', '', '', '', 'WEB', '终端机日志上传检查', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_DictMgr', 'SH_SysData', '', '字典数据管理', '字典数据管理', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2Fdictitem%2FinitQryPage.action', 3, '-1', 'SH_DictMgr', '', '-1', '', '1', '', '', '', '', 'WEB', '字典数据管理', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_InvoicePrintLog', 'SH_PaymentLog', '', '发票打印日志查询', '发票打印日志查询', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FuserPayLogQry%2FinvoicePrintLogPage.action', 6, '-1', 'SH_InvoicePrintLog', '', '-1', '', '1', '', '', '', '', 'WEB', '发票打印日志查询', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_LogFileInfoMgr', 'SH_TermQuery', '', '终端日志文件查询', '日志文件查询', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FterminalQryStat%2FtermLogQryPage.action', 3, '-1', 'SH_LogFileInfoMgr', '', '-1', '', '1', '', '', '', '', 'WEB', '日志文件查询', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_MachineModel', 'SH_TermInfo', '', '终端型号资料管理', '终端型号资料管理', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FtermModel%2FinitQryPage.action', 2, '-1', 'SH_MachineModel', '', '-1', '', '1', '', '', '', '', 'WEB', '终端型号资料管理', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_ManagerLog', 'SH_TermQuery', '', '维护人员日志查询', '维护人员日志查询', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FterminalQryStat%2FmanageLogQuery.action', 2, '-1', 'SH_ManagerLog', '', '-1', '', '1', '', '', '', '', 'WEB', '管理日志查询', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_ManufacturerInfo', 'SH_TermInfo', '', '终端厂商资料管理', '对终端设备厂商的资料进行登记、变更等操作', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2Fterminfo%2FinitManufacturerInfo.action', 1, '-1', 'SH_ManufacturerInfo', '', '-1', '', '1', '', '', '', '', 'WEB', '终端厂商资料管理', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_MediaPlay', 'SH_SelfHelp', '', '屏保广告管理', '屏保广告管理', '', 4, '-1', 'SH_MediaPlay', '', '-1', '', '1', '', '', '', '', 'WEB', '屏保广告管理', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_PaymentLog', 'SH_SelfHelp', '', '支付日志查询', '支付日志查询', '', 8, '-1', 'SH_PaymentLog', '', '-1', '', '1', '', '', '', '', 'WEB', '支付日志查询', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_RemoteControl', 'SH_TermControlMgr', '', '远程操控', '远程对终端机进行关机、重启、锁定、解锁操作', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FremoteControl%2FinitPage.action', 1, '-1', 'SH_RemoteControl', '', '-1', '', '1', '', '', '', '', 'WEB', '远程操控', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_ScreenProtectMgr', 'SH_MediaPlay', '', '屏保资源维护', '屏保资源维护', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FscreenAdvMgr%2FscreenProtectPage.action', 1, '-1', 'SH_ScreenProtectMgr', '', '-1', '', '1', '', '', '', '', 'WEB', '屏保资源维护', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_SelfHelp', '00', '', '自助终端管理', '自助终端管理', '', 0, '-1', 'SH_SelfHelp', '', '-1', '', '1', '', '', '', '', 'WEB', '自助终端管理', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_StatisticsMgr', 'SH_SelfHelp', '', '统计报表管理', '统计报表管理', '', 9, '-1', 'SH_StatisticsMgr', '', '-1', '', '1', '', '', '', '', 'WEB', '统计报表管理', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_SysData', 'SH_SelfHelp', '', '系统数据管理', '系统数据管理', '', 1, '-1', 'SH_SysData', '', '-1', '', '1', '', '', '', '', 'WEB', '系统数据管理', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_TermBaseInfo', 'SH_TermInfo', '', '终端基本资料管理', '对终端设备整体所具有的属性进行登记、变更等操作', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FtermConfig%2FinitQryPage.action', 3, '-1', 'SH_TermBaseInfo', '', '-1', '', '1', '', '', '', '', 'WEB', '终端基本资料管理', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_TermCashAudit', 'SH_PaymentLog', '', '现金稽核日志查询', '现金稽核日志查询', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FuserPayLogQry%2FinitCashAuditPage.action', 5, '-1', 'SH_TermCashAudit', '', '-1', '', '1', '', '', '', '', 'WEB', '现金稽核日志查询', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_TermControl', 'SH_TermControlMgr', '', '终端监控', '终端监控', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FtermAlarm%2FinitQryPage.action', 4, '-1', 'SH_TermControl', '', '-1', '', '1', '', '', '', '', 'WEB', '终端监控', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_TermControlMgr', 'SH_SelfHelp', '', '监控管理', '监控管理', '', 7, '-1', 'SH_TermControlMgr', '', '-1', '', '1', '', '', '', '', 'WEB', '监控管理', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_TermGroupScreenConfigMgr', 'SH_MediaPlay', '', '终端组屏保资源配置', '终端组屏保资源配置', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FscreenAdvMgr%2FtermGrpscreenConfigPage.action', 2, '-1', 'SH_TermGroupScreenConfigMgr', '', '-1', '', '1', '', '', '', '', 'WEB', '终端组屏保资源配置', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_TermInfo', 'SH_SelfHelp', '', '终端资料管理', '终端资料是指与终端设备相关的物理属性和抽象信息，主要包括厂商资料、基本资料、控件信息、归属信息等。', '', 2, '-1', 'SH_TermInfo', '', '-1', '', '1', '', '', '', '', 'WEB', '终端资料管理', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_TermPluginInfoMgr', 'SH_TermInfo', '', '终端控件信息管理', '终端控件信息管理', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2Fterminfo%2FinitPluginInfo.action', 4, '-1', 'SH_TermPluginInfoMgr', '', '-1', '', '1', '', '', '', '', 'WEB', '终端控件信息管理', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_TermQuery', 'SH_SelfHelp', '', '终端查询统计', '终端查询统计', '', 3, '-1', 'SH_TermQuery', '', '-1', '', '1', '', '', '', '', 'WEB', '终端查询统计', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_TermRecLogQuery', 'SH_TermQuery', '', '用户日志查询', '用户日志查询', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FterminalQryStat%2FuserLogQuery.action', 1, '-1', 'SH_TermRecLogQuery', '', '-1', '', '1', '', '', '', '', 'WEB', '终端日志查询', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_TermRightMgr', 'SH_TermInfo', '', '终端业务状态管理', '按终端组查询和管理终端上可使用的业务的功能', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FtermGroup%2FinitQryPage.action', 5, '-1', 'SH_TermRightMgr', '', '-1', '', '1', '', '', '', '', 'WEB', '终端业务状态管理', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');

insert into t_ucp_menu (MODULEID, MENUID, PARENTID, IMAGEURL, MENUNAME, MENUDESCRIPTION, MENUURL, DISPLAYNO, OPENMODULE, AUTHID, FASTKEY, ISHIDDEN, SERVICEID, ISDISPLAY, CALLBACK, ISCALLBARITEM, DATASOURCEID, INTERFACEOBJECT, MENUTYPE, APPENDINFO, HELPLINK, TABTYPE, MUTEXFLAG, DISPLAYTYPE, DYFIELD1, DYFIELD2, DYFIELD3, DYFIELD4, DYFIELD5, CLICKTOTAL, LOGLEVEL, STATUS, ENABLEDATE, DISABLEDATE, REGION, STATUSDATE, CREATEDATE, EMERGENCYID, SYSTEMID)
values ('SH_SelfHelp', 'SH_TermRuleMgr', 'SH_TermControlMgr', '', '告警规则设置', '告警规则设置', '/custcare/crmSession.do?targetUrl=http%3A%2F%2F171.0.0.4%3A7003%2Fselfsvcmgr%2FalarmRule%2FinitQryPage.action', 2, '-1', 'SH_TermRuleMgr', '', '-1', '', '1', '', '', '', '', 'WEB', '告警规则设置', '', '32', 0, '', '', '', '', '', '', 0, 1, 1, null, null, null, null, null, '1111', '');



delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_AlarmBindMgr';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_AlarmInfoMgr';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_CallChargeStat';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_ChargeLog';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_CheckLog';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_DictMgr';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_InvoicePrintLog';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_LogFileInfoMgr';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_MachineModel';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_ManagerLog';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_ManufacturerInfo';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_MediaPlay';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_PaymentLog';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_RemoteControl';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_ScreenProtectMgr';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_SelfHelp';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_StatisticsMgr';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_SysData';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_TermBaseInfo';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_TermCashAudit';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_TermControl';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_TermControlMgr';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_TermGroupScreenConfigMgr';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_TermInfo';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_TermPluginInfoMgr';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_TermQuery';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_TermRecLogQuery';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_TermRightMgr';
delete t_ucp_authelement where moduleid= 'SH_SelfHelp' and authid = 'SH_TermRuleMgr';

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_TermRuleMgr', 'SH_SelfHelp', 'SH_TermRuleMgr', 'SH_TermControlMgr', '告警规则设置', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_TermRightMgr', 'SH_SelfHelp', 'SH_TermRightMgr', 'SH_TermInfo', '终端业务状态管理', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_TermRecLogQuery', 'SH_SelfHelp', 'SH_TermRecLogQuery', 'SH_TermQuery', '用户日志查询', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:29', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:29', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_TermQuery', 'SH_SelfHelp', 'SH_TermQuery', 'SH_SelfHelp', '终端查询统计', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:29', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:29', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_TermPluginInfoMgr', 'SH_SelfHelp', 'SH_TermPluginInfoMgr', 'SH_TermInfo', '终端控件信息管理', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:29', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:29', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_TermInfo', 'SH_SelfHelp', 'SH_TermInfo', 'SH_SelfHelp', '终端资料管理', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_TermGroupScreenConfigMgr', 'SH_SelfHelp', 'SH_TermGroupScreenConfigMgr', 'SH_MediaPlay', '终端组屏保资源配置', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_TermControlMgr', 'SH_SelfHelp', 'SH_TermControlMgr', 'SH_SelfHelp', '监控管理', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_TermControl', 'SH_SelfHelp', 'SH_TermControl', 'SH_TermControlMgr', '终端监控', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_TermCashAudit', 'SH_SelfHelp', 'SH_TermCashAudit', 'SH_PaymentLog', '现金稽核日志查询', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_TermBaseInfo', 'SH_SelfHelp', 'SH_TermBaseInfo', 'SH_TermInfo', '终端基本资料管理', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_SysData', 'SH_SelfHelp', 'SH_SysData', 'SH_SelfHelp', '系统数据管理', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_StatisticsMgr', 'SH_SelfHelp', 'SH_StatisticsMgr', 'SH_SelfHelp', '统计报表管理', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_SelfHelp', 'SH_SelfHelp', 'SH_SelfHelp', '-1', '自助终端管理', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_ScreenProtectMgr', 'SH_SelfHelp', 'SH_ScreenProtectMgr', 'SH_MediaPlay', '屏保资源维护', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:29', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:29', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_RemoteControl', 'SH_SelfHelp', 'SH_RemoteControl', 'SH_TermControlMgr', '远程操控', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_PaymentLog', 'SH_SelfHelp', 'SH_PaymentLog', 'SH_SelfHelp', '支付日志查询', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_MediaPlay', 'SH_SelfHelp', 'SH_MediaPlay', 'SH_SelfHelp', '屏保广告管理', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_ManufacturerInfo', 'SH_SelfHelp', 'SH_ManufacturerInfo', 'SH_TermInfo', '终端厂商资料管理', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_ManagerLog', 'SH_SelfHelp', 'SH_ManagerLog', 'SH_TermQuery', '维护人员日志查询', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_MachineModel', 'SH_SelfHelp', 'SH_MachineModel', 'SH_TermInfo', '终端型号资料管理', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_LogFileInfoMgr', 'SH_SelfHelp', 'SH_LogFileInfoMgr', 'SH_TermQuery', '终端日志文件查询', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_InvoicePrintLog', 'SH_SelfHelp', 'SH_InvoicePrintLog', 'SH_PaymentLog', '发票打印日志查询', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_DictMgr', 'SH_SelfHelp', 'SH_DictMgr', 'SH_SysData', '字典数据管理', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_CheckLog', 'SH_SelfHelp', 'SH_CheckLog', 'SH_TermControlMgr', '终端机日志上传检查', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_ChargeLog', 'SH_SelfHelp', 'SH_ChargeLog', 'SH_PaymentLog', '缴纳日志查询', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_CallChargeStat', 'SH_SelfHelp', 'SH_CallChargeStat', 'SH_StatisticsMgr', '话费充值统计', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:28', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_AlarmInfoMgr', 'SH_SelfHelp', 'SH_AlarmInfoMgr', 'SH_TermControlMgr', '告警信息处理', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:27', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);

insert into t_ucp_authelement (AUTHID, MODULEID, AUTHCODE, SUPERCODE, AUTHNAME, MENUFLAG, DESCRIPTION, HOSTEDCCID, AUTHTYPE, REGION, CREATOR, CREATEORG, ISPUBLIC, CREATEDATE, STATUS, STATUSDATE, NOTES, RIGHTGROUP, CREATETYPE, DISPLAYNO)
values ('SH_AlarmBindMgr', 'SH_SelfHelp', 'SH_AlarmBindMgr', 'SH_TermControlMgr', '告警处理人设置', '1', '', '', 1, '', '', '', 1, to_date('04-07-2011 14:58:29', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-07-2011 14:58:29', 'dd-mm-yyyy hh24:mi:ss'), '', 'SH_SelfHelp', '', null);


--增加可赋权限工号
delete from t_ucp_putauth where moduleid='SH_SelfHelp';

declare 
begin 
for cur_roleauth in (select distinct(staffid) from t_ucp_staffrole where roleid in(
'localsysadmin_711',
'localsysadmin_718',
'localadmin_713',
'localsysadmin_714',
'localadmin_728',
'localadmin_724',
'dishixitonggly_716',
'localadmin_719',
'localsysadmin_722',
'localadmin_270',
'localsysadmin_715',
'system_710',
'xtgl_712',
'localadmin_717',
'province_manager_300')) loop 
insert into t_ucp_putauth(staffid,moduleid,authid) select cur_roleauth.staffid,t.moduleid,t.menuid from t_ucp_menu t where t.moduleid = 'SH_SelfHelp';
end loop;
end;
/

--添加各地市管理员角色 可用权限
delete from t_ucp_roleauth where moduleid='SH_SelfHelp';
declare 
begin 
for cur_roleid in (select distinct(roleid) from t_ucp_staffrole where roleid in (
'billsysadmin',
'localsysadmin_711',
'localsysadmin_718',
'localadmin_713',
'localsysadmin_714',
'localadmin_728',
'localadmin_724',
'dishixitonggly_716',
'localadmin_719',
'localsysadmin_722',
'localadmin_270',
'localsysadmin_715',
'system_710',
'xtgl_712',
'localadmin_717',
'province_manager_300')) loop 
  insert into t_ucp_roleauth(roleid,moduleid,authid,status,statusdate,createdate) select cur_roleid.roleid,t.moduleid,t.menuid,'1',sysdate,sysdate from t_ucp_menu t where t.moduleid = 'SH_SelfHelp';
end loop;
end;