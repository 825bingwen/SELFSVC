delete from accagentunit where agentunit in ('bsacAtsv','HUAWEI') and agenttype='bsacAtsv';
insert into accagentunit (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE, ISLOCAL, MAXOPERS, VIRTUALOPERNUMS)
values ('bsacAtsv', '自助终端营业厅工号', 'innerintaoto1', '230', 'bsacAtsv', 1, 1, 1);

insert into accagentunit (AGENTUNIT, AGENTNAME, AGENTOPERATOR, AGENTCODE, AGENTTYPE, ISLOCAL, MAXOPERS, VIRTUALOPERNUMS)
values ('HUAWEI', '自助终端营业厅工号', 'innerintaoto1', '230', 'bsacAtsv', 1, 1, 1);