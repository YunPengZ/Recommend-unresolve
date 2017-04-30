var currentPage = 1;
        var pageNum = 1;
        var pageSize = 6;
        var totalRecord=6;
		 var websocket = null;
		 var blogState=0;//all
		 
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
			 totalRecord = 6 ;
			 $('#home').empty();
        	blogState=1;//hot
        	OnScrollQuery(1);
        }
        function showUserBlog(){
        	 totalRecord = 6 ;
        	 $('#home').empty();
        	blogState=0;
        	OnScrollQuery(1);
        }
        function showRecBlog(){
        	 totalRecord = 6 ;
        	 $('#home').empty();
        	blogState=2;//rec
        	OnScrollQuery(1);
        }	
        function getfollower(){
        	 totalRecord = 6 ;
        	blogState=6;
        	OnScrollQuery(1);
        }
        function getfans(){
        	 totalRecord = 6 ;
        	blogState=7;
        	OnScrollQuery(1);
        }
		 function praiseClick(blogId){
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
  