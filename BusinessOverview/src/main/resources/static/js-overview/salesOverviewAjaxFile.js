$(document).ready(function() {
	getSalesOverview();
});
var salesOverviewData;
function getSalesOverview() {
	$.ajax({
		url: "/orders/admin/getSalesOverview",
		success: function(salesOverview) {
			salesOverviewData = salesOverview;
			console.log(salesOverview);
			var total = 0;
			var totalDue = 0;
			var totalThisMonth = 0;
			var totalDueThisMonth = 0;
			var totalThisYear = 0;
			var totalDueThisYear = 0;
			var salesData = {};
			var currentDate = new Date();
			var currentMonth = currentDate.getMonth() + 1;
			var currentYear = currentDate.getFullYear()
			for (i = 0; i < salesOverview.length; i++) {
				total += salesOverview[i].receptAmount;
				totalDue += salesOverview[i].due;
				var saleDate = new Date(salesOverview[i].date);
				var saleMonth = saleDate.getMonth() + 1;
				var saleYear = saleDate.getFullYear();

				if (saleMonth === currentMonth && saleYear === currentYear) {
					totalThisMonth += salesOverview[i].receptAmount;
					totalDueThisMonth += salesOverview[i].due;
				}
				if (saleYear === currentYear) {
					totalThisYear += salesOverview[i].receptAmount;
					totalDueThisYear += salesOverview[i].due;
				}
				var monthYear = saleYear + '-' + (saleMonth < 10 ? '0' + saleMonth : saleMonth);
				if (!salesData[monthYear]) {
					salesData[monthYear] = 0;
				}
				salesData[monthYear] += salesOverview[i].receptAmount;
			}
			$('.LifetimeSales').empty().append(
				'<p>৳ ' + total + '</p>'
			);
			$('.totalDue').empty().append(
				'<p>৳ ' + totalDue + '</p>'
			);
			$('.totalThisMonth').empty().append(
				'<p>৳ ' + totalThisMonth + '</p>'
			);
			$('.totalDueThisMonth').empty().append(
				'<p>৳ ' + totalDueThisMonth + '</p>'
			);
			$('.totalThisYear').empty().append(
				'<p>৳ ' + totalThisYear + '</p>'
			);
			$('.totalDueThisYear').empty().append(
				'<p>৳ ' + totalDueThisYear + '</p>'
			);

			// Data for chart
			var labels = Object.keys(salesData).sort();
			var data = labels.map(function(label) {
				return salesData[label];
			});

			// Create chart
			var ctx = document.getElementById('salesChart').getContext('2d');
			var salesChart = new Chart(ctx, {
				type: 'bar',
				data: {
					labels: labels,
					datasets: [{
						label: 'Sales Amount',
						data: data,
						backgroundColor: 'rgba(75, 192, 192, 0.2)',
						borderColor: 'rgba(75, 192, 192, 1)',
						borderWidth: 1
					}]
				},
				options: {
					scales: {
						y: {
							beginAtZero: true
						}
					}
				}
			});
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

	var filteredTotalSales = 0;
	var filteredTotalDue = 0;

	for (var i = 0; i < salesOverviewData.length; i++) {
		var saleDate = new Date(salesOverviewData[i].date);
		if (saleDate >= startDate && saleDate <= endDate) {
			filteredTotalSales += salesOverviewData[i].receptAmount;
			filteredTotalDue += salesOverviewData[i].due;
		}
	}

	// Update the filtered stats
	$('#filteredTotalSales').empty().append('৳ ' + filteredTotalSales);
	$('#filteredTotalDue').empty().append('৳ ' + filteredTotalDue);
});