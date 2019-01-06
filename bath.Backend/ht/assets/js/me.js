
var storage=window.localStorage;
var username=storage["adminUsername"];
var id;
var date;
var url=getUrl();
var limits="";
var imageList="";


$.ajax(
    {
        url: url+"/getAdminByUsername",
        data: {
            username:username
        },
        async:false,
        success: function (data) {
            document.getElementById("number").innerText=data.admin.id;
            id=data.admin.id;
            document.getElementById("name").value=data.admin.username;
            document.getElementById("password").value=data.admin.password;
            date=data.admin.date;
            limits=data.admin.limits;
            imageList=data.admin.face;
            document.getElementById("imageList").innerText="";
            $("#imageList").append("<img src='"+"../"+imageList+"' style=\"width: 10rem;height: 10rem;\">")
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

function fileSelected() {
    var fd = new FormData($("#upload-file-form")[0]);
    var url = getUrl();
    $.ajax({
        url: url + "/uploadAdmin",
        type: "POST",
        data: fd,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        async: false,
        success: function (data) {
            imageList=data;
            document.getElementById("imageList").innerText="";
            $("#imageList").append("<img src='"+"../"+imageList+"' style=\"width: 10rem;height: 10rem;\">")
        },
        error: function (xhr) {
            alert("上传图片失败！")
            // Handle upload error
            // ...
        }
    });

}

document.getElementById("ad").onclick=function() {
    var name = $("#name").val();
    var pass = $("#password").val();
    $.ajax(
        {
            url: url+"/updateAdmin",
            data: {
                id:id,
                username:name,
                password:pass,
                limits:limits,
                date:date,
                face:imageList
            },
            success: function (data) {
                alert("修改成功");
                window.location.href = "index.html";
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )


}