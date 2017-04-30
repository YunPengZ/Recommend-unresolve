function addLoadEvent(func) {
    var oldonload = window.onload;
    if (typeof window.onload != 'function') {
        window.onload = func;
    } else {
        window.onload = function () {
            oldonload();
            func();
        }
    }
}
function getScrollTop() {
    var scrollTop = 0;
    if (document.documentElement && document.documentElement.scrollTop) {
        scrollTop = document.documentElement.scrollTop;
    }
    else if (document.body) {
        scrollTop = document.body.scrollTop;
    }
    return scrollTop;
}

function getClientHeight() {
    var clientHeight = 0;
    if (document.body.clientHeight && document.documentElement.clientHeight) {
        clientHeight = Math.min(document.body.clientHeight, document.documentElement.clientHeight);
        alert(document.documentElement.clientHeight)
    }
    else {
        clientHeight = Math.max(document.body.clientHeight, document.documentElement.clientHeight);
       
    }
    return clientHeight;
}

function getScrollHeight() {
    return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
}
window.onscroll = function () {//设置两边侧边栏的高度
    var elem= document.getElementById("blank_bar");
    var elem_2= document.getElementById("blank_bar_2");
    var height = window.innerHeight- 70;//-20来自底部栏，-50来自顶部导航栏
 
    if (getScrollTop() +document.documentElement.clientHeight== getScrollHeight()) {
        elem.style.height = height + "px";
        elem_2.style.height=height+"px";
    }
    else {
    elem.style.height="100%";
    	elem_2.style.height="100%";
    	}
}



var sdelay = 0;
function returnTop() {
    window.scrollBy(0, -100);//Only for y vertical-axis
    if (document.body.scrollTop > 0) {
        sdelay = setTimeout('returnTop()', 50);
    }
}

