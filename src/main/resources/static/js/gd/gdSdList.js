var list;
var sd_way;
var yhdm;
var cljg = 0;
var selectedEmsId;
$(document).ready(function () {
    sd_way = $.cookie('way');
    yhm = $.cookie('yhm');
    yhjs = $.cookie('yhjs');
    $("#userName").append(yhm);
    // todo yhm yhdm
    yhdm = yhm;


    getCurrentFyNameBySessionFybh();

    getGd(sd_way, yhdm, cljg);//获得未处理工单
});
var xqxx;
var telDataList = [];//当事人号码集合
var yysdbhSelected; //当前正在操作的工单id
var ssdrbhSelected;//当前选择的受送达人id
var webCallId; //当前操作的外呼id
var ssdrList = []; //当事人集合

function getGd(way, yhm, cljg) {
    if(cljg==0){
        var jg = false;
    }else {
        var  jg = true;
    }
    $.ajax({
        url: "/getGd.aj",
        type:"post",
        data: {
            sdWay: way,
            yhdm: yhdm,
            cljg: jg

        },
        success: function (data) {
            list = data;
            $("#ajjbxx_table").dataTable().fnDestroy();
            show_table(data, cljg);
            $('#ajjbxx_table').DataTable(
                {
                    bLengthChange: false, //去掉每页显示多少条数据方法
                    "info": false,   //去掉底部文字
                    "scrollY": "500px", // 内部滚动条
                    "scrollCollapse": "true", // 内部滚动条
                    destory: true,
                    "language": {
                        "emptyTable":"暂无数据",
                        "sSearch": "搜索:",
                        "oPaginate": {
                            "sPrevious": "上页",
                            "sNext": "下页"
                        }
                    }
                }
            );
        }, error: function () {
            alert("获取案件信息失败")
        }
    });
}

function show_table(data, cljg) {
    if(cljg==0){
        var jg = false;
    }else {
        var  jg = true;
    }
    var test_data = {
        title: '基本例子',
        isAdmin: true,
        list: data,
        cljg: jg,
        isEmssd:sd_way=='EMSSD'
    };
    if(cljg==0){
        var html = template('test', test_data);
    }else if(cljg==1) {
        var html = template('test1', test_data);
    }
    $("#ajjbxx_tbody").empty().append(html);

    layui.use('upload', function(){
        var upload = layui.upload;
        var uploadInst=upload.render({
            elem: '.uploadEmsmd', //绑定元素
            url: '/uploadEmsmd.do', //上传接口
            method:'POST',
            multiple:false,
            data: {
                yysdbh:selectedEmsId
            },
            accept: 'images', //允许上传的文件类型
            size: 204800, //设置文件最大可允许上传的大小，单位 KB
            before:function(obj){
              $.extend(true,this.data,{
                  yysdbh:selectedEmsId
              })
            },
            done: function(res){ //上传完毕回调
                alert(res.message);
                getGd(sd_way, yhdm, cljg);
            },

            error: function(){
                //请求异常回调
               alert("上传失败",{icon: 0});
            }
        });
    })
}


$("#return_main").on("click", function () {
    if(yhjs=='admin'){
        location.href = "/wbryIndex"
    }else{
        location.href = "/gdIndex"
    }

});



function sd(e) {
    var index = $(e).attr('data-index');
    var yysdbh = list[index].yysdbh;
    if(sd_way=="DHSD"){
        sdHzzf(e);
        $("#addWebCallModel").modal('show');
        return;
    }
    $.ajax({
        type: 'post',
        url: 'getSdUrl.aj',
        data: {
            sdWay: sd_way,
            yysdbh: yysdbh
        },
        success: function (content) {
            if (content.success) {
                var url = content.object;
                window.open(url);
            } else {
                alert("获取送达地址失败");
            }
        }
    })
}
function scmd(e) {
    selectedEmsId = $(e).attr('data-index');
}
function sdjg(e) {
    var bh = $(e).attr('data-index');
    layer.confirm('选择当前方式送达结果', {
        btn: ['成功', '失败']
        , btn1: function (index, layero) {
            $.ajax({
                url: "/uploadSdjg.aj",
                type:"post",
                data: {
                    sdWay: sd_way,
                    sdjg: 1,
                    yysdbh: list[bh].yysdbh
                },
                success: function (data) {
                    location.reload();
                }, error: function () {
                    alert("上传失败")
                }
            });
        }, btn2: function (index, layero) {
            $.ajax({
                url: "/uploadSdjg.aj",
                type:"post",
                data: {
                    sdWay: sd_way,
                    sdjg: 0,
                    yysdbh: list[bh].yysdbh
                },
                success: function (data) {
                    location.reload();
                }, error: function () {
                    alert("上传失败")
                }
            });
        }
    });
}

$("#select_sdjg").change(function () {
    cljg = $("#select_sdjg").val();
    getGd(sd_way, yhm, cljg)
});

function ck(e) {
    var index = $(e).attr('data-index');
    var obj = list[index];
    $.cookie('yysdbh',obj['yysdbh']);
    window.open("wbrywdsd");
 }

function download(e) {
    var index = $(e).attr('data-index');
    var ws = xqxx.ws[index];
    var url = 'downloadWs.do?yysdbh=' + ws.yysdbh + '&bh=' + ws.bh;
    window.open(url)
}

function formatDate(e) {
    var date = new Date(e);
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    var dateFormat = Y + M + D + h + m + s;
    console.log(dateFormat);
    return dateFormat;

}

$('#tel-tbody').on("click",".web-call-btn",function () {
    var index = $(this).attr("data-index");

    var telData = telDataList[index];
    var webTel = telData.showTel;
    $("#webTel").html(webTel);
    var data = {
        yysdbh: yysdbhSelected,
        ssdrbh: ssdrbhSelected,
        telBh: telData.bh,
        yhm: yhm
    };
    $.ajax({
        url: "/hzzf/web_call.zf",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            var code = data.code;
            if (code == '200') {
                //业务成功
                webCallId = data.data.webCallId;
                var seatNumber = data.data.seatNumber;
                $("#seatNumber").html(seatNumber);

                //打开二级弹窗
                $("#editWebCallModel").modal({backdrop: 'static', keyboard: false})
                $("#showA").css("display", "none")
                $("#showB").css("display", "none")
                $("#showC").css("display", "none")
                $("#iscon").val("");
                $("#addressb").val("");
                $("#lylq-yysj-input").val("");
                $("#orderAddress").val("");
                $("#dhzt-select").val("<option value=\"\">请选择电话状态</option>");
                $("#sendType").val("<option value=\"0\">请选择送达方式</option>");

            } else {
                //业务异常
                alert(data.message)
            }
        }, error: function () {
            alert("发起外呼失败")
        }
    });

})
$("#submitEditWebCall").on("click",function () {

    //1.电话状态校验
    var dhzt = $("#dhzt-select").val();
    if(!dhzt){
        alert("请选择电话状态");
        return;
    }
    //2.号码确认校验
    var confirmNumberSet = []; //确认号码数组
    var error = false;
    $(".confirmNumber").each(function () {
        var str = $(this).val();
        if(null == str || undefined == str || str.trim().length == 0){
        }else {
            str = str.trim();
            var phoneReg = /^\d{10,12}$/;
            if(!phoneReg.test(str)){
                alert("确认号码格式不规范："+str);
                error = true;
            }else {
                confirmNumberSet.push(str)
            }
        }
    })
    if(error){
        return;
    }

    //3.封装确认地址
    var confirmAddressSet = []; //确认地址组
    $(".confirmAddress").each(function () {
        var address = $(this).val();
        if(null == address && undefined == address){
            address = "";
        }
        address = address.trim();
        if(address.length > 0){
            confirmAddressSet.push(address)
        }
    })

    //4.送达方式相关数据处理
    var sendType = $("#sendType").val();
    var dxtzNumber = $("input[name='show']:checked").val();
    var mailAddress = $("input[name='address']:checked").val();
    if(sendType == 2){
        //邮寄送达-优先输入框内容
        if(null != $("#emsAddress").val() && undefined != $("#emsAddress").val() && $("#emsAddress").val().trim().length > 0){
            mailAddress = $("#emsAddress").val();
        }
        if(null == mailAddress || undefined == mailAddress || mailAddress.trim().length == 0){
            alert("请选择或输入邮寄送达地址")
            return;
        }
    }else if(sendType == 3){
        //选择短信下发通知-所选号码-优先输入框内容

        if(null != $("#dxtzNumber").val() && undefined != $("#dxtzNumber").val()&& $("#dxtzNumber").val().trim().length >0){
            dxtzNumber = $("#dxtzNumber").val();
        }
        if(null == dxtzNumber || undefined == dxtzNumber || dxtzNumber.trim().length == 0){
            alert("请选择或输入短信电子送达号码")
            return;
        }else {
            if(dxtzNumber.indexOf("1") != 0 || dxtzNumber.length != 11){
                alert("短信电子送达号码格式不规范:"+dxtzNumber)
                return;
            }
        }
    }


    var data = {
        webCallId: webCallId,
        callstate: dhzt,
        remarks: $("#remarks").val(),
        confirmNumberSet: confirmNumberSet,
        confirmAddressSet: confirmAddressSet,
        electronSend: $("input[name='electronSend']:checked").val(), //是否同意电子送达
        sendType: $("#sendType").val(), //选择送达方式
        order: $("#lylq-yysj-input").val() +","+ $("#orderAddress").val(), //送达方式-来院领取数据（单一）
        mailAddress: mailAddress, //送达方式-邮寄送达地址数据（单一）
        callPhone: dxtzNumber, //送达方式-短信电子送达号码数据（单一）
        sdpName: sdpName,
        caseNo: caseNo,
        yysdBh: yysdbhSelected,
        createPeople: yhm,
        yhdm: yhdm,
        ssdrBh: ssdrbhSelected,
        ajxh: ajbh,
        fybh: fgxh
    }
    $.ajax({
        url: "/webCall/submit_web_call.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(data),
        success: function (data) {
            showPhone();
            $("#editWebCallModel").modal('hide');
        },error:function () {
            alert("失败")
        }
    })
    $("#remarks").val('');

    // $("#editWebCallModel").modal('hide');
})
//打开工单外呼弹窗
var caseNo;
var sdpName;
var ajbh;
var fgxh;

// var sfzhm;
function sdHzzf(e) {
    var index = $(e).attr('data-index');
    var yysdInfo = list[index];
    yysdbhSelected = yysdInfo.yysdbh;
    caseNo = yysdInfo.ah;
    ajbh = yysdInfo.ajxh;
    fgxh = yysdInfo.fybh;
    // sfzhm = yysdInfo.sfzhm;
    $(".case-no").html(yysdInfo.ah);
    $("#tel-tbody").html("");

    //$(".sdp-name").html(yysdInfo.bsdr);
    //showPhone();


    //查询工单下的当事人
    var dataJson = {
        yysdbh: yysdbhSelected
    }
    $("#webcall-ssdrbh-input").html("<option value=''>请选择受送达人</option>")
    $.ajax({
        url: "/ssdr/query_ssdr_list.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(dataJson),
        success: function (data) {

            ssdrList = data;
            for (var i = 0; i < ssdrList.length; i++) {
                $("#webcall-ssdrbh-input").append("<option value=\"" + i + "\">" + data[i].ssdrmc + "</option>");
            }
            $("#webcall-ssdrbh-input").selectpicker("refresh");

        },error:function () {
            alert("失败")
        }
    });
}
function addPhone(val) {
    var addPhone = "<div class='input-group'><input class=\"col-sm-9 form-control confirmNumber\" rows=\"3\" placeholder=\"请确认号码\"/><div onclick='deleteInput(this)' class='input-group-addon'>移除</div></div>";
    if (val == "addPhoneA"){
        $('#phone').append(addPhone);
    }
    if (val == "addPhoneB"){

        //短信电子送达号码
        var text = $("#phoneListInput").text();
        if(null != text && undefined != text && text.trim().length > 0){
            //存在输入框 不处理
        }else {
            addPhone = "<div class='input-group'><input class=\"col-sm-9 form-control\" id='dxtzNumber' rows=\"3\" placeholder=\"请确认号码\"/><div onclick='deleteInput(this)' class='input-group-addon'>移除</div></div>";
            $('#phoneListInput').html(addPhone);
        }
    }
}
function addAddress(val) {
    var addAddress = "<div class='input-group'><input class=\"form-control confirmAddress\" rows=\"3\" placeholder=\"请确认地址\"/><div onclick='deleteInput(this)' class='input-group-addon'>移除</div></div>";
    if (val == "addAddressA"){
        $('#address').append(addAddress);
    }
    if (val == "addAddressB"){
        //邮寄送达地址
        var text = $('#addressInput').text();
        if(null != text && undefined != text && text.trim().length > 0){
            //存在输入框 不处理
        }else {
            addAddress = "<div class='input-group'><input class=\"form-control\" id='emsAddress' rows=\"3\" placeholder=\"请确认地址\"/><div onclick='deleteInput(this)' class='input-group-addon'>移除</div></div>";
            $('#addressInput').html(addAddress);
        }
    }
}
function deleteInput(e) {
    $(e).parent().remove();
}
function showInfo(val) {
    $('#phoneList').html("");
    $('#addressRadio').html("");
    if (val == 0){
        $("#showA").css("display","none")
        $("#showB").css("display","none")
        $("#showC").css("display","none")
    }
    if (val == 1){
        $("#showA").css("display","block")
        $("#showB").css("display","none")
        $("#showC").css("display","none")
    }
    if (val == 2){
        addressRadio();
        $("#showB").css("display","block")
        $("#showA").css("display","none")
        $("#showC").css("display","none")
    }
    if (val == 3){
        $("#showC").css("display","block")
        $("#showA").css("display","none")
        $("#showB").css("display","none")
        for (var i = 0; i < telDataList.length; i++) {
            var item = telDataList[i];
            var telList = "<input type='radio' id='confirmNumber' name='show' class='radio-inline' value='"+item.showTel+"'/>" + item.showTel +" "
            $('#phoneList').append(telList);
        }
    }
}

/**
 * 提交修复
 */
function submitRepair(e) {
    var yysdbh = $(e).attr('data-index');
    var ssdrbh = $(e).attr('data-hint');
    if(yysdbh){
        var dataParam = {
            pubYysdSsdrKeyList: [{
                yysdbh: yysdbh,
                ssdrbh: ssdrbh
            }],
            yhm: $.cookie('yhm')
        }
        $.ajax({
            url: "/hzzf/submit_repair.zf",
            type: 'post',
            headers:{'Content-type':'application/json'},
            dataType:"json",
            data: JSON.stringify(dataParam),
            success: function (data) {
                if(data.code == '200'){
                    //成功
                    alert("提交成功");
                    $(".layui-layer-ico").click();
                }else {
                    //失败
                    alert(data.message);
                }
            },error:function () {
                alert("操作失败")
            }
        });
    }

}

function restLayUiopen(yysdbh) {
    $.ajax({
        type: 'post',
        url: '/getGdxqxx.aj',//获取工单详情信息
        data: {
            yysdbh: yysdbh
        },
        success: function (content) {
            //转换时间格式
            xqxx = content.object;
            content.object.jb.yysj = formatDate(content.object.jb.yysj);
            var test_data = {
                gdxqxx: content.object,
                cusIndex:index,
                isAdmin : 1
            }
            var html = template('gdxqxx', test_data);
            layer.open({
                type: 1,
                title: false,
                closeBtn: 1,
                shadeClose: true,
                area: ['800px', '600px'],
                content: html
            });
        }
    })
}

function showPhone() {
    $("#tel-tbody").html("");
    //查询当事人号码
    var data = {
        sdpName: sdpName,
        // sdpIdCard: sfzhm,
        yysdBh: yysdbhSelected,
        ssdrbh: ssdrbhSelected
    };
    var phoneStatus = ["","有效-本人","可联-非本人","可联-关机","可联-停机","可联-未接通","可联-再跟进","可联-正忙","空号","呼叫受限","可联-挂机","代理人接听","代理律师接听"]

    $.ajax({
        url: "/dxtz/query_sdp_phone.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(data),
        success: function (data) {
            telDataList = data;

            for (var i = 0; i < telDataList.length; i++) {
                var item = telDataList[i];
                var htmlStr = "<tr>";
                if(item.hmly == 'CYR'){
                    htmlStr += "<td>相关参与人</td>";
                }else if(item.hmly){
                    htmlStr += "<td>"+_sysCodeToCn(item.hmly,'SYS_HIS_DATA_LY')+"</td>";
                }else if(item.operatorType == 'ENTRY'){
                    htmlStr += "<td>录入</td>"
                }else {
                    htmlStr += "<td>查询</td>"
                }

                htmlStr += "<td>"+item.showTel+"</td>";

                if(item.newphonestatus){
                    htmlStr += "<td>"+phoneStatus[item.newphonestatus]+"</td>";
                }else {
                    htmlStr += "<td>暂无</td>";
                }

                // htmlStr += "<td><div data-toggle='modal' data-target='#editWebCallModel' class='web-call-btn btn btn-success' data-index='"+i+"'>拨打</div></td>";
                htmlStr += "<td><div class='web-call-btn btn btn-success' data-index='"+i+"'>拨打</div></td>";

                htmlStr += "</tr>"
                $("#tel-tbody").append(htmlStr);
            }

        },error:function () {
            alert("获取号码信息失败")
        }
    });
}

$("#webcall-ssdrbh-input").on("change",function () {
    if($(this).val()){
        var ssdrInfo = ssdrList[$(this).val()];
        sdpName = ssdrInfo.ssdrmc;
        //查询更新号码表格
        ssdrbhSelected = ssdrInfo.ssdrbh;
        showPhone();
    }else {
        $("#tel-tbody").html("");
    }
})


$(function () {
    layui.use("laydate",function () {

        layui.laydate.render({
            elem:"#lylq-yysj-input",
            type: "datetime",
            min: -7,
            format: "yyyy-MM-dd HH:mm"
        })
    })

})


function addressRadio() {
    var data = {
        yysdbh: yysdbhSelected,
        ssdrbh: ssdrbhSelected
    };
    $.ajax({
        url: "/ssdrdz/select_ssdrdz.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(data),
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var item = data[i]
                var html="<div>";
                html += '<input id="addressRadio'+i+'" type="radio" name="address" value="'+item.dz+'" />';
                html += '<label for="addressRadio'+i+'">'+item.dz+'</label>';
                html +="</div>";
                $("#addressRadio").append(html);
            }
        },error:function () {
            alert("获取地址信息失败")
        }
    });

}
var qrdz;
$('#addressRadio').on("change","input",function () {
    qrdz = $(this).val();
})



