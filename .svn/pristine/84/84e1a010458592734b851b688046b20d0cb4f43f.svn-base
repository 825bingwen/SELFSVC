/*
 * 文 件 名:  HotAPPDownloadServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <热门APP应用下载service实现类>
 * 修 改 人:  jWX216858
 * 修改时间:  Jun 26, 2015
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.hotAPPDownload.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customize.sd.selfsvc.common.service.BaseServiceSDImpl;
import com.customize.sd.selfsvc.hotAPPDownload.dao.HotAPPDownloadDaoImpl;
import com.customize.sd.selfsvc.hotAPPDownload.model.APPInfoPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DESedeEncrypt;
import com.gmcc.boss.selfsvc.util.DocumentUtil;

/**
 * <热门APP应用下载service实现类>
 * <功能详细描述>
 * 
 * @author  jWX216858
 * @version  [版本号, Jun 26, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本 OR_SD_201506_152_山东_在自助终端增加“热门APP应用”下载]
 */
@Service
@Transactional(noRollbackFor=ReceptionException.class)
public class HotAPPDownloadServiceImpl extends BaseServiceSDImpl implements IHotAPPDownloadService 
{
	/**
     * 日志
     */
    private static Log logger = LogFactory.getLog(HotAPPDownloadServiceImpl.class);
	
	@Autowired
	private HotAPPDownloadDaoImpl appDownloadDao;
	
	/**
     * <根据app应用id获取app描述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
	@Override
	public APPInfoPO getAppInfoById(String appId) 
	{
		return appDownloadDao.getAppInfoById(appId);
	}

	/**
     * <获取所有的app应用>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
	@Override
	public List<List<APPInfoPO>> getAppInfoList() 
	{
		List<APPInfoPO> appInfoList = appDownloadDao.getAppInfoList();
		// 没有查询到App信息跳转到错误页面
		if (null == appInfoList || appInfoList.size() == 0)
		{
			throw new ReceptionException("没有查询到相应的APP应用信息！");
		}
		
		String lastApptype = "";
        String nextApptype = "";
        String currApptype = "";
        APPInfoPO appInfo = null;
        List<List<APPInfoPO>> appInfoLists = new ArrayList<List<APPInfoPO>>();
        List<APPInfoPO> subAppInfoList = null;
        for (int i = 0; i < appInfoList.size(); i++)
        {
        	appInfo = appInfoList.get(i);
        	nextApptype = appInfo.getAppType();
        	if (!currApptype.equalsIgnoreCase(nextApptype))
            {
                if (subAppInfoList != null && subAppInfoList.size() > 0)
                {
                	appInfoLists.add(subAppInfoList);
                }
                
                currApptype = nextApptype;
            }
        	if (!lastApptype.equalsIgnoreCase(currApptype))
        	{
        		lastApptype = currApptype;
        		subAppInfoList = new ArrayList<APPInfoPO>();
        	}
        	subAppInfoList.add(appInfo);
        }
        if (null != subAppInfoList && subAppInfoList.size() > 0)
        {
        	appInfoLists.add(subAppInfoList);
        }
        return appInfoLists;
	}

	/**
     * <短信发送app下载地址>
     * <功能详细描述>
     * @param menuId 当前菜单
	 * @param servNumber 手机号码
	 * @param appInfo app信息
	 * @throws UnsupportedEncodingException 
     * @see [类、类#方法、类#成员]
     */
	@Override
	public void sendAddress(String menuId, String servNumber, APPInfoPO appInfo) 
	{
		// 获取客户信息
        NserCustomerSimp customer = getCustomer();
        
        // 终端机信息
        TerminalInfoPO termInfo = getTermInfo();
		
        // 手机号码
        String telnum = (null == customer) ? servNumber : customer.getServNumber();
        
        // 菜单id
        menuId = (null == menuId) ? "" : menuId;
        
		// 组装报文头信息
        MsgHeaderPO header = new MsgHeaderPO(menuId, termInfo.getOperid(), termInfo.getTermid(),
                "", MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
		
		Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        // 手机号码
        DocumentUtil.addSubElementToEle(eBody, "telnum", telnum);

        // 短信模板编号
        DocumentUtil.addSubElementToEle(eBody, "templateno", "Atsv0001");
        
        // 应用名称
        String appName = "";
        try 
        {
        	appName = java.net.URLDecoder.decode(appInfo.getAppName(), "UTF-8");
		} 
        catch (UnsupportedEncodingException e)
        {
        	insertRecLog(Constants.SH_HOTAPPDOWNLOAD, "", "", Constants.RECSTATUS_FALID, "app链接下发失败，请重试！");
			throw new ReceptionException("app链接下发失败，请重试！");
		}
        
        // 发送端新内容
        String smsparam = CommonUtil.getParamValue(Constants.SH_APPDOWNLOAD_CONTENT);
        smsparam = "1" + smsparam.replace("[%1]", appName).replace("[%2]", appInfo.getShortAddress()); 
        
        // 参数列表
        DocumentUtil.addSubElementToEle(eBody, "smsparam", smsparam);
        
        // 是否服务间调用,1:是；0：否
        DocumentUtil.addSubElementToEle(eBody, "isrvcall", "1");
        
        // 短信发送app下载地址
        ReturnWrap rw = selfSvcCall.sendAddress(header, doc);
        
        if(SSReturnCode.ERROR == rw.getStatus())
        {
        	String msg = ("".equals(rw.getReturnMsg())) ? "app下载链接发送短信失败" : rw.getReturnMsg();
            insertRecLog(Constants.SH_HOTAPPDOWNLOAD, "", "", Constants.RECSTATUS_FALID, msg);
            throw new ReceptionException(msg);
        }
        
        // 办理成功，添加业务日志
        insertRecLog(Constants.SH_HOTAPPDOWNLOAD, "", "", Constants.RECSTATUS_SUCCESS, "app下载链接发送成功!");
	}
	
	/**
	 * <用户登录，登录成功后短信发送app下载地址>
     * <功能详细描述>
	 * @param menuId 当前菜单
	 * @param servNumber 手机号码
	 * @param appInfo app信息
	 * @param password 服务密码
	 */
	@SuppressWarnings("unchecked")
	public void customerLogin(String menuId, String servNumber, APPInfoPO appInfo, String password) 
	{
        // 终端机信息
        TerminalInfoPO termInfo = getTermInfo();
		
		try 
		{
			// 对密码进行加密
			DESedeEncrypt encrypt = new DESedeEncrypt();
			password = encrypt.encrypt(null == password ? "" : password);
		} 
		catch (Exception e)
		{
			logger.error("山东密码验证时，加密密码错误!", e);
		}
        
        // modify by lKF60882 OR_huawei_201703_629  【山东移动接口迁移专题】-自助终端已有接口1 2017-4-13 begin
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servNumber);
        
        // 0：密码校验 1：密码修改 2：密码重置，不校验oldpwd
        paramMap.put("subcmdid", "0");
        paramMap.put("oldpwd", password);
        paramMap.put("menuID", menuId);
        paramMap.put("touchOID", "");
        paramMap.put("newpwd", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.resetPassword(paramMap);
        // modify by lKF60882 OR_huawei_201703_629  【山东移动接口迁移专题】-自助终端已有接口1 2017-4-13 end
        
        if(SSReturnCode.ERROR == rw.getStatus())
        {
        	String msg = ("".equals(rw.getReturnMsg())) ? "用户登录验证失败，请重试" :rw.getReturnMsg();
            insertRecLog(Constants.SH_HOTAPPDOWNLOAD, "", "", Constants.RECSTATUS_FALID, msg);
            throw new ReceptionException(msg);
        }
        
		this.sendAddress(menuId, servNumber, appInfo);
	}
}
















