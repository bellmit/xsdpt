$(document).ready(function(){
    checkExplore();
});
function checkExplore() {
    var explorer = window.navigator.userAgent.toLowerCase();
    if (explorer.indexOf("msie") >= 0) {
        var ver = explorer.match(/msie ([\d.]+)/)[1];
        if(ver<=8){
            layer.confirm('检测到当前浏览器不兼容,正在为您跳转chrome浏览器,尚未安装的请点击下载', {
                btn: ['确定', '下载']
                , btn1: function (index, layero) {
                    $.ajax({
                        url: "/getYysdAddress.aj",
                        type:"post",
                        success: function (data) {
                            var objShell= new ActiveXObject("WScript.Shell");
                            objShell.Run("cmd.exe /c start chrome "+data,0,true);
                            layer.close(index);
                        },error:function () {
                            alert("获取地址信息失败")
                        }
                    });
                }, btn2: function (index, layero) {
                    window.open("chromeDownload.do?version=64");
                    layer.close(index);
                }
            });

        }
        }else {
        $.ajax({
            url: "/getYysdAddress.aj",
            type:"post",
            success: function (data) {
                location.href=data;
                layer.close(index);
            },error:function () {
                alert("获取地址信息失败")
            }
        });
    }
}