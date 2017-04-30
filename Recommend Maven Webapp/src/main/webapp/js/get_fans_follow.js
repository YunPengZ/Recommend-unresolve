var user_follow=document.getElementById("user_follow");
var user_fans=document.getElementById("user_fans");
//var user_blog=document.getElementById("user_blog"); 

function queryuserfollower(pageNumber,userId){
	   		//alert("may a test");
		 	//$("#follower").empty();
		 	 if((pageNumber-1)  * pageSize > totalRecord){
		        // alert("没有更多微博！+totalRecord"+totalRecord);
		        // $(window).unbind("scroll");
		         return ;
		      }
		 	 else $.ajax({
	    		url: "query/user/follower",    //请求的url地址
	    	    dataType: "JSON",   //返回格式为json
	    	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
	    	    data: { "id": userId,
	    	    	"page_num":pageNumber,
	    	    	    },    //参数值
	    	    type: "POST",   //请求方式
	    	    success: function(data) {
	    	    	totalRecord = data.totalRecord;
	    	    	user_follow.innerHTML="关注"+totalRecord;
	    	    	if(totalRecord<=0)alert("暂无关注者");
        	    	var userset=data.dataList;
        
        	    	$.each(userset,function(i,item){
        	    			$("#follower").append(
        	    					"<div class=\"row clearfix box\">"+
    								"<div class=\"card\">"+
    									"<div class=\"media-left\">"+
    	                                    "<a href=\"#\"><img class=\"media-object img-thumbnail\" src=\"src\\user.png\"></a>"+
    	                                "</div>"+
    									"<div class=\"media-body\">"+
    	                                    "<h4 style=\"font-weight: bold;\">"+item.followname+"</h4>"+
    									"</div>"+
    								"</div>"+
    							"</div>"
    							);
        	    	});
        	    
        	    },
	    	    error: function() {
	    	        alert("出错");
	    	    }
	    		
	    	});
		}
	 function queryuserfans(pageNumber,userId){
		//alert("may a test");
		 	//$("#fans").empty();
		 	 if((pageNumber-1)  * pageSize > totalRecord){
		        // alert("没有更多微博！+totalRecord"+totalRecord);
		        // $(window).unbind("scroll");
		         return ;
		      }
		 	 else
	   		$.ajax({
	    		url: "query/user/fans",    //请求的url地址
	    	    dataType: "JSON",   //返回格式为json
	    	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
	    	    data: { "id": userId,
	    	    	"page_num":pageNumber,
	    	    	    },    //参数值
	    	    type: "POST",   //请求方式
	    	    success: function(data) {
	    	    	totalRecord = data.totalRecord;
	    	    	user_fans.innerHTML="粉丝"+totalRecord;
	    	    	var userset=data.dataList;
	    	    	if(totalRecord<=0)alert("无粉丝!");
	    	    	else
	    	    	$.each(userset,function(i,item){
     	    			$("#fans").append(
     	    					"<div class=\"row clearfix box\">"+
 								"<div class=\"card\">"+
 									"<div class=\"media-left\">"+
 	                                    "<a href=\"#\"><img class=\"media-object img-thumbnail\" src=\"src\\user.png\"></a>"+
 	                                "</div>"+
 									"<div class=\"media-body\">"+
 	                                    "<h4 style=\"font-weight: bold;\">"+item.fansname+"</h4>"+
 									"</div>"+
 								"</div>"+
 							"</div>"
 							);
     	    	});
     	    
     	    },
	    	    error: function() {
	    	        alert("出错");
	    	    }
	    		
	    	});
	 }