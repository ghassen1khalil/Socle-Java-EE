//configuration logger js

var logger = LogJS.get({
	name : "MyLog",
	level : "debug",
	enabled : true
});

function showLevel(level) {
	for (var i = 0; i < tab.length; i++) {
		if (LogJS.api.Level.strs[tab[i].level] === level) {
			console[LogJS.api.Level.strs[tab[i].level]](tab[i].msg);
		}
	}
}
