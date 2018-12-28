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

document.getElementById("ad").onclick=function() {
    if($("#password").val()!=$("#passwordagain").val()){
        alert("请保证密码一致！")
    }
    else {
        var name = $("#name").val();
        var pass = $("#password").val();
        var myDate = new Date();
        var date = myDate.toLocaleDateString();
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
        if (document.getElementById("c9").checked) {
            limits = limits + "9" + "...";
        }
        var url=getUrl();
        $.ajax(
            {
                url: url+"/addadmin",
                data: {
                    username:name,
                    password:pass,
                    limits:limits,
                    date:date,
                    face:imageList
                },

                success: function (data) {
                    alert("添加成功");
                    window.location.href = "admin-list.html";
                },
                error: function (xhr) {
                    alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )

    }
}