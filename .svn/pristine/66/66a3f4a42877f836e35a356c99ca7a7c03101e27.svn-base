// 银联卡缴费接口初始化方法
// 参数：unionUserId：银联商户号；unionPayCode：银联为刷卡的终端分配的唯一编号
// 返回：0 - 初始化成功
//      -1 - 初始化失败（写INI文件错误）
function initUnionPayPlugin(unionUserId,unionPayCode){
    //return 0;//测试使用
	try{
		var ret = window.parent.document.getElementById("cardpluginid").InitConfig(unionUserId,unionPayCode);
		return ret;
	}catch(Ex){
		return -99;
	}
}
/*输入密码完毕*/
function readPwdFinish(){
    //return 1; //测试使用
	try{
		var ret = window.parent.document.getElementById("cardpluginid").ReadPasswordFinished();
		return ret;
	}catch(Ex){
		return -99;
	}
}
/*等待输入密码*/
function readingPwd(){
    //return 0;//测试使用
	try{
		var ret = window.parent.document.getElementById("cardpluginid").ReadingPassword();
		return ret;
	}catch(Ex){
		return -99;
	}
}

/*银联扣款*/
function fPayByPos(posnum,batchnum,bankcardID,tMoney){
    //return "-1$超时不是我的错"; //测试使用
    //return "0$00$123456789101112$200907031049$100";//测试使用
	try{
		var ret=window.parent.document.getElementById("cardpluginid").Pay(posnum,batchnum,bankcardID,tMoney)
		return ret;
	}catch(Ex){
		return -1;
	}
}

/*银联冲正*/
function cancelPayToUnion(posnum,batchnum,bankcardID,money){
	try{
		var ret=window.parent.document.getElementById("cardpluginid").CancelPay(posnum,batchnum,bankcardID,money);
		return ret;
	}catch(Ex){
		return -1;
	}
}

/*等待用户刷卡*/
function waitingForCard(){
    //return 0;//测试使用
	try{
		var ret = window.parent.document.getElementById("cardpluginid").ReadingBankCard();
		return ret;
	}catch(Ex){
		return -99;
	}
}

/*取消等待用户刷卡*/
function cancelWaitingCard(){
    //return 0; //测试使用
	try{
		var ret = window.parent.document.getElementById("cardpluginid").CancelReadingBankCard();
		return ret;
	}catch(Ex){
		return -1;
	}
}

/*读卡结束*/
function readCardFinish(){
    //return 0; //测试使用
	try{
		var ret = window.parent.document.getElementById("cardpluginid").ReadBankCardFinished();
		return ret;
	}catch(Ex){
		return -99;
	}
}

/*获取用户卡号*/
function getCardNum(){
    //return "1314520000000888";  //测试使用
	try{
		var ret = window.parent.document.getElementById("cardpluginid").GetBankCardID();
		return ret;
	}catch(Ex){
		return "";
	}
}

function signTo(){
	//return "0$00"; //测试使用
	try{
		var ret = window.parent.document.getElementById("cardpluginid").SigntoUnionPay();
		return ret;
	}catch(Ex){
		return -99;
	}
}

//退卡
function returnCard(){
    //alert("returnCard:退卡");
    //return 0; //测试使用
	try{
		var ret = window.parent.document.getElementById("cardpluginid").TakeOutBankCard();
		return ret;
	}catch(Ex){
		return -1;
	}
}

//获取读卡器取卡状态,1表示未取卡
function takeCardState(){
    //alert("takeCardState:获取读卡器取卡状态");
    //return 1; //测试使用
	try{
		var ret = window.parent.document.getElementById("cardpluginid").TakeOutBankCardStatus();
		return ret;
	}catch(Ex){
		return -1;
	}
}

//吞卡(河北需求)
function captureCard(){
    //return 0;//测试使用
	try{
		var ret = window.parent.document.getElementById("cardpluginid").CaptureBankCard();
		return ret;
	}catch(Ex){
		return -1;
	}
}
function getPosNum(){
    //return "0$000001$888888"; //测试使用
	try{
		var ret = window.parent.document.getElementById("cardpluginid").GetPosBatchNum();
		return ret;
	}catch(Ex){
		return -1;
	}
}

function fPayByUnion(posnum,batchnum,bankcardID,money,servnumber){
    //0$返回应答码$银联交易流水号$银联交易时间YYYYMMDDhhmmss$扣款金额$发票信息
    //交易流水号,BOSS系统流水号,客户名称,缴费金额,收款单位名称;结算月份,合计金额, 汇总信息,发票内容
    //return "-1$就是超时了";
    //return "0$00$00000000000000000001$200907091706$100$hd35020090602095000,31088111898202316,华章明,5000,涉县自办营业厅;2009.04.01---2009.06.01,50.00;计费金额合计：72.54,当前月话费：0.00,最新余额：49.47,积分:0;##基本费用##|||";
    try{
        var ret = window.parent.document.getElementById("cardpluginid").UniPay(posnum,batchnum,bankcardID,money,servnumber);
        return ret;
    }catch(Ex){
        return -1;
    }
}

function getUnionInfo(code){
	if(code=="75"){
		return "PIN次数超限密码错误次数超限";
	}else if(code=="79"){
		return "POS终端重传脱机数据";
	}else if(code=="22"){
		return "操作有误，请重试";
	}else if(code=="38"){
		return "操作员可以没收密码错误次数超限";
	}else if(code=="65"){
		return "超出取款次数限制";
	}else if(code=="15" || code=="31"){
		return "此卡不能受理";
	}else if(code=="36"){
		return "此卡有误，请换卡重试";
	}else if(code=="37"){
		return "此卡有误，请联系收单行";
	}else if(code=="61"){
		return "金额太大";
	}else if(code=="33"){
		return "过期卡,请联系发卡行";
	}else if(code=="68"||code=="98"){
		return "交易超时，请重试";
	}else if(code=="00"){
		return "交易成功";
	}else if(code=="42"){
		return "交易失败，请联系发卡方";
	}else if(code == "01" || code == "02" || code == "05" || code == "06" || code == "19" || code == "20" 
		|| code == "21" || code == "23" || code == "25" || code == "39" || code == "40" || code == "44" 
		|| code == "52" || code == "53" || code == "56" || code == "57" || code == "59" || code == "60" 
		|| code == "62" || code == "63" || code == "64" || code == "93"){
		return "交易失败，请联系发卡行";
	}else if(code=="66"){
		return "交易失败，请联系收单行或银联";
	}else if(code == "90" || code == "91" || code == "92" || code == "94" || code == "95" || code == "96"){
		return "交易失败，请稍后重试";
	}else if(code == "09" || code == "12" || code == "13" || code == "30"){
		return "交易失败，请重试";
	}else if(code=="67"){
		return "没收卡";
	}else if(code == "34" || code == "35" || code == "04" || code == "07" || code == "41" || code == "43"){
		return "没收卡,请联系收单行";
	}else if(code == "54"){
		return "请联系发卡行";
	}else if(code=="77"){
		return "请向网络中心签到";
	}else if(code=="55"){
		return "请重试";
	}else if(code == "99" || code == "0A"){
		return "请重新签到";
	}else if(code=="03"){
		return "商户未登记";
	}else if(code=="14"){
		return "无效卡号，请联系发卡行";
	}else if(code=="51"){
		return "余额不足，请查询";
	}else if(code=="97"){
		return "终端未登记，请联系收单行或银联";
	}else if(code=="58"){
		return "终端无效，请联系收单行或银联";
	}else{
		return "未知错误";
	}
}

