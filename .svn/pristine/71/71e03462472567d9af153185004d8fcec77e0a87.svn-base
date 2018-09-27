package com.gmcc.boss.selfsvc.managerOperation.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.managerOperation.model.CashDetailPO;
import com.gmcc.boss.selfsvc.managerOperation.model.ManagerOperationPO;
import com.gmcc.boss.selfsvc.managerOperation.service.ManagerOperationService;
import com.gmcc.boss.selfsvc.resdata.model.RegionInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 维护人员管理 包括现金稽核等
 * 
 * @author xKF29026
 * 
 */
public class ManagerOperationAction extends BaseAction
{
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 日志
    // modify begin by xkf29026 2011/10/6 添加final
    public static final Log logger = LogFactory.getLog(ManagerOperationAction.class);
    // modify end by xkf29026 2011/10/6  添加final
    
    // 稽核人密码
    private String auditPsw;

    // 稽核实际金额
    private String realMoney;
    
    // 错误信息
    private String errorMessage;
    
    // 成功信息
    private String successMessage;
    
    //系统计算金额
    private String sysMoney;
       
    //调用后台服务
    private ManagerOperationService managerOperationService;
    
    // add begin g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
    /**
     * 上次稽核结束时间，精确到秒
     */
    private String lastAuditEndTime = "";
    
    /**
     * 上次稽核开始时间，精确到分
     */
    private String lastAuditStartTimeFen = "";
    
    /**
     * 上次稽核结束时间，精确到分
     */
    private String lastAuditEndTimeFen = "";
    
    /**
     * 各种面值的纸币信息
     */
    private List<CashDetailPO> cashes = null;
    
    /**
     * 地区名称
     */
    private String regionName = "";
    // add end g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312    
    
    private String checkCashStatus ="";
    
    // add begin OR_NX_201207_777 yKF28472
    
    /**
     * 稽核ID
     */
    private String auditId = "";
    
    /**
     * printflag 0:更新记录 1:不更新记录
     */
    private String updateFlag = "0";
    
    /**
     * 本次稽核结束时间
     */
    private String auditEndTime = "";
    private String auditEndTimeFen = "";
    // end begin OR_NX_201207_777 yKF28472
    
    /**
     * 转到维护人员选择操作菜单页面
     * 
     * @return
     */
    public String selectOperationType()
    {
        return "selectOperationType";
    }
    
    /**
     * 转到稽核员输入密码页面
     */
    public String doCashAudit()
    {
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        String from = request.getParameter("from");
        if (from != null && !"".equals(from.trim()))
        {
            if (logger.isInfoEnabled())
            {
                logger.info("进入现金稽核页面，来源：" + from);
            }
        }
        
        checkCashStatus = "1";
        
        // 结账单打印标识。1：有未打印的结账单
        String unprintFlag = request.getParameter("unprintFlag");
        
        // add begin zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能
        // 现金稽核开关
        String cashAuditSwitch = CommonUtil.getParamValue(Constants.SH_CASHAUDIT_SWITCH);
        // add end zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能

		// modify begin zKF69263 2015-4-10 OR_SD_201502_169_山东_自助终端实现现金稽核功能
	    // add begin OR_NX_201207_777 yKF28472
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if ((Constants.PROOPERORGID_NX.equalsIgnoreCase(province) 
            || "1".equals(cashAuditSwitch)) && !"1".equals(unprintFlag))
        {
            //获取终端机信息
            TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
            
            //终端机id
            String termid = termInfo.getTermid();
            
            //封装稽核人对象
            ManagerOperationPO managerOperationPO = new ManagerOperationPO();
            managerOperationPO.setTermid(termid);
            
            // 查询
            List<ManagerOperationPO> unPrintRecords = managerOperationService.qryUnPrintRecords(managerOperationPO);
            
            // 有未打印的结账单记录
            if (unPrintRecords == null || unPrintRecords.size() == 0)
            {
                // 插入新记录
                managerOperationService.insertLogByAuditBefore(managerOperationPO);
            }
        }
        // add begin OR_NX_201207_777 yKF28472
        // modify end zKF69263 2015-4-10 OR_SD_201502_169_山东_自助终端实现现金稽核功能
        
        return "cashAuditPage";
    }
    
    /**
     * 校验稽核人密码
     * 
     * @return
     */
    public String checkAuditPassword()
    {
    	//加入控制台打印日志，开始
		logger.debug("checkPassword start");
    	
		String forward = "";
		
		//获取终端机信息
		TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
		
		//终端机id
		String termid = termInfo.getTermid();
		
    	//封装稽核人对象
        ManagerOperationPO managerOperationPO = new ManagerOperationPO();
        managerOperationPO.setTermid(termid);
        // add begin zKF69263 2015-4-10 OR_SD_201502_169_山东_自助终端实现现金稽核功能
        managerOperationPO.setRegion(termInfo.getRegion());
        // add end zKF69263 2015-4-10 OR_SD_201502_169_山东_自助终端实现现金稽核功能
        managerOperationPO.setAuditPsw(CommonUtil.MD5Encode(auditPsw));
        
        //调用后台校验稽核人密码
        String checkStatus = managerOperationService.checkAuditPassword(managerOperationPO);
        if (checkStatus.equals("1"))
        {
            checkCashStatus = "1";
            
            // add begin zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能
            // 现金稽核开关
            String cashAuditSwitch = CommonUtil.getParamValue(Constants.SH_CASHAUDIT_SWITCH);
            // add end zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能
            
            // modify begin g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
            String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province) || "1".equals(cashAuditSwitch))
            {
                // add begin OR_NX_201207_777 yKF28472
                updateFlag = "0";
                
                // 检查未打印的记录
                List<ManagerOperationPO> unPrintRecords = managerOperationService.qryUnPrintRecords(managerOperationPO);
                
                if (unPrintRecords != null && unPrintRecords.size()>0)
                {
                    auditId = unPrintRecords.get(0).getId();
                    
                    auditEndTime = unPrintRecords.get(0).getAuditEndTime();
                 // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
//                    if (auditEndTime != null && !"".equals(auditEndTime.trim()))
//                    {
//                        auditEndTimeFen = auditEndTime.substring(0, 16);
//                    }
//                    else
//                    {
//                        auditEndTimeFen = "现在";                       
//                    }
                    auditEndTimeFen = (auditEndTime != null && !"".equals(auditEndTime.trim())) ? auditEndTime.substring(0, 16) : "现在";
                 // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
                    
                    realMoney = unPrintRecords.get(0).getRealMoney();
                    if (realMoney != null && !"".equals(realMoney.trim()))
                    {
                        updateFlag = "1";
                        
                        realMoney = String.valueOf(Integer.parseInt(realMoney) / 100);
                    }
                }
                
                // add end OR_NX_201207_777 yKF28472
                
                // 获取上次稽核时间段
                ManagerOperationPO auditLog = managerOperationService.qryLastAuditLog(termid);
                if (null != auditLog)
                {
                    String lastAuditStartTime = auditLog.getAuditStartTime();
                    
                 // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
                    if (null == lastAuditStartTime)
                    {
                        lastAuditStartTimeFen = "";
                    }
                    else if (!"".equals(lastAuditStartTime))
                    {
                        lastAuditStartTimeFen = lastAuditStartTime.substring(0, 16);
                    }
                 // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
                    
                    lastAuditEndTime = auditLog.getAuditEndTime();
                    if (null == lastAuditEndTime)
                    {
                        lastAuditEndTime = "";
                        lastAuditEndTimeFen = "";
                    }
                    else if (!"".equals(lastAuditEndTime))
                    {
                        lastAuditEndTimeFen = lastAuditEndTime.substring(0, 16);
                    }
                }
                else
                {
                    lastAuditEndTime = "";
                    lastAuditEndTimeFen = "";
                }
                
                auditLog = new ManagerOperationPO();
                auditLog.setTermid(termid);
                // add begin zKF69263 2015-4-10 OR_SD_201502_169_山东_自助终端实现现金稽核功能
                auditLog.setRegion(termInfo.getRegion());
                // add end zKF69263 2015-4-10 OR_SD_201502_169_山东_自助终端实现现金稽核功能
                auditLog.setAuditStartTime(lastAuditEndTime);
                auditLog.setAuditEndTime(auditEndTime);
                
                //获取系统统计金额
                sysMoney = CommonUtil.fenToYuan(managerOperationService.getSysMoney(auditLog));
                
                // add begin zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能
                if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
                {
                    // 查询面额信息
                    cashes = managerOperationService.qryCashDetailInfo(auditLog, "&nbsp;&nbsp;");
                    
                    //转向inputCheckMoney.jsp页面
                    forward="nxInputCheckMoney";
                }
                else if ("1".equals(cashAuditSwitch))
                {
                    //转向sdInputCheckMoney.jsp页面
                    forward="sdInputCheckMoney";
                }
                // add end zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能
            }
            else
            {
                //获取系统计算总金额
                String sysMoney = managerOperationService.getSysMoney(termid);
                setSysMoney(sysMoney.substring(0, sysMoney.length() - 2));

                //转向inputCheckMoney.jsp页面
                forward="inputCheckMoney";
            }
            // modify end g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
        }
        
    	else if(checkStatus.equals("2"))
		{
			//设置错误信息
			setErrorMessage("现金稽核人员密码错误，请重新输入！");
     
			//转向错误页面
			forward="error";
		}
        
        //加入控制台打印日志，结束
        logger.debug("checkPassword End");
        
        return forward;
    }
    
    /**
     * 插入现金稽核日志
     * 
     * @return
     */
    public String insertAuditCashLog()
    {
    	//加入控制台打印日志，开始
		if (logger.isDebugEnabled())
		{
		    logger.debug("insertAuditCashLog start");
		}
    	
		String forward = "";
			
    	// 终端信息
    	TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
    	
    	//封装现金稽核员对象
        ManagerOperationPO managerOperationPO = new ManagerOperationPO();
        managerOperationPO.setRealMoney(realMoney+"00");
        managerOperationPO.setTermid(termInfo.getTermid());
        
        //调用后台插入现金稽核日志
        String insertStatus = managerOperationService.insertAuditCashLog(managerOperationPO);
        
        if (insertStatus.equals("1"))
        {
        	//设置成功信息
        	setSuccessMessage("现金稽核成功");
        	
        	//记录现金稽核成功日志
            this.createRecLog("", "cashAudit", "0", "0", "0", "现金稽核成功");
        	
            //转向成功页面
            forward="success";
        }
        else
        {
        	//设置错误信息
        	setErrorMessage("现金稽核失败");
        	
        	//记录现金稽核失败日志
            this.createRecLog("", "cashAudit", "0", "0", "1", "现金稽核失败");
        	
            //转向错误页面
            forward="error";
        }
        
        //加入控制台打印日志，开始
		if (logger.isDebugEnabled())
		{
		    logger.debug("insertAuditCashLog end");
		}
        
        return forward;
    }
    
    /**
     * 插入宁夏现金稽核日志
     * 
     * @return
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public String insertNXAuditLog()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("insertNXAuditLog start");
        }
        
        String forward = "finish";

        // add begin OR_NX_201207_777 yKF28472

        //调用后台插入现金稽核日志
        String insertStatus = "1";
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        List data = (List) PublicCache.getInstance().getCachedData(Constants.REGION_INFO);
        if (null != data && data.size() > 0)
        {
            RegionInfoPO regionInfoPO = null;
            for (int i = 0; i < data.size(); i++)
            {
                regionInfoPO = (RegionInfoPO) data.get(i);
                
                if (termInfo.getRegion().equalsIgnoreCase(regionInfoPO.getRegion()))
                {
                    regionName = regionInfoPO.getRegionname();
                    break;
                }
            }
        }
        
        checkCashStatus = "1";
        
        if ("0".equals(updateFlag))
        {
            // 封装现金稽核员对象
            ManagerOperationPO managerOperationPO = new ManagerOperationPO();
            managerOperationPO.setTermid(termInfo.getTermid());
            managerOperationPO.setAuditStartTime(lastAuditEndTime);
            managerOperationPO.setSysMoney(CommonUtil.yuanToFen(sysMoney));
            managerOperationPO.setRealMoney(realMoney + "00");
            managerOperationPO.setId(this.auditId);
                        
            insertStatus = managerOperationService.updateNXAuditLog(managerOperationPO);            

            if ("1".equals(insertStatus))
            {
                //设置成功信息
                setSuccessMessage("现金稽核成功");
                
                //记录现金稽核成功日志
                this.createRecLog("", "cashAudit", "0", "0", "0", "现金稽核成功");
            }
            else
            {
                //设置错误信息
                setSuccessMessage("现金稽核失败");
                
                //记录现金稽核失败日志
                this.createRecLog("", "cashAudit", "0", "0", "1", "现金稽核失败");
            }
        }
        // begin begin OR_NX_201207_777 yKF28472
        
        if (logger.isDebugEnabled())
        {
            logger.debug("insertNXAuditLog end");
        }
        
        return forward;
    }
    
    /**
     * 获取宁夏现金缴费对账单打印信息
     * 
     * @throws IOException
     * @see 
     * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
     */
    public void getPrintedData() throws IOException
    {        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        
        PrintWriter out = response.getWriter();
        
        StringBuffer buffer = new StringBuffer("");        

        buffer.append("  结账范围：\n\n  从\n");
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 封装现金稽核员对象
        ManagerOperationPO managerOperationPO = new ManagerOperationPO();
        managerOperationPO.setTermid(termInfo.getTermid());
        // add begin zKF69263 2015-4-10 OR_SD_201502_169_山东_自助终端实现现金稽核功能
        managerOperationPO.setRegion(termInfo.getRegion());
        // add end zKF69263 2015-4-10 OR_SD_201502_169_山东_自助终端实现现金稽核功能
        managerOperationPO.setAuditStartTime(lastAuditEndTime);
        managerOperationPO.setAuditEndTime(auditEndTime);
        
        // 查询稽核时间段内的前6条缴费记录
        List<CardChargeLogVO> records = managerOperationService.qrySixChargeRecords("first", managerOperationPO);
        if (null != records)
        {
            CardChargeLogVO record = null;
            for (int i = 0; i < records.size(); i++)
            {
                record = records.get(i);
                
                buffer.append("  ").append(record.getRecdate()).append(" ").append(record.getServnumber())
                        .append(" ").append(record.getFee()).append("\n");
            }
        }
        
        buffer.append("  到\n");
        
        // 查询稽核时间段内的后6条缴费记录
        records = managerOperationService.qrySixChargeRecords("", managerOperationPO);
        if (null != records)
        {
            CardChargeLogVO record = null;
            for (int i = records.size(); i > 0; i--)
            {
                record = records.get(i - 1);
                
                buffer.append("  ").append(record.getRecdate()).append(" ").append(record.getServnumber())
                        .append(" ").append(record.getFee()).append("\n");
            }
        }
        buffer.append("\n  总进钞金额：").append(sysMoney).append("\n  总笔数：");
        
        // 查询稽核时间段内的总缴费记录数目
        String strNum = managerOperationService.qryChargeRecordsNum("selectChargeRecordsNum", managerOperationPO);
        
        buffer.append(strNum).append("\n\n  面值明细：\n\n  面值总数：");
        
        int totalNum = 0;
        
        // 查询稽核时间段内面值明细
        StringBuffer subBuff = new StringBuffer(64);
        cashes = managerOperationService.qryCashDetailInfo(managerOperationPO, " ");
        if (null != cashes && cashes.size() > 0)
        {
            CashDetailPO cashDetail = null;
            for (int i = 0; i < cashes.size(); i++)
            {
                cashDetail = cashes.get(i);
                
                String cashNum = cashDetail.getCashNum();
                
                if (null == cashNum || "".equals(cashNum.trim()))
                {
                    continue;
                }
                
                totalNum += Integer.parseInt(cashDetail.getCashNum().trim());
                
                subBuff.append("  ").append(cashDetail.getCashFee()).append("元：")
                        .append(cashDetail.getCashNum()).append("张\n");
            }
        }
        
        buffer.append(totalNum).append("张\n").append(subBuff.toString())
                .append("\n\n  缴费失败及冲正明细：\n  失败笔数：");
        
        // 查询稽核时间段内的失败缴费记录数目
        strNum = managerOperationService.qryChargeRecordsNum("selectFailedRecordsNum", managerOperationPO);
        
        // add begin cKF76106 2012/08/07 R003C12L07n01 OR_NX_201207_780
        // 失败交易比数
        int failCnt = Integer.parseInt(strNum);
        // add end cKF76106 2012/08/07 R003C12L07n01 OR_NX_201207_780
        
        buffer.append(strNum).append("   冲正笔数：");
        
        // 查询稽核时间段内的冲正缴费记录数目
        strNum = managerOperationService.qryChargeRecordsNum("selectRollbackRecordsNum", managerOperationPO);
        
        buffer.append(strNum).append("\n\n  钞箱实际金额：").append(realMoney).append("\n\n");
        
        // add begin cKF76106 2012/08/07 R003C12L07n01 OR_NX_201207_780 
        // 有缴费失败记录，查询缴费失败记录
        if(failCnt > 0)
        {
            buffer.append("  缴费失败明细:\n");
            records = managerOperationService.qryFailedRecords(managerOperationPO);
            if (null != records)
            {
                CardChargeLogVO record = null;
                for (int i = 0; i < records.size(); i++)
                {
                    record = records.get(i);
                    
                    buffer.append("  时间:").append(record.getRecdate()).append(" 号码:").append(record.getServnumber())
                            .append("\n  交易流水:").append(record.getTerminalSeq()).append(" 金额:").append(record.getFee())
                            .append("\n  交易结果:").append(record.getDescription()).append("\n\n");
                }
            }
        }
        // add end cKF76106 2012/08/07 R003C12L07n01 OR_NX_201207_780
        
        if (logger.isInfoEnabled())
        {
            logger.info("终端机ID：" + termInfo.getTermid() + ";" + "对账单信息：" + buffer.toString());
        }
        
        out.write(buffer.toString());
        out.flush();
    }
    
    /**
     * 获取山东现金缴费对账单打印信息
     * 
     * @remark: create zKF69263 2015/04/17 OR_SD_201502_169_山东_自助终端实现现金稽核功能
     */
    public void getCashPrintedData() throws Exception
    {
        StringBuffer cashData = new StringBuffer();
        
        // 组装现金缴费对账单打印信息
        if (StringUtils.isNotEmpty(lastAuditStartTimeFen) || StringUtils.isNotEmpty(lastAuditEndTimeFen))
        {
            cashData.append("  上次稽核时间段：")
                .append(StringUtils.isEmpty(lastAuditStartTimeFen) ? "开始" : lastAuditStartTimeFen)
                .append("-").append(lastAuditEndTimeFen).append("\n\n");
        }
        
        cashData.append("  本次稽核时间段：")
            .append(StringUtils.isEmpty(lastAuditEndTimeFen) ? "开始" : lastAuditEndTimeFen)
            .append("-").append(java.net.URLDecoder.decode(auditEndTimeFen, "UTF-8")).append("\n\n");
        cashData.append("  系统统计金额：").append(sysMoney).append("元").append("\n\n");
        cashData.append("  钞箱实际金额：").append(realMoney).append(".00元").append("\n\n");
        
        // 生成对账单打印信息
        writeXmlMsg(cashData.toString());
        
        logger.debug("终端机ID：" + getTerminalInfoPO().getTermid() + ";" + "对账单信息：" + cashData.toString());
    }
    
  	// add begin OR_NX_201207_777 yKF28472
    /**
     * 更新状态
     * 
     * @throws IOException
     * @see 
     * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
     */
    public void updatePringFlag() throws IOException
    {        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        
        PrintWriter out = response.getWriter();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 封装现金稽核员对象
        ManagerOperationPO managerOperationPO = new ManagerOperationPO();
        managerOperationPO.setId(this.auditId);
        
        // 更新状态
        managerOperationService.updatePringFlag(managerOperationPO);
        
        if (logger.isInfoEnabled())
        {
            logger.info("终端机ID：" + termInfo.getTermid() + ";" + "更新状态成功！");
        }
        
        out.write("");
        
        out.flush();
    }
    
    /**
     * 更新状态
     * 
     * @throws IOException
     * @see 
     * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
     */
    public void getUnPrintRecord() throws IOException
    {        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        
        PrintWriter out = response.getWriter();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 封装现金稽核员对象
        ManagerOperationPO managerOperationPO = new ManagerOperationPO();
        managerOperationPO.setTermid(termInfo.getTermid());
        
        // 查询
        List<ManagerOperationPO> unPrintRecords = managerOperationService.qryUnPrintRecords(managerOperationPO);
        
        // 有未打印的结账单记录
        if (unPrintRecords != null && unPrintRecords.size() > 0)
        {
            out.write("1");
        }
        // 没有未打印的结账单记录
        else
        {
            out.write("0");
        }
        
        out.flush();
    }
    // end begin OR_NX_201207_777 yKF28472
    
    public String getAuditPsw()
    {
        return auditPsw;
    }
    
    public void setAuditPsw(String auditPsw)
    {
        this.auditPsw = auditPsw;
    }
    
    public ManagerOperationService getManagerOperationService()
    {
        return managerOperationService;
    }
    
    public void setManagerOperationService(ManagerOperationService managerOperationService)
    {
        this.managerOperationService = managerOperationService;
    }
    
    public String getRealMoney()
    {
        return realMoney;
    }
    
    public void setRealMoney(String realMoney)
    {
        this.realMoney = realMoney;
    }


	public String getSysMoney() {
		return sysMoney;
	}

	public void setSysMoney(String sysMoney) {
		this.sysMoney = sysMoney;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	// add begin g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
    public String getLastAuditEndTime()
    {
        return lastAuditEndTime;
    }

    public void setLastAuditEndTime(String lastAuditEndTime)
    {
        this.lastAuditEndTime = lastAuditEndTime;
    }

    public List<CashDetailPO> getCashes()
    {
        return cashes;
    }

    public void setCashes(List<CashDetailPO> cashes)
    {
        this.cashes = cashes;
    }
    
    public String getLastAuditStartTimeFen()
    {
        return lastAuditStartTimeFen;
    }

    public void setLastAuditStartTimeFen(String lastAuditStartTimeFen)
    {
        this.lastAuditStartTimeFen = lastAuditStartTimeFen;
    }

    public String getLastAuditEndTimeFen()
    {
        return lastAuditEndTimeFen;
    }

    public void setLastAuditEndTimeFen(String lastAuditEndTimeFen)
    {
        this.lastAuditEndTimeFen = lastAuditEndTimeFen;
    }    

    public String getRegionName()
    {
        return regionName;
    }

    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }
    // add end g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312

    public String getCheckCashStatus()
    {
        return checkCashStatus;
    }

    public void setCheckCashStatus(String checkCashStatus)
    {
        this.checkCashStatus = checkCashStatus;
    }

	// add begin OR_NX_201207_777 yKF28472
    public String getAuditId()
    {
        return auditId;
    }

    public void setAuditId(String auditId)
    {
        this.auditId = auditId;
    }


    // end begin OR_NX_201207_777 yKF28472

    public String getUpdateFlag()
    {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag)
    {
        this.updateFlag = updateFlag;
    }

    public String getAuditEndTimeFen()
    {
        return auditEndTimeFen;
    }

    public void setAuditEndTimeFen(String auditEndTimeFen)
    {
        this.auditEndTimeFen = auditEndTimeFen;
    }

    public String getAuditEndTime()
    {
        return auditEndTime;
    }

    public void setAuditEndTime(String auditEndTime)
    {
        this.auditEndTime = auditEndTime;
    }   
}
