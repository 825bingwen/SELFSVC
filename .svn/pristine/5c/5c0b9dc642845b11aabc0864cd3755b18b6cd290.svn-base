package com.customize.sd.selfsvc.packageService.service;

import java.util.List;

import com.customize.sd.selfsvc.packageService.dao.PrivServPackDaoImpl;
import com.customize.sd.selfsvc.packageService.model.PrivServPackPO;

/**
 * 特惠业务包Service
 * @author  hWX5316476
 * @version  [版本号, Dec 22, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PrivServPackServiceImpl implements PrivServPackService
{
    /**
     * dao
     */
    private PrivServPackDaoImpl privServPackDaoImpl;

    /**
     * 查询特惠业务包列表
     * @param privServPackPO
     * @return
     */
    public List<PrivServPackPO> qryPrivServPackList(PrivServPackPO privServPackPO)
    {
        return privServPackDaoImpl.qryPrivServPackList(privServPackPO);
    }

    /**
     * 查询特惠业务包详情
     * @param privServPackPO
     * @return
     */
    public PrivServPackPO qryPrivServPackDetail(PrivServPackPO privServPackPO)
    {
        // 查询特惠业务包详情
        List<PrivServPackPO> resultList = privServPackDaoImpl.qryPrivServPackDetail(privServPackPO);
        
        // 如果多个返回结果取第一个
        if(null != resultList && resultList.size() > 0)
        {
            return resultList.get(0);
        }
        else
        {
            return null;
        }
    }
    public PrivServPackDaoImpl getPrivServPackDaoImpl()
    {
        return privServPackDaoImpl;
    }

    public void setPrivServPackDaoImpl(PrivServPackDaoImpl privServPackDaoImpl)
    {
        this.privServPackDaoImpl = privServPackDaoImpl;
    }
    
    
}
