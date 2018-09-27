package com.customize.hub.selfsvc.call.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.alibaba.dubbo.common.json.ParseException;
import com.customize.hub.selfsvc.call.SelfSvcCallHub;
import com.customize.hub.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.invoice.model.CyclePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.call.IntMsgUtilNew;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;



public class SelfSvcCallHubTest implements SelfSvcCallHub
{
    private IntMsgUtil intMsgUtil;
    
    private IntMsgUtilNew intMsgUtilNew;
    
    public ReturnWrap qryArrear(Map paramMap)
    {
        try
        {
            String resp = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recQryArrear</menuid> "
                    + "<process_code>cli_qry_arrear</process_code><verify_code></verify_code><resp_time>20110513002353</resp_time> "
                    + "<sequence><req_seq>50</req_seq><operation_seq></operation_seq></sequence><retinfo> "
                    + "<rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg> "
                    + "</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?> "
                    + "<message_content><arrear><time>201101</time><fee>10.00</fee></arrear><arrear><time>201103</time> "
                    + "<fee>19.73</fee></arrear><arrear><time>201104</time><fee>121.25</fee></arrear><arrear><time>201105</time> "
                    + "<fee>99.12</fee></arrear></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(resp);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            return rtwp;
        }
        
    }
    
    /**
     * 湖北账户缴费方式查询（校验是否为托收账户）
     * @param map
     * @return
     * @remark create hWX5316476 2014-05-22 V200R003C10LG0501 OR_huawei_201405_234  自助终端接入EBUS一阶段_缴费
     */
    public ReturnWrap getAccSettleTypeByPayType(Map<String, String> map)
    {
        // 湖北统一接口平台转EBUS开关开启
        if(IntMsgUtil.isTransEBUS("PTGetAccSettleTypeByPayType"))
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(1);
            rtwp.setReturnMsg("");
            return rtwp;
        }
        
        return null;
    }
    
    /**
     * 湖北缴费查询接口
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryfeeChargeAccount(Map map)
    {
        try
        {
            // 统一接口平台转EBUS开启
            if(IntMsgUtil.isTransEBUS("Atsv_Qry_Fee_Hub"))
            {
                // 出参为键值对，放在JSONObj
                Map<String,Object> outParamMap = new HashMap<String,Object>();

                // 拼装出参
                outParamMap.put("region", "531");
                outParamMap.put("regionname", "济南");
                outParamMap.put("productid", "");
                outParamMap.put("productname", "pp.gt.ygtch.634");
                outParamMap.put("balance", "1000");
                JSONObject jsonObj = JSONObject.fromObject(outParamMap);
                System.out.println(jsonObj.toString());
                
                return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
            
            }
            String resp = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
                    + "<message_head version=\"1.0\"><menuid></menuid><process_code>cli_qry_fee_hub</process_code>"
                    + "<verify_code></verify_code><resp_time>20100921001956</resp_time>"
                    + "<sequence><req_seq>33</req_seq><operation_seq></operation_seq></sequence>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
                    + "</retinfo></message_head><message_body>" + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    + "<message_content>" + "<region>531</region>" + "<regionname>济南</regionname>"
                    + "<productid></productid>" + "<productname></productname>" + "<balance>1000</balance>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(resp);
        }
        catch (Exception e)
        {
            return new ReturnWrap();
        }
    }
    
    /**
     * 湖北缴费接口
     * 
     * @param map
     * @return
     */
    public ReturnWrap chargeCommit(Map map)
    {
        try
        {
            // 统一接口平台转EBUS开启
            if(IntMsgUtil.isTransEBUS("atsvBusiChargeFee"))
            {
                // 出参为键值对，放在JSONObj
                Map<String,Object> outParamMap = new HashMap<String,Object>();

                // 拼装出参
                outParamMap.put("dealNum", "123456");
                outParamMap.put("dealTime", "2011-05-28 10:07:55");
                outParamMap.put("presentOid", "6546498781111447");
                JSONObject jsonObj = JSONObject.fromObject(outParamMap);
                System.out.println(jsonObj.toString());
                String[][] outParamKeyDefine = {{"dealnum","dealtime","presentOid"},{"dealNum","dealTime","presentOid"}};

                
                return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),outParamKeyDefine,null);
            }
            String resp = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_busi_chargefee_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<dealNum>123456789</dealNum><dealTime>2011-05-28 10:07:55</dealTime>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(resp);
        }
        catch (Exception e)
        {           
            return new ReturnWrap();
        }
    }
    /**
     * 湖北代理商自助终端现金缴费接口 <功能详细描述>
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chargeCommitByAgent(Map map){
        try
        {
            // 统一接口平台转EBUS开启
            if(IntMsgUtil.isTransEBUS("BLDeductAcctBalance"))
            {
                // 出参为键值对，放在JSONObj
                Map<String,Object> outParamMap = new HashMap<String,Object>();

                // 拼装出参
                outParamMap.put("dealNum", "123456");
                outParamMap.put("dealTime", "2011-05-28 10:07:55");
                JSONObject jsonObj = JSONObject.fromObject(outParamMap);
                System.out.println(jsonObj.toString());
                return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    +"<menuid>feeCharge</menuid><process_code>cli_busi_agentaccchargeback_hub</process_code><verify_code></verify_code>"
                    +"<resp_time>20131114170411</resp_time><sequence><req_seq>2</req_seq><operation_seq></operation_seq></sequence>"
                    +"<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
                    +"</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    +"<message_content><dealNum>711131114405769745</dealNum><dealTime>2013-11-14 17:06:07</dealTime></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    /**
     * 湖北取系统参数接口
     * 
     * @param map
     * @return
     */
    public ReturnWrap getParamValue(Map map)
    {
        try
        {
            // 统一接口平台转EBUS开启
            if(IntMsgUtil.isTransEBUS("PTGetParameterByID"))
            {
                // 出参为键值对，放在JSONObj
                Map<String,Object> outParamMap = new HashMap<String,Object>();

                // 拼装出参
                outParamMap.put("paramValue", "123456");
                
                // 出参键值对 取值键名与输出的键名对应关系数组
                String[][] outParamKeyDefine = {{"paramValue"},{"paramvalue"}};
                JSONObject jsonObj = JSONObject.fromObject(outParamMap);
                System.out.println(jsonObj.toString());
                return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),outParamKeyDefine,null);
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
                    "<menuid></menuid><process_code>cli_qry_parametervaluebyid_hub</process_code><verify_code>" +
                    "</verify_code><resp_time>20110715144704</resp_time><sequence><req_seq>52</req_seq><operation_seq>" +
                    "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" +
                    "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>" +
                    "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><paramvalue>1</paramvalue>" +
                    "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 向用户发送随机密码短信
     * 
     * @param map
     * @return
     */
    public ReturnWrap sendSMS(Map map)
    {
        ReturnWrap rtwp = new ReturnWrap();
        rtwp.setStatus(SSReturnCode.SUCCESS);
        
        return rtwp;
    }
    
    /**
     * 获取发票信息
     * 
     * @param map
     * @return
     * @see
     */
    public ReturnWrap getInvoiceData(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_busi_chargefee</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<bcycle_count>2</bcycle_count>" 
                    // add begin zKF66389 2015-11-13 R003C10L11n01 OR_huawei_201510_582
                    + "<invoice_cnt>1</invoice_cnt>"
                    // add end zKF66389 2015-11-13 R003C10L11n01 OR_huawei_201510_582
                    + "<bill><bcycleid>201009</bcycleid><ss_fee>100</ss_fee><ys_fee>99.99</ys_fee>"
                    + "<capital_fee>玖拾玖元玖角玖分</capital_fee><last_balance>0</last_balance><this_balance>100</this_balance>"
                    + "<item_count>3</item_count><item_set>上次余额|0;本次缴纳|100;本次余额|100</item_set></bill>"
                    + "<bill><bcycleid>201009</bcycleid><ss_fee>100</ss_fee><ys_fee>99.99</ys_fee>"
                    + "<capital_fee>玖拾玖元玖角玖分</capital_fee><last_balance>0</last_balance><this_balance>100</this_balance>"
                    + "<item_count>3</item_count><item_set>上次余额|0;本次缴纳|100;本次余额|100</item_set></bill>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 月账单查询
     */
    public ReturnWrap qryMonthBill(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recQryArrear</menuid> "
                    + "<process_code>cli_qry_arrear</process_code><verify_code></verify_code><resp_time>20110513002353</resp_time> "
                    + "<sequence><req_seq>50</req_seq><operation_seq></operation_seq></sequence><retinfo> "
                    + "<rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg> "
                    + "</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?> "
                    + "<message_content>"
                    + "<billItem><type>1</type><recName>奥运套餐</recName><recFee>6000</recFee></billItem>"
                    + "<billItem><type>2</type><recName>彩铃</recName><recFee>500</recFee></billItem>"
                    + "<billItem><type>2</type><recName>来电提醒</recName><recFee>600</recFee></billItem>"
                    + "<billItem><type>2</type><recName>手机报信息费</recName><recFee>300</recFee></billItem>"
                    + "<billItem><type>2</type><recName>随心游</recName><recFee>400</recFee></billItem>"
                    + "<billItem><type>2</type><recName>来电显示</recName><recFee>500</recFee></billItem>"
                    + "<billItem><type>3</type><recName>本地通话费</recName><recFee>100</recFee></billItem>"
                    + "<billItem><type>3</type><recName>短信通信费</recName><recFee>450</recFee></billItem>"
                    + "<billItem><type>3</type><recName>省内漫游费</recName><recFee>20</recFee></billItem>"
                    + "<billItem><type>4</type><recName>代收梦网消息费</recName><recFee>800</recFee></billItem>"
                    + "<billItem><type>5</type><recName>总账保底消费额</recName><recFee>2650</recFee></billItem>"
                    + "<billItem><type>TotalFee</type><recName>本月费用合计</recName><recFee>8500</recFee></billItem>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
        
    }
    
    /**
     * 账户余额查询
     */
    public ReturnWrap queryBalance(Map<String, String> paramMap)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if(IntMsgUtil.isTransEBUS("nkfqoverage139Mail"))
            {
                // 出参为键值对，放在JSONObj
                Map<String,Object> outParamMap = new HashMap<String,Object>();
                
                // 拼装出参
                outParamMap.put("balance", "127");
                outParamMap.put("cashBalance", "1");
                outParamMap.put("cardBalance", "2");
                outParamMap.put("monDeduction", "3");
                outParamMap.put("presentBalance", "4");
                outParamMap.put("monpresentBalance", "5");
                outParamMap.put("dkBalance", "6");
                outParamMap.put("predkBalance", "7");
                outParamMap.put("left", "8");
                outParamMap.put("overdraft", "9");
                outParamMap.put("nowfee", "10");
                outParamMap.put("totalowe", "11");
                outParamMap.put("receiveMoney", "999");

                // 出参键值对 取值键名与输出的键名对应关系数组
                String[][] outParamKeyDefine = {{"balance","cashBalance","cardBalance","monDeduction","presentBalance","monpresentBalance","dkBalance","predkBalance","left","overdraft","nowfee","totalowe","receiveMoney"},
                                                {"balance","cashBalance","cardBalance","monDeduction","presentBalance","monPresentBalance","DKBalance","preDKBalance","availableBalance","credit","realTimeFee","hisArrears","receiveMoney"}};
                
                JSONObject jsonObj = JSONObject.fromObject(outParamMap);
                System.out.println(jsonObj.toString());
                return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),outParamKeyDefine,null);
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryBill</menuid><process_code>cli_qry_balance</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<balance>1299</balance>"
                    + "<cashBalance>1</cashBalance>"
                    + "<cardBalance>2</cardBalance>"
                    + "<monDeduction>3</monDeduction>"
                    + "<presentBalance>4</presentBalance>"
                    + "<monPresentBalance>5</monPresentBalance>"
                    + "<DKBalance>6</DKBalance>"
                    + "<preDKBalance>7</preDKBalance>"
                    + "<availableBalance>8</availableBalance>"
                    + "<credit>9</credit>"
                    + "<realTimeFee>10</realTimeFee>"
                    + "<hisArrears>11</hisArrears>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 湖北套餐信息查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryComboInfo(Map map)
    {
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("telnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        String billcycle = (String)map.get("billcycle");
        
        
            // 入参
            Map<String, Object> paramMap = new HashMap<String, Object>();
            
            // 电话号码
            paramMap.put("telNum", telnumber);
            
            // 查询账期,YYYYMM
            paramMap.put("cycle", billcycle);
            
            // 是否发送邮件，0不发送邮件；1发送邮件
            paramMap.put("isEmail", "0");
            paramMap.put("servType", "ALL");
            paramMap.put("reqCode", "1000");

            
            // 发送请求到Dubbo服务
            MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuid, operid, atsvNum, touchoid, "1", telnumber);    
            ReturnWrap rtwp = intMsgUtil.invokeDubbo("cli_intf_qusages", msgHeaderPO, paramMap,false);    
            
            //1:语音 分钟，2:短信 条，3:彩信 条 4：WLAN MB  5:EDUWLAN MB，6国内流量 MB 7.专用型流量 MB  8 WLAN 分钟 9 EDUWLAN 分钟
            Map attrTypeMap=new HashMap();
            attrTypeMap.put("1", "语音");
            attrTypeMap.put("2", "短信");
            attrTypeMap.put("3", "彩信");
            attrTypeMap.put("4", "WLAN");
            attrTypeMap.put("5", "EDUWLAN");
            attrTypeMap.put("6", "国内流量");
            attrTypeMap.put("7", "专用型流量");
            attrTypeMap.put("8", "WLAN");
            attrTypeMap.put("9", "EDUWLAN");
            if (SSReturnCode.SUCCESS == rtwp.getStatus())
            {
                String json=(String)rtwp.getReturnObject();
                try
                {
                    com.alibaba.dubbo.common.json.JSONObject  jsonObj = (com.alibaba.dubbo.common.json.JSONObject)com.alibaba.dubbo.common.json.JSON.parse(json);
                    com.alibaba.dubbo.common.json.JSONArray  mealInfoLst=(com.alibaba.dubbo.common.json.JSONArray)jsonObj.getArray("MealInfoLst");
                    List result = new ArrayList();
                    for(int i=0;i<mealInfoLst.length();i++){
                        com.alibaba.dubbo.common.json.JSONObject _obj=(com.alibaba.dubbo.common.json.JSONObject)mealInfoLst.get(i);
                        
                        List listInner = new ArrayList();
                        String attrType=(String)_obj.get("ATTR_TYPE");
                        String attrTypeName=(String)attrTypeMap.get(_obj.get("ATTR_TYPE"));                        
                        listInner.add(attrTypeName==null?attrType:attrTypeName);
                        listInner.add(_obj.get("PRODNAME"));
                        listInner.add(_obj.get("ATTR_NAME"));
                        listInner.add(_obj.get("FREE_VALUE"));
                        listInner.add(_obj.get("USAGE_VALUE"));
                        listInner.add(_obj.get("ATTR_UNIT"));
                        result.add(listInner);
                    }
                    rtwp.setReturnObject(result);
                }
                catch (ParseException e)
                {
                    rtwp.setStatus(SSReturnCode.ERROR);
                    rtwp.setReturnMsg("解析返回值异常");
                    e.printStackTrace();
                }                
            }
            
            return rtwp;
        
        
        
//        try
//        {
//            // 湖北统一接口平台转EBUS开关开启
//            if (IntMsgUtil.isTransEBUS("nkfQueryPrivUESD")) 
//            {
//                String response = "{errno:'0',retDataList:[{packagetype:'1type',packageid:'1privid',packagename:'1privname',presenttype:'1attname',presentnum:'1attcost',totalused:'1cyclecount',unit:'1unit'}"
//                    + ",{packagetype:'2type',packageid:'2privid',packagename:'2privname',presenttype:'2attname',presentnum:'2attcost',totalused:'2cyclecount',unit:'2unit'}"
//                    + ",{packagetype:'3type',packageid:'3privid',packagename:'3privname',presenttype:'3attname',presentnum:'3attcost',totalused:'3cyclecount',unit:'3unit'}"
//                    + ",{packagetype:'4type',packageid:'4privid',packagename:'4privname',presenttype:'4attname',presentnum:'4attcost',totalused:'4cyclecount',unit:'4unit'}"
//                    + ",{packagetype:'1type',packageid:'1privid',packagename:'1privname',presenttype:'1attname',presentnum:'1attcost',totalused:'1cyclecount',unit:'1unit'}"
//                    + ",{packagetype:'2type',packageid:'2privid',packagename:'2privname',presenttype:'2attname',presentnum:'2attcost',totalused:'2cyclecount',unit:'2unit'}"
//                    + ",{packagetype:'3type',packageid:'3privid',packagename:'3privname',presenttype:'3attname',presentnum:'3attcost',totalused:'3cyclecount',unit:'3unit'}"
//                    + ",{packagetype:'4type',packageid:'4privid',packagename:'4privname',presenttype:'4attname',presentnum:'4attcost',totalused:'4cyclecount',unit:'4unit'}"
//                    + ",{packagetype:'1type',packageid:'1privid',packagename:'1privname',presenttype:'1attname',presentnum:'1attcost',totalused:'1cyclecount',unit:'1unit'}"
//                    + ",{packagetype:'2type',packageid:'2privid',packagename:'2privname',presenttype:'2attname',presentnum:'2attcost',totalused:'2cyclecount',unit:'2unit'}"
//                    + ",{packagetype:'3type',packageid:'3privid',packagename:'3privname',presenttype:'3attname',presentnum:'3attcost',totalused:'3cyclecount',unit:'3unit'}"
//                    + ",{packagetype:'4type',packageid:'4privid',packagename:'4privname',presenttype:'4attname',presentnum:'4attcost',totalused:'4cyclecount',unit:'4unit'}]}";
//                
//                ReturnWrap rtwp = getIntMsgUtil().parseJsonResponse(response, null, 
//                        new String[]{"packagetype", "packageid", "packagename", "presenttype", "presentnum", "totalused", "unit"});
//                if (SSReturnCode.SUCCESS == rtwp.getStatus() && rtwp.getReturnObject() instanceof CTagSet) {
//                    
//                    Vector vector = new Vector();
//                    vector.add(rtwp.getReturnObject());
//                    vector.add(new CRSet());
//                    rtwp.setReturnObject(vector);
//                }
//                return rtwp;
//            }
//            
//            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
//                    + "<menuid></menuid><process_code>cli_busi_chargefee_hub</process_code><verify_code></verify_code>"
//                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
//                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
//                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
//                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
//                    + "<calctime>20110530120000</calctime><privitem><type>1type</type><privid>1privid</privid><privname>1privname</privname><attname>1attname</attname><attcost>1attcost</attcost><cyclecount>1cyclecount</cyclecount><unit>1unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>2type</type><privid>2privid</privid><privname>2privname</privname><attname>2attname</attname><attcost>2attcost</attcost><cyclecount>2cyclecount</cyclecount><unit>2unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>3type</type><privid>3privid</privid><privname>3privname</privname><attname>3attname</attname><attcost>3attcost</attcost><cyclecount>3cyclecount</cyclecount><unit>3unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>4type</type><privid>4privid</privid><privname>4privname</privname><attname>4attname</attname><attcost>4attcost</attcost><cyclecount>4cyclecount</cyclecount><unit>4unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>1type</type><privid>1privid</privid><privname>1privname</privname><attname>1attname</attname><attcost>1attcost</attcost><cyclecount>1cyclecount</cyclecount><unit>1unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>2type</type><privid>2privid</privid><privname>2privname</privname><attname>2attname</attname><attcost>2attcost</attcost><cyclecount>2cyclecount</cyclecount><unit>2unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>3type</type><privid>3privid</privid><privname>3privname</privname><attname>3attname</attname><attcost>3attcost</attcost><cyclecount>3cyclecount</cyclecount><unit>3unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>4type</type><privid>4privid</privid><privname>4privname</privname><attname>4attname</attname><attcost>4attcost</attcost><cyclecount>4cyclecount</cyclecount><unit>4unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>1type</type><privid>1privid</privid><privname>1privname</privname><attname>1attname</attname><attcost>1attcost</attcost><cyclecount>1cyclecount</cyclecount><unit>1unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>2type</type><privid>2privid</privid><privname>2privname</privname><attname>2attname</attname><attcost>2attcost</attcost><cyclecount>2cyclecount</cyclecount><unit>2unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>3type</type><privid>3privid</privid><privname>3privname</privname><attname>3attname</attname><attcost>3attcost</attcost><cyclecount>3cyclecount</cyclecount><unit>3unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>4type</type><privid>4privid</privid><privname>4privname</privname><attname>4attname</attname><attcost>4attcost</attcost><cyclecount>4cyclecount</cyclecount><unit>4unit</unit></privitem>"
//                    + "</message_content>]]></message_body></message_response>";
//            return intMsgUtil.parseResponse(response);
//        }
//        catch (Exception e)
//        {
//            ReturnWrap rtwp = new ReturnWrap();
//            rtwp.setStatus(0);
//            rtwp.setReturnMsg("");
//            e.printStackTrace();
//            return rtwp;
//        }
    }
    
    /**
     * 本机品牌资费及已开通功能
     */
    public ReturnWrap qryFavourable(MsgHeaderPO msgHeaderPO)
    {
        // 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("kfQueryProductTree")) 
        {
            String response = "[{\"applydate\":\"2013-11-11 00:00:00\",\"cdeep1\":\"1\",\"enddate\":\"\",\"packageid\":\"150\"," +
            "\"packagename\":\"移动数据流量业务功能\",\"pkgtype\":\"产品\"},{\"applydate\":\"2013-11-11 00:00:00\"," +
            "\"cdeep1\":\"1.1\",\"enddate\":\"\",\"packageid\":\"150\",\"packagename\":\"移动数据流量功能\",\"pkgtype\":" +
            "\"服务\"},{\"applydate\":\"2011-08-26 19:13:59\",\"cdeep1\":\"2\",\"enddate\":\"\",\"packageid\":" +
            "\"pkg.serv.fetion\",\"packagename\":\"飞信业务包\",\"pkgtype\":\"产品包\"},{\"applydate\":\"2011-08-26 19:13:59\"," +
            "\"cdeep1\":\"3\",\"enddate\":\"\",\"packageid\":\"pkg.serv.my\",\"packagename\":\"服务漫游包\",\"pkgtype\":\"产品包\"}]";
          
            return getIntMsgUtil().parseJsonResponse(response, null, new String[]{"cdeep1","pkgtype", "packageid", "packagename", "applydate", "enddate"});
        }
        
        // 正确
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>showUserservice</menuid><process_code>cli_qry_producttree_hub" +
                "</process_code><verify_code></verify_code><resp_time>20110802163908</resp_time><sequence><req_seq>34" +
                "</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode>" +
                "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>" +
                "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
                "<detail><level>1</level><type>产品</type><typeid>pkg.prod.zshf</typeid>" +
                "<typename>[可选优惠包27]－湖北折扣分月送类</typename><startdate>2009-07-01 00:00:00</startdate>" +
                "<enddate></enddate></detail><detail><level>2</level><type>产品</type><typeid>pkg.prod.least_qs</typeid>" +
                "<typename>[可选优惠包26]-湖北保底消费类</typename><startdate>2009-08-01 00:00:00</startdate><enddate>" +
                "</enddate></detail><detail><level>3</level><type>产品包</type><typeid>pkg.serv.fetion</typeid><typename>" +
                "飞信业务包</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>3.1</level>" +
                "<type>产品</type><typeid>FetionBase</typeid><typename>飞信基本业务</typename><startdate>2010-01-31 13:28:09</startdate>" +
                "<enddate></enddate></detail><detail><level>3.1.1</level><type>服务</type><typeid>FetionBase</typeid><typename>飞信基本业务" +
                "</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>4</level>" +
                "<type>产品包</type><typeid>pkg.prod.139mail</typeid><typename>139手机邮箱产品包</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>4.1</level><type>产品</type><typeid>139mail_mf</typeid><typename>139手机邮箱免费版</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>4.1.1</level><type>服务</type><typeid>139mail_mf</typeid><typename>139手机邮箱免费版</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>5</level><type>产品</type><typeid>SELFRING</typeid><typename>彩铃</typename><startdate>2010-06-11 09:47:40</startdate><enddate></enddate></detail><detail><level>5.1</level><type>服务</type><typeid>SELFRING</typeid><typename>彩铃</typename><startdate>2010-06-11 09:47:40</startdate><enddate></enddate></detail><detail><level>6</level><type>产品</type><typeid>pkg.prod.newbusi</typeid><typename>新业务类优惠包</typename><startdate>2010-07-01 00:00:00</startdate><enddate></enddate></detail><detail><level>7</level><type>产品</type><typeid>LDTX</typeid><typename>来电提醒</typename><startdate>2010-06-22 11:52:22</startdate><enddate></enddate></detail><detail><level>7.1</level><type>服务</type><typeid>LDTX</typeid><typename>来电提醒</typename><startdate>2010-06-22 11:52:22</startdate><enddate></enddate></detail><detail><level>8</level><type>产品</type><typeid>KJTX</typeid><typename>开机早知道</typename><startdate>2010-06-22 11:52:22</startdate><enddate></enddate></detail><detail><level>8.1</level><type>服务</type><typeid>KJTX</typeid><typename>开机早知道</typename><startdate>2010-06-22 11:52:22</startdate><enddate></enddate></detail><detail><level>9</level><type>产品</type><typeid>inc.grp.vpmn.mem.next</typeid><typename>成员可选套餐-下月生效(仅适用于当月转网用户)</typename><startdate>2011-04-01 00:00:00</startdate><enddate></enddate></detail><detail><level>9.1</level><type>优惠</type><typeid>SVN250035</typeid><typename>成员套餐37-本地5元套餐5</typename><startdate>2011-04-01 00:00:00</startdate><enddate></enddate></detail><detail><level>10</level><type>产品</type><typeid>pkg.gt.st</typeid><typename>[基本优惠包]:标准全球通</typename><startdate>2007-09-01 00:00:00</startdate><enddate></enddate></detail><detail><level>10.1</level><type>优惠</type><typeid>B210221</typeid><typename>湖北全球通88套餐88元G3升级版</typename><startdate>2010-02-01 00:00:00</startdate><enddate></enddate></detail><detail><level>11</level><type>产品包</type><typeid>pkg.prod.gprs</typeid><typename>GPRS业务功能</typename><startdate>2005-07-01 00:00:00</startdate><enddate></enddate></detail><detail><level>11.1</level><type>服务</type><typeid>150</typeid><typename>GPRS功能</typename><startdate>2005-04-26 17:58:42</startdate><enddate></enddate></detail><detail><level>12</level><type>产品包</type><typeid>pkg.serv.ct</typeid><typename>服务长途包</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>12.1</level><type>产品</type><typeid>109</typeid><typename>国际长途</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>12.1.1</level><type>服务</type><typeid>109</typeid><typename>国际长途</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>12.2</level><type>产品</type><typeid>901</typeid><typename>国内长途</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>12.2.1</level><type>服务</type><typeid>901</typeid><typename>国内长途</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>13</level><type>产品包</type><typeid>pkg.serv.my</typeid><typename>服务漫游包</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>13.1</level><type>产品</type><typeid>904</typeid><typename>国内漫游</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>13.1.1</level><type>服务</type><typeid>904</typeid><typename>国内漫游</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>14</level><type>产品</type><typeid>120</typeid><typename>呼叫转移</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>14.1</level><type>服务</type><typeid>120</typeid><typename>呼叫转移</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>15</level><type>产品</type><typeid>107</typeid><typename>三方通话</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>15.1</level><type>服务</type><typeid>107</typeid><typename>三方通话</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>16</level><type>产品</type><typeid>100</typeid><typename>来电显示</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>16.1</level><type>服务</type><typeid>100</typeid><typename>来电显示</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>17</level><type>产品</type><typeid>114</typeid><typename>短消息</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>17.1</level><type>服务</type><typeid>114</typeid><typename>短消息</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>18</level><type>产品</type><typeid>inc.grp.vpmn.mem.base</typeid><typename>成员VPMN功能</typename><startdate>2009-08-11 00:00:00</startdate><enddate></enddate></detail><detail><level>18.1</level><type>服务</type><typeid>841</typeid><typename>VPMN功能</typename><startdate>2009-08-11 00:00:00</startdate><enddate></enddate></detail></message_content>]]></message_body></message_response>";
        // 错误
        // String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
        // +
        // "<menuid>qryFavourable</menuid><process_code>cli_qry_zfinfo</process_code>" +
        // "<verify_code></verify_code>" +
        // "<resp_time>20110620144828</resp_time><" +
        // "sequence><req_seq>14</req_seq><operation_seq></operation_seq>" +
        // "</sequence><retinfo><rettype>0</rettype><retcode>999</retcode>" +
        // "<retmsg><![CDATA[BUSINESSID:QrySubsFeeInfo_Atsv,中间件调用失败，EUniCall抛出异常,ErrorID -1 ErrorMsg EXCEPTION
        // 'ETuxedo': Destination service not found: IntCmd:CscGetSetInfo, IntSubCmd:, IntID:bsacAtsv,
        // Region:711]]></retmsg></retinfo></message_head>" +
        // "</message_response>";
        
        ReturnWrap rtwp = null;
        try
        {
            rtwp = intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rtwp;
        
    }
    
    /**
     * 查询预约号码_湖北
     * 
     * @param map
     * @return
     */
    public ReturnWrap phoneNumQuery(Map map)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLCSQueryAvlTelNum")) 
            {
                String response = "{telInfoList:[{telNum:'13645319981',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319982',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319983',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319984',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319985',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319986',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319987',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319988',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319989',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319980',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319901',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319902',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319903',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319904',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319905',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319906',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319907',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319908',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319909',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319900',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319911',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319912',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319913',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319914',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319915',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319916',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319917',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319918',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319919',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319910',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319999',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319999',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319999',prepay:'1000',minimumCharge:'1223'}]}";
                
                return getIntMsgUtil().parseJsonResponse(response, null,null);
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<tellist><telnum>13645319981</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319982</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319983</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319984</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319985</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319986</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319987</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319988</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319989</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319980</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319901</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319902</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319903</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319904</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                 /*   + "<tellist><telnum>13645319905</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319906</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319907</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319908</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319909</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319900</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319911</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319912</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319913</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319914</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319915</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319916</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319917</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319918</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319919</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319910</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319999</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319999</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319999</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"*/
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 预约号码_湖北
     * 
     * @param map
     * @return
     */
    public ReturnWrap bespeakPhone(Map map)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("nkfSelNBR")) 
            {
                String response = "{oid:'10000000001',vidatetime:'19010101000000',remind:'手机号码已成功预约，请在48小时内到指定营业办理，带好身份证及有效证件，欢迎下次使用！湖北综合业务自助终端为你提供最真诚的服务！'}";
                
                return getIntMsgUtil().parseJsonResponse(response, null, null);
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryWlanMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921055850</resp_time><sequence><req_seq>21</req_seq>"
                    + "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
                    + "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
                    + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<oid>10000000001</oid>"
                    + "<vidatetime>1901-01-01 00:00:00</vidatetime>"
                    + "<remind>手机号码已成功预约，请在48小时内到指定营业办理，带好身份证及有效证件，欢迎下次使用！湖北综合业务自助终端为你提供最真诚的服务！</remind>"
                    + "</message_content>]]>" + "</message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 湖北查询网号
     * 
     * @param map
     * @return
     */
    public ReturnWrap netNbrQuery(Map map)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("atsvGetNetNbr")) 
            {
                String response = "{retDataList:[{resSegment:'135'},{resSegment:'136'},{resSegment:'137'},{resSegment:'138'},{resSegment:'139'},{resSegment:'135'},{resSegment:'136'}"
                    + ",{resSegment:'137'},{resSegment:'138'},{resSegment:'139'},{resSegment:'135'},{resSegment:'136'},{resSegment:'137'},{resSegment:'138'},{resSegment:'139'}"
                    + ",{resSegment:'135'},{resSegment:'136'},{resSegment:'137'},{resSegment:'138'},{resSegment:'139'},{resSegment:'135'},{resSegment:'136'},{resSegment:'137'}"
                    + ",{resSegment:'138'},{resSegment:'139'},{resSegment:'135'},{resSegment:'136'},{resSegment:'137'},{resSegment:'138'},{resSegment:'139'},{resSegment:'135'}"
                    + ",{resSegment:'136'},{resSegment:'137'},{resSegment:'138'},{resSegment:'139'}]}";
                
                return getIntMsgUtil().parseJsonResponse(response, null, null);
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<tellist><netnum>135</netnum></tellist>"
                    + "<tellist><netnum>136</netnum></tellist>"
                    + "<tellist><netnum>137</netnum></tellist>"
                    + "<tellist><netnum>138</netnum></tellist>"
                    + "<tellist><netnum>139</netnum></tellist>"
                    + "<tellist><netnum>135</netnum></tellist>"
                    + "<tellist><netnum>136</netnum></tellist>"
                    + "<tellist><netnum>137</netnum></tellist>"
                    + "<tellist><netnum>138</netnum></tellist>"
                    + "<tellist><netnum>139</netnum></tellist>"
                    + "<tellist><netnum>135</netnum></tellist>"
                    + "<tellist><netnum>136</netnum></tellist>"
                    + "<tellist><netnum>137</netnum></tellist>"
                    + "<tellist><netnum>138</netnum></tellist>"
                    + "<tellist><netnum>139</netnum></tellist>"
                    + "<tellist><netnum>135</netnum></tellist>"
                    + "<tellist><netnum>136</netnum></tellist>"
                    + "<tellist><netnum>137</netnum></tellist>"
                    + "<tellist><netnum>138</netnum></tellist>"
                    + "<tellist><netnum>139</netnum></tellist>"
                    + "<tellist><netnum>135</netnum></tellist>"
                    + "<tellist><netnum>136</netnum></tellist>"
                    + "<tellist><netnum>137</netnum></tellist>"
                    + "<tellist><netnum>138</netnum></tellist>"
                    + "<tellist><netnum>139</netnum></tellist>"
                    + "<tellist><netnum>135</netnum></tellist>"
                    + "<tellist><netnum>136</netnum></tellist>"
                    + "<tellist><netnum>137</netnum></tellist>"
                    + "<tellist><netnum>138</netnum></tellist>"
                    + "<tellist><netnum>139</netnum></tellist>"
                    + "<tellist><netnum>135</netnum></tellist>"
                    + "<tellist><netnum>136</netnum></tellist>"
                    + "<tellist><netnum>137</netnum></tellist>"
                    + "<tellist><netnum>138</netnum></tellist>"
                    + "<tellist><netnum>139</netnum></tellist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 湖北查询网段
     * 
     * @param map
     * @return
     */
    public ReturnWrap netValueQuery(Map map)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("atsvGetTelSection")) 
            {
                String response = "{retDataList:[{resSegment:'0531'},{resSegment:'0532'},{resSegment:'0533'},{resSegment:'0534'},{resSegment:'0535'},{resSegment:'0536'},{resSegment:'0537'}"
                    + ",{resSegment:'0538'},{resSegment:'0539'},{resSegment:'0530'},{resSegment:'1531'},{resSegment:'2532'},{resSegment:'3533'},{resSegment:'4534'},{resSegment:'5535'}"
                    + ",{resSegment:'6536'},{resSegment:'7537'},{resSegment:'8538'},{resSegment:'9539'},{resSegment:'0531'},{resSegment:'0532'},{resSegment:'0533'},{resSegment:'0534'}"
                    + ",{resSegment:'0535'},{resSegment:'0536'},{resSegment:'0537'},{resSegment:'0538'},{resSegment:'0539'},{resSegment:'0530'},{resSegment:'1531'},{resSegment:'2532'}"
                    + ",{resSegment:'3533'},{resSegment:'4534'},{resSegment:'5535'},{resSegment:'6536'},{resSegment:'7537'},{resSegment:'8538'},{resSegment:'9539'},{resSegment:'0531'}"
                    + ",{resSegment:'0532'},{resSegment:'0533'},{resSegment:'0534'},{resSegment:'0535'},{resSegment:'0536'},{resSegment:'0537'},{resSegment:'0538'},{resSegment:'0539'}"
                    + ",{resSegment:'0530'},{resSegment:'1531'},{resSegment:'2532'},{resSegment:'3533'},{resSegment:'4534'},{resSegment:'5535'},{resSegment:'6536'},{resSegment:'7537'}"
                    + ",{resSegment:'8538'},{resSegment:'9539'}]}";
                
                return getIntMsgUtil().parseJsonResponse(response, null, null);
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<tellist><telsection>0531</telsection></tellist>"
                    + "<tellist><telsection>0532</telsection></tellist>"
                    + "<tellist><telsection>0533</telsection></tellist>"
                    + "<tellist><telsection>0534</telsection></tellist>"
                    + "<tellist><telsection>0535</telsection></tellist>"
                    + "<tellist><telsection>0536</telsection></tellist>"
                    + "<tellist><telsection>0537</telsection></tellist>"
                    + "<tellist><telsection>0538</telsection></tellist>"
                    + "<tellist><telsection>0539</telsection></tellist>"
                    + "<tellist><telsection>0530</telsection></tellist>"
                    + "<tellist><telsection>1531</telsection></tellist>"
                    + "<tellist><telsection>2532</telsection></tellist>"
                    + "<tellist><telsection>3533</telsection></tellist>"
                    + "<tellist><telsection>4534</telsection></tellist>"
                    + "<tellist><telsection>5535</telsection></tellist>"
                    + "<tellist><telsection>6536</telsection></tellist>"
                    + "<tellist><telsection>7537</telsection></tellist>"
                    + "<tellist><telsection>8538</telsection></tellist>"
                    + "<tellist><telsection>9539</telsection></tellist>"
                    + "<tellist><telsection>0531</telsection></tellist>"
                    + "<tellist><telsection>0532</telsection></tellist>"
                    + "<tellist><telsection>0533</telsection></tellist>"
                    + "<tellist><telsection>0534</telsection></tellist>"
                    + "<tellist><telsection>0535</telsection></tellist>"
                    + "<tellist><telsection>0536</telsection></tellist>"
                    + "<tellist><telsection>0537</telsection></tellist>"
                    + "<tellist><telsection>0538</telsection></tellist>"
                    + "<tellist><telsection>0539</telsection></tellist>"
                    + "<tellist><telsection>0530</telsection></tellist>"
                    + "<tellist><telsection>1531</telsection></tellist>"
                    + "<tellist><telsection>2532</telsection></tellist>"
                    + "<tellist><telsection>3533</telsection></tellist>"
                    + "<tellist><telsection>4534</telsection></tellist>"
                    + "<tellist><telsection>5535</telsection></tellist>"
                    + "<tellist><telsection>6536</telsection></tellist>"
                    + "<tellist><telsection>7537</telsection></tellist>"
                    + "<tellist><telsection>8538</telsection></tellist>"
                    + "<tellist><telsection>9539</telsection></tellist>"
                    + "<tellist><telsection>0531</telsection></tellist>"
                    + "<tellist><telsection>0532</telsection></tellist>"
                    + "<tellist><telsection>0533</telsection></tellist>"
                    + "<tellist><telsection>0534</telsection></tellist>"
                    + "<tellist><telsection>0535</telsection></tellist>"
                    + "<tellist><telsection>0536</telsection></tellist>"
                    + "<tellist><telsection>0537</telsection></tellist>"
                    + "<tellist><telsection>0538</telsection></tellist>"
                    + "<tellist><telsection>0539</telsection></tellist>"
                    + "<tellist><telsection>0530</telsection></tellist>"
                    + "<tellist><telsection>1531</telsection></tellist>"
                    + "<tellist><telsection>2532</telsection></tellist>"
                    + "<tellist><telsection>3533</telsection></tellist>"
                    + "<tellist><telsection>4534</telsection></tellist>"
                    + "<tellist><telsection>5535</telsection></tellist>"
                    + "<tellist><telsection>6536</telsection></tellist>"
                    + "<tellist><telsection>7537</telsection></tellist>"
                    + "<tellist><telsection>8538</telsection></tellist>"
                    + "<tellist><telsection>9539</telsection></tellist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 身份证入网预约_湖北
     * 
     * @param map
     * @return
     */
    public ReturnWrap idCardBook(Map map)
    {
        try
        {
            if(IntMsgUtil.isTransEBUS("CTCSInstallByIdCard"))
            {
                ReturnWrap rtwp = new ReturnWrap();
                rtwp.setStatus(1);
                rtwp.setReturnMsg("预约成功");
                return rtwp;
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryWlanMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921055850</resp_time><sequence><req_seq>21</req_seq>"
                    + "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
                    + "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
                    + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<oid>10000000001</oid>"
                    + "<vidatetime>1901-01-01 00:00:00</vidatetime>"
                    + "<remind></remind>"
                    + "</message_content>]]>" + "</message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 湖北优惠业务查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryPrivInfo(Map map)
    {
        
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_promotions_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20110601001932</resp_time><sequence><req_seq>35</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>G1119</privid><privname>商旅保底消费B类388元</privname><ncode>G1111</ncode><prepayfee>1000</prepayfee><groupid>123</groupid><groupname>1234</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>G1116</privid><privname>商旅保底消费B类88元</privname><ncode>G1111</ncode><prepayfee>1000</prepayfee><groupid>123</groupid><groupname>1234</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>G1115</privid><privname>商旅保底消费A类788元</privname><ncode>G1111</ncode><prepayfee>1000</prepayfee><groupid>123</groupid><groupname>1234</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>G1114</privid><privname>商旅保底消费A类588元</privname><ncode>G1111</ncode><prepayfee>1000</prepayfee><groupid>123</groupid><groupname>1234</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>G1113</privid><privname>商旅保底消费A类388元</privname><ncode>G1111</ncode><prepayfee>1000</prepayfee><groupid>123</groupid><groupname>1234</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>G220012H</privid><privname>湖北保底300元18个月</privname><ncode>G220012H</ncode><prepayfee>1000</prepayfee><groupid>123</groupid><groupname>1234</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>220000H</privid><privname>湖北保底10元2个月</privname><ncode>220000H</ncode><prepayfee>1000</prepayfee><groupid>223</groupid><groupname>湖北保底10元2个月</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>CMMB_1</privid><privname>手机电视包月套餐</privname><ncode>CMMB_MON</ncode><prepayfee>600</prepayfee><groupid>223</groupid><groupname>123411</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>G220220</privid><privname>GPRS5元新</privname><ncode>G220220</ncode><prepayfee>0</prepayfee><groupid>123</groupid><groupname>1234</groupname></reclist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    public ReturnWrap privAcceptCommit(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>recPrivAccept</menuid><process_code>cli_qry_promotions_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921014415</resp_time><sequence><req_seq>35</req_seq><operation_seq></operation_seq></sequence>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                    + "</retmsg></retinfo></message_head></message_response>";
            
            return intMsgUtil.parseResponse(response);
            
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 湖北账单分析查询
     */
    public ReturnWrap qryBillAanlysis(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_billanalysis_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20110603191908</resp_time><sequence><req_seq>52</req_seq><operation_seq></operation_seq></sequence>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<restatus>0</restatus>"
                    + "<billAnalysisInfo>#*#月基本费#*#:10%;#*#通信费#*#:20%;#*#其中:#*#:;本地通话费:30%;漫游通话费:20%;国内长途费:10%;国际/港澳台长途费:5%;国际/港澳台漫游费:5%;IP通信费:6%;短信通信费:30%;彩信通信费:1%;无线上网费:30%;#*#增值业务费#*#:10%;#*#代收费#*#:10%;#*#其他费#*#:20%;</billAnalysisInfo>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
            
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 湖北资费推荐
     */
    public ReturnWrap qryChargeGuide(Map map)
    {
        try
        {
            // 用户地区
            String region = (String)map.get("region");
            // 流水编号
            String streamNo = (String)map.get("streamno");
            // 问题编号
            String questionCode = (String)map.get("questioncode");
            // 答案编号
            String answerCode = (String)map.get("answercode");
            
            String question = "";
            String answer = "";
            String recinfo = "";
            if (region.length() > 0)
            {
                if (streamNo.length() == 0 && questionCode.length() == 0 && answerCode.length() == 0)
                {
                    streamNo = "20011957982339";
                    question = "1";
                    answer = "是否接受不能带号转的办理限制条件？";
                    recinfo = "1-1-1~不能~1-1-2~能";
                }
                
                if (streamNo.equals("20011957982339"))
                {
                    if (questionCode.equals("1") && answerCode.equals("1-1-2"))
                    {
                        streamNo = "20011957982339";
                        question = "3";
                        answer = "您更愿意选择无月租的资费，还是有月租的资费？";
                        recinfo = "2-2-1~无月租~2-2-2~有月租";
                    }
                    if (questionCode.equals("3") && answerCode.equals("2-2-1"))
                    {
                        streamNo = "20011957982339";
                        question = "11";
                        answer = "您每月用于通信的消费预算是多少？";
                        recinfo = "4-6-1~50元以下~4-6-2~50元-100元~4-6-3~100元-150元~4-6-4~150元-250元~4-6-5~250元-400元~4-6-6~400元-600元~4-6-7~600元-800元~4-6-8~800元以上";
                    }
                    if (questionCode.equals("11") && answerCode.equals("4-6-1"))
                    {
                        streamNo = "20011957982339";
                        question = "14";
                        answer = "你是否有黄石黄冈鄂州三地漫游需求？";
                        recinfo = "5-29-1~没有~5-29-2~有";
                    }
                    if (questionCode.equals("14") && answerCode.equals("5-29-1"))
                    {
                        question = "";
                        answer = "";
                        streamNo = "20011957982339";
                        recinfo = "EZPP24~鄂州神州行大众卡单向版10元~    月租10元/月，（可选）来电显示5元/月,被叫免费，送主叫40分钟，超出忙时(8-21)主叫0.25元/分,闲时(21-8)主叫0.2元/分。~~~";
                    }
                }
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_chargeguide_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20110603042314</resp_time><sequence><req_seq>52</req_seq><operation_seq></operation_seq></sequence>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<streamno>"
                    + streamNo
                    + "</streamno><questioncode>"
                    + question
                    + "</questioncode>"
                    + "<question>"
                    + answer
                    + "</question><recinfo>"
                    + recinfo
                    + "</recinfo>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
            
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 产品变更，查询用户可转换套餐清单
     */
    public ReturnWrap qryChangeList(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_package_changelist_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20110623115115</resp_time><sequence><req_seq>52</req_seq><operation_seq></operation_seq></sequence>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<sprid>B2135</sprid><sprname>湖北全球通88套餐88元</sprname>"
                    + "<changelist><tprid>B210221</tprid><tprname>湖北全球通88套餐88元G3升级版</tprname></changelist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
            
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 产品变更，获取优惠/服务/产品变更清单
     */
    public ReturnWrap qryChgContent(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_package_chgcontent_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20110603191908</resp_time><sequence><req_seq>52</req_seq><operation_seq></operation_seq></sequence>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<sn>2011062100040023</sn>"
                    + "<sbrandname>1234</sbrandname>"
                    + "<tbrandname>4567</tbrandname>"
                    + "<chgcontent><optype>1</optype><prtype>PRIVILEGE</prtype><prid>B2135</prid><prname>湖北全球通88套餐88元</prname></chgcontent>"
                    + "<chgcontent><optype>0</optype><prtype>PRIVILEGE</prtype><prid>LDTX</prid><prname>来电提醒</prname></chgcontent>"
                    + "<chgcontent><optype>0</optype><prtype>PRODUCT</prtype><prid>656</prid><prname>656</prname></chgcontent>"
                    + "<chgcontent><optype>0</optype><prtype>PRIVILEGE</prtype><prid>B210221</prid><prname>湖北全球通88套餐88元G3升级版</prname></chgcontent>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
            
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 产品变更，获取优惠/服务/产品变更清单
     */
    public ReturnWrap prodChgCommit(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_package_chgcommit_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20110603191908</resp_time><sequence><req_seq>52</req_seq><operation_seq></operation_seq></sequence>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<commitlist><optype>1</optype><prtype>PRIVILEGE</prtype><prid>B2135</prid><prname>湖北全球通88套餐88元</prname></commitlist>"
                    + "<commitlist><optype>0</optype><prtype>PRIVILEGE</prtype><prid>LDTX</prid><prname>来电提醒</prname></commitlist>"
                    + "<commitlist><optype>0</optype><prtype>PRODUCT</prtype><prid>656</prid><prname>656</prname></commitlist>"
                    + "<commitlist><optype>0</optype><prtype>PRIVILEGE</prtype><prid>B210221</prid><prname>湖北全球通88套餐88元G3升级版</prname></commitlist>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
            
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 业务查询统一接口 梦网业务查询
     */
    public ReturnWrap queryService(MsgHeaderPO msgHead)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>recQrySPinfo</menuid><process_code>cli_qry_spinfo</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921002353</resp_time><sequence><req_seq>50</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request "
                    + "succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    + "<message_content><cusermoninfodt><usermoninfo_deal_type>22</usermoninfo_deal_type>"
                    + "<usermoninfo_spid>000001</usermoninfo_spid><usermoninfo_spname>中国移动</usermoninfo_spname>"
                    + "<usermoninfo_spbizid>FetionBase</usermoninfo_spbizid><usermoninfo_spbizname>飞信功能</usermoninfo_spbizname>"
                    + "<usermoninfo_spbiztype></usermoninfo_spbiztype><usermoninfo_pricetype></usermoninfo_pricetype>"
                    + "<usermoninfo_price></usermoninfo_price><usermoninfo_domain></usermoninfo_domain><usermoninfo_start_time>"
                    + "2010-06-08 08:22:18</usermoninfo_start_time><usermoninfo_end_time /><usermoninfo_seq />"
                    + "<usermoninfo_replace_charge></usermoninfo_replace_charge><usermoninfo_pricedesc>免费</usermoninfo_pricedesc>"
                    + "</cusermoninfodt><cusermoninfodt><usermoninfo_deal_type>21</usermoninfo_deal_type>"
                    + "<usermoninfo_spid>801174</usermoninfo_spid><usermoninfo_spname>中国移动</usermoninfo_spname><usermoninfo_spbizid>"
                    + "125820</usermoninfo_spbizid><usermoninfo_spbizname>生活播报</usermoninfo_spbizname><usermoninfo_spbiztype>52"
                    + "</usermoninfo_spbiztype><usermoninfo_pricetype>免费</usermoninfo_pricetype><usermoninfo_price>0"
                    + "</usermoninfo_price><usermoninfo_domain>12580</usermoninfo_domain><usermoninfo_start_time>2010-09-20 13:52:13"
                    + "</usermoninfo_start_time><usermoninfo_end_time /><usermoninfo_seq /><usermoninfo_replace_charge>"
                    + "</usermoninfo_replace_charge><usermoninfo_pricedesc>0.00免费</usermoninfo_pricedesc></cusermoninfodt>"
                    + "<cusermoninfodt><usermoninfo_deal_type>22</usermoninfo_deal_type><usermoninfo_spid>000001</usermoninfo_spid>"
                    + "<usermoninfo_spname>中国移动</usermoninfo_spname><usermoninfo_spbizid>MMAIL_F</usermoninfo_spbizid>"
                    + "<usermoninfo_spbizname>139邮箱免费版</usermoninfo_spbizname><usermoninfo_spbiztype></usermoninfo_spbiztype>"
                    + "<usermoninfo_pricetype></usermoninfo_pricetype><usermoninfo_price></usermoninfo_price><usermoninfo_domain>"
                    + "</usermoninfo_domain><usermoninfo_start_time>2010-09-14 17:46:47</usermoninfo_start_time>"
                    + "<usermoninfo_end_time /><usermoninfo_seq /><usermoninfo_replace_charge></usermoninfo_replace_charge>"
                    + "<usermoninfo_pricedesc>免费</usermoninfo_pricedesc></cusermoninfodt></message_content>]]></message_body>"
                    + "</message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
        
    }
    
    /**
     * 账单寄送方式提交
     * @param msgHead 消息头
     * @param billSendType 寄送方式
     * @param mailAddr 邮寄地址
     * @return
     * @remark modify by sWX219697 2014-9-9 09:36:24 OR_huawei_201409_430 自助终端接入EBUS_账单寄送
     */
    public ReturnWrap billSendCommit(MsgHeaderPO msgHead, String billSendType, String mailAddr)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_busi_chargefee</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            return rtwp;
        }
    }
    
    public IntMsgUtil getIntMsgUtil()
    {
        return intMsgUtil;
    }
    
    public void setIntMsgUtil(IntMsgUtil intMsgUtil)
    {
        this.intMsgUtil = intMsgUtil;
    }
    
    public ReturnWrap getProdTmpList(Map inMap)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ReturnWrap prodChgCheck(Map inMap)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ReturnWrap getDictItemByGroup(Map map)
    {
        try
        {
              // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("PTPUBQryDictItemsByGrpID"))
            {
                if("City420000".equals(map.get("groupid"))){
                    String response = "[{\"description\":\"270\",\"dictID\":\"420100\",\"dictName\":\"武汉市\",\"groupID\":\"City420000\",\"sortOrder\":1},{\"description\":\"714\",\"dictID\":\"420200\",\"dictName\":\"黄石市\",\"groupID\":\"City420000\",\"sortOrder\":2},{\"description\":\"719\",\"dictID\":\"420300\",\"dictName\":\"十堰市\",\"groupID\":\"City420000\",\"sortOrder\":3},{\"description\":\"717\",\"dictID\":\"420500\",\"dictName\":\"宜昌市\",\"groupID\":\"City420000\",\"sortOrder\":4},{\"description\":\"710\",\"dictID\":\"420600\",\"dictName\":\"襄阳市\",\"groupID\":\"City420000\",\"sortOrder\":5},{\"description\":\"711\",\"dictID\":\"420700\",\"dictName\":\"鄂州市\",\"groupID\":\"City420000\",\"sortOrder\":6},{\"description\":\"724\",\"dictID\":\"420800\",\"dictName\":\"荆门市\",\"groupID\":\"City420000\",\"sortOrder\":7},{\"description\":\"712\",\"dictID\":\"420900\",\"dictName\":\"孝感市\",\"groupID\":\"City420000\",\"sortOrder\":8},{\"description\":\"716\",\"dictID\":\"421000\",\"dictName\":\"荆州市\",\"groupID\":\"City420000\",\"sortOrder\":9},{\"description\":\"713\",\"dictID\":\"421100\",\"dictName\":\"黄冈市\",\"groupID\":\"City420000\",\"sortOrder\":10},{\"description\":\"715\",\"dictID\":\"421200\",\"dictName\":\"咸宁市\",\"groupID\":\"City420000\",\"sortOrder\":11},{\"description\":\"722\",\"dictID\":\"421300\",\"dictName\":\"随州市\",\"groupID\":\"City420000\",\"sortOrder\":12},{\"description\":\"718\",\"dictID\":\"422800\",\"dictName\":\"恩施土家族苗族自治州\",\"groupID\":\"City420000\",\"sortOrder\":13},{\"description\":\"HB.JH\",\"dictID\":\"429004\",\"dictName\":\"仙桃市\",\"groupID\":\"City420000\",\"sortOrder\":14},{\"description\":\"HB.QJ\",\"dictID\":\"429005\",\"dictName\":\"潜江市\",\"groupID\":\"City420000\",\"sortOrder\":15},{\"description\":\"HB.TM\",\"dictID\":\"429006\",\"dictName\":\"天门市\",\"groupID\":\"City420000\",\"sortOrder\":16},{\"description\":\"shen nong jia lin qu\",\"dictID\":\"429021\",\"dictName\":\"神农架林区\",\"groupID\":\"City420000\",\"sortOrder\":17}]";
                    return intMsgUtil.parseJsonResponse(response, null, new String[]{"dictID","dictName","description"});
                }
                
                if("District420700".equals(map.get("groupid"))){
                    String response = "[{\"description\":\"liang zi hu qu\",\"dictID\":\"420702\",\"dictName\":\"梁子湖区\",\"groupID\":\"District420700\",\"sortOrder\":0},{\"description\":\"hua rong qu\",\"dictID\":\"420703\",\"dictName\":\"华容区\",\"groupID\":\"District420700\",\"sortOrder\":0},{\"description\":\"e cheng qu\",\"dictID\":\"420704\",\"dictName\":\"鄂城区\",\"groupID\":\"District420700\",\"sortOrder\":0},{\"description\":\"qi ta qu\",\"dictID\":\"420705\",\"dictName\":\"其它区\",\"groupID\":\"District420700\",\"sortOrder\":0}]";
                    return intMsgUtil.parseJsonResponse(response, null, new String[]{"dictID","dictName","description"});
                }
                
                String response = "[{'dictID':'30','dictName':'30元'},{'dictID':'50','dictName':'50元'}]";
                
                return intMsgUtil.parseJsonResponse(response, null, new String[]{"dictID","dictName"});
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<list><dictId>420700</dictId><dictName>鄂州市</dictName></list>"
                    + "<list><dictId>RwdGift_Fee</dictId><dictName>话费</dictName></list>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    public ReturnWrap qryMonthlyReturnInfo(Map map)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    // add begin l00190940 2011-12-7 OR_HUB_201112_16
    /** 
     * 查询亲情号码
     * 
     * @param msgHeader 报文头信息
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public ReturnWrap qryFamilyNumber(MsgHeaderPO msgHeader)
    {
        try
        {
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("CTCSQryDealDealTelNum"))
            {
                CTagSet cTagSet = new CTagSet();
                
                // 循环将空亲情号码放入CTagSet中
                for (int i = 0; i < 3; i++)
                {
                    cTagSet.SetValue("friendnum" + (i + 1), "");
                }
                
                String response = "[{'attrID':'FriendNum1','attrValue':'15168866090'},{'attrID':'FriendNum2','attrValue':''},{'attrID':'FriendNum3','attrValue':'15168866091'}]";
                
                ReturnWrap rtwp = intMsgUtil.parseJsonResponse(response, null, new String[]{"attrID", "attrValue"});
                
                // 取得所有亲情号码
                CRSet crSet = (CRSet) rtwp.getReturnObject();
                
                if (crSet != null)
                {
                    // 取前三个亲情号码
                    String[] arrays = new String[]{"FriendNum1", "FriendNum2", "FriendNum3"};
                    
                    // 循环将前三个亲情号码放入CTagSet中
                    for (int i = 0; i < crSet.GetRowCount(); i++)
                    {
                        if (ArrayUtils.contains(arrays, crSet.GetValue(i, 0)))
                        {
                            cTagSet.SetValue(crSet.GetValue(i, 0).toLowerCase(Locale.ENGLISH), crSet.GetValue(i, 1));
                        }
                    }
                }
                
                rtwp.setReturnObject(cTagSet);
                
                return rtwp;
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryWlanMuster</menuid><process_code>cli_qry_szxqskqqh</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921055850</resp_time><sequence><req_seq>21</req_seq>"
                    + "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
                    + "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
                    + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<friendnum1>15168866090</friendnum1>"
                    + "<friendnum2></friendnum2>"
                    + "<friendnum3>15168866091</friendnum3>"
                    + "</message_content>]]>"
                    + "</message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 设置、修改或取消亲情号码
     */
    public ReturnWrap setFamilyNumber(MsgHeaderPO msgHeader, String sn, String sregion, String style)
    {
    	// 湖北转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("IncProductSrv2"))
        {
            String response = "{'ACCESSTYPE':'INNER','ADDNCODE':'FriendNew','ADD_ENDDATE':'','ADD_STARTDATE':'2014-12-15 18:57:00'," 
            	+ "'BOSSCODE':'FriendNew','BRAND':'BrandSzx','BUSITYPE':'0','CALL_NUMBER':'13971984332','CURID':'','CURNCODE':''," 
            	+ "'DELNCODE':'','DEL_ENDDATE':'','EFFTYPE':'Type_Default','EFF_DATE':'2014-12-15 18:57:00','END_DATE':'ZERO'," 
            	+ "'ERRNO':'0','EXECUTECMD':'TRUE','FORMNUMBER':'711141215229853868','FRIEND':'13908689393','FriendNum2':'13908689393'," 
            	+ "'HASPARAM':'2','IMSI':'460001984733294','INPARAMFORMAT':'','INPARAMSPLIT':'~','IPADDRESS':'','ISNEEDTHIRDCONF':'0'," 
            	+ "'ISWRITEFILELOG':'0','ISWRITETABLOG':'1','JOBDESC':'亲情号码2009','JOBNAME':'亲情号码2009','LINKNODE':'','LINKTYPE':''," 
            	+ "'MAINPRODID':'MP9990103000138','MSISDN':'13971984332','NCODE':'FriendNew','NEXTOUTPARAM':'FriendNum1~FriendNum2~FriendNum3'," 
            	+ "'NEXTOUTPARAMALL':'','NEXTOUTPARAMNOREP':'','NOPENEDPMP':'ZERO','OLD_PASSWD':'','OPENEDPMP':'ZERO','ORDERRESULT':'9900'," 
            	+ "'ORI_NEXTATTRS':'','OUTOPERID':'','OUTPARAMFORMAT':'#FriendNum1~#FriendNum2~#FriendNum3','OUTPARAMSPLIT':''," 
            	+ "'PARM':'','REALOPERID':'','REALRETCODE':'100','REGION':'711','RETCODE':'0','RETCONVERT':'1','RETMSG':'受理亲情号码2009成功了'," 
            	+ "'RETURN':'0','SENDORNOT':'0','SN':'2','STEP':'10','STYPE':'ADD','SUBORDERRESULT':'','SUBSCREATEDATE':'2001-01-18 10:01:10'," 
            	+ "'SUBSID':'7110000002387','TELNUM':'13971984332','TEMPLATENO':'P00119','TEMPLATEPARA':'1$亲情号码2009&5$13908689393&3$2&2$2014-12-15生效,&4$暂无'," 
            	+ "'UNITID':'INNER','VNCODE':'vFrenw','YYWWFORMNUM':'20089229853866','accessType':'bsacAtsv','formnum':'','interfaceID':'IncProductSrv2'," 
            	+ "'menuID':'recFamilyNumber','operatorID':'101','region':'','reqSeq':'1','reqTime':'20141215190602','routeType':'1','telNum':'13971984332'," 
            	+ "'uniContact':'','uniContactFlag':'','unitID':'HUAWEI','virtualActorFlag':'1'}";
            
            return getIntMsgUtil().parseJsonResponse(response, null, null);
        }
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid></menuid><process_code>cli_busi_szxqskqqh</process_code><verify_code></verify_code><resp_time>20100921002348</resp_time><sequence><req_seq>49</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
        ReturnWrap rtwp = null;
        try
        {
            rtwp = intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rtwp;
    }
    
    // add end l00190940 2011-12-7 OR_HUB_201112_16
    
    /**
     * 查询用户已存在的档次_湖北
     * 
     * @param map
     * @return
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public ReturnWrap qrySubsDangcisList(Map map)
    {
        try
        {
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLCSQrySubsValidPrivList"))
            {
                String response = "[{'privID':'dangci001','privName':'档次名称001'}]";
                
                return getIntMsgUtil().parseJsonResponse(response, null, new String[]{"privID","privName"});
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<list><privid>dangci001</privid><privname>档次名称001</privname></list>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 查询档次下的描述_湖北 <功能详细描述>
     * 
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryDangciDesc(Map paramMap)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<list><dangciName>dangciName</dangciName><dangciDesc>2011年1月7日起，" +
                            "北京移动全球通客户可通过网上商城或指定营业厅参加全球通预存话费换手机活动。" +
                            "只需预存相应话费（或交押金），并且承诺消费一定金额，就能以超值价格换购G3靓机，" +
                            "最低可0元购机。畅享移动新生活的同时，感受话费换新机的非凡体验。多款机型任你选择，" +
                            "快来参加吧！ <br/> <br/> 适用客户：北京移动全球通客户 <br/><br/>" +
                            " 活动方案：特别提示 <br/><br/> 注：办理本方案客户，需开通手机阅读新书速递包业务，" +
                            "手机阅读新书速递包业务信息费5元/月。业务开通期间，系统每月采用直接减免的方式为其返还5元“" +
                            "新书速递包”业务的月功能费，但是客户使用该业务产生的其他费用将自行支付。" +
                            " <br/><br/> 办理黑莓（BlackBerry）手机8310、8910全球通预存换机方案时，" +
                            "要求客户先开通“中国移动黑莓个人邮箱业务或BlackBerry业务（BES）”后，" +
                            "才能办理换机方案，否则将不能办理此两款机型的换机业务。<br/><br/>" +
                            "黑莓（BlackBerry）换机方案，只能在指定厅台办理，网站不能办理此方案。" +
                            "<br/><br/>承诺期12个月方案</dangciDesc></list>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 查询奖品列表_湖北
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryRewardList(Map map)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("PTPCEIGetBatchRecPresentsByActId")) 
            {
                String response = "[{'rewardID':'rewardid001','rewardName':'rewardname001','rewardType':'RwdGift_Prod','rewardValue':'570','scoreDealType':'0','useScore':'50','rewardInfo':'500元话费，5个月内消费'},"
                    + "{'rewardID':'rewardid001','rewardName':'rewardname001','rewardType':'RwdGift_Prod','rewardValue':'570','scoreDealType':'0','useScore':'50','rewardInfo':'500元话费，5个月内消费'},"
                    + "{'rewardID':'rewardid001','rewardName':'rewardname001','rewardType':'RwdGift_Prod','rewardValue':'570','scoreDealType':'0','useScore':'50','rewardInfo':'500元话费，5个月内消费'},"
                    + "{'rewardID':'rewardid001','rewardName':'rewardname001','rewardType':'RwdGift_Prod','rewardValue':'570','scoreDealType':'0','useScore':'50','rewardInfo':'500元话费，5个月内消费'},"
                    + "{'rewardID':'rewardid001','rewardName':'rewardname001','rewardType':'RwdGift_Prod','rewardValue':'570','scoreDealType':'0','useScore':'50','rewardInfo':'500元话费，5个月内消费'},"
                    + "{'rewardID':'rewardid001','rewardName':'rewardname001','rewardType':'RwdGift_Prod','rewardValue':'570','scoreDealType':'0','useScore':'50','rewardInfo':'500元话费，5个月内消费'},"
                    + "{'rewardID':'rewardid001','rewardName':'rewardname001','rewardType':'RwdGift_Prod','rewardValue':'570','scoreDealType':'0','useScore':'50','rewardInfo':'500元话费，5个月内消费'}]";
                
                return getIntMsgUtil().parseJsonResponse(response, null, null);
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_rewardlist</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Prod</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>0</scoredealtype><userscore>50</userscore><rewardnote>500元话费，5个月内消费</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Fee</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>0</scoredealtype><userscore>50</userscore><rewardnote>500元话费，5个月内消费</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Prod</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>0</scoredealtype><userscore>50</userscore><rewardnote>500元话费，5个月内消费</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Fee</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>0</scoredealtype><userscore>50</userscore><rewardnote>500元话费，5个月内消费</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Prod</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>0</scoredealtype><userscore>50</userscore><rewardnote>500元话费，5个月内消费</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Fee</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>0</scoredealtype><userscore>50</userscore><rewardnote>500元话费，5个月内消费</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Prod</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>0</scoredealtype><userscore>50</userscore><rewardnote>500元话费，5个月内消费</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Fee</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>1</scoredealtype><userscore>50</userscore><rewardnote>500元话费，5个月内消费</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Prod</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>1</scoredealtype><userscore>50</userscore><rewardnote>500元话费，5个月内消费</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Fee</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>1</scoredealtype><userscore>50</userscore><rewardnote>500元话费，5个月内消费</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Prod</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>1</scoredealtype><userscore>50</userscore><rewardnote>500元话费，5个月内消费</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Fee</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>1</scoredealtype><userscore>50</userscore><rewardnote>500元话费，5个月内消费</rewardnote></rewardlist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 查询奖品列表_湖北
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryPrivFee(Map map)
    {
        try
        {
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLCSGetSubsRecFeeAndPreFee"))
            {
                String response = "[{'recFee':'100','preFee':'20'}]";
                
                return intMsgUtil.parseJsonResponse(response, null, new String[]{"recFee","preFee"});
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<privfeelist><yxfaFee>100</yxfaFee><ycFee>20</ycFee></privfeelist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 促销活动受理_湖北 <功能详细描述>
     * 
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap recRewardCommit(Map paramMap)
    {
        try
        {
            // 湖北EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLCSRecRewardactiveLight"))
            {
                String response = "{'recOid':'100000001'}";
                
                return getIntMsgUtil().parseJsonResponse(response, new String[][]{{"recOid"},{"recoid"}}, null);
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryBill</menuid><process_code>cli_qry_balance</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<recoid>100000001</recoid>" + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return new ReturnWrap();
        }
    }
    
    @Override
    public ReturnWrap qryBillCustInfo(Map map)
    {
        // TODO Auto-generated method stub
        String servnum = (String)map.get("SERVNUM");
        String cyclemonth = (String)map.get("CYCLEMONTH");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // ---湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("CTCSQryBillCycleCustInfo"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // 电话号码
                inParamMap.put("telNum", servnum);
                
                // 月份
                inParamMap.put("cycle", cyclemonth);
                
                MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuID, operID, termID, touchOID, "1", servnum);
                
                ReturnWrap rtwp = intMsgUtil.invokeDubbo("CTCSQryBillCycleCustInfo", msgHeaderPO, inParamMap);
                
                Vector v = new Vector();
                if (rtwp.getReturnObject() instanceof CTagSet)
                {
                    v.add(rtwp.getReturnObject());
                    rtwp.setReturnObject(v);
                }
                return rtwp;
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("TELNUM");
            eReq_telnum.addText(servnum);
            
            Element eReq_month = eBody.addElement("CYCLE");
            eReq_month.addText(cyclemonth);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_BillCycleCustInfo_hub",
                    menuID,
                    touchOID,
                    "1",
                    servnum,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    @Override
    public ReturnWrap qryCurBillInfo(Map map)
    {
        // TODO Auto-generated method stub
        return null;
    }
    public void addJsonArray2Xml(Element bodyElement,com.alibaba.dubbo.common.json.JSONArray  pkgLst,String[] keys,String parentKey){
        if(pkgLst!=null){
            for(int i=0;i<pkgLst.length();i++){
                Element parentElement=bodyElement.addElement(parentKey);
                com.alibaba.dubbo.common.json.JSONObject _pkgLst=(com.alibaba.dubbo.common.json.JSONObject)pkgLst.get(i);                    
                for(int j=0;j<keys.length;j++){
                    Element element =parentElement.addElement(keys[j]);
                    element.setText((String)_pkgLst.get(keys[j])); 
                }
            }
        }
    }
    public String arQryBillCommuHuBExcelEbus(Map map) throws Exception{   
        String telnum = (String)map.get("telnum");
        String billcycle = (String)map.get("billcycle");
        //String isunitepayment = (String)map.get("isunitepayment");
        //String arealist = (String)map.get("arealist");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");        
        Map<String, String> inParamMap = new HashMap<String, String>();      
        // 电话号码
        inParamMap.put("telNum", telnum);
        // 月份
        inParamMap.put("cycle", billcycle);   
        inParamMap.put("printInfo", "1"); 
        MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuID, operID, termID, touchOID, "1", telnum);        
        ReturnWrap rtwp = intMsgUtil.invokeDubbo("arQryBillCommuHuBExcelEbus", msgHeaderPO, inParamMap,false);        
        if (SSReturnCode.SUCCESS == rtwp.getStatus())
        {
            String json=(String)rtwp.getReturnObject();
            try
            {
                com.alibaba.dubbo.common.json.JSONObject jsonObj=(com.alibaba.dubbo.common.json.JSONObject)com.alibaba.dubbo.common.json.JSON.parse(json);
                com.alibaba.dubbo.common.json.JSONArray  pkgLst=(com.alibaba.dubbo.common.json.JSONArray)jsonObj.getArray("pkgLst");
                com.alibaba.dubbo.common.json.JSONArray  overLst=(com.alibaba.dubbo.common.json.JSONArray)jsonObj.getArray("overLst");
                com.alibaba.dubbo.common.json.JSONArray  bcallLst=(com.alibaba.dubbo.common.json.JSONArray)jsonObj.getArray("bcallLst");
                String printInfo=(String)jsonObj.get("printInfo");
                Document doc = DocumentHelper.createDocument();
                doc.setXMLEncoding("GBK");
                Element bodyElement=doc.addElement("body");
                addJsonArray2Xml( bodyElement,  pkgLst,new String[]{"remainderDesc","includeDesc","usageDesc","className","packageName"},"pkgLst");
                addJsonArray2Xml( bodyElement,  overLst,new String[]{"className","includeDesc"},"overLst");
                addJsonArray2Xml( bodyElement,  bcallLst,new String[]{"usageDesc","className","remainderDesc","includeDesc"},"bcallLst");                
                if(printInfo!=null){
                    Element printInfoElement= bodyElement.addElement("printInfo");
                    printInfoElement.addText(printInfo);
                }                
                //System.out.println(doc.asXML());
                return doc.asXML();
            }
            catch (ParseException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw e;
            }
        }
        return null;
        /*
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>"+
"<body><pkgLst><remainderDesc>0分钟</remainderDesc><includeDesc>60分钟</includeDesc><usageDesc>70分钟</usageDesc><className>国内主叫</className><packageName>全球通统一套餐（2012版）_上网套餐158档</packageName></pkgLst><pkgLst><remainderDesc>10分钟</remainderDesc><includeDesc>60分钟</includeDesc><usageDesc>50分钟</usageDesc><className>本地IP长途</className><packageName>武汉动感地带短信套餐(19元)</packageName></pkgLst><pkgLst><remainderDesc>0条</remainderDesc><includeDesc>500条</includeDesc><usageDesc>520条</usageDesc><className>短信</className><packageName>武汉动感地带短信套餐(19元)</packageName></pkgLst><pkgLst><remainderDesc>50条</remainderDesc><includeDesc>200条</includeDesc><usageDesc>150条</usageDesc><className>国内短信</className><packageName>武汉畅享5元短信套餐3个月</packageName></pkgLst><pkgLst><remainderDesc>50条</remainderDesc><includeDesc>100条</includeDesc><usageDesc>50条</usageDesc><className>国内短信</className><packageName>武汉短信包</packageName></pkgLst><pkgLst><remainderDesc>0条</remainderDesc><includeDesc>100条</includeDesc><usageDesc>150条</usageDesc><className>彩信</className><packageName>湖北5元彩信包</packageName></pkgLst><pkgLst><remainderDesc>823.951M</remainderDesc><includeDesc>1024M</includeDesc><usageDesc>200.049M</usageDesc><className>WLAN流量</className><packageName>公众WLAN套餐</packageName></pkgLst><pkgLst><remainderDesc>2.5分钟</remainderDesc><includeDesc>5分钟</includeDesc><usageDesc>2.5分钟</usageDesc><className>校园WLAN时长</className><packageName>校园WLAN套餐</packageName></pkgLst><pkgLst><remainderDesc>0M</remainderDesc><includeDesc>80M</includeDesc><usageDesc>90.002M</usageDesc><className>国内流量</className><packageName>全球通统一套餐（2012版）_上网套餐158档</packageName></pkgLst><pkgLst><remainderDesc>92159.961M</remainderDesc><includeDesc>102400M</includeDesc><usageDesc>10240.039M</usageDesc><className>国内流量</className><packageName>武汉动感地带短信套餐(19元)*0</packageName></pkgLst><pkgLst><remainderDesc>90M</remainderDesc><includeDesc>100M</includeDesc><usageDesc>10M</usageDesc><className>国内流量</className><packageName>CPE定向流量40元套餐</packageName></pkgLst><pkgLst><className>国内上网流量</className><includeDesc>0M</includeDesc></pkgLst><pkgLst><className>高校WLAN流量</className><includeDesc>0分钟</includeDesc></pkgLst><pkgLst><className>WLAN流量</className><includeDesc>0M</includeDesc></pkgLst><pkgLst><usageDesc>70分钟</usageDesc><className>国内主叫</className><remainderDesc>0分钟</remainderDesc><includeDesc>60分钟</includeDesc></pkgLst><pkgLst><usageDesc>50分钟</usageDesc><className>本地IP长途</className><remainderDesc>10分钟</remainderDesc><includeDesc>60分钟</includeDesc></pkgLst><pkgLst><usageDesc>520条</usageDesc><className>短信</className><remainderDesc>0条</remainderDesc><includeDesc>500条</includeDesc></pkgLst><pkgLst><usageDesc>200条</usageDesc><className>国内短信</className><remainderDesc>100条</remainderDesc><includeDesc>300条</includeDesc></pkgLst><pkgLst><usageDesc>150条</usageDesc><className>彩信</className><remainderDesc>0条</remainderDesc><includeDesc>100条</includeDesc></pkgLst><pkgLst><usageDesc>200.049M</usageDesc><className>WLAN流量</className><remainderDesc>823.951M</remainderDesc><includeDesc>1024M</includeDesc></pkgLst><pkgLst><usageDesc>2.5分钟</usageDesc><className>校园WLAN时长</className><remainderDesc>2.5分钟</remainderDesc><includeDesc>5分钟</includeDesc></pkgLst><pkgLst><usageDesc>10330.041M</usageDesc><className>国内流量</className><remainderDesc>92159.961M</remainderDesc><includeDesc>102480M</includeDesc></pkgLst><pkgLst><usageDesc>10M</usageDesc><className>国内流量</className><remainderDesc>90M</remainderDesc><includeDesc>100M</includeDesc></pkgLst></body>";
        
        return "<?xml version=\"1.0\" encoding=\"GBK\" ?>"+
        "<body>"+
        "   <pkgLst>"+ 
        "       <remark/>"+ 
        "       <remainderDesc>362分钟</remainderDesc>"+ 
        "       <includeDesc>380分钟</includeDesc>"+ 
        "       <usageDesc>18分钟</usageDesc>"+ 
        "       <classID>1</classID>"+ 
        "       <className>本地主叫本地</className>"+ 
        "       <packageOid>B210703</packageOid>"+ 
        "       <packageName>湖北神州行本地套餐38元A</packageName>"+ 
        "   </pkgLst>"+ 
        "   <pkgLst>"+ 
        "       <remark/>"+ 
        "       <remainderDesc>98条</remainderDesc>"+ 
        "       <includeDesc>100条</includeDesc>"+ 
        "       <usageDesc>2条</usageDesc>"+ 
        "       <classID>2</classID>"+ 
        "       <className>国内短信</className>"+ 
        "       <packageOid>G271427AP</packageOid>"+ 
        "       <packageName>武汉短信包</packageName>"+ 
        "   </pkgLst>"+ 
        "   <pkgLst>"+ 
        "       <remark/>"+ 
        "       <remainderDesc>70M</remainderDesc>"+ 
        "       <includeDesc>70M</includeDesc>"+ 
        "       <usageDesc>0M</usageDesc>"+ 
        "       <classID>6</classID>"+ 
        "       <className>国内流量</className>"+ 
        "       <packageOid>G220672</packageOid>"+ 
        "       <packageName>手机上网10元流量套餐</packageName>"+ 
        "   </pkgLst>"+ 
        "   <overLst>"+ 
        "       <className>国内流量</className>"+ 
        "       <includeDesc>国内流量includedesc</includeDesc>"+ 
        "   </overLst>"+ 
        "   <overLst>"+ 
        "       <className>国内流量2</className>"+ 
        "       <includeDesc>国内流量2includedesc</includeDesc>"+ 
        "   </overLst>"+ 
        "   <bcallLst>"+ 
        "       <usageDesc>18分钟</usageDesc>"+ 
        "       <remark/>"+ 
        "       <className>本地主叫本地</className>"+ 
        "       <classID>1</classID>"+ 
        "       <remainderDesc>362分钟</remainderDesc>"+ 
        "       <includeDesc>380分钟</includeDesc>"+ 
        "   </bcallLst>"+ 
        "   <bcallLst>"+ 
        "       <usageDesc>2条</usageDesc>"+ 
        "       <remark/>"+ 
        "       <className>国内短信</className>"+ 
        "       <classID>2</classID>"+ 
        "       <remainderDesc>98条</remainderDesc>"+ 
        "       <includeDesc>100条</includeDesc>"+ 
        "   </bcallLst>"+ 
        "   <bcallLst>"+ 
        "       <usageDesc>0M</usageDesc>"+ 
        "       <remark/>"+ 
        "       <className>国内流量</className>"+ 
        "       <classID>6</classID>"+ 
        "       <remainderDesc>70M</remainderDesc>"+ 
        "       <includeDesc>70M</includeDesc>"+ 
        "   </bcallLst><printInfo>&lt;&lt;&gt;&gt;&gt;啊啊啊啊啊啊啊啊\n鹅鹅鹅饿鹅鹅鹅饿</printInfo>"+ 
        "</body>"
        ;*/
    }
    // add begin ykf38827 2012/03/20 R003C12L03n01 OR_HUB_201202_800
    /**
     * 查询未打印的发票记录数据
     * 
     * @param map
     * @return
     */
    @Override
    public ReturnWrap invoiceList(Map map)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLARQueryUnprintInvoceList")) 
            {
                String response = "[{\"accessName\":\"CRM营业厅\",\"billCycle\":0,\"custName\":\"姓名13607233161\",\"formnum\":\"711131111405756007\",\"invoiceFee\":0,\"itemName\":\"收费\",\"oid\":\"10005405756005\",\"operName\":\"系统管理员(101)\",\"orgName\":\"鄂州\",\"recDate\":\"2013-11-11 20:07:40\",\"recFee\":11200,\"recType\":\"Charge\",\"rowNum\":1,\"servNumber\":\"13607233161\",\"status\":\"正常\",\"statusDate\":\"2013-11-11 20:07:40\",\"upTotalFee\":11200,\"upperFee\":\"壹佰壹拾贰圆整\"}," +
                        "{\"accessName\":\"CRM营业厅\",\"billCycle\":0,\"custName\":\"姓名13607233161\",\"formnum\":\"711131111405756059\",\"invoiceFee\":0,\"itemName\":\"收费\",\"oid\":\"10005405756057\",\"operName\":\"系统管理员(101)\",\"orgName\":\"鄂州\",\"recDate\":\"2013-11-11 20:38:14\",\"recFee\":11300,\"recType\":\"Charge\",\"rowNum\":1,\"servNumber\":\"13607233161\",\"status\":\"正常\",\"statusDate\":\"2013-11-11 20:38:14\",\"upTotalFee\":11300,\"upperFee\":\"壹佰壹拾叁圆整\"}," +
                        "{\"accessName\":\"CRM营业厅\",\"billCycle\":0,\"custName\":\"姓名13607233161\",\"formnum\":\"711131130405795045\",\"invoiceFee\":0,\"itemName\":\"收费\",\"oid\":\"10005405795043\",\"operName\":\"系统管理员(101)\",\"orgName\":\"鄂州\",\"recDate\":\"2013-11-30 15:38:27\",\"recFee\":10000,\"recType\":\"Charge\",\"rowNum\":1,\"servNumber\":\"13607233161\",\"status\":\"正常\",\"statusDate\":\"2013-11-30 15:38:27\",\"upTotalFee\":10000,\"upperFee\":\"壹佰圆整\"}," +
                        "{\"accessName\":\"CRM营业厅\",\"billCycle\":0,\"custName\":\"姓名13607233161\",\"formnum\":\"711131202405796667\",\"invoiceFee\":0,\"itemName\":\"收费\",\"oid\":\"10005405796665\",\"operName\":\"系统管理员(101)\",\"orgName\":\"鄂州\",\"recDate\":\"2013-12-02 16:45:18\",\"recFee\":100000,\"recType\":\"Charge\",\"rowNum\":1,\"servNumber\":\"13607233161\",\"status\":\"正常\",\"statusDate\":\"2013-12-02 16:45:18\",\"upTotalFee\":100000,\"upperFee\":\"壹仟圆整\"}," +
                        "{\"accessName\":\"CRM营业厅\",\"billCycle\":0,\"custName\":\"姓名13607233161\",\"formnum\":\"711131202405796851\",\"invoiceFee\":0,\"itemName\":\"收费\",\"oid\":\"10005405796849\",\"operName\":\"系统管理员(101)\",\"orgName\":\"鄂州\",\"recDate\":\"2013-12-02 17:02:26\",\"recFee\":54500,\"recType\":\"Charge\",\"rowNum\":1,\"servNumber\":\"13607233161\",\"status\":\"正常\",\"statusDate\":\"2013-12-02 17:02:26\",\"upTotalFee\":54500,\"upperFee\":\"伍佰肆拾伍圆整\"}," +
                        "{\"accessName\":\"CRM营业厅\",\"billCycle\":0,\"custName\":\"姓名13607233161\",\"formnum\":\"711131202405796981\",\"invoiceFee\":0,\"itemName\":\"收费\",\"oid\":\"10005405796979\",\"operName\":\"系统管理员(101)\",\"orgName\":\"鄂州\",\"recDate\":\"2013-12-02 17:07:45\",\"recFee\":78900,\"recType\":\"Charge\",\"rowNum\":1,\"servNumber\":\"13607233161\",\"status\":\"正常\",\"statusDate\":\"2013-12-02 17:07:45\",\"upTotalFee\":78900,\"upperFee\":\"柒佰捌拾玖圆整\"}]";
                
                return getIntMsgUtil().parseJsonResponse(response, null, new String[] {"billCycle", "formnum", "itemName", "custName", "servNumber", "recFee", "accessName", "recDate",
                        "status", "statusDate", "operName", "orgName", "recType", "oid", "invoiceFee", "upperFee"});
            }
            
            String resp = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
                    "<menuid>invoicePrint</menuid><process_code>cli_qry_noinvoiceprint_hub</process_code><verify_code>100101</verify_code>" +
                    "<unicontact></unicontact><resp_time>20120522094655</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq>" +
                    "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>" +
                    "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>" +
                    "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
                    "<invoicelist><cycle>0</cycle><formnum>634120509891654602</formnum><busname>收费</busname>" +
                    "<custname>姓名13963456977</custname><svrnumber>13963456977</svrnumber><optsum>10000</optsum>" +
                    "<channel>网上营业厅</channel><accepttime>2012-05-07 19:18:57</accepttime><status>在网</status>" +
                    "<statustime>2012-05-07 19:18:57</statustime><operator>内置工号-网上客服虚拟工号(pinet004)</operator>" +
                    "<acceptunit>莱芜</acceptunit><businessId>Charge</businessId><busOptCode>88009891654601</busOptCode>" +
                    "<invoiceSum>0</invoiceSum><capitalSum>壹佰圆整</capitalSum><cardtype>88009891654601</cardtype><linkphone>0</linkphone>" +
                    "<linkaddress>壹佰圆整</linkaddress></invoicelist><invoicelist><cycle>0</cycle><formnum>634120509891654609</formnum>" +
                    "<busname>收费</busname><custname>姓名13963456977</custname><svrnumber>13963456977</svrnumber><optsum>1000</optsum>" +
                    "<channel>网上营业厅</channel><accepttime>2012-05-07 19:18:57</accepttime><status>在网</status>" +
                    "<statustime>2012-05-07 19:18:57</statustime><operator>内置工号-网上客服虚拟工号(pinet004)</operator>" +
                    "<acceptunit>莱芜</acceptunit><businessId>Charge</businessId><busOptCode>88009891654608</busOptCode>" +
                    "<invoiceSum>0</invoiceSum><capitalSum>壹拾圆整</capitalSum><cardtype>88009891654608</cardtype>" +
                    "<linkphone>0</linkphone><linkaddress>壹拾圆整</linkaddress></invoicelist><invoicelist><cycle>0</cycle>" +
                    "<formnum>634120509891656444</formnum><busname>收费</busname><custname>姓名13963456977</custname>" +
                    "<svrnumber>13963456977</svrnumber><optsum>13000</optsum><channel>网上营业厅</channel>" +
                    "<accepttime>2012-05-09 13:50:57</accepttime><status>在网</status><statustime>2012-05-09 13:50:57</statustime>" +
                    "<operator>内置工号-网上客服虚拟工号(pinet007)</operator><acceptunit>莱芜</acceptunit><businessId>Charge</businessId>" +
                    "<busOptCode>88009891656443</busOptCode><invoiceSum>0</invoiceSum><capitalSum>壹佰叁拾圆整</capitalSum>" +
                    "<cardtype>88009891656443</cardtype><linkphone>0</linkphone><linkaddress>壹佰叁拾圆整</linkaddress></invoicelist>" +
                    "<invoicelist><cycle>0</cycle><formnum>634120509891656451</formnum><busname>收费</busname><custname>姓名13963456977</custname>" +
                    "<svrnumber>13963456977</svrnumber><optsum>2000</optsum><channel>网上营业厅</channel><accepttime>2012-05-09 13:50:57</accepttime>" +
                    "<status>在网</status><statustime>2012-05-09 13:50:57</statustime><operator>内置工号-网上客服虚拟工号(pinet007)</operator>" +
                    "<acceptunit>莱芜</acceptunit><businessId>Charge</businessId><busOptCode>88009891656450</busOptCode>" +
                    "<invoiceSum>0</invoiceSum><capitalSum>贰拾圆整</capitalSum><cardtype>88009891656450</cardtype>" +
                    "<linkphone>0</linkphone><linkaddress>贰拾圆整</linkaddress></invoicelist><invoicelist><cycle>0</cycle>" +
                    "<formnum>634120509891658704</formnum><busname>收费</busname><custname>姓名13963456977</custname>" +
                    "<svrnumber>13963456977</svrnumber><optsum>14000</optsum><channel>网上营业厅</channel>" +
                    "<accepttime>2012-05-09 13:50:57</accepttime><status>在网</status><statustime>2012-05-09 13:50:57</statustime>" +
                    "<operator>内置工号-网上客服虚拟工号(pinet004)</operator><acceptunit>莱芜</acceptunit><businessId>Charge</businessId>" +
                    "<busOptCode>88009891658703</busOptCode><invoiceSum>0</invoiceSum><capitalSum>壹佰肆拾圆整</capitalSum>" +
                    "<cardtype>88009891658703</cardtype><linkphone>0</linkphone><linkaddress>壹佰肆拾圆整</linkaddress></invoicelist>" +
                    "<invoicelist><cycle>0</cycle><formnum>634120509891658711</formnum><busname>收费</busname>" +
                    "<custname>姓名13963456977</custname><svrnumber>13963456977</svrnumber><optsum>2000</optsum><channel>网上营业厅</channel>" +
                    "<accepttime>2012-05-09 13:50:57</accepttime><status>在网</status><statustime>2012-05-09 13:50:57</statustime>" +
                    "<operator>内置工号-网上客服虚拟工号(pinet004)</operator><acceptunit>莱芜</acceptunit><businessId>Charge</businessId>" +
                    "<busOptCode>88009891658710</busOptCode><invoiceSum>0</invoiceSum><capitalSum>贰拾圆整</capitalSum>" +
                    "<cardtype>88009891658710</cardtype><linkphone>0</linkphone><linkaddress>贰拾圆整</linkaddress></invoicelist>" +
                    "<invoicelist><cycle>0</cycle><formnum>634120509891659798</formnum><busname>收费</busname><custname>姓名13963456977</custname>" +
                    "<svrnumber>13963456977</svrnumber><optsum>2967</optsum><channel>网上营业厅</channel>" +
                    "<accepttime>2012-05-09 13:50:57</accepttime><status>在网</status><statustime>2012-05-09 13:50:57</statustime>" +
                    "<operator>内置工号-银行代收虚拟工号(p162ds02)</operator><acceptunit>莱芜</acceptunit><businessId>Charge</businessId>" +
                    "<busOptCode>88009891659797</busOptCode><invoiceSum>0</invoiceSum><capitalSum>贰拾玖圆陆角柒分</capitalSum>" +
                    "<cardtype>88009891659797</cardtype><linkphone>0</linkphone><linkaddress>贰拾玖圆陆角柒分</linkaddress></invoicelist>" +
                    "<invoicelist><cycle>0</cycle><formnum>634120509891659805</formnum><busname>收费</busname><custname>姓名13963456977</custname>" +
                    "<svrnumber>13963456977</svrnumber><optsum>33</optsum><channel>网上营业厅</channel><accepttime>2012-05-09 13:50:57</accepttime>" +
                    "<status>在网</status><statustime>2012-05-09 13:50:57</statustime><operator>内置工号-银行代收虚拟工号(p162ds02)</operator>" +
                    "<acceptunit>莱芜</acceptunit><businessId>Charge</businessId><busOptCode>88009891659804</busOptCode><invoiceSum>0</invoiceSum>" +
                    "<capitalSum>叁角叁分</capitalSum><cardtype>88009891659804</cardtype><linkphone>0</linkphone><linkaddress>叁角叁分</linkaddress>" +
                    "</invoicelist></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(resp);
        }
        catch (Exception e)
        {
            ReturnWrap rtwpp = new ReturnWrap();            
            return rtwpp;
        }
    }
    
    /**
     * 查询要打印的发票打印项数据
     * 
     * @param map
     * @return
     */
    @Override
    public ReturnWrap invoiceData(Map map)
    {
    	ReturnWrap rtwp = new ReturnWrap();
        try
        {
            /*
             * String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head
             * version=\"1.0\">" + "<menuid></menuid><process_code>cli_qry_supplyprtinvoice</process_code><verify_code></verify_code>" + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>" + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" + "<![CDATA[Processing
             * the request succeeded!]]></retmsg></retinfo></message_head><message_body>" + "<![CDATA[<?xml
             * version=\"1.0\" encoding=\"GBK\" ?><message_content>" + "<invoice_cnt>1</invoice_cnt>" + "<invoicelist><chargedate>20120101</chargedate><invoiceinfo>printDate@~@20110609||" +
             * "username@~@帐7115176988576||callfeeCreateTime@~@||telnumber@~@15864500526||" +
             * "formnum@~@711110609957987385||invoicefeedx@~@欢迎选用中国移动通信！||invoicefee@~@99.00元||" +
             * "paynumno@~@135176988576|| ||invoicefeehj@~@预存话费@99.00||Score@~@||agreementleftbal@~@@||" +
             * "agreementleftbal_Z@~@@||||||InvoiceNote@~@费用详情，请登录www.hb.10086.cn查询||" +
             * "printtime@~@打印发票时间@@16:47:40||totalmoney@~@实收现金@@99.00||leftmoney@~@可用余额@@30799.29||" +
             * "overdraft@~@其中信用额度@@0.00</invoiceinfo></invoicelist>" + "</message_content>]]></message_body></message_response>";
             */

            /*
             * String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head
             * version=\"1.0\">"+ "<menuid>invoicePrint</menuid><process_code>cli_qry_invoiceinfo_hub</process_code><verify_code></verify_code><unicontact></unicontact>"+ "<resp_time>20120326142254</resp_time><sequence><req_seq>9</req_seq><operation_seq>"+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"+ "<![CDATA[Processing
             * the request succeeded!]]></retmsg></retinfo></message_head><message_body>"+ "<![CDATA[<?xml
             * version=\"1.0\" encoding=\"GBK\" ?><message_content>"+ "<invoice_cnt>1</invoice_cnt>"+ "<invoicelist>"+ "<chargedate>20120323</chargedate>"+ "<invoiceinfo>"+
             * "paynumno@~@||"+ "username@~@姓名13605444000||"+ "telnumber@~@13605444000||"+
             * "formnum@~@634120323891281550||"+ "note@~@||"+ "WorkStation@~@市区||"+ "CollectOper@~@101||"+
             * "time@~@2012.03.23||"+ "rectype@~@重打发票||"+ "PrdGrpHead@~@神州行||"+ "PrdGrpInfo@~@自由 实惠 便捷||"+
             * "BrandLogo@~@BrandXNWLogo.bmp||"+ "ContentItem1Name@~@附加费||"+ "ContentItem1@~@20.00||"+
             * "ContentItem4Name@~@合计:||"+ "ContentItem4@~@20.00||"+ "totalmoneydx@~@贰拾圆整||"+ "totalmoney@~@20.00元"+ "</invoiceinfo>"+ "</invoicelist>"+ "</message_content>]]></message_body></message_response>";
             */
            
            // 湖北统一接口平台转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLARReprintInvoice")) 
            {
//              String response = "[{invoiceTypeItem:'invoice_cnt',invoiceItemData:'1'},{invoiceTypeItem:'chargedate',invoiceItemData:'20111010'},{invoiceTypeItem:'hz_username',invoiceItemData:'客户全称'}"
//                  + ",{invoiceTypeItem:'hz_formnum',invoiceItemData:'634120509891654602'},{invoiceTypeItem:'hz_telnumber',invoiceItemData:'电话号码'},{invoiceTypeItem:'hz_totalmoneydx',invoiceItemData:'收费金额(大写)'}"
//                  + ",{invoiceTypeItem:'hfjedx',invoiceItemData:'话费金额(大写)'},{invoiceTypeItem:'hz_totalmoney',invoiceItemData:'小  写'},{invoiceTypeItem:'hz_rectype',invoiceItemData:'受理类别'}"
//                  + ",{invoiceTypeItem:'kxxz',invoiceItemData:'款项性质'},{invoiceTypeItem:'hz_time',invoiceItemData:'打印时间'},{invoiceTypeItem:'paynumno',invoiceItemData:''}"
//                  + ",{invoiceTypeItem:'username',invoiceItemData:'客20029663004184'},{invoiceTypeItem:'telnumber',invoiceItemData:'13677113451'},{invoiceTypeItem:'formnum',invoiceItemData:'711111010663004170'}"
//                  + ",{invoiceTypeItem:'note',invoiceItemData:''},{invoiceTypeItem:'WorkStation',invoiceItemData:'鄂州市区营业区'},{invoiceTypeItem:'CollectOper',invoiceItemData:'1101838'}"
//                  + ",{invoiceTypeItem:'time',invoiceItemData:'10:07:30'},{invoiceTypeItem:'year',invoiceItemData:'2011'},{invoiceTypeItem:'month',invoiceItemData:'10'}"
//                  + ",{invoiceTypeItem:'day',invoiceItemData:'10'},{invoiceTypeItem:'rectype',invoiceItemData:'重打发票'},{invoiceTypeItem:'PrdGrpHead',invoiceItemData:'全球通'}"
//                  + ",{invoiceTypeItem:'PrdGrpInfo',invoiceItemData:'积极、掌控、品位'},{invoiceTypeItem:'ggcont',invoiceItemData:''},{invoiceTypeItem:'BrandLogo',invoiceItemData:'C://BrandGotoneLogo.bmp'}"
//                  + ",{invoiceTypeItem:'ContentItem1Name',invoiceItemData:'手机支付充值'},{invoiceTypeItem:'ContentItem1',invoiceItemData:'300.00'},{invoiceTypeItem:'ContentItem3Name',invoiceItemData:'合计:'}"
//                  + ",{invoiceTypeItem:'ContentItem3',invoiceItemData:'300.00'},{invoiceTypeItem:'totalmoneydx',invoiceItemData:'叁佰圆整'},{invoiceTypeItem:'totalmoney',invoiceItemData:'300.00元'}]";
                
                String response = "[{\"invoiceItemData\":\"集团业务使用费\",\"invoiceTypeItem\":\"feetype\"},{\"invoiceItemData\":\"全球通:积极、掌控、品位\",\"invoiceTypeItem\":\"productgrp\"},{\"invoiceItemData\":\"姓名13607233161\",\"invoiceTypeItem\":\"username\"},{\"invoiceItemData\":\"13607233161\",\"invoiceTypeItem\":\"telnumber\"},{\"invoiceItemData\":\"7110000000161\",\"invoiceTypeItem\":\"paynumno\"},{\"invoiceItemData\":\"711131111405755683\",\"invoiceTypeItem\":\"formnum\"},{\"invoiceItemData\":\"鄂州\",\"invoiceTypeItem\":\"WorkStation\"},{\"invoiceItemData\":\"101\",\"invoiceTypeItem\":\"CollectOper\"},{\"invoiceItemData\":\"2013\",\"invoiceTypeItem\":\"printyear\"},{\"invoiceItemData\":\"11\",\"invoiceTypeItem\":\"printmonth\"},{\"invoiceItemData\":\"11\",\"invoiceTypeItem\":\"printday\"},{\"invoiceItemData\":\"14年5月\",\"invoiceTypeItem\":\"custnetage\"},{\"invoiceItemData\":\"壹佰壹拾壹圆整\",\"invoiceTypeItem\":\"invoicefeedx\"},{\"invoiceItemData\":\"111.00元\",\"invoiceTypeItem\":\"invoicefee\"},{\"invoiceItemData\":\"预存话费：111.00\",\"invoiceTypeItem\":\"invoicefeehj\"},{\"invoiceItemData\":\"打印发票时间：\",\"invoiceTypeItem\":\"hz_printtime\"},{\"invoiceItemData\":\"16:41:17\",\"invoiceTypeItem\":\"printtime\"},{\"invoiceItemData\":\"实收现金：\",\"invoiceTypeItem\":\"hz_totalmoney\"},{\"invoiceItemData\":\"111.00\",\"invoiceTypeItem\":\"totalmoney\"},{\"invoiceItemData\":\"可用余额：\",\"invoiceTypeItem\":\"hz_leftmoney\"},{\"invoiceItemData\":\"0.00\",\"invoiceTypeItem\":\"leftmoney\"},{\"invoiceItemData\":\"其中信用额度：\",\"invoiceTypeItem\":\"hz_overdraft\"},{\"invoiceItemData\":\"0.00\",\"invoiceTypeItem\":\"overdraft\"},{\"invoiceItemData\":\"费用详情，请登录www.hb.10086.cn查询\",\"invoiceTypeItem\":\"note\"}]";
                
                return getIntMsgUtil().parseJsonResponse(response, null, new String[]{"invoiceTypeItem", "invoiceItemData"});
            }

            String response = "<message_response><message_head version=\"1.0\"><menuid>invoicePrint</menuid><process_code>cli_qry_invoiceinfo_hub</process_code>"
                    + "<verify_code></verify_code><resp_time>20120423162734</resp_time><sequence><req_seq>346</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA["
                    + "<message_content><invoice_cnt>1</invoice_cnt><invoicelist><chargedate>20111010</chargedate>"
                    + "<invoiceinfo>hz_username@~@客户全称||hz_formnum@~@634120509891654602||hz_telnumber@~@电话号码||hz_totalmoneydx@~@收费金额(大写)||"
                    + "hfjedx@~@话费金额(大写)||hz_totalmoney@~@小  写||hz_rectype@~@受理类别||kxxz@~@款项性质||hz_time@~@打印时间||paynumno@~@||"
                    + "username@~@客20029663004184||telnumber@~@13677113451||formnum@~@711111010663004170||note@~@||WorkStation@~@鄂州市区营业区||"
                    + "CollectOper@~@1101838||time@~@10:07:30||year@~@2011||month@~@10||day@~@10||rectype@~@重打发票||PrdGrpHead@~@全球通||"
                    + "PrdGrpInfo@~@积极、掌控、品位||ggcont@~@||BrandLogo@~@C://BrandGotoneLogo.bmp||ContentItem1Name@~@手机支付充值||"
                    + "ContentItem1@~@300.00||ContentItem3Name@~@合计:||ContentItem3@~@300.00||totalmoneydx@~@叁佰圆整||totalmoney@~@300.00元"
                    + "</invoiceinfo></invoicelist></message_content>]]></message_body></message_response>";
            
            response = "<message_response><message_head version=\"1.0\"><menuid>invoicePrint</menuid><process_code>cli_qry_invoiceinfo_hub</process_code>"
                + "<verify_code></verify_code><resp_time>20120423162734</resp_time><sequence><req_seq>346</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                + "</message_head><message_body><![CDATA["
                + "<message_content>" 
                + "<invoicelist><name>invoice_cnt</name><value>1</value></invoicelist>" 
                + "<invoicelist><name>chargedate</name><value>20111010</value></invoicelist>" 
                + "<invoicelist><name>hz_username</name><value>客户全称</value></invoicelist>" 
                + "<invoicelist><name>hz_formnum</name><value>634120509891654602</value></invoicelist>" 
                + "<invoicelist><name>hz_telnumber</name><value>电话号码</value></invoicelist>" 
                + "<invoicelist><name>hz_totalmoneydx</name><value>收费金额(大写)</value></invoicelist>" 
                + "<invoicelist><name>hfjedx</name><value>话费金额(大写)</value></invoicelist>" 
                + "<invoicelist><name>hz_totalmoney</name><value>小  写</value></invoicelist>" 
                + "<invoicelist><name>hz_rectype</name><value>受理类别</value></invoicelist>" 
                + "<invoicelist><name>kxxz</name><value>款项性质</value></invoicelist>" 
                + "<invoicelist><name>hz_time</name><value>打印时间</value></invoicelist>" 
                + "<invoicelist><name>paynumno</name><value></value></invoicelist>" 
                + "<invoicelist><name>username</name><value>客20029663004184</value></invoicelist>" 
                + "<invoicelist><name>telnumber</name><value>13677113451</value></invoicelist>" 
                + "<invoicelist><name>formnum</name><value>711111010663004170</value></invoicelist>" 
                + "<invoicelist><name>note</name><value></value></invoicelist>" 
                + "<invoicelist><name>WorkStation</name><value>鄂州市区营业区</value></invoicelist>" 
                + "<invoicelist><name>CollectOper</name><value>1101838</value></invoicelist>" 
                + "<invoicelist><name>time</name><value>10:07:30</value></invoicelist>" 
                + "<invoicelist><name>year</name><value>2011</value></invoicelist>" 
                + "<invoicelist><name>month</name><value>10</value></invoicelist>" 
                + "<invoicelist><name>day</name><value>10</value></invoicelist>" 
                + "<invoicelist><name>rectype</name><value>重打发票</value></invoicelist>" 
                + "<invoicelist><name>PrdGrpHead</name><value>全球通</value></invoicelist>" 
                + "<invoicelist><name>PrdGrpInfo</name><value>积极、掌控、品位</value></invoicelist>" 
                + "<invoicelist><name>ggcont</name><value></value></invoicelist>" 
                + "<invoicelist><name>BrandLogo</name><value>C://BrandGotoneLogo.bmp</value></invoicelist>" 
                + "<invoicelist><name>ContentItem1Name</name><value>手机支付充值</value></invoicelist>" 
                + "<invoicelist><name>ContentItem1</name><value>300.00</value></invoicelist>" 
                + "<invoicelist><name>ContentItem3Name</name><value>合计:</value></invoicelist>" 
                + "<invoicelist><name>ContentItem3</name><value>300.00</value></invoicelist>" 
                + "<invoicelist><name>totalmoneydx</name><value>叁佰圆整</value></invoicelist>" 
                + "<invoicelist><name>totalmoney</name><value>300.00元</value></invoicelist>" 
                + "</message_content>]]></message_body></message_response>";
            
            response = "<message_response><message_head version=\"1.0\"><menuid>invoicePrint</menuid><process_code>cli_qry_invoiceinfo_hub</process_code>"
                + "<verify_code></verify_code><resp_time>20120423162734</resp_time><sequence><req_seq>346</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                + "</message_head><message_body><![CDATA["
                + "<message_content>" 
                + "<invoice_cnt>1</invoice_cnt>" 
                + "<chargedate>20111010</chargedate>"
                + "<hz_username>客户全称</hz_username>"
                + "<hz_formnum>634120509891654602</hz_formnum>"
                + "<hz_telnumber>电话号码</hz_telnumber>"
                + "<hz_totalmoneydx>收费金额(大写)</hz_totalmoneydx>"
                + "<hfjedx>话费金额(大写)</hfjedx>" 
                + "<hz_totalmoney>小  写</hz_totalmoney>"
                + "<hz_rectype>受理类别</hz_rectype>" 
                + "<kxxz>款项性质</kxxz>" 
                + "<hz_time>打印时间</hz_time>" 
                + "<paynumno></paynumno>" 
                + "<username>客20029663004184</username>" 
                + "<telnumber>13677113451</telnumber>" 
                + "<formnum>711111010663004170</formnum>" 
                + "<note></note>" 
                + "<WorkStation>鄂州市区营业区</WorkStation>" 
                + "<CollectOper>1101838</CollectOper>" 
                + "<time>10:07:30</time>" 
                + "<year>2011</year>" 
                + "<month>10</month>" 
                + "<day>10</day>" 
                + "<rectype>重打发票</rectype>" 
                + "<PrdGrpHead>全球通</PrdGrpHead>" 
                + "<PrdGrpInfo>积极、掌控、品位</PrdGrpInfo>" 
                + "<ggcont></ggcont>" 
                + "<BrandLogo>C://BrandGotoneLogo.bmp</BrandLogo>" 
                + "<ContentItem1Name>手机支付充值</ContentItem1Name>" 
                + "<ContentItem1>300.00</ContentItem1>" 
                + "<ContentItem3Name>合计:</ContentItem3Name>" 
                + "<ContentItem3>300.00</ContentItem3>" 
                + "<totalmoneydx>叁佰圆整</totalmoneydx>" 
                + "<totalmoney>300.00元</totalmoney>"
                + "</message_content>]]></message_body></message_response>";
            
            rtwp = intMsgUtil.parseResponse(response);
            return rtwp;
        }
        catch (Exception e)
        {
            return rtwp;
        }
    }
    
    // add end ykf38827 2012/03/20 R003C12L03n01 OR_HUB_201202_800
    
    // add begin yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
    /**
     * 调用接口获取产品变更确认信息
     * 
     * @param map
     * @return
     */
    @Override
    public ReturnWrap mainProductRecInfo(MsgHeaderPO msgHeader, String ncode, String inttime)
    {
        try
        {
            // modify begin wWX217192 2014-09-20 OR_huawei_201409_433 自助终端接入EBUS_资费套餐转换 
            if(IntMsgUtil.isTransEBUS("BLCSProductRec"))
            {
                /*String response = "{\"formNumber\":\"711140923210886396\",\"orderID\":\"711140923210886388\",\"subsID\":\"7115183830258\",\"voipImsi\":\"\",\"yywwFormNum\":\"20089210886394\"," +
                        "\"newProdList\":[{\"editStatus\":\"A\",\"prodID\":\"Christ_bag1\",\"prodName\":\"Christ产品包1\",\"prodCreateDate\":\"2012-04-25 10:06:53\",\"prodEndDate\":\"\",\"prodPackName\":\"\",\"privID\":\"\",\"privName\":\"\",\"privCreateDate\":\"\",\"privEndDate\":\"\",\"delReason\":\"\"}]," +
                        "\"originalProdList\":[{\"editStatus\":\"O\",\"prodID\":\"C06\",\"prodName\":\"呼出限制\",\"prodCreateDate\":\"2007-09-01 00:00:00\",\"prodEndDate\":\"\",\"prodPackName\":\"\",\"privID\":\"\",\"privName\":\"\",\"privCreateDate\":\"\",\"privEndDate\":\"\",\"delReason\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodID\":\"H01\",\"prodName\":\"来电显示\",\"prodCreateDate\":\"2002-07-02 17:33:40\",\"prodEndDate\":\"\",\"prodPackName\":\"\",\"privID\":\"\",\"privName\":\"\",\"privCreateDate\":\"\",\"privEndDate\":\"\",\"delReason\":\"\"}]," +
                        "\"delProdList\":[{\"editStatus\":\"D\",\"prodID\":\"Christmasappend\",\"prodName\":\"Christmas增值1\",\"prodCreateDate\":\"2012-04-12 15:46:20\",\"prodEndDate\":\"\",\"prodPackName\":\"\",\"privID\":\"\",\"privName\":\"\",\"privCreateDate\":\"\",\"privEndDate\":\"\",\"delReason\":\"\"}]}";*/
                
                /*String response = "{\"delProdList\":[{\"delReason\":\"\",\"editStatus\":\"D\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"2014-10-01 00:00:00\",\"prodID\":\"MP9990402000353\",\"prodName\":\"湖北动感地带上网套餐(校园版)18元\",\"prodPackName\":\"\"}]," +
                        "\"newProdList\":[{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-10-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"MP9990402000355\",\"prodName\":\"湖北动感地带上网套餐(校园版)38元\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-10-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"pkg.mz.st\",\"prodName\":\"[基本优惠包]:标准动感地带\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-10-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"B211055\",\"prodName\":\"湖北动感地带上网套餐（校园版）38元必选产品\",\"prodPackName\":\"[基本优惠包]:标准动感地带\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-10-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"pkg.eduwlan\",\"prodName\":\"高校WLAN业务\",\"prodPackName\":\"\"},{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-10-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"atom.EduWlan\",\"prodName\":\"高校WLAN业务(服务)\",\"prodPackName\":\"高校WLAN业务\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-10-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"G211058\",\"prodName\":\"湖北动感地带上网套餐（校园版）38元WLAN优惠\",\"prodPackName\":\"高校WLAN业务\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-10-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"G_RP210211\",\"prodName\":\"湖北长途一费制必选优惠包(老资费)\",\"prodPackName\":\"\"}]," +
                        "\"originalProdList\":[{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"LTE\",\"prodName\":\"4G功能\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"114\",\"prodName\":\"短消息\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"pkg.serv.ct\",\"prodName\":\"服务长途包\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"901\",\"prodName\":\"国内长途\",\"prodPackName\":\"服务长途包\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"CTYFZ\",\"prodName\":\"国际长途一费制\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"G210212\",\"prodName\":\"国际长途一费制按分钟计费\",\"prodPackName\":\"国际长途一费制\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"S1406684101\",\"prodName\":\"和娱乐体验包\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"120\",\"prodName\":\"呼叫转移\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"100\",\"prodName\":\"来电显示\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"4G1stMatch\",\"prodName\":\"4G客户专享入网首月减免优惠\",\"prodPackName\":\"\"}]}";*/
                String response = "{\"delProdList\":[{\"delReason\":\"\",\"editStatus\":\"D\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"2014-12-01 00:00:00\",\"prodID\":\"MP9990402000353\",\"prodName\":\"湖北动感地带上网套餐(校园版)18元\",\"prodPackName\":\"\"}]," +
                        "\"newProdList\":[{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"MP9990402000355\",\"prodName\":\"湖北动感地带上网套餐(校园版)38元\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"pkg.mz.st\",\"prodName\":\"[基本优惠包]:标准动感地带\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"B211055\",\"prodName\":\"湖北动感地带上网套餐（校园版）38元必选产品\",\"prodPackName\":\"[基本优惠包]:标准动感地带\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"pkg.eduwlan\",\"prodName\":\"高校WLAN业务\",\"prodPackName\":\"\"},{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"atom.EduWlan\",\"prodName\":\"高校WLAN业务(服务)\",\"prodPackName\":\"高校WLAN业务\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"G211058\",\"prodName\":\"湖北动感地带上网套餐（校园版）38元WLAN优惠\",\"prodPackName\":\"高校WLAN业务\"},{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"G_RP210211\",\"prodName\":\"湖北长途一费制必选优惠包(老资费)\",\"prodPackName\":\"\"}]," +
                        "\"originalProdList\":[{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"LTE\",\"prodName\":\"4G功能\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"114\",\"prodName\":\"短消息\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"pkg.serv.ct\",\"prodName\":\"服务长途包\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"901\",\"prodName\":\"国内长途\",\"prodPackName\":\"服务长途包\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"CTYFZ\",\"prodName\":\"国际长途一费制\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"G210212\",\"prodName\":\"国际长途一费制按分钟计费\",\"prodPackName\":\"国际长途一费制\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"S1406684101\",\"prodName\":\"和娱乐体验包\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"120\",\"prodName\":\"呼叫转移\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"100\",\"prodName\":\"来电显示\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"4G1stMatch\",\"prodName\":\"4G客户专享入网首月减免优惠\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"privCreateDate\":\"2014-11-01 00:00:00\",\"privEndDate\":\"\",\"privID\":\"2014101301\",\"privName\":\"档次1\",\"prodCreateDate\":\"2014-11-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"20141013\",\"prodName\":\"预存赠送-spj测试\",\"prodPackName\":\"\"}]," +
                        "\"showProdList\":[{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"MP9990402000355\",\"prodName\":\"湖北动感地带上网套餐(校园版)38元\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"B211055\",\"prodName\":\"湖北动感地带上网套餐（校园版）38元必选产品\",\"prodPackName\":\"[基本优惠包]:标准动感地带\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"atom.EduWlan\",\"prodName\":\"高校WLAN业务(服务)\",\"prodPackName\":\"高校WLAN业务\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"G211058\",\"prodName\":\"湖北动感地带上网套餐（校园版）38元WLAN优惠\",\"prodPackName\":\"高校WLAN业务\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"G_RP210211\",\"prodName\":\"湖北长途一费制必选优惠包(老资费)\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"LTE\",\"prodName\":\"4G功能\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"114\",\"prodName\":\"短消息\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"pkg.serv.ct\",\"prodName\":\"服务长途包\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"901\",\"prodName\":\"国内长途\",\"prodPackName\":\"服务长途包\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"CTYFZ\",\"prodName\":\"国际长途一费制\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"G210212\",\"prodName\":\"国际长途一费制按分钟计费\",\"prodPackName\":\"国际长途一费制\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"S1406684101\",\"prodName\":\"和娱乐体验包\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"120\",\"prodName\":\"呼叫转移\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"100\",\"prodName\":\"来电显示\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"4G1stMatch\",\"prodName\":\"4G客户专享入网首月减免优惠\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"privCreateDate\":\"2014-11-01 00:00:00\",\"privEndDate\":\"\",\"privID\":\"2014101301\",\"privName\":\"档次1\",\"prodCreateDate\":\"2014-11-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"20141013\",\"prodName\":\"预存赠送-spj测试\",\"prodPackName\":\"\"}," +
                        "{\"delReason\":\"\",\"editStatus\":\"D\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"2014-12-01 00:00:00\",\"prodID\":\"MP9990402000353\",\"prodName\":\"湖北动感地带上网套餐(校园版)18元\",\"prodPackName\":\"\"}]}";
                
                ReturnWrap rtwp = new ReturnWrap();
                
                rtwp.setReturnObject(response);
                rtwp.setStatus(SSReturnCode.SUCCESS);
                rtwp.setReturnMsg("");
                
                return rtwp;
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recMainProdChange</menuid>"
                    + "<process_code>cli_busi_MainIntProductRec</process_code><verify_code></verify_code><unicontact></unicontact>"
                    + "<resp_time>20120425100133</resp_time><sequence><req_seq>4</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype>"
                    + "<retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    + "<message_content>"
                    + "<prodlist><type>A</type><prodid>Christ_bag1</prodid><prodname>Christ产品包1</prodname><begindate>2012-04-25 10:06:53</begindate>"
                    + "<enddate>2012-04-25 10:06:53</enddate><pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate>"
                    + "</privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>C06</prodid><prodname>呼出限制</prodname><begindate>2007-09-01 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>H01</prodid><prodname>来电显示</prodname><begindate>2002-07-02 17:33:40</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>SELFRING</prodid><prodname>彩铃</prodname><begindate>2006-02-23 11:17:50</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>Z07</prodid><prodname>移动全时通</prodname><begindate>2006-08-01 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>M03</prodid><prodname>国内漫游开通</prodname><begindate>2001-03-23 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname>漫游级别包</pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>pkg_roamid</prodid><prodname>漫游级别包</prodname><begindate>2001-03-23 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>pp.gt.csl</prodid><prodname>融合V网</prodname><begindate>2007-09-01 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>pkg_cslbx.634</prodid><prodname>国内商旅套餐必选包</prodname><begindate>2007-09-01 00:00:00</begindate><enddate>"
                    + "</enddate><pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>gl.base.999251.634</prodid><prodname>全时通协议消费3元</prodname><begindate>2007-09-01 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid>gl.base.999251.634</privid><privname>全时通协议消费3元</privname><privbegindate>2007-09-01 00:00:00</privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>D</type><prodid>Christmasappend</prodid><prodname>Christmas增值1</prodname><begindate>2012-04-12 15:46:20</begindate><enddate>2012-04-26 00:00:00</enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * 调用接口执行主体产品转换
     * 
     * @param map
     * @return
     */
    public ReturnWrap mainProductChangeSubmit(MsgHeaderPO msgHeader, String ncode)
    {
        try
        {
        	// 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("IncProductSrv2"))
            {
                String response = "{'-preparebusi':'BsacNBSubmit','ACCESSTYPE':'INNER','ADDNCODE':'MP9990402000353'," 
                	+ "'ADD_ENDDATE':'','ADD_STARTDATE':'2014-12-01 00:00:00','BOSSCODE':'INT_B211053_MAIN','BRAND':'BrandSzx',"
                	+ "'BUSITYPE':'0','CALL_NUMBER':'13545408602','CURID':'','CURNCODE':'','DELNCODE':'MP7110503000163','DEL_ENDDATE':'',"
                	+ "'EFFTYPE':'Type_Default','EFF_DATE':'2014-12-01','EFF_DATETIME':'2014-12-01 00:00:00','END_DATE':'ZERO'," 
                	+ "'ERRNO':'0','EXECUTECMD':'TRUE','FORMNUMBER':'711141117221133696','HASPARAM':'0','IMSI':'460001823766622'," 
                	+ "'INPARAMFORMAT':'','INPARAMSPLIT':'','IPADDRESS':'','ISNEEDTHIRDCONF':'0','ISWRITEFILELOG':'0'," 
                	+ "'ISWRITETABLOG':'1','JOBDESC':'动感地带上网套餐2012版（校园版）18元','JOBNAME':'主体产品转换用','LINKNODE':''," 
                	+ "'LINKTYPE':'','MAINPRODID':'MP7110503000163','MSISDN':'13545408602','NCODE':'INT_B211053_MAIN'," 
                	+ "'NEXTOUTPARAM':'','NEXTOUTPARAMALL':'','NEXTOUTPARAMNOREP':'','NOPENEDPMP':'ZERO','OLD_PASSWD':''," 
                	+ "'OPENEDPMP':'ZERO','ORDERRESULT':'9900','ORI_NEXTATTRS':'','OUTOPERID':'','OUTPARAMFORMAT':'','OUTPARAMSPLIT':''," 
                	+ "'PARM':'','REALOPERID':'','REALRETCODE':'100','REGION':'711','RETCODE':'0','RETCONVERT':'1'," 
                	+ "'RETMSG':'','RETURN':'','SENDORNOT':'0','STEP':'76','STYPE':'ADD','SUBORDERRESULT':'','SUBSCREATEDATE':''," 
                	+ "'SUBSID':'7115181030118','TELNUM':'13545408602','TEMPLATENO':'P00100','TEMPLATEPARA':'1$主体产品转换用&2$2014-12-01&3$ZERO&4$ZERO'," 
                	+ "'UNITID':'INNER','VNCODE':'vsmasingle','YYWWFORMNUM':'20089221133694','accessType':'bsacAtsv','formnum':'','interfaceID':'IncProductSrv2'," 
                	+ "'menuID':'telProdDiy','operatorID':'101','region':'','reqSeq':'1','reqTime':'20141117152831','routeType':'1'," 
                	+ "'telNum':'13545408602','uniContact':'','uniContactFlag':'','unitID':'HUAWEI','virtualActorFlag':'1'}";
                return intMsgUtil.parseJsonResponse(response, null, null);
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
                    "<message_response><message_head version=\"1.0\">" +
                    "<menuid>recMainProdChange</menuid><process_code>" +
                    "cli_busi_ChangeProductSubmit</process_code><verify_code></verify_code>" +
                    "<unicontact></unicontact><resp_time>20120425100313</resp_time><sequence>" +
                    "<req_seq>5</req_seq><operation_seq></operation_seq></sequence><retinfo>" +
                    "<rettype>0</rettype><retcode>979077</retcode><retmsg>" +
                    "<![CDATA[不满足业务办理的条件，原因:提示：产品[彩铃]不允许办理[产品变更]业务!......]]>" +
                    "</retmsg></retinfo></message_head></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    // add begin yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
    
    /**
     * 查询要打印的发票打印项数据
     * @param map
     * @return
     */
    public ReturnWrap invoiceDataByActivity(Map map)
    {
        try
        { 
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("PTSupplyPrintInvoice"))
            {
                String response = "{'payTime':'20120323','invoiceCount':'1','printItems':" +
                        "'paynumno@~@||username@~@姓名13605444000||telnumber@~@13605444000||"+
                        "formnum@~@634120323891281550||note@~@||WorkStation@~@市区||"+
                        "CollectOper@~@101||time@~@2012.03.23||rectype@~@重打发票||PrdGrpHead@~@神州行||"+
                        "PrdGrpInfo@~@自由 实惠 便捷||BrandLogo@~@BrandXNWLogo.bmp||ContentItem1Name@~@附加费||"+
                        "ContentItem1@~@20.00||ContentItem4Name@~@合计:||ContentItem4@~@20.00||"+
                        "totalmoneydx@~@贰拾圆整||totalmoney@~@20.00元'}";
                ReturnWrap rtwp = null;
                rtwp = intMsgUtil.parseJsonResponse(response, null, null);
                
                if (rtwp != null && rtwp.getStatus() == SSReturnCode.SUCCESS)
                {
                    CTagSet ctagSet = (CTagSet) rtwp.getReturnObject();
                    
                    // tag出参
                    CTagSet outParam = new CTagSet();
                    
                    // 组装crset出参
                    CRSet crset = new CRSet(2);
                    
                    Vector v = new Vector();
                    
                    // 发票数目
                    String invoiceCount = ctagSet.GetValue("invoiceCount");
                    outParam.SetValue("invoice_cnt", invoiceCount);
                    
                    v.add(outParam);
                    
                    // 有发票内容，取内容组装出参
                    if ("1".equals(invoiceCount))
                    {
                        // 增加一行
                        crset.AddRow();
                        
                        // 受理日期
                        crset.SetValue(0, 0, ctagSet.GetValue("payTime"));
                        
                        // 发票内容
                        crset.SetValue(0, 1, ctagSet.GetValue("printItems"));
                    }
                    v.add(crset);
                    rtwp.setReturnObject(v);
                }
                return rtwp;
                
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"+
                       "<menuid>invoicePrint</menuid><process_code>cli_qry_invoiceinfo_hub</process_code><verify_code></verify_code><unicontact></unicontact>"+
                       "<resp_time>20120326142254</resp_time><sequence><req_seq>9</req_seq><operation_seq>"+
                       "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"+
                       "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"+
                       "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"+
                       "<invoice_cnt>1</invoice_cnt>"+
                       "<invoicelist>"+
                       "<chargedate>20120323</chargedate>"+
                       "<invoiceinfo>"+
                       "paynumno@~@||"+
                       "username@~@姓名13605444000||"+
                       "telnumber@~@13605444000||"+
                       "formnum@~@634120323891281550||"+
                        "note@~@||"+
                       "WorkStation@~@市区||"+
                       "CollectOper@~@101||"+
                       "time@~@2012.03.23||"+
                       "rectype@~@重打发票||"+
                       "PrdGrpHead@~@神州行||"+
                       "PrdGrpInfo@~@自由 实惠 便捷||"+
                       "BrandLogo@~@BrandXNWLogo.bmp||"+
                       "ContentItem1Name@~@附加费||"+
                        "ContentItem1@~@20.00||"+
                        "ContentItem4Name@~@合计:||"+
                        "ContentItem4@~@20.00||"+
                        "totalmoneydx@~@贰拾圆整||"+
                        "totalmoney@~@20.00元"+
                        "</invoiceinfo>"+
                        "</invoicelist>"+
                        "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    public ReturnWrap validateTelSim(Map<String, String> paramMap)
    {
        return null;
    }
    
    public ReturnWrap queryFeeItemList(Map<String, String> paramMap)
    {
        return null;
    }
   
    public ReturnWrap querySaleCond(Map<String, String> paramMap){
        return null;
    }
@Override
    
    public ReturnWrap qryAllBackInfo(Map map)
    {
        // TODO Auto-generated method stub
        return null;
    }
    public ReturnWrap commitInstallProd(Map<String, String> paramMap)
    {
        return null;
    }
/**
 * 证件校验
 * 证件校验
 * @param 证件类型
 * @param startTime 证件号码
 * @param endTime 相关号码
 * @param logLevel 操作员Id
 * @param userName 终端机ID
 * @param bufferNum 当前菜单Id
 * @return tagset 结果集，
 * @since CommonLog1.0
 * @remark create yKF73963 2012-07-12 OR_HUB_201202_1192
*/
 public ReturnWrap certCardInfo(Map paramMap){
     String certType = (String)paramMap.get("certType");
     String certId = (String)paramMap.get("certId");
     String pesSubsNum = (String)paramMap.get("pesSubsNum");
     String operid = (String)paramMap.get("curOper");
     String atsvNum = (String)paramMap.get("atsvNum");
     String menuid = (String)paramMap.get("curMenuId");
     String region = (String)paramMap.get("region");
    
     
     Document doc = DocumentHelper.createDocument();
     Element eBody = doc.addElement("message_content");
    // 证件类型
     Element eReq_telnum = eBody.addElement("certType");
     eReq_telnum.addText(certType);
     // 证件号码
     Element eReq_startdate = eBody.addElement("certId");
     eReq_startdate.addText(certId);
     // 相关号码
     Element eReq_endDate = eBody.addElement("pesSubsNum");
     eReq_endDate.addText(pesSubsNum);
     Document docXML = intMsgUtil.createMsg(doc,"cli_ChkCertSubs_hub",menuid,null,"0",region,operid,atsvNum);
     return intMsgUtil.invoke(docXML);
   
 }
 /**
  * 获取SIM卡信息
  * 
  * @param 
  * @param iccid sim卡_iccid
 * @param region 地区
 * @param operid 操作员Id
 * @param atsvNum 终端机ID
 * @param menuid 当前菜单Id
  * @return tagset 结果集，
  * @since CommonLog1.0
  * @remark create yKF73963 2012-07-16 OR_HUB_201202_1192
 */
 public ReturnWrap getSimInfo(Map paramMap){
     String iccid = (String)paramMap.get("iccid");
     String operid = (String)paramMap.get("curOper");
     String atsvNum = (String)paramMap.get("atsvNum");
     String menuid = (String)paramMap.get("curMenuId");
     String region = (String)paramMap.get("region");
    
     
     Document doc = DocumentHelper.createDocument();
     Element eBody = doc.addElement("message_content");
    //sim——iccid
     Element eiccid = eBody.addElement("iccid");
     eiccid.addText(iccid);
     Document docXML = intMsgUtil.createMsg(doc,"cli_GetSimInfo_hub",menuid,null,"0",region,operid,atsvNum);
     return intMsgUtil.invoke(docXML);
   
 }
 /**
  * 获取号码
  * 
  * @param
  * @param fitmod 号码符合条件
  * @param hlrid 归属HLR
  * @param groupid HLR分组号
  * @param istoretype 查询库标志
  * @param teltype 产品品牌
  * @param prdtype 号码类型
  * @param maxcount 返回记录的最大数量
  * @param region 地区
  * @param operid 操作员Id
  * @param atsvNum 终端机ID
  * @param menuid 当前菜单Id
  * @return crset 结果集，
  * @since CommonLog1.0
  * @remark create yKF73963 2012-07-16 OR_HUB_201202_1192
  */
 public ReturnWrap getTelnumbs(Map paramMap)
 {
     String operid = (String)paramMap.get("curOper");
     String atsvNum = (String)paramMap.get("atsvNum");
     String menuid = (String)paramMap.get("curMenuId");
     String region = (String)paramMap.get("region");
     
     String fitmod = (String)paramMap.get("fitmod");
     String hlrid = (String)paramMap.get("hlrid");
     String groupid = (String)paramMap.get("groupid");
     String istoretype = (String)paramMap.get("istoretype");
     String prdtype = (String)paramMap.get("prdtype");
     String teltype = (String)paramMap.get("teltype");
     String maxcount = (String)paramMap.get("maxcount");
     
     Document doc = DocumentHelper.createDocument();
     Element eBody = doc.addElement("message_content");
     // sim——iccid
     Element efitmod = eBody.addElement("fitmod");
     efitmod.addText(fitmod);
     Element ehlrid = eBody.addElement("hlrid");
     ehlrid.addText(hlrid);
     Element egroupid = eBody.addElement("groupid");
     egroupid.addText(groupid);
     Element eistoretype = eBody.addElement("istoretype");
     eistoretype.addText(istoretype);
     Element eprdtype = eBody.addElement("prdtype");
     eprdtype.addText(prdtype);
     Element eteltype = eBody.addElement("teltype");
     eteltype.addText(teltype);
     Element emaxcount = eBody.addElement("maxcount");
     emaxcount.addText(maxcount);
     
     Document docXML = intMsgUtil.createMsg(doc, "cli_TheHlrGrpNum_hub", menuid, null, "0", region, operid, atsvNum);
     return intMsgUtil.invoke(docXML);
     
 }
 /**
  * 暂选号码
  * * @param telnum 电话号码
  * @param region 地区
  * @param operid 操作员Id
  * @param atsvNum 终端机ID
  * @param menuid 当前菜单Id
  * @return tagset 结果集，
  * @since CommonLog1.0
  * @remark create yKF73963 2012-07-17 OR_HUB_201202_1192
  */
 public ReturnWrap chooseTheTelnum(Map paramMap)
 {
     String telnum = (String)paramMap.get("telnum");
     
     String operid = (String)paramMap.get("curOper");
     String atsvNum = (String)paramMap.get("atsvNum");
     String menuid = (String)paramMap.get("curMenuId");
     String region = (String)paramMap.get("region");
     
     Document doc = DocumentHelper.createDocument();
     Element eBody = doc.addElement("message_content");
     // 电话号码
     Element etelnum = eBody.addElement("telnum");
     etelnum.addText(telnum);
    
     Document docXML = intMsgUtil.createMsg(doc, "cli_TelNumTmpPick_hub", menuid, null, "0", region, operid, atsvNum);
     return intMsgUtil.invoke(docXML);
     
 }
 
 
 /**
  * 查询用户可推荐的业务列表_湖北营销推荐活动
  * 
  * @param map
  * @return
  */
 public ReturnWrap qryRecommendProductList(MsgHeaderPO msgHeader)
 {
     try
     {
        // 湖北统一接口平台转EBUS开关开启
         if(IntMsgUtil.isTransEBUS("BLCSGetRecommendProductList"))
         {
             // 出参为一个List
            List<Map> list = new ArrayList<Map>();
            Map<String,String> param1 = new HashMap<String,String>();
            param1.put("prodName", "prodName001");
            param1.put("privName", "commendoid001");
            param1.put("recmdDiction", "privName001");
            param1.put("userSeq", "userSeq001");
            param1.put("actid", "actid001");
            param1.put("oid", "oid001");
            param1.put("entityName", "entityName001");
            param1.put("prodid", "prodid001");
            param1.put("privid", "privid001");
            param1.put("recmdType", "recmdType001");
            param1.put("contactTimes", "contactTimes001");
            param1.put("notes", "notes001");
            param1.put("actName", "actName001");
            param1.put("recmdTimeValue", "recmdTimeValue001");
            param1.put("isFeedBackDef", "1");
            param1.put("type", "typee001");
            
            Map<String,String> param2 = new HashMap<String,String>();
            param2.put("prodName", "prodName002");
            param2.put("privName", "commendoid002");
            param2.put("recmdDiction", "privName002");
            param2.put("userSeq", "userSeq002");
            param2.put("actid", "actid002");
            param2.put("oid", "oid002");
            param2.put("entityName", "entityName002");
            param2.put("prodid", "prodid002");
            param2.put("privid", "privid002");
            param2.put("recmdType", "recmdType002");
            param2.put("contactTimes", "contactTimes002");
            param2.put("notes", "notes002");
            param2.put("actName", "actName002");
            param2.put("recmdTimeValue", "recmdTimeValue002");
            param2.put("isFeedBackDef", "0");
            param2.put("type", "type002");
            
            list.add(param1);
            list.add(param2);
            
            JSONArray jsonArray = JSONArray.fromObject(list);

            // 出参List  取值与输出的List索引位置对应关系数组
            String[] outParamListIndexDefine = {"oid","entityName","recmdDiction","ncode","ncode","privid","userSeq","smsport",
                    "smstempletid","recmdTimeValue","actid","actName","contactTimes","notes","prodName","privName","recmdType",
                    "isFeedBackDef","type"};
            
            return getIntMsgUtil().parseJsonResponse(jsonArray.toString(),null,outParamListIndexDefine);
         }
         
         String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                 + "<menuid></menuid><process_code>cli_qry_recommendProductList</process_code><verify_code></verify_code>"
                 + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                 + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                 + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                 + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                 + "<rewardlist>" +
                        "<commendoid>commendoid001</commendoid>" +
                        "<businame>businame001</businame>" +
                        "<actdict>actdict001</actdict>" +
                        "<ncode>ncode001</ncode>" +
                        "<prodid>prodid001</prodid>" +
                        "<privid>privid001</privid>" +
                        "<userseq>userseq001</userseq>" +
                        "<smsport>smsport001</smsport>" +
                        "<smstempletid>smstempletid001</smstempletid>" +
                        "<commendtimetable>commendtimetable001</commendtimetable>" +
                        "<actid>actid001</actid>" +
                        "<actname>actname001</actname>" +
                        "<commendtime>commendtime001</commendtime>" +
                        "<actcontent>actcontent001</actcontent>" +
                        "<prodname>prodname001</prodname>" +
                        "<privname>privname001</privname>" +
                        "<commendtype>commendtype001</commendtype>" +
                        "<isfeedbackdef>0</isfeedbackdef>" +
                        "<eventtype>even</eventtype>" +
                 "</rewardlist>"+
                 "<rewardlist>" +
                         "<commendoid>commendoid002</commendoid>" +
                         "<businame>businame002</businame>" +
                         "<actdict>actdict002</actdict>" +
                         "<ncode>ncode002</ncode>" +
                         "<prodid>prodid002</prodid>" +
                         "<privid>privid002</privid>" +
                         "<userseq>userseq002</userseq>" +
                         "<smsport>smsport002</smsport>" +
                         "<smstempletid>smstempletid002</smstempletid>" +
                         "<commendtimetable>commendtimetable002</commendtimetable>" +
                         "<actid>actid002</actid>" +
                         "<actname>actname002</actname>" +
                         "<commendtime>commendtime002</commendtime>" +
                         "<actcontent>actcontent002</actcontent>" +
                         "<prodname>prodname002</prodname>" +
                         "<privname>privname002</privname>" +
                         "<commendtype>commendtype002</commendtype>" +
                         "<isfeedbackdef>1</isfeedbackdef>" +
                         "<eventtype></eventtype>" +
                  "</rewardlist>"

                 + "</message_content>]]></message_body></message_response>";
         return intMsgUtil.parseResponse(response);
     }
     catch (Exception e)
     {
         ReturnWrap rtwp = new ReturnWrap();
         rtwp.setStatus(0);
         rtwp.setReturnMsg("");
         
         return rtwp;
     }
 }
 
 /**
  * 记录业务推荐结果接口_湖北营销推荐活动
  * 
  * @param paramMap
  * @return
  * @see [类、类#方法、类#成员]
  */
 public ReturnWrap recommendFeedback(MsgHeaderPO msgHeader, String userSeqs, String status, String actIds, String eventTypes)
 {
     try
     {
         // 湖北统一接口平台转EBUS开关开启
         if(IntMsgUtil.isTransEBUS("BLCSRecommendFeedback"))
         {
            // 出参为键值对，放在JSONObj
            Map<String,Object> outParamMap = new HashMap<String,Object>();
            JSONObject jsonObj = JSONObject.fromObject(outParamMap);
            
            return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
         }
         
         String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                 + "<menuid>qryBill</menuid><process_code>cli_busi_recommendFeedback</process_code><verify_code></verify_code>"
                 + "<resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq>"
                 + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                 + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                 + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                 +"</message_content>]]></message_body></message_response>";
         
         return intMsgUtil.parseResponse(response);
     }
     catch (Exception e)
     {
         ReturnWrap rtwp = new ReturnWrap();
         rtwp.setStatus(0);
         rtwp.setReturnMsg("");
         
         return rtwp;
     }
 }
 
 
 /**
  * 推荐业务受理_湖北营销推荐活动
  * 
  * @param paramMap
  * @return
  * @see [类、类#方法、类#成员]
  */
 public ReturnWrap recommendProduct(MsgHeaderPO msgHeader, String userSeq, String actId, String recmdType)
 {
     try
     {
         // 湖北统一接口平台转EBUS开关开启
         if(IntMsgUtil.isTransEBUS("BLCSRecommendProductByActID"))
         {
            // 出参为键值对，放在JSONObj
            Map<String,Object> outParamMap = new HashMap<String,Object>();
            // outParamMap.put("orderID", "34343434");
            JSONObject jsonObj = JSONObject.fromObject(outParamMap);
            
            return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
         }
         
         String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                 + "<menuid>qryBill</menuid><process_code>cli_busi_recommendProductByActID</process_code><verify_code></verify_code>"
                 + "<resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq>"
                 + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                 + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                 + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                 + "</message_content>]]></message_body></message_response>";
         
         return intMsgUtil.parseResponse(response);
     }
     catch (Exception e)
     {
         ReturnWrap rtwp = new ReturnWrap();
         rtwp.setStatus(0);
         rtwp.setReturnMsg("");
         
         return rtwp;
     }
 }
 
 
 
 /**
  * 推荐业务办理成功，更新业务推荐结果_湖北营销推荐活动
  * 
  * @param paramMap
  * @return
  * @see [类、类#方法、类#成员]
  */
 public ReturnWrap setRecommendSuccess(MsgHeaderPO msgHeader, String commendOID)
 {
     try
     {
         // 湖北统一接口平台转EBUS开关开启
         if(IntMsgUtil.isTransEBUS("BLCSSetRecommendSuccess"))
         {
            // 出参为键值对，放在JSONObj
            Map<String,Object> outParamMap = new HashMap<String,Object>();
            JSONObject jsonObj = JSONObject.fromObject(outParamMap);
            
            return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
         }
         
         String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                 + "<menuid>qryBill</menuid><process_code>cli_busi_setRecommendSuccess</process_code><verify_code></verify_code>"
                 + "<resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq>"
                 + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                 + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                 + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                 + "</message_content>]]></message_body></message_response>";
         
         return intMsgUtil.parseResponse(response);
     }
     catch (Exception e)
     {
         ReturnWrap rtwp = new ReturnWrap();
         rtwp.setStatus(0);
         rtwp.setReturnMsg("");
         
         return rtwp;
     }
 }
 
    /** 
     * 查询回馈定义信息列表
     * 
     * @param msgHeader 报文头信息
     * @param actId 业务推荐活动编码
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public ReturnWrap qryFeedBackDefList(MsgHeaderPO msgHeader, String actId)
    {
        try
        {
            // 湖北统一接口平台转EBUS开关开启
            if(IntMsgUtil.isTransEBUS("BLCSGetFeedBackDef"))
            {
                Map<String,Object> outParamMap = new HashMap<String,Object>();
                
                outParamMap.put("actID", "bb");
                
                outParamMap.put("actinfo", "20121229;20121229;10005402818165|20000306880396");
                
                outParamMap.put("feedbackStatus", "1");
                
                outParamMap.put("isNeedFeedback", "0");
                
                outParamMap.put("isNotify", "1");
                
                outParamMap.put("moContent", "2");
                
                outParamMap.put("msgText", "营销");
                
                outParamMap.put("nCode", "");
                
                outParamMap.put("recmdName", "反馈活动1");
                
                String[] arrAttrsKey = {"recmdName", "nCode", "actinfo", "moContent"};
                
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                
                list.add(outParamMap);
                JSONArray jsonObj = JSONArray.fromObject(outParamMap);
                
                
                return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,arrAttrsKey);
            }

            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>cli_qry_getFeedBackDefList</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                + "<feedbacklist><recmdname>推荐名称</recmdname><ncode>推荐ncode</ncode><actinfo>推荐活动信息</actinfo><mocontent>回复内容</mocontent></feedbacklist>"
                + "<feedbacklist><recmdname>recmdname001</recmdname><ncode>ncode001</ncode><actinfo>actinfo001</actinfo><mocontent>1</mocontent></feedbacklist>"
                + "<feedbacklist><recmdname>recmdname002</recmdname><ncode>ncode002</ncode><actinfo>actinfo002</actinfo><mocontent>1</mocontent></feedbacklist>"
                + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
 
    /** 
     * 回馈定义受理
     * 
     * @param telNum 用户手机号码
     * @param operId 终端机操作员
     * @param termId 终端机编号
     * @param touchOID
     * @param menuId 菜单Id
     * @param actId 业务推荐活动编码
     * @param contents 回复内容
     * @param recmdType 推荐类型
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public ReturnWrap doFeedBackDef(MsgHeaderPO msgHeader, String actId, String contents, String recmdType)
    {
        try
        {
            if(IntMsgUtil.isTransEBUS("BLCSRecommendProdByResponse"))
            {
                Map<String,Object> outParamMap = new HashMap<String,Object>();
                JSONObject jsonObj = JSONObject.fromObject(outParamMap);
                
                return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>qryBill</menuid><process_code>cli_busi_recommendProdByReply</process_code><verify_code></verify_code>"
                + "<resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }

    /**
     * 铁通缴费
     * @param map
     * @return
     */
    public ReturnWrap wbankpay(Map<String, String> map, MsgHeaderPO msgHead)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><Envelope><Header><request_head version=\"1.0\">"
            + "<menuid>qryServiceHistory</menuid><process_code>cli_qry_rechistory</process_code>"
            + "<verify_code></verify_code><resp_time>20100921002233</resp_time>"
            + "<sequence><req_seq>40</req_seq><operation_seq></operation_seq></sequence><retinfo>"
            + "<rettype>0</rettype><retcode>100</retcode>"
            + "<retmsg><![CDATA[Processing the request succeeded!]]>"
            + "</retmsg></retinfo></request_head></Header>"
            + "<Body>"
            + "<cli_busi_wbandrecresp>"
            + "<tagset>"
            + "<dealNum>dealNum001</dealNum>"
            + "</tagset>"
            + "</cli_busi_wbandrecresp>"
            + "</Body></Envelope>";
        
        try {
            return intMsgUtilNew.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);

            return rtwp;
        }
    }
    /**
     * 资助终端账单协同查询之139email
     * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
     */
     public ReturnWrap billColQuery139Email(Map map)
     {   String region=(String)map.get("region");
         String telnum = (String)map.get("SERVNUM");
         String qrytype="0";
         String sendtype="1";
         String triggertype="1";
         String channel="102";
         String modelid="he1002";
         String sendprior="01";
         String busicode="ngsendmail";
         String starttime=DateUtilHub._getCurrentTime();
         String statustime=DateUtilHub._getCurrentTime();
         String isqrybill="0";
         String cycle = (String)map.get("CYCLEMONTH");
         String menuID = (String)map.get("menuID");
         String touchOID = (String)map.get("touchOID");
         String operID = (String)map.get("operID");
         String termID = (String)map.get("termID");
         
         try
         {
             IntMsgUtil imu = new IntMsgUtil();
             Document docXML = null;
             Document doc = DocumentHelper.createDocument();
             Element eBody = doc.addElement("message_content");
             Element eReq_region = eBody.addElement("region");
             eReq_region.addText(region);
             Element eReq_telnum = eBody.addElement("telnum");
             eReq_telnum.addText(telnum);
             Element eReq_qrytype = eBody.addElement("qrytype");
             eReq_qrytype.addText(qrytype);
             Element eReq_sendtype = eBody.addElement("sendtype");
             eReq_sendtype.addText(sendtype);
             Element eReq_triggertype = eBody.addElement("triggertype");
             eReq_triggertype.addText(triggertype);
             Element eReq_channel = eBody.addElement("channel");
             eReq_channel.addText(channel);
             Element eReq_modelid = eBody.addElement("modelid");
             eReq_modelid.addText(modelid);
             Element eReq_sendprior = eBody.addElement("sendprior");
             eReq_sendprior.addText(sendprior);
             Element eReq_busicode = eBody.addElement("busicode");
             eReq_busicode.addText(busicode);
             Element eReq_mailaddr = eBody.addElement("mailaddr");
             Element eReq_starttime = eBody.addElement("starttime");
             eReq_starttime.addText(starttime);
             Element eReq_statustime = eBody.addElement("statustime");
             eReq_statustime.addText(statustime);
             Element eReq_isqrybill = eBody.addElement("isqrybill");
             eReq_isqrybill.addText(isqrybill);
             Element eReq_cycle = eBody.addElement("cycle");
             eReq_cycle.addText(cycle);
             Element eReq_billinfo = eBody.addElement("billinfo");
             docXML = imu.createMsg(doc, "cli_sendbillby139mail_hub", menuID, touchOID, "1", telnum, operID, termID);
             
             return imu.invoke(docXML);
         }
         catch (Exception e)
         {
             ReturnWrap rtwp = new ReturnWrap();
             rtwp.setStatus(0);
             rtwp.setReturnMsg("");
             
             return rtwp;
         }
     }
     /**
      * 资助终端账单协同查询之短信
      * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
      */
      public ReturnWrap billColQueryMessage(Map map)
      {
          String telnum = (String)map.get("SERVNUM");
          String billcycle = (String)map.get("CYCLEMONTH");
          String menuID = (String)map.get("menuID");
          String touchOID = (String)map.get("touchOID");
          String operID = (String)map.get("operID");
          String termID = (String)map.get("termID");
          
          try
          {
              IntMsgUtil imu = new IntMsgUtil();
              Document docXML = null;
              
              Document doc = DocumentHelper.createDocument();
              Element eBody = doc.addElement("message_content");
              
              Element eReq_telnum = eBody.addElement("telnum");
              eReq_telnum.addText(telnum);
              
              Element eReq_billcycle = eBody.addElement("billcycle");
              eReq_billcycle.addText(billcycle);
              
              docXML = imu.createMsg(doc, "bsacSms_cli_sms_qry_bill2012", menuID, touchOID, "1", telnum, operID, termID);
              
              return imu.invoke(docXML);
          }
          catch (Exception e)
          {
              ReturnWrap rtwp = new ReturnWrap();
              rtwp.setStatus(0);
              rtwp.setReturnMsg("");
              
              return rtwp;
          }
      }
      /**
       * 资助终端账单协同查询之彩信
       * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
       */
       public ReturnWrap billColQueryColorMessage(Map map)
       {
           String telnum = (String)map.get("SERVNUM");
           String billcycle = (String)map.get("CYCLEMONTH");
           String menuID = (String)map.get("menuID");
           String touchOID = (String)map.get("touchOID");
           String operID = (String)map.get("operID");
           String termID = (String)map.get("termID");
           
           try
           {
               IntMsgUtil imu = new IntMsgUtil();
               Document docXML = null;
               
               Document doc = DocumentHelper.createDocument();
               Element eBody = doc.addElement("message_content");
               
               Element eReq_telnum = eBody.addElement("telnum");
               eReq_telnum.addText(telnum);
               
               Element eReq_billcycle = eBody.addElement("billcycle");
               eReq_billcycle.addText(billcycle);
               
               docXML = imu.createMsg(doc, "cli_sms_qry_mmsbilldetail", menuID, touchOID, "1", telnum, operID, termID);
               
               return imu.invoke(docXML);
           }
           catch (Exception e)
           {
               ReturnWrap rtwp = new ReturnWrap();
               rtwp.setStatus(0);
               rtwp.setReturnMsg("");
               
               return rtwp;
           }
       }
       /**
        *免费抽奖
        * @remark create yKF73963 （2012-11-09） OR_HUB_201209_706  电子渠道-抽奖平台-抽奖接口改造 
        */
        public ReturnWrap mianFeiChouJiang(Map map)
        {   String actId=(String)map.get("ACTID");
            String telnum = (String)map.get("SERVNUM");
        
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            try
            {
                IntMsgUtil imu = new IntMsgUtil();
                Document docXML = null;
                Document doc = DocumentHelper.createDocument();
                Element eBody = doc.addElement("message_content");
               
                Element eReq_telnum = eBody.addElement("TELNUM");
                eReq_telnum.addText(telnum);
              
                Element eReq_actId = eBody.addElement("ACTID");
                eReq_actId.addText(actId);
                docXML = imu.createMsg(doc, "cli_sendbillby139mail_hub", menuID, touchOID, "1", telnum, operID, termID);
                
                return imu.invoke(docXML);
            }
            catch (Exception e)
            {
                ReturnWrap rtwp = new ReturnWrap();
                rtwp.setStatus(0);
                rtwp.setReturnMsg("");
                
                return rtwp;
            }
        }
        /**
         * 电子券返还信息查询
         * 
         * @param [参数1] [参数1说明]
         * @param [参数2] [参数2说明]
         * @return [返回类型说明]
         * @exception/throws [违例类型] [违例说明]
         * @see [类、类#方法、类#成员]
         * @depreced
         * @remark create yKF73963（2013-03-18） OR_HUB_201301_780  关于BOSS触发手机支付电子券的分月赠送的需求 
         * 
         */
         public ReturnWrap qryEcashReturnInfo(Map map)
         {
         
             String startDate = (String)map.get("startDate");
             String endDate = (String)map.get("endDate");
             String operid = (String)map.get("curOper");
             String atsvNum = (String)map.get("atsvNum");
             String telnumber = (String)map.get("servnumber");
             String touchoid = (String)map.get("touchoid");
             String menuid = (String)map.get("curMenuId");
             String region = (String)map.get("region");
           
             
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
                 //地区
                 Element eReq_region = eBody.addElement("region");
                 eReq_region.addText(region);
                 Document docXML = intMsgUtil.createMsg(doc,
                         "cli_qry_ecashReturnInfo_hub",
                         menuid,
                         touchoid,
                         "1",
                         telnumber,
                         operid,
                         atsvNum);
                 return intMsgUtil.invoke(docXML);
             }
             catch (Exception e)
             {
                
                 
                 ReturnWrap rtwp = new ReturnWrap();
                 rtwp.setStatus(0);
                 rtwp.setReturnMsg("查询电子券返还返还信息出现异常！");
                 
                 return rtwp;
             }
         }
         
         /** 
          * 上网流量受理
          * 
          * @param msgHeader 报文头信息
          * @param productset 开通增值产品串
          * @return ReturnWrap
          * @see [类、类#方法、类#成员]
          * @remark create cKF76106 2013-05-14 OR_HUB_201305_29
          */
         public ReturnWrap gprsWlanRec(MsgHeaderPO msgHeader, String productset)
         {
             try
             {
                 // 湖北统一接口平台转EBUS开关开启
                 if (IntMsgUtil.isTransEBUS("BLCSChgOrChkMainProd"))
                 {
                     // 调用服务接口
                     return getIntMsgUtil().parseJsonResponse("[{\"editStatus\":\"4\",\"effectTime\":\"2014-09-25 09:46:48\",\"entityID\":\"G211039\",\"objType\":\"PRODUCT\"},{\"editStatus\":\"0\",\"effectTime\":\"2014-09-25 09:46:48\",\"entityID\":\"G221772\",\"objType\":\"PRODUCT\"},{\"editStatus\":\"0\",\"effectTime\":\"2014-09-25 09:46:48\",\"entityID\":\"00.2010043001\",\"objType\":\"PRIVILEGE\"}]", 
                             null, null);
                 }
                 
                 String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
                        "<menuid>recMainProdChange</menuid><process_code>cli_busi_GprsWlanRec</process_code><verify_code>" +
                        "</verify_code><resp_time>20130514140538</resp_time><sequence><req_seq>1</req_seq><operation_seq>" +
                        "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" +
                        "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response> ";
                 return intMsgUtil.parseResponse(response);
             }
             catch (Exception e)
             {
                 ReturnWrap rtwp = new ReturnWrap();
                 rtwp.setStatus(0);
                 rtwp.setReturnMsg("");
                 
                 return rtwp;
             }
         }
         
         /**
          * 数据业务受理
          * 
          * @param msgHeader 报文头信息
          * @param spbizid sp业务编码
          * @param spid 企业编码
          * @return ReturnWrap
          * @see [类、类#方法、类#成员]
          * @remark create cKF76106 2013-05-14 OR_HUB_201305_29
          */
         public ReturnWrap spRec(MsgHeaderPO msgHeader, String spbizid, String spid)
         {
             try
             {
                 // 湖北统一接口平台转EBUS开关开启
                 if (IntMsgUtil.isTransEBUS("BLCSChangeSubsMonServ"))
                 {
                     return getIntMsgUtil().parseJsonResponse("{orderResult:'0',effetiTime:'2014-04-15',orderFlag:'1',formNumber:'20140115000001'}", 
                         new String[][]{{"orderResult", "formNumber"},{"orderresult", "formnum"}}, null);
                 }
                 
                 String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
                        "<menuid>recMainProdChange</menuid><process_code>cli_busi_GprsWlanRec</process_code><verify_code>" +
                        "</verify_code><resp_time>20130514140538</resp_time><sequence><req_seq>1</req_seq><operation_seq>" +
                        "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" +
                        "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response> ";
                 return intMsgUtil.parseResponse(response);
             }
             catch (Exception e)
             {
                 ReturnWrap rtwp = new ReturnWrap();
                 rtwp.setStatus(0);
                 rtwp.setReturnMsg("");
                 
                 return rtwp;
             }
         }
    
     /**
      * 向用户发送随机密码短信
      * 
      * @param map
      * @return
      */
     public ReturnWrap sendSmsHub(Map map) {
         ReturnWrap rtwp = new ReturnWrap();
         rtwp.setStatus(SSReturnCode.SUCCESS);

         return rtwp;
     }
     
    public IntMsgUtilNew getIntMsgUtilNew()
    {
        return intMsgUtilNew;
    }

    public void setIntMsgUtilNew(IntMsgUtilNew intMsgUtilNew)
    {
        this.intMsgUtilNew = intMsgUtilNew;
    }

    @Override
    public ReturnWrap getMonInvoiceData(MsgHeaderPO msgHeader, CyclePO cycle) {
        try
        {
            // 湖北转EBUS开关开启
            if (IntMsgUtil.isTransEBUS("BLARIntPrintAddValueBillInv"))
            {
                String response = "{'billInvoice':[{ 'invoiceInfo':'全球通:积极、掌控、品位'," 
                    + "'invoiceTypeItem':'productgrp'},{'invoiceInfo':'0','invoiceTypeItem': 'Score'},    "
                    + "{'invoiceInfo':'6年4月','invoiceTypeItem':'custnetage'}," 
                    + "{'invoiceInfo':'姓名13908686600','invoiceTypeItem':'username'},"
                    + "{'invoiceInfo':'13908686600(鄂州)','invoiceTypeItem':'telnumber'},"
                    + "{'invoiceInfo':'7119000825245','invoiceTypeItem':'paynumno'}," 
                    + "{'invoiceInfo':'711140919406408357','invoiceTypeItem':'formnum'}," 
                    + "{'invoiceInfo':'鄂州','invoiceTypeItem':'WorkStation'}," 
                    + "{'invoiceInfo':'101','invoiceTypeItem':'CollectOper'},"
                    + "{'invoiceInfo':'2014','invoiceTypeItem' : 'printyear'},"
                    + "{'invoiceInfo':'09','invoiceTypeItem':'printmonth'}," 
                    + "{'invoiceInfo':'19','invoiceTypeItem':'printday'},"
                    + "{'invoiceInfo':'2014年04月话费','invoiceTypeItem':'feetype'},"
                    + "{'invoiceInfo':'壹拾叁圆壹角肆分','invoiceTypeItem':'invoicefeedx'},"
                    + "{'invoiceInfo':'13.14元','invoiceTypeItem':'invoicefee'},"
                    + "{'invoiceInfo':'通信服务费：13.14元','invoiceTypeItem':'invoicefeehj'},"
                    + "{'invoiceInfo':'本次发票金额: 13.14元','invoiceTypeItem':'thisinvamt'},"
                    + "{'invoiceInfo':'打印发票时间：','invoiceTypeItem':'hz_printtime'}," 
                    + "{'invoiceInfo':'09:50:39','invoiceTypeItem':'printtime'},"
                    + "{'invoiceInfo':'湖北发票','invoiceTypeItem':'note'}]}";
                ReturnWrap rtwp = intMsgUtil.parseJsonResponse(response, null, new String[]{"invoiceTypeItem", "invoiceInfo"});
                if (SSReturnCode.SUCCESS == rtwp.getStatus())
                {
                    if (rtwp.getReturnObject() instanceof Vector)
                    {
                        Vector v = (Vector) rtwp.getReturnObject();
                        rtwp.setReturnObject((CRSet)v.get(1));
                    }
                }
                return rtwp;
            }
            // 正确的返回报文
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
                    "<message_response>" +
                    "<message_head version=\"1.0\">" +
                    "<menuid>recMonthInvoice</menuid>" +
                    "<process_code>cli_qry_monthinvoiceinfo_sd</process_code>" +
                    "<verify_code></verify_code>" +
                    "<resp_time>20140520191951</resp_time>" +
                    "<sequence>" +
                    "<req_seq>2</req_seq>" +
                    "<operation_seq></operation_seq>" +
                    "</sequence>" +
                    "<retinfo>" +
                    "<rettype>0</rettype>" +
                    "<retcode>0</retcode>" +
                    "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg>" +
                    "</retinfo>" +
                    "</message_head>" +
                    "<message_body>" +
                    "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
                    "<message_content>" +
                    "<invoicelist><name>productgrp</name><value>全球通:积极、掌控、品位</value></invoicelist>" +
                    "<invoicelist><name>Score</name><value>0</value></invoicelist>" +
                    "<invoicelist><name>username</name><value>徐剑平0612</value></invoicelist>" +
                    "<invoicelist><name>custnetage</name><value>11年9月</value></invoicelist>" +
                    "<invoicelist><name>telnumber</name><value>13972963980</value></invoicelist>" +
                    "<invoicelist><name>paynumno</name><value>7110005037647</value></invoicelist>" +
                    "<invoicelist><name>formnum</name><value>20091128448092</value></invoicelist>" +
                    "<invoicelist><name>WorkStation</name><value>鄂州</value></invoicelist>" +
                    "<invoicelist><name>CollectOper</name><value>1301818</value></invoicelist>" +
                    "<invoicelist><name>printyear</name><value>2014</value></invoicelist>" +
                    "<invoicelist><name>printmonth</name><value>06</value></invoicelist>" +
                    "<invoicelist><name>printday</name><value>19</value></invoicelist>" +
                    "<invoicelist><name>feetype</name><value>2014年03月话费</value></invoicelist>" +
                    "<invoicelist><name>invoicefeedx</name><value>捌拾捌圆壹角贰分</value></invoicelist>" +
                    "<invoicelist><name>invoicefee</name><value>88.12元</value></invoicelist>" +
                    "<invoicelist><name>invoicefeehj</name><value>通信服务费：103.12元</value></invoicelist>" +
                    "<invoicelist><name>presentused</name><value>赠送消费: 15.00元</value></invoicelist>" +
                    "<invoicelist><name>thisinvamt</name><value>本次发票金额: 88.12元</value></invoicelist>" +
                    "<invoicelist><name>hz_printtime</name><value>打印发票时间：</value></invoicelist>" +
                    "<invoicelist><name>printtime</name><value>09:32:23</value></invoicelist>" +
                    "<invoicelist><name>note</name><value></value></invoicelist>" +
                    "</message_content>]]>" +
                    "</message_body></message_response>";
            
            // 未完全销账的异常返回报文
            /*String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
                    "<message_response>" +
                    "<message_head version=\"1.0\">" +
                    "<menuid>recMonthInvoice</menuid>" +
                    "<process_code>cli_qry_monthinvoiceinfo_sd</process_code>" +
                    "<verify_code></verify_code>" +
                    "<resp_time>20140520152439</resp_time>" +
                    "<sequence><req_seq>4</req_seq><operation_seq></operation_seq></sequence>" +
                    "<retinfo>" +
                    "<rettype>0</rettype>" +
                    "<retcode>966391</retcode>" +
                    "<retmsg><![CDATA[帐号：[6348888934481]、帐期：[20140101]，您本月账单没有完全销账，不能打印此帐期发票！]]></retmsg>" +
                    "</retinfo>" +
                    "</message_head></message_response>";*/
            
            // 已打印过的发票异常返回报文
            /*String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
                    "<message_response>" +
                    "<message_head version=\"1.0\">" +
                    "<menuid>recMonthInvoice</menuid>" +
                    "<process_code>cli_qry_monthinvoiceinfo_sd</process_code>" +
                    "<verify_code></verify_code>" +
                    "<resp_time>20140520154904</resp_time>" +
                    "<sequence>" +
                    "<req_seq>4</req_seq>" +
                    "<operation_seq></operation_seq>" +
                    "</sequence>" +
                    "<retinfo>" +
                    "<rettype>0</rettype>" +
                    "<retcode>966515</retcode>" +
                    "<retmsg><![CDATA[打印次数已经达到最大打印次数，请复位后，再次打印！]]></retmsg>" +
                    "</retinfo>" +
                    "</message_head></message_response>";*/
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /** 
     * 开户时证件号码校验
     * 
     * @param msgHeader 报文头信息
     * @param certType 证件类型
     * @param certId 证件号码
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkCertSubs(MsgHeaderPO msgHeader, String certType, String certId)
    {
        try
        {
            String response = "{\"count\":0,\"ifValid\":\"1\"}";
            return intMsgUtil.parseJsonResponse(response, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"开户时证件号码校验接口调用异常");
        }
    }
    
    /** 
     * 依据选号规则查询手机号码列表
     * 
     * @param msgHeader 报文头信息
     * @param orgId 组织机构
     * @param selTelRule 选号规则
     * @param telPrefix 号码前缀
     * @param telSuffix 号码后缀
     * @param mainProdid 主体产品ID
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryTelNumberListByRule(MsgHeaderPO msgHeader, String orgId, String selTelRule,
        String telPrefix, String telSuffix, String mainProdid)
    {
        try
        {
            String response = "{\"retCode\":1,\"retMsg\":\"\",\"telNumlist\":[" +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655557169\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655557176\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655557075\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655557068\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556993\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556885\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556777\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556791\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556683\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556265\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556373\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556366\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556474\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556467\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556481\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556575\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556568\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556669\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555981\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555974\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555967\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556070\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556063\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556056\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556171\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556164\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556272\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555462\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555455\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555448\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555549\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555570\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555563\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555556\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555671\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555664\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555657\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555772\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555765\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555758\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555873\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555866\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555859\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556676\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556784\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556892\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556878\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556986\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556979\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655557082\",\"telnumGrade\":\"2\"}]}";
            
            String[] outParamKeyDefine = {"telnum","brand","price","orgid","lowConsumFee","lowConsumPre"};
            return intMsgUtil.parseJsonResponse(response, null, outParamKeyDefine);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"作废卡接口调用异常");
        }
    }
    
    /** 
     * 号码资源暂选接口
     * 
     * @param msgHeader 
     * @param telNum 手机号码
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap telNumTmpPick(MsgHeaderPO msgHeader, Map<String,String> inParamMap)
    {
        try
        {
            return CommonUtil.getReturnWrap(SSReturnCode.SUCCESS,"号码资源暂选成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"号码资源暂选接口调用异常");
        }
    }
    
    /** 
     * 校验空白卡资源是否可用
     * 
     * @param msgHeader 报文头信息
     * @param blankNo 空白卡卡号
     * @param orgId 组织机构
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkBlankNo(MsgHeaderPO msgHeader, Map<String,String> inParamMap)
    {
        try
        {
            return CommonUtil.getReturnWrap(SSReturnCode.SUCCESS,"校验空白卡资源是否可用成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"校验空白卡资源是否可用接口调用异常");
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
    public ReturnWrap chkPreSetBlankCard(MsgHeaderPO msgHeader, String blankNo, String telNum)
    {
        try
        {
            
            String response = "{\"busiStatus\":\"INVALID\",\"isPresetBlankCard\":\"TRUE\"," +
            		"\"orgID\":\"HB.EZ\",\"presetBlankCardType\":\"\",\"resTypeId\":\"rsclW.PSAM001\",\"retMsg\":\"\"}";
            String[][] outParamKeyDefine = {{"isPresetBlankCard","resTypeId"},{"isPresetBlankCard","resTypeId"}};
            return intMsgUtil.parseJsonResponse(response, outParamKeyDefine, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"校验空白卡是否是预置空卡接口调用异常");
        }
    }

    
    /** 
     * 空白卡资源暂选
     * 
     * @param msgHeader 报文头信息
     * @param blankNo 空白卡卡号
     * @param telNum 手机号码
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap blankCardTmpPick(MsgHeaderPO msgHeader, String blankNo, String telNum)
    {
        try
        {
            String response = "{\"atr\":\"\",\"cardType\":\"\"," +
            		"\"eki\":\"FBA690E6944D405579134357B2C26C76\"," +
            		"\"iccID\":\"898600A1178407806261\",\"imsi\":\"460077178083738\"," +
            		"\"pin1\":\"1234\",\"pin2\":\"0731\",\"puk1\":\"28405510\",\"puk2\":\"41134844\"," +
            		"\"smsp\":\"+8613800711500\"}";
            return intMsgUtil.parseJsonResponse(response, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"空白卡资源暂选接口调用异常");
        }

    }
    /** 
     * 校验空白卡资源是否可用
     * 
     * @param msgHeader 报文头信息
     * @param inParamMap 入参
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
//    public ReturnWrap chkBlankNo(MsgHeaderPO msgHeader, Map<String,String> inParamMap)
//    {
//        try
//        {
//            return intMsgUtil.invokeDubbo("BLCSCheckSaleRealSign", msgHeader, inParamMap);
//        }
//        catch (Exception e)
//        {
//            
//            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"校验空白卡资源是否可用接口调用异常");
//        }
//    }
//
//    /** 
//     * 空白卡资源暂选
//     * 
//     * @param msgHeader 报文头信息
//     * @param blankNo 空白卡卡号
//     * @param telNum 手机号码
//     * @return ReturnWrap
//     * @see [类、类#方法、类#成员]
//     */
//    public ReturnWrap blankCardTmpPick(MsgHeaderPO msgHeader, String blankNo, String telNum)
//    {
//        try
//        {
//            // 入参
//            Map<String,String> inParamMap = new HashMap<String,String>();
//            
//            // 空白卡号
//            inParamMap.put("blankCardNo", blankNo);
//            
//            // 手机号码
//            inParamMap.put("telNum", telNum);
//            
//            return intMsgUtil.invokeDubbo("BLCSGetPersonalData", msgHeader, inParamMap);
//        }
//        catch (Exception e)
//        {
//            
//            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"空白卡资源暂选调用异常");
//        }
//
//    }
//    
//    /** 
//     * 校验空白卡是否是预置空卡
//     * 
//     * @param termInfo 终端信息
//     * @param curMenuId 当前菜单ID
//     * @param blankNo 空白卡序列号
//     * @param telNum 手机号码
//     * @see [类、类#方法、类#成员]
//     */
//    public ReturnWrap chkPreSetBlankCard(MsgHeaderPO msgHeader, String blankNo, String telNum)
//    {
//        try
//        {
//            Map<String,String> inParamMap = new HashMap<String,String>();
//            
//            // 空白卡号
//            inParamMap.put("blankCardNo", blankNo);
//            
//            // 手机号
//            inParamMap.put("severNumber", telNum);
//            
//            // 是否返回预置数据（可以传0，如果传0，后面出参presetData是空的）
//            inParamMap.put("retPreSetData", "0");
//            
//            // 预置空卡鉴权数据次序号（传空）
//            inParamMap.put("dataSeq", "");
//
//            // 是否是预置空卡，空白卡资源
//            String[][] outParamKeyDefine = {{"isPresetBlankCard","resTypeId"},{"isPresetBlankCard","resTypeId"}};
//            
//            return intMsgUtil.invokeDubbo("BLCSChkPreSetBlankCard", msgHeader, inParamMap,outParamKeyDefine);
//        }
//        catch (Exception e)
//        {
//            
//            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"校验空白卡是否是预置空卡接口调用异常");
//        }
//    }
    
    /**
     * 查询账期（湖北）
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_HUB_201405_829_湖北_[营改增]自助终端提供增值税月结发票打印
     */
    @Override
    public ReturnWrap qryBillCycle(MsgHeaderPO msgHeader, String billCycle)
    {
        try
        {
            if (IntMsgUtil.isTransEBUS("BLARBillCycleCustInfo"))
            {
                String response = "{'cycList':[{'acctID':'','brandName':'全球通',"
                    + "'custName':'刘鑫test','cycle':'201407','endDate':'2014-07-31',"
                    + "'isUnion':'0','productName':'4G飞享套餐338元档','region':'711'," 
                    + "'regionName':'鄂州','startDate':'2014-07-01','subsCredit':'-1'," 
                    + "'subsID':'7115183834310','unSubsNum':0}],'errCode':'0'}";
                return intMsgUtil.parseJsonResponse(response, null, 
                        new String[]{"cycle","startDate","endDate","acctID","isUnion"});
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>recMonthInvoice</menuid><process_code>cli_qry_billCycleCustInfo</process_code><verify_code></verify_code>"
                + "<resp_time>20100921004740</resp_time><sequence><req_seq>89</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                
                // 客户名称
                + "<custname>闫强</custname>"
                
                // 品牌
                + "<brandnm>神州行</brandnm>"
                
                // 主体产品
                + "<productnm>主体产品</productnm>"
                
                // 地区名称
                + "<regionname>山东</regionname>"
                
                // 用户ID
                + "<userid>10000000000001</userid>"
                
                // 用户等级
                + "<creditlevel>3</creditlevel>"
                
                // 账期信息 
                
                // 帐期+开始时间+结束时间+主账号+是否合户用户
                + "<cyclelist><cycle>20120601</cycle><startdate>2012-06-01</startdate><enddate>2012-06-30</enddate><acctid>10000001</acctid><unionacct>1</unionacct><unionacct>2</unionacct></cyclelist>"
                
                + "</message_content>]]></message_body></message_response>";
                
            ReturnWrap rtwp = null;   
        
            rtwp = intMsgUtil.parseResponse(response);
            return rtwp;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 补卡时检验手机号码和身份证信息是否一致
     * @param msgHeader
     * @param idCardNo
     * @return
     * @remark create by sWX219697 2014-10-25 13:59:39 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap checkReissueIdCard(MsgHeaderPO msgHeader, String idCardNo) 
    {
        // 出参为键值对，放在JSONObj
        Map<String,Object> outParamMap = new HashMap<String,Object>();

        // 拼装出参
        outParamMap.put("ifValid", "1");
        
        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        
        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(), null, null);
    }

    /**
     * 校验用户的补卡次数
     * @param msgHeader
     * @return
     * @remark create by sWX219697 2014-10-25 14:12:49 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap checkReissueNum(MsgHeaderPO msgHeader, String subscriber) 
    {
        // 出参为键值对，放在JSONObj
        Map<String,Object> outParamMap = new HashMap<String,Object>();

        // 拼装出参
        outParamMap.put("retInfo", "true");
        
        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        
        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(), null, null);
    }
    
    /**
     * 获取写卡信息加密数据
     * @param msgHead 公共消息头
     * @param inParamMap 入参
     * @return ReturnWrap
     */
    public ReturnWrap getEncryptedData(MsgHeaderPO msgHead,Map<String,Object> inParamMap)
    {
        try
        {
            Map<String,Object> outParamMap = new HashMap<String,Object>();
            
            outParamMap.put("issueData", "ABCABCABCCCCCC");
            
            JSONObject jsonObj = JSONObject.fromObject(outParamMap);
            
            return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"获取写卡信息加密数据异常");
        }
    }
    
    /**
     * <补卡算费>
     * <功能详细描述>
     * @param msgHeader
     * @param iccid
     * @param blankCardNum 空白卡卡号
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-10-28 16:45:41 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap countReissueFee(MsgHeaderPO msgHeader, String iccid, String blankCardNum)
    {
    	String response = "[{\"chargeItemID\":\"Prepay635620417\",\"chargePrivID\":\"\"," +
    			"\"chargeProdID\":\"\",\"chargeRequest\":\"\",\"chargeType\":0,\"discPrivID\":\"\"," +
    			"\"discount\":0,\"endDate\":\"\",\"entityID\":\"7110000000555\",\"entityType\":\"CSubscriber\"," +
    			"\"extTag\":{},\"fee\":50,\"feeID\":\"Prepay\",\"feeName\":\"补卡预存\",\"fromSubjectID\":\"\"," +
    			"\"oid\":\"20089219053152\",\"prePayTagParam\":{},\"remitType\":\"\",\"startDate\":\"\"," +
    			"\"subjectID\":\"acsbAdv\",\"subjectOid\":\"\",\"subsPrivOid\":\"\"," +
    			"\"toBillCode\":\"\"},{\"chargeItemID\":\"InsSimReUse\",\"chargePrivID\":\"\"," +
    			"\"chargeProdID\":\"\",\"chargeRequest\":\"\",\"chargeType\":0,\"discPrivID\":\"\"," +
    			"\"discount\":0,\"endDate\":\"2014-11-10 11:45:02\",\"entityID\":\"7110000000555\"," +
    			"\"entityType\":\"CSubscriber\",\"extTag\":{\"cqRwdActChargeProdID\":\"\"," +
    			"\"endResID\":\"17711000000000000946\",\"firstDisc\":\"0\",\"quantity\":\"1\"," +
    			"\"resTypeID\":\"rsclW.PSAM001\",\"startResID\":\"17711000000000000946\"," +
    			"\"tgchgOffset\":\"0\"},\"fee\":100,\"feeID\":\"SIM\",\"feeName\":\"老号重启专用卡(装机)\"," +
    			"\"fromSubjectID\":\"\",\"oid\":\"20089219053162\",\"prePayTagParam\":{}," +
    			"\"remitType\":\"\",\"startDate\":\"2014-11-10 11:45:02\",\"subjectID\":\"\"," +
    			"\"subjectOid\":\"\",\"subsPrivOid\":\"\",\"toBillCode\":\"\"}]";
    	
    	String[] str = {"fee","feeName","chargeType"};
    	
    	return getIntMsgUtil().parseJsonResponse(response,null,str);
    }
    
    /**
     * <补卡提交>
     * <功能详细描述>
     * @param msgHeader
     * @param recFee 应缴费用
     * @param payType 支付方式
     * @param blankno 空白卡卡号     
     * @param simInfo sim卡信息
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-10-30 13:57:27 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap reissueCommit(MsgHeaderPO msgHeader, String recFee, String payType, 
            String blankno, SimInfoPO simInfo)
    {
        Map<String,Object> outParamMap = new HashMap<String,Object>();
        
        //流水号
        outParamMap.put("recOid", "123455666646");
        
        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        
        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
    }
    
    /**
     * 空白卡开户
     * @param msgHead 公共消息头
     * @param map 入参
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户)
     */
    public ReturnWrap terminalInstall(MsgHeaderPO msgHead,Map<String, String> inParamMap)
    {
        try
        {
            Map<String,Object> outParamMap = new HashMap<String,Object>();
            
            // 开户流水
            outParamMap.put("installFormNum", "20141031102035");
            
            // 业务OID
            outParamMap.put("oid", "20141031102035");
            
            JSONObject jsonObj = JSONObject.fromObject(outParamMap);
            
            return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"预置空白卡开户异常");
        }
    }
    
    /**
     * 作废卡接口
     * @param msgHead 公共消息头
     * @param map 入参
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户)
     */
    public ReturnWrap updateWriteCardResult(MsgHeaderPO msgHead,Map<String, String> inParamMap)
    {
        try
        {
            return CommonUtil.getReturnWrap(SSReturnCode.SUCCESS,"作废卡接口调用成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"作废卡接口调用异常");
        }
    }
    
    /**
     * 校验写卡结果接口
     * @param msgHead 公共消息头
     * @param map 入参
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户)
     */
    public ReturnWrap checkWriteCardInfo(MsgHeaderPO msgHead,Map<String, String> inParamMap)
    {
        try
        {
            Map<String,Object> outParamMap = new HashMap<String,Object>();
            
            // 校验结果 0：成功 1：失败
            outParamMap.put("result", "0");
            
            JSONObject jsonObj = JSONObject.fromObject(outParamMap);
            System.out.println(jsonObj.toString());
            return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);  
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"校验写卡结果接口调用异常");
        }
    }
    
    /**
     * 计算开户费用
     * @param msgHead 公共消息头
     * @param map 入参
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-31 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户)
     */
    public ReturnWrap reckonRecFee(MsgHeaderPO msgHead,Map<String, String> inParamMap)
    {
        try
        {
            Map<String, Object> outParamMap = new HashMap<String, Object>();
            Map<String, String> inParam = new HashMap<String, String>();
            List feelist = new ArrayList();
            inParam.put("feeName","新入网用户话费预付款");
            inParam.put("fee", "1000");
            inParam.put("quantity", "1");
            inParam.put("feeID", "费用项");
            inParam.put("discount", "200");
            inParam.put("realfee", "800");
            
            feelist.add(inParam);
            outParamMap.put("feelist", feelist);
            JSONObject jsonObj = JSONObject.fromObject(outParamMap);
            String[] str = {"feeName","fee","quantity","feeID","discount","realfee"};
            return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,str);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"计算开户费用接口调用异常");
        }
    }
    
    /**
   	 * 查询手机号码是否有备卡
   	 * @param msgHeader
   	 * @param subsID 
   	 * @return
   	 * @remark create by c00233019 2014-10-25 13:59:39 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡备卡)
   	 */
    public ReturnWrap qryStoreCard(MsgHeaderPO msgHeader, String subsID)
    {
	    try 
	    {
	    	//CTagSet
//            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
//                +"<menuid>openAccount</menuid><process_code>cli_qryStoreCard_nx</process_code><verify_code></verify_code>"
//                +"<resp_time>20140305180720</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence>"
//                +"<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
//                +"</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
//                +"<subsID>1</subsID><region>1</region><iccID>1</iccID><startDate>1</startDate><endDate>1</endDate><activeDate>1</activeDate>"
//                +"<applyOID>1</applyOID><cancleOID>1</cancleOID></message_content>]]></message_body></message_response>";
//            
//            return intMsgUtil.parseResponse(response);
            
	    	//CRSet
//			String response = "[{startDate:'',endDate:'',subsID:'',iccID:''}]";
//			return getIntMsgUtil().parseJsonResponse(response, null, null);
			
			ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(SSReturnCode.SUCCESS);

            return rtwp;
	    	
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * <备卡算费>
     * <功能详细描述>
     * @param msgHeader
     * @param iccid
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by c00233019 2014-10-28 16:45:41 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡备卡)
     */
    public ReturnWrap reckonrecfeeByStore(MsgHeaderPO msgHeader, String servnum, String iccid)
    {
    	try 
	    {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                +"<menuid>openAccount</menuid><process_code>cli_qryStoreCard_nx</process_code><verify_code></verify_code>"
                +"<resp_time>20140305180720</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence>"
                +"<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
                +"</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                +"<totalFee>10</totalFee></message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * <备卡提交>
     * <功能详细描述>
     * @param msgHeader
     * @param servnum 手机号码
     * @param iccid iccid 
     * @param tMoney 用户投入金额     
     * @param payType 支付方式
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by c00233019 2014-10-28 16:45:41 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡备卡)
     */
    public ReturnWrap prepareCashCommit(MsgHeaderPO msgHeader, String servnum, String iccid, String tMoney, String payType)
    {
        Map<String,Object> outParamMap = new HashMap<String,Object>();
        
        //流水号
        outParamMap.put("formNum", "12345677777777777777");
        
        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        
        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
    }
    
    /**
     * <查询用户信息>
     * <功能详细描述>
     * @param msgHeader
     * @param region
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-11-6 18:13:32 OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
     */
    public ReturnWrap getSubscriberByTel(MsgHeaderPO msgHeader,String region)
    {
        String response = "{\"qryResult\":1,\"subscriber\":{\"BOType\":\"CSubscriber\",\"acctID\":\"7115179983706\"," +
                "\"active\":1,\"areaID\":\"HB.EZ.02.08.02\",\"authType\":\"AuthCheckB\",\"blankCardNo\":\"\"," +
                "\"brand\":\"BrandSzx\",\"controlModal\":\"4\",\"countyID\":\"HB.EZ.02\"," +
                "\"createDate\":\"2011-08-07 15:22:01\",\"credit\":0,\"creditStatus\":\"0\"," +
                "\"currentCustMgr\":\"\",\"custID\":\"7116104535428\",\"delayTime\":0,\"dummySubs\":1," +
                "\"editStatus\":1,\"enum\":\"89860097174121982474\",\"iccID\":\"89860097174121982474\"," +
                "\"imsi\":\"460001984747525\",\"invalidDate\":\"\",\"isSingle\":0,\"langType\":\"Chinese\"," +
                "\"mobileType\":\"mbtpNml\",\"netType\":\"GSM\",\"notes\":\"\",\"ownerOrgID\":\"HB.EZ.02.08.02\"," +
                "\"password\":\"123456\",\"payMode\":\"\",\"platType\":0,\"proStatus\":\"\",\"proStopKey\":\"\"," +
                "\"prodID\":\"MP7110503000163\",\"pzFlag\":0,\"region\":\"711\",\"registerOrgID\":\"HB.EZ.02.08.02\"," +
                "\"relateSubsID\":\"0\",\"responseCustMgr\":\"\",\"score\":0,\"servNumber\":\"15871503875\"," +
                "\"settleDay\":1,\"signType\":\"sbsnInst\",\"srvNum\":\"15871503875\"," +
                "\"startDate\":\"2011-08-07 15:22:01\",\"status\":\"US10\"," +
                "\"statusDate\":\"2014-04-25 09:55:21\",\"stopKey\":\"00000000\"," +
                "\"stopType\":2,\"subsID\":\"7115179964250\",\"subsName\":\"用7115179964250\"," +
                "\"subsType\":\"Person\",\"unFinish\":\"\",\"urgeType\":\"ugtpUS\"," +
                "\"userID\":\"7116104535428\",\"vipCardID\":\"\",\"vipCardType\":\"\"," +
                "\"vipType\":\"VC0000\"}}";
        
        return getIntMsgUtil().parseJsonResponse(response,new String[][]{{"qryResult", "subscriber"},{"qryResult", "subscriber"}},null);
    }

    @Override
    public ReturnWrap isRealName(MsgHeaderPO msgHeader)
    {
        Map<String,Object> outParamMap = new HashMap<String,Object>();
        //流水号
        outParamMap.put("isrealName", "1");
        
        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        
        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
    }

    @Override
    public ReturnWrap broadBandAppoint(String currArea,String installDate, String iDcard, String currProd, String band, MsgHeaderPO msgHeader)
    {
        ReturnWrap rtwp = new ReturnWrap();
        rtwp.setStatus(SSReturnCode.SUCCESS);
        return rtwp;
    }
    
    /**
     * <查询余额明细接口>
     * <功能详细描述>
     * @param msgHeader
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2016-03-31 OR_HUB_201602_493
     */
    public ReturnWrap showBalanceDetail(MsgHeaderPO msgHeader)
    {
    	try
    	{
    		String response = "{\"credit\":\"3.00元\",\"curFee\":\"0.00\"," +
    				"\"leftBalance\":\"259.00\",\"subjectListInfo\":[{\"categories\":\"普通余额\"," +
    				"\"consumption\":\"25900\",\"destroy\":\"\",\"range\":\"无\",\"subject\":\"现金科目\"," +
    				"\"validity\":\"永久有效\"}]}";
    		String[] outParam = {"categories", "subject", "range", "consumption", "validity", "destroy"};
    		return getIntMsgUtil().parseJsonResponse(response, null, outParam);
    	}
    	catch(Exception e)
    	{
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "查询余额明细接口调用异常");
    	}
    }
    
    /**
     * 创建支付任务接口
     * @param customerSimp
     * @param curMenuId
     * @param termInfo
     * @param isTelNum
     * @param currNumber
     * @param selfPayLog
     * @return
     * @remark create by lwx439898 2017-10-16 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    public ReturnWrap createPayCenterTrans(NserCustomerSimp customerSimp, String curMenuId, TerminalInfoPO termInfo,
            String isTelNum, String currNumber, Map<String, String> selfPayLog)
    {
        try
        {       
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>arcreateunionpaytask</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                + "<taskoid>711123456789</taskoid>"
                + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "创建支付任务接口异常");
        }
    }    
    
    /**
     * 更新银行支付状态
     * @param payTransMsg
     * @param payCenterParam
     * @return
     */
    public ReturnWrap updateBankPaymentStatus(String payCenterParamInfo,String respMsg,String payTransMsg,TerminalInfoPO termInfo,String curMenuId,String currNumber)
    {
        try
        {         
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>arcreateunionpaytask</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "更新银行支付状态异常");
        }       
    }
    
    public ReturnWrap qryPayChargeInfo(TerminalInfoPO termInfo, String taskoid, String currNumber, String curMenuId)
    {
        try
        {       
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>arcreateunionpaytask</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                + "<taskoid>711123456789</taskoid><region>711</region><servnumber>13688883412</servnumber><bankcode></bankcode><bankseqno></bankseqno><bankstatus>1</bankstatus><paytype>PayCenter</paytype>"
                + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "创建支付任务接口异常");
        }
    }
    
    public ReturnWrap updateBankRecoid(TerminalInfoPO termInfo,String currNumber,String curMenuId,String taskoid,String recoid,String status)
    {
        try
        {       
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>arcreateunionpaytask</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "更新表异常"); 
        }
    }
}

