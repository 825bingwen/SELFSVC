package com.customize.hub.selfsvc.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.customize.hub.selfsvc.cardbusi.model.FeeConfirmPO;
import com.customize.hub.selfsvc.cardbusi.model.SimInfoPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * <补卡接口调用>
 * <功能详细描述>
 * 
 * @author  sWX219697
 * @version  [版本号, Oct 27, 2014]
 * @see  [相关类/方法]
 * @since  [产品/OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)]
 */
public class ReissueCardBean extends BaseBeanHubImpl
{
    /**
     * <校验身份证信息与手机号是否相符>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
   	 * @remark create by sWX219697 2014-10-25 14:34:42 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public void checkReissueIdCard(String idCardNo, String telnum, String curMenuId, 
    		TerminalInfoPO termInfo)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        ReturnWrap rw = this.getSelfSvcCallHub().checkReissueIdCard(msgHeader, idCardNo);
    	
        //调用成功
        if (SSReturnCode.SUCCESS == rw.getStatus())
        {
        	CTagSet tagSet = (CTagSet)rw.getReturnObject();
        	
        	//校验结果：校验通过 1；不通过，0
        	if(!"1".equals(tagSet.GetValue("ifValid")))
        	{
        		throw new ReceptionException("校验用户身份证信息与手机号码失败");
        	}
        }
        else
        {
        	throw new ReceptionException("校验用户身份证信息与手机号码失败");

        }
    }
    
    /**
     * <根据手机号查询用户信息>
     * <此接口出参用于校验补卡业务规则>
     * @param telnum
     * @param curMenuId
     * @param termInfo
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getSubscriberByTel(String telnum, String curMenuId, TerminalInfoPO termInfo)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
    	
        ReturnWrap rw = this.getSelfSvcCallHub().getSubscriberByTel(msgHeader,termInfo.getRegion());  
        
        //调用成功
        if (SSReturnCode.SUCCESS == rw.getStatus())
        {
        	CTagSet tagSet = (CTagSet)rw.getReturnObject();
        	
        	//返回用户信息
        	if("1".equals(tagSet.GetValue("qryResult")))
        	{
        		//返回查询的用户信息串
        		return tagSet.GetValue("subscriber");
        	}
        }
        
        throw new ReceptionException("查询用户信息失败");
    }
    
    /**
     * <补卡规则校验>
     * <功能详细描述>
     * @param telnum
     * @param curMenuId
     * @param termInfo
     * @param subscriber 用户信息
     * @see [类、类#方法、类#成员]
   	 * @remark create by sWX219697 2014-10-25 14:34:35 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public void checkReissueNum(String telnum, String curMenuId, TerminalInfoPO termInfo, String subscriber)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        ReturnWrap rw = this.getSelfSvcCallHub().checkReissueNum(msgHeader, subscriber);  
        
        //调用成功
        if (SSReturnCode.SUCCESS == rw.getStatus())
        {
        	if (rw.getReturnObject() instanceof CTagSet)
        	{
            	CTagSet tagSet = (CTagSet)rw.getReturnObject();
            	
            	//校验结果：校验通过 true；不通过，false
            	if(!"true".equalsIgnoreCase(tagSet.GetValue("retInfo")))
            	{
            		throw new ReceptionException(rw.getReturnMsg());
            	}
        	}
        }
        
        //调用失败，返回错误信息
        else
        {
        	throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
    /**
     * <补卡算费>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
   	 * @remark create by sWX219697 2014-10-28 16:53:20 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public Map<String,Object> countReissueFee(String servnum, String iccid, String blankCardNum, 
    		String curMenuId, TerminalInfoPO termInfo)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, servnum);
        
        ReturnWrap rw = this.getSelfSvcCallHub().countReissueFee(msgHeader,iccid,blankCardNum);  
        
        //调用成功
        if (SSReturnCode.SUCCESS == rw.getStatus())
        {
        	Map<String,Object> map = new HashMap<String,Object>();
        	
        	//费用明细
        	List<FeeConfirmPO> feeList = new ArrayList<FeeConfirmPO>();
        	
        	CRSet crSet = (CRSet)rw.getReturnObject();
        	
        	//总费用
        	BigDecimal totalFee = new BigDecimal("0");
        	
        	//遍历返回费用明细，并计算费用合计
        	for(int i=0; i < crSet.GetRowCount(); i++)
        	{
        		FeeConfirmPO feePO = new FeeConfirmPO();
        		
        		//费用:分
        		feePO.setFee(CommonUtil.fenToYuan(crSet.GetValue(i, 0)));
        		
        		//费用名称
        		feePO.setFeeName(crSet.GetValue(i, 1));
        		
        		//计算总费用
        		totalFee = totalFee.add(new BigDecimal(crSet.GetValue(i, 0)));
        		
        		feeList.add(feePO);
        	}
        	
        	//总费用
        	FeeConfirmPO feeConfirmPO = new FeeConfirmPO();
        	feeConfirmPO.setFee(CommonUtil.fenToYuan(totalFee.toString()));
        	feeConfirmPO.setFeeName("费用合计");
        	feeList.add(feeConfirmPO);
        	
        	map.put("feeList", feeList);
        	map.put("recFee", CommonUtil.fenToYuan(totalFee.toString()));
        	
        	return map;
        }
        
        //调用失败，返回错误信息
        else
        {
        	throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
    /**
     * <补卡提交>
     * <功能详细描述>
     * @param servnum
     * @param curMenuId
     * @param recFee
     * @param payType
     * @param simInfo
     * @param termInfo
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String reissueCommit(String servnum, String curMenuId, String recFee, 
    		String payType, String blankno, SimInfoPO simInfo, TerminalInfoPO termInfo)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, servnum);
        
        ReturnWrap rw = this.getSelfSvcCallHub().reissueCommit(msgHeader, recFee, payType, blankno, simInfo);

        //调用成功
        if (SSReturnCode.SUCCESS == rw.getStatus())
        {
        	CTagSet tagSet = (CTagSet)rw.getReturnObject();
        	
        	//流水号
        	return tagSet.GetValue("orderId");
        }
        
        throw new ReceptionException(rw.getReturnMsg());
    }
    
    

}
