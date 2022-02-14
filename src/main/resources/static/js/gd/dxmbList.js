var list;
var status = 1;

$(document).ready(function () {
    yhjs = $.cookie('yhjs');
    fybhc = $.cookie('fybh');
    getDxmbList();
    getCurrentFyName(fybhc);

});



//获取短信模板集合
function getDxmbList() {
    $.ajax({
        url: "/dxtz/get_template_list.zf",
        type: 'post',
        success: function (data) {
            list = data;
            $("#dxmb_table").dataTable().fnDestroy();
            show_table(data);
            $('#dxmb_table').DataTable({
                bLengthChange: false, //去掉每页显示多少条数据方法
                "bAutoWidth":false,//关闭列宽自动优化
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
                },

            });
        },error:function () {
            alert("获取短信模板信息失败")
        }
    });
}

function editDxmb(e){
    console.log(fybhc)
    if (fybhc==-1){
        $("#org").css("display","block")
    }
    var index = $(e).attr('data-index');
    var obj = list[index];
    $("#bh").val(obj.bh);
    $("#mbmc").val(obj.mbmc);
    $("#mbhsy").val(obj.mbhsy);
    $("#mbnr").val(obj.mbnr);
    $("#selectOrg option[value='"+obj.fybh+"']").prop("selected",true);
    if (obj.mbzt=="0"){
        $("#mbzt1").attr("checked","checked");
    }
    if (obj.mbzt=="1"){
        $("#mbzt2").attr("checked","checked");
    }
    $("#editDxmbModel").modal('show');
}

$("#submitEditDxmb").on("click",function () {
    var data = {
        bh: $("#bh").val().trim(),
        mbmc: $("#mbmc").val().trim(),
        mbhsy: $("#mbhsy").val().trim(),
        mbnr: $("#mbnr").val().trim(),
        mbzt: $("input[type=radio]:checked").val(),
        fybh: $("#selectOrg option:checked").val(),
    }
    $.ajax({
        url: "/dxtz/update_template.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(data),
        success: function (data) {
            alert("修改成功")
            $("#editDxmbModel").modal('hide');
            getDxmbList()
        },error:function () {
            alert("失败")
        }
    })
})

$("#add_dxmb").on("click",function (){
    console.log(fybhc)
    $("#addmbmc").val("")
    $("#addmbhsy").val("")
    $("#addmbnr").val("")
    if (fybhc==-1){
        $("#addorg").css("display","block")
    }
    $("#addDxmbModel").modal('show');
})

$("#submitAddDxmb").on("click",function () {
    if ($("#addmbmc").val()==""){
        alert("请输入模板名称！")
        return
    }
    if ($("#addmbhsy").val()==""){
        alert("请输入模板话术语！")
        return
    }
    if ($("#addmbnr").val()==""){
        alert("请输入模板内容！")
        return
    }
    var bh =  $("#addselectOrg option:checked").val()
    if (fybhc!=-1){
        bh = fybhc
    }
    if (bh==""){
        alert("请选择法院！")
        return
    }
    var data = {
        mbmc: $("#addmbmc").val().trim(),
        mbhsy: $("#addmbhsy").val().trim(),
        mbnr: $("#addmbnr").val().trim(),
        fybh: bh,
        mbzt: $("input[name=addDxmb]:checked").val()
    }
    $.ajax({
        url: "/dxtz/add_template.zf",
        type: 'post',
        headers:{'Content-type':'application/json'},
        dataType:"json",
        data: JSON.stringify(data),
        success: function (data) {
            alert("添加成功")
            $("#addDxmbModel").modal('hide');
            getDxmbList()
        },error:function () {
            alert("失败")
        }
    })
})

function show_table(data) {
    var test_data = {
        title: '基本例子',
        isAdmin: false,
        list: data,
    };
    var html = template('test', test_data);
    $("#dxmb_tbody").empty().append(html);
}

$("#return_main").on("click", function () {
    if(yhjs=='admin'){
        location.href = "/wbryIndex"
    }else{
        location.href = "/gdIndex"
    }

});

