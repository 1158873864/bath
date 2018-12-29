var url=getUrl();
var storage = window.localStorage;
var openid=storage["thisUser"];
$.ajax(
    {
        url: url+"/getUser",
        data: {
            openid:openid
        },
        async:false,
        success: function (data) {
            document.getElementById("number").innerText=data.user.openid;
            document.getElementById("username").innerText=data.user.username;
            document.getElementById("head").src="../"+data.user.face;
            document.getElementById("phone").innerText=data.user.phone;
            document.getElementById("email").innerText=data.user.email;
            document.getElementById("company").innerText=data.user.company;
            document.getElementById("department").innerText=data.user.department;
            document.getElementById("position").innerText=data.user.position;
            document.getElementById("intro").innerText=data.user.intro;
            document.getElementById("city").innerText=data.user.city;
            document.getElementById("credit").innerText=data.user.credit;
            document.getElementById("label").innerText=data.user.label;
            document.getElementById("levelName").innerText=data.user.levelName;
            if(data.user.valid){
                document.getElementById("valid").innerText="启用";
            }
            else{
                document.getElementById("valid").innerText="禁用";
            }

        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)
