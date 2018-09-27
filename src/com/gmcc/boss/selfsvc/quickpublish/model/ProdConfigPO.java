package com.gmcc.boss.selfsvc.quickpublish.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * NCODE���Ʒ����ͨ��Ʒ�Ͳ�Ʒ��������PO
 * 
 * @author cKF76106
 * @version [�汾��, Jun 30, 2012]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class ProdConfigPO implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // ��Ʒ����
    private String ncode = "";
    
    // ����
    private String areaCode = "";
    
    // Ʒ��
    private String brand = "";
    
    // ����
    private String channel = "";
    
    // �����Ʒ
    private String mainProd = "";
    
    // ͬ������ 1������ 2��ɾ�� 3���޸�( �� ���� ���� ��� )
    private String operType = "";
    
    // ��Ʒ���� 1����ͨ��Ʒ 2����Ʒ�� 3��ģ�� 4��ͬ���Ʒ 5�������Ʒ
    private String prodType = "";
    
    // ��Ʒ�汾��Ϊ�����û������̲�Ʒ�������ڲ�Ʒ������ �����������죬���Ӱ汾��Ϣ
    private String version = "";
    
    // �Ƿ��и������� 0���� 1����HAVEAPDATTR
    private String haveApdAttr = "";
    
    // ��Чʱ��
    private String startDate = "";
    
    // ʧЧʱ��
    private String endDate = "";
    
    // ״̬
    private String status = "";
    
    // ��������
    private String dealDesc = "";
    
    // �ʷ�����
    private String feeDesc = "";
    
    // ҵ�����
    private String description = "";
    
    // �Ƿ�Ϊ�Ƽ���Ʒ��1Ϊ�Ƽ���Ʒ��0�����Ƽ���Ʒ
    private String isRec = "";
    
    // ��ܰ��ʾ��ҵ�񷢲�ʱ������Ա�Լ��ֹ����롣��
    private String reminder = "";
    
    // ״̬ʱ��
    private String statusDate = "";
    
    // ��ƷͼƬ·��
    private String imgPath = "";
    
    // ��Ʒ����
    private String prodName = "";
    
    // ��Ч��ʽ
    private String effectType = "";
    
    // ��ͨ��Ч��ʽ
    private String effectRec = "";
    
    // �����Ч��ʽ
    private String effectMod = "";
    
    // �˶���Ч��ʽ
    private String effectDel = "";
        
    // ��ͨ��Ʒ�µĸ�������
    private List<AddAttrPO> addAttrPOList = new ArrayList<AddAttrPO>();
    
    // ��Ʒ�����Ӳ�ƷPO
    private ChildProdPO childProdPO = new ChildProdPO();
    
    // ��Ʒ�Ƿ��ѿ�ͨ��1���ѿ�ͨ
    private String isOpen = "";
    
    // ��Ʒ�Ƿ���԰��� 1�����԰���
    private String isSupportRec = "";
    
    // ��Ʒ����
    private String typeID = "";
    
    // �Ƿ��ǲ�Ʒ���� 1����
    private String isTypeID = "";
    
    // ��Ʒ����PO
    private ProdTypePO prodTypePO =  new ProdTypePO();
    
    
    public String getNcode()
    {
        return ncode;
    }
    
    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }
    
    public String getAreaCode()
    {
        return areaCode;
    }
    
    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }
    
    public String getBrand()
    {
        return brand;
    }
    
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    
    public String getChannel()
    {
        return channel;
    }
    
    public void setChannel(String channel)
    {
        this.channel = channel;
    }
    
    public String getMainProd()
    {
        return mainProd;
    }
    
    public void setMainProd(String mainProd)
    {
        this.mainProd = mainProd;
    }
    
    public String getOperType()
    {
        return operType;
    }
    
    public void setOperType(String operType)
    {
        this.operType = operType;
    }
    
    public String getProdType()
    {
        return prodType;
    }
    
    public void setProdType(String prodType)
    {
        this.prodType = prodType;
    }
    
    public String getVersion()
    {
        return version;
    }
    
    public void setVersion(String version)
    {
        this.version = version;
    }
    
    public String getHaveApdAttr()
    {
        return haveApdAttr;
    }
    
    public void setHaveApdAttr(String haveApdAttr)
    {
        this.haveApdAttr = haveApdAttr;
    }
    
    public String getStartDate()
    {
        return startDate;
    }
    
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    
    public String getEndDate()
    {
        return endDate;
    }
    
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String getDealDesc()
    {
        return dealDesc;
    }
    
    public void setDealDesc(String dealDesc)
    {
        this.dealDesc = dealDesc;
    }
    
    public String getFeeDesc()
    {
        return feeDesc;
    }
    
    public void setFeeDesc(String feeDesc)
    {
        this.feeDesc = feeDesc;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getIsRec()
    {
        return isRec;
    }
    
    public void setIsRec(String isRec)
    {
        this.isRec = isRec;
    }
    
    public String getReminder()
    {
        return reminder;
    }
    
    public void setReminder(String reminder)
    {
        this.reminder = reminder;
    }
    
    public String getStatusDate()
    {
        return statusDate;
    }
    
    public void setStatusDate(String statusDate)
    {
        this.statusDate = statusDate;
    }
    
    public String getImgPath()
    {
        return imgPath;
    }
    
    public void setImgPath(String imgPath)
    {
        this.imgPath = imgPath;
    }
    
    public String getProdName()
    {
        return prodName;
    }
    
    public void setProdName(String prodName)
    {
        this.prodName = prodName;
    }
    
    public List<AddAttrPO> getAddAttrPOList()
    {
        return addAttrPOList;
    }
    
    public void setAddAttrPOList(List<AddAttrPO> addAttrPOList)
    {
        this.addAttrPOList = addAttrPOList;
    }
    
    public ChildProdPO getChildProdPO()
    {
        return childProdPO;
    }
    
    public void setChildProdPO(ChildProdPO childProdPO)
    {
        this.childProdPO = childProdPO;
    }

    public String getEffectType()
    {
        return effectType;
    }

    public void setEffectType(String effectType)
    {
        this.effectType = effectType;
    }

    public String getEffectRec()
    {
        return effectRec;
    }

    public void setEffectRec(String effectRec)
    {
        this.effectRec = effectRec;
    }

    public String getEffectMod()
    {
        return effectMod;
    }

    public void setEffectMod(String effectMod)
    {
        this.effectMod = effectMod;
    }

    public String getEffectDel()
    {
        return effectDel;
    }

    public void setEffectDel(String effectDel)
    {
        this.effectDel = effectDel;
    }

    public String getIsOpen()
    {
        return isOpen;
    }

    public String getIsSupportRec()
    {
        return isSupportRec;
    }

    public void setIsOpen(String isOpen)
    {
        this.isOpen = isOpen;
    }

    public void setIsSupportRec(String isSupportRec)
    {
        this.isSupportRec = isSupportRec;
    }

    public String getTypeID()
    {
        return typeID;
    }

    public String getIsTypeID()
    {
        return isTypeID;
    }

    public void setTypeID(String typeID)
    {
        this.typeID = typeID;
    }

    public void setIsTypeID(String isTypeID)
    {
        this.isTypeID = isTypeID;
    }

    public ProdTypePO getProdTypePO()
    {
        return prodTypePO;
    }

    public void setProdTypePO(ProdTypePO prodTypePO)
    {
        this.prodTypePO = prodTypePO;
    }


    
}
