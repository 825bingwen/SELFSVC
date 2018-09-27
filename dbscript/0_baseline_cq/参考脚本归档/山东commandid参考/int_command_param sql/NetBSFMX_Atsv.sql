insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 3, 3, '0=1&1=5&2=4&3=6&4=9&5=15&6=17', '', '', '输出参数转换', 0);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 2, 0, 'TELNUM=MSISDN&STARTDATE=STARTDATE&ENDDATE=ENDDATE&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 1, null, '', 'OPCODE', 'QryReceptionHistory', '缴费历史查询（网上营业厅）', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetBSFMX_Atsv', 1, null, '', 'RECEPTION_TYPE', 'Charge', '缴费历史查询（网上营业厅调用）', null);

