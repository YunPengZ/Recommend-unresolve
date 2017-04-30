<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
            <!--页面实现用户修改个人资料的功能-->
        <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
        <link rel="stylesheet" href="css/bootstrap.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
       
        <script type="text/javascript">
        var flag_tag=true; 
        var ID='${sessionScope.user.userId}'; 
       alert(" "+ID)
        var userName='${sessionScope.user.userName}'+'的个人资料';
        document.title=userName;
        function selectTag(str){
        	var reg1=new RegExp("^请选择*");
        	if(reg1.test(document.getElementById("showTag").value)){
        		document.getElementById("showTag").value='';
        	}
            document.getElementById("showTag").value += ' ' + str;
        }
      	/*window.onload=function test(){
      	  alert(document.forms['reg'].showTag.value);
      	}*/
      	function checkTag(str){
      		//alert(str)
      		if(str==""||str=="请选择标签！"){
      			document.getElementById("showTag").value="请选择标签！";
        		//document.getElementById("showtagHelp").style.display='block';
      			flag_tag=false;
      		}else {
      			flag_tag=true;
      		}
      	}
        function save(){
        	//alert(flag_tag)
	        	if(document.getElementById("showTag").value!=""||document.getElementById("showTag").value!="请选择标签！"){
	      			//document.getElementById("showTag").value="请选择标签！";
	        		//document.getElementById("showtagHelp").style.display='block';
	      			flag_tag=true;
	      		}
	        	//alert(flag_tag)
        		var form=document.forms[0];        		
        		var userIntro=document.getElementById("user_introduction").value;
        		var tel=form.tel.value;
        		var email=form.email.value;
        		var birth=form.birth.value;
        		var region=form.region.value;
        		var sexValue=null;
        		if(form.sex[0].checked=="checked")sexValue=form.sex[0].value;
        		else sexValue=form.sex[1].value;
        		
        		if(flag_tag){
        			$.ajax({
        				url:"update/userinfo",
        				dataType:"text",
        				async:true,
        				data:{"userName":userName,
        					"userSex":sexValue,
        					"userTel":tel,
        					"userEmail":email,
        					"userLabels":document.getElementById("showTag").value,
        					"userBirth":birth,
        					"userAddress":region,
        					"userIntroduction":userIntro
        					//"userPassword":document.getElementById("showTag").value,
        					},
        				success:function(){
        					
        					window.location="AfterLogin.jsp"
        				},
        				error:function(){
        					alert("修改个人资料出错")
        				}	
        			});
        	}else {
        		
        		alert("重新更新用户信息")
        	}
        	
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
                        <form name="user_info" method=" post" class="form-horizontal" role="form">
                            <div class="row clearfix box col-md-offset-2 col-md-8"><!--上传头像-->
                                <h4>用户头像</h4>
                                <img id="thumbnail" src="src/user-lg.png" height="64">
                                <input type="file" onchange="previewFile()">
                            </div>
                            <div class="row clearfix box col-md-offset-2 col-md-8"><!--用户名-->
                                <h4>用户名</h4>
                                <input name="username" onfocus="this.value=''" type="text"  class="form-control" id="signusername" />
                            </div>
                            <div class="row clearfix box col-md-offset-2 col-md-8"><!--手机号-->
                                <h4>手机号</h4>
                                <input name="tel"onfocus="this.value=''"  class="form-control"/>
                            </div>
                             <div class="row clearfix box col-md-offset-2 col-md-8"><!--生日-->
                                <h4>生日</h4>
                                <input name="birth" onfocus="this.value=''" class="form-control"/>
                            </div>
                             <div class="row clearfix box col-md-offset-2 col-md-8"><!--住址-->
                                <h4>籍贯</h4>
                                <input name="region" onfocus="this.value=''" class="form-control"/>
                            </div>
                            <div class="row clearfix box col-md-offset-2 col-md-8"><!--邮箱-->
                                <h4>邮箱</h4>
                                <input name="email" onfocus="this.value=''" class="form-control"/>
                            </div>
                            <div class="row clearfix box col-md-offset-2 col-md-8"><!--性别-->
                                <h4>性别</h4>
                                <input type="radio" name="sex" value="男"  />男
                                <input type="radio" name="sex" value="女" />女
                            </div>
                           
                            <div class="row clearfix box col-md-offset-2 col-md-8"><!--标签选择-->
                                <h4>用户标签</h4>
                          
                                 <div class="input-group">
                                    <input id="showTag" name="showTag" onfocus="this.value=''" onBlur="checkTag(this.value)" type="text" class="form-control">
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
                            <!--  
                             <div class="row clearfix box col-md-offset-2 col-md-8">
                              	<h4>评论转发系数</h4>
                            	<input type="radio" name="prefer" value="评论" checked="checked"/>关注评论
                            	<input type="radio" name="prefer" value="转发" checked="checked"/>关注转发
                            	<input type="radio" name="prefer" value="相同" checked="checked"/>同样关注
                            </div>
                            -->
                            <div class="row clearfix box col-md-offset-2 col-md-8"><!--用户简介-->
                                <h4>用户简介</h4>
                                <textarea  name="introduction" rows="4" id="user_introduction" maxlength="30" style="width:100%;border:2px groove #cccccc;"></textarea>
                            </div>
                            <div class="row clearfix box">
                                <button class="btn btn-default col-xs-offset-9" onclick="save()"style="background-color: #ff8140;color: #ffffff"><strong>保存</strong></button>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-3"><!--留白作对齐用-->
                </div>
            </div>
        </div>
        <script src="js/profileLoad.js"></script>
    </body>
    
</html>
