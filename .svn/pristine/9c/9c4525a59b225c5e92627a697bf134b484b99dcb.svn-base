// 展现错误信息
var viewErrDiv;
var viewErr;
function showInitErr(errMsg) {
	if (viewErr != "" && viewErr != undefined) {
		viewErr = viewErr + "<br>" + errMsg;
	} else {
		viewErr = errMsg;
	}
	if (viewErrDiv == null || viewErrDiv == undefined) {
		var sWidth, sHeight;
		sWidth = document.body.offsetWidth;
		sHeight = screen.height;
		viewErrDiv = document.createElement("div");
		viewErrDiv.setAttribute("id", "frmErrDiv");
		viewErrDiv.style.position = "absolute";
		viewErrDiv.style.top = "76%";
		viewErrDiv.style.left = "5%";
		viewErrDiv.style.textAlign = "left";
		viewErrDiv.style.color = "red";
		viewErrDiv.style.font = "bold 20px,\u9ed1\u4f53";
		viewErrDiv.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
		viewErrDiv.style.opacity = "0.6";
		viewErrDiv.style.width = sWidth + "px";
		viewErrDiv.style.height = sHeight + "px";
		viewErrDiv.style.zIndex = "10000";
		document.body.appendChild(viewErrDiv);
		viewErrDiv.innerHTML = viewErr;
	} else {
		viewErrDiv.innerHTML = viewErr;
	}
}

// 票据打印机初始化
function indexInitListPrt() {
     //是否支持打印票据标志,0不支持,1:支持
	if (isPrintFlag == 1) {
		try {
            //初始化票据打印机
			var initListPrt1 = window.parent.document.getElementById("prtpluginid").InitListPrinter();
			if (initListPrt1 == 1) {
    		    showInitErr("警告:票据打印机缺纸或故障!");
				return;
			} else if (initListPrt1 == 41) {
   			    showInitErr("错误:票据打印机设备低层驱动程序未安装!");
				return;
			}
			
        	//设置打印图标的路径 
			var initListPrt2 = window.parent.document.getElementById("prtpluginid").SetPicturePath("");
			if (initListPrt2 == 1) {
    		    showInitErr("警告:票据打印机缺纸或故障!");
				return;
			} else {
				if (initListPrt2 == 41) {
    			    showInitErr("错误:票据打印机设备低层驱动程序未安装!");
					return;
				}
			}
		}catch (e) {
    		showInitErr("发生异常,票据打印机初始化失败,无法打印小票!");
			return;
		}
	}
}

// 发票打印机
function indexInitInvoicePrint() {
	if (isInvoicePrint == 1) {
		try {
		    // 打开发票打印机串口
			var openCom = window.parent.document.getElementById("invprtpluginid").OpenCom();
			if (openCom == 1) {
  	        	showInitErr("警告:发票打印机串口故障!");
				return;
			} else {
				if (openCom == 61) {
	  			    showInitErr("错误:发票打印机故障,无法初始化!");
					return;
				} else if (openCom == 65) {
	  			    showInitErr("警告:发票打印机缺纸!");
					return;
				} else if (openCom != 0) {
	 			    showInitErr("错误:打开设备异常,无法初始化发票打印机!");
					return;
				}
			}
			
			// 初始化发票打印机
			var initInvoicePrt = window.parent.document.getElementById("invprtpluginid").InitVoicePrint();
			if (initInvoicePrt == 61) {
  		    	showInitErr("错误:发票打印机故障,无法初始化!");
				return;
			} else if (initInvoicePrt == 65) {
 			    showInitErr("警告:发票打印机缺纸!");
				return;
			}else if (openCom != 0) {
 			    showInitErr("错误:打开设备异常,无法初始化发票打印机!");
				return;
			}
			
			// 检查发票打印机缺纸
			var v = window.parent.document.getElementById("invprtpluginid").CheckPaper();
			if (v != 0 ){
			    showInitErr("警告:发票打印机缺纸或故障!");
			    return;
			}
			
		}catch (e) {
  			showInitErr("警告:发票打印机初故障,无法打印发票!");
			return;
		}
	}
}
// 现金识币器初始化
function indexOpenCashEquip() {
	if (isCashEquip == 1) {
		try {
			var ret = window.parent.document.getElementById("cashpluginid").OpenCom();
			if (ret != 0) {
			    showInitErr("警告:现金识币器故障!");
			    return;
			}
		}catch (e) {
    	    showInitErr("警告:现金识币器故障,无法进行现金缴费!");
			return;
		}
	}
}
// 加密键盘初始化
function indexInitKeyBoard() {
	if (isKeyBoard == 1) {
    	//初始化加密键盘
		try {
			var ret = window.parent.document.getElementById("keybrdpluginid").OpenCom();
			if (ret != 0) {
  				showInitErr("警告:开启终端键盘串口失败！");
				return;
			}
			ret = window.parent.document.getElementById("keybrdpluginid").SetWorkMode(0);
			if (ret != 0) {
  				showInitErr("警告:设置键盘模式失败！");
				return;
			}
		}catch (e) {
  			showInitErr("警告:密码键盘故障,无法使用密码键盘!");
			return;
		}
	}
}

// 初始化银联读卡器
function indexInitReadUserCard() {
	if(isCardPay == 1){
		try{
			var initCash = window.parent.document.getElementById("cardpluginid").InitReadUserCard();
			if (initCash != 0) {
			    showInitErr("警告:银联读卡器故障！");
			    return;
			}
		}catch(e){
    	    showInitErr("警告:银联读卡器故障,无法进行银联卡缴费!");
    	    return;
		}
	}
}