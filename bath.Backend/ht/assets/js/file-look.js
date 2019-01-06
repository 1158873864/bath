var url=getUrl();
var storage = window.localStorage;
var id=storage["thisDocument"];
var path;
$.ajax(
    {
        url: url+"/getDocument",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.document.id;
            document.getElementById("title").innerText=data.document.title;
            document.getElementById("content").innerText=data.document.content;
            document.getElementById("writerName").innerText=data.document.writerName;
            document.getElementById("date").innerText=data.document.date;
            document.getElementById("likeNum").innerText=data.document.likeNum;
            path="../"+data.document.attachment;
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

function attatchment(){
    var $eleForm = $("<form method='get'></form>");

    $eleForm.attr("target",'');
    $eleForm.attr("action",path);
    $(document.body).append($eleForm);
    $eleForm.submit();   //提交表单，实现下载
}
