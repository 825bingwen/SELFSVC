
/**
 * �Żݾ��ӡ
 */
function fAbateLipPrint(prtData) {
	//return true; //����ʹ��
  	try {
	  	//��ӡ�ƶ�ͼ�� 	
	  	var ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
	  	if (ret == 1)
	  	{
	  	   pubErrShow("����:��ӡ��ȱֽ�����!");
	  	   return;
	  	}
	  	else if (ret == 41)
	  	{
	  	   pubErrShow("����:��ӡ���豸�Ͳ���������δ��װ!");
	  	   return;
	  	}
	  	
	  	//��ӡͷ����Ϣ
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
	  	for(var i = 0;i<prtData.prtNum;i++){
	  	    ret = window.parent.document.getElementById("prtpluginid").PrintThePicture(prtData.localImgURL);
	  	}
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ��������,���в���֮��,����ӪҵԱ��ѯ.");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ��ӡ�ص�: " + prtData.pOrgName+".");
	  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ��ӡʱ��: " + prtData.pPrintDate+".");
	  	if (ret == 1)
	  	{
	  	    pubErrShow("����:��ӡ��ȱֽ�����!");
	  	    return;
	  	}    
			
		//������ӡ����,��ֽ
	 	cutPaper();
	} catch(ex) {
	 		pubErrShow("��ӡ�ɷ�ƾ������,����ϵӪҵ������Ա�鿴�ɷ��Ƿ�ɹ���");
	 		cutPaper();//����������ֽ
	}	
}

//��ֽ      
function cutPaper() {
  	var ret = window.parent.document.getElementById("prtpluginid").SetCutPaper();
  	if (ret == 1)
  	{
  		  pubErrShow("����:��ӡ��ȱֽ�����!");
  		  return;
  	}	
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
