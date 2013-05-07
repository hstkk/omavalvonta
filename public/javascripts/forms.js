$(function() {
	var sortable = $('#sortable .controls');
	sortable.sortable({
		axis: 'y',
		cursor: 'move',
		forcePlaceholderSize: true,
		placeholder: 'ui-state-highlight',
		update: function( event, ui ) {
			renumber();
		}
	});
	sortable.disableSelection();

	// renumber form fields
	var renumber = function(){
		sortable.find('input').each(function(i) {
console.log(i);
			$(this).attr('name', $(this).attr('name').replace(/\[.+?\]/g, '[' + i + ']'));
		});
	};
});