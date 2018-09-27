function doPrintPayProofhub(pServNumber,pOrgName,pPrintDate,pTerminalInfo,pDealNum,pDealTime,pAmount,pDealStatus,pTerminalSeq,pbrand,mpay_seq,isFee,presentFee) {
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
  	
  	//add begin sWX219697 2015-4-7 10:17:30 OR_NX_201501_1030 跨省异地缴费
  	if(pbrand != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  用户品牌: "+pbrand);
  	}
  	//add end sWX219697 2015-4-7 10:17:30 OR_NX_201501_1030 跨省异地缴费
  	
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
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端信息  : "+pTerminalInfo + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  支付流水  : "+pTerminalSeq + ";");
  	if("1" == isFee)
  	{
	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  BOSS流水  : "+pDealNum + ";");
	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  支付流水  : "+mpay_seq + ";");
  	}
  	else
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易流水  : "+pDealNum + ";");
  	}
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易时间  : "+pDealTime + ";");
  	
  	//modify begin g00140516 2012/08/03 R003C12L08n01 OR_NX_201207_779
  	if (pAmount != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易金额  : "+pAmount + ";");
  	}
  	//modify end g00140516 2012/08/03 R003C12L08n01 OR_NX_201207_779
  	
  	//add begin sWX219697 2015-1-4 15:59:36 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
  	if (presentFee != "" && presentFee != "0")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  赠送金额  : "+presentFee + "元;");
  	}
  	//add end sWX219697	2015-1-4 15:59:36 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
  	
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态  : "+pDealStatus + ";");
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
function doPrintPayProof(pServNumber,pOrgName,pPrintDate,pTerminalInfo,pDealNum,pDealTime,pAmount,pDealStatus,pTerminalSeq,mpay_seq,isFee) {
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
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端信息  : "+pTerminalInfo + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  支付流水  : "+pTerminalSeq + ";");
  	if("1" == isFee)
  	{
	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  BOSS流水  : "+pDealNum + ";");
	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  支付流水  : "+mpay_seq + ";");
  	}
  	else
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易流水  : "+pDealNum + ";");
  	}
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易时间  : "+pDealTime + ";");
  	if (pAmount != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易金额  : "+pAmount + ";");
  	}
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态  : "+pDealStatus + ";");
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
function doPrintPayProof_wband(pServNumber,pOrgName,pPrintDate,pTerminalInfo,pDealNum,pDealTime,pAmount,pDealStatus,pTerminalSeq,mpay_seq,isFee) {
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
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  宽带号码: "+pServNumber);
	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助缴费机交易凭据");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端信息  : "+pTerminalInfo + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  支付流水  : "+pTerminalSeq + ";");
	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易流水  : "+pDealNum + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易时间  : "+pDealTime + ";");
  	if (pAmount != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易金额  : "+pAmount + ";");
  	}
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态  : "+pDealStatus + ";");
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
function doPrintUnionBill_hb(printcontext,pTerminalSeq,pOrgName,pPrintDate) {
  try {
  	var printcontexts = printcontext.split('~');
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
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  银联刷卡交易凭据");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  支付流水: "+pTerminalSeq);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }
	if (printcontexts[0] != "")
	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  商户编号  : "+ printcontexts[0] + ";");
  	}
  	if (printcontexts[1] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端编号  : "+ printcontexts[1] + ";");
  	}
  	if (printcontexts[2] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易类型  : "+ printcontexts[2] + ";");
  	}
  	if (printcontexts[3] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  银行卡号  : "+ printcontexts[3] + ";");
  	}
  	if (printcontexts[4] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  批 次 号  : "+ printcontexts[4] + ";");
  	}
  	if (printcontexts[5] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  授 权 码  : "+ printcontexts[5] + ";");
  	}
  	if (printcontexts[6] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易参考号  : "+ printcontexts[6] + ";");
  	}
  	if (printcontexts[7] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  日期时间  : "+ printcontexts[7] + ";");
  	}
  	if (printcontexts[8] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  凭 证 号  : "+ printcontexts[8] + ";");
  	}
  	if (printcontexts[9] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  金额(RMB)  : "+ printcontexts[9] + ";");
  	}
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }
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
	 	cutPaper();
	 	
	} catch(ex) {
	 		alertError("打印缴费凭条出错,请联系营业厅服务员查看缴费是否成功！");
	 		cutPaper();//出现问题切纸
	}
}
function doPrintUnionBill_hb2(printcontext,pTerminalSeq,pOrgName,pPrintDate) {
  try {
  	var printcontexts = printcontext.split('~');
  	var ret;
  	try{
  		ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
  	}
  	catch(e)
  	{
		return;
  	}
  	if (ret == 1)
  	{
  	   return;
  	}
  	else if (ret == 41)
  	{
  	   return;
  	}
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  银联刷卡交易凭据");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  支付流水: "+pTerminalSeq);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        return;
    }
	if (printcontexts[0] != "")
	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  商户编号  : "+ printcontexts[0] + ";");
  	}
  	if (printcontexts[1] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端编号  : "+ printcontexts[1] + ";");
  	}
  	if (printcontexts[2] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易类型  : "+ printcontexts[2] + ";");
  	}
  	if (printcontexts[3] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  银行卡号  : "+ printcontexts[3] + ";");
  	}
  	if (printcontexts[4] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  批 次 号  : "+ printcontexts[4] + ";");
  	}
  	if (printcontexts[5] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  授 权 码  : "+ printcontexts[5] + ";");
  	}
  	if (printcontexts[6] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易参考号  : "+ printcontexts[6] + ";");
  	}
  	if (printcontexts[7] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  日期时间  : "+ printcontexts[7] + ";");
  	}
  	if (printcontexts[8] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  凭 证 号  : "+ printcontexts[8] + ";");
  	}
  	if (printcontexts[9] != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  金额(RMB)  : "+ printcontexts[9] + ";");
  	}
    if (ret == 1)
    {
        return;
    }
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  客户服务热线:10086.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印地点:"+pOrgName+".");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间:"+pPrintDate+".");
  	if (ret == 1)
  	{
  	    return;
  	}
	 	cutPaper();
	 	
	} catch(ex) {
	 		cutPaper();//出现问题切纸
	}	
	
}
function doPrintPayProof_NX(pServNumber,pOperID,pDealNum,pDealTime,pAmount, pSubsName, pLatestBalance) 
{
    try 
    {	
  	    var ret;
	  	try
	  	{
	  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("      中国移动通信集团宁夏有限公司预收话费收据");
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
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  顾客您好，您已缴费成功，请拿好凭条");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  成功缴费金额: " + pAmount);
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
	  	
	  	// modify begin hWX5316476 2015-4-14 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
	  	if(pLatestBalance != "")
	  	{
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  最新余额: " + pLatestBalance);
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费客户: " + pSubsName);	
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
		}
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费号码: " + pServNumber);
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费时间: " + pDealTime);
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
	  	if(pDealNum != "")
	  	{
	  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费流水号: " + pDealNum);
	  	}
	  	// modify end hWX5316476 2015-4-14 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
	  	
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  收费员工号: " + pOperID);
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  如有其它疑问请与营业员联系");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  客户服务热线：10086");
	 	cutPaper();
	} 
	catch(ex) 
	{
	 		alertError("打印缴费凭条出错,请联系营业厅服务员查看缴费是否成功！");
	 		cutPaper();//出现问题切纸
	}	
}

// 宁夏在线开户打印在线开户缴费凭条
function doPrintPayProofProdInstall_NX(pServNumber,pOperID,pDealNum,pDealTime,pAmount, pSubsName) {
  try {	
  	var ret = window.parent.document.getElementById("prtpluginid").PrintLine("      中国移动通信集团宁夏有限公司在线开户收据");
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
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  顾客您好，您已开户成功，请拿好凭条");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  成功缴费金额: " + pAmount);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费客户: " + pSubsName);	
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费号码: " + pServNumber);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费时间: " + pDealTime);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费流水号: " + pDealNum);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  收费员工号: " + pOperID);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  如有其它疑问请与营业员联系");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  客户服务热线：10086");
	cutPaper();
	} catch(ex) {
	 		alertError("打印缴费凭条出错,请联系营业厅服务员查看缴费是否成功！");
	 		cutPaper();//出现问题切纸
	}	
}

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
function printInvoiceHB(invoice,operid) 
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
	try
	{
		var ret = 0; 
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var chargeDate = getXmlInvoiceData(invoice, "chargeDate");
		if(chargeDate != "")
		{
			ret = prtObj.PrintLine("                    "+chargeDate.substr(0,4)+"     "+chargeDate.substr(4,2)+"     "+chargeDate.substr(6,2));
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var username = getXmlInvoiceData(invoice, "username");
		if(username != "")
		{
			ret = prtObj.PrintLine("            "+username);
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var telnumber = getXmlInvoiceData(invoice, "telnumber");
		if(telnumber != "")
		{
			ret = prtObj.PrintLine("            "+telnumber);
		}
		
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var cardid = getXmlInvoiceData(invoice, "cardid");
		if(cardid != "")
		{
			ret = prtObj.PrintLine("            "+cardid); 
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var formnum = getXmlInvoiceData(invoice, "formnum");
		if(formnum != "")
		{
			ret = prtObj.PrintLine("            "+formnum);
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var paynumno = getXmlInvoiceData(invoice, "paynumno");
		if(paynumno != "")
		{
			ret = prtObj.PrintLine("            "+paynumno);
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var callfeeCreateTime = getXmlInvoiceData(invoice, "callfeeCreateTime");
		if(callfeeCreateTime != "")
		{
			ret = prtObj.PrintLine("            "+callfeeCreateTime);
		}
		else
		{
			ret = prtObj.PrintLine("            自助终端缴费");
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var invoicefee = getXmlInvoiceData(invoice, "invoicefee");
		if(invoicefee != "")
		{
			ret = prtObj.PrintLine("            "+invoicefee);
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var Score = getXmlInvoiceData(invoice, "Score");
		if(Score != "")
		{
			if(Score.indexOf("@") > 0 && Score.indexOf("@") < (Score.length-1))
			{
				ret = prtObj.PrintLine("            "+Score.substr(Score.indexOf("@")+1));
			}
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var InvoiceNote = getXmlInvoiceData(invoice, "InvoiceNote");
		if(InvoiceNote != "")
		{
			ret = prtObj.PrintLine("      "+InvoiceNote);
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		
		var printtime = getXmlInvoiceData(invoice, "printtime");
		if(printtime != "")
		{
			if(printtime.indexOf("@") > 0 && printtime.indexOf("@") < (printtime.length-1))
			{
				var tmp = /@/g;
				ret = prtObj.PrintLine(printtime.replace(tmp,":"));
			}
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var totalmoney = getXmlInvoiceData(invoice, "totalmoney");
		if(totalmoney != "")
		{
			if(totalmoney.indexOf("@") > 0 && totalmoney.indexOf("@") < (totalmoney.length-1))
			{
				var tmp = /@/g;
				ret = prtObj.PrintLine(totalmoney.replace(tmp,":"));
			}
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		
		var leftmoney = getXmlInvoiceData(invoice, "leftmoney");
		if(leftmoney != "")
		{
			if(leftmoney.indexOf("@") > 0 && leftmoney.indexOf("@") < (leftmoney.length-1))
			{
				var tmp = /@/g;
				ret = prtObj.PrintLine(leftmoney.replace(tmp,":"));
			}
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		
		var overdraft = getXmlInvoiceData(invoice, "overdraft");
		if(overdraft != "")
		{
			if(overdraft.indexOf("@") > 0 && overdraft.indexOf("@") < (overdraft.length-1))
			{
				var tmp = /@/g;
				ret = prtObj.PrintLine("    "+overdraft.replace(tmp,":"));
			}
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var agreementleftbal = getXmlInvoiceData(invoice, "agreementleftbal");
		if(agreementleftbal != "")
		{
			if(agreementleftbal.indexOf("@") > 0 && agreementleftbal.indexOf("@") < (agreementleftbal.length-1))
			{
				var tmp = /@/g;
				ret = prtObj.PrintLine(agreementleftbal.replace(tmp,":"));
			}
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var agreementleftbal_Z = getXmlInvoiceData(invoice, "agreementleftbal_Z");
		if(agreementleftbal_Z != "")
		{
			if(agreementleftbal_Z.indexOf("@") > 0 && agreementleftbal_Z.indexOf("@") < (agreementleftbal_Z.length-1))
			{
				var tmp = /@/g;
				ret = prtObj.PrintLine(agreementleftbal_Z.replace(tmp,":"));
			}
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.ToBlackMark();
		if(ret == 62)
		{
			cutInvoicePaper();
			alert("警告:打印机缺纸或故障!");
		  return 62;
		}
		else if(ret == 61)
		{
			cutInvoicePaper();
			alert("打印机初始化失败(故障)");
			return 61;
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		if(operid != "")
		{
			ret = prtObj.PrintLine("   自助缴费终端            "+operid);
		}
		if (ret == 1)
		{
				cutInvoicePaper();
		    alertError("警告:发票打印机缺纸或故障!");
		    return 1;
		}
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
		ret = prtObj.BackPaper(5);
		cutInvoiceToBlack();
		return ret;
	}
	catch (e) 
	{
		
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

function printInvoiceSD(invoice) {
	try 
	{
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
		ret = prtObj.absolutePosition(7);
		ret = prtObj.PrintLine("");
		var dealNum = getXmlInvoiceData(invoice, "dealNum");
		if (dealNum != "") {
			ret = prtObj.PrintLine("缴费流水:" + dealNum);
			ret = printInvoiceEnterSD(1);
		} else {
			ret = printInvoiceEnterSD(2);
		}
		var acceptType = getXmlInvoiceData(invoice, "acceptType");
		var acctID = getXmlInvoiceData(invoice, "acctID");
		if (acceptType != "" && acctID != "") 
		{
			ret = prtObj.PrintLine("受理类别:" + acceptType + "        合同号:" + acctID);
			ret = printInvoiceEnterSD(1);
		} 
		else if (acceptType != "") 
		{
			ret = prtObj.PrintLine("受理类别:" + acceptType);
			ret = printInvoiceEnterSD(1);
		} 
		else if (acctID != "") 
		{
			ret = prtObj.PrintLine("合同号:" + acctID);
			ret = printInvoiceEnterSD(1);
		}
		else
		{
			ret = printInvoiceEnterSD(2);
		}
		var servNumber = getXmlInvoiceData(invoice, "servNumber");
		var payDate = getXmlInvoiceData(invoice, "payDate");
		if (servNumber != "" && payDate != "") 
		{
			ret = prtObj.PrintLine("手机号码:" + servNumber + "      日期:" + payDate);
			ret = printInvoiceEnterSD(1);
		} 
		else if (servNumber != "") 
		{
			ret = prtObj.PrintLine("手机号码:" + servNumber);
			ret = printInvoiceEnterSD(1);
		} 
		else if (payDate != "") 
		{
			ret = prtObj.PrintLine("日期:" + payDate);
			ret = printInvoiceEnterSD(1);
		}
		else
		{
			ret = printInvoiceEnterSD(2);
		}
		var subsName = getXmlInvoiceData(invoice, "subsName");
		if (subsName != "") {
			ret = prtObj.PrintLine("客户姓名:" + subsName);
			ret = printInvoiceEnterSD(1);
		} else {
			ret = printInvoiceEnterSD(2);
		}
		ret = prtObj.PrintLine(" ");
		ret = printInvoiceEnterSD(2);
		var consumeList = getXmlInvoiceData(invoice, "consumeList");
		if (consumeList != "") {
			ret = prtObj.PrintLine(consumeList);
			ret = printInvoiceEnterSD(1);
		} else {
			ret = printInvoiceEnterSD(1);
		}
		ret = prtObj.PrintLine(" ");
		ret = printInvoiceEnterSD(1);
		ret = prtObj.PrintLine(" ");
		ret = printInvoiceEnterSD(1);
		ret = prtObj.PrintLine(" ");
		ret = printInvoiceEnterSD(3);
        var capitalFee = getXmlInvoiceData(invoice, "capitalFee");
		var capitalYsFee = getXmlInvoiceData(invoice, "capitalYsFee");
		if (capitalFee != "" && capitalYsFee != "") 
		{
			ret = prtObj.PrintLine("         " + capitalFee + "    "+capitalYsFee);
			ret = printInvoiceEnterSD(1);
		}
		else if(capitalFee != "")
		{
			ret = prtObj.PrintLine("         " + capitalFee);
			ret = printInvoiceEnterSD(1);
		}
		else 
		{
			ret = printInvoiceEnterSD(2);
		}
		var ysFee = getXmlInvoiceData(invoice, "ysFee");
		var feeTime = getXmlInvoiceData(invoice, "feeTime");
		if (ysFee != "" && feeTime != "") 
		{
			ret = prtObj.PrintLine("        " + ysFee + "元                   " + feeTime);
			ret = printInvoiceEnterSD(1);
		} 
		else if (ysFee != "") 
		{
			ret = prtObj.PrintLine("        " + ysFee);
			ret = printInvoiceEnterSD(1);
		} 
		else if (feeTime != "") 
		{
			ret = prtObj.PrintLine("                           " + feeTime);
			ret = printInvoiceEnterSD(1);
		}
		else
		{
			ret = printInvoiceEnterSD(2);
		}
		var pOrgName = getXmlInvoiceData(invoice, "pOrgName");
		if (pOrgName != "") {
			ret = prtObj.PrintLine("        " + pOrgName);
			ret = printInvoiceEnterSD(1);
		} else {
			ret = printInvoiceEnterSD(2);
		}
		cutInvoiceToBlack();
		return ret;
	}
	catch (e) {
	 	alertError("打印发票出错！");
	 	cutInvoiceToBlack();//出现问题切纸
		return -1;
	}
}

function printInvoiceSD1(invoice)
{
	try 
	{
		var bossFormnum = getXmlInvoiceData(invoice, "bossFormnum");
        if (bossFormnum == "" || bossFormnum.length < 1)
        {
            alertError("对不起,发票信息不全,请联系营业员确认缴费是否成功!");
			return;
        }
		var v = checkInvoice();
		if (v != 0) 
		{
		    alertError("对不起,发票打印机缺纸,请联系营业员!");
			return;
		}
		
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
		ret = printInvoiceEnterSD(9);
		
		var dealNum = getXmlInvoiceData(invoice, "dealNum");
		if (dealNum != "") {
			ret = prtObj.PrintLine("缴费流水:" + dealNum);
			ret = printInvoiceEnterSD(1);
		} else {
			ret = printInvoiceEnterSD(2);
		}
		var acceptType = getXmlInvoiceData(invoice, "acceptType");
		if (acceptType != "") 
		{
			ret = prtObj.PrintLine("受理类别:" + acceptType);
			ret = printInvoiceEnterSD(1);
		}		
		else
		{
			ret = printInvoiceEnterSD(2);
		}
		
		var acctID = getXmlInvoiceData(invoice, "acctID");
		if (acctID != "") 
		{
			ret = prtObj.PrintLine("合同号:" + acctID);
			ret = printInvoiceEnterSD(1);
		}
		else
		{
			ret = printInvoiceEnterSD(2);
		}
		var servNumber = getXmlInvoiceData(invoice, "servNumber");
		var payDate = getXmlInvoiceData(invoice, "payDate");
		if (servNumber != "" && payDate != "") 
		{
			ret = prtObj.PrintLine("手机号码:" + servNumber + "      日期:" + payDate);
			ret = printInvoiceEnterSD(1);
		} 
		else if (servNumber != "") 
		{
			ret = prtObj.PrintLine("手机号码:" + servNumber);
			ret = printInvoiceEnterSD(1);
		} 
		else if (payDate != "") 
		{
			ret = prtObj.PrintLine("日期:" + payDate);
			ret = printInvoiceEnterSD(1);
		}
		else
		{
			ret = printInvoiceEnterSD(2);
		}
		ret = printInvoiceEnterSD(1);
		var subsName = getXmlInvoiceData(invoice, "subsName");
		if (subsName != "") {
			ret = prtObj.PrintLine("客户姓名:" + subsName);
			ret = printInvoiceEnterSD(1);
		} else {
			ret = printInvoiceEnterSD(2);
		}       
		
		ret = prtObj.PrintLine(" ");
		ret = printInvoiceEnterSD(2);
		var consumeList = getXmlInvoiceData(invoice, "consumeList");
		if (consumeList != "") {
			ret = prtObj.PrintLine(consumeList);
			ret = printInvoiceEnterSD(1);
		} else {
			ret = printInvoiceEnterSD(1);
		}
		ret = prtObj.PrintLine(" ");
		ret = printInvoiceEnterSD(1);
		ret = prtObj.PrintLine(" ");
		ret = printInvoiceEnterSD(1);
		ret = prtObj.PrintLine(" ");
		ret = printInvoiceEnterSD(4);
    	var capitalFee = getXmlInvoiceData(invoice, "capitalFee");
    	var capitalYsFee = getXmlInvoiceData(invoice, "capitalYsFee");
		if (capitalFee != "" && capitalYsFee != "") 
		{
			ret = prtObj.PrintLine("         " + capitalFee + "    "+capitalYsFee);
			ret = printInvoiceEnterSD(1);
		}
		else if(capitalFee != "")
		{
			ret = prtObj.PrintLine("         " + capitalFee);
			ret = printInvoiceEnterSD(1);
		}
		else 
		{
			ret = printInvoiceEnterSD(2);
		}
		ret = printInvoiceEnterSD(1);
		var ysFee = getXmlInvoiceData(invoice, "ysFee");
		var feeTime = getXmlInvoiceData(invoice, "feeTime");
		if (ysFee != "" && feeTime != "") 
		{
			ret = prtObj.PrintLine("         " + ysFee + "元                   " + feeTime);
			ret = printInvoiceEnterSD(1);
		} 
		else if (ysFee != "") 
		{
			ret = prtObj.PrintLine("         " + ysFee);
			ret = printInvoiceEnterSD(1);
		} 
		else if (feeTime != "") 
		{
			ret = prtObj.PrintLine("                            " + feeTime);
			ret = printInvoiceEnterSD(1);
		}
		else
		{
			ret = printInvoiceEnterSD(2);
		}
		ret = printInvoiceEnterSD(1);
		var pOrgName = getXmlInvoiceData(invoice, "pOrgName");
		if (pOrgName != "") {
			ret = prtObj.PrintLine("         " + pOrgName);
			ret = printInvoiceEnterSD(1);
		} else {
			ret = printInvoiceEnterSD(2);
		}
		cutInvoiceToBlack();
		return ret;
	}
	catch (e) {
	 	alertError("打印发票出错！");
	 	cutInvoiceToBlack();//出现问题切纸
		return -1;
	}
}
function printInvoiceNX(invoice) {
	try 
	{
        var bossFormnum = getXmlInvoiceData(invoice, "bossFormnum");
        if(bossFormnum == "" || bossFormnum.length < 1){
			return "对不起，发票打印失败。";
        }
		var v = checkInvoice();
		if (v != 0) {
			return "对不起，发票打印机缺纸。";
		}
		var ret = 0;
		ret = window.parent.document.getElementById("invprtpluginid").absolutePosition(7);
		if (ret != 0)
		{
			return "对不起，发票打印失败。";
		}
		var payDate = getXmlInvoiceData(invoice, "payDate");		
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + payDate);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();		
		if (ret != 0)
		{
			return "对不起，发票打印失败。";
		}
		var chargeAmount = getXmlInvoiceData(invoice, "chargeAmount");
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + chargeAmount);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		if (ret != 0)
		{
			alert("对不起，发票打印失败。");
			return;
		}
		var totalLower = getXmlInvoiceData(invoice, "totalLower");
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + totalLower);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();	
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();	
		if (ret != 0)
		{
			alert("对不起，发票打印失败。");
			return;
		}
		var totalUpper = getXmlInvoiceData(invoice, "totalUpper");
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + totalUpper);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();		
		if (ret != 0)
		{
			return "对不起，发票打印失败。";
		}	
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + bossFormnum);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter(); 
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		if (ret != 0)
		{
			return "对不起，发票打印失败。";
		}
		var subsName = getXmlInvoiceData(invoice, "subsName");		
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + subsName);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		if (ret != 0)
		{
			alert("对不起，发票打印失败。");
			return;
		}
		var servNumber = getXmlInvoiceData(invoice, "servNumber");
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + servNumber);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		if (ret != 0)
		{
			return "对不起，发票打印失败。";
		}
		var cycle = getXmlInvoiceData(invoice, "cycle");
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + cycle);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();		
		if (ret != 0)
		{
			alert("对不起，发票打印失败。");
			return;
		}
		var consumedAmount = getXmlInvoiceData(invoice, "consumedAmount");
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + consumedAmount);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		if (ret != 0)
		{
			alert("对不起，发票打印失败。");
			return;
		}
		var latestBalance = getXmlInvoiceData(invoice, "latestBalance");
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + latestBalance);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		if (ret != 0)
		{
			alert("对不起，发票打印失败。");
			return;
		}
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		var consumeList = getXmlInvoiceData(invoice, "consumeList");
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("\n");
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		if (ret != 0)
		{
			return "对不起，发票打印失败。";
		}
		var pOrgName = getXmlInvoiceData(invoice, "pOrgName");	
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + pOrgName);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        if (ret != 0)
		{
			return "对不起，发票打印失败。";
		}
		return cutInvoiceToBlackNX();
	}
	catch (e) {
		cutInvoiceToBlackNX();
		return "对不起，发票打印失败。";
	}
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
  	   alertError("错误:打印机设备驱动程序未安装!");
  	   return;
  	}
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: "+pServNumber);
  	
  	//modify by sWX219697 2015-6-25 17:12:30 OR_SD_201506_581去掉客户名称项
  	//ret = window.parent.document.getElementById("prtpluginid").PrintLine("  客户名称: " + custName);
  	//modify by sWX219697 2015-6-25 17:12:30 OR_SD_201506_581去掉客户名称项
  	
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
function doPrintByActivity(p_activityId,p_dangciId,p_servnumber,p_orgName,p_date,p_termId,p_termName,p_prepayFee,p_totalfee,
		p_terminalSeq,p_recoid,p_status,yxfaFee_yuan,ycFee_yuan,p_dangciName,p_groupName,tiptext, brandName)
{
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
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: "+p_servnumber);
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("      品牌: " + brandName);
	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助缴费机交易凭据");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
	    if (ret == 1)
	    {
	        alertError("警告:打印机缺纸或故障!");
	        return;
	    }  	
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端信息  : "+p_termName + ";");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  活动名称  : "+p_groupName + ";");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  档次名称  : "+p_dangciName + ";");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  支付流水  : "+p_terminalSeq + ";");
	  	if (p_recoid != '')
	  	{
	  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  受理流水  : "+p_recoid + ";");
	  	}
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易时间  : "+p_date + ";");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  实缴金额  : "+p_totalfee + "元;");
	  	if (yxfaFee_yuan != '')
	  	{
	  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  受理金额  : "+yxfaFee_yuan + "元;");
	  	}
	  	if (ycFee_yuan != '')
	  	{
	  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费金额  : "+ycFee_yuan + "元;");
	  	}
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态  : "+p_status + ";");
	  	
	  	if (tiptext != '')
	  	{
	  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  特别提示  : "+ tiptext + ";");
	  	}
	    if (ret == 1)
	    {
	        alertError("警告:打印机缺纸或故障!");
	        return;
	    }
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询.");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印地点:"+p_orgName+".");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间:"+p_date+".");
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
function printAuditReceipt(regionName, orgName, operID, printTime, detailInfo) {
  try 
  {
  	var ret;

  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("                自助终端现金缴费结账单");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");  	
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
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  所属地区：" + regionName);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  营业厅名称: " + orgName);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端工号: " + operID);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间: " + printTime);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");  
	if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(detailInfo);
  	if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  结账人签字：            监督人签字：");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  备注：");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("");
  	if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("                   提示与声明");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  1.现金清点和结账时，应保持双人在场。");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  2.注意签字，如有问题请在备注栏说明。");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  3.如有缴费失败或冲正业务，请务必与BOSS核实。");
  	if (ret == 1)
  	{
  	    alertError("警告:打印机缺纸或故障!");
  	    return;
  	}
	 	cutPaper();
	} catch(ex) {
 		alertError("打印现金缴费结账单出错");
 		cutPaper();//出现问题切纸
	}	
	return "success";
}
function doPrintScoreExECou(pServNumber,pOrgName,pPrintDate,pTerminalInfo,pExchNum,pExScore,pECoupon,pUsableScore) {
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
    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  积分兑换电子券");

  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端信息  : "+pTerminalInfo + ";");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  业务流水  : "+pExchNum + ";");
  	if (pExScore != "")
  	{
  		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  消费积分  : "+pExScore + ";");  		  	
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  电子券金额  : "+pECoupon + ";");
  	}
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  可用积分  : "+pUsableScore + ";");
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

// 湖北月结发票打印
function printMonInvoiceHB(printyear,printmonth,printday,username,telnum,acctId,formnum,invoiceFee,operid,commServFee,sellDiscount,fee,feeType,thirdPay)
{
    try{
    var v = checkInvoice();
    if (v != 0) {
        alert("对不起,发票打印机缺纸,请联系营业员!");
        return;
    }
    
    var ret = 0;
    ret = window.parent.document.getElementById("invprtpluginid").absolutePosition(1);
    if (ret != 0)
    {
        alertError("对不起，发票打印失败。");
        return;
    }
    for (var i = 0; i < 7; i++)
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //缴费日期  格斯: 年  月  日
    if(printyear != ""  && printmonth != "" && printday != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("             "+printyear+"   "+printmonth+"   "+printday);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //-------------------------------------------
    //客户名称 
    if(username != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           "+username);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //客户号码
    if(telnum != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           "+telnum);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //合同号
    if(acctId != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           "+acctId);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //款项性质
    if(feeType != "" )
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           "+feeType);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //金额
    if(fee != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           "+fee);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //通信服务费
    if(commServFee != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("     "+commServFee);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //销售折扣
    if(sellDiscount != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("     "+sellDiscount);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    
    // add begin wWX217192 2016-01-27 OR_HUB_201512_256_湖北_关于在BOSS系统中标记天猫话费充值已开票金额的需求
    // 第三方支付
    if(thirdPay != "")
    {
    	ret = window.parent.document.getElementById("invprtpluginid").PrintLine("     "+thirdPay);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    // add end wWX217192 2016-01-27 OR_HUB_201512_256_湖北_关于在BOSS系统中标记天猫话费充值已开票金额的需求
    
    //本次发票金额
    if(invoiceFee != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("     "+invoiceFee);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //空格
    for (var i = 0; i < 13; i++)
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //流水号
    if(formnum != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        "+formnum);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //开票
    if(operid != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        "+operid);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    
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

    if (ret == 1)
    {
        cutInvoicePaper();
        alertError("警告:发票打印机缺纸或故障!");
        return 1;
    }
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
    cutInvoiceToBlack();
    return ret;
    }
    catch (e) 
    {
        alertError("打印月结发票出错，请联系管理员！");
        cutInvoicePaper();//出现问题切纸
        return -1;
    }
}

// 宁夏月结发票
function printMonInvoiceNX(printTime,username,feeType,regionName,fee,telnum,acctId,
	formnum,invoiceFee,operid,commServFee,commServFeeName,sellDiscount)
{
    var v = checkInvoice();
    if (v != 0) {
        alert("对不起,发票打印机缺纸,请联系营业员!");
        return;
    }
	for (var i = 0; i < 8; i++)
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //缴费日期  格斯: 年  月  日
    if(printyear != ""  && printmonth != "" && printday != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("                  "+printTime);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //-------------------------------------------
    //客户名称 
    if(username != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("                 "+username);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //客户号码
    if(telnum != "" && regionName != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("                 "+telnum+"("+regionName+")");
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //合同号
    if(acctId != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("               "+acctId);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //款项性质
    if(printyear != ""  && printmonth != "" )
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("                "+feeType);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //金额
    if(fee != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("               "+fee);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //通信服务费
    if(commServFee != "" && commServFeeName != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("     "+commServFeeName+commServFee);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //销售折扣
    if(sellDiscount != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("     "+sellDiscount);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //本次发票金额
    if(invoiceFee != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("     "+invoiceFee);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //空格
    for (var i = 0; i < 8; i++)
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //流水号
    if(formnum != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        "+formnum);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    //开票
    if(operid != "")
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        "+operid);
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    else
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    
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

    if (ret == 1)
    {
        cutInvoicePaper();
        alertError("警告:发票打印机缺纸或故障!");
        return 1;
    }
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

// add begin wWX217192 2014-07-21 OR_huawei_201406_338 山东_营业厅全业务流程优化-业务分流(自助终端)_非实名制补卡
// 山东非实名制认证补卡的凭条打印	
function doPrintNoRealName_SD(pServNumber,pOrgName,pPrintDate,printcontext) {
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
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助终端平台非实名认证补卡凭据");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }  	
  	if (printcontext != "")
	{
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  " + printcontext);
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
 		alertError("打印非实名制认证补卡凭条出错,请联系营业厅服务员验证非实名制认证补卡是否成功！");
 		cutPaper();//出现问题切纸
	}	
}
// add end wWX217192 2014-07-21 OR_huawei_201406_338 山东_营业厅全业务流程优化-业务分流(自助终端)_非实名制补卡

// add begin wWX217192 2014-07-29 OR_huawei_201406_849 山东_营业厅全业务流程优化-业务分流(自助终端)_实名制认证
// 山东实名制认证的凭条打印	
function doPrintRealName_SD(pServNumber,pOrgName,pPrintDate,printcontext) {
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
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助终端平台实名制登记认证凭据");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }  	
  	if (printcontext != "")
	{
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  " + printcontext);
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
 		alertError("打印实名制认证凭条出错,请联系营业厅服务员验证实名制认证是否成功！");
 		cutPaper();//出现问题切纸
	}	
}
// add end wWX217192 2014-07-29 OR_huawei_201406_849 山东_营业厅全业务流程优化-业务分流(自助终端)_实名制认证

// add begin jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
// 宁夏打印收据
function printReceipt_new(pDealTime, pName, pServNumber, tMoney, upperTMoney, pDealNum, pOperID, pOrgName)
{
    try
    {
	    var prtObj;
	    var v = checkInvoice();
	    if (v != 0) {
	        alertError("对不起,发票打印机缺纸,请联系营业员!");
	        return;
	    }
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
        
        // 定位打印位置
	    ret = window.parent.document.getElementById("invprtpluginid").absolutePosition(7);
        if (ret != 0)
        {
            alertError("对不起，发票打印失败。");
            return;
        }
        ret = printEnterCommon(2);
        
        // 开票日期
        if(pDealTime != "")
        {
            ret = prtObj.PrintLine("            "+pDealTime);
        }
        ret = printEnterCommon(1);
        
        // 行业分类
        ret = prtObj.PrintLine("            "+"电信业");
        ret = printEnterCommon(2);
        
        // 客户名称
        if(pName != "")
        {
            ret = prtObj.PrintLine("   客户名称:"+pName);
	        ret = printEnterCommon(2);
        }
        
        // 手机号码
        if(pServNumber != "")
        {
            ret = prtObj.PrintLine("   手机号码:"+pServNumber);
	        ret = printEnterCommon(2);
        }
        
        // 通信服务费
        if(tMoney != "")
        {
            ret = prtObj.PrintLine("   通信服务费:"+tMoney); 
	        ret = printEnterCommon(2);
        }
        
        // 合计
        if(tMoney != "")
        {
            ret = prtObj.PrintLine("   合计:"+tMoney); 
	        ret = printEnterCommon(2);
        }
        
        // 本次收据金额
        if(tMoney != "")
        {
            ret = prtObj.PrintLine("   本次收据金额:"+tMoney); 
	        ret = printEnterCommon(2);
        }
        
        // 大写金额合计
        if(upperTMoney != "")
        {
            ret = prtObj.PrintLine("   大写金额合计:"+upperTMoney);
        }
        if(ret == 1)
        {
            cutInvoiceToBlackNX();
            alertError("警告:打印机缺纸或故障!");
            return;
        }
        ret = printEnterCommon(11);
        
        // 流水号
        if(pDealNum != "")
        {
            ret = prtObj.PrintLine("          "+pDealNum);
        }
        ret = printEnterCommon(1);
        
        // 开票人
        if(pOperID != "")
        {
            ret = prtObj.PrintLine("          "+pOperID);
        }
        ret = printEnterCommon(1);
        
        // 营业厅
        if(pOrgName != "")
        {
            ret = prtObj.PrintLine("          "+pOrgName);
        }
        if (ret == 1)
        {
            alertError("警告:打印机缺纸或故障!");
            return;
        }
        cutInvoiceToBlackNX();
    }
    catch (e) 
    {
        cutInvoiceToBlackNX();//出现问题切纸
        alertError("打印缴费收据出错，请联系营业厅服务员查看缴费是否成功！");
        return;
    }
}

// 发票打印空行
function printEnterCommon(num)
{
    var ret;
    for (var i = 0; i < num; i++) 
    {
        ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
    }
    return ret;
}
// add end jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求

// 预存有礼受理，打印小票
function doPrintByActivitySD(p_telNum,p_orgName,p_date,p_termId,p_termName,p_tMoney,p_chargeId,
        p_terminalSeq,p_recoid,p_status,p_recFee,p_preFee,p_actLevelName,p_groupName,p_custName,p_awardDesc,printcontext)
{
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
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: "+p_telNum);
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  客户名称: " + p_custName);
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助缴费机交易凭据");
        ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");         
        if (ret == 1)
        {
            alertError("警告:打印机缺纸或故障!");
            return;
        }   
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端信息  : "+p_termName + ";");
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  活动名称  : "+p_groupName + ";");
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  档次名称  : "+p_actLevelName + ";");
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费日志流水  : "+p_chargeId + ";");
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端流水  : "+p_terminalSeq + ";");
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  受理流水  : "+p_recoid + ";");
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易时间  : "+p_date + ";");
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  实缴金额  : "+p_tMoney + "元;");
        if (p_recFee != '')
        {
            ret = window.parent.document.getElementById("prtpluginid").PrintLine("  活动费用  : "+p_recFee + "元;");
        }
        if (p_preFee != '')
        {
            ret = window.parent.document.getElementById("prtpluginid").PrintLine("  预存金额  : "+p_preFee + "元;");
        }
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态  : "+p_status + ";");
        
        if (p_awardDesc != '')
        {
            ret = window.parent.document.getElementById("prtpluginid").PrintLine("  奖品描述  : "+p_awardDesc + "。");
        }
        
        if (printcontext != "")
	    {
	        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
	        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
	        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  银联刷卡交易凭据");
	        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  支付流水: " + p_terminalSeq);
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
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印地点:"+p_orgName+".");
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间:"+p_date+".");
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

// add begin jWX216858 2015-4-16 OR_SD_201501_1063 自助终端支撑连缴功能优化
function doPrintMorePhoneSD(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, tMoney,totalFee,
                    pTerminalSeq, pCustName, pStatus, printcontext) {
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
    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助缴费机交易凭据");
    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端名称  : "+pTerminalInfo + ";");
    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易时间  : "+pDealTime + ";");
    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易总金额  : "+totalFee + ".00元;");
    
    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费用户信息");
    ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");         
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }   
    // 手机号码
    var telnums = pServNumber.substring(0, pServNumber.length - 1).split(",");
    
    // 客户名称
    var custNames = pCustName.split(",");
    
    // 终端交易流水
    var termSeqs = pTerminalSeq.split(",");
    
    // 交易流水
    var pDealNums = pDealNum.split(",");
    
    // 单笔业务缴费金额
    var tMoneys = tMoney.split(",");
    
    // 缴费状态，0：成功，1：失败
    var pStatuss = pStatus.split(",");
    for (var i = 0; i < telnums.length; i++)
    {
	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: " + telnums[i]);
	    
	    //modify begin sWX219697 2015-6-25 17:12:30 OR_SD_201506_581去掉客户名称项
	    //ret = window.parent.document.getElementById("prtpluginid").PrintLine("  客户名称: " + custNames[i]);
	    //modify by sWX219697 2015-6-25 17:12:30 OR_SD_201506_581去掉客户名称项
	    
	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端流水: " + termSeqs[i]);
	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  受理编号: " + pDealNums[i]);
	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  缴费金额: " + tMoneys[i] + ".00元");
	    if ("0" == pStatuss[i] || 0 == pStatuss[i])
	    {
		    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态: 缴费成功;");
	    }
	    else
	    {
	       ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态: 缴费失败;");
	    }
    }
    
    if (printcontext != "")
    {
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  银联刷卡交易凭据");
        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  支付流水: " + printcontext.substring(43, 55));
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
// add end jWX216858 2015-4-16 OR_SD_201501_1063 自助终端支撑连缴功能优化

// add begin wWX217192 2015-05-19 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
// 有价卡购买失败时打印小票信息
function doPrintValueCardError(pServNumber, pOrgName, pPrintDate, pTermId, pDealTime, pAmount,
					pTotalFee, pDealStatus, pDealNum)
{
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
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助终端平台电子有价卡购买凭据");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: "+pServNumber);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }
    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: " + pServNumber);
    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端机编号: " + pTermId);
    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易流水号: " + pDealNum);
    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  应缴金额: " + pAmount);
    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  实缴金额: " + pTotalFee);
    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态: " + pDealStatus);
    
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
 		alertError("打印非实名制认证补卡凭条出错,请联系营业厅服务员验证非实名制认证补卡是否成功！");
 		cutPaper();//出现问题切纸
	}	
    
}
// add end wWX217192 2015-05-19 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
