package com.gmcc.boss.selfsvc.quickpublish.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * NCODE类产品（普通产品和产品包）配置PO
 * 
 * @author cKF76106
 * @version [版本号, Jun 30, 2012]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ProdConfigPO implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // 产品编码
    private String ncode = "";
    
    // 地市
    private String areaCode = "";
    
    // 品牌
    private String brand = "";
    
    // 渠道
    private String channel = "";
    
    // 主体产品
    private String mainProd = "";
    
    // 同步类型 1：新增 2：删除 3：修改( 即 上线 下线 变更 )
    private String operType = "";
    
    // 产品类型 1：普通产品 2：产品包 3：模板 4、同组产品 5、主体产品
    private String prodType = "";
    
    // 产品版本，为减少用户长流程产品受理由于产品上下线 引起的受理差异，增加版本信息
    private String version = "";
    
    // 是否有附加属性 0：无 1：有HAVEAPDATTR
    private String haveApdAttr = "";
    
    // 生效时间
    private String startDate = "";
    
    // 失效时间
    private String endDate = "";
    
    // 状态
    private String status = "";
    
    // 办理描述
    private String dealDesc = "";
    
    // 资费描述
    private String feeDesc = "";
    
    // 业务介绍
    private String description = "";
    
    // 是否为推荐产品（1为推荐产品；0不是推荐产品
    private String isRec = "";
    
    // 温馨提示（业务发布时，操作员自己手工加入。）
    private String reminder = "";
    
    // 状态时间
    private String statusDate = "";
    
    // 产品图片路径
    private String imgPath = "";
    
    // 产品名称
    private String prodName = "";
    
    // 生效方式
    private String effectType = "";
    
    // 开通生效方式
    private String effectRec = "";
    
    // 变更生效方式
    private String effectMod = "";
    
    // 退订生效方式
    private String effectDel = "";
        
    // 普通产品下的附加属性
    private List<AddAttrPO> addAttrPOList = new ArrayList<AddAttrPO>();
    
    // 产品包下子产品PO
    private ChildProdPO childProdPO = new ChildProdPO();
    
    // 产品是否已开通，1：已开通
    private String isOpen = "";
    
    // 产品是否可以办理 1：可以办理
    private String isSupportRec = "";
    
    // 产品大类
    private String typeID = "";
    
    // 是否是产品大类 1：是
    private String isTypeID = "";
    
    // 产品大类PO
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
