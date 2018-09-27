/*
 * �ļ�����RegionInfoPO.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ�����
 */
package com.gmcc.boss.selfsvc.resdata.model;

import com.gmcc.boss.selfsvc.common.BasePO;

/**
 * region
 * 
 * @author g00140516
 * 
 */
public class RegionInfoPO extends BasePO
{
    private static final long serialVersionUID = -1369776060032878131L;
    
    private String region;
    
    private String regionname;
    
    private String orgid;
    
    private String city_id;
    
    public String getRegionname()
    {
        return regionname;
    }
    
    public String getOrgid()
    {
        return orgid;
    }
    
    public void setOrgid(String orgid)
    {
        this.orgid = orgid;
    }
    
    public void setRegionname(String regionname)
    {
        this.regionname = regionname;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getCity_id()
    {
        return city_id;
    }

    public void setCity_id(String city_id)
    {
        this.city_id = city_id;
    }
}
