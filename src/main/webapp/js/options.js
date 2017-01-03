/**
 * 	动态生成option
 */
var show_items = function() {   		
			
	$("#exchange").change(function(){
		
        $("#name").empty();
	    $("#name").append("<option value='' selected='selected'> --请选择-- </option>");

	    var parent = $("#exchange").val();

	    if (parent == ''){
	      return false;
	    } else {
	    	$.getJSON("/deposit/standard/api/getProperty.do", "property=name&exchange=" + parent, function(resp){
	    		var pro = resp.result;					    
	    		if(pro){
	    			for(var i = 0; i < pro.length; i++) {
	    				var opt = new Option(pro[i], pro[i]);
	    				$("#name").append(opt);
	    			}			    			
	    		}
	    	})
	    }
	});
}	

var calculate = function() {
	
	var exchange = $("#exchange").val();
	if(exchange == "") {
		alert("请选择交易所！");
		return false;
	}
	
	var name = $("#name").val();
	if(name == "") {
		alert("请选择品种！");
		return false;
	}
	
	var money = $("#money").val();
	if(money == "") {
		alert("请填写点位！");
		return false;
	}
	if(money < 1) {
		alert("点位需大于0！");
		return false;
	}
	
	var hand = $("#hand").val();
	if(hand == "") {
		alert("请填写手数！");
		return false;
	}
	if(hand < 1) {
		alert("手数需大于0！");
		return false;
	}
	
	$.getJSON("/deposit/standard/api/calculate.do", "name=" + name + "&exchange=" + exchange + "&money=" + money + "&hand=" + hand, function(resp){
		var pro = resp.result;					    
		$("#result").val(pro);
	})
}

$(document).ready(function(){
   show_items();
   $("#calculate").on("click", calculate);
});