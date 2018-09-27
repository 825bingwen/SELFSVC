package com.customize.nx.selfsvc.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.customize.nx.selfsvc.bean.impl.BaseBeanNXImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.CurrencyUtil;

/**
 * 宁夏月账单查询功能bean
 * 
 * @author xkf29026
 * 
 */
public class MonthBillBean extends BaseBeanNXImpl
{
    /**
     * 月账单查询
     * 
     * @param customerSimp，用户信息
     * @param terminalInfo，终端机信息
     * @param month，查询月份
     * @param curMenuId，当前菜单
     * @param qryType，查询类型
     * @return 当前月账单查询
     * @see
     */
    public List qryMonthBill(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo, String month,
            String curMenuId)
    {
        Map map = new HashMap();
        map.put("telnumber", customerSimp.getServNumber());
        map.put("month", month);
        map.put("menuID", curMenuId);
        map.put("touchOID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = getSelfSvcCallNX().qryMonthBill(map);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Object obj = rw.getReturnObject();
            if (obj instanceof Vector)
            {
                Vector vector = (Vector) obj;
                
                //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
                if (vector.size() > 1)
                 //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
                {
                    CRSet crset = (CRSet) vector.get(1);
                    
                    if (crset != null && crset.GetRowCount() > 0)
                    {
                        /*
                         * 以如下的返回结果为例
                         * <billitem><subsname>#闫涛</subsname><itemname>增值业务费|020000000</itemname><fee>0.00</fee></billitem>
                         * <billitem><subsname>#闫涛</subsname><itemname>通信费|030000000</itemname><fee>0.00</fee></billitem>
                         * <billitem><subsname>#闫涛</subsname><itemname>短信费|020010000</itemname><fee>0.00</fee></billitem>
                         * <billitem><subsname>#闫涛</subsname><itemname>信息点播|020010001</itemname><fee>0.00</fee></billitem>
                         * <billitem><subsname>#闫涛</subsname><itemname>本地国内长途|030020000</itemname><fee>0.00</fee></billitem>
                         * <billitem><subsname>#闫涛</subsname><itemname>国内长途费|030020006</itemname><fee>0.00</fee></billitem>
                         * 最后的解析结果为
                         * list -- 02                              
                         *      -- list -- 增值业务费|0.00
                         *              -- 02001
                         *              -- list --    短信费|0.00
                         *                               信息点播|0.00
                         *      -- 03
                         *      -- list -- 通信费|0.00
                         *              -- 03002
                         *              -- list --    本地国内长途|0.00
                         *                               国内长途费|0.00
                         * 之所以会在list中保存02、03、02001这样的信息是为了确定账单条目的上下级关系。目前该应用程序仅支持三级，如020000000，前两位
                         * 可以确定一级菜单，中间三位可以区分二级菜单，其余的直接划分为三级菜单
                         */
                        List returnList = new ArrayList();
                        
                        List subList = null;
                        List subList1 = null;
                        
                        String itemName = "";
                        String fee = "";
                        String feeFen = "";
                        String[] pairs = null;
                        
                        for (int i = 0; i < crset.GetRowCount(); i++)
                        {
                            itemName = crset.GetValue(i, 1);
                            fee = crset.GetValue(i, 2);
                            feeFen = CommonUtil.yuanToFen(fee);
                            
                            pairs = itemName.split("\\|");
                            if (pairs.length == 2)
                            {
                                if (!returnList.contains(pairs[1].subSequence(0, 2)))
                                {
                                    returnList.add(pairs[1].subSequence(0, 2));
                                    
                                    subList = new ArrayList();
                                    subList.add(pairs[0] + "|" + fee);
                                    returnList.add(subList);
                                }
                                else
                                {
                                    //去掉费用为0的二级菜单、三级菜单
                                    if ("0".equals(feeFen))
                                    {
                                        continue;
                                    }
                                    
                                    int index = returnList.indexOf(pairs[1].subSequence(0, 2));
                                    
                                    subList = (List) returnList.get(index + 1);
                                       
                                    if (!subList.contains(pairs[1].subSequence(0, 5)))
                                    {
                                        subList.add(pairs[1].subSequence(0, 5));
                                            
                                        subList1 = new ArrayList();
                                        subList1.add("&nbsp;&nbsp;&nbsp;&nbsp;" + pairs[0] + "|" + fee);
                                        subList.add(subList1);
                                    }
                                    else
                                    {
                                         int index1 = subList.indexOf(pairs[1].subSequence(0, 5)); 
                                         
                                         subList1 = (List) subList.get(index1 + 1);
                                         subList1.add("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + pairs[0] + "|" + fee);
                                    }                                    
                                }
                            }
                        }
                        
                        String itemName1 = "";
                        String[] pairs1 = null;
                        for (int i = returnList.size() - 1; i > 0; )
                        {
                            subList = (List) returnList.get(i);
                            
                            itemName = (String) subList.get(0);
                            pairs = itemName.split("\\|");
                            
                            fee = "0";
                            for (int j = 2; j < subList.size(); )
                            {
                                subList1 = (List) subList.get(j);
                                
                                itemName1 = (String) subList1.get(0);
                                pairs1 = itemName1.split("\\|");
                                
                                fee = CurrencyUtil.add(fee, pairs1[1]);
                                j += 2;
                            }
                            
                            feeFen = CommonUtil.yuanToFen(fee);
                            
                            //去掉一级账单费用为0的记录
                            if ("0".equals(feeFen))
                            {
                                returnList.remove(i);
                                returnList.remove(i - 1);
                            }
                            //更新一级账单的费用
                            else
                            {
                                subList.remove(0);
                                subList.add(0, pairs[0] + "|" + fee);
                            }                            
                            
                            i -= 2;
                        }
                        
                        return returnList;
                    }
                }
            }
        }
        
        return null;
    }
    
    /**
     * 根据手机号码查询客户信息
     * 
     * @param terminalInfo 终端机信息
     * @param curMenuId 当前菜单
     * @param telnum 手机号码
     * @param cycle 账期
     * @return 客户信息
     * @see
     */
    public ReturnWrap getCustinfo(TerminalInfoPO terminalInfo, String curMenuId, String telnum, String cycle)
    {
        Map map = new HashMap();
        map.put("menuID", curMenuId);
        map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        map.put("telnum", telnum);// 手机号码
        map.put("cycle", cycle);// 账期
        
        ReturnWrap rw = this.getSelfSvcCallNX().getCustinfo(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Object obj = rw.getReturnObject();
            if (obj instanceof Vector)
            {
                return rw;
            }
        }
        
        // 返回
        return null;
        
    }
    
    /**
     * 取账单信息_新版
     * <功能详细描述>
     * @param terminalInfo 终端信息
     * @param curMenuId 当前菜单
     * @param telnum 手机号码
     * @param acctid 帐户ID，同客户信息查询接口返回的主账号
     * @param subsid 用户ID
     * @param startcycle 开始帐期
     * @param starttime 账期开始时间，格式yyyymmddhh24miss。客户信息查询接口返回的账期开始日期+000000
     * @param endtime 账期结束时间，格式yyyymmddhh24miss。客户信息查询接口返回的账期结束日期+235959
     * @param isunitpayment 是否合并付费
     * @param region 地市信息
     * @param arealist 区域列表，以逗号分割的区域标识。取值如下：
     *        SCORE 积分信息
     *        PKGINFO 通信量信息 
     *        BILLTREND 半年消费趋势图
     *        RECOMMEND 资费推荐
     *        ACCTBALANCE 平衡信息
     *        AGREEMENT 协议款
     *        PRESENT 赠送款
     *        PAYEDBYOTHER他人代付费
     *        PAYEDFOROTHER代他人付费
     *        SPBILL 代收费
     *        BILLINFO 费用详细信息
     *        INOUT 入账明细
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryMonthBill_new(TerminalInfoPO terminalInfo, String curMenuId, String telnum, String acctid, String subsid,
            String startcycle, String starttime, String endtime, String isunitpayment, String region, String arealist, String factory)
    {
        Map map = new HashMap();
        map.put("menuID", curMenuId);
        map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        map.put("telnum", telnum);// 手机号码
        map.put("acctid", acctid);// 帐户ID
        map.put("subsid", subsid);// 用户ID
        map.put("startcycle", startcycle);// 开始帐期
        map.put("starttime", starttime);// 帐期开始时间
        map.put("endtime", endtime);// 帐期结束时间
        map.put("isunitpayment", isunitpayment);// 是否合并付费
        map.put("region", region);// 区域
        map.put("arealist", arealist);// 区域列表
        map.put("factory", factory);// 厂家编码的密文标识，不能为空
        
        
        ReturnWrap rw = this.getSelfSvcCallNX().qryMonthBill_new(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return rw;
        }
        else 
        {
            return null;
        }
    }
}
