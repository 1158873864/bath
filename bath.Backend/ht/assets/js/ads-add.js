

function adduser() {

    var fd = new FormData($("#upload-file-form")[0]);
    var obj1 = document.getElementById("showPlace"); //定位id
    var index1 = obj1.selectedIndex; // 选中索引
    var showPlace = obj1.options[index1].text; // 选中文本
    if(showPlace=="首页"){
        showPlace="index";
    }
    else if(showPlace=="业务"){
        showPlace="service";
    }
    else if(showPlace=="弹窗"){
        showPlace="jump";
    }

    var url = getUrl();
    $.ajax({
        url: url + "/uploadAd",
        type: "POST",
        data: fd,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        async: false,
        success: function () {
            $.ajax(
                {
                    url: url + "/addAd",
                    data: {
                        link:$("#link").val(),
                        showPlace:showPlace
                    },
                    async: false,
                    success: function (data) {
                        alert("添加成功");
                        window.location.href="ads.html";
                    },
                    error: function (xhr) {
                        //alert('动态页有问题噶！\n\n' + xhr.responseText);
                    },
                    traditional: true,
                }
            )
            // Handle upload success
            // ...
        },
        error: function (xhr) {
            //alert(xhr.responseText);
            // Handle upload error
            // ...
        }
    });
}