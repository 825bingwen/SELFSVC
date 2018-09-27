package com.customize.nx.selfsvc.chooseTel.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.nx.selfsvc.bean.ChooseTelBean;
import com.customize.sd.selfsvc.chooseTel.model.ServerNumPO;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
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
    
    private static Log logger = LogFactory.getLog(ChooseTelAction.class);
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单id
    private String curMenuId = "";
    
    // 错误信息
    private String error;
    
    // 结果列表标题
    private String[] servicetitle;
    
    // 查询结果
    private List result;
    
    // 区域编码
    private String region;
    
    // 区域名称
    private String regionname;
    
    // 单位
    private String orgid;
    
    // 查询类型
    private String sele_rule;
    
    // 号码前缀
    private String tel_prefix;
    
    // 号码后缀
    private String tel_suffix;
    
    // 单位
    private String org_id;
    
    // 证件类型
    private String certtype;
    
    // 证件名称
    private String certname;
    
    // 证件号码
    private String certid;
    
    // 客户名称
    private String name;
    
    // 联系方式
    private String contacttel;
    
    // 预约手机号吗
    private String telnum;
    
    // 终端信息
    private String termname;
    
    // 打印地点
    private String addr;
    
    // 号码页数
    private int page = 0;
    
    // 总页数
    private String pageCount;
    
    private String orderID;
    
    // 温馨提示信息
    private String additionalInfo;
    
    // 证件类型列表
    private List<DictItemPO> certTypeList;
    
    // 存放全部手机号码数据
    private List<ServerNumPO> tmpList = null;
    
    // 每页号码列表
    private List<ServerNumPO> serverNumList = null;
    
    // 调用接口bean
    private ChooseTelBean chooseTelBean;
    
    /**
     * 选号页面每页最多显示21条记录，如超过21条，需分页
     */
    private final int nMaxNum = 9;
    
    // modify begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201210_1336
    /**
     * 分页时每页显示18条记录
     */
    private final int pageSize = 9;
    // modify end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201210_1336
    
    /**
     * 分页标识。true时需分页
     */
    private String pageFlag = "false";
    
    /**
     * 短信接收号码
     */
    private String sendTelNum = "";
    
    /**
     * 选择查询规则
     * <功能详细描述>
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String selectRule()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("selectRule Starting...");
        }
        
        // 获取session
        HttpSession session = getRequest().getSession(true);
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        List<RegionInfoPO> regionListTmp = (List)PublicCache.getInstance().getCachedData(Constants.REGION_INFO);
        
        for (RegionInfoPO regionInfoPO:regionListTmp)
        {
            if (terminalInfoPO.getRegion().equals(regionInfoPO.getRegion()))
            {
            	region = regionInfoPO.getRegion();
            	regionname = regionInfoPO.getRegionname();
            	orgid = regionInfoPO.getOrgid();
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("selectRule End");
        }
        
        return "selectRule";       
    }
    
    /**
     * 选择查询规则
     * <功能详细描述>
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String inputTelNumByRule()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("inputTelNumByRule Starting...");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("inputTelNumByRule End");
        }
        
        return "inputTelNumByRule";       
    }
    
    /**
     * 查询需要预定的号码
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String telNumResult()
    {
        // 开始记录日志
        logger.debug("telNumResult start...");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        // 前缀
        // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        tel_prefix = tel_prefix == null ? "" : tel_prefix;
        // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        
        // 后缀
        // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        tel_suffix = tel_suffix == null ? "" : tel_suffix;
        // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）

        
        // 处理前缀
        if ("2".equals(sele_rule))// 查询类型 2：按前缀查询 3：按后缀查询
        {
            while (tel_prefix.length() < 7)
            {
                tel_prefix = tel_prefix + "_";
            }        
        }
        // 处理后缀
        else if("3".equals(sele_rule))
        {
            while (tel_suffix.length() < 4)
            {
                tel_suffix = tel_suffix + "_";
            }
        }
        
        // 查询号码信息列表
        Map mapResult = this.chooseTelBean.qryChooseTel(terminalInfoPO, curMenuId, orgid, sele_rule, tel_prefix, tel_suffix, region);
        
        //未查询到数据，进入错误页面
        if (mapResult == null || mapResult.get("returnObj") == null)
        {
        	// modify begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
            String errMsg = getErrMsg();
            // modify end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
            
            getRequest().setAttribute("errormessage", errMsg); 
            
            this.createRecLog(Constants.BUSITYPE_CHOOSE_TEL_NUM, "0", "0", "1", errMsg);
            
            return "error";
        }
        
        // 数据存放到临时变量中（为做内存分页准备）
        CRSet crset = (CRSet) mapResult.get("returnObj");
        
        this.getRequest().setAttribute("recordCount", crset.GetRowCount());
        
        int pageviewnum = nMaxNum;
        
        if (crset.GetRowCount() > nMaxNum)
        {
            pageFlag = "true";
            pageviewnum = pageSize;
        }
        
        tmpList = new ArrayList<ServerNumPO>();
        ServerNumPO serverNumPO = null;        
        for (int i = 0; i < crset.GetRowCount(); i++)
        {
            serverNumPO = new ServerNumPO();
            serverNumPO.setTelnum(crset.GetValue(i, 0));// 手机号码
            serverNumPO.setFee(crset.GetValue(i, 2));
            serverNumPO.setOrg_id(crset.GetValue(i, 3));
                
            tmpList.add(serverNumPO);
        }       
        
        // modify begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        page = (page == 0) ? 1 : page;
        // modify end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        
        // 结束行数
        int endRowNum = pageviewnum * page;
        
        // 开始条数
        int startRowNum = endRowNum - pageviewnum;
        
        // 总页数
        // modify begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        pageCount = "0";
        pageCount = (tmpList.size() % pageviewnum != 0) ? ((tmpList.size() / pageviewnum + 1) + "") : ((tmpList.size() / pageviewnum) + "");
        // modify end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
      
        // 从内存中取得每页数据
        serverNumList = new ArrayList<ServerNumPO>();
        for (int i = startRowNum; i < endRowNum && i < tmpList.size(); i++)
        {
            serverNumList.add((ServerNumPO)tmpList.get(i));
        }
        
        // 填冲不满一页的其它对象，防止页面变型
        if (page == Integer.parseInt(pageCount))
        {
            for (int i = 0; i < endRowNum - tmpList.size(); i++)
            {
                // 写入空对象
                ServerNumPO po = new ServerNumPO();
                po.setTelnum("");
                
                serverNumList.add(po);
            }
        }
        
        logger.debug("telNumResult End ...");

        return "telNumResult";
    }

    /**
     * 根据不同情况获取错误信息
     * @return 错误信息
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
     */
	private String getErrMsg()
	{
		String errMsg = "";
		
		if (("".equals(tel_prefix.trim()) && "".equals(tel_suffix.trim())) || "4".equals(sele_rule))
		{
		    errMsg = "未查询到符合条件的记录";
		    
		    // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
		    getRequest().setAttribute("backStep", "-2");
		    // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
		}
		else if ("2".equals(sele_rule))// 查询类型 2：按前缀查询 3：按后缀查询
		{
		    errMsg = "未查询到符合条件的记录(前缀：" + tel_prefix + ")";
		    
		    // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
		    getRequest().setAttribute("backStep", "-1");
		    // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
		}
		else if("3".equals(sele_rule))
		{
		    errMsg = "未查询到符合条件的记录(后缀：" + tel_suffix + ")";
		    
		    // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
		    getRequest().setAttribute("backStep", "-1");
		    // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
		}
		return errMsg;
	}
    
    /**
     * 选择证件类型页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String selectCertType()
    {
        // 开始记录日志
        logger.debug("selectCertType start...");
        
        // 从字典表中查询证件类型
        certTypeList = super.getDictItemByGrp("ChooseNumBycertType");
        
        if (certTypeList != null && certTypeList.size() == 1)
        {
            // 如果只支持一种证件类型的话，必须是身份证。
            DictItemPO dictItem = certTypeList.get(0);
            
            // 证件类型
            certtype = dictItem.getDictid();
            
            // 证件名称
            certname = dictItem.getDictname();
            
            // 进入输入证件号码页面
            return "inputCertid";
        }

        logger.debug("selectCertType End ...");
        
        return "selectCertType";
    }

    /**
     * 输入身份证号码页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String inputCertid()
    {
        // 开始记录日志
        logger.debug("inputCertid start...");

        logger.debug("inputCertid End ...");

        return "inputCertid";
    }
    
    /**
     * 预定号码
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String makeSureTel()
    {
        // 开始记录日志
        logger.debug("makeSureTel start...");
        
        HttpServletRequest request = this.getRequest();
        
        // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        request.setAttribute("backStep", "-1");
        // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        // 获取session
        HttpSession session = request.getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 执行预定
        Map mapResult = this.chooseTelBean.chooseTel(terminalInfoPO, curMenuId, telnum, region, org_id, certtype, certid, name, contacttel);
       
        if (mapResult != null && mapResult.size() > 1)
        {  
            CTagSet tagSet = (CTagSet) mapResult.get("returnObj");
            orderID = tagSet.GetValue("orderid");
            
            this.createRecLog(Constants.BUSITYPE_CHOOSE_TEL_NUM, "0", "0", "0", "号码预订成功");
            
            // 取菜单的温馨提示信息
            List totalMenus = (List)PublicCache.getInstance().getCachedData(terminalInfoPO.getTermgrpid());
            
            MenuInfoPO menuInfoPO = null;
            for (int i = 0; i < totalMenus.size(); i++)
            {
                menuInfoPO = (MenuInfoPO)totalMenus.get(i);
                if (this.curMenuId.equals(menuInfoPO.getMenuid()))
                {
                    break;
                }
                else
                {
                    menuInfoPO = null;
                }
            }
            
            if (menuInfoPO != null)
            {
                additionalInfo = menuInfoPO.getAdditionalInfo();
            }
            return "makeSureTel";
        }
        else
        {
            String errMsg = "";
            
            if (mapResult != null)
            {
                errMsg = (String) mapResult.get("returnMsg");
            }
            
            if (errMsg == null || "".equals(errMsg.trim()))
            {
                errMsg = "号码" + telnum + "预订失败";
            }
            
            request.setAttribute("errormessage", errMsg);            
            
            // 记录错误日志
            this.createRecLog(Constants.BUSITYPE_CHOOSE_TEL_NUM, "0", "0", "1", errMsg);
        }
        
        logger.debug("makeSureTel End ...");

        return "error";
    }
    
    /**
     * 转到输入发送手机号码页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String inputSendNum()
    {
        logger.debug("inputSendNum start...");
        
        logger.debug("inputSendNum end!");
        
        return "inputSendNum";
    }
    
    /**
     * 发送短信后转到完成页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String sendNumFinish()
    {
        logger.debug("sendNumFinish start...");
        
        // 发送短信
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        String shortMessage = "手机号码："+telnum+"已预定成功！请携带有效证件和身份证及时前往营业厅办理开户!";
        this.chooseTelBean.sendMsg(sendTelNum, terminalInfoPO, shortMessage, "");
        
        logger.debug("sendNumFinish end!");
        
        return "sendNumFinish";
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    public String[] getServicetitle()
    {
        return servicetitle;
    }

    public void setServicetitle(String[] servicetitle)
    {
        this.servicetitle = servicetitle;
    }

    public List getResult()
    {
        return result;
    }

    public void setResult(List result)
    {
        this.result = result;
    }

    public ChooseTelBean getChooseTelBean()
    {
        return chooseTelBean;
    }

    public void setChooseTelBean(ChooseTelBean chooseTelBean)
    {
        this.chooseTelBean = chooseTelBean;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getRegionname()
    {
        return regionname;
    }

    public void setRegionname(String regionname)
    {
        this.regionname = regionname;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getOrgid()
    {
        return orgid;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setOrgid(String orgid)
    {
        this.orgid = orgid;
    }

    public String getSele_rule()
    {
        return sele_rule;
    }

    public void setSele_rule(String sele_rule)
    {
        this.sele_rule = sele_rule;
    }

    public String getTel_prefix()
    {
        return tel_prefix;
    }

    public void setTel_prefix(String tel_prefix)
    {
        this.tel_prefix = tel_prefix;
    }

    public String getTel_suffix()
    {
        return tel_suffix;
    }

    public void setTel_suffix(String tel_suffix)
    {
        this.tel_suffix = tel_suffix;
    }

    public List<ServerNumPO> getServerNumList()
    {
        return serverNumList;
    }

    public void setServerNumList(List<ServerNumPO> serverNumList)
    {
        this.serverNumList = serverNumList;
    }

    public String getOrg_id()
    {
        return org_id;
    }

    public void setOrg_id(String org_id)
    {
        this.org_id = org_id;
    }

    public String getCerttype()
    {
        return certtype;
    }

    public void setCerttype(String certtype)
    {
        this.certtype = certtype;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getContacttel()
    {
        return contacttel;
    }

    public void setContacttel(String contacttel)
    {
        this.contacttel = contacttel;
    }

    public String getCertid()
    {
        return certid;
    }

    public void setCertid(String certid)
    {
        this.certid = certid;
    }

    public String getTelnum()
    {
        return telnum;
    }

    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }

    public String getTermname()
    {
        return termname;
    }

    public void setTermname(String termname)
    {
        this.termname = termname;
    }

    public String getAddr()
    {
        return addr;
    }

    public void setAddr(String addr)
    {
        this.addr = addr;
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
    public String getPageCount()
    {
        return pageCount;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPageCount(String pageCount)
    {
        this.pageCount = pageCount;
    }

    public List<ServerNumPO> getTmpList()
    {
        return tmpList;
    }

    public void setTmpList(List<ServerNumPO> tmpList)
    {
        this.tmpList = tmpList;
    }

    public String getOrderID()
    {
        return orderID;
    }

    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }
    
    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }

    public List<DictItemPO> getCertTypeList()
    {
        return certTypeList;
    }

    public void setCertTypeList(List<DictItemPO> certTypeList)
    {
        this.certTypeList = certTypeList;
    }

    public String getCertname()
    {
        return certname;
    }

    public void setCertname(String certname)
    {
        this.certname = certname;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public String getPageFlag()
    {
        return pageFlag;
    }

    public void setPageFlag(String pageFlag)
    {
        this.pageFlag = pageFlag;
    }

    public String getSendTelNum()
    {
        return sendTelNum;
    }

    public void setSendTelNum(String sendTelNum)
    {
        this.sendTelNum = sendTelNum;
    }

}
