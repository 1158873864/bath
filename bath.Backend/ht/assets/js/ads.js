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
        url: url+"/getAdList",
        data: {
        },
        async:false,
        success: function (data) {
            for(var i=0;i<data.ads.length;i++){
                if(data.ads[i].showPlace=="index"){
                    data.ads[i].showPlace="首页";
                }
                else if(data.ads[i].showPlace=="service"){
                    data.ads[i].showPlace="业务";
                }
                else if(data.ads[i].showPlace=="jump"){
                    data.ads[i].showPlace="弹窗";
                }
                list.push(data.ads[i]);
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
    storage["thisAd"]=q.id;
}
function deletequestion(n){
    var r=confirm("确定删除么？");
    if(r) {
        var q = list[firstID + n];
        var url = getUrl();
        $.ajax(
            {
                url: url + "/deleteAd",
                data: {
                    id: q.id
                },
                async: false,
                success: function (data) {
                    alert("删除成功");
                    window.location.href = "ads.html";
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
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID].id;
        document.getElementById("name"+(firstID%5+1)).src="../"+list[firstID].image;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].link;
        document.getElementById("place"+(firstID%5+1)).innerText=list[firstID].showPlace;

        $("#your-alert-2").hide();
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+3)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID].id;
        document.getElementById("name"+(firstID%5+1)).src="../"+list[firstID].image;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].link;
        document.getElementById("place"+(firstID%5+1)).innerText=list[firstID].showPlace;

        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1].id;
        document.getElementById("name"+(firstID%5+2)).src="../"+list[firstID+1].image;
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1].link;
        document.getElementById("place"+(firstID%5+2)).innerText=list[firstID+1].showPlace;
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+4)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID].id;
        document.getElementById("name"+(firstID%5+1)).src="../"+list[firstID].image;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].link;
        document.getElementById("place"+(firstID%5+1)).innerText=list[firstID].showPlace;

        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1].id;
        document.getElementById("name"+(firstID%5+2)).src="../"+list[firstID+1].image;
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1].link;
        document.getElementById("place"+(firstID%5+2)).innerText=list[firstID+1].showPlace;

        document.getElementById("number"+(firstID%5+3)).innerText=list[firstID+2].id;
        document.getElementById("name"+(firstID%5+3)).src="../"+list[firstID+2].image;
        document.getElementById("date"+(firstID%5+3)).innerText=list[firstID+2].link;
        document.getElementById("place"+(firstID%5+3)).innerText=list[firstID+2].showPlace;
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+5)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID].id;
        document.getElementById("name"+(firstID%5+1)).src="../"+list[firstID].image;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].link;
        document.getElementById("place"+(firstID%5+1)).innerText=list[firstID].showPlace;

        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1].id;
        document.getElementById("name"+(firstID%5+2)).src="../"+list[firstID+1].image;
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1].link;
        document.getElementById("place"+(firstID%5+2)).innerText=list[firstID+1].showPlace;

        document.getElementById("number"+(firstID%5+3)).innerText=list[firstID+2].id;
        document.getElementById("name"+(firstID%5+3)).src="../"+list[firstID+2].image;
        document.getElementById("date"+(firstID%5+3)).innerText=list[firstID+2].link;
        document.getElementById("place"+(firstID%5+3)).innerText=list[firstID+2].showPlace;

        document.getElementById("number"+(firstID%5+4)).innerText=list[firstID+3].id;
        document.getElementById("name"+(firstID%5+4)).src="../"+list[firstID+3].image;
        document.getElementById("date"+(firstID%5+4)).innerText=list[firstID+3].link;
        document.getElementById("place"+(firstID%5+4)).innerText=list[firstID+3].showPlace;
        $("#your-alert-5").hide();
    }
    else{
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        $("#your-alert-5").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID].id;
        document.getElementById("name"+(firstID%5+1)).src="../"+list[firstID].image;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].link;
        document.getElementById("place"+(firstID%5+1)).innerText=list[firstID].showPlace;

        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1].id;
        document.getElementById("name"+(firstID%5+2)).src="../"+list[firstID+1].image;
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1].link;
        document.getElementById("place"+(firstID%5+2)).innerText=list[firstID+1].showPlace;

        document.getElementById("number"+(firstID%5+3)).innerText=list[firstID+2].id;
        document.getElementById("name"+(firstID%5+3)).src="../"+list[firstID+2].image;
        document.getElementById("date"+(firstID%5+3)).innerText=list[firstID+2].link;
        document.getElementById("place"+(firstID%5+3)).innerText=list[firstID+2].showPlace;

        document.getElementById("number"+(firstID%5+4)).innerText=list[firstID+3].id;
        document.getElementById("name"+(firstID%5+4)).src="../"+list[firstID+3].image;
        document.getElementById("date"+(firstID%5+4)).innerText=list[firstID+3].link;
        document.getElementById("place"+(firstID%5+4)).innerText=list[firstID+3].showPlace;

        document.getElementById("number"+(firstID%5+5)).innerText=list[firstID+4].id;
        document.getElementById("name"+(firstID%5+5)).src="../"+list[firstID+4].image;
        document.getElementById("date"+(firstID%5+5)).innerText=list[firstID+4].link;
        document.getElementById("place"+(firstID%5+5)).innerText=list[firstID+4].showPlace;
    }


}

function deletesingle(n){
    var q=list[firstID+n];
    var url=getUrl();
    $.ajax(
        {
            url: url+"/deleteAd",
            data: {
                id:q.id
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
    var r=confirm("确定删除么？");
    if(r) {
        if (document.getElementById("c1").checked) {
            deletesingle(1);
        }
        if (document.getElementById("c2").checked) {
            deletesingle(2);
        }
        if (document.getElementById("c3").checked) {
            deletesingle(3);
        }
        if (document.getElementById("c4").checked) {
            deletesingle(4);
        }
        if (document.getElementById("c5").checked) {
            deletesingle(5);
        }
        alert("批量删除成功");
        window.location.href = "ads.html";
    }

}

function search(){
    var text=$("#con").val();
    for(var i=0;i<list.length;i++){
        if(list[i].id==text){
            $("#your-alert-1").show();
            document.getElementById("number"+(firstID%5+1)).innerText=list[i].id;
            document.getElementById("name"+(firstID%5+1)).innerText="../"+list[i].image;
            document.getElementById("date"+(firstID%5+1)).innerText=list[i].link;
            $("#your-alert-2").hide();
            $("#your-alert-3").hide();
            $("#your-alert-4").hide();
            $("#your-alert-5").hide();
            firstID=i-1;
        }
    }

}

function setAsChecked(n){
    var q=list[firstID+n];
    $.ajax(
        {
            url: url+"/setCheckedAd",
            data: {
                id:q.id
            },
            async:false,
            success: function (data) {
                if(q.showPlace=="首页") {
                    alert("选中编号为" + q.id + "作为首页广告！")
                }
                else if(q.showPlace=="业务") {
                    alert("选中编号为" + q.id + "作为业务广告！")
                }else if(q.showPlace=="弹窗") {
                    alert("选中编号为" + q.id + "作为弹窗广告！")
                }
                window.location.href="ads.html";
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )
}


