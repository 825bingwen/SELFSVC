<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.DictItemPO" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>

<%
	String keyForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_KEY");
	if (keyForPopWindow == null || "".equals(keyForPopWindow))
	{
		keyForPopWindow = "";
	}
	else
	{
		keyForPopWindow = (" (�밴" + keyForPopWindow + "��)"); 
	}
     
     //add begin m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032 
     //�ӻ�����ȡ���ֵ����groupidΪUserSatfy��EChannelDefy��ֵ
     List<DictItemPO> itemListUerSatfy = (List<DictItemPO>) PublicCache.getInstance().getCachedData("UserSatfy");
     if (itemListUerSatfy == null)
     {
     	itemListUerSatfy = new ArrayList<DictItemPO>();
     }
     
     List<DictItemPO> itemListECDefy = (List<DictItemPO>) PublicCache.getInstance().getCachedData("EChannelDefy"); 
     if (itemListECDefy == null)
     {
     	itemListECDefy = new ArrayList<DictItemPO>();
     }
     
     //�Ӳ������л�ȡ��Ҫ�����Ĳ˵�id
     String surveyFlag = (String) PublicCache.getInstance().getCachedData("SURV_" + curMenuId);
     
     //�Ӳ�������ȡ����ʱʱ��
     String surveyOutTime = (String) PublicCache.getInstance().getCachedData("SURV_WIN_TIMEOUT");
     //add end m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032 
     
	NserCustomerSimp customerInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
	
	String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
	String usimFlag = (String) session.getAttribute("usimFlag");
	
	// add begin qWX279398 2015-5-13 OR_SD_201503_942_ɽ��_�����ն���ʾ��USIM
	// ʡ����Ϣ
	String provinceSDInfo = Constants.PROOPERORGID_SD;
	
	// �ӻ����ȡUSIM��������Ϣ
	String alertMessage = CommonUtil.getParamValue("SH_USIM_MESSAGE");
	
	// USIM���Ƿ���ʾ�Ŀ���
	String usimSwitch = (String) PublicCache.getInstance().getCachedData(Constants.SH_USIMCHANGE_FLAG);
	
	// add end qWX279398 2015-5-13 OR_SD_201503_942_ɽ��_�����ն���ʾ��USIM	

// Ʒ��
String brand = "";

// ����
String name = "";

// ����
String subage = "";

// ����
String subscore = "";
String telnum = "";

if (!Constants.PROOPERORGID_NX.equalsIgnoreCase(province) 
		&& customerInfor != null && customerInfor.getServNumber() != null && customerInfor.getCustomerName() != null)
{
	telnum = customerInfor.getServNumber();
	if (customerInfor.getBrandName() != null && customerInfor.getBrandName().length() > 0)
	{
		brand = "Ʒ�ƣ�"+customerInfor.getBrandName();
	
	}
	if (customerInfor.getCustomerName() != null && customerInfor.getCustomerName().length() > 0 && Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
	{
	    //modify begin l00190940 2011/10/25 ��ǰ״̬��ѯ��ʱ����ʾ�û�ȫ�����̶���ʾ����**�����ֵ����һ���֣����û������Σ���ʾ**�� ����ɽ��
		String fullName = customerInfor.getCustomerName();
		name = fullName.substring(fullName.length()-1);
	    name = "**" + name;
		//modify end l00190940 2011/10/25 ��ǰ״̬��ѯ��ʱ����ʾ�û�ȫ�����̶���ʾ����**�����ֵ����һ���֣����û������Σ���ʾ**�� ����ɽ��
		name = "������" + name;
	}
	//modify begin l00190940 2011/10/18 ��ǰ״̬��ѯ��ɽ��ʡ����ʾ��+��*���������
	if (customerInfor.getCustomerName() != null && customerInfor.getCustomerName().length() > 0 && !Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
	{	
		name = customerInfor.getCustomerName();
	//	name = customerInfor.getCustomerName().substring(0,1);
	//	for(int i=0;i<customerInfor.getCustomerName().length() - 1;i++)
	// 	{
	//		name = name + "*" ;
	//	}
		name = "������" + name;
	}
	//modify end l00190940 2011/10/18 ��ǰ״̬��ѯ��ɽ��ʡ����ʾ��+��*���������
	//add begin l00190940 2011/10/18 OR_HUB_201108_1001
	if (customerInfor.getSubage() != null && customerInfor.getSubage().length() > 0)
	{
	    subage = "���䣺" + customerInfor.getSubage();
	}
	if (customerInfor.getSubscore() != null && customerInfor.getSubscore().length() > 0)
	{
	    //add begin m00227318 2012/09/14 eCommerce V200R003C12L09  OR_huawei_201209_33
	    //������ʾ�����û��֣���
	    if (Constants.PROOPERORGID_HUB.equalsIgnoreCase(province))
	    {
	        subscore = "���û��֣�" + customerInfor.getSubscore();
	    }
	    else
	    {
	    //add end m00227318 2012/09/14 eCommerce V200R003C12L09  OR_huawei_201209_33
		    subscore = "���֣�" + customerInfor.getSubscore();
		}
	}
	//add end l00190940 2011/10/18 OR_HUB_201108_1001
%>
	<div class="blank20"></div>
	
	<%-- modify begin sWX219697 2015-1-22 15:54:27 OR_HUB_201408_620_���������ն˸İ�
		�����İ棬�����Ϊ��ɫ
	--%>
	<%
	if(Constants.PROOPERORGID_HUB.equalsIgnoreCase(province))
	{
	%>
		<span class="fs16" >
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=name %>&nbsp;&nbsp;&nbsp;&nbsp;<%="WBAND".equals(customerInfor.getNetType()) || "��������".equals(customerInfor.getNetType()) ?"�����ʺ�":"�ֻ�����"  %>��<%=customerInfor.getServNumber()%>&nbsp;&nbsp;&nbsp;&nbsp;<%=brand %>&nbsp;&nbsp;&nbsp;&nbsp;<%=subage %>&nbsp;&nbsp;&nbsp;&nbsp;<%=subscore %>
		</span>
	<%
	}
	else
	{
	%>
		<span class="yellow fs16" >
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		��ӭʹ�������ն�&nbsp;&nbsp;<%="WBAND".equals(customerInfor.getNetType()) || "��������".equals(customerInfor.getNetType()) ?"�����ʺ�":"�ֻ�����"  %>��<%=customerInfor.getServNumber()%>&nbsp;&nbsp;&nbsp;&nbsp;<%=name %>&nbsp;&nbsp;&nbsp;&nbsp;<%=brand %>&nbsp;&nbsp;&nbsp;&nbsp;<%=subage %>&nbsp;&nbsp;&nbsp;&nbsp;<%=subscore %>
		</span>
		
	<%
	} 
	%>
	<%-- modify end sWX219697 2015-1-22 15:54:27 OR_HUB_201408_620_���������ն˸İ�--%>
<%
}
else
{
%>
	<div class="blank20"></div>
	<span class="yellow fs16 ml10" >
	&nbsp;&nbsp;
	</span>
<% 
}
%>
<!-- �������� -->
<input type="hidden" id="totScore" name="totScore" value="0" />

<!-- �������� -->
	<%
	for (int i = 0; i < itemListUerSatfy.size(); i++) 
	{
	%>
		<input type="hidden" id="<%= itemListUerSatfy.get(i).getDictid() %>_Score" name="<%= itemListUerSatfy.get(i).getDictid() %>_Score" value="0" />
	<%
	}
	%>
		
	<!-- �ʹ�õ������� -->
	<input type="hidden" id="favorEC" name="favorEC" value="ECSelfsvcTerm" />
	

<!--������ʾ��-->
<div class="openwin_tip" id="openWin_common">
  	<div class="bg"></div>
	<div class=" blank60"></div>
	<div class=" blank25"></div>
	<div class="  blank10n"></div>
	<p class="fs28 lh2 pl20" id="winText_common" name="winText_common" style="margin-right: 15px;">
	������Ϣ
	</p>
	<div class="tc">
    <div class=" clear "></div>
    <div class=" blank30 "></div>
		<a href="javascript:void(0)" style="font-size:20px;" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">ȡ��<%=keyForPopWindow %></a> 
	</div>
</div>

<!-- �ȴ���ʾ�� -->
<div class="openwin_tip" id="openWin_jd">
  	<div class="bg"></div>
	<div class=" blank60"></div>
	<div class=" blank60"></div>
	<div class="  blank10n"></div>
	<p class="fs28 lh2 pl20" id="winText_jd" name="winText_jd">
	������Ϣ
	</p>
	<div class="tc">
    <div class=" clear "></div>
    <div class=" blank30 "></div>
		 
	</div>
</div>

<!-- ��ɫ������ʾ��Ϣ -->
<div class="popup_confirm" id="openWin_ErrorMsg">
	<div class="bg"></div>
	<div class="tips_title">��ʾ��</div>
	<div class="fs24 red pl55 pr30 pt40 line_height_12 h200" id="winText_ErrorMsg">
		
  	</div>
	<div class="btn_box tc mt20">
		<span class=" inline_block ">
			<a class="btn_bg_146" href="javascript:void(0);" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a>
		</span>
	</div>
</div>

<!-- add begin CKF76106 2012/09/02 R003C12L08n01 OR_HUB_201207_875 -->
<!-- �ɹ���ʾ��Ϣ -->
<div class="popup_confirm" id="openWin_successMsg">
	<div class="bg"></div>
	<div class="tips_title">��ʾ��</div>
	
	<%--
	modify by sWX219697 2015-1-29 16:55:32 OR_HUB_201408_620_�����ն˽���İ��Ż�
	�����İ��������ɫ��Ϊ��ɫblue
	--%>
	<%
	if(Constants.PROOPERORGID_HUB.equalsIgnoreCase(province))
	{
	%>
	<div class="fs24 blue pl55 pr30 pt40 line_height_12 h200" id="winText_successMsg">
	<%
	}
	else
	{
	%>
		<div class="fs24 yellow pl55 pr30 pt40 line_height_12 h200" id="winText_successMsg">
	<%
	}
	%>
	
  	</div>
	<div class="btn_box tc mt20">
		<span class=" inline_block ">
			<a class="btn_bg_146" href="javascript:void(0);" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȷ��</a>
		</span>
	</div>
</div>
<!-- add end CKF76106 2012/09/02 R003C12L08n01 OR_HUB_201207_875 -->

<!--�������ڲ�ѯdiv-->
<div class="popupWin fs28 credit_pls_wait" id="qryWaitLoading">
	<div class="bg"></div>
    <p class="mt120"><img src="${sessionScope.basePath }images/loading.gif" alt="��ѯ��..." /></p>
    
    <%-- modify begin hWX5316476 2015-6-24 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>
   	<p class="tips_txt"><%=CommonUtil.getParamValue(Constants.QRY_WAITLOADING_MSG,"���ڲ�ѯ�����Ժ�......") %></p> 
   	<%-- modify end hWX5316476 2015-6-24 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>             
</div>

<!--�������ڴ���div-->
<div class="popupWin fs28 credit_pls_wait" id="recWaitLoading">
	<div class="bg"></div>
    <p class="mt120"><img src="${sessionScope.basePath }images/loading.gif" alt="������..." /></p>
    
    <%-- modify begin hWX5316476 2015-6-24 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>
   	<p class="tips_txt"><%=CommonUtil.getParamValue(Constants.REC_WAITLOADING_MSG,"���ڴ��������Ժ�......") %></p> 
   	<%-- modify end hWX5316476 2015-6-24 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>               
</div>

<!--�ɷ����󣬵������ڴ���div-->
<div class="popupWin fs28 credit_pls_wait" id="chargeWaitLoading">
	<div class="bg"></div>
    <p class="mt120"><img src="${sessionScope.basePath }images/loading.gif" alt="������..." /></p>
   	<p class="tips_txt">�𾴵Ŀͻ������ĳ�ֵ���������ѱ����գ���ȴ����������</p>                     
</div>

<script type="text/javascript">
alertError = function(content){
	document.getElementById('winText_common').innerHTML = content;
	wiWindow = new OpenWindow("openWin_common",708,392);//�򿪵�����������					
};
alertJd = function(content){
	document.getElementById('winText_jd').innerHTML = content;
	wiWindow = new OpenWindow("openWin_jd",708,392);//�򿪵�����������					
};
alertJdNX = function(content){
	document.getElementById('winText_jd').innerHTML = content;
	jdWindow = new OpenWindow("openWin_jd",708,392);//�򿪵�����������					
};
alertRedErrorMsg = function(content)
{
	document.getElementById('winText_ErrorMsg').innerHTML = content;
	wiWindow = new OpenWindow("openWin_ErrorMsg", 708, 392);
};
//add begin CKF76106 2012/09/02 R003C12L08n01 OR_HUB_201207_875
alertSuccessMsg = function(content)
{
	document.getElementById('winText_successMsg').innerHTML = content;
	wiWindow = new OpenWindow("openWin_successMsg", 708, 392);
};
//add end CKF76106 2012/09/02 R003C12L08n01 OR_HUB_201207_875
 
// �������ڲ�ѯDIV
openQryWaitLoading = function(id){
	wiWindow = new OpenWindow("qryWaitLoading", 804, 515);
	
}

// �������ڴ���DIV
openRecWaitLoading = function(id){
	wiWindow = new OpenWindow("recWaitLoading", 804, 515);
}

// �ɷ����󣬵������ڴ���DIV
openChargeWaitLoading = function(id){
	wiWindow = new OpenWindow("chargeWaitLoading", 804, 515);
}

// �������ڴ���DIV(����)
openRecWaitLoading_NX = function(id){
<%
	if(Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
	{
%>
		wiWindow = new OpenWindow("recWaitLoading", 804, 515);
<%
	}
%>
}

//add begin m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032 
var timeoutSurv;
// �����û�����ȵ���Ի���
openSurveyDialog = function(id){

	<%
	if ("1".equals(surveyFlag))
	{
	%>		
	    wiWindowSurv = new OpenWindow("surveyDialog", 889, 670);	    
	    
		//ҳ�泬ʱ�رղ��ύ�����ݿ⣬�Ժ���Ϊ��λ
    	timeoutSurv = setTimeout("closeTimeoutFun()", <%= surveyOutTime%>);
	<%
	}
	%>    
}
    
    //���������У�ѡ�еİ�ť���̣�btn������İ�ť��btClass��ťҪ��ɵ���ʽ
	function totScoreBtnClick(btn,btClass,totScoreValue)
	{
	    var btns = document.getElementById('totScoreTable').getElementsByTagName('a');
	    for (i = 0; i < btns.length; i++)
	    {
		    btns[i].className = btClass;
	    }
	    btn.className = btClass+'on';
	                
	    document.getElementById("totScore").value = totScoreValue;
	   // alert(document.getElementById("totScore").value);
	    
	    	
	}
	
    //�ھ�������б��У�ÿ��ѡ��һ�������ӣ���ť���̲�����ʾ
    //btn������İ�ť��btClass:��ťҪ��ɵ���ʽ��uerSatfyid:Ҳ����id����Ӧ�ֵ����dictid��speciScoreValue:ѡ�����ж�Ӧ�ķ�����Ҳ���������ݿ��д���Ĳ���
	function speciScoreBtnClick(btn,btClass,uerSatfyid,speciScoreValue)
	{
	    //����Ϊ��λ
	    var btns = document.getElementById(uerSatfyid).getElementsByTagName('a');
		//���������а�ť��ʽ����
	    for (i = 0; i < btns.length; i++)
	    {
		    btns[i].innerHTML = '';
		    btns[i].className = btClass;
	    }
	    //����İ�ť��ʽ�ı�
	    btn.innerHTML = '��';
	    btn.className = btClass + 'on';
	    //��input��ֵ
	    document.getElementById(uerSatfyid + '_Score').value = speciScoreValue;
	   // alert(document.getElementById(uerSatfyid + '_Score').value);
	}
				
	//�ڳ��õ�������ѡ���У�ѡ�еİ�ť���̣�btn������İ�ť��btClass��ť��ʽ��favorECValue��ȡֵ���������ݿ��д���Ĳ���
	function favorECBtnClick(btn,btClass,favorECValue)
	{
	    var btns = document.getElementById('favorECTable').getElementsByTagName('a');
	    for (i = 0; i < btns.length; i++)
	    {
		    btns[i].className = btClass;
	    }
	    btn.className = btClass+'on';
	    document.getElementById("favorEC").value = favorECValue;
	   // alert(document.getElementById("favorEC").value);
	}
	
	//����Ajax�ύ�ķ���
    function subUSatfyAjax()
    {        
        //�ӱ����л�ȡ����
        //�û���������
        var totScore = document.getElementById("totScore").value;
        
        //�û���ϲ��ʹ�õ�������
        var favorEC = document.getElementById("favorEC").value;
        
        //�û��������֣����������ֵ�ֵƴ�ӳ�һ���ַ������������
        var specifySatfyScore = ""; 
	   <%
	       for(int i = 0; i < itemListUerSatfy.size(); i++)
	       {
	   %>
	           //alert('<%=itemListUerSatfy.get(i).getDictid() %>'+'_Score');
	           //alert(document.getElementById('<%=itemListUerSatfy.get(i).getDictid() %>'+'_Score').value);
	           specifySatfyScore = specifySatfyScore + "<%=itemListUerSatfy.get(i).getDictid() %>" + ","
	                               + document.getElementById('<%=itemListUerSatfy.get(i).getDictid() %>'+'_Score').value + ";";

	   <%
	       }
	   %>
	   
	   //alert(specifySatfyScore);
	  // alert(document.getElementById("speciSatfyScore").value);
	   //Ҫ������������ַ���                                                   
	   var postStr = "totScore=" + totScore + "&speciSatfyScore=" + specifySatfyScore + "&favorEC=" + favorEC;
	  // alert(postStr);
	  
	  var url = "${sessionScope.basePath }common/surveyUserSatfy.action";
	  
	  new net.ContentLoader(url, null, null, "POST", postStr, null);	   
    }

    //ҳ�泬ʱ�ر�ִ�еĺ���
    function closeTimeoutFun()
    {
        //alert();
        subUSatfyAjax();
        wiWindowSurv.close();
    }
    
//add end m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032 

</script>
<!-- add begin m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032  -->
<!-- �û�����ȵ��鵯���� -->
<div class="openwin_big1 " id="surveyDialog" >	
	<div class="bg" ></div>
	<div class="blank30 tc fs24 pt15 pos_rel" >
			�����ƶ����������ն˿ͻ�����Ȳ���
	</div>
	
	<div class="blank30">
        <p class="fs18 lh2 yellow pl45" >�𾴵ĺ����ƶ��ͻ������ã�</p>
	</div>
	<div class="blank50 width800 fs16 1h2 pl45" >
		    Q1.��л��ʹ���й��ƶ����������ն˰���ҵ�񣬶��ڸղŵķ�����̣�����������������Σ�����1��10�������ۣ�10�ַǳ��ã�1�ַǳ��
	</div>
	<div class="blank40 width800 fs14 1h2 pl45" >
	    <table width="100%" class="tb_blue08" id="totScoreTable" >
	        <tr>
	            <s:generator val="%{'10,9,8,7,6,5,4,3,2,1'}" separator="," id="tot_Satfy">
	                 <s:iterator>
	                      <td>
	                          <a href="#" class="b_bt2_1" onclick="totScoreBtnClick(this,'b_bt2_1','<s:property/>')">
		    	                  <s:property/>
		    	              </a>
	                      </td>
	                 </s:iterator>
	            </s:generator>
	        </tr>
	    </table>
    </div>
		<div class="blank30 width900 fs16 1h2 pl45" >
		    Q2.������ԣ������������й��ƶ��������ն˷��������¸�����ı��֣�
		</div>
		<div class="blank60 height260 width800 fs16 1h2 pl45" >
		     <table width="100%" class="tb_blue07" id="speciScoreTable">
		    	<tr>
		    	    <th class="width140"></th>  
		    	    <th>10�֣�<br/>�ǳ���</th>  
		    	    <th>9��</th>  
		    	    <th>8��</th>  
		    	    <th>7��</th>  
		    	    <th>6��</th>  
		    	    <th>5��</th>
		    	    <th>4��</th>  
		    	    <th>3��</th>  
		    	    <th>2��</th>  
		    	    <th>1�֣�<br/>�ǳ���</th>
		    	</tr>
		    	<%
		    	for(int i = 0; i < itemListUerSatfy.size(); i++) 
		    	{
		    	%>
		    	    <tr id="<%= itemListUerSatfy.get(i).getDictid() %>">
		    	        <th><%= itemListUerSatfy.get(i).getDictname() %></th>
		    	        <td><a href="#" class="b_bt2_1" onclick="speciScoreBtnClick(this,'b_bt2_1','<%= itemListUerSatfy.get(i).getDictid() %>','10')"></a></td> 
		    	        <td><a href="#" class="b_bt2_1" onclick="speciScoreBtnClick(this,'b_bt2_1','<%= itemListUerSatfy.get(i).getDictid() %>','9')"></a></td>  
		    	        <td><a href="#" class="b_bt2_1" onclick="speciScoreBtnClick(this,'b_bt2_1','<%= itemListUerSatfy.get(i).getDictid() %>','8')"></a></td> 
		    	        <td><a href="#" class="b_bt2_1" onclick="speciScoreBtnClick(this,'b_bt2_1','<%= itemListUerSatfy.get(i).getDictid() %>','7')"></a></td>  
		    	        <td><a href="#" class="b_bt2_1" onclick="speciScoreBtnClick(this,'b_bt2_1','<%= itemListUerSatfy.get(i).getDictid() %>','6')"></a></td> 
		    	        <td><a href="#" class="b_bt2_1" onclick="speciScoreBtnClick(this,'b_bt2_1','<%= itemListUerSatfy.get(i).getDictid() %>','5')"></a></td>  
		    	        <td><a href="#" class="b_bt2_1" onclick="speciScoreBtnClick(this,'b_bt2_1','<%= itemListUerSatfy.get(i).getDictid() %>','4')"></a></td>
		    	        <td><a href="#" class="b_bt2_1" onclick="speciScoreBtnClick(this,'b_bt2_1','<%= itemListUerSatfy.get(i).getDictid() %>','3')"></a></td>  
		    	        <td><a href="#" class="b_bt2_1" onclick="speciScoreBtnClick(this,'b_bt2_1','<%= itemListUerSatfy.get(i).getDictid() %>','2')"></a></td> 
		    	        <td><a href="#" class="b_bt2_1" onclick="speciScoreBtnClick(this,'b_bt2_1','<%= itemListUerSatfy.get(i).getDictid() %>','1')"></a></td>
		    	    </tr>
		    	<%
		    	}
		    	%>
		    </table>
		</div>
		<div class="blank30 width800 fs16 1h2 pl45" >
		    Q3.���ʣ����ʹ�õĵ��������ǣ�
		</div>
		<div class="blank30 width800 1h2 pl45" >
		     <table width="100%" class="tb_blue08" id="favorECTable" >
		         <tr>
		         
		         <%
		         for (int i = 0; i < itemListECDefy.size(); i++)
		         {
		         	if (i == 0)
		         	{
		         	%>
		         	<!-- Ĭ�������ն���ѡ��״̬ -->
		             <td>
		    	         <a href="#" class="b_bt2_2on" onclick="favorECBtnClick(this,'b_bt2_2','<%=itemListECDefy.get(0).getDictid() %>')">
		    	             <%=itemListECDefy.get(0).getDictname() %>
		    	         </a>
		    	     </td>
		         	
		         	<%		         	
		         	}
		         	else
		         	{
		         	%>
		         	 <td>
		    	         <a href="#" class="b_bt2_2" onclick="favorECBtnClick(this,'b_bt2_2','<%=itemListECDefy.get(i).getDictid() %>')">
		    	             <%=itemListECDefy.get(i).getDictname() %>
		    	         </a>
		    	     </td>
		         	
		         	<%
		         	}
		         %>
		    	    
		    	 <%
		    	 }
		    	 %>
		    	 </tr>
		    </table>
		</div>

		<div class="blank30">
		    <p class="fs18 lh2 yellow pl45" >лл���������֧�֣�ף��������죡</p>
		</div>
		<div class="btn_box tc">
	    	<span class=" mr10 inline_block "><a title="����" href="#" class="btn_bg_146" onmousedown="this.className='key_down';clearTimeout(timeoutSurv);" onmouseup="this.className='btn_bg_146';subUSatfyAjax();wiWindowSurv.close();">����</a></span>
	        <span class=" inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down';clearTimeout(timeoutSurv);" onmouseup="this.className='btn_bg_146';wiWindowSurv.close();">�Ҳ������</a></span>
        </div>
</div>
<!-- add end m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032  -->