// չ�ִ�����Ϣ
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

// Ʊ�ݴ�ӡ����ʼ��
function indexInitListPrt() {
     //�Ƿ�֧�ִ�ӡƱ�ݱ�־,0��֧��,1:֧��
	if (isPrintFlag == 1) {
		try {
            //��ʼ��Ʊ�ݴ�ӡ��
			var initListPrt1 = window.parent.document.getElementById("prtpluginid").InitListPrinter();
			if (initListPrt1 == 1) {
    		    showInitErr("����:Ʊ�ݴ�ӡ��ȱֽ�����!");
				return;
			} else if (initListPrt1 == 41) {
   			    showInitErr("����:Ʊ�ݴ�ӡ���豸�Ͳ���������δ��װ!");
				return;
			}
			
        	//���ô�ӡͼ���·�� 
			var initListPrt2 = window.parent.document.getElementById("prtpluginid").SetPicturePath("");
			if (initListPrt2 == 1) {
    		    showInitErr("����:Ʊ�ݴ�ӡ��ȱֽ�����!");
				return;
			} else {
				if (initListPrt2 == 41) {
    			    showInitErr("����:Ʊ�ݴ�ӡ���豸�Ͳ���������δ��װ!");
					return;
				}
			}
		}catch (e) {
    		showInitErr("�����쳣,Ʊ�ݴ�ӡ����ʼ��ʧ��,�޷���ӡСƱ!");
			return;
		}
	}
}

// ��Ʊ��ӡ��
function indexInitInvoicePrint() {
	if (isInvoicePrint == 1) {
		try {
		    // �򿪷�Ʊ��ӡ������
			var openCom = window.parent.document.getElementById("invprtpluginid").OpenCom();
			if (openCom == 1) {
  	        	showInitErr("����:��Ʊ��ӡ�����ڹ���!");
				return;
			} else {
				if (openCom == 61) {
	  			    showInitErr("����:��Ʊ��ӡ������,�޷���ʼ��!");
					return;
				} else if (openCom == 65) {
	  			    showInitErr("����:��Ʊ��ӡ��ȱֽ!");
					return;
				} else if (openCom != 0) {
	 			    showInitErr("����:���豸�쳣,�޷���ʼ����Ʊ��ӡ��!");
					return;
				}
			}
			
			// ��ʼ����Ʊ��ӡ��
			var initInvoicePrt = window.parent.document.getElementById("invprtpluginid").InitVoicePrint();
			if (initInvoicePrt == 61) {
  		    	showInitErr("����:��Ʊ��ӡ������,�޷���ʼ��!");
				return;
			} else if (initInvoicePrt == 65) {
 			    showInitErr("����:��Ʊ��ӡ��ȱֽ!");
				return;
			}else if (openCom != 0) {
 			    showInitErr("����:���豸�쳣,�޷���ʼ����Ʊ��ӡ��!");
				return;
			}
			
			// ��鷢Ʊ��ӡ��ȱֽ
			var v = window.parent.document.getElementById("invprtpluginid").CheckPaper();
			if (v != 0 ){
			    showInitErr("����:��Ʊ��ӡ��ȱֽ�����!");
			    return;
			}
			
		}catch (e) {
  			showInitErr("����:��Ʊ��ӡ��������,�޷���ӡ��Ʊ!");
			return;
		}
	}
}
// �ֽ�ʶ������ʼ��
function indexOpenCashEquip() {
	if (isCashEquip == 1) {
		try {
			var ret = window.parent.document.getElementById("cashpluginid").OpenCom();
			if (ret != 0) {
			    showInitErr("����:�ֽ�ʶ��������!");
			    return;
			}
		}catch (e) {
    	    showInitErr("����:�ֽ�ʶ��������,�޷������ֽ�ɷ�!");
			return;
		}
	}
}
// ���ܼ��̳�ʼ��
function indexInitKeyBoard() {
	if (isKeyBoard == 1) {
    	//��ʼ�����ܼ���
		try {
			var ret = window.parent.document.getElementById("keybrdpluginid").OpenCom();
			if (ret != 0) {
  				showInitErr("����:�����ն˼��̴���ʧ�ܣ�");
				return;
			}
			ret = window.parent.document.getElementById("keybrdpluginid").SetWorkMode(0);
			if (ret != 0) {
  				showInitErr("����:���ü���ģʽʧ�ܣ�");
				return;
			}
		}catch (e) {
  			showInitErr("����:������̹���,�޷�ʹ���������!");
			return;
		}
	}
}

// ��ʼ������������
function indexInitReadUserCard() {
	if(isCardPay == 1){
		try{
			var initCash = window.parent.document.getElementById("cardpluginid").InitReadUserCard();
			if (initCash != 0) {
			    showInitErr("����:�������������ϣ�");
			    return;
			}
		}catch(e){
    	    showInitErr("����:��������������,�޷������������ɷ�!");
    	    return;
		}
	}
}