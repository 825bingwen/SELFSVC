
/**
 * 自助缴费凭条打印
 * pServNumber --号码
 * pOrgName    --打印地点
 * pPrintDate  --打印日期
 * pTerminalInfo --终端信息
 * pDealNum     --交易流水号
 * pDealTime   --交易时间
 * pAmount     --交易金额
 * pDealStatus --交易状态
 */
function doPrintPayProof(pServNumber,pOrgName,pPrintDate,pTerminalInfo,pDealNum,pDealTime,pAmount,pDealStatus) {
  try {
		var v = window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
		if ( v == 1){
		    alertError("对不起,票据打印机发生故障,请联系营业员!");
		    return;
		}else if (v == 2) {
		    alertError("对不起,票据打印机缺纸,请联系营业员!");
		    return;
		}else if (v != 0){
		    alertError("对不起,票据打印机发生错误:"+v+",请联系营业员!");
		    return;
		}
  
  	//打印移动图标 	
  	var ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
  	if (ret == 1)
  	{
  	   alertError("警告:打印机缺纸或故障!");
  	   return;
  	}
  	else if (ret == 41)
  	{
  	   alertError("错误:打印机设备低层驱动程序未安装!");
  	   return;
  	}
  	     
  	//打印头部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: "+pServNumber);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助缴费机交易凭据");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }  	
    
    //打印具体缴费信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端信息  : "+pTerminalInfo + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易流水号: "+pDealNum + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易时间  : "+pDealTime + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易金额  : "+pAmount + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态  : "+pDealStatus + ";");
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }     
    
    
    //打印尾部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  客户服务热线:10086.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印地点:"+pOrgName+".");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间:"+pPrintDate+".");
  	if (ret == 1)
  	{
  	    alertError("警告:打印机缺纸或故障!");
  	    return;
  	}    
			
		//正常打印结束,切纸
	 	cutPaper();
	} catch(ex) {
 		alertError("打印缴费凭条出错,请联系营业厅服务员查看缴费是否成功！");
 		cutPaper();//出现问题切纸
	}	
}

/**
 * 优惠受理自助缴费凭条打印
 * pServNumber --号码
 * pOrgName    --打印地点
 * pPrintDate  --打印日期
 * pTerminalInfo --终端信息
 * pDealNum     --交易流水号
 * pDealTime   --交易时间
 * pAmount     --交易金额
 * pDealStatus --交易状态
 */
function doPrintPrivPayProof(pServNumber, pOrgName, pPrintDate, pTerminalInfo, priAmount, privToMoney,
							 pRecDate, pBackAmount, pDealNum, pDealTime, pAmount, pDealStatus, pTerminalSeq,printFlag) {
  try {
		var v = window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
		if ( v == 1){
		    alertError("对不起,票据打印机发生故障,请联系营业员!");
		    return;
		}else if (v == 2) {
		    alertError("对不起,票据打印机缺纸,请联系营业员!");
		    return;
		}else if (v != 0){
		    alertError("对不起,票据打印机发生错误:"+v+",请联系营业员!");
		    return;
		}
  
  	//打印移动图标 	
  	var ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
  	if (ret == 1)
  	{
  	   alertError("警告:打印机缺纸或故障!");
  	   return;
  	}
  	else if (ret == 41)
  	{
  	   alertError("错误:打印机设备低层驱动程序未安装!");
  	   return;
  	}
  	     
  	//打印头部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: "+pServNumber);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助缴费机交易凭据");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }  	
    
    //打印具体缴费信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端信息  : "+pTerminalInfo + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  活动金额  : "+priAmount + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  投币金额  : "+privToMoney + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  受理时间  : "+pRecDate + ";");
	if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    } 
	if(printFlag != "0" ){
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易金额  : "+pAmount + ";");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易流水号: "+pDealNum + ";");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易时间  : "+pDealTime + ";");
	}

  	if(pBackAmount != ""){
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  退还金额  : "+pBackAmount + ";");
  	}
  	if(pDealStatus != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态  : "+pDealStatus + ";");
  	}
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }     
    
    
    //打印尾部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  客户服务热线:10086.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印地点:"+pOrgName+".");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间:"+pPrintDate+".");
  	if (ret == 1)
  	{
  	    alertError("警告:打印机缺纸或故障!");
  	    return;
  	}    
			
		//正常打印结束,切纸
	 	cutPaper();
	} catch(ex) {
 		alertError("打印缴费凭条出错,请联系营业厅服务员查看缴费是否成功！");
 		cutPaper();//出现问题切纸
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
      
//设置打印状态    
function setProcessInfo(info) {
	  var oProcessDiv = document.getElementById("process");				
		//加载打印提示
	  if(info!="" && oProcessDiv) {  	
  		oProcessDiv.style.display = "";
	  	oProcessDiv.innerHTML = "<font color=red>" + info + "</font>";
	  }
}

//返回打印机状态
function getPrinterStatus(){
	var ret = window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
	return ret;
}
//初始化打印机
function initPrinter(){
	window.parent.document.getElementById("prtpluginid").CloseCom();
	var ret = window.parent.document.getElementById("prtpluginid").InitListPrinter();
	//初始化图片路径
	window.parent.document.getElementById("prtpluginid").SetPicturePath("c:\\PsWorkXpe\\multimedia\\");	
	return ret;
}

//打开发票打印机
function oPenInvoice(com){
	var ret = window.parent.document.getElementById("invprtpluginid").OpenCom();
	return ret;
}
//初始化发票打印机
function initInvoice(){
	var ret = window.parent.document.getElementById("invprtpluginid").InitVoicePrint();
	return ret;
}
//检查发票打印机打印纸情况
function checkInvoice(){
	var ret = window.parent.document.getElementById("invprtpluginid").CheckPaper();
	return ret;
}

//发票打印机切纸      
function cutInvoicePaper(){
  	var ret = window.parent.document.getElementById("invprtpluginid").CutPaper();
  	if (ret == 1)
  	{
  		  alertError("警告:发票打印机缺纸或故障!");
  		  return 1;
  	}	
  	//alert("切纸");
}
//黑标处切纸
function cutInvoiceToBlack(){
	var ret = window.parent.document.getElementById("invprtpluginid").cutToBlack();
  	if (ret == 1)
  	{
  		  alertError("警告:发票打印机缺纸或故障!");
  		  return 1;
  	}
}

/**
 * 自助刷卡缴费银联小票
 * pServNumber --号码
 * pOrgName    --打印地点
 * pPrintDate  --打印日期
 * pTerminalInfo --终端信息
 * pDealNum     --交易流水号
 * pDealTime   --交易时间
 * pAmount     --交易金额
 * pDealStatus --交易状态
 */
function doPrintUnionBill(cardNum,unionFormnum,unionPayFee,unionPayTime,pServNumber,pOrgName,pPrintDate,pTerminalInfo,pDealStatus) {
  try {
  	//打印移动图标
  	var ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
  	if (ret == 1)
  	{
  	   alertError("警告:打印机缺纸或故障!");
  	   return;
  	}
  	else if (ret == 41)
  	{
  	   alertError("错误:打印机设备低层驱动程序未安装!");
  	   return;
  	}
  	     
  	//打印头部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  银联刷卡交易凭据");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  流水号: "+unionFormnum);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }
    
    //打印具体缴费信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易时间  : "+unionPayTime + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易金额  : "+unionPayFee + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码  : "+pServNumber + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端信息  : "+pTerminalInfo + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态  : "+pDealStatus + ";");
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }
    
    //打印尾部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  客户服务热线:10086.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印地点:"+pOrgName+".");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间:"+pPrintDate+".");
  	if (ret == 1)
  	{
  	    alertError("警告:打印机缺纸或故障!");
  	    return;
  	}    
			
		//正常打印结束,切纸
	 	cutPaper();
	} catch(ex) {
	 		alertError("打印缴费凭条出错,请联系营业厅服务员查看缴费是否成功！");
	 		cutPaper();//出现问题切纸
	}	
}
function printInvoice(printDate,username,telnumber,cardid,formnum,dealtime,Score,totalmoney,leftmoney,orgid,curMonthfee,sumfee,feeList){
    
    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			//缴费日期  格斯: 年  月  日
			if(printDate != "")
			{
				ret = window.parent.document.getElementById("invprtpluginid").PrintLine("                     "+printDate.substr(0,4)+"     "+printDate.substr(4,2)+"     "+printDate.substr(6,2));
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			else
			{
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			//缴费金额 格式: 元
			if(totalmoney != ""){
			    ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            "+totalmoney);
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}else{
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			//大写 格式： 仟  佰  拾  元
			if(capitalMoney != ""){
			    var capitals = capitalMoney.split(" ");
			    ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            "+capitals[0]+"   "+capitals[1]+"   "+capitals[2]+"   "+capitals[3]+"   ");
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}else{
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			//流水号
	        if(formnum != "")
			{
				ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            "+formnum);
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			else
			{
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			//----------------------
			//客户名称 
			if(username != "")
			{
				ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            "+username);
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			else
			{
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
	        //电话号码
			if(telnumber != "")
			{
				ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            "+telnumber);
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			else
			{
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			//计费期间
			if(dealtime != "")
			{
				ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            "+dealtime);
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			else
			{
				ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            自助终端现金缴费");
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			//消费金额
			if(sumfee != ""){
			    ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            "+dealtime);
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}else{
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			//最新余额
			if(leftmoney != "")
			{
				ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            "+leftmoney);
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			else
			{
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			//===============
			//消费明细
			if(feeList != ""){
			    ret = window.parent.document.getElementById("invprtpluginid").PrintLine(feeList);
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}else{
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			//计费金额合计：xxx  当前月话费：xxx
			if(sumfee != "" && curMonthfee != ""){
			    ret = window.parent.document.getElementById("invprtpluginid").PrintLine("计费金额合计："+sumfee+"    "+"当前月话费："+curMonthfee);
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}else{
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			//最新余额:xxx
            if(leftmoney != "")
			{
				ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            "+leftmoney);
				ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			else
			{
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			//===============
			//积分：xx    单位名称：xxxxxx
			if(Score != ""){
			    ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        "+Score);
			}else{}
			if(orgid != ""){
			    ret = window.parent.document.getElementById("invprtpluginid").PrintLine("                    "+orgid);
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}else{
			    ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			}
			
		  //==============
			
		  //定位黑标
			ret = window.parent.document.getElementById("invprtpluginid").ToBlackMark();
			if(ret == 62)
			{
				cutInvoicePaper();
				alertError("警告:打印机缺纸或故障!");
			  return 62;
			}
			else if(ret == 61)
			{
				cutInvoicePaper();
				alertError("打印机初始化失败(故障)");
				return 61;
			}
			//调整切纸位置到黑标处
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();

			if(operatorid != "")
			{
				ret = window.parent.document.getElementById("invprtpluginid").PrintLine("   自助缴费终端            "+operatorid);
			}
		  if (ret == 1)
		  {
		  		cutInvoicePaper();
		      alertError("警告:发票打印机缺纸或故障!");
		      return 1;
		  }
	
			
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			if(ret == 62)
			{
				cutInvoicePaper();
				alertError("警告:发票打印机缺纸或故障!");
			  return 62;
			}
			else if(ret == 61)
			{
				cutInvoicePaper();
				alertError("发票打印机初始化失败(故障)");
				return 61;
			}
			//反向走纸
			ret = window.parent.document.getElementById("invprtpluginid").BackPaper(6);
		//正常打印结束,切纸
		 	cutInvoicePaper();
	 		return ret;
		
}

function printInvoiceWS(invoice) {
	try {

        //检查打印机是否有纸
		var v = checkInvoice();
		if (v != 0) {
		    alertError("对不起,发票打印机缺纸,请联系营业员!");
			return;
		}
		//定位黑标
		//ret = document.getElementById("invprtpluginid").ToBlackMark();
		//if(ret == 62)
		//	{
		//		cutInvoicePaper();
		//		alert("警告:打印机缺纸或故障!");
		//	  return 62;
		//	}
		//	else if(ret == 61)
		//	{
		//		cutInvoicePaper();
		//		alert("打印机初始化失败(故障)");
		//		return 61;
		//	}
        //开始打印
		var ret = 0;
		ret = window.parent.document.getElementById("invprtpluginid").absolutePosition(7);
		//反向走纸
		//ret = window.parent.document.getElementById("invprtpluginid").BackPaper(30);
		
		//打印地点
		//var pOrgName = getXmlInvoiceData(invoice, "pOrgName");
        //缴费时间
		var dealTime = getXmlInvoiceData(invoice, "dealTime");
        
		//alert("缴费时间==>"+dealTime);
		if (dealTime != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            " + dealTime.substr(0, 4) + "      " + dealTime.substr(4, 2) + "      " + dealTime.substr(6, 2));
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
        //缴费金额
		var tMoney = getXmlInvoiceData(invoice, "tMoney");
		if (tMoney != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + tMoney.toString());
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
	    //缴费金额大写
		var capitalFee = getXmlInvoiceData(invoice, "capitalFee");
		if (capitalFee != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            " + capitalFee);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
        //缴费流水
		var dealNum = getXmlInvoiceData(invoice, "dealNum");
		if (dealNum != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + dealNum);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
        //用户名
		var subsName = getXmlInvoiceData(invoice, "subsName");
		if (subsName != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("          " + subsName);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
        //手机号码
		var servNumber = getXmlInvoiceData(invoice, "servNumber");
		if (servNumber != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("          " + servNumber);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
        //计费期间
		var feeTime = getXmlInvoiceData(invoice, "feeTime");
		if (feeTime != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("          " + feeTime);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
        //当前月话费
		var curMonthFee = getXmlInvoiceData(invoice, "curMonthFee");
		if (curMonthFee != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("          " + curMonthFee);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
        //最新余额
		var surplusFee = getXmlInvoiceData(invoice, "surplusFee");
		if (surplusFee != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("          " + surplusFee);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		//调整位置
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		
		//消费明细
		var consumeList = getXmlInvoiceData(invoice, "consumeList");
		if (consumeList != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("  " + consumeList);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		//ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		//反向走纸
		//ret = window.parent.document.getElementById("invprtpluginid").BackPaper(255);
		//计费合计、当前月话费、最新余额
		var sumFee = getXmlInvoiceData(invoice, "sumFee");
		var curMonthFee = getXmlInvoiceData(invoice, "curMonthFee");
		var surplusFee = getXmlInvoiceData(invoice, "surplusFee");
		
		if (sumFee != "" && curMonthFee != "" && surplusFee != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("  计费合计：" + sumFee + "    当前月话费："+ curMonthFee +"\n"+"  最新余额："+ surplusFee);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        //M值
		var MScore = getXmlInvoiceData(invoice, "MScore");
	    //营业厅
		var pOrgName = getXmlInvoiceData(invoice, "pOrgName");
		if (MScore != "" || pOrgName != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("         " + MScore + "                  " + pOrgName);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		//正常打印结束,切纸
		cutInvoiceToBlack();
		return ret;
	}
	catch (e) {
	 	alertError("打印缴费发票出错！");
	 	cutInvoiceToBlack();//出现问题切纸
		return -1;
	}
}

//取XML发票数据
function getXmlInvoiceData(invoice, itemname) {
	var items = invoice.getElementsByTagName(itemname);
	var itemdata = "";
	if (items.length > 0) {
		itemdata = items[0].childNodes[0].nodeValue;
	}
	return itemdata;
}
