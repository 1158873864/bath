
var storage=window.localStorage;
var newsId=storage["updateNews"];
var url=getUrl();
$.ajax(
    {
        url: url+"/getNews",
        data: {
            newsId:newsId
        },
        async:false,
        success: function (data) {
            document.getElementById("newsId").innerText=data.news.newsId;
            document.getElementById("source").innerText=data.news.source;
            document.getElementById("time").innerText=data.news.time;
            document.getElementById("content").value=data.news.content;
            document.getElementById("level").innerText=data.news.level;
            document.getElementById("type").innerText=data.news.type;
            document.getElementById("keywords").innerText=data.news.keywords;
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)
document.getElementById("ad").onclick=function() {
    $.ajax(
        {
            url: url+"/updateNews",
            data: {
                newsId:document.getElementById("newsId").innerText,
                source:document.getElementById("source").innerText,
                time:document.getElementById("time").innerText,
                content:$("#content").val(),
                level:document.getElementById("level").innerText,
                type:document.getElementById("type").innerText,
                keywords:document.getElementById("keywords").innerText
            },
            success: function (data) {
                alert("修改成功");
                window.location.href = "news.html";
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )


}