var url=getUrl();
var storage = window.localStorage;
var id=storage["thisCourse"];
var video;
$.ajax(
    {
        url: url+"/getCourse",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.course.id;
            document.getElementById("title").innerText=data.course.title;
            document.getElementById("image").src="../"+data.course.image;
            document.getElementById("writerName").innerText=data.course.writerName;
            document.getElementById("date").innerText=data.course.date;
            document.getElementById("likeNum").innerText=data.course.likeNum;
            document.getElementById("price").innerText=data.course.price;
            video=data.course.video;
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

function look(){
    storage["thisVideo"]=video;
    window.location.href="tv.html";
}