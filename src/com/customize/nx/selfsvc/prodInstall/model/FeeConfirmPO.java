package com.customize.nx.selfsvc.prodInstall.model;

/**
 * 
 * 在线开户
 * <功能详细描述>
 * 
 * @author  user
 * @version  [版本号, Jul 29, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]OR_NX_201303_280  宁夏自助终端优化需求之在线开户
 */
public class FeeConfirmPO
{
    private String feeName = "";// 费用名称
    private String fee = "";// 金额（分）
    private String num = "";// 数量
    private String feeType = "";// 费用类型
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
