/*
 * 文 件 名:  HotAPPDownloadAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <热门APP应用下载Action>
 * 修 改 人:  jWX216858
 * 修改时间:  Jun 25, 2015
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.hotAPPDownload.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.customize.sd.selfsvc.hotAPPDownload.model.APPInfoPO;
import com.customize.sd.selfsvc.hotAPPDownload.service.IHotAPPDownloadService;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * <热门APP应用下载Action>
 * <功能详细描述>
 * 
 * @author  jWX216858
 * @version  [版本号, Jun 25, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本 OR_SD_201506_152_山东_在自助终端增加“热门APP应用”下载]
 */
@Controller
@Scope("prototype")
public class HotAPPDownloadAction extends BaseAction
{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 2275634866425023545L;
	
	/**
     * 日志
     */
    private static Log logger = LogFactory.getLog(HotAPPDownloadAction.class);
	
	/**
	 * 可下载的APP信息PO
	 */
	private transient APPInfoPO appInfoPO;
	
	/**
	 * 所有的app应用
	 */ 
	private List<List<APPInfoPO>> appInfoList;
	
	/**
	 * 热门app下载service
	 */
	@Autowired
	private transient IHotAPPDownloadService appDownloadService;
	
	/**
	 * 错误信息
	 */
	private String errormessage;
	
	/**
	 * 图片路径，即文件名
	 */
	private String picPath;
	
	/**
	 * 更多应用标志
	 */
	private String moreFlag = "0";
	
	/**
	 * 成功信息
	 */
	private String successMessage;
	
	/**
	 * 手机号码
	 */
	private String servNumber;
	
	/**
	 * 服务密码
	 */
	private String password;
	
	/**
     * app展示列表
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     */
	public String hotAppShow()
	{
		try
		{
			 appInfoList = appDownloadService.getAppInfoList();
		}
		catch (ReceptionException e)
		{
			logger.error(e.getMessage());
			setErrormessage(e.getMessage());
			return ERROR;
		}
		
		return "hotAppShow";
	}
	
	/**
     * 展示app详情
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     */
	public String showDetail()
	{
		appInfoPO = appDownloadService.getAppInfoById(appInfoPO.getAppId());
		return "showDetail";
	}
	
	/**
     * 跳转到用户登录页面
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     */
	public String inputNumber()
	{
		return "inputNumber";
	}
	
	/**
     * 用户已登录，异步发送app下载链接
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     */
	public void sendAdress()
	{
		String xml = "";
		try
		{
			appDownloadService.sendAddress(this.getCurMenuId(), servNumber, appInfoPO);
			xml = "1";
		}
		catch(ReceptionException e)
		{
			logger.error(e.getMessage());
			xml = "0";
		}
		this.writeXmlMsg(xml);
	}
	
	/**
     * 跳转到用户登录页面
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     */
	public String customerLogin()
	{
		try
		{
			appDownloadService.customerLogin(this.getCurMenuId(), servNumber, appInfoPO, password);
		}
		catch (ReceptionException e)
		{
			logger.error(e.getMessage());
			setErrormessage(e.getMessage());
			return "failed";
		}

		setSuccessMessage(appInfoPO.getAppName() + "下载链接已发送至您的手机，请注意查收！");
		return SUCCESS;
	}
	
	/**
     * 显示App图片
     * 
     * @throws Exception
     * 
     * @see [类、类#方法、类#成员]
     */
	public void showAppImg() throws Exception
	{
		// 初始化输入输出流及其他参数
		OutputStream out = null;
		InputStream in = null;
		byte[] buf = new byte[1024];
		int len = 0;
		try
		{
			// 获取图片绝对路径
			String imagePath = CommonUtil.getParamValue(Constants.APPIMGPATH);
			
			File file = new File(imagePath + System.getProperty("file.separator") + picPath);
			
			in = new FileInputStream(file);
			out = getResponse().getOutputStream();
			
			while (-1 != (len = in.read(buf)))
			{
				out.write(buf, 0, len);
			}
		}
		catch (IOException e)
		{
			logger.error("读取相应的图片失败：" + ExceptionUtils.getFullStackTrace(e));
            throw e;
		}
		finally
		{
			CommonUtil.closeStream(out);
            CommonUtil.closeStream(in);
		}
	}
	
	public APPInfoPO getAppInfoPO() {
		return appInfoPO;
	}

	public void setAppInfoPO(APPInfoPO appInfoPO) {
		this.appInfoPO = appInfoPO;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getMoreFlag() {
		return moreFlag;
	}

	public void setMoreFlag(String moreFlag) {
		this.moreFlag = moreFlag;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getServNumber() {
		return servNumber;
	}

	public void setServNumber(String servNumber) {
		this.servNumber = servNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<List<APPInfoPO>> getAppInfoList() {
		return appInfoList;
	}

	public void setAppInfoList(List<List<APPInfoPO>> appInfoList) {
		this.appInfoList = appInfoList;
	}

}
