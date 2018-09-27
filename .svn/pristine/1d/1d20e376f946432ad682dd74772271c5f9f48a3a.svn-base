
/**********终端现金识币器控件PsCashCode.ocx**********/
/**
 *方法名称：initCashEquip(servNumber)
 *调用接口：short InitCashBill(servNumber)
 *方法功能：初始化OMRON识币器
 *参数：无
 *返回：0 表示操作成功，否则 见返回值说明。
 */
function initCashEquip(servNumber) {
	//return '0,20110509143345';//测试使用
	try {
		var cashFlag = window.parent.document.getElementById("cashpluginid").InitCashBill(servNumber);
		return cashFlag;
	}
	catch (e) {
        //解决初始化失败问题
        try {
            window.parent.document.getElementById("cashpluginid").OpenCom();
            var reCashFlag = window.parent.document.getElementById("cashpluginid").InitCashBill(servNumber);
		    return reCashFlag;
        }
        catch (e) {
            return -99;
        }
		return -99;
	}
}

function initCashEquip_sd(servNumber) {
	//return '0,20110509143345';//测试使用
	try {
		var cashFlag = window.parent.document.getElementById("cashpluginid").InitCashBill(servNumber);
		if (cashFlag.substring(0, 2) == "51")
		{
			//解决初始化失败问题
	        try {
	            window.parent.document.getElementById("cashpluginid").OpenCom();
	            var retFlag = window.parent.document.getElementById("cashpluginid").InitCashBill(servNumber);
			    return retFlag;
	        }
	        catch (e) {
	            return -99;
	        }
			return -99;
		}
		return cashFlag;
	}
	catch (e) {
        //解决初始化失败问题
        try {
            window.parent.document.getElementById("cashpluginid").OpenCom();
            var reCashFlag = window.parent.document.getElementById("cashpluginid").InitCashBill(servNumber);
		    return reCashFlag;
        }
        catch (e) {
            return -99;
        }
		return -99;
	}
}

/**
 *方法名称：getOnceCash()
 *调用接口：short GetCashBill (short nTimOut)
 *方法功能：获取投币金额
 *参数：无
 *返回：0 表示没有投币，否则 为投币面值(可能的值为：1,2,5,10,20,50,100)。
 */
function getOnceCash() {
	//return 5;//测试用
	var onceMoney = window.parent.document.getElementById("cashpluginid").GetCashBill(1);
	return onceMoney;
}
function cancelGetCash() {
	window.parent.document.getElementById("cashpluginid").CancelAccept();
}

//增加识币器日志附加属性
function setLogAddInfo(content) {
	//return 0;//测试用
	var result = window.parent.document.getElementById("cashpluginid").SetAddInfor(content);
	return result;
}
/**
 *方法名称：checkCashStatus()
 *调用接口：short CheckCashState()
 *方法功能：检测识币器状态
 *参数：无
 *返回：状态含义:0-正常 1-异常 2-钱箱满 3-钱箱打开 4-入口被夹 5-钱箱被夹 6-其它故障 9-无此设备。
 */
function checkCashStatus() {
	//return 0;//测试用
	var cashStatus = window.parent.document.getElementById("cashpluginid").CheckCashState();
	return cashStatus;
}

function fCloseCashBill() {
    //return 0;//测试用
	try {
		var v = window.parent.document.getElementById("cashpluginid").CloseCashBill();
		return v;
	}
	catch (e) {
		return -99;
	}
}

