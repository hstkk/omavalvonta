$(function() {
	// Check all checkboxes
	$('.checkAll').on('click', function() {
		$("input[type='checkbox']").attr('checked', $('.checkAll').is(':checked'));
	});
});