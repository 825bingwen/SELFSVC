/*
 * 文 件 名:  CardInstallBean.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  空白卡开户Bean
 * 修 改 人:  zKF69263
 * 修改时间:  2014-12-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.customize.sd.selfsvc.cardbusi.model.IdCardPO;
import com.customize.sd.selfsvc.cardbusi.model.ProdTempletPO;
import com.customize.sd.selfsvc.cardbusi.model.ServerNumPO;
import com.customize.sd.selfsvc.cardbusi.model.SimInfoPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 空白卡开户Bean
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-12-27]
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class CardInstallBean extends BaseBeanSDImpl
{
    /** 
     * 开户时证件号码校验
     * 
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param certType 证件类型
     * @param certId 证件号码
     * @see [类、类#方法、类#成员]
     */
    public void chkCertSubs(TerminalInfoPO termInfo, String curMenuId, String certType, String certId)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());

        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallSD().chkCertSubs(msgHeader, certType, certId);
        
        // 调用接口失败返回错误信息
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException("证件号码校验失败");
        }
        
        CTagSet cTagSet = (CTagSet)rw.getReturnObject();
        
        //modify begin lwx439898 2017-05-06 OR_huawei_201704_404_【山东移动接口迁移专题】-自助终端业务办理2
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CHKCERTINFOFORINSTALL))
        {
            String[] openEbusRtn = new String[] {"ifvalid"};
            String[] destRtn = new String[] {"ifValid"};
            cTagSet = CommonUtil.rtnConvert(cTagSet, BusinessIdConstants.CLI_BUSI_CHKCERTINFOFORINSTALL, openEbusRtn, destRtn);
        } 
        //modify end lwx439898 2017-05-06 OR_huawei_201704_404_【山东移动接口迁移专题】-自助终端业务办理2
        
        // 参数ifValid校验通过 1；不通过，0
        String ifValid = cTagSet.GetValue("ifValid");
        
        // 如果调用接口成功，返回的参数ifValid 校验通过 1；不通过，0 
        if (!"1".equals(ifValid))
        {
            throw new ReceptionException("户主证件号码下的有效用户数量超过用户上限");
        }
    }
    
    /**
     * 查询手机号码列表
     * 
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param selTelRule 查询类型 2：按前缀查询 3：按后缀查询 4:随机
     * @param telPrefix 号码前缀 sele_rule = 2时，如果没有限制，为_______；有限制，但不足7位，后面补_ sele_rule = 3时，为“”
     * @param telSuffix 号码后缀 sele_rule = 2时，为“” sele_rule = 3时，不足4位，后面补_
     * @pramam ProdTempletPO 产品模板
     * @return List<ServerNumPO>
     * @see [类、类#方法、类#成员]
     */
    public List<ServerNumPO> qryTelNumberListByRule(TerminalInfoPO termInfo, String curMenuId,
        String selTelRule, String telPrefix, String telSuffix, String mainProdid)
    {
    	String fitmod = "";
        // 处理前缀，长度不够在后面补_
        // 查询类型 2：按前缀查询 3：按后缀查询
        if ("2".equals(selTelRule))
        {
            // 右边补足11位_
        	fitmod = StringUtils.rightPad((null == telPrefix) ? "" : telPrefix, 11, "_");
        }
        // 处理后缀,长度不够在后面补_
        else if("3".equals(selTelRule))
        {
            // 右边补足4位_
        	fitmod = "_______" + StringUtils.rightPad((null == telSuffix) ? "" : telSuffix, 4, "_");;
        }
        else if ("4".equals(selTelRule))
        {
        	fitmod = "";
        }
        
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallSD().qryTelNumberListByRule(msgHeader, termInfo.getOrgid(), fitmod, mainProdid);
        
        // 调用接口失败
        // begin zKF66389 2015-09-15 9月份findbugs修改
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            // 错误信息和错误码定义
            String errMsg = "未查询到符合条件的记录";
            
            // 按前缀查询
            if ("2".equals(selTelRule))
            {
                // 查询类型 2：按前缀查询
                errMsg = "未查询到符合条件的记录(前缀：" + telPrefix + ")";
            }
            // 按后缀查询
            else if("3".equals(selTelRule))
            {
                // 查询类型 3：按后缀查询
                errMsg = "未查询到符合条件的记录(后缀：" + telSuffix + ")";
            }
            
            // 调用接口失败返回异常信息
            throw new ReceptionException(errMsg);
        }
        // 查询选号列表
    	CRSet crset = (CRSet)rw.getReturnObject();
        
        List<ServerNumPO> serverNumList = new ArrayList<ServerNumPO>();
        
        // 循环crset，放入List中
        for (int i = 1; i < crset.GetRowCount(); i++)
        {
            ServerNumPO serverNumPO = new ServerNumPO();
            
            // 手机号
            serverNumPO.setTelnum(crset.GetValue(i, 0));
            
            // 选号费 单位：分
            serverNumPO.setFee(CommonUtil.fenToYuan(crset.GetValue(i, 1)));
            
            // 单位
            serverNumPO.setOrgId(crset.GetValue(i, 3));
            
            // 保底费用（最低消费） 单位：分
            serverNumPO.setLowConsumFee(CommonUtil.fenToYuan(crset.GetValue(i, 6)));
           
            // 预存费用 单位：分
            serverNumPO.setLowConsumPre(CommonUtil.fenToYuan(crset.GetValue(i, 5)));
            
            // 加入serverNumList中
            serverNumList.add(serverNumPO);
        }
        
        // 返回
        return serverNumList;
    }
    
    /**
     * 号码资源暂选接口
     * 
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param telnum 手机号码
     * @see [类、类#方法、类#成员]
     */
    public void telNumTmpPick(TerminalInfoPO termInfo, String curMenuId, String telNum)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallSD().telNumTmpPick(msgHeader, telNum);
        
        // 调用接口失败返回错误信息
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
    /**
     * 校验空白卡资源是否可用
     * 
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param blankNo 空白卡序列号
     * @param prodid 主体产品
     * @param recType 受理类型，开户Install，补卡ChangeEnum
     * @see [类、类#方法、类#成员]
     */
    public void chkBlankNo(TerminalInfoPO termInfo, String curMenuId, String blankNo, String prodid, String recType)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());

        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallSD().chkBlankNo(msgHeader, termInfo, blankNo, prodid, recType);
        
        // 调用接口失败返回错误信息
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
    /** 
     * 校验空白卡是否是预置空卡
     * 
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param blankNo 空白卡序列号
     * @param telNum 手机号码
     * @see [类、类#方法、类#成员]
     */
    public void chkPreSetBlankCard(TerminalInfoPO termInfo, String curMenuId, String blankNo, String telNum, String recType)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallSD().chkPreSetBlankCard(msgHeader, blankNo, telNum, recType);
        
        // 调用接口失败返回错误信息
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet cTagSet = (CTagSet)rw.getReturnObject();
        
        // 1:是，0：不是
        String isPresetBlankCard = null;
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CHKISPRESETBLANKCARD))
        {
            isPresetBlankCard = cTagSet.GetValue("ISPRESETBLANKCARD");
        }
        else
        {
            isPresetBlankCard = cTagSet.GetValue("status");
        }
        // 如果调用接口成功，返回的参数isPresetBlankCard校验不通过
        if (!"1".equals(isPresetBlankCard))
        {
            throw new ReceptionException("该卡不是预置空白卡");
        }
        
    }
    
    /**
     * 计算开户费用
     * @param termInfo
     * @param curMenuId
     * @param telnum
     * @param mainprodid 主体产品ID
     * @param prodtempletid  主体产品模板iD
     * @param simInfoPO SIM卡信息
     * @param blankcardno 空白卡号
     * @return
     */
    public CRSet reckonRecFee(TerminalInfoPO termInfo, String curMenuId, String telnum, ProdTempletPO tpltTempletPO, SimInfoPO simInfoPO, String blankno)
    {
        //封装消息头
        MsgHeaderPO msgHead = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), 
                "", MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        ReturnWrap rw = this.getSelfSvcCallSD().reckonRecFee(msgHead, tpltTempletPO, simInfoPO, blankno);
        
        if (SSReturnCode.ERROR == rw.getStatus())
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        return (CRSet)rw.getReturnObject();
    }
    
    /**
     * 获取写卡信息加密数据
     * @param termInfo 终端机信息
     * @param curMenuId 菜单信息
     * @param telNum 手机号
     * @return 
     * @see [类、类#方法、类#成员]
     */
    public SimInfoPO getEncryptedData(TerminalInfoPO termInfo, String curMenuId, String blankNo, String telNum, String recType)
    {
        // 组装消息头
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), 
                "", MsgHeaderPO.ROUTETYPE_TELNUM, telNum);

        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallSD().getEncryptedData(msgHeader, blankNo, recType, termInfo.getRegion());
        
        if (SSReturnCode.ERROR == rw.getStatus())
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet v = (CTagSet)rw.getReturnObject();
        
        SimInfoPO simInfoPO = new SimInfoPO();
        
        // iccid
        simInfoPO.setIccid(v.GetValue("ICCID"));
        
        // imsi
        simInfoPO.setImsi(v.GetValue("IMSI"));
        
        // 加密数据
        simInfoPO.setIssueData(v.GetValue("issueData"));
        
        // 写卡流水
        simInfoPO.setFormNum(v.GetValue("fromnum"));
        
        // 老的iccid
        simInfoPO.setOldiccid(v.GetValue("OLDICCID"));
        
        return simInfoPO;
    }
    
    /**
     * 开户提交
     * <功能详细描述>
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param idCardPO 身份证信息PO
     * @param tpltTempletPO 主体产品PO
     * @param telnum 手机号码
     * @param totalfee 用户缴纳费用
     * @param SimInfoPO 卡信息
     * @param passwd 服务密码
     * @param chargeType 缴费类型
     * @param terminalSeq 银行缴费流水号
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, String> terminalInstall(TerminalInfoPO termInfo, String curMenuId, IdCardPO idCardPO,
        ProdTempletPO tpltTempletPO, String telnum, String totalfee, SimInfoPO simInfoPO, String passwd,
        String chargeType, String terminalSeq)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "", MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        // modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
        // 调用开户接口
        ReturnWrap rw = this.getSelfSvcCallSD().terminalInstall(msgHeader,
            simInfoPO, tpltTempletPO, idCardPO, totalfee, passwd, telnum, chargeType + termInfo.getBankno(), terminalSeq);
		// modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送

        // 调用接口失败返回错误信息
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet v = (CTagSet)rw.getReturnObject();
        
        Map<String, String> map = new HashMap<String, String>();
        
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_SELFINSTALL))
        {
            
            // 开户流水
            map.put("formnum", v.GetValue("FORMNUM"));
            
            // subsid
            map.put("subsid", v.GetValue("install_subsid"));
            
            // 受理流水
            map.put("dealnum", v.GetValue("RECOID"));
        }
        else
        {
            // 开户流水
            map.put("formnum", v.GetValue("install_formnum"));
            
            // subsid
            map.put("subsid", v.GetValue("install_subsid"));
            
            // 受理流水
            map.put("dealnum", v.GetValue("install_recoid"));
            
        }
        
        return map;
    }
    
    /**
     * 写卡成功失败接口
     * @param termInfo 终端机信息
     * @param curMenuId 菜单id
     * @param blankno 空白卡序列号
     * @param SimInfoPO 卡信息
     * @see [类、类#方法、类#成员]
     */
    public void updateWriteCardResult(TerminalInfoPO termInfo, String curMenuId, String blankno, SimInfoPO simInfoPO)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
        		"", MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallSD().updateWriteCardResult(msgHeader, blankno, simInfoPO);
        
        // 调用接口失败返回错误信息
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
    }
    
    /** 
     * 业务办理前记录业务费用信息
     * 
     * @param termInfo 终端信息
     * @param customer 客户信息
     * @param curMenuId 菜单id
     * @param servnumber 手机号码
     * @param chargeType 付款类型
     * @param terminalSeq 银行缴费流水号
     * @param amount 缴费总额
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2015-05-07 OR_SD_201503_333_SD_自助终端活动受理预存赠送
     */
    public void writeNetFeeInfo(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId,
        String servnumber, String chargeType, String terminalSeq, String amount, String acceptType)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, servnumber);
        
        // 调用开户接口
        ReturnWrap rw = this.getSelfSvcCallSD().writeNetFeeInfo(msgHeader,
            chargeType + termInfo.getBankno(), acceptType, terminalSeq, amount, null);
        
        // 调用接口失败返回错误信息
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
    }
}
