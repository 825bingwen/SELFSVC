
/**
 * 优惠卷打印
 */
function fAbateLipPrint(prtData) {
	//return true; //测试使用
  	try {
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
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
	  	for(var i = 0;i<prtData.prtNum;i++){
	  	    ret = window.parent.document.getElementById("prtpluginid").PrintThePicture(prtData.localImgURL);
	  	}
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 以上内容,如有不详之处,请向营业员查询.");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" 打印地点: " + prtData.pOrgName+".");
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

function fGetPrinterStatus() {
	try {
		var initListPrt3 = window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
		return initListPrt3;
	}
	catch (e) {
		return -99;
	}
}
