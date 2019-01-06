var url=getUrl();
var storage = window.localStorage;
var id=storage["thisDocument"];
var path;
var attachment="";
$.ajax(
    {
        url: url+"/getDocument",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.document.id;
            document.getElementById("title").value=data.document.title;
            document.getElementById("content").value=data.document.content;
            document.getElementById("writerName").value=data.document.writerName;
            document.getElementById("date").value=data.document.date;
            document.getElementById("likeNum").value=data.document.likeNum;
            path="../"+data.document.attachment;
            attachment=data.document.attachment;
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
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
function changeFile(){
    if(checkRate("likeNum")) {
        var fd = new FormData($("#upload-file-form")[0]);

        $.ajax({
            url: url + "/uploadDocument",
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
            },
            error: function (xhr) {
                //alert(xhr.responseText);
                // Handle upload error
                // ...
            }
        });

        $.ajax(
            {
                url: url + "/updateDocument",
                data: {
                    id: id,
                    title: $("#title").val(),
                    content: $("#content").val(),
                    attachment:attachment,
                    writerName: $("#writerName").val(),
                    date: $("#date").val(),
                    likeNum: $("#likeNum").val()
                },
                async: false,
                success: function (data) {
                    alert("修改成功");
                    window.location.href = "file.html";
                },
                error: function (xhr) {
                    alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )

    }
}
