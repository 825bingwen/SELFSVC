insert into tx_int_dispatcher (INTID, OPCODE, REGION, SERVICENAME, STATUS, NOTES, SECONDSERVICE, TCLASS)
values ('IntSms', 'SendSmsInfo', 230, 'SENDSMS', 1, '', '', '');

insert into tx_int_dispatcher (INTID, OPCODE, REGION, SERVICENAME, STATUS, NOTES, SECONDSERVICE, TCLASS)
values ('IntSms', 'SendSmsInfo', 999, 'SENDSMS', 1, '', '', '');

insert into tx_int_dispatcher (INTID, OPCODE, REGION, SERVICENAME, STATUS, NOTES, SECONDSERVICE, TCLASS)
values ('bsacAtsv', 'default', 230, 'IntSrvNet', 1, '', '', '');

insert into tx_int_dispatcher (INTID, OPCODE, REGION, SERVICENAME, STATUS, NOTES, SECONDSERVICE, TCLASS)
values ('bsacAtsv', 'default', 999, 'IntSrvNet', 1, '', '', '');

insert into tx_int_dispatcher (INTID, OPCODE, REGION, SERVICENAME, STATUS, NOTES, SECONDSERVICE, TCLASS)
values ('IntBank', 'QueryFee', 230, 'CSNRECSRV', 1, '自助终端充值缴费查话费', '', '');

insert into tx_int_dispatcher (INTID, OPCODE, REGION, SERVICENAME, STATUS, NOTES, SECONDSERVICE, TCLASS)
values ('IntBank', 'QueryFee', 999, 'CSNRECSRV', 1, '自助终端充值缴费查话费', '', '');
