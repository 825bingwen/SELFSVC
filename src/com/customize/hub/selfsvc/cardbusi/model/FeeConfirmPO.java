package com.customize.hub.selfsvc.cardbusi.model;

/**
 * 
 * �������������������po
 * <������ϸ����>
 * 
 * @author  sWX219697
 * @version  [�汾��, Jul 29, 2013]
 * @see  [�����/����]
 * @since  [��Ʒ/OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)]
 */
public class FeeConfirmPO
{
	/**
	 * ��������
	 */
    private String feeName = "";
    
    /**
     * ���֣�
     */
    private String fee = "";
    
    /**
     * ����
     */
    private String num = "";
    
    /**
     * ��������
     */
    private String feeType = "";
    
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getFeeName() 
	{
		return feeName;
	}
	
	public String getFeeType() 
	{
		return feeType;
	}
	
	public String getNum() 
	{
		return num;
	}
	
	public String getFee() 
	{
		return fee;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setFeeName(String feeName) 
	{
		this.feeName = feeName;
	}
	
	public void setFeeType(String feeType) 
	{
		this.feeType = feeType;
	}

	public void setNum(String num) 
	{
		this.num = num;
	}

	public void setFee(String fee) 
	{
		this.fee = fee;
	}
}
