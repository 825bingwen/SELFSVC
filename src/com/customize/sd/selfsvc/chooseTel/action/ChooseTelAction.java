package com.customize.sd.selfsvc.chooseTel.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.sd.selfsvc.bean.ChooseTelBean;
import com.customize.sd.selfsvc.chooseTel.model.ServerNumPO;
import com.customize.sd.selfsvc.chooseTel.service.ChooseTelService;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.PagedAction;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.resdata.model.RegionInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

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
public class ChooseTelAction extends PagedAction
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
    
    private String orderID;
    
    //add begin cKF48754 2011/10/25 L10 增加温馨提示信息
    // 温馨提示信息
    private String additionalInfo;
    //add end cKF48754 2011/10/25 L10 增加温馨提示信息
    
    // 存放全部手机号码数据
    private List<ServerNumPO> tmpList = null;
    
    // 每页号码列表
    private List<ServerNumPO> serverNumList = null;
    
    // 调用接口bean
    private ChooseTelBean chooseTelBean;
    
    // 调用数据库Service
    private ChooseTelService chooseTelService;
    
    /**
     * 分页时每页显示15条记录
     */
    private final int pageSize = 15;
    
    /**
     * 分页标识。true时需分页
     */
    private String pageFlag = "false";
    
    /**
     * 短信接收号码
     */
    private String sendTelNum = "";
    
    /**
     * 预存款
     */
    private String preFee = "";
    
    /**
     * 选号接口切换至webservice接口的开关
     * webservice接口的开关 1：开启， 0：关闭
     */
    private String webserviceFlag = (String) PublicCache.getInstance().getCachedData("SH_WEBSERVICE_CHOOSETEL_SWITCH");
    
    /**
     * 自定义号码时的条件
     */
    private String condition = "";
    
    /**
     * 虚拟产品编码 vprodId
     * 商城侧生成的虚拟产品编码，进行号码预约时，需要将此编码传递给商城
     */
    private String vprodId = "";
    
    /**
     * 
     */
    private String manuTelnumFlag = "";
    
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
        
        // 选号接口切换至webservice接口的开关
        webserviceFlag = (String) PublicCache.getInstance().getCachedData("SH_WEBSERVICE_CHOOSETEL_SWITCH");
        
        manuTelnumFlag = (String) PublicCache.getInstance().getCachedData("SH_WEBSERVICE_QRYMANUNUM_FLAG");
        
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
        logger.debug("inputTelNumByRule Starting...");
        
        webserviceFlag = (String) PublicCache.getInstance().getCachedData("SH_WEBSERVICE_CHOOSETEL_SWITCH");
        
        logger.debug("inputTelNumByRule End");
        
        if("4".equals(sele_rule))
        {
        	return "manuNumber";
        }
        
        return "inputTelNumByRule";       
    }
    
    /**
     * 调用webservice接口查询号码
     * @return
     * @remark create by wWX217192 2015-03-12 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
     */
    public String queryNum()
    {
    	// 打印日志
    	logger.debug("queryNum start...");
    	
    	String responseMsg = "";
    	
    	if (tel_prefix == null)
        {
            tel_prefix = "";
        }
        
        if (tel_suffix == null)
        {
            tel_suffix = "";
        }
        
        // 号码前缀
        while (tel_prefix.length() < 7)
        {
            tel_prefix = tel_prefix + "*";
        }
    	
        while (tel_suffix.length() < 4)
        {
            tel_suffix = tel_suffix + "*";
        }
        
        responseMsg = chooseTelBean.getQueryNumReq(region, preFee, tel_prefix, 
        		tel_suffix, CommonUtil.getParamValue(Constants.SH_CHOOSETEL_QUERYNUM_ROWNUM), condition);
        
        // 错误信息
        String errorMsg = "";
        
        try 
        {
			
        	List<ServerNumPO> list = new ArrayList<ServerNumPO>();
        	
        	tmpList = new ArrayList<ServerNumPO>();
			
			responseMsg = getResponseMsg(responseMsg);
			
			Document body = DocumentHelper.parseText(responseMsg);
    		
    		// 获取返回报文的return根节点
    		Element root = body.getRootElement();
    		
    		// 成功返回100，失败返回负值
    		if(Constants.SH_CHOOSETEL_SUCCESSCODE.equals(root.element("returnCode").getText()))
    		{
    			// 解析查询号码webservice接口的返回报文信息
    			list = parseQueryNumResMsg(root);
    			
    			tmpList = getLimitFee(list);
    		}
    		else
    		{
    			// 获取错误信息
    			errorMsg = root.element("errorMsg").getText();
    			getRequest().setAttribute("errormessage", errorMsg);
    			return ERROR;
    		}
			
			this.getRequest().setAttribute("recordCount", tmpList.size());
			
			// 调用分页方法显示
			serverNumList = this.getPageList(tmpList, pageSize);
			
			return SUCCESS;
			
		}
        catch (Exception e) 
		{
			logger.info("号码查询失败！", e);
			getRequest().setAttribute("errormessage", "号码查询失败！");
			return ERROR;
		}
    }
    
    // 控制最低消费和预存款的值
    private List<ServerNumPO> getLimitFee(List<ServerNumPO> list)
    {
    	tmpList = new ArrayList<ServerNumPO>();
    	
    	String lowFeeLimit = (String)PublicCache.getInstance().getCachedData("SH_LOWFEE_LIMIT");
        
        String lowPreLimit = (String)PublicCache.getInstance().getCachedData("SH_LOWPRE_LIMIT");

        ServerNumPO serverNumPO = null;
        
        for (int i = 0; i < list.size(); i++)
        {
            // 展示号码限制最低消费最大值
            if (null != lowFeeLimit)
            {
                if (!"".equals(list.get(i).getLow_consum_fee()) && !"".equals(list.get(i).getLow_consum_pre())
                        && (Double.valueOf(list.get(i).getLow_consum_fee()) <= Double.valueOf(lowFeeLimit))
                        && (Double.valueOf(list.get(i).getLow_consum_pre()) >= Double.valueOf(lowPreLimit))
                        )
                {
                    serverNumPO = new ServerNumPO();
                    serverNumPO.setTelnum(list.get(i).getTelnum());// 手机号码
                    
                    serverNumPO.setLow_consum_fee(list.get(i).getLow_consum_fee());
                    serverNumPO.setLow_consum_pre(list.get(i).getLow_consum_pre());
                    serverNumPO.setLow_inservice_time(list.get(i).getLow_inservice_time());
                    
                    tmpList.add(serverNumPO);
                }
            }
            else
            {                    
                if(!"".equals(list.get(i).getLow_consum_fee()) && !"".equals(list.get(i).getLow_consum_pre()))
                {
                	serverNumPO = new ServerNumPO();
                    serverNumPO.setTelnum(list.get(i).getTelnum());// 手机号码
                    
                    serverNumPO.setLow_consum_fee(list.get(i).getLow_consum_fee());
                    serverNumPO.setLow_consum_pre(list.get(i).getLow_consum_pre());
                    serverNumPO.setLow_inservice_time(list.get(i).getLow_inservice_time());
                    
                    tmpList.add(serverNumPO);
                }
            }
        }
        
        return tmpList;
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
        
        getRequest().setAttribute("backStep", "-1");
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        this.linkTelNum();
        
        // 查询号码信息列表
        Map mapResult = this.chooseTelBean.qryChooseTel(terminalInfoPO, curMenuId, orgid, sele_rule, tel_prefix, tel_suffix, region);
        
        //未查询到数据，进入错误页面
        if (mapResult == null || mapResult.get("returnObj") == null)
        {
            String errMsg = "";
            
            if ("".equals(tel_prefix.trim()) && "".equals(tel_suffix.trim()))
            {
                errMsg = "未查询到符合条件的记录";
            }
            else if ("2".equals(sele_rule))// 查询类型 2：按前缀查询 3：按后缀查询
            {
                errMsg = "未查询到符合条件的记录(前缀：" + tel_prefix + ")";               
            }
            else if("3".equals(sele_rule))
            {
                errMsg = "未查询到符合条件的记录(后缀：" + tel_suffix + ")";
            }
            
            getRequest().setAttribute("errormessage", errMsg); 
            
            this.createRecLog(Constants.BUSITYPE_CHOOSE_TEL_NUM, "0", "0", "1", errMsg);
            
            return "error";
        }
        
        // 数据存放到临时变量中（为做内存分页准备）
        CRSet crset = (CRSet) mapResult.get("returnObj");
        
        this.getRequest().setAttribute("recordCount", crset.GetRowCount());
        
        tmpList = new ArrayList<ServerNumPO>();
        ServerNumPO serverNumPO = null;  
        
        // modify begin cKF76106 2013/01/23 R003C12L12n01 OR_SD_201301_279
        // OR_SD_201301_296 上线标志
        String upFlag = (String)PublicCache.getInstance().getCachedData("SH_TELCHOOSE_UPFLAG");
        
        if("1".equals(upFlag))
        {
        	limitLowFee(crset);
        }
        else
        {
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                serverNumPO = new ServerNumPO();
                serverNumPO.setTelnum(crset.GetValue(i, 0));// 手机号码
                serverNumPO.setFee(crset.GetValue(i, 2));
                serverNumPO.setOrg_id(crset.GetValue(i, 3));
                
                //add by fKF59607 OR_SD_201203_1057 选号问题  2012-05-10 begin
                serverNumPO.setTellevel(crset.GetValue(i, 5));
                //add by fKF59607 OR_SD_201203_1057 选号问题  2012-05-10 end
                
                tmpList.add(serverNumPO);
            }       
        }
        
        serverNumList = this.getPageList(tmpList, pageSize);
        
        logger.debug("telNumResult End ...");
        
        String forward = "telNumResult";

        if("1".equals(upFlag))
        {
            forward = "telNumResultNew";
        }
        return forward;
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
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // add begin hWX5316476 2014-3-1 OR_SD_201312_300 电子渠道号码预约防欺诈措施优化
        // 从数据库中获取trunc(sysdate+4)~trunc(sysdate+5)之间的一个随机时间
        String enddate = chooseTelService.qryEnddate().substring(0,10).replaceAll("-","");
        
        Random r=new Random();
        StringBuffer b = new StringBuffer();
        
        // 0(包含)-24(不包含)之间的随机数
		int h = r.nextInt(24);
		
		// 0(包含)-60(不包含)之间的随机数
		int m = r.nextInt(60);

		String hh = "";
		String mm = "";
		if(h < 10)
		{
			hh = "0"+h;
		}
		else
		{
			hh = ""+h;
		}
		
		if(m < 10)
		{
			mm = "0"+m;
		}
		else
		{
			mm = ""+m;
		}
		
		StringBuffer ramdomTime = b.append(hh).append(mm);
       
		// 获取预约到期释放时间，格式参考：201401141555，表示2014年1月14日15点55分到期释放，精确到分
        String enddateRandom = enddate.concat(ramdomTime.toString());
        // add end hWX5316476 2014-3-1 OR_SD_201312_300 电子渠道号码预约防欺诈措施优化

        // 执行预定
        Map mapResult = this.chooseTelBean.chooseTel(terminalInfoPO, curMenuId, telnum, region, org_id, certtype, certid, name, contacttel,enddateRandom);
       
        if (mapResult != null && mapResult.size() > 1)
        {  
            CTagSet tagSet = (CTagSet) mapResult.get("returnObj");
            orderID = tagSet.GetValue("orderid");
            
            this.createRecLog(Constants.BUSITYPE_CHOOSE_TEL_NUM, "0", "0", "0", "号码预订成功");
            
            //add begin cKF48754 2011/10/25 L10 增加温馨提示信息
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
            //add end cKF48754 2011/10/25 L10 增加温馨提示信息
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
            
            getRequest().setAttribute("errormessage", errMsg);
            
            getRequest().setAttribute("backStep", "-1");
            
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
    
    /**
     * 暂选号码 新增加流程 
     * 原流程为：查询号码->预约号码
     * 改为
     * 现流程为：查询号码->暂选号码->预约号码
     * @return
     * @remark create by wWX217192 2015-03-13 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
     */
    public String pickNum() throws Exception
    {
    	logger.debug("pickNum start...");
    	
    	String responseMsg = chooseTelBean.getPickNumReq(telnum);
    	
    	responseMsg = getResponseMsg(responseMsg);
    	
		// 解析返回报文信息
		Document body = DocumentHelper.parseText(responseMsg);
		
		// 获取返回报文的根节点
		Element root = body.getRootElement();
		
		// 成功返回100，失败返回负值
		if(Constants.SH_CHOOSETEL_SUCCESSCODE.equals(root.element("returnCode").getText()))
		{
			// 虚拟产品编码
			vprodId = root.element("vprodId").getText();
		}
		else
		{
			// 获取错误信息
			String errorMsg = root.element("errorMsg").getText();
			
			if(errorMsg.isEmpty())
			{
				errorMsg = "号码" + telnum + "暂选失败！";
			}
			// 设置号码暂选失败的返回信息
			getRequest().setAttribute("errormessage", errorMsg);
			return ERROR;
		}
    		
    	logger.debug("pickNum end...");
    	
    	return SUCCESS;
    }
    
    /**
     * 预订号码 调用webservice接口
     * @return
     * @throws AxisFault 
     * @throws DocumentException 
     * @remark create by wWX217192 2015-03-16 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
     */
    public String bookNum() throws Exception
    {
    	logger.debug("bookNum start...");
    	
    	// 获取请求报文信息
    	String responseMsg = chooseTelBean.getBookNumReq(name, telnum, certid, contacttel, vprodId);
    	
    	responseMsg = getResponseMsg(responseMsg);
    	
		// 解析返回报文信息
		Document body = DocumentHelper.parseText(responseMsg);
		
		// 获取返回报文的根节点
		Element root = body.getRootElement();
		
		// returnCode 为100成功，为负值时失败
		if(Constants.SH_CHOOSETEL_SUCCESSCODE.equals(root.element("returnCode").getText()))
		{
			return SUCCESS;
		}
		else
		{
			// 获取错误信息
			String errorMsg = root.element("errorMsg").getText();
			
			if(errorMsg.isEmpty())
			{
				errorMsg = "号码" + telnum + "预订失败！";
			}
			
			// 设置号码暂选失败的返回信息
			getRequest().setAttribute("errormessage", errorMsg);
			
			return ERROR;
		}
    	
    }
    
    /**
     * 解析webservice查询号码接口的返回报文
     * @param resMsg
     * @return 解析后的号码List
     * @remark create by wWX217192 2015-03-12 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
     */
    private List<ServerNumPO> parseQueryNumResMsg(Element root)
    {
    	List<ServerNumPO> resultList = new ArrayList<ServerNumPO>();
    	
    	try
    	{
			ServerNumPO numPO = null;
			// 遍历返回报文中的numList节点
			Iterator numList = root.element("numList").elementIterator("numInfo");
			
			// 遍历numList节点下的内容
			while(numList.hasNext())
			{
				Element eChild = (Element) numList.next();
				
				numPO = new ServerNumPO();
				
				// 最低消费
				numPO.setLow_consum_fee(eChild.element("minCost").getText());
				
				// 号码
				numPO.setTelnum(eChild.element("telnum").getText());
				
				// 预存款
				numPO.setLow_consum_pre(eChild.element("preFee").getText());
				
				// 签约时长
				numPO.setLow_inservice_time(eChild.element("signTime").getText());
				
				resultList.add(numPO);
			}
    	}
    	catch (Exception e) 
		{
			logger.error("解析号码查询接口的返回报文失败：", e);
		}
		
    	return resultList;
    }
    
    /**
     * 对展示号码的最低消费的最大值进行限制
     * @param crset
     * @return
     * @remark create by wWX217192 2015-03-13 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
     */
    private List<ServerNumPO> limitLowFee(CRSet crset)
    {
    	tmpList = new ArrayList<ServerNumPO>();
    	ServerNumPO serverNumPO = null;  
    	
    	// modify begin cKF76106 2013/03/20 R003C13L02n01 OR_SD_201303_1010
        String lowFeeLimit = (String)PublicCache.getInstance().getCachedData("SH_LOWFEE_LIMIT");
        
        String lowPreLimit = (String)PublicCache.getInstance().getCachedData("SH_LOWPRE_LIMIT");

        for (int i = 0; i < crset.GetRowCount(); i++)
        {
            // 展示号码限制最低消费最大值
            if (null != lowFeeLimit)
            {
                if (!"".equals(crset.GetValue(i, 6)) && !"".equals(crset.GetValue(i, 7))
                        && (Double.valueOf(CommonUtil.fenToYuan(crset.GetValue(i, 6))) <= Double.valueOf(lowFeeLimit))
                        && (Double.valueOf(CommonUtil.fenToYuan(crset.GetValue(i, 7))) >= Double.valueOf(lowPreLimit))
                        )
                {
                    serverNumPO = new ServerNumPO();
                    serverNumPO.setTelnum(crset.GetValue(i, 0));// 手机号码
                    serverNumPO.setFee(crset.GetValue(i, 2));
                    serverNumPO.setOrg_id(crset.GetValue(i, 3));
                    
                    // add by fKF59607 OR_SD_201203_1057 选号问题 2012-05-10 begin
                    serverNumPO.setTellevel(crset.GetValue(i, 5));
                    // add by fKF59607 OR_SD_201203_1057 选号问题 2012-05-10 end
                    
                    // add begin cKF76106 2013/01/23 R003C12L12n01 OR_SD_201301_279
                    serverNumPO.setLow_consum_fee(CommonUtil.fenToYuan(crset.GetValue(i, 6)));
                    serverNumPO.setLow_consum_pre(CommonUtil.fenToYuan(crset.GetValue(i, 7)));
                    serverNumPO.setLow_inservice_time(crset.GetValue(i, 8));
                    // add end cKF76106 2013/01/23 R003C12L12n01 OR_SD_201301_279
                    
                    tmpList.add(serverNumPO);
                }
            }
            else
            {                    
                if(!"".equals(crset.GetValue(i, 6)) && !"".equals(crset.GetValue(i, 7)))
                {
                    serverNumPO = new ServerNumPO();
                    serverNumPO.setTelnum(crset.GetValue(i, 0));// 手机号码
                    serverNumPO.setFee(crset.GetValue(i, 2));
                    serverNumPO.setOrg_id(crset.GetValue(i, 3));
                    
                    // add by fKF59607 OR_SD_201203_1057 选号问题 2012-05-10 begin
                    serverNumPO.setTellevel(crset.GetValue(i, 5));
                    // add by fKF59607 OR_SD_201203_1057 选号问题 2012-05-10 end
                    
                    // add begin cKF76106 2013/01/23 R003C12L12n01 OR_SD_201301_279
                    serverNumPO.setLow_consum_fee(CommonUtil.fenToYuan(crset.GetValue(i, 6)));
                    serverNumPO.setLow_consum_pre(CommonUtil.fenToYuan(crset.GetValue(i, 7)));
                    serverNumPO.setLow_inservice_time(crset.GetValue(i, 8));
                    // add end cKF76106 2013/01/23 R003C12L12n01 OR_SD_201301_279
                    tmpList.add(serverNumPO);

                }
            }
        }
        
        return tmpList;
    }
    
    /**
     * 拼接号码的前缀和后缀
     * @remark create by wWX217192 2015-03-13 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
     */
    private void linkTelNum()
    {
    	if (tel_prefix == null)
        {
            tel_prefix = "";
        }
        
        if (tel_suffix == null)
        {
            tel_suffix = "";
        }
        
        if ("2".equals(sele_rule))// 查询类型 2：按前缀查询 3：按后缀查询
        {
            while (tel_prefix.length() < 7)
            {
                tel_prefix = tel_prefix + "_";
            }        
        }
        else if("3".equals(sele_rule))
        {
            while (tel_suffix.length() < 4)
            {
                tel_suffix = tel_suffix + "_";
            }
        }
    }
    
    // 截取返回报文信息
    private String getResponseMsg(String respMsg)
    {
    	String responseMsg = "";
    	
    	// 标签左侧被转义
    	if(respMsg.contains("&lt;"))
    	{
    		respMsg = respMsg.replace("&lt;", "<");
    	}
    	
    	// 标签右侧被转义
    	if(respMsg.contains("&gt;"))
    	{
    		respMsg = respMsg.replace("&gt;", ">");
    	}
    	
    	int startNum = respMsg.indexOf("<ns:return>");
    	
    	int endNum = respMsg.indexOf("</ns:return>");
    	
    	responseMsg = respMsg.substring(startNum, endNum);
    	
    	responseMsg = responseMsg.replace("<ns:return>", "");
    	
    	responseMsg = responseMsg.replace("</ns:return>", "");
    	
    	return responseMsg;
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

    public String getOrgid()
    {
        return orgid;
    }

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
    
    //add begin cKF48754 2011/10/25 L10 增加温馨提示信息
    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }
    //add end cKF48754 2011/10/25 L10 增加温馨提示信息

    public String getPageFlag()
    {
        return pageFlag;
    }

    public void setPageFlag(String pageFlag)
    {
        this.pageFlag = pageFlag;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public String getSendTelNum()
    {
        return sendTelNum;
    }

    public void setSendTelNum(String sendTelNum)
    {
        this.sendTelNum = sendTelNum;
    }

	public ChooseTelService getChooseTelService()
	{
		return chooseTelService;
	}

	public void setChooseTelService(ChooseTelService chooseTelService)
	{
		this.chooseTelService = chooseTelService;
	}

	public String getPreFee() {
		return preFee;
	}

	public void setPreFee(String preFee) {
		this.preFee = preFee;
	}

	public String getWebserviceFlag() {
		return webserviceFlag;
	}

	public void setWebserviceFlag(String webserviceFlag) {
		this.webserviceFlag = webserviceFlag;
	}

	public String getVprodId() {
		return vprodId;
	}

	public void setVprodId(String vprodId) {
		this.vprodId = vprodId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getManuTelnumFlag() {
		return manuTelnumFlag;
	}

	public void setManuTelnumFlag(String manuTelnumFlag) {
		this.manuTelnumFlag = manuTelnumFlag;
	}

}
