package com.gmcc.boss.selfsvc.media.service;

import java.util.List;

/**
 * 多媒体播放播放Service
 * 终端机生成广告播放配置文件adv.wpl
 * 终端机生成屏保播放配置文件sc.wpl
 * @author  yKF28472
 * @version  [版本号, 2010-12-06]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MediaService 
{
	/**
	 * 取资源文件列表
	 * @param termID
	 * @param region
	 * @param mediaType
	 * @param path
	 * @return
	 */
	public String getMediaWpl(String termID, String region, String mediaType, String path);
	
	public List getAllMediaFiles();
	
	/**
     * 获取按省份设置的屏保信息列表
     * @return
     * @remark create by wWX217192 2014-09-19 OR_HUB_201403_1773 自助终端LOGO更换及屏保一键更新功能
     */
	public String getProvinceScList(String mediaType, String path);
}
