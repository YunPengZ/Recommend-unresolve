<%@ page import="ahu.edu.cn.JavaBeans.WeiboUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
            <!--如果注册成功，就显示title为 注册成功（后台不知道怎么实现）-->
    	<title>
    	<%-- <%
            out.println(((WeiboUser)session.getAttribute("User")).getUsername()+"个人资料");
            %> --%>
        </title>
        <meta http-equiv="Content-Type" content="text/html">
        <link rel="stylesheet" href="css/bootstrap.css">
 		<script type="text/javascript">
 		var ID='${sessionScope.user.userId}'; 
 		alert(ID)
	          function selectTag(str){
	          	var reg1=new RegExp("^请选择*");
	          	if(reg1.test(document.getElementById("showTag").value)){
	          		document.getElementById("showTag").value='';
	          	}
	              document.getElementById("showTag").value += ' ' + str;
	          }
        </script>
    </head>
    <body style="background-color: #94c5e3;">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12" style="text-align: center;padding: 5%;">
                    <img src="src/logo_big.png">
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-2"><!--留白作对齐用-->
                </div>
                <div class="col-md-8">
                    <div class="row clearfix box">
                        <div class="card" style="padding: 5%">
                            <div class="row clearfix box"><!--上传头像-->
                                <h4>用户头像</h4>
                                <img id="thumbnail" src="src/user-lg.png" height="64">
                                <input type="file" onchange="previewFile()">
                            </div>
                            <div class="row clearfix box"><!--标签选择-->
                                <h4>用户标签</h4>
                                <div class="input-group">
                                    <input id="showTag" type="text" class="form-control">
                                    <div class="input-group-btn">
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">标签<span class="caret"></span></button>
                                        <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                            <li><a href="#" onclick="selectTag(this.innerHTML);return false;">科技</a></li>
                                            <li><a href="#" onclick="selectTag(this.innerHTML);return false;">财经</a></li>
                                            <li><a href="#" onclick="selectTag(this.innerHTML);return false;">社会</a></li>
                                            <li><a href="#" onclick="selectTag(this.innerHTML);return false;">搞笑</a></li>
                                             <li><a href="#" onclick="selectTag(this.innerHTML);return false;">体育</a></li>
                                            <li><a href="#" onclick="selectTag(this.innerHTML);return false;">明星</a></li>
                                            <li><a href="#" onclick="selectTag(this.innerHTML);return false;">影视</a></li>
                                            <li><a href="#" onclick="selectTag(this.innerHTML);return false;">军事</a></li>
                                        
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="row clearfix box"><!--用户简介-->
                                <h4>用户简介</h4>
                                <textarea rows="4" id="user_introduction" maxlength="30" style="width:100%;border:2px groove #cccccc;"></textarea>
                            </div>
                            <div class="row clearfix box">
                                <button class="btn btn-default" onclick="window.location='AfterLogin.jsp'"><strong>跳过</strong></button>
                                <button class="btn btn-default col-xs-offset-10" onclick="save()"style="background-color: #ff8140;color: #ffffff"><strong>完成</strong></button><!-- 原来也并不能跳转，所以不知道怎么跳转到登陆后界面-->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3"><!--留白作对齐用-->
                </div>
            </div>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/message.js"></script>
    </body>
</html>
