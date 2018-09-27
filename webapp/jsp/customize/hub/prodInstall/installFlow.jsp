<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript"
						src="${sessionScope.basePath }jsp/customize/hub/js/romoveAclick_Hub.js">
</script>
		<style type="text/css">
			.tb_blue_08,.tb_blue_08 td ,.tb_blue_08 th,.tb_num{ border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px; color:#fff; height:30px;}
			.tb_blue_08 th{ background:#056e99; }	
		</style>
		<script type="text/javascript">
		//防止页面重复提交
		var submitFlag = 0;
		
		document.onkeydown = pwdKeyboardDown;
		
		function pwdKeyboardDown(e)
		{	
			var key = GetKeyCode(e);
			
			//更正
			if (key == 77) 
			{
				preventEvent(e);
			}
			
			if (!KeyIsNumber(key))
			{
				return false;//这句话最关键
			}
		}
		
		function KeyIsNumber(KeyCode) 
		{
    		//只允许输入0-9
    		if (KeyCode >= 48 && KeyCode <= 57)
    		{
    			return true;
    		}
    		
    		return false;
		}			
		
		document.onkeyup = pwdKeyboardUp;
		
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);

			//返回
			if (key == 82 || key == 220) 
			{
				goback("<s:property value='curMenuId' />");
			}			
		}
		
		// 下一页
		function nextPage(linkURL)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				document.forms[0].target = "_self";
				document.forms[0].action = linkURL;
				document.forms[0].submit();
			}
		}
		
		function confirmFee()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				var url = "${sessionScope.basePath }hubprodinstall/installChargeType.action";
				document.forms[0].action = url;
				document.forms[0].target = "_self";
				document.forms[0].submit();
			}
		}
		</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
		<input type="hidden" id="telnum" name="telnum" value="<s:property value="telnum" />"/>
		<input type="hidden" id="brand" name="brand" value="<s:property value="brand" />"/>
		<input type="hidden" id="mainprodname" name="mainprodname" value='<s:property value="mainprodname" />'/>
		<input type="hidden" id="prodtempletid" name="prodtempletid" value="<s:property value="prodtempletid" />"/>
		<input type="hidden" id="receptionFee" name="receptionFee" value="<s:property value="receptionFee" />"/>
		<!-- 每页条数 -->
		<input type="hidden" name="pagesize" id="pagesize" value='<s:property value="#request.pagesize" />'/>
		<input type="hidden" name="totalsize" id="totalsize" value='<s:property value="#request.totalsize" />'/>
		<input type="hidden" name="pagenum" id="pagenum" value='<s:property value="#request.pagenum" />'/>
		<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">			
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>开户入网流程：</h2>
      					<div class="blank10"></div>
      					<a title="入网协议确认" href="#">1. 入网协议确认</a>
      					<a title="身份证信息认证" href="#">2. 身份证信息记取</a>
      					<a title="产品选择" href="#">3. 产品选择</a>  
      					<a title="号码选择" href="#">4. 号码选择</a> 
      					<a title="费用确认" href="#" class="on">5. 费用确认</a>
      					<a title="开户缴费" href="#">6. 开户缴费</a>
      					<a title="取卡打印发票" href="#">7. 取卡打印发票</a>
					</div>
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank30"></div>
					<div class="p40">
						<p class=" tit_info">
							<span class="bg"></span>费用明细列表<span class="yellow"></span>
						</p>
						<div class="blank10"></div>
					
					<table id="infoTab" class="tb_blue_08" width="100%">
								<tr>
									<td width="25%" class="tc">号码</td>
									<td width="25%" class="tc"><span class="yellow"><s:property value="#request.telnum" /></span></td>
									<td width="25%" class="tc">品牌</td>
									<td width="25%" class="tc">
										<span class="yellow">
											<s:if test="#request.brand == 'BrandGotone'">
													全球通
											</s:if>
											<s:elseif test="#request.brand == 'BrandMzone'">
													动感地带
											</s:elseif>
											<s:else>
													神州行
											</s:else>
										</span>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tc">产品</td>
									<td width="75%" class="tc" colspan='3'><span class="yellow"><s:property value="#request.mainprodname" /></span></td>
								</tr>
					</table>
					<br/>
					<table id="feeTab" width="100%" class="tb_blue_08" >
	                  <tr>
	                    <th scope="col">费用名称</th>
	                    <th scope="col">金额(元)</th>
	                    <th scope="col">数量</th>
	                    <th scope="col">费用类型</th>
	                  </tr>
	                  <s:iterator value="itemlist" id="item" status="st">
		                  <tr>
			                    <td><s:property value="#item.itemname" /></td>
			                    <td>
			                    <s:property value="#item.itemfee" /></td>
			                    <td><s:property value="#item.itemnum" /></td>
			                    <td>
				                    <s:if test="#item.itemtype == '预存款'">
											预存款
									</s:if>
									<s:elseif test="#item.itemtype != null && #item.itemtype != ''">
											正常收费
									</s:elseif>
			                    </td>
		                  </tr>
	                  </s:iterator>
	                </table>
	                <br/>
	                <table id="feeTab" width="100%" class="tb_blue_08" >
	                  <tr>
	                    <th scope="col">装机预存(元)</th>
	                    <th scope="col">最低消费(元)</th>
	                    <th scope="col">销售品牌</th>
	                    <th scope="col">可用资费</th>
	                  </tr>
	                  <tr>
	                    <th scope="col"><s:property value="#request.installFee" /></th>
	                    <th scope="col"><s:property value="#request.lowFee"/></th>
	                    <th scope="col"><s:property value="#request.sallBrand"/></th>
	                    <th scope="col"><s:property value="#request.haveFee"/></th>
	                  </tr>
	                  </table>
					</div>	
					<!-- 分页 -->
					<pg:paginator recordsCount="${totalsize }" pageSize="${pagesize }" page="${page }" linkUrl="hubprodinstall/ckTelSimCard.action" />
					
					<br/>
					<div class="btn_box tc">
						    <span class=" mr10 inline_block "><a id='reckonSpan' href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';" onclick="confirmFee()">费用确认</a></span>
					</div>
				</div>
			</div>	
			<%@ include file="/backinc.jsp"%>	
		</form>
	</body>
		<script type="text/javascript">
removeAclick();
</script>
</html>
