$("#zidingyi").hide();
var zi=false;
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
        var attachment="";
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
                url: url + "/addProject",
                data: {
                    title: $("#title").val(),
                    writerName: id,
                    identity: $("#identity").val(),
                    phone: $("#phone").val(),
                    city: $("#city").val(),
                    industry: industry,
                    business: business,
                    content: $("#content").val(),
                    money: $("#money").val(),
                    date: date,
                    attachment:attachment
                },
                async: false,
                success: function (data) {
                    alert("添加成功");
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