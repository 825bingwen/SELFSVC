package com.customize.hub.selfsvc.telProdDiy.model;

public class TelProdPO
{
    
    private String goods_id;
    
    // 1������ͨ����2����;ͨ����3��GPRS��4��WLAN��5�����ݲ�Ʒ
    private String goods_type;
    
    private String itemID;
    
    private String ncode;
    
    // ��ֵ��ƷID
    private String addProdID;
    
    // ��Ʒ��ID
    private String packageID;
    
    // �Ż�ID
    private String privID;
    
    // ��ҵ����
    private String spID;
    
    // spҵ�����
    private String spBusID;
    
    // ҵ��˵��
    private String busDesc;
    
    // �ʺ���Ⱥ
    private String suitAble_people;
    
    // �ʷѱ�׼
    private String feestandard;
    
    // ���Ű���ʽ
    private String message_way;
    
    // ��Чʱ��
    private String eff_date;
    
    // ʧЧʱ��
    private String exp_date;
    
    // �ʷ�����
    private String fee_data;
    
    // �ʷ����ݵ�λ
    private String unit;
    
    // �۸�
    private String price;
    
    // ״̬�ı�ʱ��
    private String statusDate;
    
    // ״̬ 1�����ã�0������
    private String status;
    
    // ����Ʒ��
    private String belongBrands;
    
    // �����ײͷ��� 1,2
    private String belong_package;
    
    // ��������,�����Ҫ���ֵ�������ֵ����������ö���(,)����
    private String belong_region;
    
    // �ʷ�����
    private String item_name;
    
    // �������
    private String sort_order;
    
    // ��ע
    private String remark;
    
    // ��ѯ���� MAINPROD, ����ͨ��; GPRSWLAN, ����������SP, ����ҵ��
    private String qryType;
    
    // �ײ�����
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
