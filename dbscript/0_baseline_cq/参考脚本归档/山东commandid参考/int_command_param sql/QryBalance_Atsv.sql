insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 1, null, '', 'QRYDROPFLAG', '1', '返回消户用户资料,于超要求', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 1, null, '', 'PRINTTRACELOG', '1', '出错打印日志', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 4, null, '100=0', '', '', '返回值转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 1, null, '', 'OPCODE', 'QryBalance', '查询余额--查询余额(帐务)', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 2, 0, 'TELNUM=MSISDN&OPERID=OPERID', '', '', '输入参数转换', null);

insert into int_command_param (COMMANDID, PARAMTYPE, TRANSTYPE, PARAMFMT, PARAMNAME, PARAMVALUE, PARAMDESC, BEGINROW)
values ('QryBalance_Atsv', 3, 0, 'FEE=FEE&PAY_BALANCE=PAY_BALANCE&CARD_BALANCE=CARD_BALANCE&CURRENT_XYK=CURRENT_XYK&CURRENT_COMP=CURRENT_COMP&LEFT_XYK=LEFT_XYK&LEFT_COMP=LEFT_COMP&LEFT_SKY=LEFT_SKY', '', '', '输出参数转换', null);

