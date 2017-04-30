       var currentPage = 1;
       var pageNum = 1;
       var pageSize = 6;
       var totalRecord = 10 ;
       $(function (){
       		bindScrollEvent();
       		QueryBlog(1);
       		queryTopics();
       })
        function QueryBlog(pageNumber) {
            if(pageNumber  * pageSize > totalRecord){
               alert("没有更多微博！");
               $(window).unbind("scroll");
               return ;
            }
        	$.ajax({
        		url: "query/allblog",    //请求的url地址
        	    dataType: "JSON",   //返回格式为json
        	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
        	    data: {"page_num": pageNumber},    //参数值
        	    type: "POST",   //请求方式
        	    success: function(data) {
        	        var totalRecord = data.totalRecord;
        	    	var blogset=data.dataList;
        	    	$.each(blogset,function(i,item){
        	    		//检查是否点赞
        	    		/*if(item.praiseState=="false"){
        	    			imgSrc="src/Praise.png";//未点赞
        	    			praiseColor="color:#03a9f4";
        	    		}
         	    		else{
         	    			imgSrc="src/Like.png";//点赞后的处理
         	    			praiseColor="color:#e65260";
         	    		}*/
        	    		imgSrc="src/Praise.png";//未点赞
        	    		praiseColor="color:#03a9f4";
        	    		if(item.blogParent!=null){//转发后的微博设置未true
        	    			$("#home").append(
     	                           " <div class=\"card\"><!-- 转发微博的结构 -->"+
                                    " <div class=\"media-left\"><!-- 自己的头像 -->"+
                                         "<a href=\"#\">"+
                                            " <img class=\"media-object img-thumbnail\" src=\"src/user.png\" alt=\"...\">"+
                                        " </a>"+
                                     "</div>"+
                                     "<div class=\"media-body\">"+
                                       "  <h5 class=\"media-heading\"><a href=\"Profile.jsp?word="+item.blogAuthor.userId+"\" onclick=\"javascript:window.open(\"Profile.jsp\");return false;\">"+item.blogAuthor.userName+"</a></h5>"+
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
     	    		}else
     	    		$("#home").append(
    	    				 "<div class=\"row clearfix box\">"+
    	    				 " <div class=\"card\">"+
                                 "<div class=\"media-left\">"+
                                     "<a href=\"#\">"+
                                         "<img class=\"media-object img-thumbnail\" src=\"src/user.png\" alt=\"...\">"+
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
        	    	});
        	    
        	    },
        	    error: function() {
        	        alert("出错");
        	    }
        		
        	});
		}
       
       
        var flag_username=true;
        var flag_password=true;
        function usernameIsExist(str){
      	
        	if(str==""){
        
        		document.getElementById("username").innerHTML="请输入用户名！";
        	}else{
        	    $.ajax({
        	    	
        	    	data :{"userName":str},
        	    	dataType:"text",
        	    	type:"POST",
        	    	url :"login/check/username",
        	    	success: function(data) {
        	    	    if(data == 0){
        	    	   		 $("#username").html("");
        	    	    }else{
  							 $("#username").html("用户名不存在！");   	    
        	    	    }
        	    	   },
        	    		error: function() {
       	        	       alert("出错");
       	    	        }
        	        });
        	     
        	        
        	}
        }
        
        
  		function login() {
			if(log.password.value==""){
				document.getElementById("password").innerHTML="请输入密码！";
				log.password.focus();
				flag_password=false;
			}
			if(log.username.value==""){
				document.getElementById("username").innerHTML="请输入用户名！";
				log.username.focus();
				flag_username=false;
			}
			if(flag_username&&flag_password){
        	     $.ajax({
        	    	
        	    	data :{"userName":log.username.value,
        	    	       "userPassword":log.password.value
        	    	       },
        	    	dataType:"text",
        	    	type:"POST",
        	    	url :"login/log",
        	    	success: function(data) {
        	    	    if(data == 1){
        	    	   		 $("#password").html("");
        	    	   		 window.location="AfterLogin.jsp";
        	    	    }else{
  							 $("#password").html("用户名或者密码错误！");   	    
        	    	    }
        	    	   },
        	    		error: function() {
       	        	       alert("出错");
       	    	        }
        	        });
			}else{
				return ;
			}
		}


		function bindScrollEvent(){
	// 添加滚动监听事件
			$(window).scroll( function() {
			var docHeight = $(document).height(); // 获取整个页面的高度
			var winHeight = $(window).height(); // 获取当前窗体的高度
			var winScrollHeight = $(window).scrollTop(); // 获取滚动条滚动的距离
			if(docHeight -30 <= winHeight + winScrollHeight){
				currentPage ++;
				QueryBlog(currentPage);
				
			}
			
			} );
		}