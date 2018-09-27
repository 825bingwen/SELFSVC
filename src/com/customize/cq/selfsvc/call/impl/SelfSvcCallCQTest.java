package com.customize.cq.selfsvc.call.impl;

import java.util.Map;
import java.util.Vector;

import com.customize.cq.selfsvc.call.SelfSvcCallCQ;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.util.CommonUtil;

public class SelfSvcCallCQTest implements SelfSvcCallCQ
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

    //modify start l00190940 2011-11-16 套餐信息查询（重庆）
    /**
     * 重庆套餐信息查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryComboInfo(Map map)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>qrySerPri</menuid><process_code>cli_qry_taocan_cq</process_code><verify_code></verify_code><resp_time>20100921002206</resp_time><sequence><req_seq>39</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><detailinfo><privid>10元包120条点对点短信＋移动全时通必选包</privid><name>SMS</name><type>4</type><total>33</total><used>0</used><remain>33</remain><priname>0</priname></detailinfo></message_content>]]></message_body></message_response>";
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
    //modify end l00190940 2011-11-16 套餐信息查询（重庆）
    
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
    public ReturnWrap queryBalance(Map<String, String> paramMap)
    {
//        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"+
//        "<menuid>qryBill</menuid><process_code>cli_qry_balance</process_code><verify_code></verify_code>"+
//        "<resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq>"+
//        "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"+
//        "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"+
//        "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_body><prepayType>pptpPost</prepayType>"+
//        "<currBillFee>100</currBillFee><hisBillFee>200</hisBillFee><balance>300</balance>"+
//        "<expireDate>20110809</expireDate><contractBalance>400</contractBalance>"+
//        "<contractCanUse>500</contractCanUse><contractThisUsed>600</contractThisUsed>"+
//        "<contract_drawamt>700</contract_drawamt><present_balance>800</present_balance>"+
//        "<present_canuse>900</present_canuse><present_thisused>1000</present_thisused>"+
//        "<present_drawamt>1100</present_drawamt><cash_balance>1200</cash_balance>"+
//        "<other_balance>1300</other_balance></message_body>]]></message_body></message_response>";
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>qryBill</menuid><process_code>cli_qry_balance</process_code><verify_code></verify_code><resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_body><serv_flag>0</serv_flag><exp_date_flag>0</exp_date_flag><this_exp_date></this_exp_date><freebalance>127.16</freebalance><protocol_all></protocol_all><present_all></present_all><protcl_curcycle></protcl_curcycle><protcl_used></protcl_used><protcl_left></protcl_left><presnt_curcycle></presnt_curcycle><presnt_used></presnt_used><presnt_left></presnt_left></message_body>]]></message_body></message_response>";
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
                    + "<balance></balance><sum_fee>1000.9</sum_fee></message_content>]]></message_body></message_response>";
            
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
    public ReturnWrap chargeCommit(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_busi_chargefee</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<bill_nbr>123456789</bill_nbr><accept_name>缴费</accept_name><acct_id>987654321</acct_id>"
                    + "<cust_name>高群</cust_name><pay_date>20100910</pay_date><prt_count>0</prt_count>"
                    + "<comment></comment><bcycle_count>2</bcycle_count>"
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
        rw.setStatus(SSReturnCode.SUCCESS);
        
        return rw;
    }
    
    /**
     * 不校验密码，直接获取用户信息
     */
    public ReturnWrap getUserStatus(Map map)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>cli_qry_userinfo</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><subname>高群</subname>"
                + "<region>230</region><regionname></regionname><productid></productid>"
                + "<productname></productname><productgroup></productgroup><viptype></viptype>"
                +
                // "<productname>全球通</productname><productgroup>BrandMzone</productgroup><viptype>VC0000</viptype>" +
                "<logintype></logintype><feeflag></feeflag><question></question><answer></answer><needcheckstr>"
                + "</needcheckstr><contactid></contactid><status>US28</status><nettype></nettype></message_content>]]></message_body></message_response>";
        
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
	 * 获取账单寄送查询的返回信息
	 * 
	 * @param map
	 * @return 
	 * @see
	 */
	public ReturnWrap getBillSendInfo(Map map)
	{
		
		String responseBill = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
	        + "<menuid></menuid><process_code>cli_qry_billmail</process_code><verify_code></verify_code>"
	        + "<resp_time>20110901135956</resp_time><sequence><req_seq>21</req_seq><operation_seq>12</operation_seq>"
	        + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
	        + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
	        + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><billsenditem>" +
	        		"<user_name>23090650095999</user_name><region>230</region><user_no>2300024709810</user_no>" +
	        		"<bill_type>Bill</bill_type><lang>Che</lang><type>mltpSMS</type><addr>13908279427</addr>" +
	        		"<code></code><link_man></link_man><link_tel></link_tel><e_addr></e_addr>" +
	        		"<create>2011-10-25 18:06:17</create><status>0</status><status_date>2011-11-11 14:32:19</status_date>" +
	        		"</billsenditem></message_content>]]></message_body></message_response>";
	
        ReturnWrap rw = null;
        try
        {
    		rw = intMsgUtil.parseResponse(responseBill); 
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
    
    /**
     * 业务查询统一接口 梦网业务查询
     */
    public ReturnWrap queryService(Map map)
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
                + "<cusermoninfodt><usermoninfo_deal_type>22</usermoninfo_deal_type><usermoninfo_spid>917270</usermoninfo_spid>"
                + "<usermoninfo_spname>中国移动</usermoninfo_spname><usermoninfo_spbizid>+MAILMF+MAILBZ+MAILVIP</usermoninfo_spbizid>"
                + "<usermoninfo_spbizname>139邮箱免费版</usermoninfo_spbizname><usermoninfo_spbiztype></usermoninfo_spbiztype>"
                + "<usermoninfo_pricetype></usermoninfo_pricetype><usermoninfo_price></usermoninfo_price><usermoninfo_domain>"
                + "</usermoninfo_domain><usermoninfo_start_time>2010-09-14 17:46:47</usermoninfo_start_time>"
                + "<usermoninfo_end_time /><usermoninfo_seq /><usermoninfo_replace_charge></usermoninfo_replace_charge>"
                + "<usermoninfo_pricedesc>免费</usermoninfo_pricedesc></cusermoninfodt></message_content>]]></message_body>"
                + "</message_response>";
        ReturnWrap rw = new ReturnWrap();
        try
        {
            rw = intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            rw.setStatus(0);
            rw.setReturnMsg("");
        }
        return rw;
    }
    
    /**
     * 账单寄送方式提交
     */
    public ReturnWrap billSendCommit(Map map)
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
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
    }
    
    /**
     * 本机品牌资费及已开通功能
     */
    public ReturnWrap qryFavourable(Map map)
    {
        // 查询类型
        String queryType = (String)map.get("queryType");
        
        // 正确
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>showUserservice</menuid><process_code>cli_qry_producttree_hub</process_code><verify_code></verify_code><resp_time>20110802163908</resp_time><sequence><req_seq>34</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><detail><level>1</level><type>产品</type><typeid>pkg.prod.zshf</typeid><typename>[可选优惠包27]－湖北折扣分月送类</typename><startdate>2009-07-01 00:00:00</startdate><enddate></enddate></detail><detail><level>2</level><type>产品</type><typeid>pkg.prod.least_qs</typeid><typename>[可选优惠包26]-湖北保底消费类</typename><startdate>2009-08-01 00:00:00</startdate><enddate></enddate></detail><detail><level>3</level><type>产品包</type><typeid>pkg.serv.fetion</typeid><typename>飞信业务包</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>3.1</level><type>产品</type><typeid>FetionBase</typeid><typename>飞信基本业务</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>3.1.1</level><type>服务</type><typeid>FetionBase</typeid><typename>飞信基本业务</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>4</level><type>产品包</type><typeid>pkg.prod.139mail</typeid><typename>139手机邮箱产品包</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>4.1</level><type>产品</type><typeid>139mail_mf</typeid><typename>139手机邮箱免费版</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>4.1.1</level><type>服务</type><typeid>139mail_mf</typeid><typename>139手机邮箱免费版</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>5</level><type>产品</type><typeid>SELFRING</typeid><typename>彩铃</typename><startdate>2010-06-11 09:47:40</startdate><enddate></enddate></detail><detail><level>5.1</level><type>服务</type><typeid>SELFRING</typeid><typename>彩铃</typename><startdate>2010-06-11 09:47:40</startdate><enddate></enddate></detail><detail><level>6</level><type>产品</type><typeid>pkg.prod.newbusi</typeid><typename>新业务类优惠包</typename><startdate>2010-07-01 00:00:00</startdate><enddate></enddate></detail><detail><level>7</level><type>产品</type><typeid>LDTX</typeid><typename>来电提醒</typename><startdate>2010-06-22 11:52:22</startdate><enddate></enddate></detail><detail><level>7.1</level><type>服务</type><typeid>LDTX</typeid><typename>来电提醒</typename><startdate>2010-06-22 11:52:22</startdate><enddate></enddate></detail><detail><level>8</level><type>产品</type><typeid>KJTX</typeid><typename>开机早知道</typename><startdate>2010-06-22 11:52:22</startdate><enddate></enddate></detail><detail><level>8.1</level><type>服务</type><typeid>KJTX</typeid><typename>开机早知道</typename><startdate>2010-06-22 11:52:22</startdate><enddate></enddate></detail><detail><level>9</level><type>产品</type><typeid>inc.grp.vpmn.mem.next</typeid><typename>成员可选套餐-下月生效(仅适用于当月转网用户)</typename><startdate>2011-04-01 00:00:00</startdate><enddate></enddate></detail><detail><level>9.1</level><type>优惠</type><typeid>SVN250035</typeid><typename>成员套餐37-本地5元套餐5</typename><startdate>2011-04-01 00:00:00</startdate><enddate></enddate></detail><detail><level>10</level><type>产品</type><typeid>pkg.gt.st</typeid><typename>[基本优惠包]:标准全球通</typename><startdate>2007-09-01 00:00:00</startdate><enddate></enddate></detail><detail><level>10.1</level><type>优惠</type><typeid>B210221</typeid><typename>湖北全球通88套餐88元G3升级版</typename><startdate>2010-02-01 00:00:00</startdate><enddate></enddate></detail><detail><level>11</level><type>产品包</type><typeid>pkg.prod.gprs</typeid><typename>GPRS业务功能</typename><startdate>2005-07-01 00:00:00</startdate><enddate></enddate></detail><detail><level>11.1</level><type>服务</type><typeid>150</typeid><typename>GPRS功能</typename><startdate>2005-04-26 17:58:42</startdate><enddate></enddate></detail><detail><level>12</level><type>产品包</type><typeid>pkg.serv.ct</typeid><typename>服务长途包</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>12.1</level><type>产品</type><typeid>109</typeid><typename>国际长途</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>12.1.1</level><type>服务</type><typeid>109</typeid><typename>国际长途</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>12.2</level><type>产品</type><typeid>901</typeid><typename>国内长途</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>12.2.1</level><type>服务</type><typeid>901</typeid><typename>国内长途</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>13</level><type>产品包</type><typeid>pkg.serv.my</typeid><typename>服务漫游包</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>13.1</level><type>产品</type><typeid>904</typeid><typename>国内漫游</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>13.1.1</level><type>服务</type><typeid>904</typeid><typename>国内漫游</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>14</level><type>产品</type><typeid>120</typeid><typename>呼叫转移</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>14.1</level><type>服务</type><typeid>120</typeid><typename>呼叫转移</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>15</level><type>产品</type><typeid>107</typeid><typename>三方通话</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>15.1</level><type>服务</type><typeid>107</typeid><typename>三方通话</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>16</level><type>产品</type><typeid>100</typeid><typename>来电显示</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>16.1</level><type>服务</type><typeid>100</typeid><typename>来电显示</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>17</level><type>产品</type><typeid>114</typeid><typename>短消息</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>17.1</level><type>服务</type><typeid>114</typeid><typename>短消息</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>18</level><type>产品</type><typeid>inc.grp.vpmn.mem.base</typeid><typename>成员VPMN功能</typename><startdate>2009-08-11 00:00:00</startdate><enddate></enddate></detail><detail><level>18.1</level><type>服务</type><typeid>841</typeid><typename>VPMN功能</typename><startdate>2009-08-11 00:00:00</startdate><enddate></enddate></detail></message_content>]]></message_body></message_response>";
        // 错误
//        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
//        		"<menuid>qryFavourable</menuid><process_code>cli_qry_zfinfo</process_code>" +
//        		"<verify_code></verify_code>" +
//        		"<resp_time>20110620144828</resp_time><" +
//        		"sequence><req_seq>14</req_seq><operation_seq></operation_seq>" +
//        		"</sequence><retinfo><rettype>0</rettype><retcode>999</retcode>" +
//        		"<retmsg><![CDATA[BUSINESSID:QrySubsFeeInfo_Atsv,中间件调用失败，EUniCall抛出异常,ErrorID -1 ErrorMsg EXCEPTION 'ETuxedo': Destination service not found: IntCmd:CscGetSetInfo, IntSubCmd:, IntID:bsacAtsv, Region:711]]></retmsg></retinfo></message_head>" +
//        		"</message_response>";
        
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
     * 重庆优惠业务查询
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryPrivInfo(Map map){
    	
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_promotions_cq</process_code><verify_code></verify_code>"
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
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    public ReturnWrap privAcceptCommit(Map map){
    	try
        {
    		 String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                 + "<menuid>recPrivAccept</menuid><process_code>cli_qry_promotions_cq</process_code><verify_code></verify_code>"
                 + "<resp_time>20100921014415</resp_time><sequence><req_seq>35</req_seq><operation_seq></operation_seq></sequence>"
                 + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                 + "</retmsg></retinfo></message_head></message_response>";

    		return intMsgUtil.parseResponse(response);
    		
        }catch(Exception e){
        	ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;	
        }
    }
    
    /**
     * 重庆产品变更，查询用户可转换套餐清单
     */
    public ReturnWrap qryChangeList(Map map){
    	try
        {
    		 String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                 + "<menuid></menuid><process_code>cli_package_changelist_cq</process_code><verify_code></verify_code>"
                 + "<resp_time>20110623115115</resp_time><sequence><req_seq>52</req_seq><operation_seq></operation_seq></sequence>"
                 + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                 + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                 //+ "<sprid>B2135</sprid><sprname>湖北全球通88套餐88元</sprname>"
                 + "<changelist><tprid>B210221</tprid><tprname>湖北全球通88套餐88元G3升级版</tprname><tbrid>1</tbrid></changelist>"
                 + "<changelist><tprid>B210221</tprid><tprname>重庆全球通88套餐88元G3升级版</tprname><tbrid>2</tbrid></changelist>"
                 + "</message_content>]]></message_body></message_response>";
    		 return intMsgUtil.parseResponse(response);
    		
        }catch(Exception e){
        	ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;	
        }
    }
    
    public ReturnWrap prodChgCheck(Map inMap) {
		// TODO Auto-generated method stub
		return null;
	}
    
    public ReturnWrap getProdTmpList(Map inMap) {
		// TODO Auto-generated method stub
		return null;
	}
    
    /**
     * 产品变更，获取优惠/服务/产品变更清单
     */
    public ReturnWrap qryChgContent(Map map){
    	try
        {
    		 String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                 + "<menuid></menuid><process_code>cli_package_chgcontent_cq</process_code><verify_code></verify_code>"
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
    		
        }catch(Exception e){
        	ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;	
        }
    }
    
    /**
     * 产品变更，获取优惠/服务/产品变更清单
     */
    public ReturnWrap prodChgCommit(Map map){
    	try
        {
    		 String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                 + "<menuid></menuid><process_code>cli_package_chgcommit_cq</process_code><verify_code></verify_code>"
                 + "<resp_time>20110603191908</resp_time><sequence><req_seq>52</req_seq><operation_seq></operation_seq></sequence>"
                 + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                 + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                 + "<commitlist><optype>1</optype><prtype>PRIVILEGE</prtype><prid>B2135</prid><prname>湖北全球通88套餐88元</prname></commitlist>"
                 + "<commitlist><optype>0</optype><prtype>PRIVILEGE</prtype><prid>LDTX</prid><prname>来电提醒</prname></commitlist>"
                 + "<commitlist><optype>0</optype><prtype>PRODUCT</prtype><prid>656</prid><prname>656</prname></commitlist>"
                 + "<commitlist><optype>0</optype><prtype>PRIVILEGE</prtype><prid>B210221</prid><prname>湖北全球通88套餐88元G3升级版</prname></commitlist>"
                 + "</message_content>]]></message_body></message_response>";
    		 
    		 return intMsgUtil.parseResponse(response);
    		
        }catch(Exception e){
        	ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;	
        }
    }
    
  //add start l00190940 2011-11-03 积分兑换中的积分查询
    /**
     * 积分查询(仅适用于重庆)
     */
	public ReturnWrap queryScoreValue(Map paramMap) 
	{
		try
        {
    		 String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
    			 + "<menuid>111111</menuid><process_code>cli_qry_scorevalue</process_code><verify_code></verify_code>"
    			 + "<resp_time>20111103153519</resp_time><sequence><req_seq>6</req_seq><operation_seq></operation_seq></sequence>"
    			 + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
    			 + "</retinfo></message_head><message_body><![CDATA[<message_content><scoreinfo>2011</scoreinfo></message_content>]]>" 
    			 + "</message_body></message_response>";
    		 
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
	//add end l00190940 2011-11-03 积分兑换中的积分查询

	/**
     * 积分兑换信息查询(仅适用于重庆)
     */
	public ReturnWrap queryScoreExchangeInfo(Map paramMap) 
	{
		try
        {
    		 String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
    			 + "<menuid>111111</menuid><process_code>cli_qry_scoreexchange_cq</process_code><verify_code></verify_code>"
    			 + "<resp_time>20111103141219</resp_time><sequence><req_seq>6</req_seq><operation_seq></operation_seq></sequence>"
    			 + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
    			 + "</retmsg></retinfo></message_head><message_body><![CDATA[<message_content><detailinfo><score>1500</score>"
    			 + "<nlevel>ScoreExchangeCharge1-1</nlevel><note>1500分兑换50元话费</note><itemid>1000433044</itemid>"
    			 + "<activeno>ScoreExchangeCharge1</activeno></detailinfo><detailinfo><score>3000</score>"
    			 + "<nlevel>ScoreExchangeCharge2-1</nlevel><note>3000分兑换100元话费</note><itemid>1000433056</itemid>"
    			 + "<activeno>ScoreExchangeCharge2</activeno></detailinfo><detailinfo><score>1000</score>"
    			 + "<nlevel>jfhhf_qqt09_30.1</nlevel><note>全球通1000积分换30话费</note><itemid>90630289</itemid>"
    			 + "<activeno>jfhhf_qqt09_30</activeno></detailinfo></message_content>]]></message_body></message_response>";
    		 
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
     * 积分兑换话费(仅适用于重庆)
     */
	public ReturnWrap exchangeBalance(Map paramMap) 
	{
		String response1 = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid></menuid><process_code>cli_busi_ scoreexchange_cq</process_code><verify_code></verify_code><resp_time>20100921002348</resp_time><sequence><req_seq>49</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
	    String response2 = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>111111</menuid><process_code>cli_busi_scoreexchange_cq</process_code><verify_code></verify_code><resp_time>20111104140902</resp_time><sequence><req_seq>6</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>979050</retcode><retmsg><![CDATA[用户产品[新标准神州行]不允许参加此活动!]]></retmsg></retinfo></message_head></message_response>";
		ReturnWrap rw = null;
	    try
	    {
	        rw = intMsgUtil.parseResponse(response2);
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    return rw;
	}
}
