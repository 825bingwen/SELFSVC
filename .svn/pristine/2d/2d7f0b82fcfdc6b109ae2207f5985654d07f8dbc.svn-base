/*
 * 文件名：Constants.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增，常量类
 */
package com.gmcc.boss.selfsvc.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 * 
 * @author g00140516
 * 
 */
public class Constants extends ConstantsBase
{
    /**
     * 积分查询湖北表头
     */
    private static final String[] SCORE_TITLE = {"本年度积分","上年度总积分", "前年度总积分", "总积分"};
    
    public static String[] getScoreTitle() {
		return SCORE_TITLE;
	}

	/**
     * GSM清单显示字段
     * 
     */
    private final static Object[] GSM_SHOW = {
        new String[] {"【通话详单】"},
        new String[] {"序号", "对方号码", "话单类型", "话单产生地", "通话开始时间", "通话时长(秒)", "基本费(元)", "长途费(元)", "声讯信息费(元)", "总话费(元)"},
        new String[] {"详单条数：", "时长合计：", "费用合计(元)："}, new String[] {"recordcount", "totaltimes", "totalfee"}};
    
    
    
    /**
     * 短信详单显示字段
     * 
     */
    private final static Object[] SMS_SHOW = {new String[] {"【短信详单】"},
        new String[] {"序号", "消息类型", "源地址", "目的地址", "处理时间", "消息长度", "费用(元)"}, new String[] {"详单条数：", "费用合计(元)："},
        new String[] {"recordcount", "totalfee"}};
    
    /**
     * 梦网详单显示字段
     * 
     */
    private final static Object[] ISMG_SHOW = {new String[] {"【移动梦网详单】"},
        new String[] {"序号", "服务提供商", "按条计收信息费(元)", "包月费(元)", "业务代码", "企业代码", "产生时间", "服务代码"},
        new String[] {"详单条数：", "费用合计(元)："}, new String[] {"recordcount", "totalfee1"}};
    
    /**
     * GPRS详单显示字段
     * 
     */
    private final static Object[] GPRS_SHOW = {
        new String[] {"【GPRS详单】"},
        new String[] {"序号", "网络标识", "起始时间", "时长", "正常时段上行数据量", "正常时段下行数据量", "正常时段时长", "优惠时段上行数据量", "优惠时段下行数据量",
                "优惠时段时长", "费用"},
        new String[] {"详单条数：", "上行总数据量(K)：", "下行总数据量(K)："},
        new String[] {"recordcount", "totalgprs1", "totalgprs2"},
        new String[] {"1、数据量单位如不特别指明，为字节；费用单位为元；时长单位为秒",
                "2、每条话单的计费数据量为(正常时段上行数据量+正常时段下行数据量)除以1024后的KB数，其中不足1KB按照1KB算"}};
    
    /**
     * WLAN详单显示字段
     * 
     */
    private final static Object[] WLAN_SHOW = {new String[] {"【WLAN详单】"},
        new String[] {"序号", "清单产生地", "认证方式", "产生时间", "时长(秒)", "上行流量(字节)", "下行流量(字节)", "费用(元)"},
        new String[] {"详单条数：", "时长合计：", "费用合计(元)："}, new String[] {"recordcount", "totaltimes", "totalfee"}};
    
    /**
     * 彩信详单显示字段
     * 
     */
    private final static Object[] MMS_SHOW = {new String[] {"【彩信详单】"},
        new String[] {"话单类型", "业务类型", "发送时间", "发送方地址", "对方号码", "信息长度", "通信费(元)", "信息费(元)"},
        new String[] {"详单条数：", "通信费合计：", "信息费合计："}, new String[] {"recordcount", "totalfee1", "totalfee2"}};
    
    /**
     * 代收信息费详单显示字段
     * 
     */
    private final static Object[] INFOFEE_SHOW = {new String[] {"【代收信息费详单】"},
        new String[] {"对方号码", "话单产生地", "通话开始时间", "时长", "小计(元)"}, new String[] {"详单条数：", "时长合计：", "费用合计："},
        new String[] {"recordcount", "totaltimes", "totalfee"}};
    
    /**
     * VPMN详单显示字段
     * 
     */
    private final static Object[] VPMN_SHOW = {new String[] {"【VPMN详单】"},
        new String[] {"通话类型", "对方号码", "通话起始时间", "通话时长", "话单产生地", "基本通话费(元)", "长途费(元)", "漫游费(元)", "小计(元)", "第三方号码"},
        new String[] {"详单条数：", "时长合计：", "费用合计(元)："}, new String[] {"recordcount", "totaltimes", "totalfee"}};
    
    /**
     * 清单所有后台服务接口名数组，接口名在数组中的位置和清单界面上按什么键(即和type参数值)一一对应
     */
    private static final String[] TYPE_SERVICE_NAME_ARRAY = {"ALL", "GSMCDR", "SMSCDR", "IMSGCDR", "GPRSCDR", "WLANCDR"};
    
    /**
     * 清单名称。在数组中的位置和清单界面上按什么键(即和type参数值)一一对应
     */
    private static final String[] TYPE_NAME_ARRAY = {"全部清单", "通话清单", "短信清单", "移动梦网清单", "GPRS清单", "WLAN清单"};
    
    private static final Object[] TYPE_TABLE_DETAIL = {"", GSM_SHOW, SMS_SHOW, ISMG_SHOW, GPRS_SHOW, WLAN_SHOW};
    
    // 详单打印字段
    // 凭条打印一行最多48个字节，每个汉字二个字节
    private static final String[] TYPE_TITLES = {
        "类型   对方号码     起始时间     时长  话费 地点",
        "源地址         目的地址        处理时间     费用", 
        "服务提供商            产生时间    信息费  包月费",
        "网络标识     时长    上行流量    下行流量   费用", 
        "地点       产生时间     时长   上行   下行  费用"};
    
    private static final String[] pluginIdArray = {"prtpluginid", "invprtpluginid", "keybrdpluginid", "cashpluginid",
        "cardpluginid", "mgrpluginid", "simcardpluginid", "writecardpluginid", "idcardpluginid", "unionpluginid", "pursepluginid",
        "sellgoodspluginid"};
    
    private static final String[] pluginKeyArray = {"prtpluginflag", "invprtpluginflag", "keybrdpluginflag",
        "cashpluginflag", "cardpluginflag", "mgrpluginflag", "simcardpluginidflag", "writecardpluginflag", "idcardpluginflag", "unionpluginflag",
        "pursepluginflag", "sellgoodspluginflag"};
    
    // /*******************************************************************************************************************
    // * ****************** 缴 费 ********************************************
    // ******************************************************************************************************************/
    /** 银联卡缴费 */
    public static final String PAY_BYCARD = "SH_PAYBYCARD";
    
    /**
     * 现金缴费
     * 
     */
    public static final String PAY_BYCASH = "SH_PAYBYCASH";
    
    //add begin g00140516 2012/01/07 R003C11L12n01 bug 18636
    public static final int MENU_CENTER_MAX = 4;
    //add end g00140516 2012/01/07 R003C11L12n01 bug 18636
    
    /**
     * LBS详单显示字段
     * 
     */
    private final static Object[] LBS_SHOW = {
        new String[] {"【LBS详单】"},
        new String[] {"计费用户", "应用类型", "定位发起用户的号码", "被定位方号码", "SP企业代码", "SP服务代码 ", "SP业务代码", "服务类型", "计费方式",
                "起始定位时间", "终止定位时间", "定位费用(元)"}, new String[] {"详单条数：", "费用合计(元)："},
        new String[] {"recordcount", "totalfee"}};
    
    // add begin g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
    /**
     * 套餐与固定费
     */
    private static final String[] CDR_FIXFEE_SHOW = {"套餐及固定费详单", "扣费日期,使用周期,套餐及固定费名称,费用(元)"};
    
    /**
     * 通话详单
     */
    private static final String[] CDR_GSM_SHOW = {"通话详单", "时间,地区,类型,对方号码,时长,金额(元)"};
        
    /**
     * 短彩信详单
     */
    private static final String[] CDR_SMS_SHOW = {"短彩信详单", "时间,地区,对方号码,类型,业务代码,金额(元)"};
    
    /**
     * 上网详单
     */
    private static final String[] CDR_GPRSWLAN_SHOW = {"上网详单", "时间,地区,方式,时长,流量(M)"};
    
    /**
     * 增值业务费
     */
    private static final String[] CDR_ISMG_SHOW = {"增值业务费", "时间,方式,业务名称,业务端口,金额(元)"};
    
    /**
     * 代收费业务扣费记录
     */
    private static final String[] CDR_INFOFEE_SHOW = {"代收费业务扣费记录", "时间,方式,业务名称,业务端口,金额(元)"};
    
    /**
     * 其他扣费记录
     */
    private static final String[] CDR_OTHERFEE_SHOW = {"其他扣费记录", "时间,业务名称,类型,金额(元)"};
    
    /**
     * 减免详单
     */
    private static final String[] CDR_DISCOUNT_SHOW = {"减免详单", "时间,减免类型,减免金额(元)"};
    
    public static final Map<String, String[]> CDRTYPEMAP = new HashMap<String, String[]>();
    
    static
    {
        CDRTYPEMAP.put(CDRTYPE_FIXFEE, CDR_FIXFEE_SHOW);
        CDRTYPEMAP.put(CDRTYPE_GSM, CDR_GSM_SHOW);
        CDRTYPEMAP.put(CDRTYPE_SMS, CDR_SMS_SHOW);
        CDRTYPEMAP.put(CDRTYPE_GPRSWLAN, CDR_GPRSWLAN_SHOW);
        CDRTYPEMAP.put(CDRTYPE_ISMG, CDR_ISMG_SHOW);
        CDRTYPEMAP.put(CDRTYPE_INFOFEE, CDR_INFOFEE_SHOW);
        CDRTYPEMAP.put(CDRTYPE_OTHERFEE, CDR_OTHERFEE_SHOW);
        CDRTYPEMAP.put(CDRTYPE_DISCOUNT, CDR_DISCOUNT_SHOW);
    }
    // add end g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
    
    //add begin sWX219697 2014-05-09 OR_SD_201404_777 
    //山东用户是否开通积分计划接口 产品id
    private static final String[] SCORE_TITLE_NEW_SD = {"本年度累计积分", "当前可兑换积分"};
    //add end sWX219697 2014-05-09 OR_SD_201404_777
    
    /**
     * 积分明细查询
     */
    private static final String[] SCOREDETIAL_TITLE={"用户积分","变更原因","变更时间","受理渠道","积分类型"};
   
    /**
     * 积分兑换历史查询(山东)
     */
    private static final String[]SCORECHANGE_TITLE={"活动名称","赠品名称","兑换时间"};
    
    /**
     * 积分兑换历史查询(湖北)
     */
    private static final String[]SCOREPRIZE_TITLE={"兑换时间","兑换详细物品","兑换地点","兑换操作员"};

    // add begin jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 山东移动促销回馈积分查询方案支撑需求
    // 山东移动促销回馈积分查询开关
    public static final String SH_SCOREQRY_SWITCH = "SH_SCOREQRY_SWITCH";
    
    // 山东积分查询新标题
    private static final String[] SCORE_NEWTITLE_SD = {"积分值","到期日"};

	// 积分发放查询标题头
    private static final String[] PAYMENTSCORE_TITLE = {"发放时间","发放积分","到期日","备注"};
    // add end jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 山东移动促销回馈积分查询方案支撑需求

	public static Object[] getGsmShow() {
		return GSM_SHOW;
	}

	public static Object[] getSmsShow() {
		return SMS_SHOW;
	}

	public static Object[] getIsmgShow() {
		return ISMG_SHOW;
	}

	public static Object[] getGprsShow() {
		return GPRS_SHOW;
	}

	public static Object[] getWlanShow() {
		return WLAN_SHOW;
	}

	public static Object[] getMmsShow() {
		return MMS_SHOW;
	}

	public static Object[] getInfofeeShow() {
		return INFOFEE_SHOW;
	}

	public static Object[] getVpmnShow() {
		return VPMN_SHOW;
	}

	public static String[] getTypeServiceNameArray() {
		return TYPE_SERVICE_NAME_ARRAY;
	}

	public static String[] getTypeNameArray() {
		return TYPE_NAME_ARRAY;
	}

	public static Object[] getTypeTableDetail() {
		return TYPE_TABLE_DETAIL;
	}

	public static String[] getTypeTitles() {
		return TYPE_TITLES;
	}

	public static String[] getPluginidarray() {
		return pluginIdArray;
	}

	public static String[] getPluginkeyarray() {
		return pluginKeyArray;
	}

	public static Object[] getLbsShow() {
		return LBS_SHOW;
	}

	public static String[] getCdrFixfeeShow() {
		return CDR_FIXFEE_SHOW;
	}

	public static String[] getCdrGsmShow() {
		return CDR_GSM_SHOW;
	}

	public static String[] getCdrSmsShow() {
		return CDR_SMS_SHOW;
	}

	public static String[] getCdrGprswlanShow() {
		return CDR_GPRSWLAN_SHOW;
	}

	public static String[] getCdrIsmgShow() {
		return CDR_ISMG_SHOW;
	}

	public static String[] getCdrInfofeeShow() {
		return CDR_INFOFEE_SHOW;
	}

	public static String[] getCdrOtherfeeShow() {
		return CDR_OTHERFEE_SHOW;
	}

	public static String[] getCdrDiscountShow() {
		return CDR_DISCOUNT_SHOW;
	}

	public static String[] getScoreTitleNewSd() {
		return SCORE_TITLE_NEW_SD;
	}

	public static String[] getScoredetialTitle() {
		return SCOREDETIAL_TITLE;
	}

	public static String[] getScorechangeTitle() {
		return SCORECHANGE_TITLE;
	}

	public static String[] getScoreprizeTitle() {
		return SCOREPRIZE_TITLE;
	}

	public static String[] getScoreNewtitleSd() {
		return SCORE_NEWTITLE_SD;
	}

	public static String[] getPaymentscoreTitle() {
		return PAYMENTSCORE_TITLE;
	}

    
}
