package com.customize.nx.selfsvc.call.impl;

import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.nx.selfsvc.call.SelfSvcCallNX;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.SSReturnCode;

public class SelfSvcCallNXTest implements SelfSvcCallNX
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
     * 账户余额查询
     */
    public ReturnWrap queryBalance(Map<String, String> paramMap)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
            		"<message_head version=\"1.0\"><menuid>feeCharge</menuid>" +
            		"<process_code>cli_qry_balance_nx</process_code><verify_code></verify_code>" +
            		"<resp_time>20110630194643</resp_time><sequence><req_seq>5</req_seq>" +
            		"<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype>" +
            		"<retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]>" +
            		"</retmsg></retinfo></message_head><message_body>" +
            		"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
            		"<lasttimebalance>31924.06</lasttimebalance><shouldpay>0.00</shouldpay>" +
            		"<newbalance>31924.06</newbalance></message_content>]]></message_body>" +
            		"</message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
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
                    + "<message_content><lastbal>1276.48</lastbal><curbal>1276.48</curbal><totalpayed>17.72</totalpayed>" 
                    + "<billitem><type>#闫涛</type><itemname>月基本费|010000000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>增值业务费|020000000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>代他人付费|060000000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>通信费|030000000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>代收费|050000000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>补收费|040000000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>月租费|010010000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>套餐月租费|010020000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>市话包月费|010030000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>区内包月费|010040000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>两城一家月功能费|010050000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>国内（不含港澳台）包月费|010060000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>短信包月|010070000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>动感地带短信包月费|010080000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>动感地带本地通话包月费|010090000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>长话包月费|010100000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>TD随e行G3上网笔记本基本费|010110000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>GPRS月租|010120000</itemname><fee>10.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>非常假期月功能费|010130000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>虚拟网月租费|010140000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>400功能费|010150000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>随e行G3手机流量套餐费|010160000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>套餐月使用费(日分摊累计)|010170000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>黑莓（BlackBerry）个人邮箱功能费|010180000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>BlackBerry 集团客户扩展业务功能费|010190000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>BlackBerry 集团客户扩展业务通信费|010200000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>12580信息费|020010000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>12580移动秘书费|020020000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>ADC功能费|020030000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>手机对讲|020040000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>ADC通信费|020060000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>GPRS无线DDN费|020070000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>IP直通车费|020090000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>TDGPRS无线DDN费|020100000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>TD手机上网GPRS费|020110000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>TD随E行GPRS费|020120000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>USSD费|020130000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>随e行G3手机流量费|020150000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>WAP费|020170000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>MobileMarket信息费|020210000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>百宝箱信息费|020220000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>彩铃功能费|020230000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>彩信套餐费|020250000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>彩信信息费|020260000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>菜单更新费|020270000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>端口费|020290000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>飞信POP功能费|020320000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>飞信QQ功能费|020330000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>飞信会员月功能费|020340000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>飞信速配交友功能费|020350000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>飞信通讯录月功能费|020360000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>飞信位置业务月功能费|020370000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>飞信自写彩信通信费|020380000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>飞信自写短信通信费|020390000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>飞信靓号月功能费|020400000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>国际及港澳台彩信费|020410000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>国际及港澳台短信息费|020420000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>话费快递|020460000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>集团E网费|020470000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>集团彩铃月功能费|020500000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>空间月租费|020580000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>来电提醒费|020590000</itemname><fee>2.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>宁夏手机报（新）|020620000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>宁夏手机报统付|020630000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>农村信息平台信息费|020640000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>农信通彩信通信费|020650000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>其他(MAS)费|020670000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>G+至尊游戏包功能费|020680000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>其他话费|020690000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>无线网站(MAS)费|020700000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>手机视频精彩15功能费|020710000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>企业邮箱费(ADC)|020720000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>手机邮箱(MAS)费|020740000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>强制显示月功能费|020770000</itemname><fee>10.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>强制隐藏月功能费|020780000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>全球通国际长途套餐月租费|020790000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>商信通费|020800000</itemname><fee>0.00</fee></billitem><billitem><type>#闫涛</type><itemname>生活百事通费|020840000</itemname><fee>0.00</fee></billitem><billitem><type>#闫涛</type><itemname>手机视频包月费|020850000</itemname><fee>0.00</fee></billitem><billitem><type>#闫涛</type><itemname>手机视频点播费|020860000</itemname><fee>0.00</fee></billitem><billitem><type>#闫涛</type><itemname>手机上网GPRS费|020870000</itemname><fee>0.00</fee></billitem><billitem><type>#闫涛</type><itemname>M2M行业无线数据应用功能费|020880000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>手机邮箱(ADC)通信费|020890000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>手机游戏平台信息费[GP]|020910000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>手机证券服务费|020920000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>手机桌面助理信息费|020940000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>数据月租费|020950000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>MAS类产品增值功能费|020960000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>随E行GPRS费|020970000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>无限音乐俱乐部会费|020980000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>无线局域网费|020990000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>WLAN预付卡费|021000000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>企业建站（ADC）通信费|021010000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>校讯通(ADC)通信费|021040000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>校讯通(ADC)功能费|050330000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>新华彩信统付|021060000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>新闻早晚报信息费|021070000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>新业务包月费|021080000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>信息费|021100000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>号簿管家月功能使用费|021110000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>移动CRM(ADC)费|021140000</itemname><fee>0.00</fee></billitem>"
                    + "  <billitem><type>#闫涛</type><itemname>移动CRM(MAS)费|021160000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>移动OA(ADC)费|021180000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>移动OA(MAS)费|021200000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>移动财务(ADC)费|021220000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>移动财务(MAS)费|021240000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>移动进销存(ADC)费|021260000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>移动进销存(MAS)费|021280000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>移动沙龙费|021310000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>移动总机费|021320000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>语音杂志费|021350000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>主被叫付费|021370000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>主叫显示月功能费|021380000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>手机阅读功能费|021390000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>手机电视收视费|021420000</itemname><fee>6.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>穆斯林闹钟功能费|021430000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>一卡通服务费|021440000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>12582农信通信息费|021460000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>M2M套餐费|021470000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>M2M专用APN端口费|021480000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>数据业务营销活动月租|021490000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>GPRS通信费|030010000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>IP长途长途费|030020000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>IP长途市话费|030030000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>IP漫游长途费|030060000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>TD随e行G3上网笔记本流量费|030120000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>港澳台长途长途费|030130000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>本地市话费|030140000</itemname><fee>37.60</fee></billitem>"
                    + "  <billitem><type>#闫涛</type><itemname>彩信费|030150000</itemname><fee>0.00</fee></billitem>"
                    + "    <billitem><type>#闫涛</type><itemname>短信息通信费|030160000</itemname><fee>3.80</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>港澳台长途市话费|030170000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>港澳台漫游长途费|030180000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>港澳台漫游费|030190000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>港澳台IP长途费|030200000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>港澳台长途费|030210000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>港澳台漫游费|030220000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>国际长途长途费|030230000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>国际长途市话费|030250000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>国际漫游长途费|030260000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>国际漫游费|030290000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>国内（不含港澳台）长途费|030300000</itemname><fee>142.76</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>国内（不含港澳台）漫游IP长途费|030320000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>国内（不含港澳台）漫游长途费|030370000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>国内（不含港澳台）漫游长途|030380000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>国内（不含港澳台）漫游费|030390000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>区内漫游费|030480000</itemname><fee>1.80</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>区内漫游长途费|030490000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>TDIP通话费|033010000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>TD普通长途费|033020000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>TD普通市话费|033030000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>TD普通漫游费|033040000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>TD视频长途费|033050000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>TD视频市话费|033060000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>TD视频漫游费|033070000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>其他费|040010000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>财信通彩信费|050010000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>梦网费|050020000</itemname><fee>0.30</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>财信通短信费|050030000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>中国绿色基金会捐款|050040000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>彩铃信息费|050050000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>残疾人捐款|050060000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>儿童基金费|050070000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>飞信互通信息费|050080000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>妇女基金费|050090000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>健康快车费|050100000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>农信通费|050110000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>手机工资单(ADC)费|050140000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#闫涛</type><itemname>手机杂志包月费|050160000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>售货机购物费|050170000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>信息台信息费费|050190000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>医信通彩信费|050200000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>医信通短信费|050220000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>移动气象站费|050240000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>音乐盒月租费|050250000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>音乐图片下载信息费|050260000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>语音杂志费|050280000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>中国红十字总会短信捐款|050300000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>手机邮箱(ADC)功能费|050310000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>企业建站（ADC）功能费|050320000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>本地农信通包月费|050340000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>本地农信通包年费|050350000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>中国社会工作协会短信捐款|050360000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#闫涛</type><itemname>代收费|060010000</itemname><fee>0.00</fee></billitem>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
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
        ReturnWrap rtw = new ReturnWrap();
        rtw.setStatus(SSReturnCode.SUCCESS);
        
        return rtw;
    }
    
    /**
     * 缴费查询接口
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryfeeChargeAccount(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_busi_chargefee_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<subsname>高群</subsname><chargeflag>00</chargeflag><shouldpay>0.00</shouldpay>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    
    /**
     * 缴费接口
     * 
     * @param map
     * @return
     */
    public ReturnWrap chargeCommit(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
            		"<message_head version=\"1.0\"><menuid>feeCharge</menuid>" +
            		"<process_code>cli_busi_chargefee_nx</process_code><verify_code></verify_code>" +
            		"<resp_time>20110630194643</resp_time><sequence><req_seq>4</req_seq><operation_seq>" +
            		"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>" +
            		"<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>" +
            		"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
            		"<formnum>at95120110630195051</formnum><dealNum>95188004080808909</dealNum>" +
            		"<subsname>#闫涛</subsname><chargeamount>100</chargeamount><unitname>银川地区</unitname>" +
            		"<cycle></cycle><totalamount></totalamount><suminfo></suminfo><invoicecontent>" +
            		"费用名称1|10||费用名称2|10\n费用名称13|10||费用名称4|10\n费用名称5|10||费用名称6|10\n" +
            		"费用名称7|10||费用名称8|10\n费用名称9|10||费用名称10|10\n费用名称11|10||费用名称12|10\n" +
            		"费用名称13|10||费用名称14|10\n费用名称15|10||费用名称16|10\n" +
            		"</invoicecontent></message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    
    /**
     * 宁夏套餐信息查询
     * @param map
     * @return
     */
    public ReturnWrap qryPackageInfo(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            "<menuid>qrySerPri</menuid><process_code>cli_qry_taocan_nx</process_code><verify_code></verify_code>" +
            "<resp_time>20110715145931</resp_time><sequence><req_seq>27</req_seq><operation_seq></operation_seq>" +
            "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>" +
            "</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
            "<rownum>6</rownum><privitem><name>(200分钟)赠送时长</name><sumnum>12000</sumnum><usednum>60</usednum>" +
            "<leftnum>11940</leftnum></privitem><privitem><name>(300分钟)赠送时长</name><sumnum>18000</sumnum><usednum>0</usednum>" +
            "<leftnum>18000</leftnum></privitem><privitem><name>100打400</name><sumnum>400</sumnum><usednum>16.8</usednum>" +
            "<leftnum>383.2</leftnum></privitem><privitem><name>赠送400分钟通话(长途通话)</name><sumnum>24000</sumnum>" +
            "<usednum>840</usednum><leftnum>23160</leftnum></privitem><privitem><name>GPRS赠送8M</name><sumnum>8388608</sumnum>" +
            "<usednum>0</usednum><leftnum>8388608</leftnum></privitem><privitem><name>GPRS5元包30M</name><sumnum>31457280</sumnum>" +
            "<usednum>1229824</usednum><leftnum>30227456</leftnum></privitem>" +
            "</message_content>]]></message_body>" +
            "</message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
        
    }
    
    /**
     * 根据手机号码查询客户信息
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap getCustinfo(Map map)
    {
            String response = "";
            
            String cycle = (String)map.get("cycle");
            if ("201206".equals(cycle))
            {
                response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
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
                    + "<cyclelist><cycle>20120601</cycle><startdate>20120501</startdate><enddate>20120630</enddate><acctid>10000001</acctid><unionacct>1</unionacct></cyclelist>"
//                    + "<cyclelist><cycle>20120516</cycle><startdate>20120515</startdate><enddate>20120531</enddate><acctid>10000001</acctid><unionacct>0</unionacct></cyclelist>"
                    
                    + "</message_content>]]></message_body></message_response>";
            }
            else
            {
                response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
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
                    + "<cyclelist><cycle>20120501</cycle><startdate>20120501</startdate><enddate>20120531</enddate><acctid>10000001</acctid><unionacct>1</unionacct></cyclelist>"
 //                   + "<cyclelist><cycle>20120516</cycle><startdate>20120515</startdate><enddate>20120531</enddate><acctid>10000001</acctid><unionacct>0</unionacct></cyclelist>"
                    
                    + "</message_content>]]></message_body></message_response>";
            }
            
                        
                ReturnWrap rtw = null;
                try
                {
                    rtw = intMsgUtil.parseResponse(response);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                
                return rtw;
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
                
                +"<custbill>"
                +" <billinfo>"
                
                // 费用信息
                +"     <billfixed>"
                +"         <fee><name>套餐及固定费用</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>"
                +"         <feegroup>"
                +"             <name>套餐外费用</name><sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>语音通信费</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>"
                +"                 <fee><name>上网费</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>"
                +"                 <fee><name>短彩信费</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>"
                +"             </subfee>"
                +"         </feegroup>"
                +"         <fee><name>自有增值业务费</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>           "
                +"         <fee><name>优惠减免</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>"
                +"         <fee><name>集团代付费</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>"
                +"         <fee><name>个人代付费</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>         "
                +"     </billfixed>"
                
                // 自有业务
                +"     <feedetails>"
                +"         <feegroup>"
                +"             <name>套餐和固定费</name><sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>全球通128套餐</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>亲情号码套餐</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>国内数据套餐包</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>wlan</name><value>10.00</value><sortorder/></fee>"
                +"             </subfee>"
                +"             <fee><name>合计</name><value>10.00</value></fee>"
                +"         </feegroup>"
                +"         <feegroup>"
                +"             <name>套餐外语音费</name><sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>国内长途</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>港澳长途</name><value>10.00</value><sortorder/></fee>"
                +"             </subfee>"
                +"             <fee><name>合计</name><value>10.00</value><sortorder/></fee>"
                +"         </feegroup>"
                +"         <feegroup>"
                +"             <name>套餐上网费</name><sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>wlan时长</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>数据业务</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>省内wlan上网</name><value>10.00</value><sortorder/></fee>"
                +"             </subfee>"
                +"             <fee><name>合计</name><value>10.00</value></fee>"
                +"         </feegroup>"
                +"         <feegroup>"
                +"             <name>套餐外端彩信费</name>"
                +"             <sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>国内短信</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>彩信</name><value>10.00</value><sortorder/></fee>"
                +"             </subfee>"
                +"             <fee><name>合计</name><value>10.00</value></fee>"
                +"         </feegroup>"
                +"         <feegroup>"
                +"             <name>自由增值业务费</name><sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>交通秘书</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>无限音乐</name><value>10.00</value><sortorder/></fee>"
                +"             </subfee>"
                +"             <fee><name>合计</name><value>10.00</value></fee>"
                +"         </feegroup>"
                +"         <feegroup>"
                +"             <name>带他人付费</name><sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>telnum</name><value>10.00</value><sortorder/></fee>"
                +"             </subfee>"
                +"             <fee><name>合计</name><value>10.00</value></fee>"
                +"         </feegroup>"
                +"         <feegroup>"
                +"             <name>其他</name><sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>222222</name><value>10.00</value><sortorder/></fee>"
                +"             </subfee>"
                +"             <fee><name>合计</name><value>10.00</value></fee>"
                +"         </feegroup>"
                +"     </feedetails>"

                +" </billinfo>"
                
                // 帐本信息
                +" <acctbalance>"
                       //<!--话费账户余额-->
                +"     <leftmoney1>10.00</leftmoney1>"
                       //<!--协议款余额-->
                +"     <leftmoney2>10.00</leftmoney2>     "
                       //<!--本期消费-->
                +"     <consume>10.00</consume>"
                       //<!--本期末欠费-->
                +"     <debtfee>10.00</debtfee>"
                +"     <acctlist>"
                +"         <acct>"
                               //<!--0 话费 1 协议款-->
                +"             <accttype>0</accttype>"
                               //<!--上月末结余 -->
                +"             <lastval>10.00</lastval>"
                               //<!--本月充值 -->
                +"             <income>10.00</income>"
                               //<!-- 本期返还  -->"
                +"             <returnfee>10.00</returnfee>"
                               //<!-- 退费 -->"
                +"             <backfee>5.00</backfee>"
                               //<!-- 本期可用  -->"
                +"             <canuse>10.00</canuse>"
                               //<!-- 消费支出 -->"
                +"             <outfee>10.00</outfee>"
                               //<!-- 其他支出 -->"
                +"             <otherfee>10.00</otherfee>"
                               //<!-- 本期末余额  -->"
                +"             <thisval>10.00</thisval >"
                +"         </acct>"
                +"     </acctlist>"
                +" </acctbalance> "
                
                // 资费推荐
                +" <recommend>推荐</recommend>"
                
                // <!-- 查询账期的往前推六个月返回有几个月返回几个月 -->
                +" <billhalfyeartrend>"
                +"     <bill><month>201201</month><money>100.00</money></bill>"
                +"     <bill><month>201202</month><money>200.00</money></bill>"
                +"     <bill><month>201203</month><money>300.00</money></bill>"
                +"     <bill><month>201204</month><money>400.00</money></bill>"
                +"     <bill><month>201205</month><money>500.00</money></bill>"
                +"     <bill><month>201206</month><money>600.00</money></bill>"
                +" </billhalfyeartrend>"
                
                // 积分信息
                +" <scoreinfo>"
                +"     <score>"
                //         <!--上期可用-->
                +"         <lastavail>10.00</lastavail>"
                //         <!--本期新增-->
                +"         <consume>10.00</consume>"
                //         <!--本期奖励-->
                +"         <award>10.00</award>"
                //         <!--本期跨区转入-->
                +"         <transin>10.00</transin>"
                //         <!--本期兑换上-->
                +"         <exchange>10.00</exchange>"
                //         <!--本期跨区转出-->
                +"         <transout>10.00</transout>"
                //         <!--本期作废-->
                +"         <clear>10.00</clear>"
                //         <!--可用积分-->
                +"         <thisavail>10.00</thisavail>"
                //         <!--本期总增加-->
                +"         <totalin>10.00</totalin>"
                //         <!--本期总增加-->
                +"         <totalout>10.00</totalout>"
                +"     </score>"
                +" </scoreinfo>"
                
                // 通讯量明细
                +" <pkginfo>"
                +"     <pkg>"
                +"         <!--套餐ID-->"
                +"         <pkgid>11111</pkgid>"
                +"         <!--套餐名称-->"
                +"         <pkgname>套餐名称</pkgname>"
                +"         <!--套餐描述-->"
                +"         <pkgdesc>套餐描述</pkgdesc>"
                +"         <!--字符列表-->"
                +"         <privlist>              "
                +"             <priv>"
                                   //<!--资费政策ID-->
                +"                 <rateid>1000014</rateid>"
                                   //<!--通讯类型：ALL,GSM,SMS,GPRS,WLAN,MMS,ISMG,MMR-->
                +"                 <freetype>GSM</freetype>"
                                   //<!--赠送的总量-->
                +"                 <total>22</total>"
                                   //<!--已经使用的量-->
                +"                 <used>15</used>"
                                   //<!--单位 1 次数 2 时长(秒) 3 费用(元) 4 流量(KB) 5 流量(M) 6 时长(小时) -->
                +"                 <attrtype>1</attrtype>"
                                   //<!--资费政策名称-->
                +"                 <feename>111111</feename>"
                +"             </priv>"
                +"         </privlist>"
                +"     </pkg>"
                +" </pkginfo>"
                
                // <!--代收业务费-->
                +" <spbill>"
                +"     <sp>"
                //         <!--业务端口-->
                +"         <spcode>业务端口</spcode>"
                //         <!--企业名称-->
                +"         <spname>企业名称</spname>"
                //         <!--业务名称-->
                +"         <servcode>业务名称</servcode>"
                //         <!--使用方式-->
                +"         <usetype>使用方式</usetype>"
                //         <!--费用类型-->
                +"         <feetype>费用类型</feetype>"
                //         <!--费用-->
                +"         <fee>10.00</fee>"
                +"     </sp>"
                +" </spbill>"
                
                // <!--出入账明细-->
                +" <inoutdetail>"
                
                +"     <inout>"
                //         <!--现金还是协议款 0:现金 1：协议款 2:专用账户变更 3：协议款-->"
                +"         <feetype>0</feetype>"
                //         <!--时间-->"
                +"         <date>2012-01-01 01:01:01</date>"
                //         <!--方式-->"
                +"         <model>方式</model>"
                //         <!--类别-->"
                +"         <type>类别</type>"
                //         <!--金额-->"
                +"         <fee>10.00</fee>"
                //         <!--备注-->"
                +"         <desc>备注...</desc>"
                +"     </inout>"
                
                +"     <inout>"
                //         <!--现金还是协议款 0:现金 1：协议款 2:专用账户变更 3：协议款-->"
                +"         <feetype>1</feetype>"
                //         <!--时间-->"
                +"         <date>2012-01-01 01:01:01</date>"
                //         <!--方式-->"
                +"         <model>方式</model>"
                //         <!--类别-->"
                +"         <type>类别</type>"
                //         <!--金额-->"
                +"         <fee>10.00</fee>"
                //         <!--备注-->"
                +"         <desc>备注...</desc>"
                +"     </inout>"
                
                +"     <inout>"
                //         <!--现金还是协议款 0:现金 1：协议款 2:专用账户变更 3：协议款-->"
                +"         <feetype>2</feetype>"
                //         <!--时间-->"
                +"         <date>2012-01-01 01:01:01</date>"
                //         <!--方式-->"
                +"         <model>方式</model>"
                //         <!--类别-->"
                +"         <type>类别</type>"
                //         <!--金额-->"
                +"         <fee>10.00</fee>"
                //         <!--备注-->"
                +"         <desc>备注...</desc>"
                +"     </inout>"
                
                +"     <inout>"
                //         <!--现金还是协议款 0:现金 1：协议款 2:专用账户变更 3：协议款-->"
                +"         <feetype>3</feetype>"
                //         <!--时间-->"
                +"         <date>2012-01-01 01:01:01</date>"
                //         <!--方式-->"
                +"         <model>方式</model>"
                //         <!--类别-->"
                +"         <type>类别</type>"
                //         <!--金额-->"
                +"         <fee>10.00</fee>"
                //         <!--备注-->"
                +"         <desc>备注...</desc>"
                +"     </inout>"
                
                +" </inoutdetail>"
                
                +"</custbill>"
                
                + "</message_content>]]></message_body></message_response>";
                        
                ReturnWrap rtw = null;
                try
                {
                    rtw = intMsgUtil.parseResponse_MonthFee_NX(response);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                
                return rtw;
    }

    /**
     * 查询用户现有支付方式
     * 
     * @param paramMap
     * @return
     * @see 
     */
    public ReturnWrap qryChargeType(Map<String, String> paramMap)
    {
        try
        {
            String menuID = (String)paramMap.get("menuID");
            String touchOID = (String)paramMap.get("touchOID");
            String operID = (String)paramMap.get("operID");
            String termID = (String)paramMap.get("termID");
            
            String telnum = (String)paramMap.get("telnum");// 手机号码
            String payType = (String) paramMap.get("paytype");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // 手机号码
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 支付方式
            Element eReq_paytype = eBody.addElement("paytype");
            eReq_paytype.addText(payType);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_chargetype_nx", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            return rtw;
        }
    }
    
    /**
     * 缴费接口
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/02/04 R003C13L01n01 OR_huawei_201302_122 
     */
    public ReturnWrap queryInvoice(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
                    "<message_head version=\"1.0\"><menuid>feeCharge</menuid>" +
                    "<process_code>cli_busi_chargefee_nx</process_code><verify_code></verify_code>" +
                    "<resp_time>20110630194643</resp_time><sequence><req_seq>4</req_seq><operation_seq>" +
                    "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>" +
                    "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>" +
                    "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
                    "<formnum>at95120110630195051</formnum><dealNum>95188004080808909</dealNum>" +
                    "<subsname>#闫涛</subsname><chargeamount>100</chargeamount><unitname>银川地区</unitname>" +
                    "<cycle></cycle><totalamount></totalamount><suminfo></suminfo><invoicecontent>" +
                    "费用名称1|10||费用名称2|10\n费用名称13|10||费用名称4|10\n费用名称5|10||费用名称6|10\n" +
                    "费用名称7|10||费用名称8|10\n费用名称9|10||费用名称10|10\n费用名称11|10||费用名称12|10\n" +
                    "费用名称13|10||费用名称14|10\n费用名称15|10||费用名称16|10\n" +
                    "</invoicecontent></message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    
    /**
     * 调用接口获取产品变更确认信息
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281
     */
    public ReturnWrap mainProductRecInfo(Map map)
    {
        try
        {
            
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
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    
    /**
     * 调用接口执行主体产品转换
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281
     */
    public ReturnWrap mainProductChangeSubmit(Map map)
    {
        try
        {
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recMainProdChange</menuid><process_code>cli_busi_ChangeProductSubmit</process_code><verify_code></verify_code><unicontact></unicontact><resp_time>20120425100313</resp_time><sequence><req_seq>5</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[不满足业务办理的条件，原因:提示：产品[彩铃]不允许办理[产品变更]业务!......]]></retmsg></retinfo></message_head></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    
    /**
     * 调用接口获取可转换产品
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281
     */
    public ReturnWrap qryChangeMainProduct(Map map)
    {
        try
        {
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            		"<menuid>recMainProdChange</menuid><process_code>cli_qry_changeMainProduct</process_code><verify_code></verify_code><resp_time>20130619150956</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
            		"<message_content><prodlist><prodid>name5</prodid></prodlist><prodlist><prodid>name6</prodid></prodlist><prodlist><prodid>name7</prodid></prodlist><prodlist><prodid>_pp.mz.cyk</prodid></prodlist><prodlist><prodid>pp.mz.2007</prodid></prodlist><prodlist><prodid>Christmasmain</prodid></prodlist><prodlist><prodid>zzwmainprod</prodid></prodlist><prodlist><prodid>pp.gt.st</prodid></prodlist><prodlist><prodid>pp.mz.cyk</prodid></prodlist><prodlist><prodid>123_test</prodid></prodlist><prodlist><prodid>pp.mz.lj</prodid></prodlist><prodlist><prodid>_pp.eo.pr</prodid></prodlist><prodlist><prodid>gaoprod</prodid></prodlist><prodlist><prodid>pp.gt.88rl.new.999</prodid></prodlist><prodlist><prodid>pp.gt.ct</prodid></prodlist><prodlist><prodid>pp.gt.68h.634</prodid></prodlist><prodlist><prodid>pp.gt.yg.634</prodid></prodlist><prodlist><prodid>pp.eo.qsk.634</prodid></prodlist><prodlist><prodid>pp.eo.sx.634</prodid></prodlist><prodlist><prodid>pp.gt.68y.634</prodid></prodlist><prodlist><prodid>pp.gt.68</prodid></prodlist><prodlist><prodid>pp.gt.88tcy.634</prodid></prodlist><prodlist><prodid>pp.gt.ygp.634</prodid></prodlist><prodlist><prodid>pp.eo.ch.634</prodid></prodlist><prodlist><prodid>pp.eo.es</prodid></prodlist><prodlist><prodid>pp.eo.szxctk1</prodid></prodlist><prodlist><prodid>pp.eo.ffct.634</prodid></prodlist><prodlist><prodid>pp.eo.07szxctk.634</prodid></prodlist><prodlist><prodid>pp.eo.fk</prodid></prodlist><prodlist><prodid>pp.eo.xyh.634</prodid></prodlist><prodlist><prodid>pp.eo.zfgj.634</prodid></prodlist><prodlist><prodid>pp.eo.ty</prodid></prodlist><prodlist><prodid>pp.eo.fr</prodid></prodlist><prodlist><prodid>pp.gt.lc</prodid></prodlist><prodlist><prodid>pp.gt.stp</prodid></prodlist><prodlist><prodid>pp.eo.pr</prodid></prodlist><prodlist><prodid>pp.gt.ct1p</prodid></prodlist><prodlist><prodid>pp.gt.lcp</prodid></prodlist><prodlist><prodid>pp.gt.frp</prodid></prodlist><prodlist><prodid>pp.gt.cslp</prodid></prodlist><prodlist><prodid>pp.gt.ct2p</prodid></prodlist></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    
    /**
     * 根据类型查询积分值
     * 
     * @param map
     * @return
     * @remark create zWX176560 2013/08/22 R003C13L09n01 OR_NX_201308_595
     */
    public ReturnWrap qryUserScoreInfoByType(Map paramMap)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryUserScoreInfoByType</menuid><process_code>cli_qry_userscoreInfo</process_code><verify_code></verify_code><resp_time>20130619150956</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    + "<message_content><crset><COL_0>ConsumeScore</COL_0><COL_1>1111</COL_1></crset><crset><COL_0>InnetScore</COL_0><COL_1>1111</COL_1></crset><crset><COL_0>BrandScore</COL_0><COL_1>1111</COL_1></crset><crset><COL_0>OtherAward</COL_0><COL_1>1111</COL_1></crset></message_content>]]></message_body></message_response>";
            
          //String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
          //        + "<menuid>qryUserScoreInfoByType</menuid><process_code>cli_qry_userscoreInfo</process_code><verify_code></verify_code><resp_time>20130619150956</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[No Information!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
          //        + "<message_content></message_content>]]></message_body></message_response>";
            
          // String response = "<!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML 2.0//EN"><html><head><title>500 Internal Server Error</title></head><body><h1>Internal Server Error</h1><p>The server encountered an internal error or misconfiguration and was unable to complete your request.</p><p>Please contact the server administrator,you@example.com and inform them of the time the error occurred,and anything you might have done that may have"
          // + "caused the error.</p><p>More information about this error may be available in the server error log.</p><hr><address>Apache/2.0.63 (Unix) mod_fastcgi/2.4.6 Server at 192.168.11.145 Port 9700</address></body></html>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    @Override
    public ReturnWrap addChargeType(Map<String, String> paramMap)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReturnWrap cancelChargeType(Map<String, String> paramMap)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * 空白卡资源暂选
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap blankCardTmpPick(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_blankcardtmppick_nx</process_code><verify_code></verify_code>"
                    + "<resp_time>20140311153704</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<siminfo>89860050150353805206,460006345032906,B074F86AA65E4F83C1AE0D2DA4A8B79A,+8613800634500,1234,6767,97827907,89550562,</siminfo>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }

    /**
     * 校验空白卡资源是否可用
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkBlankNo(Map map)
    {
    	try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
				+"<message_head version=\"1.0\"><menuid></menuid><process_code>cli_chkblankno_nx"
				+"</process_code><verify_code></verify_code><resp_time>20140306193816</resp_time>"
				+"<sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo>"
				+"<rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
				+"</retmsg></retinfo></message_head></message_response>";
			
			return intMsgUtil.parseResponse(response);
		} 
    	catch (Exception e)
    	{
			ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
		}
    	
    }
    
    /**
     * 开户时证件号码校验
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkCertSubs(Map map)
    {
    	try {
    		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
    			+"<menuid>openAccount</menuid><process_code>cli_chkcertsubs_nx</process_code><verify_code></verify_code>"
    			+"<resp_time>20140305180720</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence>"
    			+"<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
    			+"</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
    			+"<ifValid>1</ifValid></message_content>]]></message_body></message_response>";
    		
    		return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
		}
    }

    /**
     * 号卡校验
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap chkTelSimcard(Map map)
    {
    	try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
				+"<message_head version=\"1.0\"><menuid></menuid><process_code>cli_chktelsimcard_nx"
				+"</process_code><verify_code></verify_code><resp_time>20140307115011</resp_time>"
				+"<sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo>"
				+"<rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
				+"</retmsg></retinfo></message_head></message_response>";
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
		}
    }

    /**
     * 查询手机号码列表
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryNumberByProdid(Map map)
    {
        try {
//            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
//                    + "<message_head version=\"1.0\"><menuid></menuid><process_code>cli_qry_numbynet</process_code>"
//                    + "<verify_code></verify_code><resp_time>20110621111358</resp_time><sequence><req_seq>28</req_seq>"
//                    + "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
//                    + "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
//                    + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
//                    + "<tellist><telnum>18709610009</telnum><productinfo></productinfo><fee>1000</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709611604</telnum><productinfo></productinfo><fee>2000</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709614964</telnum><productinfo></productinfo><fee>3000</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709617084</telnum><productinfo></productinfo><fee>400</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709614472</telnum><productinfo></productinfo><fee>50</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709610013</telnum><productinfo></productinfo><fee>60</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709612745</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709610034</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709610001</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709610018</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709614134</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709610022</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709617424</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709610039</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709610006</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709614870</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709612647</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709610754</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709610016</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709617947</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709618430</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709618174</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709612074</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709610020</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709610037</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709610004</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709614740</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709610025</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709617154</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "<tellist><telnum>18709613914</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
//                    + "</message_content>]]></message_body></message_response>";
        	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>"
        		+"openAccount</menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code><resp_time>20140311185636"
        		+"</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>"
        		+"100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
        		+"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
        		+"<tellist><telnum>13706387658</telnum><productinfo>未知</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706386386</telnum><productinfo>未知</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706389126</telnum><productinfo>未知</productinfo><fee>0</fee><org_id>SD.LP.01.02.01</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706386006</telnum><productinfo>未知</productinfo><fee>128</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706388159</telnum><productinfo>未知</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706388259</telnum><productinfo>未知</productinfo><fee>0</fee><org_id>SD.LP.01.02.01</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706385226</telnum><productinfo>未知</productinfo><fee>128</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706388136</telnum><productinfo>未知</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706386786</telnum><productinfo>未知</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706385129</telnum><productinfo>未知</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706387444</telnum><productinfo>未知</productinfo><fee>128</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706386477</telnum><productinfo>未知</productinfo><fee>128</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706385469</telnum><productinfo>未知</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706387176</telnum><productinfo>未知</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706386565</telnum><productinfo>未知</productinfo><fee>0</fee><org_id>SD.LP.01.02.04</org_id><region>634</region><tellevel>豆豆测试啦</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706384958</telnum><productinfo>未知</productinfo><fee>0</fee><org_id>SD.LP.01.02.01</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706385768</telnum><productinfo>未知</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");

            return rtw;
        }
    }

    /**
     * 计算费用
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap reckonRecFee(Map map)
    {
    	try{
    	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
    		+"<message_head version=\"1.0\"><menuid>openAccount</menuid><process_code>cli_reckonrecfee_nx"
    		+"</process_code><verify_code></verify_code><resp_time>20140311155844</resp_time><sequence>"
    		+"<req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype>"
    		+"<retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
    		+"</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
    		+"<message_content><feelist><feename>新入网用户话费预付款1</feename><realfee>800</realfee><num>"
    		+"1</num><feeitem>Prepay</feeitem><discountfee>0</discountfee><receivablefee>800</receivablefee>"
    		+"</feelist></message_content>]]></message_body></message_response>";
    	
    	return intMsgUtil.parseResponse(response);
    	}catch(Exception ex)
    	{
    		ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");

            return rtw;
    	}
    }

    /**
     * 号码资源暂选接口
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap telNumTmpPick(Map map)
    {
    	try{
        	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
        		+"<message_head version=\"1.0\"><menuid></menuid><process_code>cli_telnumtmppick_nx"
        		+"</process_code><verify_code></verify_code><resp_time>20140306161150</resp_time>"
        		+"<sequence><req_seq>2</req_seq><operation_seq></operation_seq></sequence><retinfo>"
        		+"<rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
        		+"</retmsg></retinfo></message_head></message_response>";
        	
        	return intMsgUtil.parseResponse(response);
    	}
    	catch(Exception ex)
    	{
    		ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");

            return rtw;
    	}
    }

    /**
     * 开户
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap terminalInstall(Map map)
    {
    	try{
        	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
        		+"<menuid>cashChargeByOpen</menuid><process_code>cli_terminalinstall_nx</process_code><verify_code>"
        		+"</verify_code><resp_time>20140312130130</resp_time><sequence><req_seq>2</req_seq><operation_seq>"
        		+"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
        		+"<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
        		+"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><installformnum>" +
        		"634140408898309356</installformnum><oid>88009898309338</oid></message_content>]]></message_body></message_response>";
        	
        	return intMsgUtil.parseResponse(response);
    	}
    	catch(Exception ex)
    	{
    		ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");

            return rtw;
    	}
    }
    
	@Override
	public ReturnWrap getMonInvoiceData(Map<String, String> paramMap) {
		try
        {
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
            		"<invoicelist><name>paynumno</name><value>9522003091182</value></invoicelist>" +
            		"<invoicelist><name>username</name><value>夏宫</value></invoicelist>" +
            		"<invoicelist><name>telnumber</name><value>13469625267</value></invoicelist>" +
            		"<invoicelist><name>formnum</name><value>88009855697681</value></invoicelist>" +
            		"<invoicelist><name>callfeeCreateTime</name><value>2014.04---2014.04</value></invoicelist>" +
            		"<invoicelist><name>totalmoneydx</name><value>伍拾圆整</value></invoicelist>" +
            		"<invoicelist><name>totalmoney</name><value>￥50.00</value></invoicelist>" +
            		"<invoicelist><name>note</name><value></value></invoicelist>" +
            		"<invoicelist><name>WorkStation</name><value>石嘴山地区</value></invoicelist>" +
            		"<invoicelist><name>time</name><value>2014     06      23</value></invoicelist>" +
            		"<invoicelist><name>printtime</name><value>2014.06.23</value></invoicelist>" +
            		"<invoicelist><name>OperatorName</name><value>系统管理员</value></invoicelist>" +
            		"<invoicelist><name>ContentItem1Name</name><value>通信服务费:</value></invoicelist>" +
            		"<invoicelist><name>ContentItem1</name><value>50.00</value></invoicelist>" +
            		"<invoicelist><name>thisinvamt</name><value>本次发票金额: 88.12元</value></invoicelist>" +
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
            return new ReturnWrap();
        }
	}

	/**
     * 查询账期（宁夏）
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印
     */
	@Override
	public ReturnWrap qryBillCycle(Map<String, String> paramMap)
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
            + "<cyclelist><cycle>20120601</cycle><startdate>2012-06-01</startdate><enddate>2012-06-30</enddate><acctid>10000001</acctid><unionacct>1</unionacct><unionacct>2</unionacct></cyclelist>"
            
            + "</message_content>]]></message_body></message_response>";
            
            ReturnWrap rtw = null;
            try
            {
                rtw = intMsgUtil.parseResponse(response);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
            return rtw;
	}
}
