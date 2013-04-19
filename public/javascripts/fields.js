$(function() {
	var sortable = $('#sortable');
	sortable.sortable({
		placeholder: "ui-state-highlight"
	});
	sortable.disableSelection();

	$('.addField').live('click', function(e) {
		var template = $('.field_template');
		sortable.append(template.html());
		renumber();
	});

var renumber = function(){

};
});