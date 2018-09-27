/*
* @filename: NewSelfSvcCallImpl.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  新版协议调用接口
* @author: g00140516
* @de:  2012/07/03 
* @description: 
* @remark: create g00140516 2012/07/03 R003C12L07n01 OR_huawei_201205_740
*/
package com.gmcc.boss.selfsvc.call;

import java.util.List;
import java.util.Map;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.Axis2Client;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.quickpublish.model.MultiProdCommitPO;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdCommitPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DocumentUtil;

/**
 * 新版协议调用接口
 * 
 * @author  g00140516
 * @version  1.0, 2012/07/03
 * @see  
 * @since  
 */
public class NewSelfSvcCallImpl extends SelfSvcCallImpl
{
    private static Log logger = LogFactory.getLog(NewSelfSvcCallImpl.class);
    
    private IntMsgUtilNew intMsgUtilNew;
    
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
            String purpose = "NetShopReserv";//走按商城的定价
            String enddateRandom = (String)map.get("enddateRandom");// 到期释放时间
            
            // 封装包体入参
            // 被预定号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            //地市（查询时返回的信息）
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
           
            //modify begin lwx439898 2017-05-13 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_OCCUPYTELNEW_SD))
            {
                Element eReq_org_id = eBody.addElement("orgid");
                eReq_org_id.addText(org_id); 
                
                //用户姓名
                Element eReq_name = eBody.addElement("custname");
                eReq_name.addText(name);
                
                //联系号码，可为“”
                Element eReq_contacttel = eBody.addElement("contactphone");
                eReq_contacttel.addText(contacttel);
            }
            else              
            {
                //单位（查询时返回的信息）
                Element eReq_org_id = eBody.addElement("org_id");
                eReq_org_id.addText(org_id);
                
                //用户姓名
                Element eReq_name = eBody.addElement("name");
                eReq_name.addText(name);
                
                //联系号码，可为“”
                Element eReq_contacttel = eBody.addElement("contacttel");
                eReq_contacttel.addText(contacttel);
            }
            //modify end lwx439898 2017-05-13 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
            
            //默认：IdCard
            Element eReq_certtype = eBody.addElement("certtype");
            eReq_certtype.addText(certtype);
            
            //身份证号
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(certid);
                      
            //预约时长
            Element eReq_validday = eBody.addElement("validday");
            eReq_validday.addText(validday);
            
            //走按商城的定价
            Element eReq_purpose = eBody.addElement("purpose");
            eReq_purpose.addText(purpose);
            
            // modify begin hWX5316476 2014-3-1 OR_SD_201312_300 电子渠道号码预约防欺诈措施优化
            //到期释放时间
            Element eReq_enddateRandom = eBody.addElement("enddateRandom");
            eReq_enddateRandom.addText(enddateRandom);
            
            Document docXML = getIntMsgUtil().createMsg(doc, "cli_busi_occupytelnew_sd", menuid, "", "1", telnum, operID, atsvNum);
            // modify begin hWX5316476 2014-3-1 OR_SD_201312_300 电子渠道号码预约防欺诈措施优化
            
            return getIntMsgUtil().invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("占号失败：", e);
            
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
            Document docXML = intMsgUtilNew.createMsg(msgHeader);
            
            Element root = docXML.getRootElement();
            Element body = root.element("Body");
            
            Element eReq_body = body.addElement("cli_qry_addedprodlistreq");
            
            Element eReq_tagset = eReq_body.addElement("tagset");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eReq_tagset, "telnum", msgHeader.getTelNum());
            
            // 增值产品大类编码
            DocumentUtil.addSubElementToEle(eReq_tagset, "prodclass", "");
            
            // 查询方式,固定传5
            DocumentUtil.addSubElementToEle(eReq_tagset, "qrytype", "5");
            
            // 增值产品编码，固定传""
            DocumentUtil.addSubElementToEle(eReq_tagset, "prodid", "");
            
            // 套餐大类，套餐查询时用。传""
            DocumentUtil.addSubElementToEle(eReq_tagset, "pkgtype", "");
            
            // 是否输出产品包，默认输出。传""
            DocumentUtil.addSubElementToEle(eReq_tagset, "issolution", "");
            
            // 是否输出集团产品 0：不输出 1：输出 默认为1。固定传0
            DocumentUtil.addSubElementToEle(eReq_tagset, "grpsubsoid", "0");
            
            // 赠送方用户标识。传“”
            DocumentUtil.addSubElementToEle(eReq_tagset, "donorsubsid", "");
            
            // 是否查询宽带用户个人增值产品信息。传""
            DocumentUtil.addSubElementToEle(eReq_tagset, "ishavebandprod", "");
            
            // 客服IVR套餐使用查询用。传""
            DocumentUtil.addSubElementToEle(eReq_tagset, "queryflag", "");
            
            // 客服IVR套餐使用查询用。传""
            DocumentUtil.addSubElementToEle(eReq_tagset, "pkginfo", "");
            
            // 客服IVR套餐使用查询用。传""
            DocumentUtil.addSubElementToEle(eReq_tagset, "exceptclass", "");
            
            // 客服IVR套餐使用查询用。传""
            DocumentUtil.addSubElementToEle(eReq_tagset, "prodidlist", "");
            
            // 调用后台接口
            return intMsgUtilNew.invoke(docXML);
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
            Document docXML = intMsgUtilNew.createMsg(msgHeader);
            
            Element root = docXML.getRootElement();
            Element body = root.element("Body");
            
            Element eReq_body = body.addElement("cli_qry_prodattrreq");
            
            Element eReq_tagset = eReq_body.addElement("tagset");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eReq_tagset, "telnum", msgHeader.getTelNum());
            
            // 查询类型,0：NCODE;1：产品包下子产品或者模板下子产品
            DocumentUtil.addSubElementToEle(eReq_tagset, "qrytype", qryType);
            
            // NCODE或者产品编码
            DocumentUtil.addSubElementToEle(eReq_tagset, "ncode", nCode);
            
            // 是否所有受理类型标志,传""
            DocumentUtil.addSubElementToEle(eReq_tagset, "isallrectype", "");
            
            // 受理类型传ChangeProduct
            DocumentUtil.addSubElementToEle(eReq_tagset, "rectype", "ChangeProduct");
            
            // PCOpRec:开通
            DocumentUtil.addSubElementToEle(eReq_tagset, "opttype", operType);
            
            // 调用后台接口
            return intMsgUtilNew.invoke(docXML);
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
            Document docXML = intMsgUtilNew.createMsg(msgHeader);
            
            Element root = docXML.getRootElement();
            Element body = root.element("Body");
            
            // cli_busi_multiprodrecreq
            Element eReq_multiprodrecreq = body.addElement("cli_busi_multiprodrecreq");
            
            // tagset
            Element eReq_tagset = eReq_multiprodrecreq.addElement("tagset");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eReq_tagset, "telnum", multiProdCommitPO.getTelnum());
            
            // 标识该接口是否只进行校验，不进行办理 0：不进行校验，而是进行办理 1：只进行校验，不进行办理 可以传“”，默认为不进行校验，而是办理
            DocumentUtil.addSubElementToEle(eReq_tagset, "ischeck", multiProdCommitPO.getIscheck());
            
            // 赠送号码，用于赠送业务。传“”
            DocumentUtil.addSubElementToEle(eReq_tagset, "doneenum", multiProdCommitPO.getDoneenum());

            // 算费标识，预留字段，暂不使用。传“”
            DocumentUtil.addSubElementToEle(eReq_tagset, "iscalcfee", multiProdCommitPO.getIscalcfee());

            // 短信发送标识 0：不发送 1：发送 默认为1。外围固定传1
            DocumentUtil.addSubElementToEle(eReq_tagset, "issendsms", multiProdCommitPO.getIssendsms());
            
            // 传“”
            DocumentUtil.addSubElementToEle(eReq_tagset, "opersource", multiProdCommitPO.getOpersource());
            
            // crset[]
            //Element eReq_crsets = body.addElement("cli_busi_multiprodrecreq");
            
            List<ProdCommitPO> prodCommitPOList = multiProdCommitPO.getProdCommitList();
            for(ProdCommitPO prodCommitPO : prodCommitPOList)
            {
                // crset
                Element eReq_crset = eReq_multiprodrecreq.addElement("crset");
                                
                // 对产品包下的子产品或者模板下的子产品，传产品编码；其它传NCODE
                DocumentUtil.addSubElementToEle(eReq_crset, "ncode", prodCommitPO.getNcode());
                
                // 生效方式 2：立即 3：次月 4：次日 5：指定生效时间 默认是立即生效。需外围根据产品的实际情况传入
                DocumentUtil.addSubElementToEle(eReq_crset, "effecttype", prodCommitPO.getEffecttype());
                
                // 操作类型 PCOpRec:开通 PCOpMod:修改 PCOpDel:关闭 PCOpPau:暂停 PCOpRes:恢复
                DocumentUtil.addSubElementToEle(eReq_crset, "opertype", prodCommitPO.getOpertype());

                // 附加属性，格式： attrid1=attrvalue1=optype1#attrid2=attrvalue2=optype2… 即附加属性编码=附加属性值=附加属性的操作类型，其中操作类型目前没用，直接使用attrid1=attrvalue1= #attrid2=attrvalue2=#…即可
                DocumentUtil.addSubElementToEle(eReq_crset, "attrparam", prodCommitPO.getAttrparam());
                
                // 原增值产品编码，目前没用。直接传“”
                DocumentUtil.addSubElementToEle(eReq_crset, "oldprodid", prodCommitPO.getOldprodid());
                
                // 服务资源，格式：resid1=restype1=optype1#resid2=restype2=optype2…目前没用。直接传“”
                DocumentUtil.addSubElementToEle(eReq_crset, "serviceres", prodCommitPO.getServiceres());
                
                // 接口对应类型，对产品包下子产品或者模板下子产品受理的时候使用。 PCIntRelaNormal：普通业务 PCIntRelaRadio：业务切换
                DocumentUtil.addSubElementToEle(eReq_crset, "inftype", prodCommitPO.getInftype());
                
                // 是否按模板处理 1：是 “”：不是 目前没用，传“”即可
                DocumentUtil.addSubElementToEle(eReq_crset, "templetflag", prodCommitPO.getTempletflag());
                
                // 具体生效时间，格式：yyyymmddhh24miss 当effecttype=5时，必传，不可为空。 其它情况，传“”即可
                DocumentUtil.addSubElementToEle(eReq_crset, "startdate", prodCommitPO.getStartdate());
                
                // 具体失效时间，格式：yyyymmddhh24miss 当effecttype=5时，传用户指定失效时间，若用户未指定，传“”。其它情况，传“”即可
                DocumentUtil.addSubElementToEle(eReq_crset, "enddate", prodCommitPO.getEnddate());
                
                // 产品包编码。对产品包下子产品或者模板下子产品受理的时候使用
                DocumentUtil.addSubElementToEle(eReq_crset, "pkgid", prodCommitPO.getPkgid());
                
                // 对象类型，包括产品、优惠、服务、SP、主体产品、模板、ncode
                DocumentUtil.addSubElementToEle(eReq_crset, "objtype", prodCommitPO.getObjtype());
                
                // 优惠编码。对产品包下子产品或者模板下子产品受理的时候使用
                DocumentUtil.addSubElementToEle(eReq_crset, "privid", prodCommitPO.getPrivid());
                
                // 模板编码。对产品包下子产品或者模板下子产品受理的时候使用
                DocumentUtil.addSubElementToEle(eReq_crset, "templetid", prodCommitPO.getTempletid());
            }
            
            // 调用后台接口
            return intMsgUtilNew.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("产品受理失败：", e);
            
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
            Document docXML = intMsgUtilNew.createMsg(msgHeader);
            
            Element root = docXML.getRootElement();
            Element body = root.element("Body");
            
            // cli_qry_prodlistreq
            Element eReq_prodlistreq = body.addElement("cli_qry_prodlistreq");
            
            // tagset
            Element eReq_tagset = eReq_prodlistreq.addElement("tagset");
            
            // 手机号码
            DocumentUtil.addSubElementToEle(eReq_tagset, "telnum", msgHeader.getTelNum());
            
            // 产品包编码或者模板编码
            DocumentUtil.addSubElementToEle(eReq_tagset, "ncode", nCode);
            
            // 类型 2：产品包 3：模板
            DocumentUtil.addSubElementToEle(eReq_tagset, "type", type);

            // 受理类型
            DocumentUtil.addSubElementToEle(eReq_tagset, "rectype", "ChangeProduct");

            // 是否所有受理类型标志
            DocumentUtil.addSubElementToEle(eReq_tagset, "isallrectype", "");
            
            // 操作类型
            DocumentUtil.addSubElementToEle(eReq_tagset, "opttype", optType);

            // 调用后台接口
            return intMsgUtilNew.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询产品包下子产品：", e);
            
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
        // 手机号码
        String telnum = paramMap.get("telnum");
        
        // 受理类型
        String recType = paramMap.get("rectype");
        
        // ncode
        String ncode = paramMap.get("ncode");
        
        // 操作类型
        String operType = paramMap.get("operType");

        // 提示类型
        String tipType = paramMap.get("tipType");
        
        try
        {            
            Document docXML = intMsgUtilNew.createMsg(paramMap);
            
            Element root = docXML.getRootElement();
            Element body = root.element("Body");

            Element eReq_prodlistreq = body.addElement("cli_qry_ncodetipsreq");

            Element eReq_tagset = eReq_prodlistreq.addElement("tagset");
            
            // 手机号码
            Element eReq_telnum = eReq_tagset.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 受理类型
            Element eReq_rectype = eReq_tagset.addElement("rectype");
            eReq_rectype.addText(recType);
            
            Element eReq_crset = eReq_prodlistreq.addElement("crset");           
            
            // ncode
            Element eReq_ncode = eReq_crset.addElement("ncode");
            eReq_ncode.addText(ncode);
            
            // 操作类型
            Element eReq_operType = eReq_crset.addElement("opertype");
            eReq_operType.addText(operType);

            // 提示类型
            Element eReq_tipType = eReq_crset.addElement("tiptype");
            eReq_tipType.addText(tipType);

            return intMsgUtilNew.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询特别提示信息失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 根据对象编码查询特别提示信息
     * @param paramMap
     * @param prods 产品列表
     * @return
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public ReturnWrap qryObjectTips(Map<String, String> paramMap, List<Map<String, String>> prods)
    {
        // 手机号码
        String telnum = paramMap.get("telnum");
        
        // 受理类型
        String recType = paramMap.get("recType");
        
        try
        {            
            Document docXML = intMsgUtilNew.createMsg(paramMap);
            
            Element root = docXML.getRootElement();
            Element body = root.element("Body");

            Element eReq_prodlistreq = body.addElement("cli_qry_objecttipsreq");

            Element eReq_tagset = eReq_prodlistreq.addElement("tagset");
            
            // 手机号码
            Element eReq_telnum = eReq_tagset.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 受理类型
            Element eReq_rectype = eReq_tagset.addElement("rectype");
            eReq_rectype.addText(recType);
            
            Map<String, String> prodMap = null;
            
            String objectID = "";
            String objectType = "";
            String tipType = "";
            String operType = "";
            for (int i = 0; i < prods.size(); i++)
            {
                prodMap = prods.get(i);
                
                objectID = prodMap.get("objectID");
                objectType = prodMap.get("objectType");
                tipType = prodMap.get("tipType");
                operType = prodMap.get("operType");
                
                Element eReq_crset = eReq_prodlistreq.addElement("crset");
                
                // 对象编码
                Element eReq_objectid = eReq_crset.addElement("objectid");
                eReq_objectid.addText(objectID);
                
                // 对象类型
                Element eReq_objectType = eReq_crset.addElement("objecttype");
                eReq_objectType.addText(objectType);
                
                // 提示类型
                Element eReq_tipType = eReq_crset.addElement("tiptype");
                eReq_tipType.addText(tipType);
                
                // 操作类型
                Element eReq_operType = eReq_crset.addElement("opertype");
                eReq_operType.addText(operType);     
            }                 

            return intMsgUtilNew.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("查询特别提示信息失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 查询需要预约号码（山东）
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/01/23 R003C13L01n01 OR_SD_201301_279
     */
    public ReturnWrap qryChooseTelSD(Map map)
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
            String isselorgid= "1";
            String selcountryid = (String) map.get("selcountryid");
            
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
						.addSubElementToEle(eBody, "ISSELORGID", isselorgid);

				// 商城单位，SD.LA.ES(山东用)
				DocumentUtil.addSubElementToEle(eBody, "SELCOUNTRYID",
						selcountryid);
				
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
                
                // 该值为1的时候，表示通过外围传入单位ID
                Element eReq_isselorgid = eBody.addElement("isselorgid");
                eReq_isselorgid.addText(isselorgid);
                
                // isselorgid为1时，通过selcountryid传入单位ID
                Element eReq_selcountryid = eBody.addElement("selcountryid");
                eReq_selcountryid.addText(selcountryid);
          
            }	
            Document docXML = getIntMsgUtil().createMsg(doc, businessId, menuid, "", "0", region, operid, atsvNum);
            return   getIntMsgUtil().invoke(docXML);
            //modify begin fwx439896 2017-5-16 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理
            
        }
        catch (Exception e)
        {
            logger.error("选号失败：", e);
            
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
        String telnum = (String)map.get("telnumber");
        String smsparam =(String)map.get("smsparam");
        String templateno =(String)map.get("templateno");
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
            eReq_templateno.addText(templateno);
            
            //参数列表
            Element eReq_smsparam = eBody.addElement("smsparam");
            eReq_smsparam.addText(smsparam);
            
            //是否服务间调用
            Element eReq_isrvcall = eBody.addElement("isrvcall");
            eReq_isrvcall.addText("1");
            
            Document docXML = getIntMsgUtil().createMsg(doc, "cli_busi_sendsmsinfo", menuID, touchOID, "1", telnum, operID, termID);
            return getIntMsgUtil().invoke(docXML);

        }
        catch (Exception e)
        {
            logger.error("发送短信失败：", e);
            
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
    @SuppressWarnings("unchecked")
    public ReturnWrap deleteFamilyMem(Map map)
    {
        // 手机号码
        String servnum = (String)map.get("servnum");
        
        String menuid = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("servnumber");
            eReq_telnum.addText(servnum);
            
            Element eReq_operType = eBody.addElement("opertype");
            eReq_operType.addText("0");
            
            Element eReq_isSendSMS = eBody.addElement("issendsms");
            eReq_isSendSMS.addText("1");
            
            Document docXML = getIntMsgUtil().createMsg(doc, "cli_delete_familymem_sd", menuid, touchOID, "1", servnum, operID, termID);
            
            return getIntMsgUtil().invoke(docXML);
        }
        catch(Exception e)
        {
            logger.error("家庭网取消失败!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
	
    public IntMsgUtilNew getIntMsgUtilNew()
    {
        return intMsgUtilNew;
    }

    public void setIntMsgUtilNew(IntMsgUtilNew intMsgUtilNew)
    {
        this.intMsgUtilNew = intMsgUtilNew;
    }
}
