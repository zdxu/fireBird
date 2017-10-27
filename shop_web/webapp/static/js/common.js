// JavaScript Document

$(document).ready(function(){

//产品详情tab	
	var pt=$(".detail-title span");
	var pc=$(".detail-con");
	pt.first().addClass("detail-c");
	pc.first().show();
	pt.each(function(index){
		$(this).click(function(){
			$(this).addClass("detail-c").siblings("span").removeClass("detail-c");
			pc.hide();
			pc.eq(index).show();
			});
		});

//筛选
	var f_btn=$(".filter-btn");
	var f_wrap=$(".filter-bg");
	var f_con=$(".filter");	
	var f_mask=$(".filter-mask");
	
	f_btn.click(function(){
		f_wrap.show();
		$("html,body").css({"height":"100%","overflow":"hidden"});
		f_con.animate({ right:0}, 200);			
		});		

	function hides(){		
		f_con.animate({ right:"-3.3rem"}, 200);	
		setTimeout(function(){
			f_wrap.hide();	
			$("html,body").css({"height":"auto","overflow":"auto"});	
			},200); 
		} 		
		
	f_mask.click(function(){
		hides();
		});		
		
	$(".filter-finish").click(function(){
		hides();
		});	
	
});