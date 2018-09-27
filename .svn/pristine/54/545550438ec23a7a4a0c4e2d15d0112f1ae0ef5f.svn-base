package com.customize.hub.selfsvc.feeservice.action;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;

/**
 * 
 * <一句话功能简述>详单查询Bean类
 * 
 * @author
 * @version [版本号, Jun 15, 2011]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class JFreeChartFactory
{
    
    public static JFreeChart generatePieChart(String mapInfo) throws Exception
    {
    	JFreeChart chart;
        DefaultPieDataset data = new DefaultPieDataset();
        
        String[] rowStr = mapInfo.split(",");
        
        double itemFees = 0.00d;
        
        for(int i=0; i<rowStr.length; i++)
        {
        	 String[] array = rowStr[i].split("%");
        	 String feeItem = array[0];
        	 double fee = Double.parseDouble(array[1])/100;
        	 
        	 if(0d == fee)
        	 {
        		continue;
        	 }
        	 
        	 if(i < rowStr.length -1)
        	 {
        		 data.setValue(feeItem, fee/getTotalFee(mapInfo));
        		 itemFees += fee/getTotalFee(mapInfo);
        	 }
        	 else
        	 {
        		 data.setValue(feeItem, 1-itemFees);
        	 }
        	 
        }
        chart = ChartFactory.createPieChart("", data, true, true, false);
        chart.setBackgroundPaint(new Color(236, 232, 220));
        
        LegendTitle legend = chart.getLegend(0);
        legend.setItemFont(new Font("宋体", Font.BOLD, 14));
        legend.setPosition(RectangleEdge.RIGHT);
        
        PiePlot plot = (PiePlot)chart.getPlot();        
        plot.setSectionOutlinesVisible(false);
        chart.removeLegend();
       
        return chart;
    }
    
    public static JFreeChart generateBarChart(String mapInfo) throws Exception
    {
        // 设置数据
    	JFreeChart freeChart;
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        DecimalFormat df = new DecimalFormat("0.00");
        String[] rowStr = mapInfo.split(",");
        for(int i=0; i<rowStr.length; i++)
        {
        	 String[] array = rowStr[i].split("%");
        	 String month = array[0];
        	 
        	 double fee = Double.parseDouble(array[1])/100;
        	 
        	 fee = Double.parseDouble(df.format(fee));
        	 
        	 data.addValue(fee, Double.valueOf(fee), month);
        }
       
        // 生成JFreeChart对象
        freeChart = ChartFactory.createBarChart("费用", "", "", data, PlotOrientation.VERTICAL, true, true, false);
        freeChart.setBackgroundPaint(new Color(202, 227, 205));
        freeChart.setBorderPaint(Color.BLACK);
        
        CategoryPlot plot = (CategoryPlot)freeChart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(212, 212, 214));
        // 横坐标网格线 
        //plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinePaint(new Color(170, 170, 170));
        plot.setRangeGridlinesVisible(true);
        
        // 获取横坐标
        CategoryAxis domainAxis = plot.getDomainAxis();
        // 设置横坐标的标题字体和大小
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 13));
        // 设置横坐标的坐标值的字体颜色  
        domainAxis.setTickLabelPaint(Color.BLACK);
        // 设置横坐标的坐标值的字体  
        domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 13));
        // 设置横坐标的显示  
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.5));
        
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setItemMargin(-1);
        renderer.setOutlinePaint(Color.BLACK);
        renderer.setPaint(new Color(146,150,199));
        // renderer.setDrawBarOutline(true);
     
        freeChart.removeLegend();
        
        Font font = new java.awt.Font("黑体", java.awt.Font.CENTER_BASELINE, 14);
        // 设置图例标题 
        TextTitle title = new TextTitle("费用");
        title.getBackgroundPaint();
        title.setFont(font);
        // 设置标题的字体颜色
        title.setPaint(Color.BLACK);
        freeChart.setTitle(title);
        
        return freeChart;
    }
   
    private static double getTotalFee(String mapInfo)
    {
    	double total = 0.00d;
    	String[] rowStr = mapInfo.split(",");
        for(int i=0; i<rowStr.length; i++)
        {
        	 String[] array = rowStr[i].split("%");
        	 double fee = Double.parseDouble(array[1])/100;
        	 total+=fee;
        }
        if(0.00d == total)
        {
        	return 1;
        }
        return total;
    }
}