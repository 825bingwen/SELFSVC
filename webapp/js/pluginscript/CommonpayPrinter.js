/**
 * 公共事业缴费凭条打印
 * paymentNum --公共事业缴费号码
 * pOrgName    --打印地点
 * printTime  --打印日期
 * termid    --终端信息
 * touchoid     --交易流水号
 * dealTime    --交易时间
 * moneyNum     --交易金额
 * dealStatus --交易状态
 * feeType      --缴费单位类型
 * serverNum      --缴费人信息
 */
function doPrintPayProof(prtData) {
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
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    自助缴费机交易凭据");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("   ---------------------------------------------");  	    
    if (ret == 1)
    {
        pubErrShow("警告:打印机缺纸或故障!");
        return;
    }  	
    
    //打印具体缴费信息
    ret = window.parent.document.getElementById("prtpluginid").PrintLine("    公共事业缴费号码: "+prtData.paymentNum);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    终端编号        : "+prtData.termid );
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    交易流水号      : "+prtData.touchoid);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    交易时间        : "+prtData.dealTime);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    交易金额        : "+prtData.moneyNum);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    缴费单位类型    : "+prtData.feeType);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    缴费单位名称    : "+prtData.unitName);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    缴费人信息      : "+prtData.serverNum);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    交易状态        : "+prtData.dealStatus);
    if (ret == 1)
    {
        pubErrShow("警告:打印机缺纸或故障!");
        return;
    }     
    
    
    //打印尾部信息
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("   ---------------------------------------------"); 
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    以上内容,如有不详之处,请向营业员查询.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    客户服务热线:10086.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("   ---------------------------------------------");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    打印地点:"+prtData.pOrgName+".");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    打印时间:"+prtData.dealTime+".");
  	if (ret == 1)
  	{
  	    pubErrShow("警告:打印机缺纸或故障!");
  	    return;
  	}    
			
		//正常打印结束,切纸
	 	cutPaper();
	} catch(ex) {
	 		setProcessInfo("打印缴费凭条出错,请联系营业厅服务员查看缴费是否成功！");
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
