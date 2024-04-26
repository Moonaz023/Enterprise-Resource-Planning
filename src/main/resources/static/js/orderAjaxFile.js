$(document).ready(function () {
			getAllProduct();
			getAllDistributors();
			$("#insert").click(function () {
				
				var form = $("#orderForm");
				
				event.preventDefault();
				
				var formData = form.serializeArray();
				var products = [];
				
				
				$.ajax({
					type: "POST",
					url: "/addOrder",
					data: formData,

					success: function (result) {
						console.log(result); 
						
						$("#productsContainerDynamic").empty();
						
						document.getElementById("orderForm").reset();
						
					},
					error: function (err) {
						alert("Error: " + JSON.stringify(err));
					}
				});
			});

			$("#addProduct").click(function () {
				var productInput = $("<select>").attr({
					class: "productInput",
					name: "product"
				});
				
				if (data) {
					productInput.append(`<option value="">Select Product</option>`);
					data.forEach(function (product) {
						productInput.append(`<option value="${product.id}">${product.name}</option>`);
					});
				}
				var productQuantity = $("<input>").attr({
					type: "text",
					class: "productQuantity",
					name: "productQuantity"
				});
				$("#productsContainerDynamic").append(productInput).append('<span>&nbsp;</span>').append(productQuantity).append("<br>");
			});


		});

		var data = "";

		function getAllProduct() {
			$.ajax({
				type: "GET",
				url: "/getAllProducts",
				success: function (response) {
					
					data = response;
					var dropdown = $("#productInput");
					dropdown.empty();
					dropdown.append('<option value="">Select Product</option>');
					$.each(response, function (index, product) {
						dropdown.append('<option value="' + product.id + '">' + product.name + '</option>');
					});
				},
				error: function (err) {
					alert("Error: " + err);
					console.error("Error:", err);
				}
			});
		}
		var distributorOption = "";

		function getAllDistributors() {
			$.ajax({
				type: "GET",
				url: "/getAllDistributors",
				success: function (distributor_response) {
					
					distributorOption = distributor_response;
					var dropdownDistributor = $("#distributor_id");
					dropdownDistributor.empty();
					dropdownDistributor.append('<option value="">Select Distributor</option>');
					$.each(distributor_response, function (index, distributor) {
						dropdownDistributor.append('<option value="' + distributor.id + '">' + distributor.name + '</option>');
					});
				},
				error: function (err) {
					alert("Error: " + err);
					console.error("Error:", err);
				}
			});
		}