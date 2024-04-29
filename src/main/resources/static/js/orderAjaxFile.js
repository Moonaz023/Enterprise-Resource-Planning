$(document).ready(function() {
	getAllProduct();
	getAllDistributors();
	getOrderList();
	$("#insertOrder").click(function() {

		var form = $("#orderForm");

		event.preventDefault();

		var formData = form.serializeArray();
		var products = [];
		if (!form[0].checkValidity()) {

			alert("Please fill out all required fields.");
			return; 
		}

		$.ajax({
			type: "POST",
			url: "/addOrder",
			data: formData,

			success: function(result) {
				console.log(result);

				$("#productsContainerDynamic").empty();

				document.getElementById("orderForm").reset();

			},
			error: function(err) {
				alert("Error: " + JSON.stringify(err));
			}
		});
	});

	$("#resetOrder").click(function() {
		$("#productsContainerDynamic").empty();

		document.getElementById("orderForm").reset();

	});

	$("#addProduct").click(function() {
		var productInput = $("<select>").attr({
			class: "productInput halfinput",
			name: "product"
		});

		if (data) {
			productInput.append(`<option value="">Select Product</option>`);
			data.forEach(function(product) {
				productInput.append(`<option value="${product.id}">${product.name}</option>`);
			});
		}
		var productQuantity = $("<input>").attr({
			type: "text",
			class: "productQuantity halfinput",
			name: "productQuantity",
			placeholder: "Product Quantity"
		});
		$("#productsContainerDynamic").append(productInput).append('<span>&nbsp;</span>').append(productQuantity).append("<br>");
	});


});

var data = "";

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
var distributorOption = "";

function getAllDistributors() {
	$.ajax({
		type: "GET",
		url: "/getAllDistributors",
		success: function(distributor_response) {

			distributorOption = distributor_response;
			var dropdownDistributor = $("#distributor_id");
			dropdownDistributor.empty();
			dropdownDistributor.append('<option value="">Select Distributor</option>');
			$.each(distributor_response, function(index, distributor) {
				dropdownDistributor.append('<option value="' + distributor.id + '">' + distributor.name + '</option>');
			});
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}

//==========================================show table========================================================
var orderList = "";

function getOrderList() {
	$.ajax({
		type: "GET",
		url: "/getodd",
		success: function(orderList_response) {
			
			orderList = orderList_response;

			for (i = 0; i < orderList_response.length; i++) {
				
				$("#order_result").append(
					'<tr class="tr">' +
					'<td>' + orderList_response[i].orderDetails + '</td>' +
					'<td>' + orderList_response[i].date + '</td>' +
					'<td>' + orderList_response[i].distributor_id.name + '</td>' +
					'<td><a href="#" onclick="CheckOutValidation('+ orderList[i].id +');">Checkout Now</a></td>' +

				     
					'<td><a href="#" onclick="deleteRecord(' + orderList_response[i].id + ')"><i class="fa fa-ellipsis-v" style="font-size:24px"></i></a></td>' +
					'</tr>'
				);
			}


			// Initialize DataTables plugin
			$('#orderTable').DataTable();
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}

function CheckOutValidation(validity_id) {
    var CheckOutOrder = orderList.find(function(CheckOutOrder) {
		return CheckOutOrder.id === validity_id;
	});
     console.log(validity_id);
    $.ajax({
        type: "GET",
        url: "/checkOutValidity?order_id=" + validity_id,
        data: validity_id,
        success: function(response) {
            
            if (response.success === true) {
               // checkOutOrderDiv(validity_id);
               var CheckOutForm = `
       
        <form >
             <h4>Checkout Order</h4>
            ${response.prices.map(function(price, index) {
    return `<div><h4>${CheckOutOrder.product[index]} (*${CheckOutOrder.productQuantity[index]}) = ${price}</h4></div>`;
}).join('')}
<h4> ${response.totalPrice}</h4>
            <label for="editRoll">Roll</label>
            
            <label for="editName">Name</label>
            
            <label for="editSesson">Session</label>
           
            <label for="editRegistration">Passing Year</label>
            
            
            <button type="button" id="updateaStuRecord" class="btn btn-success">Update</button>
            <button type="button" id="cancelupdateaStuRecord" class="btn btn-primary">Cancel</button>
        </form>
    `; 
                $("#CheckOutContainer").html(CheckOutForm).show();
				$(".container").addClass("hidden");

                
            } else {
                // Handle validation failure
                alert("Not Enough product in stock. Please start new production.");
            }
        },
        error: function(xhr, status, error) {
            // Handle error response
            console.error("Error performing order validation:", error);
            alert("An error occurred while validating the order. Please try again later.");
        }
    });
}
function checkOutOrderDiv(valid_id) {
	              //  alert("Order validation successful. Proceeding with checkout.");
              var CheckOutOrder = orderList.find(function(CheckOutOrder) {
		return CheckOutOrder.id === valid_id;
	});
	console.log(valid_id);
		$.ajax({
		type: "GET",
		url: "/getodd",
		success: function(orderList_response) {
			
			
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
		});
          var CheckOutForm = `
       
        <form >
             <h4>Checkout Order</h4>
             <div>
           <h4>${CheckOutOrder.orderDetails}</h4>
             
             </div>
            <label for="editRoll">Roll</label>
            
            <label for="editName">Name</label>
            
            <label for="editSesson">Session</label>
           
            <label for="editRegistration">Passing Year</label>
            
            
            <button type="button" id="updateaStuRecord" class="btn btn-success">Update</button>
            <button type="button" id="cancelupdateaStuRecord" class="btn btn-primary">Cancel</button>
        </form>
    `; 
                $("#CheckOutContainer").html(CheckOutForm).show();
				$(".container").addClass("hidden");
}
