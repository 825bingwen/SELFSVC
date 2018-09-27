(function(){
//-------------------------------------小功能集合
window.Fun=
{
	ie:/*@cc_on!@*/false,//是否为IE
	props:{"class":"className"},
	toarr:function(ul){var ar=[];for(var i=0,n=ul.length;i<n;i++){ar[i]=ul[i];}return ar;},//转为数组
	copy:function(o,ul){if(o._ex){return o;}else{for(var n in ul){o[n]=ul[n];}o._ex=true;return o;}},//扩展属性
	xcopy:function(o,ul){for(var n in ul){reg(n);}function reg(n){o[n]=function(){var c=Fun.toarr(arguments);return ul[n].apply(c.shift(),c);};}}//拷贝属性并将第一个参数作为新属性的this
}

window.$=function(id,tag)
{
	var re=id&&typeof id!="string"?id:document.getElementById(id)||document;
	if(!tag){return Fun.copy(re,Element);}else{return Dom.find(re,tag);}
}
Element=
{
	find:function(tag)//查找属性集合[标签及约束 (如：li[bb>li]  *[class=bbb]  *[src=a.jpg] li[src=a.jpg]) ]
	{
		var m=/(.+)\[(\w*)(\W+)(.*)\]/.exec(tag);
		if(!m){var re=this.getElementsByTagName(tag);for(var i=0,n=re.length;i<n;i++){Fun.copy(re[i],Element);};return re;}
		else
		{
			var arr=[],re=this.getElementsByTagName(m[1]==""?"*":m[1]);
			if(Fun.ie&&Fun.props[m[2]]){m[2]=Fun.props[m[2]];}
			for(var i=0,n=re.length;i<n;i++)
			{if(m[3]==">"&&re[i].parentNode.id==m[2]||m[3]=="="&&re[i].getAttribute(m[2])==m[4]||m[3]=="!="&&re[i].getAttribute(m[2])!=m[4]){arr.push(Fun.copy(re[i],Element));}}
			m=null;return arr;
		}
	},
	attr:function(key,v){if(Fun.ie&&Fun.props[key]){key=Fun.props[key];}if(v){this.setAttribute(key,v);}else{return this.getAttribute(key);}},//获取或设置节点属性
	w:function(v){if(v){this.style.width=v+"px";}else{return this.offsetWidth||this.body.offsetWidth||0;}},	//获取或设置节点宽
	h:function(v){if(v){this.style.height=v+"px";}else{return this.offsetHeight||this.body.offsetHeight||0;}},	//获取或设置节点高
	t:function(v){if(v){this.style.top=v+"px";}else{return this.offsetTop||(this.documentElement.scrollTop||this.body.scrollTop||0);}},	//设置或返回上边距
	l:function(v){if(v){this.style.left=v+"px";}else{return this.offsetLeft||(this.documentElement.scrollLeft||this.body.scrollLeft||0);}},	//设置或返回左边距
	v:function(v){if(v){this.innerHTML?this.innerHTML=v:this.value=v;}else{return this.innerHTML||this.value||"";};},	//设置或返回值
	op:function(v){if(Fun.ie){this.filters.alpha.opacity=v;}else{this.style.opacity=(v/100);}}	//设置层的透明度
}
//页面功能，无法针对节点来执行的
window.Dom=Dom=
{	
	addEvent:function(s,fn){this.attachEvent?this.attachEvent('on'+s,fn):this.addEventListener(s,fn,false);return this;},//添加事件[事件(要去掉前面的on),方法]
	delEvent:function(s,fn){this.detachEvent?this.detachEvent('on'+s,fn):this.removeEventListener(s,fn,false);return this;},//删除事件[事件(要去掉前面的on),方法]
	addDom:function(node,tag,first){var o=node.createElement(tag);first?node.insertBefore(o,node.firstChild):node.appendChild(o);return o;},//创建子节点[节点，要创建的TAG，插入位置]
	delDom:function(node,obj){node.removeChild(obj);},//删除子节点[父节点，要删除节点]
	addImg:function(url){var img=new Image();img.src=url;return img;},//创建缓存图片[图片地址]
	winh:function(){return Math.min(document.documentElement.clientHeight,document.body.clientHeight);},//返回浏览器可用高
	mouseX:function(event){return (event.pageX || (event.clientX +l(document)));},//返回鼠标的X座标
	mouseY:function(event){return (event.pageY || (event.clientY +t(document)));}//返回鼠标的Y座标
}
Fun.xcopy(Dom,Element);

})();




window.Effect=
{
	
	//焦点滚动图[切换时间,数据集合,大图及链接id,切换的效果,小图及链接ID,文字说明及链接ID]
	FocusImg:function(time,foc,fbimg,show,fsimg,ftext)
	{
		var au=$(foc,"a"),bimgs=$(foc,"img[alt!=simg]"),ba=$(fbimg,"dt")[0].find("a")[0],now=1,tm,len=au.length;
		ba.appendChild(Dom.addImg(bimgs[0].src));ba.href=au[0].href;var bi=ba.find("img")[0];bi.alt=bimgs[0].alt;
		var fi=["progid:DXImageTransform.Microsoft.Fade()","progid:DXImageTransform.Microsoft.Wipe(GradientSize=1.0,motion=forward)",
		"progid:DXImageTransform.Microsoft.RandomDissolve(enable=true)","progid:DXImageTransform.Microsoft.Slide(bands=1,SlideStyle=swap)",
		"progid:DXImageTransform.Microsoft.RandomBars(orientation=vertical)","progid:DXImageTransform.Microsoft.Slide(slidestyle=push,bands=1)"];
		var bp=$(fbimg,"dd")[0].find("ul")[0];for(var i=0;i<len;i++){bp.innerHTML+="<li>"+(i+1)+"</li>";}
		var bps=bp.find("li");bps[0].className="show";function pfn(i){bps[i].onclick=function(){go(i);}};for(var i=0;i<len;i++){pfn(i);}
		if(fsimg){var simgs=$(foc,"img[alt=simg]"),simg=$(fsimg);for(var i=0;i<len;i++){simg.innerHTML+="<dl><dt><img src="+simgs[i].src+" /></dt><dd>"+au[i].title+"</dd></dl>";}
		var sx=$(simg,"dl");sx[0].className="show";function sfn(i){sx[i].onclick=function(){go(i);}};for(var i=0;i<len;i++){sfn(i);}}
		if(ftext){var fte=$(ftext);fte.innerHTML="<dt><a href="+au[0].href+">"+bimgs[0].alt+"</a></dt><dd>"+au[0].title+"</dd>";}
		function xunhuan(){if(Fun.ie){bi.style.filter=fi[show];bi.filters[0].Apply();bi.filters[0].Play(duration=1);}
		ba.href=au[now].href;bi.src=bimgs[now].src;bi.alt=bimgs[now].alt;for(var i=0;i<len;i++){bps[i].className="";}bps[now].className="show";
		if(fsimg){for(var i=0;i<len;i++){sx[i].className="";}sx[now].className="show";}
		if(ftext){$(ftext).innerHTML="<dt><a href="+au[now].href+">"+bimgs[now].alt+"</a></dt><dd>"+au[now].title+"</dd>";}(now<len-1)?now++:now=0;}
		function init(){tm=setInterval(xunhuan,time);}function go(n){clearInterval(tm);now=n;xunhuan();init();}init();
	},
	//滚动/切屏效果，[id,子容器/孙容器,方向,速度,上按钮,下按钮,分页切换时间,每次切屏的条数]
	HtmlMove:function(id,tag,path,rate,upbt,downbt,pgtime,lis)
	{
		var c,mous=false,fg=tag.split('/'),o=$(id),as=o.find(fg[1]),fx=(path=="scrollRight"||path=="scrollLeft")?"scrollLeft":"scrollTop",ow=fx=="scrollTop"?as[0].h():as[0].w();
		o.onmouseover=function(){mous=true;};o.onmouseout=function(){mous=false;}
		if(pgtime==null)
		{
			var mx=ow*as.length,mi=0,oldra=rate,os=o.find(fg[0])[0];os.innerHTML+=os.innerHTML;
			if(upbt){$(upbt).onmousedown=function(){down();rate+=3;};$(upbt).onmouseup=function(){rate=oldra;};}
			if(downbt){$(downbt).onmousedown=function(){up();rate+=3;};$(downbt).onmouseup=function(){rate=oldra;};}
			function up(){clearInterval(c);c=setInterval(function(){if(mous){return;}(o[fx]-rate>0)?(o[fx]-=rate):(o[fx]=mx);},30);}
			function down(){clearInterval(c);c=setInterval(function(){if(mous){return;}(o[fx]+rate<mx)?(o[fx]+=rate):(o[fx]=0);},30);}
			if(path=="scrollTop"||path=="scrollLeft"){down();}else{up();}
		}
		else
		{
			var pw=fx=="scrollTop"?o.h():o.w(),pgli=lis||Math.floor((pw+ow/2)/ow),pg=Math.floor((as.length+(pgli-1))/pgli),pgmx=ow*pgli,now=0,mx,d;
			var os=o.find(fg[0])[0];os.innerHTML+=os.innerHTML;d=setInterval(function(){go_to((path=="scrollTop"||path=="scrollLeft")?true:false);},pgtime);
			if(upbt){$(upbt).onmousedown=function(){clearInterval(d);go_to(true);d=setInterval(function(){go_to(true);},pgtime);}}
			if(downbt){$(downbt).onmousedown=function(){clearInterval(d);go_to(false);d=setInterval(function(){go_to(false);},pgtime);}}
			if(fg[2]){var pf=o.find(fg[2])[0];};function pfs(vs){if(fg[2]){pf.style.display="block";pf.style.left=vs+"px";}};function pfscl(){if(fg[2]){pf.style.display="none";}}
			function go_to(fxs)
			{
				if(mous){return;};var ex;
				if(fxs){if(now<pg){now++;}else{now=1;o[fx]=0;}pfs((now-1)*pgmx);mx=now*pgmx;ex=setInterval(function(){(o[fx]+rate<mx)?(o[fx]+=rate):o[fx]=mx;if(o[fx]==mx){clearInterval(ex);ex=null;pfscl();}},5);}
				else{if(now>0){now--;}else{now=pg-1;o[fx]=pg*pgmx;}pfs((now+1)*pgmx);mx=now*pgmx;ex=setInterval(function(){(o[fx]-rate>mx)?(o[fx]-=rate):o[fx]=mx;if(o[fx]==mx){clearInterval(ex);ex=null;pfscl();}},5);}
			}
		}
	}	
}