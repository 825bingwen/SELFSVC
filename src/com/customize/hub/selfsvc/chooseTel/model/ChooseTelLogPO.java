package com.customize.hub.selfsvc.chooseTel.model;

/**
 * 选号预约PO
 * 
 * @author lWX431760
 * @version [版本号, Jan 9, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ChooseTelLogPO
{
    
    private static final long serialVersionUID = 1L;
    
    // 日志流水号
    private String oid;
    
    // 地市
    private String region;
    
    // 证件号码
    private String idCard;
    
    // 预约号码
    private String telNum;
    
    // 预约结果：0成功，1失败
    private String subResult;
    
    // 预约结果描述
    private String subResultDesc;
    
    // 验证码
    private String randomCode;
    
    // 失效时间
    private String vidateTime;
    
    // 需预约金额
    private String payfee;
    
    // 选号营业厅
    private String orgId;
    
    // 选号工号
    private String operId;
    
    // 预约终端
    private String termId;
    
    // 预约时间
    private String subscribeTime;
    
    public String getOid()
    {
        return oid;
    }
    
    public void setOid(String oid)
    {
        this.oid = oid;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public void setRegion(String region)
    {
        this.region = region;
    }
    
    public String getIdCard()
    {
        return idCard;
    }
    
    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }
    
    public String getTelNum()
    {
        return telNum;
    }
    
    public void setTelNum(String telNum)
    {
        this.telNum = telNum;
    }
    
    public String getSubResult()
    {
        return subResult;
    }
    
    public void setSubResult(String subResult)
    {
        this.subResult = subResult;
    }
    
    public String getSubResultDesc()
    {
        return subResultDesc;
    }
    
    public void setSubResultDesc(String subResultDesc)
    {
        this.subResultDesc = subResultDesc;
    }
    
    public String getRandomCode()
    {
        return randomCode;
    }
    
    public void setRandomCode(String randomCode)
    {
        this.randomCode = randomCode;
    }
    
    public String getVidateTime()
    {
        return vidateTime;
    }
    
    public void setVidateTime(String vidateTime)
    {
        this.vidateTime = vidateTime;
    }
    
    public String getPayfee()
    {
        return payfee;
    }
    
    public void setPayfee(String payfee)
    {
        this.payfee = payfee;
    }
    
    public String getOrgId()
    {
        return orgId;
    }
    
    public void setOrgId(String orgId)
    {
        this.orgId = orgId;
    }
    
    public String getOperId()
    {
        return operId;
    }
    
    public void setOperId(String operId)
    {
        this.operId = operId;
    }
    
    public String getTermId()
    {
        return termId;
    }
    
    public void setTermId(String termId)
    {
        this.termId = termId;
    }
    
    public String getSubscribeTime()
    {
        return subscribeTime;
    }
    
    public void setSubscribeTime(String subscribeTime)
    {
        this.subscribeTime = subscribeTime;
    }
    
}
