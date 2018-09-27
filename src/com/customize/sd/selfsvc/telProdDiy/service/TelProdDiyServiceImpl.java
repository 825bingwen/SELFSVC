/*
 * 文 件 名:  TelProdDiyServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <自选套餐>
 * 创 建 人: jWX216858
 * 创建时间: 2014-10-10
 */
package com.customize.sd.selfsvc.telProdDiy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customize.sd.selfsvc.common.service.BaseServiceSDImpl;
import com.customize.sd.selfsvc.telProdDiy.dao.TelProdDiyDaoImpl;
import com.customize.sd.selfsvc.telProdDiy.model.TelProdPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 自选套餐service接口实现类
 * @author  jWX216858
 * @version  [版本号, 2014-10-10]
 * @see  
 * @since 
 */
@Service
@Transactional(noRollbackFor=ReceptionException.class)
public class TelProdDiyServiceImpl extends BaseServiceSDImpl implements TelProdDiyService
{
    @Autowired
	private TelProdDiyDaoImpl telProdDiyDaoImpl;

	/**
	 * 查询语音通话套餐信息
     * @param prodId
	 * @return
     * @remark modify by sWX219697 2015-5-6 OR_SD_201503_508_SD_自助终端增加主体产品自选套餐的办理
	 */
	public List<TelProdPO> qryVoiceProdList(String prodId) 
	{
        //获取客户信息
        NserCustomerSimp customer = getCustomer();
        
        //准备查询自选套餐的入参
        TelProdPO telProdPO = new TelProdPO();
        
        //所属品牌
        telProdPO.setBelongBrand(customer.getBrandID());
        
        //所属地市
        telProdPO.setBelongRegion(customer.getRegionID()); 
        
        //主体产品编码
        telProdPO.setProdId(prodId);
        
        //查询语音通话信息
        List<TelProdPO> voiceProdList = telProdDiyDaoImpl.qryVoiceProdList(telProdPO);
        
        if (null == voiceProdList || 0 == voiceProdList.size())
        {   
            insertRecLog(Constants.SH_VOICECALLREC, "", "", Constants.RECSTATUS_FALID,
                    "没有相应的语音通话套餐信息");
            
            throw new ReceptionException("没有相应的语音通话套餐信息");
        }
        
        return voiceProdList;
	}
	
	/**
	 * 查询上网流量套餐信息
     * @param prodId
	 * @return
     * @remark modify by sWX219697 2015-5-6 OR_SD_201503_508_SD_自助终端增加主体产品自选套餐的办理
	 */
	public List<TelProdPO> qryNetProdList(String prodId) 
	{
        //获取客户信息
        NserCustomerSimp customer = getCustomer();
        
        //准备查询自选套餐的入参
        TelProdPO telProdPO = new TelProdPO();
        
        //所属品牌
        telProdPO.setBelongBrand(customer.getBrandID());
        
        //所属地市
        telProdPO.setBelongRegion(customer.getRegionID()); 
        
        //主体产品编码
        telProdPO.setProdId(prodId);
        
        //查询上网流量套餐
        List<TelProdPO> netProdList = telProdDiyDaoImpl.qryNetProdList(telProdPO);
        
        if (null == netProdList || 0 == netProdList.size())
        {
            insertRecLog(Constants.SH_GPRSWLANREC, "", "", Constants.RECSTATUS_FALID,
                "没有相应的语音通话套餐信息");
            
            throw new ReceptionException("没有相应的上网流量自选套餐信息");
        }
        return netProdList;
	}
	
	/**
	 * 根据id查询套餐信息
	 * @param telProdPO
	 * @return
	 */
	public String qryProdById(TelProdPO telProdPO)
	{
		return telProdDiyDaoImpl.qryProdById(telProdPO);
	}
	
    /**
     * <查询用户可用的主体产品套餐>
     * <1、查询表sh_goods_telprodinfo中配置的主体产品编码。2、调用接口查询用户可转换的主体产品编码列表，与1中查询的取交集>
     * @param curMenuId 菜单
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-4-29 15:06:06 OR_SD_201503_508_SD_自助终端增加主体产品自选套餐的办理
     */
    public List<TelProdPO> qryUsableProdList(String curMenuId)
    {
        //获取客户信息
        NserCustomerSimp customer = getCustomer();
        
        //终端机信息
        TerminalInfoPO termInfo = getTermInfo();
        
        //准备查询自选套餐的入参
        TelProdPO telProdPO = new TelProdPO();
        
        //所属品牌
        telProdPO.setBelongBrand(customer.getBrandID());
        
        //所属地市
        telProdPO.setBelongRegion(customer.getRegionID());
        
        //查询列表中的主体产品信息
        List<TelProdPO> prodList = telProdDiyDaoImpl.qryProdList(telProdPO);
        
        //没有查询到相应的信息
        if(null == prodList || 0 == prodList.size())
        {
            insertRecLog(Constants.SH_VOICECALLREC, "", "", Constants.RECSTATUS_FALID,
                "没有相应的自选套餐信息");
            
            throw new ReceptionException("没有相应的自选套餐信息！");
        }
        
        //开关开启时，调接口查询用户可转换的主体产品列表
        if ("open".equals(CommonUtil.getParamValue(Constants.MAINPROD_CHANGE_SWITCH)))
        {
        	//modify begin fwx439896 2017-07-04 OR_huawei_201704_376_【山东移动接口迁移专题】-自助终端业务办理1
        	// 组装请求报文头
    		// 参数
    		Map<String,String> paramMap = new HashMap<String, String>();
    		
    		// 操作员id
    		paramMap.put("operId", termInfo.getOperid());
    		
    		// 终端机id
    		paramMap.put("termId", termInfo.getTermid());
    		
    		// 当前菜单
    		paramMap.put("menuId", curMenuId);
    		
    		// 客户接触id
    		paramMap.put("touchId", customer.getContactId());
    		
    		// 区域编码
    		paramMap.put("region", customer.getRegionID());
    		
    		// 产品编码
    		paramMap.put("prodId", customer.getProductID());
    		
    		// 渠道类型
    		paramMap.put("accessType", "bsacAtsv");
            
            
            //调用接口查询用户可变更的主体产品列表qryMainProdInfo(paramMap);
            ReturnWrap rw = getSelfSvcCallSD().qryMainProdInfo(paramMap);
            
            //调用成功，过滤用户可变更的主体产品
            if (SSReturnCode.SUCCESS == rw.getStatus())
            {     
            	CRSet crset = (CRSet)rw.getReturnObject();
            
                //用户可转换的主题产品列表
                List<TelProdPO> prodChangeList = new ArrayList<TelProdPO>();
            	// 遍历crset，取得返回信息
        		//modify begin lWX431760 2017-06-14 OR_huawei_201704_376_【山东移动接口迁移专题】-自助终端业务办理1
        		if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CONVERTPRODINFO))
        		{
        		    for (int i = 0; i < crset.GetRowCount(); i++)
        	        {
        		    	TelProdPO prodChangePO = new TelProdPO();
        	            // 产品编码
        	            prodChangePO.setProdId(crset.GetValue(i, 0));
        	           
        	            prodChangeList.add(prodChangePO);
        	        }
        		}
        		else
        		{
        			// 遍历crset，取得可变更的主体产品编码列表
                    for (int i = 0; i < crset.GetRowCount(); i++)
                    {
                        TelProdPO prodChangePO = new TelProdPO();
                        
                        // 产品编码
                        prodChangePO.setProdId(crset.GetValue(i, 1));
                        prodChangeList.add(prodChangePO);
                    }
        		}
        		//modify end fwx439896 2017-07-04 OR_huawei_201704_376_【山东移动接口迁移专题】-自助终端业务办理1
                
                //添加上用户自己的主体产品编码
                telProdPO.setProdId(customer.getProductID());
                prodChangeList.add(telProdPO);
                
                prodList = handleProdList(prodList, prodChangeList);
            }
        }
        
        return prodList;
    }
    
    /**
     * <将从表中查询的主体产品列表和接口查询的可变更主体产品列表取交集>
     * <功能详细描述>
     * @param prodList 主体产品列表
     * @param prodChangeList 可变更的主体产品列表
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-4-29 15:06:06 OR_SD_201503_508_SD_自助终端增加主体产品自选套餐的办理
     */
    private List<TelProdPO> handleProdList(List<TelProdPO> prodList,List<TelProdPO> prodChangeList)
    {
        List<TelProdPO> telProdList = new ArrayList<TelProdPO>();
        
        //遍历表中主体产品和用户可变更的主体产品，取交集
        for(TelProdPO prodPO : prodList)
        {
            for(TelProdPO prodChangePO : prodChangeList)
            {
                if(prodPO.getProdId().equals(prodChangePO.getProdId()))
                {
                    telProdList.add(prodPO);
                    break;
                }
            }
        }
        
        return telProdList;
    }
    
    /**
     * <主体产品套餐办理>
     * <功能详细描述>
     * @param telProdPO
     * @param curMenuId
     * @see [类、类#方法、类#成员]
     * @remark modify by sWX219697 2015-5-6 OR_SD_201503_508_SD_自助终端增加主体产品自选套餐的办理
     */
    public void recCommit(TelProdPO telProdPO, String curMenuId)
    {
        //获取客户信息
        NserCustomerSimp customer = getCustomer();
        
        //终端机信息
        TerminalInfoPO termInfo = getTermInfo();
        
        //根据所选的语音和上网流量套餐查询对应的ncode
        String nCode = telProdDiyDaoImpl.qryProdById(telProdPO);
        
        // 组装报文头信息
        MsgHeaderPO header = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
                customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        //调用流水线办理接口
        ReturnWrap rw = getSelfSvcCallSD().voiceCallRec(header, nCode);
        
        //办理失败
        if (SSReturnCode.ERROR == rw.getStatus())
        {
            insertRecLog(Constants.SH_VOICECALLREC, "", "", Constants.RECSTATUS_FALID, rw.getReturnMsg());
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        //办理成功，记录业务日志
        insertRecLog(Constants.SH_VOICECALLREC, "", "", Constants.RECSTATUS_SUCCESS, "自选套餐:"+nCode+"办理成功");
    }

}
