$(document).ready(function () {
    var sd_way = $.cookie('way');
    xtyh.fybh = $.cookie('fybh');
    xtyh.ajxh = $.cookie('ajxh');
    xtyh.yhm = $.cookie('yhm');
    yhjs = $.cookie('yhjs');

    getCurrentFyName(xtyh.fybh);

    //获取目标账户的来院领取记录
    getTargetJudgeZjsdInfos();

});
var zjsdList = [];
var xtyh = {
    fybh: "",
    ajxh: "",
    yhm: "",
};
var selectedZjsdId = 0;
/**
 * 查询直接送达列表
 */
function getTargetJudgeZjsdInfos() {

    var data = {
        gdryxm: "11"
    }
    loadZjsdList(data);
}
function loadZjsdList(dataJson) {
    $("#zjsd_table").dataTable().fnDestroy();
    $("#zjsd_tbody").html("");
    $.ajax({
        url: "/zjsd/zjsdList",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(dataJson),
        success: function (data) {
            show_table(data);
            zjsdList = data;
            $("#zjsd_table").dataTable().fnDestroy();
            $('#zjsd_table').DataTable({
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


            // 绑定上传按钮事件
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
        list: data,
    };
    var html = template('zjsd_td_body', test_data);
    $("#zjsd_tbody").append(html);
}
$("#return_main").on("click", function () {
    if(yhjs=='admin'){
        location.href = "/wbryIndex"
    }else if(yhjs=='zjzy'){
        location.href = "/zjzyIndex"
    }else {
        location.href = "/gdIndex"
    }
});
/**
 * 上传 送达回执 单机事件
 */
$('#zjsd_tbody').on("click",".upload_zjsdhz",function () {
    selectedZjsdId = $(this).data("hint");
    console.log("************",selectedZjsdId)

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
/**
 * 送达结果编辑按钮单机事件
 */
$('#zjsd_tbody').on("click",".edit-sdjg",function () {
    selectedZjsdId = $(this).data("hint");
})
function updateZjsdSdjg(dataJson) {
    $.ajax({
        url: "/zjsd/updateByZjsdbh",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data:JSON.stringify(dataJson),
        success: function (data) {
            location.reload();
            alert("操作成功")
        },error:function () {
            alert("送达结果更新失败")
        }
    });
}
function downloadWj(e) {
    var key = $(e).attr('data-index');
    var zjsdbh = zjsdList[key].zjsdbh;
    var yysdbh = zjsdList[key].yysdbh;
    var wjid = zjsdList[key].wjid;
    var wjmc = zjsdList[key].wjmc
    window.open("/zjsd/downloadZjsdWj.do?yysdbh="+yysdbh+"&zjsdbh="+zjsdbh+"&wjid="+wjid+"&wjmc="+wjmc);
}
var selectedYysdbh;
var selectedzjsdbh;

function scWj(e) {
    var key = $(e).attr('data-index');
    selectedYysdbh = zjsdList[key].yysdbh;
    selectedzjsdbh = zjsdList[key].zjsdbh;
}

function cxscWj(e) {
    var key = $(e).attr('data-index');
    selectedYysdbh = zjsdList[key].yysdbh;
    selectedzjsdbh = zjsdList[key].zjsdbh;
}


function sdjg(e) {
    var zjsdbh = $(e).attr('data-hint');
    layer.confirm('选择当前方式送达结果', {
        btn: ['成功', '失败']
        , btn1: function (index, layero) {
            var dataJson = {
                zjsdbh: zjsdbh,
                zjsdjg: 1
            }
            updateZjsdSdjg(dataJson)
        }, btn2: function (index, layero) {
            var dataJson = {
                zjsdbh: zjsdbh,
                zjsdjg: 2
            }
            updateZjsdSdjg(dataJson)
        }
    });
}

$("#select_zj_state").on("change",function () {

    var data={
        qszt: $(this).val()
    }
    loadZjsdList(data);
})


/**
 * 查看 送达回执 单机事件
 */
$('#zjsd_tbody').on("click",".look_zjsdhz",function () {
    selectedZjsdId = $(this).data("hint");
    window.open("zjsd/showSdhz?zjsdbh="+selectedZjsdId);
});

/**
 * 查看 送达过程记录 单机事件
 * yhm=" + $.cookie('yhm') + "&&
 */
$('#zjsd_tbody').on("click",".look_zjsdjl",function () {
    selectedZjsdId = $(this).data("hint");
    $.cookie('way',"show");
    window.open("zjsd/showZjsdjl?id=" + selectedZjsdId);
});

function bindUpload() {
    layui.use('upload', function(){
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '.upload_zjsdhz' ,
            // url: '/zjsd/uploadFile',
            accept:'file',
            auto:true,
            // data:{"zjsdbh":$(this).data("hint")},
            done:function (res) {
                alert("上传成功")
                getTargetJudgeZjsdInfos();
            }
        });
    });
    layui.use('upload', function(){

        var upload = layui.upload;
        var uploadInst=upload.render({
            elem: '.uploadWj', //绑定元素
            url:'/zjsd/uploadZjsdWj.do', //上传接口
            method:'POST',
            multiple:false,
            data: {
                yysdbh:selectedYysdbh,
                zjsdbh:selectedzjsdbh
            },
            accept: 'file', //允许上传的文件类型
            size: 304800, //设置文件最大可允许上传的大小，单位 KB
            before:function(obj){
                $.extend(true,this.data,{
                    yysdbh:selectedYysdbh,
                    zjsdbh:selectedzjsdbh
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
            elem: '.secondUploadWj', //绑定元素
            url:'/zjsd/uploadZjsdWj.do', //上传接口
            method:'POST',
            multiple:false,
            data: {
                yysdbh:selectedYysdbh,
                zjsdbh:selectedzjsdbh
            },
            accept: 'file', //允许上传的文件类型
            size: 304800, //设置文件最大可允许上传的大小，单位 KB
            before:function(obj){
                $.extend(true,this.data,{
                    yysdbh:selectedYysdbh,
                    zjsdbh:selectedzjsdbh
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
}