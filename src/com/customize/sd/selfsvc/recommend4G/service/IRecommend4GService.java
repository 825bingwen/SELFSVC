/*
 * 文 件 名:  IRecommend4GService.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  4G终端推荐service接口
 * 修 改 人:  jWX216858
 * 修改时间:  2014-12-19
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.recommend4G.service;

import java.util.List;

import com.customize.sd.selfsvc.recommend4G.model.Recommend4GPO;

/**
 * 4G终端推荐service接口
 * 
 * @author  jWX216858
 * @version  [版本号, 2014-12-19]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求 
 */
public interface IRecommend4GService 
{
	/** 
     * 获取手机列表
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2014-12-22 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
     */
	public List<Recommend4GPO> getPhoneList(Recommend4GPO recommend4GPO);
}
