/**
 * ������ҵ�ɷ�ƾ����ӡ
 * paymentNum --������ҵ�ɷѺ���
 * pOrgName    --��ӡ�ص�
 * printTime  --��ӡ����
 * termid    --�ն���Ϣ
 * touchoid     --������ˮ��
 * dealTime    --����ʱ��
 * moneyNum     --���׽��
 * dealStatus --����״̬
 * feeType      --�ɷѵ�λ����
 * serverNum      --�ɷ�����Ϣ
 */
function doPrintPayProof(prtData) {
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
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    �����ɷѻ�����ƾ��");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("   ---------------------------------------------");  	    
    if (ret == 1)
    {
        pubErrShow("����:��ӡ��ȱֽ�����!");
        return;
    }  	
    
    //��ӡ����ɷ���Ϣ
    ret = window.parent.document.getElementById("prtpluginid").PrintLine("    ������ҵ�ɷѺ���: "+prtData.paymentNum);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    �ն˱��        : "+prtData.termid );
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    ������ˮ��      : "+prtData.touchoid);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    ����ʱ��        : "+prtData.dealTime);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    ���׽��        : "+prtData.moneyNum);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    �ɷѵ�λ����    : "+prtData.feeType);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    �ɷѵ�λ����    : "+prtData.unitName);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    �ɷ�����Ϣ      : "+prtData.serverNum);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    ����״̬        : "+prtData.dealStatus);
    if (ret == 1)
    {
        pubErrShow("����:��ӡ��ȱֽ�����!");
        return;
    }     
    
    
    //��ӡβ����Ϣ
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("   ---------------------------------------------"); 
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    ��������,���в���֮��,����ӪҵԱ��ѯ.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    �ͻ���������:10086.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("   ---------------------------------------------");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    ��ӡ�ص�:"+prtData.pOrgName+".");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("    ��ӡʱ��:"+prtData.dealTime+".");
  	if (ret == 1)
  	{
  	    pubErrShow("����:��ӡ��ȱֽ�����!");
  	    return;
  	}    
			
		//������ӡ����,��ֽ
	 	cutPaper();
	} catch(ex) {
	 		setProcessInfo("��ӡ�ɷ�ƾ������,����ϵӪҵ������Ա�鿴�ɷ��Ƿ�ɹ���");
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
