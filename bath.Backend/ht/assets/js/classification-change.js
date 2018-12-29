
var storage=window.localStorage;
var id=storage["thisClassification"];
var url=getUrl();
var x1="";
var x2="";
var x3="";
$.ajax(
    {
        url: url+"/getClassificationDescriptionList",
        data: {
        },
        async:false,
        success: function (data) {
            for(var i=0;i<3;i++){
                if(data.classificationDescriptionItems[i].workClass=="capital"){
                    x1=data.classificationDescriptionItems[i].description;
                }
                else if(data.classificationDescriptionItems[i].workClass=="stock"){
                    x2=data.classificationDescriptionItems[i].description;
                }
                else if(data.classificationDescriptionItems[i].workClass=="merge"){
                    x3=data.classificationDescriptionItems[i].description;
                }
            }

        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)
$("#workClass").append("<option value=''>"+x1+"</option>");
$("#workClass").append("<option value=''>"+x2+"</option>");
$("#workClass").append("<option value=''>"+x3+"</option>");
$.ajax(
    {
        url: url+"/getClassification",
        data: {
            userLabel:id
        },
        async:false,
        success: function (data) {

            document.getElementById("userLabel").value=data.classification.userLabel;
            if(data.classification.workClass=="capital"){
                document.getElementById("workClass").value=x1;
            }
            else if(data.classification.workClass=="stock"){
                document.getElementById("workClass").value=x2;
            }
            else if(data.classification.workClass=="merge"){
                document.getElementById("workClass").value=x3;
            }
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)
document.getElementById("ad").onclick=function() {
    var userLabel = $("#userLabel").val();
    var obj1= document.getElementById("workClass"); //定位id
    var index1 = obj1.selectedIndex; // 选中索引
    var workClass = obj1.options[index1].text; // 选中文本
    if(workClass==x1){
        workClass="capital";
    }
    else if(workClass==x2){
        workClass="stock";
    }
    else if(workClass==x3){
        workClass="merge";
    }
    var url=getUrl();
    $.ajax(
        {
            url: url+"/updateClassification",
            headers :{
                'Authorization': 'Bearer ' + getToken(),
                'content-type': 'application/x-www-form-urlencoded'
            },
            data: {
                userLabel:userLabel,
                workClass:workClass
            },
            success: function (data) {
                alert("修改成功");
                window.location.href = "classification.html";
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )


}
