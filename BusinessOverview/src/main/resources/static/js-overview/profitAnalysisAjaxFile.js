$(document).ready(function() {
	getSalesOverview();
	getOperatingProfit();
});

function getSalesOverview() {
	$.ajax({
		url: "/overview/admin/getLifetimeProfitData",
		headers: {
			'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
		},
		success: function(profitData) {
			console.log(profitData);

			$('#filteredTotalSales').empty().append(
				'<p>৳ ' + profitData.sales + '</p>'
			);
			$('#filteredTotalCOGS').empty().append(
				'<p>৳ ' + profitData.cogs + '</p>'
			);
			$('#filteredTotalGP').empty().append(
				'<p>৳ ' + profitData.gp + '</p>'
			);

		},
		error: function(e) {
			alert(e);
		}
	});
}


function getOperatingProfit() {
	$.ajax({
		url: "/overview/admin/getOperatingProfit",
		headers: {
			'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
		},
		success: function(O_profitData) {


			$('#filteredGrossProfit').empty().append(
				'<p>৳ ' + O_profitData.grossProfit + '</p>'
			);
			$('#filteredOperatingExpenses').empty().append(
				'<p>৳ ' + O_profitData.operatingExpenses + '</p>'
			);
			$('#filteredOperatingProfit').empty().append(
				'<p>৳ ' + O_profitData.operatingProfit + '</p>'
			);

		},
		error: function(e) {
			alert(e);
		}
	});
}


$('#filterButton').on('click', function() {
	var startDate = new Date($('#startDate').val());
	var endDate = new Date($('#endDate').val());

	if (isNaN(startDate.getTime()) || isNaN(endDate.getTime())) {
		alert("Please select valid start and end dates.");
		return;
	}
	var form = $("#filterForm");
	// Prevent the default form submission
	event.preventDefault();
	$.ajax({
		type: "POST",
		url: "/overview/admin/getFilteredProfitData",
		data: form.serialize(),
		headers: {
			'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
		},
		success: function(profitData) {
			console.log(profitData);

			$('#filteredTotalSales').empty().append(
				'<p>৳ ' + profitData.sales + '</p>'
			);
			$('#filteredTotalCOGS').empty().append(
				'<p>৳ ' + profitData.cogs + '</p>'
			);
			$('#filteredTotalGP').empty().append(
				'<p>৳ ' + profitData.gp + '</p>'
			);






		},
		error: function(e) {
			alert(e);
		}
	});



});

$('#filterButton2').on('click', function() {
	var startDate = new Date($('#startDate').val());
	var endDate = new Date($('#endDate').val());

	if (isNaN(startDate.getTime()) || isNaN(endDate.getTime())) {
		alert("Please select valid start and end dates.");
		return;
	}
	var form = $("#filterFormOperatingProfit");
	// Prevent the default form submission
	event.preventDefault();
	$.ajax({
		type: "POST",
		url: "/overview/admin/getFilteredOperatingProfit",
		headers: {
			'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`
		},
		data: form.serialize(),
		success: function(profitData) {
			console.log(profitData);

			$('#filteredGrossProfit').empty().append(
				'<p>৳ ' + profitData.grossProfit + '</p>'
			);
			$('#filteredOperatingExpenses').empty().append(
				'<p>৳ ' + profitData.operatingExpenses + '</p>'
			);
			$('#filteredOperatingProfit').empty().append(
				'<p>৳ ' + profitData.operatingProfit + '</p>'
			);

		},
		error: function(e) {
			alert(e);
		}
	});

});