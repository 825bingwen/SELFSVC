package com.customize.hub.selfsvc.prodInstall.service;

import java.util.List;

import com.customize.hub.selfsvc.prodInstall.model.InstallLogVO;
import com.customize.hub.selfsvc.prodInstall.model.ShDictItemVO;
import com.customize.hub.selfsvc.prodInstall.model.ShTpltTempletVO;

public interface ProdInstallHubService {
    /**
     * 业务日志
     * 优惠受理记录日志
     * @param privLogVO
     * @see [类、类#方法、类#成员]
     */
    public List<ShTpltTempletVO> queryTpltTemplet(ShTpltTempletVO shTpltTempletVO);

	public void createInstallLog(InstallLogVO installLogVO);
	
	public List<ShDictItemVO> queryShDictItem(ShDictItemVO dictVO);
}
