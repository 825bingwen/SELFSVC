/*
 * 文 件 名:  TelProdDiyService.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <自选套餐>
 * 创 建 人: jWX216858
 * 创建时间: 2014-10-10
 */
package com.customize.sd.selfsvc.telProdDiy.service;

import java.util.List;

import com.customize.sd.selfsvc.telProdDiy.model.TelProdPO;

/**
 * 自选套餐service接口
 * @author  jWX216858
 * @version  [版本号, 2014-10-10]
 * @see  
 * @since 
 */
public interface TelProdDiyService
{
	/**
	 * 查询语音通话套餐信息
     * @param prodId
	 * @return
	 */
	public List<TelProdPO> qryVoiceProdList(String prodId);
	
	/**
	 * 查询上网流量套餐信息
     * @param prodId
	 * @return
	 */
	public List<TelProdPO> qryNetProdList(String prodId);
	
	/**
	 * 根据id查询套餐信息
	 * @param telProdPO
	 * @return
	 */
	public String qryProdById(TelProdPO telProdPO);
	
	/**
	 * <查询主体产品套餐>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @remark create by sWX219697 2015-4-29 15:06:06 OR_SD_201503_508_SD_自助终端增加主体产品自选套餐的办理
	 */
	public List<TelProdPO> qryUsableProdList(String curMenuId);
	
	/**
	 * <主体产品套餐办理>
	 * <功能详细描述>
	 * @param telProdPO
	 * @param curMenuId
	 * @see [类、类#方法、类#成员]
     * @remark modify by sWX219697 2015-5-6 OR_SD_201503_508_SD_自助终端增加主体产品自选套餐的办理
	 */
	public void recCommit(TelProdPO telProdPO, String curMenuId);
}
