/***********************************************

				�������������۷���ز���

***********************************************/

/**
����: ��ʼ�������� 
����: 0:��ʼ���ɹ� -1:��ʼ��ʧ��
����: ���ö������ӿ�ǰ�Ƚ��г�ʼ��
**/
function InitReadUserCard()// �ն˳�ʼ��
{	//return '0';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").InitReadUserCard();
		return ret;
	}catch(e){
		showFrmErr("�����쳣,������������ʼ��ʧ��,�޷������������ɷ�!");
		return '-1';
	}
}

/**
���ܣ��������ؼ����������ն��ϵĶ����豸�ؼ������ȴ������̣߳��綯��������򿪲��Ž����û����п�
���أ��ɹ���ʶ$������ʾ(�ɹ����޴��ֶ�) 0:�ɹ� -1:ʧ��
�������û�ˢ��ǰ���ô˽ӿڣ����������ն��ϵĶ����豸����س��򣬶����豸��һֱ�ȴ��û�ˢ��
**/
function ReadingUserCard()// ׼��ˢ��
{	//return '0';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").ReadingUserCard();
		return ret;
	}catch(e){
		showFrmErr("�����쳣,�򿪲��Ž����û����п�����!");
		return '-1';
	}
}

/**
���ܣ������û�ˢ��״̬�����������������
���أ�0 �����ɹ���-1 ����ʧ�ܣ�1 ��δ��ȡ������Ϣ
����������׼��ˢ���ӿڣ�ReadingUserCard ����ҳ�涨ʱѭ�����ô˽ӿڻ�ȡ����������������������0��ʾ�����ɹ���ֹͣ��ʱѭ�����ã��������-1��ʾ����ʧ�ܣ�ֹͣ��ʱѭ�����ã��������1��ʾ�豸��δ��ȡ������Ϣ��������ʱѭ�����á���ָ��ʱ�䣨30�룩���豸һֱδ��ȡ������Ϣ������Ϊ������ʱ��ֹͣ��ʱѭ�����á�
**/
function ReadUserCardFinished()// ˢ����Ͻӿ�
{	//return '0';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").ReadUserCardFinished();
		return ret;
	}catch(e){
		showFrmErr("�����쳣,ȡ�û�ˢ��״̬ʱ����!");
		return '-1';
	}
}

/**
���ܣ��رն�����������������д򿪵��߳�
���أ��ɹ���ʶ$������ʾ(�ɹ����޴��ֶ�)
�������������ն˶����豸��鵽�û��Ѿ��ſ�ʱ�����ô˽ӿڹرն����豸��������̼�����̣߳�Ȼ����������ӿڣ�������ʹ������豸��
**/
function CloseReadingCardFixing()// ������������̹رսӿ�
{	//return 0;
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").CloseReadingCardFixing();
		return ret;
	}catch(e){
		showFrmErr("�����쳣,�رն��������������ʱ����!");
		return -1;
	}
}

/**
���ܣ�֪ͨ�����ն��϶����豸�û�����ˢ�����رն����豸������̣߳���ֹ�����豸�ȴ�����״̬
���أ��ɹ���ʶ$������ʾ(�ɹ����޴��ֶ�)
�������������ն˶����豸���ڵȴ��û�ˢ��ʱ�����û�������ˢ�������ô˽ӿ�ȡ�������豸�ĵȴ�״̬���رն����豸������̡߳�
**/
function CancelReadingUserCard()// ȡ��ˢ��
{	//return '0';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").CancelReadingUserCard();
		return ret;
	}catch(e){
		showFrmErr("�����쳣,ȡ��ˢ������!");
		return '-1';
	}
}

// ���ܣ��жϵ�ǰ���������Ƿ��п�������п������˿����ٹر����ж�������̣߳���������п���Ϣ��������Ϣ���ӿ��Զ��жϵ�ǰ�豸ʱ����������ˢ���������Ϊˢ��������Ҫ���˿�������
// ���أ��ɹ���ʶ$������ʾ(�ɹ����޴��ֶ�)
// ������������ɻ�ˢ�������У��û���ˢ�����򽫿�����������ڣ������ύ�ۿ�����ǰ���û�����ȡ�����ף����߷����쳣�����ô˽ӿ��˿����رն�������̡߳�
function TakeOutUserCard()// �˿��ӿ�
{	//return '0';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").TakeOutUserCard();
		return ret;
	}catch(e){
		showFrmErr("�����쳣,ȡ���˿�����!");
		return '-1';
	}
}

/**
���ܣ��ӿ��Զ��жϵ�ǰ�豸ʱ����������ˢ���������Ϊ�������������豸�˿����û�ȡ����״̬���û���ȡ�����п�����1���û���δȡ�����п�����0�������Ϊˢ���豸�������ֱ�ӷ���0��
���أ�0 ��ʾ�û���ȡ�߿���1 ��ʾ�û���δȡ�߿�
�����������豸�˿���������ƽ̨ҳ��������ʱѭ�����ô˽ӿڣ��ж��û��Ƿ�ȡ�߿�����ָ��ʱ��(30��)�ڣ�û��ȡ�߿�������Ϊ�û�ȡ����ʱ���ں���������ҳ����Ҫ�����̿��ӿڡ�
**/
function TakeOutUserCardStatus()// ��ȡ������ȡ��״̬
{	//return '1';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").TakeOutUserCardStatus();
		return ret;
	}catch(e){
		showFrmErr("�����쳣,��ȡ������ȡ��״̬����!");
		return '-1';
	}
}

/**
���ܣ��̿�
���أ��ɹ���ʶ$������ʾ(�ɹ����޴��ֶ�)
���������豸�˿����û���ָ��ʱ�䷶Χ��û��ȡ�߿�������ô˽ӿ��̵��û��Ŀ���
**/
function captureUserCard()// �̿�
{	//return '0';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").CaptureUserCard();
		return ret;
	}catch(e){
		showFrmErr("�����쳣, �̿�����!");
		return '-1';
	}
}

/**
���ܣ���ȡ����Ϣ
���أ��ɹ�����:0$����Ϣ������Ϣ��ʽ�ɰ�������~�û���~������������ʧ�ܷ���-1
���������ô˽ӿڶ�ȡ�������ڵĿ�����������ˮ�ѿ����翨��ú�����ȣ���Ϣ��
**/
function GetUserCardInfo()// ��ȡ����Ϣ�ӿ�
{	//return '0$123456789~��ǿ~1050';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").GetUserCardInfo();
		return ret;
	}catch(e){
		showFrmErr("�����쳣,��ȡ����Ϣ����!");
		return '-1';
	}
}

/**
���ܣ���������
������
	strin
	����Ա��	    ASCII	10	�����Ҳ��ո�
	�ֻ���	    ASCII	18	�����Ҳ��ո�
	�������ͱ�־	ASCII	1	Ϊ'A'����ʾ�ǣ����ѽ��ף���Ϊ'B'����ʾ�ǣ��ش�ӡ����Ϊ'C'����ʾ������
	���	        ASCII	12	�Է�Ϊ��λ�������󲹡�0��, ������׽��Ϊ��000000000000��,�̳��򽫸��ݽ������ͱ�־�����Ӧ�Ĳ�������������ش�Ʊ�ݣ���ѯ���
	���磺 987654321 13951878070       A000000001234000000  
	��ʾ����Ա��Ϊ 987654321�� ���ѽ��ף� ���׽��Ϊ12.34
	���磺 987654321 13951878070       C000000000000000000  
	��ʾ����Ա��Ϊ 987654321�� ��ʾ������
���أ�
	Resultstr ������Ӧ��������
	�ж�ʱ���ж�ǰ��λ�Ƿ�Ϊ��00��
	������ǣ���ʾ����ʧ�ܣ�
	����ǣ���������±�ȡ������Ҫ��ֵ��
	��������ΪA��Bʱ�����׳ɹ���Ҫ��ӡprintcontext

	����	          ��ʽ	     ��	    ��   ��
	��Ӧ��	    ASCII	     2	    00 ��ʾ�ɹ���������ʾʧ��
	����	        ASCII	     19	     �����Ҳ��ո�
	�ֻ���	    ASCII	     18	     �����Ҳ��ո�
	�������ͱ�־	ASCII	     1	     Ϊ'A'����ʾ�ǣ����ѽ��ף���Ϊ'B'����ʾ�ǣ��ش�ӡ����Ϊ'C'����ʾ��������
	���	        ASCII	     12	     �Է�Ϊ��λ�������󲹡�0��
	
	���磺00456351000000000012313951878070       A000000001234
	��ʾ�����׳ɹ�������Ϊ4563510000000000123���ֻ���Ϊ13951878070�����Ϊ12.34
	���磺ER��������
	��ʾ������ʧ�ܣ�������ΪER��
	
	printcontext ��ӡ�ļ�����
	�̻����:???????????????                          
	�ն˱��:????????                                 
	��������:???????????????                          
	���п���:???????????????????                      
	�� �� ��:?????? ��Ȩ��:??????                     
	���ײο���:????????????                           
	����ʱ��:???????????????????                      
	ƾ ֤ �� :????????????                            
	���(RMB):??????????????????      
	
	�������ڹرտ�����������̵Ĵ��ں󣬵��ô˽ӿ�����������������ۿ�����
**/
function cardPay(strin)// �ۿ�ӿ�
{
	//var array = ["00456351000000000012313506341953      A000000001234","000000000000005~yanqiang~A~12345567891234556789~111111~333333~222222222222~2010-8-11 15:10:41~1111111111111~000000000000001000"];
	return array;
	try{
		window.parent.document.getElementById("unionpluginid").cardPay(strin);
		var resultstr = window.parent.document.getElementById("unionpluginid").resultstr;
		var printcontext = window.parent.document.getElementById("unionpluginid").printcontext;
		array = [resultstr,printcontext];
	}catch(e){
		showFrmErr("�����쳣,�������ۿ�ʧ��!");
		return -1;
	}
	return 0;
}

/**
���ܣ����豸���õĴ��ڲ����������߳�
���أ�0 ��ʾ������1 �˿ڣ����ڣ����ϣ����� ������ֵ˵��
��������ҳ����������нӿڷ���ʹ��ǰ�����ñ��ӿڴ򿪸��豸
**/
function OpenCom()
{
	var ret = "";
	try{
		ret = window.parent.document.getElementById("keybrdpluginid").OpenCom();
		return ret;
	}catch(e){
		showFrmErr("�����쳣,��������̴���ʧ�ܣ�");
		return "-1";
	}	
}

/**
���ܣ����ù���ģʽ
������WorkMode �C 0����ͨ����ģʽ��1�����ܼ���ģʽ
���أ�������ֵ˵��
��������ҳ������̴򿪺���ʹ��ǰ���ñ��ӿ�����������̹���ģʽ
**/
function SetWorkMode(WorkMode)
{
	//return "0";
	var ret;
	try{
		ret = window.parent.document.getElementById("keybrdpluginid").SetWorkMode(WorkMode);
		return ret;
	}catch(e){
		showFrmErr("�����쳣,����������̵Ĺ���ģʽʧ�ܣ�");
		return "-1";
	}
}

/**
���ܣ����ַ�
������resource:�����ַ��� 
	 align:left right  
	 sign:�滻�ķ���
	 count:����
���أ���װ�õ��ַ����ַ���
**/
function formatStr(resource,align,sign,count)
{
	var str = "";
	for(var i=0;i<count-resource.length;i++){
		str = str + sign;
	}
	if("left" == align){// ��
		str = str + resource;
	}else if("right" == align){// �Ҳ�
		str = resource + str;
	}
	return str;
	
}







