$(document).ready(function () {
    xtyh.fybh = $.cookie('fybh');
    xtyh.yhm = $.cookie('yhm');

    getCurrentFyName(xtyh.fybh);

    //获取目标账户的来院领取记录
    loadLylqList(-1);
});


var xtyh = {
    fybh: "",
    yhm: "",
};

var jg = -1;

function loadLylqList(e) {
    $("#lylqjbxx_table").dataTable().fnDestroy();
    $("#lylqjbxx_tbody").html("");
    $.ajax({
        url: "/lylq/fgload_list.zf",
        type: 'post',
        data: {
            sdjg:e
        },
        success: function (data) {
            show_table(data);
            $("#lylqjbxx_table").dataTable().fnDestroy();
            $('#lylqjbxx_table').DataTable({
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
            //绑定上传按钮事件
            bindUpload();
            $(".paginate_button ").on("click",function(){
                bindUpload();
            });
        },error:function () {
            alert("获取案件信息失败")
        }
    });
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
    location.href="jzsdpt.aj?fybh="+xtyh.fybh +"&yhm="+xtyh.yhm;
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
 * 查看 送达回执 单击事件
 */
$('#lylqjbxx_tbody').on("click",".look_sdhz",function () {
    selectedLylqId = $(this).data("hint");
    // window.open("lylq/imgView_lylq_sdhz.zf?lylqId="+selectedLylqId);
})




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
            loadLylqList(jg);
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
            updateLylqSdjg(dataJson);
            layer.close(index);
        }, btn2: function (index, layero) {
            var dataJson = {
                lylqId: lylqId,
                sdjg: 2
            }
            updateLylqSdjg(dataJson);
            layer.close(index);

        }
    });
}

$("#select_lq_state").on("change",function () {
    jg = $(this).val();
    loadLylqList(jg);
})

function bindUpload() {
    layui.use('upload', function(){
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '.upload_sdhz' ,
            accept:'images',
            acceptMime: 'image/*',
            auto: false,
            bindAction: ".upload_sdhzA",
            field:'lylqsdhz', //来院领取送达回执
            multiple:false,
            choose: function (obj){
                obj.preview(function (index,file,result) {
                    var dataJson = {
                        lylqId: selectedLylqId,
                        sdhzBase64Str: result
                    }
                    $.ajax({
                        url: "/lylq/upload_sdhz.zf",
                        type: 'post',
                        headers:{'Content-type':'application/json'},
                        dataType:"json",
                        data:JSON.stringify(dataJson),
                        success: function (data) {
                            //重新加载数据
                            loadLylqList(jg);
                        },error:function () {
                            alert("回执上传失败")
                        }
                    });
                })
            }
        });
    });
}

