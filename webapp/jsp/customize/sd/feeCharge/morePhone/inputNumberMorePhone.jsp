<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    // 清除缓存，防止页面后退安全隐患 
    response.setHeader("Pragma", "no-cache"); 
    response.setHeader("Cache-Control", "no-store"); 
    response.setDateHeader("Expires", -1);
    
    // 话费连缴人数，默认为5
    String morePhoneCount = CommonUtil.getParamValue(Constants.SH_MOREPHONE_COUNT, "5");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>移动自助终端</title>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK" />
        <META HTTP-EQUIV="pragma" CONTENT="no-cache">
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
        <META HTTP-EQUIV="Expires" CONTENT="0">
        <link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
        <link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/script.js?ver=${jsVersion }"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js?ver=${jsVersion }"></script>
        <script type="text/javascript">
        //防止页面重复提交
        var submitFlag = 0;
        
        //8、32、66、77 更正
        //82、220 返回
        //13、89、221 确认
        //80 打印
        //85 上一页
        //68 下一页
        
        /*
         *　去掉左右两边的空格
         */
        function trim(str) 
        {
            while (str.charAt(str.length - 1) == " ") 
            {
                str = str.substring(0, str.length - 1);
            }
            
            while (str.charAt(0) == " ") 
            {
                str = str.substring(1, str.length);
            }
            
            return str;
        }
        
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
                doSub();
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
            var r = lastObj.createTextRange(); 
            r.collapse(false); 
            r.select();
        }

        function feeCharge()
        {
            document.forms[0].target = "_self";
            document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
            document.forms[0].submit();
        }
        </script>
    </head>
    <body scroll="no" onload="document.getElementById('numBoardText1').focus();setTMoney();setClassOn();">
        <form name="actform" method="post">
            <s:hidden id="morePhoneFee" name="morePhoneFee"></s:hidden> 
            <s:hidden id="totalFee" name="totalFee"></s:hidden>
            <s:hidden id="morePhoneFlag" name="morePhoneFlag" />
            <!-- 用户信息字符串 -->
            <s:hidden id="morePhoneInfo" name="morePhoneInfo"/>
            <%@ include file="/titleinc.jsp"%>

            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
                
                <div class="b966">
                    <div class="clear"></div>
                    <ul id="phone_contentul_titile" class="FenLei_lu">
                        <li class="clearfix phone_multinum_title" style="height:50px; margin: 0px 0px 0px 0px">
                            <div class="telnum">手机号码</div><div class="telmoney">充值金额</div>
                        </li>
                    </ul>
                    <div class="changebtn" style="left:400px; _left:380px;"><a href="javascript:void(0)" onclick="feeCharge();return false;">单人交费</a></div>  
                    <div class="clear"></div>
                    <div class="blank5"></div>   
                    <div class="fl ml30 mt20">
                     <div class="box50W fl">
                        <input type="button" class="btnUp0" onclick="myScroll.moveUp(85)" />
                        <div class="boxPage0" style="width:50px; height:230px; ">
                            <div class="blank10px"></div>
                            <div class="box500W tc lh30" id="gunDom" style=" width:50px; height:36px; position:absolute; cursor:move;top:175px; _top:183px; line-height:36px" >0%</div>
                        </div>
                        <input type="button" class="btnDown0" onclick="myScroll.moveDown(30)"/>
                    </div>
                      <p class="fs22 mb30"></p>
                      <!--键盘+输入框+温馨提示-->
                        <div class="keyboard_wrap clearfix"> 
                        <div class="phone_multinum_list_title fl" id="phone_contentdiv_total" >
                            <div id="phone_contentdiv" class="phone_multinum_list fl" style="position:relative; display:block;">
                                <ul id="phone_contentul">
                                  <s:if test="null != morePhones && morePhones.size() >= 1">
                                     <s:iterator value="morePhones" id="morePhone" status="st">
                                        <li class="fs20  clearfix" id="input_phone_<s:property value='#st.index + 1'/>" >
                                            <div>
                                                <input style="position:relative;" type="text" maxlength="11" id="numBoardText1" onkeyup="checkTelnum(this,'<s:property value="#st.index + 1"/>');" name="servnumbers" value="<s:property value='#morePhone.servnumber'/>" class="text_Input fl" onclick="changObj(this);MoveLast(this);"/>
                                            </div>
                                            <div class="choose_money">
                                                <span id='phone_money_<s:property value="#st.index + 1"/>'><s:property value="#morePhone.tMoney"/>元</span>
                                                <a style="position:relative;" onclick="changeBoard(this); return false;">选择</a>
                                            </div>
                                        <s:if test="1 >= #st.index">
                                            </li>
                                        </s:if>
                                        <s:else>
                                                <div class="delete_num positionrel">
                                                    <a onclick="deletePhoneInput('phone_contentul', this); return false;">删除</a>
                                                </div>
                                            </li>
                                        </s:else>
                                     </s:iterator>
                                  </s:if>
                                  <s:else>
                                     <li class="on fs20 clearfix" id="input_phone_1" >
                                        <div><input style="position:relative;" type="text" maxlength="11" id="numBoardText1" name="servnumbers" class="text_Input fl" onkeyup="checkTelnum(this,'1');" onclick="changObj(this);MoveLast(this);"/></div>
                                        <div class="choose_money"><span id='phone_money_1'>100元</span><a style="position:relative;" onclick="changeBoard(this); return false;">选择</a></div>
                                     </li>
                                     <li class="fs20 clearfix" id="input_phone_2" >
                                        <div><input style="position:relative;" type="text" maxlength="11" id="numBoardText2" name="servnumbers" class="text_Input fl" onkeyup="checkTelnum(this,'2');"  onclick="changObj(this);MoveLast(this);"/></div>
                                        <div class="choose_money"><span id='phone_money_2'>100元</span><a style="position:relative;" onclick="changeBoard(this); return false;">选择</a></div>
                                     </li>
                                  </s:else>
                                </ul>
                            </div> 
                        </div>     
                
                        <!--号码键盘-->
                        <div class="numboard numboard_multinum fl" id="numBoard">
                          <div class=" numbox">
                            <div class="blank10"></div>
                            <a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
                            <div class="clear"></div>
                            <a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
                            <div class="clear"></div>
                            <div class="nleft"> <a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)">#</a> </div>
                            <div class="nright"> <a onclick="doSub()" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'">1</a> </div>
                            <div class="blank10"></div>
                          </div>
                        </div>
                        <!--金额键盘-->
                         <div class="numboard moneyboard_multinum fl mt50 disn" id="moneyBoard" >
                          <div class=" numbox">
                            <div class="blank10"></div>
                            <s:iterator value="amountList" id="amount">
                                <a href="javascript:void(0);" onclick="getMoney(this);return false;"><s:property value="#amount.dictid"/>元</a>
                            </s:iterator>
                          </div>
                        </div>
                        
                         <!--添加手机号码 -->
                        <div style="position:relative; display:block; z-index:9999">
                            <a class="add_num" onclick="addNumLine(); return false;"style="position:relative; display:block; z-index:9999">+ 添加手机号码</a>
                        </div>
                        
                        <!--合计 -->
                        <div class="total">
                            <span class="yellow fr" id="totalFeeSpan">0.00元</span>  <span class="fr" >充值金额合计：</span>     
                        </div>
                        
                        <!--弹出框 正在处理 请稍候-->
                        <div class="popupWin fs28 credit_pls_wait" id="pls_wait">
                            <div class="bg"></div>
                            <p class="mt40">
                                <img src="${sessionScope.basePath }images/loading.gif" alt="处理中..." />
                            </p>
                            
                            <%-- modify begin hWX5316476 2015-6-27 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
                            <p class="tips_txt">
                                <%=CommonUtil.getParamValue(Constants.REC_WAITLOADING_MSG,"正在处理，请稍候......") %>
                            </p>
                            <%-- modify end hWX5316476 2015-6-27 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
                            
                            <div class="line"></div>
                            <div class="popup_banner"></div>
                        </div>
                        <script type="text/javascript">
                            openWindow_wait = function(id)
                            {
                                wiWindow = new OpenWindow("pls_wait", 804, 515);//打开弹出窗口
                            }           
                        </script>
                        <!--弹出窗结束-->
                        
                      </div>                      
                        <div class="blank10"></div>
                    </div>                  
                </div>
            </div>
            <%@ include file="/backinc.jsp"%>       
        </form>
    </body>
</html>
<script type="text/javascript">
function setClassOn()
{
    document.getElementById("input_phone_1").className = 'on fs20  clearfix';
}


// 校验手机号码是否可用
function checkTelnum(phoneInput, spanId)
{
    if (11 == phoneInput.value.length)
    {
        // 判断手机号码是否重复
        if (checkRepeatTelnum())
        {
            return;
        }
        
        // 同步校验手机号码是否可用
        synCheckTelnum(phoneInput,"phone_money_" + spanId);
    }
    
}

function checkTelnumBoard(phoneInput, spanId)
{
    if (11 == phoneInput.value.length)
    {
        // 判断手机号码是否重复
        if (checkRepeatTelnum())
        {
            return;
        }
        
        // 同步校验手机号码是否可用
        synCheckTelnum(phoneInput,spanId);
    }
}

// 获取所有手机号码输入框对象
var telnumObjs = document.getElementById("phone_contentul").getElementsByTagName("input");

// 校验手机号码重复
function checkRepeatTelnum()
{
    for (var i = 0; i < telnumObjs.length; i++)
    {
        for (var j = i + 1; j < telnumObjs.length; j++)
        {
            if (0 != telnumObjs[j].value.length && telnumObjs[i].value == telnumObjs[j].value)
            {
                alertRedErrorMsg("手机号码不能相同！");
                return true;
            }
        }
    }
    return false;
}

// 同步校验手机号码是否可用
function synCheckTelnum(phoneInput, spanId)
{
    var money = document.getElementById(spanId).innerHTML;
    money = money.substring(0, money.length - 1); 

    var url="${sessionScope.basePath}charge/qryUserStatusMorePhone.action";
    var param="servnumber="+phoneInput.value + "&money=" + money + "&morePhoneFlag=" + "1";
    var data = "";
    var contentLoader=new net.ContentLoaderSynchro(url,function(){
        data=this.req.responseText;
    },null,"POST",param,"application/x-www-form-urlencoded");

    var msg = data.split(";,;");
    
    // 验证失败，弹出错误信息
    if ("0" == msg[0])
    {
        phoneInput.focus();
        phoneInput.setAttribute("validFlag","0");
        alertRedErrorMsg(msg[1]);
        return;
    }
    else
    {
        var morePhoneInfo = document.getElementById("morePhoneInfo").value;
        if ("" == morePhoneInfo || null == morePhoneInfo)
        {
            morePhoneInfo = msg[1];
        }
        else
        {
            morePhoneInfo = morePhoneInfo + "," + msg[1];
        }
        document.getElementById("morePhoneInfo").value = morePhoneInfo;
    }
    phoneInput.setAttribute("validFlag","1");
    
}

// 提交
function doSub()
{
    if (submitFlag == 0)
    {
        setTMoney();
        
        for (var i = 0; i < telnumObjs.length; i++)
        {
            if (0 == telnumObjs[i].value.length)
            {
                alertRedErrorMsg("手机号码不能为空！");
                return true;
            }
            if (11 != telnumObjs[i].value.length || "0" == telnumObjs[i].getAttribute("validFlag"))
            {
                alertRedErrorMsg("手机号码：" + telnumObjs[i].value + " 有误，请输入正确的手机号码！");
                return;
            }
        }
        
        // 判断手机号码是否重复
        if (checkRepeatTelnum())
        {
            return;
        }
        
        // 调整用户信息串
        setUserInfo();
        
        setMorePhoneInfo();
        var userInfo = document.getElementById("morePhoneInfo").value;
        for (var i = 0; i < telnumObjs.length; i++)
        {
            if (-1 == userInfo.indexOf(telnumObjs[i].value))
            {
                var spanId = telnumObjs[i].parentNode.parentNode.getElementsByTagName("span")[0].id;
                synCheckTelnum(telnumObjs[i], spanId);
            }
        }
        setUserInfo();
        
        
        submitFlag = 1;
        
        openWindow_wait('pls_wait');
        
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
            "选择充值交费");
            
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath}charge/qryAcountMorePhone.action";
        document.actform.submit();
    }   
}

// 返回上一页
function goback(menuid)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", document.getElementById("numBoardText1").value + "退出充值交费功能");
        
        document.getElementById("curMenuId").value = menuid;
                
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
        document.forms[0].submit();
    }
}   

// 为方便选择充值金额，设置此处的全局变量
var moneyObj;

var numBoardBtns=document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
var lastObj=document.getElementById('numBoardText1');
for(i=0;i<numBoardBtns.length;i++){
     if(!numBoardBtns[i].className) numBoardBtns[i].className='';
     if(numBoardBtns[i].name=='functionkey') continue ;  
     
          numBoardBtns[i].onmousedown=function(){
         
          this.className+='on';
        }
        numBoardBtns[i].onmouseup=function(){
            changValue(0,this.innerHTML);
            var fullClass=this.className;
            this.className=fullClass.slice(0,fullClass.indexOf('on'));      
           
        }                   
    }
    
function changObj(o)
{
    lastObj = o;
    document.getElementById('moneyBoard').className = "numboard moneyboard_multinum fl disn";
    document.getElementById('numBoard').className = "numboard numboard_multinum fl ";
    
    var len = document.getElementById('phone_contentul').childNodes.length;
                         
    for(var t = 0; t <= len - 1; t++)
    {
        if(document.getElementById('phone_contentul').childNodes[t].className == "disn")
        {
            continue;
        }
        else if(document.getElementById('phone_contentul').childNodes[t].id == lastObj.parentNode.parentNode.id)
        {
            lastObj.parentNode.parentNode.className = "on fs20  clearfix";
        }
        else
        {
            document.getElementById('phone_contentul').childNodes[t].className = "fs20  clearfix";
        }
    }
}
                    
function changValue(t,v)
{
    if(t==-1)
    {
        lastObj.value = lastObj.value.slice(0,-1);
    }
    else if(t==-2)
    {
        lastObj.value = ""
    }
    else
    {
        if(lastObj.value.length<11 && !isNaN(v))
        {
            lastObj.value +=v;
            
            // 若手机号码为11位，异步校验手机号码是否可用
            if(lastObj.value.length == 11)
            {
                var spanId = lastObj.parentNode.parentNode.getElementsByTagName("span")[0].id;
                checkTelnumBoard(lastObj,spanId)
            }
        }
        
        /*// 若手机号码为11位，异步校验手机号码是否可用
        if(lastObj.value.length == 11)
        {
            checkTelnum(lastObj)
        }*/
    }   
}       

// 展示选择金额页面
function changeBoard(x)
{   
    // 代码设置主要是控制光标所在位置的背景显示            
    var len = document.getElementById('phone_contentul').childNodes.length;
    for(t=0;t<=len-1; t++)
    {
        if(document.getElementById('phone_contentul').childNodes[t].className == "disn")
        {
            document.getElementById('phone_contentul').childNodes[t].className = "disn";
        }
        else
        {
            document.getElementById('phone_contentul').childNodes[t].className = "fs20  clearfix";
        }
    }
    
    chooseobj=x;
    
    // 将点击的光标所在位置设置为高亮背景
    chooseobj.parentNode.parentNode.className = "on fs20  clearfix";    
    document.getElementById('moneyBoard').className = "numboard moneyboard_multinum fl ";
    document.getElementById('numBoard').className = "numboard numboard_multinum fl disn";
    
    moneyObj = chooseobj.parentNode.getElementsByTagName('span')[0];
    setTMoney();
}   

// 滚动条方法加载及控制
myScroll = new virtualScroll("phone_contentdiv","gunDom");        
myScroll.parentTop = myScroll.parentTop + 135;

// 获取用户在金额键盘上选择的充值金额
function getMoney(obj)
{
    moneyObj.innerHTML = obj.innerHTML;
    document.getElementById('moneyBoard').className = "numboard moneyboard_multinum fl disn";
    document.getElementById('numBoard').className = "numboard numboard_multinum fl";
    
    setTMoney();
}

// 设置缴费金额往action提交
function setTMoney()
{
  var moneyObjs = document.getElementById("phone_contentul").getElementsByTagName("span");
  
  // 缴费金额字符串
  var tmoneyStr = "";
  var money = null;
  // 缴费中金额
  var totalFee = 0;
  for (var i = 0; i < moneyObjs.length; i++)
  {
      money = moneyObjs[i].innerHTML.substring(0, moneyObjs[i].innerHTML.length-1);
      totalFee = totalFee + parseInt(money);
      tmoneyStr = tmoneyStr + money + ",";
  }
  document.getElementById("morePhoneFee").value = tmoneyStr.substring(0, tmoneyStr.length-1);
  document.getElementById("totalFee").value = totalFee;
  document.getElementById("totalFeeSpan").innerHTML = totalFee + "元";
}

// 添加手机号码
function addNumLine()
{
    var len = document.getElementById('phone_contentul').childNodes.length;
    
    // 展示在页面上的号码记录条数
    var usingLength = 0;
    
    // 可添加的最大条数 此数据需要配置到自助终端数据库的参数表中
    var morePhoneCount = <%=morePhoneCount%>;
    
    // 统计在页面上显示的号码输入框的个数
    for(t = 0; t <= len - 1; t++)
    {
        if(document.getElementById('phone_contentul').childNodes[t].className == "disn")
        {
            continue;
        }
        else
        {
            usingLength = usingLength + 1;
        }
    }
    
    // 若页面现有的数量未超出限制的最大值，则用户可继续进行添加
    if(usingLength < morePhoneCount)
    {
        var ul = document.getElementById("phone_contentul");
        
        // 创建li标签
        var li = document.createElement("li");
        li.id = 'input_phone_' + (len + 1);
        li.className = 'fs20 clearfix';
        
        // 创建DIV标签
        var numDiv = document.createElement("div");
        
        // 创建input标签
        var numInput = document.createElement("input");
        numInput.type = 'text';
        numInput.maxLength = '11';
        numInput.className = 'text_Input fl positionrel';
        numInput.id = 'numBoardText' + (len + 1);
        numInput.maxLength = '11';
        numInput.name = "servnumbers";
        numInput.onclick = function() {
                changObj(this);
        };
        numInput.onkeyup = function(){
            checkTelnum(this,len + 1);
        };
        
        numDiv.appendChild(numInput);
        
        // 创建DIV标签
        var moneyDiv = document.createElement("div");
        moneyDiv.className = 'choose_money';
        
        // 创建span标签
        var moneySpan = document.createElement("span");
        moneySpan.id = 'phone_money_' + (len + 1);
        moneySpan.innerHTML = '100元';
        
        // 创建a标签
        var moneyA = document.createElement("a");
        moneyA.onclick = function() {
            changeBoard(this);
            setTMoney();
            return false;
            };
        moneyA.innerHTML = '选择';
        moneyA.className = "positionrel";
        
        moneyDiv.appendChild(moneySpan);
        
        moneyDiv.appendChild(moneyA);
        
        // 创建删除按钮的格式
        var deleteDiv = document.createElement("div");
        deleteDiv.className = "delete_num positionrel";
        
        // 删除li标签
        var deleteA = document.createElement("a");
            deleteA.onclick = function() {
            ul.removeChild(li);// 此句最关键
            setTMoney();
            return false;
            };
        
        deleteA.innerHTML = '删除';
        
        deleteDiv.appendChild(deleteA);
        
        li.appendChild(numDiv);
        
        li.appendChild(moneyDiv);
        
        li.appendChild(deleteDiv);
        
        ul.appendChild(li);
        
        // 若页面上的输入框数量超过div的最大值，光标直接定位至100%的位置，显示最新添加的输入框
        myScroll.moveDown(240);
    }
    else
    {
        alertRedErrorMsg("非常抱歉，话费连缴功能目前仅支持" + morePhoneCount + "个号码交费！");
    }
    setTMoney();
}

// 删除输入框
function deletePhoneInput(id, aObj)
{
    var ul = document.getElementById(id);
    ul.removeChild(aObj.parentNode.parentNode);// 此句最关键
    setTMoney();
}

function setUserInfo()
{
    var userInfo = document.getElementById("morePhoneInfo").value;
    userInfos = userInfo.split(",");
    var morePhoneInfo = "";
    for (var i = 0; i < telnumObjs.length; i++)
    {
        for (var j = 0; j < userInfos.length; j++)
        {
	        if (telnumObjs[i].value == userInfos[j].split("~")[0])
	        { 
	            morePhoneInfo = morePhoneInfo + userInfos[j] + ",";
	            break;
	        }
        }
    }
    morePhoneInfo = morePhoneInfo.substring(0, morePhoneInfo.length - 1);
    document.getElementById("morePhoneInfo").value = morePhoneInfo;
}

function setMorePhoneInfo()
{
    var moneyObjs = document.getElementById("phone_contentul").getElementsByTagName("span");
    var userInfo = document.getElementById("morePhoneInfo").value;
    userInfos = userInfo.split(",");
    var morePhoneInfo = "";
    var money = "";
    for (var i = 0; i < telnumObjs.length; i++)
    {
        for (var j = 0; j < userInfos.length; j++)
        {
            if (telnumObjs[i].value == userInfos[j].split("~")[0])
            { 
            	var userArray = userInfos[j].split("~");
                money = moneyObjs[i].innerHTML;
                //userInfos[j] = userInfos[j].replace(userInfos[j].split("~")[2], money.substring(0, money.length - 1));
                userInfos[j] = userInfos[j].replace(userArray[0]+"~"+userArray[1]+"~"+userArray[2], 
                	userArray[0]+"~"+userArray[1]+"~"+money.substring(0, money.length - 1));
                morePhoneInfo = morePhoneInfo + userInfos[j] + ",";
                break;
            }
        }
    }
    morePhoneInfo = morePhoneInfo.substring(0, morePhoneInfo.length - 1);
    document.getElementById("morePhoneInfo").value = morePhoneInfo;
}
</script>
