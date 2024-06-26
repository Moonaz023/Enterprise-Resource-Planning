$(document).ready(function() {
	getAllProduction();
});
var productionList;
function getAllProduction() {
	$.ajax({
		type: "GET",
		url: "/admin/getAllProductions",
		success: function(response) {
			productionList = response;
			var dropdown = $("#productionInput");
			$.each(response, function(index, production) {
				dropdown.append('<option value="' + production.id + '">' + production.dateOfProduction + ' | ' + production.product.name + ' | ' + production.productionQuantity + '</option>');
			});
		},
		error: function(er) {
			alert("error:" + er);
		}

	});

}
$("#insert").click(function(){
	//alert("YYYOOOOOOOOOOOO");
	var form=$("#formDamagedProduct");
	event.preventDefault;
	var formdata=form.serializeArray();
	$.ajax({
		type:"POST",
		url:"/saveDamagedProduct",
		data:formdata,
		success:function(response){
			alert("YYYOOOOOOOOOOOO");
		},
		error: function(err){
			alert("error:" + er);
		}
	});
});