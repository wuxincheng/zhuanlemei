
function checkDataAndReload(data){
	var htmlRegex = /<[a-z\][\s\S]*>/;
	var flag = htmlRegex.test(data);
	if (flag) {
		window.top.location.reload();
	} else {
		return flag;
	}
}

function checkAjaxResponse(data){
	var htmlRegex = /<[a-z\][\s\S]*>/;
	return htmlRegex.test(data);
}
