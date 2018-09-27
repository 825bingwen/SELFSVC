package com.customize.sd.selfsvc.packageService.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.PrivServPackBean;
import com.customize.sd.selfsvc.packageService.model.PrivServPackPO;
import com.customize.sd.selfsvc.packageService.service.PrivServPackService;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.PagedAction;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 特惠业务包
 * @author hWX5316476
 * @version  [版本号, 2014-12-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark OR_SD_201410_482_SD_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
 */
public class PrivServPackAction extends PagedAction
{
    /**
     * 序列化
     */ 
    private static final long serialVersionUID = 1L;
    
    /**
     * 日志
     */ 
    public static final Log logger = LogFactory.getLog(PrivServPackAction.class);
    
    /**
     * 当前菜单
     */
    private String curMenuId = "";
    
    /**
     * 地区
     */
    private String region;
    
    /**
     * NCODE
     */
    private String ncode;
    
    /**
     * 生效时间
     */
    private String effDate;
    
    /**
     * 失效时间
     */
    private String endDate;
    
    /**
     * 特惠业务包PO
     */
    private transient PrivServPackPO privServPackPO;
    
    /**
     * 特惠业务包列表
     */
    private List<PrivServPackPO> privServPackList;
    
    /**
     * 业务service
     */
    private transient PrivServPackService privServPackService;
    
    /**
     * bean
     */
    private transient PrivServPackBean privServPackBean;
    
    /**
     * 查询可选特惠业务包
     * @return
     */
    public String qryPrivServPack()
    {
        // 获取用户信息
        NserCustomerSimp  customer = getCustomerSimp();
        
        // 设置特惠业务查询条件
        privServPackPO = new PrivServPackPO();
        privServPackPO.setRegion(customer.getRegionID());
        privServPackPO.setBrand(customer.getBrandID());
        
        // 查询可选的特惠业务包
        privServPackList = privServPackService.qryPrivServPackList(privServPackPO);
        
        // 分页
        if (null != privServPackList && privServPackList.size() >  0)
        {
            privServPackList = getPageList(privServPackList, 6);
        }
        else
        {
            // 设置错误信息
            getRequest().setAttribute("errormessage", "对不起，没有适合您的特惠业务包！"); 
            
            return ERROR;
        }
        
        return "qryPrivServPack";
    }
    
    /**
     * 查询可选特惠业务包
     * @return
     */
    public String qryPrivServPackDetail()
    {  
        // 设置特惠业务查询条件
        privServPackPO = new PrivServPackPO();
        privServPackPO.setNcode(ncode);
        privServPackPO.setRegion(region);
        
        // 查询特惠业务描述
        privServPackPO = privServPackService.qryPrivServPackDetail(privServPackPO);
        
        return "privServPackDetail";
    }
    
    /**
     * 特惠业务办理
     * @return
     */
    public String privServPackRec()
    {
        // 获取用户信息
        NserCustomerSimp  customer = this.getCustomerSimp();
        
        // 获取终端机信息
        TerminalInfoPO termInfo = this.getTerminalInfoPO();
        
        // 设置特惠业务包属性
        privServPackPO = new PrivServPackPO();
        privServPackPO.setNcode(ncode);
        privServPackPO.setEffDate(effDate);
        privServPackPO.setEndDate(endDate);
        
        try
        {
            privServPackBean.privServPackRec(termInfo, customer, curMenuId, privServPackPO);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            
            // 设置错误信息
            getRequest().setAttribute("errormessage", "对不起，办理失败！"+e.getMessage()); 
            
            // 记录错误日志信息
            this.createRecLog(Constants.SH_PRIV_PACK_REC, "0", "0", "1", e.getMessage());
            
            return ERROR;
        }
        
        // 记录错误日志信息
        this.createRecLog(Constants.SH_PRIV_PACK_REC, "0", "0", "0", "特惠业务包办理成功！");
        
        return SUCCESS;
    }
    
   

    public PrivServPackPO getPrivServPackPO()
    {
        return privServPackPO;
    }

    public void setPrivServPackPO(PrivServPackPO privServPackPO)
    {
        this.privServPackPO = privServPackPO;
    }

    public List<PrivServPackPO> getPrivServPackList()
    {
        return privServPackList;
    }

    public void setPrivServPackList(List<PrivServPackPO> privServPackList)
    {
        this.privServPackList = privServPackList;
    }

    public PrivServPackService getPrivServPackService()
    {
        return privServPackService;
    }

    public void setPrivServPackService(PrivServPackService privServPackService)
    {
        this.privServPackService = privServPackService;
    }

    public PrivServPackBean getPrivServPackBean()
    {
        return privServPackBean;
    }

    public void setPrivServPackBean(PrivServPackBean privServPackBean)
    {
        this.privServPackBean = privServPackBean;
    }

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getNcode()
    {
        return ncode;
    }

    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }

    public String getEffDate()
    {
        return effDate;
    }

    public void setEffDate(String effDate)
    {
        this.effDate = effDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
}
