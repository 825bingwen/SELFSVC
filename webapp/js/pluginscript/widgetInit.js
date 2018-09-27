// ---- 1 ----
// 初始化票据打印机
// 0 - 正常
// 1 - 缺纸或打印机故障
// 41 - 设备低层驱动程序未安装
function fInitListPrinter() {
	try {
        //初始化打印机
		var initListPrt1 = window.parent.document.getElementById("prtpluginid").InitListPrinter();
		if (initListPrt1 == 1) {
		    showInitErr("警告:票据打印机缺纸或故障!");
		    return;
		} else {
			if (initListPrt1 == 41) {
			    showInitErr("错误:票据打印机设备低层驱动程序未安装!");
			    return;
			}
		}
        //设置打印图标的路径 TODO 
		var initListPrt2 = window.parent.document.getElementById("prtpluginid").SetPicturePath("");
		if (initListPrt2 == 1) {
		    showInitErr("警告:票据打印机缺纸或故障!");
		    return;
		} else {
			if (initListPrt2 == 41) {
			    showInitErr("错误:票据打印机设备低层驱动程序未安装!");
			    return;
			}
		}
		
		var v = window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
		if ( v == 1){
		    showInitErr("对不起,票据打印机发生故障,请确定是否继续!");
		    return;
		}else if (v == 2) {
		    showInitErr("对不起,票据打印机缺纸,请确定是否继续!");
		    return;
		}else if (v != 0){
		    showInitErr("对不起,票据打印机发生错误:"+v+",请确定是否继续!");
		    return;
		}
	}
	catch (e) {
		showInitErr("发生异常,票据打印机初始化失败,无法打印小票!");
		return;
	}
}

// ---- 2 ----
// 初始化发票打印机
// 0 - 正常
// 61 - 打印机初始化失败(故障)
// 62 - 缺纸
function fInitInvoicePrint() {
	try {
	    var openCom = window.parent.document.getElementById("invprtpluginid").OpenCom();
	    if(openCom == 1){
	        showInitErr("错误:端口(串口)故障,打开设备异常,无法初始化发票打印机!");
	        return;
	    } else {
	        if(openCom == 61){
			    showInitErr("错误:发票打印机故障,无法初始化!");
			    return;
	        }
			if (openCom == 65) {
			    showInitErr("警告:发票打印机缺纸!");
			    return;
			} 
            if (openCom != 0) {
			    showInitErr("错误:打开设备异常,无法初始化发票打印机!");
			    return;
			}
	    }
		var initInvoicePrt = window.parent.document.getElementById("invprtpluginid").InitVoicePrint();
		if (initInvoicePrt == 61) {
		    showInitErr("错误:发票打印机故障,无法初始化!");
		    return;
		} else {
			if (initInvoicePrt == 65) {
			    showInitErr("警告:发票打印机缺纸!");
			    return;
			}
		}
		
		var v = window.parent.document.getElementById("invprtpluginid").CheckPaper();
		if (v != 0 ){
		    showInitErr("对不起,发票打印机缺纸,请确认是否继续!");
		    return;
		}
	}
	catch (e) {
		showInitErr("发生异常,发票打印机初始化失败,无法打印发票!");
		return;
	}
}

// ---- 3 ----
// 初始化加密键盘
// 调用接口：short OpenCom(char* Port, int Baud,int Parity,int Data,int Stop)
// 方法功能：初始化加密键盘
// 参数：加密键盘初始化:nBaudRate=9600 nParity=0 nByteSize=8 nStopBits=0
// 返回：0 表示打开设备成功,否则 见返回值说明
function fInitKeyBoard() {
	try {
		var isKeyInit = window.parent.document.getElementById("keybrdpluginid").OpenCom();
		var ret = window.parent.document.getElementById("keybrdpluginid").SetWorkMode(0);
		if (isKeyInit != 0) {
			showInitErr("对不起,开启终端键盘失败,请与管理员联系！");
			return;
		}
		if (ret != 0) {
			showInitErr("对不起,设置键盘模式失败,请与管理员联系！");
			return;
		}
	}
	catch (e) {
		showInitErr("发生异常,无法开启终端键盘!");
		return;
	}
}

// ---- 4 ----
// 本机终端初始化
// 定义：short InitManageConf(char* sockaddr,char *logftpaddr,char *cashinterval)
// 功能：配置自助终端管理平台SOCKET服务器和FTP服务器地址,SOCKET服务器功能包括透传银联包
// 参数：sockaddr：自助终端管理平台socket地址和端口,如192.168.0.1,8888;logftpaddr：上传日志的地址和端口,如192.168.0.1,21
// 返回：0 - 初始化成功;-1 - 初始化失败
// 场景：在自助终端平台首页加载时获取sockaddr和logftpaddr,调用此接口传给自助终端机,供自助终端管理和银联交易用。
function fInitManageConf(sockaddr, logftpaddr, cashinterval) {
	try {
		var initMC = window.parent.document.getElementById("mgrpluginid").InitManageConf(sockaddr, logftpaddr, cashinterval);
		if (initMC == -1) {
			showInitErr("错误:本机终端初始化失败,请与管理员联系！");
			return;
		}
	}
	catch (e) {
		showInitErr("发生异常,无法完成本机终端初始化!");
		return;
	}
}

// ---- 5 ----
// 初始化OMRON识币器
// 参数：无
// 返回：0 - 表示操作成功
//      51 - 失败
function fInitCashEquip() {
	try {
	    //return 0;//测试用
        //打开现金识币器所用的串口并创建监听线程
		//mod by yangtao 在frame开端口
		//window.parent.document.getElementById("cashpluginid").OpenCom();
		var initCash = window.parent.document.getElementById("cashpluginid").InitCashBill();
		if (initCash != 0) {
		    showInitErr("无法初始化现金识币器!");
		    return;
		
		}
	}
	catch (e) {
	    showInitErr("发生异常,现金识币器接口初始化失败,无法进行现金缴费!");
	    return;
	}
}

// ---- 6 ----
// 银联卡缴费接口初始化方法
// 参数：unionUserId：银联商户号；unionPayCode：银联为刷卡的终端分配的唯一编号
// 返回：0 - 初始化成功
//      -1 - 初始化失败（写INI文件错误）
function fInitUnionPayPlugin(unionUserId, unionPayCode) {
	try {
		var initUPP = window.parent.document.getElementById("cardpluginid").InitConfig(unionUserId, unionPayCode);
		if (initUPP != 0) {
			showInitErr("错误:银联卡缴费接口初始化失败!");
			return;
		}
	}
	catch (Ex) {
		showInitErr("发生异常,银联卡缴费接口初始化失败,无法进行银联卡缴费!");
		return;
	}
}

//add by yangtao 初始化打印机在frame完成，其他页面不做 提供只检查打印机状态的函数，主要是userinfo.jsp用
function fChkListPrinter() {
   	try {
		var v = window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
		if ( v == 1){
		    showInitErr("对不起,票据打印机发生故障,请确定是否继续!");
		    return;
		}else if (v == 2) {
		    showInitErr("对不起,票据打印机缺纸,请确定是否继续!");
		    return;
		}else if (v != 0){
		    showInitErr("对不起,票据打印机发生错误:"+v+",请确定是否继续!");
		    return;
		}
	}
	catch (e) {
		showInitErr("发生异常,票据打印机初始化失败,无法打印小票!");
		return;
	}
}


function fChkInvoicePrint() {
	try {
		var v = window.parent.document.getElementById("invprtpluginid").CheckPaper();
		if (v != 0 ){
		    showInitErr("对不起,发票打印机缺纸,请确认是否继续!");
		    return;
		}
	}
	catch (e) {
		showInitErr("发生异常,发票打印机初始化失败,无法打印发票!");
		return;
	}
}

//初始化二代身份证读卡器并检查状态
//0返回正常
function fInitCardReader()
{
	//测试用
	//return 0;
	try {
		var isKeyInit = window.parent.document.getElementById("idcardpluginid").OpenCom();
		if (isKeyInit != 0) 
		{
			showInitErr("对不起,开启二代身份证读卡器失败,请与管理员联系！");
			return isKeyInit;
		}
		
		var ret = window.parent.document.getElementById("idcardpluginid").InitCardReader();
		if (ret != 0) {
			showInitErr("对不起,二代身份证读卡器初始化失败,请与管理员联系！");
			return ret;
		}
	}
	catch (e) {
		showInitErr("发生异常,无法开启二代身份证读卡器!");
		return -99;
	}
}
