insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetBSFMX_Atsv', '缴费历史查询（网上营业厅）', 1, 7, 'CRecInterface', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '根据手机号码查询该用户的缴费纪录，网上营业厅系统调用', '', 0, 0, 0, 0);

