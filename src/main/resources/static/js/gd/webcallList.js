$(document).ready(function () {
    xtyh.fybh = $.cookie('fybh');
    xtyh.ajxh = $.cookie('ajxh');
    yhjs = $.cookie('yhjs');
    xtyh.yhm = $.cookie('yhm');
    start = getLastMonthTime();
    end = getNowTime();

    $("#select_time1").val(start);
    $("#select_time2").val(end);


    getCurrentFyName(xtyh.fybh);

    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#select_time1' //指定元素
        });

        var laydate2 = layui.laydate;
        //执行一个laydate实例
        laydate2.render({
            elem: '#select_time2' //指定元素
        });
    });
    loadList();
});

$("#select_range_time").click(function () {
    start = $("#select_time1").val();
    end = $("#select_time2").val();
    flash_table();

});

function flash_table() {
    // console.log(' 现在在未分配页面')
    // 重新获取表格数据 刷新表格
    var dttable = $('#ajjbxx_table').dataTable();
    // dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
    loadList();
}

var usrList = [];
var typeArr = [];
var xtyh = {
    fybh: "",
    ajxh: "",
    yhm: "",
};
var dxmbList = []; //全局短信模板list
var yysdList =[]; //工单列表
var selectedDxtzId = 0;

var webCallModeList =[];
var telDataList =[];

$(function () {
    layui.use("laydate",function () {
        layui.laydate.render({
            elem:"#lylq-yysj-input",
            type: "datetime",
            min: -7,
            format: "yyyy-MM-dd HH:mm"
        }),
            layui.laydate.render({
                elem:"#lylq-yysj-input1",
                type: "datetime",
                min: -7,
                format: "yyyy-MM-dd HH:mm"
            })
    })
})
/**
 * 查询列表
 */
function loadList(data) {
    var newEnd = end+" 23:59:59";
    $("#webcalljbxx_table").dataTable().fnDestroy();
    $("#webcalljbxx_tbody").html("");
    $.ajax({
        url: "/webCall/web_call_list.zf?start="+start+"&end="+newEnd,
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        success: function (data) {
            var code = data.code;
            if(code == '200'){
                show_table(data.data);
                webCallModeList = data.data || [];
            }
            $("#webcalljbxx_table").dataTable().fnDestroy();
            $('#webcalljbxx_table').DataTable({
                bLengthChange: false, //去掉每页显示多少条数据方法
                iDisplayLength: 20,
                "info": false,   //去掉底部文字
                "scrollY": "500px", // 内部滚动条
                "scrollCollapse": "true", // 内部滚动条
                destory: true,
                "language" : {
                    "emptyTable":"暂无数据",
                    "sSearch" : "搜索:",
                    "oPaginate" : {
                        "sPrevious" : "上页",
                        "sNext" : "下页"
                    }
                }
            });
        },error:function () {
            alert("获取信息失败")
        }
    });
}
function viewWebCallInfo_item(item,title) {
    var phone = item.confirmnumber,address = item.confirmaddress;
    var phoneArr = [],addressArr = [],area = ["400px","400px"];
    if(null != phone){
        phoneArr = phone.split(",");
    }
    if(null != address){
        addressArr = address.split(",");
    }

    var html ="\n" +
        "            <div style='padding: 5px;'>\n" ;

    if("确认信息" == title){
        html += "<div><blockquote class=\"custm-blockquote layui-elem-quote\">确认号码：</blockquote>"
        if(phoneArr.length > 0){
            phoneArr.forEach(function (pItem) {
                html += "<blockquote class=\"custm-blockquote layui-elem-quote layui-quote-nm\">"+(pItem|| "&nbsp;")+"</blockquote>"
            })
        }else {
            html += "<div style='margin-left: 10px;'>暂无</div>"
        }
        html += "</div>\n"

        html += "<div><blockquote class=\"custm-blockquote layui-elem-quote\">确认地址：</blockquote>"
        if(addressArr.length > 0){
            addressArr.forEach(function (aItem) {
                html += "<blockquote class=\"custm-blockquote layui-elem-quote layui-quote-nm\">"+(aItem || "&nbsp;")+"</blockquote>"
            })
        }else {
            html += "<div style='margin-left: 10px;'>暂无</div>"
        }
        html += "</div>\n"
    }else if ("选择送达方式信息" == title) {
        area = ["400px","230px"]
        if(item.sdtype != null){

            if(item.sdtype == 1){
                html += "<div><blockquote class=\"custm-blockquote layui-elem-quote\">来院领取：</blockquote>"
            }else if(item.sdtype == 2){
                html += "<div><blockquote class=\"custm-blockquote layui-elem-quote\">邮寄送达：</blockquote>"
            }else if(item.sdtype == 3){
                html += "<div><blockquote class=\"custm-blockquote layui-elem-quote\">短信电子送达：</blockquote>"
            }
            html += "<blockquote class=\"custm-blockquote layui-elem-quote layui-quote-nm\">"+(item.sdfscontent || "&nbsp;")+"</blockquote>"

            html += "</div>\n"
        }
    }


    html +="</div>";
    layer.open({
        type: 1,
        title: title,
        shadeClose: true,
        area: area,
        content: html,
        btn: ["确认"],
        closeBtn: 1,
        btn1:function (index, layero) {
            layer.close(index);
        }
    })
}
function viewWebCallInfo(index, title) {
    var item = webCallModeList[index];
    viewWebCallInfo_item(item,title)
}

function show_table(data) {
    var test_data = {
        title: '基本例子',
        isAdmin: true,
        list: data,
    };
    var html = template('webcall_td_body', test_data);
    $("#webcalljbxx_tbody").append(html);
}

$("#return_main").on("click", function () {
    if(yhjs=='admin'){
        location.href = "/wbryIndex"
    }else{
        location.href = "/gdIndex"
    }
});

/**
 * 工单选择项改变事件
 */
var yysdbhall;
var ssdrList;
$("#dxtz-yysdbh-input").on("change", function () {
    //$("#demoList").html("");
    $("#uploadFileShow").css("display","none");
    var thisVal = $(this).val();
    yysdbhall = $(this).val();
    if (thisVal) {
        //选择了工单
        //第一步：查询工单中当事人下的电话号码并将数据填充到页面上
        $("#tel-tbody").html(""); //清空后填充
        $("#dxtz-dsr").val("") //清空后填充
        $("#dxtz-dsr").html("<option value='0'>请选择当事人</option>") //清空后填充
        for (var i =0 ;i<yysdList.length ;i++){
            var item = yysdList[i];
            if(thisVal == item.yysdbh){
                $.ajax({
                    url: "/getGdxqxx.aj",
                    type: 'post',
                    data:{
                        yysdbh:thisVal
                    },
                    success: function (result) {
                        $.ajax({
                            url: "/dxtz/load_template_list.zf",
                            type: 'post',
                            data:{
                                fybh:result.object.jb.fybh
                            },
                            success: function (data) {
                                for (var i = 0; i < data.length; i++) {
                                    dxmbList = data;
                                    for (var i = 0; i < data.length; i++) {
                                        var item = data[i];
                                        $("#dxtz-dxmb-input").append("<option value='"+item.bh+"'>"+item.mbmc+"</option>")
                                    }
                                }
                            }
                        });
                    }
                });
                var data = {
                    sdpName: item.dsrmc,
                    sdpIdCard: item.sfzhm,
                    yysdbh: item.yysdbh
                };
                $.ajax({
                    url: "/ssdr/query_ssdr_list.zf",
                    type: 'post',
                    headers:{'Content-type':'application/json'},
                    dataType:"json",
                    data: JSON.stringify(data),
                    success: function (data) {
                        ssdrList = data || [];
                        $("#sdp").css("display","block");
                        for (var i = 0; i < data.length; i++) {
                            $("#dxtz-dsr").append("<option value=\"" + data[i].ssdrbh + "\">" +  data[i].ssdrmc + "</option>");
                        }
                        $("#dxtz-dsr").selectpicker("refresh");
                        //第二步：显示短信下发主体内容
                        $("#dxtz-zhuti").show();
                    },error:function () {
                        alert("获取当事人信息失败")
                    }
                });
            }
        }



    } else {
        //没选中 隐藏
        $("#dxtz-zhuti").hide();
    }
})
var ssdrbh;
var ssdr;
$("#dxtz-dsr").on("change", function () {
    // $("#demoList").html("");
    // $("#uploadFileShow").css("display","none");
    $("#tel-tbody").html(""); //清空后填充
    var thisVal = $(this).val();
    ssdrbh = thisVal;
    for (var i = 0; i < ssdrList.length; i++) {
        if(ssdrList[i].ssdrbh == ssdrbh){
            ssdr = ssdrList[i];
            break;
        }
    }
    for (var i =0 ;i<yysdList.length ;i++){
        var item = yysdList[i];
        if(yysdbhall == item.yysdbh) {
            var data = {
                ssdrbh: thisVal,
                yysdBh: item.yysdbh
            };
            $.ajax({
                url: "/dxtz/query_sdp_phone.zf",
                type: 'post',
                headers: {'Content-type': 'application/json'},
                dataType: "json",
                data: JSON.stringify(data),
                success: function (data) {
                    var telData = data;
                    telDataList = telData;
                    for (var i = 0; i < telData.length; i++) {
                        var htmlStr = "<tr>";

                        if (telData[i].hmly == 'CYR') {
                            htmlStr += "<td>相关参与人</td>"
                        } else if(telData[i].hmly){
                            htmlStr += "<td>"+_sysCodeToCn(telData[i].hmly,'SYS_HIS_DATA_LY')+"</td>";
                        } else if (telData[i].operatorType == 'ENTRY') {
                            htmlStr += "<td>录入</td>";
                        } else {
                            htmlStr += "<td>查询</td>"
                        }
                        htmlStr+="  <td>" + telData[i].showTel + "</td>";
                        htmlStr+="  <td>" + _sysCodeToCn(telData[i].newphonestatus,'SYS_DHZT','暂无') + "</td>";
                        htmlStr += "<td><div class='web-call-btn btn btn-success' data-index='"+i+"'>拨打</div></td>";
                        htmlStr += "</tr>"
                        $("#tel-tbody").append(htmlStr);


                    }
                        //第二步：显示短信下发主体内容
                        $("#dxtz-zhuti").show();
                }, error: function () {
                    alert("获取号码信息失败")
                }
            });
        }
    }
})
var webCallId;
$('#tel-tbody').on("click",".web-call-btn",function () {
    var index = $(this).attr("data-index");

    var telData = telDataList[index];
    var webTel = telData.showTel;
    $("#webTel").html(webTel);
    var data = {
        yysdbh: telData.yysdbh,
        ssdrbh: telData.ssdrbh,
        telBh: telData.bh,
        yhm: xtyh.yhm
    };
    $.ajax({
        url: "/hzzf/web_call.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(data),
        success: function (data) {
            var code = data.code;
            if(code == '200'){
                //业务成功
                webCallId = data.data.webCallId;
                var seatNumber = data.data.seatNumber;
                $("#seatNumber").html(seatNumber);

                //打开二级弹窗
                $("#editWebCallModel").modal({backdrop:'static',keyboard:false})
                $("#showA").css("display","none")
                $("#showB").css("display","none")
                $("#showC").css("display","none")
                $("#iscon").val("");
                $("#addressb").val("");
                $("#lylq-yysj-input").val("");
                $("#selectedSdjg").val("0");
                $("#orderAddress").val("");
                $("#dhzt-select").val("<option value=\"\">请选择电话状态</option>");
                $("#sendType").val("<option value=\"0\">请选择送达方式</option>");

                $.ajax({
                    url: "/getDsrwslb.aj",
                    type: 'post',
                    data: {
                        yysdbh: ssdr.yysdbh,
                        ssdrbh: ssdr.ssdrbh
                    },
                    success: function (data) {
                        $("#wslbcheckboxdhsd").html("");
                        if(data.length == 0){
                            $("#wslbcheckboxdhsd").append("<input class=\"form-control confirmAddress\" rows=\"3\" placeholder=\"无相应文书\"/>");
                        }
                        for (var i = 0; i < data.length; i++) {
                            $("#wslbcheckboxdhsd").append(
                                "<div class=\"form-check\">" +
                                "<label class=\"form-check-label\">" +
                                " <input type=\"checkbox\" class=\"form-check-input\" value=\""+ data[i].yysdbhBh + "\">" + data[i].ssdrmcWslb +
                                "</label>" +
                                "</div>"
                            )
                        }
                    }
                });


            }else {
                //业务异常
                alert(data.message)
            }
        },error:function () {
            alert("发起外呼失败")
        }
    });
})


/**
 * 打开新增弹窗 时  数据填充
 */
$("#add_dxtz").on("click", function () {
    //隐藏短信表单那主体 选择工单后显示
    $("#sdp").css("display","none");
    $("#dxtz-zhuti").hide();
    //获取当前登陆账号未结束的可见的工单列表
    $("#dxtz-yysdbh-input").html("<option value=''>请选择工单</option>")
    $.ajax({
        url: "/lylq/get_yysd_list.zf",
        type: 'post',
        success: function (data) {
            yysdList = data || [];
            for (var i = 0; i < data.length; i++) {
                $("#dxtz-yysdbh-input").append("<option value=\"" + data[i].yysdbh + "\">" + data[i].yysdbh + " - " + data[i].ah + "</option>");
            }
            $("#dxtz-yysdbh-input").selectpicker("refresh");
        }
    });
});

function layerErrorMsg(msg){
    //异常提醒
    layui.use("layer",function () {
        layui.layer.msg(msg,{icon:7})
    })
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


function addressRadio() {
    var data = {
        yysdbh: yysdbhall,
        ssdrbh: ssdrbh
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

    var dsrwslbArray  = []
    var checkedbox =  $('input[type=checkbox]:checked');
    $.each(checkedbox,function(){
        // console.log("你选了："+
        //     checkedbox.length+"个，其中有："+$(this).val());
        dsrwslbArray.push($(this).val());
    });


    var data = {
        webCallId: webCallId,
        callstate: dhzt,
        remarks: $("#remarks").val(),
        confirmNumberSet: confirmNumberSet,
        confirmAddressSet: confirmAddressSet,
        electronSend: $("input[name='electronSend']:checked").val(), //是否同意电子送达
        sendType: $("#sendType").val(), //选择送达方式
        sdjg: $("#selectedSdjg").val(),//送达结果
        order: $("#lylq-yysj-input").val() +","+ $("#orderAddress").val(), //送达方式-来院领取数据（单一）
        mailAddress: mailAddress, //送达方式-邮寄送达地址数据（单一）
        callPhone: dxtzNumber, //送达方式-短信电子送达号码数据（单一）
        sdpName: ssdr.ssdrmc,
        yysdBh: yysdbhall,
        createPeople: $.cookie('yhm'),
        yhdm: $.cookie('yhm'),
        ssdrBh: ssdr.ssdrbh,
        wtfs:$("#select_wtfs_dhsd").val(),
        wtclrbh:$("#select_wtclr_dhsd").val(),
        dsrwslbArray:dsrwslbArray
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


function showPhone() {
    $("#tel-tbody").html("");
    //查询当事人号码
    var data = {
        yysdBh: ssdr.yysdbh,
        ssdrbh: ssdr.ssdrbh,

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
                }else if(item.operatorType == 'ENTRY'){
                    htmlStr += "<td>录入</td>";
                }else {
                    htmlStr += "<td>查询</td>";
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

function downloadCallRadio(webCallId) {
    // console.log(webCallId);
    location.href = "/webCall/download_call_radio.zf?webCallId=" + webCallId;

}


function changeWtfs(e) {
    var wtfs = $('#'+$(e).attr('data-id'));
    var selectedWtclr = $('#'+$(e).attr('data-change'));
    $.ajax({
        url: "/query_group.aj",
        type: 'post',
        data: {
            groupName:wtfs.val()
        },
        success: function (data) {
            selectedWtclr.html("<option value=''>请选择 </option>");
            for (var i = 0; i < data.length; i++) {
                selectedWtclr.append("<option value=" + data[i].ryid + ">" +  data[i].rymc + "</option>");
            }
            selectedWtclr.selectpicker("refresh");
        }, error: function () {
            alert("获取分组失败")
        }
    });
}
