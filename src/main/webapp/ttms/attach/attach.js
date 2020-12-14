$(document).ready(function(){
   $("#uploadFormId")
   .on("click",".btn-upload",doUpload);	
});
/*点击文件上传按钮执行此函数*/
function doUpload(){
	//异步提交表单($.ajaxSubmit为异步提交表单)
	//使用此函数时需要在页面引入(jquery.form.js )
	$("#uploadFormId").ajaxSubmit({
		type:"post",
		url:"attachement/doUpload.do",
		dataType:"json",
		success:function(result){
			alert(result.message);
		}
	});
	//$("#uploadFormId").resetForm();
	return false;//防止表单重复提交的一种方式
}
