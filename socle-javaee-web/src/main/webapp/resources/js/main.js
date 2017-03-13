var bull = bull || {};
var $ = jQuery;

/*
 * INIT
 * --------------------------------------------------------------------------------------------------------------------------------------
 */
bull.init = function() {
	heightWindow();
	log4js();
	toggleTools();
	console.clear();
};

/*
 * DOM READY
 * --------------------------------------------------------------------------------------------------------------------------------------
 */
$(document).ready(bull.init);

/*--------------------------------------------------------------------------------------------------------------------------------------*/
/*
 * plugins
 * /*--------------------------------------------------------------------------------------------------------------------------------------
 */

/* 1----HeightWindow ----- */
/* 2----log4js ----------- */
/* 3----ToggleTools ------ */

/*-1-HeightWindow -----------------------------------------------------------------------------------------------------------------------*/
function heightWindow() {
	x = $(window).height() - 50 - 70 - 60;
	$(".page-wrap").css('height', x);
}

/*-2-log4js -----------------------------------------------------------------------------------------------------------------------*/

function log4js() {
	var sel = document.getElementById('idLevelogger');
	$('#idLevelogger').on('change', function() {
		VarSelection = sel.value;
		switch (VarSelection) {
		case 'debug':
			showLevel("debug");
			break;

		case 'info':
			showLevel("info");
			break;

		case 'warn':
			showLevel("warn");
			break;

		case 'error':
			showLevel("error");
			break;

		default:
			showLevel("fatal");
		}
	});
}

/*-3-ToggleTools -----------------------------------------------------------------------------------------------------------------------*/
function toggleTools() {
	$('.Levelogger h3').on('click', function() {
		$(this).parents('.Levelogger').toggleClass('active');
		$(this).next().slideToggle("fast");
	});
};