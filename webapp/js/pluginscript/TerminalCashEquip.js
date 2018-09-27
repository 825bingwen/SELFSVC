
/**********�ն��ֽ�ʶ�����ؼ�PsCashCode.ocx**********/
/**
 *�������ƣ�initCashEquip(servNumber)
 *���ýӿڣ�short InitCashBill(servNumber)
 *�������ܣ���ʼ��OMRONʶ����
 *��������
 *���أ�0 ��ʾ�����ɹ������� ������ֵ˵����
 */
function initCashEquip(servNumber) {
	//return '0,20110509143345';//����ʹ��
	try {
		var cashFlag = window.parent.document.getElementById("cashpluginid").InitCashBill(servNumber);
		return cashFlag;
	}
	catch (e) {
        //�����ʼ��ʧ������
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
	//return '0,20110509143345';//����ʹ��
	try {
		var cashFlag = window.parent.document.getElementById("cashpluginid").InitCashBill(servNumber);
		if (cashFlag.substring(0, 2) == "51")
		{
			//�����ʼ��ʧ������
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
        //�����ʼ��ʧ������
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
 *�������ƣ�getOnceCash()
 *���ýӿڣ�short GetCashBill (short nTimOut)
 *�������ܣ���ȡͶ�ҽ��
 *��������
 *���أ�0 ��ʾû��Ͷ�ң����� ΪͶ����ֵ(���ܵ�ֵΪ��1,2,5,10,20,50,100)��
 */
function getOnceCash() {
	//return 5;//������
	var onceMoney = window.parent.document.getElementById("cashpluginid").GetCashBill(1);
	return onceMoney;
}
function cancelGetCash() {
	window.parent.document.getElementById("cashpluginid").CancelAccept();
}

//����ʶ������־��������
function setLogAddInfo(content) {
	//return 0;//������
	var result = window.parent.document.getElementById("cashpluginid").SetAddInfor(content);
	return result;
}
/**
 *�������ƣ�checkCashStatus()
 *���ýӿڣ�short CheckCashState()
 *�������ܣ����ʶ����״̬
 *��������
 *���أ�״̬����:0-���� 1-�쳣 2-Ǯ���� 3-Ǯ��� 4-��ڱ��� 5-Ǯ�䱻�� 6-�������� 9-�޴��豸��
 */
function checkCashStatus() {
	//return 0;//������
	var cashStatus = window.parent.document.getElementById("cashpluginid").CheckCashState();
	return cashStatus;
}

function fCloseCashBill() {
    //return 0;//������
	try {
		var v = window.parent.document.getElementById("cashpluginid").CloseCashBill();
		return v;
	}
	catch (e) {
		return -99;
	}
}

