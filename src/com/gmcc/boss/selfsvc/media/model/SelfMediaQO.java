package com.gmcc.boss.selfsvc.media.model;

/**
 * 多媒体查询对象
 * <功能详细描述>
 * @author  yKF28472
 * @version  [版本号, 2010-12-06]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SelfMediaQO {

	private String regions;// 区域
	private String resids;// 资源编码
	private String restype;// 资源类型

	public String getRegions() {
		return regions;
	}
	public void setRegions(String regions) {
		this.regions = regions;
	}
	public String getResids() {
		return resids;
	}
	public void setResids(String resids) {
		this.resids = resids;
	}
	public String getRestype() {
		return restype;
	}
	public void setRestype(String restype) {
		this.restype = restype;
	}
	
	
}
