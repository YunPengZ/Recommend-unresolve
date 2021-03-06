 function QueryHotBlog(pageNumber){
	// $("#home").empty();
	 if((pageNumber-1)  * pageSize > totalRecord){
        // alert("没有更多微博！+totalRecord"+totalRecord);
        // $(window).unbind("scroll");
         return ;
      }
  	$.ajax({
  		url: "query/hotblog",    //请求的url地址
  	    dataType: "JSON",   //返回格式为json
  	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
  	    data: {"page_num":pageNumber,
  	    	"User_Id":ID},    //参数值
  	    type: "POST",   //请求方式
  	    success: function(data) { 
  	        totalRecord = data.totalRecord;
  	      if(totalRecord<=0){
	        	alert("本站微博已清空")
	        	return ;
	        }
  	    	var blogset=data.dataList;
        	    	//var praiseColor="color:#03a9f4";
        	    	$.each(blogset,function(i,item){
        	    		//检查是否点赞
        	    		if(item.userLike=="true"){//item.UserLike undefine?
        	    			imgSrc="src/Like.png";//点赞后的处理
         	    			praiseColor="color:#e65260";
        	    		}
         	    		else{
         	    			imgSrc="src/Praise.png";//未点赞
        	    			praiseColor="color:#03a9f4";
         	    		}
        	    		if(item.blogParent!=null){//转发后的微博设置未true
        	    			$("#home").append(
     	                           " <div class=\"card\"><!-- 转发微博的结构 -->"+
                                    " <div class=\"media-left\"><!-- 自己的头像 -->"+
                                         "<a href=\"#\">"+
                                         "<img  data-container=\"body\"  data-toggle=\"popover"+item.blogId+"\" class=\"media-object img-thumbnail\" src=\"src/user.png\" alt=\"...\">"+
                                        " </a>"+
                                     "</div>"+
                                     "<div class=\"media-body\">"+
                                       "  <h5 class=\"media-heading\"><a href=\"Profile.jsp?word="+item.blogAuthor.userId+"\" id="+item.blogId+'author'+" onclick=\"javascript:window.open(\"Profile.jsp\");return false;\">"+item.blogAuthor.userName+"</a></h5>"+
                                        " <h6 class=\"media-heading-time\">"+item.blogTime+"</h6>"+
                                        "<p id="+item.blogId+'content'+">"+item.blogContent+"</p><!-- 转发时发的微博 -->"+
                                         "<div class=\"media-body\" style=\"padding: 5px;background-color: #f2f2f5\";><!-- 被转发的微博 -->"+
                                           "  <h5 class=\"media-heading\">"+item.blogParent.blogAuthor.userName+"</h5><!-- 参考新浪微博，没有被转发微博的博主头像 -->"+
                                            " <h6 class=\"media-heading-time\">"+item.blogParent.blogTime+"</h6>"+
                                             "<p>"+item.blogParent.blogContent+"</p>"+
                                             "<div class=\"list-group list-group-horizontal\" style=\"text-align: right;\">"+
                                             "<a href=\"#\" class=\"list-group-item\" style=\"color:#1abc9c\"><img src=\"src/Retweet.png\">"+item.blogParent.blogTransferNum+"</a>"+
                                             "<a href=\"#\" name=\"commentBtn\" class=\"list-group-item\" style=\"color:#03a9f4\"><img src=\"src/Reply.png\">"+item.blogParent.blogCommentNum+"</a>"+
                                             "<a href=\"#\" class=\"list-group-item\" style=\"color:#03a9f4\"><img src=\"src/Praise.png\">"+item.blogParent.blogLikeNum+"</a>"+
                                        " </div>"+
                                        " </div>"+
                                 
                                        "<div class=\"list-group list-group-horizontal\"  style=\"padding-left:5px;\">"+
                                            "<a href=\"#\" class=\"list-group-item\" id="+item.blogId+'commentNum'+" style=\"color:#1abc9c\"  onclick=\"CommentClick("+item.blogId+");return false;\"><img src=\"src/Retweet.png\">"+item.blogCommentNum+"</a>"+
                                            "<a href=\"#\" class=\"list-group-item\" style=\"color:#03a9f4\"><img src=\"src/Reply.png\" onclick=\"TransferClick("+item.blogId+");return false;\">"+item.blogTransferNum+"</a>"+
                                       //mySmallModalLabel  data-toggle=\"modal\" href=\"#mySmallModalLabel\" 
                                            "<a href=\"#\" class=\"list-group-item\" id="+item.blogId+'praise'+"  onclick=\"praiseClick("+item.blogId+");return false;\" style="+praiseColor+"\"><img src="+imgSrc+">"+item.blogLikeNum+"</a>"+
                                        "</div>"+
                                        
                                    " </div>"+
                                    "<div class=\"list-group list-group-horizontal\"  style=\"padding-left:5px;\"id="+item.blogId+" >"+
                                    "</div>"+
                                " </div>"
     	    			);
        	    			appendChat(item);
     	    		}else
     	    		$("#home").append(
    	    				 "<div class=\"row clearfix box\">"+
    	    				 " <div class=\"card\">"+
                                 "<div class=\"media-left\">"+
                                     "<a href=\"#\">"+
                                     "<img  data-container=\"body\"  data-toggle=\"popover"+item.blogId+"\" class=\"media-object img-thumbnail\" src=\"src/user.png\" alt=\"...\">"+
                                     "</a>"+
                                 "</div>"+
                                 "<div class=\"media-body\">"+
                                 "  <h5 class=\"media-heading\"><a href=\"Profile.jsp?word="+item.blogAuthor.userId+"\" id="+item.blogId+'author'+" onclick=\"javascript:window.open(\"Profile.jsp\");return false;\">"+item.blogAuthor.userName+"</a></h5>"+
                                     "<h6 class=\"media-heading-time\">"+item.blogTime+"</h6>"+
                                     "<p id="+item.blogId+'content'+">"+item.blogContent+"</p>"+
                                     "<div class=\"list-group list-group-horizontal\"  style=\"padding-left:5px;\">"+
                                         "<a href=\"#\" class=\"list-group-item\" id="+item.blogId+'commentNum'+" style=\"color:#1abc9c\"  onclick=\"CommentClick("+item.blogId+");return false;\"><img src=\"src/Retweet.png\">"+item.blogCommentNum+"</a>"+
                                         "<a href=\"#\"  class=\"list-group-item\" style=\"color:#03a9f4\"><img src=\"src/Reply.png\" onclick=\"TransferClick("+item.blogId+");return false;\">"+item.blogTransferNum+"</a>"+
                                         "<a href=\"#\" class=\"list-group-item\" id="+item.blogId+'praise'+"  onclick=\"praiseClick("+item.blogId+");return false;\" style="+praiseColor+"\"><img src="+imgSrc+">"+item.blogLikeNum+"</a>"+
                                     "</div>"+
                                     "<div class=\"list-group list-group-horizontal\"  style=\"padding-left:5px;\"id="+item.blogId+" >"+
                                     "</div>"+
                                 "</div>"+
                             "</div>"+
                         "</div>"
    	                  );
        	    		appendChat(item);
        	    	});
        	    
        	    },
        	    error: function() {
        	        alert("出错");
        	    }
        		
        	});
		}
	 function QueryRecBlog(pageNumber){
		// $("#home").empty();
		 if((pageNumber-1)   * pageSize > totalRecord){
	       //  alert("没有更多微博！");
	       //  $(window).unbind("scroll");
	         return ;
	      }
	  	$.ajax({
	  		url: "query/recblog",    //请求的url地址
	  	    dataType: "JSON",   //返回格式为json
	  	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
	  	    data: {"page_num":pageNumber,
	  	    	"User_Id":ID},    //参数值
	  	    type: "POST",   //请求方式
	  	    success: function(data) { 
	  	        totalRecord = data.totalRecord;
	  	        if(totalRecord<=0){
	  	        	alert("无推荐结果")
	  	        	return ;
	  	        }
	  	    	var blogset=data.dataList;
	        	    	//var praiseColor="color:#03a9f4";
	        	    	$.each(blogset,function(i,item){
	        	    		//检查是否点赞
	        	    		if(item.userLike=="true"){//item.UserLike undefine?
	        	    			imgSrc="src/Like.png";//点赞后的处理
	         	    			praiseColor="color:#e65260";
	        	    		}
	         	    		else{
	         	    			imgSrc="src/Praise.png";//未点赞
	        	    			praiseColor="color:#03a9f4";
	         	    		}
	        	    		if(item.blogParent!=null){//转发后的微博设置未true
	        	    			$("#home").append(
	     	                           " <div class=\"card\"><!-- 转发微博的结构 -->"+
	                                    " <div class=\"media-left\"><!-- 自己的头像 -->"+
	                                         "<a href=\"#\">"+
	                                         "<img  data-container=\"body\"  data-toggle=\"popover"+item.blogId+"\" class=\"media-object img-thumbnail\" src=\"src/user.png\" alt=\"...\">"+
	                                        " </a>"+
	                                     "</div>"+
	                                     "<div class=\"media-body\">"+
	                                       "  <h5 class=\"media-heading\"><a href=\"Profile.jsp?word="+item.blogAuthor.userId+"\" id="+item.blogId+'author'+" onclick=\"javascript:window.open(\"Profile.jsp\");return false;\">"+item.blogAuthor.userName+"</a></h5>"+
	                                        " <h6 class=\"media-heading-time\">"+item.blogTime+"</h6>"+
	                                        "<p id="+item.blogId+'content'+">"+item.blogContent+"</p><!-- 转发时发的微博 -->"+
	                                         "<div class=\"media-body\" style=\"padding: 5px;background-color: #f2f2f5\";><!-- 被转发的微博 -->"+
	                                           "  <h5 class=\"media-heading\">"+item.blogParent.blogAuthor.userName+"</h5><!-- 参考新浪微博，没有被转发微博的博主头像 -->"+
	                                            " <h6 class=\"media-heading-time\">"+item.blogParent.blogTime+"</h6>"+
	                                             "<p>"+item.blogParent.blogContent+"</p>"+
	                                             "<div class=\"list-group list-group-horizontal\" style=\"text-align: right;\">"+
	                                             "<a href=\"#\" class=\"list-group-item\" style=\"color:#1abc9c\"><img src=\"src/Retweet.png\">"+item.blogParent.blogTransferNum+"</a>"+
	                                             "<a href=\"#\" name=\"commentBtn\" class=\"list-group-item\" style=\"color:#03a9f4\"><img src=\"src/Reply.png\">"+item.blogParent.blogCommentNum+"</a>"+
	                                             "<a href=\"#\" class=\"list-group-item\" style=\"color:#03a9f4\"><img src=\"src/Praise.png\">"+item.blogParent.blogLikeNum+"</a>"+
	                                        " </div>"+
	                                        " </div>"+
	                                 
	                                        "<div class=\"list-group list-group-horizontal\"  style=\"padding-left:5px;\">"+
	                                            "<a href=\"#\" class=\"list-group-item\" id="+item.blogId+'commentNum'+" style=\"color:#1abc9c\"  onclick=\"CommentClick("+item.blogId+");return false;\"><img src=\"src/Retweet.png\">"+item.blogCommentNum+"</a>"+
	                                            "<a href=\"#\" class=\"list-group-item\" style=\"color:#03a9f4\"><img src=\"src/Reply.png\" onclick=\"TransferClick("+item.blogId+");return false;\">"+item.blogTransferNum+"</a>"+
	                                       //mySmallModalLabel  data-toggle=\"modal\" href=\"#mySmallModalLabel\" 
	                                            "<a href=\"#\" class=\"list-group-item\" id="+item.blogId+'praise'+"  onclick=\"praiseClick("+item.blogId+");return false;\" style="+praiseColor+"\"><img src="+imgSrc+">"+item.blogLikeNum+"</a>"+
	                                        "</div>"+
	                                        
	                                    " </div>"+
	                                    "<div class=\"list-group list-group-horizontal\"  style=\"padding-left:5px;\"id="+item.blogId+" >"+
	                                    "</div>"+
	                                " </div>"
	     	    			);
	        	    			appendChat(item);
	     	    		}else
	     	    		$("#home").append(
	    	    				 "<div class=\"row clearfix box\">"+
	    	    				 " <div class=\"card\">"+
	                                 "<div class=\"media-left\">"+
	                                     "<a href=\"#\">"+
	                                     "<img  data-container=\"body\"  data-toggle=\"popover"+item.blogId+"\" class=\"media-object img-thumbnail\" src=\"src/user.png\" alt=\"...\">"+
	                                     "</a>"+
	                                 "</div>"+
	                                 "<div class=\"media-body\">"+
	                                 "  <h5 class=\"media-heading\"><a href=\"Profile.jsp?word="+item.blogAuthor.userId+"\" id="+item.blogId+'author'+" onclick=\"javascript:window.open(\"Profile.jsp\");return false;\">"+item.blogAuthor.userName+"</a></h5>"+
	                                     "<h6 class=\"media-heading-time\">"+item.blogTime+"</h6>"+
	                                     "<p id="+item.blogId+'content'+">"+item.blogContent+"</p>"+
	                                     "<div class=\"list-group list-group-horizontal\"  style=\"padding-left:5px;\">"+
	                                         "<a href=\"#\" class=\"list-group-item\" id="+item.blogId+'commentNum'+" style=\"color:#1abc9c\"  onclick=\"CommentClick("+item.blogId+");return false;\"><img src=\"src/Retweet.png\">"+item.blogCommentNum+"</a>"+
	                                         "<a href=\"#\"  class=\"list-group-item\" style=\"color:#03a9f4\"><img src=\"src/Reply.png\" onclick=\"TransferClick("+item.blogId+");return false;\">"+item.blogTransferNum+"</a>"+
	                                         "<a href=\"#\" class=\"list-group-item\" id="+item.blogId+'praise'+"  onclick=\"praiseClick("+item.blogId+");return false;\" style="+praiseColor+"\"><img src="+imgSrc+">"+item.blogLikeNum+"</a>"+
	                                     "</div>"+
	                                     "<div class=\"list-group list-group-horizontal\"  style=\"padding-left:5px;\"id="+item.blogId+" >"+
	                                     "</div>"+
	                                 "</div>"+
	                             "</div>"+
	                         "</div>"
	    	                  );
	        	    		appendChat(item);
	        	    	});
	        	    
	        	    },
	        	    error: function() {
	        	        alert("出错");
	        	    }
	        		
	        	});
			}
	 function QueryUserBlog(pageNumber) {
		 //$("#home").empty();
		 //alert("what is wrong with this system")
         if((pageNumber-1)  * pageSize > totalRecord){
           // alert("没有更多微博！");
           // $(window).unbind("scroll");
            return ;
         }
         else $.ajax({
     		url: "query/userblog",    //请求的url地址
     	    dataType: "JSON",   //返回格式为json
     	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
     	    data: {"page_num":pageNumber,
     	    	"User_Id":ID},    //参数值
     	    type: "POST",   //请求方式
     	    success: function(data) {
     	    	//alert(totalRecord)
     	        totalRecord = data.totalRecord;
     	       if(totalRecord<=0){
	  	        	alert("尚未发布微博")
	  	        	return ;
	  	        }
     	    	var blogset=data.dataList;
     	    	$.each(blogset,function(i,item){
     	    		//alert(item.blogAuthor.userId)
    	    		//检查是否点
     	    		//alert(item.userLike)
    	    		if(item.userLike=="true"){//item.UserLike undefine?
    	    			imgSrc="src/Like.png";//点赞后的处理
     	    			praiseColor="color:#e65260";
    	    		}
     	    		else{
     	    			imgSrc="src/Praise.png";//未点赞
    	    			praiseColor="color:#03a9f4";
     	    		}
    	    		if(item.blogParent!=null){//转发后的微博设置未true
    	    			$("#home").append(
 	                           " <div class=\"card\"><!-- 转发微博的结构 -->"+
                                " <div class=\"media-left\"><!-- 自己的头像 -->"+
                                     "<a href=\"#\">"+ 
                                     "<img  data-container=\"body\"  data-toggle=\"popover"+item.blogId+"\" class=\"media-object img-thumbnail\" src=\"src/user.png\" alt=\"...\">"+
                                    " </a>"+
                                 "</div>"+
                                 "<div class=\"media-body\">"+
                                   "  <h5 class=\"media-heading\"><a href=\"Profile.jsp?word="+item.blogAuthor.userId+"\" id="+item.blogId+'author'+" onclick=\"javascript:window.open(\"Profile.jsp\");return false;\">"+item.blogAuthor.userName+"</a></h5>"+
                                    " <h6 class=\"media-heading-time\">"+item.blogTime+"</h6>"+
                                    "<p id="+item.blogId+'content'+">"+item.blogContent+"</p><!-- 转发时发的微博 -->"+
                                     "<div class=\"media-body\" style=\"padding: 5px;background-color: #f2f2f5\";><!-- 被转发的微博 -->"+
                                       "  <h5 class=\"media-heading\">"+item.blogParent.blogAuthor.userName+"</h5><!-- 参考新浪微博，没有被转发微博的博主头像 -->"+
                                        " <h6 class=\"media-heading-time\">"+item.blogParent.blogTime+"</h6>"+
                                         "<p>"+item.blogParent.blogContent+"</p>"+
                                         "<div class=\"list-group list-group-horizontal\" style=\"text-align: right;\">"+
                                         "<a href=\"#\" class=\"list-group-item\" style=\"color:#1abc9c\"><img src=\"src/Retweet.png\">"+item.blogParent.blogTransferNum+"</a>"+
                                         "<a href=\"#\" name=\"commentBtn\" class=\"list-group-item\" style=\"color:#03a9f4\"><img src=\"src/Reply.png\">"+item.blogParent.blogCommentNum+"</a>"+
                                         "<a href=\"#\" class=\"list-group-item\" style=\"color:#03a9f4\"><img src=\"src/Praise.png\">"+item.blogParent.blogLikeNum+"</a>"+
                                    " </div>"+
                                    " </div>"+
                             
                                    "<div class=\"list-group list-group-horizontal\"  style=\"padding-left:5px;\">"+
                                        "<a href=\"#\" class=\"list-group-item\" id="+item.blogId+'commentNum'+" style=\"color:#1abc9c\"  onclick=\"CommentClick("+item.blogId+");return false;\"><img src=\"src/Retweet.png\">"+item.blogCommentNum+"</a>"+
                                        "<a href=\"#\" class=\"list-group-item\" style=\"color:#03a9f4\"><img src=\"src/Reply.png\" onclick=\"TransferClick("+item.blogId+");return false;\">"+item.blogTransferNum+"</a>"+
                                   //mySmallModalLabel  data-toggle=\"modal\" href=\"#mySmallModalLabel\" 
                                        "<a href=\"#\" class=\"list-group-item\" id="+item.blogId+'praise'+"  onclick=\"praiseClick("+item.blogId+");return false;\" style="+praiseColor+"\"><img src="+imgSrc+">"+item.blogLikeNum+"</a>"+
                                    "</div>"+
                                    
                                " </div>"+
                                "<div class=\"list-group list-group-horizontal\"  style=\"padding-left:5px;\"id="+item.blogId+" >"+
                                "</div>"+
                            " </div>"
 	    			);
    	    			appendChat(item);
 	    		}else
 	    		$("#home").append(
	    				 "<div class=\"row clearfix box\">"+
	    				 " <div class=\"card\">"+
                             "<div class=\"media-left\">"+
                                 "<a href=\"#\">"+
                                     "<img  data-container=\"body\"  data-toggle=\"popover"+item.blogId+"\" class=\"media-object img-thumbnail\" src=\"src/user.png\" alt=\"...\">"+
                                 "</a>"+
                             "</div>"+
                             "<div class=\"media-body\">"+
                             "  <h5 class=\"media-heading\"><a href=\"Profile.jsp?word="+item.blogAuthor.userId+"\" id="+item.blogId+'author'+" onclick=\"javascript:window.open(\"Profile.jsp\");return false;\">"+item.blogAuthor.userName+"</a></h5>"+
                                 "<h6 class=\"media-heading-time\">"+item.blogTime+"</h6>"+
                                 "<p id="+item.blogId+'content'+">"+item.blogContent+"</p>"+
                                 "<div class=\"list-group list-group-horizontal\"  style=\"padding-left:5px;\">"+
                                     "<a href=\"#\" class=\"list-group-item\" id="+item.blogId+'commentNum'+" style=\"color:#1abc9c\"  onclick=\"CommentClick("+item.blogId+");return false;\"><img src=\"src/Retweet.png\">"+item.blogCommentNum+"</a>"+
                                     "<a href=\"#\"  class=\"list-group-item\" style=\"color:#03a9f4\"><img src=\"src/Reply.png\" onclick=\"TransferClick("+item.blogId+");return false;\">"+item.blogTransferNum+"</a>"+
                                     "<a href=\"#\" class=\"list-group-item\" id="+item.blogId+'praise'+"  onclick=\"praiseClick("+item.blogId+");return false;\" style="+praiseColor+"\"><img src="+imgSrc+">"+item.blogLikeNum+"</a>"+
                                 "</div>"+
                                 "<div class=\"list-group list-group-horizontal\"  style=\"padding-left:5px;\"id="+item.blogId+" >"+
                                 "</div>"+
                             "</div>"+
                         "</div>"+
                     "</div>"
	                  );
    	    		appendChat(item);
    	    	});
     	    	
    	    },
    	    error: function() {
    	        alert("出错");
    	    }
    	   
    	});
	}
	