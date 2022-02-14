$(document).ready(function () {
    var sd_way = $.cookie('way');
    xtyh.fybh = $.cookie('fybh');
    xtyh.ajxh = $.cookie('ajxh');
    xtyh.yhm = $.cookie('yhm');
    yhjs = $.cookie('yhjs');

    //获取目标账户的来院领取记录
    getTargetJudgeLylqInfos();

});


var xtyh = {
    fybh: "",
    ajxh: "",
    yhm: "",
};

var selectedLylqId = 0;
var selectedYysdbh_lylq;

/**
 * 查询来院记录列表
 */
function getTargetJudgeLylqInfos() {

    var data = {
        gdryxm: "11"
    }
    loadLylqList(data);
}
function loadLylqList(dataJson) {
    $("#lylqjbxx_table").dataTable().fnDestroy();
    $("#lylqjbxx_tbody").html("");
    $.ajax({
        url: "/lylq/load_list.zf",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(dataJson),
        success: function (data) {
            show_table(data);
            $("#lylqjbxx_table").dataTable().fnDestroy();
            $('#lylqjbxx_table').DataTable({
                bLengthChange: false, //去掉每页显示多少条数据方法
                iDisplayLength: 100,
                "info": false,   //去掉底部文字
                "scrollY": "500px", // 内部滚动条
                "scrollCollapse": "true", // 内部滚动条
                destory: true,
                "language": {
                    "emptyTable": "暂无数据",
                    "sSearch": "搜索:",
                    "oPaginate": {
                        "sPrevious": "上页",
                        "sNext": "下页"
                    }
                }
            });
            //绑定上传按钮监听方法
            bindUpload();
            $(".paginate_button ").on("click",function(){
                bindUpload();
            })
        }, error: function () {
            alert("获取案件信息失败")
        }

    });
    // bindUpload();
    // $(".paginate_button ").on("click",function(){
    //     bindUpload();
    // })
}

function show_table(data) {
    var test_data = {
        title: '基本例子',
        isAdmin: true,
        list: data
    };
    var html = template('lylq_td_body', test_data);
    $("#lylqjbxx_tbody").append(html);
}

$("#return_main").on("click", function () {
    if(yhjs=='admin'){
        location.href = "/wbryIndex"
    }else{
        location.href = "/gdIndex"
    }
});

/**
 * 上传 送达回执 单机事件
 */
$('#lylqjbxx_tbody').on("click",".upload_sdhz",function () {
    selectedLylqId = $(this).data("hint");

})

/**
 * 送达结果编辑按钮单机事件
 */
$('#lylqjbxx_tbody').on("click",".edit-sdjg",function () {
    selectedLylqId = $(this).data("hint");
})

/**
 * 查看 送达回执 单机事件
 */
$('#lylqjbxx_tbody').on("click",".look_sdhz",function () {
    // selectedLylqId = $(this).data("hint");
    // window.open("lylq/imgView_lylq_sdhz.zf?lylqId="+selectedLylqId);
})



/**
 * 打开新增弹窗 时  数据填充
 */
$("#add_lylq").on("click",function () {
    //获取当前登陆账号未结束的可见的工单列表
    $("#sdp").css("display","none");
    $("#lylq-yysj-input").val("");
    $("#lylq-address-input").val("");
    $("#lylq-address-div").html("");
    $("#lylq-yysdbh-input").html("<option value=''>请选择工单</option>")
    $.ajax({
        url: "/lylq/get_yysd_list.zf",
        type: 'post',
        success: function (data) {
            for(var i = 0; i < data.length; i++){
                $("#lylq-yysdbh-input").append("<option value=\""+data[i].yysdbh+"\">"+ data[i].yysdbh + " - "+data[i].ah+"</option>");
            }
            $("#lylq-yysdbh-input").selectpicker("refresh");
        }
    });
});
/**
 * 新增来院 - 提交
 */
$("#submit-lylq").on("click",function () {

    var sdpbh = $("#lylq-dsr").val();
    var yysdbh = $("#lylq-yysdbh-input").val();
    var yylqsj = $("#lylq-yysj-input").val();
    var lylqaddress = $("#lylq-address-input").val();
    if(!yysdbh){
        alert("请选择工单");
        return;
    }
    if(!yylqsj){
        alert("请选择预约时间");
        return;
    }
    if(!lylqaddress || !lylqaddress.trim()){
        alert("请填写预约地址");
        return;
    }
    var dsrwslbArray  = [];
    var checkedbox =  $('input[type=checkbox]:checked');
    $.each(checkedbox,function(){
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
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(lylqData),
        success: function (data) {
            alert("操作成功")
            //重新加载数据
            getTargetJudgeLylqInfos();
            //关闭弹窗
            $("#addLylqModel").modal('hide');
            //清空表单数据
            $("#lylq-yysj-input").val("");
            $("#lylq-address-input").val("");
        },error:function (res) {
            var errorMsg = res.responseJSON.message || '来院领取操作失败';
            alert(errorMsg)
        }
    });
});

$("#submit-edit-sdjg").on("click",function () {
    var sdjg = $("input[name='selectedSdjg']:checked").val();
    if(!sdjg){
        alert("请选择送达结果后操作");
        return;
    }
    var dataJson = {
        lylqId: selectedLylqId,
        sdjg: sdjg
    }
    updateLylqSdjg(dataJson);
});

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

function updateLylqSdjg(dataJson) {
    $.ajax({
        url: "/lylq/upload_sdhz.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data:JSON.stringify(dataJson),
        success: function (data) {
            location.reload();
            alert("操作成功")
            // //重新加载数据
            // getTargetJudgeLylqInfos();
            // //关闭弹窗
            // $("#editSdjgModel").modal('hide');
            // //清空表单数据
            // $("input[name='selectedSdjg']:checked").prop("checked",false);
        },error:function () {
            alert("送达结果更新失败")
        }
    });
}
function sdjg(e) {
    var lylqId = $(e).attr('data-hint');
    layer.confirm('选择当前方式送达结果', {
        btn: ['成功', '失败']
        , btn1: function (index, layero) {
            var dataJson = {
                lylqId: lylqId,
                sdjg: 1
            }
            updateLylqSdjg(dataJson)
        }, btn2: function (index, layero) {
            var dataJson = {
                lylqId: lylqId,
                sdjg: 2
            }
            updateLylqSdjg(dataJson)
        }
    });
}

$("#select_lq_state").on("change",function () {

    var data={
        lqstate: $(this).val()
    }
    loadLylqList(data);
})



$("#lylq-yysdbh-input").on("change",function () {
    $("#lylq-dsr").val("") //清空后填充
    $("#lylq-dsr").html("<option value=''>请选择当事人</option>") //清空后填充
    var thisval = $(this).val();
    selectedYysdbh_lylq = thisval;
    var data = {
        yysdbh: thisval
    };
    $.ajax({
        url: "/ssdr/query_ssdr_list.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(data),
        success: function (data) {
            $("#sdp").css("display","block");
            for (var i = 0; i < data.length; i++) {
                $("#lylq-dsr").append("<option value=\"" + data[i].ssdrbh + "\">" +  data[i].ssdrmc + "</option>");
            }
            $("#lylq-dsr").selectpicker("refresh");
            //第二步：显示短信下发主体内容
            $("#dxtz-zhuti").show();
        },error:function () {
            alert("获取当事人信息失败")
        }
    });
})

/**
 * 当事人选择 改变事件
 */
$("#lylq-dsr").on("change",function () {

    $.ajax({
        url: "/getDsrwslb.aj",
        type: 'post',
        data: {
            yysdbh: selectedYysdbh_lylq,
            ssdrbh: $(this).val()
        },
        success: function (data) {
            $("#wslbcheckboxlylqsd").html("");
            if(data.length == 0){
                $("#wslbcheckboxlylqsd").append("<input class=\"form-control lylq-yysj-input\" placeholder=\"无相应文书\"/>");
            }
            for (var i = 0; i < data.length; i++) {
                $("#wslbcheckboxlylqsd").append(
                    "<div class=\"form-check\">" +
                    "<label class=\"form-check-label\">" +
                    " <input type=\"checkbox\" class=\"form-check-input\" value=\""+ data[i].yysdbhBh + "\">" + data[i].ssdrmcWslb +
                    "</label>" +
                    "</div>"
                )
            }
        }
    });

    // $("#lylq-address-div").html("");
    // if($(this).val()){
    //     //查询受送达人下的地址信息
    //     var data = {
    //         yysdbh: selectedYysdbh_lylq,
    //         ssdrbh: $(this).val()
    //     };
    //     $.ajax({
    //         url: "/ssdrdz/select_ssdrdz.zf",
    //         type: 'post',
    //         headers:{'Content-type':'application/json'},
    //         dataType:"json",
    //         data: JSON.stringify(data),
    //         success: function (data) {
    //             for (var i = 0; i < data.length; i++) {
    //                 var item = data[i]
    //                 var html="<div>";
    //                 html += '<input id="addressRadio'+i+'" type="radio" name="address" value="'+item.dz+'" />';
    //                 html += '<label for="addressRadio'+i+'">'+item.dz+'</label>';
    //                 html +="</div>";
    //                 $("#lylq-address-div").append(html);
    //             }
    //         },error:function () {
    //             alert("获取号码信息失败")
    //         }
    //     });
    // }
})
//地址radio改变事件
$('#lylq-address-div').on("change","input",function () {
    $("#lylq-address-input").val($(this).val())
})

function upload_sdjg(e) {
    var yysdbh = $(e).attr('data-yysdbh');
    var lylqid = $(e).attr('data-lylqid');
    var data={
        yysdbh:yysdbh,
        lylqid:lylqid
    };
    var html = template('upload_sdjg_html',data);
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
function upload_sdjg_lylq(e) {
    $.ajax({
        url: "/upload_sdjg.aj",
        type: 'post',
        data: {
            type:$(e).attr('data-type'),
            yysdbh: $(e).attr('data-yysdbh'),
            id: $(e).attr('data-id'),
            sdjg:$('#sdjg_select_lylq').val(),
            wtfs:$('#select_wtfs_lylq').val(),
            wtclrbh:$('#select_wtclr_lylq').val()
        },
        success: function (data) {
            alert(data.message);
            var dataJson = {
                lylqId: $(e).attr('data-id'),
                sdjg: $('#sdjg_select_lylq').val()
            };
            layer.closeAll();
            location.reload();
        }, error: function () {
            alert("上传失败")
        }
    });
}

function bindUpload() {
    //绑定上传按钮事件
    layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '.upload_sdhz',
            accept: 'images',
            acceptMime: 'image/*',
            auto: false,
            bindAction: ".upload_sdhzA",
            field: 'lylqsdhz', //来院领取送达回执
            multiple: false,
            choose: function (obj) {
                obj.preview(function (index, file, result) {
                    var dataJson = {
                        lylqId: selectedLylqId,
                        sdhzBase64Str: result,
                        imageName:file.name
                    };
                    $.ajax({
                        url: "/lylq/upload_sdhz.zf",
                        type: 'post',
                        headers: {'Content-type': 'application/json'},
                        dataType: "json",
                        data: JSON.stringify(dataJson),
                        success: function (data) {
                            //重新加载数据
                            getTargetJudgeLylqInfos();
                        }, error: function () {
                            alert("回执上传失败")
                        }
                    });
                })
            }
        });
    });
}