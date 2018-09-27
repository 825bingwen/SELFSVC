
/**
 * 湖北自助缴费凭条打印
 * pServNumber --号码
 * pOrgName    --打印地点
 * pPrintDate  --打印日期
 * pTerminalInfo --终端信息
 * pDealNum     --移动流水号
 * pDealTime   --交易时间
 * pAmount     --交易金额
 * pDealStatus --交易状态
 * pTerminalSeq -- 投币流水号
 * mpay_seq    --手机支付流水号
 * isFee       --为1时代表手机支付
 */
function doPrintPayProofhub(pServNumber,pOrgName,pPrintDate,pTerminalInfo,pDealNum,pDealTime,pAmount,pDealStatus,pTerminalSeq,pbrand,mpay_seq,isFee) {
  try {

  	//打印移动图标
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
  	
  	//打印头部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: "+pServNumber);
  	// 打印品牌
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  用户品牌: "+pbrand);
  	// isFee为1时打印手机主账户充值小票
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
    
    //打印具体缴费信息
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
  	
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态  : "+pDealStatus + ";");
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }     
    
    
    //打印尾部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询.");
  	//delete begin g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
  	//delete end g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
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
 * 自助缴费凭条打印
 * pServNumber --号码
 * pOrgName    --打印地点
 * pPrintDate  --打印日期
 * pTerminalInfo --终端信息
 * pDealNum     --移动流水号
 * pDealTime   --交易时间
 * pAmount     --交易金额
 * pDealStatus --交易状态
 * pTerminalSeq -- 投币流水号
 * mpay_seq    --手机支付流水号
 * isFee       --为1时代表手机支付
 */
function doPrintPayProof(pServNumber,pOrgName,pPrintDate,pTerminalInfo,pDealNum,pDealTime,pAmount,pDealStatus,pTerminalSeq,mpay_seq,isFee) {
  try {

  	//打印移动图标
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
  	
  	//打印头部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: "+pServNumber);
  	// isFee为1时打印手机主账户充值小票
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
    
    //打印具体缴费信息
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
  	
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  交易状态  : "+pDealStatus + ";");
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }     
    
    
    //打印尾部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询.");
  	//delete begin g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
  	//delete end g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
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
 * 自助缴费凭条打印_宽带缴费
 * pServNumber --号码
 * pOrgName    --打印地点
 * pPrintDate  --打印日期
 * pTerminalInfo --终端信息
 * pDealNum     --移动流水号
 * pDealTime   --交易时间
 * pAmount     --交易金额
 * pDealStatus --交易状态
 * pTerminalSeq -- 投币流水号
 * mpay_seq    --手机支付流水号
 * isFee       --为1时代表手机支付
 */
function doPrintPayProof_wband(pServNumber,pOrgName,pPrintDate,pTerminalInfo,pDealNum,pDealTime,pAmount,pDealStatus,pTerminalSeq,mpay_seq,isFee) {
  try {

  	//打印移动图标
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
  	
  	//打印头部信息
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
    
    //打印具体缴费信息
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
    
    
    //打印尾部信息
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
			
		//正常打印结束,切纸
	 	cutPaper();
	} catch(ex) {
 		alertError("打印缴费凭条出错,请联系营业厅服务员查看缴费是否成功！");
 		cutPaper();//出现问题切纸
	}	
}

/**
 * 自助刷卡缴费银联小票
 * printcontext --银联返回的打印信息
 * 如：
 * 302370150210713~123456789012345~消费交易~6222021602004724078~000001~100001~000000000003~20101019 155445~000003~000000001234
 * 表示：
 * 商户编号: 302370150210713，终端编号: 123456789012345，交易类型: 消费交易，银行卡号: 6222021602004724078，批 次 号: 000001，授权码: 100001，交易参考号: 000000000003，日期时间: 20101019 155445，凭 证 号 : 000003，金额(RMB): 000000001234
 * pTerminalSeq --支付流水
 * pOrgName     --打印地点
 * pPrintDate   --打印日期
 */
function doPrintUnionBill_hb(printcontext,pTerminalSeq,pOrgName,pPrintDate) {
  try {
  
  	var printcontexts = printcontext.split('~');

  	//打印移动图标
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
  	
  	//打印头部信息
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
    
    //打印具体银联返回信息
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
 * 自助刷卡缴费银联小票
 * printcontext --银联返回的打印信息
 * 如：
 * 302370150210713~123456789012345~消费交易~6222021602004724078~000001~100001~000000000003~20101019 155445~000003~000000001234
 * 表示：
 * 商户编号: 302370150210713，终端编号: 123456789012345，交易类型: 消费交易，银行卡号: 6222021602004724078，批 次 号: 000001，授权码: 100001，交易参考号: 000000000003，日期时间: 20101019 155445，凭 证 号 : 000003，金额(RMB): 000000001234
 * pTerminalSeq --支付流水
 * pOrgName     --打印地点
 * pPrintDate   --打印日期
 */
function doPrintUnionBill_hb2(printcontext,pTerminalSeq,pOrgName,pPrintDate) {
  try {
  
  	var printcontexts = printcontext.split('~');

  	//打印移动图标
  	var ret;
  	try{
  		ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
  	}
  	catch(e)
  	{
		//alertError("警告:打印机控件未安装!");
		return;
  	}
  	if (ret == 1)
  	{
  	   //alertError("警告:打印机缺纸或故障!");
  	   return;
  	}
  	else if (ret == 41)
  	{
  	   //alertError("错误:打印机设备低层驱动程序未安装!");
  	   return;
  	}
  	
  	//打印头部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  银联刷卡交易凭据");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  支付流水: "+pTerminalSeq);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        //alertError("警告:打印机缺纸或故障!");
        return;
    }
    
    //打印具体银联返回信息
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
        //alertError("警告:打印机缺纸或故障!");
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
  	    //alertError("警告:打印机缺纸或故障!");
  	    return;
  	}    
			
		//正常打印结束,切纸
	 	cutPaper();
	 	
	} catch(ex) {
	 		//alertError("打印缴费凭条出错,请联系营业厅服务员查看缴费是否成功！");
	 		cutPaper();//出现问题切纸
	}	
	
}

/**
 * 宁夏打印缴费凭条
 */
function doPrintPayProof_NX(pServNumber,pOperID,pDealNum,pDealTime,pAmount, pSubsName, pLatestBalance) {
  try {	
  	var ret = window.parent.document.getElementById("prtpluginid").PrintLine("      中国移动通信集团宁夏有限公司预收话费收据");
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
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  最新余额: " + pLatestBalance);
  	
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
}
      
//设置打印状态    
function alertError(info) {
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
function cutInvoicePaper() {
  	var ret = window.parent.document.getElementById("invprtpluginid").CutPaper();
  	if (ret == 1)
  	{
  		  alertError("警告:发票打印机缺纸或故障!");
  		  return 1;
  	}	
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

function printInvoiceWS(invoice) {
	try {

        //Added By Yangtao 检查boss交易流水
        var bossFormnum = getXmlInvoiceData(invoice, "bossFormnum");
        if(bossFormnum == "" || bossFormnum.length < 1){
            alertError("对不起,发票信息不全,请联系营业员确认缴费是否成功!");
			return;
        }
        //End Added By Yangtao 检查boss交易流水

        //检查打印机是否有纸
		var v = checkInvoice();
		if (v != 0) {
		    alertError("对不起,发票打印机缺纸,请联系营业员!");
			return;
		}

        //开始打印
		var ret = 0;
		ret = window.parent.document.getElementById("invprtpluginid").absolutePosition(7);

        //缴费时间
		var dealTime = getXmlInvoiceData(invoice, "dealTime");
		if (dealTime != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + dealTime.substr(0, 4) + "     " + dealTime.substr(4, 2) + "    " + dealTime.substr(6, 2));
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
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("            " + dealNum);
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
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + subsName);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
        //手机号码
		var servNumber = getXmlInvoiceData(invoice, "servNumber");
		if (servNumber != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + servNumber);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
        //计费期间
		var feeTime = getXmlInvoiceData(invoice, "feeTime");
		if (feeTime != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + feeTime);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
        //当前月话费
		//var curMonthFee = getXmlInvoiceData(invoice, "curMonthFee");
        var curMonthFee = getXmlInvoiceData(invoice, "sumFee");
		if (curMonthFee != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + curMonthFee);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
        //最新余额
		var surplusFee = getXmlInvoiceData(invoice, "surplusFee");
		if (surplusFee != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("           " + surplusFee);
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

        //M值
		var MScore = getXmlInvoiceData(invoice, "MScore");
	    //营业厅
		var pOrgName = getXmlInvoiceData(invoice, "pOrgName");
		if (MScore != "" || pOrgName != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("          " + MScore + "                  " + pOrgName);
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
		// alert();
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
		//alert(username);
		if(username != "")
		{
			ret = prtObj.PrintLine("            "+username);
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var telnumber = getXmlInvoiceData(invoice, "telnumber");
		//alert(telnumber);
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
		//alert(formnum);
		if(formnum != "")
		{
			ret = prtObj.PrintLine("            "+formnum);
		}
		ret = prtObj.PrintEnter();
		ret = prtObj.PrintEnter();
		var paynumno = getXmlInvoiceData(invoice, "paynumno");
		// alert(paynumno);
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
		//============备注信息
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
		//alert(totalmoney);
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
		//定位黑标
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
		//调整切纸位置到黑标处
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
		//反向走纸
		ret = prtObj.BackPaper(5);
		
		//正常打印结束,切纸
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
//山东自助终端打印发票
function printInvoiceSD(invoice) {

	try 
	{
        //Added By Yangtao 检查boss交易流水
        var bossFormnum = getXmlInvoiceData(invoice, "bossFormnum");
        if(bossFormnum == "" || bossFormnum.length < 1){
            alertError("对不起,发票信息不全,请联系营业员确认缴费是否成功!");
			return;
        }
        //End Added By Yangtao 检查boss交易流水

        //检查打印机是否有纸
		var v = checkInvoice();
		if (v != 0) {
		    alertError("对不起,发票打印机缺纸,请联系营业员!");
			return;
		}

        //开始打印
		var ret = 0;
		//ret = window.parent.document.getElementById("invprtpluginid").ToBlackMark();
		ret = window.parent.document.getElementById("invprtpluginid").absolutePosition(7);
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine(" ");

		//缴费流水
		var dealNum = getXmlInvoiceData(invoice, "dealNum");
		if (dealNum != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + dealNum);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}

		//受理类别、合同号
		var acceptType = getXmlInvoiceData(invoice, "acceptType");

		var acctID = getXmlInvoiceData(invoice, "acctID");
		if (acceptType != "" && acctID != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + acceptType + "               " + acctID);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} 
		else if (acceptType != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + acceptType);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} 
		else if (acctID != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("                        " + acctID);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		else
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}

		//手机号码、日期
		var servNumber = getXmlInvoiceData(invoice, "servNumber");
		var payDate = getXmlInvoiceData(invoice, "payDate");
		if (servNumber != "" && payDate != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + servNumber + "          " + payDate);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} 
		else if (servNumber != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + servNumber);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} 
		else if (payDate != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("                        " + payDate);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		else
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		
		//客户名称
		var subsName = getXmlInvoiceData(invoice, "subsName");
		if (subsName != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + subsName);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}       
		
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine(" ");
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		
		//消费明细
		var consumeList = getXmlInvoiceData(invoice, "consumeList");
		if (consumeList != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine(consumeList);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
	
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        全 球 通：积极 掌控 品位");
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        神 州 行：自由 便捷 实惠");
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        动感地带：时尚 探索 好玩");
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		
		//大写金额
        var capitalFee = getXmlInvoiceData(invoice, "capitalFee");
		if (capitalFee != "") {
		    //modify begin l00190940 2011-11-23 大写金额显示单位
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + capitalFee);
			//modify end l00190940 2011-11-23 大写金额显示单位
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		
		//小写金额、话费账期
		var ysFee = getXmlInvoiceData(invoice, "ysFee");
		var feeTime = getXmlInvoiceData(invoice, "feeTime");
		if (ysFee != "" && feeTime != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + ysFee + "元                   " + feeTime);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} 
		else if (ysFee != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + ysFee);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} 
		else if (feeTime != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("                           " + feeTime);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		else
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}

		//收费单位
		var pOrgName = getXmlInvoiceData(invoice, "pOrgName");
		if (pOrgName != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + pOrgName);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
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

/*
 * 发票打印格式优化
 * 
 * @remark create g00140516 2012/08/14 R003C12L08n01 发票打印格式优化
 */
function printInvoiceSD1(invoice)
{
	try 
	{
		// 检查boss交易流水
		var bossFormnum = getXmlInvoiceData(invoice, "bossFormnum");
        if (bossFormnum == "" || bossFormnum.length < 1)
        {
            alertError("对不起,发票信息不全,请联系营业员确认缴费是否成功!");
			return;
        }
        
        //检查打印机是否有纸
		var v = checkInvoice();
		if (v != 0) 
		{
		    alertError("对不起,发票打印机缺纸,请联系营业员!");
			return;
		}
		
		var ret = 0;
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		
		//缴费流水
		var dealNum = getXmlInvoiceData(invoice, "dealNum");
		if (dealNum != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("         " + dealNum);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
	
		//受理类别、合同号
		var acceptType = getXmlInvoiceData(invoice, "acceptType");
		if (acceptType != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("         " + acceptType);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}		
		else
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		
		var acctID = getXmlInvoiceData(invoice, "acctID");
		if (acctID != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("                           " + acctID);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		else
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		
		//手机号码、日期
		var servNumber = getXmlInvoiceData(invoice, "servNumber");
		var payDate = getXmlInvoiceData(invoice, "payDate");
		if (servNumber != "" && payDate != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("         " + servNumber + "          " + payDate);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} 
		else if (servNumber != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("         " + servNumber);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} 
		else if (payDate != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("                         " + payDate);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		else
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		
		//客户名称
		var subsName = getXmlInvoiceData(invoice, "subsName");
		if (subsName != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("         " + subsName);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}       
		
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine(" ");
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		
		//消费明细
		var consumeList = getXmlInvoiceData(invoice, "consumeList");
		if (consumeList != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine(consumeList);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        全 球 通：积极 掌控 品味");
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        神 州 行：自由 便捷 实惠");
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        动感地带：时尚 探索 好玩");
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		
		//大写金额
    	var capitalFee = getXmlInvoiceData(invoice, "capitalFee");
		if (capitalFee != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("         " + capitalFee);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		
		//小写金额、话费账期
		var ysFee = getXmlInvoiceData(invoice, "ysFee");
		var feeTime = getXmlInvoiceData(invoice, "feeTime");
		if (ysFee != "" && feeTime != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("         " + ysFee + "元                   " + feeTime);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} 
		else if (ysFee != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("         " + ysFee);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} 
		else if (feeTime != "") 
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("                            " + feeTime);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		else
		{
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		}
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		
		//收费单位
		var pOrgName = getXmlInvoiceData(invoice, "pOrgName");
		if (pOrgName != "") {
			ret = window.parent.document.getElementById("invprtpluginid").PrintLine("         " + pOrgName);
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		} else {
			ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
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

//宁夏自助终端打印发票
function printInvoiceNX(invoice) {
	// modify begin g00140516 2013/02/02 R003C13L01n01 发票打印失败时，提供小票打印功能
	try 
	{
        //Added By Yangtao 检查boss交易流水
        var bossFormnum = getXmlInvoiceData(invoice, "bossFormnum");
        if(bossFormnum == "" || bossFormnum.length < 1){
//            alertError("对不起,发票信息不全,请联系营业员确认缴费是否成功!");
			return "对不起，发票打印失败。";
        }
        //End Added By Yangtao 检查boss交易流水

        //检查打印机是否有纸
		var v = checkInvoice();
		if (v != 0) {
//		    alertError("对不起,发票打印机缺纸,请联系营业员!");
			return "对不起，发票打印机缺纸。";
		}

        //开始打印
		var ret = 0;
		/**
		ret = window.parent.document.getElementById("invprtpluginid").ToBlackMark();
		if (ret != 0)
		{
			return "对不起，发票打印失败。";
		}
		**/
		
		ret = window.parent.document.getElementById("invprtpluginid").absolutePosition(7);
		if (ret != 0)
		{
			return "对不起，发票打印失败。";
		}
		
		//缴费时间
		var payDate = getXmlInvoiceData(invoice, "payDate");		
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + payDate);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();		
		if (ret != 0)
		{
			return "对不起，发票打印失败。";
		}
		
		//缴费金额
		var chargeAmount = getXmlInvoiceData(invoice, "chargeAmount");
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + chargeAmount);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		if (ret != 0)
		{
			alert("对不起，发票打印失败。");
			return;
		}

		//合计(小写)
		var totalLower = getXmlInvoiceData(invoice, "totalLower");
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + totalLower);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();	
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();	
		if (ret != 0)
		{
			alert("对不起，发票打印失败。");
			return;
		}
		
		//合计(大写)
		var totalUpper = getXmlInvoiceData(invoice, "totalUpper");
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + totalUpper);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();		
		if (ret != 0)
		{
			return "对不起，发票打印失败。";
		}
		
		//流水号		
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + bossFormnum);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter(); 
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		if (ret != 0)
		{
			return "对不起，发票打印失败。";
		}
				
		//客户名称
		var subsName = getXmlInvoiceData(invoice, "subsName");		
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + subsName);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		if (ret != 0)
		{
			alert("对不起，发票打印失败。");
			return;
		}
		
		//用户号码
		var servNumber = getXmlInvoiceData(invoice, "servNumber");
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + servNumber);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		if (ret != 0)
		{
			return "对不起，发票打印失败。";
		}
		
		//计费周期
		var cycle = getXmlInvoiceData(invoice, "cycle");
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + cycle);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();		
		if (ret != 0)
		{
			alert("对不起，发票打印失败。");
			return;
		}	
		
		//消费金额
		var consumedAmount = getXmlInvoiceData(invoice, "consumedAmount");
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + consumedAmount);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
		if (ret != 0)
		{
			alert("对不起，发票打印失败。");
			return;
		}
		
		//最新余额
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
				
		//消费明细
		var consumeList = getXmlInvoiceData(invoice, "consumeList");
		//ret = window.parent.document.getElementById("invprtpluginid").PrintLine(consumeList);
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
		
		//终端编号
		var pOrgName = getXmlInvoiceData(invoice, "pOrgName");	
		ret = window.parent.document.getElementById("invprtpluginid").PrintLine("        " + pOrgName);
		ret = window.parent.document.getElementById("invprtpluginid").PrintEnter();
        if (ret != 0)
		{
			return "对不起，发票打印失败。";
		}
		
		//正常打印结束,切纸
//		cutInvoiceToBlack();
		return cutInvoiceToBlackNX();
	}
	catch (e) {
//	 	alertError("打印缴费发票出错！");
//	 	cutInvoiceToBlack();//出现问题切纸
		cutInvoiceToBlackNX();
		return "对不起，发票打印失败。";
	}
	// modify end g00140516 2013/02/02 R003C13L01n01 发票打印失败时，提供小票打印功能
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

// add begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
/**
 * 自助缴费凭条打印
 * pServNumber --号码
 * pOrgName    --打印地点
 * pPrintDate  --打印日期
 * pTerminalInfo --终端名称
 * pDealNum     --受理编号
 * pDealTime   --交易时间
 * pAmount     --交易金额
 * pDealStatus --交易状态
 * pTerminalSeq -- 终端流水
 * mpay_seq    --手机支付流水号
 * isFee       --为1时代表手机支付
 */
//modify begin g00140516 2012/04/24 R003C12L03n01 凭条打印时打印客户名称
function doPrintPayProof_SD(pServNumber,pOrgName,pPrintDate,pTerminalInfo,pDealNum,pDealTime,pAmount,
		pDealStatus,pTerminalSeq,mpay_seq,isFee, custName, printcontext) {
  try {

  	//打印移动图标
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
  	
  	//打印头部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: "+pServNumber);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  客户名称: " + custName);
  	// isFee为1时打印手机主账户充值小票
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
    
    //打印具体缴费信息
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
  	// add begion cKF76106 2013/03/20 R003C13L02n01 OR_SD_201303_785
  	// 打印银联小票
  	if (printcontext != "")
	{
		//打印头部信息
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
		
		// 卡号超过10位时，除前6位、后四位外，其余用*代替
		var hiddenCardNumber = cardNumber;
		if (cardNumber.length > 10)
		{
			// 前6位
			hiddenCardNumber = cardNumber.substring(0, 6) + "******" + cardNumber.substring(cardNumber.length - 4, cardNumber.length);
		}
				
		// 交易金额
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
	// add end cKF76106 2013/03/20 R003C13L02n01 OR_SD_201303_785
	
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }     
    
    
    //打印尾部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询.");
  	//delete begin g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
  	//delete end g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
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
//modify end g00140516 2012/04/24 R003C12L03n01 凭条打印时打印客户名称

// add end cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371

// modify begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
/**
 * 活动受理凭条打印
 * p_activityId 活动编码
 * p_dangciId 档次编码
 * p_servnumber 手机号码
 * p_orgName 打印地点
 * p_date 打印日期
 * p_termId 终端编码
 * p_termName 终端名称
 * p_prepayFee 受理金额
 * p_totalfee 投向金额
 * p_terminalSeq 终端流水
 * p_recoid 交易流水
 * p_status 状态
 * yxfaFee_yuan 营销方案费用
 * ycFee_yuan 用户预存
 * p_dangciName 档次名称
 * p_groupName 活动名称
 * tiptext 提示信息
 * brandName 品牌名称
 */
function doPrintByActivity(p_activityId,p_dangciId,p_servnumber,p_orgName,p_date,p_termId,p_termName,p_prepayFee,p_totalfee,
		p_terminalSeq,p_recoid,p_status,yxfaFee_yuan,ycFee_yuan,p_dangciName,p_groupName,tiptext, brandName)
{
  try {

	  	//打印移动图标
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
	  	
	  	//打印头部信息
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: "+p_servnumber);
	  	
	  	//add begin g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("      品牌: " + brandName);
	    //add begin g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
	    
	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助缴费机交易凭据");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
	    if (ret == 1)
	    {
	        alertError("警告:打印机缺纸或故障!");
	        return;
	    }  	
	    
	    //打印具体缴费信息
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  终端信息  : "+p_termName + ";");
	  	//ret = window.parent.document.getElementById("prtpluginid").PrintLine("  活动编码  : "+p_activityId + ";");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  活动名称  : "+p_groupName + ";");
	  	//ret = window.parent.document.getElementById("prtpluginid").PrintLine("  档次编码  : "+p_dangciId + ";");
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
	    
	    //打印尾部信息
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
		
		//正常打印结束,切纸
	 	cutPaper();
	 	
	} catch(ex) {
 		alertError("打印缴费凭条出错,请联系营业厅服务员查看缴费是否成功！");
 		cutPaper();//出现问题切纸
	}	
}
// modify end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089

/**
 * 打印宁夏现金缴费对账单
 * 
 * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
 */
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
			
		//正常打印结束,切纸
	 	cutPaper();
	} catch(ex) {
 		alertError("打印现金缴费结账单出错");
 		cutPaper();//出现问题切纸
	}	
	return "success";
}

/**
 * 积分兑换电子券凭条打印
 * @remark create m00227318 2012/09/14 eCommerce V200R003C12L09  OR_huawei_201209_33
 * @param: pServNumber --号码
 * @param: pOrgName    --打印地点
 * @param: pPrintDate  --打印日期
 * @param: pTerminalInfo --终端信息
 * @param: pExchNum     --移动积分兑换流水号
 * @param: pExScore     --兑换积分
 * @param: pECoupon --电子券金额
 * @param: pUsableScore    --剩余可用积分
 */
function doPrintScoreExECou(pServNumber,pOrgName,pPrintDate,pTerminalInfo,pExchNum,pExScore,pECoupon,pUsableScore) {
  try {

  	//打印移动图标
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
  	
  	//打印头部信息
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
    
    //打印具体缴费信息
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
    
    
    //打印尾部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询.");
  	//delete begin g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
  	//delete end g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
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
 * 山东银联卡交费银联小票打印
 * 
 * @remark create g00140516 2012/12/10 eCommerce V200R003C12L11 OR_SD_201211_692

function doPrintUnionBill_sd(printcontext, pTerminalSeq, pOrgName, pPrintDate)
{
	try 
  	{
  		//打印移动图标
  		var ret;
  		try
  		{
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
  	
  		//打印头部信息
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
		
		// 卡号超过10位时，除前6位、后四位外，其余用*代替
		var hiddenCardNumber = cardNumber;
		if (cardNumber.length > 10)
		{
			// 前6位
			hiddenCardNumber = cardNumber.substring(0, 6) + "******" + cardNumber.substring(cardNumber.length - 4, cardNumber.length);
		}
		
		// modify begion cKF76106 2013/03/20 R003C13L02n01 OR_SD_201303_785
		
		// 交易金额
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
    	// modify end cKF76106 2013/03/20 R003C13L02n01 OR_SD_201303_785
    	
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
	} 
	catch(ex) 
	{
	 	alertError("打印缴费凭条出错,请联系营业厅服务员查看缴费是否成功！");
	 	cutPaper();//出现问题切纸
	}	
}
*/

/*
 *　去掉左右两边的空格
 */
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

/**
 * 宁夏专用
 * @remark create g00140516 2013/02/02 R003C13L01n01 发票打印失败时，提供小票打印功能
 */
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