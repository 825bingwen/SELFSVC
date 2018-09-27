// �������ɷѽӿڳ�ʼ������
// ������unionUserId�������̻��ţ�unionPayCode������Ϊˢ�����ն˷����Ψһ���
// ���أ�0 - ��ʼ���ɹ�
//      -1 - ��ʼ��ʧ�ܣ�дINI�ļ�����
function initUnionPayPlugin(unionUserId,unionPayCode){
    //return 0;//����ʹ��
	try{
		var ret = window.parent.document.getElementById("cardpluginid").InitConfig(unionUserId,unionPayCode);
		return ret;
	}catch(Ex){
		return -99;
	}
}
/*�����������*/
function readPwdFinish(){
    //return 1; //����ʹ��
	try{
		var ret = window.parent.document.getElementById("cardpluginid").ReadPasswordFinished();
		return ret;
	}catch(Ex){
		return -99;
	}
}
/*�ȴ���������*/
function readingPwd(){
    //return 0;//����ʹ��
	try{
		var ret = window.parent.document.getElementById("cardpluginid").ReadingPassword();
		return ret;
	}catch(Ex){
		return -99;
	}
}

/*�����ۿ�*/
function fPayByPos(posnum,batchnum,bankcardID,tMoney){
    //return "-1$��ʱ�����ҵĴ�"; //����ʹ��
    //return "0$00$123456789101112$200907031049$100";//����ʹ��
	try{
		var ret=window.parent.document.getElementById("cardpluginid").Pay(posnum,batchnum,bankcardID,tMoney)
		return ret;
	}catch(Ex){
		return -1;
	}
}

/*��������*/
function cancelPayToUnion(posnum,batchnum,bankcardID,money){
	try{
		var ret=window.parent.document.getElementById("cardpluginid").CancelPay(posnum,batchnum,bankcardID,money);
		return ret;
	}catch(Ex){
		return -1;
	}
}

/*�ȴ��û�ˢ��*/
function waitingForCard(){
    //return 0;//����ʹ��
	try{
		var ret = window.parent.document.getElementById("cardpluginid").ReadingBankCard();
		return ret;
	}catch(Ex){
		return -99;
	}
}

/*ȡ���ȴ��û�ˢ��*/
function cancelWaitingCard(){
    //return 0; //����ʹ��
	try{
		var ret = window.parent.document.getElementById("cardpluginid").CancelReadingBankCard();
		return ret;
	}catch(Ex){
		return -1;
	}
}

/*��������*/
function readCardFinish(){
    //return 0; //����ʹ��
	try{
		var ret = window.parent.document.getElementById("cardpluginid").ReadBankCardFinished();
		return ret;
	}catch(Ex){
		return -99;
	}
}

/*��ȡ�û�����*/
function getCardNum(){
    //return "1314520000000888";  //����ʹ��
	try{
		var ret = window.parent.document.getElementById("cardpluginid").GetBankCardID();
		return ret;
	}catch(Ex){
		return "";
	}
}

function signTo(){
	//return "0$00"; //����ʹ��
	try{
		var ret = window.parent.document.getElementById("cardpluginid").SigntoUnionPay();
		return ret;
	}catch(Ex){
		return -99;
	}
}

//�˿�
function returnCard(){
    //alert("returnCard:�˿�");
    //return 0; //����ʹ��
	try{
		var ret = window.parent.document.getElementById("cardpluginid").TakeOutBankCard();
		return ret;
	}catch(Ex){
		return -1;
	}
}

//��ȡ������ȡ��״̬,1��ʾδȡ��
function takeCardState(){
    //alert("takeCardState:��ȡ������ȡ��״̬");
    //return 1; //����ʹ��
	try{
		var ret = window.parent.document.getElementById("cardpluginid").TakeOutBankCardStatus();
		return ret;
	}catch(Ex){
		return -1;
	}
}

//�̿�(�ӱ�����)
function captureCard(){
    //return 0;//����ʹ��
	try{
		var ret = window.parent.document.getElementById("cardpluginid").CaptureBankCard();
		return ret;
	}catch(Ex){
		return -1;
	}
}
function getPosNum(){
    //return "0$000001$888888"; //����ʹ��
	try{
		var ret = window.parent.document.getElementById("cardpluginid").GetPosBatchNum();
		return ret;
	}catch(Ex){
		return -1;
	}
}

function fPayByUnion(posnum,batchnum,bankcardID,money,servnumber){
    //0$����Ӧ����$����������ˮ��$��������ʱ��YYYYMMDDhhmmss$�ۿ���$��Ʊ��Ϣ
    //������ˮ��,BOSSϵͳ��ˮ��,�ͻ�����,�ɷѽ��,�տλ����;�����·�,�ϼƽ��, ������Ϣ,��Ʊ����
    //return "-1$���ǳ�ʱ��";
    //return "0$00$00000000000000000001$200907091706$100$hd35020090602095000,31088111898202316,������,5000,�����԰�Ӫҵ��;2009.04.01---2009.06.01,50.00;�Ʒѽ��ϼƣ�72.54,��ǰ�»��ѣ�0.00,������49.47,����:0;##��������##|||";
    try{
        var ret = window.parent.document.getElementById("cardpluginid").UniPay(posnum,batchnum,bankcardID,money,servnumber);
        return ret;
    }catch(Ex){
        return -1;
    }
}

function getUnionInfo(code){
	if(code=="75"){
		return "PIN����������������������";
	}else if(code=="79"){
		return "POS�ն��ش��ѻ�����";
	}else if(code=="22"){
		return "��������������";
	}else if(code=="38"){
		return "����Ա����û����������������";
	}else if(code=="65"){
		return "����ȡ���������";
	}else if(code=="15" || code=="31"){
		return "�˿���������";
	}else if(code=="36"){
		return "�˿������뻻������";
	}else if(code=="37"){
		return "�˿���������ϵ�յ���";
	}else if(code=="61"){
		return "���̫��";
	}else if(code=="33"){
		return "���ڿ�,����ϵ������";
	}else if(code=="68"||code=="98"){
		return "���׳�ʱ��������";
	}else if(code=="00"){
		return "���׳ɹ�";
	}else if(code=="42"){
		return "����ʧ�ܣ�����ϵ������";
	}else if(code == "01" || code == "02" || code == "05" || code == "06" || code == "19" || code == "20" 
		|| code == "21" || code == "23" || code == "25" || code == "39" || code == "40" || code == "44" 
		|| code == "52" || code == "53" || code == "56" || code == "57" || code == "59" || code == "60" 
		|| code == "62" || code == "63" || code == "64" || code == "93"){
		return "����ʧ�ܣ�����ϵ������";
	}else if(code=="66"){
		return "����ʧ�ܣ�����ϵ�յ��л�����";
	}else if(code == "90" || code == "91" || code == "92" || code == "94" || code == "95" || code == "96"){
		return "����ʧ�ܣ����Ժ�����";
	}else if(code == "09" || code == "12" || code == "13" || code == "30"){
		return "����ʧ�ܣ�������";
	}else if(code=="67"){
		return "û�տ�";
	}else if(code == "34" || code == "35" || code == "04" || code == "07" || code == "41" || code == "43"){
		return "û�տ�,����ϵ�յ���";
	}else if(code == "54"){
		return "����ϵ������";
	}else if(code=="77"){
		return "������������ǩ��";
	}else if(code=="55"){
		return "������";
	}else if(code == "99" || code == "0A"){
		return "������ǩ��";
	}else if(code=="03"){
		return "�̻�δ�Ǽ�";
	}else if(code=="14"){
		return "��Ч���ţ�����ϵ������";
	}else if(code=="51"){
		return "���㣬���ѯ";
	}else if(code=="97"){
		return "�ն�δ�Ǽǣ�����ϵ�յ��л�����";
	}else if(code=="58"){
		return "�ն���Ч������ϵ�յ��л�����";
	}else{
		return "δ֪����";
	}
}

