
function selfPursePrint(prtData) {
	if (prtData != "undefined" ) {
		        //打印图标
		var Ret3 = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
		if (Ret3 == 1) {
			pubErrShow("警告:打印机缺纸或故障!");
			return;
		} else {
			if (Ret3 == 41) {
				pubErrShow("错误:打印机设备低层驱动程序未安装!");
				return;
			}
		}
		var Ret4;
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 商户存根                        MERCHANT COPY");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 商户名称(MERCHANT NAME): " + prtData.businessName);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 商户编号(MERCHANT NO.): " + prtData.businessID);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 终端编号(TERMINAL ID): " + prtData.posTermID);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 操作员号(OPERATOR NO.): " + prtData.operID);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 卡    号(CARD NO.): " + prtData.recCardNO);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 发卡机构号(ISS NO.): " + prtData.sendCardID);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 受理机构号(ACQ NO.): " + "");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 交易类型(TRANS TYPE): " + "消费(SALE)");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 有 效 期(EXP): " + "");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 批 次 号(BATCH NO.): " + prtData.batchNum);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 凭 证 号(VOUCHER NO.): " + prtData.posSeqID);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 流 水 号(TRACE NO.): " + prtData.posOid);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 参 考 号(REF.NO.): " + "");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 日期/时间(DATE/TIME): " + prtData.recdate);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 交易金额(AMOUNT): " + prtData.money);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 备注/REFERENCE");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 应用标识AID: " + prtData.appAid);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 发卡方标识: " + prtData.sendCardID);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 脱机交易序号: " + prtData.offRecNum);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 密匙版本号:" + prtData.keyEdition);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 密匙索引号: " + prtData.keySeqNum);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 终端机编号: " + prtData.termCode);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 终端交易序号: " + prtData.termRecSeq);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" TAC: " + prtData.tacNum);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 为了您的合法权益,请妥善保留此凭条");
		//modify begin g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" 更多信息请查询www.cmpay.com");
  	    //modify end g00140516 2011/10/26 R003C11L10n01 去掉10086提示信息
	} else {
	    pubErrShow("对不起,没有可打印的数据!");
		return;
	}
	var Ret5 = window.parent.document.getElementById("prtpluginid").SetCutPaper();
	if (Ret5 == 1) {
	    pubErrShow("警告:打印机缺纸或故障!");
		return;
	} else {
		if (Ret5 == 41) {
	        pubErrShow("错误:打印机设备低层驱动程序未安装!");
			return;
		}
	}
	document.getElementById('recInfo').innerHTML = "打印已经完成，谢谢您的使用！";
    pubErrShow("打印已经完成!");
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

