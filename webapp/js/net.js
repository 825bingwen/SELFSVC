var net = new Object();

net.READY_STATE_UNINITIALIZED=0;
net.READY_STATE_LOADING=1;
net.READY_STATE_LOADED=2;
net.READY_STATE_INTERACTIVE=3;
net.READY_STATE_COMPLETED=4;

net.ContentLoader = function(url,onload,onerror,method,params,contentType) {
	this.req = null;
	net.currentLoader = this;
	this.onload = (onload) ? onload:this.defaultLoad;
	this.onerror = (onerror) ? onerror:this.defaultError;
	this.loadXMLDoc(url,method,params,contentType);
}

net.ContentLoader.prototype.loadXMLDoc = function(url,method,params,contentType) {
	if(!method) {
		method = "GET";
	}
	if(!contentType && method=="POST") {
		contentType = "application/x-www-form-urlencoded";
	}

	//����ajax�첽�������
	if(window.XMLHttpRequest) { //mozila
		this.req = new XMLHttpRequest();
	} else if(window.ActiveXObject) { //ie
		try
		{
			this.req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		catch (err)
		{
			this.req = new ActiveXObject("Ms2.XMLHTTP");
		}
	}
	//��������ɹ�����������
	if(this.req) {
		try
		{
			var loader = this;
			//����״̬�仯��������call()����������̬���ú�����Ч����
			this.req.onreadystatechange = function() {
				net.ContentLoader.onReadyState.call(loader);
			}
			//��url������
			this.req.open(method,url,true);
			if(contentType) {
				this.req.setRequestHeader('Content-Type',contentType);
			}
			this.req.send(params);
		}
		catch (err)
		{
			this.onerror.call(this);
		}
	}

}

net.ContentLoader.onReadyState = function() {
	var req = this.req;
	var ready = req.readyState;
	if(ready==net.READY_STATE_COMPLETED) {
		var httpStatus = req.status;
		if(httpStatus==200 || httpStatus==0) {
			this.onload.call(this);
		} else {
			this.onerror.call(this);
		}
	}
}

net.ContentLoader.prototype.defaultError = function() {
    try{
	pubErrShow("ajax����!"+
			"\n\nreadyState:"+this.req.readyState+
			"\nhaders:"+this.req.getAllResponseHeaders());
	}catch(e){
			//alert("ajax����!"+ "\n\nreadyState:"+this.req.readyState+ "\nhaders:"+this.req.getAllResponseHeaders());
			}
}

net.ContentLoader.prototype.defaultLoad = function() {
}

//add begin yKF28472 2011/11/23 R003C11L11n01 ˵�����˷�����ajax����ʹ��ͬ����ʽ
net.ContentLoaderSynchro = function(url,onload,onerror,method,params,contentType) {
	this.req = null;
	net.currentLoader = this;
	this.onload = (onload) ? onload:this.defaultLoad;
	this.onerror = (onerror) ? onerror:this.defaultError;
	this.loadXMLDocSynchro(url,method,params,contentType);
}

net.ContentLoaderSynchro.prototype.loadXMLDocSynchro = function(url,method,params,contentType) {
	if(!method) {
		method = "GET";
	}
	if(!contentType && method=="POST") {
		contentType = "application/x-www-form-urlencoded";
	}

	//����ajax�첽�������
	if(window.XMLHttpRequest) { //mozila
		this.req = new XMLHttpRequest();
	} else if(window.ActiveXObject) { //ie
		try
		{
			this.req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		catch (err)
		{
			this.req = new ActiveXObject("Ms2.XMLHTTP");
		}
	}
	//��������ɹ�����������
	if(this.req) {
		try
		{
			var loader = this;
			//����״̬�仯��������call()����������̬���ú�����Ч����
			this.req.onreadystatechange = function() {
				net.ContentLoader.onReadyState.call(loader);
			}
			//��url������
			this.req.open(method,url,false);
			if(contentType) {
				this.req.setRequestHeader('Content-Type',contentType);
			}
			this.req.send(params);
		}
		catch (err)
		{
			this.onerror.call(this);
		}
	}

}
net.ContentLoaderSynchro.onReadyState = function() {
	var req = this.req;
	var ready = req.readyState;
	if(ready==net.READY_STATE_COMPLETED) {
		var httpStatus = req.status;
		if(httpStatus==200 || httpStatus==0) {
			this.onload.call(this);
		} else {
			this.onerror.call(this);
		}
	}
}

net.ContentLoaderSynchro.prototype.defaultError = function() {
    try{
	pubErrShow("ajax����!"+
			"\n\nreadyState:"+this.req.readyState+
			"\nhaders:"+this.req.getAllResponseHeaders());
	}catch(e){
			//alert("ajax����!"+ "\n\nreadyState:"+this.req.readyState+ "\nhaders:"+this.req.getAllResponseHeaders());
			}
}

net.ContentLoaderSynchro.prototype.defaultLoad = function() {
}
//add end yKF28472 2011/11/23 R003C11L11n01 

