
/**
 * 自助售货机小票打印
 */
function sellGoodsPrint(prtData) {
	//return true; //测试使用
  	try {
      	var v = window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
		if ( v == 1){ 
		    pubErrShow("对不起,票据打印机发生故障,请联系营业员!");
		    return;
		}else if (v == 2) {
		    pubErrShow("对不起,票据打印机缺纸,请联系营业员!");
		    return;
		}else if (v != 0){
		    pubErrShow("对不起,票据打印机发生错误,请联系营业员!");
		    return;
		}
  
	  	//打印移动图标 	
	  	var ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
	  	if (ret == 1)
	  	{
	  	   pubErrShow("警告:打印机缺纸或故障!");
	  	   return;
	  	}
	  	else if (ret == 41)
	  	{
	  	   pubErrShow("错误:打印机设备低层驱动程序未安装!");
	  	   return;
	  	}
	  	
	  	//打印头部信息
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 自助售货机交易凭据");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 终端编号: " + prtData.termID);
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 交易流水: " + prtData.serialNum);
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 商品名称: " + prtData.merchandiseName);
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 交易时间: " + prtData.recDate);
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 购买数量: " + prtData.shopNum + "件");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 出货数量: " + prtData.shipmentNum + "件");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 支付方式: " + prtData.payType);
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 支付账户: " + prtData.cardNO);
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 交易金额: " + prtData.Money);
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 交易结果: " + prtData.payResult);
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 以上内容,如有不详之处,请向营业员查询.");
	  	//delete begin g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
  	    //delete end g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 购买地点: " + prtData.pOrgName+".");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 打印时间: " + prtData.pPrintDate+".");
	  	if (ret == 1)
	  	{
	  	    pubErrShow("警告:打印机缺纸或故障!");
	  	    return;
	  	}    
			
		//正常打印结束,切纸
	 	cutPaper();
	} catch(ex) {
	 		pubErrShow("打印缴费凭条出错,请联系营业厅服务员查看缴费是否成功！");
	 		cutPaper();//出现问题切纸
	}	
}

//切纸      
function cutPaper() {
  	var ret = window.parent.document.getElementById("prtpluginid").SetCutPaper();
  	if (ret == 1)
  	{
  		  pubErrShow("警告:打印机缺纸或故障!");
  		  return;
  	}	
}
