insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetGetAcpt_Atsv', '受理流水信息查询（网上营业厅）', 1, 5, 'CRecInterface', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '根据输入的手机号码，查询该用户的受理流水信息，网上营业厅调用', '', 0, 0, 0, 0);

