package com.gmcc.boss.selfsvc.common.model;

/**
 * 
 * <山东自助终端对接ISSS智能营销平台，推荐业务>
 * <功能详细描述>
 * 
 * @author  sWX219697
 * @version  [版本号, Sep 12, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark create by sWX219697 2014-9-12 17:06:00 OR_SD_201409_556_自助终端营销功能优化
 */
public class OfferInfoVO implements Comparable<OfferInfoVO>
{
	/**
	 * 业务名称
	 */
	private String offerName;
	
	/**
	 * 业务简介
	 */
	private String desc;
	
	/**
	 * offer类型(1、套餐\ 2、营销方案 \3、SP)
	 */
	private String offerType;
	
	/**
	 * 产品id
	 */
	private String offerId;
	
	/**
	 * 套餐: 产品包;产品;
	 * 优惠营销案: ;批次;档次
	 * SP: ;SP企业编码;SP业务编码
	 */
	private String offerCode;

	/**
	 * 战役ID，反馈时使用
	 */
	private String treatment_id;
	
	/**
	 * 该产品对应的菜单id
	 */
	private String menuId;
	
	/**
	 * 对应的url
	 */
	private String url;
	
    /**
     * 订购状态
     */
    private String status;
    
    // add begin jWX216858 2015-5-11 OR_SD_201504_452_山东_ISSS自助终端UCD改造
    /**
     * 优先级, 数值精确到两位小数，数值越大，优先级越高
     */
    private String priority;
    // add end jWX216858 2015-5-11 OR_SD_201504_452_山东_ISSS自助终端UCD改造

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getTreatment_id() {
		return treatment_id;
	}

	public void setTreatment_id(String treatment_id) {
		this.treatment_id = treatment_id;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	// add begin jWX216858 2015-5-11 OR_SD_201504_452_山东_ISSS自助终端UCD改造
	@Override
	public int compareTo(OfferInfoVO o) {
		return Integer.parseInt(o.getPriority()) - Integer.parseInt(priority);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((priority == null) ? 0 : priority.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final OfferInfoVO other = (OfferInfoVO) obj;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		return true;
	}
	// add begin jWX216858 2015-5-11 OR_SD_201504_452_山东_ISSS自助终端UCD改造
	
}
