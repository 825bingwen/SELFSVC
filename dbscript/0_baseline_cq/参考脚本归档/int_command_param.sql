insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('OpenSubs_Atsv', 1, null, '', 'OPCODE', 'CCEIoutStopOpenSubs', '申请开机', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('OpenSubs_Atsv', 1, null, '', 'stoptype', 'OpenSubs', '用户开停机类型是开机', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('OpenSubs_Atsv', 2, 0, 'TELNUM=MSISDN&CALLERNUM=CALL_NUMBER&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('OpenSubs_Atsv', 3, 0, 'RECFORMNUM=RECFORMNUM', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('OpenSubs_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('StopSubs_Atsv', 1, null, '', 'OPCODE', 'CCEIoutStopOpenSubs', '申请停机', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('StopSubs_Atsv', 1, null, '', 'stoptype', 'StopSubs', '用户开停机类型是停机', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('StopSubs_Atsv', 2, 0, 'TELNUM=MSISDN&CALLERNUM=CALL_NUMBER&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('StopSubs_Atsv', 3, 0, 'RECFORMNUM=RECFORMNUM', '', '', '输出参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('StopSubs_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

