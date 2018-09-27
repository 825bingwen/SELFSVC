/*
 * 文件名：MonthFeeAction.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增，月账单查询对应的action类
 */
package com.customize.sd.selfsvc.feeService.action;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;

import com.customize.sd.selfsvc.bean.MonthFeeBean;
import com.customize.sd.selfsvc.feeService.model.BillPO;
import com.customize.sd.selfsvc.feeService.model.CyclePO;
import com.customize.sd.selfsvc.feeService.model.FeeGroupPO;
import com.customize.sd.selfsvc.feeService.model.FeePO;
import com.customize.sd.selfsvc.feeService.model.FeedetailPO;
import com.customize.sd.selfsvc.feeService.model.PkgPO;
import com.gmcc.boss.common.base.CEntityString;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MarketingEventThread;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.SendMailUtil;

/**
 * 月账单查询 
 * 
 * @author xkf29026
 * 
 */
public class MonthFeeAction extends BaseAction
{
    private static Log logger = LogFactory.getLog(MonthFeeAction.class);
    
    private static final long serialVersionUID = -3950351941316700610L;
    
    private transient MonthFeeBean monthFeeBean = null;
    
    private String curMenuId = "";
    
    private String month = "";
    
    private String billcycle;
    
    private String telnum;
    
    private String custName;
    
    private String brandID;
    
    private String needCheckMail; // 是否需要对用户已经开通手机邮箱进行检测.1,需要；0,不需要
    
    // add begin cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    private String billAddInfor; // 账单备注信息
    
    // add end cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    
    // 账期列表
    private List<CyclePO> cycleList = new ArrayList<CyclePO>();
    
    // ---------------------帐期信息--------------------------------------------------------------
    
    // 帐期，分散帐期多帐期格式YYYYMMDD，自然月帐期格式YYYYMM
    private String cycle;
    
    // 开始时间，格式:YYYYMMDD
    private String startdate;
    
    // 结束时间，格式:YYYYMMDD
    private String enddate;
    
    // 主账号
    private String acctid;
    
    // 是否合户用户。1，是；0，不是
    private String unionacct;
    
    // --------------------客户信息----------------------------------------------------------------
    
    // 客户名称
    private String custname;
    
    // 品牌
    private String brandnm;
    
    // 主体产品
    private String productnm;
    
    // 用户ID
    private String subsid;
    
    // 客户星级
    private String subsCreditName;
    
    // --------------------新版账单信息-------------------------------------------------------------------------
    
    // 新版账单
    Map map;
    
    // 历史/实时
    private String realbz;
    
    // newstatus 0:老流程 1:新流程
    private String newflow;
    
    // 0:测试 1:生产
    private String cs = "1";
    
    /**
     * 查询账单时的当前时间。 格式："yyyy年MM月dd日HH时mm分"
     */
    private String curdate;
    
    /**
     * 查询近六个月月份
     * 
     * @return
     * @throws Exception
     */
    public String qryCurrentMonthBill() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCurrentMonthBill Starting ...");
        }
        
        // 可查询当前月和前五个月的账单信息
        String pattern = "yyyyMM";
        this.setMonth(CommonUtil.getLastMonth(pattern, 0));
        String month1 = CommonUtil.getLastMonth(pattern, 1);
        String month2 = CommonUtil.getLastMonth(pattern, 2);
        String month3 = CommonUtil.getLastMonth(pattern, 3);
        String month4 = CommonUtil.getLastMonth(pattern, 4);
        String month5 = CommonUtil.getLastMonth(pattern, 5);
        
        HttpServletRequest request = this.getRequest();
        request.setAttribute("month", month);
        request.setAttribute("month1", month1);
        request.setAttribute("month2", month2);
        request.setAttribute("month3", month3);
        request.setAttribute("month4", month4);
        request.setAttribute("month5", month5);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCurrentMonthBill End");
        }
        
        return "qryCurrentMonthBill";
    }
    
    /**
     * 查询非当前月份的账单信息 <功能详细描述>
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String qryMonthBill() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryMonthBill Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        
        request.setAttribute("backStep", "-1");
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
        ReturnWrap rw = monthFeeBean.qryMonthBill(customerSimp.getServNumber(),
                terminalInfo,
                month,
                curMenuId,
                billcycle,
                customerSimp.getVerifyCode());
        
        // modify begin cKF48754 2011/12/02 R003C11L10n01 OR_SD_201110_598
        // 查询账单备注
        ReturnWrap addInfoRw = monthFeeBean.queryBillAddInfo(customerSimp.getServNumber(),
                terminalInfo,
                month,
                curMenuId,
                customerSimp.getVerifyCode());
        // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS && addInfoRw != null
                && addInfoRw.getStatus() == SSReturnCode.SUCCESS)
        // modify end cKF48754 2011/12/02 R003C11L10n01 OR_SD_201110_598
        {
            CRSet billData = (CRSet)rw.getReturnObject();
            
            String startDate = "";
            String endDate = "";
            CRSet brief = new CRSet(billData.GetColumnCount()); // 摘要信息
            CRSet billfixed = new CRSet(billData.GetColumnCount()); // 账单基本信息
            CRSet billflex = new CRSet(billData.GetColumnCount()); // 账单费用信息
            CRSet acct = new CRSet(billData.GetColumnCount()); // 帐户信息
            CRSet score = new CRSet(billData.GetColumnCount()); // 积分信息
            CRSet sp = new CRSet(billData.GetColumnCount()); // 代收费信息
            
            // add begin jWX216858 2014/08/14 V200R003C10LG0801 OR_huawei_201408_93 圈复杂度_自助终端（二阶段）
            this.parseBill(billData, brief, billfixed, billflex, acct, score, sp);
            // add end jWX216858 2014/08/14 V200R003C10LG0801 OR_huawei_201408_93 圈复杂度_自助终端（二阶段）
            request.setAttribute("brief", brief);
            request.setAttribute("billfixed", billfixed);
            request.getSession().setAttribute("billflex", billflex);
            request.setAttribute("acct", acct);
            request.setAttribute("score", score);
            request.setAttribute("sp", sp);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            
            request.setAttribute("printFlag", "1");
            
            // add begin cKF48754 2011/11/25 R003C11L10n01 OR_SD_201110_598
            CTagSet tagSet = (CTagSet)addInfoRw.getReturnObject();
            
            // 账单备注信息
            billAddInfor = tagSet.GetValue("remark");
            
            billAddInfor = billAddInfor.replace("\n", "<br>");
            // add end cKF48754 2011/11/25 R003C11L10n01 OR_SD_201110_598
            
            this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "0", "查询" + month + "月份的账单信息成功");
            
            if (logger.isInfoEnabled())
            {
                logger.info("用户" + customerSimp.getServNumber() + "的" + month + "月份的账单信息查询成功");
            }
        }
        else
        {
            request.setAttribute("errormessage", "未查询到" + month + "月份的账单信息");
            
            this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "1", "未查询到" + month + "月份的账单信息");
            
            logger.error("未查询到用户" + customerSimp.getServNumber() + "的" + month + "月份的账单信息");
            
            return "error";
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryMonthBill End");
        }
        
        return "qryMonthBill";
    }
    
    /**
     * 解析账单数据
     * 
     * @param billData
     * @param brief 摘要信息
     * @param billfixed 账单基本信息
     * @param billflex 账单费用信息
     * @param acct 帐户信息
     * @param score 积分信息
     * @param sp 代收费信息
     * @remark create by jWX216858 2014/08/14 V200R003C10LG0801 OR_huawei_201408_93 圈复杂度_自助终端（二阶段）
     */
    private void parseBill(CRSet billData, CRSet brief, CRSet billfixed, CRSet billflex, CRSet acct, CRSet score,
            CRSet sp)
    {
        int rowCnt = billData.GetRowCount();
        int colCnt = billData.GetColumnCount();
        int briefCnt = 0;
        int infoCnt = 0;
        int feeCnt = 0;
        int acctCnt = 0;
        int scoreCnt = 0;
        int spCnt = 0;
        
        for (int i = 0; i < rowCnt; i++)
        {
            if ("brief".equals(billData.GetValue(i, 1)))
            {
                brief.AddRow();
                for (int j = 0; j < colCnt; j++)
                {
                    brief.SetValue(briefCnt, j, billData.GetValue(i, j));
                }
                briefCnt++;
            }
            else if ("billfixed".equals(billData.GetValue(i, 1)))
            {
                billfixed.AddRow();
                for (int j = 0; j < colCnt; j++)
                {
                    billfixed.SetValue(infoCnt, j, billData.GetValue(i, j));
                }
                infoCnt++;
            }
            else if ("billflex".equals(billData.GetValue(i, 1)))
            {
                billflex.AddRow();
                for (int j = 0; j < colCnt; j++)
                {
                    billflex.SetValue(feeCnt, j, billData.GetValue(i, j));
                }
                feeCnt++;
            }
            else if ("acct".equals(billData.GetValue(i, 1)))
            {
                acct.AddRow();
                for (int j = 0; j < colCnt; j++)
                {
                    acct.SetValue(acctCnt, j, billData.GetValue(i, j));
                }
                acctCnt++;
            }
            else if ("score".equals(billData.GetValue(i, 1)))
            {
                score.AddRow();
                for (int j = 0; j < colCnt; j++)
                {
                    score.SetValue(scoreCnt, j, billData.GetValue(i, j));
                }
                scoreCnt++;
            }
            else if ("sp".equals(billData.GetValue(i, 1)))
            {
                sp.AddRow();
                for (int j = 0; j < colCnt; j++)
                {
                    sp.SetValue(spCnt, j, billData.GetValue(i, j));
                }
                spCnt++;
            }
        }
    }
    
    /**
     * 账单寄送
     * 
     * @return 1:发送成功；2:未开通139邮箱
     */
    public void sendmail() throws IOException
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("MonthFeeAction - sendmail Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        this.getResponse().setContentType("text/html;charset=GBK");
        
        // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 账单寄送内容
        String mailContent = "";
        
        ReturnWrap rw = monthFeeBean.qryMonthBill(telnum,
                terminalInfo,
                month,
                curMenuId,
                billcycle,
                customerSimp.getVerifyCode());
        // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet billData = (CRSet)rw.getReturnObject();
            
            CRSet brief = new CRSet(billData.GetColumnCount()); // 摘要信息
            CRSet billfixed = new CRSet(billData.GetColumnCount()); // 账单基本信息
            CRSet billflex = new CRSet(billData.GetColumnCount()); // 帐单费用信息
            CRSet acct = new CRSet(billData.GetColumnCount()); // 帐户信息
            CRSet score = new CRSet(billData.GetColumnCount()); // 积分信息
            CRSet sp = new CRSet(billData.GetColumnCount()); // 代收费信息
            
            // add begin jWX216858 2014/08/14 V200R003C10LG0801 OR_huawei_201408_93 圈复杂度_自助终端（二阶段）
            this.parseBill(billData, brief, billfixed, billflex, acct, score, sp);
            // add end jWX216858 2014/08/14 V200R003C10LG0801 OR_huawei_201408_93 圈复杂度_自助终端（二阶段）
            
            // 此处拼凑发送内容
            mailContent = getMailContent(telnum, custName, brandID, brief, billfixed, billflex, acct, score, sp);
        }
        
        String mailFlag = "";
        
        // 账单发送时间
        int time = 0;
        if ("1".equals(needCheckMail))
        {
            /**
             * 判断用户是否已经开通手机邮箱，用户已经开通手机邮箱，则进行发送邮件。
             */
            mailFlag = monthFeeBean.qrymailBox(telnum, terminalInfo, curMenuId);
        }
        else
        {
            mailFlag = "1";
            time = Integer.parseInt((String)PublicCache.getInstance().getCachedData("SH_MAIL_DELAY"));
        }
        
        PrintWriter out = this.getResponse().getWriter();
        if ("1".equals(mailFlag))
        {
            Timer timer = new Timer();
            timer.schedule(new SendMailUtil(telnum + "@139.com", "您" + month + "月份的话费账单已到，请查收。", mailContent),
                    new Date(System.currentTimeMillis() + time * 60L * 1000));
            
            if ("1".equals(needCheckMail))
            {
                out.write("1");
                out.flush();
            }
        }
        /**
         * 如果用户未开通，则提示用户进行开通免费139邮箱。对于开通免费139邮箱的，稍后几分钟发送邮件。
         */
        else
        {
            // 用户没有开通手机邮箱
            out.write("2");
            out.flush();
        }
    }
    
    /**
     * 构造发送账单内容
     * 
     * @param brief
     * @param billfixed
     * @param billflex
     * @param acct
     * @param score
     * @param sp
     * @return
     */
    public String getMailContent(String telnum, String custName, String brandID, CRSet brief, CRSet billfixed,
            CRSet billflex, CRSet acct, CRSet score, CRSet sp)
    {
        // 根据不同品牌显示不同的提示信息
        String logoTitle = "";
        if ("BrandGotone".equals(brandID))
        {
            logoTitle = "1";
        }
        else if ("BrandMzone".equals(brandID))
        {
            logoTitle = "2";
        }
        else
        // if ("BrandSzx".equals(brandID))
        {
            logoTitle = "3";
        }
        
        // 显示客户信息
        String startDate = CommonUtil.getBillValueByKey(billfixed, "startdate");
        String endDate = CommonUtil.getBillValueByKey(billfixed, "enddate");
        
        String custItem = "";
        custItem += "<tr><td width=\"13%\" rowspan=\"2\"></td><td style=\"width: 34%\"><span class=\"title02\">客户名称：</span>"
                + custName + "</td>";
        custItem += "<td width=\"35%\"><span class=\"title02\">手机号码：</span>" + telnum
                + "</td><td width=\"23%\" rowspan=\"2\"></td></tr>";
        custItem += "<tr><td style=\"width: 34%\"><span class=\"title02\">计费周期：</span>" + startDate.substring(0, 4)
                + "年" + startDate.substring(4, 6) + "月" + startDate.substring(6, 8) + "日至" + endDate.substring(0, 4)
                + "年" + endDate.substring(4, 6) + "月" + endDate.substring(6, 8) + "日</td></tr>";
        
        // 费用信息开始
        String billContent1 = "";
        String billContent2 = "";
        StringBuffer sbuf1 = new StringBuffer(billContent1);
        StringBuffer sbuf2 = new StringBuffer(billContent2);
        String billTotalFee = "";
        if (null != billflex && 0 != billflex.GetRowCount())
        {
            int count = billflex.GetRowCount();
            int num = count / 2;
            if (count / 2 == 1)
            {
                num += 1;
            }
            for (int i = 0; i < num; i++)
            {
                sbuf1.append("<tr>").append(toStrong(billflex, i)).append("</tr>");
                if (i + num < count - 1)
                {
                    sbuf2.append("<tr>").append(toStrong(billflex, i + num)).append("</tr>");
                    
                }
            }
            billContent1 = sbuf1.toString();
            billContent2 = sbuf2.toString();
            billTotalFee += "<tr><td colspan=\"2\" valign=\"top\"   class=\"paddingLeft12 bold bgEAEAEA\"><span class=\"STYLE18\">费用合计： "
                    + billflex.GetValue(billflex.GetRowCount() - 1, 3) + "元</span></td></tr>";
        }
        
        // 账户积分信息开始
        String acctItem = "";
        if (null != acct && 0 != acct.GetRowCount())
        {
            // modify begin l00190940 2011-11-25 不显示单位、OR_SD_201110_384
            acctItem += "<tr><td>本月初结余</td><td align='right'>"
                    + fomartStrNoUnitSigned(CommonUtil.getBillValueByKey(acct, "lastval")) + "</tr>"
                    + "<tr><td>退费</td><td align='right'>"
                    + fomartStrNoUnitSigned(CommonUtil.getBillValueByKey(acct, "backfee")) + "</tr>"
                    + "<tr><td>过户转出余额</td><td align='right'>"
                    + fomartStrNoUnitSigned(CommonUtil.getBillValueByKey(acct, "transferout")) + "</tr>"
                    + "<tr><td>过户转入余额</td><td align='right'>"
                    + fomartStrNoUnitSigned(CommonUtil.getBillValueByKey(acct, "transferin")) + "</td></tr>"
                    + "<tr><td >协议款分月返还</td><td align='right'>"
                    + fomartStrNoUnitSigned(CommonUtil.getBillValueByKey(acct, "contractused")) + "</td></tr>"
                    + "<tr><td>本月累计已交费</td><td align='right'>"
                    + fomartStrNoUnitSigned(CommonUtil.getBillValueByKey(acct, "income")) + "</td></tr>"
                    + "<tr><td>费用合计</td><td align='right'>"
                    + fomartStrNoUnitSigned(CommonUtil.getBillValueByKey(acct, "totalfee")) + "</td></tr>"
                    + "<tr><td>违约金</td><td align='right'>"
                    + fomartStrNoUnitSigned(CommonUtil.getBillValueByKey(acct, "latefee")) + "</td></tr>"
                    + "<tr><td class='title02'>本月末结余</td><td class='title02' align='right'>"
                    + fomartStrNoUnitSigned(CommonUtil.getBillValueByKey(acct, "thisval")) + "</td></tr>"
                    + "<tr><td>赠送费剩余额</td><td align='right'>"
                    + fomartStrNoUnitSigned(CommonUtil.getBillValueByKey(acct, "presentval")) + "</td></tr>"
                    + "<tr><td>协议款剩余额</td><td align='right'>"
                    + fomartStrNoUnitSigned(CommonUtil.getBillValueByKey(acct, "contractval")) + "</td></tr>";
            // modify end l00190940 2011-11-25 不显示单位
        }
        
        String scoreItem = "";
        if (null != score && 0 != score.GetRowCount())
        {
            scoreItem += "<tr><td>上月末剩余可兑换积分</td><td align='right'>"
                    + fomartStr(CommonUtil.getBillValueByKey(score, "lastval"), "分") + "</td></tr>"
                    + "<tr><td>本月新增积分</td><td align='right'>"
                    + fomartStr(CommonUtil.getBillValueByKey(score, "income"), "分") + "</td></tr>"
                    + "<tr><td>其中：消费积分</td><td align='right'>"
                    + fomartStr(CommonUtil.getBillValueByKey(score, "consume"), "分") + "</td></tr>"
                    + "<tr><td>其中：在网时间奖励积分</td><td align='right'>"
                    + fomartStr(CommonUtil.getBillValueByKey(score, "innet"), "分") + "</td></tr>"
                    + "<tr><td>其中：其他奖励积分</td><td align='right'>"
                    + fomartStr(CommonUtil.getBillValueByKey(score, "otheraward"), "分") + "</td></tr>"
                    + "<tr><td >本月已兑换积分</td><td align='right'>"
                    + fomartStr(CommonUtil.getBillValueByKey(score, "thisused"), "分") + "</td></tr>"
                    + "<tr><td>本月末剩余可兑换积分</td><td align='right'>"
                    + fomartStr(CommonUtil.getBillValueByKey(score, "thisval"), "分") + "</td></tr>"
                    + "<tr><td>年底将清零积分</td><td align='right'>"
                    + fomartStr(CommonUtil.getBillValueByKey(score, "resetval"), "分") + "</td></tr>"
                    + "<tr><td>&nbsp;</td><td >&nbsp;</td></tr>" + "<tr><td>&nbsp;</td><td >&nbsp;</td></tr>";
        }
        
        // 代收信息费明细开始
        String spItem = "";
        // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端
        spItem = qrySpItem(sp, spItem);
        // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端
        
        // modify begin by xkf29026 2011/10/6 modify "+=" to "append"
        StringBuffer mailContent = new StringBuffer(2048);
        mailContent.append("<html>");
        mailContent.append("<head>");
        mailContent.append("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\">");
        mailContent.append("    <style type=\"text/css\" media=\"screen\"><!--");
        mailContent.append("        body {");
        mailContent.append("            background-color:#ffffff;");
        mailContent.append("            font-size:14px;");
        mailContent.append("            font-family:'';");
        mailContent.append("            overflow-x:auto;");
        mailContent.append("            overflow-y:hidden;");
        mailContent.append("        }");
        mailContent.append("    //--></style>");
        mailContent.append("    <base target=\"_blank\" />");
        mailContent.append("    <title></title>");
        mailContent.append("    <script type=\"text/javascript\">");
        mailContent.append("        document.domain = window.location.host.substring(window.location.host.substring(0,window.location.host.lastIndexOf(\".\")).lastIndexOf(\".\")+1);");
        mailContent.append("    </script>");
        mailContent.append("</head>");
        mailContent.append("<body>");
        mailContent.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        mailContent.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        mailContent.append("<head>");
        mailContent.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" />");
        mailContent.append("<title>中国移动通信客户账单</title>");
        mailContent.append("<style type=\"text/css\">");
        mailContent.append("");
        mailContent.append("body, td, th {");
        mailContent.append("    font-family:\"宋体\", Arial, Helvetica, sans-serif;");
        mailContent.append("    font-size: 12px;");
        mailContent.append("    color: #333333;");
        mailContent.append("    line-height:21px;");
        mailContent.append("}");
        mailContent.append("body {");
        mailContent.append("    background-color: #CCCCCC;");
        mailContent.append("    margin-left: 0px;");
        mailContent.append("    margin-top: 0px;");
        mailContent.append("    margin-right: 0px;");
        mailContent.append("    margin-bottom: 0px;");
        mailContent.append("}");
        mailContent.append(".padding5 {");
        mailContent.append("    padding:5px;");
        mailContent.append("}");
        mailContent.append(".STYLE14 {");
        mailContent.append("    color: #336600;");
        mailContent.append("}");
        mailContent.append(".paddingLeftRight35 {");
        mailContent.append("    padding:15px 35px;");
        mailContent.append("}");
        mailContent.append(".bold {");
        mailContent.append("    font-weight:bold");
        mailContent.append("}");
        mailContent.append(".paddingLeft12 {");
        mailContent.append("    padding-left:12px;");
        mailContent.append("}");
        mailContent.append(".paddingLeft25 {");
        mailContent.append("    padding-left:25px;");
        mailContent.append("}");
        mailContent.append(".red {");
        mailContent.append("    color:#f00");
        mailContent.append("}");
        mailContent.append(".title01 {");
        mailContent.append("    background:#EAEAEA;");
        mailContent.append("    border-bottom:solid 1px #666666;");
        mailContent.append("    font-weight:bold");
        mailContent.append("}");
        mailContent.append(".title02 {");
        mailContent.append("    color:#006699;");
        mailContent.append("    font-weight:bold");
        mailContent.append("}");
        mailContent.append(".content td {");
        mailContent.append("    padding-left:12px;");
        mailContent.append("}");
        mailContent.append(".margintop10 {");
        mailContent.append("    margin-top:10px");
        mailContent.append("}");
        mailContent.append(".STYLE92 {");
        mailContent.append("    color: #006699;");
        mailContent.append("}");
        mailContent.append(".STYLE21 {");
        mailContent.append("    color: #CC3300;");
        mailContent.append("}");
        mailContent.append(".STYLE7 {");
        mailContent.append("    font-size: 12px;");
        mailContent.append("    line-height: 20px;");
        mailContent.append("    font-weight: bold;");
        mailContent.append("    color: #006699;");
        mailContent.append("}");
        mailContent.append(".bgWhite{ background:#fff;}");
        mailContent.append(".bg666{ background:#666;}");
        mailContent.append(".bgEAEAEA{ background:#EAEAEA}");
        mailContent.append("a img{ border:none} ");
        mailContent.append("");
        mailContent.append("</style>");
        mailContent.append("</head>");
        mailContent.append("<body  style=\"overflow:scroll\">");
        mailContent.append("<table width=\"760\" height=\"257\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
        mailContent.append("  <tr>");
        mailContent.append("    <td colspan=\"2\"><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/index_title")
                .append(logoTitle)
                .append(".jpg\" width=\"760\" height=\"67\" alt=\"\" /></td>");
        mailContent.append("  </tr>");
        mailContent.append("  <tr>");
        mailContent.append("    <td colspan=\"2\"><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_02.gif\" width=\"760\" height=\"55\" alt=\"\" /></td>");
        mailContent.append("  </tr>");
        mailContent.append("  <tr>");
        mailContent.append("    <td><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_03.gif\" width=\"759\" height=\"58\" alt=\"\" /></td>");
        mailContent.append("    <td><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_04.gif\" width=\"1\" height=\"58\" alt=\"\" /></td>");
        mailContent.append("  </tr>");
        mailContent.append("  <tr>");
        mailContent.append("    <td width=\"760\" height=\"42\" colspan=\"2\" background=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_05.gif\">");
        mailContent.append("        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
        mailContent.append(custItem);
        mailContent.append("      </table>");
        mailContent.append("    </td>");
        mailContent.append("  </tr>");
        mailContent.append("  <tr>");
        mailContent.append("    <td colspan=\"2\"><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_06.gif\" width=\"760\" height=\"35\" alt=\"\" /></td>");
        mailContent.append("  </tr>");
        mailContent.append("</table>");
        mailContent.append("<table width=\"760\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\">");
        mailContent.append("  <tr>");
        mailContent.append("    <td width=\"540\" valign=\"top\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
        mailContent.append("        <tr>");
        mailContent.append("          <td background=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_07.gif\" class=\"paddingLeftRight35\">");
        mailContent.append("            <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"1\"  class=\"bg666\">");
        mailContent.append("              <tr>");
        mailContent.append("                <td colspan=\"2\" align=\"center\"  class=\"bold bgWhite\">费用信息（单位：元）</td>");
        mailContent.append("              </tr>");
        mailContent.append("              <tr>");
        mailContent.append("                <td width=\"33%\" valign=\"top\" class=\"bgWhite\">");
        mailContent.append("                    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"content\">");
        mailContent.append("                    <tr>");
        mailContent.append("                      <td class=\"title01 paddingLeft12\" style=\"width: 65%\">费用项目</td>");
        mailContent.append("                      <td  width=\"35%\" class=\"title01\"><strong>费用</strong>金额</td>");
        mailContent.append("                    </tr>");
        mailContent.append(billContent1);
        mailContent.append("                  </table>");
        mailContent.append("                </td>");
        mailContent.append("                <td width=\"33%\" valign=\"top\" class=\"bgWhite\">");
        mailContent.append("                    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"content\">");
        mailContent.append("                    <tr>");
        mailContent.append("                      <td class=\"title01 paddingLeft12\" style=\"width: 65%\">费用项目</td>");
        mailContent.append("                      <td  width=\"35%\" class=\"title01\"><strong>费用</strong>金额</td>");
        mailContent.append("                    </tr>");
        mailContent.append(billContent2);
        mailContent.append("                  </table>");
        mailContent.append("                </td>");
        mailContent.append("              </tr>");
        mailContent.append(billTotalFee);
        mailContent.append("            </table>");
        mailContent.append("            <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"margintop10\" >");
        mailContent.append("              <tr>");
        mailContent.append("                <td width=\"50%\" valign=\"top\">");
        mailContent.append("                    <table width=\"98%\" border=\"0\" cellpadding=\"0\" cellspacing=\"1\"  class=\"bg666\">");
        mailContent.append("                    <tr>");
        mailContent.append("                      <td align=\"center\"   class=\"bold bgWhite\">账单信息</td>");
        mailContent.append("                    </tr>");
        mailContent.append("                    <tr>");
        mailContent.append("                      <td width=\"33%\" valign=\"top\" class=\"bgWhite\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"content\">");
        mailContent.append("                          <tr>");
        mailContent.append("                            <td class=\"title01 paddingLeft12\" style=\"width: 64%\">账单项目</td>");
        mailContent.append("                            <td width=\"42%\" class=\"title01\">金额/元</td>");
        mailContent.append("                          </tr>");
        mailContent.append(acctItem);
        mailContent.append("                        </table>");
        mailContent.append("                      </td>");
        mailContent.append("                    </tr>");
        mailContent.append("                  </table>");
        mailContent.append("                </td>");
        mailContent.append("                <td width=\"50%\" valign=\"top\"><table width=\"98%\" border=\"0\" align=\"right\" cellpadding=\"0\" cellspacing=\"1\"  class=\"bg666\">");
        mailContent.append("                    <tr>");
        mailContent.append("                      <td align=\"center\"  class=\"bold bgWhite\">积分信息</td>");
        mailContent.append("                    </tr>");
        mailContent.append("                    <tr>");
        mailContent.append("                      <td width=\"33%\" valign=\"top\" class=\"bgWhite\">");
        mailContent.append("                        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"content\">");
        mailContent.append("                          <tr>");
        mailContent.append("                            <td class=\"title01 paddingLeft12\" style=\"width: 65%\">积分项目</td>");
        mailContent.append("                            <td width=\"35%\" class=\"title01\">积分值</td>");
        mailContent.append("                          </tr>");
        mailContent.append(scoreItem);
        mailContent.append("                        </table>");
        mailContent.append("                      </td>");
        mailContent.append("                    </tr>");
        mailContent.append("                  </table>");
        mailContent.append("                </td>");
        mailContent.append("              </tr>");
        mailContent.append("            </table>");
        mailContent.append("            <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"1\"  class=\"margintop10 bg666\">");
        mailContent.append("              <tr>");
        mailContent.append("                <td width=\"66%\" colspan=\"3\" align=\"center\" class=\"bgWhite\"><span class=\"bold\">代收信息费明细</span></td>");
        mailContent.append("              </tr>");
        mailContent.append("              <tr>");
        mailContent.append("                <td colspan=\"3\" class=\"bgWhite\"><table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
        mailContent.append("                    <tr class=\"bgEAEAEA\">");
        mailContent.append("                      <td width=\"13%\" align=\"center\"  class=\"title01\">服务商（SP）</td>");
        mailContent.append("                      <td width=\"10%\" align=\"center\"  class=\"title01\">服务代码</td>");
        mailContent.append("                      <td width=\"13%\" align=\"center\"  class=\"title01\">订购业务名称</td>");
        mailContent.append("                      <td width=\"12%\" align=\"center\"  class=\"title01\">费用/元</td>");
        mailContent.append("                    </tr>");
        mailContent.append(spItem);
        mailContent.append("                  </table>");
        mailContent.append("                </td>");
        mailContent.append("              </tr>");
        mailContent.append("            </table>");
        mailContent.append("            <table width=\"98%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"margintop10\">");
        mailContent.append("              <tr>");
        mailContent.append("                <td width=\"11%\" align=\"right\" valign=\"top\" class=\"STYLE13\">备注：</td>");
        mailContent.append("                <td width=\"89%\"><p class=\"STYLE21\">1、账单的相关数据仅作参考，实际交费数据以中国移动通信集团山东有限公司的收费凭证为准。<br />");
        mailContent.append("                  2、由于该邮件不具备回复功能，请不要回复本邮件，如您有建议或意见，请拨打客户服务热线10086反映。<br />");
        mailContent.append("                  <span lang=\"EN-US\" xml:lang=\"EN-US\">3</span>、欢迎全球通用户登录<span lang=\"EN-US\" xml:lang=\"EN-US\"><a href=\"http://jf.10086.cn/\" target=\"_blank\">jf.10086.cn</a></span>畅享好礼兑换。<br />");
        mailContent.append("                  <br />");
        mailContent.append("                  客服热线：10086 网址：<a href=\"http://sd.10086.cn/\">sd.10086.cn</a><br />");
        mailContent.append("如果您以后不想再收到此账单，请登录山东移动网站，在“<a href=\"http://www.sd.10086.cn/newecare/common/index.jsp?menuID=sendEmail&randnum=9354.912194773477\">网上营业厅－话费服务－定制话费信息</a>”页面退订该账单。<br /><span lang=\"EN-US\" xml:lang=\"EN-US\">4</span>、山东移动温馨提示：手机访问<span lang=\"EN-US\" xml:lang=\"EN-US\"><U><a href=\"wap.sd.10086.cn\" target=\"_blank\">wap.sd.10086.cn</a></U></span>掌上营业厅，随时随地查询话费、办理业务，免<span lang=\"EN-US\" xml:lang=\"EN-US\">GPRS</span>流量费（接入点为<span lang=\"EN-US\" xml:lang=\"EN-US\">cmwap</span>），欢迎使用！<br />");
        mailContent.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;中国移动通信集团山东有限公司 敬启</p>");
        mailContent.append("                </td>");
        mailContent.append("              </tr>");
        mailContent.append("              <tr>");
        mailContent.append("                <td colspan=\"2\" align=\"left\" valign=\"top\" class=\"STYLE13\"><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/logo.jpg\" width=\"102\" height=\"33\" /></td>");
        mailContent.append("              </tr>");
        mailContent.append("            </table></td>");
        mailContent.append("        </tr>");
        mailContent.append("        <tr>");
        mailContent.append("          <td><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_09.gif\" width=\"540\" height=\"18\" /></td>");
        mailContent.append("        </tr>");
        mailContent.append("      </table></td>");
        mailContent.append("    <td width=\"220\" valign=\"top\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
        mailContent.append("      <tr>");
        mailContent.append("        <td><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_08.gif\" width=\"220\" height=\"49\" /></td>");
        mailContent.append("      </tr>");
        mailContent.append("      ");
        mailContent.append("    </table>");
        mailContent.append("      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\" style=\"border:solid 2px #fff; \">");
        mailContent.append("      <tr>");
        mailContent.append("        <td height=\"140\"><a id=\"139Command_LinksShow\" href=\"http://mail.10086.cn\" rel=\"homemail\" clicklog=\"true\" thingid=\"80001\">");
        mailContent.append("<img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/205133.jpg\" width=\"205\" height=\"133\" /></a></td>");
        mailContent.append("      </tr>");
        mailContent.append("      <tr>");
        mailContent.append("        <td height=\"140\"><a id=\"139Command_LinksShow\" href=\"http://mail.10086.cn\" rel=\"greetingcard\" clicklog=\"true\" thingid=\"80002\"><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/huodong1.jpg\" width=\"205\" height=\"133\" /></a></td>");
        mailContent.append("      </tr>");
        mailContent.append("      <tr>");
        mailContent.append("        <td eight=\"140\"><a id=\"139Command_LinksShow\" href=\"http://mail.10086.cn\" rel=\"weblink\" clicklog=\"true\" thingid=\"80003\"><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/wlsq.jpg\" width=\"205\" height=\"133\" /></a></td>");
        mailContent.append("      </tr>");
        mailContent.append("      <tr>");
        mailContent.append("        <td height=\"140\"><a id=\"139Command_LinksShow\" href=\"http://mail.10086.cn\" rel=\"calendar\" clicklog=\"true\" thingid=\"80006\">");
        mailContent.append("");
        mailContent.append("<img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/rctx5.jpg\" width=\"205\" height=\"133\" /></a></td>");
        mailContent.append("      </tr>");
        mailContent.append("    </table></td>");
        mailContent.append("  </tr>");
        mailContent.append("</table>");
        mailContent.append("<table width=\"760\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" background=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_109.gif\">");
        mailContent.append("  <tr>");
        mailContent.append("    <td height=\"35\" align=\"center\" class=\"paddingLeft12\"><a href=\"http://www.miibeian.gov.cn/\"> ");
        mailContent.append("    京ICP备05002571号</a> 中国移动通信版权所有 客服电话：10086</td>");
        mailContent.append("  </tr>");
        mailContent.append("</table>");
        mailContent.append("<img width=0 height=0 src=\"http://interface.mail.10086.cn/UrlRedirect/UserReplay.aspx?BatchLot=1282011083&UserNumber=15864500526&OtherInfo=\" />");
        mailContent.append("</body>");
        mailContent.append("</html>");
        mailContent.append("");
        mailContent.append("</body>");
        mailContent.append("</html>");
        mailContent.append("<!-- CM 3.0.x -->");
        
        return mailContent.toString();
        // modify end by xkf29026 2011/10/6 modify "+=" to "append"
    }
    
    /**
     * 代收费明细 <功能详细描述>
     * 
     * @param sp
     * @param spItem
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String qrySpItem(CRSet sp, String spItem)
    {
        if (null != sp && sp.GetRowCount() > 0)
        {
            String[] strSP;
            BigDecimal fee = new BigDecimal("0");
            int spCnt = sp.GetRowCount();
            StringBuffer sbuf = new StringBuffer(spItem);
            for (int i = 0; i < spCnt - 1; i++)
            {
                if (i < 3)
                {
                    strSP = sp.GetValue(i, 2).split("  ");
                    
                    sbuf.append("<tr><td width=\"13%\" align=\"center\">")
                            .append(strSP[0])
                            .append("</td><td width=\"10%\" align=\"center\">")
                            .append(strSP[1])
                            .append("</td><td width=\"13%\" align=\"center\">")
                            .append(strSP[2])
                            .append("</td><td width=\"12%\" align=\"center\" >")
                            .append(sp.GetValue(i, 3))
                            .append("</td></tr>");
                }
                else
                {
                    fee = fee.add(new BigDecimal(sp.GetValue(i, 3)));
                }
            }
            spItem = sbuf.toString();
            if (fee.doubleValue() > 0)
            {
                
                spItem += "<tr><td width=\"13%\" align=\"center\">其它</td><td width=\"10%\" align=\"center\"></td><td width=\"13%\" align=\"center\"></td><td width=\"12%\" align=\"center\">"
                        + fee.toString() + "</td></tr>";
            }
            
            spItem += "<tr><td width=\"13%\" align=\"center\">合计</td><td width=\"10%\" align=\"center\"></td><td width=\"13%\" align=\"center\"></td><td width=\"12%\" align=\"center\">"
                    + sp.GetValue(spCnt - 1, 3) + "</td></tr>";
        }
        return spItem;
    }
    
    /**
     * 开通139手机邮箱免费版
     * 
     * @return
     */
    public void provideMailService() throws IOException
    {
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        this.getResponse().setContentType("text/html;charset=GBK");
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 是否开通标志 1：开通成功；0：开通失败
        String addMailFlag = "";
        
        // 调用接口开通139手机邮箱
        String addMailStatus = monthFeeBean.add139Mail(telnum, terminalInfo, curMenuId);
        if ("1".equals(addMailStatus))
        {
            addMailFlag = "1";
        }
        else
        {
            addMailFlag = "0";
        }
        PrintWriter out = this.getResponse().getWriter();
        out.write(addMailFlag);
        out.flush();
    }
    
    /**
     * 消费分析图
     */
    public String billPieChart()
    {
        HttpServletRequest request = this.getRequest();
        HttpServletResponse response = this.getResponse();
        
        CRSet myConsume = (CRSet)request.getSession().getAttribute("billflex");
        if (myConsume == null)
        {
            return null;
        }
        Vector vDetailFee = new Vector();
        for (int i = 0; i < myConsume.GetRowCount(); i++)
        {
            if (myConsume.GetValue(i, 1).indexOf(" ") != 0)
            {
                vDetailFee.add(new CEntityString(myConsume.GetValue(i, 2)));
                vDetailFee.add(new CEntityString(myConsume.GetValue(i, 3)));
            }
        }
        request.getSession().removeAttribute("billflex");
        
        try
        {
            response.setContentType("image/jpeg");
            DefaultPieDataset data = new DefaultPieDataset();
            // 获得费用明细
            
            String fee = null;
            String[] feeName = {"月基本费", "语音通信费", "增值业务费", "代收费", "补收费"};
            Arrays.sort(feeName);
            for (int ix = 0; ix < vDetailFee.size() - 1; ix += 2)
            {
                if (Arrays.binarySearch(feeName, ((CEntityString)vDetailFee.get(ix)).EntityString) < 0)
                {
                    continue;
                }
                // 取出费用
                fee = ((CEntityString)vDetailFee.get(ix + 1)).EntityString;
                
                if (!fee.equals("0"))
                {
                    data.setValue(((CEntityString)vDetailFee.get(ix)).EntityString, Float.parseFloat(fee));
                }
            }
            
            PiePlot plot = new PiePlot(data);// 饼图
            JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
            chart.setBackgroundPaint(java.awt.Color.white);// 可选，设置图片背景色
            plot.setStartAngle(180);
            
            plot.setLabelBackgroundPaint(java.awt.Color.white);// 设置标签背景色为白色
            plot.setShadowPaint(java.awt.Color.white);// 出去饼图后的阴影
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}", NumberFormat.getNumberInstance(),
                    NumberFormat.getPercentInstance()));// 去掉标签后的数字
            plot.setLabelOutlinePaint(java.awt.Color.white);// 除去标签边框
            plot.setOutlinePaint(java.awt.Color.white);// 出去图片边框
            plot.setLabelShadowPaint(java.awt.Color.white);// 除去标签的阴影
            plot.setLabelFont(new Font("黑体", Font.TRUETYPE_FONT, 11));
            
            // ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 100,chart, 300, 120, null);
            ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 300, 150, null);
            
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public String toStrong(CRSet crset, int i)
    {
        String rtn = "";
        String src = crset.GetValue(i, 2);
        if (src.indexOf(" ") == 0)
        {
            rtn = "<td>" + src.replaceAll(" ", "&nbsp;") + "</td><td align='right'>"
                    + (src.substring(0, src.lastIndexOf(" "))).replaceAll(" ", "&nbsp;")
                    + crset.GetValue(i, 3).replaceAll(" ", "") + "</td>";
        }
        else
        {
            rtn = "<td class='title02'>" + src.trim() + "</td><td class='title02' align='right'>"
                    + crset.GetValue(i, 3).trim() + "</td>";
        }
        return rtn;
    }
    
    public String fomartStr(String src, String unit)
    {
        if (src == null || "".equals(src.trim()))
        {
            return "";
        }
        
        try
        {
            if (new Double(src).doubleValue() > 0)
            {
                return src + unit;
            }
            else
            {
                return "";
            }
        }
        catch (NumberFormatException ex)
        {
            return "";
        }
    }
    
    // add begin l00190940 2011-11-24 不显示单位
    public String fomartStrNoUnit(String src)
    {
        if (src == null || "".equals(src.trim()))
        {
            return "";
        }
        
        try
        {
            if (new Double(src).doubleValue() > 0)
            {
                return src;
            }
            else
            {
                return "";
            }
        }
        catch (NumberFormatException ex)
        {
            return "";
        }
    }
    
    // add end l00190940 2011-11-24 不显示单位
    
    // add begin l00190940 2011-12-21 带符号不显示单位
    public String fomartStrNoUnitSigned(String src)
    {
        if (src == null || "".equals(src.trim()))
        {
            return "";
        }
        
        try
        {
            if (new Double(src).doubleValue() != 0)
            {
                return src;
            }
            else
            {
                return "";
            }
        }
        catch (NumberFormatException ex)
        {
            return "";
        }
    }
    
    // add end l00190940 2011-12-21 带符号不显示单位
    
    /**
     * 查询账期 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String qryBillCycle() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryMonthBill Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 根据手机号码查询客户信息
        ReturnWrap rw = monthFeeBean.getCustinfo(terminalInfo, curMenuId, customerSimp.getServNumber(), month);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 取返回对象
            Vector vector = (Vector)(rw.getReturnObject());
            
            // 客户信息
            CTagSet ctagset = (CTagSet)(vector.get(0));
            
            //modify begin lwx439898 2017-06-26 OR_SD_201706_196_自助终端部分菜单“客户名称”模糊化改造
            // 客户名称
            custname = CommonUtil.getUserLastName(ctagset.GetValue("custname"));
            
            //modify end lwx439898 2017-06-26 OR_SD_201706_196_自助终端部分菜单“客户名称”模糊化改造
            
            brandnm = ctagset.GetValue("brandnm");// 品牌
            productnm = ctagset.GetValue("productnm");// 主体产品
            subsid = ctagset.GetValue("subsid");// 用户ID
            // add by lKF60882 2016-10-10 OR_SD_201604_913_山东_关于在各账单查询功能界面中增加星级展示的需求
            subsCreditName = ctagset.GetValue("subsCreditName");// 客户星级
            
            // 账期信息
            CRSet crset = (CRSet)(vector.get(1));
            
            // 多账期转到账期选择页面
            CyclePO cyclePO;
            if (crset.GetRowCount() > 1)// 多账期
            {
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    cyclePO = new CyclePO();
                    cyclePO.setCycle(crset.GetValue(i, 0));// 帐期
                    cyclePO.setStartdate(crset.GetValue(i, 1));// 开始时间
                    cyclePO.setEnddate(crset.GetValue(i, 2));// 结束时间
                    cyclePO.setAcctid(crset.GetValue(i, 3));// 主账号
                    cyclePO.setUnionacct(crset.GetValue(i, 4));// 是否合户用户。1，是；0，不是
                    cycleList.add(cyclePO);
                }
                
                // 返回
                return "selectCycle";
            }
            else
            // 单账期
            {
                // 直接转到查询账单页面
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    cyclePO = new CyclePO();
                    cyclePO.setCycle(crset.GetValue(i, 0));// 帐期
                    cycle = crset.GetValue(i, 0);
                    cyclePO.setStartdate(crset.GetValue(i, 1));// 开始时间
                    startdate = crset.GetValue(i, 1);
                    cyclePO.setEnddate(crset.GetValue(i, 2));// 结束时间
                    enddate = crset.GetValue(i, 2);
                    cyclePO.setAcctid(crset.GetValue(i, 3));// 主账号
                    acctid = crset.GetValue(i, 3);
                    cyclePO.setUnionacct(crset.GetValue(i, 4));// 是否合户用户。1，是；0，不是
                    unionacct = crset.GetValue(i, 4);
                    
                    cycleList.add(cyclePO);
                }
                
                // 返回
                return isNewOrOld();
            }
        }
        else
        {
            request.setAttribute("errormessage", "客户信息查询失败！");
            
            request.setAttribute("backStep", "-1");
            
            // 返回
            return "error";
        }
    }
    
    /**
     * 查询账单（新老版本处理） <功能详细描述>
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String isNewOrOld() throws Exception
    {
        
        // 当前时间
        String crrentdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CUSTINFO))
        {
            startdate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("yyyy-MM-dd").parse(startdate));
            enddate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("yyyy-MM-dd").parse(enddate));
        }
        
        if (Integer.parseInt(crrentdate) >= Integer.parseInt(startdate)
                && Integer.parseInt(crrentdate) <= Integer.parseInt(enddate))
        {
            // 新版_实时
            return qryMonthBill_real_new();
        }
        else
        {
            // 新版_历史
            return qryMonthBill_new();
        }
        
    }
    
    /**
     * 查询账单信息_新版实时
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String qryMonthBill_real_new() throws Exception
    {
        HttpServletRequest request = this.getRequest();
        
        request.setAttribute("backStep", "-1");
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        
        // 添加WebService调用接口
        this.startMarketingEventThread(customerSimp);
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 账单信息
        String factory = "9A3A9B26E157B508228301EF1F7BF352";// 厂家编码的密文标识，不能为空
        String arealist = "";
        if ("0".equals(cs))
        {
            arealist = "SCORE,PKGINFO,BILLTREND,RECOMMEND,ACCTBALANCE,AGREEMENT,PRESENT,PAYEDBYOTHER,PAYEDFOROTHER,INOUT";// 区域列表
        }
        else
        {
            arealist = "SCORE,PKGINFO,BILLTREND,RECOMMEND,ACCTBALANCE,AGREEMENT,PRESENT,PAYEDBYOTHER,PAYEDFOROTHER,SPBILL,BILLINFO,INOUT,SCOREREMARK,ACKNOWLEDGEMENT";// 区域列表
        }
        ReturnWrap rw = monthFeeBean.qryMonthBill_new(terminalInfo,
                "10000100",
                customerSimp.getServNumber(),
                acctid,
                subsid,
                cycle,
                startdate,
                enddate,
                unionacct,
                customerSimp.getRegionID(),
                arealist,
                factory);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            map = (Map)rw.getReturnObject();
            
            if (map != null)
            {
                // 当月费用结构图
                if (map.get("billfixed") != null)
                {
                    request.getSession().setAttribute(customerSimp.getServNumber() + "_billfixed",
                            (List<Object>)map.get("billfixed"));
                }
                
                // 消费趋势图
                if (map.get("billhalfyeartrend") != null)
                {
                    request.getSession().setAttribute(customerSimp.getServNumber() + "_billhalfyeartrend",
                            (List<Object>)map.get("billhalfyeartrend"));
                }
                
                // 成功
                this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "0", "查询账期" + startdate + " - "
                        + enddate + "的实时账单信息成功");
            }
            else
            {
                request.setAttribute("errormessage", "查询账单信息失败！");
                
                // 失败
                this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "1", "未查询到账期" + startdate + " - "
                        + enddate + "的实时账单信息");
                
                // 返回
                return "error";
            }
        }
        else
        {
            request.setAttribute("errormessage", "查询账单信息失败！");
            
            // 失败
            this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "1", "未查询到账期" + startdate + " - " + enddate
                    + "的历史账单信息");
            
            // 返回
            return "error";
        }
        
        curdate = CommonUtil.dateToString(new Date(), "yyyy年MM月dd日HH时mm分");
        
        // 返回
        return "qryMonthBill_real_new";
    }
    
    /**
     * 查询账单信息_新版历史
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public String qryMonthBill_new() throws Exception
    {
        HttpServletRequest request = this.getRequest();
        
        request.setAttribute("backStep", "-1");
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        
        // 添加WebService调用接口
        this.startMarketingEventThread(customerSimp);
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 账单信息
        String factory = "9A3A9B26E157B508228301EF1F7BF352";// 厂家编码的密文标识，不能为空
        String arealist = "";
        if ("0".equals(cs))
        {
            arealist = "SCORE,PKGINFO,BILLTREND,RECOMMEND,ACCTBALANCE,AGREEMENT,PRESENT,PAYEDBYOTHER,PAYEDFOROTHER,INOUT";// 区域列表
        }
        else
        {
            arealist = "SCORE,PKGINFO,BILLTREND,RECOMMEND,ACCTBALANCE,AGREEMENT,PRESENT,PAYEDBYOTHER,PAYEDFOROTHER,SPBILL,BILLINFO,INOUT,SCOREREMARK,ACKNOWLEDGEMENT";// 区域列表
        }
        
        ReturnWrap rw = monthFeeBean.qryMonthBill_new(terminalInfo,
                "10000100",
                customerSimp.getServNumber(),
                acctid,
                subsid,
                cycle,
                startdate,
                enddate,
                unionacct,
                customerSimp.getRegionID(),
                arealist,
                factory);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            map = (Map)rw.getReturnObject();
            
            if (map != null)
            {
                // 当月费用结构图
                if (map.get("billfixed") != null)
                {
                    request.getSession().setAttribute(customerSimp.getServNumber() + "_billfixed",
                            (List<Object>)map.get("billfixed"));
                }
                
                // 消费趋势图
                if (map.get("billhalfyeartrend") != null)
                {
                    request.getSession().setAttribute(customerSimp.getServNumber() + "_billhalfyeartrend",
                            (List<Object>)map.get("billhalfyeartrend"));
                }
                
                // 成功
                this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "0", "查询账期" + startdate + " - "
                        + enddate + "的历史账单信息成功");
            }
            else
            {
                request.setAttribute("errormessage", "查询账单信息失败！");
                
                // 失败
                this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "1", "未查询到账期" + startdate + " - "
                        + enddate + "的历史账单信息");
                
                // 返回
                return "error";
            }
        }
        else
        {
            request.setAttribute("errormessage", "查询账单信息失败！");
            
            // 失败
            this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "1", "未查询到账期" + startdate + " - " + enddate
                    + "的历史账单信息");
            
            // 返回
            return "error";
        }
        
        curdate = CommonUtil.dateToString(new Date(), "yyyy年MM月dd日HH时mm分");
        
        // 返回
        return "qryMonthBill_new";
    }
    
    /**
     * 当月费用结构图_新版
     */
    public String billfixedPieChart_new()
    {
        HttpServletRequest request = this.getRequest();
        HttpServletResponse response = this.getResponse();
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        
        // 取得数据
        List<Object> obj_billfixed = (List<Object>)request.getSession().getAttribute(customerSimp.getServNumber()
                + "_billfixed");
        
        // 删除数据
        request.getSession().removeAttribute(customerSimp.getServNumber() + "_billfixed");
        
        try
        {
            // 设定生成图片
            response.setContentType("image/jpeg");
            
            // 获得费用明细
            DefaultPieDataset data = new DefaultPieDataset();
            for (Object obj : obj_billfixed)
            {
                if (obj instanceof FeePO)
                {
                    FeePO feePO = (FeePO)obj;
                    if ("1".equals(feePO.getIsshow()) && !"0.00".equals(feePO.getValue()))
                    {
                        data.setValue(feePO.getName(), Double.parseDouble(feePO.getValue()));
                    }
                }
                if (obj instanceof FeeGroupPO)
                {
                    FeeGroupPO feeGroupPO = (FeeGroupPO)obj;
                    List<FeePO> subfee = feeGroupPO.getSubfee();
                    for (FeePO feePO : subfee)
                    {
                        if ("1".equals(feePO.getIsshow()) && !"0.00".equals(feePO.getValue()))
                        {
                            data.setValue(feePO.getName(), Double.parseDouble(feePO.getValue()));
                        }
                    }
                }
            }
            
            response.setContentType("image/jpeg");
            JFreeChart chart = ChartFactory.createPieChart("费用结构图", data, false, false, false);
            
            // 图片背景色
            chart.setBackgroundPaint(Color.white);
            
            PiePlot plot = (PiePlot)chart.getPlot();
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("", NumberFormat.getNumberInstance(),
                    NumberFormat.getPercentInstance()));// 去掉标签
            plot.setShadowPaint(java.awt.Color.white);// 出去饼图后的阴影
            plot.setLabelOutlinePaint(java.awt.Color.white);// 除去标签边框
            plot.setOutlinePaint(java.awt.Color.white);// 出去图片边框
            plot.setLabelShadowPaint(java.awt.Color.white);// 除去标签的阴影
            plot.setLabelLinksVisible(true);
            
            // 设置无数据时的信息
            plot.setNoDataMessageFont(new Font("无数据", Font.BOLD, 14));
            
            // 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}", NumberFormat.getNumberInstance(),
                    new DecimalFormat("0%")));// 去掉标签
            
            // 右边框显示
            plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}", NumberFormat.getNumberInstance(),
                    new DecimalFormat("0%")));
            
            LegendTitle legendtitle = new LegendTitle(chart.getPlot());
            BlockContainer blockcontainer = new BlockContainer(new BorderArrangement());
            blockcontainer.setBorder(new BlockBorder(2D, 2D, 2D, 2D));
            BlockContainer blockcontainer1 = legendtitle.getItemContainer();
            blockcontainer.add(blockcontainer1);
            legendtitle.setWrapper(blockcontainer);
            legendtitle.setPosition(RectangleEdge.RIGHT);
            chart.addSubtitle(legendtitle);
            ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 450, 300, null);
            return null;
        }
        catch (Exception e)
        {
            logger.error("生成当月费用结构图失败：", e);
        }
        return null;
    }
    
    /**
     * 消费趋势图_新版 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String billfixedBarChart_new()
    {
        HttpServletRequest request = this.getRequest();
        HttpServletResponse response = this.getResponse();
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        
        // 取得数据
        List<BillPO> obj_billhalfyeartrend = (List<BillPO>)request.getSession()
                .getAttribute(customerSimp.getServNumber() + "_billhalfyeartrend");
        
        // 删除数据
        request.getSession().removeAttribute(customerSimp.getServNumber() + "_billhalfyeartrend");
        
        try
        {
            // 设定生成图片
            response.setContentType("image/jpeg");
            
            // 图表标题
            String chartTitle = "消费趋势图";
            
            // 目录轴的显示标签
            String xName = "";
            
            // 数值轴的显示标签
            String yName = "";
            
            // double[][] data = new double[][] { { 672, 766, 223, 540, 126 } };
            String[] rowKeys = {""};
            // String[] columnKeys = { "北京", "上海", "广州", "成都", "深圳" };
            double[][] data = new double[1][obj_billhalfyeartrend.size()];
            String[] columnKeys = new String[obj_billhalfyeartrend.size()];
            
            for (int i = 0; i < obj_billhalfyeartrend.size(); i++)
            {
                BillPO po = obj_billhalfyeartrend.get(i);
                data[0][i] = Double.parseDouble(po.getMoney());
                columnKeys[i] = po.getMonth();
            }
            
            CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
            JFreeChart chart = ChartFactory.createBarChart(chartTitle, // 图表标题
                    xName, // 目录轴的显示标签
                    yName, // 数值轴的显示标签
                    dataset, // 数据集
                    PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                    false, // 是否显示图例(对于简单的柱状图必须是false)
                    false, // 是否生成工具
                    false // 是否生成URL链接
            );
            Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);
            chart.setBackgroundPaint(Color.white);
            
            // create plot
            CategoryPlot plot = chart.getCategoryPlot();
            
            // 设置横虚线可见
            plot.setRangeGridlinesVisible(false);
            
            // 虚线色彩
            // plot.setRangeGridlinePaint(Color.gray);
            
            // 数据轴精度
            NumberAxis vn = (NumberAxis)plot.getRangeAxis();
            DecimalFormat df = new DecimalFormat("#0");
            vn.setNumberFormatOverride(df); // 数据轴数据标签的显示格式
            
            // x轴设置
            CategoryAxis domainAxis = plot.getDomainAxis();
            domainAxis.setLabelFont(labelFont);// 轴标题
            domainAxis.setTickLabelFont(labelFont);// 轴数值
            domainAxis.setMaximumCategoryLabelWidthRatio(0);// 横轴上的 Lable 是否完整显示
            domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
            
            // 设置距离图片左端距离
            domainAxis.setLowerMargin(0.1);
            // 设置距离图片右端距离
            domainAxis.setUpperMargin(0.1);
            // 设置 columnKey 是否间隔显示
            domainAxis.setCategoryLabelPositionOffset(1);
            
            plot.setDomainAxis(domainAxis);
            // 设置柱图背景色（注意，系统取色的时候要使用16位的模式来查看颜色编码，这样比较准确）
            // plot.setBackgroundPaint(new Color(255, 255, 204));
            
            // y轴设置
            ValueAxis rangeAxis = plot.getRangeAxis();
            // rangeAxis.setLabelFont(labelFont);
            // rangeAxis.setTickLabelFont(labelFont);
            // 设置最高的一个 Item 与图片顶端的距离
            rangeAxis.setUpperMargin(0.15);
            // 设置最低的一个 Item 与图片底端的距离
            rangeAxis.setLowerMargin(0.15);
            plot.setRangeAxis(rangeAxis);
            
            BarRenderer renderer = new BarRenderer();
            // 设置柱子宽度
            renderer.setMaximumBarWidth(0.05);
            // 设置柱子高度
            renderer.setMinimumBarLength(0.2);
            // 设置柱子边框颜色
            // renderer.setBaseOutlinePaint(Color.BLACK);
            // 设置柱子边框可见
            renderer.setDrawBarOutline(false);
            
            // 设置柱的颜色
            // renderer.setSeriesPaint(0, new Color(204, 255, 255));
            // renderer.setSeriesPaint(1, new Color(153, 204, 255));
            // renderer.setSeriesPaint(2, new Color(51, 204, 204));
            
            // 设置每个地区所包含的平行柱的之间距离
            renderer.setItemMargin(0.0);
            
            // 显示每个柱的数值，并修改该数值的字体属性
            renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setBaseItemLabelsVisible(false);
            plot.setRenderer(renderer);
            
            // 设置柱的透明度
            // plot.setForegroundAlpha(1.0f);
            
            // ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 100,chart, 300, 120, null);
            ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 450, 300, null);
            
            return null;
        }
        catch (Exception e)
        {
            logger.error("生成消费趋势图失败：", e);
        }
        return null;
    }
    
    /**
     * 新版本账单寄送
     * 
     * @remark modify by sWX219697 2014-04-29 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
     * @return 1:发送成功；2:未开通139邮箱
     */
    public void sendmail_new() throws IOException
    {
        // modify begin sWX219697 2014-04-29 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
        // 账单邮件下发新接口开关（1：开启 0：关闭）
        String sendBillMailSwitch = (String)PublicCache.getInstance().getCachedData(Constants.SEND_BILL_MAIL_SWITCH);
        
        if ("1".equals(sendBillMailSwitch))
        {
            this.sendBillMail_new();
        }
        else
        {
            
            // modify end sWX219697 2014-04-29 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
            if (logger.isDebugEnabled())
            {
                logger.debug("MonthFeeAction - sendmail_new Starting ...");
            }
            
            HttpServletRequest request = this.getRequest();
            request.setCharacterEncoding("GBK");
            this.getResponse().setContentType("text/html;charset=GBK");
            
            // 终端机信息
            TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession()
                    .getAttribute(Constants.TERMINAL_INFO);
            
            // 用户信息
            NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
            
            // 账单寄送内容
            String mailContent = "";
            
            // 账单信息
            String factory = "9A3A9B26E157B508228301EF1F7BF352";// 厂家编码的密文标识，不能为空
            String arealist = "";
            if ("0".equals(cs))
            {
                arealist = "SCORE,PKGINFO,BILLTREND,RECOMMEND,ACCTBALANCE,AGREEMENT,PRESENT,PAYEDBYOTHER,PAYEDFOROTHER,INOUT";// 区域列表
            }
            else
            {
                arealist = "SCORE,PKGINFO,BILLTREND,RECOMMEND,ACCTBALANCE,AGREEMENT,PRESENT,PAYEDBYOTHER,PAYEDFOROTHER,SPBILL,BILLINFO,INOUT,SCOREREMARK,ACKNOWLEDGEMENT";// 区域列表
            }
            
            ReturnWrap rw = monthFeeBean.qryMonthBill_new(terminalInfo,
                    "10000100",
                    customerSimp.getServNumber(),
                    acctid,
                    subsid,
                    cycle,
                    startdate,
                    enddate,
                    unionacct,
                    customerSimp.getRegionID(),
                    arealist,
                    factory);
            
            if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
            {
                map = (Map)rw.getReturnObject();
                
                // 此处拼凑发送内容
                custname = URLDecoder.decode(custname, "UTF-8");
                brandnm = URLDecoder.decode(brandnm, "UTF-8");
                productnm = URLDecoder.decode(productnm, "UTF-8");
                mailContent = getMailContent_new(customerSimp.getServNumber(),
                        custname,
                        brandnm + " " + productnm,
                        startdate,
                        enddate,
                        map);
            }
            
            String mailFlag = "";
            
            // 账单发送时间
            int time = 0;
            if ("1".equals(needCheckMail))
            {
                /**
                 * 判断用户是否已经开通手机邮箱，用户已经开通手机邮箱，则进行发送邮件。
                 */
                mailFlag = monthFeeBean.qrymailBox(customerSimp.getServNumber(), terminalInfo, curMenuId);
            }
            else
            {
                mailFlag = "1";
                time = Integer.parseInt((String)PublicCache.getInstance().getCachedData("SH_MAIL_DELAY"));
            }
            
            PrintWriter out = this.getResponse().getWriter();
            if ("1".equals(mailFlag))
            {
                Timer timer = new Timer();
                
                timer.schedule(new SendMailUtil(customerSimp.getServNumber() + "@139.com", "您" + startdate + "-"
                        + enddate + "的话费账单已到，请查收。", mailContent), new Date(System.currentTimeMillis() + time * 60L
                        * 1000));
                if ("1".equals(needCheckMail))
                {
                    out.write("1");
                    out.flush();
                }
            }
            /**
             * 如果用户未开通，则提示用户进行开通免费139邮箱。对于开通免费139邮箱的，稍后几分钟发送邮件。
             */
            else
            {
                // 用户没有开通手机邮箱
                out.write("2");
                out.flush();
            }
        }
    }
    
    /**
     * <山东账单邮件下发> <功能详细描述>
     * 
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-04-29 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
     * 
     */
    public void sendBillMail_new() throws IOException
    {
        
        if (logger.isDebugEnabled())
        {
            logger.debug("MonthFeeAction - sendBillMail start");
        }
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)this.getRequest()
                .getSession()
                .getAttribute(Constants.TERMINAL_INFO);
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest()
                .getSession()
                .getAttribute(Constants.USER_INFO);
        
        // 用户是否开通邮箱标识，1：已开通，0：未开通
        String mailFlag = "";
        
        // 账单发送时间
        int time = 0;
        
        // 是否验证用户邮箱开通情况
        if ("1".equals(needCheckMail))
        {
            
            // 判断用户是否已经开通手机邮箱，用户已经开通手机邮箱，则进行发送邮件。
            mailFlag = monthFeeBean.qrymailBox(customerSimp.getServNumber(), terminalInfo, curMenuId);
        }
        else
        {
            mailFlag = "1";
            time = Integer.parseInt((String)PublicCache.getInstance().getCachedData("SH_MAIL_DELAY"));
            
        }
        
        PrintWriter out = this.getResponse().getWriter();
        
        // 1:已成功开通邮箱
        if ("1".equals(mailFlag))
        {
            Timer timer = new Timer();
            
            // 若用户之前已开通139邮箱，则不需要延时发送，对于新开通139邮箱用户，则延时3分钟再调用下发邮件接口
            /*
             * timer.schedule(new
             * SendBillMailUtil(terminalInfo.getOperid(),terminalInfo.getTermid(),customerSimp.getServNumber
             * (),cycle,unionacct,curMenuId), new Date(System.currentTimeMillis()+ time * 60L * 1000));
             */

            // modify by sWX219697 2014-8-13 修改接口调用方式，采用Timer延时调用时获取不到Action上下文信息，调用失败
            monthFeeBean.sendBillMail(terminalInfo.getOperid(),
                    terminalInfo.getTermid(),
                    customerSimp.getServNumber(),
                    cycle,
                    unionacct,
                    curMenuId);
            
            // 是否需要检查邮箱 1：需要
            if ("1".equals(needCheckMail))
            {
                out.write("1");
                out.flush();
            }
        }
        
        // 如果用户未开通，则提示用户进行开通免费139邮箱。对于开通免费139邮箱的，稍后几分钟发送邮件。
        else
        {
            // 用户没有开通手机邮箱
            out.write("2");
            out.flush();
        }
        
    }
    
    /**
     * 构造发送账单内容_新版
     * 
     * @param brief
     * @param billfixed
     * @param billflex
     * @param acct
     * @param score
     * @param sp
     * @param contract
     * @param present
     * @param payedbyother
     * @param payedforother
     * @return
     */
    public String getMailContent_new(String telnum, String custName, String brandnm, String startdate, String enddate,
            Map map)
    {
        // 费用信息
        List<Object> obj_billfixed = (List<Object>)map.get("billfixed");
        
        // 费用合计
        String parseBillfixed_hj = (String)map.get("parseBillfixed_hj");
        
        // 账户概要信息
        Map<String, String> acctbalanceMap = (Map<String, String>)map.get("acctbalance");
        
        // 积分信息
        Map<String, String> scoreinfoMap = (Map<String, String>)map.get("scoreinfo");
        
        // 资费推荐
        String recommend = (String)map.get("recommend");
        
        // 积分备注
        String scoreremark = (String)map.get("scoreremark");
        if (CommonUtil.isEmpty(scoreremark))
        {
            scoreremark = "";
        }
        
        // 感谢语备注
        String acknowledgement = (String)map.get("acknowledgement");
        if (CommonUtil.isEmpty(acknowledgement))
        {
            acknowledgement = "";
        }
        
        // 自有业务信息(双排)
        List<FeedetailPO> feedetailList = (List<FeedetailPO>)map.get("feedetails");
        
        // 自有业务信息(单排)
        List<FeedetailPO> feedetailList_ = (List<FeedetailPO>)map.get("feedetails_");
        
        // 代收费
        List<Map> spList = (List<Map>)map.get("spbill");
        
        // 套餐信息
        List<PkgPO> pkgList = (List<PkgPO>)map.get("pkg");
        
        // 汇总信息
        List<PkgPO> obj_total = (List<PkgPO>)map.get("total");
        
        // 账本入账明细
        List<Map> inoutdetailList = (List<Map>)map.get("inoutdetail");
        
        // 协议款信息
        List<Map> agreementinfoList = (List<Map>)map.get("agreementinfo");
        
        // 赠送款信息
        List<Map> presentinfoList = (List<Map>)map.get("presentinfo");
        
        // 他人代付信息
        List<Map> payedbyotherList = (List<Map>)map.get("payedbyother");
        
        // 代他人付信息
        List<Map> payedforotherList = (List<Map>)map.get("payedforother");
        
        // 显示客户信息
        String startDate = startdate;
        String endDate = enddate;
        
        String startdateStr = startdate.substring(0, 4) + "年" + startdate.substring(4, 6) + "月"
                + startdate.substring(6, 8) + "日";
        String enddateStr = enddate.substring(0, 4) + "年" + enddate.substring(4, 6) + "月" + enddate.substring(6, 8)
                + "日";
        
        StringBuffer mailContent = new StringBuffer(2048);
        mailContent.append("<html>");
        mailContent.append("<head><style type='text/css'>.tb_blue02, .tb_blue02 td, .tb_blue02 th{border:1px solid #1a9ae3; border-collapse:collapse; font-size:14px; line-height:30px; height:30px; text-align:center;}.tit_info{line-height:30px; height:30px; font-size:22px; position:relative; font-weight:bold;}.bold{ font-weight:bold;}.tb_blue02 td.tr, .tb_blue02 th.tr{text-align:right;}.tb_blue02 td.tl, .tb_blue02 th.tl{text-align:left;}</style></head>");
        mailContent.append("<title></title>");
        mailContent.append("<body>");
        
        // 查询账单的当前时间
        curdate = CommonUtil.dateToString(new Date(), "yyyy年MM月dd日HH时mm分");
        
        if ("history".equals(realbz))
        {
            // 本期末余额
            mailContent.append("<table class='tb_blue02' width=\"70%\" align='center'>");
            
            // -----------------------------------------------------------
            mailContent.append("<tr>");
            mailContent.append("<td>");
            
            mailContent.append("<table class='tb_blue02' border=\"1\" width=\"100%\">");
            mailContent.append("<tr>");
            mailContent.append("<th class='tl' colspan=\"2\" style=\"text-align: center\"><Strong>中国移动通信&nbsp;&nbsp;客户账单</Strong></th>");
            mailContent.append("</tr>");
            mailContent.append("<tr>");
            mailContent.append("<td class='tl'>客户名称：</td><td class='tl'>").append(custName).append("</td>");
            mailContent.append("</tr>");
            mailContent.append("<tr>");
            mailContent.append("<td class='tl'>手机号码：</td><td class='tl'>").append(telnum).append("</td>");
            mailContent.append("</tr>");
            mailContent.append("<tr>");
            mailContent.append("<td class='tl'>当前品牌：</td><td class='tl'>").append(brandnm).append("</td>");
            mailContent.append("</tr>");
            mailContent.append("<tr>");
            mailContent.append("<td class='tl'>计费周期：</td><td class='tl'>" + startdateStr + " - " + enddateStr + "</td>");
            mailContent.append("</tr>");
            mailContent.append("<tr>");
            mailContent.append("<td class='tl'>查询时间：</td>");
            mailContent.append("<td class='tl'>").append(curdate).append("</td>");
            mailContent.append("</tr>");
            mailContent.append("</table>");
            
            mailContent.append("</td>");
            
            // Change by hWX5316476 2013/10/17 OR_SD_201308_1167 begin
            // 判断概要信息和积分信息是否都存在
            if (acctbalanceMap != null && scoreinfoMap != null && acctbalanceMap.size() > 0 && scoreinfoMap.size() > 0)
            {
                mailContent.append("<td class='tl'>");
                
                mailContent.append("<table class='tb_blue02' border=\"1\" width=\"100%\">");
                mailContent.append("<tr>");
                mailContent.append("<th class='tl' colspan=\"2\" style=\"text-align: left\"><Strong>本期末余额</Strong></th>");
                mailContent.append("</tr>");
                mailContent.append("<tr>");
                mailContent.append("<td class='tl'>&nbsp;&nbsp;&nbsp;&nbsp;现金余额</td>");
                mailContent.append("<td class='tl' style=\"text-align: right;\">")
                        .append(acctbalanceMap.get("thisval") != null ? acctbalanceMap.get("thisval") : "")
                        .append("</td>");
                mailContent.append("</tr>");
                
                if (acctbalanceMap.get("contractval") != null && !"0.00".equals(acctbalanceMap.get("contractval")))
                {
                    mailContent.append("<tr>");
                    mailContent.append("<td class='tl'>&nbsp;&nbsp;&nbsp;&nbsp;协议款余额</td>");
                    mailContent.append("<td class='tl' style=\"text-align: right;\">")
                            .append(acctbalanceMap.get("contractval") != null ? acctbalanceMap.get("contractval")
                                    : "&nbsp;")
                            .append("</td>");
                    mailContent.append("</tr>");
                }
                
                if (acctbalanceMap.get("presentval") != null && !"0.00".equals(acctbalanceMap.get("presentval")))
                {
                    mailContent.append("<tr>");
                    mailContent.append("<td class='tl'>&nbsp;&nbsp;&nbsp;&nbsp;赠款余额</td>");
                    mailContent.append("<td class='tl' style=\"text-align: right;\">")
                            .append(acctbalanceMap.get("presentval") != null ? acctbalanceMap.get("presentval")
                                    : "&nbsp;")
                            .append("</td>");
                    mailContent.append("</tr>");
                }
                
                if (scoreinfoMap.get("thisavail") != null && !"0".equals(scoreinfoMap.get("thisavail")))
                {
                    mailContent.append("<tr>");
                    mailContent.append("<td class='tl'>&nbsp;&nbsp;&nbsp;&nbsp;可用积分</td>");
                    mailContent.append("<td class='tl' style=\"text-align: right;\">")
                            .append(scoreinfoMap.get("thisavail") != null ? scoreinfoMap.get("thisavail") : "&nbsp;")
                            .append("</td>");
                    mailContent.append("</tr>");
                }
                
                mailContent.append("<tr>");
                mailContent.append("<td class='tl' style=\"color: red;\"><Strong>本月费用合计</Strong></td>");
                mailContent.append("<td class='tl' style=\"text-align: right;\">")
                        .append(parseBillfixed_hj != null ? parseBillfixed_hj : "&nbsp;")
                        .append("</td>");
                mailContent.append("</tr>");
                mailContent.append("</table>");
                
                mailContent.append("</td>");
            }
            // Change by hWX5316476 2013/10/17 OR_SD_201308_1167 end
            
            mailContent.append("</tr>");
            mailContent.append("</table>");
            
            mailContent.append("<br>");
        }
        else
        {
            // 客户信息
            mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\"  align='center'>");
            mailContent.append("<tr>")
                    .append("<td class='tl'>客户名称：</td><td class='tl'>")
                    .append(custName)
                    .append("</td></tr>");
            mailContent.append("<tr>")
                    .append("<td class='tl'>手机号码：</td><td class='tl'>")
                    .append(telnum)
                    .append("</td></tr>");
            mailContent.append("<tr>")
                    .append("<td class='tl'>当前品牌：</td><td class='tl'>")
                    .append(brandnm)
                    .append("</td></tr>");
            mailContent.append("<tr>");
            mailContent.append("<td class='tl'>计费周期：</td><td class='tl'>" + startDate + " - " + endDate + "</td>");
            mailContent.append("</tr>");
            mailContent.append("<tr>");
            mailContent.append("<td class='tl'>查询时间：</td>");
            mailContent.append("<td class='tl'>").append(curdate).append("</td>");
            mailContent.append("</tr>");
            mailContent.append("</table>");
            mailContent.append("<br>");
        }
        
        // 费用信息
        mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\"  align='center'>");
        mailContent.append("<tr>");
        mailContent.append("<th class='tl' style=\"text-align: center;\" colspan=\"2\"><Strong>费用信息</Strong></th>");
        mailContent.append("</tr>");
        mailContent.append("<tr>");
        mailContent.append("<td class='tl' style=\"text-align: center;\">费用项目</td>");
        mailContent.append("<td class='tl' style=\"text-align: center;\">金额/元</td>");
        mailContent.append("</tr>");
        if (obj_billfixed != null && obj_billfixed.size() > 0)
        {
            for (Object obj : obj_billfixed)
            {
                
                if (obj instanceof FeePO)
                {
                    FeePO feePO = (FeePO)obj;
                    mailContent.append("<tr>");
                    mailContent.append("<td class='tl' style=\"text-align: left;\"><Strong>")
                            .append(feePO.getName())
                            .append("</Strong></td>");
                    mailContent.append("<td class='tl' style=\"text-align: right;\">")
                            .append(feePO.getValue())
                            .append("</td>");
                    mailContent.append("</tr>");
                }
                if (obj instanceof FeeGroupPO)
                {
                    FeeGroupPO feeGroupPO = (FeeGroupPO)obj;
                    List<FeePO> subfee = feeGroupPO.getSubfee();
                    
                    mailContent.append("<tr>");
                    mailContent.append("<td class='tl' style=\"text-align: left;\"><Strong>")
                            .append(feeGroupPO.getName())
                            .append("</Strong></td>");
                    mailContent.append("<td class='tl' style=\"text-align: left;\">&nbsp;</td>");
                    mailContent.append("</tr>");
                    for (FeePO po : subfee)
                    {
                        mailContent.append("<tr>");
                        mailContent.append("<td class='tl' style=\"text-align: left;\">&nbsp;&nbsp;&nbsp;&nbsp;")
                                .append(po.getName())
                                .append("</td>");
                        mailContent.append("<td class='tl' style=\"text-align: right;\">")
                                .append(po.getValue())
                                .append("</td>");
                        mailContent.append("</tr>");
                    }
                }
            }
        }
        else
        {
            mailContent.append("<tr><td class='tl' colspan=\"2\" >无数据</td></tr>");
        }
        mailContent.append("<table class='tb_blue02' align='center'>");
        mailContent.append("<br>");
        
        // 资费推荐
        /**
         * mailContent.append("
         * <table class='tb_blue02' border=\"1\" width=\"70%\"  align='center'>"); mailContent.append("
         * <tr>"); mailContent.append("
         * <th class='tl' style=\"text-align: center;\"><Strong>资费推荐</Strong></th>"); mailContent.append("
         * </tr>"); mailContent.append("
         * <tr>"); mailContent.append("
         * <td class='tl'>"); mailContent.append(recommend != null ? recommend : ""); mailContent.append("</td>");
         * mailContent.append("
         * </tr>"); mailContent.append("
         * </table>
         * "); mailContent.append(" <br>
         * ");
         **/
        
        if ("history".equals(realbz))
        {
            // Change by hWX5316476 2013/10/17 OR_SD_201308_1167 begin
            if (acctbalanceMap != null && acctbalanceMap.size() > 0)
            {
                // 账户信息
                mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\"  align='center'>");
                mailContent.append("        <tr>");
                mailContent.append("            <th class='tl' colspan=\"20\" style=\"text-align: center;\"><Strong>账户信息</Strong></td>");
                mailContent.append("        </tr>");
                mailContent.append("        <tr>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">上月末结余</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＋</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">本月充值</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＋</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">使用协议款</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＋</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">退费</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＋</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">过户转入</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＝</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\"  colspan=\"3\">本月合计可用</td>");
                mailContent.append("        </tr>");
                mailContent.append("        <tr>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                        .append(acctbalanceMap.get("lastval") != null ? acctbalanceMap.get("lastval") : "")
                        .append("</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＋</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                        .append(acctbalanceMap.get("income") != null ? acctbalanceMap.get("income") : "")
                        .append("</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＋</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                        .append(acctbalanceMap.get("contractused") != null ? acctbalanceMap.get("contractused") : "")
                        .append("</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＋</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                        .append(acctbalanceMap.get("backfee") != null ? acctbalanceMap.get("backfee") : "")
                        .append("</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＋</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                        .append(acctbalanceMap.get("transferin") != null ? acctbalanceMap.get("transferin") : "")
                        .append("</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＝</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\" colspan=\"3\">")
                        .append(acctbalanceMap.get("totalcanuse") != null ? acctbalanceMap.get("totalcanuse") : "")
                        .append("</td>");
                mailContent.append("        </tr>");
                mailContent.append("        <tr>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">本月合计可用</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">本月费用合计</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">代他人付费</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">合户号码费用</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">违约金</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">过户转出</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＝</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">月末余额</td>");
                mailContent.append("        </tr>");
                mailContent.append("        <tr>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                        .append(acctbalanceMap.get("totalcanuse") != null ? acctbalanceMap.get("totalcanuse") : "")
                        .append("</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                        .append(acctbalanceMap.get("substotalfee") != null ? acctbalanceMap.get("substotalfee") : "")
                        .append("</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                        .append(acctbalanceMap.get("payotherfee") != null ? acctbalanceMap.get("payotherfee") : "")
                        .append("</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                        .append(acctbalanceMap.get("othersubsfee") != null ? acctbalanceMap.get("othersubsfee") : "")
                        .append("</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                        .append(acctbalanceMap.get("latefee") != null ? acctbalanceMap.get("latefee") : "")
                        .append("</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                        .append(acctbalanceMap.get("transferin") != null ? acctbalanceMap.get("transferin") : "")
                        .append("</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＝</td>");
                mailContent.append("            <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                        .append(acctbalanceMap.get("thisval") != null ? acctbalanceMap.get("thisval") : "")
                        .append("</td>");
                mailContent.append("        </tr>");
                mailContent.append("</table>");
                mailContent.append("<br>");
            }
            // Change by hWX5316476 2013/10/17 OR_SD_201308_1167 end
            
            // 积分信息
            int bz = 0;
            if (scoreinfoMap != null)
            {
                if (!("0".equals(scoreinfoMap.get("thisavail")) && "0".equals(scoreinfoMap.get("lastavail"))
                        && "0".equals(scoreinfoMap.get("consume")) && "0".equals(scoreinfoMap.get("award"))
                        && "0".equals(scoreinfoMap.get("transin")) && "0".equals(scoreinfoMap.get("exchange"))
                        && "0".equals(scoreinfoMap.get("transout")) && "0".equals(scoreinfoMap.get("clear"))))
                {
                    mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\" align='center'>");
                    mailContent.append("    <tr>");
                    mailContent.append("        <th class='tl' colspan=\"20\" style=\"text-align: center;\"><Strong>积分信息</Strong></td>");
                    mailContent.append("    </tr>");
                    mailContent.append("    <tr>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">可用积分</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＝</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">上期可用</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＋</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">本期新增积分</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＋</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">本期奖励积分</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＋</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">本期跨区转入</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">本期兑换</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">本期跨区转出</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">本期作废</td>");
                    mailContent.append("    </tr>");
                    mailContent.append("    <tr>");
                    // findbugs修改 2015-09-02 zKF66389
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                            .append(scoreinfoMap.get("thisavail") != null ? scoreinfoMap.get("thisavail") : "")
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＝</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                            .append(scoreinfoMap.get("lastavail") != null ? scoreinfoMap.get("lastavail") : "")
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＋</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                            .append(scoreinfoMap.get("consume") != null ? scoreinfoMap.get("consume") : "")
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＋</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                            .append(scoreinfoMap.get("award") != null ? scoreinfoMap.get("award") : "")
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">＋</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                            .append(scoreinfoMap.get("transin") != null ? scoreinfoMap.get("transin") : "")
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                            .append(scoreinfoMap.get("exchange") != null ? scoreinfoMap.get("exchange") : "")
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                            .append(scoreinfoMap.get("transout") != null ? scoreinfoMap.get("transout") : "")
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">－</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center; border-left:none; border-right:none;\">")
                            .append(scoreinfoMap.get("clear") != null ? scoreinfoMap.get("clear") : "")
                            .append("</td>");
                    // findbugs修改 2015-09-02 zKF66389
                    mailContent.append("    </tr>");
                    mailContent.append("</table>");
                    mailContent.append("<br>");
                    
                    // 备注
                    mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\"  align='center'>");
                    mailContent.append("<tr>");
                    mailContent.append("<th class='tl' style=\"text-align: center;\"><Strong>备注</Strong></th>");
                    mailContent.append("</tr>");
                    mailContent.append("<tr>");
                    mailContent.append("<td class='tl'>");
                    mailContent.append(scoreremark);
                    mailContent.append("</td>");
                    mailContent.append("</tr>");
                    mailContent.append("<tr>");
                    mailContent.append("<td class='tl'>");
                    mailContent.append(acknowledgement);
                    mailContent.append("</td>");
                    mailContent.append("</tr>");
                    mailContent.append("</table>");
                    mailContent.append(" <br>");
                }
                else
                {
                    bz = 1;
                    
                    // 备注
                    mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\"  align='center'>");
                    mailContent.append("<tr>");
                    mailContent.append("<th class='tl' style=\"text-align: center;\"><Strong>备注</Strong></th>");
                    mailContent.append("</tr>");
                    mailContent.append("<tr>");
                    mailContent.append("<td class='tl'>");
                    mailContent.append(acknowledgement);
                    mailContent.append("</td>");
                    mailContent.append("</tr>");
                    mailContent.append("</table>");
                    mailContent.append(" <br>");
                }
            }
            else
            {
                if (bz == 0)
                {
                    // 备注
                    mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\"  align='center'>");
                    mailContent.append("<tr>");
                    mailContent.append("<th class='tl' style=\"text-align: center;\"><Strong>备注</Strong></th>");
                    mailContent.append("</tr>");
                    mailContent.append("<tr>");
                    mailContent.append("<td class='tl'>");
                    mailContent.append(acknowledgement);
                    mailContent.append("</td>");
                    mailContent.append("</tr>");
                    mailContent.append("</table>");
                    mailContent.append(" <br>");
                }
            }
            
        }
        else
        {
            // 备注
            mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\"  align='center'>");
            mailContent.append("<tr>");
            mailContent.append("<th class='tl' style=\"text-align: center;\"><Strong>备注</Strong></th>");
            mailContent.append("</tr>");
            mailContent.append("<tr>");
            mailContent.append("<td class='tl'>");
            mailContent.append(acknowledgement);
            mailContent.append("</td>");
            mailContent.append("</tr>");
            mailContent.append("</table>");
            mailContent.append(" <br>");
        }
        mailContent.append("<br>");
        
        // 自有业务
        mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\" align='center'>");
        mailContent.append("<tr>");
        mailContent.append("<th class='tl' colspan=\"20\" style=\"text-align: left;\"><Strong>中国移动自有业务费用</Strong></td>");
        mailContent.append("</tr>");
        mailContent.append("<tr>");
        mailContent.append("<td class='tl' style=\"text-align: center;\">费用名称</td>");
        mailContent.append("<td class='tl' style=\"text-align: center;\">金额/元</td>");
        mailContent.append("<td class='tl' style=\"text-align: center;\">费用名称</td>");
        mailContent.append("<td class='tl' style=\"text-align: center;\">金额/元</td>");
        mailContent.append("</tr>");
        if (feedetailList != null && feedetailList.size() > 0)
        {
            for (FeedetailPO feedetailPO : feedetailList)
            {
                mailContent.append("<tr>");
                if ("0".equals(feedetailPO.getBz1()))
                {
                    mailContent.append("<td class='tl' style=\"text-align: left;\"><Strong>")
                            .append(feedetailPO.getName1())
                            .append("</Strong></td>");
                }
                else
                {
                    mailContent.append("<td class='tl' style=\"text-align: left;\">&nbsp;&nbsp;&nbsp;&nbsp;")
                            .append(feedetailPO.getName1())
                            .append("</td>");
                }
                mailContent.append("<td class='tl' style=\"text-align: right;\">")
                        .append(feedetailPO.getValue1())
                        .append("&nbsp;</td>");
                
                if ("0".equals(feedetailPO.getBz2()))
                {
                    mailContent.append("<td class='tl' style=\"text-align: left;\"><Strong>")
                            .append(feedetailPO.getName2())
                            .append("</Strong></td>");
                }
                else
                {
                    mailContent.append("<td class='tl' style=\"text-align: left;\">&nbsp;&nbsp;&nbsp;&nbsp;")
                            .append(feedetailPO.getName2())
                            .append("</td>");
                }
                mailContent.append("<td class='tl' style=\"text-align: right;\">")
                        .append(feedetailPO.getValue2())
                        .append("&nbsp;</td>");
                mailContent.append("</tr>");
            }
        }
        else
        {
            mailContent.append("<tr><td class='tl' colspan=\"4\">无数据</td></tr>");
        }
        mailContent.append("</table>");
        mailContent.append("<br>");
        
        // 代收费业务
        if (spList != null && spList.size() > 0)
        {
            mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\" align='center'>");
            mailContent.append("    <tr>");
            mailContent.append("        <th class='tl' colspan=\"20\" style=\"text-align: left;\"><Strong>代收费业务</Strong></td>");
            mailContent.append("    </tr>");
            mailContent.append("    <tr>");
            mailContent.append("        <td class='tl' style=\"text-align: center;\">服务号码</td>");
            mailContent.append("        <td class='tl' style=\"text-align: center;\">业务名称</td>");
            mailContent.append("        <td class='tl' style=\"text-align: center;\">服务提供商</td>");
            mailContent.append("        <td class='tl' style=\"text-align: center;\">使用方式</td>");
            mailContent.append("        <td class='tl' style=\"text-align: center;\">费用类型</td>");
            mailContent.append("        <td class='tl' style=\"text-align: center;\">金额</td>");
            mailContent.append("        ");
            mailContent.append("    </tr>");
            for (Map obj : spList)
            {
                mailContent.append("    <tr>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                        .append(obj.get("spcode"))
                        .append("</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                        .append(obj.get("spname"))
                        .append("</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                        .append(obj.get("servcode"))
                        .append("</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                        .append(obj.get("usetype"))
                        .append("</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                        .append(obj.get("feetype"))
                        .append("</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                        .append(obj.get("fee"))
                        .append("</td>");
                mailContent.append("    </tr>");
            }
            mailContent.append("</table>");
            mailContent.append("<br>");
        }
        
        // 通信量使用明细
        if (pkgList != null && pkgList.size() > 0 && obj_total != null && obj_total.size() > 0)
        {
            mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\" align='center'>");
            mailContent.append("    <tr>");
            mailContent.append("        <th class='tl' colspan=\"20\" style=\"text-align: left;\"><Strong>通信量使用明细</Strong></td>");
            mailContent.append("    </tr>");
            mailContent.append("    <tr>");
            mailContent.append("        <th class='tl' colspan=\"20\" style=\"text-align: center;\"><Strong>套餐包含通信量</Strong></td>");
            mailContent.append("    </tr>");
            // findbugs修改 2015-09-02 zKF66389
            // if (pkgList != null && pkgList.size() > 0)
            if (pkgList.size() > 0)
            // findbugs修改 2015-09-02 zKF66389
            {
                for (PkgPO obj : pkgList)
                {
                    mailContent.append("    <tr>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                            .append(obj.getPkgname())
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: left;\">")
                            .append(obj.getPkgdesc())
                            .append("</td>");
                    mailContent.append("    </tr>");
                }
            }
            else
            {
                mailContent.append("<tr><td class='tl' colspan=\"2\">无数据</td></tr>");
            }
            mailContent.append("    <tr>");
            mailContent.append("        <th class='tl' colspan=\"20\" style=\"text-align: center;\"><Strong>实际使用通信量</Strong></td>");
            mailContent.append("    </tr>");
            
            // findbugs修改 2015-09-02 zKF66389
            // if (obj_total != null && obj_total.size() > 0)
            if (obj_total.size() > 0)
            // findbugs修改 2015-09-02 zKF66389
            {
                for (PkgPO obj : obj_total)
                {
                    mailContent.append("    <tr>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\" >通信总量</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: left;\">")
                            .append(obj.getPrivs())
                            .append("</td>");
                    mailContent.append("    </tr>");
                }
            }
            
            // findbugs修改 2015-09-02 zKF66389
            // if (pkgList != null && pkgList.size() > 0)
            if (pkgList.size() > 0)
            // findbugs修改 2015-09-02 zKF66389
            {
                for (PkgPO obj : pkgList)
                {
                    mailContent.append("    <tr>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                            .append(obj.getPkgname())
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: left;\">")
                            .append(obj.getPrivs())
                            .append("</td>");
                    mailContent.append("    </tr>");
                }
            }
            else
            {
                mailContent.append("<tr><td class='tl' colspan=\"2\">无数据</td></tr>");
            }
            
            mailContent.append("</table>");
            mailContent.append("<br>");
        }
        
        // Change by hWX5316476 2013/10/17 OR_SD_201308_1167 begin
        // 账本入账明细
        if (inoutdetailList != null && inoutdetailList.size() > 0)
        {
            mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\" align='center'>");
            mailContent.append("    <tr>");
            mailContent.append("        <th class='tl' colspan=\"20\" style=\"text-align: left;\"><Strong>账户入账明细</Strong></td>");
            mailContent.append("    </tr>");
            mailContent.append("    <tr>");
            mailContent.append("        <td class='tl' style=\"text-align: center;\">时间</td>");
            mailContent.append("        <td class='tl' style=\"text-align: center;\">方式</td>");
            mailContent.append("        <td class='tl' style=\"text-align: center;\">类别</td>");
            mailContent.append("        <td class='tl' style=\"text-align: center;\">金额</td>");
            mailContent.append("        <td class='tl' style=\"text-align: center;\">备注</td>");
            mailContent.append("    </tr>");
            for (Map inoutdetailMap : inoutdetailList)
            {
                mailContent.append("    <tr>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                        .append(inoutdetailMap.get("date") != null ? inoutdetailMap.get("date") : "")
                        .append("</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                        .append(inoutdetailMap.get("model") != null ? inoutdetailMap.get("model") : "")
                        .append("</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                        .append(inoutdetailMap.get("type") != null ? inoutdetailMap.get("type") : "")
                        .append("</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                        .append(inoutdetailMap.get("fee") != null ? inoutdetailMap.get("fee") : "")
                        .append("</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                        .append(inoutdetailMap.get("desc") != null ? inoutdetailMap.get("desc") : "")
                        .append("</td>");
                mailContent.append("    </tr>");
            }
            mailContent.append("</table>");
            mailContent.append("<br>");
        }
        // Change by hWX5316476 2013/10/17 OR_SD_201308_1167 end
        
        if ("history".equals(realbz))
        {
            // 协议款信息
            if (agreementinfoList != null && agreementinfoList.size() > 0)
            {
                mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\" align='center'>");
                mailContent.append("    <tr>");
                mailContent.append("        <th class='tl' colspan=\"20\" style=\"text-align: left;\"><Strong>协议款信息</Strong></td>");
                mailContent.append("    </tr>");
                mailContent.append("    <tr>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">协议款名称</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">使用号码</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">上月末余额</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">＋</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">当月续费</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">－</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">当月扣除</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">＝</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">月末余额</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">有效期</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">每月最低消费额度</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">当月实际使用</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">备注</td>");
                // mailContent.append("        <td class='tl' style=\"text-align: center;\">当月返还额度</td>");
                mailContent.append("    </tr>");
                for (Map obj : agreementinfoList)
                {
                    mailContent.append("    <tr>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                            .append(obj.get("name"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                            .append(obj.get("usedtel") == null ? "" : obj.get("usedtel"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">")
                            .append(obj.get("lastmonthleft"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">＋</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">")
                            .append(obj.get("curmonthpay"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">－</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">")
                            .append(obj.get("curmonthdeduct"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">＝</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">")
                            .append(obj.get("monthclosing"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                            .append(obj.get("efftime"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                            .append(obj.get("lowestconsume") == null ? "" : obj.get("lowestconsume"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                            .append(obj.get("curmonthused"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                            .append(obj.get("remark"))
                            .append("</td>");
                    // mailContent.append("        <td class='tl' style=\"text-align: center;\">").append(obj.get("curmonthreturn")).append("</td>");
                    mailContent.append("    </tr>");
                }
                mailContent.append("</table>");
                mailContent.append("<br>");
            }
            
            // 赠送款信息
            if (presentinfoList != null && presentinfoList.size() > 0)
            {
                mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\" align='center'>");
                mailContent.append("    <tr>");
                mailContent.append("        <th class='tl' colspan=\"20\" style=\"text-align: left;\"><Strong>赠送款信息</Strong></td>");
                mailContent.append("    </tr>");
                mailContent.append("    <tr>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">赠品名称</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">使用号码</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">上月末余额</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">＋</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">当月续费</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">－</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">当月扣除</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">＝</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">月末余额</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">有效期</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">每月最低消费额度</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">当月实际使用</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">备注</td>");
                // mailContent.append("        <td class='tl' style=\"text-align: center;\">当月返还额度</td>");
                mailContent.append("    </tr>");
                for (Map obj : presentinfoList)
                {
                    mailContent.append("    <tr>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                            .append(obj.get("name"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                            .append(obj.get("usedtel") == null ? "" : obj.get("usedtel"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">")
                            .append(obj.get("lastmonthleft"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">＋</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">")
                            .append(obj.get("curmonthpay"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">－</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">")
                            .append(obj.get("curmonthdeduct"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">＝</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;border-left:none; border-right:none;\">")
                            .append(obj.get("monthclosing"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                            .append(obj.get("efftime"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                            .append(obj.get("lowestconsume") == null ? "" : obj.get("lowestconsume"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                            .append(obj.get("curmonthused"))
                            .append("</td>");
                    mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                            .append(obj.get("remark"))
                            .append("</td>");
                    // mailContent.append("        <td class='tl' style=\"text-align: center;\">").append(obj.get("curmonthreturn")).append("</td>");
                    mailContent.append("    </tr>");
                }
                mailContent.append("</table>");
                mailContent.append("<br>");
            }
        }
        
        // 他人代付信息
        if (payedbyotherList != null && payedbyotherList.size() > 0)
        {
            mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\" align='center'>");
            mailContent.append(" <tr>");
            mailContent.append("     <th class='tl' colspan=\"20\" style=\"text-align: left;\"><Strong>他人代付信息</Strong></td>");
            mailContent.append(" </tr>");
            mailContent.append(" <tr>");
            mailContent.append("     <td class='tl' style=\"text-align: center;\">代付号码</td>");
            mailContent.append("     <td class='tl' style=\"text-align: center;\">付费范围</td>");
            mailContent.append("     <td class='tl' style=\"text-align: center;\">本月代付</td>");
            mailContent.append(" </tr>");
            for (Map payedbyother : payedbyotherList)
            {
                mailContent.append(" <tr>");
                mailContent.append("     <td class='tl' style=\"text-align: center;\">")
                        .append(payedbyother.get("paytelnum"))
                        .append("</td>");
                mailContent.append("     <td class='tl' style=\"text-align: center;\">")
                        .append(payedbyother.get("paytype"))
                        .append("</td>");
                mailContent.append("     <td class='tl' style=\"text-align: center;\">")
                        .append(payedbyother.get("fee"))
                        .append("</td>");
                mailContent.append(" </tr>");
            }
            mailContent.append("</table>");
            mailContent.append("<br>");
        }
        
        // 他人代付信息
        if (payedforotherList != null && payedforotherList.size() > 0)
        {
            mailContent.append("<table class='tb_blue02' border=\"1\" width=\"70%\" align='center'>");
            mailContent.append("    <tr>");
            mailContent.append("        <th class='tl' colspan=\"20\" style=\"text-align: left;\"><Strong>代他人付费信息</Strong></td>");
            mailContent.append("    </tr>");
            mailContent.append("    <tr>");
            mailContent.append("        <td class='tl' style=\"text-align: center;\">代付号码</td>");
            mailContent.append("        <td class='tl' style=\"text-align: center;\">付费范围</td>");
            mailContent.append("        <td class='tl' style=\"text-align: center;\">本月代付</td>");
            mailContent.append("    </tr>");
            mailContent.append("    ");
            for (Map payedforother : payedforotherList)
            {
                mailContent.append("    <tr>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                        .append(payedforother.get("payedtelnum"))
                        .append("</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                        .append(payedforother.get("paytype"))
                        .append("</td>");
                mailContent.append("        <td class='tl' style=\"text-align: center;\">")
                        .append(payedforother.get("fee"))
                        .append("</td>");
                mailContent.append("    </tr>");
            }
            mailContent.append("</table>");
            mailContent.append("<br>");
        }
        
        mailContent.append("</body>");
        mailContent.append("</html>");
        
        if (logger.isInfoEnabled())
        {
            logger.info(mailContent.toString());
        }
        
        return mailContent.toString();
    }
    
    /**
     * 新启一个线程,通过webservice触发“营销事件触发接口”
     * 
     * @param businessType 业务编码
     * @param customerSimp 用户对象
     * @see [类、类#方法、类#成员]
     * @remark create wWX217192 2014/6/19 OR_SD_201403_1491
     */
    public void startMarketingEventThread(NserCustomerSimp customerSimp)
    {
        
        // 若开关开启，则启用
        if ("open".equals(PublicCache.getInstance().getCachedData(Constants.SH_COMPAIGN_WEBSERVICE_SWITCH)))
        {
            // 根据事件类型获取配置的事件编码
            String eventCode = (String)PublicCache.getInstance()
                    .getCachedData(Constants.SH_COMPAIGN_WEBSERVICE_EVENTCODE);
            
            // wsUrl
            String wsUrl = (String)PublicCache.getInstance().getCachedData(Constants.SH_COMPAIGN_WEBSERVICE_WSURL);
            
            // 调用方法
            String operation = (String)PublicCache.getInstance()
                    .getCachedData(Constants.SH_COMPAIGN_WEBSERVICE_OPERATION);
            
            // 组装请求报文信息
            OMElement requestMsg = buildingMethodParam(eventCode, customerSimp.getServNumber());
            
            // 新启一个线程，触发“营销事件触发接口”
            MarketingEventThread marketingEventThread = new MarketingEventThread(wsUrl, operation, requestMsg);
            
            Thread thread = new Thread(marketingEventThread);
            thread.start();
        }
        
    }
    
    /**
     * 组织调用“营销事件触发接口”的方法参数
     * 
     * @param eventCode 事件编码
     * @param serNumber 服务号
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create wWX217192 2014/06/19 OR_SD_201403_1491
     */
    private OMElement buildingMethodParam(String eventCode, String servNum)
    {
        
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omSerNs = fac.createOMNamespace((String)PublicCache.getInstance()
                .getCachedData(Constants.SH_COMPAIGN_WEBSERVICE_OMSERNS), "");
        OMNamespace omXsdNs = fac.createOMNamespace((String)PublicCache.getInstance()
                .getCachedData(Constants.SH_COMPAIGN_WEBSERVICE_OMXSDNS), "");
        
        // 组装请求报文信息
        OMElement requestMsg = fac.createOMElement("getRecommendedOffer", omSerNs);
        
        // 组装requestHeader信息对象
        OMElement requestHeader = fac.createOMElement("requestHeader", omSerNs);
        
        // 外围渠道ID，各渠道调用时传入一级渠道ID
        OMElement accessChannel = fac.createOMElement("accessChannel", omXsdNs);
        accessChannel.setText("bsacAtsv");
        requestHeader.addChild(accessChannel);
        
        // 固定为101
        OMElement beId = fac.createOMElement("beId", omXsdNs);
        beId.setText("101");
        requestHeader.addChild(accessChannel);
        
        // 固定为2
        OMElement language = fac.createOMElement("language", omXsdNs);
        language.setText("2");
        requestHeader.addChild(language);
        
        // 固定为：Campaign
        OMElement operator = fac.createOMElement("operator", omXsdNs);
        operator.setText("Campaign");
        requestHeader.addChild(operator);
        
        // 固定为：qQF2KXpjc+ZV4yRDAO9GXQ==
        OMElement password = fac.createOMElement("password", omXsdNs);
        password.setText("qQF2KXpjc+ZV4yRDAO9GXQ==");
        requestHeader.addChild(password);
        
        // 填空
        OMElement transactionId = fac.createOMElement("transactionId", omXsdNs);
        transactionId.setText("");
        requestHeader.addChild(transactionId);
        
        requestMsg.addChild(requestHeader);
        
        // 组装eventBody信息对象
        OMElement eventBody = fac.createOMElement("eventBody", omSerNs);
        
        // 事件编码
        OMElement eventCodeElt = fac.createOMElement("eventCode", omXsdNs);
        eventCodeElt.setText(eventCode);
        eventBody.addChild(eventCodeElt);
        
        // 号码
        OMElement msisdn = fac.createOMElement("msisdn", omXsdNs);
        msisdn.setText(servNum);
        eventBody.addChild(msisdn);
        
        requestMsg.addChild(eventBody);
        requestMsg.build();
        
        return requestMsg;
        
    }
    
    public String getMonth()
    {
        return month;
    }
    
    public void setMonth(String month)
    {
        this.month = month;
    }
    
    public MonthFeeBean getMonthFeeBean()
    {
        return monthFeeBean;
    }
    
    public void setMonthFeeBean(MonthFeeBean monthFeeBean)
    {
        this.monthFeeBean = monthFeeBean;
    }
    
    public String getBillcycle()
    {
        return billcycle;
    }
    
    public void setBillcycle(String billcycle)
    {
        this.billcycle = billcycle;
    }
    
    public String getNeedCheckMail()
    {
        return needCheckMail;
    }
    
    public void setNeedCheckMail(String needCheckMail)
    {
        this.needCheckMail = needCheckMail;
    }
    
    public String getCurMenuId()
    {
        return curMenuId;
    }
    
    public void setCurMenuId(String curMenuId)
    {
        this.curMenuId = curMenuId;
    }
    
    public String getTelnum()
    {
        return telnum;
    }
    
    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }
    
    public String getCustName()
    {
        return custName;
    }
    
    public void setCustName(String custName)
    {
        try
        {
            this.custName = URLDecoder.decode(custName, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }
    
    public String getBrandID()
    {
        return brandID;
    }
    
    public void setBrandID(String brandID)
    {
        this.brandID = brandID;
    }
    
    // add begin cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    public String getBillAddInfor()
    {
        return billAddInfor;
    }
    
    public void setBillAddInfor(String billAddInfor)
    {
        this.billAddInfor = billAddInfor;
    }
    
    // add end cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    
    public List<CyclePO> getCycleList()
    {
        return cycleList;
    }
    
    public void setCycleList(List<CyclePO> cycleList)
    {
        this.cycleList = cycleList;
    }
    
    public String getCycle()
    {
        return cycle;
    }
    
    public void setCycle(String cycle)
    {
        this.cycle = cycle;
    }
    
    public String getStartdate()
    {
        return startdate;
    }
    
    public void setStartdate(String startdate)
    {
        this.startdate = startdate;
    }
    
    public String getEnddate()
    {
        return enddate;
    }
    
    public void setEnddate(String enddate)
    {
        this.enddate = enddate;
    }
    
    public String getAcctid()
    {
        return acctid;
    }
    
    public void setAcctid(String acctid)
    {
        this.acctid = acctid;
    }
    
    public String getUnionacct()
    {
        return unionacct;
    }
    
    public void setUnionacct(String unionacct)
    {
        this.unionacct = unionacct;
    }
    
    public String getCustname()
    {
        return custname;
    }
    
    public void setCustname(String custname)
    {
        this.custname = custname;
    }
    
    public String getBrandnm()
    {
        return brandnm;
    }
    
    public void setBrandnm(String brandnm)
    {
        this.brandnm = brandnm;
    }
    
    public String getProductnm()
    {
        return productnm;
    }
    
    public void setProductnm(String productnm)
    {
        this.productnm = productnm;
    }
    
    public String getSubsid()
    {
        return subsid;
    }
    
    public void setSubsid(String subsid)
    {
        this.subsid = subsid;
    }
    
    public String getSubsCreditName()
    {
        return subsCreditName;
    }
    
    public void setSubsCreditName(String subsCreditName)
    {
        this.subsCreditName = subsCreditName;
    }
    
    public Map<String, Object> getMap()
    {
        return map;
    }
    
    public String getRealbz()
    {
        return realbz;
    }
    
    public void setRealbz(String realbz)
    {
        this.realbz = realbz;
    }
    
    public String getNewflow()
    {
        return newflow;
    }
    
    public void setNewflow(String newflow)
    {
        this.newflow = newflow;
    }
    
    public String getCurdate()
    {
        return curdate;
    }
    
    public void setCurdate(String curdate)
    {
        this.curdate = curdate;
    }
    
}
