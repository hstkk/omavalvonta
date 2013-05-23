$(function() {
	// Check all checkboxes
	$('#checkAll:checkbox').change(function() {
		$("input[type='checkbox']").attr('checked', $(this).is(':checked'));
	});
});
