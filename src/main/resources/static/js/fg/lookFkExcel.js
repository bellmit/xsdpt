var xtyh = {
};
var yysdbh;

$(document).ready(function () {
    var sd_way = $.cookie('way');
    xtyh.fybh = $.cookie('fybh');
    xtyh.ajxh = $.cookie('ajxh');
    xtyh.yhm = $.cookie('yhm');

    getCurrentFyName(xtyh.fybh);

    //加载送达反馈数据表
    loadSdfkData();

});

function loadSdfkData() {
    $("#sdfk-table-div").html();
    $.ajax({
        url: "/sdfk/get_sdfk_data.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        success: function (data) {
            $("#loading-div").hide();
            $("#btn-div").show();

            //封装table html
            if(data.data){
                var fkzt;
                for (var i = 0; i <data.data.length; i++) {
                    var item = data.data[i];
                    yysdbh = item.yysdbh;
                    fkzt = item.fkzt;
                    renderPage(item);
                }
                if(fkzt == 1){
                    $("#confirm-div").html("已确认");
                }else {
                    $("#confirm-div").html("<button class=\"btn btn-primary\" type=\"button\" id=\"qrsdfk-btn\">确认送达结果/退回</button>");
                }
                if(data.data.length == 0){
                    //暂无反馈信息 或 已被退回
                    $("#loading-div").html("暂无反馈信息 或 已被退回");
                    $("#loading-div").show();
                    $("#btn-div").hide();

                }
            }
        },error:function () {
            alert("获取信息失败")
        }
    });
}

function renderPage(item) {

    //拼接直接送达结果
    var zjsd = "";
    if(item.zjsdResultModels){
        for (var i = 0; i < item.zjsdResultModels.length; i++) {
            var objItem = item.zjsdResultModels[i];
            zjsd += objItem.sdfkStr;
        }
        zjsd = zjsd.replace(/\n/g,"<br/>")
    }
    //拼接电话送达结果
    var dhsd = "";
    if(item.dhsdResultModelList){
        for (var i = 0; i < item.dhsdResultModelList.length; i++) {
            var objItem = item.dhsdResultModelList[i];
            dhsd += objItem.sdfkStr;
        }
        dhsd = dhsd.replace(/\n/g,"<br/>")
    }
    //拼接电子送达结果
    var dzsd = "";
    if(item.dzsdResultModelList){
        for (var i = 0; i < item.dzsdResultModelList.length; i++) {
            var objItem = item.dzsdResultModelList[i];
            dzsd += objItem.sdfkStr;
        }
        dzsd = dzsd.replace(/\n/g,"<br/>")
    }
    //拼接来院领取结果
    var lylq = "";
    if(item.lylqResultModelList){
        for (var i = 0; i < item.lylqResultModelList.length; i++) {
            var objItem = item.lylqResultModelList[i];
            lylq += objItem.sdfkStr;
        }
        lylq = lylq.replace(/\n/g,"<br/>")
    }
    //拼接来院领取结果
    var ems = "";
    if(item.emsResultModelList){
        for (var i = 0; i < item.emsResultModelList.length; i++) {
            var objItem = item.emsResultModelList[i];
            ems += objItem.sdfkStr;
        }
        ems = ems.replace(/\n/g,"<br/>")
        ems = ems.replace(/\t/g,"&nbsp;&nbsp;&nbsp;&nbsp;")
    }
    var webTels = "";
    if(item.webTels){
        webTels = item.webTels.replace(/\n/g,"<br/>")
    }

    var sdqrWebTels = "";
    if(item.sdqrWebTels){
        sdqrWebTels = item.sdqrWebTels.replace(/\n/g,"<br/>")
    }
    var address = "";
    if(item.webTels){
        address = item.address.replace(/\n/g,"<br/>")
    }


    var html = "<table class=\"table table-bordered table-condensed sdfk-table\">\n" +
        "            <tr>\n" +
        "                <td colspan=\"4\" class=\"td-head-label\" align=\"center\">送达反馈表</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">案号</td>\n" +
        "                <td colspan=\"3\" class=\"td-param\">"+item.ah+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">承办法官</td>\n" +
        "                <td colspan=\"3\" class=\"td-param\">"+item.cbfgmc+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">法官预约备注</td>\n" +
        "                <td colspan=\"3\" class=\"td-param\">"+item.yysdbz+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">案由</td>\n" +
        "                <td colspan=\"3\" class=\"td-param\">"+item.anyou+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">整案送达结果</td>\n" +
        "                <td colspan=\"3\" class=\"td-param\">"+item.zasdjg+"</td>\n" +

        "            </tr>\n" +

        "            <tr>\n" +
        "                <td class=\"td-label\">受送达人</td>\n" +
        "                <td class=\"td-param\">"+item.ssdrMc+"</td>\n" +
        "                <td class=\"td-label\">身份证号</td>\n" +
        "                <td class=\"td-param\">"+item.sfzHm+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">受送达人送达结果</td>\n" +
        "                <td class=\"td-param\">"+item.ssdrSdjg+"</td>\n" +
        "                <td class=\"td-label\">送达成功方式</td>\n" +
        "                <td class=\"td-param\">"+item.sdcgfs+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">送达文书</td>\n" +
        "                <td colspan=\"3\" class=\"td-param\">"+item.sdwsMcs+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">联系电话</td>\n" +
        "                <td class=\"td-param\">"+webTels+"</td>\n" +
        "                <td class=\"td-label\">送达中心确认联系电话</td>\n" +
        "                <td class=\"td-param\">"+sdqrWebTels+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">联系地址</td>\n" +
        "                <td colspan=\"3\" class=\"td-param\">"+address+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">是否签署送达地址确认书</td>\n" +
        "                <td class=\"td-param\">"+item.addressQrs+"</td>\n" +
        "                <td class=\"td-label\">是否同意电子送达</td>\n" +
        "                <td class=\"td-param\">"+item.dzshQr+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">电话送达结果</td>\n" +
        "                <td colspan=\"3\" class=\"td-param-left\">"+dhsd+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">电子送达结果</td>\n" +
        "                <td colspan=\"3\" class=\"td-param-left\">"+dzsd+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">来院领取结果</td>\n" +
        "                <td colspan=\"3\" class=\"td-param-left\">"+lylq+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">EMS送达结果</td>\n" +
        "                <td colspan=\"3\" class=\"td-param-left\">"+ems+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">直接送达结果</td>\n" +
        "                <td colspan=\"3\" class=\"td-param-left\">"+zjsd+"</td>\n" +
        "            </tr>\n" +
        // "            <tr>\n" +
        // "                <td class=\"td-label\">公告送达结果</td>\n" +
        // "                <td colspan=\"3\" class=\"td-param-left\">"+ggsd+"</td>\n" +
        // "            </tr>\n" +
        "            <tr>\n" +
        "                <td class=\"td-label\">备注</td>\n" +
        "                <td colspan=\"3\" class=\"td-param\">"+item.remark+"</td>\n" +
        "            </tr>\n" +
        "            <tr>\n" +
        // "                <td class=\"td-label\">整案送达结果</td>\n" +
        // "                <td class=\"td-param\">"+item.sdjg+"</td>\n" +
        "                <td class=\"td-label\">送达专员</td>\n" +
        "                <td colspan=\"3\" class=\"td-param\">"+item.sdzyName+"</td>\n" +
        "            </tr>\n" +
        "        </table>";

    $("#sdfk-table-div").append(html);
}

$("#download_sdfk_excel").on("click",function () {
   if(yysdbh){
       location.href = "sdfk/download_sdfk_excel.zf?yysdbh="+yysdbh;
   } else {
       alert("数据加载中，请稍后！")
   }
});

$("#download_sdfk_pdf").on("click",function () {
    if(yysdbh){
        location.href = "sdfk/downloadSdfkb.do?yysdbh="+yysdbh;
    } else {
        alert("数据加载中，请稍后！")
    }
});


$("#confirm-div").on("click","#qrsdfk-btn",function () {
    if(yysdbh){
        layer.confirm('是否确认当前工单送达结果', {
            btn: ['确认', '退回']
            , btn1: function (index, layero) {
                qrsdfk(true);
            }, btn2: function (index, layero) {
                qrsdfk(false);
            }
        });
    } else {
        alert("数据加载中，请稍后！")
    }
});

/**
 * 确认送达反馈方法
 * @param isQr  true > 确认  false => 退回
 */
function qrsdfk(isQr) {
    var dataJson = {
        yysdbh: yysdbh,
        confirmState: isQr
    }
    $.ajax({
        url: "/sdfk/confirm_sdfk.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(dataJson),
        success: function (data) {

            if(isQr){
                location.reload();
            }else {
                alert("已退回");
                window.close();
            }
        },error:function () {
            alert("处理异常")
        }
    });
}
