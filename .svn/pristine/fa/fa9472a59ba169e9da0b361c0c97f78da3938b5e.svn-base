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
package com.customize.hub.selfsvc.telProdDiy.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.TelProdDiyBean;
import com.customize.hub.selfsvc.telProdDiy.model.TelProdPO;
import com.customize.hub.selfsvc.telProdDiy.service.TelProdDiyService;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class TelProdDiyAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    // modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    //private static final Logger log = Logger.getLogger(TelProdDiyAction.class);
    private static Log log = LogFactory.getLog(TelProdDiyAction.class);
    // modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    // 错误信息
    private String errormessage = "";
    
    // 成功提示信息
    private String successMessage = "";
    
    // 查询service
    private TelProdDiyService telProdDiyService;
    
    // 接口bean
    private TelProdDiyBean telProdDiyBean;
    
    // 可选业务列表
    private List<TelProdPO> telProdList1 = new ArrayList<TelProdPO>();
    
    // 可选业务列表
    private List<TelProdPO> telProdList2 = new ArrayList<TelProdPO>();
    
    // 当前菜单
    private String curMenuId = "";
    
    // 查询类型 MAINPROD, 语音通话; GPRSWLAN, 上网流量；SP, 数据业务
    private String qryType = "MAINPROD";
    
    // 页数
    private int pageCount;
    
    // 每页显示容量
    private int pageSize;
    
    // 第几页
    private int page = 0;
    
    // nocode(语音通话)
    private String ncode = "";
    
    // 开通增值产品串(产品包,增值产品,优惠;产品包,增值产品,优惠;)
    private String productset = "";
    
    // 数据业务（spid,spbizid;spid,spbizid;）
    private String spStr = "";
    
    // 右侧滚动div内容
    private String rightDivHtml = "";
    
    // 总费用
    private String totalHidden = "0.00";
    
    List<TelProdPO> telProdList = new ArrayList<TelProdPO>();

            
    /**
     * 根据用户的归属地区、品牌查询语音业务列表
     * 
     * @return
     * @see
     */
    public String qryTelProdList()
    {
        String forward = "qryTelProdList";
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 非神州行用户，不允许办理自选套餐
        if (!(customer.getBrandID()).equals("BrandSzx"))
        {
            setErrormessage("您不是神州行用户，不能办理自选套餐业务");
            forward = "error";
            
            return forward;
        }
        
        TelProdPO telProdPO = new TelProdPO();
        telProdPO.setBelongBrands(customer.getBrandID());
        //telProdPO.setBelong_region(customer.getRegionID());

        if ("".equals(customer.getSmallregion()) || null == customer.getSmallregion())
        {
            telProdPO.setBelong_region(customer.getRegionID());
        }
        else
        {
            telProdPO.setBelong_region(customer.getSmallregion());
        }
        telProdPO.setQryType(qryType);
        
        telProdList = telProdDiyService.qryTelProdList(telProdPO);
        
//        if (null != telProdList && 0 < telProdList.size())
//        {
//            forward = "qryTelProdList";
//        }
//        else
//        {
//            setErrormessage("暂时没有您可选的套餐");
//            forward = "error";
//        }
       
        this.getRequest().setAttribute("recordCount", telProdList.size());
        
        if("SP".equals(qryType))
        {
            pageSize = 10;
        }else
        {
            pageSize = 7;
        }
        
        telProdList = this.getPageList(telProdList);
        
        for(TelProdPO po:telProdList)
        {
            if(po.getGoods_type().equals("1") || po.getGoods_type().equals("3")  || po.getGoods_type().equals("5"))
            {
                telProdList1.add(po);
            }
            else
            {
                telProdList2.add(po);

            }
        }
        
        this.getRequest().setAttribute("telProdFlag", "1");

        return forward;
    }
    
    /**
     * 受理提交
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String recSubmit()
    {
        String forward = "error";
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 调用语音通话受理接口
        ReturnWrap result = telProdDiyBean.mainProductChangeSubmit(terminalInfoPO, customer, curMenuId, ncode);
        
        // 语音通话受理成功
        // begin zKF66389 2015-09-15 9月份findbugs修改
        if (result.getStatus() == SSReturnCode.SUCCESS)
        {
            this.createRecLog("SH_mainProductRec", "0", "0", "0", "语音通话：" + ncode + "受理成功！");
            
            // 上网流量和数据业务受理失败信息
            String errorMessage = "";
            
            // 选择了上网流量
            //if (null != productset && !"".equals(productset))
            if (isNotEmpty(productset))
            {
                // 调用上网流量受理接口
                ReturnWrap wlanGprsResult = telProdDiyBean.gprsWlanRec(terminalInfoPO, customer, curMenuId, productset);
                // begin zKF66389 2015-09-15 9月份findbugs修改
                if (wlanGprsResult.getStatus() == SSReturnCode.SUCCESS)
                {
                    this.createRecLog("SH_wlanGprsRec", "0", "0", "0", "上网流量：" + productset + "受理成功！");
                }
                else
                {
                    String resultMsg = wlanGprsResult.getReturnMsg();
                    
                    resultMsg = super.getConvertMsg(resultMsg,
                            "zz_04_24_000004",
                            String.valueOf(wlanGprsResult.getErrcode()),
                            null);
                    
                    this.createRecLog("SH_wlanGprsRec", "0", "0", "1", resultMsg);
                    
                    errorMessage = errorMessage + resultMsg;
                }
            }
            
            // 选择了数据业务
            //if (null != spStr && !"".equals(spStr))
            if (isNotEmpty(spStr))
            {
                String[] spArray = spStr.split(";");
                StringBuffer sbuf = new StringBuffer(errorMessage);
                for (int i = 0; i < spArray.length; i++)
                {
                    
                    String spid = spArray[i].split(",")[0];
                    
                    String spbizid = spArray[i].split(",")[1];
                    
                    // 调用数据业务受理接口
                    ReturnWrap spResult = telProdDiyBean.spRec(terminalInfoPO, customer, curMenuId, spbizid, spid);
                    // begin zKF66389 2015-09-15 9月份findbugs修改
                    if (spResult.getStatus() == SSReturnCode.SUCCESS)
                    {
                        this.createRecLog("SH_spRec", "0", "0", "0", "数据业务(sp编码，sp业务编码)：" + spArray[i] + "受理成功！");
                    }
                    else
                    {
                        String resultMsg = spResult.getReturnMsg();
                        
                        resultMsg = super.getConvertMsg(resultMsg,
                                "zz_04_24_000003",
                                String.valueOf(spResult.getErrcode()),
                                null);
                        
                        this.createRecLog("SH_spRec", "0", "0", "1", resultMsg);
                        
                        sbuf.append(",").append(resultMsg);
                        
                    }
                }
                errorMessage = sbuf.toString();
            }
            
            if ("".equals(errorMessage))
            {
                setSuccessMessage("自选套餐受理成功！");
                
                forward = "success";
            }
            else
            {
                setErrormessage(errorMessage);
                
                forward = "error";
            }
        }
        else
        {
            String resultMsg = result.getReturnMsg();
            
            resultMsg = super.getConvertMsg(resultMsg, "zz_04_24_000001", String.valueOf(result.getErrcode()), null);
            
            this.createRecLog("SH_mainProductRec", "0", "0", "1", resultMsg);
            
            setErrormessage(resultMsg);
            
            forward = "error";
        }
        
        return forward;
    }
    
    /**
     * 判断是否不用空
     * <功能详细描述>
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean isNotEmpty(String str)
    {
        return null != str && !"".equals(str);
    }
    
    /**
     * 分页
     * 
     * @param list
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<TelProdPO> getPageList(List<TelProdPO> list)
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
        
        List<TelProdPO> telProdList = new ArrayList<TelProdPO>();
        
        for (int i = start; i < end; i++)
        {
            telProdList.add(list.get(i));
        }
        return telProdList;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public TelProdDiyService getTelProdDiyService()
    {
        return telProdDiyService;
    }
    
    public String getNcode()
    {
        return ncode;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public void setTelProdDiyService(TelProdDiyService telProdDiyService)
    {
        this.telProdDiyService = telProdDiyService;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }
    
    public TelProdDiyBean getTelProdDiyBean()
    {
        return telProdDiyBean;
    }
    
    public void setTelProdDiyBean(TelProdDiyBean telProdDiyBean)
    {
        this.telProdDiyBean = telProdDiyBean;
    }
    
    public String getQryType()
    {
        return qryType;
    }
    
    public void setQryType(String qryType)
    {
        this.qryType = qryType;
    }
    
    public int getPage()
    {
        return page;
    }
    
    public int getPageCount()
    {
        return pageCount;
    }
    
    public int getPageSize()
    {
        return pageSize;
    }
    
    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }
    
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public void setPage(int page)
    {
        this.page = page;
    }
    
    public String getProductset()
    {
        return productset;
    }
    
    public String getSpStr()
    {
        return spStr;
    }
    
    public void setProductset(String productset)
    {
        this.productset = productset;
    }
    
    public void setSpStr(String spStr)
    {
        this.spStr = spStr;
    }
    
    public String getSuccessMessage()
    {
        return successMessage;
    }
    
    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }

    public List<TelProdPO> getTelProdList1()
    {
        return telProdList1;
    }

    public List<TelProdPO> getTelProdList2()
    {
        return telProdList2;
    }

    public void setTelProdList1(List<TelProdPO> telProdList1)
    {
        this.telProdList1 = telProdList1;
    }

    public void setTelProdList2(List<TelProdPO> telProdList2)
    {
        this.telProdList2 = telProdList2;
    }

    public String getRightDivHtml()
    {
        return rightDivHtml;
    }

    public void setRightDivHtml(String rightDivHtml)
    {
        this.rightDivHtml = rightDivHtml;
    }

    public String getTotalHidden()
    {
        return totalHidden;
    }

    public void setTotalHidden(String totalHidden)
    {
        this.totalHidden = totalHidden;
    }

    public List<TelProdPO> getTelProdList()
    {
        return telProdList;
    }

    public void setTelProdList(List<TelProdPO> telProdList)
    {
        this.telProdList = telProdList;
    }
    
}
