$(document).ready(function () {
    var sd_way = $.cookie('way');
    xtyh.fybh = $.cookie('fybh');
    xtyh.ajxh = $.cookie('ajxh');
    yhjs = $.cookie('yhjs');
    xtyh.yhm = $.cookie('yhm');

    getCurrentFyName(xtyh.fybh);

    loadList();
});
var xtyh = {
    fybh: "",
    ajxh: "",
    yhm: ""
};

var selectedYysdbh;
var selectedKdid;
var emsList=[];
var yysdList;
var addedYysdbh;
var addedSsdrbh;
function loadList() {
    $.ajax({
        url: "/getEmsGd.aj",
        type: "post",
        success: function (data) {
            emsList=data;
            showTable('ems_body', 'ems_body_html', data);
            $("#ems_table").dataTable().fnDestroy();
            $('#ems_table').DataTable({
                bLengthChange: false, //去掉每页显示多少条数据方法
                iDisplayLength: 20,
                "info": false,   //去掉底部文字
                "scrollY": "500px", // 内部滚动条
                "order": [],//禁用自动排序
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
            });

        },error:function () {
            alert("获取EMS信息失败")
        }
    })
}

function scmd(e) {
    var key = $(e).attr('data-index');
    selectedYysdbh = emsList[key].yysdbh;
    selectedKdid = emsList[key].kdid;
}

function downloadMd(e) {
    var key = $(e).attr('data-index');
    var kdid = emsList[key].kdid;
    var yysdbh = emsList[key].yysdbh;
    window.open("/downloadEmsmd.do?yysdbh="+yysdbh+"&kdid="+kdid);
}

function uploadKddh(e) {
    var key = $(e).attr('data-index');
    var kdid = emsList[key].kdid;
    var yysdbh = emsList[key].yysdbh;
    var html ="<div style=\"float: left;font-size: 15px;color: rgb(0,169,238);margin-left: 15px;margin-top: 30px;\">快递单号:&nbsp<input id=\"input-kddh\" ></div>";
    layer.open({
        type: 1,
        title: '上传快递单号',
        shadeClose: true,
        area: ['300px', '150px'],
        content: html,
        btn: ["确认提交"],
        closeBtn: 1,
        btn1:function (index, layero) {
            $.ajax({
                url: "/uploadKddh.aj",
                type: 'POST',
                dataType: 'json',
                data:{
                    yysdbh:yysdbh,
                    kdid:kdid,
                    kddh:$("#input-kddh").val()
                },
                success: function (data) {
                    alert(data.message);
                    location.reload();
                },error:function(){
                    alert("提交失败");
                    location.reload();
                }
            });
        }
    })
}

/**
 * 打开新增弹窗 时  数据填充
 */
$("#add_ems").on("click", function () {
    var yysdbh = $("#yysdbh");
    var dsr = $("#dsr");
    var dsr_dh = $("#dsr_dh");
    var dsr_dz = $("#dsr_dz");
    yysdbh.empty().append("<option value='0'>" + "请选择工单" + "</option>");
    dsr.empty().append("<option value='0'>" + "请选择当事人" + "</option>").selectpicker("refresh");
    dsr_dh.empty().append("<option value='0'>" + "请选择电话" + "</option>").selectpicker("refresh");
    dsr_dz.empty().append("<option value='0'>" + "请选择地址" + "</option>").selectpicker("refresh");
    $.ajax({
        url: "/lylq/get_yysd_list.zf",
        type: 'post',
        success: function (data) {
            yysdList = data || [];
            for (var i = 0; i < data.length; i++) {
                yysdbh.append("<option value=\"" + data[i].yysdbh + "\">" + data[i].yysdbh + " - " + data[i].ah + "</option>");
            }
            yysdbh.selectpicker("refresh");
        }
    });
});


$(document).on('change', '#yysdbh', function() {
    var dsr = $("#dsr");
    var dsr_dh = $("#dsr_dh");
    var dsr_dz = $("#dsr_dz");
    var thisVal = $(this).val();
    dsr.empty().append("<option value='0'>" + "请选择当事人" + "</option>").selectpicker("refresh");
    dsr_dh.empty().append("<option value='0'>" + "请选择电话" + "</option>").selectpicker("refresh");
    dsr_dz.empty().append("<option value='0'>" + "请选择地址" + "</option>").selectpicker("refresh");
    if(thisVal==0){
        return;
    }
    addedYysdbh= $(this).val();
    data={
        yysdbh:addedYysdbh
    };
    $.ajax({
        url: "/ssdr/query_ssdr_list.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(data),
        success: function (data) {

            for (var i = 0; i < data.length; i++) {
                dsr.append("<option value=\"" + data[i].ssdrbh + "\">" +  data[i].ssdrmc + "</option>");
            }
            dsr.selectpicker("refresh");
        }
    });
});

$(document).on('change', '#dsr', function() {
    var thisVal = $(this).val();
    var dsr_dh = $("#dsr_dh");
    var dsr_dz = $("#dsr_dz");
    dsr_dh.empty().append("<option value='0'>" + "请选择电话" + "</option>").selectpicker("refresh");
    dsr_dz.empty().append("<option value='0'>" + "请选择地址" + "</option>").selectpicker("refresh");
    if(thisVal==0){
        return;
    }
    addedSsdrbh = $(this).val();
    data={
        yysdBh:addedYysdbh,
        ssdrbh:addedSsdrbh
    };
    $.ajax({
        url: "/dxtz/query_sdp_phone.zf",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                if(data[i] != null && data[i].operatorType != null && data[i].operatorType === "ENTRY"){
                    var lableStr = "【录入】";
                    if(data[i].hmly === "HIS_YYSD"){
                        lableStr = "【历史工单】";
                    }else if(data[i].hmly === "HIS_CASE"){
                        lableStr = "【历史案件】";
                    }
                    dsr_dh.append("<option value=\"" + data[i].operatorTel + "\">" + lableStr +data[i].showTel + "</option>");
                }
            }
            dsr_dh.selectpicker("refresh");
        }, error: function () {
            alert("获取号码信息失败")
        }
    });
    data={
        yysdbh:addedYysdbh,
        ssdrbh:addedSsdrbh
    };


    $.ajax({
        url: "/ssdrdz/select_ssdrdz.zf",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                if(data[i].dzly=='HIS_CASE'){
                    dsr_dz.append("<option value=\"" +data[i].dz + "\">" + "【历史案件】"+  data[i].dz + "</option>");
                }else if(data[i].dzly=='HIS_YYSD'){
                    dsr_dz.append("<option value=\"" +data[i].dz + "\">" + "【历史工单】"+  data[i].dz + "</option>");
                }else {
                    dsr_dz.append("<option value=\"" + data[i].dz + "\">" +  data[i].dz + "</option>");
                }
            }
            dsr_dz.selectpicker("refresh");
        }, error: function () {
            alert("获取地址信息失败")
        }
    });
});
$(document).on('click', '#submit_ems', function() {
    if($("#yysdbh").val()==0){
        alert("请选择工单");
        return;
    }
    if($("#dsr").val()==0){

        $("#addEmsModel").modal('hide');
        $.ajax({
            url: "/gdemssd.aj",
            type: 'post',
            data:{
              yysdbh:$("#yysdbh").val()
            },
            success: function (data) {
                window.open(data.object);
            }, error: function () {
                alert("获取地址信息失败")
            }
        });
        return;
    }
    emsdata={
        yysdbh:addedYysdbh,
        ssdrbh:addedSsdrbh,
        dh:[$("#dsr_dh").val()],
        dz:$("#dsr_dz").val()
    };
    $("#addEmsModel").modal('hide');
    $.ajax({
        url: "/add_emsGd.aj",
        type: 'post',
        headers: {'Content-type': 'application/json'},
        dataType: "json",
        data: emsdata,
        success: function (data) {
          window.open(data.object);
        }, error: function () {
            alert("获取地址信息失败")
        }
    });

})

function look_wlxx(e) {
    var i = $(e).attr('data-index');
    $.ajax({
        url: "/kdcx.aj",
        type: 'post',
        data: {
            kddh:emsList[i].kddh,
            fybh:emsList[i].fybh
        },
        success: function (data) {
            if(data.object==null||data.object.length==0){
                alert("暂无物流信息");
            }else {
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

function upload_kdhz(e) {
    var key = $(e).attr('data-index');
    selectedYysdbh = emsList[key].yysdbh;
    selectedKdid = emsList[key].kdid;
}
function look_kdhz(e) {
    var key = $(e).attr('data-index');
    window.open("/ems_sdhz_show.do?kdid="+emsList[key].kdid+"&fybh="+emsList[key].fybh);
}
function upload_emsSdjg(e) {
    var key = $(e).attr('data-index');
    var yysdbh = emsList[key].yysdbh;
    var kdid = emsList[key].kdid;
    var data={
        yysdbh:yysdbh,
        kdid:kdid
    }
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

function upload_sdjg_ems(e) {
    $.ajax({
        url: "/upload_sdjg.aj",
        type: 'post',
        data: {
            type:$(e).attr('data-type'),
            yysdbh: $(e).attr('data-yysdbh'),
            id: $(e).attr('data-id'),
            sdjg:$('#sdjg_select_ems').val(),
            wtfs:$('#select_wtfs_ems').val(),
            wtclrbh:$('#select_wtclr_ems').val()
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
$("#return_main").on("click", function () {
    if(yhjs=='admin'){
        location.href = "/wbryIndex"
    }else{
        location.href = "/gdIndex"
    }

});

function bindUpload() {
    layui.use('upload', function(){

        var upload = layui.upload;
        var uploadInst=upload.render({
            elem: '.upload_kdhz', //绑定元素
            url: '/upload_ems_kdhz.do', //上传接口
            method:'POST',
            multiple:false,
            data: {
                yysdbh:selectedYysdbh,
                kdid:selectedKdid
            },
            acceptMime: 'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document, text/plain,application/pdf,image/*',
            size: 204800, //设置文件最大可允许上传的大小，单位 KB
            before:function(obj){
                $.extend(true,this.data,{
                    yysdbh:selectedYysdbh,
                    kdid:selectedKdid
                })
            },
            done: function(res){
                //上传完毕回调
                alert(res.message);
                location.reload();
            },

            error: function(){
                //请求异常回调
                alert("上传失败",{icon: 0});
            }
        });
    });

    layui.use('upload', function(){

        var upload = layui.upload;
        var uploadInst=upload.render({
            elem: '.uploadEmsmd', //绑定元素
            url: '/uploadEmsmd.do', //上传接口
            method:'POST',
            multiple:false,
            data: {
                yysdbh:selectedYysdbh,
                kdid:selectedKdid
            },
            accept: 'images', //允许上传的文件类型
            size: 204800, //设置文件最大可允许上传的大小，单位 KB
            before:function(obj){
                $.extend(true,this.data,{
                    yysdbh:selectedYysdbh,
                    kdid:selectedKdid
                })
            },
            done: function(res){
                //上传完毕回调
                alert(res.message);
                location.reload();
            },

            error: function(){
                //请求异常回调
                alert("上传失败",{icon: 0});
            }
        });
    })

}