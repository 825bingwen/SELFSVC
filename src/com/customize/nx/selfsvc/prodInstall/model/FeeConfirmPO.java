package com.customize.nx.selfsvc.prodInstall.model;

/**
 * 
 * ���߿���
 * <������ϸ����>
 * 
 * @author  user
 * @version  [�汾��, Jul 29, 2013]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]OR_NX_201303_280  ���������ն��Ż�����֮���߿���
 */
public class FeeConfirmPO
{
    private String feeName = "";// ��������
    private String fee = "";// ���֣�
    private String num = "";// ����
    private String feeType = "";// ��������
	public String getFeeName() {
		return feeName;
	}
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

}
