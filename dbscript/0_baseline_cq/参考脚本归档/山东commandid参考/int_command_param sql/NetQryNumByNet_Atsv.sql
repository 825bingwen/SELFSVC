insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 1, null, '', 'OPCODE', 'QryNumByNet', '触摸屏选号（客服）', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 1, null, '', 'OPTYPE', 'COMMAGT_SELTEL', '原空中充值代理商选号', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 2, 0, 'COUNTY_ID=COUNTY_ID&STAFF_ID=STAFF_ID&TEL_BEGIN=CODE_BEGIN&TEL_END=CODE_END&TEL_SUFFIX=MSISDN_SUFFIX&TEL_PREFIX=MSISDN_PRE&SELECT_RULE=SELE_RULE&TEL_BRANDID=SERV_TYPE&NEEDOPER=NEEDOPER', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 3, 3, '0=0&1=3&2=2', '', '', '输出参数转换', 0);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('NetQryNumByNet_Atsv', 4, null, '10000=0', '', '', '返回值转换', null);

