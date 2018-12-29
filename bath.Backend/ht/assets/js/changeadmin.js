
var storage=window.localStorage;
var id=storage["updateadmin"];
var date;
var url=getUrl();
var imageList="";
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
$.ajax(
    {
        url: url+"/getAdmin",
        data: {
            id:id
        },
        async:false,
        success: function (data) {

            document.getElementById("name").value=data.admin.username;
            document.getElementById("password").value=data.admin.password;
            date=data.admin.date;
            var limits=data.admin.limits;
            for(var i=1;i<=14;i++){
                for(var j=0;j<limits.length;j++){
                    if(parseInt(limits[j])==i){
                        document.getElementById("c"+i).checked=true;
                    }
                }
            }
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
document.getElementById("ad").onclick=function() {
    var name = $("#name").val();
    var pass = $("#password").val();
    var limits = "";
    if (document.getElementById("c1").checked) {
        limits = limits + "1" + "...";
    }
    if (document.getElementById("c2").checked) {
        limits = limits + "2" + "...";
    }
    if (document.getElementById("c3").checked) {
        limits = limits + "3" + "...";
    }
    if (document.getElementById("c4").checked) {
        limits = limits + "4" + "...";
    }
    if (document.getElementById("c5").checked) {
        limits = limits + "5" + "...";
    }
    if (document.getElementById("c6").checked) {
        limits = limits + "6" + "...";
    }
    if (document.getElementById("c7").checked) {
        limits = limits + "7" + "...";
    }
    if (document.getElementById("c8").checked) {
        limits = limits + "8" + "...";
    }
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
                window.location.href = "admin-list.html";
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )


}