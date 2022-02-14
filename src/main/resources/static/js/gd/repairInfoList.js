
var xtyh = {}
$(document).ready(function () {
    xtyh.yhm = $.cookie('yhm');
    yhjs = $.cookie('yhjs');

    getCurrentFyNameBySessionFybh();

    //获取目标账户的来院领取记录
    loadList({});


});

function loadList(dataObj) {
    $("#repair_list_table").dataTable().fnDestroy();
    $("#repair_list_tbody").html("");
    $.ajax({
        url: "/repairInfo/load_list.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(dataObj),
        success: function (data) {
            show_table(data);
            $("#repair_list_table").dataTable().fnDestroy();
            // $("#repair_list_table").dataTable({order:[[1,"desc"]]});
            $('#repair_list_table').DataTable({
                order:[[1,"desc"]], //设置默认排序
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
function show_table(data) {
    var test_data = {
        title: '基本例子',
        isAdmin: true,
        list: data,
    };
    var html = template('repair_list_td_body', test_data);
    $("#repair_list_tbody").append(html);
}

function gd_info(e){
    var yysdbh = $(e).attr('data-hint');
    $.cookie('yysdbh',yysdbh);
    window.open("wbrywdsd");
}

$("#select_repair_state").change(function () {
    var data={
        repairstatus: $(this).val()
    }
    loadList(data);
})

$("#return_main").on("click", function () {
    if(yhjs=='admin'){
        location.href = "/wbryIndex"
    }else{
        location.href = "/gdIndex"
    }
});
