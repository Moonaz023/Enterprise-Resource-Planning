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

function getAllUnit() {
	$.ajax({
		type: "GET",
		url: "/products/admin/getAllUnits",
		headers: {
			'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
		},
		success: function(response) {

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