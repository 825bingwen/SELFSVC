var timeOut;//����
function IsIE() {
  if (window.navigator.userAgent.indexOf("MSIE")>=1)
  {
    return 1;
  }
  else //��������ΪFirefox
  {
    return 0;
  }
}

function GetKeyCode(e) {//ȡ�ò�ͬ������µļ����¼�ֵ
  if(IsIE()==1) {
    return window.event.keyCode;
  }else {
    return e.which;
  }
}

function backString(str) {//ʵ��backspaceɾ���ַ�����
  return str.substr(0,str.length-1);
}

function preventEvent(e) {//֪ͨ�������Ҫִ�����¼�������Ĭ�϶���
  if(IsIE()==1) {
    window.event.returnValue = false;
  }else {
    e.preventDefault();
  }
}

function getEventTarget(e) {//��ȡ�¼�Ŀ��Ԫ��
  if(IsIE()==1) {
    return window.event.srcElement;
  }else {
    return e.target;
  }
}
//��֤�ƶ��ֻ���
function validateMobile(controlName) {
		var n = document.all[controlName].value;
		var reg = /^13[4-9]\d{8}$/;
		var reg1 = /^15\d{9}$/;
		return reg.test(n) || reg1.test(n);
}


//ȡ�е�λ����ֵֵ֮,���"12px"��ȡ��"12"
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

//���Ű�Ŧ�������
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
//���Ű�Ŧ�������,��Ŧ���ȷ��
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

//����������Ϣ
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
//��ӡ������Ϣ
function showErr(showElement, errorMsg) {
	var oMsg = document.getElementById(showElement);
	oMsg.innerHTML = "<font size=4 color=red>" + errorMsg + "</font>";
}