set define off;
delete from int_command_define
 where commandid in
       ('Atsv_Busi_ChkChgPwd'
,'Atsv_Qry_producttree'
,'CallTransfer_Atsv'
,'CancleSubsIntServ_Atsv'
,'CheckResetPassword_Atsv'
,'CidCheck_Atsv'
,'NetBSFMX_Atsv'
,'NetChkAndChgPwd_Atsv'
,'NetGetAC_Atsv'
,'NetGetAcpt_Atsv'
,'NetOccupytRes_Atsv'
,'NetPukquery_Atsv'
,'NetQryAlarmBalance_Atsv'
,'NetQryNumByNet_Atsv'
,'OpenStopSubs_Atsv'
,'PreBlr_Atsv'
,'QryBalance_Atsv'
,'QryBill_Atsv'
,'QryCurrentBill_Atsv'
,'QryDetailBill_Atsv'
,'QryProductFeeNew_Atsv'
,'QrySubsAllIntServ_Atsv'
,'QrySubsDetailAvailScore_Atsv'
,'QrySubsFeeInfo_Atsv'
,'QryUserStatus_Atsv'
,'QuePriUsed_Atsv'
,'SendSMSInfo_Atsv'
,'UniGetBalanceByTelnum_Atsv'
,'YCKFCX_Atsv'
,'cli_busi_modacct_Atsv'
,'CommScoreChange_Atsv'
,'GetDictByGroup_Atsv'
,'LogIn_Atsv');

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetPukquery_Atsv', 'PIN\PUK码查询（自助终端）', 1, 4, 'CIntCommonDispatcher', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '根据手机号码查询pin/puk码信息，自助终端调用', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('GetDictByGroup_Atsv', '按组取字典表数据', 1, 4, 'CIntCommonDispatcher', to_date('26-01-2007', 'dd-mm-yyyy'), 1, to_date('26-01-2007 01:00:00', 'dd-mm-yyyy hh24:mi:ss'), '按组取字典表数据', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QrySubsFeeInfo_Atsv', '查询本机资费和已开通服务', 1, 5, 'CIntCommonDispatcher', to_date('13-06-2010 18:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('13-06-2010 18:00:00', 'dd-mm-yyyy hh24:mi:ss'), '查询本机资费和已开通服务', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryBalance_Atsv', '查询余额--查询余额(帐务)', null, null, 'CChargeInterface', to_date('30-12-2006', 'dd-mm-yyyy'), 1, null, '查询余额--查询余额(帐务)', '', 0, 0, 1, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetQryAlarmBalance_Atsv', '查询余额提醒阀值（网上营业厅）', 1, 1, 'CRecInterface', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '查询余额提醒阀值，网上营业厅调用', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetQryNumByNet_Atsv', '触摸屏选号（客服）', 1, 5, 'CAgentInterface', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '触摸屏选号，客服调用', '', 0, 1, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryCurrentBill_Atsv', '当前话费查询', 1, 7, 'CChargeInterface', to_date('08-05-2009 17:24:57', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '当前话费查询', '', 0, 0, 0, 1);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryProductFeeNew_Atsv', '根据nocde(新)查询产品,优惠的资费描述信息', 1, 1, 'CRecInterface', to_date('06-05-2009', 'dd-mm-yyyy'), 1, null, '根据nocde查询产品,优惠的资费描述信息', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CallTransfer_Atsv', '呼叫转移', null, null, 'CIntCommonDispatcher', to_date('04-01-2007', 'dd-mm-yyyy'), 1, null, '呼叫转移', '', 0, 1, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryBill_Atsv', '话费查询', null, 6, 'CChargeInterface', to_date('30-12-2006', 'dd-mm-yyyy'), 1, null, '话费查询', '', 0, 0, 1, 1);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetGetAC_Atsv', '获取手机归属地（网上营业厅）', 1, 1, 'CIntCommonDispatcher', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '根据手机号码，得到它的归属地区号和省代码，客服系统调用', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CommScoreChange_Atsv', '积分调整', 1, 1, 'CRecInterface', to_date('25-12-2007', 'dd-mm-yyyy'), 1, null, '积分调整', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetBSFMX_Atsv', '缴费历史查询（网上营业厅）', 1, 7, 'CRecInterface', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '根据手机号码查询该用户的缴费纪录，网上营业厅系统调用', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryDetailBill_Atsv', '山东月账单查询', 1, 4, 'CChargeInterface', to_date('30-12-2006', 'dd-mm-yyyy'), 1, null, '山东月帐单查询', '', 0, 0, 0, 1);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetGetAcpt_Atsv', '受理流水信息查询（网上营业厅）', 1, 5, 'CRecInterface', to_date('19-12-2006', 'dd-mm-yyyy'), 1, null, '根据输入的手机号码，查询该用户的受理流水信息，网上营业厅调用', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QuePriUsed_Atsv', '套餐使用情况查询', null, 9, 'CRecInterface', to_date('30-01-2007 11:38:28', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '套餐使用情况查询', '', 0, 0, 1, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('PreBlr_Atsv', '提醒余额设定', 1, 1, 'CRecInterface', to_date('18-12-2006 16:25:14', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '通过1861，用户可以自己设定要提醒的余额。如果取消余额提醒，输入参数中的CREDIT参数传0', '', 0, 1, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetChkAndChgPwd_Atsv', '网上营业厅密码验证及修改', 1, 1, 'CIntCommonDispatcher', to_date('09-02-2007', 'dd-mm-yyyy'), 1, to_date('09-02-2007', 'dd-mm-yyyy'), '密码验证及修改，供网上营业厅调用', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QryUserStatus_Atsv', '网上营业厅停开机业务（先查询用户状态）', 1, 2, 'CRecInterface', to_date('16-01-2007', 'dd-mm-yyyy'), 1, null, '网上营业厅用户停开机', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('LogIn_Atsv', '网上营业厅用户登陆验证', 1, 1, 'CRecInterface', to_date('16-01-2007', 'dd-mm-yyyy'), 1, null, '网上营业厅用户登陆验证', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('NetOccupytRes_Atsv', '网上预订号码（自助终端）', 1, 1, 'CIntCommonDispatcher', to_date('23-08-2011', 'dd-mm-yyyy'), 1, null, '网上预订号码，自助终端调用', '', 0, 1, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QrySubsDetailAvailScore_Atsv', '用户积分查询', 1, 1, 'CRecInterface', to_date('21-01-2009', 'dd-mm-yyyy'), 1, null, '根据用户输入的手机号码，查询该用户的当前积分、本年度积分、上年度积分、前年度积分等信息，客服系统调用', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('YCKFCX_Atsv', '月初扣费查询', null, 4, 'CChargeInterface', to_date('30-12-2006', 'dd-mm-yyyy'), 1, null, '查询用户月初扣费情况，客服系统调用', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('QrySubsAllIntServ_Atsv', '增值业务统一查询接口', 1, 28, 'CIntCommonDispatcher', to_date('25-05-2010', 'dd-mm-yyyy'), 1, null, '统一查询', '', 0, 0, 1, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CancleSubsIntServ_Atsv', '增值业务统一退订接口，自助终端调用', 0, 0, 'CIntCommonDispatcher', to_date('25-05-2010', 'dd-mm-yyyy'), 1, null, '统一退订', '', 0, 0, 1, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('SendSMSInfo_Atsv', '自助终端发送短信接口', 0, 0, 'CIntCommInterface', to_date('11-03-2009', 'dd-mm-yyyy'), 1, null, '自助终端发送短信接口', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CheckResetPassword_Atsv', '自助终端密码重置接口', 0, 0, 'CIntCommonDispatcher', to_date('16-08-2011', 'dd-mm-yyyy'), 1, to_date('16-08-2011', 'dd-mm-yyyy'), '自助终端密码重置接口', '', 0, 1, 1, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('CidCheck_Atsv', '自助终端身份证校验接口', 1, 1, 'CIntCommonDispatcher', to_date('16-08-2011', 'dd-mm-yyyy'), 1, null, '自助终端身份证校验接口', '', 0, 1, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('UniGetBalanceByTelnum_Atsv', '自助终端用户余额查询', 1, 1, 'CIntCommonDispatcher', to_date('24-08-2011 15:46:03', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '自助终端用户余额查询', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('cli_busi_modacct_Atsv', '短信帐单业务受理 ', 1, 1, 'CHubRecStrInterface', to_date('13-06-2011 17:58:29', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Busi_ChkChgPwd', '密码修改', 1, 1, 'CRecInterface', to_date('20-01-2008', 'dd-mm-yyyy'), 1, to_date('20-01-2008', 'dd-mm-yyyy'), '密码修改', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('OpenStopSubs_Atsv', '用户停&开机业务', 1, 1, 'CIntCommonDispatcher', to_date('20-10-2011', 'dd-mm-yyyy'), 1, to_date('20-10-2011', 'dd-mm-yyyy'), '用户停&开机业务', '', 0, 0, 0, 0);

insert into int_command_define (COMMANDID, COMMANDNAME, TOINCOLS, TOOUTCOLS, PROCESSCLASS, INSERTDATE, STATUS, STATUSDATE, COMMANDDESC, NEXTSQL, ISWRITEFILELOG, ISWRITETABLOG, NEEDTRANS, RETCONVERT)
values ('Atsv_Qry_producttree', '以树形查询用户所有产品', 1, 6, 'CHubCustStrInterface', to_date('28-10-2010 00:19:50', 'dd-mm-yyyy hh24:mi:ss'), 1, null, '', '', 0, 0, 0, 0);



