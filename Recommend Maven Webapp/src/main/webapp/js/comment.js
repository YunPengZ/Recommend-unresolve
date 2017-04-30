var commentClick=0;
var oldId=null;
function CommentClick(blogId){
	//alert("test maybe failed")
	if(blogId==oldId){
		if(commentClick==0||commentClick==2){
			commentClick=0;
			QueryComment(blogId);
		}else {
			$("#"+blogId).empty();
		}
	}else QueryComment(blogId);
	oldId=blogId;
	commentClick++;
}

function publishComment(blogId){
	var content=document.getElementById(blogId+"comment_publish").value;
	if(content==""||content==null)alert("请输入评论");
	else{
		//var str = $("#wchatcontent").val();
	     
     	var json={"from":ID,"to":ID,"message":content,"kind":"评论"};
     	 websocket.send(JSON.stringify(json)); 
		$.ajax({
			url:"query/addcomment",
			dataType:"text",
			async:true,
			data:{"blogId":blogId,
				"userId":ID,
				"content":content},
			type: "POST",
			success:function(data){
				//var resultArray=result.split("|");
				//alert("111")
	 			var time=getNowFormatDate();
	 			//if(data=="分配成功"){
	 				$("#"+blogId+"comment").append(
	 			
	 					 "<div><!-- 评论展示 -->"+
	                     "<div class=\"media-left\"><!--  评论者的头像 -->"+
	                         "<a href=\"#\">"+
	                             "<img class=\"media-object img-thumbnail\" src=\"src/user.png\">"+
	                         "</a>"+
	                     "</div>"+
	                     "<div class=\"media-body\">"+
	                         "<span class=\"media-heading\">"+userName+"发表评论:"+"</span>"+
	                         "<span>"+content+"</span>"+
	                     "</div>"+
	                     "<div class=\"media-body\" style=\"float:left;width:100%;\">"+
	                     
	                     "<span>"+"发表于"+time+"</span>"+
	                 "</div>"+
	                 "</div>"
	 			);
	 			//}
				
			},
			error:function(){
				alert("发表评论出错")
			}
		});
	}
		



	
	
}
function QueryComment(blogId) {
			//alert(blogId);
			$("#"+blogId).append(
	    	"<div class=\"commentBlock card\" style=\"background-color: #f2f2f5;display: block;\"><!-- 评论框 -->"+
                    "<div class=\"media-left\"><!--  自己的头像 -->"+
                    "<a href=\"#\">"+
                        "<img class=\"media-object img-thumbnail\" src=\"src/user.png\">"+
                    "</a>"+
                "</div>"+
              "<div class=\"media-body\" id="+blogId+'comment'+">"+
                "<div style=\"padding: 0px 0px 10px 0px;\"><!-- 评论输入框及发布按钮 -->"+
                    "<textarea rows=\"1\" id="+blogId+'comment_publish'+" length=\"140\" node-type=\"textEl\" style=\"margin: 0px; padding: 5px 2px 0px 6px; border-style: solid; border-width: 1px; border-color: #cccccc;font-size: 12px; word-wrap: break-word; line-height: 18px;overflow: hidden; outline: none; height: 23px; width: 100%\" range=\"0&amp;0\">"+"</textarea>"+
                    "<button class=\"btn-xs btn-default\" style=\"background-color:#fa7a38;color:#ffffff\" onclick=\"publishComment("+blogId+");\">评论</button>"+
                "</div>"+
              "</div>"+
            "</div>"
                );
        	$.ajax({
        		url: "query/comment",    //请求的url地址
        	    dataType: "JSON",   //返回格式为json
        	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
        	    data: { "id":blogId},    //参数值
        	    type: "POST",   //请求方式
        	    success: function(data) {
        	    	var commentset=data;
        	    	$.each(commentset,function(i,item){
        	    		//alert(item.content);
        	    		$("#"+blogId+"comment").append(
        	    	 			
       	 					 "<div><!-- 评论展示 -->"+
       	                     "<div class=\"media-left\"><!--  评论者的头像 -->"+
       	                         "<a href=\"#\">"+
       	                             "<img class=\"media-object img-thumbnail\" src=\"src/user.png\">"+
       	                         "</a>"+
       	                     "</div>"+
       	                     "<div class=\"media-body\">"+
       	                         "<span class=\"media-heading\">"+userName+"发表评论:"+"</span>"+
       	                         "<span>"+item.content+"</span>"+
       	                     "</div>"+
       	                     "<div class=\"media-body\" style=\"float:left;width:100%;\">"+
       	                     
       	                     "<span>"+"发表于"+item.time+"</span>"+
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