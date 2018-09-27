/*
* @filename: BankHuakouAction.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  个人银行划扣业务
* @author: g00140516
* @de:  2012/09/05 
* @description: 
* @remark: create g00140516 2012/09/05 R003C12L07n01 OR_NX_201206_794
*/
package com.customize.nx.selfsvc.reception.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.nx.selfsvc.bean.BankHuakouBean;


import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 个人银行划扣业务
 * 
 * @author  g00140516
 * @version  1.0, 2012/09/05
 * @see  
 * @since  
 */
public class BankHuakouAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    public static final Log logger = LogFactory.getLog(BankHuakouAction.class);
    
    /**
     * 当前菜单
     */
    private String curMenuId;
    
    /**
     * 划扣类型
     */
    private String drawType = "";
    
    /**
     * 划扣金额
     */
    private String drawAmt = "";
    
    /**
     * 开通时间
     */
    private String createTime = "";
    
    /**
     * 银行账号
     */
    private String pan = "";
    
    /**
     * 身份证号
     */
    private String IDCard = "";
    
    /**
     * 划扣类型列表
     */
    public List<DictItemPO> drawTypes = null;
    
    /**
     * 划扣金额列表
     */
    public List<DictItemPO> drawAmts = null;
    
    private BankHuakouBean huakouBean = null;
    
    /**
     * 查询银行划扣支付方式，判断用户是否已开通
     * @return
     */
    public String qryChargeType()
    {
        HttpServletRequest request = getRequest();
        
        HttpSession session = request.getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        /**
         * 银行划扣支付方式
         */
        String paytype = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_CHARGETYPE);
        
        Map<String, Object> resultMap = huakouBean.qryChargeType(termInfo, customerSimp, curMenuId, paytype);
        
        // 已开通
        if ("1".equals((String) resultMap.get("flag")) && resultMap.get("resultObj") != null)
        {
            CTagSet tagSet = (CTagSet) resultMap.get("resultObj");
            
            // 将后台返回的划扣类型编码转为类型描述
            drawType = tagSet.GetValue("drawtype");
            qryDrawType();           
            
            // 将后台返回的划扣金额编码转为金额描述
            drawAmt = tagSet.GetValue("drawamt");
            qryDrawAmt();
            
            createTime = tagSet.GetValue("createdate");
            
            // 商户ID
            String merchantID = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_MERCHANTID);
            
            // 调用银联接口查询绑定关系
            Map<String, String> bankResultMap = huakouBean.qryBindInfo(merchantID, customerSimp.getSubsID());
            if (bankResultMap == null || !bankResultMap.containsKey("pan"))
            {
                String respDesc = "";
                if (bankResultMap != null)
                {
                    respDesc = (String) bankResultMap.get("respDesc");
                }
                
                if (respDesc == null || "".equals(respDesc.trim()))
                {
                    respDesc = "查询个人银行划扣业务办理状态失败";
                }
                
                logger.error(respDesc);
                
                request.setAttribute("errormessage", respDesc);
                
                this.createRecLog(curMenuId, "0", "0", "1", respDesc);
                
                return "error";
            }
            
            // 银行账号
            pan = bankResultMap.get("pan");
            
            return "delPage";
        }
        
        // 未开通
        return "addPage";
    }

    /**
     * 将后台返回的划扣金额编码转为金额描述
     * <功能详细描述>
     * @see [类、类#方法、类#成员]OR_huawei_201407_371 圈复杂度_自助终端 
     */
    private void qryDrawAmt()
    {
        drawAmts = (List<DictItemPO>) PublicCache.getInstance().getCachedData("HuaKouMoney");
        if (drawAmts != null && drawAmts.size() > 0 || drawAmt != null && !"".equals(drawAmt.trim()))
        {
        	DictItemPO dictitemPO = null;
        	for (int i = 0; i < drawAmts.size(); i++)
        	{
        		dictitemPO = drawAmts.get(i);
        		if (drawAmt.equals(dictitemPO.getDictid()))
        		{
        			drawAmt = dictitemPO.getDictname();
        			break;
        		}
        	}
        }
    }

    /**
     * 将后台返回的划扣类型编码转为类型描述
     * <功能详细描述>
     * @see [类、类#方法、类#成员]OR_huawei_201407_371 圈复杂度_自助终端 
     */
    private void qryDrawType()
    {
        drawTypes = (List<DictItemPO>) PublicCache.getInstance().getCachedData("HuaKouType");
        if (drawTypes != null && drawTypes.size() > 0 || drawType != null && !"".equals(drawType.trim()))
        {
        	DictItemPO dictitemPO = null;
        	for (int i = 0; i < drawTypes.size(); i++)
        	{
        		dictitemPO = drawTypes.get(i);
        		if (drawType.equals(dictitemPO.getDictid()))
        		{
        			drawType = dictitemPO.getDictname();
        			break;
        		}
        	}
        }
    }
    
    /**
     * 解除绑定关系
     * 
     * @return
     * @see
     */
    public String cancelBindInfo()
    {
        HttpServletRequest request = getRequest();
        
        HttpSession session = request.getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        // 商户ID
        String merchantID = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_MERCHANTID);
        
        // 调用银联接口解除绑定关系
        Map<String, Object> resultMap = huakouBean.cancelBindInfo(merchantID, pan, customerSimp.getSubsID());
        
        boolean delFlag = false;
        if (resultMap != null && resultMap.containsKey("delFlag"))
        {
            delFlag = (Boolean) resultMap.get("delFlag");
        }
        
        // 解除失败
        if (!delFlag)
        {
            String respDesc = "";
           
            //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
            respDesc = (String) resultMap.get("respDesc");
            //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
            
            if (respDesc == null || "".equals(respDesc.trim()))
            {
                respDesc = "取消个人银行划扣业务失败";
            }
            
            logger.error(respDesc);
            
            request.setAttribute("errormessage", respDesc);
            
            this.createRecLog(curMenuId, "0", "0", "1", respDesc);
            
            return "error";
        }
        
        // 划扣方式
        String paytype = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_CHARGETYPE);
        
        // 在CRM中取消银行划扣方式
        resultMap = huakouBean.cancelChargeType(termInfo, customerSimp, curMenuId, paytype);
        
        delFlag = false;
        if (resultMap != null && resultMap.containsKey("delFlag"))
        {
            delFlag = (Boolean) resultMap.get("delFlag");
        }
        
        // 取消失败
        if (!delFlag)
        {
            logger.error("解除绑定关系失败");
            
            request.setAttribute("errormessage", "取消个人银行划扣业务失败");
            
            this.createRecLog(curMenuId, "0", "0", "1", "取消个人银行划扣业务失败");
            
            return "error";
        }
        
        this.createRecLog(curMenuId, "0", "0", "0", "取消个人银行划扣业务成功");
        
        return "success";
    }

    /**
     * 身份证号与银行账号的认证
     * 
     * @return
     * @see
     */
    public String confirmBankInfo()
    {
        HttpServletRequest request = getRequest();

        HttpSession session = request.getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);        
    
        // 商户ID
        String merchantID = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_MERCHANTID);
        
        // 调用银联的无密实名认证接口进行认证
        Map<String, Object> resultMap = huakouBean.confirmBankInfo(merchantID, pan, IDCard, "", customerSimp.getSubsID());
        
        boolean confirmFlag = false;
        if (resultMap != null && resultMap.containsKey("confirmFlag"))
        {
            confirmFlag = (Boolean) resultMap.get("confirmFlag");
        }
        
        if (!confirmFlag)
        {
            String respDesc = "";
            
            //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
            respDesc = (String) resultMap.get("respDesc");
            //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
            
            if (respDesc == null || "".equals(respDesc.trim()))
            {
                respDesc = "银行账号信息认证失败";
            }
            
            logger.error(respDesc);
            
            request.setAttribute("errormessage", respDesc);
            
            this.createRecLog(curMenuId, "0", "0", "1", respDesc);
            
            return "error";
        }
        
        return "confirmPage";
    }
    
    /**
     * 转移划扣类型、划扣金额选择页面
     * 
     * @return
     * @see 
     */
    public String selectHuakouInfo()
    {
        // 划扣类型列表
    	drawTypes = (List<DictItemPO>) PublicCache.getInstance().getCachedData("HuaKouType");
    	
    	// 划扣金额列表
    	drawAmts = (List<DictItemPO>) PublicCache.getInstance().getCachedData("HuaKouMoney");
    	
    	return "huakouInfo";
    }
    
    /**
     * 新增银行划扣方式
     * 
     * @return
     * @see 
     */
    public String addBindInfo()
    {
        HttpServletRequest request = getRequest();
        
        HttpSession session = request.getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        // 商户ID
        String merchantID = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_MERCHANTID);
        
        // 调用银联接口建立绑定关系
        Map<String, Object> resultMap = huakouBean.addBindInfo(customerSimp, merchantID, pan, IDCard, "");
        
        boolean addFlag = false;
        if (resultMap != null && resultMap.containsKey("addFlag"))
        {
            addFlag = (Boolean) resultMap.get("addFlag");
        }
        
        // 绑定失败
        if (!addFlag)
        {
            String respDesc = "";
            
            //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
            respDesc = (String) resultMap.get("respDesc");
            //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
            
            
            if (respDesc == null || "".equals(respDesc.trim()))
            {
                respDesc = "开通个人银行划扣业务失败";
            }
            
            logger.error(respDesc);
            
            request.setAttribute("errormessage", respDesc);
            
            this.createRecLog(curMenuId, "0", "0", "1", respDesc);
            
            return "error";
        }
        
        // 划扣方式
        String paytype = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_CHARGETYPE);
        
        // 触发金额
        String triggerAmt = (String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_TRIGGERMONEY);
        
        // 在CRM中开通银行划扣方式
        resultMap = huakouBean.addChargeType(termInfo, customerSimp, curMenuId, paytype, pan, drawType, drawAmt, triggerAmt);
        
        addFlag = false;
        if (resultMap != null && resultMap.containsKey("addFlag"))
        {
            addFlag = (Boolean) resultMap.get("addFlag");
        }
        
        // 开通失败
        if (!addFlag)
        {
            logger.error("建立绑定关系失败");
            
            request.setAttribute("errormessage", "开通个人银行划扣业务失败");
            
            this.createRecLog(curMenuId, "0", "0", "1", "开通个人银行划扣业务失败");
            
            return "error";
        }
        
        this.createRecLog(curMenuId, "0", "0", "0", "开通个人银行划扣业务成功");
        
        return "success";
    }
    
    public BankHuakouBean getHuakouBean()
    {
        return huakouBean;
    }

    public void setHuakouBean(BankHuakouBean huakouBean)
    {
        this.huakouBean = huakouBean;
    }

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getDrawType()
    {
        return drawType;
    }

    public void setDrawType(String drawType)
    {
        this.drawType = drawType;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getDrawAmt()
    {
        return drawAmt;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setDrawAmt(String drawAmt)
    {
        this.drawAmt = drawAmt;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getPan()
    {
        return pan;
    }

    public void setPan(String pan)
    {
        this.pan = pan;
    }

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String card) {
		IDCard = card;
	}

	public List<DictItemPO> getDrawTypes() {
		return drawTypes;
	}

	public void setDrawTypes(List<DictItemPO> drawTypes) {
		this.drawTypes = drawTypes;
	}

	public List<DictItemPO> getDrawAmts() {
		return drawAmts;
	}

	public void setDrawAmts(List<DictItemPO> drawAmts) {
		this.drawAmts = drawAmts;
	}
}
