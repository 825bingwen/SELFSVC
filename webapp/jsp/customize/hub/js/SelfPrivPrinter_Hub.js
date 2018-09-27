/*
 * Add by Lifeng
 */

/**
 * 优惠受理小票打印测试
 * @param {Object} prtData
 * @return {TypeName} 
 */
function _recNotePrint(prtData) 
{
	if (prtData != "undefined") 
	{
		var tmpStr = "";
  		//打印头部信息
		tmpStr = tmpStr + "  " + "\n";
		tmpStr = tmpStr + "  " + "\n";
  	    tmpStr = tmpStr + "  手机号码: " + prtData.servnumber + "\n";
  	    //tmpStr = tmpStr + "  用 户 名: " + prtData.username + "\n";
  	    tmpStr = tmpStr + "  自助缴优惠受理交易凭据" + "\n";
		tmpStr = tmpStr + " ---------------------------------------------" + "\n";

    
        //打印具体缴费信息
  	    tmpStr = tmpStr + "  终端信息: " + prtData.termID + "\n";
  	    tmpStr = tmpStr + "  优惠名称: " + prtData.groupname + "\n";
  	    tmpStr = tmpStr + "  活动应收: " + prtData.prepayfee + " 元\n";
  	    tmpStr = tmpStr + "  投币金额: " + prtData.tMoney + " 元\n";
  	    if(prtData.recDate != "")
  	    {
  	    	tmpStr = tmpStr + "  受理时间: " + prtData.recDate + "\n";
  	    }
  	    if(prtData.payStatus == 4 || prtData.payStatus == 6)
  	    {
  	        if(prtData.payStatus == 4){
	  	    tmpStr = tmpStr + "  缴费金额: " + prtData.tmpMoney + " 元\n";
	  	    }
	  	    if(prtData.payStatus == 6){
	  	    tmpStr = tmpStr + "  缴费金额: " + prtData.tMoney + " 元\n";
	  	    }
	  	    tmpStr = tmpStr + "  缴费流水: " + prtData.dealNum + "\n";
	  	    tmpStr = tmpStr + "  缴费时间: " + prtData.dealTime + "\n";
  	    }
  	    if(prtData.payStatus == 1 || prtData.payStatus == 5)
  	    {
  	        tmpStr = tmpStr + "  退还金额: " + prtData.tMoney + " 元\n";
  	    }
  	    if(prtData.payStatus == 3)
  	    {
  	        tmpStr = tmpStr + "  退还金额: " + prtData.tmpMoney + " 元\n";
  	    }
  	    tmpStr = tmpStr + "  交易状态: " + prtData.transResult + "\n";
        //打印尾部信息
		tmpStr = tmpStr + " ---------------------------------------------" + "\n";
  	    tmpStr = tmpStr + "  以上内容,如有不详之处,请向营业员查询" + "\n";
  	    tmpStr = tmpStr + "  客户服务热线:10086" + "\n";
  	    tmpStr = tmpStr + " ---------------------------------------------" + "\n";
  	    tmpStr = tmpStr + "  打印地点:" + prtData.pOrgName + "\n";
  	    tmpStr = tmpStr + "  打印时间:" + prtData.pPrintDate + "\n";
  	    
  	    alert(tmpStr);

	} 
	else 
	{
	    alertError("对不起,没有可打印的数据!");
		return;
	}
}
/**
 * 优惠受理小票打印
 * @param {Object} prtData
 * @return {TypeName} 
 */
function recNotePrint(prtData) 
{
	if (prtData != "undefined") 
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
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: " + prtData.servnumber);
  	    //ret = window.parent.document.getElementById("prtpluginid").PrintLine("  用 户 名: " + prtData.username);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助缴优惠受理交易凭据");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		if (ret == 1) 
		{
        	alertError("警告:打印机缺纸或故障!");
			return;
		}  	
    
        //打印具体缴费信息
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端信息: " + prtData.termID);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  优惠名称: " + prtData.groupname);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  活动应收: " + prtData.prepayfee + " 元");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  投币金额: " + prtData.tMoney + " 元");
  	    if(prtData.recDate != "")
  	    {
  	        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  受理时间: " + prtData.recDate);
  	    }
  	    if(prtData.payStatus == 4 || prtData.payStatus == 6)
  	    {
  	        if(prtData.payStatus == 4)
  	        {
	  	    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费金额: " + prtData.tmpMoney + " 元");
	  	    }
	  	    if(prtData.payStatus == 6)
  	        {
	  	    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费金额: " + prtData.tMoney + " 元");
	  	    }
	  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费流水: " + prtData.dealNum);
	  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费时间: " + prtData.dealTime);
  	    }
  	    if(prtData.payStatus == 1 || prtData.payStatus == 5)
  	    {
  	        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  退还金额: " + prtData.tMoney + " 元");
  	    }
  	    if(prtData.payStatus == 3)
  	    {
  	        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  退还金额: " + prtData.tmpMoney + " 元");
  	    }
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态: " + prtData.transResult);
        //打印尾部信息
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  客户服务热线:10086");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印地点:" + prtData.pOrgName);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间:" + prtData.pPrintDate);
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
function cutPaper() {
  	var ret = window.parent.document.getElementById("prtpluginid").SetCutPaper();
  	if (ret == 1)
  	{
  		  alertError("警告:打印机缺纸或故障!");
  		  return;
  	}	
  	//alert("切纸");
}
      

