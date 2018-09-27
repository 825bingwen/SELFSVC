package com.customize.hub.selfsvc.feeservice.action;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import com.gmcc.boss.selfsvc.common.BaseAction;

public class BillPieImgAction extends BaseAction
{
	private static final long serialVersionUID = 1234234324L;

	public String printStructImg() throws IOException
	{
		ServletOutputStream sos = null;
		try
		{
			HttpServletRequest request = getRequest();
			
			HttpServletResponse response = getResponse();
			
			request.setCharacterEncoding("UTF-8");
			
			sos = response.getOutputStream();
			
			String structInfo = request.getParameter("structInfo");
			
			System.out.println("********************************");
			System.out.println(structInfo);
			System.out.println("********************************");
			
			if(null == structInfo || "".equals(structInfo))
			{
				return null;
			}
			
			structInfo = URLDecoder.decode(structInfo, "UTF-8");
			
			
			String height = request.getParameter("height");
			String width = request.getParameter("width");
			
			JFreeChart chart = JFreeChartFactory.generatePieChart(structInfo);
			
			ChartUtilities.writeChartAsJPEG(sos, chart,
					Integer.parseInt(width), Integer.parseInt(height));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("printStructImg() error!");
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
