/*
 * 文件名：DocumentUtil.java
 * 版权：CopyRight 2014-2018 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：通用工具类
 * 创建人:hWX5316476
 * 创建时间：2014-06-23
 */
package com.gmcc.boss.selfsvc.util;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

/**
 * 
 * doc工具类
 * 
 * @author hWX5316476
 * @version [版本号, Jun 24, 2014]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DocumentUtil
{
    /**
     * 在父节点下创建子节点，并赋值
     * 
     * @param eleRoot 父节点
     * @param subEle 子节点
     * @param subValue 子节点值
     * @see [类、类#方法、类#成员]
     */
    public static void addSubElementToEle(Element eleRoot, String subEle, String subValue)
    {
        addSubElementToEle(eleRoot, subEle, subValue, null);
    }
    
    /**
     * 在父节点下创建子节点，并赋值
     * 
     * @param eleRoot 父节点
     * @param subEle 子节点
     * @param subValue 子节点值
     * @param defaultValue 默认值
     * @see [类、类#方法、类#成员]
     */
    public static void addSubElementToEle(Element eleRoot, String subEle, String subValue, String defaultValue)
    {
        if (null == eleRoot)
        {
            return;
        }
        if (StringUtils.isEmpty(subEle))
        {
            return;
        }
        
        // 子节点值为null时，使用传入的默认值，若默认值为空，则默认为空串
        if (null == subValue)
        {
            if (StringUtils.isNotEmpty(defaultValue))
            {
                subValue = defaultValue;
            }
            else
            {
                subValue = "";
            }
        }
        eleRoot.addElement(subEle).addText(subValue);
    }
}
