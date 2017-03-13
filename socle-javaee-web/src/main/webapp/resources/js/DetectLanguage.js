/*-1-update langue-----------------------------------------------------------------------------------------------------------------------*/
function updateArabe() {
	if ($("select[name$='lang_fix_input'] option:selected").val() === 'ar') {
		$("#lang_id").addClass("arabe");
		$("#loginForm").addClass("arabe");

		logger.debug("Test, debug code javascript");
		$("body").addClass("ar");
	} else {
		$("#lang_id").removeClass("arabe");
		$("#loginForm").removeClass("arabe");
		$("body").removeClass("ar");
	}
}

/*-2-init langue-----------------------------------------------------------------------------------------------------------------------*/
function initlang() {
	d = document.getElementsByClassName("selectLanguage");
	$(".selectLanguage").removeClass("ar");
	$(".selectLanguage").removeClass("fr");
	$(".selectLanguage").removeClass("en");
	langue = $("select[name$='lang_fix_input'] option:selected").val();
	d = document.getElementsByClassName("clearfix");
	$(".selectLanguage").addClass(langue);
	logger.warn("Test, debug code javascript");

}

//A $( document ).ready() block.
$(document).ready(function() {
	updateArabe();
	initlang();
	console.clear();
});