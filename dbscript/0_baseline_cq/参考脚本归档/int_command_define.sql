insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('OpenSubs_Atsv', '申请开机', null, null, 'CIntCommonDispatcher', to_date('03-01-2007 16:11:02', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '申请开机', '', 0, 1, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('StopSubs_Atsv', '申请停机', null, null, 'CIntCommonDispatcher', to_date('03-01-2007', 'dd-mm-yyyy'), 1, null, '申请停机', '', 0, 1, 0, 0);

