/*
 * 文 件 名:  CardInstallBean.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  空白卡开户Bean
 * 修 改 人:  zKF69263
 * 修改时间:  2014-10-23
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.customize.hub.selfsvc.cardbusi.model.IdCardPO;
import com.customize.hub.selfsvc.cardbusi.model.ProdTempletPO;
import com.customize.hub.selfsvc.cardbusi.model.ServerNumPO;
import com.customize.hub.selfsvc.cardbusi.model.SimInfoPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 空白卡开户Bean
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-10-23]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CardInstallBean extends BaseBeanHubImpl
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
        ReturnWrap rw = this.getSelfSvcCallHub().chkCertSubs(msgHeader, certType, certId);
        
        // 调用接口失败返回错误信息
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException("证件号码校验失败");
        }
        
        CTagSet cTagSet = (CTagSet)rw.getReturnObject();
        
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
        // 处理前缀，长度不够在后面补_
        // 查询类型 2：按前缀查询 3：按后缀查询
        if ("2".equals(selTelRule))
        {
            // 右边补足7位_
            telPrefix = StringUtils.rightPad((null == telPrefix) ? "" : telPrefix, 7, "_");
        }
        // 处理后缀,长度不够在后面补_
        else if("3".equals(selTelRule))
        {
            // 右边补足4位_
            telSuffix = StringUtils.rightPad((null == telSuffix) ? "" : telSuffix, 4, "_");
        }
        
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallHub().qryTelNumberListByRule(msgHeader, termInfo.getOrgid(), selTelRule,
            telPrefix, telSuffix, mainProdid);
        
        // 调用接口失败
        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if (rw != null && rw.getStatus() == SSReturnCode.ERROR)
        if (rw.getStatus() == SSReturnCode.ERROR)
        // begin zKF66389 2015-09-15 9月份findbugs修改
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
        CRSet crset = new CRSet();
        if(rw.getReturnObject() instanceof Vector)
        {
            Vector v = (Vector)rw.getReturnObject();
            if(v.size() > 1)
            {
                crset = (CRSet)v.get(1);
            }
        }
        
        List<ServerNumPO> serverNumList = new ArrayList<ServerNumPO>();
        
        // 循环crset，放入List中
        for (int i = 0; i < crset.GetRowCount(); i++)
        {
            ServerNumPO serverNumPO = new ServerNumPO();
            
            // 手机号
            serverNumPO.setTelnum(crset.GetValue(i, 0));
            
            // 品牌
            serverNumPO.setBrand(crset.GetValue(i, 1));
            
            // 选号费 单位：分
            serverNumPO.setFee(CommonUtil.fenToYuan(crset.GetValue(i, 2)));
            
            // 单位
            serverNumPO.setOrgId(crset.GetValue(i, 3));
            
            // 保底费用（最低消费） 单位：分
            serverNumPO.setLowConsumFee(CommonUtil.fenToYuan(crset.GetValue(i, 4)));
           
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
        
        Map<String,String> inParamMap = new HashMap<String,String>();
        
        // 手机号码
        inParamMap.put("serial", telNum);
        
        // 资源类型 rsclTgsm
        inParamMap.put("resType", "rsclTgsm");
        
        // 操作标志 0为锁定，1为解锁
        inParamMap.put("operType", "0");
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallHub().telNumTmpPick(msgHeader, inParamMap);
        
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
     * @see [类、类#方法、类#成员]
     */
    public void chkBlankNo(TerminalInfoPO termInfo, String curMenuId, String blankNo)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());

        Map<String,String> inParamMap = new HashMap<String,String>();
        
        // 地区
        inParamMap.put("region", termInfo.getRegion());
        
        // 单位
        inParamMap.put("org_id", termInfo.getOrgid());
        
        // 操作员
        inParamMap.put("oper_id", termInfo.getOperid());
        
        // 资源大类 (可不传)
        //inParamMap.put("res_kind_id", "");
        
        // 资源类型必传
        inParamMap.put("resTypeID", "rsclTgsm");
        
        // 开始卡号 空白卡序列号
        inParamMap.put("startResID", blankNo);
        
        // 结束卡号 空白卡序列号
        inParamMap.put("endResID", blankNo);
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallHub().chkBlankNo(msgHeader, inParamMap);
        
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
    public void chkPreSetBlankCard(TerminalInfoPO termInfo, String curMenuId, String blankNo, String telNum)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallHub().chkPreSetBlankCard(msgHeader, blankNo, telNum);
        
        // 调用接口失败返回错误信息
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet cTagSet = (CTagSet)rw.getReturnObject();
        
        // 是否是预置空卡 TRUE 是 FALSE 不是
        String isPresetBlankCard = cTagSet.GetValue("isPresetBlankCard");
        
        // 如果调用接口成功，返回的参数isPresetBlankCard校验不通过
        if (!"TRUE".equals(isPresetBlankCard))
        {
            throw new ReceptionException("该卡不是预置空白卡");
        }
        
    }
    
    /**
     * 空白卡资源暂选
     * 
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param blankNo 空白卡序列号
     * @param telNum 手机号码
     * @return SimInfoPO
     * @see [类、类#方法、类#成员]
     */
    public SimInfoPO blankCardTmpPick(TerminalInfoPO termInfo, String curMenuId, String blankNo, String telNum)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "",
            MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallHub().blankCardTmpPick(msgHeader, blankNo, telNum);
        
        // 调用接口失败返回错误信息
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet ct = (CTagSet)rw.getReturnObject();;
        
        SimInfoPO simInfoPO = new SimInfoPO();
        
        simInfoPO.setIccid(ct.GetValue("iccID"));
        simInfoPO.setImsi(ct.GetValue("imsi"));
        simInfoPO.setEki(ct.GetValue("eki"));
        simInfoPO.setSmsp(ct.GetValue("smsp"));
        simInfoPO.setPin1(ct.GetValue("pin1"));
        simInfoPO.setPin2(ct.GetValue("pin2"));
        simInfoPO.setPuk1(ct.GetValue("puk1"));
        simInfoPO.setPuk2(ct.GetValue("puk2"));
                
        return simInfoPO;
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
        Map<String, String> paramMap = new HashMap<String, String>();
        
        //封装消息头
        MsgHeaderPO msgHead = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), 
                "", MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        Map<String, String> inParamMap = new HashMap<String, String>();
        
        // 手机号
        inParamMap.put("telNum", telnum);
        
        // 主体产品ID
        inParamMap.put("mainProd", tpltTempletPO.getMainProdId());
        
        // 产品模板ID
        inParamMap.put("prodTempletID", tpltTempletPO.getTempletId());
        
        // ICCID
        inParamMap.put("simNum", simInfoPO.getIccid());
        
        // IMSI
        inParamMap.put("imsi", simInfoPO.getImsi());
        
        // 空白卡序列号
        inParamMap.put("blankCardNo", blankno);
        
        // 是否返回 减免费用 传1
        inParamMap.put("retDiscountFee", "1");
        
        ReturnWrap rw = this.getSelfSvcCallHub().reckonRecFee(msgHead,inParamMap);
        
        if (SSReturnCode.ERROR == rw.getStatus())
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        return (CRSet)rw.getReturnObject();
    }
    
    /**
     * 获取写卡信息加密数据
     * @param termInfo
     * @param curMenuId
     * @param telnum
     * @param simInfoPO 卡信息
     * @param cardformNum 申请写卡流水号
     * @return String 加密数据
     */
    public String getEncryptedData(TerminalInfoPO termInfo,String curMenuId, String telnum,
            SimInfoPO simInfoPO,String cardformNum)
    {
        //封装消息头
        MsgHeaderPO msgHead = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), 
                "", MsgHeaderPO.ROUTETYPE_TELNUM, telnum);

        Map<String, Object> inParamMap = new HashMap<String, Object>();
        Map<String, String> inParam = new HashMap<String, String>();
        List dataList = new ArrayList();
        inParam.put("PIN1", simInfoPO.getPin1());
        inParam.put("PIN2", simInfoPO.getPin2());
        inParam.put("PUK1", simInfoPO.getPuk1());
        inParam.put("PUK2", simInfoPO.getPuk2());
        inParam.put("serverNumber", telnum);
        inParam.put("SMSP", simInfoPO.getSmsp());
        inParam.put("ICCID", simInfoPO.getIccid());
        inParam.put("IMSI", simInfoPO.getImsi());
        dataList.add(inParam);
        inParamMap.put("individualData", dataList);
        inParamMap.put("formNum", cardformNum);
        inParamMap.put("iccid", simInfoPO.getIccid());
        inParamMap.put("blankCardNo", simInfoPO.getBlankno());
        inParamMap.put("skey", "");
        ReturnWrap rw = this.getSelfSvcCallHub().getEncryptedData(msgHead,inParamMap);
        
        Map<String, String> map = new HashMap<String, String>();
        
        if (SSReturnCode.ERROR == rw.getStatus())
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        CTagSet v = (CTagSet)rw.getReturnObject();
        
        return v.GetValue("issueData");
        
    }
    
    /**
     * 开户
     * <功能详细描述>
     * @param termInfo 终端信息
     * @param curMenuId 当前菜单ID
     * @param idCardPO 身份证信息PO
     * @param tpltTempletPO 主体产品PO
     * @param telnum 手机号码
     * @param totalfee 用户缴纳费用
     * @param SimInfoPO 卡信息
     * @return
     */
    public String terminalInstall(TerminalInfoPO termInfo, String curMenuId, IdCardPO idCardPO, ProdTempletPO tpltTempletPO, 
            String telnum,String totalfee, SimInfoPO simInfoPO)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "", MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        Map<String, String> inParamMap = new HashMap<String, String>();
 
        // 受理渠道
        inParamMap.put("accessType", "bsacAtsv");
        
        // 开户号码
        inParamMap.put("installTelNum", telnum);
        
        // ICCID
        inParamMap.put("simNum", simInfoPO.getIccid());
        
        // IMSI
        inParamMap.put("IMSI", simInfoPO.getImsi());
        
        // 主体产品编码
        inParamMap.put("mainProdID", tpltTempletPO.getMainProdId());
        
        // 产品模板编码
        inParamMap.put("prodTempletID", tpltTempletPO.getTempletId());
        
        // 办理类型 
        inParamMap.put("recType", "Install");

        // 子业务类型（传InstallAtsv）
        inParamMap.put("subsType", "InstallAtsv");
        
        // 证件类型
        inParamMap.put("certType", "IdCard");
        
        // 证件号
        inParamMap.put("certID", idCardPO.getIdCardNo());
        
        // 客户名称
        inParamMap.put("custName", idCardPO.getIdCardName());
        
        // 总费用
        inParamMap.put("totalFee", totalfee);        
        
        // 密码 没有设置 生成随机密码 调用开户接口会给用户发送短信
        inParamMap.put("password",  CommonUtil.getRandomNum(6));
        
        // 联系电话
        inParamMap.put("linkPhone", "");
        
        // 联系地址
        inParamMap.put("linkAddr", idCardPO.getIdCardAddr());
        
        inParamMap.put("existDetail", "0");   
        
        // 客户地址
        inParamMap.put("addressInfo", idCardPO.getIdCardAddr());
       
        // 联系人姓名
        inParamMap.put("linkName", idCardPO.getIdCardName());
        
        // 空白卡号
        inParamMap.put("blankCardNo", simInfoPO.getBlankno());
        
        // 调用开户接口
        ReturnWrap rw = this.getSelfSvcCallHub().terminalInstall(msgHeader, inParamMap);
        
        // 调用接口失败返回错误信息
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet v = (CTagSet)rw.getReturnObject();
        
        // 开户流水
        return v.GetValue("installFormNum");
    }
    
    /**
     * 校验写卡结果
     * @param termInfo 终端机信息
     * @param curMenuId 当前菜单
     * @param formnum 流水
     * @param blankno 空白卡号
     * @param writeCardInfo 写卡所需家庙数据
     * @return
     */
    public String checkWriteCardInfo(TerminalInfoPO termInfo,String curMenuId,String formnum,String blankno,String writeCardInfo)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "", MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        // 入参
        Map<String, String> inParamMap = new HashMap<String, String>();

        // 空白卡号
        inParamMap.put("blankCardNo", blankno);
        
        // 流水号
        inParamMap.put("formNum", formnum);
        
        // 卡商返回的信息
        inParamMap.put("cardDsp", writeCardInfo);
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallHub().checkWriteCardInfo(msgHeader, inParamMap);
        
        // 调用接口失败返回错误信息
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet v = (CTagSet)rw.getReturnObject();
        
        // 0 成功  1 失败
        return v.GetValue("result");
    }
    
    /**
     * 作废卡
     * @param termInfo
     * @param curMenuId
     * @param blankno
     * @param SimInfoPO 卡信息
     * @param status 
     */
    public void updateWriteCardResult(TerminalInfoPO termInfo,String curMenuId,String blankno,SimInfoPO simInfoPO)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), "", MsgHeaderPO.ROUTETYPE_REGION, termInfo.getRegion());
        
        Map<String, String> inParamMap = new HashMap<String, String>();
        
        // 空白卡号
        inParamMap.put("blankCardNo", blankno);
        
        // ICCID
        inParamMap.put("iccID", simInfoPO.getIccid());
        
        // IMSI
        inParamMap.put("imsi", simInfoPO.getImsi());
        
        // 传空或imsi都行
        inParamMap.put("secImsi", "");
        
        // 写卡结果 1 成功  -1 失败
        inParamMap.put("iswSus", simInfoPO.getWriteResult());

        // 写卡结果信息
        inParamMap.put("errMsg", simInfoPO.getErrMsg());
        
        // 写卡结果错误码 0 成功 -1 失败
        inParamMap.put("errCode", simInfoPO.getErrCode());
        
        // 调用接口
        ReturnWrap rw = this.getSelfSvcCallHub().updateWriteCardResult(msgHeader, inParamMap);
        
        // 调用接口失败返回错误信息
        if (rw.getStatus() == SSReturnCode.ERROR)
        {
            throw new ReceptionException(rw.getReturnMsg());
        }
    }
}
