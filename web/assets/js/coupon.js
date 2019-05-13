$("#your-alert-1").hide();
$("#your-alert-2").hide();
$("#your-alert-3").hide();
$("#your-alert-4").hide();
$("#your-alert-5").hide();
$("#page1").hide();
$("#page2").hide();
$("#page3").hide();
$("#page4").hide();
$("#page5").hide();
var list=new Array();
var firstID=0;
var theGroup=0;
var url=getUrl();
window.onload=function(){ 
 this.getCouponList(list);
}
function getCouponList(list){

   if(list.length==0){
    $.ajax(
        {
            // 查看所有优惠券列表
            url: url+"/groupon/ordinary",
            data: {
            },
            async:false,
            success: function (data) {
                //console.log(data,'couponlistaaaaaaaa')
                for(var i=0;i<data.groupons.length;i++){
                    list.push(data.groupons[i]);
                    
                }
                console.log(list,"listlistlisr")
                document.getElementById("jilu").innerText="共"+(list.length)+"条记录";
                changepage(1,list);
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )
}else{
    document.getElementById("jilu").innerText="共"+(list.length)+"条记录";
    changepage(1,list);
}
}

function setthisquestion(id){
    console.log($("#"+id).text(),list)
    var couponId = $("#"+id).text()
    //alert(n);
  
    var storage = window.localStorage;
    storage.setItem("thisCoupon",couponId);
    console.log(storage.getItem("thisCoupon"),11111)
    window.location.href="../web/coupon-edit.html"
    /*
    var q=list[firstID+n];
    var storage = window.localStorage;
    // storage["thisUser"]=q.openid;
    storage["thisCoupon"]=q.id;*/
}
function deletequestion(n){
    console.log(list,"ffddfdfdf",n)
    var r=confirm("确定删除么？");
    if(r) {
        var q = list[firstID + n];
        var url = getUrl();
        $.ajax(
            {
                type: 'POST',
                url: url + "/groupon/delete?id=" +q.id,
                async: false,
                success: function (data) {
                    alert("删除成功");
                    window.location.href = "coupon.html";
                },
                error: function (xhr) {
                    alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )
    }
}



function changegroup(to){
    $("#page1").show();
    $("#page2").show();
    $("#page3").show();
    $("#page4").show();
    $("#page5").show();
    if(to==1){
        if(theGroup!=0){
            theGroup=theGroup-1;
        }
    }
    else{
        theGroup=theGroup+1;
    }
    document.getElementById("page1").innerText=theGroup*5+1;
    document.getElementById("page2").innerText=theGroup*5+2;
    document.getElementById("page3").innerText=theGroup*5+3;
    document.getElementById("page4").innerText=theGroup*5+4;
    document.getElementById("page5").innerText=theGroup*5+5;
    changepage(1,list);
}
function changepage(page,list){
    console.log(list.length,'llll',list)
    $("#page1").show();
    $("#page2").show();
    $("#page3").show();
    $("#page4").show();
    $("#page5").show();
    firstID=(theGroup*5+page-1)*5;
    if(list.length<(theGroup*25+21)){
        $("#page5").hide();
        if(list.length<(theGroup*25+16)){
            $("#page4").hide();
            if(list.length<(theGroup*25+11)){
                $("#page3").hide();
                if(list.length<(theGroup*25+6)){
                    $("#page2").hide();
                    if(list.length<(theGroup*25+1)){
                        $("#page1").hide();
                    }
                }
            }
        }

    }
    if(list.length<(firstID+1)){
        $("#your-alert-1").hide();
        $("#your-alert-2").hide();
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+2)){
        $("#your-alert-1").show();
        document.getElementById("id"+(firstID%5+1)).innerText=list[firstID].id;
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].name;
        
        document.getElementById("image"+(firstID%5+1)).innerText='';
        var img = new Image();
        img.src = "https://www.junrongcenter.com/"+list[firstID].image;
        document.getElementById("image"+(firstID%5+1)).appendChild(img);
       
        document.getElementById("description"+(firstID%5+1)).innerText=list[firstID].description;
        document.getElementById("type"+(firstID%5+1)).innerText=list[firstID].type;
        document.getElementById("takeEffectTime"+(firstID%5+1)).innerText=formatDate(list[firstID].takeEffectTime);
        document.getElementById("loseEffectTime"+(firstID%5+1)).innerText=formatDate(list[firstID].loseEffectTime);
        document.getElementById("price"+(firstID%5+1)).innerText=list[firstID].price;
        document.getElementById("originalPrice"+(firstID%5+1)).innerText=list[firstID].originalPrice;
        document.getElementById("amount"+(firstID%5+1)).innerText=list[firstID].amount;
        $("#your-alert-2").hide();
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+3)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        document.getElementById("id"+(firstID%5+1)).innerText=list[firstID].id;
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].name;
        document.getElementById("image"+(firstID%5+1)).innerText='';
        var img = new Image();
        img.src = "https://www.junrongcenter.com/"+list[firstID].image;
        document.getElementById("image"+(firstID%5+1)).appendChild(img);
        document.getElementById("description"+(firstID%5+1)).innerText=list[firstID].description;
        document.getElementById("type"+(firstID%5+1)).innerText=list[firstID].type;
        document.getElementById("takeEffectTime"+(firstID%5+1)).innerText=formatDate(list[firstID].takeEffectTime);
        document.getElementById("loseEffectTime"+(firstID%5+1)).innerText=formatDate(list[firstID].loseEffectTime);
        document.getElementById("price"+(firstID%5+1)).innerText=list[firstID].price;
        document.getElementById("originalPrice"+(firstID%5+1)).innerText=list[firstID].originalPrice;
        document.getElementById("amount"+(firstID%5+1)).innerText=list[firstID].amount;

         document.getElementById("id"+(firstID%5+2)).innerText=list[firstID+1].id;
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1].name;
        document.getElementById("image"+(firstID%5+2)).innerText='';
        var img = new Image();
        img.src = "https://www.junrongcenter.com/"+list[firstID+1].image;
        document.getElementById("image"+(firstID%5+2)).appendChild(img);
        document.getElementById("description"+(firstID%5+2)).innerText=list[firstID+1].description;
        document.getElementById("type"+(firstID%5+2)).innerText=list[firstID+1].type;
        document.getElementById("takeEffectTime"+(firstID%5+2)).innerText=formatDate(list[firstID+1].takeEffectTime);
        document.getElementById("loseEffectTime"+(firstID%5+2)).innerText=formatDate(list[firstID+1].loseEffectTime);
        document.getElementById("price"+(firstID%5+2)).innerText=list[firstID+1].price;
        document.getElementById("originalPrice"+(firstID%5+2)).innerText=list[firstID+1].originalPrice;
        document.getElementById("amount"+(firstID%5+2)).innerText=list[firstID+1].amount;
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+4)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        document.getElementById("id"+(firstID%5+1)).innerText=list[firstID].id;
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].name;
        document.getElementById("image"+(firstID%5+1)).innerText='';
      // '<img src="https://www.junrongcenter.com/'+list[firstID].image+'" alt="">'
      //  document.getElementById("image"+(firstID%5+1)).innerText=  '<img src="https://www.junrongcenter.com/'+list[firstID].image+'" alt="">';
      //img.src = "https://www.junrongcenter.com/record/groupon/66cb577649aa4000ab572097034031c0.jpg";
      var img = new Image();
      img.src = "https://www.junrongcenter.com/"+list[firstID].image;      
      document.getElementById("image"+(firstID%5+1)).appendChild(img);
     
      document.getElementById("description"+(firstID%5+1)).innerText=list[firstID].description;
        document.getElementById("type"+(firstID%5+1)).innerText=list[firstID].type;
        console.log(list[firstID].takeEffectTime,'sssssss',formatDate(list[firstID].takeEffectTime))
        document.getElementById("takeEffectTime"+(firstID%5+1)).innerText=formatDate(list[firstID].takeEffectTime)
        document.getElementById("loseEffectTime"+(firstID%5+1)).innerText=formatDate(list[firstID].loseEffectTime);
        document.getElementById("price"+(firstID%5+1)).innerText=list[firstID].price;
        document.getElementById("originalPrice"+(firstID%5+1)).innerText=list[firstID].originalPrice;
        document.getElementById("amount"+(firstID%5+1)).innerText=list[firstID].amount;

        document.getElementById("id"+(firstID%5+2)).innerText=list[firstID+1].id;
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1].name;
        document.getElementById("image"+(firstID%5+2)).innerText='';
        var img = new Image();
        img.src = "https://www.junrongcenter.com/"+list[firstID+1].image;  
        console.log(img,"sdsdlalalalalalla")      
        document.getElementById("image"+(firstID%5+2)).appendChild(img);

        document.getElementById("description"+(firstID%5+2)).innerText=list[firstID+1].description;
        document.getElementById("type"+(firstID%5+2)).innerText=list[firstID+1].type;
        document.getElementById("takeEffectTime"+(firstID%5+2)).innerText=formatDate(list[firstID+1].takeEffectTime);
        document.getElementById("loseEffectTime"+(firstID%5+2)).innerText=formatDate(list[firstID+1].loseEffectTime);
        document.getElementById("price"+(firstID%5+2)).innerText=list[firstID+1].price;
        document.getElementById("originalPrice"+(firstID%5+2)).innerText=list[firstID+1].originalPrice;
        document.getElementById("amount"+(firstID%5+2)).innerText=list[firstID+1].amount;

        document.getElementById("id"+(firstID%5+3)).innerText=list[firstID+2].id;
        document.getElementById("name"+(firstID%5+3)).innerText=list[firstID+2].name;
        var img = new Image();
        document.getElementById("image"+(firstID%5+3)).innerText='';
        img.src = "https://www.junrongcenter.com/"+list[firstID+2].image;
        document.getElementById("image"+(firstID%5+3)).appendChild(img);
        document.getElementById("description"+(firstID%5+3)).innerText=list[firstID+2].description;
        document.getElementById("type"+(firstID%5+3)).innerText=list[firstID+2].type;
        document.getElementById("takeEffectTime"+(firstID%5+3)).innerText=formatDate(list[firstID+2].takeEffectTime);
        document.getElementById("loseEffectTime"+(firstID%5+3)).innerText=formatDate(list[firstID+2].loseEffectTime);
        document.getElementById("price"+(firstID%5+3)).innerText=list[firstID+2].price;
        document.getElementById("originalPrice"+(firstID%5+3)).innerText=list[firstID+2].originalPrice;
        document.getElementById("amount"+(firstID%5+3)).innerText=list[firstID+2].amount;
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+5)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        document.getElementById("id"+(firstID%5+1)).innerText=list[firstID].id;
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].name;
        document.getElementById("image"+(firstID%5+1)).innerText='';
        var img = new Image();
        img.src = "https://www.junrongcenter.com/"+list[firstID].image;
        document.getElementById("image"+(firstID%5+1)).appendChild(img);
        document.getElementById("description"+(firstID%5+1)).innerText=list[firstID].description;
        document.getElementById("type"+(firstID%5+1)).innerText=list[firstID].type;
        
        document.getElementById("takeEffectTime"+(firstID%5+1)).innerText=formatDate(list[firstID].takeEffectTime);
        console.log(list[firstID].takeEffectTime,'sssssss',formatDate(list[firstID].takeEffectTime))
        document.getElementById("loseEffectTime"+(firstID%5+1)).innerText=formatDate(list[firstID].loseEffectTime);
        document.getElementById("price"+(firstID%5+1)).innerText=list[firstID].price;
        document.getElementById("originalPrice"+(firstID%5+1)).innerText=list[firstID].originalPrice;
        document.getElementById("amount"+(firstID%5+1)).innerText=list[firstID].amount;

         document.getElementById("id"+(firstID%5+2)).innerText=list[firstID+1].id;
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1].name;
        document.getElementById("image"+(firstID%5+2)).innerText='';
        var img = new Image();
        img.src = "https://www.junrongcenter.com/"+list[firstID+1].image;
        document.getElementById("image"+(firstID%5+2)).appendChild(img);
        document.getElementById("description"+(firstID%5+2)).innerText=list[firstID+1].description;
        document.getElementById("type"+(firstID%5+2)).innerText=list[firstID+1].type;
        document.getElementById("takeEffectTime"+(firstID%5+2)).innerText=formatDate(list[firstID+1].takeEffectTime);
        document.getElementById("loseEffectTime"+(firstID%5+2)).innerText=formatDate(list[firstID+1].loseEffectTime);
        document.getElementById("price"+(firstID%5+2)).innerText=list[firstID+1].price;
        document.getElementById("originalPrice"+(firstID%5+2)).innerText=list[firstID+1].originalPrice;
        document.getElementById("amount"+(firstID%5+2)).innerText=list[firstID+1].amount;

        document.getElementById("id"+(firstID%5+3)).innerText=list[firstID+2].id;
        document.getElementById("name"+(firstID%5+3)).innerText=list[firstID+2].name;
        document.getElementById("image"+(firstID%5+3)).innerText='';
        var img = new Image();
        img.src = "https://www.junrongcenter.com/"+list[firstID+2].image;
        document.getElementById("image"+(firstID%5+3)).appendChild(img);
        document.getElementById("description"+(firstID%5+3)).innerText=list[firstID+2].description;
        document.getElementById("type"+(firstID%5+3)).innerText=list[firstID+2].type;
        document.getElementById("takeEffectTime"+(firstID%5+3)).innerText=formatDate(list[firstID+2].takeEffectTime);
        document.getElementById("loseEffectTime"+(firstID%5+3)).innerText=formatDate(list[firstID+2].loseEffectTime);
        document.getElementById("price"+(firstID%5+3)).innerText=list[firstID+2].price;
        document.getElementById("originalPrice"+(firstID%5+3)).innerText=list[firstID+2].originalPrice;
        document.getElementById("amount"+(firstID%5+3)).innerText=list[firstID+2].amount;

        document.getElementById("id"+(firstID%5+4)).innerText=list[firstID+3].id;
        document.getElementById("name"+(firstID%5+4)).innerText=list[firstID+3].name;
        document.getElementById("image"+(firstID%5+4)).innerText='';
        var img = new Image();
        img.src = "https://www.junrongcenter.com/"+list[firstID+3].image;
        document.getElementById("image"+(firstID%5+4)).appendChild(img);
        document.getElementById("description"+(firstID%5+4)).innerText=list[firstID+3].description;
        document.getElementById("type"+(firstID%5+4)).innerText=list[firstID+3].type;
        document.getElementById("takeEffectTime"+(firstID%5+4)).innerText=formatDate(list[firstID+3].takeEffectTime);
        document.getElementById("loseEffectTime"+(firstID%5+4)).innerText=formatDate(list[firstID+3].loseEffectTime);
        console.log(list[firstID+3].takeEffectTime,'saaaaaaaa',formatDate(list[firstID+3].takeEffectTime))
        document.getElementById("price"+(firstID%5+4)).innerText=list[firstID+3].price;
        document.getElementById("originalPrice"+(firstID%5+4)).innerText=list[firstID+3].originalPrice;
        document.getElementById("amount"+(firstID%5+4)).innerText=list[firstID+3].amount;
        $("#your-alert-5").hide();
    }
    else{
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        $("#your-alert-5").show();
        document.getElementById("id"+(firstID%5+1)).innerText=list[firstID].id;
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID].name;
        document.getElementById("image"+(firstID%5+1)).innerText='';
        var img = new Image();
        img.src = "https://www.junrongcenter.com/"+list[firstID].image;
        document.getElementById("image"+(firstID%5+1)).appendChild(img);
        document.getElementById("description"+(firstID%5+1)).innerText=list[firstID].description;
        document.getElementById("type"+(firstID%5+1)).innerText=list[firstID].type;
        document.getElementById("takeEffectTime"+(firstID%5+1)).innerText=formatDate(list[firstID].takeEffectTime);
        document.getElementById("loseEffectTime"+(firstID%5+1)).innerText=formatDate(list[firstID].loseEffectTime);
        document.getElementById("price"+(firstID%5+1)).innerText=list[firstID].price;
        document.getElementById("originalPrice"+(firstID%5+1)).innerText=list[firstID].originalPrice;
        document.getElementById("amount"+(firstID%5+1)).innerText=list[firstID].amount;

         document.getElementById("id"+(firstID%5+2)).innerText=list[firstID+1].id;
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1].name;
        document.getElementById("image"+(firstID%5+2)).innerText='';
        var img = new Image();
        img.src = "https://www.junrongcenter.com/"+list[firstID+1].image;
        document.getElementById("image"+(firstID%5+2)).appendChild(img);
        document.getElementById("description"+(firstID%5+2)).innerText=list[firstID+1].description;
        document.getElementById("type"+(firstID%5+2)).innerText=list[firstID+1].type;
        document.getElementById("takeEffectTime"+(firstID%5+2)).innerText=formatDate(list[firstID+1].takeEffectTime);
        document.getElementById("loseEffectTime"+(firstID%5+2)).innerText=formatDate(list[firstID+1].loseEffectTime);
        document.getElementById("price"+(firstID%5+2)).innerText=list[firstID+1].price;
        document.getElementById("originalPrice"+(firstID%5+2)).innerText=list[firstID+1].originalPrice;
        document.getElementById("amount"+(firstID%5+2)).innerText=list[firstID+1].amount;

        document.getElementById("id"+(firstID%5+3)).innerText=list[firstID+2].id;
        document.getElementById("name"+(firstID%5+3)).innerText=list[firstID+2].name;
        document.getElementById("image"+(firstID%5+3)).innerText='';
        var img = new Image();
        img.src = "https://www.junrongcenter.com/"+list[firstID+2].image;
        document.getElementById("image"+(firstID%5+3)).appendChild(img);
        document.getElementById("description"+(firstID%5+3)).innerText=list[firstID+2].description;
        document.getElementById("type"+(firstID%5+3)).innerText=list[firstID+2].type;
        document.getElementById("takeEffectTime"+(firstID%5+3)).innerText=formatDate(list[firstID+2].takeEffectTime);
        document.getElementById("loseEffectTime"+(firstID%5+3)).innerText=formatDate(list[firstID+2].loseEffectTime);
        document.getElementById("price"+(firstID%5+3)).innerText=list[firstID+2].price;
        document.getElementById("originalPrice"+(firstID%5+3)).innerText=list[firstID+2].originalPrice;
        document.getElementById("amount"+(firstID%5+3)).innerText=list[firstID+2].amount;

        document.getElementById("id"+(firstID%5+4)).innerText=list[firstID+3].id;
        document.getElementById("name"+(firstID%5+4)).innerText=list[firstID+3].name;
        document.getElementById("image"+(firstID%5+4)).innerText='';
        var img = new Image();
        img.src = "https://www.junrongcenter.com/"+list[firstID+3].image;
        document.getElementById("image"+(firstID%5+4)).appendChild(img);
        document.getElementById("description"+(firstID%5+4)).innerText=list[firstID+3].description;
        document.getElementById("type"+(firstID%5+4)).innerText=list[firstID+3].type;
        document.getElementById("takeEffectTime"+(firstID%5+4)).innerText=formatDate(list[firstID+3].takeEffectTime);
        document.getElementById("loseEffectTime"+(firstID%5+4)).innerText=formatDate(list[firstID+3].loseEffectTime);
        document.getElementById("price"+(firstID%5+4)).innerText=list[firstID+3].price;
        document.getElementById("originalPrice"+(firstID%5+4)).innerText=list[firstID+3].originalPrice;
        document.getElementById("amount"+(firstID%5+4)).innerText=list[firstID+3].amount;

        document.getElementById("id"+(firstID%5+5)).innerText=list[firstID+4].id;
        document.getElementById("name"+(firstID%5+5)).innerText=list[firstID+4].name;
        document.getElementById("image"+(firstID%5+5)).innerText='';
        var img = new Image();
        img.src = "https://www.junrongcenter.com/"+list[firstID+4].image;
        document.getElementById("image"+(firstID%5+5)).appendChild(img);
        document.getElementById("description"+(firstID%5+5)).innerText=list[firstID+4].description;
        document.getElementById("type"+(firstID%5+5)).innerText=list[firstID+4].type;
        document.getElementById("takeEffectTime"+(firstID%5+5)).innerText=formatDate(list[firstID+4].takeEffectTime);
        document.getElementById("loseEffectTime"+(firstID%5+5)).innerText=formatDate(list[firstID+4].loseEffectTime);
        document.getElementById("price"+(firstID%5+5)).innerText=list[firstID+4].price;
        document.getElementById("originalPrice"+(firstID%5+5)).innerText=list[firstID+4].originalPrice;
        document.getElementById("amount"+(firstID%5+5)).innerText=list[firstID+4].amount;
    }

}

function deletesingle(n){
    var q=list[firstID+n];
    var url=getUrl();
    $.ajax(
        {
            type: 'POST',
            url: url + "/groupon/delete?id=" + q.id,
            async: false,
            success: function (data) {
                alert("批量删除成功");
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )
}
function delAll(){
    if(document.getElementById("c1").checked){
        deletesingle(1);
    }
    if(document.getElementById("c2").checked){
        deletesingle(2);
    }
    if(document.getElementById("c3").checked){
        deletesingle(3);
    }
    if(document.getElementById("c4").checked){
        deletesingle(4);
    }
    if(document.getElementById("c5").checked){
        deletesingle(5);
    }
    alert("批量删除成功");
    window.location.href="coupon.html";

}
function formatDate(value) {
    var ss = Date.parse(value);
      var date = new Date(ss);  
            var y = date.getFullYear();    
            var m = date.getMonth() + 1;    
            m = m < 10 ? ('0' + m) : m;    
            var d = date.getDate();    
            d = d < 10 ? ('0' + d) : d;    
            var h = date.getHours();  
            h = h < 10 ? ('0' + h) : h;  
            var minute = date.getMinutes();  
            var second = date.getSeconds();  
            minute = minute < 10 ? ('0' + minute) : minute;    
            second = second < 10 ? ('0' + second) : second;   
            return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
}
function search(){
    var text=$("#con").val();
    for(var i=0;i<list.length;i++){
        if(list[i].openid==text){
            $("#your-alert-1").show();
            document.getElementById("number"+(firstID%5+1)).innerText=list[i].openid;
            document.getElementById("name"+(firstID%5+1)).innerText=list[i].username;
            document.getElementById("date"+(firstID%5+1)).innerText=list[i].levelName;
            $("#your-alert-2").hide();
            $("#your-alert-3").hide();
            $("#your-alert-4").hide();
            $("#your-alert-5").hide();
            firstID=i-1;
        }
    }

}


function searchByName(){
    var text=$("#con").val();
    alert(text)
    var newList = new Array();
    newList.length = 0;
    list.filter(item=>{
        console.log(item.name,222,item.name.indexOf(text))

         if(item.name.indexOf(text)>=0){
            newList.push(item)
        }
        
        return item.name.indexOf(text)>0;
    })
   // list = newList
   console.log(newList,'666')
    this.getCouponList(newList);

    /*
    for(var i=0;i<list.length;i++){
        if(list[i].name==text){
            $("#your-alert-1").show();
            document.getElementById("number"+(firstID%5+1)).innerText=list[i].openid;
            document.getElementById("name"+(firstID%5+1)).innerText=list[i].username;
            document.getElementById("date"+(firstID%5+1)).innerText=list[i].levelName;
            $("#your-alert-2").hide();
            $("#your-alert-3").hide();
            $("#your-alert-4").hide();
            $("#your-alert-5").hide();
            firstID=i-1;
        }
    }*/

}


