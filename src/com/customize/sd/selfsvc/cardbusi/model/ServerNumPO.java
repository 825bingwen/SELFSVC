/*
 * 文 件 名:  ServerNumPO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  选择服务号码
 * 修 改 人:  zKF69263
 * 修改时间:  2014-12-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.cardbusi.model;

/**
 * 选择服务号码
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-12-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ServerNumPO
{
    /**
     * 单位（预约时使用）
     */
    private String orgId;
    
    /**
     * 地市（预约时使用）
     */
    private String region;
    
    /**
     * 手机号码
     */
    private String telnum;
    
    /**
     * 品牌信息
     */
    private String brand;
    
    /**
     * 号码级别，即选号费，单位：分
     */
    private String fee;
    
    /**
     * 最低消费
     */
    private String lowConsumFee;
    
    /**
     * 预存话费
     */
    private String lowConsumPre;
    
    /**
     * 签约时长
     */
    private String lowInserviceTime;
    
    //add begin sWX219697 2015-6-9 OR_SD_201505_489_开户中增加预约选号功能
    /**
     * 预约号码到期时间
     */
    private String deadline;
    
    /**
     * 证件类型
     */
    private String idType;
    
    /**
     * 证件号码
     */
    private String idCardNo;
    
    /**
     * 预约费用，元
     */
    private String bookFee;
    //add end sWX219697 2015-6-9 OR_SD_201505_489_开户中增加预约选号功能

    
    /**
     * @return 返回 orgId
     */
    public String getOrgId()
    {
        return orgId;
    }

    /**
     * @param 对orgId进行赋值
     */
    public void setOrgId(String orgId)
    {
        this.orgId = orgId;
    }

    /**
     * @return 返回 region
     */
    public String getRegion()
    {
        return region;
    }

    /**
     * @param 对region进行赋值
     */
    public void setRegion(String region)
    {
        this.region = region;
    }

    /**
     * @return 返回 telnum
     */
    public String getTelnum()
    {
        return telnum;
    }

    /**
     * @param 对telnum进行赋值
     */
    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }

    /**
     * @return 返回 fee
     */
    public String getFee()
    {
        return fee;
    }

    /**
     * @param 对fee进行赋值
     */
    public void setFee(String fee)
    {
        this.fee = fee;
    }
    
    /**
     * @return 返回 lowConsumFee
     */
    public String getLowConsumFee()
    {
        return lowConsumFee;
    }

    /**
     * @param 对lowConsumFee进行赋值
     */
    public void setLowConsumFee(String lowConsumFee)
    {
        this.lowConsumFee = lowConsumFee;
    }

    /**
     * @return 返回 lowConsumPre
     */
    public String getLowConsumPre()
    {
        return lowConsumPre;
    }

    /**
     * @param 对lowConsumPre进行赋值
     */
    public void setLowConsumPre(String lowConsumPre)
    {
        this.lowConsumPre = lowConsumPre;
    }

    /**
     * @return 返回 lowInserviceTime
     */
    public String getLowInserviceTime()
    {
        return lowInserviceTime;
    }

    /**
     * @param 对lowInserviceTime进行赋值
     */
    public void setLowInserviceTime(String lowInserviceTime)
    {
        this.lowInserviceTime = lowInserviceTime;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getDeadline()
    {
        return deadline;
    }

    public void setDeadline(String deadline)
    {
        this.deadline = deadline;
    }

    public String getIdType()
    {
        return idType;
    }

    public void setIdType(String idType)
    {
        this.idType = idType;
    }

    public String getIdCardNo()
    {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo)
    {
        this.idCardNo = idCardNo;
    }

    public String getBookFee()
    {
        return bookFee;
    }

    public void setBookFee(String bookFee)
    {
        this.bookFee = bookFee;
    }
    
}
