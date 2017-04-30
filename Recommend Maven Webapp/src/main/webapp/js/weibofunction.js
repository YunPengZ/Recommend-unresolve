function checkUsernameIsValid(str){
	var regExpression= /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{2,15}$/;
	if(!regExpression.test(str)){
		return false;
	}
	return true;
}
function checkPasswordIsValid(str){
	var regExpression= /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,15}$/;
	if(!regExpression.test(str)){
		return false;
	}
	return true;
}
function checkTelIsValid(str){
	var regExpression= /^\d{11}$/;

	
	if(!regExpression.test(str)){
		return false;
	}
	return true;
}
function checkEmailIsValid(str){
	var regExpression=/\w+([-+.]\w)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	if(!regExpression.test(str)){
		return false;
	}
	return true;
}