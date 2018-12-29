var imageList=new Array();

var url=getUrl();
var storage = window.localStorage;
var id=storage["thisFeed"];
$.ajax(
    {
        url: url+"/getFeed",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.feed.id;
            document.getElementById("writerOpenid").innerText=data.feed.writerOpenid;
            document.getElementById("content").innerText=data.feed.content;
            document.getElementById("date").innerText=data.feed.date;
            document.getElementById("likeNum").innerText=data.feed.likeNum;
            for(var i=0;i<data.feed.images.length;i++){
                imageList.push(data.feed.images[i]);
                $("#imageList").append("<img src='"+"../"+data.feed.images[i]+"' style=\"width: 10rem;height: 10rem;\">")
            }

        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)