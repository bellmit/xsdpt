var fybh;
$(document).ready(function () {
    start = getLastMonthTime();
    end = getNowTime();
    $("#select_time1").val(start);
    $("#select_time2").val(end);
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
    getUnFeedbackdRecord();
    $("#select_distribute").change(function () {
        if($("#select_distribute").val() == '1'){
            getUnFeedbackdRecord();
        }else{
            getFeedbackRecord();
        }
    });
});
var start;
var end;
$("#select_range_time").click(function () {
    start = $("#select_time1").val();
    end = $("#select_time2").val();
    flash_table();
});



var selectedYysdbh = [];
var xtyh = {
    fybh: "",
    ajxh: "",
    yhm: ""

};


$("body").on("click", "#feedback", function () {
    selectedYysdbh = [];
    $("input[name='ids']:checked").each(function (i) {
        selectedYysdbh.push($(this).val());
    });
});

$("#define_change").click(function () {
    var temp_data = {
        selectedYysdbh : selectedYysdbh
    };
    $.ajax({
        url: "/saveFeedback.do",
        type: 'post',
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(temp_data),
        success: function (data) {
            // 先把selectedYysdbh清空，为了下次使用
            selectedYysdbh = [];
            flash_table();
        }
    });
});

function flash_table() {
    var dttable = $('#ajjbxx_table').dataTable();
    dttable.fnDestroy();
    console.log($("#select_distribute").val());
    if($("#select_distribute").val() == '1'){
        getUnFeedbackdRecord();
    }else{
        getFeedbackRecord();
    }
}
function getUnFeedbackdRecord() {
    var newEnd = end+" 23:59:59";
    $.ajax({
        url: "/getUnFeedbackRecord.do?start="+start+"&end="+newEnd,
        type:'POST',
        success: function (data) {
            show_table(data);
            $("#ajjbxx_table").dataTable().fnDestroy();
            var table = $('#ajjbxx_table').DataTable({
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
        }, error: function () {
            alert("获取案件信息失败")
        }
    });
}


function getFeedbackRecord() {
    var newEnd = end+" 23:59:59";

    $.ajax({
        url: "/getFeedbackRecord.do?start="+start+"&end="+newEnd,
        type:'POST',
        success: function (data) {
            show_table(data);
            $("#ajjbxx_table").dataTable().fnDestroy();
            var table1 = $('#ajjbxx_table').DataTable({
                bLengthChange: false, //去掉每页显示多少条数据方法
                iDisplayLength: 20,
                "info": false,   //去掉底部文字
                "scrollY": "500px",
                "scrollCollapse": "true",
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
        }, error: function () {
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
    // console.log(data);
    var html = template('test', test_data);
    $("#ajjbxx_tbody").empty().append(html);
}


function info(e){
    var yysdbh = $(e).attr('data-index');
    $.cookie('yysdbh',yysdbh);
    window.open("wbrywdsd");
}

function showBz(bz) {
    layui.use("layer", function () {
        layui.layer.alert(bz);
    })
}