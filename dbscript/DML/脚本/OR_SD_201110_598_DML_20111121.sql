set define off;

delete from INT_RETCODE_CONVERT where BUSINESSID='cli_qry_billaddinfo';

insert into INT_RETCODE_CONVERT (CHANNELID, BUSINESSID, FROM_RETCODE, TO_RETCODE, TO_RETMSG, RET_TYPE, STATUS, STATUSTIME, CREATETIME, NOTE)
values ('bsacAtsv', 'cli_qry_billaddinfo', '0', '100', '', '0', 1, to_date('21-08-2011', 'dd-mm-yyyy'), to_date('21-08-2011', 'dd-mm-yyyy'), '账单备注查询');

delete from int_flow_define where FLOWID='CLT_bsacAtsv_QryBillAddInfo';
insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_QryBillAddInfo', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<qryflag>/message_request/message_body/message_content/qryflag/text()</qryflag>
<subsid>/message_request/message_body/message_content/subsid/text()</subsid>
<begin_time>/message_request/message_body/message_content/begin_time/text()</begin_time>
<end_time>/message_request/message_body/message_content/end_time/text()</end_time>
</tagset>
</xml>
</request>
<response>
<success>
<xml>
<head default="1"/>
<body path="/message_response">
<xml>
<templet>
<![CDATA[
<message_body>
</message_body>
]]>
</templet>
<infill>
    <batch>
<cdata path="./message_body">
<templet>
<![CDATA[
<message_content>
<remark></remark>
</message_content>
]]>
</templet>
<infill>
<single>
<xml>
<remark>./message_content/remark/insert($REMARK)</remark>
</xml>
</single>
</infill>
</cdata>
</batch>
</infill>
</xml>
</body>
</xml>
</success>
<fail>
<xml>
<head default="1"/>
</xml>
</fail>
</response>
<opProcessor flow="client">
<invoke intid="$channelid" businessid="SVR_cli_qry_billaddinfo" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<QRYFLAG>$qryflag</QRYFLAG>
<SUBSID>$subsid</SUBSID>
<BEGIN_TIME>$begin_time</BEGIN_TIME>
<END_TIME>$end_time</END_TIME>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<tagset>
<REMARK>$REMARK</REMARK>
</tagset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);

delete from int_flow_define where FLOWID='SVR_CLT_bsacAtsv_QryBillAddInfo';
insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('SVR_CLT_bsacAtsv_QryBillAddInfo', 0, '<portal>
<opProcessor flow="server">
<action opcode="LocQryBillAddInfo">
<request>
<pkg>
<tagset>
<QRYFLAG>$QRYFLAG</QRYFLAG>
<SUBSID>$SUBSID</SUBSID>
<BEGIN_TIME>$BEGIN_TIME</BEGIN_TIME>
<END_TIME>$END_TIME</END_TIME>
<OPERID>$OPERID</OPERID>
</tagset>
</pkg>
</request>
</action>
</opProcessor>
</portal>', 0);

delete from int_business_define where BUSINESSID='SVR_cli_qry_billaddinfo';
insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('SVR_cli_qry_billaddinfo', 'BUSINESS', 'BUSINESS', 'CscGetInfo', 0, 60, 'SVR_CLT_bsacAtsv_QryBillAddInfo', '账单备注查询', '采用地区路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 4, 2);

delete from int_business_define where  BUSINESSID='cli_qry_billaddinfo';
insert into int_business_define (BUSINESSID, CHANNELID, PROTOCOLID, BUSITYPE, URGENCY, TIMEOUT, FLOWID, FLOWNAME, DESCRIBE, EFFTIME, EXPTIME, LOGLEVEL, COLPERF)
values ('cli_qry_billaddinfo', 'bsacAtsv', 'SelfTerminalProtocolID', 'CscUnionGetInfo', 0, 60, 'CLT_bsacAtsv_QryBillAddInfo', '账单备注查询', '采用地区路由', to_date('11-08-2010', 'dd-mm-yyyy'), to_date('01-08-2039', 'dd-mm-yyyy'), 4, 2);

