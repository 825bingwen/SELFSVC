/*
 * �ļ�����Constants.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ�������������
 */
package com.gmcc.boss.selfsvc.common;

import java.util.HashMap;
import java.util.Map;


/**
 * ������
 * 
 * @author g00140516
 * 
 */
public class Constants extends ConstantsBase
{
    /**
     * ���ֲ�ѯ��ͷ
     */
    private static final String[] SCORE_TITLE = {"Ʒ������","���û���","������������","�������ѻ���","���½�������","�����Ѷһ�����","����ĩʣ�����","����������"};
    
    /**
     * GSM�嵥��ʾ�ֶ�(1:GSMCDR)
     * 
     */
    private final static Object[] GSM_SHOW = {
        new String[] {"��ͨ���굥��"},
        // new String[] {"���", "�Է�����", "��������", "����������", "ͨ����ʼʱ��", "ͨ��ʱ��(��)", "������(Ԫ)", "��;��(Ԫ)", "��Ѷ��Ϣ��(Ԫ)", "�ܻ���(Ԫ)"},
        new String[] {"���","ͨ����ʼʱ��", "ʱ��(��)", "��������", "�Է�����", "����ͨ����", "��;��", "���η�", "����������", "�Զ�ͨ����", "�Զ����ڵ�", "С��", "������", "��վ����"},
        new String[] {"�굥������", "ʱ���ϼƣ�", "���úϼ�(Ԫ)��"}, 
        new String[] {"recordcount", "totaltimes", "totalfee"}};
    
    /**
     * �����굥��ʾ�ֶ�(2:SMSCDR)
     * 
     */
    private final static Object[] SMS_SHOW = {
	    new String[] {"�������굥��"},
        //new String[] {"���", "��Ϣ����", "Դ��ַ", "Ŀ�ĵ�ַ", "����ʱ��", "��Ϣ����", "����(Ԫ)"},
	    new String[] {"���", "��Ϣ����", "Դ��ַ", "Ŀ�ĵ�ַ", "��ʼʱ��", "��Ϣ����", "���ŷ���"},
        new String[] {"�굥������", "���úϼ�(Ԫ)��"},
        new String[] {"recordcount", "totalfee"}};
    
    /**
     * �����굥��ʾ�ֶ�(3:IMSGCDR)
     * 
     */
    private final static Object[] ISMG_SHOW = {
	    new String[] {"�������굥��"},
        //new String[] {"���", "�����ṩ��", "����������Ϣ��(Ԫ)", "���·�(Ԫ)", "ҵ�����", "��ҵ����", "����ʱ��", "�������"},
        new String[] {"���", "ҵ�����", "��ҵ����", "�������", "ҵ�����", "����������", "����ʱ��", "����������Ϣ��", "���·�"},
        //new String[] {"�굥������", "���úϼ�(Ԫ)��"}, 
        new String[] {"�굥������", "������Ϣ��(Ԫ)��", "���·�(Ԫ)��"},
        //new String[] {"recordcount", "totalfee1"}};
        new String[] {"recordcount", "totalfee1", "totalfee2"}};
    
    /**
     * GPRS�굥��ʾ�ֶ�(4:GPRSCDR)
     * 
     */
    private final static Object[] GPRS_SHOW = {
        new String[] {"��GPRS�굥��"},
        //new String[] {"���", "�����ʶ", "��ʼʱ��", "ʱ��", "����ʱ������������", "����ʱ������������", "����ʱ��ʱ��", "�Ż�ʱ������������", "�Ż�ʱ������������","�Ż�ʱ��ʱ��", "����"},
        //new String[] {"���", "�����ʶ", "æ��ʱ", "��ʼʱ��", "��������", "��������", "�Ʒ�����", "ҵ������", "�������", "ͨ������", "æʱ����", "��ʱ����"},
        new String[] {"���","�����ʶ","��ʼʱ��","æ/��ʱ","�Ʒ���������","�Ʒ���������","�Ʒ�ʱ��","���ݼƷ�ҵ������","���������������","���������������","ͨ������"},
        new String[] {"�굥������", "������������(K)��", "������������(K)��"},
        new String[] {"recordcount", "totalgprs1", "totalgprs2"},
        new String[] {"1����������λ�粻�ر�ָ����Ϊ�ֽڣ����õ�λΪԪ��ʱ����λΪ��",
                "2��ÿ�������ļƷ�������Ϊ(����ʱ������������+����ʱ������������)����1024���KB�������в���1KB����1KB��"}};
    
    /**
     * WLAN�굥��ʾ�ֶ�(5:WLANCDR)
     * 
     */
    private final static Object[] WLAN_SHOW = {
		new String[] {"��WLAN�굥��"},
        //new String[] {"���", "�嵥������", "��֤��ʽ", "����ʱ��", "ʱ��(��)", "��������(�ֽ�)", "��������(�ֽ�)", "����(Ԫ)"},
		new String[] {"���", "�嵥������", "��֤��ʽ", "����ʱ��", "ʱ��", "����"},
        new String[] {"�굥������", "ʱ���ϼƣ�", "���úϼ�(Ԫ)��"}, 
        new String[] {"recordcount", "totaltimes", "totalfee"}};
    
    /**
     * �����굥��ʾ�ֶ�
     * 
     */
    private final static Object[] MMS_SHOW = {
	    new String[] {"�������굥��"},
        //new String[] {"��������", "ҵ������", "����ʱ��", "���ͷ���ַ", "�Է�����", "��Ϣ����", "ͨ�ŷ�(Ԫ)", "��Ϣ��(Ԫ)"},
	    //new String[] {"���", "ҵ������" ,"��������" ,"���ͷ���ַ" ,"���շ���ַ" ,"����ʱ��" ,"��Ϣ����" ,"ͨ�ŷ�" ,"��Ϣ��"},
	    new String[] {"���","ҵ������","��������","ҵ�����","�������","��ҵ����","���ͷ���ַ","���շ���ַ","����ʱ��","��Ϣ����","ͨ�ŷ�","��Ϣ��"},
        new String[] {"�굥������", "ͨ�ŷѺϼƣ�", "��Ϣ�Ѻϼƣ�"}, 
        new String[] {"recordcount", "totalfee1", "totalfee2"}};
    
    /**
     * ������Ϣ���굥��ʾ�ֶ�
     * 
     */
    private final static Object[] INFOFEE_SHOW = {
	    new String[] {"��������Ϣ���굥��"},
        //new String[] {"�Է�����", "����������", "ͨ����ʼʱ��", "ʱ��", "С��(Ԫ)"},
	    new String[] {"���", "ͨ����ʼʱ��" ,"ʱ��(��)" ,"С��" ,"�Է�����" ,"����������"},
        new String[] {"�굥������", "ʱ���ϼƣ�", "���úϼƣ�"},
        new String[] {"recordcount", "totaltimes", "totalfee"}};
    
    /**
     * VPMN�굥��ʾ�ֶ�
     * 
     */
    private final static Object[] VPMN_SHOW = {
	    new String[] {"��VPMN�굥��"},
	    //new String[] {"���", "ͨ����ʼʱ��" ,"ʱ��(��)" ,"��������" ,"�Է�����" ,"����ͨ����" ,"��;��" ,"���η�" ,"����������" ,"С��" ,"������"},
        //new String[] {"�굥������", "���úϼ�(Ԫ)��"}, 
	    new String[] {"���", "��������" ,"�Է�����" ,"ͨ����ʼʱ��" ,"ʱ��" ,"ͨ���ص�" ,"ͨ�ŷ�"},
        new String[] {"�굥������", "���úϼ�(Ԫ)��"}, 
        new String[] {"recordcount", "totalfee"}};
    
    /**
     * PIMM�굥��ʾ�ֶ�
     * 
     */
    private final static Object[] PIMM_SHOW = {
	    new String[] {"��PIMM�굥��"},
	    new String[] {"���", "��������", "ҵ�����", "ʹ���û�����", "����ʱ��", "����������Ϣ��", "���·�"},
        new String[] {"�굥������", "������Ϣ�ѣ�", "���·ѣ�"}, 
        new String[] {"recordcount", "totalfee1", "totalfee2"}};
     
     /**
      * �ֻ������굥��ʾ�ֶ�
      * 
      */
    private final static Object[] FLASH_SHOW = {
 	    new String[] {"���ֻ������굥��"},
 	    new String[] {"���", "��ҵ����", "ҵ�����", "ҵ������", "URL����ʱ��", "��Ϣ���ݳ���", "ҵ������", "������Ϣ��", "���·�"},
        new String[] {"�굥������", "��Ϣ�ѣ�", "���·ѣ�"}, 
        new String[] {"recordcount", "totalfee1", "totalfee2"}};
     
     /**
      * G3�굥��ʾ�ֶ�
      * 
      */
    private final static Object[] G3GPRS_SHOW = {
 	    new String[] {"��G3�굥��"},
 	    //new String[] {"���", "�����ʶ", "æ��ʱ", "��ʼʱ��", "��������", "��������", "�Ʒ�����", "ҵ������", "�������", "ͨ������", "æʱ����", "��ʱ����"},
        //new String[] {"�굥������", "�Ʒ�������(K)��", "�Ż�������(K)��"},
        //new String[] {"recordcount", "totalgprs1", "totalgprs2"}
        new String[] {"���","�����ʶ","��ʼʱ��","æ/��ʱ","�Ʒ���������","�Ʒ���������","�Ʒ�ʱ��","���ݼƷ�ҵ������","���������������","���������������","ͨ������"},
        new String[] {"�굥������", "������������(K)��", "������������(K)��"},
        new String[] {"recordcount", "totalgprs1", "totalgprs2"}
 	    };
     
     /**
      * ��Ϸ�㿨�굥��ʾ�ֶ�
      * 
      */
    private final static Object[] GAME_SHOW = {
 	    new String[] {"����Ϸ�㿨�굥��"},
 	    new String[] {"���", "ҵ�����", "��ҵ����", "�������", "ҵ�����", "����������", "����ʱ��", "�������ѵ���", "�������ѵ���"},
        new String[] {"�굥������", "��������Ϣ�ѣ�", "���·�"}, 
        new String[] {"recordcount", "totalgame1", "totalgame2"}};
     
    /**
     * �嵥���к�̨����ӿ������飬�ӿ����������е�λ�ú��嵥�����ϰ�ʲô��(����type����ֵ)һһ��Ӧ
     */
    //public static final String[] TYPE_SERVICE_NAME_ARRAY = {"ALL", "GSMCDR", "SMSCDR", "MMSCDR", "IMSGCDR", "GPRSCDR", "WLANCDR", "INFOFEECDR", "VPMNCDR", "LBSCDR"};
    private static final String[] TYPE_SERVICE_NAME_ARRAY = {"ALL", "GSMCDR", "SMSCDR", "IMSGCDR", "GPRSCDR", "WLANCDR", "MMSCDR",
    	                                                    "INFOFEECDR", "VPMNCDR", "PIMMCDR","FLASHCDRKF1","G3GPRSCDR","GAMECDR"};
    
    /**
     * �嵥���ơ��������е�λ�ú��嵥�����ϰ�ʲô��(����type����ֵ)һһ��Ӧ
     */
    //public static final String[] TYPE_NAME_ARRAY = {"ȫ���嵥", "ͨ���嵥", "�����嵥", "�����嵥", "�����嵥", "GPRS�嵥", "WLAN�嵥", "������Ϣ���嵥", "VPMN�嵥", "LBS�嵥"};
    private static final String[] TYPE_NAME_ARRAY = {"ȫ���嵥", "ͨ���嵥", "�����嵥", "�����嵥", "GPRS�嵥", "WLAN�嵥", "�����嵥", 
    	                                            "������Ϣ���嵥", "VPMN�嵥", "PIM�嵥","�ֻ������嵥","G3�嵥","��Ϸ�㿨�嵥"};
    
    /**
     * ��ʾ�ֶ�
     */
    private static final Object[] TYPE_TABLE_DETAIL = {"", GSM_SHOW, SMS_SHOW, ISMG_SHOW, GPRS_SHOW, WLAN_SHOW, MMS_SHOW, 
        INFOFEE_SHOW, VPMN_SHOW, PIMM_SHOW, FLASH_SHOW, G3GPRS_SHOW, GAME_SHOW};
    
    /**
     * ��ӡ�ֶ�
     */
    private static final String[] TYPE_TITLES = {
		"���� �Է�����    ��ʼʱ��    ʱ��  ����  �ص�",     		//"ͨ���嵥",       
		"�Է�����     ͨ����ʼʱ��  ���ŷ�",                       		//"�����嵥",              
		"������� ҵ������ ��ʼʱ��  ���·�  ��Ϣ��",              		//"�����嵥",       
		"���� ��ʼʱ�� �������� �������� ҵ������ ����",         	//"GPRS�嵥",       
		"ͨ����ʼʱ��  ʱ��  ͨ���� ͨ����",                       		//"WLAN�嵥",  
		"�Է�����    ����ʱ��  ͨ�ŷ�  ��Ϣ��  �ܷ���",              		//"�����嵥",     
		"�Է�����     ������    ͨ����ʼʱ��  ʱ��  ����",         		//"������Ϣ���嵥", 
		"�Է�����   ͨ����ʼʱ�� ʱ��  ͨ���ص�  ͨ�ŷ�",        		//"VPMN�嵥",       
		"ҵ������ ��������ʱ��  ����  ���·�" ,                    		//"PIM�嵥",        
		"URL����ʱ�� SPҵ����� SP��ҵ���� ��Ϣ�� ���·�",     		//"�ֻ������嵥",   
		"���� ��ʼʱ�� �������� �������� ҵ������ ����",       		//"G3�嵥",         
		"ҵ�����       ����ʱ��    ���ε��� ���µ���"      		//"��Ϸ�㿨�嵥"    
		};
    
    
    private static final String[] pluginIdArray = {"prtpluginid", "invprtpluginid", "keybrdpluginid", "cashpluginid",
        "cardpluginid", "mgrpluginid", "simcardpluginid", "writecardpluginid", "idcardpluginid", "unionpluginid", "pursepluginid",
        "sellgoodspluginid"};           
    
    private static final String[] pluginKeyArray = {"prtpluginflag", "invprtpluginflag", "keybrdpluginflag",
        "cashpluginflag", "cardpluginflag", "mgrpluginflag", "simcardpluginidflag", "writecardpluginflag", "idcardpluginflag", "unionpluginflag",
        "pursepluginflag", "sellgoodspluginflag"};
    
    /** �������ɷ� */
    public static final String PAY_BYCARD = "PAYTYPE_CARD";
    
    /**
     * �ֽ�ɷ�
     * 
     */
    public static final String PAY_BYCASH = "PAYTYPE_CASH";
    
    //add begin g00140516 2012/01/07 R003C11L12n01 bug 18636
    public static final int MENU_CENTER_MAX = 5;
    //add end g00140516 2012/01/07 R003C11L12n01 bug 18636

    // add begin g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
    /**
     * �ײ���̶���
     */
    private static final String[] CDR_FIXFEE_SHOW = {"�ײͼ��̶����굥", "�۷�ʱ��,ʹ������,�ײͼ��̶�������,����"};
    
    /**
     * ͨ���굥
     */
    private static final String[] CDR_GSM_SHOW = {"ͨ���굥", "��ʼʱ��,ͨ�ŵص�,ͨ�ŷ�ʽ,�Է�����,ͨ��ʱ��,ͨ������,�ײ��Ż�,ͨ�ŷ�"};
        
    /**
     * �̲����굥
     */
    private static final String[] CDR_SMS_SHOW = {"��/�����굥", "��ʼʱ��,ͨ�ŵص�,�Է�����,ͨ�ŷ�ʽ,��Ϣ����,ҵ������,�ײ�,ͨ�ŷ�"};
    
    /**
     * �����굥
     */
    //modify zKF73966 20160831 OR_HUB_201607_558 ����ͳ���굥չʾ���� begin
    //add by xkf57421 for ��ע���� begin
    //private static final String[] CDR_GPRSWLAN_SHOW = {"�����굥", "��ʼʱ��,ͨ�ŵص�,������ʽ,WLAN�Ʒ���ʱ��,WLAN�Ʒ�������"};
    //add by xkf57421 for ��ע���� end
    //private static final String[] CDR_GPRSWLAN_SHOW = {"�����굥", "��ʼʱ��,ͨ�ŵص�,������ʽ,��ʱ��,������,ͳ��ҵ���ͳ���ͻ�,ͳ�����"};
    //modify zKF73966 20160831 OR_HUB_201607_558 ����ͳ���굥չʾ���� end  
    
    //modify rwx368137 20171019 R20170724002�����Ż������嵥��ѯ���ܵ��ؼ����� start
    private static final String[] CDR_GPRSWLAN_SHOW = {"�����굥", "��ʼʱ��,ͨ�ŵص�,Ӧ�����ݼ�������ʽ,��ʱ��,������,ͳ��ҵ���ͳ���ͻ�,ͳ�����"};
    //modify rwx368137 20171019 R20170724002�����Ż������嵥��ѯ���ܵ��ؼ����� end 
    
    /**
     * ��ֵҵ���
     */
    private static final String[] CDR_ISMG_SHOW = {"��ֵҵ��۷Ѽ�¼", "ʱ��,ʹ�÷�ʽ,ҵ������,ҵ��˿�,����"};
    
    /**
     * ���շ�ҵ��۷Ѽ�¼
     */
    private static final String[] CDR_INFOFEE_SHOW = {"���շ�ҵ���¼", "ʱ��,ʹ�÷�ʽ,ҵ������,ҵ��˿�,�����ṩ��,��ҵ����,��������,����"};
    
    /**
     * �����۷Ѽ�¼
     */
    private static final String[] CDR_OTHERFEE_SHOW = {"�����۷Ѽ�¼", "ʱ��,��������,���"};
    
    /**
     * �����굥
     */
    private static final String[] CDR_DISCOUNT_SHOW = {"�˵������¼", "ʱ��,��������,������"};
    
    public static final Map<String, String[]> CDRTYPEMAP = new HashMap<String, String[]>();
    
    static
    {
        CDRTYPEMAP.put(CDRTYPE_FIXFEE, CDR_FIXFEE_SHOW);
        CDRTYPEMAP.put(CDRTYPE_GSM, CDR_GSM_SHOW);
        CDRTYPEMAP.put(CDRTYPE_SMS, CDR_SMS_SHOW);
        CDRTYPEMAP.put(CDRTYPE_GPRSWLAN, CDR_GPRSWLAN_SHOW);
        CDRTYPEMAP.put(CDRTYPE_ISMG, CDR_ISMG_SHOW);
        CDRTYPEMAP.put(CDRTYPE_INFOFEE, CDR_INFOFEE_SHOW);
        CDRTYPEMAP.put(CDRTYPE_OTHERFEE, CDR_OTHERFEE_SHOW);
        CDRTYPEMAP.put(CDRTYPE_DISCOUNT, CDR_DISCOUNT_SHOW);
    }
    // add end g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
    
    //add begin sWX219697 2014-05-09 OR_SD_201404_777 
    //ɽ���û��Ƿ�ͨ���ּƻ��ӿ� ��Ʒid
    private static final String[] SCORE_TITLE_NEW_SD = {"������ۼƻ���", "��ǰ�ɶһ�����"};
    //add end sWX219697 2014-05-09 OR_SD_201404_777
    
    /**
     * ������ϸ��ѯ
     */
    private static final String[] SCOREDETIAL_TITLE={"�û�����","���ԭ��","���ʱ��","��������","��������"};
   
    /**
     * ���ֶһ���ʷ��ѯ(ɽ��)
     */
    private static final String[]SCORECHANGE_TITLE={"�����","��Ʒ����","�һ�ʱ��"};
    
    /**
     * ���ֶһ���ʷ��ѯ(����)
     */
    private static final String[]SCOREPRIZE_TITLE={"�һ�ʱ��","�һ���ϸ��Ʒ","�һ��ص�","�һ�����Ա"};

    // add begin jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 ɽ���ƶ������������ֲ�ѯ����֧������
    // ɽ���ƶ������������ֲ�ѯ����
    public static final String SH_SCOREQRY_SWITCH = "SH_SCOREQRY_SWITCH";
    
    // ɽ�����ֲ�ѯ�±���
    private static final String[] SCORE_NEWTITLE_SD = {"����ֵ","������"};
    
    // ���ַ��Ų�ѯ����ͷ
    private static final String[] PAYMENTSCORE_TITLE = {"����ʱ��","���Ż���","������","��ע"};
    // add end jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 ɽ���ƶ������������ֲ�ѯ����֧������

	public static String[] getScoreTitle() {
		return SCORE_TITLE;
	}

	public static Object[] getGsmShow() {
		return GSM_SHOW;
	}

	public static Object[] getSmsShow() {
		return SMS_SHOW;
	}

	public static Object[] getIsmgShow() {
		return ISMG_SHOW;
	}

	public static Object[] getGprsShow() {
		return GPRS_SHOW;
	}

	public static Object[] getWlanShow() {
		return WLAN_SHOW;
	}

	public static Object[] getMmsShow() {
		return MMS_SHOW;
	}

	public static Object[] getInfofeeShow() {
		return INFOFEE_SHOW;
	}

	public static Object[] getVpmnShow() {
		return VPMN_SHOW;
	}

	public static Object[] getPimmShow() {
		return PIMM_SHOW;
	}

	public static Object[] getFlashShow() {
		return FLASH_SHOW;
	}

	public static Object[] getG3gprsShow() {
		return G3GPRS_SHOW;
	}

	public static Object[] getGameShow() {
		return GAME_SHOW;
	}

	public static String[] getTypeServiceNameArray() {
		return TYPE_SERVICE_NAME_ARRAY;
	}

	public static String[] getTypeNameArray() {
		return TYPE_NAME_ARRAY;
	}

	public static Object[] getTypeTableDetail() {
		return TYPE_TABLE_DETAIL;
	}

	public static String[] getTypeTitles() {
		return TYPE_TITLES;
	}

	public static String[] getPluginidarray() {
		return pluginIdArray;
	}

	public static String[] getPluginkeyarray() {
		return pluginKeyArray;
	}

	public static String[] getCdrFixfeeShow() {
		return CDR_FIXFEE_SHOW;
	}

	public static String[] getCdrGsmShow() {
		return CDR_GSM_SHOW;
	}

	public static String[] getCdrSmsShow() {
		return CDR_SMS_SHOW;
	}

	public static String[] getCdrGprswlanShow() {
		return CDR_GPRSWLAN_SHOW;
	}

	public static String[] getCdrIsmgShow() {
		return CDR_ISMG_SHOW;
	}

	public static String[] getCdrInfofeeShow() {
		return CDR_INFOFEE_SHOW;
	}

	public static String[] getCdrOtherfeeShow() {
		return CDR_OTHERFEE_SHOW;
	}

	public static String[] getCdrDiscountShow() {
		return CDR_DISCOUNT_SHOW;
	}

	public static String[] getScoreTitleNewSd() {
		return SCORE_TITLE_NEW_SD;
	}

	public static String[] getScoredetialTitle() {
		return SCOREDETIAL_TITLE;
	}

	public static String[] getScorechangeTitle() {
		return SCORECHANGE_TITLE;
	}

	public static String[] getScoreprizeTitle() {
		return SCOREPRIZE_TITLE;
	}

	public static String getShScoreqrySwitch() {
		return SH_SCOREQRY_SWITCH;
	}

	public static String[] getScoreNewtitleSd() {
		return SCORE_NEWTITLE_SD;
	}

	public static String[] getPaymentscoreTitle() {
		return PAYMENTSCORE_TITLE;
	}
    
    

}
