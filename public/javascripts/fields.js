$(function() {
	var sortable = $('#sortable');
	sortable.sortable({
		axis: 'y',
		cursor: 'move',
		forcePlaceholderSize: true,
		handle: 'h3',
		update: renumber
	});
	sortable.disableSelection();

	sortable.accordion({
		collapsible: true,
		header: '> fieldset > h3'
	});

	// add form fields
	$('.addField').on('click', function() {
		var template = $('.field_template');
		sortable.append(template.html());
		sortable.accordion('refresh');
		sortable.accordion('option', 'active', -1);
		renumber();
	});

	// renumber form fields
	var renumber = function(){
	
	};

	// update field header
	sortable.on('change', '.field_name', function() {
		var that = $(this),
		    value = that.val(),
		    header = that.closest('fieldset').find('h3 .field_header');
		header.text(value);
	});
});