var url = location.href;
var fileList;
var numArr = [];
var pageNum = 0;
$(document).ready(function () {
    show();
});

function show() {
    $("#showZjsdjl").html("");
    var theRequest = new Object();
    var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
    if (url.indexOf("?") != -1) {
        for (var i = 0; i < paraString.length; i++) {
            theRequest[paraString[i].split("=")[0]] = unescape(paraString[i].split("=")[1]);
        }
    }
    var ywid = theRequest["id"];
    $.ajax({
        url: "/zjsd/showzjsdList",
        type: 'post',
        data: ywid,
        success: function (data) {
            fileList = data;
            for (var i = 0; i < fileList.length; i++) {
                $("#btn").css("display", "none");
                $("#showZjsdjl").append("<div align='center' class='layui-nav-img'><a href='/zjsd/downloadFile?url=" + fileList[i] + "'>" +
                    "<img style='width: 30%;height: 40%' src='/zjsd/showZjsdjlImage?url=" + fileList[i] + "'/>" +
                    "</a><p>请点击图片下载</p></div>")
            }
        }, error: function () {
            alert("查询送达记录失败")
        }
    });
}