function transfer(blogId){
	var content=document.getElementById(blogId+"transfertext").value;
	if(content==""||content==null)alert("请输入评论");
	else{
		$.ajax({
			url:"operation/transfer",
			dataType:"text",
			asycn:true,
			data:{
				"blogId":blogId,
				"userId":ID,
				"content":content
			},
			type:"POST",
			success:function(){
	 			$("#mySmallModalLabel"+blogId).modal("hide");
	 			location.href="AfterLogin.jsp";
			},
			error:function(){
				alert("转发出错");
			}
		});
	}
	
	
}


function TransferClick(blogId){
	//alert(blogId)
	var author=document.getElementById(blogId+"author").innerHTML;
	var oldcontent=document.getElementById(blogId+"content").innerHTML;
		$("#home").append(
				"<div class=\"modal fade\" tabindex=\"-1\" role=\"dialog\" id=\"mySmallModalLabel"+blogId+"\">"+
                " <!--  转发框 -->"+
                    " <div class=\"modal-dialog\">"+
                         "<div class=\"modal-content\">"+
                          "   <div class=\"modal-header\">"+
                              "   <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>"+
                              "   <h4 class=\"modal-title\" id=\"myModalLabel\">转发框</h4>"+
                             "</div>"+
                             "<div class=\"modal-body\">"+
                                " <div class=\"row clearfix\">"+
                                  "   <div class=\"col-md-12\">"+
                                        " <div style=\"margin: 0px 0px 5px 0px;background-color: #f2f2f5\">"+
                                        "   <span>"+author+":</span>"+
                                        " <span>"+oldcontent+"</span>"+
                                         "</div>"+
                                     "</div>"+
                                " </div>"+
                                 "<div class=\"row clearfix\">"+
                                    " <div class=\"col-md-12\">"+
                                       "  <textarea id="+blogId+'transfertext'+" rows=\"4\" maxlength=\"140\" style=\"width:100%;border:2px groove #cccccc;\"></textarea>"+
                                     "</div>"+
                                " </div>"+
                            " </div>"+
                            " <div class=\"modal-footer\">"+
                                 "<button onclick=\"transfer("+blogId+")\" class=\"btn btn-default\" style=\"background-color:#fa7a38;color:#ffffff\">发布</button>"+
                             "</div>"+
                         "</div>"+
                     "</div>"+
                 "</div>"
		);
		$("#mySmallModalLabel"+blogId).modal("show");
	}

/*
 * 转发不同于点赞和评论，转发之后需要刷新页面，那么就简单一些。
 * */
function afterTransfer(){
	if(http_request.readyState==4){
 		if(http_request.status==200){
 			result=http_request.responseText;
 			result.replace(/\s/g,""); 
 			
 			}
	}
}