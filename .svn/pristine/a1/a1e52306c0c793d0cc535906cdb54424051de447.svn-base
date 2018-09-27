//二代身份证接口，具体接口需厂商提供

//打开串口
function fOpenCom(){
	//测试用
	//return 0;
	try{
		var stat = window.parent.document.getElementById("idcardpluginid").OpenCom();
		return stat;
	}catch(e){
	    return -99;
	}
	
}

//初始化二代身份证读卡器并检查状态
//0返回正常
function fInitCardReader(){
	//测试用
	//return 0;
	try{
		var init = window.parent.document.getElementById("idcardpluginid").InitCardReader();
		return init;
	}catch(e){
	    try{
	        window.parent.document.getElementById("idcardpluginid").OpenCom();
	        var init = window.parent.document.getElementById("idcardpluginid").InitCardReader();
		return init;
	    }catch(e){
	        return -99;
	    }
		return -99;
	}
}
//获取读卡器状态
function fCheckIDCardReader(){
	//测试用
	//return 0;
	try{
		var stat = window.parent.document.getElementById("idcardpluginid").GetIdCardStatus();
		return stat;
	}catch(e){
	    return -99;
	}
	
}
//功能：获取身份证文字内容
function fGetIdCardContent(){
	//测试用
	//return "0李锋~男~汉~19850416~武汉市汉阳区新江大号8号~421127198504160456~20040409~20140409~";
	try{
		var content = window.parent.document.getElementById("idcardpluginid").GetIdCardContent();
		return content;
	}catch(e){
		return -99;
	}
	
	
}
//功能：关闭身份证读卡器
function fCloseCardReader(){
	//测试用
	//return 0;
	try{
		var rNo = window.parent.document.getElementById("idcardpluginid").CloseCardReader();
		return rNo;
	}catch(e){
		return -99
	}
	
}