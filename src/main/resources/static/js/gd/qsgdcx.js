var start;
var end;
var yysdbh;
$(document).ready(function () {

    getCurrentFyNameBySessionFybh();

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
    getFyInfo();
    getLastMonthTime();
    getNowTime();
    start = getLastMonthTime();
    end = getNowTime();

    $("#select_time1").val(start);
    $("#select_time2").val(end);

    initTable();
})

function initTable() {
    $("#gdxx_table").dataTable().fnDestroy();
    $("#gdxx_table").DataTable({
        bLengthChange: false, //去掉每页显示多少条数据方法
        iDisplayLength: 20,
        "search":false,
        "bFilter" : false,
        "bInfo":false,
        'paging':false,
        "scrollY": "500px", // 内部滚动条
        "scrollCollapse": "true", // 内部滚动条
        destory: true,
        "language" : {
            "emptyTable":"暂无数据",
        }
    });
}

function getFyInfo() {
    $.ajax({
        url: "/getFyInfos_cx.do",
        type: 'POST',
        success: function (data) {
            for(var i = 0; i < data.length; i++){
                $("#gdcx_fy").append("<option value=\""+data[i].fybh+"\">"+data[i].jc+"</option>");
            }

        }
    });
}


$("#gdcx_sybmit").on("click",function(){
    var data ={};
    data["fybh"]=  $("#gdcx_fy").val();
    data["yysdbh"]=$("#gdcx_yysdbh").val();
    data["ah"]=$("#gdcx_ah").val();
    data["ajmc"]=$("#gdcx_ajmc").val();
    data["ssdr"]=$("#gdcx_ssdr").val();
    data["yyr"]=$("#gdcx_yyr").val();
    data["sdr"]=$("#gdcx_sdr").val();
    data["sdzt"]=$("#gdcx_sdzt").val();
    data["startTime"]=$("#select_time1").val();
    data["endTime"]=$("#select_time2").val()+" 23:59:59";;
    $.ajax({
        url:"gdcx.aj" ,
        type:'POST',
        contentType:'application/json',
        data:JSON.stringify(data),
        success: function (data) {
            show_table(data);
            var table_info_html = "共"+data.length+"条数据";
            $("#table_info").empty().html(table_info_html);
        },error:function () {
            layer.msg("查询失败");
        }

    });
})


function show_table(data) {
    var test_data = {
        list: data
    };
    var htmlstr = template('test', test_data);
    $("#gdxx_tbody").empty().html(htmlstr);
}



function info(e){
    var yysdbh = $(e).attr('data-index');
    $.cookie('yysdbh',yysdbh);
    window.open("wbrywdsd");
}
//送达状态修改弹窗
function editSdzt(e) {
    var yysdjb = $(e).attr('data-index');
    yysdbh = yysdjb;
    $("#editSdztModel").modal('show');
}
//修改送达状态
$("#editSdzt").on("click",function () {
    var sdzt = $("#sdztSelect option:checked").val()
    $.ajax({
        url: "/editSdzt.aj",
        type: 'post',
        data : {
            yysdbh: yysdbh,
            sdzt: sdzt
        },
        success: function (data) {
            if (data.code=="200"){
                alert("修改成功")
                $("#editSdztModel").modal('hide');
                initTable();
            }else {
                alert(data.msg || "异常")
            }
        },error:function () {
            alert("失败")
        }
    })
})