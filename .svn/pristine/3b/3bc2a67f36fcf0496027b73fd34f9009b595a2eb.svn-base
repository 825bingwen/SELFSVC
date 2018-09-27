function  billitemPrinter(printFlag,vPrintMsg,servnumber,cycle,subsname,sUpperTotalFee,sTotalFee,sBusiPlaceName,curDay){
    if("1"==printFlag){
        if( vPrintMsg!=null && vPrintMsg.length > 0){
		        //打印图标
				    var Ret3 = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
				    if (Ret3 == 1)
				    {
				        pubErrShow("警告:打印机缺纸或故障!");
				        return;
				    } else if (Ret3 == 41)
				    {
				        pubErrShow("错误:打印机设备低层驱动程序未安装!");
				        return;
				    }
				    var Ret4;
			      Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
			      Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
			      Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 手机号码: "+ servnumber );
			      Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 帐单月份: "+ cycle );
			      Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 帐户名称: "+ subsname);
			      Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		        for(var i=0;i<vPrintMsg.length;i++){
                //打印一行
		            Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(vPrintMsg[i]); //打印
		            if (Ret4 == 1)
		            {
		                pubErrShow("警告:打印机缺纸或故障!");
		                return;
		            } else if (Ret4 == 41)
		            {
		                pubErrShow("错误:打印机设备低层驱动程序未安装!");
		                return;
		            }
		        }
		        Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
		        Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 帐户帐单合计:"+sUpperTotalFee +"("+sTotalFee +")");           //帐户帐单合计
		        Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); //打印说明
		        Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 说明:费用单位为元."); 
		        Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 声明:本帐单信息仅供客户核对之用,不做任何凭据.");
		        Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 提示:为保护您的密码不被他人恶意窃用,请经常修改;");
		        Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 以上内容,如有不详之处,请向营业员查询.");
		        Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 客户服务热线:10086.");
		        Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		        Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 打印地点:" + sBusiPlaceName + ".");
		        Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 打印时间:" + curDay + ".");
		    }else{
	          pubErrShow("对不起,没有可打印的数据!");
	          return;
	      }
        var Ret5 = window.parent.document.getElementById("prtpluginid").SetCutPaper();
	      if (Ret5 == 1)
        {
	          pubErrShow("警告:打印机缺纸或故障!");
	          return;
        } else if (Ret5 == 41)
        {
	          pubErrShow("错误:打印机设备低层驱动程序未安装!");
	          return;
        }
        pubErrShow("打印已经完成!");
        
    }
}

function fGetPrinterStatus(){
	try {
		var initListPrt3 = window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
        return initListPrt3;
	}
	catch (e) {
		return -99;
	}
}
