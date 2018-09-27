insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetChkAndChgPwd_Atsv', '网上营业厅密码验证及修改', 1, 1, 'CIntCommonDispatcher', to_date('09-02-2007', 'dd-mm-yyyy'), 1, to_date('09-02-2007', 'dd-mm-yyyy'), '密码验证及修改，供网上营业厅调用', '', 0, 0, 0, 0);

