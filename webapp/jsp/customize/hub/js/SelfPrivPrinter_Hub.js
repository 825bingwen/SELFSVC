/*
 * Add by Lifeng
 */

/**
 * �Ż�����СƱ��ӡ����
 * @param {Object} prtData
 * @return {TypeName} 
 */
function _recNotePrint(prtData) 
{
	if (prtData != "undefined") 
	{
		var tmpStr = "";
  		//��ӡͷ����Ϣ
		tmpStr = tmpStr + "  " + "\n";
		tmpStr = tmpStr + "  " + "\n";
  	    tmpStr = tmpStr + "  �ֻ�����: " + prtData.servnumber + "\n";
  	    //tmpStr = tmpStr + "  �� �� ��: " + prtData.username + "\n";
  	    tmpStr = tmpStr + "  �������Ż�������ƾ��" + "\n";
		tmpStr = tmpStr + " ---------------------------------------------" + "\n";

    
        //��ӡ����ɷ���Ϣ
  	    tmpStr = tmpStr + "  �ն���Ϣ: " + prtData.termID + "\n";
  	    tmpStr = tmpStr + "  �Ż�����: " + prtData.groupname + "\n";
  	    tmpStr = tmpStr + "  �Ӧ��: " + prtData.prepayfee + " Ԫ\n";
  	    tmpStr = tmpStr + "  Ͷ�ҽ��: " + prtData.tMoney + " Ԫ\n";
  	    if(prtData.recDate != "")
  	    {
  	    	tmpStr = tmpStr + "  ����ʱ��: " + prtData.recDate + "\n";
  	    }
  	    if(prtData.payStatus == 4 || prtData.payStatus == 6)
  	    {
  	        if(prtData.payStatus == 4){
	  	    tmpStr = tmpStr + "  �ɷѽ��: " + prtData.tmpMoney + " Ԫ\n";
	  	    }
	  	    if(prtData.payStatus == 6){
	  	    tmpStr = tmpStr + "  �ɷѽ��: " + prtData.tMoney + " Ԫ\n";
	  	    }
	  	    tmpStr = tmpStr + "  �ɷ���ˮ: " + prtData.dealNum + "\n";
	  	    tmpStr = tmpStr + "  �ɷ�ʱ��: " + prtData.dealTime + "\n";
  	    }
  	    if(prtData.payStatus == 1 || prtData.payStatus == 5)
  	    {
  	        tmpStr = tmpStr + "  �˻����: " + prtData.tMoney + " Ԫ\n";
  	    }
  	    if(prtData.payStatus == 3)
  	    {
  	        tmpStr = tmpStr + "  �˻����: " + prtData.tmpMoney + " Ԫ\n";
  	    }
  	    tmpStr = tmpStr + "  ����״̬: " + prtData.transResult + "\n";
        //��ӡβ����Ϣ
		tmpStr = tmpStr + " ---------------------------------------------" + "\n";
  	    tmpStr = tmpStr + "  ��������,���в���֮��,����ӪҵԱ��ѯ" + "\n";
  	    tmpStr = tmpStr + "  �ͻ���������:10086" + "\n";
  	    tmpStr = tmpStr + " ---------------------------------------------" + "\n";
  	    tmpStr = tmpStr + "  ��ӡ�ص�:" + prtData.pOrgName + "\n";
  	    tmpStr = tmpStr + "  ��ӡʱ��:" + prtData.pPrintDate + "\n";
  	    
  	    alert(tmpStr);

	} 
	else 
	{
	    alertError("�Բ���,û�пɴ�ӡ������!");
		return;
	}
}
/**
 * �Ż�����СƱ��ӡ
 * @param {Object} prtData
 * @return {TypeName} 
 */
function recNotePrint(prtData) 
{
	if (prtData != "undefined") 
	{
		var ret = window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
		if ( ret == 1)
		{
		    alertError("�Բ���,Ʊ�ݴ�ӡ����������,����ϵӪҵԱ!");
		    return;
		}
		else if (ret == 2) 
		{
		    alertError("�Բ���,Ʊ�ݴ�ӡ��ȱֽ,����ϵӪҵԱ!");
		    return;
		}
		else if (ret != 0)
		{
		    alertError("�Բ���,Ʊ�ݴ�ӡ����������:" + ret + ",����ϵӪҵԱ!");
		    return;
		}
		
  	    //��ӡ�ƶ�ͼ��  	
		ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
		if (ret == 1) 
		{
  	   		alertError("����:��ӡ��ȱֽ�����!");
			return;
		} 
		else 
		{
			if (ret == 41) 
			{
  	   			alertError("����:��ӡ���豸�Ͳ���������δ��װ!");
				return;
			}
		}
  	     
  		//��ӡͷ����Ϣ
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ֻ�����: " + prtData.servnumber);
  	    //ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �� �� ��: " + prtData.username);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �������Ż�������ƾ��");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		if (ret == 1) 
		{
        	alertError("����:��ӡ��ȱֽ�����!");
			return;
		}  	
    
        //��ӡ����ɷ���Ϣ
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ն���Ϣ: " + prtData.termID);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �Ż�����: " + prtData.groupname);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �Ӧ��: " + prtData.prepayfee + " Ԫ");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  Ͷ�ҽ��: " + prtData.tMoney + " Ԫ");
  	    if(prtData.recDate != "")
  	    {
  	        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ����ʱ��: " + prtData.recDate);
  	    }
  	    if(prtData.payStatus == 4 || prtData.payStatus == 6)
  	    {
  	        if(prtData.payStatus == 4)
  	        {
	  	    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ɷѽ��: " + prtData.tmpMoney + " Ԫ");
	  	    }
	  	    if(prtData.payStatus == 6)
  	        {
	  	    	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ɷѽ��: " + prtData.tMoney + " Ԫ");
	  	    }
	  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ɷ���ˮ: " + prtData.dealNum);
	  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ɷ�ʱ��: " + prtData.dealTime);
  	    }
  	    if(prtData.payStatus == 1 || prtData.payStatus == 5)
  	    {
  	        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �˻����: " + prtData.tMoney + " Ԫ");
  	    }
  	    if(prtData.payStatus == 3)
  	    {
  	        ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �˻����: " + prtData.tmpMoney + " Ԫ");
  	    }
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ����״̬: " + prtData.transResult);
        //��ӡβ����Ϣ
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ��������,���в���֮��,����ӪҵԱ��ѯ");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ͻ���������:10086");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ��ӡ�ص�:" + prtData.pOrgName);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ��ӡʱ��:" + prtData.pPrintDate);
		if (ret == 1) 
		{
  	    	alertError("����:��ӡ��ȱֽ�����!");
			return;
		}    
			
		//������ӡ����,��ֽ
		cutPaper();
	} 
	else 
	{
	    alertError("�Բ���,û�пɴ�ӡ������!");
		return;
	}
}


//��ֽ      
function cutPaper() {
  	var ret = window.parent.document.getElementById("prtpluginid").SetCutPaper();
  	if (ret == 1)
  	{
  		  alertError("����:��ӡ��ȱֽ�����!");
  		  return;
  	}	
  	//alert("��ֽ");
}
      

