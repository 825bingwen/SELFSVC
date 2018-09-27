package com.gmcc.boss.selfsvc.common;

/**
 * 调用接口时，请求报文头实体类
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-8-19]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MsgHeaderPO extends BasePO
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -4056115091916833283L;
    
    /**
     * 路由类型:region
     */
    public static final String ROUTETYPE_REGION = "0";
    
    /**
     * 路由类型:手机号码
     */
    public static final String ROUTETYPE_TELNUM = "1";
    
    /**
     * 构造函数
     */
    public MsgHeaderPO(String menuId, String operId, String termId, String uniContact, String routeType, String region,
        String telNum, String verifyCode, String processCode)
    {
        super();
        this.menuId = menuId;
        this.operId = operId;
        this.termId = termId;
        this.uniContact = uniContact;
        this.routeType = routeType;
        this.routeValue = ROUTETYPE_REGION.equals(routeType) ? region : telNum;
        this.region = region;
        this.telNum = telNum;
        this.verifyCode = verifyCode;
        this.processCode = processCode;
    }
    
    /**
     * 构造函数
     */
    public MsgHeaderPO(String menuId, String operId, String termId, String uniContact, String routeType,
        String routeValue, String verifyCode, String processCode)
    {
        this(menuId, operId, termId, uniContact, routeType, ROUTETYPE_REGION.equals(routeType) ? routeValue : "", 
            ROUTETYPE_TELNUM.equals(routeType) ? routeValue : "", verifyCode, processCode);
    }
    
    /**
     * 构造函数
     */
    public MsgHeaderPO(String menuId, String operId, String termId, String uniContact, String routeType,
        String routeValue, String verifyCode)
    {
        this(menuId, operId, termId, uniContact, routeType, routeValue, verifyCode, null);
    }
    
    /**
     * 构造函数
     */
    public MsgHeaderPO(String menuId, String operId, String termId, String uniContact, String routeType, String routeValue)
    {
        this(menuId, operId, termId, uniContact, routeType, routeValue, null);
    }
    
    /**
     * 业务编码，对应crm侧UAP的businessid
     */
    private String processCode;
    
    /**
     * 菜单ID
     */
    private String menuId;
    
    /**
     * 统一接触流水，密码验证接口返回
     */
    private String uniContact;
    
    /**
     * 路由类型: 0 按地市路由   1 按手机号码路由
     */
    private String routeType;
    
    /**
     * 路由值
     */
    private String routeValue;
    
    /**
     * 操作员ID
     */
    private String operId;
    
    /**
     * 终端Id
     */
    private String termId;
    
    /**
     * 操作员所属地市编码
     */
    private String region;
    
    /**
     * 用户手机号码
     */
    private String telNum;
    
    /**
     * 渠道编码
     */
    private String channelId = "bsacAtsv";
    
    /**
     * 校验码，密码验证接口返回
     */
    private String verifyCode;
    
    /**
     * 测试标记 可以为空；0表示交易为测试用；1表示正式交易
     */
    private String testFlag = "";
    
    /**
     * 渠道下级单位信息
     */
    private String unitId = "";

    /**
     * @return 返回 processCode
     */
    public String getProcessCode()
    {
        return processCode;
    }

    /**
     * @param 对processCode进行赋值
     */
    public void setProcessCode(String processCode)
    {
        this.processCode = processCode;
    }

    /**
     * @return 返回 menuId
     */
    public String getMenuId()
    {
        return menuId;
    }

    /**
     * @param 对menuId进行赋值
     */
    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }

    /**
     * @return 返回 uniContact
     */
    public String getUniContact()
    {
        return uniContact;
    }

    /**
     * @param 对uniContact进行赋值
     */
    public void setUniContact(String uniContact)
    {
        this.uniContact = uniContact;
    }

    /**
     * @return 返回 routeType
     */
    public String getRouteType()
    {
        return routeType;
    }

    /**
     * @param 对routeType进行赋值
     */
    public void setRouteType(String routeType)
    {
        this.routeType = routeType;
    }

    /**
     * @return 返回 operId
     */
    public String getOperId()
    {
        return operId;
    }

    /**
     * @param 对operId进行赋值
     */
    public void setOperId(String operId)
    {
        this.operId = operId;
    }

    /**
     * @return 返回 termId
     */
    public String getTermId()
    {
        return termId;
    }

    /**
     * @param 对termId进行赋值
     */
    public void setTermId(String termId)
    {
        this.termId = termId;
    }

    /**
     * @return 返回 region
     */
    public String getRegion()
    {
        return region;
    }

    /**
     * @param 对region进行赋值
     */
    public void setRegion(String region)
    {
        this.region = region;
    }

    /**
     * @return 返回 channelId
     */
    public String getChannelId()
    {
        return channelId;
    }

    /**
     * @param 对channelId进行赋值
     */
    public void setChannelId(String channelId)
    {
        this.channelId = channelId;
    }

    /**
     * @return 返回 telNum
     */
    public String getTelNum()
    {
        return telNum;
    }

    /**
     * @param 对telNum进行赋值
     */
    public void setTelNum(String telNum)
    {
        this.telNum = telNum;
    }

    /**
     * @return 返回 routeValue
     */
    public String getRouteValue()
    {
        return routeValue;
    }

    /**
     * @param 对routeValue进行赋值
     */
    public void setRouteValue(String routeValue)
    {
        this.routeValue = routeValue;
    }

    /**
     * @return 返回 verifyCode
     */
    public String getVerifyCode()
    {
        return verifyCode;
    }

    /**
     * @param 对verifyCode进行赋值
     */
    public void setVerifyCode(String verifyCode)
    {
        this.verifyCode = verifyCode;
    }

    /**
     * @return 返回 testFlag
     */
    public String getTestFlag()
    {
        return testFlag;
    }

    /**
     * @param 对testFlag进行赋值
     */
    public void setTestFlag(String testFlag)
    {
        this.testFlag = testFlag;
    }

    /**
     * @return 返回 unitId
     */
    public String getUnitId()
    {
        return unitId;
    }

    /**
     * @param 对unitId进行赋值
     */
    public void setUnitId(String unitId)
    {
        this.unitId = unitId;
    }
}
