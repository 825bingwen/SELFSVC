
/**
 * 自助选号凭条
 * userName --号码
 * userIDCard    --身份证号
 * chooseTelNum  --所选号码
 * pTerminalInfo --终端信息
 * cityName     --打印地点
 * pPrintDate   --打印时间
 */
function doPrintTheTel(userName, userIDCard, chooseTelNum, pTerminalInfo, cityName, pPrintDate) {
	try {
  		//打印移动图标
		var ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
		if (ret == 1) {
  	        alertError("警告:打印机缺纸或故障!");
			return;
		} else {
			if (ret == 41) {
  	   			alertError("错误:打印机设备低层驱动程序未安装!");
				return;
			}
		}
  	     
  		//打印头部信息
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端选号凭据");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  选定号码: "+chooseTelNum);
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		if (ret == 1) {
        	alertError("警告:打印机缺纸或故障!");
			return;
		}
    
    	//打印具体缴费信息
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  用户姓名  : "+userName + ";");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  身份证号  : "+userIDCard + ";");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端信息  : "+pTerminalInfo + ";");
		if (ret == 1) {
        	alertError("警告:打印机缺纸或故障!");
			return;
		}
    
    	//打印尾部信息
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询.");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  客户服务热线:10086.");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印地点:"+cityName+".");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间:"+pPrintDate+".");
		if (ret == 1) {
  	    	alertError("警告:打印机缺纸或故障!");
			return;
		}    
		//正常打印结束,切纸
		cutPaper();
	}
	catch (ex) {
	 	alertError("打印选号凭证出错");
		cutPaper();//出现问题切纸
	}
}

/**
 * 宁夏自助选号凭条打印
 */
function doPrintTheTel_NX(chooseTelNum, orderID, pTerminalInfo, cityName, pPrintDate) 
{
	try {
  		//打印移动图标
  		/*
		var ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
		if (ret == 1) {
  	        alertError("警告:打印机缺纸或故障!");
			return;
		} else {
			if (ret == 41) {
  	   			alertError("错误:打印机设备低层驱动程序未安装!");
				return;
			}
		}
  	    */
  		//打印头部信息
  		var ret = window.parent.document.getElementById("prtpluginid").PrintLine("                  自助选号凭证");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端编号: "+pTerminalInfo);
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  选定号码: " + chooseTelNum);
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  预约流水: " + orderID);
		
		if (ret == 1) {
        	alertError("警告:打印机缺纸或故障!");
			return;
		}
    
    	//打印尾部信息
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印地点:"+cityName+".");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间:"+pPrintDate+".");
		if (ret == 1) {
  	    	alertError("警告:打印机缺纸或故障!");
			return;
		}    
		//正常打印结束,切纸
		cutPaper();
	}
	catch (ex) {
	 	alertError("打印选号凭证出错");
		cutPaper();//出现问题切纸
	}
}

/**
 * 山东自助选号凭条打印
 */
function doPrintTheTel_SD(chooseTelNum, orderID, pTerminalInfo, cityName, pPrintDate) 
{
	try {
  		//打印移动图标
  		/*
		var ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
		if (ret == 1) {
  	        alertError("警告:打印机缺纸或故障!");
			return;
		} else {
			if (ret == 41) {
  	   			alertError("错误:打印机设备低层驱动程序未安装!");
				return;
			}
		}
  	    */
  		//打印头部信息
  		var ret = window.parent.document.getElementById("prtpluginid").PrintLine("                  自助选号凭证");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端编号: "+pTerminalInfo);
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  选定号码: " + chooseTelNum);
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  预约流水: " + orderID);
		
		if (ret == 1) {
        	alertError("警告:打印机缺纸或故障!");
			return;
		}
    
    	//打印尾部信息
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印地点:"+cityName+".");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间:"+pPrintDate+".");
		if (ret == 1) {
  	    	alertError("警告:打印机缺纸或故障!");
			return;
		}    
		//正常打印结束,切纸
		cutPaper();
	}
	catch (ex) {
	 	alertError("打印选号凭证出错");
		cutPaper();//出现问题切纸
	}
}

//切纸      
function cutPaper() {
  	var ret5 = window.parent.document.getElementById("prtpluginid").SetCutPaper();
  	if (ret5 == 1)
  	{
  		  alertError("警告:打印机缺纸或故障!");
  		  return;
  	}
}
