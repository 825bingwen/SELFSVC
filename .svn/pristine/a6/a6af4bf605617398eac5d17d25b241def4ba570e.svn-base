<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
			<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
				<META HTTP-EQUIV="Expires" CONTENT="0">
					<link href="${sessionScope.basePath }css/reset.css" type="text/css"
						rel="stylesheet" />
					<link href="${sessionScope.basePath }css/style.css" type="text/css"
						rel="stylesheet" />
					<script type="text/javascript"
						src="${sessionScope.basePath }js/public.js">
</script>
					<script type="text/javascript"
						src="${sessionScope.basePath }js/script.js">
</script>
					<script type="text/javascript"
						src="${sessionScope.basePath }js/dialyzer.js">
</script>
					<script type="text/javascript"
						src="${sessionScope.basePath }jsp/customize/hub/js/romoveAclick_Hub.js">
</script>
					<script language="javascript">
//防止重复提交
var submitFlag = 0;

//82、220 返回		
document.onkeydown = pwdKeyboardDown;

function pwdKeyboardDown(e) {
	var key = GetKeyCode(e);

	//更正
	if (key == 77) {
		preventEvent(e);
	}

	if (!KeyIsNumber(key)) {
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
					return;
				}
				//确认
				else if (key == 13 || key == 89 || key == 221)
				{
					doSub();
				}			
			}
			
			function goback(menuid)
			{
<%--				if (submitFlag == 0)--%>
<%--				{--%>
<%--					submitFlag = 1;--%>
<%--					--%>
<%--					document.getElementById("curMenuId").value = menuid;--%>
<%--				--%>
<%--					document.dutyInfoForm.target = "_self";--%>
<%--					document.dutyInfoForm.action = "${sessionScope.basePath }hubprodinstall/ruleConfirm.action";--%>
<%--					document.dutyInfoForm.submit();--%>
<%--				}--%>
			}
						
			function doSub() 
			{
				if (submitFlag == 0) 
				{
					submitFlag = 1;	//提交标记
					
					document.dutyInfoForm.target = "_self";
					document.dutyInfoForm.action = "${sessionScope.basePath }hubprodinstall/ReadIdCard.action";
					document.dutyInfoForm.submit();
				}
			}
			
			
			
   
		</script>
	</head>

	<body scroll="no" onload="">
		<form name="dutyInfoForm" method="post" target="_self">


			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>
							选号开户流程
						</h2>
						<div class="blank10"></div>
						<a href="#" class="on">1. 入网协议确认</a>
						<a href="#">2. 身份证信息记取</a>
						<a href="#">3. 产品选择</a>
						<a href="#">4. 号码选择</a>
						<a href="#">5. 费用确认</a>
						<a href="#">6. 开户缴费</a>
						<a href="#">7. 取卡打印发票</a>
					</div>

					<div class="b712">
						<div class="bg bg712"></div>
						<div class="blank20"></div>
						<div class="p40">
							
							<table id="pags" width="100%" class="" align="center" border="0" style="padding: 0px;">
								<tr>
									<td width="60%" class="">
										<p class=" tit_info1" style="line-height:30px;">
										<br />
								尊敬的客户：
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;您好！感谢您成为我们中国移动通信集团湖北有限公司的客户。在您申办业务前，请认真阅读以下协议：
								<br />
								
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								湖北移动自助终端入网电子协议
								<br />
								
								友好提示：<br />
								◆甲方应具有完全的民事行为能力。无民事行为能力或限制民事行为能力的甲方入网时应经过其监护人的同意后办理。<br />
								◆在办理之前，请甲方或其代理人仔细阅读本协议各条款，如有疑问请及时咨询。<br />
								◆甲方或其代理人在办理后即视为完全理解并同意接受本协议的全部条款。<br />
								◆甲方所需的业务、办理手续以及资费标准请参见乙方的相关业务说明。 根据《中华人民共和国合同法》、《中华人民共和国电信条例》及其他有关法律、法规的规定，甲乙双方在平等、自愿、公平、诚实、信用的基础上，就
								</p>
									</td>
								</tr>
								<tr>
									<td width="60%" class="">
										<p class=" tit_info1" style="line-height: 30px;">
											移动电话网络服务的有关事宜，达成协议如下：
											<br />
											一、服务内容和资费标准
											<br />
											1、乙方在现有技术条件下的移动电话网络覆盖范围内，为甲方有偿提供移动通信服务；其中，乙方在签订有自动漫游协议的国家和地区的电信运营商网络覆盖范围内为甲方提供国际漫游服务。
											<br />
											2、甲方可自主选择乙方提供的资费方案及所包含的默认服务；甲方申请开通默认服务以外的其他通信服务，应按乙方规定办理手续，并缴纳费用。
											<br />
											3、乙方根据政府主管部门批准或备案的资费标准向甲方收取相关费用。计费周期为自然月，即每月第一日至当月最后一日（由于网络设备产生话单及相关处理会有时延，可能发生月末部分话费计入下月话费中收取的情况）。
											<br />
											二、乙方的义务
											<br />
											1、乙方为甲方提供的移动通信服务应当达到国家的相关标准。
											<br />
											2、乙方通过营业厅、网站及短信等方式向甲方公布并提示服务项目、服务时限、服务范围及资费标准等内容。
										</p>
									</td>
								</tr>
								<tr>
								<td width="60%" class="">
										<p class=" tit_info1" style="line-height: 30px;">
											3、乙方应向甲方提供方便的业务办理、业务咨询渠道，包括但不限于营业厅、服务热线、服务网站、短信等。
											<br />
											4、乙方有义务采取公布监督电话等形式受理甲方投诉，并在接到投诉之日起15日内答复甲方。
											<br />
											5、乙方对移动电话原始话费数据及信息服务计费原始数据保留期限为5个月(系统产生用户话单当月起后5个月，不含当月)，对用户提出异议的话单，应保存至异议解决为止。
											<br />
											6、若甲方对乙方收取的话费存有异议，乙方有责任进行调查、解释，并告知甲方核实处理的结果。
											<br />
											7、乙方不得侵害甲方的通信自由和通信秘密，对甲方的客户资料负有保密义务。但根据法律法规规定，乙方应配合公安机关、人民检察院、国家安全机关及依据法律法规有权进行调查的其他部门的工作要求。
											<br />
											8、乙方应免费向甲方提供通话所在地（仅限国内）火警119、匪警110、医疗急救120、交通事故报警122等公益性电话接入服务。
										</p>
									</td>
								</tr>
								<tr>
								<td width="60%" class="">
										<p class=" tit_info1" style="line-height: 30px;">
										9、在甲方欠费的情况下，如甲方在约定的期限内补齐费用并申请开机的，乙方在收到甲方费用时起24个小时内为甲方恢复移动通信服务。
										<br />
										10、乙方应向甲方提供7×24小时话费查询服务，查询方式包括但不限于服务热线、服务网站、短信等。
										<br />
										11、乙方检修线路、设备搬迁、工程割接、网络及软件升级等可预见的原因可能影响甲方使用的，应提前72小时通知甲方，通知方式包括但不限于短信、媒体公告。
										<br />
										12、甲方向乙方申告移动电话通信障碍（指交换设备或传输线路原因造成通信中断等现象，不包括网络覆盖、互联互通故障和终端设备故障），乙方应自接到申告之时起48小时修复或者调通。
										<br />
										13、乙方应在承诺时限内为甲方开通其申请的服务(双方约定超出此时限的除外)，乙方未及时开通的，应减免甲方自申请之日至服务开通期间的该项服务的月功能费。
										<br />
										三、甲方的义务
									</p>
									</td>
								</tr>
								<tr>
								<td width="60%" class="">
										<p class=" tit_info1" style="line-height: 30px;">
										1、甲方自助方式入网时若无法提供身份证件资料或复印件，需第一时间设置10086密码，并携带有效证件前往移动营业厅补录身份资料。个人客户的有效证件包括居民身份证、驾驶执照、护照、军官证、武装警察身份证；单位客户有效证件包括营业执照副本原件及复印件、单位介绍信（具有单位公章证明）及经办人有效身份证件原件及复印件。如甲方入网登记资料发生变更，应及时通知乙方。
										<br />
										2、甲方应按照与乙方约定的时间、方式，及时、足额地向乙方支付移动通信费用。过期未交的款项，自付费日期期满后次日起，甲方应按照每日未交款项的3‰（千分之三）向乙方支付违约金。
										<br />
										3、若甲方对乙方收取的移动通信费及代信息服务商收取的信息费存有异议，应在异议话费发生后五个月内向乙方提出(系统产生用户话单当月起后5个月，不含当月)。
										<br />
										4、甲方要求终止提供移动通信服务时，应在结清所有费用后办理退网手续。
										<br />
										四、特殊情况的责任承担
										</p>
									</td>
								</tr>
								<tr>
								<td width="60%" class="">
										<p class=" tit_info1" style="line-height: 30px;">
										1、在下列情况下，乙方有权暂停或限制甲方的移动通信服务，由此给甲方造成的损失，乙方不承担责任：
										<br />
										（1）甲方银行帐户被查封、冻结或余额不足等非乙方原因造成乙方结算时划扣不成功的；
										<br />
										（2）甲方预付费使用完毕而未及时补交款项（包括预付费账户余额不足以划扣下一笔预付费用）的；
										<br />
										（3）甲方使用"先使用，后付费"的费用结算方式时，移动电话费用超过可透支额度的；
										<br />
										（4）甲方突然出现超过自己此前三个月平均通信费用5倍以上通信费用的；
										<br />
										（5）甲方发送违法类信息，或未经接收客户同意发送商业广告信息给他人造成骚扰，引起客户投诉举报的；
										<br />
										2、 甲方名下的移动电话号码逾期未交清费用的，乙方有权暂停甲方通信服务或拒绝甲方以自己名义提出的其他移动通信业务申请，缴费、话费查询除外。
										</p>
									</td>
								</tr>
								<tr>
								<td width="60%" class="">
										<p class=" tit_info1" style="line-height: 30px;">
										3、在甲方欠费时，乙方有权以信函、公告、委托第三方等形式追缴欠费。
										<br />
										4、甲方入网后即获取客户服务密码，服务密码是业务办理的重要凭证，甲方应妥善保管。为方便办理业务，乙方也可根据甲方申请以短信等方式提供随机服务密码，该密码可作为办理业务的临时凭证。凡使用服务密码/随机服务密码定制、变更业务的行为均被视为甲方或甲方授权的行为。
										<br />
										5、因甲方保管不善等原因导致其移动电话被他人非法使用，甲方应及时办理停机手续，并向公安部门报案。乙方应在技术上协助公安部门进行调查，但乙方不承担由此对甲方所造成的不良后果。
							 			<br />
							 			6、因甲方提供的客户资料不详、不实或变更后未通知乙方等原因，使乙方无法将服务（如话费单据等）提供给甲方，乙方不承担由此对甲方所造成的不良后果。
										<br />
										7、因不可抗力（包括但不限于如地震、山洪、雷电及其他自然灾害、电磁干扰、战争暴乱、政府行为等）而使本协议部分或全部不能履行，双方互不承担违约责任。
										</p>
									</td>
								</tr>
								<tr>
								<td width="60%" class="">
										<p class=" tit_info1" style="line-height: 30px;">
										8、一方违约给对方造成损失的，应当依法承担赔偿责任，但任何一方应当承担的赔偿损失的责任范围不包括对方未实现的预期利润或利益、商业信誉的损失、数据的丢失、第三方损失以及其他间接损失。
										<br />
										五、协议的变更、转让与终止
										<br />
										1、甲方办理各类业务所签署的表单、乙方以公告等书面形式公开做出的服务承诺均为本协议的补充协议，与本协议冲突部分以补充协议为准，补充协议中未约定部分以本协议为准。
										<br />
										2、乙方承诺资费方案有效期为一年（双方特殊约定的除外）。乙方有权在有效期截至后修改资费方案。如需修改，乙方应在有效期届满前一个月通知甲方。如无需修改，则原资费方案顺延一年，顺延次数不限。
										<br />
										3、甲方将协议项下的权利义务转让给第三人（过户）后，由第三人与乙方重新签订移动通信服务协议，转受让双方到场付清已出账的通信费用后本协议自动终止。因甲方私自转让而造成的欠费、停机等后果，由甲方自行承担。
										<br /> 
										4、因技术进步，乙方为提升服务质量对移动电话网络进行整体换代升级而导
										</p>
									</td>
								</tr>
								<tr>
								<td width="60%" class="">
										<p class=" tit_info1" style="line-height: 30px;">
										致移动通信服务无法继续履行的，乙方应至少提前90日告知甲方，并提供合理的解决方案。
										<br />
										5、下列情况下乙方有权解除协议，收回号码，终止提供服务。由此给甲方造成的损失，乙方不承担责任，并有权向甲方追讨欠费：
										<br />
										(1)甲方（包括代理人）提供的有效证件虚假不实；
										<br />
										(2)移动电话被用于违法犯罪活动或不当用途（有损乙方或相关第三方利益）；
										<br />
										(3)乙方收到国家有关部门发文要求停止为甲方提供通信服务；
										<br />
										(4)甲方逾期60日仍未交纳费用；
										<br />
										6、在法定终止条件或约定终止条件具备时，本协议终止。乙方继续保留向甲方追缴所欠费用的权利，并有权收回号码重新启用。
										<br />
										六、其他约定
										<br />
										1、信息服务提供商通过乙方的移动网络平台提供的增值电信应用服务，甲方可自愿订购或退订。
										</p>
									</td>
								</tr>
								<tr>
								<td width="60%" class="">
										<p class=" tit_info1" style="line-height: 30px;">
										2、如甲方通过乙方的通信网络定制、收发与乙方有合作关系的信息服务提供商所提供的各类信息，甲方同意乙方根据信息服务提供商的授权，向甲方代为收取信息费。
										<br />
										3、甲方使用信息服务提供商提供的增值电信应用服务的资费标准由信息服务提供商公布。若甲方对收取的服务费用有异议，可在乙方协助下与信息服务提供商协商解决。
										<br />
										本协议项下发生的争议，双方可协商解决，协商不成的，双方可向电信管理部门申诉或向消费者协会等有关部门投诉，或通过法律途径解决。
										<br />
										4、乙方在使用自助终端时，点击同意标明接受本协议各项条款约定，点击退出标明不接受本协议，系统将中止服务。
										<br />
										5、客户点击同意本协议后，本协议即生效，甲乙双方成立事实上的合同关系，效力于纸质签署协议一致。
										
								<br />
										&nbsp;&nbsp;&nbsp;&nbsp;如果您完全接受以上的所有条款，请按[同意]继续受理业务。如果您不同意以上条款，请按[首页]或[退出]，本系统将自动退至主界面。
										</p>
									</td>
								</tr>
								<tr>
								<td width="60%" class="">
										<p class=" tit_info1" style="line-height: 30px;">
										&nbsp;&nbsp;&nbsp;&nbsp;您在申请业务后，如需要查询，请您浏览自助终端的相关栏目或者拨打10086查询。
										</p>
									</td>
								</tr>
							</table>
							
							<div class="btn_box tc">
								<span class=" mr10 inline_block " ><a href="#" id="pagup"
									class="btn_bg_146" onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';upPage();">上一页</a>
								</span>
								<span class=" mr10 inline_block " ><a href="#" id="pagnext"
									class="btn_bg_146" onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';nextPage();">下一页</a>
								</span>
								<span class=" mr10 inline_block "><a href="#" 
									class="btn_bg_146" onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';"
									onclick="doSub();return false;">同意</a>
								</span>
							</div>
						</div>
					</div>
					
				</div>
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
<script type="text/javascript">
removeAclick();
var numPage,totalPage;
var star = 0;
totalPage = document.getElementById("pags").getElementsByTagName("tr");
numPage=totalPage.length;
onload = function(){
	toPag(0);
	document.getElementById("pagup").style.display='none';
}
function toPag(num){
	for(var i=0;i<numPage;i++){
		totalPage[i].style.display='none';
		if(num==i){
			totalPage[i].style.display='block';
		}
	}
}
function upPage(){
	star = star-1;
	toPag(star);
	if(star<=0){
		document.getElementById("pagup").style.display='none';
	}else{
		document.getElementById("pagup").style.display='block';
		document.getElementById("pagnext").style.display='block';
	}
}
function nextPage(){
	star = star+1;
	toPag(star);
	if(star==0){
		document.getElementById("pagup").style.display='none';
	}else{
		document.getElementById("pagup").style.display='block';
	}
	if(star>=numPage-1){
		document.getElementById("pagnext").style.display='none';
	}else{
		document.getElementById("pagnext").style.display='block';
	}
}
</script>
</html>
