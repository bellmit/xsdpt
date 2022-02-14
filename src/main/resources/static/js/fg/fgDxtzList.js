$(document).ready(function () {
    xtyh.fybh = $.cookie('fybh');
    xtyh.yhm = $.cookie('yhm');

   getCurrentFyName(xtyh.fybh);

    //获取目标账户的短信通知记录
    loadDxtzList(-1);

});


var xtyh = {
    fybh: "",
    yhm: "",
};
var dxmbList = []; //全局短信模板list

function loadDxtzList(status) {
    $("#dxtzjbxx_table").dataTable().fnDestroy();
    $("#dxtzjbxx_tbody").html("");

        $.ajax({
        url: "/dxtz/fg_load_list.zf",
        type: 'post',
        data: {status:status},
        success: function (data) {
            show_table(data);
            $("#dxtzjbxx_table").dataTable().fnDestroy();
            $('#dxtzjbxx_table').DataTable({
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
    var html = template('dxtz_td_body', test_data);
    $("#dxtzjbxx_tbody").append(html);
}

$("#return_main").on("click", function () {
    location.href="jzsdpt.aj?fybh="+xtyh.fybh +"&yhm="+xtyh.yhm;
});


function layerErrorMsg(msg){
    //异常提醒
    layui.use("layer",function () {
        layui.layer.msg(msg,{icon:7})
    })
}

/**
 * 发送状态下拉框改变时间
 */
$("#select_send_state").on("change",function () {
    loadDxtzList($(this).val());
})


$('#dxtzjbxx_tbody').on("click",".msgcontent-look",function () {
    var msg = $(this).data("hint");
    layui.use("layer",function () {
        layui.layer.alert(msg);
    })
})

$("#dxtzjbxx_tbody").on("click",".look_zy",function(){
    var dxtzid = $(this).data("hint");
    window.open("dxtz/showImages_fg?yhm=" + xtyh.yhm + "&id=" + dxtzid);
});