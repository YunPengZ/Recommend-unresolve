function checkTag(str){
      		//alert(str)
      		if(str==""||str=="请选择标签！"){
      			document.getElementById("showTag").value="请选择标签！";
        		//document.getElementById("showtagHelp").style.display='block';
      			flag_tag=false;
      		}else {
      			flag_tag=true;
      		}
      	}
function save(){
	if(document.getElementById("showTag").value!=""||document.getElementById("showTag").value!="请选择标签！"){
			//document.getElementById("showTag").value="请选择标签！";
		//document.getElementById("showtagHelp").style.display='block';
			flag_tag=true;
		}
}