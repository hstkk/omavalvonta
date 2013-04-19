$(function() {
	$( "#sortable" ).sortable({
		placeholder: "ui-state-highlight"
	});
	$( "#sortable" ).disableSelection();

	$('.addField').live('click', function(e) {
		var template = $('.field_template');
		var actions = $(.'form-actions');
		actions.before(template.html());
	});
});