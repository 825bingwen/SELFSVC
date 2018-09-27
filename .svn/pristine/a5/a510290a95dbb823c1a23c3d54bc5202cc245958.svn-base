/*
 * 文 件 名:  IHotAPPDownloadService.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <热门APP应用下载service>
 * 修 改 人:  jWX216858
 * 修改时间:  Jun 26, 2015
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.hotAPPDownload.service;

import java.util.List;

import com.customize.sd.selfsvc.hotAPPDownload.model.APPInfoPO;

/**
 * <热门APP应用下载service>
 * <功能详细描述>
 * 
 * @author  jWX216858
 * @version  [版本号, Jun 26, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本 OR_SD_201506_152_山东_在自助终端增加“热门APP应用”下载]
 */
public interface IHotAPPDownloadService
{
	/**
     * <获取所有的app应用>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
	public List<List<APPInfoPO>> getAppInfoList();
	
	/**
     * <根据app应用id获取app描述>
     * <功能详细描述>
     * @param appId app应用id，唯一标识
     * @return
     * @see [类、类#方法、类#成员]
     */
	public APPInfoPO getAppInfoById(String appId);
	
	/**
     * <短信发送app下载地址>
     * <功能详细描述>
     * @param menuId 当前菜单
	 * @param servNumber 手机号码
	 * @param appInfo app信息
     * @see [类、类#方法、类#成员]
     */
	public void sendAddress(String menuId, String servNumber, APPInfoPO appInfo);
	
	/**
	 * <用户登录，登录成功后短信发送app下载地址>
     * <功能详细描述>
	 * @param menuId 当前菜单
	 * @param servNumber 手机号码
	 * @param appInfo app信息
	 * @param password 服务密码
	 */
	public void customerLogin(String menuId, String servNumber, APPInfoPO appInfo, String password);

}
