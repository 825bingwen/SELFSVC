/*
 * 文 件 名:  ProdChangeServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  hWX5316476
 * 修改时间:  Jan 5, 2015
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.prodChange.service;

import java.util.List;

import com.customize.sd.selfsvc.prodChange.dao.ProdChangeDaoImpl;
import com.customize.sd.selfsvc.prodChange.model.ProdChangePO;
import com.customize.sd.selfsvc.prodChange.model.ProdTemplatePO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  hWX5316476
 * @version  [版本号, Jan 5, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProdChangeServiceImpl implements ProdChangeService
{
    /**
     * 套餐资费变更daoImpl
     */
    private ProdChangeDaoImpl prodChangeDaoImpl;
    
    /**
     * 查询产品内可转换的档次列表
     * @param prodid 主体产品id
     * @param region 地区
     * @param brand 品牌
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ProdTemplatePO> qryLevelByProdId(NserCustomerSimp customer, String newProdName)
    {
        // 设置查询跳进啊
        ProdChangePO prodChangePO = new ProdChangePO();
        
        // 主体产品名称
        prodChangePO.setNewProdName(newProdName);
        
        // 主体产品ID
        prodChangePO.setOldProdId(customer.getProductID());
        prodChangePO.setNewProdId(customer.getProductID());
        
        // 地区
        prodChangePO.setRegion(customer.getRegionID());
        
        // 数据库后查询产品内可转换的档次列表
        return prodChangeDaoImpl.qryLevelByProdId(prodChangePO);
    }
    public ProdChangeDaoImpl getProdChangeDaoImpl()
    {
        return prodChangeDaoImpl;
    }
    public void setProdChangeDaoImpl(ProdChangeDaoImpl prodChangeDaoImpl)
    {
        this.prodChangeDaoImpl = prodChangeDaoImpl;
    }
    
    
}
