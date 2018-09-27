package com.customize.hub.selfsvc.feeservice.action;

import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.ChargeGuideBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class ChargeGuideAction extends BaseAction{
	
    private static final long serialVersionUID = -1L;
    
    private static Log logger = LogFactory.getLog(ChargeGuideAction.class);
    
    private String curMenuId = "";
    
    private String errormessage;
    
    private ChargeGuideBean chargeGuideBean;
    
    private String streamNo;
    
    private String questionCode;
    
    //问题
    private String question;
    
    private String answerCode;
    
    private Vector answer;
    
    
    
    public String qryChargeGuide(){
    	
    	logger.debug("qryChargeGuide start!");
    	
    	String forward ="success";
        // 获取session
        HttpSession session = getRequest().getSession();
        // 获取request
        HttpServletRequest request = this.getRequest();
        
        // 终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
         
        if(streamNo==null){
        	streamNo = "";
        }
        if(questionCode==null){
        	questionCode = "";
        }
        if(answerCode==null){
        	answerCode = "";
        }
        if (curMenuId == null)
        {
        	curMenuId = "";
        }
        String questionFlag = request.getParameter("questionFlag");
        System.out.println("标志为："+questionFlag);
        System.out.println("操作员："+terminalInfoPO.getOperid());
        System.out.println("地市："+terminalInfoPO.getRegion());
        
        if(questionFlag == null||!questionFlag.equals("1")){
        	streamNo = "";
        	questionCode = "";
        	answerCode = "";
        }
        System.out.println(answerCode +"---------"+questionCode+"------"+streamNo);
        
        Map result = chargeGuideBean.qryChargeGuide(terminalInfoPO, curMenuId, streamNo, questionCode, answerCode);
        if (result != null && result.size() > 0){
        	
        	CTagSet tagSet =(CTagSet)result.get("returnObj");
        	streamNo = tagSet.GetValue("streamno");
        	questionCode = tagSet.GetValue("questioncode");
        	question = tagSet.GetValue("question")==null?"":tagSet.GetValue("question");
        	String recInfo = tagSet.GetValue("recinfo");
        	System.out.println(recInfo);
        	logger.info("当前输入的问题和答案："+ question +"--"+recInfo);
        	if(question.trim().length()> 0){
        		String[] answerItem = recInfo.split("~");
        		Vector<String[]> v = new Vector<String[]>();
        		String[] answerItmes = new String[2];
        		for(int i=0;i<answerItem.length;i++){
        			if(i%2==0){
        				answerItmes[0] = answerItem[i];
        			}
        			if(i%2==1){
        				answerItmes[1] = answerItem[i];
        				v.add(answerItmes);
        				answerItmes = new String[2];
        			}
        		}
        		setAnswer(v);
        		forward ="success";
        	}else{
        		Vector<String> v = new Vector<String>();
        		if(recInfo.trim().length()>0){
        			recInfo = recInfo.replaceAll(" ", "");
	        		String[] answerItem = recInfo.split("~");
	        		if(answerItem.length>0){
		        		for(int i=0;i<answerItem.length;i++){
		        			v.add(answerItem[i].trim());
		        		}
	        		}else{	
	        			v.add("对不起，暂时没有您所需要的资费！");
	        		}
        		}else{
        			v.add("对不起，暂时没有您所需要的资费！");
        		}
        		setAnswer(v);
        		streamNo = "";
        		questionCode = "";
        		answerCode = "";
        		forward = "chargeGuide";
        	}
        }else{
        	setErrormessage("资费推荐查询错误!");
        	forward = "error";
        }
    	return forward;
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public ChargeGuideBean getChargeGuideBean() {
		return chargeGuideBean;
	}

	public void setChargeGuideBean(ChargeGuideBean chargeGuideBean) {
		this.chargeGuideBean = chargeGuideBean;
	}

	public String getStreamNo() {
		return streamNo;
	}

	public void setStreamNo(String streamNo) {
		this.streamNo = streamNo;
	}

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public String getAnswerCode() {
		return answerCode;
	}

	public void setAnswerCode(String answerCode) {
		this.answerCode = answerCode;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Vector getAnswer() {
		return answer;
	}

	public void setAnswer(Vector answer) {
		this.answer = answer;
	}

}
