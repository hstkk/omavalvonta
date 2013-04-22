$(function() {
	$('head').append('<link rel="stylesheet" href="/assets/stylesheets/jquery.uix.multiselect.css" type="text/css" />');

$.uix.multiselect.i18n['fi'] = {
    itemsSelected_nil: 'Valitut',
    itemsSelected: 'Valitut',
    itemsSelected_plural: 'Valitut',
    itemsAvailable_nil: 'Valitsemattomat',
    itemsAvailable: 'Valitsemattomat',
    itemsAvailable_plural: 'Valitsemattomat',
    selectAll: 'Valitse kaikki',
    deselectAll: 'Poista valinnat'
};
	$('.multiselect').multiselect({
		collapsableGroups: false,
		locale: 'fi',
		searchField: false,
		sortable: true
	});
});
