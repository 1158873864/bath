var list=new Array();
var url=getUrl();
list=new Array();
var coupouId='';
window.onload=function(){ 
    var storage = window.localStorage;
    coupouId = storage.getItem("thisCoupon")
    console.log( coupouId,'55555555',storage,storage.getItem("thisCoupon"))
    this.getEdit();
    // 初始化内容 
}

function getEdit(){
    $.ajax(
    {
        url: url+"/coupon/view",
        method:'post',
        data: {
            id :coupouId
        },
        async:true,
        success: function (data) {
          /*  for(var i=0;i<data.levels.length;i++){
                list.push(data.levels[i]);
            }
            for(var i=0;i<list.length;i++){
                $("#levelName").append("<option value=''>"+list[i].name+"</option>");
            }*/
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

}
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

// $(function () {
//     var action = getUrlParam('id');
  
   
// });
// function getUrlParam(name) {//a标签跳转获取参数
//     var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
//     var r = window.location.search.substr(1).match(reg);
//     console.log(r[2],88888888888)
//     if (r != null){
//         $.ajax({
//             type:'get',
//             url:url+'/groupon/ordinary?id=r[2]',
//             data:{
//                 id:r[2]
//             },
//             async: false,
//             success: function (data) {
                
//                 console.log(data,'2222')
//             }
//         })
//     } 
    
    
// }
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
                            id,
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
//修改优惠券
function updateCoupon(){
    alert('修改')
   $.ajax({
       url: url+ '/groupon/update ',
       type:'post',
       data:{
        id,
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
        image:$('#fupPhoto').val(),

       },
       async: false,
       success: function (data) {
           alert("修改成功");
           console.log(data,'2222')
        //    window.location.href = "vip-list.html";
       },
       error: function (xhr) {
           //alert('动态页有问题噶！\n\n' + xhr.responseText);
       },
       traditional: true,
   })
}