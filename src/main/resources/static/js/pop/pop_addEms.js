var yysdbh;
var ssdrbh;
var ssdrmc;

function emsShow(yysdbh1, ssdrbh1, ssdrmc1) {
    yysdbh = yysdbh1;
    ssdrbh = ssdrbh1;
    ssdrmc = ssdrmc1;
    $(".sdp-name").html(ssdrmc);
    $(".sd-name").html(ssdrmc);
    var dsr_dh = $("#dsr_dh");
    dsr_dh.empty();
    var dsr_dz = $("#dsr_dz");
    dsr_dz.empty();
    var sd_dz=$('#sd_dz');
    sd_dz.empty();
    data = {
        yysdBh: yysdbh,
        ssdrbh: ssdrbh
    };

    $.ajax({
        url: "/dxtz/query_sdp_phone.zf",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                if (data[i] != null && data[i].operatorType != null && data[i].operatorType === "ENTRY") {
                    var lableStr = "【录入】";
                    if (data[i].hmly === "HIS_YYSD") {
                        lableStr = "【历史工单】";
                    } else if (data[i].hmly === "HIS_CASE") {
                        lableStr = "【历史案件】";
                    }
                    dsr_dh.append("<div><label><input name=\"ems_dsr_dh\" type=\"checkbox\" value=\"" + data[i].operatorTel + "\">" + lableStr + data[i].showTel + "</label></div>");
                }
            }
        }, error: function () {
            alert("获取号码信息失败")
        }
    });
    if (jsName == 'wbrywdsd.js') {
        dsr_dz.append("   <select id=\"select_dsr_dz\" class=\"form-control selectpicker\" data-live-search=\"true\">\n" +
            "                                <option value=\"\">请选择地址</option>\n" +
            "                            </select>");
        sd_dz.append("   <select id=\"sd_dz\" class=\"form-control selectpicker\" data-live-search=\"true\">\n" +
            "                                <option value=\"\">请选择地址</option>\n" +
            "                            </select>");
        var select_dsr_dz = $("#select_dsr_dz");
        var select_sd_dz = $("#select_sd_dz");
        data = {
            yysdbh: yysdbh,
            ssdrbh: ssdrbh
        };
        $.ajax({
            url: "/ssdrdz/select_ssdrdz.zf",
            type: 'post',
            headers: {'Content-type': 'application/json'},
            dataType: "json",
            data: JSON.stringify(data),
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].dzly == 'HIS_CASE') {
                        select_dsr_dz.append("<option value=\"" + data[i].dz + "\">" + "【历史案件】" + data[i].dz + "</option>");
                        select_sd_dz.append("<option value=\"" + data[i].dz + "\">" + "【历史案件】" + data[i].dz + "</option>");
                    } else if (data[i].dzly == 'HIS_YYSD') {
                        select_dsr_dz.append("<option value=\"" + data[i].dz + "\">" + "【历史工单】" + data[i].dz + "</option>");
                        select_sd_dz.append("<option value=\"" + data[i].dz + "\">" + "【历史工单】" + data[i].dz + "</option>");
                    } else {
                        select_dsr_dz.append("<option value=\"" + data[i].dz + "\">" + data[i].dz + "</option>");
                        select_sd_dz.append("<option value=\"" + data[i].dz + "\">" + data[i].dz + "</option>");
                    }
                }
                select_dsr_dz.selectpicker("refresh");
                select_sd_dz.selectpicker("refresh");
            }, error: function () {
                alert("获取地址信息失败")
            }
        });
    } else {
        dsr_dz.val(xzsdfsxq);
        dsr_dz.append(xzsdfsxq);
    }

    $("#addEmsModel").modal('show');
}

$(document).on('click', '#submit_ems', function () {
    var dhList = [];
    $("input[name='ems_dsr_dh']:checked").each(function (i) {
        dhList.push($(this).val());
    });
    if (dhList.length > 3) {
        alert("最多勾选3个电话号码");
        return;
    }
    var dz = $("#dsr_dz").val();
    if (jsName == 'wbrywdsd.js') {
        dz = $("#select_dsr_dz").val()
    }
    data = {
        yysdbh: yysdbh,
        ssdrbh: ssdrbh,
        dh: dhList,
        dz: dz
    };
    $("#addEmsModel").modal('hide');
    $.ajax({
        url: "/add_emsGd.aj",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            if (data.success) {
                if (jsName == 'dbrwList.js') {
                    updateById();
                }
                window.open(data.object)
            } else {
                alert(data.object)
            }

        }, error: function () {
            alert("获取地址信息失败")
        }
    });
})

function verification(e,id) {
    if (!/^[\d\w]+$/.test(e.value)) {
        $("#tip"+id).text( '必须输入数字或字母，且不能有空格。');
        $("#btn"+id).attr("style","display:none;")
    }
    else if(!/^\d+$/.test(e.value)){
        $("#tip"+id).text( '单号内存在英文字母，请确认后提交');
        $("#btn"+id).attr("style", "display:block;");
    }
    else {
        $("#tip"+id).text('');
        $("#btn"+id).attr("style", "display:block;");
    }
}

function getEmsInfoByKddh(){
    var kddh=$("#kddh").val();
    if(kddh==''){
        return
    }
    var html='';
    var yhm=$.cookie('yhm')
    var params={
        kddh:kddh,
        yhm:yhm,
    }
    $.ajax({
        url:"/getEmsInfoByKddh",
        type: 'post',
        dataType:'json',
        contentType:'application/json',
        data:JSON.stringify(params),
        success:function (data) {
            if(data.length==0){
                html+='<tr><td colspan="4" align="center">暂无符合要求的案件快递单号</td></tr>'
            }
            for (var i = 0; i < data.length; i++){
                html+='<tr align="center">'
                html+='<td><button type="button" class="myButton hasAuthority" onclick="glkdxx('+data[i].kddh+')"><img src="/img/get.png" height="24" width="24" style="margin: 0"></button></td>'
                html+='<td>'+data[i].kddh+'</td>'
                html+='<td>'+data[i].sjrdz+'</td>'
                html+='<td>'+data[i].sjrxm+'</td>'
                html+='</tr>'
            }
            $("#kddh_tbody").empty();
            $("#kddh_tbody").append(html);
        }
    })
}

function glkdxx(kddh) {
    var yhm=$.cookie('yhm')
    var params={
        kddh:kddh,
        yhm:yhm,
        yysdbh:yysdbh,
        ssdrbh:ssdrbh
    }
    $.ajax({
        url:"/glkddh",
        type: 'post',
        dataType:'json',
        contentType:'application/json',
        data:JSON.stringify(params),
        success:function (data) {
            alert(data.message)
        }
    })
    $("#addEmsModel").modal('hide');
}