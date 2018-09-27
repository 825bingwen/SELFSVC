--==============================================================
-- 注意：本脚本仅供开发测试使用包含以下内容（用户ngsh）
-- 在生产环境上： regionlis、操作员表、组织信息表、组织信息CHILD表 需要建立与tbcs的同义词
-- 请参考脚本“NG自助终端同义词_NGSH_DDL.sql”

-- 1.删除用户下所有数据库表数据(delete)
-- 2.建初始化表数据

-- 本脚本以ngsh用户执行
-- 
--==============================================================
set feedback off
set define off
-------------------------------------------------------------------
prompt Disabling triggers for SH_INFO_REGIONLIST...
alter table SH_INFO_REGIONLIST disable all triggers;
prompt Disabling triggers for SH_INFO_OPERATOR...
alter table SH_INFO_OPERATOR disable all triggers;
prompt Disabling triggers for SH_ORGANIZATION...
alter table SH_ORGANIZATION disable all triggers;
prompt Disabling triggers for SH_ORGANIZATION_CHILD...
alter table SH_ORGANIZATION_CHILD disable all triggers;

prompt Deleting SH_INFO_REGIONLIST...
delete from SH_INFO_REGIONLIST;
commit;
prompt Deleting SH_INFO_OPERATOR...
delete from SH_INFO_OPERATOR
commit;
prompt Deleting SH_ORGANIZATION...
delete from SH_ORGANIZATION
commit;
prompt Deleting SH_ORGANIZATION_CHILD...
delete from SH_ORGANIZATION_CHILD
commit;

prompt Loading SH_INFO_REGIONLIST...
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('270', 1, '武汉', 'HB.WH', 'h', 'B', null, null, 'A', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('714', 1, '黄石', 'HB.HS', 's', 'A', null, null, 'A', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('711', 1, '鄂州', 'HB.EZ', 'z', 'A', null, null, 'A', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('718', 1, '恩施', 'HB.ES', 'e', 'A', null, null, 'A', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('728', 1, '江汉', 'HB.JH', 'j', 'A', null, null, 'A', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('715', 1, '咸宁', 'HB.XN', 'x', 'A', null, null, 'A', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('722', 1, '随州', 'HB.SZ', 'z', 'A', null, null, 'A', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('717', 1, '宜昌', 'HB.YC', 'c', 'A', null, null, 'B', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('719', 1, '十堰', 'HB.SY', 'y', 'A', null, null, 'B', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('710', 1, '襄樊', 'HB.XF', 'f', 'A', null, null, 'B', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('716', 1, '荆州', 'HB.JZ', 'i', 'A', null, null, 'B', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('724', 1, '荆门', 'HB.JM', 'm', 'A', null, null, 'B', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('713', 1, '黄冈', 'HB.HG', 'a', 'A', null, null, 'B', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('712', 1, '孝感', 'HB.XG', 'g', 'A', null, null, 'B', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('999', 1, '湖北', 'HB', '0', 'A', null, null, 'A', null);
commit;
prompt 15 records loaded
prompt Loading SH_INFO_OPERATOR...
insert into SH_INFO_OPERATOR (OPERID, REGION, OPERNAME, PASSWORD, PASSCHGDATE, OPERGROUP, OPERTYPE, OPERLEVEL, ISMANAGER, CONTACTPHONE, ORGID, ISRESTRICT, STARTTIME, ENDTIME, ENABLEGPRS, GPRSSTARTTIME, GPRSENDTIME, ISCHKMAC, MAC, NOTES, CREATEDATE, STATUS, STATUSDATE, RELE_STAFF_ID, START_USING_TIME, END_USING_TIME, LOGINTYPE, AREAID, ROLEID)
values ('admin', 711, '系统管理员admin', '96e79218965eb72c92a549dd5a330112', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'OPRT31', null, 1, '13908684713', 'HB.EZ', 0, 0, 0, 0, 0, 0, 0, '00-00-00-00-00-00', '不可删除、修改', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '00000000', to_date('01-01-2000', 'dd-mm-yyyy'), to_date('01-01-2100', 'dd-mm-yyyy'), null, null, '10000000000001');
insert into SH_INFO_OPERATOR (OPERID, REGION, OPERNAME, PASSWORD, PASSCHGDATE, OPERGROUP, OPERTYPE, OPERLEVEL, ISMANAGER, CONTACTPHONE, ORGID, ISRESTRICT, STARTTIME, ENDTIME, ENABLEGPRS, GPRSSTARTTIME, GPRSENDTIME, ISCHKMAC, MAC, NOTES, CREATEDATE, STATUS, STATUSDATE, RELE_STAFF_ID, START_USING_TIME, END_USING_TIME, LOGINTYPE, AREAID, ROLEID)
values ('1301834', 711, '系统管理员1301834', '96e79218965eb72c92a549dd5a330112', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'OPRT31', null, 1, '13908684713', 'HB.EZ', 0, 0, 0, 0, 0, 0, 0, '00-00-00-00-00-00', '不可删除、修改', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '00000000', to_date('01-01-2000', 'dd-mm-yyyy'), to_date('01-01-2100', 'dd-mm-yyyy'), null, null, '10000000000001');
insert into SH_INFO_OPERATOR (OPERID, REGION, OPERNAME, PASSWORD, PASSCHGDATE, OPERGROUP, OPERTYPE, OPERLEVEL, ISMANAGER, CONTACTPHONE, ORGID, ISRESTRICT, STARTTIME, ENDTIME, ENABLEGPRS, GPRSSTARTTIME, GPRSENDTIME, ISCHKMAC, MAC, NOTES, CREATEDATE, STATUS, STATUSDATE, RELE_STAFF_ID, START_USING_TIME, END_USING_TIME, LOGINTYPE, AREAID, ROLEID)
values ('1301143', 711, '系统管理员1301143', '96e79218965eb72c92a549dd5a330112', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'OPRT31', null, 1, '13908684713', 'HB.EZ', 0, 0, 0, 0, 0, 0, 0, '00-00-00-00-00-00', '不可删除、修改', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '00000000', to_date('01-01-2000', 'dd-mm-yyyy'), to_date('01-01-2100', 'dd-mm-yyyy'), null, null, '10000000000001');
commit;
prompt 3 records loaded
prompt Loading SH_ORGANIZATION...
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01', 711, '鄂州市区营业部', 'ogkdDept', 4, 0, null, 'HB.EZ.01', null, null, null, null, null, null, 'SiteID=0,ParentSiteID=1301', '2', 'HB.EZ.01', null, null, to_date('18-12-2005 21:43:35', 'dd-mm-yyyy hh24:mi:ss'), 1, null);
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01.09', 711, '集团业务中心', 'ogkdDept', 5, 0, null, 'HB.EZ.01.01', null, null, null, null, null, null, null, '2', 'HB.EZ.01', null, null, to_date('18-12-2005 21:43:42', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-01-2011 10:38:24', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01.08', 711, '催欠中心', 'ogkdDept', 5, 0, null, 'HB.EZ.01.01', null, null, null, null, null, null, 'SiteID=130105,ParentSiteID=1301', '2', 'HB.EZ.01', null, null, to_date('26-12-2001 02:24:10', 'dd-mm-yyyy hh24:mi:ss'), 1, null);
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01.07', 711, '数据业务中心', 'ogkdDept', 5, 0, null, 'HB.EZ.01.01', null, null, null, null, null, null, null, '2', 'HB.EZ.01', null, null, to_date('18-12-2005 21:43:42', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('20-07-2010 16:10:06', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01.06', 711, '城区营销中心', 'ogkdDept', 5, 0, null, 'HB.EZ.01.01', null, null, null, null, null, null, null, '2', 'HB.EZ.01', null, null, to_date('18-12-2005 21:43:42', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-11-2010 11:07:51', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01.05', 711, '帐务中心', 'ogkdDept', 5, 0, null, 'HB.EZ.01.01', null, null, null, null, null, null, null, '2', 'HB.EZ.01', null, null, to_date('26-12-2001 02:24:10', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('25-04-2011 09:04:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01.04', 711, '直销中心', 'ogkdDept', 5, 0, null, 'HB.EZ.01.01', null, null, null, null, null, null, 'SiteID=130188,ParentSiteID=1301', '2', 'HB.EZ.01', null, null, to_date('18-12-2005 21:43:42', 'dd-mm-yyyy hh24:mi:ss'), 1, null);
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01.01', 711, '省客服', 'ogkdDept', 5, 0, null, 'HB.EZ.01.01', null, null, null, null, null, null, null, '2', 'HB.EZ.01', null, null, to_date('12-06-2006 14:56:58', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('12-06-2006 14:56:58', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01', 711, '鄂州市区营业区', 'ogkdCounty', 3, 0, null, 'HB.EZ', null, null, null, null, null, null, null, '2', 'HB.EZ.01', null, null, to_date('18-12-2005 21:43:35', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-02-2011 11:04:15', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ', 711, '鄂州', 'ogkdRegion', 2, 0, null, 'HB', null, null, null, null, null, null, null, '0', 'HB.EZ', null, null, to_date('18-12-2005 21:43:35', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('25-04-2011 10:36:49', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB', 999, '湖北', 'ogkdProv', 1, 1, null, null, null, null, null, null, null, null, 'SiteID=0,ParentSiteID=0', '0', 'HB', null, null, to_date('03-01-2000 17:09:31', 'dd-mm-yyyy hh24:mi:ss'), 1, null);
commit;
prompt 11 records loaded
prompt Loading SH_ORGANIZATION_CHILD...
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB', 0, to_date('18-07-2009 23:41:56', 'dd-mm-yyyy hh24:mi:ss'), 999, '湖北');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ', 1, to_date('18-07-2009 23:42:12', 'dd-mm-yyyy hh24:mi:ss'), 711, '鄂州');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01', 2, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '鄂州市区营业区');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01', 3, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '鄂州市区营业部');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.01', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '省客服');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.04', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '直销中心');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.05', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '帐务中心');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.06', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '城区营销中心');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.07', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '数据业务中心');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.08', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '催欠中心');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.09', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '集团业务中心');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.10', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, 'vip俱乐部');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.11', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '186客服中心');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.12', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '凤凰路主营业厅');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.12.01', 5, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '凤凰路主营业厅自助厅');
commit;
prompt 15 records loaded
prompt Enabling triggers for SH_INFO_OPERATOR...
alter table SH_INFO_OPERATOR enable all triggers;
prompt Enabling triggers for SH_ORGANIZATION...
alter table SH_ORGANIZATION enable all triggers;
prompt Enabling triggers for SH_ORGANIZATION_CHILD...
alter table SH_ORGANIZATION_CHILD enable all triggers;

--------------------------------------------------------------------------------
set feedback on
set define on
prompt Done.
