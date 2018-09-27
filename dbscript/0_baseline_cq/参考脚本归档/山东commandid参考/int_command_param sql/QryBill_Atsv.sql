insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 1, null, '', 'OPCODE', 'QryBill', '话费查询', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 1, null, '', 'QRYTYPE', '0', '查询标志', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 2, 0, 'TELNUM=MSISDN&MONTH=MONTH&BILLTEMPLNO=BILLTEMPLNO&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 3, 3, '0=1&1=2&2=3&3=4&4=5&5=6', '', '', '输出参数转换', 1);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBill_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

