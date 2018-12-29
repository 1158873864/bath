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
var tempList=new Array();
var firstID=0;
var theGroup=0;
var url=getUrl();
$.ajax(
    {
        url: url+"/getAllEnterprises",
        data: {
        },
        async:false,
        success: function (data) {
            for(var i=0;i<data.enterprises.length;i++){
                if(data.enterprises[i].status=="submitted"){
                    data.enterprises[i].status="待审核";
                }
                else if(data.enterprises[i].status=="verified"){
                    data.enterprises[i].status="已通过审核";
                }
                else if(data.enterprises[i].status=="rejected"){
                    data.enterprises[i].status="审核不通过";
                }
                else if(data.enterprises[i].status=="disqualified"){
                    data.enterprises[i].status="身份被取消";
                }
                list.push(data.enterprises[i]);
            }
            for(var i=0;i<list.length;i++){
                tempList.push(list[i]);
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
    storage["thisEnterprise"]=q.id;
}

function deletequestion(n){
    var r=confirm("确定删除/驳回么？");
    if(r) {
        var q = list[firstID + n];
        var url = getUrl();
        $.ajax(
            {
                url: url + "/deleteEnterpriseById",
                data: {
                    id: q.id
                },
                async: false,
                success: function (data) {
                    alert("删除/驳回成功");
                    window.location.href = "enterpriseList.html";
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
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].name;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].status;
        $("#your-alert-2").hide();
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+3)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID].id;
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].name;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].status;

        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1].id;
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1].name;
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1].status;
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+4)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID].id;
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].name;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].status;

        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1].id;
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1].name;
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1].status;

        document.getElementById("number"+(firstID%5+3)).innerText=list[firstID+2].id;;
        document.getElementById("name"+(firstID%5+3)).innerText=list[firstID+2].name;
        document.getElementById("date"+(firstID%5+3)).innerText=list[firstID+2].status;
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+5)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID].id;
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].name;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].status;

        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1].id;
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1].name;
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1].status;

        document.getElementById("number"+(firstID%5+3)).innerText=list[firstID+2].id;;
        document.getElementById("name"+(firstID%5+3)).innerText=list[firstID+2].name;
        document.getElementById("date"+(firstID%5+3)).innerText=list[firstID+2].status;

        document.getElementById("number"+(firstID%5+4)).innerText=list[firstID+3].id;;
        document.getElementById("name"+(firstID%5+4)).innerText=list[firstID+3].name;
        document.getElementById("date"+(firstID%5+4)).innerText=list[firstID+3].status;
        $("#your-alert-5").hide();
    }
    else{
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        $("#your-alert-5").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID].id;
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].name;
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID].status;

        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1].id;
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1].name;
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1].status;

        document.getElementById("number"+(firstID%5+3)).innerText=list[firstID+2].id;;
        document.getElementById("name"+(firstID%5+3)).innerText=list[firstID+2].name;
        document.getElementById("date"+(firstID%5+3)).innerText=list[firstID+2].status;

        document.getElementById("number"+(firstID%5+4)).innerText=list[firstID+3].id;;
        document.getElementById("name"+(firstID%5+4)).innerText=list[firstID+3].name;
        document.getElementById("date"+(firstID%5+4)).innerText=list[firstID+3].status;

        document.getElementById("number"+(firstID%5+5)).innerText=list[firstID+4].id;;
        document.getElementById("name"+(firstID%5+5)).innerText=list[firstID+4].name;
        document.getElementById("date"+(firstID%5+5)).innerText=list[firstID+4].status;

    }


}

function deletesingle(n){
    var q=list[firstID+n];
    var url=getUrl();
    $.ajax(
        {
            url: url+"/deleteEnterpriseById",
            headers :{
                'Authorization': 'Bearer ' + getToken(),
                'content-type': 'application/x-www-form-urlencoded'
            },
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
    var r=confirm("确定删除/驳回么？");
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
        alert("批量删除/驳回成功");
        window.location.href = "enterpriseList.html";
    }

}

function search(){
    var text=$("#con").val();
    for(var i=0;i<list.length;i++){
        if(list[i].id==text){
            $("#your-alert-1").show();
            document.getElementById("number"+(firstID%5+1)).innerText=list[i].id;
            document.getElementById("name"+(firstID%5+1)).innerText=list[i].name;
            document.getElementById("date"+(firstID%5+1)).innerText=list[i].status;
            $("#your-alert-2").hide();
            $("#your-alert-3").hide();
            $("#your-alert-4").hide();
            $("#your-alert-5").hide();
            firstID=i-1;
        }
    }

}

$("#type").change(function(){
        var obj2= document.getElementById("type"); //定位id
        var index2 = obj2.selectedIndex; // 选中索引
        var b = obj2.options[index2].text; // 选中文本
        if(b=="全部"){
            list=new Array();
            for(var i=0;i<tempList.length;i++){
                list.push(tempList[i]);
            }
            firstID=0;
            theGroup=0;
            changepage(1);
        }
        else{
            list=new Array();
            for(var i=0;i<tempList.length;i++){
                if(tempList[i].status==b) {
                    list.push(tempList[i]);
                }
            }
            firstID=0;
            theGroup=0;
            changepage(1);
        }
    }
)


