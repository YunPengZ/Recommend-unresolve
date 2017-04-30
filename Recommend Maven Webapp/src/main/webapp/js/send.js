/**
 * 发布用户微博
 */
function sendBlog(){       
	var content=document.getElementById("weibocontent").value;
	//点击发布的事件
	$.ajax({
		url:"operation/publish",
		dataType:"text",
		async:true,
		data:{"userId":ID,	
			"content":content},
		type:"POST",
		success:function(data){
			//alert(data);
		},
		error:function(){
			//alert("发送微博出错");
		}
		
	});
};
function fontLeng() {
    sum=140; 
    var str=document.getElementById("weibocontent").value;
    leng=Math.ceil(getLength(str));
    sum=sum-leng;
    if(sum >= 0) {
           tips= "你还可以输入<strong style=color:#FF3333>" + sum + "</strong>个字"; 
           $("#show").html(tips); 
           document.getElementById("send").disabled=false;
           
    }
    else {
           sum=-sum;
           tips="你已经超出"+ sum +"个字";
           $("#show").html(tips);
           document.getElementById("send").disabled="ture";
    }
}
function getLength(str) {
	var totallength=0;  
	for (var i=0;i<str.length;i++) {
		var intCode=str.charCodeAt(i);
		if(intCode>=0&&intCode<=128) { 
			totallength=totallength+0.5;//非中文单个字符长度加 1
     }
		else {
			totallength=totallength+1;//中文字符长度则加 2
		}
 }
	return totallength;
}
function IsNull() {
	str=document.getElementById("weibocontent").value;
	if(str=="") {
		alert("内容不能为空！");
		return false;
 }
	else { 
		sendBlog();   
		//return true; 
	}
}