$("#your-alert-1").hide();
$("#your-alert-2").hide();
$("#your-alert-3").hide();
$("#your-alert-4").hide();
$("#your-alert-5").hide();
$("#page1").hide();
$("#page2").hide();
$("#page3").hide();
$("#page4").hide();
$("#page5").hide();
var list=new Array();
var firstID=0;
var theGroup=0;
var url=getUrl();
$.ajax(
    {
        url: url+"/getUserList",
        data: {
        },
        async:false,
        success: function (data) {
            for(var i=0;i<data.users.length;i++){
                list.push(data.users[i]);
            }
            document.getElementById("jilu").innerText="共"+(list.length)+"条记录";
            changepage(1);
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

function setthisquestion(n){
    var q=list[firstID+n];
    var storage = window.localStorage;
    storage["thisUser"]=q.openid;
}
function deletequestion(n){
    var r=confirm("确定删除么？");
    if(r) {
        var q = list[firstID + n];
        var url = getUrl();
        $.ajax(
            {
                url: url + "/deleteUser",
                data: {
                    openid: q.openid
                },
                async: false,
                success: function (data) {
                    alert("删除成功");
                    window.location.href = "vip-list.html";
                },
                error: function (xhr) {
                    alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )
    }
}



function changegroup(to){
    $("#page1").show();
    $("#page2").show();
    $("#page3").show();
    $("#page4").show();
    $("#page5").show();
    if(to==1){
        if(theGroup!=0){
            theGroup=theGroup-1;
        }
    }
    else{
        theGroup=theGroup+1;

    }
    document.getElementById("page1").innerText=theGroup*5+1;
    document.getElementById("page2").innerText=theGroup*5+2;
    document.getElementById("page3").innerText=theGroup*5+3;
    document.getElementById("page4").innerText=theGroup*5+4;
    document.getElementById("page5").innerText=theGroup*5+5;


    changepage(1);
}
function changepage(page){
    $("#page1").show();
    $("#page2").show();
    $("#page3").show();
    $("#page4").show();
    $("#page5").show();
    firstID=(theGroup*5+page-1)*5;
    if(list.length<(theGroup*25+21)){
        $("#page5").hide();
        if(list.length<(theGroup*25+16)){
            $("#page4").hide();
            if(list.length<(theGroup*25+11)){
                $("#page3").hide();
                if(list.length<(theGroup*25+6)){
                    $("#page2").hide();
                    if(list.length<(theGroup*25+1)){
                        $("#page1").hide();
                    }
                }
            }
        }

    }
    if(list.length<(firstID+1)){
        $("#your-alert-1").hide();
        $("#your-alert-2").hide();
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+2)){
        $("#your-alert-1").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID].openid;
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].username;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].levelName;
        $("#your-alert-2").hide();
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+3)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID].openid;
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].username;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].levelName;
        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1].openid;
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1].username;
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1].levelName;
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+4)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID].openid;
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].username;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].levelName;
        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1].openid;
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1].username;
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1].levelName;
        document.getElementById("number"+(firstID%5+3)).innerText=list[firstID+2].openid;;
        document.getElementById("name"+(firstID%5+3)).innerText=list[firstID+2].username;
        document.getElementById("date"+(firstID%5+3)).innerText=list[firstID+2].levelName;
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+5)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID].openid;
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].username;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].levelName;
        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1].openid;
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1].username;
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1].levelName;
        document.getElementById("number"+(firstID%5+3)).innerText=list[firstID+2].openid;;
        document.getElementById("name"+(firstID%5+3)).innerText=list[firstID+2].username;
        document.getElementById("date"+(firstID%5+3)).innerText=list[firstID+2].levelName;
        document.getElementById("number"+(firstID%5+4)).innerText=list[firstID+3].openid;;
        document.getElementById("name"+(firstID%5+4)).innerText=list[firstID+3].username;
        document.getElementById("date"+(firstID%5+4)).innerText=list[firstID+3].levelName;
        $("#your-alert-5").hide();
    }
    else{
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        $("#your-alert-5").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID].openid;
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].username;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].levelName;
        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1].openid;
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1].username;
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1].levelName;
        document.getElementById("number"+(firstID%5+3)).innerText=list[firstID+2].openid;;
        document.getElementById("name"+(firstID%5+3)).innerText=list[firstID+2].username;
        document.getElementById("date"+(firstID%5+3)).innerText=list[firstID+2].levelName;
        document.getElementById("number"+(firstID%5+4)).innerText=list[firstID+3].openid;;
        document.getElementById("name"+(firstID%5+4)).innerText=list[firstID+3].username;
        document.getElementById("date"+(firstID%5+4)).innerText=list[firstID+3].levelName;
        document.getElementById("number"+(firstID%5+5)).innerText=list[firstID+4].openid;;
        document.getElementById("name"+(firstID%5+5)).innerText=list[firstID+4].username;
        document.getElementById("date"+(firstID%5+5)).innerText=list[firstID+4].levelName;
    }


}

function deletesingle(n){
    var q=list[firstID+n];
    var url=getUrl();
    $.ajax(
        {
            url: url+"/deleteUser",
            data: {
                openid:q.openid
            },
            async:false,
            success: function (data) {
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )
}
function delAll(){
    if(document.getElementById("c1").checked){
        deletesingle(1);
    }
    if(document.getElementById("c2").checked){
        deletesingle(2);
    }
    if(document.getElementById("c3").checked){
        deletesingle(3);
    }
    if(document.getElementById("c4").checked){
        deletesingle(4);
    }
    if(document.getElementById("c5").checked){
        deletesingle(5);
    }
    alert("批量删除成功");
    window.location.href="vip-list.html";

}

function search(){
    var text=$("#con").val();
    for(var i=0;i<list.length;i++){
        if(list[i].openid==text){
            $("#your-alert-1").show();
            document.getElementById("number"+(firstID%5+1)).innerText=list[i].openid;
            document.getElementById("name"+(firstID%5+1)).innerText=list[i].username;
            document.getElementById("date"+(firstID%5+1)).innerText=list[i].levelName;
            $("#your-alert-2").hide();
            $("#your-alert-3").hide();
            $("#your-alert-4").hide();
            $("#your-alert-5").hide();
            firstID=i-1;
        }
    }

}


