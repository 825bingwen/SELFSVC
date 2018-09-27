package com.customize.sd.selfsvc.call.impl;

import java.util.Map;

import org.apache.axiom.om.OMElement;
import org.dom4j.Document;

import com.customize.sd.selfsvc.call.SelfSvcCallSD;
import com.customize.sd.selfsvc.cardbusi.model.IdCardPO;
import com.customize.sd.selfsvc.cardbusi.model.ProdTempletPO;
import com.customize.sd.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.sd.selfsvc.packageService.model.PrivServPackPO;
import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;
import com.customize.sd.selfsvc.serviceinfo.model.BankCardInfoPO;
import com.customize.sd.selfsvc.serviceinfo.model.BindBankCardPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;


public class SelfSvcCallSDTest implements SelfSvcCallSD
{
    private IntMsgUtil intMsgUtil;
    
    public IntMsgUtil getIntMsgUtil()
    {
        return intMsgUtil;
    }

    public void setIntMsgUtil(IntMsgUtil intMsgUtil)
    {
        this.intMsgUtil = intMsgUtil;
    }

    /**
     * 山东套餐信息查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryComboInfo(Map map)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
        		"<menuid>qrySerPri</menuid><process_code>cli_qry_taocan</process_code><verify_code></verify_code>" +
        		"<resp_time>20100921002206</resp_time><sequence><req_seq>39</req_seq><operation_seq></operation_seq>" +
        		"</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>" +
        		"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
        		"<message_content><calctime>09月21日00时</calctime><privitem><privset>10元包120条点对点短信＋移动全时通必选包</privset>" +
        		"<type>SMS</type><sumnum>120</sumnum><leftnum>33</leftnum><unit>条</unit><feename></feename><servtype>0</servtype>" +
        		"<starttime>20100901</starttime><endtime>20100930</endtime></privitem>" +
        		"<privitem><privset>12元市话包</privset><type>GSM</type>" +
        		"<sumnum>180</sumnum><leftnum>117</leftnum><unit>分钟</unit><feename></feename><servtype>0</servtype>" +
        		"<starttime>20140526</starttime><endtime>20140625</endtime></privitem>"+
        		"<privitem><privset>12元市话包</privset><type>GSM</type>" +
        		"<sumnum>180</sumnum><leftnum>117</leftnum><unit>分钟</unit><feename></feename><servtype>0</servtype>" +
        		"<starttime>20140526</starttime><endtime>20140625</endtime></privitem>"+
        		"</message_content>]]></message_body>" +
        		"</message_response>";
        ReturnWrap rw = null;
        try
        {
            rw = intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rw;
    }
    
    /**
     * 山东月账单查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryMonthBill(Map map)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>qryBillItem</menuid><process_code>cli_qry_bill</process_code><verify_code></verify_code>"
                + "<resp_time>20100921004740</resp_time><sequence><req_seq>89</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                
                +"<billItem><cycle>20110401</cycle><type>cust</type><key>post</key><value>271100</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>cust</type><key>addr</key><value>南冶镇</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>cust</type><key>name</key><value>姓名13506340790</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>brief</type><key>brand</key><value>神州行</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>brief</type><key>logo</key><value>神州行:自由 实惠 便捷</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>brief</type><key>title</key><value>尊敬的姓名13506340790</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billfixed</type><key>custname</key><value>姓名13506340790</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billfixed</type><key>telnumber</key><value>13506340790</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billfixed</type><key>billcycle</key><value>20110401</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billfixed</type><key>startdate</key><value>20110601</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billfixed</type><key>enddate</key><value>20110630</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billfixed</type><key>billflag</key><value>0</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>月基本费</key><value>10.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    动感地带必选包功能费</key><value>10.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>增值业务费</key><value>130.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    主叫显示</key><value>10.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    彩铃</key><value>10.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    移动秘书</key><value>10.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    集团彩铃</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    神侃特区功能费</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    情侣夜话功能费</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    大话夜游功能费</key><value>10.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    加点颜色功能费</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    网际飞车功能费</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    夜话绵绵功能费</key><value>10.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    一卡双号功能费</key><value>10.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    集团总机</key><value>10.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    手机炒股</key><value>10.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    拇指派功能费</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    情侣派功能费</key><value>3.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    长聊派功能费</key><value>10.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    长途功能费</key><value>10.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    APN功能费</key><value>1.00</value></billItem>"+
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>    亲情号码费</key><value>20.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>        亲情号码费</key><value>20.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>billflex</type><key>费用合计</key><value>140.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>acct</type><key>lastval</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>acct</type><key>backfee</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>acct</type><key>transferout</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>acct</type><key>transferin</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>acct</type><key>contractused</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>acct</type><key>income</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>brief</type><key>totalfee</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>acct</type><key>totalfee</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>acct</type><key>latefee</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>brief</type><key>oddbalance</key><value>0.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>acct</type><key>thisval</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>acct</type><key>presentval</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>acct</type><key>contractval</key><value>1.00</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>acct</type><key>notice</key><value>2.00 元。</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>score</type><key>income</key><value>1</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>score</type><key>consume</key><value>1</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>score</type><key>hortation</key><value>0</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>score</type><key>lastval</key><value>1</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>score</type><key>thisused</key><value>1</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>brief</type><key>oddscore</key><value>0</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>score</type><key>thisval</key><value>1</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>score</type><key>resetval</key><value>1</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>score</type><key>innet</key><value>1</value></billItem>" +
        		"<billItem><cycle>20110401</cycle><type>score</type><key>otheraward</key><value>1</value></billItem>"+
        		"<billItem><cycle>20110401</cycle><type>sp</type><key>spProvider1  spServiceCode1  spName1</key><value>55555</value></billItem>"+
        		"<billItem><cycle>20110401</cycle><type>sp</type><key>spProvider1  spServiceCode1  spName1</key><value>55555</value></billItem>"
                
                + "</message_content>]]></message_body></message_response>";
        ReturnWrap rw = null;
        try
        {
            rw = intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return rw;
    }
    
    /**
     * 账户余额查询
     * 
     * @param map
     * @return
     */
    
    // modify begin cKF48754 2011/10/19 OR_SD_201106_95 根据接口协议 V3.6修改山东余额查询
    public ReturnWrap queryBalance(Map<String, String> paramMap)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"+
        "<menuid>qryBill</menuid><process_code>cli_qry_balance</process_code><verify_code></verify_code>"+
        "<resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq>"+
        "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"+
        "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"+
        "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_body><prepayType>pptpPre</prepayType>"+
        "<currBillFee>100</currBillFee><hisBillFee>200</hisBillFee><balance>300</balance>"+
        "<expireDate>20110809</expireDate><contractBalance>400</contractBalance>"+
        "<contractCanUse>500</contractCanUse><contractThisUsed>600</contractThisUsed>"+
        "<contract_drawamt>700</contract_drawamt><present_balance>800</present_balance>"+
        "<present_canuse>900</present_canuse><present_thisused>1000</present_thisused>"+
        "<present_drawamt>1100</present_drawamt><cash_balance>1200</cash_balance>"+
        "<other_balance>1300</other_balance></message_body>]]></message_body></message_response>";
        ReturnWrap rw = null;
        try
        {
            rw = intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rw;
    }
    // modify end cKF48754 2011/10/19 OR_SD_201106_95 根据接口协议 V3.6修改山东余额查询
    
    /**
     * 话费充值账户信息查询
     */
    public ReturnWrap qryfeeChargeAccount(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_fee</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><servregion>531</servregion><accept_type>S30</accept_type>"
                    + "<balance>10</balance><sum_fee>20.00</sum_fee><cust_name>贾承权</cust_name></message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * 话费充值
     */
    public ReturnWrap chargeCommit(Map<String,String> map)
    {
        try
        {
//            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
//                    + "<menuid></menuid><process_code>cli_busi_chargefee</process_code><verify_code></verify_code>"
//                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
//                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
//                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
//                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
//                    + "<bill_nbr>123456789</bill_nbr><accept_name>缴费</accept_name><acct_id>987654321</acct_id>"
//                    + "<cust_name>高群</cust_name><pay_date>20100910</pay_date><prt_count>0</prt_count>"
//                    + "<comment></comment><bcycle_count>2</bcycle_count>"
//                    + "<bill><bcycleid>201009</bcycleid><ss_fee>100</ss_fee><ys_fee>99.99</ys_fee>"
//                    + "<capital_fee>玖拾玖元玖角玖分</capital_fee><last_balance>0</last_balance><this_balance>100</this_balance>"
//                    + "<item_count>3</item_count><item_set>上次余额|0;本次缴纳|100;本次余额|100</item_set></bill>" 
//                    + "<bill><bcycleid>201009</bcycleid><ss_fee>100</ss_fee><ys_fee>99.99</ys_fee>"
//                    + "<capital_fee>玖拾玖元玖角玖分</capital_fee><last_balance>0</last_balance><this_balance>100</this_balance>"
//                    + "<item_count>3</item_count><item_set>上次余额|0;本次缴纳|100;本次余额|100</item_set></bill>"
//                    + "</message_content>]]></message_body></message_response>";
						// 现场数据
//            String response = "<message_response><message_head version=\"1.0\"><menuid>feeCharge</menuid>" +
//            		"<process_code>cli_busi_chargefee</process_code><verify_code></verify_code>" +
//            		"<resp_time>20140626110156</resp_time><sequence><req_seq>263</req_seq><operation_seq>" +
//            		"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>" +
//            		"<retmsg><![CDATA[缴费成功]]></retmsg></retinfo></message_head><message_body>" +
//            		"<![CDATA[<message_content><servregion>531</servregion><bill_nbr>531140626835593780" +
//            		"</bill_nbr><accept_name>收费</accept_name><acct_id>5318001909860</acct_id>" +
//            		"<cust_name>刘方彬</cust_name><pay_date>2014.06.26</pay_date>" +
//            		"<prt_count>0</prt_count><comment></comment><bcycle_count>1</bcycle_count>" +
//            		"<bill><bcycleid></bcycleid><ss_fee>5.00</ss_fee><ys_fee></ys_fee>" +
//            		"<capital_fee>5.00</capital_fee><last_balance></last_balance><this_balance>" +
//            		"</this_balance><item_count>3</item_count><item_set>充值前余额|2.02;充值金额|5.00;充值后余额|7.02" +
//            		"</item_set></bill></message_content>]]></message_body></message_response>";
            // L05版本以后现场数据
            /*String response = "<message_response><message_head version=\"1.0\"><menuid>feeCharge</menuid><process_code>" +
            		"cli_busi_chargefee</process_code><verify_code></verify_code><resp_time>20140705154625</resp_time>" +
            		"<sequence><req_seq>25</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0" +
            		"</rettype><retcode>100</retcode><retmsg><![CDATA[缴费成功]]></retmsg></retinfo></message_head>" +
            		"<message_body><![CDATA[<message_content><servregion>531</servregion><bill_nbr>531140705139767708" +
            		"</bill_nbr><accept_name>收费</accept_name><acct_id>5318043606733</acct_id><cust_name>吕友运" +
            		"</cust_name><pay_date>2014.07.05</pay_date><prt_count>0</prt_count><comment></comment>" +
            		"<bcycle_count>1</bcycle_count><bill><bcycleid></bcycleid><ss_fee>5.00</ss_fee><ys_fee>" +
            		"</ys_fee><capital_fee>5.00</capital_fee><last_balance></last_balance><this_balance>" +
            		"</this_balance><item_count>3</item_count><item_set>通信服务费|5.00元;合计|5.00元;本次发票金额|5.00元" +
            		"</item_set>" +
            		"</bill></message_content>]]></message_body></message_response>";*/
            
            
            String response = "<message_response><message_head version=\"1.0\"><menuid>feeCharge</menuid>" +
    		"<process_code>cli_busi_chargefee</process_code><verify_code></verify_code>" +
    		"<resp_time>20140722194849</resp_time><sequence><req_seq>9</req_seq>" +
    		"<operation_seq></operation_seq></sequence>" +
    		"<retinfo><rettype>0</rettype><retcode>100</retcode>" +
    		"<retmsg><![CDATA[缴费成功]]></retmsg></retinfo></message_head>" +
    		"<message_body><![CDATA[<message_content><servregion>531</servregion>" +
    		"<bill_nbr>531140722078576860</bill_nbr><accept_name></accept_name>" +
    		"<acct_id>5318001909860</acct_id><cust_name>刘方彬</cust_name>" +
    		"<pay_date></pay_date><prt_count></prt_count>" +
    		"<comment></comment><bcycle_count></bcycle_count>" +
    		"</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
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
        ReturnWrap rw = new ReturnWrap();
        rw.setStatus(1);
        
        return rw;
    }
    
    /**
     * 不校验密码，直接获取用户信息
     */
    public ReturnWrap getUserStatus(Map map)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>cli_qry_userinfo</process_code><verify_code></verify_code>"
                + "<unicontact></unicontact><resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><subname>高群</subname>"
                + "<region>634</region><regionname></regionname><productid></productid>"
                + "<productname></productname><productgroup></productgroup><viptype></viptype>"
                +
                // "<productname>全球通</productname><productgroup>BrandMzone</productgroup><viptype>VC0000</viptype>" +
                "<logintype></logintype><feeflag></feeflag><question></question><answer></answer><needcheckstr>"
                + "</needcheckstr><status>US28</status><nettype></nettype></message_content>]]></message_body></message_response>";
        
        ReturnWrap rw = null;
        try
        {
            rw = intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return rw;
    }
    
	
    /**
	 * 获取账单寄送查询的返回信息
	 * 
	 * @param map
	 * @return 
	 * @see
	 */
	public ReturnWrap getMailBillSendInfo(Map map)
	{
		//Bill开通了139邮箱账单定制
		String responseBill = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
	        + "<menuid></menuid><process_code>cli_qry_billmail</process_code><verify_code></verify_code>"
	        + "<resp_time>20110901135956</resp_time><sequence><req_seq>21</req_seq><operation_seq>12</operation_seq>"
	        + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
	        + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
	        + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><oid>1</oid>"
	        + "<subsid></subsid><billType>Bill</billType><langtype></langtype>"
	        + "<mailtype></mailtype><mailaddr></mailaddr><postcode></postcode>"
	        + "<linkman></linkman><linkphone></linkphone><email>15168866090@139.com</email><createdate></createdate><status>"
	        + "</status><statusdate></statusdate></message_content>]]></message_body></message_response>";
		
		//Bill开通了邮箱定制但不是139邮箱
		String responseBill2 = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
	        + "<menuid></menuid><process_code>cli_qry_billmail</process_code><verify_code></verify_code>"
	        + "<resp_time>20110901135956</resp_time><sequence><req_seq>21</req_seq><operation_seq>12</operation_seq>"
	        + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
	        + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
	        + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><oid>2</oid>"
	        + "<subsid></subsid><billType>Bill</billType><langtype></langtype>"
	        + "<mailtype></mailtype><mailaddr></mailaddr><postcode></postcode>"
	        + "<linkman></linkman><linkphone></linkphone><email>lvxuguang@huawei.com</email><createdate></createdate><status>"
	        + "</status><statusdate></statusdate></message_content>]]></message_body></message_response>";
		
		//Bill未开通邮箱定制
		String responseBill3 = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
	        + "<menuid></menuid><process_code>cli_qry_billmail</process_code><verify_code></verify_code>"
	        + "<resp_time>20110901135956</resp_time><sequence><req_seq>21</req_seq><operation_seq>12</operation_seq>"
	        + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
	        + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
	        + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><oid></oid>"
	        + "<subsid></subsid><billType>Bill</billType><langtype></langtype>"
	        + "<mailtype></mailtype><mailaddr></mailaddr><postcode></postcode>"
	        + "<linkman></linkman><linkphone></linkphone><email></email><createdate></createdate><status>"
	        + "</status><statusdate></statusdate></message_content>]]></message_body></message_response>";
		
		//BillDetail开通了139邮箱账单定制
		String responseDetail = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
	        + "<menuid></menuid><process_code>cli_qry_billmail</process_code><verify_code></verify_code>"
	        + "<resp_time>20110901135956</resp_time><sequence><req_seq>21</req_seq><operation_seq>12</operation_seq>"
	        + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
	        + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
	        + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><oid>3</oid>"
	        + "<subsid></subsid><billType>BillDetail</billType><langtype></langtype>"
	        + "<mailtype></mailtype><mailaddr></mailaddr><postcode></postcode>"
	        + "<linkman></linkman><linkphone></linkphone><email>15168866090@139.com</email><createdate></createdate><status>"
	        + "</status><statusdate></statusdate></message_content>]]></message_body></message_response>";
		
		//BillDetail开通了邮箱定制但不是139邮箱
		String responseDetail2 = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
	        + "<menuid></menuid><process_code>cli_qry_billmail</process_code><verify_code></verify_code>"
	        + "<resp_time>20110901135956</resp_time><sequence><req_seq>21</req_seq><operation_seq>12</operation_seq>"
	        + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
	        + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
	        + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><oid>4</oid>"
	        + "<subsid></subsid><billType>BillDetail</billType><langtype></langtype>"
	        + "<mailtype></mailtype><mailaddr></mailaddr><postcode></postcode>"
	        + "<linkman></linkman><linkphone></linkphone><email>xglv@mail.sdu.edu.cn</email><createdate></createdate><status>"
	        + "</status><statusdate></statusdate></message_content>]]></message_body></message_response>";
		
		//BillDetail未开通邮箱定制
		String responseDetail3 = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
	        + "<menuid></menuid><process_code>cli_qry_billmail</process_code><verify_code></verify_code>"
	        + "<resp_time>20110901135956</resp_time><sequence><req_seq>21</req_seq><operation_seq>12</operation_seq>"
	        + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
	        + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
	        + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><oid></oid>"
	        + "<subsid></subsid><billType>BillDetail</billType><langtype></langtype>"
	        + "<mailtype></mailtype><mailaddr></mailaddr><postcode></postcode>"
	        + "<linkman></linkman><linkphone></linkphone><email></email><createdate></createdate><status>"
	        + "</status><statusdate></statusdate></message_content>]]></message_body></message_response>";
	
	        ReturnWrap rw = null;
	        try
	        {
	        	if( map.get("billtype").equals("Bill"))
	        	{
	        		rw = intMsgUtil.parseResponse(responseBill3); 
	        	}
	        	else if( map.get("billtype").equals("BillDetail"))
	        	{
	        		rw = intMsgUtil.parseResponse(responseDetail2);
	        	}
	        }
	        catch (Exception e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	
	        return rw;
	 }
	
	/**
	 * 撤销原邮箱寄送
	 * 
	 * @param map
	 * @return 
	 * @see
	 */
	public ReturnWrap getCancelCaseInfo(Map map) 
	{
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid></menuid><process_code>cli_busi_cancelbillmail</process_code><verify_code></verify_code><resp_time>20100921002348</resp_time><sequence><req_seq>49</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
	    ReturnWrap rw = null;
	    try
	    {
	        rw = intMsgUtil.parseResponse(response);
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    return rw;
	}
	
	/**
	 * 开通邮箱寄送
	 * 
	 * @param map
	 * @return 
	 * @see
	 */
	public ReturnWrap getOpenBillMailInfo(Map map) 
	{
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid></menuid><process_code>cli_busi_addbillmail</process_code><verify_code></verify_code><resp_time>20100921002348</resp_time><sequence><req_seq>49</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
	    ReturnWrap rw = null;
	    try
	    {
	        rw = intMsgUtil.parseResponse(response);
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    return rw;
	}
    
    /**
     * 查询用户是否已开通手机邮箱
     * @param map
     * @return
     */
    public ReturnWrap qrymailBox(Map map)
    {
    	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
            + "<menuid></menuid><process_code>cli_qry_mailbox</process_code><verify_code></verify_code>"
            + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
            + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
            + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
            + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><havemailbox>1</havemailbox>"
            + "</message_content>]]></message_body></message_response>";
    
	    try
	    {
	        return intMsgUtil.parseResponse(response);
	    }
	    catch (Exception e)
	    {
	    	ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("查询用户是否已开通手机邮箱失败!");
            
            return rw;
	    }
	}
    
    /**
     * 查询用户是否已开通手机邮箱
     * @param map
     * @return
     */
    public ReturnWrap add139Mail(Map map)
    {
    	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
            + "<menuid></menuid><process_code>cli_busi_add139mail</process_code><verify_code></verify_code>"
            + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
            + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
            + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
            + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
            + "</message_content>]]></message_body></message_response>";
    
	    try
	    {
	        return intMsgUtil.parseResponse(response);
	    }
	    catch (Exception e)
	    {
	    	ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("开通139免费邮箱失败!");
            
            return rw;
	    }
	}
    
    // add begin cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    /**
     * 账单备注查询
     * @param map
     * @return
     */
    public ReturnWrap queryBillAddInfo(Map map)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
            + "<menuid></menuid><process_code>cli_qry_billaddinfo</process_code><verify_code></verify_code>"
            + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
            + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
            + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
            + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><remark>账单备注信息：测试</remark>"
            + "</message_content>]]></message_body></message_response>";
        
        //王胜利测试
        String response2 = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>qryBillItem</menuid><process_code>cli_qry_billaddinfo</process_code><verify_code></verify_code><resp_time>20111206114952</resp_time><sequence><req_seq>26</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><remark>市话最低消费18元，优惠月租.&#x0A;网内被叫优惠(本地网内单向)&#x0A;亲情卡品牌优惠&#x0A;</remark></message_content>]]></message_body></message_response>";
    
        try
        {
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("账单备注查询失败!");
            
            return rw;
        }
    }
    // add end cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    
    
    /**
     * 根据手机号码查询客户信息
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap getCustinfo(Map map)
    {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>qryBillItem</menuid><process_code>getcustinfo</process_code><verify_code></verify_code>"
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
                
                // 用户ID
                + "<subsid>10000000000001</subsid>"
                
                // 账期信息 
                
                // 帐期+开始时间+结束时间+主账号+是否合户用户
                + "<cyclelist><cycle>20120301</cycle><startdate>20120301</startdate><enddate>20120315</enddate><acctid>10000001</acctid><unionacct>1</unionacct></cyclelist>"
                + "<cyclelist><cycle>20120316</cycle><startdate>20120316</startdate><enddate>20120331</enddate><acctid>10000001</acctid><unionacct>0</unionacct></cyclelist>"
                
                + "</message_content>]]></message_body></message_response>";
                
                // 历史账单
                //response = "<message_response><message_head version=\"1.0\"><menuid>qryBillItemNew</menuid><process_code>cli_qry_custinfo</process_code><verify_code></verify_code><resp_time>20120315112844</resp_time><sequence><req_seq>181</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><custname>曹芹</custname><brandnm>全球通</brandnm><productnm>预付费省内商旅套餐2012版</productnm><subsid>5388004382544</subsid><cyclelist><cycle>20120201</cycle><startdate>20120201</startdate><enddate>20120229</enddate><acctid>5388004375950</acctid><unionacct>0</unionacct></cyclelist></message_content>]]></message_body></message_response>";
                
                // 实时账单
                //response = "<message_response><message_head version=\"1.0\"><menuid>qryBillItemNew</menuid><process_code>cli_qry_custinfo</process_code><verify_code></verify_code><resp_time>20120315112538</resp_time><sequence><req_seq>177</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><custname>曹芹</custname><brandnm>全球通</brandnm><productnm>预付费省内商旅套餐2012版</productnm><subsid>5388004382544</subsid><cyclelist><cycle>20120301</cycle><startdate>20120301</startdate><enddate>20120331</enddate><acctid>5388004375950</acctid><unionacct>0</unionacct></cyclelist></message_content>]]></message_body></message_response>";
                ReturnWrap rw = null;
                try
                {
                    rw = intMsgUtil.parseResponse(response);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                
                return rw;
    }
    
    /**
     * 根据手机号码查询客户信息
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryMonthBill_new(Map map)
    {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>qryBillItem</menuid><process_code>getcustinfo</process_code><verify_code></verify_code>"
                + "<resp_time>20100921004740</resp_time><sequence><req_seq>89</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                
                + "<custbill>"
                + "<billinfo>"
                    //-----------------------费用信息-----------------------------------------------------
                    + "<billfixed>"
                    +      "<fee><name>套餐及固定费用</name><value>10.00</value><sortorder/></fee>"
                    +      "<feegroup>"
                    +           "<name>套餐外费用</name><sortorder/>"
                    +           "<subfee>"
                    +               "<fee><name>语音通信费</name><value>10.00</value><isshow>1</isshow><sortorder/></fee>"
                    +               "<fee><name>上网费</name><value>10.00</value><isshow>1</isshow><sortorder/></fee>"
                    +               "<fee><name>短彩信费</name><value>10.00</value><isshow>1</isshow><sortorder/></fee>"
                    +           "</subfee>"
                    +       "</feegroup>"
                    +       "<fee><name>增值业务费</name><value>0.00</value><isshow>1</isshow><sortorder/></fee>"
                    +       "<fee><name>代收费</name><value>10.00</value><isshow>1</isshow><sortorder/></fee>"
                    +       "<fee><name>补收费</name><value>10.00</value><isshow>1</isshow><sortorder/></fee>"
                    +       "<fee><name>优惠减免</name><value>10.00</value><isshow>0</isshow><sortorder/></fee>"
                    +       "<fee><name>小计</name><value>10.00</value><isshow>0</isshow><sortorder/></fee>"
                    +       "<fee><name>他人代付费</name><value>10.00</value><isshow>0</isshow><sortorder/></fee>"
                    +       "<fee><name>本月赠费</name><value>10.00</value><isshow>0</isshow><sortorder/></fee>"
                    +       "<fee><name>本月费用合计</name><value>20.00</value><isshow>0</isshow><sortorder/></fee>"
                    + "</billfixed>"
                    //-----------------------自有业务-----------------------------------------------------
                    + "<feedetails>"
                    +         "<feegroup>"
                    +             "<name>套餐和固定费</name><sortorder/>"
                    +             "<subfee>"
                    +                 "<fee><name>全球通128套餐</name><value>10.00</value><sortorder/></fee>"
                    +                 "<fee><name>亲情号码套餐</name><value>10.00</value><sortorder/></fee>"
                    +                 "<fee><name>国内数据套餐包</name><value>10.00</value><sortorder/></fee>"
                    +                 "<fee><name>wlan</name><value>10.00</value><sortorder/></fee>"
                    +             "</subfee>"
                    +             "<fee><name>小计</name><value>10.00</value></fee>"
                    +         "</feegroup>"
                    +         "<feegroup>"
                    +             "<name>套餐外语音费</name><sortorder/>"
                    +             "<subfee>"
                    +                 "<fee><name>国内长途</name><value>10.00</value><sortorder/></fee>"
                    +                 "<fee><name>港澳长途</name><value>10.00</value><sortorder/></fee>"
                    +             "</subfee>"
                    +             "<fee><name>小计</name><value>10.00</value><sortorder/></fee>"
                    +         "</feegroup>"
                    +         "<feegroup>"
                    +             "<name>套餐上网费</name><sortorder/>"
                    +             "<subfee>"
                    +                 "<fee><name>wlan时长</name><value>10.00</value><sortorder/></fee>"
                    +                 "<fee><name>数据业务</name><value>10.00</value><sortorder/></fee>"
                    +                 "<fee><name>省内wlan上网</name><value>10.00</value><sortorder/></fee>"
                    +             "</subfee>"
                    +             "<fee><name>小计</name><value>10.00</value></fee>"
                    +         "</feegroup>"
                    +         "<feegroup>"
                    +             "<name>套餐外端彩信费</name><sortorder/>"
                    +             "<subfee>"
                    +                 "<fee><name>国内短信</name><value>10.00</value><sortorder/></fee>"
                    +                 "<fee><name>彩信</name><value>10.00</value><sortorder/></fee>"
                    +             "</subfee>"
                    +             "<fee><name>小计</name><value>10.00</value></fee>"
                    +         "</feegroup>"
                    +         "<feegroup>"
                    +             "<name>自由增值业务费</name>"
                    +             "<sortorder/>"
                    +             "<subfee>"
                    +                 "<fee><name>交通秘书</name><value>10.00</value><sortorder/></fee>"
                    +                 "<fee><name>无限音乐</name><value>10.00</value><sortorder/></fee>"
                    +             "</subfee>"
                    +             "<fee><name>小计</name><value>10.00</value></fee>"
                    +         "</feegroup> "
                    +         "<feegroup>"
                    +             "<name>带他人付费</name><sortorder/>"
                    +             "<subfee>"
                    +                 "<fee><name>telnum</name><value>12.00</value><sortorder/></fee>"
                    +             "</subfee>"
                    +             "<fee><name>小计</name><value>11.00</value></fee>"
                    +         "</feegroup>"
                    +         "<feegroup>"
                    +             "<fee><name>其他</name><value>10.00</value><sortorder/></fee>"
                    +         "</feegroup>"
                    + "</feedetails>"
                + "</billinfo>"
                //-----------------------账户概要信息-----------------------------------------------------
                +"<acctbalance>"
                + "<acct>"
                +     "<name>账户name</name>"
                     //<!--上月末结余 -->
                +     "<lastval>10</lastval>"
                     //<!-- 退费 -->
                +     "<backfee>10.00</backfee>"
                     //<!--过户转出 -->
                +     "<transferout>5.00</transferout>"
                     //<!--过户转入 -->
                +     "<transferin>3.00</transferin>"
                     //<!--使用协议款 -->
                +     "<contractused>20.00</contractused>"
                     //<!--本月充值 -->
                +     "<income>100.00</income>"
                     //<!--本月费用合计 -->
                +     "<totalfee>120.00</totalfee>"
                     //<!--月末剩余、现金账本余额 -->
                +     "<thisval>8.00</thisval>"
                     //<!--赠款余额 -->
                +     "<presentval>5.00</presentval>"
                     //<!--协议款余额 -->
                +     "<contractval>2.00</contractval>"
                     //<!--代他人付费 -->
                +     "<payotherfee>5.00</payotherfee>"
                +     "<selftotalfee>10.00</selftotalfee>"
                     //"<!--本月合计可用 -->
                +     "<totalcanuse>20.00</totalcanuse>"
                     //"<!--违约金 -->
                +     "<latefee>0.00</latefee>" 
                     //<!--合户号码费用 -->
                +     "<othersubsfee>10.00</othersubsfee>"
                     //<!-- 本月费用合计 -->
                +     "<substotalfee>30.00</substotalfee>"
                + "</acct>"       
                +"</acctbalance>"
                //----------------------资费推荐----------------------------------------------------
                +"<recommend>资费推荐：（ 1 ）20元打100市话费（ 2 ）50元打150市话费</recommend>"
                //----------------------备注-------------------------------------------------------
                +"<scoreremark>积分备注信息...</scoreremark>"
                +"<acknowledgement>感谢语备注信息...</acknowledgement>"
                //----------------------半年消费趋势图----------------------------------------------------
                +"<billhalfyeartrend>"
                +"<bill><month>201101</month><money>10000</money></bill>"
                +"<bill><month>201102</month><money>20000</money></bill>"
                +"<bill><month>201103</month><money>15000</money></bill>"
                +"<bill><month>201104</month><money>16000</money></bill>"
                +"<bill><month>201105</month><money>17000</money></bill>"
                +"<bill><month>201106</month><money>19000</money></bill>"
                +"</billhalfyeartrend>"
                //----------------------积分信息区----------------------------------------------------
                +"<scoreinfo>"
                    +"<score>"
                        //<!--上期可用-->
                        +"<lastavail>10</lastavail>"
                        //<!--本期新增-->
                        +"<consume>10</consume>"
                        //<!--本期奖励-->
                        +"<award>10</award>"
                        //<!--本期跨区转入-->
                        +"<transin>10</transin>"
                        //<!--本期兑换上-->
                        +"<exchange>10</exchange>"
                        //<!--本期跨区转出-->
                        +"<transout>10</transout>"
                        //<!--本期作废-->
                        +"<clear>10</clear>"
                        //<!--可用积分-->
                        +"<thisavail>10</thisavail>"
                        //<!--本期总增加-->
                        +"<totalin>10</totalin>"
                        //<!--本期总增加-->
                        +"<totalout>10</totalout>"
                        
//                    +"<lastavail>0</lastavail>"
//                    +"<consume>0</consume>"
//                    +"<award>0</award>"
//                    +"<transin>0</transin>"
//                    +"<exchange>0</exchange>"
//                    +"<transout>0</transout>"
//                    +"<clear>0</clear>"
//                    +"<thisavail>0</thisavail>"
//                    +"<totalin>0</totalin>"
//                    +"<totalout>0</totalout>"
                        
                    +"</score>"
                +"</scoreinfo>"
                
                //-----------------------------------------通讯量使用明细------------------------------------------------------------
                +"<pkginfo>"
                //<!-- 对应一个套餐。使用量的显示：freetype:used attrtype -->
                +"<pkg>"
                //    <!--套餐ID-->
                +"    <pkgid>pkgid</pkgid>"
                //    <!--套餐名称-->
                +"    <pkgname>神州行88套餐</pkgname>"
                //    <!--套餐描述-->
                +"    <pkgdesc>包含GPRS：200M；国内漫游：20分钟；本地通话：500分钟</pkgdesc>"
                //    <!--字符列表-->
                +"    <privlist>"        
                +"        <priv>"
                //            <!--资费政策ID-->
                +"            <rateid>rateid</rateid>"
                //            <!--通讯类型：ALL,GSM,SMS,GPRS,WLAN,MMS,ISMG,MMR-->
                +"            <freetype>GRPS</freetype>"
                //            <!--赠送的总量-->
                +"            <total>500</total>"
                //            <!--已经使用的量-->
                +"            <used>200</used>"
                //            <!--单位 1 次数 2 时长(秒) 3 费用(元) 4 流量(KB) 5 流量(M) 6 时长(小时) -->
                +"            <attrtype>5</attrtype>"
                //            <!--资费政策名称-->
                +"            <feename>资费政策名称</feename>"
                +"        </priv>"
                +"        <priv>"
                //            <!--资费政策ID-->
                +"            <rateid>rateid</rateid>"
                //            <!--通讯类型：ALL,GSM,SMS,GPRS,WLAN,MMS,ISMG,MMR-->
                +"            <freetype>国内漫游</freetype>"
                //            <!--赠送的总量-->
                +"            <total>500</total>"
                //            <!--已经使用的量-->
                +"            <used>20</used>"
                //            <!--单位 1 次数 2 时长(秒) 3 费用(元) 4 流量(KB) 5 流量(M) 6 时长(小时) -->
                +"            <attrtype>6</attrtype>"
                //            <!--资费政策名称-->
                +"            <feename>资费政策名称</feename>"
                +"        </priv>"
                +"        <priv>"
                //            <!--资费政策ID-->
                +"            <rateid>rateid</rateid>"
                //            <!--通讯类型：ALL,GSM,SMS,GPRS,WLAN,MMS,ISMG,MMR-->
                +"            <freetype>本地通话</freetype>"
                //            <!--赠送的总量-->
                +"            <total>500</total>"
                //            <!--已经使用的量-->
                +"            <used>20</used>"
                //            <!--单位 1 次数 2 时长(秒) 3 费用(元) 4 流量(KB) 5 流量(M) 6 时长(小时) -->
                +"            <attrtype>6</attrtype>"
                //            <!--资费政策名称-->
                +"            <feename>资费政策名称</feename>"
                +"        </priv>"
                +"    </privlist>"
                +"</pkg>"
                +"<pkg>"
                +"<pkgid>0R8S</pkgid>"
                +"<pkgname>12元市话包</pkgname>"
                +"<pkgdesc>每月12元，包180分钟本地市话主叫通话</pkgdesc>"
                +"<privlist>"
                +"<priv>"//本地接口其实是total以及used都是自带单位的
                +"<rateid>731241</rateid>"
                +"	<freetype>语音</freetype>"
                +"	<total>3小时0分0秒</total>"
                +"	<used>1小时3分0秒</used>"
                +"	<attrtype>2</attrtype>"
                +"	<feename />"
                +"</priv>"
                +"</privlist>"
                +"</pkg>"
                //<!--以下是汇总信息-->
                +"<total>"
                +"    <priv>"
                //        <!--资费政策ID-->
                +"        <rateid>资费政策ID</rateid>"
                //        <!--通讯类型：ALL,GSM,SMS,GPRS,WLAN,MMS,ISMG,MMR -->
                +"        <freetype>GSM</freetype>"
                //        <!--赠送的总量 -->
                +"        <total>100</total>"
                //        <!--已经使用的量 -->
                +"        <used>50</used>"
                //        <!--单位 1 次数 2 时长(秒) 3 费用(分) 4 流量(KB) 5 流量(M) 6 时长(小时 )-->
                +"        <attrtype>5</attrtype>"
                //        <!--资费政策名称 -->
                +"        <feename>资费政策名称</feename>"
                +"    </priv>"
                +"    <priv>"
                //        <!--资费政策ID-->
                +"        <rateid>资费政策ID</rateid>"
                //        <!--通讯类型：ALL,GSM,SMS,GPRS,WLAN,MMS,ISMG,MMR -->
                +"        <freetype>GPRS</freetype>"
                //        <!--赠送的总量 -->
                +"        <total>200</total>"
                //        <!--已经使用的量 -->
                +"        <used>100</used>"
                //        <!--单位 1 次数 2 时长(秒) 3 费用(分) 4 流量(KB) 5 流量(M) 6 时长(小时 )-->
                +"        <attrtype>5</attrtype>"
                //        <!--资费政策名称 -->
                +"        <feename>资费政策名称1</feename>"
                +"    </priv>"
                +"    <priv>"//本地接口其实是total以及used都是自带单位的
                +"        <freetype>语音</freetype>"
                +"        <total>3小时0分0秒</total>"
                +"		  <used>1小时3分0秒</used>"
                +"	      <attrtype>2</attrtype>"
                +"	  </priv>"
                +"</total>"
                +"</pkginfo>"
                
                //------------------------协议款信息-----------------------------------------------------------
                +"<agreementinfo>"
                +"<agreement>"
                    //<!--协议款协议名称 -->
                    +"<name>协议款协议名称</name>"
                    //<!--上月末余额 -->
                    +"<lastmonthleft>10.00</lastmonthleft>"
                    //<!--当月续费 -->
                    +"<curmonthpay>10.00</curmonthpay>"
                    //<!--当月返还额度 -->
                    +"<curmonthreturn>10.00</curmonthreturn>"
                    //<!--当月使用 -->
                    +"<curmonthused>10.00</curmonthused>"
                    //<!--当月扣除 -->
                    +"<curmonthdeduct>10.00</curmonthdeduct>"
                    //<!--月末剩余 -->
                    +"<monthclosing>10.00</monthclosing>"
                    //<!--有效期 -->
                    +"<efftime>2010-01-01</efftime>"
                    //<!--备注 -->
                    +"<remark>备注</remark>"
                    //<!-- 使用号码 -->
                    +"<usedtel>13645319981</usedtel>"
                    //<!-- 每月最低消费额度 -->
                    +"<lowestconsume>20.00</lowestconsume>"
                +"</agreement>"
                +"</agreementinfo>"
                
                //----------------------------赠送款信息-----------------------------------------------------------
                +"<presentinfo>"
                +"<present>"
                    //<!--赠送款协议名称 -->
                    +"<name>赠送款协议名称</name>"
                    //<!--上月末余额 -->
                    +"<lastmonthleft>10.00</lastmonthleft>"
                    //<!--当月续费 -->
                    +"<curmonthpay>10.00</curmonthpay>"
                    //<!--当月返还额度 -->
                    +"<curmonthreturn>10.00</curmonthreturn>"
                    //<!--当月使用 -->
                    +"<curmonthused>10.00</curmonthused>"
                    //<!--当月扣除 -->
                    +"<curmonthdeduct>10.00</curmonthdeduct>"
                    //<!--月末剩余 -->
                    +"<monthclosing>10.00</monthclosing>"
                    //<!--有效期 -->
                    +"<efftime>2012-01-01</efftime>"
                    //<!--备注 -->
                    +"<remark>备注</remark>"
                    //<!-- 使用号码 -->
                    +"<usedtel>13645319981</usedtel>"
                    //<!-- 每月最低消费额度 -->
                    +"<lowestconsume>20.00</lowestconsume>"
                +"</present>"
                +"</presentinfo>"
                
                //----------------------------他人代付信息-----------------------------------------------------------
                +"<payedbyother>"
                +"<payinfo>"
                    //<!--代付号码-->
                    +"<paytelnum>13645319981</paytelnum>"
                    //<!--使用范围-->
                    +"<paytype>使用范围</paytype>"
                    //<!--本月代付-->
                    +"<fee>10.00</fee>"
                +"</payinfo>"
                +"</payedbyother>"
                
                //----------------------------代他人付信息-----------------------------------------------------------
                +"<payedforother>"
                +"<payinfo>"
                    //<!--代付号码-->
                    +"<payedtelnum>13645321545</payedtelnum>"
                    //<!--使用范围-->
                    +"<paytype>使用范围</paytype>"
                    //<!--本月代付-->
                    +"<fee>10.00</fee>"
                +"</payinfo>"
                +"</payedforother>"
                
                //----------------------------代收费业务-----------------------------------------------------------
                
                +"<spbill>"
                +"<sp>"
                    //<!--业务端口-->
                    +"<spcode>spcode</spcode>"
                    //<!--企业名称-->
                    +"<spname>spname</spname>"
                    //<!--业务名称-->
                    +"<servcode>servcode</servcode>"
                    //<!--使用方式-->
                    +"<usetype>usetype</usetype>"
                    //<!--费用类型-->
                    +"<feetype>feetype</feetype>"
                    //<!--处理方式-->
                    +"<fee>10.00</fee>"
                +"</sp>"
                +"</spbill>"
                
                //----------------------------账本入账明细-----------------------------------------------------------
                +"<inoutdetail>"
                +"<inout>"
                    //<!--时间-->
                    +"<date>2010-01-01 12:01:01</date>"
                    //<!--方式-->
                    +"<model>方式</model>"
                    //<!--类别-->
                    +"<type>类别</type>"
                    //<!--金额-->
                    +"<fee>10.00</fee>"
                    //<!--备注-->
                    +"<desc>备注</desc>"
                +"</inout>"
                +"</inoutdetail>"
                
                
                + "</custbill>"
                
                + "</message_content>]]></message_body></message_response>";
                        
                // 历史账单
                //response = "<message_response><message_head version=\"1.0\"><menuid>10000100</menuid><process_code>cli_qry_bill2012_sd</process_code><verify_code></verify_code><resp_time>20120315112855</resp_time><sequence><req_seq>182</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[查询成功]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><custbill><scoreinfo><score><lastavail>0</lastavail><consume>0</consume><award>0</award><transin>0</transin><exchange>0</exchange><transout>0</transout><clear>0</clear><thisavail>0</thisavail><totalin>0</totalin><totalout>0</totalout></score></scoreinfo><pkginfo /><billhalfyeartrend><bill><month>20110901</month><money>2.35</money></bill><bill><month>20111001</month><money>0.00</money></bill><bill><month>20111101</month><money>0.00</money></bill><bill><month>20111201</month><money>0.00</money></bill><bill><month>20120101</month><money>0.00</money></bill><bill><month>20120201</month><money>0.00</money></bill></billhalfyeartrend><recommend>我们根据您最近6个月消费情况进行了测算，您目前使用的资费非常适合您的消费习惯，近期无须变更资费方案。</recommend><acctbalance><acct><lastval>36.17</lastval><backfee>0.00</backfee><transferout>0.00</transferout><transferin>0.00</transferin><contractused>0.00</contractused><income>0.00</income><totalfee>0.00</totalfee><thisval>36.17</thisval><presentval>0.00</presentval><contractval>0.00</contractval><payotherfee>0.00</payotherfee><selftotalfee>0.00</selftotalfee><totalcanuse>36.17</totalcanuse><latefee>0.00</latefee></acct></acctbalance><agreementinfo /><presentinfo /><payedbyother /><payedforother /><spbill><sp><spcode /><spname /><servcode>北京英克必成</servcode><usetype /><feetype /><fee>15.00</fee></sp></spbill><billinfo><billfixed><fee><name>套餐及固定费</name><value>0.00</value><sortorder>10000</sortorder><isshow>1</isshow></fee><feegroup><name>套餐外费用</name><sortorder>0</sortorder><subfee><fee><name>语音通信费</name><value>23.40</value><sortorder>20000</sortorder><isshow>1</isshow></fee><fee><name>短彩信费</name><value>3.50</value><sortorder>22000</sortorder><isshow>1</isshow></fee><fee><name>上网费</name><value>70.35</value><sortorder>23000</sortorder><isshow>1</isshow></fee></subfee></feegroup><fee><name>增值业务费</name><value>28.10</value><sortorder>26000</sortorder><isshow>1</isshow></fee><fee><name>代收费</name><value>15.00</value><sortorder>60000</sortorder><isshow>1</isshow></fee><fee><name>补收费</name><value>0.00</value><sortorder>70000</sortorder><isshow>1</isshow></fee><fee><name>**优惠减免</name><value>140.35</value><sortorder>80000</sortorder><isshow>0</isshow></fee><fee><name>**使用赠款</name><value>0.00</value><sortorder>3</sortorder></fee><fee><name>本月费用合计</name><value>0.00</value><sortorder>4</sortorder></fee></billfixed><feedetails><feegroup><name>套餐外语音通信费</name><sortorder>20000</sortorder><subfee><fee><name>市话主叫通话费</name><value>12.80</value><sortorder>20010</sortorder></fee><fee><name>国内长途费</name><value>0.75</value><sortorder>20110</sortorder></fee><fee><name>省内漫游主叫费</name><value>3.25</value><sortorder>20230</sortorder></fee><fee><name>省内漫游被叫费</name><value>6.00</value><sortorder>20280</sortorder></fee><fee><name>语音短信通信费</name><value>0.60</value><sortorder>20560</sortorder></fee></subfee><fee><name>合计</name><value>23.40</value></fee></feegroup><feegroup><name>套餐外短彩信费</name><sortorder>22000</sortorder><subfee><fee><name>网内短信</name><value>3.40</value><sortorder>22010</sortorder></fee><fee><name>网间短信</name><value>0.10</value><sortorder>22020</sortorder></fee></subfee><fee><name>合计</name><value>3.50</value></fee></feegroup><feegroup><name>套餐外上网费</name><sortorder>23000</sortorder><subfee><fee><name>本地WLAN通信费</name><value>70.35</value><sortorder>23150</sortorder></fee></subfee><fee><name>合计</name><value>70.35</value></fee></feegroup><feegroup><name>增值业务费</name><sortorder>26000</sortorder><subfee><fee><name>关爱e家</name><value>5.00</value><sortorder>26030</sortorder></fee><fee><name>信息点播</name><value>0.10</value><sortorder>27100</sortorder></fee><fee><name>歌曲下载</name><value>2.00</value><sortorder>27230</sortorder></fee><fee><name>飞信会员</name><value>5.00</value><sortorder>27240</sortorder></fee><fee><name>手机报</name><value>9.00</value><sortorder>27300</sortorder></fee><fee><name>号簿管家</name><value>3.00</value><sortorder>27400</sortorder></fee><fee><name>移动全时通功能费</name><value>3.00</value><sortorder>27610</sortorder></fee><fee><name>手机阅读</name><value>1.00</value><sortorder>31330</sortorder></fee></subfee><fee><name>合计</name><value>28.10</value></fee></feegroup><feegroup><name>优惠减免</name><sortorder>80000</sortorder><subfee><fee><name>全球通员工优惠</name><value>37.00</value><sortorder>80600</sortorder></fee><fee><name>合计费优惠150元</name><value>103.35</value><sortorder>80600</sortorder></fee></subfee><fee><name>合计</name><value>140.35</value></fee></feegroup></feedetails></billinfo><inoutdetail /></custbill></message_content>]]></message_body></message_response>";
                // 实时账单
                //response = "<message_response><message_head version=\"1.0\"><menuid>10000100</menuid><process_code>cli_qry_bill2012_sd</process_code><verify_code></verify_code><resp_time>20120315112549</resp_time><sequence><req_seq>178</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[查询成功]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><custbill><scoreinfo><score><lastavail>0</lastavail><consume>0</consume><award>0</award><transin>0</transin><exchange>0</exchange><transout>0</transout><clear>0</clear><thisavail>0</thisavail><totalin>0</totalin><totalout>0</totalout></score></scoreinfo><pkginfo /><billhalfyeartrend><bill><month>20110901</month><money>2.35</money></bill><bill><month>20111001</month><money>0.00</money></bill><bill><month>20111101</month><money>0.00</money></bill><bill><month>20111201</month><money>0.00</money></bill><bill><month>20120101</month><money>0.00</money></bill><bill><month>20120201</month><money>0.00</money></bill></billhalfyeartrend><recommend>我们根据您最近6个月消费情况进行了测算，您目前使用的资费非常适合您的消费习惯，近期无须变更资费方案。</recommend><acctbalance><acct><lastval>0.00</lastval><backfee>0.00</backfee><transferout>0.00</transferout><transferin>0.00</transferin><contractused>0.00</contractused><income>0.00</income><totalfee>0.00</totalfee><thisval>0.00</thisval><presentval>0.00</presentval><contractval>0.00</contractval><payotherfee>0.00</payotherfee><selftotalfee>0.00</selftotalfee><totalcanuse>0.00</totalcanuse><latefee>0.00</latefee></acct></acctbalance><agreementinfo /><presentinfo /><payedbyother /><payedforother /><spbill><sp><spcode /><spname /><servcode>北京英克必成</servcode><usetype /><feetype /><fee>15.00</fee></sp></spbill><billinfo><billfixed><fee><name>套餐及固定费</name><value>58.00</value><sortorder>10000</sortorder><isshow>1</isshow></fee><feegroup><name>套餐外费用</name><sortorder>0</sortorder><subfee><fee><name>语音通信费</name><value>29.50</value><sortorder>20000</sortorder><isshow>1</isshow></fee><fee><name>短彩信费</name><value>3.00</value><sortorder>22000</sortorder><isshow>1</isshow></fee><fee><name>上网费</name><value>0.00</value><sortorder>23000</sortorder><isshow>1</isshow></fee></subfee></feegroup><fee><name>增值业务费</name><value>28.00</value><sortorder>26000</sortorder><isshow>1</isshow></fee><fee><name>代收费</name><value>15.00</value><sortorder>60000</sortorder><isshow>1</isshow></fee><fee><name>补收费</name><value>0.00</value><sortorder>70000</sortorder><isshow>1</isshow></fee><fee><name>**优惠减免</name><value>133.50</value><sortorder>80000</sortorder><isshow>0</isshow></fee><fee><name>**使用赠款</name><value>0.00</value><sortorder>3</sortorder></fee><fee><name>本月费用合计</name><value>0.00</value><sortorder>4</sortorder></fee></billfixed><feedetails><feegroup><name>套餐及固定费</name><sortorder>10000</sortorder><subfee><fee><name>套餐基本费</name><value>58.00</value><sortorder>10110</sortorder></fee></subfee><fee><name>合计</name><value>58.00</value></fee></feegroup><feegroup><name>套餐外语音通信费</name><sortorder>20000</sortorder><subfee><fee><name>市话主叫通话费</name><value>2.40</value><sortorder>20010</sortorder></fee><fee><name>国内长途费</name><value>1.50</value><sortorder>20110</sortorder></fee><fee><name>省内漫游主叫费</name><value>18.35</value><sortorder>20230</sortorder></fee><fee><name>省内漫游被叫费</name><value>7.25</value><sortorder>20280</sortorder></fee></subfee><fee><name>合计</name><value>29.50</value></fee></feegroup><feegroup><name>套餐外短彩信费</name><sortorder>22000</sortorder><subfee><fee><name>网内短信</name><value>3.00</value><sortorder>22010</sortorder></fee></subfee><fee><name>合计</name><value>3.00</value></fee></feegroup><feegroup><name>增值业务费</name><sortorder>26000</sortorder><subfee><fee><name>位置通</name><value>5.00</value><sortorder>26050</sortorder></fee><fee><name>飞信会员</name><value>5.00</value><sortorder>27240</sortorder></fee><fee><name>手机报</name><value>9.00</value><sortorder>27300</sortorder></fee><fee><name>号簿管家</name><value>3.00</value><sortorder>27400</sortorder></fee><fee><name>移动全时通功能费</name><value>3.00</value><sortorder>27610</sortorder></fee><fee><name>新华移动快报VIP套餐B</name><value>2.00</value><sortorder>31230</sortorder></fee><fee><name>手机阅读</name><value>1.00</value><sortorder>31330</sortorder></fee></subfee><fee><name>合计</name><value>28.00</value></fee></feegroup><feegroup><name>优惠减免</name><sortorder>80000</sortorder><subfee><fee><name>合计费优惠150元</name><value>98.50</value><sortorder>80600</sortorder></fee><fee><name>全球通员工优惠</name><value>35.00</value><sortorder>80600</sortorder></fee></subfee><fee><name>合计</name><value>133.50</value></fee></feegroup></feedetails></billinfo><inoutdetail /></custbill></message_content>]]></message_body></message_response>";
                
                //response = "<message_response><message_head version=\"1.0\"><menuid>10000100</menuid><process_code>cli_qry_bill2012_sd</process_code><verify_code></verify_code><resp_time>20120319221737</resp_time><sequence><req_seq>18</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[查询成功]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><custbill><scoreinfo><score><lastavail>0</lastavail><consume>0</consume><award>0</award><transin>0</transin><exchange>0</exchange><transout>0</transout><clear>0</clear><thisavail>0</thisavail><totalin>0</totalin><totalout>0</totalout></score></scoreinfo><pkginfo /><billhalfyeartrend><bill><month>20110901</month><money>147.41</money></bill><bill><month>20111001</month><money>189.98</money></bill><bill><month>20111101</month><money>83.85</money></bill><bill><month>20111201</month><money>112.21</money></bill><bill><month>20120101</month><money>105.73</money></bill><bill><month>20120201</month><money>56.44</money></bill></billhalfyeartrend><recommend>我们根据您最近6个月消费情况进行了测算，您目前使用的资费非常适合您的消费习惯，近期无须变更资费方案。</recommend><acctbalance><acct><lastval>33.12</lastval><backfee>0.00</backfee><transferout>0.00</transferout><transferin>0.00</transferin><contractused>20.00</contractused><income>100.00</income><totalfee>56.44</totalfee><thisval>96.68</thisval><presentval>0.00</presentval><contractval>80.00</contractval><payotherfee>0.00</payotherfee><selftotalfee>56.44</selftotalfee><totalcanuse>153.12</totalcanuse><latefee>0.00</latefee><othersubsfee>0.00</othersubsfee><substotalfee>56.44</substotalfee></acct></acctbalance><agreementinfo><agreement><name>预付240元送花生油2桶(每月最多使用20元，使用期限12个月）</name><lastmonthleft>100.00</lastmonthleft><curmonthpay>0.00</curmonthpay><curmonthreturn>20.00</curmonthreturn><curmonthused>20.00</curmonthused><curmonthdeduct>20.00</curmonthdeduct><monthclosing>80.00</monthclosing><efftime>201212</efftime><remark /><usedtel>13625389523</usedtel><lowestconsume>20.00</lowestconsume></agreement></agreementinfo><presentinfo /><payedbyother /><payedforother /><spbill /><billinfo><billfixed><fee><name>套餐及固定费</name><value>40.00</value><sortorder>10000</sortorder><isshow>1</isshow></fee><feegroup><name>套餐外费用</name><sortorder>0</sortorder><subfee><fee><name>语音通信费</name><value>19.44</value><sortorder>20000</sortorder><isshow>1</isshow></fee><fee><name>短彩信费</name><value>0.00</value><sortorder>22000</sortorder><isshow>1</isshow></fee><fee><name>上网费</name><value>0.00</value><sortorder>23000</sortorder><isshow>1</isshow></fee></subfee></feegroup><fee><name>增值业务费</name><value>9.00</value><sortorder>26000</sortorder><isshow>1</isshow></fee><fee><name>代收费</name><value>0.00</value><sortorder>60000</sortorder><isshow>1</isshow></fee><fee><name>补收费</name><value>0.00</value><sortorder>70000</sortorder><isshow>1</isshow></fee><fee><name>**优惠减免</name><value>12.00</value><sortorder>80000</sortorder><isshow>0</isshow></fee><fee><name>**使用赠款</name><value>0.00</value><sortorder>3</sortorder></fee><fee><name>本月费用合计</name><value>56.44</value><sortorder>4</sortorder></fee></billfixed><feedetails><feegroup><name>套餐及固定费</name><sortorder>10000</sortorder><subfee><fee><name>必选包费</name><value>10.00</value><sortorder>10003</sortorder></fee><fee><name>本地通话优惠功能费</name><value>2.00</value><sortorder>10011</sortorder></fee><fee><name>非常假期功能费</name><value>2.00</value><sortorder>10031</sortorder></fee><fee><name>亲情号码通话优惠功能费</name><value>1.00</value><sortorder>10047</sortorder></fee><fee><name>虚拟网包月费</name><value>10.00</value><sortorder>10056</sortorder></fee><fee><name>短信包月费</name><value>3.00</value><sortorder>10058</sortorder></fee><fee><name>拇指派包月费</name><value>6.00</value><sortorder>10061</sortorder></fee><fee><name>GPRS/3G数据流量套餐费</name><value>6.00</value><sortorder>10067</sortorder></fee></subfee><fee><name>合计</name><value>40.00</value></fee></feegroup><feegroup><name>套餐外语音通信费</name><sortorder>20000</sortorder><subfee><fee><name>省内漫游主叫费</name><value>19.44</value><sortorder>20230</sortorder></fee></subfee><fee><name>合计</name><value>19.44</value></fee></feegroup><feegroup><name>套餐外短彩信费</name><sortorder>22000</sortorder><subfee /><fee><name>合计</name><value>0.00</value></fee></feegroup><feegroup><name>套餐外上网费</name><sortorder>23000</sortorder><subfee /><fee><name>合计</name><value>0.00</value></fee></feegroup><feegroup><name>增值业务费</name><sortorder>26000</sortorder><subfee><fee><name>信息点播</name><value>0.10</value><sortorder>27100</sortorder></fee><fee><name>主叫显示</name><value>2.90</value><sortorder>27170</sortorder></fee><fee><name>手机报</name><value>3.00</value><sortorder>27300</sortorder></fee><fee><name>手机阅读</name><value>3.00</value><sortorder>31330</sortorder></fee></subfee><fee><name>合计</name><value>9.00</value></fee></feegroup><feegroup><name>优惠减免</name><sortorder>80000</sortorder><subfee><fee><name>优惠2元非常假期功能费</name><value>2.00</value><sortorder>80600</sortorder></fee><fee><name>封冻用户vpmn通话费优惠</name><value>10.00</value><sortorder>80600</sortorder></fee></subfee><fee><name>合计</name><value>12.00</value></fee></feegroup></feedetails></billinfo><inoutdetail><inout><date>2012-02-10 12:18:32</date><model>充值卡</model><type>充值</type><fee>100.00</fee><desc /></inout></inoutdetail></custbill></message_content>]]></message_body></message_response>";
                ReturnWrap rw = null;
                try
                {
                    rw = intMsgUtil.parseResponse_MonthFee(response);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                
                return rw;
    }
    /**
     * 校验手机号是否已实名制登记
     * 
     * @param map
     * @return
     */
    public ReturnWrap checkFactNameRegist(Map paramMap)
    {
    	ReturnWrap rwp = new ReturnWrap(); 
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
            		"<message_head version=\"1.0\"><menuid></menuid>" +
            		"<process_code>cli_qry_bindBankCardUserInfo</process_code>" +
            		"<verify_code></verify_code><resp_time>20130922160029</resp_time>" +
            		"<sequence><req_seq>3</req_seq><operation_seq></operation_seq></sequence>" +
            		"<retinfo><rettype>0</rettype><retcode>100</retcode>" +
            		"<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>" +
            		"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
            		"<message_content><custname>易充值</custname>" +
            		"<certtypeid>IdCard</certtypeid>" +
            		"<certid>371520198912200001</certid>" +
            		"</message_content>]]>" +
            		"</message_body>" +
            		"</message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return rwp;
        }
    }

    /**
     * 自动交费操作接口
     * 
     * @param bindBankCardPO
     * @return
     * @see [类、类#方法、类#成员]
     * @modify yWX163692 2013年11月19日 OR_SD_201309_940 易充值二阶段，解约新增自动交费判断流程  
     * @remark modify by sWX219697 2014-12-2 15:33:07 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
     */
	@Override
	public ReturnWrap autoFeeSet(MsgHeaderPO msgHeader, String oprtype, String trigamt, String drawamt) 
	{
		try
        {
			String response = null;
			if("0".equals(oprtype))
			{
				response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid></menuid><process_code>cli_AutoChargeSettleType</process_code><verify_code></verify_code><resp_time>20130922160029</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><trigamt>2000</trigamt><drawamt>2000</drawamt><RETCODE>1</RETCODE><RETMSG>未开通(检查是否开通直连网关产品)</RETMSG></message_content>]]></message_body></message_response>";
			}else if("1".equals(oprtype))
			{
				response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid></menuid><process_code>cli_AutoChargeSettleType</process_code><verify_code></verify_code><resp_time>20130922160029</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><TRIGAMT>2000</TRIGAMT><DRAWAMT>20</DRAWAMT><RETCODE>0</RETCODE><RETMSG>受理成功</RETMSG></message_content>]]></message_body></message_response>";
			}else if("2".equals(oprtype))
			{
				response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid></menuid><process_code>cli_AutoChargeSettleType</process_code><verify_code></verify_code><resp_time>20130922160029</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><TRIGAMT>2000</TRIGAMT><DRAWAMT>20</DRAWAMT><RETCODE>03</RETCODE><RETMSG>手机号码状态异常(业务规则验证失败)</RETMSG></message_content>]]></message_body></message_response>";
			}else if("3".equals(oprtype))
			{
				response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid></menuid><process_code>cli_AutoChargeSettleType</process_code><verify_code></verify_code><resp_time>20130922160029</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><TRIGAMT>2000</TRIGAMT><DRAWAMT>20</DRAWAMT><RETCODE>0</RETCODE><RETMSG>受理成功</RETMSG></message_content>]]></message_body></message_response>";
			}
            //response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid></menuid><process_code>cli_AutoChargeSettleType</process_code><verify_code></verify_code><resp_time>20130922160029</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><TRIGAMT>2000</TRIGAMT><DRAWAMT>20</DRAWAMT><RETCODE>01</RETCODE><RETMSG>用户未开通需要取消的支付方式</RETMSG></message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
	}
	
	/**
     * 易充值签约之前调用接口检查是否满足产品开通条件
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap checkProCondition(Map map)
    {
    	try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+"<menuid>recBindBankCard</menuid><process_code>cli_busi_productreccheck</process_code><verify_code>"
				+"</verify_code><resp_time>20140213134448</resp_time><sequence><req_seq>1</req_seq><operation_seq>"
				+"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]>"
				+"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
				+"<message_content><addncode></addncode><delncode></delncode><curncode></curncode><nextncode /><add_startdate>"
				+"</add_startdate><add_enddate></add_enddate><del_enddate></del_enddate></message_content>]]></message_body>"
				+"</message_response>";
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			
			ReturnWrap rwp = new ReturnWrap();

			return rwp;
		}
    }
    
    // add begin zKF69263 2014/05/09 R003C14L05n01 OR_huawei_201404_1109
    /**
     * 查询未打印的发票记录数据
     * 
     * @param map
     * @return ReturnWrap
     * @remark add by zKF69263 OR_huawei_201404_1109 营业厅全业务流程优化-业务分流(自助终端)_补打缴费发票
     */
    @Override
    public ReturnWrap invoiceList(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            		"<menuid>rePrintInvoice</menuid><process_code>cli_qry_noinvoiceprint_sd</process_code><verify_code></verify_code>" +
            		"<resp_time>20140702175327</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq></sequence>" +
            		"<retinfo><rettype>0</rettype><retcode>0</retcode>" +
            		"<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>" +
            		"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
            		"<invoicelist><recoid>88009898367046</recoid><custname>姓名13563420088</custname><invtype>0</invtype><invoicefee>1000</invoicefee><billcycle>0</billcycle><acctid>6341025258171</acctid><accesstypename>CRM营业厅</accesstypename></invoicelist>" +
            		"<invoicelist><recoid>88009898367046</recoid><custname>姓名13563420088</custname><invtype>1</invtype><invoicefee>1000</invoicefee><billcycle>0</billcycle><acctid>6341025258171</acctid><accesstypename>CRM营业厅</accesstypename></invoicelist>" +
            		"<invoicelist><recoid>88009898367026</recoid><custname>姓名13563420088</custname><invtype>1</invtype><invoicefee>2300</invoicefee><billcycle>0</billcycle><acctid>6341025258171</acctid><accesstypename>CRM营业厅</accesstypename></invoicelist>" +
            		"<invoicelist><recoid>88009898367026</recoid><custname>姓名13563420088</custname><invtype>0</invtype><invoicefee>2300</invoicefee><billcycle>0</billcycle><acctid>6341025258171</acctid><accesstypename>CRM营业厅</accesstypename></invoicelist>" +
            		"</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return new ReturnWrap();
        }
    }
    
    /**
     * 查询要打印的发票打印项数据
     * 
     * @param map
     * @return ReturnWrap
     * @remark add by zKF69263 OR_huawei_201404_1109 营业厅全业务流程优化-业务分流(自助终端)_补打缴费发票
     */
    @Override
    public ReturnWrap invoiceData(Map map)
    {
        try
        {
            // 正确的返回报文
            String response = "<message_response><message_head version=\"1.0\">" +
            		"<menuid>rePrintInvoice</menuid><process_code>cli_qry_invoiceinfo_sd</process_code>" +
            		"<verify_code></verify_code><resp_time>20140710170803</resp_time>" +
            		"<sequence><req_seq>35</req_seq><operation_seq></operation_seq></sequence>" +
            		"<retinfo><rettype>0</rettype><retcode>0</retcode>" +
            		"<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>" +
            		"<message_body><![CDATA[<message_content>" +
            		"<invoicelist><name>ContentItem2</name><value>15.00元</value></invoicelist>" +
            		"<invoicelist><name>ContentItem2Name</name><value>合计</value></invoicelist>" +
            		"<invoicelist><name>ContentItem3</name><value>15.00元</value></invoicelist>" +
            		"<invoicelist><name>telnumber</name><value>15866779032</value></invoicelist>" +
            		"<invoicelist><name>InvoiceNo</name><value></value></invoicelist>" +
            		"<invoicelist><name>formnum</name><value>531140710185729268</value></invoicelist>" +
            		"<invoicelist><name>WorkStation</name><value>济南</value></invoicelist>" +
            		"<invoicelist><name>ContentItem3Name</name><value>本次发票金额</value></invoicelist>" +
            		"<invoicelist><name>note</name><value></value></invoicelist>" +
            		"<invoicelist><name>paynumno</name><value>5318043606733</value></invoicelist>" +
            		"<invoicelist><name>CollectOper</name><value>a1008908</value></invoicelist>" +
            		"<invoicelist><name>time</name><value>2014.07.10</value></invoicelist>" +
            		"<invoicelist><name>ContentItem1Name</name><value>通信服务费</value></invoicelist>" +
            		"<invoicelist><name>rectype</name><value>收费</value></invoicelist>" +
            		"<invoicelist><name>username</name><value>吕友运</value></invoicelist>" +
            		"<invoicelist><name>totalmoneydx</name><value>壹拾伍圆整</value></invoicelist>" +
            		"<invoicelist><name>totalmoney</name><value>15.00</value></invoicelist>" +
            		"<invoicelist><name>ContentItem1</name><value>15.00元</value></invoicelist>" +
            		"</message_content>]]></message_body></message_response>";
            
            // 发票余额不足时返回的报文
            /*String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
                    "<message_response>" +
                    "<message_head version=\"1.0\">" +
                    "<menuid>rePrintInvoice</menuid>" +
                    "<process_code>cli_qry_invoiceinfo</process_code>" +
                    "<verify_code></verify_code>" +
                    "<resp_time>20140520154904</resp_time>" +
                    "<sequence>" +
                    "<req_seq>4</req_seq>" +
                    "<operation_seq></operation_seq>" +
                    "</sequence>" +
                    "<retinfo>" +
                    "<rettype>0</rettype>" +
                    "<retcode>969555</retcode>" +
                    "<retmsg><![CDATA[发票余额[3.00],要使用发票金额[20.00],发票余额不足，不能办理业务.]]></retmsg>" +
                    "</retinfo>" +
                    "</message_head></message_response>";*/     
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    // add end zKF69263 2014/05/09 R003C14L05n01 OR_huawei_201404_1109
    
    /**
     * 话费充值账户应缴费用查询
     * @param paramMap
     * @remark  add by hWX5316476 2014-03-12 OR_SD_201312_90_山东_自助终端交费应交话费显示的优化需求
     * @return 
     */
    public ReturnWrap  qryRetcharge(Map paramMap)
    {
    	try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+"<menuid>feeCharge</menuid><process_code>cli_qry_retcharge</process_code><verify_code></verify_code><resp_time>"
				+"20140314125102</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo>"
				+"<rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[]]></retmsg></retinfo></message_head>"
				+"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><retcharge>2000" 
				+"</retcharge><errmsg>执行成功</errmsg></message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
    }
    
    /**
     * 账单邮件下发接口
     * @param map 
     * @remark  add by sWX219697 2014-04-29 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
     * @return ReturnWrap
     */
    public ReturnWrap sendBillMail(Map<String,String> map)
    {
    	try 
    	{
    	    String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
			+"<menuid>feeCharge</menuid><process_code>cli_qry_retcharge</process_code><verify_code></verify_code><resp_time>"
			+"20140314125102</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo>"
			+"<rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[发送成功]]></retmsg></retinfo></message_head>"
			+"</message_response>";
    	    return intMsgUtil.parseResponse(response);
    	} 
    	catch (Exception e) 
    	{
    	    ReturnWrap rw = new ReturnWrap();
		    rw.setStatus(0);
		    rw.setReturnMsg("");

		    return rw;
    	}
    }
    
    /**
     * 月结发票的账期接口查询
     * @param paramMap
     * @remark add by wWX217192 on 20140504 for OR_huawei_201404_1108 营业厅全业务流程优化-业务分流(自助终端)_打印月结发票
     * @return
     */
    public ReturnWrap qryBillCycle(Map map)
    {
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
            + "<cyclelist><cycle>20150301</cycle><startdate>2015-03-01</startdate><enddate>2015-03-15</enddate><acctid>10000001</acctid><unionacct>1</unionacct><unionacct>2</unionacct></cyclelist>"
            + "<cyclelist><cycle>20150316</cycle><startdate>2015-03-16</startdate><enddate>2015-03-31</enddate><acctid>10000001</acctid><unionacct>0</unionacct><unionacct>4</unionacct></cyclelist>"
            
            + "</message_content>]]></message_body></message_response>";
            
            ReturnWrap rw = null;
            try
            {
                rw = intMsgUtil.parseResponse(response);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
            return rw;
    }
    
    /**
     * 月结发票数据查询接口
     * @param paramMap
     * @remark add by wWX217192 on 20140504 for OR_huawei_201404_1108 营业厅全业务流程优化-业务分流(自助终端)_打印月结发票
     * @return
     */
    public ReturnWrap qryMonthInvoice(Map map)
    {
    	try
        {
    		// 正确的返回报文
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
            		"<message_response><message_head version=\"1.0\">" +
					"<menuid>recMonthInvoice</menuid><process_code>cli_qry_monthinvoiceinfo</process_code>" +
					"<verify_code></verify_code><resp_time>20140707102058</resp_time><sequence><req_seq>106</req_seq>" +
					"<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>" +
					"<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>" +
					"<![CDATA[<message_content>" +
					"<invoicelist><name>username</name><value>刘方彬</value></invoicelist>" +
					"<invoicelist><name>telnumber</name><value>13969002490</value></invoicelist>" +
					"<invoicelist><name>formnum</name><value>88179139782944</value></invoicelist>" +
					"<invoicelist><name>callfeeCreateTime</name><value>20140501-20140531</value></invoicelist>" +
					"<invoicelist><name>WorkStation</name><value>济南</value></invoicelist>" +
					"<invoicelist><name>CollectOper</name><value>aauto001</value></invoicelist>" +
					"<invoicelist><name>time</name><value>2014.07.07</value></invoicelist>" +
					"<invoicelist><name>rectype</name><value>发票打印</value></invoicelist>" +
					"<invoicelist><name>paynumno</name><value>5318001909860</value></invoicelist>" +
					"<invoicelist><name>ContentItem1</name><value>9.90元</value></invoicelist>" +
					"<invoicelist><name>ContentItem1Name</name><value>通信服务费</value></invoicelist>" +
					"<invoicelist><name>ContentItem2</name><value>0.00元</value></invoicelist>" +
					"<invoicelist><name>ContentItem2Name</name><value>合约套餐费</value></invoicelist>" +
					"<invoicelist><name>ContentItem3</name><value>3.00元</value></invoicelist>" +
					"<invoicelist><name>ContentItem3Name</name><value>**折扣折让-优惠减免</value></invoicelist>" +
					"<invoicelist><name>ContentItem4</name><value>0.00元</value></invoicelist>" +
					"<invoicelist><name>ContentItem4Name</name><value>**折扣折让-使用赠款</value></invoicelist>" +
					"<invoicelist><name>ContentItem5</name><value>0.00元</value></invoicelist>" +
					"<invoicelist><name>ContentItem5Name</name><value>**折扣折让-其他</value></invoicelist>" +
					"<invoicelist><name>ContentItem6</name><value>6.90元</value></invoicelist>" +
					"<invoicelist><name>ContentItem6Name</name><value>合计</value></invoicelist>" +
					"<invoicelist><name>ContentItem9</name><value>0.00元</value></invoicelist>" +
					"<invoicelist><name>ContentItem9Name</name><value>已出具发票金额</value></invoicelist>" +
					"<invoicelist><name>ContentItem10</name><value>0.00元</value></invoicelist>" +
					"<invoicelist><name>ContentItem10Name</name><value>    预存款已出具发票金额</value></invoicelist>" +
					"<invoicelist><name>ContentItem11</name><value>0.00元</value></invoicelist>" +
					"<invoicelist><name>ContentItem11Name</name><value>    充值卡已出具发票金额</value></invoicelist>" +
					"<invoicelist><name>ContentItem12</name><value>0.00元</value></invoicelist>" +
					"<invoicelist><name>ContentItem12Name</name><value>    合约款已出具发票金额</value></invoicelist>" +
					"<invoicelist><name>ContentItem13</name><value>6.90元</value></invoicelist>" +
					"<invoicelist><name>ContentItem13Name</name><value>本次发票金额</value></invoicelist>" +
					"<invoicelist><name>InvoiceNo</name><value></value></invoicelist>" +
					"<invoicelist><name>totalmoneydx</name><value>陆圆玖角整</value></invoicelist>" +
					"<invoicelist><name>totalmoney</name><value>6.90</value></invoicelist>" +
					"<invoicelist><name>paynumno</name><value>5318001909860</value></invoicelist>" +
					"</message_content>]]></message_body></message_response>";
    		
    		// 未完全销账的异常返回报文
    		/*String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
    				"<message_response>" +
    				"<message_head version=\"1.0\">" +
    				"<menuid>recMonthInvoice</menuid>" +
    				"<process_code>cli_qry_monthinvoiceinfo</process_code>" +
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
    				"<process_code>cli_qry_monthinvoiceinfo</process_code>" +
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
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * <查询代理商账户信息>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-6-6 09:17:25 OR_huawei_201404_1118 山东_[自助终端]_支撑代理商空中充值续费
     */
    public ReturnWrap qryAgentInfo(Map<String,String> map)
    {
		try 
		{
			String agentInfo = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
			    + "<menuid></menuid><process_code>cli_qry_agentinfo</process_code><verify_code></verify_code>"
			    + "<resp_time>20110901135956</resp_time><sequence><req_seq>21</req_seq><operation_seq>12</operation_seq>"
			    + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
			    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
			    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
			    + "<orgid>111</orgid><orgname>代理商A</orgname><ACCTSUBJECTDETAIL>"
			    + "<acct>12345689</acct><acctname>acctname</acctname><subjectId>333</subjectId>"
			    + "<subjectname>444</subjectname><subjectphone>13964168769</subjectphone><blance>20</blance>"
			    + "</ACCTSUBJECTDETAIL></message_content>]]></message_body></message_response>";
			
			return intMsgUtil.parseResponse(agentInfo);
		} 
		catch (Exception e) 
		{
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
		}
    }
    
    /**
     * 代理商缴费前记录
     * @param map
     * @return
     * @remark create by sWX219697 2014-6-6 OR_huawei_201404_1118 山东_[自助终端]_支撑代理商空中充值续费
     */
    public ReturnWrap beforeAgentCharge(Map<String,String> map)
    {
		try 
		{
			String beforeCharge = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
			    + "<menuid></menuid><process_code>cli_qry_agentinfo</process_code><verify_code></verify_code>"
			    + "<resp_time>20110901135956</resp_time><sequence><req_seq>21</req_seq><operation_seq>12</operation_seq>"
			    + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
			    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
			    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
			    + "<orderno>8888</orderno>"
			    + "</message_content>]]></message_body></message_response>";
			
			return intMsgUtil.parseResponse(beforeCharge);
		} 
		catch (Exception e) 
		{
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
		}
    }
    
    /**
     * 代理商充值
     * @param map
     * @return
     * @remark create by sWX219697 2014-6-6 OR_huawei_201404_1118 山东_[自助终端]_支撑代理商空中充值续费
     */
    public ReturnWrap agentCharge(Map<String,String> map)
    {    	
		try 
		{
			String charge = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
			    + "<menuid></menuid><process_code>cli_qry_agentinfo</process_code><verify_code></verify_code>"
			    + "<resp_time>20110901135956</resp_time><sequence><req_seq>21</req_seq><operation_seq>12</operation_seq>"
			    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
			    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
			    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
			    + "</message_content>]]></message_body></message_response>";
			
			return intMsgUtil.parseResponse(charge);
			
		} 
		catch (Exception e) 
		{
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
		}
		
    }

    /**
     * 查询可变更的主体产品信息
     * @param paramMap
     * @remark add by jWX216858 2014-5-7 OR_huawei_201404_1116_山东_营业厅全业务流程优化-业务分流(自助终端)_转套餐产品
     * @return ReturnWrap
     */
	public ReturnWrap qryMainProdInfo(Map<String, String> paramMap)
	{
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"+
		        "<menuid>recProductChange</menuid><process_code>cli_qry_convertprodinfo</process_code><verify_code>" +
		        "</verify_code><resp_time>20140604153658</resp_time><sequence><req_seq>7</req_seq><operation_seq>" +
		        "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" +
		        "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>" +
		        "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"+
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.68h.634</newprodid><region>634</region>" +
		        "<newprodname>莱芜后付费全球通68套餐</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.tp</newprodid><region>634</region><newprodname>资费套餐</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.88tcy.634</newprodid><region>634</region><newprodname>莱芜预付费全球通新88套餐</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.es.634</newprodid><region>634</region><newprodname>后付费亲情卡轻松打</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.xyh.634</newprodid><region>634</region><newprodname>神州行大众卡夕阳红套餐</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>_pp.eo.pr</newprodid><region>634</region><newprodname>复制的预付费亲情卡</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.lcp</newprodid><region>634</region><newprodname>预付费市话套餐</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>_pp.mz.cyk</newprodid><region>634</region><newprodname>07动感地带</newprodname><brandid>BrandMzone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.cslp</newprodid><region>634</region><newprodname>预付费国内商旅套餐◎◎◎◎</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.szxfsd.634</newprodid><region>634</region><newprodname>莱芜神州行分时打</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.sx.634</newprodid><region>634</region><newprodname>莱芜神州行四喜卡</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.ty</newprodid><region>634</region><newprodname>神州行田园卡</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.ffct.634</newprodid><region>634</region><newprodname>神州行畅听卡发发套餐</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>lixiaojing</newprodid><region>634</region><newprodname>lixiaojing</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.ct1</newprodid><region>634</region><newprodname>后付费全球通畅听套餐1</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.ch.634</newprodid><region>634</region><newprodname>神州行长话卡</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.88rl.new.999</newprodid><region>634</region><newprodname>后付费全球通88套餐（新）</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.qsk.634</newprodid><region>634</region><newprodname>莱芜神州行轻松卡</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.tpp</newprodid><region>634</region><newprodname>预付费资费套餐</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.stp</newprodid><region>634</region><newprodname>预付费标准全球通</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.mz.2007</newprodid><region>634</region><newprodname>2007动感地带</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.68</newprodid><region>634</region><newprodname>莱芜预付费全球通68套餐(JHZL)</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.pr</newprodid><region>634</region><newprodname>预付费亲情卡</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>test.1125</newprodid><region>634</region><newprodname>test1125</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.yg.634</newprodid><region>634</region><newprodname>莱芜后付费全球通阳光套餐</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.68y.634</newprodid><region>634</region><newprodname>莱芜预付费全球通68套餐</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>zzwmainprod</newprodid><region>634</region><newprodname>zzwmainprod</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>test22</newprodid><region>634</region><newprodname>预付费全球通畅听套餐1</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>newmainprod00</newprodid><region>634</region><newprodname>预付费68套餐</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.rl</newprodid><region>634</region><newprodname>后付费亲情卡</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.es</newprodid><region>634</region><newprodname>神州行畅听卡</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.jy.634</newprodid><region>634</region><newprodname>莱芜神州行家园卡</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
		        "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.07szxctk.634</newprodid><region>634</region><newprodname>神州行畅听卡套餐测试奥运</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
		        "</message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
		}
		catch (Exception e) 
		{
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}

	/**
	 * 查询主体产品模板信息
	 * @return map
	 * @remark add by jWX216858 2014-5-8 OR_huawei_201404_1116_山东_营业厅全业务流程优化-业务分流(自助终端)_转套餐产品
	 */
	public ReturnWrap qryProdTemplateInfo(Map<String, String> paramMap)
	{
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"+
	        "<menuid>recProductChange</menuid><process_code>cli_qry_prodtemplateinfo</process_code><verify_code>" +
	        "</verify_code><resp_time>20140605095115</resp_time><sequence><req_seq>8</req_seq><operation_seq></operation_seq>" +
	        "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>" +
	        "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
	        "<resultList><templateid>88009884191614</templateid><templatename>全球通阳光套餐必选业务切换</templatename>" +
	        "<templatedescr></templatedescr><prodid>pp.gt.68h.634</prodid><prodname>莱芜后付费全球通68套餐</prodname>" +
	        "</resultList>" +
	        "</message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
		}
		catch (Exception e) 
		{
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}

	/**
	 * 执行主体产品变更
	 * @param paramMap
     * @return ReturnWrap
	 * @remark add by jWX216858 2014-5-8 OR_huawei_201404_1116_山东_营业厅全业务流程优化-业务分流(自助终端)_转套餐产品
	 */
	public ReturnWrap mainProdChangeRec(Map<String, String> paramMap)
	{
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
					"<menuid>recProductChange</menuid><process_code>cli_busi_prodchangeconfir</process_code>" +
					"<verify_code></verify_code><resp_time>20140612120040</resp_time><sequence><req_seq>5</req_seq" +
					"><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" +
					"<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>" +
					"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
					"<FORMNUM></FORMNUM><RetMOPrivTips>该客户将要办理的业务将自动取消MO套餐且无法恢复，请确认是否继续办理？</RetMOPrivTips>" +
					"<prodchangelist><stauts>A</stauts><prodid>pp.gt.68h.634</prodid>" +
					"<prodname>莱芜后付费全球通68套餐</prodname><startdate>2014-07-01 00:00:00</startdate><enddate></enddate>" +
					"<packageid></packageid><privid>aaaaa</privid><privname>5a优惠</privname>" +
					"<privStartdate>2014-07-01 00:00:00</privStartdate><privEndDate /><reason />" +
					"</prodchangelist><prodchangelist><stauts>A</stauts><prodid>pp.gt.68h.634</prodid>" +
					"<prodname>莱芜后付费全球通68套餐</prodname><startdate>2014-07-01 00:00:00</startdate><enddate></enddate>" +
					"<packageid></packageid><privid>gl.side.A00.634</privid><privname>网内被叫优惠(本地网内单向)</privname>" +
					"<privStartdate>2001-09-01 00:00:00</privStartdate><privEndDate /><reason /></prodchangelist>" +
					"<prodchangelist><stauts>A</stauts><prodid>newbaoxianzhi123</prodid><prodname>包限制121</prodname>" +
					"<startdate>2014-07-01 00:00:00</startdate><enddate></enddate><packageid></packageid><privid></privid>" +
					"<privname></privname><privStartdate></privStartdate><privEndDate /><reason /></prodchangelist>" +
					"<prodchangelist><stauts>A</stauts><prodid>baoxianzhizengzhi4</prodid><prodname>包限制增值4</prodname>" +
					"<startdate>2014-07-01 00:00:00</startdate><enddate>2015-01-01 00:00:00</enddate><packageid>包限制121</packageid>" +
					"<privid>CC12015-01-01 00:00:00</privid><privname>优惠C=1</privname><privStartdate>2014-07-01 00:00:00</privStartdate>" +
					"<privEndDate /><reason /></prodchangelist><prodchangelist><stauts>A</stauts><prodid>baoxianzhi123</prodid>" +
					"<prodname>包限制123</prodname><startdate>2014-07-01 00:00:00</startdate><enddate>2014-10-01 00:00:00</enddate>" +
					"<packageid></packageid><privid></privid><privname></privname><privStartdate></privStartdate><privEndDate />" +
					"<reason /></prodchangelist><prodchangelist><stauts>A</stauts><prodid>gl.base.764014.634</prodid>" +
					"<prodname>短信包2元</prodname><startdate>2014-09-01 00:00:00</startdate><enddate></enddate><packageid>" +
					"</packageid><privid></privid><privname></privname><privStartdate></privStartdate><privEndDate /><reason />" +
					"</prodchangelist><prodchangelist><stauts>A</stauts><prodid>new654654645</prodid><prodname>复制0311sh</prodname>" +
					"<startdate>2014-07-01 00:00:00</startdate><enddate>2014-08-01 00:00:00</enddate><packageid></packageid><privid>" +
					"</privid><privname></privname><privStartdate></privStartdate><privEndDate /><reason /></prodchangelist>" +
					"<prodchangelist><stauts>A</stauts><prodid>fensan.product</prodid><prodname>熊熊产品1</prodname>" +
					"<startdate>2014-07-01 00:00:00</startdate><enddate></enddate><packageid></packageid><privid></privid><privname>" +
					"</privname><privStartdate></privStartdate><privEndDate /><reason /></prodchangelist>" +
					"<prodchangelist><stauts>O</stauts><prodid>SJTZBOON</prodid><prodname>福彩特服</prodname>" +
					"<startdate>2005-02-28 01:18:00</startdate><enddate>2099-01-01 00:00:00</enddate><packageid></packageid>" +
					"<privid></privid><privname></privname><privStartdate></privStartdate><privEndDate /><reason /></prodchangelist>" +
					"<prodchangelist><stauts>O</stauts><prodid>H01</prodid><prodname>ceshi</prodname><startdate>2002-07-02 17:33:40</startdate>" +
					"<enddate></enddate><packageid></packageid><privid></privid><privname></privname><privStartdate></privStartdate>" +
					"<privEndDate /><reason /></prodchangelist><prodchangelist><stauts>O</stauts><prodid>C06</prodid>" +
					"<prodname>呼出限制</prodname><startdate>2004-12-14 14:47:22</startdate><enddate></enddate><packageid>" +
					"</packageid><privid></privid><privname></privname><privStartdate></privStartdate><privEndDate /><reason />" +
					"</prodchangelist><prodchangelist><stauts>O</stauts><prodid>FetionBase</prodid><prodname>飞信基础业务</prodname>" +
					"<startdate>2004-08-03 20:48:00</startdate><enddate></enddate><packageid></packageid><privid></privid>" +
					"<privname></privname><privStartdate></privStartdate><privEndDate /><reason /></prodchangelist>" +
					"<prodchangelist><stauts>O</stauts><prodid>FetionChat</prodid><prodname>飞信交友服务</prodname>" +
					"<startdate>2004-08-03 20:48:00</startdate><enddate></enddate><packageid></packageid><privid></privid>" +
					"<privname></privname><privStartdate></privStartdate><privEndDate /><reason /></prodchangelist>" +
					"<prodchangelist><stauts>O</stauts><prodid>1258ZWMS</prodid><prodname>1258移动秘书</prodname>" +
					"<startdate>1999-12-09 16:41:18</startdate><enddate></enddate><packageid></packageid><privid></privid>" +
					"<privname></privname><privStartdate></privStartdate><privEndDate /><reason /></prodchangelist>" +
					"<prodchangelist><stauts>O</stauts><prodid>Z07</prodid><prodname>移动全时通C</prodname>" +
					"<startdate>2006-03-24 09:36:36</startdate><enddate></enddate><packageid></packageid>" +
					"<privid></privid><privname></privname><privStartdate></privStartdate><privEndDate /><reason />" +
					"</prodchangelist><prodchangelist><stauts>O</stauts><prodid>pg.vo.vpmn.it</prodid><prodname>智能VPMN虚拟网</prodname>" +
					"<startdate>2008-03-05 17:51:43</startdate><enddate></enddate><packageid></packageid><privid></privid>" +
					"<privname></privname><privStartdate></privStartdate><privEndDate /><reason /></prodchangelist>" +
					"<prodchangelist><stauts>D</stauts><prodid>M03</prodid><prodname>国内漫游开通</prodname>" +
					"<startdate>1999-12-09 16:41:18</startdate><enddate>2014-07-01 00:00:00</enddate><packageid>漫游优惠包(2008)</packageid>" +
					"<privid></privid><privname></privname><privStartdate></privStartdate><privEndDate /><reason /></prodchangelist>" +
					"<prodchangelist><stauts>D</stauts><prodid>pkg_roamid</prodid><prodname>漫游优惠包(2008)</prodname>" +
					"<startdate>1999-12-09 16:41:18</startdate><enddate>2014-07-01 00:00:00</enddate><packageid></packageid>" +
					"<privid></privid><privname></privname><privStartdate></privStartdate><privEndDate /><reason /></prodchangelist>" +
					"<prodchangelist><stauts>D</stauts><prodid>pp.gt.st</prodid><prodname>标准全球通</prodname>" +
					"<startdate>1999-12-09 16:41:18</startdate><enddate>2014-07-01 00:00:00</enddate><packageid></packageid>" +
					"<privid>pp.gt.st.6342014-07-01 00:00:00</privid><privname>全球通品牌优惠(后付费)</privname>" +
					"<privStartdate>1999-12-09 16:41:18</privStartdate><privEndDate /><reason /></prodchangelist>" +
					"<prodchangelist><stauts>D</stauts><prodid>pkg_bjby.634</prodid><prodname>本地被叫包月优惠包</prodname>" +
					"<startdate>2005-04-01 00:00:00</startdate><enddate>2014-07-01 00:00:00</enddate><packageid></packageid>" +
					"<privid>gl.m.mon144.6342014-07-01 00:00:00</privid><privname>0元包300分钟,用户在本地做被叫,主叫任意</privname>" +
					"<privStartdate>2005-04-01 00:00:00</privStartdate><privEndDate /><reason /></prodchangelist>" +
					"</message_content>]]></message_body></message_response>";
			
			String response2 = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
					"<menuid>recProductChange</menuid><process_code>cli_qry_prodtemplateinfo</process_code><verify_code>" +
					"</verify_code><resp_time>20140604181921</resp_time><sequence><req_seq>8</req_seq><operation_seq></operation_seq>" +
					"</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>" +
					"</retmsg></retinfo></message_head></message_response>";
			if("".equals(paramMap.get("PREPAREBUSI")))
			{
				return intMsgUtil.parseResponse(response2);
			}
			else
			{
				return intMsgUtil.parseResponse(response);
			}
			
		}
		catch (Exception e) 
		{
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}
	
    /**
     * 验证SIM卡
     * @param paramMap
     * @return
     * @remark add by hWX5316476 2014-06-23 OR_SD_201405_849_关于在营业厅增加实名制认证的功能
     */
    public ReturnWrap chkSIMCardNo(Map<String,String> paramMap)
    {
        try
        {
            String response1 = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
                    "<message_head version=\"1.0\"><menuid>realNameReg</menuid><process_code>" +
                    "cli_busi_chksimcardno</process_code><verify_code></verify_code><resp_time>" +
                    "20140628112946</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq>" +
                    "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]>" +
                    "</retmsg></retinfo></message_head><message_body>" +
                    "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><authchkresult>0</authchkresult>" +
                    "<authchkmsg>该用户[13806346789]输入的卡号[89860080150257C40644]与用户表数据不一致，请重新输入.</authchkmsg>" +
                    "</message_content>]]></message_body></message_response>";
            String response =  "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
                    "<message_head version=\"1.0\"><menuid>realNameReg</menuid><process_code>" +
                    "cli_busi_chksimcardno</process_code><verify_code></verify_code><resp_time>" +
                    "20140628112946</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq>" +
                    "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]>" +
                    "</retmsg></retinfo></message_head><message_body>" +
                    "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><authchkresult>1</authchkresult>" +
                    "<authchkmsg></authchkmsg>" +
                    "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            return rw;
        }
    }
    
    /**
     * 充值记录认证
     * @param paramMap
     * @return 
     * @remark add by hWX5316476 2014-06-24 V200R003C10LG0601 OR_SD_201405_849_关于在营业厅增加实名制认证的功能
     */
    public ReturnWrap chkChargeRecord(Map<String, Object> paramMap)
    {
        try 
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
                    "<message_head version=\"1.0\"><menuid>realNameReg</menuid><process_code>cli_busi_chkchargerecord</process_code>" +
                    "<verify_code></verify_code><resp_time>20140630113534</resp_time><sequence><req_seq>2</req_seq><operation_seq>" +
                    "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]>" +
                    "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
                    "<message_content><authchkresult>1</authchkresult><authchkmsg></authchkmsg></message_content>]]>" +
                    "</message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("充值记录验证异常，请稍后重试！");
            return rw;
        }
    }
    
    /**
     * 通话记录验证
     * @param paramMap
     * @return
     * @remark add by hWX5316476 2014-06-24 V200R003C10LG0601 OR_SD_201405_849_关于在营业厅增加实名制认证的功能
     */
    public ReturnWrap chkCallRecord(Map<String, String> paramMap)
    {
        try 
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            		"<menuid>realNameReg</menuid><process_code>cli_busi_chkcallrecord</process_code><verify_code>" +
            		"</verify_code><resp_time>20140710135217</resp_time><sequence><req_seq>4</req_seq><operation_seq>" +
            		"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]>" +
            		"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
            		"<message_content><authchkresult>1</authchkresult><authchkmsg></authchkmsg></message_content>]]>" +
            		"</message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("通话记录验证异常，请稍后重试！");
            return rw;
        }
    }
    
    /**
     * 记录实名制认证受理日志
     * @param paramMap
     * @return ReturnWrap
     * @remark add by hWX5316476 2014-06-24 OR_SD_201405_849_关于在营业厅增加实名制认证的功能
     */
    public ReturnWrap saveRealNameChkRecLog(Map<String,String> paramMap,Map<String,String> map)
    {
        try 
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            		"<menuid>realNameReg</menuid><process_code>cli_busi_saverealnamechkreclog</process_code><verify_code>" +
            		"</verify_code><resp_time>20140630120106</resp_time><sequence><req_seq>3</req_seq><operation_seq>" +
            		"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]>" +
            		"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
            		"<message_content><authchkresult></authchkresult><authchkmsg></authchkmsg></message_content>]]>" +
            		"</message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(1);
            rw.setReturnMsg("记录实名制认证受理日志异常");
            return rw;
        }
    }
    
    /**
	 * 查询用户实名制登记标志
	 * @param 手机号码
	 * @param 终端信息
	 * @param 菜单信息
	 * @return 接口调用成功与否的标志位及接口返回信息
	 * @remark create wWX217192 2014-06-23 OR_huawei_201406_338
	 */
	public ReturnWrap qryRealNameType(Map<String, String> map) 
	{
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_response><message_head version=\"1.0\"><menuid>recNonRegister</menuid>" +
					"<process_code>cli_qry_custrealnametype_sd</process_code><verify_code></verify_code>" +
					"<resp_time>20140627170820</resp_time>" +
					"<sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence>" +
					"<retinfo><rettype>0</rettype><retcode>100</retcode>" +
					"<retmsg><![CDATA[]]></retmsg></retinfo></message_head>" +
					"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_content><isrealname>0</isrealname><realnametype>invalid</realnametype></message_content>]]>" +
					"</message_body></message_response>";
			
			return intMsgUtil.parseResponse(response);
		}
		catch(Exception e)
		{
			ReturnWrap rw = new ReturnWrap();
			
			rw.setStatus(0);
			rw.setReturnMsg("");
			return rw;
		}
	}
	
	/**
	 * 生成短信验证随机码
	 * 
	 * @param map
	 * @return 接口返回信息
	 * @remark create wWX217192 2014-06-24 OR_huawei_201406_338
	 */
	public ReturnWrap getRandomPwd(Map<String, String> map)
	{
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_response><message_head version=\"1.0\"><menuid></menuid>" +
					"<process_code>cli_busi_randompwd</process_code><verify_code></verify_code>" +
					"<resp_time>20140628151310</resp_time><sequence><req_seq>2</req_seq>" +
					"<operation_seq></operation_seq></sequence>" +
					"<retinfo><rettype>0</rettype><retcode>100</retcode>" +
					"<retmsg><![CDATA[]]></retmsg></retinfo></message_head>" +
					"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_content><new_passwd>212130</new_passwd>" +
					"<old_passwd>212130</old_passwd><randpassoid>1403986390</randpassoid>" +
					"</message_content>]]></message_body></message_response>";
			
			return intMsgUtil.parseResponse(response);
		}
		catch(Exception e)
		{
			ReturnWrap rw = new ReturnWrap();
			
			rw.setStatus(0);
			rw.setReturnMsg("");
			
			return rw;
		}
	}
	
	/**
	 * 短信随机密码验证
	 * 
	 * @param map
	 * @return 接口返回信息
	 * @remark create wWX217192 2014-06-25 OR_huawei_201406_338
	 */
	public ReturnWrap checkRandomPwd(Map<String, String> map)
	{
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_response><message_head version=\"1.0\"><menuid>recNonRegister</menuid>" +
					"<process_code>cli_busi_chkrandompwd</process_code><verify_code></verify_code>" +
					"<resp_time>20140628152809</resp_time><sequence><req_seq>3</req_seq>" +
					"<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype>" +
					"<retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo></message_head>" +
					"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_content><authchkresult>1</authchkresult><authchkmsg></authchkmsg>" +
					"</message_content>]]></message_body></message_response>";
			
			return intMsgUtil.parseResponse(response);
		}
		catch(Exception e)
		{
			ReturnWrap rw = new ReturnWrap();
			
			rw.setStatus(0);
			rw.setReturnMsg("");
			
			return rw;
		}
	}
	
	/**
	 * 个人密码验证接口
	 * 
	 * @param map
	 * @return 接口返回信息
	 * @remark create wWX217192 2014-06-25 OR_huawei_201406_338
	 */
	public ReturnWrap checkUserPwd(Map<String, String> map)
	{
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_response><message_head version=\"1.0\"><menuid>recNonRegister</menuid>" +
					"<process_code>cli_busi_chkserverpwd</process_code><verify_code></verify_code>" +
					"<resp_time>20140628171851</resp_time><sequence><req_seq>4</req_seq>" +
					"<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype>" +
					"<retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo></message_head>" +
					"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_content><authchkresult>1</authchkresult><authchkmsg></authchkmsg>" +
					"</message_content>]]></message_body></message_response>";
			
			return intMsgUtil.parseResponse(response);
		}
		catch(Exception e)
		{
			ReturnWrap rw = new ReturnWrap();
			
			rw.setStatus(0);
			rw.setReturnMsg("");
			
			return rw;
		}
	}
	
    /**
     * 代理商充值金额校验
     * @param msgHeader 消息头
     * @param orgId 组织机构编码
     * @param agentAccount 资金账户编码
     * @param subjectId 科目编码
     * @param tMoney 充值金额 分
     * @return
     * @remark create by sWX219697 2014-8-23 10:43:09 OR_huawei_201408_657_自助终端代理商资金账户充值功能优化
     */
	public ReturnWrap checkBeforeAgentCharge(MsgHeaderPO msgHeader, String orgId, 
			String agentAccount, String subjectId, String tMoney)
	{
	    try 
		{
			String charge = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
			    + "<menuid></menuid><process_code>cli_qry_agentinfo</process_code><verify_code></verify_code>"
			    + "<resp_time>20110901135956</resp_time><sequence><req_seq>21</req_seq><operation_seq>12</operation_seq>"
			    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
			    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
			    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
			    + "</message_content>]]></message_body></message_response>";
			
			return intMsgUtil.parseResponse(charge);
			
		} 
		catch (Exception e) 
		{
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
		}
        
	}

	/**
	 * <查询用户的付费类型>
	 * <功能详细描述>
	 * @param msgHeader 消息头部
	 * @return
	 * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-10-9 10:08:30 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
	 */
	public ReturnWrap qrySubsPrepayType(MsgHeaderPO msgHeader) 
	{
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_response><message_head version=\"1.0\"><menuid></menuid>" +
					"<process_code></process_code><verify_code></verify_code>" +
					"<resp_time>20140628171851</resp_time><sequence><req_seq>4</req_seq>" +
					"<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype>" +
					"<retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo></message_head>" +
					"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_content><prepaytype>1</prepaytype>" +
					"</message_content>]]></message_body></message_response>";
			
			return intMsgUtil.parseResponse(response);
		}
		catch(Exception e)
		{
			ReturnWrap rtw = new ReturnWrap();
			
			return rtw;
		}
	}

	/**
	 * 上网流量受理
	 * @param header 消息头
	 * @param productset 增值产品串(产品包,增值产品,优惠;产品包,增值产品,优惠;)
	 * @return
	 * @remark create by jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_山东_关于自助终端产品变更功能添加4G自选套餐以及修改GPRS和4G互斥的功能（全业务流程优化）
	 */
	@Override
	public ReturnWrap gprsWlanRec(MsgHeaderPO header, String productset) {
		try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            		"<menuid>recMainProdChange</menuid><process_code>cli_busi_GprsWlanRec</process_code><verify_code>" +
            		"</verify_code><resp_time>20130514140538</resp_time><sequence><req_seq>1</req_seq><operation_seq>" +
            		"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" +
            		"<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response> ";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            
            return rw;
        }
	}

	/**
	 * 语音通话受理
	 * @param msgHeader 报文头信息
	 * @param nCode nCode
	 * @return
	 * @remark create by jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_山东_关于自助终端产品变更功能添加4G自选套餐以及修改GPRS和4G互斥的功能（全业务流程优化）
	 */
	@Override
	public ReturnWrap voiceCallRec(MsgHeaderPO msgHeader, String code) {
		try
        {
            
            String resp = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
            		"<message_response><message_head version=\"1.0\">" +
            		"<menuid>recMainProdChange</menuid><process_code>" +
            		"cli_busi_ChangeProductSubmit</process_code><verify_code></verify_code>" +
            		"<unicontact></unicontact><resp_time>20120425100313</resp_time><sequence>" +
            		"<req_seq>5</req_seq><operation_seq></operation_seq></sequence><retinfo>" +
            		"<rettype>0</rettype><retcode>100</retcode><retmsg>" +
            		"<![CDATA[Processing the request succeeded!]]>" +
            		"</retmsg></retinfo></message_head></message_response>";
            
            return intMsgUtil.parseResponse(resp);
        }
        catch (Exception e)
        {
            return new ReturnWrap();
        }
	}
	
	/**
	 * <查询易充值用户副号码列表>
	 * <功能详细描述>
	 * @param msgHeader
	 * @param ncode
	 * @param stype
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @remark sWX219697 2014-12-2 19:36:41 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
	 */
	public ReturnWrap viceNumberQry(MsgHeaderPO msgHeader, String ncode, String stype)
	{
		try 
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_response><message_head version=\"1.0\">" +
					"<menuid>recAgentCharge</menuid>" +
					"<process_code>cli_qry_vicenum</process_code>" +
					"<verify_code></verify_code>" +
					"<resp_time>20141202175602</resp_time>" +
					"<sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence>" +
					"<retinfo><rettype>0</rettype>" +
					"<retcode>100</retcode>" +
					"<retmsg><![CDATA[已开通]]></retmsg></retinfo>" +
					"</message_head>" +
					"<message_body>" +
					"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_content>" +
					"<outparam>13963478598~18660165052~18660165052~18660165052~18660165052~18660165052~FriendNum6~FriendNum7~FriendNum8~FriendNum9</outparam>" +
					"<outparamsplit>~</outparamsplit>" +
					"</message_content>]]>" +
					"</message_body>" +
					"</message_response>";

			return intMsgUtil.parseResponse(response);
		} 
		catch (Exception e) 
		{
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
		}
	}
	
	
	/**
	 * <新增、删除易充值副号码>
	 * <功能详细描述>
	 * @param msgHeader
	 * @param viceArray 副号码数组
	 * @param opertype 操作类型，1：新增，2：删除
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @remark sWX219697 2014-12-4 12:00:03 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
	 */
	public ReturnWrap viceNumberSet(MsgHeaderPO msgHeader, String[] viceArray, String opertype)
	{
        ReturnWrap rw = new ReturnWrap();
        rw.setStatus(1);
        rw.setReturnMsg("");
        
        return rw;
	}
	
	/**
	 * 查询当前用户是否签约和包易充值
	 * @param headerPo 消息头
	 * @return 用户签约信息
	 * @remark create by wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap checkHeBao(MsgHeaderPO headerPo, String bankCardNum)
	{
		try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
    		"<message_head version=\"1.0\"><menuid>recBindBankCard</menuid>" +
    		"<process_code>cli_busi_SDHBTelQryRegist</process_code><verify_code></verify_code>" +
    		"<resp_time>20150407095636</resp_time>" +
    		"<sequence><req_seq>231</req_seq><operation_seq></operation_seq></sequence>" +
    		"<retinfo><rettype>0</rettype><retcode>100</retcode>" +
    		"<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>" +
    		"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
    		"<message_content><AGRNO></AGRNO><BANKABBR></BANKABBR><cardType></cardType>" +
    		"<cardNo>6225885415787779</cardNo><status>2</status>" +
    		"<signDate></signDate><signTime></signTime></message_content>]]>" +
    		"</message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
	}
	
	/**
	 * 和包易充值平台发送短信随机密码
	 * @param headerPo 请求报文头
	 * @param smsType 验证码类型
	 * @param AGRNO 协议号
	 * @return 和包易充值平台的返回报文
	 * @remark create by wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap sendHeBaoRandom(MsgHeaderPO headerPo, String smsType, BindBankCardPO cardPo)
	{
		try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recBindBankCard</menuid>" +
            		"<process_code>cli_busi_SDHBTelGetSmsChkCode</process_code><verify_code></verify_code><resp_time>20141212143317</resp_time>" +
            		"<sequence><req_seq>5</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>" +
            		"<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>" +
            		"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><tradeNo>203409300072234651</tradeNo>" +
            		"</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
	}
	
	/**
	 * 和包易充值签约
	 * @param headerPo
	 * @param cardPo
	 * @param smsCode
	 * @return
	 * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap signHeBao(MsgHeaderPO headerPo, BindBankCardPO cardPo, String smsCode)
	{
		try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recBindBankCard</menuid>" +
            		"<process_code>cli_busi_SDHBTelRegist</process_code><verify_code></verify_code><resp_time>20141212170915</resp_time><sequence>" +
            		"<req_seq>12</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" +
            		"<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>" +
            		"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><AGRNO>0001U94AdNRDj826tm2hnP9c4zngdwlEv36Y</AGRNO>" +
            		"</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
	}
	
	/**
	 * 和包易充值解约接口
	 * @param headerPo
	 * @param cardPo
	 * @param smsCode
	 * @return
	 * @remark create by wWX217192 2014-11-29 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap unsignHeBao(MsgHeaderPO headerPo, BindBankCardPO cardPo, String smsCode)
	{
		try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recBindBankCard</menuid>" +
            		"<process_code>cli_busi_SDHBTelUnRegist</process_code><verify_code></verify_code><resp_time>20141212171046</resp_time>" +
            		"<sequence><req_seq>10</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>" +
            		"<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
	}
	
	
	/**
	 * 和包易充值自动交费功能设置
	 * @param headerPo
	 * @param oprType
	 * @param trigAmt
	 * @param drawAmt
	 * @param bankId
	 * @return
	 * @remark create by wWX217192 2014-12-10 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
	 */
	public ReturnWrap setHeBaoAutoValue(MsgHeaderPO headerPo, String oprType, BankCardInfoPO bankCardInfoPO)
	{
		try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recBindBankCard</menuid>" +
            		"<process_code>cli_busi_ChgMobilePaySettleType</process_code><verify_code></verify_code><resp_time>20141212180323</resp_time>" +
            		"<sequence><req_seq>3</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>" +
            		"<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
	}
	
	/**
     * 补卡时检验手机号码和身份证信息是否一致
     * @param msgHeader
     * @param idCardNo
     * @return ReturnWrap
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap checkReissueIdCard(MsgHeaderPO msgHeader, String idCardNo) 
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
                + "<menuid>prestoredReward</menuid><process_code>cli_busi_checkRecValid</process_code><verify_code></verify_code>" 
                + "<resp_time>20141211172813</resp_time><sequence><req_seq>26</req_seq><operation_seq></operation_seq></sequence>" 
                + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo></message_head>" 
                + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><ifvalid>1</ifvalid>" 
                + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }

    /**
     * 校验用户的补卡次数
     * @param msgHeader
     * @return ReturnWrap
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap checkReissueNum(MsgHeaderPO msgHeader) 
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
                + "<menuid>prestoredReward</menuid><process_code>cli_busi_checkRecValid</process_code><verify_code></verify_code>" 
                + "<resp_time>20141211172813</resp_time><sequence><req_seq>26</req_seq><operation_seq></operation_seq></sequence>" 
                + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo></message_head>" 
                + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><retinfo>1</retinfo>" 
                + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * <补卡算费>
     * <功能详细描述>
     * @param msgHeader
     * @param iccid
     * @param blankCardNum 空白卡卡号
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap countReissueFee(MsgHeaderPO msgHeader, String iccid, String blankCardNum)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
                + "<menuid>prestoredReward</menuid><process_code>cli_busi_checkRecValid</process_code><verify_code></verify_code>" 
                + "<resp_time>20141211172813</resp_time><sequence><req_seq>26</req_seq><operation_seq></operation_seq></sequence>" 
                + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo></message_head>" 
                + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" 
                + "<feelist><feeName>补卡预存</feeName><fee>5000</fee><privfee>1000</privfee><feekind></feekind><feetype></feetype></feelist></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * <补卡提交>
     * <功能详细描述>
     * @param msgHeader
     * @param recFee 应缴费用
     * @param payType 支付方式
     * @param blankno 空白卡卡号     
     * @param simInfo sim卡信息
     * @param bankNo 银行编号
     * @param bankNbr 银行缴费流水号
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap reissueCommit(MsgHeaderPO msgHeader, String recFee, String payType, 
            String blankno, SimInfoPO simInfo, String bankNo, String bankNbr)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
                + "<menuid>prestoredReward</menuid><process_code>cli_busi_checkRecValid</process_code><verify_code></verify_code>" 
                + "<resp_time>20141211172813</resp_time><sequence><req_seq>26</req_seq><operation_seq></operation_seq></sequence>" 
                + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo></message_head>" 
                + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><recoid>123455666646</recoid>" 
                + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
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
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
                + "<menuid>prestoredReward</menuid><process_code>cli_busi_checkRecValid</process_code><verify_code></verify_code>" 
                + "<resp_time>20141211172813</resp_time><sequence><req_seq>26</req_seq><operation_seq></operation_seq></sequence>" 
                + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo></message_head>" 
                + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><qryResult>1</qryResult>" 
                + "<subscriber>acctID=7115179983706</subscriber></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
	
	/**
	 * 业务有效性校验
	 * 
	 * @param msgHeader 消息头
	 * @return true：可以继续办理业务，false：终止办理业务
	 * @see [类、类#方法、类#成员]
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap checkRecValid(MsgHeaderPO msgHeader)
	{
		try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
            	+ "<menuid>prestoredReward</menuid><process_code>cli_busi_checkRecValid</process_code><verify_code></verify_code>" 
            	+ "<resp_time>20141211172813</resp_time><sequence><req_seq>26</req_seq><operation_seq></operation_seq></sequence>" 
            	+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo></message_head>" 
            	+ "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><fromptMsg></fromptMsg>" 
            	+ "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
	}
	
	/**
	 * 查询用户已经存在的档次
	 * @param msgHeader 信息头
	 * @return
	 * @remark create by jWX216858 2014-11-29 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap qrySubsActLevelList(MsgHeaderPO msgHeader) 
	{
		try
        {
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
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
	}
	
	/**
	 * 查询奖品列表
	 * @param msgHeader 消息头
	 * @param actLevelId 档次编码
	 * @param activityId 活动编码
	 * @return
	 * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap qryRewardList(MsgHeaderPO msgHeader, String actLevelId, String activityId)
	{
		try
        {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
				+ "<menuid>prestoredReward</menuid><process_code>cli_qry_rewardListSD</process_code><verify_code></verify_code>" 
				+ "<resp_time>20141211172815</resp_time><sequence><req_seq>28</req_seq><operation_seq></operation_seq></sequence>" 
				+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" 
				+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" 
				+ "<rewardlist><rewardid>88009887588632</rewardid><rewardName>审核测试</rewardName><rewardType>RwdGift_fee</rewardType>" 
				+ "<rewardValue>0</rewardValue><scoreDealType>0</scoreDealType><userScore>0</userScore><rewardNote></rewardNote>" 
				+ "</rewardlist></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
	}
	
	/**
	 * 查询活动费用
	 * @param msgHeader 消息头
	 * @param actid 活动编码
	 * @param levelid 档次编码
	 * @param rewardId 奖品编码
	 * @return
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap qryActivityFee(MsgHeaderPO msgHeader, String actid, String levelid, String rewardId)
	{
		try
        {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
				+ "<menuid>prestoredReward</menuid><process_code>cli_qry_chkPrivAndCalcFee</process_code><verify_code>" 
				+ "</verify_code><resp_time>20141212100413</resp_time><sequence><req_seq>5</req_seq><operation_seq>" 
				+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" 
				+ "<![CDATA[]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" 
				+ "<message_content><prsFeeInfo><feeid>Charge_P01</feeid><feeValue>10000</feeValue>" 
				+ "<feeName>预存的本金费用项目</feeName></prsFeeInfo></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
	}
	
	/**
	 * 预存有礼受理
	 * 
     * @param msgHeader 消息头
     * @param prestoredRewardPO 入参 
     * @param bankNo 银行编号
     * @param bankNbr 银行缴费流水号
	 * @return
	 * @remark create by jWX216858 2014-12-08 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap recRewardCommit(MsgHeaderPO msgHeader, PrestoredRewardPO prestoredRewardPO, String bankNo,
        String bankNbr)
	{
		try
        {
			// 预受理
			if ("1".equals(prestoredRewardPO.getOnlycheck()))
			{
				String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
					+ "<menuid>prestoredReward</menuid><process_code>cli_busi_recRewardCommitSD</process_code><verify_code>" 
					+ "</verify_code><resp_time>20141215114451</resp_time><sequence><req_seq>7</req_seq><operation_seq>" 
					+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" 
					+ "<![CDATA[]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" 
					+ "<message_content><recoid></recoid></message_content>]]></message_body></message_response>";
	            return intMsgUtil.parseResponse(response);
			}
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
				+ "<menuid>prestoredReward</menuid><process_code>cli_busi_recRewardCommitSD</process_code><verify_code>" 
				+ "</verify_code><resp_time>20141215114451</resp_time><sequence><req_seq>7</req_seq><operation_seq>" 
				+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" 
				+ "<![CDATA[受理成功]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" 
				+ "<message_content><recoid>88009899496519</recoid></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
	}
	
	/** 
     * 业务办理前记录业务费用信息
     * 
     * @param msgHeader 报文请求头
     * @param bankNo 银行号
     * @param payDate 缴费日期,格式：YYYYMMDDHH24MISS
     * @param acceptType 业务类型(开户：ZZKH 补卡：ZZBK 预存赠：ZZHD)
     * @param bankNbr 唯一流水(和agentcharge表的AGENTFORMNUM字段保持一致) ，终端机返回的termseq
     * @param amount 订单金额
     * @param prestoredRewardPO 活动信息
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create by zKF69263 2015-05-07 OR_SD_201503_333_SD_自助终端活动受理预存赠送
     */
    public ReturnWrap writeNetFeeInfo(MsgHeaderPO msgHeader, String bankNo, String acceptType,
        String bankNbr, String amount, PrestoredRewardPO prestoredRewardPO)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
                "<menuid></menuid><process_code>cli_busi_lockOrUnLockTelNum</process_code><verify_code>" +
                "</verify_code><resp_time>20150119144407</resp_time><sequence><req_seq>45</req_seq>" +
                "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode>" +
                "<retmsg><![CDATA[]]></retmsg></retinfo></message_head></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
	
	/**
	 * 查询营销方案费用和用户预存费用
	 * @param msgHeader 消息头
	 * @param recoid 受理流水
	 * @param totalFee 用户存入的费用
	 * @return
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
	 */
	public ReturnWrap qryRecFeeAndPreFee(MsgHeaderPO msgHeader, String recoid, String totalFee)
	{
		try
        {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
				+ "<menuid>prestoredReward</menuid><process_code>cli_qry_privFeeSD</process_code><verify_code>" 
				+ "</verify_code><resp_time>20141212101304</resp_time><sequence><req_seq>7</req_seq><operation_seq>" 
				+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" 
				+ "<![CDATA[查询营销案费用和用户的预存款成功!]]></retmsg></retinfo></message_head><message_body>" 
				+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><resultList>" 
				+ "<recFee>10000</recFee><preFee>5000</preFee></resultList></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
	}
	
	/**
     * 办理特惠业务包
     * @param msgHeader
     * @param privServPackPO
     * @return
     * @remark create by hWX5316476 2014-12-24 OR_SD_201410_482_SD_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
     */
    public ReturnWrap privServPackRec(MsgHeaderPO msgHeader,PrivServPackPO privServPackPO)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
                    "<message_head version=\"1.0\"><menuid>privServPack</menuid>" +
                    "<process_code>cli_busi_privServPackCommit</process_code><verify_code>" +
                    "</verify_code><resp_time>20141216164935</resp_time><sequence><req_seq>18</req_seq>" +
                    "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype>" +
                    "<retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo></message_head>" +
                    "</message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 根据主体产品Id获取主体产品信息
     * 
     * @param msgHeader
     * @param prodid
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-1-9 OR_SD_201411_411_SD_关于自助终端产品受理功能优化的需求
     */
    public ReturnWrap qryProdInfoById(MsgHeaderPO msgHeader,String prodid, String type)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
                + "<menuid>prestoredReward</menuid><process_code>cli_qry_chkPrivAndCalcFee</process_code><verify_code>" 
                + "</verify_code><resp_time>20141212100413</resp_time><sequence><req_seq>5</req_seq><operation_seq>" 
                + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" 
                + "<![CDATA[]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" 
                + "<message_content><prodInfo><prodid>" +
                	//	"pp.gt.ygtch.634" +
                prodid+"</prodid><prodname>标准全球通套餐</prodname>" 
                + "<proddesc>标准全球通套餐，让你横行全球，哇哈哈</proddesc></prodInfo></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "根据主体产品Id获取主体产品信息异常");
        }
    }
    
    /**
     * 组内档次转换
     * @param msgHeader
     * @param ncode NCODE
     * @param stype 操作类型 ADD 增加 DEL 删除 MOD 修改 QRY 查询
     * @param preparebusi 预受理 固定传BsacNBSubmit
     * @param premutex 是否将互斥或依赖的进行关联删除
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-1-9 OR_SD_201411_411_SD_关于自助终端产品受理功能优化的需求
     */
    public ReturnWrap chgLevelInGroup(MsgHeaderPO msgHeader, String ncode, String stype, String preparebusi, String premutex)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            		"<menuid>recProductChange</menuid><process_code>cli_busi_ChangeProductSubmitSD</process_code><verify_code>" +
            		"</verify_code><resp_time>20150115180423</resp_time><sequence><req_seq>17</req_seq><operation_seq></operation_seq>" +
            		"</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo></message_head>" +
            		"</message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"组内档次转换异常");
        }
    }
    
    /** 
     * 开户时证件号码校验
     * 
     * @param msgHeader 消息头
     * @param certType 证件类型
     * @param certId 证件号码
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap chkCertSubs(MsgHeaderPO msgHeader, String certType, String certId)
    {
    	try
    	{
    		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
    				"<menuid>cardInstall</menuid><process_code>cli_qry_chkCertInfoForInstall</process_code><verify_code>" +
    				"</verify_code><resp_time>20150119100741</resp_time><sequence><req_seq>6</req_seq><operation_seq>" +
    				"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[]]>" +
    				"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
    				"<message_content><ifValid>1</ifValid></message_content>]]></message_body></message_response>";
    		
    		return intMsgUtil.parseResponse(response);
    	}
    	catch (Exception e)
    	{
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
    	}
    }
    
    /** 
     * 依据选号规则查询手机号码列表
     * 
     * @param msgHeader 报文头信息
     * @param orgId 组织机构
     * @param fitmod 选号规则
     * @param mainProdid 主体产品ID
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-4 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap qryTelNumberListByRule(MsgHeaderPO msgHeader, String orgId, String fitmod, String mainProdid)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            		"<menuid>cardInstall</menuid><process_code>cli_qry_qryAvlTelNum</process_code><verify_code>" +
            		"</verify_code><resp_time>20150123085839</resp_time><sequence><req_seq>24</req_seq>" +
            		"<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode>" +
            		"<retmsg><![CDATA[]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
            		"<message_content><servlist><telnum>有效手机号</telnum><prePay>选号费</prePay><region>归属县市</region>" +
            		"<orgid>归属地址</orgid><seqnum>序号</seqnum><lowConsumPre>最低消费预存</lowConsumPre>" +
            		"<lowConsumFee>最低消费金额</lowConsumFee><lowFeeProdid>最低消费对应的产品</lowFeeProdid>" +
            		"<lowFeePrivid>最低消费对应的优惠</lowFeePrivid><minCount>吉祥号码最低在网时长</minCount></servlist>" +
            		"<servlist><telnum>13468235295</telnum><prePay>0</prePay><region>SD.LP.01</region><orgid>634</orgid>" +
            		"<seqnum>1</seqnum><lowConsumPre>1200</lowConsumPre><lowConsumFee>7153</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>999</minCount></servlist>" +
            		"<servlist><telnum>13666346621</telnum><prePay>0</prePay><region>SD.LP</region><orgid>634</orgid>" +
            		"<seqnum>2</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13468265090</telnum><prePay>0</prePay><region>SD.LP.01</region><orgid>634</orgid>" +
            		"<seqnum>3</seqnum><lowConsumPre>1200</lowConsumPre><lowConsumFee>7194</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13468239972</telnum><prePay>0</prePay><region>SD.LP.01</region>" +
            		"<orgid>634</orgid><seqnum>4</seqnum><lowConsumPre>1200</lowConsumPre><lowConsumFee>7188</lowConsumFee>" +
            		"<lowFeeProdid></lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13468228272</telnum><prePay>0</prePay><region>SD.LP</region><orgid>634</orgid>" +
            		"<seqnum>5</seqnum><lowConsumPre>1200</lowConsumPre><lowConsumFee>7177</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13468238317</telnum><prePay>0</prePay><region>SD.LP.01</region><orgid>634</orgid>" +
            		"<seqnum>6</seqnum><lowConsumPre>1200</lowConsumPre><lowConsumFee>7197</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>15063408630</telnum><prePay>0</prePay><region>SD.LP.01</region>" +
            		"<orgid>634</orgid><seqnum>7</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee>" +
            		"<lowFeeProdid></lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13563484712</telnum><prePay>0</prePay><region>SD.LP</region><orgid>634</orgid>" +
            		"<seqnum>8</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>15063418421</telnum><prePay>0</prePay><region>SD.LP</region><orgid>634</orgid>" +
            		"<seqnum>9</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>15063410335</telnum><prePay>0</prePay><region>SD.LP</region><orgid>634</orgid>" +
            		"<seqnum>10</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>15063400854</telnum><prePay>0</prePay><region>SD.LP</region><orgid>634</orgid>" +
            		"<seqnum>11</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13563465123</telnum><prePay>0</prePay><region>SD.LP</region><orgid>634</orgid>" +
            		"<seqnum>12</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13468256842</telnum><prePay>0</prePay><region>SD.LP</region><orgid>634</orgid>" +
            		"<seqnum>13</seqnum><lowConsumPre>1200</lowConsumPre><lowConsumFee>7168</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13468266323</telnum><prePay>0</prePay><region>SD.LP.01</region><orgid>634</orgid>" +
            		"<seqnum>14</seqnum><lowConsumPre>1200</lowConsumPre><lowConsumFee>7151</lowConsumFee>" +
            		"<lowFeeProdid></lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>15063403932</telnum><prePay>0</prePay><region>SD.LP.01</region><orgid>634</orgid>" +
            		"<seqnum>15</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13561744361</telnum><prePay>0</prePay><region>SD.LP</region><orgid>634</orgid>" +
            		"<seqnum>16</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>15063403791</telnum><prePay>0</prePay><region>SD.LP.01</region><orgid>634</orgid>" +
            		"<seqnum>17</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13561744164</telnum><prePay>0</prePay><region>SD.LP</region><orgid>634</orgid>" +
            		"<seqnum>18</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13563403123</telnum><prePay>0</prePay><region>SD.LP</region><orgid>634</orgid>" +
            		"<seqnum>19</seqnum><lowConsumPre>0</lowConsumPre><lowConsumFee>0</lowConsumFee>" +
            		"<lowFeeProdid></lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>0</minCount></servlist>" +
            		"<servlist><telnum>13561744812</telnum><prePay>0</prePay><region>SD.LP</region><orgid>634</orgid>" +
            		"<seqnum>20</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13468238373</telnum><prePay>0</prePay><region>SD.LP.01</region><orgid>634</orgid>" +
            		"<seqnum>21</seqnum><lowConsumPre>1200</lowConsumPre><lowConsumFee>7191</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13963455897</telnum><prePay>0</prePay><region>SD.LP.02</region><orgid>634</orgid>" +
            		"<seqnum>22</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>15063413131</telnum><prePay>0</prePay><region>SD.LP</region><orgid>634</orgid>" +
            		"<seqnum>23</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13561744981</telnum><prePay>0</prePay><region>SD.LP</region><orgid>634</orgid>" +
            		"<seqnum>24</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee><lowFeeProdid>" +
            		"</lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"<servlist><telnum>13516346047</telnum><prePay>0</prePay><region>SD.LP.01</region><orgid>634</orgid>" +
            		"<seqnum>25</seqnum><lowConsumPre>100</lowConsumPre><lowConsumFee>10</lowConsumFee>" +
            		"<lowFeeProdid></lowFeeProdid><lowFeePrivid></lowFeePrivid><minCount>120</minCount></servlist>" +
            		"</message_content>]]></message_body></message_response>";
		    return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * 号码资源暂选接口
     * 
     * @param msgHeader 消息头
     * @param telnum 手机号
     * @param resType 资源类型 rsclTgsm
     * @param operType 操作标志 0为锁定，1为解锁
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap telNumTmpPick(MsgHeaderPO msgHeader, String telnum)
	{
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
					"<menuid></menuid><process_code>cli_busi_lockOrUnLockTelNum</process_code><verify_code>" +
					"</verify_code><resp_time>20150119144407</resp_time><sequence><req_seq>45</req_seq>" +
					"<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode>" +
					"<retmsg><![CDATA[]]></retmsg></retinfo></message_head></message_response>";
			return intMsgUtil.parseResponse(response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
     * 校验空白卡资源是否可用
     * 
     * @param msgHeader 消息头
     * @param curMenuId 当前菜单ID
     * @param blankNo 空白卡序列号
     * @param prodid 主体产品
     * @param recType 受理类型，开户Install，补卡ChangeEnum
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap chkBlankNo(MsgHeaderPO msgHeader, TerminalInfoPO termInfo, String blankNo, String prodid, String recType)
	{
		try 
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
					"<menuid></menuid><process_code>cli_busi_checkBlankCard</process_code><verify_code></verify_code>" +
					"<resp_time>20150119151945</resp_time><sequence><req_seq>10</req_seq><operation_seq></operation_seq>" +
					"</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" +
					"</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
					"<cardType>GSM_KI</cardType><cardResType>rsclTgsm</cardResType></message_content>]]></message_body>" +
					"</message_response>";
			return intMsgUtil.parseResponse(response);
		}
		catch (Exception e)
		{
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
     * 校验空白卡是否是预置空卡
     * 
     * @param msgHeader 消息头
     * @param blankNo 空白卡序列号
     * @param telNum 手机号
     * @param recType 受理类型，开户Install，补卡ChangeEnum
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap chkPreSetBlankCard(MsgHeaderPO msgHeader, String blankNo, String telNum, String recType)
	{
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
					"<menuid></menuid><process_code>cli_busi_chkIsPreSetBlankCard</process_code><verify_code></verify_code>" +
					"<resp_time>20150119171954</resp_time><sequence><req_seq>42</req_seq><operation_seq></operation_seq>" +
					"</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" +
					"</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><status>1</status>" +
					"<preSetBlankCardType>1</preSetBlankCardType></message_content>]]></message_body></message_response>";
			
			return intMsgUtil.parseResponse(response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
     * 开户算费
     * 
     * @param msgHeader 消息头
     * @param telnum 手机号
     * @param tpltTempletPO 模板po
     * @param simInfoPO sim卡po
     * @param blankno 空白卡序列号
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap reckonRecFee(MsgHeaderPO msgHeader, ProdTempletPO tpltTempletPO, SimInfoPO simInfoPO, String blankno)
	{
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
					"<menuid>cardInstall</menuid><process_code>cli_qry_qryRecFeeForInstall</process_code><verify_code>" +
					"</verify_code><resp_time>20150120144814</resp_time><sequence><req_seq>40</req_seq><operation_seq>" +
					"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[]]>" +
					"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_content><feelist><feeName>新入网用户话费预付款1</feeName><fee>10000</fee><quantity>1</quantity>" +
					"<feeID>Prepay</feeID><discount>0</discount><realfee>10000</realfee></feelist></message_content>]]>" +
					"</message_body></message_response>";
		
			return intMsgUtil.parseResponse(response);
		}
		catch (Exception e)
		{
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
     * 申请写卡，包括空白卡资源暂选和获取加密数据
     * 
     * @param msgHeader 消息头
     * @param blankno 空白卡序列号
     * @param recType 受理类型，开户Install，补卡ChangeEnum
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap getEncryptedData(MsgHeaderPO msgHeader, String blankNo, String recType, String region)
	{
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
					"<menuid></menuid><process_code>cli_qry_genWriteCardDataEncrypt</process_code><verify_code></verify_code><" +
					"resp_time>20150119173108</resp_time><sequence><req_seq>43</req_seq><operation_seq></operation_seq>" +
					"</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]>" +
					"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
					"<issueData>72070003040101700000681106000505B000F2111EDA6E3741A52EF0A96BD46D3C55C0530FEF400A35070A34D92E3545155F5B56A358290881B4D814E195384CFF274D45F7A9575E38A60116351AB7A5CEB7162834D0AED658D0D6FBCABB18E00B2FC9A84682412E1FFBBE0902EE0FBFBC1298</issueData>" +
					"<ICCID>89860096150003806455</ICCID><OLDICCID>89860096150013806455</OLDICCID><IMSI>460003424602855</IMSI>" +
					"<fromnum></fromnum><wrcardFormnum></wrcardFormnum></message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
		}
		catch (Exception e)
		{
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
     * 作废卡，写卡失败时调用
     * 
     * @param msgHeader 消息头
     * @param blankno 空白卡序列号
     * @param simInfoPO sim卡信息
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
	public ReturnWrap updateWriteCardResult(MsgHeaderPO msgHeader, String blankno, SimInfoPO simInfoPO)
	{
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
					"<menuid>cardInstall</menuid><process_code>cli_qry_writeBlankCardFail</process_code><verify_code>" +
					"</verify_code><resp_time>20150121102731</resp_time><sequence><req_seq>24</req_seq><operation_seq>" +
					"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>" +
					"<![CDATA[]]></retmsg></retinfo></message_head></message_response>";
			return intMsgUtil.parseResponse(response);
		}
		catch (Exception e)
		{
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
     * 开户提交
     * 
     * @param msgHeader 消息头
     * @param simInfoPO sim卡信息
     * @param tpltTempletPO 模板信息
     * @param idCardPO 用户身份信息
     * @param totalfee 总费用
     * @param passwd 服务密码
     * @param telnum 手机号码
     * @param bankNo 银行编号
     * @param bankNbr 银行缴费流水号
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-1-15 OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求
     */
    public ReturnWrap terminalInstall(MsgHeaderPO msgHeader, SimInfoPO simInfoPO, ProdTempletPO tpltTempletPO,
        IdCardPO idCardPO, String totalfee, String passwd, String telnum, String bankNo, String bankNbr)
    {
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
					"<menuid></menuid><process_code>cli_qry_selfInstall</process_code><verify_code></verify_code>" +
					"<resp_time>20150120112739</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq>" +
					"</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[装载用户[Region=634," +
					"Servnumber=13666346683,LoadType=1,SubsID=]资料失败:无法查询到指定用户信息：[telnum]=[13666346683];]]>" +
					"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_content><imsi></imsi><cardcheck></cardcheck><install_formnum>634150120899713177</install_formnum>" +
					"<install_subsid>6348000361005</install_subsid><install_oid>88009899713167</install_oid>" +
					"<blankCardNo></blankCardNo></message_content>]]></message_body></message_response>";
			
			return intMsgUtil.parseResponse(response);
		}
		catch (Exception e)
		{
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
    /**
     * 查询可变更的主体产品信息
     * @param msgHeader 消息头
     * @param prodid 主体产品编码
     * @return ReturnWrap
     * @remark add by sWX219697 2015-5-4 17:29:19 OR_SD_201503_508_SD_自助终端增加主体产品自选套餐的办理
     */
    public ReturnWrap qryMainProdInfo(MsgHeaderPO msgHeader, String prodid)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"+
                "<menuid>recProductChange</menuid><process_code>cli_qry_convertprodinfo</process_code><verify_code>" +
                "</verify_code><resp_time>20140604153658</resp_time><sequence><req_seq>7</req_seq><operation_seq>" +
                "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" +
                "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>" +
                "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"+
                "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.68h.634</newprodid><region>634</region><newprodname>莱芜后付费全球通68套餐</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
                "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.tp.test</newprodid><region>634</region><newprodname>资费套餐</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
                "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.gt.88tcy.634</newprodid><region>634</region><newprodname>莱芜预付费全球通新88套餐</newprodname><brandid>BrandGotone</brandid><newproddescr></newproddescr></resultList>" +
                "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.es.634</newprodid><region>634</region><newprodname>后付费亲情卡轻松打</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
                "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.ty</newprodid><region>634</region><newprodname>神州行田园卡</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
                "<resultList><oldprodid>pp.gt.slp</oldprodid><newprodid>pp.eo.ffct.634</newprodid><region>634</region><newprodname>神州行畅听卡发发套餐</newprodname><brandid>BrandSzx</brandid><newproddescr></newproddescr></resultList>" +
                "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e) 
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");

            return rw;
        }
    }
    
    /**
     * <查询用户已预约的号码列表>
     * <功能详细描述>
     * @param msgHeader
     * @param Document doc
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-6-9 OR_SD_201505_489_开户中增加预约选号功能
     */
    public ReturnWrap qryBookedTelnum(MsgHeaderPO msgHeader, Document doc)
    {
        try 
        {
             String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
               "<message_response><message_head version=\"1.0\">" +
               "<menuid></menuid><process_code>cli_busi_chkIsPreSetBlankCard</process_code>" +
               "<verify_code></verify_code>" +
               "<resp_time>20150119171954</resp_time><sequence><req_seq>42</req_seq><operation_seq></operation_seq>" +
               "</sequence>" +
               "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" +
               "</message_head><message_body>" +
               "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
               "<message_content>" +
               "<resultList>" +
               "<SERVNUMBER>13561713647</SERVNUMBER><ENDDATE>2015-06-18 19:24:14</ENDDATE><CERTTYPE>IdCard</CERTTYPE><CERTID>372928198710122932</CERTID><PREPAY></PREPAY>" +
               "</resultList>" +
               "<resultList>" +
               "<SERVNUMBER>13561713646</SERVNUMBER><ENDDATE>2015-06-17 19:24:14</ENDDATE><CERTTYPE>IdCard</CERTTYPE><CERTID>372928198710122932</CERTID><PREPAY></PREPAY>" +
               "</resultList>" +
               "<resultList>" +
               "<SERVNUMBER>13561713645</SERVNUMBER><ENDDATE>2015-06-20 19:24:14</ENDDATE><CERTTYPE>IdCard</CERTTYPE><CERTID>372928198710122932</CERTID><PREPAY></PREPAY>" +
               "</resultList>" +
               "<resultList>" +
               "<SERVNUMBER>13561713644</SERVNUMBER><ENDDATE>2015-06-20 19:24:14</ENDDATE><CERTTYPE>IdCard</CERTTYPE><CERTID>372928198710122932</CERTID><PREPAY></PREPAY>" +
               "</resultList>" +
               "</message_content>]]></message_body></message_response>";
             
             return intMsgUtil.parseResponse(response);
        }
        catch (Exception e) 
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    
    /**
     * USIM卡更换信息
     * @param msgHeader 报文头信息   
     * @return
     * @remark add by qWX279398 2015-5-13 OR_SD_201503_942_山东_自助终端提示换USIM 
     */
    public ReturnWrap changeUSIMCard(MsgHeaderPO msgHeader)
    {
     try 
     {
	      String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
	        "<menuid></menuid><process_code>cli_busi_chkIsPreSetBlankCard</process_code><verify_code></verify_code>" +
	        "<resp_time>20150119171954</resp_time><sequence><req_seq>42</req_seq><operation_seq></operation_seq>" +
	        "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" +
	        "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><status>1</status>" +
	        "<message>0</message></message_content>]]></message_body></message_response>";
	      
	      return intMsgUtil.parseResponse(response);
     }
     catch (Exception e) 
     {
    	 return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
     }
    }

    /**
	 * 山东有价卡购买接口
	 * @param header
	 * @param doc
	 * @return
	 * @remark create by wWX217192 2015-06-17 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造
	 */
	public ReturnWrap buyValueCard(MsgHeaderPO header, Document doc)
	{
		try
		{
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
					"<menuid>cardcharge_valueCard</menuid><process_code>cli_qry_elecCardSale</process_code><verify_code>" +
					"</verify_code><resp_time>20150617181645</resp_time><sequence><req_seq>3</req_seq><operation_seq>" +
					"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode>" +
					"<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>" +
					"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
					"<transActionID>53120150617181645480229</transActionID><crmFormNum>634150617900982309</crmFormNum>" +
					"<cardList><cardNo>15991580000077747</cardNo><cardPwd>123456</cardPwd><elecCardCntTotal>100</elecCardCntTotal>" +
					"<cardDate>20200306</cardDate><CardType>00</CardType><CardBusiPro>0</CardBusiPro></cardList><cardList>" +
					"<cardNo>15991580000077748</cardNo><cardPwd>123456</cardPwd><elecCardCntTotal>100</elecCardCntTotal>" +
					"<cardDate>20200306</cardDate><CardType>00</CardType><CardBusiPro>0</CardBusiPro></cardList><cardList>" +
					"<cardNo>15991580000077749</cardNo><cardPwd>123456</cardPwd><elecCardCntTotal>100</elecCardCntTotal>" +
					"<cardDate>20200306</cardDate><CardType>00</CardType><CardBusiPro>0</CardBusiPro></cardList><cardList>" +
					"<cardNo>15991580000077750</cardNo><cardPwd>123456</cardPwd><elecCardCntTotal>100</elecCardCntTotal>" +
					"<cardDate>20200306</cardDate><CardType>00</CardType><CardBusiPro>0</CardBusiPro></cardList><cardList>" +
					"<cardNo>15991580000077751</cardNo><cardPwd>123456</cardPwd><elecCardCntTotal>100</elecCardCntTotal>" +
					"<cardDate>20200306</cardDate><CardType>00</CardType><CardBusiPro>0</CardBusiPro></cardList><cardList>" +
					"<cardNo>15991580000077752</cardNo><cardPwd>123456</cardPwd><elecCardCntTotal>100</elecCardCntTotal>" +
					"<cardDate>20200306</cardDate><CardType>00</CardType><CardBusiPro>0</CardBusiPro></cardList>" +
					"</message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
		}
		catch(Exception e)
		{
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
}
