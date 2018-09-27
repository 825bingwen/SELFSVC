/*
 * 文件名：DetailedRecordsBean.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：月详单查询Bean
 * 修改人：g00140516
 * 修改时间：2010-12-16
 * 修改内容：新增
 * 
 */
package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 月详单查询
 * 
 * 
 * @author g00140516
 * @version 1.0，2010-12-16
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DetailedRecordsBean extends BaseBeanImpl
{
    private static Log logger = LogFactory.getLog(DetailedRecordsBean.class);
    
    /**
     * 调用后台接口详单查询权限验证
     * 
     * @param customerSimp，用户信息
     * @param terminalInfo，终端机信息
     * @param servNumber，手机号码
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean checkQueryAuth(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo, String servNumber, String menuID)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, terminalInfo.getOperid(), terminalInfo.getTermid(),
            customerSimp.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customerSimp.getServNumber());
        
        // 详单查询权限验证
        ReturnWrap rw = selfSvcCall.checkQueryAuth(msgHeader);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * 调用后台接口查询详单记录
     * 
     * @param customerSimp，用户信息
     * @param terminalInfo，终端机信息
     * @param month，查询月份
     * @param fee_type, 查询类型
     * @param type，详单类型
     * @param menuID
     * @param colNum，详单显示字段数目
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Vector queryCDRList(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo, String month,String fee_type,String type,
            String menuID, int colNum)
    {
        String startTime = "";
        String endTime = "";
        
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

        //宁夏的详单查询需要传月份、开始时间、结束时间
        if (Constants.PROOPERORGID_NX.equals(province))
        {
            String[] days = new String[]{"31", "28", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31"};
            
            String subYear = month.substring(0, 4);
            String subMonth = month.substring(4, 6);
            
            /**
             * 如果是2月，需要计算是不是闰年
             */
            int nMonth = Integer.parseInt(subMonth);
            if (nMonth == 2)
            {
                int nYear = Integer.parseInt(subYear);
                
                //世纪年，能被400整除，即为闰年。
                //普通年，能被4整除，即为闰年。
                //如2000是闰年，但是1900不是
                if ((nYear % 100 == 0 && nYear % 400 == 0) || (nYear % 100 != 0 && nYear % 4 == 0))
                {
                    days[2] = "29";
                }           
            }
            
            startTime = subYear + "-" + subMonth + "-01" + " 00:00:00";
            endTime = subYear + "-" + subMonth + "-" + days[nMonth - 1] + " 23:59:59";
        }    
        
        Map map = new HashMap();
        map.put("servNumber", customerSimp.getServNumber());
        map.put("month", month);
        map.put("CDRType", type);
        map.put("menuID", menuID);
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        map.put("fee_type", fee_type);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        
        // add begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
        map.put("verifyCode", customerSimp.getVerifyCode());
        // add end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
        
        // modify begin g00140516 2011/12/09 R003C11L12n01 详单查询实现socket协议
//        if (Constants.PROOPERORGID_NX.equals(province))
//        {
//            ReturnWrap rw = selfSvcCall.queryCDRListBySocket(map);
//            if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
//            {
//                String cdrInfo = (String) rw.getReturnObject();
//                
//                Vector records = parseCdrData(cdrInfo, colNum);
//                
//                return records;
//            }
//        }
//        else
//        {
            ReturnWrap rw = selfSvcCall.queryCDRList(map);
            if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
            {
                Vector v = (Vector)rw.getReturnObject();
                if (v != null && v.size() > 1)
                {
                    CRSet crset = (CRSet)v.get(1);
                    Vector records = parseCdrData(crset, colNum);
                    
                    return records;
                }
                
                return v;
            }            
//        }
        // modify end g00140516 2011/12/09 R003C11L12n01 详单查询实现socket协议
        
        return null;
    }
    
    private Vector parseCdrData(CRSet crset, int colNum)
    {
        Vector records = new Vector();
        
        if (crset != null && crset.GetRowCount() > 0)
        {
            /*
             * colNum：页面显示的列数 cols.length：接口返回的列数 通话、短信、梦网、WLAN的详单查询，接口返回几列，就在页面上显示几列 GPRS的详单查询，由于返回列数比较多，页面显示不开，所以只取部分列
             */
            String record = (String)crset.GetValue(0, 0);
            if (record.endsWith("~"))
            {
                record = record + " ";
            }
            
            String[] cols = record.split("~");
            
            StringBuffer buffer = null;
            for (int i = 0; i < crset.GetRowCount(); i++)
            {
                record = (String)crset.GetValue(i, 0);
                if (record.endsWith("~"))
                {
                    record = record + " ";
                }
                cols = record.split("~");
                
                buffer = new StringBuffer(1024);
                buffer.append(i + 1).append(Constants.STR_SPLIT_TRANS);
                for (int j = 0; j < colNum; j++)
                {
                    if (j == colNum - 1)
                    {
                        if ("".equals(cols[j]))
                        {
                            buffer.append(" ");
                        }
                        else
                        {
                            buffer.append(cols[j]);
                        }
                    }
                    else
                    {
                        if ("".equals(cols[j]))
                        {
                            buffer.append(" ").append(Constants.STR_SPLIT_TRANS);
                        }
                        else
                        {
                            buffer.append(cols[j]).append(Constants.STR_SPLIT_TRANS);
                        }
                        
                    }
                }
                
                records.add(buffer.toString());
            }
        }
        return records;
    }
    
//    /**
//     * 将String型的详单记录转为Vector
//     * 
//     * @param cdrInfo 详单记录，格式如：主叫~13909590065~2011-06-18 17:34:39~59~宁夏银川~0.22;主叫~13895389498~2011-06-18 17:47:38~144~宁夏银川~0.00
//     * @param colNum 一条详单记录对应的字段数
//     * @return 详单记录
//     * @see 
//     * @remark create g00140516 2011/12/09 R003C11L12n01 详单查询实现socket协议
//     */
//    private Vector<String> parseCdrData(String cdrInfo, int colNum)
//    {
//        Vector<String> records = new Vector<String>();
//        
//        if (null == cdrInfo || "".equals(cdrInfo.trim()))
//        {
//            return records;
//        }
//        
//        //客户姓名;客户品牌;品牌宣传语;入网时间;总费用;手机号码;记录条数;详单记录
//        String[] recordsArray = cdrInfo.split(";");
//        if (8 > recordsArray.length)
//        {
//            return records;
//        }
//                
//        String record = "";
//        
//        String[] cols = null;
//        
//        //第8部分是详单记录，所以从7开始
//        StringBuffer buffer = null;
//        for (int i = 7; i < recordsArray.length; i++)
//        {
//            record = recordsArray[i];
//            if (record.endsWith("~"))
//            {
//                record = record + " ";
//            }
//            cols = record.split("~");
//            
//            buffer = new StringBuffer(1024);
//            buffer.append(i - 7 + 1).append(Constants.STR_SPLIT_TRANS);
//            for (int j = 0; j < colNum; j++)
//            {
//                if (j == colNum - 1)
//                {
//                    if ("".equals(cols[j]))
//                    {
//                        buffer.append(" ");
//                    }
//                    else
//                    {
//                        buffer.append(cols[j]);
//                    }
//                }
//                else
//                {
//                    if ("".equals(cols[j]))
//                    {
//                        buffer.append(" ").append(Constants.STR_SPLIT_TRANS);
//                    }
//                    else
//                    {
//                        buffer.append(cols[j]).append(Constants.STR_SPLIT_TRANS);
//                    }
//                    
//                }
//            }
//            
//            records.add(buffer.toString());
//        }
//        
//        return records;
//    }
    
    /**
     * 查询用户是否已开通手机邮箱
     * 
     * @param customerSimp，用户信息
     * @param terminalInfo，终端机信息
     * @param servNumber，手机号码
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map qryMailbox(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo,String menuID)
    {
        Map map = new HashMap();
        map.put("servNumber", customerSimp.getServNumber());
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        map.put("menuID", menuID);
        map.put("email", customerSimp.getServNumber()+"@139.com");
        
        ReturnWrap rw = selfSvcCall.qrymailBox(map);

        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map returnMap = new HashMap();
            
            // 设置返回结果
            returnMap.put("returnObj", v);
            
            // 设置返回信息
            returnMap.put("returnMsg", returnMsg);
            
            return returnMap;
        }
        return null;
    }
    
    /**
     * 定制139免费邮箱
     * <功能详细描述>
     * @param customerSimp
     * @param terminalInfo
     * @param menuID
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map add139Mail(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo,String menuID)
    {
        Map map = new HashMap();
        map.put("servNumber", customerSimp.getServNumber());
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        map.put("menuID", menuID);
        map.put("email", customerSimp.getServNumber()+"@139.com");
        
        ReturnWrap rw = selfSvcCall.add139Mail(map);

        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map returnMap = new HashMap();
            
            // 设置返回结果
            returnMap.put("returnObj", v);
            
            // 设置返回信息
            returnMap.put("returnMsg", returnMsg);
            
            return returnMap;
        }
        return null;
    }
    
    /**
     * NG3.5帐详单改造专题之详单查询功能
     * 
     * @param customerSimp 用户信息
     * @param terminalInfo 终端机信息
     * @param month 查询月份
     * @param cdrType 详单类型
     * @param feeType 费用类型
     * @param menuID
     * @return
     * @see 
     * @remark create g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
     */
    public Map<String, String> queryDetailedRecords2012(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo, String month,
            String cdrType, String feeType, String menuID)
    {
        Map<String, String> resultMap = null;
        
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, terminalInfo.getOperid(), terminalInfo.getTermid(),
            customerSimp.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customerSimp.getServNumber(), 
            customerSimp.getVerifyCode());
        
        // 帐详单改造之详单查询
        ReturnWrap rw = selfSvcCall.queryDetailedRecords2012(msgHeader, month, cdrType, feeType);
        
        if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet tagSet = (CTagSet) rw.getReturnObject();
            
            resultMap = new HashMap<String, String>();          
            resultMap.put("billsummary", tagSet.GetValue("billsummary"));
            resultMap.put("billitem", tagSet.GetValue("billitem"));
        }
        
        return resultMap;
    }
    
    /**
     * NG3.5帐详单改造之查询客户信息
     * 
     * @param customerSimp 用户信息
     * @param terminalInfo 终端机信息
     * @param month 查询月份
     * @param menuID 菜单
     * @return
     * @see 
     * @remark create g00140516 2012/02/13 R003C12L02n01 OR_huawei_201112_953
     */
    public Vector queryCustomerInfo(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo, String month,
            String tokenFlag, String menuID)
    {        
        Map<String, String> map = new HashMap<String, String>();
        map.put("servNumber", customerSimp.getServNumber());
        map.put("month", month);       
        map.put("menuID", "10001101");
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        map.put("verifyCode", customerSimp.getVerifyCode());

        ReturnWrap rw = selfSvcCall.queryCustomerInfo(map);
        if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
        {
            Object obj = rw.getReturnObject();
            if (obj instanceof Vector)
            {
                return (Vector) obj;
            }                        
        }
        
        return null;
    }
    
    /**
     * NG3.5帐详单查询改造之山东详单查询
     * 
     * @param customerSimp
     * @param terminalInfo
     * @param cdrType 详单类型
     * @param startDate 账期开始时间
     * @param endDate 账期结束时间
     * @param menuID
     * @param countFlag
     * @param token
     * @return
     * @see 
     * @remark create g00140516 2012/02/13 R003C12L02n01 OR_huawei_201112_953
     */
    public Map<String, String> queryDetailedRecordsSD2012(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo, 
            String cdrType, String startDate, String endDate, String menuID, String iscycle, String cycle)
    {   
        Map<String, String> resultMap = null;
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("servNumber", customerSimp.getServNumber());        
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        map.put("verifyCode", customerSimp.getVerifyCode());
        map.put("iscycle", iscycle);
        
        // 按时间段查询
        if ("0".equals(iscycle))
        {
            map.put("cycle", "");
        }
        // 按账期查询
        else if ("1".equals(iscycle))
        {
            map.put("cycle", cycle);
        }
        else
        {
            logger.error("不支持的查询标识：" + iscycle);
        }
        
        startDate = startDate.replace("-", "");
        endDate = endDate.replace("-", "");
        map.put("startDate", startDate + "000000");         
        map.put("endDate", endDate + "235959");
        
        ReturnWrap rw = null;
        
        // 语音类返回码
        int voiceErrCode = 0;
        // 非语音类返回码
        int noVoiceErrCode = 0;
        
        // 增值业务分语音类和非语音类
        if (Constants.CDRTYPE_ISMG.equalsIgnoreCase(cdrType))
        {
            resultMap = new HashMap<String, String>();          
            
            // 增值业务语音类
            map.put("CDRType", "5");
            map.put("menuID", "10000601");
            
            rw = selfSvcCall.queryDetailedRecordsSD2012(map);
            
            if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                
                resultMap.put("totalfee-voice", tagSet.GetValue("totalfee"));
                resultMap.put(cdrType + "-voice", tagSet.GetValue("billinfo"));    
                
                // 详单优化，添加各种合计  add by lKF60882 2012-07-05 begin
                resultMap.put("zzywtotaltime", tagSet.GetValue("zzywtotaltime"));
                // 详单优化，添加各种合计  add by lKF60882 2012-07-05 end
                
                // 设置语音类返回码
                voiceErrCode = rw.getErrcode();
            }
            else if(null != rw)
            {
                // resultMap = new HashMap<String, String>();  
                resultMap.put("errorMessage", rw.getReturnMsg());
                
                // 设置语音类返回码
                voiceErrCode = rw.getErrcode();
            }
            
            // 增值业务语音类查询成功后，才去查询增值业务非语音类的
            map.put("CDRType", "6");
            map.put("menuID", "10000701");
            
            rw = selfSvcCall.queryDetailedRecordsSD2012(map);
            
            if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                resultMap.put("totalfee-nonvoice", tagSet.GetValue("totalfee"));
                resultMap.put(cdrType + "-nonvoice", tagSet.GetValue("billinfo"));
                
                // 设置非语音类返回码
                noVoiceErrCode = rw.getErrcode();
            }
            else if(null != rw)
            {
                // resultMap = new HashMap<String, String>();  
                resultMap.put("errorMessage", rw.getReturnMsg());
                
                // 设置非语音类返回码
                noVoiceErrCode = rw.getErrcode();
            }
            
            // 返回码=102表示没有此用户的详单信息，返回=100表示有详单信息
            // 详单信息 语音类没有 且 非语音类有  或 语音类有 且 非语音类没有 不提示"没有此用户的详单信息"
            if ((voiceErrCode == 102 && noVoiceErrCode == 100) 
                || (voiceErrCode == 100 && noVoiceErrCode == 102))
            {
                resultMap.remove("errorMessage");
            }
        }
        // 代收费业务分语音类和非语音类
        else if (Constants.CDRTYPE_INFOFEE.equalsIgnoreCase(cdrType))
        {
            resultMap = new HashMap<String, String>();          
            
            // 语音类
            map.put("CDRType", "7"); 
            map.put("menuID", "10000801");
            
            rw = selfSvcCall.queryDetailedRecordsSD2012(map);
            
            if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                
                resultMap.put("totalfee-voice", tagSet.GetValue("totalfee"));
                resultMap.put(cdrType + "-voice", tagSet.GetValue("billinfo"));     
                
                // 详单优化，添加各种合计  add by lKF60882 2012-07-05 begin
                resultMap.put("dsftotaltime", tagSet.GetValue("dsftotaltime"));
                // 详单优化，添加各种合计  add by lKF60882 2012-07-05 end
                
                // 设置语音类返回码
                voiceErrCode = rw.getErrcode();
            }
            else if(null != rw)
            {
                // resultMap = new HashMap<String, String>();  
                resultMap.put("errorMessage", rw.getReturnMsg());
                
                // 设置语音类返回码
                voiceErrCode = rw.getErrcode();
            }
            
            // 语音类查询成功后，才去查询非语音类的
            map.put("CDRType", "8");
            map.put("menuID", "10000901");
            
            rw = selfSvcCall.queryDetailedRecordsSD2012(map);
            
            if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                resultMap.put("totalfee-nonvoice", tagSet.GetValue("totalfee"));
                resultMap.put(cdrType + "-nonvoice", tagSet.GetValue("billinfo"));
                
                // 设置非语音类返回码
                noVoiceErrCode = rw.getErrcode();
            }
            else if(null != rw)
            {
                // resultMap = new HashMap<String, String>();  
                resultMap.put("errorMessage", rw.getReturnMsg());
                
                // 设置非语音类返回码
                noVoiceErrCode = rw.getErrcode();
            }
            
            // 返回码=102表示没有此用户的详单信息，返回=100表示有详单信息
            // 详单信息 语音类没有 且 非语音类有  或 语音类有 且 非语音类没有 不提示"没有此用户的详单信息"
            if ((voiceErrCode == 102 && noVoiceErrCode == 100) 
                || (voiceErrCode == 100 && noVoiceErrCode == 102))
            {
                resultMap.remove("errorMessage");
            }
        }
        else
        {
            if (Constants.CDRTYPE_FIXFEE.equalsIgnoreCase(cdrType))
            {
                map.put("CDRType", "1");
                map.put("menuID", "10000201");
            }
            else if (Constants.CDRTYPE_GSM.equalsIgnoreCase(cdrType))
            {
                map.put("CDRType", "2");
                map.put("menuID", "10000301");
            }
            else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(cdrType))
            {
                map.put("CDRType", "3");
                map.put("menuID", "10000401");
            }
            else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(cdrType))
            {
                map.put("CDRType", "4");
                map.put("menuID", "10000501");
            }
            
            rw = selfSvcCall.queryDetailedRecordsSD2012(map);
            if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                
                resultMap = new HashMap<String, String>();          
                resultMap.put("totalfee", tagSet.GetValue("totalfee"));
                resultMap.put("smstotalnum", tagSet.GetValue("smstotalnum"));
                resultMap.put("mmstotalnum", tagSet.GetValue("mmstotalnum"));
                resultMap.put("gprstotalnum", tagSet.GetValue("gprstotalnum"));
                resultMap.put("gprstotalfee", tagSet.GetValue("gprstotalfee"));
                resultMap.put("wlantotalnum", tagSet.GetValue("wlantotalnum"));
                resultMap.put("wlantotalfee", tagSet.GetValue("wlantotalfee"));
                resultMap.put(cdrType, tagSet.GetValue("billinfo"));
                
                // 详单优化，添加各种合计  add by lKF60882 2012-07-05 begin
                resultMap.put("txtotaltime", tagSet.GetValue("txtotaltime"));
                resultMap.put("thtotalfee", tagSet.GetValue("thtotalfee"));
                resultMap.put("cttotalfee", tagSet.GetValue("cttotalfee"));
                resultMap.put("gprstotaltime", tagSet.GetValue("gprstotaltime"));
                resultMap.put("gprstotalflux", tagSet.GetValue("gprstotalflux"));
                resultMap.put("wlantotaltime", tagSet.GetValue("wlantotaltime"));
                resultMap.put("wlantotalflux", tagSet.GetValue("wlantotalflux"));
                // 详单优化，添加各种合计  add by lKF60882 2012-07-05 end
                
                // add begin qWX279398 2015-8-3 OR_SD_201506_821_山东_增加宽带详单查询方案
                resultMap.put("wiredtotalnum", tagSet.GetValue("wiredtotalnum"));
                resultMap.put("wiredtotalfee", tagSet.GetValue("wiredtotalfee"));
                resultMap.put("wiredtotaltime", tagSet.GetValue("wiredtotaltime"));
                resultMap.put("wiredtotalflux", tagSet.GetValue("wiredtotalflux"));
                // add end qWX279398 2015-8-3 OR_SD_201506_821_山东_增加宽带详单查询方案
                
            }
            else if(null != rw)
            {
                resultMap = new HashMap<String, String>();  
                resultMap.put("errorMessage", rw.getReturnMsg());
            }
        }        
        
        return resultMap;
    }
    
    /**
     * 山东旧版详单查询功能，按时间段查询
     * 
     * @param customerSimp
     * @param terminalInfo
     * @param startDate
     * @param endDate
     * @param fee_type
     * @param type
     * @param menuID
     * @param colNum
     * @return
     * @see 
     * @remark create g00140516 2012/02/13 R003C12L02n01 OR_huawei_201112_953
     */
    public Vector queryCDRListSDOld(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo, String startDate, String endDate, String fee_type, String type,
            String menuID, int colNum, String month, String iscycle)
    {
        Map map = new HashMap();
        map.put("servNumber", customerSimp.getServNumber());        
        map.put("CDRType", type);
        map.put("menuID", menuID);
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        map.put("fee_type", fee_type);       
        map.put("verifyCode", customerSimp.getVerifyCode());
        
        // 按时间段查询
        if ("0".equals(iscycle))
        {
            map.put("month", "");
            map.put("startTime", startDate);
            map.put("endTime", endDate);
        }
        // 按账期查询
        else if ("1".equals(iscycle))
        {
            map.put("month", month.substring(0, 4) + month.substring(5, 7));
            map.put("startTime", "");
            map.put("endTime", "");
        }
        else
        {
            logger.error("不支持的查询标识：" + iscycle);
        }
        
        ReturnWrap rw = selfSvcCall.queryCDRList(map);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Vector v = (Vector)rw.getReturnObject();
            if (v != null && v.size() > 1)
            {
                CRSet crset = (CRSet)v.get(1);
                Vector records = parseCdrData(crset, colNum);
                    
                return records;
            }
                
            return v;
        }
        
        return null;
    }
    
    /**
     * 调用后台接口查询详单记录
     * 
     * @param customerSimp，用户信息
     * @param terminalInfo，终端机信息
     * @param month，查询月份
     * @param type，详单类型
     * @param menuID
     * @param colNum，详单显示字段数目
     * @param startTime
     * @param endTime
     * @return
     * @see 
     * @remark create g00140516 2012/03/14 R003C12L03n01 OR_NX_201203_128
     */
    public Vector queryCDRListNXOld(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo, String month, String type,
            String menuID, int colNum, String startTime, String endTime)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("servNumber", customerSimp.getServNumber());
        map.put("month", month);
        map.put("CDRType", type);
        map.put("menuID", menuID);
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        map.put("fee_type", "");
        map.put("startTime", startTime.substring(0, 4) + "-" + startTime.substring(4, 6) + "-" + startTime.substring(6) + " 00:00:00");
        map.put("endTime", endTime.substring(0, 4) + "-" + endTime.substring(4, 6) + "-" + endTime.substring(6) + " 23:59:59");
        map.put("verifyCode", customerSimp.getVerifyCode());

        ReturnWrap rw = selfSvcCall.queryCDRList(map);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Vector v = (Vector) rw.getReturnObject();
            if (v != null && v.size() > 1)
            {
                CRSet crset = (CRSet)v.get(1);
                Vector records = parseCdrData(crset, colNum);
                    
                return records;
            }
                
            return v;
        }
        else if (null != rw && 114 == rw.errcode)
        {
            String detailErrorMsg = (String) PublicCache.getInstance().getCachedData("SH_DETAIL_ERRORMSG");
            if (detailErrorMsg == null || "".equals(detailErrorMsg))
            {
                detailErrorMsg = rw.getReturnMsg();
            }
            Vector v = new Vector();
            v.add("errormsg:"+detailErrorMsg);
            return v;
        }
        
        return null;
    }
    
    /**
     * 新版详单查询
     * 
     * @param customerSimp
     * @param terminalInfo
     * @param cdrType
     * @param startDate
     * @param endDate
     * @param menuID
     * @return
     * @see
     * @remark create g00140516 2012/03/14 R003C12L03n01 OR_NX_201203_128
     */
    public Map<String, String> queryDetailedRecordsNX2012(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo, 
            String cdrType, String startDate, String endDate, String menuID, String cycle)
    {   
        Map<String, String> resultMap = null;
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("servNumber", customerSimp.getServNumber());
        map.put("startDate", startDate + "000000");         
        map.put("endDate", endDate + "235959");
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        map.put("verifyCode", customerSimp.getVerifyCode());
        map.put("menuID", menuID);
        
        // add begin g00140516 2013/02/28 R003C13L02n01 BUG_NX_201302_123 根据账期查询详单时增加两个入参：ISCYCLE和BILLCYCLE
        map.put("cycle", cycle);
        // add end g00140516 2013/02/28 R003C13L02n01 BUG_NX_201302_123 根据账期查询详单时增加两个入参：ISCYCLE和BILLCYCLE
        
        if (Constants.CDRTYPE_FIXFEE.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "1");
        }
        else if (Constants.CDRTYPE_GSM.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "2");
        }
        else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "3");
        }
        else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "4");
        }
        else if (Constants.CDRTYPE_ISMG.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "5");
        }
        else if (Constants.CDRTYPE_INFOFEE.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "6");
        }
        
        ReturnWrap rw = selfSvcCall.queryDetailedRecordsNX2012(map);
        if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet tagSet = (CTagSet) rw.getReturnObject();
            
            resultMap = new HashMap<String, String>();          
            resultMap.put("totalfee", tagSet.GetValue("totalfee"));
            
            resultMap.put("smstotalnum", tagSet.GetValue("smstotalnum"));
            resultMap.put("mmstotalnum", tagSet.GetValue("mmstotalnum"));
            
            resultMap.put("localcall", tagSet.GetValue("localcall"));
            resultMap.put("longcall", tagSet.GetValue("longcall"));
            resultMap.put("roamcall", tagSet.GetValue("roamcall"));
            resultMap.put("citycall", tagSet.GetValue("citycall"));
            resultMap.put("othercall", tagSet.GetValue("othercall"));
            resultMap.put("inlancall", tagSet.GetValue("inlancall"));
            resultMap.put("colonycall", tagSet.GetValue("colonycall"));
            resultMap.put("interncall", tagSet.GetValue("interncall"));
            resultMap.put("inlanroam", tagSet.GetValue("inlanroam"));
            resultMap.put("colonyroam", tagSet.GetValue("colonyroam"));
            resultMap.put("internroam", tagSet.GetValue("internroam"));
            
            resultMap.put("gprssum", tagSet.GetValue("gprssum"));
            resultMap.put("chargeflux", tagSet.GetValue("chargeflux"));
            resultMap.put("freechargeflux", tagSet.GetValue("freechargeflux"));
            resultMap.put("sumflux", tagSet.GetValue("sumflux"));
            resultMap.put("wlansumtime", tagSet.GetValue("wlansumtime"));
            resultMap.put("wlansumfee", tagSet.GetValue("wlansumfee"));
            resultMap.put("wlansum", tagSet.GetValue("wlansum"));
            resultMap.put("wlansumflux", tagSet.GetValue("wlansumflux"));
            resultMap.put("gprssumfee", tagSet.GetValue("gprssumfee"));
            resultMap.put("cmwapsum", tagSet.GetValue("cmwapsum"));
            resultMap.put("cmnetsum", tagSet.GetValue("cmnetsum"));
            resultMap.put("cmwapflux", tagSet.GetValue("cmwapflux"));
            resultMap.put("cmnetflux", tagSet.GetValue("cmnetflux"));
            resultMap.put("cmwapfreeflux", tagSet.GetValue("cmwapfreeflux"));
            resultMap.put("cmnetfreeflux", tagSet.GetValue("cmnetfreeflux"));
            resultMap.put("cmwapsumflux", tagSet.GetValue("cmwapsumflux"));
            resultMap.put("cmnetsumflux", tagSet.GetValue("cmnetsumflux"));
            resultMap.put("pubwlansum", tagSet.GetValue("pubwlansum"));
            resultMap.put("campuswlansum", tagSet.GetValue("campuswlansum"));
            resultMap.put("pubwlanflux", tagSet.GetValue("pubwlanflux"));
            resultMap.put("campuswlanflux", tagSet.GetValue("campuswlanflux"));
            resultMap.put("pubwlantime", tagSet.GetValue("pubwlantime"));
            resultMap.put("campuswlantime", tagSet.GetValue("campuswlantime"));
            resultMap.put("pubwlanfee", tagSet.GetValue("pubwlanfee"));
            resultMap.put("campuswlanfee", tagSet.GetValue("campuswlanfee"));
            resultMap.put("cmwapfee", tagSet.GetValue("cmwapfee"));
            resultMap.put("cmnetfee", tagSet.GetValue("cmnetfee"));
            
            resultMap.put(cdrType, tagSet.GetValue("billinfo"));
        }
        else if (null != rw && 114 == rw.errcode)
        {
            String detailErrorMsg = (String) PublicCache.getInstance().getCachedData("SH_DETAIL_ERRORMSG");
            if (detailErrorMsg == null || "".equals(detailErrorMsg))
            {
                detailErrorMsg = rw.getReturnMsg();
            }
            resultMap = new HashMap<String, String>(); 
            resultMap.put("errormsg", detailErrorMsg);
        }
        
        return resultMap;
    }
    
    /**
     * 检查详单打印是否超出次数限制
     * 
     * @param customerSimp
     * @param terminalInfo
     * @param cdrType
     * @param menuID
     * @return
     * @see
     * @remark create cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
     */
    public Map<String, String> queryPrintCdr(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo, 
            String cdrType, String menuID)
    {
        Map<String, String> resultMap = null;
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("telnum", customerSimp.getServNumber());
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        map.put("menuID", menuID);
        
        if (Constants.CDRTYPE_FIXFEE.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "1");
        }
        else if (Constants.CDRTYPE_GSM.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "2");
        }
        else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "3");
        }
        else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "4");
        }
        else if (Constants.CDRTYPE_ISMG.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "5");
        }
        else if (Constants.CDRTYPE_INFOFEE.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "6");
        }
        else if (Constants.CDRTYPE_OTHERFEE.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "7");
        }
        
        ReturnWrap rw = selfSvcCall.queryPrintCdr(map);
        if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
        {
            resultMap = new HashMap<String, String>();
            resultMap.put("success", "success");
        }
        else if (null != rw)
        {
            String detailErrorMsg = "";
            
            if (null != rw.getReturnMsg())
            {
                detailErrorMsg = (String)rw.getReturnMsg();
            }
            
            resultMap = new HashMap<String, String>();
            resultMap.put("errormsg", detailErrorMsg);
        }
        
        return resultMap;
    }
    
    /**
     *  更新详单打印次数
     * 
     * @param customerSimp
     * @param terminalInfo
     * @param cdrType
     * @param menuID
     * @return
     * @see
     * @remark create cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
     */
    public Map<String, String> writePrintCdrLog(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo, 
            String cdrType, String menuID)
    {
        Map<String, String> resultMap = null;
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("telnum", customerSimp.getServNumber());
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        map.put("menuID", menuID);
        
        if (Constants.CDRTYPE_FIXFEE.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "1");
        }
        else if (Constants.CDRTYPE_GSM.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "2");
        }
        else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "3");
        }
        else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "4");
        }
        else if (Constants.CDRTYPE_ISMG.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "5");
        }
        else if (Constants.CDRTYPE_INFOFEE.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "6");
        }
        else if (Constants.CDRTYPE_OTHERFEE.equalsIgnoreCase(cdrType))
        {
            map.put("CDRType", "7");
        }
        
        ReturnWrap rw = selfSvcCall.writePrintCdrLog(map);
        if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
        {
            resultMap = new HashMap<String, String>();
            resultMap.put("success", "success");
        }
        else if (null != rw)
        {
            String detailErrorMsg = "";
            
            if (null != rw.getReturnMsg())
            {
                detailErrorMsg = (String)rw.getReturnMsg();
            }
            
            resultMap = new HashMap<String, String>();
            resultMap.put("errormsg", detailErrorMsg);
        }
        
        return resultMap;
    }
    
    /**
     * <详单邮件下发>
     * <功能详细描述> 
     * @param customerSimp 客户资料信息
     * @param terminalInfo 终端信息
     * @param menuID 当前菜单编号
     * @param startcycle 开始时间
     * @param endcycle 结束时间
     * @param balcycle 账期
     * @param iscycle 是否按账期，1：按账期，0：按起止时间
     * @param cdrType 业务类别
     * @param staffid 工号
     * @param nosms 是否发送短信提醒 0：发送，1：不发送
     * @return String 1：发送成功，0：发送失败
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-04-29 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
     * 
     */
    public String sendRecordsMail(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo, String menuID,String startcycle,String endcycle,
    		String balcycle,String iscycle,String cdrType,String nosms)
    {   
    	Map<String,String> map = new HashMap<String,String>();
    	
    	//客户号码
        map.put("telnum", customerSimp.getServNumber());
        
        //客户接触id
        map.put("contactID", customerSimp.getContactId());
        
        //操作员编号
        map.put("operID", terminalInfo.getOperid());
        
        //终端机编号
        map.put("termID", terminalInfo.getTermid());
        
        //当前菜单id
        map.put("menuID", menuID);
        
        //查询地市
        map.put("qryregion", "");
 
        //业务模板对应的序号
        if (Constants.CDRTYPE_FIXFEE.equalsIgnoreCase(cdrType))
        {
        	//FIXFEE:套餐及固定费详单 
            map.put("cdr_type", "1");
        }
        else if (Constants.CDRTYPE_GSM.equalsIgnoreCase(cdrType))
        {
        	
        	//GSM：通话详单
            map.put("cdr_type", "2");
        }
        else if (Constants.CDRTYPE_SMS.equalsIgnoreCase(cdrType))
        {
        	
        	//SMS：短彩信详单
            map.put("cdr_type", "3");
        }
        else if (Constants.CDRTYPE_GPRSWLAN.equalsIgnoreCase(cdrType))
        {
        	
        	//GPRSWLAN：上网详单
            map.put("cdr_type", "4");
        }
        else if (Constants.CDRTYPE_ISMG.equalsIgnoreCase(cdrType))
        {
        	
        	//ISMG:增值业务扣费记录
            map.put("cdr_type", "5");
        }
        else if (Constants.CDRTYPE_INFOFEE.equalsIgnoreCase(cdrType))
        {
        	
        	//INFOFEE:代收费业务扣费记录
            map.put("cdr_type", "6");
        }
        else
        {
        	logger.error("不支持的cdrType查询标识：" + cdrType);
        }
        	
        //是否按账期查询 1 按账期，0 按起止时间，默认为0
        if("1".equals(iscycle))
        {
        	map.put("iscycle", "1");
        	map.put("balcycle", balcycle.substring(0, 4) + balcycle.substring(5, 7));
        	map.put("startcycle", "");
        	map.put("endcycle", "");
        }
        else if("0".equals(iscycle))
        {
        	map.put("iscycle", "0");
        	map.put("balcycle", "");
        	map.put("startcycle", startcycle);//开始时间
        	map.put("endcycle", endcycle);//结束时间
        }
        else
        {
            logger.error("不支持的iscycle查询标识：" + iscycle);
        }
        
        //客服接入号码
        map.put("accessnum", customerSimp.getServNumber());
        
        //工号
        map.put("staffid", terminalInfo.getOperid());
        
        //是否发送短信提醒，0 发送，1 不发送，默认为0。
        map.put("nosms", nosms);
        
        ReturnWrap rw = selfSvcCall.sendRecordsMail(map);
        
    	if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
    		
    		//发送成功
            return "1";
        }
        else
        {
        	
        	//发送失败
           	return "0";
        }
    }
    
}
