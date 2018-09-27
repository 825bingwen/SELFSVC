package com.customize.hub.selfsvc.telProdDiy.model;

public class TelProdPO
{
    
    private String goods_id;
    
    // 1：本地通话，2：长途通话，3：GPRS，4：WLAN，5：数据产品
    private String goods_type;
    
    private String itemID;
    
    private String ncode;
    
    // 增值产品ID
    private String addProdID;
    
    // 产品包ID
    private String packageID;
    
    // 优惠ID
    private String privID;
    
    // 企业代码
    private String spID;
    
    // sp业务代码
    private String spBusID;
    
    // 业务说明
    private String busDesc;
    
    // 适合人群
    private String suitAble_people;
    
    // 资费标准
    private String feestandard;
    
    // 短信办理方式
    private String message_way;
    
    // 生效时间
    private String eff_date;
    
    // 失效时间
    private String exp_date;
    
    // 资费数据
    private String fee_data;
    
    // 资费数据单位
    private String unit;
    
    // 价格
    private String price;
    
    // 状态改变时间
    private String statusDate;
    
    // 状态 1：启用，0：禁用
    private String status;
    
    // 所属品牌
    private String belongBrands;
    
    // 所属套餐方案 1,2
    private String belong_package;
    
    // 所属地市,如果需要区分地市则置值；多个地市用逗号(,)隔开
    private String belong_region;
    
    // 资费名称
    private String item_name;
    
    // 排序序号
    private String sort_order;
    
    // 备注
    private String remark;
    
    // 查询类型 MAINPROD, 语音通话; GPRSWLAN, 上网流量；SP, 数据业务
    private String qryType;
    
    // 套餐描述
    private String tcDesc;

    public String getGoods_id()
    {
        return goods_id;
    }

    public String getGoods_type()
    {
        return goods_type;
    }

    public String getItemID()
    {
        return itemID;
    }

    public String getNcode()
    {
        return ncode;
    }

    public String getAddProdID()
    {
        return addProdID;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPackageID()
    {
        return packageID;
    }

    public String getPrivID()
    {
        return privID;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getSpID()
    {
        return spID;
    }

    public String getSpBusID()
    {
        return spBusID;
    }

    public String getBusDesc()
    {
        return busDesc;
    }

    public String getSuitAble_people()
    {
        return suitAble_people;
    }

    public String getFeestandard()
    {
        return feestandard;
    }

    public String getMessage_way()
    {
        return message_way;
    }

    public String getEff_date()
    {
        return eff_date;
    }

    public String getExp_date()
    {
        return exp_date;
    }

    public String getFee_data()
    {
        return fee_data;
    }

    public String getUnit()
    {
        return unit;
    }

    public String getPrice()
    {
        return price;
    }

    public String getStatusDate()
    {
        return statusDate;
    }

    public String getStatus()
    {
        return status;
    }

    public String getBelongBrands()
    {
        return belongBrands;
    }

    public String getBelong_package()
    {
        return belong_package;
    }

    public String getBelong_region()
    {
        return belong_region;
    }

    public String getItem_name()
    {
        return item_name;
    }

    public String getSort_order()
    {
        return sort_order;
    }

    public String getRemark()
    {
        return remark;
    }

    public String getQryType()
    {
        return qryType;
    }

    public void setGoods_id(String goods_id)
    {
        this.goods_id = goods_id;
    }

    public void setGoods_type(String goods_type)
    {
        this.goods_type = goods_type;
    }

    public void setItemID(String itemID)
    {
        this.itemID = itemID;
    }

    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }

    public void setAddProdID(String addProdID)
    {
        this.addProdID = addProdID;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPackageID(String packageID)
    {
        this.packageID = packageID;
    }

    public void setPrivID(String privID)
    {
        this.privID = privID;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setSpID(String spID)
    {
        this.spID = spID;
    }

    public void setSpBusID(String spBusID)
    {
        this.spBusID = spBusID;
    }

    public void setBusDesc(String busDesc)
    {
        this.busDesc = busDesc;
    }

    public void setSuitAble_people(String suitAble_people)
    {
        this.suitAble_people = suitAble_people;
    }

    public void setFeestandard(String feestandard)
    {
        this.feestandard = feestandard;
    }

    public void setMessage_way(String message_way)
    {
        this.message_way = message_way;
    }

    public void setEff_date(String eff_date)
    {
        this.eff_date = eff_date;
    }

    public void setExp_date(String exp_date)
    {
        this.exp_date = exp_date;
    }

    public void setFee_data(String fee_data)
    {
        this.fee_data = fee_data;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public void setStatusDate(String statusDate)
    {
        this.statusDate = statusDate;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setBelongBrands(String belongBrands)
    {
        this.belongBrands = belongBrands;
    }

    public void setBelong_package(String belong_package)
    {
        this.belong_package = belong_package;
    }

    public void setBelong_region(String belong_region)
    {
        this.belong_region = belong_region;
    }

    public void setItem_name(String item_name)
    {
        this.item_name = item_name;
    }

    public void setSort_order(String sort_order)
    {
        this.sort_order = sort_order;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public void setQryType(String qryType)
    {
        this.qryType = qryType;
    }

    public String getTcDesc()
    {
        return tcDesc;
    }

    public void setTcDesc(String tcDesc)
    {
        this.tcDesc = tcDesc;
    }
    
    
}
