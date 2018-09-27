/***********************************************

				发卡器/写卡器接口

***********************************************/

/**
功能：打开发卡器/写卡器端口
参数：无 
返回：成功0；失败-1
场景：首次登录自助平台时调用一次，以后不再调用。
**/
function OpenMoveCardCom(){
	//return 0;
	try{
		var a = window.parent.document.getElementById("movecardid").OpenCom();
		return a;
	}
	catch(b)
	{
		return -1;
	}

}

/**
功能：初始化发卡器/写卡器，使其处于可用状态
参数：无 
返回：成功0；失败-1
场景：发卡器/写卡器端口打开后，使用发卡器/写卡器之前，调用该接口将发卡器/写卡器设置为可用状态。
**/
function InitMoveCard()
{
	//return 0;
	try{
		var a = window.parent.document.getElementById("movecardid").InitCard();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}

/**
功能：将空白卡移动至写卡处
参数：无 
返回：成功0；失败-1
场景：在写卡之前调用该接口，将空白卡移动到写卡处。
**/
function MoveCardToWrite()
{
	//return 0;
	try{
		var a = window.parent.document.getElementById("movecardid").MoveCardToWrite();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}

/**
功能：检查发卡器状态
参数：无
返回：失败：-1；成功：0~通道卡片位置~卡箱卡片状态
举例：如返回 0~0~1 表示 通道无卡，卡箱卡片不足,提醒需要加卡
             0~2~0 表示 IC卡位置有卡，卡箱无卡
通道卡片位置：长度一个字节。
0：通道无卡
1：读磁卡位置有卡
2：IC卡位置有卡
3：前端夹卡位置有卡
4：前端不夹卡位置有卡
注意：当卡不在以上四个位置时，自动将卡移至回收箱，再返回通道和卡片箱状态。
卡箱卡片状态：
  0:  卡箱无卡
  1:  卡箱卡片不足,提醒需要加卡
  2:  卡箱卡片足够
场景：初始化发卡器后，需要调用此接口检查设备状态。
**/
function ReadCardStatus()
{
	//return 1;
	try{
		var a = window.parent.document.getElementById("movecardid").ReadCardStatus();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}

/**
返回：0已经插入，-1未插卡
场景：写卡前判断写卡器是否已经插入卡。
**/
function IsCardExist()
{
	//return 0;
	try{
		var a = window.parent.document.getElementById("movecardid").IsCardExist();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}

/**
功能：写卡失败后将卡移至回收箱
参数：无
返回：0：成功  -1：失败
场景：写卡失败后进行回收
**/
function callBackCard()
{
	//return 0;
	try{
		var a = window.parent.document.getElementById("movecardid").callBackCard();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}

/**
返回：成功0；失败-1
场景：开户成功后，调用该接口，将写好的SIM卡吐出来
**/
function MoveOutCard()
{
	//return 0;
	try{
		var a=window.parent.document.getElementById("movecardid").MoveOutCard();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}

/**
功能：将发卡器/写卡器设置为不可使用状态
参数：无 
返回：成功0；失败-1
场景：与InitCard()接口对应。InitCard接口调用后，允许使用该设备，使用完毕该控件后调用CloseCard()。
**/
function CloseMoveCard()
{
	//return 0;
	try{
		var a = window.parent.document.getElementById("movecardid").CloseCard();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}

/**
功能：初始化票据打印机
参数：无
返回：0:成功 其他失败
场景：初始化票据打印机
**/
function getPrinterStatus()
{
	//return 0;
	try{
		var a = window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}

/**function checkCashStatus1()
{
	return 1;
	try{
		var a = window.parent.document.getElementById("cashpluginid").CheckCashState();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}*/

/**
功能：建立与RPS系统的连接，需打通每台终端机到卡商RPS系统的网络
参数：连接地址 bstrURL (IN): 写卡服务器配置信息
返回：成功0；失败-1
场景：调用卡号初始化接口
**/
function InitWebService(url)
{
	//return 0;
	//创建连接
	try
	{
		var ret =window.parent.document.getElementById("movecardid").InitWebService(url);
		return ret;
	}catch(e)
	{	
		return -1;
	}
}

/**
功能：读取空白卡序列号
参数：无
返回：空白卡的序列号
场景：读取空白卡序列号， 提交BOSS开户使用。
**/
function GetICCSerial()
{
	//return '12345678';
	try
	{
		var serial=window.parent.document.getElementById("movecardid").GetICCSerial();
	}catch(e)
	{	
		return -1;
	}
}

/**
功能：写卡
参数：无
返回：成功0；失败-1
场景：服务密码设备成功后调用卡商的写卡接口
**/
function writeCard(telnum, province_code, BKiFlag, iccid, imsi, smspNumber)
{
	//return 0;
	try
	{
		var ret = window.parent.document.getElementById("movecardid").writeCard(telnum, province_code, BKiFlag, iccid, imsi, smspNumber);
	}catch(e)
	{
		return -1;
	}
}

function GetLastError()
{
	try
	{
		var ret = window.parent.document.getElementById("movecardid").GetLastError();
	}
	catch(e)
	{	
		return -1;
	}
}