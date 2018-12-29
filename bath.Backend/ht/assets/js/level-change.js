
var storage=window.localStorage;
var thisname=storage["thisLevelName"];
var thisCardLimit=storage["thisLevelCardLimit"];
var thisPrice=storage["thisLevelPrice"];
var thisCourseDiscountedRatio=storage["thisCourseDiscountedRatio"];
var thisCheckCardPrice=storage["thisCheckCardPrice"];
var url=getUrl();
document.getElementById("name").innerText=thisname;
document.getElementById("cardLimit").value=thisCardLimit;
document.getElementById("price").value=thisPrice;
document.getElementById("courseDiscountedRatio").value=thisCourseDiscountedRatio;
document.getElementById("checkCardPrice").value=thisCheckCardPrice;
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
document.getElementById("ad").onclick=function() {
    if(checkRate("cardLimit")&&checkRate("price")&&checkRate("courseDiscountedRatio")&&checkRate("checkCardPrice")) {
        var name = $("#name").val();
        var cardLimit = $("#cardLimit").val();
        var price = $("#price").val();
        var courseDiscountedRatio=$("#courseDiscountedRatio").val();
        var checkCardPrice=$("#checkCardPrice").val();
        var url = getUrl();
        $.ajax(
            {
                url: url + "/updateLevel",
                data: {
                    name: document.getElementById("name").innerText,
                    cardLimit: cardLimit,
                    price: price,
                    courseDiscountedRatio:courseDiscountedRatio,
                    checkCardPrice:checkCardPrice
                },
                success: function (data) {
                    alert("修改成功");
                    window.location.href = "level.html";
                },
                error: function (xhr) {
                    alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )
    }

}