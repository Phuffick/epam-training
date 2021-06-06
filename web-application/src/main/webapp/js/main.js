var startMessage = null;
var dropClicked = false;
var currentMenu = null;

window.onload = function() {
	var menus = document.getElementById("header").getElementsByClassName("drop");
	for(var index = 0, size = menus.length; index < size; index++) {
		menus[index].style.display = "none";
		menus[index].parentNode.getElementsByTagName("a")[0].onclick = dropMenu;
	}
	var lists = document.getElementById("page").getElementsByClassName("drop");
	for(var index = 0, size = lists.length; index < size; index++) {
		var element = lists[index];
		if(element.tagName === "ul" || element.tagName === "div") {
			var parent = element.parentNode;
			child.onclick = dropMenu;
			child.style.cursor = "pointer";
			parent.insertBefore(child, parent.firstChild);
			element.style.display = "none";
		}
	}
	if(startMessage !== null) {
		show(startMessage);
	}
}

window.onclick = function() {
	if(currentMenu != null) {
		if(!dropClicked) {
			currentMenu.style.display = "none";
			currentMenu = null;
		} else {
			dropClicked = false;
		}
	}
}

function dropMenu(e) {
	var menu = e.currentTarget.parentNode.getElementsByClassName("drop")[0];
	if(menu.style.display === "none") {
		if(currentMenu != null && menu !== currentMenu) {
			currentMenu.style.display = "none";
		}
		menu.style.display = "block";
		currentMenu = menu;
		dropClicked = true;
	}
	return false;
}
