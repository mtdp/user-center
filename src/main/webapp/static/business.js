var url = window.location.href;
if(url.indexOf("user") != -1){
	$('#J_user').addClass("active").siblings().removeClass("active");
}
if(url.indexOf("role") != -1){
	$('#J_role').addClass("active").siblings().removeClass("active");
}
if(url.indexOf("res") != -1){
	$('#J_res').addClass("active").siblings().removeClass("active");
}
//全选
$('.select-all').on('click',function(e){
	var _this = $(this);
	if(_this.is(":checked")){
		//选中
		_this.prop("checked",true);
		//查找所有的
		$("[type='checkbox']").each(function(){
			var _cThis = $(this);
			_cThis.prop("checked",true);
		});
	}else{
		//选中
		_this.prop("checked",false);
		//查找所有的
		$("[type='checkbox']").each(function(){
			var _cThis = $(this);
			_cThis.prop("checked",false);
		});
	}
});