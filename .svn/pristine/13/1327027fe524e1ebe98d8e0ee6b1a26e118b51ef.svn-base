package com.gmcc.boss.selfsvc.query.common;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;

public class SelfSvcCdrType
{
    public static String[] getSupportServiceNames()
    {
        String[] supportTypeNames = {};
        
        String cdrTypes = (String)PublicCache.getInstance().getCachedData(Constants.CDR_TYPES);
        if (cdrTypes != null && cdrTypes.trim().length() > 0)
        {
            supportTypeNames = cdrTypes.split(Constants.STR_SPLIT_TRANS);
        }
        
        return supportTypeNames;
    }
    
    public static int previousFactType(int listType)
    {
        int factType = 0;
        if (listType >= 0)
        {
            String supportName = getSupportServiceNames()[listType].trim();
            int i = 0;
            // begin zKF66389 2015-09-10 9月份findbugs修改
//            int typeSize = Constants.TYPE_SERVICE_NAME_ARRAY.length;
            int typeSize = Constants.getTypeServiceNameArray().length;
            // end zKF66389 2015-09-10 9月份findbugs修改
            while (i < typeSize)
            {
            	// begin zKF66389 2015-09-10 9月份findbugs修改
//                if (supportName.equalsIgnoreCase(Constants.TYPE_SERVICE_NAME_ARRAY[i]))
                if (supportName.equalsIgnoreCase(Constants.getTypeServiceNameArray()[i]))
                // end zKF66389 2015-09-10 9月份findbugs修改
                {
                    factType = i;
                    break;
                }
                i++;
            }
        }
        return factType;
    }
    
    public static int nextFactType(int listType)
    {
    	// begin zKF66389 2015-09-10 9月份findbugs修改
//        int factType = Constants.TYPE_SERVICE_NAME_ARRAY.length;
        int factType = Constants.getTypeServiceNameArray().length;
        // end zKF66389 2015-09-10 9月份findbugs修改
        if (listType < getSupportServiceNames().length)
        {
            String supportName = getSupportServiceNames()[listType].trim();
            
            int i = 0;
            // begin zKF66389 2015-09-10 9月份findbugs修改
//            int typeSize = Constants.TYPE_SERVICE_NAME_ARRAY.length;
            int typeSize = Constants.getTypeServiceNameArray().length;
            // end zKF66389 2015-09-10 9月份findbugs修改
            while (i < typeSize)
            {
            	// begin zKF66389 2015-09-10 9月份findbugs修改
//                if (supportName.equalsIgnoreCase(Constants.TYPE_SERVICE_NAME_ARRAY[i]))
                if (supportName.equalsIgnoreCase(Constants.getTypeServiceNameArray()[i]))
                // end zKF66389 2015-09-10 9月份findbugs修改
                {
                    factType = i;
                    break;
                }
                i++;
            }
        }
        return factType;
    }
    
    public static int supportType(int listType)
    {
    	// begin zKF66389 2015-09-10 9月份findbugs修改
//        String supportName = Constants.TYPE_SERVICE_NAME_ARRAY[listType];
        String supportName = Constants.getTypeServiceNameArray()[listType];
        // end zKF66389 2015-09-10 9月份findbugs修改
        int i = 0;
        int typeSize = getSupportServiceNames().length;
        int supportType = typeSize;
        while (i < typeSize)
        {
            if (supportName.equalsIgnoreCase(getSupportServiceNames()[i].trim()))
            {
                supportType = i;
                break;
            }
            i++;
        }
        return supportType;
    }
    
    public static boolean isSupport(int listType)
    {
        boolean isSupport = false;
        // begin zKF66389 2015-09-10 9月份findbugs修改
//        String typeName = Constants.TYPE_SERVICE_NAME_ARRAY[listType];
        String typeName = Constants.getTypeServiceNameArray()[listType];
        // end zKF66389 2015-09-10 9月份findbugs修改
        String[] supportTypeNames = {};
        supportTypeNames = getSupportServiceNames();
        int i = 0;
        int typeSize = supportTypeNames.length;
        while (i < typeSize)
        {
            if (typeName.equalsIgnoreCase(supportTypeNames[i].trim()))
            {
                isSupport = true;
                break;
            }
            i++;
        }
        return isSupport;
    }
}
