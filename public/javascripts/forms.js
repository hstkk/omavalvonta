$(document).ready(function() {

	var $sortable = $("#sortable");
	
	$('#_fieldset').change(function () {
		var option = $("#_fieldset option:selected");
   		addToSortable(option);
		option.remove();
	});

	$sortable
		.sortable()
		.disableSelection();

	var addToSortable function(option) {
		var text = option.text(),
		    val = option.val();

		$sortable.append(
			'<label class="checkbox">' +
			'<input type="checkbox" disabled="disabled" checked="checked" value="' +
			val +
			'"> ' +
			text +
			' &#x25B2;&#x25BC;</label>'
		);
	}
});