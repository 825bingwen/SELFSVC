
function fVending(boothCode) {
    //����
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
    if(code == "-7"){return "�޷���λ��������ԭ��";}
    else if(code == "-8"){return "ִ��ָ��ָ���ʧ��";}
    else if(code == "-9"){return "���������Ϲ淶";}
    else if(code == "-10"){return "�豸���ڶ�дʧ��";}
    else if(code == "-11"){return "�����쳣";}
    else if(code == "-12"){return "У�����";}
}
