
function selfPursePrint(prtData) {
	if (prtData != "undefined" ) {
		        //��ӡͼ��
		var Ret3 = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
		if (Ret3 == 1) {
			pubErrShow("����:��ӡ��ȱֽ�����!");
			return;
		} else {
			if (Ret3 == 41) {
				pubErrShow("����:��ӡ���豸�Ͳ���������δ��װ!");
				return;
			}
		}
		var Ret4;
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" �̻����                        MERCHANT COPY");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" �̻�����(MERCHANT NAME): " + prtData.businessName);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" �̻����(MERCHANT NO.): " + prtData.businessID);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" �ն˱��(TERMINAL ID): " + prtData.posTermID);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ����Ա��(OPERATOR NO.): " + prtData.operID);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ��    ��(CARD NO.): " + prtData.recCardNO);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ����������(ISS NO.): " + prtData.sendCardID);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ���������(ACQ NO.): " + "");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ��������(TRANS TYPE): " + "����(SALE)");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" �� Ч ��(EXP): " + "");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" �� �� ��(BATCH NO.): " + prtData.batchNum);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ƾ ֤ ��(VOUCHER NO.): " + prtData.posSeqID);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" �� ˮ ��(TRACE NO.): " + prtData.posOid);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" �� �� ��(REF.NO.): " + "");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ����/ʱ��(DATE/TIME): " + prtData.recdate);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ���׽��(AMOUNT): " + prtData.money);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ��ע/REFERENCE");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" Ӧ�ñ�ʶAID: " + prtData.appAid);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ��������ʶ: " + prtData.sendCardID);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" �ѻ��������: " + prtData.offRecNum);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" �ܳװ汾��:" + prtData.keyEdition);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" �ܳ�������: " + prtData.keySeqNum);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" �ն˻����: " + prtData.termCode);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" �ն˽������: " + prtData.termRecSeq);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" TAC: " + prtData.tacNum);
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" Ϊ�����ĺϷ�Ȩ��,�����Ʊ�����ƾ��");
		//modify begin g00140516 2011/10/26 R003C11L10n01 ȥ��10086��ʾ��Ϣ
		Ret4 = window.parent.document.getElementById("prtpluginid").PrintLine(" ������Ϣ���ѯwww.cmpay.com");
  	    //modify end g00140516 2011/10/26 R003C11L10n01 ȥ��10086��ʾ��Ϣ
	} else {
	    pubErrShow("�Բ���,û�пɴ�ӡ������!");
		return;
	}
	var Ret5 = window.parent.document.getElementById("prtpluginid").SetCutPaper();
	if (Ret5 == 1) {
	    pubErrShow("����:��ӡ��ȱֽ�����!");
		return;
	} else {
		if (Ret5 == 41) {
	        pubErrShow("����:��ӡ���豸�Ͳ���������δ��װ!");
			return;
		}
	}
	document.getElementById('recInfo').innerHTML = "��ӡ�Ѿ���ɣ�лл����ʹ�ã�";
    pubErrShow("��ӡ�Ѿ����!");
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

