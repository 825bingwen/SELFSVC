//读卡器返回信息分隔符，方便统一处理
var splitChar = "|";

// 控件函数结果码索引
var resultCodeIndex = 1;

// 函数错误码位置索引
var errCodeIndex = 1;

// 控件函数结果信息索引
var	resultInfoIndex = 0;

// 控件函数结果码，正确码
var successCode = "0000";
var writeCardObject = window.parent.document.getElementById('writecardpluginid');

//ajax调用返回结果
var res;

/**
 * 空白卡写卡
 * 入参： cardInfoStr 空白卡信息字符串（iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2）
 * 	     SIMSerialCommon 空白卡号
 *       basePath 项目路径，js里不能解析s标签
 *	     telnum 手机号  获取加密数据需要
 * 返回结果： 0: 成功 1 : 失败  1~失败描述
 */
function writeCard(cardInfoStr,SIMSerialCommon,basePath,telnum)
{	
	//return "0";//测试用
	//return "1~写卡数据报文错误！";
	try
	{		
		var cardformnum = new Date().getTime();
		
		// 获取加密数据
		var encryptedData = asynGetEncryptedData(SIMSerialCommon,cardInfoStr,cardformnum, basePath,telnum);
		
		if(encryptedData.indexOf("1~~") != -1)
		{
			return "1~获取写卡数据失败！";
		}
		
		if(encryptedData.length < 4)
		{
			return "1~写卡数据报文错误！";
		}
		
		// 预置空卡写卡数据加密报文
		var preSetBlankCardData = encryptedData.substring(3);
		
		// 获取空白卡序列号
		var getBlankCardSNResult = writeCardObject.Get_CardSN(); // var getBlankCardSNResult = "12345678901234567890|0000";// 测试用
		
		// 判断空白卡序列号是否正确
		var blankCardSN = analyzeReadCardInfo(getBlankCardSNResult, "获取空白卡序列号错误！");
	    if(blankCardSN.indexOf("1~") != -1)
		{
			return blankCardSN;
		}
				
		if(blankCardSN != SIMSerialCommon)
		{
			return "1~空白卡序列号不一致,请联系营业厅管理员！";
		    
		}
		
		// 写卡
		var writeCardResult = writeCardObject.Write_Card(preSetBlankCardData);// var writeCardResult = "ABCDEFG|0000";// 测试用
		var writeCardInfo = analyzeReadCardInfo(writeCardResult, "写卡失败");
		
		// 设置是否需要退卡
		document.getElementById("callBackCard").value = "1";
		
		if(writeCardInfo.indexOf("1~") != -1)
		{
			// 写卡失败，异步调用作废卡接口
			asynUpdateWriteCardResult(SIMSerialCommon,cardInfoStr,basePath);
			
			// 将卡移到回收箱
			var callbackcard = callBackCard();
			if(-1 == callbackcard)
			{
				return "1~将卡移至回收箱失败！";
			}
			return "1"+writeCardInfo;
		}

		// 写卡成功，去验证写卡结果报文，如果报文验证错误，则返回错误
		var chkWriteCardInfoResult = checkWriteDataResult(SIMSerialCommon,writeCardInfo,cardformnum,cardInfoStr,basePath);
		
		if(chkWriteCardInfoResult == "0")
		{
			return "0";
		}
		else if(chkWriteCardInfoResult == "1")
		{
			// 将卡移到回收箱
			var callbackcard = callBackCard();
			if(-1 == callbackcard)
			{
				return "1~将卡移至回收箱失败！";
			}
			
			//写卡结果报文验证失败
			return "11~写卡结果验证写卡失败！";
		}
		else
		{
			// 获取写卡结果验证报文失败
			return "1~获取写卡结果验证报文失败！";
		}
	}
	catch(b)
	{
		return "1~获取写卡控件异常！";
	}
	
}
   
// 公共函数 ：解析读卡控件返回信息
function analyzeReadCardInfo(readCardResult, showErrMsg)
{	
	try
	{
		var readCardResultArr = readCardResult.split(splitChar);
		if(!readCardResultArr || readCardResultArr.length != 2)
		{
		    return "1~读取卡片信息错误！";	
		}
		
		if(readCardResultArr[resultCodeIndex] != successCode)
		{
			// 显示错误码对应的错误信息
			return "1~"+OPSGetErrorMsg(readCardResultArr[errCodeIndex], showErrMsg);	
		}

		return readCardResultArr[resultInfoIndex];
	}
	catch(e)
	{
		return "1~解析读卡控件返回信息异常！";
	}
	
}

// 根据错误码获取错误信息
function OPSGetErrorMsg(errorCode, showErrMsg)
{
	try
	{	
		var errInfo = writeCardObject.Get_OPSErrorMsg(errorCode);//var errInfo = "写卡失败了|0000";// 测试用
		var errInfoArr = errInfo.split(splitChar);
		if(errInfoArr && errInfoArr.length == 2)
		{
			if(errInfoArr[resultCodeIndex] != successCode)
			{
				return showErrMsg;
	      	}
	      	else
	      	{
	      		return errInfoArr[resultInfoIndex];
	      	}
		}
		else
		{
			return showErrMsg;
		}
	}
	catch(e)
	{
		return "根据错误码获取错误信息异常！";
	}			
}

// 读取空白卡序列号
function readBlankCardSN()
{
	//return "12345678901234567890";// 测试用
	try
	{
		// 获取空白卡序列号
		var getBlankCardSNResult = writeCardObject.Get_CardSN(); 
		
		// 判断空白卡序列号是否正确
		return analyzeReadCardInfo(getBlankCardSNResult, "获取空白卡序列号错误！");
	}
	catch(e)
	{
		return "1~读取空白卡序列号失败！";
	}
}


// 异步获取写卡加密数据
function asynGetEncryptedData(blankno,cardInfoStr,cardformnum,basePath,telnum)
{
	// URL
	var url = basePath + "cardInstall/asynGetEncryptedData.action";
	// 参数
	var params = "blankno=" + blankno;
	    params = params + "&cardInfoStr=" + cardInfoStr ;
	    params = params + "&cardformnum=" + cardformnum ;
	    params = params + "&telnum=" + telnum ;
	
	// 调用
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			res = this.req.responseText;
	}, null, "POST", params, null);
	
	// 返回
	return res;
}

// 校验写卡是否成功
function checkWriteDataResult(SIMSerialCommon,writeCardInfo,cardformnum,cardInfoStr,basePath)
{
	// URL
	var url = basePath + "cardInstall/checkWriteCardInfo.action";
	// 参数
	var params = "blankno=" + SIMSerialCommon;
	    params = params + "&writeCardInfo=" + writeCardInfo ;
	    params = params + "&cardformnum="+cardformnum;
		params = params + "&cardInfoStr=" + cardInfoStr ;
		
	// 调用
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			res = this.req.responseText;
	}, null, "POST", params, null);
	
	// 返回
	return res;
}

// 异步调用写卡失败作废卡信息接口
function asynUpdateWriteCardResult(blankno,cardInfoStr,basePath)
{
	// URL
	var url = basePath + "cardInstall/asynUpdateWriteCardResult.action";
	
	// 参数
	var params = "blankno=" + blankno;
	    params = params + "&cardInfoStr=" + cardInfoStr ;
	
	// 调用
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			res = this.req.responseText;
	}, null, "POST", params, null);
}

/**
 * 自助终端开户受理小票打印
 * @param {Object} piData
 * @return {TypeName} 
 */
function printInstallTicket(piData) 
{
	try
	{
		var prtpluginidEle = window.parent.document.getElementById("prtpluginid");

		if (piData != "undefined") 
		{
			var ret = prtpluginidEle.GetPrinterStatus();// var ret = 0;// 测试用
			
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
			ret = prtpluginidEle.PrintPicture(1);
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
			ret = prtpluginidEle.PrintLine("  ");
			ret = prtpluginidEle.PrintLine("  ");
	  	    ret = prtpluginidEle.PrintLine("  自助终端空白卡开户受理交易凭据");
			ret = prtpluginidEle.PrintLine(" ---------------------------------------------");
			if (ret == 1) 
			{
	        	alertError("警告:打印机缺纸或故障!");
				return;
			}  	
	    
	        //打印具体缴费信息
	  	    ret = prtpluginidEle.PrintLine("  终端信息: " + piData.termId);
	  	    ret = prtpluginidEle.PrintLine("  手机号码: " + piData.servnumber);
	  	    ret = prtpluginidEle.PrintLine("  主体产品: " + piData.mainprodname);
	  	    ret = prtpluginidEle.PrintLine("  开户金额: " + piData.receptionFee + " 元");
	  	    ret = prtpluginidEle.PrintLine("  投币金额: " + piData.tMoney + " 元");
	  	    ret = prtpluginidEle.PrintLine("  受理时间: " + piData.pDealTime);
	  	    
	  	    // 缴费成功
	  	    if(piData.payStatus == 0)
	  	    {
		  	    ret = prtpluginidEle.PrintLine("  缴费流水: " + piData.dealNum);
	  	    }
	  	    if(piData.installStatus == 0)
	  	    {
	  	    	ret = prtpluginidEle.PrintLine("  受理流水: " + piData.formnum);
	  	    }
	  	    ret = prtpluginidEle.PrintLine("  交易状态: " + piData.pDealStatus);
	        
	        //打印尾部信息
			ret = prtpluginidEle.PrintLine(" ---------------------------------------------"); 
	  	    ret = prtpluginidEle.PrintLine("  以上内容,如有不详之处,请向营业员查询");
	  	    ret = prtpluginidEle.PrintLine("  客户服务热线:10086");
	  	    ret = prtpluginidEle.PrintLine(" ---------------------------------------------");
	  	    ret = prtpluginidEle.PrintLine("  打印地点:" + piData.pOrgName);
	  	    ret = prtpluginidEle.PrintLine("  打印时间:" + piData.pPrintDate);
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
	catch(e) 
	{
		alertError("对不起,票据打印机故障!");
		return;
	}
		
}
	   	
//切纸      
function cutPaper() 
{	
	try
	{
	  	var ret = window.parent.document.getElementById("prtpluginid").SetCutPaper();// var ret = 0;// 测试用
	  	if (ret == 1)
	  	{
			alertError("警告:打印机缺纸或故障!");
			return;
	  	}
	}
	catch(e)
	{
		alertError("警告:打印机故障!");
		return;
	}
  	//alert("切纸");
}

// 弹出提示框
function alertError(info) 
{
    var oProcessDiv = document.getElementById("process");				
	if(info!="" && oProcessDiv) 
	{
  		oProcessDiv.style.display = "";
	  	oProcessDiv.innerHTML = "<font color=red>" + info + "</font>";
	}
}

/**
 *方法名称：initBlankCardReader()
 *调用接口：short InitCard()
 *方法功能：初始化并校验读卡、写卡器是否正常
 *参数：无
 *返回："" 表示操作成功，否则 见返回值说明。
 */
function initBlankCardReader()
{
	//return "";//测试使用
	
	var message = "";
	
	try
	{ 
		var ret = window.parent.document.getElementById("simcardpluginid").InitCard();
		
		if (ret != 0) 
		{
			message = "警告:读卡器故障!";
		}
	}
	catch(e)
	{
		message = "警告:读卡器故障!";
	}
	
	return message;
}

/**
 *方法名称：initOpenIdCardReader()
 *调用接口：short OpenCom()
 *方法功能：初始化并校验二代身份证读卡器是否正常
 *参数：无
 *返回："" 表示操作成功，否则 见返回值说明。
 */
function initOpenIdCardReader()
{
	//return "";//测试使用
	
	var message = "";
	
	try
	{
		var ret = window.parent.document.getElementById("idcardpluginid").OpenCom();
		
		if (ret != 0) 
		{
			message = "警告:身份证读卡器故障!";
		}
	}
	catch(e)
	{
		message = "警告:身份证读卡器故障!";
	}
	
	return message;
}

/**
 *方法名称：initListPrinter()
 *调用接口：short InitListPrinter()
 *		short SetPicturePath()
 *方法功能：初始化并校验票据打印机是否正常
 *参数：无
 *返回："" 表示操作成功，否则 见返回值说明。
 */
function initListPrinter() 
{
	//return "";//测试使用
	
	var message = "";
	
	try 
	{
        // 初始化票据打印机
		var initListPrt1 = window.parent.document.getElementById("prtpluginid").InitListPrinter();
		if (initListPrt1 == 1) 
		{
			message = "警告:票据打印机缺纸或故障!";
		} 
		else if (initListPrt1 == 41) 
		{
			message = "错误:票据打印机设备低层驱动程序未安装!";
		}
		
    	// 设置打印图标的路径 
		var initListPrt2 = window.parent.document.getElementById("prtpluginid").SetPicturePath("");
		if (initListPrt2 == 1) 
		{
			message = "警告:票据打印机缺纸或故障!";
		} 
		else if (initListPrt2 == 41) 
		{
			message = "错误:票据打印机设备低层驱动程序未安装!";
		}
	}
	catch (e) 
	{
		message = "发生异常,票据打印机初始化失败,无法打印小票!";
	}
	
	return message;
}

/**
 *方法名称：initCashPayEquip()
 *调用接口：short OpenCom()
 *方法功能：初始化并校验现金识币器是否正常
 *参数：无
 *返回："" 表示操作成功，否则 见返回值说明。
 */
function initCashPayEquip() 
{
	//return "";//测试使用
	
	var message = "";
	
	try 
	{
		var ret = window.parent.document.getElementById("cashpluginid").OpenCom();
		
		if (ret != 0) 
		{
			message = "警告:现金识币器故障!";
		}
	}
	catch (e) 
	{
		message = "警告:现金识币器故障!";
	}
	
	return message;
}

/**
 *方法名称：initUnionCardPayEquip(unionPayCode, unionUserId)
 *调用接口：short InitConfig()
 *方法功能：初始化并校验银联读卡器是否正常
 *参数：有，unionPayCode:银联终端ID, unionUserId:银联商户号
 *返回："" 表示操作成功，否则 见返回值说明。
 */
function initUnionCardPayEquip(unionPayCode, unionUserId) 
{
	//return "";//测试使用
	
	var message = "";
	
	try
	{
		var ret = window.parent.document.getElementById("cardpluginid").InitConfig(unionPayCode, unionUserId);
		
		if (ret != 0) {
		    
			message = "警告:银联读卡器初始化失败!";
		}
	}
	catch(e)
	{
		message = "警告:银联读卡器初始化异常!";
	}
	
	return message;
}

/**
 *方法名称：checkReadCardStatus()
 *调用接口：short InitConfig()
 *	ReadCardStatus()
 *	short MoveCardToWrite()
 *方法功能：检查发卡器状态
 *参数：无
 *返回："" 表示操作成功，否则 见返回值说明。
 */
function checkReadCardStatus()
{
	//return "";//测试使用
	
	try
	{
		// 检查写卡器是否已经插入卡
		var stat = window.parent.document.getElementById("simcardpluginid").IsCardExist();
		
		if (stat == 0)
		{
			return "";
		}
		
		/**
		功能：检查发卡器状态
		参数：无
		返回：失败：-1；成功：0~通道卡片位置~卡箱卡片状态
		举例：如返回 0~0~1 表示 通道无卡，卡箱卡片不足,提醒需要加卡
		           0~2~0 表示 IC卡位置有卡，卡箱无卡
		通道卡片位置：长度一个字节。
		0：通道无卡
		1：读磁卡位置有卡
		2：IC卡位置有卡
		3：前端夹卡位置有卡
		4：前端不夹卡位置有卡
		注意：当卡不在以上四个位置时，自动将卡移至回收箱，再返回通道和卡片箱状态。
		卡箱卡片状态：
		  0:  卡箱无卡
		  1:  卡箱卡片不足,提醒需要加卡
		  2:  卡箱卡片足够
		场景：初始化发卡器后，需要调用此接口检查设备状态。
		**/
		stat = window.parent.document.getElementById("simcardpluginid").ReadCardStatus();
		
		if (stat == -1)
		{
			return "检查读卡器状态失败！";
		}
		
		var arr = stat.split('~');
		if(arr[2] == 0)
		{
			 return "卡箱卡片不足,需要加卡！";
		}
			
		stat = window.parent.document.getElementById("simcardpluginid").MoveCardToWrite();
		
		if (stat == -1)
		{
			return "将空白卡走到读卡位置失败！";
		}
	}
	catch(e)
	{
		return "读取空白卡信息出现异常";
	}
	
	return "";
}

/**
返回：成功0；失败-1
场景：开户成功后，调用该接口，将写好的SIM卡吐出来
**/
function MoveOutCard()
{
	//return 0;// 测试用
	try{
		var a=window.parent.document.getElementById("simcardpluginid").MoveOutCard();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}

/**
返回：成功0；失败-1
场景：写卡失败后将卡移至回收箱
**/
function callBackCard()
{
	//return 0;// 测试用
	try{
		var a=window.parent.document.getElementById("simcardpluginid").callBackCard();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}

/**
返回：成功0；失败-1
场景：将发卡器/写卡器设置为不可使用状态
与InitCard()接口对应。InitCard接口调用后，允许使用该设备，使用完毕该控件后调用CloseCard()
**/
function CloseCard()
{
	//return 0; // 测试用
	try{
		var a=window.parent.document.getElementById("simcardpluginid").CloseCard();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}

/**
 * 自助终端备卡受理小票打印
 * @param {Object} piData
 * @return {TypeName} 
 */
function printPrepareTicket(piData) 
{
	try
	{
		var prtpluginidEle = window.parent.document.getElementById("prtpluginid");

		if (piData != "undefined") 
		{
			var ret = prtpluginidEle.GetPrinterStatus();
			//var ret = 0;// 测试用
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
			ret = prtpluginidEle.PrintPicture(1);
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
			ret = prtpluginidEle.PrintLine("  ");
			ret = prtpluginidEle.PrintLine("  ");
	  	    ret = prtpluginidEle.PrintLine("  自助终端空白卡备卡受理交易凭据");
			ret = prtpluginidEle.PrintLine(" ---------------------------------------------");
			if (ret == 1) 
			{
	        	alertError("警告:打印机缺纸或故障!");
				return;
			}  	
	    
	        //打印具体缴费信息
	  	    ret = prtpluginidEle.PrintLine("  终端编号: " + piData.termId);
	  	    ret = prtpluginidEle.PrintLine("  手机号码: " + piData.servnumber);
	  	    ret = prtpluginidEle.PrintLine("  应缴金额: " + piData.receptionFee + " 元");
	  	    ret = prtpluginidEle.PrintLine("  实缴金额: " + piData.tMoney + " 元");
	  	    ret = prtpluginidEle.PrintLine("  受理时间: " + piData.pDealTime);
	  	    
	  	    // 缴费成功
	  	    if(piData.payStatus == 0)
	  	    {
		  	    ret = prtpluginidEle.PrintLine("  缴费流水: " + piData.dealNum);
	  	    }
	  	    if(piData.installStatus == 0)
	  	    {
	  	    	ret = prtpluginidEle.PrintLine("  受理流水: " + piData.formnum);
	  	    }
	  	    ret = prtpluginidEle.PrintLine("  交易状态: " + piData.pDealStatus);
	        
	        //打印尾部信息
			ret = prtpluginidEle.PrintLine(" ---------------------------------------------"); 
	  	    ret = prtpluginidEle.PrintLine("  以上内容,如有不详之处,请向营业员查询");
	  	    ret = prtpluginidEle.PrintLine("  客户服务热线:10086");
	  	    ret = prtpluginidEle.PrintLine(" ---------------------------------------------");
	  	    ret = prtpluginidEle.PrintLine("  打印地点:" + piData.pOrgName);
	  	    ret = prtpluginidEle.PrintLine("  打印时间:" + piData.pPrintDate);
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
	catch(e) 
	{
		alertError("对不起,票据打印机故障!");
		return;
	}
		
}

/**
 * 自助终端补卡受理小票打印
 * @param {Object} piData
 * @return {TypeName} 
 */
function printReissueTicket(piData) 
{
	try
	{
		var prtpluginidEle = window.parent.document.getElementById("prtpluginid");

		if (piData != "undefined") 
		{
			var ret = prtpluginidEle.GetPrinterStatus();
			//var ret = 0;// 测试用
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
			ret = prtpluginidEle.PrintPicture(1);
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
			ret = prtpluginidEle.PrintLine("  ");
			ret = prtpluginidEle.PrintLine("  ");
	  	    ret = prtpluginidEle.PrintLine("  自助终端空白卡补卡受理交易凭据");
			ret = prtpluginidEle.PrintLine(" ---------------------------------------------");
			if (ret == 1) 
			{
	        	alertError("警告:打印机缺纸或故障!");
				return;
			}  	
	    
	        //打印具体缴费信息
	  	    ret = prtpluginidEle.PrintLine("  终端编号: " + piData.termId);
	  	    ret = prtpluginidEle.PrintLine("  手机号码: " + piData.servnumber);
	  	    ret = prtpluginidEle.PrintLine("  应缴金额: " + piData.receptionFee + " 元");
	  	    ret = prtpluginidEle.PrintLine("  实缴金额: " + piData.tMoney + " 元");
	  	    ret = prtpluginidEle.PrintLine("  受理时间: " + piData.pDealTime);
	  	    
	  	    // 缴费成功
	  	    if(piData.payStatus == 0)
	  	    {
		  	    ret = prtpluginidEle.PrintLine("  缴费流水: " + piData.dealNum);
	  	    }
	  	    if(piData.installStatus == 0)
	  	    {
	  	    	ret = prtpluginidEle.PrintLine("  受理流水: " + piData.formnum);
	  	    }
	  	    ret = prtpluginidEle.PrintLine("  交易状态: " + piData.pDealStatus);
	        
	        //打印尾部信息
			ret = prtpluginidEle.PrintLine(" ---------------------------------------------"); 
	  	    ret = prtpluginidEle.PrintLine("  以上内容,如有不详之处,请向营业员查询");
	  	    ret = prtpluginidEle.PrintLine("  客户服务热线:10086");
	  	    ret = prtpluginidEle.PrintLine(" ---------------------------------------------");
	  	    ret = prtpluginidEle.PrintLine("  打印地点:" + piData.pOrgName);
	  	    ret = prtpluginidEle.PrintLine("  打印时间:" + piData.pPrintDate);
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
	catch(e) 
	{
		alertError("对不起,票据打印机故障!");
		return;
	}
		
}
