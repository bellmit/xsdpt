var ajInfo;
var dsrjbVO;
var logData;
var logPage=1;
var typeArr = [];
var usrList = [];
var thisgd=0;
var wslb=0;
$(function () {
    getAjInfo();
    getDhsdInfo();
    getDxsdInfo();
    getDzsdInfo();
    getEmssdInfo();
    getGgsdInfo();
    getLylqInfo();
    getZhiJsdInfo();
    getLog();

    $("#jbxx_table tr>td:nth-child(4)").text("");
})


$(document).ready(function () {

    getCurrentFyNameBySessionFybh();

    layui.use("laydate",function () {

        layui.laydate.render({
            elem:"#lylq-yysj-input",
            type: "datetime",
            min: -7,
            format: "yyyy-MM-dd HH:mm"
        })
    })



});


function getLog() {
    $("#buttom_log_ul").empty();
    $.ajax({
        url: "/getlogFg.aj",
        type:'POST',
        dataType: 'json',
        success: function (data) {
            logData = data;
            showLog(1);

        }
    });
}

//获取电话送达列表
function  getDhsdInfo() {
    var phoneStatus = ["", "有效-本人", "可联-非本人", "可联-关机", "可联-停机", "可联-未接通", "可联-再跟进", "可联-正忙", "空号", "呼叫受限", "可联-挂机","代理人接听","代理律师接听"];
    $.ajax({
        url: "/getDhsdInfo.do",
        type:'POST',
        dataType: 'json',
        success: function (data) {
            $("#dhsdxx").empty();
            for (var i = 0; i < data.length; i++) {
                for (var i = 0; i < data.length; i++) {
                    var html = "<tr>\n" +
                        "                        <td>" + data[i].ssdrmc + "</td>\n" +
                        "                        <td>" + data[i].webtel + "</td>\n" +
                        "                        <td>" + data[i].whTime + "</td>\n" +
                        "                        <td>" + phoneStatus[data[i].callstate] + "</td>\n" ;
                    if( data[i].callduration!=null){
                        html+=  "                        <td>" + data[i].callduration + "</td>\n";
                    }else {
                        html += "<td></td>";
                    }
                    html+= "                        <td>" + data[i].seatnumber + "</td>\n";
                    html+= "                        <td>" + data[i].remarks + "</td>\n";
                    if (data[i].callduration > 0) {
                        html += "                        <td>" +
                            "                            <button onclick='downloadCallRadio(" + data[i].webcallid + ")' class=\"myButton\"><img src=\"/css/img/download_icon.png\" width=\"45px\" height=\"25px\"\n" +
                            "                                                          style=\"margin-left: 10px\"></button>\n" +
                            "                        </td>";
                    } else {
                        html += "<td></td>";
                    }
                    html += "</tr>";
                    $("#dhsdxx").append(html);
                }
            }

        }
    });
}

//获取短信送达列表
function  getDxsdInfo() {
  $.ajax({
        url: "/getDxsdInfo.do",
        type:'POST',
        dataType: 'json',
        success: function (data) {
            $("#dxtzxx").empty();
            for (var i = 0; i < data.length; i++) {
                var dxtz_html = "<tr>\n" +
                    "                        <td>" + data[i].ssdrmc + "</td>\n" +
                    "                        <td>" + data[i].dxly + "</td>\n" +
                    "                        <td>" + data[i].phone + "</td>\n" +
                    "                        <td>" + data[i].time + "</td>\n" +
                    "                        <td>" + "<button onclick='showMsgContent(\"" + data[i].content + "\")' class=\"btn btn-primary\">查看</button>" + "</td>"+
                    "                        <td>" + data[i].status + "</td>\n"+
                    "<td>"+data[i].fwzt+"</td>";
                if(data[i].dxtzid){
                    dxtz_html+=   "<td>" + "<button onclick='showImages(\"" + data[i].dxtzid + "\")' class=\"btn btn-primary\">查看</button>" + "</td>";
                }else {
                    dxtz_html+=   "<td>暂无</td>" ;
                }

                dxtz_html += "</tr>";
                $("#dxtzxx").append(dxtz_html);
            }

        }
    });
}

function showMsgContent(msgcontent) {
    layui.use("layer", function () {
        layui.layer.alert(msgcontent);
    })
}

/**
 * 查看 送达回执 单击事件
 */
$('#emssd_table').on("click",".look_sdhz",function () {
    var kdid = $(this).data("hint");
    window.open("/ems_sdhz_show.do?kdid="+kdid+"&fybh="+ajInfo.fybh);
})


function getGgsdInfo() {
    $.ajax({
        url: "/getGgsdInfo.do",
        type:'POST',
        dataType: 'json',
        success: function (data) {
            $("#ggsd_body").empty();
            for (var i = 0; i < data.length; i++) {
                $("#ggsd_table").append("<tr>\n" +
                    "    <td>" + data[i].sdr + "</td>\n" +
                    "    <td>" + data[i].tjggrq + "</td>\n" +
                    "    <td>" + data[i].ggnr + "</td>\n" +
                    "    <td>" + data[i].ggshzt + "</td>\n" +
                    "    <td>" + data[i].dcsj + "</td>\n" +
                    "</tr>\n" );
            }
        }
    });
}

function getEmssdInfo() {
    $.ajax({
        url: "/getEmssdInfo.do",
        type:'POST',
        dataType: 'json',
        success: function (data) {
            $("#emssd_tbody").empty();
            for (var i = 0; i < data.length; i++) {
                var html ='';
                html+="<tr>\n" ;
                html+= "    <td>" + data[i].sjrxm + "</td>\n" ;
                html+= "    <td>" + data[i].yjdz + "</td>\n" ;
                html+=  "    <td>" + data[i].yjsj + "</td>\n" ;
                html+= "    <td>" + data[i].kddh + "</td>\n";
                html+= "    <td>" + data[i].jjrxm + "</td>\n";
                if(data[i].sdhz!=null){
                    html+=  "    <td><button class=\"myButton look_sdhz\" data-hint='"+data[i].kdid+"'><img src=\"/css/img/download_icon.png\" width=\"45px\" height=\"25px\" style=\"margin-left: 10px\"></button></td>\n";
                }else {
                    html+="<td></td>"
                }
                html+=  "    <td><button class=\"btn btn-primary uploadEmsmd\" type=\"button\"'>上传</button></td>\n";
                html+="</tr>"
                $("#emssd_tbody").append(html);
            }
            layui.use('upload', function(){
                var upload = layui.upload;
                var uploadInst=upload.render({
                    elem: '.uploadEmsmd', //绑定元素
                    method:'POST',
                    multiple:false,
                    accept: 'images', //允许上传的文件类型
                    size: 204800, //设置文件
                    url: '/uploadEmsmd.fg', //上传接口最大可允许上传的大小，单位 KB
                    done: function(res){ //上传完毕回调
                        layer.msg("上传成功",{icon: 1});
                    },

                    error: function(){
                        //请求异常回调
                        layer.msg("上传失败",{icon: 0});
                    }
                });
            });
        }
    })
}
var dzsdInfo;
function getDzsdInfo() {
    $.ajax({
        url: "/getDzsdInfo.do",
        type:'POST',
        dataType: 'json',
        success: function (data) {
            $("#dzsd_table_body").empty();
            dzsdInfo = data;
            for (var i = 0; i < data.length; i++) {
                $("#dzsd_table_body").append("<tr>\n" +
                    "    <td>" + data[i].sdrq + "</td>\n" +
                    "    <td>" + data[i].sdnr + "</td>\n" +
                    "    <td>" + data[i].qsr + "</td>\n" +
                    "    <td>" + data[i].qsrq + "</td>\n" +
                    "    <td><button class=\"myButton\"  onclick='downloadDzsdhz("+i+")'><img src=\"/css/img/watch_icon.png\" width=\"45px\" height=\"25px\" style=\"margin-left: 10px\"></button></td>\n" +
                    "</tr>\n" );
            }
        }
    });
}

//获取来院领取列表
function  getLylqInfo() {
    var phoneStatus = ["", "有效-本人", "可联-非本人", "可联-关机", "可联-停机", "可联-未接通", "可联-再跟进", "可联-正忙", "空号", "呼叫受限", "可联-挂机","代理人接听","代理律师接听"];
    $.ajax({
        url: "/getLylqInfo.do",
        type:'POST',
        dataType: 'json',
        success: function (data) {
            $("#lylqxx").empty();
            for (var i = 0; i < data.length; i++) {
                for (var i = 0; i < data.length; i++) {
                    var html = "<tr>\n" +
                        "                        <td>" + data[i].ssdrmc + "</td>\n" +
                        "                        <td>" + data[i].fqr + "</td>\n" +
                        "                        <td>" + data[i].yylqsjStr + "</td>\n" +
                        "                        <td>" + data[i].lylqaddress + "</td>\n"  ;
                    if (data[i].lqstate == 1) {
                        html += "<td>已领取</td>";
                    } else if (data[i].lqstate == 2) {
                        html += "<td>未领取</td>";
                    } else {
                        html += "<td>待领取</td>";
                    }
                    if (data[i].sdhz != null) {
                       html += "<td><a href='lylq/imgView_lylq_sdhz.zf?lylqId=" + data[i].lylqid +"'><button class=\"btn btn-primary \" type=\"button\">查看</button></a></td>";
                    } else {
                        html += "<td>暂无</td>";
                    }
                    html += "</tr>";

                    $("#lylqxx").append(html);
                };
            }

        }
    });
}

function  getZhiJsdInfo() {

    $.ajax({
        url: "/getZjsdInfo.do",
        type:'POST',
        dataType: 'json',
        success: function (data) {
            $("#zjsdxx").empty();
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                var qsztStr = ""
                //签收结果
                if(item.qszt == null){
                    qsztStr = "暂无";
                }else if(item.qszt == 1){
                    qsztStr = "妥投";
                }else if(item.qszt == 2){
                    qsztStr = "拒收";
                }else if(item.qszt == 3){
                    qsztStr = "本人签收";
                }
                var html =""
                html +="<tr>\n" +
                    "    <td>" + item.ssdrmc + "</td>\n" +
                    "    <td>" + item.sddz + "</td>\n" +
                    "    <td>" + item.smsjStr + "</td>\n";
                if(item.sdgcjl){
                    html += "<td><button class=\"btn btn-primary\" onclick='look_zjsdjl("+item.zjsdbh+")' type=\"button\">查看</button></td>\n";
                }else {
                    html += "<td>暂无</td>\n";
                }

                html += "    <td>" + item.remark + "</td>\n" +
                    "    <td>" + qsztStr + "</td>\n";

                if(item.sdhz){
                    html += "<td><button class=\"btn btn-primary\" onclick='look_zjsdhz("+item.zjsdbh+")' type=\"button\">查看</button></td>\n";
                }else {
                    html += "<td>暂无</td>\n";
                }

                html += "</tr>\n";
                $("#zjsdxx").append(html);
            }

        }
    });

}
function look_zjsdhz(zjsdbh){
    window.open("zjsd/showSdhz?zjsdbh="+zjsdbh);
}
function look_sdhz(lylqid) {
    // window.open("lylq/imgView_lylq_sdhz.zf?lylqId="+lylqid);
}

function getAjInfo() {
    $.ajax({
        url: "/getAjDsrInfos.do",
        type:'POST',
        success: function (data) {
            ajInfo = data;
            $("#title_ah").html(data.ah);
            var userName = data.fgmc;
            $.cookie('fgmc', userName);
            $("#userName").append("<span>" + userName + "</span>&nbsp;&nbsp;");
            var ah = data.ah;
            $("#ah").append("<span>" + ah + "</span>");
            var dsrList = data.ssdrVOS;
            // console.log(dsrList);
            var test_data = {
                title: '基本例子',
                isAdmin: true,
                list: dsrList
            };
            var html = template('test', test_data);
            $("#dsrxx").append(html);

        }, error: function () {
            // alert("获取案件信息失败")
        }
    });
}

function downloadDzsdhz(i) {
    var link = dzsdInfo[i].sdhz;
    window.open(link);
}
$('#dsrxx').on("click", ".nine_button > .myButton", function (e) {
    e.stopPropagation();
    var index = $(this).attr("data-index");
    if($(this).data("type")=="dxtz.aj"){
        dxsdShow(index);
        return;
    }
    if($(this).data("type")=="lylq.aj"){
        lylqShow(index);
        return;
    }
    $.ajax({
        type: 'POST',
        url: $(this).data("type"),
        success: function (content) {
            if (content.success) {
                var url = content.object;
                // console.log(url);
                window.open(url);
            } else {
                alert("送达失败");
            }
        }
    })
    getLog();
});


$("#tuichu").click(function () {
    location.href = '/ajSongda';
});


function qsqrs_update(e) {
    var index = $(e).attr("data-index");
    var ssdr = ajInfo.ssdrVOS[index];
    layer.confirm('修改是否签署送达地址确认书', {
        btn: ['已签署', '未签署']
        , btn1: function (index, layero) {
            var dataJson = {
                dsrbh:ssdr.ssdrbh,
                ajxh:ajInfo.ajxh,
                fybh:ajInfo.fybh,
                sfqssddzqrs: 1
            }
            updateQsqrs(dataJson);
            layer.close(index);
        }, btn2: function (index, layero) {
            var dataJson = {
                dsrbh:ssdr.ssdrbh,
                ajxh:ajInfo.ajxh,
                fybh:ajInfo.fybh,
                sfqssddzqrs: 2
            }
            updateQsqrs(dataJson);
            layer.close(index);
        }
    });
};

function updateQsqrs(dataJson) {
    $.ajax({
        url: "/qsqrs_update_fg.aj",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data:JSON.stringify(dataJson),
        success: function (data) {
            location.reload();
        },error:function () {
            alert("结果更新失败")
        }
    });
}


function tydzsd_update(e) {
    var index = $(e).attr("data-index");
    var ssdr = ajInfo.ssdrVOS[index];
    layer.confirm('修改是否同意电子送达', {
        btn: ['同意', '不同意']
        , btn1: function (index, layero) {
            var dataJson = {
                dsrbh:ssdr.ssdrbh,
                ajxh:ajInfo.ajxh,
                fybh:ajInfo.fybh,
                sftydzsd: 1
            }
            tydzsdUpdate(dataJson);
            layer.close(index);
        }, btn2: function (index, layero) {
            var dataJson = {
                dsrbh:ssdr.ssdrbh,
                ajxh:ajInfo.ajxh,
                fybh:ajInfo.fybh,
                sftydzsd: 2
            }
            tydzsdUpdate(dataJson);
            layer.close(index);
        }
    });
};

function tydzsdUpdate(dataJson) {
    $.ajax({
        url: "/tydzsd_update_fg.aj",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data:JSON.stringify(dataJson),
        success: function (data) {
            location.reload();
        },error:function () {
            alert("结果更新失败")
        }
    });
}




//短信送达
function dxsdShow(index){
    usrList = [];
    //案号当事人信息
    dsrjbVO  = ajInfo.ssdrVOS[index];
    if(dsrjbVO.dh==null||dsrjbVO.dh==""||dsrjbVO.dh.isUndefined){
        alert("系统内没有该当事人的电话");
        return;
    }
    var caseNo = ajInfo.ah;
    $(".case-no").html(caseNo);
    $("#addDxtzModel").modal('show');
    $("#demoList").html("");
    $("#uploadFileShow").css("display","none");
    $("#sdp").css("display","none");
    $("#dxtz-zhuti").hide();
    $("#dxtz-param").hide();
    $("#dxtz-dsr").append(dsrjbVO.ssdrmc);
    var dh = dsrjbVO.dh.toString().split(';');
    $("#tel-tbody1").empty();
    for (var i = 0; i < dh.length; i++ ){
        htmlStr = "<tr>\n" +
            "  <td>\n" +
            "     <input  type=\"checkbox\" value='" + dh[i] + "' name='dxtz-tel-checkbox'>\n" +
            "  </td>\n" +
            "  <td>" + dh[i] + "</td>\n" +
            "</tr>";
        $("#tel-tbody1").append(htmlStr);
    }
    $("#dxtz-zhuti").show();
    $.ajax({
        url: "/dxtz/load_template_list.zf",
        type: 'post',
        data:{
          fybh:ajInfo.fybh
        },
        success: function (data) {
            $("#dxtz-dxmb-input").empty();
            $("#dxtz-mbtc").empty();
            $("#dxtz-dxmb-input").append(" <option>请选择短信模板</option>");
                dxmbList = data;
                for (var i = 0; i < data.length; i++) {
                    var item = data[i];
                    $("#dxtz-dxmb-input").append("<option value='"+item.bh+"'>"+item.mbmc+"</option>")
                }
        }
    });
}



/**
 * 发送短信按钮点击事件
 */
$("#sendMSg").on("click", function () {


    var dxmb = $("#dxtz-dxmb-input").val();
    if(!dxmb){
        layerErrorMsg("请选择短信模板");
        return;
    }

    var inputError = false,errorMsg="";
    var paramInputEle = $(".dxmb-param");
    var paramObjList = [];
    for (var i = 0; i < paramInputEle.length; i++) {
        var itemEle = $(paramInputEle[i]);
        var value = itemEle.val();
        if(value){
            if(!value.trim()){
                itemEle.addClass("input-error");
                inputError = true;
                errorMsg = "请不要全部输入空格"
            }
            if(value.length > 100){
                itemEle.addClass("input-error");
                inputError = true;
                errorMsg = "长度超过100，请重新输入"
            }else {
                //校验通过
                itemEle.removeClass("input-error");

                paramObjList.push({
                    paramName: itemEle.attr("name"),
                    paramValue: value,
                })
            }
        }else {
            itemEle.addClass("input-error");
            inputError = true;
            errorMsg = "请完善短信模板参数信息"
        }
    }
    if(inputError){
        layerErrorMsg(errorMsg)
        return;
    }

    var telCheckedEle = [];
    $("input[name='dxtz-tel-checkbox']:checked").each(function () {
        telCheckedEle.push($(this).val())
    });
    if(!telCheckedEle.length){
        layerErrorMsg("请选择下发号码");
        return;
    }

        var dataJson = {};
        dataJson["ajxh"] = ajInfo.ajxh;
        dataJson["fybh"] = ajInfo.fybh;
        dataJson["yhm"] = ajInfo.yhm;
        var dsr = JSON.stringify(dsrjbVO);
        dataJson["dsr"] = dsr;
        dataJson["templateId"] = dxmb;
        var pList = JSON.stringify(paramObjList);
        dataJson["paramObjList"] = pList;
        dataJson["urlList"] = JSON.stringify(usrList);
        console.log(usrList);
        dataJson["dh"] = JSON.stringify(telCheckedEle);
        $.ajax({
            url: "/dxtz/fg_send_message.aj",
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify(dataJson),
            success: function (data) {
                if (data.message == "success") {
                    alert("发送成功");
                } else {
                    alert("发送失败");
                }
                $("#addDxtzModel").modal('hide');
                getLog();
                getDxsdInfo();
            }, error: function () {
                alert("回执上传失败")
            }
        });





})


function layerErrorMsg(msg){
    //异常提醒
    layui.use("layer",function () {
        layui.layer.msg(msg,{icon:7})
    })
}
/**
 * 短信模板改变事件
 */
$("#dxtz-dxmb-input").on("change", function () {
    $("#demoList").html("");
    $("#uploadFileShow").css("display","none");
    $("#lj").val("");
    var thisVal = $(this).val();
    if (thisVal) {
        //选择了模板
        //第一步：重新动态填充短信内容参数模板
        var data = [],fymc="";
        for (var i = 0; i < dxmbList.length; i++) {
            var item = dxmbList[i];
            if(thisVal != item.bh){
                continue;
            }
            fymc = item.fymc;
            var paramNameList = item.paramNameList;
            if (paramNameList.indexOf("文书详情")!=-1){
                $("#uploadFileShow").css("display","block");
            }
            var arr_step_one = item.mbnr.split(/{{|}}/);
            if(arr_step_one.length > 0){
                for (var j = 0;j<arr_step_one.length;j++){
                    var v = arr_step_one[j];
                    var pIndex = paramNameList.indexOf(v);
                    if(pIndex >=0){
                        data.push({
                            type: "I", //类型 ： T=>固定文本 I:变量
                            content: "请输入" + v,// 类型为 =》代表固定文本内容 I =》代表输入框placeholder值
                            paramName: v //变量名
                        });
                    }else {
                        data.push({
                            type: "T", //类型 ： T=>固定文本 I:变量
                            content: v,// 类型为 =》代表固定文本内容 I =》代表输入框placeholder值
                        });
                    }
                }
            }

        }

        $("#dxtz-mbtc").html("");
        var htmlStr = "";
        for (var i = 0; i < data.length; i++) {
            htmlStr = "";
            if (data[i].type == "I") {
                if (data[i].paramName=="文书详情"){
                    htmlStr = "<input readonly class='dxmb-param form-control' placeholder='" + data[i].content + "' name='" + data[i].paramName + "'/>";
                }
                else if(data[i].paramName=="案件基本信息"||data[i].paramName=="案件信息"){
                    htmlStr =  "<span>" + ajInfo.ajmc + "</span>";
                }

                else if(data[i].paramName=="文书名称"||data[i].paramName=="请输入文书名称"){
                    // htmlStr =  "<span>" + wslb + "</span>";
                }
                else if(data[i].paramName=="XX区人民法院"){
                    htmlStr = "<input readonly class='dxmb-param form-control dxmb-param-fymc' value='"+fymc+"' placeholder='" + data[i].content + "' name='" + data[i].paramName + "'/>";
                }else {
                    htmlStr = "<input class='dxmb-param form-control' placeholder='" + data[i].content + "' name='" + data[i].paramName + "' />";
                }
            } else {
                htmlStr = "<span>" + data[i].content + "</span>";
            }
            $("#dxtz-mbtc").append(htmlStr);
        }
            //第二步：显示参数模板内容
        $("#dxtz-param").show();
    } else {
        //没选中 隐藏
        $("#dxtz-param").hide();
    }
})

function lylqShow(index){
    //案号当事人信息
    dsrjbVO  = ajInfo.ssdrVOS[index];
    var caseNo = ajInfo.ah;
    $(".case-no").html(caseNo);
    $(".sdp-name").html(dsrjbVO.dsrjc);
    $("#addLylqModel").modal('show');
}

$("#submit-lylq").on("click",function () {
    var yylqsj = $("#lylq-yysj-input").val();
    var lylqaddress = $("#lylq-address-input").val();
    var dataJson ={};
    dataJson["ajxh"] = ajInfo.ajxh;
    dataJson["fybh"] = ajInfo.fybh;
    dataJson["yhm"] = ajInfo.yhm;
    dataJson["yylqsj"] = yylqsj;
    dataJson["lylqaddress"] = lylqaddress;
    var dsr = JSON.stringify(dsrjbVO);
    dataJson["dsr"] = dsr;
    if(!yylqsj){
        alert("请选择预约时间");
        return;
    }
    if(!lylqaddress || !lylqaddress.trim()){
        alert("请填写预约地址");
        return;
    }
    $.ajax({
        url: "/lylq/addFgLylq.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(dataJson),
        success: function (data) {
            //重新加载数据
            alert("新增成功");
            //关闭弹窗
            $("#addLylqModel").modal('hide');
            //清空表单数据
            $("#lylq-yysj-input").val("");
            $("#lylq-address-input").val("");
            getLog();
            getLylqInfo();
        }
    });
});


function showLog(index) {
    $("#buttom_log_ul").empty();
    var startIndex = 0;
    if(5*(index-1)+4>=logData.length){
        startIndex = logData.length-1;
    }else {
        startIndex = 5*(index-1)+4;
    }
    for(var i=startIndex;i>=5*(index-1);i--){
        var date = new Date(logData[i].creattime);
        var day = date.getDate();
        var month = date.getMonth() + 1;
        var year = date.getFullYear();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var second = date.getSeconds();
        html ="<li>\n" +
            "                <div class=\"bottom_li_div\">\n" +
            "                    <div style=\"height: 15px;width: 100%;color: #2D93CA\">\n" +
            "                        " + year + "-" + month + "-" + day +
            "                    </div>\n" +
            "                    <div style=\"height: 15px;width: 100%;color: #2D93CA;margin-top: 3px;\">\n" +
            "                        "+hour+":"+minute+":"+second+
            "                    </div>\n" +
            "                    <div style=\"border: 1px solid #2D93CA;margin-top: 3px;\"></div>\n" +
            "                    <div style=\"height: 20px;width: 100%;color: #2D93CA;margin-left: 1px;\">\n" +
            "                        o\n" +
            "                    </div>\n" +
            "                    <div style=\"background: url(/css/img/bottom_jiantou.png) no-repeat;background-size: cover; height: 10px;width: 180px;margin-left: 10%;margin-right: 10%;\"></div>\n" +
            "                    <div style=\"height: auto;width: 180px;margin-left: 10%;margin-right: 10%;border: 1px solid #2D93CA;text-align: left\">\n" +
            "                        <div style=\"margin-top: 8px;margin-left: 5px;\">【"+logData[i].creater+"】</div>\n" +
            "                        <div style=\"margin-top: 8px;margin-left: 5px;\">"+logData[i].type+"</div>\n";
        if(logData[i].targetname!==" "&&logData[i].targetname!==null&&!logData[i].targetname.isUndefined){
            html+= "<div style=\"margin-top: 8px;margin-left: 5px;\">被操作对象 :"+logData[i].targetname+"</div>\n"
        }
        html+= "                    </div>\n" +
            "                </div>\n" +
            "            </li>";
        $("#buttom_log_ul").prepend(html);
    }
}


$("#nextLogPage").on("click",function () {
    if(logPage<((logData.length)/5)){
        logPage++;
        showLog(logPage);
    }
})

$("#preLogPage").on("click",function () {
    if(logPage<=1){
        return;
    }
    logPage--;
    showLog(logPage);
})

var typeRule = ['image/png','image/gif','image/jpg','image/jpeg','application/pdf'];
layui.use('upload', function() {
    var upload = layui.upload;
    var demoListView = $("#demoList"), uploadListIns = upload.render({
        elem: '#uploadFile',
        url: "/dxtz/uploadFile",
        exts: "pdf|jpg|png|jpeg|gif",
        accept: 'file',
        multiple: true,
        auto: false,
        size: 30720,
        bindAction: "#sclj-btn",
        choose: function (obj) {
            var files = obj.pushFile();
            obj.preview(function (index, file, result) {
                typeArr.push(file.type);
                var falg = false;
                for (var i=0;i<typeArr.length;i++){
                    if (typeArr[0].split("/")[0]!=file.type.split("/")[0]){
                        alert("请上传同一种类型文件");
                        falg = true;
                        delete files[index];
                        break;
                    }
                }
                if(!falg){
                    var tr = $(['<tr id="upload-' + index + '">',
                        '<td>' + file.name + '</td>',
                        '<td>',
                        '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>',
                        '</td>',
                        '</tr>'].join(''));

                    $(".dxmb-param[name=\"文书详情\"]").val("");

                    tr.find(".demo-delete").on("click", function () {
                        delete files[index];
                        tr.remove()
                        $(".dxmb-param[name=\"文书详情\"]").val("");
                        var delLen = $(".demo-delete").length;
                        if(delLen == 0){
                            typeArr = [];
                        }
                        uploadListIns.config.elem.next()[0].value = '';
                    });
                    demoListView.append(tr)
                }

            })
        },
        before: function (obj) {
            var files = obj.pushFile();
            for (var key in files){
                var eleLen = $("#upload-"+key).length;
                if(eleLen == 0){
                    //元素不存在 移除文件
                    delete files[key];
                }
            }
        },
        done: function (res, index, upload) {
            console.log(res);
            if (null != res) {
                usrList.push(res.data);//文件地址集合
                var delLen = $(".demo-delete").length; //删除按钮个数
                if(delLen == usrList.length){
                    //上传完成
                    $("#sclj-btn").text("生成链接").removeClass("layui-btn-disabled");
                    $("input[name='文书详情']").val(_randLink());
                }
                return;
            }
            this.error(index, upload)
        },
        error: function (index, upload) {
            var tr = demoListView.find('tr#upload-' + index),
                tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722">上传成功</span>');
            tds.eq(3).find('.demo-delete').removeClass("layui-hide");
        }
    })
})


$("#dxtzjbxx_tbody").on("click",".look_zy",function(){
    var dxtzid = $(this).data("hint");
    $.cookie('way',"show");
    window.open("dxtz/showImages?yhm=" + $.cookie('yhm') + "&id=" + dxtzid);
});

function sclj() {
    if ($("#demoList").html()==''){
        alert("请先上传文件");
        return;
    }
    usrList = [];
    $("#sclj-btn").text("正在生成，请稍后").addClass("layui-btn-disabled");
}

function showImages(dxtzid) {
    window.open("dxtz/showImages_fg?yhm=" + "a"+ "&id=" + dxtzid);
}

function downloadCallRadio(webCallId) {
    // console.log(webCallId);
    location.href = "/webCall/download_call_radio.zf?webCallId=" + webCallId;

}