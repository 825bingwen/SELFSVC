package com.customize.hub.selfsvc.backInfo.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.backInfo.model.EcashSheduVo;
import com.customize.hub.selfsvc.bean.BackInfoBean;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class EcashRetInfoQueryAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    // modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    //private static final Logger log = Logger.getLogger(EcashRetInfoQueryAction.class);
    private static Log log = LogFactory.getLog(EcashRetInfoQueryAction.class);
    // modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    
    // 开始日期
    private String startDate;
    
    // 当前菜单
    private String telnum;
    
    // 结束日期
    private String endDate;
    
    // 当前菜单
    private String curMenuId;
    
    // 错误信息
    private String errormessage = "";
    
    // 接口调用
    private BackInfoBean backInfoBean;
    
    // 查询结果
    private List<EcashSheduVo> totalList=new ArrayList<EcashSheduVo>();
    // 查询总记录数
    private List<EcashSheduVo> resList=new ArrayList<EcashSheduVo>();
    
    //分页信息
    // 第几页
    private int page = 1;
    
    // 每页显示条数
    private int pagesize = 6;
    
    // 总条数
    private int totalsize = 0;
    
    // 总页数
    private int pagenum = 0;
  
    public String ecashReturnInfoQuery()
    {
        // 记录开始日志
        log.debug("EcashRetInfoQueryAction---ecashReturnInfoQuery()...");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // add begin g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 客户手机号
        String servnumber = customer.getServNumber();
        
        // 定位到错误页面
        String forward = "error";
        
     
        if(startDate!=null&&!"".equals(startDate)&&!startDate.equals("null")&&endDate!=null&&!"".equals(endDate)&&!endDate.equals("null")){
            ReturnWrap rw = (ReturnWrap)backInfoBean.ecashReturnInfoQuery(startDate,endDate,
                    terminalInfoPO,
                    customer,
                    curMenuId);
            if(rw.getStatus()!=1&&(9000001!=rw.getErrcode())){
                errormessage=rw.getReturnMsg();
                
            }else{
                
                totalList=(List<EcashSheduVo>)rw.getReturnObject(); 
                //if(totalList!=null&&totalList.size()>0){
                forward = "ecashReturnInfoList";  
                   // pagesize = 5;
                    //displayPage();
               // }
            }
           
        }else{
            forward = "ecashDateInput";
            errormessage="查询起始，结束时间不能为空！" ;
        }
        
        return forward;
      }
//    // 产品分页
//    private void displayPage()
//    {
//        if (page == 0)
//        {
//            page = 1;
//        }
//        
//        // 清空数据
//        resList.clear();
//        
//        // 计算总条数
//        totalsize = totalList.size();
//        
//        // 计算总页数
//        if (totalsize % pagesize > 0)
//        {
//            pagenum = totalsize / pagesize + 1;
//        }
//        else
//        {
//            pagenum = totalsize / pagesize;
//        }
//        
//        // 开始条数
//        int startNum = pagesize * (page - 1) + 1;
//        
//        // 结束条数
//        int endNum = pagesize * page;
//        
//        // 开始条数
//        for (int i = startNum; i <= endNum; i++)
//        {
//            if (i <= totalsize)
//            {
//                this.resList.add(totalList.get(i - 1));
//            }
//        }
//        
//        if (this.resList.size() < pagesize)
//        {
//            int size = resList.size();
//            EcashSheduVo shTpltTempletVO = new EcashSheduVo();
//            for (int i = size; i < pagesize; i++)
//            {
//                
//                resList.add(shTpltTempletVO);
//            }
//        }
//    }
    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getTelnum()
    {
        return telnum;
    }

    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getCurMenuId() {
		return curMenuId;
	}
	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}
	public String getErrormessage()
    {
        return errormessage;
    }

    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }

    public BackInfoBean getBackInfoBean()
    {
        return backInfoBean;
    }

    public void setBackInfoBean(BackInfoBean backInfoBean)
    {
        this.backInfoBean = backInfoBean;
    }

    public List getResList()
    {
        return resList;
    }


    public List<EcashSheduVo> getTotalList()
    {
        return totalList;
    }
    public void setTotalList(List<EcashSheduVo> totalList)
    {
        this.totalList = totalList;
    }
    public int getPage()
    {
        return page;
    }
    public void setPage(int page)
    {
        this.page = page;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public int getPagesize()
    {
        return pagesize;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPagesize(int pagesize)
    {
        this.pagesize = pagesize;
    }
    public int getTotalsize()
    {
        return totalsize;
    }
    public void setTotalsize(int totalsize)
    {
        this.totalsize = totalsize;
    }
    public int getPagenum()
    {
        return pagenum;
    }
    public void setPagenum(int pagenum)
    {
        this.pagenum = pagenum;
    }
    public void setResList(List<EcashSheduVo> resList)
    {
        this.resList = resList;
    }
    
 }
