package com.customize.hub.selfsvc.prodChange.model;

@edu.umd.cs.findbugs.annotations.SuppressWarnings( {"NM_CONFUSING", "EC_UNRELATED_TYPES"})
public class ProdLogVO {
	
	/**
	 * SH_PRIV_LOG��ˮ��
	 */
	private String prodLogOID;
	
	/**
	 * ��Ʒ����SN
	 */
	private String prodSn;
	
	/**
	 * ����
	 */
	private String region;
	
	/**
	 *�û��������
	 */
	private String servnumber;
	
	/**
	 * ��ǰ�û���Ʒ
	 */
	private String fromProdId;
	
	/**
	 * �û�ת����Ʒ
	 */
	private String toProdId;
	
	/**
	 * �û���������
	 */
	private String opType;
	
	/**
	 * ��Ʒת������
	 */
	private String prodType;
	
	/**
	 * ��Ʒת��ID
	 */
	private String changeId;
	
	/**
	 * ҵ������ʱ��
	 */
	private String recDate;
	
	/**
	 * ҵ������״̬
	 */
	private String recStauts;
	
	//add begin yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
	/**
	 * ��ϸ�ñ�����
	 */	
	
	private String chgdesp;
	
	/**
	 * ����״̬����
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