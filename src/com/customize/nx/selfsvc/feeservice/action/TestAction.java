package com.customize.nx.selfsvc.feeservice.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.gmcc.boss.common.base.CPEntity;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.util.CommonUtil;

public class TestAction {

	public static void main(String[] args) {
		Map<String, String> resultMap = null;
		try {
			StringBuffer records = new StringBuffer(1024);
			ReturnWrap rw = parseResponse("<message_response>\r\n" + 
					"	<message_head version=\"1.0\">\r\n" + 
					"		<menuid>qryMuster</menuid>\r\n" + 
					"		<process_code>cli_qry_cdr2012_nx</process_code>\r\n" + 
					"		<verify_code/>\r\n" + 
					"		<resp_time>2018-04-02 15:40:40</resp_time>\r\n" + 
					"		<sequence>\r\n" + 
					"			<req_seq>5205</req_seq>\r\n" + 
					"			<operation_seq>5205</operation_seq>\r\n" + 
					"		</sequence>\r\n" + 
					"		<retinfo>\r\n" + 
					"			<rettype>0</rettype>\r\n" + 
					"			<retcode>100</retcode>\r\n" + 
					"			<retmsg>\r\n" + 
					"				<![CDATA[Processing the request succeeded!]]>\r\n" + 
					"			</retmsg>\r\n" + 
					"		</retinfo>\r\n" + 
					"	</message_head>\r\n" + 
					"	<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?>\r\n" + 
					"<message_content>\r\n" + 
					"	<totalfee>0.00元</totalfee>\r\n" + 
					"	<smstotalnum/>\r\n" + 
					"	<mmstotalnum/>\r\n" + 
					"	<localcall/>\r\n" + 
					"	<longcall/>\r\n" + 
					"	<roamcall/>\r\n" + 
					"	<citycall/>\r\n" + 
					"	<othercall/>\r\n" + 
					"	<inlancall/>\r\n" + 
					"	<colonycall/>\r\n" + 
					"	<interncall/>\r\n" + 
					"	<inlanroam/>\r\n" + 
					"	<colonyroam/>\r\n" + 
					"	<internroam/>\r\n" + 
					"	<gprssum>48条</gprssum>\r\n" + 
					"	<chargeflux>0.000M</chargeflux>\r\n" + 
					"	<freechargeflux>62.863M</freechargeflux>\r\n" + 
					"	<sumflux>62.863M</sumflux>\r\n" + 
					"	<wlansumtime>0秒</wlansumtime>\r\n" + 
					"	<wlansumfee>0.00元</wlansumfee>\r\n" + 
					"	<wlansum>0条</wlansum>\r\n" + 
					"	<wlansumflux>0.000M</wlansumflux>\r\n" + 
					"	<gprssumfee/>\r\n" + 
					"	<cmwapsum>0条</cmwapsum>\r\n" + 
					"	<cmnetsum>48条</cmnetsum>\r\n" + 
					"	<cmwapflux>0M</cmwapflux>\r\n" + 
					"	<cmnetflux>0M</cmnetflux>\r\n" + 
					"	<cmwapfreeflux>0M</cmwapfreeflux>\r\n" + 
					"	<cmnetfreeflux>62.863M</cmnetfreeflux>\r\n" + 
					"	<cmwapsumflux>0M</cmwapsumflux>\r\n" + 
					"	<cmnetsumflux>62.863M</cmnetsumflux>\r\n" + 
					"	<pubwlansum>0条</pubwlansum>\r\n" + 
					"	<campuswlansum>0条</campuswlansum>\r\n" + 
					"	<pubwlanflux>0M</pubwlanflux>\r\n" + 
					"	<campuswlanflux>0M</campuswlanflux>\r\n" + 
					"	<pubwlantime>0秒</pubwlantime>\r\n" + 
					"	<campuswlantime>0秒</campuswlantime>\r\n" + 
					"	<pubwlanfee>0元</pubwlanfee>\r\n" + 
					"	<campuswlanfee>0元</campuswlanfee>\r\n" + 
					"	<cmwapfee/>\r\n" + 
					"	<cmnetfee/>\r\n" + 
					"	<billinfo>起始时间@_@通信地点@_@上网方式@_@时长@_@流量@_@2G/3G/4G标识@_@基站信息@_@通信费|\r\n" + 
					"	2018-04-01 00:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@12KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 01:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@3KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 02:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@2KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 03:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@11KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 04:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@14KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 05:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@2KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 06:00:03@_@宁夏@_@CMNET@_@59分57秒@_@3KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 07:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@5KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 08:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@8KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 09:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@12KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 10:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@16KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 11:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@5KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 12:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@8KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 13:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@2KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 14:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@8KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 15:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@3KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 16:00:03@_@宁夏@_@CMNET@_@29分60秒@_@10KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 16:30:03@_@宁夏@_@CMNET@_@29分60秒@_@1MB936KB@_@4G@_@951A|0970910C@_@0.0000000元|2018-04-01 17:00:03@_@宁夏@_@CMNET@_@29分60秒@_@850KB@_@4G@_@957D|0979760D@_@0.0000000元|2018-04-01 17:30:03@_@宁夏@_@CMNET@_@29分60秒@_@2MB82KB@_@4G@_@9510|08F17584@_@0.0000000元|2018-04-01 18:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@3KB@_@4G@_@9510|08F17584@_@0.0000000元|2018-04-01 19:00:03@_@宁夏@_@CMNET@_@29分60秒@_@8KB@_@4G@_@9510|08F17584@_@0.0000000元|2018-04-01 19:30:03@_@宁夏@_@CMNET@_@29分60秒@_@4MB196KB@_@4G@_@951A|0970910D@_@0.0000000元|2018-04-01 20:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@2MB88KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 21:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@5KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 22:00:03@_@宁夏@_@CMNET@_@59分57秒@_@2KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-01 23:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@7KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-02 00:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@3KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-02 01:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@2KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-02 02:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@2KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-02 03:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@1KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-02 04:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@9KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-02 05:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@11KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-02 06:00:03@_@宁夏@_@CMNET@_@59分57秒@_@2KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-02 07:00:03@_@宁夏@_@CMNET@_@29分60秒@_@35KB@_@4G@_@9515|09743D03@_@0.0000000元|2018-04-02 07:30:03@_@宁夏@_@CMNET@_@29分60秒@_@5MB658KB@_@4G@_@9516|0979920B@_@0.0000000元|2018-04-02 08:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@8MB297KB@_@4G@_@9516|0978180F@_@0.0000000元|2018-04-02 09:00:03@_@宁夏@_@CMNET@_@29分60秒@_@225KB@_@4G@_@9516|09781819@_@0.0000000元|2018-04-02 09:30:03@_@宁夏@_@CMNET@_@29分60秒@_@127KB@_@4G@_@9516|0978180F@_@0.0000000元|2018-04-02 10:00:03@_@宁夏@_@CMNET@_@1小时0分0秒@_@163KB@_@4G@_@9516|0978180F@_@0.0000000元|2018-04-02 11:00:03@_@宁夏@_@CMNET@_@29分60秒@_@3MB536KB@_@4G@_@9516|09781819@_@0.0000000元|2018-04-02 11:30:03@_@宁夏@_@CMNET@_@29分60秒@_@54KB@_@4G@_@9516|0978180F@_@0.0000000元|2018-04-02 12:00:03@_@宁夏@_@CMNET@_@52分23秒@_@10MB370KB@_@4G@_@9516|0978180F@_@0.0000000元|2018-04-02 12:30:03@_@宁夏@_@CMNET@_@34分8秒@_@20MB2KB@_@4G@_@9516|09781819@_@0.0000000元|2018-04-02 13:04:11@_@宁夏@_@CMNET@_@1小时0分0秒@_@2MB79KB@_@4G@_@9516|0978180F@_@0.0000000元|2018-04-02 14:04:11@_@宁夏@_@CMNET@_@29分60秒@_@144KB@_@4G@_@9516|09781719@_@0.0000000元|2018-04-02 14:34:11@_@宁夏@_@CMNET@_@29分60秒@_@673KB@_@4G@_@9516|0978180F@_@0.0000000元|2018-04-02 15:04:11@_@宁夏@_@CMNET@_@29分60秒@_@270KB@_@4G@_@9516|0978180F@_@0.0000000元|</billinfo>\r\n" + 
					"</message_content>]]>\r\n" + 
					"	</message_body>\r\n" + 
					"</message_response>\r\n" + 
					"");
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
	            resultMap.put("GPRSWLAN", tagSet.GetValue("billinfo"));
	        }
			
			
			// GPRS话单条数
            String gprssum = resultMap.get("gprssum");
            
            // CMWAP条数
            String cmwapsum = resultMap.get("cmwapsum");

            // CMNET条数
            String cmnetsum = resultMap.get("cmnetsum");
            
            // 收费流量
            String chargeflux = resultMap.get("chargeflux");
            
            // CMWAP收费流量
            String cmwapflux = resultMap.get("cmwapflux");
            
            // CMNET收费流量
            String cmnetflux = resultMap.get("cmnetflux");
            
            // 免费流量
            String freechargeflux = resultMap.get("freechargeflux");
            
            // CMWAP免费流量
            String cmwapfreeflux = resultMap.get("cmwapfreeflux");
            
            // CMNET免费流量
            String cmnetfreeflux = resultMap.get("cmnetfreeflux");                    
            
            // 总流量
            String sumflux = resultMap.get("sumflux");
            
            // CMWAP总流量
            String cmwapsumflux = resultMap.get("cmwapsumflux");
            
            // CMNET总流量
            String cmnetsumflux = resultMap.get("cmnetsumflux");
            
            // WLAN话单条数
            String wlansum = resultMap.get("wlansum");
            
            // 公众WLAN条数
            String pubwlansum = resultMap.get("pubwlansum");

            // 校园WLAN条数
            String campuswlansum = resultMap.get("campuswlansum");

            // WLAN总流量
            String wlansumflux = resultMap.get("wlansumflux");

            // 公众WLAN流量
            String pubwlanflux = resultMap.get("pubwlanflux");

            // 校园WLAN流量
            String campuswlanflux = resultMap.get("campuswlanflux");

            // WLAN总时长
            String wlansumtime = resultMap.get("wlansumtime");

            // 公众WLAN时长
            String pubwlantime = resultMap.get("pubwlantime");

            // 校园WLAN时长
            String campuswlantime = resultMap.get("campuswlantime");

            // WLAN总费用
            String wlansumfee = resultMap.get("wlansumfee");

            // 公众WLAN费用
            String pubwlanfee = resultMap.get("pubwlanfee");

            // 校园WLAN费用
            String campuswlanfee = resultMap.get("campuswlanfee");                    
            
            // 单条记录
            String record = "";                    

            // 单条记录的各字段
            String[] fields = null;
            
            // 单条记录的时间
            String timeField = "";
            
            // 单条记录对应的日期
            String day = "";
            
            String lastDay = "";
            
            String allRecords = resultMap.get("GPRSWLAN");
            if (null != allRecords && !"".equals(allRecords.trim()))
            {                        
                records.append("'       GPRS话单条数:").append(CommonUtil.fillLeftBlank(gprssum, 19)).append("',")
                        .append("'    其中：CMWAP条数:").append(CommonUtil.fillLeftBlank(cmwapsum, 19)).append("',")
                        .append("'    其中：CMNET条数:").append(CommonUtil.fillLeftBlank(cmnetsum, 19)).append("',");
                
                records.append("'           收费流量:").append(CommonUtil.fillLeftBlank(chargeflux, 19)).append("',")
                        .append("'其中：CMWAP收费流量:").append(CommonUtil.fillLeftBlank(cmwapflux, 19)).append("',")
                        .append("'其中：CMNET收费流量:").append(CommonUtil.fillLeftBlank(cmnetflux, 19)).append("',");
                
                records.append("'           免费流量:").append(CommonUtil.fillLeftBlank(freechargeflux, 19)).append("',")
                        .append("'其中：CMWAP免费流量:").append(CommonUtil.fillLeftBlank(cmwapfreeflux, 19)).append("',")
                        .append("'其中：CMNET免费流量:").append(CommonUtil.fillLeftBlank(cmnetfreeflux, 19)).append("',");
        
                records.append("'             总流量:").append(CommonUtil.fillLeftBlank(sumflux, 19)).append("',")
                        .append("'  其中：CMWAP总流量:").append(CommonUtil.fillLeftBlank(cmwapsumflux, 19)).append("',")
                        .append("'  其中：CMNET总流量:").append(CommonUtil.fillLeftBlank(cmnetsumflux, 19)).append("',");
                                        
                records.append("'       WLAN话单条数:").append(CommonUtil.fillLeftBlank(wlansum, 19)).append("',")
                        .append("' 其中：公众WLAN条数:").append(CommonUtil.fillLeftBlank(pubwlansum, 19)).append("',")
                        .append("' 其中：校园WLAN条数:").append(CommonUtil.fillLeftBlank(campuswlansum, 19)).append("',");
                
                records.append("'         WLAN总流量:").append(CommonUtil.fillLeftBlank(wlansumflux, 19)).append("',")
                        .append("' 其中：公众WLAN流量:").append(CommonUtil.fillLeftBlank(pubwlanflux, 19)).append("',")
                        .append("' 其中：校园WLAN流量:").append(CommonUtil.fillLeftBlank(campuswlanflux, 19)).append("',");
                
                records.append("'         WLAN总时长:").append(CommonUtil.fillLeftBlank(wlansumtime, 19)).append("',")
                        .append("' 其中：公众WLAN时长:").append(CommonUtil.fillLeftBlank(pubwlantime, 19)).append("',")
                        .append("' 其中：校园WLAN时长:").append(CommonUtil.fillLeftBlank(campuswlantime, 19)).append("',");
                                        
                records.append("'         WLAN总费用:").append(CommonUtil.fillLeftBlank(wlansumfee, 19)).append("',")
                        .append("' 其中：公众WLAN费用:").append(CommonUtil.fillLeftBlank(pubwlanfee, 19)).append("',")
                        .append("' 其中：校园WLAN费用:").append(CommonUtil.fillLeftBlank(campuswlanfee, 19)).append("',");
                
                //records.append("'起始时间 通信地点 上网方式 时长 流量 2G/3G标识 通信费',");
                String[] recordsArray = allRecords.split("\\|");
                int recordsCount = recordsArray.length/2+1;
                
                for (int i = 0; i < recordsCount; i++)
                {
                	if(i==0) {
                		record = recordsArray[i];
                	}else {
                		record = recordsArray[(i*2)-1]+recordsArray[i*2];
                	}
                	System.out.println("record==="+record);
                    
                    // 空记录
                    if (null == record || "".equals(record.trim()))
                    {
                        continue;
                    }                            

                    // 如果以@_@结尾，说明最后一个字段为空，追加空格
                    if (record.endsWith("@_@"))
                    {
                        record = record + " ";
                    }                            

                    // 记录中若存在@_@@_@，说明中间某个字段为空，使用空格代替，变为@_@ @_@
                    record = record.replaceAll("@@", "@ @");
                    
                    fields = record.split("@_@");
                    
                    timeField = fields[0];
                    
                    // 每条记录的第一个字段都是时间，时间格式应为yyyy-mm-dd hh24:mi:ss，从中取日期yyyy-mm-dd
                    int index = timeField.indexOf(" ");
                    if (-1 != index)
                    {
                        day = timeField.substring(0, index);
                    }
                    else
                    {
                        day = timeField;
                    }
                    
                    // 如果当前记录的日期与之前处理的记录的日期不同，则向vector中添加日期，同时在后面追加保存该天记录的列表
                    // 即vector中保存的数据为：8月1日，8月1日的全部记录，8月2日，8月2日的全部记录
                    if (!lastDay.equals(day))
                    {
                        lastDay = day;
                        
                        records.append("'").append(day).append("',");
                    } 
                    
                    records.append(formatGprsWlanRecord(record));
                }
                System.out.println("resords=============="+records);
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
     * 格式化单条上网详单记录
     * 起始时间，占8个字节，左对齐；通信地点5、上网方式6、时长11、流量7个字节，左对齐；2G/3G(3个字节)通信费(元)占5个字节，右对齐。
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static String formatGprsWlanRecord(String record)
    {
        StringBuffer buffer = new StringBuffer(256);
        
        // 单条记录的各字段
        String[] fields = record.split("@_@");
        
        String[] rows = null;
        int rowNum = 0;
        int tempRowNum = 0;
        
        // 通信地点        
        if (fields[1].getBytes().length <= 5)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[1].length() % 2)
            {
                tempRowNum = fields[1].length() / 2;
            }
            else
            {
                tempRowNum = fields[1].length() / 2 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 对上网方式进行特殊处理，英文占多数        
        tempRowNum = 0;
        
        int offset = 0;
        int len = 6;
        while (offset < fields[2].length())
        {
            if (offset + len > fields[2].length())
            {
                len = fields[2].length() - offset;
            }
            
            while (fields[2].substring(offset, offset + len).getBytes().length > 6)
            {
                len -= 1;
            }
            
            offset += len;
            
            tempRowNum++;
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 时长
        if (fields[3].getBytes().length <= 12)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[3].length() % 12)
            {
                tempRowNum = fields[3].length() / 12;
            }
            else
            {
                tempRowNum = fields[3].length() / 12 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        // 流量
        if (fields[4].getBytes().length <= 7)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fields[4].length() % 7)
            {
                tempRowNum = fields[4].length() / 7;
            }
            else
            {
                tempRowNum = fields[4].length() / 7 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        } 
        
        // 2G/3G
        String fileds6 = fields[5];
        if(fileds6.equals("2G/3G标识"))
        {
            fileds6 = "2G/3G";
        }
        if (fileds6.getBytes().length <= 3)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == fileds6.length() % 2)
            {
                tempRowNum = fileds6.length() / 2;
            }
            else
            {
                tempRowNum = fileds6.length() / 2 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
        
        String txFee = fields[7];
        if(txFee.equals("通信费"))
        {
            txFee = "通信费(元)";
        }
        else
        {
            txFee = txFee.substring(0,txFee.length()-1);
        }
        // 通讯费
        if (txFee.getBytes().length <= 5)
        {
            tempRowNum = 1;
        }
        else
        {
            if (0 == txFee.length() % 4)
            {
                tempRowNum = txFee.length() / 4;
            }
            else
            {
                tempRowNum = txFee.length() / 4 + 1;
            }                              
        }
        
        if (rowNum < tempRowNum)
        {
            rowNum = tempRowNum;
        }
            
        rows = new String[rowNum];
        
        // 对上网方式进行特殊处理，英文占多数
        offset = 0;
        len = 6;
        
        for (int m = 0; m < rowNum; m++)
        {
            rows[m] = "";
            
            if (0 == m)
            {
                int index = fields[0].indexOf(" ");
                if (-1 != index)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0].substring(index + 1), 8); 
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[0], 8);  
                }                   
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 8);
            }
            
            // 通信地点
            if (fields[1].getBytes().length <= 5)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[1], 5);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 5);
            	}
            }
            else
            {
            	if (fields[1].length() > (m + 1) * 2)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 2, (m + 1) * 2), 5);
                }
                else if (fields[1].length() > m * 2)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[1].substring(m * 2), 5);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 5);
                }
            }
            
            // 上网方式
            if (offset < fields[2].length())
            {
                if (offset + len > fields[2].length())
                {
                    len = fields[2].length() - offset;
                }
                
                while (fields[2].substring(offset, offset + len).getBytes().length > 6)
                {
                    len -= 1;
                }
                rows[m] += CommonUtil.fillRightBlank(fields[2].substring(offset, offset + len), 6);
                offset += len; 
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 6);
            }
            
            // 时长
            if (fields[3].getBytes().length <= 12)
            {
            	if (0 == m)
            	{
            		rows[m] += CommonUtil.fillRightBlank(fields[3], 12);
            	}
            	else
            	{
            		rows[m] += CommonUtil.fillRightBlank("", 12);
            	}
            }
            else
            {
            	if (fields[3].length() > (m + 1) * 8)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 8, (m + 1) * 8), 12);
                }
                else if (fields[3].length() > m * 8)
                {
                    rows[m] += CommonUtil.fillRightBlank(fields[3].substring(m * 8), 12);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 12);
                }
            }
            
            // 流量
            if (fields[4].length() > (m + 1) * 6)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 6, (m + 1) * 6), 7);
            }
            else if (fields[4].length() > m * 6)
            {
                rows[m] += CommonUtil.fillRightBlank(fields[4].substring(m * 6), 7);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 7);
            }
            
            // 2G/3G
            if (fileds6.length() > (m + 1) * 2)
            {
                rows[m] += CommonUtil.fillRightBlank(fileds6.substring(m * 2, (m + 1) * 2), 3);
            }
            else if (fileds6.length() > m * 2)
            {
                rows[m] += CommonUtil.fillRightBlank(fileds6.substring(m * 2), 3);
            }
            else
            {
                rows[m] += CommonUtil.fillRightBlank("", 3);
            }
            
//            if (fields[5].getBytes().length <= 3)
//            {
//                if (0 == m)
//                {
//                    rows[m] += CommonUtil.fillRightBlank(fields[5], 3);
//                }
//                else
//                {
//                    rows[m] += CommonUtil.fillRightBlank("", 3);
//                }
//            }
//            else
//            {
//                if (fields[5].length() > (m + 1) * 2)
//                {
//                    rows[m] += CommonUtil.fillRightBlank(fields[5].substring(m * 2, (m + 1) * 2), 3);
//                }
//                else if (fields[5].length() > m * 2)
//                {
//                    rows[m] += CommonUtil.fillRightBlank(fields[5].substring(m * 2), 3);
//                }
//                else
//                {
//                    rows[m] += CommonUtil.fillRightBlank("", 3);
//                }
//            }
            
            // 通讯费
            if (txFee.getBytes().length <= 5)
            {
                if (0 == m)
                {
                    rows[m] += CommonUtil.fillRightBlank(txFee, 5);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 5);
                }
            }
            else
            {
                if (txFee.length() > (m + 1) * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(txFee.substring(m * 5, (m + 1) * 5), 5);
                }
                else if (txFee.length() > m * 5)
                {
                    rows[m] += CommonUtil.fillRightBlank(txFee.substring(m * 5), 5);
                }
                else
                {
                    rows[m] += CommonUtil.fillRightBlank("", 5);
                }
            }
            
            buffer.append("'").append(rows[m]).append("',");
        }
        
        return buffer.toString();
    }

	
    /**
     * 解析响应报文
     * 
     * @param response
     * @return ReturnWrap
     * 
     * 将响应报文解析成ReturnWrap对象
     */
    public static ReturnWrap parseResponse(String response) throws Exception
    {
        Document doc = DocumentHelper.parseText(response);
        Element root = doc.getRootElement();
        Element eHead = root.element("message_head");
        
        // String resType = null;
        String rspCode = null;
        String rspDesc = null;
        
        // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        
        String verifyCode = "";
        String contactid = "";
        
        ReturnWrap rtData = new ReturnWrap();
        CTagSet tagSet = new CTagSet();
        CRSet rSet = new CRSet();
        
        Vector<CPEntity> retVector = new Vector<CPEntity>();
        // 解析信息头，保存错误码和错误信息
        if (eHead != null)
        {
            Element eVerifyCode = eHead.element("verify_code");
            if (null != eVerifyCode)
            {
                verifyCode = eVerifyCode.getText();
            }            
            
            Element eUnicontact = eHead.element("unicontact");
            if (null != eUnicontact)
            {
                contactid = eUnicontact.getText();    
            }                    
            
            Element eRetinfo = eHead.element("retinfo");
            
            // resType = eRetinfo.element("rettype").getText();
            rspCode = eRetinfo.element("retcode").getText();
            rspDesc = eRetinfo.element("retmsg").getText();
            
            // 状态
            rtData.setStatus(1);
            // 如果错误码不是0，状态设为0
            if (!"100".equals(rspCode) && !"0".equals(rspCode))
            {
                // rspDesc = getErrorMessage(resType, rspCode, rspDesc);
                rtData.setStatus(0);
                rtData.setErrcode(new Integer(rspCode).intValue());
            }
            else
            {
                rtData.setErrcode(100);
            }
            // 错误信息
            rtData.setReturnMsg(rspDesc);
        }
        // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
        
        Element ebody = root.element("message_body");
        if (ebody == null)
        {
            return rtData;
        }
        String rspCont = ebody.getText();
        if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
        {
            rspCont = rspCont.replaceAll("&", "_");
        }
        Document body = DocumentHelper.parseText(rspCont);
        Iterator itBody = body.getRootElement().elementIterator();
        
        // 解析消息体，将信息封装为Tagset或Rset
        int i = 0;
        int j = 0;
        while (itBody.hasNext())
        {
            Element e = (Element)itBody.next();
            Iterator itChild = e.elementIterator();
            if (!itChild.hasNext())
            {
                String key = e.getName();
                String val = e.getText().replace("^_^", "&");
                tagSet.SetValue(key, val);
            }
            else
            {
                if (i == 0)
                {
                    rSet = new CRSet(e.elements().size());
                }
                
                rSet.AddRow();
                Element eChild = null;
                while (itChild.hasNext())
                {
                    eChild = (Element)itChild.next();
                    rSet.SetValue(i, j, eChild.getText().replace("^_^", "&"));
                    j++;
                }
                i++;
                j = 0;
            }
        }
        
        // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
        // 返回值同时存在tagset和Rset
        if (!tagSet.isEmpty() && rSet.GetRowCount() > 0)
        {
            if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
            {
                tagSet.SetValue("verifyCode", verifyCode);
                tagSet.SetValue("contactid", contactid);
            }
            
            retVector.add(tagSet);
            retVector.add(rSet);
            rtData.setReturnObject(retVector);
        }
        else if (!tagSet.isEmpty())
        {
            if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
            {
                tagSet.SetValue("verifyCode", verifyCode);
                tagSet.SetValue("contactid", contactid);
            }
            
            // 返回值只有Tagset
            rtData.setReturnObject(tagSet);
            
        }
        else if (rSet.GetRowCount() > 0)
        {
            // 返回值只有Rset
            rtData.setReturnObject(rSet);
        }
        // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
        
        return rtData;
        
    }
}
