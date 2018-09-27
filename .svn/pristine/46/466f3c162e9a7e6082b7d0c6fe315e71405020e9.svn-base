/*
 * 文 件 名:  SimInfoPO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  Sim卡信息
 * 修 改 人:  zKF69263
 * 修改时间:  2014-12-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.cardbusi.model;

/**
 * Sim卡信息
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-12-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SimInfoPO
{
    private String iccid;
    
    private String oldiccid;
    
    private String imsi;
    
    private String eki;
    
    private String smsp;
    
    private String pin1;
    
    private String pin2;
    
    private String puk1;
    
    private String puk2;
    
    /**
     * 空白卡号
     */
    private String blankno;
    
    /**
     * 加密后的写卡信息
     */
    private String issueData;
    
    /**
     * 写卡流水号
     */
    private String formNum;

    /**
     * 写卡是否成功 1：成功 -1：失败
     */
    private String  writeResult;
    
    /**
     * 写卡结果信息
     */
    private String errMsg;
    
    /**
     * 错误码 0 成功 -1 失败
     */
    private String errCode;
    /**
     * 无参构造方法
     */
    public SimInfoPO(){}
    
    /**
     * 有参构造方法
     * @param iccid
     * @param imsi
     * @param eki
     * @param smsp
     * @param pin1
     * @param pin2
     * @param puk1
     * @param puk2
     */
    public SimInfoPO(String cardInfoStr)
    {
        String[] resArray = cardInfoStr.split("~~");
        this.iccid = resArray[0];
        this.imsi = resArray[1];
        this.issueData = resArray[2];
        this.formNum = resArray[3];
    }
    
    /**
     * @return 返回 iccid
     */
    public String getIccid()
    {
        return iccid;
    }

    /**
     * @param 对iccid进行赋值
     */
    public void setIccid(String iccid)
    {
        this.iccid = iccid;
    }

    /**
     * @return 返回 imsi
     */
    public String getImsi()
    {
        return imsi;
    }

    /**
     * @param 对imsi进行赋值
     */
    public void setImsi(String imsi)
    {
        this.imsi = imsi;
    }

    /**
     * @return 返回 eki
     */
    public String getEki()
    {
        return eki;
    }

    /**
     * @param 对eki进行赋值
     */
    public void setEki(String eki)
    {
        this.eki = eki;
    }

    /**
     * @return 返回 smsp
     */
    public String getSmsp()
    {
        return smsp;
    }

    /**
     * @param 对smsp进行赋值
     */
    public void setSmsp(String smsp)
    {
        this.smsp = smsp;
    }

    /**
     * @return 返回 pin1
     */
    public String getPin1()
    {
        return pin1;
    }

    /**
     * @param 对pin1进行赋值
     */
    public void setPin1(String pin1)
    {
        this.pin1 = pin1;
    }

    /**
     * @return 返回 pin2
     */
    public String getPin2()
    {
        return pin2;
    }

    /**
     * @param 对pin2进行赋值
     */
    public void setPin2(String pin2)
    {
        this.pin2 = pin2;
    }

    /**
     * @return 返回 puk1
     */
    public String getPuk1()
    {
        return puk1;
    }

    /**
     * @param 对puk1进行赋值
     */
    public void setPuk1(String puk1)
    {
        this.puk1 = puk1;
    }

    /**
     * @return 返回 puk2
     */
    public String getPuk2()
    {
        return puk2;
    }

    /**
     * @param 对puk2进行赋值
     */
    public void setPuk2(String puk2)
    {
        this.puk2 = puk2;
    }
    
    /**
	 * @return 返回 issueData
	 */
	public String getIssueData() 
	{
		return issueData;
	}

	/**
	 * @param 对issueData进行赋值
	 */
	public void setIssueData(String issueData) 
	{
		this.issueData = issueData;
	}

	
	public String getBlankno()
    {
        return blankno;
    }

    public void setBlankno(String blankno)
    {
        this.blankno = blankno;
    }

    public String getWriteResult()
    {
        return writeResult;
    }

    public void setWriteResult(String writeResult)
    {
        this.writeResult = writeResult;
    }

    public String getErrMsg()
    {
        return errMsg;
    }

    public void setErrMsg(String errMsg)
    {
        this.errMsg = errMsg;
    }

    public String getErrCode()
    {
        return errCode;
    }

    public void setErrCode(String errCode)
    {
        this.errCode = errCode;
    }
    
    /**
     * @return 返回 formNum
     */
    public String getFormNum()
    {
        return formNum;
    }

    /**
     * @param 对formNum进行赋值
     */
    public void setFormNum(String formNum)
    {
        this.formNum = formNum;
    }
    
    /** 
     * <取得写卡数据>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getWriteCardData()
    {
        return this.iccid+"~~"+this.imsi+"~~"+this.issueData+"~~"+this.formNum+"~~"+this.oldiccid;
    }

    /**
     * 
     * @return iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2
     */
    public String toString()
    {
    	return this.iccid+"~~"+this.imsi+"~~"+this.eki+"~~"
    		+this.smsp+"~~"+this.pin1+"~~"+this.pin2+"~~"+this.puk1+"~~"+this.puk2;
    }

	public String getOldiccid() {
		return oldiccid;
	}

	public void setOldiccid(String oldiccid) {
		this.oldiccid = oldiccid;
	}
    
}
