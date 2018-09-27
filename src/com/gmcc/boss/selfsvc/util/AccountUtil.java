package com.gmcc.boss.selfsvc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.customize.sd.selfsvc.feeService.model.BillPO;
import com.customize.sd.selfsvc.feeService.model.FeeGroupPO;
import com.customize.sd.selfsvc.feeService.model.FeePO;
import com.customize.sd.selfsvc.feeService.model.FeedetailPO;
import com.customize.sd.selfsvc.feeService.model.PkgPO;
import com.customize.sd.selfsvc.feeService.model.PrivPO;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.cache.PublicCache;

/**
 * 
 * 新版本账单解析
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Feb 27, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AccountUtil
{
    /**
     * 解析费用信息
     * <功能详细描述>
     * @param ebillfixed
     * @return list<FeePO/FeeGroupPO>
     * @see [类、类#方法、类#成员]
     */
    public static List<Object> parseBillfixed(Element ebillfixed)
    {
        List<Object> list = new ArrayList<Object>();
        
        Iterator it = ebillfixed.elementIterator();
        //add begin m00227318 eCommerce R003C12L09_ESD OR_SD_201208_605
        String grantsResName = (String) PublicCache.getInstance().getCachedData(Constants.MONTHBILL_GRANTSFEE);
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        //add end m00227318 eCommerce R003C12L09_ESD OR_SD_201208_605
        while (it.hasNext())
        {
            Element objElement = (Element) it.next();
            String key = objElement.getName();
            
            if ("fee".equals(key))
            {
            	//add begin m00227318 2012/09/13 eCommerce R003C12L09_ESD OR_SD_201208_605
            	Element nameEle = objElement.element("name");
            	Element valueEle = objElement.element("value");
            	//判断当前省份是不是山东，如是，执行过滤.判断“使用赠费”是否为0，若为0，不拼接到解析过的报文中
            	if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province) && nameEle != null && valueEle != null)
            	{
            		String grantsName = nameEle.getText();
	                String grantsValue = valueEle.getText();
	            	if (grantsResName.equals(grantsName) 
	            			&& ("".equals(grantsValue) || "0.00".equals(grantsValue) || "0".equals(grantsValue) || "0.0".equals(grantsValue)))
	            	{
	            		continue;
	            	}	                
            	}
            	//add end m00227318 2012/09/13 eCommerce R003C12L09_ESD OR_SD_201208_605
                
                FeePO feePO = new FeePO();
                
                // modify begin hWX5316476 2014-7-22 V200R003C10LG0701 OR_huawei_201407_371_圈复杂度_自助终端
//                //modify begin m00227318 2012/09/13 eCommerce R003C12L09_ESD OR_SD_201208_605
//                if (nameEle != null)
//                {
//                    feePO.setName(nameEle.getText());
//                }
//                if (valueEle != null)
//                {
//                    feePO.setValue(valueEle.getText());
//                }
//                //modify end m00227318 2012/09/13 eCommerce R003C12L09_ESD OR_SD_201208_605
//                if (objElement.element("sortorder") != null)
//                {
//                    feePO.setSortorder(objElement.element("sortorder").getText());
//                }
//                if (objElement.element("isshow") != null)
//                {
//                    feePO.setIsshow(objElement.element("isshow").getText());
//                }
                feePO = getFeePOByEle(objElement, feePO, true);
                
                // modify begin hWX5316476 2014-7-22 V200R003C10LG0701 OR_huawei_201407_371_圈复杂度_自助终端
                
                list.add(feePO);
            }
            else if ("feegroup".equals(key))
            {
                FeeGroupPO feeGroupPO = new FeeGroupPO();
                feeGroupPO.setName(objElement.element("name").getText());
                feeGroupPO.setSortorder(objElement.element("sortorder").getText());
                
                Element subfeeElement = objElement.element("subfee");
                List<FeePO> subfee = new ArrayList<FeePO>();
                Iterator it_subfee = subfeeElement.elementIterator();
                while (it_subfee.hasNext())
                {
                    subfeeElement = (Element)it_subfee.next();
                    FeePO feePO = new FeePO();
                    // modify begin hWX5316476 2014-7-22 V200R003C10LG0701 OR_huawei_201407_371_圈复杂度_自助终端
//                    if (subfeeElement.element("name") != null)
//                    {
//                        feePO.setName(subfeeElement.element("name").getText());
//                    }
//                    if (subfeeElement.element("value") != null)
//                    {
//                        feePO.setValue(subfeeElement.element("value").getText());
//                    }
//                    if (subfeeElement.element("sortorder") != null)
//                    {
//                        feePO.setSortorder(subfeeElement.element("sortorder").getText());
//                    }
//                    if (subfeeElement.element("isshow") != null)
//                    {
//                        feePO.setIsshow(subfeeElement.element("isshow").getText());
//                    }
                    feePO = getFeePOByEle(subfeeElement, feePO, true);
                   // modify end hWX5316476 2014-7-22 V200R003C10LG0701 OR_huawei_201407_371_圈复杂度_自助终端
                    
                    subfee.add(feePO);
                }
                feeGroupPO.setSubfee(subfee);
                list.add(feeGroupPO);
            }
        }
        return list;
    }
    
    public static FeePO getFeePOByEle(Element element, FeePO feePO, boolean isshowFlag)
    {
        if (element.element("name") != null)
        {
            feePO.setName(element.element("name").getText());
        }
        
        if (element.element("value") != null)
        {
            feePO.setValue(element.element("value").getText());
        }
        
        if (element.element("sortorder") != null)
        {
            feePO.setSortorder(element.element("sortorder").getText());
        }
        
        if (isshowFlag)
        {
        	if (element.element("isshow") != null)
        	{
        		feePO.setIsshow(element.element("isshow").getText());
        	}
        }
        
        return feePO;
    }
    
    /**
     * 解析费用信息
     * <功能详细描述>
     * @param ebillfixed
     * @return list<FeePO/FeeGroupPO>
     * @see [类、类#方法、类#成员]
     */
    public static String parseBillfixed_hj(Element ebillfixed)
    {
        List<Object> list = new ArrayList<Object>();
        
        Iterator it = ebillfixed.elementIterator();
        while (it.hasNext())
        {
            Element objElement = (Element)it.next();
            String key = objElement.getName();
            String val = objElement.getText();
            if ("fee".equals(key))
            {
                FeePO feePO = new FeePO();
                if (objElement.element("name") != null)
                {
                    if ("本月费用合计".equals(objElement.element("name").getText()))
                    {
                        if (objElement.element("value") != null)
                        {
                            return objElement.element("value").getText();
                        }
                    }
                }
            }
        }
        return "";
    }
    
    /**
     * 解析自有业务_双排
     * <功能详细描述>
     * @param efeedetails
     * @return list<FeeGroupPO>
     * @see [类、类#方法、类#成员]
     */
    public static List<FeedetailPO> parseFeedetails(Element efeedetails)
    {
        List<FeeGroupPO> list = new ArrayList<FeeGroupPO>();
        
        Iterator it = efeedetails.elementIterator();
        while (it.hasNext())
        {
            Element objElement = (Element)it.next();
            String key = objElement.getName();
            String val = objElement.getText();
            if ("feegroup".equals(key))
            {
                FeeGroupPO feeGroupPO = new FeeGroupPO();
                
                //---------------name-------------------------------------------------------
                if (objElement.element("name") != null)
                {
                    feeGroupPO.setName(objElement.element("name").getText());
                }
                
                //---------------sortorder-------------------------------------------------------
                if (objElement.element("sortorder") != null)
                {
                    feeGroupPO.setSortorder(objElement.element("sortorder").getText());
                }
                
                //---------------subfee-------------------------------------------------------
                if (objElement.element("subfee") != null)
                {
                    Element subfeeElement = objElement.element("subfee");
                    List<FeePO> subfee = new ArrayList<FeePO>();
                    Iterator it_subfee = subfeeElement.elementIterator();
                    while (it_subfee.hasNext())
                    {
                        subfeeElement = (Element)it_subfee.next();
                        FeePO feePO = new FeePO();
                        if (subfeeElement.element("name") != null)
                        {
                            feePO.setName(subfeeElement.element("name").getText());
                        }
                        if (subfeeElement.element("value") != null)
                        {
                            feePO.setValue(subfeeElement.element("value").getText());
                        }
                        if (subfeeElement.element("sortorder") != null)
                        {
                            feePO.setSortorder(subfeeElement.element("sortorder").getText());
                        }
                        subfee.add(feePO);
                    }
                    feeGroupPO.setSubfee(subfee);
                }
                
                //---------------fee-------------------------------------------------------
                if (objElement.element("fee") != null)
                {
                    Element feeElement = objElement.element("fee");
                    FeePO feePO = new FeePO();
                    if (feeElement.element("name") != null)
                    {
                        feePO.setName(feeElement.element("name").getText());
                    }
                    if (feeElement.element("value") != null)
                    {
                        feePO.setValue(feeElement.element("value").getText());
                    }
                    if (feeElement.element("sortorder") != null)
                    {
                        feePO.setSortorder(feeElement.element("sortorder").getText());
                    }
                    feeGroupPO.setFee(feePO);
                }
                
                //--------------add--------------------------------------------------------
                list.add(feeGroupPO);
            }
        }
        
        List<FeedetailPO> tmpList = new ArrayList<FeedetailPO>();
        FeedetailPO feedetailPO;
        for(FeeGroupPO feeGroupPO : list)
        {
            // 费用组信息
            if (feeGroupPO.getName() != null)
            {
                feedetailPO = new FeedetailPO();
                feedetailPO.setName(feeGroupPO.getName());
                feedetailPO.setValue("");
                feedetailPO.setBz("0");
                tmpList.add(feedetailPO);
            }
            
            // 费用组内小项
            List<FeePO> feeList = feeGroupPO.getSubfee();
            if (feeList != null)
            {
                for(FeePO feePO : feeList)
                {
                    feedetailPO = new FeedetailPO();
                    feedetailPO.setName(feePO.getName());
                    feedetailPO.setValue(feePO.getValue());
                    feedetailPO.setBz("1");
                    tmpList.add(feedetailPO);
                }           
            }
            
            // 合计
            FeePO feePO = feeGroupPO.getFee();
            if (feePO != null)
            {
                feedetailPO = new FeedetailPO();
                feedetailPO.setName(feePO.getName());
                feedetailPO.setValue(feePO.getValue());
                feedetailPO.setBz("0");
                tmpList.add(feedetailPO);
            }
            
        }
        
        List<FeedetailPO> feedtailList = new ArrayList<FeedetailPO>();
        
        int size = 0;
        if (tmpList.size()%2 == 0)
        {
            size = tmpList.size()/2;
        }
        else
        {
            size = tmpList.size()/2 + 1;
        }
        
        // 处理name1,value1
        for(int i=1;i<=size;i++)
        {
            FeedetailPO tmpPO = tmpList.get(i-1);
            feedetailPO = new FeedetailPO();
            feedetailPO.setName1(tmpPO.getName());
            feedetailPO.setValue1(tmpPO.getValue());
            feedetailPO.setBz1(tmpPO.getBz());
            feedtailList.add(feedetailPO);
        }
        
        // 处理name2,value2
        int j = size;
        for(FeedetailPO po : feedtailList)
        {
            if (j < tmpList.size())
            {
                FeedetailPO tmpPO = tmpList.get(j);
                po.setName2(tmpPO.getName());
                po.setValue2(tmpPO.getValue());
                po.setBz2(tmpPO.getBz());
                j = j + 1;
            }
        }
        
        // 处理空信息
        for(FeedetailPO po : feedtailList)
        {
            if (po.getName2() == null)
            {
                po.setName2("");
            }
            if (po.getValue2() == null)
            {
                po.setValue2("");
            }
        }
        

        return feedtailList;
    }
    
    /**
     * 解析自有业务_单排
     * <功能详细描述>
     * @param efeedetails
     * @return list<FeeGroupPO>
     * @see [类、类#方法、类#成员]
     */
    public static List<FeedetailPO>  parseFeedetails_(Element efeedetails)
    {
        List<FeeGroupPO> list = new ArrayList<FeeGroupPO>();
        
        Iterator it = efeedetails.elementIterator();
        while (it.hasNext())
        {
            Element objElement = (Element)it.next();
            String key = objElement.getName();
            String val = objElement.getText();
            if ("feegroup".equals(key))
            {
                FeeGroupPO feeGroupPO = new FeeGroupPO();
                
                //---------------name-------------------------------------------------------
                if (objElement.element("name") != null)
                {
                    feeGroupPO.setName(objElement.element("name").getText());
                }
                
                //---------------sortorder-------------------------------------------------------
                if (objElement.element("sortorder") != null)
                {
                    feeGroupPO.setSortorder(objElement.element("sortorder").getText());
                }
                
                //---------------subfee-------------------------------------------------------
                if (objElement.element("subfee") != null)
                {
                    Element subfeeElement = objElement.element("subfee");
                    List<FeePO> subfee = new ArrayList<FeePO>();
                    Iterator it_subfee = subfeeElement.elementIterator();
                    while (it_subfee.hasNext())
                    {
                        subfeeElement = (Element)it_subfee.next();
                        FeePO feePO = new FeePO();
                        
                        // modify begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                        /*if (subfeeElement.element("name") != null)
                        {
                            feePO.setName(subfeeElement.element("name").getText());
                        }
                        if (subfeeElement.element("value") != null)
                        {
                            feePO.setValue(subfeeElement.element("value").getText());
                        }
                        if (subfeeElement.element("sortorder") != null)
                        {
                            feePO.setSortorder(subfeeElement.element("sortorder").getText());
                        }*/
                        feePO = getFeePOByEle(subfeeElement, feePO, false);
                        // modify end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                        subfee.add(feePO);
                    }
                    feeGroupPO.setSubfee(subfee);
                }
                
                //---------------fee-------------------------------------------------------
                if (objElement.element("fee") != null)
                {
                    Element feeElement = objElement.element("fee");
                    FeePO feePO = new FeePO();
                    
                    // modify begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                    /*if (feeElement.element("name") != null)
                    {
                        feePO.setName(feeElement.element("name").getText());
                    }
                    if (feeElement.element("value") != null)
                    {
                        feePO.setValue(feeElement.element("value").getText());
                    }
                    if (feeElement.element("sortorder") != null)
                    {
                        feePO.setSortorder(feeElement.element("sortorder").getText());
                    }*/
                    feePO = getFeePOByEle(feeElement, feePO, false);
                    // modify end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
                    feeGroupPO.setFee(feePO);
                }
                
                //--------------add--------------------------------------------------------
                list.add(feeGroupPO);
            }
        }
        
        List<FeedetailPO> tmpList = new ArrayList<FeedetailPO>();
        FeedetailPO feedetailPO;
        for(FeeGroupPO feeGroupPO : list)
        {
            // 费用组信息
            if (feeGroupPO.getName() != null)
            {
                feedetailPO = new FeedetailPO();
                feedetailPO.setName(feeGroupPO.getName());
                feedetailPO.setValue("");
                feedetailPO.setBz("0");
                tmpList.add(feedetailPO);
            }
            
            // 费用组内小项
            List<FeePO> feeList = feeGroupPO.getSubfee();
            if (feeList != null)
            {
                for(FeePO feePO : feeList)
                {
                    feedetailPO = new FeedetailPO();
                    feedetailPO.setName(feePO.getName());
                    feedetailPO.setValue(feePO.getValue());
                    feedetailPO.setBz("1");
                    tmpList.add(feedetailPO);
                }           
            }
            
            // 合计
            FeePO feePO = feeGroupPO.getFee();
            if (feePO != null)
            {
                feedetailPO = new FeedetailPO();
                feedetailPO.setName(feePO.getName());
                feedetailPO.setValue(feePO.getValue());
                feedetailPO.setBz("0");
                tmpList.add(feedetailPO);
            }
            
        }
        
        return tmpList;
    }
    
    /**
     * 解析出MAP
     * <功能详细描述>
     * @param eacct
     * @return Map
     * @see [类、类#方法、类#成员]
     */
    public static Map parseMap(Element eacct)
    {
        Map<String,String> map = new HashMap<String,String>();
        
        Iterator it = eacct.elementIterator();
        while (it.hasNext())
        {
            Element objElement = (Element)it.next();
            String key = objElement.getName();
            String val = objElement.getText();
            
            map.put(key, val);
        }
        return map;
    }
    
    /**
     * 半年消费趋势图
     * <功能详细描述>
     * @param ebillhalfyeartrend
     * @return list<FeeGroupPO>
     * @see [类、类#方法、类#成员]
     */
    public static List<BillPO> parseBillhalfyeartrend(Element ebillhalfyeartrend)
    {
        List<BillPO> list = new ArrayList<BillPO>();
        
        Iterator it = ebillhalfyeartrend.elementIterator();
        while (it.hasNext())
        {
            Element objElement = (Element)it.next();
            String key = objElement.getName();
            String val = objElement.getText();
            if ("bill".equals(key))
            {
                BillPO billPO = new BillPO();
                
                //---------------month-------------------------------------------------------
                if (objElement.element("month") != null)
                {
                    billPO.setMonth(objElement.element("month").getText());
                }
                
                //---------------money-------------------------------------------------------
                if (objElement.element("money") != null)
                {
                    billPO.setMoney(objElement.element("money").getText());
                }
                
                //--------------add--------------------------------------------------------
                list.add(billPO);
            }
        }

        return list;
    }
    
    /**
     * 套餐信息
     * <功能详细描述>
     * @param ebillfixed
     * @return list<PkgPO>
     * @see [类、类#方法、类#成员]
     */
    public static List<PkgPO> parsePkg(Element epkginfo)
    {
        List<PkgPO> list = new ArrayList<PkgPO>();
        
        Iterator it = epkginfo.elementIterator();
        while (it.hasNext())
        {
            Element objElement = (Element)it.next();
            String key = objElement.getName();
            String val = objElement.getText();
            if ("pkg".equals(key))
            {
                PkgPO pkgPO = new PkgPO();
                if (objElement.element("pkgid") != null)
                {
                    pkgPO.setPkgid(objElement.element("pkgid").getText());
                }
                if (objElement.element("pkgname") != null)
                {
                    pkgPO.setPkgname(objElement.element("pkgname").getText());
                }
                if (objElement.element("pkgdesc") != null)
                {
                    pkgPO.setPkgdesc(objElement.element("pkgdesc").getText());
                }
                
                Element privlistElement = objElement.element("privlist");
                List<PrivPO> priv = new ArrayList<PrivPO>();
                Iterator it_priv = privlistElement.elementIterator();
                while (it_priv.hasNext())
                {
                    privlistElement = (Element)it_priv.next();
                    PrivPO privPO = new PrivPO();
                    if (privlistElement.element("rateid") != null)
                    {
                        privPO.setRateid(privlistElement.element("rateid").getText());
                    }
                    if (privlistElement.element("freetype") != null)
                    {
                        privPO.setFreetype(privlistElement.element("freetype").getText());
                    }
                    if (privlistElement.element("total") != null)
                    {
                        privPO.setTotal(privlistElement.element("total").getText());
                    }
                    if (privlistElement.element("used") != null)
                    {
                        privPO.setUsed(privlistElement.element("used").getText());
                    }
                    if (privlistElement.element("attrtype") != null)
                    {
                        privPO.setAttrtype(privlistElement.element("attrtype").getText());
                    }
                    if (privlistElement.element("feename") != null)
                    {
                        privPO.setFeename(privlistElement.element("feename").getText());
                    }
                    priv.add(privPO);
                }
                pkgPO.setPrivlist(priv);
                list.add(pkgPO);
            }
        }
        
        // 返回
        return list;
    }
    
    /**
     * 套餐汇总信息
     * <功能详细描述>
     * @param ebillfixed
     * @return list<PkgPO>
     * @see [类、类#方法、类#成员]
     */
    public static List<PkgPO> parseTotal(Element epkginfo)
    {
        List<PkgPO> list = new ArrayList<PkgPO>();
        
        Iterator it = epkginfo.elementIterator();
        while (it.hasNext())
        {
            Element objElement = (Element)it.next();
            String key = objElement.getName();
            String val = objElement.getText();
            if ("total".equals(key))
            {
                PkgPO pkgPO = new PkgPO();
                pkgPO.setPkgid("");
                pkgPO.setPkgname("");
                pkgPO.setPkgdesc("");
                
                Element tmpElement;
                List<PrivPO> priv = new ArrayList<PrivPO>();
                Iterator it_priv = objElement.elementIterator();
                while (it_priv.hasNext())
                {
                    tmpElement = (Element)it_priv.next();
                    PrivPO privPO = new PrivPO();
                    if (tmpElement.element("rateid") != null)
                    {
                        privPO.setRateid(tmpElement.element("rateid").getText());
                    }
                    if (tmpElement.element("freetype") != null)
                    {
                        privPO.setFreetype(tmpElement.element("freetype").getText());
                    }
                    if (tmpElement.element("total") != null)
                    {
                        privPO.setTotal(tmpElement.element("total").getText());
                    }
                    if (tmpElement.element("used") != null)
                    {
                        privPO.setUsed(tmpElement.element("used").getText());
                    }
                    if (tmpElement.element("attrtype") != null)
                    {
                        privPO.setAttrtype(tmpElement.element("attrtype").getText());
                    }
                    if (tmpElement.element("feename") != null)
                    {
                        privPO.setFeename(tmpElement.element("feename").getText());
                    }
                    priv.add(privPO);
                }
                pkgPO.setPrivlist(priv);
                list.add(pkgPO);
            }
        }
        
        // 返回
        return list;
    }
    
    /**
     * 解析代收费业务
     * <功能详细描述>
     * @param ebillfixed
     * @return list<FeePO/FeeGroupPO>
     * @see [类、类#方法、类#成员]
     */
    public static List<Map> parseSp(Element esp)
    {
        List<Map> list = new ArrayList<Map>();
        Map<String,String> map = null;
        Iterator it = esp.elementIterator();
        while (it.hasNext())
        {
            Element objElement = (Element)it.next();
            if ("sp".equals(objElement.getName()))
            {
                Iterator it_sp = objElement.elementIterator();
                map = new HashMap<String,String>();
                while (it_sp.hasNext())
                {
                    Element element = (Element)it_sp.next();
                    String key = element.getName();
                    String val = element.getText();
                    map.put(key, val);
                }
                list.add(map);
            }
        }
        
        // 返回
        return list;
    }
    
    /**
     * 解析List<Map>
     * <功能详细描述>
     * @param ebillfixed
     * @return list<FeePO/FeeGroupPO>
     * @see [类、类#方法、类#成员]
     */
    public static List<Map> parseListMap(Element e,String name)
    {
        List<Map> list = new ArrayList<Map>();
        Map<String,String> map = null;
        Iterator it = e.elementIterator();
        while (it.hasNext())
        {
            Element objElement = (Element)it.next();
            if (name.equals(objElement.getName()))
            {
                Iterator it_sp = objElement.elementIterator();
                map = new HashMap<String,String>();
                while (it_sp.hasNext())
                {
                    Element element = (Element)it_sp.next();
                    String key = element.getName();
                    String val = element.getText();
                    map.put(key, val);
                }
                list.add(map);
            }
        }
        
        // 返回
        return list;
    }
    
    /**
     * 解析acctbalance
     * <功能详细描述>
     * @param ebillfixed
     * @return list<FeePO/FeeGroupPO>
     * @see [类、类#方法、类#成员]
     */
    public static Map parseAcctbalance_NX(Element e)
    {
        Map map = new HashMap();
        List<Map> list = new ArrayList<Map>();
        Map mapObj = null;
        Iterator it = e.elementIterator();
        while (it.hasNext())
        {
            Element objElement = (Element)it.next();
            if ("leftmoney0".equals(objElement.getName()))
            {
                map.put("leftmoney0", objElement.getText());
            }
            else if ("leftmoney1".equals(objElement.getName()))
            {
                map.put("leftmoney1", objElement.getText());
            }
            else if ("leftmoney2".equals(objElement.getName()))
            {
                map.put("leftmoney2", objElement.getText());
            }
            else if ("consume".equals(objElement.getName()))
            {
                map.put("consume", objElement.getText());
            }
            else if ("debtfee".equals(objElement.getName()))
            {
                map.put("debtfee", objElement.getText());
            }
            else if ("acctlist".equals(objElement.getName()))
            {
                Iterator it_sp = objElement.elementIterator();
                while (it_sp.hasNext())
                {
                    Element element = (Element)it_sp.next();
                    Iterator it_obj = element.elementIterator();
                    mapObj = new HashMap();
                    while (it_obj.hasNext())
                    {
                        Element element_ = (Element)it_obj.next();
                        String key = element_.getName();
                        String val = element_.getText();
                        mapObj.put(key, val);
                    }
                    list.add(mapObj);
                }
            }
        }
        
        map.put("acctlist", list);
        
        // 返回
        return map;
    }
    
}
