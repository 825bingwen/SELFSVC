insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_zfinfo', 0, '<portal>
<request>
<xml>
<cdata>/message_request/message_body/</cdata>
<tagset>
<telnum>/message_request/message_body/message_content/telnum/text()</telnum>
<qrytype>/message_request/message_body/message_content/qrytype/text()</qrytype>
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
<templet><![CDATA[
<message_content></message_content>
]]></templet>
<infill>
<batch>
<crset from="invoke_resp_cr" path="./message_content" need="0">
<templet><![CDATA[
<info>
<type></type>
<name></name>
<desp></desp>
<start_time></start_time>
<end_time></end_time>
</info>
]]></templet>
<infill>
<single>
<xml>
<type>./info/type/insert(invoke_resp_cr[$,0])</type>
<name>./info/name/insert(invoke_resp_cr[$,1])</name>
<desp>./info/desp/insert(invoke_resp_cr[$,2])</desp>
<start_time>./info/start_time/insert(invoke_resp_cr[$,3])</start_time>
<end_time>./info/end_time/insert(invoke_resp_cr[$,4])</end_time>
</xml>
</single>
</infill>
</crset>
</batch>
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
</response>', 0);


insert into int_flow_define (FLOWID, SORTORDER, FLOWSEGMENT, FLOWTYPE)
values ('CLT_bsacAtsv_qry_zfinfo', 1, '<opProcessor flow="client">
<invoke intid="$channelid" commandid="QrySubsFeeInfo_Atsv" retcode="$RETCODE" retmsg="$RETMSG" telnum="$telnum">
<request>
<pkg>
<tagset>
<UNITID>$unitid</UNITID>
<TELNUM>$telnum</TELNUM>
<QRYTYPE>$qrytype</QRYTYPE>
<OPERID>$operatorid</OPERID>
</tagset>
</pkg>
</request>
<response>
<pkg>
<crset name="invoke_resp_cr">
<rows cnt="RESULTCRSET.Rows()">
<cols cnt="5">
<COL_0>RESULTCRSET[*,0]</COL_0>
<COL_1>RESULTCRSET[*,1]</COL_1>
<COL_2>RESULTCRSET[*,2]</COL_2>
<expr name="COL_3" handle="DATEFMT" from="RESULTCRSET">
<datetime>RESULTCRSET[$,3]</datetime>
<fromfmt>yyyy-mm-dd hh:mi:ss</fromfmt>
<tofmt>YYYYMMDDHH24MISS</tofmt>
</expr>
<expr name="COL_4" handle="DATEFMT" from="RESULTCRSET">
<datetime>RESULTCRSET[$,4]</datetime>
<fromfmt>yyyy-mm-dd hh:mi:ss</fromfmt>
<tofmt>YYYYMMDDHH24MISS</tofmt>
</expr>
</cols>
</rows>
</crset>
</pkg>
</response>
</invoke>
</opProcessor>
</portal>', 0);