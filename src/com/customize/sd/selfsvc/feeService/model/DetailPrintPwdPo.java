package com.customize.sd.selfsvc.feeService.model;

/**
 * 
 * <详单二次打印密码>
 * <功能详细描述>
 * 
 * @author  sWX219697
 * @version  [版本号, Jun 3, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark create by sWX219697 2014-6-3 14:58:28 OR_huawei_201405_877
 */
public class DetailPrintPwdPo 
{
	
	/**
	 * 终端机ID
	 */
	private String termId;
	
	/**
	 * 详单补打密码
	 */
	private String detailPwd;

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getDetailPwd() {
		return detailPwd;
	}

	public void setDetailPwd(String detailPwd) {
		this.detailPwd = detailPwd;
	}

	
}
