/**
��ȡ������ȡ��״̬
**/
function GetBankCardStatus()
{	
    //return '1';
	var ret;
	try
	{
		ret = window.parent.document.getElementById("cardpluginid").GetBankCardStatus();
		return ret;
	}
	catch(e)
	{
		return '-1';
	}
}

/**
�رն�����
**/
function CloseCardDevice()
{	
	try
	{
		window.parent.document.getElementById("cardpluginid").CloseDevice();
	}
	catch(e)
	{
	}
}