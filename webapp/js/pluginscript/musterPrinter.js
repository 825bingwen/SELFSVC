var _isPrint_ = 0;

var sameParams = "";

var oUrl = "";

var orgName = "";

var printDate = "";

// 初始化票据打印机
// 0 - 正常
// 1 - 缺纸或打印机故障
// 41 - 设备低层驱动程序未安装
function fGetPrinterStatus() {
    //return 0;//测试
	try {
        var initListPrt3 = window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
        return initListPrt3;
	}
	catch (e) {
		return -99;
	}
}


/**
 * 终端打印方法
 *
 */
function doPrint(pPrintFlag,pListType,pServNumber,pMonth,pOrgName,pPrintDate,contextPath,forMenuid) {
	if (_isPrint_ == 1) 
	{
		setProcessInfo("已打印，请等打印完毕，返回再查询打印。");
		return;
	}
	
	//pPrintFlag = 1;
	if (pPrintFlag != 1) 
	{
		return;
	}
    
  	orgName = pOrgName;
  	printDate = pPrintDate;
  	//山东清单类型
  	//var typeName = ["全部清单", "通话清单", "短信清单", "彩信清单", "梦网清单", "GPRS清单", "WLAN清单", "代收信息费清单", "VPMN清单", "LBS清单"];
  	//湖北清单类型
    var typeName = ["全部清单", "通话清单", "短信清单", "梦网清单", "GPRS清单", "WLAN清单", "彩信清单", "代收信息费清单", "VPMN清单", "PIM清单","手机动画清单","G3清单","游戏点卡清单"];
  	var listType = pListType;   //清单类型数字标志0表示全部
  	var isPrintAll = null;
  	if (listType == "0") 
  	{ 
  		//查询全部
  		isPrintAll = "YES";
  	} 
  	else 
  	{
  		isPrintAll = "NO";
  	}
   
  	try 
  	{
  		//打印图标
  		var Ret3;
	  	try{
	  		Ret3 = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
	  	}
	  	catch(e)
	  	{
			alertError("警告:打印机控件未安装!");
			return;
	  	}
  		if (Ret3 == 1)
  		{
  	  	 	alertError("警告:打印机缺纸或故障!");
  	   		return;
  		}
  		else if (Ret3 == 41)
  		{
  	   		alertError("错误:打印机设备低层驱动程序未安装!");
  	   		return;
  		} 

  		//打印头部信息
  		var Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 清单类型: "+typeName[listType]+"  ");
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 手机号码: "+pServNumber);
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 清单月份: "+pMonth);
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    	if (Ret4 == 1)
    	{
        	alertError("警告:打印机缺纸或故障!");
        	return;
    	}
        //alert(" 清单类型: "+typeName[listType]+"  ");
  		//设置打印状态为1,表示已经打印过一次。
  		_isPrint_ = 1;
  	    
  		//第一次提交打印，打印清单的第一页        
  		var page = 1;
  	    
		oUrl = contextPath  + "feeservice/getPrintData.action";
	
		sameParams = "?month=" + pMonth + "&isPrintAll=" + isPrintAll;
	
		var params = sameParams + "&listtype=" + listType + "&page="+page+"&curMenuId="+forMenuid+"&forMenuid="+forMenuid + "&number=" + Math.random();
	
		//alert(oUrl+params);
		
		setProcessInfo("正在打印，请等待...");

		hiddenFrameSubmit(oUrl + params);
	} 
	catch(ex) 
	{
	 	setProcessInfo("开始打印清单数据出错：" + ex);
	 	cutPaper();//出现问题切纸
	}	
}


/**
* 提交隐藏域，取打印数据
*/
function hiddenFrameSubmit(pUrl) 
{
	try
	{			
		var oFrame = document.getElementById("processFrm");

		if(oFrame) 
		{		
			oFrame.src = pUrl;				
		}								      
	} 
	catch (err)
	{ 
		setProcessInfo("打印过程中,请求打印数据出错:" + err); 
		cutPaper();				
	}
}	//end of hiddenFrameSubmitf

//切纸      
function cutPaper() {
  	var ret5 = window.parent.document.getElementById("prtpluginid").SetCutPaper();
  	if (ret5 == 1)
  	{
  		  alertError("警告:打印机缺纸或故障!");
  		  return;
  	}
}
      
//相应打印
function netload(printDataObject) 
{
	//如果没有取到打印数据,则切纸结束打印
	if (typeof(printDataObject) == "undefined" || printDataObject == null)
	{
		setProcessInfo("取打印数据错误...");
		cutPaper();
		return;
	}
	try 
	{
		var ret = null;
		//取错误，如果出现错误则停止打印
		var errmsg = printDataObject['errmsg'];	
		if (errmsg!=null) 
		{
			setProcessInfo("打印发生错误,错误信息：" + errmsg);				
			cutPaper();
			return;
		}
		//取总页码
		var pageCount = printDataObject['pageCount'];							
		//取下一页码，打印第一页时候，返回的是2
		var page = printDataObject['page'];			
		//取当前正在打印的清单						
		var currentListType = printDataObject['currentListType'];				
		//取是否结束打印标志。
		var printTail = printDataObject['printTail'];
		//取断是否打印清单名称信息标志
		var printTypeName = printDataObject['printTypeName'];			  		  		
		//取断是否打印清单名称信息标志
		var processInfo = printDataObject['process'];
				
		//如果是打印全部，并且打印第一页且存在数据的时候，先打印清单名						
		if (printTypeName != "") 
		{
			if (printTypeName != undefined)
			{
				ret = window.parent.document.getElementById("prtpluginid").PrintLine(" " +printTypeName)
			}
		}
		//开始打印清单数据
		if (pageCount > 0) 
		{		
			//加载打印提示
 			setProcessInfo(processInfo);
 			
  			//取清单数据		
			var entries = printDataObject['records'];	
			for (var i = 0; i < entries.length; i++) 
			{	
				if (entries[i] != undefined)
				{
					ret = window.parent.document.getElementById("prtpluginid").PrintLine(" " + entries[i]);
				} 				
			}
			
  		  	if (ret == 1)
  		  	{
  		      	alertError("警告:打印机缺纸或故障!");
  		      	return;
  		  	}
  		}
  		           
		//如果打印尾部标志为YES，则打印尾部，切纸。              
		if (printTail == "YES") 
		{	              
 			setProcessInfo("结束打印..."); 				 	 		 			  
 				 
 			//打印说明
  		   	var ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" 说明:时长单位为秒,费用单位为元,流量单位为K."); 
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" 声明:本清单信息仅供客户核对之用,不做任何凭据.");
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" 提示:为保护您的密码不被他人恶意窃用,请经常修改.");
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" 以上内容,如有不详之处,请向营业员查询.");
  		   	//delete begin g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
  	      	//delete end g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" 打印地点:"+orgName+".");
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" 打印时间:"+printDate+".");
  		   	if (ret5 == 1)
  		   	{
  		       	alertError("警告:打印机缺纸或故障!");
  		       	return;
  		   	}
  		   	
  		   	//切纸
  		  	cutPaper();
  		  	
  		   	//记入打印次数
  		   	InsertPrintInfo();
		}	
		else 
		{ 
			//否则继续提交打印请求。
			params = sameParams + ("&listtype="+currentListType+"&page="+page+"&curMenuId=qryMuster&forMenuid=qryMuster");	
			hiddenFrameSubmit(oUrl+params);
		}
	} 
	catch(ex) 
	{
		setProcessInfo("打印清单数据出错:" + ex);
		cutPaper(); //出现问题则切纸			
	}
} //end of netload

//从打印对象中获取打印属性
function getValue(keyV,defaultV) 
{
	if (printDataObject[keyV]) 
	{				
		return printDataObject[keyV];
	}
	
	return (typeof(defaultV)!="undefined") ? defaultV : "";		
}	
      
//设置打印状态    
function setProcessInfo(info) {
	  var oProcessDiv = document.getElementById("process");				
		//加载打印提示
	  if(info!="" && oProcessDiv) {  	
  		oProcessDiv.style.display = "";
	  	oProcessDiv.innerHTML = "<font color=red>" + info + "</font>";
	  }
}

// add begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
/**
 * 山东老版详单打印功能
 */
function doPrint_SDOld(pPrintFlag,pListType,pServNumber,startDate, endDate, pOrgName,pPrintDate,
		contextPath, forMenuid, iscycle, cycle, month) 
{
	if (_isPrint_ == 1) 
	{
		setProcessInfo("已打印，请等打印完毕，返回再查询打印。");
		return;
	}
	
	if (pPrintFlag != 1) 
	{
		return;
	}
    
  	orgName = pOrgName;
  	printDate = pPrintDate;

    var typeName = ["全部清单", "通话详单", "短信详单", "移动梦网详单", "GPRS详单", "WLAN详单"];
  	var listType = pListType;   //清单类型数字标志
  	
  	try 
  	{
  		//打印图标
  		var Ret3;
	  	try
	  	{
	  		Ret3 = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
	  	}
	  	catch(e)
	  	{
			alertError("警告:打印机控件未安装!");
			return;
	  	}
  		if (Ret3 == 1)
  		{
  	  	 	alertError("警告:打印机缺纸或故障!");
  	   		return;
  		}
  		else if (Ret3 == 41)
  		{
  	   		alertError("错误:打印机设备低层驱动程序未安装!");
  	   		return;
  		} 

  		//打印头部信息  	
  		var Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 详单类型: "+typeName[listType]+"  ");
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 手机号码: "+pServNumber);
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 查询时段: "+startDate + "--" + endDate);
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    	if (Ret4 == 1)
    	{
        	alertError("警告:打印机缺纸或故障!");
        	return;
    	}
       
  		//设置打印状态为1,表示已经打印过一次。
  		_isPrint_ = 1;
  	    
		oUrl = contextPath  + "cdrquery/getPrintData.action?startDate=" + startDate + "&endDate=" + endDate;
		oUrl = oUrl + "&cdrType=" + listType + "&curMenuId=" + forMenuid + "&iscycle=" + iscycle + "&cycle=" + cycle + "&month=" + month + "&number=" + Math.random();
		
		setProcessInfo("正在打印，请等待...");

		hiddenFrameSubmit(oUrl);
	} 
	catch(ex) 
	{
	 	setProcessInfo("开始打印清单数据出错：" + ex);
	 	cutPaper();//出现问题切纸
	}	
}

/**
 * 山东新版详单打印功能
 */
function doPrint_SDNew(pPrintFlag, cdrType, pServNumber, startDate, endDate, pOrgName,pPrintDate,contextPath, forMenuid,
		cdrTypeName, chQueryDate, chStartDate, chEndDate, custName, iscycle, cycle, month) {
	if (_isPrint_ == 1) 
	{
		setProcessInfo("已打印，请等打印完毕，返回再查询打印。");
		return;
	}

	if (pPrintFlag != 1) 
	{
		return;
	}

  	orgName = pOrgName;
  	printDate = pPrintDate;

  	try 
  	{
  		//打印图标
  		var Ret3;
	  	try
	  	{
	  		Ret3 = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
	  	}
	  	catch(e)
	  	{
			alertError("警告:打印机控件未安装!");
			return;
	  	}
  		if (Ret3 == 1)
  		{
  	  	 	alertError("警告:打印机缺纸或故障!");
  	   		return;
  		}
  		else if (Ret3 == 41)
  		{
  	   		alertError("错误:打印机设备低层驱动程序未安装!");
  	   		return;
  		} 

  		//打印头部信息	
  		var Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine("                中国移动通信 客户详单");
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 客户名称: " + custName);
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 手机号码: " + pServNumber);
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 查询时段: " + chStartDate + "-" + chEndDate);
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 查询日期：" + chQueryDate);
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ");  
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" " + cdrTypeName);
	    
    	if (Ret4 == 1)
    	{
        	alertError("警告:打印机缺纸或故障!");
        	return;
    	}
      
  		//设置打印状态为1,表示已经打印过一次。

  		_isPrint_ = 1;
		
		var params = "referenceServNumber=" + pServNumber + "&cdrType=" + cdrType + "&startDate=" + startDate + "&endDate=" + endDate + "&curMenuId=" + forMenuid + "&iscycle=" + iscycle + "&cycle=" + cycle + "&month=" + month + "&number=" + Math.random();
		oUrl = contextPath  + "cdrquery/getPrintDataNew.action?" + params;

		setProcessInfo("正在打印，请等待...");

		hiddenFrameSubmit(oUrl);
	} 
	catch(ex) 
	{
	 	setProcessInfo("开始打印清单数据出错：" + ex);
	 	cutPaper();//出现问题切纸
	}	
}

//相应打印
function netload_SD(printDataObject) 
{
	//如果没有取到打印数据,则切纸结束打印
	if (typeof(printDataObject) == "undefined" || printDataObject == null)
	{
		setProcessInfo("取打印数据错误...");
		cutPaper();
		return;
	}
	try 
	{
		var ret = null;
		//取错误，如果出现错误则停止打印
		var errmsg = printDataObject['errmsg'];	
		if (errmsg != null) 
		{
			setProcessInfo("打印错误：" + errmsg);
			cutPaper();
			return;
		}
		
		//取是否结束打印标志。
		var printTail = printDataObject['printTail'];
					  		  		
		//取断是否打印清单名称信息标志
		var processInfo = printDataObject['process'];
	
		//加载打印提示
 		setProcessInfo(processInfo);
 			
  		//开始打印清单数据	
		var entries = printDataObject['records'];	
		for (var i = 0; i < entries.length; i++) 
		{	
			if (entries[i] != undefined)
			{
				//modify begin g00140516 2012/07/23 R003C12L07n01 山东详单打印每行46个字节
				ret = window.parent.document.getElementById("prtpluginid").PrintLine(entries[i]);
				//modify end g00140516 2012/07/23 R003C12L07n01 山东详单打印每行46个字节
			} 				
		}
			
  		if (ret == 1)
  		{
  		    alertError("警告:打印机缺纸或故障!");
  		   	return;
  		}  		
  		           
		//如果打印尾部标志为YES，则打印尾部，切纸。              
		if (printTail == "YES") 
		{	              
 			setProcessInfo("结束打印..."); 				 	 		 			  
 				 
 			//打印说明
  		   	var ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  		   	//modify begin g00140516 2012/07/23 R003C12L07n01 山东详单打印每行46个字节
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" 声明：本详单信息仅供客户核对之用，不做任何凭");
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" 据。以上内容，如有不详之处，请向营业员询问。");
  		   	//modify end g00140516 2012/07/23 R003C12L07n01 山东详单打印每行46个字节
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" 打印地点："+orgName);
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" 打印时间："+printDate);
  		   	if (ret5 == 1)
  		   	{
  		       	alertError("警告:打印机缺纸或故障!");
  		       	return;
  		   	}

  		   	//切纸
  		  	cutPaper();
  		  	
  		   	//记入打印次数
  		   	InsertPrintInfo();
		}
	} 
	catch(ex) 
	{
		setProcessInfo("打印清单数据出错:" + ex);
		
		cutPaper(); //出现问题则切纸			
	}
} //end of netload_SD
// add end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953

/**
 * 宁夏老版详单打印
 * 
 * @remark create g00140516 2012/03/14 R003C12L03n01 OR_NX_201203_128
 */
function doPrint_NXOld(pPrintFlag,pListType,pServNumber, cycle, startDate, endDate, pOrgName,pPrintDate,contextPath,forMenuid) {
	if (_isPrint_ == 1) 
	{
		setProcessInfo("已打印，请等打印完毕，返回再查询打印。");
		return;
	}
	
	if (pPrintFlag != 1) 
	{
		return;
	}
    
  	orgName = pOrgName;
  	printDate = pPrintDate;

    var typeName = ["", "通话详单", "", "梦网短信详单", "GPRS流量查询"];
  	var listType = pListType;   //清单类型数字标志
  	
  	try 
  	{
  		//打印图标
  		var Ret3;
	  	try
	  	{
	  		Ret3 = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
	  	}
	  	catch(e)
	  	{
			alertError("警告:打印机控件未安装!");
			return;
	  	}
  		if (Ret3 == 1)
  		{
  	  	 	alertError("警告:打印机缺纸或故障!");
  	   		return;
  		}
  		else if (Ret3 == 41)
  		{
  	   		alertError("错误:打印机设备低层驱动程序未安装!");
  	   		return;
  		} 

  		//打印头部信息  	
  		var Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 详单类型: "+typeName[listType]+"  ");
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 手机号码: "+pServNumber);
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 查询时段: "+startDate + "--" + endDate);
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    	if (Ret4 == 1)
    	{
        	alertError("警告:打印机缺纸或故障!");
        	return;
    	}
       
  		//设置打印状态为1,表示已经打印过一次。
  		_isPrint_ = 1;
  	    
		oUrl = contextPath  + "cdrquery/getPrintData.action?cycle=" + cycle + "&startDate=" + startDate + "&endDate=" + endDate + "&cdrType=" + listType + "&curMenuId="+forMenuid + "&number=" + Math.random();
		
		setProcessInfo("正在打印，请等待...");

		hiddenFrameSubmit(oUrl);
	} 
	catch(ex) 
	{
	 	setProcessInfo("开始打印清单数据出错：" + ex);
	 	cutPaper();//出现问题切纸
	}	
}

/**
 * 宁夏新版详单打印
 * 
 * @remark create g00140516 2012/03/14 R003C12L03n01 OR_NX_201203_128
 */
function doPrint_NXNew(pPrintFlag, cdrType, pServNumber, cycle, startDate, endDate, pOrgName,pPrintDate,contextPath, forMenuid,
		cdrTypeName, chQueryDate, chStartDate, chEndDate, custName) {
	// modify begin g00140516 2013/02/05 R003C13L01n01 用户在同一页面点击打印按钮时，给出正确提示。而不是毫无反应
	/*
	if (_isPrint_ == 1) 
	{
		setProcessInfo("已打印，请等打印完毕，返回再查询打印。");
		return;
	}

	if (pPrintFlag != 1) 
	{
		return;
	}
	*/

  	orgName = pOrgName;
  	printDate = pPrintDate;

  	try 
  	{
  		//打印图标
  		var Ret3;
	  	try
	  	{
	  		Ret3 = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
	  	}
	  	catch(e)
	  	{
			// 先关闭之前的提示框，再显示新的提示框
		  	try
			{
				jdWindow.close();
			}
			catch (ex){}
			
			alertError("警告:打印机控件未安装!");
			return;
	  	}
  		if (Ret3 == 1)
  		{
  	  	 	// 先关闭之前的提示框，再显示新的提示框
		  	try
			{
				jdWindow.close();
			}
			catch (ex){}
  	  	 	
  	  	 	alertError("警告:打印机缺纸或故障!");
  	   		return;
  		}
  		else if (Ret3 == 41)
  		{
  	   		// 先关闭之前的提示框，再显示新的提示框
		  	try
			{
				jdWindow.close();
			}
			catch (ex){}
  	   		
  	   		alertError("错误:打印机设备低层驱动程序未安装!");
  	   		return;
  		} 

  		//打印头部信息	
  		var Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine("                中国移动通信 客户详单");
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 客户名称: " + custName);
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 手机号码: " + pServNumber);
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 查询时段: " + chStartDate + "-" + chEndDate);
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 查询日期：" + chQueryDate);
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ");  
  		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" " + cdrTypeName);
	    
    	if (Ret4 == 1)
    	{
        	// 先关闭之前的提示框，再显示新的提示框
		  	try
			{
				jdWindow.close();
			}
			catch (ex){}
        	
        	alertError("警告:打印机缺纸或故障!");
        	return;
    	}
      
  		//设置打印状态为1,表示已经打印过一次。
  		_isPrint_ = 1;

		var params = "cycle=" + cycle + "&cdrType=" + cdrType + "&startDate=" + startDate + "&endDate=" + endDate + "&curMenuId=" + forMenuid + "&number=" + Math.random();
		oUrl = contextPath  + "cdrquery/getPrintDataNew.action?" + params;

		setProcessInfo("正在打印，请等待...");

		hiddenFrameSubmitNX(oUrl);
	} 
	catch(ex) 
	{
	 	setProcessInfo("开始打印清单数据出错：" + ex);
	 	cutPaperNX();//出现问题切纸
	}
	// modify end g00140516 2013/02/05 R003C13L01n01 用户在同一页面点击打印按钮时，给出正确提示。而不是毫无反应
}

/**
 * 宁夏详单打印
 * 
 * @remark create g00140516 2012/03/14 R003C12L03n01 OR_NX_201203_128
 */
function netload_NX(printDataObject) 
{
	// modify begin g00140516 2013/02/05 R003C13L01n01 用户在同一页面点击打印按钮时，给出正确提示。而不是毫无反应
	//如果没有取到打印数据,则切纸结束打印
	if (typeof(printDataObject) == "undefined" || printDataObject == null)
	{
		setProcessInfo("取打印数据错误...");
		cutPaperNX();
		return;
	}
	try 
	{
		var ret = null;
		//取错误，如果出现错误则停止打印
		var errmsg = printDataObject['errmsg'];	
		if (errmsg != null) 
		{
			setProcessInfo("打印发生错误,错误信息：" + errmsg);				
			cutPaperNX();
			return;
		}
		
		//取是否结束打印标志。
		var printTail = printDataObject['printTail'];
					  		  		
		//取断是否打印清单名称信息标志
		var processInfo = printDataObject['process'];
	
		//加载打印提示
 		setProcessInfo(processInfo);
 			
  		//开始打印清单数据	
		var entries = printDataObject['records'];	
		for (var i = 0; i < entries.length; i++) 
		{	
			if (entries[i] != undefined)
			{
				ret = window.parent.document.getElementById("prtpluginid").PrintLine(" " + entries[i]);
			} 				
		}
			
  		if (ret == 1)
  		{
  		    // 先关闭之前的提示框，再显示新的提示框
		  	try
			{
				jdWindow.close();
			}
			catch (ex){}
  		    
  		    alertError("警告:打印机缺纸或故障!");
  		   	return;
  		}  		
  		           
		//如果打印尾部标志为YES，则打印尾部，切纸。              
		if (printTail == "YES") 
		{	              
 			setProcessInfo("结束打印..."); 				 	 		 			  
 				 
 			//打印说明
  		   	var ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" 声明：本详单信息仅供客户核对之用，不做任何凭据。");
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" 以上内容，如有不详之处，请向营业员询问。");
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" 打印地点："+orgName);
  		   	ret5 = window.parent.document.getElementById("prtpluginid").PrintLine(" 打印时间："+printDate);
  		   	if (ret5 == 1)
  		   	{
  		       	// 先关闭之前的提示框，再显示新的提示框
			  	try
				{
					jdWindow.close();
				}
				catch (ex){}
  		       	
  		       	alertError("警告:打印机缺纸或故障!");
  		       	return;
  		   	}

  		   	//切纸
  		  	cutPaperNX();
  		  	
  		   	//记入打印次数
  		   	InsertPrintInfo();
		}
	} 
	catch(ex) 
	{
		setProcessInfo("打印清单数据出错:" + ex);
		
		cutPaperNX(); //出现问题则切纸
	}
	// modify end g00140516 2013/02/05 R003C13L01n01 用户在同一页面点击打印按钮时，给出正确提示。而不是毫无反应
}

/**
 * 宁夏详单打印
 * 
 * @remark create g00140516 2013/02/05 R003C13L01n01 用户在同一页面点击打印按钮时，给出正确提示。而不是毫无反应
 */
function hiddenFrameSubmitNX(pUrl) 
{
	try
	{
		var oFrame = document.getElementById("processFrm");

		if(oFrame) 
		{
			oFrame.src = pUrl;
		}
	} 
	catch (err)
	{ 
		setProcessInfo("打印过程中,请求打印数据出错:" + err); 
		cutPaperNX();
	}
}

/**
 * 宁夏详单打印。切纸，打印的最后一步
 * 
 * @remark create g00140516 2013/02/05 R003C13L01n01 用户在同一页面点击打印按钮时，给出正确提示。而不是毫无反应
 */
function cutPaperNX() 
{
  	try
  	{
  		var ret5 = window.parent.document.getElementById("prtpluginid").SetCutPaper();
	  	if (ret5 == 1)
	  	{
	  		// 在切纸出现异常时，先关闭之前的提示框。然后再显示新的提示框
	  		try
			{
				jdWindow.close();
			}
			catch (ex){}
	  		  
	  		alertError("抱歉，打印机缺纸或故障，请与营业人员联系。");
	  		return;
	  	}
	  	
	  	// 切纸是打印的最后一步，切纸成功，将“正在打印”的提示框关闭
	  	try
		{
			jdWindow.close();
		}
		catch (ex){}
  	}
  	catch (ex)
  	{
  		// 在切纸出现异常时，先关闭之前的提示框。然后再显示新的提示框
	  	try
		{
			jdWindow.close();
		}
		catch (ex){}
	  		  
	  	alertError("抱歉，详单打印失败，请与营业人员联系。");
	  	return;
  	}
}
