var url = location.href;
var fileList;
var numArr = [];
var pageNum = 0;
$(document).ready(function () {
    show();
});

function show() {
    $("#showImages").html("");
    var theRequest = new Object();
    var paraString = url.substring(url.indexOf("？") + 1, url.length).split("&");
    if (url.indexOf("?") != -1) {
        for (var i = 1; i < paraString.length; i++) {
            theRequest[paraString[i].split("=")[0]] = unescape(paraString[i].split("=")[1]);
        }
    }
    var ywid = 0;
    ywid = theRequest["id"];
    $.ajax({
        url: "/dxtz/showZyList",
        type: 'post',
        data: {
            ywid:ywid
        },
        success: function (data) {
            fileList = data;
            for (var i = 0; i < fileList.length; i++) {
                if (fileList[i].zyurl.indexOf(".pdf") != -1) {
                    numArr.push(fileList[i].id);
                    $("#btn").css("display", "block");
                    $("#totalNum").html(numArr.length);
                    $("#pageNum").html(1);
                    $("#showImages").append("<embed style='position: absolute;overflow:auto;width: 100%;height: 100%' src='/dxtz/showZy?id=" + numArr[0] + "'></embed>")
                } else {
                    // console.log(fileList[i].id)
                    $("#btn").css("display", "none");
                    $("#showImages").append("<div align='center' class='layui-nav-img'><a href='/dxtz/downloadFile?id=" + fileList[i].id + "'>" +
                        "<img style='width: 30%;height: 40%' src='/dxtz/showZy?id=" + fileList[i].id + "'/>" +
                        "</a><p>请点击图片下载</p></div>")
                }
            }
        }, error: function () {
            alert("查询文书详情失败")
        }
    });
}

var num = 0;

function up() {
    pageNum--;
    if (pageNum >= 0) {
        num = numArr[pageNum]
        $("#pageNum").html(pageNum+1)
        $("#showImages").html("<embed style='position: absolute;overflow:auto;width: 100%;height: 100%' src='/dxtz/showZy?id=" + num + "'></embed>")
    } else {
        pageNum++;
        alert("当前已是第一页")
    }

}

function down() {
    pageNum++;
    if (pageNum < numArr.length) {
        num = numArr[pageNum]
        $("#pageNum").html(pageNum+1)
        $("#showImages").html("<embed style='position: absolute;overflow:auto;width: 100%;height: 100%' src='/dxtz/showZy?id=" + num + "'></embed>")
    } else {
        pageNum--;
        alert("当前已是最后一页")
    }
}