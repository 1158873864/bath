
function adduser() {
    var fd = new FormData($("#upload-file-form")[0]);
    var url = getUrl();
    var storage = window.localStorage;
    var id = storage["adminUsername"];
    var myDate = new Date();
    var date = myDate.toLocaleDateString();
    var attachment="";
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
            url: url + "/addDocument",
            data: {
                title: $("#title").val(),
                content: $("#content").val(),
                attachment:attachment,
                writerName: id,
                date: date
            },
            async: false,
            success: function (data) {
                alert("添加成功");
                window.location.href = "file.html";
            },
            error: function (xhr) {
                //alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )


}