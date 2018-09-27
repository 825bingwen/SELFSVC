package com.customize.hub.selfsvc.prodInstall.service;

import java.util.List;

import com.customize.hub.selfsvc.prodInstall.model.InstallLogVO;
import com.customize.hub.selfsvc.prodInstall.model.ShDictItemVO;
import com.customize.hub.selfsvc.prodInstall.model.ShTpltTempletVO;

public interface ProdInstallHubService {
    /**
     * ҵ����־
     * �Ż������¼��־
     * @param privLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<ShTpltTempletVO> queryTpltTemplet(ShTpltTempletVO shTpltTempletVO);

	public void createInstallLog(InstallLogVO installLogVO);
	
	public List<ShDictItemVO> queryShDictItem(ShDictItemVO dictVO);
}
