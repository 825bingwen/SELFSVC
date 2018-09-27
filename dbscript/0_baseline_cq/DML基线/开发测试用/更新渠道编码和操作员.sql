--渠道由bsacAtsv转为bsacTYAuto
--虚拟操作员使用innerintaoto1
--客户端接入点定义表
select * from common_dict.INT_ACCESS_POINT;
update common_dict.INT_ACCESS_POINT set defchanid='bsacAtsv';
--业务和流程对应关系定义表
select * from common_dict.INT_BUSINESS_DEFINE;
update common_dict.INT_BUSINESS_DEFINE set channelid='bsacAtsv';

--返回值转换
select * from common_dict.INT_RETCODE_CONVERT where channelid='bsacAtsv';
update common_dict.INT_RETCODE_CONVERT
   set channelid = 'bsacAtsv'
 where channelid = 'bsacTYAuto'
   and businessid in
       ('cli_busi_scoreexchange_cq'
,'cli_qry_scoreexchange_cq'
,'cli_qry_taocan_cq'
,'cli_qry_billsend_cq'
,'cli_busi_alarmbalance'
,'cli_busi_calltransfer'
,'cli_busi_cancelsp'
,'cli_busi_chgpwd'
,'cli_busi_chgscore'
,'cli_busi_cidcheck'
,'cli_busi_mpaycharge'
,'cli_busi_occupytel'
,'cli_busi_productrec'
,'cli_busi_pwdreset'
,'cli_busi_sendsms'
,'cli_busi_sendsmsinfo'
,'cli_busi_stopopensubs'
,'cli_qry_alarmbalance'
,'cli_qry_balance_cq'
,'cli_qry_bill'
,'cli_qry_cdr'
,'cli_qry_chargehistory'
,'cli_qry_currentbill'
,'cli_qry_dictitem'
,'cli_qry_mpayacc'
,'cli_qry_numbynet'
,'cli_qry_numregion'
,'cli_qry_productfee'
,'cli_qry_pukcode'
,'cli_qry_scorevalue'
,'cli_qry_spinfo'
,'cli_qry_taocan'
,'cli_qry_userinfo'
,'cli_qry_userstate'
,'cli_qry_yckf'
,'cli_qry_zfinfo'
,'cli_qry_detailedbill'
);

select * from accagentunit where agentunit='bsacTYAuto';
