package com.gmcc.boss.selfsvc.quickpublish.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.QuickPubBean;
import com.gmcc.boss.selfsvc.bean.ReceptionBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.quickpublish.model.AddAttrPO;
import com.gmcc.boss.selfsvc.quickpublish.model.ChildProdPO;
import com.gmcc.boss.selfsvc.quickpublish.model.MultiProdCommitPO;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdCommitPO;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdConfigPO;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdTypePO;
import com.gmcc.boss.selfsvc.quickpublish.model.SpeakProdPO;
import com.gmcc.boss.selfsvc.quickpublish.model.SubsChildProdPO;
import com.gmcc.boss.selfsvc.quickpublish.service.QuickPubService;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 产品快速发布
 * 
 * @author cKF76106
 * @version [版本号, Jul 3, 2012]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class QuickPubAction extends BaseAction
{
    private static final long serialVersionUID = 1L;

    // 当前菜单ID
    private String curMenuId = "";
    
    // 页数
    private int pageCount;
    
    
    // 每页显示容量
    private int pageSize;
    
    // 第几页
    private int page = 0;
    
    // 产品查询service
    private QuickPubService quickPubService;
    
    // 产品列表
    private List<ProdConfigPO> prodList = new ArrayList<ProdConfigPO>();
    
    // 产品、产品包ID
    private String prodID;
    
    // 子产品ID
    private String subProdId;
    
    // 选中的子产品列表
    private String childProdIds;
    
    // 选中的子产品属性列表
    private String childProdAddAttrs;
    
    // 操作类型
    private String opertype;
    
    // 1：清除
    private String initBz;
    
    // 产品region
    private String region;
    
    // 产品所属品牌
    private String brand;
    
    // 一级菜单url，initButton=0；将查询按钮重置为推荐产品查询
    private String initButton;
    
    private QuickPubBean quickPubBean;
    
    // 错误信息
    private String errormessage;
    
    private String successMessage;
    
    // 用户选择的普通产品附加属性
    private String selectAttrStr;
    
    // 已订购的产品列表
    private List<SpeakProdPO> speakProdPOList = new ArrayList<SpeakProdPO>();
    
    // 生效方式
    private String effectType;
    
    // 生效时间
    private String startDate;
    
    // 失效时间
    private String endDate;
    
    // 原子产品列表
    private String childProdIds_old;
    
    // 原附加属性
    private String childProdAddAttrs_old;
    
    // add begin g00140516 2012/09/28 R003C12L09n01 OR_HUB_201207_1089    
    /**
     * 特别提示信息
     */
    private String tip = "";
    
    private ReceptionBean receptionBean = null;
    // add end g00140516 2012/09/28 R003C12L09n01 OR_HUB_201207_1089
    
    // 产品大类
    private String typeID = "";
    
    // 页面展现样式 (1:统一图片 0:自定义图片)
    private String style = "";
    
    // 热门产品受理标志
    private String hotRecFlag = "";
    
    // 快速发布标识
    private String quickPubFlag = "";
    
    /**
     * 菜单的搜索类型。2，品牌；3，首字母
     */
    private String searchType = "";
    
    // 按钮类型 0：推荐业务 1：已开通业务 2：未开通业务
    private String buttonType = "";

    /**
     * 产品查询 <功能详细描述>
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String initProdList()
    {
    	// add begin jWX216858 2015-2-9 OR_HUB_201501_167_HUB_关于自助终端菜单层级优化需求
    	if ("recNetNew".equals(curMenuId))
        {
        	typeID = "recNet";
        }
    	// add end jWX216858 2015-2-9 OR_HUB_201501_167_HUB_关于自助终端菜单层级优化需求
    	
        String forward = "failed";
        
        prodList.clear();
        HttpSession session = this.getRequest().getSession();

        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        NserCustomerSimp customerInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        List<ProdConfigPO> prods = new ArrayList<ProdConfigPO>();
        
        ProdConfigPO prodConfigPO = new ProdConfigPO();
        prodConfigPO.setTypeID(typeID);
        prodConfigPO.setAreaCode(terminalInfo.getRegion());
        
        if("".equals(buttonType) || null == buttonType)
        {
            buttonType = "0";
        }
        if ("0".equals(buttonType))
        {
            // 查询推荐产品
            prodConfigPO.setIsRec("1");
        }
        // 已登录用户查询
        if (null != customerInfor)
        {
            prodConfigPO.setBrand(customerInfor.getBrandID());
            prodConfigPO.setMainProd(customerInfor.getProductID());
            prodConfigPO.setAreaCode(customerInfor.getRegionID());
        }
        
        // 产品查询:查询产品配置表
        prods = quickPubService.getProdList(prodConfigPO);
                
        // 去掉重复的NCODE
        Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < prods.size(); i++) 
		{
			String nCode = prods.get(i).getNcode();
			if (map.get(nCode) != null) 
			{
				prods.remove(i);
				i--;
			} 
			else 
			{
				map.put(nCode, "");
			}
		}
		
		CRSet cr = new CRSet();
		 // 用户已登录
        if (null != customerInfor)
        {
            // 调接口获取用户已开通产品
            Map mapResult = quickPubBean.qryHasProds(terminalInfo, customerInfor, curMenuId);
            
            if (null != mapResult && "1".equals(mapResult.get("successFlag")))
            {
                cr = (CRSet)(mapResult.get("returnObj"));
                
                this.createRecLog(Constants.BUSITYPE_QRYHASPRODS, "0", "0", "0", "用户已订购产品信息查询:用户已订购产品信息查询成功!");
            }
            else if (null != mapResult && null == mapResult.get("returnObj"))
            {
                String resultMsg = (String)mapResult.get("returnMsg");
                
                resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000002", String.valueOf(mapResult.get("errcode")), null);
                
                this.createRecLog(Constants.BUSITYPE_QRYADDATTR, "0", "0", "1", resultMsg);
                
                this.getRequest().setAttribute("errormessage", resultMsg);
                
                // 返回
                return forward;
                
            }
            else
            {
                String resultMsg = "已订购产品信息查询失败";
                
                resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000002", "", null);
                
                this.createRecLog(Constants.BUSITYPE_QRYHASPRODS, "0", "0", "1", resultMsg);
                
                this.getRequest().setAttribute("errormessage", resultMsg);
                // 返回
                return forward;
            }
            
            // 推荐业务
            if ("0".equals(buttonType) && null != cr)
            {
                // 从用户可办理推荐产品列表prods去掉用户已开通产品
                for (int i = 0; i < prods.size(); i++)
                {
                    for (int j = 0; j < cr.GetRowCount(); j++)
                    {
                        String nCode =  cr.GetValue(j, 18);
                        if (prods.get(i).getNcode().equals(nCode))
                        {
                            prods.remove(i);
                            i--;
                            break;
                        }
                    }
                }
            }
            // 已开通业务
            else if ("1".equals(buttonType))
            {
                List<ProdConfigPO> prodsInfor = new ArrayList<ProdConfigPO>();
                if(null != cr)
                {
                    // 从用户可办理产品列表prods中过滤出在界面展示的用户已开通产品（crm接口返回开通的产品，但在自助终端不支持的业务不展示）
                    for (ProdConfigPO prodConfig : prods)
                    {
                        for (int i = 0; i < cr.GetRowCount(); i++)
                        {
                            String nCode = cr.GetValue(i, 18);
                            if (prodConfig.getNcode().equals(nCode))
                            {
                                prodsInfor.add(prodConfig);
                                break;
                            }
                        }
                    }
                }
                
                prods = prodsInfor;
                
            }
            // 未开通业务
            else if ("2".equals(buttonType) && null != cr)
            {
                // 从用户可办理产品列表prods过滤出用户未开通产品列表(去掉用户已开通产品)
                for (int i = 0; i < prods.size(); i++)
                {
                    for (int k = 0; k < cr.GetRowCount(); k++)
                    {
                        String nCode = cr.GetValue(k, 18);
                        if (prods.get(i).getNcode().equals(nCode))
                        {
                            prods.remove(i);
                            i--;
                            break;
                        }
                    }
                }
            }
            
        }
		prods = getResultList(prods,typeID);
		
        
        this.getRequest().setAttribute("recordCount", prods.size());
   
        // 返回
        if ("1".equals(style))
        {
            pageSize = 8;
            
            prodList = this.getPageList(prods);
            
            // 统一图片
            return "resultList_unify";
        }
        else
        {
            pageSize = 12;
            
            prodList = this.getPageList(prods);
            
            // 自定义图片
            return "resultList";
        }
    }
    
    /**
     * 分类展示
     * <功能详细描述>
     * @param prods
     * @param typeID
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List getResultList(List<ProdConfigPO> prods,String typeID)
    {
        
        Map<String, String> map = new HashMap<String, String>();
        
        // 未选择产品大类查询
        if ("".equals(typeID) || null == typeID)
        {
            // 产品大类
            List typeList = new ArrayList();
            
            Map<String, String> typeMap = new HashMap<String, String>();
            
            for (int i = 0; i < prods.size(); i++)
            {
                String type = prods.get(i).getTypeID();
                
                if (!"".equals(type) && null != type && map.get(type) == null)
                {
                    map.put(type, "");
                    typeList.add(type);
                }
            }
            
            // 查询产品大类信息
            List<ProdTypePO> prodTypeList = new ArrayList<ProdTypePO>();
            if (typeList.size() > 0)
            {
                prodTypeList = quickPubService.getProdTypeList();
            }
            
            // 取出要展示的产品大类信息
            for (int i = 0; i < typeList.size(); i++)
            {
                for (ProdTypePO typePO : prodTypeList)
                {
                    if (typePO.getTypeID().equals(typeList.get(i)))
                    {
                        ProdConfigPO po = new ProdConfigPO();
                        po.setIsTypeID("1");
                        po.setProdTypePO(typePO);
                        prods.add(po);
                        break;
                    }
                }
                
            }
            
            // 查询结果中删除大类下的产品，显示大类和不归属于任何大类的产品
            for (int i = 0; i < prods.size(); i++)
            {
                String type = prods.get(i).getTypeID();
                
                if (!"".equals(type) && null != type)
                {
                    prods.remove(i);
                    i--;
                }
            }
        }
        return prods;
    }
    /**
     * 产品详情查询 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String prodDetail()
    {
        
        String forward = "failed";
        HttpSession session = this.getRequest().getSession();
        
        // 拼装查询参数
        ProdConfigPO paramPO = new ProdConfigPO();
        paramPO.setBrand(brand);
        paramPO.setNcode(prodID);
        paramPO.setAreaCode(region);
        
        // 根据PRODID查询产品对象
        List<ProdConfigPO> prods = quickPubService.getProdList(paramPO);
        ProdConfigPO prodDetailPO = prods.get(0);
        
        // 生效方式
        String effectTime = prodDetailPO.getEffectType();
        
        if("" != effectTime && null != effectTime)
        {
            String[] effectArray = effectTime.trim().split("\\|");
            
            String effectRec = "";
            if (effectArray.length > 0)
            {
                effectRec = effectArray[0];
            }
            
            String effectMod = "";
            if (effectArray.length > 1)
            {
                effectMod = effectArray[1];
            }
            
            String effectDel = "";
            if (effectArray.length > 2)
            {
                effectDel = effectArray[2];
            }
            // 受理生效方式
            prodDetailPO.setEffectRec(effectRec);
            // 变更生效方式
            prodDetailPO.setEffectMod(effectMod);
            // 取消生效方式
            prodDetailPO.setEffectDel(effectDel);
        }
        
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        NserCustomerSimp customerInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        if(null != customerInfor)
        {
            CRSet cr = new CRSet();
            
            // 调接口获取用户已开通产品
            Map mapResult = quickPubBean.qryHasProds(terminalInfo, customerInfor, curMenuId);
            
            if (null != mapResult && "1".equals(mapResult.get("successFlag")))
            {
                cr = (CRSet)(mapResult.get("returnObj"));
                
                this.createRecLog(Constants.BUSITYPE_QRYHASPRODS, "0", "0", "0", "用户已订购产品信息查询:用户已订购产品信息查询成功!");
            }
            else if (null != mapResult && null == mapResult.get("returnObj"))
            {
                String resultMsg = (String)mapResult.get("returnMsg");
                
                resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000002", String.valueOf(mapResult.get("errcode")), null);
                
                this.createRecLog(Constants.BUSITYPE_QRYADDATTR, "0", "0", "1", resultMsg);
                
                this.getRequest().setAttribute("errormessage", resultMsg);
                
                // 返回
                return forward;
                
            }
            else
            {
                String resultMsg = "已订购产品信息查询失败";
                
                resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000002", "", null);
                
                this.createRecLog(Constants.BUSITYPE_QRYHASPRODS, "0", "0", "1", resultMsg);
                
                this.getRequest().setAttribute("errormessage", resultMsg);
                // 返回
                return forward;
            }
           
            if(null != cr)
            {
                // 已开通业务
                for (int i = 0; i < cr.GetRowCount(); i++)
                {
                    String nCode = cr.GetValue(i, 18);
                    if (prodDetailPO.getNcode().equals(nCode))
                    {
                        // 当前产品已开通
                        prodDetailPO.setIsOpen("1");
                        break;
                    }
                }
            }
        }
        
        
        // add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
        String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);
     // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
//        if ("1".equals(tipFlag) && customerInfor != null)
//        {           
//            tip = receptionBean.qryNcodeTips(customerInfor, terminalInfo, prodID, "PCOpRec", "PCTIPNORMAL", curMenuId);
//        }
        tip = ("1".equals(tipFlag) && customerInfor != null) ? receptionBean.qryNcodeTips(customerInfor, terminalInfo, prodID, "PCOpRec", "PCTIPNORMAL", curMenuId) : tip;
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
        // add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
        
        // 清session中的产品信息
        session.removeAttribute(Constants.PROD_INFO);
        
        // 将新选择的产品信息放到session中
        session.setAttribute(Constants.PROD_INFO, prodDetailPO);
        
        return "prodDetail";
    }
    
    /**
     * 转到子产品展示页面 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String subsProd()
    {
        String forward = "failed";
        
        HttpServletRequest request = getRequest();
        
        // session
        HttpSession session = request.getSession();

        // 终端信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 客户信息
        NserCustomerSimp customerInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 取出当前产品包
        ProdConfigPO prodDetailPO = (ProdConfigPO)session.getAttribute(Constants.PROD_INFO);
        
        // 查询包下的子产品 -----------------------------------------------------------------------------
        String nCode = prodDetailPO.getNcode(); // 产品包编码、模板ID
        String type = prodDetailPO.getProdType(); // 类型：2 产品包 3 模板
        String opttype = opertype;// 操作类型
        Map mapResult = quickPubBean.qrySubProds(terminalInfo, customerInfor, curMenuId, nCode, type, opttype);
        
        if (this.isMapNotEmpty(mapResult))
        {
            // 返回值
            Vector vector = (Vector)(mapResult.get("returnObj"));
            
            // 返回CTagSet
            CTagSet ctagset = (CTagSet)vector.get(0);
            
            if (vector.size() == 1)
            {
                String resultMsg = "未查询到产品包下的子产品!";
                
                resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000003", String.valueOf(mapResult.get("errcode")), null);
                
                this.createRecLog(Constants.BUSITYPE_QRYSUBPRODS, "0", "0", "1", resultMsg);
                
                request.setAttribute("errormessage", resultMsg);
                
                // 返回
                return forward;
            }
            
            // 返回CRSet
            CRSet crset = (CRSet)vector.get(1);
            
            // 子产品包
            ChildProdPO childProdPO = new ChildProdPO();
            childProdPO.setMinprod((String)ctagset.get("minprod"));// 可选产品最小数
            childProdPO.setMaxprod((String)ctagset.get("maxprod"));// 可选产品最大数
            
            List<Map<String, String>> prods = new ArrayList<Map<String, String>>();
            
            Map<String, String> prodMap = null;
            
            List<SubsChildProdPO> subsChildProdPOList = new ArrayList<SubsChildProdPO>();
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                SubsChildProdPO subsChildProdPO = new SubsChildProdPO();
                
                prodMap = new HashMap<String, String>();
                
                subsChildProdPO.setPkgid(crset.GetValue(i, 0));// 产品包编码
                subsChildProdPO.setProdid(crset.GetValue(i, 1));// 产品编码
                subsChildProdPO.setPrivid(crset.GetValue(i, 2));// 优惠编码
                subsChildProdPO.setProdname(crset.GetValue(i, 3));// 产品名称
                
                // modify begin jWX216858 V200R003C10LG0901 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
                subsChildProdPO.setIsmandatory(this.convertType(crset.GetValue(i, 4)));// 选择类型 0：可选 1：必选
                // modify end jWX216858 V200R003C10LG0901 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
                subsChildProdPO.setHasattr(crset.GetValue(i, 5));// 是否有附加属性 0：无 1：有
                subsChildProdPO.setInftype(crset.GetValue(i, 6));// 接口业务类型 PCIntRelaNormal：普通业务 PCIntRelaRadio：业务切换
                subsChildProdPOList.add(subsChildProdPO);
                
                if (!"".equals(crset.GetValue(i, 2)))
                {
                    // 对象编码
                    prodMap.put("objectID", crset.GetValue(i, 2));
                    
                    // 对象类型
                    prodMap.put("objectType", "Privilege");
                }
                else
                {
                    // 对象编码
                    prodMap.put("objectID", crset.GetValue(i, 1));
                    
                    // 对象类型
                    prodMap.put("objectType", "Product");
                }
                
                // 提示类型
                prodMap.put("tipType", "PCTIPNORMAL");
                
                // 操作类型
                prodMap.put("operType", "PCOpRec");
            }
            
            String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);
            if ("1".equals(tipFlag))
            {
                Map<String, String> tipsMap = receptionBean.qryObjectTips(customerInfor, terminalInfo, curMenuId, "ChangeProduct", prods);
                               
                request.setAttribute("tipsMap", tipsMap);
            }
            
            // 放入子产品对象
            childProdPO.setSubsChildProdPOList(subsChildProdPOList);
            
            // 存入当前产品中
            prodDetailPO.setChildProdPO(childProdPO);
            
            // 成功日志
            this.createRecLog(Constants.BUSITYPE_QRYSUBPRODS, "0", "0", "0", "产品包下子产品查询:产品包子产品查询成功!");
        }
        else if (null != mapResult && null == mapResult.get("returnObj"))
        {
            String resultMsg = (String)mapResult.get("returnMsg");
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000003", String.valueOf(mapResult.get("errcode")), null);
            
            this.createRecLog(Constants.BUSITYPE_QRYSUBPRODS, "0", "0", "1", resultMsg);
            
            request.setAttribute("errormessage", resultMsg);
            
            // 返回
            return forward;
        }
        else
        {
            String resultMsg = "未查询到产品包下的子产品";
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000003", "", null);
            
            this.createRecLog(Constants.BUSITYPE_QRYSUBPRODS, "0", "0", "1", resultMsg);
            
            request.setAttribute("errormessage", resultMsg);
            
            // 返回
            return forward;
        }
        
        // 查询用户已开通产品------------------------------------------------------------------------------------------------
        mapResult = quickPubBean.qryHasProds(terminalInfo, customerInfor, curMenuId);
        
        CRSet cr = new CRSet();
        if (this.isMapNotEmpty(mapResult))
        {
            cr = (CRSet)(mapResult.get("returnObj"));
            
            this.createRecLog(Constants.BUSITYPE_QRYHASPRODS, "0", "0", "0", "用户已订购产品信息查询:用户已订购产品信息查询成功!");
        }
        else if (null != mapResult && null == mapResult.get("returnObj"))
        {
            String resultMsg = (String)mapResult.get("returnMsg");
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000002", String.valueOf(mapResult.get("errcode")), null);
            
            this.createRecLog(Constants.BUSITYPE_QRYHASPRODS, "0", "0", "1", resultMsg);
            
            request.setAttribute("errormessage", resultMsg);
            
            return forward;
        }
        else
        {
            String resultMsg = "已订购产品信息查询失败!";
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000002", "", null);
            
            this.createRecLog(Constants.BUSITYPE_QRYHASPRODS, "0", "0", "1", resultMsg);
            
            request.setAttribute("errormessage", resultMsg);
            
            return forward;
        }
        
        // 从用户可办理推荐产品列表prods去掉用户已开通产品
        speakProdPOList = new ArrayList<SpeakProdPO>();
        SpeakProdPO speakProdPO;
        for (int j = 0; j < cr.GetRowCount(); j++)
        {
            speakProdPO = new SpeakProdPO();
            speakProdPO.setProdid(cr.GetValue(j, 0));// 对产品包下的子产品或者模板下的子产品，返回产品编码；其它返回“”
            speakProdPO.setProdname(cr.GetValue(j, 1));// 产品名称
            speakProdPO.setAttrparam(cr.GetValue(j, 2));// 附加属性串
            speakProdPO.setServiceres(cr.GetValue(j, 3));// 服务资源串
            speakProdPO.setRecdate(cr.GetValue(j, 4));// 受理时间，格式：yyyymmddhh24miss
            speakProdPO.setStartdate(cr.GetValue(j, 5));// 开始时间，格式：yyyymmddhh24miss
            speakProdPO.setEnddate(cr.GetValue(j, 6));// 结束时间，格式：yyyymmddhh24miss
            speakProdPO.setStatus(cr.GetValue(j, 7));// 状态 0：预约 1：正常 2：暂停 3：已退订
            speakProdPO.setFormnum(cr.GetValue(j, 8));// 操作流水
            speakProdPO.setPkgtype(cr.GetValue(j, 9));// 套餐大类
            speakProdPO.setProddesc(cr.GetValue(j, 10));// 产品描述
            speakProdPO.setDoneenum(cr.GetValue(j, 11));// 赠送方
            speakProdPO.setDoneerelaid(cr.GetValue(j, 12));// 赠送关系编码
            speakProdPO.setPkgname(cr.GetValue(j, 13));// 套餐大类名称
            speakProdPO.setCanceldate(cr.GetValue(j, 14));// 取消时间，格式：yyyymmddhh24miss
            speakProdPO.setPkgid(cr.GetValue(j, 15));// 对产品包下的子产品或者模板下的子产品，返回产品包编码或者模板编码；其它返回“”
            speakProdPO.setProdtype(cr.GetValue(j, 16));// 该字段用于标识是产品包还是NCODE，若为0标识为NCODE那么COL_0里面的值就为NCODE，若为1，COL_1里面的内容就是产品包
            speakProdPO.setPrivid(cr.GetValue(j, 17));// 优惠编码
            speakProdPO.setNcode(cr.GetValue(j, 18));// 对产品包下的子产品或者模板下的子产品，返回“”；其它返回ncode
            speakProdPOList.add(speakProdPO);
        }
        
        // 返回
        return "subsProd";
    }
    
    /**
     * 转换产品包的选择类型
     * @param type SeleType_Must 必选，SeleType_Choice 可选
     * @return 1：必选 0：可选
     * @remark create by jWX216858 V200R003C10LG0901 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
     */
    public String convertType(String type)
    {
    	String choiceType = "";
    	if ("SeleType_Must".equals(type))
    	{
    		choiceType = "1";
    	}
    	else if ("SeleType_Choice".equals(type))
    	{
    		choiceType =  "0";
    	}
    	return choiceType;
    }
    /**
     * 判断bean返回的map是否为空
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark: create by jWX216858 2014/08/5 OR_huawei_201408_93_圈复杂度_自助终端(二阶段)
     */
    public boolean isMapNotEmpty(Map map)
    {
    	return null != map && null != map.get("returnObj");
    }
    
    /**
     * 展示子产品附加属性 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String subProdAddAttr()
    {
        // session
        HttpSession session = this.getRequest().getSession();
        
        // 终端信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 客户信息
        NserCustomerSimp customerInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 当前产品PO
        ProdConfigPO prodConfigPO = (ProdConfigPO)session.getAttribute(Constants.PROD_INFO); 
        
        // 产品包编码
        String pkgid = subProdId.split("~")[0];
        
        // 产品编码
        String prodid = subProdId.split("~")[1];
        
        // 优惠编码
        String privid = subProdId.split("~").length == 3 ? subProdId.split("~")[2] : "";
        
        // 查询类型 0：NCODE 1：产品包下子产品或者模板下子产品 2 :优惠编码
        String qrytype = "".equals(privid)?"1":"2";
        
        // 产品编码或优惠编码
        String ncode = "".equals(privid)?prodid:privid;
        
        // nCode
        String nCode = prodConfigPO.getNcode();
        
        // 调用接口查询产品附加属性
        Map mapResult = quickPubBean.qryAddAttr(terminalInfo, customerInfor, curMenuId, qrytype, ncode, opertype);
        
        if (null != mapResult && null != mapResult.get("returnObj"))
        {
            CRSet cr = (CRSet)(mapResult.get("returnObj"));
            
            List<AddAttrPO> addAttrPOListBySubProd = new ArrayList<AddAttrPO>();
            for (int i = 0; i < cr.GetRowCount(); i++)
            {
                AddAttrPO addStrPO = new AddAttrPO();
                addStrPO.setObjid(cr.GetValue(i, 0));// 对象编码
                addStrPO.setAttrid(cr.GetValue(i, 1));// 附加属性编码
                addStrPO.setAttrname(cr.GetValue(i, 2));// 附加属性名称
                addStrPO.setAttrtype(cr.GetValue(i, 3));// 附加属性类型，参考： select t.* from dict_item t where t.groupid = 'EditorType' MEMO，多行编辑框 SINGLE，单行编辑框 DATE，时间编辑框(格式：yyyy-mm-dd hh24:mi:ss) SELECT，下拉列表
                addStrPO.setValuetype(cr.GetValue(i, 4));// 值类型 NUMBER:数字 STRING:字符串
                addStrPO.setMinlength(cr.GetValue(i, 5));// 附加属性值最小长度
                addStrPO.setMaxlength(cr.GetValue(i, 6));// 附加属性值最大长度
                addStrPO.setIsmandatory(cr.GetValue(i, 7));// 是否必须 0：不是 1：是
                addStrPO.setIsshow(cr.GetValue(i, 8));// 是否界面展现 0：不是 1：是
                addStrPO.setAttrnum("0".equals(cr.GetValue(i, 9))?"1":cr.GetValue(i, 9));// 属性值数量
                addStrPO.setAttrsplit("".equals(cr.GetValue(i, 10))?";":cr.GetValue(i, 10));// 附加属性分隔符
                addStrPO.setAttrvalue(cr.GetValue(i, 11));// 用户订购值。如果用户没有订购，就填充默认值。
                addStrPO.setDictinfo(cr.GetValue(i, 12));// 字典信息，格式： dictid=dictname|dictid=dictname ...
                addStrPO.setObjType(cr.GetValue(i, 13));// 对象类型:产品/优惠/服务
                addAttrPOListBySubProd.add(addStrPO);
            }
            
            this.getRequest().setAttribute("addAttrPOListBySubProd", addAttrPOListBySubProd);
            
            this.createRecLog(Constants.BUSITYPE_QRYADDATTR, "0", "0", "0", "子产品附加属性查询:子产品附加属性查询成功!");
        }
        else if (null != mapResult && null == mapResult.get("returnObj"))
        {
            String resultMsg = (String)mapResult.get("returnMsg");
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000004", String.valueOf(mapResult.get("errcode")), null);
            
            this.createRecLog(Constants.BUSITYPE_QRYADDATTR, "0", "0", "1", resultMsg);
            
            this.getRequest().setAttribute("errormessage", resultMsg);
            
            // 返回
            return "error";
        }
        else
        {
            String resultMsg = "产品附加属性查询失败";
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000004", "", null);
            
            this.createRecLog(Constants.BUSITYPE_QRYADDATTR, "0", "0", "1", resultMsg);
            
            this.getRequest().setAttribute("errormessage", resultMsg);
            
            // 返回
            return "error";
        }
        
        // 返回
        return "subProdAddAttr";
    }
    
    /**
     * 查询子产品的附加属性
     * 
     * @return
     */
    public void getSubProdAttrsByAjax() throws IOException
    {
        // 头信息
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        this.getResponse().setContentType("text/html;charset=GBK");
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 客户信息
        NserCustomerSimp customerInfor = (NserCustomerSimp)getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // 当前产品PO
        ProdConfigPO prodConfigPO = (ProdConfigPO)getRequest().getSession().getAttribute(Constants.PROD_INFO); 

        // 子产品列表
        List<SubsChildProdPO> subsChildProdPOList = prodConfigPO.getChildProdPO().getSubsChildProdPOList();
        
        // 返回字符串
        String returnStr = "";
        
        for (SubsChildProdPO subsChildProdPO : subsChildProdPOList)
        {
            if ("0".equals(subsChildProdPO.getHasattr()))
            {
                continue;
            }
            // 产品包编码
            String pkgid = subsChildProdPO.getPkgid();
            
            // 产品编码
            String prodid = subsChildProdPO.getProdid();
            
            // 优惠编码
            String privid = subsChildProdPO.getPrivid();
            
            // 查询类型 0：NCODE 1：产品包下子产品或者模板下子产品 2 :优惠编码
            String qrytype = "".equals(privid)?"1":"2";
            
            // 产品编码或优惠编码
            String ncode = "".equals(privid)?prodid:privid;
            
            // 调用接口查询产品附加属性
            Map mapResult = quickPubBean.qryAddAttr(terminalInfo, customerInfor, curMenuId, qrytype, ncode, opertype);
            
            if (null != mapResult && null != mapResult.get("returnObj"))
            {
                CRSet cr = (CRSet)(mapResult.get("returnObj"));
                
                // 开头
                returnStr = returnStr + pkgid + "~" + prodid + "~" + privid + "::";
                
                // 正文
                for (int i = 0; i < cr.GetRowCount(); i++)
                {
                    AddAttrPO addStrPO = new AddAttrPO();
                    addStrPO.setObjid(cr.GetValue(i, 0));// 对象编码
                    addStrPO.setAttrid(cr.GetValue(i, 1));// 附加属性编码
                    addStrPO.setAttrname(cr.GetValue(i, 2));// 附加属性名称
                    addStrPO.setAttrtype(cr.GetValue(i, 3));// 附加属性类型，参考： select t.* from dict_item t where t.groupid = 'EditorType' MEMO，多行编辑框 SINGLE，单行编辑框 DATE，时间编辑框(格式：yyyy-mm-dd hh24:mi:ss) SELECT，下拉列表
                    addStrPO.setValuetype(cr.GetValue(i, 4));// 值类型 NUMBER:数字 STRING:字符串
                    addStrPO.setMinlength(cr.GetValue(i, 5));// 附加属性值最小长度
                    addStrPO.setMaxlength(cr.GetValue(i, 6));// 附加属性值最大长度
                    addStrPO.setIsmandatory(cr.GetValue(i, 7));// 是否必须 0：不是 1：是
                    addStrPO.setIsshow(cr.GetValue(i, 8));// 是否界面展现 0：不是 1：是
                    addStrPO.setAttrnum(cr.GetValue(i, 9));// 属性值数量
                    addStrPO.setAttrsplit(cr.GetValue(i, 10));// 附加属性分隔符
                    addStrPO.setAttrvalue(cr.GetValue(i, 11));// 用户订购值。如果用户没有订购，就填充默认值。
                    addStrPO.setDictinfo(cr.GetValue(i, 12));// 字典信息，格式： dictid=dictname|dictid=dictname ...
                    addStrPO.setObjType(cr.GetValue(i, 13));// 对象类型:产品/优惠/服务
                    
                    if ("EDIT".equals(addStrPO.getAttrtype()) || "PASSWORD".equals(addStrPO.getAttrtype()) || "TEXTAREA".equals(addStrPO.getAttrtype()) || "MONEY".equals(addStrPO.getAttrtype()))
                    {
                        returnStr = returnStr + addStrPO.getAttrid() + "=" + addStrPO.getAttrvalue() + "=#";
                    }
                    else if ("SELECT".equals(addStrPO.getAttrtype()))
                    {
                        returnStr = returnStr + addStrPO.getAttrid() + "=" + addStrPO.getAttrvalue() + "=#";
                    }
                }
                
                // 结尾
                returnStr = returnStr + "]";
                
            }
        }
        returnStr = returnStr.replaceAll("#]", "]");
        
        // 附加属性@@ID
        returnStr = returnStr + "@@";
        
        // 查询用户已开通产品------------------------------------------------------------------------------------------------
        Map mapResult = quickPubBean.qryHasProds(terminalInfo, customerInfor, curMenuId);
        
        CRSet cr = new CRSet();
        if (null != mapResult && null != mapResult.get("returnObj"))
        {
            cr = (CRSet)(mapResult.get("returnObj"));
            speakProdPOList = new ArrayList<SpeakProdPO>();
            SpeakProdPO speakProdPO;
            for (int j = 0; j < cr.GetRowCount(); j++)
            {
                speakProdPO = new SpeakProdPO();
                speakProdPO.setProdid(cr.GetValue(j, 0));// 对产品包下的子产品或者模板下的子产品，返回产品编码；其它返回“”
                speakProdPO.setProdname(cr.GetValue(j, 1));// 产品名称
                speakProdPO.setAttrparam(cr.GetValue(j, 2));// 附加属性串
                speakProdPO.setServiceres(cr.GetValue(j, 3));// 服务资源串
                speakProdPO.setRecdate(cr.GetValue(j, 4));// 受理时间，格式：yyyymmddhh24miss
                speakProdPO.setStartdate(cr.GetValue(j, 5));// 开始时间，格式：yyyymmddhh24miss
                speakProdPO.setEnddate(cr.GetValue(j, 6));// 结束时间，格式：yyyymmddhh24miss
                speakProdPO.setStatus(cr.GetValue(j, 7));// 状态 0：预约 1：正常 2：暂停 3：已退订
                speakProdPO.setFormnum(cr.GetValue(j, 8));// 操作流水
                speakProdPO.setPkgtype(cr.GetValue(j, 9));// 套餐大类
                speakProdPO.setProddesc(cr.GetValue(j, 10));// 产品描述
                speakProdPO.setDoneenum(cr.GetValue(j, 11));// 赠送方
                speakProdPO.setDoneerelaid(cr.GetValue(j, 12));// 赠送关系编码
                speakProdPO.setPkgname(cr.GetValue(j, 13));// 套餐大类名称
                speakProdPO.setCanceldate(cr.GetValue(j, 14));// 取消时间，格式：yyyymmddhh24miss
                speakProdPO.setPkgid(cr.GetValue(j, 15));// 对产品包下的子产品或者模板下的子产品，返回产品包编码或者模板编码；其它返回“”
                speakProdPO.setProdtype(cr.GetValue(j, 16));// 该字段用于标识是产品包还是NCODE，若为0标识为NCODE那么COL_0里面的值就为NCODE，若为1，COL_1里面的内容就是产品包
                speakProdPO.setPrivid(cr.GetValue(j, 17));// 优惠编码
                speakProdPO.setNcode(cr.GetValue(j, 18));// 对产品包下的子产品或者模板下的子产品，返回“”；其它返回ncode
                speakProdPOList.add(speakProdPO);
            }
        }
        
        // 根据用户已开通列表、包下的子产品 查询已订购的子产品--------------------------------------------------------------
        if ("PCOpMod".equals(opertype))
        {
            List<SubsChildProdPO> hasSubProds = new ArrayList<SubsChildProdPO>();
            for (SubsChildProdPO po1 : subsChildProdPOList)
            {
                for (SpeakProdPO po2 : speakProdPOList)
                {
                    if (po1.getPkgid().equals(po2.getPkgid()) && po1.getProdid().equals(po2.getProdid()) && po1.getPrivid().equals(po2.getPrivid()))
                    {
                        // 子产品下优惠
                        hasSubProds.add(po1);
                    }
                }
            }
            String id = "";
            StringBuffer sbuf = new StringBuffer(id);
            Map map = new HashMap();
            for (SubsChildProdPO susChileProdPO : hasSubProds)
            {
                // 子产品
                if (!"".equals(susChileProdPO.getPrivid()))
                {
                    String key = susChileProdPO.getPkgid() + "~" + susChileProdPO.getProdid() + "~" ;
                    if (map.get(key) == null)
                    {
                        map.put(key, "");
                        sbuf.append(key).append("^");
                    }
                }
                
                // 子产品下优惠
                sbuf.append(susChileProdPO.getPkgid()).append("~").append(susChileProdPO.getProdid()).append("~").append(susChileProdPO.getPrivid()).append("^");
            }
            id = sbuf.toString();
            if (id.length() > 0)
            {
                returnStr = returnStr + id.substring(0, id.length()-1);
            }
        }
        
        // 返回信息
        PrintWriter out = this.getResponse().getWriter();
        out.write(returnStr);
        out.flush();
    }
    
    /**
     * 校验子产品
     * 
     * @return
     */
    public void checkBySubProd() throws IOException
    {
        // 头信息
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        this.getResponse().setContentType("text/html;charset=GBK");
        PrintWriter out = this.getResponse().getWriter();
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 客户信息
        NserCustomerSimp customerInfor = (NserCustomerSimp)getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // 当前产品PO
        ProdConfigPO prodConfigPO = (ProdConfigPO)getRequest().getSession().getAttribute(Constants.PROD_INFO); 
        
        String returnStr = "";
        
        // 查询包下的子产品 childProdIds-----------------------------------------------------------------------------
        String nCode = prodConfigPO.getNcode(); // 产品包编码、模板ID
        String type = prodConfigPO.getProdType(); // 类型：2 产品包 3 模板
        String opttype = opertype;// 操作类型
        
        // 执行查询
        Map mapResult = quickPubBean.qrySubProds(terminalInfo, customerInfor, curMenuId, nCode, type, opttype);
        
        List<SubsChildProdPO> subsChildProdPOList = new ArrayList<SubsChildProdPO>();
        ChildProdPO childProdPO = new ChildProdPO();
        if (null != mapResult && null != mapResult.get("returnObj"))
        {
            // 返回值
            Vector vector = (Vector)(mapResult.get("returnObj"));
            
            // 返回CTagSet
            CTagSet ctagset = (CTagSet)vector.get(0);
            
            // 返回CRSet
            CRSet crset = (CRSet)vector.get(1);
            
            // 产品包
            childProdPO.setMinprod((String)ctagset.get("minprod"));// 可选产品最小数
            childProdPO.setMaxprod((String)ctagset.get("maxprod"));// 可选产品最大数
            
            // 子产品列表
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                SubsChildProdPO subsChildProdPO = new SubsChildProdPO();
                subsChildProdPO.setPkgid(crset.GetValue(i, 0));// 产品包编码
                subsChildProdPO.setProdid(crset.GetValue(i, 1));// 产品编码
                subsChildProdPO.setPrivid(crset.GetValue(i, 2));// 优惠编码
                subsChildProdPO.setProdname(crset.GetValue(i, 3));// 产品名称
                
                // modify begin jWX216858 V200R003C10LG0901 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
                subsChildProdPO.setIsmandatory(this.convertType(crset.GetValue(i, 4)));// 选择类型 0：可选 1：必选
                // modify end jWX216858 V200R003C10LG0901 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
                subsChildProdPO.setHasattr(crset.GetValue(i, 5));// 是否有附加属性 0：无 1：有
                subsChildProdPO.setInftype(crset.GetValue(i, 6));// 接口业务类型 PCIntRelaNormal：普通业务 PCIntRelaRadio：业务切换
                subsChildProdPOList.add(subsChildProdPO);
            }
            
            // 取出子产品 key:pkgid~prodid~ value:优惠个数^产品是否必选^产品名称
            Map<String,String> map = new HashMap<String,String>();
            for (SubsChildProdPO subsChildProdPO : subsChildProdPOList)
            {
                String key = subsChildProdPO.getPkgid() + "~" + subsChildProdPO.getProdid() + "~";
                if ("".equals(subsChildProdPO.getPrivid()))
                {
                    map.put(key, "0^"+subsChildProdPO.getIsmandatory()+"^"+subsChildProdPO.getProdname());
                }
                else
                {
                    int count = Integer.parseInt(map.get(key).split("\\^")[0]);
                    String ismandatory = map.get(key).split("\\^")[1];
                    String prodname = map.get(key).split("\\^")[2];
                    map.put(key, String.valueOf(count + 1) + "^" + ismandatory + "^" + prodname);
                }
            }

            // 校验_子产品校验必选
            for (Entry<String,String> entry : map.entrySet())
            {
            	int count = Integer.parseInt(entry.getValue().split("\\^")[0]);
            	String ismandatory = entry.getValue().split("\\^")[1];
        		String prodname = entry.getValue().split("\\^")[2];
        		String key = entry.getKey();
        		if ("1".equals(ismandatory))
        		{
        			if (!childProdIds.contains(key))
        			{
                        out.write(prodname + "必选！");
                        out.flush();
                        return;
        			}
        		}
            }
            
            // 校验_选择的个数
            String prodObjs[] = childProdIds.split("\\^");
            Map<String,String> prodMap = new HashMap<String,String>();
            for (int i=0;i<prodObjs.length;i++)
            {
            	String pkgid = prodObjs[i].split("~")[0];
            	String prodid = prodObjs[i].split("~")[1];
            	//String privid = prodObjs[i].split("~")[2];
            	if(prodMap.get(pkgid+"~"+prodid+"~") == null)
            	{
            		prodMap.put(pkgid+"~"+prodid+"~", "");
            	}
            }
            if (prodMap.size() > Integer.parseInt(childProdPO.getMaxprod()) && prodMap.size() < Integer.parseInt(childProdPO.getMinprod()))
            {
                out.write("可选最少"+childProdPO.getMinprod()+"个,最大" + childProdPO.getMaxprod()+"个。");
                out.flush();
                return;
            }

        }
        
        out.write("");
        out.flush();
    }
    
    /**
     * 未登录转向服务密码认证,认证后判断用户是否可以办理此业务
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String authPassword()
    {
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();

        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        NserCustomerSimp customerInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        ProdConfigPO prodConfigPO = (ProdConfigPO)session.getAttribute(Constants.PROD_INFO);
        
        // 用户可以办理的业务
        List<ProdConfigPO> prods = new ArrayList<ProdConfigPO>();
        
        ProdConfigPO paramPO = new ProdConfigPO();

        paramPO.setBrand(customerInfor.getBrandID());
        paramPO.setMainProd(customerInfor.getProductID());
        paramPO.setAreaCode(customerInfor.getRegionID());
        
        // 产品查询:查询产品配置表
        prods = quickPubService.getProdList(paramPO);
        
        // 处理重复的NCODE
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < prods.size(); i++) 
        {
            String nCode = prods.get(i).getNcode();
            if (map.get(nCode) != null) 
            {
                prods.remove(i);
                i--;
            } 
            else 
            {
                map.put(nCode, "");
            }
        }
        
        CRSet cr = new CRSet();
        
        // 调接口获取用户已开通产品
        Map mapResult = quickPubBean.qryHasProds(terminalInfo, customerInfor, curMenuId);
        
        if (null != mapResult && "1".equals(mapResult.get("successFlag")))
        {
            cr = (CRSet)(mapResult.get("returnObj"));
            
            this.createRecLog(Constants.BUSITYPE_QRYHASPRODS, "0", "0", "0", "用户已订购产品信息查询:用户已订购产品信息查询成功!");
        }
        else if (null != mapResult && null == mapResult.get("returnObj"))
        {
            String resultMsg = (String)mapResult.get("returnMsg");
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000002", String.valueOf(mapResult.get("errcode")), null);
            
            this.createRecLog(Constants.BUSITYPE_QRYADDATTR, "0", "0", "1", resultMsg);
            
            this.getRequest().setAttribute("errormessage", resultMsg);
            
            // 返回
            return forward;
            
        }
        else
        {
            String resultMsg = "已订购产品信息查询失败";
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000002", "", null);
            
            this.createRecLog(Constants.BUSITYPE_QRYHASPRODS, "0", "0", "1", resultMsg);
            
            this.getRequest().setAttribute("errormessage", resultMsg);
            // 返回
            return forward;
        }
        
       
        if(null != cr)
        {
            // 已开通业务
            for (int i = 0; i < cr.GetRowCount(); i++)
            {
                String nCode = cr.GetValue(i, 18);
                if (prodConfigPO.getNcode().equals(nCode))
                {
                    // 当前产品已开通
                    prodConfigPO.setIsOpen("1");
                    break;
                }
            }
        }
        
        // 判断当前业务是否可以办理
        for (ProdConfigPO prodConfig : prods)
        {
            if(prodConfig.getNcode().equals(prodConfigPO.getNcode()))
            {
                prodConfigPO.setIsSupportRec("1");
                break;
            }
        }
        
        // add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
        String tipFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRODTIPS_FLAG);        
        if ("1".equals(tipFlag))
        {           
            tip = receptionBean.qryNcodeTips(customerInfor, terminalInfo, prodConfigPO.getNcode(), "PCOpRec", "PCTIPNORMAL", curMenuId);
        }
        // add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
        
        // 标志：未登录，登录后转向prodDetail.jsp
        this.getRequest().setAttribute("loginFlag", "1");
        
        return "prodDetail";
    }
    
    /**
     * 展示普通产品附加属性 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String showAddAttr()
    {
        return "showAddAttr";
    }
    
    /**
     * 普通产品受理 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String prodRec()
    {
        
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        // 当前终端机
        NserCustomerSimp customerInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 当前客户
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 当前产品包
        ProdConfigPO prodConfigPO = (ProdConfigPO)session.getAttribute(Constants.PROD_INFO);
        
        // 有附加属性产品订购
        if (!"".equals(selectAttrStr) && null != selectAttrStr )
        {
            selectAttrStr = selectAttrStr.replaceAll(";:;", "#");
            
            // 开通时，将不显示的默认附加属性添加到要开通的附加属性串中
            if("PCOpRec".equals(opertype))
            {
                List<AddAttrPO> addAttrPOList = prodConfigPO.getAddAttrPOList();
                
                for (AddAttrPO addAttrPO : addAttrPOList)
                {
                    if (addAttrPO.getIsshow().equals("0"))
                    {
                        // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
//                        if (!"".equals(selectAttrStr))
//                        {
//                            selectAttrStr = selectAttrStr + "#" + addAttrPO.getAttrid() + "=" + addAttrPO.getAttrvalue() + "=optype";
//                        }
//                        else
//                        {
//                            selectAttrStr = addAttrPO.getAttrid() + "=" + addAttrPO.getAttrvalue() + "=optype";
//                        }
                        selectAttrStr = (!"".equals(selectAttrStr)) ? selectAttrStr + "#" + addAttrPO.getAttrid() + "=" + addAttrPO.getAttrvalue() + "=optype" : addAttrPO.getAttrid() + "=" + addAttrPO.getAttrvalue() + "=optype";
                        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
                    }
                }
            }
        }
        
        // 优惠附加属性ID，转换为大写
        selectAttrStr = getUpperAttrStr(selectAttrStr);
       
        MultiProdCommitPO multiProdCommitPO = new MultiProdCommitPO();
        ProdCommitPO prodCommitPO = new ProdCommitPO();
        multiProdCommitPO.setTelnum(customerInfor.getServNumber());// 手机号码
        multiProdCommitPO.setIscheck("");// 标识该接口是否只进行校验，不进行办理 0：不进行校验，而是进行办理 1：只进行校验，不进行办理 可以传“”，默认为不进行校验，而是办理
        multiProdCommitPO.setDoneenum("");// 赠送号码
        multiProdCommitPO.setIscalcfee("");// 算费标识
        multiProdCommitPO.setIssendsms("1");// 短信发送标识
        multiProdCommitPO.setOpersource("");// 传“”
        
        prodCommitPO.setNcode(prodConfigPO.getNcode());// 产品编码
        
        if("".equals(effectType) || null == effectType)
        {
            effectType = "0";
        }
        
        prodCommitPO.setEffecttype(effectType);// 生效方式
        prodCommitPO.setOpertype(opertype);// 操作类型
        prodCommitPO.setAttrparam(selectAttrStr);// 附加属性
        prodCommitPO.setOldprodid("");// 原增值产品编码
        prodCommitPO.setServiceres("");// 服务资源
        prodCommitPO.setInftype("");// 接口对应类型
        prodCommitPO.setPkgid("");// 产品包编码
        prodCommitPO.setObjtype("NCODE");// 对象类型
        prodCommitPO.setPrivid("");// 优惠编码
        prodCommitPO.setTempletid("");// 模板编码
        prodCommitPO.setTempletflag("");// 是否按模板处理
        prodCommitPO.setStartdate(startDate == null ? "" : startDate);// 具体生效时间
        prodCommitPO.setEnddate(endDate == null ? "" : endDate);// 具体失效时间
        
        List<ProdCommitPO> prodCommitList = new ArrayList<ProdCommitPO>();
        prodCommitList.add(prodCommitPO);
        multiProdCommitPO.setProdCommitList(prodCommitList);
        
        ReturnWrap rw = quickPubBean.prodRec(terminalInfo, customerInfor, curMenuId, multiProdCommitPO);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String message = "";
            
            if ("PCOpRec".equals(opertype))
            {
                message = "产品开通成功！";
            }
            else if ("PCOpMod".equals(opertype))
            {
                message = "产品变更成功！";
            }
            else if ("PCOpDel".equals(opertype))
            {
                message = "产品退订成功！";
            }
            
            setSuccessMessage(message);
            
            forward = "success";
            
            this.createRecLog(Constants.BUSITYPE_PRODREC, "0", "0", "0", message);
            
        }
        else
        {
            String resultMsg = "产品受理失败";
            String errorCode = "";
            if(null !=  rw)
            {
                resultMsg = rw.getReturnMsg();
                errorCode = String.valueOf(rw.getErrcode());
            }
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000005", errorCode, formatParams(opertype, prodConfigPO.getProdName()));
            
            this.createRecLog(Constants.BUSITYPE_PRODREC, "0", "0", "1", resultMsg);
            
            this.getRequest().setAttribute("errormessage", resultMsg);
        }
        
        return forward;
    }
    
    /**
     * 产品包/模板受理 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String prodPackegRec()
    {
 
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        // 当前终端机
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 当前客户
        NserCustomerSimp customerInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 当前产品包/模板
        ProdConfigPO prodConfigPO = (ProdConfigPO)session.getAttribute(Constants.PROD_INFO);
        
        // 拼装提交对象
        MultiProdCommitPO multiProdCommitPO = new MultiProdCommitPO();
        multiProdCommitPO.setTelnum(customerInfor.getServNumber());// 手机号码
        multiProdCommitPO.setIscheck("");// 标识该接口是否只进行校验，不进行办理 0：不进行校验，而是进行办理 1：只进行校验，不进行办理 可以传“”，默认为不进行校验，而是办理
        multiProdCommitPO.setDoneenum("");// 赠送号码
        multiProdCommitPO.setIscalcfee("");// 算费标识
        multiProdCommitPO.setIssendsms("1");// 短信发送标识
        multiProdCommitPO.setOpersource("");// 传“”
        
        if (!"PCOpDel".equals(opertype))
        {
	        // 查询用户已开通产品------------------------------------------------------------------------------------------------
	        Map mapResult = quickPubBean.qryHasProds(terminalInfo, customerInfor, curMenuId);
	        
	        CRSet cr = new CRSet();
	        if (null != mapResult && null != mapResult.get("returnObj"))
	        {
	            cr = (CRSet)(mapResult.get("returnObj"));
	            speakProdPOList = new ArrayList<SpeakProdPO>();
	            SpeakProdPO speakProdPO;
	            for (int j = 0; j < cr.GetRowCount(); j++)
	            {
	                speakProdPO = new SpeakProdPO();
	                speakProdPO.setProdid(cr.GetValue(j, 0));// 对产品包下的子产品或者模板下的子产品，返回产品编码；其它返回“”
	                speakProdPO.setProdname(cr.GetValue(j, 1));// 产品名称
	                speakProdPO.setAttrparam(cr.GetValue(j, 2));// 附加属性串
	                speakProdPO.setServiceres(cr.GetValue(j, 3));// 服务资源串
	                speakProdPO.setRecdate(cr.GetValue(j, 4));// 受理时间，格式：yyyymmddhh24miss
	                speakProdPO.setStartdate(cr.GetValue(j, 5));// 开始时间，格式：yyyymmddhh24miss
	                speakProdPO.setEnddate(cr.GetValue(j, 6));// 结束时间，格式：yyyymmddhh24miss
	                speakProdPO.setStatus(cr.GetValue(j, 7));// 状态 0：预约 1：正常 2：暂停 3：已退订
	                speakProdPO.setFormnum(cr.GetValue(j, 8));// 操作流水
	                speakProdPO.setPkgtype(cr.GetValue(j, 9));// 套餐大类
	                speakProdPO.setProddesc(cr.GetValue(j, 10));// 产品描述
	                speakProdPO.setDoneenum(cr.GetValue(j, 11));// 赠送方
	                speakProdPO.setDoneerelaid(cr.GetValue(j, 12));// 赠送关系编码
	                speakProdPO.setPkgname(cr.GetValue(j, 13));// 套餐大类名称
	                speakProdPO.setCanceldate(cr.GetValue(j, 14));// 取消时间，格式：yyyymmddhh24miss
	                speakProdPO.setPkgid(cr.GetValue(j, 15));// 对产品包下的子产品或者模板下的子产品，返回产品包编码或者模板编码；其它返回“”
	                speakProdPO.setProdtype(cr.GetValue(j, 16));// 该字段用于标识是产品包还是NCODE，若为0标识为NCODE那么COL_0里面的值就为NCODE，若为1，COL_1里面的内容就是产品包
	                speakProdPO.setPrivid(cr.GetValue(j, 17));// 优惠编码
	                speakProdPO.setNcode(cr.GetValue(j, 18));// 对产品包下的子产品或者模板下的子产品，返回“”；其它返回ncode
	                speakProdPOList.add(speakProdPO);
	            }
	        }
	        // -------------------------------------------------------------------------------------------
	        
	        // 查询包下的子产品 -----------------------------------------------------------------------------
	        String nCode = prodConfigPO.getNcode(); // 产品包编码、模板ID
	        String type = prodConfigPO.getProdType(); // 类型：2 产品包 3 模板
	        String opttype = opertype;// 操作类型
	        mapResult = quickPubBean.qrySubProds(terminalInfo, customerInfor, curMenuId, nCode, type, opttype);
	        List<SubsChildProdPO> subsChildProdPOList = null;
	        if (null != mapResult && null != mapResult.get("returnObj"))
	        {
	            // 返回值
	            Vector vector = (Vector)(mapResult.get("returnObj"));
	            
	            // 返回CTagSet
	            CTagSet ctagset = (CTagSet)vector.get(0);
	            
	            // 返回CRSet
	            CRSet crset = (CRSet)vector.get(1);
	            
	            // 子产品包
	            ChildProdPO childProdPO = new ChildProdPO();
	            childProdPO.setMinprod((String)ctagset.get("minprod"));// 可选产品最小数
	            childProdPO.setMaxprod((String)ctagset.get("maxprod"));// 可选产品最大数
	            
	            subsChildProdPOList = new ArrayList<SubsChildProdPO>();
	            for (int i = 0; i < crset.GetRowCount(); i++)
	            {
	                SubsChildProdPO subsChildProdPO = new SubsChildProdPO();
	                subsChildProdPO.setPkgid(crset.GetValue(i, 0));// 产品包编码
	                subsChildProdPO.setProdid(crset.GetValue(i, 1));// 产品编码
	                subsChildProdPO.setPrivid(crset.GetValue(i, 2));// 优惠编码
	                subsChildProdPO.setProdname(crset.GetValue(i, 3));// 产品名称
	                
	                // modify begin jWX216858 V200R003C10LG0901 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
	                subsChildProdPO.setIsmandatory(this.convertType(crset.GetValue(i, 4)));// 选择类型 0：可选 1：必选
	                // modify end jWX216858 V200R003C10LG0901 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布

	                subsChildProdPO.setHasattr(crset.GetValue(i, 5));// 是否有附加属性 0：无 1：有
	                subsChildProdPO.setInftype(crset.GetValue(i, 6));// 接口业务类型 PCIntRelaNormal：普通业务 PCIntRelaRadio：业务切换
	                subsChildProdPOList.add(subsChildProdPO);
	            }
	        }
	        // --------------------------------------------------------------------------------------------------------
	        
	        // 已开通的 包下子产品的优惠 + 包下子产品下无优惠的子产品--------------------------------------------------------------
	        List<SubsChildProdPO> hasSubProds = new ArrayList<SubsChildProdPO>();
	        if ("PCOpMod".equals(opertype))
	        {
		        for (SubsChildProdPO po1 : subsChildProdPOList)// 包下子产品
		        {
		        	for (SpeakProdPO po2 : speakProdPOList)// 已开通
		        	{
		        		if (po1.getPkgid().equals(po2.getPkgid()) && po1.getProdid().equals(po2.getProdid()) && po1.getPrivid().equals(po2.getPrivid()))
		        		{
		        		    // 已开通最后一级
		        			hasSubProds.add(po1);
		        		}
		        	}
		        }
	        }
	        
	        // 已开通的 包下子产品的优惠的子产品
	        Map map = new HashMap();
	        List<SubsChildProdPO> hasSubProds_ = new ArrayList<SubsChildProdPO>();
	        for (SubsChildProdPO po : hasSubProds)// 包下子产品
            {
	            if (!"".equals(po.getPrivid()))
	            {
	                String key = po.getPkgid() + "~" + po.getProdid() + "~";
	                if (map.get(key) == null)
	                {
	                    map.put(key, "");
	                    SubsChildProdPO tmp = new SubsChildProdPO();
	                    tmp.setPkgid(po.getPkgid());
	                    tmp.setProdid(po.getProdid());
	                    tmp.setPrivid("");
	                    hasSubProds_.add(tmp);
	                }
	            }
            }
	        for (SubsChildProdPO po : hasSubProds_)
	        {
	            hasSubProds.add(po);
	        }
	        
	        // 产品包受理------------------------------------------------------------------------------------------------
	        if (childProdIds != null)
	        {
		        // 选中的子产品
		        String prodIds[] = childProdIds.split("\\^");
		        
		        // 把选中的子产品封装到prodCommitList
		        List<ProdCommitPO> prodCommitList = new ArrayList<ProdCommitPO>();
		        ProdCommitPO prodCommitPO = null;
		        for (int i=0;i<prodIds.length;i++)
		        {
		            prodCommitPO = new ProdCommitPO();
		            
		            // 产品包编码
		            String pkgid = prodIds[i].split("~")[0];
		            
		            // 产品编码
		            String prodid = prodIds[i].split("~")[1];
		            
		            // 优惠编码
		            String privid = prodIds[i].split("~").length == 3 ? prodIds[i].split("~")[2] : "";
		            
		            prodCommitPO.setNcode(prodid);// 产品编码
		            prodCommitPO.setEffecttype(this.effectType == null || "".equals(this.effectType) ? "0" : this.effectType);// 生效方式
		            // 修改操作_ 子产品有PCOpRec:开通 PCOpMod:修改 PCOpDel:关闭
		            if ("PCOpMod".equals(opertype))
		            {
		            	if (this.isExist(speakProdPOList, pkgid, prodid, privid))
		            	{
		            		prodCommitPO.setOpertype("PCOpMod");// 修改
		            	}
		            	else
		            	{
		            		prodCommitPO.setOpertype("PCOpRec");// 新增
		            	}
		            }
		            else
		            {
		            	prodCommitPO.setOpertype(opertype);// 操作类型
		            }
		            String attrparam = getProdAddAttrs(childProdAddAttrs,prodIds[i]);
		            prodCommitPO.setAttrparam(attrparam);// 附加属性
		            prodCommitPO.setOldprodid("");// 原增值产品编码
		            prodCommitPO.setServiceres("");// 服务资源
		            prodCommitPO.setInftype("PCIntRelaNormal");// 接口对应类型，对产品包下子产品或者模板下子产品受理的时候使用。 PCIntRelaNormal：普通业务 PCIntRelaRadio：业务切换
		            prodCommitPO.setTempletflag("");// 是否按模板处理 1：是 “”：不是 目前没用，传“”即可
		            prodCommitPO.setStartdate(this.startDate == null ?"":this.startDate);// 具体生效时间
		            prodCommitPO.setEnddate(this.endDate == null ? "":this.endDate);// 具体失效时间
		            prodCommitPO.setPkgid(pkgid);// 产品包编码。对产品包下子产品或者模板下子产品受理的时候使用
		            prodCommitPO.setObjtype("".equals(privid)?"PCIntObjProd":"PCIntObjPriv");// 对象类型，包括产品、优惠、服务、SP、主体产品、模板、ncode。参考pc_prod_ncode_map。
		            prodCommitPO.setPrivid(privid);// 优惠编码。对产品包下子产品或者模板下子产品受理的时候使用
		            prodCommitPO.setTempletid("");// 是否按模板处理
		            prodCommitList.add(prodCommitPO);
		        }
		        
		        // 已订购被删除的子产品
		        List<ProdCommitPO> prodCommitList_del = new ArrayList<ProdCommitPO>();
		        
		        // 已订购又订购的子产品
		        List<ProdCommitPO> prodCommitList_mod = new ArrayList<ProdCommitPO>();
		        
		        // 新增的子产品
		        List<ProdCommitPO> prodCommitList_add = new ArrayList<ProdCommitPO>();
		        
		        if ("PCOpMod".equals(opertype))
	            {
		            // 获取删除和修改的子产品
		        	for (SubsChildProdPO po1 : hasSubProds)
		        	{
		        		int bz = 0;
		        		for (ProdCommitPO po2 : prodCommitList)
		        		{
		        		    if (po1.getPkgid().equals(po2.getPkgid()) && po1.getProdid().equals(po2.getNcode()) && po1.getPrivid().equals(po2.getPrivid()))
		        			{
		        				bz = 1;
		        				prodCommitList_mod.add(po2);
		        			}
		        		}
		        		if (bz == 0)
		        		{
		        			prodCommitPO = new ProdCommitPO();
	        				prodCommitPO.setNcode(po1.getProdid());// 产品编码
	        	            prodCommitPO.setEffecttype("0");// 生效方式
	        	            prodCommitPO.setOpertype("PCOpDel");// 修改
	        	            prodCommitPO.setAttrparam("");// 附加属性
	        	            prodCommitPO.setOldprodid("");// 原增值产品编码
	        	            prodCommitPO.setServiceres("");// 服务资源
	        	            prodCommitPO.setInftype("PCIntRelaNormal");// 接口对应类型，对产品包下子产品或者模板下子产品受理的时候使用。 PCIntRelaNormal：普通业务 PCIntRelaRadio：业务切换
	        	            prodCommitPO.setTempletflag("");// 是否按模板处理 1：是 “”：不是 目前没用，传“”即可
	        	            prodCommitPO.setStartdate("");// 具体生效时间
	        	            prodCommitPO.setEnddate("");// 具体失效时间
	        	            prodCommitPO.setPkgid(po1.getPkgid());// 产品包编码。对产品包下子产品或者模板下子产品受理的时候使用
	        	            prodCommitPO.setObjtype("".equals(po1.getPrivid())?"PCIntObjProd":"PCIntObjPriv");// 对象类型，包括产品、优惠、服务、SP、主体产品、模板、ncode。参考pc_prod_ncode_map。
	        	            prodCommitPO.setPrivid(po1.getPrivid());// 优惠编码。对产品包下子产品或者模板下子产品受理的时候使用
	        	            prodCommitPO.setTempletid("");// 是否按模板处理
	        	            prodCommitList_del.add(prodCommitPO);
		        		}
		        	}
		        	
		        	// 获取新增的子产品
		        	for (ProdCommitPO po1 : prodCommitList)
		        	{
		        	    int bz = 0;
		        	    for (ProdCommitPO po2 : prodCommitList_mod)
		        	    {
		        	        if (po1.getPkgid().equals(po2.getPkgid()) && po1.getNcode().equals(po2.getNcode()) && po1.getPrivid().equals(po2.getPrivid()))
		        	        {
		        	            bz = 1;
		        	            break;
		        	        }
		        	    }
		        	    if (bz == 0)
		        	    {
		        	        prodCommitList_add.add(po1);
		        	    }
		        	}
		        	
			        // 组装提交列表
			        List<ProdCommitPO> prodCommits = new ArrayList<ProdCommitPO>();
			        
			        // 加入删除列表
			        for (ProdCommitPO po : prodCommitList_del)
			        {
			        	prodCommits.add(po);
			        }
			        
			        // 加入修改列表
			        for (ProdCommitPO po : prodCommitList_mod)
			        {
			            ProdCommitPO addrparam = balanceAddAttr(po);
			            if (addrparam != null)
			            {
			                po.setAttrparam(getAttrparamStr(addrparam.getAttrparam()));
			                prodCommits.add(po);
			            }
			        }
			        
			        // 加入新增列表
			        for (ProdCommitPO po : prodCommitList_add)
			        {
			            po.setAttrparam(getAttrparamStr(po.getAttrparam()));
			            prodCommits.add(po);
			        }
			        
			        prodCommitList = prodCommits;
	            }
	
		        multiProdCommitPO.setProdCommitList(prodCommitList);
	        }
	        
	        // 排序
	        
	        // 所有需提交的产品及优惠列表
	        List<ProdCommitPO> allList = multiProdCommitPO.getProdCommitList();
	        
	        // 产品
	        List<ProdCommitPO> prodList = new ArrayList<ProdCommitPO>();
	        
	        // 优惠
	        List<ProdCommitPO> privList = new ArrayList<ProdCommitPO>();
	        
	        // 迭代
	        for (ProdCommitPO po : allList)
	        {
	            // 优惠
	            if (!"".equals(po.getPrivid()))
	            {
	                privList.add(po);
	            }
	            // 产品
	            else
	            {
	                prodList.add(po);
	            }
	        }
	        
	        // 清空
	        allList.clear();
	        
	        // 按顺序加入
	        allList.addAll(prodList);
	        allList.addAll(privList);
	        
	        // 存入对象
	        multiProdCommitPO.setProdCommitList(allList);
        }
        else
        {
        	List<ProdCommitPO> list = new ArrayList<ProdCommitPO>();
        	ProdCommitPO prodCommitPO = new ProdCommitPO();
			prodCommitPO.setNcode(prodConfigPO.getNcode());// 产品编码
            prodCommitPO.setEffecttype(this.effectType == null || "".equals(this.effectType) ? "0" : this.effectType);// 生效方式
            prodCommitPO.setOpertype("PCOpDel");// 删除
            prodCommitPO.setAttrparam("");// 附加属性
            prodCommitPO.setOldprodid("");// 原增值产品编码
            prodCommitPO.setServiceres("");// 服务资源
            prodCommitPO.setInftype("PCIntRelaNormal");// 接口对应类型，对产品包下子产品或者模板下子产品受理的时候使用。 PCIntRelaNormal：普通业务 PCIntRelaRadio：业务切换
            prodCommitPO.setTempletflag("");// 是否按模板处理 1：是 “”：不是 目前没用，传“”即可
            prodCommitPO.setStartdate(this.startDate == null ?"":this.startDate);// 具体生效时间
            prodCommitPO.setEnddate(this.endDate == null ?"":this.endDate);// 具体失效时间
            prodCommitPO.setPkgid("");// 产品包编码。对产品包下子产品或者模板下子产品受理的时候使用
            prodCommitPO.setObjtype("NCODE");// 对象类型，包括产品、优惠、服务、SP、主体产品、模板、ncode。参考pc_prod_ncode_map。
            prodCommitPO.setPrivid("");// 优惠编码。对产品包下子产品或者模板下子产品受理的时候使用
            prodCommitPO.setTempletid("");// 是否按模板处理
            list.add(prodCommitPO);
            multiProdCommitPO.setProdCommitList(list);
        }
        
        List<ProdCommitPO> prodCommitPOs = multiProdCommitPO.getProdCommitList();
        if (prodCommitPOs != null && prodCommitPOs.size() > 0)
        {
            // 调用产品包受理
            ReturnWrap rw = quickPubBean.prodRec(terminalInfo, customerInfor, curMenuId, multiProdCommitPO);
            if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
            {
                
                setSuccessMessage("产品受理成功！");
                
                forward = "success";
                
                this.createRecLog(Constants.BUSITYPE_PRODREC, "0", "0", "0", "产品受理:产品受理成功!");
    
            }
            else
            {
                String resultMsg = "产品受理失败";
                String errorCode = "";
                if(null !=  rw)
                {
                    resultMsg = rw.getReturnMsg();
                    errorCode = String.valueOf(rw.getErrcode());
                }
                
                resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000005", errorCode, formatParams(opertype, prodConfigPO.getProdName()));
                
                this.createRecLog(Constants.BUSITYPE_PRODREC, "0", "0", "1", resultMsg);
                
                this.getRequest().setAttribute("errormessage", resultMsg);
            }
        }
        else
        {
            setErrormessage("包订购内容没有改变，不能做变更！");
        }
        
        return forward;
    }
    
    private String[] formatParams(String operType, String prodName)
    {
        String[] params = new String[]{"", prodName};
        
        if ("PCOpRec".equals(opertype))
        {
            params[0] = "开通";
        }
        else if ("PCOpMod".equals(opertype))
        {
            params[0] = "变更";
        }
        else if ("PCOpDel".equals(opertype))
        {
            params[0] = "退订";
        }
        
        return params;
    }
    
    /**
     * 产品是否存在有已订购列表内
     * @param speakProdPOList
     * @param pkgid
     * @param prodid
     * @param privid
     * @return
     */
    private boolean isExist(List<SpeakProdPO> speakProdPOList, String pkgid, String prodid, String privid)
    {
    	// 迭代查询
    	for (SpeakProdPO speakProdPO : speakProdPOList)
    	{
    		if (pkgid.equals(speakProdPO.getPkgid()) && prodid.equals(speakProdPO.getProdid()) && privid.equals(speakProdPO.getPrivid()))
    		{
    			return true;
    		}
    	}
    	
    	// 返回
    	return false;
    }
    
    /**
     * 取产品附加属性
     * <功能详细描述>
     * @param childProdAddAttrs
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getProdAddAttrs(String childProdAddAttrs, String id)
    {
        String prodAddAttrs[] = childProdAddAttrs.split("]");
        for (int i=0;i<prodAddAttrs.length;i++)
        {
            if (prodAddAttrs[i].split("::")[0].equals(id))
            {
                if(prodAddAttrs[i].split("::").length>1)
                {
                    return prodAddAttrs[i].split("::")[1];
                }
                else
                {
                    return "";
                }
            }
        }
        return "";
    }
    
    /**
     * 比对附加属性是否修改过
     * <功能详细描述>
     * @param prodid
     * @param privid
     * @return
     * @see [类、类#方法、类#成员]
     */
    private ProdCommitPO balanceAddAttr(ProdCommitPO prodCommitPO)
    {
        String attrparam = prodCommitPO.getAttrparam();
        if ("".equals(attrparam))
        {
            return null;
        }
        
        HttpSession session = this.getRequest().getSession();
        
        // 当前终端机
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 当前客户
        NserCustomerSimp customerInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 产品编码
        String prodid = prodCommitPO.getNcode();
        
        // 优惠编码
        String privid = prodCommitPO.getPrivid();
    
        // 产品编码或优惠编码
        String ncode = "".equals(privid)?prodid:privid;
        
        // 查询类型 0：NCODE 1：产品包下子产品或者模板下子产品 2 :优惠编码
        String qrytype = "".equals(privid)?"1":"2";
        
        // 存放用户订购的属性值
        Map<String,String> map_all = new HashMap<String,String>();
        
        // 调用接口查询产品附加属性
        Map mapResult = quickPubBean.qryAddAttr(terminalInfo, customerInfor, curMenuId, qrytype, ncode, "PCOpMod");
        
        if (null != mapResult && null != mapResult.get("returnObj"))
        {
            CRSet cr = (CRSet)(mapResult.get("returnObj"));
            
            // 正文
            for (int i = 0; i < cr.GetRowCount(); i++)
            {
                AddAttrPO addStrPO = new AddAttrPO();
                addStrPO.setObjid(cr.GetValue(i, 0));// 对象编码
                addStrPO.setAttrid(cr.GetValue(i, 1));// 附加属性编码
                addStrPO.setAttrname(cr.GetValue(i, 2));// 附加属性名称
                addStrPO.setAttrtype(cr.GetValue(i, 3));// 附加属性类型，参考： select t.* from dict_item t where t.groupid = 'EditorType' MEMO，多行编辑框 SINGLE，单行编辑框 DATE，时间编辑框(格式：yyyy-mm-dd hh24:mi:ss) SELECT，下拉列表
                addStrPO.setValuetype(cr.GetValue(i, 4));// 值类型 NUMBER:数字 STRING:字符串
                addStrPO.setMinlength(cr.GetValue(i, 5));// 附加属性值最小长度
                addStrPO.setMaxlength(cr.GetValue(i, 6));// 附加属性值最大长度
                addStrPO.setIsmandatory(cr.GetValue(i, 7));// 是否必须 0：不是 1：是
                addStrPO.setIsshow(cr.GetValue(i, 8));// 是否界面展现 0：不是 1：是
                addStrPO.setAttrnum(cr.GetValue(i, 9));// 属性值数量
                addStrPO.setAttrsplit(cr.GetValue(i, 10));// 附加属性分隔符
                addStrPO.setAttrvalue(cr.GetValue(i, 11));// 用户订购值。如果用户没有订购，就填充默认值。
                addStrPO.setDictinfo(cr.GetValue(i, 12));// 字典信息，格式： dictid=dictname|dictid=dictname ...
                addStrPO.setObjType(cr.GetValue(i, 13));// 对象类型:产品/优惠/服务
                map_all.put(addStrPO.getAttrid(), addStrPO.getAttrvalue());
            }
        }
        
        // 前台提交的附加属性转换成MAP
        Map<String,String> map_tj = new HashMap<String,String>();
        String attrparams[] = attrparam.split("#");
        for (int i=0;i<attrparams.length;i++)
        {
            String objs[] = attrparams[i].split("=");
            String key = objs[0];
            String value = objs.length > 1 ? objs[1] : "";
            map_tj.put(key, value);
        }
        
        String addattr = "";
        StringBuffer sbuf = new StringBuffer(addattr);
        for (Entry<String,String> entry1 : map_all.entrySet())
        {
            int bz = 0;
            String key1 = entry1.getKey();
            for (Entry<String,String> entry2 : map_tj.entrySet())
            {
                String key2 = entry2.getKey();
                if (key1.equals(key2) && map_all.get(key1).equals(map_tj.get(key2)))
                {
                    bz = 1;
                    break;
                }
            }
            if (bz == 0)
            {
                sbuf.append(key1).append("=").append(map_tj.get(key1)).append("=#");
            }
        }
        addattr = sbuf.toString();
        if (!"".equals(addattr))
        {
            
            ProdCommitPO po = prodCommitPO;
            po.setAttrparam(addattr);
            po.setOpertype("PCOpMod");
            return po;
        }
        
        return null;
    }
    
    /**
     * 取处理好的附加属性
     * <功能详细描述>
     * @param attrparam Priv:RESCONTENT=111111=OPTYPE#Priv:PASSWORD=111111=OPTYPE#Priv:SEX=0=OPTYPE#Priv:ELECTRY=1111=OPTYPE#Priv:CMNET_RENT1==OPTYPE#
     * @return
     * @see [类、类#方法、类#成员]
     */
//    private String getAttrparamStr(String attrparam)
//    {
//        if ("".equals(attrparam))
//        {
//            return attrparam;
//        }
//        
//        String returnStr = "";
//    	String attrs[] = attrparam.split("#");
//    	for (int i=0;i<attrs.length;i++)
//    	{
//    		String strs[] = attrs[i].split("=");
//    		returnStr = returnStr + strs[0].split(":")[0] + ":" + strs[0].split(":")[1].toUpperCase() + "=" + (strs.length > 1 ? strs[1] : "") + "=optype#";
//    	}
//    	return returnStr;
//    }
//    
    /**
     * 将优惠、服务附加属性名称转化为大写 （产品包）
     * 
     * @param attrparam
     *            Priv:RESCONTENT=111111=OPTYPE#Priv:PASSWORD=111111=OPTYPE#Priv:SEX=0=OPTYPE#Priv:ELECTRY=1111=OPTYPE#Priv:CMNET_RENT1==OPTYPE#
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getAttrparamStr(String attrparam)
    {
        if ("".equals(attrparam) || null == attrparam)
        {
            return "";
        }
        
        String returnStr = "";
        StringBuffer sbuf = new StringBuffer(returnStr);
        String attrs[] = attrparam.split("#");
        for (int i = 0; i < attrs.length; i++)
        {
            String strs[] = attrs[i].split("=");
            if ("Priv".equals(strs[0].split(":")[0]) || "Serv".equals(strs[0].split(":")[0]))
            {
                //sbuf.append(strs[0].split(":")[0]).append(":").append(strs[0].split(":")[1].toUpperCase()).append("=").append((strs.length > 1 ? strs[1] : "")).append("=optype#");
                String str = strs[0].split(":")[1];                
                Locale locale = new Locale(str);            
                str = str.toUpperCase(locale);  
                sbuf.append(strs[0].split(":")[0]).append(":").append(str).append("=").append((strs.length > 1 ? strs[1] : "")).append("=optype#");
            }
            else
            {
                sbuf.append(attrs[i]).append("optype#");
            }
        }
        returnStr = sbuf.toString();
        return returnStr;
    }
    
    /**
     * 将优惠、服务附加属性名称转化为大写 （普通产品）
     * 
     * @param attrparam
     *            Priv:RESCONTENT=111111=OPTYPE#Priv:PASSWORD=111111=OPTYPE#Priv:SEX=0=OPTYPE#Priv:ELECTRY=1111=OPTYPE#Priv:CMNET_RENT1==OPTYPE#
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getUpperAttrStr(String attrparam)
    {
        if ("".equals(attrparam) || null == attrparam)
        {
            return "";
        }
        
        String returnStr = "";
        StringBuffer sbuf = new StringBuffer(returnStr);
        String attrs[] = attrparam.split("#");
        for (int i = 0; i < attrs.length; i++)
        {
            String strs[] = attrs[i].split("=");
            if ("Priv".equals(strs[0].split(":")[0]) || "Serv".equals(strs[0].split(":")[0]))
            {
                //sbuf.append(strs[0].split(":")[0]).append(":").append(strs[0].split(":")[1].toUpperCase()).append("=").append((strs.length > 1 ? strs[1] : "")).append("=optype#");
                String str = strs[0].split(":")[1];                
                Locale locale = new Locale(str);            
                str = str.toUpperCase(locale);  
                sbuf.append(strs[0].split(":")[0]).append(":").append(str).append("=").append((strs.length > 1 ? strs[1] : "")).append("=optype#");
            }
            else
            {
                sbuf.append(attrs[i]).append("#");
            }
        }
        returnStr = sbuf.toString();
        return returnStr;
    }
    
    /**
     * 普通产品附加属性展示
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String goProdAddAttr()
    {
        String forward = "failed";

        HttpSession session = this.getRequest().getSession();

        // 当前终端机
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 当前客户
        NserCustomerSimp customerInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 当前产品包/模板
        ProdConfigPO prodConfigPO = (ProdConfigPO)session.getAttribute(Constants.PROD_INFO);
        
        // 查询类型 0：NCODE 1：产品包下产品
        String qryType = "0";
        
        // nCode
        String nCode = prodConfigPO.getNcode();
        
        // 调用接口查询产品附加属性
        Map mapResult = quickPubBean.qryAddAttr(terminalInfo, customerInfor, curMenuId, qryType, nCode, opertype);
        
        if (null != mapResult && null != mapResult.get("returnObj"))
        {
            CRSet cr = (CRSet)(mapResult.get("returnObj"));
            
            List<AddAttrPO> addAttrPOList = new ArrayList<AddAttrPO>();
            
            for (int i = 0; i < cr.GetRowCount(); i++)
            {

                AddAttrPO addStrPO = new AddAttrPO();
                addStrPO.setObjid(cr.GetValue(i, 0));// 对象编码
                addStrPO.setAttrid(cr.GetValue(i, 1));// 附加属性编码
                addStrPO.setAttrname(cr.GetValue(i, 2));// 附加属性名称
                addStrPO.setAttrtype(cr.GetValue(i, 3));// 附加属性类型
                addStrPO.setValuetype(cr.GetValue(i, 4));// 值类型
                addStrPO.setMinlength(cr.GetValue(i, 5));// 附加属性值最小长度
                addStrPO.setMaxlength(cr.GetValue(i, 6));// 附加属性值最大长度
                addStrPO.setIsmandatory(cr.GetValue(i, 7));// 是否必须
                addStrPO.setIsshow(cr.GetValue(i, 8));// 是否界面展现
                addStrPO.setAttrnum(cr.GetValue(i, 9));// 属性值数量
                addStrPO.setAttrsplit(cr.GetValue(i, 10));// 附加属性分隔符
                addStrPO.setAttrvalue(cr.GetValue(i, 11));// 用户订购值           
                addStrPO.setDictinfo(cr.GetValue(i, 12));// 字典信息
                addStrPO.setObjType(cr.GetValue(i, 13));//对象类型:产品/优惠/服务

                addAttrPOList.add(addStrPO);
                
            }
            
            prodConfigPO.setAddAttrPOList(addAttrPOList);
            this.createRecLog(Constants.BUSITYPE_QRYADDATTR, "0", "0", "0", "产品附加属性查询:产品附加属性查询成功!");
        }
        else if (null != mapResult && null == mapResult.get("returnObj"))
        {
            String resultMsg = "".equals(mapResult.get("returnMsg"))?"未查询到附加属性！":(String)mapResult.get("returnMsg");
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000004", String.valueOf(mapResult.get("errcode")), null);
            
            this.createRecLog(Constants.BUSITYPE_QRYADDATTR, "0", "0", "1", resultMsg);
            
            this.getRequest().setAttribute("errormessage", resultMsg);
            
            return forward;
        }
        else
        {
            String resultMsg = "产品附加属性查询失败!";
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_22_000004", "", null);
            
            this.createRecLog(Constants.BUSITYPE_QRYADDATTR, "0", "0", "1", resultMsg);
            
            this.getRequest().setAttribute("errormessage", resultMsg);
            
            return forward;
        }
        // 清session中的产品信息
        session.removeAttribute(Constants.PROD_INFO);
        
        // 将新选择的产品信息放到session中
        session.setAttribute(Constants.PROD_INFO, prodConfigPO);
        return "prodAddAttr";
    }
    
    /**
     * 转向生效方式选择页面
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String goEffectType()
    {
        return "goEffectType";
    }
    
    /**
     * 分页
     * 
     * @param list 菜单集合
     * @return
     * @see
     */
    public List<ProdConfigPO> getPageList(List<ProdConfigPO> list)
    {
        int sum = 0;
        int start = 0;
        int end = 0;
        
        // 获取当前页
        if (page == 0)
        {
            page = 1;
        }
        
        // 获取总页数
        if (!list.isEmpty())
        {
            sum = list.size();
            pageCount = list.size() / pageSize;
            if (list.size() % pageSize > 0)
            {
                pageCount = pageCount + 1;
            }
        }
        else
        {
            pageCount = 0;
        }
        
        start = (page - 1) * pageSize;
        if (page >= pageCount)
        {
            end = sum;
        }
        else
        {
            end = page * pageSize;
        }
        
        List<ProdConfigPO> prodConfigList = new ArrayList<ProdConfigPO>();
        
        for (int i = start; i < end; i++)
        {
            prodConfigList.add(list.get(i));
        }
        return prodConfigList;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public int getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public int getPageCount()
    {
        return pageCount;
    }
    
    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }
    
    public int getPage()
    {
        return page;
    }
    
    public void setPage(int page)
    {
        this.page = page;
    }
    
    public QuickPubService getQuickPubService()
    {
        return quickPubService;
    }
    
    public void setQuickPubService(QuickPubService quickPubService)
    {
        this.quickPubService = quickPubService;
    }
    
    public List<ProdConfigPO> getProdList()
    {
        return prodList;
    }
    
    public void setProdList(List<ProdConfigPO> prodList)
    {
        this.prodList = prodList;
    }
    
    public String getProdID()
    {
        return prodID;
    }
    
    public void setProdID(String prodID)
    {
        this.prodID = prodID;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public void setRegion(String region)
    {
        this.region = region;
    }
    
    public String getBrand()
    {
        return brand;
    }
    
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    
    public String getInitButton()
    {
        return initButton;
    }
    
    public void setInitButton(String initButton)
    {
        this.initButton = initButton;
    }
    
    public QuickPubBean getQuickPubBean()
    {
        return quickPubBean;
    }
    
    public void setQuickPubBean(QuickPubBean quickPubBean)
    {
        this.quickPubBean = quickPubBean;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public String getSuccessMessage()
    {
        return successMessage;
    }
    
    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }
    
    public String getSubProdId()
    {
        return subProdId;
    }
    
    public void setSubProdId(String subProdId)
    {
        this.subProdId = subProdId;
    }
    
    public String getChildProdIds()
    {
        return childProdIds;
    }
    
    public void setChildProdIds(String childProdIds)
    {
        this.childProdIds = childProdIds;
    }
    
    public String getChildProdAddAttrs()
    {
        return childProdAddAttrs;
    }
    
    public void setChildProdAddAttrs(String childProdAddAttrs)
    {
        this.childProdAddAttrs = childProdAddAttrs;
    }
    
    public String getInitBz()
    {
        return initBz;
    }

    public void setInitBz(String initBz)
    {
        this.initBz = initBz;
    }

    public String getSelectAttrStr()
    {
        return selectAttrStr;
    }
    
    public void setSelectAttrStr(String selectAttrStr)
    {
        this.selectAttrStr = selectAttrStr;
    }
    
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getOpertype()
    {
        return opertype;
    }
    
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setOpertype(String opertype)
    {
        this.opertype = opertype;
    }

	public List<SpeakProdPO> getSpeakProdPOList() {
		return speakProdPOList;
	}

	public void setSpeakProdPOList(List<SpeakProdPO> speakProdPOList) {
		this.speakProdPOList = speakProdPOList;
	}

    public String getEffectType()
    {
        return effectType;
    }

    public void setEffectType(String effectType)
    {
        this.effectType = effectType;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getChildProdIds_old()
    {
        return childProdIds_old;
    }

    public void setChildProdIds_old(String childProdIds_old)
    {
        this.childProdIds_old = childProdIds_old;
    }

    public String getChildProdAddAttrs_old()
    {
        return childProdAddAttrs_old;
    }

    public void setChildProdAddAttrs_old(String childProdAddAttrs_old)
    {
        this.childProdAddAttrs_old = childProdAddAttrs_old;
    }

    public ReceptionBean getReceptionBean()
    {
        return receptionBean;
    }

    public void setReceptionBean(ReceptionBean receptionBean)
    {
        this.receptionBean = receptionBean;
    }

    public String getTip()
    {
        return tip;
    }

    public void setTip(String tip)
    {
        this.tip = tip;
    }

    public String getTypeID()
    {
        return typeID;
    }

    public void setTypeID(String typeID)
    {
        this.typeID = typeID;
    }

    public String getStyle()
    {
        return style;
    }

    public void setStyle(String style)
    {
        this.style = style;
    }

    public String getHotRecFlag()
    {
        return hotRecFlag;
    }

    public void setHotRecFlag(String hotRecFlag)
    {
        this.hotRecFlag = hotRecFlag;
    }

    public String getQuickPubFlag()
    {
        return quickPubFlag;
    }

    public void setQuickPubFlag(String quickPubFlag)
    {
        this.quickPubFlag = quickPubFlag;
    }
    
    public String getSearchType()
    {
        return searchType;
    }

    public void setSearchType(String searchType)
    {
        this.searchType = searchType;
    }

    public String getButtonType()
    {
        return buttonType;
    }

    public void setButtonType(String buttonType)
    {
        this.buttonType = buttonType;
    }
}