var _oPopUp=null;
function _openDummyKey(currentlyObj,length){
	_oPopUp=window.createPopup();
	var _popBody=_oPopUp.document.body;
	_popBody.style.backgroundColor = "#FFFF99";
	_popBody.style.border = "solid black 1px";
	_WriteToPopup(_oPopUp,currentlyObj,length);
	_oPopUp.show(0,25,300,238,currentlyObj);
}
function _WriteToPopup(oPopUp,currentlyObj,length){

	var strHTML='';
	
	strHTML+='<html>';
	strHTML+='<head>';
	strHTML+='<meta http-equiv="Content-Type" content="text/html; charset=gb2312">';
	strHTML+='<style type="text/css">';
	strHTML+='.btn_css_td{width: 50px; height: 45px; font-size: 18; font: bold;}';
	strHTML+='.btn_css_btn{width: 80px; height: 45px; font-size: 18; font: bold;}';
	strHTML+='</style>';
	strHTML+='</head>';
	
	strHTML+='<script language="javascript">';
	strHTML+='var _currentlyObj = parent.document.getElementById("'+currentlyObj.id+'");';
	strHTML+='var _length = '+length+';';
	
	strHTML+='function _cancelDisabled(buttenObj){'
	strHTML+='	buttenObj.disabled="";';
	strHTML+='}';
	
	// 设定值
	strHTML+='function _btnSetValue(value) {';
	strHTML+='	if(_currentlyObj.value.length < _length){';
	strHTML+='		if((_currentlyObj.value.length+value.length) > _length){';
	strHTML+='			return;';
	strHTML+='		}';
	strHTML+='		_currentlyObj.value += value;';
	strHTML+='		if(_currentlyObj.value.length == _length){';
	strHTML+='			parent._oPopUp.hide();';
	strHTML+='			return;';
	strHTML+='      };';
	strHTML+='	}';
	strHTML+='}';
	
	// 更正
	strHTML+='function _btnDelDown() {';
	strHTML+='	_currentlyObj.value = _currentlyObj.value.substring(0, _currentlyObj.value.length - 1); ';
	strHTML+='}';
	
	// 清除
	strHTML+='function _btnReset(){';
	strHTML+='	_currentlyObj.value = "";';
	strHTML+='}';
	
	// 取消
	strHTML+='function _btnEnterDown() {';
	strHTML+='	parent._oPopUp.hide(); ';
	strHTML+='}';
	
	strHTML+='</script>';
	
	strHTML+='<body style="margin:0; border:0;overflow:hidden;" oncontextmenu="javascript:event.returnValue=false;" onselectstart="javascript:event.returnValue=false;">';
	strHTML+='<table align="center" cellpadding="3" cellspacing="0" border="0" style="TABLE-LAYOUT: fixed; width: 300px; height: 238px; border-collapse: collapse; cursor: default;margin-top: 0px; background:url(images/lo_bg.gif) repeat-x; border:1px solid #000;">';
	
	// 第一行
	strHTML+='<tr height="50px">';
	strHTML+='<td width="60px" align="center" valign="middle">';
	strHTML+='<input type="button" value="1" class="btn_css_td" onclick="_btnSetValue(1)" ondblclick="_btnSetValue(1)"></td>';
	strHTML+='<td width="60px" align="center" valign="middle">';
	strHTML+='<input type="button" value="2" class="btn_css_td" onclick="_btnSetValue(2)" ondblclick="_btnSetValue(2)"></td>';
	strHTML+='<td width="60px" align="center" valign="middle">';
	strHTML+='<input type="button" value="3" class="btn_css_td" onclick="_btnSetValue(3)" ondblclick="_btnSetValue(3)"></td>';
	strHTML+='<td width="80px" align="center" valign="middle">';
	strHTML+='<input type="button" value="取 消" class="btn_css_btn" onclick="_btnEnterDown()" ondblclick="_btnEnterDown()"></td>';
	strHTML+='</tr>';
	
	// 第二行
	strHTML+='<tr height="50px">';
	strHTML+='<td width="60px" align="center" valign="middle">';
	strHTML+='<input type="button" value="4" class="btn_css_td" onclick="_btnSetValue(4)" ondblclick="_btnSetValue(4)"></td>';
	strHTML+='<td width="60px" align="center" valign="middle">';
	strHTML+='<input type="button" value="5" class="btn_css_td" onclick="_btnSetValue(5)" ondblclick="_btnSetValue(5)"></td>';
	strHTML+='<td width="60px" align="center" valign="middle">';
	strHTML+='<input type="button" value="6" class="btn_css_td" onclick="_btnSetValue(6)" ondblclick="_btnSetValue(6)"></td>';
	strHTML+='<td width="80px" align="center" valign="middle">';
	strHTML+='<input type="button" value="更 正" class="btn_css_btn" onclick="_btnDelDown()" ondblclick="_btnDelDown()"></td>';
	strHTML+='</tr>';
	
	// 第三行
	strHTML+='<tr height="50px">';
	strHTML+='<td width="60px" align="center" valign="middle">';
	strHTML+='<input type="button" value="7" class="btn_css_td" onclick="_btnSetValue(7)" ondblclick="_btnSetValue(7)"></td>';
	strHTML+='<td width="60px" align="center" valign="middle">';
	strHTML+='<input type="button" value="8" class="btn_css_td" onclick="_btnSetValue(8)" ondblclick="_btnSetValue(8)"></td>';
	strHTML+='<td width="60px" align="center" valign="middle">';
	strHTML+='<input type="button" value="9" class="btn_css_td" onclick="_btnSetValue(9)" ondblclick="_btnSetValue(9)"></td>';
	strHTML+='<td width="80px" align="center" valign="middle">';
	strHTML+='<input type="button" value="清 除" class="btn_css_btn" onclick="_btnReset()" ondblclick="_btnReset()"></td>';
	strHTML+='</tr>';
	
	// 第四行
	strHTML+='<tr height="50px">';
	strHTML+='<td width="60px" align="center" valign="middle">';
	strHTML+='<input type="button" value="0" class="btn_css_td" onclick="_btnSetValue(0)" ondblclick="_btnSetValue(0)"></td>';
	strHTML+='<td width="60px" align="center" valign="middle">';
	strHTML+='<input type="button" value="00" class="btn_css_td" onclick="_btnSetValue(\'00\')" ondblclick="_btnSetValue(\'00\')"></td>';
	strHTML+='<td width="60px" align="center" valign="middle">';
	strHTML+='<input type="button" value="." class="btn_css_td" onclick="_btnSetValue(\'.\')" ondblclick="_btnSetValue(\'.\')"></td>';;
	strHTML+='<td width="80px" align="center" valign="middle">';
	strHTML+='<input type="button" value="确 定" class="btn_css_btn" onclick="_btnEnterDown()" ondblclick="_btnEnterDown()"></td>';
	strHTML+='</tr>';
	
	strHTML+='</table>';
	strHTML+='</body>';
	strHTML+='</html>';
	_oPopUp.document.write(strHTML);
}