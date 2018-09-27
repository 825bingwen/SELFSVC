
function cutPaper() {
  	var ret = window.parent.document.getElementById("prtpluginid").SetCutPaper();
  	if (ret == 1)
  	{
  		  alertError("����:��ӡ��ȱֽ�����!");
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
  		  alertError("����:��Ʊ��ӡ��ȱֽ�����!");
  		  return 1;
  	}	
}
function cutInvoiceToBlack(){
	var ret = window.parent.document.getElementById("invprtpluginid").cutToBlack();	
  	if (ret == 1)
  	{
  		  alertError("����:��Ʊ��ӡ��ȱֽ�����!");
  		  return 1;
  	}
}
function printInvoiceWS(invoice) {
	try {
        var bossFormnum = getXmlInvoiceData(invoice, "bossFormnum");
        if(bossFormnum == "" || bossFormnum.length < 1){
            alertError("�Բ���,��Ʊ��Ϣ��ȫ,����ϵӪҵԱȷ�Ͻɷ��Ƿ�ɹ�!");
			return;
        }
		var v = checkInvoice();
		if (v != 0) {
		    alertError("�Բ���,��Ʊ��ӡ��ȱֽ,����ϵӪҵԱ!");
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
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("  �ƷѺϼƣ�" + sumFee + "    ��ǰ�»��ѣ�"+ curMonthFee +"\n"+"  ������"+ surplusFee);
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
	 	alertError("��ӡ�ɷѷ�Ʊ����");
	 	cutInvoiceToBlack();//����������ֽ
		return -1;
	}
}


// ѭ����ӡ�س�num��
function printInvoiceEnterSD(num)
{
	var prtObj;
	try
	{
		prtObj = window.parent.document.getElementById("invprtpluginid");
		if(prtObj == "" || prtObj == undefined)
		{
		    alertError("���ڷ�Ʊ��ӡ���ؼ�δ��װ�����ķ�Ʊ��ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
			return;
		}
	}
	catch(e)
	{
		alertError("���ڷ�Ʊ��ӡ���ؼ�δ��װ�����ķ�Ʊ��ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
		return;
	}
	var ret = 0;
	for(var i=0;i<num;i++)
	{
       //��ӡ�س�
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
		alertError("����:��ӡ���ؼ�δ��װ!");
		return;
  	}
  	if (ret == 1)
  	{
  	   alertError("����:��ӡ��ȱֽ�����!");
  	   return;
  	}
  	else if (ret == 41)
  	{
  	   alertError("����:��ӡ���豸�Ͳ���������δ��װ!");
  	   return;
  	}
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ֻ�����: "+pServNumber);
  	//add by sWX219697 
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ����������: " + custName);
  	if("1" == isFee)
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ֻ�֧�����˻���ֵƾ֤");
  	}
  	else
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �����ɷѻ�����ƾ��");
  	}
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        alertError("����:��ӡ��ȱֽ�����!");
        return;
    }  	
    
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ն�����  : "+pTerminalInfo + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ն���ˮ  : "+pTerminalSeq + ";");
  	if("1" == isFee)
  	{
	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  BOSS��ˮ  : "+pDealNum + ";");
	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ֧����ˮ  : "+mpay_seq + ";");
  	}
  	else
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ������  : "+pDealNum + ";");
  	}
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ����ʱ��  : "+pDealTime + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ���׽��  : "+pAmount + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ����״̬  : "+pDealStatus + ";");
  	if (printcontext != "")
	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ����ˢ������ƾ��");
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ֧����ˮ: " + pTerminalSeq);
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    	if (ret == 1)
    	{
        	alertError("����:��ӡ��ȱֽ�����!");
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
		unionPayFee = unionPayFee.substring(0, unionPayFee.length-2) + "." + unionPayFee.substring(unionPayFee.length-2, unionPayFee.length) + "Ԫ";
    	
    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  POS��ˮ��   : " + printcontext.substring(0, 6) + ";");
    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ���п���    : " + hiddenCardNumber + ";");
    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �����ο���  : " + printcontext.substring(43, 55) + ";");
    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ն˺�      : " + trim(printcontext.substring(55, 70)) + ";");
    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �̻���      : " + printcontext.substring(70, 85) + ";");
    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ���׽��    : " + unionPayFee + ";");    	
	}
    if (ret == 1)
    {
        alertError("����:��ӡ��ȱֽ�����!");
        return;
    }
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ��������,���в���֮��,����ӪҵԱ��ѯ.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ��ӡ�ص�:"+pOrgName+".");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ��ӡʱ��:"+pPrintDate+".");
  	if (ret == 1)
  	{
  	    alertError("����:��ӡ��ȱֽ�����!");
  	    return;
  	}
	 	cutPaper();
	} catch(ex) {
 		alertError("��ӡ�ɷ�ƾ������,����ϵӪҵ������Ա�鿴�ɷ��Ƿ�ɹ���");
 		cutPaper();//����������ֽ
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
			return "�Բ��𣬷�Ʊ��ӡʧ��";
		}
		return "";
	}
	catch(ex)
	{
		return "�Բ��𣬷�Ʊ��ӡʧ��";
	}
}