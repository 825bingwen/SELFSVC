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
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js?ver=${jsVersion }"></script>
</head>
<body scroll="no" onload="bodyLoad();">
<form name="actform" method="post">
	<input type="hidden" name="sele_rule" id="sele_rule" value="<s:property value='sele_rule' />" />
	<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
	<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />			
	<input type="hidden" id="regionname" name="regionname" value="<s:property value='regionname'/>" />
	<input type="hidden" id="webserviceFlag" name="webserviceFlag" value="<s:property value='webserviceFlag' />"/>
	<input type="hidden" id="condition" name="condition" value="" />
	
	<%@ include file="/titleinc.jsp"%>
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		
		<div class="pl30">
			<div class="b257 ">
				<div class="bg bg257"></div>
				<h2><%=menuName %>流程</h2>
				<div class="blank10"></div>
				<a href="javascript:void(0);">
					1.选择查询方式
				</a>
				<a href="javascript:void(0);" class="on">
					2.输入查询条件
				</a>
				<a href="javascript:void(0);">
					3.选择号码
				</a>
				<a href="javascript:void(0);">
					4.输入个人信息
				</a>
				<a href="javascript:void(0);">
					5.完成
				</a>
			</div>
		</div>
		
		<div class="b712">
			<div class="bg bg712" id="errorMsg"></div>
			<div class="p40 pr0">
				<div class="blank20"></div>
				<div class="blank20"></div>
				<p class=" tit_info"><span class="bg"></span>输入自定义号码</p>
	            <div class="line w625"></div>
				<div class="blank10"></div>
				<div class="custom_money fl">
					<span class="pl40 fs20 fl lh48">自定义号码：</span>
					<input type="text" name="manuTelnumHead" id="manuTelnumHead" value="***"  maxlength="3" class="text_header_100" onclick="changObj(this, 1);" onfocus="MoveLast(event);" />
					<input type="text" name="manuTelnumContent" id="manuTelnumContent1" value="*" maxlength="1" class="text_content_56" onclick="changObj(this, 2);" onfocus="MoveLast(event);" />
					<input type="text" name="manuTelnumContent" id="manuTelnumContent2" value="*" maxlength="1" class="text_content_56" onclick="changObj(this, 3);" onfocus="MoveLast(event);" />
					<input type="text" name="manuTelnumContent" id="manuTelnumContent3" value="*" maxlength="1" class="text_content_56" onclick="changObj(this, 4);" onfocus="MoveLast(event);" />
					<input type="text" name="manuTelnumContent" id="manuTelnumContent4" value="*" maxlength="1" class="text_content_56" onclick="changObj(this, 5);" onfocus="MoveLast(event);" />
					<input type="text" name="manuTelnumContent" id="manuTelnumContent5" value="*" maxlength="1" class="text_content_56" onclick="changObj(this, 6);" onfocus="MoveLast(event);" />
					<input type="text" name="manuTelnumContent" id="manuTelnumContent6" value="*" maxlength="1" class="text_content_56" onclick="changObj(this, 7);" onfocus="MoveLast(event);" />
					<input type="text" name="manuTelnumContent" id="manuTelnumContent7" value="*" maxlength="1" class="text_content_56" onclick="changObj(this, 8);" onfocus="MoveLast(event);" />
					<input type="text" name="manuTelnumContent" id="manuTelnumContent8" value="*" maxlength="1" class="text_content_56" onclick="changObj(this, 9);" onfocus="MoveLast(event);" />
				</div>
				<div class="blank20"></div>
  					<div class=" mt10 pl20 clearfix" style="padding-left:60px;margin-top:40px;">
   					<div class="numboard numboard_big numboard_small2 fl" id="numBoard">
   						<div class=" numbox clearfix">
       						<div class="clearfix">
       							<a href="javascript:void(0)">1</a>
       							<a href="javascript:void(0)">2</a>
       							<a href="javascript:void(0)">3</a> 
       							<a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
     						</div>
       						<div class="clearfix"> 
       							<a href="javascript:void(0)">4</a>
       							<a href="javascript:void(0)">5</a>
       							<a href="javascript:void(0)">6</a>
								<a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
       						</div>
       						<div class="nleft"> 
       							<a href="javascript:void(0)">7</a>
       							<a href="javascript:void(0)">8</a>
       							<a href="javascript:void(0)">9</a> 
       							<a href="javascript:void(0)">x</a>
       							<a href="javascript:void(0)">0</a><a href="javascript:void(0)" name="functionkey">#</a> 
       						</div>
       						<div class="nright"> 
       							<a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="doSub();return false;">1</a> 
       						</div>
       						<div class="blank10"></div>
     						</div>
   					</div>
   				</div>
			</div>
		</div>		
	</div>
	<%@ include file="/backinc.jsp"%>
</form>
	<script type="text/javascript">
	var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
	var type = 1;
	var numBoardText = document.getElementById('manuTelnumHead');
	var numBoardText1 = document.getElementById("manuTelnumContent1");
	var numBoardText2 = document.getElementById("manuTelnumContent2");
	var numBoardText3 = document.getElementById("manuTelnumContent3");
	var numBoardText4 = document.getElementById("manuTelnumContent4");
	var numBoardText5 = document.getElementById("manuTelnumContent5");
	var numBoardText6 = document.getElementById("manuTelnumContent6");
	var numBoardText7 = document.getElementById("manuTelnumContent7");
	var numBoardText8 = document.getElementById("manuTelnumContent8");
	
	for (i = 0; i < numBoardBtns.length; i++)
	{
		if (!numBoardBtns[i].className) 
		{
			numBoardBtns[i].className = '';
		}
		// firfox下获取name属性值用getAttribute("name"),而不能直接用obj.name
		if (numBoardBtns[i].getAttribute("name") == 'functionkey')
		{
			continue;
		}  
	
		numBoardBtns[i].onmousedown = function()
		{						 
			this.className = 'on';
		}
		
		numBoardBtns[i].onmouseup = function()
		{					  				
			changValue(0, this.innerHTML);
			
			this.className = '';
					
			if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText1.value == "*" 
					&& numBoardText2.value == "*" && numBoardText3.value == "*" && numBoardText4.value == "*" 
					&& numBoardText5.value == "*" && numBoardText6.value == "*" && numBoardText7.value == "*" 
					&& numBoardText8.value == "*") 
			{
				numBoardText1.focus();
				
				changObj(numBoardText1, 2);
			}
			else if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText1.value != "*" && pangu_getStrLen(trim(numBoardText1.value)) == 1 
					&& numBoardText2.value == "*" && numBoardText3.value == "*" && numBoardText4.value == "*" 
					&& numBoardText5.value == "*" && numBoardText6.value == "*" && numBoardText7.value == "*" 
					&& numBoardText8.value == "*")
			{
				numBoardText2.focus();
				
				changObj(numBoardText2, 3);
			}
			else if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText2.value != "*" && pangu_getStrLen(trim(numBoardText2.value)) == 1 
					&& numBoardText3.value == "*" && numBoardText4.value == "*" && numBoardText7.value == "*" 
					&& numBoardText5.value == "*" && numBoardText6.value == "*" 
					&& numBoardText8.value == "*" && numBoardText1.value != "*")
			{
				numBoardText3.focus();
				
				changObj(numBoardText3, 4);
			}
			else if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText3.value != "*" && pangu_getStrLen(trim(numBoardText3.value)) == 1 
					&& numBoardText4.value == "*" && numBoardText7.value == "*" && numBoardText8.value == "*"  
					&& numBoardText5.value == "*" && numBoardText6.value == "*" && numBoardText1.value != "*"
					&& numBoardText2.value != "*")
			{
				numBoardText4.focus();
				
				changObj(numBoardText4, 5);
			}
			else if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText4.value != "*" && pangu_getStrLen(trim(numBoardText4.value)) == 1 
					&& numBoardText5.value == "*" && numBoardText6.value == "*" && numBoardText7.value == "*"  
					&& numBoardText3.value != "*" && numBoardText2.value != "*" && numBoardText1.value != "*"
					&& numBoardText8.value == "*")
			{
				numBoardText5.focus();
				
				changObj(numBoardText5, 6);
			}
			else if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText5.value != "*" && pangu_getStrLen(trim(numBoardText5.value)) == 1 
					&& numBoardText6.value == "*" && numBoardText8.value == "*" && numBoardText7.value == "*" 
					&& numBoardText4.value != "*" && numBoardText3.value != "*" && numBoardText2.value != "*" 
					&& numBoardText1.value != "*")
			{
				numBoardText6.focus();
				
				changObj(numBoardText6, 7);
			}
			else if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText6.value != "*" && pangu_getStrLen(trim(numBoardText6.value)) == 1 
					&& numBoardText7.value == "*" && numBoardText8.value == "*" && numBoardText5.value != "*" 
					&& numBoardText4.value != "*" && numBoardText3.value != "*" && numBoardText2.value != "*" 
					&& numBoardText1.value != "*")
			{
				numBoardText7.focus();
				
				changObj(numBoardText7, 8);
			}
			else if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText7.value != "*" && pangu_getStrLen(trim(numBoardText7.value)) == 1
					&& numBoardText8.value == "*" && numBoardText6.value != "*" && numBoardText5.value != "*" 
					&& numBoardText4.value != "*" && numBoardText3.value != "*" && numBoardText2.value != "*" 
					&& numBoardText1.value != "*")
			{
				numBoardText8.focus();
				
				changObj(numBoardText8, 9);
			}
		}
	}
	
	// 修改页面上的光标定位
	function changObj(o, t)
	{
		type = t;
		
		o.focus();
		
		// 判断输入框的值是否需要置为"*"
		for(var i = 1; i < 9; i++)
		{
			var inputValue = document.getElementById("manuTelnumContent" + i).value;
			if("" == inputValue)
			{
				document.getElementById("manuTelnumContent" + i).value = "*";
			}
		}
		
		// 将输入框中的*去掉，准备用户的输入条件
		if("*" == o.value)
		{
			o.value = "";
		}
		
		numBoardText = o;
		
		if(t == 1 && (o.value == "***" || o.value == ""))
		{
			o.value = "";
		}
		else if(document.getElementById("manuTelnumHead").value == "")
		{
			document.getElementById("manuTelnumHead").value = "***";
		}
	}	
	
	function changValue(t, v)
	{
		if (t == -1)
		{
			numBoardText.value = numBoardText.value.slice(0, -1);
		}
		else if(t == -2)
		{
			numBoardText.value = "";
		}
		else if (type == 1 && numBoardText.value.length < 3)
		{			
			numBoardText.value += v;																		
		}
		else if (type == 2 && numBoardText1.value.length < 1 && !isNaN(v))
		{
			numBoardText1.value += v;
		}
		else if(type == 3 && numBoardText2.value.length < 1 && !isNaN(v))
		{
			numBoardText2.value += v;
		}
		else if(type == 4 && numBoardText3.value.length < 1 && !isNaN(v))
		{
			numBoardText3.value += v;
		}
		else if(type == 5 && numBoardText4.value.length < 1 && !isNaN(v))
		{
			numBoardText4.value += v;
		}
		else if(type == 6 && numBoardText5.value.length < 1 && !isNaN(v))
		{
			numBoardText5.value += v;
		}
		else if(type == 7 && numBoardText6.value.length < 1 && !isNaN(v))
		{
			numBoardText6.value += v;
		}
		else if(type == 8 && numBoardText7.value.length < 1 && !isNaN(v))
		{
			numBoardText7.value += v;
		}
		else if(type == 9 && numBoardText8.value.length < 1 && !isNaN(v))
		{
			numBoardText8.value += v;
		}
		
		var r = numBoardText.createTextRange(); 
		r.collapse(false); 
		r.select();
	}		
	</script>
	
	<script type="text/javascript">
	// 提交标志
	var submitFlag = 0;
	
	document.onkeyup = pwdKeyboardUp;
	
	function pwdKeyboardUp(e) 
	{
		var key = GetKeyCode(e);
		//确认
		if (key == 13 || key == 89 || key == 221) 
		{
			doSub();
			return;
		}
		//返回
		else if (key == 82 || key == 220) 
		{
			goback("<s:property value = 'curMenuId'/>");
			return;
		}
		// 清除
		else if (key == 77)
		{
			changValue(-2);
			return;
		}	
		
		var numBoardText = document.getElementById('manuTelnumHead');
		var numBoardText1 = document.getElementById("manuTelnumContent1");
		var numBoardText2 = document.getElementById("manuTelnumContent2");
		var numBoardText3 = document.getElementById("manuTelnumContent3");
		var numBoardText4 = document.getElementById("manuTelnumContent4");
		var numBoardText5 = document.getElementById("manuTelnumContent5");
		var numBoardText6 = document.getElementById("manuTelnumContent6");
		var numBoardText7 = document.getElementById("manuTelnumContent7");
		var numBoardText8 = document.getElementById("manuTelnumContent8");
		
		if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText1.value == "*" 
				&& numBoardText2.value == "*" && numBoardText3.value == "*" && numBoardText4.value == "*" 
				&& numBoardText5.value == "*" && numBoardText6.value == "*" && numBoardText7.value == "*" 
				&& numBoardText8.value == "*") 
		{
			numBoardText1.focus();
			
			changObj(numBoardText1, 2);
		}
		else if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText1.value != "*" && pangu_getStrLen(trim(numBoardText1.value)) == 1 
				&& numBoardText2.value == "*" && numBoardText3.value == "*" && numBoardText4.value == "*" 
				&& numBoardText5.value == "*" && numBoardText6.value == "*" && numBoardText7.value == "*" 
				&& numBoardText8.value == "*")
		{
			numBoardText2.focus();
			
			changObj(numBoardText2, 3);
		}
		else if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText2.value != "*" && pangu_getStrLen(trim(numBoardText2.value)) == 1 
				&& numBoardText3.value == "*" && numBoardText4.value == "*" && numBoardText7.value == "*" 
				&& numBoardText5.value == "*" && numBoardText6.value == "*" 
				&& numBoardText8.value == "*" && numBoardText1.value != "*")
		{
			numBoardText3.focus();
			
			changObj(numBoardText3, 4);
		}
		else if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText3.value != "*" && pangu_getStrLen(trim(numBoardText3.value)) == 1 
				&& numBoardText4.value == "*" && numBoardText7.value == "*" && numBoardText8.value == "*"  
				&& numBoardText5.value == "*" && numBoardText6.value == "*" && numBoardText1.value != "*"
				&& numBoardText2.value != "*")
		{
			numBoardText4.focus();
			
			changObj(numBoardText4, 5);
		}
		else if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText4.value != "*" && pangu_getStrLen(trim(numBoardText4.value)) == 1 
				&& numBoardText5.value == "*" && numBoardText6.value == "*" && numBoardText7.value == "*"  
				&& numBoardText3.value != "*" && numBoardText2.value != "*" && numBoardText1.value != "*"
				&& numBoardText8.value == "*")
		{
			numBoardText5.focus();
			
			changObj(numBoardText5, 6);
		}
		else if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText5.value != "*" && pangu_getStrLen(trim(numBoardText5.value)) == 1 
				&& numBoardText6.value == "*" && numBoardText8.value == "*" && numBoardText7.value == "*" 
				&& numBoardText4.value != "*" && numBoardText3.value != "*" && numBoardText2.value != "*" 
				&& numBoardText1.value != "*")
		{
			numBoardText6.focus();
			
			changObj(numBoardText6, 7);
		}
		else if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText6.value != "*" && pangu_getStrLen(trim(numBoardText6.value)) == 1 
				&& numBoardText7.value == "*" && numBoardText8.value == "*" && numBoardText5.value != "*" 
				&& numBoardText4.value != "*" && numBoardText3.value != "*" && numBoardText2.value != "*" 
				&& numBoardText1.value != "*")
		{
			numBoardText7.focus();
			
			changObj(numBoardText7, 8);
		}
		else if (pangu_getStrLen(trim(numBoardText.value)) == 3 && numBoardText7.value != "*" && pangu_getStrLen(trim(numBoardText7.value)) == 1
				&& numBoardText8.value == "*" && numBoardText6.value != "*" && numBoardText5.value != "*" 
				&& numBoardText4.value != "*" && numBoardText3.value != "*" && numBoardText2.value != "*" 
				&& numBoardText1.value != "*")
		{
			numBoardText8.focus();
			
			changObj(numBoardText8, 9);
		}
			
		return true;
	}
	
	// 判断字符串的长度
	function pangu_getStrLen(s) 
	{
		var count = 0;
		var lenByte = s.length;
		for (i = 0; i < lenByte; i++) 
		{
			if (s.charCodeAt(i) > 256) 
			{
				count = count + 2;
			} 
			else 
			{
				count = count + 1;
			}
		}
		
		return count;
	}	
	
	// 自定义号码搜索
	function doSub()
	{
		var condHead = document.getElementById('manuTelnumHead').value;
		while(condHead.length < 3)
		{
			condHead = condHead + "*";
		}
		
		// 拼接自定义号码的查询条件
		document.getElementById("condition").value = condHead + document.getElementById("manuTelnumContent1").value
				+ document.getElementById("manuTelnumContent2").value + document.getElementById("manuTelnumContent3").value
				+ document.getElementById("manuTelnumContent4").value + document.getElementById("manuTelnumContent5").value
				+ document.getElementById("manuTelnumContent6").value + document.getElementById("manuTelnumContent7").value
				+ document.getElementById("manuTelnumContent8").value;
		
		if (submitFlag == 0)
		{
			submitFlag = 1;
	
			document.actform.action = "${sessionScope.basePath}chooseTel/queryNum.action";
			document.actform.submit();
		}
	}
	
	// 返回上一页
	function goback(curmenu) 
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.getElementById("curMenuId").value = curmenu;
			
			document.getElementById("sele_rule").value = "4";
			
			document.actform.action="${sessionScope.basePath }chooseTel/selectRule.action";
			document.actform.submit();				
		}			
	}
	
	// 聚焦
	function bodyLoad()
	{
	    document.getElementById("manuTelnumHead").focus();
	    document.getElementById("manuTelnumHead").value = "";
	}
	
	</script>

</body>
</html>
