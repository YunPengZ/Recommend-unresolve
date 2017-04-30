window.onload=function request(){
	var str=location.href;
	var param=null;
	var userId = parseInt(str.substr(str.indexOf('=')+1));
	//alert(userId)
	if(userId==null||userId==""||isNaN(userId))
		{
		param=ID;
		//createRequest("querycomments.do",getComments,param);
		}
	else param=userId;
	$.ajax({
		url:"user/getinfo",
		dataType:"JSON",
		async:true,
		data:{"userId":param},
		type:"POST",
		success:function(data){
			//alert(data)
			$.each(data,function(i,item){
				//alert(item.userName)
				if(item==null||item==""||item==undefined)
				{
				alert("该用户暂未注册");
				return;
				}
			else{
				//var user=result.split("|");
				//var str=location.href;
				var reg1=new RegExp("^.*Profile.*$");
				var reg2=new RegExp("^.*combine.*$");
				//alert(item.userAddress)
				if(reg2.test(str)){/*页面combine对应的js代码*/
					//alert(user)
					var form=document.forms[0];
					form.username.value=item.userName;
 				form.introduction.value=item.userIntroduction;
 				if(form.sex[0].value=item.userSex)form.sex[0].checked="checked";
 				else form.sex[1].checked="checked";
 				form.showTag.value=item.userLabels;
 				form.email.value=item.userEmail;
 				form.tel.value=item.userTel;
 				form.birth.value=item.userBirth;
 				form.region.value=item.userAddress;
 				
					}
				else{
				//	alert(item.userFollowNum);
					//var user_follow=item.userFollowNum;
					var user_follow=document.getElementById("user_follow");
	 				var user_fans=document.getElementById("user_fans");
	 				var user_blog=document.getElementById("user_blog");
	 				var user_name=document.getElementById("user_name")
					if(reg1.test(str)){//页面Profile对应的js代码区域
						var user_name=document.getElementById("user_name");
	 					var user_sex=document.getElementById("user_sex");
		 				var user_email=document.getElementById("user_email");
		 				var user_labels=document.getElementById("user_labels");
		 				var user_intro=document.getElementById("user_intro");
		 				var user_birth=document.getElementById("user_birth");
		 				var user_addr=document.getElementById("user_addr");
		 				
		 				var userIntro=item.userIntroduction;

	 				user_name.innerHTML=item.userName;
	 				//user_sex.innerHTML="性别："+user_sex;
	 				user_email.innerHTML="邮箱："+item.userEmail;
	 				user_birth.innerHTML="生日："+item.userBirth;
	 				user_addr.innerHTML="住址："+item.userBirth;
	 				//alert(user_addr)
	 				user_labels.innerHTML="用户标签："+item.userLabels;
	 				if(userIntro==null||userIntro=="")userIntro="无 ";
	 				user_intro.innerHTML="用户简介:"+userIntro;
	 				}
					
 				document.title=item.userName+"的微博";
 				user_name.innerHTML=item.userName;
 				user_fans.innerHTML="粉丝"+item.userFansNum;
 				user_follow.innerHTML="关注"+item.userFollowNum;
 				user_blog.innerHTML="微博"+item.userBlogNum;
 				//getrelateInfo();//relate_user_operation.js
 				//getfansfollow();//
				}
			}
			});
			
			
		},
		error:function(){
			alert("获取用户信息出错")
		}
	});
	//createRequest("getuserinfo.do",UserInfo,param);
	
}