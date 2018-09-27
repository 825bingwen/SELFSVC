package com.customize.hub.selfsvc.recommendProduct.model;

/**
 * ����Ӫ���Ƽ��PO
 * 
 * @author cKF76106
 * @version [�汾��, Aug 22, 2012]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class RecommendProductPO
{
    // ���л�
    private static final long serialVersionUID = 1L;
    
    // �Ƽ���ˮ��
    private String commendOID = "";
    
    // �Ƽ���ҵ������
    private String busiName = "";
    
    // Ӫ������
    private String actDict = "";
    
    // �ӿ�NCODE����
    private String ncode = "";
    
    // ��ֵ��Ʒ����
    private String prodID = "";
    
    // �Żݱ���
    private String privID = "";
    
    // �û����к�
    private String userSeq = "";
    
    // ���Ŷ˿�
    private String smsPort = "";
    
    // ����ģ���
    private String smsTempletID = "";
    
    // �Ƽ�ʱ���
    private String commendTimeTable = "";
    
    // Ӫ�������
    private String actID = "";
    
    // Ӫ�������
    private String actName = "";
    
    // ���Ƽ�����
    private String commendTime = "";
    
    // ��ע:�������(�����)
    private String actContent = "";
    
    // ��ֵ��Ʒ����
    private String prodName = "";
    
    // �Ż�����
    private String privName = "";
    
    // �Ƽ�����
    private String commendType = "";
    
    /**
     * �Ƿ��лظ���Ϣ����
     */
    private String isFeedBackDef;
    
    /**
     * �¼��Ƽ�����
     */
    private String eventType;
    
    public String getActDict()
    {
        return actDict;
    }
    
    public void setActDict(String actDict)
    {
        this.actDict = actDict;
    }
    
    public String getNcode()
    {
        return ncode;
    }
    
    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }
    
    public String getProdID()
    {
        return prodID;
    }
    
    public void setProdID(String prodID)
    {
        this.prodID = prodID;
    }
    
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPrivID()
    {
        return privID;
    }
    
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPrivID(String privID)
    {
        this.privID = privID;
    }
    
    public String getUserSeq()
    {
        return userSeq;
    }
    
    public void setUserSeq(String userSeq)
    {
        this.userSeq = userSeq;
    }
    
    public String getSmsPort()
    {
        return smsPort;
    }
    
    public void setSmsPort(String smsPort)
    {
        this.smsPort = smsPort;
    }
    
    public String getSmsTempletID()
    {
        return smsTempletID;
    }
    
    public void setSmsTempletID(String smsTempletID)
    {
        this.smsTempletID = smsTempletID;
    }
    
    public String getActID()
    {
        return actID;
    }
    
    public void setActID(String actID)
    {
        this.actID = actID;
    }
    
    public String getActName()
    {
        return actName;
    }
    
    public void setActName(String actName)
    {
        this.actName = actName;
    }
    
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPrivName()
    {
        return privName;
    }
    
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPrivName(String privName)
    {
        this.privName = privName;
    }
    
    public String getActContent()
    {
        return actContent;
    }
    
    public void setActContent(String actContent)
    {
        this.actContent = actContent;
    }
    
    public String getBusiName()
    {
        return busiName;
    }
    
    public void setBusiName(String busiName)
    {
        this.busiName = busiName;
    }
    
    public String getProdName()
    {
        return prodName;
    }
    
    public void setProdName(String prodName)
    {
        this.prodName = prodName;
    }

    public String getCommendOID()
    {
        return commendOID;
    }

    public String getCommendTimeTable()
    {
        return commendTimeTable;
    }

    public String getCommendTime()
    {
        return commendTime;
    }

    public String getCommendType()
    {
        return commendType;
    }

    public void setCommendOID(String commendOID)
    {
        this.commendOID = commendOID;
    }

    public void setCommendTimeTable(String commendTimeTable)
    {
        this.commendTimeTable = commendTimeTable;
    }

    public void setCommendTime(String commendTime)
    {
        this.commendTime = commendTime;
    }

    public void setCommendType(String commendType)
    {
        this.commendType = commendType;
    }

    /**
     * @return ���� isFeedBackDef
     */
    public String getIsFeedBackDef()
    {
        return isFeedBackDef;
    }

    /**
     * @param ��isFeedBackDef���и�ֵ
     */
    public void setIsFeedBackDef(String isFeedBackDef)
    {
        this.isFeedBackDef = isFeedBackDef;
    }

    /**
     * @return ���� eventType
     */
    public String getEventType()
    {
        return eventType;
    }

    /**
     * @param ��eventType���и�ֵ
     */
    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }
}
