$(document).ready(function() {
    var data = [];
    getAllProduct();
    getAllDistributors();
    getOrderList();

    $("#insertOrder").click(function(event) {
        var form = $("#orderForm");
        event.preventDefault();
        if (!form[0].checkValidity()) {
            alert("Please fill out all required fields.");
            return;
        }
        var formData = form.serializeArray();
        //console.log(formData);
        $.ajax({
            type: "POST",
            url: "/orders/admin/addOrder",
            data: formData,
            headers: {
				'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
			},
            success: function(result) {
                console.log(result);
                $("#productsContainerDynamic").empty();
                document.getElementById("orderForm").reset();
                getOrderList();
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
        var productRow = $('<div class="product-row">');

        var productInput = $("<select>").attr({
            class: "productInput halfinput",
            name: "product"
        });

        if (data.length > 0) {
            productInput.append('<option value="">Select Product</option>');
            data.forEach(function(product) {
                productInput.append('<option value="' + product.id + '">' + product.name + '</option>');
            });
        }

        var productQuantity = $("<input>").attr({
            type: "text",
            class: "productQuantity halfinput",
            name: "productQuantity",
            placeholder: "Product Quantity"
        });

        var unitInput = $("<select>").attr({
            class: "unitInput halfinput",
            name: "unit"
        });
        unitInput.append('<option value="">Select unit</option>');

        productRow.append(productInput)
                  .append('<span>&nbsp;</span>')
                  .append(productQuantity)
                  .append('<span>&nbsp;</span>')
                  .append(unitInput)
                  .append("<br>");

        $("#productsContainerDynamic").append(productRow);
    });

    $("#productsContainerDynamic").on('change', '.productInput', function() {
        var productId = $(this).val();
        var unitInput = $(this).closest('.product-row').find('.unitInput');

        if (productId) {
            $.ajax({
                type: "GET",
                url: "/products/admin/getProductById?id=" + productId,
				headers: {
					'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
				},
                success: function(response) {
                    var units = response.unitPrice;
                    unitInput.empty();
                    unitInput.append('<option value="">Select Unit</option>');
                    for (var i = 0; i < units.length; i++) {
                        unitInput.append('<option value="' + units[i].unit.id + '">' + units[i].unit.name + '</option>');
                    }
                },
                error: function(err) {
                    alert("Error: " + JSON.stringify(err));
                    console.error("Error:", err);
                }
            });
        } else {
            unitInput.empty();
            unitInput.append('<option value="">Select Unit</option>');
        }
    });

    function getAllProduct() {
	$.ajax({
		type: "GET",
		url: "/products/admin/getAllProducts",
		headers: {
			'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
		},
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

    function getAllDistributors() {
        $.ajax({
			type: "GET",
			url: "/distributor/admin/getAllDistributors",
			headers: {
				'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
			},
            success: function(distributor_response) {
                var distributorOption = distributor_response;
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
var orderList = "";
    function getOrderList() {
        $.ajax({
            type: "GET",
            url: "/orders/admin/getodd",
            headers: {
				'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
			},
            success: function(orderList_response) {
                orderList = orderList_response;
                $('#orderTable').DataTable().destroy();
                $('#order_result').empty();
                for (i = 0; i < orderList_response.length; i++) {
                    $("#order_result").append(
                        '<tr class="tr">' +
                        '<td>' + orderList_response[i].orderDetails + '</td>' +
                        '<td>' + orderList_response[i].date + '</td>' +
                        '<td>' + orderList_response[i].distributor_id + '</td>' +
                        '<td><a href="#" onclick="CheckOutValidation(' + orderList[i].id + ');">Checkout Now</a></td>' +
                        '<td>'+
                            '<div class="dropdown">' +
                                '<a href="#" onclick="toggleDropdown(event, ' + orderList_response[i].id + ')">' +
                                    '<i class="fa fa-ellipsis-v" style="font-size:24px"></i>' +
                                '</a>' +
                                '<div id="dropdown-' + orderList_response[i].id + '" class="dropdown-content">' +
                                    '<a href="#" onclick="editOrder(' + orderList_response[i].id + ')">Edit</a>' +
                                    '<a href="#" onclick="deleteOrder(' + orderList_response[i].id + ')">Delete</a>' +
                                    '<a href="#" onclick="downloadOrder(' + orderList_response[i].id + ')">Download</a>' +
                                '</div>' +
                            '</div>' +
                        '</td>' +
                        '</tr>'
                    );
                }
                // Initialize DataTables plugin
                $('#orderTable').DataTable();
            },
            error: function(err) {
                console.error("Error:", err);
            }
        });
    }

    window.CheckOutValidation = function(validity_id) {
        var CheckOutOrder = orderList.find(function(CheckOutOrder) {
            return CheckOutOrder.id === validity_id;
        });
        $.ajax({
            type: "GET",
            url: "/orders/admin/checkOutValidity?order_id=" + validity_id,
            data: validity_id,
            headers: {
				'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
			},
            success: function(response) {
                if (response.success === true) {
                    var CheckOutForm = `
                    <div class="CheckoutDiv">
                        <h4>Checkout Order</h4>
                        ${response.details.map(function(detail) {
                            return `
                            <div class="CheckoutContent">
                                <div class="CheckoutContentLeft">
                                    <h6>${detail.productName} (*${detail.quantity} ${detail.unitName})</h6>
                                </div>
                                <div class="CheckoutContentRight">
                                    <h6>${detail.price}</h6>
                                </div>
                            </div>`;
                        }).join('')}
                        <hr>
                        <div class="CheckoutContent">
                            <div class="CheckoutContentLeft">
                                <h4>Total</h4>
                            </div>
                            <div class="CheckoutContentRight">
                                <h4>${response.totalPrice}</h4>
                            </div>
                        </div>
                        <div class="formdiv">
                            <form id="paymentForm">
                                <input type="number" id="orderId" name="orderId" value="${validity_id}" class="hidden">
                                <div>
                                    <label for="fullPaymentReceived">Full Payment Received</label>
                                    <input type="checkbox" id="fullPaymentReceived" onchange="togglePaymentFields(${response.totalPrice})">
                                </div>
                                <div class="CheckoutContent">
                                    <div class="CheckoutContentLeft">
                                        <label for="amount">Amount:</label>
                                        <input type="number" id="amount" name="amount" required>
                                    </div>
                                    <div id="dueField" class="CheckoutContentRight dueField">
                                        <label for="due">Due:</label>
                                        <input type="number" id="due" name="due" readonly>
                                    </div>
                                </div>
                                <div class="btnDiv">
                                    <button type="button" id="Checkoutbtn" class="btn btn-success">Checkout</button>
                                    <button type="button" id="resetCheckout" class="btn btn-primary">Cancel</button>
                                </div>
                            </form>
                        </div>
                    </div>`;
                    $("#CheckOutContainer").html(CheckOutForm).show();
                    $(".container").addClass("hidden");
                    const amountInput = document.getElementById('amount');
                    amountInput.addEventListener('input', function() {
                        var dueInput = document.getElementById("due");
                        dueInput.value = response.totalPrice - amountInput.value;
                    });

                    $("#Checkoutbtn").click(function() {
                        var paymentinfo = {
                            orderId: $("input[name='orderId']").val(),
                            receptAmount: $("input[name='amount']").val()
                        };
                        event.preventDefault();
                        $.ajax({
                            type: "POST",
                            url: "/orders/admin/checkoutNow",
                            data: paymentinfo,
							headers: {
								'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
							},
                            xhrFields: {
                                        responseType: 'blob'  // Important to handle binary data (PDF) correctly
                                    },
                            success: function(response) {
                                //alert("Download starting...");

                                // Create a link element and trigger download
                                const url = URL.createObjectURL(response);
                                const link = document.createElement('a');
                                link.href = url;
                                link.download = `Order.pdf`;  // Set a name for the downloaded file
                                document.body.appendChild(link);
                                link.click();

                                // Clean up
                                document.body.removeChild(link);
                                URL.revokeObjectURL(url);


                                    getOrderList();
                                    $("#CheckOutContainer").hide();
                                    $(".container").removeClass("hidden");

                            },
                            error: function(err) {
                                alert("Error Checkout: " + JSON.stringify(err));
                            }
                        });
                    });

                    $("#resetCheckout").click(function() {
                        $("#CheckOutContainer").hide();
                        $(".container").removeClass("hidden");
                    });
                } else {
                    alert("Not Enough product in stock. Please start new production.");
                }
            },
            error: function(xhr, status, error) {
                console.error("Error performing order validation:", error);
                alert("An error occurred while validating the order. Please try again later.");
            }
        });
    };

    window.togglePaymentFields = function(recived) {
        var fullPaymentReceived = document.getElementById("fullPaymentReceived");
        var dueField = document.getElementById("dueField");
        if (fullPaymentReceived.checked) {
            dueField.style.display = "none";
            var dueInput = document.getElementById("due");
            dueInput.value = 0;
            var amountInput = document.getElementById("amount");
            amountInput.value = recived;
        } else {
            dueField.style.display = "block";
        }
    };
});





$('#productInput').change(function() {
	var productId = $(this).val();
	if (productId) {
		// Fetch the selected product details to get unit prices
		$.ajax({
			type: "GET",
			url: "/products/admin/getProductById?id=" + productId,
			headers: {
				'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
			},
			success: function(response) {
				var units = response.unitPrice;
				$('#unitInput').empty();
				$('#unitInput').append('<option value="">Select Unit</option>');
				for (var i = 0; i < units.length; i++) {
					$('#unitInput').append('<option value="' + units[i].unit.id + '">' + units[i].unit.name + '</option>');
				}
			},
			error: function(err) {
				alert("Error: " + JSON.stringify(err));
				console.error("Error:", err);
			}
		});
	} else {
		$('#unitInput').empty();
		$('#unitInput').append('<option value="">Select Unit</option>');
	}
});

function toggleDropdown(event, orderId) {
    event.stopPropagation(); // Prevents click event from bubbling to document
    // Hide all dropdowns first
    document.querySelectorAll('.dropdown-content').forEach(dropdown => {
        dropdown.style.display = 'none';
    });
    // Toggle the current dropdown
    const dropdown = document.getElementById(`dropdown-${orderId}`);
    dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
}

// Close dropdown if clicked outside
document.addEventListener('click', function() {
    document.querySelectorAll('.dropdown-content').forEach(dropdown => {
        dropdown.style.display = 'none';
    });
});

function downloadOrder(id) {
    //alert(id);
    $.ajax({
        type: "GET",
        url: "/orders/admin/creat_pdf?order_id=" + id,
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
        },
        xhrFields: {
            responseType: 'blob'  // Important to handle binary data (PDF) correctly
        },
        success: function(response) {
            //alert("Download starting...");

            // Create a link element and trigger download
            const url = URL.createObjectURL(response);
            const link = document.createElement('a');
            link.href = url;
            link.download = `Order_${id}.pdf`;  // Set a name for the downloaded file
            document.body.appendChild(link);
            link.click();

            // Clean up
            document.body.removeChild(link);
            URL.revokeObjectURL(url);
        },
        error: function(xhr, status, error) {
            console.error("Error generating PDF:", error);
            alert("An error occurred while creating PDF. Please try again later.");
        }
    });
}
