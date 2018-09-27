<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	String maxNum = (String) PublicCache.getInstance().getCachedData(Constants.SH_VALUECARD_MAXNUM);
	
	// ��δ���ù����мۿ��������������Ĭ��Ϊ10
	if("".equals(maxNum) || null == maxNum)
	{
		maxNum = "10";
	}
	
	// ʡ����Ϣ
	String provinceId = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<html>
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>		
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
</head>
<body scroll="no">
<form name="actform" method="post">

	<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum' />" />
	<input type="hidden" id="cardType" name="valueCardVO.cardType" value="" />
	<input type="hidden" id="totalFee" name="totalFee" value="" />
	<input type="hidden" id="cardNum" name="valueCardVO.cardNum" value="" />
	<input type="hidden" id="cntTotal" name="valueCardVO.cntTotal" value="" />
	
	<%@ include file="/titleinc.jsp"%>
	
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		
		<h1><span></span>�����ֵ��</h1>
        <div class="pl30">
			<%@include file="/jsp/valuecard/valueCardHeader.jsp" %>
        <div class="b712">
          <div class="bg bg712"></div>
          <div class="blank30"></div>
          <div class="p40">
          	<p class="tit_info"><span class="bg"></span>ѡ����Ŀ����ͣ�</p>
            <div class="num_brandnew">
            	<s:iterator value="typeList" status="st">
                	<a href="javascript:void(0)" id="brand<s:property value='#st.index + 1'/>new" class="" 
                		onclick="changeDictList();this.className='on';if(<s:property value='#st.index'/> == 0){document.getElementById('brand2new').className='';}
                			else{document.getElementById('brand1new').className='';}document.getElementById('cardType').value=<s:property value='dictid' />;return false;">
                		<p class="text"><s:property value="dictname" /></p>
                	</a>
                </s:iterator>
            </div>
            <div class="tabline"></div>
            <div class="blank10"></div>                       
            <div class="b_cardnew " id="btns">
	            <div class="blk_18">
					<a class="LeftBotton" onmousedown="ISL_GoUp_1()" onmouseup="ISL_StopUp_1()" onmouseout="ISL_StopUp_1()" href="javascript:void(0);" target="_self"></a>
	  				<div class="pcont" id="ISL_Cont_1">
    					<div class="ScrCont">
      						<div id="List1_1">
				              <ul>
				              	<s:iterator value="dictList" status="st">
									<li >
					                	<a href="javascript:void(0)" id="dict_<s:property value='#st.index + 1'/>" class=" b_bt2" onmousedown="btnClick(this); return false;" 
					                		onMouseUp="document.getElementById('total').innerHTML=document.getElementById('textId').value*<s:property value='dictid' />+'Ԫ';
					                		window.moneySign=<s:property value='dictid' />; return false;"><s:property value='dictname' />
					                	</a>
					                </li>
				                </s:iterator>
				              </ul>
				            </div>
    					</div>
  					</div>
  					<a class="RightBotton" onmousedown="ISL_GoDown_1()" onmouseup="ISL_StopDown_1()" onmouseout="ISL_StopDown_1()" href="javascript:void(0);" target="_self"></a> </div>
            	</div>
	            <div class="blank10"></div>
	            <div class="b_card2">
	              <div class="bdt1px blank10"></div>
	              <p class="tit_info"><span class="bg"></span>ѡ���ֵ��������һ�����ɹ���<span class="yellow"><%=maxNum %>��</span>��ֵ��</p>
	              <div class="blank20"></div>
	              <ul class="b_cs">
	                <li><a href="javascript:void(0)" class="b_xz fl" name="left" onclick="btnAdd(this); return false;"></a></li>
	                <li >
	                  	<%
	              		if("HUB".equals(provinceId))
	              		{
	              		%>
	                  		<input style="width: 150px;" class=" text4" type="text" id="textId" value="1" readOnly="true" />
	                  	<%
	                  	}
	                  	else if ("SD".equals(provinceId))
	                  	{
	                  	%>
	                  		<input style="width: 160px;" class=" text4" type="text" id="textId" value="1" readOnly="true" />
	                  	<%
	                  	}
	                  	%>
	                </li>
	                <li style="margin-left: 8px;"><a href="javascript:void(0)" class="b_xz2 fl" name="right" onclick="btnAdd(this); return false;"></a></li>
	              </ul>
	            </div>
	            <div class="blank10"></div>
	              <div class="bdt1px blank10"></div>
	            <div class="b_card2">
	              <p class="tit_info"><span class="bg"></span>�����ܽ�<span class="yellow fs28" id="total"><s:property value='dictList[0].dictname' /></span></p>
	            </div>
	            <div class=" tr"> <a class=" btbuy" href="#" onMouseDown="this.className='btbuyon'" onMouseUp="buyCard(this);return false;">����</a> </div>
            </div>
            
          </div>
        </div>
      </div>
      <script type="text/javascript">
      	// �ύ��־
      	var submitFlag = 0;
      
      	// �����мۿ����͵ĸ���
      	document.getElementById("brand1new").className="on";
      	
      	// �����мۿ����͵ĳ�ʼֵ
      	document.getElementById('cardType').value = "<s:property value='typeList[0].dictid' />";
      	
      	// �������ҳ��ĸ�����ʾ
		document.getElementById("highLight2").className = "on";
      	
      	// �����мۿ���ֵ��һ����������ķ���
      	document.getElementById("dict_1").className="b_bt2on";
      	
      	// ����Ĭ�ϵ��мۿ���ֵ���
      	window.moneySign = "<s:property value='dictList[0].dictid' />" ;
      	
      	// ѡ���мۿ����ͺ��޸��мۿ���ֵ��Ϣ����
      	function changeDictList()
      	{
      		var btns=document.getElementById('List1_1').getElementsByTagName('a');
			for(i=0;i<btns.length;i++)
			{
				btns[i].className='b_bt2';
			}
			document.getElementById('dict_1').className='b_bt2on';
			
			document.getElementById('textId').value = '1';
			
			window.moneySign = "<s:property value='dictList[0].dictid' />";
			
			document.getElementById('total').innerHTML = "<s:property value='dictList[0].dictname' />";
      	}
      	
      	// ѡ���мۿ���ֵ��Ϣ
		function btnClick(btn)
		{
			var List1_1=document.getElementById('List1_1').getElementsByTagName('a');
			for(i=0;i<List1_1.length;i++)
			{
				List1_1[i].className='b_bt2';
			}
			btn.className='b_bt2on';
		}
		
		// ���ӹ�����мۿ�����
		function btnAdd(btn)
		{
			var totalText=document.getElementById('total'); 
			var numText=document.getElementById('textId');
			
			if(!window.moneySign)
			{
				window.moneySign=30;
			}
			if(btn.name=='left')
			{
				if(document.getElementById('textId').value > 1)
				{
					document.getElementById('textId').value -= 1;
				}
				totalText.innerHTML=numText.value*window.moneySign + 'Ԫ'; 
			}
			  
			else
			{
				if(document.getElementById('textId').value< <%=maxNum %>)
				{
					document.getElementById('textId').value++;
				}
				totalText.innerHTML=numText.value*window.moneySign + 'Ԫ'; 
			}
		}
		
		// ����ȷ�Ϲ������Ϣ��ʾ��
		function buyCard(obj)
		{
			var totalText=document.getElementById('total');
			obj.className='btbuy';
			
			var message = "<p class='tc fs26'>��ѡ����<span class='yellow'>" + document.getElementById('textId').value +
						"��</span>���Ϊ<span class='yellow'>" + window.moneySign + 
						"Ԫ</span>�ĳ�ֵ��</p><p class='tc fs26 pt30'>�����ܽ��Ϊ<span class='yellow'>" + totalText.innerHTML + "</span></p>";
						
			document.getElementById("tips").innerHTML = message;
			
			document.getElementById("totalFee").value = totalText.innerHTML.replace('Ԫ', '');
			
			openWindow('openWin1');
		}
		
		// ���ȷ�Ϲ���ť����ת��֧��ҳ��
		function doSub()
		{
			// ��ȡ��������Ϣ��װ��������
			document.getElementById("cardNum").value = document.getElementById("textId").value;
			
			// ��ȡ����ֵ��Ϣ��װ��������
			document.getElementById("cntTotal").value = window.moneySign;
			
			document.actform.action = "${sessionScope.basePath}valueCard/qryPayType.action";
			document.actform.submit();
		}
		
		// ������һҳ
		function goback(curmenu) 
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = curmenu;
				
				document.actform.action="${sessionScope.basePath }valueCard/inputNumber.action";
				document.actform.submit();				
			}			
		}
		
		var Speed_1 = 0.00001; //�ٶ�(����)
		var Space_1 = 25; //ÿ���ƶ�(px)
		var PageWidth_1 = 116 *2; //��ҳ���
		var interval_1 = 500000; //��ҳ���ʱ��
		var fill_1 = 1; //������λ
		var MoveLock_1 = false;
		var MoveTimeObj_1;
		var MoveWay_1="right";
		var Comp_1 = 0;
		var AutoPlayObj_1=null;
		function GetObj(objName)
		{
			if(document.getElementById)	{return eval('document.getElementById("'+objName+'")')}
			else{return eval('document.all.'+objName)}
		}
			
		function AutoPlay_1()
		{
			clearInterval(AutoPlayObj_1);
			AutoPlayObj_1=setInterval('ISL_GoDown_1();ISL_StopDown_1();',interval_1);
		}
			
		function ISL_GoUp_1()
		{
			if(MoveLock_1)
			return;
			clearInterval(AutoPlayObj_1);
			MoveLock_1=true;
			MoveWay_1="left";
			MoveTimeObj_1=setInterval('ISL_ScrUp_1();',Speed_1);
		}
			
		function ISL_StopUp_1()
		{
			if(MoveWay_1 == "right")
			{
				return;
			}
			clearInterval(MoveTimeObj_1);
			
			if((GetObj('ISL_Cont_1').scrollLeft-fill_1)%PageWidth_1!=0)
			{
				Comp_1=fill_1-(GetObj('ISL_Cont_1').scrollLeft%PageWidth_1);
				CompScr_1();
			}
			else
			{
			MoveLock_1=false;
			}	
		    
		    AutoPlay_1();
		}
			 
		function ISL_ScrUp_1(){
			if(GetObj('ISL_Cont_1').scrollLeft<=0)
			{GetObj('ISL_Cont_1').scrollLeft=GetObj('ISL_Cont_1').scrollLeft+GetObj('List1_1').offsetWidth}
			GetObj('ISL_Cont_1').scrollLeft-=Space_1}
			
		function ISL_GoDown_1(){
			
			clearInterval(MoveTimeObj_1);
			if(MoveLock_1)	return;
			clearInterval(AutoPlayObj_1);
			MoveLock_1=true;
			MoveWay_1="right";
			ISL_ScrDown_1();
			MoveTimeObj_1=setInterval('ISL_StopUp_1()',Speed_1)}
			
		function ISL_StopDown_1(){
			
			if(MoveWay_1 == "left"){return};
			clearInterval(MoveTimeObj_1);
			if(GetObj('ISL_Cont_1').scrollLeft%PageWidth_1-(fill_1>=0?fill_1:fill_1+1)!=0)
				{
					Comp_1=PageWidth_1-GetObj('ISL_Cont_1').scrollLeft%PageWidth_1+fill_1;
					CompScr_1()}
			else
				{MoveLock_1=false}
			AutoPlay_1()}
			
		function ISL_ScrDown_1(){
			if(GetObj('ISL_Cont_1').scrollLeft>=GetObj('List1_1').scrollWidth)
			{GetObj('ISL_Cont_1').scrollLeft=GetObj('ISL_Cont_1').scrollLeft-GetObj('List1_1').scrollWidth}
			
			GetObj('ISL_Cont_1').scrollLeft-=Space_1
			}
			
		function CompScr_1(){
			if(Comp_1==0){MoveLock_1=false;return}
			var num,TempSpeed=Speed_1,TempSpace=Space_1;
			if(Math.abs(Comp_1)<PageWidth_1/2)
			{	
				
				TempSpace=Math.round(Math.abs(Comp_1/Space_1));
				if(TempSpace<1){TempSpace=1}
			}
				
			if(Comp_1<0)
			{
				if(Comp_1<-TempSpace){Comp_1+=TempSpace;num=TempSpace}
				else{num=-Comp_1;Comp_1=0}		
				
				GetObj('ISL_Cont_1').scrollLeft-=num;		
				setTimeout('CompScr_1()',TempSpeed)
			}
			else{
				if(Comp_1>TempSpace){Comp_1-=TempSpace;num=TempSpace}
				else{num=Comp_1;Comp_1=0}
				GetObj('ISL_Cont_1').scrollLeft+=num;setTimeout('CompScr_1()',TempSpeed)
			}
			}
		function picrun_ini(){	
			GetObj('ISL_Cont_1').scrollLeft=fill_1>=0?fill_1:GetObj('List1_1').scrollWidth-Math.abs(fill_1);	
			GetObj("ISL_Cont_1").onmouseover=function(){clearInterval(AutoPlayObj_1)}
			GetObj("ISL_Cont_1").onmouseout=function(){AutoPlay_1()};
		}
			
      </script>
 
		<!--������-->
		<div class="openwin_tip openwin_tip" id="openWin1" style="">
		    <div class="bg"></div>
		    <div class=" blank60"></div>
		    <div class=" blank40"></div>
		    <div class="  blank10n"></div>
		    <div id="tips"></div>
		    <div class="tc">
			    <div class=" blank40"></div>
			    <a href="javascript:void(0);" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';" onclick="doSub();return false;">ȷ��</a>
			    <a href="javascript:void(0);" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close();return false;">ȡ��</a>
		    </div>
		</div>
		
		<script type="text/javascript">
		openWindow = function(id){
			wiWindow = new OpenWindow("openWin1",708,392);//�򿪵�����������
		}
		
		</script>
		<!--����������-->
		
	</div>
	<%@ include file="/backinc.jsp"%>
</form>
</body>
</html>