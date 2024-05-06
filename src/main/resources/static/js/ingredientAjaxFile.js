$(document).ready(function() {
	//getAllIngredients();
	$("#insertIngredient").click(function() {
		// Get the form associated with the clicked button
		var form = $("#formIngredient");
		// Prevent the default form submission
		event.preventDefault();
		console.log("yes");
		// Make the AJAX request
		$.ajax({
			type: "POST",
			url: "/addIngredient",
			data: form.serialize(),
			success: function(result) {
				getAllIngredients();
				$("#formIngredient")[0].reset();
			},
			error: function(err) {
				alert("Error: " + JSON.stringify(err));
			}
		});
	});
});