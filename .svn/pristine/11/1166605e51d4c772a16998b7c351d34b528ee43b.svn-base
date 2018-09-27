package com.customize.hub.selfsvc.prodChange.action;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.ProdChangeHubBean;
import com.customize.hub.selfsvc.prodChange.model.ProdLogVO;
import com.customize.hub.selfsvc.prodChange.model.TemplateFilterInfoVO;
import com.customize.hub.selfsvc.prodChange.model.TempletItemVO;
import com.customize.hub.selfsvc.prodChange.model.TempletVO;
import com.customize.hub.selfsvc.prodChange.service.ProdChangeHubService;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.model.DictItemVO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class ProdChangeHubAction extends BaseAction
{
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    //private static final Logger log = Logger.getLogger(ProdChangeHubAction.class);
    private static Log log = LogFactory.getLog(ProdChangeHubAction.class);
    // modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    
    // 当前菜单
    private String curMenuId;
    
    // 错误信息
    private String errormessage = "";
    
    // 用户手机号
    private String servnumber;
    
    // 新产品ID
    private String toProdId;
    
    // 新产品名称
    private String toProdName;
    
    // 新产品品牌
    private String toProdBrand;
    
    // 用户转换套餐的集合
    private Vector prodList;
    
    // 模板编号
    private String templetID;
    
    // 模板明细
    private Vector<TempletItemVO> templetItemList;
    
    // 产品对应模板
    private Vector<TempletVO> templetList;
    
    // 开通的产品编码数组
    private String[] entityId;
    
    // 调用接口
    private ProdChangeHubBean prodChangeBean;
    
    private ProdChangeHubService prodChangeHubService;
    
    /**
     * 根据用户当前主体产品查询可以转换的产品
     * 
     * @return
     */
    public String qryChangeList()
    {
        
        log.debug("qryChangeList start!");
        
        String forward = "error";
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        if (curMenuId == null)
        {
        	curMenuId = "";
        }
        
        servnumber = customer.getServNumber();
        
        Map result = prodChangeBean.qryChangeList(customer, terminalInfoPO, curMenuId);
        
        if (result != null && result.size() > 0)
        {
            /*
             * Object prodListObject = result.get("returnObj");
             * 
             * if(prodListObject instanceof Vector){ Vector prodVector = (Vector)prodListObject; CTagSet tagSet =
             * (CTagSet) prodVector.get(0); CRSet crset = (CRSet) prodVector.get(1);
             * 
             * Vector v = new Vector();
             * 
             * //被转换的套餐ID,name String fromProdId = tagSet.GetValue("sprid"); String fromProdName =
             * tagSet.GetValue("sprname"); String[] fromProdList = {fromProdId,fromProdName}; v.add(0,fromProdList);
             * 
             * //可转换的套餐 int num = crset.GetRowCount(); for(int i=0;i<num;i++){ String toProdId = crset.GetValue(i, 0);
             * String toProdName = crset.GetValue(i, 1); String[] toProdList = {toProdId,toProdName};
             * v.add(i+1,toProdList); }
             * 
             * //放入产品的集合 setProdList(v);
             * 
             * forward = "qryList"; }
             */
            CRSet crset = (CRSet)result.get("returnObj");
            
            Vector v = new Vector();
            
            int num = crset.GetRowCount();
            
            for (int i = 0; i < num; i++)
            {
                String toProdId = crset.GetValue(i, 0);
                String toProdName = crset.GetValue(i, 1);
                String toBrandid = crset.GetValue(i, 2);
                String toBrandName = getDictName(crset.GetValue(i, 2), prodChangeBean.PRODUCTBRAND);
                String[] toProdList = {toProdId, toProdName, toBrandid, toBrandName};
                v.add(toProdList);
            }
            setProdList(v);
            forward = "qryList";
        }
        else
        {
            
            setErrormessage("暂时没有您可转换的产品。");
            forward = "error";
            
        }
        log.debug("qryChangeList end!");
        return forward;
    }
    
    /**
     * 根据用户选择的新产品，查询出该产品的模板列表
     * 
     * @return
     */
    public String getProdTmpList()
    {
        log.debug("qryChangeContent start!");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 需先验证用户是否具备变更条件
        Map<String, String> inMap = new HashMap<String, String>();
        
        getInMap(inMap);
        
        // 旧的主体产品编码
        inMap.put("oldmainprodid", customer.getProductID());
        
        // 新的主体产品编码
        inMap.put("newmainprodid", toProdId);
        
        // 受理类型(ChangProductMain)
        inMap.put("rectype", "ChangProductMain");
        
        // 生效类型(EffectNextMonth)
        inMap.put("affecttype", "EffectNextMonth");
        
        Map result = prodChangeBean.prodChgCheck(inMap);
        if ("1".equals(result.get("status")))
        {
            String retCode = (String)result.get("retcode");
            if (!"100".equals(retCode) && !"0".equals(retCode))
            {
                setErrormessage("不可转换此类产品," + (String)result.get("returnMsg"));
                return ERROR;
            }
        }
        else
        {
            String errMsg = (String)result.get("returnMsg");
            setErrormessage(errMsg);
            this.createRecLog("SH_ProdChange", "0", "0", "0", errMsg);
            return ERROR;
        }
        
        // 手机号码
        inMap.put("telnum", customer.getServNumber());
        
        // 新的主体产品编码
        inMap.put("mainprodid", toProdId);
        
        // 受理类型(ChangeProduct)
        inMap.put("rectype", "ChangeProduct");
        
        Map tmpResult = prodChangeBean.getProdTmpList(inMap);
       
        templetList = new Vector<TempletVO>();
        
        if (tmpResult != null && tmpResult.size() > 0)
        {
            // 获取过滤模板
            TemplateFilterInfoVO templateFilterInfo = new TemplateFilterInfoVO(customer.getProductID(), toProdId,
                    inMap.get("region"));
            List<TemplateFilterInfoVO> tempFilterList = prodChangeHubService.qryTempFilterInfo(templateFilterInfo);
            
            CRSet crset = (CRSet)tmpResult.get("returnObj");
            
            int num = crset.GetRowCount();
            
            TempletVO templet = null;
            for (int i = 0; i < num; i++)
            {
                boolean isFilter = false;
                String templetID = crset.GetValue(i, 0);
                String templetName = crset.GetValue(i, 1);
                
                //需要进行模板过滤
                for (TemplateFilterInfoVO tempTemplate : tempFilterList)
                {
                    if (templetID.equals(tempTemplate.getTempletNO()))
                    {
                        isFilter = true;
                        continue;
                    }
                }
                if(isFilter)
                {
                    continue;
                }
                templet = new TempletVO(templetID, templetName);
                templetList.add(templet);
            }
            
            return SUCCESS;
        }
        else
        {
            setErrormessage("对不起系统发生异常,请稍后再试.");
            this.createRecLog("SH_ProdChange", "0", "0", "0", "调用接口异常.");
            return ERROR;
        }
    }
    
    /**
     * 获取模板明细
     * 
     * @return
     */
    public String qryChangeContent()
    {
        
        log.debug("qryChangeContent start!");
        
        String forward = "error";
        
        Map<String, String> inMap = new HashMap<String, String>();
        
        getInMap(inMap);
        
        // 受理类型(Install)
        inMap.put("rectype", "Install");
        
        // 新主体产品编码
        inMap.put("mainprodid", toProdId);
        
        // 模板ID
        inMap.put("templeteid", templetID);
        
        Map result = prodChangeBean.quryChgContent(inMap);
        
        if ("1".equals(result.get("status")))
        {
            
            setTempletItemList((Vector<TempletItemVO>)result.get("returnObj"));
            
            return SUCCESS;
        }
        else
        {
            
            setErrormessage("获取模板明细失败。");
            
            // 创建错误日志
            this.createRecLog("SH_ProdChange", "0", "0", "0", "获取模板明细。");
            
        }
        log.debug("qryChangeContent end!");
        return ERROR;
    }
    
    private void getInMap(Map<String, String> inMap)
    {
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 设置操作员id
        inMap.put("curOper", terminalInfoPO.getOperid());
        
        // 设置终端机id
        inMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // 设置客户接触id
        inMap.put("touchoid", customer.getContactId());
        
        // 设置当前菜单
        inMap.put("curMenuId", curMenuId == null ? "" : curMenuId);
        
        // 设置客户手机号
        inMap.put("telnumber", customer.getServNumber());
        inMap.put("telnum", customer.getServNumber());
        
        // 地区
        inMap.put("region", terminalInfoPO.getRegion());
    }
    
    /**
     * 产品变更受理
     * 
     * @return
     */
    public String proChgCommit()
    {
        
        log.debug("proChgCommit start!");
        
        String forward = ERROR;
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        Map<String, String> inMap = new HashMap<String, String>();
        
        getInMap(inMap);
        
        String productset = arrayToString(entityId, ";");
        
        // 主体产品编码
        inMap.put("mainprodid", toProdId);
        
        // 模板编码
        inMap.put("templeteid", templetID);
        
        String recDate = getTodaytime();
        // 受理时间
        inMap.put("recdate", recDate);
        
        // 生效类型(1)
        inMap.put("affecttype", "1");
        
        // 开通的产品编码串
        inMap.put("productset", productset);
        
        Map result = prodChangeBean.quryChgCommit(inMap);
        
        ProdLogVO prodLogVO = new ProdLogVO();
        prodLogVO.setRegion(inMap.get("region"));
        prodLogVO.setToProdId(toProdId);
        prodLogVO.setFromProdId(customer.getProductID());
        prodLogVO.setProdSn(templetID);
        prodLogVO.setServnumber(customer.getServNumber());
        prodLogVO.setChangeId(productset);
        prodLogVO.setOpType("ChangProductMain");
        prodLogVO.setProdType("1");
        prodLogVO.setRecDate(recDate);
        
        String error = "";
        
        //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
        String status = (String)result.get("status");
        if ("1".equals(status))
        {
            prodLogVO.setRecStauts("1");// 表示成功
            error = "主体产品变更受理成功.";
            setErrormessage("主体产品变更受理成功.");
            forward = SUCCESS;
            
        }
        else
        {
            prodLogVO.setRecStauts("0");
            error = (String)result.get("returnMsg");
            if (error.length() > 30)
            {
                setErrormessage("主体产品变更受理失败.");
            }
            else
            {
                setErrormessage("主体产品变更受理失败," + error);
            }
            log.error(error);
            forward = ERROR;
        }
        //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
        
        this.createRecLog("SH_ProdChange", "0", "0", prodLogVO.getRecStauts(), error);
        
        prodChangeHubService.createProdLog(prodLogVO);
        
        log.debug("proChgCommit end!");
        return forward;
    }
    
    private String arrayToString(String[] tmpStr, String separator)
    {
        StringBuffer result = new StringBuffer();
        if (tmpStr.length > 0)
        {
            result.append(tmpStr[0]);
            for (int i = 1; i < tmpStr.length; i++)
            {
                result.append(separator);
                result.append(tmpStr[i]);
            }
        }
        return result.toString();
    }
    
    private String getTodaytime()
    {
        Calendar cl = new GregorianCalendar();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cl.getTime()) + " " + cl.get(Calendar.HOUR_OF_DAY) + ":" + cl.get(Calendar.MINUTE) + ":"
                + cl.get(Calendar.SECOND) + " ";
    }
    
    private String getDictName(String dictID, String groupID)
    {
        if (null == dictID || null == groupID)
        {
            return dictID;
        }
        if (!prodChangeBean.pubMap.containsKey(groupID))
        {
            // 需要去CRM系统里获取字典表数据
            Map<String, String> inMap = new HashMap<String, String>();
            getInMap(inMap);
            inMap.put("groupid", groupID);
            prodChangeBean.getDictItemByGroup(inMap);
        }
        Vector<DictItemVO> v = (Vector<DictItemVO>)prodChangeBean.pubMap.get(groupID);
        for (DictItemVO dictItem : v)
        {
            if (dictID.equals(dictItem.getDictid()))
            {
                return dictItem.getDictname();
            }
        }
        
        return dictID;
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
    
    public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public String getToProdId()
    {
        return toProdId;
    }
    
    public void setToProdId(String toProdId)
    {
        this.toProdId = toProdId;
    }
    
    public Vector getProdList()
    {
        return prodList;
    }
    
    public void setProdList(Vector prodList)
    {
        this.prodList = prodList;
    }
    
    public ProdChangeHubBean getProdChangeBean()
    {
        return prodChangeBean;
    }
    
    public void setProdChangeBean(ProdChangeHubBean prodChangeBean)
    {
        this.prodChangeBean = prodChangeBean;
    }
    
    public ProdChangeHubService getProdChangeHubService()
    {
        return prodChangeHubService;
    }
    
    public void setProdChangeHubService(ProdChangeHubService prodChangeHubService)
    {
        this.prodChangeHubService = prodChangeHubService;
    }
    
    /**
     * @return the templetList
     */
    public Vector<TempletVO> getTempletList()
    {
        return templetList;
    }
    
    /**
     * @param templetList the templetList to set
     */
    public void setTempletList(Vector<TempletVO> templetList)
    {
        this.templetList = templetList;
    }
    
    /**
     * @return the templetID
     */
    public String getTempletID()
    {
        return templetID;
    }
    
    /**
     * @param templetID the templetID to set
     */
    public void setTempletID(String templetID)
    {
        this.templetID = templetID;
    }
    
    /**
     * @return the templetItemList
     */
    public Vector<TempletItemVO> getTempletItemList()
    {
        return templetItemList;
    }
    
    /**
     * @param templetItemList the templetItemList to set
     */
    public void setTempletItemList(Vector<TempletItemVO> templetItemList)
    {
        this.templetItemList = templetItemList;
    }
    
    /**
     * @return the toProdName
     */
    public String getToProdName()
    {
        return toProdName;
    }
    
    /**
     * @param toProdName the toProdName to set
     */
    public void setToProdName(String toProdName)
    {
        this.toProdName = toProdName;
    }
    
    /**
     * @return the toProdBrand
     */
    public String getToProdBrand()
    {
        return toProdBrand;
    }
    
    /**
     * @param toProdBrand the toProdBrand to set
     */
    public void setToProdBrand(String toProdBrand)
    {
        this.toProdBrand = toProdBrand;
    }
    
    /**
     * @return the entityId
     */
    public String[] getEntityId()
    {
        return entityId;
    }
    
    /**
     * @param entityId the entityId to set
     */
    public void setEntityId(String[] entityId)
    {
        this.entityId = entityId;
    }
    
}
