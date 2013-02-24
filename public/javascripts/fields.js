$(document).ready(function() {
	$('#fieldType').change(function () {
		var min = '#min_field';
		var max = '#max_field';
		var target = '#targetValue_field';

		switch($('#fieldType').val()){
			case "1":
			case "2":
				$(min).show();
				$(max).show();
				$(target).hide();
				break;
			case "4":
				$(min).hide();
				$(max).hide();
				$(target).show();
				break;
			default:
				$(min).hide();
				$(max).hide();
				$(target).hide();
		}
	});
});