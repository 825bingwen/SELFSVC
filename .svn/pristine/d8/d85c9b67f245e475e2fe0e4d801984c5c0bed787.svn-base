package com.customize.sd.selfsvc.packageService.dao;

import java.util.List;

import com.customize.sd.selfsvc.packageService.model.PrivServPackPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 特惠业务包
 * @author hWX5316476
 *
 */
public class PrivServPackDaoImpl  extends BaseDaoImpl
{
    /**
     * 查询特惠业务包列表
     * @param privServPackPO
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<PrivServPackPO> qryPrivServPackList(PrivServPackPO privServPackPO)
    {
        return sqlMapClientTemplate.queryForList("packageservice.qryPrivServPackList", privServPackPO);
    }
    
    /**
     * 查询特惠业务包详情
     * @param privServPackPO
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<PrivServPackPO> qryPrivServPackDetail(PrivServPackPO privServPackPO)
    {
        return sqlMapClientTemplate.queryForList("packageservice.qryPrivServPackDetail", privServPackPO);
    }
}
