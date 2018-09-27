package com.customize.hub.selfsvc.bean;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * <备卡接口调用>
 * <功能详细描述>
 * 
 * @author  c00233019
 * @version  [版本号, Oct 28, 2014]
 * @see  [相关类/方法]
 * @since  [产品/OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(备卡)]
 */
public class PrepareCardBean extends BaseBeanHubImpl
{
    /**
     * <查询手机号码是否有备卡>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
   	 * @remark create by 00233019 2014-10-28 14:34:42 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(备卡)
     */
    public void qryStoreCard(String subsID, String curMenuId, TerminalInfoPO termInfo)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, subsID);
        
        ReturnWrap rw = this.getSelfSvcCallHub().qryStoreCard(msgHeader,subsID);  
        
        //调用成功
        if (SSReturnCode.SUCCESS == rw.getStatus())
        {
        	if(rw.getReturnObject() instanceof CRSet)
        	{
        		CRSet crSet = (CRSet)rw.getReturnObject();
            	
            	//校验结果，如果有备卡结束备卡办理业务，无备卡继续业务
            	if(crSet!=null && crSet.GetRowCount()>0)
            	{
            		throw new ReceptionException("此号码已有备卡业务！");
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
     * <备卡算费>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
   	 * @remark create by c00233019 2014-10-29 16:53:20 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(备卡)
     */
    public String reckonrecfeeByStore(String servnum, String iccid, String curMenuId, TerminalInfoPO termInfo)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, servnum);
        
        ReturnWrap rw = this.getSelfSvcCallHub().reckonrecfeeByStore(msgHeader,servnum,iccid);  
        
        //调用成功
        if (SSReturnCode.SUCCESS == rw.getStatus())
        {
        	CTagSet tagSet = (CTagSet)rw.getReturnObject();
        	
        	//总费用
        	return tagSet.GetValue("totalFee");
        }
        
        //调用失败，返回错误信息
        else
        {
        	throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
    /**
     * <备卡提交>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
   	 * @remark create by c00233019 2014-10-29 16:53:20 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(备卡)
     */
    public String prepareCashCommit(String servnum, String iccid, String tMoney, String payType,
    		String curMenuId, TerminalInfoPO termInfo)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, servnum);
        
        ReturnWrap rw = this.getSelfSvcCallHub().prepareCashCommit(msgHeader,servnum,iccid,tMoney,payType);  
        
        String formNum = null;
        
        //调用成功
        if (SSReturnCode.SUCCESS == rw.getStatus())
        {
        	CTagSet tagSet = (CTagSet)rw.getReturnObject();
        	
        	//流水号
        	return tagSet.GetValue("formNum");
        }
        else
        {
        	throw new ReceptionException(rw.getReturnMsg());
        }
    }

}
