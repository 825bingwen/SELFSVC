/***********************************************

				身份证阅读器接口

***********************************************/

/**
功能: 打开身份证读卡器端口 
返回: 成功0；失败-1
场景: 首次登录自助平台时调用一次，以后不再调用
**/
function OpenCom()
{
	//return '0';
	var ret;
	try{
		ret = document.getElementById("idcardpluginid").OpenCom();
		return ret;
	}catch(e){
		return '-1';
	}
}

/**
功能: 设置身份证读卡器为可用状态
返回: 成功0；失败-1
场景: 打开身份证读卡器端口后，使用身份证读卡器之前，调用此接口，将身份证读卡器设置为就绪状态
**/
function InitIdCardReader()
{
	//return '0';
	var ret;
	try{
		ret = document.getElementById("idcardpluginid").InitCardReader();
		return ret;
	}catch(e){
		return '-1';
	}
}

/**
功能: 用于验证身份证合法性，并获取身份证文字内容。每次调用身份证阅读器只做一次动作，该接口由页面循环调用。
返回: 0读卡成功，1未读取到身份证信息，2无法识别的证件，-1读卡异常；
	 返回值后的内容为格式为：姓名~性别~民族~出生~住址~公民身份号码~签发机关~有效期起始日期~有效期截止日期~最新住址
场景: 身份证读卡器初始化后，循环调用此接口，读取用户身份证信息。
	 如果返回0表示读卡成功，停止定时循环调用；如果返回-1表示读卡失败，停止定时循环调用；如果返回1表示设备尚未读取到卡信息，继续定时循环调用；如果返回2，停止定时循环调用，提示无法识别的证件。
	 在指定时间（30秒）内设备一直未读取到卡信息，则认为读卡超时，停止定时循环调用。
**/
function GetIdCardContent()
{
	//return '0闫强~男~汉~1984-12-20~山东省阳谷县~371521198412200050~阳谷县公安局~2003-04-09~2013-04-09~济南市高新区';
	var ret;
	try{
		ret =document.getElementById("idcardpluginid").GetIdCardContent();
		return ret;
	}catch(e){
		return '-1';
	}
}

/**
功能: 获取身份证阅读器状态
返回: 正常0；异常-1
场景: 在调用初始化身份证读卡器后，获取身份证信息之前，需要调用此接口检查一下读卡器状态
**/
function GetIdCardStatus()
{
	//return '0';
	var ret;
	try{
		ret = document.getElementById("idcardpluginid").GetIdCardStatus();
		return ret;
	}catch(e){
		return '-1';
	}
}

/**
功能: 将身份证阅读器设置为不可使用状态
返回: 0成功，-1失败
场景: 与InitCardReader()接口对应。InitCardReader接口调用后，允许使用该设备，使用完毕该控件后调用CloseCardReader()
**/
function CloseCardReader()
{
	//return '0';
	var ret;
	try{
		ret = document.getElementById("idcardpluginid").CloseCardReader();
		return ret;
	}catch(e){
		return '-1';
	}
}








