<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
</head>

<div class="b257 ">
	<div class="bg bg257"></div>
	<h2>充值流程</h2>
	<div class="blank10"></div>
	<s:if test="1 != chargeLogVO.payType">
		<a id="highLight1" href="javascript:void(0)">1. 输入手机号码</a> 
		<a id="highLight2" href="javascript:void(0)">2. 选择支付方式</a>
		<a id="highLight3" href="javascript:void(0)">3. 支付</a>
		<a id="highLight4" href="javascript:void(0)">4. 完成</a>
	</s:if>
	<s:elseif test="1 == chargeLogVO.payType">
		<a id="highLight1" href="javascript:void(0)">1. 输入手机号码</a> 
		<a id="highLight2" href="javascript:void(0)">3. 选择支付方式</a>
		<a id="highLight3" href="javascript:void(0)">3. 选择金额</a>
		<a id="highLight4" href="javascript:void(0)">4. 免责声明</a> 
		<a id="highLight5" href="javascript:void(0)">5. 插入银联卡</a>
		<a id="highLight6" href="javascript:void(0)">6. 输入密码</a>
		<a id="highLight7" href="javascript:void(0)">7. 完成</a>
	</s:elseif>
</div>