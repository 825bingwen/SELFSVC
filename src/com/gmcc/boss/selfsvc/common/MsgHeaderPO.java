package com.gmcc.boss.selfsvc.common;

/**
 * ���ýӿ�ʱ��������ͷʵ����
 * 
 * @author  zKF69263
 * @version  [�汾��, 2014-8-19]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MsgHeaderPO extends BasePO
{
    /**
     * ע������
     */
    private static final long serialVersionUID = -4056115091916833283L;
    
    /**
     * ·������:region
     */
    public static final String ROUTETYPE_REGION = "0";
    
    /**
     * ·������:�ֻ�����
     */
    public static final String ROUTETYPE_TELNUM = "1";
    
    /**
     * ���캯��
     */
    public MsgHeaderPO(String menuId, String operId, String termId, String uniContact, String routeType, String region,
        String telNum, String verifyCode, String processCode)
    {
        super();
        this.menuId = menuId;
        this.operId = operId;
        this.termId = termId;
        this.uniContact = uniContact;
        this.routeType = routeType;
        this.routeValue = ROUTETYPE_REGION.equals(routeType) ? region : telNum;
        this.region = region;
        this.telNum = telNum;
        this.verifyCode = verifyCode;
        this.processCode = processCode;
    }
    
    /**
     * ���캯��
     */
    public MsgHeaderPO(String menuId, String operId, String termId, String uniContact, String routeType,
        String routeValue, String verifyCode, String processCode)
    {
        this(menuId, operId, termId, uniContact, routeType, ROUTETYPE_REGION.equals(routeType) ? routeValue : "", 
            ROUTETYPE_TELNUM.equals(routeType) ? routeValue : "", verifyCode, processCode);
    }
    
    /**
     * ���캯��
     */
    public MsgHeaderPO(String menuId, String operId, String termId, String uniContact, String routeType,
        String routeValue, String verifyCode)
    {
        this(menuId, operId, termId, uniContact, routeType, routeValue, verifyCode, null);
    }
    
    /**
     * ���캯��
     */
    public MsgHeaderPO(String menuId, String operId, String termId, String uniContact, String routeType, String routeValue)
    {
        this(menuId, operId, termId, uniContact, routeType, routeValue, null);
    }
    
    /**
     * ҵ����룬��Ӧcrm��UAP��businessid
     */
    private String processCode;
    
    /**
     * �˵�ID
     */
    private String menuId;
    
    /**
     * ͳһ�Ӵ���ˮ��������֤�ӿڷ���
     */
    private String uniContact;
    
    /**
     * ·������: 0 ������·��   1 ���ֻ�����·��
     */
    private String routeType;
    
    /**
     * ·��ֵ
     */
    private String routeValue;
    
    /**
     * ����ԱID
     */
    private String operId;
    
    /**
     * �ն�Id
     */
    private String termId;
    
    /**
     * ����Ա�������б���
     */
    private String region;
    
    /**
     * �û��ֻ�����
     */
    private String telNum;
    
    /**
     * ��������
     */
    private String channelId = "bsacAtsv";
    
    /**
     * У���룬������֤�ӿڷ���
     */
    private String verifyCode;
    
    /**
     * ���Ա�� ����Ϊ�գ�0��ʾ����Ϊ�����ã�1��ʾ��ʽ����
     */
    private String testFlag = "";
    
    /**
     * �����¼���λ��Ϣ
     */
    private String unitId = "";

    /**
     * @return ���� processCode
     */
    public String getProcessCode()
    {
        return processCode;
    }

    /**
     * @param ��processCode���и�ֵ
     */
    public void setProcessCode(String processCode)
    {
        this.processCode = processCode;
    }

    /**
     * @return ���� menuId
     */
    public String getMenuId()
    {
        return menuId;
    }

    /**
     * @param ��menuId���и�ֵ
     */
    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }

    /**
     * @return ���� uniContact
     */
    public String getUniContact()
    {
        return uniContact;
    }

    /**
     * @param ��uniContact���и�ֵ
     */
    public void setUniContact(String uniContact)
    {
        this.uniContact = uniContact;
    }

    /**
     * @return ���� routeType
     */
    public String getRouteType()
    {
        return routeType;
    }

    /**
     * @param ��routeType���и�ֵ
     */
    public void setRouteType(String routeType)
    {
        this.routeType = routeType;
    }

    /**
     * @return ���� operId
     */
    public String getOperId()
    {
        return operId;
    }

    /**
     * @param ��operId���и�ֵ
     */
    public void setOperId(String operId)
    {
        this.operId = operId;
    }

    /**
     * @return ���� termId
     */
    public String getTermId()
    {
        return termId;
    }

    /**
     * @param ��termId���и�ֵ
     */
    public void setTermId(String termId)
    {
        this.termId = termId;
    }

    /**
     * @return ���� region
     */
    public String getRegion()
    {
        return region;
    }

    /**
     * @param ��region���и�ֵ
     */
    public void setRegion(String region)
    {
        this.region = region;
    }

    /**
     * @return ���� channelId
     */
    public String getChannelId()
    {
        return channelId;
    }

    /**
     * @param ��channelId���и�ֵ
     */
    public void setChannelId(String channelId)
    {
        this.channelId = channelId;
    }

    /**
     * @return ���� telNum
     */
    public String getTelNum()
    {
        return telNum;
    }

    /**
     * @param ��telNum���и�ֵ
     */
    public void setTelNum(String telNum)
    {
        this.telNum = telNum;
    }

    /**
     * @return ���� routeValue
     */
    public String getRouteValue()
    {
        return routeValue;
    }

    /**
     * @param ��routeValue���и�ֵ
     */
    public void setRouteValue(String routeValue)
    {
        this.routeValue = routeValue;
    }

    /**
     * @return ���� verifyCode
     */
    public String getVerifyCode()
    {
        return verifyCode;
    }

    /**
     * @param ��verifyCode���и�ֵ
     */
    public void setVerifyCode(String verifyCode)
    {
        this.verifyCode = verifyCode;
    }

    /**
     * @return ���� testFlag
     */
    public String getTestFlag()
    {
        return testFlag;
    }

    /**
     * @param ��testFlag���и�ֵ
     */
    public void setTestFlag(String testFlag)
    {
        this.testFlag = testFlag;
    }

    /**
     * @return ���� unitId
     */
    public String getUnitId()
    {
        return unitId;
    }

    /**
     * @param ��unitId���и�ֵ
     */
    public void setUnitId(String unitId)
    {
        this.unitId = unitId;
    }
}
