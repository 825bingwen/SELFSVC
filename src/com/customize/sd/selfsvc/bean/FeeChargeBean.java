package com.customize.sd.selfsvc.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 话费充值缴费
 * @author xkf29026
 *
 */
// modify begin cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371
public class FeeChargeBean  extends BaseBeanSDImpl
{
    
    /**
     * 话费充值账户信息查询
     * @param termInfo 终端机信息
     * @param servnumber 手机号码
     * @param curMenuId 当前菜单
     * @param bankNo 缴费方式
     * @param payDate 缴费时间
     * @param acceptType 受理类型
 	 * @param chargeType 缴费类型
     * @return
     */
    public Map<String, Object> qryfeeChargeAccount(TerminalInfoPO termInfo, String servnumber, String curMenuId, String bankNo,
            String payDate, String acceptType, String chargeType)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        //设置操作员id
        paramMap.put("operid", termInfo.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", termInfo.getTermid());
        
        //设置客户手机号
        paramMap.put("servnumber", servnumber);
        
        //设置客户接触id
        paramMap.put("touchoid", "");
        
        //设置当前菜单id
        paramMap.put("menuid", curMenuId);
        
        //缴费方式
        paramMap.put("bankNo", bankNo);
        
        //缴费日期
        paramMap.put("payDate", payDate);
        
        //受理类型
        paramMap.put("acceptType", acceptType);
        
        //缴费方式
        paramMap.put("chargeType", chargeType);
        
        //获取结果
        ReturnWrap rw = this.getSelfSvcCallSD().qryfeeChargeAccount(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // modify begin g00140516 2012/02/29 R003C12L03n01 接口可能返回CTagSet，也可能返回Vector
            CTagSet v = null;
            
            Object obj = rw.getReturnObject();
            if (obj instanceof Vector)
            {
               v = (CTagSet) ((Vector) obj).get(0);
            }
            else if (obj instanceof CTagSet)
            {
                v = (CTagSet) obj;
            }
            // modify end g00140516 2012/02/29 R003C12L03n01 接口可能返回CTagSet，也可能返回Vector
            
            String returnMsg = rw.getReturnMsg();
            Map<String, Object> map = new HashMap<String, Object>();
            // modify begin fwx439896 2017-12-25  R005C20LG1701   OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
            if(null!=v&&CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_FEE))
            {
                v=CommonUtil.lowerTagKey(v);
            }    
            // modify end fwx439896 2017-12-25 R005C20LG1701   OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
             // 设置返回结果
             map.put("returnObj", v);

            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        return null;
    }
    // modify end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371
    
    // modify begin cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371
    /**
     * 话费充值缴费
     * @param termInfo 终端机信息
     * @param curMenuId 当前菜单
     * @param servnumber 手机号码
     * @param bankNo 缴费方式
     * @param payDate 缴费时间
     * @param terminalSeq 终端序列号
     * @param money 缴费金额
     * @param acceptType 受理类型
     * @param invoiceType 发票类型
     * @param bankSite
     * @param bankOper
     * @param chargeType 缴费类型
     * @param regionFlag 0:跨区交费，非0，本地交费
     * @return
     */
    public Map chargeCommit(TerminalInfoPO termInfo, String curMenuId, 
    		String servnumber, String bankNo,
            String payDate, String terminalSeq, 
            String money, String acceptType, 
            String invoiceType, String bankSite,
            String bankOper, String chargeType)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("operid", termInfo.getOperid());
        paramMap.put("termid", termInfo.getTermid());
        paramMap.put("menuid", curMenuId);
        paramMap.put("servnumber", servnumber);
        paramMap.put("bankNo", bankNo);
        paramMap.put("payDate", payDate);
        paramMap.put("terminalSeq", terminalSeq);
        paramMap.put("money", money);
        paramMap.put("acceptType", acceptType);
        paramMap.put("invoiceType", invoiceType);
        paramMap.put("bankSite", bankSite);
        paramMap.put("bankOper", bankOper);
        paramMap.put("touchoid", "");
        paramMap.put("chargeType", chargeType);
        
        //modify begin sWX219697 2014-7-17 09:57:56 OR_huawei_201406_1125_支撑跨区缴费
        //受理地市
        paramMap.put("region", termInfo.getRegion());
        //modify end sWX219697 2014-7-17 OR_huawei_201406_1125_支撑跨区缴费
        
        ReturnWrap rw = this.getSelfSvcCallSD().chargeCommit(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet tagSet = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            //add begin fwx439896 R005C20LG1701   OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
            if(null!=tagSet&&CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CHARGEFEE))
            {
                tagSet=CommonUtil.lowerTagKey(tagSet);
            }
            //add end fwx439896 R005C20LG1701   OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
            //设置返回结果
            map.put("returnObj", tagSet);
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
            Map map = new HashMap();
            
            //设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        return null;
    }
    // modify end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
    
    /**
     * 不校验密码，直接获取用户信息
     * 
     * @param servnumber，手机号码
     * @param password，服务密码
     * @param termInfo，终端机信息
     * @return 用户信息，如果返回null，说明身份验证失败
     */
    public Map<String,String> getUserStatus(String servnumber, String password, TerminalInfoPO termInfo)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnum", servnumber);
        paramMap.put("password", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallSD().getUserStatus(paramMap);
        Map<String,String> map = new HashMap<String,String>();
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet cout = (CTagSet) rw.getReturnObject();
            
            map.put("status", cout.GetValue("status") == null ? "" : cout.GetValue("status"));
            map.put("servRegion", cout.GetValue("region") == null ? "" : cout.GetValue("region"));
            
            //add begin sWX219697 2014-7-19 11:57:22 OR_huawei_201406_1125_山东_支撑跨区缴费
            map.put("regionName", cout.GetValue("regionname") == null ? "" : cout.GetValue("regionname"));
            //add end sWX219697 2014-7-19 11:57:22 OR_huawei_201406_1125_山东_支撑跨区缴费
            return map;
        }
        
        return null;
    }
    
    /**
     * 话费充值账户应缴费用查询
     * @param termInfo 终端机信息
     * @param servnumber 手机号码
     * @param curMenuId 当前菜单
     * @author hWX5316476
     * @remark  add by hWX5316476 2014-03-12 OR_SD_201312_90_山东_自助终端交费应交话费显示的优化需求
     * @return
     */
    public Map<String, Object> qryRetcharge(TerminalInfoPO termInfo, String servnumber, String curMenuId)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 设置操作员id
        paramMap.put("operid", termInfo.getOperid());
        
        // 设置终端机id
        paramMap.put("termid", termInfo.getTermid());
        
        // 设置客户接触id
        paramMap.put("touchoid", "");
        
        // 设置当前菜单id
        paramMap.put("curMenuid", curMenuId);
        
        // 设置客户手机号
        paramMap.put("servnumber", servnumber);
        
        // 帐号
        paramMap.put("acctid", "");
        
        // 查询类型    默认1，1：个人，2：家庭，3集团
        paramMap.put("qrytype", "1");
        
        // 查询帐期
        paramMap.put("cycle", "");
        
        // 帐单状态 默认1；0：指定账期查询，1：没有结帐的月份的所有帐单（缴费时用（为1时，cycle不起作用）
        paramMap.put("status", "1");
        
        // 账单类型 默认1；“1”正常，“2”冷号，“3”核销（坏账）
        paramMap.put("isbaddebt", "1");
        
        // 受理渠道
        paramMap.put("accesstype", "bsacAtsv");
        
        // 保留字段，默认为 0
        paramMap.put("unbilled", "");
        
        // 业务代码 -1,减免滞纳金 0,缴费 1,退预交款 2,退网结帐 4,赠送预交款 5,坏帐回收 6,后打发票缴费 7,呆账回收
        paramMap.put("processcode", "0");
        
        // 用户号,可为空，除江苏外的其他省市使用）
        paramMap.put("subsid", "");
        
        // 是否需要返回用户信息""
        paramMap.put("isneedsubsinfo", "");
        
        // 是否要返回的是最新余额
        paramMap.put("isneedleftmoney", "");
        
        //获取结果
        ReturnWrap rw = this.getSelfSvcCallSD().qryRetcharge(paramMap);
        Map<String, Object> map = new HashMap<String, Object>();
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	CTagSet tagset = (CTagSet) rw.getReturnObject();
        	
        	// add begin lWX431760 2017-12-27 R005C20LG2401 DTS2017122704697
        	if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_RETCHARGE))
        	{
        	   tagset = CommonUtil.lowerTagKey(tagset);
        	}
        	// add end lWX431760 2017-12-27 R005C20LG2401 DTS2017122704697
        	
            String returnMsg = rw.getReturnMsg();
            String retcharge = tagset.GetValue("retcharge");
            //设置返回结果
            map.put("returnObj", tagset);
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            // 设置应交费用
            map.put("retcharge", retcharge);
            
            return map;
        }
        else if (rw != null && rw.getStatus() == SSReturnCode.ERROR)
        {
            String returnMsg = rw.getReturnMsg();
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        return null;
    }
    
    /**
     * 获取发票信息
     * 
     * @param termInfo
     * @param curMenuId
     * @param servnumber
     * @param recoid
     * @return 预存发票信息
     * @remark create wWX217192 2014-07-22 
     */
    public Map<String, Object> invoiceData(TerminalInfoPO termInfo, String curMenuId, String servnumber, String recoid)
    {
    	Map<String, String> paramMap = new HashMap<String, String>();
    	
    	paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        paramMap.put("menuID", curMenuId);
        paramMap.put("telnumber", servnumber);
        
        //受理流水号 
        paramMap.put("recoid", recoid);
        
        // 账期为0，后台接口协议提供
        paramMap.put("billCycle", "0");
        
        // 账号，营销组去查询，自助终端传值为空
        paramMap.put("acctId", "");
        
        // 打印类型，1：发票 0：收据
        paramMap.put("invType", "1");
        paramMap.put("touchOID", "");
        
        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
        //读取开关配置，是否启用电子发票,true为开启
        String isElectronInvoice = CommonUtil.getDictNameById(termInfo.getRegion(), "SH_ELECTRON_INVOICE");
        if("true".equals(isElectronInvoice))
        {
            //是否开具电子发票1是 0否
            paramMap.put("eleinvType", "1");
        }
        else
        {
            //是否开具电子发票1是 0否
            paramMap.put("eleinvType", "0");
        }
        //推送方式 1邮箱
        paramMap.put("pushType", "1");
        //推送信息 电子邮件地址
        paramMap.put("receiveMode", servnumber+"@139.com");
        //add end by cwx456134 2017-04-19 OR_SD_201703_234_电子发票优化需求
        
        ReturnWrap rw = this.getSelfSvcCallSD().invoiceData(paramMap);
        
        if (rw != null)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            
            if (rw.getStatus() == SSReturnCode.SUCCESS) {
                
                // 设置返回结果
                map.put("returnObj", rw.getReturnObject());
            }
            
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        
        return null;
    }
    
    /**
     * 话费连缴获取用户信息
     * @param termInfo 终端机信息
     * @param servnumber 手机号码
     * @param curMenuId 当前菜单
     * @param bankNo 缴费方式
     * @param payDate 缴费时间
     * @param acceptType 受理类型
 	 * @param chargeType 缴费类型
     * @param chargelogoid 话费充值缴费流水
     * @param regionFlag 是否跨区，1：本地，0：异地缴费
     * @param tMoney 用户实缴金额
     * @return 
     * @remark create by jWX216858 2015-4-16 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public CardChargeLogVO qryAcountMorePhone(TerminalInfoPO termInfo, String servnumber, String curMenuId, String bankNo,
            String payDate, String acceptType, String chargeType, String chargelogoid, String tMoney)
    {
    	Map<String, Object> result = this.qryfeeChargeAccount(termInfo, servnumber, curMenuId, bankNo, payDate, acceptType, chargeType);
    	
    	CardChargeLogVO morePhone = new CardChargeLogVO();
        String region = ""; // 手机归属地市
        String accepttype = ""; // 受理类型
        String custname = ""; // 客户姓名
        String shouldpay = ""; // 应缴费用
        String bal = ""; // 余额
        
        // 解析接口返回信息
    	if (result != null && result.size() > 0)
        {
    		CTagSet tagSet = (CTagSet)result.get("returnObj");
            
    		region = tagSet.GetValue("servregion");// 号码归属地
            
            //受理方式，regionFlag为0(跨区交费)时，受理类型为ZZYD（自助异地）
    		accepttype = tagSet.GetValue("accept_type");
            
    		custname = tagSet.GetValue("cust_name"); // 凭条打印客户名称
            
            if (StringUtils.isEmpty(accepttype))
            {
                throw new ReceptionException("查询用户:"+ servnumber +"受理类型失败");
            }
            
            // 应缴费用
            if (StringUtils.isEmpty(tagSet.GetValue("sum_fee")))
            {
                // 设置话费余额
                bal = tagSet.GetValue("balance");
            }
            else
            {
            	// 调用接口查询应缴话费金额
                Map<String, Object> resultfee = this.qryRetcharge(termInfo, servnumber, curMenuId);
                
                if(null == resultfee || null == resultfee.get("retcharge"))
                {
                    throw new ReceptionException("查询用户:"+ servnumber +"应缴话费金额失败");
                }
                String retcharge = (String) resultfee.get("retcharge");
            	shouldpay = CommonUtil.fenToYuan(retcharge);
            }
            
            // 存储用户信息
            morePhone.setServRegion(region); // 归属地市
            morePhone.settMoney(tMoney); // 缴费金额
            morePhone.setBalance(bal); // 余额
            morePhone.setServnumber(servnumber); // 手机号码
            morePhone.setYingjiaoFee(shouldpay); // 应缴费用
            morePhone.setCustName(custname); // 客户名称
            morePhone.setAcceptType(accepttype); // 受理类型
            morePhone.setChargeLogOID(chargelogoid);// 缴费日志流水
            return morePhone;
        }
    	else
    	{
    		throw new ReceptionException("获取用户:" + servnumber + "信息失败！");
    	}
    }
    
    /**
     * 话费连缴提交
     * @param termInfo 终端机信息
     * @param curMenuId 当前菜单
     * @param morePhone 话费连缴po
     * @param chargeType 缴费类型
     * @return
     * @remark create by jWX216858 2015-4-16 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    public CardChargeLogVO chargeCommitMorePhone(TerminalInfoPO termInfo, CardChargeLogVO morePhone, String curMenuId, String chargeType)
    {
    	CardChargeLogVO cardCharge = new CardChargeLogVO();
    	
    	// 山东接口 -- 发票打印标志，0＝不打印 1＝打印 2=不打印发票可以补打
        String invoiceType = "2";
        Map resultMap = this.chargeCommit(termInfo, 
        		curMenuId, 
        		morePhone.getServnumber(), 
        		termInfo.getBankno(), 
        		CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"), 
        		morePhone.getChargeLogOID(), 
        		morePhone.gettMoney(), 
        		morePhone.getAcceptType(), 
        		invoiceType, "", "", 
        		chargeType);
        
        // 解析返回信息
        if (resultMap != null && resultMap.size() > 1)
        {
        	CTagSet tagSet = (CTagSet) resultMap.get("returnObj");
            
        	if (null != tagSet && !tagSet.isEmpty())
            {
                String dealNum = (String)tagSet.GetValue("bill_nbr");// 受理编号
                
                // 受理流水，预存发票打印使用
                String recoid = (String)tagSet.GetValue("recoid");
                
                cardCharge.setRecoid(recoid); 
                cardCharge.setDealnum(dealNum);
                cardCharge.setCustName((String)tagSet.GetValue("cust_name"));
            }
                
        	cardCharge.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
        	cardCharge.setStatus(Constants.CHARGE_SUCCESS);
        	cardCharge.setDescription("银联卡交费成功！");
            
            // 把缴费状态置为成功
            cardCharge.setChargeStatus("0");
        }
        else
        {
        	String errorMsg = "";
            if (resultMap != null)
            {
            	errorMsg = (String) resultMap.get("returnMsg");
            }
            errorMsg = CommonUtil.isEmpty(errorMsg) ? "银联卡扣款成功，但是交费失败！" : errorMsg;
            
            cardCharge.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
            cardCharge.setStatus(Constants.PAYSUCCESS_CHARGEERROR);
            cardCharge.setDescription(errorMsg);
            cardCharge.setDealnum("");
            
            // 把缴费状态置为失败
            cardCharge.setChargeStatus("1");
        }
        
        // 缴费日志流水
        cardCharge.setChargeLogOID(morePhone.getChargeLogOID());
        
        // region
        cardCharge.setRegion(termInfo.getRegion());
        
        // 组织机构
        cardCharge.setOrgID(termInfo.getOrgid());
        return cardCharge;
    }
}
