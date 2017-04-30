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
	//alert(param)
	$.ajax({
		url:"user/getinfo",
		dataType:"JSON",
		async:true,
		data:{"userId":param},
		type:"POST",
		success:function(data){
			
			if(data==null||data=="")
				{
				alert("该用户暂未注册");
				return;
				}
			else{
				//var userset=data.dataList;
				$.each(data,function(i,item){
					//var user=result.split("|");
					var str=location.href;
					var reg1=new RegExp("^.*Profile.*$");
					var reg2=new RegExp("^.*combine.*$");
					if(reg2.test(str)){/*页面combine对应的js代码*/
						//alert(user)
						var form=document.forms[0];
						form.username.value=item.userName;
	 				form.introduction.value=userIntroduction;
	 				if(form.sex[0].value=item.userSex)form.sex[0].checked="checked";
	 				else form.sex[1].checked="checked";
	 				form.showTag.value=item.userLabels;
	 				form.email.value=item.userEmail;
	 				form.tel.value=item.userTel;
	 				form.birth.value=item.userBirth;
	 				form.region.value=item.userAddress;
	 				
						}
					else{
						//alert(user);
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
		 				user_name.innerHTML=item.userName;
		 				user_sex.innerHTML="性别："+item.userSex;
		 				user_email.innerHTML="邮箱："+item.userEmail;
		 				user_birth.innerHTML="生日："+item.userBirth;
		 				user_addr.innerHTML="住址："+item.userAddress;
		 				user_labels.innerHTML="用户标签："+item.userLabels;
		 				if(item.userIntroduction==null||item.userIntroduction=="")item.userIntroduction="无 ";
		 				user_intro.innerHTML="用户简介:"+item.userIntroduction;
		 				}
						alert("userName"+item.userName)
	 				document.title=item.userName+"的微博";
	 				user_name.innerHTML=item.userName;
	 				user_fans.innerHTML="粉丝"+item.userFansNum;
	 				user_follow.innerHTML="关注"+item.userFollowNum;
	 				user_blog.innerHTML="微博"+item.userBlogNum;
	 				//getrelateInfo();//relate_user_operation.js
	 				//getfansfollow();//
					}
				});
				
			}
		},
		error:function(){
			
		}
	});
	//createRequest("getuserinfo.do",UserInfo,param);
	
}

function UserInfo()
{
	 if(http_request.readyState==4){
	 		if(http_request.status==200){
	 			result=http_request.responseText;
	 			result.replace(/\s/g,""); 
	 			if(result==null||result=="")
	 				{
	 				alert("该用户暂未注册");
	 				return;
	 				}
	 			else{
	 				var user=result.split("|");
	 				var str=location.href;
	 				var reg1=new RegExp("^.*Profile.*$");
	 				var reg2=new RegExp("^.*combine.*$");
	 				if(reg2.test(str)){/*页面combine对应的js代码*/
	 					//alert(user)
	 					var form=document.forms[0];
	 					form.username.value=user[0];
		 				form.introduction.value=user[7];
		 				if(form.sex[0].value=user[6])form.sex[0].checked="checked";
		 				else form.sex[1].checked="checked";
		 				form.showTag.value=user[8];
		 				form.email.value=user[4];
		 				form.tel.value=user[5];
		 				form.birth.value=user[9];
		 				form.region.value=user[10];
		 				
	 					}
	 				else{
	 					//alert(user);
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
			 				user_name.innerHTML=user[0];
			 				user_sex.innerHTML="性别："+user[6];
			 				user_email.innerHTML="邮箱："+user[4];
			 				user_birth.innerHTML="生日："+user[9];
			 				user_addr.innerHTML="住址："+user[10];
			 				user_labels.innerHTML="用户标签："+user[8];
			 				if(user[7]==null||user[7]=="")user[7]="无 ";
			 				user_intro.innerHTML="用户简介:"+user[7];
			 				}
	 					
		 				document.title=user[0]+"的微博";
		 				user_name.innerHTML=user[0];
		 				user_fans.innerHTML="粉丝"+user[1];
		 				user_follow.innerHTML="关注"+user[2];
		 				user_blog.innerHTML="微博"+user[3];
		 				getrelateInfo();//relate_user_operation.js
		 				//getfansfollow();//
	 				}
	 			}
	 		}
	 }
}