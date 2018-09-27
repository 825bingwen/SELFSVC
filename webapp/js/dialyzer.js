function correctPNG() // correctly handle PNG transparency in Win IE 5.5 & 6. 
{ 
    var arVersion = navigator.appVersion.split("MSIE");
    var version = parseFloat(arVersion[1]);
    if ((version >= 5.5) && (document.body.filters)) 
    { 
       for (var j = 0; j < document.images.length; j++) 
       { 
          var img = document.images[j];
          var imgName = img.src.toUpperCase();
          if (imgName.substring(imgName.length-3, imgName.length) == "PNG") 
          { 
             var imgID = (img.id) ? "id='" + img.id + "' " : "";
             var imgClass = (img.className) ? "class='" + img.className + "' " : "";
             var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' ";
             var imgStyle = "display:inline-block;" + img.style.cssText 
             if (img.align == "left") imgStyle = "float:left;" + imgStyle 
             if (img.align == "right") imgStyle = "float:right;" + imgStyle 
             if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle 
             var strNewHTML = "<span " + imgID + imgClass + imgTitle 
             + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";" 
             + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader" 
             + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>" 
             img.outerHTML = strNewHTML 
             j = j - 1;
          } 
       } 
    }     
} 
window.attachEvent("onload", correctPNG); 

function startTime(){
    getNowTime();
    setInterval('getNowTime()',1000*60);
}
function getNowTime(){

	var today=new Date() 
	var years=today.getFullYear(); 
	var months=checkTime(today.getMonth()+1); 
	var d=checkTime(today.getDate() );
	var h=today.getHours();
	var m=checkTime(today.getMinutes() );
	//var s=checkTime(today.getSeconds() );
	// add a zero in front of numbers<10 
	var weekday=new Array("星期日","星期一","星期二" ,"星期三" ,"星期四","星期五","星期六" );
	var w=weekday[today.getDay()] ;
	//var shtml=years+"年"+months+"月"+d+"日 "+" "+h+":"+m+":"+s+" "+w;
	var shtml=years+"年"+months+"月"+d+"日 "+" "+h+":"+m+" "+w;
	document.getElementById('_ShowTime').innerHTML= shtml;
	
	/** 
	var url = "SubsLoginAction.do?actionCase=getCurrentDate";
	var loader = new net.ContentLoader(url, netload = function () {
	   		try {
	   			var resXml = this.req.responseText;	    			
	   			document.getElementById('_ShowTime').innerHTML = resXml;
	   		} catch (e) {}
	}, null, "POST", "", null);
	**/
}
function checkTime(i) { 
	if (i<10) {i="0" + i} 
	return i 
}

function getCurrentTime()
{
	var now = new Date();
	
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	var day = now.getDate();
	var h = now.getHours();
	var min = now.getMinutes();
	var sec = now.getSeconds();
	
	return year + "-" + month + "-" + day + " " + h + ":" + min + ":" + sec;  
}

//获取当前时间，格式yyyyMMddHHmmss
function getCurrentTime1()
{
	var now = new Date();
	
	var year = now.getFullYear().toString();
	
	var month = now.getMonth() + 1;
	if (month < 10)
	{
		month = "0" + month.toString();
	}
	
	var day = now.getDate();
	if (day < 10)
	{
		day = "0" + day.toString();
	}
	
	var h = now.getHours();
	if (h < 10)
	{
		h = "0" + h.toString();
	}
	
	var min = now.getMinutes();
	if (min < 10)
	{
		min = "0" + min.toString();
	}
	
	var sec = now.getSeconds();
	if (sec < 10)
	{
		sec = "0" + sec.toString();
	}
		
	return year + month + day + h + min + sec; 
}
/**
 * 检查用户是不是在黑名单中
 */
function checkBlackList(telNumber)
{
	var url="${sessionScop.basePath}login/checkBlackList.action";
	var param="servnumber="+telNumber;
	var data = "";
	var contentLoader=new net.ContentLoaderSynchro(url,function(){
		data=this.req.responseText;
	},null,"POST",param,"application/x-www-form-urlencoded");
	if(data == "no")
	{
		return false;
	}
	else
	{
		return true;
	}
}