$(function() {
    var editor = editormd("question-editor", {
        width  : "100%",
        height : 350,
        path   : "/js/lib/",
        delay  : 0,
        watch  :false,
        placeholder:"请输入问题内容",
        imageUpload    : true,
        imageFormats   : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL : "/file/upload",
        
    });
});

function comment(){
	var question_id=$("#question_id").val();
	var content=$("#comment_content").val();
	console.log(content);
	if(!content){
		alert("评论内容不能为空");
		return;
	}
	var type=1;
	$.ajax({
		type:"POST",
		url:"/comment",
		contentType:"application/json",
		data: JSON.stringify({
			"question_id":question_id,
			"content":content,
			"type":type
		}),
		success:function(data){
			window.location.reload();
			console.log(data)
		},
		dataType:"json"
	});
}
function sec_comment(e){
	var id=e.getAttribute("data_id");
	var question_id=$("#question_id").val();
	var sec_content=$("#sec_comment_content"+id).val();
	console.log(comment_session);
	if(!sec_content){
		alert("评论内容不能为空");
		return;
	}
	var type=2;
	$.ajax({
		type:"POST",
		url:"/sec_comment",
		contentType:"application/json",
		data: JSON.stringify({
			"comment_id":id,
			"question_id":question_id,
			"content":sec_content,
			"type":type
		}),
		success:function(data){
			window.location.reload();
			console.log(data)
		},
		dataType:"json"
	});
}
function publisn_article(){
	var title=$("[name='title']");
	var description=$("[name='description']");
	var tag=$("[name='tag']");
	console.log(title)
	if(!title){
		alert("标题不能为空")
	}
	if(!description){
		alert("问题不能为空")
	}
	if(!tag){
		alert("标签不能为空")
	}
}
