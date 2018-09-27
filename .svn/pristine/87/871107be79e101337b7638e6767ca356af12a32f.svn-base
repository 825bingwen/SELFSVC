set define off;

delete from sh_param_value where paramid='SH_MAIL_HOST';
delete from sh_param_value where paramid='SH_MAIL_PORT';
delete from sh_param_value where paramid='SH_MAIL_FROM';
delete from sh_param_value where paramid='SH_MAIL_USERNAME';
delete from sh_param_value where paramid='SH_MAIL_PWD';
delete from sh_param_value where paramid='SH_MAIL_HASPROXY';
delete from sh_param_value where paramid='SH_MAIL_PROXYHOST';
delete from sh_param_value where paramid='SH_MAIL_DELAY';
delete from sh_param_value where paramid='SH_MAIL_PROXYTYPE';
delete from sh_param_value where paramid='SH_MAIL_PROXYHOSTPORT';

insert into sh_param_value (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_MAIL_PROXYHOSTPORT', '8083', 1, sysdate, '', 'sys_oper', sysdate);

insert into sh_param_value (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_MAIL_PWD', 'sK3sN8yS', 1, sysdate, '', 'sys_oper', sysdate);

insert into sh_param_value (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_MAIL_USERNAME', 'cKF48754', 1, sysdate, '', 'sys_oper', sysdate);

insert into sh_param_value (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_MAIL_FROM', 'chenhangcheng@huawei.com', 1, sysdate, '', 'sys_oper', sysdate);

insert into sh_param_value (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_MAIL_PORT', '8083', 1, sysdate, '', 'sys_oper', sysdate);

insert into sh_param_value (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_MAIL_HOST', '192.168.10.236', 1, sysdate, '', 'sys_oper', sysdate);

insert into sh_param_value (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_MAIL_PROXYHOST', '192.168.10.236', 1, sysdate, '', 'sys_oper', sysdate);

insert into sh_param_value (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_MAIL_DELAY', '3', 1, sysdate, '', 'sys_oper', sysdate);

insert into sh_param_value (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_MAIL_PROXYTYPE', 'http', 1, sysdate, '', 'sys_oper', sysdate);

insert into sh_param_value (PARAMID, PARAMVALUE, STATUS, STATUSDATE, ADDDATA, LOG_OPER_ID, LOG_OPER_DATE)
values ('SH_MAIL_HASPROXY', '1', 1, sysdate, '', 'sys_oper', sysdate);

delete from sh_param where paramid='SH_MAIL_HOST';
delete from sh_param where paramid='SH_MAIL_PORT';
delete from sh_param where paramid='SH_MAIL_FROM';
delete from sh_param where paramid='SH_MAIL_USERNAME';
delete from sh_param where paramid='SH_MAIL_PWD';
delete from sh_param where paramid='SH_MAIL_HASPROXY';
delete from sh_param where paramid='SH_MAIL_PROXYHOST';
delete from sh_param where paramid='SH_MAIL_DELAY';
delete from sh_param where paramid='SH_MAIL_PROXYTYPE';
delete from sh_param where paramid='SH_MAIL_PROXYHOSTPORT';

insert into sh_param (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_MAIL_HOST', '邮件服务器地址', 'String', 1, sysdate, 1, sysdate, '');

insert into sh_param (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_MAIL_PORT', '邮件服务器端口', 'String', 1, sysdate, 1, sysdate, '');

insert into sh_param (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_MAIL_FROM', '发件人邮箱地址', 'String', 1, sysdate, 1, sysdate, '');

insert into sh_param (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_MAIL_USERNAME', '发件人用户名', 'String', 1, sysdate, 1, sysdate, '');

insert into sh_param (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_MAIL_PWD', '发件人密码', 'String', 1, sysdate, 1, sysdate, '');

insert into sh_param (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_MAIL_HASPROXY', '是否使用代理标识(1:使用，0:不使用)', 'String', 1, sysdate, 1, sysdate, '');

insert into sh_param (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_MAIL_PROXYHOST', '代理主机', 'String', 1, sysdate, 1, sysdate, '');

insert into sh_param (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_MAIL_DELAY', '邮件发送延迟时间（分钟）', 'String', 1, sysdate, 1, sysdate, '');

insert into sh_param (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_MAIL_PROXYTYPE', '代理类型(http、socks)', 'String', 1, sysdate, 1, sysdate, '');

insert into sh_param (PARAMID, PARAMNAME, DATATYPE, MANAGEABLE, CREATEDATE, STATUS, STATUSDATE, DESCRIPTION)
values ('SH_MAIL_PROXYHOSTPORT', ' 代理主机端口', 'String', 1, sysdate, 1, sysdate, '');
