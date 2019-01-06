var url=getUrl();
var storage = window.localStorage;
var id=storage["thisProject"];
var path;
$.ajax(
    {
        url: url+"/getProject",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.project.id;
            document.getElementById("title").innerText=data.project.title;
            document.getElementById("writerName").innerText=data.project.writerName;
            document.getElementById("identity").innerText=data.project.identity;
            document.getElementById("phone").innerText=data.project.phone;
            document.getElementById("city").innerText=data.project.city;
            document.getElementById("industry").innerText=data.project.industry;
            document.getElementById("business").innerText=data.project.business;
            document.getElementById("content").innerText=data.project.content;
            document.getElementById("money").innerText=data.project.money;
            document.getElementById("date").innerText=data.project.date;
            document.getElementById("likeNum").innerText=data.project.likeNum;
            path="../"+data.project.attachment;
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