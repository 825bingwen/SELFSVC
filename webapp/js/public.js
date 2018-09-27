﻿﻿var Public = function(){
	this.userAgent = window.navigator.userAgent;
	this.platform = window.navigator.platform;
	this.msProgIDs = ["MSXML2.XMLHTTP.6.0", "MSXML2.XMLHTTP.5.0", "MSXML2.XMLHTTP.4.0", "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP", "Microsoft.XMLHTTP"];
	this.match = {
		//匹配非负整数（正整数 + 0）
		notNegative		: /^[1-9]\d*|0$/,　 	
		//匹配非负浮点数（正浮点数 + 0）
		notNFloating 	:/^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$/	
	}
};
Function.prototype.bind = function() {
  var __method = this, args = arguments, object = args.length>1?args:(args.length>0?args[0]:null);
  return function() {
  	try{
	    return __method.apply(object, arguments);
  	}catch(e){
  	}
  }
};
Array.prototype.insert = function(place,data){
	var tmpArrayT = this.slice(0,place);
	var tmpArrayD = this.slice(place,this.length);
	return tmpArrayT.concat(data,tmpArrayD)
};
Array.prototype.move = function(b,t){
	if(b>t){
		var tmpArrayO = this.slice(0,t);
		var tmpArrayT = this.slice(t,b);
		var tmpArrayR = this.slice(b+1,this.length);
		return tmpArrayO.concat(this[b],tmpArrayT,tmpArrayR)
	}else{
		var tmpArrayO = this.slice(0,b);
		var tmpArrayT = this.slice(b+1,t+1);
		var tmpArrayR = this.slice(t+1,this.length);
		return tmpArrayO.concat(tmpArrayT,this[b],tmpArrayR)
	}
};
String.prototype.escapeString = function(){ 
	return ('"' + this.replace(/(["\\])/g, '\\$1') + '"'
		).replace(/[\f]/g, "\\f"
		).replace(/[\b]/g, "\\b"
		).replace(/[\n]/g, "\\n"
		).replace(/[\t]/g, "\\t"
		).replace(/[\r]/g, "\\r");
}
Public.prototype = {
	isFunction: function( fn ) {
		return !!fn && typeof fn != "string" &&
			typeof fn[0] == "undefined" && /function/i.test( fn + "" );
	},
	isObject : function(value){
		return (Object.prototype.toString.apply(value) === '[object Object]')
	},
	isArray : function(value){
		return (Object.prototype.toString.apply(value) === '[object Array]')
	},
	isString : function(wh) {
		return (wh instanceof String || typeof wh == "string");
	},
	load : function(fn){
		if(typeof(window.onload) == "function"){
			this.onloadList[this.onloadList.length] = window.onload
			window.onload = function(){
				this.onloadList.splice(this.onloadList.length-1,1)[0]();
				fn();
			}.bind(this);
		}else{
			this.onloadList = [];
			window.onload = function(){
				fn();
			};
		}
	},
	fixEvent:function(e){
		if(typeof e=="undefined")e=window.event;
		if(typeof e.layerX=="undefined")e.layerX=e.offsetX;
		if(typeof e.layerY=="undefined")e.layerY=e.offsetY;
		if(typeof e.pageX == "undefined")e.pageX = e.clientX + document.documentElement.scrollLeft - document.documentElement.clientLeft;
		if(typeof e.pageY == "undefined")e.pageY = e.clientY + document.documentElement.scrollTop - document.documentElement.clientTop;
		return e;
	},
	serialize : function(o){
		var objtype = typeof(o);
		if(objtype == "undefined"){
			return "undefined";
		}else if((objtype == "number")||(objtype == "boolean")){
			return o + "";
		}else if(o === null){
			return "null";
		}
		if (objtype == "string") { return o.escapeString(); }
		var me = arguments.callee;
		var newObj;
		if(typeof(o.__json__) == "function"){
			newObj = o.__json__();
			if(o !== newObj){
				return me(newObj);
			}
		}
		if(typeof(o.json) == "function"){
			newObj = o.json();
			if (o !== newObj) {
				return me(newObj);
			}
		}
		if(objtype != "function" && typeof(o.length) == "number"){
			var res = [];
			for(var i = 0; i < o.length; i++){
				var val = me(o[i]);
				if(typeof(val) != "string"){
					val = "undefined";
				}
				res.push(val);
			}
			return "[" + res.join(",") + "]";
		}

		if(objtype == "function"){
			return null;
		}
		res = [];
		for (var k in o){
			var useKey;
			if (typeof(k) == "number"){
				useKey = '"' + k + '"';
			}else if (typeof(k) == "string"){
				useKey = k.escapeString();
			}else{
				continue;
			}
			val = me(o[k]);
			if(typeof(val) != "string"){
				continue;
			}
			res.push(useKey + ":" + val);
		}
		return "{" + res.join(",") + "}";
	},
	trim: function(t){
		return t.replace(/^\s+|\s+$/g, "");
	},
	validate : function(string,matchId){
		if(matchId in this.match) 
			return this.match[matchId].test(string);
		return matchId.test(string);
	},
	domObj : function(){
		/**
		 * 将此对象克隆为一个新对象
		 */
		this.clone = function(){
			var cloneNode = this.cloneNode(true);
			$.domObj.call(cloneNode);
			return cloneNode;
		};
		this.getStyle = function(_key){
			if($.isIE()){
				return this.currentStyle[_key];
			}else{
				return window.getComputedStyle(this,null)[_key];
			}
		}
		/**
		 * 检查元素是否可见
		 * 根据3个方面检查
		 * 1、判断元素是否有实际的宽高，任意一项为0时都为不可见 -------- 有的浏览器子节点有高度，父节点还为零，此条无法做可见判断
		 * 2、判断元素的visibility的状态是否为hidden（包括继承的）
		 * 3、判断元素的display的状态是否为none（包括继承的）
		 */
		this.isVisibility=function(){
//			if(this.offsetWidth == 0 ||  this.offsetHeight == 0)
//				return false;
//			else{
				if(this.getStyle("visibility") == "hidden" || this.getStyle("display") == "none")
					return false;
				return true;
//			}
			
		};
		/**
		 * html()替代系统的innerHTML
		 * 参数（字符串），允许为空
		 * 参数不为空时向目标插入HTML代码，并返回目标对象
		 * 参数为空时返回目标中的HTML代码字符串
		 */
		this.html = function(txt){
			if(txt || txt==""){
				this.innerHTML = txt;
				return this;
			}
			return this.innerHTML;
		};
		/**
		 * 描述：跨浏览器的设置 innerHTML 方法  
		 *       允许插入的 HTML 代码中包含 script 和 style  
		 * 参数：
		 *    txt: 合法的 HTML 代码
		 * 经测试的浏览器：ie6+, firefox1.5+, opera8.5+ ,safari4.0+ ,谷歌
		 */
		this.shtml = function(txt){
			if($.isIE()){
				var htmlCode = '<div style="display:none">for IE</div>' + txt;
				htmlCode = htmlCode.replace(/<script([^>]*)>/gi,'<script$1 defer>');
				this.html(htmlCode);
				this.find(0).remove();
			}else{
				var newDom = $.createDom("div");
				newDom.insert(this.clone());
				newDom.find(-1).html(txt);
				this.replace(newDom.find(-1));
			}
			if($.isWebKit() || $.isOpera()){
				document.write(document.documentElement.innerHTML);
			}
		}
		//异步加载HTML
		this.htmlByAjax = function(url){
			var htmlStr = $.ajax(url,null,null,"get",false);
			this.shtml(htmlStr);
		}
		/**
		 * txt()替代系统的innerText,textContent(firefox)
		 * 参数（字符串），允许为空
		 * 参数不为空时向目标插入文本信息，并返回目标对象
		 * 参数为空时返回目标中的文本信息
		 */
		this.txt = function(txt){
			if($.isIE()){
				if(txt || txt==""){
					this.innerText = txt;
					return this;
				}
				return this.innerText;
			}else{
				if(txt || txt==""){
					this.textContent = txt;
					return this;
				}
				return this.textContent;
			}
		};
//		/**
//		 * 迭代
//		 * 
//		 */
//		this.each = function(fn, args){
//			return $.each( this, fn, args );
//		}
		/**
		 * find()查找目标对象的子节点
		 * 参数（数字），允许为空
		 * 参数不为空时返回目标对象的对应值的子节点，如，当0时返回第一个子节点
		 * 当参数为-1时返回目标对象的最后一个子节点
		 * 参数为空时返回目标对象的子节点数组
		 * 当没有子节点时返回目标节点
		 * 当参数为不可识别或大于目标子节点数量时返回最后一个子节点
		 */
		this.find = function(Num){
			this.myChild = [];
			for(var i=0,n=0;i<this.childNodes.length;i++){
				if(this.childNodes[i].nodeName=="#text" || this.childNodes[i].nodeName=="#comment")continue;
				this.myChild[n++]=this.childNodes[i]
			}
			if(this.myChild.length == 0){
				return this;
			}
			if(!Num && Num != 0){
				for(var i=0;i<this.myChild.length;i++){
					$.domObj.call(this.myChild[i]);
				}
				return this.myChild;
			}else if(Num == -1){
				var len = this.myChild.length;
				this.dom = this.myChild[len-1];
				$.domObj.call(this.dom);
				return this.dom;
			}else{
				if(Num >= this.myChild.length || !$.validate(Num,"notNegative"))
					Num = this.myChild.length-1;
				this.dom = this.myChild[Num];
				$.domObj.call(this.dom);
				return this.dom;
			}
		};
		/**
		 * 获取目标对象的父容器
		 * 无参数，
		 * 返回目标对象的父对象
		 */
		this.parent = function(){
			if(!this.parentNode)
				return this;
			var parentDom = this.parentNode;
			$.domObj.call(parentDom);
			return parentDom;
		};
		/**
		 * 获取目标对象的上一个节点
		 * 无参数，
		 */
		this.previous = function(){
			function getPrevious(dom){
				if(dom.previousSibling == null)
					return null;
				if(dom.previousSibling.nodeName == "#text"){
					return getPrevious(dom.previousSibling);
				}
				return dom.previousSibling;
			}
			
			var previousDom = getPrevious(this);
			if(previousDom != null)
				$.domObj.call(previousDom);
			return previousDom;
		};
		/**
		 * 获取目标对象的下一个节点
		 * 无参数
		 */
		this.next = function(){
			function getNext(dom){
				if(dom.nextSibling == null)
					return null;
				if(dom.nextSibling.nodeName == "#text"){
					return getNext(dom.nextSibling);
				}
				return dom.nextSibling;
			}
			var nextDom = getNext(this);
			if(nextDom != null)
				$.domObj.call(nextDom);
			return nextDom;
		};
		/**
		 * dom:需要插入的DOM对象
		 * station：插入的位置
		 * 为-1或空是插入到最后
		 * 为0时插入到第一位
		 * 其他数字即插入到相应子节点之后
		 * 返回目标DOM对象
		 */
		this.insert = function(dom,station){
			var len = this.find().length;
			if((!station && station != 0) || station == -1 || station>=len){
				this.appendChild(dom);
				return this;
			}else{
				this.insertBefore(dom,this.find(station));
				return this;
			}
		};
		/**
		 * replace()
		 * 将目标对象替换为一个新的对象
		 */
		this.replace = function(dom){
			if(this.next()){
				this.next().before(dom)
			}else{
				this.parent().insert(dom);
			}
			this.remove();
			$.domObj.call(dom);
			return dom;
		}
		/**
		 * before();
		 * 在目标对象前面插入一个DOM对象
		 * 参数，不允许空：dom对象
		 * 返回插入的对象
		 */
		this.before = function(dom){
			var parentDom = this.parent();
			parentDom.insertBefore(dom,this);
			return dom;
		},
		/**
		 * after();
		 * 在目标对象后面插入一个DOM对象
		 * 参数，不允许空：dom对象
		 * 返回插入的对象
		 */
		this.after = function(dom){
			var parentDom = this.parent();
			if(this.nextSibling){
				this.parent().insertBefore(dom,this.nextSibling);
			}else{
				$.domObj.call(parentDom);
				parentDom.insert(dom);
			}
			return dom;
		};
		/**
		 * insertDom(tagName,station)
		 * 插入一个新的DOM对象
		 * 参数：tagName,新的对象标签名（如：span、div）
		 * station：插入的位置
		 * 为-1或空是插入到最后
		 * 为0时插入到第一位
		 * 其他数字即插入到相应子节点之后
		 * 返回插入的对象
		 */
		this.insertDom = function(tagName,station){
			var tmpDom = $.createDom(tagName);
			this.insert(tmpDom,station);
			return tmpDom;
		};
		/**
		 * hide(vision)
		 * 隐藏一个DOM节点
		 * 参数：允许为空
		 * vision：当为真是启用效果
		 * 返回当前对象
		 */
		this.hide = function(vision){
			if(!this.isVisibility()){return;}
			this.runHide = function(obj){
				return obj.css("display","none");
			}
			if(vision){
				var height = this.offsetHeight + "px";
				var hidden = this.css("overflow");
				if(this.css("overflow") != "hidden"){
					this.css("overflow","hidden");
				}
				this.vision("hid",function(obj){
					obj
					.css("height",height)
					.css("overflow",hidden)
					.runHide(obj)
				})
			}else{
				return this.runHide(this);
			}
			
		};
		/**
		 * 收缩
		 */
		this.deflate = function(size,vision){
			this.rundeflate = function(obj){
				return obj.css({
					width:size["width"],
					height:size["height"]
				});
			}
			if(vision){
				var height = this.offsetHeight + "px";
				var hidden = this.css("overflow");
				if(this.css("overflow") != "hidden"){
					this.css("overflow","hidden");
				}
				this.vision("def",function(obj){
					obj
					.css("height",height)
					.css("overflow",hidden)
					.rundeflate(obj)
				})
			}else{
				this.rundeflate(this);
			}
		};
		/**
		 * 展开
		 */
		this.expand = function(size,vision){
			this.rundeflate = function(obj){
				return obj.css({
					width:size["width"],
					height:size["height"]
				});
			}
			if(vision){
				var height = this.offsetHeight + "px";
				var hidden = this.css("overflow");
				if(this.css("overflow") != "hidden"){
					this.css("overflow","hidden");
				}
				this.vision("exp",function(obj){
					obj
					.css("height",height)
					.css("overflow",hidden)
					.rundeflate(obj)
				})
			}else{
				this.rundeflate(this);
			}
		};
		/**
		 * show(vision)
		 * 显示一个DOM节点
		 * 参数：允许为空
		 * vision：当为真是启用效果
		 * 返回当前对象
		 */
		this.show = function(vision){
			if(this.isVisibility())return;
			var 	  height	= this.css("height") || "";
			var 	  hidden	= this.css("overflow");
				this.runShow	= function(obj){
												return obj
														.css("opacity",1)
														.css("overflow",hidden)
														.css("height",height);
											}
			this.css("display","");
			if(vision){
				if(this.css("overflow") != "hidden"){
					this.css("overflow","hidden");
				}
				this
				.css("height","1px")
				.css("opacity",0.1)
				.css("display","")
				.vision("shw",function(obj){
					obj.runShow(obj);
				})
			}else{
				return this.runShow(this);
			}
		};
		/**
		 * empty()
		 * 清空当前节点下的所有子节点
		 * 返回当前节点
		 */
		this.empty = function(){
			while ( this.firstChild )
				this.removeChild( this.firstChild );
			return this;
		};
		/**
		 * remove(vision)
		 * 移除一个DOM节点
		 * 参数：允许为空
		 * vision：当为真是启用效果
		 * 返回父节点
		 */
		this.remove = function(vision){
			var runRemove = function(obj){
				var parentDom = obj.parentNode;
					parentDom.removeChild(obj);
					return parentDom;
			};
			if(vision){
				this.css("overflow","hidden")
					.vision("hid",function(obj){
						return runRemove(obj)
					})
			}else{
			return runRemove(this);}
		};
		
		/**
		 * css(name,value)
		 * 修改和获取style下的信息
		 * 第一个参数，不允许为空，
		 * 传入样式名称
		 * 或者为一个数组[[cssName1,cssValue1],[cssName2,cssValue2]]
		 * 或者为一个hash对象{cssName1:cssValue1,cssName2:cssValue2}
		 * 第二个参数，允许为空，传入样式的值，
		 * 当第二个参数为空时并且第一个参数不是一个数组对象和hash对象，返回样式名称对应的值，
		 * 当第二个参数不为空时休要样式的值，返回当前dom对象
		 */
		this.css = function(){
			var eCss = function(obj,name,value){
				if($.isIE() && name == "opacity"){
					name = "filter";
					if($.validate(value,"notNFloating"))
						value = "alpha(opacity="+value*100+")"
				}else if($.isFirefox() && name == "opacity"){
					name = "MozOpacity";
				}else if(name == "float" && ($.isIE() || $.isOpera())){
					name = "styleFloat";
				}else if(name == "float"){
					name = "cssFloat";
				}
				if(!value && value != 0 && value != false && value != "")
					return eval("obj.style."+name);
				if($.isFunction(value))
					return obj;
				eval("obj.style." + name + " = " + "'" + value + "'");
				return obj;
			};
			if($.isArray(arguments[0])){
				for(var i=0;i<arguments[0].length;i++){
					eCss(this,arguments[0][i][0],arguments[0][i][1])
				}
			}else if($.isObject(arguments[0])){
				for(i in arguments[0]){
					eCss(this,i,arguments[0][i])
				}
			}else{
				return eCss(this,arguments[0],arguments[1])
			}
			return this;
		};
		/**
		 * attr()
		 * 设置或者获取html标签自定义属性
		 * 当key是一个对象时，如：{onclick:"alert('string')",onmouseover:"alert('string')"}
		 * 改写或写入对象所有的自定义属性的值
		 * 当有参数value传入是改写或写入自定义属性的值
		 * 返回当前对象
		 * 否则获取自定义属性名为参数name的值
		 */
		this.attr = function(key,value){
			if($.isObject(key)){
				for(var i in key){
					this.setAttribute(i,key[i]);
				}
				return this;
			}else if(value){
				this.setAttribute(key,value);
				return this;
			}else{
				if(this.getAttributeNode(key))
					return this.getAttributeNode(key).value;
				return this.getAttribute(key);
			}
		};
		/**
		 * removeAttr()
		 * 移除当前节点的自定义属性
		 * 返回当前节点
		 */
		this.removeAttr = function(key){
			this.attr(key,"");
			this.removeAttribute(key);
			return this
		};
		/**
		 * addClass()
		 * 为dom对象添加一个样式名
		 * 参数value不为空，字符串类型,可以连续多个(中间用空格分开) 如："className1 className2"
		 * 返回当前对象
		 */
		this.addClass = function(value){
			this.removeClass(value)
			if(this.className)
				this.className = this.className +" "+ value
			else
				this.className = value
			return this;
		};
		/**
		 * removeClass()
		 * 从dom对象中移除一个或多个样式名
		 * 参数value可以为空，为空时时移除所有的样式名
		 * 有值时是制定的样式名
		 * 返回当前对象
		 */
		this.removeClass = function(value){
			if(value){
				this.className = this.className.replace(value,"")
			}else{
				this.className = "";
			}
			return this;
		};
		this.removeOn = function(type,fn){
			 if ( this.detachEvent ) {
		        this.detachEvent( 'on'+type, this[type+fn] );
		        this[type+fn] = null;
		      } else
		        this.removeEventListener( type, fn, false );
		}
		this.on = function(type,fn){
			try {
				this.removeOn(type,fn);
			} catch (e) {
				// TODO: handle exception
			}
			if($.isIE()){
				if(type == "input")
					type = "propertychange"
				this['e'+type+fn] = fn;
				this[type+fn] = function(){this['e'+type+fn]( window.event );}.bind(this)
				this.attachEvent("on"+type,this[type+fn]);
			}else
				this.addEventListener(type,fn,false);
			return this;
		};
		/**
		 * drag(handle,callback,model)
		 * 对象的拖拽
		 * handle，指定可拖拽的区域，可以不传、也可以传空字符串或null
		 * callback，回调函数，在拖动开始、过程中、结束调用传入的方法，返回拖动当前的状态（start，drag，end）、left、top，可以不传、也可以传空字符串或null
		 * model，为true是模态化窗口
		 */
		this.drag = function(handle,callback,model){
			var oldCss = {
				position:this.css("position"),
				zIndex:this.css("zIndex")
			}
			var hOldCss = {
				cursor:this.css("cursor")
			}
			if(!handle || handle == "" || handle==null)
				handle = this;
			var iframe,selectDoms=[];
			if($.isIE()&& parseFloat($.getBrowser().edition) < 7){
				var tmpDoms = $.getByTagName("select");
				for(var i=0,j=0;i<tmpDoms.length;i++){
					if (tmpDoms[i].css("visibility") != "hidden")
						selectDoms[j++] = tmpDoms[i];
				}
			}
			var start = function(e){
				this.css({
					position:"absolute",
					zIndex:999,
					left:this.offsetLeft+"px",
					top:this.offsetTop+"px"
				});
				handle.css({
					cursor:"move"
				})
				e=$.fixEvent(e);
				var top=parseInt(this.css("top"));
				var left=parseInt(this.css("left"));
				if($.isIE()&& parseFloat($.getBrowser().edition) < 7){
					for(var i=0;i<selectDoms.length;i++){
						selectDoms[i].css("visibility","hidden");
					}
				}
				if(model){
					iframe = $.createDom("div").css({
						width:document.documentElement.clientWidth+document.documentElement.scrollLeft+"px",
						height:document.documentElement.clientHeight+document.documentElement.scrollTop+"px",
						position:"absolute",
						background:"#acd",
						left:0,
						top:0,
						opacity:"0.3",
						zIndex:990
					}).noSelect();
					document.body.appendChild(iframe);
				}
				if($.isFunction(callback)){
					callback("start",left,top)
				}
				this.onDragStart(left,top,e.pageX,e.pageY);
				handle.lastMouseX=e.pageX;
				handle.lastMouseY=e.pageY;
				document.onmousemove=drags;
				document.onmouseup=end;
				return false;
			}.bind(this);
			var drags = function(e){
				e=$.fixEvent(e);
				var mouseY=e.pageY;
				var mouseX=e.pageX;
				var top=parseInt(this.css("top"));
				var left=parseInt(this.css("left"));//这里的top和left是handle.root距离浏览器边框的上边距和左边距
				
				var currentLeft,currentTop;
				currentLeft=left+mouseX-handle.lastMouseX;
				currentTop=top+(mouseY-handle.lastMouseY);
				
				//上一瞬间的上边距加上鼠标在两个瞬间移动的距离 得到现在的上边距
				
				this.css("left",currentLeft +"px");
				this.css("top",currentTop+"px");
				
				if($.isFunction(callback)){
					callback("drag",currentLeft,currentTop)
				}
				
				//更新当前的位置
				
				handle.lastMouseX=mouseX;
				handle.lastMouseY=mouseY;
				
				//保存这一瞬间的鼠标值 用于下一次计算位移
				
				this.onDrag(currentLeft,currentTop,e.pageX,e.pageY);//调用外面对应的函数
				return false;
			}.bind(this);
			var end = function(){
				this.css({
					zIndex:oldCss.zIndex
				});
//				handle.css({
//					cursor:hOldCss.cursor
//				})
				if($.isIE()&& parseFloat($.getBrowser().edition) < 7){
					for(var i=0;i<selectDoms.length;i++){
						selectDoms[i].css("visibility","");
					}
				}
				if(model){
					iframe.remove();
					iframe = null;
				}
				document.onmousemove=null;
				document.onmouseup=null;
				var currentLeft,currentTop;
				currentLeft = parseInt(this.css("left"));
				currentTop = parseInt(this.css("top"));
				if($.isFunction(callback)){
					callback("end",currentLeft,currentTop)
				}
				this.onDragEnd(currentLeft,currentTop);
			}.bind(this);
			handle.onmousedown=start;
			//if(isNaN(parseInt(this.css("left"))))this.css("left","0px");

			//if(isNaN(parseInt(this.css("top"))))this.css("top","0px");//确保后来能够取得top值
			this.onDragStart=new Function();
			this.onDragEnd=new Function();
			this.onDrag=new Function();
			
		};
		/**
		 * vision(type,fn)
		 * 展开及收起效果
		 * 参数：type，效果是类型，目前只有hid（收起）、shw（展开）
		 * fn，执行完成后的回调函数。
		 * 无返回值
		 */
	    this.vision = function(type,fn){
	    	var obj = this;
	    	if(type == "hid" || type == "def"){
		    	var i = 100;
		    	var hid = function(){
		    		i-=8;
		    		obj.css("height",(obj.offsetHeight - 5)<0 ?1:(obj.offsetHeight - 5) +"px");
		    		if(type == "hid")obj.css("opacity",i/100);
					if(i>=10){
						setTimeout(hid,10);
					}else{
						if(fn)
							fn(obj);
					}
		    	}
		    	setTimeout(hid,10);
	    	}else if(type == "shw" || type == "exp"){
	    		var i = 1;
	    		var shw = function(){
	    			i+=8;
	    			obj.css("height",obj.offsetHeight + 5 +"px");
	    			if(type == "shw")obj.css("opacity",i/100);
					if(i<=90){
						setTimeout(shw,10);
					}else{
						if(fn)
							fn(obj);
					}
	    		}
	    		setTimeout(shw,10);
	    	}
	    };
	    /**
	     * 禁止选择
	     */
	    this.noSelect = function(){
	    	if($.isFirefox())
	    		this.css("MozUserSelect","none")
	    	else{
	    		this.onselectstart=function(){return false;}
	    		this.attr("onselectstart","return false")
	    	}
	    	return this;
	    };
	    
	},
	getById : function(ID) {
    	this.dom = document.getElementById(ID);
    	if(this.dom)
    		this.domObj.call(this.dom);
    	return this.dom;
    },
    getByName : function(NAME){
    	this.domArray = document.getElementsByName(NAME);
    	for(var i=0;i<this.domArray.length;i++){
    		this.domObj.call(this.domArray[i]);
    	}
    	return this.domArray;
    },
    getByTagName : function(tagName){
   		this.domArray = document.getElementsByTagName(tagName);
    	for(var i=0;i<this.domArray.length;i++){
    		this.domObj.call(this.domArray[i]);
    	}
    	return this.domArray;
    },
    createDom : function(tagName,map){
    	if(!tagName)
    		tagName = "DIV";
    	var formMap = {
    		form:true,
    		input:true,
    		select:true,
    		textarea:true,
    		button:true
    	};
    	var map = map || {};
    	if(tagName.toLowerCase() in formMap && $.isIE() && "name" in map){
    		var str = "<"+tagName+" ";
    		for(var i in map){
    			str+=i+"="+map[i]+" ";
    		}
    		str+="/>"
    		this.dom = document.createElement(str);
    		if(this.dom)
    			this.domObj.call(this.dom);
    	}else{
	    	this.dom = document.createElement(tagName);
	    	if(this.dom)
	    		this.domObj.call(this.dom);
	    	for(var i in map){
	    		this.dom.attr(i,map[i]);
	    	}
	    	
    	}
    	return this.dom;
    },
    getScrollTop:function(win){
    	var win = win || window;
    	var scrollPos = 0;
    	
		if (typeof win.pageYOffset != 'undefined') {
		   scrollPos = win.pageYOffset;
		}
		else if (typeof win.document.compatMode != 'undefined' &&
		     win.document.compatMode != 'BackCompat') {
		   scrollPos = win.document.documentElement.scrollTop;
		}
		else if (typeof win.document.body != 'undefined') {
		   scrollPos = win.document.body.scrollTop;
		}
		return scrollPos;
    },
    
    getX : function (obj){  
   		return obj.offsetLeft + (obj.offsetParent ? this.getX(obj.offsetParent) : obj.x ? obj.x : 0);  
    },        
    getY : function (obj){
      	return (obj.offsetParent ? obj.offsetTop + this.getY(obj.offsetParent) : obj.y ? obj.y : 0);  
    },
    getBrowser : function(){
		if(this.isOpera()){
			this.OperaEdition = 0.0;
			if(navigator.appName == "Opera"){
				this.OperaEdition = window.navigator.appVersion;
			}else{
				this.reOperaEdition = new RegExp("Opera(\\d+\\.\\d+)");
				this.reOperaEdition.test(this.userAgent);
				this.OperaEdition = parseFloat(RegExp["$1"]);
			}
			return {
				browser:"Opera",
				edition:parseFloat(this.OperaEdition).toString()
					};
		}else if(this.isSafari()){
			this.reAppleWebKit = new RegExp("AppleWebKit\\/(\\d+(?:\\.\\d*)?)");
	        this.reAppleWebKit.test(this.userAgent);
	        this.safariEdition = parseFloat(RegExp["$1"]);
	        return {
				browser:"Safari",
				edition:this.safariEdition
					};
		}else if(this.isIE()){
			 this.reIE = new RegExp("MSIE (\\d+\\.\\d+)");
			 this.reIE.test(this.userAgent);
			 this.ieEdition = RegExp["$1"];
			 return {
				browser:"Internet Explorer",
				edition:this.ieEdition
					};
		}else if(this.isChrome()){
			this.reAppleWebKit = new RegExp("AppleWebKit\\/(\\d+(?:\\.\\d*)?)");
	        this.reAppleWebKit.test(this.userAgent);
	        this.chromeEdition = parseFloat(RegExp["$1"]);
	        return {
				browser:"Chrome",
				edition:this.chromeEdition
					};
		}else if(this.isFirefox()){
			this.reMoz = new RegExp("Firefox\\/(\\d+\\.\\d+\\.\\d+)");
    		this.reMoz.test(this.userAgent);
    		this.firefoxEdition = RegExp["$1"];
    		return {
				browser:"Firefox",
				edition:this.firefoxEdition
					};
		}else{
			return {
				browser:"未知浏览器",
				edition:"未知版本"
					};
		}
	},
	getSystem : function(){
		if(this.isWin()){
			this.isWin95 = this.userAgent.indexOf("Win95")>-1 || this.userAgent.indexOf("Windows 95") > -1;
			this.isWin98 = this.userAgent.indexOf("Win98")>-1 || this.userAgent.indexOf("Windows 98") > -1;
			this.isWinME = this.userAgent.indexOf("Win 9x 4.90")>-1 || this.userAgent.indexOf("Windows ME") > -1
			this.isWin2000 = this.userAgent.indexOf("Windows NT 5.0")>-1 || this.userAgent.indexOf("Windows 2000") > -1;
			this.isWinXP = this.userAgent.indexOf("Windows NT 5.1")>-1 || this.userAgent.indexOf("Windows XP") > -1;
			this.isWin2003 = this.userAgent.indexOf("Windows NT 5.2")>-1 || this.userAgent.indexOf("Windows 2003") > -1;
			this.isWinNT = this.userAgent.indexOf("WinNT")>-1 || this.userAgent.indexOf("Windows NT") > -1 || this.userAgent.indexOf("WinNT4.0") > -1 || this.userAgent.indexOf("Windows NT 4.0") > -1 && (!this.isWinME && !this.isWin2000 && !this.isWinXP && !this.isWin2003);
			if(this.isWin95){
				this.system = "Windows 95";
			}else if(this.isWin98){
				this.system = "Windows 98";
			}else if(this.isWinME){
				this.system = "Windows ME";
			}else if(this.isWin2000){
				this.system = "Windows 2000";
			}else if(this.isWinXP){
				this.system = "Windows XP";
			}else if(this.isWin2003){
				this.system = "Windows 2003";
			}else if(this.isWinNT){
				this.system = "Windows NT";
			}else{
				this.system = "Windows vista or Windows 2008 or windows7 or 更高的windows版本";
			}
		}else if(this.isMac()){
		    this.isMac68K = this.userAgent.indexOf("Mac_68000") > -1|| this.userAgent.indexOf("68K") > -1;
		    this.isMacPPC = this.userAgent.indexOf("Mac_PowerPC") > -1|| this.userAgent.indexOf("PPC") > -1;
		    this.system = "Mac OS";
		}else if(this.isUnix()){
    		this.isSunOS = this.userAgent.indexOf("SunOS") > -1;
    		this.system = "Unix or Lunix";
		}
		return this.system;
	},
	isOpera : function(){
		return this.userAgent.indexOf("Opera") > -1;
	},
	isSafari : function(){
		return this.isWebKit() && !this.isChrome();
	},
	isIE : function(){
		return this.userAgent.indexOf('compatible') > -1 && this.userAgent.indexOf('MSIE') > -1 && !this.isOpera()
	},
	isChrome : function(){
		return this.userAgent.indexOf("Chrome") > -1;
	},
	isFirefox : function(){
		return this.userAgent.indexOf('Firefox') > -1 && !this.isSafari();
	},
	isWebKit : function(){
		return this.userAgent.indexOf("AppleWebKit") > -1;
	},
	isWin : function(){
		return this.platform == "Win32" || this.platform == "Windows";
	},
	isMac : function(){
		return this.platform == "Mac68K" || this.platform == "MacPPC";
	},
	isUnix : function(){
		return this.platform == "X11" && !this.isWin() && !this.isMac();
	},
	createXMLHttpRequest : function(){
		var req = null;
		try	{
			if (window.ActiveXObject){
				while (!req && this.msProgIDs.length){
					try { req = new ActiveXObject(this.msProgIDs[0]); } catch (e) { req = null; }
					if (!req)
						this.msProgIDs.splice(0, 1);
				}
			}
			if (!req && window.XMLHttpRequest)
				req = new XMLHttpRequest();
		}
		catch (e) { req = null;	}
		return req;
	},
	ajax : function(url,data,dataType,method,Async,fn){
		var data = data || null;
		var method = method || "GET";
		var xml = this.createXMLHttpRequest();
		xml.onreadystatechange = onReadyState;
		if(typeof data != "string"){
			var tData = new Array();
			for(var i in data){
				if(typeof data[i] != "string"){
					tData.push( encodeURIComponent(i) + "=" + encodeURIComponent( encodeURI($.serialize(data[i])) ) );
				}else{
					tData.push( encodeURIComponent(i) + "=" + encodeURIComponent( encodeURI(data[i]) ) );
				}
			};
			data = tData.join("&");
		}
		if(method.toLowerCase() == "get"){
			url += ((url.indexOf("?") > -1) ? "&" : "?") + data;
			data = null;
		}
		var Async = Async || false;
		
		xml.open(method,url,Async);
		if(method.toLowerCase() == "post"){
			xml.setRequestHeader("Content-Length",data.length);
			xml.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		};
		try {
			xml.send(data);
		} catch (e) {
			// TODO: handle exception
			return e;
		};
		if(Async)return;//2010-5-6
		var results;
		
		if(dataType && dataType.toLowerCase()=="json"){
			try {
//				results =  eval('('+$.trim(xml.responseText).replace(/\</g,"&lt;").replace(/\>/g,"&gt;")+')');
				results =  eval('('+decodeURIComponent($.trim(xml.responseText)).replace(/\</g,"&lt;").replace(/\>/g,"&gt;")+')');	//2010-11-12
			} catch (e) {
				// TODO: handle exception
			}
		}else if(dataType && dataType.toLowerCase()=="xml"){
			try {
				results =  decodeURI(xml.responseText);
			} catch (e) {
				results =  xml.responseText;
			}
			results = $.xmlStrToXML(results);
		}else{
			try {
				results =  decodeURI(xml.responseText);
			} catch (e) {
				results =  xml.responseText;
			}
			
		}

		function onReadyState(){
			if(fn && $.isFunction(fn)){
				fn(xml);
			}
		}
		
		return results;
	},
	/**
	 * jsonToXmlStr(json)
	 * json：传入一个json对象
	 * 返回xml字符串
	 */
	jsonToXmlStr:function(json){
		var xmlStr = '<?xml version="1.0" encoding="UTF-8" ?>';
		var str='';
		function toStr(json){
			for(var i in json){
				str+="<"+i+">"
				if($.isObject(json[i])){
					toStr(json[i]);
				}else if($.isArray(json[i])){
					for(var j=0;j<json[i].length;j++){
						toStr(json[i][j]);
						if(j<json[i].length-1){
							str+="</"+i+"><"+i+">"
						}
					}
				}else{
					str+=json[i];
				}
				str+="</"+i+">"
			}			
		}
		toStr(json);
		return xmlStr+str;
	},
	xmlStrToXML:function(str){
		if($.isIE()){
			var doc = new ActiveXObject("Microsoft.XMLDOM");
			doc.loadXML(str);
			return doc;
		}else{
			var parser=new DOMParser();
   			return parser.parseFromString(str,"text/xml");
		}
	}
	
}
var $ = new Public;


var openWindowFlag = 0;

/**
 * title:标题
 * fn:创建完窗口后写入窗口数据方法
 * closeDom：是否有关闭按钮
 * modify by jWX216858 2014-10-17
 */
var OpenWindow = function(id){

  //弹出框标志位，解决按回车键背景一直变黑的问题
  if (openWindowFlag == 1) {
    
		wiWindow.close();
		  
        return;
    }
	
	if($.isString(id)){//判断传入是是字符串还是dom对象
		this.modelWindow = $.getById(id);
	}else{
		this.modelWindow = id;
	}
	this.iframe,
	this.selectDoms=[];
	this.init();
	
	openWindowFlag = 1;
}
var upIndex = 100;
OpenWindow.prototype = {
	init : function(){
		//IE6先隐藏掉所有的select
		if($.isIE()&& parseFloat($.getBrowser().edition) < 7){
			this.tmpDoms = $.getByTagName("select");
			for(var i=0,j=0;i<this.tmpDoms.length;i++){
				if (this.tmpDoms[i].isVisibility())
					this.selectDoms[j++] = this.tmpDoms[i];
			};
			for(var i=0;i<this.selectDoms.length;i++){
				this.selectDoms[i].css("visibility","hidden");
			};
		};
		this.iframe = $.createDom("div").css({
			width:document.documentElement.scrollWidth+"px",//"100%",//document.documentElement.clientWidth+document.documentElement.scrollLeft+"px",
			height:document.documentElement.scrollHeight+"px",
			position:"absolute",
			background:"#000",
			left:0,
			top:0,
			opacity:"0.3",
			zIndex:upIndex++
		}).noSelect();
		document.body.appendChild(this.iframe);
		this.modelWindow.css("display","block");
		target = {
			width : this.modelWindow.clientWidth,
			height : this.modelWindow.clientHeight,
			top : (document.documentElement.clientHeight - this.modelWindow.clientHeight)/2+document.documentElement.scrollTop,
			left : (document.documentElement.clientWidth - this.modelWindow.clientWidth)/2+document.documentElement.scrollLeft
		};
		if(target.top<0)
			target.top = 0;
		this.modelWindow.css({
			left:target.left+"px",
			top:target.top+"px",
			position:"absolute",
			zIndex:upIndex++
		});
		document.body.appendChild(this.modelWindow);
		
	},
	close : function(){
		if($.isIE()&& parseFloat($.getBrowser().edition) < 7){
			for(var i=0;i<this.selectDoms.length;i++){
				this.selectDoms[i].css("visibility","visible");
			}
		};
		
		//捕获异常，改变openWindowFlag状态，防止直接点弹出框取消按钮未改变openWindowFlag的值，
		//modify by sWX219697 2014-10-24 11:37:30
		try
		  {
		  	openWindowFlag = 0;
		  
		    this.modelWindow.hide();
			this.iframe.remove();
		  }
		  catch(e)
		  {
		  } 
		
	}
}

//替换背景图片
upDateBGImg = function(obj,img){
	if($.isIE()&& parseFloat($.getBrowser().edition) < 7 && img.split("\.")[img.split("\.").length-1] == "png"){
		obj.style.filter =  "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+img+"',sizingMethod='scale')";
		obj.style.background = "none";
	}else{
		obj.style.backgroundImage = "url('"+img+"')";
	}
}

VirtualKeyboard = function(){
	this.activatedDom = null;
}
VirtualKeyboard.prototype = {
	//激活一个表单作为虚拟键盘的输入目标
	activate:function(id){
		if($.isString(id)){//判断传入是是字符串还是dom对象
			this.activatedDom = $.getById(id);
		}else{
			this.activatedDom = id;
		}
	},
	//清空
	clear:function(){
		if(this.activatedDom != null){
			this.activatedDom.value = "";
		}
	},
	//退格
	backspace:function(){
		if(this.activatedDom != null){
			var str = this.activatedDom.value;
			var strLen = str.length;
			if(strLen >0){
				this.activatedDom.value = str.substring(0,strLen-1);
			}
		}
	},
	//输入具体值
	key:function(value){
		if(this.activatedDom != null){
			this.activatedDom.value = this.activatedDom.value+value;
		}
	},
	//获取当前值
	getValue:function(){
		return this.activatedDom.value;
	}
}
myKeyboard = new VirtualKeyboard();



var MyMenu = function(){
	this.activationDom = null;
}

MyMenu.prototype = {
	activate:function(id){
		if($.isString(id)){//判断传入是是字符串还是dom对象
			var obj = $.getById(id);
		}else{
			var obj = id;
			$.domObj.call(obj);
		}
		
		if(this.activationDom){
			this.activationDom.removeClass("on");
		}
		this.activationDom = obj.addClass("on");
	}
}

virtualScroll = function(id,id2,type){

	if($.isString(id)){//判断传入是是字符串还是dom对象
		this.activatedDom = $.getById(id);
	}else{
		this.activatedDom = id;
	}
	if(this.activatedDom){
		this.activatedDom.scrollTop = 0;
	}
	if($.isString(id2)){//判断传入是是字符串还是dom对象
		this.scrollDom = $.getById(id2);
	}else{
		this.scrollDom = id2;
	}
	if(this.scrollDom){
		
		this.parentTop = this.scrollDom.parent().offsetTop;
		
		if("1" == type)
		{
			this.parentTop = this.scrollDom.parent().offsetTop - 132;
		}
		
		this.parentHeight = this.scrollDom.parent().offsetHeight; 

		this.scrollDom.drag(null,function(st,left,top){
			if(st == "start"){
				this.parentLeft = this.scrollDom.style.left;
				if(top < this.parentTop){
					this.scrollDom.style.top = this.parentTop+"px";
				}
				if(top > this.parentHeight+this.parentTop-this.scrollDom.offsetHeight){
					this.scrollDom.style.top = this.parentHeight+this.parentTop-this.scrollDom.offsetHeight+"px";
				}
			}else if(st == "drag"){
				this.scrollDom.style.left = this.parentLeft;
				if(top < this.parentTop){
					this.scrollDom.style.top = this.parentTop+"px";
				}
				if(top > this.parentHeight+this.parentTop-this.scrollDom.offsetHeight){
					this.scrollDom.style.top = this.parentHeight+this.parentTop-this.scrollDom.offsetHeight+"px";
				}
			}else if(st == "end"){
				this.scrollDom.style.left = this.parentLeft;
				if(top < this.parentTop){
					this.scrollDom.style.top = this.parentTop+"px";
				}
				if(top > this.parentHeight+this.parentTop-this.scrollDom.offsetHeight){
					this.scrollDom.style.top = this.parentHeight+this.parentTop-this.scrollDom.offsetHeight+"px";
				}
				this.moveTo((this.scrollDom.offsetTop-this.parentTop)/(this.parentHeight-this.scrollDom.offsetHeight));
			}
		}.bind(this))
	}
}
virtualScroll.prototype = {
	getScroll : function(){
		return {
			myScrollHeight : this.activatedDom.scrollHeight,//内容高度
			myHeight : this.activatedDom.clientHeight,//容器的高度
			myScrollTop : this.activatedDom.scrollTop//滚动的高度
		}
	},
	moveTo : function(scale){
		var myScroll = this.getScroll();
		this.activatedDom.scrollTop = (myScroll.myScrollHeight-myScroll.myHeight)*scale;
		this.scrollDom.txt(parseInt(scale*100)+"%");
	},
	moveDown : function(step){
		var myScroll = this.getScroll();
		if(myScroll.myHeight+myScroll.myScrollTop+step > myScroll.myScrollHeight){
			this.activatedDom.scrollTop = myScroll.myScrollHeight - myScroll.myHeight;
		}else{
			this.activatedDom.scrollTop = myScroll.myScrollTop + step;
		}
		this.moveScrollDom();
	},
	moveUp : function(step){
		var myScroll = this.getScroll();
		if(myScroll.myScrollTop - step < 0){
			this.activatedDom.scrollTop = 0;
		}else{
			this.activatedDom.scrollTop = myScroll.myScrollTop - step;
		}
		this.moveScrollDom();
	},
	moveScrollDom : function(){
		if(this.scrollDom){
			var scrollDomHeight = this.scrollDom.clientHeight;//当前滚动条的长度;
			var myScroll = this.getScroll();
			if (myScroll.myScrollHeight != myScroll.myHeight)
			{
				var scrollScale = myScroll.myScrollTop/(myScroll.myScrollHeight-myScroll.myHeight);
				this.scrollDom.css({
					top:parseInt((this.parentHeight-scrollDomHeight)*scrollScale)+this.parentTop+"px"
				})
				this.scrollDom.txt(parseInt(scrollScale*100)+"%"); ;
			}			
		}
	}
}


/**************************校验身份证号码是否合法 begin*********************************************************************
 *
 * 功能： 校验身份证号码是否合法
 * @author lWX431760
 * 2017-01-11
 ***********************************************************************************************************************/
// 省份编码
var vcity = {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
             21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
             33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",
             41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",
             46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",
             54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",
             65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"
            };
			
// 加权因子
var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 

// 身份证验证位值
var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 

checkIDNo = function(card) {
    //是否为空
    if(card === '')
	{
        return "请输入身份证号码！";
    }
    //校验长度，类型
    if(!isCardNo(card))
	{
        return "身份证必须为15位或者18位，18位身份证最后一位只能为数字或者字母X（大写）！";
    }
    //检查省份
    if(!checkProvince(card))
	{
        return "身份证号码不合法！";
    }
    //校验生日
    if(!checkBirthday(card))
	{
        return "身份证号码不合法！";
    }
    //检验位的检测
    if(!checkParity(card))
    {
        return "身份证号码不合法！";
    }
    return "1";
};

//检查号码是否符合规范，包括长度，类型
isCardNo = function(card) {
    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
    if(reg.test(card) === false) {
        return false;
    }
    return true;
};

//取身份证前两位,校验省份
checkProvince = function(card) {
    var province = card.substr(0,2);
    if(vcity[province] == undefined) {
        return false;
    }
    return true;
};

//检查生日是否正确
checkBirthday = function(card) {
    var len = card.length;
    //身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字
    if(len == '15') {
        var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/; 
        var arr_data = card.match(re_fifteen);
        var year = arr_data[2];
        var month = arr_data[3];
        var day = arr_data[4];
        return verifyBirthday('19'+year,month,day);
    }
    //身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X
    if(len == '18') {
        var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
        var arr_data = card.match(re_eighteen);
        var year = arr_data[2];
        var month = arr_data[3];
        var day = arr_data[4];
        return verifyBirthday(year,month,day);
    }
    return false;
};

//校验日期
verifyBirthday = function(year,month,day,birthday) {
	var birthday = new Date(year+'/'+month+'/'+day);
    var now = new Date();
    var now_year = now.getFullYear();
    //年月日是否合理
    if(birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day) {
        //判断年份的范围（3岁到100岁之间)
        var time = now_year - year;
        if(time >= 0 && time <= 120) {
            return true;
        }
        return false;
    }
    return false;
};

//校验位的检测
checkParity = function(card) {
    //15位转18位
    card = changeFivteenToEighteen(card);
    var len = card.length;
    if(len == '18') {
        var cardTemp = 0, i, valnum; 
        for(i = 0; i < 17; i ++) { 
            cardTemp += card.substr(i, 1) * arrInt[i]; 
        } 
        valnum = arrCh[cardTemp % 11]; 
        if (valnum == card.substr(17, 1)) {
            return true;
        }
        return false;
    }
    return false;
};

//15位转18位身份证号
changeFivteenToEighteen = function(card) {
    if(card.length == '15') {
        var cardTemp = 0, i;   
        card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);
        for(i = 0; i < 17; i ++) { 
            cardTemp += card.substr(i, 1) * arrInt[i]; 
        } 
        card += arrCh[cardTemp % 11]; 
        return card;
    }
    return card;
};
/**************************校验身份证号码是否合法 end*********************************************************************/
function encryptPassword(id,pubKey,modKey)
{
	// 对密码的加密处理要放到最后
    var thisPwd = document.getElementById(id);
    setMaxDigits(130);
    // 对服务密码进行加密
	 var key1 = new RSAKeyPair(pubKey,
    	"",modKey); 
    	
    var p = encodeURIComponent(thisPwd.value);
    var result = encryptedString(key1, p);
    thisPwd.value = result;
    return true;
}


