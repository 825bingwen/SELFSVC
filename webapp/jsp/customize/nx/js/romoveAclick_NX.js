 function removeAclick(){
	 //当走流程时去掉顶部菜单的点击事件
	var amenus=document.getElementsByTagName("a");
	if(amenus){
   for(var i=0;i<amenus.length;i++){
		if(amenus[i].id.substr(0,4)=='nav_'){
			
			amenus[i].onclick="";
		}
	}
   }
   //当走流程时去掉回退事件
	if(document.getElementById("backBtn"))
	document.getElementById("backBtn").onclick="";
  }

//当走流程时去掉顶部菜单的点击事件
function removeMenuAclick()
{
	var amenus=document.getElementsByTagName("a");
	if(amenus){
   for(var i=0;i<amenus.length;i++){
		if(amenus[i].id.substr(0,4)=='nav_'){
			
			amenus[i].onclick="";
		}
	}
	}
}
