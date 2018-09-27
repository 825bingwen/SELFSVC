
//播放列表个数是否一样,一样1,不一样0
var wplSize = 0;

//下载列表
var downList = new Array();

//下载文件个数
var downSum = 0;

//文件列表
var playNames = new Array();

//是否更新播放列表,是0,否1
var updateWpl = 1;

//是否更新本地资源文件列表,是0,否1
var updateFileList = 1;

/**
 * 生成并下载文件
 * url 取得播放文件内容
 * playFileName 播放列表文件名
 * mediaType 多媒体类型
 * wplFileName 本地播放列表文件名
 */
function getMediaList(url, playFileName, mediaType, wplFileName) {
	var resXml;
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			// 后台取文件内容
			resXml = this.req.responseText;
	}, null, "POST", "", null);
	
	// 如果为"" 判断是否为0也可
	if (resXml == 0) 
	{
		//获取列表失败,或无列表
		return resXml;
	}
	
	// 生成并下载文件
	fUpdateFile(localURL, playFileName, resXml, mediaType, wplFileName);
	
	return resXml;
}

/**
 * 生成并下载文件主体代码
 * 此方法会比较从服务器获取的文件列表与本地存储的文件列表，
 * 如果本地列表不存在或与服务器获取的文件列表不同，则会下载不同的文件并更新本地播放列表(sc.wpl)与文件列表（scfile.xml）
 * localURL 本地存放路径
 * playFileName 播放列表文件名
 * remoteResXml 远程返回的XML
 * mediaType 多媒体类型
 * wplFileName 本地播放列表文件名
 */
function fUpdateFile(localURL, playFileName, remoteResXml, mediaType, wplFileName) {
	try {
		var remoteXmlDoc = getDocument(remoteResXml);
		var remoteMedias = remoteXmlDoc.getElementsByTagName("media");
		var remoteSize = remoteMedias.length;
		var loclXmlDoc;
    	//声明一个 FileSystemObject 的实例
		var myFileSysObj = new ActiveXObject("Scripting.FileSystemObject");
		if (!myFileSysObj.FolderExists(localURL)) {// 如果D:\media不存在者创建
			myFileSysObj.CreateFolder(localURL);
		}
		if (!myFileSysObj.FileExists(localURL + "/" + playFileName)) {
			//如果本地连播放列表都没有,则重新下载所有文件及播放列表
			for (var i = 0; i < remoteSize; i++) {
				downMediaFile(rootPath + "/" + getXmlData(remoteMedias[i], "downLoadPath"), localURL + "/" + getXmlData(remoteMedias[i], "resName"));
				playNames[i] = getXmlData(remoteMedias[i], "resName");
			}
			// 写播放列表文件(scfile.xml)
			writePlayList(myFileSysObj, localURL + "/" + playFileName, remoteResXml);
			// 写本地播放列表文件(sc.wpl)
			writeWpl(myFileSysObj, mediaType, localURL + "/" + wplFileName);
		} else {
			if (IsIE() == 1) {
				loclXmlDoc = new ActiveXObject("Msxml2.DOMDocument");
				loclXmlDoc.async = false;
				loclXmlDoc.load(localURL + "/" + playFileName);
				var state = loclXmlDoc.readyState;
				if (state == 4) {
					var oNodes = loclXmlDoc.selectNodes("//root/media");
					var localSize = oNodes.length;
					if (remoteSize == localSize) {
						wplSize = 1;// 多媒体文件相同
					} else {
						wplSize = 0;// 多媒体文件不同
						updateWpl = 0;// 0:更新 1:不更新(sc.wpl、adv.wpl)
						updateFileList = 0;// 0:更新 1:不更新(scfile.xml、advfile.xml)
					}
					var isFound = false;
					for (var i = 0; i < remoteSize; i++) {
						for (var j = 0; j < localSize; j++) {
							if (getXmlData(oNodes[j], "resID") == getXmlData(remoteMedias[i], "resID")) {
								isFound = true;
								playNames[i] = getXmlData(remoteMedias[i], "resName");
								if (getXmlData(oNodes[j], "sortID") != getXmlData(remoteMedias[i], "sortID")) {
									//只修改序号,不用重新下载
									updateWpl = 0;
									updateFileList = 0;
								}
								if (needDownload(oNodes[j], remoteMedias[i]))
								{
									downList[downSum] = [getXmlData(remoteMedias[i], "downLoadPath"), getXmlData(remoteMedias[i], "resName")];
									downSum++;
									updateWpl = 0;
									updateFileList = 0;
								}
								break;
							}
						}
						//没有找到的资源
						if (!isFound) {
						    updateWpl = 0;
						    updateFileList = 0;
							playNames[i] = getXmlData(remoteMedias[i], "resName");
							downList[downSum] = [getXmlData(remoteMedias[i], "downLoadPath"), getXmlData(remoteMedias[i], "resName")];
							downSum++;
						}
						isFound = false;
					}
					if (0 < downSum) {
						for (var n = 0; n < downList.length; n++) {
							downMediaFile(rootPath + "/" + downList[n][0], localURL + "/" + downList[n][1]);
						}
					}
					if (0 == updateWpl) {
						writeWpl(myFileSysObj, mediaType, localURL + "/" + wplFileName);
					}
					if (0 == updateFileList) {
						writePlayList(myFileSysObj, localURL + "/" + playFileName, remoteResXml);
					}
				}
			} else {
			}
		}
	}catch (e) {
	    //失败记个日志
		var writeLogObj = new ActiveXObject("Scripting.FileSystemObject");
		var createFile = writeLogObj.CreateTextFile(localURL + "/error" + _getNowTime() + ".log", true);
		createFile.WriteLine("更新媒体文件在" + _getNowDate() + "发生错误!");
		createFile.WriteLine("错误信息为:" + e.description);
		createFile.Close();
	}
}

/**
 * 判断本地屏保文件是否需要更新（下载）
 * 其判断主要依据本地资源文件与远程下载的资源文件中：
 * 资源名称是否相同、状态时间是否相同、播放时间是否相同及资源文件是否存在。
 * @param localMedia 本地资源描述(xml字符串)
 * @param remoteMedia 远程下载资源描述(xml字符串)
 * 
 * @returns true--需要更新此文件；false--不需要更新
 * @create by l00263786 2015-06-02 OR_SD_201504_102 自助终端屏保实现全省统一配置
 */
function needDownload(localMedia, remoteMedia)
{
	return getXmlData(localMedia, "resName") != getXmlData(remoteMedia, "resName") 
		|| getXmlData(localMedia, "statusDate") != getXmlData(remoteMedia, "statusDate") 
		|| getXmlData(localMedia, "resPlayTime") != getXmlData(remoteMedia, "resPlayTime")
		|| !scFileExist(localURL + "/" + getXmlData(remoteMedia, "resName"));
}

/**
 * 判断屏保资源是否存在
 * 其判断包括屏保资源文件scfile.xml与播放列表文件sc.wpl是否存在，
 * 及其对应的真正的屏保文件（如图片或视频）是否存在。
 * @param localFilePath 本地（终端机）保存屏保文件路径
 * @param scWplFileName 屏保播放列表sc.wpl
 * @param scResFileName 屏保资源文件列表 scfile.xml
 * @create by l00263786 20150616 OR_SD_201504_102 自助终端屏保实现全省统一配置
 */

function scResExist(localFilePath,scWplFileName,scResFileName)
{
	// 获取播放列表文件全路径
	var scWplFilePath = localFilePath + "/" + scWplFileName;
	//如果文件播放列表不存在，返回false
	if(!scFileExist(scWplFilePath))
	{
		return false;
	}
	// 获取屏保资源文件列表
	var scResFilePath = localFilePath + "/" + scResFileName;
	// 如果屏保资源文件列表不存在，返回false
	if(!scFileExist(scResFilePath))
	{
		return false;
	}
	if (IsIE() == 1) 
	{
		// 加载资源描述文件
		loclXmlDoc = new ActiveXObject("Msxml2.DOMDocument");
		loclXmlDoc.async = false;
		loclXmlDoc.load(scResFilePath);
		var state = loclXmlDoc.readyState;
		// 加载成功，对其内容进行判断
		if (state == 4)
		{
			// 获取资源描述节点
			var oNodes = loclXmlDoc.selectNodes("//root/media");
			// 如果资源描述节点为空，则返回false，认为屏保资源不存在
			if(oNodes.length == 0)
			{
				return false;
			}
			// 遍历屏保资源节点，判断其文件是否存在
			for(var i = 0; i < oNodes.length; i++)
			{
				// 如果有文件存在，则返回true，认为屏保文件存在
				if(scFileExist(localFilePath + "/" + getXmlData(oNodes[i], "resName") ))
				{
					return true;
				}
			}
		}
	}
	return false;
}
/**
 * 判断本地文件是否存在 
 * @param absoluteFilePath 本地文件对应的绝对路径 
 * @returns true--文件存在；false--文件不存在 
 * @create by l00263786 2015-06-01 OR_SD_201504_102 自助终端屏保实现全省统一配置
 */
function scFileExist(absoluteFilePath)
{
    // 获取一个文件系统对象，主要用来判断文件是否存在。
    var myFileSysObj = new ActiveXObject("Scripting.FileSystemObject");
    return myFileSysObj.FileExists(absoluteFilePath);
}
/**
 * 判断屏保文件是否下载成功
 * 如果有一个文件存在，即认为下载成功，否则认为下载失败。 
 * @param xmlFileList 文件描述(xml字符串)
 * @returns true--文件中至少有一个存在；false--所有文件都不存在
 * 
 * @create by l00263786 2015-05-30 OR_SD_201504_102 自助终端屏保实现全省统一配置
 */
function isMediaScDownloaded(xmlFileList)
{
	var xmlScreenFiles = getDocument(xmlFileList);
	var medias = xmlScreenFiles.getElementsByTagName("media");
	// 获取一个文件系统对象，主要用来判断文件是否存在。
	var myFileSysObj = new ActiveXObject("Scripting.FileSystemObject");
	for(var i = 0;i<medias.length;i++)
	{
		var media = medias[i];
		// 获取播放列表文件的绝对路径
		var absoluteFilePath = localURL + "/" + getXmlData(media, "resName")
		if(myFileSysObj.FileExists(absoluteFilePath))
		{
			return true;
		}
	}
	return false;
}
/**
 * 取得xml数据的documentElement对象
 */
function getDocument(xmlStr) {
	try {
		var xmlDoc;
		if (IsIE() == 1) {
			xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
			xmlDoc.async = "false";
			xmlDoc.loadXML(xmlStr);
			return xmlDoc;
		} else {
			var parser = new DOMParser();
			xmlDoc = parser.parseFromString(xmlStr, "text/xml");
			return xmlDoc;
		}
	}
	catch (e) {
		alert(e.description);
	}
}
/**
 * id xml对象的ID属性
 */
function getXmlDocument(id) {
	var oXml = document.getElementById(id);
	return oXml.XMLDocument;
}
function getXmlData(xml, itemName) {
	var items = xml.getElementsByTagName(itemName);
	var itemData = "";
	if (items.length > 0) {
		itemData = items[0].childNodes[0].nodeValue;
	}
	return itemData;
}

/**
 * 生成wpl文件
 * myFileSysObj FileSystemObject实例 
 * path 咱径+文件名
 * resInfo 文件内容
 */
function writePlayList(myFileSysObj, path, resInfo) {
	if (myFileSysObj.FileExists(path)) {
		var fileObj = myFileSysObj.GetFile(path);
		// 删除文件 
		fileObj.Delete();
	}
	var createFile = myFileSysObj.CreateTextFile(path, true);
	createFile.WriteLine(resInfo);
	createFile.Close();
}

/**
 * 生成wpl文件
 * myFileSysObj FileSystemObject实例 
 * type 文件类型
 * path 咱径+文件名
 */
function writeWpl(myFileSysObj, type, path) {
	if (myFileSysObj.FileExists(path)) {
		var fileObj = myFileSysObj.GetFile(path);
		// 删除文件 
		fileObj.Delete();
	}
	var ctf = myFileSysObj.CreateTextFile(path, true);
	ctf.WriteLine("<?wpl version=\"1.0\" encoding=\"utf-8\"?>");
	ctf.WriteLine("<smil>");
	ctf.WriteLine("<head>");
	ctf.WriteLine("<meta name=\"Generator\" content=\"Microsoft Windows Media Player -- 9.0.0.3075\"/>");
	ctf.WriteLine("<title>" + type + "</title>");
	ctf.WriteLine("</head>");
	ctf.WriteLine("<body>");
	ctf.WriteLine("<seq>");
	var size = playNames.length;
	for (var i = 0; i < size; i++) {
		ctf.WriteLine("<media src=\"" + localURL + "/" + playNames[i] + "\"/>");
	}
	ctf.WriteLine("</seq>");
	ctf.WriteLine("</body>");
	ctf.WriteLine("</smil>");
	ctf.Close();
}
function getNowHour() {
	var nowDate = new Date();
	return nowDate.getHours();
}
function _getNowDate() {
	var now = new Date();
	var year = now.getYear();
	var month = now.getMonth() + 1;
	var day = now.getDate();
	var hour = now.getHours();
	var minute = now.getMinutes();
	var second = now.getSeconds();
	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	if (hour < 10) {
		hour = "0" + hour;
	}
	if (minute < 10) {
		minute = "0" + minute;
	}
	if (second < 10) {
		second = "0" + second;
	}
	return year + "-" + month + "-" + day + " " + hour + ":" + minute + " " + second;
}

//获取当天日期yyyyMMddhhmmss格式
function _getNowTime() {
	var now = new Date();
	var year = now.getYear();
	var month = now.getMonth() + 1;
	var day = now.getDate();
	var hour = now.getHours();
	var minute = now.getMinutes();
	var second = now.getSeconds();
	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	if (hour < 10) {
		hour = "0" + hour;
	}
	if (minute < 10) {
		minute = "0" + minute;
	}
	if (second < 10) {
		second = "0" + second;
	}
	return year + "" + month + "" + day + "" + hour + "" + minute + "" + second;
}

