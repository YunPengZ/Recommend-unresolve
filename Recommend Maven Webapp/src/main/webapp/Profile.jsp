<%@ page import="ahu.edu.cn.JavaBeans.WeiboUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
    <head>
        <meta charset = "utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.css">
        <title>加载中....</title>
        
    </head>
    <body  style="background-color: #94c5e3;">
        <div class="container-fluid">
            <div class="row clearfix"><!--导航栏-->
                <div class="col-md-12">
                    <nav class="navbar navbar-default navbar-fixed-top">
                        <div class="navbar-header">
                            <a class="navbar-brand" href = "#"><img src="src/wb_logo.png" alt="..." style="max-height:25px"></a>
                        </div>
                        <form class="navbar-form navbar-right" role="search">
                            <div class="form-group">
                                <input type="text" value="Search" class="form-control" placeholder="Search">
                            </div>
                            <button type="submit" class="btn btn-default">查找</button>
                        </form>
                    </nav>
                </div>
            </div>
            <div class="row clearfix"><!-- 用户头像 -->
                <div class="col-md-12" style="text-align: center;padding: 5%;">
                    <img src="src/user-lg.png" class="img-circle" style="min-width: 5%;">
                    <div style="padding: 20px;">
                       <h4 id="user_name"></h2>
                    </div>
                </div>
            </div>


            <div class="row clearfix"><!--主页面-->
                <div class="col-md-2"><!--留白作对齐用-->
                </div>
                <div class="col-md-2"><!--侧边栏-->
                    <div class="row clearfix box"><!-- 个人资料显示 -->
						<div class="card">
							<ul style="list-style: none;padding: 0px">
								<li id="user_sex">性别：</li>
								<li id="user_email">邮箱：</li>
								<li id="user_birth">生日：</li>
								<li id="user_addr">住址：</li>
								<li id="user_labels">个人标签：</li>
								<li id="user_intro">个人简介：</li>
							</ul>
						</div>
                    </div>

                </div>
                <div class="col-md-5"><!--微博页面-->
					<div class="row clearfix box"><!-- TAB栏 -->
                        <div class="card" style="text-align: center;font-size: 0.9vw">
							<div class="list-group list-group-horizontal" id="user_relation">
								<span style="padding-right: 4%;"><a href="#follower" onclick="getfollower()" id="user_follow"class="list-group-item" aria-controls="following" role="tab" data-toggle="tab">关注</a></span>
								<span style="padding-left: 3%;padding-right: 3%;"><a href="#fans" onclick="getfans()" id="user_fans" class="list-group-item" aria-controls="follower" role="tab" data-toggle="tab">粉丝</a></span>
								<span style="padding-left: 4%;"><a href="#home" id="user_blog" onclick="getUserBlog()" class="list-group-item" aria-controls="weibo" role="tab" data-toggle="tab">微博</a></span>
							</div>
                        </div>
                    </div>
                    <div class="tab-content">  
                        <div role="tabpanel" class="tab-pane active" id="home"><!--用户微博页面-->

                    	</div>
						<div role="tabpanel" class="tab-pane" id="follower"><!-- 用户关注用户 -->
							<div class="row clearfix box">
								<div class="card">
									<div class="media-left">
	                                    <a href="#"><img class="media-object img-thumbnail" src="src\user.png"></a>
	                                </div>
									<div class="media-body">
	                                    <h4 style="font-weight: bold;">follower</h4>
									</div>
								</div>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="fans"><!-- 用户粉丝用户 -->
							<div class="row clearfix box">
								<div class="card">
									<div class="media-left">
	                                    <a href="#"><img class="media-object img-thumbnail" src="src\user.png"></a>
	                                </div>
									<div class="media-body">
	                                    <h4 style="font-weight: bold;">fans</h4>
									</div>
								</div>
							</div>
						</div>
                        <div class="row clearfix box"><!--翻页页面-->
                          <div id="pagination" class="Pagination"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3"><!--留白作对齐用-->
                </div>
            </div>
        </div>
		<script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/profileLoad.js"></script>
        <script src="js/get_fans_follow.js"></script>
        <script src="js/profile.js"></script>
    </body>
</html>
