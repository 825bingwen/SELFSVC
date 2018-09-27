insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryCurrentBill_Atsv', 4, null, '100=0', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryCurrentBill_Atsv', 1, null, '', 'OPCODE', 'QryGivenBill', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryCurrentBill_Atsv', 1, null, '', 'QRYTYPE', '0', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryCurrentBill_Atsv', 2, 0, 'TELNUM=MSISDN&CYCLEOFFSET=CYCLEOFFSET&BILLTEMPLNO=BILLTEMPLNO&OPERID=OPERID', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryCurrentBill_Atsv', 3, 3, '0=0&1=1&2=2&3=3&4=4&5=5&6=6', '', '', '', 1);

