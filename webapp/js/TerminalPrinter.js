var _isPrint_ = 0;

var sameParams = "";

var oUrl = "";

var orgName = "";

var printDate = "";
/**
 * �ն˴�ӡ����
 *
 */
function doPrint(pPrintFlag,pListType,pServNumber,pMonth,pOrgName,pPrintDate,contextPath) {
	if(_isPrint_ == 1) {
		setProcessInfo("�Ѵ�ӡ����ȴ�ӡ��ϣ������ٲ�ѯ��ӡ��");
		return;
	}
	
	//pPrintFlag = 1;
	if(pPrintFlag!=1) {
		return;
	}
    
  orgName = pOrgName;
  printDate = pPrintDate;
  var typeName = ["ȫ���嵥","ͨ���嵥","�����嵥","�����嵥","�����嵥","GPRS�嵥","WLAN�嵥","������Ϣ���嵥","VPMN�嵥"];
  
  var listType = pListType;   //�嵥�������ֱ�־0��ʾȫ��
  var isPrintAll = null;
  if(listType=="0") 
  { //��ѯȫ��
  	isPrintAll = "YES";
  } else {
  	isPrintAll = "NO";
  }
   
  try {
  	//��ӡͼ��
  	var Ret3 = DPsListPrinter1.PrintPicture(1);
  	if (Ret3 == 1)
  	{
  	   alertError("����:��ӡ��ȱֽ�����!");
  	   return;
  	}
  	else if (Ret3 == 41)
  	{
  	   alertError("����:��ӡ���豸�Ͳ���������δ��װ!");
  	   return;
  	} 
  	     
  	//��ӡͷ����Ϣ
  	var Ret4 = DPsListPrinter1.PrintLine("  ");
  	Ret4 = DPsListPrinter1.PrintLine("  ");
  	Ret4 = DPsListPrinter1.PrintLine("  �嵥����: "+typeName[listType]+"  ");
  	Ret4 = DPsListPrinter1.PrintLine("  �ֻ�����: "+pServNumber);
  	Ret4 = DPsListPrinter1.PrintLine("  �嵥�·�: "+pMonth);
  	Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------");  	    
    if (Ret4 == 1)
    {
        alertError("����:��ӡ��ȱֽ�����!");
        return;
    }
  	
  	//���ô�ӡ״̬Ϊ1,��ʾ�Ѿ���ӡ��һ�Ρ�
  	_isPrint_ = 1;
  	
  	     
  	//��һ���ύ��ӡ����ӡ�嵥�ĵ�һҳ        
  	var page = 1;
		oUrl = contextPath  + "/BusinessActon.do";
		sameParams = "?actionCase=printList&month=" + pMonth + "&isPrintAll=" + isPrintAll;
		var params = sameParams + "&listtype=" + listType + "&page="+page;		  		  
		setProcessInfo("���ڴ�ӡ����ȴ�...");
		hiddenFrameSubmit(oUrl+params);

	} catch(ex) {
	 	alertError("��ʼ��ӡ�嵥���ݳ���"+ex);
	 	cutPaper();//����������ֽ
	}	
}


/**
* �ύ������ȡ��ӡ����
*/
function hiddenFrameSubmit(pUrl) {
		try
		{			
			var oFrame = document.getElementById("processFrm");
			if(oFrame) {		
				oFrame.src = pUrl;				
			}								      
		} catch (err)
		{ 
			alertError("��ӡ������,�����ӡ���ݳ���:" + err); 
			cutPaper();				
		}
}	//end of hiddenFrameSubmitf

//��ֽ      
function cutPaper() {
  	var ret5 = DPsListPrinter1.SetCutPaper();
  	if (ret5 == 1)
  	{
  		  alertError("����:��ӡ��ȱֽ�����!");
  		  return;
  	}	
  	//alert("��ֽ");
}
      
//��Ӧ��ӡ
function netload(printDataObject) {
	//���û��ȡ����ӡ����,����ֽ������ӡ
	if( typeof(printDataObject)=="undefined"||printDataObject==null ) {
		alertError("ȡ��ӡ���ݴ���...");
		cutPaper();
		return;
	}
	
	try {
			var ret = null;
			//ȡ����������ִ�����ֹͣ��ӡ
			var errmsg = getValue("errmsg");	
			if(errmsg!="") {
				alertError("��ӡ��������,������Ϣ��"+errmsg);				
				cutPaper();
				return;
			}
			
			//ȡ��ҳ��
			var pageCount = getValue("pageCount",0);								
			//ȡ��һҳ�룬��ӡ��һҳʱ�򣬷��ص���2
			var page = getValue("page",0);			
			//ȡ��ǰ���ڴ�ӡ���嵥						
			var currentListType = getValue("currentListType",0);				
			//ȡ�Ƿ������ӡ��־��
			var printTail = getValue("printTail");
			//ȡ���Ƿ��ӡ�嵥������Ϣ��־
			var printTypeName = getValue("printTypeName");			  		  		
			//ȡ���Ƿ��ӡ�嵥������Ϣ��־
			var processInfo = getValue("process");
				
			//alert("page:"+page+" pageCount:"+pageCount + " currentListType:" + currentListType);
					
			//����Ǵ�ӡȫ�������Ҵ�ӡ��һҳ�Ҵ������ݵ�ʱ���ȴ�ӡ�嵥��						
			if(printTypeName!="") {
				  //alert(printTypeName);
				ret = DPsListPrinter1.PrintLine("  " +printTypeName)
			}
 		
			//��ʼ��ӡ�嵥����
			if(pageCount > 0) {		
				//���ش�ӡ��ʾ
 				setProcessInfo(processInfo);
  			
  			//ȡ�嵥����		
				var entries = getValue("records",[]);	
				//alert("entries.length:"+entries.length);	
				for(var i = 0;i<entries.length;i++) {
					ret = DPsListPrinter1.PrintLine(entries[i]); 				
				}
  		  if (ret == 1)
  		  {
  		      alertError("����:��ӡ��ȱֽ�����!");
  		      return;
  		  }
  		}
  		              
			              
			//�����ӡβ����־ΪYES�����ӡβ������ֽ��              
			if(printTail=="YES") {	              
 				 setProcessInfo("������ӡ..."); 				 	 		 			  
 				 //��ӡ˵��
  		   var ret5 = DPsListPrinter1.PrintLine(" ---------------------------------------------"); 
  		   ret5 = DPsListPrinter1.PrintLine(" ˵��:ʱ��Ϊ����ͨ��ʱ��(��),���õ�λΪԪ,������λΪK."); 
  		   ret5 = DPsListPrinter1.PrintLine(" ����:���嵥��Ϣ�����ͻ��˶�֮��,�����κ�ƾ��.");
  		   ret5 = DPsListPrinter1.PrintLine(" ��ʾ:Ϊ�����������벻�����˶�������,�뾭���޸�.");
  		   ret5 = DPsListPrinter1.PrintLine(" ��������,���в���֮��,����ӪҵԱ��ѯ.");
  		   //delete begin g00140516 2011/10/26 R003C11L10n01 ȥ��10086��ʾ��Ϣ
  	       //delete end g00140516 2011/10/26 R003C11L10n01 ȥ��10086��ʾ��Ϣ
  		   ret5 = DPsListPrinter1.PrintLine(" ---------------------------------------------");
  		   ret5 = DPsListPrinter1.PrintLine(" ��ӡ�ص�:"+orgName+".");
  		   ret5 = DPsListPrinter1.PrintLine(" ��ӡʱ��:"+printDate+".");
  		   if (ret5 == 1)
  		   {
  		       alertError("����:��ӡ��ȱֽ�����!");
  		       return;
  		   }
  		   //��ֽ
  		   cutPaper();
  		  
			}	else { //��������ύ��ӡ����
				params = sameParams;
				params += ("&listtype="+currentListType+"&page="+page);	
			  hiddenFrameSubmit(oUrl+params);
			}
	} catch(ex) {
			alertError("��ӡ�嵥���ݳ���:" + ex);
			cutPaper(); //������������ֽ			
	}
	
	//�Ӵ�ӡ�����л�ȡ��ӡ����
	function getValue(keyV,defaultV) {
		if(printDataObject[keyV]) {				
			return printDataObject[keyV];
		}
		return (typeof(defaultV)!="undefined") ? defaultV : "";		
	}		
	
} //end of netload	
      
//���ô�ӡ״̬    
function setProcessInfo(info) {
	  var oProcessDiv = document.getElementById("process");				
		//���ش�ӡ��ʾ
	  if(info!="" && oProcessDiv) {  	
  		oProcessDiv.style.display = "";
	  	oProcessDiv.innerHTML = "<font color=red>" + info + "</font>";
	  }
}


