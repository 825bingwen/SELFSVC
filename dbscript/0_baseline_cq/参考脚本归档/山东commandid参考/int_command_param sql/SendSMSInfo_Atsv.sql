insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('SendSMSInfo_Atsv', 2, 0, 'TELNUM=SERVNUM&TEMPLATENO=TEMPLATENO&SMPARAM=SMPARAM&ISRVCALL=ISRVCALL&NOTIFYTYPE=NOTIFYTYPE&DISPPORT=DISPPORT&RELATEDFORMNUM=RELATEDFORMNUM&YWFORMNUM=YWFORMNUM&SENDDATE=SENDDATE&RETCODE=OUTRETCODE&RETMSG=RETMSG&OPERID=OPERID', '', '', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('SendSMSInfo_Atsv', 1, null, '', 'OPCODE', 'SendSMSInfo', '', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('SendSMSInfo_Atsv', 4, null, '100=0', '', '', '', null);

