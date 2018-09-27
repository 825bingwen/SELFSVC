var bgObj;
var msgObj;
var title;
var but;
function divTip(str){
    var msgw,msgh,bordercolor;
    msgw=400;//提示窗口的宽度
    msgh=100;//提示窗口的高度
    titleheight=25 //提示窗口标题高度
    bordercolor="#62A6E9";//提示窗口的边框颜色
    titlecolor="#62A6E9";//提示窗口的标题颜色
    var sWidth,sHeight;
    sWidth=document.body.offsetWidth;
    sHeight=screen.height;

    bgObj=document.createElement("div");
    bgObj.setAttribute('id','bggDiv');
    bgObj.style.position="absolute";
    bgObj.style.top="0";
    bgObj.style.background="#62A6E9";
    bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
    bgObj.style.opacity="0.6";
    bgObj.style.left="0";
    bgObj.style.width=sWidth + "px";
    bgObj.style.height=sHeight + "px";
    bgObj.style.zIndex = "10000";
    document.body.appendChild(bgObj);
   
    msgObj=document.createElement("div")
    msgObj.setAttribute("id","msggDiv");
    msgObj.setAttribute("align","center");
    msgObj.style.background="white";
    msgObj.style.border="1px solid " + bordercolor;
    msgObj.style.position = "absolute";
    msgObj.style.left = "50%";
    msgObj.style.top = "50%";
    msgObj.style.font="12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif";
    msgObj.style.marginLeft = "-225px" ;
    msgObj.style.marginTop = -75+document.documentElement.scrollTop+"px";
    msgObj.style.width = msgw + "px";
    msgObj.style.height =msgh + "px";
    msgObj.style.textAlign = "center";
    msgObj.style.lineHeight ="25px";
    msgObj.style.zIndex = "10001";
   
    title=document.createElement("h4");
    title.setAttribute("id","msggTitle");
    title.setAttribute("align","left");
    title.style.margin="0";
    title.style.padding="3px";
    title.style.background=bordercolor;
    title.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
    title.style.opacity="0.75";
    title.style.border="1px solid " + bordercolor;
    title.style.height="18px";
    title.style.font="12px Verdana, Geneva, Arial, Helvetica, sans-serif";
    title.style.color="white";
    title.style.cursor="pointer";
    title.innerHTML="错误提示";
    
    but=document.createElement("h4");
    but.setAttribute("id","msggbut");
    but.setAttribute("align","center");
    but.style.margin="0";
    but.style.padding="3px";
    but.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
    but.style.opacity="0.75";
    but.style.height="18px";
    but.style.font="18px Verdana, Geneva, Arial, Helvetica, sans-serif";
    but.style.color="black";
    but.style.cursor="pointer";
    but.innerHTML="<img src='images/button_affirm.gif' />";
    but.innerHTML="确定"

    document.body.appendChild(msgObj);
    document.getElementById("msggDiv").appendChild(title);

    var txt=document.createElement("pp");
    txt.style.margin="1em 0"
    txt.setAttribute("id","msggTxt");
    txt.style.color="red";
    txt.innerHTML=str;
    document.getElementById("msggDiv").appendChild(txt);
    document.getElementById("msggDiv").appendChild(but);
    but.onclick=function()
    {
            document.body.removeChild(bgObj);
            document.getElementById("msggDiv").removeChild(title);
            document.body.removeChild(msgObj);
            bgObj=null;
            msgObj=null;
            back();
   }
}
function removeDiv(key){
	if((key==89)||(key==13)){
			if (bgObj != null && typeof (bgObj) != "undefined") {
		      document.body.removeChild(bgObj);
	    }
			if (document.getElementById("msggDiv") != null && typeof (document.getElementById("msggDiv")) != "undefined") {
		      document.getElementById("msggDiv").removeChild(title);
	    }
			if (msgObj != null && typeof (msgObj) != "undefined") {
		      document.body.removeChild(msgObj);
	    }
	    bgObj=null;
        msgObj=null;	
	}
}