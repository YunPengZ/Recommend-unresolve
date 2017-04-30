<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
    <head>
        <meta charset = "utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
		<link rel="stylesheet" title="css1" href="css/s1_1.css">
		<link rel="stylesheet" title="css2" href="css/s1_2.css">
		<link rel="stylesheet" title="css3" href="css/s1_3.css">
        <title>微博推荐系统</title>
    </head>
    <body>
    <div class="container-fluid">
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
                        <input type="button" class="btn btn-default" onclick="searchClick();" value="查找" />
                        
                        
                    </form>
                </nav>
            </div>
        </div>
        <div class="row clearfix" id= "home_page" style="background-color: #94c5e3;"><!--主页面-->
            <div class="col-xs-2 blank_bar" style="background-color: #b4daf0;padding-bottom:60%;"><!--留白作对齐用-->
            </div>
            <div class="col-md-1"><!--左侧边透明栏-->
                <ul class="nav" style="opacity: 0.7;margin-top: 10px;margin-right: 0px;text-align: center">
                    <li><a href="#" onclick="recClick('推荐')">推荐</a></li>
                    <li><a href="#" onclick="recClick('搞笑')">搞笑</a></li>
                    <li><a href="#" onclick="recClick('情感')">情感</a></li>
                    <li><a href="#" onclick="recClick('社会')">社会</a></li>
                    <li><a href="#" onclick="recClick('明星')">明星</a></li>
                </ul>
            </div>
            <div class="col-md-5" ><!--微博显示页面--> 
            	<div id="home"></div>
            	 <!-- 使用pagination插件生成页码 -->
         	   <div id="pagination" class="pagination"></div>
            </div>
           
            <div class="col-md-2"><!--登陆页面和热门话题显示页面-->
                <div class="row clearfix box">
                    <div class="card">
                        <form name="log" class="form-horizontal" role="form">
                            <div class="form-group">
                                <label  for="inputEmail3" class="col-sm-3 control-label">账号</label>
                                <div class="col-sm-8">
                                    <input name="username" type="text" class="form-control" onblur="usernameIsExist(this.value)" />
                                </div>
                                <div id="username" class="col-md-offset-2 col-md-10" style="font-size: 80%;color: #e64141">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-3 control-label">密码</label>
                                <div class="col-sm-8">
                                    <input name="password" type="password" class="form-control" />
                                </div>
                                 <div id="password" class="col-md-offset-2 col-md-10" style="font-size: 80%;color: #e64141">
                                 </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <div class="checkbox">
                                        <label><input type="checkbox" />记住我</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group" style="margin-bottom: 0px">
                                <div class="col-sm-offset-3 col-sm-4">
                                    <button type="button" onclick="login()" class="btn btn-default">登录</button>
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#signUp" style="background-color:#fa7a38;color:#ffffff"><a href="register.jsp" style="text-decoration:none;color:#ffffff">注册</a></button>
                                </div>
                                <div id="login"></div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row clearfix box"><!--热门微博框-->
                    <div class="card">
                        <ul id="hottopic">
                          
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-xs-2 blank_bar" style="background-color: #b4daf0;padding-bottom:60%;">
                <div id="row">
						<div class="bs">
							<a class="styleswitch a1" style="CURSOR: pointer" title="黄" rel=css1></a>
							<a class="styleswitch a2" style="CURSOR: pointer" title="绿" rel=css2></a> 
							<a class="styleswitch a3" style="CURSOR: pointer" title="蓝" rel=css3></a>		
						</div>
					</div>
            </div>
        </div><!--主页面结束-->
        <div class="row clearfix blank_bar" style="background-color: #ffffff">
        @微博推荐系统
        </div>
    </div><!--流动容器Ending-->
    <div id="topbtn"><!--回到顶部按钮-->
        <a href="javascript:returnTop()" style="color: #ffffff;text-align: center">Top</a>
    </div>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	<script src="js/topic_operation.js"></script>
    <script src="js/beforelogin.js"></script>
    <script src="js/styleswitch.js"></script>
    </body>
</html>
