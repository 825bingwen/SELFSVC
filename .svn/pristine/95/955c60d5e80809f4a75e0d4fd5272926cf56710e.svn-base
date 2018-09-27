package com.customize.nx.selfsvc.monthbill.action;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

import com.customize.nx.selfsvc.bean.MonthBillBean;
import com.customize.sd.selfsvc.feeService.model.BillPO;
import com.customize.sd.selfsvc.feeService.model.CyclePO;
import com.customize.sd.selfsvc.feeService.model.FeeGroupPO;
import com.customize.sd.selfsvc.feeService.model.FeePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 宁夏月账单查询功能
 * 
 * @author xkf29026
 * 
 */
public class MonthBillAction extends BaseAction
{
    
    private static Log logger = LogFactory.getLog(MonthBillAction.class);
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 调用接口bean
    private MonthBillBean monthBillBean;
    
    // 当前菜单
    private String curMenuId;
    
    // 账单集合
    private List billItems;
    
    // 月份
    private String month;
    
    // ------------------新版本账单-----------------------------------------------------------
    
    // 账期列表
    private List<CyclePO> cycleList = new ArrayList<CyclePO>();
    
    //---------------------帐期信息--------------------------------------------------------------
    
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
    
    //--------------------客户信息----------------------------------------------------------------
    
    // 客户名称
    private String custname;
    
    // 品牌
    private String brandnm;
    
    // 主体产品
    private String productnm;
    
    // 用户ID
    private String subsid;
    
    //--------------------新版账单信息-------------------------------------------------------------------------
    
    // 新版账单
    Map map;
    
    // 历史/实时
    private String realbz;
    
    // newstatus 0:老流程 1:新流程
    private String newflow;
    
    //add begin m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179
    /**
      * 月账单查询页面添加超链接的文本
      */
    private String textUrl;
    //add end m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179
    
    //--------------------------------------------------------------------------------------------------------
    
    /**
     * 转到选择账单月份页面
     * 
     * @return
     */
    public String selectBillMonth()
    {
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
        
        return "selectBillMonth";
    }
    
    /**
     * 月账单查询
     * 
     * @return
     */
    public String queryMonthBill()
    {        
        HttpServletRequest request = this.getRequest();
        
        // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        request.setAttribute("backStep", "-1");
        // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 调用接口进行月账单查询
        billItems = monthBillBean.qryMonthBill(customerSimp, terminalInfo, month, curMenuId);
        if (billItems != null && billItems.size() > 0)
        {
            return "monthBillDetail";
        }
        else
        {
            this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "1", "未查询到" + month + "月份的账单信息");
            request.setAttribute("errormessage", "未查询到用户" + customerSimp.getServNumber() + "的" + month + "月份的账单信息");
            return "error";
        }
    }
    
    /**
     * 查询账期
     * <功能详细描述>
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
        ReturnWrap rw = monthBillBean.getCustinfo(terminalInfo, curMenuId, customerSimp.getServNumber(), month);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 取返回对象
            Vector vector = (Vector)(rw.getReturnObject());
            
            // 客户信息
            CTagSet ctagset = (CTagSet)(vector.get(0));
            
            custname = ctagset.GetValue("custname");// 客户名称
            
            //add begin yWX163692 2013/11/21  OR_NX_201310_1186 客户界面信息模糊化展示
            custname = CommonUtil.getVagueName(custname);
            //add end yWX163692 2013/11/21  OR_NX_201310_1186 客户界面信息模糊化展示
            
            brandnm = ctagset.GetValue("brandnm");// 品牌
            productnm = ctagset.GetValue("productnm");// 主体产品
            subsid = ctagset.GetValue("subsid");// 用户ID
                
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
            else// 单账期
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
            
            // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            request.setAttribute("backStep", "-1");
            // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            
            // 返回
            return "error";
        }
        

        
    }
    
    /**
     * 查询账单（新老版本处理）
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String isNewOrOld() throws Exception
    {
        
        // 当前时间
        String crrentdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        
        if(Integer.parseInt(crrentdate)>=Integer.parseInt(startdate) && Integer.parseInt(crrentdate)<=Integer.parseInt(enddate))
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
        
        // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        request.setAttribute("backStep", "-1");
        // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 账单信息
        String factory = "9A3A9B26E157B508228301EF1F7BF352";// 厂家编码的密文标识，不能为空
        String arealist = "";
        arealist = "SCORE,PKGINFO,BILLTREND,RECOMMEND,ACCTBALANCE,SPBILL,BILLINFO,INOUT";// 区域列表
        ReturnWrap rw = monthBillBean.qryMonthBill_new(terminalInfo, "10000100", customerSimp.getServNumber(), acctid, subsid, cycle, startdate, enddate, unionacct, customerSimp.getRegionID(), arealist, factory);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            map = (Map)rw.getReturnObject();
            
            if (map != null)
            {
                // 当月费用结构图
                if (map.get("billfixed") != null)
                {
                    request.getSession().setAttribute(customerSimp.getServNumber()+"_billfixed", (List<Object>)map.get("billfixed"));
                }
                
                // 消费趋势图
                if (map.get("billhalfyeartrend") != null)
                {
                    request.getSession().setAttribute(customerSimp.getServNumber()+"_billhalfyeartrend", (List<Object>)map.get("billhalfyeartrend"));
                }
                
                // 成功
                this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "0", "查询账期" + startdate + " - " + enddate + "的实时账单信息成功");
            }
            else
            {
                request.setAttribute("errormessage", "查询账单信息失败！");
                
                // 失败
                this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "1", "未查询到账期" + startdate + " - " + enddate + "的实时账单信息");
                
                // 返回
                return "error";
            }
        }
        else
        {
            request.setAttribute("errormessage", "查询账单信息失败！");
            
            // 失败
            this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "1", "未查询到账期" + startdate + " - " + enddate + "的实时账单信息");
            
            // 返回
            return "error";
        }
        
        // 返回
        return "qryMonthBill_real_new";
    }
    
    /**
     * 查询账单信息_新版实时
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String qryMonthBill_new() throws Exception
    {
        HttpServletRequest request = this.getRequest();
        
        // modify begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        request.setAttribute("backStep", "-1");
        // modify end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 账单信息
        String factory = "9A3A9B26E157B508228301EF1F7BF352";// 厂家编码的密文标识，不能为空
        String arealist = "";
        arealist = "SCORE,PKGINFO,BILLTREND,RECOMMEND,ACCTBALANCE,SPBILL,BILLINFO,INOUT";// 区域列表
        ReturnWrap rw = monthBillBean.qryMonthBill_new(terminalInfo, "10000100", customerSimp.getServNumber(), acctid, subsid, cycle, startdate, enddate, unionacct, customerSimp.getRegionID(), arealist, factory);
       
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            map = (Map)rw.getReturnObject();
            
            if (map != null)
            {
                // 当月费用结构图
                if (map.get("billfixed") != null)
                {
                    request.getSession().setAttribute(customerSimp.getServNumber()+"_billfixed", (List<Object>)map.get("billfixed"));
                }
                
                // 消费趋势图
                if (map.get("billhalfyeartrend") != null)
                {
                    request.getSession().setAttribute(customerSimp.getServNumber()+"_billhalfyeartrend", (List<Object>)map.get("billhalfyeartrend"));
                }
                
                // 成功
                this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "0", "查询账期" + startdate + " - " + enddate + "的历史账单信息成功");
            }
            else
            {
                request.setAttribute("errormessage", "查询账单信息失败！");
                
                // 失败
                this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "1", "未查询到账期" + startdate + " - " + enddate + "的历史账单信息");
                
                // 返回
                return "error";
            }
        }
        else
        {
            request.setAttribute("errormessage", "查询账单信息失败！");
            
            // 失败
            this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "1", "未查询到账期" + startdate + " - " + enddate + "的实时账单信息");
            
            // 返回
            return "error";
        }
        
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
        List<Object> obj_billfixed = (List<Object>)request.getSession().getAttribute(customerSimp.getServNumber()+"_billfixed");

        try
        {
            // 设定生成图片
            response.setContentType("image/jpeg");
            
            // 获得费用明细
            DefaultPieDataset data = new DefaultPieDataset();
            for(Object obj:obj_billfixed)
            {
                
                if (obj instanceof FeePO)
                {
                    FeePO feePO = (FeePO)obj;
                    if ("1".equals(feePO.getIsshow()))
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
                        if ("1".equals(feePO.getIsshow()))
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
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}",NumberFormat.getNumberInstance(),new DecimalFormat("0.00%")));// 去掉标签

            // 右边框显示
            plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}", NumberFormat.getNumberInstance(),new DecimalFormat("0.00%")));

            LegendTitle legendtitle = new LegendTitle(chart.getPlot());
            BlockContainer blockcontainer = new BlockContainer(new BorderArrangement());
            blockcontainer.setBorder(new BlockBorder(2D, 2D, 2D, 2D));
            BlockContainer blockcontainer1 = legendtitle.getItemContainer();
            blockcontainer.add(blockcontainer1);
            legendtitle.setWrapper(blockcontainer);
            legendtitle.setPosition(RectangleEdge.LEFT);
            chart.addSubtitle(legendtitle);
            ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 450, 300, null);
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 消费趋势图_新版
     * <功能详细描述>
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
        List<BillPO> obj_billhalfyeartrend = (List<BillPO>)request.getSession().getAttribute(customerSimp.getServNumber()+"_billhalfyeartrend");

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
            
            //double[][] data = new double[][] { { 672, 766, 223, 540, 126 } }; 
            String[] rowKeys = { "" }; 
            //String[] columnKeys = { "北京", "上海", "广州", "成都", "深圳" }; 
            double[][] data = new double[1][obj_billhalfyeartrend.size()];
            String[] columnKeys = new String[obj_billhalfyeartrend.size()];

            for (int i=0;i<obj_billhalfyeartrend.size();i++)
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
            plot.setRangeGridlinesVisible(true);   
            
            // 虚线色彩   
            plot.setRangeGridlinePaint(Color.gray);            
      
            // 数据轴精度   
            NumberAxis vn = (NumberAxis) plot.getRangeAxis();
            DecimalFormat df = new DecimalFormat("#0.00");   
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
            // domainAxis.setSkipCategoryLabelsToFit(true);   
      
            plot.setDomainAxis(domainAxis);   
            // 设置柱图背景色（注意，系统取色的时候要使用16位的模式来查看颜色编码，这样比较准确）   
            plot.setBackgroundPaint(new Color(255, 255, 204));   
      
            // y轴设置   
            ValueAxis rangeAxis = plot.getRangeAxis();   
            rangeAxis.setLabelFont(labelFont);   
            rangeAxis.setTickLabelFont(labelFont);   
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
            renderer.setBaseOutlinePaint(Color.BLACK);   
            // 设置柱子边框可见   
            renderer.setDrawBarOutline(true);   
      
            // 设置柱的颜色   
            renderer.setSeriesPaint(0, new Color(204, 255, 255));   
            renderer.setSeriesPaint(1, new Color(153, 204, 255));   
            renderer.setSeriesPaint(2, new Color(51, 204, 204));   
      
            // 设置每个地区所包含的平行柱的之间距离   
            renderer.setItemMargin(0.0);   
      
            // 显示每个柱的数值，并修改该数值的字体属性   
            renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());   
            renderer.setBaseItemLabelsVisible(true);   
            plot.setRenderer(renderer);   
            
            // 设置柱的透明度   
            plot.setForegroundAlpha(1.0f);   

            // ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 100,chart, 300, 120, null);
            ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 450, 300, null);
            
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 在月账单页面添加超链接
     * 在月账单页面中出现一段文字，点击可进入新的页
     * @return 从当前页转向新的页面
     * @see 
     * @remark create m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179
    */
    public String montnBillUrl()
    {
    	//文字链接所指向内容
    	textUrl = (String) PublicCache.getInstance().getCachedData(Constants.MONTHBILL_TEXT_URL);
        return "qrymonthBillUrl";
    }
    
    public MonthBillBean getMonthBillBean()
    {
        return monthBillBean;
    }
    
    public void setMonthBillBean(MonthBillBean monthBillBean)
    {
        this.monthBillBean = monthBillBean;
    }
    
    public String getMonth()
    {
        return month;
    }
    
    public void setMonth(String month)
    {
        this.month = month;
    }

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public List getBillItems()
    {
        return billItems;
    }

    public void setBillItems(List billItems)
    {
        this.billItems = billItems;
    }

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

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getCustname()
    {
        return custname;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
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

    public Map getMap()
    {
        return map;
    }

    public void setMap(Map map)
    {
        this.map = map;
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
    
	public String getTextUrl() 
	{
		return textUrl;
	}

	public void setTextUrl(String textUrl) 
	{
		this.textUrl = textUrl;
	}
    
}
