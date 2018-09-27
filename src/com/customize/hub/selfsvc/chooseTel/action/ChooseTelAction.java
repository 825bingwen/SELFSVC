package com.customize.hub.selfsvc.chooseTel.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.ChooseTelBean;
import com.customize.hub.selfsvc.chooseTel.model.ChooseTelLogPO;
import com.customize.hub.selfsvc.chooseTel.model.ChooseTelNumPO;
import com.customize.hub.selfsvc.chooseTel.model.LoverNumPO;
import com.customize.hub.selfsvc.chooseTel.model.NetNbrPO;
import com.customize.hub.selfsvc.chooseTel.model.NetValuePO;
import com.customize.hub.selfsvc.chooseTel.model.ServerNumPO;
import com.customize.hub.selfsvc.chooseTel.service.ChooseTelService;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 预约选号_湖北
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
    
    //号码模式=网号+号段+尾数(11位下划线)
    private String phoneMode;
    
    // 号码用途(默认传"rsupSal")
    private String purpose;
    
    // 号码页数
    private int page = 0;
    
    // 每页显示号码数
    private int pageviewnum = 0;
    
    // 总页数
    private int pageCount = 0;
    
    // 预约手机号吗
    private String telnum;
    
    // 预存费用
    private String payfee;
    
    // 预存费用
    private String payfeeY;
    
    // 身份证号码
    private String idCard;
    
    // 网号
    private String netNbr;
    
    // 号段
    private String netValue;
    
    //  尾数
    String finalNbr;
    
    // 分页时用到（1:从第一页开始）
    String bz;
    
    // 存放全部手机号码数据
    private List<ServerNumPO> tmpList = null;
    
    // 每页号码列表
    private List<ServerNumPO> serverNumList = null;
    
    // 存放全部网号数据
    private List<NetNbrPO> netNbrTmpList = null;
    
    // 每页网号列表
    private List<NetNbrPO> netNbrList = null;
    
    // 存放全部网段数据
    private List<NetValuePO> netValueTmpList = null;
    
    // 每页网段信息
    private List<NetValuePO> netValueList = null;
    
    // success 0:成功 1:失败
    private String successBz; 
    
    // 验证码
    private String coid;
    
    // 失效时间
    private String ctime;
    
    // 提示信息
    private String cremind = "";
    
    // 打印地点
    private String locus;
    
    // bean
    private ChooseTelBean chooseTelBean;
    
    // service
    private ChooseTelService chooseTelService;
    
    //add by xkf57421 for ZG[2011]_11_006 begin
    private String initBz;
    //add by xkf57421 for ZG[2011]_11_006 end
    
    /**
     * 是否需要分页
     */
    private String pageFlag = "false";
    
    /**
     * 号码类别。ALL，所有号码；GOOD，吉祥号码；LOVE，情侣号码；AABB；ABAB；ABBB
     */
    private String telNumType = "ALL";
    
    /**
     * 吉祥号码规则列表
     */
    private List<DictItemPO> luckyNumRules = null; 
    
    /**
     * 吉祥号码规则
     */
    private String luckyNumRule = "";
    
    /**
     * 情侣号码列表
     */
    private List<LoverNumPO> loverNums = null;
    
    /**
     * 每页显示的情侣号码
     */
    private List<LoverNumPO> pageLoverNums = null;
    
    /**
     * 所选号码是否是情侣号码。1，是；其它，不是
     */
    private String loverNumFlag = "";
    
    /**
	 * 提示信息
	 */	
    private String alertMsg = "";

    /**
     * 由于选号页面出现停滞，增加一loading页面，并防止重复提交
     * @author xkf57421
     */
    public String loadingMain()
    {
    	return "loadingPhoneList";
    }
    
    /**
     * 号码列表主页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String phoneListMain()
    {
        // 新版自助选号标识
        String newVersion = (String) PublicCache.getInstance().getCachedData(Constants.SH_SELECTTEL_NEW);
                
        try
        {
            // 获取session
            HttpSession session = getRequest().getSession(true);
            
            // 获取终端机信息
            TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // 号码模式=网号+号段+尾数(不选任何规则情况下传11位下划线)
            phoneMode = "___________";
            
            // 号码用途(默认传"rsupSal")
            purpose = "rsupSal";
            
            // 号码页数(第几页)
            if ("1".equals(bz) || page == 0)
            {
                page = 1;
            }
            
            // 查询预约号码列表
            tmpList = this.chooseTelBean.phoneNumQuery(terminalInfoPO, curMenuId, phoneMode, purpose, String.valueOf(page));
            
            // 记录日志
            if (tmpList != null && tmpList.size() > 0)
            {
	            this.createRecLog("SHChooseTelNumQry", "0", "0", "0", "自助选号查询成功.");
            }
            else
            {
                String resultMsg = getConvertMsg("自助选号查询失败。", "zz_04_18_000001", null, new String[]{""});
                
                this.createRecLog("SHChooseTelNumQry", "0", "0", "1", resultMsg);
            }
            
            this.getRequest().setAttribute("recordCount", tmpList.size());
            
            // 新版，每页仅显示20条记录
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
            pageviewnum = "1".equals(newVersion) ? 20 : 25;
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
            
            if (pageviewnum < tmpList.size())
            {
                pageFlag = "true";
            }
            
            // 结束行数
            int endRowNum = pageviewnum * page;
            
            // 开始条数
            int startRowNum = endRowNum - pageviewnum;
            
            // 总页数
            if (tmpList.size() % pageviewnum != 0)
            {
                pageCount = tmpList.size() / pageviewnum + 1;
            }
            else
            {
                pageCount = tmpList.size() / pageviewnum;
            }
            
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
            pageCount = (pageCount == 0) ? 1 : pageCount;
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）

            // 从内存中取得每页数据
            serverNumList = new ArrayList<ServerNumPO>();
            for(int i=startRowNum;i<endRowNum && i<tmpList.size();i++)
            {
                serverNumList.add((ServerNumPO)tmpList.get(i));
            }
            
            // 填冲不满一页的其它对象，防止页面变型
            if (page == pageCount)
            {
                for(int i=0;i<endRowNum-tmpList.size();i++)
                {
                    // 写入空对象
                    ServerNumPO po = new ServerNumPO();
                    po.setTelnum("");
                    po.setSeltel_prepayfee("");
                    serverNumList.add(po);
                }
            }
        
        }
        catch(Exception e)
        {
            logger.error("自助选号，查询号码列表失败：", e);
        }
        
        // 新版，跳至新页面。保留原有页面不变
        if ("1".equals(newVersion))
        {
            return "phoneListMainNew";
        }
        
        // 返回
        return "phoneListMain";
    }
    
    /**
     * 输入身份证号码页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String inputIdCard()
    {
        logger.debug("inputIdCard Starting...");
        
        System.out.println("telnum:"+this.telnum);
        System.out.println("payfee:"+this.payfee);
        
        logger.debug("inputIdCard end!");
        
        // 返回
        return "inputIdCard";
    }
    
    /**
     * 检查身份证号码
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String checkIdCard()
    {
        logger.debug("checkIdCard Starting ...");
        
        try
        {
            getRequest().setCharacterEncoding("GBK");
            getResponse().setContentType("text/html;charset=GBK");
            String data = "";
            
            // 检查预约次数
            // 组装查询对象
            ChooseTelNumPO chooseTelNumPO = new ChooseTelNumPO();
            chooseTelNumPO.setIdCard(this.getRequest().getParameter("idCard"));
            chooseTelNumPO.setChooseTelDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            
            // 执行查询
            List<ChooseTelNumPO> chooseTelNumList = this.chooseTelService.getChooseTelNum(chooseTelNumPO);
            if(chooseTelNumList.size() != 0)// 预定过
            {
                // 取得查询对象
                chooseTelNumPO = (ChooseTelNumPO)chooseTelNumList.get(0);
                int time = Integer.parseInt(chooseTelNumPO.getChooseTelTime());
                int sh_choosetel_idcard_time = Integer.parseInt((String)PublicCache.getInstance().getCachedData("SH_CHOOSETEL_IDCARD_TIME"));
                if (time >= sh_choosetel_idcard_time)
                {
                    data = "一个身份证号码每天最多预约"+sh_choosetel_idcard_time+"次!";
                }
            }

            PrintWriter out = getResponse().getWriter();
            out.print(data);
            out.flush();
            logger.debug("checkIdCard End!");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 号码预约（身份证方式）
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String idCardFinish()
    {
        logger.debug("inputIdCard Starting...");
        
        // 获取session
        HttpSession session = getRequest().getSession(true);
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        if (terminalInfoPO == null)
        {
            // 失败
            this.successBz = "1";
            
            alertMsg = getConvertMsg("身份证预约号码失败。", "zz_04_18_000003", null, new String[]{telnum});
            
            return "idCardFinish";
        }
        
        // 参数
        String telnum = this.telnum;// 手机号码
        String seltelprepay = payfee;// 预存费用
        String channelid = "bsacAtsv";// 受理渠道, 默认："bsacAtsv"
        String credentFlag = "1";// 0 验证码；1 身份证
        String certtype = "IdCard";// 证件号码
        String certid = idCard;// 证件号码
        
        // 预约号码
        Map resultMap = this.chooseTelBean.bespeakPhone(terminalInfoPO, curMenuId,telnum,seltelprepay,channelid,credentFlag,certtype,certid);
        
        ChooseTelLogPO chooseTelLogPO = new ChooseTelLogPO();
        
        chooseTelLogPO.setRegion(terminalInfoPO.getRegion());
        chooseTelLogPO.setTermId(terminalInfoPO.getTermid());//终端编号
        chooseTelLogPO.setOrgId(terminalInfoPO.getOrgid());//选号营业厅
        chooseTelLogPO.setOperId(terminalInfoPO.getOperid());//选号工号
        
        chooseTelLogPO.setIdCard(certid);
        chooseTelLogPO.setTelNum(telnum);
        chooseTelLogPO.setPayfee(seltelprepay);
        
        if (resultMap != null && resultMap.size() > 0)
        {
            CTagSet tagSet = (CTagSet)resultMap.get("returnObj");
            
            // 验证ID
            this.coid = tagSet.GetValue("oid");
            
            // 失效时间
            this.ctime = tagSet.GetValue("vidatetime");
            
            // 友情提示
            this.cremind = tagSet.GetValue("remind");
            
            // add begin g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
            if (cremind == null || "".equals(cremind.trim()))
            {
            	cremind = "入网号码对应套餐选择、预存话费及保底消费等政策以当地营业厅为准。";
            }
            else
            {
            	cremind = cremind.trim();
            	
            	if (cremind.endsWith(".") || cremind.endsWith("。") || cremind.endsWith("!") || cremind.endsWith("！"))
                {
                	cremind += "入网号码对应套餐选择、预存话费及保底消费等政策以当地营业厅为准。";
                }
                else
                {
                	cremind += "。入网号码对应套餐选择、预存话费及保底消费等政策以当地营业厅为准。";
                }
            }
            // add end g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
            
            // 打印地点
            this.locus = terminalInfoPO.getOrgname();
            
            // 成功
            this.successBz = "0";
            
            // 如果是情侣号码，需更新sh_tel_lover中的状态和失效时间
            if ("1".equals(loverNumFlag))
            {
                LoverNumPO po = new LoverNumPO();
                po.setMainNum(telnum);
                po.setExpiredDate(ctime);
                
                chooseTelService.updateLoverNumInfo(po);
            }
            
            // 检查预约次数
            // 组装查询对象
            ChooseTelNumPO chooseTelNumPO = new ChooseTelNumPO();
            chooseTelNumPO.setIdCard(this.getRequest().getParameter("idCard"));
            chooseTelNumPO.setChooseTelDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            
            // 执行查询
            List<ChooseTelNumPO> chooseTelNumList = this.chooseTelService.getChooseTelNum(chooseTelNumPO);
            if(chooseTelNumList.size() == 0)// 未预定过
            {
                // 组装新增对象
                chooseTelNumPO = new ChooseTelNumPO();
                chooseTelNumPO.setIdCard(idCard);
                chooseTelNumPO.setChooseTelDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                chooseTelNumPO.setChooseTelTime("1");
                
                // 执行新增
                this.chooseTelService.insertChooseTelNum(chooseTelNumPO);
            }
            else
            {
                // 取得查询对象
                chooseTelNumPO = (ChooseTelNumPO)chooseTelNumList.get(0);
                int time = Integer.parseInt(chooseTelNumPO.getChooseTelTime());
                
                // 组装更新对象
                chooseTelNumPO = new ChooseTelNumPO();
                chooseTelNumPO.setIdCard(idCard);
                chooseTelNumPO.setChooseTelDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                chooseTelNumPO.setChooseTelTime(String.valueOf(time + 1));
                
                // 执行更新
                this.chooseTelService.updateChooseTelNum(chooseTelNumPO);
            }
            
            // 记录日志
            this.createRecLog("SHChooseTelNum", "0", "0", "0", "身份证预约号码成功.");
            
            //记录选号预约日志
            String vidateTime = null;
            if (StringUtils.isNotBlank(this.ctime))
            {
                if (this.ctime.contains("0~"))
                {
                    vidateTime = this.ctime.substring(2, this.ctime.length());
                }
                else
                {
                    vidateTime = this.ctime;
                }
                chooseTelLogPO.setVidateTime(CommonUtil.formatDate(vidateTime, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
            }            
            chooseTelLogPO.setSubResult("0");
            chooseTelLogPO.setSubResultDesc("身份证预约号码成功");
            chooseTelService.createTelLog(chooseTelLogPO);
        }
        else
        {
            // 失败
            this.successBz = "1";
            
            alertMsg = getConvertMsg("身份证预约号码失败。", "zz_04_18_000003", null, new String[]{telnum});
            
            // 记录日志
            this.createRecLog("SHChooseTelNum", "0", "0", "1", alertMsg);
            
            //记录选号预约日志
            chooseTelLogPO.setSubResult("1");
            
            if (alertMsg.length() > 256)
            {
                alertMsg = alertMsg.substring(0, 256);
            }
            
            chooseTelLogPO.setSubResultDesc(alertMsg);
            chooseTelService.createTelLog(chooseTelLogPO);
        }
        
        logger.debug("idCardFinish end!");
        
        return "idCardFinish";
    }
    
    /**
     * 号码预约（验证码方式）
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String validateFinish()
    {
        logger.debug("validateFinish Starting...");
        
        // 获取session
        HttpSession session = getRequest().getSession(true);
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 参数
        String telnum = this.telnum;// 手机号码
        String seltelprepay = payfee;// 预存费用
        String channelid = "bsacAtsv";// 受理渠道, 默认："bsacAtsv"
        String credentFlag = "0";// 0 验证码；1 身份证
        String certtype = "";// 证件号码
        String certid = "";// 证件号码
        
        // 预约号码
        Map resultMap = this.chooseTelBean.bespeakPhone(terminalInfoPO, curMenuId,telnum,seltelprepay,channelid,credentFlag,certtype,certid);
        
        if (resultMap != null && resultMap.size() > 0)
        {
            CTagSet tagSet = (CTagSet)resultMap.get("returnObj");
            
            // 验证ID
            this.coid = tagSet.GetValue("oid");
            
            // 失效时间
            this.ctime = tagSet.GetValue("vidatetime");
            
            // 友情提示
            this.cremind = tagSet.GetValue("remind");
            
            // add begin g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
            if (cremind == null || "".equals(cremind.trim()))
            {
            	cremind = "入网号码对应套餐选择、预存话费及保底消费等政策以当地营业厅为准。";
            }
            else
            {
            	cremind = cremind.trim();
            	
            	if (cremind.endsWith(".") || cremind.endsWith("。") || cremind.endsWith("!") || cremind.endsWith("！"))
                {
                	cremind += "入网号码对应套餐选择、预存话费及保底消费等政策以当地营业厅为准。";
                }
                else
                {
                	cremind += "。入网号码对应套餐选择、预存话费及保底消费等政策以当地营业厅为准。";
                }
            }
            // add end g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
            
            // 打印地点
            this.locus = terminalInfoPO.getOrgname();
            
            // 预约费用
            this.payfeeY = new DecimalFormat("0.00").format(Double.parseDouble(payfee)/100);   
            
            // 成功
            this.successBz = "0";
            
            // 如果是情侣号码，需更新sh_tel_lover中的状态和失效时间
            if ("1".equals(loverNumFlag))
            {
                LoverNumPO po = new LoverNumPO();
                po.setMainNum(telnum);
                po.setExpiredDate(ctime);
                
                chooseTelService.updateLoverNumInfo(po);
            }
            
            // 记录日志
            this.createRecLog("SHChooseTelNum", "0", "0", "0", "验证码预约号码成功.");
        }
        else
        {
            // 失败
            this.successBz = "1";
            
            alertMsg = getConvertMsg("验证码预约号码失败。", "zz_04_18_000002", null, new String[]{telnum});
            
            // 记录日志
            this.createRecLog("SHChooseTelNum", "0", "0", "1", alertMsg);
        }
        
        logger.debug("validateFinish end!");
        
        return "validateFinish";
    }
    
    /**
     * 按尾号进行搜索
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String phoneSearchByFinalNbr()
    {
        logger.debug("phoneSearchByFinalNbr Starting...");
        
            
        // 获取session
        HttpSession session = getRequest().getSession(true);
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 首次登录标志
        if ("1".equals(bz) || page == 0)
        {
            page = 1;
        }
        
        // 网号
        netNbr = "___";
        
        // 号段
        netValue = "____";
        
        // 尾号
        if (finalNbr == null)
        {
            finalNbr = "";
        }
        String tmpFinalNbr = finalNbr;
        
        // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        finalNbr = this.evalUnderline(finalNbr);
        // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        
        // 号码模式=网号+号段+尾数
        String phoneMode = netNbr + netValue + finalNbr;

        // 号码用途(默认传"rsupSal")
        String purpose = "rsupSal";
        
        // 查询预约号码列表
        tmpList = this.chooseTelBean.phoneNumQuery(terminalInfoPO, curMenuId, phoneMode, purpose, String.valueOf(page));
        
        this.getRequest().setAttribute("recordCount", tmpList.size());
        
        // 每页显示号码数
        pageviewnum = 12;
        
        if (pageviewnum < tmpList.size())
        {
            pageFlag = "true";
        }
        
        // 结束行数
        int endRowNum = pageviewnum * page;
        
        // 开始行数
        int startRowNum = endRowNum - pageviewnum;
        
        // 总页数
        if (tmpList.size() % pageviewnum != 0)
        {
            pageCount = tmpList.size() / pageviewnum + 1;
        }
        else
        {
            pageCount = tmpList.size() / pageviewnum;
        }
        
        if (pageCount == 0)
        {
       		pageCount = 1;
        }
        
        // 每页数据
        serverNumList = new ArrayList<ServerNumPO>();
        for(int i=startRowNum;i<endRowNum && i<tmpList.size();i++)
        {
            serverNumList.add((ServerNumPO)tmpList.get(i));
        }
        
        if (page == pageCount)
        {
            for(int i=0;i<endRowNum-tmpList.size();i++)
            {
                ServerNumPO po = new ServerNumPO();
                po.setTelnum("");
                po.setSeltel_prepayfee("");
                serverNumList.add(po);
            }
        }
        
        finalNbr = tmpFinalNbr;
        
        logger.debug("phoneSearchByFinalNbr end!");
        
        // 返回
        return "phoneSearch";
    }

    /**
     * 圈复杂度
     * @param finalNbr
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
     */
	private String evalUnderline(String str) 
	{
		if ("".equals(str) || str == null || "null".equals(str)) {
			str = "____";
        } else if (str.length() == 1) {
        	str = "___" + str;
        } else if (str.length() == 2) {
        	str = "__" + str;
        } else if (str.length() == 3) {
        	str = "_" + str;
        }
		return str;
	}
    
    /**
     * 按网号进行搜索
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String phoneSearchByNetNbr()
    {
        logger.debug("phoneSearchByNetNbr Starting...");
        
        try{
            
            // 获取session
            HttpSession session = getRequest().getSession(true);
            
            // 获取终端机信息
            TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            if ("1".equals(bz) || page == 0)
            {
                page = 1;
            }
            
            // 网络类型(默认传"GSM")
            String netType = "GSM";
    
            // 号码用途(默认传"rsupSal")
            String pur = "rsupSal";
            
            // 查询预约号码列表
            Map mapResult = this.chooseTelBean.netNbrQuery(terminalInfoPO, curMenuId, netType, pur);
            
            // 全部数据取出存放到临时变量中
            netNbrTmpList = new ArrayList<NetNbrPO>();
            if (mapResult != null && mapResult.size() > 0)
            {   
                NetNbrPO netNbrPO = null;
                CRSet crset = (CRSet) mapResult.get("returnObj");
                for (int i=0;i<crset.GetRowCount();i++)
                {
                    netNbrPO = new NetNbrPO();
                    netNbrPO.setNetNbr(crset.GetValue(i, 0));// 网号
                    netNbrTmpList.add(netNbrPO);
                }
            }
            
            this.getRequest().setAttribute("recordCount", netNbrTmpList.size());
            
            // 每页显示号码数
            pageviewnum = 30;
            
            if (pageviewnum < netNbrTmpList.size())
            {
                pageFlag = "true";
            }
            
            // 开始行
            int endRowNum = pageviewnum * page;
            
            // 结束行
            int startRowNum = endRowNum - pageviewnum;
            
            // 总页数
            if (netNbrTmpList.size() % pageviewnum != 0)
            {
                pageCount = netNbrTmpList.size() / pageviewnum + 1;
            }
            else
            {
                pageCount = netNbrTmpList.size() / pageviewnum;
            }
            
            if (pageCount == 0)
            {
            	pageCount = 1;
            }
            
            // 取得每页的值
            netNbrList = new ArrayList<NetNbrPO>();
            for(int i=startRowNum;i<endRowNum && i<netNbrTmpList.size();i++)
            {
                netNbrList.add((NetNbrPO)netNbrTmpList.get(i));
            }
            
            // 不够一页的用空对象来填冲
            if (page == pageCount)
            {
                for(int i=0;i<endRowNum-netNbrTmpList.size();i++)
                {
                    NetNbrPO po = new NetNbrPO();
                    po.setNetNbr("");
                    netNbrList.add(po);
                }
            }
            
            for(NetNbrPO a:netNbrList)
            {
                System.out.println("------"+a.getNetNbr());
            }
            
            logger.debug("phoneSearchByNetNbr end!");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return "phoneSearchByNetNbr";
        
    }
    
    /**
     * 按网段进行搜索
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String phoneSearchByNetValue()
    {
        logger.debug("phoneSearchByNetValue Starting...");
            
        // 获取session
        HttpSession session = getRequest().getSession(true);
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        if ("1".equals(bz) || page == 0)
        {
            page = 1;
        }
        // 网号，没有用___代替
        String netnbr = "___";
        
        // 网络类型(默认传"GSM")
        String nettype = "GSM";

        // 号码用途(默认传"rsupSal")
        String pur = "rsupSal";
        
        // 查询网段列表
        Map mapResult = this.chooseTelBean.netValueQuery(terminalInfoPO, curMenuId, netnbr, nettype, pur);

        // 命令字 GetTelnumsForSelfHelp 
        
        netValueTmpList = new ArrayList<NetValuePO>();
        if (mapResult != null && mapResult.size() > 0)
        {   
            NetValuePO netValuePO = null;
            CRSet crset = (CRSet) mapResult.get("returnObj");
            for (int i=0;i<crset.GetRowCount();i++)
            {
                netValuePO = new NetValuePO();
                netValuePO.setNetValue(crset.GetValue(i, 0));// 网号
                netValueTmpList.add(netValuePO);
            }
        }
        
        this.getRequest().setAttribute("recordCount", netValueTmpList.size());
        
        // 每页显示号码数
        pageviewnum = 30;
        
        if (pageviewnum < netValueTmpList.size())
        {
            pageFlag = "true";
        }
        
        // 结否行数
        int endRowNum = pageviewnum * page;
        
        // 开始行数
        int startRowNum = endRowNum - pageviewnum;
        
        // 总页数
        if (netValueTmpList.size() % pageviewnum != 0)
        {
            pageCount = netValueTmpList.size() / pageviewnum + 1;
        }
        else
        {
            pageCount = netValueTmpList.size() / pageviewnum;
        }
        
        if (pageCount == 0)
        {
            pageCount = 1;
        }
        
        // 查询每页信息
        netValueList = new ArrayList<NetValuePO>();
        for(int i=startRowNum;i<endRowNum && i<netValueTmpList.size();i++)
        {
            netValueList.add((NetValuePO)netValueTmpList.get(i));
        }
        
        // 处理空值
        if (page == pageCount)
        {
            for(int i=0;i<endRowNum-netValueTmpList.size();i++)
            {
                NetValuePO po = new NetValuePO();
                po.setNetValue("");
                netValueList.add(po);
            }
        }
        
        logger.debug("phoneSearchByNetValue end!");
            
        return "phoneSearchByNetValue";
    }
    
    /**
     * 网段搜索结果页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String netValueSearchResult()
    {
        logger.debug("netValueSearchResult Starting...");
        
        try{
            
        
            // 获取session
            HttpSession session = getRequest().getSession(true);
            
            // 获取终端机信息
            TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段） 
            netValue = this.evalUnderline(netValue);
            // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
            
            // 号码模式=网号+号段+尾数(11位下划线)
            phoneMode = "___"+netValue+"____";
            
            // 号码用途(默认传"rsupSal")
            purpose = "rsupSal";
            
            // 号码页数(第几页)
            if ("1".equals(bz))
            {
                page = 1;
            }
            else if (page == 0)
            {
                page = 1;
            }
            
            // 查询预约号码列表
            tmpList = this.chooseTelBean.phoneNumQuery(terminalInfoPO, curMenuId, phoneMode, purpose, String.valueOf(page));
            
            this.getRequest().setAttribute("recordCount", tmpList.size());
            
            // 每页显示号码数
            pageviewnum = 25;
            
            if (pageviewnum < tmpList.size())
            {
                pageFlag = "true";
            }
            
            // 结束行数
            int endRowNum = pageviewnum * page;
            
            // 开始行数
            int startRowNum = endRowNum - pageviewnum;
            
            // 总页数
            if (tmpList.size() % pageviewnum != 0)
            {
                pageCount = tmpList.size() / pageviewnum + 1;
            }
            else
            {
                pageCount = tmpList.size() / pageviewnum;
            }
            
            if (pageCount == 0)
            {
            	pageCount = 1;
            }
            
            // 查询每页信息
            serverNumList = new ArrayList<ServerNumPO>();
            for(int i=startRowNum;i<endRowNum && i<tmpList.size();i++)
            {
                serverNumList.add((ServerNumPO)tmpList.get(i));
            }
            
            // 填冲空值
            if (page == pageCount)
            {
                for(int i=0;i<endRowNum-tmpList.size();i++)
                {
                    ServerNumPO po = new ServerNumPO();
                    po.setTelnum("");
                    po.setSeltel_prepayfee("");
                    serverNumList.add(po);
                }
            }
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        logger.debug("netValueSearchResult end!");
        
        return "netValueSearchResult";
    }
    
    /**
     * 网号搜索结果页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String netNbrSearchResult()
    {
        logger.debug("netNbrSearchResult Starting...");
        
        try
        {
            
            // 获取session
            HttpSession session = getRequest().getSession(true);
            
            // 获取终端机信息
            TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // 号码模式=网号+号段+尾数(11位下划线)
            phoneMode = this.netNbr + "________";
            
            // 号码用途(默认传"rsupSal")
            purpose = "rsupSal";
            
            // 号码页数(第几页)
            if ("1".equals(bz))
            {
                page = 1;
            }
            else if (page == 0)
            {
                page = 1;
            }
            
            // 查询预约号码列表
            tmpList = this.chooseTelBean.phoneNumQuery(terminalInfoPO, curMenuId, phoneMode, purpose, String.valueOf(page));
            
            this.getRequest().setAttribute("recordCount", tmpList.size());
            
            // 每页显示号码数
            pageviewnum = 25;
            
            if (pageviewnum < tmpList.size())
            {
                pageFlag = "true";
            }
            
            // 结束行数
            int endRowNum = pageviewnum * page;
            
            // 开始行数
            int startRowNum = endRowNum - pageviewnum;
            
            // 总页数
            if (tmpList.size() % pageviewnum != 0)
            {
                pageCount = tmpList.size() / pageviewnum + 1;
            }
            else
            {
                pageCount = tmpList.size() / pageviewnum;
            }
            
            if (pageCount == 0)
            {
            	pageCount = 1;
            }
            
            // 查询每页信息
            serverNumList = new ArrayList<ServerNumPO>();
            for(int i=startRowNum;i<endRowNum && i<tmpList.size();i++)
            {
                serverNumList.add((ServerNumPO)tmpList.get(i));
            }
            
            // 处理空值
            if (page == pageCount)
            {
                for(int i=0;i<endRowNum-tmpList.size();i++)
                {
                    ServerNumPO po = new ServerNumPO();
                    po.setTelnum("");
                    po.setSeltel_prepayfee("");
                    serverNumList.add(po);
                }
            }
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        logger.debug("netNbrSearchResult end!");
        
        return "netNbrSearchResult";
    }
    
    /**
     * 按指定类型查询可选号码列表
     * 
     * @return
     * @see 
     */
    public String qryTelnumsWithType()
    {
        // 所有号码
        if ("ALL".equalsIgnoreCase(telNumType))
        {
            return phoneListMain();
        }
        // 吉祥号码
        else if ("GOOD".equalsIgnoreCase(telNumType))
        {
            // 吉祥号码规则列表
            luckyNumRules = chooseTelService.qryLuckyNumRules();
            
            if (luckyNumRules == null)
            {
                luckyNumRules = new ArrayList<DictItemPO>();
            }
            
            int size = luckyNumRules.size();

            // 每页最多显示20种规则
            if (size < 20)
            {
                DictItemPO po = null;
                for (int i = size; i < 20; i++)
                {
                    po = new DictItemPO();
                    
                    luckyNumRules.add(po);
                }
            }
            
            return "goodTypePage";
        }
        // 情侣号码
        else if ("LOVE".equalsIgnoreCase(telNumType))
        {
            return qryLoverNums();
        }
        // ABAB、AABB、ABBB
        else
        {
            return qrySpecifiedPatternNums();
        }
    }
    
    /**
     * 根据吉祥号码规则查询符合条件的吉祥号码
     * 
     * @return
     * @see 
     */
    public String qryLuckyNums()
    {
        // 获取session
        HttpSession session = getRequest().getSession(true);
            
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
        // 号码模式
        phoneMode = "%" + luckyNumRule + "%";

        // 符合吉祥号码规则的号码列表
        tmpList = this.chooseTelBean.phoneNumQuery(terminalInfoPO, curMenuId, phoneMode, "", "");
     
        // 查询吉祥号码对应的费用
//        Map<String, ServerNumPO> luckyNumInfo = null;
        
        // 记录日志
        if (tmpList != null && tmpList.size() > 0)
        {
            this.createRecLog("SHChooseTelNumQry", "0", "0", "0", "自助选号吉祥号码(" + phoneMode + ")查询成功.");

//            luckyNumInfo = this.chooseTelService.qryLuckyNumInfo();
        }
        else
        {
            this.createRecLog("SHChooseTelNumQry", "0", "0", "1", "自助选号吉祥号码(" + phoneMode + ")查询失败.");
        }
            
        this.getRequest().setAttribute("recordCount", tmpList.size());
            
        pageviewnum = 20;
        
        if (pageviewnum < tmpList.size())
        {
            pageFlag = "true";
        }
            
        // 结束行数
        if (page == 0)
        {
            page = 1;
        }
        int endRowNum = pageviewnum * page;
            
        // 开始条数
        int startRowNum = endRowNum - pageviewnum;
            
        // 总页数
        if (tmpList.size() % pageviewnum != 0)
        {
            pageCount = tmpList.size() / pageviewnum + 1;
        }
        else
        {
            pageCount = tmpList.size() / pageviewnum;
        }
        
        if (pageCount == 0)
        {
            pageCount = 1;
        }
            
        // 从内存中取得每页数据
        serverNumList = new ArrayList<ServerNumPO>();
        ServerNumPO luckyNumPO = null;
        
        for(int i=startRowNum;i<endRowNum && i<tmpList.size();i++)
        {
            luckyNumPO = (ServerNumPO) tmpList.get(i);
            
//            if (null != luckyNumInfo && luckyNumInfo.containsKey(luckyNumPO.getTelnum()))
//            {
//                luckyNumPO.setSeltel_prepayfee(luckyNumInfo.get(luckyNumPO.getTelnum()).getSeltel_prepayfee());
//            }
            
            serverNumList.add(luckyNumPO);
        }
            
        // 填冲不满一页的其它对象，防止页面变型
        if (page == pageCount)
        {
            for(int i=0;i<endRowNum-tmpList.size();i++)
            {
                // 写入空对象
                ServerNumPO po = new ServerNumPO();
                po.setTelnum("");
                po.setSeltel_prepayfee("");
                serverNumList.add(po);
            }
        }
        
        // 返回
        return "resultPage";
    }
    
    /**
     * 查询情侣号码列表
     * 
     * @return
     * @see 
     */
    public String qryLoverNums()
    {
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession(true);
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 根据region查询号码列表
        LoverNumPO po = new LoverNumPO();
        po.setCityID(terminalInfoPO.getRegion());
        
        loverNums = this.chooseTelService.qryLoverNum(po);
        
        // 记录日志
        if (loverNums != null && loverNums.size() > 0)
        {
            this.createRecLog("SHChooseTelNumQry", "0", "0", "0", "自助选号情侣号码查询成功.");
        }
        else
        {
            this.createRecLog("SHChooseTelNumQry", "0", "0", "1", "自助选号情侣号码查询失败.");
        }
            
        this.getRequest().setAttribute("recordCount", loverNums.size());
            
        pageviewnum = 20;
        
        if (pageviewnum < loverNums.size())
        {
            pageFlag = "true";
        }
            
        // 结束行数
        if (page == 0)
        {
            page = 1;
        }
        int endRowNum = pageviewnum * page;
            
        // 开始条数
        int startRowNum = endRowNum - pageviewnum;
            
        // 总页数
        if (loverNums.size() % pageviewnum != 0)
        {
            pageCount = loverNums.size() / pageviewnum + 1;
        }
        else
        {
            pageCount = loverNums.size() / pageviewnum;
        }
        
        if (pageCount == 0)
        {
            pageCount = 1;
        }
            
        // 从内存中取得每页数据
        pageLoverNums = new ArrayList<LoverNumPO>();
        
        for (int i = startRowNum; i < endRowNum && i < loverNums.size(); i++)
        {
            pageLoverNums.add(loverNums.get(i));
        }
            
        // 填冲不满一页的其它对象，防止页面变型
        if (page == pageCount)
        {
            for(int i = 0; i < endRowNum - loverNums.size(); i++)
            {
                // 写入空对象
                LoverNumPO po1 = new LoverNumPO();
                po1.setMainNum("");
                po1.setSubsNum("");
                po1.setCost("");
                
                pageLoverNums.add(po1);
            }
        }
        
        return "loveTypePage";
    }
    
    /**
     * ABAB、AABB、ABBB模式号码列表查询
     * 
     * @return
     * @see 
     */
    public String qrySpecifiedPatternNums()
    {
        // 获取session
        HttpSession session = getRequest().getSession(true);
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 号码模式=网号+号段+尾数(不选任何规则情况下传11位下划线)
        phoneMode = "___________";
                
        // 号码页数(第几页)
        if (page == 0)
        {
            page = 1;
        }
        
        // 查询号码列表
        tmpList = this.chooseTelBean.querySpecifiedPatternNums(terminalInfoPO, curMenuId, phoneMode, telNumType);
        
        // 记录日志
        if (tmpList != null && tmpList.size() > 0)
        {
            this.createRecLog("SHChooseTelNumQry", "0", "0", "0", "自助选号(" + telNumType + ")查询成功");
        }
        else
        {
            this.createRecLog("SHChooseTelNumQry", "0", "0", "1", "自助选号(" + telNumType + ")查询失败");
        }
        
        this.getRequest().setAttribute("recordCount", tmpList.size());
        
        pageviewnum = 20;
        
        if (pageviewnum < tmpList.size())
        {
            pageFlag = "true";
        }
        
        // 结束行数
        int endRowNum = pageviewnum * page;
        
        // 开始条数
        int startRowNum = endRowNum - pageviewnum;
        
        // 总页数        
        if (tmpList.size() % pageviewnum != 0)
        {
            pageCount = tmpList.size() / pageviewnum + 1;
        }
        else
        {
            pageCount = tmpList.size() / pageviewnum;
        }
        
        if (pageCount == 0)
        {
            pageCount = 1;
        }
        
        // 从内存中取得每页数据
        serverNumList = new ArrayList<ServerNumPO>();
        for (int i = startRowNum; i < endRowNum && i < tmpList.size(); i++)
        {
            serverNumList.add((ServerNumPO)tmpList.get(i));
        }
        
        // 填冲不满一页的其它对象，防止页面变型
        if (page == pageCount)
        {
            for (int i = 0; i < endRowNum - tmpList.size(); i++)
            {
                // 写入空对象
                ServerNumPO po = new ServerNumPO();
                po.setTelnum("");
                po.setSeltel_prepayfee("");
                serverNumList.add(po);
            }
        }
        
        return "specifiedPatternNumPage";
    }
    

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getPhoneMode()
    {
        return phoneMode;
    }

    public void setPhoneMode(String phoneMode)
    {
        this.phoneMode = phoneMode;
    }

    public String getPurpose()
    {
        return purpose;
    }

    public void setPurpose(String purpose)
    {
        this.purpose = purpose;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getPageviewnum()
    {
        return pageviewnum;
    }

    public void setPageviewnum(int pageviewnum)
    {
        this.pageviewnum = pageviewnum;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public String getTelnum()
    {
        return telnum;
    }

    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }

    public String getPayfee()
    {
        return payfee;
    }

    public void setPayfee(String payfee)
    {
        this.payfee = payfee;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getIdCard()
    {
        return idCard;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    public String getNetNbr()
    {
        return netNbr;
    }

    public void setNetNbr(String netNbr)
    {
        this.netNbr = netNbr;
    }

    public String getNetValue()
    {
        return netValue;
    }

    public void setNetValue(String netValue)
    {
        this.netValue = netValue;
    }

    public String getFinalNbr()
    {
        return finalNbr;
    }

    public void setFinalNbr(String finalNbr)
    {
        this.finalNbr = finalNbr;
    }

    public String getBz()
    {
        return bz;
    }

    public void setBz(String bz)
    {
        this.bz = bz;
    }

    public List<ServerNumPO> getTmpList()
    {
        return tmpList;
    }

    public void setTmpList(List<ServerNumPO> tmpList)
    {
        this.tmpList = tmpList;
    }

    public List<ServerNumPO> getServerNumList()
    {
        return serverNumList;
    }

    public void setServerNumList(List<ServerNumPO> serverNumList)
    {
        this.serverNumList = serverNumList;
    }

    public List<NetNbrPO> getNetNbrTmpList()
    {
        return netNbrTmpList;
    }

    public void setNetNbrTmpList(List<NetNbrPO> netNbrTmpList)
    {
        this.netNbrTmpList = netNbrTmpList;
    }

    public List<NetNbrPO> getNetNbrList()
    {
        return netNbrList;
    }

    public void setNetNbrList(List<NetNbrPO> netNbrList)
    {
        this.netNbrList = netNbrList;
    }

    public List<NetValuePO> getNetValueTmpList()
    {
        return netValueTmpList;
    }

    public void setNetValueTmpList(List<NetValuePO> netValueTmpList)
    {
        this.netValueTmpList = netValueTmpList;
    }

    public List<NetValuePO> getNetValueList()
    {
        return netValueList;
    }

    public void setNetValueList(List<NetValuePO> netValueList)
    {
        this.netValueList = netValueList;
    }

    public ChooseTelBean getChooseTelBean()
    {
        return chooseTelBean;
    }

    public void setChooseTelBean(ChooseTelBean chooseTelBean)
    {
        this.chooseTelBean = chooseTelBean;
    }

    public ChooseTelService getChooseTelService()
    {
        return chooseTelService;
    }

    public void setChooseTelService(ChooseTelService chooseTelService)
    {
        this.chooseTelService = chooseTelService;
    }

    public String getSuccessBz()
    {
        return successBz;
    }

    public void setSuccessBz(String successBz)
    {
        this.successBz = successBz;
    }

    public String getCtime()
    {
        return ctime;
    }

    public void setCtime(String ctime)
    {
        this.ctime = ctime;
    }

    public String getCoid()
    {
        return coid;
    }

    public void setCoid(String coid)
    {
        this.coid = coid;
    }

    public String getCremind()
    {
        return cremind;
    }

    public void setCremind(String cremind)
    {
        this.cremind = cremind;
    }

    public String getLocus()
    {
        return locus;
    }

    public void setLocus(String locus)
    {
        this.locus = locus;
    }

    public String getPayfeeY()
    {
        return payfeeY;
    }

    public void setPayfeeY(String payfeeY)
    {
        this.payfeeY = payfeeY;
    }

	public String getInitBz()
	{
		return initBz;
	}

	public void setInitBz(String initBz)
	{
		this.initBz = initBz;
	}

    public String getPageFlag()
    {
        return pageFlag;
    }

    public void setPageFlag(String pageFlag)
    {
        this.pageFlag = pageFlag;
    }

    public String getTelNumType()
    {
        return telNumType;
    }

    public void setTelNumType(String telNumType)
    {
        this.telNumType = telNumType;
    }

    public List<DictItemPO> getLuckyNumRules()
    {
        return luckyNumRules;
    }

    public void setLuckyNumRules(List<DictItemPO> luckyNumRules)
    {
        this.luckyNumRules = luckyNumRules;
    }

    public String getLuckyNumRule()
    {
        return luckyNumRule;
    }

    public void setLuckyNumRule(String luckyNumRule)
    {
        this.luckyNumRule = luckyNumRule;
    }

    public List<LoverNumPO> getPageLoverNums()
    {
        return pageLoverNums;
    }

    public void setPageLoverNums(List<LoverNumPO> pageLoverNums)
    {
        this.pageLoverNums = pageLoverNums;
    }
    
    public List<LoverNumPO> getLoverNums()
    {
        return loverNums;
    }

    public void setLoverNums(List<LoverNumPO> loverNums)
    {
        this.loverNums = loverNums;
    }

    public String getLoverNumFlag()
    {
        return loverNumFlag;
    }

    public void setLoverNumFlag(String loverNumFlag)
    {
        this.loverNumFlag = loverNumFlag;
    }

    public String getAlertMsg()
    {
        return alertMsg;
    }

    public void setAlertMsg(String alertMsg)
    {
        this.alertMsg = alertMsg;
    }
}
