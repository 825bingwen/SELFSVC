/*
 * Add by xkf57421
 */

/**
 * �����ն˿�������СƱ��ӡ����
 * @param {Object} piData
 * @return {TypeName} 
 */
function _installPrint(piData) 
{
	if (piData != "undefined") 
	{
		var tmpStr = "";
  		//��ӡͷ����Ϣ
		tmpStr = tmpStr + "  " + "\n";
		tmpStr = tmpStr + "  " + "\n";
  	    tmpStr = tmpStr + "  �����ն˿���������ƾ��" + "\n";
		tmpStr = tmpStr + " ---------------------------------------------" + "\n";

    
        //��ӡ����ɷ���Ϣ
  	    tmpStr = tmpStr + "  �ն���Ϣ: " + piData.termId + "\n";
  	    tmpStr = tmpStr + "  Ԥѡ����: " + piData.servnumber + "\n";
  	    tmpStr = tmpStr + "  �����Ʒ: " + piData.mainprodname + "\n";
  	    tmpStr = tmpStr + "  �������: " + piData.receptionFee + " Ԫ\n";
  	    tmpStr = tmpStr + "  Ͷ�ҽ��: " + piData.tMoney + " Ԫ\n";
  	    tmpStr = tmpStr + "  ����ʱ��: " + piData.pDealTime + "\n";
  	    
  	    if(piData.payStatus == 1)
  	    {
	  	    tmpStr = tmpStr + "  ������ˮ: " + piData.dealNum + "\n";
  	    }
  	    
  	    tmpStr = tmpStr + "  ����״̬: " + piData.pDealStatus + "\n";
        
        //��ӡβ����Ϣ
		tmpStr = tmpStr + " ---------------------------------------------" + "\n";
  	    tmpStr = tmpStr + "  ��������,���в���֮��,����ӪҵԱ��ѯ" + "\n";
  	    tmpStr = tmpStr + "  �ͻ���������:10086" + "\n";
  	    tmpStr = tmpStr + " ---------------------------------------------" + "\n";
  	    tmpStr = tmpStr + "  ��ӡ�ص�:" + piData.pOrgName + "\n";
  	    tmpStr = tmpStr + "  ��ӡʱ��:" + piData.pPrintDate + "\n";
  	    
  	    alert(tmpStr);

	} 
	else 
	{
	    alertError("�Բ���,û�пɴ�ӡ������!");
		return;
	}
}
/**
 * �����ն˿�������СƱ��ӡ
 * @param {Object} piData
 * @return {TypeName} 
 */
function installPrint(piData) 
{
	if (piData != "undefined") 
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
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �����ն˿���������ƾ��");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		if (ret == 1) 
		{
        	alertError("����:��ӡ��ȱֽ�����!");
			return;
		}  	
    
        //��ӡ����ɷ���Ϣ
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ն���Ϣ: " + piData.termId);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  Ԥѡ����: " + piData.servnumber);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �����Ʒ: " + piData.mainprodname);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �������: " + piData.receptionFee + " Ԫ");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  Ͷ�ҽ��: " + piData.tMoney + " Ԫ");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ����ʱ��: " + piData.pDealTime);
  	    if(piData.payStatus == 1)
  	    {
	  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ������ˮ: " + piData.dealNum);
  	    }
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ����״̬: " + piData.pDealStatus);
        
        //��ӡβ����Ϣ
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------"); 
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ��������,���в���֮��,����ӪҵԱ��ѯ");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ͻ���������:10086");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ��ӡ�ص�:" + piData.pOrgName);
  	    ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ��ӡʱ��:" + piData.pPrintDate);
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
function cutPaper() 
{
  	var ret = window.parent.document.getElementById("prtpluginid").SetCutPaper();
  	if (ret == 1)
  	{
  		  alertError("����:��ӡ��ȱֽ�����!");
  		  return;
  	}	
  	//alert("��ֽ");
}
      

