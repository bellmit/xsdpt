var wbrywdsdVO;
var ssdr;
var yhjs;
var yysdbh;
var fybh;
var yysdList;
var webCallId;
var telDataList = [];
var ssdrVOs;
var logData;
var logPage = 1;
var urlList = [];
var typeArr = [];
var webCallModeList = []; //外呼记录数据集合
var selectedEmsId;
var wslb = 0;
var thisgd = 0;
var ah = 0;
var paramList = [];
var selectedDxtzId = 0;
var thisgd = 0;
var wslb = 0;
var wslbList = [];
var getParamjsonBySsdrbh = 0;
var getParamjsonByYysdbh = 0;
var getParamjsonByBh = 0;
var thislsdx = [];
var zjsdList = [];
var jsName = "wbrywdsd.js";
var msguuid
$(document).ready(function () {
    yhjs = $.cookie('yhjs');
    getCurrentFyNameBySessionFybh();
    getSdry();
});
$(function () {
    layui.use("laydate", function () {
        layui.laydate.render({
            elem: "#lylq-yysj-input",
            type: "datetime",
            min: -7,
            format: "yyyy-MM-dd HH:mm"
        });
        layui.laydate.render({
            elem: "#lylq-yysj-input1",
            type: "datetime",
            min: -7,
            format: "yyyy-MM-dd HH:mm"
        })
    });
})

function preview() {
    $.ajax({
        url: "/dxtz/preview.zf",
        type: "post",
        data: {
            yysdbh: yysdbh
        },
        success: function (data) {
            var html = '<span>【' + data.fymc + '】' + data.ay + '，已向您发送电子送达短信，请及时点击链接localhost:8091/dzsd?uuid=' + data.uuid + '查看文书。</span>'
            $("#dxtz-mbtc1").html(html)
            msguuid = data.uuid
        }
    })
}

function look_zjsdjl(zjsdbh) {
    window.open("zjsd/showZjsdjl?id=" + zjsdbh);
}

function look_zjsdhz(zjsdbh) {
    window.open("zjsd/showSdhz?zjsdbh=" + zjsdbh);
}

function loadSDList(wbryshow, queryType) {
    var phoneStatus = ["", "有效-本人", "可联-非本人", "可联-关机", "可联-停机", "可联-未接通", "可联-再跟进", "可联-正忙", "空号", "呼叫受限", "可联-挂机", "代理人接听", "代理律师接听"];
    $.ajax({
        url: "getwbrysdInfo.do",
        type: "post",
        data: {
            wbryshow: wbryshow,
            queryType: queryType
        },
        success: function (data) {
            wbrywdsdVO = data;
            yysdbh = data.pubYysdJbEntity.yysdbh;
            fybh = data.pubYysdJbEntity.fybh;
            if (queryType === "DHSD") {
                getEmssdInfo();
            }
            var ahHeight = (data.ah.length / 100 + 1) * 25;
            ahHeight += "px";
            $(".ah_background").css('height', data.ah.length < 50 ? '40px' : ahHeight);
            $("#title_ah").html(data.ah);
            if (queryType === "DHSD") {
                webCallModeList = data.pubWebCallInfoModels;
                for (var i = 0; i < data.pubWebCallInfoModels.length; i++) {
                    data.pubWebCallInfoModels[i].callstate = phoneStatus[data.pubWebCallInfoModels[i].callstate];
                }
                loadTable(data.pubWebCallInfoModels, 'dhsdInfos', 'dhsdInfos_html');
            }
            if (queryType === "DXTZ") {
                loadTable(data.dxtzListModels, 'dxtzInfos', 'dxtzInfos_html');
            }

            if (queryType === "LYLQ") {
                loadTable(data.lylqModels, 'lylqInfos', 'lylqInfos_html')
            }

            if (queryType === "ZJSD") {
                loadTable(data.zjsdModels, "zhijsdInfos", "zhijsdInfos_html");
                if (ssdrVOs[0].authority == 0) {
                    $(".secondUploadWj").attr('disabled', true);
                    $(".uploadWj").attr('disabled', true);
                }
            }
            load();
        }
    })
}
function zjsdShow(yysdbh1, ssdrbh1, ssdrmc1) {
    yysdbh = yysdbh1;
    ssdrbh = ssdrbh1;
    ssdrmc = ssdrmc1;
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
                    // dsr_dh.append("<div><label><input name=\"ems_dsr_dh\" type=\"checkbox\" value=\"" + data[i].operatorTel + "\">" + lableStr + data[i].showTel + "</label></div>");
                }
            }
        }, error: function () {
            alert("获取号码信息失败")
        }
    });
    if (jsName == 'wbrywdsd.js') {
        // dsr_dz.append("   <select id=\"select_dsr_dz\" class=\"form-control selectpicker\" data-live-search=\"true\">\n" +
        //     "                                <option value=\"\">请选择地址</option>\n" +
        //     "                            </select>");
        sd_dz.append("   <select id=\"select_sd_dz\" class=\"form-control selectpicker\" data-live-search=\"true\">\n" +
            "                                <option value=\"\">请选择地址</option>\n" +
            "                            </select>");
        // var select_dsr_dz = $("#select_dsr_dz");
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
                        // select_dsr_dz.append("<option value=\"" + data[i].dz + "\">" + "【历史案件】" + data[i].dz + "</option>");
                        select_sd_dz.append("<option value=\"" + data[i].dz + "\">" + "【历史案件】" + data[i].dz + "</option>");
                    } else if (data[i].dzly == 'HIS_YYSD') {
                        // select_dsr_dz.append("<option value=\"" + data[i].dz + "\">" + "【历史工单】" + data[i].dz + "</option>");
                        select_sd_dz.append("<option value=\"" + data[i].dz + "\">" + "【历史工单】" + data[i].dz + "</option>");
                    } else {
                        // select_dsr_dz.append("<option value=\"" + data[i].dz + "\">" + data[i].dz + "</option>");
                        select_sd_dz.append("<option value=\"" + data[i].dz + "\">" + data[i].dz + "</option>");
                    }
                }
                // select_dsr_dz.selectpicker("refresh");
                select_sd_dz.selectpicker("refresh");
            }, error: function () {
                alert("获取地址信息失败")
            }
        });
    } else {
        sd_dz.val(xzsdfsxq);
        sd_dz.append(xzsdfsxq);
    }

    $("#zjsdModel").modal('show');
}

$(document).on('click', '#submit_wfysd', function () {
    var dhList = [];
    $("input[name='wfy_dsr_dh']:checked").each(function (i) {
        dhList.push($(this).val());
    });
    if (dhList.length > 1) {
        alert("最多勾选1个电话号码");
        return;
    }
    var dh=$("input[name='wfy_dsr_dh']:checked").val();
    var dz = $("#wfy_dz").val();
    if (jsName == 'wbrywdsd.js') {
        dz = $("#select_wfy_dz").val()
    }
    data = {
        yysdbh: yysdbh,
        ssdrbh: ssdr.ssdrbh,
        lxdh: dh,
        sddz: dz,
        fsrmc:$.cookie("yhm")
    };
    $("#wfysdModel").modal('hide');
    $.ajax({
        url: "/dzsd/wfysd",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            if (data.success) {
                alert(data.object)
            } else {
                alert(data.object)
            }

        }, error: function () {
            alert("提交微法院送达失败")
        }
    });
})

function wfyShow(yysdbh1, ssdrbh1, ssdrmc1) {
    yysdbh = yysdbh1;
    ssdrbh = ssdrbh1;
    ssdrmc = ssdrmc1;
    $(".sd-name").html(ssdrmc);
    var dsr_dh = $("#wfy_dsr_dh");
    dsr_dh.empty();
    var dsr_dz = $("#dsr_dz");
    dsr_dz.empty();
    var wfy_dz=$('#wfy_dz');
    wfy_dz.empty();
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
                    dsr_dh.append("<div><label>" +
                        "<input name=\"wfy_dsr_dh\" type=\"checkbox\" value=\""
                        + data[i].operatorTel + "\">"
                        + lableStr + data[i].showTel + "</label></div>");
                }
            }
        }, error: function () {
            alert("获取号码信息失败")
        }
    });
    if (jsName == 'wbrywdsd.js') {
        // dsr_dz.append("   <select id=\"select_dsr_dz\" class=\"form-control selectpicker\" data-live-search=\"true\">\n" +
        //     "                                <option value=\"\">请选择地址</option>\n" +
        //     "                            </select>");
        wfy_dz.append("   <select id=\"select_wfy_dz\" class=\"form-control selectpicker\" data-live-search=\"true\">\n" +
            "                                <option value=\"\">请选择地址</option>\n" +
            "                            </select>");
        // var select_dsr_dz = $("#select_dsr_dz");
        var select_wfy_dz = $("#select_wfy_dz");
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
                        // select_dsr_dz.append("<option value=\"" + data[i].dz + "\">" + "【历史案件】" + data[i].dz + "</option>");
                        select_wfy_dz.append("<option value=\"" + data[i].dz + "\">" + "【历史案件】" + data[i].dz + "</option>");
                    } else if (data[i].dzly == 'HIS_YYSD') {
                        // select_dsr_dz.append("<option value=\"" + data[i].dz + "\">" + "【历史工单】" + data[i].dz + "</option>");
                        select_wfy_dz.append("<option value=\"" + data[i].dz + "\">" + "【历史工单】" + data[i].dz + "</option>");
                    } else {
                        // select_dsr_dz.append("<option value=\"" + data[i].dz + "\">" + data[i].dz + "</option>");
                        select_wfy_dz.append("<option value=\"" + data[i].dz + "\">" + data[i].dz + "</option>");
                    }
                }
                // select_dsr_dz.selectpicker("refresh");
                select_wfy_dz.selectpicker("refresh");
            }, error: function () {
                alert("获取地址信息失败")
            }
        });
    } else {
        wfy_dz.val(xzsdfsxq);
        wfy_dz.append(xzsdfsxq);
    }

    $("#wfysdModel").modal('show');
}

$(document).on('click', '#submit_zjsd', function () {
    var dz = $("#dsr_dz").val();
    if (jsName == 'wbrywdsd.js') {
        dz = $("#select_sd_dz").val()
    }
    data = {
        yysdbh: yysdbh,
        ssdrbh: ssdrbh,
        yhm:$("#sdry_select").val(),
        sddz:dz,
        wjmc:$("#wjmc").val()
    };
    $("#zjsdModel").modal('hide');
    $.ajax({
        url: "/zjsd/zjsd.aj",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            if (data.success) {
                if (jsName == 'dbrwList.js') {
                    updateById();
                }
                alert(data.object)
            } else {
                alert(data.object)
            }

        }, error: function () {
            alert("提交失败")
        }
    });
})
function getSdry() {
    $.ajax({
        url: "/getSdry.do",
        type: 'POST',
        success: function (data) {
            for(var i = 0; i < data.length; i++){
                $("#sdryselect").append("<option value=\""+data[i].yhbh+"\">"+data[i].yhmc+"</option>");
                $("#sdry_select").append("<option value=\""+data[i].yhbh+"\">"+data[i].yhmc+"</option>");
            }

        }
    });
}

$(document).ready(function () {
    yhm = $.cookie('yhm');
    var yhjs = $.cookie('yhjs');
    var wbryshow = $.cookie('yysdbh');
    yysdbh = $.cookie('yysdbh');
    getLog();
    getSsdrInfos(wbryshow);


    if (yhjs !== "zjzy") {
        //直接专员不显示其他数据
        loadSDList(wbryshow, "DHSD");
        loadSDList(wbryshow, "DXTZ");
        loadSDList(wbryshow, "LYLQ");
    } else {
        $("#dxtz_table").hide();
        $("#emssd_table").hide();
        $("#dzsd_table").hide();
        $("#lylq_table").hide();

        $("#dhsd").hide();
        $("#dxtz").hide();
        $("#emssd").hide();

        $("#dzsd").hide();
        $("#lylq").hide();

        $("#many_sd_dhsd").hide();
        $("#many_sd_dxtz").hide();
        $("#many_sd_emssd").hide();
        $("#many_sd_dzsd").hide();
        $("#many_sd_lylq").hide();
    }


    $("#submit-lylq").on("click", function () {
        var sdpbh = ssdr.ssdrbh;
        var yylqsj = $("#lylq-yysj-input1").val();
        var lylqaddress = $("#lylq-address-input").val();
        if (!yylqsj) {
            alert("请选择预约时间");
            return;
        }
        if (!lylqaddress || !lylqaddress.trim()) {
            alert("请填写预约地址");
            return;
        }
        var dsrwslbArray = [];
        var checkedbox = $('input[type=checkbox]:checked');
        $.each(checkedbox, function () {
            // console.log("你选了："+
            //     checkedbox.length+"个，其中有："+$(this).val());
            dsrwslbArray.push($(this).val());
        });

        var lylqData = {
            yysdbh: yysdbh,
            ssdrbh: sdpbh,
            yylqsj: new Date(yylqsj),
            lylqaddress: lylqaddress,
            dsrwslbArray: dsrwslbArray
        }
        $.ajax({
            url: "/lylq/addLylq.zf",
            type: 'post',
            headers: {'Content-type': 'application/json'},
            dataType: "json",
            data: JSON.stringify(lylqData),
            success: function (data) {
                //重新加载数据
                alert("新增成功");
                //重新加载数据
                document.location.reload();
                //关闭弹窗
                $("#addLylqModel").modal('hide');
                //清空表单数据
                $("#lylq-yysj-input").val("");
                $("#lylq-address-input").val("");
            }, error: function (res) {
                var errorMsg = res.responseJSON.message || '来院领取操作失败';
                alert(errorMsg)
            }
        });
    });
});

function getSsdrInfos(wbryshow) {
    var yysdbh = wbryshow;
    $.ajax({
        url: "/getSsdrInfos.do",
        type: "post",
        data: {
            yysdbh: yysdbh
        },
        success: function (data) {
            if (data) {
                for (var i = 0; i < data.length; i++) {
                    var ssdrItem = data[i];
                    ssdrItem.havHis = false;
                    ssdrItem.hisBtnId = "hisBtn_" + i;
                    ssdrItem.hisData = {};
                    ssdrItem.yhjs = yhjs;
                }
            }
            ssdrVOs = data;
            var test_data = {
                title: '基本例子',
                isAdmin: true,
                data: ssdrVOs
            };
            var html = template('test', test_data);
            $("#dsrxx").empty().append(html);
            if (ssdrVOs[0].authority == 0) {
                $(".hasAuthority").attr('disabled', true);
            }

            if (data) {
                for (var i = 0; i < data.length; i++) {
                    var ssdrItem = data[i];
                    if (ssdrItem && ssdrItem.sfzhm) {
                        var dataObj = {
                            yysdbh: ssdrItem.yysdbh,
                            ssdrmc: ssdrItem.ssdrmc,
                            sfzhm: ssdrItem.sfzhm
                        }
                        if (yhjs !== 'zjzy') {
                            loadHisSdjl(dataObj, ssdrItem);
                        }
                    }
                }

                loadSDList(yysdbh, "ZJSD");
                if (yhjs === 'zjzy') {
                    $(".hasAuthority").hide();
                }
            }
        }
    })
}

function load() {
    layui.use('upload', function () {

        var upload = layui.upload;
        var uploadInst = upload.render({
            elem: '.secondUploadWj', //绑定元素
            url: '/zjsd/uploadZjsdWj.do', //上传接口
            method: 'POST',
            multiple: false,
            data: {
                yysdbh: selectedYysdbh,
                zjsdbh: selectedzjsdbh
            },
            accept: 'file', //允许上传的文件类型
            size: 304800, //设置文件最大可允许上传的大小，单位 KB
            before: function (obj) {
                $.extend(true, this.data, {
                    yysdbh: selectedYysdbh,
                    zjsdbh: selectedzjsdbh
                })
            },
            done: function (res) {
                //上传完毕回调
                alert(res.message);
                location.reload();
            },

            error: function () {
                //请求异常回调
                alert("上传失败", {icon: 0});
            }
        });
    });
    layui.use('upload', function () {

        var upload = layui.upload;
        var uploadInst = upload.render({
            elem: '.uploadWj', //绑定元素
            url: '/zjsd/uploadZjsdWj.do', //上传接口
            method: 'POST',
            multiple: false,
            data: {
                yysdbh: selectedYysdbh,
                zjsdbh: selectedzjsdbh
            },
            accept: 'file', //允许上传的文件类型
            size: 304800, //设置文件最大可允许上传的大小，单位 KB
            before: function (obj) {
                $.extend(true, this.data, {
                    yysdbh: selectedYysdbh,
                    zjsdbh: selectedzjsdbh
                })
            },
            done: function (res) {
                //上传完毕回调
                alert(res.message);
                location.reload();
            },

            error: function () {
                //请求异常回调
                alert("上传失败", {icon: 0});
            }
        });
    });
}

function downloadWj(e) {
    var zjsdbh = $(e).attr("data-zjsdbh");
    var yysdbh = $(e).attr("data-yysdbh");
    var wjid = $(e).attr("data-wjid");
    var wjmc = $(e).attr("data-wjmc");
    window.open("/zjsd/downloadZjsdWj.do?yysdbh=" + yysdbh + "&zjsdbh=" + zjsdbh + "&wjid=" + wjid + "&wjmc=" + wjmc);
}

var selectedYysdbh;
var selectedzjsdbh;

function cxscWj(e) {
    selectedYysdbh = $(e).attr("data-yysdbh");
    selectedzjsdbh = $(e).attr("data-zjsdbh");

}

function scWj(e) {
    selectedYysdbh = $(e).attr("data-yysdbh");
    selectedzjsdbh = $(e).attr("data-zjsdbh");
}

function downloadCallRadio(webcallid) {
    location.href = "/webCall/download_call_radio.zf?webCallId=" + webcallid;

}

function showMsgContent(msgcontent) {
    // console.log(msgcontent);
    layui.use("layer", function () {
        layui.layer.alert(msgcontent);
    })
}

function look_sdhz(lylqid) {
    // window.open("lylq/imgView_lylq_sdhz.zf?lylqId="+lylqid);
}

function look_kdhz(e) {
    var kdid = $(e).attr("data-index");
    window.open("/ems_sdhz_show.do?kdid=" + kdid + "&fybh=" + wbrywdsdVO.pubYysdJbEntity.fybh);
}

function delete_kdhz(e) {
    var kdid = $(e).attr("data-index");
    //
    //modal遮盖
    var options = {
        type: "POST",
        url: "/ems_sdhz_delete.do",
        data: {"kdid": kdid, "fybh": wbrywdsdVO.pubYysdJbEntity.fybh},
        success: function (data) {
            if (data) {
                layer.confirm("删除成功", {btn: ['确定']}, function () {
                    getEmssdInfo();
                    layer.closeAll()
                })
            } else {
                layer.confirm("删除失败", {btn: ['确定']}, function () {
                    layer.closeAll()
                })
            }
        },
        error: function () {
            layer.confirm("删除失败", {btn: ['确定']}, function () {
                layer.closeAll()
            })

        }
    }
    layer.confirm("是否删除EMS送达-快递回执", {btn: ['确定']}, function () {
        $.ajax(options);
        layer.closeAll()
        layer.load(1, {shade: [0.1, '#fff']})
    })
}

function returnMain() {
    if ($.cookie('preHtml') == 'wbryRwFp') {
        location.href = "/wbryRwFp"
    } else {
        location.href = "/mySd"
    }

}

$('#dsrxx').on("click", ".nine_button > .myButton", function (e) {

    var index = $(this).data("index");
    if ($(this).data("type") == "gddhsd.aj") {
        dhsdShow(index);
        return;
    }

    if ($(this).data("type") == "gddxtz.aj") {
        dxsdShow(index);
        return;
    }
    if ($(this).data("type") == "gdlylq.aj") {
        lylqShow(index);
        return;
    }

    ssdr = ssdrVOs[index];
    if ($(this).data("type") == "gdemssd.aj") {
        emsShow(yysdbh, ssdr.ssdrbh, ssdr.ssdrmc);
        return;
    }

    if($(this).data("type") == "zjsd.aj"){
        zjsdShow(yysdbh, ssdr.ssdrbh, ssdr.ssdrmc)
        // alert("功能暂未开放")
        return;
    }

    if($(this).data("type") == "kysd.aj"){
        // zjsdShow(yysdbh, ssdr.ssdrbh, ssdr.ssdrmc)
        alert("功能正在维护中")
        return;
    }

    if($(this).data("type")=="wfysd.aj"){
        wfyShow(yysdbh, ssdr.ssdrbh, ssdr.ssdrmc)
        return;
    }

    $.ajax({
        type: 'post',
        url: $(this).data("type"),
        data: {
            yysdbh: yysdbh
        },
        success: function (content) {
            if (content.success) {
                var url = content.object;
                // console.log(url);
                window.open(url);
                getLog();
            } else {
                alert("送达失败");
            }
        }
    })

});


function dhsdShow(index) {
    //案号当事人信息
    // console.log(JSON.stringify(wbrywdsdVO))
    ssdr = ssdrVOs[index];
    var caseNo = wbrywdsdVO.ah;

    $(".case-no").html(yysdbh + '-' + wbrywdsdVO.ah);

    $(".sdp-name").html(ssdr.ssdrmc);
    $("#addWebCallModel").modal('show');
    //添加号码
    $("#tel-tbody").html("");
    //查询当事人号码
    var data = {
        sdpName: ssdr.ssdrmc,
        sdpIdCard: ssdr.sfzhm,
        yysdBh: ssdr.yysdbh,
        ssdrbh: ssdr.ssdrbh
    };
    var phoneStatus = ["", "有效-本人", "可联-非本人", "可联-关机", "可联-停机", "可联-未接通", "可联-再跟进", "可联-正忙", "空号", "呼叫受限", "可联-挂机", "代理人接听", "代理律师接听"]

    $.ajax({
        url: "/dxtz/query_sdp_phone.zf",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {


            telDataList = data;
            for (var i = 0; i < telDataList.length; i++) {
                var item = telDataList[i];
                var htmlStr = "<tr>";
                if (item.hmly == 'CYR') {
                    htmlStr += "<td>相关参与人</td>";
                } else if (item.hmly) {
                    htmlStr += "<td>" + _sysCodeToCn(item.hmly, 'SYS_HIS_DATA_LY') + "</td>";
                } else if (item.operatorType == 'ENTRY') {
                    htmlStr += "<td>录入</td>"
                } else {
                    htmlStr += "<td>查询</td>"
                }

                htmlStr += "<td>" + item.showTel + "</td>";

                if (item.newphonestatus) {
                    htmlStr += "<td>" + phoneStatus[item.newphonestatus] + "</td>";
                } else {
                    htmlStr += "<td>暂无</td>";
                }

                // htmlStr += "<td><div data-toggle='modal' data-target='#editWebCallModel' class='web-call-btn btn btn-success' data-index='"+i+"'>拨打</div></td>";
                htmlStr += "<td><div class='web-call-btn btn btn-success' data-index='" + i + "'>拨打</div></td>";

                htmlStr += "</tr>"
                $("#tel-tbody").append(htmlStr);
                getLog();
            }

        }, error: function () {
            alert("获取号码信息失败")
        }
    });
}

//打开发送短信弹窗
function dxsdShow(index) {
    typeArr = [];
    usrList = [];
    $("#demoList").html("");
    $("#uploadFileShow").css("display", "none");
    $("#JZDXLS").css("display", "none");
    //案号当事人信息
    ssdr = ssdrVOs[index];
    if (wbrywdsdVO != null) {
        var caseNo = yysdbh + '-' + wbrywdsdVO.ah;
        $(".case-no").html(caseNo);

    }
    $(".dsrmc").html(ssdr.ssdrmc);
    $("#addDxtzModel").modal('show');
    var data = {
        ssdrbh: ssdr.ssdrbh,
        yysdBh: yysdbh
    };
    $.ajax({
        url: "/dxtz/query_sdp_phone.zf",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            var telData = data;
            $("#tel-tbody1").empty();
            for (var i = 0; i < telData.length; i++) {
                var htmlStr = "<tr><td><input type=\"checkbox\" data-hint='" + telData[i].bh + "' name='dxtz-tel-checkbox'></td>";
                if (telData[i].hmly == 'CYR') {
                    htmlStr += "<td>相关参与人</td>"
                } else if (telData[i].hmly) {
                    htmlStr += "<td>" + _sysCodeToCn(telData[i].hmly, 'SYS_HIS_DATA_LY') + "</td>";
                } else if (telData[i].operatorType == 'ENTRY') {
                    htmlStr += "<td>录入</td>";
                } else {
                    htmlStr += "<td>查询</td>"
                }
                htmlStr += "  <td>" + telData[i].showTel + "</td>";
                htmlStr += "  <td>" + _sysCodeToCn(telData[i].newSendState, 'SYS_SEND_STATE', '暂无') + "</td>";
                htmlStr += "  <td>" + _sysCodeToCn(telData[i].newFwzt, 'SYS_FWZT', '暂无') + "</td></tr>";
                $("#tel-tbody1").append(htmlStr);
            }
            $.ajax({
                url: "/lylq/get_laay.zf",
                type: 'post',
                data: {
                    yysdbh: yysdbh
                },
                success: function (data) {
                    ah = data;
                    $(".laay").html(ah);

                }
            });
            getParamjsonBySsdrbh = ssdr.ssdrbh;
            $.ajax({
                url: "/lylq/get_ws_list.zf",
                type: 'post',
                data: {
                    yysdbh: yysdbh,
                    ssdrbh: getParamjsonBySsdrbh
                },
                success: function (result) {
                    wslb = result;

                }
            });
            $.ajax({
                url: "/dxtz/get_paramjson.zf",
                type: 'post',
                data: {
                    yysdbh: yysdbh,
                    ssdrbh: getParamjsonBySsdrbh,

                },
                success: function (result) {
                    paramList = result;
                }
            });
            $.ajax({
                url: "/lylq/get_yysd.zf",
                type: 'post',
                data: {
                    yysdbh: yysdbh
                },
                success: function (data) {
                    thisgd = data;

                }
            });

            $.ajax({
                url: "/getDsrwslb.aj",
                type: 'post',
                data: {
                    yysdbh: ssdr.yysdbh,
                    ssdrbh: ssdr.ssdrbh
                },
                success: function (data) {
                    $("#wslbcheckboxdx").html("");
                    if (data.length == 0) {
                        $("#wslbcheckboxdx").append("<input class=\"form-control confirmAddress\" rows=\"3\" placeholder=\"无相应文书\"/>");
                    }
                    for (var i = 0; i < data.length; i++) {
                        $("#wslbcheckboxdx").append(
                            "<div class=\"form-check\">" +
                            "<label class=\"form-check-label\">" +
                            " <input type=\"checkbox\" class=\"form-check-input\" value=\"" + data[i].yysdbhBh + "\">" + data[i].ssdrmcWslb +
                            "</label>" +
                            "</div>"
                        )
                    }
                }
            });


            //第二步：显示短信下发主体内容
            $("#dxtz-zhuti").show();
        }, error: function () {
            alert("获取号码信息失败")
        }
    });
    $.ajax({
        url: "/dxtz/load_template_list.zf",
        type: 'post',
        data: {
            fybh: wbrywdsdVO.pubYysdJbEntity.fybh
        },
        success: function (data) {
            $("#dxtz-dxmb-input").empty();
            $("#dxtz-mbtc").empty();
            $("#dxtz-dxmb-input").append(" <option>请选择短信模板</option>");

            dxmbList = data;
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                $("#dxtz-dxmb-input").append("<option value='" + item.bh + "'>" + item.mbmc + "</option>")
            }

        }
    });
}


$('#tel-tbody').on("click", ".web-call-btn", function () {
    var index = $(this).attr("data-index");

    var telData = telDataList[index];
    var webTel = telData.showTel;
    $("#webTel").html(webTel);
    var data = {
        yysdbh: ssdr.yysdbh,
        ssdrbh: ssdr.ssdrbh,
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
                $("#selectedSdjg").val("0");
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
                        $("#wslbcheckboxdh").html("");
                        if (data.length == 0) {
                            $("#wslbcheckboxdh").append("<input class=\"form-control confirmAddress\" rows=\"3\" placeholder=\"无相应文书\"/>");
                        }
                        for (var i = 0; i < data.length; i++) {
                            $("#wslbcheckboxdh").append(
                                "<div class=\"form-check\">" +
                                "<label class=\"form-check-label\">" +
                                " <input type=\"checkbox\" class=\"form-check-input\" value=\"" + data[i].yysdbhBh + "\">" + data[i].ssdrmcWslb +
                                "</label>" +
                                "</div>"
                            )
                        }
                    }
                });


            } else {
                //业务异常
                alert(data.message)
            }
        }, error: function () {
            alert("发起外呼失败")
        }
    });
})


$("#ajxq").on("click", function () {
    loadGdxx();
})

//dsrbh->wsInfos
var wsInfoMap = {}

function loadGdxx() {
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
                isAdmin: 1
            }
            var html = template('gdxqxx', test_data);
            layer.open({
                type: 1,
                title: false,
                closeBtn: 1,
                shadeClose: true,
                area: ['80%', '80%'],
                content: html
            });
            //TODO: 文书上传按钮
            layui.use('upload', function () {
                var upload = layui.upload
                upload.render({
                    elem: '.btn_chooseFile',
                    accept: 'file',
                    acceptMime: 'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/pdf,image/*',
                    auto: false,
                    multiple: true,
                    choose: function (obj) {
                        var btn_chooseFile = this.item;
                        var $wsType = btn_chooseFile.prev().prev().prev()
                        var d_files = btn_chooseFile.parent().prev()
                        var dsrbh = $wsType.data('hint')
                        var yysdbh = $wsType.data('index')
                        obj.preview(function (index, file, result) {
                            if ($wsType.val() === '' || $wsType.val() == null) {
                                layer.msg("请先选择文件类型");
                                return;
                            }
                            if (file.size === 0) {
                                layer.msg("不能上传空文件");
                                return;
                            }
                            var wsInfos = wsInfoMap[dsrbh] == null ? [] : wsInfoMap[dsrbh]
                            var wsInfo = {
                                wslb: $wsType.val(),
                                wsly: 3,
                                wsnr: result,
                                wsmc: file.name,
                                dsrbh: dsrbh,
                                yysdbh: yysdbh
                            }
                            // if(wsInfos.filter(function (x){
                            //     if(x.wslb===$wsType.val()){
                            //         return true;
                            //     }
                            // }).length>0){
                            //     layer.msg('不能上传同一文书类型')
                            //     return;
                            // }
                            wsInfos.push(wsInfo);
                            wsInfoMap[dsrbh] = wsInfos;
                            d_files.append(
                                '<div style="display: -webkit-inline-flex">' +
                                '<div style="color: rgb(0,169,238)">&nbsp;&nbsp;类型：</div>' +
                                '<div style="color: rgb(0,169,238)">' + $wsType.val() + '</div>' +
                                '<div style="color: rgb(0,169,238)">&nbsp;&nbsp;名称：</div>' +
                                '<div style="color: rgb(0,169,238)">' + file.name.substring(0, 15) + '</div>' +
                                '&nbsp;&nbsp;<a class="remove_wsInfo" data-hint="' + dsrbh + '" data-type="' + $wsType.val() + '" style="color: red">移除</a>' +
                                '</div>'
                            )
                            $('.remove_wsInfo').off('click')
                            $('.remove_wsInfo').on('click', function () {
                                var dsrbh = $(this).data('hint');
                                var wsType = $(this).data('type');
                                var wsInfos = wsInfoMap[dsrbh];
                                wsInfos = wsInfos.filter(function (x) {
                                    if (x.wslb !== wsType) {
                                        return true;
                                    }
                                })
                                wsInfoMap[dsrbh] = wsInfos;
                                $(this).parent().remove()
                            })
                        })
                    }
                })
            })
            $('.btn_transferFile').on('click', function () {
                var dsrbh = $(this).data('hint')
                var btn_clearFiles = $(this).prev()
                $.ajax({
                    url: 'uploadFile_Offline.aj',
                    type: 'post',
                    headers: {'Content-type': 'application/json'},
                    data: JSON.stringify(wsInfoMap[dsrbh]),
                    success: function (data) {
                        btn_clearFiles.click()
                        alert(data.message)
                    }
                })
            })
            $('.btn_clearFiles').on('click', function () {
                var d_files = $(this).parent().prev()
                var dsrbh = $(this).data('hint')
                wsInfoMap[dsrbh] = [];
                d_files.html('')
            })
            var yhjs = $.cookie('yhjs');
            if (yhjs === "zjzy") {
                $(".btn-submit-repair").hide();
            }
            if (yhjs != "admin") {
                $(".deletePhone").hide();
            }
        }
    })
}


function qsqrs_update(e) {
    var index = $(e).attr("data-index");
    var ssdr = ssdrVOs[index];
    layer.confirm('修改是否签署送达地址确认书', {
        btn: ['已签署', '未签署']
        , btn1: function (index, layero) {
            var dataJson = {
                dsrbh: ssdr.ssdrbh,
                sfqssddzqrs: 1,
                yysdbh: yysdbh
            }
            updateQsqrs(dataJson);
            layer.close(index);
        }, btn2: function (index, layero) {
            var dataJson = {
                dsrbh: ssdr.ssdrbh,
                sfqssddzqrs: 2,
                yysdbh: yysdbh
            }
            updateQsqrs(dataJson);
            layer.close(index);
        }
    });
};

function updateQsqrs(dataJson) {
    $.ajax({
        url: "/qsqrs_update_gd.aj",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(dataJson),
        success: function (data) {
            location.reload();
        }, error: function () {
            alert("结果更新失败")
        }
    });
}


function tydzsd_update(e) {
    var index = $(e).attr("data-index");
    var ssdr = ssdrVOs[index];
    layer.confirm('修改是否同意电子送达', {
        btn: ['同意', '不同意']
        , btn1: function (index, layero) {
            var dataJson = {
                dsrbh: ssdr.ssdrbh,
                sftydzsd: 1,
                yysdbh: yysdbh
            }
            tydzsdUpdate(dataJson);
            layer.close(index);
        }, btn2: function (index, layero) {
            var dataJson = {
                dsrbh: ssdr.ssdrbh,
                sftydzsd: 2,
                yysdbh: yysdbh
            }
            tydzsdUpdate(dataJson);
            layer.close(index);
        }
    });
};

function tydzsdUpdate(dataJson) {
    $.ajax({
        url: "/tydzsd_update_gd.aj",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(dataJson),
        success: function (data) {
            location.reload();
        }, error: function () {
            alert("结果更新失败")
        }
    });
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
    return dateFormat;

}

//短信模板发生变化
var thisitem = 0;
$("#dxtz-dxmb-input").on("change", function () {
    usrList = [];
    $("#demoList").html("");
    $("#JZDXLS").css("display", "none");
    $("#lj").val("");
    $("#uploadFileShow").css("display", "none");
    var thisVal = $(this).val();
    if (thisVal) {
        for (var i = 0; i < paramList.length; ++i) {
            var item = paramList[i];
            if (thisVal != item.bh) {
                continue;
            }
            thislsdx = item.paramjson;
            if (thislsdx.length != 0) {
                $("#JZDXLS").css("display", "block");
            }

        }
        //选择了模板
        //第一步：重新动态填充短信内容参数模板
        var data = [], fymc;
        for (var i = 0; i < dxmbList.length; i++) {
            var item = dxmbList[i];
            if (thisVal != item.bh) {
                continue;
            }
            fymc = item.fymc;
            thisitem = item;
            var paramNameList = item.paramNameList;
            if (paramNameList.indexOf("文书详情") != -1) {
                $("#uploadFileShow").css("display", "block");
            }
            var arr_step_one = item.mbnr.split(/{{|}}/);
            if (arr_step_one.length > 0) {
                for (var j = 0; j < arr_step_one.length; j++) {
                    var v = arr_step_one[j];
                    var pIndex = paramNameList.indexOf(v);
                    if (pIndex >= 0) {
                        data.push({
                            type: "I", //类型 ： T=>固定文本 I:变量
                            content: "请输入" + v,// 类型为 =》代表固定文本内容 I =》代表输入框placeholder值
                            paramName: v //变量名
                        });
                    } else {
                        data.push({
                            type: "T", //类型 ： T=>固定文本 I:变量
                            content: v,// 类型为 =》代表固定文本内容 I =》代表输入框placeholder值
                        });
                    }
                }
            }

        }


        $("#dxtz-mbtc").html("");
        $("#dxtz-mbtc1").html("");
        var htmlStr = "";
        var html1 = "";
        for (var i = 0; i < data.length; i++) {
            htmlStr = "";
            html1 = "";
            if (data[i].type === 'I') {
                if (data[i].paramName == "文书详情") {
                    htmlStr = "<span><input readonly class='dxmb-param form-control goal" + i.toString() + "'style='width: 30%' aria-describedby=\"input-group-example\" placeholder='" + data[i].content + "' name='" + data[i].paramName + "'  onmouseover=\"this.title=this.value\"/></span>";
                    html1 = "<span> 此处为生成的文书链接 </span>";
                } else if (data[i].paramName == "案件基本信息" || data[i].paramName == "案件信息") {
                    htmlStr = "<input  class='dxmb-param form-control goal" + i.toString() + "' aria-describedby=\"input-group-example\" value=" + thisgd + "  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    html1 = "<span class='text-info goal" + i.toString() + "-view'></span>";
                } else if (data[i].paramName == "文书名称" || data[i].paramName == "请输入文书名称") {
                    htmlStr = "<input  class='dxmb-param form-control goal" + i.toString() + "' aria-describedby=\"input-group-example\" value=" + wslb + " placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    html1 = "<span class='text-info goal" + i.toString() + "-view'></span>";

                } else {
                    htmlStr = "<input class='dxmb-param form-control goal" + i.toString() + "' aria-describedby=\"input-group-example\" placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                    html1 = "<span class='text-info goal" + i.toString() + "-view'></span>";
                }
            } else {
                htmlStr = "<span>" + data[i].content + "</span>";
                html1 = "<span>" + data[i].content + "</span>";

            }
            $("#dxtz-mbtc").append(htmlStr);
            $("#dxtz-mbtc1").append(html1);
        }
        $('.goal0').keyup(function () {
            var gtext = $('.goal0').val()
            $('.goal0-view').html(gtext)
        });
        $('.goal1').keyup(function () {
            var gtext = $('.goal1').val()
            $('.goal1-view').html(gtext)
        });
        $('.goal2').keyup(function () {
            var gtext = $('.goal2').val()
            $('.goal2-view').html(gtext)
        });
        $('.goal3').keyup(function () {
            var gtext = $('.goal3').val()
            $('.goal3-view').html(gtext)
        });
        $('.goal4').keyup(function () {
            var gtext = $('.goal4').val()
            $('.goal4-view').html(gtext)
        });
        $('.goal5').keyup(function () {
            var gtext = $('.goal5').val()
            $('.goal5-view').html(gtext)
        });
        $('.goal6').keyup(function () {
            var gtext = $('.goal6').val()
            $('.goal6-view').html(gtext)
        });
        $('.goal7').keyup(function () {
            var gtext = $('.goal7').val()
            $('.goal7-view').html(gtext)
        });
        $('.goal8').keyup(function () {
            var gtext = $('.goal8').val()
            $('.goal8-view').html(gtext)
        });
        $('.goal9').keyup(function () {
            var gtext = $('.goal9').val()
            $('.goal9-view').html(gtext)
        });
        $('.goal10').keyup(function () {
            var gtext = $('.goal10').val()
            $('.goal10-view').html(gtext)
        });
        $('.goal11').keyup(function () {
            var gtext = $('.goal11').val()
            $('.goal11-view').html(gtext)
        });
        $('.goal12').keyup(function () {
            var gtext = $('.goal12').val()
            $('.goal12-view').html(gtext)
        });
        $('.goal13').keyup(function () {
            var gtext = $('.goal13').val()
            $('.goal13-view').html(gtext)
        });
        $('.goal14').keyup(function () {
            var gtext = $('.goal14').val()
            $('.goal14-view').html(gtext)
        });
        $('.goal15').keyup(function () {
            var gtext = $('.goal15').val()
            $('.goal15-view').html(gtext)
        });
        $('.goal16').keyup(function () {
            var gtext = $('.goal16').val()
            $('.goal16-view').html(gtext)
        });
        $('.goal17').keyup(function () {
            var gtext = $('.goal17').val()
            $('.goal17-view').html(gtext)
        });
        $('.goal18').keyup(function () {
            var gtext = $('.goal18').val()
            $('.goal18-view').html(gtext)
        });
        $('.goal19').keyup(function () {
            var gtext = $('.goal19').val()
            $('.goal19-view').html(gtext)
        });
        $('.goal20').keyup(function () {
            var gtext = $('.goal20').val()
            $('.goal20-view').html(gtext)
        });
        $('.goal21').keyup(function () {
            var gtext = $('.goal21').val()
            $('.goal2l-view').html(gtext)
        });
        $('.goal22').keyup(function () {
            var gtext = $('.goal22').val()
            $('.goal22-view').html(gtext)
        });
        $('.goal23').keyup(function () {
            var gtext = $('.goal23').val()
            $('.goal23-view').html(gtext)
        });
        $('.goal24').keyup(function () {
            var gtext = $('.goal24').val()
            $('.goal24-view').html(gtext)
        });
        $('.goal25').keyup(function () {
            var gtext = $('.goal25').val()
            $('.goal25-view').html(gtext)
        });
        $('.goal26').keyup(function () {
            var gtext = $('.goal26').val()
            $('.goal26-view').html(gtext)
        });
        $('.goal27').keyup(function () {
            var gtext = $('.goal27').val()
            $('.goal27-view').html(gtext)
        });
        $('.goal28').keyup(function () {
            var gtext = $('.goal28').val()
            $('.goal28-view').html(gtext)
        })

        //第二步：显示参数模板内容
        $("#dxtz-param").show();

    } else {
        //没选中 隐藏
        $("#dxtz-param").hide();
    }
})

$("#jzlssj-dx").on("click", function () {
    $("#JZDXLS").css("display", "none");
    $("#demoList").html("");
    $("#uploadFileShow").css("display", "none");
    $("#lj").val("");
    //选择了模板
    //第一步：重新动态填充短信内容参数模板
    var data = [];
    var pu = thislsdx;
    if (pu.length != 0) {
        $("#JZDXLS").css("display", "block");
    }
    var paramNameList = thisitem.paramNameList;
    if (paramNameList.indexOf("文书详情") != -1) {
        $("#uploadFileShow").css("display", "block");
    }


    var arr_step_one = thisitem.mbnr.split(/{{|}}/);
    if (arr_step_one.length > 0) {
        for (var j = 0; j < arr_step_one.length; j++) {
            var v = arr_step_one[j];
            var pIndex = paramNameList.indexOf(v);
            if (pIndex >= 0) {
                data.push({
                    type: "I", //类型 ： T=>固定文本 I:变量
                    content: "请输入" + v,// 类型为 =》代表固定文本内容 I =》代表输入框placeholder值
                    paramName: v //变量名
                });
            } else {
                data.push({
                    type: "T", //类型 ： T=>固定文本 I:变量
                    content: v,// 类型为 =》代表固定文本内容 I =》代表输入框placeholder值
                });
            }
        }
    }


    $("#dxtz-mbtc").html("");
    $("#dxtz-mbtc1").html("");

    var htmlStr = "";
    var html1 = "";
    var htmlStr1 = "";
    var count = 0;

    for (var i = 0; i < data.length; i++) {
        htmlStr = "";
        html1 = "";
        htmlStr1 = "";
        if (data[i].type === 'I') {
            if (data[i].paramName == "文书详情") {
                htmlStr = "<input readonly class='dxmb-param form-control goal" + i.toString() + "' aria-describedby=\"input-group-example\" placeholder='" + data[i].content + "' name='" + data[i].paramName + "'  onmouseover=\"this.title=this.value\"/>";
                htmlStr1 = htmlStr1 + "<input readonly class='dxmb-param form-control goal" + i.toString() + "' aria-describedby=\"input-group-example\"      placeholder='" + data[i].content + "' name='" + data[i].paramName + "'  onmouseover=\"this.title=this.value\"/>";
                html1 = "<span> 此处为生成的文书链接 </span>";
                ++count;
            } else if (data[i].paramName == "案件基本信息" || data[i].paramName == "案件信息") {
                htmlStr = "<input  class='dxmb-param form-control goal" + i.toString() + "' aria-describedby=\"input-group-example\" value=" + thisgd + "  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                htmlStr1 = htmlStr1 + "<input  class='dxmb-param form-control goal" + i.toString() + "' aria-describedby=\"input-group-example\" value=" + thislsdx[count] + "  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                html1 = "<span class=' text-info goal" + i.toString() + "-view'></span>";
                ++count;

            } else if (data[i].paramName == "文书名称" || data[i].paramName == "请输入文书名称") {
                htmlStr = "<input  class='dxmb-param form-control goal" + i.toString() + "' aria-describedby=\"input-group-example\" value=" + wslb + " placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                htmlStr1 = htmlStr1 + "<input  class='dxmb-param form-control goal" + i.toString() + "' aria-describedby=\"input-group-example\" value=" + thislsdx[count] + "  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                html1 = "<span class=' text-info goal" + i.toString() + "-view'></span>";
                ++count;

            } else {
                htmlStr = "<input class='dxmb-param form-control goal" + i.toString() + "' aria-describedby=\"input-group-example\"  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                htmlStr1 = htmlStr1 + "<input  class='dxmb-param form-control goal" + i.toString() + "' aria-describedby=\"input-group-example\" value=" + thislsdx[count] + "  placeholder='" + data[i].content + "' name='" + data[i].paramName + "' onmouseover=\"this.title=this.value\"/>";
                html1 = "<span class=' text-info goal" + i.toString() + "-view'></span>";
                ++count;
            }
        } else {
            htmlStr = "<span>" + data[i].content + "</span>";
            html1 = "<span>" + data[i].content + "</span>";
            htmlStr1 = htmlStr1 + "<span>" + data[i].content + "</span>";

        }

        $("#dxtz-mbtc").append(htmlStr1);
        $("#dxtz-mbtc1").append(html1);
    }
    $('.goal0').keyup(function () {
        var gtext = $('.goal0').val()
        $('.goal0-view').html(gtext)
    });
    $('.goal1').keyup(function () {
        var gtext = $('.goal1').val()
        $('.goal1-view').html(gtext)
    });
    $('.goal2').keyup(function () {
        var gtext = $('.goal2').val()
        $('.goal2-view').html(gtext)
    });
    $('.goal3').keyup(function () {
        var gtext = $('.goal3').val()
        $('.goal3-view').html(gtext)
    });
    $('.goal4').keyup(function () {
        var gtext = $('.goal4').val()
        $('.goal4-view').html(gtext)
    });
    $('.goal5').keyup(function () {
        var gtext = $('.goal5').val()
        $('.goal5-view').html(gtext)
    });
    $('.goal6').keyup(function () {
        var gtext = $('.goal6').val()
        $('.goal6-view').html(gtext)
    });
    $('.goal7').keyup(function () {
        var gtext = $('.goal7').val()
        $('.goal7-view').html(gtext)
    });
    $('.goal8').keyup(function () {
        var gtext = $('.goal8').val()
        $('.goal8-view').html(gtext)
    });
    $('.goal9').keyup(function () {
        var gtext = $('.goal9').val()
        $('.goal9-view').html(gtext)
    });
    $('.goal10').keyup(function () {
        var gtext = $('.goal10').val()
        $('.goal10-view').html(gtext)
    });
    $('.goal11').keyup(function () {
        var gtext = $('.goal11').val()
        $('.goal11-view').html(gtext)
    });
    $('.goal12').keyup(function () {
        var gtext = $('.goal12').val()
        $('.goal12-view').html(gtext)
    });
    $('.goal13').keyup(function () {
        var gtext = $('.goal13').val()
        $('.goal13-view').html(gtext)
    });
    $('.goal14').keyup(function () {
        var gtext = $('.goal14').val()
        $('.goal14-view').html(gtext)
    });
    $('.goal15').keyup(function () {
        var gtext = $('.goal15').val()
        $('.goal15-view').html(gtext)
    });
    $('.goal16').keyup(function () {
        var gtext = $('.goal16').val()
        $('.goal16-view').html(gtext)
    });
    $('.goal17').keyup(function () {
        var gtext = $('.goal17').val()
        $('.goal17-view').html(gtext)
    });
    $('.goal18').keyup(function () {
        var gtext = $('.goal18').val()
        $('.goal18-view').html(gtext)
    });
    $('.goal19').keyup(function () {
        var gtext = $('.goal19').val()
        $('.goal19-view').html(gtext)
    });
    $('.goal20').keyup(function () {
        var gtext = $('.goal20').val()
        $('.goal20-view').html(gtext)
    });
    $('.goal21').keyup(function () {
        var gtext = $('.goal21').val()
        $('.goal2l-view').html(gtext)
    });
    $('.goal22').keyup(function () {
        var gtext = $('.goal22').val()
        $('.goal22-view').html(gtext)
    });
    $('.goal23').keyup(function () {
        var gtext = $('.goal23').val()
        $('.goal23-view').html(gtext)
    });
    $('.goal24').keyup(function () {
        var gtext = $('.goal24').val()
        $('.goal24-view').html(gtext)
    });
    $('.goal25').keyup(function () {
        var gtext = $('.goal25').val()
        $('.goal25-view').html(gtext)
    });
    $('.goal26').keyup(function () {
        var gtext = $('.goal26').val()
        $('.goal26-view').html(gtext)
    });
    $('.goal27').keyup(function () {
        var gtext = $('.goal27').val()
        $('.goal27-view').html(gtext)
    });
    $('.goal28').keyup(function () {
        var gtext = $('.goal28').val()
        $('.goal28-view').html(gtext)
    });
    $('.goal29').keyup(function () {
        var gtext = $('.goal29').val()
        $('.goal29-view').html(gtext)
    });
    $('.goal30').keyup(function () {
        var gtext = $('.goal30').val()
        $('.goal30-view').html(gtext)
    })

    //第二步：显示参数模板内容
    $("#dxtz-param").show();

});

function jzlssj() {
    if ($("#dxtz-mbtc").change()) {
        alert("加载完成");
        return;
    }

}


/**
 * 发送短信按钮点击事件
 */
$("#sendMSg").on("click", function () {

    var telCheckedEle = $("input[name='dxtz-tel-checkbox']:checked");
    if(!telCheckedEle.length){
        layerErrorMsg("请选择下发号码");
        return;
    }
    var targetTelBhs = [];
    for (var i = 0; i < telCheckedEle.length; i++) {
        var telId = $(telCheckedEle[i]).data("hint");
        targetTelBhs.push(telId)
    }


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
                    paramValue: value
                })
            }
        }else {
            itemEle.addClass("input-error");
            inputError = true;
            errorMsg = "请完善短信模板参数信息"
        }
    }
    if(inputError){
        layerErrorMsg(errorMsg);
        return;
    }

    // 保存文书类型
    var dsrwslbArray  = []
    var checkedbox =  $('input[type=checkbox]:checked');
    $.each(checkedbox,function(){
        // console.log("你选了："+
        //     checkedbox.length+"个，其中有："+$(this).val());
        dsrwslbArray.push($(this).val());
    });


    var dataJson = {
        targetTelBhs: targetTelBhs,
        templateId: dxmb,
        yysdbh: yysdbh,
        paramObjList: paramObjList,
        ssdrbh:ssdr.ssdrbh,
        urlLis: usrList,
        dsrwslbArray: dsrwslbArray
    }
    //请求API
    $.ajax({
        url: "/dxtz/send_short_msg.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data:JSON.stringify(dataJson),
        success: function (data) {
            $("#demoList").html("");
            //重新加载数据
            document.location.reload();
            //关闭弹窗
            $("#addDxtzModel").modal('hide');
            //清空表单数据
            $("#dxtz-yysdbh-input").val("");
            getLog();
        },error:function (res) {
            var errorMsg = res.responseJSON.message || '短信下发操作失败';
            alert(errorMsg)
        }
    });

})
// $("#sendMSg").on("click", function () {
//     var targetTelBhs = [];
//     for (var i = 0; i < telCheckedEle.length; i++) {
//         var telId = $(telCheckedEle[i]).data("hint");
//         targetTelBhs.push(telId)
//     }
//     var dataJson = {
//         // targetTelBhs: targetTelBhs,
//         // templateId: dxmb,
//         yysdbh: yysdbh,
//         // paramObjList: paramObjList,
//         ssdrbh: ssdr.ssdrbh,
//         // urlLis: usrList,
//         dsrwslbArray: dsrwslbArray
//     }
//     $.ajax({
//         url: "/dxtz/send_short_msg.zf",
//         type: 'post',
//         headers: {'Content-type': 'application/json'},
//         dataType: "json",
//         data: JSON.stringify(dataJson),
//         success: function (data) {
//             $("#demoList").html("");
//             //重新加载数据
//             document.location.reload();
//             //关闭弹窗
//             $("#addDxtzModel").modal('hide');
//             //清空表单数据
//             $("#dxtz-yysdbh-input").val("");
//             getLog();
//         }, error: function (res) {
//             var errorMsg = res.responseJSON.message || '短信下发操作失败';
//             alert(errorMsg)
//         }
//     });
// })
/**
 * 发送状态下拉框改变时间
 */
$("#select_send_state").on("change", function () {
    var data = {
        sendstate: $(this).val()
    }
    loadDxtzList(data);
})

function lylqShow(index) {
    //案号当事人信息
    ssdr = ssdrVOs[index];
    var caseNo = wbrywdsdVO.ah;
    $(".case-no").html(caseNo);
    $(".sdp-name").html(ssdr.ssdrmc);
    $("#addLylqModel").modal('show');

    $.ajax({
        url: "/getDsrwslb.aj",
        type: 'post',
        data: {
            yysdbh: ssdr.yysdbh,
            ssdrbh: ssdr.ssdrbh
        },
        success: function (data) {
            $("#wslbcheckboxlylq").html("");
            if (data.length == 0) {
                $("#wslbcheckboxlylq").append("<input class=\"form-control confirmAddress\" rows=\"3\" placeholder=\"无相应文书\"/>");
            }
            for (var i = 0; i < data.length; i++) {
                $("#wslbcheckboxlylq").append(
                    "<div class=\"form-check\">" +
                    "<label class=\"form-check-label\">" +
                    " <input type=\"checkbox\" class=\"form-check-input\" value=\"" + data[i].yysdbhBh + "\">" + data[i].ssdrmcWslb +
                    "</label>" +
                    "</div>"
                )
            }
        }
    });


}


/**
 * 提交修复
 */
function submitRepair(e) {
    var ssdrbh = $(e).attr('data-hint');
    if (yysdbh) {
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
            headers: {'Content-type': 'application/json'},
            dataType: "json",
            data: JSON.stringify(dataParam),
            success: function (data) {
                if (data.code == '200') {
                    //成功
                    alert("提交成功");
                    getSsdrInfos(yysdbh);
                } else {
                    //失败
                    alert(data.message);
                }
            }, error: function () {
                alert("操作失败")
            }
        });
    }


}


$("#submitEditWebCall").on("click", function () {

    //1.电话状态校验
    var dhzt = $("#dhzt-select").val();
    if (!dhzt) {
        alert("请选择电话状态");
        return;
    }
    //2.号码确认校验
    var confirmNumberSet = []; //确认号码数组
    var error = false;
    $(".confirmNumber").each(function () {
        var str = $(this).val();
        if (null == str || undefined == str || str.trim().length == 0) {
        } else {
            str = str.trim();
            var phoneReg = /^\d{10,12}$/;
            if (!phoneReg.test(str)) {
                alert("确认号码格式不规范：" + str);
                error = true;
            } else {
                confirmNumberSet.push(str)
            }
        }
    })
    if (error) {
        return;
    }

    //3.封装确认地址
    var confirmAddressSet = []; //确认地址组
    $(".confirmAddress").each(function () {
        var address = $(this).val();
        if (null == address && undefined == address) {
            address = "";
        }
        address = address.trim();
        if (address.length > 0) {
            confirmAddressSet.push(address)
        }
    })

    //4.送达方式相关数据处理
    var sendType = $("#sendType").val();
    var dxtzNumber = $("input[name='show']:checked").val();
    var mailAddress = $("input[name='address']:checked").val();
    if (sendType == 2) {
        //邮寄送达-优先输入框内容
        if (null != $("#emsAddress").val() && undefined != $("#emsAddress").val() && $("#emsAddress").val().trim().length > 0) {
            mailAddress = $("#emsAddress").val();
        }
        if (null == mailAddress || undefined == mailAddress || mailAddress.trim().length == 0) {
            alert("请选择或输入邮寄送达地址")
            return;
        }
    } else if (sendType == 3) {
        //选择短信下发通知-所选号码-优先输入框内容

        if (null != $("#dxtzNumber").val() && undefined != $("#dxtzNumber").val() && $("#dxtzNumber").val().trim().length > 0) {
            dxtzNumber = $("#dxtzNumber").val();
        }
        if (null == dxtzNumber || undefined == dxtzNumber || dxtzNumber.trim().length == 0) {
            alert("请选择或输入短信电子送达号码")
            return;
        } else {
            if (dxtzNumber.indexOf("1") != 0 || dxtzNumber.length != 11) {
                alert("短信电子送达号码格式不规范:" + dxtzNumber)
                return;
            }
        }
    }

    // 保存文书类型
    var dsrwslbArray = []
    var checkedbox = $('input[type=checkbox]:checked');
    $.each(checkedbox, function () {
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
        order: $("#lylq-yysj-input").val() + "," + $("#orderAddress").val(), //送达方式-来院领取数据（单一）
        mailAddress: mailAddress, //送达方式-邮寄送达地址数据（单一）
        callPhone: dxtzNumber, //送达方式-短信电子送达号码数据（单一）
        sdpName: ssdr.ssdrmc,
        caseNo: wbrywdsdVO.ah,
        yysdBh: wbrywdsdVO.pubYysdJbEntity.yysdbh,
        createPeople: $.cookie('yhm'),
        yhdm: $.cookie('yhm'),
        ssdrBh: ssdr.ssdrbh,
        ajxh: wbrywdsdVO.pubYysdJbEntity.ajxh,
        fybh: wbrywdsdVO.pubYysdJbEntity.fybh,
        wtfs: $("#select_wtfs_dhsd").val(),
        wtclrbh: $("#select_wtclr_dhsd").val(),
        dsrwslbArray: dsrwslbArray

    }
    $.ajax({
        url: "/webCall/submit_web_call.zf",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            showPhone();
            $("#selectedWtfs").val('');
            wtclrbh:$("#selectedWtclr").val('');
            $("#radio").val('');
            $('input:radio[name="electronSend"]').prop('checked', false);
            $("#editWebCallModel").modal('hide');

        }, error: function () {
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
        sdpName: wbrywdsdVO.ah,
        sdpIdCard: ssdr.sfzhm,
        yysdBh: wbrywdsdVO.pubYysdJbEntity.yysdbh,
        ssdrbh: ssdr.ssdrbh

    };
    var phoneStatus = ["", "有效-本人", "可联-非本人", "可联-关机", "可联-停机", "可联-未接通", "可联-再跟进", "可联-正忙", "空号", "呼叫受限", "可联-挂机", "代理人接听", "代理律师接听"]

    $.ajax({
        url: "/dxtz/query_sdp_phone.zf",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            telDataList = data;

            for (var i = 0; i < telDataList.length; i++) {
                var item = telDataList[i];
                var htmlStr = "<tr>";
                if (item.hmly == 'CYR') {
                    htmlStr += "<td>相关参与人</td>";
                } else if (item.operatorType == 'ENTRY') {
                    htmlStr += "<td>录入</td>";
                } else {
                    htmlStr += "<td>查询</td>";
                }

                htmlStr += "<td>" + item.showTel + "</td>";

                if (item.newphonestatus) {
                    htmlStr += "<td>" + phoneStatus[item.newphonestatus] + "</td>";
                } else {
                    htmlStr += "<td>暂无</td>";
                }

                // htmlStr += "<td><div data-toggle='modal' data-target='#editWebCallModel' class='web-call-btn btn btn-success' data-index='"+i+"'>拨打</div></td>";
                htmlStr += "<td><div class='web-call-btn btn btn-success' data-index='" + i + "'>拨打</div></td>";

                htmlStr += "</tr>"
                $("#tel-tbody").append(htmlStr);
            }

        }, error: function () {
            alert("获取号码信息失败")
        }
    });
}

function addressRadio() {
    var data = {
        yysdbh: ssdr.yysdbh,
        ssdrbh: ssdr.ssdrbh
    };
    $.ajax({
        url: "/ssdrdz/select_ssdrdz.zf",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                var html = "<div>";
                html += '<input id="addressRadio' + i + '" type="radio" name="address" value="' + item.dz + '" />';
                html += '<label for="addressRadio' + i + '">' + item.dz + '</label>';
                html += "</div>";
                $("#addressList").append(html);
            }
        }, error: function () {
            alert("获取地址信息失败")
        }
    });

}

function addressRadio() {
    var data = {
        yysdbh: ssdr.yysdbh,
        ssdrbh: ssdr.ssdrbh
    };
    $.ajax({
        url: "/ssdrdz/select_ssdrdz.zf",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var item = data[i]
                var html = "<div>";
                html += '<input id="addressRadio' + i + '" type="radio" name="address" value="' + item.dz + '" />';
                html += '<label for="addressRadio' + i + '">' + item.dz + '</label>';
                html += "</div>";
                $("#addressRadio").append(html);
            }
        }, error: function () {
            alert("获取地址信息失败")
        }
    });

}

var qrdz;
$('#addressRadio').on("change", "input", function () {
    qrdz = $(this).val();
})


function getLog() {
    $("#buttom_log_ul").empty();
    $.ajax({
        url: "/getlogGd.aj",
        type: 'POST',
        dataType: 'json',
        data: {
            yysdbh: yysdbh
        },
        success: function (data) {
            logData = data;
            showLog(1);

        }
    });
}

function showLog(index) {
    $("#buttom_log_ul").empty();
    var startIndex = 0;
    if (5 * (index - 1) + 4 >= logData.length) {
        startIndex = logData.length - 1;
    } else {
        startIndex = 5 * (index - 1) + 4;
    }
    for (var i = startIndex; i >= 5 * (index - 1); i--) {
        var date = new Date(logData[i].creattime);
        var day = date.getDate();
        var month = date.getMonth() + 1;
        var year = date.getFullYear();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var second = date.getSeconds();
        html = "<div class=\"col log\">\n" +
            "                            <div class=\"d-flex flex-column\" style=\"font-size: 10px;\">\n" +
            "                                <div style=\"margin-top: 20%;color: red;font-size: 12px;font-weight: bold;\">"+year+"-"+month+"-"+day+"\n" +
            "                                    "+hour+":"+minute+":"+second+"\n" +
            "                                </div>\n" +
            "                                <div style=\"margin-top: 5%;\">【"+logData[i].creater+"】</div>\n" +
            "                                <div style=\"margin-top: 5%;\">"+logData[i].type+"</div>\n"
        if (logData[i].targetname !== " " && logData[i].targetname !== null && !logData[i].targetname.isUndefined) {
            html += "<div style=\"margin-top: 5%;\">被操作对象 :" + logData[i].targetname + "</div>\n"
        }
        html += "</div>\n" +
            "</div>";
        $("#buttom_log_ul").prepend(html);

    }
}

//日志分页
$("#nextLogPage").on("click", function () {
    if (logPage < ((logData.length) / 5)) {
        logPage++;
        showLog(logPage);
    }
})

$("#preLogPage").on("click", function () {
    if (logPage <= 1) {
        return;
    }
    logPage--;
    showLog(logPage);
})


layui.use('upload', function () {
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
        // data: {dsrwslbArray: function () {
        //         var dsrwslbArray  = []
        //         var checkedbox =  $('input[type=checkbox]:checked');
        //         $.each(checkedbox,function(){
        //             dsrwslbArray.push($(this).val());
        //         });
        //         return dsrwslbArray;
        //     }},
        choose: function (obj) {
            var files = obj.pushFile();
            obj.preview(function (index, file, result) {
                typeArr.push(file.type);
                var falg = false;
                for (var i = 0; i < typeArr.length; i++) {
                    if (typeArr[0].split("/")[0] != file.type.split("/")[0]) {
                        alert("请上传同一种类型文件");
                        falg = true;
                        delete files[index];
                        break;
                    }
                }
                if (!falg) {
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
                        if (delLen == 0) {
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
            for (var key in files) {
                var eleLen = $("#upload-" + key).length;
                if (eleLen == 0) {
                    //元素不存在 移除文件
                    delete files[key];
                }
            }
        },
        done: function (res, index, upload) {
            if (null != res) {
                usrList.push(res.data);
                var delLen = $(".demo-delete").length; //删除按钮个数
                if (delLen == usrList.length) {
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

function sclj() {
    if ($("#demoList").html() == '') {
        alert("请先上传文件");
        return;
    }
    usrList = [];
    $("#sclj-btn").text("正在生成，请稍后").addClass("layui-btn-disabled");
}

function showImages(dxtzid) {
    window.open("dxtz/showImages_fg?yhm=" + "a" + "&id=" + dxtzid);
}

function layerErrorMsg(msg) {
    //异常提醒
    layui.use("layer", function () {
        layui.layer.msg(msg, {icon: 7})
    })
}


function addPhone(val) {
    var addPhone = "<div class='input-group'><input class=\"col-sm-9 form-control confirmNumber\" rows=\"3\" placeholder=\"请确认号码\"/><div onclick='deleteInput(this)' class='input-group-addon'>移除</div></div>";
    if (val == "addPhoneA") {
        $('#phone').append(addPhone);
    }
    if (val == "addPhoneB") {

        //短信电子送达号码
        var text = $("#phoneListInput").text();
        if (null != text && undefined != text && text.trim().length > 0) {
            //存在输入框 不处理
        } else {
            addPhone = "<div class='input-group'><input class=\"col-sm-9 form-control\" id='dxtzNumber' rows=\"3\" placeholder=\"请确认号码\"/><div onclick='deleteInput(this)' class='input-group-addon'>移除</div></div>";
            $('#phoneListInput').html(addPhone);
        }
    }
}

function addAddress(val) {
    var addAddress = "<div class='input-group'><input class=\"form-control confirmAddress\" rows=\"3\" placeholder=\"请确认地址\"/><div onclick='deleteInput(this)' class='input-group-addon'>移除</div></div>";
    if (val == "addAddressA") {
        $('#address').append(addAddress);
    }
    if (val == "addAddressB") {
        //邮寄送达地址
        var text = $('#addressInput').text();
        if (null != text && undefined != text && text.trim().length > 0) {
            //存在输入框 不处理
        } else {
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
    if (val == 0) {
        $("#showA").css("display", "none")
        $("#showB").css("display", "none")
        $("#showC").css("display", "none")
    }
    if (val == 1) {
        $("#showA").css("display", "block")
        $("#showB").css("display", "none")
        $("#showC").css("display", "none")
    }
    if (val == 2) {
        addressRadio();
        $("#showB").css("display", "block")
        $("#showA").css("display", "none")
        $("#showC").css("display", "none")
    }
    if (val == 3) {
        $("#showC").css("display", "block")
        $("#showA").css("display", "none")
        $("#showB").css("display", "none")
        for (var i = 0; i < telDataList.length; i++) {
            var item = telDataList[i];
            var telList = "<input type='radio' id='confirmNumber' name='show' class='radio-inline' value='" + item.showTel + "'/>" + item.showTel + " "
            $('#phoneList').append(telList);
        }
    }
}

/**
 * 录入当事人数据
 * 地址/联系电话/身份证号
 */
function inputData(e) {
    var ssdrbh = $(e).attr('data-hint'); //受送达人编号
    var yysdbh = $(e).attr('data-index'); //工单编号
    var inputDataType = $(e).attr('data-type'); //录入类型

    var title = "";
    if (inputDataType == "INPUT_PHONE") {
        title = "添加联系电话";
    } else if (inputDataType == "INPUT_ID_CARD") {
        title = "添加身份证号";
    } else if (inputDataType == "INPUT_ADDRESS") {
        title = "添加地址信息";
    } else {
        //other
    }

    var html = "<div style=\"float: left;font-size: 15px;color: rgb(0,169,238);margin-left: 30px;margin-top: 30px;\">内容:&nbsp&nbsp<input id=\"input-data-content\" ></div>";
    layer.open({
        type: 1,
        title: title,
        shadeClose: true,
        area: ['300px', '150px'],
        content: html,
        btn: ["确认提交"],
        closeBtn: 1,
        btn1: function (index, layero) {
            var dataObj = {
                yysdbh: yysdbh,
                ssdrbh: ssdrbh,
                inputDataType: inputDataType,
                content: $("#input-data-content").val()
            }
            $.ajax({
                url: "/ssdr/input_data.zf",
                type: "post",
                headers: {'Content-type': 'application/json'},
                dataType: "json",
                data: JSON.stringify(dataObj),
                success: function (data) {
                    var code = data.code;
                    if (code == '200') {
                        alert("提交成功");
                        layer.closeAll();//关闭所有弹窗
                        loadGdxx(); //重新打开工单详情弹窗
                    } else {
                        //业务异常
                        alert(data.message)
                    }
                }, error: function () {
                    alert("提交失败")
                }
            })
        }
    })
}

function viewWebCallInfo_item(item, title) {
    var phone = item.confirmnumber, address = item.confirmaddress;
    var phoneArr = [], addressArr = [], area = ["400px", "400px"];
    if (null != phone) {
        phoneArr = phone.split(",");
    }
    if (null != address) {
        addressArr = address.split(",");
    }

    var html = "\n" +
        "            <div style='padding: 5px;'>\n";

    if ("确认信息" == title) {
        html += "<div><blockquote class=\"custm-blockquote layui-elem-quote\">确认号码：</blockquote>"
        if (phoneArr.length > 0) {
            phoneArr.forEach(function (pItem) {
                html += "<blockquote class=\"custm-blockquote layui-elem-quote layui-quote-nm\">" + (pItem || "&nbsp;") + "</blockquote>"
            })
        } else {
            html += "<div style='margin-left: 10px;'>暂无</div>"
        }
        html += "</div>\n"

        html += "<div><blockquote class=\"custm-blockquote layui-elem-quote\">确认地址：</blockquote>"
        if (addressArr.length > 0) {
            addressArr.forEach(function (aItem) {
                html += "<blockquote class=\"custm-blockquote layui-elem-quote layui-quote-nm\">" + (aItem || "&nbsp;") + "</blockquote>"
            })
        } else {
            html += "<div style='margin-left: 10px;'>暂无</div>"
        }
        html += "</div>\n"
    } else if ("选择送达方式信息" == title) {
        area = ["400px", "230px"]
        if (item.sdtype != null) {

            if (item.sdtype == 1) {
                html += "<div><blockquote class=\"custm-blockquote layui-elem-quote\">来院领取：</blockquote>"
            } else if (item.sdtype == 2) {
                html += "<div><blockquote class=\"custm-blockquote layui-elem-quote\">邮寄送达：</blockquote>"
            } else if (item.sdtype == 3) {
                html += "<div><blockquote class=\"custm-blockquote layui-elem-quote\">短信电子送达：</blockquote>"
            }
            html += "<blockquote class=\"custm-blockquote layui-elem-quote layui-quote-nm\">" + (item.sdfscontent || "&nbsp;") + "</blockquote>"

            html += "</div>\n"
        }
    }


    html += "</div>";
    layer.open({
        type: 1,
        title: title,
        shadeClose: true,
        area: area,
        content: html,
        btn: ["确认"],
        closeBtn: 1,
        btn1: function (index, layero) {
            layer.close(index);
        }
    })
}

function viewWebCallInfo(index, title) {
    var item = webCallModeList[index];
    viewWebCallInfo_item(item, title)
}

function viewWebCallInfo1(webcallid, title) {
    for (var i = 0; i < webCallModeList.length; i++) {
        if (webCallModeList[i].webcallid == webcallid) {
            ;
            viewWebCallInfo_item(webCallModeList[i], title);
            break;
        }
    }
}


function scmd(e) {
    selectedEmsId = $(e).attr('data-index');
}

$("input[name='dxtz-tel-checkbox-checkAll']").on("change", function () {
    if (this.checked) {
        $(":checkbox[name='dxtz-tel-checkbox']").not(":disabled").each(function () {
            this.checked = true
        })
    } else {
        $(":checkbox[name='dxtz-tel-checkbox']").not(":disabled").each(function () {
            this.checked = false
        })
    }
    // $("input[name='dxtz-tel-checkbox']").prop("checked", $(this).is(":checked"))
})

$('#dxtzjbxx_tbody').on("click", ".msgcontent-look", function () {
    var msg = $(this).data("hint");
    layui.use("layer", function () {
        layui.layer.alert(msg);
    })
})

function uploadKddh(e) {

    var yysdbh = wbrywdsdVO.pubYysdJbEntity.yysdbh;
    var kdid = $(e).attr('data-index');
    // var html ="<div style=\"float: left;font-size: 15px;color: rgb(0,169,238);margin-left: 15px;margin-top: 30px;\">快递单号:&nbsp<input id=\"input-kddh\" ></div>";
    // layer.open({
    //     type: 1,
    //     title: '上传快递单号',
    //     shadeClose: true,
    //     area: ['300px', '150px'],
    //     content: html,
    //     btn: ["确认提交"],
    //     closeBtn: 1,
    //     btn1:function (index, layero) {
    $.ajax({
        url: "/uploadKddh.aj",
        type: 'POST',
        dataType: 'json',
        data: {
            yysdbh: yysdbh,
            kdid: kdid,
            kddh: $("#input-kddh" + kdid).val()
        },
        success: function (data) {
            getEmssdInfo();
            alert(data.message);
            layer.closeAll();//关闭所有弹窗
        }, error: function () {
            alert("修改失败");
        }
    });
    // }
    // })


}

function getEmssdInfo() {
    $.ajax({
        url: "/getGdEmssdInfos.do",
        type: 'POST',
        dataType: 'json',
        data: {
            yysdbh: yysdbh,
            fybh: fybh
        },
        success: function (data) {
            loadTable(data, 'emssdInfos', 'emssdInfos_html');
            if (yhjs == 'user') {
                // $("#emssdInfos .hasAuthority").attr('disabled', true);
            }
            layui.use('upload', function () {
                var upload = layui.upload;
                var uploadInst = upload.render({
                    elem: '.uploadEmsmd', //绑定元素
                    url: '/uploadEmsmd.do', //上传接口
                    method: 'POST',
                    multiple: false,
                    data: {
                        yysdbh: yysdbh,
                        kdid: selectedEmsId
                    },
                    accept: 'images', //允许上传的文件类型
                    size: 204800, //设置文件最大可允许上传的大小，单位 KB
                    before: function (obj) {
                        $.extend(true, this.data, {
                            yysdbh: yysdbh,
                            kdid: selectedEmsId
                        })
                    },
                    done: function (res) {
                        //上传完毕回调
                        alert(res.message);
                        location.reload();
                    },

                    error: function () {
                        //请求异常回调
                        alert("上传失败", {icon: 0});
                    }
                });
            })
            layui.use('upload', function () {
                var upload = layui.upload;
                var uploadInst = upload.render({
                    elem: '.upload_sdhz', //绑定元素
                    url: '/upload_ems_kdhz.do', //上传接口
                    method: 'POST',
                    multiple: false,
                    data: {
                        yysdbh: yysdbh,
                        kdid: selectedEmsId
                    },
                    acceptMime: 'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document, text/plain,application/pdf,image/*',
                    size: 204800, //设置文件最大可允许上传的大小，单位 KB
                    before: function (obj) {
                        $.extend(true, this.data, {
                            yysdbh: yysdbh,
                            kdid: selectedEmsId
                        })
                    },
                    done: function (res) {
                        //上传完毕回调
                        alert(res.message);
                        location.reload();
                    },

                    error: function () {
                        //请求异常回调
                        alert("上传失败", {icon: 0});
                    }
                });
            })
        }
    });
}

function downloadMd(e) {
    window.open("/downloadEmsmd.do?yysdbh=" + yysdbh + "&kdid=" + $(e).attr('data-index'));
}

function deleteMd(e) {
    layer.load(1, {shade: [0.1, '#fff']})
    var options = {
        type: "POST",
        url: "/deleteEmsmd.do",
        data: {
            "yysdbh": yysdbh,
            "kdid": $(e).attr("data-index"),
            "fybh": wbrywdsdVO.pubYysdJbEntity.fybh
        },
        success: function (data) {
            if (data.success) {
                layer.confirm("删除成功", {btn: ['确定']}, function () {
                    getEmssdInfo()
                    layer.closeAll()
                })
            } else {
                layer.confirm("删除失败", {btn: ['确定']}, function () {
                    layer.closeAll()
                })
            }
        },
        error: function () {
            layer.confirm("删除失败", {btn: ['确定']}, function () {
                layer.closeAll()
            })
        }
    }
    //modal遮盖
    layer.confirm("是否删除EMS送达-快递面单", {btn: ['确定']}, function () {
        $.ajax(options);
        layer.closeAll()
        layer.load(1, {shade: [0.1, '#fff']})
    })


}


/**
 * 查询当事人历史送达记录
 * @param dataObj
 */
function loadHisSdjl(dataObj, ssdr) {
    dataObj = dataObj || {};
    ssdr = ssdr || {};
    $.ajax({
        url: "/ssdr/query_ssdr_his_data.zf",
        type: 'POST',
        headers: {'Content-type': 'application/json'},
        dataType: 'json',
        // async:false, //同步 此次必须加
        data: JSON.stringify(dataObj),
        success: function (res) {
            if (res.code === "200") {
                //请求成功
                var data = res.data;
                if (data.hisWebCall.length > 0 || data.hisDxtz.length > 0) {
                    ssdr.havHis = true;
                    ssdr.hisData = {
                        hisWebCall: data.hisWebCall,
                        hisDxtz: data.hisDxtz
                    }
                    if (ssdr.havHis) {
                        $("#" + ssdr.hisBtnId).show();
                    }
                } else {
                    ssdr.havHis = false;
                    ssdr.hisData = {};
                }
                return ssdr;
            } else {
                alert(res.msg || "异常")
            }
        }
    });
}

function openHisDialog(e) {
    var index = $(e).attr("data-index");
    var ssdr = ssdrVOs[index];
    var hisData = ssdr.hisData || [];
    himWebCall(hisData.hisWebCall, ssdr);
    himDx(hisData.hisDxtz, ssdr);
    himEms(hisData.hisEms, ssdr);
    $("#hisYysdRecordModel").modal('show');

}

var hisWebCall = [];

function himWebCall(data, ssdr) {

    $("#dhsdxx_his").empty();
    data = data || [];
    ssdr = ssdr || {};
    hisWebCall = data;
    for (var i = 0; i < data.length; i++) {
        var item = data[i];
        var electronsend = item.electronsend;
        if (null == electronsend) {
            electronsend = "";
        } else if (electronsend == 0) {
            electronsend = "是";
        } else if (electronsend == 1) {
            electronsend = "否";
        } else {
            //other
            electronsend = "";
        }

        if (null == item.callstate) {
            item.callstate = 0;
        }
        var html = "<tr>\n" +
            "<td>" + ssdr.ssdrmc + "</td>\n" +
            "<td>" + item.webtel + "</td>\n";
        if (item.confirmnumber != null || item.confirmaddress != null) {
            html += "<td><button type='button' class='btn' onclick='viewWebCallInfo_his(" + i + ",\"确认信息\")'><img src=\"/img/detail.png\" height=\"24\" width=\"24\" style=\"margin: 0\"></button></td>\n";
        } else {
            html += "<td></td>"
        }
        html += "<td>" + electronsend + "</td>";
        if (item.sdtype != null && item.sdtype > 0) {
            html += "<td><button type='button' class='btn' onclick='viewWebCallInfo_his(" + i + ",\"选择送达方式信息\")'><img src=\"/img/detail.png\" height=\"24\" width=\"24\" style=\"margin: 0\"></button></td>\n";
        } else {
            html += "<td></td>";
        }
        html += "<td>" + _formatDate(item.createtime) + "</td>\n" +
            "<td>" + _sysCodeToCn(item.callstate, "SYS_DHZT") + "</td>\n";
        html += "<td>" + (item.remarks || "") + "</td>\n";
        html += "</tr>";
        $("#dhsdxx_his").append(html);
    }
}

function himDx(data, ssdr) {
    data = data || [];
    ssdr = ssdr || {};
    $("#dxtzxx_his").empty();
    for (var i = 0; i < data.length; i++) {
        var dxtz_html = "<tr>\n" +
            " <td>" + ssdr.ssdrmc + "</td>\n" +
            " <td>" + data[i].webtel + "</td>\n" +
            " <td>" + _formatDate(data[i].createtime) + "</td>\n";
        if (data[i].sendstate == 1) {
            dxtz_html += "<td>发送成功</td>";
        } else if (data[i].sendstate == 2) {
            dxtz_html += "<td>发送失败</td>";
        } else {
            dxtz_html += "<td>发送中</td>";
        }
        if (data[i].fwzt == 1) {
            dxtz_html += "<td>已访问</td>";
        } else if (data[i].fwzt == 0) {
            dxtz_html += "<td>未访问</td>";
        } else {
            dxtz_html += "<td>暂无</td>";
        }
        dxtz_html += "</tr>";
        $("#dxtzxx_his").append(dxtz_html);
    }
}

function himEms(data, ssdr) {
    data = data || [];
    ssdr = ssdr || {};
    showTable('emsbody_his', 'emsTbodyHtml_his', data);
}

function viewWebCallInfo_his(index, title) {
    var item = hisWebCall[index];
    viewWebCallInfo_item(item, title);
}

function getJkmStatus(e) {
    layer.msg("从非税系统获取缴款码状态时间可能较长,请耐心等待");
    var index = $(e).attr('data-index');
    var jkm = xqxx.ws[index].bz;
    $.ajax({
        url: "/getJkmStatus.aj",
        type: 'post',
        data: {
            fybh: wbrywdsdVO.pubYysdJbEntity.fybh,
            jkm: jkm
        },
        success: function (data) {
            alert(data.message);
        }, error: function () {
            alert("获取缴款码失败")
        }
    });
}

function upload_kdhz(e) {
    selectedEmsId = $(e).attr('data-index');
}

function look_wlxx(e) {
    var i = $(e).attr('data-index');
    $.ajax({
        url: "/kdcx.aj",
        type: 'post',
        data: {
            kddh: i,
            fybh: wbrywdsdVO.pubYysdJbEntity.fybh
        },
        success: function (data) {
            if (data.object == null || data.object.length == 0) {
                alert("暂无物流信息");
            } else {
                var test_data = {
                    title: '基本例子',
                    isAdmin: true,
                    list: data.object
                };
                var html = template('wlxx_html', test_data);
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 1,
                    shadeClose: true,
                    area: ['800px', '600px'],
                    content: html
                });
            }
        }, error: function () {
            alert("暂无物流信息")
        }
    });
}

function upload_emsSdjg(e) {
    var data = {
        yysdbh: yysdbh,
        kdid: $(e).attr('data-index')
    };
    var html = template('upload_sdjg_html', data);
    layer.open({
        type: 1,
        title: "上传送达结果",
        area: ['600px', '400px'],
        closeBtn: 1,
        shadeClose: true,
        skin: 'layui-layer-lan',
        content: html
    });
}

function delete_emsSdjg(e) {
    var data = {
        yysdbh: yysdbh,
        kdid: $(e).attr('data-index')
    };
    var options = {
        url: 'delete_sdjg.aj',
        type: 'post',
        data: data,
        success: function (data) {
            if (data.success) {
                layer.confirm("删除成功", {btn: ['确定']}, function () {
                    getEmssdInfo();
                    layer.closeAll()
                })
            } else {
                layer.confirm("删除失败", {btn: ['确定']}, function () {
                    layer.closeAll()
                })
            }
        },
        error: function () {
            layer.confirm("删除失败", {btn: ['确定']}, function () {
                layer.closeAll()
            })
        }
    };
    //modal遮盖
    layer.confirm("是否删除EMS送达-送达结果", {btn: ['确定']}, function () {
        $.ajax(options);
        layer.closeAll();
        layer.load(1, {shade: [0.1, '#fff']})
    })
}

function delete_Record(e, type) {
    var data = {
        yysdbh: yysdbh,
        fybh: fybh,
        kdid: $(e).attr('data-index')
    };
    var url = '';
    switch (type) {
        case 'ems': {
            url = 'delete_EmsRecord.do';
        }
    }
    var options = {
        url: url,
        type: 'post',
        data: data,
        success: function (data) {
            if (data.success) {
                layer.confirm("删除成功", {btn: ['确定']}, function () {
                    getEmssdInfo()
                    layer.closeAll()
                })
            } else {
                layer.confirm(data.message, {btn: ['确定']}, function () {
                    layer.closeAll()
                })
            }
        },
        error: function () {
            layer.confirm(data.message, {btn: ['确定']}, function () {
                layer.closeAll()
            })
        }
    }
    layer.confirm("是否删除EMS送达记录", {btn: ['确定']}, function () {
        $.ajax(options);
        layer.closeAll()
    })
}

function upload_sdjg_ems(e) {
    $.ajax({
        url: "/upload_sdjg.aj",
        type: 'post',
        data: {
            type: $(e).attr('data-type'),
            yysdbh: $(e).attr('data-yysdbh'),
            id: $(e).attr('data-id'),
            sdjg: $('#sdjg_select_ems').val(),
            wtfs: $('#select_wtfs_ems').val(),
            wtclrbh: $('#select_wtclr_ems').val()
        },
        success: function (data) {
            alert(data.message);
            layer.closeAll();
            location.reload();
        }, error: function () {
            alert("上传失败")
        }
    });
}


//WEB-CALL-INFO
var webcallid;

function editDhsd(e) {

    ssdr = {
        ssdrbh: $(e).attr('data-ssdrbh'),
        yysdbh: $(e).attr('data-yysdbh')
    };
    webcallid = $(e).attr('data-webcallid');
    var electronsend = $(e).attr('data-electronsend');
    if (electronsend == 0) {
        $("#editsftydzsd1").attr("checked", true);
    } else if (electronsend == 1) {
        $("#editsftydzsd2").attr("checked", true);
    } else {
        $("#editsftydzsd3").attr("checked", true);
    }
    var callstate = $(e).attr('data-callstate');
    var phoneStatus = ["", "有效-本人", "可联-非本人", "可联-关机", "可联-停机", "可联-未接通", "可联-再跟进", "可联-正忙", "空号", "呼叫受限", "可联-挂机", "代理人接听", "代理律师接听"];
    for (var i = 0; i < phoneStatus.length; i++) {
        if (phoneStatus[i] == callstate) {
            callstate = i;
            break;
        }
    }
    var remarks = $(e).attr('data-remarks');
    var phone = $(e).attr('data-phone');
    var address = $(e).attr('data-address');
    var sdtype = $(e).attr('data-sdtype');
    var sdfscontent = $(e).attr('data-sdfscontent');
    // if (electronsend=="0"){
    //     $("#editsftydzsd1").attr("checked","checked");
    // }
    // if (electronsend=="1"){
    //     $("#editsftydzsd2").attr("checked","checked");
    // }
    $("#remark").val(remarks);
    $("#sdtype").val(sdtype);
    $("#sdfscontent").val(sdfscontent);
    $("#confirmPhone").val(phone);
    $("#confirmAddress").val(address);
    $("#dhztSelect option[value='" + callstate + "']").prop("selected", true);
    $("#editDhsdModel").modal('show');
}

$("#editCallData").on("click", function () {

    // var sendType = $("#sendTypeModel").val();
    // var dxtzNumber = $("input[name='show']:checked").val();
    // var mailAddress = $("input[name='address']:checked").val();
    // if(sendType == 2){
    //     //邮寄送达-优先输入框内容
    //     if(null != $("#emsAddress").val() && undefined != $("#emsAddress").val() && $("#emsAddress").val().trim().length > 0){
    //         mailAddress = $("#emsAddress").val();
    //     }
    //     if(null == mailAddress || undefined == mailAddress || mailAddress.trim().length == 0){
    //         alert("请选择或输入邮寄送达地址")
    //         return;
    //     }
    // }else if(sendType == 3){
    //     //选择短信下发通知-所选号码-优先输入框内容
    //
    //     if(null != $("#dxtzNumber").val() && undefined != $("#dxtzNumber").val()&& $("#dxtzNumber").val().trim().length >0){
    //         dxtzNumber = $("#dxtzNumber").val();
    //     }
    //     if(null == dxtzNumber || undefined == dxtzNumber || dxtzNumber.trim().length == 0){
    //         alert("请选择或输入短信电子送达号码")
    //         return;
    //     }else {
    //         if(dxtzNumber.indexOf("1") != 0 || dxtzNumber.length != 11){
    //             alert("短信电子送达号码格式不规范:"+dxtzNumber)
    //             return;
    //         }
    //     }
    // }
    var electronsend = $("input[name='sftydzsd']:checked").val();
    var callState = $("#dhztSelect option:checked").val();
    // var sdtype =  $("#sdtype option:checked").val();
    if (callState == "") {
        alert("请选择电话状态！");
        return
    }
    $.ajax({
        url: "/editCallData.aj",
        type: 'post',
        data: {
            webcallid: webcallid,
            electronsend: electronsend,
            // sdtype: sdtype,
            // sdfscontent:$("#sdfscontent").val(),
            callState: callState,
            remarks: $("#remark").val(),
            phone: $("#confirmPhone").val(),
            address: $("#confirmAddress").val(),
            yysdbh: yysdbh,
            dsrbh: ssdr.ssdrbh,
        },
        success: function (data) {
            if (data.code == "200") {
                alert("修改成功");
                $("#editDhsdModel").modal('hide');
                location.reload();
            } else {
                alert(data.msg || "异常")
            }
        }, error: function () {
            alert("失败")
        }
    })
})

function deleteMsg(dxtzid) {
    if (confirm("确认删除吗？")) {
        $.ajax({
            url: "/deleteMsg.aj",
            type: 'post',
            data: {
                dxtzid: dxtzid,
            },
            success: function (data) {
                if (data.code == "200") {
                    alert("删除成功")
                    location.reload();
                } else {
                    alert(data.msg || "异常")
                }
            }, error: function () {
                alert("失败")
            }
        })
    }
}

function deletePhone(e) {
    if (confirm("确认删除吗？")) {
        var bh = $(e).attr('data-index');
        $.ajax({
            url: "/deletePhone.aj",
            type: 'post',
            data: {
                bh: bh,
            },
            success: function (data) {
                if (data.code == "200") {
                    alert("删除成功")
                    location.reload();
                } else {
                    alert(data.msg || "异常")
                }
            }, error: function () {
                alert("失败")
            }
        })
    }
}

function markHmDisable(e) {
    if (confirm("确认标记失效吗？")) {
        var bh = $(e).attr('data-index');
        $.ajax({
            url: "/ssdr/markHmDisable.aj",
            type: 'post',
            data: {
                bh: bh
            },
            success: function (data) {
                alert("标记成功");
                location.reload();
            }, error: function () {
                alert("失败")
            }
        })
    }
}

function markDzDisable(e) {
    if (confirm("确认标记失效吗？")) {
        var bh = $(e).attr('data-index');
        $.ajax({
            url: "/ssdr/markDzDisable.aj",
            type: 'post',
            data: {
                bh: bh
            },
            success: function (data) {
                alert("标记成功")
                location.reload();
            }, error: function () {
                alert("失败")
            }
        })
    }
}

function undoMarkHmDisable(e) {
    if (confirm("确认撤销标记吗？")) {
        var bh = $(e).attr('data-index');
        $.ajax({
            url: "/ssdr/undoMarkHmDisable.aj",
            type: 'post',
            data: {
                bh: bh
            },
            success: function (data) {
                alert("标记成功")
                location.reload();
            }, error: function () {
                alert("失败")
            }
        })
    }
}

function undoMarkDzDisable(e) {
    if (confirm("确认撤销标记吗？")) {
        var bh = $(e).attr('data-index');
        $.ajax({
            url: "/ssdr/undoMarkDzDisable.aj",
            type: 'post',
            data: {
                bh: bh
            },
            success: function (data) {
                alert("标记成功")
                location.reload();
            }, error: function () {
                alert("失败")
            }
        })
    }
}

function deleteAddress(e) {
    if (confirm("确认删除吗？")) {
        var bh = $(e).attr('data-index');
        $.ajax({
            url: "/deleteAddress.aj",
            type: 'post',
            data: {
                bh: bh,
            },
            success: function (data) {
                if (data.code == "200") {
                    alert("删除成功")
                    location.reload();
                } else {
                    alert(data.msg || "异常")
                }
            }, error: function () {
                alert("失败")
            }
        })
    }
}

function deleteLy(lylqid) {
    if (confirm("确认删除吗？")) {
        $.ajax({
            url: "/deleteLylq.aj",
            type: 'post',
            data: {
                lylqid: lylqid,
            },
            success: function (data) {
                if (data.code == "200") {
                    alert("删除成功")
                    location.reload();
                } else {
                    alert(data.msg || "异常")
                }
            }, error: function () {
                alert("失败")
            }
        })
    }
}

function look_dsr_ws(ssdrbh) {
    $.ajax({
        url: "/look_dsr_ws.aj",
        type: 'post',
        data: {
            yysdbh: yysdbh,
            ssdrbh: ssdrbh
        },
        success: function (data) {
            var test_data = {
                list: data.object
            };
            var html = template("pop_dsr_ws_html", test_data);
            layer.open({
                type: 1,
                title: false,
                area: ['700px', '450px'],
                closeBtn: 0,
                shadeClose: true,
                skin: 'yourclass',
                content: html
            });
        }, error: function () {
            alert("获取失败")
        }
    })
}

var lylqid;

function editLy(lyid) {
    lylqid = lyid
    $("#editLylqModel").modal('show');
}

$("#editLylqData").on("click", function () {
    var lylqSelect = $("#lylqSelect option:checked").val()
    if (lylqSelect == "") {
        alert("请选择领取状态！")
        return
    }
    $.ajax({
        url: "/editLylqData.aj",
        type: 'post',
        data: {
            lylqid: lylqid,
            lylqsSate: lylqSelect
        },
        success: function (data) {
            if (data.code == "200") {
                alert("修改成功")
                location.reload();
            } else {
                alert(data.msg || "异常")
            }
        }, error: function () {
            alert("失败")
        }
    })

})
var sdpyysdbh
var sdpssdrbh

function sdzt_update(e) {
    sdpyysdbh = $(e).attr("data-yysdbh");
    sdpssdrbh = $(e).attr("data-ssdrbh");
    $("#editSdztModel").modal('show');
    console.log(sdpssdrbh)
}

$("#editSdztData").on("click", function () {
    var sdzt = $("#sdztSelect option:checked").val()
    $.ajax({
        url: "/editSdztData.aj",
        type: 'post',
        data: {
            yysdbh: sdpyysdbh,
            sdjg: sdzt,
            ssdrbh: sdpssdrbh
        },
        success: function (data) {
            if (data.code == "200") {
                alert("修改成功")
                location.reload();
            } else {
                alert(data.msg || "异常")
            }
        }, error: function () {
            alert("失败")
        }
    })
})


function showInfoModel(val) {
    $('#phoneListA').html("");
    $('#addressRadioA').html("");
    if (val == 0) {
        $("#showAA").css("display", "none")
        $("#showBB").css("display", "none")
        $("#showCC").css("display", "none")
    }
    if (val == 1) {
        $("#showAA").css("display", "block")
        $("#showBC").css("display", "none")
        $("#showCC").css("display", "none")
    }
    if (val == 2) {
        showAddressA();
        $("#showBB").css("display", "block")
        $("#showAA").css("display", "none")
        $("#showCC").css("display", "none")
    }
    if (val == 3) {
        showPhoneA()
        $("#showCC").css("display", "block")
        $("#showAA").css("display", "none")
        $("#showBB").css("display", "none")
    }
}

function showAddressA() {
    var data = {
        yysdbh: ssdr.yysdbh,
        ssdrbh: ssdr.ssdrbh
    };
    $.ajax({
        url: "/ssdrdz/select_ssdrdz.zf",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var item = data[i]
                var html = "<div>";
                html += '<input id="addressRadioA' + i + '" type="radio" name="address" value="' + item.dz + '" />';
                html += '<label for="addressRadioA' + i + '">' + item.dz + '</label>';
                html += "</div>";
                $("#addressRadioA").append(html);
            }
        }, error: function () {
            alert("获取地址信息失败")
        }
    });

}

function showPhoneA() {

    //查询当事人号码
    var data = {
        yysdBh: ssdr.yysdbh,
        ssdrbh: ssdr.ssdrbh
    };
    $.ajax({
        url: "/dxtz/query_sdp_phone.zf",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            telDataList = data;
            for (var i = 0; i < telDataList.length; i++) {
                var item = telDataList[i];
                var telList = "<input type='radio' id='confirmNumber' name='show' class='radio-inline' value='" + item.showTel + "'/>" + item.showTel + " "
                $('#phoneListA').append(telList);
            }
        }, error: function () {
            alert("获取号码信息失败")
        }
    });
}

function addPhoneA(val) {
    var addPhone = "<div class='input-group'><input class=\"col-sm-9 form-control confirmNumber\" rows=\"3\" placeholder=\"请确认号码\"/><div onclick='deleteInput(this)' class='input-group-addon'>移除</div></div>";
    if (val == "addPhoneBA") {
        //短信电子送达号码
        var text = $("#phoneListInput").text();
        if (null != text && undefined != text && text.trim().length > 0) {
            //存在输入框 不处理
        } else {
            addPhone = "<div class='input-group'><input class=\"col-sm-9 form-control\" id='dxtzNumber' rows=\"3\" placeholder=\"请确认号码\"/><div onclick='deleteInput(this)' class='input-group-addon'>移除</div></div>";
            $('#phoneListInputA').html(addPhone);
        }
    }
}

function addAddressA(val) {
    var addAddress = "<div class='input-group'><input class=\"form-control confirmAddress\" rows=\"3\" placeholder=\"请确认地址\"/><div onclick='deleteInput(this)' class='input-group-addon'>移除</div></div>";
    if (val == "addAddressBA") {
        //邮寄送达地址
        var text = $('#addressInput').text();
        if (null != text && undefined != text && text.trim().length > 0) {
            //存在输入框 不处理
        } else {
            addAddress = "<div class='input-group'><input class=\"form-control\" id='emsAddress' rows=\"3\" placeholder=\"请确认地址\"/><div onclick='deleteInput(this)' class='input-group-addon'>移除</div></div>";
            $('#addressInputA').html(addAddress);
        }
    }
}

//填充表格统一方法
function loadTable(arrays, id, templateId) {
    var list = [];
    for (var i = 0; i < arrays.length; i++) {
        var hasContained = false;
        if (id == 'emssdInfos') {
            for (var j = 0; j < list.length; j++) {
                if (list[j][0].sjrxm == arrays[i].sjrxm) {
                    list[j].push(arrays[i]);
                    hasContained = true;
                }
            }

        } else {
            for (var j = 0; j < list.length; j++) {
                if (list[j][0].ssdrmc == arrays[i].ssdrmc) {
                    list[j].push(arrays[i]);
                    hasContained = true;
                }
            }
        }
        if (!hasContained) {
            var newList = [];
            newList.push(arrays[i]);
            list.push(newList);
        }
    }
    var isAdmin = false;
    if (yhjs == "admin") {
        isAdmin = true;
    }
    var test_data = {
        title: '基本例子',
        isAdmin: isAdmin,
        list: list
    };
    var htmlStr = template(templateId, test_data);
    $("#" + id).empty().append(htmlStr);
}

function plxzws(yysdbh, ssdrbh) {
    $.ajax({
        url: "/plwsqzqr.aj",
        type: 'get',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: {
            yysdbh: yysdbh,
        },
        success: function (data) {
            if (data.message == "成功") {
                plxz(yysdbh, ssdrbh);
            } else {
                alert(data.message);
            }
        }, error: function () {
            alert("获取文书信息失败")
        }
    });
}

function xzAllws(yysdbh, ssdrbh) {
    $.ajax({
        url: "/plwsqzqr.aj",
        type: 'get',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: {
            yysdbh: yysdbh,
        },
        success: function (data) {
            if (data.message == "成功") {
                xzAll(yysdbh, ssdrbh);
            } else {
                alert(data.message);
            }
        }, error: function () {
            alert("获取文书信息失败")
        }
    });
}

function plxz(yysdbh, ssdrbh) {
    var url = '/plxzws.do?yysdbh=' + yysdbh + '&ssdrbh=' + ssdrbh;
    window.open(url)
}

function xzAll(yysdbh, ssdrbh) {
    var url = '/xzAllws.do?yysdbh=' + yysdbh + '&ssdrbh=' + ssdrbh;
    window.open(url)
}

function maodian(e) {
    $(".miaodian").removeClass("focus")
    $(e).addClass("focus")
}

function downloadfkb() {
    location.href = "sdfk/downloadSdfkb.do?yysdbh="+yysdbh;
}

$('#ksrwlz').on("click",function () {
    var yysdbhList = [];
    yysdbhList.push(yysdbh)
    // if($(".wtlz .selectedWtclr").val()==null||$(".wtlz .selectedWtclr").val()==0){
    //     alert("未选中处理人");
    //     return;
    // }
    checkSdjgList(yysdbhList);

});

function checkSdjgList(yysdbhList) {
    $.ajax({
        url: "/checkSdjgList.aj",
        type: "post",
        traditional:true,
        data: {
            yysdbhList: yysdbhList
        },
        success: function (data) {
            if(data.message=="成功"){
                rwlzPop();
            }else {
                layer.msg(data.message, {
                    btn: ['确定', '取消']
                    ,yes: function(index){
                        rwlzPop();
                    }
                });
            }
        }, error: function () {
            alert("流转失败")
        }
    })
}
function rwlzPop() {

    layer.open({
        type: 1,
        title: "任务流转",
        area: ['30em', '15em'],
        content: $('#rwlz_pop')
    })
}

function submitRwlz() {
    if($("#select_wtclr").val()==null&&$("#select_wtclr").val()==0){
        alert("未选中处理人");
    }
    if($("#sdryselect").val()==""){
        alert("没有选择送达员");
        return;
    }
    // if($("#sdry_select").val()!==""&&$("#sdfysdy_select").val()!==""){
    //     alert("只能选择一个送达员");
    //     return;;
    // }
    var sdybh;
    if ($("#sdryselect").val()!==""){
        sdybh= $("#sdryselect").val();
    } else {
        sdybh = $("#sdfysdy_select").val();
    }
    var yysdbhList = [];
    yysdbhList.push(yysdbh)
    $.ajax({
        url: "/uploadRwlzList.aj",
        type: "post",
        data: {
            yysdbhList: yysdbhList,
            target:sdybh
        },
        success: function (data) {
            layer.closeAll();
            alert("流转成功")
        }, error: function () {
            layer.closeAll();
            alert("流转失败");
        }
    })
}