//分页
$(function() {

	$(".page-link").click(function() {
		var url = $(this).attr("data");
	
		$("#content-wrapper").load(url);
	})
})
