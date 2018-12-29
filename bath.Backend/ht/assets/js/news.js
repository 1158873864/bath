
$("#your-alert-1").hide();
$("#your-alert-2").hide();
$("#your-alert-3").hide();
$("#your-alert-4").hide();
$("#your-alert-5").hide();
$("#your-alert-6").hide();
$("#your-alert-7").hide();
$("#your-alert-8").hide();
$("#your-alert-9").hide();
$("#your-alert-10").hide();
$("#page1").hide();
$("#page2").hide();
$("#page3").hide();
$("#page4").hide();
$("#page5").hide();
var list=new Array();
var firstID=0;
var currentpage=1;
var theGroup=0;
var url=getUrl();
$.ajax(
    {
        method: 'GET',
        url: url+"/getNewsListBefore",
        dataType: 'json',
        data: {
            type:"admin",
            newsId:""
        },
        async:false,
        success: function (data) {
            for(var i=0;i<data.news.length;i++){
                list.push(data.news[i]);
            }
            changepage(1);
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)
$.ajax(
    {
        method: 'GET',
        url: url+"/getNewsNumber",
        dataType: 'json',
        data: {
        },
        async:false,
        success: function (data) {
            document.getElementById("jilu").innerText="共"+(data.number)+"条记录";
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
    storage["updateNews"]=q.newsId;
}
function deletequestion(n){
    var r=confirm("确定删除么？");
    if(r) {
        var q = list[firstID + n];
        var url = getUrl();
        $.ajax(
            {
                url: url + "/deleteNews",
                data: {
                    newsId: q.newsId
                },
                async: false,
                success: function (data) {
                    window.location.href = "news.html";
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
        $.ajax(
            {
                method: 'GET',
                url: url+"/getNewsListBefore",
                dataType: 'json',
                data: {
                    type:"admin",
                    newsId:list[list.length-1].newsId
                },
                async:false,
                success: function (data) {

                    for(var i=0;i<data.news.length;i++){
                        list.push(data.news[i]);
                    }
                },
                error: function (xhr) {
                    alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )
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
    firstID=(theGroup*5+page-1)*10;
    if(list.length<(theGroup*50+41)){
        $("#page5").hide();
        if(list.length<(theGroup*50+31)){
            $("#page4").hide();
            if(list.length<(theGroup*50+21)){
                $("#page3").hide();
                if(list.length<(theGroup*50+11)){
                    $("#page2").hide();
                    if(list.length<(theGroup*50+1)){
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
        $("#your-alert-6").hide();
        $("#your-alert-7").hide();
        $("#your-alert-8").hide();
        $("#your-alert-9").hide();
        $("#your-alert-10").hide();
    }
    else if(list.length<(firstID+2)){
        $("#your-alert-1").show();
        document.getElementById("number"+(firstID%10+1)).innerText=list[firstID].newsId;
        document.getElementById("name"+(firstID%10+1)).innerText=list[firstID].source;
        document.getElementById("date"+(firstID%10+1)).innerText=list[firstID].time;
        $("#your-alert-2").hide();
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
        $("#your-alert-6").hide();
        $("#your-alert-7").hide();
        $("#your-alert-8").hide();
        $("#your-alert-9").hide();
        $("#your-alert-10").hide();
    }
    else if(list.length<(firstID+3)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        document.getElementById("number"+(firstID%10+1)).innerText=list[firstID].newsId;
        document.getElementById("name"+(firstID%10+1)).innerText=list[firstID].source;
        document.getElementById("date"+(firstID%10+1)).innerText=list[firstID].time;
        document.getElementById("number"+(firstID%10+2)).innerText=list[firstID+1].newsId;
        document.getElementById("name"+(firstID%10+2)).innerText=list[firstID+1].source;
        document.getElementById("date"+(firstID%10+2)).innerText=list[firstID+1].time;
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
        $("#your-alert-6").hide();
        $("#your-alert-7").hide();
        $("#your-alert-8").hide();
        $("#your-alert-9").hide();
        $("#your-alert-10").hide();
    }
    else if(list.length<(firstID+4)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        document.getElementById("number"+(firstID%10+1)).innerText=list[firstID].newsId;
        document.getElementById("name"+(firstID%10+1)).innerText=list[firstID].source;
        document.getElementById("date"+(firstID%10+1)).innerText=list[firstID].time;
        document.getElementById("number"+(firstID%10+2)).innerText=list[firstID+1].newsId;
        document.getElementById("name"+(firstID%10+2)).innerText=list[firstID+1].source;
        document.getElementById("date"+(firstID%10+2)).innerText=list[firstID+1].time;
        document.getElementById("number"+(firstID%10+3)).innerText=list[firstID+2].newsId;
        document.getElementById("name"+(firstID%10+3)).innerText=list[firstID+2].source;
        document.getElementById("date"+(firstID%10+3)).innerText=list[firstID+2].time;
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
        $("#your-alert-6").hide();
        $("#your-alert-7").hide();
        $("#your-alert-8").hide();
        $("#your-alert-9").hide();
        $("#your-alert-10").hide();
    }
    else if(list.length<(firstID+5)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        document.getElementById("number"+(firstID%10+1)).innerText=list[firstID].newsId;
        document.getElementById("name"+(firstID%10+1)).innerText=list[firstID].source;
        document.getElementById("date"+(firstID%10+1)).innerText=list[firstID].time;
        document.getElementById("number"+(firstID%10+2)).innerText=list[firstID+1].newsId;
        document.getElementById("name"+(firstID%10+2)).innerText=list[firstID+1].source;
        document.getElementById("date"+(firstID%10+2)).innerText=list[firstID+1].time;
        document.getElementById("number"+(firstID%10+3)).innerText=list[firstID+2].newsId;
        document.getElementById("name"+(firstID%10+3)).innerText=list[firstID+2].source;
        document.getElementById("date"+(firstID%10+3)).innerText=list[firstID+2].time;
        document.getElementById("number"+(firstID%10+4)).innerText=list[firstID+3].newsId;
        document.getElementById("name"+(firstID%10+4)).innerText=list[firstID+3].source;
        document.getElementById("date"+(firstID%10+4)).innerText=list[firstID+3].time;
        $("#your-alert-5").hide();
        $("#your-alert-6").hide();
        $("#your-alert-7").hide();
        $("#your-alert-8").hide();
        $("#your-alert-9").hide();
        $("#your-alert-10").hide();
    }
    else if(list.length<(firstID+6)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        $("#your-alert-5").show();
        document.getElementById("number"+(firstID%10+1)).innerText=list[firstID].newsId;
        document.getElementById("name"+(firstID%10+1)).innerText=list[firstID].source;
        document.getElementById("date"+(firstID%10+1)).innerText=list[firstID].time;
        document.getElementById("number"+(firstID%10+2)).innerText=list[firstID+1].newsId;
        document.getElementById("name"+(firstID%10+2)).innerText=list[firstID+1].source;
        document.getElementById("date"+(firstID%10+2)).innerText=list[firstID+1].time;
        document.getElementById("number"+(firstID%10+3)).innerText=list[firstID+2].newsId;
        document.getElementById("name"+(firstID%10+3)).innerText=list[firstID+2].source;
        document.getElementById("date"+(firstID%10+3)).innerText=list[firstID+2].time;
        document.getElementById("number"+(firstID%10+4)).innerText=list[firstID+3].newsId;
        document.getElementById("name"+(firstID%10+4)).innerText=list[firstID+3].source;
        document.getElementById("date"+(firstID%10+4)).innerText=list[firstID+3].time;
        document.getElementById("number"+(firstID%10+5)).innerText=list[firstID+4].newsId;
        document.getElementById("name"+(firstID%10+5)).innerText=list[firstID+4].source;
        document.getElementById("date"+(firstID%10+5)).innerText=list[firstID+4].time;
        $("#your-alert-6").hide();
        $("#your-alert-7").hide();
        $("#your-alert-8").hide();
        $("#your-alert-9").hide();
        $("#your-alert-10").hide();
    }
    else if(list.length<(firstID+7)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        $("#your-alert-5").show();
        $("#your-alert-6").show();
        document.getElementById("number"+(firstID%10+1)).innerText=list[firstID].newsId;
        document.getElementById("name"+(firstID%10+1)).innerText=list[firstID].source;
        document.getElementById("date"+(firstID%10+1)).innerText=list[firstID].time;
        document.getElementById("number"+(firstID%10+2)).innerText=list[firstID+1].newsId;
        document.getElementById("name"+(firstID%10+2)).innerText=list[firstID+1].source;
        document.getElementById("date"+(firstID%10+2)).innerText=list[firstID+1].time;
        document.getElementById("number"+(firstID%10+3)).innerText=list[firstID+2].newsId;
        document.getElementById("name"+(firstID%10+3)).innerText=list[firstID+2].source;
        document.getElementById("date"+(firstID%10+3)).innerText=list[firstID+2].time;
        document.getElementById("number"+(firstID%10+4)).innerText=list[firstID+3].newsId;
        document.getElementById("name"+(firstID%10+4)).innerText=list[firstID+3].source;
        document.getElementById("date"+(firstID%10+4)).innerText=list[firstID+3].time;
        document.getElementById("number"+(firstID%10+5)).innerText=list[firstID+4].newsId;
        document.getElementById("name"+(firstID%10+5)).innerText=list[firstID+4].source;
        document.getElementById("date"+(firstID%10+5)).innerText=list[firstID+4].time;
        document.getElementById("number"+(firstID%10+6)).innerText=list[firstID+5].newsId;
        document.getElementById("name"+(firstID%10+6)).innerText=list[firstID+5].source;
        document.getElementById("date"+(firstID%10+6)).innerText=list[firstID+5].time;
        $("#your-alert-7").hide();
        $("#your-alert-8").hide();
        $("#your-alert-9").hide();
        $("#your-alert-10").hide();
    }
    else if(list.length<(firstID+8)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        $("#your-alert-5").show();
        $("#your-alert-6").show();
        $("#your-alert-7").show();
        document.getElementById("number"+(firstID%10+1)).innerText=list[firstID].newsId;
        document.getElementById("name"+(firstID%10+1)).innerText=list[firstID].source;
        document.getElementById("date"+(firstID%10+1)).innerText=list[firstID].time;
        document.getElementById("number"+(firstID%10+2)).innerText=list[firstID+1].newsId;
        document.getElementById("name"+(firstID%10+2)).innerText=list[firstID+1].source;
        document.getElementById("date"+(firstID%10+2)).innerText=list[firstID+1].time;
        document.getElementById("number"+(firstID%10+3)).innerText=list[firstID+2].newsId;
        document.getElementById("name"+(firstID%10+3)).innerText=list[firstID+2].source;
        document.getElementById("date"+(firstID%10+3)).innerText=list[firstID+2].time;
        document.getElementById("number"+(firstID%10+4)).innerText=list[firstID+3].newsId;
        document.getElementById("name"+(firstID%10+4)).innerText=list[firstID+3].source;
        document.getElementById("date"+(firstID%10+4)).innerText=list[firstID+3].time;
        document.getElementById("number"+(firstID%10+5)).innerText=list[firstID+4].newsId;
        document.getElementById("name"+(firstID%10+5)).innerText=list[firstID+4].source;
        document.getElementById("date"+(firstID%10+5)).innerText=list[firstID+4].time;
        document.getElementById("number"+(firstID%10+6)).innerText=list[firstID+5].newsId;
        document.getElementById("name"+(firstID%10+6)).innerText=list[firstID+5].source;
        document.getElementById("date"+(firstID%10+6)).innerText=list[firstID+5].time;
        document.getElementById("number"+(firstID%10+7)).innerText=list[firstID+6].newsId;
        document.getElementById("name"+(firstID%10+7)).innerText=list[firstID+6].source;
        document.getElementById("date"+(firstID%10+7)).innerText=list[firstID+6].time;
        $("#your-alert-8").hide();
        $("#your-alert-9").hide();
        $("#your-alert-10").hide();
    }
    else if(list.length<(firstID+9)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        $("#your-alert-5").show();
        $("#your-alert-6").show();
        $("#your-alert-7").show();
        $("#your-alert-8").show();
        document.getElementById("number"+(firstID%10+1)).innerText=list[firstID].newsId;
        document.getElementById("name"+(firstID%10+1)).innerText=list[firstID].source;
        document.getElementById("date"+(firstID%10+1)).innerText=list[firstID].time;
        document.getElementById("number"+(firstID%10+2)).innerText=list[firstID+1].newsId;
        document.getElementById("name"+(firstID%10+2)).innerText=list[firstID+1].source;
        document.getElementById("date"+(firstID%10+2)).innerText=list[firstID+1].time;
        document.getElementById("number"+(firstID%10+3)).innerText=list[firstID+2].newsId;
        document.getElementById("name"+(firstID%10+3)).innerText=list[firstID+2].source;
        document.getElementById("date"+(firstID%10+3)).innerText=list[firstID+2].time;
        document.getElementById("number"+(firstID%10+4)).innerText=list[firstID+3].newsId;
        document.getElementById("name"+(firstID%10+4)).innerText=list[firstID+3].source;
        document.getElementById("date"+(firstID%10+4)).innerText=list[firstID+3].time;
        document.getElementById("number"+(firstID%10+5)).innerText=list[firstID+4].newsId;
        document.getElementById("name"+(firstID%10+5)).innerText=list[firstID+4].source;
        document.getElementById("date"+(firstID%10+5)).innerText=list[firstID+4].time;
        document.getElementById("number"+(firstID%10+6)).innerText=list[firstID+5].newsId;
        document.getElementById("name"+(firstID%10+6)).innerText=list[firstID+5].source;
        document.getElementById("date"+(firstID%10+6)).innerText=list[firstID+5].time;
        document.getElementById("number"+(firstID%10+7)).innerText=list[firstID+6].newsId;
        document.getElementById("name"+(firstID%10+7)).innerText=list[firstID+6].source;
        document.getElementById("date"+(firstID%10+7)).innerText=list[firstID+6].time;
        document.getElementById("number"+(firstID%10+8)).innerText=list[firstID+7].newsId;
        document.getElementById("name"+(firstID%10+8)).innerText=list[firstID+7].source;
        document.getElementById("date"+(firstID%10+8)).innerText=list[firstID+7].time;

        $("#your-alert-9").hide();
        $("#your-alert-10").hide();
    }
    else if(list.length<(firstID+10)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        $("#your-alert-5").show();
        $("#your-alert-6").show();
        $("#your-alert-7").show();
        $("#your-alert-8").show();
        $("#your-alert-9").show();
        document.getElementById("number"+(firstID%10+1)).innerText=list[firstID].newsId;
        document.getElementById("name"+(firstID%10+1)).innerText=list[firstID].source;
        document.getElementById("date"+(firstID%10+1)).innerText=list[firstID].time;
        document.getElementById("number"+(firstID%10+2)).innerText=list[firstID+1].newsId;
        document.getElementById("name"+(firstID%10+2)).innerText=list[firstID+1].source;
        document.getElementById("date"+(firstID%10+2)).innerText=list[firstID+1].time;
        document.getElementById("number"+(firstID%10+3)).innerText=list[firstID+2].newsId;
        document.getElementById("name"+(firstID%10+3)).innerText=list[firstID+2].source;
        document.getElementById("date"+(firstID%10+3)).innerText=list[firstID+2].time;
        document.getElementById("number"+(firstID%10+4)).innerText=list[firstID+3].newsId;
        document.getElementById("name"+(firstID%10+4)).innerText=list[firstID+3].source;
        document.getElementById("date"+(firstID%10+4)).innerText=list[firstID+3].time;
        document.getElementById("number"+(firstID%10+5)).innerText=list[firstID+4].newsId;
        document.getElementById("name"+(firstID%10+5)).innerText=list[firstID+4].source;
        document.getElementById("date"+(firstID%10+5)).innerText=list[firstID+4].time;
        document.getElementById("number"+(firstID%10+6)).innerText=list[firstID+5].newsId;
        document.getElementById("name"+(firstID%10+6)).innerText=list[firstID+5].source;
        document.getElementById("date"+(firstID%10+6)).innerText=list[firstID+5].time;
        document.getElementById("number"+(firstID%10+7)).innerText=list[firstID+6].newsId;
        document.getElementById("name"+(firstID%10+7)).innerText=list[firstID+6].source;
        document.getElementById("date"+(firstID%10+7)).innerText=list[firstID+6].time;
        document.getElementById("number"+(firstID%10+8)).innerText=list[firstID+7].newsId;
        document.getElementById("name"+(firstID%10+8)).innerText=list[firstID+7].source;
        document.getElementById("date"+(firstID%10+8)).innerText=list[firstID+7].time;
        document.getElementById("number"+(firstID%10+9)).innerText=list[firstID+8].newsId;
        document.getElementById("name"+(firstID%10+9)).innerText=list[firstID+8].source;
        document.getElementById("date"+(firstID%10+9)).innerText=list[firstID+8].time;

        $("#your-alert-10").hide();
    }
    else{
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        $("#your-alert-5").show();
        $("#your-alert-6").show();
        $("#your-alert-7").show();
        $("#your-alert-8").show();
        $("#your-alert-9").show();
        $("#your-alert-10").show();
        document.getElementById("number"+(firstID%10+1)).innerText=list[firstID].newsId;
        document.getElementById("name"+(firstID%10+1)).innerText=list[firstID].source;
        document.getElementById("date"+(firstID%10+1)).innerText=list[firstID].time;
        document.getElementById("number"+(firstID%10+2)).innerText=list[firstID+1].newsId;
        document.getElementById("name"+(firstID%10+2)).innerText=list[firstID+1].source;
        document.getElementById("date"+(firstID%10+2)).innerText=list[firstID+1].time;
        document.getElementById("number"+(firstID%10+3)).innerText=list[firstID+2].newsId;
        document.getElementById("name"+(firstID%10+3)).innerText=list[firstID+2].source;
        document.getElementById("date"+(firstID%10+3)).innerText=list[firstID+2].time;
        document.getElementById("number"+(firstID%10+4)).innerText=list[firstID+3].newsId;
        document.getElementById("name"+(firstID%10+4)).innerText=list[firstID+3].source;
        document.getElementById("date"+(firstID%10+4)).innerText=list[firstID+3].time;
        document.getElementById("number"+(firstID%10+5)).innerText=list[firstID+4].newsId;
        document.getElementById("name"+(firstID%10+5)).innerText=list[firstID+4].source;
        document.getElementById("date"+(firstID%10+5)).innerText=list[firstID+4].time;
        document.getElementById("number"+(firstID%10+6)).innerText=list[firstID+5].newsId;
        document.getElementById("name"+(firstID%10+6)).innerText=list[firstID+5].source;
        document.getElementById("date"+(firstID%10+6)).innerText=list[firstID+5].time;
        document.getElementById("number"+(firstID%10+7)).innerText=list[firstID+6].newsId;
        document.getElementById("name"+(firstID%10+7)).innerText=list[firstID+6].source;
        document.getElementById("date"+(firstID%10+7)).innerText=list[firstID+6].time;
        document.getElementById("number"+(firstID%10+8)).innerText=list[firstID+7].newsId;
        document.getElementById("name"+(firstID%10+8)).innerText=list[firstID+7].source;
        document.getElementById("date"+(firstID%10+8)).innerText=list[firstID+7].time;
        document.getElementById("number"+(firstID%10+9)).innerText=list[firstID+8].newsId;
        document.getElementById("name"+(firstID%10+9)).innerText=list[firstID+8].source;
        document.getElementById("date"+(firstID%10+9)).innerText=list[firstID+8].time;
        document.getElementById("number"+(firstID%10+10)).innerText=list[firstID+9].newsId;
        document.getElementById("name"+(firstID%10+10)).innerText=list[firstID+9].source;
        document.getElementById("date"+(firstID%10+10)).innerText=list[firstID+9].time;

    }


}

function deletesingle(n){
    var q=list[firstID+n];
    var url=getUrl();
    $.ajax(
        {
            url: url + "/deleteNews",
            data: {
                newsId: q.newsId
            },
            async: false,
            success: function (data) {
                window.location.href = "news.html";
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )
}
function delAll(){
    var r=confirm("确定删除么？");
    if(r) {
        if (document.getElementById("c1").checked) {
            deletesingle(0);
        }
        if (document.getElementById("c2").checked) {
            deletesingle(1);
        }
        if (document.getElementById("c3").checked) {
            deletesingle(2);
        }
        if (document.getElementById("c4").checked) {
            deletesingle(3);
        }
        if (document.getElementById("c5").checked) {
            deletesingle(4);
        }
        if (document.getElementById("c6").checked) {
            deletesingle(5);
        }
        if (document.getElementById("c7").checked) {
            deletesingle(6);
        }
        if (document.getElementById("c8").checked) {
            deletesingle(7);
        }
        if (document.getElementById("c9").checked) {
            deletesingle(8);
        }
        if (document.getElementById("c10").checked) {
            deletesingle(9);
        }
        alert("批量删除成功");
        window.location.href = "news.html";
    }

}

function search(){
    var text=$("#con").val();
    for(var i=0;i<list.length;i++){
        if(list[i].newsId==text){
            $("#your-alert-1").show();
            document.getElementById("number"+(firstID%10+1)).innerText=list[i].newsId;
            document.getElementById("name"+(firstID%10+1)).innerText=list[i].source;
            document.getElementById("date"+(firstID%10+1)).innerText=list[i].time;
            $("#your-alert-2").hide();
            $("#your-alert-3").hide();
            $("#your-alert-4").hide();
            $("#your-alert-5").hide();
            $("#your-alert-6").hide();
            $("#your-alert-7").hide();
            $("#your-alert-8").hide();
            $("#your-alert-9").hide();
            $("#your-alert-10").hide();
            firstID=i-1;
        }
    }

}


