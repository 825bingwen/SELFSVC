/*
 * 文件名：SelfSvcCallTest.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：仅供测试使用的接口调用实现类
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增
 * 
 */
package com.gmcc.boss.selfsvc.call;

import com.customize.sd.selfsvc.po.NcodePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.quickpublish.model.MultiProdCommitPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import org.apache.axiom.om.OMElement;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 仅供测试使用
 * 
 * @author g00140516
 * 
 */
public class SelfSvcCallTest implements SelfSvcCall {
	private IntMsgUtil intMsgUtil;

	private SocketUtil socketUtil;
	
	 /**
     * 查询家庭网成员
     * 
     * @param map 参数
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark create kWX211786 May 23, 2014 版本号 需求/BUG编号：OR_huawei_201404_1115_山东_家庭网成员添加功能
    */
    public ReturnWrap queryFamilyMemService(Map<String,String> map)
    {
        try
        {
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String menuid = (String)map.get("curMenuId");
            
            // 手机号码
            eBody.addElement("telnum").addText(telnumber);
            
            docXML = intMsgUtil.createMsg(doc, "cli_qry_familymeminfo_sd", menuid, "", "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
           // logger.error("业务统一查询接口失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * 添加家庭网成员
     * 
     * @param map 参数
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark create kWX211786 May 23, 2014 版本号 需求/BUG编号：OR_huawei_201404_1115_山东_家庭网成员添加功能
    */
    public ReturnWrap addFamilyMem(Map<String, String> map)
    {
        try
        {
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String menuid = (String)map.get("curMenuId");
            String servNumber = (String)map.get("servNumber");
            String memTelnum = (String)map.get("memTelnum");
            String shortNum = (String)map.get("shortNum");
            
            // 手机号码
            eBody.addElement("servNumber").addText(servNumber);
            eBody.addElement("memTelnum").addText(memTelnum);
            eBody.addElement("shortNum").addText(shortNum);
            //是否订购成员必选优惠 1：是 其他：否
            eBody.addElement("addMustPriv").addText("0");
            //主体产品编码
            eBody.addElement("prodId").addText("");
            //优惠编码
            eBody.addElement("privId").addText("");
            
      
            
            docXML = intMsgUtil.createMsg(doc, "cli_add_familymem_sd", menuid, "", "1", servNumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
           // logger.error("业务统一查询接口失败：", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }

	/**
     * 服务密码验证
     * 
     * @param map
     * @return  ReturnWrap
     * @remark create by hWX5316476 2014-05-13 OR_huawei_201405_235  自助终端接入EBUS一阶段_获取用户信息
     */
    public ReturnWrap checkUserPassword(Map<String, String> map)
    {
    	return null;
    }
    
    /**
     * 获取用户信息
     * 
     * @param map
     * @return
     * @remark create by hWX5316476 2014-05-13 OR_huawei_201405_235  自助终端接入EBUS一阶段_获取用户信息
     */
    public ReturnWrap getUserInfoHub(Map<String, String> map)
    {
    	return null;
    }
    
	/**
	 * 使用手机号码、服务密码进行身份认证
	 */
	public ReturnWrap getUserInfoWithPwd(Map map) {
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+ "<menuid></menuid><process_code>cli_qry_userinfo</process_code><verify_code></verify_code>"
				+ "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
				+ "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
				+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
				+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><subname>高群</subname>"
				+ "<region>951</region><regionname></regionname><productid>pp.gt.ygtch.634</productid>"
				+ "<productname></productname><productgroup></productgroup><viptype></viptype>"
				+ "<productname>全球通</productname><productgroup>BrandMzone</productgroup><viptype>VC0000</viptype>"
				+ "<logintype></logintype><feeflag></feeflag><question></question><answer></answer><needcheckstr>"
				+ "</needcheckstr><contactid></contactid><status>US22</status><nettype></nettype></message_content>]]></message_body></message_response>";

		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rw;
	}

	/**
	 * 无密码登录
	 */
	public ReturnWrap getUserInfo(Map map) {
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+ "<menuid></menuid><process_code>cli_qry_userinfo</process_code><verify_code>verifyCode</verify_code>"
				+ "<unicontact>unicontact</unicontact><resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
				+ "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
				+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
				+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><subname>高群</subname>"
				+ "<region>951</region><regionname></regionname><productid>pp.gt.ygtch.634</productid>"
				+ "<productname></productname><productgroup></productgroup><viptype></viptype>"
				+ "<productname>全球通</productname><productgroup>BrandMzone</productgroup><viptype>VC0000</viptype>"
				+ "<logintype></logintype><feeflag></feeflag><question></question><answer></answer><needcheckstr>"
				+ "</needcheckstr><status>US22</status><nettype></nettype><subage>2011-05-08</subage><subscore>600</subscore></message_content>]]></message_body></message_response>";

		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rw;
	}

	/**
	 * 向用户发送随机密码短信
	 * 
	 * @param map
	 * @return
	 */
	public ReturnWrap sendSMS(Map map) {
		ReturnWrap rw = new ReturnWrap();
		rw.setStatus(SSReturnCode.SUCCESS);

		return rw;
	}

	public ReturnWrap queryMonthDeduct(Map map) {
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>qryBill</menuid><process_code>cli_qry_yckf</process_code><verify_code></verify_code><resp_time>20100921004855</resp_time><sequence><req_seq>97</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><sum_funcfee>300</sum_funcfee><sum_packagefee>2600</sum_packagefee><sum_basefee>0</sum_basefee><feeitem><type>2</type><name>1元本地亲情号码市话主叫0.05元/分钟</name><fee>100</fee><time>09.12</time></feeitem><feeitem><type>2</type><name>20元包200分钟本地国内长途</name><fee>2000</fee><time>09.04</time></feeitem><feeitem><type>2</type><name>GPRS套餐 -5元包国内30M，超出0.01元/K</name><fee>500</fee><time>09.04</time></feeitem><feeitem><type>1</type><name>移动全时通费</name><fee>300</fee><time>09.02</time></feeitem></message_content>]]></message_body></message_response>";

		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Vector vector = null;
		if (rw != null) {
			vector = (Vector) rw.getReturnObject();
		}

		Vector retVector = new Vector();
		if (vector != null && vector.size() == 2) {
			CTagSet tagset = (CTagSet) vector.get(0);
			CRSet crset = (CRSet) vector.get(1);

			List listBaseFee = null;// //套餐信息费
			List listFuncFee = null;// //功能费
			List listSumPackage = null;// 包月费

			if ((crset != null) && (crset.GetRowCount() > 0)) {
				// 套餐信息费
				if (!"0".equals(tagset.GetValue("sum_basefee"))) {
					listBaseFee = getMonthFeeList(crset, "3");
				}
				// 功能费
				if (!"0".equals(tagset.GetValue("sum_funcfee"))) {
					listFuncFee = getMonthFeeList(crset, "1");
				}
				// 包月费
				if (!"0".equals(tagset.GetValue("sum_packagefee"))) {
					listSumPackage = getMonthFeeList(crset, "2");
				}
			}
			retVector.add(listBaseFee);
			retVector.add(listFuncFee);
			retVector.add(listSumPackage);
			rw.setReturnObject(retVector);
		}

		return rw;
	}

	/**
	 * 功能费、包月费、套餐信息费 <功能详细描述>
	 * 
	 * @param crset
	 * @param typeStr
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<List> getMonthFeeList(CRSet crset, String typeStr) {

		// 费用list
		List<List> feeList = new ArrayList<List>();

		for (int i = 0; i < crset.GetRowCount(); i++) {
			String type = crset.GetValue(i, 0) == null ? "" : crset.GetValue(i,
					0);
			if (typeStr.equals(type)) {
				List<String> list = new ArrayList<String>();

				list.add(crset.GetValue(i, 1));
				list.add(CommonUtil.fenToYuan(crset.GetValue(i, 2)));
				list.add(CommonUtil.formatDate(crset.GetValue(i, 3), "MM.dd",
						"MM月dd日"));

				feeList.add(list);
			}
		}
		return feeList;
	}

	/**
	 * 本机品牌资费及已开通功能
	 */
	public ReturnWrap qryFavourable(Map map) {
		// 查询类型
		String queryType = (String) map.get("queryType");

		// 正确
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+ "<menuid>qryFavourable</menuid><process_code>cli_qry_zfinfo</process_code>"
				+ "<verify_code></verify_code><resp_time>20100921090605</resp_time><sequence><req_seq>23</req_seq>"
				+ "<operation_seq></operation_seq></sequence><retinfo>"
				+ "<rettype>0</rettype><retcode>100</retcode><retmsg>"
				+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
				+ "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
				+ "<message_content>"
				+ "<info><type>ShowByPrivRate</type><name>神州行09版思乡卡品牌优惠[必]</name><desp>0月租，来电显示费0.1元/天。通话费:市话主叫0.25元/分钟，本地被叫免费,长途费0.25元/分钟，漫游费主叫0.6元/分钟、被叫0.4元/分钟</desp><start_time>20090401000000</start_time><end_time></end_time></info>"
				+ "<info><type>ShowByPrivRate</type><name>思乡热线</name><desp>1元/月，指定一个国内亲情城市，在本地拨打该城市电话，长途通话费每分钟0.20元，变更亲情城市收取1元，变更亲情城市无需设置生效类型，均为次月生效</desp><start_time>20090401000000</start_time><end_time></end_time></info>"
				+ "<info><type>ShowByProdRate</type><name>来电显示</name><desp>主叫号码显示</desp><start_time>20060205000000</start_time><end_time></end_time></info><info><type>ShowByProdRate</type><name>国内漫游功能</name><desp>开通国内漫游功能可以在全国范围内使用</desp><start_time>20060205000000</start_time><end_time></end_time></info>"
				+ "<info><type>ShowByProdRate</type><name>09版思乡卡必选包[必]</name><desp>必选包</desp><start_time>20090401000000</start_time><end_time></end_time></info><info><type>ShowByProdRate</type><name>飞信功能</name><desp>基本业务免功能费</desp><start_time>20071123145754</start_time><end_time></end_time></info><info><type>ShowByProdRate</type><name>5元包25M省内GPRS流量</name><desp>流量包</desp><start_time>20090501000000</start_time><end_time></end_time></info>"
				+ "</message_content>]]></message_body></message_response>";
		// 错误
		// String response = "<?xml version=\"1.0\" encoding=\"GBK\"
		// ?><message_response><message_head version=\"1.0\">" +
		// "<menuid>qryFavourable</menuid><process_code>cli_qry_zfinfo</process_code>"
		// +
		// "<verify_code></verify_code>" +
		// "<resp_time>20110620144828</resp_time><" +
		// "sequence><req_seq>14</req_seq><operation_seq></operation_seq>" +
		// "</sequence><retinfo><rettype>0</rettype><retcode>999</retcode>" +
		// "<retmsg><![CDATA[BUSINESSID:QrySubsFeeInfo_Atsv,中间件调用失败，EUniCall抛出异常,ErrorID
		// -1 ErrorMsg EXCEPTION 'ETuxedo': Destination service not found:
		// IntCmd:CscGetSetInfo, IntSubCmd:, IntID:bsacAtsv,
		// Region:711]]></retmsg></retinfo></message_head>" +
		// "</message_response>";

		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rw;

	}

	/**
	 * PUK码查询
	 * 
	 * @return
	 */
	public ReturnWrap queryPUK(Map map) {
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>qryPukQry</menuid>"
				+ "<process_code>cli_qry_pukcode</process_code><verify_code></verify_code>"
				+ "<resp_time>20100921090638</resp_time><sequence><req_seq>25</req_seq>"
				+ "<operation_seq></operation_seq></sequence>"
				+ "<retinfo><rettype>0</rettype><retcode>100</retcode>"
				+ "<retmsg><![CDATA[Processing the request succeeded!]]><"
				+ "/retmsg></retinfo></message_head><message_body>"
				+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
				+ "<message_content>"
				+ "<pin1>1234</pin1>"
				+ "<pin2>0167</pin2>"
				+ "<puk1>07577572</puk1>"
				+ "<puk2>19639147</puk2>"
				+ "</message_content>]]></message_body></message_response>";
		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rw;
	}

	public void setIntMsgUtil(IntMsgUtil intMsgUtil) {
		this.intMsgUtil = intMsgUtil;
	}

	/**
	 * 本机状态查询
	 */
	public ReturnWrap queryCurrentStatus(Map map) {
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>"
				+ "<message_response><message_head version=\"1.0\">"
				+ "<menuid>qryService</menuid>"
				+ "<process_code>cli_qry_userstate</process_code>"
				+ "<verify_code></verify_code>"
				+ "<resp_time>20100921002201</resp_time>"
				+ "<sequence>"
				+ "<req_seq>38</req_seq><operation_seq></operation_seq>"
				+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
				+ "<retmsg><![CDATA[Processing the request succeeded!]]>"
				+ "</retmsg></retinfo>" + "</message_head>" + "<message_body>"
				+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
				+ "<message_content>" + "<state>正使用</state>"
				+ "</message_content>]]>"
				+ "</message_body></message_response>";
		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rw;
	}

	/**
	 * 号码归属地查询
	 */
	public ReturnWrap queryUserRegionReq(Map map) {

		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+ "<menuid>qryBillItem</menuid><process_code>cli_qry_bill</process_code><verify_code></verify_code>"
				+ "<resp_time>20100921004740</resp_time><sequence><req_seq>89</req_seq><operation_seq></operation_seq>"
				+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
				+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
				+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
				+ "<message_content>"
				+ "<provname>山东</provname>"
				+ "<cityname>济南</cityname>"
				+ "<region>531</region>"
				+ "</message_content>]]></message_body></message_response>";

		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rw;
	}

	/**
	 * 详单查询
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ReturnWrap queryCDRList(Map map) {
		try {
			String cdrType = (String) map.get("CDRType");

			String response = "";

			String province = (String) PublicCache.getInstance().getCachedData(
					Constants.PROVINCE_ID);
			if (Constants.PROOPERORGID_NX.equals(province)) {
				if ("1".equals(cdrType)) {
					response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
							+ "<message_head version=\"1.0\"><menuid></menuid><process_code>cli_qry_cdr</process_code>"
							+ "<verify_code></verify_code><resp_time>20110619115611</resp_time><sequence>"
							+ "<req_seq>28</req_seq><operation_seq></operation_seq></sequence><retinfo>"
							+ "<rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
							+ "</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
							+ "<message_content><cust_name></cust_name><product_name></product_name><product_info></product_info>"
							+ "<createdate></createdate><total_fee></total_fee><telnum>13895600783</telnum><rowcount>63</rowcount>"
							+ "<billitem><bill>gprs~559941~2011-06-18 00:36:28~9425336~信令流量~0.00</bill></billitem>"
							+ "<billitem><bill>gprs~0~2011-06-18 01:36:29~0~信令流量~0.00</bill></billitem>"
							+
							// "<billitem><bill>被叫~18795203778~2011-06-18
							// 10:28:42~111~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>主叫~18795203778~2011-06-18
							// 10:45:31~107~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>被叫~18795203778~2011-06-18
							// 11:08:57~15~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>主叫~13895389498~2011-06-18
							// 11:34:23~42~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>被叫~13895389498~2011-06-18
							// 11:37:18~16~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>被叫~18795203778~2011-06-18
							// 14:23:25~80~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>被叫~13909590065~2011-06-18
							// 17:03:16~45~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>主叫~15109577632~2011-06-18
							// 17:05:17~45~宁夏银川~0.22</bill></billitem>" +
							// "<billitem><bill>主叫~13629580286~2011-06-18
							// 17:06:29~212~宁夏银川~0.88</bill></billitem>" +
							// "<billitem><bill>被叫~13895176008~2011-06-18
							// 17:10:14~63~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>主叫~15109577632~2011-06-18
							// 17:11:38~65~宁夏银川~0.44</bill></billitem>" +
							// "<billitem><bill>被叫~13909590065~2011-06-18
							// 17:13:28~58~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>被叫~13895176008~2011-06-18
							// 17:17:01~69~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>被叫~15825316020~2011-06-18
							// 17:24:17~69~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>主叫~13909590065~2011-06-18
							// 17:30:47~42~宁夏银川~0.22</bill></billitem>" +
							"<billitem><bill>主叫~13909590065~2011-06-18 17:34:39~59~宁夏银川~0.22</bill></billitem>"
							+ "<billitem><bill>短信主叫~13895176008~2011-06-18 17:45:44~0~宁夏银川~0.10</bill></billitem>"
							+ "<billitem><bill>主叫~13895389498~2011-06-18 17:47:38~144~宁夏银川~0.00</bill></billitem>"
							+
							// "<billitem><bill>短信主叫~13895389498~2011-06-18
							// 18:03:40~0~宁夏银川~0.10</bill></billitem>" +
							// "<billitem><bill>主叫~13895675851~2011-06-18
							// 20:44:49~67~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~3057~2011-06-18
							// 21:06:52~66582~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~716~2011-06-18
							// 21:07:15~1543~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>被叫~13895389498~2011-06-18
							// 21:31:17~32~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>主叫~18795203778~2011-06-18
							// 21:33:04~10~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>被叫~13895389498~2011-06-18
							// 21:35:02~28~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~4421~2011-06-18
							// 22:06:53~49430~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>主叫~18795203778~2011-06-18
							// 22:41:08~149~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~45615~2011-06-19
							// 00:02:36~87126~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~2802~2011-06-19
							// 01:06:52~22614~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~779~2011-06-19
							// 01:07:12~801~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~2463~2011-06-19
							// 02:06:52~19513~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~716~2011-06-19
							// 02:07:11~773~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~2853~2011-06-19
							// 03:06:52~20647~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~716~2011-06-19
							// 03:07:12~771~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~2690~2011-06-19
							// 04:06:52~28685~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~716~2011-06-19
							// 04:07:14~768~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~2873~2011-06-19
							// 05:06:52~20638~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~716~2011-06-19
							// 05:07:12~770~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~2088~2011-06-19
							// 06:06:52~13654~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~768~2011-06-19
							// 06:07:10~793~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~2912~2011-06-19
							// 07:06:52~66413~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~716~2011-06-19
							// 07:07:17~1534~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~24785~2011-06-19
							// 07:45:20~930554~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~1257~2011-06-19
							// 07:50:46~2945~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~1950~2011-06-19
							// 07:52:56~4609~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~1048~2011-06-19
							// 08:00:19~2550~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~8370~2011-06-19
							// 08:05:34~107504~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~938~2011-06-19
							// 08:09:30~6702~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~8953~2011-06-19
							// 08:10:04~27995~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~938~2011-06-19
							// 08:14:21~5710~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~3978~2011-06-19
							// 09:06:52~101745~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~830~2011-06-19
							// 09:07:17~1856~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>被叫~053188730192~2011-06-19
							// 09:27:17~276~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>梦网下行~10658483~2011-06-19
							// 09:51:04~0~929868~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~3677~2011-06-19
							// 10:06:52~78051~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~716~2011-06-19
							// 10:07:15~1553~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>行业网关下行~13895600783~2011-06-19
							// 10:08:56~0~600032/95588~0.00</bill></billitem>" +
							// "<billitem><bill>行业网关下行~13895600783~2011-06-19
							// 10:08:56~0~600032/95588~0.00</bill></billitem>" +
							// "<billitem><bill>被叫~053188730192~2011-06-19
							// 10:34:31~150~宁夏银川~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~4613~2011-06-19
							// 11:06:52~125151~信令流量~0.00</bill></billitem>" +
							// "<billitem><bill>gprs~716~2011-06-19
							// 11:07:21~1543~信令流量~0.00</bill></billitem>" +
							"</message_content>]]></message_body></message_response>";
				} else if ("3".equals(cdrType)) {
					response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
							+ "<message_head version=\"1.0\"><menuid></menuid><process_code>cli_qry_cdr</process_code>"
							+ "<verify_code></verify_code><resp_time>20110620103246</resp_time><sequence>"
							+ "<req_seq>28</req_seq><operation_seq></operation_seq></sequence><retinfo>"
							+ "<rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
							+ "</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
							+ "<message_content><cust_name></cust_name><product_name></product_name><product_info></product_info>"
							+ "<createdate></createdate><total_fee></total_fee><telnum>13895600783</telnum><rowcount>114</rowcount>"
							+ "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-10 09:57:23~0.00~~其他~0.00~</bill></billitem>"
							+ "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-10 10:03:52~0.00~~其他~0.00~</bill></billitem>"
							+ "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-10 14:59:58~0.00~~其他~0.00~</bill></billitem>"
							+
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-10
							// 15:57:53~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-10
							// 16:04:18~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929835|10086~~2011-06-11
							// 10:06:03~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-11
							// 16:27:09~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-11
							// 18:45:12~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-11
							// 19:00:03~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-11
							// 19:05:28~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929835|10086~~2011-06-12
							// 10:41:29~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-13
							// 08:31:43~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-13
							// 09:57:41~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-13
							// 10:04:10~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-13
							// 11:22:03~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-13
							// 11:22:05~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-13
							// 11:34:50~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-13
							// 11:41:59~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-13
							// 11:41:59~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-13
							// 11:43:01~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-13
							// 11:45:03~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-13
							// 12:09:25~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-13
							// 12:09:25~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-13
							// 15:57:23~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-13
							// 16:03:44~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-13
							// 16:44:38~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929839|10658139~~2011-06-14
							// 09:56:28~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-14
							// 09:57:31~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-14
							// 10:04:02~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-14
							// 10:48:35~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-14
							// 14:36:14~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-14
							// 15:50:15~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-14
							// 15:57:20~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-14
							// 16:03:39~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-15
							// 09:57:54~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-15
							// 10:04:23~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-15
							// 15:12:58~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-15
							// 15:57:06~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-15
							// 16:04:25~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-16
							// 08:39:30~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 09:57:47~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 10:04:09~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 15:57:10~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 15:57:12~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 15:57:14~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 15:57:15~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 15:57:16~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 15:57:17~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 15:57:17~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 15:57:18~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 15:57:19~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 15:57:20~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 16:03:36~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 16:03:38~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 16:03:40~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 16:03:40~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 16:03:41~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 16:03:42~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 16:03:43~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 16:03:43~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 16:03:46~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-16
							// 16:03:47~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-16
							// 19:18:13~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-16
							// 20:32:13~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-17
							// 09:36:54~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 09:57:50~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 09:57:55~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 09:58:02~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 09:58:04~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 09:58:07~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 09:58:11~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 09:58:12~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 09:58:14~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 09:58:19~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 09:58:23~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 10:03:48~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 10:03:54~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 10:03:59~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 10:04:01~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 10:04:05~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 10:04:08~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 10:04:09~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 10:04:11~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 10:04:15~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 10:04:20~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-17
							// 14:43:19~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 15:57:03~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 15:57:10~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 15:57:16~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 15:57:18~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 15:57:21~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 15:57:24~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 15:57:26~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 15:57:28~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 15:57:31~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 15:57:38~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 16:04:09~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 16:04:15~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 16:04:21~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 16:04:24~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 16:04:27~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 16:04:31~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 16:04:32~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 16:04:34~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 16:04:38~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 16:04:43~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-17
							// 18:01:18~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-17
							// 18:32:09~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-17
							// 19:16:45~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 20:36:45~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 20:46:47~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 20:47:48~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929845|10658470~~2011-06-17
							// 20:47:50~0.00~~其他~0.00~</bill></billitem>" +
							// "<billitem><bill>梦网短信[梦网下行]~929868|10658483~~2011-06-19
							// 09:51:04~0.00~~其他~0.00~</bill></billitem>" +
							"</message_content>]]></message_body></message_response>";
				} else if ("4".equals(cdrType)) {
					response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
							+ "<menuid></menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code>"
							+ "<resp_time>20110620103433</resp_time><sequence><req_seq>28</req_seq><operation_seq>"
							+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
							+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
							+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><cust_name></cust_name>"
							+ "<product_name></product_name><product_info></product_info><createdate></createdate><total_fee>"
							+ "</total_fee><telnum>13895600783</telnum><rowcount>58</rowcount>"
							+ "<billitem><bill>201106CMNET[信令流量]~2011-06-18 00:36:28~48429~9532.40~327.97~9204.43~0.00~0.19~546.82~8985.58</bill></billitem>"
							+ "<billitem><bill>201106CMNET[信令流量]~2011-06-18 01:36:29~24057~0.00~0.00~0.00~0.00~0.00~0.00~0.00</bill></billitem>"
							+
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-18
							// 21:06:52~12~65.02~0.00~65.02~0.00~5.42~2.99~62.04</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-18
							// 21:07:15~4~1.51~0.00~1.51~0.00~0.38~0.70~0.81</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-18
							// 22:06:53~24~48.27~0.00~48.27~0.00~2.01~4.32~43.95</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 00:02:36~300~170.17~85.09~85.08~0.00~0.28~44.55~125.62</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 01:06:52~9~44.17~22.09~22.08~0.00~2.45~2.74~41.43</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 01:07:12~5~1.57~0.79~0.78~0.00~0.16~0.76~0.80</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 02:06:52~8~38.11~19.05~19.06~0.00~2.38~2.41~35.71</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 02:07:11~5~1.51~0.76~0.75~0.00~0.15~0.70~0.81</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 03:06:52~11~40.33~20.17~20.16~0.00~1.83~2.79~37.54</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 03:07:12~5~1.51~0.76~0.75~0.00~0.15~0.70~0.81</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 04:06:52~11~56.03~28.02~28.01~0.00~2.55~2.63~53.40</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 04:07:14~5~1.50~0.75~0.75~0.00~0.15~0.70~0.80</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 05:06:52~11~40.31~20.16~20.15~0.00~1.83~2.81~37.50</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 05:07:12~5~1.50~0.75~0.75~0.00~0.15~0.70~0.80</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 06:06:52~8~26.67~13.34~13.33~0.00~1.67~2.04~24.63</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 06:07:10~5~1.55~0.78~0.77~0.00~0.15~0.75~0.80</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 07:06:52~13~64.86~0.00~64.86~0.00~4.99~2.84~62.01</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 07:07:17~11~1.50~0.00~1.50~0.00~0.14~0.70~0.80</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 07:45:20~211~908.74~0.00~908.74~0.00~4.31~24.20~884.54</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 07:50:46~40~2.88~0.00~2.88~0.00~0.07~1.23~1.65</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 07:52:56~352~4.50~0.00~4.50~0.00~0.01~1.90~2.60</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 08:00:19~50~2.49~0.00~2.49~0.00~0.05~1.02~1.47</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 08:05:34~107~104.98~0.00~104.98~0.00~0.98~8.17~96.81</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 08:09:30~28~6.54~0.00~6.54~0.00~0.23~0.92~5.63</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 08:10:04~235~27.34~0.00~27.34~0.00~0.12~8.74~18.60</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 08:14:21~22~5.58~0.00~5.58~0.00~0.25~0.92~4.66</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 09:06:52~12~99.36~0.00~99.36~0.00~8.28~3.88~95.48</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 09:07:17~6~1.81~0.00~1.81~0.00~0.30~0.81~1.00</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 10:06:52~10~76.22~0.00~76.22~0.00~7.62~3.59~72.63</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 10:07:15~5~1.52~0.00~1.52~0.00~0.30~0.70~0.82</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 11:06:52~15~122.22~0.00~122.22~0.00~8.15~4.50~117.71</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 11:07:21~4~1.51~0.00~1.51~0.00~0.38~0.70~0.81</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 12:06:52~11~107.48~0.00~107.48~0.00~9.77~4.26~103.22</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 12:07:17~5~1.49~0.00~1.49~0.00~0.30~0.70~0.79</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 13:06:52~11~101.30~0.00~101.30~0.00~9.21~4.11~97.19</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 13:07:17~5~1.50~0.00~1.50~0.00~0.30~0.70~0.80</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 14:06:52~16~85.88~0.00~85.88~0.00~5.37~4.11~81.77</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 14:07:21~5~1.51~0.00~1.51~0.00~0.30~0.70~0.81</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 15:06:52~7~39.63~0.00~39.63~0.00~5.66~2.57~37.07</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 15:07:09~5~1.55~0.00~1.55~0.00~0.31~0.75~0.80</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 15:20:22~217~20.36~0.00~20.36~0.00~0.09~6.20~14.15</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 15:25:37~59~5.10~0.00~5.10~0.00~0.09~0.98~4.12</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 16:06:52~9~55.04~0.00~55.04~0.00~6.12~2.70~52.34</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 16:07:12~5~1.50~0.00~1.50~0.00~0.30~0.70~0.80</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 17:06:52~7~39.05~0.00~39.05~0.00~5.58~2.50~36.55</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 17:07:09~5~1.50~0.00~1.50~0.00~0.30~0.70~0.80</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 18:06:52~10~75.95~0.00~75.95~0.00~7.60~3.00~72.95</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 18:07:13~5~1.50~0.00~1.50~0.00~0.30~0.70~0.80</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 20:06:52~7~34.92~0.00~34.92~0.00~4.99~2.54~32.38</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 20:07:10~4~1.50~0.00~1.50~0.00~0.38~0.70~0.80</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 21:06:52~10~50.99~0.00~50.99~0.00~5.10~2.55~48.44</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 21:07:13~5~1.50~0.00~1.50~0.00~0.30~0.70~0.80</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 22:06:52~11~37.26~0.00~37.26~0.00~3.39~2.54~34.72</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 22:07:14~7~1.50~0.00~1.50~0.00~0.21~0.70~0.80</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 22:50:56~119~6.89~0.00~6.89~0.00~0.06~0.98~5.91</bill></billitem>"
							// +
							// "<billitem><bill>201106CMNET[信令流量]~2011-06-19
							// 22:53:10~235~30.08~0.00~30.08~0.00~0.13~8.91~21.17</bill></billitem>"
							// +
							"</message_content>]]></message_body></message_response>";
				}
			} else if (Constants.PROOPERORGID_SD.equals(province)) {
				if ("1".equals(cdrType)) {
					response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
							+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code>"
							+ "<resp_time>20100921001546</resp_time><sequence><req_seq>28</req_seq><operation_seq>"
							+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
							+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
							+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><cust_name>高群</cust_name>"
							+ "<product_name>动感地带</product_name><product_info>时尚 好玩 探索</product_info>"
							+ "<createdate>20080822112345</createdate><total_fee></total_fee>"
							+ "<billitem><bill>15864500526~主叫~本地~2011-06-01 01:42:16~24505~1.0~2.0~3.0~6.0</bill></billitem>"
							+ "<billitem><bill>15864500526~被叫~本地~2011-06-01 01:42:16~24505~1.0~2.0~3.0~6.0</bill></billitem>"
							+ "<billitem><bill>15864500526~被叫~本地~2011-06-01 01:42:16~24505~1.0~2.0~3.0~6.0</bill></billitem>"
							+ "</message_content>]]></message_body></message_response>";
				} else if ("3".equals(cdrType)) {
					response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
							+ "<menuid>qryImsgMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code>"
							+ "<resp_time>20100921001709</resp_time><sequence><req_seq>29</req_seq><operation_seq></operation_seq>"
							+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
							+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
							+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><cust_name>高群</cust_name>"
							+ "<product_name>动感地带</product_name><product_info>时尚 好玩 探索</product_info>"
							+ "<createdate>20080822112345</createdate><total_fee></total_fee>"
							+ "<billitem><bill>中国移动-飞信~0.00~0.00~12520~915942~09-19 16:54:56~+MCHAT</bill></billitem>"
							+ "<billitem><bill>中国移动-飞信~0.00~0.00~12520~915942~09-19 16:54:56~+MCHAT</bill></billitem>"
							+
							// "<billitem><bill>中国移动-飞信~0.00~0.00~12520~915942~09-04
							// 20:32:48~+MCHAT</bill></billitem>" +
							// "<billitem><bill>山东移动手机邮箱(个人版)~0.00~0.00~10658139~915910~09-14
							// 17:46:46~10000</bill></billitem>" +
							// "<billitem><bill>山东移动手机邮箱(个人版)~0.00~0.00~10658139~915910~09-14
							// 17:46:46~10000</bill></billitem>" +
							// "<billitem><bill>中国移动-飞信~0.00~0.00~12520~915942~09-19
							// 16:54:56~+MCHAT</bill></billitem>" +
							// "<billitem><bill>中国移动-飞信~0.00~0.00~12520~915942~09-19
							// 16:54:56~+MCHAT</bill></billitem>" +
							"</message_content>]]></message_body></message_response>";
				} else if ("2".equals(cdrType)) {
					response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
							+ "<menuid>qrySmsMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code>"
							+ "<resp_time>20100921001724</resp_time><sequence><req_seq>30</req_seq><operation_seq>"
							+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
							+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
							+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><cust_name>高群</cust_name>"
							+ "<product_name>动感地带</product_name><product_info>时尚 好玩 探索</product_info>"
							+ "<createdate>20080822112345</createdate><total_fee></total_fee>"
							+ "<billitem><bill>移动网内点对点短信~15864500526~13645319981~09-01 06:59:57~20~0.00</bill></billitem>"
							+ "<billitem><bill>移动网内点对点短信~15864500526~15920677652~09-01 18:27:02~6~0.00</bill></billitem>"
							+
							// "<billitem><bill>移动网内点对点短信~15864500526~15920677652~09-01
							// 20:16:35~76~0.00</bill></billitem>"
							// +
							// "<billitem><bill>移动网内点对点短信~15864500526~15920677652~09-01
							// 20:31:20~60~0.00</bill></billitem>"
							// +
							// "<billitem><bill>移动网内点对点短信~15864500526~15920677652~09-01
							// 20:37:59~30~0.00</bill></billitem>"
							// +
							// "<billitem><bill>移动网内点对点短信~15864500526~13791145921~09-02
							// 14:25:47~60~0.00</bill></billitem>"
							// +
							// "<billitem><bill>移动网内点对点短信~15864500526~13791145921~09-02
							// 14:29:02~36~0.00</bill></billitem>"
							// +
							// "<billitem><bill>移动网内点对点短信~15864500526~13791145921~09-03
							// 11:45:38~16~0.00</bill></billitem>"
							// +
							"</message_content>]]></message_body></message_response>";
				} else if ("4".equals(cdrType)) {
					response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
							+ "<menuid>qryAllMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code>"
							+ "<resp_time>20100921010640</resp_time><sequence><req_seq>127</req_seq><operation_seq></operation_seq>"
							+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
							+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
							+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><cust_name>高群</cust_name>"
							+ "<product_name>动感地带</product_name><product_info>时尚 好玩 探索</product_info>"
							+ "<createdate>20080822112345</createdate><total_fee></total_fee>"
							+ "<billitem><bill>201106CMNET[信令流量]~2011-06-01 01:42:16~10~47~23~23~0~23~23~0</bill></billitem>"
							+ "<billitem><bill>201106CMNET[信令流量]~2011-06-01 01:42:32~4~1~0~0~0~23~23~0</bill></billitem>"
							+ "</message_content>]]></message_body></message_response>";
				} else if ("5".equals(cdrType)) {
					response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
							+ "<menuid>qryWlanMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code>"
							+ "<resp_time>20100921055850</resp_time><sequence><req_seq>21</req_seq><operation_seq>"
							+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
							+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
							+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><cust_name>赵鹏</cust_name>"
							+ "<product_name>神州行</product_name><product_info>自由 实惠 便捷</product_info>"
							+ "<createdate>20100809145848</createdate><total_fee></total_fee>"
							+ "<billitem><bill>本地~WEB~09-21 05:20:31~243~5185859~640992~0.25</bill></billitem>"
							+ "</message_content>]]></message_body></message_response>";
				}
			} else if (Constants.PROOPERORGID_HUB.equals(province)) {
				if ("1".equals(cdrType))// 通话详单
				{
					response = "<message_response><message_head version=\"1.0\"><menuid>qryAllMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121105626</resp_time><sequence><req_seq>236</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><cust_name>用2700008515650</cust_name><product_name>全球通</product_name><product_info></product_info><createdate></createdate><total_fee></total_fee><billitem><bill>20111001 08:10:29~231~被叫~13986160101~0.00~0.00~0.00~~~~0.00~~71260A2A</bill></billitem><billitem><bill>20111001 08:18:49~31~主叫~13986160101~0.00~0.00~0.00~~~~0.00~~71260A2A</bill></billitem><billitem><bill>20111001 08:19:56~39~被叫~13986160101~0.00~0.00~0.00~~~~0.00~~71260A2A</bill></billitem></message_content>]]></message_body></message_response>";
				} else if ("2".equals(cdrType))// 短信清单
				{
					response = "<message_response><message_head version=\"1.0\"><menuid>qrySmsMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102321</resp_time><sequence><req_seq>206</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><cust_name>用2700008515650</cust_name><product_name>全球通</product_name><product_info></product_info><createdate></createdate><total_fee></total_fee><billitem><bill>发送~13797060656~15927072614~10-01 13:46:12~10~         0.10</bill></billitem><billitem><bill>发送~13797060656~15050258478~10-01 13:53:20~76~         0.10</bill></billitem><billitem><bill>发送~13797060656~12520569313493~10-05 10:39:36~46~         0.10</bill></billitem></message_content>]]></message_body></message_response>";
				} else if ("3".equals(cdrType))// 梦网清单
				{
					response = "<message_response><message_head version=\"1.0\"><menuid>qryImsgMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121161920</resp_time><sequence><req_seq>60</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><cust_name>用2700008515650</cust_name><product_name>全球通</product_name><product_info></product_info><createdate></createdate><total_fee></total_fee><billitem><bill>SP服务提供商~~~13507101860~13507101860~10-01 00:14:59~         0.00~         5.00</bill></billitem><billitem><bill>SP服务提供商~~~13507101860~13507101860~10-01 00:14:59~         0.00~        10.00</bill></billitem><billitem><bill>SP服务提供商~901449~10666226~-XXVL~13797060656~10-01 14:33:07~         0.00~         0.00</bill></billitem></message_content>]]></message_body></message_response>";
				} else if ("4".equals(cdrType))// GPRS清单
				{
					response = "<message_response><message_head version=\"1.0\"><menuid>qryGprsMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102333</resp_time><sequence><req_seq>207</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><cust_name>用2700008515650</cust_name><product_name>全球通</product_name><product_info></product_info><createdate></createdate><total_fee></total_fee><billitem><bill>CMNET~闲时~10-01 00:18:42~554~736~1024~非内容计费流量~0~省内~0~1024</bill></billitem><billitem><bill>CMNET~闲时~10-01 01:49:06~40~55~0~非内容计费流量~0~省内~0~0</bill></billitem><billitem><bill>CMNET~闲时~10-01 03:19:20~778~864~2048~非内容计费流量~0~省内~0~2048</bill></billitem></message_content>]]></message_body></message_response>";
				} else if ("5".equals(cdrType))// WLAN清单
				{
					response = "<message_response><message_head version=\"1.0\"><menuid>qryWlanMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102341</resp_time><sequence><req_seq>208</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><cust_name>用2700008515650</cust_name><product_name>全球通</product_name><product_info></product_info><createdate></createdate><total_fee></total_fee><billitem><bill>本地~WEB~10-09 14:37:21~4325~         3.65</bill></billitem><billitem><bill>本地~WEB~10-09 16:13:53~3065~         2.60</bill></billitem><billitem><bill>本地~WEB~10-17 09:11:53~3391~         2.85</bill></billitem><billitem><bill>本地~WEB~10-17 14:12:53~10775~         9.00</bill></billitem></message_content>]]></message_body></message_response>";
				} else if ("6".equals(cdrType))// 彩信清单
				{
					response = "<message_response><message_head version=\"1.0\"><menuid>qryMmsMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102348</resp_time><sequence><req_seq>209</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><cust_name>用2700008515650</cust_name><product_name>全球通</product_name><product_info></product_info><createdate></createdate><total_fee></total_fee><billitem><bill>国内彩信~不定~801234~10658000~110360~801234~13797060656~10-01 08:02:13~0~         0.00~         0.00</bill></billitem><billitem><bill>国内彩信~不定~801180~106540330000~101101~801180~13797060656~10-01 08:02:21~44284~         0.00~         0.00</bill></billitem><billitem><bill>国内彩信~不定~801234~10658000~110301~801234~13797060656~10-01 08:44:06~0~         0.00~         0.00</bill></billitem></message_content>]]></message_body></message_response>";
				} else if ("7".equals(cdrType))// 代收信息费清单
				{
					response = "<message_response><message_head version=\"1.0\"><menuid>qryInfoFeeMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102355</resp_time><sequence><req_seq>210</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>-100</retcode><retmsg><![CDATA[*没有您要查询的记录!]]></retmsg></retinfo></message_head></message_response>";
				} else if ("8".equals(cdrType))// VPMN清单
				{
					response = "<message_response><message_head version=\"1.0\"><menuid>qryVpmnMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102401</resp_time><sequence><req_seq>211</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><cust_name>用2700008515650</cust_name><product_name>全球通</product_name><product_info></product_info><createdate></createdate><total_fee></total_fee><billitem><bill>被叫~13797060626~20111001 09:40:03~125~~0.00</bill></billitem><billitem><bill>被叫~13971275566~20111001 10:24:57~18~~0.00</bill></billitem><billitem><bill>被叫~13507187666~20111006 08:59:59~80~~0.00</bill></billitem><billitem><bill>被叫~13797060626~20111006 09:26:14~118~~0.00</bill></billitem><billitem><bill>被叫~13971275598~20111008 11:00:58~52~~0.00</bill></billitem><billitem><bill>被叫~13871272007~20111008 16:44:52~37~~0.00</bill></billitem></message_content>]]></message_body></message_response>";
				} else if ("9".equals(cdrType))// PIM清单
				{
					response = "<message_response><message_head version=\"1.0\"><menuid>qryPimMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102407</resp_time><sequence><req_seq>212</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>-100</retcode><retmsg><![CDATA[*没有您要查询的记录!]]></retmsg></retinfo></message_head></message_response>";
				} else if ("10".equals(cdrType))// 手机动画清单
				{
					response = "<message_response><message_head version=\"1.0\"><menuid>qryFlashMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102414</resp_time><sequence><req_seq>213</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>-100</retcode><retmsg><![CDATA[*没有您要查询的记录!]]></retmsg></retinfo></message_head></message_response>";
				} else if ("11".equals(cdrType))// G3清单
				{
					response = "<message_response><message_head version=\"1.0\"><menuid>qryG3Muster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102419</resp_time><sequence><req_seq>214</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>-100</retcode><retmsg><![CDATA[*没有您要查询的记录!]]></retmsg></retinfo></message_head></message_response>";
				} else if ("12".equals(cdrType))// 游戏点卡清单
				{
					response = "<message_response><message_head version=\"1.0\"><menuid>qryGameMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102424</resp_time><sequence><req_seq>215</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>-100</retcode><retmsg><![CDATA[*没有您要查询的记录!]]></retmsg></retinfo></message_head></message_response>";
				}
			}

			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}

	/** 
     * 详单查询权限验证_湖北
     * 
     * @param msgHeader 报文头信息
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap checkQueryAuth(MsgHeaderPO msgHeader)
    {
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recStopOpen</menuid><process_code>cli_busi_stopopensubs</process_code><verify_code></verify_code><resp_time>20100921002614</resp_time><sequence><req_seq>55</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
			
			//modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
			rw = new ReturnWrap();
			//modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
			
			rw.setStatus(0);
			rw.setReturnMsg("");
		}
		return rw;
	}

	/**
	 * 受理历史查询
	 */
	public ReturnWrap qryAllServiceHistory(Map map) {
		String startDate = (String) map.get("startDate");
		String endDate = (String) map.get("endDate");
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+ "<menuid>qryServiceHistory</menuid><process_code>cli_qry_rechistory</process_code>"
				+ "<verify_code></verify_code><resp_time>20100921002233</resp_time>"
				+ "<sequence><req_seq>40</req_seq><operation_seq></operation_seq></sequence><retinfo>"
				+ "<rettype>0</rettype><retcode>100</retcode>"
				+ "<retmsg><![CDATA[Processing the request succeeded!]]>"
				+ "</retmsg></retinfo></message_head>"
				+ "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
				+ "<message_content>"
				+ "<acptitem><accept_time>20100303210238</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>收费</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100318223958</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>收费</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100420221556</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>收费</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100521172823</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>产品变更</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100521172840</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>产品变更</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100521172918</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>产品变更</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100522214906</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>收费</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100527143358</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>产品变更</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100527143407</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>产品变更</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100531203055</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>改资料</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100608082218</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>产品变更</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100612095443</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>产品变更</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100622210420</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>收费</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100726165208</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>收费</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100823221444</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>收费</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100914174647</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>产品变更</accept_type><contents /></acptitem>"
				+ "<acptitem><accept_time>20100915172513</accept_time><accept_site>济南</accept_site><accept_staff /><accept_type>改密码</accept_type><contents /></acptitem>"
				+ "</message_content>]]></message_body></message_response>";
		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rw;
	}

	/**
	 * 套餐信息查询
	 * 
	 * @param map
	 * @return
	 */
	public ReturnWrap qryComboInfo(Map map) {
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>qrySerPri</menuid><process_code>cli_qry_taocan</process_code><verify_code></verify_code><resp_time>20100921002206</resp_time><sequence><req_seq>39</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><calctime>09月21日00时</calctime><privitem><privset>10元包120条点对点短信＋移动全时通必选包</privset><type>SMS</type><sumnum>120</sumnum><leftnum>33</leftnum><unit>条</unit><feename></feename><servtype>0</servtype><starttime>20100901</starttime><endtime>20100930</endtime></privitem></message_content>]]></message_body></message_response>";
		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rw;
	}

	/**
	 * 缴费历史查询
	 * 
	 * @param map
	 * @return
	 */
	public ReturnWrap qryChargeHistory(Map map) {
		String response = "";

		String province = (String) PublicCache.getInstance().getCachedData(
				Constants.PROVINCE_ID);
		if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province)) {
			response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
					+ "<message_head version=\"1.0\"><menuid>qryBill</menuid>"
					+ "<process_code>cli_qry_chargehistory</process_code><verify_code></verify_code>"
					+ "<resp_time>20110713160218</resp_time><sequence><req_seq>96</req_seq><operation_seq>"
					+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
					+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
					+ "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
					+ "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
					+ "<pay_date>2011-01-01 12:25:59</pay_date><billing_cycle_id></billing_cycle_id>"
					+ "<amount>2000</amount><statusname>否</statusname><payment_method></payment_method>"
					+ "<paytype>神州行充值卡</paytype><recorgid></recorgid><recorgname></recorgname></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
					+ "<pay_date>2011-01-29 09:31:43</pay_date><billing_cycle_id></billing_cycle_id>"
					+ "<amount>2000</amount><statusname>否</statusname><payment_method></payment_method>"
					+ "<paytype>神州行充值卡</paytype><recorgid></recorgid><recorgname></recorgname></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
					+ "<pay_date>2011-02-03 20:03:04</pay_date><billing_cycle_id></billing_cycle_id><amount>1000</amount>"
					+ "<statusname>否</statusname><payment_method></payment_method><paytype>神州行充值卡</paytype><recorgid>"
					+ "</recorgid><recorgname></recorgname></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
					+ "<pay_date>2011-02-08 10:51:05</pay_date><billing_cycle_id></billing_cycle_id><amount>3000</amount>"
					+ "<statusname>否</statusname><payment_method></payment_method><paytype>神州行充值卡</paytype>"
					+ "<recorgid></recorgid><recorgname></recorgname></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
					+ "<pay_date>2011-02-09 15:05:59</pay_date><billing_cycle_id></billing_cycle_id><amount>3000</amount>"
					+ "<statusname>否</statusname><payment_method></payment_method><paytype>神州行充值卡</paytype>"
					+ "<recorgid></recorgid><recorgname></recorgname></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
					+ "<pay_date>2011-02-18 18:24:11</pay_date><billing_cycle_id></billing_cycle_id><amount>2000</amount>"
					+ "<statusname>否</statusname><payment_method></payment_method><paytype>神州行充值卡</paytype>"
					+ "<recorgid></recorgid><recorgname></recorgname></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
					+ "<pay_date>2011-04-01 23:25:58</pay_date><billing_cycle_id></billing_cycle_id><amount>2000</amount>"
					+ "<statusname>否</statusname><payment_method></payment_method><paytype>神州行充值卡</paytype>"
					+ "<recorgid></recorgid><recorgname></recorgname></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
					+ "<pay_date>2011-04-07 17:32:37</pay_date><billing_cycle_id></billing_cycle_id><amount>10000</amount>"
					+ "<statusname>否</statusname><payment_method></payment_method><paytype>自助终端</paytype>"
					+ "<recorgid></recorgid><recorgname></recorgname></payhistoryitem>"
					+ "</message_content>]]></message_body></message_response>";
		} else {
			response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
					+ "<menuid>qryBill</menuid><process_code>cli_qry_chargehistory</process_code><verify_code></verify_code>"
					+ "<resp_time>20100921152941</resp_time><sequence><req_seq>75</req_seq><operation_seq></operation_seq>"
					+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
					+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
					+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
					+ "<payhistoryitem><pay_serial_nbr>531100504168436860</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100504090015</pay_date><billing_cycle_id>201004</billing_cycle_id><amount>5000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100731726732692</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100731112459</pay_date><billing_cycle_id>201006</billing_cycle_id><amount>500</amount>"
					+ "<statusname>在网</statusname><payment_method>自助终端</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100812442814884</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100812133704</pay_date><billing_cycle_id>201007</billing_cycle_id><amount>0</amount>"
					+ "<statusname>在网</statusname><payment_method>CRM营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100902623805704</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100902093409</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100504168436860</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100504090015</pay_date><billing_cycle_id>201004</billing_cycle_id><amount>5000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100731726732692</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100731112459</pay_date><billing_cycle_id>201006</billing_cycle_id><amount>500</amount>"
					+ "<statusname>在网</statusname><payment_method>自助终端</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100812442814884</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100812133704</pay_date><billing_cycle_id>201007</billing_cycle_id><amount>0</amount>"
					+ "<statusname>在网</statusname><payment_method>CRM营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100902623805704</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100902093409</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100504168436860</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100504090015</pay_date><billing_cycle_id>201004</billing_cycle_id><amount>5000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100731726732692</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100731112459</pay_date><billing_cycle_id>201006</billing_cycle_id><amount>500</amount>"
					+ "<statusname>在网</statusname><payment_method>自助终端</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100812442814884</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100812133704</pay_date><billing_cycle_id>201007</billing_cycle_id><amount>0</amount>"
					+ "<statusname>在网</statusname><payment_method>CRM营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100902623805704</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100902093409</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100504168436860</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100504090015</pay_date><billing_cycle_id>201004</billing_cycle_id><amount>5000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100731726732692</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100731112459</pay_date><billing_cycle_id>201006</billing_cycle_id><amount>500</amount>"
					+ "<statusname>在网</statusname><payment_method>自助终端</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100812442814884</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100812133704</pay_date><billing_cycle_id>201007</billing_cycle_id><amount>0</amount>"
					+ "<statusname>在网</statusname><payment_method>CRM营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100902623805704</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100902093409</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100504168436860</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100504090015</pay_date><billing_cycle_id>201004</billing_cycle_id><amount>5000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100731726732692</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100731112459</pay_date><billing_cycle_id>201006</billing_cycle_id><amount>500</amount>"
					+ "<statusname>在网</statusname><payment_method>自助终端</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100812442814884</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100812133704</pay_date><billing_cycle_id>201007</billing_cycle_id><amount>0</amount>"
					+ "<statusname>在网</statusname><payment_method>CRM营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100902623805704</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100902093409</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>收费</operation_type>"
					+ "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
					+ "<statusname>在网</statusname><payment_method>网上营业厅</payment_method></payhistoryitem>"
					+ "</message_content>]]></message_body></message_response>";
		}

		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rw;
	}

	/**
	 * 积分查询
	 */
	public ReturnWrap queryScoreValue(Map map) {
		String response;
		if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance()
				.getCachedData("ProvinceID"))) {
			response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
					+ "<menuid>qryWlanMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code>"
					+ "</verify_code><resp_time>20100921055850</resp_time><sequence><req_seq>21</req_seq>"
					+ "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
					+ "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
					+ "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
					+ "<scoreinfo>神州行，我看行！~300~50~20~20~20~280~10</scoreinfo>"
					+ "</message_content>]]>"
					+ "</message_body></message_response>";
		} else if (Constants.PROOPERORGID_NX.equals(PublicCache.getInstance()
				.getCachedData("ProvinceID"))) {
			response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
					+ "<message_head version=\"1.0\"><menuid>qryScore</menuid>"
					+ "<process_code>cli_qry_scorevalue</process_code><verify_code></verify_code>"
					+ "<resp_time>20110615183754</resp_time><sequence><req_seq>81</req_seq>"
					+ "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype>"
					+ "<retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
					+ "</retmsg></retinfo></message_head><message_body>"
					+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
					+ "<scoreinfo>80364</scoreinfo></message_content>]]></message_body>"
					+ "</message_response>";
		} else {
			response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
					+ "<menuid>qryWlanMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code>"
					+ "</verify_code><resp_time>20100921055850</resp_time><sequence><req_seq>21</req_seq>"
					+ "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
					+ "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
					+ "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
					+ "<scoreinfo>500~300~200~100</scoreinfo>"
					+ "</message_content>]]>"
					+ "</message_body></message_response>";
		}

		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rw;
	}

	/**
	 * 余额提醒查询
	 */
	public ReturnWrap qryBalanceNotice(Map map) {
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+ "<menuid>recChgBalanceUrge</menuid><process_code>cli_qry_alarmbalance</process_code><verify_code>"
				+ "</verify_code><resp_time>20100921002343</resp_time><sequence><req_seq>47</req_seq><operation_seq>"
				+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
				+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
				+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><prepay_type>1</prepay_type>"
				+ "<credit>500</credit></message_content>]]>"
				+ "</message_body></message_response>";

		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rw;
	}

	/**
	 * 获取余额提醒字典表数据
	 */
	public ReturnWrap getDictItem(Map map) {
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+ "<menuid>recChgBalanceUrge</menuid><process_code>cli_qry_dictitem</process_code><verify_code>"
				+ "</verify_code><resp_time>20100921002343</resp_time><sequence><req_seq>48</req_seq><operation_seq>"
				+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
				+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
				+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><dictitem><dict_id>5</dict_id>"
				+ "<dict_name>5元</dict_name></dictitem><dictitem><dict_id>10</dict_id><dict_name>10元</dict_name>"
				+ "</dictitem><dictitem><dict_id>15</dict_id><dict_name>15元</dict_name></dictitem><dictitem>"
				+ "<dict_id>20</dict_id><dict_name>20元</dict_name></dictitem><dictitem><dict_id>30</dict_id>"
				+ "<dict_name>30元</dict_name></dictitem><dictitem><dict_id>50</dict_id><dict_name>50元</dict_name>"
				+ "</dictitem></message_content>]]></message_body></message_response>";

		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rw;
	}

	/**
	 * 余额提醒值设置
	 */
	public ReturnWrap setBalanceNotice(Map map) {
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recChgBalanceUrge</menuid><process_code>cli_busi_alarmbalance</process_code><verify_code></verify_code><resp_time>20100921002348</resp_time><sequence><req_seq>49</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
		ReturnWrap rw = null;
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rw;
	}

	/**
	 * 根据nocde(新)查询产品,优惠的资费描述信息
	 * 
	 * @param map
	 * @return
	 * @see
	 */
	public ReturnWrap queryFeeMessage(Map map) {
		try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
					+ "<menuid>recCallDisplay</menuid><process_code>cli_qry_productfee</process_code><verify_code></verify_code>"
					+ "<resp_time>20100921002647</resp_time><sequence><req_seq>57</req_seq><operation_seq></operation_seq>"
					+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
					+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
					+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><desc>主叫号码显示</desc>"
					+ "</message_content>]]></message_body></message_response>";

			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}

	/**
	 * 调用能开产品受理通用接口
	 *
	 * @return
	 * @throws Exception
     * @Remark create by lWX431760 2017-07-19 OR_huawei_201706_780_【山东移动接口迁移专题】-自助终端新业务办理需求
	 */
	public ReturnWrap recCommonServNK(MsgHeaderPO msgHeader, String nCode, String operType, String effectType, String param) {
		try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
					+ "<menuid>recCallDisplay</menuid><process_code>cli_busi_productrec</process_code><verify_code>"
					+ "</verify_code><resp_time>20100921002649</resp_time><sequence><req_seq>58</req_seq><operation_seq>"
					+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]>"
					+ "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
					+ "<message_content><addncode></addncode><delncode>H01</delncode><curncode>H01</curncode><add_startdate>"
					+ "</add_startdate><add_enddate></add_enddate><del_enddate>2010-09-21 00:26:30</del_enddate><formnum>productrec001</formnum>"
					+ "</message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}
	
	/**
     * 产品受理通用接口
     *
     * @return
     * @throws Exception
     */
    public ReturnWrap recCommonServ(MsgHeaderPO msgHeader, String nCode, String operType, String effectType, String param) {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>recCallDisplay</menuid><process_code>cli_busi_productrec</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921002649</resp_time><sequence><req_seq>58</req_seq><operation_seq>"
                    + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    + "<message_content><addncode></addncode><delncode>H01</delncode><curncode>H01</curncode><add_startdate>"
                    + "</add_startdate><add_enddate></add_enddate><del_enddate>2010-09-21 00:26:30</del_enddate><formnum>productrec001</formnum>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");

            return rw;
        }
    }
    
    public ReturnWrap recCommonProductQry (MsgHeaderPO msgHeader, String nCode)
    {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>recCallDisplay</menuid><process_code>cli_busi_productrec</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921002649</resp_time><sequence><req_seq>58</req_seq><operation_seq>"
                    + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    + "<message_content><curncode></curncode><nextncode>H01</nextncode>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");

            return rw;
        }
    }

	/**
     * 停开机业务处理
     */
    public ReturnWrap stopOpenSubs(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recStopOpen</menuid><process_code>cli_busi_stopopensubs</process_code><verify_code></verify_code><resp_time>20100921002614</resp_time><sequence><req_seq>55</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
        ReturnWrap rw = null;
        try {
            rw = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
            rw = new ReturnWrap();
            //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
            
            rw.setStatus(0);
            rw.setReturnMsg("");
        }
        return rw;
    }

	/**
	 * 密码修改
	 */
	public ReturnWrap recChangePassword(Map map) {
		try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recChangePwd</menuid><process_code>cli_busi_chgpwd</process_code><verify_code></verify_code><resp_time>20100921002448</resp_time><sequence><req_seq>51</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("系统错误，密码修改受理错误！");

			return rw;
		}

	}

	/**
     * 呼叫转移受理
     * 
     * @param map
     * @return
     */
    public ReturnWrap commitCallTransferNo(Map map) {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>recCallTransfer</menuid><process_code>cli_busi_calltransfer</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921002750</resp_time><sequence><req_seq>61</req_seq><operation_seq>"
                    + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";

            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");

            return rw;
        }
    }

	/**
	 * 业务查询统一接口 梦网业务查询
	 */
	public ReturnWrap queryService(Map map) {
		try {
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
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}

	}

	/**
     * 业务统一退订接口
     */
    public ReturnWrap cancelService(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>recQrySPinfo</menuid><process_code>cli_busi_cancelsp</process_code><verify_code></verify_code>"
                + "<resp_time>20100921014415</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq></sequence>"
                + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                + "</retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                + "<formnum>cancelsp001</formnum>"
                + "</message_content>]]></message_body></message_response>";
        ReturnWrap rw = new ReturnWrap();
        try {
            rw = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            rw.setStatus(0);
            rw.setReturnMsg("");
        }
        return rw;
    }

	/**
	 * 手机支付主账户信息查询
	 * 
	 * @param map
	 * @return
	 */
	public ReturnWrap qryCmpayAccount(Map map) {
		try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
					+ "<menuid>mainAccoutCharge</menuid><process_code>cli_qry_mpayacc</process_code><verify_code>"
					+ "</verify_code><resp_time>20100921043608</resp_time><sequence><req_seq>8</req_seq><operation_seq>"
					+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
					+ "<![CDATA[操作成功]]></retmsg></retinfo></message_head><message_body>"
					+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
					+ "<boss_seq>531BIP2B09220100921043543521736</boss_seq><telnum>15153161532</telnum>"
					+ "<card_type>00</card_type><card_num>150102197511132076</card_num><user_name>赵鹏</user_name>"
					+ "<other_name>lczp</other_name><true_name>01</true_name><sacc_status>0</sacc_status>"
					+ "<acc_mess>|开户|密码更新|账户余额变动通知|</acc_mess><sacc_mess>|开户|预留|正常|</sacc_mess><cash>0</cash>"
					+ "<card>0</card><wait>0</wait></message_content>]]></message_body></message_response>";

			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}

	/**
	 * 手机支付主账户充值
	 * 
	 * @param map
	 * @return
	 */
	public ReturnWrap recMainFee(Map map) {
		try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
					+ "<menuid>cashchargemain</menuid><process_code>cli_busi_mpaycharge</process_code><verify_code>"
					+ "</verify_code><resp_time>20100921044459</resp_time><sequence><req_seq>11</req_seq><operation_seq>"
					+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
					+ "<![CDATA[操作成功]]></retmsg></retinfo></message_head><message_body>"
					+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
					+ "<boss_seq>531BIP2B09520100921044419949872</boss_seq><mpay_seq>10092100127173</mpay_seq>"
					+ "<cash>1000</cash></message_content>]]></message_body></message_response>";

			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}

	/**
	 * 密码重置校验身份证号
	 * 
	 * @param map
	 * @return
	 */
	public ReturnWrap checkIDCard(Map map) {
		try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
					+ "<menuid></menuid><process_code>cli_busi_cidcheck</process_code><verify_code></verify_code>"
					+ "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
					+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
					+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
					+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
					+ "<maxfailnum>3</maxfailnum><curfailnum>2</curfailnum><subname>吕旭光</subname><region></region>"
					+ "<regionname></regionname><productid></productid><productname>神州行</productname>"
					+ "<productgroup></productgroup><viptype></viptype><logintype></logintype><feeflag></feeflag>"
					+ "<question></question><answer></answer><needcheckstr></needcheckstr><contactid></contactid>"
					+ "<nettype></nettype><status></status><subage>10</subage><subscore>1000</subscore>"
					+ "</message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}

	/**
	 * 密码重置
	 * 
	 * @param map
	 * @return
	 */
	public ReturnWrap resetPassword(Map map) {
		try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
					+ "<menuid></menuid><process_code>cli_busi_chargefee</process_code><verify_code></verify_code>"
					+ "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
					+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
					+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
					+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content></message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();

			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}

	/**
	 * 查询预约号码
	 * 
	 * @param map
	 * @return
	 */
	public ReturnWrap qryChooseTel(Map map) {
		try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
					+ "<message_head version=\"1.0\"><menuid></menuid><process_code>cli_qry_numbynet</process_code>"
					+ "<verify_code></verify_code><resp_time>20110621111358</resp_time><sequence><req_seq>28</req_seq>"
					+ "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
					+ "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
					+ "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
					+ "<tellist><telnum>18709610009</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709611604</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709614964</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709617084</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709614472</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709610013</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709612745</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709610034</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709610001</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709610018</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709614134</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709610022</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709617424</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709610039</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709610006</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709614870</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709612647</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709610754</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709610016</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709617947</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709618430</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709618174</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709612074</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709610020</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709610037</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709610004</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709614740</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709610025</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709617154</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "<tellist><telnum>18709613914</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow 山东济南测试12</col></tellist>"
					+ "</message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}

	/**
	 * 预约号码
	 * 
	 * @param map
	 * @return
	 */
	public ReturnWrap chooseTel(Map map) {
		try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
					+ "<menuid></menuid><process_code>cli_busi_chargefee</process_code><verify_code></verify_code>"
					+ "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
					+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
					+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
					+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><orderid>123456</orderid></message_content>]]></message_body></message_response>";
			// String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>
			// <message_response><message_head version=\"1.0\">" +
			// "<menuid>chooseTel</menuid><process_code>cli_busi_occupytel</process_code><verify_code>"
			// +
			// "</verify_code><resp_time>20110622160603</resp_time><sequence><req_seq>17</req_seq>"
			// +
			// "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>109</retcode>"
			// +
			// "<retmsg><![CDATA[该证件预约号码数量已经达到最大，不允许再预定!]]></retmsg></retinfo></message_head>"
			// +
			// "</ message_response>";

			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}

	/**
	 * 充值卡充值缴费
	 * 
	 * @param map
	 * @return
	 */
	public ReturnWrap cardPayCommit(Map map) {
		try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
					+ "<menuid></menuid><process_code>cli_busi_chargefee</process_code><verify_code></verify_code>"
					+ "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
					+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
					+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
					+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content></message_content>]]></message_body></message_response>";

			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}

	/**
	 * 查询用户是否已开通手机邮箱
	 * 
	 * @param map
	 * @return
	 */
	public ReturnWrap qrymailBox(Map map) {
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+ "<menuid></menuid><process_code>cli_qry_userinfo</process_code><verify_code></verify_code>"
				+ "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
				+ "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
				+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
				+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><havemailbox>0</havemailbox>"
				+ "</message_content>]]></message_body></message_response>";

		try {
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("查询用户是否已开通手机邮箱失败!");

			return rw;
		}
	}

	/**
	 * 定制139免费邮箱
	 * 
	 * @param map
	 * @return
	 */
	public ReturnWrap add139Mail(Map map) {
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+ "<menuid></menuid><process_code>cli_qry_userinfo</process_code><verify_code></verify_code>"
				+ "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
				+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
				+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
				+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
				+ "</message_content>]]></message_body></message_response>";

		try {
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("开通139邮箱失败!");

			return rw;
		}
	}

	public ReturnWrap orderSPService(Map map) {
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+ "<menuid>recQrySPinfo</menuid><process_code>cli_busi_chgsubsmonserv</process_code><verify_code></verify_code>"
				+ "<resp_time>20100921014415</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq></sequence>"
				+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
				+ "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
				+ "<orderresult></orderresult><ordertime></ordertime><orderflag></orderflag><formnum>chgsubsmonserv001</formnum></message_content>]]></message_body></message_response>";

		ReturnWrap rw = new ReturnWrap();
		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
			rw.setStatus(0);
			rw.setReturnMsg("");
		}
		return rw;
	}

	/**
	 * 通过socket接口查询详单记录
	 * 
	 * @param map
	 *            入参
	 * @return
	 * @see
	 * @remark create g00140516 2011/12/09 R003C11L12n01 详单查询实现socket协议
	 */
	public ReturnWrap queryCDRListBySocket(Map map) {
		try {
			String cdrType = (String) map.get("CDRType");

			String response = "";

			String province = (String) PublicCache.getInstance().getCachedData(
					Constants.PROVINCE_ID);
			if (Constants.PROOPERORGID_NX.equals(province)) {
				if ("1".equals(cdrType)) {
					response = "gprs~559941~2011-06-18 00:36:28~9425336~信令流量~0.00;"
							+ "gprs~0~2011-06-18 01:36:29~0~信令流量~0.00;"
							+ "主叫~13909590065~2011-06-18 17:34:39~59~宁夏银川~0.22;"
							+ "短信主叫~13895176008~2011-06-18 17:45:44~0~宁夏银川~0.10;"
							+ "主叫~13895389498~2011-06-18 17:47:38~144~宁夏银川~0.00;";
				} else if ("3".equals(cdrType)) {
					response = "梦网短信[梦网下行]~929845|10658470~~2011-06-10 09:57:23~0.00~~其他~0.00~;"
							+ "梦网短信[梦网下行]~929845|10658470~~2011-06-10 10:03:52~0.00~~其他~0.00~;"
							+ "梦网短信[梦网下行]~929868|10658483~~2011-06-10 14:59:58~0.00~~其他~0.00~;";
				} else if ("4".equals(cdrType)) {
					response = "201106CMNET[信令流量]~2011-06-18 00:36:28~48429~9532.40~327.97~9204.43~0.00~0.19~546.82~8985.58;"
							+ "201106CMNET[信令流量]~2011-06-18 01:36:29~24057~0.00~0.00~0.00~0.00~0.00~0.00~0.00;";
				}
			}

			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(1);
			rw.setReturnObject(response);

			return rw;
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}

	/** 
     * NG3.5帐详单改造之详单查询
     * 
     * @param msgHeader 报文头信息
     * @param month 查询月份
     * @param cdrType 详单类型
     * @param feeType 费用类型
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
     */
    public ReturnWrap queryDetailedRecords2012(MsgHeaderPO msgHeader, String month,
        String cdrType, String feeType)
	{
		String response = "";

		// 全部费用
		if ("ALL".equals(feeType)) {
			if (Constants.CDRTYPE_FIXFEE.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryFixfeeMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary></billsummary><billitem>201201@_@01-01 -- 01-31@_@98新商旅套餐@_@98|"
						+ "201201@_@01-01 -- 01-31@_@98新商旅套餐@_@98|201201@_@01-01 -- 01-31@_@98新商旅套餐@_@98</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_GSM.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary></billsummary><billitem>2011-08-01 21:56:04@_@武汉@_@主叫@_@66174709@_@60@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
						+ "2011-08-02 21:56:04@_@武汉@_@主叫@_@05387763965@_@610@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
						+ "2011-08-02 21:56:04@_@武汉@_@主叫@_@66174709@_@132@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
						+ "2011-08-03 21:56:04@_@武汉@_@主叫@_@66174709@_@45@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
						+ "2011-08-03 21:56:04@_@武汉@_@主叫@_@66174709@_@390@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
						+ "2011-08-03 21:56:04@_@武汉@_@主叫@_@66174709@_@88@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
						+ "2011-08-26 21:56:04@_@武汉@_@主叫@_@66174709@_@269@_@国际长途@_@@_@34.40|"
						+ "2011-08-27 21:56:04@_@武汉@_@主叫@_@66174709@_@269@_@国际长途@_@@_@5.07</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_SMS.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qrySmsMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary>3条@_@2条</billsummary>"
						+ "<billitem>2011-08-01 21:56:04@_@内地@_@10659001@_@发送@_@SP彩信@_@体坛周报@_@@_@0.30|"
						+ "2011-08-03 21:56:04@_@内地@_@10659001@_@发送@_@SP彩信@_@**@_@@_@0.10|"
						+ "2011-08-04 21:56:04@_@内地@_@139********@_@接收@_@短信@_@@_@武汉短信包@_@0.00|"
						+ "2011-08-04 21:56:04@_@港澳台@_@139********@_@发送@_@彩信@_@@_@@_@0.10|"
						+ "2011-08-04 21:56:04@_@美国ATAT@_@139********@_@发送@_@彩信@_@@_@@_@0.70"
						+ "</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_GPRSWLAN.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary></billsummary><billitem>"
						+ "2011-08-01 21:56:04@_@北京@_@WLAN@_@12650@_@2018|"
						+ "2011-08-02 21:56:04@_@北京@_@BLACKBERRY@_@150@_@20180|"
						+ "2011-08-02 21:56:04@_@美国ATAT@_@CMNET@_@800@_@7|"
						+ "2011-08-03 21:56:04@_@武汉@_@WLAN@_@800@_@7|"
						+ "2011-08-03 21:56:04@_@武汉@_@WLAN@_@800@_@1256|</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_DISCOUNT.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+
						// "<billcount>1</billcount><billitem>08-01
						// 21:56:04@_@总账优惠@_@-76.00</billitem>" +
						"<billcount>4</billcount><billitem>"
						+ "2011-08-02 21:56:04@_@总账优惠@_@-76.00|2011-08-03 21:56:04@_@总账优惠@_@-76.00|"
						+ "2011-08-02 21:56:04@_@总账优惠@_@-76.00|2011-08-01 21:56:04@_@总账优惠@_@-76.00</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_INFOFEE.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary>5</billsummary><billitem>"
						+ "2011-08-01 21:56:04@_@彩信@_@超级贺卡@_@10658899@_@新浪@_@801005@_@点播@_@1.00|"
						+ "2011-08-02 21:56:04@_@彩信@_@超级贺卡@_@10658899@_@新浪@_@801005@_@点播@_@1.00|"
						+ "2011-08-02 21:56:04@_@彩信@_@超级贺卡@_@10658899@_@新浪@_@801005@_@点播@_@1.00|"
						+ "</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_ISMG.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+
						// "<billcount>1</billcount><billitem>08-01
						// 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@2</billitem>" +
						"<billcount>5</billcount><billitem>2011-08-01 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@2|"
						+ "2011-08-02 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@1|2011-08-02 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@0|"
						+ "2011-08-02 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@1|2011-08-03 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@2</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_OTHERFEE.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary></billsummary><billitem>"
						+ "2011-08-05 21:56:04@_@违约金@_@20.00|2011-08-05 21:56:04@_@协议补收费@_@15.00|"
						+ "2011-08-05 21:56:04@_@租机费@_@25.00</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			}
		}
		// 非零费用
		else if ("1".equals(feeType)) {
			if (Constants.CDRTYPE_FIXFEE.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryFixfeeMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary></billsummary><billitem>201201@_@01-01 -- 01-31@_@98新商旅套餐@_@98|"
						+ "201201@_@01-01 -- 01-31@_@98新商旅套餐@_@98|201201@_@01-01 -- 01-31@_@98新商旅套餐@_@98</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_GSM.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary></billsummary><billitem>"
						+ "2011-08-26 21:56:04@_@武汉@_@主叫@_@66174709@_@269@_@国际长途@_@@_@34.40|"
						+ "2011-08-27 21:56:04@_@武汉@_@主叫@_@66174709@_@269@_@国际长途@_@@_@5.07</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_SMS.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qrySmsMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary>3条@_@2条</billsummary>"
						+ "<billitem>2011-08-01 21:56:04@_@内地@_@10659001@_@发送@_@SP彩信@_@体坛周报@_@@_@0.30|"
						+ "2011-08-03 21:56:04@_@内地@_@10659001@_@发送@_@SP彩信@_@**@_@@_@0.10|"
						+ "2011-08-04 21:56:04@_@港澳台@_@139********@_@发送@_@彩信@_@@_@@_@0.10|"
						+ "2011-08-04 21:56:04@_@美国ATAT@_@139********@_@发送@_@彩信@_@@_@@_@0.70"
						+ "</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_GPRSWLAN.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary></billsummary><billitem>"
						+ "2011-08-01 21:56:04@_@北京@_@WLAN@_@12650@_@2018|"
						+ "2011-08-02 21:56:04@_@北京@_@BLACKBERRY@_@150@_@20180|"
						+ "2011-08-02 21:56:04@_@美国ATAT@_@CMNET@_@800@_@7|"
						+ "2011-08-03 21:56:04@_@武汉@_@WLAN@_@800@_@7|"
						+ "2011-08-03 21:56:04@_@武汉@_@WLAN@_@800@_@1256|</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_DISCOUNT.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billcount>4</billcount><billitem>"
						+ "2011-08-02 21:56:04@_@总账优惠@_@-76.00|2011-08-03 21:56:04@_@总账优惠@_@-76.00|"
						+ "2011-08-02 21:56:04@_@总账优惠@_@-76.00|2011-08-01 21:56:04@_@总账优惠@_@-76.00</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_INFOFEE.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary>5</billsummary><billitem>"
						+ "2011-08-01 21:56:04@_@彩信@_@超级贺卡@_@10658899@_@新浪@_@801005@_@点播@_@1.00|"
						+ "2011-08-02 21:56:04@_@彩信@_@超级贺卡@_@10658899@_@新浪@_@801005@_@点播@_@1.00|"
						+ "2011-08-02 21:56:04@_@彩信@_@超级贺卡@_@10658899@_@新浪@_@801005@_@点播@_@1.00|"
						+ "</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_ISMG.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billcount>4</billcount><billitem>2011-08-01 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@2|"
						+ "2011-08-02 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@1|"
						+ "2011-08-02 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@1|2011-08-03 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@2</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_OTHERFEE.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>192</retcode><retmsg><![CDATA[No information!]]></retmsg></retinfo>"
						+ "</message_head></message_response>";
			}
		}
		// 零费用
		else {
			if (Constants.CDRTYPE_FIXFEE.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryFixfeeMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>192</retcode><retmsg><![CDATA[No Information!]]></retmsg></retinfo>"
						+ "</message_head></message_response>";
			} else if (Constants.CDRTYPE_GSM.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary></billsummary><billitem>2011-08-01 21:56:04@_@武汉@_@主叫@_@66174709@_@60@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
						+ "2011-08-02 21:56:04@_@武汉@_@主叫@_@66174709@_@89@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
						+ "2011-08-02 21:56:04@_@武汉@_@主叫@_@66174709@_@132@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
						+ "2011-08-03 21:56:04@_@武汉@_@主叫@_@66174709@_@45@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
						+ "2011-08-03 21:56:04@_@武汉@_@主叫@_@66174709@_@390@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
						+ "2011-08-03 21:56:04@_@武汉@_@主叫@_@66174709@_@88@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
						+ "</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_SMS.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qrySmsMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary>3条@_@2条</billsummary>"
						+ "<billitem>2011-08-04 21:56:04@_@内地@_@139********@_@接收@_@短信@_@@_@武汉短信包@_@0.00|"
						+ "</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_GPRSWLAN.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary></billsummary><billitem>"
						+ "2011-08-01 21:56:04@_@北京@_@WLAN@_@12650@_@2018|"
						+ "2011-08-02 21:56:04@_@北京@_@BLACKBERRY@_@150@_@20180|"
						+ "2011-08-02 21:56:04@_@美国ATAT@_@CMNET@_@800@_@7|"
						+ "2011-08-03 21:56:04@_@武汉@_@WLAN@_@800@_@7|"
						+ "2011-08-03 21:56:04@_@武汉@_@WLAN@_@800@_@1256|</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_DISCOUNT.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>192</retcode><retmsg><![CDATA[No information!]]></retmsg></retinfo>"
						+ "</message_head></message_response>";
			} else if (Constants.CDRTYPE_INFOFEE.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary>5</billsummary><billitem>"
						+ "2011-08-01 21:56:04@_@彩信@_@超级贺卡@_@10658899@_@新浪@_@801005@_@点播@_@0.00|"
						+ "</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_ISMG.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billcount>1</billcount><billitem>2011-08-02 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@0|"
						+ "</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			} else if (Constants.CDRTYPE_OTHERFEE.equals(cdrType)) {
				response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
						+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
						+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
						+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
						+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
						+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
						+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
						+ "<billsummary></billsummary><billitem>"
						+ "2011-08-05 21:56:04@_@违约金@_@20.00|2011-08-05 21:56:04@_@协议补收费@_@15.00|"
						+ "2011-08-05 21:56:04@_@租机费@_@25.00</billitem>"
						+ "</message_content>]]></message_body></message_response>";
			}
		}

		ReturnWrap rw = new ReturnWrap();

		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
			rw.setStatus(0);
			rw.setReturnMsg("");
		}
		return rw;
	}

	/**
	 * NG3.5帐详单改造之查询客户信息
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 * @remark create g00140516 2012/02/13 R003C12L02n01
	 */
	public ReturnWrap queryCustomerInfo(Map<String, String> map) {
		String month = (String) map.get("month");
		String response = "";
		if ("201201".equals(month)) {
			response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
					+ "<menuid>qryMuster</menuid><process_code>cli_qry_custinfo</process_code><verify_code></verify_code>"
					+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
					+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
					+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
					+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
					+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
					+ "<custname>高群</custname><brandnm>动感地带</brandnm><productnm>创业卡</productnm><subsid>1234567890123456</subsid>"
					+ "<cyclelist><cycle>20120101</cycle><startdate>20120101</startdate><enddate>20120115</enddate>"
					+ "<acctid>9876543210123456</acctid><unionacct>0</unionacct></cyclelist>"
					+ "<cyclelist><cycle>20120116</cycle><startdate>20120116</startdate><enddate>20120131</enddate>"
					+ "<acctid>6543210123456789</acctid><unionacct>0</unionacct></cyclelist>"
					+ "</message_content>]]></message_body></message_response>";
		}
		if ("201112".equals(month)) {
			response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
					+ "<menuid>qryMuster</menuid><process_code>cli_qry_custinfo</process_code><verify_code></verify_code>"
					+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
					+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
					+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
					+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
					+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
					+ "<custname>高群</custname><brandnm>动感地带</brandnm><productnm>创业卡</productnm><subsid>1234567890123456</subsid>"
					+ "<cyclelist><cycle>20120101</cycle><startdate>20111201</startdate><enddate>20111231</enddate>"
					+ "<acctid>9876543210123456</acctid><unionacct>0</unionacct></cyclelist>"
					+ "</message_content>]]></message_body></message_response>";
		} else {
			response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
					+ "<menuid>qryMuster</menuid><process_code>cli_qry_custinfo</process_code><verify_code></verify_code>"
					+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
					+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
					+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
					+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
					+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
					+ "<custname>高群</custname><brandnm>动感地带</brandnm><productnm>创业卡</productnm><subsid>1234567890123456</subsid>"
					+ "<cyclelist><cycle>20120501</cycle><startdate>20120501</startdate><enddate>20120529</enddate>"
					+ "<acctid>9876543210123456</acctid><unionacct>0</unionacct></cyclelist>"
					+ "</message_content>]]></message_body></message_response>";
		}

		ReturnWrap rw = new ReturnWrap();

		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
			rw.setStatus(0);
			rw.setReturnMsg("");
		}
		return rw;
	}

	/**
	 * NG3.5帐详单改造之详单查询
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 * @remark create g00140516 2012/02/13 R003C12L02n01
	 */
	public ReturnWrap queryDetailedRecordsSD2012(Map<String, String> map) {
		String response = "";
		String cdrType = map.get("CDRType");

        if ("1".equals(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>178.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum></gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum></wlantotalnum><wlantotalfee></wlantotalfee>"

                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    + "<billinfo>"
                    + "2012-1-12@_@20120101-20120129@_@168元全球通市话套餐@_@168.00|"
                    + "2012-1-12@_@20120101-20120129@_@10元GPRS包10M@_@10.00|"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("2".equals(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>0.60</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum></gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum></wlantotalnum><wlantotalfee></wlantotalfee>"
                    
                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    + "<billinfo>"
                    + "2011-1-15 9:09:57@_@济南@_@主叫@_@18660187701@_@1小时1分24秒@_@港澳台@_@188元市话套餐@_@0.00@_@0.00|"
                    + "2011-1-15 9:28:18@_@济南@_@被叫@_@13853183025@_@1分31秒@_@@_@188元市话套餐@_@0.00@_@0.00|"
                    + "2011-1-15 9:34:11@_@济南@_@被叫@_@18953190875@_@48秒@_@@_@188元市话套餐@_@@_@|"
                    + "2011-1-16 14:28:06@_@广东深圳@_@主叫@_@15953105927@_@13秒@_@国内@_@188元市话套餐@_@0.00@_@0.30|"
                    + "2011-1-16 17:09:20@_@广东深圳@_@被叫@_@18660187701@_@11秒@_@国内@_@188元市话套餐@_@0.00@_@0.00|"
                    + "2011-1-16 17:21:34@_@广东深圳@_@主叫@_@13964060128@_@41秒@_@国内@_@188元市话套餐@_@0.00@_@0.30|"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("3".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    +
                    // "<totalfee>3.90</totalfee><smstotalnum>6</smstotalnum><mmstotalnum>2</mmstotalnum><gprstotalnum></gprstotalnum>"
                    // +
                    // "<gprstotalfee></gprstotalfee><wlantotalnum></wlantotalnum><wlantotalfee></wlantotalfee>"
                    // +
                    // "<billinfo>" +
                    // "2011-1-15
                    // 0:20:31@_@内地@_@13905320000@_@发送@_@短信@_@@_@8元短信包@_@0.00|"
                    // +
                    // "2011-1-15
                    // 0:25:36@_@内地@_@13905320000@_@发送@_@短信@_@@_@9元短信包@_@0.00|"
                    // +
                    // "2011-1-15
                    // 12:24:33@_@港澳台@_@13905311111@_@发送@_@短信@_@@_@10元短信包@_@0.00|"
                    // +
                    // "2011-1-16
                    // 0:20:31@_@内地@_@13905320000@_@发送@_@短信@_@@_@8元短信包@_@0.00|"
                    // +
                    // "2011-1-16
                    // 0:25:36@_@内地@_@13905320000@_@发送@_@短信@_@@_@9元短信包@_@0.00|"
                    // +
                    // "2011-1-16
                    // 12:24:33@_@港澳台@_@13905311111@_@发送@_@短信@_@@_@10元短信包@_@0.00|"
                    // +
                    // "2011-1-15
                    // 0:15:11@_@内地@_@1065812345@_@接收@_@彩信@_@体坛周报@_@@_@1.30|" +
                    // "2011-1-15
                    // 0:25:31@_@内地@_@1065812345@_@发送@_@彩信@_@体坛周报@_@@_@1.30|" +
                    // "2011-1-15
                    // 12:18:21@_@港澳台@_@1065812345@_@发送@_@彩信@_@体坛周报@_@@_@1.30" +
                    // "</billinfo>" +
                    "<totalfee>0.80</totalfee><smstotalnum>4</smstotalnum><mmstotalnum>0</mmstotalnum>"
                    + "<gprstotalnum></gprstotalnum><gprstotalfee></gprstotalfee><wlantotalnum></wlantotalnum>"
                    + "<wlantotalfee></wlantotalfee>"

                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    + "<billinfo>2012-02-20 08:27:09@_@国内@_@15165482328@_@发送@_@"
                    + "网内短信@_@@_@@_@0.10|2012-02-20 19:08:40@_@国内@_@13605385534@_@发送@_@网内短信@_@@_@@_@0.10"
                    + "|2012-02-20 19:42:46@_@国内@_@13605385534@_@发送@_@网内短信@_@@_@@_@0.10|2012-02-20 20:43:14"
                    + "@_@国内@_@008613605385534@_@终端发起@_@增值业务或代收费引发彩信@_@@_@@_@0.50|</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("4".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee></totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>2</gprstotalnum>"
                    + "<gprstotalfee>0.00</gprstotalfee><wlantotalnum>4</wlantotalnum><wlantotalfee>0.00</wlantotalfee>"

                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>";
                    
                    // GprsWlan显示正常和优惠时段流量开关参数（1：显示  0：不显示）
                    String showGprsWlanFlux = CommonUtil.getParamValue(Constants.SH_GPRSWLAN_SHOWFLUX);
                    if ("1".equals(showGprsWlanFlux))
                    {
                        response = response + "<billinfo>"
                        +
                        // "2012-04-01 04:22:46@_@山东省@_@CMNET(G网)@_@30分@_@3KB
                        // 7B@_@388元套餐@_@0.00|" +
                        // "2012-04-01
                        // 04:52:46@_@山东省@_@CMNET(G网)@_@30分@_@888B@_@388元套餐@_@0.00|"
                        // +
                        // "2012-04-16 16:05:49@_@山东省@_@CMWAP(G网)@_@30分@_@25KB
                        // 358B@_@388元套餐@_@0.00|" +
                        "2012-04-16 18:18:02@_@山东省@_@CMWAP(G网)@_@26分3秒@_@3KB 552B@_@2KB 552B@_@业务名称1@_@388元套餐@_@0.00|"
                        + "2012-04-16 19:14:10@_@山东省@_@CMWAP(G网)@_@30分@_@84KB 605B@_@83KB 605B@_@业务名称2@_@388元套餐@_@0.00|"
                        + "2012-04-01 09:05:49@_@山东济南@_@WLAN@_@2小时6分8秒@_@14MB 213KB@_@13MB 213KB@_@业务名称3@_@10元WLAN统一套餐@_@0.00|"
                        + "2012-04-01 13:09:11@_@山东济南@_@WLAN@_@5小时21分54秒@_@10MB 493KB@_@9MB 493KB@_@业务名称4@_@10元WLAN统一套餐@_@0.00|"
                        + "2012-04-01 18:34:08@_@山东济南@_@WLAN@_@7分7秒@_@37KB@_@36KB@_@业务名称5@_@10元WLAN统一套餐@_@0.00|"
                        + "2012-04-05 08:38:45@_@山东济南@_@WLAN@_@14分35秒@_@436KB@_@435KB@_@业务名称6@_@10元WLAN统一套餐@_@0.00|"
                        + "</billinfo>"
                        + "</message_content>]]></message_body></message_response>";
                    }
                    else
                    {
                        response = response + "<billinfo>"
                        +
                        // "2012-04-01 04:22:46@_@山东省@_@CMNET(G网)@_@30分@_@3KB
                        // 7B@_@388元套餐@_@0.00|" +
                        // "2012-04-01
                        // 04:52:46@_@山东省@_@CMNET(G网)@_@30分@_@888B@_@388元套餐@_@0.00|"
                        // +
                        // "2012-04-16 16:05:49@_@山东省@_@CMWAP(G网)@_@30分@_@25KB
                        // 358B@_@388元套餐@_@0.00|" +
                        "2012-04-16 18:18:02@_@山东省@_@CMWAP(G网)@_@26分3秒@_@3KB 552B@_@388元套餐@_@0.00|"
                        + "2012-04-16 19:14:10@_@山东省@_@CMWAP(G网)@_@30分@_@84KB 605B@_@388元套餐@_@0.00|"
                        + "2012-04-01 09:05:49@_@山东济南@_@WLAN@_@2小时6分8秒@_@14MB 213KB@_@10元WLAN统一套餐@_@0.00|"
                        + "2012-04-01 13:09:11@_@山东济南@_@WLAN@_@5小时21分54秒@_@10MB 493KB@_@10元WLAN统一套餐@_@0.00|"
                        + "2012-04-01 18:34:08@_@山东济南@_@WLAN@_@7分7秒@_@37KB@_@10元WLAN统一套餐@_@0.00|"
                        + "2012-04-05 08:38:45@_@山东济南@_@WLAN@_@14分35秒@_@436KB@_@10元WLAN统一套餐@_@0.00|"
                        + "</billinfo>"
                        + "</message_content>]]></message_body></message_response>";
                    }
        } else if ("5".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>28.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>10</gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum>2</wlantotalnum><wlantotalfee>5.00</wlantotalfee>"

                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    + "<billinfo>"
                    + "2012-1-15 23:50:01@_@语音@_@彩铃DIY@_@10658899@_@1小时10分5秒@_@2.00|"
                    + "2012-1-15 23:50:01@_@语音@_@农信通@_@12590@_@1小时10分5秒@_@10.00|"
                    + "2012-1-16 23:50:01@_@语音@_@彩铃DIY@_@10658899@_@1小时10分5秒@_@2.00|"
                    + "2012-1-16 23:50:01@_@语音@_@农信通@_@12590@_@1小时10分5秒@_@10.00"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("6".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>28.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>10</gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum>2</wlantotalnum><wlantotalfee>5.00</wlantotalfee>"

                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    + "<billinfo>"
                    + "2012-1-15 23:50:01@_@WAP@_@彩铃@_@@_@3.00|"
                    + "2012-1-15 22:14:05@_@彩信@_@新华时讯@_@10658999@_@5.00|"
                    + "2012-1-15 23:05:18@_@WAP@_@号簿管家@_@10652333@_@6.00|"
                    + "2012-1-16 23:50:01@_@WAP@_@彩铃@_@@_@3.00|"
                    + "2012-1-16 22:14:05@_@彩信@_@新华时讯@_@10658999@_@5.00|"
                    + "2012-1-16 23:05:18@_@WA@_@号簿管家@_@10652333@_@6.00"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("7".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>18.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>10</gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum>2</wlantotalnum><wlantotalfee>5.00</wlantotalfee>"

                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    + "<billinfo>"
                    + "2012-1-15 22:31:15@_@语音@_@超级贺卡@_@1065888@_@广州智益信息有限公司@_@31秒@_@2.00|"
                    + "2012-1-15 22:15:32@_@语音@_@虚拟人生@_@10666666@_@搜狐@_@1分20秒@_@7.00|"
                    + "2012-1-17 22:31:15@_@语音@_@超级贺卡@_@1065888@_@广州智益信息有限公司@_@31秒@_@2.00|"
                    + "2012-1-17 22:15:32@_@语音@_@虚拟人生@_@10666666@_@搜狐@_@1分20秒@_@7.00"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("8".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>18.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>10</gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum>2</wlantotalnum><wlantotalfee>5.00</wlantotalfee>"

                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    + "<billinfo>"
                    + "2012-1-16 22:31:15@_@彩信@_@超级贺卡@_@1065888@_@广州智益信息有限公司@_@801005@_@点播信息费@_@2.00|"
                    + "2012-1-16 22:15:32@_@短信@_@虚拟人生@_@10666666@_@搜狐@_@901002@_@包月信息费@_@7.00|"
                    + "2012-1-18 22:31:15@_@彩信@_@超级贺卡@_@1065888@_@广州智益信息有限公司@_@801005@_@点播信息费@_@2.00|"
                    + "2012-1-18 22:15:32@_@短信@_@虚拟人生@_@10666666@_@搜狐@_@901002@_@包月信息费@_@7.00"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        }

		ReturnWrap rw = new ReturnWrap();

		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
			rw.setStatus(0);
			rw.setReturnMsg("");
		}
		return rw;
	}

	public ReturnWrap queryDetailedRecordsNX2012(Map<String, String> map) {
		String response = "";
		String cdrType = map.get("CDRType");

		if ("1".equals(cdrType)) {
			response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
					+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
					+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
					+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
					+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
					+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
					+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
					+ "<totalfee>178.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum></gprstotalnum>"
					+ "<gprstotalfee></gprstotalfee><wlantotalnum></wlantotalnum><wlantotalfee></wlantotalfee>"
					+ "<billinfo>"
					+ "2012-1-12@_@20120101-20120129@_@168元全球通市话套餐@_@168.00|"
					+ "2012-01-12@_@20120101-20120129@_@10元GPRS包10M@_@10.00|"
					+ "</billinfo>"
					+ "</message_content>]]></message_body></message_response>";
		} else if ("2".equals(cdrType)) {
			response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
					+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
					+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
					+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
					+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
					+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
					+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
					+ "<totalfee>0.60</totalfee><localcall>localcall</localcall><longcall>longcall</longcall>"
					+ "<roamcall>roamcall</roamcall><citycall>citycall</citycall><othercall>othercall</othercall>"
					+ "<inlancall>inlancall</inlancall><colonycall>colonycall</colonycall><interncall>interncall</interncall>"
					+ "<inlanroam>inlanroam</inlanroam><colonyroam>colonyroam</colonyroam><internroam>internroam</internroam>"
					+ "<smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum></gprstotalnum>"
					+ "<gprstotalfee></gprstotalfee><wlantotalnum></wlantotalnum><wlantotalfee></wlantotalfee>"
					+ "<billinfo>"
					+ "2011-1-15 9:09:57@_@济南@_@主叫@_@18660187701@_@1小时1分24秒@_@港澳台@_@188元市话套餐@_@0.00|"
					+ "2011-1-15 9:28:18@_@济南@_@被叫@_@13853183025@_@1分31秒@_@@_@188元市话套餐@_@0.00|"
					+ "2011-1-15 9:34:11@_@济南@_@被叫@_@18953190875@_@48秒@_@@_@188元市话套餐@_@|"
					+ "2011-1-16 14:28:06@_@广东深圳@_@主叫@_@15953105927@_@13秒@_@国内@_@188元市话套餐@_@0.00|"
					+ "2011-1-16 17:09:20@_@广东深圳@_@被叫@_@18660187701@_@11秒@_@国内@_@188元市话套餐@_@0.00|"
					+ "2011-1-16 17:21:34@_@广东深圳@_@主叫@_@13964060128@_@41秒@_@国内@_@188元市话套餐@_@0.00|"
					+ "</billinfo>"
					+ "</message_content>]]></message_body></message_response>";
		} else if ("3".equalsIgnoreCase(cdrType)) {
			response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
					+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
					+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
					+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
					+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
					+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
					+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
					+ "<totalfee>3.90</totalfee><smstotalnum>6</smstotalnum><mmstotalnum>2</mmstotalnum><gprstotalnum></gprstotalnum>"
					+ "<gprstotalfee></gprstotalfee><wlantotalnum></wlantotalnum><wlantotalfee></wlantotalfee>"
					+ "<billinfo>"
					+ "2011-1-15 0:20:31@_@内地@_@13905320000@_@发送@_@短信@_@@_@8元短信包@_@0.00|"
					+ "2011-1-15 0:25:36@_@内地@_@13905320000@_@发送@_@短信@_@@_@9元短信包@_@0.00|"
					+ "2011-1-15 12:24:33@_@港澳台@_@13905311111@_@发送@_@短信@_@@_@10元短信包@_@0.00|"
					+ "2011-1-16 0:20:31@_@内地@_@13905320000@_@发送@_@短信@_@@_@8元短信包@_@0.00|"
					+ "2011-1-16 0:25:36@_@内地@_@13905320000@_@发送@_@短信@_@@_@9元短信包@_@0.00|"
					+ "2011-1-16 12:24:33@_@港澳台@_@13905311111@_@发送@_@短信@_@@_@10元短信包@_@0.00|"
					+ "2011-1-15 0:15:11@_@内地@_@1065812345@_@接收@_@彩信@_@体坛周报@_@@_@1.30|"
					+ "2011-1-15 0:25:31@_@内地@_@1065812345@_@发送@_@彩信@_@体坛周报@_@@_@1.30|"
					+ "2011-1-15 12:18:21@_@港澳台@_@1065812345@_@发送@_@彩信@_@体坛周报@_@@_@1.30"
					+ "</billinfo>"
					+ "</message_content>]]></message_body></message_response>";
		} else if ("4".equalsIgnoreCase(cdrType)) {
		    
			response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
					+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
					+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
					+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
					+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
					+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
					+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
					+ "<totalfee></totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprssum>gprssum</gprssum>"
					+ "<chargeflux>chargeflux</chargeflux><freechargeflux>freechargeflux</freechargeflux><sumflux>sumflux</sumflux>"
					+ "<wlansumtime>wlansumtime</wlansumtime><wlansumfee>wlansumfee</wlansumfee><wlansum>wlansum</wlansum>"
					+ "<wlansumflux>wlansumflux</wlansumflux><gprssumfee>gprssumfee</gprssumfee><cmwapsum>cmwapsum</cmwapsum>"
					+ "<cmnetsum>cmnetsum</cmnetsum><cmwapflux>cmwapflux</cmwapflux><cmnetflux>cmnetflux</cmnetflux>"
					+ "<cmwapfreeflux>cmwapfreeflux</cmwapfreeflux><cmnetfreeflux>cmnetfreeflux</cmnetfreeflux>"
					+ "<cmwapsumflux>cmwapsumflux</cmwapsumflux><cmnetsumflux>cmnetsumflux</cmnetsumflux><pubwlansum>pubwlansum</pubwlansum>"
					+ "<campuswlansum>campuswlansum</campuswlansum><pubwlanflux>pubwlanflux</pubwlanflux><campuswlanflux>campuswlanflux</campuswlanflux>"
					+ "<pubwlantime>pubwlantime</pubwlantime><campuswlantime>campuswlantime</campuswlantime><pubwlanfee>pubwlanfee</pubwlanfee>"
					+ "<campuswlanfee>campuswlanfee</campuswlanfee><cmwapfee>cmwapfee</cmwapfee><cmnetfee>cmnetfee</cmnetfee>"
					+ "<billinfo>"
					+ "2012-12-1 12:24:33@_@深圳@_@CMWAP(G网)@_@02:01:02@_@1MB1KB24B@_@5元包20M流量@_@2G@_@0.00|"
					+ "2012-12-1 15:24:32@_@深圳@_@CMNET(T网)@_@01:03:00@_@1MB1KB25B@_@5元包21M流量@_@2G@_@0.00|"
					+ "2012-12-2 13:24:33@_@深圳@_@@_@00:05:00@_@1MB1KB25B@_@5元包流量@_@3G@_@0.00|"
					+ "2012-12-2 15:24:32@_@深圳@_@@_@00:00:08@_@1MB1KB25B@_@5元包流量@_@3G@_@0.00|"
					+ "2012-12-2 17:24:33@_@深圳@_@WAP@_@00:05:00@_@@_@10元包50小时@_@3G@_@0.00|"
					+ "2012-12-2 19:24:32@_@深圳@_@WAp@_@00:00:08@_@@_@10元包51小时@_@3G@_@0.00"
					+ "</billinfo>"
					+ "</message_content>]]></message_body></message_response>";
		    
		} else if ("5".equalsIgnoreCase(cdrType)) {
			response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
					+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
					+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
					+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
					+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
					+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
					+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
					+ "<totalfee>28.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>10</gprstotalnum>"
					+ "<gprstotalfee></gprstotalfee><wlantotalnum>2</wlantotalnum><wlantotalfee>5.00</wlantotalfee>"
					+ "<billinfo>"
					+ "2012-1-15 23:50:01@_@语音@_@彩铃DIY@_@10658899@_@2.00|"
					+ "2012-1-15 23:50:01@_@语音@_@农信通@_@12590@_@10.00|"
					+ "2012-1-16 23:50:01@_@语音@_@彩铃DIY@_@10658899@_@2.00|"
					+ "2012-1-16 23:50:01@_@语音@_@农信通@_@12590@_@10.00"
					+ "</billinfo>"
					+ "</message_content>]]></message_body></message_response>";
		} else if ("6".equalsIgnoreCase(cdrType)) {
			response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
					+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
					+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
					+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
					+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
					+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
					+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
					+ "<totalfee>18.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>10</gprstotalnum>"
					+ "<gprstotalfee></gprstotalfee><wlantotalnum>2</wlantotalnum><wlantotalfee>5.00</wlantotalfee>"
					+ "<billinfo>"
					+ "2012-1-15 22:31:15@_@语音@_@超级贺卡@_@1065888@_@广州智益信息有限公司@_@@_@31秒@_@2.00|"
					+ "2012-1-15 22:15:32@_@语音@_@虚拟人生@_@10666666@_@搜狐@_@@_@1分20秒@_@7.00|"
					+ "2012-1-17 22:31:15@_@语音@_@超级贺卡@_@1065888@_@广州智益信息有限公司@_@@_@31秒@_@2.00|"
					+ "2012-1-17 22:15:32@_@语音@_@虚拟人生@_@10666666@_@搜狐@_@@_@1分20秒@_@7.00"
					+ "</billinfo>"
					+ "</message_content>]]></message_body></message_response>";
		} else if ("7".equalsIgnoreCase(cdrType)) {
			response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
					+ "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
					+ "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
					+ "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
					+ "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
					+ "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
					+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
					+ "<totalfee>18.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>10</gprstotalnum>"
					+ "<gprstotalfee></gprstotalfee><wlantotalnum>2</wlantotalnum><wlantotalfee>5.00</wlantotalfee>"
					+ "<billinfo>"
					+ "2012-1-16 22:31:15@_@彩信@_@超级贺卡@_@1065888@_@广州智益信息有限公司@_@801005@_@点播信息费@_@2.00|"
					+ "2012-1-16 22:15:32@_@短信@_@虚拟人生@_@10666666@_@搜狐@_@901002@_@包月信息费@_@7.00|"
					+ "2012-1-18 22:31:15@_@彩信@_@超级贺卡@_@1065888@_@广州智益信息有限公司@_@801005@_@点播信息费@_@2.00|"
					+ "2012-1-18 22:15:32@_@短信@_@虚拟人生@_@10666666@_@搜狐@_@901002@_@包月信息费@_@7.00"
					+ "</billinfo>"
					+ "</message_content>]]></message_body></message_response>";
		}

		ReturnWrap rw = new ReturnWrap();

		try {
			rw = intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
			rw.setStatus(0);
			rw.setReturnMsg("");
		}
		return rw;
	}

	public SocketUtil getSocketUtil() {
		return socketUtil;
	}

	public void setSocketUtil(SocketUtil socketUtil) {
		this.socketUtil = socketUtil;
	}

	@Override
	public ReturnWrap valiIsfirstpwd(Map<String, String> map) {
		try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
					+ "<menuid></menuid><process_code>cli_busi_isfirstpwd</process_code><verify_code></verify_code>"
					+ "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
					+ "</sequence><retinfo><rettype>0</rettype><retcode>101</retcode><retmsg>"
					+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
					+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content></message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			return new ReturnWrap();
		}
	}

	@Override
	public ReturnWrap queryScoreChangeHis(Map map) {
		try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
					"<menuid>qryScore</menuid><process_code>cli_qry_scorechange</process_code><verify_code></verify_code>" +
					"<unicontact></unicontact><resp_time>20120518174110</resp_time><sequence><req_seq>18</req_seq><operation_seq>" +
					"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" +
					"<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>" +
					"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
					"<message_content><responsesubpackge><col_1>姓名13963456288</col_1><col_2>autotest积分回馈01</col_2>" +
					"<col_3>内置工号-网上客服虚拟工号(pinet001)</col_3><col_4>SD.LP</col_4><col_5>电子缴费卡</col_5><col_6>0</col_6>" +
					"<col_7>0.00</col_7><col_8></col_8><col_9>0.00</col_9><col_10>2009-06-19 14:58:24</col_10></responsesubpackge>" +
					"<responsesubpackge><col_1>姓名13963456288</col_1><col_2>autotest积分回馈01</col_2>" +
					"<col_3>内置工号-网上客服虚拟工号(pinet001)</col_3><col_4>SD.LP</col_4><col_5>电子缴费卡</col_5>" +
					"<col_6>0</col_6><col_7>0.00</col_7><col_8></col_8><col_9>0.00</col_9><col_10>2009-06-19 17:11:06</col_10>" +
					"</responsesubpackge></message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			return new ReturnWrap();
		}
	}

	@Override
	public ReturnWrap queryScoreDetailHis(Map map) {
		try {
			String resp = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
					+ "<menuid>qryScore</menuid><process_code>cli_qry_scroedetail</process_code><verify_code></verify_code>"
					+ "<unicontact></unicontact><resp_time>20120518155720</resp_time><sequence><req_seq>6</req_seq><operation_seq>"
					+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
					+ "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
					+ "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><responsesubpackge><col_1>6340963456288</col_1>"
					+ "<col_2>88009891730539</col_2><col_3>积分浮动</col_3><col_4>-20</col_4><col_5>CRM营业厅</col_5><col_6></col_6><col_7></col_7>"
					+ "<col_8>2012-04-16 16:54:14</col_8><col_9>积分消费</col_9><col_10>-20</col_10><col_11></col_11><col_12></col_12><col_13></col_13>"
					+ "<col_14></col_14><col_15></col_15><col_16>201204</col_16><col_17>13963456288</col_17><col_18>姓名13963456288</col_18><"
					+ "/responsesubpackge><responsesubpackge><col_1>6340963456288</col_1><col_2>88009891729892</col_2><col_3>积分浮动</col_3>"
					+ "<col_4>100</col_4><col_5>CRM营业厅</col_5><col_6></col_6><col_7></col_7><col_8>2012-04-16 15:44:10</col_8><col_9>调整积分增加</col_9>"
					+ "<col_10>100</col_10><col_11></col_11><col_12></col_12><col_13></col_13><col_14></col_14><col_15></col_15><col_16>201205</col_16>"
					+ "<col_17>13963456288</col_17><col_18>姓名13963456288</col_18></responsesubpackge><responsesubpackge><col_1>6340963456288</col_1>"
					+ "<col_2>88009891740313</col_2><col_3>积分浮动</col_3><col_4>5000</col_4><col_5>CRM营业厅</col_5><col_6></col_6><col_7></col_7>"
					+ "<col_8>2012-05-17 11:31:00</col_8><col_9>调整积分增加</col_9><col_10>5000</col_10><col_11></col_11><col_12></col_12><col_13></col_13>"
					+ "<col_14></col_14><col_15></col_15><col_16>201205</col_16><col_17>13963456288</col_17><col_18>姓名13963456288</col_18>"
					+ "</responsesubpackge></message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(resp);
		} catch (Exception e) {
			ReturnWrap rw1 = new ReturnWrap();

			return rw1;
		}
	}

	@Override
	public ReturnWrap queryScorePrizeHis(Map map) {
		// TODO Auto-generated method stub
		try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
					"<menuid>qryScore</menuid><process_code>cli_qry_scoreprize</process_code><verify_code>" +
					"</verify_code><resp_time>20120518160755</resp_time><sequence><req_seq>3</req_seq>" +
					"<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode>" +
					"<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>" +
					"<![CDATA[<message_content><responsesubpackge><col_1>2012-03-01 09:50:50</col_1><col_2>常态化营销活动</col_2>" +
					"<col_3>鄂州</col_3><col_4>系统管理员(101)</col_4></responsesubpackge><responsesubpackge><col_1>2012-03-01 09:50:50</col_1>" +
					"<col_2>常态化营销活动</col_2><col_3>鄂州</col_3><col_4>系统管理员(101)</col_4></responsesubpackge><responsesubpackge>" +
					"<col_1>2012-03-01 09:50:50</col_1><col_2>常态化营销活动</col_2><col_3>鄂州</col_3><col_4>系统管理员(101)</col_4>" +
					"</responsesubpackge></message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
	}
	
	public ReturnWrap qryRecStatusHub(MsgHeaderPO msgHeader, String nCode, String operType)
    {
		try {
			String response = "<message_response><message_head version=\"1.0\"><menuid>recCallDisplay</menuid><process_code>" +
					"cli_qry_recstatus_hub</process_code><verify_code></verify_code><resp_time>20120619185704</resp_time><sequence>" +
					"<req_seq>2</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode>" +
					"<retmsg><![CDATA[GPRS10:1]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content>" +
					"<recinfo><col0>GPRS10</col0><col1>1</col1></recinfo></message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
    }
	
	 /**
     * 产品快速发布- 用户已订购产品信息查询
     */
	public ReturnWrap qryHasProds(MsgHeaderPO msgHeader)
	{    
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>qryServiceHistory</menuid><process_code>cli_qry_rechistory</process_code>"
                + "<verify_code></verify_code><resp_time>20100921002233</resp_time>"
                + "<sequence><req_seq>40</req_seq><operation_seq></operation_seq></sequence><retinfo>"
                + "<rettype>0</rettype><retcode>100</retcode>"
                + "<retmsg><![CDATA[Processing the request succeeded!]]>"
                + "</retmsg></retinfo></message_head>"
                + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                + "<message_content>"
                + "<proditem>" +
                        "<ncode>C06</ncode>" +//对产品包下的子产品或者模板下的子产品，返回“”；其它返回ncode
                        "<prodid>subproductcode001</prodid>" +//对产品包下的子产品或者模板下的子产品，返回产品编码；其它返回“”
                        "<prodname>C06</prodname>" +//产品名称
                        "<attrparam>addarrtid_003=15965613173=#addarrtid_001=DXTCB5</attrparam>" +//附加属性串
                        "<serviceres></serviceres>" +//服务资源串
                        "<recdate>20100303210238</recdate>" +//受理时间，格式： yyyymmddhh24miss
                        "<startdate>20100303210238</startdate>" +//开始时间，格式： yyyymmddhh24miss
                        "<enddate>20100303210238</enddate>" +//结束时间，格式： yyyymmddhh24miss
                        "<state>1</state>" +//状态 0：预约1：正常2：暂停3：已退订

                        "<formnum></formnum>" +//操作流水
                        "<pkgtype></pkgtype>" +//套餐大类
                        "<proddesc></proddesc>" +//产品描述
                        "<doneenum></doneenum>" +//赠送方
                        "<doneerelaid></doneerelaid>" +//赠送关系编码
                        "<pkgname></pkgname>" +//套餐大类名称
                        "<canceldate></canceldate>" +//取消时间，格式： yyyymmddhh24miss
                        "<prodpkgid>C06</prodpkgid>" +//对产品包下的子产品或者模板下的子产品，返回产品包编码或者模板编码；其它返回“”
                        
                  "</proditem>"
                        
                   + "<proditem>" +
                        "<ncode>C0615</ncode>" +//对产品包下的子产品或者模板下的子产品，返回“”；其它返回ncode
                        "<prodid>subproductcode001</prodid>" +//对产品包下的子产品或者模板下的子产品，返回产品编码；其它返回“”
                        "<prodname>C06</prodname>" +//产品名称
                        "<attrparam>addarrtid_003=15965613173=#addarrtid_001=DXTCB5</attrparam>" +//附加属性串
                        "<serviceres></serviceres>" +//服务资源串
                        "<recdate>20100303210238</recdate>" +//受理时间，格式： yyyymmddhh24miss
                        "<startdate>20100303210238</startdate>" +//开始时间，格式： yyyymmddhh24miss
                        "<enddate>20100303210238</enddate>" +//结束时间，格式： yyyymmddhh24miss
                        "<state>1</state>" +//状态 0：预约1：正常2：暂停3：已退订

                        "<formnum></formnum>" +//操作流水
                        "<pkgtype></pkgtype>" +//套餐大类
                        "<proddesc></proddesc>" +//产品描述
                        "<doneenum></doneenum>" +//赠送方
                        "<doneerelaid></doneerelaid>" +//赠送关系编码
                        "<pkgname></pkgname>" +//套餐大类名称
                        "<canceldate></canceldate>" +//取消时间，格式： yyyymmddhh24miss
                        "<prodpkgid></prodpkgid>" +//对产品包下的子产品或者模板下的子产品，返回产品包编码或者模板编码；其它返回“”
                        
                  "</proditem>"
                   
                        
                  + "<proditem>" +
                        "<ncode>C061</ncode>" +//对产品包下的子产品或者模板下的子产品，返回“”；其它返回ncode
                        "<prodid>subproductcode001</prodid>" +//对产品包下的子产品或者模板下的子产品，返回产品编码；其它返回“”
                        "<prodname>C06</prodname>" +//产品名称
                        "<attrparam>addarrtid_003=15965613173=#addarrtid_001=DXTCB5</attrparam>" +//附加属性串
                        "<serviceres></serviceres>" +//服务资源串
                        "<recdate>20100303210238</recdate>" +//受理时间，格式： yyyymmddhh24miss
                        "<startdate>20100303210238</startdate>" +//开始时间，格式： yyyymmddhh24miss
                        "<enddate>20100303210238</enddate>" +//结束时间，格式： yyyymmddhh24miss
                        "<state>1</state>" +//状态 0：预约1：正常2：暂停3：已退订

                        "<formnum></formnum>" +//操作流水
                        "<pkgtype></pkgtype>" +//套餐大类
                        "<proddesc></proddesc>" +//产品描述
                        "<doneenum></doneenum>" +//赠送方
                        "<doneerelaid></doneerelaid>" +//赠送关系编码
                        "<pkgname></pkgname>" +//套餐大类名称
                        "<canceldate></canceldate>" +//取消时间，格式： yyyymmddhh24miss
                        "<prodpkgid></prodpkgid>" +//对产品包下的子产品或者模板下的子产品，返回产品包编码或者模板编码；其它返回“”
                        
                  "</proditem>"
                        
                       
              
                + "</message_content>]]></message_body></message_response>";
        ReturnWrap rw = null;
        try {
            rw = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rw;
    }
    
	/** 
     * 产品快速发布-查询产品附加属性
     * 
     * @param msgHeader 报文头信息
     * @param qryType 查询类型 0：NCODE 1：产品包下产品
     * @param nCode nCode
     * @param operType PCOpRec:开通  PCOpMod:修改 PCOpDel:关闭 
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qryAddAttr(MsgHeaderPO msgHeader, String qryType, String nCode, String operType)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>qryServiceHistory</menuid><process_code>cli_qry_rechistory</process_code>"
                + "<verify_code></verify_code><resp_time>20100921002233</resp_time>"
                + "<sequence><req_seq>40</req_seq><operation_seq></operation_seq></sequence><retinfo>"
                + "<rettype>0</rettype><retcode>100</retcode>"
                + "<retmsg><![CDATA[Processing the request succeeded!]]>"
                + "</retmsg></retinfo></message_head>"
                + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                + "<message_content>"
                
                + "<addattritem>" +
                    "<attrid>addarrtid_003</attrid>" + // 附加属性编码
                    "<attrname>亲情号码1</attrname>" + // 附加属性名称
                    "<dictinfor></dictinfor>" + // 字典信息
                    "<attrvalue>13645319981</attrvalue>" + // 用户订购值（如果用户没有订购就填充默认值）
                    "<ismandatory>1</ismandatory>" + // 是否必须
                    "<isshow>1</isshow>" + // 是否界面展现
                    "<COL_6></COL_6>" + // 产品/优惠/服务
                    "<maxlength>11</maxlength>" + // 附加属性最大 
                    "<attrtype>SINGLE</attrtype>" + // 附加属性类型 MEMO，多行编辑框 SINGLE，单行编辑框 DATE，时间编辑框 SELECT，下拉列表
                    "<valuetype>NUMBER</valuetype>" + // 值类型 NUMBER，数字 STRING，字符串
                    "<minlength>11</minlength>" + // 最小长度
                "</addattritem>" 
                    
               + "<addattritem>" +
                    "<attrid>addarrtid_004</attrid>" + // 附加属性编码
                    "<attrname>亲情号码2</attrname>" + // 附加属性名称
                    "<dictinfor></dictinfor>" + // 字典信息
                    "<attrvalue>13645319982</attrvalue>" + // 用户订购值（如果用户没有订购就填充默认值）
                    "<ismandatory>1</ismandatory>" + // 是否必须
                    "<isshow>1</isshow>" + // 是否界面展现
                    "<COL_6></COL_6>" + // 产品/优惠/服务
                    "<maxlength>11</maxlength>" + // 附加属性最大 
                    "<attrtype>SINGLE</attrtype>" + // 附加属性类型 MEMO，多行编辑框 SINGLE，单行编辑框 DATE，时间编辑框 SELECT，下拉列表
                    "<valuetype>NUMBER</valuetype>" + // 值类型 NUMBER，数字 STRING，字符串
                    "<minlength>11</minlength>" + // 最小长度
              "</addattritem>"
                    
              + "<addattritem>" +
                    "<attrid>addarrtid_005</attrid>" + // 附加属性编码
                    "<attrname>开通日期</attrname>" + // 附加属性名称
                    "<dictinfor></dictinfor>" + // 字典信息
                    "<attrvalue></attrvalue>" + // 用户订购值（如果用户没有订购就填充默认值）
                    "<ismandatory>1</ismandatory>" + // 是否必须
                    "<isshow>1</isshow>" + // 是否界面展现
                    "<COL_6></COL_6>" + // 产品/优惠/服务
                    "<maxlength></maxlength>" + // 附加属性最大 
                    "<attrtype>DATE</attrtype>" + // 附加属性类型 MEMO，多行编辑框 SINGLE，单行编辑框 DATE，时间编辑框 SELECT，下拉列表
                    "<valuetype>NUMBER</valuetype>" + // 值类型 NUMBER，数字 STRING，字符串
                    "<minlength></minlength>" + // 最小长度
              "</addattritem>"
                    
             + "<addattritem>" +
                    "<attrid>addarrtid_010</attrid>" + // 附加属性编码
                    "<attrname>亲情号码3</attrname>" + // 附加属性名称
                    "<dictinfor></dictinfor>" + // 字典信息
                    "<attrvalue>13645319981</attrvalue>" + // 用户订购值（如果用户没有订购就填充默认值）
                    "<ismandatory>1</ismandatory>" + // 是否必须
                    "<isshow>0</isshow>" + // 是否界面展现
                    "<COL_6></COL_6>" + // 产品/优惠/服务
                    "<maxlength>11</maxlength>" + // 附加属性最大 
                    "<attrtype>SINGLE</attrtype>" + // 附加属性类型 MEMO，多行编辑框 SINGLE，单行编辑框 DATE，时间编辑框 SELECT，下拉列表
                    "<valuetype>NUMBER</valuetype>" + // 值类型 NUMBER，数字 STRING，字符串
                    "<minlength>11</minlength>" + // 最小长度
                "</addattritem>" 
                    
                + "<addattritem>" +
                        "<attrid>addarrtid_001</attrid>" + // 附加属性编码
                        "<attrname>亲情城市</attrname>" + // 附加属性名称
                        "<dictinfor>" +
                            "0531=济南|0532=青岛|0533=淄博|0534=潍坊|0536=聊城|0635=莱芜" +
                        "</dictinfor>" + // 字典信息
                        "<attrvalue>0531</attrvalue>" + // 用户订购值（如果用户没有订购就填充默认值）
                        "<ismandatory>1</ismandatory>" + // 是否必须
                        "<isshow>1</isshow>" + // 是否界面展现
                        "<COL_6></COL_6>" + // 产品/优惠/服务
                        "<maxlength></maxlength>" + // 附加属性最大 
                        "<attrtype>SELECT</attrtype>" + // 附加属性类型 MEMO，多行编辑框 SINGLE，单行编辑框 DATE，时间编辑框 SELECT，下拉列表
                        "<valuetype>STRING</valuetype>" + // 值类型 NUMBER，数字 STRING，字符串
                        "<minlength></minlength>" + // 最小长度
                  "</addattritem>"
                         
              
                + "</message_content>]]></message_body></message_response>";
        ReturnWrap rw = null;
        try {
            rw = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rw;
    }
    
    /** 
     * 产品快速发布-产品受理
     * 
     * @param msgHeader MsgHeaderPO
     * @param multiProdCommitPO MultiProdCommitPO
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap prodRec(MsgHeaderPO msgHeader, MultiProdCommitPO multiProdCommitPO)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>cli_busi_prodrec</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                + "</message_content>]]></message_body></message_response>";

        try {
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);

            return rw;
        }
    }
    
    /** 
     * 产品快速发布-查询产品包下子产品
     * 
     * @param msgHeader 报文头信息
     * @param nCode 产品包编码、模板ID
     * @param type 类型：2 产品包 3 模板
     * @param optType 操作类型
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap qrySubProds(MsgHeaderPO msgHeader, String nCode, String type, String optType)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>qryServiceHistory</menuid><process_code>cli_qry_subProds</process_code>"
                + "<verify_code></verify_code><resp_time>20100921002233</resp_time>"
                + "<sequence><req_seq>40</req_seq><operation_seq></operation_seq></sequence><retinfo>"
                + "<rettype>0</rettype><retcode>100</retcode>"
                + "<retmsg><![CDATA[Processing the request succeeded!]]>"
                + "</retmsg></retinfo></message_head>"
                + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                + "<message_content>"
                
                + "<minprod>2</minprod>" // 可选产品最小数
                + "<maxprod>2</maxprod>" // 可选最大产品数
                + "<productlist>" 
                    + "<pkgid>pkg001</pkgid>"
                    + "<prodid>subproductcode001</prodid>" // 增值产品编码
                    + "<privid></privid>" // 优惠编码
                    + "<prodname>子产品001</prodname>" // 产品名称
                    + "<ismandatory>0</ismandatory>" // 选择类型（可选和必选） 0:可选 1:必选
                    + "<hasattr>1</hasattr>"  // 是否有附加属性
                    + "<hasattr>PCIntRelaNormal</hasattr>" // 普通业务：PCIntRelaNormal 业务切换:PCIntReladio等
                + "</productlist>"
                
                // 产品包下的子产品（subproductcode002）
                + "<productlist>" 
                    + "<pkgid>pkg001</pkgid>"// 增值产品编码
                    + "<prodid>subproductcode002</prodid>" // 产品编码
                    + "<privid></privid>" // 优惠编码
                    + "<prodname>子产品002</prodname>" // 产品名称
                    + "<ismandatory>0</ismandatory>" // 选择类型（可选和必选） 0:可选 1:必选
                    + "<hasattr>0</hasattr>"  // 是否有附加属性
                    + "<hasattr>PCIntRelaNormal</hasattr>" // 普通业务：PCIntRelaNormal 业务切换:PCIntReladio等
                + "</productlist>"
                // 子产品下的优惠（favourableCode002_001）
                + "<productlist>" 
                    + "<pkgid>pkg001</pkgid>"// 增值产品编码
                    + "<prodid>subproductcode002</prodid>" // 产品编码
                    + "<privid>favourableCode002_001</privid>" // 优惠编码
                    + "<prodname>优惠001</prodname>" // 产品名称
                    + "<ismandatory>0</ismandatory>" // 选择类型（可选和必选） 0:可选 1:必选
                    + "<hasattr>1</hasattr>"  // 是否有附加属性
                    + "<hasattr>PCIntRelaNormal</hasattr>" // 普通业务：PCIntRelaNormal 业务切换:PCIntReladio等
                + "</productlist>"
                // 子产品下的优惠（favourableCode002_002）
                + "<productlist>" 
                    + "<pkgid>pkg001</pkgid>"// 增值产品编码
                    + "<prodid>subproductcode002</prodid>" // 产品编码
                    + "<privid>favourableCode002_002</privid>" // 优惠编码
                    + "<prodname>优惠002</prodname>" // 产品名称
                    + "<ismandatory>0</ismandatory>" // 选择类型（可选和必选） 0:可选 1:必选
                    + "<hasattr>0</hasattr>"  // 是否有附加属性
                    + "<hasattr>PCIntRelaNormal</hasattr>" // 普通业务：PCIntRelaNormal 业务切换:PCIntReladio等
                + "</productlist>"
                
                + "<productlist>" 
                    + "<pkgid>pkg001</pkgid>"// 增值产品编码
                    + "<prodid>subproductcode003</prodid>" // 产品编码
                    + "<privid></privid>" // 优惠编码
                    + "<prodname>子产品003</prodname>" // 产品名称
                    + "<ismandatory>1</ismandatory>" // 选择类型（可选和必选） 0:可选 1:必选
                    + "<hasattr>1</hasattr>"  // 是否有附加属性
                    + "<hasattr>PCIntRelaNormal</hasattr>" // 普通业务：PCIntRelaNormal 业务切换:PCIntReladio等
                    
                + "</productlist>"
                + "</message_content>]]></message_body></message_response>";
        ReturnWrap rw = null;
        try {
            rw = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rw;
    }

    /**
     * 根据ncode查询特别提示信息
     * @param paramMap
     * @return
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public ReturnWrap qryNcodeTips(Map<String, String> paramMap)
    {
        return null;
    }
    
    /**
     * 根据对象编码查询特别提示信息
     * @param paramMap
     * @return
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public ReturnWrap qryObjectTips(Map<String, String> paramMap, List<Map<String, String>> prods)
    {
    	return null;
    }
    
    /**
     * 查询需要预约号码（山东）
     * @param map
     * @return
     * @remark create cKF76106 2013/01/23 R003C13L01n01 OR_SD_201301_279
     */
    public ReturnWrap qryChooseTelSD(Map map)
    {
        return null;
    }
    
    /**
     * 话费总额查询
     * @param map
     * @return
     * @remark create g00140516 2013/02/22 R003C13L02n01 OR_NX_201302_600
     */
    public ReturnWrap qryCurrBillFee(Map<String, String> map)
    {    	
    	try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
					"<menuid>qryBalance</menuid><process_code>cli_qry_totalfee</process_code><verify_code></verify_code>" +
					"<resp_time>20130222103846</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence>" +
					"<retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[]]></retmsg></retinfo></message_head>" +
					"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><currbillfee>0.00</currbillfee>" +
					"</message_content>]]></message_body></message_response>";
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rw = new ReturnWrap();
			rw.setStatus(0);
			rw.setReturnMsg("");

			return rw;
		}
    }
    
    /**
     * 检查详单打印是否超出次数限制
     * 
     * @param map
     * @return
     * @throws Exception
     * @remark create cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
     */
    public ReturnWrap queryPrintCdr(Map<String, String>  map)
    {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            		"<menuid></menuid><process_code>cli_qry_printcdr</process_code><verify_code></verify_code><resp_time>20130326174402</resp_time>" +
            		"<sequence><req_seq>14</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype>" +
            		"<retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");

            return rw;
        }
    }
    
    
    /**
     * 更新详单打印次数
     * 
     * @param map
     * @return
     * @throws Exception
     * @remark create cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
     */
    public ReturnWrap writePrintCdrLog(Map<String, String>  map)
    {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            		"<menuid></menuid><process_code>cli_busi_writeprintcdrlog</process_code><verify_code></verify_code>" +
            		"<resp_time>20130326174045</resp_time><sequence><req_seq>5</req_seq><operation_seq></operation_seq>" +
            		"</sequence><retinfo><rettype>0</rettype><retcode>100</retcode>" +
            		"<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
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
    public ReturnWrap sendSmsHub(Map map) {
        ReturnWrap rw = new ReturnWrap();
        rw.setStatus(SSReturnCode.SUCCESS);

        return rw;
    }
	
	/**
     * 校验手机号是否已实名制登记
     */
    public ReturnWrap realNameCheck(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>cli_qry_userinfo</process_code><verify_code>verifyCode</verify_code>"
                + "<unicontact>unicontact</unicontact><resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><ret>0</ret>"
                + "</message_content>]]></message_body></message_response>";

        ReturnWrap rw = null;
        try {
            rw = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rw;
    }
    
    /**
     * 重置密码（新）（宁夏）
     * 
     * @param map
     * @return ReturnWrap
     * @remark create by hWX5316476 2014/2/18 OR_NX_201402_306 宁夏自助终端优化需求_弱密码改造需求
     */
    public ReturnWrap resetPwdNew(Map map)
    {
		String subcmdid = (String)map.get("subcmdid");
		
		String response = "";
		if("0".equals(subcmdid))
		{
			response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+"<menuid></menuid><process_code>cli_busi_pwdresetnew_nx</process_code><verify_code></verify_code>"
				+"<resp_time>20140222092348</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq>"
				+"</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
				+"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
				+"<message_content><maxfailnum></maxfailnum><curfailnum></curfailnum><leftfailnum></leftfailnum><remindflag>2"
				+"</remindflag></message_content>]]></message_body></message_response>";
		}
		else if("1".equals(subcmdid))
		{
			response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+"<menuid>recChangePwd</menuid><process_code>cli_busi_pwdresetnew_nx</process_code><verify_code>"
				+"</verify_code><resp_time>20140222093020</resp_time><sequence><req_seq>19</req_seq><operation_seq>"
				+"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
				+"<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
				+"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><maxfailnum></maxfailnum>"
				+"<curfailnum></curfailnum><leftfailnum></leftfailnum><remindflag></remindflag></message_content>]]>"
				+"</message_body></message_response>";
		}
		else if("2".equals(subcmdid))
		{
			response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+"<menuid>recPasswordReset</menuid><process_code>cli_busi_pwdresetnew_nx</process_code><verify_code>"
				+"</verify_code><resp_time>20140222092137</resp_time><sequence><req_seq>6</req_seq><operation_seq></operation_seq>"
				+"</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
				+"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
				+"<message_content><maxfailnum></maxfailnum><curfailnum></curfailnum><leftfailnum></leftfailnum><remindflag>"
				+"</remindflag></message_content>]]></message_body></message_response>";
		}

	    ReturnWrap rw = null;
	    try {
	        rw = intMsgUtil.parseResponse(response);
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	
	    return rw;
    }
    
    public ReturnWrap sendRecordsMail(Map map){
    	
        ReturnWrap rw = new ReturnWrap();
        rw.setStatus(SSReturnCode.SUCCESS);

        return rw;
    }
    
    /**
     * 判断用户是否开通积分计划 山东
     * 
     * @param map
     * @return ReturnWrap
     * @remark create by sWX219697 2014/5/12 OR_SD_201404_777_山东_网厅、自助终端、掌厅__全渠道积分查询及兑换功能
     */
    public ReturnWrap qryIsScoreOpen(Map map)
    {
    	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
			+"<menuid>recPasswordReset</menuid><process_code>cli_busi_pwdresetnew_nx</process_code><verify_code>"
			+"</verify_code><resp_time>20140222092137</resp_time><sequence><req_seq>6</req_seq><operation_seq></operation_seq>"
			+"</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
			+"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
			+"<message_content><result>0</result>"
			+"</message_content>]]></message_body></message_response>";
	    
    	ReturnWrap rw = null;
	    try {
	        rw = intMsgUtil.parseResponse(response);
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return rw;
    }
    
    /**
     * 家庭网取消业务接口(山东)
     * 
     * @param map
     * @return 接口处理结果
     * @remark add begin wWX217192 on 20140603 for OR_huawei_201405_875
     */
    @SuppressWarnings("unchecked")
	public ReturnWrap deleteFamilyMem(Map<String, String> map)
    {
    	// 正确的响应报文
    	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>"
    		+ "<message_response><message_head version=\"1.0\">"
    		+ "<menuid>recAddFamilyMem</menuid>"
    		+ "<process_code>cli_delete_familymem_sd</process_code>"
    		+ "<verify_code></verify_code><resp_time>20140606143318</resp_time>"
    		+ "<sequence><req_seq>2</req_seq><operation_seq></operation_seq></sequence>"
    		+ "<retinfo><rettype>0</rettype><retcode>100</retcode>"
    		+ "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
    		+ "</message_head></message_response>";
    	
    	// 主号开通多个家庭网
    	/*String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>"
    		+ "<message_response><message_head version=\"1.0\">"
    		+ "<menuid>recAddFamilyMem</menuid><process_code>cli_delete_familymem_sd</process_code>"
    		+ "<verify_code></verify_code><resp_time>20140605172333</resp_time><sequence><req_seq>2</req_seq><operation_seq>"
    		+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>934382</retcode>"
    		+ "<retmsg><![CDATA[业务办理失败，失败原因:该主号开通了多个家庭网，无法销户！]]></retmsg>"
    		+ "</retinfo></message_head></message_response>";*/
    	
    	// 用户未开通业务
    	/*String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
    			"<message_response><message_head version=\"1.0\">" +
    			"<menuid>recAddFamilyMem</menuid>" +
    			"<process_code>cli_delete_familymem_sd</process_code>" +
    			"<verify_code></verify_code><resp_time>20140606150137</resp_time>" +
    			"<sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence>" +
    			"<retinfo><rettype>0</rettype><retcode>112</retcode>" +
    			"<retmsg><![CDATA[用户未开通业务]]></retmsg></retinfo>" +
    			"</message_head></message_response>";*/
    	
    	ReturnWrap rw = null;
    	try
    	{
    		rw = intMsgUtil.parseResponse(response);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return rw;
    }

    /**
     * 积分发放查询（山东）
     * @param header 报文头信息
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     * @remark create by jWX216858 2014-10-20 R003C10LG1001 OR_SD_201407_498_山东移动促销回馈积分查询方案支撑需求
     */
    @Override
    public ReturnWrap qryPayMentScoreSD(MsgHeaderPO header, String startDate,
    		String endDate) {
    	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
    		+ "<menuid>qryScore</menuid><process_code>cli_qry_paymentSocre</process_code><verify_code></verify_code>" 
    		+ "<resp_time>20141022091842</resp_time><sequence><req_seq>4</req_seq><operation_seq></operation_seq>"
    		+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo>"
    		+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
    		+ "<resultList><payTime>2014-10-21 11:31:22</payTime><scoretypeid>02</scoretypeid><scoreValue>100</scoreValue>"
    		+ "<invalidTime></invalidTime><reason>调整积分增加</reason><reasonType>50</reasonType></resultList>" 
    		+ "<resultList><payTime>2014-10-21 11:32:47</payTime><scoretypeid>02</scoretypeid><scoreValue>200</scoreValue>"
    		+ "<invalidTime></invalidTime><reason>调整积分增加</reason><reasonType>50</reasonType></resultList></message_content>]]></message_body></message_response>";
	    
    	ReturnWrap rw = null;
	    try {
	        rw = intMsgUtil.parseResponse(response);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return rw;
    }
    
    /**
     * 积分查询（山东）
     * @param header 报文头信息
     * @return
     * @remark create by jWX216858 2014-10-20 R003C10LG1001 OR_SD_201407_498_山东移动促销回馈积分查询方案支撑需求
     */
    @Override
    public ReturnWrap qryScoreValueSD(MsgHeaderPO header)
    {
    	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
    		+ "<menuid>qryScore</menuid><process_code>cli_qry_scorevalueSD</process_code><verify_code></verify_code>" 
    		+ "<resp_time>20141022091147</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq>"
    		+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" 
    		+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" 
    		+ "<pointBalance>300</pointBalance><userBrand>BrandGotone</userBrand><userLevel>VC1400</userLevel>" 
    		+ "<userStatus>US10</userStatus><resultList><col_0>02</col_0><col_1>300</col_1><col_2></col_2>" 
    		+ "</resultList></message_content>]]></message_body></message_response>";
	    
    	ReturnWrap rw = null;
	    try {
	        rw = intMsgUtil.parseResponse(response);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return rw;
    }
    
    /**
     * 删除家庭网成员
     * @param header 请求报文头
     * @param memTelnum 成员手机号
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap delMemByTelnum(MsgHeaderPO header, String memTelnum)
    {
        return null;
    }
    
    /**
	 * 查询可预约号码列表
	 * @param requestMsg
	 * @return
	 * @remark create by wWX217192 2015-03-10 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
	 */
	public String queryNumResp(OMElement requestMsg)
	{
		String responseMsg = "<queryNum xmlns='http://num.webservice.emall.huawei.com'>"
			+ "<message>"
			+ "<returnCode>100</returnCode>"
			+ "<numList>"
			+ "<numInfo>"
			+ "<telnum>15806619824</telnum>"
			+ "<minCost>0</minCost>"
			+ "<preFee>50</preFee>"
			+ "<signTime></signTime>"
			+ "</numInfo>"
			+ "<numInfo>"
			+ "<telnum>15806682724</telnum>"
			+ "<minCost>0</minCost>"
			+ "<preFee>50</preFee>"
			+ "<signTime></signTime>"
			+ "</numInfo>"
			+ "<numInfo>"
			+ "<telnum>15806401124</telnum>"
			+ "<minCost>0</minCost>"
			+ "<preFee>50</preFee>"
			+ "<signTime></signTime>"
			+ "</numInfo>"
			+ "<numInfo>"
			+ "<telnum>15806622024</telnum>"
			+ "<minCost>0</minCost>"
			+ "<preFee>50</preFee>"
			+ "<signTime></signTime>"
			+ "</numInfo>"
			+ "<numInfo>"
			+ "<telnum>15806615424</telnum>"
			+ "<minCost>0</minCost>"
			+ "<preFee>50</preFee>"
			+ "<signTime></signTime>"
			+ "</numInfo>"
			+ "<numInfo>"
			+ "<telnum>15806613124</telnum>"
			+ "<minCost>0</minCost>"
			+ "<preFee>50</preFee>"
			+ "<signTime></signTime>"
			+ "</numInfo>"
			+ "<numInfo>"
			+ "<telnum>15805419124</telnum>"
			+ "<minCost>0</minCost>"
			+ "<preFee>50</preFee>"
			+ "<signTime></signTime>"
			+ "</numInfo>"
			+ "<numInfo>"
			+ "<telnum>15806682024</telnum>"
			+ "<minCost>0</minCost>"
			+ "<preFee>50</preFee>"
			+ "<signTime></signTime>"
			+ "</numInfo>"
			+ "<numInfo>"
			+ "<telnum>15806649424</telnum>"
			+ "<minCost>0</minCost>"
			+ "<preFee>50</preFee>"
			+ "<signTime></signTime>"
			+ "</numInfo>"
			+ "<numInfo>"
			+ "<telnum>15806697524</telnum>"
			+ "<minCost>0</minCost>"
			+ "<preFee>50</preFee>"
			+ "<signTime></signTime>"
			+ "</numInfo>"
			+ "</numList>"
			+ "</message>"
			+ "</queryNum>";
		
		return responseMsg;
	}
	
	/**
	 * 暂选号码
	 * @param requestMsg
	 * @return
	 * @remark create by wWX217192 2015-03-10 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
	 */
	public String pickNumResp(OMElement requestMsg)
	{
		String responseMsg = "<pickNum xmlns=\"http://service.prp.campaign.huawei.com\">"
			+ "<message>"
			+ "<returnCode>100</returnCode>"
			+ "<vprodId>15806619824</vprodId>"
			+ "<errorMsg>0</errorMsg>"
			+ "</message>"
			+ "</pickNum>";
		
		return responseMsg;
	}
	
	/**
	 * 预约号码
	 * @param requestMsg
	 * @return
	 * @remark create by wWX217192 2015-03-10 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求
	 */
	public String bookNumResp(OMElement requestMsg)
	{
		String responseMsg = "<bookNum xmlns=\"http://service.prp.campaign.huawei.com\">"
			+ "<message>"
			+ "<returnCode>100</returnCode>"
			+ "<errorMsg>0</errorMsg>"
			+ "</message>"
			+ "</bookNum>";
		
		return responseMsg;
	}
	
    /**
     * 查询客户应缴总金额
     * @param msgHeader
     * @param orgid
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-3-24 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
     */
    public ReturnWrap qryPayAmount(MsgHeaderPO msgHeader, String orgid)
    {
        try
        {
            String responseSuc = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            		"<menuid>nonlocalCharge</menuid><process_code>cli_qry_userPayAmount</process_code><verify_code></verify_code>" +
            		"<resp_time>20150415135438</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence>" +
            		"<retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[success]]></retmsg></retinfo></message_head>" +
            		"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><ProvinceCode>5310</ProvinceCode>" +
            		"<IDType>01</IDType><IDValue>15966670507</IDValue><BizOrderResult>0000</BizOrderResult><ResultDesc>success</ResultDesc>" +
            		"<CustName>喀咯发</CustName><PayAmount>30000</PayAmount><Balance>10000</Balance></message_content>]]>" +
            		"</message_body></message_response>";
            ReturnWrap rw = intMsgUtil.parseResponse(responseSuc);
            return rw;
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"查询客户应缴总金额异常！");
        }
        
    }
    
    /**
     * 异地缴费
     * <功能详细描述>
     * @param msgHeader
     * @param seq 规则操作流水号
     * @param actualPayAmount 实际缴费金额（厘）
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-3-24 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
     */
    public ReturnWrap nonlocalCharge(MsgHeaderPO msgHeader, String seq, String actualPayAmount, String orgid)
    {
        try
        {
            String responseSuc = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>" +
            		"nonlocalCharge</menuid><process_code>cli_busi_nonlocalCharge</process_code><verify_code></verify_code>" +
            		"<resp_time>20150415144928</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq>" +
            		"</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" +
            		"</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><IDType>" +
            				"01</IDType><IDValue>15054183395</IDValue><BizOrderResult>0000</BizOrderResult><ResultDesc></ResultDesc>" +
            				"</message_content>]]></message_body></message_response>";
            
            ReturnWrap rw = intMsgUtil.parseResponse(responseSuc);
            return rw;
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"异地缴费异常！");
        }
    }
    
    /**
	 * 山东梦网订购业务
	 * @param header
	 * @param spid
	 * @param bizid
	 * @return
	 * @remark create by wWX217192 2015-04-02 OR_SD_201502_373_山东_关于自助终端承载和娱乐包新业务的支撑需求
	 */
	public ReturnWrap orderSP(MsgHeaderPO header, String spid, String bizid)
    {
        try
        {
        	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
        		+ "<message_head version=\"1.0\">"
        		+ "<menuid>recOrderSP</menuid>"
        		+ "<process_code>cli_sd_ChangeSubsMonServ</process_code>"
        		+ "<verify_code></verify_code>"
        		+ "<resp_time>20150409113534</resp_time>"
        		+ "<sequence>"
        		+ "<req_seq>3</req_seq>"
        		+ "<operation_seq></operation_seq>"
        		+ "</sequence>"
        		+ "<retinfo>"
				+ "<rettype>0</rettype>"
				+ "<retcode>0</retcode>"
				+ "<retmsg>"
				+ "<![CDATA[]]>"
				+ "</retmsg>"
				+ "</retinfo>"
				+ "</message_head>"
				+ "</message_response>";
            
            ReturnWrap rw = null;
    	    try {
    	        rw = intMsgUtil.parseResponse(response);
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    return rw;
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "家庭网成员删除异常");
        }
    }
	
	/**
	 * 湖北有价卡购买
	 * @param header
	 * @param inParam
	 * @return
	 * @remark create by wWX217192 2015-05-13 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
	 */
	public ReturnWrap getValueCards(MsgHeaderPO header, Map<String, String> inParam)
	{
		String response = "{transActionID:'1234567890978',crmFormNum:'54321678907',cardList:[" +
				"{cardNo:'111111111111',cardPwd:'1111111111111',elecCardCntTotal:'30元',cardDate:'2015-06-01',CardType:'话费充值卡',CardBusiPro:'30元话费'}," +
				"{cardNo:'222222222222',cardPwd:'2222222222222',elecCardCntTotal:'30元',cardDate:'2015-06-01',CardType:'话费充值卡',CardBusiPro:'30元话费'}," +
				"{cardNo:'333333333333',cardPwd:'3333333333333',elecCardCntTotal:'30元',cardDate:'2015-06-01',CardType:'话费充值卡',CardBusiPro:'30元话费'}]}";
        
        return intMsgUtil.parseJsonResponse(response, null, null);
	}
	
    /**
     * <有价卡校验>
     * <功能详细描述>
     * @param msgHeader 消息头
     * @param paramMap 消息体
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-05-11 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     */
    public ReturnWrap authValueCard(MsgHeaderPO msgHeader, Map<String, String> paramMap)
    {
        return null;
    }
    
    /**
     * <有价卡充值>
     * <功能详细描述>
     * @param msgHeader 消息头
     * @param paramMap 消息体
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-05-11 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     */
    public ReturnWrap valueCardCharge(MsgHeaderPO msgHeader, Map<String, String> paramMap)
    {
        return null;
    }
    
    /**
     * <山东积分兑换电子券>
     * <功能详细描述>
     * @param header
     * @param doc
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-5-29 14:44:35 OR_SD_201505_61自助终端上增加积分兑换电子券
     */
    public ReturnWrap scoreExchange(MsgHeaderPO header, Document doc)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
            + "<menuid>qryScore</menuid><process_code>cli_busi_scoreExchangeSD</process_code><verify_code></verify_code>" 
            + "<resp_time>20141022091147</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq>"
            + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" 
            + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" 
            + "<recoid>1113699787498</recoid></message_content>]]></message_body></message_response>";
        
        ReturnWrap rw = null;
        try {
            rw = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rw;
    }

    @Override
    public ReturnWrap valueCardCharge(MsgHeaderPO msgHeader, String valueCardPwd)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * <短信发送app下载地址>
     * <功能详细描述>
     * @param header
     * @param doc
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-7-7 OR_SD_201506_152_山东_在自助终端增加“热门APP应用”下载
     */
    @Override
	public ReturnWrap sendAddress(MsgHeaderPO header, Document doc) 
    {
    	ReturnWrap rw = new ReturnWrap();
        rw.setStatus(SSReturnCode.SUCCESS);

        return rw;
	}
    
    /**
     * <用户登录验证>
     * <功能详细描述>
     * @param header
     * @param doc
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-7-10 OR_SD_201506_152_山东_在自助终端增加“热门APP应用”下载
     */
    public ReturnWrap customerLogin(MsgHeaderPO header, Document doc)
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
			rw.setReturnMsg("");

			return rw;
		}
    }

	@Override
	public ReturnWrap getIs4GCard(MsgHeaderPO msgHeader)
	{
		return null;
	}

	@Override
	public ReturnWrap getAvailIntegralEbus(MsgHeaderPO msgHeader)
	{
		return null;
	}
}
