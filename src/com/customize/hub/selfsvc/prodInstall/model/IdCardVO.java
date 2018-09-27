package com.customize.hub.selfsvc.prodInstall.model;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.idCardBook.action.IdCardBookAction;
/*
* @filename: IdCardVO.java
* @  ��Ȩ
* @brif:  ����ѡ�ſ���
* @author: yedengchu
* @de: ��2012/07/12�� 
* @description: 
* @remark: create bp6Xdpvh OR_HUB_201202_1192
*/
public class IdCardVO implements Serializable

{
    // ���л�
    private static final long serialVersionUID = 1L;
    
    // ��־
    private static Log logger = LogFactory.getLog(IdCardVO.class);
    // ����(��ʾ��)
    private String idCardName = "";
    
    // �Ա���ʾ�ã�
    private String idCardSex = "";
    
    // ���֤��(��ʾ��)
    private String idCardNo = "";
    
    //֤����ַ
    private String idCardAddr;
    
    //֤����ʼʱ��
    private String idCardStartTime;
    
    //֤������ʱ��
    private String idCardEndTime;
    
    // 0����~��~����~����~סַ~37061218840530402X~ǩ������~��Ч����ʼ����~��Ч�ڽ�ֹ����~����סַ
    private String idCardContent = "";

    public String getIdCardName()
    {
        return idCardName;
    }

    public void setIdCardName(String idCardName)
    {
        this.idCardName = idCardName;
    }

    public String getIdCardSex()
    {
        return idCardSex;
    }

    public void setIdCardSex(String idCardSex)
    {
        this.idCardSex = idCardSex;
    }

    public String getIdCardNo()
    {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo)
    {
        this.idCardNo = idCardNo;
    }

    public String getIdCardAddr()
    {
        return idCardAddr;
    }

    public void setIdCardAddr(String idCardAddr)
    {
        this.idCardAddr = idCardAddr;
    }

    public String getIdCardStartTime()
    {
        return idCardStartTime;
    }

    public void setIdCardStartTime(String idCardStartTime)
    {
        this.idCardStartTime = idCardStartTime;
    }

    public String getIdCardEndTime()
    {
        return idCardEndTime;
    }

    public void setIdCardEndTime(String idCardEndTime)
    {
        this.idCardEndTime = idCardEndTime;
    }

    public String getIdCardContent()
    {
        return idCardContent;
    }

    public void setIdCardContent(String idCardContent)
    {
        this.idCardContent = idCardContent;
    }
    
    
}
