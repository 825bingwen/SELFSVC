/*
 * 湖北小票打印
 * Add by Lifeng
 */
/**
 * 重复缴费凭据打印
 * @param {Object} prtData
 * @return {TypeName} 
 */
function repeatFeePrint(prtData) 
{
	try 
	{
		var pObj = window.parent.document.getElementById("prtpluginid");
		var v = pObj.GetPrinterStatus();
		if (v == 1) 
		{
			alertError("对不起,票据打印机发生故障,请联系营业员!");
			return;
		}
		else if (v == 2) 
		{
			alertError("对不起,票据打印机缺纸,请联系营业员!");
			return;
		} 
		else if (v != 0) 
		{
			alertError("对不起,票据打印机发生错误:" + v + ",请联系营业员!");
			return;
		}

		//打印移动图标 	
		var ret = pObj.PrintPicture(1);
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
		ret = pObj.PrintLine("  ");
		ret = pObj.PrintLine("  ");
		ret = pObj.PrintLine("  手机号码: " + prtData.pServNumber);
		ret = pObj.PrintLine("  自助缴费机交易凭据");
		ret = pObj.PrintLine(" ---------------------------------------------");
		if (ret == 1) 
		{
			alertError("警告:打印机缺纸或故障!");
			return;
		}

		//打印具体缴费信息
		ret = pObj.PrintLine("  终端编号  : " + prtData.pTermID);
		ret = pObj.PrintLine("  终端信息  : " + prtData.pTerminalInfo);
		ret = pObj.PrintLine("  交易流水号: " + prtData.pTerminalSeq);
		ret = pObj.PrintLine("  交易时间  : " + prtData.pRecDate);
		ret = pObj.PrintLine("  投币金额  : " + prtData.pAmount);
		ret = pObj.PrintLine("  交易状态  : " + prtData.pDealStatus);
		if (ret == 1) 
		{
			alertError("警告:打印机缺纸或故障!");
			return;
		}

		//打印尾部信息
		ret = pObj.PrintLine(" ---------------------------------------------");
		ret = pObj.PrintLine("  以上内容,如有不详之处,请向营业员查询");
		ret = pObj.PrintLine("  客户服务热线:10086");
		ret = pObj.PrintLine(" ---------------------------------------------");
		ret = pObj.PrintLine("  打印地点:" + prtData.pOrgName + "");
		ret = pObj.PrintLine("  打印时间:" + prtData.pPrintDate + "");
		if (ret == 1) 
		{
			alertError("警告:打印机缺纸或故障!");
			return;
		}

		//正常打印结束,切纸
		cutPaper();
	} 
	catch (ex) 
	{
		alertError("对不起,打印缴费凭条出错,请到营业厅找营业员核实缴费情况。");
		//出现问题切纸
		cutPaper();
	}
}
/**
 * 测试重复缴费凭据打印
 * @param {Object} prtData
 * @return {TypeName} 
 */
function _repeatFeePrint(prtData) 
{
	try 
	{
		var tmpStr = "";
		tmpStr = tmpStr + "  手机号码  : " + prtData.pServNumber + "\n";
		tmpStr = tmpStr + "  自助缴费机交易凭据" + "\n";
		tmpStr = tmpStr + " ---------------------------------------------" + "\n";

		//打印具体缴费信息
		tmpStr = tmpStr + "  终端编号  : " + prtData.pTermID + "\n";
		tmpStr = tmpStr + "  终端信息  : " + prtData.pTerminalInfo + "\n";
		tmpStr = tmpStr + "  交易流水号: " + prtData.pTerminalSeq + "\n";
		tmpStr = tmpStr + "  交易时间  : " + prtData.pRecDate + "\n";
		tmpStr = tmpStr + "  投币金额  : " + prtData.pAmount + "\n";
		tmpStr = tmpStr + "  交易状态  : " + prtData.pDealStatus + "\n";


		//打印尾部信息
		tmpStr = tmpStr + " ---------------------------------------------" + "\n";
		tmpStr = tmpStr + "  以上内容,如有不详之处,请向营业员查询" + "\n";
		tmpStr = tmpStr + "  客户服务热线:10086" + "\n";
		tmpStr = tmpStr + " ---------------------------------------------" + "\n";
		tmpStr = tmpStr + "  打印地点:" + prtData.pOrgName + "" + "\n";
		tmpStr = tmpStr + "  打印时间:" + prtData.pPrintDate + "" + "\n";
		
		alert(tmpStr);

	} 
	catch (ex) 
	{
		alertError("对不起,打印缴费凭条出错,请到营业厅找营业员核实缴费情况。");
	}
}

/**
 * 切纸
 * @return {TypeName} 
 */
function cutPaper() 
{
	var ret = window.parent.document.getElementById("prtpluginid").SetCutPaper();
	if (ret == 1) 
	{
		alertError("警告:打印机缺纸或故障!");
		return;
	}
}