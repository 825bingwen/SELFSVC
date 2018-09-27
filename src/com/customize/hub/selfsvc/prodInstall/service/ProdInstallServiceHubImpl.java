package com.customize.hub.selfsvc.prodInstall.service;

import java.util.List;

import com.customize.hub.selfsvc.prodInstall.dao.ProdInstallDaoImpl;
import com.customize.hub.selfsvc.prodInstall.model.InstallLogVO;
import com.customize.hub.selfsvc.prodInstall.model.ShDictItemVO;
import com.customize.hub.selfsvc.prodInstall.model.ShTpltTempletVO;


public class ProdInstallServiceHubImpl implements ProdInstallHubService
{
	private ProdInstallDaoImpl prodInstallDaoImpl;
	
	public void setProdInstallDaoImpl(ProdInstallDaoImpl prodInstallDaoImpl)
	{
		this.prodInstallDaoImpl = prodInstallDaoImpl;
	}
	
	public List<ShTpltTempletVO> queryTpltTemplet(ShTpltTempletVO shTpltTempletVO)
	{
		return prodInstallDaoImpl.getTpltlist(shTpltTempletVO);
	}

	@Override
	public void createInstallLog(InstallLogVO installLogVO)
	{
		prodInstallDaoImpl.addInstallLog(installLogVO);
	}

	@Override
	public List<ShDictItemVO> queryShDictItem(ShDictItemVO dictVO)
	{
		return prodInstallDaoImpl.getDictItem(dictVO);
	}
}
