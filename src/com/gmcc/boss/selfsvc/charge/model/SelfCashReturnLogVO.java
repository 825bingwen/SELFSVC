package com.gmcc.boss.selfsvc.charge.model;

/**
 * ����������Ϣ
 * @author user
 *
 */
public class SelfCashReturnLogVO {
	
	private String cashReturnId;// �������׷���Ψһ��ʶ(SEQ_UNIONRETURNLOG)
	private String termId;// �ն�ID
	private String serverNumber;// �ֻ�����
	private int fee;// ���(��)
	private String recdate;// ����ʱ��
	private String touchOid;// �Զ��ն����ɵĽӴ���ˮ
	private int status;// 0:�ɹ� 1:ʧ��
	private String terminalSeq;//Ͷ����ˮ
	
	public String getCashReturnId() {
		return cashReturnId;
	}
	public void setCashReturnId(String cashReturnId) {
		this.cashReturnId = cashReturnId;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getServerNumber() {
		return serverNumber;
	}
	public void setServerNumber(String serverNumber) {
		this.serverNumber = serverNumber;
	}

	public String getRecdate() {
		return recdate;
	}
	public void setRecdate(String recdate) {
		this.recdate = recdate;
	}

	public int getFee()
    {
        return fee;
    }
    public void setFee(int fee)
    {
        this.fee = fee;
    }
    public int getStatus()
    {
        return status;
    }
    public void setStatus(int status)
    {
        this.status = status;
    }
    public String getTouchOid() {
		return touchOid;
	}
	public void setTouchOid(String touchOid) {
		this.touchOid = touchOid;
	}
    public String getTerminalSeq()
    {
        return terminalSeq;
    }
    public void setTerminalSeq(String terminalSeq)
    {
        this.terminalSeq = terminalSeq;
    }
	
	
	
	
	
}
