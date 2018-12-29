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
                    document.getElementById("capital").value=data.classificationDescriptionItems[i].description;
                }
                else if(data.classificationDescriptionItems[i].workClass=="stock"){
                    document.getElementById("stock").value=data.classificationDescriptionItems[i].description;
                }
                else if(data.classificationDescriptionItems[i].workClass=="merge"){
                    document.getElementById("merge").value=data.classificationDescriptionItems[i].description;
                }
            }

        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

function changeFile(){

        $.ajax(
            {
                url: url + "/updateClassificationDescription",
                data: {
                    workClass: "capital",
                    description: $("#capital").val()
                },
                async: false,
                success: function (data) {
                },
                error: function (xhr) {
                    alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )

    $.ajax(
        {
            url: url + "/updateClassificationDescription",
            data: {
                workClass: "stock",
                description: $("#stock").val()
            },
            async: false,
            success: function (data) {
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )

    $.ajax(
        {
            url: url + "/updateClassificationDescription",
            data: {
                workClass: "merge",
                description: $("#merge").val()
            },
            async: false,
            success: function (data) {
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )
       alert("修改成功");
    window.location.href="bigClass.html";

}
