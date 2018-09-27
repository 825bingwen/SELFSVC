package com.gmcc.boss.selfsvc.baseService.familymem.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.baseService.familymem.model.FamilyMemPO;
import com.gmcc.boss.selfsvc.bean.AddFamilyMemBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class AddFamilyMemAction extends BaseAction
{
	/**
     * 日志
     */
    private static Log logger = LogFactory.getLog(AddFamilyMemAction.class);
      
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单
    private String curMenuId;
    
    // 用户家庭网成员结果集
    private List<FamilyMemPO> familyMemList = new ArrayList<FamilyMemPO>();
    
    // 家庭网成员PO
    private FamilyMemPO familyMemPO;
    
    // 可选短号
    private String shortNum;
    
    // 错误信息
    private String errormessage;
    
    // 删除家庭网成功提示信息
    private String successMessage;
    
    // add begin hWX5316476 2014-12-30 OR_SD_201412_777_SD_自助终端放开家庭网成员删除的功能
    /**
     * 家庭网成员手机号
     */ 
    private String memTelnum;
    // add end hWX5316476 2014-12-30 OR_SD_201412_777_SD_自助终端放开家庭网成员删除的功能
    
    /**
     * 家庭网添加成员bean
     */
    private transient AddFamilyMemBean addFamilyMemBean;
    
    /**
     * 检查是否是家庭网主号并且查询家庭网成员
     * 
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark create kWX211786 May 24, 2014 版本号 需求/BUG编号：OR_huawei_201404_1115_山东_家庭网成员添加功能
     */
    @SuppressWarnings("unchecked")
    public String checkTelNumAndQryFamilyMem()
    {
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        return queryFamilyMem(terminalInfoPO, customer, curMenuId);
    }
    
    /**
     * 初始化家庭网成员添加
     * 
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark create kWX211786 May 24, 2014 版本号 需求/BUG编号：OR_huawei_201404_1115_山东_家庭网成员添加功能
     */
    public String initAddFamilyMem()
    {
        return "initAddFamilyMemSuccess";
    }
    
    /**
     * 添加家庭网成员
     * 
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark create kWX211786 May 24, 2014 版本号 需求/BUG编号：OR_huawei_201404_1115_山东_家庭网成员添加功能
     */
    public String addFamilyMem()
    {
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        Map<String, Object> result = addFamilyMemBean.addFamilyMem(terminalInfoPO, customer, curMenuId, familyMemPO);
        
        if (result != null && "1".equals(result.get("result")))
        {
            return SUCCESS;
        }
        else if (result != null)
        {
            // 设置错误信息
            setErrormessage((String)result.get("returnMsg"));
            
            // 创建错误日志
            this.createRecLog(curMenuId, "0", "0", "1", (String)result.get("returnMsg"));
            
            return ERROR;
        }
        // 设置错误信息
        setErrormessage("添加家庭网成员失败！");
        
        // 创建错误日志
        this.createRecLog(curMenuId, "0", "0", "1", "添加家庭网成员失败！");
        
        return ERROR;
    }
    
    /**
     * 查询家庭网成员
     * 
     * @param terminalInfoPO 终端机信息
     * @param customer 主号信息
     * @param curMenuId 菜单
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark create kWX211786 May 24, 2014 版本号 需求/BUG编号：OR_huawei_201404_1115_山东_家庭网成员添加功能
     */
    @SuppressWarnings("unchecked")
    private String queryFamilyMem(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId)
    {
        
        // 查询数据库中配置的短号
        shortNum = (String)PublicCache.getInstance().getCachedData("FamilyMemShortNumMenu");
        
        Map<String, Object> result = addFamilyMemBean.queryFamilyMem(terminalInfoPO, customer, curMenuId);
        if (result != null && result.size() > 1)
        {
            CRSet crset = (CRSet)result.get("returnObj");
            
            if (crset != null && crset.GetRowCount() > 0)
            {
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    // 家庭网成员PO
                    FamilyMemPO familyMemPO = new FamilyMemPO();
                    
                    // 成员电话
                    familyMemPO.setTelNum(crset.GetValue(i, 0));
                    
                    // 成员名称
                    familyMemPO.setName(crset.GetValue(i, 1));
                    
                    // 成员短号
                    familyMemPO.setShortNum(crset.GetValue(i, 2));
                    
                    // 成员加入时间
                    familyMemPO.setAddDate(crset.GetValue(i, 3));
                    
                    // 成员是否是主号
                    familyMemPO.setIsHost(crset.GetValue(i, 4));
                    
                    familyMemList.add(familyMemPO);
                    
                    // 过滤已用短号
                    shortNum = shortNum.replace(crset.GetValue(i, 2) + ",", "")
                            .replace("," + crset.GetValue(i, 2), "")
                            .replace(crset.GetValue(i, 2), "");
                }
                return "queryFamilyMemSuccess";
            }
        }
        else if (result != null)
        {
            // 设置错误信息
            setErrormessage((String)result.get("returnMsg"));
            
            // 创建错误日志
            this.createRecLog(curMenuId, "0", "0", "1", (String)result.get("returnMsg"));
            
            return ERROR;
        }
        // 设置错误信息
        setErrormessage("查询家庭网成员信息失败！");
        
        // 创建错误日志
        this.createRecLog(curMenuId, "0", "0", "1", "查询家庭网成员信息失败！");
        
        return ERROR;
        
    }
    
    /**
     * 删除家庭网
     * 
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @remark add by wWX217192 on 20140604 for OR_huawei_201405_875 营业厅全业务流程优化-业务分流(自助终端)_家庭网取消功能 
     */
    public String deleteFamilyMem()
    {
    	if(logger.isDebugEnabled())
    	{
    		logger.debug("qryBillCycle Starting ...");
    	}
    	
    	String forward = "error";
    	
    	// 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        ReturnWrap rw = addFamilyMemBean.deleteFamilyMen(terminalInfoPO, customer, curMenuId);
        
        if(rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	// 删除家庭网成功的提示信息
        	setSuccessMessage("删除家庭网成功!");
        	forward = "success";
        }
        else if(rw != null && rw.getStatus() == SSReturnCode.ERROR)
        {
        	getRequest().setAttribute("errormessage", rw.getReturnMsg());
        } 
        else
        {
        	getRequest().setAttribute("errormessage", "家庭网删除失败!");
        }
        
    	return forward;
    }
    
    /**
     * 根据手机号删除家庭网成员
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-2-4 10:39:43 OR_SD_201412_777 自助终端放开家庭网成员删除的功能 
     */
    public String delMemByTelNum()
    {
    	String forward = ERROR;
    	
        try
        {
            // 调用接口删除家庭网成员
            addFamilyMemBean.delMemByTelNum(getTerminalInfoPO(), getCustomerSimp(), curMenuId, memTelnum);
            
            // 删除家庭网成功的提示信息
            setSuccessMessage("家庭网成员"+memTelnum+"删除成功！");
            
            // 创建成功日志
            this.createRecLog(curMenuId, "0", "0", "0", "家庭网成员："+memTelnum+"删除成功！");
            
            forward = checkTelNumAndQryFamilyMem();
        }
        catch (ReceptionException e)
        {
            getRequest().setAttribute("errormessage", e.getMessage());
            
            // 创建错误日志
            this.createRecLog(curMenuId, "0", "0", "1", e.getMessage());
        }
        
        return forward;
        
    }
    
    public AddFamilyMemBean getAddFamilyMemBean()
    {
        return addFamilyMemBean;
    }
    
    public void setAddFamilyMemBean(AddFamilyMemBean addFamilyMemBean)
    {
        this.addFamilyMemBean = addFamilyMemBean;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public List<FamilyMemPO> getFamilyMemList()
    {
        return familyMemList;
    }
    
    public void setFamilyMemList(List<FamilyMemPO> familyMemList)
    {
        this.familyMemList = familyMemList;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public FamilyMemPO getFamilyMemPO()
    {
        return familyMemPO;
    }
    
    public void setFamilyMemPO(FamilyMemPO familyMemPO)
    {
        this.familyMemPO = familyMemPO;
    }
    
    public String getShortNum()
    {
        return shortNum;
    }
    
    public void setShortNum(String shortNum)
    {
        this.shortNum = shortNum;
    }

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

    public String getMemTelnum()
    {
        return memTelnum;
    }

    public void setMemTelnum(String memTelnum)
    {
        this.memTelnum = memTelnum;
    }
    
}
