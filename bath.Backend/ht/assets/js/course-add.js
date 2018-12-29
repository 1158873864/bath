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
    if(checkRate("price")) {
        var fd = new FormData($("#upload-file-form")[0]);
        var url = getUrl();
        var fd2 = new FormData($("#upload-video-form")[0]);
        var storage = window.localStorage;
        var id = storage["adminUsername"];
        var myDate = new Date();
        var date = myDate.toLocaleDateString();
        var image="";
        var video="";
        $.ajax({
            url: url + "/courseImage",
            type: "POST",
            data: fd,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            async: false,
            success: function (data) {
                if(data!="") {
                    image = data;
                }
            },
            error: function (xhr) {

            }
        });

        $.ajax({
            url: url + "/courseVideo",
            type: "POST",
            data: fd2,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            async: false,
            success: function (data) {
                if(data!="") {
                    video = data;
                }

            },
            error: function (xhr) {

            }
        });

        $.ajax(
            {
                url: url + "/addCourse",
                data: {
                    title: $("#title").val(),
                    writerName: id,
                    date: date,
                    price: parseInt($("#price").val()),
                    image:image,
                    video:video
                },
                async: false,
                success: function (data) {
                    alert("添加成功");
                    window.location.href = "course.html";
                },
                error: function (xhr) {
                    //alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )
    }
}