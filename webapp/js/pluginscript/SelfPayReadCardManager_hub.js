/***********************************************

				读卡器与银联扣费相关操作

***********************************************/

/**
功能: 初始化读卡器 
返回: 0:初始化成功 -1:初始化失败
场景: 调用读卡器接口前先进行初始化
**/
function InitReadUserCard(unionTermID, unionPayCode)// 终端初始化
{
	//return '0';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").InitConfig(unionTermID, unionPayCode);
		return ret;
	}catch(e){
		return '-1';
	}
}

/**
功能：读卡器控件调用自助终端上的读卡设备控件启动等待读卡线程，电动读卡器需打开仓门接收用户银行卡
返回：成功标识$错误提示(成功则无此字段) 0:成功 -1:失败
场景：用户刷卡前调用此接口，启动自助终端上的读卡设备及相关程序，读卡设备会一直等待用户刷卡
**/
function ReadingUserCard()// 准备刷卡
{	
	//return '0';
	try{
		var ret = window.parent.document.getElementById("cardpluginid").ReadingBankCard();
		return ret;
	}catch(e){
		return '-1';
	}
}

/**
功能：返回用户刷卡状态（读卡器读卡结果）
返回：0 读卡成功；-1 读卡失败；1 尚未读取到卡信息
场景：调用准备刷卡接口（ReadingUserCard ）后，页面定时循环调用此接口获取读卡器读卡结果。如果返回0表示读卡成功，停止定时循环调用；如果返回-1表示读卡失败，停止定时循环调用；如果返回1表示设备尚未读取到卡信息，继续定时循环调用。在指定时间（30秒）内设备一直未读取到卡信息，则认为读卡超时，停止定时循环调用。
**/
function ReadUserCardFinished()// 刷卡完毕接口
{
	//return '0';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").ReadBankCardFinished();
		return ret;
	}catch(e){
		return '-1';
	}
}

/**
 * 功能：通知自助终端读卡设备准备读取用户银行卡密码，并切换到密码键盘模式
 * 返回：成功标识$错误提示(成功则无此字段)
 * 场景：用户输入密码前，调用此接口
 */
function ReadingPassword()
{
	//return '0';
	var ret;
	try
	{
		ret = window.parent.document.getElementById("cardpluginid").ReadingPassword();
		
		return ret;
	}
	catch (e)
	{
		return '-1';
	}
}

/**
 * 功能：获取当前用户密码输入情况
 * 返回：0 表示输入正常结束；-1 表示密码输入未完，异常结束输入；1 等待用户输入完毕
 * 场景：调用准备输入密码接口（ReadingPassword()）后，定时循环调用此接口获取用户密码输入状态。
 */
function ReadCardPasswordFinished()
{
	//return '0';
	var ret;
	try
	{
		ret = window.parent.document.getElementById("cardpluginid").ReadPasswordFinished();
		
		return ret;
	}
	catch (e)
	{
		return '-1';
	}
}

/**
 * 功能：银联签到和获取跟踪号和批次号
 * 返回：成功返回:0$跟踪号$批次号;失败返回:-1
 * 场景：每次刷卡交易前BOSS调用获取跟踪号和批次号接口
 */
function GetPosBatchNum()
{
	//return '0$genzong$pici';
	var ret;
	try
	{
		ret = window.parent.document.getElementById("cardpluginid").GetPosBatchNum();
		
		return ret;
	}
	catch (e)
	{
		return '-1';
	}
}

/**
功能：关闭读卡器和密码键盘所有打开的线程
返回：成功标识$错误提示(成功则无此字段)
场景：当自助终端读卡设备检查到用户已经放卡时，调用此接口关闭读卡设备和密码键盘及相关线程，然后调用银联接口，供银联使用相关设备。
**/
function CloseReadingCardFixing()// 卡机和密码键盘关闭接口
{	
	//return 0;
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").CloseReadingCardFixing();
		return ret;
	}catch(e){
		showFrmErr("发生异常,关闭读卡器和密码键盘时出错!");
		return -1;
	}
}

/**
功能：通知自助终端上读卡设备用户放弃刷卡，关闭读卡设备及相关线程，终止读卡设备等待读卡状态
返回：成功标识$错误提示(成功则无此字段)
场景：当自助终端读卡设备处于等待用户刷卡时，若用户决定不刷卡，调用此接口取消读卡设备的等待状态，关闭读卡设备及相关线程。
**/
function CancelReadingUserCard()// 取消刷卡
{	
	//return '0';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").CancelReadingBankCard();
		return ret;
	}catch(e){
		return '-1';
	}
}

// 功能：判断当前读卡器内是否有卡，如果有卡首先退卡，再关闭所有读卡相关线程，并清除银行卡信息和密码信息。接口自动判断当前设备时读卡器还是刷卡器，如果为刷卡器则不需要做退卡操作。
// 返回：成功标识$错误提示(成功则无此字段)
// 场景：交易完成或刷卡过程中，用户已刷卡（或将卡插入读卡器内），且提交扣款请求前，用户主动取消交易，或者发生异常，调用此接口退卡，关闭读卡相关线程。
function TakeOutUserCard()// 退卡接口
{	
	//return '0';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").TakeOutBankCard();
		return ret;
	}catch(e){
		return '-1';
	}
}

/**
功能：接口自动判断当前设备时读卡器还是刷卡器，如果为读卡器，返回设备退卡后用户取卡的状态（用户已取走银行卡返回1，用户尚未取走银行卡返回0）；如果为刷卡设备，则可以直接返回0。
返回：0 表示用户已取走卡；1 表示用户尚未取走卡
场景：读卡设备退卡后，由自助平台页面主动定时循环调用此接口，判断用户是否取走卡。在指定时间(30秒)内，没有取走卡，则认为用户取卡超时，在后续操作中页面需要调用吞卡接口。
**/
function TakeOutUserCardStatus()// 获取读卡器取卡状态
{	
    //return '0';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").TakeOutBankCardStatus();
		return ret;
	}catch(e){
		return '-1';
	}
}

/**
功能：吞卡
返回：成功标识$错误提示(成功则无此字段)
场景：当设备退卡后，用户在指定时间范围内没有取走卡，则调用此接口吞掉用户的卡。
**/
function captureUserCard()// 吞卡
{	
	//return '0';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").CaptureBankCard();
		return ret;
	}catch(e){
		return '-1';
	}
}

/**
功能：获取用户卡号
返回：成功返回:卡号，失败返回""
场景：在接收到ReadBankCardFinished()事件读卡成功的通知后，应及时调用此接口，并保存到页面的元素中，以确保后续的页面能获取到银行卡号信息。
**/
function GetUserCardInfo()// 获取卡信息接口
{
	//return '1234567890123456';
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").GetBankCardID();
		return ret;
	}catch(e){
		return '';
	}
}

/**
功能：银联交易
参数：
	strin
	操作员号	    ASCII	10	不足右补空格
	手机号	    ASCII	18	不足右补空格
	交易类型标志	ASCII	1	为'A'，表示是（消费交易）；为'B'，表示是（重打印）；为'C'，表示（查余额）
	金额	        ASCII	12	以分为单位，不足左补‘0’, 如果交易金额为”000000000000”,程程序将根据交易类型标志完成相应的操作，可以完成重打票据，查询余额
	例如： 987654321 13951878070       A000000001234000000  
	表示：柜员号为 987654321， 消费交易， 交易金额为12.34
	例如： 987654321 13951878070       C000000000000000000  
	表示：柜员号为 987654321， 表示查余额交易
返回：
	Resultstr 交易相应传出参数
	判断时先判断前两位是否为“00”
	如果不是，表示交易失败；
	如果是，则继续按下表取得所需要的值；
	交易类型为A、B时，交易成功需要打印printcontext

	内容	          格式	     域长	    描   述
	响应码	    ASCII	     2	    00 表示成功，其它表示失败
	卡号	        ASCII	     19	     不足右补空格
	手机号	    ASCII	     18	     不足右补空格
	交易类型标志	ASCII	     1	     为'A'，表示是（消费交易）；为'B'，表示是（重打印）；为'C'，表示（查余额）。
	金额	        ASCII	     12	     以分为单位，不足左补‘0’
	
	例如：00456351000000000012313951878070       A000000001234
	表示：交易成功，卡号为4563510000000000123，手机号为13951878070，金额为12.34
	例如：ER。。。。
	表示：交易失败（错误码为ER）
	
	printcontext 打印文件内容
	商户编号:???????????????                          
	终端编号:????????                                 
	交易类型:???????????????                          
	银行卡号:???????????????????                      
	批 次 号:?????? 授权码:??????                     
	交易参考号:????????????                           
	日期时间:???????????????????                      
	凭 证 号 :????????????                            
	金额(RMB):??????????????????      
	
	场景：在关闭卡机和密码键盘的串口后，调用此接口与银联向银联发起扣款请求。
**/
// posnum 跟踪号;batchnum 批次号； bankcardID银行卡号；money 缴费金额(分)
function Pay(posnum,batchnum,bankcardID,money)// 扣款接口
{
	// 测试扣款成功
	//return "006222021602004724078A000000001234898420148145268~00167923~消费交易~6225887840088682~000725~~171633071883~20110817171633~~1";

	// 测试扣款失败
	//return "ER6222021602004724078A000000001234密码错误";
	
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").Pay(posnum,batchnum,bankcardID,money);
	}catch(e){
		showFrmErr("发生异常,银联卡扣款失败!");
		return "ER";
	}
	return ret;
}

/**
功能：打开设备所用的串口并创建监听线程
返回：0 表示正常；1 端口（串口）故障；否则 见返回值说明
场景：首页密码键盘所有接口方法使用前，调用本接口打开该设备
**/
function OpenCom()
{
	//return "0";
	var ret;
	try{
		ret = window.parent.document.getElementById("keybrdpluginid").OpenCom();
		return ret;
	}catch(e){
		showFrmErr("发生异常,打开密码键盘串口失败！");
		return "-1";
	}	
}

/**
功能：关闭设备所用的串口
返回：0 表示正常；否则 见返回值说明
场景：调用准备刷卡后调用此方法
**/
function CloseCom()
{
	//return "0";
	var ret;
	try{
		ret = window.parent.document.getElementById("keybrdpluginid").CloseCom();
		return ret;
	}catch(e){
		showFrmErr("发生异常,关闭密码键盘串口失败！");
		return "-1";
	}	
}

/**
功能：设置工作模式
参数：WorkMode – 0：普通键盘模式；1：加密键盘模式
返回：见返回值说明
场景：首页密码键盘打开后，在使用前调用本接口设置密码键盘工作模式
**/
function SetWorkMode(WorkMode)
{
	//return "0";
	var ret;
	try{
		ret = window.parent.document.getElementById("keybrdpluginid").SetWorkMode(WorkMode);
		return ret;
	}catch(e){
		showFrmErr("发生异常,设置密码键盘的工作模式失败！");
		return "-1";
	}
}

/**
功能：补字符
参数：resource:传入字符串 
	 align:left right  
	 sign:替换的符号
	 count:个数
返回：组装好的字符串字符串
**/
function formatStr(resource,align,sign,count)
{
	var str = "";
	for(var i=0;i<count-resource.length;i++){
		str = str + sign;
	}
	if("left" == align){// 左补
		str = str + resource;
	}else if("right" == align){// 右补
		str = resource + str;
	}
	return str;
	
}

/**
 * 读银联卡初始化方法
 * @remark create by sWX219697 2015-4-21 OR_NX_201501_1030_关于跨区服务业务支撑系统改造的通知
 */
function readCardInit() 
{
	try 
	{
	    // 接口调用返回信息
		var ret;
		
		// 打开提示框
		alertJd("正在加载,请稍候...");
		
		// 关闭键盘串口
		try
		{
			ret = CloseCom();
		}
		catch(e)
		{
			// 打开键盘串口、设置明文模式
			OpenCom();
			SetWorkMode(0);
			return "关闲密码键盘串口发生异常！";
		}
		
		if (ret != "0" && ret != 0) 
		{
			// 打开键盘串口、设置明文模式
			OpenCom();
			SetWorkMode(0);
			
			return "关闲密码键盘串口发生异常！";
		}

		// 准备刷卡（此接口包括签到银联功能）
		try
		{
			ret = ReadingUserCard();
		}
		catch(e)
		{
			// 打开键盘串口、设置明文模式
			OpenCom();
			SetWorkMode(0);
			
			return "调用准备刷卡发生异常！";
		}

		// 关闲提示框
		wiWindow.close()

		// 调用准备刷卡后返回
		if (ret != "0" && ret != 0) 
		{
            return "读卡设备控件启动等待读卡线程失败，无法使用储蓄卡进行充值，请选用其它方式。";
		}
		else
		{		
			
			//等待读卡进程启动成功后，就需要关闭该进程。而”首页“、“退出”按钮无法执行此操作，所以禁用”首页“、“退出”按钮
            document.getElementById("homeBtn").disabled = true;
            document.getElementById("quitBtn").disabled = true;
			
			// 打开键盘串口、设置明文模式
			OpenCom();
			SetWorkMode(0);
			
			// 下一步启动定时任务
			return "success";
		}
	}
	catch (ex) 
	{
        return "读卡设备控件启动等待读卡线程失败，无法使用储蓄卡进行充值，请选用其它方式。";
    }
}

/**
 * 银联卡密码读取初始化
 * @remark create by sWX219697 2015-4-21 OR_NX_201501_1030_关于跨区服务业务支撑系统改造的通知
 */
function readPwdInit()
{
	try 
	{				
		// 关闭键盘串口
		var ret = CloseCom();
		if (ret != "0" && ret != 0) 
		{
			return "准备刷卡前调用关闲密码键盘串口发生异常！";
		}
		
		// 准备输入密码 通知自助终端读卡设备准备读取用户银行卡密码，并切换到密码键盘模式
		var ret = ReadingPassword();
		if (ret != "0" && ret != 0) 
		{
            return "准备读取用户银行卡密码失败，无法使用储蓄卡进行充值。请取走您的储蓄卡。";
		}
		else
		{		
			// 禁用”首页“、“退出”按钮
            document.getElementById("homeBtn").disabled = true;
            document.getElementById("quitBtn").disabled = true;
			
            // 初始化成功
			return "success";;
		}
		
	}
	catch (ex) 
	{
        return "准备读取用户银行卡密码失败，无法使用储蓄卡进行充值。请取走您的储蓄卡。";
	}
}

/**
 * 输入银联密码超时后，是否关闭密码键盘
 * @param IsNeedCloseEncryptKey 是否关闭密码键盘，1：关闭。
 * @remark create by sWX219697 2015-4-21 OR_NX_201501_1030_关于跨区服务业务支撑系统改造的通知
 */
function closePwdKey(IsNeedCloseEncryptKey)
{
	var ret;
	try
	{
		if(IsNeedCloseEncryptKey != "1")
		{
			return "读取用户银行卡密码超时，无法使用储蓄卡进行充值。请取走您的储蓄卡。";
		}
		
		ret = window.parent.document.getElementById("cardpluginid").CloseEncryptKey();
		
		if(ret == "0")
		{
			return "读取用户银行卡密码超时，无法使用储蓄卡进行充值。请取走您的储蓄卡。";
		}
		else
		{
			return "系统设置明文模式失败，无法使用储蓄卡进行充值。请取走您的储蓄卡。";
		}
	}
	catch(e)
	{
		return "读取用户银行卡密码超时，无法使用储蓄卡进行充值。请取走您的储蓄卡。";
	}
}