function createSimple(tab)
{
	var array = new Array();
	var rows = tab.rows;
	var len = rows.length;
	for(var i=0; i<len; i++)
	{
		var content = '';
		var cells = rows[i].cells;
		var cLen = cells.length;
		for(var j=0; j<cLen; j++)
		{
			content += cells[j].innerText;
		}
		
		if('' == content)
		{
			break;
		}
		
		array[i] = content;
	}
	return array;
}

function createCustArray(custTab, balanceTab)
{
	var custArray = new Array();
	
	custArray[0] = '中国移动通信客户账单';
	custArray[1] = '====================';
	custArray = custArray.concat(createSimple(custTab));
	custArray = custArray.concat(createSimple(balanceTab));
	
	return custArray;
}

function createUniteBd(tab)
{
	var array = new Array();
	var rows = tab.rows;
	var len = rows.length;
	for(var i=0; i<len; i++)
	{
		var content = '';
		var cells = rows[i].cells;
		var cLen = cells.length;
		
		for(var j=0; j<cLen; j++)
		{
			if(j == 2)
			{
				break;
			}
			content += cells[j].innerText;
			
			if(j != cLen -1)
			{
				content += '   ';	
			}
		}
		array[i] = content;
	}
	return array;
}

function createNoUniteBd(tab,flag)
{
	var array = new Array();
	var rows = tab.rows;
	var len = rows.length;
	//第一行不打印
	for(var i=1; i<len; i++)
	{
		var content = '';
		var cells = rows[i].cells;
		var cLen = cells.length;
		for(var j=0; j<cLen; j++)
		{
			if('unite' == flag)
			{
				if(j == 1)
				{
					continue;
				}
			}
			content += cells[j].innerText;
			if(j != cLen -1)
			{
				content += '   ';	
			}
		}
		array[i-1] = content;
	}
	return array;
}

function createBdArray(tab3,isUnite)
{
	var bdArray = new Array();
	
	if('1' == isUnite)
	{
		bdArray[0] = '*账户与费用信息*'
		bdArray[1] = '====================';
		bdArray[2] = '合户费用信息[金额/元]';
		bdArray = bdArray.concat(createUniteBd(tab3));
		var array = new Array('*账户与费用信息*','====================','个人费用信息[金额/元]');
		bdArray = bdArray.concat(array);
		bdArray = bdArray.concat(createNoUniteBd(tab3,'unite'));
	}
	else
	{
		var array = new Array('*账户与费用信息*','====================','费用信息[金额/元]');
		bdArray = bdArray.concat(array);
		bdArray = bdArray.concat(createNoUniteBd(tab3,'111'));
	}
	
	return bdArray;
}

function getBssTotalFee(tab)
{
	var bssFee = 0.00;
	var rows = tab.rows;
	var len = rows.length;
	var lastRow = rows[len-1];
	var cells = lastRow.cells;
	var cLen = cells.length;
	var lastCell = cells[cLen-1];
	bssFee = lastCell.innerText;
	return bssFee;
}

function createBssArray(tab)
{
	var array = new Array();
	var rows = tab.rows;
	var len = rows.length;
	var blankStr = '          ';
	
	//首尾不打印
	for(var i=1; i<len-1; i++)
	{
		var content = '';
		var cells = rows[i].cells;
		var cLen = cells.length;
		for(var j=0; j<cLen; j++)
		{
			if(j == 0)
			{
				var title = cells[0].innerText;
				if(title.substring(0,10) != blankStr && '' != title)
				{
					content += '>>';
				}
			}
			if('' != cells[j].innerText)
			{
				content += cells[j].innerText;
				content += '   ';
			}
		}
		if('' == content)
		{
			break;
		}
		array[i-1] = content;
	}
	
	return array; 
}

function createAsvArray(tab)
{
	var array = new Array();
	array[0] = '>>代收费用业务费用';
	
	var rows = tab.rows;
	var len = rows.length;
	for(var i=1; i<len; i++)
	{
		var content = '';
		if(i != len -1)
		{
			var cells = rows[i].cells;
			var cLen = cells.length;
			content += cells[1].innerText;
			content += '    ';
			content += cells[cLen-1].innerText;
		}
		else
		{
			var cells = rows[i].cells;
			var cLen = cells.length;
			content += cells[0].innerText;
			content += '    ';
			content += cells[cLen-1].innerText;
		}
		
		array[i] = content;		
	}
	return array;
}

function createSsArray(tab4,tab5,tab6)
{
	var bssArray = new Array();
	
	bssArray[0] = '*费用明细*[金额/元]';
	bssArray[1] = '====================';
	
	bssArray = bssArray.concat(createBssArray(tab4));
	bssArray = bssArray.concat(createBssArray(tab5));
	var bssFee = '费用合计  ' + getBssTotalFee(tab5);
	var array = new Array(bssFee);
	bssArray = bssArray.concat(array);
	bssArray = bssArray.concat(createAsvArray(tab6));
	
	return bssArray;
} 


function createCommArray(tab1,tab2)
{
	var commArray = new Array();
	
	commArray[0] = '*通信量使用信息明细*';
	
	commArray[1] = '====================';
	
	commArray = commArray.concat(createPkgAttr(tab1,'comm1'));
	
	commArray = commArray.concat(createPkgAttr(tab2,'comm2'));
	
	return commArray;
}

function createPkgAttr(tab,flag)
{
	var array = new Array();
	
	if(flag == 'comm1')
	{
		array[0] = '>>套餐包含通信量';
	}
	if(flag == 'comm2')
	{
		array[0] = '>>实际通信使用量';
	}
	
	var rows = tab.rows;
	var len = rows.length;
	
	//第一行不打印
	for(var i=1; i<len; i++)
	{
		var cells = rows[i].cells;
		var cLen = cells.length;
		
		var subArray = new Array();
		for(var j=0; j<cLen; j++)
		{
			if(j == 0)
			{
				subArray[0] = cells[0].innerText;
			}
			else
			{
				var strArray = cells[j].innerText.split('；');
				var strLen = strArray.length - 1;
				for(var k=0; k<strLen; k++)
				{
					strArray[k] = '      ' + (k+1) + '、' + strArray[k];
				}
				
				subArray = subArray.concat(strArray.slice(0,strLen));
			}
		}
		
		array = array.concat(subArray);
		
	}
	return array;
}

function createAccByRow(row,flag)
{
	var array = new Array();
	var si = 0;
	if("acc" == flag)
	{
		array[0] = '--账户信息--'
		array[1] = '====================';
		array[2] = '[账户项目][金额/元]';
		si = 3;
	    var titleArr = new Array('>>','上期结余','本期收入','本期返还','本期退费',
	    						 '本期可用','消费支出','其他支出','本期末结余');
		
	}
	if("mv" == flag)
	{
		array[0] = '可用积分余额'
		array[1] = '====================';
		si = 2;
	    var titleArr = new Array('可用M值余额','上期可用M值余额','本期新增M值',
	    						 '本期奖励M值','本期转入M值','本期兑换M值','本期转出M值','本期作废M值');
	}
	var cells = row.cells;
	for(var i=0; i<cells.length; i++)
	{
		array[i+si] = titleArr[i] + '   ' + cells[i].innerText;
	}
	return array;
}

function createBaccArray(tab)
{
	var baccArr = new Array();
	
	var length = tab.rows.length;
	
	for(var i=1; i<length; i++)
	{
		var row = tab.rows[i];
		baccArr = baccArr.concat(createAccByRow(row,"acc"));
	}
	return baccArr;
}


function createBmArray(tab)
{
	var bmvArr = new Array();
	
	var row = tab.rows[tab.rows.length -1];
	bmvArr = bmvArr.concat(createAccByRow(row,"mv"));
	
	return bmvArr;
}

function createIodeByRow(rowInfo)
{
	var titles = new Array('账户类型','时间','方式','入账金额','备注');
	
	var array = new Array();
	
	var cells = rowInfo.cells;
	
	for(var j=0; j<cells.length; j++)
	{
		array[j] = titles[j] + "：" + cells[j].innerText;
	}
	
	array[array.length] = '----------------------------------------------';
	
	return array;
}

function createIodeArray(tab)
{
	var iodeArr = new Array();
	iodeArr[0] = '*账户明细(元)*';
	iodeArr[1] = '====================';
	
	var rows = tab.rows;
	for(var i=1; i<rows.length; i++)
	{
		var rowInfo = rows[i];
		iodeArr = iodeArr.concat(createIodeByRow(rowInfo))
	}
	
	return iodeArr;
}
