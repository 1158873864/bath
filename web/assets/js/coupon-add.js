var list=new Array();
var url=getUrl();
// $.ajax(
//     {
//         url: url+"/getLevelList",
//         data: {
//         },
//         async:false,
//         success: function (data) {
//             for(var i=0;i<data.levels.length;i++){
//                 list.push(data.levels[i]);
//             }
//             for(var i=0;i<list.length;i++){
//                 $("#levelName").append("<option value=''>"+list[i].name+"</option>");
//             }
//         },
//         error: function (xhr) {
//             alert('动态页有问题噶！\n\n' + xhr.responseText);
//         },
//         traditional: true,
//     }
// )

list=new Array();
// $.ajax(
//     {
//         url: url+"/getClassificationList",
//         data: {
//         },
//         async:false,
//         success: function (data) {
//             for(var i=0;i<data.classifications.length;i++){
//                 if(data.classifications[i].workClass=="capital"){
//                     data.classifications[i].workClass="金融类";
//                 }
//                 else if(data.classifications[i].workClass=="stock"){
//                     data.classifications[i].workClass="股票类";
//                 }
//                 else if(data.classifications[i].workClass=="merge"){
//                     data.classifications[i].workClass="并购类";
//                 }
//                 list.push(data.classifications[i]);
//             }
//             for(var i=0;i<list.length;i++){
//                 $("#label").append("<option value=''>"+list[i].userLabel+"</option>");
//             }
//         },
//         error: function (xhr) {
//             alert('动态页有问题噶！\n\n' + xhr.responseText);
//         },
//         traditional: true,
//     }
// )

/*
$("#fupPhoto").change(function () {
   console.log($("#fupPhoto")[0].files,8744,$("#fupPhoto"),'77788',$("#fupPhoto")[0].files[0]);
    var fd = new FormData($("#fupPhoto")[0]);
    var url = getUrl();
    $.ajax({
        url: url + "/admin/uploadAdmin",
        type: "POST",
        data:{"image":fd},
      //  enctype: 'multipart/form-data',
        processData: false,
        contentType:false,//"multipart/form-data", // , content="multipart/form-data;         charset=utf-8"
        cache: false,
        async: false,
        success: function (data) {
            //imageList=data;  
              alert("上传图片成功！")
          //  document.getElementById("imageList").innerText="";
          //  $("#imageList").append("<img src='"+"../"+imageList+"' style=\"width: 10rem;height: 10rem;\">")
        },
        error: function (xhr) {
            alert("上传图片失败！")
            // Handle upload error
            // ...
        }
    });
 

})*/
$("#upload").click(function(){
    $.ajax({
        url: url + "/admin/uploadAdmin",
        type: 'POST',
        cache: false,
        enctype: 'multipart/form-data',
        data: {"image":new FormData($('#fupPhoto')[0])},
        processData: false,
        contentType: false
    }).done(function(res) {
    }).fail(function(res) {})
})

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

function adduser() {
    if(checkRate("credit")) {
        var fd = new FormData($("#upload-file-form")[0]);
        var url = getUrl();
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
                $.ajax(
                    {
                        url: url + "/addUser",
                        data: {
                            openid: $("#openid").val(),
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
                            alert("添加成功");
                            window.location.href = "vip-list.html";
                        },
                        error: function (xhr) {
                            //alert('动态页有问题噶！\n\n' + xhr.responseText);
                        },
                        traditional: true,
                    }
                )
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

//添加优惠券接口
function ocConfirm(){
    console.log($('#fupPhoto').val(),555)
   $.ajax({
       url: url+'/groupon/add',
       type:'post',
       data:{
        name: $('#couponname').val(),
        originalPrice: $('#oPrice').val(),
        price: $('#price').val(),
        takeEffectTime:$('#takeEffectTime').val(),
        loseEffectTime: $('#loseEffectTime').val(),
        putOnShelvesTime: $('#putOnShelvesTime').val(),
        pullOffShelvesTime:$('#pullOffShelvesTime').val(),
        description:$('#description').val(),
        amount:$('#amount').val(),
        type:$('#type').val(),
        image:$('#fupPhoto').val,

       },
       async: false,
       success: function (data) {
           alert("添加成功");
           console.log(data,'2222')
           //window.location.href = "coupon.html";
       },
       error: function (xhr) {
           //alert('动态页有问题噶！\n\n' + xhr.responseText);
       },
       traditional: true,
   })
}