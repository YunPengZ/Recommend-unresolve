<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>注册页面</title>
        <meta charset = "utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.css">
         
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
                        <div class="card">
                            <div class="row clearfix">
                                <div class="col-md-8" style="padding:5%"><!--注册表格-->
                                    <form name="reg" action="RegisterServlet" method=" post" onsubmit="return register(this);" class="form-horizontal" role="form">
                                    	<div class="form-group">
                                            <label for="signEmail" class="col-md-2 control-label">用户名</label>
                                            <div class="col-md-6">
                                                <input  name="username" type="text" onBlur="checkUsernameIsAvaiable(this.value)" class="form-control" id="signusername" /><!--id="signMail"-->
                            
                                            </div>
                                            <div class="col-md-4" id="usernamehelp" style="font-size: 80%">用户名必须由英文字母和数字构成，并且长度不超过15</div>
                                            <div class="col-md-offset-2 col-md-6" id="username" style="font-size: 80%;color: #e64141"></div>
                                             
                                             
                                           
                                        </div>
                                    	<div class="form-group">
                                            <label for="signEmail" class="col-md-2 control-label">手机号码</label>
                                            <div class="col-md-6">
                                                <input name="tel" class="form-control" onblur="checkTel(this.value)" />
                                            </div>
                                             <div class="col-md-4" id="telhelp" style="font-size: 80%">
                                                	请输入11位手机号码
                                            </div>
                                            <div class="col-md-offset-2 col-md-10" id="tel" style="font-size: 80%;color: #e64141"><!--offset类作对齐用-->
                                            </div>
                                           
                                        </div>
                                    	<div class="form-group">
                                            <label for="signEmail" class="col-md-2 control-label">邮箱</label>
                                            <div class="col-md-6">
                                                <input name="email" class="form-control" onblur="checkEmail(this.value)" id="signMail"/><!--id="signMail"-->
                                            </div>
                                              <div class="col-md-4" id="emailhelp" style="font-size: 80%">
                                                	请输入正确的邮箱
                                            </div>
                                            <div class="col-md-offset-2 col-md-10" id="email" style="font-size: 80%;color: #e64141"><!--offset类作对齐用-->
                                            </div>
                                           
                                        </div>
                                        <div class="form-group">
                                            <label for="signEmail" class="col-md-2 control-label">性别</label>
                                            <div class="col-md-6">
                                                <input type="radio" name="sex" value="男" checked="checked" />男<!--id="signMail"-->
                                                <input type="radio" name="sex" value="女" checked="checked" />女<!--id="signMail"-->
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="signPassword" class="col-md-2 control-label">密码</label>
                                            <div class="col-md-6">
                                                <input type="password" name="password" class="form-control" onblur="checkPassword(this.value)" id="signPassword" "/>
                                            </div>
                                             <div class="col-md-4" id="passwordhelp" style="font-size: 80%">
                                                	用户名必须由英文字母和数字构成，长度在6到15
                                             </div>
                                            <div class="col-md-offset-2 col-md-10" id="password" style="font-size: 80%;color: #e64141"><!--offset类作对齐用-->
                                            </div>
                                           
                                        </div>
                                        <div class="form-group" style="margin-bottom: 0px">
                                            <div class="col-md-offset-2 col-xs-4">
                                                <button type="button" onclick="save()" class="btn btn-default"  data-toggle="modal" data-target="#myModal" id="btn" style="background-color: #ffc09f;color:#ffffff">
                                                    <a style="color: #ffffff"><strong>立即注册</strong></a>
                                                </button><!--#fa7a38-->
                                            </div>
                                            <div class="col-xs-6">
                                                <button type="reset" class="btn btn-default"><strong>重置</strong></button>
                                            </div>
                                        </div>
                                        
                                    </form>
                                </div>
                                <div class="col-md-4" style="border-left: 2px;border-color: #000000;padding:5%"><!--注册帮助-->
                                    <h5 class="col-md-offset-1">注册帮助</h5>
                                    <ul>
                                        <li><a href="#">注册操作指南</a></li>
                                        <li><a href="#">注册操作指南</a></li>
                                        <li><a href="#">注册操作指南</a></li>
                                        <li><a href="#">注册操作指南</a></li>
                                    </ul>
                                </div>
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
        <script src="js/ajaxrequest.js"></script>
        <script src="js/weibofunction.js"></script>
        <script src="js/register.js"></script>
    </body>
  
</html>
