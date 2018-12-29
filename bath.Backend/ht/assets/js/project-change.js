
$("#zidingyi").hide();
var zi=false;

var url=getUrl();
var storage = window.localStorage;
var id=storage["thisProject"];
var path;
var attachment="";
$.ajax(
    {
        url: url+"/getProject",
        headers :{
            'Authorization': 'Bearer ' + getToken(),
            'content-type': 'application/x-www-form-urlencoded'
        },
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.project.id;
            document.getElementById("title").value=data.project.title;
            document.getElementById("writerName").value=data.project.writerName;
            document.getElementById("identity").value=data.project.identity;
            document.getElementById("phone").value=data.project.phone;
            document.getElementById("city").value=data.project.city;
            document.getElementById("industryIni").innerText=data.project.industry;
            document.getElementById("businessIni").innerText=data.project.business;
            document.getElementById("content").value=data.project.content;
            document.getElementById("money").value=data.project.money;
            document.getElementById("date").value=data.project.date;
            path="../"+data.project.attachment;
            attachment=data.project.attachment;
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)
$("#business").change(function(){
        var obj2= document.getElementById("business"); //定位id
        var index2 = obj2.selectedIndex; // 选中索引
        var b = obj2.options[index2].text; // 选中文本
        if(b=="自定义"){
            $("#zidingyi").show();
            zi=true;
        }
        else{
            $("#zidingyi").hide();
            zi=false;
        }
    }
)

function checkRate(input) {
    var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
    var nubmer = document.getElementById(input).value;

    if (!re.test(nubmer)) {
        alert(input+"请输入数字");
        document.getElementById(input).value = "";
        return false;
    }
    return true;
}
function adduser() {
    if(checkRate("money")) {
        var fd = new FormData($("#upload-file-form")[0]);
        var url = getUrl();
        var obj1 = document.getElementById("industry"); //定位id
        var index1 = obj1.selectedIndex; // 选中索引
        var industry = obj1.options[index1].text; // 选中文本

        var obj2 = document.getElementById("business"); //定位id
        var index2 = obj2.selectedIndex; // 选中索引
        var business = obj2.options[index2].text; // 选中文本
        if (zi) {
            business = $("#zidingyi").val();
        }
        var storage = window.localStorage;
        var id = storage["adminUsername"];
        var myDate = new Date();
        var date = myDate.toLocaleDateString();
        $.ajax({
            url: url + "/uploadAttachment",
            type: "POST",
            data: fd,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            async: false,
            success: function (data) {
                if(data!="") {
                    attachment = data;
                }
                // Handle upload success
                // ...
            },
            error: function (xhr) {
                //alert(xhr.responseText);
                // Handle upload error
                // ...
            }
        });

        $.ajax(
            {
                url: url + "/updateProject",
                data: {
                    id: document.getElementById("id").innerText,
                    title: $("#title").val(),
                    writerName: $("#writerName").val(),
                    identity: $("#identity").val(),
                    phone: $("#phone").val(),
                    city: $("#city").val(),
                    industry: industry,
                    business: business,
                    content: $("#content").val(),
                    money: $("#money").val(),
                    date: $("#date").val(),
                    attachment:attachment
                },
                async: false,
                success: function (data) {
                    alert("修改成功");
                    window.location.href = "project.html";
                },
                error: function (xhr) {
                    //alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )
    }
}