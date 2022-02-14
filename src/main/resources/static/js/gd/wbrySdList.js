var fybh;
$(document).ready(function () {
    var yhm =  $.cookie('mc');
    $("#userName").append("<span>"+yhm+"</span>");
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

    var sd_way = $.cookie('way');
    xtyh.fybh = $.cookie('fybh');
    xtyh.ajxh = $.cookie('ajxh');
    xtyh.yhm = $.cookie('yhm');

    getCurrentFyName(xtyh.fybh);

    getUndistributedRecord();
    getSdry();
    getFyInfo();

    $("#select_distribute").change(function () {
        if($("#select_distribute").val() == '1'){
            $("#myfp").show();
            $("#yjfp").hide();
            getUndistributedRecord();
        }else{
            $("#myfp").hide();
            $("#yjfp").show();
            getAssignedRecord();
        }
    });

    $("#myfp_checkOrCancelAll").click(function () {
        checkOrCancelAll(this);
    });
    $("#yjfp_checkOrCancelAll").click(function () {
        checkOrCancelAll(this);
    });

});

var start;
var end;

$("#select_range_time").click(function () {
    start = $("#select_time1").val();
    end = $("#select_time2").val();

    flash_table();

});

function checkOrCancelAll(checkbox) {
    var hobbies = document.getElementsByName("ids");
    if(checkbox.checked){
        for (var i = 0; i < hobbies.length; i++) {
            hobbies[i].checked = true;
        }
     }else{
        for (var i = 0; i < hobbies.length; i++) {
            hobbies[i].checked = false;
        }
     }
}

var selectedYysdbh = [];
var xtyh = {
    fybh: "",
    ajxh: "",
    yhm: ""

};


$("body").on("click", "#distribute", function () {
    selectedYysdbh = [];
    if(getCheckedBox() > 0){
        // 显示modal
         $("#myModal").modal('show');
    }else{
        alert("请选择工单");
    }

});



function getCheckedBox() {
    $("input[name='ids']:checked").each(function (i) {
        selectedYysdbh.push($(this).val());
    });
    return selectedYysdbh.length;
}

$("#define_change").click(function () {
    if($("#sdry_select").val()==""&&$("#sdfysdy_select").val()==""){
        alert("没有选择送达员");
        return;
    }
    if($("#sdry_select").val()!==""&&$("#sdfysdy_select").val()!==""){
        alert("只能选择一个送达员");
        return;;
    }
    var sdybh;
    if ($("#sdry_select").val()!==""){
        sdybh= $("#sdry_select").val();
    } else {
        sdybh = $("#sdfysdy_select").val();
    }
    var temp_data = {
        sdrybh : sdybh,
        selectedYysdbh : selectedYysdbh
    };
    $.ajax({
        url: "/saveSdryAssigned.do",
        type: 'post',
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(temp_data),
        success: function (data) {
            // 先把selectedYysdbh清空，为了下次使用
            selectedYysdbh = [];
            // 隐藏modal
            $('#myModal').modal('hide');
            flash_table();

        }

    });
});

function flash_table() {
    if($("#select_distribute").val() == '1'){
        // console.log(' 现在在未分配页面')
        // 重新获取表格数据 刷新表格
        var dttable = $('#ajjbxx_table').dataTable();
        // dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
        getUndistributedRecord();
    }else{
        // console.log(' 现在在已分配页面')
        // 重新获取表格数据 刷新表格
        var dttable1 = $('#ajjbxx_distribute_table').dataTable();
        dttable1.fnDestroy(); //还原初始化了的datatable
        getAssignedRecord();
    }
}

function getSdry() {
    $.ajax({
        url: "/getSdry.do",
        type: 'POST',
        success: function (data) {
            for(var i = 0; i < data.length; i++){
                $("#sdry_select").append("<option value=\""+data[i].yhbh+"\">"+data[i].yhmc+"</option>");
            }

        }
    });
}

function getFyInfo() {
    $.ajax({
        url: "/getFyInfos.do",
        type: 'POST',
        success: function (data) {
            for(var i = 0; i < data.length; i++){
                $("#sdfy_select").append("<option value=\""+data[i].fybh+"\">"+data[i].jc+"</option>");
            }

        }
    });
}

/**
 * 送达法院选择 改变事件
 */
$("#sdfy_select").on("change",function () {
    $.ajax({
        url: "/getFySdry.do",
        type: 'POST',
        data:{
            fybh:$("#sdfy_select").val()
        },
        success: function (data) {
            $("#sdfysdy_select").empty();
            $("#sdfysdy_select").append("<option value=\"\">请选择送达员</option>");
            for(var i = 0; i < data.length; i++){
                $("#sdfysdy_select").append("<option value=\""+data[i].yhbh+"\">"+data[i].yhmc+"</option>");
            }

        }
    });

})

$("#download_sdfk_excel").click(function () {
    var start = $("#select_time1").val();
    var end = $("#select_time2").val();
    var status = $("#select_distribute").val();
    if(status == 1) {
        alert("只能导出已分配案件工单");
        return;
    }
    downloadSdxxInfo(start,end,status);
});

function downloadSdxxInfo(start,end,status) {
    var newEnd = end+" 23:59:59";
    var uri = "/download_rwfp_excel.do?start="+start+"&end="+newEnd+"&sdjg=" + status;
    window.open(uri)
}

function getUndistributedRecord() {
    var newEnd = end+" 23:59:59";
    $.ajax({
        url: "/getUndistributedRecord.do?start="+start+"&end="+newEnd,
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


function getAssignedRecord() {
    var newEnd = end+" 23:59:59";

    $.ajax({
        url: "/getAssignedRecord.do?start="+start+"&end="+newEnd,
        type:'POST',
        success: function (data) {
            show_distribute_table(data);
            $("#ajjbxx_distribute_table").dataTable().fnDestroy();
            var table1 = $('#ajjbxx_distribute_table').DataTable({
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

function show_distribute_table(data) {
    var test_data = {
        title: '基本例子',
        isAdmin: true,
        list: data
    };
    // console.log(data);
    var html = template('test1', test_data);
    $("#ajjbxx_distribute_tbody").empty().append(html);
}


$("#return_main").on("click", function () {
    location.href = "/wbryIndex";
});


$(".songda_button").click(function () {
    $.ajax({
        type: "POST",
        url: "getWay.aj",
        dataType: "text",
        success: function (data) {
            switch (data) {
                case "dh" :
                    /* alert(data);*/
                    window.open('http://130.39.111.182:8080/znsdgj/zngjlogin?ajID=750354&nAjlb=1&nFyid=51&loginId=xuzhil');
                    // location.href='http://130.39.111.182:8080/znsdgj/zngjlogin?ajID=750354&nAjlb=1&nFyid=51&loginId=xuzhil';
                    break;
                case "dz" :
                    /*alert(data);*/
                    window.open('http://130.1.198.91:8080/sfgk/artery/parse.do?action=parse&formId=f4ed83fafc880f02afbd2893ef874bb9&rtt=insert&user=weilt&timestamp=1572247830656&authcode=57c08162ba3561322eee50fbee17303f&fydm=69&funcPage=cjsd&uuid=e8afd032bf7e44d385e4fb5bb7172014');
                    // location.href='http://130.1.198.91:8080/sfgk/artery/parse.do?action=parse&formId=f4ed83fafc880f02afbd2893ef874bb9&rtt=insert&user=weilt&timestamp=1572247830656&authcode=57c08162ba3561322eee50fbee17303f&fydm=69&funcPage=cjsd&uuid=e8afd032bf7e44d385e4fb5bb7172014';
                    break;
                case "ems" :
                    /* alert(data);*/
                    window.open('http://130.1.1.150:8080/emsdyxt/sqkdd.do?ajxh=63565&isspxt=1');
                    // location.href='http://130.1.1.150:8080/emsdyxt/sqkdd.do?ajxh=63565&isspxt=1';
                    break;
                case "gg" :
                    /*   alert(data);*/
                    window.open('http://130.1.1.161:8080/Announcement/showGglr.do?ajxh=63565&&fydm=120000%20200&&yhbh=83');
                    // location.href='http://130.1.1.161:8080/Announcement/showGglr.do?ajxh=63565&&fydm=120000%20200&&yhbh=83';
                    break;
            }

        }
    })

});


function info(e){
    var yysdbh = $(e).attr('data-index');
    $.cookie('yysdbh',yysdbh);
    window.open("wbrywdsd");
}