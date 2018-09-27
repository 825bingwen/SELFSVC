/*
 * 文件名：ResDataDaoImpl.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增
 */
package com.customize.hub.selfsvc.prodInstall.dao;

import java.util.List;

import com.customize.hub.selfsvc.prodInstall.model.InstallLogVO;
import com.customize.hub.selfsvc.prodInstall.model.ShDictItemVO;
import com.customize.hub.selfsvc.prodInstall.model.ShTpltTempletVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 
 * 自助终端入网
 * @author  xkf57421
 * @version  [版本号, Jul 7, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProdInstallDaoImpl extends BaseDaoImpl
{
    public ProdInstallDaoImpl()
    {
        super();
    }
   
    /**
     * 取自助终端入网产品模板
     * <功能详细描述>
     * @param tpltVO
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
	public List<ShTpltTempletVO> getTpltlist(ShTpltTempletVO tpltVO)
    {
    	return sqlMapClientTemplate.queryForList("prodInstall.qryTpltTemplate",tpltVO);
    }

	public void addInstallLog(InstallLogVO installLogVO)
	{
		sqlMapClientTemplate.insert("prodInstall.insertInstallLog", installLogVO);
	}
	
	@SuppressWarnings("unchecked")
	public List<ShDictItemVO> getDictItem(ShDictItemVO dictVO)
	{
		return sqlMapClientTemplate.queryForList("prodInstall.qryShDictItem", dictVO);
	}
}
