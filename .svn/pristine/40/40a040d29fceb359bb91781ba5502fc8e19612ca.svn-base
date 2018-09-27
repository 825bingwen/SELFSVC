/*
 * Add by xkf57421
 */

/**
 * 自助终端开户受理小票打印测试
 * @param {Object} piData
 * @return {TypeName} 
 */
function _installPrint(piData) 
{
	if (piData != "undefined") 
	{
		var tmpStr = "";
  		//打印头部信息
		tmpStr = tmpStr + "  " + "\n";
		tmpStr = tmpStr + "  " + "\n";
  	    tmpStr = tmpStr + "  自助终端开户受理交易凭据" + "\n";
		tmpStr = tmpStr + " ---------------------------------------------" + "\n";

    
        //打印具体缴费信息
  	    tmpStr = tmpStr + "  终端信息: " + piData.termId + "\n";
  	    tmpStr = tmpStr + "  预选号码: " + piData.servnumber + "\n";
  	    tmpStr = tmpStr + "  主体产品: " + piData.mainprodname + "\n";
  	    tmpStr = tmpStr + "  开户金额: " + piData.receptionFee + " 元\n";
  	    tmpStr = tmpStr + "  投币金额: " + piData.tMoney + " 元\n";
  	    tmpStr = tmpStr + "  受理时间: " + piData.pDealTime + "\n";
  	    
  	    if(piData.payStatus == 1)
  	    {
	  	    tmpStr = tmpStr + "  受理流水: " + piData.dealNum + "\n";
  	    }
  	    
  	    tmpStr = tmpStr + "  交易状态: " + piData.pDealStatus + "\n";
        
        //打印尾部信息
		tmpStr = tmpStr + " ---------------------------------------------" + "\n";
  	    tmpStr = tmpStr + "  以上内容,如有不详之处,请向营业员查询" + "\n";
  	    tmpStr = tmpStr + "  客户服务热线:10086" + "\n";
  	    tmpStr = tmpStr + " ---------------------------------------------" + "\n";
  	    tmpStr = tmpStr + "  打印地点:" + piData.pOrgName + "\n";
  	    tmpStr = tmpStr + "  打印时间:" + piData.pPrintDate + "\n";
  	    
  	    alert(tmpStr);

	} 
	else 
	{
	    alertError("对不起,没有可打印的数据!");
		return;
	}
}
/**
 * 自助终端开户受理小票打印
 * @param {Object} piData
 * @return {TypeName} 
 */
function installPrint(piData) 
{
	if (piData != "undefined") 
	{
		var ret = window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
		if ( ret == 1)
		{
		    alertError("对不起,票据打印机发生故障,请联系营业员!");
		    return;
		}
		else if (ret == 2) 
		{
		    alertError("对不起,票据打印机缺纸,请联系营业员!");
		    return;
		}
		else if (ret != 0)
		{
		    alertError("对不起,票据打印机发生错误:" + ret + ",请联系营业员!");
		    return;
		}
		
  	    //打印移动图标  	
		ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
		if (ret == 1) 
		{
  	   		alertError("警告:打印机缺纸或故障!");
			return;
		} 
		else 
		{
			if (ret == 41) 
			{
  	   			alertError("错误:打印机设备低层驱动程序未安装!");
				return;
			}
		}
  	     
  		//打印头部信息
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助终端开户受理交易凭据");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		if (ret == 1) 
		{
        	alertError("警告:打印机缺纸或故障!");
			return;
		}  	
    
        //打印具体缴费信息
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端信息: " + piData.termId);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  预选号码: " + piData.servnumber);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  主体产品: " + piData.mainprodname);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  开户金额: " + piData.receptionFee + " 元");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  投币金额: " + piData.tMoney + " 元");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  受理时间: " + piData.pDealTime);
  	    if(piData.payStatus == 1)
  	    {
	  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  受理流水: " + piData.dealNum);
  	    }
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态: " + piData.pDealStatus);
        
        //打印尾部信息
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  客户服务热线:10086");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印地点:" + piData.pOrgName);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间:" + piData.pPrintDate);
		if (ret == 1) 
		{
  	    	alertError("警告:打印机缺纸或故障!");
			return;
		}    
			
		//正常打印结束,切纸
		cutPaper();
	} 
	else 
	{
	    alertError("对不起,没有可打印的数据!");
		return;
	}
}


//切纸      
function cutPaper() 
{
  	var ret = window.parent.document.getElementById("prtpluginid").SetCutPaper();
  	if (ret == 1)
  	{
  		  alertError("警告:打印机缺纸或故障!");
  		  return;
  	}	
  	//alert("切纸");
}
      

