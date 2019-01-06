var x1="";
var x2="";
var x3="";
var url=getUrl();
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
                url: url+"/addClassification",
                data: {
                    userLabel:userLabel,
                    workClass:workClass
                },
                success: function (data) {
                    alert("添加成功");
                    window.location.href = "classification.html";
                },
                error: function (xhr) {
                    alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )


}
