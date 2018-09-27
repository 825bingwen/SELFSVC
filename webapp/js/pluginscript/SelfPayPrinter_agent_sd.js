
function cutPaper() {
  	var ret = window.parent.document.getElementById("prtpluginid").SetCutPaper();
  	if (ret == 1)
  	{
  		  alertError("警告:打印机缺纸或故障!");
  		  return;
  	}	
}
function alertError(info) {
	  var oProcessDiv = document.getElementById("process");				
	  if(info!="" && oProcessDiv) {
  		oProcessDiv.style.display = "";
	  	oProcessDiv.innerHTML = "<font color=red>" + info + "</font>";
	  }
}
function getPrinterStatus(){
	var ret = window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
	return ret;
}
function initPrinter(){
	window.parent.document.getElementById("prtpluginid").CloseCom();
	var ret = window.parent.document.getElementById("prtpluginid").InitListPrinter();
	window.parent.document.getElementById("prtpluginid").SetPicturePath("c:\\PsWorkXpe\\multimedia\\");	
	return ret;
}
function oPenInvoice(com){
	var ret = window.parent.document.getElementById("invprtpluginid").OpenCom();
	return ret;
}
function initInvoice(){
	var ret = window.parent.document.getElementById("invprtpluginid").InitVoicePrint();
	return ret;
}
function checkInvoice(){
	var ret = window.parent.document.getElementById("invprtpluginid").CheckPaper();
	return ret;
}    
function cutInvoicePaper() {
  	var ret = window.parent.document.getElementById("invprtpluginid").CutPaper();
  	if (ret == 1)
  	{
  		  alertError("警告:发票打印机缺纸或故障!");
  		  return 1;
  	}	
}
function cutInvoiceToBlack(){
	var ret = window.parent.document.getElementById("invprtpluginid").cutToBlack();	
  	if (ret == 1)
  	{
  		  alertError("警告:发票打印机缺纸或故障!");
  		  return 1;
  	}
}
function printInvoiceWS(invoice) {
	try {
        var bossFormnum = getXmlInvoiceData(invoice, "bossFormnum");
        if(bossFormnum == "" || bossFormnum.length < 1){
            alertError("对不起,发票信息不全,请联系营业员确认缴费是否成功!");
			return;
        }
		var v = checkInvoice();
		if (v != 0) {
		    alertError("对不起,发票打印机缺纸,请联系营业员!");
			return;
		}
		var ret = 0;
		ret = window.parent.document.getElementById("invprtpluginid").absolutePosition(7);
		var dealTime = getXmlInvoiceData(invoice, "dealTime");
		if (dealTime != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + dealTime.substr(0, 4) + "     " + dealTime.substr(4, 2) + "    " + dealTime.substr(6, 2));
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		var tMoney = getXmlInvoiceData(invoice, "tMoney");
		if (tMoney != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + tMoney.toString());
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		var capitalFee = getXmlInvoiceData(invoice, "capitalFee");
		if (capitalFee != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            " + capitalFee);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		var dealNum = getXmlInvoiceData(invoice, "dealNum");
		if (dealNum != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            " + dealNum);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		var subsName = getXmlInvoiceData(invoice, "subsName");
		if (subsName != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + subsName);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		var servNumber = getXmlInvoiceData(invoice, "servNumber");
		if (servNumber != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + servNumber);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		var feeTime = getXmlInvoiceData(invoice, "feeTime");
		if (feeTime != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + feeTime);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
        var curMonthFee = getXmlInvoiceData(invoice, "sumFee");
		if (curMonthFee != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + curMonthFee);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		var surplusFee = getXmlInvoiceData(invoice, "surplusFee");
		if (surplusFee != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + surplusFee);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		var consumeList = getXmlInvoiceData(invoice, "consumeList");
		if (consumeList != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("  " + consumeList);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
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
		var MScore = getXmlInvoiceData(invoice, "MScore");
		var pOrgName = getXmlInvoiceData(invoice, "pOrgName");
		if (MScore != "" || pOrgName != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("          " + MScore + "                  " + pOrgName);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		cutInvoiceToBlack();
		return ret;
	}
	catch (e) {
	 	alertError("打印缴费发票出错！");
	 	cutInvoiceToBlack();//出现问题切纸
		return -1;
	}
}


// 循环打印回车num次
function printInvoiceEnterSD(num)
{
	var prtObj;
	try
	{
		prtObj = window.parent.document.getElementById("invprtpluginid");
		if(prtObj == "" || prtObj == undefined)
		{
		    alertError("由于发票打印机控件未安装，您的发票打印失败，给您带来的不便，敬请原谅。");
			return;
		}
	}
	catch(e)
	{
		alertError("由于发票打印机控件未安装，您的发票打印失败，给您带来的不便，敬请原谅。");
		return;
	}
	var ret = 0;
	for(var i=0;i<num;i++)
	{
       //打印回车
	   ret =prtObj.PrintEnter();
	}
	return ret;
}

function getXmlInvoiceData(invoice, itemname) {
	var items = invoice.getElementsByTagName(itemname);
	var itemdata = "";
	if (items.length > 0) {
		itemdata = items[0].childNodes[0].nodeValue;
	}
	return itemdata;
}
function doPrintPayProof_SD(pServNumber,pOrgName,pPrintDate,pTerminalInfo,pDealNum,pDealTime,pAmount,
		pDealStatus,pTerminalSeq,mpay_seq,isFee, custName, printcontext) {
  try {
  
  	var ret;
  	try{
  		ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
  	}
  	catch(e)
  	{
		alertError("警告:打印机控件未安装!");
		return;
  	}
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
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: "+pServNumber);
  	//add by sWX219697 
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  代理商名称: " + custName);
  	if("1" == isFee)
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机支付主账户充值凭证");
  	}
  	else
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助缴费机交易凭据");
  	}
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }  	
    
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端名称  : "+pTerminalInfo + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端流水  : "+pTerminalSeq + ";");
  	if("1" == isFee)
  	{
	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  BOSS流水  : "+pDealNum + ";");
	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  支付流水  : "+mpay_seq + ";");
  	}
  	else
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  受理编号  : "+pDealNum + ";");
  	}
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易时间  : "+pDealTime + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易金额  : "+pAmount + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态  : "+pDealStatus + ";");
  	if (printcontext != "")
	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  银联刷卡交易凭据");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  支付流水: " + pTerminalSeq);
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    	if (ret == 1)
    	{
        	alertError("警告:打印机缺纸或故障!");
        	return;
    	}
    	var cardNumber = printcontext.substring(18, 37);
    	while (cardNumber.charAt(cardNumber.length - 1) == " ") 
		{
			cardNumber = cardNumber.substring(0, cardNumber.length - 1);
		}
		while (cardNumber.charAt(0) == " ") 
		{
			cardNumber = cardNumber.substring(1, cardNumber.length);
		}
		var hiddenCardNumber = cardNumber;
		if (cardNumber.length > 10)
		{
			hiddenCardNumber = cardNumber.substring(0, 6) + "******" + cardNumber.substring(cardNumber.length - 4, cardNumber.length);
		}
		var unionPayFee = printcontext.substring(85, 97);
		var unionPayFeeFront = unionPayFee.substring(0, unionPayFee.length-3);
		while (unionPayFeeFront.charAt(0) == "0") 
		{
			unionPayFeeFront = unionPayFeeFront.substring(1, unionPayFeeFront.length);
		}
		
		unionPayFee = unionPayFeeFront + unionPayFee.substring(unionPayFee.length-3, unionPayFee.length);
		unionPayFee = unionPayFee.substring(0, unionPayFee.length-2) + "." + unionPayFee.substring(unionPayFee.length-2, unionPayFee.length) + "元";
    	
    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  POS流水号   : " + printcontext.substring(0, 6) + ";");
    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  银行卡号    : " + hiddenCardNumber + ";");
    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  检索参考号  : " + printcontext.substring(43, 55) + ";");
    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端号      : " + trim(printcontext.substring(55, 70)) + ";");
    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  商户号      : " + printcontext.substring(70, 85) + ";");
    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易金额    : " + unionPayFee + ";");    	
	}
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印地点:"+pOrgName+".");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间:"+pPrintDate+".");
  	if (ret == 1)
  	{
  	    alertError("警告:打印机缺纸或故障!");
  	    return;
  	}
	 	cutPaper();
	} catch(ex) {
 		alertError("打印缴费凭条出错,请联系营业厅服务员查看缴费是否成功！");
 		cutPaper();//出现问题切纸
	}	
}

function trim(str) 
{
	while (str.charAt(str.length - 1) == " ") 
	{
		str = str.substring(0, str.length - 1);
	}
	
	while (str.charAt(0) == " ") 
	{
		str = str.substring(1, str.length);
	}
	
	return str;
}
function cutInvoiceToBlackNX()
{
	try
	{
		var ret = window.parent.document.getElementById("invprtpluginid").cutToBlack();
		if (ret != 0)
		{
			return "对不起，发票打印失败";
		}
		return "";
	}
	catch(ex)
	{
		return "对不起，发票打印失败";
	}
}