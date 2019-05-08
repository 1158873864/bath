//var address = "http://localhost:8080";
// var address = "https://junrongcenter.com:3389";
var address = "https://junrongcenter.com:8080";
function getUrl() {
    return address;
}

function getToken(){
    var storage = window.localStorage;
    var token=storage["token"];
    return token;
}
