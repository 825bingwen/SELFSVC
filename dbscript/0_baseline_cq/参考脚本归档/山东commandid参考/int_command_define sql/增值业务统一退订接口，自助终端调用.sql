insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CancleSubsIntServ_Atsv', '增值业务统一退订接口，自助终端调用', 0, 0, 'CIntCommonDispatcher', to_date('25-05-2010', 'dd-mm-yyyy'), 1, null, '统一退订', '', 0, 0, 1, 0);

