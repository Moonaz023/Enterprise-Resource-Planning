$(document).ready(function() {
	getVendors();

});
var vendor = "";

function getVendors() {
	$.ajax({
		type: "GET",
		url: "/getAllVendors", 
		success: function(vendor_response) {
			console.log(vendor_response); 
			vendor = vendor_response;

			
			var dropdown = $("#vendor");
			dropdown.empty();
			dropdown.append('<option value="">Select Vendor</option>');
			$.each(vendor_response, function(index, vendor) {
				dropdown.append('<option value="' + vendor.id + '">' + vendor.name + '</option>');
			});
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}

function getAllProduct() {
	$.ajax({
		type: "GET",
		url: "/getAllProducts",
		success: function(response) {

			data = response;
			var dropdown = $("#productInput");
			dropdown.empty();
			dropdown.append('<option value="">Select Product</option>');
			$.each(response, function(index, product) {
				dropdown.append('<option value="' + product.id + '">' + product.name + '</option>');
			});
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}