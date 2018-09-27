insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('PreBlr_Atsv', '提醒余额设定', 1, 1, 'CRecInterface', to_date('18-12-2006 16:25:14', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '通过1861，用户可以自己设定要提醒的余额。如果取消余额提醒，输入参数中的CREDIT参数传0', '', 0, 1, 0, 0);

