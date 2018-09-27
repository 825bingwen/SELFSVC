insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetPukquery_Atsv', 'PIN\PUK码查询（自助终端）', 1, 4, 'CIntCommonDispatcher', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '根据手机号码查询pin/puk码信息，自助终端调用', '', 0, 0, 0, 0);

