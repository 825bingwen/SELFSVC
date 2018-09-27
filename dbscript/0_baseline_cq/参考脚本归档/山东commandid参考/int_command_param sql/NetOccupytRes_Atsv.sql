insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 1, null, '', 'EFF_LEN', '4320', '占号时间默认为3天', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 2, 0, 'TELNUM=MSISDN&REGION=REGION&ORGID=ORG_ID&CERTTYPE=CERTTYPE&CERTID=CERTI_NO&CONTACTPHONE=CONTACTTEL&CUSTNAME=NAME&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 1, null, '', 'OPCODE', 'CCEIOccupytRes', '网上占号（自助终端）', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetOccupytRes_Atsv', 3, 0, 'ORDERID=ORDERID', '', '', '', null);

