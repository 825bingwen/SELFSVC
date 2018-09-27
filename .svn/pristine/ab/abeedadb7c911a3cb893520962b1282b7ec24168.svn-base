/*
* @filename: BankHuakouBean.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  个人银行划扣业务
* @author: g00140516
* @de:  2012/09/05 
* @description: 
* @remark: create g00140516 2012/09/05 R003C12L07n01 OR_NX_201206_794
*/
package com.customize.nx.selfsvc.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mpi.client.common.MPIConstants;
import mpi.client.data.BindCell;
import mpi.client.data.OrderData;
import mpi.client.exception.PayException;
import mpi.client.trans.TopPayLink;

import com.customize.nx.selfsvc.bean.impl.BaseBeanNXImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * 个人银行划扣业务
 * 
 * @author  g00140516
 * @version  1.0, 2012/09/05
 * @see  
 * @since  
 */
public class BankHuakouBean extends BaseBeanNXImpl
{
    public static final Log logger = LogFactory.getLog(BankHuakouBean.class);
    
    /**
     * 查询银行划扣方式
     * 
     * @param terminalInfoPO
     * @param customer
     * @param menuid
     * @param payType 个人银行划扣方式
     * @return
     * @see 
     */
    public Map<String, Object> qryChargeType(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer,
            String menuid, String payType)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("menuID", menuid);
        paramMap.put("touchOID", customer.getContactId());
        paramMap.put("operID", terminalInfoPO.getOperid());
        paramMap.put("termID", terminalInfoPO.getTermid());
        paramMap.put("telnum", customer.getServNumber());
        paramMap.put("paytype", payType);
        
        ReturnWrap rw = getSelfSvcCallNX().qryChargeType(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            resultMap.put("flag", "1");
            
            resultMap.put("resultObj", (CTagSet) rw.getReturnObject());
        }
        else if (rw != null && rw.getReturnMsg() != null && !"".equals(rw.getReturnMsg().trim()))
        {
            resultMap.put("flag", "0");
            
            resultMap.put("resultMsg", rw.getReturnMsg());
        }
        else
        {
            resultMap.put("flag", "0");
            
            resultMap.put("resultMsg", "个人银行划扣方式查询失败");
        }
        
        return resultMap;
    }
    
    /**
     * 开通个人银行划扣业务
     * 
     * @param terminalInfoPO
     * @param customer
     * @param menuid
     * @param payType 个人银行划扣方式
     * @param bankAcct  银行账号
     * @param drawType 划扣类型
     * @param drawAmt 划扣金额
     * @param triggerAmt 触发金额
     * @return
     * @see 
     */
    public Map<String, Object> addChargeType(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer,
            String menuid, String payType, String bankAcct, String drawType, String drawAmt, String triggerAmt)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("addFlag", false);
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("menuID", menuid);
        paramMap.put("touchOID", customer.getContactId());
        paramMap.put("operID", terminalInfoPO.getOperid());
        paramMap.put("termID", terminalInfoPO.getTermid());
        paramMap.put("telnum", customer.getServNumber());
        paramMap.put("paytype", payType);
        paramMap.put("bankid", "");
        paramMap.put("bankacct", bankAcct);
        paramMap.put("drawtype", drawType);
        paramMap.put("drawamt", drawAmt);
        paramMap.put("trigamt", triggerAmt);
                
        ReturnWrap rw = getSelfSvcCallNX().addChargeType(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            resultMap.put("addFlag", true);
        }
        else if (rw != null)
        {
            resultMap.put("respCode", rw.getErrcode());
        }
        
        return resultMap;
    }
    
    /**
     * 取消个人银行划扣业务
     * 
     * @param terminalInfoPO
     * @param customer
     * @param menuid
     * @param payType 个人银行划扣方式
     * @return
     * @see 
     */
    public Map<String, Object> cancelChargeType(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer,
            String menuid, String payType)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("delFlag", false);
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("menuID", menuid);
        paramMap.put("touchOID", customer.getContactId());
        paramMap.put("operID", terminalInfoPO.getOperid());
        paramMap.put("termID", terminalInfoPO.getTermid());
        paramMap.put("telnum", customer.getServNumber());
        paramMap.put("paytype", payType);
        
        ReturnWrap rw = getSelfSvcCallNX().cancelChargeType(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            resultMap.put("delFlag", true);
        }
        else if (rw != null)
        {
            resultMap.put("respCode", rw.getErrcode());
        }
        
        return resultMap;
    }
        
    /**
     * 调用银联接口查询绑定关系
     * 
     * @param userID
     * @param subsID
     * @return
     * @see 
     */
    public Map<String, String> qryBindInfo(String userID, String subsID)
    {
        Map<String, String> resultMap = new HashMap<String, String>();
        
        OrderData tstOrderData = new OrderData();       
        
        //绑定关系查询交易码：4051
        tstOrderData.setTranCode("4051");
        
        //商户ID（每个商户对应唯一的ID）
        tstOrderData.setMerchantID(userID);
        
        String currTime = CommonUtil.getCurrentTime(Constants.DATE_PATTERN_LONG);
        
        //订单号（生成规则：从商户ID第四位开始的四位+取商户ID倒数四位+8位随机序列号）
        tstOrderData.setMerOrderNum("zzzd" + currTime.substring(2));
                
        //交易时间(注意位数:14位，交易时间和系统当前时间相差不能超过2小时)
        tstOrderData.setTranDateTime(currTime);
        
        //以用户的subsID作为定制标识号
        tstOrderData.setUserId(subsID);
        
        try 
        {
            //调用交易接口发送到中国银联互联网收单服务平台并同步接收返回结果
            //商户可根据返回结果进行相关后续处理
            tstOrderData = TopPayLink.PayOtherTrans(tstOrderData);
            if (tstOrderData == null) 
            {
                logger.error("查询定制标示号与卡号的绑定关系失败，返回值为null。");
            } 
            else 
            {
                //打印返回交易结果码,返回6个0(000000)表示交易成功
                //其他的交易码可参考MPI商户插件文档（附录一）.               
                if (tstOrderData.getRespCode() != null && tstOrderData.getRespCode().equals(MPIConstants.SUCCESS)) 
                {
                    // 获取绑定关系
                    List bindInfo = tstOrderData.genBindInfo();
                    if (bindInfo != null) 
                    {
                        StringBuffer buffer = new StringBuffer("查询定制标示号与卡号的绑定关系成功。");                        
                        
                        Iterator iter = bindInfo.iterator();
                        while (iter.hasNext()) 
                        {
                            BindCell bindCell = (BindCell) iter.next();
                            
                            buffer.append("绑定标识号：").append(bindCell.getUserId()).append("卡号：")
                                    .append(bindCell.getPan()).append("绑定时间：")
                                    .append(bindCell.getBindDateTime()).append("姓名：")
                                    .append(bindCell.getCustName()).append("手机号：").append(bindCell.getPayerTel());
                            
                            resultMap.put("userID", bindCell.getUserId());
                            resultMap.put("pan", bindCell.getPan());
                            resultMap.put("bindTime", bindCell.getBindDateTime());
                            resultMap.put("custName", bindCell.getCustName());
                            resultMap.put("payerTel", bindCell.getPayerTel());
                        }
                        
                        if (logger.isInfoEnabled())
                        {
                            logger.info(buffer.toString());
                        }
                        
                        return resultMap;
                    }
                    else
                    {
                        logger.error("获取定制标示号与卡号的绑定关系失败: bindInfo is null");
                    }
                }
                else 
                {
                    resultMap.put("respDesc", tstOrderData.getRespDesc());
                    
                    logger.error("查询定制标示号与卡号的绑定关系失败，返回码：" + tstOrderData.getRespCode());
                    
                    return resultMap;
                }
            }            
        } 
        catch (PayException ex) 
        {
            logger.error("查询定制标示号与卡号的绑定关系失败", ex);
        } 
        catch (Exception ex) 
        {
            logger.error("查询定制标示号与卡号的绑定关系失败", ex);
        }
        
        return null;
    }
    
    /**
     * 调用银联接口解除绑定关系
     * 
     * @param merchantID
     * @param pan 卡号
     * @param subsID 定制标示号
     * @return
     * @see 
     */
    public Map<String, Object> cancelBindInfo(String merchantID, String pan, String subsID)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("delFlag", false);
        
        OrderData tstOrderData = new OrderData();       
        
        //解除绑定关系交易码：4052
        tstOrderData.setTranCode("4052");
        
        //商户ＩＤ（每个商户对应唯一的ID）
        tstOrderData.setMerchantID(merchantID);
        
        String currTime = CommonUtil.getCurrentTime(Constants.DATE_PATTERN_LONG);
        
        //订单号（生成规则：从商户ID第四位开始的四位+取商户ID倒数四位+8位随机序列号）
        tstOrderData.setMerOrderNum("zzzd" + currTime.substring(2));
        
        //交易时间(注意位数:14位，交易时间和系统当前时间相差不能超过2小时)
        tstOrderData.setTranDateTime(currTime);
        
        tstOrderData.setPan(pan);
        
        tstOrderData.setUserId(subsID);
        
        try 
        {
            //调用交易接口发送到中国银联互联网收单服务平台并同步接收返回结果
            //商户可根据返回结果进行相关后续处理
            tstOrderData = TopPayLink.PayOtherTrans(tstOrderData);
            if (tstOrderData == null) 
            {
                logger.error("解除定制标示号与卡号的绑定关系失败，返回值为null。");
            } 
            else 
            {
                //打印返回交易结果码,返回6个0(000000)表示交易成功
                //其他的交易码可参考MPI商户插件文档（附录一）.
                if (tstOrderData.getRespCode() != null && tstOrderData.getRespCode().equals(MPIConstants.SUCCESS)) 
                {
                    if (logger.isInfoEnabled())
                    {
                        logger.info("解除定制标示号与卡号的绑定关系成功");
                    }
                    
                    resultMap.put("delFlag", true);
                }
                else 
                {
                    resultMap.put("respDesc", tstOrderData.getRespDesc());
                    logger.error("解除定制标示号与卡号的绑定关系失败，返回码：" + tstOrderData.getRespCode());
                }
            }            
        } 
        catch (PayException ex) 
        {
            logger.error("解除定制标示号与卡号的绑定关系失败", ex);
        } 
        catch (Exception ex) 
        {
            logger.error("解除定制标示号与卡号的绑定关系失败", ex);
        }

        return resultMap;
    }
    
    /**
     * 调用银联的无密实名认证接口
     * 
     * @param merchantID
     * @param pan 银行账号
     * @param idNo 身份证号
     * @param name
     * @return
     * @see 
     */
    public Map<String, Object> confirmBankInfo(String merchantID, String pan, String idNo, String name, String subsID)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("confirmFlag", false);
        
        OrderData tstOrderData = new OrderData();
        
        //以下字段是无密实名认证交易必输字段
        //无密实名认证交易码：4021
        tstOrderData.setTranCode("4021");
        
        //商户ＩＤ（每个商户对应唯一的ID）
        tstOrderData.setMerchantID(merchantID);
        
        String currTime = CommonUtil.getCurrentTime(Constants.DATE_PATTERN_LONG);
        
        //订单号（生成规则：从商户ID第四位开始的四位+取商户ID倒数四位+8位随机序列号）
        tstOrderData.setMerOrderNum("zzzd" + currTime.substring(2));
        
        //交易时间(注意位数:14位，交易时间和系统当前时间相差不超过2个小时)：20101125112433
        tstOrderData.setTranDateTime(currTime);
        
        //卡号
        //若输入定制标识号，则卡号可填写卡号的后4位或不填
        tstOrderData.setPan(pan);
        
        //以下字段是无密实名认证交易可输字段（未全部列出）
        //持卡人信息(01(证件类别)+310123198808083821(证件编号)+zhangsan(姓名))
        tstOrderData.setChInfo("01" + idNo, name, "", "", "", "");
        
        //定制标识号
        tstOrderData.setUserId(subsID);
        
        try 
        {
            //调用交易接口发送到中国银联互联网收单服务平台并同步接收返回结果
            //商户可根据返回结果进行相关后续处理
            tstOrderData = TopPayLink.RemitTrans(tstOrderData);
            
            if (tstOrderData == null) 
            {
                logger.error("无密实名认证失败，返回值为null。");
            } 
            else 
            {
                //打印返回交易结果码,返回6个0(000000)表示交易成功
                //其他的交易码可参考MPI商户插件文档（附录一）.
                if (tstOrderData.getRespCode() != null && tstOrderData.getRespCode().equals(MPIConstants.SUCCESS)) 
                {
                    if (logger.isInfoEnabled())
                    {
                        logger.info("无密实名认证成功");
                    }
                    
                    resultMap.put("confirmFlag", true);
                }
                else 
                {
                    resultMap.put("respDesc", tstOrderData.getRespDesc());
                    logger.error("无密实名认证失败，返回码：" + tstOrderData.getRespCode());
                }
            }
        } 
        catch (PayException ex) 
        {
            logger.error("无密实名认证失败", ex);
        } 
        catch (Exception ex) 
        {
            logger.error("无密实名认证失败", ex);
        }
        
        return resultMap;
    }
    
    /**
     * 调用银联接口建立绑定关系
     * 
     * @param customer
     * @param merchantID
     * @param pan 银行账号
     * @param idNo 身份证号
     * @param name 
     * @return
     * @see 
     */
    public Map<String, Object> addBindInfo(NserCustomerSimp customer, String merchantID, 
            String pan, String idNo, String name)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("bindFlag", false);
        
        OrderData tstOrderData = new OrderData();       
        
        //绑定关系建立交易码：4050
        tstOrderData.setTranCode("4050");
        
        //商户ID（每个商户对应唯一的ID）
        tstOrderData.setMerchantID(merchantID);
        
        String currTime = CommonUtil.getCurrentTime(Constants.DATE_PATTERN_LONG);
        
        //订单号
        tstOrderData.setMerOrderNum("zzzd" + currTime.substring(2));
        
        //交易时间(注意位数:14位，交易时间和系统当前时间相差不能超过2小时)
        tstOrderData.setTranDateTime(currTime);
        
        tstOrderData.setPan(pan);
        
        tstOrderData.setChInfo("01" + idNo, name, "", "", "", "");
        
        tstOrderData.setUserId(customer.getSubsID());
        
        tstOrderData.setPayerTel(customer.getServNumber());
        
        try 
        {
            //调用交易接口发送到中国银联互联网收单服务平台并同步接收返回结果
            //商户可根据返回结果进行相关后续处理
            tstOrderData = TopPayLink.PayOtherTrans(tstOrderData);
            if (tstOrderData == null) 
            {
                logger.error("绑定定制标示号与卡号失败，返回值为null。");
            } 
            else 
            {
                //打印返回交易结果码,返回6个0(000000)表示交易成功
                //其他的交易码可参考MPI商户插件文档（附录一）.
                if (tstOrderData.getRespCode() != null && tstOrderData.getRespCode().equals(MPIConstants.SUCCESS)) 
                {
                    if (logger.isInfoEnabled())
                    {
                        resultMap.put("bindFlag", true);
                        
                        logger.info("绑定定制标示号与卡号成功");
                    }
                }
                else 
                {
                    resultMap.put("respDesc", tstOrderData.getRespDesc());
                    
                    logger.error("绑定定制标示号与卡号失败，返回码：" + tstOrderData.getRespCode());
                }
            }            
        } 
        catch (PayException ex) 
        {
            logger.error("绑定定制标示号与卡号失败", ex);
        } 
        catch (Exception ex) 
        {
            logger.error("绑定定制标示号与卡号失败", ex);
        }

        return resultMap;
    }
}
