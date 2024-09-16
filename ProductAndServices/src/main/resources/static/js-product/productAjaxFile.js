//===========================================Insert=========================================================
var unitlist; 
$(document).ready(function() {
	getAllRecords();
	getUnits();
	$("#insert").click(function() {
		// Get the form associated with the clicked button
		var form = $("#formProduct");
		//var form = gatherFormData();
		 const formData = gatherFormData();
		// Prevent the default form submission
		event.preventDefault();
		// Make the AJAX request
		$.ajax({
			type: "POST",
			url: "/products/admin/saveProduct",
			headers: {
				'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
			},
			data: formData,
			success: function(result) {
				getAllRecords();
				$("#formProduct")[0].reset();
			},
			error: function(err) {
				alert("Error: " + JSON.stringify(err));
			}
		});
	});
});
//==========================================show table========================================================
var data = "";

function getAllRecords() {
	$.ajax({
		type: "GET",
		url: "/products/admin/getAllProducts",
		headers: {
			'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
		}, 
		success: function(response) {
			console.log(response); 
			data = response;
			$('#example').DataTable().destroy();
			$('#tableresult').empty();

			for (i = 0; i < response.length; i++) {
				var editUrl = "/edit/" + response[i].id; // Replace with your actual edit URL
				var deleteUrl = "/delete/" + response[i].id; // Replace with your actual delete URL
				// console.log("Row data:", response[i]); // Add this line

				$("#tableresult").append(
					'<tr class="tr">' +
					'<td>' + response[i].productCode + '</td>' +
					'<td>' + response[i].name + '</td>' +
					'<td>' + response[i].category + '</td>' +
					//  '<td>' + response[i].price + '</td>' +
					//  '<td>' + response[i].discount + '</td>' +

					'<td><a href="#" onclick="editRecord(' + data[i].id + ')">Edit</a></td>' +
					'<td><a href="#" onclick="deleteRecord(' + response[i].id + ')">Delete</a></td>' +
					'</tr>'
				);
			}


			// Initialize DataTables plugin
			$('#example').DataTable();
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}

//====================================Edit==========================================================================

function editRecord(id) {
	var record = data.find(function(item) {
		return item.id === id;
	});

	var editFormHtml = `
        <h2>Edit Product Record</h2>
        <form id="editForm" name="editForm" class="edit-form" action="@{/update}" method="post">
            <input type="hidden" id="id" name="id" value="${record.id}"><br>
            <label for="editProductCode">product Code</label>
            <input type="text" id="editProductCode" name="productCode" value="${record.productCode}"><br>
            <label for="editName">Name</label>
            <input type="text" id="editName" name="name" value="${record.name}"><br>
            <label for="editCategory">Category</label>
            <input type="text" id="editCategory" name="category" value="${record.category}"><br>
            <label for="editPrice">Price</label>
            <input type="text" id="editPrice" name="price" value="${record.price}"><br>
            <button type="button" id="update" class="btn btn-success">Save</button>
            <button type="button" id="cancel" class="btn btn-primary">Cancel</button>
        </form>
    `;

	// Show the edit form
	$("#editFormContainer").html(editFormHtml).show();

	// Hide the container
	$(".container").addClass("hidden");

	// Attach click event for the update button
	$("#update").click(function(event) {
		// Get the form associated with the clicked button
		var editForm = $("#editForm");

		// Prevent the default form submission
		event.preventDefault();
		console.log(editForm.serialize())
		$.ajax({
			type: "POST",
			url: "/products/admin/updateProduct",
			data: editForm.serialize(),
			headers: {
				'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
			},
			success: function(result) {
				// Handle success, e.g., update the UI
				alert("product updated successfully!");
				$("#editFormContainer").empty().hide();
				$(".container").removeClass("hidden");
				getAllRecords();
			},
			error: function(err) {
				alert("Error: " + JSON.stringify(err));
			}
		});
	});

	// Attach click event for the cancel button
	$("#cancel").click(function(event) {
		$("#editFormContainer").empty().hide();
		$(".container").removeClass("hidden");
		getAllRecords();
	});
}

//=========================================DELETE====================================================================

function deleteRecord(id) {
	$.ajax({
		type: "DELETE",
		url: "/products/admin/deleteProduct?id=" + id,
		headers: {
			'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
		},
		success: function(result) {
			// Refresh the table after successful deletion
			getAllRecords();
		},
		error: function(err) {
			alert("Error deleting record: " + JSON.stringify(err));
		}
	});
}










function addRecipeItem() {
            const container = document.getElementById('recipeContainerdynamic');
            const newItem = document.createElement('div');
            newItem.classList.add('recipeItem');

            let ingredientOptions = '<option value="">Select Ingredient</option>';
            unitlist.forEach(function(unit) {
                ingredientOptions += `<option value="${unit.id}">${unit.name}</option>`;
            });
           // let ingredientOptions = '<option value="">Select Unit</option>';
            

            newItem.innerHTML = `
            	 
                <select type="text" class="sellingUnit productInput halfinput" required>
                    ${ingredientOptions}
                </select>
               <input type="number" class="sellingPrice productQuantity halfinput" placeholder="Price" required>
                <i onclick="removeRecipeItem(this)" style="font-size:30px; color:red" class="fa fa-trash" aria-hidden="true"></i>
            `;
            container.appendChild(newItem);
        }
        
               function removeRecipeItem(button) {
            const item = button.parentNode;
            item.parentNode.removeChild(item);
        }

       
 function getUnits() {
	$.ajax({
		type: "GET",
		url: "/products/admin/getAllUnits",
		headers: {
			'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
		},
		success: function(respons_unit) {

			unitlist = respons_unit;
			var dropdown = $("#sellingUnit");
			dropdown.empty();
			dropdown.append('<option value="">Select Unity</option>');
			$.each(respons_unit, function(index, unit) {
				//alert(unit.id);
				dropdown.append('<option value="' + unit.id + '">' + unit.name + '</option>');
				
			});
		},
		error: function(err) {
			alert("Error: " + err);
			console.error("Error:", err);
		}
	});
}       
        
        
function gatherFormData() {
    const productCode = document.getElementById('productCode').value;
    const name = document.getElementById('name').value;
    const category = document.getElementById('category').value;
    const price = document.getElementById('price').value;

    const container = document.getElementById('recipeContainer');
    const ingredientInputs = container.getElementsByClassName('sellingUnit');
    const quantityInputs = container.getElementsByClassName('sellingPrice');

    let formData = `productCode=${productCode}&name=${name}&category=${category}&price=${price}`;

    for (let i = 0; i < ingredientInputs.length; i++) {
        let unitId = ingredientInputs[i].value;
        let Sellingprice = quantityInputs[i].value;

        formData += `&unitPrice[${i}].unit.id=${unitId}&unitPrice[${i}].price=${Sellingprice}`;
        
    }
console.log(formData);
    return formData;
}