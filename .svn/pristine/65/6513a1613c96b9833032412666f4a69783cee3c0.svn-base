
function fVending(boothCode) {
    //测试
    //return -99;
	try {
		var ret = window.parent.document.getElementById("sellgoodspluginid").Vending(boothCode);
		return ret;
	}
	catch (Ex) {
		return -99;
	}
}

function fSellReturnInfo(code){
    if(code == "-7"){return "无法定位产生错误原因";}
    else if(code == "-8"){return "执行指定指令动作失败";}
    else if(code == "-9"){return "参数不符合规范";}
    else if(code == "-10"){return "设备串口读写失败";}
    else if(code == "-11"){return "数据异常";}
    else if(code == "-12"){return "校验错误";}
}
