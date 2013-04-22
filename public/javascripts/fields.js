$(function() {
	var sortable = $('#sortable');
	sortable.sortable({
		axis: 'y',
		cursor: 'move',
		forcePlaceholderSize: true,
		handle: 'h3',
		update: function( event, ui ) {
			renumber();
		}
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
		rename();
	});

	// renumber form fields
	var renumber = function(){
		$('#sortable fieldset').each(function(i) {
			$(this).find('input, textarea, select').each(function() {
				$(this).attr('name', $(this).attr('name').replace(/fields\[.+?\]/g, 'fields[' + i + ']'));
			});
		});
	};

	// rename form fields
	var rename = function(){
		var item = $('#sortable fieldset:last'),
		    index = $('#sortable fieldset').length;
		item.find('input, textarea, select').each(function() {
				$(this).attr('id', $(this).attr('id').replace('fields_x__', 'fields_' + index + '__'));
		});
		item.find('label').each(function() {
				$(this).attr('for', $(this).attr('for').replace('fields_x__', 'fields_' + index + '__'));
		});
	};

	// update field header
	sortable.on('change', '.field_name', function() {
		var that = $(this),
		    value = that.val(),
		    header = that.closest('fieldset').find('h3 .field_header');
		header.text(value);
	});
});