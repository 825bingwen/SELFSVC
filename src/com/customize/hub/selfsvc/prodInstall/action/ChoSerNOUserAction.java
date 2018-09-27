package com.customize.hub.selfsvc.prodInstall.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.ChoSerNuAOUserBean;
import com.customize.hub.selfsvc.common.CommonUtilHub;
import com.customize.hub.selfsvc.prodInstall.model.IdCardVO;
import com.customize.hub.selfsvc.prodInstall.model.ShTpltTempletVO;
import com.customize.hub.selfsvc.prodInstall.model.SimVO;
import com.customize.hub.selfsvc.prodInstall.model.TelnumbeVO;
import com.customize.hub.selfsvc.prodInstall.service.ProdInstallHubService;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
/*
 * @filename: ChoSerNOUserAction.java
 * @  版权
 * @brif:  关于选号开户
 * @author: yedengchu
 * @de: （2012/07/12） 
 * @description: 
 * @remark: create bp6Xdpvh OR_HUB_201202_1192
 */

public class ChoSerNOUserAction extends BaseAction
{
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    //private static final Logger log = Logger.getLogger(ChoSerNOUserAction.class);
    private static Log log = LogFactory.getLog(ChoSerNOUserAction.class);
    // modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    
    // 当前菜单
    private String curMenuId;
    
    // 错误信息 errormessage
    private String errormessage = "";
    
    // 身份证信息
    private IdCardVO idCardVO;
    
    // 选号开户服务
    private ProdInstallHubService prodInstallHubService;
    
    // 用户选择的产品
    private ShTpltTempletVO shTpltTempletVO;
    
    // 第几页
    private int page = 1;
    
    // 每页显示条数
    private int pagesize = 6;
    
    // 总条数
    private int totalsize = 0;
    
    // 总页数
    private int pagenum = 0;
    
    // 当前页要显示的记录
    private List<ShTpltTempletVO> products = new ArrayList<ShTpltTempletVO>();
    
    // 总记录
    private List<ShTpltTempletVO> totalProducts = new ArrayList<ShTpltTempletVO>();
    
    // sim卡信息
    private SimVO simVO;
    
    // 当前页要显示的电话号码数量
    private List<TelnumbeVO> telnums = new ArrayList<TelnumbeVO>();
    
    // 总的电话号码数量
    private List<TelnumbeVO> totalTelnums = new ArrayList<TelnumbeVO>();
    
    // 客户选择的号码
    private TelnumbeVO telnumbeVO;
    
    private ChoSerNuAOUserBean choSerNuAOUserBean;
    
    // 清除以前的session缓存
    public void clearSession()
    {
        HttpServletRequest res = super.getRequest();
        HttpSession hs = res.getSession();
        hs.removeAttribute("IdCardInfo");
        hs.removeAttribute("productInfo");
        hs.removeAttribute("SimCardInfo");
        hs.removeAttribute("telnumInfo");
    }
    
    public String ruleConfirm()
    {
        clearSession();
        
        return "ruleConfirm";
    }
    
    public String getRuleInfo()
    {
        
        return "ruleInfoShow";
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String ReadIdCard()
    {
        
        return "ReadIdCard";
    }
    
    public String getCardInfo()
    {
        String forwardString = "cardInfo";
        HttpServletRequest res = super.getRequest();
        HttpSession hS = res.getSession();
        hS.setAttribute("IdCardInfo", idCardVO);
        log.info(idCardVO.getIdCardContent());
        log.info("身份证信息读取成功!");
        
        return forwardString;
    }
    
    public String cardInfoConfirm()
    {
        String forward = "chooseProduct";
        HttpServletRequest res = super.getRequest();
        HttpSession hs = res.getSession();
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)hs.getAttribute(Constants.TERMINAL_INFO);
        Map map = choSerNuAOUserBean.certCardInfo("IdCard",
                ((IdCardVO)hs.getAttribute("IdCardInfo")).getIdCardNo(),
                "1",
                terminalInfoPO,
                curMenuId);
        if (map == null || map.get("returnObj") == null || map.get("returnObj").equals("")
                || map.get("returnObj").equals("fail"))
        {
            log.debug("身份证信息信息校验失败！" + "-returnMsg-" + map.get("returnMsg"));
            errormessage = "身份证信息信息校验失败！";
            this.createRecLog(((IdCardVO)hs.getAttribute("IdCardInfo")).getIdCardNo(),
                    "cardInfoConfirm",
                    "0",
                    "0",
                    "0",
                    "身份证信息信息校验失败！");
            clearSession();
            forward = "error";
        }
        else
        {
            // Map map = new HashMap();// 测试用
            shTpltTempletVO = new ShTpltTempletVO();
            shTpltTempletVO.setStatus("1");
            // Change by Lifeng 2013-05-18 [OR_HUB_201305_410][江汉拆分]自助终端开户 begion
            if (CommonUtilHub.isSplitRegion(terminalInfoPO.getRegion()))
            {
                // 获取终端机所在的小地市
                String smallregion = CommonUtilHub.getDictNameByID(terminalInfoPO.getCityOrgid(), "SMALLREGION");
                if (null != smallregion)
                {
                    shTpltTempletVO.setRegion(smallregion);
                }
                else
                {
                    shTpltTempletVO.setRegion(terminalInfoPO.getRegion());
                }
            }
            else
            {
                shTpltTempletVO.setRegion(terminalInfoPO.getRegion());
            }
            // Change by Lifeng 2013-05-18 [OR_HUB_201305_410][江汉拆分]自助终端开户 end
            totalProducts = prodInstallHubService.queryTpltTemplet(shTpltTempletVO);
            if (totalProducts.size() > 0)
            {// 调用分页
                displayPage();
            }
            else
            {
                errormessage = "对不起,现在没有产品,暂时不能受理选号开户业务！";
                forward = "error";
                
            }
            
        }
        return forward;
        
    }
    
    // 选择产品
    public String chooseTheProduct()
    {
        HttpServletRequest res = super.getRequest();
        HttpSession hs = res.getSession();
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)hs.getAttribute(Constants.TERMINAL_INFO);
        if (shTpltTempletVO.getTempletid() != null && !shTpltTempletVO.getTempletid().equals(""))
        {
            
            hs.setAttribute("productInfo", shTpltTempletVO);
            
            // 获取sim卡信息
            
            Map map = choSerNuAOUserBean.getSimInfo(simVO.getIccid(), terminalInfoPO, curMenuId);
            if (map != null && ((CTagSet)map.get("returnObj")) != null)
            {
                CTagSet cTagSet = (CTagSet)map.get("returnObj");
                simVO.setGroupid(cTagSet.GetValue("groupid"));
                simVO.setHlrid(cTagSet.GetValue("hlrid"));
                simVO.setImsi(cTagSet.GetValue("imsi"));
                simVO.setInvid(cTagSet.GetValue("invid"));
                hs.setAttribute("SimCardInfo", simVO);
                
            }
            else
            {
                
                log.debug("choSerNuAOUserBean.getSimInfo(simVO.getIccid(), terminalInfoPO, curMenuId)");
                log.debug("获取SIM卡信息失败！");
                clearSession();
                errormessage = "获取SIM卡信息失败！";
                return "error";
                // 错误处理
            }
            
        }
        return "telnumbLoad";
    }
    
    // 选号
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String ShowTelNumbers()
    {
        HttpServletRequest res = super.getRequest();
        HttpSession hs = res.getSession();
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)hs.getAttribute(Constants.TERMINAL_INFO);
        SimVO simVO = (SimVO)hs.getAttribute("SimCardInfo");
        ShTpltTempletVO shTpltTempletVO = (ShTpltTempletVO)hs.getAttribute("productInfo");
        
        totalTelnums = (List<TelnumbeVO>)choSerNuAOUserBean.getTelnumbers(terminalInfoPO,
        		curMenuId,
                simVO,
                shTpltTempletVO.getBrand()).get("returnObj");
        if (totalTelnums == null || totalTelnums.size() == 0)
        {
            errormessage = "SIM卡" + simVO.getGroupid() + "缺号源";
            log.debug(errormessage);
            clearSession();
            return "error";
            
        }
        else
        {
            pagesize = 24;
            displayPage1();
            return "ShowTelNumbers";
        }
        
    }
    
    // 号码暂选
    public String chooseTelNum()
    {
        
        HttpServletRequest res = super.getRequest();
        HttpSession hs = res.getSession();
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)hs.getAttribute(Constants.TERMINAL_INFO);
        if (telnumbeVO.getTelnum() != null && !telnumbeVO.getTelnum().equals(""))
        {
            
            hs.setAttribute("telnumInfo", telnumbeVO);
            // 调用接口
            Map map = choSerNuAOUserBean.chooseTheTelNum(telnumbeVO.getTelnum(), terminalInfoPO, curMenuId);
            if (map.get("returnObj") == null || (map.get("returnObj").equals("fail")))
            {
                hs.removeAttribute("telnumInfo");
                return "reChooseTelnum";
                
            }
            else
            {
                
                // 跳到徐向力哪里去
                return "feeConfirm";
            }
            
        }
        else
        {
            errormessage = "没有选择号码,不能受理选号开户业务!";
            clearSession();
            return "error";
        }
        
    }
    
    // 同意错误处理
    public String handleError()
    {
        clearSession();
        // URLDecoder ud=new URLDecoder();
        //
        // try
        // {
        // errormessage = ud.decode(this.errormessage,"UTF-8");
        // }
        // catch (UnsupportedEncodingException e)
        // {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        log.debug(errormessage);
        return "error";
    }
    
    // 产品分页
    private void displayPage()
    {
        if (page == 0)
        {
            page = 1;
        }
        
        // 清空数据
        products.clear();
        
        // 计算总条数
        totalsize = totalProducts.size();
        
        // 计算总页数
        if (totalsize % pagesize > 0)
        {
            pagenum = totalsize / pagesize + 1;
        }
        else
        {
            pagenum = totalsize / pagesize;
        }
        
        // 开始条数
        int startNum = pagesize * (page - 1) + 1;
        
        // 结束条数
        int endNum = pagesize * page;
        
        // 开始条数
        for (int i = startNum; i <= endNum; i++)
        {
            if (i <= totalsize)
            {
                this.products.add(totalProducts.get(i - 1));
            }
        }
        
        if (this.products.size() < pagesize)
        {
            int size = products.size();
            ShTpltTempletVO shTpltTempletVO = new ShTpltTempletVO();
            for (int i = size; i < pagesize; i++)
            {
                
                products.add(shTpltTempletVO);
            }
        }
    }
    
    // 号码分页
    private void displayPage1()
    {
        if (page == 0)
        {
            page = 1;
        }
        
        // 清空数据
        telnums.clear();
        
        // 计算总条数
        totalsize = totalTelnums.size();
        
        // 计算总页数
        if (totalsize % pagesize > 0)
        {
            pagenum = totalsize / pagesize + 1;
        }
        else
        {
            pagenum = totalsize / pagesize;
        }
        
        // 开始条数
        int startNum = pagesize * (page - 1) + 1;
        
        // 结束条数
        int endNum = pagesize * page;
        
        // 开始条数
        for (int i = startNum; i <= endNum; i++)
        {
            if (i <= totalsize)
            {
                this.telnums.add(totalTelnums.get(i - 1));
            }
        }
        
        if (this.telnums.size() < pagesize)
        {
            int size = telnums.size();
            TelnumbeVO telnumbeVO = new TelnumbeVO();
            for (int i = size; i < pagesize; i++)
            {
                
                telnums.add(telnumbeVO);
            }
        }
    }
    
    public ChoSerNuAOUserBean getChoSerNuAOUserBean()
    {
        return choSerNuAOUserBean;
    }
    
    public void setChoSerNuAOUserBean(ChoSerNuAOUserBean choSerNuAOUserBean)
    {
        this.choSerNuAOUserBean = choSerNuAOUserBean;
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
    
    public IdCardVO getIdCardVO()
    {
        return idCardVO;
    }
    
    public void setIdCardVO(IdCardVO idCardVO)
    {
        this.idCardVO = idCardVO;
    }
    
    public List<ShTpltTempletVO> getProducts()
    {
        return products;
    }
    
    public void setProducts(List<ShTpltTempletVO> products)
    {
        this.products = products;
    }
    
    public ProdInstallHubService getProdInstallHubService()
    {
        return prodInstallHubService;
    }
    
    public void setProdInstallHubService(ProdInstallHubService prodInstallHubService)
    {
        this.prodInstallHubService = prodInstallHubService;
    }
    
    public ShTpltTempletVO getShTpltTempletVO()
    {
        return shTpltTempletVO;
    }
    
    public void setShTpltTempletVO(ShTpltTempletVO shTpltTempletVO)
    {
        this.shTpltTempletVO = shTpltTempletVO;
    }
    
    public int getPage()
    {
        return page;
    }
    
    public void setPage(int page)
    {
        this.page = page;
    }
    
    public int getPagesize()
    {
        return pagesize;
    }
    
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
    
    public List<ShTpltTempletVO> getTotalProducts()
    {
        return totalProducts;
    }
    
    public void setTotalProducts(List<ShTpltTempletVO> totalProducts)
    {
        this.totalProducts = totalProducts;
    }
    
    public SimVO getSimVO()
    {
        return simVO;
    }
    
    public void setSimVO(SimVO simVO)
    {
        this.simVO = simVO;
    }
    
    public List<TelnumbeVO> getTelnums()
    {
        return telnums;
    }
    
    public void setTelnums(List<TelnumbeVO> telnums)
    {
        this.telnums = telnums;
    }
    
    public List<TelnumbeVO> getTotalTelnums()
    {
        return totalTelnums;
    }
    
    public void setTotalTelnums(List<TelnumbeVO> totalTelnums)
    {
        this.totalTelnums = totalTelnums;
    }
    
    public TelnumbeVO getTelnumbeVO()
    {
        return telnumbeVO;
    }
    
    public void setTelnumbeVO(TelnumbeVO telnumbeVO)
    {
        this.telnumbeVO = telnumbeVO;
    }
    
}
