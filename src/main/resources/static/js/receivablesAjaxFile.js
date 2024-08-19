$(document).ready(function() {
	getAllDueRecords();

});
   //==========================================show table========================================================
var sales = "";

function getAllDueRecords() {
    $.ajax({
        type: "GET",
        url: "/getAllSalesDue", 
        success: function (sales) {
            console.log(sales); 
            salesdata = sales;
            $('#ReceivablesTable').DataTable().destroy();
             $('#Receivables_result').empty();
            $('#due_id').empty();
			var dropdown = $("#due_id");
			dropdown.append('<option value="">Select Receivable</option>');
            for (i = 0; i < sales.length; i++) {
				var bill=sales[i].receptAmount+sales[i].due;
                
                $("#Receivables_result").append(
                    '<tr class="tr">' +
                    '<td>' + sales[i].date + '-'+sales[i].id+ '</td>' +
                    '<td>' + bill + '</td>' +
                    '<td>' + sales[i].due + '</td>' +
                    '<td>' + sales[i].distributor.name + '</td>' +
                    '</tr>'
                );
                
                dropdown.append('<option value="' + sales[i].id + '">' + sales[i].date + '-'+sales[i].id + '</option>');
            }

           
            // Initialize DataTables plugin
            $('#ReceivablesTable').DataTable();
        },
        error: function (err) {
            alert("Error: " + err);
            console.error("Error:", err);
        }
    });
}



$("#insert").click(function() {
	//alert("YYYOOOOOOOOOOOO");
	var form = $("#formReceivables");
	event.preventDefault;
	var formdata = form.serializeArray();
	$.ajax({
		type: "PUT",
		url: "/updateDue",
		data: formdata,
		success: function(response) {

			form[0].reset();
			getAllDueRecords();
		},
		error: function(err) {
			alert("error:" + er);
		}
	});
});

$("#resett").click(function() {
	var form = $("#formReceivables");
	form[0].reset();

});