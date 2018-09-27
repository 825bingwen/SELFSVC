//初始设置,如果使用默认值可不用调用此接口
function fInitReadUserCard(HandShakeValue, BalanceInquiryValue, WalletPayValue) {
	try {
    	//握手延时返回时间, 查询余额,支付
		var ret = window.parent.document.getElementById("pursepluginid").SetDelayPar(HandShakeValue, BalanceInquiryValue, WalletPayValue);
		return ret;
	}
	catch (Ex) {
		return -99;
	}
}
//握手
function fHandShake() {
	try {
		var ret = window.parent.document.getElementById("pursepluginid").HandShake();
		return ret;
	}
	catch (Ex) {
		return -99;
	}
}
//查询余额接口
function fBalanceInquiry() {
	try {
		var ret = window.parent.document.getElementById("pursepluginid").BalanceInquiry();
		return ret;
	}
	catch (Ex) {
		return -99;
	}
}
//支付接口
//金额，流水
function fWalletPay(Money, SerialNum) {
    //测试
    //return "0$00$00000001,A103010010011689,53100341,888053157000000,SDXCCS              ,01,00000053130000100280,000002,000150,140832,20100814,D1560001018003800000000100000000,D156000101000000,001A,01,03,155310000188,00000019,1FD9BD64";
	try {
		var ret = window.parent.document.getElementById("pursepluginid").WalletPay(Money, SerialNum);
		return ret;
	}
	catch (Ex) {
		return -99;
	}
}

//设置商户号
function fSetBusinessID(BusinessID) {
	try {
		var ret = window.parent.document.getElementById("pursepluginid").SetBusinessID(BusinessID);
		return ret;
	}
	catch (Ex) {
		return -99;
	}
}

//结算，手动结算
function fWalletClearing(BusinessID) {
	try {
		var ret = window.parent.document.getElementById("pursepluginid").WalletClearing();
		return ret;
	}
	catch (Ex) {
		return -99;
	}
}

//手机钱包应答码解析
function fGetReturnInfo(code){
	if(code == "01"){return "无效商户";}
		else if(code == "02"){return "无效POS终端";}
		else if(code == "03"){return "无效交易";}
		else if(code == "04"){return "无效金额";}
		else if(code == "08"){return "格式错误";}
		else if(code == "09"){return "无效应答";}
		else if(code == "10"){return "无效卡号";}
		else if(code == "11"){return "无此发卡行";}
		else if(code == "12"){return "怀疑操作有误";}
		else if(code == "13"){return "用户状态不合法";}
		else if(code == "14"){return "应用状态不合法";}
		else if(code == "15"){return "余额超过余额上限";}
		else if(code == "20"){return "无足够的存款";}
		else if(code == "21"){return "不予承兑";}
		else if(code == "22"){return "无此帐户";}
		else if(code == "23"){return "无此卡记录";}
		else if(code == "24"){return "存在未确认交易，不能退订";}
		else if(code == "30"){return "受限制的卡";}
		else if(code == "31"){return "超过允许的PIN试输入";}
		else if(code == "32"){return "不允许进行的交易";}
		else if(code == "33"){return "超出取款金额限制";}
		else if(code == "34"){return "超出取款次数限制";}
		else if(code == "35"){return "充值后余额超过上限";}
		else if(code == "40"){return "查发卡行";}
		else if(code == "41"){return "没收卡";}
		else if(code == "42"){return "重新送入交易";}
		else if(code == "43"){return "有作弊嫌疑";}
		else if(code == "44"){return "丢失卡";}
		else if(code == "45"){return "需要向网络中心签到";}
		else if(code == "46"){return "重复交易";}
		else if(code == "47"){return "脱机交易对帐不平";}
		else if(code == "48"){return "日期切换正在处理";}
		else if(code == "50"){return "与主机链路无法建立";}
		else if(code == "51"){return "出错";}
		else if(code == "52"){return "收到的回答太迟";}
		else if(code == "53"){return "PIN格式错";}
		else if(code == "54"){return "过期的卡";}
		else if(code == "55"){return "密码错误";}
		else if(code == "56"){return "流水号重复";}
		else if(code == "80"){return "报文MAC校验错";}
		else if(code == "81"){return "IC卡TAC/MAC校验错";}
		else if(code == "82"){return "加密机异常";}
		else if(code == "95"){return "主机系统异常";}
		else if(code == "98"){return "数据接收超时";}
		else if(code == "99"){return "POSP系统异常";}
		else if(code == "D0"){return "打印机缺纸";}
		else if(code == "D1"){return "读卡器连接错误";}
		else if(code == "D2"){return "用户刷卡失败";}
		else if(code == "D3"){return "GPRS拨号失败";}
		else if(code == "D4"){return "GPRS连接超时";}
		else if(code == "D6"){return "余额不足";}
		else if(code == "D7"){return "暂不支持该交易";}
		else if(code == "D8"){return "终端未签到";}
		else if(code == "D9"){return "终端结算失败";}
		else if(code == "DF"){return "交易失败";}
		else if(code == "-10"){return "无应答";}
		else if(code == "-11"){return "数据异常";}
		else if(code == "-12"){return "校验错误";}
}
