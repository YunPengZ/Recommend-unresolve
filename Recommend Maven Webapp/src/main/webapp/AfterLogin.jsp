<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    	<link rel="shortcut icon" type="image/x-icon" href="src/favicon.png">
        <meta charset = "utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/style.css">
        <!--    <link rel="stylesheet" href="css/font-awesome.min.css">-->
		<link rel="stylesheet" title="css1" href="css/s1_1.css">
		<link rel="stylesheet" title="css2" href="css/s1_2.css">
		<link rel="stylesheet" title="css3" href="css/s1_3.css">
        <title>Home</title>
         <script src="js/jquery.min.js"></script>
        <script type="text/javascript">
        
        var currentPage = 1;
        var pageNum = 1;
        var pageSize = 6;
        var totalRecord = 6 ;
		 var websocket = null;
		 var blogState=0;//all
		 var ID='${sessionScope.user.userId}'; 
		 //alert(ID)
		 if(typeof(ID)=="undefined"||ID==""){
			 alert("获取用户信息失败，请重新登录");
			 window.location="BeforeLogin.jsp"
		 }
		 var userName='${sessionScope.user.userName}';
		 //alert("Id"+ID)
		 function bindScrollEvent(){
				// 添加滚动监听事件
						$(window).scroll( function() {
						var docHeight = $(document).height(); // 获取整个页面的高度
						var winHeight = $(window).height(); // 获取当前窗体的高度
						var winScrollHeight = $(window).scrollTop(); // 获取滚动条滚动的距离
						if(docHeight -30 <= winHeight + winScrollHeight){
							currentPage ++;
							OnScrollQuery(currentPage);
							
						}
						
						} );
					}
		 function OnScrollQuery(pageNumber){
			 if(blogState==0)QueryUserBlog(pageNumber);
	        	else if(blogState==1)QueryHotBlog(pageNumber);
	        	else if(blogState==2)QueryRecBlog(pageNumber);
	        	else if(blogState==4)search(pageNumber);
	        	else if(blogState==5)findTopicTo(pageNumber,0);
	        	else if(blogState==6)queryuserfollower(pageNumber,ID);
	        	else if(blogState==7)queryuserfans(pageNumber,ID);
				
		 }
		 $(function (){
	       		bindScrollEvent();
	       		QueryUserBlog(1);
	       		queryTopics();
	       })
	       function showHotBlog(){
			 $('#home').empty();
        	blogState=1;//hot
        	OnScrollQuery(1);
        }
        function showUserBlog(){
        	 $('#home').empty();
        	blogState=0;
        	OnScrollQuery(1);
        }
        function showRecBlog(){
        	 $('#home').empty();
        	blogState=2;//rec
        	OnScrollQuery(1);
        }	
        function getfollower(){
        	blogState=6;
        	OnScrollQuery(1);
        }
        function getfans(){
        	blogState=7;
        	OnScrollQuery(1);
        }
        
		 function praiseClick(blogId){
			 alert(blogId+' '+ID);
			 $.ajax({
				 url:"query/like",
				 datatype:"text",
				 async:true,
				 data:{"blogId":blogId,
					 "userId":ID},
				 type:"POST",
				 success: function(data) { 
					// var resultArray=data.split("|");
					 ///alert(data)
	         			if(data==0){
	         	       		/*
	         	       		设置对应微博点赞状态改变
	         	       		*/
	         	       	//var blogId=resultArray[1];
	        	       		var element=document.getElementById(blogId+"praise");
				        	var praiseElement=element.getElementsByTagName("img")[0];
				        	praiseElement.setAttribute("src","src/Like.png");
				        	var praiseArray=element.innerHTML.split(">");	
				        	var praiseNum=parseInt(praiseArray[1])+1;
				        	element.setAttribute("style","color:#000000");
				        	element.innerHTML=praiseArray[0]+">"+praiseNum;
	         	       		praiseState=true; 
	         			}else praiseState=false;
				 },
				error: function() {
        	        alert("出错");
		       }
			 });
		 }
		  function searchClick(){
	        	blogState=4;//搜索导入的微博状态是4
	        	$("#home").empty();
	        	search(1);
	        }
			function getNowFormatDate() {
		   	    var date = new Date();
		   	    var seperator1 = "-";
		   	    var seperator2 = ":";
		   	    var month = date.getMonth() + 1;
		   	    var strDate = date.getDate();
		   	    if (month >= 1 && month <= 9) {
		   	        month = "0" + month;
		   	    }
		   	    if (strDate >= 0 && strDate <= 9) {
		   	        strDate = "0" + strDate;
		   	    }
		   	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		   	            + " " + date.getHours() + seperator2 + date.getMinutes()
		   	            + seperator2 + date.getSeconds();
		   	    return currentdate;
		   	}
			
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
          alert("");
         var json=JSON.parse(event.data);
         if(json.from == "${sessionScope.user.userId}"){
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
     function send() {
     
     	var str = $("#wchatcontent").val();
     
     	var json={"from":"${sessionScope.user.userId}","to":1,"message":str};
  
     	
     	 websocket.send(JSON.stringify(json)); 
     }
  	function openUserInfoWindow()
   	{
   		location.href="Profile.jsp?word="+ID;//其余功能暂不用
   	}
  	function openCombine(){
  		location.href="combine.jsp?word="+ID;
  	}

        </script>
    </head>
    <body>
    <div class="container-fluid"><!--#f5f8fa #a2cbe7-->
        <div class="row clearfix"><!--导航栏-->
            <div class="col-md-12 column">
                <nav class="navbar navbar-default navbar-fixed-top">
                    <div class="navbar-header">
                        <a class="navbar-brand" href = "#"><img src="src/wb_logo.png" alt="..." style="max-height:25px"></a>
                    </div>
                    <form class="navbar-form navbar-right" role="search">
                        <div class="form-group">
                           <input type="text" value="Search" onfocus="this.value=''" id="search_input" class="form-control" placeholder="Search">
                        </div>
                        <button class="btn btn-info" onclick="searchClick(); required"><i class="fa fa-search"></i>查找</button>
                    </form>
                </nav>
            </div>
        </div>
        <div class="row clearfix" id= "home_page" style="background-color: #94c5e3;height:100%;"><!--主页面-->

            <div class="col-xs-2 blank_bar" id="blank_bar" style="background-color: #b4daf0;position:fixed;height:100%;"><!--留白作对齐用-->
            </div>
			<div class="col-xs-2 blank_bar"style="background-color: #b4daf0;height:100%;"><!--留白作对齐用-->
            </div>
            <div class="col-md-1"><!--左侧边透明栏-->
                <ul class="nav" data-spy="affix" role="tab-list" style="opacity:0.9;margin-top:10px;margin-right:-15%;font-size: 0.5vw;">
                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab" onclick="showRecBlog()">推荐微博</a></li>
                    <li role="presentation"><a href="#home" aria-controls="home" role="tab" data-toggle="tab" onclick="showHotBlog()">全部微博</a></li>
                    <li role="presentation"><a href="#comment" onclick="queryusercomments()"aria-controls="comment" role="tab" data-toggle="tab">评论<span class="badge" id="relate_comment"></span></a></li>
                    <li role="presentation"><a href="#like" onclick="queryuserpraise()"aria-controls="like" role="tab" data-toggle="tab">点赞<span class="badge" id="relate_praise"></span></a></li>
                    <li role="presentation"><a href="#chat" onclick="chat()" aria-controls="chat" role="tab" data-toggle="tab">私信<span class="badge">99</span></a></li>
                    <li><a onclick="openCombine()">修改资料</a></li>
                    <li><a href="logout.jsp">注销用户</a></li><!--注销用户-->
                </ul>
            </div>

            <div class="col-md-5"><!--微博显示页面-->
                <div class="tab-content">
                  <div class="row clearfix box" id="weiboPublish"><!--微博发布框 放入推荐微博框中-->
                            <div class="card">
                                <div class="row clearfix">
                                    <div class="col-md-12" style="font-weight:bold;">
                                        有什么新鲜事想告诉大家?
                                    </div>
                                </div>
                                <form name="sentComment" onsubmit="return IsNull()">
                                    <div class="row clearfix">
                                        <div id="outer" class="col-md-12">
                            	            <p id="show" align="right">你还可以输入<strong style="color:#FF3333;">140</strong>个字</p> 
                                            <textarea name="weibocontent" id="weibocontent" wrap="100"rows="5" onkeyup="fontLeng()" style="width:100%;border:2px groove #cccccc;"></textarea>                            
                                        </div>
                                    </div>
                                    <div class="row clearfix">
                                        <div class="col-md-1" style="margin-bottom:5px;" >
                            	            <button type="sumbit" id="sendBlog"  onbutton="sendBlog()" class="btn btn-default" style="background-color:#fa7a38;color:#ffffff;" >发布</button>                         	
                                        </div> 
                                    </div>
                                </form>
                            </div>
                        </div>
                    <div role="tabpanel" class="tab-pane active" id="home" style="height:100%"><!--推荐微博页面-->
                      
                    </div>
                     <div role="tabpanel" class="tab-pane" id="follower" style="height:100%"><!-- 评论消息 -->
                        
                    </div>
                     <div role="tabpanel" class="tab-pane" id="fans" style="height:100%"><!-- 评论消息 -->
                        
                    </div>
                    <div role="tabpanel" class="tab-pane" id="comment" style="height:100%"><!-- 评论消息 -->
                        
                    </div>

                    <div role="tabpanel" class="tab-pane" id="like"><!-- 点赞消息 -->
                   
                    </div>
                    
                    <div role="tabpanel" class="tab-pane" id="chat" style="padding-top: 2%;"><!-- 私信消息 -->
                        <div class="panel panel-default"><!-- 聊天面板 -->
                            <div class="panel-heading" style="text-align: center;">
                                <p>与 user 对话中</p>
                            </div>
                            <div class="panel-body" style="min-height: 600px;"  id="wchat">
                                <div class="row box"><!--对方消息-->
                                    <div class="col-md-5">
                                        <div class="media-left"><img class="media-object img-thumbnail" src="src/user.png"></div>
                                        <div class="media-body">
                                            <div class="card">
                                                <p>消息内容</p>
                                            </div>
                                        </div>
                                    </div>                              
                                </div>
                                <div class="row box"><!--己方消息-->
                                    <div class="col-md-offset-7 col-md-5">
                                        <div class="media-body">
                                            <div class="card">
                                                <p>消息内容</p>
                                            </div>
                                        </div>
                                        <div class="media-right"><img class="media-object img-thumbnail" src="src/user.png"></div>
                                    </div>                              
                                </div>
                            </div>
                            <div class="panel-heading">
                                <div class="input-group">
                                    <input type="text" class="form-control" aria-label="..." id="wchatcontent">
                                    <div class="input-group-btn">
                                        <button type="button" id="send"  onclick="send()" class="btn btn-default" style="background-color:#fa7a38;color:#ffffff;" >发布</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>  
                </div>
                
             
               
              	<!-- 使用pagination插件生成页码 -->
             	<div id="pagination" class="Pagination"></div>
            </div>

            <div class="col-md-2" style="height:100%;"><!--个人资料页面和热门话题显示页面-->

                <div class="row clearfix box">
                    <div class="card-profile" style="text-align:center;">
                        <div>
                            <img src="src/profile-bg.jpg" style="width:100%">
                        </div>
                        <div style="position:relative;top:50%;transform:translate(0,-30%);min-width:10%;">
                            <img src="src/user.png" height="32" class="img-thumbnail" >
                            <div id="user_name_div"style="margin-top:10px;font-size:16px;"><a href="#"id="user_name"onclick="openUserInfoWindow();return false;">${sessionScope.user.userName}</a></div>
                        </div>
                        <div class="list-group list-group-horizontal" style="height:30%;font-weight:bold;">
                            <a href="#follower" style="max-width:33%;" id="user_follow" onclick="getfollower()"class="list-group-item" aria-controls="follower" role="tab" data-toggle="tab">关注 </a>
                            <a href="#fans"style="max-width:33%;" id="user_fans" onclick="getfans()"class="list-group-item" aria-controls="fans" role="tab" data-toggle="tab">粉丝</a>
                            <a href="#home"style="max-width:29%;"  id="user_blog" class="list-group-item" aria-controls="weibo" role="tab" data-toggle="tab" onclick="showUserBlog()">微博</a>
                        </div>
                    </div>
                </div>
                <div class="row clearfix box"><!--热门微博框-->
         				<div class="card">
         					<ul id="hottopic" style="list-style: none;">
         					</ul>
         				</div>
                </div>

            </div>

            <div class="col-xs-2 blank_bar" style="background-color: #b4daf0;height:100%;">
	             <div class="col-xs-2 blank_bar"  id="blank_bar_2" style="height:800px;background-color: #b4daf0;position:fixed;"><!--留白作对齐用-->
					<div id="row">
						<div class="bs">
							<a class="styleswitch a1" style="CURSOR: pointer" title="黄" rel=css1></a>
							<a class="styleswitch a2" style="CURSOR: pointer" title="绿" rel=css2></a> 
							<a class="styleswitch a3" style="CURSOR: pointer" title="蓝" rel=css3></a>		
						</div>
					</div>				 
	            </div>
            </div>
        </div><!--主页面结束-->

        <div class="row clearfix" style="background-color: #ffffff;">
        @微博推荐系统
        </div>
    </div>
    </div><!--流动容器Ending-->
    <div id="topbtn"><!--回到顶部按钮-->
        <a href="javascript:returnTop()" style="color: #ffffff;text-align: center;"><i class="fa fa-arrow-circle-up fa-2x"></i></a>
    </div>
       
        <script src="js/bootstrap.min.js"></script>
        <script src="js/onScroll.js"></script>
        <script src="js/profileLoad.js"></script><!--设置加载页面时的操作，包括向下加载时的左右边栏加载 -->
        <script src="js/comment.js"></script>
        <script src="js/send.js"></script>
        <script src="js/transfer.js"></script>
        <script src="js/get_fans_follow.js"></script>
        <script src="js/topic_operation.js"> </script>
        <script src="js/search_afterLogin.js"> </script><!-- 导入搜索后的微博 -->
        <script src="js/queryblog_afterLogin.js"></script>
        <!-- <script src="js/jquery-1.2.6.pack.js"></script> 	-->
		<script src="js/chat.js"></script>
        <script src="js/styleswitch.js"></script>
        <!--   <script src="js/afterlogin.js"></script>-->
       
    </body>
    
</html>
