package com.gmcc.boss.selfsvc.baseService.spService.model;

/**
 * ȫ������ҵ�񶩹�PO
 * @author xkf29026
 *
 */
public class SpAvailPO 
{
	private String servType;  //ҵ������
	
	private String spcode; // ��ҵ����
	
	private String operatorCode;  // ҵ�����
	
	private String operatorName;  // ҵ������
	
	private String billFlag;  // �Ʒ�����
	
	private String fee;  // ��Ϣ��
	
	private String validdate;  //��Ч����
	
	private String expireDate;  // ʧЧ����
	
	private String domain;  // ҵ��ƽ̨
	
	// ------- �����˵� ---------------
	
	private String offerMan;// �ṩ��
	
	private String menuType;// hot���
	
	private String imgPath;// ͼƬ·��
	
	private String linkUrl;// url

	public String getOperatorName() 
	{
		return operatorName;
	}

	public void setOperatorName(String operatorName) 
	{
		this.operatorName = operatorName;
	}

	public String getDomain() 
	{
		return domain;
	}

	public void setDomain(String domain) 
	{
		this.domain = domain;
	}
	
	public String getSpcode() 
	{
		return spcode;
	}

	public void setSpcode(String spcode) 
	{
		this.spcode = spcode;
	}

	public String getOperatorCode() 
	{
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) 
	{
		this.operatorCode = operatorCode;
	}

	public String getBillFlag() 
	{
		return billFlag;
	}

	public void setBillFlag(String billFlag) 
	{
		this.billFlag = billFlag;
	}

	public String getFee() 
	{
		return fee;
	}

	public void setFee(String fee) 
	{
		this.fee = fee;
	}

	public String getValiddate() 
	{
		return validdate;
	}

	public void setValiddate(String validdate) 
	{
		this.validdate = validdate;
	}

	public String getServType() 
	{
		return servType;
	}

	public void setServType(String servType) 
	{
		this.servType = servType;
	}

    public String getMenuType()
    {
        return menuType;
    }

    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
    }

    public String getImgPath()
    {
        return imgPath;
    }

    public void setImgPath(String imgPath)
    {
        this.imgPath = imgPath;
    }

    public String getLinkUrl()
    {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl)
    {
        this.linkUrl = linkUrl;
    }

    public String getOfferMan()
    {
        return offerMan;
    }

    public void setOfferMan(String offerMan)
    {
        this.offerMan = offerMan;
    }

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
}
