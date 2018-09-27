//������������Ϣ�ָ���������ͳһ����
var splitChar = "&";

// �ؼ��������������
var resultCodeIndex = 0;

// ����������λ������
var errCodeIndex = 1;

// �ؼ����������Ϣ����
var	resultInfoIndex = 1;

// �ؼ���������룬��ȷ��
var successCode = "0";

// ������ʹ�����Ϣ
var errCode = "";
var errMsg = "";
var writeCardObject = window.parent.document.getElementById('writecardpluginid');

//ajax���÷��ؽ��
var res;

/**
 * �հ׿�д��
 * ��Σ� cardInfoStr �հ׿���Ϣ�ַ�����iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2��
 * 	     SIMSerialCommon �հ׿���
 *       basePath ��Ŀ·����js�ﲻ�ܽ���s��ǩ
 *	     telnum �ֻ���  ��ȡ����������Ҫ
 * ���ؽ���� 0: �ɹ� 1 : ʧ��  1~ʧ������
 */
function writeCard(cardInfoStr,SIMSerialCommon,basePath,telnum)
{	
	//return "0";//������
	//return "1~д�����ݱ��Ĵ���";
	try
	{		
		var cardformnum = new Date().getTime();
		
		// Ԥ�ÿտ�д�����ݼ��ܱ���
		var preSetBlankCardData = cardInfoStr.split("~~")[2];
		
		// ��ȡ�հ׿����к�
		var getBlankCardSNResult = writeCardObject.OPS_GetCardSN(); // var getBlankCardSNResult = "0&12345678901234567890";// ������
		
		// �жϿհ׿����к��Ƿ���ȷ
		var blankCardSN = analyzeReadCardInfo(getBlankCardSNResult);
	    if(blankCardSN.indexOf("1~") != -1)
		{
			return blankCardSN;
		}
				
		if(blankCardSN != SIMSerialCommon)
		{
			return "1~�հ׿����кŲ�һ��,����ϵӪҵ������Ա��";
		}
		
		// д��
		var writeCardResult = writeCardObject.OPS_WriteCard(preSetBlankCardData);// var writeCardResult = "0&ABCDEFG";// ������
		var writeCardInfo = analyzeReadCardInfo(writeCardResult);
		
		// �����Ƿ���Ҫ�˿�
		document.getElementById("callBackCard").value = "1";
		
		if(writeCardInfo.indexOf("1~") != -1)
		{
		    var errorCode = writeCardResult.substring(2);
		    
		    // ���ÿհ׿����ϴ����߼����������������Ϊ1�������Ͽհ׿�
		    if (errorCode == "1" || errorCode == 1)
		    {
				// д��ʧ�ܣ��첽�������Ͽ��ӿ�
				asynUpdateWriteCardResult(SIMSerialCommon,cardInfoStr,basePath,"");
		    }
			
			// �����Ƶ�������
			var callbackcard = callBackCard();
			if(-1 == callbackcard)
			{
				return "1~��������������ʧ�ܣ�";
			}
			return "1"+writeCardInfo;
		}
        return writeCardInfo;
	}
	catch(b)
	{
		return "1~��ȡд���ؼ��쳣��";
	}
	
}
   
// �������� �����������ؼ�������Ϣ
function analyzeReadCardInfo(readCardResult)
{	
	try
	{
		var readCardResultArr = readCardResult.split(splitChar);
		if(!readCardResultArr || readCardResultArr.length != 2)
		{
		    return "1~��ȡ��Ƭ��Ϣ����";	
		}
		
		if(readCardResultArr[resultCodeIndex] != successCode)
		{
		    // ���ô�����ʹ�����Ϣ
		    errCode = readCardResultArr[errCodeIndex];
		    errMsg = OPSGetErrorMsg(errCode);
		    
			// ��ʾ�������Ӧ�Ĵ�����Ϣ
			return "1~"+errMsg;	
		}
        
		return readCardResultArr[resultInfoIndex];
	}
	catch(e)
	{
		return "1~���������ؼ�������Ϣ�쳣��";
	}
}

// ���ݴ������ȡ������Ϣ
function OPSGetErrorMsg(errorCode)
{
	try
	{	
		var errInfo = writeCardObject.OPS_GetErrorMsg(errorCode);//var errInfo = "д��ʧ����|0000";// ������
	    return errInfo;
	}
	catch(e)
	{
		return "���ݴ������ȡ������Ϣ�쳣��";
	}			
}

// ��ȡ�հ׿����к�
function readBlankCardSN()
{
	//return "20150106142000000008";// ������
	try
	{
	    stat = window.parent.document.getElementById("simcardpluginid").MoveCardToWrite();
        
        if (stat == -1)
        {
            return "���հ׿��ߵ�����λ��ʧ�ܣ�";
        }
        
		// ��ȡ�հ׿����к�
		var getBlankCardSNResult = writeCardObject.OPS_GetCardSN(); 
		
		// �жϿհ׿����к��Ƿ���ȷ
		return analyzeReadCardInfo(getBlankCardSNResult);
	}
	catch(e)
	{
		return "1~��ȡ�հ׿����к�ʧ�ܣ�";
	}
}

// �첽����д��ʧ�����Ͽ���Ϣ�ӿ�
function asynUpdateWriteCardResult(blankno,cardInfoStr,basePath,writeResult)
{
	// URL
	var url = basePath + "cardInstall/asynUpdateWriteCardResult.action";
	
	// ����
	var params = "blankno=" + blankno;
	    params = params + "&cardInfoStr=" + cardInfoStr ;
	    params = params + "&errCode=" + errCode ;
	    params = params + "&errMsg=" + errMsg ;
	    params = params + "&writeResult=" + writeResult ;
	
	// ����
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			res = this.req.responseText;
	}, null, "POST", params, null);
}

/**
 * �����ն˿�������СƱ��ӡ
 * @param {Object} piData
 * @return {TypeName} 
 */
function printInstallTicket(piData,printcontext) 
{
	try
	{
		var prtpluginidEle = window.parent.document.getElementById("prtpluginid");
        
        var ret;
	    
		if (piData != "undefined") 
		{
		    try{
	            ret = prtpluginidEle.PrintPicture(1);
	        }
	        catch(e)
	        {
	            alertError("����:��ӡ���ؼ�δ��װ!");
	            return;
	        }
	        if (ret == 1)
	        {
	           alertError("����:��ӡ��ȱֽ�����!");
	           return;
	        }
	        else if (ret == 41)
	        {
	           alertError("����:��ӡ���豸�Ͳ���������δ��װ!");
	           return;
	        }   
	  	     
	  		//��ӡͷ����Ϣ
			ret = prtpluginidEle.PrintLine("  ");
			ret = prtpluginidEle.PrintLine("  ");
	  	    ret = prtpluginidEle.PrintLine("  �����ն˿հ׿�����������ƾ��");
			ret = prtpluginidEle.PrintLine(" ---------------------------------------------");
			if (ret == 1) 
			{
	        	alertError("����:��ӡ��ȱֽ�����!");
				return;
			}  	
	    
	        //��ӡ����ɷ���Ϣ
	  	    ret = prtpluginidEle.PrintLine("  �ն���Ϣ: " + piData.termId);
	  	    ret = prtpluginidEle.PrintLine("  �ֻ�����: " + piData.servnumber);
	  	    ret = prtpluginidEle.PrintLine("  �����Ʒ: " + piData.mainprodname);
	  	    ret = prtpluginidEle.PrintLine("  �ײ͵���: " + piData.tpltname);
	  	    ret = prtpluginidEle.PrintLine("  �������: " + piData.recFee + " Ԫ");
	  	    ret = prtpluginidEle.PrintLine("  Ͷ�ҽ��: " + piData.tMoney + " Ԫ");
	  	    ret = prtpluginidEle.PrintLine("  ����ʱ��: " + piData.pDealTime);
	  	    ret = prtpluginidEle.PrintLine("  �ɷ���ˮ: " + piData.chargeId);
	  	    if(piData.installStatus == 0)
	  	    {
	  	    	ret = prtpluginidEle.PrintLine("  ������ˮ: " + piData.formnum);
	  	    }
	  	    ret = prtpluginidEle.PrintLine("  ����״̬: " + piData.pDealStatus);
	        
	        if (printcontext != "")
		    {
		        ret = prtpluginidEle.PrintLine("  ");
		        ret = prtpluginidEle.PrintLine("  ");
		        ret = prtpluginidEle.PrintLine("  ����ˢ������ƾ��");
		        ret = prtpluginidEle.PrintLine("  ֧����ˮ: " + piData.pTerminalSeq);
		        ret = prtpluginidEle.PrintLine(" ---------------------------------------------");         
		        if (ret == 1)
		        {
		            alertError("����:��ӡ��ȱֽ�����!");
		            return;
		        }
		        var cardNumber = printcontext.substring(18, 37);
		        while (cardNumber.charAt(cardNumber.length - 1) == " ") 
		        {
		            cardNumber = cardNumber.substring(0, cardNumber.length - 1);
		        }
		        while (cardNumber.charAt(0) == " ") 
		        {
		            cardNumber = cardNumber.substring(1, cardNumber.length);
		        }
		        var hiddenCardNumber = cardNumber;
		        if (cardNumber.length > 10)
		        {
		            hiddenCardNumber = cardNumber.substring(0, 6) + "******" + cardNumber.substring(cardNumber.length - 4, cardNumber.length);
		        }
		        var unionPayFee = printcontext.substring(85, 97);
		        var unionPayFeeFront = unionPayFee.substring(0, unionPayFee.length-3);
		        while (unionPayFeeFront.charAt(0) == "0") 
		        {
		            unionPayFeeFront = unionPayFeeFront.substring(1, unionPayFeeFront.length);
		        }
		        
		        unionPayFee = unionPayFeeFront + unionPayFee.substring(unionPayFee.length-3, unionPayFee.length);
		        unionPayFee = unionPayFee.substring(0, unionPayFee.length-2) + "." + unionPayFee.substring(unionPayFee.length-2, unionPayFee.length) + "Ԫ";
		        
		        ret = prtpluginidEle.PrintLine("  POS��ˮ��   : " + printcontext.substring(0, 6) + ";");
		        ret = prtpluginidEle.PrintLine("  ���п���    : " + hiddenCardNumber + ";");
		        ret = prtpluginidEle.PrintLine("  �����ο���  : " + printcontext.substring(43, 55) + ";");
		        ret = prtpluginidEle.PrintLine("  �ն˺�      : " + trim(printcontext.substring(55, 70)) + ";");
		        ret = prtpluginidEle.PrintLine("  �̻���      : " + printcontext.substring(70, 85) + ";");
		        ret = prtpluginidEle.PrintLine("  ���׽��    : " + unionPayFee + ";");       
		    }
		    if (ret == 1)
		    {
		        alertError("����:��ӡ��ȱֽ�����!");
		        return;
		    }
		    ret = prtpluginidEle.PrintLine(" ---------------------------------------------"); 
		    ret = prtpluginidEle.PrintLine("  ��������,���в���֮��,����ӪҵԱ��ѯ.");
		    ret = prtpluginidEle.PrintLine(" ---------------------------------------------");
		    ret = prtpluginidEle.PrintLine("  ��ӡ�ص�:"+piData.pOrgName+".");
		    ret = prtpluginidEle.PrintLine("  ��ӡʱ��:"+piData.pDealTime+".");
		    if (ret == 1)
		    {
		        alertError("����:��ӡ��ȱֽ�����!");
		        return;
		    }
	        cutPaper();
		} 
		else 
		{
		    alertError("�Բ���,û�пɴ�ӡ������!");
			return;
		}
	}
	catch(e) 
	{
		alertError("�Բ���,Ʊ�ݴ�ӡ������!");
		cutPaper();//����������ֽ
	}
}
	   	
//��ֽ      
function cutPaper() 
{	
	try
	{
	  	var ret = window.parent.document.getElementById("prtpluginid").SetCutPaper();// var ret = 0;// ������
	  	if (ret == 1)
	  	{
			alertError("����:��ӡ��ȱֽ�����!");
			return;
	  	}
	}
	catch(e)
	{
		alertError("����:��ӡ������!");
		return;
	}
  	//alert("��ֽ");
}

// ������ʾ��
function alertError(info) 
{
    var oProcessDiv = document.getElementById("process");				
	if(info!="" && oProcessDiv) 
	{
  		oProcessDiv.style.display = "";
	  	oProcessDiv.innerHTML = "<font color=red>" + info + "</font>";
	}
}

/**
 *�������ƣ�initBlankCardReader()
 *���ýӿڣ�short InitCard()
 *�������ܣ���ʼ����У�������д�����Ƿ�����
 *��������
 *���أ�"" ��ʾ�����ɹ������� ������ֵ˵����
 */
function initBlankCardReader()
{
	//return "";//����ʹ��
	
	var message = "";
	
	try
	{ 
		var ret = window.parent.document.getElementById("simcardpluginid").InitCard();
		
		if (ret != 0) 
		{
			message = "����:����������!";
		}
	}
	catch(e)
	{
		message = "����:����������!";
	}
	
	return message;
}

/**
 *�������ƣ�initOpenIdCardReader()
 *���ýӿڣ�short OpenCom()
 *�������ܣ���ʼ����У��������֤�������Ƿ�����
 *��������
 *���أ�"" ��ʾ�����ɹ������� ������ֵ˵����
 */
function initOpenIdCardReader()
{
	//return "";//����ʹ��
	
	var message = "";
	
	try
	{
		var ret = window.parent.document.getElementById("idcardpluginid").OpenCom();
		
		if (ret != 0) 
		{
			message = "����:���֤����������!";
		}
	}
	catch(e)
	{
		message = "����:���֤����������!";
	}
	
	return message;
}

/**
 *�������ƣ�initListPrinter()
 *���ýӿڣ�short InitListPrinter()
 *		short SetPicturePath()
 *�������ܣ���ʼ����У��Ʊ�ݴ�ӡ���Ƿ�����
 *��������
 *���أ�"" ��ʾ�����ɹ������� ������ֵ˵����
 */
function initListPrinter() 
{
	//return "";//����ʹ��
	
	var message = "";
	
	try 
	{
        // ��ʼ��Ʊ�ݴ�ӡ��
		var initListPrt1 = window.parent.document.getElementById("prtpluginid").InitListPrinter();
		if (initListPrt1 == 1) 
		{
			message = "����:Ʊ�ݴ�ӡ��ȱֽ�����!";
		} 
		else if (initListPrt1 == 41) 
		{
			message = "����:Ʊ�ݴ�ӡ���豸�Ͳ���������δ��װ!";
		}
		
    	// ���ô�ӡͼ���·�� 
		var initListPrt2 = window.parent.document.getElementById("prtpluginid").SetPicturePath("");
		if (initListPrt2 == 1) 
		{
			message = "����:Ʊ�ݴ�ӡ��ȱֽ�����!";
		} 
		else if (initListPrt2 == 41) 
		{
			message = "����:Ʊ�ݴ�ӡ���豸�Ͳ���������δ��װ!";
		}
	}
	catch (e) 
	{
		message = "�����쳣,Ʊ�ݴ�ӡ����ʼ��ʧ��,�޷���ӡСƱ!";
	}
	
	return message;
}

/**
 *�������ƣ�initInvoicePrinter()
 *���ýӿڣ�short OpenCom()
 *		short InitVoicePrint()
 *�������ܣ���ʼ����У�鷢Ʊ��ӡ���Ƿ�����
 *��������
 *���أ�"" ��ʾ�����ɹ������� ������ֵ˵����
 */
function initInvoicePrinter() 
{
	//return "";//����ʹ��
	
	try 
	{
	    // �򿪷�Ʊ��ӡ������
		var openCom = window.parent.document.getElementById("invprtpluginid").OpenCom();
		
		if (openCom == 1) 
		{
			return "����:��Ʊ��ӡ�����ڹ���!";
		} 
		else 
		{
			if (openCom == 61) 
			{
				return "����:��Ʊ��ӡ������,�޷���ʼ��!";
			} 
			else if (openCom == 65) 
			{
				return "����:��Ʊ��ӡ��ȱֽ!";
			} 
			else if (openCom != 0) 
			{
				return "����:���豸�쳣,�޷���ʼ����Ʊ��ӡ��!";
			}
		}
		
		// ��ʼ����Ʊ��ӡ��
		var initInvoicePrt = window.parent.document.getElementById("invprtpluginid").InitVoicePrint();
		if (initInvoicePrt == 61) 
		{
			return "����:��Ʊ��ӡ������,�޷���ʼ��!";
		} 
		else if (initInvoicePrt == 65) 
		{
			return "����:��Ʊ��ӡ��ȱֽ!";
		}
		else if (openCom != 0) 
		{
			return "����:���豸�쳣,�޷���ʼ����Ʊ��ӡ��!";
		}
		
		// ��鷢Ʊ��ӡ��ȱֽ
		var v = window.parent.document.getElementById("invprtpluginid").CheckPaper();
		if (v != 0 )
		{
		    return "����:��Ʊ��ӡ��ȱֽ�����!";
		}
		
		return "";
	}
	catch (e) 
	{
		return "����:��Ʊ��ӡ��������,�޷���ӡ��Ʊ!";
	}
}

/**
 *�������ƣ�initCashPayEquip()
 *���ýӿڣ�short OpenCom()
 *�������ܣ���ʼ����У���ֽ�ʶ�����Ƿ�����
 *��������
 *���أ�"" ��ʾ�����ɹ������� ������ֵ˵����
 */
function initCashPayEquip() 
{
	//return "";//����ʹ��
	
	var message = "";
	
	try 
	{
		var ret = window.parent.document.getElementById("cashpluginid").OpenCom();
		
		if (ret != 0) 
		{
			message = "����:�ֽ�ʶ��������!";
		}
	}
	catch (e) 
	{
		message = "����:�ֽ�ʶ��������!";
	}
	
	return message;
}

/**
���ܣ��򿪶����豸���ô��ڣ��ȴ��û�����������
���أ�0 ��ʾ������-1 ��ʾ�쳣
�������û�ѡ��ʹ�����������н��Ѻ�ƽ̨ϵͳ���ô˽ӿڴ򿪶����豸������������ʾ�û�������������
**/
function initUnionCardPayEquip() 
{
	//return "";//����ʹ��
	
	var message = "";
	
	try
	{
		var ret = window.parent.document.getElementById("cardpluginid").OpenCom();
		
		if (ret != 0) {
		    
			message = "����:������������ʼ��ʧ��!";
		}
	}
	catch(e)
	{
		message = "����:������������ʼ���쳣!";
	}
	
	return message;
}

/**
 *�������ƣ�initKeyBoard()
 *���ýӿڣ�short OpenCom()
 *		short SetWorkMode()
 *�������ܣ���ʼ����У���ն���������Ƿ�����
 *��������
 *���أ�"" ��ʾ�����ɹ������� ������ֵ˵����
 */
function initKeyBoard() 
{
	//return "";//����ʹ��
	
	// ��ʼ�����ܼ���
	try 
	{
		var ret = window.parent.document.getElementById("keybrdpluginid").OpenCom();
		if (ret != 0) 
		{
			return "����:�����ն˼��̴���ʧ�ܣ�";
		}
		
		ret = window.parent.document.getElementById("keybrdpluginid").SetWorkMode(0);
		if (ret != 0) 
		{
			return "����:���ü���ģʽʧ�ܣ�";
		}
		
		return "";
	}
	catch (e) 
	{
		return "����:������̹���,�޷�ʹ���������!";
	}
}

/**
 *�������ƣ�checkReadCardStatus()
 *���ýӿڣ�short InitConfig()
 *	ReadCardStatus()
 *	short MoveCardToWrite()
 *�������ܣ���鷢����״̬
 *��������
 *���أ�"" ��ʾ�����ɹ������� ������ֵ˵����
 */
function checkReadCardStatus()
{
	//return "";//����ʹ��
	
	try
	{
		/**
		���ܣ���鷢����״̬
		��������
		���أ�ʧ�ܣ�-1���ɹ���0~ͨ����Ƭλ��~���俨Ƭ״̬
		�������緵�� 0~0~1 ��ʾ ͨ���޿������俨Ƭ����,������Ҫ�ӿ�
		           0~2~0 ��ʾ IC��λ���п��������޿�
		ͨ����Ƭλ�ã�����һ���ֽڡ�
		0��ͨ���޿�
		1�����ſ�λ���п�
		2��IC��λ���п�
		3��ǰ�˼п�λ���п�
		4��ǰ�˲��п�λ���п�
		ע�⣺�������������ĸ�λ��ʱ���Զ��������������䣬�ٷ���ͨ���Ϳ�Ƭ��״̬��
		���俨Ƭ״̬��
		  0:  �����޿�
		  1:  ���俨Ƭ����,������Ҫ�ӿ�
		  2:  ���俨Ƭ�㹻
		��������ʼ������������Ҫ���ô˽ӿڼ���豸״̬��
		**/
		stat = window.parent.document.getElementById("simcardpluginid").ReadCardStatus();
		
		if (stat == -1)
		{
			return "��������״̬ʧ�ܣ�";
		}
		
		var arr = stat.split('~');
		if(arr[2] == 0)
		{
			 return "���俨Ƭ����,��Ҫ�ӿ���";
		}
			
	}
	catch(e)
	{
		return "У�鷢����״̬�쳣";
	}
	
	return "";
}

/**
���أ��ɹ�0��ʧ��-1
�����������ɹ��󣬵��øýӿڣ���д�õ�SIM���³���
**/
function MoveOutCard()
{
	//return 0;// ������
	try{
		var a=window.parent.document.getElementById("simcardpluginid").MoveOutCard();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}

/**
���أ��ɹ�0��ʧ��-1
������д��ʧ�ܺ󽫿�����������
**/
function callBackCard()
{
	//return 0;// ������
	try{
		var a=window.parent.document.getElementById("simcardpluginid").callBackCard();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}

/**
���أ��ɹ�0��ʧ��-1
��������������/д��������Ϊ����ʹ��״̬
��InitCard()�ӿڶ�Ӧ��InitCard�ӿڵ��ú�����ʹ�ø��豸��ʹ����ϸÿؼ������CloseCard()
**/
function CloseCard()
{
	//return 0; // ������
	try{
		var a=window.parent.document.getElementById("simcardpluginid").CloseCard();
		return a;
	}
	catch(b)
	{
		return -1;
	}
}

/**
 * �����ն˱�������СƱ��ӡ
 * @param {Object} piData
 * @return {TypeName} 
 */
function printPrepareTicket(piData) 
{
	try
	{
		var prtpluginidEle = window.parent.document.getElementById("prtpluginid");

		if (piData != "undefined") 
		{
			var ret = prtpluginidEle.GetPrinterStatus();
			//var ret = 0;// ������
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
			ret = prtpluginidEle.PrintPicture(1);
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
			ret = prtpluginidEle.PrintLine("  ");
			ret = prtpluginidEle.PrintLine("  ");
	  	    ret = prtpluginidEle.PrintLine("  �����ն˿հ׿�����������ƾ��");
			ret = prtpluginidEle.PrintLine(" ---------------------------------------------");
			if (ret == 1) 
			{
	        	alertError("����:��ӡ��ȱֽ�����!");
				return;
			}  	
	    
	        //��ӡ����ɷ���Ϣ
	  	    ret = prtpluginidEle.PrintLine("  �ն˱��: " + piData.termId);
	  	    ret = prtpluginidEle.PrintLine("  �ֻ�����: " + piData.servnumber);
	  	    ret = prtpluginidEle.PrintLine("  Ӧ�ɽ��: " + piData.receptionFee + " Ԫ");
	  	    ret = prtpluginidEle.PrintLine("  ʵ�ɽ��: " + piData.tMoney + " Ԫ");
	  	    ret = prtpluginidEle.PrintLine("  ����ʱ��: " + piData.pDealTime);
	  	    
	  	    // �ɷѳɹ�
	  	    if(piData.payStatus == 0)
	  	    {
		  	    ret = prtpluginidEle.PrintLine("  �ɷ���ˮ: " + piData.dealNum);
	  	    }
	  	    if(piData.installStatus == 0)
	  	    {
	  	    	ret = prtpluginidEle.PrintLine("  ������ˮ: " + piData.formnum);
	  	    }
	  	    ret = prtpluginidEle.PrintLine("  ����״̬: " + piData.pDealStatus);
	        
	        //��ӡβ����Ϣ
			ret = prtpluginidEle.PrintLine(" ---------------------------------------------"); 
	  	    ret = prtpluginidEle.PrintLine("  ��������,���в���֮��,����ӪҵԱ��ѯ");
	  	    ret = prtpluginidEle.PrintLine("  �ͻ���������:10086");
	  	    ret = prtpluginidEle.PrintLine(" ---------------------------------------------");
	  	    ret = prtpluginidEle.PrintLine("  ��ӡ�ص�:" + piData.pOrgName);
	  	    ret = prtpluginidEle.PrintLine("  ��ӡʱ��:" + piData.pPrintDate);
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
	catch(e) 
	{
		alertError("�Բ���,Ʊ�ݴ�ӡ������!");
		return;
	}
		
}

/**
 * �����ն˲�������СƱ��ӡ
 * @param {Object} piData
 * @param {Object} printcontext
 * @return {TypeName} 
 */
function printReissueTicket(piData, printcontext) 
{
	try 
	{
		var prtpluginidEle = window.parent.document.getElementById("prtpluginid");
		
	  	var ret;
	  	try
	  	{
	  		ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
	  	}
	  	catch(e)
	  	{
			alertError("����:��ӡ���ؼ�δ��װ!");
			return;
	  	}
	  	if (ret == 1)
	  	{
	  	   alertError("����:��ӡ��ȱֽ�����!");
	  	   return;
	  	}
	  	else if (ret == 41)
	  	{
	  	   alertError("����:��ӡ���豸�Ͳ���������δ��װ!");
	  	   return;
	  	}
	  	
	  	ret = prtpluginidEle.PrintLine("  ");
	  	ret = prtpluginidEle.PrintLine("  ");
	  	ret = prtpluginidEle.PrintLine("  �����ն˿հ׿�����������ƾ��");
	  	ret = prtpluginidEle.PrintLine(" ---------------------------------------------");  	    
	    if (ret == 1)
	    {
	        alertError("����:��ӡ��ȱֽ�����!");
	        return;
	    }  	
	    
	    //��ӡ����ɷ���Ϣ
  	    ret = prtpluginidEle.PrintLine("  �ն˱��: " + piData.termId);
  	    ret = prtpluginidEle.PrintLine("  �ֻ�����: " + piData.servnumber);
  	    ret = prtpluginidEle.PrintLine("  ��        ��: " + piData.idCardName);
  	    ret = prtpluginidEle.PrintLine("  Ӧ�ɽ��: " + piData.receptionFee + " Ԫ");
  	    ret = prtpluginidEle.PrintLine("  ʵ�ɽ��: " + piData.tMoney + " Ԫ");
  	    ret = prtpluginidEle.PrintLine("  ����ʱ��: " + piData.pDealTime);
  	    ret = prtpluginidEle.PrintLine("  �ն���ˮ: " + piData.pTerminalSeq);
  	    
  	    // �ɷѳɹ�
  	    if(piData.payStatus == 0)
  	    {
	  	    ret = prtpluginidEle.PrintLine("  �ɷ���ˮ: " + piData.dealNum);
  	    }
  	    if(piData.installStatus == 0)
  	    {
  	    	ret = prtpluginidEle.PrintLine("  ������ˮ: " + piData.formnum);
  	    }
  	    ret = prtpluginidEle.PrintLine("  ����״̬: " + piData.pDealStatus);
	  	
	  	//��ӡ������СƱ
	  	if (printcontext != "")
		{
	  		ret = prtpluginidEle.PrintLine("  ");
	  		ret = prtpluginidEle.PrintLine("  ");
	  		ret = prtpluginidEle.PrintLine("  ����ˢ������ƾ��");
	  		ret = prtpluginidEle.PrintLine("  ֧����ˮ: " + piData.pTerminalSeq);
	  		ret = prtpluginidEle.PrintLine(" ---------------------------------------------");  	    
	    	if (ret == 1)
	    	{
	        	alertError("����:��ӡ��ȱֽ�����!");
	        	return;
	    	}
	    	
	    	var cardNumber = printcontext.substring(18, 37);
	    	while (cardNumber.charAt(cardNumber.length - 1) == " ") 
			{
				cardNumber = cardNumber.substring(0, cardNumber.length - 1);
			}
			while (cardNumber.charAt(0) == " ") 
			{
				cardNumber = cardNumber.substring(1, cardNumber.length);
			}
			var hiddenCardNumber = cardNumber;
			if (cardNumber.length > 10)
			{
				hiddenCardNumber = cardNumber.substring(0, 6) + "******" + cardNumber.substring(cardNumber.length - 4, cardNumber.length);
			}
			var unionPayFee = printcontext.substring(85, 97);
			var unionPayFeeFront = unionPayFee.substring(0, unionPayFee.length-3);
			while (unionPayFeeFront.charAt(0) == "0") 
			{
				unionPayFeeFront = unionPayFeeFront.substring(1, unionPayFeeFront.length);
			}
			
			unionPayFee = unionPayFeeFront + unionPayFee.substring(unionPayFee.length-3, unionPayFee.length);
			unionPayFee = unionPayFee.substring(0, unionPayFee.length-2) + "." + unionPayFee.substring(unionPayFee.length-2, unionPayFee.length) + "Ԫ";
	    	
	    	ret = prtpluginidEle.PrintLine("  POS��ˮ��   : " + printcontext.substring(0, 6) + ";");
	    	ret = prtpluginidEle.PrintLine("  ���п���    : " + hiddenCardNumber + ";");
	    	ret = prtpluginidEle.PrintLine("  �����ο���  : " + printcontext.substring(43, 55) + ";");
	    	ret = prtpluginidEle.PrintLine("  �ն˺�      : " + trim(printcontext.substring(55, 70)) + ";");
	    	ret = prtpluginidEle.PrintLine("  �̻���      : " + printcontext.substring(70, 85) + ";");
	    	ret = prtpluginidEle.PrintLine("  ���׽��    : " + unionPayFee + ";");    	
		}
	  	
	    if (ret == 1)
	    {
	        alertError("����:��ӡ��ȱֽ�����!");
	        return;
	    }
	    
	  	ret = prtpluginidEle.PrintLine(" ---------------------------------------------"); 
	  	ret = prtpluginidEle.PrintLine("  ��������,���в���֮��,����ӪҵԱ��ѯ.");
	  	ret = prtpluginidEle.PrintLine(" ---------------------------------------------");
	  	ret = prtpluginidEle.PrintLine("  ��ӡ�ص�:"+piData.pOrgName+".");
	  	ret = prtpluginidEle.PrintLine("  ��ӡʱ��:"+piData.pPrintDate+".");
	  	if (ret == 1)
	  	{
	  	    alertError("����:��ӡ��ȱֽ�����!");
	  	    return;
	  	}
		
	  	//��ӡ����ֽ
	  	cutPaper();
	} 
	catch(ex) 
	{
		alertError("��ӡ�ɷ�ƾ������,����ϵӪҵ������Ա�鿴�ɷ��Ƿ�ɹ���");
	 	cutPaper();//����������ֽ
	}
}
