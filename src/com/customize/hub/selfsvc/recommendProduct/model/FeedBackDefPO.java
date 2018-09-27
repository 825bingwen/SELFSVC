package com.customize.hub.selfsvc.recommendProduct.model;

import java.io.Serializable;

/**
 * �����Ƽ���Ϣ����PO
 * 
 * @author  zKF69263
 * @version  [�汾��, 2014-7-4]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class FeedBackDefPO implements Serializable {
    
    /**
     * ע������
     */
    private static final long serialVersionUID = -1102155931126868298L;

    /**
     * �Ƽ�����
     */
    private String recmdName;

    /**
     * �Ƽ�ncode
     */
    private String ncode;
    
    /**
     * �Ƽ����Ϣ
     */
    private String actInfo;
    
    /**
     * ����
     */
    private String moContent;
    
    /**
     * Ӫ�������
     */
    private String actId;
    
    /**
     * �ֻ�����
     */
    private String servNumber;

    /**
     * @return ���� recmdName
     */
    public String getRecmdName()
    {
        return recmdName;
    }

    /**
     * @param ��recmdName���и�ֵ
     */
    public void setRecmdName(String recmdName)
    {
        this.recmdName = recmdName;
    }

    /**
     * @return ���� ncode
     */
    public String getNcode()
    {
        return ncode;
    }

    /**
     * @param ��ncode���и�ֵ
     */
    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }

    /**
     * @return ���� actInfo
     */
    public String getActInfo()
    {
        return actInfo;
    }

    /**
     * @param ��actInfo���и�ֵ
     */
    public void setActInfo(String actInfo)
    {
        this.actInfo = actInfo;
    }

    /**
     * @return ���� moContent
     */
    public String getMoContent()
    {
        return moContent;
    }

    /**
     * @param ��moContent���и�ֵ
     */
    public void setMoContent(String moContent)
    {
        this.moContent = moContent;
    }

    /**
     * @return ���� actId
     */
    public String getActId()
    {
        return actId;
    }

    /**
     * @param ��actId���и�ֵ
     */
    public void setActId(String actId)
    {
        this.actId = actId;
    }

    /**
     * @return ���� servNumber
     */
    public String getServNumber()
    {
        return servNumber;
    }

    /**
     * @param ��servNumber���и�ֵ
     */
    public void setServNumber(String servNumber)
    {
        this.servNumber = servNumber;
    }
}
