package com.customize.hub.selfsvc.prodChange.model;

@edu.umd.cs.findbugs.annotations.SuppressWarnings( {"NM_CONFUSING", "EC_UNRELATED_TYPES"})
public class ProdLogVO {
	
	/**
	 * SH_PRIV_LOG流水号
	 */
	private String prodLogOID;
	
	/**
	 * 产品受理SN
	 */
	private String prodSn;
	
	/**
	 * 地区
	 */
	private String region;
	
	/**
	 *用户服务号码
	 */
	private String servnumber;
	
	/**
	 * 当前用户产品
	 */
	private String fromProdId;
	
	/**
	 * 用户转换产品
	 */
	private String toProdId;
	
	/**
	 * 用户受理类型
	 */
	private String opType;
	
	/**
	 * 产品转换类型
	 */
	private String prodType;
	
	/**
	 * 产品转换ID
	 */
	private String changeId;
	
	/**
	 * 业务受理时间
	 */
	private String recDate;
	
	/**
	 * 业务受理状态
	 */
	private String recStauts;
	
	//add begin yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
	/**
	 * 详细得变更情况
	 */	
	
	private String chgdesp;
	
	/**
	 * 受理状态描述
	 */
	private String recstatusdesc;
	//add begin yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
	
	
	public String getProdLogOID() {
		return prodLogOID;
	}
	public void setProdLogOID(String prodLogOID) {
		this.prodLogOID = prodLogOID;
	}
	public String getProdSn() {
		return prodSn;
	}
	public void setProdSn(String prodSn) {
		this.prodSn = prodSn;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getServnumber() {
		return servnumber;
	}
	public void setServnumber(String servnumber) {
		this.servnumber = servnumber;
	}
	public String getFromProdId() {
		return fromProdId;
	}
	public void setFromProdId(String fromProdId) {
		this.fromProdId = fromProdId;
	}
	public String getToProdId() {
		return toProdId;
	}
	public void setToProdId(String toProdId) {
		this.toProdId = toProdId;
	}
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getProdType() {
		return prodType;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getChangeId() {
		return changeId;
	}
	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}
	public String getRecDate() {
		return recDate;
	}
	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}
	public String getRecStauts() {
		return recStauts;
	}
	public void setRecStauts(String recStauts) {
		this.recStauts = recStauts;
	}
    public String getChgdesp()
    {
        return chgdesp;
    }
    public void setChgdesp(String chgdesp)
    {
        this.chgdesp = chgdesp;
    }
    public String getRecstatusdesc()
    {
        return recstatusdesc;
    }
    public void setRecstatusdesc(String recstatusdesc)
    {
        this.recstatusdesc = recstatusdesc;
    }
	
}
