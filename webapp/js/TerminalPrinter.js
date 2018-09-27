var _isPrint_ = 0;

var sameParams = "";

var oUrl = "";

var orgName = "";

var printDate = "";
/**
 * 终端打印方法
 *
 */
function doPrint(pPrintFlag,pListType,pServNumber,pMonth,pOrgName,pPrintDate,contextPath) {
	if(_isPrint_ == 1) {
		setProcessInfo("已打印，请等打印完毕，返回再查询打印。");
		return;
	}
	
	//pPrintFlag = 1;
	if(pPrintFlag!=1) {
		return;
	}
    
  orgName = pOrgName;
  printDate = pPrintDate;
  var typeName = ["全部清单","通话清单","短信清单","彩信清单","梦网清单","GPRS清单","WLAN清单","代收信息费清单","VPMN清单"];
  
  var listType = pListType;   //清单类型数字标志0表示全部
  var isPrintAll = null;
  if(listType=="0") 
  { //查询全部
  	isPrintAll = "YES";
  } else {
  	isPrintAll = "NO";
  }
   
  try {
  	//打印图标
  	var Ret3 = DPsListPrinter1.PrintPicture(1);
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
  	var Ret4 = DPsListPrinter1.PrintLine("  ");
  	Ret4 = DPsListPrinter1.PrintLine("  ");
  	Ret4 = DPsListPrinter1.PrintLine("  清单类型: "+typeName[listType]+"  ");
  	Ret4 = DPsListPrinter1.PrintLine("  手机号码: "+pServNumber);
  	Ret4 = DPsListPrinter1.PrintLine("  清单月份: "+pMonth);
  	Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------");  	    
    if (Ret4 == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }
  	
  	//设置打印状态为1,表示已经打印过一次。
  	_isPrint_ = 1;
  	
  	     
  	//第一次提交打印，打印清单的第一页        
  	var page = 1;
		oUrl = contextPath  + "/BusinessActon.do";
		sameParams = "?actionCase=printList&month=" + pMonth + "&isPrintAll=" + isPrintAll;
		var params = sameParams + "&listtype=" + listType + "&page="+page;		  		  
		setProcessInfo("正在打印，请等待...");
		hiddenFrameSubmit(oUrl+params);

	} catch(ex) {
	 	alertError("开始打印清单数据出错："+ex);
	 	cutPaper();//出现问题切纸
	}	
}


/**
* 提交隐藏域，取打印数据
*/
function hiddenFrameSubmit(pUrl) {
		try
		{			
			var oFrame = document.getElementById("processFrm");
			if(oFrame) {		
				oFrame.src = pUrl;				
			}								      
		} catch (err)
		{ 
			alertError("打印过程中,请求打印数据出错:" + err); 
			cutPaper();				
		}
}	//end of hiddenFrameSubmitf

//切纸      
function cutPaper() {
  	var ret5 = DPsListPrinter1.SetCutPaper();
  	if (ret5 == 1)
  	{
  		  alertError("警告:打印机缺纸或故障!");
  		  return;
  	}	
  	//alert("切纸");
}
      
//相应打印
function netload(printDataObject) {
	//如果没有取到打印数据,则切纸结束打印
	if( typeof(printDataObject)=="undefined"||printDataObject==null ) {
		alertError("取打印数据错误...");
		cutPaper();
		return;
	}
	
	try {
			var ret = null;
			//取错误，如果出现错误则停止打印
			var errmsg = getValue("errmsg");	
			if(errmsg!="") {
				alertError("打印发生错误,错误信息："+errmsg);				
				cutPaper();
				return;
			}
			
			//取总页码
			var pageCount = getValue("pageCount",0);								
			//取下一页码，打印第一页时候，返回的是2
			var page = getValue("page",0);			
			//取当前正在打印的清单						
			var currentListType = getValue("currentListType",0);				
			//取是否结束打印标志。
			var printTail = getValue("printTail");
			//取断是否打印清单名称信息标志
			var printTypeName = getValue("printTypeName");			  		  		
			//取断是否打印清单名称信息标志
			var processInfo = getValue("process");
				
			//alert("page:"+page+" pageCount:"+pageCount + " currentListType:" + currentListType);
					
			//如果是打印全部，并且打印第一页且存在数据的时候，先打印清单名						
			if(printTypeName!="") {
				  //alert(printTypeName);
				ret = DPsListPrinter1.PrintLine("  " +printTypeName)
			}
 		
			//开始打印清单数据
			if(pageCount > 0) {		
				//加载打印提示
 				setProcessInfo(processInfo);
  			
  			//取清单数据		
				var entries = getValue("records",[]);	
				//alert("entries.length:"+entries.length);	
				for(var i = 0;i<entries.length;i++) {
					ret = DPsListPrinter1.PrintLine(entries[i]); 				
				}
  		  if (ret == 1)
  		  {
  		      alertError("警告:打印机缺纸或故障!");
  		      return;
  		  }
  		}
  		              
			              
			//如果打印尾部标志为YES，则打印尾部，切纸。              
			if(printTail=="YES") {	              
 				 setProcessInfo("结束打印..."); 				 	 		 			  
 				 //打印说明
  		   var ret5 = DPsListPrinter1.PrintLine(" ---------------------------------------------"); 
  		   ret5 = DPsListPrinter1.PrintLine(" 说明:时长为单独通话时间(秒),费用单位为元,流量单位为K."); 
  		   ret5 = DPsListPrinter1.PrintLine(" 声明:本清单信息仅供客户核对之用,不做任何凭据.");
  		   ret5 = DPsListPrinter1.PrintLine(" 提示:为保护您的密码不被他人恶意窃用,请经常修改.");
  		   ret5 = DPsListPrinter1.PrintLine(" 以上内容,如有不详之处,请向营业员查询.");
  		   //delete begin g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
  	       //delete end g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
  		   ret5 = DPsListPrinter1.PrintLine(" ---------------------------------------------");
  		   ret5 = DPsListPrinter1.PrintLine(" 打印地点:"+orgName+".");
  		   ret5 = DPsListPrinter1.PrintLine(" 打印时间:"+printDate+".");
  		   if (ret5 == 1)
  		   {
  		       alertError("警告:打印机缺纸或故障!");
  		       return;
  		   }
  		   //切纸
  		   cutPaper();
  		  
			}	else { //否则继续提交打印请求。
				params = sameParams;
				params += ("&listtype="+currentListType+"&page="+page);	
			  hiddenFrameSubmit(oUrl+params);
			}
	} catch(ex) {
			alertError("打印清单数据出错:" + ex);
			cutPaper(); //出现问题则切纸			
	}
	
	//从打印对象中获取打印属性
	function getValue(keyV,defaultV) {
		if(printDataObject[keyV]) {				
			return printDataObject[keyV];
		}
		return (typeof(defaultV)!="undefined") ? defaultV : "";		
	}		
	
} //end of netload	
      
//设置打印状态    
function setProcessInfo(info) {
	  var oProcessDiv = document.getElementById("process");				
		//加载打印提示
	  if(info!="" && oProcessDiv) {  	
  		oProcessDiv.style.display = "";
	  	oProcessDiv.innerHTML = "<font color=red>" + info + "</font>";
	  }
}


