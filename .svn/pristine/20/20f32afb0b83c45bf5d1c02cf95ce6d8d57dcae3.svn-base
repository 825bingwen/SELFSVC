<%--
 @User: m00227318
 @De: 2012/09/20
 @comment: 显示自定义要兑换的积分数
 @remark: create m00227318 2012/09/14 eCommerce V200R003C12L09  OR_huawei_201209_33
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<script type="text/javascript" src="${sessionScope.basePath }js/net.js"></script>
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
			
		//确认
		if (key == 13 || key == 89 || key == 221) 
		{
			selectScore();
			return;
		}
		//返回
		else if (key == 82 || key == 220) 
		{
			goback("<s:property value='curMenuId' />");
			return;
		}
		//更正
		else if (key == 8 || key == 32 || key == 66 || key ==77)
		{
			var etarget = getEventTarget(e);
			if (etarget.type == "text" || etarget.type == "password") 
			{
				etarget.value = backString(etarget.value);
			}
		}			
	}

	function MoveLast(e) 
	{
		var etarget = getEventTarget(e);
		var r = etarget.createTextRange();
		r.moveStart("character", etarget.value.length);
		r.collapse(true);
		r.select();
	}

	function goback(menuid)
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
				
			document.getElementById("curMenuId").value = menuid;
						
			document.forms[0].target = "_self";
			document.forms[0].action = "${sessionScope.basePath }scoreExECoup/getPrefRewardList.action";
			document.forms[0].submit();
		}
	}
	
	<!-- 弹出窗 -->
openWindow = function(id){                       
        wiWindow = new OpenWindow(id,708,392);//打开弹出窗口例子                                        
        }
<!-- 弹出窗结束 -->
	
</script>
</head>
	<body scroll="no" onload="document.getElementById('exScore').focus();">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<input type="hidden" id="changeType" name="changeType" value='<s:property value="#request.changeType" />'/>
			
			<!-- 活动编码 -->
		    <input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
			<!-- 档次编码 -->
			<input type="hidden" id="dangciId" name="dangciId" value='<s:property value="#request.dangciId" />'/>
			<!-- 奖品编码串 -->
		    <input type="hidden" id="prefRewardId" name="prefRewardId" value='<s:property value="#request.prefRewardId" />'/>
		    <!-- 要兑换的积分数 -->
		    <input type="hidden" id="exchangeScore" name="exchangeScore" value=""/>
		    
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div style="padding-left:100px; padding-top:10px">				
				    <div class="b712" style="padding-left:100px; padding-top:10px">
						<div class="bg bg712" style="margin-left:120px; margin-top:10px"></div>
    					<div class="blank20"></div>
    					<div class="p40 pr0" style="padding-left:70px">
    						<p class="tit_info"><span class="bg"></span>请选择您要兑换的积分数：<span class="yellow"><s:if test="score_value!=0">（最少兑换积分为：<s:property value="score_value"/> )</s:if> </span></p>
    						<p class="tit_info"><span >您当前可用积分值：<%=customerInfor.getSubscore()%></span></p>
    						<div class="line w625"></div>
    						<div class="blank10"></div>
    						<div class="custom_money"><span class="pl40 fs20 fl lh48">请输入兑换积分值：</span>
          						<input type="text" id="exScore" name="exScore" class="text1 fl" onfocus="MoveLast(event);" value="" />
        					</div>
        					<div class="keyboard_wrap mt5 pl55 clearfix">
        						<div class="fl btn_back_box" style="padding-top:200px;">
        							<p style="padding-left:10px; font-size:16px;">&nbsp;&nbsp;</p>        							
        						</div>
        						<div class="numboard numboard_big numboard_small2 fl" id="numBoard">
        							<div class=" numbox clearfix">
             							<div class="clearfix">
             								<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
           								</div>
            							<div class="clearfix"> 
            								<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
            							</div>
            							<div class="nleft"> 
            								<a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)" name="functionkey">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)" name="functionkey">#</a> 
            							</div>
            							<div class="nright"> 
            								<a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="selectScore();return false;">1</a> 
            							</div>
            							<div class="blank10"></div>
          							</div>
        						</div>
        						<script type="text/javascript">
                				var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var numBoardText = document.getElementById('exScore');
								for (i = 0; i < numBoardBtns.length; i++)
								{
					    			if (!numBoardBtns[i].className) 
					    			{
					    				numBoardBtns[i].className = '';
					    			}
					    			//// firfox下获取name属性值用getAttribute("name"),而不能直接用obj.name
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
									else
									{								
										if (v == 0 && numBoardText.value.length == 0)
						  				{
						  				
						  				}
						  				else
						  				{
						  					numBoardText.value += v;
						  				}																			
									}
									
									var r = numBoardText.createTextRange(); 
									r.collapse(false); 
									r.select();
								}
		
              					</script>
        					</div>	
    					</div>
					</div>
		        </div>
		    </div>
		    <!-- 弹出窗，确认选择信息 -->	
			<div class = "openwin_tip" id="openWin_conf" style="width:708px; height:392px;">
			    <div class="bg"></div>
				<div class=" blank30"></div>
				<p class="fs24 lh2" style="padding-left:70px;" id="winText" name="winText">
				</p>
	  			<div class="tc">
					<div class=" clear "></div>
					<div class=" blank30 "></div>
	    			<a title="确认提交积分兑换电子券" href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();commitPrefRwd();">确认</a> 
	    			<a title="" href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close();changValue(-2);">取消</a> 
	    		</div>
			</div>
			
			<!-- 弹出窗，出错信息 -->
			<div class="openwin_tip" id="openWin_err">
			    <div class="bg"></div>
				<div class=" blank60"></div>
			    <div class="  blank10n"></div>
				<p class="fs28 lh2" style="padding-left:50px; padding-right:50px" id="winText2" name="winText2">
					<span class="yellow"><font color="red" size="5">出错啦！</font></span>
					<br/>
					<span><font color="red" size="5">输入积分大于当前可用积分！请重新输入！</font>
					</span>
					<br/>
					<span></span>
				</p>
	  			<div class="tc">
					<div class=" clear "></div>
					<div class=" blank30 "></div>
                    <a title="积分兑换电子券出错" href="javascript:void(0)" class=" bt4 tc " onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();changValue(-2);">确认</a> 
	    		</div>
			</div>
			
			<!-- 弹出窗，小于最小兑换值信息 -->
			<div class="openwin_tip" id="openWin_err_s">
			    <div class="bg"></div>
				<div class=" blank60"></div>
			    <div class="  blank10n"></div>
				<p class="fs28 lh2" style="padding-left:50px; padding-right:50px" id="winText2" name="winText2">
					<span class="transcact_ok"><font color="red" size="5">出错啦！</font> </span>
					<br/>
					<span><font color="red" size="5">允许最少兑换的积分值为：<s:property value="score_value"/></font>
					</span>
					<br/>
					<span></span>
				</p>
	  			<div class="tc">
					<div class=" clear "></div>
					<div class=" blank30 "></div>
                    <a title="积分兑换电子券出错" href="javascript:void(0)" class=" bt4 tc " onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();changValue(-2);">确认</a> 
	    		</div>
			</div>
			
		    <%@ include file="/backinc.jsp"%>	
		</form>
    </body>
    <script type="text/javascript">
    //点击确认
	function selectScore()
	{
	    var commitScore = document.getElementById("exScore").value;
	    //对金额进行判断
		//if (parseInt(commitScore) <= 0)
		//{			 	
			// return;
		//}
		
		//判断自定义的金额是否小于指定的最小兑换金额
		if (<s:property value="score_value"/> > 0&&commitScore < <s:property value="score_value"/> )
		{
		   openWindow("openWin_err_s");
        }
		else if ((<%=customerInfor.getSubscore()%> < commitScore) )
		{
		   //出错，输入积分不是100的倍数或者大于当前可用积分
		   openWindow("openWin_err");
        }
        //用户确认提交信息
        else
        {
            var msg = '<br/>您确认使用<span class="yellow">' + commitScore + '</span>积分兑换电子券。<br/>点击"确认"提交，“取消”返回。';

            document.getElementById('winText').innerHTML = msg;
            document.getElementById('exchangeScore').value = commitScore;
            openWindow("openWin_conf");
        }	
	}	
	
	//提交业务
    function commitPrefRwd()
    {
        //已经选择了或者点击了返回，等待应答，不再执行任何操作
	    if (submitFlag == 0)
	    {
	        submitFlag = 1;
	        	        
	        openRecWaitLoading();
	        
            setTimeout(
		    	function(){
            		document.actform.target = "_self";
            		document.actform.action = "${sessionScope.basePath }scoreExECoup/commitPrefRewardList.action";
            		document.actform.submit();
            		}, 500);
        }
    }
    </script>
</html>
				    