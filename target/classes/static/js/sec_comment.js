var flag=false;

function add_comments(e){
	var id=e.getAttribute("data_id");
	flag =!flag;
	if(flag==true){
		$("#sec_comment"+id)[0].style.display="block"; 
		$("#comment-data"+id)[0].style.display="none"; 
	}else{
		$("#sec_comment"+id)[0].style.display = "none"; 
		$("#comment-data"+id)[0].style.display="block"; 
	}
}
