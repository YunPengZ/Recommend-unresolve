
		      //判断当前浏览器是否支持WebSocket
		     if ('WebSocket' in window) {
		         websocket = new WebSocket("ws://localhost:8080/Recommend/websocket");
		     }
		     else {
		         alert('当前浏览器 Not support websocket')
		     }
		 
		     //连接发生错误的回调方法
		     websocket.onerror = function () {
		       
		     };
		 
		     //连接成功建立的回调方法
		     websocket.onopen = function () {
		        
		     }
		 
		     //接收到消息的回调方法
		     websocket.onmessage = function (event) {
		          ///alert("");
		         var json=JSON.parse(event.data);
		         switch(json.kind){
		         case "聊天":
		        	 if(json.from == ID){
				         	$("#wchat").append
				         	( 
				         	"<div class=\"row box\">"+
				                "<div class=\"col-md-offset-7 col-md-5\">"+
				                       "<div class=\"media-body\">"+
				                              "<div class=\"card\">"+
				                                     "<p>"+json.message+"</p>"+
				                              "</div>"+
				                       "</div>"+
				                 "<div class=\"media-right\"><img class=\"media-object img-thumbnail\" src=\"src/user.png\"></div>"+
				                 "</div>"+                              
				             " </div>"
				         	);
				         }else{
				         $("#wchat").append
				         	( 
				            "<div class=\"row box\">"+
				                "<div class=\"col-md-5\">"+
				                      "<div class=\"media-left\"><img class=\"media-object img-thumbnail\" src=\"src/user.png\"></div>"+
				                      "<div class=\"media-body\">"+
				                            "<div class=\"card\">"+
				                                  "<p>"+json.message+"</p>"+
				                             "</div>"+
				                     " </div>"+
				                "</div>"+                             
				           "</div>"
				         	);
				         }
		        	 break;
		         case "评论":
		        	 alert(json.message)
		        	 break;
		         case "点赞":
		        	 break;
		         }
		         
		     }
		 
		     //连接关闭的回调方法
		     websocket.onclose = function () {
		        
		     }
		 
		     //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
		     window.onbeforeunload = function () {
		        
		     }
		 
		     //将消息显示在网页上
		     function setMessageInnerHTML(innerHTML) {
		        
		     }
		 
		     //关闭WebSocket连接
		     function closeWebSocket() {
		        
		    }
		 
		     //发送消息
		     function send(userId) {
		     
		     	var str = $("#wchatcontent").val();
		     
		     	var json={"from":ID,"to":userId,"message":str,"kind":"聊天"};
		  
		     	
		     	 websocket.send(JSON.stringify(json)); 
		     }
		     function chatwith(chatNeeded){
		    	// alert(chatNeeded)
			    	var user=chatNeeded.split(",");
			    	if(user[1]>1000000){
			    		alert("非本站用户，暂不提供聊天功能")
			    		return ;
			    	}
			    	$('#home').empty();
			    	$("#chat").empty();
			    	$("#chat").append(
			    			 "<div class=\"panel panel-default\"><!-- 聊天面板 -->"+
			                 "<div class=\"panel-heading\" style=\"text-align: center;\">"+
			                     "<p>与 "+user[0]+" 对话中</p>"+
			                " </div>"+
			                 "<div class=\"panel-body\" style=\"min-height: 600px;\"  id=\"wchat\">"+
			                    " <div class=\"row box\"><!--对方消息-->"+
			                         "<div class=\"col-md-5\">"+
			                           "  <div class=\"media-left\"><img class=\"media-object img-thumbnail\" src=\"src/user.png\">"+
			                            " </div>"+
			                             "<div class=\"media-body\">"+
			                              "   <div class=\"card\">"+
			                                  "   <p>消息内容</p>"+
			                                " </div>"+
			                            " </div>"+
			                        " </div>"+                              
			                     "</div>"+
			                     "<div class=\"row box\"><!--己方消息-->"+
			                        " <div class=\"col-md-offset-7 col-md-5\">"+
			                           "  <div class=\"media-body\">"+
			                               "  <div class=\"card\">"+
			                                   "  <p>消息内容</p>"+
			                               "  </div>"+
			                             "</div>"+
			                             "<div class=\"media-right\"><img class=\"media-object img-thumbnail\" src=\"src/user.png\">"+
			                            " </div>"+
			                        " </div>  "+                            
			                    " </div>"+
			                " </div>"+
			                " <div class=\"panel-heading\">"+
			                     "<div class=\"input-group\">"+
			                        " <input type=\"text\" class=\"form-control\" aria-label=\"...\" id=\"wchatcontent\">"+
			                         "<div class=\"input-group-btn\">"+
			                            " <button type=\"button\" id=\"send\"  onclick=\"send("+user[1]+");\" class=\"btn btn-default\" style=\"background-color:#fa7a38;color:#ffffff;\" >"+
			                             "	发布"+
			                             	"</button>"+
			                        " </div>"+
			                    " </div>"+
			                 "</div>"+
			             "</div>"
			    	);
			    	$('#chat').attr("class","tab-pane active");
			    	//机关算尽太聪明，反误了卿卿性命
			    }
		     function appendChat(item){
		 		$(function () {
		 			var chatNeeded=item.blogAuthor.userName+","+item.blogAuthor.userId;
		 			//alert(chatNeeded)
		          	$('[data-toggle=\"popover'+item.blogId+'\"]').popover({delay: {"show": 10, "hide": 1000}, placement: "left",trigger:"hover",content:"hhhhh",template:
		           	     "<div class=\"box\" style=\"width: 300px; height: 150px\">"+
		           	       "<div class=\"card-profile\" style=\"text-align:center\">"+
		           	                "<div>"+"<img src=\"src/profile-bg.jpg\" style=\"width:100%\">"+ "</div>"+
		           	                "<div style=\"position:relative;top:20px;transform:translate(0,-50%);min-width:150px\">"+
		           	                    "<img src=\"src/user.png\" height=\"32\" class=\"img-thumbnail\" >"+
		           	                    "<div style=\"font-size:80%;\">"+
		           	                        "<a href=\"#\" onclick=\"javascript:location.href=\'Profile.jsp?word="+item.blogAuthor.userId+"'\";return false;\"style=\"color: #000000\">"+item.blogAuthor.userName+"</a>"+ 
		           	                             "<button class=\"btn-xs btn-primary\" onclick=\"chatwith(\'"+chatNeeded+"\');return false;\">"+"私聊"+"</button>"+
		           	                     "</div>"+
		           	                "</div>"+
		           	                "<div class=\"list-group list-group-horizontal\" style=\"height:30px;font-weight:bold\">"+
		           	                    "<a href=\"#\" class=\"list-group-item\">"+ "关注"+item.blogAuthor.userFollowNum+ "</a>"+
		           	                    "<span>"+"|"+"</span>"+
		           	                    "<a href=\"#\" class=\"list-group-item\">"+"粉丝"+item.blogAuthor.userFansNum+"</a>"+
		           	                    "<span>"+"|"+"</span>"+
		           	                    "<a href=\"#\" class=\"list-group-item\">"+"微博"+item.blogAuthor.userBlogNum+"</a>"+
		           	               "</div>"+ 
		           	            "</div>"+
		           	        "</div>"+
		           	     "</div>"
		           	        })
		  	})
		 	}
		 	