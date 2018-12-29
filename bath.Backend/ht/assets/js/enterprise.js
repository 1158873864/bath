var url=getUrl();
$.ajax(
    {
        url: url+"/getPrivilege",
        data: {
            name:"enterprise"
        },
        async:false,
        success: function (data) {
            document.getElementById("price").value=data.privilege.price;
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
function changeFile(){
    if(checkRate("price")) {
        $.ajax(
            {
                url: url + "/updatePrivilege",
                data: {
                    name: "enterprise",
                    price: parseInt($("#price").val())
                },
                async: false,
                success: function (data) {
                    alert("修改成功");
                    window.location.href = "enterprise.html";
                },
                error: function (xhr) {
                    alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )
    }
}
