insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('OpenSubs_Atsv', 1, null, '', 'OPCODE', 'CCEIoutStopOpenSubs', '���뿪��', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('OpenSubs_Atsv', 1, null, '', 'stoptype', 'OpenSubs', '�û���ͣ�������ǿ���', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('OpenSubs_Atsv', 2, 0, 'TELNUM=MSISDN&CALLERNUM=CALL_NUMBER&OPERID=OPERID', '', '', '�������ת��', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('OpenSubs_Atsv', 3, 0, 'RECFORMNUM=RECFORMNUM', '', '', '�������ת��', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('OpenSubs_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('StopSubs_Atsv', 1, null, '', 'OPCODE', 'CCEIoutStopOpenSubs', '����ͣ��', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('StopSubs_Atsv', 1, null, '', 'stoptype', 'StopSubs', '�û���ͣ��������ͣ��', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('StopSubs_Atsv', 2, 0, 'TELNUM=MSISDN&CALLERNUM=CALL_NUMBER&OPERID=OPERID', '', '', '�������ת��', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('StopSubs_Atsv', 3, 0, 'RECFORMNUM=RECFORMNUM', '', '', '�������ת��', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('StopSubs_Atsv', 4, null, '100=0', '', '', '����ֵת��', null);

