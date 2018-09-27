package com.customize.hub.selfsvc.feeservice.action;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import com.gmcc.boss.selfsvc.common.BaseAction;

public class BillBarImgAction extends BaseAction
{
	/**
	 * ×¢ÊÍÄÚÈÝ
	 */
	private static final long serialVersionUID = 12321321L;

	public String printTrendImg() throws IOException
	{
		ServletOutputStream sos = null; 
		try
		{
			HttpServletRequest request = getRequest();
			
			HttpServletResponse response = getResponse();
			
			request.setCharacterEncoding("UTF-8");
			
			sos = response.getOutputStream();
			
			String trendInfo = request.getParameter("trendInfo");
			
			System.out.println("###########################");
			System.out.println(trendInfo);
			System.out.println("###########################");
			
			if(null == trendInfo || "".equals(trendInfo))
			{
				return null;
			}
			trendInfo = URLDecoder.decode(trendInfo, "UTF-8");
			
			String height = request.getParameter("height");
			String width = request.getParameter("width");
			
			JFreeChart chart = JFreeChartFactory.generateBarChart(trendInfo);
			
			ChartUtilities.writeChartAsJPEG(sos, chart,
					Integer.parseInt(width), Integer.parseInt(height));
			
			sos.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("printTrendImg() error!");
			return null;
		}
		finally
		{
			if(null != sos)
			{
				sos.close();
			}
		}
		return null;
	}
}
