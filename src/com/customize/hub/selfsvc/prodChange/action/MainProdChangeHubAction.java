/*
 * 文 件 名:  MainProdChangeHubAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人: yKF70747
 * 修改时间:  Apr 12, 2012
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.prodChange.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.MainProdChangeBean;
import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.prodChange.model.MainProdTempletInfoPO;
import com.customize.hub.selfsvc.prodChange.model.MainProdTempletPO;
import com.customize.hub.selfsvc.prodChange.model.ProdInfoPO;
import com.customize.hub.selfsvc.prodChange.model.ProdLogVO;
import com.customize.hub.selfsvc.prodChange.service.MainProdChangeHubService;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.bean.ReceptionBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 主体产品变更
 * 
 * @author yKF70747
 * @version [版本号, Apr 12, 2012]
 * @see
 * @since
 */
public class MainProdChangeHubAction extends BaseAction
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    // modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    //private static final Logger log = Logger.getLogger(MainProdChangeHubAction.class);
    private static Log log = LogFactory.getLog(MainProdChangeHubAction.class);
    // modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    
    /**
     * 错误信息
     */
    private String errormessage = "";
    
    /**
     * 用户手机号
     */
    private String servnumber;
    
    /**
     * 主体套餐业务service
     */
    private MainProdChangeHubService mainProdChangeHubService;
    
    /**
     * 接口业务Bean
     */
    private MainProdChangeBean mainProdChangeBean;
    
    /**
     * 主体套餐列表
     */
    private List<MainProdTempletInfoPO> mainProdTempletList = new ArrayList<MainProdTempletInfoPO>();
    
    /**
     * 需开通的套餐列表
     */
    
    private List<ProdInfoPO> openProdList = new ArrayList<ProdInfoPO>();;
    
    /**
     * 需取消的套餐列表
     */
    
    private List<ProdInfoPO> cancelProdList = new ArrayList<ProdInfoPO>();;
    
    /**
     * 当前菜单
     */
    private String curMenuId = "";
    
    /**
     * ncode
     */
    private String ncode = "";
    
    /**
     * 老主体产品
     */
    private String fromProdId = "";
    
    /**
     * 新主体产品
     */
    private String toProdId = "";
    
    /**
     * 产品所属地区
     */
    private String region = "";
    
    /**
     * 模板ID
     */
    private String prodSN = "";
    
    /**
     * 详细变更信息
     */
    private String chgdesp = "";
    
    private String newProdName = "";
    
    // add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
    private Map<String, String> tipsMap = null;
    
    private ReceptionBean receptionBean = null;
    // add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
    
    /**
     * 根据用户的当前主体产品ID、归属地区、品牌查询可转换的主体产品信息列表
     * 
     * @return
     * @see
     */
    public String qryMainProdTempChangeList()
    {
        log.debug("qryMainProdTempChangeList start!");
        
        String forward = "error";
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        servnumber = customer.getServNumber();
        region = customer.getRegionID();
        
        MainProdTempletPO mainProdTempletPO = new MainProdTempletPO();
        mainProdTempletPO.setOldProdID(customer.getProductID());
        
        // add begion yKF28472 OR_huawei_201305_474
        //mainProdTempletPO.setRegion(customer.getRegionID());
        if (customer.getSmallregion() != null && !"".equals(customer.getSmallregion()))
        {
            mainProdTempletPO.setRegion(customer.getSmallregion());
        }
        else
        {
            mainProdTempletPO.setRegion(customer.getRegionID());
        }
        // add end yKF28472 OR_huawei_201305_474
        
        mainProdTempletList = mainProdChangeHubService.qryMainProdTempChangeList(mainProdTempletPO);
        
        if (null != mainProdTempletList && 0 < mainProdTempletList.size())
        {
            forward = "qryMainProdTempList";
        }
        else
        {
            setErrormessage("暂时没有您可转换的产品。");
            forward = "error";
        }
        return forward;
    }
    
    /**
     * 调用接口查询产品变更确认信息，包括用户需开通的业务、会保留的业务，需取消的业务
     * 
     * @return
     * @see
     */
    public String mainProductRecInfo()
    {
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        servnumber = customer.getServNumber();
        region = customer.getRegionID();
        
        // 调用接口获取产品变更确认信息
        Map mapResult = mainProdChangeBean.mainProductRecInfo(terminalInfoPO,
                customer,
                curMenuId,
                ncode,
                DateUtilHub.getCurrentDateTime());
        
        String openBusi = "用户开通: ";
        String cancel = "用户取消：";
        if (null != mapResult && 1 < mapResult.size())
        {
            // modify begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
            CRSet crset = (CRSet)mapResult.get("returnObj");
            
            List<Map<String, String>> prods = new ArrayList<Map<String, String>>();
            
            Map<String, String> prodMap = null;
            
            ProdInfoPO prodInfoPO = null;
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                prodInfoPO = new ProdInfoPO();
                
                String typeStr = crset.GetValue(i, 0);
                prodInfoPO.setType(typeStr);
                prodInfoPO.setProdid(crset.GetValue(i, 1));
                prodInfoPO.setProdname(crset.GetValue(i, 2));
                prodInfoPO.setBegindate(crset.GetValue(i, 3));
                prodInfoPO.setEnddate(crset.GetValue(i, 4));
                prodInfoPO.setPkgname(crset.GetValue(i, 5));
                prodInfoPO.setPrivid(crset.GetValue(i, 6));
                prodInfoPO.setPrivname(crset.GetValue(i, 7));
                prodInfoPO.setPrivbegindate(crset.GetValue(i, 8));
                prodInfoPO.setPrivenddate(crset.GetValue(i, 9));
                prodInfoPO.setReason(crset.GetValue(i, 10));
                
                if ("A".equals(typeStr)) // 需开通的套餐信息
                {
                    openBusi = openBusi + crset.GetValue(i, 2) + " " + crset.GetValue(i, 5) + " "
                            + crset.GetValue(i, 7);
                    openProdList.add(prodInfoPO);
                    
                    prodMap = new HashMap<String, String>();
                    
                    // 对象编码
                    prodMap.put("objectID", crset.GetValue(i, 1));
                    
                    // 对象类型
                    prodMap.put("objectType", "Product");
                    
                    // 提示类型
                    prodMap.put("tipType", "PCTIPNORMAL");
                    
                    // 操作类型
                    prodMap.put("operType", "PCOpRec");
                    
                    prods.add(prodMap);
                }
                else if ("D".equals(typeStr))// 需取消的套餐信息
                {
                    cancel = cancel + crset.GetValue(i, 2) + "  " + crset.GetValue(i, 5) + " " + crset.GetValue(i, 7);
                    cancelProdList.add(prodInfoPO);
                    
                    prodMap = new HashMap<String, String>();
                    
                    // 对象编码
                    prodMap.put("objectID", crset.GetValue(i, 1));
                    
                    // 对象类型
                    prodMap.put("objectType", "Product");
                    
                    // 提示类型
                    prodMap.put("tipType", "PCTIPNORMAL");
                    
                    // 操作类型
                    prodMap.put("operType", "PCOpRec");
                    
                    prods.add(prodMap);
                }
            }
            chgdesp = chgdesp + openBusi + "  " + cancel;
            
            String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);
            if ("1".equals(tipFlag))
            {
                tipsMap = receptionBean.qryObjectTips(customer, terminalInfoPO, curMenuId, "ChangeProduct", prods);
            }            
            // modify end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
        }
        else
        {
            // add begin g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            getRequest().setAttribute("backStep", "-1");
            // add end g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            
            if (mapResult != null)
            {
                getRequest().setAttribute("errormessage", mapResult.get("returnMsg"));
            }
            else
            {
                getRequest().setAttribute("errormessage", "查询产品变更确认信息失败");
                
            }
            
            return "error";
        }
        
        return "mainProductRecInfo";
    }
    
    /**
     * 调用接口执行主体产品转换
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String mainProductChangeSubmit()
    {
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 调用接口获取产品变更确认信息
        ReturnWrap result = mainProdChangeBean.mainProductChangeSubmit(terminalInfoPO, customer, curMenuId, ncode);
        
        // 写日志
        ProdLogVO prodLogVO = new ProdLogVO();
        
        prodLogVO.setRegion(region);
        prodLogVO.setToProdId(toProdId);
        prodLogVO.setFromProdId(customer.getProductID());
        prodLogVO.setProdSn(prodSN);
        prodLogVO.setServnumber(customer.getServNumber());
        // prodLogVO.setChangeId(productset);
        prodLogVO.setOpType("ChangProductMain");
        prodLogVO.setProdType("1");
        prodLogVO.setRecDate(DateUtilHub.getCurrentDateTime());
        prodLogVO.setChgdesp(chgdesp);
        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if (null != result && result.getStatus() == SSReturnCode.SUCCESS)
        if (result.getStatus() == SSReturnCode.SUCCESS)
        // begin zKF66389 2015-09-15 9月份findbugs修改
        {
            prodLogVO.setRecStauts("1");
            prodLogVO.setRecstatusdesc("主体产品变更成功");
            this.createRecLog("SH_ProdChange", "0", "0", "0", prodLogVO.getRecstatusdesc());
            // 向SH_LOG_PRODCHANGE写入产品转换日志
            mainProdChangeHubService.createProdChangeLog(prodLogVO);
            getRequest().setAttribute("message", "主体产品变更成功");
            return "mainProductRecInfo";
        }
        else
        {
            prodLogVO.setRecStauts("0");
            prodLogVO.setRecstatusdesc(result.getReturnMsg());
            getRequest().setAttribute("errormessage", result.getReturnMsg());
            
            // add begin g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            getRequest().setAttribute("backStep", "-1");
            // add end g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            
            this.createRecLog("SH_ProdChange", "0", "0", "1", prodLogVO.getRecstatusdesc());
            // 向SH_LOG_PRODCHANGE写入产品转换日志
            mainProdChangeHubService.createProdChangeLog(prodLogVO);
            return "error";
        }
    }
    
    public MainProdChangeHubService getMainProdChangeHubService()
    {
        return mainProdChangeHubService;
    }
    
    public void setMainProdChangeHubService(MainProdChangeHubService mainProdChangeHubService)
    {
        this.mainProdChangeHubService = mainProdChangeHubService;
    }
    
    public List<MainProdTempletInfoPO> getMainProdTempletList()
    {
        return mainProdTempletList;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public MainProdChangeBean getMainProdChangeBean()
    {
        return mainProdChangeBean;
    }
    
    public void setMainProdChangeBean(MainProdChangeBean mainProdChangeBean)
    {
        this.mainProdChangeBean = mainProdChangeBean;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getNcode()
    {
        return ncode;
    }
    
    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }
    
    public List<ProdInfoPO> getOpenProdList()
    {
        return openProdList;
    }
    
    public void setOpenProdList(List<ProdInfoPO> openProdList)
    {
        this.openProdList = openProdList;
    }
    
    public List<ProdInfoPO> getCancelProdList()
    {
        return cancelProdList;
    }
    
    public void setCancelProdList(List<ProdInfoPO> cancelProdList)
    {
        this.cancelProdList = cancelProdList;
    }
    
    public void setMainProdTempletList(List<MainProdTempletInfoPO> mainProdTempletList)
    {
        this.mainProdTempletList = mainProdTempletList;
    }
    
    public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public String getFromProdId()
    {
        return fromProdId;
    }
    
    public void setFromProdId(String fromProdId)
    {
        this.fromProdId = fromProdId;
    }
    
    public String getToProdId()
    {
        return toProdId;
    }
    
    public void setToProdId(String toProdId)
    {
        this.toProdId = toProdId;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public void setRegion(String region)
    {
        this.region = region;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getProdSN()
    {
        return prodSN;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setProdSN(String prodSN)
    {
        this.prodSN = prodSN;
    }
    
    public String getChgdesp()
    {
        return chgdesp;
    }
    
    public void setChgdesp(String chgdesp)
    {
        this.chgdesp = chgdesp;
    }

    public String getNewProdName()
    {
        return newProdName;
    }

    public void setNewProdName(String newProdName)
    {
        this.newProdName = newProdName;
    }

	public Map<String, String> getTipsMap() {
		return tipsMap;
	}

	public void setTipsMap(Map<String, String> tipsMap) {
		this.tipsMap = tipsMap;
	}

	public ReceptionBean getReceptionBean() {
		return receptionBean;
	}

	public void setReceptionBean(ReceptionBean receptionBean) {
		this.receptionBean = receptionBean;
	}
}
