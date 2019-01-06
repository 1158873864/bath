var url=getUrl();
var storage = window.localStorage;
var id=storage["thisEnterprise"];
$.ajax(
    {
        url: url+"/getEnterpriseById",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("number").innerText=data.enterprise.id;
            document.getElementById("name").innerText=data.enterprise.name;
            document.getElementById("intro").innerText=data.enterprise.description;
            document.getElementById("adminName").innerText=data.enterprise.adminUsername;
            document.getElementById("adminPassword").innerText=data.enterprise.adminPassword;
            document.getElementById("openid").innerText=data.enterprise.openid;
            document.getElementById("license").src="../"+data.enterprise.licenseUrl;
            if(data.enterprise.status=="submitted"){
                document.getElementById("status").innerText="待审核";
            }
            else if(data.enterprise.status=="verified"){
                document.getElementById("status").innerText="已通过审核";
            }
            else if(data.enterprise.status=="rejected"){
                document.getElementById("status").innerText="审核不通过";
            }
            else if(data.enterprise.status=="disqualified"){
                document.getElementById("status").innerText="身份被取消";
            }

        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

function agree(){
    $.ajax(
        {
            url: url+"/verifyEnterpriseById",
            data: {
                id:id
            },
            async:false,
            success: function (data) {
                alert("设置成功！");
                window.location.href="enterpriseList.html";

            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )
}

function getBack(){
    window.location.href="enterpriseList.html";
}


