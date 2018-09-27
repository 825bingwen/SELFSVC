var bgObj=null;
var msgObj=null;
var titleObj=null;
var butObj=null;
var txtObj=null;
function divAlert(str){
    if(bgObj==null && msgObj==null && butObj==null && titleObj==null)
    {
        var msgw,msgh,bordercolor;
        msgw=400;//提示窗口的宽度
        msgh=100;//提示窗口的高度
        titleheight=25 //提示窗口标题的高度
        bordercolor="#62A6E9";//提示窗口的边框颜色
        titlecolor="#62A6E9";//提示窗口的标题颜色
        var sWidth,sHeight;
        sWidth=document.body.offsetWidth;
        sHeight=screen.height;
    
        bgObj=document.createElement("div");
        bgObj.setAttribute('id','bgDiv');
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
        msgObj.setAttribute("id","msgDiv");
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
       
        titleObj=document.createElement("h4");
        titleObj.setAttribute("id","msgTitle");
        titleObj.setAttribute("align","left");
        titleObj.style.margin="0";
        titleObj.style.padding="3px";
        titleObj.style.background=bordercolor;
        titleObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
        titleObj.style.opacity="0.75";
        titleObj.style.border="1px solid " + bordercolor;
        titleObj.style.height="18px";
        titleObj.style.font="12px Verdana, Geneva, Arial, Helvetica, sans-serif";
        titleObj.style.color="white";
        titleObj.style.cursor="pointer";
        titleObj.innerHTML="错误提示";
        
        butObj=document.createElement("h4");
        butObj.setAttribute("id","msgbut");
        butObj.setAttribute("align","center");
        butObj.style.margin="0";
        butObj.style.padding="3px";
        butObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
        butObj.style.opacity="0.75";
        butObj.style.height="18px";
        butObj.style.font="18px Verdana, Geneva, Arial, Helvetica, sans-serif";
        butObj.style.color="black";
        butObj.style.cursor="pointer";
        //but.innerHTML="<img src='images/button_affirm.gif' />";
        butObj.innerHTML="确定";
    
        document.body.appendChild(msgObj);
        document.getElementById("msgDiv").appendChild(titleObj);
    
        txtObj=document.createElement("p");
        txtObj.style.margin="1em 0"
        txtObj.setAttribute("id","msgTxt");
        txtObj.style.color="red";
        txtObj.innerHTML=str;
        document.getElementById("msgDiv").appendChild(txtObj);
        document.getElementById("msgDiv").appendChild(butObj);
        butObj.onblur="this.focus();";
        var srcDocKeydown = document.onkeydown;//alert(document.onkeydown);
        var srcDocKeyup = document.onkeyup;//alert(document.onkeyup);
        var srcDocKeypress = document.onkeypress;//alert(document.onkeypress);
        document.onkeydown = function (e){return false;};
        document.onkeyup = function (e){return false;};
        document.onkeypress = function (e){return false;};
        document.onkeydown=function(e)
        {
            var key=GetKeyCode(e);
            if(key==89 || key==13) {
                document.body.removeChild(bgObj);
                document.getElementById("msgDiv").removeChild(titleObj);
                document.getElementById("msgDiv").removeChild(txtObj);
                document.getElementById("msgDiv").removeChild(butObj);
                document.body.removeChild(msgObj);
                bgObj=null;
                msgObj=null;
                butObj=null;
                titleObj=null;
                document.onkeydown = srcDocKeydown;
                document.onkeyup = srcDocKeyup;
                document.onkeypress = srcDocKeypress;
            }
        }
		butObj.onclick=function(){
                document.body.removeChild(bgObj);
                document.getElementById("msgDiv").removeChild(titleObj);
                document.getElementById("msgDiv").removeChild(txtObj);
                document.getElementById("msgDiv").removeChild(butObj);
                document.body.removeChild(msgObj);
                bgObj=null;
                msgObj=null;
                butObj=null;
                titleObj=null;
		}
    }else{
        txtObj.innerHTML=txtObj.innerHTML+"<br>"+str;
    }
}