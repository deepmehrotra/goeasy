/* For 1st column */
$(document).ready(function() {
 $(".span-1-of-3 > p:not(:first-of-type)").css({"display": "none"});
	if($(window).width()>=768) {

	
	$(".one > .read-more a, .one > h3").click(function(){
	$(".two, .three, .one .read-more a").hide();
	$(".one > .back-button").show();
	$(".one").css({"width": "100%", "border":"none"}).animate({ "left":"0","right": "100%" }, "120000" );
	$(".one h3 a").addClass("active");
	$(".main-body-content").css({"height":"500px"});
	$(".span-1-of-3 > p").css({"display": "block"});
	});
	
	$(".one > .back-button ").click(function(){
	$(".one").css({"width": "33.33%", "border-right":"1px solid #ff9933"}).animate({ "left":"0"}, "120000" );
	$(".one h3 a").removeClass("active");
	$(".main-body-content").css({"height":"360px"});
	$(".span-1-of-3").css({"height":"313px"});
	$(".span-1-of-3 > p:not(:first-of-type)").css({"display": "none"});
	$(".two, .three, .one .read-more a").show();
	$(".back-button").hide();
	});
	
	/* For 2nd column */
	
	$(".two > .read-more a, .two > h3").click(function(){
	$(".one, .three, .two .read-more a").hide();
	$(".two > .back-button").show();
	$(".two").css({"width": "100%", "border":"none"}).animate({ "left":"0","right": "100%" }, "1200000" );
	$(".two h3 a").addClass("active");
	$(".main-body-content").css({"height":"400px"});
	$(".span-1-of-3 > p").css({"display": "block"});
	});
	
	$(".two > .back-button").click(function(){
	$(".two").css({"width": "33.33%", "border-right":"1px solid #ff9933"}).animate({ "left":"0"}, "120000" );
	$(".two h3 a").removeClass("active");
	$(".main-body-content").css({"height":"360px"});
	$(".span-1-of-3").css({"height":"313px"});
	$(".span-1-of-3 > p:not(:first-of-type)").css({"display": "none"});
	$(".one, .three, .two .read-more a").show();
	$(".back-button").hide();
	});
	
	/* For 3rd column */
	
	$(".three > .read-more a, .three > h3").click(function(){
	$(".one, .two, .three .read-more a").hide();
	$(".three > .back-button").show();
	$(".three").css({"width": "100%", "border":"none"}).animate({ "left":"0","right": "100%" }, "120000" );
	$(".three h3 a").addClass("active");
	$(".main-body-content").css({"height":"250px"});
	$(".span-1-of-3 > p").css({"display": "block"});
	});
	
	$(".three > .back-button").click(function(){
	$(".three").css({"width": "33.33%"}).animate({ "left":"0"}, "120000" );
	$(".three h3 a").removeClass("active");
	$(".main-body-content").css({"height":"360px"});
	$(".span-1-of-3").css({"height":"313px"});
	$(".span-1-of-3 > p:not(:first-of-type)").css({"display": "none"});
	$(".one, .two, .three .read-more a").show();
	$(".back-button").hide();
	});
	
	$(".sign-in-button").hover(function(){
	$(".sign-in-window").toggle();
	$(".sign-in-button").addClass("active");
	$("#frm1 [name='uname']").focus();
	});
	
	}
	$(".mobile_collapser").click(function(){
	$(".menu ul").toggle();
	});
	
});




