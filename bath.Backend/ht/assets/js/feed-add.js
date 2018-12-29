
var imageList=new Array();

function fileSelected() {
    var fd = new FormData($("#upload-file-form")[0]);
    var url = getUrl();
    $.ajax({
        url: url + "/uploadFeed",
        type: "POST",
        data: fd,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        async: false,
        success: function (data) {
            imageList.push(data);
            $("#imageList").append("<img src='"+"../"+imageList[imageList.length-1]+"' style=\"width: 10rem;height: 10rem;\">")
        },
        error: function (xhr) {
            alert("上传图片失败！")
            // Handle upload error
            // ...
        }
    });

}

function fileSelect() {
    document.getElementById("image").click();
}

function clearImage(){
    imageList.length=0;
    document.getElementById("imageList").innerText="";
}



function addUser(){
    var url = getUrl();

    var writerOpenid=$("#writerOpenid").val();
    var content=$("#content").val();
        if(imageList.length==0){
            imageList.push("");
        }
        $.ajax(
            {
                url: url + "/addFeed",
                data: {
                    writerOpenid:writerOpenid,
                    images:imageList,
                    content:content
                },
                async: false,
                success: function (data) {
                    alert("添加成功");
                    window.location.href = "feed.html";
                },
                error: function (xhr) {
                    alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )

}