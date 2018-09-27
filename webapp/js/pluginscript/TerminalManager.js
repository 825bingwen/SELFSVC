/**********终端管理控件PsTermManager.ocx**********/
 
/**
 *方法名称：getMacAdd()
 *调用接口：BSTR GetLocalMac()
 *方法功能：获取本机MAC地址
 *参数：无
 *返回：如果返回 空 表示获取MAC地L址失败，否则为本机的MAC地址
 */
function getMacAdd(){
		var mac = window.parent.document.getElementById("mgrpluginid").GetLocalMac();
		//return "00-01-6C-98-97-C4";
		return mac;
	}
	
/**
 *方法名称：getIpAdd()
 *调用接口：BSTR GetLocalIpaddress()
 *方法功能：获取本机MAC地址
 *参数：无
 *返回：本机的Ipaddress地址。
 */
function getIpAdd(){
		var ip = window.parent.document.getElementById("mgrpluginid").GetLocalIpaddress();
		//return "10.25.149.178";
		return ip;
	}
	
/**
 *方法名称：initKeyBoard()
 *调用接口：short OpenCom(char* Port, int Baud,int Parity,int Data,int Stop)
 *方法功能：初始化加密键盘
 *参数：加密键盘初始化:nBaudRate=9600 nParity=0 nByteSize=8 nStopBits=0
 *返回：0 表示打开设备成功，否则 见返回值说明。
 */	
function initKeyBoard(){
	var isInit = window.parent.document.getElementById("keybrdpluginid").OpenCom();
	if(isInit!=0){
		pubErrShow("对不起,开启终端键盘失败,请与管理员联系!");
		return false;
	}
	var v = window.parent.document.getElementById("keybrdpluginid").SetWorkMode(0);
	if(v!=0){
		pubErrShow("对不起,设置键盘模式失败,请与管理员联系!");
		return false;
	}
//	alert("打开键盘串口："+isInit);
}
function closeKeyBoard(){
	window.parent.document.getElementById("keybrdpluginid").CloseCom();
}
/**
 *方法名称：writeSummaryLog()
 *调用接口：short TradeWriteLog (char * LogFileName,char* LogContent)
 *方法功能：记录本地交易日志
 *参数：无
 *返回：0 表示打开设备成功，否则 见返回值说明。
 */	
function writeSummaryLog(servNumber,money)
{
	var strFileName = "";
	var dateTime = getDateTime();
	var strContent = dateTime+"  "+servNumber+"  "+ "缴费金额" + money;
	var ret=window.parent.document.getElementById("mgrpluginid").TradeWriteLog(strFileName,strContent);
	return ret;
}
/**
 *方法名称：writeLog()
 *调用接口：short TradeWriteLog (char * LogFileName,char* LogContent)
 *方法功能：记录本地交易日志
 *参数：无
 *返回：0 表示打开设备成功，否则 见返回值说明。
 */	
function writeLog(servNumber,money){
	var strFileName = "";
	var dateTime = getDateTime();
	var strContent = dateTime+"  "+servNumber+"  "+money;
	var ret=window.parent.document.getElementById("mgrpluginid").TradeWriteLog(strFileName,strContent);
	return ret;
}
/**
 *方法名称：writeLog()
 *调用接口：short TradeWriteLog (char * LogFileName,char* LogContent)
 *方法功能：记录本地交易日志
 *参数：status 0成功,1失败
 *返回：0 表示打开设备成功，否则 见返回值说明。
 */	
function writeLog(servNumber,money,status){
    if(status == 0){
        var msg = "成功";
    }else{
        var msg = "失败";
    }
	var strFileName = "";
	var dateTime = getDateTime();
	var strContent = dateTime+"  "+servNumber+"  "+money+ "  " +msg;
	var ret=window.parent.document.getElementById("mgrpluginid").TradeWriteLog(strFileName,strContent);
	return ret;
}
/**
 *方法名称：getDateTime()
 *方法功能：取当前终端日期和时间
 *参数：无
 *返回：终端当前日期和时间，格式：yyyy-mm-dd HH:MM:SS
 */	
function getDateTime(){
	var today = new Date(); 
	var dateTime = today.getYear()+"-"+(today.getMonth()+1)+"-"+today.getDate()+" ";
	var c = ":";
  d = new Date();
  dateTime += d.getHours() + c;
  dateTime += d.getMinutes() + c;
  dateTime += d.getSeconds();
	return dateTime;
}


function termShutDown(){
	try{
		var ret = window.parent.document.getElementById("mgrpluginid").TerminalShutDown(1);
		return ret;
	}catch(Ex){
		return -1;
	}
}

/**
 * 统一平台发起日志记录请求。
 * 
 * @remark create g00140516 2012/07/23 R003C12L07n01 山东充值交费功能记录详细日志
 */ 
function writeDetailLog(flag, logContent)
{
	if (flag == "1")
	{
		try
		{
			window.parent.document.getElementById("mgrpluginid").TradeWriteLog("", logContent);
		}
		catch (Ex)
		{}
	}
	else if (flag == "2")
	{
		var writeLogObj = new ActiveXObject("Scripting.FileSystemObject");
		if (writeLogObj.DriveExists("C:"))
		{
			if (!writeLogObj.FolderExists("C:/hwplatform"))
			{
				writeLogObj.CreateFolder("C:/hwplatform");
			}
			
			var fileName = "C:/hwplatform/" + getCurrentDate() + ".log";
			var fileIO;
			
			if (writeLogObj.FileExists(fileName))
			{
				fileIO = writeLogObj.OpenTextFile(fileName, 8, true);
			}
			else
			{
				fileIO = writeLogObj.CreateTextFile(fileName, true);
			}
			
			fileIO.WriteLine(logContent);
			fileIO.Close();
		}
	}
}

function getCurrentDate() {
	var now = new Date();
	var year = now.getYear();
	var month = now.getMonth() + 1;
	var day = now.getDate();

	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	return year + "" + month + "" + day;
}