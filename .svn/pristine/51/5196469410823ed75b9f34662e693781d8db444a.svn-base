
var page_data = null;  			//?????
var currentPage = 1;   			//?????
var pageSize = 5;				//????
var pageTableName = null;		//??????ID
var pageAllPage = 0;			//???
var currentPageNumber = null;	//??????????
var ondblclickHandler = "";		//??????????????
var onclickHandler = "";		//??????????????
var alignarray = null;

function initPage(dataArray , pageSize,tableName,arrayalign){
	if(dataArray == null){
		page_data = new Array(new Array());
	}else{
		page_data = dataArray;
	}
	
	if(!validateInteger(pageSize)){
		this.pageSize = 5;
	}else{
		this.pageSize = pageSize;
	}
	if (arrayalign != null){
	    alignarray = arrayalign
	}
	
	var pageAllCount = document.getElementById("pageAllCount");
	if (pageAllCount != null){
	    pageAllCount.innerHTML = dataArray.length;	    
	}
		
	var pageAllPageTag = document.getElementById("pageAllPage");
	if (pageAllPageTag != null)
	{
	    if(page_data.length%pageSize == 0){
    		pageAllPage = parseInt(page_data.length/pageSize);
    	}else{
    		pageAllPage =parseInt(page_data.length/pageSize) +1;
    	}
	    pageAllPageTag.innerHTML = pageAllPage ;
	}
	
	currentPageNumber = document.getElementById("currentPageNumber");
	if (currentPageNumber != null){
	    currentPageNumber.innerHTML = 1;
	}
	
	pageTableName = tableName;
	if ((page_data.length > 0)&&(page_data[0].length > 0)){
	    showArrayOnTable(page_data,0,pageSize,pageTableName);
	}
}

function firstPage(){
	if(page_data.length <= pageSize){
		return;
	}
	showArrayOnTable(page_data,0,pageSize,pageTableName);
	currentPage = 1;
	currentPageNumber.innerHTML = 1;
}

function lastPage(){
	if(page_data.length <= pageSize){
		return;
	}
	var value = page_data.length%pageSize;
	if(value == 0){
		value = pageSize;
	}
	var startIndex = page_data.length- value;
	if(startIndex < 0){
		startIndex = 0;
	}
	showArrayOnTable(page_data,startIndex,pageSize,pageTableName);
	currentPage = pageAllPage;
	currentPageNumber.innerHTML = currentPage;
}

function previousPage(){
	
	if(currentPage < 2)
		return;
	else{
		var startIndex = (currentPage-2)*pageSize;
		showArrayOnTable(page_data,startIndex,pageSize,pageTableName);
		currentPage--;
		currentPageNumber.innerHTML = currentPage;
	}
	
}

function nextPage(){
	
	if(currentPage*pageSize >= page_data.length)
		return;
	else{
		var startIndex = currentPage*pageSize;
		showArrayOnTable(page_data,startIndex,pageSize,pageTableName);
		currentPage++;
		currentPageNumber.innerHTML = currentPage;
	}

}
function goPage( goPageIndex ){

	var value = document.getElementsByName("goPageIndex")[0].value;
	if( !validateInteger(value) ){
		document.getElementsByName("goPageIndex")[0].select();
		alert("输入的不是正整数!");
		return;
	}
	
	if(value > 0 && (value-1)*pageSize < page_data.length ){
		
		var startIndex = (value-1)*pageSize;
		
		showArrayOnTable(page_data,startIndex,pageSize,pageTableName);
		currentPage = value;
		currentPageNumber.innerHTML = currentPage;
	}
	
}

function  showArrayOnTable(  arrayData,  startIndex, pageSize, tableID){
	
	var showData = arrayData.slice(startIndex,startIndex+pageSize);
	var table = document.getElementById(tableID);
	var rows = table.rows;
	for(var i = 0 ; i <	showData.length; i++ ){
		var row = null;
		if(rows[i+1] == null){
			row  = table.insertRow();
		}else{
			row = rows[i+1];
		}
		row.height = 25;
		//row.ondblclick = new Function(ondblclickHandler);
		//row.onclick = new Function(onclickHandler);
		
		var cells = row.cells;
		//alert(showData[i]);
		for(var m = 0; m < showData[i].length ; m++){
			var cell = cells[m];
			if(cell == null){
				cell = row.insertCell();
			}
			cell.className = "border_RightBottom"+(i%2 == 0?"2":"");
			cell.innerHTML = showData[i][m];
			if (alignarray != null)
			{
			    cell.align = alignarray[m];
			}
		}
	}

	while(rows.length-1 > showData.length){
		table.deleteRow();
	}
}

function validateInteger(value){
  	var reg = /^[1-9]\d*$/;
  	return reg.test(value);
}

function doGoForEnter(src){

	if(	event.keyCode == 13){
		goPage(src.name);
	}
}
