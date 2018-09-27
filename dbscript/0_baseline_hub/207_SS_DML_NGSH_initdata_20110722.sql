--==============================================================
-- ע�⣺���ű�������������ʹ�ð����������ݣ��û�ngsh��
-- �����������ϣ� regionlis������Ա����֯��Ϣ����֯��ϢCHILD�� ��Ҫ������tbcs��ͬ���
-- ��ο��ű���NG�����ն�ͬ���_NGSH_DDL.sql��

-- 1.ɾ���û����������ݿ������(delete)
-- 2.����ʼ��������

-- ���ű���ngsh�û�ִ��
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
values ('270', 1, '�人', 'HB.WH', 'h', 'B', null, null, 'A', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('714', 1, '��ʯ', 'HB.HS', 's', 'A', null, null, 'A', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('711', 1, '����', 'HB.EZ', 'z', 'A', null, null, 'A', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('718', 1, '��ʩ', 'HB.ES', 'e', 'A', null, null, 'A', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('728', 1, '����', 'HB.JH', 'j', 'A', null, null, 'A', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('715', 1, '����', 'HB.XN', 'x', 'A', null, null, 'A', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('722', 1, '����', 'HB.SZ', 'z', 'A', null, null, 'A', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('717', 1, '�˲�', 'HB.YC', 'c', 'A', null, null, 'B', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('719', 1, 'ʮ��', 'HB.SY', 'y', 'A', null, null, 'B', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('710', 1, '�差', 'HB.XF', 'f', 'A', null, null, 'B', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('716', 1, '����', 'HB.JZ', 'i', 'A', null, null, 'B', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('724', 1, '����', 'HB.JM', 'm', 'A', null, null, 'B', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('713', 1, '�Ƹ�', 'HB.HG', 'a', 'A', null, null, 'B', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('712', 1, 'Т��', 'HB.XG', 'g', 'A', null, null, 'B', null);
insert into SH_INFO_REGIONLIST (REGION, CAPABILITY, REGIONNAME, ORGID, CITY_ID, PARTITION, ZIP, INT_BIT, DBAREA, DBSID)
values ('999', 1, '����', 'HB', '0', 'A', null, null, 'A', null);
commit;
prompt 15 records loaded
prompt Loading SH_INFO_OPERATOR...
insert into SH_INFO_OPERATOR (OPERID, REGION, OPERNAME, PASSWORD, PASSCHGDATE, OPERGROUP, OPERTYPE, OPERLEVEL, ISMANAGER, CONTACTPHONE, ORGID, ISRESTRICT, STARTTIME, ENDTIME, ENABLEGPRS, GPRSSTARTTIME, GPRSENDTIME, ISCHKMAC, MAC, NOTES, CREATEDATE, STATUS, STATUSDATE, RELE_STAFF_ID, START_USING_TIME, END_USING_TIME, LOGINTYPE, AREAID, ROLEID)
values ('admin', 711, 'ϵͳ����Աadmin', '96e79218965eb72c92a549dd5a330112', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'OPRT31', null, 1, '13908684713', 'HB.EZ', 0, 0, 0, 0, 0, 0, 0, '00-00-00-00-00-00', '����ɾ�����޸�', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '00000000', to_date('01-01-2000', 'dd-mm-yyyy'), to_date('01-01-2100', 'dd-mm-yyyy'), null, null, '10000000000001');
insert into SH_INFO_OPERATOR (OPERID, REGION, OPERNAME, PASSWORD, PASSCHGDATE, OPERGROUP, OPERTYPE, OPERLEVEL, ISMANAGER, CONTACTPHONE, ORGID, ISRESTRICT, STARTTIME, ENDTIME, ENABLEGPRS, GPRSSTARTTIME, GPRSENDTIME, ISCHKMAC, MAC, NOTES, CREATEDATE, STATUS, STATUSDATE, RELE_STAFF_ID, START_USING_TIME, END_USING_TIME, LOGINTYPE, AREAID, ROLEID)
values ('1301834', 711, 'ϵͳ����Ա1301834', '96e79218965eb72c92a549dd5a330112', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'OPRT31', null, 1, '13908684713', 'HB.EZ', 0, 0, 0, 0, 0, 0, 0, '00-00-00-00-00-00', '����ɾ�����޸�', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '00000000', to_date('01-01-2000', 'dd-mm-yyyy'), to_date('01-01-2100', 'dd-mm-yyyy'), null, null, '10000000000001');
insert into SH_INFO_OPERATOR (OPERID, REGION, OPERNAME, PASSWORD, PASSCHGDATE, OPERGROUP, OPERTYPE, OPERLEVEL, ISMANAGER, CONTACTPHONE, ORGID, ISRESTRICT, STARTTIME, ENDTIME, ENABLEGPRS, GPRSSTARTTIME, GPRSENDTIME, ISCHKMAC, MAC, NOTES, CREATEDATE, STATUS, STATUSDATE, RELE_STAFF_ID, START_USING_TIME, END_USING_TIME, LOGINTYPE, AREAID, ROLEID)
values ('1301143', 711, 'ϵͳ����Ա1301143', '96e79218965eb72c92a549dd5a330112', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), null, 'OPRT31', null, 1, '13908684713', 'HB.EZ', 0, 0, 0, 0, 0, 0, 0, '00-00-00-00-00-00', '����ɾ�����޸�', to_date('01-01-2000 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '00000000', to_date('01-01-2000', 'dd-mm-yyyy'), to_date('01-01-2100', 'dd-mm-yyyy'), null, null, '10000000000001');
commit;
prompt 3 records loaded
prompt Loading SH_ORGANIZATION...
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01', 711, '��������Ӫҵ��', 'ogkdDept', 4, 0, null, 'HB.EZ.01', null, null, null, null, null, null, 'SiteID=0,ParentSiteID=1301', '2', 'HB.EZ.01', null, null, to_date('18-12-2005 21:43:35', 'dd-mm-yyyy hh24:mi:ss'), 1, null);
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01.09', 711, '����ҵ������', 'ogkdDept', 5, 0, null, 'HB.EZ.01.01', null, null, null, null, null, null, null, '2', 'HB.EZ.01', null, null, to_date('18-12-2005 21:43:42', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-01-2011 10:38:24', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01.08', 711, '��Ƿ����', 'ogkdDept', 5, 0, null, 'HB.EZ.01.01', null, null, null, null, null, null, 'SiteID=130105,ParentSiteID=1301', '2', 'HB.EZ.01', null, null, to_date('26-12-2001 02:24:10', 'dd-mm-yyyy hh24:mi:ss'), 1, null);
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01.07', 711, '����ҵ������', 'ogkdDept', 5, 0, null, 'HB.EZ.01.01', null, null, null, null, null, null, null, '2', 'HB.EZ.01', null, null, to_date('18-12-2005 21:43:42', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('20-07-2010 16:10:06', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01.06', 711, '����Ӫ������', 'ogkdDept', 5, 0, null, 'HB.EZ.01.01', null, null, null, null, null, null, null, '2', 'HB.EZ.01', null, null, to_date('18-12-2005 21:43:42', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-11-2010 11:07:51', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01.05', 711, '��������', 'ogkdDept', 5, 0, null, 'HB.EZ.01.01', null, null, null, null, null, null, null, '2', 'HB.EZ.01', null, null, to_date('26-12-2001 02:24:10', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('25-04-2011 09:04:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01.04', 711, 'ֱ������', 'ogkdDept', 5, 0, null, 'HB.EZ.01.01', null, null, null, null, null, null, 'SiteID=130188,ParentSiteID=1301', '2', 'HB.EZ.01', null, null, to_date('18-12-2005 21:43:42', 'dd-mm-yyyy hh24:mi:ss'), 1, null);
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01.01.01', 711, 'ʡ�ͷ�', 'ogkdDept', 5, 0, null, 'HB.EZ.01.01', null, null, null, null, null, null, null, '2', 'HB.EZ.01', null, null, to_date('12-06-2006 14:56:58', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('12-06-2006 14:56:58', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ.01', 711, '��������Ӫҵ��', 'ogkdCounty', 3, 0, null, 'HB.EZ', null, null, null, null, null, null, null, '2', 'HB.EZ.01', null, null, to_date('18-12-2005 21:43:35', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('23-02-2011 11:04:15', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB.EZ', 711, '����', 'ogkdRegion', 2, 0, null, 'HB', null, null, null, null, null, null, null, '0', 'HB.EZ', null, null, to_date('18-12-2005 21:43:35', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('25-04-2011 10:36:49', 'dd-mm-yyyy hh24:mi:ss'));
insert into SH_ORGANIZATION (ORGID, REGION, ORGNAME, ORGTYPE, ORGLEVEL, ISINNER, MANAGERID, PARENTID, OPENPHONE, ADDRESS, POSTCODE, CONTACTMAN, CONTACTPHONE, FAX, EMAIL, COUNTY, AREAID, OWNERTYPE, NOTES, CREATEDATE, STATUS, STATUSDATE)
values ('HB', 999, '����', 'ogkdProv', 1, 1, null, null, null, null, null, null, null, null, 'SiteID=0,ParentSiteID=0', '0', 'HB', null, null, to_date('03-01-2000 17:09:31', 'dd-mm-yyyy hh24:mi:ss'), 1, null);
commit;
prompt 11 records loaded
prompt Loading SH_ORGANIZATION_CHILD...
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB', 0, to_date('18-07-2009 23:41:56', 'dd-mm-yyyy hh24:mi:ss'), 999, '����');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ', 1, to_date('18-07-2009 23:42:12', 'dd-mm-yyyy hh24:mi:ss'), 711, '����');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01', 2, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '��������Ӫҵ��');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01', 3, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '��������Ӫҵ��');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.01', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, 'ʡ�ͷ�');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.04', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, 'ֱ������');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.05', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '��������');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.06', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '����Ӫ������');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.07', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '����ҵ������');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.08', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '��Ƿ����');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.09', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '����ҵ������');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.10', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, 'vip���ֲ�');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.11', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '186�ͷ�����');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.12', 4, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '���·��Ӫҵ��');
insert into SH_ORGANIZATION_CHILD (PARWAYID, SUBWAYID, HICHYOFFSET, CREATETIME, REGION, SUBWAYNAME)
values ('HB', 'HB.EZ.01.01.12.01', 5, to_date('18-07-2009 23:42:13', 'dd-mm-yyyy hh24:mi:ss'), 711, '���·��Ӫҵ��������');
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
