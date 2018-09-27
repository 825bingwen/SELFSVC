package com.customize.cq.selfsvc.chooseTel.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.cq.selfsvc.bean.ChooseTelBean;
import com.customize.cq.selfsvc.chooseTel.model.ServerNumPO;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.RegionInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 预约选号
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Apr 19, 2011]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChooseTelAction extends BaseAction
{
    
//    private static Log logger = LogFactory.getLog(ChooseTelAction.class);
//    
//    // 序列化
//    private static final long serialVersionUID = 1L;
//    
//    // begin zKF66389 findbus清零
//    // 当前菜单id
//    private String curMenuId = "";
//    // end zKF66389 findbus清零
//    
//    // 错误信息
//    private String error;
//    
//    // begin zKF66389 findbus清零
//    // 结果列表标题
//    private String[] serviceTitle;
//    // end zKF66389 findbus清零
//    
//    // 查询结果
//    private List result;
//    
//    // 区域编码
//    private String region;
//    
//    // begin zKF66389 findbus清零
//    // 区域名称
//    private String regionName;
//    // end zKF66389 findbus清零
//    
//    // 单位
//    private String orgid;
//    
//    // 查询类型
//    private String sele_rule;
//    
//    // 号码前缀
//    private String tel_prefix;
//    
//    // 号码后缀
//    private String tel_suffix;
//    
//    // 单位
//    private String org_id;
//    
//    // 证件类型
//    private String certtype;
//    
//    // begin zKF66389 findbus清零
//    // 证件号码
//    private String certId;
//    // end zKF66389 findbus清零
//    
//    // 客户名称
//    private String name;
//    
//    // 联系方式
//    private String contacttel;
//    
//    // 预约手机号吗
//    private String telnum;
//    
//    // begin zKF66389 findbus清零
//    // 终端信息
//    private String termName;
//    // end zKF66389 findbus清零
//    
//    // 打印地点
//    private String addr;
//    
//    // begin zKF66389 findbus清零
//    // 号码页数
//    private String pageFlag;
//    // end zKF66389 findbus清零
//    
//    // 总页数
//    private String pageCount;
//    
//    // begin zKF66389 findbus清零
//    private String orderId;
//    // end zKF66389 findbus清零
//    
//    // 存放全部手机号码数据
//    private List<ServerNumPO> tmpList = null;
//    
//    // 每页号码列表
//    private List<ServerNumPO> serverNumList = null;
//    
//    // 调用接口bean
//    private ChooseTelBean chooseTelBean;
//    
//    /**
//     * 选择查询规则
//     * <功能详细描述>
//     * @param request
//     * @return
//     * @see [类、类#方法、类#成员]
//     */
//    public String selectRule()
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("selectRule Starting...");
//        }
//        
//        // 获取session
//        HttpSession session = getRequest().getSession(true);
//        
//        // 获取终端机信息
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        List<RegionInfoPO> regionListTmp = (List)PublicCache.getInstance().getCachedData(Constants.REGION_INFO);
//        
//        for (RegionInfoPO regionInfoPO:regionListTmp)
//        {
//            if (terminalInfoPO.getRegion().equals(regionInfoPO.getRegion()))
//            {
//            	region = regionInfoPO.getRegion();
//            	regionName = regionInfoPO.getRegionname();
//            	orgid = regionInfoPO.getOrgid();
//            }
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("selectRule End");
//        }
//        
//        return "selectRule";       
//    }
//    
//    /**
//     * 选择查询规则
//     * <功能详细描述>
//     * @param request
//     * @return
//     * @see [类、类#方法、类#成员]
//     */
//    public String inputTelnumByRule()
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("inputTelNumByRule Starting...");
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("inputTelNumByRule End");
//        }
//        
//        return "inputTelNumByRule";       
//    }
//    
//    /**
//     * 查询需要预定的号码
//     * <功能详细描述>
//     * @return
//     * @see [类、类#方法、类#成员]
//     */
//    public String telNumResult()
//    {
//        // 开始记录日志
//        logger.debug("telNumResult start...");
//        
//        // 获取session
//        HttpSession session = getRequest().getSession();
//        
//        // 获取终端机信息
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
//        
//        if (tel_prefix == null)
//        {
//            tel_prefix = "";
//        }
//        
//        if (tel_suffix == null)
//        {
//            tel_suffix = "";
//        }
//        
//        if ("2".equals(sele_rule))// 查询类型 2：按前缀查询 3：按后缀查询
//        {
//            while (tel_prefix.length() < 7)
//            {
//                tel_prefix = tel_prefix + "_";
//            }        
//        }
//        else if("3".equals(sele_rule))
//        {
//            while (tel_suffix.length() < 4)
//            {
//                tel_suffix = tel_suffix + "_";
//            }
//        }
//        
//        // 查询号码信息列表
//        Map mapResult = this.chooseTelBean.qryChooseTel(terminalInfoPO, curMenuId, orgid, sele_rule, tel_prefix, tel_suffix, region);
//        
//        //未查询到数据，进入错误页面
//        if (mapResult == null || mapResult.get("returnObj") == null)
//        {
//            String errMsg = "";
//            
//            if ("".equals(tel_prefix.trim()) && "".equals(tel_suffix.trim()))
//            {
//                errMsg = "未查询到符合条件的记录";
//            }
//            else if ("2".equals(sele_rule))// 查询类型 2：按前缀查询 3：按后缀查询
//            {
//                errMsg = "未查询到符合条件的记录(前缀：" + tel_prefix + ")";               
//            }
//            else if("3".equals(sele_rule))
//            {
//                errMsg = "未查询到符合条件的记录(后缀：" + tel_suffix + ")";
//            }
//            
//            getRequest().setAttribute("errormessage", errMsg); 
//            
//            this.createRecLog(Constants.BUSITYPE_CHOOSE_TEL_NUM, "0", "0", "1", errMsg);
//            
//            return "error";
//        }
//        
//        // 数据存放到临时变量中（为做内存分页准备）
//        CRSet crset = (CRSet) mapResult.get("returnObj");
//        
//        tmpList = new ArrayList<ServerNumPO>();
//        ServerNumPO serverNumPO = null;        
//        for (int i = 0; i < crset.GetRowCount(); i++)
//        {
//            serverNumPO = new ServerNumPO();
//            serverNumPO.setTelnum(crset.GetValue(i, 0));// 手机号码
//            serverNumPO.setFee(crset.GetValue(i, 2));
//            serverNumPO.setOrg_id(crset.GetValue(i, 3));
//                
//            tmpList.add(serverNumPO);
//        }       
//        
//        // 每页显示号码数
//        String pageviewnum = "21";
//        
//        if (pageFlag == null || "".equals(pageFlag))
//        {
//        	pageFlag = "1";
//        }
//        
//        // 结束行数
//        int endRowNum = Integer.parseInt(pageviewnum) * Integer.parseInt(pageFlag);
//        
//        // 开始条数
//        int startRowNum = endRowNum - Integer.parseInt(pageviewnum);
//        
//        // 总页数
//        pageCount = "0";
//        if (tmpList.size() % Integer.parseInt(pageviewnum) != 0)
//        {
//            pageCount = (tmpList.size() / Integer.parseInt(pageviewnum) + 1) + "";
//        }
//        else
//        {
//            pageCount = (tmpList.size() / Integer.parseInt(pageviewnum)) + "";
//        }
//        
//        // 从内存中取得每页数据
//        serverNumList = new ArrayList<ServerNumPO>();
//        for (int i = startRowNum; i < endRowNum && i < tmpList.size(); i++)
//        {
//            serverNumList.add((ServerNumPO)tmpList.get(i));
//        }
//        
//        // 填冲不满一页的其它对象，防止页面变型
//        if (Integer.parseInt(pageFlag) == Integer.parseInt(pageCount))
//        {
//            for (int i = 0; i < endRowNum - tmpList.size(); i++)
//            {
//                // 写入空对象
//                ServerNumPO po = new ServerNumPO();
//                po.setTelnum("");
//                
//                serverNumList.add(po);
//            }
//        }
//        
//        logger.debug("telNumResult End ...");
//
//        return "telNumResult";
//    }
//
//    /**
//     * 输入身份证号码页面
//     * <功能详细描述>
//     * @return
//     * @see [类、类#方法、类#成员]
//     */
//    public String inputCertid()
//    {
//        // 开始记录日志
//        logger.debug("inputCertid start...");
//
//        logger.debug("inputCertid End ...");
//
//        return "inputCertid";
//    }
//    
//    /**
//     * 预定号码
//     * <功能详细描述>
//     * @return
//     * @see [类、类#方法、类#成员]
//     */
//    public String makeSureTel()
//    {
//        // 开始记录日志
//        logger.debug("makeSureTel start...");
//        
//        // 获取session
//        HttpSession session = getRequest().getSession();
//        
//        // 获取终端机信息
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 执行预定
//        Map mapResult = this.chooseTelBean.chooseTel(terminalInfoPO, curMenuId, telnum, region, org_id, certtype, certId, name, contacttel);
//       
//        if (mapResult != null && mapResult.size() > 1)
//        {  
//            CTagSet tagSet = (CTagSet) mapResult.get("returnObj");
//            orderId = tagSet.GetValue("orderid");
//            
//            this.createRecLog(Constants.BUSITYPE_CHOOSE_TEL_NUM, "0", "0", "0", "号码预订成功");
//            
//            return "makeSureTel";
//        }
//        else
//        {
//            String errMsg = "";
//            
//            if (mapResult != null)
//            {
//                errMsg = (String) mapResult.get("returnMsg");
//            }
//            
//            if (errMsg == null || "".equals(errMsg.trim()))
//            {
//                errMsg = "号码" + telnum + "预订失败";
//            }
//            
//            getRequest().setAttribute("errormessage", errMsg);
//            
//            // 记录错误日志
//            this.createRecLog(Constants.BUSITYPE_CHOOSE_TEL_NUM, "0", "0", "1", errMsg);
//        }
//        
//        logger.debug("makeSureTel End ...");
//
//        return "error";
//    }
//    
//    // begin zKF66389 findbus清零
//    public String getCurMenuId() {
//		return curMenuId;
//	}
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
//	// end zKF66389 findbus清零
//
//	public String getError()
//    {
//        return error;
//    }
//
//    public void setError(String error)
//    {
//        this.error = error;
//    }
//
//    public String[] getServiceTitle() {
//		return serviceTitle;
//	}
//
//	public void setServiceTitle(String[] serviceTitle) {
//		this.serviceTitle = serviceTitle;
//	}
//
//	public List getResult()
//    {
//        return result;
//    }
//
//    public void setResult(List result)
//    {
//        this.result = result;
//    }
//
//    public ChooseTelBean getChooseTelBean()
//    {
//        return chooseTelBean;
//    }
//
//    public void setChooseTelBean(ChooseTelBean chooseTelBean)
//    {
//        this.chooseTelBean = chooseTelBean;
//    }
//
//    public String getRegion()
//    {
//        return region;
//    }
//
//    public void setRegion(String region)
//    {
//        this.region = region;
//    }
//
//    public String getRegionName() {
//		return regionName;
//	}
//
//	public void setRegionName(String regionName) {
//		this.regionName = regionName;
//	}
//
//	public String getOrgid()
//    {
//        return orgid;
//    }
//
//    public void setOrgid(String orgid)
//    {
//        this.orgid = orgid;
//    }
//
//    public String getSele_rule()
//    {
//        return sele_rule;
//    }
//
//    public void setSele_rule(String sele_rule)
//    {
//        this.sele_rule = sele_rule;
//    }
//
//    public String getTel_prefix()
//    {
//        return tel_prefix;
//    }
//
//    public void setTel_prefix(String tel_prefix)
//    {
//        this.tel_prefix = tel_prefix;
//    }
//
//    public String getTel_suffix()
//    {
//        return tel_suffix;
//    }
//
//    public void setTel_suffix(String tel_suffix)
//    {
//        this.tel_suffix = tel_suffix;
//    }
//
//    public List<ServerNumPO> getServerNumList()
//    {
//        return serverNumList;
//    }
//
//    public void setServerNumList(List<ServerNumPO> serverNumList)
//    {
//        this.serverNumList = serverNumList;
//    }
//
//    public String getOrg_id()
//    {
//        return org_id;
//    }
//
//    public void setOrg_id(String org_id)
//    {
//        this.org_id = org_id;
//    }
//
//    public String getCerttype()
//    {
//        return certtype;
//    }
//
//    public void setCerttype(String certtype)
//    {
//        this.certtype = certtype;
//    }
//
//    public String getName()
//    {
//        return name;
//    }
//
//    public void setName(String name)
//    {
//        this.name = name;
//    }
//
//    public String getContacttel()
//    {
//        return contacttel;
//    }
//
//    public void setContacttel(String contacttel)
//    {
//        this.contacttel = contacttel;
//    }
//
//    public String getCertId() {
//		return certId;
//	}
//
//	public void setCertId(String certId) {
//		this.certId = certId;
//	}
//
//	public String getTelnum()
//    {
//        return telnum;
//    }
//
//    public void setTelnum(String telnum)
//    {
//        this.telnum = telnum;
//    }
//
//    public String getTermName() {
//		return termName;
//	}
//
//	public void setTermName(String termName) {
//		this.termName = termName;
//	}
//
//	public String getAddr()
//    {
//        return addr;
//    }
//
//    public void setAddr(String addr)
//    {
//        this.addr = addr;
//    }
//
//    public String getPageFlag() {
//		return pageFlag;
//	}
//
//	public void setPageFlag(String pageFlag) {
//		this.pageFlag = pageFlag;
//	}
//
//	public String getPageCount()
//    {
//        return pageCount;
//    }
//
//    public void setPageCount(String pageCount)
//    {
//        this.pageCount = pageCount;
//    }
//
//    public List<ServerNumPO> getTmpList()
//    {
//        return tmpList;
//    }
//
//    public void setTmpList(List<ServerNumPO> tmpList)
//    {
//        this.tmpList = tmpList;
//    }
//
//	public String getOrderId() {
//		return orderId;
//	}
//
//	public void setOrderId(String orderId) {
//		this.orderId = orderId;
//	}

}
