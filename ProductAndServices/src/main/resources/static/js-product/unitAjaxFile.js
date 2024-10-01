$(document).ready(function() {
	getAllUnit();
});
var productionList;

$("#insert").click(function() {
	var form = $("#formUnit");
	event.preventDefault;
	var formdata = form.serializeArray();
	$.ajax({
		type: "POST",
		url: "/products/admin/saveNewUnit",
		data: formdata,
		headers: {
			'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
		},
		success: function(response) {

			form[0].reset();
			getAllUnit();
		},
		error: function(err) {
			alert("error:" + er);
		}
	});
});

$("#cancle").click(function() {
	var form = $("#formUnit");
	form[0].reset();

});
var data = "";
function getAllUnit() {
	$.ajax({
		type: "GET",
		url: "/products/admin/getAllUnits",
		headers: {
			'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
		},
		success: function(response) {
            data = response;
			$('#unitTable').DataTable().destroy();

			$("#unitTable_result").empty();
			$.each(response, function(index, record) {
				$("#unitTable_result").append(
					'<tr class="tr">' +
					'<td>' + record.name + '</td>' +
					'<td><a href="#" onclick="EditRecord(' + record.id + ');">Edit</a></td>' +
					'<td><a href="#" onclick="deleteRecord(' + record.id + ')">Delete</a></td>' +
					'</tr>'
				);
			});

			$("#unitTable").DataTable();

		},
		error: function(err) {
			alert("error=" + err);
		}

	});

}
function deleteRecord(id) {
     	$.ajax({
     		type: "DELETE",
     		url: "/products/admin/deleteUnit?id=" + id,
     		headers: {
     			'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
     		},
     		success: function(result) {
     			// Refresh the table after successful deletion
     			getAllUnit();
     		},
     		error: function(err) {
     			alert("Error deleting record: " + JSON.stringify(err));
     		}
     	});
     }






 function EditRecord(id) {
    var record = data.find(function(item) {
        return item.id === id;
    });

    var editFormHtml = `
         <h2>Edit Unit Record</h2>
         <form id="editForm" name="editForm" class="edit-form">
             <input type="hidden" id="id" name="id" value="${record.id}"><br>
             <label for="editUnitName">Unit Name</label>
             <input type="text" id="editUnitName" name="name" value="${record.name}"><br>
             <input type="hidden" id="tenantId" name="tenantId" value="${record.tenantId}"><br>
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
            type: "PUT",
            url: "/products/admin/updateUnit",
            data: editForm.serialize(),
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
            },
            success: function(result) {
                // Handle success, e.g., update the UI
                alert("Unit updated successfully!");
                $("#editFormContainer").empty().hide();
                $(".container").removeClass("hidden");
                getAllUnit();
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
        getAllUnit();
    });
 }