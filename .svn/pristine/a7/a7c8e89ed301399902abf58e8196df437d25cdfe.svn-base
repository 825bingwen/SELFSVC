var timeOut;//必需
function IsIE() {
  if (window.navigator.userAgent.indexOf("MSIE")>=1)
  {
    return 1;
  }
  else //如果浏览器为Firefox
  {
    return 0;
  }
}

function GetKeyCode(e) {//取得不同浏览器下的键盘事件值
  if(IsIE()==1) {
    return window.event.keyCode;
  }else {
    return e.which;
  }
}

function backString(str) {//实现backspace删除字符功能
  return str.substr(0,str.length-1);
}

function preventEvent(e) {//通知浏览器不要执行与事件关联的默认动作
  if(IsIE()==1) {
    window.event.returnValue = false;
  }else {
    e.preventDefault();
  }
}

function getEventTarget(e) {//获取事件目标元素
  if(IsIE()==1) {
    return window.event.srcElement;
  }else {
    return e.target;
  }
}
//验证移动手机号
function validateMobile(controlName) {
		var n = document.all[controlName].value;
		var reg = /^13[4-9]\d{8}$/;
		var reg1 = /^15\d{9}$/;
		return reg.test(n) || reg1.test(n);
}


//取有单位的数值之值,如从"12px"中取出"12"
function getLeft(mainStr,lngLen) {
	if (mainStr) {
		if(!lngLen)
		{
			var lngLen = mainStr.length-2;
		}
		return mainStr.substring(0,lngLen)
	}
	else
	{
		return null
	}
}

//横排按纽点击动作
function linkPress(tableID,mAct,classNumber) {
	var tableObj = document.getElementById(tableID);
	var trObj = tableObj.getElementsByTagName("td");
	if(mAct=="down")
	{
		trObj[0].className = "link"+classNumber+"_left_on";
		trObj[1].className = "link"+classNumber+"_text_on";
		trObj[2].className = "link"+classNumber+"_right_on";
	}
	else
	{
		trObj[0].className = "link"+classNumber+"_left";
		trObj[1].className = "link"+classNumber+"_text";
		trObj[2].className = "link"+classNumber+"_right";
	}
}
//横排按纽点击动作,按纽宽度确定
function linkPressFix(tableID,mAct,classNumber) {
	var tableObj = document.getElementById(tableID);
	var trObj = tableObj.getElementsByTagName("td");
	if(mAct=="down")
	{
		tableObj.className = "link"+classNumber+"_bg_on";
		trObj[0].className = "link"+classNumber+"_left_on";
		trObj[1].className = "link"+classNumber+"_right_on";
	}
	else
	{
		tableObj.className = "link"+classNumber+"_bg";
		trObj[0].className = "link"+classNumber+"_left";
		trObj[1].className = "link"+classNumber+"_right";
	}
}

//滚动文字信息
function moveText(bAct) {
	var outDiv = document.getElementById("textDivOut");
	var inDiv = document.getElementById("textDivIn");
	var outDivH = outDiv.offsetHeight;
	var inDivH = inDiv.offsetHeight;
	var inDivT = parseInt(getLeft(inDiv.style.top));
	if(bAct=="up")
	{
		if(inDivT>-(inDivH-outDivH))
		{
			inDivT=inDivT-2;
		}
		else
		{
			stopMoveText();
		}
	}
	if(bAct=="down")
	{
		if(inDivT<0)
		{
			inDivT=inDivT+2;
		}
		else
		{
			stopMoveText();
		}
	}
	inDiv.style.top = inDivT+"px";
}
function startMoveText(bAct) {
	bbAct = bAct;
	var MT = setInterval("moveText('"+bbAct+"')",1);
}
function stopMoveText() {
	clearInterval(MT);
}
function getValue(name) {
	return getObj(name).value;
}
function getObj(name) {
	return document.getElementById(name);
}
function setValue(key,value){
    document.getElementById(key).value = value;
}
//打印错误信息
function showErr(showElement, errorMsg) {
	var oMsg = document.getElementById(showElement);
	oMsg.innerHTML = "<font size=4 color=red>" + errorMsg + "</font>";
}