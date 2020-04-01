var time;
function init(){
	time = setInterval("setImage()",3000)
}
function setImage(){
	document.getElementById("image").style.display = "block"
	clearInterval(time)
	setInterval("hideImage()",5000)
}
function hideImage(){
	document.getElementById("image").style.display = "none"
	clearInterval(time)
}