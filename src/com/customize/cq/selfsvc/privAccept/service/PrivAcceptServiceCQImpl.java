package com.customize.cq.selfsvc.privAccept.service;

import com.customize.cq.selfsvc.privAccept.dao.PrivAcceptDaoCQImpl;
import com.customize.cq.selfsvc.privAccept.model.PrivLogVO;


public class PrivAcceptServiceCQImpl implements PrivAcceptCQService{
	
	private PrivAcceptDaoCQImpl privAcceptDaoCQImpl;

	/**
	 * ��־��¼
	 */
	public void createPrivLog(PrivLogVO privLogVO) {
		privAcceptDaoCQImpl.createPrivLog(privLogVO);
		
	}

	public PrivAcceptDaoCQImpl getPrivAcceptDaoCQImpl() {
		return privAcceptDaoCQImpl;
	}

	public void setPrivAcceptDaoCQImpl(PrivAcceptDaoCQImpl privAcceptDaoCQImpl) {
		this.privAcceptDaoCQImpl = privAcceptDaoCQImpl;
	}
	
	

}
