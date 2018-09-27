/**********�ն˹���ؼ�PsTermManager.ocx**********/
 
/**
 *�������ƣ�getMacAdd()
 *���ýӿڣ�BSTR GetLocalMac()
 *�������ܣ���ȡ����MAC��ַ
 *��������
 *���أ�������� �� ��ʾ��ȡMAC��Lַʧ�ܣ�����Ϊ������MAC��ַ
 */
function getMacAdd(){
		var mac = window.parent.document.getElementById("mgrpluginid").GetLocalMac();
		//return "00-01-6C-98-97-C4";
		return mac;
	}
	
/**
 *�������ƣ�getIpAdd()
 *���ýӿڣ�BSTR GetLocalIpaddress()
 *�������ܣ���ȡ����MAC��ַ
 *��������
 *���أ�������Ipaddress��ַ��
 */
function getIpAdd(){
		var ip = window.parent.document.getElementById("mgrpluginid").GetLocalIpaddress();
		//return "10.25.149.178";
		return ip;
	}
	
/**
 *�������ƣ�initKeyBoard()
 *���ýӿڣ�short OpenCom(char* Port, int Baud,int Parity,int Data,int Stop)
 *�������ܣ���ʼ�����ܼ���
 *���������ܼ��̳�ʼ��:nBaudRate=9600 nParity=0 nByteSize=8 nStopBits=0
 *���أ�0 ��ʾ���豸�ɹ������� ������ֵ˵����
 */	
function initKeyBoard(){
	var isInit = window.parent.document.getElementById("keybrdpluginid").OpenCom();
	if(isInit!=0){
		pubErrShow("�Բ���,�����ն˼���ʧ��,�������Ա��ϵ!");
		return false;
	}
	var v = window.parent.document.getElementById("keybrdpluginid").SetWorkMode(0);
	if(v!=0){
		pubErrShow("�Բ���,���ü���ģʽʧ��,�������Ա��ϵ!");
		return false;
	}
//	alert("�򿪼��̴��ڣ�"+isInit);
}
function closeKeyBoard(){
	window.parent.document.getElementById("keybrdpluginid").CloseCom();
}
/**
 *�������ƣ�writeSummaryLog()
 *���ýӿڣ�short TradeWriteLog (char * LogFileName,char* LogContent)
 *�������ܣ���¼���ؽ�����־
 *��������
 *���أ�0 ��ʾ���豸�ɹ������� ������ֵ˵����
 */	
function writeSummaryLog(servNumber,money)
{
	var strFileName = "";
	var dateTime = getDateTime();
	var strContent = dateTime+"  "+servNumber+"  "+ "�ɷѽ��" + money;
	var ret=window.parent.document.getElementById("mgrpluginid").TradeWriteLog(strFileName,strContent);
	return ret;
}
/**
 *�������ƣ�writeLog()
 *���ýӿڣ�short TradeWriteLog (char * LogFileName,char* LogContent)
 *�������ܣ���¼���ؽ�����־
 *��������
 *���أ�0 ��ʾ���豸�ɹ������� ������ֵ˵����
 */	
function writeLog(servNumber,money){
	var strFileName = "";
	var dateTime = getDateTime();
	var strContent = dateTime+"  "+servNumber+"  "+money;
	var ret=window.parent.document.getElementById("mgrpluginid").TradeWriteLog(strFileName,strContent);
	return ret;
}
/**
 *�������ƣ�writeLog()
 *���ýӿڣ�short TradeWriteLog (char * LogFileName,char* LogContent)
 *�������ܣ���¼���ؽ�����־
 *������status 0�ɹ�,1ʧ��
 *���أ�0 ��ʾ���豸�ɹ������� ������ֵ˵����
 */	
function writeLog(servNumber,money,status){
    if(status == 0){
        var msg = "�ɹ�";
    }else{
        var msg = "ʧ��";
    }
	var strFileName = "";
	var dateTime = getDateTime();
	var strContent = dateTime+"  "+servNumber+"  "+money+ "  " +msg;
	var ret=window.parent.document.getElementById("mgrpluginid").TradeWriteLog(strFileName,strContent);
	return ret;
}
/**
 *�������ƣ�getDateTime()
 *�������ܣ�ȡ��ǰ�ն����ں�ʱ��
 *��������
 *���أ��ն˵�ǰ���ں�ʱ�䣬��ʽ��yyyy-mm-dd HH:MM:SS
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
 * ͳһƽ̨������־��¼����
 * 
 * @remark create g00140516 2012/07/23 R003C12L07n01 ɽ����ֵ���ѹ��ܼ�¼��ϸ��־
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