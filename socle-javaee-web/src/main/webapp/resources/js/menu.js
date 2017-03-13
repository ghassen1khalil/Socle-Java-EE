/*-1-ActiveMenu-----------------------------------------------------------------------------------------------------------------------*/
function activeMenu() {
	$('.ui-menuitem-link').each(function() {
		if (window.location.pathname.indexOf($(this).attr('href')) !== -1) {
			$(this).css('background', '#1ABC9C');
			$(this).css('color', 'white');
			$(this).addClass("hola");
			$('.sousmenu_position').each(function() {
				if (this.innerHTML.indexOf('hola') !== -1) {
					$(this).addClass('active_menu');
				}
			})
		}
	});
}

/*-1-ToggleMenu-----------------------------------------------------------------------------------------------------------------------*/
function toggleMenu() {
	logger.warn("Test, warn code javascript");
	$('#menu-btn').click("slow", function() {
		$('aside').slideToggle();
		logger.error("Test, error code javascript");

	});

}

// A $( document ).ready() block.
$(document).ready(function() {
	activeMenu();
	toggleMenu();
	console.clear();

});