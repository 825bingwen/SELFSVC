package com.gmcc.boss.selfsvc.common.model;

/**
 * 
 * <ɽ�������ն˶Խ�ISSS����Ӫ��ƽ̨���Ƽ�ҵ��>
 * <������ϸ����>
 * 
 * @author  sWX219697
 * @version  [�汾��, Sep 12, 2014]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 * @remark create by sWX219697 2014-9-12 17:06:00 OR_SD_201409_556_�����ն�Ӫ�������Ż�
 */
public class OfferInfoVO implements Comparable<OfferInfoVO>
{
	/**
	 * ҵ������
	 */
	private String offerName;
	
	/**
	 * ҵ����
	 */
	private String desc;
	
	/**
	 * offer����(1���ײ�\ 2��Ӫ������ \3��SP)
	 */
	private String offerType;
	
	/**
	 * ��Ʒid
	 */
	private String offerId;
	
	/**
	 * �ײ�: ��Ʒ��;��Ʒ;
	 * �Ż�Ӫ����: ;����;����
	 * SP: ;SP��ҵ����;SPҵ�����
	 */
	private String offerCode;

	/**
	 * ս��ID������ʱʹ��
	 */
	private String treatment_id;
	
	/**
	 * �ò�Ʒ��Ӧ�Ĳ˵�id
	 */
	private String menuId;
	
	/**
	 * ��Ӧ��url
	 */
	private String url;
	
    /**
     * ����״̬
     */
    private String status;
    
    // add begin jWX216858 2015-5-11 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
    /**
     * ���ȼ�, ��ֵ��ȷ����λС������ֵԽ�����ȼ�Խ��
     */
    private String priority;
    // add end jWX216858 2015-5-11 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����

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

	// add begin jWX216858 2015-5-11 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
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
	// add begin jWX216858 2015-5-11 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
	
}
