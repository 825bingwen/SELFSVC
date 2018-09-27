/*
 * 文件名：SelfSvcCallImpl.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：自助终端系统通版接口调用实现类，调用统一接口平台
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增
 * 
 */
package com.gmcc.boss.selfsvc.call;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.sd.selfsvc.po.NcodePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Axis2Client;
import com.gmcc.boss.selfsvc.common.BusiCodeConstants;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.quickpublish.model.MultiProdCommitPO;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdCommitPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DESedeEncrypt;
import com.gmcc.boss.selfsvc.util.DocumentUtil;


/**
 * 山东自助终端系统接口调用实现类，调用统一接口平台 <一句话功能简述> <功能详细描述>
 * 
 * @author g00140516
 * @version 1.0, 2011/05/31
 * @see 
 * @since 
 */
public class SelfSvcCallImpl implements SelfSvcCall
{
    private static Log logger = LogFactory.getLog(SelfSvcCallImpl.class);
    
    private IntMsgUtil intMsgUtil;
    
    private SocketUtil socketUtil;
    
    public SocketUtil getSocketUtil()
    {
        return socketUtil;
    }
    
    public void setSocketUtil(SocketUtil socketUtil)
    {
        this.socketUtil = socketUtil;
    }
    
    /**
     * 查询家庭网成员
     * 
     * @param map 参数
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark create kWX211786 May 23, 2014 版本号 需求/BUG编号：OR_huawei_201404_1115_山东_家庭网成员添加功能
    */
    public ReturnWrap queryFamilyMemService(Map<String,String> map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String menuid = (String)map.get("curMenuId");
            
            // 手机号码
            eBody.addElement("telnum").addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_familymeminfo_sd", menuid, "", "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询家庭网成员接口失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 添加家庭网成员
     * 
     * @param map 参数
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark create kWX211786 May 23, 2014 版本号 需求/BUG编号：OR_huawei_201404_1115_山东_家庭网成员添加功能
    */
    public ReturnWrap addFamilyMem(Map<String, String> map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String menuid = (String)map.get("curMenuId");
            String servNumber = (String)map.get("servNumber");
            String memTelnum = (String)map.get("memTelnum");
            String shortNum = (String)map.get("shortNum");
            
            // 手机号码
            eBody.addElement("servNumber").addText(servNumber);
            eBody.addElement("memTelnum").addText(memTelnum);
            eBody.addElement("shortNum").addText(shortNum);
            //是否订购成员必选优惠 1：是 其他：否
            eBody.addElement("addMustPriv").addText("0");
            //主体产品编码
            eBody.addElement("prodId").addText("");
            //优惠编码
            eBody.addElement("privId").addText("");
            
      
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_add_familymem_sd", menuid, "", "1", servNumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("添加家庭网成员接口失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
	/**
     * 服务密码验证
     * 
     * @param map
     * @return  ReturnWrap
     * @remark create by hWX5316476 2014-05-13 OR_huawei_201405_235  自助终端接入EBUS一阶段_获取用户信息
     */
    public ReturnWrap checkUserPassword(Map<String, String> map)
    {
    	return null;
    }
    
    /**
     * 获取用户信息
     * 
     * @param map
     * @return
     * @remark create by hWX5316476 2014-05-13 OR_huawei_201405_235  自助终端接入EBUS一阶段_获取用户信息
     */
    public ReturnWrap getUserInfoHub(Map<String, String> map)
    {
    	return null;
    }
    
    /**
     * 使用手机号码、服务密码进行身份认证
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap getUserInfoWithPwd(Map map)
    {
        try
        {            
            String telnumber = (String)map.get("telnum");
            String password = (String)map.get("password");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            // 是否校验密码
            Element eReq_isCheck = eBody.addElement("ischeckpass");
            eReq_isCheck.addText("1");
            
            // 密码
            Element eReq_password = eBody.addElement("password");
            eReq_password.addText(password);
            
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_userinfo", "10001001", "", "1", telnumber, operID, termID);
            // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("服务密码认证失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 无密码登录
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap getUserInfo(Map map)
    {
        try
        {            
            String telnumber = (String)map.get("telnum");
            String password = (String)map.get("password");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            // 是否校验密码
            Element eReq_isCheck = eBody.addElement("ischeckpass");
            eReq_isCheck.addText("0");
            
            // 密码
            Element eReq_password = eBody.addElement("password");
            eReq_password.addText(password);
            
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            // modify begin  fwx439896 2017-8-7 OR_huawei_201704_411_【山东移动接口迁移专题】-用户信息查询接口拆分
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_USERINFO))
            {
                Element accessType = eBody.addElement("ACCESSTYPE");
                accessType.addText(Constants.CHANNEL_ID);
                
                //是否模糊化 1模糊化  0不模糊化
                Element isblurry = eBody.addElement("ISBLURRY");
                isblurry.addText("0");
            }
            
            // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_USERINFO, "10001001", "", "1", telnumber, operID, termID);
            // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            
            ReturnWrap rw = intMsgUtil.invoke(docXML);
            
            // 调能开,出参转为小写
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_USERINFO) && null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                // 出参统一转小写
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                String[] openEbusRtn = {"subsname", "prodid", "viptypeid", "brandid", "brandname"};
                String[] destRtn = {"subname", "productid", "viptype", "productgroup", "productname"};
                tagSet = CommonUtil.rtnConvert(tagSet, BusinessIdConstants.CLI_QRY_USERINFO, openEbusRtn, destRtn);
                rw.setReturnObject(tagSet);
            }
            
            return rw;
            // modify end  fwx439896 2017-8-7 OR_huawei_201704_411_【山东移动接口迁移专题】-用户信息查询接口拆分
        }
        catch (Exception e)
        {
            logger.error("认证失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 月初扣费查询
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryMonthDeduct(Map map)
    {
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("menuid");
        String telnumber = (String)map.get("telnumber");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        // 封装包体入参
        // 手机号码
        Element eReq_telnum = eBody.addElement("telnum");
        eReq_telnum.addText(telnumber);
        
        Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_YCKF, menuid, touchoid, "1", telnumber, operID, termID);
        ReturnWrap rw = intMsgUtil.invoke(docXML);
        Vector vector = (Vector)rw.getReturnObject();
        
        Vector retVector = new Vector();
        if (vector != null && vector.size() == 2)
        {
            CTagSet tagset = (CTagSet)vector.get(0);
            
            // 能开出参key转为小写
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_YCKF))
            {
                tagset = CommonUtil.lowerTagKey(tagset);
                vector.set(0, tagset);
            }
            
            CRSet crset = (CRSet)vector.get(1);
            
            List listBaseFee = null;// //套餐信息费
            List listFuncFee = null;// //功能费
            List listSumPackage = null;// 包月费
            
            if ((crset != null) && (crset.GetRowCount() > 0))
            {
                // 套餐信息费
                if (!"0".equals(tagset.GetValue("sum_basefee")))
                {
                    listBaseFee = getMonthFeeList(crset, "3");
                }
                // 功能费
                if (!"0".equals(tagset.GetValue("sum_funcfee")))
                {
                    listFuncFee = getMonthFeeList(crset, "1");
                }
                // 包月费
                if (!"0".equals(tagset.GetValue("sum_packagefee")))
                {
                    listSumPackage = getMonthFeeList(crset, "2");
                }
            }
            retVector.add(listBaseFee);
            retVector.add(listFuncFee);
            retVector.add(listSumPackage);
            rw.setReturnObject(retVector);
        }
        
        return rw;
    }
    
    /**
     * 功能费、包月费、套餐信息费
     * <功能详细描述>
     * @param crset
     * @param typeStr
     * @return  List<List>
     * @see [类、类#方法、类#成员]
     */
    private List<List> getMonthFeeList(CRSet crset, String typeStr)
    {
        
        // 费用list
        List<List> feeList = new ArrayList<List>();
        
        for (int i = 0; i < crset.GetRowCount(); i++)
        {
            String type = crset.GetValue(i, 0) == null ? "" : crset.GetValue(i, 0);
            if (typeStr.equals(type))
            {
                List<String> list = new ArrayList<String>();
                
                list.add(crset.GetValue(i, 1));
                list.add(CommonUtil.fenToYuan(crset.GetValue(i, 2)));
                
                // 能开返回的格式转换
                if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_YCKF))
                {
                    list.add(CommonUtil.formatDate(crset.GetValue(i, 3).substring(0, 5), "MM-dd", "MM月dd日"));
                }
                else
                {
                    list.add(CommonUtil.formatDate(crset.GetValue(i, 3), "MM.dd", "MM月dd日"));
                }
                
                feeList.add(list);
            }
        }
        return feeList;
    }
    
   
    public void setIntMsgUtil(IntMsgUtil intMsgUtil)
    {
        this.intMsgUtil = intMsgUtil;
    }
    
    /**
     * 向用户发送随机密码短信
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap sendSMS(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String smsContent = "1" + (String)map.get("smsContent");
        String priority = (String)map.get("priority");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            //短信模板编号
            Element eReq_templateno = eBody.addElement("templateno");
            eReq_templateno.addText("Atsv0001");
            
            //参数列表
            // modify by lKF60882 OR_huawei_201703_629  【山东移动接口迁移专题】-自助终端已有接口1 2017-4-13 begin
            Element eReq_smsparam = null;
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_SENDSMSINFO))
            {
                eReq_smsparam = eBody.addElement("SMPARAM");
            }
            else
            {
                eReq_smsparam = eBody.addElement("smsparam");
            }
            // modify by lKF60882 OR_huawei_201703_629  【山东移动接口迁移专题】-自助终端已有接口1 2017-4-13 end
            
            eReq_smsparam.addText(smsContent);
            
            //是否服务间调用
            Element eReq_isrvcall = eBody.addElement("isrvcall");
            eReq_isrvcall.addText("1");
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_BUSI_SENDSMSINFO, menuID, touchOID, "1", telnum, operID, termID);
            return intMsgUtil.invoke(docXML);

        }
        catch (Exception e)
        {
            logger.error("发送短信失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 本机品牌资费及已开通功能
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryFavourable(Map map)
    {
        
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("telnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        String queryType = (String)map.get("queryType");
        
        try
        {
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 查询类型 1：品牌资费 2：已开通服务、优惠
            Element eReq_qrytype = eBody.addElement("qrytype");
            eReq_qrytype.addText(queryType);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_ZFINFO, menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        
        }
        catch (Exception e)
        {
            logger.error("查询本机品牌资费及已开通功能失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * PUK码查询
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryPUK(Map map)
    {
       try{ 
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,BusinessIdConstants.CLI_QRY_PUKCODE,menuid,touchoid,"1",telnumber,operid,atsvNum);
            ReturnWrap rw=intMsgUtil.invoke(docXML);
            
            //modify begin fwx439896 2017-04-19 OR_huawei_201703_629_【山东移动接口迁移专题】-自助终端已有接口1
            // 走能开 出参统一转小写
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_PUKCODE) && null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                tagSet = CommonUtil.lowerTagKey(tagSet);
                rw.setReturnObject(tagSet);
            }
            //modify end fwx439896 2017-04-19 OR_huawei_201703_629_【山东移动接口迁移专题】-自助终端已有接口1
          
            return rw ;
        }
        catch (Exception e)
        {
            logger.error("PUK码查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 本机状态查询
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryCurrentStatus(Map map)
    {
       try{
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
    
            String operid = (String)map.get("operid");
            String atsvNum = (String)map.get("termid");
            String telnumber = (String)map.get("telnum");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("menuid");
                       
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,BusinessIdConstants.CLI_QRY_USERSTATE,menuid,touchoid,"1",telnumber,operid,atsvNum);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("当前状态查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 号码归属地查询
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryUserRegionReq(Map map)
    {
        try{
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("qryServnuber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_NUMREGION, menuid, touchoid, "0", "999", operid, atsvNum);
            ReturnWrap rw = intMsgUtil.invoke(docXML);
           
            //modify begin fwx439896 2017-04-19 OR_huawei_201703_629_【山东移动接口迁移专题】-自助终端已有接口
            //走能开  出参统一转小写
            if(CommonUtil.isInvokeOpenEbus("cli_qry_numregion") && null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                tagSet = CommonUtil.lowerTagKey(tagSet);
                rw.setReturnObject(tagSet);
            }
            //modify end fwx439896 2017-04-19 OR_huawei_201703_629_【山东移动接口迁移专题】-自助终端已有接口
         
            return rw;
        }
        catch (Exception e)
        {
            logger.error("号码归属地查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 详单查询
     * 
     * @param map
     * @return ReturnWrap 
     * @see 
     * @remark modify g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
     */
    public ReturnWrap queryCDRList(Map map)
    {
        try
        {
            String telnum = (String)map.get("servNumber");
            String month = (String)map.get("month");
            String cdrType = (String)map.get("CDRType");
            String fee_type = (String)map.get("fee_type");
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String startTime = (String) map.get("startTime");
            String endTime = (String) map.get("endTime");
            String verifyCode = (String) map.get("verifyCode");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 月份
            Element eReq_month = eBody.addElement("month");
            eReq_month.addText(month);
            
            // 是否传送合计值
            Element eReq_count = eBody.addElement("count_flag");
            eReq_count.addText("1");
            
            // 通话清单查询标志
            Element eReq_query = eBody.addElement("query_flag");
            eReq_query.addText("");
            
            // 详单类型
            Element eReq_cdrtype = eBody.addElement("cdrtype");
            eReq_cdrtype.addText(cdrType);
            
            // 查询类型
            Element eReq_fee_type = eBody.addElement("fee_type");
            eReq_fee_type.addText(fee_type);
            
            // factory
            Element eReq_factory = eBody.addElement("factory");
            eReq_factory.addText("9A3A9B26E157B508228301EF1F7BF352");
            
            Element eReq_starttime = eBody.addElement("starttime");
            eReq_starttime.addText(startTime);
            
            Element eReq_endtime = eBody.addElement("endtime");
            eReq_endtime.addText(endTime);            
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_cdr", menuid, unicontact, "1", telnum, operID, termID, verifyCode);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("详单查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /** 
     * 详单查询权限验证_湖北
     * 
     * @param msgHeader 报文头信息
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap checkQueryAuth(MsgHeaderPO msgHeader)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // 调用后台接口
            return intMsgUtil.invoke("cli_check_cdrauth_hub", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("湖北验证详单查询权限失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 受理历史查询
     * <功能详细描述>
     * @param map
     * @return
     * @see  [类、类#方法、类#成员]
     */
    public ReturnWrap qryAllServiceHistory(Map map)
    {
        String startDate = (String)map.get("startDate");
        String endDate = (String)map.get("endDate");
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("servnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 开始日期
            Element eReq_startdate = eBody.addElement("startdate");
            eReq_startdate.addText(startDate);
            
            // 结束日期
            Element eReq_endDate = eBody.addElement("enddate");
            eReq_endDate.addText(endDate);
            
            Document docXML = intMsgUtil.createMsg(doc,BusinessIdConstants.CLI_QRY_RECHISTORY,menuid,touchoid,"1",telnumber,operid,atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("受理历史查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 缴费历史查询
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryChargeHistory(Map map)
    {
        try
        {
            String telnumber = (String)map.get("servNumber");
            String startDate = (String)map.get("startDate");
            String endDate = (String)map.get("endDate");
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 开始时间
            Element eReq_startdate = eBody.addElement("startdate");
            eReq_startdate.addText(startDate);
            
            // 结束时间
            Element eReq_enddate = eBody.addElement("enddate");
            eReq_enddate.addText(endDate);
            
            Document docXML = intMsgUtil.createMsg(doc,BusinessIdConstants.CLI_QRY_CHARGEHISTORY,menuid,unicontact,"1",telnumber,operID,termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("缴费历史查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 积分查询
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryScoreValue(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_scorevalue", menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("积分查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "积分查询接口出现异常");
        }
    }
    
    /**
     * 余额提醒
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryBalanceNotice(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            //modify begin fwx439896 2017-5-15 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理
            
            Document docXML = intMsgUtil.createMsg(doc,BusinessIdConstants.CLI_QRY_ALARMBALANCE,menuid,touchoid,"1",telnumber,operid,atsvNum);
            ReturnWrap  rw=intMsgUtil.invoke(docXML);
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_ALARMBALANCE) && null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            { 
                // 出参统一转小写  prepay_type  credit
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                String[] openEbusRtn = {"prepaytype", "notifyvalue"};
                String[] destRtn = {"prepay_type", "credit", };
                tagSet = CommonUtil.rtnConvert(tagSet, BusinessIdConstants.CLI_QRY_USERINFO, openEbusRtn, destRtn);
                rw.setReturnObject(tagSet);
            } 
            //modify end begin fwx439896 2017-5-15 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理
            return rw;
        }
        catch (Exception e)
        {
            logger.error("查询用户余额提醒阀值失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 获取余额提醒字典表数据
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap getDictItem(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String groupid = (String)map.get("groupid");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // group ID
            Element eReq_groupid = eBody.addElement("groupid");
            eReq_groupid.addText(groupid);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_dictitem", menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询余额提醒阀值列表失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 余额提醒值设置
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap setBalanceNotice(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String credit = (String)map.get("balanceAwake");
            String operType = (String)map.get("operType");/////////
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);

           //modify begin fwx439896 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
           if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_ALARMBALANCE))
           {
        	   // 余额提醒值
               Element eReq_credit = eBody.addElement("NOTIFYVALUE");   
               eReq_credit.addText(credit);
           }   
           else
           {
               // 余额提醒值
               Element eReq_credit = eBody.addElement("credit");
               eReq_credit.addText(credit);
               
               Element eReq_systemid = eBody.addElement("systemid");
               eReq_systemid.addText("bsacAtsv");
               
               Element eReq_operType = eBody.addElement("operType");
               eReq_operType.addText(operType);
           
           } 
           //modify end fwx439896 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
         
            Document docXML = intMsgUtil.createMsg(doc,BusinessIdConstants.CLI_BUSI_ALARMBALANCE,menuid,touchoid,"1",telnumber,operid,atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("余额提醒设置失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }

    /**
     * 根据nocde(新)查询产品,优惠的资费描述信息
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryFeeMessage(Map map)
    {
        String telnum = (String)map.get("servNumber");
        String nCode = (String)map.get("nCode");
        String menuid = (String)map.get("menuID");
        String unicontact = (String)map.get("contactID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // NCODE
            Element eReq_ncode = eBody.addElement("ncode");
            eReq_ncode.addText(nCode);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_PRODUCTFEE, menuid, unicontact, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("业务受理时，产品资费信息查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 调用能开时产品受理通用接口
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @Remark create by lWX431760 2017-07-19 OR_huawei_201706_780_【山东移动接口迁移专题】-自助终端新业务办理需求
     * @Remark modify by lWX431760 2017-09-29 OR_huawei_201706_781_【山东移动接口迁移专题】-自助终端新业务办理(同组业务)
     */
    public ReturnWrap recCommonServNK(MsgHeaderPO msgHeader, String nCode, String operType, String effectType, String param)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            //服务号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            //平台流水
            DocumentUtil.addSubElementToEle(eBody, "chnlrecoid", "");

            //推荐人号码
            DocumentUtil.addSubElementToEle(eBody, "recommender", "");

            //主体产品编码
            DocumentUtil.addSubElementToEle(eBody, "MAINPRODID","");

            //操作员
            DocumentUtil.addSubElementToEle(eBody, "OPERID",msgHeader.getOperId());

            //应收费用
            DocumentUtil.addSubElementToEle(eBody, "CONTRASTFEE","");
            
            //是否算费，1：不算费，否则根据原先逻辑算费
            DocumentUtil.addSubElementToEle(eBody, "NONEEDCALCFEE","1");

            //主体产品变更场景中既受理主体产品必选套餐，又受理其他可选包套餐场景时，必传
            DocumentUtil.addSubElementToEle(eBody, "PRODLIST", "");
            
            Element recprodlist = eBody.addElement("RECPRODLIST");
            
            // 增值产品编码
            DocumentUtil.addSubElementToEle(recprodlist, "PRODID", nCode);
            
            // 生效方式 Type_Immediate:立即 Type_NextCycle:次月 Type_Default:默认
            DocumentUtil.addSubElementToEle(recprodlist, "EFFECTTYPE", "Type_Default");
            
            DocumentUtil.addSubElementToEle(recprodlist, "OPTYPE", operType);
            
            // 附加属性
            DocumentUtil.addSubElementToEle(recprodlist, "ATTRPARA", "");
            
            // 原增值产品编码
            DocumentUtil.addSubElementToEle(recprodlist, "OLDPRODID", "");
            
            // 服务资源
            DocumentUtil.addSubElementToEle(recprodlist, "RESPARA", "");
            
            // 接口对应类型
            DocumentUtil.addSubElementToEle(recprodlist, "RELATYPE", "");
            
            // 是否按模板处理 默认为:0
            DocumentUtil.addSubElementToEle(recprodlist, "ISTEMPLATE", "0");
            
            // 生效时间 指定时间生效时必 传 格式为：YYYYMMDDHH24MISS
            DocumentUtil.addSubElementToEle(recprodlist, "STARTDATE", "");
            
            // 失效时间 指定时间失效时必 传 格式为：YYYYMMDDHH24MISS
            DocumentUtil.addSubElementToEle(recprodlist, "ENDDATE", "");
            
            // 产品包编码
            DocumentUtil.addSubElementToEle(recprodlist, "PACKAGEID", "");
            
            // 对象类型
            DocumentUtil.addSubElementToEle(recprodlist, "OBJTYPE", "NCODE");
            
            // 优惠编码
            DocumentUtil.addSubElementToEle(recprodlist, "PRIVID", "");
            
            // 模板编码
            DocumentUtil.addSubElementToEle(recprodlist, "TEMPLATEID", "");
          
            return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_PRODUCTREC, msgHeader, doc);
            
        }
        catch (Exception e)
        {
            logger.error("产品受理失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 产品受理通用接口
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap recCommonServ(MsgHeaderPO msgHeader, String nCode, String operType, String effectType, String param)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // NCODE
            DocumentUtil.addSubElementToEle(eBody, "ncode", nCode);
            
            // 操作类型
            DocumentUtil.addSubElementToEle(eBody, "stype", operType);
            
            // 生效方式
            DocumentUtil.addSubElementToEle(eBody, "effect_type", effectType);
            
            DocumentUtil.addSubElementToEle(eBody, "param", param);
            
            return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_PRODUCTREC, msgHeader, doc);
            
        }
        catch (Exception e)
        {
            logger.error("产品受理失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }    
    
    /**
     * 查询同组业务用户订购信息
     * @param msgHeader
     * @param nCode
     * @return
     * @remark create by lWX431760 2017-09-28 OR_huawei_201706_781_【山东移动接口迁移专题】-自助终端新业务办理(同组业务)
     */
    public ReturnWrap recCommonProductQry (MsgHeaderPO msgHeader, String nCode)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // NCODE
            DocumentUtil.addSubElementToEle(eBody, "ncode", nCode);                        
            
            return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_PRODUCTQRY, msgHeader, doc);
            
        }
        catch (Exception e)
        {
            logger.error("查询同组业务用户订购信息失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 停开机业务处理
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap stopOpenSubs(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String stoptype = (String)map.get("stoptype");
            String reason = (String)map.get("reason");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 操作类型
            Element eReq_dealtype = eBody.addElement("stoptype");
            eReq_dealtype.addText(stoptype);
            
            String reasonElement = "reason";
            if(CommonUtil.isInvokeOpenEbus("cli_busi_stopopensubs"))
            {
                reasonElement="ChangeReason";
                // 是否进行身份验证
                Element eReq_Pwdtype = eBody.addElement("needPwd");
                eReq_Pwdtype.addText("0");
            }   
            // 原因
            Element eReq_calltype = eBody.addElement(reasonElement);
            eReq_calltype.addText(reason);
            
            Document docXML = intMsgUtil.createMsg(doc,"cli_busi_stopopensubs",menuid,touchoid,"1",telnumber,operid,atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("停开机失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 密码修改
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap recChangePassword(Map map)
    {
        try
        {            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String oldPasswd = (String)map.get("oldPasswd");
            String newPasswd = (String)map.get("newPasswd");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            // 原密码
            Element eReq_oldPasswd = eBody.addElement("old_password");
            eReq_oldPasswd.addText(oldPasswd);
            
            // 新密码
            Element eReq_new_password = eBody.addElement("new_password");
            eReq_new_password.addText(newPasswd);
            
            // 子命令字
            Element eReq_subcmdid = eBody.addElement("subcmdid");
            eReq_subcmdid.addText("1");
            
            // modify by lKF60882 OR_huawei_201703_629  【山东移动接口迁移专题】-自助终端已有接口1 2017-4-13 begin
            String businessId = BusinessIdConstants.CLI_BUSI_CHGPWD;
            
            // 走能开的话,调已有的密码修改接口
            if(CommonUtil.isInvokeOpenEbus(businessId))
            {
                businessId = BusinessIdConstants.CLI_BUSI_PWDRESET_MOD;
                
                // 调能开使用明文
                DESedeEncrypt des = new DESedeEncrypt();
                eReq_oldPasswd.setName("OLDPWD");
                eReq_oldPasswd.setText(des.decrypt(oldPasswd));
                
                eReq_new_password.setName("NEWPWD");
                eReq_new_password.setText(des.decrypt(newPasswd));
            }
            
            Document docXML = intMsgUtil.createMsg(doc,businessId,menuid,touchoid,"1",telnumber,operid,atsvNum);
            // modify by lKF60882 OR_huawei_201703_629  【山东移动接口迁移专题】-自助终端已有接口1 2017-4-13 end
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("服务密码修改失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 呼叫转移受理
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap commitCallTransferNo(Map map)
    {
        String telnum = (String)map.get("servNumber");
        String dealtype = (String)map.get("dealType");
        String calltype = (String)map.get("callType");
        String callednum = (String)map.get("transferNumber");
        String menuid = (String)map.get("menuID");
        String unicontact = (String)map.get("contactID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 办理类型
            Element eReq_dealtype = eBody.addElement("dealtype");
            eReq_dealtype.addText(dealtype);
            
            // 呼转类型
            Element eReq_calltype = eBody.addElement("calltype");
            eReq_calltype.addText(calltype);
            
            //modify begin lWX431760 2017-05-11 OR_huawei_201704_404_【山东移动接口迁移专题】-自助终端业务办理2
            // 被呼转号码
            Element eReq_callednum = null;
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CALLTRANSFER))
            {             
                eReq_callednum = eBody.addElement("callednumm");                
            }
            else
            {
                eReq_callednum = eBody.addElement("callednum");                
            }
            
            if (callednum == null)
            {
                callednum = "";
            }
            eReq_callednum.addText(callednum);
            //modify end lWX431760 2017-05-11 OR_huawei_201704_404_【山东移动接口迁移专题】-自助终端业务办理2
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_BUSI_CALLTRANSFER, menuid, unicontact, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("呼叫转移受理失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 业务统一查询接口
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryService(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String sn = (String)map.get("sn");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 序号
            Element eReq_sn = eBody.addElement("sn");
            eReq_sn.addText(sn);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_spinfo", menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("业务统一查询接口失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 业务统一退订接口
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap cancelService(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String operType = (String)map.get("operType");
            String cancelType = (String)map.get("cancelType");
            String dealType = (String)map.get("dealType");
            String domain = (String)map.get("domain");
            String spid = (String)map.get("spId");
            String bizid = (String)map.get("spBizCode");
            String biztype = (String)map.get("bizType");
            String effectType = (String)map.get("effectType");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 操作类型
            Element eReq_operType = eBody.addElement("oper_type");
            eReq_operType.addText(operType);
            
            // 退订类型
            Element eReq_cancelType = eBody.addElement("cancel_type");
            eReq_cancelType.addText(cancelType);
            
            // 对象类型
            Element eReq_dealType = eBody.addElement("deal_type");
            eReq_dealType.addText(dealType);
            
            // 平台
            Element eReq_domain = eBody.addElement("domain");
            eReq_domain.addText(domain);
            
            // 对象编码
            Element eReq_spid = eBody.addElement("spid");
            eReq_spid.addText(spid);
            
            // 业务编码
            Element eReq_bizid = eBody.addElement("bizid");
            eReq_bizid.addText(bizid);
            
            // 业务类型
            Element eReq_biztype = eBody.addElement("biztype");
            eReq_biztype.addText(biztype);
            
            // 生效方式
            Element eReq_effecttype = eBody.addElement("effect_type");
            eReq_effecttype.addText(effectType);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_cancelsp", menuid, touchoid, "1", telnumber, operid, atsvNum);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("业务统一退订接口失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     *  手机支付主账户信息查询
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryCmpayAccount(Map map)
    {
        try{
            String secure_pwd = (String)map.get("securePwd");
            String telnumber = (String)map.get("servNumber");
            String orgID = (String)map.get("orgID");
            String action_time = (String)map.get("actionTime");
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            // 业务操作标识，固定为 MPAY_ACC_QRY
            Element eOper_flag = eBody.addElement("oper_flag");
            eOper_flag.addText("MPAY_ACC_QRY");
            
            // 安全密码
            Element eSecure_pwd = eBody.addElement("secure_pwd");
            eSecure_pwd.addText(secure_pwd);
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 营业厅编号
            Element eOrgid = eBody.addElement("orgid");
            eOrgid.addText(orgID);
            
            // 操作员ID
            Element eOperid = eBody.addElement("operid");
            eOperid.addText(operID);
            
            // 操作请求时间
            Element eAction_time = eBody.addElement("action_time");
            eAction_time.addText(action_time);
            
            Document docXML = intMsgUtil.createMsg(doc,"cli_qry_mpayacc",menuid,unicontact,"1",telnumber,operID,termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("手机支付主账户信息查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     *  手机支付主账户充值
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap recMainFee(Map map)
    {
        try{
            String secure_pwd = (String)map.get("securePwd");
            String telnumber = (String)map.get("servNumber");
            String orgID = (String)map.get("orgID");
            String action_time = (String)map.get("actionTime");
            String payed = (String)map.get("money");
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            // 业务操作标识，固定为 MPAY_CHARGE
            Element eOper_flag = eBody.addElement("oper_flag");
            eOper_flag.addText("MPAY_CHARGE");
            
            // 安全密码
            Element eSecure_pwd = eBody.addElement("secure_pwd");
            eSecure_pwd.addText(secure_pwd);
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 营业厅编号
            Element eOrgid = eBody.addElement("orgid");
            eOrgid.addText(orgID);
            
            // 操作员ID
            Element eOperid = eBody.addElement("operid");
            eOperid.addText(operID);
            
            // 操作请求时间
            Element eAction_time = eBody.addElement("action_time");
            eAction_time.addText(action_time);
            
            // 充值金额
            Element ePayed = eBody.addElement("payed");
            ePayed.addText(payed);
            
            Document docXML = intMsgUtil.createMsg(doc,"cli_busi_mpaycharge",menuid,unicontact,"1",telnumber,operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("主账户充值失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }

    /**
     * 密码重置校验身份证号
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap checkIDCard(Map map)
    {
        try
        {
            String telnum = (String)map.get("telnum");
            String certid = (String)map.get("IDCard");
            String operid = (String)map.get("operid");
            String termid = (String)map.get("termid");
            String menuid = (String)map.get("menuid");
            String touchoid = (String)map.get("touchoid");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 身份证号
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(certid);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_BUSI_CIDCHECK, menuid, touchoid, "1", telnum, operid, termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("身份证认证失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 密码重置
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap resetPassword(Map map)
    {
        try
        {
            String telnum = (String)map.get("telnumber");
            String subcmdid = (String)map.get("subcmdid");
            String oldpwd = (String)map.get("oldpwd");
            String menuid = (String)map.get("menuID");
            String touchoid = (String)map.get("touchOID");
            String newpwd = (String)map.get("newpwd");
            String operid = (String)map.get("operID");
            String termid = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 旧密码
            Element eReq_oldpwd = eBody.addElement("oldpwd");
            eReq_oldpwd.addText(oldpwd);
            
            // 新密码
            Element eReq_newpwd = eBody.addElement("newpwd");
            eReq_newpwd.addText(newpwd);
            
            // 0：密码校验 1：密码修改 2：密码重置，不校验oldpwd
            Element eReq_subcmdid = eBody.addElement("subcmdid");
            eReq_subcmdid.addText(subcmdid);
            
            // modify by lKF60882 OR_huawei_201703_629  【山东移动接口迁移专题】-自助终端已有接口1 2017-4-13 begin
            String businessId = BusinessIdConstants.CLI_BUSI_PWDRESET;
            
            // 调能开的话，区分功能
            if(CommonUtil.isInvokeOpenEbus(businessId))
            {
                // 密码校验
                if("0".equals(subcmdid))
                {
                    businessId = BusinessIdConstants.CLI_BUSI_PWDRESET_CHK;
                    eReq_oldpwd.setName("PASSWORD");
                }
                // 密码修改
                else if("1".equals(subcmdid))
                {
                    businessId = BusinessIdConstants.CLI_BUSI_PWDRESET_MOD;
                }
                
                // 调能开使用明文
                DESedeEncrypt des = new DESedeEncrypt();
                eReq_oldpwd.setText(des.decrypt(oldpwd));
                eReq_newpwd.setText(des.decrypt(newpwd));
            }
            
            Document docXML = intMsgUtil.createMsg(doc, businessId, menuid, touchoid, "1", telnum, operid, termid);
            // modify by lKF60882 OR_huawei_201703_629  【山东移动接口迁移专题】-自助终端已有接口1 2017-4-13 end
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("密码重置失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询需要预约号码
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryChooseTel(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");// 操作员
            String atsvNum = (String)map.get("atsvNum");// 终端机ID
            String menuid = (String)map.get("curMenuId");// 菜单ID
            
            String county_id = (String)map.get("county_id");// 地区
            String sele_rule = (String)map.get("sele_rule");// sele_rule 查询类型 2：按前缀查询 3：按后缀查询
            String tel_prefix = (String)map.get("tel_prefix");// tel_prefix 号码前缀
            String tel_suffix = (String)map.get("tel_suffix");// tel_suffix 号码后缀
            String region = (String) map.get("region");//地区            
            
            //modify begin fwx439896 2017-5-16 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理
            String businessId=BusinessIdConstants.CLI_QRY_NUMBYNET;
			//走能开 组装报文
            if (CommonUtil.isInvokeOpenEbus(businessId)) 
			{
        		// 地区，如SD.LA
				DocumentUtil.addSubElementToEle(eBody, "COUNTYID", county_id);

				// 查询类型 2：按前缀查询 3：按后缀查询
				DocumentUtil.addSubElementToEle(eBody, "SELERULE", sele_rule);

				// 号码前缀 sele_rule = 2时，如果没有限制，为_______；有限制，但不足7位，后面补_ sele_rule
				// = 3时，为“”
				DocumentUtil.addSubElementToEle(eBody, "TELPREFIX", tel_prefix==null?" " :tel_prefix);

				// 号码后缀 sele_rule = 2时，为“” sele_rule = 3时，不足4位，后面补_
				DocumentUtil.addSubElementToEle(eBody, "TELSUFFIX", tel_suffix);

				// 该值为1的时候，表示通过selcountryid传入单位ID(山东用)
				DocumentUtil
						.addSubElementToEle(eBody, "ISSELORGID", "");

				// 商城单位，SD.LA.ES(山东用) 
				DocumentUtil.addSubElementToEle(eBody, "SELCOUNTRYID",
						"");
				
				
				//原空中充值代理商选号，固定值COMMAGT_SELTEL 
				DocumentUtil.addSubElementToEle(eBody, "OPTYPE",
						"");
				// 自助终端一次选号的数量，固定值500
				DocumentUtil.addSubElementToEle(eBody, "TELCOUNT",
						"500");
				// 自助终端选号的用途，固定值SimAny
				DocumentUtil.addSubElementToEle(eBody, "TELBRANDID",
						"SimAny");

			} else
			{
            // 封装包体入参
            // 地区
            Element eReq_county_id = eBody.addElement("county_id");
            eReq_county_id.addText(county_id);
            
            // 号码前缀
            Element eReq_sele_rule = eBody.addElement("sele_rule");
            eReq_sele_rule.addText(sele_rule);
    
            // 号码后缀
            Element eReq_tel_suffix = eBody.addElement("tel_suffix");
            eReq_tel_suffix.addText(tel_suffix);
            
            // 查询类型
            Element eReq_tel_prefix = eBody.addElement("tel_prefix");
            eReq_tel_prefix.addText(tel_prefix);
		 	}
            //modify end fwx439896 2017-5-16 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理

            Document docXML = intMsgUtil.createMsg(doc, businessId, menuid, "", "0", region, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("选号失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 预约号码
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chooseTel(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String atsvNum = (String)map.get("atsvNum");// 终端机ID
            String menuid = (String)map.get("curMenuId");// 菜单ID
            
            String telnum = (String)map.get("telnum");// 被预定号码
            String region = (String)map.get("region");// 地市（查询时返回的信息）
            String org_id = (String)map.get("org_id");// 单位（查询时返回的信息）
            String certtype = (String)map.get("certtype");// 默认：IdCard
            String name = (String)map.get("name");// 用户姓名
            String certid = (String)map.get("certid");// 身份证号
            String contacttel = (String)map.get("contacttel");// 联系号码
            String validday = (String)map.get("validday");// 预约时长
            String operID = (String) map.get("curOper");
            
            // 封装包体入参
            // 被预定号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            //地市（查询时返回的信息）
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            
            //单位（查询时返回的信息）
            Element eReq_org_id = eBody.addElement("org_id");
            eReq_org_id.addText(org_id);
            
            //默认：IdCard
            Element eReq_certtype = eBody.addElement("certtype");
            eReq_certtype.addText(certtype);
            
            //身份证号
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(certid);
            
            //用户姓名
            Element eReq_name = eBody.addElement("name");
            eReq_name.addText(name);
            
            //联系号码，可为“”
            Element eReq_contacttel = eBody.addElement("contacttel");
            eReq_contacttel.addText(contacttel);
            
            //预约时长
            Element eReq_validday = eBody.addElement("validday");
            eReq_validday.addText(validday);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_occupytel", menuid, "", "1", telnum, operID, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("占号失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 充值卡充值
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap cardPayCommit(Map map)
    {
        try
        {
            
            String menuid = (String)map.get("menuid");// 当前菜单ID
            String touchoid = (String)map.get("touchoid");// 客户接触id
            String telnum = (String)map.get("telnum");// 客户手机号
            String operid = (String)map.get("operid");// 操作员id          
            String termid = (String)map.get("termid");// 终端机id
            String cardpwd = (String)map.get("cardpwd"); // 客户充值卡密码

            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            //modify begin by cwx456134 2017-05-10 OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CARDPAY))
            {
                // 充值卡密码
                Element eReq_cardPwd = eBody.addElement("CARDPWD");
                eReq_cardPwd.addText(cardpwd);
            }
            else
            {
                // 充值卡密码
                Element eReq_cardPwd = eBody.addElement("card_pwd");
                eReq_cardPwd.addText(cardpwd); 
            }      
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_BUSI_CARDPAY, menuid, touchoid, "1", telnum, operid, termid);
            //modify end by cwx456134 2017-05-10 OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {    
            logger.error("充值卡充值失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询用户是否已开通手机邮箱
     * @param map
     * @return
     */
    public ReturnWrap qrymailBox(Map map)
    {
        try
        {
            String telnum = (String)map.get("servNumber");
            String email = (String)map.get("email");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 手机邮箱地址
            Element eReq_email = eBody.addElement("email");
            eReq_email.addText(email);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_MAILBOX, menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询用户是否已开通手机邮箱失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 定制139免费邮箱
     * @param map
     * @return
     */
    public ReturnWrap add139Mail(Map map)
    {
        try
        {
            String telnum = (String)map.get("servNumber");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String email=(String)map.get("email");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 139邮箱
            Element eReq_email = eBody.addElement("email");
            eReq_email.addText(email);

            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_add139mail", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("开通139邮箱失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }

    /**
     * 订购SP业务
     * 
     * @param map 入参
     * @return 订购结果
     * @see 
     * @remark create g00140516 2011/11/05 R003C11L11n01 BUG_HUB_201111_24
     */
    public ReturnWrap orderSPService(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String) map.get("curOper");
            String atsvNum = (String) map.get("atsvNum");
            String telnumber = (String) map.get("telnumber");
            String touchoid = (String) map.get("touchoid");
            String menuid = (String) map.get("curMenuId");
            String operType = (String) map.get("operType");
            String cancelFlag = (String) map.get("cancelFlag");
            String domain = (String) map.get("domain");
            String spid = (String) map.get("spId");
            String bizid = (String) map.get("spBizCode");
            String biztype = (String)map.get("bizType");
            String effectType = (String)map.get("effectType");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 操作类型
            Element eReq_operType = eBody.addElement("oper_type");
            eReq_operType.addText(operType);
            
            // 平台
            Element eReq_domain = eBody.addElement("domain");
            eReq_domain.addText(domain);
            
            // 对象编码
            Element eReq_spid = eBody.addElement("spid");
            eReq_spid.addText(spid);
            
            // 业务编码
            Element eReq_bizid = eBody.addElement("bizid");
            eReq_bizid.addText(bizid);
            
            // 业务类型
            Element eReq_biztype = eBody.addElement("biztype");
            eReq_biztype.addText(biztype);
            
            // 生效方式
            Element eReq_effecttype = eBody.addElement("effect_type");
            eReq_effecttype.addText(effectType);
            
            // 退订类型
            Element eReq_cancelFlag = eBody.addElement("cancel_flag");
            eReq_cancelFlag.addText(cancelFlag);
            
            // 处理标志
            Element eReq_Flag = eBody.addElement("flag");
            eReq_Flag.addText("1");
            
            // 检查类型
            Element eReq_Chktype = eBody.addElement("chktype");
            eReq_Chktype.addText("");
            
            // 操作来源
            Element eReq_Source = eBody.addElement("source");
            eReq_Source.addText("");
                        
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_chgsubsmonserv", menuid, touchoid, "1", telnumber, operid, atsvNum);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("SP业务订购接口失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 通过socket接口查询详单记录
     * 
     * @param map 入参
     * @return
     * @see
     * @remark create g00140516 2011/12/09 R003C11L12n01 详单查询实现socket协议
     */
    public ReturnWrap queryCDRListBySocket(Map map)
    {
        try
        {
            String telnum = (String)map.get("servNumber");
            String month = (String)map.get("month");
            String cdrType = (String)map.get("CDRType");
            String fee_type = (String)map.get("fee_type");
            String menuid = (String)map.get("menuID");
            String operID = (String)map.get("operID");
            String startTime = (String)map.get("startTime");
            String endTime = (String)map.get("endTime");
            
            // 包体
            StringBuffer buffer = new StringBuffer(1024);
            buffer.append(telnum)
                    .append("~")
                    .append(month)
                    .append("~1~")
                    .append(cdrType)
                    .append("~")
                    .append(fee_type)
                    .append("~")
                    .append(Constants.CDR_FACTORY_INFO)
                    .append("~")
                    .append(startTime)
                    .append("~")
                    .append(endTime);
            
            return socketUtil.invoke(menuid, BusiCodeConstants.BUSICODE_CDRQRY, operID, buffer.toString());
        }
        catch (Exception e)
        {
            logger.error("详单查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }

    /** 
     * NG3.5帐详单改造之详单查询
     * 
     * @param msgHeader 报文头信息
     * @param month 查询月份
     * @param cdrType 详单类型
     * @param feeType 费用类型
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
     */
    public ReturnWrap queryDetailedRecords2012(MsgHeaderPO msgHeader, String month,
        String cdrType, String feeType)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // 用户密码
            DocumentUtil.addSubElementToEle(eBody, "password", "");
            
            // 账期
            DocumentUtil.addSubElementToEle(eBody, "billcycle", month);
            
            // 开始时间
            DocumentUtil.addSubElementToEle(eBody, "startdate", "");
            
            // 结束时间
            DocumentUtil.addSubElementToEle(eBody, "enddate", "");
            
            // 详单类型
            DocumentUtil.addSubElementToEle(eBody, "flag", cdrType);
            
            // 查询类型
            DocumentUtil.addSubElementToEle(eBody, "chkey", "");
            
            // factory
            DocumentUtil.addSubElementToEle(eBody, "selecttype", feeType);
            
            // add by xkf57421 for adding 接入方式 begin
            DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
            // add by xkf57421 for adding 接入方式 end
            
            // 调用后台接口
            return intMsgUtil.invoke("cli_qry_cdr2012", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("新详单查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * NG3.5帐详单改造之查询客户信息
     * 
     * @param map
     * @return
     * @throws Exception
     * @remark create g00140516 2012/02/13 R003C12L02n01 OR_huawei_201112_953
     */
    public ReturnWrap queryCustomerInfo(Map<String, String>  map)
    {
        try
        {
            String telnum = (String)map.get("servNumber");
            String month = (String)map.get("month");
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String verifyCode = (String) map.get("verifyCode");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 月份
            Element eReq_cycle = eBody.addElement("cycle");
            eReq_cycle.addText(month);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_CUSTINFO, menuid, unicontact, "1", telnum, operID, termID, verifyCode);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("客户信息查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * NG3.5帐详单改造之详单查询
     * 
     * @param map
     * @return
     * @throws Exception
     * @remark create g00140516 2012/02/13 R003C12L02n01 OR_huawei_201112_953
     */
    public ReturnWrap queryDetailedRecordsSD2012(Map<String, String>  map)
    {
        try
        {
            String telnum = (String)map.get("servNumber");
            String cdrType = (String)map.get("CDRType");           
            String startDate = (String)map.get("startDate");
            String endDate = (String) map.get("endDate");
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String verifyCode = (String) map.get("verifyCode");
            String iscycle = (String) map.get("iscycle");
            String cycle = (String) map.get("cycle");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 查询标识
            Element eReq_iscycle = eBody.addElement("iscycle");
            eReq_iscycle.addText(iscycle);
            
            // 手机号码
            Element eReq_balcycle = eBody.addElement("balcycle");
            eReq_balcycle.addText(cycle);
            
            // 账期开始时间
            Element eReq_startcycle = eBody.addElement("startcycle");
            eReq_startcycle.addText(startDate);            
            
            // 账期结束时间
            Element eReq_endcycle = eBody.addElement("endcycle");
            eReq_endcycle.addText(endDate);
            
            // 详单类型
            Element eReq_cdr_type = eBody.addElement("cdr_type");
            eReq_cdr_type.addText(cdrType);
                        
            // 厂家编码的密文标识
            Element eReq_factory = eBody.addElement("factory");
            eReq_factory.addText("9A3A9B26E157B508228301EF1F7BF352");
            
            // modify by lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1 begin
            // 若调用能开，则入参key值需要转换
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CDR2012_SD))
            {
                eReq_startcycle.setName("STARTTIME");
                eReq_endcycle.setName("ENDTIME");
                eReq_cdr_type.setName("CDRTYPE");
            }
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_CDR2012_SD, menuid, unicontact, "1", telnum, operID, termID, verifyCode);
            ReturnWrap rw = intMsgUtil.invoke(docXML);
            
            // 若调用能开，则出参需进行转换
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CDR2012_SD) && null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                rw= genCdrResponse(rw, cdrType);
            }
            // modify by lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1 end
            
            return rw;
        }
        catch (Exception e)
        {
            logger.error("新详单查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 根据不同的详单类型，进行返回信息的转换
     * 
     * @param rw
     * @param cdrType 详单类型
     * @return
     * @remark lKF60882 2017-4-15 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造
     */
    private ReturnWrap genCdrResponse(ReturnWrap rw, String cdrType)
    {
        Object obj = rw.getReturnObject();
        
        CTagSet tagSet = null;
        CRSet set = null;
        if("1".equals(cdrType))
        {
            tagSet = new CTagSet();
            set = (CRSet)obj;
        }
        else
        {
            Vector vec = (Vector)obj;
            
            // 出参统一转小写
            tagSet = CommonUtil.lowerTagKey((CTagSet)vec.get(0));
            set = (CRSet)vec.get(1);
        }
        
        // 获取billinfo列表，转为字符串拼接的形式，便于统一处理
        rw = genBillInfo(rw, set, tagSet);
        
        return rw;
    }

    /** 
     * 将详单信息crset转为tagset中的billinfo
     * @param rw
     * @param obj
     * @remark lKF60882 2017-4-15 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造
     */
    private ReturnWrap genBillInfo(ReturnWrap rw, CRSet set, CTagSet tagSet)
    {
        StringBuffer billinfo = new StringBuffer();
        
        // 遍历
        for(int i = 0, row = set.GetRowCount(); i < row; i++)
        {
            // 按顺序拼接，各字段用@_@隔开
            for(int j = 0, col = set.GetColumnCount(); j < col; j++)
            {
                billinfo.append(set.GetValue(i, j)).append("@_@");
            }
            
            // 多条记录用|隔开
            billinfo.append("|");
        }
        
        // 将组装后的tagset放入rw
        tagSet.put("billinfo", billinfo.toString());
        
        rw.setReturnObject(tagSet);
        
        return rw;
    }
    
    
    /**
     * 验证是否为初始密码
     * @param map
     * @return
     * @throws Exception
     * @remark create YKF38827 2012/02/24 R003C12L02n01  OR_NX_201112_87
     */
    public ReturnWrap valiIsfirstpwd(Map<String, String>  map){
        
        try
        {
            String telnum = (String)map.get("telnum");
            String menuid = (String)map.get("menuID");
            String touchoid = (String)map.get("touchOID");
            String operid = (String)map.get("operID");
            String termid = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
           
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_isfirstpwd", menuid, touchoid, "1", telnum, operid, termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询是否为初始密码失败：", e);            
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 宁夏新版详单查询
     * 
     * @param map
     * @return
     * @throws Exception
     * @remark create g00140516 2012/03/14 R003C12L03n01 OR_NX_201203_128
     */
    public ReturnWrap queryDetailedRecordsNX2012(Map<String, String>  map)
    {
        try
        {
        	// modify begin g00140516 2013/02/28 R003C13L02n01 BUG_NX_201302_123 根据账期查询详单时增加两个入参：ISCYCLE和BILLCYCLE
        	String telnum = (String)map.get("servNumber");
            String cdrType = (String)map.get("CDRType");           
            String startDate = (String)map.get("startDate");
            String endDate = (String) map.get("endDate");
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String verifyCode = (String) map.get("verifyCode");
            String cycle = (String) map.get("cycle");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 账期开始时间。自助终端只有按账期查询，所以传""
            Element eReq_startcycle = eBody.addElement("startcycle");
            eReq_startcycle.addText("");            
            
            // 账期结束时间。自助终端只有按账期查询，所以传""
            Element eReq_endcycle = eBody.addElement("endcycle");
            eReq_endcycle.addText("");
            
            // 详单类型
            Element eReq_cdr_type = eBody.addElement("cdr_type");
            eReq_cdr_type.addText(cdrType);
                        
            // 厂家编码的密文标识
            Element eReq_factory = eBody.addElement("factory");
            eReq_factory.addText("9A3A9B26E157B508228301EF1F7BF352");
            
            // 自助终端只有按账期查询，所以传1
            Element eIsCycle = eBody.addElement("iscycle");
            eIsCycle.addText("1");
            
            Element eBillCycle = eBody.addElement("billcycle");
            eBillCycle.addText(cycle);
            // modify end g00140516 2013/02/28 R003C13L02n01 BUG_NX_201302_123 根据账期查询详单时增加两个入参：ISCYCLE和BILLCYCLE
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_cdr2012_nx", menuid, unicontact, "1", telnum, operID, termID, verifyCode);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("新详单查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 山东积分明细查询
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryScoreDetailHis(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String startDate = (String)map.get("startDate");
            String endDate = (String)map.get("endDate");
            String regions = (String)map.get("region");
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 开始时间
            Element startD = eBody.addElement("startdate");
            startD.addText(startDate + "000000");
            // 结束时间
            Element endD = eBody.addElement("enddate");
            endD.addText(endDate + "235959");
            
            // 固定0，用户积分
            Element qrytype = eBody.addElement("qrytype");
            qrytype.addText("0");
              
            //modify begin lwx439898 2017-05-22 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_SCROEDETAIL))
            {
                Element regionsd = eBody.addElement("region");
                regionsd.addText(regions);
            }
            else
            {
                // 地市编号
                Element regionsd = eBody.addElement("subregion");
                regionsd.addText(regions);               
            }
            //modify end lwx439898 2017-05-22 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_scroedetail", menuid, touchoid, "1", telnumber, operid, atsvNum);
            // docXML = new IntMsgUtil().createMsg(doc, "cli_qry_scroedetail", menuid, touchoid, "1", telnumber, operid,
            // atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("积分明细查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "积分明细查询接口出现异常");
        }
    }
    
    
    /**
     * 山东积分兑换历史查询
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryScoreChangeHis(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String startDate = (String)map.get("startDate");
            String endDate = (String)map.get("endDate");
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 开始时间
            Element startD = eBody.addElement("startdate");
            startD.addText(startDate + " 00:00:00");
            // 结束时间
            Element endD = eBody.addElement("enddate");
            endD.addText(endDate + " 23:59:59");
            
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_SCORECHANGE, menuid, touchoid, "1", telnumber, operid, atsvNum);
//             docXML = new IntMsgUtil().createMsg(doc, "cli_qry_scorechange", menuid, touchoid, "1", telnumber, operid,
//             atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("山东积分兑换历史查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "山东积分兑换历史查询接口出现异常");
        }
    }
    
    
    @Override
    public ReturnWrap queryScorePrizeHis(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String startDate = (String)map.get("startDate");
            String endDate = (String)map.get("endDate");
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // 开始时间
            Element startD = eBody.addElement("startdate");
            startD.addText(startDate);
            // 结束时间
            Element endD = eBody.addElement("enddate");
            endD.addText(endDate);
            
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_scoreprize", menuid, touchoid, "1", telnumber, operid, atsvNum);
//             docXML = new IntMsgUtil().createMsg(doc, "cli_qry_scorechange", menuid, touchoid, "1", telnumber, operid,
//             atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("湖北积分兑换历史查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "湖北积分兑换历史查询接口出现异常");
        }
    }
    
    public ReturnWrap qryRecStatusHub(MsgHeaderPO msgHeader, String nCode, String operType)
    {
        
        try
        {
        	Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ncode列表
            DocumentUtil.addSubElementToEle(eBody, "ncodelist", nCode);
            
            // NCODE
            DocumentUtil.addSubElementToEle(eBody, "ncode", "SUBSNCODEEXSIT");
            
            // 操作类型
            DocumentUtil.addSubElementToEle(eBody, "stype", operType);
            
            return intMsgUtil.invoke("cli_qry_recstatus_hub", msgHeader, doc);
        }
        catch (Exception e)
        {
            logger.error("湖北产品受理状态查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /** 
     * 产品快速发布-用户已订购产品信息查询
     * 
     * @param msgHeader 报文头信息
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: modify zKF69263 2014/09/11 R003C14L09n01 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
     */
    public ReturnWrap qryHasProds(MsgHeaderPO msgHeader)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // 值产品大类编码 
            DocumentUtil.addSubElementToEle(eBody, "prodclass", "");

            // 查询方式  传5：在用和预约的 
            DocumentUtil.addSubElementToEle(eBody, "qrytype", "5");

            // 增值产品编码，如有多个，中间以#分隔。固定传“”
            DocumentUtil.addSubElementToEle(eBody, "prodid", "");

            // 套餐大类，套餐查询时用。传“”
            DocumentUtil.addSubElementToEle(eBody, "pkgtype", "");

            // 是否输出产品包，默认输出。传“”
            DocumentUtil.addSubElementToEle(eBody, "issolution", "");

            // 是否输出集团产品 0：不输出;1：输出;默认为1。固定传0
            DocumentUtil.addSubElementToEle(eBody, "grpsubsoid", "0");

            // 赠送方用户标识。传“”
            DocumentUtil.addSubElementToEle(eBody, "donorsubsid", "");

            // 否查询宽带用户个人增值产品信息。传“”
            DocumentUtil.addSubElementToEle(eBody, "ishavebandprod", "");

            // 客服IVR套餐使用查询用。传“”
            DocumentUtil.addSubElementToEle(eBody, "queryflag", "");

            // 客服IVR套餐使用查询用。传“”
            DocumentUtil.addSubElementToEle(eBody, "pkginfo", "");

            // 客服IVR套餐使用查询用。传“”
            DocumentUtil.addSubElementToEle(eBody, "exceptclass", "");
            
            // 客服IVR套餐使用查询用。传“”
            DocumentUtil.addSubElementToEle(eBody, "prodidlist", "");
            
            // 调用后台接口
            return intMsgUtil.invoke(msgHeader.getProcessCode(), msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("查询用户已订购产品失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /** 
     * 产品快速发布-查询产品附加属性
     * 
     * @param msgHeader 报文头信息
     * @param qryType 查询类型 0：NCODE 1：产品包下产品
     * @param nCode nCode
     * @param operType PCOpRec:开通  PCOpMod:修改 PCOpDel:关闭 
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: modify zKF69263 2014/09/11 R003C14L09n01 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
     */
    public ReturnWrap qryAddAttr(MsgHeaderPO msgHeader, String qryType, String nCode, String operType)
    { 
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // 查询类型 0：NCODE 1：产品包下产品
            DocumentUtil.addSubElementToEle(eBody, "qrytype", qryType);
            
            // NOCODE或者产品编码
            DocumentUtil.addSubElementToEle(eBody, "ncode", nCode);
            
            // 是否所有受理类型标志。传“”
            DocumentUtil.addSubElementToEle(eBody, "isallrectype", "");
            
            // 受理类型,传ChangeProduct
            DocumentUtil.addSubElementToEle(eBody, "rectype", "ChangeProduct");
            
            // PCOpRec:开通
            DocumentUtil.addSubElementToEle(eBody, "opttype", operType);
            
            // 调用后台接口
            return intMsgUtil.invoke(msgHeader.getProcessCode(), msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("查询产品附加属性失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /** 
     * 产品快速发布-产品受理
     * 
     * @param msgHeader MsgHeaderPO
     * @param multiProdCommitPO MultiProdCommitPO
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: modify zKF69263 2014/09/11 R003C14L09n01 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
     */
    public ReturnWrap prodRec(MsgHeaderPO msgHeader, MultiProdCommitPO multiProdCommitPO)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", multiProdCommitPO.getTelnum());
            
            // 标识该接口是否只进行校验，不进行办理 0：不进行校验，而是进行办理 1：只进行校验，不进行办理 可以传“”，默认为不进行校验，而是办理
            DocumentUtil.addSubElementToEle(eBody, "ischeck", multiProdCommitPO.getIscheck());
            
            // 赠送号码，用于赠送业务。传“”
            DocumentUtil.addSubElementToEle(eBody, "doneenum", multiProdCommitPO.getDoneenum());

            // 算费标识，预留字段，暂不使用。传“”
            DocumentUtil.addSubElementToEle(eBody, "iscalcfee", multiProdCommitPO.getIscalcfee());

            // 短信发送标识 0：不发送 1：发送 默认为1。外围固定传1
            DocumentUtil.addSubElementToEle(eBody, "issendsms", multiProdCommitPO.getIssendsms());
            
            // 传“”
            DocumentUtil.addSubElementToEle(eBody, "opersource", multiProdCommitPO.getOpersource());
            
            // 受理产品CRSET列表
            Element eReq_multiprod = eBody.addElement("cli_busi_multiprodrecreq");
            List<ProdCommitPO> prodCommitPOList = multiProdCommitPO.getProdCommitList();
            for(ProdCommitPO prodCommitPO : prodCommitPOList)
            {
                // 对产品包下的子产品或者模板下的子产品，传产品编码；其它传NCODE
                DocumentUtil.addSubElementToEle(eReq_multiprod, "ncode", prodCommitPO.getNcode());
                
                // 生效方式 2：立即 3：次月 4：次日 5：指定生效时间 默认是立即生效。需外围根据产品的实际情况传入
                DocumentUtil.addSubElementToEle(eReq_multiprod, "effecttype", prodCommitPO.getEffecttype());
                
                // 操作类型 PCOpRec:开通 PCOpMod:修改 PCOpDel:关闭 PCOpPau:暂停 PCOpRes:恢复
                DocumentUtil.addSubElementToEle(eReq_multiprod, "opertype", prodCommitPO.getOpertype());

                // 附加属性，格式： attrid1=attrvalue1=optype1#attrid2=attrvalue2=optype2… 即附加属性编码=附加属性值=附加属性的操作类型，其中操作类型目前没用，直接使用attrid1=attrvalue1= #attrid2=attrvalue2=#…即可
                DocumentUtil.addSubElementToEle(eReq_multiprod, "attrparam", prodCommitPO.getAttrparam());
                
                // 原增值产品编码，目前没用。直接传“”
                DocumentUtil.addSubElementToEle(eReq_multiprod, "oldprodid", prodCommitPO.getOldprodid());
                
                // 服务资源，格式：resid1=restype1=optype1#resid2=restype2=optype2…目前没用。直接传“”
                DocumentUtil.addSubElementToEle(eReq_multiprod, "serviceres", prodCommitPO.getServiceres());
                
                // 接口对应类型，对产品包下子产品或者模板下子产品受理的时候使用。 PCIntRelaNormal：普通业务 PCIntRelaRadio：业务切换
                DocumentUtil.addSubElementToEle(eReq_multiprod, "inftype", prodCommitPO.getInftype());
                
                // 是否按模板处理 1：是 “”：不是 目前没用，传“”即可
                DocumentUtil.addSubElementToEle(eReq_multiprod, "templetflag", prodCommitPO.getTempletflag());
                
                // 具体生效时间，格式：yyyymmddhh24miss 当effecttype=5时，必传，不可为空。 其它情况，传“”即可
                DocumentUtil.addSubElementToEle(eReq_multiprod, "startdate", prodCommitPO.getStartdate());
                
                // 具体失效时间，格式：yyyymmddhh24miss 当effecttype=5时，传用户指定失效时间，若用户未指定，传“”。其它情况，传“”即可
                DocumentUtil.addSubElementToEle(eReq_multiprod, "enddate", prodCommitPO.getEnddate());
                
                // 产品包编码。对产品包下子产品或者模板下子产品受理的时候使用
                DocumentUtil.addSubElementToEle(eReq_multiprod, "pkgid", prodCommitPO.getPkgid());
                
                // 对象类型，包括产品、优惠、服务、SP、主体产品、模板、ncode
                DocumentUtil.addSubElementToEle(eReq_multiprod, "objtype", prodCommitPO.getObjtype());
                
                // 优惠编码。对产品包下子产品或者模板下子产品受理的时候使用
                DocumentUtil.addSubElementToEle(eReq_multiprod, "privid", prodCommitPO.getPrivid());
                
                // 模板编码。对产品包下子产品或者模板下子产品受理的时候使用
                DocumentUtil.addSubElementToEle(eReq_multiprod, "templetid", prodCommitPO.getTempletid());
            }
            
            // 调用后台接口
            return intMsgUtil.invoke(msgHeader.getProcessCode(), msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("查询产品附加属性失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /** 
     * 产品快速发布-查询产品包下子产品
     * 
     * @param msgHeader 报文头信息
     * @param nCode 产品包编码、模板ID
     * @param type 类型：2 产品包 3 模板
     * @param optType 操作类型
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: modify zKF69263 2014/09/11 R003C14L09n01 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
     */
    public ReturnWrap qrySubProds(MsgHeaderPO msgHeader, String nCode, String type, String optType)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // 产品包编码或者模板编码
            DocumentUtil.addSubElementToEle(eBody, "pkgid", "");
            
            // 类型 2：产品包 3：模板
            DocumentUtil.addSubElementToEle(eBody, "type", type);

            // 受理类型
            DocumentUtil.addSubElementToEle(eBody, "rectype", "ChangeProduct");
            
            // 是否所有受理类型标志
            DocumentUtil.addSubElementToEle(eBody, "isallrectype", "");
            
            // 调用后台接口
            return intMsgUtil.invoke(msgHeader.getProcessCode(), msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("查询用户已订购产品失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 根据ncode查询特别提示信息
     * @param paramMap
     * @return
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public ReturnWrap qryNcodeTips(Map<String, String> paramMap)
    {
        return null;
    }
    
    /**
     * 根据对象编码查询特别提示信息
     * @param paramMap
     * @return
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public ReturnWrap qryObjectTips(Map<String, String> paramMap, List<Map<String, String>> prods)
    {
    	return null;
    }
    
    /**
     * 查询需要预约号码（山东）
     * @param paramMap
     * @return
     * @remark create cKF76106 2013/01/23 R003C13L01n01 OR_SD_201301_279
     */
    public ReturnWrap qryChooseTelSD(Map map)
    {
        return null;
    }
    
    /**
     * 话费总额查询
     * @param map
     * @return
     * @remark create g00140516 2013/02/22 R003C13L02n01 OR_NX_201302_600
     */
    public ReturnWrap qryCurrBillFee(Map<String, String> map)
    {
    	try
    	{
    		Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("menuid");
            String telnumber = (String)map.get("telnumber");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_currbillfee", menuid, touchoid, "1", telnumber, operID, termID);
            return intMsgUtil.invoke(docXML);
    	}
    	catch (Exception e)
        {
            logger.error("话费总额查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    	
    }
    
    /**
     * 检查详单打印是否超出次数限制
     * 
     * @param map
     * @return
     * @throws Exception
     * @remark create cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
     */
    public ReturnWrap queryPrintCdr(Map<String, String>  map)
    {
        try
        {
            String telnum = (String)map.get("telnum");
            String cdrType = (String)map.get("CDRType");           
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 详单类型
            Element eReq_cdrtype = eBody.addElement("cdrtype");
            eReq_cdrtype.addText(cdrType);
                        
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_printcdr", menuid, unicontact, "1", telnum, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("新详单查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    
    /**
     * 更新详单打印次数
     * 
     * @param map
     * @return
     * @throws Exception
     * @remark create cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
     */
    public ReturnWrap writePrintCdrLog(Map<String, String>  map)
    {
        try
        {
            String telnum = (String)map.get("telnum");
            String cdrType = (String)map.get("CDRType");           
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String actiontype = "PRINT";
            String objecttype = "PRINTCDR";

            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);

            // 详单类型
            Element eReq_cdrtype = eBody.addElement("cdrtype");
            eReq_cdrtype.addText(cdrType);
            
            // 记录打印日志时传入PRINT，可以配置成固定值
            Element eReq_actiontype = eBody.addElement("actiontype");
            eReq_actiontype.addText(actiontype);
            
            // 记录打印日志时传入PRINTCDR,可以配置成固定值
            Element eReq_objecttype = eBody.addElement("objecttype");
            eReq_objecttype.addText(objecttype);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_writeprintcdrlog", menuid, unicontact, "1", telnum, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("新详单查询失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 向用户发送随机密码短信
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     * @remark create cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
     */
    public ReturnWrap sendSmsHub(Map map)
    {
        return null;
    }
	
    /**
     * 校验手机号是否已实名制登记
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap realNameCheck(Map map)
    {
        try
        {            
            String telnumber = (String)map.get("telnum");
            String password = (String)map.get("password");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 封装包体入参
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_realNameCheck", "10001001", "", "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("校验手机号是否已实名制登记失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 重置密码（新）（宁夏）
     * 
     * @param map
     * @return ReturnWrap
     * @remark create by hWX5316476 2014/2/18 OR_NX_201402_306 宁夏自助终端优化需求_弱密码改造需求
     */
    public ReturnWrap  resetPwdNew(Map map)
    {
    	try 
    	{
    		String telnum = (String)map.get("telnum");
			String callernum = (String)map.get("callernum");
			String flag = (String)map.get("flag");
			String subcmdid = (String)map.get("subcmdid");
			String old_passwd = (String)map.get("old_passwd");
			String new_passwd = (String)map.get("new_passwd");
			String chktype = (String)map.get("chktype");
			String newpwdcount = (String)map.get("newpwdcount");
			String menuid = (String)map.get("menuid");
			String termid = (String)map.get("termid");
			String touchoid = (String)map.get("touchoid");
			String operid = (String)map.get("operid");
			
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");

            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);

            // 是否拨打本机  0本机，1非本机
            Element eReq_flag = eBody.addElement("flag");
            eReq_flag.addText(flag);

			/*接口命令字 
			 * 0 服务密码验证 1 修改服务密码 2 密码重置(按照输入的新密码进行重置) 
			 * 3 密码重置(系统产生随机密码并进行重置) 
			 * 4 生成临时随机密码(一次性有效)，并通过短信方式发送给用户，随机密码不返回给接口 
			 * 5 验证随机密码的正确性(验证密码通过老密码参数传递) 
			 * 7 短信首次设置密码校验，用户首次通过短信设置密码，要求原密码必须为初始密码，否则，认为已经设置过密码
			*/
            Element eReq_subcmdid = eBody.addElement("subcmdid");
            eReq_subcmdid.addText(subcmdid);
            
            // 老密码
            Element eReq_old_passwd = eBody.addElement("old_passwd");
            eReq_old_passwd.addText(old_passwd);
            
            // 新密码
            Element eReq_new_passwd = eBody.addElement("new_passwd");
            eReq_new_passwd.addText(new_passwd);
            
            // 认证方式
            Element eReq_chktype = eBody.addElement("chktype");
            eReq_chktype.addText(chktype);
            
            // 拨打号码(可以不传) 
            Element eReq_callernum = eBody.addElement("callernum");
            eReq_callernum.addText(callernum);
            
            // 新密码位数校验，校验是否符合传入位数，传0或不传则不校验。
            Element eReq_newpwdcount = eBody.addElement("newpwdcount");
            eReq_newpwdcount.addText(newpwdcount);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_pwdresetnew_nx", menuid, touchoid, "1", telnum, operid, termid);
            
            return intMsgUtil.invoke(docXML);
		} 
    	catch (Exception e) 
    	{
    		logger.error("密码重置(新)失败：", e);
            
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
    }
    
    /**
     * <详单邮件下发>
     * <功能详细描述>
     * @param map
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark  create by sWX219697 2014-04-29 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
     */
    public ReturnWrap sendRecordsMail(Map<String,String> map)
    {
    	String telnum = map.get("telnum");
    	String contactID = map.get("contactID");
    	String operID = map.get("operID");
    	String termID = map.get("termID");
    	String menuID = map.get("menuID");
    	String cdr_type = map.get("cdr_type");
    	String iscycle = map.get("iscycle");
    	String balcycle = map.get("balcycle");
    	String startcycle = map.get("startcycle");
    	String endcycle = map.get("endcycle");
    	String accessnum = map.get("accessnum");
    	String staffid = map.get("staffid");
    	String nosms = map.get("nosms");
    	String qryregion = map.get("qryregion");
    	
    	try 
    	{
			Document doc = DocumentHelper.createDocument();
			Element eBody = doc.addElement("message_content");
			
			//客户手机号码
			Element eReq_telnum = eBody.addElement("telnum");
			eReq_telnum.addText(telnum);
			
			//查询地市
			Element eReq_qryregion = eBody.addElement("qryregion");
			eReq_qryregion.addText(qryregion);
			
			//是否按账期查询 0 按起止时间查询，1 按账期查询，默认为0
			Element eReq_iscycle = eBody.addElement("iscycle");
			eReq_iscycle.addText(iscycle);
			
			//查询开始时间
			Element eReq_startcycle = eBody.addElement("startcycle");
			eReq_startcycle.addText(startcycle);
			
			//查询结束时间
			Element eReq_endcycle = eBody.addElement("endcycle");
			eReq_endcycle.addText(endcycle);
			
			//按账期查询
			Element eReq_balcycle = eBody.addElement("balcycle");
			eReq_balcycle.addText(balcycle);

			//模板标识
			Element eReq_cdr_type = eBody.addElement("cdr_type");
			eReq_cdr_type.addText(cdr_type);
			
			//客服接入号码
			Element eReq_accessnum = eBody.addElement("accessnum");
			eReq_accessnum.addText(accessnum);
			
			//工号
			Element eReq_staffid = eBody.addElement("staffid");
			eReq_staffid.addText(staffid);
			
			//是否发送短信提醒，0 发送，1 不发送，默认为0。
			Element eReq_nosms = eBody.addElement("nosms");
			eReq_nosms.addText(nosms);
			
			//从缓存中取出接口业务id
			String buisID = (String) PublicCache.getInstance().getCachedData(Constants.SEND_RECORDS_BUIS_ID);
			
			Document docXML=intMsgUtil.createMsg(doc, buisID, menuID, contactID, "1", telnum, operID, termID);
			
			return intMsgUtil.invoke(docXML);
		} 
    	catch (Exception e) 
    	{
    		logger.error("详单邮件下发接口调用失败：", e);
            
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
    }
    
    /**
     * 山东 判断用户是否开通积分计划
     * @param map
     * @return ReturnWrap
     * @remark create by sWX219697 2014-05-12 OR_SD_201404_777_山东_网厅、自助终端、掌厅__全渠道积分查询及兑换功能
     */
    public ReturnWrap qryIsScoreOpen(Map<String,String> map)
    {
    	
    	//用户手机号码
    	String telnum = map.get("telnum");
    	
    	//操作员编号
    	String operID = map.get("operID");
    	
    	//终端机编号
    	String termID = map.get("termID");
    	
    	//客户接触号
    	String contactID = map.get("contactID");
    	
    	//当前菜单编号
    	String menuID = map.get("curMenuId");
    	
    	//产品id
    	String prodID = map.get("prodID");
    	
    	//查询地区
    	String region = map.get("region");
    	
    	try 
    	{
			Document doc = DocumentHelper.createDocument();
			Element eBody = doc.addElement("message_content");
			
			//手机号码
			Element eReq_telnum = eBody.addElement("telnum");
			eReq_telnum.addText(telnum);
			
			//产品id
			Element eReq_prodid = eBody.addElement("prodid");
			eReq_prodid.addText(prodID);
			
			//查询地区
			Element eReq_region = eBody.addElement("region");
			eReq_region.addText(region);
			
			//是否查询历史
			Element eReq_isqryhis = eBody.addElement("isqryhis");
			eReq_isqryhis.addText("");
			
			//接口id待定
			Document docXML = intMsgUtil.createMsg(doc, "cli_qry_isopenscore", menuID, contactID, "1", telnum, operID, termID);
			
			return intMsgUtil.invoke(docXML);
		} 
    	catch (Exception e) 
    	{
    		logger.error("山东判断用户是否开通积分计划失败：",e);
    		
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
    	
    }
    
    /**
     * 家庭网取消业务接口(山东)
     * 
     * @param map
     * @return 接口处理结果
     * @remark add begin wWX217192 on 20140603 for OR_huawei_201405_875
     */
	public ReturnWrap deleteFamilyMem(Map<String, String> map)
    {
    	try
    	{
    		Document doc = DocumentHelper.createDocument();
    		Element eBody = doc.addElement("message_content");
    		
    		// 手机号码
    		eBody.addElement("servnumber").addText(map.get("servnum"));
    		
    		eBody.addElement("opertype").addText("0");
    		
    		eBody.addElement("issendsms").addText("1");
    		
    		Document docXML = intMsgUtil.createMsg(doc, "cli_delete_familymem_sd", map.get("menuID"),
    				map.get("touchOID"), "1", map.get("servnum"), map.get("operID"), map.get("termID"));
    		
    		return intMsgUtil.invoke(docXML);
    	}
    	catch(Exception e)
    	{
    		logger.error("家庭网取消失败!", e);
    		
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
    	}
    }

	/**
     * 积分发放查询（山东）
     * @param header 报文头信息
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     * @remark create by jWX216858 2014-10-20 R003C10LG1001 OR_SD_201407_498_山东移动促销回馈积分查询方案支撑需求
     */
	@Override
	public ReturnWrap qryPayMentScoreSD(MsgHeaderPO header, String startDate,
			String endDate) 
	{
		Document msgBody = DocumentHelper.createDocument();
		Element ebody = msgBody.addElement("message_content");
		
		// 手机号
		DocumentUtil.addSubElementToEle(ebody, "telNum", header.getTelNum());
		
		// 开始时间
		DocumentUtil.addSubElementToEle(ebody, "startdate", startDate + "000000");
		
		// 结束时间
		DocumentUtil.addSubElementToEle(ebody, "enddate", endDate + "235959");
		
		// 固定传：1，没有积分发放信息接口返回的crset为空的标志
		DocumentUtil.addSubElementToEle(ebody, "NODATANOROW", "1");
		
		// 调用接口查询积分发放信息
		return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_PAYMENTSOCRE, header, msgBody);
	}
	
	/**
     * 积分查询（山东）
     * @param header 报文头信息
     * @return
     * @remark create by jWX216858 2014-10-20 R003C10LG1001 OR_SD_201407_498_山东移动促销回馈积分查询方案支撑需求
     */
	@Override
	public ReturnWrap qryScoreValueSD(MsgHeaderPO header)
	{
		Document msgBody = DocumentHelper.createDocument();
		Element ebody = msgBody.addElement("message_content");
		
		// 手机号
		DocumentUtil.addSubElementToEle(ebody, "telnum", header.getTelNum());
		
		// 调用接口查询积分信息
		return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_SCOREVALUESD, header, msgBody);
	}
	
	/**
     * 删除家庭网成员
     * @param header 请求报文头
     * @param memTelnum 成员手机号
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify by sWX219697 2015-2-4 10:45:04 OR_SD_201412_777 自助终端放开家庭网成员删除的功能
     */
    public ReturnWrap delMemByTelnum(MsgHeaderPO header, String memTelnum)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");

            // 手机号
            DocumentUtil.addSubElementToEle(ebody, "servNumber", header.getTelNum());
            
            // 删除的成员手机号
            DocumentUtil.addSubElementToEle(ebody, "memtelnum", memTelnum);
            
            // 允许副号办理业务1允许,非1或空不允许
            DocumentUtil.addSubElementToEle(ebody, "allowmemdel", "");
            
            // 主体产品编码
            DocumentUtil.addSubElementToEle(ebody, "prodid", "");
            
            // 只能删除自己，非1或空不做限制
            DocumentUtil.addSubElementToEle(ebody, "onlydelitself", "");
            
            // 调用删除家庭网成员接口
            return intMsgUtil.invoke("cli_delfamilymember", header, doc);
        }
        catch (Exception e)
        {
            logger.error("家庭网成员删除异常");
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "家庭网成员删除异常");
        }
    }
    
    /**
	 * 查询可预约号码列表
	 * @param requestMsg
	 * @return
	 * @remark create by wWX217192 2015-03-10 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
	 */
	public String queryNumResp(OMElement requestMsg)
	{
		// 查询号码的请求URL
    	String wsUrl = CommonUtil.getParamValue(Constants.SH_QUERYNUM_WEBSERVICE_WSURL);
		
    	// 调用方法
    	String operation = CommonUtil.getParamValue(Constants.SH_QUERYNUM_OPERATION);
    	
        String responseMsg = "";
        
        // 向webservice服务端发送请求
		try 
		{
			responseMsg = new Axis2Client(wsUrl, operation).invokeWebService(requestMsg);
		}
		catch (AxisFault e)
		{
			logger.error("webservice接口查询可预约号码失败!", e);
		}
		
		return responseMsg;
	}
	
	/**
	 * 暂选号码
	 * @param requestMsg
	 * @return
	 * @remark create by wWX217192 2015-03-10 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
	 */
	public String pickNumResp(OMElement requestMsg)
	{
		// 请求的URL地址
    	String wsUrl = CommonUtil.getParamValue(Constants.SH_PICKNUM_WEBSERVICE_WSURL);
    	
    	String operation = CommonUtil.getParamValue(Constants.SH_PICKNUM_OPERATION);
    	
    	String responseMsg = "";
    	
    	// 向webservice服务端发送请求
    	try
    	{
			responseMsg = new Axis2Client(wsUrl, operation).invokeWebService(requestMsg);
		} 
    	catch (AxisFault e) 
    	{
			logger.error("webservice接口暂选号码失败!", e);
		}
    	
    	return responseMsg;
	}
	
	/**
	 * 预约号码
	 * @param requestMsg
	 * @return
	 * @remark create by wWX217192 2015-03-10 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
	 */
	public String bookNumResp(OMElement requestMsg)
	{
		// 请求的URL地址
		String wsUrl = CommonUtil.getParamValue(Constants.SH_BOOKNUM_WEBSERVICE_WSURL);
		
		// 调用方法
		String operation = CommonUtil.getParamValue(Constants.SH_BOOKNUM_OPERATION);
		
		String responseMsg = "";
		
		// 向webservice服务端发送请求
		try 
		{
			responseMsg = new Axis2Client(wsUrl, operation).invokeWebService(requestMsg);
		} 
		catch (AxisFault e) 
		{
			logger.error("webservice接口预约号码失败!", e);
		}
		
		return responseMsg;
	}
	
	/**
     * 查询客户应缴总金额
     * @param msgHeader
     * @param orgid
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-3-24 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
     */
    public ReturnWrap qryPayAmount(MsgHeaderPO msgHeader, String orgid)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element ebody = msgBody.addElement("message_content");

            // 01:手机号
            DocumentUtil.addSubElementToEle(ebody, "IDType", "01");
            
            // 手机号
            DocumentUtil.addSubElementToEle(ebody, "IDValue", msgHeader.getTelNum());
            
            // 组织机构编码
            DocumentUtil.addSubElementToEle(ebody, "OrgID", orgid);
            
            // BIZID 接口ID(类似opcode)
            DocumentUtil.addSubElementToEle(ebody, "BIZID", "KQFWPayFeeQry");
            
            // 调用接口查询积分信息
            return intMsgUtil.invoke("cli_qry_userPayAmount", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("查询客户应缴总金额异常!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"查询客户应缴总金额异常！");
        }
        
    }
	
    /**
     * 异地缴费
     * <功能详细描述>
     * @param msgHeader
     * @param seq 规则操作流水号
     * @param actualPayAmount 实际缴费金额（厘）
     * @param orgid 组织机构编码
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-3-24 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
     */
    public ReturnWrap nonlocalCharge(MsgHeaderPO msgHeader, String seq, String actualPayAmount, String orgid)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element ebody = msgBody.addElement("message_content");

            // 标识类型 01:手机号
            DocumentUtil.addSubElementToEle(ebody, "IDType", "01");
            
            // 手机号
            DocumentUtil.addSubElementToEle(ebody, "IDValue", msgHeader.getTelNum());
            
            // 实际缴费金额（厘）
            DocumentUtil.addSubElementToEle(ebody, "PayAmount", actualPayAmount);
            
            // 手续费
            DocumentUtil.addSubElementToEle(ebody, "HandCharge", "0");
            
            // 规则操作流水号
            DocumentUtil.addSubElementToEle(ebody, "Seq", seq);
            
            // 组织机构编码
            DocumentUtil.addSubElementToEle(ebody, "OrgID", orgid);
            
            // BIZID 接口ID(类似opcode)
            DocumentUtil.addSubElementToEle(ebody, "BIZID", "KQFWPayFee");
            
            // 调用接口查询积分信息
            return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_NONLOCALCHARGE, msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("异地缴费异常!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"异地缴费异常！");
        }
    }
    
    /**
	 * 山东梦网订购业务
	 * @param header
	 * @param spid
	 * @param bizid
	 * @return
	 * @remark create by wWX217192 2015-04-02 OR_SD_201502_373_山东_关于自助终端承载和娱乐包新业务的支撑需求
	 */
	public ReturnWrap orderSP(MsgHeaderPO header, String spid, String bizid)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");
            //modify begin fwx439896 2017-05-22 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3_需求分析设计说明书
            //走能开  组装报文
            String  businessId=BusinessIdConstants.CLI_SD_CHANGESUBSMONSERV;
        	if(CommonUtil.isInvokeOpenEbus(businessId))
        	{
        		// 手机号码
                DocumentUtil.addSubElementToEle(ebody, "telnum",header.getTelNum());
                
                //操作来源    选填可以为空
                DocumentUtil.addSubElementToEle(ebody, "source", "");
                
                 // 企业编码  业务编码
                DocumentUtil.addSubElementToEle(ebody, "DETAILINFO",spid +","+bizid);
               
                // 操作类型 Order 订购 Cancel 退订 Pause 暂停 Resume 恢复 其他 操作码错误
                DocumentUtil.addSubElementToEle(ebody, "opertype", "Order"  );
                
                //退订标志 1 退订某个梦网企业的所有业务  2 退订所有业务  3 按照业务类型退订  4	根据DOMAIN进行退订     默认传：3
                DocumentUtil.addSubElementToEle(ebody, "cancel_flag", "3");
                
                //平台编码
                DocumentUtil.addSubElementToEle(ebody, "SPDOMAIN", "bsacAtsv");
              
                //	必填	String	标识
                DocumentUtil.addSubElementToEle(ebody, "FLAG", " ");

                //	是否发送srp控制参数0不发送 1 发送
                DocumentUtil.addSubElementToEle(ebody, "SRPFLAG", "1");
                
         
        	}
        	else
        	{
        		 // 手机号
                DocumentUtil.addSubElementToEle(ebody, "telnum", header.getTelNum());
                
                // 获取操作类型
                DocumentUtil.addSubElementToEle(ebody, "operType", "Order");
                
                // 操作来源
                DocumentUtil.addSubElementToEle(ebody, "source", "");
                
                // 退订标识：只有操作类型为退订时，才取退订标识
                DocumentUtil.addSubElementToEle(ebody, "cancel_flag", "3");
                
                // 是否发送srp控制参数
                DocumentUtil.addSubElementToEle(ebody, "srpFlag", "1");
                
                // 是否提交 1为提交 0为不提交(校验) 默认提交
                DocumentUtil.addSubElementToEle(ebody, "isSubmit", "1");
                
                // 受理详细信息
                Element detailInfo = ebody.addElement("detailInfo");
                
                // 对象ID 
                DocumentUtil.addSubElementToEle(detailInfo, "spid", spid);
                
                // sp业务编码  
                DocumentUtil.addSubElementToEle(detailInfo, "spBizId", bizid);
                
                // sp业务编码类型
                DocumentUtil.addSubElementToEle(detailInfo, "spBizType", "");
                
                // sp所属domain 如果退订标志为"5"(5 == CANCEL_FLAG，根据DOMAIN进行退订
                DocumentUtil.addSubElementToEle(detailInfo, "spDomain", "");
                
                // 生效类型
                DocumentUtil.addSubElementToEle(detailInfo, "effectType", "");
                
                DocumentUtil.addSubElementToEle(detailInfo, "SPAttr", ""); 
                
                DocumentUtil.addSubElementToEle(detailInfo, "OuterOperSeq", "");
        	}	
            //modify begin fwx439896 2017-05-22 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3_需求分析设计说明书

            // 调用梦网订购业务接口
            return intMsgUtil.invoke(businessId, header, doc);
            
        }
        catch (Exception e)
        {
            logger.error("梦网业务订购失败！", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "梦网业务订购失败！");
        }
    }
	
	/**
	 * 湖北有价卡购买
	 * @param valueCardVO
	 * @param paramMap
	 * @return
	 * @remark create by wWX217192 2015-05-13 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
	 */
	public ReturnWrap getValueCards(MsgHeaderPO header, Map<String, String> inParam)
	{
		try
		{
			return getIntMsgUtil().invokeDubbo("BLCSElecCardSale", header, inParam);
		}
		catch(Exception e)
		{
			logger.error("有价卡购买失败！", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "有价卡购买失败！");
		}
	}
	
    /**
     * <有价卡校验>
     * <功能详细描述>
     * @param msgHeader 消息头
     * @param paramMap 消息体
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-6-10 OR_SD_201505_1022_山东电子充值卡改造需求_自助终端改造
     */
    public ReturnWrap authValueCard(MsgHeaderPO msgHeader, Map<String, String> paramMap)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");

            // 电子有价卡密码
            DocumentUtil.addSubElementToEle(ebody, "elecCardNum", paramMap.get("elecCardNum"));
            
            // 调用有价卡校验接口
            return intMsgUtil.invoke("cli_qry_chkIfEvcCard", msgHeader, doc);
        }
        catch (Exception e)
        {
            logger.error("有价卡类型校验失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"有价卡类型校验失败");
        }
    }
    
    /**
     * <有价卡充值>
     * @param msgHeader 消息头
     * @param valueCardPwd 有价卡密码
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-6-10 OR_SD_201505_1022_山东电子充值卡改造需求_自助终端改造
     */
    public ReturnWrap valueCardCharge(MsgHeaderPO msgHeader,String valueCardPwd)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");
            
            // 电子有价卡充值渠道 01：实体营业厅;03：移动热线;04：短信营业厅;05：自助终端;08：网上营业厅;09：赠送渠道
            DocumentUtil.addSubElementToEle(ebody, "channelType", "05");
            
            // 被充值号码
            DocumentUtil.addSubElementToEle(ebody, "calledIdValue", msgHeader.getRouteValue());
            
            // 电子充值卡密码
            DocumentUtil.addSubElementToEle(ebody, "cardPassWord", valueCardPwd);
            
            //modify begin lwx439898 2017-05-15 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_ELECCARDCHARGE))
            {               
                //能开 充值接入省份编码，山东：531
                DocumentUtil.addSubElementToEle(ebody, "callingProv", Constants.VALUECARD_SD_PROVCODE);
            }
            else
            {
                // 充值接入省份编码，山东：531
                DocumentUtil.addSubElementToEle(ebody, "calllingProv", Constants.VALUECARD_SD_PROVCODE);
                
            }
            //modify end lwx439898 2017-05-15 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
                      
            // 主叫手机号码 可不传
            DocumentUtil.addSubElementToEle(ebody, "callingIdValue", "");
            
            // 营业厅代码，可不传
            DocumentUtil.addSubElementToEle(ebody, "actionID", "");
            
            // 操作员编码
            DocumentUtil.addSubElementToEle(ebody, "actionUserID", msgHeader.getOperId());

            // 调用梦网订购业务接口
            return intMsgUtil.invoke("cli_busi_elecCardCharge", msgHeader, doc);
        }
        catch (Exception e)
        {
            logger.error("有价卡充值失败！", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "有价卡充值失败！");
        }
    }
    
    /**
     * <山东积分兑换电子券>
     * <功能详细描述>
     * @param header
     * @param doc
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-5-29 14:44:35 OR_SD_201505_61自助终端上增加积分兑换电子券
     */
    public ReturnWrap scoreExchange(MsgHeaderPO header,  Document doc)
    {
        try
        {
            return intMsgUtil.invoke("cli_busi_scoreExchangeSD", header, doc);
        }
        catch (Exception e)
        {
            logger.error("积分兑换电子券失败：", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "积分兑换电子券失败");
        }

    }
    
    /**
     * <短信发送app下载地址>
     * <功能详细描述>
     * @param header
     * @param doc
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-7-7 OR_SD_201506_152_山东_在自助终端增加“热门APP应用”下载
     */
    public ReturnWrap sendAddress(MsgHeaderPO header, Document doc)
    {
        try
        {
            return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_SENDSMSINFO, header, doc);
        }
        catch (Exception e)
        {
            logger.error("短信发送app下载地址失败：", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "短信发送app下载地址失败！");
        }
    }
    
    @Override
    public ReturnWrap getIs4GCard(MsgHeaderPO msgHeader)
    {
        return null;
    }

    @Override
    public ReturnWrap getAvailIntegralEbus(MsgHeaderPO msgHeader)
    {
        return null;
    }

    /**
     * @return 返回 intMsgUtil
     */
    public IntMsgUtil getIntMsgUtil()
    {
        return intMsgUtil;
    }
    
}



