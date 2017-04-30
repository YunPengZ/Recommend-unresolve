       var flag_username=true; //用户名是否可用
        var flag_password=true; //密码是否合法
        var flag_email=true;    //邮箱输入是否合法
        var flag_tel=true;      //手机号码是否合法

        
 
        /*
         * 用户名是否可用
         */
        function checkUsernameIsAvaiable(str){
        	if(str==""){
        		document.getElementById("username").innerHTML="请输入用户名！";
        		document.getElementById("usernamehelp").style.display='block';
		        flag_username=false;
        	}else if(!checkUsernameIsValid(str)){
        		document.getElementById("username").innerHTML="输入的用户名不合法，请重新输入!";
        		document.getElementById("usernamehelp").style.display='block';
		        flag_username=false;
        	}else{ 	   
        	   
        		$.ajax({
       			url: "register/check/username",    //请求的url地址
       	  		dataType: "text",   //返回格式为json
       	  	  	async: true, //请求是否异步，默认为异步，这也是ajax重要特性
       	    	data: {"userName":str},    //参数值
       	    	type: "POST",   //请求方式
       	    	success: function(data) {
       	    	if(data=="1"){
       	    		document.getElementById("username").innerHTML="";
                	document.getElementById("usernamehelp").style.display='none';
                	flag_username=true;
       	    	}else{
       	    		document.getElementById("username").innerHTML="该用户名已经存在！";
                	document.getElementById("usernamehelp").style.display='block';
                	flag_username=false;
       	    	}
    	    
       	   		},
       	    	error: function() {
       	        	alert("出错");
       	    	}
       		
       			});
        	}
        }

        function checkPassword(str){
        	if(str==""){
        		document.getElementById("password").innerHTML="请输入密码！";
        		document.getElementById("passwordhelp").style.display='block';
        		flag_password=false;
        	}else if(!checkPasswordIsValid(str)){
        		document.getElementById("password").innerHTML="输入的密码不合法，请重新输入!";
        		document.getElementById("passwordhelp").style.display='block';
        		flag_password=false;
        	}else{
        		document.getElementById("password").innerHTML="";
        		document.getElementById("passwordhelp").style.display='none';
        		flag_password=true;
        	}
        }
        
 
        function checkTel(str){
        	if(str==""){
        		document.getElementById("tel").innerHTML="请输入手机号码！";
        		document.getElementById("telhelp").style.display='block';
        		flag_tel=false;
        	}else if(!checkTelIsValid(str)){
        	
        		document.getElementById("tel").innerHTML="输入的手机号码不合法，请重新输入!";
        		document.getElementById("telhelp").style.display='block';
        		flag_tel=false;
        	}else{
        		document.getElementById("tel").innerHTML="";
        		document.getElementById("telhelp").style.display='none';
        		flag_tel=true;
        	}
        }
        function checkEmail(str){
        	if(str==""){
        		document.getElementById("email").innerHTML="请输入邮箱！";
        		document.getElementById("emailhelp").style.display='block';
        		flag_email=false;
        	}else if(!checkEmailIsValid(str)){
        		document.getElementById("email").innerHTML="输入的邮箱不合法，请重新输入!";
        		document.getElementById("emailhelp").style.display='block';
        		flag_email=false;
        	}else{
        		document.getElementById("email").innerHTML="";
        		document.getElementById("emailhelp").style.display='none';
        		flag_email=true;
        	}
        }
        
        function save(){
        	
        	
        	if(reg.password.value==""){
        		document.getElementById("password").innerHTML="请输入密码！";
        		document.getElementById("passwordhelp").style.display='block';
		        flag_password=false;
        	}
        	if(reg.email.value==""){
        		document.getElementById("email").innerHTML="请输入邮箱！";
        		document.getElementById("emailhelp").style.display='block';
		        flag_email=false;
        	}
        	if(reg.tel.value==""){
        		document.getElementById("tel").innerHTML="请输入手机号码！";
        		document.getElementById("telhelp").style.display='block';
		        flag_tel=false;
        	}
        	if(reg.username.value==""){
        		document.getElementById("username").innerHTML="请输入用户名！";
        		document.getElementById("usernamehelp").style.display='block';
		        flag_username=false;
        	}
        	if(flag_email&&flag_password&&flag_tel&&flag_username){
        
        		$.ajax({
       			url: "register/save",    //请求的url地址
       	  		dataType: "text",   //返回格式为json
       	  	  	async: true, //请求是否异步，默认为异步，这也是ajax重要特性
       	    	data: {"userName":reg.username.value,
       	    	       "userPassword":reg.password.value,
       	    	       "userSex":reg.sex.value,
       	    	       "userBlogNum":0,
       	    	       "userFollowNum":0,
       	    	       "userFansNum":0,
       	    	       "userEmail":reg.email.value},    //参数值
       	    	type: "POST",   //请求方式
       	    	success: function(data) {
       	    	if(data==1){
       	    		reg.email.value="";
    				reg.tel.value="";
    				reg.username.value="";
    				reg.password.value="";
    				alert("用户注册成功")
    				window.location="combine.jsp";
       	    	}else{
       	    		
       	    	}
    	    
       	   		},
       	    	error: function() {
       	        	alert("出错");
       	    	}
       		
       			});
        		
        	}else{
        		return;
        	}
        }
        
