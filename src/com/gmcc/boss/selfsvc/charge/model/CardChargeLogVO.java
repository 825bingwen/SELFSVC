package com.gmcc.boss.selfsvc.charge.model;

import java.util.List;

import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;

/**
 * 银行卡缴费日志VO
 * 
 * @author xkf29026
 * 
 */
public class CardChargeLogVO
{
    // 移动话费缴纳日志唯一标识
    private String chargeLogOID;
    
    // 缴费地点
    private String region;
    
    // 终端机编号
    private String termID;
    
    // 操作员编号
    private String operID;
    
    // 缴费号码
    private String servnumber;
    
    // 缴费方式，1：银联卡；4：现金
    private String payType;
    
    // 缴费金额，单位（分）
    private String fee;
    
    // 商户编号（授卡方标识）
    private String unionpayuser;
    
    // 终端编号
    private String unionpaycode;
    
    // 交易类型
    private String busiType;
    
    // 银行卡号
    private String cardnumber;
    
    // 终端批次号
    private String batchnum;
    
    // 授权码（山东用）
    private String authorizationcode;
    
    // 交易参考号（山东用）
    private String businessreferencenum;
    
    // 银联实际扣款时间
    private String unionpaytime;
    
    // 凭证号（山东用）
    private String vouchernum;
    
    // 银联实际扣款金额，单位（分）
    private String unionpayfee;
    
    // 交易流水号，即终端流水号
    private String terminalSeq;
    
    // 交易时间
    private String recdate;
    
    // 状态。00 初始状态（未经过实时对账或实时补缴失败）;01 二次对账成功;02 二次对账失败;03 补缴成功;04 补缴失败;10 实时补缴成功;12 银联扣款金额和BOSS缴费金额不一致（自助通异常）
    private String status;
    
    // 交易结果描述
    private String description;
    
    // BOSS受理编号
    private String dealnum = "";
    
    // BOSS受理类型
    private String acceptType;
    
    // 湖北用到
    private String posNum;
    
    // 湖北用到
    private String batchNumBeforeKoukuan;
    
    // 业务类型  phone：话费缴费  favourable：优惠缴费 (湖北用到)
    private String recType;
    
    //手机支付系统流水号
    private String mpayseq;
    
    // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    //bankno：合并缴费渠道和厂商对应的行号生成
    private String bankno;
    // add end cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    
    /**
     * 手机号码的region
     */
    private String servRegion;
    
    private String orgID;
    
    // add by xkf57421 for XQ[2011]_10_082 begin
    private String posResCode;
    // add by xkf57421 for XQ[2011]_10_082 end
    
    //add begin sWX219697 2014-12-23 15:24:40 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
    /**
     * 赠送金额
     */
    private String presentFee;
    
    /**
     * 赠送金额流水号
     */
    private String presentOid;
    
    /**
     * 赠送金额状态，0：失败，1：成功
     */
    private String presentStatus;
    //add end sWX219697 2014-12-23 15:24:40 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
    
    // add begin zKF69263 2015-4-17 OR_SD_201502_169_山东_自助终端实现现金稽核功能
    /**
     * 现金明细
     */
    private String cashDetail;
    // add end zKF69263 2015-4-17 OR_SD_201502_169_山东_自助终端实现现金稽核功能
    
    // add begin wWX217192 2015-5-30 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
    /**
     * 湖北使用的现金明细
     */
    private String cashMoney;
    // add end wWX217192 2015-5-30 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
    
    //add begin sWX219697 2015-3-25 09:25:22 OR_NX_201501_1030 跨省异地缴费
    /**
     * 省份编码
     */
    private String provinceCode;
    //add end sWX219697 2015-3-25 09:25:22 OR_NX_201501_1030 跨省异地缴费
    
    // add begin jWX216858 2015-3-30 OR_SD_201501_1063 自助终端支撑连缴功能优化
    /**
     * 话费连缴唯一流水
     */
    private String moreChargeOID;
    
    /**
	 * 话费余额
	 */
	private String balance;
	
	/**
	 * 应缴费用
	 */
    private String yingjiaoFee;
    
    /**
     * 受理流水 打印发票用
     */
    private String recoid;
    
    /**
     * 地市名称
     */
    private String regionName;
    
    /**
     * 跨区缴费标志，0：跨区
     */
    private String regionFlag;
    
    /**
     * 实缴费用
     */
    private String tMoney;
    
    /**
     * 客户名称
     */
    private String custName;
    
    /**
     * 话费连缴，单笔业务状态。0：缴费成功
     */
    private String chargeStatus;
    
    /**
     * 话费连缴字符串，手机号码~手机号码region~实缴金额~余额~应缴费~客户姓名~受理类型~缴费日志流水~受理流水~boss编号~缴费状态
     */
    public String getMorePhoneStr()
    {
    	return servnumber+"~"+servRegion+"~"+tMoney+"~"+balance+"~"+yingjiaoFee+"~"+custName+"~"+
    		acceptType+"~"+chargeLogOID+"~"+recoid+"~"+dealnum+"~"+chargeStatus;
    }
    // add end jWX216858 2015-3-30 OR_SD_201501_1063 自助终端支撑连缴功能优化

    // add begin jWX216858 2015--6-3 OR_SD_201503_949_自助终端新增跨省缴费功能的支撑
    // 菜单列表
    private List<MenuInfoPO> usableTypes;
    
    // 交易时间
    private String dealTime;
    // add end jWX216858 2015--6-3 OR_SD_201503_949_自助终端新增跨省缴费功能的支撑
    
    public String getChargeLogOID()
    {
        return chargeLogOID;
    }
    
    public void setChargeLogOID(String chargeLogOID)
    {
        this.chargeLogOID = chargeLogOID;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public void setRegion(String region)
    {
        this.region = region;
    }
    
    public String getTermID()
    {
        return termID;
    }
    
    public void setTermID(String termID)
    {
        this.termID = termID;
    }
    
    public String getOperID()
    {
        return operID;
    }
    
    public void setOperID(String operID)
    {
        this.operID = operID;
    }
    
    public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public String getPayType()
    {
        return payType;
    }
    
    public void setPayType(String payType)
    {
        this.payType = payType;
    }
    
    public String getFee()
    {
        return fee;
    }
    
    public void setFee(String fee)
    {
        this.fee = fee;
    }
    
    public String getUnionpayuser()
    {
        return unionpayuser;
    }
    
    public void setUnionpayuser(String unionpayuser)
    {
        this.unionpayuser = unionpayuser;
    }
    
    public String getUnionpaycode()
    {
        return unionpaycode;
    }
    
    public void setUnionpaycode(String unionpaycode)
    {
        this.unionpaycode = unionpaycode;
    }
    
    public String getBusiType()
    {
        return busiType;
    }
    
    public void setBusiType(String busiType)
    {
        this.busiType = busiType;
    }
    
    public String getCardnumber()
    {
        return cardnumber;
    }
    
    public void setCardnumber(String cardnumber)
    {
        this.cardnumber = cardnumber;
    }
    
    public String getBatchnum()
    {
        return batchnum;
    }
    
    public void setBatchnum(String batchnum)
    {
        this.batchnum = batchnum;
    }
    
    public String getAuthorizationcode()
    {
        return authorizationcode;
    }
    
    public void setAuthorizationcode(String authorizationcode)
    {
        this.authorizationcode = authorizationcode;
    }
    
    public String getBusinessreferencenum()
    {
        return businessreferencenum;
    }
    
    public void setBusinessreferencenum(String businessreferencenum)
    {
        this.businessreferencenum = businessreferencenum;
    }
    
    public String getUnionpaytime()
    {
        return unionpaytime;
    }
    
    public void setUnionpaytime(String unionpaytime)
    {
        this.unionpaytime = unionpaytime;
    }
    
    public String getVouchernum()
    {
        return vouchernum;
    }
    
    public void setVouchernum(String vouchernum)
    {
        this.vouchernum = vouchernum;
    }
    
    public String getUnionpayfee()
    {
        return unionpayfee;
    }
    
    public void setUnionpayfee(String unionpayfee)
    {
        this.unionpayfee = unionpayfee;
    }
    
    public String getTerminalSeq()
    {
        return terminalSeq;
    }
    
    public void setTerminalSeq(String terminalSeq)
    {
        this.terminalSeq = terminalSeq;
    }
    
    public String getRecdate()
    {
        return recdate;
    }
    
    public void setRecdate(String recdate)
    {
        this.recdate = recdate;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getDealnum()
    {
        return dealnum;
    }
    
    public void setDealnum(String dealnum)
    {
        this.dealnum = dealnum;
    }
    
    public String getAcceptType()
    {
        return acceptType;
    }
    
    public void setAcceptType(String acceptType)
    {
        this.acceptType = acceptType;
    }

    public String getServRegion()
    {
        return servRegion;
    }

    public void setServRegion(String servRegion)
    {
        this.servRegion = servRegion;
    }

    public String getOrgID()
    {
        return orgID;
    }

    public void setOrgID(String orgID)
    {
        this.orgID = orgID;
    }

    public String getPosNum()
    {
        return posNum;
    }

    public void setPosNum(String posNum)
    {
        this.posNum = posNum;
    }

    public String getBatchNumBeforeKoukuan()
    {
        return batchNumBeforeKoukuan;
    }

    public void setBatchNumBeforeKoukuan(String batchNumBeforeKoukuan)
    {
        this.batchNumBeforeKoukuan = batchNumBeforeKoukuan;
    }

	public String getRecType() {
		return recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}

    public String getMpayseq()
    {
        return mpayseq;
    }

    public void setMpayseq(String mpayseq)
    {
        this.mpayseq = mpayseq;
    }
    // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371
    public String getBankno()
    {
        return bankno;
    }

    public void setBankno(String bankno)
    {
        this.bankno = bankno;
    }
    // add begin cKF48754 2011/11/10 R003C11L11n01 OR_SD_201111_371

	public String getPosResCode()
	{
		return posResCode;
	}

	public void setPosResCode(String posResCode)
	{
		this.posResCode = posResCode;
	}

	public String getPresentFee() {
		return presentFee;
	}

	public void setPresentFee(String presentFee) {
		this.presentFee = presentFee;
	}

	public String getPresentOid() {
		return presentOid;
	}

	public void setPresentOid(String presentOid) {
		this.presentOid = presentOid;
	}

	public String getPresentStatus() {
		return presentStatus;
	}

	public void setPresentStatus(String presentStatus) {
		this.presentStatus = presentStatus;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getMoreChargeOID() {
		return moreChargeOID;
	}

	public void setMoreChargeOID(String moreChargeOID) {
		this.moreChargeOID = moreChargeOID;
	}

	// add begin zKF69263 2015-4-17 OR_SD_201502_169_山东_自助终端实现现金稽核功能
    /**
     * @return 返回 cashDetail
     */
    public String getCashDetail()
    {
        return cashDetail;
    }

    /**
     * @param 对cashDetail进行赋值
     */
    public void setCashDetail(String cashDetail)
    {
        this.cashDetail = cashDetail;
    }
    // add end zKF69263 2015-4-17 OR_SD_201502_169_山东_自助终端实现现金稽核功能

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getYingjiaoFee() {
		return yingjiaoFee;
	}

	public void setYingjiaoFee(String yingjiaoFee) {
		this.yingjiaoFee = yingjiaoFee;
	}

	public String getRecoid() {
		return recoid;
	}

	public void setRecoid(String recoid) {
		this.recoid = recoid;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionFlag() {
		return regionFlag;
	}

	public void setRegionFlag(String regionFlag) {
		this.regionFlag = regionFlag;
	}

	public String gettMoney()
    {
        return tMoney;
    }

    public void settMoney(String tMoney)
    {
        this.tMoney = tMoney;
    }

    public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public String getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(String cashMoney) {
		this.cashMoney = cashMoney;
	}

	public List<MenuInfoPO> getUsableTypes() {
		return usableTypes;
	}

	public void setUsableTypes(List<MenuInfoPO> usableTypes) {
		this.usableTypes = usableTypes;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

}
