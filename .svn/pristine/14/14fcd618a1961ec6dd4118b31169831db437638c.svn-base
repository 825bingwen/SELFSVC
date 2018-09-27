package com.customize.hub.selfsvc.bean;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.customize.hub.selfsvc.backInfo.model.EcashSheduVo;
import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class BackInfoBean extends BaseBeanHubImpl

{
    public Map queryBackInfo(String startDate, String endDate, TerminalInfoPO terminalInfoPO,
            NserCustomerSimp customer, String curMenuId, String flag)
    {
        Map paramMap = new HashMap();
        Map map = new HashMap();
        // 设置开始日期
        paramMap.put("startDate", startDate);
        
        // 设置结束日期
        paramMap.put("endDate", endDate);
        
        // 设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("servnumber", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        //设置查询标志
        paramMap.put("qryflag", flag);
        List resList = new ArrayList();
        
        ReturnWrap rw = this.getSelfSvcCallHub().qryAllBackInfo(paramMap);
        
        // begin zKF66389 9月份findbugs修改
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        // end zKF66389 9月份findbugs修改
        {
            String title = "";
            
            //--------ebus接口改造---------------
			CRSet rSet = new CRSet();
			if(rw.getReturnObject() instanceof Vector)
			{
				Vector vc=(Vector)rw.getReturnObject();
				if(vc!=null && vc.size()>1)
				{
					rSet = (CRSet)vc.get(1);
				}
			}
			if(rw.getReturnObject() instanceof CRSet)
			{
				rSet =(CRSet)rw.getReturnObject();
			}
        	//----------------------------
			
//            CRSet rSet = (CRSet)rw.getReturnObject();
            //
            if (rSet.getData() != null && rSet.GetRowCount() > 0)
            {
                for (int i = 0; i < rSet.GetRowCount(); i++)
                {
                    List innerList = new ArrayList();
                    for (int j = 0; j < rSet.GetColumnCount(); j++)
                    {
                        
                        switch (j){
                         
                         case 1:
                                String staDate=(String)rSet.getData().get(i * rSet.GetColumnCount() + j);
                                String enDate=(String)rSet.getData().get(i * rSet.GetColumnCount() + j+1);
                                String temp=null;
                                if(flag.equals("0")){
                                    temp=(staDate!=null&&!staDate.equals(""))?staDate.substring(0,4)+"年"+staDate.substring(4,6)+"月-":"-";
                                    temp+=(enDate!=null&&!enDate.equals(""))?enDate.substring(0,4)+"年"+enDate.substring(4,6)+"月":"";
                                    
                                }else{
                                    temp=(staDate!=null&&!staDate.equals(""))?staDate.substring(0,4)+"年"+staDate.substring(4,6)+"月"+(staDate.length()>6?staDate.substring(6,8)+"日":"")+"-":"-";
                                    temp+=(enDate!=null&&!enDate.equals(""))?enDate.substring(0,4)+"年"+enDate.substring(4,6)+"月"+(enDate.length()>6?enDate.substring(6,8)+"日":""):""; 
                                    
                                }
                                 
                                innerList.add(temp);
                                j++;break;
                         case 3:DateFormat df=new SimpleDateFormat("yyyyMMdd hh:mm:ss");
                              GregorianCalendar gc=new GregorianCalendar();
                                Date date;
                                try
                                {
                                    date = df.parse((String)rSet.getData().get(i * rSet.GetColumnCount() + j));
                                }
                                catch (ParseException e)
                                {
                                    // TODO Auto-generated catch block
                                    date=null;
                                    e.printStackTrace();
                                }
                                  if(date==null){
                                      innerList.add("");
                                      
                                  }else{
                                      
                                      gc.setTime(date);
                                      innerList.add(gc.get(gc.YEAR)+"年"+(((gc.get(gc.MONTH)+1))<10?"0"+(gc.get(gc.MONTH)+1):(gc.get(gc.MONTH)+1))+"月"+(
                                      gc.get(gc.DAY_OF_MONTH)<10?""+gc.get(gc.DAY_OF_MONTH):gc.get(gc.DAY_OF_MONTH))+"日");
                                      
                                  }
                               break;
                         case 5:
                             if(innerList.get(3).equals("1")){
                                 innerList.add("cash"+rSet.getData().get(i * rSet.GetColumnCount() + j));break;
                                 
                             }else{
                                 innerList.add(rSet.getData().get(i * rSet.GetColumnCount() + j));break;
                                 
                             }
                             
//                         case 0:innerList.add(rSet.getData().get(i * rSet.GetColumnCount() + j));break;
//                         case 4:innerList.add(rSet.getData().get(i * rSet.GetColumnCount() + j));break;
//                         case 6:innerList.add(rSet.getData().get(i * rSet.GetColumnCount() + j));break;
//                         case 8:innerList.add(rSet.getData().get(i * rSet.GetColumnCount() + j));break;
                         default: innerList.add(rSet.getData().get(i * rSet.GetColumnCount() + j));
                        
                            
                        }
                       
                        
                    }
                    resList.add(innerList);
                }
                
            }
        }
        //ReturnWrap rw =new ReturnWrap();
        map.put("returnObj", resList);
        // 设置返回信息
        String returnMsg = rw.getReturnMsg();
        map.put("returnMsg", returnMsg);
        
        return map;
        
    }
    /**
     * 电子券返还信息查询
     * 
     * @param [参数1] [参数1说明]
     * @param [参数2] [参数2说明]
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark create yKF73963（2013-03-18） OR_HUB_201301_780  关于BOSS触发手机支付电子券的分月赠送的需求 
     * 
     */
    public ReturnWrap ecashReturnInfoQuery(String startDate, String endDate, TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId)
    {
        Map paramMap = new HashMap();
        Map map = new HashMap();
        // 设置开始时间
        paramMap.put("startDate",startDate);
        // 设置结束时间
        
        paramMap.put("endDate", endDate);
        // 设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("servnumber", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        // 设置地区
        paramMap.put("region", customer.getRegionID());
   
        List<EcashSheduVo> resList = new ArrayList<EcashSheduVo>();
        
        ReturnWrap rw = this.getSelfSvcCallHub().qryEcashReturnInfo(paramMap);
     
        
        if (rw != null && (rw.getStatus() == SSReturnCode.SUCCESS||9000001 == rw.getErrcode()))
        {
            
            CRSet rSet = (CRSet)rw.getReturnObject();
            //
            if (rSet!=null&&rSet.getData() != null && rSet.GetRowCount() > 0)
            {//格式化对象 
                DecimalFormat nFormat=(DecimalFormat)NumberFormat.getInstance();
                nFormat.applyLocalizedPattern("0.00");
                for (int i = 0; i < rSet.GetRowCount(); i++)
                {    EcashSheduVo ecashSheduVo=new EcashSheduVo();
                   
                    //序号
                   
                    ecashSheduVo.setOrderid(String.valueOf(i+1));
                    //起止时间
                    String startdate=(String)rSet.getData().get(i * rSet.GetColumnCount() + 0);
                    String enddate=(String)rSet.getData().get(i * rSet.GetColumnCount() + 1);
                    ecashSheduVo.setFsdatetedate((startdate==null||startdate.equals("")?"":startdate.substring(0,4)+"年"+startdate.substring(4,6)+"月")+"-"+(enddate==null||enddate.equals("")?"":enddate.substring(0,4)+"年"+enddate.substring(4,6)+"月"));
                  
                    //返还时间
                    String backDate= (String)rSet.getData().get(i * rSet.GetColumnCount() + 2);
                    backDate=backDate==null||backDate.equals("")?"":backDate.substring(0,4)+"年"+backDate.substring(4,6)+"月"+backDate.substring(6,8)+"日";
                    ecashSheduVo.setProctime(backDate);
                  
                    //返还金额
                    String ecashvalue=(String)rSet.getData().get(i * rSet.GetColumnCount() + 3);
                    ecashvalue=ecashvalue==null||"".equals(ecashvalue)?"0":ecashvalue;
                    ecashSheduVo.setEcashvalue(nFormat.format(Double.valueOf(((String)rSet.getData().get(i * rSet.GetColumnCount() + 3)))/100));
                    
                    //剩余金额
                    String leftvalue=(String)rSet.getData().get(i * rSet.GetColumnCount() + 4);
                    leftvalue=leftvalue==null||"".equals(leftvalue)?"0":leftvalue;
                    ecashSheduVo.setLeftvalue(nFormat.format(Double.valueOf(leftvalue)/100));
                  
                    //活动名称
                    ecashSheduVo.setActivityname((String)rSet.getData().get(i * rSet.GetColumnCount() + 5));
                  
        
                    resList.add(ecashSheduVo);
                    Collections.sort(resList);
                }
                
            }
        }
        rw.setReturnObject(resList);
   
        
        return rw;
        
    }
    
    public String getDateContainsLastDay(String yyyymm){
        GregorianCalendar gc=new GregorianCalendar();
        DateFormat df=new SimpleDateFormat("yyyyMM");
        Date date=null;
        try
        {
             date=df.parse(yyyymm);
        }
        catch (ParseException e)
        {
        	// begin zKF66389 9月份findbugs修改
        	if (e != null)
        	{
        		e.printStackTrace();
        	}
        	// end zKF66389 9月份findbugs修改
        }
        gc.setTime(date);
        int lastDay=gc.getActualMaximum(Calendar.DAY_OF_MONTH);
        return yyyymm+(lastDay<10?0+Integer.valueOf(lastDay).toString():lastDay);
        
        
        
    }
}