<%--
 @User: m00227318
 @De: 2012/09/20
 @comment: 显示积分兑换电子券的信息
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
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript">
//防止页面重复提交
var submitFlag = 0;

function goback(curmenu) 
{
	//已经选择了或者点击了返回，等待应答，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = curmenu;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }login/backForward.action";
		document.actform.submit();
	}			
}

// 处理键盘事件
document.onkeydown = keyDown;
function keyDown(e)
{
	//8、32、66、77 更正
    //82、220 返回
    //13、89、221 确认
    //80 打印
    //85 上一页
    //68 下一页
    
	//接收键盘码
	var key = GetKeyCode(e);
     
    //8:backspace 32:空格 B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
    //82:R 220:返回
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />") ;
   		return ;
	}
}
<!-- 弹出窗 -->
openWindow = function(id){                       
        wiWindow = new OpenWindow(id,708,392);//打开弹出窗口例子                                        
        }
<!-- 弹出窗结束 -->

</script>
</head>
	<body scroll="no">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<!-- 活动编码 -->
		    <input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
			<!-- 档次编码 -->
			<input type="hidden" id="dangciId" name="dangciId" value='<s:property value="#request.dangciId" />'/>
			<!-- 奖品编码串 -->
		    <input type="hidden" id="prefRewardId" name="prefRewardId" value='<s:property value="#request.prefRewardId" />'/>
		    <!-- 要兑换的积分数 -->
		    <input type="hidden" id="exchangeScore" name="exchangeScore" value=""/>
		    
		    <input type="hidden" id="changeType" name="changeType" value='<s:property value="#request.changeType" />'/>
		    
		    <input type="hidden" id="score_value" name="score_value" value='<s:property value="#request.score_value" />'/>
		    <input type="hidden" id="score_title" name="score_title" value='<s:property value="#request.score_title" />'/>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="prefrwd_list m0auto">
				    
					<div class="blank60 tc mt20 fs24 lh2" >
					    <s:property value="score_title"/>
					</div>
					<s:if test="scoreList==null">
						<div class="blank60 tc mt20 fs24 lh2">
							<s:property value="scoreWaring" />
						</div>
					</s:if>
					<ul>
						<s:iterator value="scoreList" status="sl">
					    <li >
							    <a  class="awrap" href="javascript:void(0)" onclick = "selectPrefRwd('<s:property value="score"/>','','<s:property value="description"/>'); return false;">
					    
							    <h2 >
							   
							    <s:property value="description"/>
							   
							    </h2>
							    </a>
						</li>
						</s:iterator>
						<li >
						<a  class="awrap" href="javascript:void(0)" onclick = "selectPrefRwd(Math.floor(<%=customerInfor.getSubscore()%>/100*100),'all','全部'); return false;">
								<h2 >
								
								全部积分
							
								</h2>
									</a>
						</li>
						<s:if test="scoreList!=null" >
						<li >
						<a  class="awrap" href="javascript:void(0)" onclick = "inputscore(); return false;">
								
								<h2 >
								自定义
								
								</h2>
								</a>
						</li>
						</s:if>
				    </ul>
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
	    			<a title="确认提交积分兑换电子券" href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on';" onmouseup="this.className='bt4';wiWindow.close();commitPrefRwd();">确认</a> 
	    			<a title="" href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close();">取消</a> 
	    		</div>
			</div>
			
			<!-- 弹出窗，出错信息 -->
			<div class="openwin_tip" id="openWin_err">
			    <div class="bg"></div>
				<div class=" blank60"></div>
			    <div class="  blank10n"></div>
				<p class="fs28 lh2" style="padding-left:50px; padding-right:50px" id="winText2" name="winText2">
					<span class="yellow">积分不够！</span>
					<br/>
					<span>您当前的可用积分为:</span>
					<span class ="yellow"><%=customerInfor.getSubscore() %></span>
					<span>，请您选择积分较少的兑换业务！</span>
					<br/>
					<span></span>
				</p>
	  			<div class="tc">
					<div class=" clear "></div>
					<div class=" blank30 "></div>
                    <a title="积分兑换电子券出错" href="javascript:void(0)" class=" bt4 tc " onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确认</a> 
	    		</div>
			</div>
			
			<!-- 弹出窗，当前可用积分小于100 -->
			<div class="openwin_tip" id="openWin_err_2">
			    <div class="bg"></div>
				<div class=" blank60"></div>
			    <div class="  blank10n"></div>
				<p class="fs28 lh2" style="padding-left:50px; padding-right:50px" id="winText2" name="winText2">
					<span class="yellow">积分不够！</span>
					<br/>
					<span>您当前选择的积分:</span>
					<span class ="yellow">小于<s:property value="score_value"/></span>
					<span>，不能办理积分兑换电子券业务！</span>
					<br/>
					<span></span>
				</p>
	  			<div class="tc">
					<div class=" clear "></div>
					<div class=" blank30 "></div>
                    <a title="积分兑换电子券出错" href="javascript:void(0)" class=" bt4 tc " onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确认</a> 
	    		</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
	<script type="text/javascript">
    //选择xxx分兑换X元，提交业务，入参:用户兑换的积分数，兑换的钱数
	function selectPrefRwd(commitScore,type,scoreTitle)
    {
        if ((type==null||""==type)&&commitScore < <s:property value="score_value"/>)
        {
            openWindow("openWin_err_2");
        }
        //用户的当前积分不够用，弹出出错窗口
        else if (<%=customerInfor.getSubscore()%> < commitScore)
        {
            openWindow("openWin_err");
        }

        //用户确认提交信息
        else
        {	
       		 if(type==null||""==type)
       		 {
       		  var msg = '<br/>您确认使用<span class="yellow">'+scoreTitle+'!</span><br/>点击"确认"提交，“取消”返回。';
       		 }
       		 else
       		 {
       		  var msg = '<br/>您确认使用<span class="yellow">' + commitScore + '积分</span>'+scoreTitle+'兑换电子券!<br/>点击"确认"提交，“取消”返回。';
         	 }
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
	
	//弹出用户自定义输入积分页面
	function inputscore()
	{
		if (<%=customerInfor.getSubscore()%> < <s:property value="score_value"/>)
        {
            openWindow("openWin_err_2");
        }
        else
        {
	    	//已经选择了或者点击了返回，等待应答，不再执行任何操作
	    	if (submitFlag == 0)
	    	{
		    	submitFlag = 1;
		    
		    	document.actform.target = "_self";
		    	document.actform.action = "${sessionScope.basePath }scoreExECoup/inputScore.action";
				document.actform.submit();
			}
		}
	}
	</script>
</html>
