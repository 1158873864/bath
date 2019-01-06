var list=new Array();
var url=getUrl();
var face="";
$.ajax(
    {
        url: url+"/getLevelList",
        data: {
        },
        async:false,
        success: function (data) {
            for(var i=0;i<data.levels.length;i++){
                list.push(data.levels[i]);
            }
            for(var i=0;i<list.length;i++){
                $("#levelName").append("<option value=''>"+list[i].name+"</option>");
            }
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

list=new Array();
$.ajax(
    {
        url: url+"/getClassificationList",
        data: {
        },
        async:false,
        success: function (data) {
            for(var i=0;i<data.classifications.length;i++){
                if(data.classifications[i].workClass=="capital"){
                    data.classifications[i].workClass="金融类";
                }
                else if(data.classifications[i].workClass=="stock"){
                    data.classifications[i].workClass="股票类";
                }
                else if(data.classifications[i].workClass=="merge"){
                    data.classifications[i].workClass="并购类";
                }
                list.push(data.classifications[i]);
            }
            for(var i=0;i<list.length;i++){
                $("#label").append("<option value=''>"+list[i].userLabel+"</option>");
            }
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

var storage = window.localStorage;
var id=storage["thisUser"];
$.ajax(
    {
        url: url+"/getUser",
        data: {
            openid:id
        },
        async:false,
        success: function (data) {
            document.getElementById("openid").innerText=data.user.openid;
            document.getElementById("username").value=data.user.username;
            document.getElementById("phone").value=data.user.phone;
            document.getElementById("email").value=data.user.email;
            document.getElementById("company").value=data.user.company;
            document.getElementById("department").value=data.user.department;
            document.getElementById("position").value=data.user.position;
            document.getElementById("intro").value=data.user.intro;
            document.getElementById("city").value=data.user.city;
            document.getElementById("credit").value=data.user.credit;
            document.getElementById("tempLabel").innerText=data.user.label;
            document.getElementById("tempLevel").innerText=data.user.levelName;
            document.getElementById("lastFace").src="../"+data.user.face;
            face=data.user.face;
            if(data.user.valid){
                document.getElementById("tempis").innerText="启用";
            }
            else{
                document.getElementById("tempis").innerText="冻结";
            }

        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)


function adduser() {
    if(checkRate("credit")) {
        var url = getUrl();
        var fd = new FormData($("#upload-file-form")[0]);

        var obj1 = document.getElementById("is"); //定位id
        var index1 = obj1.selectedIndex; // 选中索引
        var valid = obj1.options[index1].text; // 选中文本

        var obj2 = document.getElementById("levelName"); //定位id
        var index2 = obj2.selectedIndex; // 选中索引
        var levelName = obj2.options[index2].text; // 选中文本

        var obj3 = document.getElementById("label"); //定位id
        var index3 = obj3.selectedIndex; // 选中索引
        var label = obj3.options[index3].text; // 选中文本
        $.ajax({
            url: url + "/uploadHead",
            type: "POST",
            data: fd,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            async: false,
            success: function () {
                if(document.getElementById("face").value.length!=0) {
                    $.ajax(
                        {
                            url: url + "/updateUser",
                            data: {
                                openid: document.getElementById("openid").innerText,
                                username: $("#username").val(),
                                phone: $("#phone").val(),
                                email: $("#email").val(),
                                company: $("#company").val(),
                                department: $("#department").val(),
                                position: $("#position").val(),
                                intro: $("#intro").val(),
                                city: $("#city").val(),
                                credit: $("#credit").val(),
                                label: label,
                                levelName: levelName,
                                valid: valid
                            },
                            async: false,
                            success: function (data) {
                                alert("修改成功");
                                window.location.href = "vip-list.html";
                            },
                            error: function (xhr) {
                                //alert('动态页有问题噶！\n\n' + xhr.responseText);
                            },
                            traditional: true,
                        }
                    )
                }
                else{
                    $.ajax(
                        {
                            url: url + "/addUserWithoutFace",
                            data: {
                                openid: document.getElementById("openid").innerText,
                                username: $("#username").val(),
                                face:face,
                                phone: $("#phone").val(),
                                email: $("#email").val(),
                                company: $("#company").val(),
                                department: $("#department").val(),
                                position: $("#position").val(),
                                intro: $("#intro").val(),
                                city: $("#city").val(),
                                credit: $("#credit").val(),
                                label: label,
                                levelName: levelName,
                                valid: valid
                            },
                            async: false,
                            success: function (data) {
                                alert("修改成功");
                                window.location.href = "vip-list.html";
                            },
                            error: function (xhr) {
                                //alert('动态页有问题噶！\n\n' + xhr.responseText);
                            },
                            traditional: true,
                        }
                    )
                }
                // Handle upload success
                // ...
            },
            error: function (xhr) {
                //alert(xhr.responseText);
                // Handle upload error
                // ...

            }
        });
    }
}