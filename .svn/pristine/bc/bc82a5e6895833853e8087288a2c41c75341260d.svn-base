package com.customize.hub.selfsvc.bean;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import java.util.Map;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.customize.hub.selfsvc.prodInstall.model.SimVO;
import com.customize.hub.selfsvc.prodInstall.model.TelnumbeVO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
/*
* @filename: ChoSerNuAOUserBean.java
* @  版权
* @brif:  关于选号开户
* @author: yedengchu
* @de: （2012/07/12） 
* @description: 
* @remark: create bp6Xdpvh OR_HUB_201202_1192
*/
@SuppressWarnings("unchecked")
public class ChoSerNuAOUserBean extends BaseBeanHubImpl
{
    
	public Map certCardInfo(String certType, String certId,String pesSubsNum,TerminalInfoPO terminalInfoPO,
             String curMenuId)
    {
        Map paramMap = new HashMap();
       
        // 设置证件号码
        paramMap.put("certtype", certType);
        // 设置证件类型
        paramMap.put("certid", certId);
        // 设置相关号码
        paramMap.put("pessubsnum", pesSubsNum);
       //设置操作员
        paramMap.put("curOper", terminalInfoPO.getOperid());
        // 设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        // 设置终端机的region
        paramMap.put("region", terminalInfoPO.getRegion());
        ReturnWrap rw = this.getSelfSvcCallHub().certCardInfo(paramMap);
        Map map =null;
       if(rw!=null){
           map = new HashMap();
           // 设置返回结果
           if(rw.getStatus()==1){
               map.put("returnObj","sucess");
           }else{
               map.put("returnObj", "fail");
               
           }
           
          
           
           // 设置返回信息
           map.put("returnMsg", rw.getReturnMsg());
           
           
       }
    
      return map;
}
    public Map getSimInfo(String iccid,TerminalInfoPO terminalInfoPO,
            String curMenuId)
   {
       Map paramMap = new HashMap();
      
       // SIM卡iccid
       paramMap.put("iccid", iccid);
       
      //设置操作员
       paramMap.put("curOper", terminalInfoPO.getOperid());
       // 设置终端机id
       paramMap.put("atsvNum", terminalInfoPO.getTermid());
       // 设置当前菜单
       paramMap.put("curMenuId", curMenuId);
       // 设置终端机的region
       paramMap.put("region", terminalInfoPO.getRegion());
       ReturnWrap rw = this.getSelfSvcCallHub().getSimInfo(paramMap);
       Map map =null;
      if(rw!=null){
          map = new HashMap();
          // 设置返回结果
          if(rw.getStatus()==1){
              map.put("returnObj",rw.getReturnObject());
          }
          // 设置返回信息
          map.put("returnMsg", rw.getReturnMsg());
          }
   return map;
}
    public Map getTelnumbers(TerminalInfoPO terminalInfoPO,
            String curMenuId,SimVO simVO,String band)
   {
       Map paramMap = new HashMap();
       paramMap.put("fitmod", "___________");
       paramMap.put("hlrid",simVO.getHlrid());
       paramMap.put("groupid", simVO.getGroupid());
       paramMap.put("istoretype","0");
       paramMap.put("prdtype", band);
       paramMap.put("teltype", "rsclTgsm");
       paramMap.put("maxcount", "100");
       paramMap.put("pur", "rsupSal");
     
      //设置操作员
       paramMap.put("curOper", terminalInfoPO.getOperid());
       // 设置终端机id
       paramMap.put("atsvNum", terminalInfoPO.getTermid());
       // 设置当前菜单
       paramMap.put("curMenuId", curMenuId);
       // 设置终端机的region
       paramMap.put("region", terminalInfoPO.getRegion());
       ReturnWrap rw = this.getSelfSvcCallHub().getTelnumbs(paramMap);
       Map map =null;
      if(rw!=null){
          map = new HashMap();
          // 设置返回结果
          if(rw.getStatus()==1){
        	  //---------ebus接口改造----------
        	  CRSet crset= new CRSet();
        	  if(rw.getReturnObject() instanceof Vector)
        	  {
        		  Vector vc=(Vector)rw.getReturnObject();
        		  if(vc!=null && vc.size()>1)
        		  {
        			  crset= (CRSet)vc.get(1);
        		  }
        	  }
        	  if(rw.getReturnObject() instanceof CRSet)
        	  {
        		  crset=(CRSet)rw.getReturnObject();
        	  }
        	  //----------------------------
        	  
//        	  CRSet crset=(CRSet)rw.getReturnObject();
              List<TelnumbeVO> telnumbeVOs=new ArrayList<TelnumbeVO>();
              if(crset!=null&&crset.GetRowCount()>0){
                  
                  int j=0;
             for(int i=0;i<crset.GetRowCount();i++){
                 j=0;
                 TelnumbeVO telnumbeVO=new TelnumbeVO();
                     
                     telnumbeVO.setTelnum((String)crset.getData().get(crset.GetColumnCount()*i+(j++)));
                     telnumbeVO.setFee((String)crset.getData().get(crset.GetColumnCount()*i+(j++)));
                     telnumbeVO.setRegion((String)crset.getData().get(crset.GetColumnCount()*i+(j++)));
                     telnumbeVO.setAddress((String)crset.getData().get(crset.GetColumnCount()*i+(j++)));
                     telnumbeVOs.add(telnumbeVO);
                 }
                }
              map.put("returnObj",telnumbeVOs);
          }
          // 设置返回信息
          map.put("returnMsg", rw.getReturnMsg());
          }
   return map;
}
    public Map chooseTheTelNum(String telnum, TerminalInfoPO terminalInfoPO,
            String curMenuId)
   {
       Map paramMap = new HashMap();
      
       //暂选的电话号码
       paramMap.put("telnum", telnum);
     
      //设置操作员
       paramMap.put("curOper", terminalInfoPO.getOperid());
       // 设置终端机id
       paramMap.put("atsvNum", terminalInfoPO.getTermid());
       // 设置当前菜单
       paramMap.put("curMenuId", curMenuId);
       // 设置终端机的region
       paramMap.put("region", terminalInfoPO.getRegion());
       ReturnWrap rw = this.getSelfSvcCallHub().chooseTheTelnum(paramMap);
       Map map =null;
      if(rw!=null){
          map = new HashMap();

          // 设置返回结果
          if(rw.getStatus()==1){
              map.put("returnObj","sucess");
          }else{
              map.put("returnObj", "fail");
              
          }
          
         
          
          // 设置返回信息
          map.put("returnMsg", rw.getReturnMsg());
          
          
      }
   
     return map;
} 
}