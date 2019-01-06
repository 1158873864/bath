
var url=getUrl();
var storage = window.localStorage;
var id=storage["thisCourse"];
var image="";
var video="";
$.ajax(
    {
        url: url+"/getCourse",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.course.id;
            document.getElementById("title").value=data.course.title;
            document.getElementById("writerName").value=data.course.writerName;
            document.getElementById("date").value=data.course.date;
            document.getElementById("likeNum").value=data.course.likeNum;
            document.getElementById("price").value=data.course.price;
            image=data.course.image;
            video=data.course.video;
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
function adduser() {
    if(checkRate("likeNum")&&checkRate("price")) {
        var fd = new FormData($("#upload-file-form")[0]);
        var url = getUrl();
        var fd2 = new FormData($("#upload-video-form")[0]);

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
                url: url + "/updateCourse",
                data: {
                    id: id,
                    title: $("#title").val(),
                    writerName: $("#writerName").val(),
                    date: $("#date").val(),
                    likeNum: $("#likeNum").val(),
                    price: parseInt($("#price").val()),
                    image:image,
                    video:video
                },
                async: false,
                success: function (data) {
                    alert("修改成功");
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